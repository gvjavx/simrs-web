package com.neurix.hris.mobileapi;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.Branch;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gondok
 * Wednesday, 20/02/19 13:32
 */
public class BranchController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(BranchController.class);

    private BranchBo branchBoProxy;
    private List<Branch> listOfBranch;
    private Branch model;

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public HttpHeaders index() {

        logger.info("[BranchController.index] end process POST /branch <<<");

        List<com.neurix.authorization.company.model.Branch> modelBranch = null;
        try {
            modelBranch = branchBoProxy.findAllBranch();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[BranchController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[BranchController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        List<Branch> branches = new ArrayList<>();

        if(modelBranch != null){
            for(com.neurix.authorization.company.model.Branch branch : modelBranch){
                Branch model = new Branch();
                model.setBranchId(branch.getBranchId());
                model.setBranchAddress(branch.getBranchAddress());
                model.setBranchName(branch.getBranchName());

                branches.add(model);
            }
        }
        logger.info("[BranchController.index] end process POST /branch <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders create() {

        logger.info("[BranchController.create] start process POST /branch <<<");

        List<com.neurix.authorization.company.model.Branch> modelBranch = null;

        com.neurix.authorization.company.model.Branch bean = new com.neurix.authorization.company.model.Branch();
        bean.setAreaId("0002");
        try {
            modelBranch = branchBoProxy.getByCriteria(bean);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[BranchController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[BranchController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }


        listOfBranch = new ArrayList<>();

        if(modelBranch != null){
            for(com.neurix.authorization.company.model.Branch branch : modelBranch){
                Branch model = new Branch();
                model.setBranchId(branch.getBranchId());
                model.setBranchAddress(branch.getBranchAddress());
                model.setBranchName(branch.getBranchName());

                listOfBranch.add(model);
            }
        }
        logger.info("[BranchController.post] end process POST /branch <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public Object getModel() {
        return (listOfBranch != null ? listOfBranch : model);
    }
}
