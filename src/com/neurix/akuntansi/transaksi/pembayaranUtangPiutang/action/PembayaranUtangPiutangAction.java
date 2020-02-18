package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.bo.PembayaranUtangPiutangBo;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutang;
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

public class PembayaranUtangPiutangAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PembayaranUtangPiutangAction.class);
    private PembayaranUtangPiutangBo pembayaranUtangPiutangBoProxy;
    private PembayaranUtangPiutang pembayaranUtangPiutang;
    private List<PembayaranUtangPiutang> listOfComboPembayaranUtangPiutang = new ArrayList<PembayaranUtangPiutang>();

    public List<PembayaranUtangPiutang> getListOfComboPembayaranUtangPiutang() {
        return listOfComboPembayaranUtangPiutang;
    }

    public void setListOfComboPembayaranUtangPiutang(List<PembayaranUtangPiutang> listOfComboPembayaranUtangPiutang) {
        this.listOfComboPembayaranUtangPiutang = listOfComboPembayaranUtangPiutang;
    }


    public PembayaranUtangPiutangBo getPembayaranUtangPiutangBoProxy() {
        return pembayaranUtangPiutangBoProxy;
    }

    public void setPembayaranUtangPiutangBoProxy(PembayaranUtangPiutangBo pembayaranUtangPiutangBoProxy) {
        this.pembayaranUtangPiutangBoProxy = pembayaranUtangPiutangBoProxy;
    }

    public PembayaranUtangPiutang getPembayaranUtangPiutang() {
        return pembayaranUtangPiutang;
    }

    public void setPembayaranUtangPiutang(PembayaranUtangPiutang pembayaranUtangPiutang) {
        this.pembayaranUtangPiutang = pembayaranUtangPiutang;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PembayaranUtangPiutangAction.logger = logger;
    }


    public String initComboPembayaranUtangPiutang() {
        logger.info("[PembayaranUtangPiutangAction.initComboPembayaranUtangPiutang] start process >>>");

        PembayaranUtangPiutang search = new PembayaranUtangPiutang();
        List<PembayaranUtangPiutang> pembayaranUtangPiutangList = new ArrayList();
        search.setFlag("Y");
        try {
            pembayaranUtangPiutangList = pembayaranUtangPiutangBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.initComboPembayaranUtangPiutang] Error when saving error,", e1);
            }
            logger.error("[PembayaranUtangPiutangAction.initComboPembayaranUtangPiutang] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboPembayaranUtangPiutang.addAll(pembayaranUtangPiutangList);
        logger.info("[PembayaranUtangPiutangAction.initComboPembayaranUtangPiutang] end process <<<");
        return SUCCESS;
    }

    public PembayaranUtangPiutang init(String kode, String flag){
        logger.info("[PembayaranUtangPiutangAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PembayaranUtangPiutang> listOfResult = (List<PembayaranUtangPiutang>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PembayaranUtangPiutang pembayaranUtangPiutang: listOfResult) {
                    if(kode.equalsIgnoreCase(pembayaranUtangPiutang.getPembayaranUtangPiutangId()) && flag.equalsIgnoreCase(pembayaranUtangPiutang.getFlag())){
                        setPembayaranUtangPiutang(pembayaranUtangPiutang);
                        break;
                    }
                }
            } else {
                setPembayaranUtangPiutang(new PembayaranUtangPiutang());
            }

            logger.info("[PembayaranUtangPiutangAction.init] end process >>>");
        }
        return getPembayaranUtangPiutang();
    }

    @Override
    public String add() {
        logger.info("[PembayaranUtangPiutangAction.add] start process >>>");
        PembayaranUtangPiutang addPembayaranUtangPiutang = new PembayaranUtangPiutang();
        setPembayaranUtangPiutang(addPembayaranUtangPiutang);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[PembayaranUtangPiutangAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PembayaranUtangPiutangAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PembayaranUtangPiutang editPembayaranUtangPiutang = new PembayaranUtangPiutang();

        if(itemFlag != null){
            try {
                editPembayaranUtangPiutang = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getPembayaranUtangPiutangByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PembayaranUtangPiutangAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PembayaranUtangPiutangAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPembayaranUtangPiutang != null) {
                setPembayaranUtangPiutang(editPembayaranUtangPiutang);
            } else {
                editPembayaranUtangPiutang.setFlag(itemFlag);
                editPembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
                setPembayaranUtangPiutang(editPembayaranUtangPiutang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
            editPembayaranUtangPiutang.setFlag(getFlag());
            setPembayaranUtangPiutang(editPembayaranUtangPiutang);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        HttpSession session = ServletActionContext.getRequest().getSession();

        logger.info("[PembayaranUtangPiutangAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PembayaranUtangPiutangAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PembayaranUtangPiutang deletePembayaranUtangPiutang = new PembayaranUtangPiutang();

        if (itemFlag != null ) {

            try {
                deletePembayaranUtangPiutang = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PembayaranUtangPiutangAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PembayaranUtangPiutangAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePembayaranUtangPiutang != null) {
                setPembayaranUtangPiutang(deletePembayaranUtangPiutang);

            } else {
                deletePembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
                deletePembayaranUtangPiutang.setFlag(itemFlag);
                setPembayaranUtangPiutang(deletePembayaranUtangPiutang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
            deletePembayaranUtangPiutang.setFlag(itemFlag);
            setPembayaranUtangPiutang(deletePembayaranUtangPiutang);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PembayaranUtangPiutangAction.delete] end process <<<");
        HttpSession session = ServletActionContext.getRequest().getSession();
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[PembayaranUtangPiutangAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PembayaranUtangPiutang deletePembayaranUtangPiutang = new PembayaranUtangPiutang();

        if (itemFlag != null ) {
            try {
                deletePembayaranUtangPiutang = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PembayaranUtangPiutangAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[PembayaranUtangPiutangAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePembayaranUtangPiutang != null) {
                setPembayaranUtangPiutang(deletePembayaranUtangPiutang);

            } else {
                deletePembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
                deletePembayaranUtangPiutang.setFlag(itemFlag);
                setPembayaranUtangPiutang(deletePembayaranUtangPiutang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
            deletePembayaranUtangPiutang.setFlag(itemFlag);
            setPembayaranUtangPiutang(deletePembayaranUtangPiutang);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PembayaranUtangPiutangAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[PembayaranUtangPiutangAction.saveEdit] start process >>>");
        try {
            PembayaranUtangPiutang editPembayaranUtangPiutang = getPembayaranUtangPiutang();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPembayaranUtangPiutang.setLastUpdateWho(userLogin);
            editPembayaranUtangPiutang.setLastUpdate(updateTime);
            editPembayaranUtangPiutang.setAction("U");
            editPembayaranUtangPiutang.setFlag("Y");

            pembayaranUtangPiutangBoProxy.saveEdit(editPembayaranUtangPiutang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PembayaranUtangPiutangAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PembayaranUtangPiutangAction.saveDelete] start process >>>");
        try {
            PembayaranUtangPiutang deletePembayaranUtangPiutang = getPembayaranUtangPiutang();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePembayaranUtangPiutang.setLastUpdate(updateTime);
            deletePembayaranUtangPiutang.setLastUpdateWho(userLogin);
            deletePembayaranUtangPiutang.setAction("U");
            deletePembayaranUtangPiutang.setFlag("N");

            pembayaranUtangPiutangBoProxy.saveDelete(deletePembayaranUtangPiutang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PembayaranUtangPiutangAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PembayaranUtangPiutangAction.saveAdd] start process >>>");

        try {
            PembayaranUtangPiutang pembayaranUtangPiutang = getPembayaranUtangPiutang();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pembayaranUtangPiutang.setCreatedWho(userLogin);
            pembayaranUtangPiutang.setLastUpdate(updateTime);
            pembayaranUtangPiutang.setCreatedDate(updateTime);
            pembayaranUtangPiutang.setLastUpdateWho(userLogin);
            pembayaranUtangPiutang.setAction("C");
            pembayaranUtangPiutang.setFlag("Y");

            pembayaranUtangPiutangBoProxy.saveAdd(pembayaranUtangPiutang);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PembayaranUtangPiutangAction.search] start process >>>");
        PembayaranUtangPiutang searchPembayaranUtangPiutang = getPembayaranUtangPiutang();
        List<PembayaranUtangPiutang> listOfsearchPembayaranUtangPiutang = new ArrayList();
        try {
            listOfsearchPembayaranUtangPiutang = pembayaranUtangPiutangBoProxy.getByCriteria(searchPembayaranUtangPiutang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.setAttribute("listOfResult", listOfsearchPembayaranUtangPiutang);

        logger.info("[PembayaranUtangPiutangAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PembayaranUtangPiutangAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[PembayaranUtangPiutangAction.initForm] end process >>>");
        return INPUT;
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
