package com.neurix.hris.mobileapi;

import com.neurix.authorization.company.bo.CompanyBo;
import com.neurix.authorization.company.model.Company;
import com.neurix.common.exception.GeneralBOException;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * @author gondok
 * Tuesday, 29/09/20 15:28
 */
public class CompanyController implements ModelDriven<Object> {
    private static final transient org.apache.log4j.Logger logger = Logger.getLogger(CompanyController.class);
    Company model = new Company();
    CompanyBo companyBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(Company model) {
        this.model = model;
    }

    public CompanyBo getCompanyBoProxy() {
        return companyBoProxy;
    }

    public void setCompanyBoProxy(CompanyBo companyBoProxy) {
        this.companyBoProxy = companyBoProxy;
    }

    @Override
    public Object getModel() {
        return model;
    }

    public HttpHeaders create() {
        logger.info("[JadwalShiftController.create] start process POST /company <<<");

        try {
          model = companyBoProxy.getById("12201300001");
        } catch (GeneralBOException e) {
            logger.error("[CompanyController.create] Error when get,", e);
        }

        logger.info("[JadwalShiftController.create] start process POST /company <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
