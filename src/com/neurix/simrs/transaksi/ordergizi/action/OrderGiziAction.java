package com.neurix.simrs.transaksi.ordergizi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.action.BaseTransactionAction;
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

public class OrderGiziAction extends BaseTransactionAction {

    protected static transient Logger logger = Logger.getLogger(OrderGiziAction.class);
    private OrderGiziBo orderGiziBoProxy;
    private OrderGizi orderGizi;

    public static Logger getLogger() {
        return logger;
    }

    public void setOrderGiziBoProxy(OrderGiziBo orderGiziBoProxy) {
        this.orderGiziBoProxy = orderGiziBoProxy;
    }

    public CheckResponse saveOrderGizi(String data, String isTomorrow) {
        logger.info("[OrderGiziAction.saveOrderGizi] start process >>>");
        CheckResponse response = new CheckResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        try {
            JSONArray json = new JSONArray(data);
            if (json != null) {
                try {
                    List<OrderGizi> orderGiziList = new ArrayList<>();
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject obj = json.getJSONObject(i);
                        OrderGizi orderGizi = new OrderGizi();
                        orderGizi.setIdRawatInap(obj.getString("id_rawat_inap"));
                        orderGizi.setIdDietGizi(obj.getString("id_diet_gizi"));
                        orderGizi.setKeterangan(obj.getString("waktu"));
                        orderGizi.setTglOrder(updateTime);
                        orderGizi.setCreatedWho(userLogin);
                        orderGizi.setCreatedDate(updateTime);
                        orderGizi.setLastUpdate(updateTime);
                        orderGizi.setLastUpdateWho(userLogin);
                        orderGizi.setAction("C");
                        orderGizi.setFlag("Y");
                        if (obj.has("id_jenis_diet")) {
                            if (!"".equalsIgnoreCase(obj.getString("id_jenis_diet"))) {
                                String[] list = obj.getString("id_jenis_diet").split(",");
                                if (list.length > 0) {
                                    List<String> stringList = new ArrayList<>();
                                    for (String jenis : list) {
                                        stringList.add(jenis);
                                    }
                                    orderGizi.setListJenisGizi(stringList);
                                }
                            }
                        }
                        orderGiziList.add(orderGizi);
                    }
                    response = orderGiziBo.saveAdd(orderGiziList, isTomorrow);
                } catch (GeneralBOException e) {
                    response.setStatus("error");
                    response.setMessage("Found Error :" + e.getMessage());
                    logger.error("[OrderGiziAction.saveOrderGizi] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                }
            } else {
                response.setStatus("error");
                response.setMessage("Data json tidak ada...!");
            }
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMessage("Error when parse JSON Objact, " + e.getMessage());
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

    public CheckResponse editOrderGizi(String idOrdeGizi, String idDietGizi, List<String> list) {
        logger.info("[OrderGiziAction.editOrderGizi] start process >>>");
        CheckResponse response = new CheckResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            OrderGizi orderGizi = new OrderGizi();
            orderGizi.setIdOrderGizi(idOrdeGizi);
            orderGizi.setLastUpdate(updateTime);
            orderGizi.setLastUpdateWho(userLogin);
            orderGizi.setAction("U");
            orderGizi.setIdDietGizi(idDietGizi);
            response = orderGiziBo.saveEdit(orderGizi, list);
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
                response.setMessage("Found Error " + e.getMessage());
            }
        }
        return response;
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }
}