package com.neurix.simrs.transaksi.initdashboard.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.initdashboard.bo.InitDashboardBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class InitDashboardBoImpl implements InitDashboardBo {
    public static transient Logger logger = Logger.getLogger(InitDashboardBoImpl.class);
    private HeaderCheckupDao headerCheckupDao;

    @Override
    public HeaderCheckup getCountAll(String bulan, String tahun, String branch) {
        HeaderCheckup headerCheckup = new HeaderCheckup();
        try {
            headerCheckup = headerCheckupDao.getCountAll(bulan, tahun, branch);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return headerCheckup;
    }

    @Override
    public List<HeaderCheckup> getKunjunganRJ(String bulan, String tahun, String branchId) {
        List<HeaderCheckup> headerCheckup = new ArrayList<>();
        try {
            headerCheckup = headerCheckupDao.getKunjunganRJ(bulan, tahun, branchId);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return headerCheckup;
    }

    @Override
    public List<HeaderCheckup> getDetailKunjunganRJ(String bulan, String tahun, String branchId) {
        List<HeaderCheckup> headerCheckup = new ArrayList<>();
        try {
            headerCheckup = headerCheckupDao.getDetailKunjunganRJ(bulan, tahun, branchId);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return headerCheckup;
    }

    @Override
    public List<HeaderCheckup> getTahunPeriksa() {
        List<HeaderCheckup> headerCheckup = new ArrayList<>();
        try {
            headerCheckup = headerCheckupDao.getTahunPeriksa();
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return headerCheckup;
    }

    @Override
    public List<HeaderCheckup> getKamarTerpakai(String bulan, String tahun, String branchId) throws GeneralBOException {
        List<HeaderCheckup> headerCheckup = new ArrayList<>();
        try {
            headerCheckup = headerCheckupDao.getKamarTerpakai(bulan, tahun, branchId);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return headerCheckup;
    }

    @Override
    public List<HeaderCheckup> getDetailKunjunganJK(String bulan, String tahun, String branchId) throws GeneralBOException {
        List<HeaderCheckup> headerCheckup = new ArrayList<>();
        try {
            headerCheckup = headerCheckupDao.getDetailKunjunganJK(bulan, tahun, branchId);
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
