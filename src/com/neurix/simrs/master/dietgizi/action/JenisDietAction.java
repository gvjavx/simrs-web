package com.neurix.simrs.master.dietgizi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dietgizi.bo.JenisDietBo;
import com.neurix.simrs.master.dietgizi.model.JenisDiet;
//import com.neurix.simrs.master.jenisdiet.bo.JenisDietBo;
//import com.neurix.simrs.master.jenisdiet.model.JenisDiet;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JenisDietAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(JenisDietAction.class);
    private JenisDietBo jenisDietBoProxy;
    private JenisDiet jenisDiet;
    private List<JenisDiet> listOfJenisDiet = new ArrayList<>();

    public List<JenisDiet> getListOfJenisDiet() {
        return listOfJenisDiet;
    }

    public void setListOfJenisDiet(List<JenisDiet> listOfJenisDiet) {
        this.listOfJenisDiet = listOfJenisDiet;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JenisDietAction.logger = logger;
    }

    public JenisDietBo getJenisDietBoProxy() {
        return jenisDietBoProxy;
    }

    public void setJenisDietBoProxy(JenisDietBo jenisDietBoProxy) {
        this.jenisDietBoProxy = jenisDietBoProxy;
    }

    public JenisDiet getJenisDiet() {
        return jenisDiet;
    }

    public void setJenisDiet(JenisDiet jenisDiet) {
        this.jenisDiet = jenisDiet;
    }


    public JenisDiet init(String kode, String flag){
        logger.info("[JenisDietAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JenisDiet> listOfResult = (List<JenisDiet>) session.getAttribute("listOfResultJenisDiet");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (JenisDiet jenisDiet : listOfResult){
                    if (kode.equalsIgnoreCase(jenisDiet.getIdJenisDiet()) && flag.equalsIgnoreCase(jenisDiet.getFlag())){
                        setJenisDiet(jenisDiet);
                        break;
                    }
                }
            } else {
                setJenisDiet(new JenisDiet());
            }
            logger.info("[[JenisDietAction.init] end process >>>>>");
        }
        return getJenisDiet();
    }

    @Override
    public String add() {
        logger.info("[JenisDietAction.add] start process >>>");

        JenisDiet addJenisDiet = new JenisDiet();
        setJenisDiet(addJenisDiet);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[Jenis Obat Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[Jenis Obat Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        JenisDiet editJenisDiet = new JenisDiet();
        if(itemFlag != null){
            try {
                editJenisDiet = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("edit action"+e.getMessage());
                throw new GeneralBOException("edit action, "+e.getMessage());
            }

            if(editJenisDiet != null) {
                setJenisDiet(editJenisDiet);
            } else {
                editJenisDiet.setFlag(itemFlag);
                setJenisDiet(editJenisDiet);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJenisDiet.setFlag(getFlag());
            setJenisDiet(editJenisDiet);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[JenisDietAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[JenisDietAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        JenisDiet deleteJenisDiet = new JenisDiet();

        if (itemFlag != null ) {
            try {
                deleteJenisDiet = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteJenisDiet != null) {
                setJenisDiet(deleteJenisDiet);

            } else {
                deleteJenisDiet.setFlag(itemFlag);
                setJenisDiet(deleteJenisDiet);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteJenisDiet.setFlag(itemFlag);
            setJenisDiet(deleteJenisDiet);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteJenisDietAction.delete] end process <<<");
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
        logger.info("[JenisDietAction.search] start process >>>");

        JenisDiet searchJenisDiet = getJenisDiet();
        List<JenisDiet> listOfsearchJenisDiet = new ArrayList();
        try {
            listOfsearchJenisDiet = jenisDietBoProxy.getByCriteria(searchJenisDiet);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultJenisDiet");
        session.setAttribute("listOfResultJenisDiet", listOfsearchJenisDiet);
        setJenisDiet(searchJenisDiet);
        logger.info("[JenisDietAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[jenisDietAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setJenisDiet(new JenisDiet());
        session.removeAttribute("listOfResultJenisDiet");
        logger.info("[jenisDietAction.initForm] end process >>>");
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

    public String getListJenisDiet(){
        logger.info("[CheckupDetailAction.getListJenisDiet] start process >>>");
        List<JenisDiet> jenisDietList = new ArrayList<>();
        JenisDiet jenisDiet = new JenisDiet();
        try {
            jenisDietList = jenisDietBoProxy.getByCriteria(jenisDiet);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListJenisDiet] Error when get jenis obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }
        listOfJenisDiet.addAll(jenisDietList);
        logger.info("[CheckupDetailAction.getListJenisDiet] end process <<<");
        return SUCCESS;
    }

    public String saveAdd(){
        logger.info("[JenisDietAction.saveAdd] start process >>>");

        try {
            JenisDiet jenisDiet = getJenisDiet();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jenisDiet.setCreatedWho(userLogin);
            jenisDiet.setLastUpdate(updateTime);
            jenisDiet.setCreatedDate(updateTime);
            jenisDiet.setLastUpdateWho(userLogin);
            jenisDiet.setAction("C");
            jenisDiet.setFlag("Y");

            jenisDietBoProxy.saveAdd(jenisDiet);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultJenisDiet");

        logger.info("[JenisDietAction.saveAdd] end process >>>");
        return "success_save_add";
    }


    public String saveDelete(){
        logger.info("[JenisDietAction.saveDelete] start process >>>");
        try {

            JenisDiet deleteJenisDiet = getJenisDiet();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteJenisDiet.setLastUpdate(updateTime);
            deleteJenisDiet.setLastUpdateWho(userLogin);
            deleteJenisDiet.setAction("D");
            deleteJenisDiet.setFlag("N");

            jenisDietBoProxy.saveDelete(deleteJenisDiet);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[JenisDietAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[JenisDietAction.saveEdit] start process >>>");
        try {
            JenisDiet editJenisDiet = getJenisDiet();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editJenisDiet.setLastUpdateWho(userLogin);
            editJenisDiet.setLastUpdate(updateTime);
            editJenisDiet.setAction("U");
            editJenisDiet.setFlag("Y");

            jenisDietBoProxy.saveEdit(editJenisDiet);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[JenisDietAction.saveEdit] end process <<<");

        return "success_save_edit";
    }




}