package com.neurix.hris.master.perjalananDinas.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.perjalananDinas.bo.PerjalananDinasBo;
import com.neurix.hris.master.perjalananDinas.dao.PerjalananDinasDao;
import com.neurix.hris.master.perjalananDinas.model.ImPerjalananDinasHistoryEntity;
import com.neurix.hris.master.perjalananDinas.model.PerjalananDinas;
import com.neurix.hris.master.perjalananDinas.model.ImPerjalananDinasEntity;
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
public class PerjalananDinasBoImpl implements PerjalananDinasBo {

    protected static transient Logger logger = Logger.getLogger(PerjalananDinasBoImpl.class);
    private PerjalananDinasDao perjalananDinasDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PerjalananDinasBoImpl.logger = logger;
    }

    public PerjalananDinasDao getPerjalananDinasDao() {
        return perjalananDinasDao;
    }

    public void setPerjalananDinasDao(PerjalananDinasDao perjalananDinasDao) {
        this.perjalananDinasDao = perjalananDinasDao;
    }

    @Override
    public void saveDelete(PerjalananDinas bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String perjalananDinasId = bean.getPerjalananDinasId();

            ImPerjalananDinasEntity imPerjalananDinasEntity = null;

            try {
                // Get data from database by ID
                imPerjalananDinasEntity = perjalananDinasDao.getById("perjalananDinasId", perjalananDinasId);
            } catch (HibernateException e) {
                logger.error("[PerjalananDinasBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPerjalananDinasEntity != null) {

                // Modify from bean to entity serializable
                imPerjalananDinasEntity.setPerjalananDinasId(bean.getPerjalananDinasId());

                imPerjalananDinasEntity.setFlag(bean.getFlag());
                imPerjalananDinasEntity.setAction(bean.getAction());
                imPerjalananDinasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPerjalananDinasEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    perjalananDinasDao.updateAndSave(imPerjalananDinasEntity);
                } catch (HibernateException e) {
                    logger.error("[PerjalananDinasBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PerjalananDinas, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PerjalananDinasBoImpl.saveDelete] Error, not found data PerjalananDinas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PerjalananDinas with request id, please check again your data ...");

            }
        }
        logger.info("[PerjalananDinasBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PerjalananDinas bean) throws GeneralBOException {
        logger.info("[PerjalananDinasBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String perjalananDinasId = bean.getPerjalananDinasId();
            String idHistory = "";
            ImPerjalananDinasEntity imPerjalananDinasEntity = null;
            ImPerjalananDinasHistoryEntity imPerjalananDinasHistoryEntity = new ImPerjalananDinasHistoryEntity();
            try {
                // Get data from database by ID
                imPerjalananDinasEntity = perjalananDinasDao.getById("perjalananDinasId", perjalananDinasId);
                idHistory = perjalananDinasDao.getNextPerjalananDinasHistoryId();
            } catch (HibernateException e) {
                logger.error("[PerjalananDinasBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PerjalananDinas by Kode PerjalananDinas, please inform to your admin...," + e.getMessage());
            }

            if (imPerjalananDinasEntity != null) {
                //
                imPerjalananDinasHistoryEntity.setId(idHistory);
                imPerjalananDinasHistoryEntity.setPerjalananDinasId(imPerjalananDinasEntity.getPerjalananDinasId());
                imPerjalananDinasHistoryEntity.setKelompokId(imPerjalananDinasEntity.getKelompokId());
                imPerjalananDinasHistoryEntity.setBiayaDinasId(imPerjalananDinasEntity.getBiayaDinasId());
                imPerjalananDinasHistoryEntity.setJumlahBiaya(imPerjalananDinasEntity.getJumlahBiaya());
                imPerjalananDinasHistoryEntity.setDinas(imPerjalananDinasEntity.getDinas());
                imPerjalananDinasHistoryEntity.setGolonganId(imPerjalananDinasEntity.getGolonganId());
                imPerjalananDinasHistoryEntity.setFlag(imPerjalananDinasEntity.getFlag());
                imPerjalananDinasHistoryEntity.setAction(imPerjalananDinasEntity.getAction());
                imPerjalananDinasHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPerjalananDinasHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imPerjalananDinasHistoryEntity.setCreatedWho(imPerjalananDinasEntity.getLastUpdateWho());
                imPerjalananDinasHistoryEntity.setCreatedDate(imPerjalananDinasEntity.getLastUpdate());

                imPerjalananDinasEntity.setPerjalananDinasId(bean.getPerjalananDinasId());
                imPerjalananDinasEntity.setKelompokId(bean.getKelompokId());
                imPerjalananDinasEntity.setBiayaDinasId(bean.getBiayaDinasId());
                imPerjalananDinasEntity.setJumlahBiaya(bean.getJumlahBiaya());
                imPerjalananDinasEntity.setDinas(bean.getDinas());
                imPerjalananDinasEntity.setGolonganId(bean.getGolonganId());
                imPerjalananDinasEntity.setFlag(bean.getFlag());
                imPerjalananDinasEntity.setAction(bean.getAction());
                imPerjalananDinasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPerjalananDinasEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    perjalananDinasDao.updateAndSave(imPerjalananDinasEntity);
                    perjalananDinasDao.addAndSaveHistory(imPerjalananDinasHistoryEntity);

//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PerjalananDinasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PerjalananDinas, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PerjalananDinasBoImpl.saveEdit] Error, not found data PerjalananDinas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PerjalananDinas with request id, please check again your data ...");
//                condition = "Error, not found data PerjalananDinas with request id, please check again your data ...";
            }
        }
        logger.info("[PerjalananDinasBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PerjalananDinas saveAdd(PerjalananDinas bean) throws GeneralBOException {
        logger.info("[PerjalananDinasBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String perjalananDinasId;
            try {
                // Generating ID, get from postgre sequence
                perjalananDinasId = perjalananDinasDao.getNextPerjalananDinasId();
            } catch (HibernateException e) {
                logger.error("[PerjalananDinasBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence perjalananDinasId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPerjalananDinasEntity imPerjalananDinasEntity = new ImPerjalananDinasEntity();

            imPerjalananDinasEntity.setPerjalananDinasId(perjalananDinasId);
            imPerjalananDinasEntity.setKelompokId(bean.getKelompokId());
            imPerjalananDinasEntity.setBiayaDinasId(bean.getBiayaDinasId());
            imPerjalananDinasEntity.setJumlahBiaya(bean.getJumlahBiaya());
            imPerjalananDinasEntity.setDinas(bean.getDinas());
            imPerjalananDinasEntity.setGolonganId(bean.getGolonganId());


            imPerjalananDinasEntity.setFlag(bean.getFlag());
            imPerjalananDinasEntity.setAction(bean.getAction());
            imPerjalananDinasEntity.setCreatedWho(bean.getCreatedWho());
            imPerjalananDinasEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPerjalananDinasEntity.setCreatedDate(bean.getCreatedDate());
            imPerjalananDinasEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                perjalananDinasDao.addAndSave(imPerjalananDinasEntity);
            } catch (HibernateException e) {
                logger.error("[PerjalananDinasBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PerjalananDinas, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PerjalananDinasBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PerjalananDinas> getByCriteria(PerjalananDinas searchBean) throws GeneralBOException {
        logger.info("[PerjalananDinasBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PerjalananDinas> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPerjalananDinasId() != null && !"".equalsIgnoreCase(searchBean.getPerjalananDinasId())) {
                hsCriteria.put("perjalanan_dinas_id", searchBean.getPerjalananDinasId());
            }

            if (searchBean.getKelompokId() != null && !"".equalsIgnoreCase(searchBean.getKelompokId()) ) {
                hsCriteria.put("position_id", searchBean.getKelompokId());
            }

            if (searchBean.getBiayaDinasId() != null && !"".equalsIgnoreCase(searchBean.getBiayaDinasId())) {
                hsCriteria.put("biaya_dinas_id", searchBean.getBiayaDinasId());
            }

            if (searchBean.getDinas() != null && !"".equalsIgnoreCase(searchBean.getDinas())) {
                hsCriteria.put("dinas", searchBean.getDinas());
            }

            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
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


            List<ImPerjalananDinasEntity> imPerjalananDinasEntity = null;
            try {

                imPerjalananDinasEntity = perjalananDinasDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PerjalananDinasBoImpl.getSearchPerjalananDinasByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPerjalananDinasEntity != null){
                PerjalananDinas returnPerjalananDinas;
                // Looping from dao to object and save in collection
                for(ImPerjalananDinasEntity perjalananDinasEntity : imPerjalananDinasEntity){
                    returnPerjalananDinas = new PerjalananDinas();

                    returnPerjalananDinas.setPerjalananDinasId(perjalananDinasEntity.getPerjalananDinasId());
                    returnPerjalananDinas.setKelompokId(perjalananDinasEntity.getKelompokId());
                    if(perjalananDinasEntity.getKelompokId() != null && !"".equalsIgnoreCase(perjalananDinasEntity.getKelompokId())){
                        returnPerjalananDinas.setKelompokName(perjalananDinasEntity.getImKelompokPositionEntity().getKelompokName());
                    }
                    returnPerjalananDinas.setBiayaDinasId(perjalananDinasEntity.getBiayaDinasId());
                    returnPerjalananDinas.setJumlahBiaya(perjalananDinasEntity.getJumlahBiaya());
                    returnPerjalananDinas.setDinas(perjalananDinasEntity.getDinas());
                    returnPerjalananDinas.setGolonganId(perjalananDinasEntity.getGolonganId());
                    if(perjalananDinasEntity.getBiayaDinasId() != null && !"".equalsIgnoreCase(perjalananDinasEntity.getBiayaDinasId())){
                        returnPerjalananDinas.setBiayaDinasName(perjalananDinasEntity.getImBiayaPerjalananDinasEntity().getBiayaName());
                    }else{
                        returnPerjalananDinas.setBiayaDinasName("");
                    }
                    if(perjalananDinasEntity.getGolonganId() != null && !"".equalsIgnoreCase(perjalananDinasEntity.getGolonganId())){
                        returnPerjalananDinas.setGolonganName(perjalananDinasEntity.getImGolonganEntity().getGolonganName());
                    }else{
                        returnPerjalananDinas.setGolonganName("");
                    }

                    returnPerjalananDinas.setCreatedWho(perjalananDinasEntity.getCreatedWho());
                    returnPerjalananDinas.setCreatedDate(perjalananDinasEntity.getCreatedDate());
                    returnPerjalananDinas.setLastUpdate(perjalananDinasEntity.getLastUpdate());

                    returnPerjalananDinas.setAction(perjalananDinasEntity.getAction());
                    returnPerjalananDinas.setFlag(perjalananDinasEntity.getFlag());
                    listOfResult.add(returnPerjalananDinas);
                }
            }
        }
        logger.info("[PerjalananDinasBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PerjalananDinas> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<PerjalananDinas> getComboPerjalananDinasWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<PerjalananDinas> listComboPerjalananDinas = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImPerjalananDinasEntity> listPerjalananDinas = null;
        try {
            listPerjalananDinas = perjalananDinasDao.getListPerjalananDinas(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPerjalananDinas != null) {
            for (ImPerjalananDinasEntity imPerjalananDinasEntity : listPerjalananDinas) {
                PerjalananDinas itemComboPerjalananDinas = new PerjalananDinas();
                itemComboPerjalananDinas.setPerjalananDinasId(imPerjalananDinasEntity.getPerjalananDinasId());
                itemComboPerjalananDinas.setKelompokId(imPerjalananDinasEntity.getKelompokId());
                itemComboPerjalananDinas.setBiayaDinasId(imPerjalananDinasEntity.getBiayaDinasId());
                itemComboPerjalananDinas.setJumlahBiaya(imPerjalananDinasEntity.getJumlahBiaya());
                itemComboPerjalananDinas.setDinas(imPerjalananDinasEntity.getDinas());
                itemComboPerjalananDinas.setGolonganId(imPerjalananDinasEntity.getGolonganId());


                listComboPerjalananDinas.add(itemComboPerjalananDinas);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboPerjalananDinas;
    }
}