package com.neurix.simrs.transaksi.checkup.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;
import com.neurix.hris.master.provinsi.model.ImDesaEntity;
import com.neurix.hris.master.provinsi.model.ImKecamatanEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;
import com.neurix.hris.master.provinsi.model.ImProvinsiEntity;
import com.neurix.hris.master.statusRekruitment.bo.impl.StatusRekruitmentBoImpl;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */

public class CheckupBoImpl implements CheckupBo {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);

    private HeaderCheckupDao headerCheckupDao;
    private CheckupDetailDao checkupDetailDao;
    private ProvinsiDao provinsiDao;
    private DokterTeamDao dokterTeamDao;

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
}
