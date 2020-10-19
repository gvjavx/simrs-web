package com.neurix.simrs.transaksi.initdashboard.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.initdashboard.bo.InitDashboardBo;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;

public class InitDashboardAction {
    public static transient Logger logger = Logger.getLogger(InitDashboardAction.class);
    public HeaderCheckup getCountAll(){
        String branchId = CommonUtil.userBranchLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        InitDashboardBo initDashboardBo = (InitDashboardBo) ctx.getBean("initDashboardBoProxy");
        HeaderCheckup headerCheckupList = new HeaderCheckup();
        try {
            headerCheckupList = initDashboardBo.getCountAll(branchId);
        }catch (GeneralBOException e){
            logger.error(e.getMessage());
        }
        return headerCheckupList;
    }
    public static Logger getLogger() {
        return logger;
    }

    public String initForm(){
        return "init";
    }
}
