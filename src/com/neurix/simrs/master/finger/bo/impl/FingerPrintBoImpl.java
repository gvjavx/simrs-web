package com.neurix.simrs.master.finger.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;
import com.neurix.simrs.master.finger.bo.FingerPrintBo;
import com.neurix.simrs.master.finger.dao.FingerPrintDao;
import com.neurix.simrs.master.finger.model.FingerPrint;
import com.neurix.simrs.master.finger.model.ImSimrsFingerPrintEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class FingerPrintBoImpl implements FingerPrintBo {

    protected static transient Logger logger = Logger.getLogger(FingerPrintBoImpl.class);

    private FingerPrintDao fingerPrintDao;

    public void setFingerPrintDao(FingerPrintDao fingerPrintDao) {
        this.fingerPrintDao = fingerPrintDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        FingerPrintBoImpl.logger = logger;
    }

    public FingerPrintDao getFingerPrintDao() {
        return fingerPrintDao;
    }




    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public List<FingerPrint> getByCriteria(FingerPrint searchBean) throws GeneralBOException {
        logger.info("[FingerPrintBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<FingerPrint> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getIdFingerPrint() != null && !"".equalsIgnoreCase(searchBean.getIdFingerPrint())) {
                hsCriteria.put("finger_print_id", searchBean.getIdFingerPrint());
            }if (searchBean.getDeviceName() != null && !"".equalsIgnoreCase(searchBean.getDeviceName())) {
                hsCriteria.put("device_name", searchBean.getDeviceName());
            }if (searchBean.getSn() != null && !"".equalsIgnoreCase(searchBean.getSn())) {
                hsCriteria.put("sn", searchBean.getSn());
            }if (searchBean.getVc() != null && !"".equalsIgnoreCase(searchBean.getVc())) {
                hsCriteria.put("vc", searchBean.getVc());
            }if (searchBean.getAc() != null && !"".equalsIgnoreCase(searchBean.getAc())) {
                hsCriteria.put("ac", searchBean.getAc());
            }if (searchBean.getVkey() != null && !"".equalsIgnoreCase(searchBean.getVkey())) {
                hsCriteria.put("vkey", searchBean.getVkey());
            }if (searchBean.getUserId() != null && !"".equalsIgnoreCase(searchBean.getUserId())) {
                hsCriteria.put("user_id", searchBean.getUserId());
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


            List<ImSimrsFingerPrintEntity> imFingerPrintEntity = null;
            try {

                imFingerPrintEntity = fingerPrintDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[FingerPrintBoImpl.getSearchFingerPrintByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imFingerPrintEntity != null){
                FingerPrint returnFingerPrint;
                // Looping from dao to object and save in collection
                for(ImSimrsFingerPrintEntity fingerPrintEntity : imFingerPrintEntity){
                    returnFingerPrint = new FingerPrint();
                    returnFingerPrint.setIdFingerPrint(fingerPrintEntity.getIdFingerPrint());
                    returnFingerPrint.setSn(fingerPrintEntity.getSn());
                    returnFingerPrint.setVc(fingerPrintEntity.getVc());
                    returnFingerPrint.setAc(fingerPrintEntity.getAc());
                    returnFingerPrint.setVkey(fingerPrintEntity.getVkey());
                    returnFingerPrint.setDeviceName(fingerPrintEntity.getDeviceName());
                    returnFingerPrint.setUserId(fingerPrintEntity.getUserId());

                    returnFingerPrint.setCreatedWho(fingerPrintEntity.getCreatedWho());
                    returnFingerPrint.setCreatedDate(fingerPrintEntity.getCreatedDate());
                    returnFingerPrint.setLastUpdate(fingerPrintEntity.getLastUpdate());
                    returnFingerPrint.setAction(fingerPrintEntity.getAction());
                    returnFingerPrint.setFlag(fingerPrintEntity.getFlag());
                    listOfResult.add(returnFingerPrint);
                }
            }
        }
        logger.info("[FingerPrintBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public void saveAdd(FingerPrint pasien) throws GeneralBOException {

    }

    @Override
    public void saveEdit(FingerPrint pasien) throws GeneralBOException {

    }

    @Override
    public void saveDelete(FingerPrint bean) throws GeneralBOException {

    }

    @Override
    public List<FingerPrint> getListComboFingerPrint(String query) throws GeneralBOException {
        return null;
    }
}
