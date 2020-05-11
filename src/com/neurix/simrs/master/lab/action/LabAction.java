package com.neurix.simrs.master.lab.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.kategorilab.bo.KategoriLabBo;
import com.neurix.simrs.master.kategorilab.model.KategoriLab;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.model.ImSimrsLabEntity;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.operatorlab.bo.OperatorLabBo;
import com.neurix.simrs.master.operatorlab.model.OperatorLab;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LabAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(LabAction.class);
    private LabBo labBoProxy;
    private OperatorLabBo operatorLabBoProxy;
    private DokterBo dokterBoProxy;
    private KategoriLabBo kategoriLabBoProxy;
    private Lab lab;
    private List<Lab> listOfLab = new ArrayList<>();
    private List<Lab> lisOfRadiologi = new ArrayList<>();

    private List<OperatorLab> listOfComboOperatorLab = new ArrayList<OperatorLab>();
    private List<Dokter> listOfComboDokter = new ArrayList<Dokter>();
    private List<KategoriLab> listOfComboKategoriLab = new ArrayList<KategoriLab>();

    public KategoriLabBo getKategoriLabBoProxy() {
        return kategoriLabBoProxy;
    }

    public void setKategoriLabBoProxy(KategoriLabBo kategoriLabBoProxy) {
        this.kategoriLabBoProxy = kategoriLabBoProxy;
    }

    public List<KategoriLab> getListOfComboKategoriLab() {
        return listOfComboKategoriLab;
    }

    public void setListOfComboKategoriLab(List<KategoriLab> listOfComboKategoriLab) {
        this.listOfComboKategoriLab = listOfComboKategoriLab;
    }

    public DokterBo getDokterBoProxy() {
        return dokterBoProxy;
    }

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    public List<Dokter> getListOfComboDokter() {
        return listOfComboDokter;
    }

    public void setListOfComboDokter(List<Dokter> listOfComboDokter) {
        this.listOfComboDokter = listOfComboDokter;
    }

    public List<OperatorLab> getListOfComboOperatorLab() {
        return listOfComboOperatorLab;
    }

    public void setListOfComboOperatorLab(List<OperatorLab> listOfComboOperatorLab) {
        this.listOfComboOperatorLab = listOfComboOperatorLab;
    }

    public OperatorLabBo getOperatorLabBoProxy() {
        return operatorLabBoProxy;
    }

    public void setOperatorLabBoProxy(OperatorLabBo operatorLabBoProxy) {
        this.operatorLabBoProxy = operatorLabBoProxy;
    }

    public List<Lab> getLisOfRadiologi() {
        return lisOfRadiologi;
    }

    public void setLisOfRadiologi(List<Lab> lisOfRadiologi) {
        this.lisOfRadiologi = lisOfRadiologi;
    }

    public List<Lab> getListOfLab() {
        return listOfLab;
    }

    public void setListOfLab(List<Lab> listOfLab) {
        this.listOfLab = listOfLab;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LabAction.logger = logger;
    }

    public LabBo getLabBoProxy() {
        return labBoProxy;
    }

    public void setLabBoProxy(LabBo labBoProxy) {
        this.labBoProxy = labBoProxy;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public Lab init(String kode, String flag){
        logger.info("[LabAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lab> listOfResult = (List<Lab>) session.getAttribute("listOfResultLab");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Lab lab: listOfResult) {
                    if(kode.equalsIgnoreCase(lab.getIdLab()) && flag.equalsIgnoreCase(lab.getFlag())){
                        setLab(lab);
                        break;
                    }
                }
            } else {
                setLab(new Lab());
            }

            logger.info("[LabAction.init] end process >>>");
        }
        return getLab();
    }

    @Override
    public String add() {
        logger.info("[LabAction.add] start process >>>");
        Lab addLab = new Lab();
        setLab(addLab);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[LabAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[LabAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Lab editLab = new Lab();

        if(itemFlag != null){
            try {
                editLab = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = labBoProxy.saveErrorMessage(e.getMessage(), "LabBO.edit");
                } catch (GeneralBOException e1) {
                    logger.error("[LabAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[LabAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editLab != null) {
                setLab(editLab);
            } else {
                editLab.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setLab(editLab);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editLab.setFlag(getFlag());
            setLab(editLab);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[LabAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[LabAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Lab deleteLab = new Lab();

        if (itemFlag != null ) {
            try {
                deleteLab = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = labBoProxy.saveErrorMessage(e.getMessage(), "LabBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[LabAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[LabAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteLab != null) {
                setLab(deleteLab);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteLab.setFlag(itemFlag);
                setLab(deleteLab);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteLab.setFlag(itemFlag);
            setLab(deleteLab);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[LabAction.delete] end process <<<");

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
        logger.info("[LabAction.search] start process >>>");

        Lab searchLab = getLab();
        List<Lab> listOfsearchLab = new ArrayList();

        try {
            listOfsearchLab = labBoProxy.getByCriteria(searchLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labBoProxy.saveErrorMessage(e.getMessage(), "LabBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LabAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LabAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultLab");
        session.setAttribute("listOfResultLab", listOfsearchLab);

        logger.info("[LabAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("LabAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultLab");
        logger.info("[LabAction.initForm] end process >>>");
        return INPUT;
    }

    public String saveAdd(){
        logger.info("[LabAction.saveAdd] start process >>>");

        try {
            Lab lab = getLab();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            lab.setCreatedWho(userLogin);
            lab.setLastUpdate(updateTime);
            lab.setCreatedDate(updateTime);
            lab.setLastUpdateWho(userLogin);
            lab.setAction("C");
            lab.setFlag("Y");

            labBoProxy.saveAdd(lab);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labBoProxy.saveErrorMessage(e.getMessage(), "labBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[labAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[labAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultLab");

        logger.info("[labAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[LabAction.saveEdit] start process >>>");
        try {

            Lab editLab = getLab();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editLab.setLastUpdateWho(userLogin);
            editLab.setLastUpdate(updateTime);
            editLab.setAction("U");
            editLab.setFlag("Y");

            labBoProxy.saveEdit(editLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labBoProxy.saveErrorMessage(e.getMessage(), "LabBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[LabAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LabAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[LabAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[LabAction.saveDelete] start process >>>");
        try {

            Lab deleteLab = getLab();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteLab.setLastUpdate(updateTime);
            deleteLab.setLastUpdateWho(userLogin);
            deleteLab.setAction("U");
            deleteLab.setFlag("N");

            labBoProxy.saveDelete(deleteLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labBoProxy.saveErrorMessage(e.getMessage(), "LabBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[LabAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[LabAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[LabAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public List<Lab> listLab(String idKategoriLab){

        logger.info("[LabAction.listLab] start process >>>");
        List<Lab> labList = new ArrayList<>();
        Lab lab = new Lab();
        lab.setIdKategoriLab(idKategoriLab);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabBo labBo = (LabBo) ctx.getBean("labBoProxy");

        try {
            labList = labBo.getByCriteria(lab);
        }catch (GeneralBOException e){
            logger.error("[LabAction.listLab] Error when get data lab ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[LabAction.listLab] end process >>>");
        return labList;

    }

    public String getListLab(){

        logger.info("[LabAction.getListLab] start process >>>");

        List<Lab> labList = new ArrayList<>();
        Lab lab = new Lab();
        lab.setIdKategoriLab("KAL00000002");
        try {
            labList = labBoProxy.getByCriteria(lab);
        }catch (GeneralBOException e){
            logger.error("[LabAction.getListLab] Error when get lab ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfLab.addAll(labList);
        logger.info("[LabAction.getListLab] end process <<<");
        return SUCCESS;

    }

    public String getListRadiologi(){

        logger.info("[LabAction.getListRadiologi] start process >>>");

        List<Lab> labList = new ArrayList<>();
        Lab lab = new Lab();
        lab.setIdKategoriLab("KAL00000001");

        try {
            labList = labBoProxy.getByCriteria(lab);
        }catch (GeneralBOException e){
            logger.error("[LabAction.getListRadiologi] Error when get lab radiologi," + "Found problem when saving add data, please inform to your admin.", e);
        }

        lisOfRadiologi.addAll(labList);
        logger.info("[LabAction.getListRadiologi] end process <<<");
        return SUCCESS;

    }

    public String initComboOperatorLab() {

        OperatorLab operatorLab = new OperatorLab();
        operatorLab.setFlag("Y");

        List<OperatorLab> listOfOperatorLab = new ArrayList<OperatorLab>();
        try {
            listOfOperatorLab = operatorLabBoProxy.getByCriteria(operatorLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = operatorLabBoProxy.saveErrorMessage(e.getMessage(), "OperatorLabBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LabAction.initComboOperatroLab] Error when saving error,", e1);
            }
            logger.error("[LabAction.initComboOperatorLab] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboOperatorLab.addAll(listOfOperatorLab);

        return "init_combo";
    }

    public String initComboDokter() {

        Dokter dokter = new Dokter();
        dokter.setFlag("Y");

        List<Dokter> listOfDokter = new ArrayList<Dokter>();
        try {
            listOfDokter = dokterBoProxy.getSearchByCriteria(dokter);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dokterBoProxy.saveErrorMessage(e.getMessage(), "DokterBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LabAction.initComboDokter] Error when saving error,", e1);
            }
            logger.error("[LabAction.initComboDokter] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboDokter.addAll(listOfDokter);

        return "init_combo";
    }

    public String initComboKategoriLab() {

        KategoriLab kategoriLab = new KategoriLab();
        kategoriLab.setFlag("Y");

        List<KategoriLab> listOfKategoriLab = new ArrayList<KategoriLab>();
        try {
            listOfKategoriLab = kategoriLabBoProxy.getByCriteria(kategoriLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriLabBoProxy.saveErrorMessage(e.getMessage(), "KategoriLabBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LabAction.initComboKategoriLab] Error when saving error,", e1);
            }
            logger.error("[LabAction.initComboKategoriLab] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboKategoriLab.addAll(listOfKategoriLab);

        return "init_combo";
    }
}