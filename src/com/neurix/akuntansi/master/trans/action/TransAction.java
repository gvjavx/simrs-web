package com.neurix.akuntansi.master.trans.action;

import com.neurix.akuntansi.master.trans.bo.TransBo;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
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

public class TransAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TransAction.class);
    private TransBo transBoProxy;
    private Trans trans;
    private List<Trans> listOfComboTrans = new ArrayList<Trans>();
    private String tipe;
    private String isOtomatis;

    public String getIsOtomatis() {
        return isOtomatis;
    }

    public void setIsOtomatis(String isOtomatis) {
        this.isOtomatis = isOtomatis;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public List<Trans> getListOfComboTrans() {
        return listOfComboTrans;
    }

    public void setListOfComboTrans(List<Trans> listOfComboTrans) {
        this.listOfComboTrans = listOfComboTrans;
    }


    public TransBo getTransBoProxy() {
        return transBoProxy;
    }

    public void setTransBoProxy(TransBo transBoProxy) {
        this.transBoProxy = transBoProxy;
    }

    public Trans getTrans() {
        return trans;
    }

    public void setTrans(Trans trans) {
        this.trans = trans;
    }

    private List<Trans> initComboTrans;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TransAction.logger = logger;
    }


    public List<Trans> getInitComboTrans() {
        return initComboTrans;
    }

    public void setInitComboTrans(List<Trans> initComboTrans) {
        this.initComboTrans = initComboTrans;
    }

    public Trans init(String kode, String flag){
        logger.info("[TransAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Trans> listOfResult = (List<Trans>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Trans trans: listOfResult) {
                    if(kode.equalsIgnoreCase(trans.getTransId()) && flag.equalsIgnoreCase(trans.getFlag())){
                        setTrans(trans);
                        break;
                    }
                }
            } else {
                setTrans(new Trans());
            }

            logger.info("[TransAction.init] end process >>>");
        }
        return getTrans();
    }

    @Override
    public String add() {
        logger.info("[TransAction.add] start process >>>");
        Trans addTrans = new Trans();
        setTrans(addTrans);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TransAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TransAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Trans editTrans = new Trans();

        if(itemFlag != null){
            try {
                editTrans = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = transBoProxy.saveErrorMessage(e.getMessage(), "TransBO.getTransByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TransAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TransAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTrans != null) {
                setTrans(editTrans);
            } else {
                editTrans.setFlag(itemFlag);
                editTrans.setTransId(itemId);
                setTrans(editTrans);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTrans.setTransId(itemId);
            editTrans.setFlag(getFlag());
            setTrans(editTrans);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        setAddOrEdit(true);
        logger.info("[TransAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[TransAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Trans deleteTrans = new Trans();

        if (itemFlag != null ) {

            try {
                deleteTrans = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = transBoProxy.saveErrorMessage(e.getMessage(), "TransBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TransAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[TransAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTrans != null) {
                setTrans(deleteTrans);

            } else {
                deleteTrans.setTransId(itemId);
                deleteTrans.setFlag(itemFlag);
                setTrans(deleteTrans);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTrans.setTransId(itemId);
            deleteTrans.setFlag(itemFlag);
            setTrans(deleteTrans);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[TransAction.delete] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[TransAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Trans deleteTrans = new Trans();

        if (itemFlag != null ) {
            try {
                deleteTrans = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = transBoProxy.saveErrorMessage(e.getMessage(), "TransBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TransAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[TransAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTrans != null) {
                setTrans(deleteTrans);

            } else {
                deleteTrans.setTransId(itemId);
                deleteTrans.setFlag(itemFlag);
                setTrans(deleteTrans);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTrans.setTransId(itemId);
            deleteTrans.setFlag(itemFlag);
            setTrans(deleteTrans);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[TransAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[TransAction.saveEdit] start process >>>");
        try {
            Trans editTrans = getTrans();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editTrans.setLastUpdateWho(userLogin);
            editTrans.setLastUpdate(updateTime);
            editTrans.setAction("U");
            editTrans.setFlag("Y");

            transBoProxy.saveEdit(editTrans);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transBoProxy.saveErrorMessage(e.getMessage(), "TransBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TransAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TransAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TransAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[TransAction.saveDelete] start process >>>");
        try {
            Trans deleteTrans = getTrans();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteTrans.setLastUpdate(updateTime);
            deleteTrans.setLastUpdateWho(userLogin);
            deleteTrans.setAction("U");
            deleteTrans.setFlag("N");

            transBoProxy.saveDelete(deleteTrans);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transBoProxy.saveErrorMessage(e.getMessage(), "TransBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TransAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TransAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[TransAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[TransAction.saveAdd] start process >>>");
        try {
            Trans trans = getTrans();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if ("".equalsIgnoreCase(trans.getMaster())){
                trans.setMaster(null);
            }
            if ("".equalsIgnoreCase(trans.getTipePembayaran())){
                trans.setTipePembayaran(null);
            }

            trans.setCreatedWho(userLogin);
            trans.setLastUpdate(updateTime);
            trans.setCreatedDate(updateTime);
            trans.setLastUpdateWho(userLogin);
            trans.setAction("C");
            trans.setFlag("Y");

            transBoProxy.saveAdd(trans);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e);
//            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[TransAction.search] start process >>>");
        Trans searchTrans = getTrans();
        List<Trans> listOfsearchTrans = new ArrayList();
        try {
            listOfsearchTrans = transBoProxy.getByCriteria(searchTrans);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transBoProxy.saveErrorMessage(e.getMessage(), "TransBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TransAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TransAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            throw new GeneralBOException(e);
//            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchTrans);

        logger.info("[TransAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TransAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");

        logger.info("[TransAction.initForm] end process >>>");
        return INPUT;
    }
    public String initComboTrans() {
        logger.info("[TransAction.initComboTrans] start process >>>");

        Trans search = new Trans();
        List<Trans> listOfSearchTrans = new ArrayList();
        search.setFlag("Y");
        try {
            listOfSearchTrans = transBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transBoProxy.saveErrorMessage(e.getMessage(), "transBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TransAction.initComboTrans] Error when saving error,", e1);
            }
            logger.error("[TransAction.initComboTrans] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboTrans.addAll(listOfSearchTrans);
        logger.info("[TransAction.initComboTrans] end process <<<");

        return SUCCESS;
    }
    public String initComboTransaksi() {
        logger.info("[TransAction.initComboTransaksi] start process >>>");
        String tipe = getTipe();
        String isOtomatis = getIsOtomatis();
        Trans search = new Trans();
        List<Trans> listOfSearchTrans = new ArrayList();
        search.setFlag("Y");
        search.setTipeJurnalId(tipe);
        search.setIsOtomatis(isOtomatis);
        try {
            listOfSearchTrans = transBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transBoProxy.saveErrorMessage(e.getMessage(), "transBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TransAction.initComboTransaksi] Error when saving error,", e1);
            }
            logger.error("[TransAction.initComboTransaksi] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboTrans.addAll(listOfSearchTrans);
        logger.info("[TransAction.initComboTransaksi] end process <<<");

        return SUCCESS;
    }
    public String paging(){
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }
}
