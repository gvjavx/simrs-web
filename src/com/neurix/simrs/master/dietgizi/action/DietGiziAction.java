package com.neurix.simrs.master.dietgizi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dietgizi.bo.DietGiziBo;
import com.neurix.simrs.master.dietgizi.model.DietGizi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DietGiziAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(DietGiziAction.class);
    private DietGiziBo dietGiziBoProxy;
    private DietGizi dietGizi;
    private List<DietGizi> listOfDietGizi = new ArrayList<>();

    public List<DietGizi> getListOfDietGizi() {
        return listOfDietGizi;
    }

    public void setListOfDietGizi(List<DietGizi> listOfDietGizi) {
        this.listOfDietGizi = listOfDietGizi;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DietGiziAction.logger = logger;
    }

    public DietGiziBo getDietGiziBoProxy() {
        return dietGiziBoProxy;
    }

    public void setDietGiziBoProxy(DietGiziBo dietGiziBoProxy) {
        this.dietGiziBoProxy = dietGiziBoProxy;
    }

    public DietGizi getDietGizi() {
        return dietGizi;
    }

    public void setDietGizi(DietGizi dietGizi) {
        this.dietGizi = dietGizi;
    }


    public DietGizi init(String kode, String flag){
        logger.info("[DietGiziAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<DietGizi> listOfResult = (List<DietGizi>) session.getAttribute("listOfResultDietGizi");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (DietGizi dietGizi : listOfResult){
                    if (kode.equalsIgnoreCase(dietGizi.getIdDietGizi()) && flag.equalsIgnoreCase(dietGizi.getFlag())){
                        setDietGizi(dietGizi);
                        break;
                    }
                }
            } else {
                setDietGizi(new DietGizi());
            }
            logger.info("[DietGiziAction.init] end process >>>>>");
        }
        return getDietGizi();
    }

    @Override
    public String add() {
        logger.info("[DietGiziAction.add] start process >>>");
        String userBranch = CommonUtil.userBranchLogin();


        DietGizi dietGizi = new DietGizi ();
//        if (CommonConstant.BRANCH_KP.equalsIgnoreCase(userBranch)){
        dietGizi.setBranchId(userBranch);
//        }

//        DietGizi addDietGizi = new DietGizi();
        setDietGizi(dietGizi);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[Diet Gizi Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[Diet Gizi Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        DietGizi editDietGizi = new DietGizi();
        if(itemFlag != null){
            try {
                editDietGizi = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("edit action"+e.getMessage());
                throw new GeneralBOException("edit action, "+e.getMessage());
            }

            if(editDietGizi != null) {
                setDietGizi(editDietGizi);
            } else {
                editDietGizi.setFlag(itemFlag);
                setDietGizi(editDietGizi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editDietGizi.setFlag(getFlag());
            setDietGizi(editDietGizi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[DietGiziAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[DietGiziAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        DietGizi deleteDietGizi = new DietGizi();

        if (itemFlag != null ) {
            try {
                deleteDietGizi = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteDietGizi != null) {
                setDietGizi(deleteDietGizi);

            } else {
                deleteDietGizi.setFlag(itemFlag);
                setDietGizi(deleteDietGizi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteDietGizi.setFlag(itemFlag);
            setDietGizi(deleteDietGizi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteDietGiziAction.delete] end process <<<");
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
        logger.info("[DietGiziAction.search] start process >>>");

        DietGizi searchDietGizi = getDietGizi();
        List<DietGizi> listOfsearchDietGizi = new ArrayList();
        try {
            listOfsearchDietGizi = dietGiziBoProxy.getByCriteria(searchDietGizi);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultDietGizi");
        session.setAttribute("listOfResultDietGizi", listOfsearchDietGizi);
        setDietGizi(searchDietGizi);
        logger.info("[DietGiziAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[dietGiziAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setDietGizi(new DietGizi());
        session.removeAttribute("listOfResultDietGizi");
        logger.info("[dietGiziAction.initForm] end process >>>");
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

    public String getListDietGizi(){
        logger.info("[CheckupDetailAction.getListDietGizi] start process >>>");
        List<DietGizi> dietGiziList = new ArrayList<>();
        DietGizi dietGizi = new DietGizi();
        try {
            dietGiziList = dietGiziBoProxy.getByCriteria(dietGizi);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListDietGizi] Error when get jenis obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }
        listOfDietGizi.addAll(dietGiziList);
        logger.info("[CheckupDetailAction.getListDietGizi] end process <<<");
        return SUCCESS;
    }

    public String saveAdd(){
        logger.info("[DietGiziAction.saveAdd] start process >>>");

        try {
            DietGizi dietGizi = getDietGizi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            dietGizi.setCreatedWho(userLogin);
            dietGizi.setLastUpdate(updateTime);
            dietGizi.setCreatedDate(updateTime);
            dietGizi.setLastUpdateWho(userLogin);
            dietGizi.setAction("C");
            dietGizi.setFlag("Y");

            dietGiziBoProxy.saveAdd(dietGizi);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultDietGizi");

        logger.info("[DietGiziAction.saveAdd] end process >>>");
        return "success_save_add";
    }


    public String saveDelete(){
        logger.info("[DietGiziAction.saveDelete] start process >>>");
        try {

            DietGizi deleteDietGizi = getDietGizi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteDietGizi.setLastUpdate(updateTime);
            deleteDietGizi.setLastUpdateWho(userLogin);
            deleteDietGizi.setAction("D");
            deleteDietGizi.setFlag("N");

            dietGiziBoProxy.saveDelete(deleteDietGizi);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[DietGiziAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[DietGiziAction.saveEdit] start process >>>");
        try {
            DietGizi editDietGizi = getDietGizi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editDietGizi.setLastUpdateWho(userLogin);
            editDietGizi.setLastUpdate(updateTime);
            editDietGizi.setAction("U");
            editDietGizi.setFlag("Y");

            dietGiziBoProxy.saveEdit(editDietGizi);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[DietGiziAction.saveEdit] end process <<<");

        return "success_save_edit";
    }




}