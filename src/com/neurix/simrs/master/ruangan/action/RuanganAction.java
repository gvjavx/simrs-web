package com.neurix.simrs.master.ruangan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pasien.action.PasienAction;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsRegistrasiOnlineEntity;
import com.neurix.simrs.transaksi.antrianonline.model.RegistrasiOnline;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RuanganAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(Ruangan.class);
    private Ruangan ruangan;
    private RuanganBo ruanganBoProxy;
    private List<Ruangan> listOfRuangan = new ArrayList<>();

    public Ruangan getRuangan() {
        return ruangan;
    }

    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }

    public RuanganBo getRuanganBoProxy() {
        return ruanganBoProxy;
    }

    public void setRuanganBoProxy(RuanganBo ruanganBoProxy) {
        this.ruanganBoProxy = ruanganBoProxy;
    }

    public List<Ruangan> getListOfRuangan() {
        return listOfRuangan;
    }

    public void setListOfRuangan(List<Ruangan> listOfRuangan) {
        this.listOfRuangan = listOfRuangan;
    }

    public Ruangan init(String kode, String flag){
        logger.info("[RuanganAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Ruangan> listOfResult = (List<Ruangan>) session.getAttribute("listOfResult");
        List<Ruangan> listPasien = new ArrayList<>();

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (Ruangan ruangan : listOfResult){
                    if (kode.equalsIgnoreCase(ruangan.getIdRuangan()) && flag.equalsIgnoreCase(ruangan.getFlag())){
                        setRuangan(ruangan);
                        break;
                    }
                }
            } else {
                setRuangan(new Ruangan());
            }
            logger.info("[PasienAction.init] end process >>>>>");
        }
        return getRuangan();
    }

    @Override
    public String add() {
        logger.info("[RuanganAction.add] start process");

        Ruangan addRuangan = new Ruangan();
        setRuangan(addRuangan);
        setAdd(true);
        setAddOrEdit(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[Ruangan.add] stop process");
        return "add";
    }

    @Override
    public String edit() {
        logger.info("[RuangAction.edit] start process >>>>");
        String ruanganId = getId();
        String ruanganFlag = getFlag();

        Ruangan editRuangan = new Ruangan();

        if (ruanganFlag != null){
            try{
                editRuangan = init(ruanganId, ruanganFlag);
            }catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = ruanganBoProxy.saveErrorMessage(e.getMessage(), "ruanganBO.getBelajarByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[RuanganAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[RuanganAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editRuangan != null){
                setRuangan(editRuangan);
            }else {
                editRuangan.setFlag(ruanganFlag);
                editRuangan.setIdRuangan(ruanganId);
                setRuangan(editRuangan);
                addActionError("Error, Unable to find data with id = "+ ruanganId);
                return "failure";
            }
        }else {
            editRuangan.setIdRuangan(ruanganId);
            editRuangan.setFlag(ruanganFlag);
            setRuangan(editRuangan);
            addActionError("Error, Unable to find data with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PasienAction.edit] end process >>>>>");
        return "edit";
    }

    @Override
    public String delete() {
        logger.info("[RuanganAction.delete] start process");

        String ruanganId = getId();
        String ruanganFlag = getFlag();

        Ruangan deleteRuangan = new Ruangan();

        if (flag != null){
            try{
                deleteRuangan = init(getId(), getFlag());
            }catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = ruanganBoProxy.saveErrorMessage(e.getMessage(), "RuanganBO.getById");
                } catch (GeneralBOException e1) {
                    logger.error("[RuanganAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[RuanganAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteRuangan != null) {
                setRuangan(deleteRuangan);
            }else {
                deleteRuangan.setIdRuangan(ruanganId);
                deleteRuangan.setFlag(ruanganFlag);
                setRuangan(deleteRuangan);
                addActionError("Error, Unable to find data with id = " + ruanganId);
                return "failure";
            }
        }else {
            deleteRuangan.setIdRuangan(ruanganId);
            deleteRuangan.setFlag(ruanganFlag);
            setRuangan(deleteRuangan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[RuanganAction.delete] end process <<<<<<");
        return "delete";
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
        logger.info("[RuanganAction.search] start process");

        Ruangan searchRuangan = getRuangan();
        List<Ruangan> listOfRuangan = new ArrayList<>();

        try{
            listOfRuangan = ruanganBoProxy.getByCriteria(searchRuangan);
        }catch (GeneralBOException e) {
            logger.error("[RuanganAction.getByCriteria] Error when get by criteria ruangan, please inform to your admin.", e);
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRuangan);

        logger.info("[RuanganAction.search] end process <<<");

        return "search";
    }

    public String saveAdd(){
        logger.info(("[RuanganAction.saveAdd] start process"));

        try{
            Ruangan ruangan = getRuangan();
            String userLogin = CommonUtil.userLogin();
            String branchId = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            ruangan.setBranchId(branchId);
            ruangan.setFlag("Y");
            ruangan.setAction("C");
            ruangan.setCreatedDate(updateTime);
            ruangan.setCreatedWho(userLogin);
            ruangan.setLastUpdate(updateTime);
            ruangan.setLastUpdateWho(userLogin);

            ruanganBoProxy.saveAdd(ruangan);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = ruanganBoProxy.saveErrorMessage(e.getMessage(), "pasienBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[pasienAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[pasienAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        logger.info("[pasienAction.saveAdd] end process >>>>");
        return "add";
    }

    public String saveEdit(){
        logger.info("[PasienAction.saveEdit] start process >>>");
        try {

            Ruangan editRuangan = getRuangan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editRuangan.setLastUpdateWho(userLogin);
            editRuangan.setLastUpdate(updateTime);
            editRuangan.setAction("U");
            editRuangan.setFlag("Y");

            ruanganBoProxy.saveEdit(editRuangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ruanganBoProxy.saveErrorMessage(e.getMessage(), "RuanganBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[RuanganAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RuanganAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[RuanganAction.saveEdit] end process <<<");
        return "edit";
    }

    public String saveDelete(){
        logger.info("[RuanganAction.saveDelete] start process >>>>");

        try{
            Ruangan deleteRuangan = getRuangan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteRuangan.setLastUpdate(updateTime);
            deleteRuangan.setLastUpdateWho(userLogin);
            deleteRuangan.setAction("U");
            deleteRuangan.setFlag("N");

            ruanganBoProxy.saveDelete(deleteRuangan);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = ruanganBoProxy.saveErrorMessage(e.getMessage(), "RuanganBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[RuanganAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RuanganAction.saveDelete] Error when editing item pasien," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[RuanganAction.saveDelete] end process <<<");
        return "delete";
    }

    @Override
    public String initForm() {
        return "search";
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
