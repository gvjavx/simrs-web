package com.neurix.simrs.transaksi.initdashboard.bo.impl;

import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.initdashboard.bo.InitDashboardBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

public class InitDashboardBoImpl implements InitDashboardBo {
    public static transient Logger logger = Logger.getLogger(InitDashboardBoImpl.class);
    private HeaderCheckupDao headerCheckupDao;

    @Override
    public HeaderCheckup getCountAll(String branch) {
        HeaderCheckup headerCheckup = new HeaderCheckup();
        try {
            headerCheckup = headerCheckupDao.getCountAll(branch);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return headerCheckup;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
