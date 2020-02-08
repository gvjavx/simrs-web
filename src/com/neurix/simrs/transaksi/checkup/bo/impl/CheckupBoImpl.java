package com.neurix.simrs.transaksi.checkup.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
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
import com.neurix.simrs.transaksi.pemeriksaanfisik.dao.PemeriksaanFisikDao;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.ItSimrsPemeriksaanFisikEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.PemeriksaanFisik;
import com.neurix.simrs.transaksi.psikososial.dao.PsikososialDao;
import com.neurix.simrs.transaksi.rencanarawat.dao.KategoriRencanaRawatDao;
import com.neurix.simrs.transaksi.rencanarawat.dao.ParameterRencanaRawatDao;
import com.neurix.simrs.transaksi.rencanarawat.dao.RencanaRawatDao;
import com.neurix.simrs.transaksi.resikojatuh.dao.KategoriResikoJatuhDao;
import com.neurix.simrs.transaksi.resikojatuh.dao.ParameterResikoJatuhDao;
import com.neurix.simrs.transaksi.resikojatuh.dao.ResikoJatuhDao;
import com.neurix.simrs.transaksi.resikojatuh.dao.SkorResikoJatuhDao;
import com.neurix.simrs.transaksi.resikojatuh.model.*;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
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
    private TindakanDao tindakanDao;
    private TindakanRawatDao tindakanRawatDao;
    private DiagnosaRawatDao diagnosaRawatDao;
    private PemeriksaanFisikDao pemeriksaanFisikDao;
    private KategoriResikoJatuhDao kategoriResikoJatuhDao;
    private ParameterResikoJatuhDao parameterResikoJatuhDao;
    private SkorResikoJatuhDao skorResikoJatuhDao;
    private ResikoJatuhDao resikoJatuhDao;
    private PsikososialDao psikososialDao;
    private KategoriRencanaRawatDao kategoriRencanaRawatDao;
    private ParameterRencanaRawatDao parameterRencanaRawatDao;
    private RencanaRawatDao rencanaRawatDao;

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
            headerCheckup.setNoSep(headerList.getNoSep());
            headerCheckup.setDiagnosa(headerList.getKodeDiagnosa());
            headerCheckup.setJenisTransaksi(headerList.getJenisTransaksi());
            headerCheckup.setTarifBpjs(headerList.getTarifBpjs());

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
            //id = getNextHeaderId();

            ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();
            headerEntity.setNoCheckup(bean.getNoCheckup());
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
            headerEntity.setNoSep(bean.getNoSep());
            headerEntity.setJenisTransaksi(bean.getIdJenisPeriksaPasien());
            headerEntity.setKetRujukan(bean.getKetPerujuk());
            headerEntity.setKetKeyakinan(bean.getKetKeyakinan());
            headerEntity.setBantuanBahasa(bean.getBantuanBahasa());
            headerEntity.setBahasa(bean.getBahasa());
            headerEntity.setAlatBantu(bean.getAlatBantu());
            headerEntity.setGangguanLain(bean.getGangguanLain());
            headerEntity.setTarifBpjs(bean.getTarifBpjs());
            headerEntity.setNoRujukan(bean.getNoRujukan());
            headerEntity.setNoPpkRujukan(bean.getNoPpkRujukan());
            if(bean.getTglRujukan() != null && !"".equalsIgnoreCase(bean.getTglRujukan())){
                headerEntity.setTglRujukan(Date.valueOf(bean.getTglRujukan()));
            }

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

                if (bean.getTindakanList() != null && bean.getTindakanList().size() > 0){
                    for (Tindakan tindakan : bean.getTindakanList()){
                        List<ImSimrsTindakanEntity> tindakanEntities = getListEntityTindakan(tindakan);
                        if (tindakanEntities.size() > 0){

                            ImSimrsTindakanEntity tindakanEntity = tindakanEntities.get(0);
                            ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                            tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                            tindakanRawatEntity.setIdTindakanRawat("TDR"+getNextTindakanRawatId());
                            tindakanRawatEntity.setIdTindakan(tindakanEntity.getIdTindakan());
                            tindakanRawatEntity.setNamaTindakan(tindakanEntity.getTindakan());
                            tindakanRawatEntity.setIdDokter(bean.getIdDokter());
                            tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                            tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                            tindakanRawatEntity.setLastUpdate(bean.getCreatedDate());
                            tindakanRawatEntity.setLastUpdateWho(bean.getCreatedWho());
                            tindakanRawatEntity.setFlag("Y");
                            tindakanRawatEntity.setAction("U");

                            if ("bpjs".equalsIgnoreCase(bean.getJenisTransaksi())){
                                tindakanRawatEntity.setTarif(tindakanEntity.getTarifBpjs());
                                tindakanRawatEntity.setApproveBpjsFlag("Y");
                                tindakanRawatEntity.setKategoriTindakanBpjs("konsultasi");
                            } else {
                                tindakanRawatEntity.setTarif(tindakanEntity.getTarif());
                            }

                            tindakanRawatEntity.setQty(new BigInteger(String.valueOf(1)));
                            tindakanRawatEntity.setTarifTotal(tindakanRawatEntity.getTarif().multiply(tindakanRawatEntity.getQty()));

                            try {
                                tindakanRawatDao.addAndSave(tindakanRawatEntity);
                            } catch (HibernateException e){
                                logger.error("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" +e.getMessage());
                                throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat"+ e.getMessage());
                            }

                        }
                    }
                }
            }

            logger.info("[CheckupBoImpl.saveAdd] End <<<<<<<");
        }
    }

    private List<ImSimrsTindakanEntity> getListEntityTindakan(Tindakan bean) throws GeneralBOException{
        logger.info("[CheckupBoImpl.getListEntityTindakan] Start >>>>>>>");

        List<ImSimrsTindakanEntity> tindakanEntities = new ArrayList<>();
        if (bean != null){
            Map hsCriteria = new HashMap();
            if (bean.getIdTindakan() != null){
                hsCriteria.put("id_tindakan", bean.getIdTindakan());
            }
            if (bean.getIdKategoriTindakan() != null){
                hsCriteria.put("id_kategori_tindakan", bean.getIdKategoriTindakan());
            }
            if (bean.getFlag() != null){
                hsCriteria.put("flag", bean.getFlag());
            }
            try {
                tindakanEntities = tindakanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.getListEntityTindakan] ERROR " + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.getListEntityTindakan] ERROR "+ e.getMessage());
            }

        }
        logger.info("[CheckupBoImpl.getListEntityTindakan] End <<<<<<<");
        return tindakanEntities;

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
                headerEntity.setNoSep(bean.getNoSep());
                headerEntity.setKodeDiagnosa(bean.getDiagnosa());
                headerEntity.setJenisTransaksi(bean.getJenisTransaksi());
                if (bean.getTarifBpjs() != null && bean.getTarifBpjs().compareTo(new BigDecimal(String.valueOf(0))) == 1){
                    headerEntity.setTarifBpjs(bean.getTarifBpjs());
                }
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

    @Override
    public String getNextHeaderId(){
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

    @Override
    public ItSimrsPemeriksaanFisikEntity getEntityPemeriksaanFisikByNoCheckup(String noCheckup) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getEntityPemeriksaanFisikByNoCheckup] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", noCheckup);

        List<ItSimrsPemeriksaanFisikEntity> pemeriksaanFisikEntities = new ArrayList<>();
        try {
            pemeriksaanFisikEntities = pemeriksaanFisikDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getEntityPemeriksaanFisikByNoCheckup] ERROR ",e);
            throw new GeneralBOException("[CheckupBoImpl.getEntityPemeriksaanFisikByNoCheckup] ERROR "+e.getMessage());
        }

        ItSimrsPemeriksaanFisikEntity pemeriksaanFisikEntity = new ItSimrsPemeriksaanFisikEntity();
        if (pemeriksaanFisikEntities.size() > 0){
            pemeriksaanFisikEntity = pemeriksaanFisikEntities.get(0);
        }
        logger.info("[CheckupBoImpl.getEntityPemeriksaanFisikByNoCheckup] End <<<<<<<<");
        return pemeriksaanFisikEntity;
    }

    @Override
    public void savePemeriksaanFisik(PemeriksaanFisik bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.savePemeriksaanFisik] Start >>>>>>>>");

        ItSimrsPemeriksaanFisikEntity entity = getEntityPemeriksaanFisikByNoCheckup(bean.getNoCheckup());
        if (entity.getId() != null && !"".equalsIgnoreCase(entity.getId())){

            entity.setKepala(bean.getKepala());
            entity.setMata(bean.getMata());
            entity.setLeher(bean.getLeher());
            entity.setThorak(bean.getThorak());
            entity.setThorakChor(bean.getThorakChor());
            entity.setThorakPulmo(bean.getThorakPulmo());
            entity.setAbdoman(bean.getAbdoman());
            entity.setExtrimitas(bean.getExtrimitas());
            entity.setTinggiBadan(bean.getTinggiBadan());
            entity.setBeratBadan(bean.getBeratBadan());
            entity.setNadi(bean.getNadi());
            entity.setRespirationRate(bean.getRespirationRate());
            entity.setTekananDarah(bean.getTekananDarah());
            entity.setSuhu(bean.getSuhu());
            entity.setTriase(bean.getTriase());

            entity.setFlag(bean.getFlag());
            entity.setAction("U");
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                pemeriksaanFisikDao.updateAndSave(entity);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.savePemeriksaanFisik] Error when update ",e);
                throw new GeneralBOException("[CheckupBoImpl.updatePenunjang] Error when update "+e.getMessage());
            }

        } else {

            DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
            String formattedDate = df.format(Calendar.getInstance().getTime());

            entity = new ItSimrsPemeriksaanFisikEntity();
            entity.setId(formattedDate+"F"+getNextIdPemeriksaanFisik());
            entity.setNoCheckup(bean.getNoCheckup());
            entity.setKepala(bean.getKepala());
            entity.setMata(bean.getMata());
            entity.setLeher(bean.getLeher());
            entity.setThorak(bean.getThorak());
            entity.setThorakChor(bean.getThorakChor());
            entity.setThorakPulmo(bean.getThorakPulmo());
            entity.setAbdoman(bean.getAbdoman());
            entity.setExtrimitas(bean.getExtrimitas());
            entity.setTinggiBadan(bean.getTinggiBadan());
            entity.setBeratBadan(bean.getBeratBadan());
            entity.setNadi(bean.getNadi());
            entity.setRespirationRate(bean.getRespirationRate());
            entity.setTekananDarah(bean.getTekananDarah());
            entity.setSuhu(bean.getSuhu());
            entity.setTriase(bean.getTriase());

            entity.setFlag(bean.getFlag());
            entity.setAction(bean.getAction());
            entity.setCreatedDate(bean.getCreatedDate());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setCreatedWho(bean.getCreatedWho());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                pemeriksaanFisikDao.addAndSave(entity);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.savePemeriksaanFisik] Error when insert ",e);
                throw new GeneralBOException("[CheckupBoImpl.savePemeriksaanFisik] Error when insert "+e.getMessage());
            }
        }

        logger.info("[CheckupBoImpl.savePemeriksaanFisik] End <<<<<<<<");
    }

    @Override
    public ResikoJatuhResponse getResikojatuh(ResikoJatuh bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.savePemeriksaanFisik] Start >>>>>>>>");

        ResikoJatuhResponse response = new ResikoJatuhResponse();

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", bean.getNoCheckup());
        List<ItSImrsResikoJatuhEntity> resikoJatuhEntities = resikoJatuhDao.getByCriteria(hsCriteria);
        if (resikoJatuhEntities.size() > 0){
            response.setStatus("success");
            response.setResikoJatuhEntityList(resikoJatuhEntities);
        } else {


            hsCriteria = new HashMap();
            hsCriteria.put("umur", bean.getUmur());
            List<ImSimrsKategoriResikoJatuhEntity> kategoriResikoJatuhEntities = kategoriResikoJatuhDao.getByCriteria(hsCriteria);
            if (kategoriResikoJatuhEntities.size() > 0){

                hsCriteria = new HashMap();
                hsCriteria.put("id_kategori", kategoriResikoJatuhEntities.get(0).getIdKategori());
                List<ImSimrsParameterResikoJatuhEntity> parameterResikoJatuhEntities = parameterResikoJatuhDao.getByCriteria(hsCriteria);
                if(parameterResikoJatuhEntities.size() > 0){

                    resikoJatuhEntities = new ArrayList<>();
                    ItSImrsResikoJatuhEntity resikoJatuhEntity;
                    for (ImSimrsParameterResikoJatuhEntity parameter : parameterResikoJatuhEntities){
                        resikoJatuhEntity = new ItSImrsResikoJatuhEntity();
                        resikoJatuhEntity.setIdParameter(parameter.getIdParameter());
                        resikoJatuhEntity.setNamaParameter(parameter.getNamaParameter());
                        resikoJatuhEntity.setIdKategori(parameter.getIdKategori());
                        resikoJatuhEntities.add(resikoJatuhEntity);
                    }
                    response.setStatus("success");
                    response.setResikoJatuhEntityList(resikoJatuhEntities);
                }
            }
        }

        logger.info("[CheckupBoImpl.savePemeriksaanFisik] End <<<<<<<<");
        return response;
    }

    @Override
    public List<ImSimrsSkorResikoJatuhEntity> getListSkorResikoByIdParameter(String id) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getListSkorResikoById] Start >>>>>>>>");

        List<ImSimrsSkorResikoJatuhEntity> skorResikoJatuhEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_parameter", id);

        try {
            skorResikoJatuhEntities = skorResikoJatuhDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getListSkorResikoById] ERROR "+e.getMessage());
        }

        logger.info("[CheckupBoImpl.getListSkorResikoById] End <<<<<<<<");
        return skorResikoJatuhEntities;
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

    private String getNextTindakanRawatId(){
        String id = "";
        try {
            id = tindakanRawatDao.getNextTindakanRawatId();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextTindakanRawatId] Error when get seq ",e);
            throw new GeneralBOException("[CheckupBoImpl.getNextTindakanRawatId] Error when get seq "+e.getMessage());
        }
        return id;
    }
    private String getNextIdPemeriksaanFisik(){
        String id = "";
        try {
            id = pemeriksaanFisikDao.getNextId();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextIdPemeriksaanFisik] Error when get seq ",e);
            throw new GeneralBOException("[CheckupBoImpl.getNextIdPemeriksaanFisik] Error when get seq "+e.getMessage());
        }
        return id;
    }

    private String getNextIdResikoJatuh(){
        String id = "";
        try {
            id = resikoJatuhDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextIdResikoJatuh] Error when get seq ",e);
            throw new GeneralBOException("[CheckupBoImpl.getNextIdResikoJatuh] Error when get seq "+e.getMessage());
        }
        return id;
    }

    private String getNextPsikososialId(){
        String id = "";
        try {
            id = psikososialDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextPsikososialId] Error when get seq ",e);
            throw new GeneralBOException("[CheckupBoImpl.getNextPsikososialId] Error when get seq "+e.getMessage());
        }
        return id;
    }

    private String getNextRencanaRawatId(){
        String id = "";
        try {
            id = rencanaRawatDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextRencanaRawatId] Error when get seq ",e);
            throw new GeneralBOException("[CheckupBoImpl.getNextRencanaRawatId] Error when get seq "+e.getMessage());
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

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public CheckupBoImpl() throws GeneralSecurityException, IOException {
    }

    public void setPemeriksaanFisikDao(PemeriksaanFisikDao pemeriksaanFisikDao) {
        this.pemeriksaanFisikDao = pemeriksaanFisikDao;
    }

    public void setKategoriResikoJatuhDao(KategoriResikoJatuhDao kategoriResikoJatuhDao) {
        this.kategoriResikoJatuhDao = kategoriResikoJatuhDao;
    }

    public void setParameterResikoJatuhDao(ParameterResikoJatuhDao parameterResikoJatuhDao) {
        this.parameterResikoJatuhDao = parameterResikoJatuhDao;
    }

    public void setSkorResikoJatuhDao(SkorResikoJatuhDao skorResikoJatuhDao) {
        this.skorResikoJatuhDao = skorResikoJatuhDao;
    }

    public void setResikoJatuhDao(ResikoJatuhDao resikoJatuhDao) {
        this.resikoJatuhDao = resikoJatuhDao;
    }

    public void setPsikososialDao(PsikososialDao psikososialDao) {
        this.psikososialDao = psikososialDao;
    }

    public void setKategoriRencanaRawatDao(KategoriRencanaRawatDao kategoriRencanaRawatDao) {
        this.kategoriRencanaRawatDao = kategoriRencanaRawatDao;
    }

    public void setParameterRencanaRawatDao(ParameterRencanaRawatDao parameterRencanaRawatDao) {
        this.parameterRencanaRawatDao = parameterRencanaRawatDao;
    }

    public void setRencanaRawatDao(RencanaRawatDao rencanaRawatDao) {
        this.rencanaRawatDao = rencanaRawatDao;
    }
}
