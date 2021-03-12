package com.neurix.simrs.transaksi.ordergizi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dietgizi.dao.DietGiziDao;
import com.neurix.simrs.master.dietgizi.dao.JenisDietDao;
import com.neurix.simrs.master.dietgizi.model.ImSimrsDietGizi;
import com.neurix.simrs.master.dietgizi.model.ImSimrsJenisDietEntity;
import com.neurix.simrs.master.dietgizi.model.JenisDiet;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.icu.model.HeaderIcu;
import com.neurix.simrs.transaksi.obatinap.dao.ObatInapDao;
import com.neurix.simrs.transaksi.obatinap.model.ItSimrsObatInapEntity;
import com.neurix.simrs.transaksi.obatinap.model.ObatInap;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.dao.OrderGiziDao;
import com.neurix.simrs.transaksi.ordergizi.dao.OrderJenisDietDao;
import com.neurix.simrs.transaksi.ordergizi.model.DetailJenisDiet;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsDetailJenisDietEntity;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsOrderGiziEntity;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.*;

public class OrderGiziBoImpl implements OrderGiziBo {
    private static transient Logger logger = Logger.getLogger(OrderGiziBoImpl.class);
    private OrderGiziDao orderGiziDao;
    private OrderJenisDietDao orderJenisDietDao;
    private JenisDietDao jenisDietDao;
    private DietGiziDao dietGiziDao;

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
    public CheckResponse saveAdd(List<OrderGizi> list, String isTomorrow) throws GeneralBOException {
        logger.info("[OrderGiziBoImpl.saveAdd] Start >>>>>>>");
        CheckResponse response = new CheckResponse();
        if (list.size() > 0){
            if("1".equalsIgnoreCase(isTomorrow) || "12".equalsIgnoreCase(isTomorrow)){
                for (OrderGizi bean: list){
                    ItSimrsOrderGiziEntity orderGiziEntity = new ItSimrsOrderGiziEntity();
                    orderGiziEntity.setIdOrderGizi("ODG" + orderGiziDao.getNextId());
                    orderGiziEntity.setIdRawatInap(bean.getIdRawatInap());
                    orderGiziEntity.setTglOrder(bean.getTglOrder());
                    orderGiziEntity.setFlag(bean.getFlag());
                    orderGiziEntity.setAction(bean.getAction());
                    orderGiziEntity.setCreatedDate(bean.getCreatedDate());
                    orderGiziEntity.setCreatedWho(bean.getCreatedWho());
                    orderGiziEntity.setLastUpdate(bean.getLastUpdate());
                    orderGiziEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    orderGiziEntity.setTarifTotal(bean.getTarifTotal());
                    orderGiziEntity.setIdDietGizi(bean.getIdDietGizi());
                    orderGiziEntity.setWaktu(bean.getWaktu());
                    orderGiziEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    orderGiziEntity.setApproveFlag("P");
                    orderGiziEntity.setDiterimaFlag("P");

                    HashMap hsCrite = new HashMap();
                    ImSimrsDietGizi dietEntity = new ImSimrsDietGizi();
                    List<ImSimrsDietGizi> dietGiziList = new ArrayList<>();
                    hsCrite.put("id_diet_gizi", bean.getIdDietGizi());
                    try {
                        dietGiziList = dietGiziDao.getByCriteria(hsCrite);
                    }catch (HibernateException e){
                        logger.error(e.getMessage());
                    }

                    if(dietGiziList.size() > 0){
                        dietEntity = dietGiziList.get(0);
                    }
                    orderGiziEntity.setBentukDiet(dietEntity.getNamaDietGizi());
                    orderGiziEntity.setTarifTotal(dietEntity.getTarif());

                    try {
                        orderGiziDao.addAndSave(orderGiziEntity);
                        response.setStatus("success");
                        response.setMessage("Berhasil menyimpan data order gizi");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Foun Error, "+e.getMessage());
                        logger.error("[OrderGiziBoImpl.saveAdd] Error when insert obat inap ", e);
                    }

                    try {
                        orderGiziDao.addAndSave(orderGiziEntity);
                        response.setStatus("success");
                        response.setMessage("Berhasil menyimpan data order gizi");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Foun Error, "+e.getMessage());
                        logger.error("[OrderGiziBoImpl.saveAdd] Error when insert obat inap ", e);
                    }

                    for (String jenis: bean.getListJenisGizi()){
                        ItSimrsDetailJenisDietEntity jenisDietEntity = new ItSimrsDetailJenisDietEntity();
                        jenisDietEntity.setIdDetailJenisDiet("DJD"+orderJenisDietDao.getNextId());
                        jenisDietEntity.setIdOrderGizi(orderGiziEntity.getIdOrderGizi());
                        jenisDietEntity.setIdJenisDiet(jenis);

                        HashMap hsCriteria = new HashMap();
                        ImSimrsJenisDietEntity jenisD = new ImSimrsJenisDietEntity();
                        List<ImSimrsJenisDietEntity> jenisDietEntityList = new ArrayList<>();
                        hsCriteria.put("id_jenis_diet", jenis);
                        try {
                            jenisDietEntityList = jenisDietDao.getByCriteria(hsCriteria);
                        }catch (HibernateException e){
                            logger.error(e.getMessage());
                        }
                        if(jenisDietEntityList.size() > 0){
                            jenisD = jenisDietEntityList.get(0);
                        }
                        jenisDietEntity.setNamaJenisDiet(jenisD.getNamaJenisDiet());
                        jenisDietEntity.setFlag(bean.getFlag());
                        jenisDietEntity.setAction(bean.getAction());
                        jenisDietEntity.setCreatedDate(bean.getCreatedDate());
                        jenisDietEntity.setCreatedWho(bean.getCreatedWho());
                        jenisDietEntity.setLastUpdate(bean.getLastUpdate());
                        jenisDietEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {
                            orderJenisDietDao.addAndSave(jenisDietEntity);
                            response.setStatus("success");
                            response.setMessage("Berhasil menyimpan data order gizi");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Foun Error, "+e.getMessage());
                            logger.error("[OrderGiziBoImpl.saveAdd] Error when insert obat inap ", e);
                        }
                    }
                }
            }
            if("2".equalsIgnoreCase(isTomorrow) || "12".equalsIgnoreCase(isTomorrow)){
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, 1);
                Timestamp tomorrow = new Timestamp(cal.getTime().getTime());

                for (OrderGizi bean: list){
                    ItSimrsOrderGiziEntity orderGiziEntity = new ItSimrsOrderGiziEntity();
                    orderGiziEntity.setIdOrderGizi("ODG" + orderGiziDao.getNextId());
                    orderGiziEntity.setIdRawatInap(bean.getIdRawatInap());
                    orderGiziEntity.setTglOrder(tomorrow);
                    orderGiziEntity.setFlag(bean.getFlag());
                    orderGiziEntity.setAction(bean.getAction());
                    orderGiziEntity.setCreatedDate(bean.getCreatedDate());
                    orderGiziEntity.setCreatedWho(bean.getCreatedWho());
                    orderGiziEntity.setLastUpdate(bean.getLastUpdate());
                    orderGiziEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    orderGiziEntity.setTarifTotal(bean.getTarifTotal());
                    orderGiziEntity.setIdDietGizi(bean.getIdDietGizi());
                    orderGiziEntity.setWaktu(bean.getWaktu());
                    orderGiziEntity.setIdDetailCheckup(bean.getIdDetailCheckup());

                    HashMap hsCrite = new HashMap();
                    ImSimrsDietGizi dietEntity = new ImSimrsDietGizi();
                    List<ImSimrsDietGizi> dietGiziList = new ArrayList<>();
                    hsCrite.put("id_diet_gizi", bean.getIdDietGizi());
                    try {
                        dietGiziList = dietGiziDao.getByCriteria(hsCrite);
                    }catch (HibernateException e){
                        logger.error(e.getMessage());
                    }

                    if(dietGiziList.size() > 0){
                        dietEntity = dietGiziList.get(0);
                    }
                    orderGiziEntity.setBentukDiet(dietEntity.getNamaDietGizi());
                    orderGiziEntity.setTarifTotal(dietEntity.getTarif());

                    try {
                        orderGiziDao.addAndSave(orderGiziEntity);
                        response.setStatus("success");
                        response.setMessage("Berhasil menyimpan data order gizi");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Foun Error, "+e.getMessage());
                        logger.error("[OrderGiziBoImpl.saveAdd] Error when insert obat inap ", e);
                    }

                    for (String jenis: bean.getListJenisGizi()){
                        ItSimrsDetailJenisDietEntity jenisDietEntity = new ItSimrsDetailJenisDietEntity();
                        jenisDietEntity.setIdDetailJenisDiet("DJD"+orderJenisDietDao.getNextId());
                        jenisDietEntity.setIdOrderGizi(orderGiziEntity.getIdOrderGizi());
                        jenisDietEntity.setIdJenisDiet(jenis);

                        HashMap hsCriteria = new HashMap();
                        ImSimrsJenisDietEntity jenisD = new ImSimrsJenisDietEntity();
                        List<ImSimrsJenisDietEntity> jenisDietEntityList = new ArrayList<>();
                        hsCriteria.put("id_jenis_diet", jenis);
                        try {
                            jenisDietEntityList = jenisDietDao.getByCriteria(hsCriteria);
                        }catch (HibernateException e){
                            logger.error(e.getMessage());
                        }
                        if(jenisDietEntityList.size() > 0){
                            jenisD = jenisDietEntityList.get(0);
                        }
                        jenisDietEntity.setNamaJenisDiet(jenisD.getNamaJenisDiet());
                        jenisDietEntity.setFlag(bean.getFlag());
                        jenisDietEntity.setAction(bean.getAction());
                        jenisDietEntity.setCreatedDate(bean.getCreatedDate());
                        jenisDietEntity.setCreatedWho(bean.getCreatedWho());
                        jenisDietEntity.setLastUpdate(bean.getLastUpdate());
                        jenisDietEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {
                            orderJenisDietDao.addAndSave(jenisDietEntity);
                            response.setStatus("success");
                            response.setMessage("Berhasil menyimpan data order gizi");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Foun Error, "+e.getMessage());
                            logger.error("[OrderGiziBoImpl.saveAdd] Error when insert obat inap ", e);
                        }
                    }
                }
            }
        }
        logger.info("[OrderGiziBoImpl.saveAdd] End <<<<<<");
        return response;
    }

    @Override
    public CheckResponse saveEdit(OrderGizi bean, List<String> list) throws GeneralBOException {
        logger.info("[OrderGiziBoImpl.saveEdit] Start >>>>>>>");
        CheckResponse response = new CheckResponse();
        if (list.size() > 0 && bean != null){
            ItSimrsOrderGiziEntity orderGiziEntity = null;
            try {
                orderGiziEntity = orderGiziDao.getById("idOrderGizi", bean.getIdOrderGizi());
                response.setStatus("success");
                response.setMessage("Berhasil mencari order gizi");
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("Found error when dao gizi save update "+e.getMessage());
                logger.error("[OrderGiziBoImpl.saveEdit] Error when insert obat inap ", e);
                logger.error("[OrderGiziBoImpl.saveEdit] Error when getById order gizi ",e);
                throw new GeneralBOException("[OrderGiziBoImpl.savaaEdit] Error when save edit order gizi "+e.getMessage());
            }

            if (bean != null) {

                orderGiziEntity.setAction(bean.getAction());
                orderGiziEntity.setLastUpdate(bean.getLastUpdate());
                orderGiziEntity.setLastUpdateWho(bean.getLastUpdateWho());
                orderGiziEntity.setIdDietGizi(bean.getIdDietGizi());

                HashMap hsCrite = new HashMap();
                ImSimrsDietGizi dietEntity = new ImSimrsDietGizi();
                List<ImSimrsDietGizi> dietGiziList = new ArrayList<>();
                hsCrite.put("id_diet_gizi", bean.getIdDietGizi());
                try {
                    dietGiziList = dietGiziDao.getByCriteria(hsCrite);
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                }

                if(dietGiziList.size() > 0){
                    dietEntity = dietGiziList.get(0);
                }
                orderGiziEntity.setBentukDiet(dietEntity.getNamaDietGizi());
                orderGiziEntity.setTarifTotal(dietEntity.getTarif());

                try {
                    orderGiziDao.updateAndSave(orderGiziEntity);
                    response.setStatus("success");
                    response.setMessage("Berhasil menyimpan order gizi");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Found error when dao gizi save update "+e.getMessage());
                    logger.error("[OrderGiziBoImpl.saveEdit] Error when insert obat inap ", e);
                    throw new GeneralBOException("[OrderGiziBoImpl.saveAdd] Error when edit order gizi " + e.getMessage());
                }

                List<ItSimrsDetailJenisDietEntity> detailJenisDietEntityList = new ArrayList<>();
                HashMap hsCt = new HashMap();
                hsCt.put("id_order_gizi", orderGiziEntity.getIdOrderGizi());
                try {
                    detailJenisDietEntityList = orderJenisDietDao.getByCriteria(hsCt);
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                }

                if(detailJenisDietEntityList.size() > 0){
                    for (ItSimrsDetailJenisDietEntity entity: detailJenisDietEntityList){
                        entity.setFlag("N");
                        entity.setAction("D");
                        entity.setLastUpdate(bean.getLastUpdate());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {
                            orderJenisDietDao.updateAndSave(entity);
                        }catch (HibernateException e){
                            logger.error(e.getMessage());
                        }
                    }
                    for (String jenis: list){
                        ItSimrsDetailJenisDietEntity jenisDietEntity = new ItSimrsDetailJenisDietEntity();
                        jenisDietEntity.setIdDetailJenisDiet("DJD"+orderJenisDietDao.getNextId());
                        jenisDietEntity.setIdOrderGizi(orderGiziEntity.getIdOrderGizi());
                        jenisDietEntity.setIdJenisDiet(jenis);

                        HashMap hsCriteria = new HashMap();
                        ImSimrsJenisDietEntity jenisD = new ImSimrsJenisDietEntity();
                        List<ImSimrsJenisDietEntity> jenisDietEntityList = new ArrayList<>();
                        hsCriteria.put("id_jenis_diet", jenis);
                        try {
                            jenisDietEntityList = jenisDietDao.getByCriteria(hsCriteria);
                        }catch (HibernateException e){
                            logger.error(e.getMessage());
                        }
                        if(jenisDietEntityList.size() > 0){
                            jenisD = jenisDietEntityList.get(0);
                        }
                        jenisDietEntity.setNamaJenisDiet(jenisD.getNamaJenisDiet());
                        jenisDietEntity.setFlag(bean.getFlag());
                        jenisDietEntity.setAction(bean.getAction());
                        jenisDietEntity.setCreatedDate(bean.getLastUpdate());
                        jenisDietEntity.setCreatedWho(bean.getLastUpdateWho());
                        jenisDietEntity.setLastUpdate(bean.getLastUpdate());
                        jenisDietEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            orderJenisDietDao.addAndSave(jenisDietEntity);
                            response.setStatus("success");
                            response.setMessage("Berhasil menyimpan data order gizi");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Foun Error, "+e.getMessage());
                            logger.error("[OrderGiziBoImpl.saveAdd] Error when insert obat inap ", e);
                        }
                    }
                }
            }
        }
        logger.info("[OrderGiziBoImpl.saveEdit] End <<<<<<");
        return response;
    }

    @Override
    public CheckResponse updateDiterimaFLag(OrderGizi bean) throws GeneralBOException {
        logger.info("[OrderGiziBoImpl.updateDiterimaFLag] Start >>>>>>>");
        CheckResponse response = new CheckResponse();
        if (bean != null){

            ItSimrsOrderGiziEntity orderGiziEntity = new ItSimrsOrderGiziEntity();
            try {
                orderGiziEntity = orderGiziDao.getById("idOrderGizi", bean.getIdOrderGizi());
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("Found Error when search order gizi "+e.getMessage());
                logger.error("[OrderGiziBoImpl.updateDiterimaFLag] Error when getById order gizi ",e);
                throw new GeneralBOException("[OrderGiziBoImpl.updateDiterimaFLag] Error when save edit order gizi "+e.getMessage());
            }

            if (bean != null) {

                orderGiziEntity.setDiterimaFlag("Y");
                orderGiziEntity.setLastUpdate(bean.getLastUpdate());
                orderGiziEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    orderGiziDao.updateAndSave(orderGiziEntity);
                    response.setStatus("success");
                    response.setMessage("Berhasil mngkonfirmasi pesanan gizi...!");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Found Error when update order gizi "+e.getMessage());
                    logger.error("[OrderGiziBoImpl.updateDiterimaFLag] Error when insert obat inap ", e);
                    throw new GeneralBOException("[TindakanRawatBoImpl.updateDiterimaFLag] Error when edit order gizi " + e.getMessage());
                }
            }
        }
        logger.info("[OrderGiziBoImpl.updateDiterimaFLag] End <<<<<<");
        return response;
    }

    @Override
    public CheckResponse cancelOrderGizi(OrderGizi bean) throws GeneralBOException {
        logger.info("[OrderGiziBoImpl.cancelOrderGizi] Start >>>>>>>");
        CheckResponse response = new CheckResponse();
        if (bean != null){

            ItSimrsOrderGiziEntity orderGiziEntity = new ItSimrsOrderGiziEntity();

            try {
                orderGiziEntity = orderGiziDao.getById("idOrderGizi", bean.getIdOrderGizi());
                response.setStatus("success");
                response.setMessage("Berhasil mencari pesanan gizi...!");
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("Found Error when search order gizi "+e.getMessage());
                logger.error("[OrderGiziBoImpl.updateDiterimaFLag] Error when getById order gizi ",e);
                throw new GeneralBOException("[OrderGiziBoImpl.updateDiterimaFLag] Error when save edit order gizi "+e.getMessage());
            }

            if (bean != null) {

                orderGiziEntity.setDiterimaFlag("R");
                orderGiziEntity.setLastUpdate(bean.getLastUpdate());
                orderGiziEntity.setLastUpdateWho(bean.getLastUpdateWho());
                orderGiziEntity.setKeterangan(bean.getKeterangan());

                try {
                    orderGiziDao.updateAndSave(orderGiziEntity);
                    response.setStatus("success");
                    response.setMessage("Berhasil membatalkan pesanan gizi...!");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Found Error when update order gizi "+e.getMessage());
                    logger.error("[OrderGiziBoImpl.cancelOrderGizi] Error when insert obat inap ", e);
                    throw new GeneralBOException("[OrderGiziBoImpl.cancelOrderGizi] Error when edit order gizi " + e.getMessage());
                }
            }
        }
        logger.info("[OrderGiziBoImpl.updateDiterimaFLag] End <<<<<<");
        return response;
    }

    @Override
    public List<DetailJenisDiet> getByCriteriaJenisDiet(DetailJenisDiet bean) throws GeneralBOException {
        List<ItSimrsDetailJenisDietEntity> results = new ArrayList<>();
        List<DetailJenisDiet> detailJenisDiets = new ArrayList<>();
        Map hsCriteria = new HashMap();
        if (bean.getIdDetailJenisDiet() != null && !"".equalsIgnoreCase(bean.getIdDetailJenisDiet())){
            hsCriteria.put("id_jenis_diet", bean.getIdDetailJenisDiet());
        }
        if (bean.getIdOrderGizi() != null && !"".equalsIgnoreCase(bean.getIdOrderGizi())){
            hsCriteria.put("id_order_gizi", bean.getIdOrderGizi());
        }
        hsCriteria.put("flag","Y");
        try {
            results = orderJenisDietDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[OrderGiziBoImpl.getListEntity] Erro when searching data obat inap ", e);
        }
        if(results.size() > 0){
            for (ItSimrsDetailJenisDietEntity jenisDiet: results){
                DetailJenisDiet detailJenisDiet = new DetailJenisDiet();
                detailJenisDiet.setIdDetailJenisDiet(jenisDiet.getIdDetailJenisDiet());
                detailJenisDiet.setIdOrderGizi(jenisDiet.getIdOrderGizi());
                detailJenisDiet.setIdJenisDiet(jenisDiet.getIdJenisDiet());
                detailJenisDiet.setNamaJenisDiet(jenisDiet.getNamaJenisDiet());
                detailJenisDiet.setAction(jenisDiet.getAction());
                detailJenisDiet.setFlag(jenisDiet.getFlag());
                detailJenisDiet.setCreatedDate(jenisDiet.getCreatedDate());
                detailJenisDiet.setCreatedWho(jenisDiet.getCreatedWho());
                detailJenisDiet.setLastUpdate(jenisDiet.getLastUpdate());
                detailJenisDiet.setLastUpdateWho(jenisDiet.getLastUpdateWho());
                detailJenisDiets.add(detailJenisDiet);
            }
        }

        return detailJenisDiets;
    }

    @Override
    public List<OrderGizi> cekOrderGizi(String id, String waktu, String type, String when) throws GeneralBOException {
        List<OrderGizi> orderGiziList = new ArrayList<>();
        try {
            orderGiziList = orderGiziDao.cekOrderGiziToday(id, waktu, type, when);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return orderGiziList;
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
        if (bean.getDiterimaFlag() != null && !"".equalsIgnoreCase(bean.getDiterimaFlag())){
            hsCriteria.put("diterima_flag", bean.getDiterimaFlag());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
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
            orderGizi.setFlag(entity.getFlag());
            orderGizi.setAction(entity.getAction());
            orderGizi.setCreatedDate(entity.getCreatedDate());
            orderGizi.setCreatedWho(entity.getCreatedWho());
            orderGizi.setLastUpdate(entity.getLastUpdate());
            orderGizi.setLastUpdateWho(entity.getLastUpdateWho());
            orderGizi.setApproveFlag(entity.getApproveFlag());
            orderGizi.setDiterimaFlag(entity.getDiterimaFlag());
            orderGizi.setTarifTotal(entity.getTarifTotal());
            orderGizi.setBentukDiet(entity.getBentukDiet());
            orderGizi.setIdDietGizi(entity.getIdDietGizi());
            orderGizi.setBentukDiet(entity.getBentukDiet());
            orderGizi.setKeterangan(entity.getKeterangan());
            orderGizi.setWaktu(entity.getWaktu());
            HashMap hsCriteria = new HashMap();
            List<ItSimrsDetailJenisDietEntity> dietEntityList = new ArrayList<>();
            hsCriteria.put("id_order_gizi", entity.getIdOrderGizi());
            hsCriteria.put("flag", "Y");
            try {
                dietEntityList = orderJenisDietDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
            String jenis = "";
            if(dietEntityList.size() > 0){
                for (ItSimrsDetailJenisDietEntity dietEntity: dietEntityList){
                    if(dietEntity.getNamaJenisDiet() != null){
                        if(!"".equalsIgnoreCase(jenis)){
                            jenis = jenis + "<span style=\"margin-left: 3px\" class=\"span-biru\">"+dietEntity.getNamaJenisDiet()+"</span>";
                        }else{
                            jenis = "<span class=\"span-biru\">"+dietEntity.getNamaJenisDiet()+"</span>";
                        }
                    }
                }
            }
            orderGizi.setJenisDiet(jenis);
            results.add(orderGizi);
        }
        logger.info("[OrderGiziBoImpl.setToTemplate] End <<<<<<");
        return results;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setOrderGiziDao(OrderGiziDao orderGiziDao) {
        this.orderGiziDao = orderGiziDao;
    }

    public void setOrderJenisDietDao(OrderJenisDietDao orderJenisDietDao) {
        this.orderJenisDietDao = orderJenisDietDao;
    }

    public void setJenisDietDao(JenisDietDao jenisDietDao) {
        this.jenisDietDao = jenisDietDao;
    }

    public void setDietGiziDao(DietGiziDao dietGiziDao) {
        this.dietGiziDao = dietGiziDao;
    }
}

