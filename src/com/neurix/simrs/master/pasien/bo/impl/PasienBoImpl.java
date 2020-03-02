package com.neurix.simrs.master.pasien.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.belajar.model.Belajar;
import com.neurix.hris.master.cuti.model.ImCutiEntity;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;

import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.dao.FingerDataDao;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.dao.RekamMedicLamaDao;
import com.neurix.simrs.master.pasien.dao.UploadRekamMedicLamaDao;
import com.neurix.simrs.master.pasien.model.*;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
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

        List<Pasien> pasiens = new ArrayList<>();
        if (bean != null) {

            List<ImSimrsPasienEntity> imSimrsPasienEntities = getEntityByCriteria(bean);

            if (!imSimrsPasienEntities.isEmpty()) {
                pasiens = setTemplatePasien(imSimrsPasienEntities);
            }

        }

        logger.info("[PasienBoImpl.getByCriteria] End <<<<<<<");
        return pasiens;
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

            try {
                date = formater.parse(pasien.getTglLahir());
//                tglLahir = formater.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            pasienEntity.setIdPasien(CommonUtil.userBranchLogin()+dateFormater("MM")+dateFormater("yy")+id);
            pasienEntity.setNama(pasien.getNama());
            pasienEntity.setJenisKelamin(pasien.getJenisKelamin());
            pasienEntity.setNoKtp(pasien.getNoKtp());
            pasienEntity.setNoBpjs(pasien.getNoBpjs());
            pasienEntity.setTempatLahir(pasien.getTempatLahir());

            pasienEntity.setNoTelp(pasien.getNoTelp());
            pasienEntity.setTglLahir(date);
            BigInteger bigInteger = new BigInteger(pasien.getDesaId());
            pasienEntity.setDesaId(bigInteger);
            pasienEntity.setJalan(pasien.getJalan());

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
    public void saveEdit(Pasien pasien) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveEdit] Start >>>>>>>");

        if (pasien != null && pasien.getIdPasien() != null && !"".equalsIgnoreCase(pasien.getIdPasien())) {

            Pasien newPasien = new Pasien();
            newPasien.setIdPasien(pasien.getIdPasien());
            ImSimrsPasienEntity pasienEntity = getEntityByCriteria(newPasien).get(0);

            if (pasienEntity != null) {

                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    date = formater.parse(pasien.getTglLahir());
//                tglLahir = formater.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                pasienEntity.setNama(pasien.getNama());
                pasienEntity.setJenisKelamin(pasien.getJenisKelamin());
                pasienEntity.setNoKtp(pasien.getNoKtp());
                pasienEntity.setNoBpjs(pasien.getNoBpjs());
                pasienEntity.setTempatLahir(pasien.getTempatLahir());

                pasienEntity.setTglLahir(date);
                BigInteger bigInteger = new BigInteger(pasien.getDesaId());
                pasienEntity.setDesaId(bigInteger);

                pasienEntity.setJalan(pasien.getJalan());
                pasienEntity.setSuku(pasien.getSuku());
                pasienEntity.setAgama(pasien.getAgama());
                pasienEntity.setProfesi(pasien.getProfesi());
                pasienEntity.setNoTelp(pasien.getNoTelp());
                pasienEntity.setUrlKtp(pasien.getNoKtp());
                pasienEntity.setFlag(pasien.getFlag());
                pasienEntity.setAction("U");
                pasienEntity.setLastUpdate(pasien.getLastUpdate());
                pasienEntity.setLastUpdateWho(pasien.getLastUpdateWho());

                try {
                    pasienDao.updateAndSave(pasienEntity);
                } catch (HibernateException e) {
                    logger.error("[PasienBoImpl.saveAdd] Error when Updating data pasien", e);
                    throw new GeneralBOException(" Error when Updating data pasien " + e.getMessage());
                }
            } else {
                logger.error("[PasienBoImpl.saveAdd] Error when get entity pasien is null");
                throw new GeneralBOException("  Error when get entity pasien is null");
            }

        } else {
            logger.error("[PasienBoImpl.saveAdd] Error when saving data pasien data is null");
            throw new GeneralBOException(" Error when saving data pasien data is null");
        }

        logger.info("[PasienBoImpl.saveEdit] End <<<<<<<");
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

                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    date = formater.parse(bean.getTglLahir());
//                tglLahir = formater.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Modify from bean to entity serializable
                entity.setNama(bean.getNama());
                entity.setJenisKelamin(bean.getJenisKelamin());
                entity.setNoKtp(bean.getNoKtp());
                entity.setNoBpjs(bean.getNoBpjs());
                entity.setTempatLahir(bean.getTempatLahir());

                entity.setTglLahir(date);
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

        List<ImSimrsPasienEntity> pasienEntityList = new ArrayList<>();
        try {
            pasienEntityList = pasienDao.getListPasienByTmp(tmp);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria " + e.getMessage());
        }

        logger.info("[PasienBoImpl.getListComboPasien] End <<<<<<<");
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
    public void saveUploadRekamMedicLama(ImSImrsRekamMedicLamaEntity rekamMedicLama, List<ImSimrsUploadRekamMedicLamaEntity> uploads) throws GeneralBOException {

        if (rekamMedicLama.getIdPasien() != null && !"".equalsIgnoreCase(rekamMedicLama.getIdPasien())) {

            ImSImrsRekamMedicLamaEntity rekamMedicLamaEntity = new ImSImrsRekamMedicLamaEntity();
            rekamMedicLamaEntity.setId("RM" + rekamMedicLamaDao.getNextSeq());
            rekamMedicLamaEntity.setIdPasien(rekamMedicLama.getIdPasien());
            rekamMedicLamaEntity.setBranchId(rekamMedicLama.getBranchId());
            rekamMedicLamaEntity.setFlag(rekamMedicLama.getFlag());
            rekamMedicLamaEntity.setAction(rekamMedicLama.getAction());
            rekamMedicLamaEntity.setLastUpdate(rekamMedicLama.getLastUpdate());
            rekamMedicLamaEntity.setLastUpdateWho(rekamMedicLama.getLastUpdateWho());
            rekamMedicLamaEntity.setCreatedDate(rekamMedicLama.getCreatedDate());
            rekamMedicLamaEntity.setCreatedWho(rekamMedicLama.getCreatedWho());

            try {
                rekamMedicLamaDao.addAndSave(rekamMedicLamaEntity);
            } catch (HibernateException e) {
                logger.error("[PasienBoImpl.saveUploadRekamMedicLama] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving data, please info to your admin..." + e.getMessage());
            }

            if (uploads.size() > 0) {
                for (ImSimrsUploadRekamMedicLamaEntity uploadEntity : uploads) {
                    uploadEntity.setHeadId(rekamMedicLamaEntity.getId());
                    uploadEntity.setFlag(rekamMedicLama.getFlag());
                    uploadEntity.setAction(rekamMedicLama.getAction());
                    uploadEntity.setCreatedDate(rekamMedicLama.getCreatedDate());
                    uploadEntity.setCreatedWho(rekamMedicLama.getCreatedWho());
                    uploadEntity.setLastUpdate(rekamMedicLama.getLastUpdate());
                    uploadEntity.setLastUpdateWho(rekamMedicLama.getLastUpdateWho());

                    try {
                        uploadRekamMedicLamaDao.addAndSave(uploadEntity);
                    } catch (HibernateException e) {
                        logger.error("[PasienBoImpl.saveUploadRekamMedicLama] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving data, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }
    }

    private String dateFormater(String type){
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    @Override
    public String getNextIdImg() {
        return uploadRekamMedicLamaDao.getNextSeq();
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
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
