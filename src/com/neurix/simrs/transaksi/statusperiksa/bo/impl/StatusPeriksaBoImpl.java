package com.neurix.simrs.transaksi.statusperiksa.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.statusperiksa.action.StatusPeriksaAction;
import com.neurix.simrs.transaksi.statusperiksa.bo.StatusPeriksaBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class StatusPeriksaBoImpl implements StatusPeriksaBo {

    public static transient Logger logger = Logger.getLogger(StatusPeriksaBoImpl.class);
    private CheckupDetailDao checkupDetailDao;
    private HeaderCheckupDao headerCheckupDao;

    @Override
    public List<HeaderDetailCheckup> getListStatusPeriksa(HeaderDetailCheckup headerDetailCheckup) throws GeneralBOException {
        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

        if (headerDetailCheckup != null) {
            try {
                detailCheckupList = checkupDetailDao.getStatusPeriksa(headerDetailCheckup);
            } catch (HibernateException e) {
                logger.error("Found Error when search status periksa " + e.getMessage());
            }
        }

        return detailCheckupList;
    }

    @Override
    public CheckResponse saveEditPerubahanStatus(HeaderDetailCheckup bean) throws GeneralBOException {
        CheckResponse response = new CheckResponse();
        if (bean != null) {

            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {

                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
                ItSimrsHeaderChekupEntity headerChekupEntity = new ItSimrsHeaderChekupEntity();

                try {

                    headerChekupEntity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());

                    if(headerChekupEntity != null){

                        headerChekupEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());
                        headerChekupEntity.setJenisTransaksi(bean.getIdJenisPeriksaPasien());
                        headerChekupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        headerChekupEntity.setLastUpdate(bean.getLastUpdate());

                        try {
                            headerCheckupDao.updateAndSave(headerChekupEntity);
                        }catch (HibernateException e){
                            logger.error("Found Error when save update header "+e.getMessage());
                        }
                    }

                    detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());

                    if(detailCheckupEntity != null){

                        detailCheckupEntity.setNoSep(bean.getNoSep());
                        detailCheckupEntity.setKodeCbg(bean.getKodeCbg());
                        detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
                        detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                        detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            checkupDetailDao.updateAndSave(detailCheckupEntity);
                        }catch (HibernateException e){
                            logger.error("Found Error when save checkup detail "+e.getMessage());
                        }
                    }

                }catch (HibernateException e){
                    logger.error("Found Error when search id detail checkup "+e.getMessage());
                }
            }


        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }
}
