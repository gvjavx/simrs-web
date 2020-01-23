package com.neurix.simrs.bpjs.fingerPrint.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.bpjs.fingerPrint.bo.FingerPrintBo;
import com.neurix.simrs.bpjs.fingerPrint.dao.FingerPrintDao;
import com.neurix.simrs.bpjs.fingerPrint.model.FingerPrint;
import com.neurix.simrs.bpjs.fingerPrint.model.ImFingerPrintEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FingerPrintBoImpl extends BpjsService implements FingerPrintBo {

    protected static transient Logger logger = Logger.getLogger(FingerPrintBoImpl.class);
    private FingerPrintDao fingerPrintDao;

    public FingerPrintBoImpl() throws GeneralSecurityException, IOException {
    }

    public static void setLogger(Logger logger) {
        FingerPrintBoImpl.logger = logger;
    }

    public FingerPrintDao getFingerPrintDao() {
        return fingerPrintDao;
    }

    public void setFingerPrintDao(FingerPrintDao fingerPrintDao) {
        this.fingerPrintDao = fingerPrintDao;
    }

    public static Logger getLogger() {
        return logger;
    }


    @Override
    public List<FingerPrint> getByCriteria(FingerPrint bean) throws GeneralBOException {
        logger.info("[FingerPrintBoimpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            hsCriteria.put("flag", "Y");

            List<ImFingerPrintEntity> fingerPrintEntityList = new ArrayList<>();
            List<FingerPrint> result = new ArrayList<>();

            try {
                fingerPrintEntityList = fingerPrintDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[FingerPrintBoimpl.getByCriteria] error when get data tindakan by get by criteria "+ e.getMessage());
            }
            if (!fingerPrintEntityList.isEmpty()){
                FingerPrint fingerPrint;
                for (ImFingerPrintEntity tindakanEntity : fingerPrintEntityList){
                    fingerPrint = new FingerPrint();

                    result.add(fingerPrint);
                }
            }

            logger.info("[FingerPrintBoimpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[FingerPrintBoimpl.getByCriteria] End <<<<<<<");
        return null;
    }
}