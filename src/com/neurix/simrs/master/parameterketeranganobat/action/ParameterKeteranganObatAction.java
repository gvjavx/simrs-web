package com.neurix.simrs.master.parameterketeranganobat.action;

import com.neurix.common.action.BaseMasterAction;

import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;

import com.neurix.simrs.master.parameterketeranganobat.bo.ParameterKeteranganObatBo;
import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ParameterKeteranganObatAction extends BaseMasterAction {
    public static transient Logger logger = Logger.getLogger(ParameterKeteranganObatAction.class);
    private ParameterKeteranganObatBo parameterKeteranganObatBoProxy;
    private ParameterKeteranganObat parameterKeteranganObat;

    public ParameterKeteranganObat getParameterKeteranganObat() {
        return parameterKeteranganObat;
    }

    public void setParameterKeteranganObat(ParameterKeteranganObat parameterKeteranganObat) {
        this.parameterKeteranganObat = parameterKeteranganObat;
    }

    public ParameterKeteranganObatBo getParameterKeteranganObatBoProxy() {
        return parameterKeteranganObatBoProxy;
    }

    public void setParameterKeteranganObatBoProxy(ParameterKeteranganObatBo parameterKeteranganObatBoProxy) {
        this.parameterKeteranganObatBoProxy = parameterKeteranganObatBoProxy;
    }

    public ParameterKeteranganObat init(String kode, String flag){
        logger.info("[ParameterKeteranganObatAction.init] start process >>>>>");
        
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterKeteranganObat> listOfResult = (List<ParameterKeteranganObat>) session.getAttribute("listOfResultParameterKeteranganObat");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (ParameterKeteranganObat parameterKeteranganObat : listOfResult){
                    if (kode.equalsIgnoreCase(parameterKeteranganObat.getId()) && flag.equalsIgnoreCase(parameterKeteranganObat.getFlag())){
                        setParameterKeteranganObat(parameterKeteranganObat);
                        break;
                    }
                }
            } else {
                setParameterKeteranganObat(new ParameterKeteranganObat());
            }
            logger.info("[ParameterKeteranganObatAction.init] end process >>>>>");
        }
        return getParameterKeteranganObat();
    }


    @Override
    public String add() {
        logger.info("[KategoriPersediaanParameterKeteranganObatAction.add] start process >>>");

        ParameterKeteranganObat addParameterKeteranganObat = new ParameterKeteranganObat();
        setParameterKeteranganObat(addParameterKeteranganObat);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[ParameterKeteranganObat Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[ParameterKeteranganObat Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        ParameterKeteranganObat editparam = new ParameterKeteranganObat();
        if(itemFlag != null){
            try {
                editparam = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("edit action"+e.getMessage());
                throw new GeneralBOException("edit action, "+e.getMessage());
            }

            if(editparam != null) {
                setParameterKeteranganObat(editparam);
            } else {
                editparam.setFlag(itemFlag);
                setParameterKeteranganObat(editparam);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editparam.setFlag(getFlag());
            setParameterKeteranganObat(editparam);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        setAddOrEdit(true);
        logger.info("[ParameterKeteranganObatAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[ParameterKeteranganObatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        ParameterKeteranganObat deleteParameterKeteranganObat = new ParameterKeteranganObat();

        if (itemFlag != null ) {
            try {
                deleteParameterKeteranganObat = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteParameterKeteranganObat != null) {
                setParameterKeteranganObat(deleteParameterKeteranganObat);

            } else {
                deleteParameterKeteranganObat.setFlag(itemFlag);
                setParameterKeteranganObat(deleteParameterKeteranganObat);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteParameterKeteranganObat.setFlag(itemFlag);
            setParameterKeteranganObat(deleteParameterKeteranganObat);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteParameterKeteranganObatAction.delete] end process <<<");
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
        logger.info("[ParameterKeteranganObatAction.search] start process >>>");

        ParameterKeteranganObat searchParameterKeteranganObat = getParameterKeteranganObat();
        List<ParameterKeteranganObat> listOfsearchParameterKeteranganObat = new ArrayList();
        try {
            listOfsearchParameterKeteranganObat = parameterKeteranganObatBoProxy.getByCriteria(searchParameterKeteranganObat);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultParameterKeteranganObat");
        session.setAttribute("listOfResultParameterKeteranganObat", listOfsearchParameterKeteranganObat);
        setParameterKeteranganObat(searchParameterKeteranganObat);
        logger.info("[ParameterKeteranganObatAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[ParameterKeteranganObatAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setParameterKeteranganObat(new ParameterKeteranganObat());
        session.removeAttribute("listOfResultParameterKeteranganObat");
        logger.info("[ParameterKeteranganObatAction.initForm] end process >>>");
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

    public String saveAdd(){
        logger.info("[ParameterKeteranganObatAction.saveAdd] start process >>>");

        try {
            ParameterKeteranganObat parameterKeteranganObat = getParameterKeteranganObat();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            parameterKeteranganObat.setCreatedWho(userLogin);
            parameterKeteranganObat.setLastUpdate(updateTime);
            parameterKeteranganObat.setCreatedDate(updateTime);
            parameterKeteranganObat.setLastUpdateWho(userLogin);
            parameterKeteranganObat.setAction("C");
            parameterKeteranganObat.setFlag("Y");

            parameterKeteranganObatBoProxy.saveAdd(parameterKeteranganObat);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultParameterKeteranganObat");

        logger.info("[ParameterKeteranganObatAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[ParameterKeteranganObatAction.saveEdit] start process >>>");
        try {
            ParameterKeteranganObat editParam = getParameterKeteranganObat();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editParam.setLastUpdateWho(userLogin);
            editParam.setLastUpdate(updateTime);
            editParam.setAction("U");
            editParam.setFlag("Y");

            parameterKeteranganObatBoProxy.saveEdit(editParam);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[ParameterKeteranganObatAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[ParameterKeteranganObatAction.saveDelete] start process >>>");
        try {

            ParameterKeteranganObat deleteParam = getParameterKeteranganObat();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteParam.setLastUpdate(updateTime);
            deleteParam.setLastUpdateWho(userLogin);
            deleteParam.setAction("D");
            deleteParam.setFlag("N");

            parameterKeteranganObatBoProxy.saveDelete(deleteParam);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[deleteParameterKeteranganObatAction.saveDelete] end process <<<");

        return "success_save_delete";
    }
}
