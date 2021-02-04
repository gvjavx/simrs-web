package com.neurix.simrs.master.license.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.license.bo.VersionMobileBo;
import com.neurix.simrs.master.license.dao.VersionMobileDao;
import com.neurix.simrs.master.license.model.ImVersionMobileEntity;
import com.neurix.simrs.master.license.model.VersionMobile;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionMobileBoImpl implements VersionMobileBo {
    protected static transient Logger logger = Logger.getLogger(com.neurix.simrs.master.license.bo.impl.VersionMobileBoImpl.class);
    private VersionMobileDao versionMobileDao;

    public void setVersionMobileDao(VersionMobileDao versionMobileDao) {
        this.versionMobileDao = versionMobileDao;
    }

    public List<ImVersionMobileEntity> getListEntityByCriteria(ImVersionMobileEntity bean) throws GeneralBOException {
        logger.info("[VersionMobileBoImpl.getByCriteria] Start >>>>>>>");
        List<ImVersionMobileEntity> imVersionMobile = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdVersionMobile() != null && !"".equalsIgnoreCase(bean.getIdVersionMobile())) {
                hsCriteria.put("id_version_mobile", bean.getIdVersionMobile());
            }
            if (bean.getNamaVersion() != null && !"".equalsIgnoreCase(bean.getNamaVersion())) {
                hsCriteria.put("nama_version", bean.getNamaVersion());
            }
            if (bean.getOs() != null && !"".equalsIgnoreCase(bean.getOs())) {
                hsCriteria.put("os", bean.getOs());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                imVersionMobile = versionMobileDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LicenseZebraBoImpl.getByCriteria] error when get data lab detailt by get by criteria " + e.getMessage());
            }
        }

        return imVersionMobile;
    }

    @Override
    public List<VersionMobile> getByCriteria(VersionMobile bean) throws GeneralBOException {
        logger.info("[VersionMobileBoImpl.getByCriteria] Start >>>>>>>");
        List<VersionMobile> result = new ArrayList<>();
        List<ImVersionMobileEntity> imVersionMobile = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdVersionMobile() != null && !"".equalsIgnoreCase(bean.getIdVersionMobile())) {
                hsCriteria.put("id_version_mobile", bean.getIdVersionMobile());
            }
            if (bean.getNamaVersion() != null && !"".equalsIgnoreCase(bean.getNamaVersion())) {
                hsCriteria.put("nama_version", bean.getNamaVersion());
            }
            if (bean.getOs() != null && !"".equalsIgnoreCase(bean.getOs())) {
                hsCriteria.put("os", bean.getOs());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                imVersionMobile = versionMobileDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LicenseZebraBoImpl.getByCriteria] error when get data lab detailt by get by criteria " + e.getMessage());
            }

            if (imVersionMobile.size() > 0) {
                VersionMobile versionMobile  = new VersionMobile();
                for (ImVersionMobileEntity item : imVersionMobile) {
                   versionMobile.setIdVersionMobile(item.getIdVersionMobile());
                   versionMobile.setNamaVersion(item.getNamaVersion());
                   versionMobile.setOs(item.getOs());
                   versionMobile.setFlag(item.getFlag());
                   versionMobile.setAction(item.getAction());
                   versionMobile.setCreatedDate(item.getCreatedDate());
                   versionMobile.setLastUpdate(item.getLastUpdate());
                   versionMobile.setCreatedWho(item.getCreatedWho());
                   versionMobile.setLastUpdateWho(item.getLastUpdateWho());

                   result.add(versionMobile);
                }

            }

        }
        logger.info("[VersionMobileBoImpl.getByCriteria] End >>>>>>>");
        return result;
    }

    @Override
    public void saveAdd(VersionMobile bean) {
        logger.info("[VersionMobileBoImpl.getByCriteria] Start >>>>>>>");
        String id = getNextIdVersionMobile();

        if(bean != null) {

            //Ubah flag versi sebelumnya menjadi N
            List<ImVersionMobileEntity> imVersionMobileEntities = new ArrayList<>();
            ImVersionMobileEntity versionMobile = new ImVersionMobileEntity();
            versionMobile.setFlag(bean.getFlag());

            try {
                imVersionMobileEntities = getListEntityByCriteria(versionMobile);
            } catch (GeneralBOException e){
                logger.info("[VersionMobile.saveAdd] Error when search license, " + e.getMessage());
                throw new GeneralBOException("[VersionMobile.saveAdd] Error when search license, " + e.getMessage());
            }

            if (imVersionMobileEntities.size() > 0) {
                for (ImVersionMobileEntity entities : imVersionMobileEntities) {
                    entities.setFlag("N");
                    entities.setAction("D");
                    entities.setLastUpdate(bean.getLastUpdate());
                    entities.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        versionMobileDao.updateAndSave(entities);
                    } catch (GeneralBOException e){
                        logger.info("[VersionMobile.saveAdd] Error when search license, " + e.getMessage());
                        throw new GeneralBOException("[VersionMobile.saveAdd] Error when search license, " + e.getMessage());
                    }
                }
            }

            //Add versi baru
            ImVersionMobileEntity entity = new ImVersionMobileEntity();
            entity.setIdVersionMobile(id);
            entity.setNamaVersion(bean.getNamaVersion());
            entity.setOs(bean.getOs());
            entity.setFlag(bean.getFlag());
            entity.setAction(bean.getAction());
            entity.setCreatedDate(CommonUtil.getCurrentDateTimes());
            entity.setLastUpdate(CommonUtil.getCurrentDateTimes());
            entity.setCreatedWho(bean.getCreatedWho());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                versionMobileDao.addAndSave(entity);
            }catch (HibernateException e) {
                logger.info("[VersionMobile.saveAdd] Error when search license, " + e.getMessage());
                throw new GeneralBOException("[VersionMobile.saveAdd] Error when search license, " + e.getMessage());
            }
        }


    }

    private String getNextIdVersionMobile () {
        logger.info("[VersionMobileBoImpl.getByCriteria] Start >>>>>>>");

        String id = "";
        try {
            id = versionMobileDao.getNextId();
        } catch (GeneralBOException e) {
            logger.error("[LicenseZebraBoImpl.getByCriteria] error when get data lab detailt by get by criteria " + e.getMessage());
        }
        logger.info("[VersionMobileBoImpl.getByCriteria] End >>>>>>>");
        return id;
    }
}
