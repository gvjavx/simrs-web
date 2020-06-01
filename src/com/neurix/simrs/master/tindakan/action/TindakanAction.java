package com.neurix.simrs.master.tindakan.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.kategoritindakanina.bo.KategoriTindakanInaBo;
import com.neurix.simrs.master.kategoritindakanina.model.KategoriTindakanIna;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TindakanAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TindakanAction.class);
    private Tindakan tindakan;
    private TindakanBo tindakanBoProxy;
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private BranchBo branchBoProxy;
    private KategoriTindakanInaBo kategoriTindakanInaBoProxy;

    private List<KategoriTindakan> listOfComboKategoriTindakan = new ArrayList<KategoriTindakan>();
    private List<Branch> listOfComboBranches = new ArrayList<Branch>();
    private List<KategoriTindakanIna> listOfComboKategoriTindakanIna = new ArrayList<KategoriTindakanIna>();

    public List<KategoriTindakanIna> getListOfComboKategoriTindakanIna() {
        return listOfComboKategoriTindakanIna;
    }

    public void setListOfComboKategoriTindakanIna(List<KategoriTindakanIna> listOfComboKategoriTindakanIna) {
        this.listOfComboKategoriTindakanIna = listOfComboKategoriTindakanIna;
    }

    public KategoriTindakanInaBo getKategoriTindakanInaBoProxy() {
        return kategoriTindakanInaBoProxy;
    }

    public void setKategoriTindakanInaBoProxy(KategoriTindakanInaBo kategoriTindakanInaBoProxy) {
        this.kategoriTindakanInaBoProxy = kategoriTindakanInaBoProxy;
    }

    public List<Branch> getListOfComboBranches() {
        return listOfComboBranches;
    }

    public void setListOfComboBranches(List<Branch> listOfComboBranches) {
        this.listOfComboBranches = listOfComboBranches;
    }

    public List<KategoriTindakan> getListOfComboKategoriTindakan() {
        return listOfComboKategoriTindakan;
    }

    public void setListOfComboKategoriTindakan(List<KategoriTindakan> listOfComboKategoriTindakan) {
        this.listOfComboKategoriTindakan = listOfComboKategoriTindakan;
    }

    public void setKategoriTindakanBoProxy(KategoriTindakanBo kategoriTindakanBoProxy) {
        this.kategoriTindakanBoProxy = kategoriTindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public Tindakan getTindakan() {
        return tindakan;
    }

    public void setTindakan(Tindakan tindakan) {
        this.tindakan = tindakan;
    }

    public BranchBo getBranchBoProxy() {
        return branchBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public KategoriTindakanBo getKategoriTindakanBoProxy() {
        return kategoriTindakanBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TindakanAction.logger = logger;
    }

    public TindakanBo getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public Tindakan init(String kode, String flag){
        logger.info("[TindakanAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Tindakan> listOfResult = (List<Tindakan>) session.getAttribute("listOfResultTindakan");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (Tindakan tindakan : listOfResult){
                    if (kode.equalsIgnoreCase(tindakan.getIdTindakan()) && flag.equalsIgnoreCase(tindakan.getFlag())){
                        setTindakan(tindakan);
                        break;
                    }
                }
            } else {
                setTindakan(new Tindakan());
            }
            logger.info("[TindakanAction.init] end process >>>>>");
        }
        return getTindakan();
    }

    @Override
    public String add() {
        logger.info("[TindakanAction.add] start process >>>");
        Tindakan addTindakan = new Tindakan();
        setAddOrEdit(true);
        setAdd(true);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId != null){
            addTindakan.setBranchUser(branchId);
            addTindakan.setBranchId(branchId);
        }else {
            addTindakan.setBranchUser("");
            addTindakan.setBranchId("");
        }
        tindakan = addTindakan;
        setTindakan(addTindakan);
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[TindakanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TindakanAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Tindakan editTindakan = new Tindakan();

        if(itemFlag != null){
            try {
                editTindakan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tindakanBoProxy.saveErrorMessage(e.getMessage(), "TindakanBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TindakanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TindakanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTindakan != null) {
                String branchId = CommonUtil.userBranchLogin();
//                Ruangan data = new Ruangan();
                if (branchId != null){
                    editTindakan.setBranchUser(branchId);
                }else {
                    editTindakan.setBranchUser("");
                }

                setTindakan(editTindakan);
            } else {
                editTindakan.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setTindakan(editTindakan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editTindakan.setFlag(getFlag());
            setTindakan(editTindakan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[TindakanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[TindakanAction.delete] start process");

        String tindakanId = getId();
        String tindakanFlag = getFlag();

        Tindakan deleteTindakan = new Tindakan();

        if (flag != null){
            try{
                deleteTindakan = init(getId(), getFlag());
            }catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = tindakanBoProxy.saveErrorMessage(e.getMessage(), "TindakanBO.getById");
                } catch (GeneralBOException e1) {
                    logger.error("[TindakanAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[TindakanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTindakan != null) {
                setTindakan(deleteTindakan);
            }else {
                deleteTindakan.setIdTindakan(tindakanId);
                deleteTindakan.setFlag(tindakanFlag);
                setTindakan(deleteTindakan);
                addActionError("Error, Unable to find data with id = " + tindakanId);
                return "failure";
            }
        }else {
            deleteTindakan.setIdTindakan(tindakanId);
            deleteTindakan.setFlag(tindakanFlag);
            setTindakan(deleteTindakan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[TindakanAction.delete] end process <<<<<<");
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
        logger.info("[TindakanAction.search] start process >>>");

        Tindakan searchTindakan = getTindakan();
        List<Tindakan> listOfsearchTindakan = new ArrayList();

        try {
            listOfsearchTindakan = tindakanBoProxy.getByCriteria(searchTindakan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tindakanBoProxy.saveErrorMessage(e.getMessage(), "TindakanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TindakanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        String branchId = CommonUtil.userBranchLogin();
        Tindakan data = new Tindakan();
        if (branchId != null){
            data.setBranchUser(branchId);
        }else {
            data.setBranchUser("");
        }
        tindakan = data;

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultTindakan");
        session.setAttribute("listOfResultTindakan", listOfsearchTindakan);

        logger.info("[TindakanAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TindakanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Tindakan data = new Tindakan();
        if (branchId != null){
            data.setBranchUser(branchId);
            data.setBranchId(branchId);
        }else {
            data.setBranchUser("");
            data.setBranchId("");
        }
        tindakan = data;

        session.removeAttribute("listOfResultTindakan");
        logger.info("[TindakanAction.initForm] end process >>>");
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

    public String initComboKategori() {

        KategoriTindakan kategoriTindakan = new KategoriTindakan();
        kategoriTindakan.setFlag("Y");

        List<KategoriTindakan> listOfKategoriTidakans = new ArrayList<KategoriTindakan>();
        try {
            listOfKategoriTidakans = kategoriTindakanBoProxy.getDataByCriteria(kategoriTindakan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriTindakanBoProxy.saveErrorMessage(e.getMessage(), "KategoriTindakanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.initComboKategori] Error when saving error,", e1);
            }
            logger.error("[TindakanAction.initComboKategori] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboKategoriTindakan.addAll(listOfKategoriTidakans);

        return "init_combo";
    }

    public String initComboKategoriIna() {

        KategoriTindakanIna kategoriTindakanIna = new KategoriTindakanIna();
        kategoriTindakanIna.setFlag("Y");

        List<KategoriTindakanIna> listOfKategoriIna = new ArrayList<KategoriTindakanIna>();
        try {
            listOfKategoriIna = kategoriTindakanInaBoProxy.getByCriteria(kategoriTindakanIna);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriTindakanInaBoProxy.saveErrorMessage(e.getMessage(), "KategoriTindakanInaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.initComboKategoriTindakanIna] Error when saving error,", e1);
            }
            logger.error("[KategoriTindakanInaAction.initComboKategoriTindakanIna] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboKategoriTindakanIna.addAll(listOfKategoriIna);

        return "init_combo";
    }

    public String initComboBranch() {

        Branch braches = new Branch();
        braches.setFlag("Y");

        List<Branch> listOfBranches = new ArrayList<Branch>();
        try {
            listOfBranches = branchBoProxy.getByCriteria(braches);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[TindakanAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboBranches.addAll(listOfBranches);

        return "init_combo";
    }

    public String saveAdd(){
        logger.info("[TindakanAction.saveAdd] start process >>>");

        try {
            Tindakan tindakan = getTindakan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tindakan.setCreatedWho(userLogin);
            tindakan.setLastUpdate(updateTime);
            tindakan.setCreatedDate(updateTime);
            tindakan.setLastUpdateWho(userLogin);
            tindakan.setAction("C");
            tindakan.setFlag("Y");

            tindakanBoProxy.saveAdd(tindakan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tindakanBoProxy.saveErrorMessage(e.getMessage(), "tindakanBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[tindakanAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[tindakanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultTindakan");

        logger.info("[tindakanAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[TindakanAction.saveEdit] start process >>>");
        try {

            Tindakan editTindakan = getTindakan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editTindakan.setLastUpdateWho(userLogin);
            editTindakan.setLastUpdate(updateTime);
            editTindakan.setAction("U");
            editTindakan.setFlag("Y");

            tindakanBoProxy.saveEdit(editTindakan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tindakanBoProxy.saveErrorMessage(e.getMessage(), "TindakanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TindakanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[TindakanAction.saveEdit] end process <<<");
        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[TindakanAction.saveDelete] start process >>>>");

        try{
            Tindakan deleteTindakan = getTindakan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteTindakan.setLastUpdate(updateTime);
            deleteTindakan.setLastUpdateWho(userLogin);
            deleteTindakan.setAction("U");
            deleteTindakan.setFlag("N");

            tindakanBoProxy.saveDelete(deleteTindakan);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = tindakanBoProxy.saveErrorMessage(e.getMessage(), "TindakanBo.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TindakanAction.saveDelete] Error when editing item pasien," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }
        logger.info("[TindakanAction.saveDelete] end process <<<");
        return "success_save_delete";
    }

    public ImSimrsTindakanEntity getDataTindakanById(String id){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        return tindakanBo.getEntityTindakanById(id);
    }
}