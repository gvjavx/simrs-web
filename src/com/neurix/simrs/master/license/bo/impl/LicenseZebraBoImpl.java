package com.neurix.simrs.master.license.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.license.bo.LicenseZebraBo;
import com.neurix.simrs.master.license.dao.LicenseZebraDao;
import com.neurix.simrs.master.license.model.ImLicenseZebraEntity;
import com.neurix.simrs.master.license.model.LicenseZebra;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.security.access.method.P;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LicenseZebraBoImpl implements LicenseZebraBo {

    protected static transient Logger logger = Logger.getLogger(com.neurix.simrs.master.license.bo.impl.LicenseZebraBoImpl.class);
    private LicenseZebraDao licenseZebraDao;

    public void setLicenseZebraDao(LicenseZebraDao licenseZebraDao) {
        this.licenseZebraDao = licenseZebraDao;
    }

    @Override
    public List<LicenseZebra> getByCriteria(LicenseZebra bean) throws GeneralBOException {
        logger.info("[LicenseZebraBoImpl.getByCriteria] Start >>>>>>>");
        List<LicenseZebra> result = new ArrayList<>();
        List<ImLicenseZebraEntity> imLicenseZebraEntities = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getLicenseId() != null && !"".equalsIgnoreCase(bean.getLicenseId())) {
                hsCriteria.put("license_id", bean.getLicenseId());
            }
            if (bean.getLicenseKey() != null && !"".equalsIgnoreCase(bean.getLicenseKey())) {
                hsCriteria.put("license_key", bean.getLicenseKey());
            }
            if (bean.getDeviceId() != null && !"".equalsIgnoreCase(bean.getDeviceId())) {
                hsCriteria.put("device_id", bean.getDeviceId());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            } else {
                hsCriteria.put("flag", "Y");
            }

            try {
                imLicenseZebraEntities = licenseZebraDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LicenseZebraBoImpl.getByCriteria] error when get data lab detailt by get by criteria " + e.getMessage());
            }

            if (imLicenseZebraEntities.size() > 0) {
                LicenseZebra licenseZebra;
                for (ImLicenseZebraEntity item : imLicenseZebraEntities) {
                    licenseZebra = new LicenseZebra();
                    licenseZebra.setLicenseId(item.getLicenseId());
                    licenseZebra.setLicenseKey(item.getLicenseKey());
                    licenseZebra.setDeviceId(item.getDeviceId());
                    licenseZebra.setFlag(item.getFlag());
                    licenseZebra.setAction(item.getAction());
                    licenseZebra.setCreatedDate(item.getCreatedDate());
                    licenseZebra.setCreatedWho(item.getCreatedWho());
                    licenseZebra.setLastUpdate(item.getLastUpdate());
                    licenseZebra.setLastUpdateWho(item.getLastUpdateWho());

                    result.add(licenseZebra);
                }

            }

        }
        logger.info("[LicenseZebraBoImpl.getByCriteria] End >>>>>>>");
        return result;
    }

    public List<ImLicenseZebraEntity> getListEntityLicenseZebra(LicenseZebra bean) throws GeneralBOException {
        logger.info("[LicenseZebraBoImpl.isKeyAvailable] Start >>>>>>>");
        List<ImLicenseZebraEntity> imLicenseZebraEntities = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getLicenseId() != null && !"".equalsIgnoreCase(bean.getLicenseId())) {
                hsCriteria.put("license_id", bean.getLicenseId());
            }
            if (bean.getLicenseKey() != null && !"".equalsIgnoreCase(bean.getLicenseKey())) {
                hsCriteria.put("license_key", bean.getLicenseKey());
            }
            if (bean.getDeviceId() != null && !"".equalsIgnoreCase(bean.getDeviceId())) {
                hsCriteria.put("device_id", bean.getDeviceId());
            }
//            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
//                hsCriteria.put("flag", bean.getFlag());
//            } else {
//                hsCriteria.put("flag", "Y");
//            }

            try {
                imLicenseZebraEntities = licenseZebraDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LicenseZebraBoImpl.getByCriteria] error when get data lab detailt by get by criteria " + e.getMessage());
            }
        }
        logger.info("[LicenseZebraBoImpl.isKeyAvailable] End >>>>>>>");
        return imLicenseZebraEntities;
    }

    public boolean isKeyAvailable(String licenseKey, String deviceId) throws GeneralBOException{
        logger.info("[LicenseZebraBoImpl.isKeyAvailable] Start >>>>>>>");

        List<LicenseZebra> listLicenseZebra = new ArrayList<>();

        LicenseZebra bean = new LicenseZebra();
        bean.setLicenseKey(licenseKey);
        bean.setDeviceId(deviceId);

        try {
            listLicenseZebra = getByCriteria(bean);
        } catch (HibernateException e) {
            logger.error("[LicenseZebraBoImpl.isKeyAvailable] error when get data by get by criteria " + e.getMessage());
        }

        logger.info("[LicenseZebraBoImpl.isKeyAvailable] End >>>>>>>");
        if (listLicenseZebra.size() > 0) {
            return true;
        } else return false;
    }

    public void updateFlag(LicenseZebra bean) throws GeneralBOException{
        logger.info("[LicenseZebraBoImpl.saveEdit] Start >>>>>>>");
        List<ImLicenseZebraEntity> imLicenseZebraEntityList = new ArrayList<>();

        try {
            imLicenseZebraEntityList = getListEntityLicenseZebra(bean);
        } catch (GeneralBOException e){
            logger.error("[LicenseZebraBoImpl.isKeyAvailable] error when get data entity by get by criteria " + e.getMessage());
        }

        ImLicenseZebraEntity entity = imLicenseZebraEntityList.get(0);
        entity.setFlag(bean.getFlag());
        entity.setAction(bean.getAction());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setLastUpdateWho("admin");

        logger.info("[LicenseZebraBoImpl.saveEdit] End >>>>>>>");
    }

    public void saveAdd(LicenseZebra bean) throws GeneralBOException {

        if (bean != null) {
            ImLicenseZebraEntity imLicenseZebraEntity = new ImLicenseZebraEntity();
            imLicenseZebraEntity.setLicenseId("LCZ" + licenseZebraDao.getNextLicenseId());
            imLicenseZebraEntity.setDeviceId(bean.getDeviceId());
            imLicenseZebraEntity.setLicenseKey(bean.getLicenseKey());
            imLicenseZebraEntity.setAction(bean.getAction());
            imLicenseZebraEntity.setFlag(bean.getFlag());
            imLicenseZebraEntity.setCreatedDate(bean.getCreatedDate());
            imLicenseZebraEntity.setCreatedWho(bean.getCreatedWho());
            imLicenseZebraEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imLicenseZebraEntity.setLastUpdate(bean.getLastUpdate());

            try {
                licenseZebraDao.updateAndSave(imLicenseZebraEntity);
            } catch (GeneralBOException e){
                logger.error("[LicenseZebraBoImpl.saveZAdd] error when get data entity by get by criteria " + e.getMessage());
            }
        }


    }
}
