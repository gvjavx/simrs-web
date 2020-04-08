package com.neurix.hris.master.study.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.study.bo.StudyBo;
import com.neurix.hris.master.study.dao.StudyDao;
import com.neurix.hris.master.study.model.Study;
import com.neurix.hris.master.study.model.ImStudyEntity;
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
public class StudyBoImpl implements StudyBo {

    protected static transient Logger logger = Logger.getLogger(StudyBoImpl.class);
    private StudyDao studyDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StudyBoImpl.logger = logger;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }


    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    @Override
    public void saveDelete(Study bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String studyId = bean.getStudyId();

            ImStudyEntity imStudyEntity = null;

            try {
                // Get data from database by ID
                imStudyEntity = studyDao.getById("studyId", studyId);
            } catch (HibernateException e) {
                logger.error("[StudyBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imStudyEntity != null) {

                // Modify from bean to entity serializable
                imStudyEntity.setStudyId(bean.getStudyId());
                imStudyEntity.setStudyName(bean.getStudyName());
                imStudyEntity.setFlag(bean.getFlag());
                imStudyEntity.setAction(bean.getAction());
                imStudyEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStudyEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    studyDao.updateAndSave(imStudyEntity);
                } catch (HibernateException e) {
                    logger.error("[StudyBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Study, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[StudyBoImpl.saveDelete] Error, not found data Study with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Study with request id, please check again your data ...");

            }
        }
        logger.info("[StudyBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Study bean) throws GeneralBOException {
        logger.info("[StudyBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String studyId = bean.getStudyId();

            ImStudyEntity imStudyEntity = null;
            try {
                // Get data from database by ID
                imStudyEntity = studyDao.getById("studyId", studyId);
            } catch (HibernateException e) {
                logger.error("[StudyBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Study by Kode Study, please inform to your admin...," + e.getMessage());
            }

            if (imStudyEntity != null) {
                imStudyEntity.setStudyId(bean.getStudyId());
                imStudyEntity.setStudyName(bean.getStudyName());
                imStudyEntity.setTypeStudy(bean.getTypeStudy());
                imStudyEntity.setTahunAwal(bean.getTahunAwal());
                imStudyEntity.setTahunAkhir(bean.getTahunAkhir());
                imStudyEntity.setProgramStudy(bean.getProgramStudy());
                imStudyEntity.setStudyJurusanId(bean.getFakultasId());
                imStudyEntity.setFlag(bean.getFlag());
                imStudyEntity.setAction(bean.getAction());
                imStudyEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStudyEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    studyDao.updateAndSave(imStudyEntity);

                } catch (HibernateException e) {
                    logger.error("[StudyBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Study, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[StudyBoImpl.saveEdit] Error, not found data Study with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Study with request id, please check again your data ...");
//                condition = "Error, not found data Study with request id, please check again your data ...";
            }
        }
        logger.info("[StudyBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Study saveAdd(Study bean) throws GeneralBOException {
        logger.info("[StudyBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String studyId;
            try {
                // Generating ID, get from postgre sequence
                studyId = studyDao.getNextStudyId();
            } catch (HibernateException e) {
                logger.error("[StudyBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence studyId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImStudyEntity imStudyEntity = new ImStudyEntity();

            imStudyEntity.setStudyId(studyId);
            imStudyEntity.setStudyName(bean.getStudyName());
            imStudyEntity.setNip(bean.getNip());
            imStudyEntity.setTypeStudy(bean.getTypeStudy());
            imStudyEntity.setTahunAwal(bean.getTahunAwal());
            imStudyEntity.setTahunAkhir(bean.getTahunAkhir());
            imStudyEntity.setProgramStudy(bean.getProgramStudy());
            imStudyEntity.setStudyJurusanId(bean.getFakultasId());

            imStudyEntity.setFlag(bean.getFlag());
            imStudyEntity.setAction(bean.getAction());
            imStudyEntity.setCreatedWho(bean.getCreatedWho());
            imStudyEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imStudyEntity.setCreatedDate(bean.getCreatedDate());
            imStudyEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                studyDao.addAndSave(imStudyEntity);
            } catch (HibernateException e) {
                logger.error("[StudyBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Study, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[StudyBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Study> getByCriteria(Study searchBean) throws GeneralBOException {
        logger.info("[StudyBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Study> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getStudyId() != null && !"".equalsIgnoreCase(searchBean.getStudyId())) {
                hsCriteria.put("study_id", searchBean.getStudyId());
            }
            if (searchBean.getStudyName() != null && !"".equalsIgnoreCase(searchBean.getStudyName())) {
                hsCriteria.put("study_name", searchBean.getStudyName());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
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


            List<ImStudyEntity> imStudyEntity = null;
            try {
                imStudyEntity = studyDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[StudyBoImpl.getSearchStudyByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(!"".equalsIgnoreCase(searchBean.getNip())){
                if(imStudyEntity != null){
                    Study returnStudy;
                    // Looping from dao to object and save in collection
                    for(ImStudyEntity studyEntity : imStudyEntity){
                        returnStudy = new Study();
                        returnStudy.setStudyId(studyEntity.getStudyId());
                        returnStudy.setStudyName(studyEntity.getStudyName());
                        returnStudy.setNip(studyEntity.getNip());
                        returnStudy.setTahunAwal(studyEntity.getTahunAwal());

                        if(studyEntity.getProgramStudy() != null && !studyEntity.getProgramStudy().equalsIgnoreCase("")){
                            returnStudy.setProgramStudy(studyEntity.getProgramStudy());
                        }else{
                            returnStudy.setProgramStudy("-");
                        }

                        if(studyEntity.getTahunAwal() != null){
                            returnStudy.setTahunAwal(studyEntity.getTahunAwal());
                        }else{
                            returnStudy.setTahunAwal("");
                        }

                        if(studyEntity.getTahunAkhir() != null){
                            returnStudy.setTahunAkhir(studyEntity.getTahunAkhir());
                        }else{
                            studyEntity.setTahunAkhir("");
                        }

                        if(studyEntity.getTypeStudy() != null){
                            returnStudy.setTypeStudy(studyEntity.getTypeStudy());
                        }else{
                            returnStudy.setTypeStudy("");
                        }

                        if(studyEntity.getStudyJurusanId() != null){
                            returnStudy.setFakultasId(studyEntity.getStudyJurusanId());
                            returnStudy.setFakultasName(studyEntity.getImStudyJurusanEntity().getJurusanName());
                        }else{
                            returnStudy.setFakultasName("-");
                            returnStudy.setFakultasId("");
                        }

                        returnStudy.setCreatedWho(studyEntity.getCreatedWho());
                        returnStudy.setCreatedDate(studyEntity.getCreatedDate());
                        returnStudy.setLastUpdate(studyEntity.getLastUpdate());

                        returnStudy.setAction(studyEntity.getAction());
                        returnStudy.setFlag(studyEntity.getFlag());
                        listOfResult.add(returnStudy);
                    }
                }
            }
        }
        logger.info("[StudyBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Study> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Study> getComboStudyWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Study> listComboStudy = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImStudyEntity> listStudy = null;
        try {
            listStudy = studyDao.getListStudy(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listStudy != null) {
            for (ImStudyEntity imStudyEntity : listStudy) {
                Study itemComboStudy = new Study();
                itemComboStudy.setStudyId(imStudyEntity.getStudyId());
                itemComboStudy.setStudyName(imStudyEntity.getStudyName());
                listComboStudy.add(itemComboStudy);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboStudy;
    }

    @Override
    public String getNextStudyId() throws GeneralBOException {
        String studyId;
        try{
            studyId = studyDao.getNextStudyId();
        }catch (HibernateException e){
            logger.error("[StudyBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence studyId, please info to your admin..." + e.getMessage());
        }
        return studyId;
    }
}
