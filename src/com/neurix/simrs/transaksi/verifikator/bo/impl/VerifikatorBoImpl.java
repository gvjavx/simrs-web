package com.neurix.simrs.transaksi.verifikator.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
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
    public CheckResponse updateApproveBpjsFlag(TindakanRawat bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.updateApproveBpjsFlag] START process <<<");
        CheckResponse response = new CheckResponse();
        if(bean != null){

            ItSimrsTindakanRawatEntity entity = new ItSimrsTindakanRawatEntity();
            try {
                entity = tindakanRawatDao.getById("idTindakanRawat", bean.getIdTindakan());
            }catch (HibernateException e){
                logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when update data flag approve tindakan rawat ", e);
            }

            if(entity != null){

                entity.setApproveBpjsFlag("Y");
                entity.setKategoriTindakanBpjs(bean.getKategoriTindakanBpjs());
                entity.setAction("U");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    tindakanRawatDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMessage("Berhasil menyimpan kategori tindakan BPJS!");
                }catch (HibernateException e){
                    logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when save update data flag approve tindakan rawat ", e);
                    response.setStatus("error");
                    response.setMessage("Terjadi kesalahan saat menyimpan ke database : "+e.getMessage());
                }
            }
        }
        logger.info("[VerifikatorBoImpl.updateApproveBpjsFlag] END process <<<");
        return response;
    }
}