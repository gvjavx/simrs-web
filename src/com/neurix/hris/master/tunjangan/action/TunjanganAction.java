package com.neurix.hris.master.tunjangan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.tipelibur.bo.TipeLiburBo;
import com.neurix.hris.master.tipelibur.model.TipeLibur;
import com.neurix.hris.master.tunjangan.bo.TunjanganBo;
import com.neurix.hris.master.tunjangan.model.Tunjangan;
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
public class TunjanganAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TunjanganAction.class);

    private Tunjangan tunjangan;
    private TunjanganBo tunjanganBoProxy;

    public Tunjangan getTunjangan() {
        return tunjangan;
    }

    public void setTunjangan(Tunjangan tunjangan) {
        this.tunjangan = tunjangan;
    }

    public TunjanganBo getTunjanganBoProxy() {
        return tunjanganBoProxy;
    }

    public void setTunjanganBoProxy(TunjanganBo tunjanganBoProxy) {
        this.tunjanganBoProxy = tunjanganBoProxy;
    }

    public Tunjangan init(String kode, String flag){
        logger.info("[TipePegawaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Tunjangan> listOfResult = (List<Tunjangan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Tunjangan tunjangan: listOfResult) {
                    if(kode.equalsIgnoreCase(tunjangan.getTunjanganId()) && flag.equalsIgnoreCase(tunjangan.getFlag())){
                        setTunjangan(tunjangan);
                        break;
                    }
                }
            } else {
                setTunjangan(new Tunjangan());
            }

            logger.info("[TipePegawaiAction.init] end process >>>");
        }
        return getTunjangan();
    }

    @Override
    public String add() {
        logger.info("[TipePegawaiAction.add] start process >>>");
        Tunjangan addTunjangan = new Tunjangan();
        setTunjangan(addTunjangan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipePegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Tunjangan editTunjangan = new Tunjangan();

        if(itemFlag != null){
            try {
                editTunjangan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tunjanganBoProxy.saveErrorMessage(e.getMessage(), "tipeLiburBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipePegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTunjangan != null) {
                setTunjangan(editTunjangan);
            } else {
                editTunjangan.setFlag(itemFlag);
                editTunjangan.setTunjanganId(itemId);
                setTunjangan(editTunjangan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTunjangan.setFlag(itemFlag);
            editTunjangan.setTunjanganId(itemId);
            setTunjangan(editTunjangan);
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

        Tunjangan deleteTunjangan = new Tunjangan();

        if(itemFlag != null){
            try {
                deleteTunjangan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tunjanganBoProxy.saveErrorMessage(e.getMessage(), "tipeLiburBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipePegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(deleteTunjangan != null) {
                setTunjangan(deleteTunjangan);
            } else {
                deleteTunjangan.setFlag(itemFlag);
                deleteTunjangan.setTunjanganId(itemId);
                setTunjangan(deleteTunjangan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTunjangan.setTunjanganId(itemId);
            deleteTunjangan.setFlag(getFlag());
            setTunjangan(deleteTunjangan);
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
            Tunjangan tunjangan = getTunjangan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tunjangan.setCreatedWho(userLogin);
            tunjangan.setLastUpdate(updateTime);
            tunjangan.setCreatedDate(updateTime);
            tunjangan.setLastUpdateWho(userLogin);
            tunjangan.setAction("C");
            tunjangan.setFlag("Y");


            tunjanganBoProxy.saveAdd(tunjangan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tunjanganBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveAdd] end process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[TipePegawaiAction.saveEdit] start process >>>");

        try {
            Tunjangan tunjangan = getTunjangan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tunjangan.setCreatedWho(tunjangan.getCreatedWho());
            tunjangan.setLastUpdate(updateTime);
            tunjangan.setCreatedDate(tunjangan.getCreatedDate());
            tunjangan.setLastUpdateWho(userLogin);
            tunjangan.setAction("U");
            tunjangan.setFlag("Y");


            tunjanganBoProxy.saveEdit(tunjangan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tunjanganBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveEdit] end process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        logger.info("[TipePegawaiAction.saveDelete] start process >>>");

        try {
            Tunjangan tunjangan = getTunjangan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tunjangan.setCreatedWho(tunjangan.getCreatedWho());
            tunjangan.setLastUpdate(updateTime);
            tunjangan.setCreatedDate(tunjangan.getCreatedDate());
            tunjangan.setLastUpdateWho(userLogin);
            tunjangan.setAction("U");
            tunjangan.setFlag("N");


            tunjanganBoProxy.saveEdit(tunjangan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tunjanganBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.saveDelete] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveDelete] end process >>>");
        return "save_delete";
    }

    @Override
    public String search() {
        logger.info("[TipePegawaiAction.search] start process >>>");

        Tunjangan tunjangan = getTunjangan();
        List<Tunjangan> listOfSearchTunjangan = new ArrayList();

        try {
            listOfSearchTunjangan = tunjanganBoProxy.getByCriteria(tunjangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tunjanganBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.getByCriteria");
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
        session.setAttribute("listOfResult", listOfSearchTunjangan);

        logger.info("[TipePegawaiAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipePegawaiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[TipePegawaiAction.initForm] end process >>>");
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
