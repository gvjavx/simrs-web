package com.neurix.akuntansi.master.parameterbudgeting.action;

import com.neurix.akuntansi.master.parameterbudgeting.bo.ParameterBudgetingRekeningBo;
import com.neurix.akuntansi.master.parameterbudgeting.model.ParameterBudgetingRekening;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.akuntansi.master.parameterbudgeting.bo.ParameterBudgetingRekeningBo;
import com.neurix.akuntansi.master.parameterbudgeting.model.ParameterBudgetingRekening;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ParameterBudgetingRekeningAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(ParameterBudgetingRekeningAction.class);
    private ParameterBudgetingRekeningBo parameterBudgetingRekeningBoProxy;
    private ParameterBudgetingRekening parameterBudgetingRekening;
    private List<ParameterBudgetingRekening> listOfParameterBudgetingRekening = new ArrayList<>();

    public List<ParameterBudgetingRekening> getListOfParameterBudgetingRekening() {
        return listOfParameterBudgetingRekening;
    }

    public void setListOfParameterBudgetingRekening(List<ParameterBudgetingRekening> listOfParameterBudgetingRekening) {
        this.listOfParameterBudgetingRekening = listOfParameterBudgetingRekening;
    }

    public ParameterBudgetingRekeningBo getParameterBudgetingRekeningBoProxy() {
        return parameterBudgetingRekeningBoProxy;
    }

    public void setParameterBudgetingRekeningBoProxy(ParameterBudgetingRekeningBo parameterBudgetingRekeningBoProxy) {
        this.parameterBudgetingRekeningBoProxy = parameterBudgetingRekeningBoProxy;
    }

    public ParameterBudgetingRekening getParameterBudgetingRekening() {
        return parameterBudgetingRekening;
    }

    public void setParameterBudgetingRekening(ParameterBudgetingRekening parameterBudgetingRekening) {
        this.parameterBudgetingRekening = parameterBudgetingRekening;
    }

    public ParameterBudgetingRekening init(String kode, String flag){
        logger.info("[ParameterBudgetingRekeningAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgetingRekening> listOfResult = (List<ParameterBudgetingRekening>) session.getAttribute("listOfResultParameterBudgetingRekening");
        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (ParameterBudgetingRekening parameterBudgetingRekening : listOfResult){
                    if (kode.equalsIgnoreCase(parameterBudgetingRekening.getId()) && flag.equalsIgnoreCase(parameterBudgetingRekening.getFlag())){
                        setParameterBudgetingRekening(parameterBudgetingRekening);
                        break;
                    }
                }
            } else {
                setParameterBudgetingRekening(new ParameterBudgetingRekening());
            }
            logger.info("[ParameterBudgetingRekeningAction.init] end process >>>>>");
        }
        return getParameterBudgetingRekening();
    }

    @Override
    public String add() {
        logger.info("[ParameterBudgetingRekeningAction.add] start process >>>");

        ParameterBudgetingRekening addParameterBudgetingRekening = new ParameterBudgetingRekening();
        setParameterBudgetingRekening(addParameterBudgetingRekening);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[ParameterBudgetingRekening Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[ParameterBudgetingRekening Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        ParameterBudgetingRekening editParameterBudgetingRekening = new ParameterBudgetingRekening();
        if(itemFlag != null){
            try {
                editParameterBudgetingRekening = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ParameterBudgetingRekening edit action"+e.getMessage());
                throw new GeneralBOException("ParameterBudgetingRekening edit action, "+e.getMessage());
            }

            if(editParameterBudgetingRekening != null) {
                setParameterBudgetingRekening(editParameterBudgetingRekening);
            } else {
                editParameterBudgetingRekening.setFlag(itemFlag);
                setParameterBudgetingRekening(editParameterBudgetingRekening);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editParameterBudgetingRekening.setFlag(getFlag());
            setParameterBudgetingRekening(editParameterBudgetingRekening);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[ParameterBudgetingRekeningAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[ParameterBudgetingRekeningAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        ParameterBudgetingRekening deleteParameterBudgetingRekening = new ParameterBudgetingRekening();

        if (itemFlag != null ) {
            try {
                deleteParameterBudgetingRekening = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteParameterBudgetingRekening != null) {
                setParameterBudgetingRekening(deleteParameterBudgetingRekening);

            } else {
                deleteParameterBudgetingRekening.setFlag(itemFlag);
                setParameterBudgetingRekening(deleteParameterBudgetingRekening);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteParameterBudgetingRekening.setFlag(itemFlag);
            setParameterBudgetingRekening(deleteParameterBudgetingRekening);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteParameterBudgetingRekeningAction.delete] end process <<<");
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
        logger.info("[ParameterBudgetingRekeningAction.search] start process >>>");

        ParameterBudgetingRekening searchParameterBudgetingRekening = getParameterBudgetingRekening();
        List<ParameterBudgetingRekening> listOfsearchParameterBudgetingRekening = new ArrayList();
        try {
            listOfsearchParameterBudgetingRekening = parameterBudgetingRekeningBoProxy.getByCriteria(searchParameterBudgetingRekening);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultParameterBudgetingRekening");
        session.setAttribute("listOfResultParameterBudgetingRekening", listOfsearchParameterBudgetingRekening);
        setParameterBudgetingRekening(searchParameterBudgetingRekening);
        logger.info("[ParameterBudgetingRekeningAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[parameterBudgetingRekeningAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setParameterBudgetingRekening(new ParameterBudgetingRekening());
        session.removeAttribute("listOfResultParameterBudgetingRekening");
        logger.info("[parameterBudgetingRekeningAction.initForm] end process >>>");
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

    public String getListParameterBudgetingRekening(){
        logger.info("[CheckupDetailAction.getListParameterBudgetingRekening] start process >>>");
        List<ParameterBudgetingRekening> parameterBudgetingRekeningList = new ArrayList<>();
        ParameterBudgetingRekening parameterBudgetingRekening = new ParameterBudgetingRekening();
        try {
            parameterBudgetingRekeningList = parameterBudgetingRekeningBoProxy.getByCriteria(parameterBudgetingRekening);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListParameterBudgetingRekening] Error when get jenis obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }
        listOfParameterBudgetingRekening.addAll(parameterBudgetingRekeningList);
        logger.info("[CheckupDetailAction.getListParameterBudgetingRekening] end process <<<");
        return SUCCESS;
    }

    public String saveAdd(){
        logger.info("[ParameterBudgetingRekeningAction.saveAdd] start process >>>");

        try {
            ParameterBudgetingRekening parameterBudgetingRekening = getParameterBudgetingRekening();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            parameterBudgetingRekening.setCreatedWho(userLogin);
            parameterBudgetingRekening.setLastUpdate(updateTime);
            parameterBudgetingRekening.setCreatedDate(updateTime);
            parameterBudgetingRekening.setLastUpdateWho(userLogin);
            parameterBudgetingRekening.setAction("C");
            parameterBudgetingRekening.setFlag("Y");

            parameterBudgetingRekeningBoProxy.saveAdd(parameterBudgetingRekening);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultParameterBudgetingRekening");

        logger.info("[ParameterBudgetingRekeningAction.saveAdd] end process >>>");
        return "success_save_add";
    }


    public String saveDelete(){
        logger.info("[ParameterBudgetingRekeningAction.saveDelete] start process >>>");
        try {

            ParameterBudgetingRekening deleteParameterBudgetingRekening = getParameterBudgetingRekening();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteParameterBudgetingRekening.setLastUpdate(updateTime);
            deleteParameterBudgetingRekening.setLastUpdateWho(userLogin);
            deleteParameterBudgetingRekening.setAction("D");
            deleteParameterBudgetingRekening.setFlag("N");

            parameterBudgetingRekeningBoProxy.saveDelete(deleteParameterBudgetingRekening);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[ParameterBudgetingRekeningAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[ParameterBudgetingRekeningAction.saveEdit] start process >>>");
        try {
            ParameterBudgetingRekening editParameterBudgetingRekening = getParameterBudgetingRekening();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editParameterBudgetingRekening.setLastUpdateWho(userLogin);
            editParameterBudgetingRekening.setLastUpdate(updateTime);
            editParameterBudgetingRekening.setAction("U");
            editParameterBudgetingRekening.setFlag("Y");

            parameterBudgetingRekeningBoProxy.saveEdit(editParameterBudgetingRekening);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[ParameterBudgetingRekeningAction.saveEdit] end process <<<");

        return "success_save_edit";
    }




}