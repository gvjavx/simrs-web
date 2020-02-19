package com.neurix.simrs.transaksi.verifikator.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.impl.TransaksiObatBoImpl;
import com.neurix.simrs.transaksi.verifikator.bo.VerifikatorBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VerifikatorBoImpl implements VerifikatorBo {

    private static transient Logger logger = Logger.getLogger(VerifikatorBoImpl.class);
    private TindakanRawatDao tindakanRawatDao;
    private HeaderCheckupDao checkupDao;
    private CheckupDetailDao checkupDetailDao;
    private RiwayatTindakanDao riwayatTindakanDao;
    private RawatInapDao rawatInapDao;

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setCheckupDao(HeaderCheckupDao checkupDao) {
        this.checkupDao = checkupDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    @Override
    public CheckResponse updateApproveBpjsFlag(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.updateApproveBpjsFlag] START process <<<");
        CheckResponse response = new CheckResponse();
        if(bean != null){

            ItSimrsRiwayatTindakanEntity entity = new ItSimrsRiwayatTindakanEntity();
            try {
                entity = riwayatTindakanDao.getById("idRiwayatTindakan", bean.getIdRiwayatTindakan());
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
                    riwayatTindakanDao.updateAndSave(entity);
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

    @Override
    public CheckResponse updateKlaimBpjsFlag(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.updateKlaimBpjsFlag] START process <<<");
        CheckResponse response = new CheckResponse();
        if(bean != null){

            ItSimrsHeaderDetailCheckupEntity entity = new ItSimrsHeaderDetailCheckupEntity();
            try {
                entity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
            }catch (HibernateException e){
                logger.error("[VerifikatorBoImpl.updateKlaimBpjsFlag] Error when update data flag approve tindakan rawat ", e);
            }

            if(entity != null){

                entity.setKlaimBpjsFlag("Y");
                entity.setAction("U");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    checkupDetailDao.updateAndSave(entity);
                    response.setStatus("200");
                    response.setMessage("Berhasil mengubah flag bpjs flag klaim!");
                }catch (HibernateException e){
                    logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when save update data flag approve tindakan rawat ", e);
                    response.setStatus("400");
                    response.setMessage("Terjadi kesalahan saat menyimpan ke database : "+e.getMessage());
                }
            }
        }
        logger.info("[VerifikatorBoImpl.updateKlaimBpjsFlag] END process <<<");
        return response;
    }

    @Override
    public List<HeaderDetailCheckup> getListVerifikasiRawatJalan(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.getListVerifikasiRawatJalan] START process <<<");
        List<HeaderDetailCheckup> result = new ArrayList<>();
        if(bean != null){
            try {
                result = checkupDetailDao.getSearchVerifikasiRawatJalan(bean);
            }catch (HibernateException e){
                logger.error("[VerifikatorBoImpl.getListVerifikasiRawatJalan] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[VerifikatorBoImpl.getListVerifikasiRawatJalan] END process <<<");
        return result;
    }

    @Override
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.getListAllTindakan] START process <<<");
        List<RiwayatTindakan> result = new ArrayList<>();
        if(bean != null){
            try {
                result = riwayatTindakanDao.getListTindakan(bean);
            }catch (HibernateException e){
                logger.error("[VerifikatorBoImpl.getListVerifikasiRawatJalan] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[VerifikatorBoImpl.getListAllTindakan] END process <<<");
        return result;
    }

    @Override
    public CheckResponse updateFlagKlaim(RiwayatTindakan bean) throws GeneralBOException {

        logger.info("[VerifikatorBoImpl.updateFlagKlaim] START process <<<");
        CheckResponse response = new CheckResponse();
        if(bean != null){
            ItSimrsRiwayatTindakanEntity entity = new ItSimrsRiwayatTindakanEntity();
            try {
                entity = riwayatTindakanDao.getById("idRiwayatTindakan", bean.getIdRiwayatTindakan());
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("Foun error"+e.getMessage());
                logger.error("[VerifikatorBoImpl.getListVerifikasiRawatJalan] Error when search data tindakan rawat ", e);
            }

            if(entity != null){

                entity.setFlagUpdateKlaim("Y");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    riwayatTindakanDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMessage("Berhasil mengupdate flag update klaim");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMessage("Foun error"+e.getMessage());
                    logger.error("[VerifikatorBoImpl.getListVerifikasiRawatJalan] Error when save update data flag klaim tindakan rawat ");
                }
            }
        }

        logger.info("[VerifikatorBoImpl.updateFlagKlaim] END process <<<");
        return response;
    }

    @Override
    public List<RawatInap> getListVerifikasiRawatInap(RawatInap bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.getListVerifikasiRawatInap] START process <<<");
        List<RawatInap> result = new ArrayList<>();
        if(bean != null){
            try {
                result = rawatInapDao.getSearchVerifikasiRawatInap(bean);
            }catch (HibernateException e){
                logger.error("[VerifikatorBoImpl.getListVerifikasiRawatInap] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[VerifikatorBoImpl.getListVerifikasiRawatInap] END process <<<");
        return result;
    }
}