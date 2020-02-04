package com.neurix.simrs.transaksi.verifikator.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.impl.TransaksiObatBoImpl;
import com.neurix.simrs.transaksi.verifikator.bo.VerifikatorBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

public class VerifikatorBoImpl implements VerifikatorBo {

    private static transient Logger logger = Logger.getLogger(VerifikatorBoImpl.class);
    private TindakanRawatDao tindakanRawatDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    @Override
    public void updateApproveBpjsFlag(TindakanRawat bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.updateApproveBpjsFlag] START process <<<");
        if(bean != null){

            ItSimrsTindakanRawatEntity entity = new ItSimrsTindakanRawatEntity();
            try {
                entity = tindakanRawatDao.getById("idTindakanRawat", bean.getIdTindakan());
            }catch (HibernateException e){
                logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when update data flag approve tindakan rawat ", e);
            }

            if(entity != null){

                entity.setApproveBpjsFlag("Y");
                entity.setAction("U");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    tindakanRawatDao.updateAndSave(entity);
                }catch (HibernateException e){
                    logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when save update data flag approve tindakan rawat ", e);
                }
            }
        }
        logger.info("[VerifikatorBoImpl.updateApproveBpjsFlag] END process <<<");

    }

    @Override
    public void saveApproveTindakan(TindakanRawat bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.saveApproveTindakan] START process <<<");

        if(bean != null){

            try {

            }catch (HibernateException e){
                logger.error("[VerifikatorBoImpl.saveApproveTindakan] Error when search detail eklaim", e);
            }


        }

        logger.info("[VerifikatorBoImpl.saveApproveTindakan] END process <<<");
    }
}