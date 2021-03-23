package com.neurix.simrs.transaksi.konsultasigizi.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.impl.CheckupBoImpl;
import com.neurix.simrs.transaksi.konsultasigizi.bo.KonsultasiGiziBo;
import com.neurix.simrs.transaksi.konsultasigizi.model.KonsultasiGizi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class KonsultasiGiziAction extends BaseTransactionAction {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);
    private KonsultasiGizi konsultasiGizi;
    private KonsultasiGiziBo konsultasiGiziBoProxy;
    private String id;

    @Override
    public String search() {
        logger.info("[KonsultasiGiziAction.search] Start >>>>>>>");
        KonsultasiGizi konsultasiGizi = getKonsultasiGizi();

        if(konsultasiGizi.getIdKonsultasiGizi() != null && !"".equalsIgnoreCase(konsultasiGizi.getIdKonsultasiGizi())){
            try {
                konsultasiGizi.setStatus("1");
                konsultasiGizi.setIsRead("Y");
                konsultasiGiziBoProxy.saveEdit(konsultasiGizi);
            }catch (HibernateException e){
                logger.error("[KonsultasiGiziAction.search] Error, "+e.getMessage());
            }
        }

        List<KonsultasiGizi> konsultasiGiziList = new ArrayList<>();
        try {
            konsultasiGiziList = konsultasiGiziBoProxy.getDataKonsultasiGizi(konsultasiGizi);
        }catch (Exception e){
            logger.error("[KonsultasiGiziAction.search] Error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", konsultasiGiziList);
        konsultasiGizi.setIdKonsultasiGizi(null);
        setKonsultasiGizi(konsultasiGizi);
        logger.info("[KonsultasiGiziAction.search] End >>>>>>>");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[KonsultasiGiziAction.initForm] Start >>>>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        setKonsultasiGizi(new KonsultasiGizi());
        logger.info("[KonsultasiGiziAction.initForm] End >>>>>>>");
        return "search";
    }

    public List<KonsultasiGizi> pushNotif(){
        logger.info("[KonsultasiGiziAction.pushNotif] Start >>>>>>>");
        List<KonsultasiGizi> konsultasiGiziList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KonsultasiGiziBo konsultasiGiziBo = (KonsultasiGiziBo) ctx.getBean("konsultasiGiziBoProxy");
        try {
            konsultasiGiziList = konsultasiGiziBo.pushNotif(CommonUtil.userBranchLogin());
        }catch (Exception e){
            logger.error("[KonsultasiGiziAction.pushNotif] Error, "+e.getMessage());
        }
        logger.info("[KonsultasiGiziAction.pushNotif] End >>>>>>>");
        return konsultasiGiziList;
    }

    public CrudResponse saveEdit(String id, String status){
        logger.info("[KonsultasiGiziAction.saveEdit] Start >>>>>>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KonsultasiGiziBo konsultasiGiziBo = (KonsultasiGiziBo) ctx.getBean("konsultasiGiziBoProxy");
        KonsultasiGizi konsultasiGizi = new KonsultasiGizi();
        try {
            konsultasiGizi.setIdKonsultasiGizi(id);
            konsultasiGizi.setStatus(status);
            konsultasiGiziBo.saveEdit(konsultasiGizi);
            response.setStatus("success");
            response.setMsg("OK");
        }catch (HibernateException e){
            logger.error("[KonsultasiGiziAction.saveEdit] Error, "+e.getMessage());
        }
        logger.info("[KonsultasiGiziAction.saveEdit] End >>>>>>>");
        return response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKonsultasiGiziBoProxy(KonsultasiGiziBo konsultasiGiziBoProxy) {
        this.konsultasiGiziBoProxy = konsultasiGiziBoProxy;
    }

    public KonsultasiGizi getKonsultasiGizi() {
        return konsultasiGizi;
    }

    public void setKonsultasiGizi(KonsultasiGizi konsultasiGizi) {
        this.konsultasiGizi = konsultasiGizi;
    }

    public static Logger getLogger() {
        return logger;
    }
}
