package com.neurix.hris.master.golonganPkwt.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.golonganPkwt.bo.GolonganPkwtBo;
import com.neurix.hris.master.golonganPkwt.dao.GolonganPkwtDao;
import com.neurix.hris.master.golonganPkwt.model.GolonganPkwt;
import com.neurix.hris.master.golonganPkwt.model.ImGolonganPkwtEntity;
import com.neurix.hris.master.golonganPkwt.model.ImGolonganPkwtHistoryEntity;
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
public class GolonganPkwtBoImpl implements GolonganPkwtBo {

    protected static transient Logger logger = Logger.getLogger(GolonganPkwtBoImpl.class);
    private GolonganPkwtDao golonganPkwtDao;
    private BiodataDao biodataDao;

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GolonganPkwtBoImpl.logger = logger;
    }

    public GolonganPkwtDao getGolonganPkwtDao() {
        return golonganPkwtDao;
    }

    public void setGolonganPkwtDao(GolonganPkwtDao golonganPkwtDao) {
        this.golonganPkwtDao = golonganPkwtDao;
    }

    @Override
    public void saveDelete(GolonganPkwt bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String golonganPkwtId = bean.getGolonganPkwtId();
            String golonganIdHistory = "";
            ImGolonganPkwtEntity imGolonganPkwtEntity = null;
            ImGolonganPkwtHistoryEntity historyEntity = new ImGolonganPkwtHistoryEntity();

            List<ImBiodataEntity> biodataEntityList = biodataDao.getBiodataByGolonganId(golonganPkwtId);

            if (biodataEntityList.size()>0){
                String status = "ERROR : data tidak bisa dihapus dikarenakan sudah digunakan di transaksi";
                logger.error(status);
                throw new GeneralBOException(status);
            }

            try {
                // Get data from database by ID
                imGolonganPkwtEntity = golonganPkwtDao.getById("golonganPkwtId", golonganPkwtId);
                golonganIdHistory = golonganPkwtDao.getNextGolonganPkwtHistoryId();
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imGolonganPkwtEntity != null) {
                historyEntity.setGolonganPkwtHistoryId(golonganIdHistory);
                historyEntity.setGolonganPkwtId(imGolonganPkwtEntity.getGolonganPkwtId());
                historyEntity.setGolonganPkwtName(imGolonganPkwtEntity.getGolonganPkwtName());
                historyEntity.setFlag(imGolonganPkwtEntity.getFlag());
                historyEntity.setAction(imGolonganPkwtEntity.getAction());
                historyEntity.setCreatedDate(imGolonganPkwtEntity.getCreatedDate());
                historyEntity.setCreatedWho(imGolonganPkwtEntity.getCreatedWho());
                historyEntity.setLastUpdate(imGolonganPkwtEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imGolonganPkwtEntity.getLastUpdateWho());

                // Modify from bean to entity serializable
                imGolonganPkwtEntity.setGolonganPkwtId(bean.getGolonganPkwtId());
                imGolonganPkwtEntity.setGolonganPkwtName(bean.getGolonganPkwtName());
                imGolonganPkwtEntity.setFlag(bean.getFlag());
                imGolonganPkwtEntity.setAction(bean.getAction());
                imGolonganPkwtEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganPkwtEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    golonganPkwtDao.updateAndSave(imGolonganPkwtEntity);
                    golonganPkwtDao.addAndSaveHistory(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[GolonganPkwtBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data GolonganPkwt, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[GolonganPkwtBoImpl.saveDelete] Error, not found data GolonganPkwt with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data GolonganPkwt with request id, please check again your data ...");

            }
        }
        logger.info("[GolonganPkwtBoImpl.saveDelete] end process <<<");
    }

    @Override
    public List<GolonganPkwt> getComboGolonganPkwtWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<GolonganPkwt> listComboGolongan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImGolonganPkwtEntity> listGolongan = null;
        try {
            listGolongan = golonganPkwtDao.getListGolongan(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listGolongan != null) {
            for (ImGolonganPkwtEntity imGolonganEntity : listGolongan) {
                GolonganPkwt itemComboGolongan = new GolonganPkwt();
                itemComboGolongan.setGolonganPkwtId(imGolonganEntity.getGolonganPkwtId());
                itemComboGolongan.setGolonganPkwtName(imGolonganEntity.getGolonganPkwtName());
                listComboGolongan.add(itemComboGolongan);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboGolongan;
    }

    @Override
    public void saveEdit(GolonganPkwt bean) throws GeneralBOException {
        logger.info("[GolonganPkwtBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String status = cekStatus(bean.getGolonganPkwtName());
            if (!status.equalsIgnoreCase("exist")){
                String golonganPkwtId = bean.getGolonganPkwtId();
                String historyId = "";

                ImGolonganPkwtEntity imGolonganPkwtEntity = null;
                ImGolonganPkwtHistoryEntity historyEntity = new ImGolonganPkwtHistoryEntity();
                try {
                    // Get data from database by ID
                    imGolonganPkwtEntity = golonganPkwtDao.getById("golonganPkwtId", golonganPkwtId);
                    historyId = golonganPkwtDao.getNextGolonganPkwtHistoryId();
                } catch (HibernateException e) {
                    logger.error("[GolonganPkwtBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data GolonganPkwt by Kode GolonganPkwt, please inform to your admin...," + e.getMessage());
                }

                if (imGolonganPkwtEntity != null) {
                    historyEntity.setGolonganPkwtHistoryId(historyId);
                    historyEntity.setGolonganPkwtId(imGolonganPkwtEntity.getGolonganPkwtId());
                    historyEntity.setGolonganPkwtName(imGolonganPkwtEntity.getGolonganPkwtName());
                    historyEntity.setFlag(imGolonganPkwtEntity.getFlag());
                    historyEntity.setAction(imGolonganPkwtEntity.getAction());
                    historyEntity.setCreatedDate(imGolonganPkwtEntity.getCreatedDate());
                    historyEntity.setCreatedWho(imGolonganPkwtEntity.getCreatedWho());
                    historyEntity.setLastUpdate(imGolonganPkwtEntity.getLastUpdate());
                    historyEntity.setLastUpdateWho(imGolonganPkwtEntity.getLastUpdateWho());

                    //
                    imGolonganPkwtEntity.setGolonganPkwtId(bean.getGolonganPkwtId());
                    imGolonganPkwtEntity.setGolonganPkwtName(bean.getGolonganPkwtName());
                    imGolonganPkwtEntity.setFlag(bean.getFlag());
                    imGolonganPkwtEntity.setAction(bean.getAction());
                    imGolonganPkwtEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imGolonganPkwtEntity.setLastUpdate(bean.getLastUpdate());

                    String flag;
                    try {
                        // Update into database
                        golonganPkwtDao.updateAndSave(imGolonganPkwtEntity);
                        golonganPkwtDao.addAndSaveHistory(historyEntity);
//                    condition = "Data SuccessFully Updated";
                    } catch (HibernateException e) {
                        logger.error("[GolonganPkwtBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data GolonganPkwt, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[GolonganPkwtBoImpl.saveEdit] Error, not found data GolonganPkwt with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data GolonganPkwt with request id, please check again your data ...");
//                condition = "Error, not found data GolonganPkwt with request id, please check again your data ...";
                }
            }else {
                throw new GeneralBOException("Maaf Data Tersebut Sudah Ada");
            }
        }
        logger.info("[GolonganPkwtBoImpl.saveEdit] end process <<<");
    }

    @Override
    public GolonganPkwt saveAdd(GolonganPkwt bean) throws GeneralBOException {
        logger.info("[GolonganPkwtBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getGolonganPkwtName());
            if (!status.equalsIgnoreCase("Exist")){
                String golonganPkwtId;
                try {
                    // Generating ID, get from postgre sequence
                    golonganPkwtId = golonganPkwtDao.getNextGolonganPkwtId();
                } catch (HibernateException e) {
                    logger.error("[GolonganPkwtBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence golonganPkwtId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImGolonganPkwtEntity imGolonganPkwtEntity = new ImGolonganPkwtEntity();

                imGolonganPkwtEntity.setGolonganPkwtId(golonganPkwtId);
                imGolonganPkwtEntity.setGolonganPkwtName(bean.getGolonganPkwtName());
                imGolonganPkwtEntity.setFlag(bean.getFlag());
                imGolonganPkwtEntity.setAction(bean.getAction());
                imGolonganPkwtEntity.setCreatedWho(bean.getCreatedWho());
                imGolonganPkwtEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganPkwtEntity.setCreatedDate(bean.getCreatedDate());
                imGolonganPkwtEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    golonganPkwtDao.addAndSave(imGolonganPkwtEntity);
                } catch (HibernateException e) {
                    logger.error("[GolonganPkwtBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data GolonganPkwt, please info to your admin..." + e.getMessage());
                }
            }
            else{
                throw new GeneralBOException("Maaf Data Tersebut Sudah Ada");
            }
        }

        logger.info("[GolonganPkwtBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<GolonganPkwt> getByCriteria(GolonganPkwt searchBean) throws GeneralBOException {
        logger.info("[GolonganPkwtBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<GolonganPkwt> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getGolonganPkwtId() != null && !"".equalsIgnoreCase(searchBean.getGolonganPkwtId())) {
                hsCriteria.put("golongan_Pkwt_id", searchBean.getGolonganPkwtId());
            }
            if (searchBean.getGolonganPkwtName() != null && !"".equalsIgnoreCase(searchBean.getGolonganPkwtName())) {
                hsCriteria.put("golongan_Pkwt_name", searchBean.getGolonganPkwtName());
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


            List<ImGolonganPkwtEntity> imGolonganPkwtEntity = null;
            try {

                imGolonganPkwtEntity = golonganPkwtDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[GolonganPkwtBoImpl.getSearchGolonganPkwtByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imGolonganPkwtEntity != null){
                GolonganPkwt returnGolonganPkwt;
                // Looping from dao to object and save in collection
                for(ImGolonganPkwtEntity golonganPkwtEntity : imGolonganPkwtEntity){
                    returnGolonganPkwt = new GolonganPkwt();
                    returnGolonganPkwt.setGolonganPkwtId(golonganPkwtEntity.getGolonganPkwtId());
                    returnGolonganPkwt.setGolonganPkwtName(golonganPkwtEntity.getGolonganPkwtName());

                    returnGolonganPkwt.setCreatedWho(golonganPkwtEntity.getCreatedWho());
                    returnGolonganPkwt.setCreatedDate(golonganPkwtEntity.getCreatedDate());
                    returnGolonganPkwt.setLastUpdate(golonganPkwtEntity.getLastUpdate());
                    returnGolonganPkwt.setLastUpdateWho(golonganPkwtEntity.getLastUpdateWho());

                    returnGolonganPkwt.setAction(golonganPkwtEntity.getAction());
                    returnGolonganPkwt.setFlag(golonganPkwtEntity.getFlag());
                    listOfResult.add(returnGolonganPkwt);
                }
            }
        }
        logger.info("[GolonganPkwtBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<GolonganPkwt> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    public String cekStatus(String golonganName)throws GeneralBOException{
        String status ="";
        List<ImGolonganPkwtEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = golonganPkwtDao.getListGolongan(golonganName);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (skalaGajiEntity.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

}
