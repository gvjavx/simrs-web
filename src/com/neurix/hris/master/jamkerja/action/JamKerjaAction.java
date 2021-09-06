package com.neurix.hris.master.jamkerja.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.group.bo.GroupBo;
import com.neurix.hris.master.group.model.Group;
import com.neurix.hris.master.jamkerja.bo.JamKerjaBo;
import com.neurix.hris.master.jamkerja.model.JamKerja;
import com.neurix.hris.master.tipepegawai.bo.TipePegawaiBo;
import com.neurix.hris.master.tipepegawai.model.TipePegawai;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class JamKerjaAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(JamKerjaAction.class);

    private JamKerja jamKerja;
    private JamKerjaBo jamKerjaBoProxy;
    private TipePegawaiBo tipePegawaiBoProxy;

    private Boolean adminUnit = false;

    public Boolean getAdminUnit() {
        return adminUnit;
    }

    public void setAdminUnit(Boolean adminUnit) {
        this.adminUnit = adminUnit;
    }

    private List<TipePegawai> listOfComboTipePegawai = new ArrayList<TipePegawai>();

    public TipePegawaiBo getTipePegawaiBoProxy() {
        return tipePegawaiBoProxy;
    }

    public void setTipePegawaiBoProxy(TipePegawaiBo tipePegawaiBoProxy) {
        this.tipePegawaiBoProxy = tipePegawaiBoProxy;
    }

    public List<TipePegawai> getListOfComboTipePegawai() {
        return listOfComboTipePegawai;
    }

    public void setListOfComboTipePegawai(List<TipePegawai> listOfComboTipePegawai) {
        this.listOfComboTipePegawai = listOfComboTipePegawai;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JamKerjaAction.logger = logger;
    }

    public JamKerja getJamKerja() {
        return jamKerja;
    }

    public void setJamKerja(JamKerja jamKerja) {
        this.jamKerja = jamKerja;
    }

    public JamKerjaBo getJamKerjaBoProxy() {
        return jamKerjaBoProxy;
    }

    public void setJamKerjaBoProxy(JamKerjaBo jamKerjaBoProxy) {
        this.jamKerjaBoProxy = jamKerjaBoProxy;
    }

    public JamKerja init(String kode, String flag){
        logger.info("[GroupShiftAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JamKerja> listOfResult = (List<JamKerja>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (JamKerja jamKerja: listOfResult) {
                    if(kode.equalsIgnoreCase(jamKerja.getJamKerjaId()) && flag.equalsIgnoreCase(jamKerja.getFlag())){
                        setJamKerja(jamKerja);
                        break;
                    }
                }
            } else {
                setJamKerja(new JamKerja());
            }

            logger.info("[GroupShiftAction.init] end process >>>");
        }
        return getJamKerja();
    }

    @Override
    public String add() {
        logger.info("[JamKerjaAction.add] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        JamKerja jamKerja = new JamKerja();
        if (branchId != null){
            jamKerja.setBranchId(branchId);
        }else {
            jamKerja.setBranchId("");
        }

        setJamKerja(jamKerja);
        setAddOrEdit(true);
        setAdd(true);

        session.removeAttribute("listOfResult");

        logger.info("[JamKerjaAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[JamKerjaAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        JamKerja editJamKerja = new JamKerja();

        if(itemFlag != null){
            try {
                editJamKerja = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jamKerjaBoProxy.saveErrorMessage(e.getMessage(), "groupBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[JamKerjaAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[JamKerjaAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editJamKerja != null) {
                setJamKerja(editJamKerja);
            } else {
                editJamKerja.setFlag(itemFlag);
                editJamKerja.setJamKerjaId(itemId);
                setJamKerja(editJamKerja);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJamKerja.setJamKerjaId(itemId);
            editJamKerja.setFlag(getFlag());
            setJamKerja(editJamKerja);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        String branchUser = CommonUtil.userBranchLogin();
        if(CommonConstant.BRANCH_KP.equalsIgnoreCase(branchUser)){
            setAdminUnit(true);
        }else{
            setAdminUnit(false);
        }

        setAddOrEdit(true);
        logger.info("[JamKerjaAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[JamKerjaAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        JamKerja editJamKerja = new JamKerja();

        if(itemFlag != null){
            try {
                editJamKerja = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jamKerjaBoProxy.saveErrorMessage(e.getMessage(), "groupBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[JamKerjaAction.delete] Error when retrieving edit data,", e1);
                }
                logger.error("[JamKerjaAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editJamKerja != null) {
                setJamKerja(editJamKerja);
            } else {
                editJamKerja.setFlag(itemFlag);
                editJamKerja.setJamKerjaId(itemId);
                setJamKerja(editJamKerja);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJamKerja.setJamKerjaId(itemId);
            editJamKerja.setFlag(getFlag());
            setJamKerja(editJamKerja);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[JamKerjaAction.delete] end process >>>");
        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[JamKerjaAction.saveAdd] start process >>>");

        try {
            JamKerja jamKerja = getJamKerja();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jamKerja.setCreatedWho(userLogin);
            jamKerja.setLastUpdate(updateTime);
            jamKerja.setCreatedDate(updateTime);
            jamKerja.setLastUpdateWho(userLogin);
            jamKerja.setAction("C");
            jamKerja.setFlag("Y");

            jamKerjaBoProxy.saveAdd(jamKerja);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jamKerjaBoProxy.saveErrorMessage(e.getMessage(), "GroupShifBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[JamKerjaAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JamKerjaAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[JamKerjaAction.saveAdd] end process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[JamKerjaAction.saveEdit] start process >>>");

        try {
            JamKerja jamKerja = getJamKerja();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jamKerja.setCreatedWho(jamKerja.getCreatedWho());
            jamKerja.setLastUpdate(updateTime);
            jamKerja.setCreatedDate(jamKerja.getCreatedDate());
            jamKerja.setLastUpdateWho(userLogin);
            jamKerja.setAction("U");
            jamKerja.setFlag("Y");


            jamKerjaBoProxy.saveEdit(jamKerja);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jamKerjaBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[JamKerjaAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JamKerjaAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[JamKerjaAction.saveEdit] end process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        logger.info("[JamKerjaAction.saveDelete] start process >>>");

        try {
            JamKerja jamKerja = getJamKerja();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jamKerja.setCreatedWho(jamKerja.getCreatedWho());
            jamKerja.setLastUpdate(updateTime);
            jamKerja.setCreatedDate(jamKerja.getCreatedDate());
            jamKerja.setLastUpdateWho(userLogin);
            jamKerja.setAction("U");
            jamKerja.setFlag("N");

            jamKerjaBoProxy.saveEdit(jamKerja);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jamKerjaBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[JamKerjaAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JamKerjaAction.saveDelete] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[JamKerjaAction.saveDelete] end process >>>");
        return "save_delete";
    }

    @Override
    public String search() {
        logger.info("[JamKerjaAction.search] start process >>>");

        JamKerja jamKerja1 = getJamKerja();
        List<JamKerja> listOfSearchJamKerja = new ArrayList();

        try {
            listOfSearchJamKerja = jamKerjaBoProxy.getByCriteria(jamKerja1);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jamKerjaBoProxy.saveErrorMessage(e.getMessage(), "JamKerjaAction.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GroupShiftAction.save] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        String branchId = CommonUtil.userBranchLogin();
        JamKerja data = new JamKerja();
        if (branchId != null){
            data.setBranchId(branchId);
        }else {
            data.setBranchId("");
        }

        jamKerja = data;

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchJamKerja);

        logger.info("[JamKerjaAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[JamKerjaAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        String branchId = CommonUtil.userBranchLogin();
        JamKerja data = new JamKerja();
        if (branchId != null){
            data.setBranchId(branchId);
        }else {
            data.setBranchId("");
        }

        jamKerja = data;

        session.removeAttribute("listOfResult");
        logger.info("[JamKerjaAction.initForm] end process >>>");
        return INPUT;
    }

    public String initComboTipePegawai() {

        TipePegawai tipePegawai = new TipePegawai();
        tipePegawai.setFlag("Y");

        List<TipePegawai> listOfTipePegawai = new ArrayList<TipePegawai>();
        try {
            listOfTipePegawai = tipePegawaiBoProxy.getByCriteria(tipePegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipePegawaiBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboTipePegawai.addAll(listOfTipePegawai);

        return "init_combo_tipe_pegawai";
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
