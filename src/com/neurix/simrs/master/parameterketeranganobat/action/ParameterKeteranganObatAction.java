package com.neurix.simrs.master.parameterketeranganobat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.parameterketeranganobat.bo.ParameterKeteranganObatBo;
import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ParameterKeteranganObatAction extends BaseMasterAction {
    public static transient Logger logger = Logger.getLogger(ParameterKeteranganObatAction.class);
    private ParameterKeteranganObatBo parameterKeteranganObatBo;
    private ParameterKeteranganObat parameterKeteranganObat;

    public ParameterKeteranganObat getParameterKeteranganObat() {
        return parameterKeteranganObat;
    }

    public void setParameterKeteranganObat(ParameterKeteranganObat parameterKeteranganObat) {
        this.parameterKeteranganObat = parameterKeteranganObat;
    }

    public void setParameterKeteranganObatBo(ParameterKeteranganObatBo parameterKeteranganObatBo) {
        this.parameterKeteranganObatBo = parameterKeteranganObatBo;
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
        logger.info("[JenisObatParameterKeteranganObatAction.add] start process >>>");

        ParameterKeteranganObat addParameterKeteranganObat = new ParameterKeteranganObat();
        setParameterKeteranganObat(addParameterKeteranganObat);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[JenisObatParameterKeteranganObat Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[ParameterKeteranganObat Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        ParameterKeteranganObat editJenisObatParameterKeteranganObat = new ParameterKeteranganObat();
        if(itemFlag != null){
            try {
                editJenisObatParameterKeteranganObat = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("edit action"+e.getMessage());
                throw new GeneralBOException("edit action, "+e.getMessage());
            }

            if(editJenisObatParameterKeteranganObat != null) {
                setParameterKeteranganObat(editJenisObatParameterKeteranganObat);
            } else {
                editJenisObatParameterKeteranganObat.setFlag(itemFlag);
                setParameterKeteranganObat(editJenisObatParameterKeteranganObat);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJenisObatParameterKeteranganObat.setFlag(getFlag());
            setParameterKeteranganObat(editJenisObatParameterKeteranganObat);
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
            listOfsearchParameterKeteranganObat = parameterKeteranganObatBo.getByCriteria(searchParameterKeteranganObat);
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


}
