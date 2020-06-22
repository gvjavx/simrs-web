package com.neurix.simrs.transaksi.asesmenicu.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenicu.bo.AsesmenIcuBo;
import com.neurix.simrs.transaksi.asesmenicu.dao.AsesmenIcuDao;
import com.neurix.simrs.transaksi.asesmenicu.model.AsesmenIcu;
import com.neurix.simrs.transaksi.asesmenicu.model.ItSimrsAsesmenIcuEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsesmenIcuBoImpl implements AsesmenIcuBo {
    private static transient Logger logger = Logger.getLogger(AsesmenIcuBoImpl.class);
    private AsesmenIcuDao asesmenIcuDao;

    @Override
    public List<AsesmenIcu> getByCriteria(AsesmenIcu bean) throws GeneralBOException {
        List<AsesmenIcu> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdAsesmenIcu() != null && !"".equalsIgnoreCase(bean.getIdAsesmenIcu())) {
                hsCriteria.put("id_asesmen_icu", bean.getIdAsesmenIcu());
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

            List<ItSimrsAsesmenIcuEntity> entityList = new ArrayList<>();

            try {
                entityList = asesmenIcuDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsAsesmenIcuEntity entity : entityList) {
                    AsesmenIcu asesmenIcu = new AsesmenIcu();
                    asesmenIcu.setIdAsesmenIcu(entity.getIdAsesmenIcu());
                    asesmenIcu.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenIcu.setParameter(entity.getParameter());

                    if ("gambar".equalsIgnoreCase(entity.getTipe()) || "ttd".equalsIgnoreCase(entity.getTipe())) {
                        if ("gambar".equalsIgnoreCase(entity.getTipe())) {
                            asesmenIcu.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_IMG_RM + entity.getJawaban());
                        } else {
                            asesmenIcu.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban());
                        }
                    } else {
                        asesmenIcu.setJawaban(entity.getJawaban());
                    }

                    asesmenIcu.setKeterangan(entity.getKeterangan());
                    asesmenIcu.setJenis(entity.getJenis());
                    asesmenIcu.setScore(entity.getScore());
                    asesmenIcu.setAction(entity.getAction());
                    asesmenIcu.setFlag(entity.getFlag());
                    asesmenIcu.setCreatedDate(entity.getCreatedDate());
                    asesmenIcu.setCreatedWho(entity.getCreatedWho());
                    asesmenIcu.setLastUpdate(entity.getLastUpdate());
                    asesmenIcu.setLastUpdateWho(entity.getLastUpdateWho());
                    asesmenIcu.setTipe(entity.getTipe());
                    list.add(asesmenIcu);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<AsesmenIcu> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            for (AsesmenIcu bean : list) {

                ItSimrsAsesmenIcuEntity asesmenIcuEntity = new ItSimrsAsesmenIcuEntity();
                asesmenIcuEntity.setIdAsesmenIcu("ICU" + asesmenIcuDao.getNextSeq());
                asesmenIcuEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                asesmenIcuEntity.setParameter(bean.getParameter());
                asesmenIcuEntity.setJawaban(bean.getJawaban());
                asesmenIcuEntity.setKeterangan(bean.getKeterangan());
                asesmenIcuEntity.setJenis(bean.getJenis());
                asesmenIcuEntity.setScore(bean.getScore());
                asesmenIcuEntity.setAction(bean.getAction());
                asesmenIcuEntity.setFlag(bean.getFlag());
                asesmenIcuEntity.setCreatedDate(bean.getCreatedDate());
                asesmenIcuEntity.setCreatedWho(bean.getCreatedWho());
                asesmenIcuEntity.setLastUpdate(bean.getLastUpdate());
                asesmenIcuEntity.setLastUpdateWho(bean.getLastUpdateWho());
                asesmenIcuEntity.setTipe(bean.getTipe());

                try {
                    asesmenIcuDao.addAndSave(asesmenIcuEntity);
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

    public void setAsesmenIcuDao(AsesmenIcuDao asesmenIcuDao) {
        this.asesmenIcuDao = asesmenIcuDao;
    }
}
