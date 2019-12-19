package com.neurix.simrs.master.pasien.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.belajar.model.Belajar;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PasienAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(PasienAction.class);

    private Pasien pasien;
    private PasienBo pasienBoProxy;
    private List<Pasien> listOfpasien = new ArrayList<>();

    public List<Pasien> getListOfpasien() {
        return listOfpasien;
    }

    public void setListOfpasien(List<Pasien> listOfpasien) {
        this.listOfpasien = listOfpasien;
    }

    public Pasien init(String kode, String flag){
        logger.info("[PasienAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pasien> listOfResult = (List<Pasien>) session.getAttribute("listOfResult");
        List<Pasien> listPasien = new ArrayList<>();

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (Pasien pasien : listOfResult){
                    if (kode.equalsIgnoreCase(pasien.getIdPasien()) && flag.equalsIgnoreCase(pasien.getFlag())){
                        String desaId = pasien.getDesaId();
                        listPasien = pasienBoProxy.getDataPasien(desaId);

                        for (Pasien data:listPasien){
                            pasien.setProvinsiId(data.getProvinsi());
                            pasien.setKotaId(data.getKota());
                            pasien.setKecamatanId(data.getKecamatan());
                            pasien.setDesa(data.getDesa());
                            pasien.setDesaId(data.getDesaId());
                        }
                        setPasien(pasien);
                        break;
                    }
                }
            } else {
                setPasien(new Pasien());
            }
            logger.info("[PasienAction.init] end process >>>>>");
        }
        return getPasien();
    }

    @Override
    public String add() {
        logger.info("[PasienAction.add] start process");

        Pasien addPasien = new Pasien();
        setPasien(addPasien);
        setAdd(true);
        setAddOrEdit(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[Pasien.add] stop process");
        return "add";
    }

    @Override
    public String edit() {
        logger.info("[PasienAction.edit] start process >>>>");
        String pasienId = getId();
        String pasienFlag = getFlag();

        Pasien editPasien = new Pasien();

        if (pasienFlag != null){
            try{
                editPasien = init(pasienId, pasienFlag);
            }catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "pasienBO.getBelajarByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PasienAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PasienAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editPasien != null){
                setPasien(editPasien);
            }else {
                editPasien.setFlag(pasienFlag);
                editPasien.setIdPasien(pasienId);
                setPasien(editPasien);
                addActionError("Error, Unable to find data with id = "+ pasienId);
                return "failure";
            }
        }else {
            editPasien.setIdPasien(pasienId);
            editPasien.setFlag(pasienFlag);
            setPasien(editPasien);
            addActionError("Error, Unable to find data with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PasienAction.edit] end process >>>>>");
        return "edit";
    }

    @Override
    public String delete() {
        logger.info("[PasienAction.delete] start process");

        String idPasien = getId();
        String flag = getFlag();
        Pasien deletePasien = new Pasien();

        if (flag != null){
            try{
                deletePasien = init(idPasien, flag);
            }catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PasienAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PasienAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePasien != null){
                setPasien(deletePasien);
            }else {
                deletePasien.setIdPasien(idPasien);
                deletePasien.setFlag(flag);
                setPasien(deletePasien);
                addActionError("Error, Unable to find data with id = " + idPasien);
                return "failure";
            }
        }else {
            deletePasien.setIdPasien(idPasien);
            deletePasien.setFlag(flag);
            setPasien(deletePasien);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PasienAction.delete] end process <<<<<<");
        return "delete";
    }

    @Override
    public String view() {
        return "view";
    }

    @Override
    public String save() {
        return "save";
    }

    @Override
    public String search() {
        logger.info("[PasienAction.search] start process");

        Pasien searchPesien = getPasien();
        List<Pasien> listOfPasien = new ArrayList<>();

        try{
            listOfPasien = pasienBoProxy.getByCriteria(searchPesien);
        }catch (GeneralBOException e) {
            logger.error("[PasienAction.getByCriteria] Error when get by criteria pasien, please inform to your admin.", e);
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfPasien);

        logger.info("[BelajarAction.search] end process <<<");

        return "search";
    }

    public String saveAdd(){
        logger.info(("[PasienAction.saveAdd] start process"));

        try{
            Pasien pasien = getPasien();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pasien.setFlag("Y");
            pasien.setAction("C");
            pasien.setCreatedDate(updateTime);
            pasien.setCreatedWho(userLogin);
            pasien.setLastUpdate(updateTime);
            pasien.setLastUpdateWho(userLogin);

            pasienBoProxy.saveAdd(pasien);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "pasienBO.saveAdd");
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

            Pasien editPasien = getPasien();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPasien.setLastUpdateWho(userLogin);
            editPasien.setLastUpdate(updateTime);
            editPasien.setAction("U");
            editPasien.setFlag("Y");

            pasienBoProxy.saveEdit(editPasien);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PasienAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PasienAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PasienAction.saveEdit] end process <<<");
        return "edit";
    }

    public String saveDelete(){
        logger.info("[pasienAction.saveDelete] start process >>>>");

        try{
            Pasien deletePasien = getPasien();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePasien.setLastUpdate(updateTime);
            deletePasien.setLastUpdateWho(userLogin);
            deletePasien.setAction("U");
            deletePasien.setFlag("N");

            pasienBoProxy.saveDelete(deletePasien);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PasienAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PasienAction.saveDelete] Error when editing item pasien," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[PasienAction.saveDelete] end process <<<");
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

    public List getListComboPasien(String query){
        logger.info("[PasienAction.getListComboPasien] start process >>>");

        List<Pasien> listOfPasien = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");

        try {
            listOfPasien = pasienBo.getListComboPasien(query);
        } catch (GeneralBOException e) {
            logger.error("[PasienAction.getListComboPasien] Error when get combo pasien, please inform to your admin.", e);
        }

        logger.info("[PasienAction.getListComboPasien] end process <<<");
        return listOfPasien;
    }

    public String getListComboSelectPasien(){
        logger.info("[PasienAction.getListComboSelectPasien] start process >>>");

        List<Pasien> pasienList = new ArrayList<>();
        Pasien pasien = new Pasien();

        try {
            pasienList = pasienBoProxy.getByCriteria(pasien);
        }catch (GeneralBOException e){
            logger.error("[PasienAction.getListComboSelectPasien] Error when get data pasien ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfpasien.addAll(pasienList);
        logger.info("[PasienAction.getListComboSelectPasien] end process <<<");
        return SUCCESS;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public void setPasienBoProxy(PasienBo pasienBoProxy) {
        this.pasienBoProxy = pasienBoProxy;
    }
}