package com.neurix.simrs.master.pasien.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.belajar.model.Belajar;
import com.neurix.hris.master.cuti.model.ImCutiEntity;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.Pasien;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class PasienBoImpl implements PasienBo {

    protected static transient Logger logger = org.apache.log4j.Logger.getLogger(PasienBoImpl.class);

    private PasienDao pasienDao;
    private Date date;

    @Override
    public List<Pasien> getByCriteria(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.getByCriteria] Start >>>>>>>");

        List<Pasien> pasiens = new ArrayList<>();
        if (bean != null){

            List<ImSimrsPasienEntity> imSimrsPasienEntities = getEntityByCriteria(bean);

            if (!imSimrsPasienEntities.isEmpty()){
                pasiens = setTemplatePasien(imSimrsPasienEntities);
            }

        }

        logger.info("[PasienBoImpl.getByCriteria] End <<<<<<<");
        return pasiens;
    }

    public List<ImSimrsPasienEntity> getEntityByCriteria(Pasien bean) throws GeneralBOException{
        logger.info("[PasienBoImpl.getEntityByCriteria] Start >>>>>>>");
        List<ImSimrsPasienEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
            hsCriteria.put("id_pasien", bean.getIdPasien());
        }
        if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())){
            hsCriteria.put("nama", bean.getNama());
        }
        if (bean.getDesaId() != null && !"".equalsIgnoreCase(bean.getDesaId())){
            hsCriteria.put("desa_id", bean.getDesaId());
        }
        if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())){
            hsCriteria.put("no_ktp", bean.getNoKtp());
        }
        if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())){
            hsCriteria.put("no_bpjs", bean.getIdPasien());
        }

        List<ImSimrsPasienEntity> imSimrsPasienEntities = null;
        try {
            imSimrsPasienEntities = pasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria "+e.getMessage());
        }

        if (!imSimrsPasienEntities.isEmpty()){
            results = imSimrsPasienEntities;
        }

        logger.info("[PasienBoImpl.getEntityByCriteria] End <<<<<<<");
        return results;
    }


    public List<Pasien> setTemplatePasien(List<ImSimrsPasienEntity> listEntity){
        logger.info("[PasienBoImpl.setTemplatePasien] Start >>>>>>>");
        List<Pasien> list = new ArrayList<>();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Pasien pasien;
        for (ImSimrsPasienEntity data : listEntity){
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
            pasien.setUrlKtp(data.getUrlKtp());
            pasien.setFlag(data.getFlag());
            pasien.setAction(data.getAction());
            pasien.setCreatedDate(data.getCreatedDate());
            pasien.setCreatedWho(data.getCreatedWho());
            pasien.setLastUpdate(data.getLastUpdate());
            pasien.setLastUpdateWho(data.getLastUpdateWho());
            pasien.setEmail(data.getEmail());
            pasien.setPassword(data.getPassword());
            list.add(pasien);
        }

        logger.info("[PasienBoImpl.setTemplatePasien] End <<<<<<<");
        return list;
    }

    @Override
    public void saveAdd(Pasien pasien) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveAdd] Start >>>>>>>");

        if (pasien != null){
            ImSimrsPasienEntity pasienEntity = new ImSimrsPasienEntity();
            String id = getIdPasien();

            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

            try{
                date = formater.parse(pasien.getTglLahir());
//                tglLahir = formater.format(date);
            }catch (ParseException e){
                e.printStackTrace();
            }

            pasienEntity.setIdPasien(id);
            pasienEntity.setNama(pasien.getNama());
            pasienEntity.setJenisKelamin(pasien.getJenisKelamin());
            pasienEntity.setNoKtp(pasien.getNoKtp());
            pasienEntity.setNoBpjs(pasien.getNoBpjs());
            pasienEntity.setTempatLahir(pasien.getTempatLahir());
            pasienEntity.setNoTelp(pasien.getNoTelp());
            pasienEntity.setTglLahir(date);
            BigInteger bigInteger = new BigInteger(pasien.getDesaId());
            pasienEntity.setDesaId(bigInteger);
            pasienEntity.setJalan(pasien.getAlamat());
            pasienEntity.setSuku(pasien.getSuku());
            pasienEntity.setAgama(pasien.getAgama());
            pasienEntity.setProfesi(pasien.getProfesi());
            pasienEntity.setNoTelp(pasien.getNoTelp());
            pasienEntity.setUrlKtp(pasien.getNoKtp());
            pasienEntity.setFlag("Y");
            pasienEntity.setAction("C");
            pasienEntity.setCreatedDate(pasien.getCreatedDate());
            pasienEntity.setLastUpdate(pasien.getLastUpdate());
            pasienEntity.setCreatedWho(pasien.getCreatedWho());
            pasienEntity.setLastUpdateWho(pasien.getLastUpdateWho());

            try {
                pasienDao.addAndSave(pasienEntity);
            } catch (HibernateException e){
                logger.error("[PasienBoImpl.saveAdd] Error when saving data pasien", e);
                throw new GeneralBOException(" Error when saving data pasien "+e.getMessage());
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

        if (pasien != null && pasien.getIdPasien() != null && !"".equalsIgnoreCase(pasien.getIdPasien())){

            Pasien newPasien = new Pasien();
            newPasien.setIdPasien(pasien.getIdPasien());
            ImSimrsPasienEntity pasienEntity = getEntityByCriteria(newPasien).get(0);

            if (pasienEntity != null){

                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

                try{
                    date = formater.parse(pasien.getTglLahir());
//                tglLahir = formater.format(date);
                }catch (ParseException e){
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
                } catch (HibernateException e){
                    logger.error("[PasienBoImpl.saveAdd] Error when Updating data pasien", e);
                    throw new GeneralBOException(" Error when Updating data pasien "+e.getMessage());
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

        if (bean!=null) {

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

                try{
                    date = formater.parse(bean.getTglLahir());
//                tglLahir = formater.format(date);
                }catch (ParseException e){
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
        } catch (HibernateException e){
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria "+e.getMessage());
        }

        logger.info("[PasienBoImpl.getListComboPasien] End <<<<<<<");
        if (!pasienEntityList.isEmpty()){
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
        } catch (HibernateException e){
            logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria "+e.getMessage());
        }

        Pasien pasien;
        if (imSimrsPasienEntities != null){
            for (Object[] data: imSimrsPasienEntities){
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
        } catch (HibernateException e){
            logger.error("[PasienBoImpl.isUserPasienById] Error when search pasien by criteria "+e.getMessage());
            throw new GeneralBOException("[PasienBoImpl.isUserPasienById] Error when search pasien by criteria "+e.getMessage());
        }

        if (!pasienEntities.isEmpty() && pasienEntities.size() > 0){
            isFound = true;
        }

        logger.info("[PasienBoImpl.isUserPasienById] End <<<<<<<");
        return isFound;
    }

    @Override
    public void saveEditPassword(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveEditPassword] Start >>>>>>>");

        List<ImSimrsPasienEntity> pasienEntities = getEntityByCriteria(bean);
        if (!pasienEntities.isEmpty() && pasienEntities.size() > 0)
        {
            ImSimrsPasienEntity pasienEntity = pasienEntities.get(0);
            pasienEntity.setPassword(bean.getPassword());
            pasienEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pasienEntity.setLastUpdate(bean.getLastUpdate());
            try {
                pasienDao.updateAndSave(pasienEntity);
            } catch (HibernateException e){
                logger.error("[PasienBoImpl.isUserPasienById] Error when update pasien. "+e.getMessage());
                throw new GeneralBOException("[PasienBoImpl.isUserPasienById] Error when update pasien. "+e.getMessage());
            }
        }

        logger.info("[PasienBoImpl.saveEditPassword] End <<<<<<<");
    }

    @Override
    public void saveCreateUserPasien(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveCreateUserPasien] Start >>>>>>>");

        List<ImSimrsPasienEntity> pasienEntities = getEntityByCriteria(bean);
        if (!pasienEntities.isEmpty() && pasienEntities.size() > 0)
        {
            ImSimrsPasienEntity pasienEntity = pasienEntities.get(0);
            pasienEntity.setPassword("123");
            pasienEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pasienEntity.setLastUpdate(bean.getLastUpdate());
            try {
                pasienDao.updateAndSave(pasienEntity);
            } catch (HibernateException e){
                logger.error("[PasienBoImpl.saveCreateUserPasien] Error when update pasien. "+e.getMessage());
                throw new GeneralBOException("[PasienBoImpl.saveCreateUserPasien] Error when update pasien. "+e.getMessage());
            }
        }

        logger.info("[PasienBoImpl.saveCreateUserPasien] End <<<<<<<");
    }

    public String getIdPasien(){
        logger.info("[PasienBoImpl.getIdPasien] Start >>>>>>>");
        String id = "";

        try {
            id = pasienDao.getNextIdPasien();
        } catch (HibernateException e){
            logger.error("[PasienBoImpl.getIdPasien] Error when get next id pasien");
        }

        logger.info("[PasienBoImpl.getIdPasien] End <<<<<<<");
        return id;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
