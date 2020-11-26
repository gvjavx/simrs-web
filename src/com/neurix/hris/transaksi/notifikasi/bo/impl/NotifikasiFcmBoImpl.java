package com.neurix.hris.transaksi.notifikasi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiFcmDao;
import com.neurix.hris.transaksi.notifikasi.model.ItNotifikasiFcmEntity;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.*;

public class NotifikasiFcmBoImpl implements NotifikasiFcmBo {
    protected static transient Logger logger = Logger.getLogger(NotifikasiFcmBoImpl.class);

    private NotifikasiFcmDao notifikasiFcmDao;

    public void setNotifikasiFcmDao(NotifikasiFcmDao notifikasiFcmDao) {
        this.notifikasiFcmDao = notifikasiFcmDao;
    }

    @Override
    public void saveDelete(NotifikasiFcm bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(NotifikasiFcm bean) throws GeneralBOException {

    }

    @Override
    public NotifikasiFcm saveAdd(NotifikasiFcm bean) throws GeneralBOException {
        logger.info("[NotifikasiFcmBoImpl.saveAdd] start process >>>");

        if (bean != null) {
            ItNotifikasiFcmEntity fcm = new ItNotifikasiFcmEntity();

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            fcm.setUserId(bean.getUserId());
            fcm.setTokenFcm(bean.getTokenFcm());
            fcm.setTokenExpo(bean.getTokenExpo());
            fcm.setOs(bean.getOs());
            fcm.setUserName(bean.getUserName());
            fcm.setCreatedWho(bean.getCreatedWho());
            fcm.setCreatedDate(updateTime);
            fcm.setLastUpdateWho(bean.getLastUpdateWho());
            fcm.setLastUpdate(updateTime);
            fcm.setAction("C");
            fcm.setFlag("Y");

            try {
                notifikasiFcmDao.updateAndSave(fcm);
            } catch (HibernateException e) {
                logger.error("[NotifikasiFcmBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data area, please info to your admin..." + e.getMessage());
            }

        }

        logger.info("[NotifikasiFcmBoImpl.saveAdd] end process <<<");

        return bean;
    }

    @Override
    public List<NotifikasiFcm> getByCriteria(NotifikasiFcm searchBean) throws GeneralBOException {

        List<ItNotifikasiFcmEntity> listOfResult = new ArrayList<>();
        List<NotifikasiFcm> returnNotifFcm = new ArrayList<>();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getUserId() != null && !searchBean.getUserId().equalsIgnoreCase("")) {
                hsCriteria.put("user_id", searchBean.getUserId());
            }

            try {
               listOfResult = notifikasiFcmDao.getByCriteria(hsCriteria);
            } catch (GeneralBOException e) {
                logger.error("[NotifikasiFcmBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getByCriteria, please info to your admin..." + e.getMessage());
            }

            for (ItNotifikasiFcmEntity item : listOfResult) {
                NotifikasiFcm notifikasiFcm = new NotifikasiFcm();
                notifikasiFcm.setUserId(item.getUserId());
                notifikasiFcm.setUserName(item.getUserName());
                notifikasiFcm.setTokenFcm(item.getTokenFcm());
                notifikasiFcm.setOs(item.getOs());

                returnNotifFcm.add(notifikasiFcm);
            }

        }


        return returnNotifFcm;
    }

    @Override
    public List<NotifikasiFcm> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
