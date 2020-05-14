package com.neurix.simrs.transaksi.rekammedik.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class RekamMedikBoImpl implements RekamMedikBo {

    protected static transient Logger logger = Logger.getLogger(RekamMedikBoImpl.class);
    private CheckupDetailDao checkupDetailDao;

    @Override
    public List<HeaderDetailCheckup> getListPasien(HeaderDetailCheckup bean) throws GeneralBOException {
        List<HeaderDetailCheckup> list = new ArrayList<>();
        try {
            list = checkupDetailDao.getListPasienRekamMedik(bean);
        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
        }
        return list;
    }

    @Override
    public List<HeaderDetailCheckup> getDetailListRekamMedis(String idPasien) throws GeneralBOException {
        List<HeaderDetailCheckup> list = new ArrayList<>();
        try {
            list = checkupDetailDao.getDetailListRekamMedis(idPasien);
        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
        }
        return list;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
