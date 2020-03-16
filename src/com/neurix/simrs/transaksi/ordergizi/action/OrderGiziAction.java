package com.neurix.simrs.transaksi.ordergizi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dietgizi.bo.DietGiziBo;
import com.neurix.simrs.master.dietgizi.model.DietGizi;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import groovy.lang.ObjectRange;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderGiziAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(OrderGiziAction.class);
    private OrderGiziBo orderGiziBoProxy;
    private OrderGizi orderGizi;

    public static Logger getLogger() {
        return logger;
    }

    public void setOrderGiziBoProxy(OrderGiziBo orderGiziBoProxy) {
        this.orderGiziBoProxy = orderGiziBoProxy;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public CheckResponse saveOrderGizi(String idRawatInap, String bentukDiet, String keterangan) throws JSONException {
        logger.info("[OrderGiziAction.saveOrderGizi] start process >>>");
        CheckResponse response = new CheckResponse();
        try {

            long millis = System.currentTimeMillis();
            java.util.Date date = new java.util.Date(millis);
            String tglToday = new SimpleDateFormat("yyyy-MM-dd").format(date);
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            DietGiziBo dietGiziBo = (DietGiziBo) ctx.getBean("dietGiziBoProxy");

            OrderGizi orderGizi = new OrderGizi();

            List<OrderGizi> orderGiziList = new ArrayList<>();
            orderGizi.setIdRawatInap(idRawatInap);
            orderGizi.setCreatedWho(userLogin);
            orderGizi.setTglOrder(Date.valueOf(tglToday));
            orderGizi.setLastUpdate(updateTime);
            orderGizi.setCreatedDate(updateTime);
            orderGizi.setLastUpdateWho(userLogin);
            orderGizi.setAction("C");
            orderGizi.setFlag("Y");

            List<DietGizi> giziList = new ArrayList<>();
            DietGizi dietGizi = new DietGizi();
            dietGizi.setIdDietGizi(bentukDiet);
            dietGizi.setFlag("Y");

            try {
                giziList = dietGiziBo.getByCriteria(dietGizi);
            } catch (GeneralBOException e) {
                logger.error("[OrderGiziAction] Found Error, " + e);
            }

            if (giziList.size() > 0) {
                dietGizi = giziList.get(0);
                orderGizi.setIdDietGizi(dietGizi.getIdDietGizi());
                orderGizi.setKeterangan(keterangan);
                orderGizi.setBentukDiet(dietGizi.getNamaDietGizi());
                orderGizi.setTarifTotal(dietGizi.getTarif());

                response = orderGiziBo.saveAdd(orderGizi);

            }else{
                response.setStatus("error");
                response.setMessage("Tidak dapat menemukan id diet gizi, Please Infor to your admin...!");
            }

        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMessage("Found Error :" + e.getMessage());
            logger.error("[OrderGiziAction.saveOrderGizi] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
        }

        logger.info("[OrderGiziAction.saveOrderGizi] end process >>>");
        return response;
    }

    public List<OrderGizi> listOrderGizi(String idRawatInap) {

        logger.info("[OrderGiziAction.listOrderGizi] start process >>>");
        List<OrderGizi> orderGiziList = new ArrayList<>();

        OrderGizi orderGizi = new OrderGizi();
        orderGizi.setIdRawatInap(idRawatInap);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");

        if (!"".equalsIgnoreCase(idRawatInap)) {
            try {
                orderGiziList = orderGiziBo.getByCriteria(orderGizi);
            } catch (GeneralBOException e) {
                logger.info("[OrderGiziAction.listOrderGizi] error when search list of obat inap, " + e.getMessage());
            }
            logger.info("[OrderGiziAction.listOrderGizi] end process >>>");
            return orderGiziList;

        } else {
            return null;
        }
    }

    public CheckResponse editOrderGizi(String idOrdeGizi, String idDietGizi) {
        logger.info("[OrderGiziAction.editOrderGizi] start process >>>");
        CheckResponse response = new CheckResponse();
        try {

            long millis = System.currentTimeMillis();
            java.util.Date date = new java.util.Date(millis);
            String tglToday = new SimpleDateFormat("yyyy-MM-dd").format(date);
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            DietGiziBo dietGiziBo = (DietGiziBo) ctx.getBean("dietGiziBoProxy");

            OrderGizi orderGizi = new OrderGizi();

            DietGizi dietGizi = new DietGizi();
            dietGizi.setIdDietGizi(idDietGizi);

            List<DietGizi> giziList = new ArrayList<>();

            try {
                giziList = dietGiziBo.getByCriteria(dietGizi);
            } catch (GeneralBOException e) {
                logger.error("[OrderGiziAction] Found Error, " + e);
            }

            if (giziList.size() > 0) {

                dietGizi = giziList.get(0);

                orderGizi.setIdOrderGizi(idOrdeGizi);
                orderGizi.setLastUpdate(updateTime);
                orderGizi.setLastUpdateWho(userLogin);
                orderGizi.setAction("U");
                orderGizi.setIdDietGizi(dietGizi.getIdDietGizi());
                orderGizi.setBentukDiet(dietGizi.getNamaDietGizi());
                orderGizi.setTarifTotal(dietGizi.getTarif());

                response = orderGiziBo.saveEdit(orderGizi);

            }else{
                response.setStatus("error");
                response.setMessage("Tidak dapat menemukan id diet gizi, Please Infor to your admin...!");
            }

        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMessage("Foun Error when save edit order gizi :" + e.getMessage());
            logger.error("[OrderGiziAction.editOrderGizi] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
        }

        logger.info("[OrderGiziAction.editOrderGizi] end process >>>");
        return response;
    }

    public CheckResponse updateDiterimaFlag(String idOrderGizi) {

        CheckResponse response = new CheckResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");

        if (idOrderGizi != null && !"".equalsIgnoreCase(idOrderGizi)) {
            OrderGizi orderGizi = new OrderGizi();
            orderGizi.setIdOrderGizi(idOrderGizi);
            orderGizi.setLastUpdateWho(userLogin);
            orderGizi.setLastUpdate(updateTime);

            try {
                response = orderGiziBo.updateDiterimaFLag(orderGizi);
            } catch (GeneralBOException e) {
                logger.error("[Found Error] " + e);
            }
        }
        return response;
    }

    public CheckResponse cancelOrderGizi(String idOrderGizi, String keterangan) {

        CheckResponse response = new CheckResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");

        if (idOrderGizi != null && !"".equalsIgnoreCase(idOrderGizi)) {

            OrderGizi orderGizi = new OrderGizi();
            orderGizi.setIdOrderGizi(idOrderGizi);
            orderGizi.setLastUpdateWho(userLogin);
            orderGizi.setLastUpdate(updateTime);
            orderGizi.setKeterangan(keterangan);

            try {
                response = orderGiziBo.cancelOrderGizi(orderGizi);
            } catch (GeneralBOException e) {
                logger.error("[Found Error] " + e);
                response.setStatus("error");
                response.setMessage("Found Error "+e.getMessage());
            }
        }
        return response;
    }

}