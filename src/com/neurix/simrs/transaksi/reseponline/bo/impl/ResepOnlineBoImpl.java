package com.neurix.simrs.transaksi.reseponline.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.reseponline.bo.ResepOnlineBo;
import com.neurix.simrs.transaksi.reseponline.dao.PengirimanObatDao;
import org.apache.log4j.Logger;
import com.neurix.simrs.transaksi.reseponline.dao.ResepOnlineDao;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsResepOnlineEntity;
import com.neurix.simrs.transaksi.reseponline.model.ResepOnline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gondok
 * Wednesday, 17/06/20 9:55
 */
public class ResepOnlineBoImpl implements ResepOnlineBo {
    protected static transient Logger logger = Logger.getLogger(ResepOnlineBoImpl.class);

    private ResepOnlineDao resepOnlineDao;
    private PengirimanObatDao pengirimanObatDao;

    public void setPengirimanObatDao(PengirimanObatDao pengirimanObatDao) {
        this.pengirimanObatDao = pengirimanObatDao;
    }

    public ResepOnlineDao getResepOnlineDao() {
        return resepOnlineDao;
    }

    public void setResepOnlineDao(ResepOnlineDao resepOnlineDao) {
        this.resepOnlineDao = resepOnlineDao;
    }

    @Override
    public List<ResepOnline> getByCriteria(ResepOnline bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.getByCriteria] Start >>>>>>>");

        List<ResepOnline> resepOnlines = new ArrayList<>();
        if (bean != null) {
            List<ItSimrsResepOnlineEntity> itSimrsResepOnlineEntities = getEntityByCriteria(bean);

            if (!itSimrsResepOnlineEntities.isEmpty()) {
                resepOnlines = setTemplateResepOnline(itSimrsResepOnlineEntities);
            }
        }

        logger.info("[ResepOnlineBoImpl.getByCriteria] End >>>>>>>");
        return resepOnlines;
    }

    public List<ItSimrsResepOnlineEntity> getEntityByCriteria(ResepOnline bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.getEntityByCriteria] Start >>>>>>>");
        List<ItSimrsResepOnlineEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())){
            hsCriteria.put("id", bean.getId());
        }
        if (bean.getIdTransaksiOnline() != null && !"".equalsIgnoreCase(bean.getIdTransaksiOnline())) {
            hsCriteria.put("id_transaksi_online", bean.getIdTransaksiOnline());
        }

        List<ItSimrsResepOnlineEntity> itSimrsResepOnlineEntities = null;
        try {
            itSimrsResepOnlineEntities = resepOnlineDao.getByCriteria(hsCriteria);
        } catch (GeneralBOException e) {
            logger.error("[ResepOnlineBoImpl.getEntityByCriteria] Error when search entity by criteria " + e.getMessage());
        }

        if (!itSimrsResepOnlineEntities.isEmpty()) {
            results = itSimrsResepOnlineEntities;
        }

        logger.info("[ResepOnlineBoImpl.getEntityByCriteria] End >>>>>>>");
        return results;
    }

    public List<ResepOnline> setTemplateResepOnline(List<ItSimrsResepOnlineEntity> listEntity) {
        logger.info("[ResepOnlineBoImpl.setTemplateKurir] Start >>>>>>>");
        List<ResepOnline> list = new ArrayList<>();

        ResepOnline resepOnline;
        for (ItSimrsResepOnlineEntity data : listEntity) {
            resepOnline = new ResepOnline();
            resepOnline.setId(data.getId());
            resepOnline.setIdTransaksiOnline(data.getIdTransaksiOnline());
            resepOnline.setIdPelayanan(data.getIdPelayanan());
            resepOnline.setIdObat(data.getIdObat());
            resepOnline.setIdDokter(data.getIdDokter());
            resepOnline.setQty(data.getQty());
            resepOnline.setHarga(data.getHarga());
            resepOnline.setSubTotal(data.getSubTotal());
            resepOnline.setTtdDokter(data.getTtdDokter());
            resepOnline.setAction(data.getAction());
            resepOnline.setFlag(data.getFlag());
            resepOnline.setCreatedDate(data.getCreatedDate());
            resepOnline.setCreatedWho(data.getCreatedWho());
            resepOnline.setLastUpdate(data.getLastUpdate());
            resepOnline.setLastUpdateWho(data.getLastUpdateWho());

            list.add(resepOnline);
        }

        logger.info("[ResepOnlineBoImpl.setTemplateKurir] End >>>>>>>");
        return list;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    private String getNextIdPengiriman(String branchId){
        logger.info("[ResepOnlineBoImpl.getNextIdPengiriman] END <<<");
        return branchId + CommonUtil.stDateSeq() + pengirimanObatDao.getNextSeq();
    }
}
