package com.neurix.authorization.company.action;

import com.neurix.authorization.company.bo.CompanyBo;
import com.neurix.authorization.company.model.Company;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class CompanyAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(CompanyAction.class);

    private CompanyBo companyBoProxy;
    private Company company;

    public void setCompanyBoProxy(CompanyBo companyBoProxy) {
        this.companyBoProxy = companyBoProxy;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String initForm() {
        logger.info("[CompanyAction.save] start process >>>");

        String companyId = CommonUtil.companyIdLogin();

        Company resultOfSearchCompany = null;
        try {
            resultOfSearchCompany = companyBoProxy.getById(companyId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = companyBoProxy.saveErrorMessage(e.getMessage(), "CompanyBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        if (resultOfSearchCompany!=null) {
            setCompany(resultOfSearchCompany);
        } else {
            setCompany(new Company());
        }

        logger.info("[CompanyAction.save] end process >>>");

        return "success";
    }

    @Override
    public String save() {
        logger.info("[CompanyAction.save] start process >>>");

        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            Company editCompany = getCompany();

            editCompany.setCreatedDate(createTime);
            editCompany.setCreatedWho(userLogin);
            editCompany.setLastUpdate(createTime);
            editCompany.setLastUpdateWho(userLogin);

            companyBoProxy.saveEdit(editCompany);
            company.setSuccessMessage("Data Successfully Updated");
        } catch (UsernameNotFoundException e) {
            logger.error("[CompanyAction.save] Error when saving company,", e);
            addActionError("Error, " + e.getMessage());
            company.setErrorMessage("Error, " + e.getMessage());
            company.setSuccessMessage("");
            return ERROR;
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = companyBoProxy.saveErrorMessage(e.getMessage(), "CompanyBO.save");
            } catch (GeneralBOException e1) {
                logger.error("[CompanyAction.save] Error when saving error,", e1);
            }
            logger.error("[CompanyAction.save] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            company.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            company.setSuccessMessage("");
            return ERROR;
        }

        logger.info("[CompanyAction.save] start process >>>");

        return "success_save";
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
    public String search() {
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
}
