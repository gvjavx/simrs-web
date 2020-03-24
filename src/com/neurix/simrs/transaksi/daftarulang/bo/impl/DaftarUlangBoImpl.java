package com.neurix.simrs.transaksi.daftarulang.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.daftarulang.bo.DaftarUlangBo;
import com.neurix.simrs.transaksi.statusperiksa.bo.impl.StatusPeriksaBoImpl;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class DaftarUlangBoImpl implements DaftarUlangBo {
    public static transient Logger logger = Logger.getLogger(DaftarUlangBoImpl.class);
    private CheckupDetailDao checkupDetailDao;

    @Override
    public List<HeaderDetailCheckup> getListDaftarUlangPasien(HeaderDetailCheckup headerDetailCheckup) throws GeneralBOException {
        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

        if (headerDetailCheckup != null) {
            try {
                detailCheckupList = checkupDetailDao.getDaftarUlangPasien(headerDetailCheckup);
            } catch (HibernateException e) {
                logger.error("Found Error when search status periksa " + e.getMessage());
            }
        }

        return detailCheckupList;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }
}
