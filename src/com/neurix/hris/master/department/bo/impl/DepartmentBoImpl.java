package com.neurix.hris.master.department.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.department.bo.DepartmentBo;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.Department;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.department.model.ImDepartmentHistoryEntity;
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
public class DepartmentBoImpl implements DepartmentBo {

    protected static transient Logger logger = Logger.getLogger(DepartmentBoImpl.class);
    private DepartmentDao departmentDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DepartmentBoImpl.logger = logger;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }


    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public void saveDelete(Department bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String departmentId = bean.getDepartmentId();

            ImDepartmentEntity imDepartmentEntity = null;

            try {
                // Get data from database by ID
                imDepartmentEntity = departmentDao.getById("departmentId", departmentId);
            } catch (HibernateException e) {
                logger.error("[DepartmentBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imDepartmentEntity != null) {

                // Modify from bean to entity serializable
                imDepartmentEntity.setDepartmentId(bean.getDepartmentId());
                imDepartmentEntity.setDepartmentName(bean.getDepartmentName());
                imDepartmentEntity.setFlag(bean.getFlag());
                imDepartmentEntity.setAction(bean.getAction());
                imDepartmentEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imDepartmentEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    departmentDao.updateAndSave(imDepartmentEntity);
                } catch (HibernateException e) {
                    logger.error("[DepartmentBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Department, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[DepartmentBoImpl.saveDelete] Error, not found data Department with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Department with request id, please check again your data ...");

            }
        }
        logger.info("[DepartmentBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Department bean) throws GeneralBOException {
        logger.info("[DepartmentBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String departmentId = bean.getDepartmentId();

            ImDepartmentEntity imDepartmentEntity = null;
            ImDepartmentHistoryEntity imDepartmentHistoryEntity = new ImDepartmentHistoryEntity();
            try {
                // Get data from database by ID
                imDepartmentEntity = departmentDao.getById("departmentId", departmentId);
                historyId = departmentDao.getNextDepartmentHistoryId();
            } catch (HibernateException e) {
                logger.error("[DepartmentBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Department by Kode Department, please inform to your admin...," + e.getMessage());
            }

            if (imDepartmentEntity != null) {
                imDepartmentHistoryEntity.setId(historyId);
                imDepartmentHistoryEntity.setDepartmentId(imDepartmentEntity.getDepartmentId());
                imDepartmentHistoryEntity.setDepartmentName(imDepartmentEntity.getDepartmentName());
                imDepartmentHistoryEntity.setFlag(imDepartmentEntity.getFlag());
                imDepartmentHistoryEntity.setAction(imDepartmentEntity.getAction());
                imDepartmentHistoryEntity.setLastUpdateWho(imDepartmentEntity.getLastUpdateWho());
                imDepartmentHistoryEntity.setLastUpdate(imDepartmentEntity.getLastUpdate());
                imDepartmentHistoryEntity.setCreatedWho(imDepartmentEntity.getLastUpdateWho());
                imDepartmentHistoryEntity.setCreatedDate(imDepartmentEntity.getLastUpdate());

                imDepartmentEntity.setDepartmentId(bean.getDepartmentId());
                imDepartmentEntity.setDepartmentName(bean.getDepartmentName());
                imDepartmentEntity.setFlag(bean.getFlag());
                imDepartmentEntity.setAction(bean.getAction());
                imDepartmentEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imDepartmentEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    departmentDao.updateAndSave(imDepartmentEntity);
                    departmentDao.addAndSaveHistory(imDepartmentHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[DepartmentBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Department, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[DepartmentBoImpl.saveEdit] Error, not found data Department with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Department with request id, please check again your data ...");
//                condition = "Error, not found data Department with request id, please check again your data ...";
            }
        }
        logger.info("[DepartmentBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Department saveAdd(Department bean) throws GeneralBOException {
        logger.info("[DepartmentBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String departmentId;
            try {
                // Generating ID, get from postgre sequence
                departmentId = departmentDao.getNextDepartmentId();
            } catch (HibernateException e) {
                logger.error("[DepartmentBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence departmentId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImDepartmentEntity imDepartmentEntity = new ImDepartmentEntity();

            imDepartmentEntity.setDepartmentId(departmentId);
            imDepartmentEntity.setDepartmentName(bean.getDepartmentName());
            imDepartmentEntity.setFlag(bean.getFlag());
            imDepartmentEntity.setAction(bean.getAction());
            imDepartmentEntity.setCreatedWho(bean.getCreatedWho());
            imDepartmentEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imDepartmentEntity.setCreatedDate(bean.getCreatedDate());
            imDepartmentEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                departmentDao.addAndSave(imDepartmentEntity);
            } catch (HibernateException e) {
                logger.error("[DepartmentBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Department, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[DepartmentBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Department> getByCriteria(Department searchBean) throws GeneralBOException {
        logger.info("[DepartmentBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Department> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getDepartmentId() != null && !"".equalsIgnoreCase(searchBean.getDepartmentId())) {
                hsCriteria.put("department_id", searchBean.getDepartmentId());
            }
            if (searchBean.getDepartmentName() != null && !"".equalsIgnoreCase(searchBean.getDepartmentName())) {
                hsCriteria.put("department_name", searchBean.getDepartmentName());
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


            List<ImDepartmentEntity> imDepartmentEntity = null;
            try {

                imDepartmentEntity = departmentDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[DepartmentBoImpl.getSearchDepartmentByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imDepartmentEntity != null){
                Department returnDepartment;
                // Looping from dao to object and save in collection
                for(ImDepartmentEntity departmentEntity : imDepartmentEntity){
                    returnDepartment = new Department();
                    returnDepartment.setDepartmentId(departmentEntity.getDepartmentId());
                    returnDepartment.setDepartmentName(departmentEntity.getDepartmentName());

                    returnDepartment.setCreatedWho(departmentEntity.getCreatedWho());
                    returnDepartment.setCreatedDate(departmentEntity.getCreatedDate());
                    returnDepartment.setLastUpdate(departmentEntity.getLastUpdate());

                    returnDepartment.setAction(departmentEntity.getAction());
                    returnDepartment.setFlag(departmentEntity.getFlag());
                    listOfResult.add(returnDepartment);
                }
            }
        }
        logger.info("[DepartmentBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Department> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Department> getComboDepartmentWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Department> listComboDepartment = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImDepartmentEntity> listDepartment = null;
        try {
            listDepartment = departmentDao.getListDepartment(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listDepartment != null) {
            for (ImDepartmentEntity imDepartmentEntity : listDepartment) {
                Department itemComboDepartment = new Department();
                itemComboDepartment.setDepartmentId(imDepartmentEntity.getDepartmentId());
                itemComboDepartment.setDepartmentName(imDepartmentEntity.getDepartmentName());
                listComboDepartment.add(itemComboDepartment);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboDepartment;
    }
}
