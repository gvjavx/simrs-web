package com.neurix.authorization.company.bo.impl;

import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.dao.AreaDao;
import com.neurix.authorization.company.model.Area;
import com.neurix.authorization.company.model.ImAreas;
import com.neurix.authorization.company.model.ImAreasHistory;
import com.neurix.authorization.company.model.ImAreasPK;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.exception.GenerateBoLog;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class AreaBoImpl implements AreaBo {

    protected static transient Logger logger = Logger.getLogger(AreaBoImpl.class);
    private AreaDao areaDao;

    public void setAreaDao(AreaDao areaDao) {
        this.areaDao = areaDao;
    }

    public List<Area> getAll() throws GeneralBOException {

        logger.info("[AreaBoImpl.getAll] start process >>>");

        List<Area> listOfResultArea = new ArrayList();
        List<ImAreas> listOfArea = null;
        try {
            listOfArea = areaDao.getAll();
        } catch (HibernateException e) {
            logger.error("[AreaBoImpl.getAll] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting all data, please info to your admin..." + e.getMessage());
        }

        if ( listOfArea != null) {
            Area resultArea;
            for (ImAreas imAreas : listOfArea) {
                resultArea = new Area();

                resultArea.setAreaId(imAreas.getPrimaryKey().getId());
                resultArea.setAreaName(imAreas.getAreaName());
                resultArea.setCreatedWho(imAreas.getCreatedWho());
                resultArea.setCreatedDate(imAreas.getCreatedDate());
                resultArea.setLastUpdateWho(imAreas.getLastUpdateWho());
                resultArea.setLastUpdate(imAreas.getLastUpdate());
                resultArea.setAction(imAreas.getAction());
                resultArea.setFlag(imAreas.getFlag());

                listOfResultArea.add(resultArea);
            }
        }

        logger.info("[AreaBoImpl.getAll] end process <<<");

        return listOfResultArea;
    }

    public List<Area> getByCriteria(Area searchArea) throws GeneralBOException {

        logger.info("[AreaBoImpl.getByCriteria] start process >>>");

        List<Area> listOfResultAreas = new ArrayList();

        if (searchArea != null) {
            Map hsCriteria = new HashMap();
            if (searchArea.getAreaId() != null && !"".equalsIgnoreCase(searchArea.getAreaId())) {
                hsCriteria.put("area_id", searchArea.getAreaId());
            }

            if (searchArea.getAreaName() != null && !"".equalsIgnoreCase(searchArea.getAreaName())) {
                hsCriteria.put("area_name", searchArea.getAreaName());
            }

            if (searchArea.getFlag() != null && !"".equalsIgnoreCase(searchArea.getFlag())) {
                if ("N".equalsIgnoreCase(searchArea.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchArea.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImAreas> listOfArea = null;
            try {
                listOfArea = areaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AreaBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfArea != null) {
                Area resultArea;
                for (ImAreas imAreas : listOfArea) {
                    resultArea = new Area();

                    resultArea.setAreaId(imAreas.getPrimaryKey().getId());
                    resultArea.setAreaName(imAreas.getAreaName());
                    resultArea.setCreatedWho(imAreas.getCreatedWho());
                    resultArea.setCreatedDate(imAreas.getCreatedDate());
                    resultArea.setLastUpdateWho(imAreas.getLastUpdateWho());
                    resultArea.setLastUpdate(imAreas.getLastUpdate());
                    resultArea.setAction(imAreas.getAction());
                    resultArea.setFlag(imAreas.getFlag());

                    listOfResultAreas.add(resultArea);
                }
            }

        }

        logger.info("[AreaBoImpl.getByCriteria] end process <<<");

        return listOfResultAreas;
    }

    /*public void saveEdit(Area bean) throws GeneralBOException {
        logger.info("[AlatBoImpl.saveEdit] start process >>>");

        if (bean!=null) {

            String areaId = bean.getAreaId();

            ImAreas imAreas = null;

            try {
                // Get data from database by ID
                imAreas = areaDao.getById("primaryKey", areaId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imAreas != null) {
                //
                imAreas.setAreaId(bean.getAreaId());
                imAreas.setAreaName(bean.getAreaName());
                imAreas.setFlag(bean.getFlag());
                imAreas.setAction(bean.getAction());
                imAreas.setLastUpdateWho(bean.getLastUpdateWho());
                imAreas.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    areaDao.updateAndSave(imAreas);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[AlatBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[AlatBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
//                condition = "Error, not found data alat with request id, please check again your data ...";
            }
        }
        logger.info("[AlatBoImpl.saveEdit] end process <<<");
    }*/

    public Area saveAdd(Area area) throws GeneralBOException {

        logger.info("[AreaBoImpl.saveAdd] start process >>>");

        if (area != null) {
            ImAreas imAreas = new ImAreas();

            long areaId;
            try {
                areaId = areaDao.getNextArea();
            } catch (HibernateException e) {
                logger.error("[AreaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when get area id, please inform to your admin...," + e.getMessage());
            }

            String id = String.format("%1$04d", Long.valueOf(areaId));
            ImAreasPK primaryKey = new ImAreasPK();
            primaryKey.setId(id);

            imAreas.setPrimaryKey(primaryKey);
            imAreas.setAreaName(area.getAreaName());
            imAreas.setCreatedWho(area.getCreatedWho());
            imAreas.setCreatedDate(area.getCreatedDate());
            imAreas.setLastUpdateWho(area.getLastUpdateWho());
            imAreas.setLastUpdate(area.getLastUpdate());
            imAreas.setAction(area.getAction());
            imAreas.setFlag("Y");

            try {
                areaDao.addAndSave(imAreas);
            } catch (HibernateException e) {
                logger.error("[AreaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data area, please info to your admin..." + e.getMessage());
            }

        }

        logger.info("[AreaBoImpl.saveAdd] end process <<<");

        return area;
    }

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {

        Long result = GenerateBoLog.generateBoLog(areaDao, message, moduleMethod);

        return result;
    }

    public void saveEdit(Area areaNew) throws GeneralBOException {

        logger.info("[AreaBoImpl.saveEdit] start process >>>");

        if (areaNew != null) {

            String areaId = areaNew.getAreaId();

            //copy new data to model tabel
            ImAreas imAreasNew = new ImAreas();

            try {
                BeanUtils.copyProperties(imAreasNew, areaNew);
            } catch (IllegalAccessException e) {
                logger.error("[AreaBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping data object areaNew to imAreasNew, please info to your admin..." + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("[AreaBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping data object areaNew to imAreasNew, please info to your admin..." + e.getMessage());
            }

            ImAreas imAreasOld = null;
            ImAreasPK primaryKey = new ImAreasPK();
            primaryKey.setId(areaId);
            try {
                imAreasOld = areaDao.getById(primaryKey, "Y");
            } catch (HibernateException e) {
                logger.error("[AreaBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data area by id, please inform to your admin...," + e.getMessage());
            }

            if (imAreasOld != null) {

                // move last data to table history
                ImAreasHistory imAreasDeactive = new ImAreasHistory();
                try {
                    BeanUtils.copyProperties(imAreasDeactive, imAreasOld);
                } catch (IllegalAccessException e) {
                    logger.error("[AreaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object areaOld to imAreasBeforeDeactive, please info to your admin..." + e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[AreaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object areaOld to imAreasBeforeDeactive, please info to your admin..." + e.getMessage());
                }

                imAreasDeactive.setAreaId(imAreasOld.getPrimaryKey().getId());

                try {
                    areaDao.addAndSaveHistory(imAreasDeactive);
                } catch (HibernateException e) {
                    logger.error("[AreaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving deactive data area, please info to your admin..." + e.getMessage());
                }

                //update some of last data become new data
                imAreasNew.setPrimaryKey(imAreasOld.getPrimaryKey());
                imAreasNew.setCreatedDate(imAreasOld.getCreatedDate());
                imAreasNew.setCreatedWho(imAreasOld.getCreatedWho());
                imAreasNew.setFlag(imAreasOld.getFlag());

                ImAreas imAreasActive = (ImAreas) areaDao.getSessionFactory().getCurrentSession().merge(imAreasNew);

                try {
                    areaDao.updateAndSave(imAreasActive);
                } catch (HibernateException e) {
                    logger.error("[AreaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving updated data area, please inform to your admin...," + e.getMessage());
                }

            } else {
                logger.error("[AreaBoImpl.saveEdit] Unable to save edit cause no found area key.");
                throw new GeneralBOException("Found problem when saving edit data role cause no found area key., please info to your admin...");
            }
        }

        logger.info("[AreaBoImpl.saveEdit] end process <<<");
    }

    public void saveDelete(Area area) throws GeneralBOException {

        logger.info("[AreaBoImpl.saveDelete] start process >>>");

        if (area != null) {

            String areaId = area.getAreaId();

            ImAreas imAreasOld = null;
            ImAreasPK primaryKey = new ImAreasPK();
            primaryKey.setId(areaId);

            try {
                imAreasOld = areaDao.getById(primaryKey, "Y");
            } catch (HibernateException e) {
                logger.error("[AreaBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving delete data area, please info to your admin..." + e.getMessage());
            }

            if (imAreasOld != null) {

                Set listOfImAreasBranchesUsers = imAreasOld.getImAreasBranchesUsers();

                if (listOfImAreasBranchesUsers.size() == 0) {

                    ImAreas imAreaToDeactive = new ImAreas();

                    try {
                        BeanUtils.copyProperties(imAreaToDeactive, imAreasOld);
                    } catch (IllegalAccessException e) {
                        logger.error("[AreaBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping data object area Will be Delete to imRolesBeforeDeactive, please info to your admin..." + e.getMessage());
                    } catch (InvocationTargetException e) {
                        logger.error("[AreaBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping data object area  Will be Delete to imRolesBeforeDeactive, please info to your admin..." + e.getMessage());
                    }

                    //update data with flag=N
                    imAreaToDeactive.setFlag("N");
                    imAreaToDeactive.setAction(area.getAction());
                    imAreaToDeactive.setLastUpdate(area.getLastUpdate());
                    imAreaToDeactive.setLastUpdateWho(area.getLastUpdateWho());

                    ImAreas imAreasDeactive = (ImAreas) areaDao.getSessionFactory().getCurrentSession().merge(imAreaToDeactive);

                    try {
                        areaDao.updateAndSave(imAreasDeactive);
                    } catch (HibernateException e) {
                        logger.error("[AreaBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving delete data area, please info to your admin..." + e.getMessage());
                    }

                } else {
                    logger.error("[AreaBoImpl.saveDelete] Unable to delete cause have reference data exist in area-branch-user table.");
                    throw new GeneralBOException("Found problem when saving delete data role cause have reference data exist in area-branch-user table, please info to your admin...");
                }
            } else {
                logger.error("[AreaBoImpl.saveDelete] Unable to delete cause no found area key.");
                throw new GeneralBOException("Found problem when saving delete data area cause no found area key., please info to your admin...");
            }
        }

        logger.info("[AreaBoImpl.saveDelete] end process <<<");
    }

    public Area getAreaById(String areaId, String flag) throws GeneralBOException {

        logger.info("[AreaBoImpl.getAreaById] start process >>>");

        String getFlag = "";
        if (flag != null && !"".equalsIgnoreCase(flag)) {
            if (flag.equalsIgnoreCase("")) getFlag = "Y";
            else getFlag = flag;
        } else {
            getFlag = "Y";
        }

        ImAreas imAreas = null;
        ImAreasPK primaryKey = new ImAreasPK();
        primaryKey.setId(areaId);

        try {
            imAreas = areaDao.getById(primaryKey,getFlag);
        } catch (HibernateException e) {
            logger.error("[AreaBoImpl.getAreaById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving area based on id and flag, please info to your admin..." + e.getMessage());
        }

        Area resultArea = new Area();
        if (imAreas != null) {

            resultArea.setAreaId(imAreas.getPrimaryKey().getId());
            resultArea.setAreaName(imAreas.getAreaName());
            resultArea.setCreatedWho(imAreas.getCreatedWho());
            resultArea.setCreatedDate(imAreas.getCreatedDate());
            resultArea.setLastUpdateWho(imAreas.getLastUpdateWho());
            resultArea.setLastUpdate(imAreas.getLastUpdate());
            resultArea.setAction(imAreas.getAction());
            resultArea.setFlag(imAreas.getFlag());

        }

        logger.info("[AreaBoImpl.getAreaById] end process <<<");

        return resultArea;
    }

    public List<Area> getComboAreaWithCriteria(String query) throws GeneralBOException {
        logger.info("[AreaBoImpl.getComboAreaWithCriteria] start process >>>");

        List<Area> listComboArea = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImAreas> listArea = null;
        try {
            listArea = areaDao.getListArea(criteria);
        } catch (HibernateException e) {
            logger.error("[AreaBoImpl.getComboAreaWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list area with criteria, please info to your admin..." + e.getMessage());
        }

        if (listArea != null) {
            for (ImAreas imAreas : listArea) {
                Area itemComboArea = new Area();
                itemComboArea.setAreaId(imAreas.getPrimaryKey().getId());
                itemComboArea.setAreaName(imAreas.getAreaName());
                listComboArea.add(itemComboArea);
            }
        }
        logger.info("[AreaBoImpl.getComboAreaWithCriteria] end process <<<");
        return listComboArea;
    }
}
