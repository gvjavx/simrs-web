package com.neurix.simrs.transaksi.checkup.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.dao.CheckupAlergiDao;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.*;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.diagnosarawat.dao.DiagnosaRawatDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Toshiba on 08/11/2019.
 */

public class CheckupBoImpl extends BpjsService implements CheckupBo {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);

    private HeaderCheckupDao headerCheckupDao;
    private CheckupDetailDao checkupDetailDao;
    private ProvinsiDao provinsiDao;
    private DokterTeamDao dokterTeamDao;
    private CheckupAlergiDao checkupAlergiDao;
    private BranchDao branchDao;

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public CheckupBoImpl() throws GeneralSecurityException, IOException {
    }
    private DiagnosaRawatDao diagnosaRawatDao;

    @Override
    public List<HeaderCheckup> getByCriteria(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {

            Boolean isPoli = false;
            Boolean isStatus = false;
            Map hsCriteria = new HashMap();

            //sodiq, 17 Nov 2019, penambahan no_checkup
            if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                isPoli = true;
                hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
            }
            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                isStatus = true;
                hsCriteria.put("status_periksa", bean.getStatusPeriksa());
            }
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                hsCriteria.put("id_pasien", bean.getIdPasien());
            }
            if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())) {
                hsCriteria.put("no_ktp", bean.getNoKtp());
            }
            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
                hsCriteria.put("nama", bean.getNama());
            }
            if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            hsCriteria.put("flag", "Y");
            List<String> listOfNoCheckup = new ArrayList<>();

            try {
                listOfNoCheckup = headerCheckupDao.getListNoCheckupByCriteria(hsCriteria, isPoli, isStatus);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.getByCriteria] error when get data by criteria on getListNoCheckupByCriteria "+ e.getMessage());
            }

            if (!listOfNoCheckup.isEmpty()){
                hsCriteria = new HashMap();
                hsCriteria.put("list_no_checkup", listOfNoCheckup);
                List<ItSimrsHeaderChekupEntity> headerChekupEntities = null;
                try {
                    headerChekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
                } catch (HibernateException e){
                    logger.error("[CheckupBoImpl.getByCriteria] error when get data by criteria "+ e.getMessage());
                }

                if (!headerChekupEntities.isEmpty()){
                    logger.info("[CheckupBoImpl.getByCriteria] End <<<<<<<");
                    return setTemplateToHeaderCheckupResult(headerChekupEntities);
                }
            }
        }
        logger.info("[CheckupBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String s) {
        return null;
    }

    public List<HeaderCheckup> setTemplateToHeaderCheckupResult(List<ItSimrsHeaderChekupEntity> listHeader){
        logger.info("[CheckupBoImpl.setTemplateToHeaderCheckupResult] Start >>>>>>>");

        List<HeaderCheckup> result = new ArrayList<>();

        HeaderCheckup headerCheckup;
        for (ItSimrsHeaderChekupEntity headerList : listHeader){
            headerCheckup = new HeaderCheckup();
            headerCheckup.setNoCheckup(headerList.getNoCheckup());
            headerCheckup.setIdPasien(headerList.getIdPasien());
            headerCheckup.setNama(headerList.getNama());
            headerCheckup.setJenisKelamin(headerList.getJenisKelamin());
            headerCheckup.setNoKtp(headerList.getNoKtp());
            headerCheckup.setTempatLahir(headerList.getTempatLahir());
            headerCheckup.setTglLahir(headerList.getTglLahir());
            headerCheckup.setStTglLahir(headerCheckup.getTglLahir().toString());
            headerCheckup.setDesaId(headerList.getDesaId());
            headerCheckup.setJalan(headerList.getJalan());
            headerCheckup.setSuku(headerList.getSuku());
            headerCheckup.setAgama(headerList.getAgama());
            headerCheckup.setProfesi(headerList.getProfesi());
            headerCheckup.setNoTelp(headerList.getNoTelp());
            headerCheckup.setIdJenisPeriksaPasien(headerList.getIdJenisPeriksaPasien());
            headerCheckup.setKeteranganKeluar(headerList.getKeteranganKeluar());
            headerCheckup.setTinggi(headerList.getTinggi());
            headerCheckup.setBerat(headerList.getBerat());

            if(headerList.getUrlKtp() != null && !"".equalsIgnoreCase(headerList.getUrlKtp())){
                String src = CommonConstant.URL_IMG + CommonConstant.RESOURCE_PATH_KTP_PASIEN + headerList.getUrlKtp();
                headerCheckup.setUrlKtp(src);
            }

            logger.info("[CheckupBoImpl.getByCriteria] URL KTP : "+headerCheckup.getUrlKtp());
            headerCheckup.setBranchId(headerList.getBranchId());
            headerCheckup.setFlag(headerList.getFlag());
            headerCheckup.setCreatedDate(headerList.getCreatedDate());
            headerCheckup.setLastUpdate(headerList.getLastUpdate());
            headerCheckup.setCreatedWho(headerList.getCreatedWho());
            headerCheckup.setLastUpdateWho(headerList.getLastUpdateWho());
            headerCheckup.setNamaPenanggung(headerList.getNamaPenanggung());
            headerCheckup.setHubunganKeluarga(headerList.getHubunganKeluarga());
            headerCheckup.setRujuk(headerList.getRujuk());
            headerCheckup.setJenisKunjungan(headerList.getJenisKunjungan());

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            try {
                headerDetailCheckup = headerCheckupDao.getLastPoliAndStatus(headerList.getNoCheckup());
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.setTemplateToHeaderCheckupResult] error when get data on getLastPoliAndStatus "+ e.getMessage());
            }

            if (headerDetailCheckup != null){
                headerCheckup.setIdPelayanan(headerDetailCheckup.getIdPelayanan());
                headerCheckup.setStatusPeriksa(headerDetailCheckup.getStatusPeriksa());
                headerCheckup.setStatusPeriksaName(headerDetailCheckup.getStatusPeriksaName());
                headerCheckup.setNamaPelayanan(headerDetailCheckup.getNamaPelayanan());
                headerCheckup.setNamaRuangan(headerDetailCheckup.getNamaRuangan());
                headerCheckup.setNoRuangan(headerDetailCheckup.getNoRuangan());
                headerCheckup.setIdDetailCheckup(headerDetailCheckup.getIdDetailCheckup());

                DokterTeam dokterTeam = new DokterTeam();
                dokterTeam.setIdDetailCheckup(headerDetailCheckup.getIdDetailCheckup());

                List<ItSimrsDokterTeamEntity> teamEntityList = getListEntityTeamDokter(dokterTeam);
                ItSimrsDokterTeamEntity teamEntity = new ItSimrsDokterTeamEntity();
                if (!teamEntityList.isEmpty()){
                    teamEntity = teamEntityList.get(0);
                }
                if(teamEntity != null){
                    headerCheckup.setIdDokter(teamEntity.getIdDokter());
                    headerCheckup.setIdTeamDokter(teamEntity.getIdTeamDokter());
                }
            }

            if (headerCheckup.getDesaId() != null){
                List<Object[]> objs = provinsiDao.getListAlamatByDesaId(headerCheckup.getDesaId().toString());
                if (!objs.isEmpty()){
                    for (Object[] obj : objs){
                        headerCheckup.setNamaDesa(obj[0].toString());
                        headerCheckup.setNamaKecamatan(obj[1].toString());
                        headerCheckup.setNamaKota(obj[2].toString());
                        headerCheckup.setNamaProvinsi(obj[3].toString());
                        headerCheckup.setKecamatanId(obj[4].toString());
                        headerCheckup.setKotaId(obj[5].toString());
                        headerCheckup.setProvinsiId(obj[6].toString());
                    }
                }
            }

            result.add(headerCheckup);
        }

        logger.info("[CheckupBoImpl.setTemplateToHeaderCheckupResult] End <<<<<<<");
        return result;
    }

    @Override
    public void saveAdd(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.saveAdd] Start >>>>>>>");
        if (bean != null){

            String id = "";
            id = getNextHeaderId();

            ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();
            headerEntity.setNoCheckup("CKP"+id);
            headerEntity.setIdPasien(bean.getIdPasien());
            headerEntity.setNama(bean.getNama());
            headerEntity.setJenisKelamin(bean.getJenisKelamin());
            headerEntity.setNoKtp(bean.getNoKtp());
            headerEntity.setTempatLahir(bean.getTempatLahir());
            headerEntity.setTglLahir(bean.getTglLahir());
            headerEntity.setDesaId(bean.getDesaId());
            headerEntity.setJalan(bean.getJalan());
            headerEntity.setSuku(bean.getSuku());
            headerEntity.setProfesi(bean.getProfesi());
            headerEntity.setNoTelp(bean.getNoTelp());
            headerEntity.setAgama(bean.getAgama());
            headerEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());
            headerEntity.setUrlKtp(bean.getUrlKtp());
            headerEntity.setBranchId(bean.getBranchId());
            headerEntity.setFlag("Y");
            headerEntity.setAction("C");
            headerEntity.setCreatedDate(bean.getCreatedDate());
            headerEntity.setLastUpdate(bean.getLastUpdate());
            headerEntity.setCreatedWho(bean.getCreatedWho());
            headerEntity.setLastUpdateWho(bean.getLastUpdateWho());
            headerEntity.setJenisKunjungan(bean.getJenisKunjungan());
            headerEntity.setNamaPenanggung(bean.getNamaPenanggung());
            headerEntity.setHubunganKeluarga(bean.getHubunganKeluarga());
            headerEntity.setRujuk(bean.getRujuk());
            headerEntity.setUrlDocRujuk(bean.getUrlDocRujuk());
            headerEntity.setBerat(bean.getBerat());
            headerEntity.setTinggi(bean.getTinggi());

            try {
                headerCheckupDao.addAndSave(headerEntity);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.saveAdd] Error When Saving data header checkup" + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data header checkup");
            }

            // saving detail checkup
            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();

                id = "";
                id = getNextDetailCheckupId();
                detailCheckupEntity.setIdDetailCheckup("DCM"+id);
                detailCheckupEntity.setNoCheckup(headerEntity.getNoCheckup());
                detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
                detailCheckupEntity.setStatusPeriksa(bean.getStatusPeriksa());
                detailCheckupEntity.setFlag("Y");
                detailCheckupEntity.setAction("C");
                detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
                detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
                detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailCheckupEntity.setTglAntrian(bean.getCreatedDate());

                try {
                    checkupDetailDao.addAndSave(detailCheckupEntity);
                } catch (HibernateException e){
                    logger.error("[CheckupBoImpl.saveAdd] Error When Saving data detail checkup" + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data detail checkup");
                }

                // saving dokter
                if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter()) &&
                        detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup()))
                {
                    DokterTeam dokterTeam = new DokterTeam();
                    dokterTeam.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    dokterTeam.setIdDokter(bean.getIdDokter());
                    saveTeamDokter(dokterTeam);
                }

                //saving diagnosa, form IGD
                if(bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa()) && detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())){
                    DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                    diagnosaRawat.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    diagnosaRawat.setIdDiagnosa(bean.getDiagnosa());
                    diagnosaRawat.setKeteranganDiagnosa(bean.getNamaDiagnosa());
                    diagnosaRawat.setJenisDiagnosa("0");
                    saveDiagnosa(diagnosaRawat);
                }
            }

            logger.info("[CheckupBoImpl.saveAdd] End <<<<<<<");
        }
    }

    @Override
    public void saveEdit(HeaderCheckup bean) throws GeneralBOException {

        logger.info("[CheckupBoImpl.saveEdit] Start >>>>>>>");

        if (bean != null){

            ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();

            try {
                headerEntity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
            } catch (HibernateException e){
                logger.error("[TeamDokterBoImpl.saveEdit] Error when save edit dokter team ",e);
                throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit dokter team "+e.getMessage());
            }

            if(headerEntity != null){
                headerEntity.setIdPasien(bean.getIdPasien());
                headerEntity.setNama(bean.getNama());
                headerEntity.setJenisKelamin(bean.getJenisKelamin());
                headerEntity.setNoKtp(bean.getNoKtp());
                headerEntity.setTempatLahir(bean.getTempatLahir());
                headerEntity.setTglLahir(bean.getTglLahir());
                headerEntity.setDesaId(bean.getDesaId());
                headerEntity.setJalan(bean.getJalan());
                headerEntity.setSuku(bean.getSuku());
                headerEntity.setProfesi(bean.getProfesi());
                headerEntity.setNoTelp(bean.getNoTelp());
                headerEntity.setAgama(bean.getAgama());
                headerEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());
                headerEntity.setUrlKtp(bean.getUrlKtp());
                headerEntity.setBranchId(bean.getBranchId());
                headerEntity.setFlag("Y");
                headerEntity.setAction("U");
                headerEntity.setLastUpdate(bean.getLastUpdate());
                headerEntity.setLastUpdateWho(bean.getLastUpdateWho());
                headerEntity.setJenisKunjungan(bean.getJenisKunjungan());
                headerEntity.setNamaPenanggung(bean.getNamaPenanggung());
                headerEntity.setHubunganKeluarga(bean.getHubunganKeluarga());
                headerEntity.setRujuk(bean.getRujuk());
            }

            try {
                headerCheckupDao.updateAndSave(headerEntity);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.saveEdit] Error When Saving data header checkup" + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveEdit] Error When Saving data header checkup");
            }

            // saving detail checkup
            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){

                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();

                try {
                    detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
                } catch (HibernateException e){
                    logger.error("[TeamDokterBoImpl.saveEdit] Error when save edit dokter team ",e);
                    throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit dokter team "+e.getMessage());
                }

                if(detailCheckupEntity != null){
                    detailCheckupEntity.setNoCheckup(headerEntity.getNoCheckup());
                    detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
                    detailCheckupEntity.setStatusPeriksa("0");
                    detailCheckupEntity.setFlag("Y");
                    detailCheckupEntity.setAction("U");
                    detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                    detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                }

                try {
                    checkupDetailDao.updateAndSave(detailCheckupEntity);
                } catch (HibernateException e){
                    logger.error("[CheckupBoImpl.saveEdit] Error When Saving data detail checkup" + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveEdit] Error When Saving data detail checkup");
                }

                // saving dokter
                if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter()) &&
                        detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup()))
                {
                    ItSimrsDokterTeamEntity entityDokter = new ItSimrsDokterTeamEntity();

                    try {
                        entityDokter = dokterTeamDao.getById("idTeamDokter", bean.getIdTeamDokter());
                    } catch (HibernateException e){
                        logger.error("[TeamDokterBoImpl.saveEdit] Error when save edit dokter team ",e);
                        throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit dokter team "+e.getMessage());
                    }

                    entityDokter.setIdDokter(bean.getIdDokter());
                    entityDokter.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    entityDokter.setFlag("Y");
                    entityDokter.setAction("U");
                    entityDokter.setLastUpdate(bean.getLastUpdate());
                    entityDokter.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        dokterTeamDao.updateAndSave(entityDokter);
                    } catch (HibernateException e){
                        logger.error("[CheckupBoImpl.saveTeamDokter] Error when save add dokter team ",e);
                        throw new GeneralBOException("[CheckupBoImpl.saveTeamDokter] Error when save add dokter team "+e.getMessage());
                    }

                }
            }

            logger.info("[CheckupBoImpl.saveEdit] End <<<<<<<");
        }
    }

    private void saveTeamDokter(DokterTeam bean){
        logger.info("[TeamDokterBoImpl.savaAdd] Start >>>>>>>>");

        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        String id = getNextTeamDokterId();

        entity.setIdTeamDokter("TDT"+id);
        entity.setIdDokter(bean.getIdDokter());
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity.setCreatedWho(CommonUtil.userLogin());
        entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        entity.setLastUpdateWho(CommonUtil.userLogin());

        try {
            dokterTeamDao.addAndSave(entity);
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.saveTeamDokter] Error when save add dokter team ",e);
            throw new GeneralBOException("[CheckupBoImpl.saveTeamDokter] Error when save add dokter team "+e.getMessage());
        }

        logger.info("[CheckupBoImpl.savaAdd] End <<<<<<<<");
    }

    public void saveDiagnosa(DiagnosaRawat bean) throws GeneralBOException {
        logger.info("[DiagnosaRawatBoImpl.saveAdd] Start >>>>>>>>>");

        if (bean != null && bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            ItSimrsDiagnosaRawatEntity entity = new ItSimrsDiagnosaRawatEntity();

            String id = getNextIdDiagnosa();
            entity.setIdDiagnosaRawat("DGR"+id);
            entity.setIdDiagnosa(bean.getIdDiagnosa());
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setKeteranganDiagnosa(bean.getKeteranganDiagnosa());
            entity.setJenisDiagnosa(bean.getJenisDiagnosa());
            entity.setTipe(bean.getTipe());
            entity.setFlag("Y");
            entity.setAction("U");
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            entity.setCreatedWho(CommonUtil.userLogin());
            entity.setLastUpdateWho(CommonUtil.userLogin());

            try {
                diagnosaRawatDao.addAndSave(entity);
            } catch (HibernateException e){
                logger.error("[DiagnosaRawatBoImpl.saveAdd] Error when saving diagnosa ", e);
                throw new GeneralBOException("Error when saving diagnosa " + e.getMessage());
            }
        }

        logger.info("[DiagnosaRawatBoImpl.saveAdd] End <<<<<<<<<");
    }

    private String getNextIdDiagnosa(){
        String id = "";
        try {
            id = diagnosaRawatDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[DiagnosaRawatBoImpl.getNextId] Error when get next diagnosa rawat id ", e);
        }
        return id;
    }

    private String getNextHeaderId(){
        String id = "";
        try {
            id = headerCheckupDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextHeaderId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getNextHeaderId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextDetailCheckupId(){
        String id = "";
        try {
            id = checkupDetailDao.getNextId();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextDetailCheckupId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getNextDetailCheckupId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextTeamDokterId(){
        String id = "";
        try {
            id = dokterTeamDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextTeamDokterId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getNextTeamDokterId] Error When Error get next seq id");
        }
        return id;
    }

    private List<ItSimrsDokterTeamEntity> getListEntityTeamDokter(DokterTeam bean) throws GeneralBOException{
        logger.info("[CheckupBoImpl.getListEntityTeamDokter] Start >>>>>>>>");
        List<ItSimrsDokterTeamEntity> entities = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        try {
            entities = dokterTeamDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getListEntityTeamDokter] Error when get data");
        }

        logger.info("[CheckupBoImpl.getListEntityTeamDokter] End <<<<<<<<");
        return entities;
    }

    @Override
    public HeaderCheckup completeBpjs(String nomorBpjs,String unitId) {
        logger.info("[CheckupBoImpl.completeBpjs] Start >>>>>>>");

        HeaderCheckup finalResult = new HeaderCheckup();
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = s.format(dt);

        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Peserta/nokartu/" + nomorBpjs + "/tglSEP/" + tanggal;

        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.completeBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                String result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    finalResult = null;
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[CheckupBoImpl.completeBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONObject obj = response.getJSONObject("peserta");
                    finalResult.setNoKtp(obj.getString("nik"));
                    finalResult.setNama(obj.getString("nama"));
                    finalResult.setJenisKelamin(obj.getString("sex"));
                    String[] tglLahir =obj.getString("tglLahir").split("-");
                    finalResult.setStTglLahir(tglLahir[2]+"-"+tglLahir[1]+"-"+tglLahir[0]);
                }
            } catch (Exception e) {
                logger.error("[CheckupBoImpl.completeBpjs] Error when get data");
            }
        }


        logger.info("[CheckupBoImpl.completeBpjs] End <<<<<<<");
        return finalResult;
    }

    @Override
    public void updatePenunjang(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.updatePenunjang] Start >>>>>>>>");

        List<ItSimrsHeaderChekupEntity> chekupEntities = null;
        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", bean.getNoCheckup());

        try {
            chekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.updatePenunjang] Error when get data checkup ",e);
            throw new GeneralBOException("[CheckupBoImpl.updatePenunjang]  Error when get data checkup "+e.getMessage());
        }

        if (chekupEntities.size() > 0){
            ItSimrsHeaderChekupEntity chekupEntity = chekupEntities.get(0);
            chekupEntity.setTinggi(bean.getTinggi());
            chekupEntity.setBerat(bean.getBerat());
            chekupEntity.setLastUpdate(bean.getLastUpdate());
            chekupEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                headerCheckupDao.updateAndSave(chekupEntity);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.updatePenunjang] Error when update data checkup ",e);
                throw new GeneralBOException("[CheckupBoImpl.updatePenunjang] Error when update data checkup "+e.getMessage());
            }
        }

        logger.info("[CheckupBoImpl.updatePenunjang] End <<<<<<<<");
    }

    @Override
    public List<ItSImrsCheckupAlergiEntity> getListAlergi(String noCheckup) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getListAlergi] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", noCheckup);

        List<ItSImrsCheckupAlergiEntity> alergiEntities = null;

        if (noCheckup != null && !"".equalsIgnoreCase(noCheckup)){
            try {
                alergiEntities = checkupAlergiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.getListAlergi] Error when get criteria ",e);
                throw new GeneralBOException("[CheckupBoImpl.getListAlergi] Error when get criteria "+e.getMessage());
            }
        }
        logger.info("[CheckupBoImpl.getListAlergi] End <<<<<<<<");
        return alergiEntities;
    }

    @Override
    public void saveAddAlergi(CheckupAlergi bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.saveAddAlergi] Start >>>>>>>>");

        ItSImrsCheckupAlergiEntity alergiEntity = new ItSImrsCheckupAlergiEntity();
        alergiEntity.setNoCheckup(bean.getNoCheckup());
        alergiEntity.setIdAlergi("ALG"+getNextIdAlergi());
        alergiEntity.setAlergi(bean.getAlergi());
        alergiEntity.setFlag("Y");
        alergiEntity.setAction("C");
        alergiEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        alergiEntity.setCreatedWho(CommonUtil.userLogin());
        alergiEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        alergiEntity.setLastUpdateWho(CommonUtil.userLogin());

        try {
            checkupAlergiDao.addAndSave(alergiEntity);
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.saveAddAlergi] Error when save Add ",e);
            throw new GeneralBOException("[CheckupBoImpl.saveAddAlergi] Error when save Add "+e.getMessage());
        }

        logger.info("[CheckupBoImpl.saveAddAlergi] End <<<<<<<<");
    }

    @Override
    public void saveEditAlergi(CheckupAlergi bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.saveEditAlergi] Start >>>>>>>>");

        if (bean.getIdAlergi() != null && !"".equalsIgnoreCase(bean.getIdAlergi())){

            Map hsCriteria = new HashMap();
            hsCriteria.put("id_alergi", bean.getIdAlergi());

            List<ItSImrsCheckupAlergiEntity> alergiEntities = null;
            try {
                alergiEntities = checkupAlergiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.saveEditAlergi] Error when get criteria ",e);
                throw new GeneralBOException("[CheckupBoImpl.saveEditAlergi] Error when get criteria "+e.getMessage());
            }

            if (alergiEntities != null && alergiEntities.size() > 0){
                ItSImrsCheckupAlergiEntity alergiEntity = alergiEntities.get(0);

                if (bean.getAlergi() != null && !"".equalsIgnoreCase(bean.getAlergi())){
                    alergiEntity.setAlergi(bean.getAlergi());
                }
                if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
                    alergiEntity.setFlag(bean.getFlag());
                    alergiEntity.setAction("U");
                }

                alergiEntity.setLastUpdate(bean.getLastUpdate());
                alergiEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    checkupAlergiDao.updateAndSave(alergiEntity);
                } catch (HibernateException e){
                    logger.error("[CheckupBoImpl.saveEditAlergi] Error when save update ",e);
                    throw new GeneralBOException("[CheckupBoImpl.saveEditAlergi] Error when save update "+e.getMessage());
                }
            }
        }

        logger.info("[CheckupBoImpl.saveEditAlergi] End <<<<<<<<");
    }

    @Override
    public AlertPasien getAlertPasien(String idPasien, String branchId) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getAlertPasien] Start >>>>>>>>");

        AlertPasien alertPasien = new AlertPasien();

        try {
            alertPasien = headerCheckupDao.getAlertPasien(idPasien, branchId);
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getAlertPasien] Error when get alert pasien ",e);
            throw new GeneralBOException("[CheckupBoImpl.getAlertPasien] Error when alert pasien "+e.getMessage());
        }

        List<String> listAlergi = new ArrayList<>();
        if (alertPasien != null){
            List<ItSImrsCheckupAlergiEntity> alergiEntities = getListAlergi(alertPasien.getNoCheckup());
            if (alergiEntities != null && alergiEntities.size() > 0){
                for (ItSImrsCheckupAlergiEntity alergiEntity : alergiEntities){
                    listAlergi.add(alergiEntity.getAlergi());
                }
            }
            alertPasien.setListOfAlergi(listAlergi);
        }

        logger.info("[CheckupBoImpl.getAlertPasien] End <<<<<<<<");
        return alertPasien;
    }

    @Override
    public List<AlertPasien> listOfRekamMedic(String idPasien) throws GeneralBOException {
        logger.info("[CheckupBoImpl.listOfRekamMedic] Start >>>>>>>>");

        List<ItSimrsHeaderChekupEntity> headerChekupEntities = null;
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pasien", idPasien);
        hsCriteria.put("tgl_keluar_not_null", "Y");

        try {
            headerChekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.listOfRekamMedic] Error when get checkup criteria ",e);
            throw new GeneralBOException("[CheckupBoImpl.listOfRekamMedic] Error when get checkup criteria "+e.getMessage());
        }

        List<AlertPasien> alertPasienList = new ArrayList<>();
        if (headerChekupEntities != null && headerChekupEntities.size() > 0){
            AlertPasien alertPasien;
            for (ItSimrsHeaderChekupEntity headerChekupEntity : headerChekupEntities)
            {
                alertPasien = new AlertPasien();
                alertPasien.setNoCheckup(headerChekupEntity.getNoCheckup());
                alertPasien.setNamaPasien(headerChekupEntity.getNama());

                AlertPasien alertPasienDiagnosa = new AlertPasien();
                try {
                    alertPasienDiagnosa = headerCheckupDao.gelLastDiagnosa(headerChekupEntity.getNoCheckup(), "");
                } catch (HibernateException e){
                    logger.error("[CheckupBoImpl.listOfRekamMedic] Error when get diagnosa ",e);
                    throw new GeneralBOException("[CheckupBoImpl.listOfRekamMedic] Error when get diagnosa "+e.getMessage());
                }

                if (alertPasienDiagnosa != null){
                    alertPasien.setDiagnosa(alertPasienDiagnosa.getDiagnosa());
                }

                Long time = headerChekupEntity.getTglKeluar().getTime();
                Date date = new Date(time);
                alertPasien.setStTgl(date.toString());
                alertPasienList.add(alertPasien);
            }
        }

        logger.info("[CheckupBoImpl.listOfRekamMedic] End <<<<<<<<");
        return alertPasienList;
    }

    private String getNextIdAlergi(){
        String id = "";
        try {
            id = checkupAlergiDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.saveEditAlergi] Error when get seq ",e);
            throw new GeneralBOException("[CheckupBoImpl.updatePenunjang] Error when get seq "+e.getMessage());
        }
        return id;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setProvinsiDao(ProvinsiDao provinsiDao) {
        this.provinsiDao = provinsiDao;
    }

    public void setDokterTeamDao(DokterTeamDao dokterTeamDao) {
        this.dokterTeamDao = dokterTeamDao;
    }

    public void setCheckupAlergiDao(CheckupAlergiDao checkupAlergiDao) {
        this.checkupAlergiDao = checkupAlergiDao;
    }

    public void setDiagnosaRawatDao(DiagnosaRawatDao diagnosaRawatDao) {
        this.diagnosaRawatDao = diagnosaRawatDao;
    }
}
