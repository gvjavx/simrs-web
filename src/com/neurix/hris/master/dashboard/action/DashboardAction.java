package com.neurix.hris.master.dashboard.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.dashboard.bo.DashboardBo;
import com.neurix.hris.master.dashboard.model.Dashboard;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class DashboardAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(DashboardAction.class);
    private DashboardBo dashboardBoProxy;
    private Dashboard dashboard;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DashboardAction.logger = logger;
    }

    public DashboardBo getDashboardBoProxy() {
        return dashboardBoProxy;
    }

    public void setDashboardBoProxy(DashboardBo dashboardBoProxy) {
        this.dashboardBoProxy = dashboardBoProxy;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public String add() {
        return "init_add";
    }

    @Override
    public String edit() {
        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
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
        logger.info("[GolonganAction.search] start process >>>");

        Dashboard searchGolongan = getDashboard();
        List<Dashboard> listOfsearchGolongan = new ArrayList();

        try {
            listOfsearchGolongan = dashboardBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dashboardBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GolonganAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GolonganAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchGolongan);

        logger.info("[AlatAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[GolonganAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listDataGolongan");
        logger.info("[GolonganAction.initForm] end process >>>");
        return INPUT;
    }


    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}
