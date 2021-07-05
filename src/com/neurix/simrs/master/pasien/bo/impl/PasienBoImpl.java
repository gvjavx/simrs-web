package com.neurix.simrs.master.pasien.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.cuti.model.ImCutiEntity;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;

import com.neurix.simrs.master.license.model.Email;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.dao.*;
import com.neurix.simrs.master.pasien.model.*;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.paketperiksa.model.ItSimrsPaketPasienEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPasien;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class PasienBoImpl implements PasienBo {

    protected static transient Logger logger = org.apache.log4j.Logger.getLogger(PasienBoImpl.class);

    private PasienDao pasienDao;
    private FingerDataDao fingerDataDao;
    private ProvinsiDao provinsiDao;
    private HeaderCheckupDao headerCheckupDao;
    private RekamMedicLamaDao rekamMedicLamaDao;
    private UploadRekamMedicLamaDao uploadRekamMedicLamaDao;
    private PasienSementaraDao pasienSementaraDao;

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public FingerDataDao getFingerDataDao() {
        return fingerDataDao;
    }

    public void setFingerDataDao(FingerDataDao fingerDataDao) {
        this.fingerDataDao = fingerDataDao;
    }

    public void setProvinsiDao(ProvinsiDao provinsiDao) {
        this.provinsiDao = provinsiDao;
    }

    private Date date;

    @Override
    public List<Pasien> getByCriteria(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.getByCriteria] Start >>>>>>>");

        List<Pasien> pasienList = new ArrayList<>();

        try {
            pasienList = pasienDao.getSearchForTransaksi(bean);
        } catch (HibernateException e){
            logger.error("[PasienBoImpl.getByCriteria] Error when search pasien by criteria " + e.getMessage());
            throw new GeneralBOException("[PasienBoImpl.getByCriteria] Error when search pasien by criteria " + e);
        }


//        List<Pasien> pasiens = new ArrayList<>();
//        if (bean != null) {
//
//            List<ImSimrsPasienEntity> imSimrsPasienEntities = getEntityByCriteria(bean);
//
//            if (!imSimrsPasienEntities.isEmpty()) {
//                pasiens = setTemplatePasien(imSimrsPasienEntities);
//            }
//
//        }

        logger.info("[PasienBoImpl.getByCriteria] End <<<<<<<");
        return pasienList;
    }

    public List<ImSimrsPasienEntity> getEntityByCriteria(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.getEntityByCriteria] Start >>>>>>>");
        List<ImSimrsPasienEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
            hsCriteria.put("id_pasien", bean.getIdPasien());
        }
        if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
            hsCriteria.put("nama", bean.getNama());
        }
        if (bean.getDesaId() != null && !"".equalsIgnoreCase(bean.getDesaId())) {
            hsCriteria.put("desa_id", bean.getDesaId());
        }
        if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())) {
            hsCriteria.put("no_ktp", bean.getNoKtp());
        }
        if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())) {
            hsCriteria.put("no_bpjs", bean.getIdPasien());
        }

        List<ImSimrsPasienEntity> imSimrsPasienEntities = null;
        try {
            imSimrsPasienEntities = pasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria " + e.getMessage());
        }

        if (!imSimrsPasienEntities.isEmpty()) {
            results = imSimrsPasienEntities;
        }

        logger.info("[PasienBoImpl.getEntityByCriteria] End <<<<<<<");
        return results;
    }

    @Override
    public List<Pasien> getSearchForMaster(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.getSearchForMaster] Start >>>>>>>");

        List<Pasien> pasienList = new ArrayList<>();

        try {
            pasienList = pasienDao.getSearchPasienForMaster(bean);
        } catch (HibernateException e){
            logger.error("[PasienBoImpl.getSearchForMaster] Error when search pasien by criteria " + e.getMessage());
            throw new GeneralBOException("[PasienBoImpl.getSearchForMaster] Error when search pasien by criteria " + e);
        }


        logger.info("[PasienBoImpl.getSearchForMaster] End <<<<<<<");
        return pasienList;
    }

    public List<Pasien> setTemplatePasien(List<ImSimrsPasienEntity> listEntity) {
        logger.info("[PasienBoImpl.setTemplatePasien] Start >>>>>>>");
        List<Pasien> list = new ArrayList<>();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Pasien pasien;
        for (ImSimrsPasienEntity data : listEntity) {
            pasien = new Pasien();
            pasien.setIdPasien(data.getIdPasien());
            pasien.setNama(data.getNama());
            pasien.setJenisKelamin(data.getJenisKelamin());
            pasien.setNoKtp(data.getNoKtp());
            pasien.setNoBpjs(data.getNoBpjs());
            pasien.setTempatLahir(data.getTempatLahir());

            if (data.getTglLahir() != null) {
                String strDate = formatter.format(data.getTglLahir());
                pasien.setTglLahir(strDate);
                pasien.setTanggalLahir(data.getTglLahir());
            }

            if(data.getDesaId() != null ) {
                pasien.setDesaId(data.getDesaId().toString());
            }

            pasien.setJalan(data.getJalan());
            pasien.setSuku(data.getSuku());
            pasien.setAgama(data.getAgama());
            pasien.setProfesi(data.getProfesi());
            pasien.setNoTelp(data.getNoTelp());
            pasien.setImgKtp(data.getUrlKtp());
            pasien.setUrlKtp(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_KTP_PASIEN + data.getUrlKtp());
            pasien.setFlag(data.getFlag());
            pasien.setAction(data.getAction());
            pasien.setCreatedDate(data.getCreatedDate());
            pasien.setCreatedWho(data.getCreatedWho());
            pasien.setLastUpdate(data.getLastUpdate());
            pasien.setLastUpdateWho(data.getLastUpdateWho());
            pasien.setEmail(data.getEmail());
            pasien.setPassword(data.getPassword());
            pasien.setPendidikan(data.getPendidikan());
            pasien.setStatusPerkawinan(data.getStatusPerkawinan());
            pasien.setFlagLogin(data.getFlagLogin());

            if (pasien.getDesaId() != null) {
                List<Object[]> objs = provinsiDao.getListAlamatByDesaId(pasien.getDesaId().toString());
                if (!objs.isEmpty()) {
                    for (Object[] obj : objs) {
                        pasien.setDesa(obj[0].toString());
                        pasien.setKecamatan(obj[1].toString());
                        pasien.setKota(obj[2].toString());
                        pasien.setProvinsi(obj[3].toString());
                        pasien.setKecamatanId(obj[4].toString());
                        pasien.setKotaId(obj[5].toString());
                        pasien.setProvinsiId(obj[6].toString());
                    }
                }
            }

            if (pasien.getIdPasien() != null) {
                Map hsCriteria = new HashMap();
                hsCriteria.put("id_pasien", pasien.getIdPasien());
                List<ItSimrsHeaderChekupEntity> cekKunjungan = headerCheckupDao.getByCriteria(hsCriteria);
                if (cekKunjungan.size() > 0) {
                    pasien.setIsPasienLama(true);
                }

                //cek finger data
                pasien.setDisabledFingerData(cekFingerData(pasien.getIdPasien()));

                HeaderDetailCheckup detailCheckup = pasienDao.getLastCheckup(data.getIdPasien());
                if (detailCheckup.getIdDetailCheckup() != null) {
                    pasien.setIdPelayanan(detailCheckup.getIdPelayanan());
                    pasien.setNoCheckuoUlang(detailCheckup.getNoCheckupUlang());
                    pasien.setIdLastDetailCheckup(detailCheckup.getIdDetailCheckup());
                    pasien.setIsOrderLab(detailCheckup.getIsOrderLab());
                    if (detailCheckup.getTglCekup() != null) {
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(detailCheckup.getTglCekup());
                        pasien.setTglCheckup(formatDate);
                        pasien.setIsCheckupUlang("Y");
                    }
                }

            }

            list.add(pasien);
        }

        logger.info("[PasienBoImpl.setTemplatePasien] End <<<<<<<");
        return list;
    }

    @Override
    public void saveAdd(Pasien pasien) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveAdd] Start >>>>>>>");

        if (pasien != null) {
            ImSimrsPasienEntity pasienEntity = new ImSimrsPasienEntity();
            String id = getIdPasien();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

            pasienEntity.setIdPasien(CommonUtil.userBranchLogin() + dateFormater("yy") + id);
            pasienEntity.setNama(pasien.getNama());
            pasienEntity.setJenisKelamin(pasien.getJenisKelamin());
            pasienEntity.setNoKtp(pasien.getNoKtp().replace("-","").replace("_",""));
            pasienEntity.setNoBpjs(pasien.getNoBpjs());
            pasienEntity.setTempatLahir(pasien.getTempatLahir());

            pasienEntity.setNoTelp(pasien.getNoTelp());
            pasienEntity.setTglLahir(java.sql.Date.valueOf(pasien.getTglLahir()));
            BigInteger bigInteger = new BigInteger(pasien.getDesaId());
            pasienEntity.setDesaId(bigInteger);
            pasienEntity.setJalan(pasien.getJalan());

            pasienEntity.setSuku(pasien.getSuku());
            pasienEntity.setAgama(pasien.getAgama());
            pasienEntity.setProfesi(pasien.getProfesi());
            pasienEntity.setNoTelp(pasien.getNoTelp().replace("-","").replace("_",""));
            pasienEntity.setUrlKtp(pasien.getUrlKtp());
            pasienEntity.setPendidikan(pasien.getPendidikan());
            pasienEntity.setStatusPerkawinan(pasien.getStatusPerkawinan());

            pasienEntity.setFlag("Y");
            pasienEntity.setAction("C");
            pasienEntity.setCreatedDate(pasien.getCreatedDate());
            pasienEntity.setLastUpdate(pasien.getLastUpdate());
            pasienEntity.setCreatedWho(pasien.getCreatedWho());
            pasienEntity.setLastUpdateWho(pasien.getLastUpdateWho());

            try {
                pasienDao.addAndSave(pasienEntity);
            } catch (HibernateException e) {
                logger.error("[PasienBoImpl.saveAdd] Error when saving data pasien", e);
                throw new GeneralBOException(" Error when saving data pasien " + e.getMessage());
            }
        } else {
            logger.error("[PasienBoImpl.saveAdd] Error when saving data pasien data is null");
            throw new GeneralBOException(" Error when saving data pasien data is null");
        }

        logger.info("[PasienBoImpl.saveAdd] End <<<<<<<");
    }

    @Override
    public CrudResponse saveEdit(Pasien pasien) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        logger.info("[PasienBoImpl.saveEdit] Start >>>>>>>");

        if (pasien != null && pasien.getIdPasien() != null && !"".equalsIgnoreCase(pasien.getIdPasien())) {
            ImSimrsPasienEntity pasienEntity = pasienDao.getById("idPasien", pasien.getIdPasien());
            if (pasienEntity != null) {

                pasienEntity.setNama(pasien.getNama());
                pasienEntity.setJenisKelamin(pasien.getJenisKelamin());

                if (pasien.getNoKtp() != null && !"".equalsIgnoreCase(pasien.getNoKtp())) {
                    pasienEntity.setNoKtp(pasien.getNoKtp().replace("-","").replace("_",""));
                }

                if (pasien.getNoBpjs() != null && !"".equalsIgnoreCase(pasien.getNoBpjs())) {
                    pasienEntity.setNoBpjs(pasien.getNoBpjs());
                }

                pasienEntity.setTempatLahir(pasien.getTempatLahir());

                if(pasien.getTglLahir()!= null && !"".equalsIgnoreCase(pasien.getTglLahir())) {
                    pasienEntity.setTglLahir(java.sql.Date.valueOf(pasien.getTglLahir()));
                }

                if (pasien.getDesaId() != null && !"".equalsIgnoreCase(pasien.getDesaId())) {
                    pasienEntity.setDesaId(new BigInteger(pasien.getDesaId()));
                }

                pasienEntity.setJalan(pasien.getJalan());
                pasienEntity.setSuku(pasien.getSuku());
                pasienEntity.setAgama(pasien.getAgama());
                pasienEntity.setProfesi(pasien.getProfesi());

                if (pasien.getNoTelp() !=null && !"".equalsIgnoreCase(pasien.getNoTelp())) {
                    pasienEntity.setNoTelp(pasien.getNoTelp().replace("-", "").replace("_",""));
                }

                pasienEntity.setPendidikan(pasien.getPendidikan());
                pasienEntity.setStatusPerkawinan(pasien.getStatusPerkawinan());

                if (pasien.getUrlKtp() != null && !"".equalsIgnoreCase(pasien.getUrlKtp())) {
                    pasienEntity.setUrlKtp(pasien.getUrlKtp());
                }
                if ("N".equalsIgnoreCase(pasien.getFlag())) {
                    pasienEntity.setAction("D");
                } else {
                    pasienEntity.setAction("U");
                }
                pasienEntity.setFlag(pasien.getFlag());
                pasienEntity.setLastUpdate(pasien.getLastUpdate());
                pasienEntity.setLastUpdateWho(pasien.getLastUpdateWho());
                pasienEntity.setFlagLogin(pasien.getFlagLogin());
                pasienEntity.setStatusPerkawinan(pasien.getStatusPerkawinan());
                pasienEntity.setPassword(pasien.getPassword());

                try {
                    pasienDao.updateAndSave(pasienEntity);
                    response.setStatus("success");
                    response.setMsg("berhasil");
                } catch (HibernateException e) {
                    response.setStatus("eror");
                    response.setMsg("[PasienBoImpl.saveAdd] Error when Updating data pasien" + e.getMessage());
                    logger.error("[PasienBoImpl.saveAdd] Error when Updating data pasien", e);
                }
            } else {
                response.setStatus("eror");
                response.setMsg("Error when get entity pasien is null");
                logger.error("[PasienBoImpl.saveAdd] Error when get entity pasien is null");
            }

        } else {
            response.setStatus("eror");
            response.setMsg("[PasienBoImpl.saveAdd] Error when saving data pasien data is null");
            logger.error("[PasienBoImpl.saveAdd] Error when saving data pasien data is null");
        }

        logger.info("[PasienBoImpl.saveEdit] End <<<<<<<");
        return response;
    }

    @Override
    public Boolean cekNikPasien(String nik) throws GeneralBOException {
        Boolean res = false;
        if (nik != null) {
            Pasien pasien = new Pasien();
            pasien.setNoKtp(nik);
            List<ImSimrsPasienEntity> pasienList = getEntityByCriteria(pasien);
            if (pasienList.size() > 0) {
                res = true;
            }
        }
        return res;
    }

    @Override
    public void saveDelete(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveDelete] start process");

        if (bean != null) {

            String idPasien = bean.getIdPasien();

            ImSimrsPasienEntity entity = null;
            try {
                // Get data from database by ID
                entity = pasienDao.getById("idPasien", idPasien);
            } catch (HibernateException e) {
                logger.error("[CutiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (entity != null) {
                // Modify from bean to entity serializable
                entity.setNama(bean.getNama());
                entity.setJenisKelamin(bean.getJenisKelamin());
                entity.setNoKtp(bean.getNoKtp());
                entity.setNoBpjs(bean.getNoBpjs());
                entity.setTempatLahir(bean.getTempatLahir());

                entity.setTglLahir(java.sql.Date.valueOf(bean.getTglLahir()));
                BigInteger bigInteger = new BigInteger(bean.getDesaId());
                entity.setDesaId(bigInteger);
                entity.setJalan(bean.getJalan());
                entity.setSuku(bean.getSuku());
                entity.setAgama(bean.getAgama());
                entity.setProfesi(bean.getProfesi());
                entity.setNoTelp(bean.getNoTelp());
                entity.setUrlKtp(bean.getNoKtp());
                entity.setFlag(bean.getFlag());
                entity.setAction("U");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    // Delete (Edit) into database
                    pasienDao.updateAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[CutiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Cuti, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[CutiBoImpl.saveDelete] Error, not found data Cuti with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Cuti with request id, please check again your data ...");
            }
        }

        logger.info("[PasienBoImpl.saveEdit] End <<<<<<<");
    }

    @Override
    public List<Pasien> getListComboPasien(String query) throws GeneralBOException {
        logger.info("[PasienBoImpl.getListComboPasien] Start >>>>>>>");

        String tmp = "%" + query + "%";
        List<Pasien> list = new ArrayList<>();
        List<ImSimrsPasienEntity> pasienEntityList = new ArrayList<>();
        try {
            pasienEntityList = pasienDao.getListPasienByTmp(tmp);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria " + e.getMessage());
        }

        if (!pasienEntityList.isEmpty()) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Pasien pasien;
            for (ImSimrsPasienEntity data : pasienEntityList) {
                pasien = new Pasien();
                pasien.setIdPasien(data.getIdPasien());
                pasien.setNama(data.getNama());
                pasien.setJenisKelamin(data.getJenisKelamin());
                pasien.setNoKtp(data.getNoKtp());
                pasien.setNoBpjs(data.getNoBpjs());
                pasien.setTempatLahir(data.getTempatLahir());

                String strDate = formatter.format(data.getTglLahir());
                pasien.setTglLahir(strDate);

                pasien.setDesaId(data.getDesaId().toString());
                pasien.setJalan(data.getJalan());
                pasien.setSuku(data.getSuku());
                pasien.setAgama(data.getAgama());
                pasien.setProfesi(data.getProfesi());
                pasien.setNoTelp(data.getNoTelp());
                pasien.setImgKtp(data.getUrlKtp());
                pasien.setUrlKtp(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_KTP_PASIEN + data.getUrlKtp());
                pasien.setFlag(data.getFlag());
                pasien.setAction(data.getAction());
                pasien.setCreatedDate(data.getCreatedDate());
                pasien.setCreatedWho(data.getCreatedWho());
                pasien.setLastUpdate(data.getLastUpdate());
                pasien.setLastUpdateWho(data.getLastUpdateWho());
                pasien.setEmail(data.getEmail());
                pasien.setPassword(data.getPassword());
                pasien.setStatusPerkawinan(data.getStatusPerkawinan());
                pasien.setPendidikan(data.getPendidikan());

                if (pasien.getDesaId() != null) {
                    List<Object[]> objs = provinsiDao.getListAlamatByDesaId(pasien.getDesaId().toString());
                    if (!objs.isEmpty()) {
                        for (Object[] obj : objs) {
                            pasien.setDesa(obj[0].toString());
                            pasien.setKecamatan(obj[1].toString());
                            pasien.setKota(obj[2].toString());
                            pasien.setProvinsi(obj[3].toString());
                            pasien.setKecamatanId(obj[4].toString());
                            pasien.setKotaId(obj[5].toString());
                            pasien.setProvinsiId(obj[6].toString());
                        }
                    }
                }

                if (pasien.getIdPasien() != null) {
                    Map hsCriteria = new HashMap();
                    hsCriteria.put("id_pasien", pasien.getIdPasien());
                    List<ItSimrsHeaderChekupEntity> cekKunjungan = headerCheckupDao.getByCriteria(hsCriteria);
                    if (cekKunjungan.size() > 0) {
                        pasien.setIsPasienLama(true);
                    }

                    //cek finger data
                    pasien.setDisabledFingerData(cekFingerData(pasien.getIdPasien()));

                    HeaderDetailCheckup detailCheckup = pasienDao.getLastCheckup(data.getIdPasien());
                    if (detailCheckup.getIdDetailCheckup() != null) {
                        if (detailCheckup.getTglCekup() != null) {
                            String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(detailCheckup.getTglCekup());
                            pasien.setTglCheckup(formatDate);
                            pasien.setIsCheckupUlang("Y");
                            pasien.setIdPelayanan(detailCheckup.getIdPelayanan());
                            pasien.setNoCheckuoUlang(detailCheckup.getNoCheckupUlang());
                            pasien.setIdLastDetailCheckup(detailCheckup.getIdDetailCheckup());
                            pasien.setIsOrderLab(detailCheckup.getIsOrderLab());
                        }
                    }
                }

                Boolean cekPendaftaran = pasienDao.cekPendaftaranPasien(data.getIdPasien());

                if (cekPendaftaran) {
                    pasien.setIsDaftar("Y");
                }else{
                    pasien.setIsDaftar("N");
                }

                list.add(pasien);
            }
        }
        logger.info("[PasienBoImpl.getListComboPasien] End <<<<<<<");
        return list;
    }

    @Override
    public List<Pasien> getTypeAheadPasienByIdAndName(String query) throws GeneralBOException {
        logger.info("[PasienBoImpl.getTypeAheadPasienByIdAndName] Start >>>>>>>");

        List<ImSimrsPasienEntity> pasienEntityList = new ArrayList<>();
        try {
            pasienEntityList = pasienDao.getPasienListByLike(query);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria " + e.getMessage());
        }

        logger.info("[PasienBoImpl.getTypeAheadPasienByIdAndName] End <<<<<<<");
        if (!pasienEntityList.isEmpty()) {
            return setTemplatePasien(pasienEntityList);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Pasien> getListComboPasienByBpjs(String query) throws GeneralBOException {
        logger.info("[PasienBoImpl.getListComboPasienByBpjs] Start >>>>>>>");

        String tmp = "%" + query + "%";

        List<ImSimrsPasienEntity> pasienEntityList = new ArrayList<>();
        try {
            pasienEntityList = pasienDao.getListPasienByTmpBpjs(tmp);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria " + e.getMessage());
        }

        logger.info("[PasienBoImpl.getListComboPasienByBpjs] End <<<<<<<");
        if (!pasienEntityList.isEmpty()) {
            return setTemplatePasien(pasienEntityList);
        }

        return new ArrayList<>();
    }


    @Override
    public List<Pasien> getDataPasien(String desaId) throws GeneralBOException {
        logger.info("[PasienBoImpl.getDataPasien] Start >>>>>>>");
        List<Pasien> list = new ArrayList<>();
        List<Object[]> imSimrsPasienEntities = null;
        try {
            imSimrsPasienEntities = pasienDao.getListAlamat(desaId);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria " + e.getMessage());
        }

        Pasien pasien;
        if (imSimrsPasienEntities != null) {
            for (Object[] data : imSimrsPasienEntities) {
                pasien = new Pasien();
                pasien.setDesaId(data[0].toString());
                pasien.setDesa(data[1].toString());
                pasien.setKecamatan(data[2].toString());
                pasien.setKota(data[3].toString());
                pasien.setProvinsi(data[4].toString());
                list.add(pasien);
            }
        }

        logger.info("[PasienBoImpl.getDataPasien] End <<<<<<<");
        return list;
    }

    @Override
    public Boolean isUserPasienById(String userId, String password) throws GeneralBOException {
        logger.info("[PasienBoImpl.isUserPasienById] Start >>>>>>>");

        Boolean isFound = false;
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pasien", userId);
        hsCriteria.put("password", password);

        List<ImSimrsPasienEntity> pasienEntities = null;
        try {
            pasienEntities = pasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.isUserPasienById] Error when search pasien by criteria " + e.getMessage());
            throw new GeneralBOException("[PasienBoImpl.isUserPasienById] Error when search pasien by criteria " + e.getMessage());
        }

        if (!pasienEntities.isEmpty() && pasienEntities.size() > 0) {
            isFound = true;
        }

        logger.info("[PasienBoImpl.isUserPasienById] End <<<<<<<");
        return isFound;
    }

    @Override
    public void saveEditFinger(String userId, String regTemp, String sn, String vStamp) {
        logger.info("[PasienBoImpl.saveEditFinger] Start >>>>>>>");

        List<ImSimrsFingerDataEntity> fingerDataEntityList = fingerDataDao.getFingerData(regTemp);

        if (fingerDataEntityList.size() == 0) {
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ImSimrsFingerDataEntity fingerDataEntity = new ImSimrsFingerDataEntity();
            fingerDataEntity.setIdFingerData(fingerDataDao.getNextIdFingerData());
            fingerDataEntity.setIdPasien(userId);
            fingerDataEntity.setFingerData(regTemp);
            fingerDataEntity.setSn(sn);

            fingerDataEntity.setAction("C");
            fingerDataEntity.setFlag("Y");
            fingerDataEntity.setCreatedWho("");
            fingerDataEntity.setLastUpdateWho("");
            fingerDataEntity.setLastUpdate(updateTime);
            fingerDataEntity.setCreatedDate(updateTime);
            try {
                fingerDataDao.addAndSave(fingerDataEntity);
            } catch (HibernateException e) {
                logger.error("[PasienBoImpl.saveEditFinger] Error when search pasien by criteria " + e.getMessage());
                throw new GeneralBOException("[PasienBoImpl.saveEditFinger] Error when search pasien by criteria " + e.getMessage());
            }
        }

        logger.info("[PasienBoImpl.saveEditFinger] End <<<<<<<");
    }

    @Override
    public void saveEditPassword(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveEditPassword] Start >>>>>>>");

//        List<ImSimrsPasienEntity> pasienEntities = getEntityByCriteria(bean);
//        if (!pasienEntities.isEmpty() && pasienEntities.size() > 0)
//        {
//            ImSimrsPasienEntity pasienEntity = pasienEntities.get(0);
//            pasienEntity.setPassword(bean.getPassword());
//            pasienEntity.setLastUpdateWho(bean.getLastUpdateWho());
//            pasienEntity.setLastUpdate(bean.getLastUpdate());
//            try {
//                pasienDao.updateAndSave(pasienEntity);
//            } catch (HibernateException e){
//                logger.error("[PasienBoImpl.isUserPasienById] Error when update pasien. "+e.getMessage());
//                throw new GeneralBOException("[PasienBoImpl.isUserPasienById] Error when update pasien. "+e.getMessage());
//            }
//        }

        ImSimrsPasienEntity pasienEntity = new ImSimrsPasienEntity();
        if (bean.getIdPasien() != null) {

            try {
                pasienEntity = pasienDao.getById("idPasien", bean.getIdPasien());
            } catch (HibernateException e) {
                logger.error("[PasienBoImpl.isUserPasienById] Error when update pasien. " + e.getMessage());
                throw new GeneralBOException("[PasienBoImpl.isUserPasienById] Error when update pasien. " + e.getMessage());
            }

            if (pasienEntity != null) {

                pasienEntity.setPassword(bean.getPassword());
                pasienEntity.setLastUpdateWho(bean.getLastUpdateWho());
                pasienEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    pasienDao.updateAndSave(pasienEntity);
                } catch (HibernateException e) {
                    logger.error("[PasienBoImpl.isUserPasienById] Error when update pasien. " + e.getMessage());
                    throw new GeneralBOException("[PasienBoImpl.isUserPasienById] Error when update pasien. " + e.getMessage());
                }
            }
        }

        logger.info("[PasienBoImpl.saveEditPassword] End <<<<<<<");
    }

    @Override
    public void saveCreateUserPasien(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveCreateUserPasien] Start >>>>>>>");

        List<ImSimrsPasienEntity> pasienEntities = getEntityByCriteria(bean);
        if (!pasienEntities.isEmpty() && pasienEntities.size() > 0) {
            ImSimrsPasienEntity pasienEntity = pasienEntities.get(0);
            pasienEntity.setPassword("123");
            pasienEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pasienEntity.setLastUpdate(bean.getLastUpdate());
            try {
                pasienDao.updateAndSave(pasienEntity);
            } catch (HibernateException e) {
                logger.error("[PasienBoImpl.saveCreateUserPasien] Error when update pasien. " + e.getMessage());
                throw new GeneralBOException("[PasienBoImpl.saveCreateUserPasien] Error when update pasien. " + e.getMessage());
            }
        }

        logger.info("[PasienBoImpl.saveCreateUserPasien] End <<<<<<<");
    }

    public String getIdPasien() {
        logger.info("[PasienBoImpl.getIdPasien] Start >>>>>>>");
        String id = "";

        try {
            id = pasienDao.getNextIdPasien();
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getIdPasien] Error when get next id pasien");
        }

        logger.info("[PasienBoImpl.getIdPasien] End <<<<<<<");
        return id;
    }

    @Override
    public List<FingerData> getListFingerPrint(String pasienId) throws GeneralBOException {
        logger.info("[PasienBoImpl.getListFingerPrint] Start >>>>>>>");
        List<FingerData> list = new ArrayList<>();
        List<ImSimrsFingerDataEntity> imSimrsFingerDataEntityList = null;
        try {
            imSimrsFingerDataEntityList = fingerDataDao.getFingerByPasien(pasienId);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getListFingerPrint] Error when search pasien by criteria " + e.getMessage());
        }

        if (imSimrsFingerDataEntityList != null) {
            for (ImSimrsFingerDataEntity data : imSimrsFingerDataEntityList) {
                FingerData fingerData = new FingerData();
                fingerData.setIdFingerData(data.getIdFingerData());
                fingerData.setFingerData(data.getFingerData());
                fingerData.setIdPasien(data.getIdPasien());
                fingerData.setSn(data.getSn());
                list.add(fingerData);
            }
        }

        logger.info("[PasienBoImpl.getListFingerPrint] End <<<<<<<");
        return list;
    }

    @Override
    public List<Pasien> getListOfPasienByQuery(String query) throws GeneralBOException {
        logger.info("[PasienBoImpl.getListOfPasienByQuery] start process >>>");

        List<Pasien> pasienList = new ArrayList();
        List<ImSimrsPasienEntity> pasienEntityList = null;
        try {
            pasienEntityList = pasienDao.getListPasienByTmpBpjs(query);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getListOfPasienByQuery] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        if (pasienEntityList != null) {
            for (ImSimrsPasienEntity simrsPasienEntity : pasienEntityList) {
                Pasien pasien = new Pasien();
                pasien.setIdPasien(simrsPasienEntity.getIdPasien());
                pasien.setNoBpjs(simrsPasienEntity.getNoBpjs());
                pasien.setNama(simrsPasienEntity.getNama());
                pasienList.add(pasien);
            }
        }
        logger.info("[PasienBoImpl.getListOfPasienByQuery] end process <<<");
        return pasienList;
    }

    @Override
    public ImSimrsPasienEntity getPasienByIdPasien(String idPasien) {
        logger.info("[PasienBoImpl.getPasienByIdPasien] start process >>>");

        ImSimrsPasienEntity pasienEntity = new ImSimrsPasienEntity();
        try {
            pasienEntity = pasienDao.getById("idPasien", idPasien);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getPasienByIdPasien] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        logger.info("[PasienBoImpl.getPasienByIdPasien] end process <<<");
        return pasienEntity;
    }

    @Override
    public CrudResponse saveUploadRekamMedicLama(ImSImrsRekamMedicLamaEntity rekamMedicLama, List<ImSimrsUploadRekamMedicLamaEntity> uploads) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (rekamMedicLama.getIdPasien() != null && !"".equalsIgnoreCase(rekamMedicLama.getIdPasien())) {

            rekamMedicLama.setId("RM" + rekamMedicLamaDao.getNextSeq());
            try {
                rekamMedicLamaDao.addAndSave(rekamMedicLama);
                response.setStatus("success");
                response.setMsg("Oke");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg(e.getMessage());
                logger.error("[PasienBoImpl.saveUploadRekamMedicLama] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving data, please info to your admin..." + e.getMessage());

            }

            if (uploads.size() > 0) {
                for (ImSimrsUploadRekamMedicLamaEntity uploadEntity : uploads) {
                    uploadEntity.setId("URM"+uploadRekamMedicLamaDao.getNextSeq());
                    uploadEntity.setHeadId(rekamMedicLama.getId());
                    uploadEntity.setFlag(rekamMedicLama.getFlag());
                    uploadEntity.setAction(rekamMedicLama.getAction());
                    uploadEntity.setCreatedDate(rekamMedicLama.getCreatedDate());
                    uploadEntity.setCreatedWho(rekamMedicLama.getCreatedWho());
                    uploadEntity.setLastUpdate(rekamMedicLama.getLastUpdate());
                    uploadEntity.setLastUpdateWho(rekamMedicLama.getLastUpdateWho());

                    try {
                        uploadRekamMedicLamaDao.addAndSave(uploadEntity);
                        response.setStatus("success");
                        response.setMsg("Oke");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
                        logger.error("[PasienBoImpl.saveUploadRekamMedicLama] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving data, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }
        return response;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    private Boolean cekFingerData(String idPasien) {
        Boolean response = false;

        try {
            response = fingerDataDao.cekFingerData(idPasien);
        } catch (HibernateException e) {
            logger.error("Found Error when cek finger data " + e.getMessage());
        }

        return response;
    }

    @Override
    public String getNextIdImg() {
        return uploadRekamMedicLamaDao.getNextSeq();
    }

    @Override
    public Pasien saveAddWithResponse(Pasien pasien) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveAdd] Start >>>>>>>");
        Pasien response = new Pasien();

        if (pasien != null) {
            ImSimrsPasienEntity pasienEntity = new ImSimrsPasienEntity();
            String id = getIdPasien();

            pasienEntity.setIdPasien(CommonUtil.userBranchLogin() + dateFormater("yy") + id);
            pasienEntity.setNama(pasien.getNama());
            pasienEntity.setJenisKelamin(pasien.getJenisKelamin());
            pasienEntity.setNoKtp(pasien.getNoKtp().replace("_",""));
            pasienEntity.setNoBpjs(pasien.getNoBpjs());
            pasienEntity.setTempatLahir(pasien.getTempatLahir());
            if(pasien.getNoTelp() != null && !"".equalsIgnoreCase(pasien.getNoTelp())){
                String trimNoTelp = pasien.getNoTelp().replace("-","").replace("_","");
                pasienEntity.setNoTelp(trimNoTelp);
            }
            if (pasien.getTglLahir() != null && !"".equalsIgnoreCase(pasien.getTglLahir())) {
                pasienEntity.setTglLahir(java.sql.Date.valueOf(pasien.getTglLahir()));
            }
            BigInteger bigInteger = new BigInteger(pasien.getDesaId());
            pasienEntity.setDesaId(bigInteger);
            pasienEntity.setJalan(pasien.getAlamat());
            pasienEntity.setSuku(pasien.getSuku());
            pasienEntity.setAgama(pasien.getAgama());
            pasienEntity.setProfesi(pasien.getProfesi());
            pasienEntity.setNoTelp(pasien.getNoTelp());
            pasienEntity.setUrlKtp(pasien.getUrlKtp());
            pasienEntity.setFlag("Y");
            pasienEntity.setAction("C");
            pasienEntity.setCreatedDate(pasien.getCreatedDate());
            pasienEntity.setLastUpdate(pasien.getLastUpdate());
            pasienEntity.setCreatedWho(pasien.getCreatedWho());
            pasienEntity.setLastUpdateWho(pasien.getLastUpdateWho());
            pasienEntity.setStatusPerkawinan(pasien.getStatusPerkawinan());
            pasienEntity.setPendidikan(pasien.getPendidikan());

            try {

                pasienDao.addAndSave(pasienEntity);

                response.setIdPasien(pasienEntity.getIdPasien());
                response.setNoKtp(pasienEntity.getNoKtp());
                response.setNama(pasienEntity.getNama());
                response.setJenisKelamin(pasienEntity.getJenisKelamin());
                response.setNoBpjs(pasienEntity.getNoBpjs());
                response.setTempatLahir(pasienEntity.getTempatLahir());
                response.setNoTelp(pasienEntity.getNoTelp());
                response.setTglLahir(pasienEntity.getTglLahir().toString());
                response.setDesaId(pasienEntity.getDesaId().toString());
                if (pasienEntity.getDesaId() != null) {
                    List<Object[]> objs = provinsiDao.getListAlamatByDesaId(pasienEntity.getDesaId().toString());
                    if (!objs.isEmpty()) {
                        for (Object[] obj : objs) {
                            response.setDesa(obj[0].toString());
                            response.setKecamatan(obj[1].toString());
                            response.setKota(obj[2].toString());
                            response.setProvinsi(obj[3].toString());
                            response.setKecamatanId(obj[4].toString());
                            response.setKotaId(obj[5].toString());
                            response.setProvinsiId(obj[6].toString());
                        }
                    }
                }
                response.setJalan(pasienEntity.getJalan());
                response.setSuku(pasienEntity.getSuku());
                response.setAgama(pasienEntity.getAgama());
                response.setProfesi(pasienEntity.getProfesi());
                response.setUrlKtp(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_KTP_PASIEN + pasienEntity.getUrlKtp());
                response.setImgKtp(pasienEntity.getUrlKtp());
                response.setStatusPerkawinan(pasienEntity.getStatusPerkawinan());
                response.setPendidikan(pasienEntity.getPendidikan());
                response.setStatus("success");
                response.setMsg("Berhasil");

            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Error " + e.getMessage());
                logger.error("[PasienBoImpl.saveAdd] Error when saving data pasien", e);
                throw new GeneralBOException(" Error when saving data pasien " + e.getMessage());
            }
        } else {
            response.setStatus("error");
            logger.error("[PasienBoImpl.saveAdd] Error when saving data pasien data is null");
            throw new GeneralBOException(" Error when saving data pasien data is null");
        }
        logger.info("[PasienBoImpl.saveAdd] End <<<<<<<");
        return response;
    }

    @Override
    public List<Pasien> getListPasienWithPaket(String nama) throws GeneralBOException {
        List<Pasien> list = new ArrayList<>();
        try {
            list = pasienDao.getListPasienWithPaket(nama);
        } catch (HibernateException e) {
            logger.error("Found Erro " + e.getMessage());
        }
        return list;
    }

    @Override
    public ImSimrsPasienEntity getPasienById(String id) throws GeneralBOException {
        logger.info("[PasienBoImpl.getPasienById] Start >>>>>>>");
        logger.info("[PasienBoImpl.getPasienById] End <<<<<<<");
        return pasienDao.getById("idPasien", id);
    }

    @Override
    public List<Pasien> getComboRmLama(String rm) throws GeneralBOException {
        return pasienDao.getComboRmLama(rm);
    }

    @Override
    public Pasien getDetailPasien(String id) throws GeneralBOException {
        Pasien pasien = new Pasien();
        List<ImSimrsPasienEntity> pasienEntityList = new ArrayList<>();
        try {
            pasienEntityList = pasienDao.getDetailPasien(id);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria " + e.getMessage());
        }
        if (pasienEntityList.size() > 0) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ImSimrsPasienEntity data = pasienEntityList.get(0);
            pasien = new Pasien();
            pasien.setIdPasien(data.getIdPasien());
            pasien.setNama(data.getNama());
            pasien.setJenisKelamin(data.getJenisKelamin());
            pasien.setNoKtp(data.getNoKtp());
            pasien.setNoBpjs(data.getNoBpjs());
            pasien.setTempatLahir(data.getTempatLahir());

            String strDate = formatter.format(data.getTglLahir());
            pasien.setTglLahir(strDate);

            pasien.setDesaId(data.getDesaId().toString());
            pasien.setJalan(data.getJalan());
            pasien.setSuku(data.getSuku());
            pasien.setAgama(data.getAgama());
            pasien.setProfesi(data.getProfesi());
            pasien.setNoTelp(data.getNoTelp());
            pasien.setImgKtp(data.getUrlKtp());
            pasien.setUrlKtp(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_KTP_PASIEN + data.getUrlKtp());
            pasien.setFlag(data.getFlag());
            pasien.setAction(data.getAction());
            pasien.setCreatedDate(data.getCreatedDate());
            pasien.setCreatedWho(data.getCreatedWho());
            pasien.setLastUpdate(data.getLastUpdate());
            pasien.setLastUpdateWho(data.getLastUpdateWho());
            pasien.setEmail(data.getEmail());
            pasien.setPassword(data.getPassword());
            pasien.setStatusPerkawinan(data.getStatusPerkawinan());
            pasien.setPendidikan(data.getPendidikan());
            pasien.setFlagMeninggal(data.getFlagMeninggal());
            pasien.setTglMeninggal(data.getTanggalMeninggal());

            if (pasien.getDesaId() != null) {
                List<Object[]> objs = provinsiDao.getListAlamatByDesaId(pasien.getDesaId().toString());
                if (!objs.isEmpty()) {
                    for (Object[] obj : objs) {
                        pasien.setDesa(obj[0].toString());
                        pasien.setKecamatan(obj[1].toString());
                        pasien.setKota(obj[2].toString());
                        pasien.setProvinsi(obj[3].toString());
                        pasien.setKecamatanId(obj[4].toString());
                        pasien.setKotaId(obj[5].toString());
                        pasien.setProvinsiId(obj[6].toString());
                    }
                }
            }

            if (pasien.getIdPasien() != null) {
                Map hsCriteria = new HashMap();
                hsCriteria.put("id_pasien", pasien.getIdPasien());
                List<ItSimrsHeaderChekupEntity> cekKunjungan = headerCheckupDao.getByCriteria(hsCriteria);
                if (cekKunjungan.size() > 0) {
                    pasien.setIsPasienLama(true);
                }

                //cek finger data
                pasien.setDisabledFingerData(cekFingerData(pasien.getIdPasien()));

                HeaderDetailCheckup detailCheckup = pasienDao.getLastCheckup(data.getIdPasien());
                if (detailCheckup.getIdDetailCheckup() != null) {
                    if (detailCheckup.getTglCekup() != null) {
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(detailCheckup.getTglCekup());
                        pasien.setTglCheckup(formatDate);
                        pasien.setIsCheckupUlang("Y");
                        pasien.setIdPelayanan(detailCheckup.getIdPelayanan());
                        pasien.setNoCheckuoUlang(detailCheckup.getNoCheckupUlang());
                        pasien.setIdLastDetailCheckup(detailCheckup.getIdDetailCheckup());
                        pasien.setIsOrderLab(detailCheckup.getIsOrderLab());
                    }
                }
            }

            Boolean cekPendaftaran = pasienDao.cekPendaftaranPasien(data.getIdPasien());

            if (cekPendaftaran) {
                pasien.setIsDaftar("Y");
            }else{
                pasien.setIsDaftar("N");
            }
        }
        return pasien;
    }

    @Override
    public PasienSementara saveAddPasienSementara(PasienSementara bean) throws GeneralBOException {
        ImSimrsPasienSementaraEntity entity = new ImSimrsPasienSementaraEntity();
        String id = pasienSementaraDao.getNextIdPasienSementara();
        bean.setId("PSS" + id);
        entity.setId("PSS" + id);
        entity.setNama(bean.getNama());
        entity.setNoKtp(bean.getNoKtp());
        entity.setNoTelp(bean.getNoTelp());
        entity.setJenisKelamin(bean.getJenisKelamin());
        entity.setTempatLahir(bean.getTempatLahir());
        entity.setTglLahir(bean.getTglLahir());
        entity.setSuku(bean.getSuku());
        entity.setEmail(bean.getEmail());
        entity.setPassword(bean.getPassword());
        entity.setAgama(bean.getAgama());
        entity.setDesaId(bean.getDesaId());
        entity.setJalan(bean.getJalan());
        entity.setFlag(bean.getFlag());
        entity.setAction(bean.getAction());
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdateWho(bean.getLastUpdateWho());
        entity.setFlagLogin(bean.getFlagLogin());
        entity.setUrlKtp(bean.getUrlKtp());
        entity.setProfesi(bean.getProfesi());
        try {
            pasienSementaraDao.addAndSave(entity);
        } catch (GeneralBOException e) {
            logger.error("[PasienBoImpl.saveAddPasienSementara] Error when saving data pasien data sementara is null");
            throw new GeneralBOException(" Error when saving data pasien data sementara is null");
        }
        //send email license
        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(entity.getCreatedDate());
        Email email = new Email();
        email.setFrom(CommonConstant.EMAIL_USERNAME);
        email.setPassword(CommonConstant.EMAIL_PASSWORD);
        email.setTo(entity.getEmail());
        email.setSubject("[GO-MEDSYS MOBILE] User ID untuk login ke aplikasi");
        email.setMsg("<h2>GO-MEDSYS MOBILE</h2>\n" +
                "=========================================\n" +
                "<h3>Gunakan Email anda beserta password yang telah ada inputkan ketika registrasi untuk login ke aplikasi GO-MEDSYS</h3>\n" +
                "<br> \n" +
                "<h3>login menggunakan email bersifat sementara, anda akan mendapatkan User ID baru setelah melakukan verifikasi pendaftaran online di Rumah Sakit yang anda tuju<h3>\n" +
                "<br> \n" +
                "<table width=\"100%\">\n" +
                "<tr>\n" +
                "<td width=\"20%\">Email</td>\n" +
                "<td>: " + entity.getEmail() + "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>Nama</td>\n" +
                "<td>: " + entity.getNama() + "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>No. KTP</td>\n" +
                "<td>: " + entity.getNoKtp() + "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "</table>\n" +
                "=========================================\n" +
                "<br> \n" +
                "<br>\n");
        CommonUtil.sendEmail(email);

        return bean;
    }

    @Override
    public List<PasienSementara> getPasienSementaraByCriteria(PasienSementara bean) throws GeneralBOException {
        List<PasienSementara> result = new ArrayList<>();
        List<ImSimrsPasienSementaraEntity> entities;

        Map hsCriteria = new HashMap();
        hsCriteria.put("id", bean.getId());
        hsCriteria.put("nama", bean.getNama());
        hsCriteria.put("desa_id", bean.getDesaId());
        hsCriteria.put("no_ktp", bean.getNoKtp());
        hsCriteria.put("flag", bean.getFlag());
        hsCriteria.put("email", bean.getEmail());

        try {
            entities = pasienSementaraDao.getByCriteria(hsCriteria);
        } catch (GeneralBOException e) {
            logger.error("[PasienBoImpl.getPasienSementaraByCriteria]");
            throw new GeneralBOException(" Error when getPasienByCriteria " + e.getMessage());
        }

        if (entities != null) {
            for(ImSimrsPasienSementaraEntity item : entities) {
                PasienSementara pasienSementara = new PasienSementara();
                pasienSementara.setId(item.getId());
                pasienSementara.setNama(item.getNama());
                pasienSementara.setJenisKelamin(item.getJenisKelamin());
                pasienSementara.setNoKtp(item.getNoKtp());
                pasienSementara.setTempatLahir(item.getTempatLahir());
                pasienSementara.setTglLahir(item.getTglLahir());
                pasienSementara.setDesaId(item.getDesaId());
                pasienSementara.setJalan(item.getJalan());
                pasienSementara.setSuku(item.getSuku());
                pasienSementara.setAgama(item.getAgama());
                pasienSementara.setNoTelp(item.getNoTelp());
                pasienSementara.setUrlKtp(item.getUrlKtp());
                pasienSementara.setFlag(item.getFlag());
                pasienSementara.setAction(item.getAction());
                pasienSementara.setCreatedDate(item.getCreatedDate());
                pasienSementara.setLastUpdate(item.getLastUpdate());
                pasienSementara.setCreatedWho(item.getCreatedWho());
                pasienSementara.setLastUpdateWho(item.getLastUpdateWho());
                pasienSementara.setFlagLogin(item.getFlagLogin());
                pasienSementara.setEmail(item.getEmail());
                pasienSementara.setPassword(item.getPassword());
                pasienSementara.setProfesi(item.getProfesi());

                result.add(pasienSementara);
            }
        }

        return result;
    }

    @Override
    public void saveEditPasienSementara (PasienSementara bean) throws GeneralBOException {
        ImSimrsPasienSementaraEntity entity = new ImSimrsPasienSementaraEntity();
        entity.setId(bean.getId());
        entity.setNama(bean.getNama());
        entity.setJenisKelamin(bean.getJenisKelamin());
        entity.setNoKtp(bean.getNoKtp());
        entity.setTempatLahir(bean.getTempatLahir());
        entity.setTglLahir(bean.getTglLahir());
        entity.setDesaId(bean.getDesaId());
        entity.setJalan(bean.getJalan());
        entity.setSuku(bean.getSuku());
        entity.setAgama(bean.getAgama());
        entity.setNoTelp(bean.getNoTelp());
        entity.setUrlKtp(bean.getUrlKtp());
        entity.setFlag(bean.getFlag());
        entity.setAction("U");
        entity.setCreatedWho(bean.getNama());
        entity.setLastUpdateWho(bean.getNama());
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setFlagLogin(bean.getFlagLogin());
        entity.setEmail(bean.getEmail());
        entity.setPassword(bean.getPassword());
        try {
            pasienSementaraDao.updateAndSave(entity);
        } catch (GeneralBOException e) {
            logger.error("[PasienBoImpl.getPasienSementaraByCriteria]");
            throw new GeneralBOException(" Error when getPasienByCriteria " + e.getMessage());
        }
    }

    @Override
    public ImSimrsPasienSementaraEntity getPasienSementaraEntityById(String id) throws GeneralBOException {
        logger.info("[PasienBoImpl.getPasienSementaraEntityById] >>>");
        return pasienSementaraDao.getById("id", id);
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setPasienSementaraDao(PasienSementaraDao pasienSementaraDao) {
        this.pasienSementaraDao = pasienSementaraDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public void setRekamMedicLamaDao(RekamMedicLamaDao rekamMedicLamaDao) {
        this.rekamMedicLamaDao = rekamMedicLamaDao;
    }

    public void setUploadRekamMedicLamaDao(UploadRekamMedicLamaDao uploadRekamMedicLamaDao) {
        this.uploadRekamMedicLamaDao = uploadRekamMedicLamaDao;
    }
}
