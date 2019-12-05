package com.neurix.simrs.transaksi.ordergizi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.obatinap.dao.ObatInapDao;
import com.neurix.simrs.transaksi.obatinap.model.ItSimrsObatInapEntity;
import com.neurix.simrs.transaksi.obatinap.model.ObatInap;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.dao.OrderGiziDao;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsOrderGiziEntity;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderGiziBoImpl implements OrderGiziBo {
    private static transient Logger logger = Logger.getLogger(OrderGiziBoImpl.class);
    private OrderGiziDao orderGiziDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setOrderGiziDao(OrderGiziDao orderGiziDao) {
        this.orderGiziDao = orderGiziDao;
    }

    @Override
    public List<OrderGizi> getByCriteria(OrderGizi bean) throws GeneralBOException {
        logger.info("[OrderGiziBoImpl.getByCriteria] Start >>>>>>>");

        List<OrderGizi> results = new ArrayList<>();

        if (bean != null){
            List<ItSimrsOrderGiziEntity> orderGiziEntityList = getListEntity(bean);
            if (!orderGiziEntityList.isEmpty()){
                results = setToTemplate(orderGiziEntityList);
            }
        }

        logger.info("[OrderGiziBoImpl.getByCriteria] End <<<<<<");
        return results;
    }

    @Override
    public void saveAdd(OrderGizi bean) throws GeneralBOException {
        logger.info("[OrderGiziBoImpl.saveAdd] Start >>>>>>>");

        if (bean != null){
            String id = getNextId();
            if (id != null && !"".equalsIgnoreCase(id)) {
                ItSimrsOrderGiziEntity orderGiziEntity = new ItSimrsOrderGiziEntity();
                orderGiziEntity.setIdOrderGizi("ODG" + id);
                orderGiziEntity.setIdRawatInap(bean.getIdRawatInap());
                orderGiziEntity.setTglOrder(bean.getTglOrder());
                orderGiziEntity.setDietPagi(bean.getDietPagi());
                orderGiziEntity.setBentukMakanPagi(bean.getBentukMakanPagi());
                orderGiziEntity.setDietSiang(bean.getDietSiang());
                orderGiziEntity.setBentukMakanSiang(bean.getBentukMakanSiang());
                orderGiziEntity.setDietMalam(bean.getDietMalam());
                orderGiziEntity.setBentukMakanMalam(bean.getBentukMakanMalam());
                orderGiziEntity.setFlag(bean.getFlag());
                orderGiziEntity.setAction(bean.getAction());
                orderGiziEntity.setCreatedDate(bean.getCreatedDate());
                orderGiziEntity.setCreatedWho(bean.getCreatedWho());
                orderGiziEntity.setLastUpdate(bean.getLastUpdate());
                orderGiziEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    orderGiziDao.addAndSave(orderGiziEntity);
                } catch (HibernateException e) {
                    logger.error("[OrderGiziBoImpl.saveAdd] Error when insert obat inap ", e);
                    throw new GeneralBOException("[TindakanRawatBoImpl.saveAdd] Error when insert obat inap " + e.getMessage());
                }
            }
        }
        logger.info("[OrderGiziBoImpl.saveAdd] End <<<<<<");
    }

    @Override
    public void saveEdit(OrderGizi bean) throws GeneralBOException {
        logger.info("[OrderGiziBoImpl.saveEdit] Start >>>>>>>");
        if (bean != null){

            ItSimrsOrderGiziEntity orderGiziEntity = null;
            try {
                orderGiziEntity = orderGiziDao.getById("idOrderGizi", bean.getIdOrderGizi());
            } catch (HibernateException e){
                logger.error("[TeamDokterBoImpl.saveEdit] Error when getById order gizi ",e);
                throw new GeneralBOException("[TeamDokterBoImpl.savaaEdit] Error when save edit order gizi "+e.getMessage());
            }

            if (bean != null) {
                orderGiziEntity.setIdRawatInap(bean.getIdRawatInap());
                orderGiziEntity.setTglOrder(bean.getTglOrder());
                orderGiziEntity.setDietPagi(bean.getDietPagi());
                orderGiziEntity.setBentukMakanPagi(bean.getBentukMakanPagi());
                orderGiziEntity.setDietSiang(bean.getDietSiang());
                orderGiziEntity.setBentukMakanSiang(bean.getBentukMakanSiang());
                orderGiziEntity.setDietMalam(bean.getDietMalam());
                orderGiziEntity.setBentukMakanMalam(bean.getBentukMakanMalam());
                orderGiziEntity.setAction(bean.getAction());
                orderGiziEntity.setLastUpdate(bean.getLastUpdate());
                orderGiziEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    orderGiziDao.updateAndSave(orderGiziEntity);
                } catch (HibernateException e) {
                    logger.error("[OrderGiziBoImpl.saveEdit] Error when insert obat inap ", e);
                    throw new GeneralBOException("[TindakanRawatBoImpl.saveAdd] Error when edit order gizi " + e.getMessage());
                }
            }
        }
        logger.info("[OrderGiziBoImpl.saveEdit] End <<<<<<");
    }

    public String getNextId(){
        logger.info("[OrderGiziBoImpl.getNextId] Start >>>>>>>");

        String id = "";
        try {
            id = orderGiziDao.getNextId();
        } catch (HibernateException e){
            logger.error("[OrderGiziBoImpl.getNextId] Error when get next id obat inap");
        }

        logger.info("[OrderGiziBoImpl.getNextId] End <<<<<<");
        return id;
    }

    protected List<ItSimrsOrderGiziEntity> getListEntity(OrderGizi bean) throws GeneralBOException{
        logger.info("[OrderGiziBoImpl.getListEntity] Start >>>>>>>");
        List<ItSimrsOrderGiziEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdOrderGizi() != null && !"".equalsIgnoreCase(bean.getIdOrderGizi())){
            hsCriteria.put("id_order_gizi", bean.getIdOrderGizi());
        }
        if (bean.getIdRawatInap() != null && !"".equalsIgnoreCase(bean.getIdRawatInap())){
            hsCriteria.put("id_rawat_inap", bean.getIdRawatInap());
        }

        hsCriteria.put("flag","Y");
        try {
            results = orderGiziDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[OrderGiziBoImpl.getListEntity] Erro when searching data obat inap ", e);
        }

        logger.info("[OrderGiziBoImpl.getListEntityT] End <<<<<<");
        return results;
    }

    protected List<OrderGizi> setToTemplate(List<ItSimrsOrderGiziEntity> entities) throws GeneralBOException{
        logger.info("[OrderGiziBoImpl.setToTemplate] Start >>>>>>>");
        List<OrderGizi> results = new ArrayList<>();

        OrderGizi orderGizi;
        for (ItSimrsOrderGiziEntity entity : entities){

            orderGizi = new OrderGizi();
            orderGizi.setIdOrderGizi(entity.getIdOrderGizi());
            orderGizi.setIdRawatInap(entity.getIdRawatInap());
            orderGizi.setTglOrder(entity.getTglOrder());
            orderGizi.setDietPagi(entity.getDietPagi());
            orderGizi.setBentukMakanPagi(entity.getBentukMakanPagi());
            orderGizi.setDietSiang(entity.getDietSiang());
            orderGizi.setBentukMakanSiang(entity.getBentukMakanSiang());
            orderGizi.setDietMalam(entity.getDietMalam());
            orderGizi.setBentukMakanMalam(entity.getBentukMakanMalam());
            orderGizi.setFlag(entity.getFlag());
            orderGizi.setAction(entity.getAction());
            orderGizi.setCreatedDate(entity.getCreatedDate());
            orderGizi.setCreatedWho(entity.getCreatedWho());
            orderGizi.setLastUpdate(entity.getLastUpdate());
            orderGizi.setLastUpdateWho(entity.getLastUpdateWho());


            results.add(orderGizi);
        }
        logger.info("[OrderGiziBoImpl.setToTemplate] End <<<<<<");
        return results;
    }
}

