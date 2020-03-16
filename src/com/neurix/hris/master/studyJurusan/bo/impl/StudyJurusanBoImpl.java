package com.neurix.hris.master.studyJurusan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.studyJurusan.bo.StudyJurusanBo;
import com.neurix.hris.master.studyJurusan.dao.StudyJurusanDao;
import com.neurix.hris.master.studyJurusan.model.ImStudyJurusanEntity;
import com.neurix.hris.master.studyJurusan.model.StudyJurusan;
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
public class StudyJurusanBoImpl implements StudyJurusanBo {

    protected static transient Logger logger = Logger.getLogger(StudyJurusanBoImpl.class);
    private StudyJurusanDao studyJurusanDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StudyJurusanBoImpl.logger = logger;
    }

    public StudyJurusanDao getStudyJurusanDao() {
        return studyJurusanDao;
    }

    public void setStudyJurusanDao(StudyJurusanDao studyJurusanDao) {
        this.studyJurusanDao = studyJurusanDao;
    }

    @Override
    public void saveDelete(StudyJurusan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String studyJurusanId = bean.getJurusanId();

            ImStudyJurusanEntity imStudyJurusanEntity = null;

            try {
                // Get data from database by ID
                imStudyJurusanEntity = studyJurusanDao.getById("jurusanId", studyJurusanId);
            } catch (HibernateException e) {
                logger.error("[StudyJurusanBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imStudyJurusanEntity != null) {

                // Modify from bean to entity serializable
                imStudyJurusanEntity.setJurusanId(bean.getJurusanId());
                imStudyJurusanEntity.setJurusanName(bean.getJurusanName());
                imStudyJurusanEntity.setFlag(bean.getFlag());
                imStudyJurusanEntity.setAction(bean.getAction());
                imStudyJurusanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStudyJurusanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    studyJurusanDao.updateAndSave(imStudyJurusanEntity);
                } catch (HibernateException e) {
                    logger.error("[StudyJurusanBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data StudyJurusan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[StudyJurusanBoImpl.saveDelete] Error, not found data StudyJurusan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data StudyJurusan with request id, please check again your data ...");

            }
        }
        logger.info("[StudyJurusanBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(StudyJurusan bean) throws GeneralBOException {
        logger.info("[StudyJurusanBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String studyJurusanId = bean.getJurusanId();
            String idHistory = "";
            ImStudyJurusanEntity imStudyJurusanEntity = null;
            try {
                // Get data from database by ID
                imStudyJurusanEntity = studyJurusanDao.getById("jurusanId", studyJurusanId);
            } catch (HibernateException e) {
                logger.error("[StudyJurusanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data StudyJurusan by Kode StudyJurusan, please inform to your admin...," + e.getMessage());
            }

            if (imStudyJurusanEntity != null) {

                imStudyJurusanEntity.setJurusanId(bean.getJurusanId());
                imStudyJurusanEntity.setJurusanName(bean.getJurusanName());
                imStudyJurusanEntity.setFlag(bean.getFlag());
                imStudyJurusanEntity.setAction(bean.getAction());
                imStudyJurusanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStudyJurusanEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    studyJurusanDao.updateAndSave(imStudyJurusanEntity);
                } catch (HibernateException e) {
                    logger.error("[StudyJurusanBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data StudyJurusan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[StudyJurusanBoImpl.saveEdit] Error, not found data StudyJurusan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data StudyJurusan with request id, please check again your data ...");
//                condition = "Error, not found data StudyJurusan with request id, please check again your data ...";
            }
        }
        logger.info("[StudyJurusanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public StudyJurusan saveAdd(StudyJurusan bean) throws GeneralBOException {
        logger.info("[StudyJurusanBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getJurusanName());
            if (!status.equalsIgnoreCase("Exist")){
                String studyJurusanId;
                try {
                    // Generating ID, get from postgre sequence
                    studyJurusanId = studyJurusanDao.getNextStudyJurusanId();
                } catch (HibernateException e) {
                    logger.error("[StudyJurusanBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence studyJurusanId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImStudyJurusanEntity imStudyJurusanEntity = new ImStudyJurusanEntity();

                imStudyJurusanEntity.setJurusanId(studyJurusanId);
                imStudyJurusanEntity.setJurusanName(bean.getJurusanName());
                imStudyJurusanEntity.setFlag(bean.getFlag());
                imStudyJurusanEntity.setAction(bean.getAction());
                imStudyJurusanEntity.setCreatedWho(bean.getCreatedWho());
                imStudyJurusanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStudyJurusanEntity.setCreatedDate(bean.getCreatedDate());
                imStudyJurusanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    studyJurusanDao.addAndSave(imStudyJurusanEntity);
                } catch (HibernateException e) {
                    logger.error("[StudyJurusanBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data StudyJurusan, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data Tersebut Sudah Ada");
            }

        }

        logger.info("[StudyJurusanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<StudyJurusan> getByCriteria(StudyJurusan searchBean) throws GeneralBOException {
        logger.info("[StudyJurusanBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<StudyJurusan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getJurusanId() != null && !"".equalsIgnoreCase(searchBean.getJurusanId())) {
                hsCriteria.put("jurusan_id", searchBean.getJurusanId());
            }
            if (searchBean.getJurusanName() != null && !"".equalsIgnoreCase(searchBean.getJurusanName())) {
                hsCriteria.put("jurusan_name", searchBean.getJurusanName());
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


            List<ImStudyJurusanEntity> imStudyJurusanEntity = null;
            try {

                imStudyJurusanEntity = studyJurusanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[StudyJurusanBoImpl.getSearchStudyJurusanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imStudyJurusanEntity != null){
                StudyJurusan returnStudyJurusan;
                // Looping from dao to object and save in collection
                for(ImStudyJurusanEntity studyJurusanEntity : imStudyJurusanEntity){
                    returnStudyJurusan = new StudyJurusan();
                    returnStudyJurusan.setJurusanId(studyJurusanEntity.getJurusanId());
                    returnStudyJurusan.setJurusanName(studyJurusanEntity.getJurusanName());

                    returnStudyJurusan.setCreatedWho(studyJurusanEntity.getCreatedWho());
                    returnStudyJurusan.setCreatedDate(studyJurusanEntity.getCreatedDate());
                    returnStudyJurusan.setLastUpdate(studyJurusanEntity.getLastUpdate());

                    returnStudyJurusan.setAction(studyJurusanEntity.getAction());
                    returnStudyJurusan.setFlag(studyJurusanEntity.getFlag());
                    listOfResult.add(returnStudyJurusan);
                }
            }
        }
        logger.info("[StudyJurusanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<StudyJurusan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<StudyJurusan> getComboJurusanWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<StudyJurusan> listComboStudyJurusan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImStudyJurusanEntity> listStudyJurusan = null;
        try {
            listStudyJurusan = studyJurusanDao.getListStudyJurusan(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listStudyJurusan != null) {
            for (ImStudyJurusanEntity imStudyJurusanEntity : listStudyJurusan) {
                StudyJurusan itemComboStudyJurusan = new StudyJurusan();
                itemComboStudyJurusan.setJurusanId(imStudyJurusanEntity.getJurusanId());
                itemComboStudyJurusan.setJurusanName(imStudyJurusanEntity.getJurusanName());
                listComboStudyJurusan.add(itemComboStudyJurusan);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboStudyJurusan;
    }
    public String cekStatus(String golonganId)throws GeneralBOException{
        String status ="";
        List<ImStudyJurusanEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = studyJurusanDao.getListStudyJurusan(golonganId);
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
