package com.neurix.hris.master.provinsi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.provinsi.bo.ProvinsiBo;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;
import com.neurix.hris.master.provinsi.model.*;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class ProvinsiBoImpl implements ProvinsiBo {

    protected static transient Logger logger = Logger.getLogger(ProvinsiBoImpl.class);
    private ProvinsiDao provinsiDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ProvinsiBoImpl.logger = logger;
    }

    public ProvinsiDao getProvinsiDao() {
        return provinsiDao;
    }


    public void setProvinsiDao(ProvinsiDao provinsiDao) {
        this.provinsiDao = provinsiDao;
    }

    @Override
    public void saveDelete(Provinsi bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String provinsiId = bean.getProvinsiId();

            ImProvinsiEntity imProvinsiEntity = null;

            try {
                // Get data from database by ID
                imProvinsiEntity = provinsiDao.getById("provinsiId", provinsiId);
            } catch (HibernateException e) {
                logger.error("[ProvinsiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imProvinsiEntity != null) {

                // Modify from bean to entity serializable
                imProvinsiEntity.setProvinsiId(bean.getProvinsiId());
                imProvinsiEntity.setProvinsiName(bean.getProvinsiName());
                imProvinsiEntity.setFlag(bean.getFlag());
                imProvinsiEntity.setAction(bean.getAction());
                imProvinsiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imProvinsiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    provinsiDao.updateAndSave(imProvinsiEntity);
                } catch (HibernateException e) {
                    logger.error("[ProvinsiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Provinsi, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[ProvinsiBoImpl.saveDelete] Error, not found data Provinsi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Provinsi with request id, please check again your data ...");

            }
        }
        logger.info("[ProvinsiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Provinsi bean) throws GeneralBOException {
        logger.info("[ProvinsiBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String provinsiId = bean.getProvinsiId();

            ImProvinsiEntity imProvinsiEntity = null;

            try {
                // Get data from database by ID
                imProvinsiEntity = provinsiDao.getById("provinsiId", provinsiId);
                historyId = provinsiDao.getNextProvinsiHistoryId();
            } catch (HibernateException e) {
                logger.error("[ProvinsiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Provinsi by Kode Provinsi, please inform to your admin...," + e.getMessage());
            }

            if (imProvinsiEntity != null) {


                imProvinsiEntity.setProvinsiId(bean.getProvinsiId());
                imProvinsiEntity.setProvinsiName(bean.getProvinsiName());
                imProvinsiEntity.setFlag(bean.getFlag());
                imProvinsiEntity.setAction(bean.getAction());
                imProvinsiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imProvinsiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    provinsiDao.updateAndSave(imProvinsiEntity);

//                    condition = "Data SuccessFulletcy Updated";
                } catch (HibernateException e) {
                    logger.error("[ProvinsiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Provinsi, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[ProvinsiBoImpl.saveEdit] Error, not found data Provinsi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Provinsi with request id, please check again your data ...");
//                condition = "Error, not found data Provinsi with request id, please check again your data ...";
            }
        }
        logger.info("[ProvinsiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Provinsi saveAdd(Provinsi bean) throws GeneralBOException {
        logger.info("[ProvinsiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String provinsiId;
            try {
                // Generating ID, get from postgre sequence
                provinsiId = provinsiDao.getNextProvinsiId();
            } catch (HibernateException e) {
                logger.error("[ProvinsiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence provinsiId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImProvinsiEntity imProvinsiEntity = new ImProvinsiEntity();

            imProvinsiEntity.setProvinsiId(provinsiId);
            imProvinsiEntity.setProvinsiName(bean.getProvinsiName());
            imProvinsiEntity.setFlag(bean.getFlag());
            imProvinsiEntity.setAction(bean.getAction());
            imProvinsiEntity.setCreatedWho(bean.getCreatedWho());
            imProvinsiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imProvinsiEntity.setCreatedDate(bean.getCreatedDate());
            imProvinsiEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                provinsiDao.addAndSave(imProvinsiEntity);
            } catch (HibernateException e) {
                logger.error("[ProvinsiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Provinsi, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[ProvinsiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Provinsi> getByCriteria(Provinsi searchBean) throws GeneralBOException {
        logger.info("[ProvinsiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Provinsi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getProvinsiId() != null && !"".equalsIgnoreCase(searchBean.getProvinsiId())) {
                hsCriteria.put("provinsi_id", searchBean.getProvinsiId());
            }
            if (searchBean.getProvinsiName() != null && !"".equalsIgnoreCase(searchBean.getProvinsiName())) {
                hsCriteria.put("provinsi_name", searchBean.getProvinsiName());
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ImProvinsiEntity> imProvinsiEntity = null;
            try {

                imProvinsiEntity = provinsiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ProvinsiBoImpl.getSearchProvinsiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imProvinsiEntity != null){
                Provinsi returnProvinsi;
                // Looping from dao to object and save in collection
                for(ImProvinsiEntity provinsiEntity : imProvinsiEntity){
                    returnProvinsi = new Provinsi();
                    returnProvinsi.setProvinsiId(provinsiEntity.getProvinsiId());
                    returnProvinsi.setProvinsiName(provinsiEntity.getProvinsiName());

                    returnProvinsi.setCreatedWho(provinsiEntity.getCreatedWho());
                    returnProvinsi.setCreatedDate(provinsiEntity.getCreatedDate());
                    returnProvinsi.setLastUpdate(provinsiEntity.getLastUpdate());

                    returnProvinsi.setAction(provinsiEntity.getAction());
                    returnProvinsi.setFlag(provinsiEntity.getFlag());
                    listOfResult.add(returnProvinsi);
                }
            }
        }
        logger.info("[ProvinsiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<Provinsi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }



    public List<Provinsi> getComboProvinsiWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Provinsi> listComboProvinsi = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImProvinsiEntity> listProvinsi = null;
        try {
            listProvinsi = provinsiDao.getListProvinsi(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listProvinsi != null) {
            for (ImProvinsiEntity imProvinsiEntity : listProvinsi) {
                Provinsi itemComboProvinsi = new Provinsi();
                itemComboProvinsi.setProvinsiId(imProvinsiEntity.getProvinsiId());
                itemComboProvinsi.setProvinsiName(imProvinsiEntity.getProvinsiName());
                listComboProvinsi.add(itemComboProvinsi);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboProvinsi;
    }

    @Override
    public String getKotaName(String query) throws GeneralBOException {
        logger.info("[provinsiBoImpl.getKotaName] start process >>>");
        String kotaName="";
        String criteria = query ;

        List<ImKotaEntity> imKotaEntityList = new ArrayList<>();
        try {
            imKotaEntityList = provinsiDao.getListKotaById(criteria);
        } catch (HibernateException e) {
            logger.error("[provinsiBoImpl.getKotaName] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving List Kota By Id, please info to your admin..." + e.getMessage());
        }

        if (imKotaEntityList != null) {
            for (ImKotaEntity imKotaEntity : imKotaEntityList){
                kotaName = imKotaEntity.getKotaName();
            }
        }

        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return kotaName;
    }

    public List<Kota> getComboKotaWithCriteria(String query, String prov) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Kota> listComboProvinsi = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImKotaEntity> listProvinsi = null;
        try {
            listProvinsi = provinsiDao.getListKota(criteria, prov);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listProvinsi != null) {
            for (ImKotaEntity imProvinsiEntity : listProvinsi) {
                Kota itemComboProvinsi = new Kota();
                itemComboProvinsi.setProvinsiId(imProvinsiEntity.getProvinsiId());
                itemComboProvinsi.setKotaId(imProvinsiEntity.getKotaId());
                itemComboProvinsi.setKotaName(imProvinsiEntity.getKotaName());
                listComboProvinsi.add(itemComboProvinsi);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboProvinsi;
    }

    public List<Kecamatan> getComboKecamatanWithCriteria(String query, String kota) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Kecamatan> listComboProvinsi = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImKecamatanEntity> listProvinsi = null;
        try {
            listProvinsi = provinsiDao.getListKecamatan(criteria, kota);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listProvinsi != null) {
            for (ImKecamatanEntity imProvinsiEntity : listProvinsi) {
                Kecamatan itemComboProvinsi = new Kecamatan();
                itemComboProvinsi.setKecamatanId(imProvinsiEntity.getKecamatanId());
                itemComboProvinsi.setKotaId(imProvinsiEntity.getKotaId());
                itemComboProvinsi.setKecamatanName(imProvinsiEntity.getKecamatanName());
                listComboProvinsi.add(itemComboProvinsi);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboProvinsi;
    }

    public List<Desa> getComboDesaWithCriteria(String query, String kecamatan) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Desa> listComboProvinsi = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImDesaEntity> listProvinsi = null;
        try {
            listProvinsi = provinsiDao.getListDesa(criteria, kecamatan);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listProvinsi != null) {
            for (ImDesaEntity imProvinsiEntity : listProvinsi) {
                Desa itemComboProvinsi = new Desa();
                itemComboProvinsi.setKecamatanId(imProvinsiEntity.getKecamatanId());
                itemComboProvinsi.setDesaId(imProvinsiEntity.getDesaId());
                itemComboProvinsi.setDesaName(imProvinsiEntity.getDesaName());
                listComboProvinsi.add(itemComboProvinsi);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboProvinsi;
    }

}
