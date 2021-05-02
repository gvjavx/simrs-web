package com.neurix.hris.master.cutiPanjang.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.cutiPanjang.bo.CutiPanjangBo;
import com.neurix.hris.master.cutiPanjang.model.CutiPanjang;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class CutiPanjangAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(CutiPanjangAction.class);
    private CutiPanjangBo cutiPanjangBoProxy;
    private CutiPanjang cutiPanjang;

    public CutiPanjangBo getCutiPanjangBoProxy() {
        return cutiPanjangBoProxy;
    }

    public void setCutiPanjangBoProxy(CutiPanjangBo cutiPanjangBoProxy) {
        this.cutiPanjangBoProxy = cutiPanjangBoProxy;
    }

    public CutiPanjang getCutiPanjang() {
        return cutiPanjang;
    }

    public void setCutiPanjang(CutiPanjang cutiPanjang) {
        this.cutiPanjang = cutiPanjang;
    }

    private List<CutiPanjang> initComboCutiPanjang;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CutiPanjangAction.logger = logger;
    }


    public List<CutiPanjang> getInitComboCutiPanjang() {
        return initComboCutiPanjang;
    }

    public void setInitComboCutiPanjang(List<CutiPanjang> initComboCutiPanjang) {
        this.initComboCutiPanjang = initComboCutiPanjang;
    }

    public CutiPanjang init(String kode, String flag){
        logger.info("[CutiPanjangAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CutiPanjang> listOfResultCutiPanjang = (List<CutiPanjang>) session.getAttribute("listOfResultCutiPanjang");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResultCutiPanjang != null){
                for (CutiPanjang cutiPanjang: listOfResultCutiPanjang) {
                    if(kode.equalsIgnoreCase(cutiPanjang.getCutiPanjangId()) && flag.equalsIgnoreCase(cutiPanjang.getFlag())){
                        setCutiPanjang(cutiPanjang);
                        break;
                    }
                }
            } else {
                setCutiPanjang(new CutiPanjang());
            }

            logger.info("[CutiPanjangAction.init] end process >>>");
        }
        return getCutiPanjang();
    }

    @Override
    public String add() {
        logger.info("[CutiPanjangAction.add] start process >>>");
        CutiPanjang addCutiPanjang = new CutiPanjang();
        setCutiPanjang(addCutiPanjang);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultCutiPanjang");

        logger.info("[CutiPanjangAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[CutiPanjangAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        CutiPanjang editCutiPanjang = new CutiPanjang();

        if(itemFlag != null){
            try {
                editCutiPanjang = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPanjangBoProxy.saveErrorMessage(e.getMessage(), "CutiPanjangBO.getCutiPanjangByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPanjangAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[CutiPanjangAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editCutiPanjang != null) {
                setCutiPanjang(editCutiPanjang);
            } else {
                editCutiPanjang.setFlag(itemFlag);
                editCutiPanjang.setCutiPanjangId(itemId);
                setCutiPanjang(editCutiPanjang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editCutiPanjang.setCutiPanjangId(itemId);
            editCutiPanjang.setFlag(getFlag());
            setCutiPanjang(editCutiPanjang);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[CutiPanjangAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[CutiPanjangAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        CutiPanjang deleteCutiPanjang = new CutiPanjang();

        if (itemFlag != null ) {

            try {
                deleteCutiPanjang = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPanjangBoProxy.saveErrorMessage(e.getMessage(), "CutiPanjangBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPanjangAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[CutiPanjangAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteCutiPanjang != null) {
                setCutiPanjang(deleteCutiPanjang);

            } else {
                deleteCutiPanjang.setCutiPanjangId(itemId);
                deleteCutiPanjang.setFlag(itemFlag);
                setCutiPanjang(deleteCutiPanjang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteCutiPanjang.setCutiPanjangId(itemId);
            deleteCutiPanjang.setFlag(itemFlag);
            setCutiPanjang(deleteCutiPanjang);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[CutiPanjangAction.delete] end process <<<");

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

    public String saveEdit(){
        logger.info("[CutiPanjangAction.saveEdit] start process >>>");
        try {

            CutiPanjang editCutiPanjang = getCutiPanjang();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editCutiPanjang.setLastUpdateWho(userLogin);
            editCutiPanjang.setLastUpdate(updateTime);
            editCutiPanjang.setAction("U");
            editCutiPanjang.setFlag("Y");

            cutiPanjangBoProxy.saveEdit(editCutiPanjang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPanjangBoProxy.saveErrorMessage(e.getMessage(), "CutiPanjangBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPanjangAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPanjangAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiPanjangAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[CutiPanjangAction.saveDelete] start process >>>");
        try {

            CutiPanjang deleteCutiPanjang = getCutiPanjang();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteCutiPanjang.setLastUpdate(updateTime);
            deleteCutiPanjang.setLastUpdateWho(userLogin);
            deleteCutiPanjang.setAction("U");
            deleteCutiPanjang.setFlag("N");

            cutiPanjangBoProxy.saveDelete(deleteCutiPanjang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPanjangBoProxy.saveErrorMessage(e.getMessage(), "CutiPanjangBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPanjangAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPanjangAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiPanjangAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[CutiPanjangAction.saveAdd] start process >>>");

        try {
            CutiPanjang cutiPanjang = getCutiPanjang();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            cutiPanjang.setCreatedWho(userLogin);
            cutiPanjang.setLastUpdate(updateTime);
            cutiPanjang.setCreatedDate(updateTime);
            cutiPanjang.setLastUpdateWho(userLogin);
            cutiPanjang.setAction("C");
            cutiPanjang.setFlag("Y");

            cutiPanjangBoProxy.saveAdd(cutiPanjang);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPanjangBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultCutiPanjang");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[CutiPanjangAction.search] start process >>>");

        CutiPanjang searchCutiPanjang = getCutiPanjang();
        List<CutiPanjang> listOfsearchCutiPanjang = new ArrayList();

        try {
            listOfsearchCutiPanjang = cutiPanjangBoProxy.getByCriteria(searchCutiPanjang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPanjangBoProxy.saveErrorMessage(e.getMessage(), "CutiPanjangBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPanjangAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPanjangAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultCutiPanjang");
        session.setAttribute("listOfResultCutiPanjang", listOfsearchCutiPanjang);

        logger.info("[CutiPanjangAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[CutiPanjangAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultCutiPanjang");
        logger.info("[CutiPanjangAction.initForm] end process >>>");
        return INPUT;
    }

    public String initCutiPanjang() {
        logger.info("[CutiPanjangAction.search] start process >>>");

        CutiPanjang searchCutiPanjang = new CutiPanjang();
        searchCutiPanjang.setFlag("Y");
        List<CutiPanjang> listOfsearchCutiPanjang = new ArrayList();

        try {
            listOfsearchCutiPanjang = cutiPanjangBoProxy.getByCriteria(searchCutiPanjang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPanjangBoProxy.saveErrorMessage(e.getMessage(), "CutiPanjangBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPanjangAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPanjangAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultCutiPanjangCutiPanjang");
        session.setAttribute("listOfResultCutiPanjangCutiPanjang", listOfsearchCutiPanjang);

        logger.info("[CutiPanjangAction.search] end process <<<");

        return "";
    }

    public String paging(){
        return SUCCESS;
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
