package com.neurix.simrs.transaksi.verifikatorasuransi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
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

    @Override
    public void saveApproveAsuransi(StrukAsuransi bean) throws GeneralBOException {

    }

    private String generateIdStrukAsuransi(String branchId) throws GeneralBOException {
        return "STA"+branchId+strukAsuransiDao.getNextSeq();
    }
}
