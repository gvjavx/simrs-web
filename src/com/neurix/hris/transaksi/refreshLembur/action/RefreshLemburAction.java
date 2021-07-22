package com.neurix.hris.transaksi.refreshLembur.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.refreshLembur.bo.RefreshLemburBo;
import com.neurix.hris.transaksi.refreshLembur.model.RefreshLembur;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Aji Noor on 22/07/2021
 */
public class RefreshLemburAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(RefreshLemburAction.class);

    private RefreshLembur refreshLembur;
    private RefreshLemburBo refreshLemburBoProxy;
    private BranchBo branchBoProxy;
    private boolean admin = false;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public RefreshLembur getRefreshLembur() {
        return refreshLembur;
    }

    public void setRefreshLembur(RefreshLembur refreshLembur) {
        this.refreshLembur = refreshLembur;
    }

    public static Logger getLogger() {
        return logger;
    }

    public RefreshLemburBo getRefreshLemburBoProxy() {
        return refreshLemburBoProxy;
    }

    public void setRefreshLemburBoProxy(RefreshLemburBo refreshLemburBoProxy) {
        this.refreshLemburBoProxy = refreshLemburBoProxy;
    }

    public BranchBo getBranchBoProxy() {
        return branchBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    @Override
    public String add() {
        logger.info("[RefreshLemburAction.add] start process >>>");
        RefreshLembur addRefreshLembur = new RefreshLembur();
        String branchId = CommonUtil.userBranchLogin();
        RefreshLembur data = new RefreshLembur();
        if (branchId != null) {
            data.setBranchId(branchId);
        } else {
            data.setBranchId("");
        }

        setRefreshLembur(data);
        setAddOrEdit(true);
        setAdd(true);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultRefreshLembur");

        logger.info("[RefreshLemburAction.add] stop process >>>");
        return "init_add";    }

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
        List<RefreshLembur> refreshLemburList = new ArrayList<>();
        RefreshLembur searchRefreshlembur = new RefreshLembur();
        searchRefreshlembur.setAbsensiPegawaiId(getId());
        searchRefreshlembur.setFlag(getFlag());
        try {
            refreshLemburList = refreshLemburBoProxy.getByCriteria(searchRefreshlembur);
        }catch (GeneralBOException e) {
            logger.error("[RefreshLemburAction.view] Error, " + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }
        for (RefreshLembur refreshLembur : refreshLemburList) {
            setRefreshLembur(refreshLembur);
        }
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        logger.info("[RefreshLemburAction.search] start process >>>");
        RefreshLembur searchRefreshLembur = getRefreshLembur();
        List<RefreshLembur> listOfSearchRefreshlembur = new ArrayList();
        String branchId = CommonUtil.userBranchLogin();
        searchRefreshLembur.setBranchId(branchId);
        String role = CommonUtil.roleAsLogin();

        if ("ADMIN".equalsIgnoreCase(role)){
            setAdmin(true);
        }

        try {
            listOfSearchRefreshlembur = refreshLemburBoProxy.getByCriteria(searchRefreshLembur);
        } catch (GeneralBOException e) {
            logger.error("[RefreshLemburAction.search] Error, " + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultRefreshLembur");
        session.setAttribute("listOfResultRefreshLembur", listOfSearchRefreshlembur);

        logger.info("[RefreshLemburAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[RefreshLemburAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        RefreshLembur data = new RefreshLembur();
        if (branchId != null) {
            data.setBranchId(branchId);
        } else {
            data.setBranchId("");
        }

        setRefreshLembur(data);
        session.removeAttribute("listOfResultRefreshLembur");
        session.removeAttribute("listOfResultRefreshLembur");
        logger.info("[RefreshLemburAction.initForm] end process >>>");
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
