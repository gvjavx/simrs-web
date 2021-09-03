package com.neurix.simrs.transaksi.permintaangizi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.ordergizi.dao.OrderGiziDao;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsOrderGiziEntity;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.permintaangizi.action.PermintaanGiziAction;
import com.neurix.simrs.transaksi.permintaangizi.bo.PermintaanGiziBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class PermintaanGiziBoImpl implements PermintaanGiziBo {

    protected static transient Logger logger = Logger.getLogger(PermintaanGiziBoImpl.class);
    private OrderGiziDao orderGiziDao;

    @Override
    public List<RawatInap> getListOrderGizi(RawatInap bean) throws GeneralBOException {
        logger.info("[PermintaanGiziBoImpl.getListOrderGizi] start process >>>");
        List<RawatInap> rawatInapList = new ArrayList<>();

        if(bean != null){
            try {
                rawatInapList = orderGiziDao.getSearchOrderGizi(bean);
            }catch (HibernateException e){
                logger.error("[PermintaanGiziBoImpl.getListOrderGizi], Found Error, when search order gizi");
            }
        }
        logger.info("[PermintaanGiziBoImpl.getListOrderGizi] start process >>>");
        return rawatInapList;
    }

    @Override
    public CheckResponse updateApproveFlag(OrderGizi orderGizi) throws GeneralBOException {
        CheckResponse response = new CheckResponse();
        if(orderGizi != null){

            ItSimrsOrderGiziEntity entity = new ItSimrsOrderGiziEntity();

            try {
                entity = orderGiziDao.getById("idOrderGizi", orderGizi.getIdOrderGizi());

                if(entity != null){

                    entity.setApproveFlag("Y");
                    entity.setLastUpdate(orderGizi.getLastUpdate());
                    entity.setLastUpdateWho(orderGizi.getLastUpdateWho());

                    try {
                        orderGiziDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMessage("Berhasil mengkonfirmasi orderan gizi...!");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMessage("Found Error when update ["+e.getMessage()+"]");
                        logger.error("[PermintaanGiziBoimpl] foun error when update order gizi "+e.getMessage());
                    }
                }
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("Found Error when search with id ["+e.getMessage()+"]");
                logger.error("[PermintaanGiziBoImpl] Foun error when search order gizi by id "+e.getMessage());
            }

        }
        return response;
    }

    @Override
    public CrudResponse updateGizi(List<OrderGizi> orderGizi) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(orderGizi.size() > 0){
            for (OrderGizi bean: orderGizi){
                ItSimrsOrderGiziEntity entity = new ItSimrsOrderGiziEntity();
                try {
                    entity = orderGiziDao.getById("idOrderGizi", bean.getIdOrderGizi());
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("");
                    logger.error(e.getMessage());
                }
                if(entity != null){
                    entity.setApproveFlag(bean.getApproveFlag());
                    if(null!=bean.getDiterimaFlag() && "N".equalsIgnoreCase(bean.getApproveFlag()))
                    { entity.setDiterimaFlag(bean.getDiterimaFlag()); }
                    entity.setKeterangan(bean.getKeterangan());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    try {
                        orderGiziDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMsg("berhasil");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setOrderGiziDao(OrderGiziDao orderGiziDao) {
        this.orderGiziDao = orderGiziDao;
    }
}
