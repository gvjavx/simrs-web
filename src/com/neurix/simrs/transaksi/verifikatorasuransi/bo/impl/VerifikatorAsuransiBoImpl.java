package com.neurix.simrs.transaksi.verifikatorasuransi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.verifikatorasuransi.bo.VerifikatorAsurasiBo;
import com.neurix.simrs.transaksi.verifikatorasuransi.dao.StrukAsuransiDao;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.ItSimrsStrukAsuransiEntity;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.StrukAsuransi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 17/07/20.
 */
public class VerifikatorAsuransiBoImpl implements VerifikatorAsurasiBo{
    private static transient Logger logger = Logger.getLogger(VerifikatorAsuransiBoImpl.class);
    private StrukAsuransiDao strukAsuransiDao;
    private CheckupDetailDao checkupDetailDao;
    private HeaderCheckupDao headerCheckupDao;
    private PasienDao pasienDao;

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setStrukAsuransiDao(StrukAsuransiDao strukAsuransiDao) {
        this.strukAsuransiDao = strukAsuransiDao;
    }


    @Override
    public List<StrukAsuransi> getSearchByCriteria(StrukAsuransi bean) throws GeneralBOException {
        logger.info("[VerifikatorAsuransiBoImpl.getSearchByCriteria] START >>>");

        List<ItSimrsStrukAsuransiEntity> asuransiEntities = getListStrukAsurasiEntity(bean);
        if (asuransiEntities.size() > 0){

            StrukAsuransi strukAsuransi;
            for (ItSimrsStrukAsuransiEntity simrsStrukAsuransiEntity : asuransiEntities){
                strukAsuransi = new StrukAsuransi();
            }
        }

        logger.info("[VerifikatorAsuransiBoImpl.getSearchByCriteria] END <<<");
        return null;
    }

    @Override
    public List<ItSimrsStrukAsuransiEntity> getListStrukAsurasiEntity(StrukAsuransi bean) throws GeneralBOException {
        logger.info("[VerifikatorAsuransiBoImpl.getListStrukAsurasiEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup()))
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        if (bean.getApproveFlag() != null && !"".equalsIgnoreCase(bean.getApproveFlag()))
            hsCriteria.put("approve_flag", bean.getApproveFlag());
        if (bean.getApproveFlagNull() != null && !"".equalsIgnoreCase(bean.getApproveFlagNull()))
            hsCriteria.put("approve_flag_null", bean.getApproveFlagNull());
        if (bean.getFlag() != null)
            hsCriteria.put("flag", bean.getFlag());
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId()))
            hsCriteria.put("branch_id", bean.getBranchId());
        if (bean.getIdAntrianTelemedic() != null)
            hsCriteria.put("id_antrian_telemedic", bean.getIdAntrianTelemedic());

        List<ItSimrsStrukAsuransiEntity> strukAsuransiEntities = new ArrayList<>();

        try {
            strukAsuransiEntities = strukAsuransiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[VerifikatorAsuransiBoImpl.getListStrukAsurasiEntity] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorAsuransiBoImpl.getListStrukAsurasiEntity] ERROR. "+ e);
        }

        logger.info("[VerifikatorAsuransiBoImpl.getListStrukAsurasiEntity] END <<<");
        return strukAsuransiEntities;
    }

    private ItSimrsStrukAsuransiEntity getStrukAsuransiEntityById(String id) throws GeneralBOException{
        return strukAsuransiDao.getById("id", id);
    }

    @Override
    public void saveUploadStrukAsuransi(StrukAsuransi bean) throws GeneralBOException {
        logger.info("[VerifikatorAsuransiBoImpl.saveUploadStrukAsuransi] START >>>");

        ItSimrsStrukAsuransiEntity strukAsuransiEntity = getStrukAsuransiEntityById(bean.getId());
        if (strukAsuransiEntity != null){

            strukAsuransiEntity.setUrlFotoStruk(bean.getUrlFotoStruk());
            strukAsuransiEntity.setLastUpdate(bean.getLastUpdate());
            strukAsuransiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            strukAsuransiEntity.setAction("U");

            try {
                strukAsuransiDao.updateAndSave(strukAsuransiEntity);
            } catch (HibernateException e){
                logger.error("[VerifikatorAsuransiBoImpl.saveUploadStrukAsuransi] ERROR. ", e);
                throw new GeneralBOException("[VerifikatorAsuransiBoImpl.saveUploadStrukAsuransi] ERROR. "+ e);
            }

            if ("confirmation".equalsIgnoreCase(strukAsuransiEntity.getJenis())){
                Map hsCriteria = new HashMap();
                hsCriteria.put("id_antrian_online", strukAsuransiEntity.getIdAntrianTelemedic());
                List<ItSimrsHeaderChekupEntity> chekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
                ItSimrsHeaderChekupEntity chekupEntity = chekupEntities.size() > 0 ? chekupEntities.get(0) : null;
                if (chekupEntity != null){
                    hsCriteria = new HashMap();
                    hsCriteria.put("no_checkup", chekupEntity.getNoCheckup());
                    List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = checkupDetailDao.getByCriteria(hsCriteria);
                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = detailCheckupEntities.size() > 0 ? detailCheckupEntities.get(0) : null;
                    if (detailCheckupEntity != null){

                        detailCheckupEntity.setCoverBiaya(bean.getJumlahCover());
                        detailCheckupEntity.setDibayarPasien(bean.getDibayarPasien());

                        try {
                            checkupDetailDao.updateAndSave(detailCheckupEntity);
                        } catch (HibernateException e){
                            logger.error("[VerifikatorAsuransiBoImpl.saveUploadStrukAsuransi] ERROR. When Update Detail Checkup ", e);
                            throw new GeneralBOException("[VerifikatorAsuransiBoImpl.saveUploadStrukAsuransi] ERROR. When Update Detail Checkup "+ e);
                        }
                    }
                }
            }
        }
        logger.info("[VerifikatorAsuransiBoImpl.saveUploadStrukAsuransi] END <<<");
    }

    private String generateIdStrukAsuransi(String branchId) throws GeneralBOException {
        return "STA"+branchId+strukAsuransiDao.getNextSeq();
    }
}
