package com.neurix.simrs.transaksi.periksalab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.periksalab.bo.OrderPeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.dao.OrderPeriksaLabDao;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsOrderPeriksaLabEntity;
import com.neurix.simrs.transaksi.periksalab.model.OrderPeriksaLab;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderPeriksaLabBoImpl implements OrderPeriksaLabBo {
    private static transient Logger logger = Logger.getLogger(OrderPeriksaLabBoImpl.class);
    private OrderPeriksaLabDao orderPeriksaLabDao;

    @Override
    public List<OrderPeriksaLab> getByCriteria(OrderPeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getByCriteria] START >>>>>>>>> ");

        List<OrderPeriksaLab> periksaLabList = new ArrayList<>();
        if (bean != null) {
            List<ItSimrsOrderPeriksaLabEntity> periksaLabEntities = getListEntityPeriksaLab(bean);

            if (!periksaLabEntities.isEmpty() && periksaLabEntities.size() > 0) {
                for (ItSimrsOrderPeriksaLabEntity periksaLabEntity : periksaLabEntities) {
                    OrderPeriksaLab orderPeriksaLab = new OrderPeriksaLab();
                    orderPeriksaLab.setIdOrderPeriksa(periksaLabEntity.getIdOrderPeriksa());
                    orderPeriksaLab.setIdDetailCheckup(periksaLabEntity.getIdDetailCheckup());
                    orderPeriksaLab.setIdPemeriksaan(periksaLabEntity.getIdPemeriksaan());
                    orderPeriksaLab.setNamaPemeriksaan(periksaLabEntity.getNamaPemeriksaan());
                    orderPeriksaLab.setIdLabDetail(periksaLabEntity.getIdLabDetail());
                    orderPeriksaLab.setNamaDetailPemeriksaan(periksaLabEntity.getNamaDetailPemeriksaan());
                    orderPeriksaLab.setIdKategoriLab(periksaLabEntity.getIdKategoriLab());
                    orderPeriksaLab.setKeterangan(periksaLabEntity.getKeterangan());
                    orderPeriksaLab.setIsPemeriksaan(periksaLabEntity.getIsPemeriksaan());
                    orderPeriksaLab.setFlag(periksaLabEntity.getFlag());
                    orderPeriksaLab.setAction(periksaLabEntity.getAction());
                    orderPeriksaLab.setCreatedDate(periksaLabEntity.getCreatedDate());
                    orderPeriksaLab.setCreatedWho(periksaLabEntity.getCreatedWho());
                    orderPeriksaLab.setLastUpdate(periksaLabEntity.getLastUpdate());
                    orderPeriksaLab.setLastUpdateWho(periksaLabEntity.getLastUpdateWho());
                    periksaLabList.add(orderPeriksaLab);
                }
            }
        }

        logger.info("[PeriksaLabBoImpl.getByCriteria] END <<<<<<<<< ");
        return periksaLabList;
    }

    @Override
    public CrudResponse saveAdd(List<OrderPeriksaLab> list) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveAdd] START >>>>>>>>> ");
        CrudResponse response = new CrudResponse();
        if(list.size() > 0){
            for (OrderPeriksaLab bean: list){
                ItSimrsOrderPeriksaLabEntity entity = new ItSimrsOrderPeriksaLabEntity();
                entity.setIdOrderPeriksa("OPL" + orderPeriksaLabDao.getNextId());
                entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                entity.setIdPemeriksaan(bean.getIdPemeriksaan());
                entity.setNamaPemeriksaan(bean.getNamaPemeriksaan());
                entity.setIdLabDetail(bean.getIdLabDetail());
                entity.setNamaDetailPemeriksaan(bean.getNamaDetailPemeriksaan());
                entity.setIdKategoriLab(bean.getIdKategoriLab());
                entity.setKeterangan(bean.getKeterangan());
                entity.setIsPemeriksaan(bean.getIsPemeriksaan());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    orderPeriksaLabDao.addAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Berhasil");
                    logger.error("[PeriksaLabBoImpl.saveAdd] ERROR when saving data periksa lab ", e.getCause());
                    throw new GeneralBOException("[PeriksaLabBoImpl.saveAdd] ERROR when saving data periksa lab " + e.getCause());
                }
            }
        }else{
            response.setStatus("error");
            response.setMsg("Berhasil");
        }
        logger.info("[PeriksaLabBoImpl.saveAdd] END <<<<<<<<< ");
        return response;
    }

    private List<ItSimrsOrderPeriksaLabEntity> getListEntityPeriksaLab(OrderPeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getListEntityPeriksaLab] START >>>>>>>>> ");

        Map hsCriteria = new HashMap();
        if (bean.getIdOrderPeriksa() != null && !"".equalsIgnoreCase(bean.getIdOrderPeriksa())) {
            hsCriteria.put("id_order_periksa", bean.getIdOrderPeriksa());
        }

        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        if (bean.getIdPemeriksaan() != null && !"".equalsIgnoreCase(bean.getIdPemeriksaan())) {
            hsCriteria.put("id_lab", bean.getIdPemeriksaan());
        }

        if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
            hsCriteria.put("keterangan", bean.getKeterangan());
        }

        hsCriteria.put("flag", "Y");
        List<ItSimrsOrderPeriksaLabEntity> periksaLabEntities = new ArrayList<>();
        try {
            periksaLabEntities = orderPeriksaLabDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab ", e);
            throw new GeneralBOException("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab " + e.getCause());
        }

        logger.info("[PeriksaLabBoImpl.getListEntityPeriksaLab] END <<<<<<<<< ");
        return periksaLabEntities;
    }

    public void setOrderPeriksaLabDao(OrderPeriksaLabDao orderPeriksaLabDao) {
        this.orderPeriksaLabDao = orderPeriksaLabDao;
    }
}
