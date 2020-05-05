package com.neurix.simrs.master.ruangan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
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
    private KelasRuanganBo kelasRuanganBoProxy;
    private List<Ruangan> listOfRuangan = new ArrayList<>();
    private List<KelasRuangan> listOfComboKelasRuangan = new ArrayList<>();

    public void setKelasRuanganBoProxy(KelasRuanganBo kelasRuanganBoProxy) {
        this.kelasRuanganBoProxy = kelasRuanganBoProxy;
    }

    public List<KelasRuangan> getListOfComboKelasRuangan() {
        return listOfComboKelasRuangan;
    }

    public void setListOfComboKelasRuangan(List<KelasRuangan> listOfComboKelasRuangan) {
        this.listOfComboKelasRuangan = listOfComboKelasRuangan;
    }

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
        List<Ruangan> listOfResult = (List<Ruangan>) session.getAttribute("listOfResultRuangan");

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
        setAdd(true);
        setAddOrEdit(true);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId != null){
            addRuangan.setBranchUser(branchId);
            addRuangan.setBranchId(branchId);
        }else {
            addRuangan.setBranchUser("");
            addRuangan.setBranchId("");
        }
        ruangan = addRuangan;

        setRuangan(addRuangan);
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[Ruangan.add] stop process");
        return "add";
    }

    @Override
    public String edit() {
        logger.info("[RuanganAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Ruangan editRuangan = new Ruangan();

        if(itemFlag != null){
            try {
                editRuangan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ruanganBoProxy.saveErrorMessage(e.getMessage(), "RuanganBO.getPayrollSkalaGajiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[RuanganAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[RuanganAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRuangan != null) {
                String branchId = CommonUtil.userBranchLogin();
//                Ruangan data = new Ruangan();
                if (branchId != null){
                    editRuangan.setBranchUser(branchId);
                }else {
                    editRuangan.setBranchUser("");
                }

                setRuangan(editRuangan);
            } else {
                editRuangan.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setRuangan(editRuangan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editRuangan.setFlag(getFlag());
            setRuangan(editRuangan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[RuanganAction.edit] end process >>>");
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

        String branchId = CommonUtil.userBranchLogin();
        Ruangan data = new Ruangan();
        if (branchId != null){
            data.setBranchUser(branchId);
        }else {
            data.setBranchUser("");
        }
        ruangan = data;

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultRuangan");
        session.setAttribute("listOfResultRuangan", listOfRuangan);

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
        session.removeAttribute("listOfResultRuangan");
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

    public String initComboKelasRuangan(){

        KelasRuangan kelasRuangan = new KelasRuangan();
        kelasRuangan.setFlag("Y");

        List<KelasRuangan> listOfKelasRuangan = new ArrayList<KelasRuangan>();
        try {
            listOfKelasRuangan = kelasRuanganBoProxy.getByCriteria(kelasRuangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kelasRuanganBoProxy.saveErrorMessage(e.getMessage(), "KelasRuanganBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RuanganAction.initComboRole] Error when saving error,", e1);
            }
            logger.error("[RuanganAction.initComboRole] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboKelasRuangan.addAll(listOfKelasRuangan);

        return "init_combo_kelasruangan";
    }

    @Override
    public String initForm() {
        logger.info("[PendapatanDokter.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Ruangan data = new Ruangan();
        if (branchId != null){
            data.setBranchUser(branchId);
            data.setBranchId(branchId);
        }else {
            data.setBranchUser("");
            data.setBranchId("");
        }
        ruangan = data;

        session.removeAttribute("listOfResultRuangan");
        logger.info("[RuanganAction.initForm] end process >>>");

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
