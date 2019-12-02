package com.neurix.simrs.transaksi.ordergizi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
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

    public String saveOrderGizi(String idRawatInap,String dietPagi, String bentukPagi, String dietSiang, String bentukSiang, String dietMalam, String bentukMalam){
        logger.info("[OrderGiziAction.saveOrderGizi] start process >>>");
        try {

            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            OrderGizi orderGizi = new OrderGizi();
            orderGizi.setIdRawatInap(idRawatInap);
            orderGizi.setDietPagi(dietPagi);
            orderGizi.setBentukMakanPagi(bentukPagi);
            orderGizi.setDietSiang(dietSiang);
            orderGizi.setBentukMakanSiang(bentukSiang);
            orderGizi.setDietMalam(dietMalam);
            orderGizi.setBentukMakanMalam(bentukMalam);
            orderGizi.setCreatedWho(userLogin);
            orderGizi.setLastUpdate(updateTime);
            orderGizi.setCreatedDate(updateTime);
            orderGizi.setLastUpdateWho(userLogin);
            orderGizi.setAction("C");
            orderGizi.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            orderGiziBo.saveAdd(orderGizi);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[OrderGiziAction.saveOrderGizi] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[OrderGiziAction.saveOrderGizi] end process >>>");
        return SUCCESS;
    }

    public List<OrderGizi> listOrderGizi(String idRawatInap){

        logger.info("[OrderGiziAction.listOrderGizi] start process >>>");
        List<OrderGizi> orderGiziList = new ArrayList<>();

        OrderGizi orderGizi = new OrderGizi();
        orderGizi.setIdRawatInap(idRawatInap);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");

        if(!"".equalsIgnoreCase(idRawatInap)){
            try {
                orderGiziList = orderGiziBo.getByCriteria(orderGizi);
            }catch (GeneralBOException e){
                logger.info("[OrderGiziAction.listOrderGizi] error when search list of obat inap, "+ e.getMessage());
            }
            logger.info("[OrderGiziAction.listOrderGizi] end process >>>");
            return orderGiziList;

        }else{
            return null;
        }
    }

    public String editOrderGizi(String idOrdeGizi, String idRawatInap,String dietPagi, String bentukPagi, String dietSiang, String bentukSiang, String dietMalam, String bentukMalam){
        logger.info("[OrderGiziAction.editOrderGizi] start process >>>");
        try {

            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            OrderGizi orderGizi = new OrderGizi();
            orderGizi.setIdOrderGizi(idOrdeGizi);
            orderGizi.setIdRawatInap(idRawatInap);
            orderGizi.setDietPagi(dietPagi);
            orderGizi.setBentukMakanPagi(bentukPagi);
            orderGizi.setDietSiang(dietSiang);
            orderGizi.setBentukMakanSiang(bentukSiang);
            orderGizi.setDietMalam(dietMalam);
            orderGizi.setBentukMakanMalam(bentukMalam);
            orderGizi.setLastUpdate(updateTime);
            orderGizi.setLastUpdateWho(userLogin);
            orderGizi.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            orderGiziBo.saveEdit(orderGizi);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[OrderGiziAction.editOrderGizi] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[OrderGiziAction.editOrderGizi] end process >>>");
        return SUCCESS;
    }

}