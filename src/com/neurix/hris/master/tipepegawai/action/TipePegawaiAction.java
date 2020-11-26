package com.neurix.hris.master.tipepegawai.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.tipepegawai.bo.TipePegawaiBo;
import com.neurix.hris.master.tipepegawai.model.TipePegawai;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class TipePegawaiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TipePegawaiAction.class);

    private TipePegawai tipePegawai;
    private TipePegawaiBo tipePegawaiBoProxy;

    private List<TipePegawai> listComboTipePegawai = new ArrayList<TipePegawai>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipePegawaiAction.logger = logger;
    }

    public TipePegawai getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(TipePegawai tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public TipePegawaiBo getTipePegawaiBoProxy() {
        return tipePegawaiBoProxy;
    }

    public void setTipePegawaiBoProxy(TipePegawaiBo tipePegawaiBoProxy) {
        this.tipePegawaiBoProxy = tipePegawaiBoProxy;
    }

    public TipePegawai init(String kode, String flag){
        logger.info("[TipePegawaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TipePegawai> listOfResult = (List<TipePegawai>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (TipePegawai tipePegawai: listOfResult) {
                    if(kode.equalsIgnoreCase(tipePegawai.getTipePegawaiId()) && flag.equalsIgnoreCase(tipePegawai.getFlag())){
                        setTipePegawai(tipePegawai);
                        break;
                    }
                }
            } else {
                setTipePegawai(new TipePegawai());
            }

            logger.info("[TipePegawaiAction.init] end process >>>");
        }
        return getTipePegawai();
    }

    @Override
    public String add() {
        logger.info("[TipePegawaiAction.add] start process >>>");
        TipePegawai addTipePegawai = new TipePegawai();
        setTipePegawai(addTipePegawai);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipePegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipePegawai editTipePegawai = new TipePegawai();

        if(itemFlag != null){
            try {
                editTipePegawai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipePegawaiBoProxy.saveErrorMessage(e.getMessage(), "tipeLiburBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipePegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTipePegawai != null) {
                setTipePegawai(editTipePegawai);
            } else {
                editTipePegawai.setFlag(itemFlag);
                editTipePegawai.setTipePegawaiId(itemId);
                setTipePegawai(editTipePegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTipePegawai.setFlag(itemFlag);
            editTipePegawai.setTipePegawaiId(itemId);
            setTipePegawai(editTipePegawai);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[TipePegawaiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {

        logger.info("[TipePegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipePegawai deleteTipePegawai = new TipePegawai();

        if(itemFlag != null){
            try {
                deleteTipePegawai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipePegawaiBoProxy.saveErrorMessage(e.getMessage(), "tipeLiburBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipePegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(deleteTipePegawai != null) {
                setTipePegawai(deleteTipePegawai);
            } else {
                deleteTipePegawai.setFlag(itemFlag);
                deleteTipePegawai.setTipePegawaiId(itemId);
                setTipePegawai(deleteTipePegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipePegawai.setTipePegawaiId(itemId);
            deleteTipePegawai.setFlag(getFlag());
            setTipePegawai(deleteTipePegawai);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[TipePegawaiAction.saveAdd] start process >>>");

        try {
            TipePegawai tipePegawai = getTipePegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipePegawai.setCreatedWho(userLogin);
            tipePegawai.setLastUpdate(updateTime);
            tipePegawai.setCreatedDate(updateTime);
            tipePegawai.setLastUpdateWho(userLogin);
            tipePegawai.setAction("C");
            tipePegawai.setFlag("Y");


            tipePegawaiBoProxy.saveAdd(tipePegawai);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipePegawaiBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveAdd");
            } catch (GeneralBOException e1) {
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TipePegawaiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveAdd] end process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[TipePegawaiAction.saveEdit] start process >>>");



        try {
            TipePegawai tipePegawai = getTipePegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipePegawai.setCreatedWho(tipePegawai.getCreatedWho());
            tipePegawai.setLastUpdate(updateTime);
            tipePegawai.setCreatedDate(tipePegawai.getCreatedDate());
            tipePegawai.setLastUpdateWho(userLogin);
            tipePegawai.setAction("U");
            tipePegawai.setFlag("Y");


            tipePegawaiBoProxy.saveEdit(tipePegawai);
        }catch (GeneralBOException e) {
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveEdit] end process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        logger.info("[TipePegawaiAction.saveDelete] start process >>>");

        try {
            TipePegawai tipePegawai = getTipePegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipePegawai.setCreatedWho(tipePegawai.getCreatedWho());
            tipePegawai.setLastUpdate(updateTime);
            tipePegawai.setCreatedDate(tipePegawai.getCreatedDate());
            tipePegawai.setLastUpdateWho(userLogin);
            tipePegawai.setAction("U");
            tipePegawai.setFlag("N");

            tipePegawaiBoProxy.saveDelete(tipePegawai);
        }catch (GeneralBOException e) {
            throw new GeneralBOException( e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveDelete] end process >>>");
        return "save_delete";
    }

    @Override
    public String search() {
        logger.info("[TipePegawaiAction.search] start process >>>");

        TipePegawai tipePegawai = getTipePegawai();
        List<TipePegawai> listOfSearchTipePegawai = new ArrayList();

        try {
            listOfSearchTipePegawai = tipePegawaiBoProxy.getByCriteria(tipePegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipePegawaiBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchTipePegawai);

        logger.info("[TipePegawaiAction.search] end process <<<");
        return SUCCESS;
    }

    public void searchTipePegawai() {
        logger.info("[TipePegawaiAction.search] start process >>>");

        TipePegawai tipePegawai = new TipePegawai();
        tipePegawai.setFlag("Y");
        List<TipePegawai> listOfSearchTipePegawai = new ArrayList();

        try {
            listOfSearchTipePegawai = tipePegawaiBoProxy.getByCriteria(tipePegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipePegawaiBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.search] Error when saving error,", e1);
            }
            logger.error("[TipePegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        listComboTipePegawai.addAll(listOfSearchTipePegawai);
    }

    public List<TipePegawai> getListComboTipePegawai() {
        return listComboTipePegawai;
    }

    public void setListComboTipePegawai(List<TipePegawai> listComboTipePegawai) {
        this.listComboTipePegawai = listComboTipePegawai;
    }

    @Override
    public String initForm() {
        logger.info("[TipePegawaiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[TipePegawaiAction.initForm] end process >>>");
        return INPUT;
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

    public List<TipePegawai> searchTipePegawai(String branchId) {
        logger.info("[TipePegawaiAction.searchTipePegawai] start process >>>");

        TipePegawai searchTipePegawai = new TipePegawai();
        searchTipePegawai.setBranchId(branchId);

        List<TipePegawai> tipePegawaiList = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TipePegawaiBo tipePegawaiBo = (TipePegawaiBo) ctx.getBean("tipePegawaiBoProxy");

            tipePegawaiList = tipePegawaiBo.searchTipePegawaiByBranch(searchTipePegawai.getBranchId());
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TipePegawaiAction.searchTipePegawai] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return tipePegawaiList;
    }
}
