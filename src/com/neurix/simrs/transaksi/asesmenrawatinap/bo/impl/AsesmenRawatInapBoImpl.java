package com.neurix.simrs.transaksi.asesmenrawatinap.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.bo.AsesmenRawatInapBo;
import com.neurix.simrs.transaksi.asesmenrawatinap.dao.AsesmenRawatInapDao;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.AsesmenRawatInap;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ItSimrsAsesmenRawatInapEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsesmenRawatInapBoImpl implements AsesmenRawatInapBo {
    private static transient Logger logger = Logger.getLogger(AsesmenRawatInapBoImpl.class);
    private AsesmenRawatInapDao asesmenRawatInapDao;

    @Override
    public List<AsesmenRawatInap> getByCriteria(AsesmenRawatInap bean) throws GeneralBOException {
        List<AsesmenRawatInap> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdAsesmenKeperawatanRawatInap() != null && !"".equalsIgnoreCase(bean.getIdAsesmenKeperawatanRawatInap())) {
                hsCriteria.put("id_asesmen_keperawatan_rawat_inap", bean.getIdAsesmenKeperawatanRawatInap());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsAsesmenRawatInapEntity> entityList = new ArrayList<>();

            try {
                entityList = asesmenRawatInapDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsAsesmenRawatInapEntity entity : entityList) {
                    AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
                    asesmenRawatInap.setIdAsesmenKeperawatanRawatInap(entity.getIdAsesmenKeperawatanRawatInap());
                    asesmenRawatInap.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenRawatInap.setParameter(entity.getParameter());
                    if ("ttd_early_warning_score".equalsIgnoreCase(entity.getJenis()) || "ttd_privasi".equalsIgnoreCase(entity.getJenis()) || "ttd_rencana_gigi".equalsIgnoreCase(entity.getJenis())) {
                        asesmenRawatInap.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban());
                    } else {
                        asesmenRawatInap.setJawaban(entity.getJawaban());
                    }
                    asesmenRawatInap.setKeterangan(entity.getKeterangan());
                    asesmenRawatInap.setJenis(entity.getJenis());
                    asesmenRawatInap.setSkor(entity.getSkor());
                    asesmenRawatInap.setAction(entity.getAction());
                    asesmenRawatInap.setFlag(entity.getFlag());
                    asesmenRawatInap.setCreatedDate(entity.getCreatedDate());
                    asesmenRawatInap.setCreatedWho(entity.getCreatedWho());
                    asesmenRawatInap.setLastUpdate(entity.getLastUpdate());
                    asesmenRawatInap.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(asesmenRawatInap);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<AsesmenRawatInap> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            for (AsesmenRawatInap bean : list) {
                ItSimrsAsesmenRawatInapEntity asesmenRawatInapEntity = new ItSimrsAsesmenRawatInapEntity();
                asesmenRawatInapEntity.setIdAsesmenKeperawatanRawatInap("ARI" + asesmenRawatInapDao.getNextSeq());
                asesmenRawatInapEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                asesmenRawatInapEntity.setParameter(bean.getParameter());
                asesmenRawatInapEntity.setJawaban(bean.getJawaban());
                asesmenRawatInapEntity.setKeterangan(bean.getKeterangan());
                asesmenRawatInapEntity.setJenis(bean.getJenis());
                asesmenRawatInapEntity.setSkor(bean.getSkor());
                asesmenRawatInapEntity.setAction(bean.getAction());
                asesmenRawatInapEntity.setFlag(bean.getFlag());
                asesmenRawatInapEntity.setCreatedDate(bean.getCreatedDate());
                asesmenRawatInapEntity.setCreatedWho(bean.getCreatedWho());
                asesmenRawatInapEntity.setLastUpdate(bean.getLastUpdate());
                asesmenRawatInapEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    asesmenRawatInapDao.addAndSave(asesmenRawatInapEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setAsesmenRawatInapDao(AsesmenRawatInapDao asesmenRawatInapDao) {
        this.asesmenRawatInapDao = asesmenRawatInapDao;
    }
}
