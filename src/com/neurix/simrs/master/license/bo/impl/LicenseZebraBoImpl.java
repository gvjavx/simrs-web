package com.neurix.simrs.master.license.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.license.bo.LicenseZebraBo;
import com.neurix.simrs.master.license.dao.LicenseLogZebraDao;
import com.neurix.simrs.master.license.dao.LicenseZebraDao;
import com.neurix.simrs.master.license.dao.VersionZebraDao;
import com.neurix.simrs.master.license.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.security.access.method.P;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LicenseZebraBoImpl implements LicenseZebraBo {

    protected static transient Logger logger = Logger.getLogger(com.neurix.simrs.master.license.bo.impl.LicenseZebraBoImpl.class);
    private LicenseZebraDao licenseZebraDao;
    private LicenseLogZebraDao licenseLogZebraDao;
    private VersionZebraDao versionZebraDao;

    public void setVersionZebraDao(VersionZebraDao versionZebraDao) {
        this.versionZebraDao = versionZebraDao;
    }

    public void setLicenseLogZebraDao(LicenseLogZebraDao licenseLogZebraDao) {
        this.licenseLogZebraDao = licenseLogZebraDao;
    }

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
                    licenseZebra.setStatus(item.getStatus());
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
        bean.setFlag("Y");

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

        try {
            licenseZebraDao.updateAndSave(entity);
        } catch (GeneralBOException e) {
            logger.error("[LicenseZebraBoImpl.isKeyAvailable] error when get data entity by get by criteria " + e.getMessage());
        }

        //saveAdd ke tabel log
        String idLog = getNextLicenseLogId();

        ImLicenseZebraLogEntity imLicenseZebraLogEntity = new ImLicenseZebraLogEntity();
        imLicenseZebraLogEntity.setLicenseLogId(idLog);
        imLicenseZebraLogEntity.setLicenseId(entity.getLicenseId());
        imLicenseZebraLogEntity.setDeviceId(entity.getDeviceId());
        imLicenseZebraLogEntity.setLicenseKey(entity.getLicenseKey());
        imLicenseZebraLogEntity.setAction(entity.getAction());
        imLicenseZebraLogEntity.setFlag(entity.getFlag());
        imLicenseZebraLogEntity.setCreatedDate(entity.getLastUpdate());
        imLicenseZebraLogEntity.setCreatedWho(entity.getCreatedWho());
        imLicenseZebraLogEntity.setLastUpdateWho(entity.getLastUpdateWho());
        imLicenseZebraLogEntity.setLastUpdate(entity.getLastUpdate());

        try {
            licenseLogZebraDao.addAndSave(imLicenseZebraLogEntity);
        } catch (GeneralBOException e) {
            logger.error("[LicenseZebraBoImpl.saveZAdd] error when get data entity by get by criteria " + e.getMessage());
        }

        logger.info("[LicenseZebraBoImpl.saveEdit] End >>>>>>>");
    }

    public void saveAdd(LicenseZebra bean) throws GeneralBOException {
        String id = getNextLicenseId();
        if (bean != null) {
            String licenseKey = CommonUtil.getRandomString(8);
            ImLicenseZebraEntity imLicenseZebraEntity = new ImLicenseZebraEntity();
            imLicenseZebraEntity.setLicenseId(id);
            imLicenseZebraEntity.setDeviceId(bean.getDeviceId());
            imLicenseZebraEntity.setAction(bean.getAction());
            imLicenseZebraEntity.setFlag(bean.getFlag());
            imLicenseZebraEntity.setCreatedDate(bean.getCreatedDate());
            imLicenseZebraEntity.setCreatedWho(bean.getCreatedWho());
            imLicenseZebraEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imLicenseZebraEntity.setLastUpdate(bean.getLastUpdate());
            imLicenseZebraEntity.setStatus("0");
            List<ImLicenseZebraEntity> list = licenseZebraDao.getDeviceId(bean.getDeviceId());
            if(list.size() > 0){
                logger.error("Device ID : "+bean.getDeviceId()+" sudah ada...! @_@");
                throw new GeneralBOException("Device ID : "+bean.getDeviceId()+" sudah ada...! @_@");
            }else{
                if(!isKeyAvailable(licenseKey, bean.getDeviceId())){
                    try {

                        licenseZebraDao.addAndSave(imLicenseZebraEntity);

                        //send api license
                        String jsonString = "{\n" +
                                "    \"licenseId\":\""+imLicenseZebraEntity.getLicenseId()+"\",\n" +
                                "    \"deviceId\":\""+imLicenseZebraEntity.getDeviceId()+"\",\n" +
                                "    \"branchName\":\""+CommonUtil.userBranchNameLogin()+"\",\n" +
                                "    \"createdWho\": \""+CommonUtil.userLogin()+"\"\n" +
                                "}";
                        String requestUrl=CommonConstant.LICENSE_API+"api/license/save";
                        sendPostRequest(requestUrl, jsonString);

                        //send email license
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(imLicenseZebraEntity.getCreatedDate());
                        Email email = new Email();
                        email.setFrom(CommonConstant.EMAIL_USERNAME);
                        email.setPassword(CommonConstant.EMAIL_PASSWORD);
                        email.setTo(CommonConstant.LICENSE_EMAIL_TO);
                        email.setSubject("License Key Verification");
                        email.setMsg("<h2>License Key Verification</h2>\n" +
                                "=========================================\n" +
                                "<table width=\"100%\">\n" +
                                "<tr>\n" +
                                "<td width=\"20%\">Device ID</td>\n" +
                                "<td>: "+imLicenseZebraEntity.getDeviceId()+"</td>\n" +
                                "</tr>\n" +
                                "<tr>\n" +
                                "<td>Unit</td>\n" +
                                "<td>: "+CommonUtil.userBranchNameLogin()+"</td>\n" +
                                "</tr>\n" +
                                "<tr>\n" +
                                "<td>Created Who</td>\n" +
                                "<td>: "+CommonUtil.userLogin()+"</td>\n" +
                                "</tr>\n" +
                                "<tr>\n" +
                                "<td>Created Date</td>\n" +
                                "<td>: "+formatDate+"</td>\n" +
                                "</tr>\n" +
                                "</table>\n" +
                                "=========================================\n" +
                                "<br> \n" +
                                "<br>\n" +
                                "<span style=\"color: blue\">click this button for activation!</span>\n" +
                                "<a href=\""+CommonConstant.LICENSE_API+"license/auth?id="+imLicenseZebraEntity.getLicenseId()+"\" target=\"__blank\"><button style=\"cursor: pointer\">Activation</button></a>");
                        CommonUtil.sendEmail(email);
                    } catch (GeneralBOException e){
                        logger.error("[LicenseZebraBoImpl.saveZAdd] error when get data entity by get by criteria " + e.getMessage());
                        throw new GeneralBOException("Error...! @_@");
                    }
                }else{
                    logger.error("License Key : "+licenseKey+" dan Device ID : "+bean.getDeviceId()+" sudah ada...! @_@");
                    throw new GeneralBOException("License Key : "+licenseKey+" dan Device ID : "+bean.getDeviceId()+" sudah ada...! @_@");
                }
            }
        }

    }

    @Override
    public void saveEdit(LicenseZebra bean) throws GeneralBOException {
        if(bean != null){
            ImLicenseZebraEntity imLicenseZebraEntity = licenseZebraDao.getById("licenseId", bean.getLicenseId());
            if(imLicenseZebraEntity != null){
                imLicenseZebraEntity.setLastUpdate(bean.getLastUpdate());
                imLicenseZebraEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imLicenseZebraEntity.setAction(bean.getAction());
                imLicenseZebraEntity.setFlag(bean.getFlag());
                imLicenseZebraEntity.setStatus(bean.getStatus());
                try {
                    licenseZebraDao.updateAndSave(imLicenseZebraEntity);
                }catch (HibernateException e){
                    logger.error("[LicenseZebraBoImpl.saveEdit] error when edit license" + e.getMessage());
                    throw new GeneralBOException("[LicenseZebraBoImpl.saveEdit] error when edit license" +e.getMessage());
                }
            }
        }
    }

    public void saveAddVersion(VersionZebra bean) throws GeneralBOException {
        String id = getNextVersionId();
        if(bean != null) {

            //Ubah flag versi sebelumnya menjadi N
            List<ImVersionZebraEntity> imVersionZebraEntities = new ArrayList<>();
            VersionZebra versionZebra = new VersionZebra();
            versionZebra.setFlag(bean.getFlag());

            try {
                imVersionZebraEntities = getListEntityVersionByCriteria(versionZebra);
            } catch (GeneralBOException e){
                logger.error("[LicenseZebraBoImpl.saveAddVersion] error when get data entity by get by criteria " + e.getMessage());
            }

            if (imVersionZebraEntities.size() > 0) {
                for (ImVersionZebraEntity entity: imVersionZebraEntities){
                    entity.setFlag("N");
                    entity.setAction("D");
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        versionZebraDao.updateAndSave(entity);
                    } catch (GeneralBOException e) {
                        logger.error("[LicenseZebraBoImpl.saveAddVersion] error when get data entity by get by criteria " + e.getMessage());
                    }
                }
            }

            //Add versi baru
            ImVersionZebraEntity imVersionZebraEntity = new ImVersionZebraEntity();
            imVersionZebraEntity.setIdVersion(id);
            imVersionZebraEntity.setVersionName(bean.getVersionName());
            imVersionZebraEntity.setDescription(bean.getDescription());
            imVersionZebraEntity.setFlag(bean.getFlag());
            imVersionZebraEntity.setAction(bean.getAction());
            imVersionZebraEntity.setCreatedDate(bean.getCreatedDate());
            imVersionZebraEntity.setLastUpdate(bean.getLastUpdate());
            imVersionZebraEntity.setCreatedWho(bean.getCreatedWho());
            imVersionZebraEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                versionZebraDao.addAndSave(imVersionZebraEntity);
            } catch (GeneralBOException e) {
                logger.error("[LicenseZebraBoImpl.saveAddVersion] error when get data entity by get by criteria " + e.getMessage());
            }
        }
    }

    public List<VersionZebra> getVersionByCriteria(VersionZebra bean) throws GeneralBOException {
        logger.info("[LicenseZebraBoImpl.getByCriteria] Start >>>>>>>");
        List<VersionZebra> result = new ArrayList<>();
        List<ImVersionZebraEntity> imVersionZebraEntities = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdVersion() != null && !"".equalsIgnoreCase(bean.getIdVersion())) {
                hsCriteria.put("id_version", bean.getIdVersion());
            }
            if (bean.getVersionName() != null && !"".equalsIgnoreCase(bean.getVersionName())) {
                hsCriteria.put("version_name", bean.getVersionName());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                imVersionZebraEntities = versionZebraDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LicenseZebraBoImpl.getByCriteria] error when get data lab detailt by get by criteria " + e.getMessage());
            }

            if (imVersionZebraEntities.size() > 0) {
                VersionZebra versionZebra;
                for (ImVersionZebraEntity item : imVersionZebraEntities) {
                    versionZebra = new VersionZebra();
                    versionZebra.setIdVersion(item.getIdVersion());
                    versionZebra.setVersionName(item.getVersionName());
                    versionZebra.setCreatedDate(item.getCreatedDate());
                    versionZebra.setLastUpdate(item.getLastUpdate());
                    versionZebra.setCreatedWho(item.getCreatedWho());
                    versionZebra.setLastUpdateWho(item.getLastUpdateWho());
                    versionZebra.setDescription(item.getDescription());
                    versionZebra.setFlag(item.getFlag());
                    versionZebra.setAction(item.getAction());

                    result.add(versionZebra);
                }

            }

        }
        logger.info("[LicenseZebraBoImpl.getByCriteria] End >>>>>>>");
        return result;
    }

    public List<ImVersionZebraEntity> getListEntityVersionByCriteria(VersionZebra bean) throws GeneralBOException {
        logger.info("[LicenseZebraBoImpl.getByCriteria] Start >>>>>>>");
        List<ImVersionZebraEntity> imVersionZebraEntities = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdVersion() != null && !"".equalsIgnoreCase(bean.getIdVersion())) {
                hsCriteria.put("id_version", bean.getIdVersion());
            }
            if (bean.getVersionName() != null && !"".equalsIgnoreCase(bean.getVersionName())) {
                hsCriteria.put("version_name", bean.getVersionName());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                imVersionZebraEntities = versionZebraDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LicenseZebraBoImpl.getByCriteria] error when get data lab detailt by get by criteria " + e.getMessage());
            }
        }

        return imVersionZebraEntities;
    }

    private String getNextLicenseId() {
        logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] Start >>>>>>>");
        String id="";

        try {
            id = licenseZebraDao.getNextLicenseId();
        } catch (HibernateException e){
            logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] Error :"+ e );
        }

        logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] End >>>>>>>");
        return id;
    }

    private String getNextLicenseLogId() {
        logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] Start >>>>>>>");
        String id="";

        try {
            id = licenseLogZebraDao.getNextLicenseLogId();
        } catch (HibernateException e){
            logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] Error :"+ e );
        }

        logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] End >>>>>>>");
        return id;
    }

    private String getNextVersionId() {
        logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] Start >>>>>>>");
        String id="";

        try {
            id = versionZebraDao.getNextId();
        } catch (HibernateException e){
            logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] Error :"+ e );
        }

        logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] End >>>>>>>");
        return id;
    }

    public static String sendPostRequest(String requestUrl, String payload) {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty( "Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
