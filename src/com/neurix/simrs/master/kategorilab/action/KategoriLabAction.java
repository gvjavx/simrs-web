package com.neurix.simrs.master.kategorilab.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kategorilab.bo.KategoriLabBo;
import com.neurix.simrs.master.kategorilab.model.KategoriLab;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KategoriLabAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KategoriLabAction.class);
    private KategoriLabBo kategoriLabBoProxy;
    private KategoriLab kategoriLab;

    private List<KategoriLab> listOfKategoriLab = new ArrayList<>();

    public List<KategoriLab> getListOfKategoriLab() {
        return listOfKategoriLab;
    }

    public void setListOfKategoriLab(List<KategoriLab> listOfKategoriLab) {
        this.listOfKategoriLab = listOfKategoriLab;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setKategoriLabBoProxy(KategoriLabBo kategoriLabBoProxy) {
        this.kategoriLabBoProxy = kategoriLabBoProxy;
    }

    public KategoriLab getKategoriLab() {
        return kategoriLab;
    }

    public void setKategoriLab(KategoriLab kategoriLab) {
        this.kategoriLab = kategoriLab;
    }

    public KategoriLab init(String kode, String flag){
        logger.info("[KategoriLabAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KategoriLab> listOfResult = (List<KategoriLab>) session.getAttribute("listOfResultKategoriLab");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (KategoriLab kategoriLab: listOfResult) {
                    if(kode.equalsIgnoreCase(kategoriLab.getIdKategoriLab()) && flag.equalsIgnoreCase(kategoriLab.getFlag())){
                        setKategoriLab(kategoriLab);
                        break;
                    }
                }
            } else {
                setKategoriLab(new KategoriLab());
            }

            logger.info("[KategoriLabAction.init] end process >>>");
        }
        return getKategoriLab();
    }

    @Override
    public String add() {
        logger.info("[KategoriLabAction.add] start process >>>");
        KategoriLab addKategoriLab = new KategoriLab();
        setKategoriLab(addKategoriLab);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[KategoriLabAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[KategoriLabAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        KategoriLab editKategoriLab = new KategoriLab();

        if(itemFlag != null){
            try {
                editKategoriLab = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kategoriLabBoProxy.saveErrorMessage(e.getMessage(), "KategoriLabBO.edit");
                } catch (GeneralBOException e1) {
                    logger.error("[KategoriLabAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[KategoriLabAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editKategoriLab != null) {
                setKategoriLab(editKategoriLab);
            } else {
                editKategoriLab.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setKategoriLab(editKategoriLab);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editKategoriLab.setFlag(getFlag());
            setKategoriLab(editKategoriLab);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[LabAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[KategoriLabAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        KategoriLab deleteKategoriLab = new KategoriLab();

        if (itemFlag != null ) {
            try {
                deleteKategoriLab = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kategoriLabBoProxy.saveErrorMessage(e.getMessage(), "KategoriLabBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[LabAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[KategoriLabAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteKategoriLab != null) {
                setKategoriLab(deleteKategoriLab);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteKategoriLab.setFlag(itemFlag);
                setKategoriLab(deleteKategoriLab);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteKategoriLab.setFlag(itemFlag);
            setKategoriLab(deleteKategoriLab);
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

    public String saveAdd(){
        logger.info("[LabAction.saveAdd] start process >>>");

        try {
            KategoriLab  kategoriLab = getKategoriLab();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            kategoriLab.setCreatedWho(userLogin);
            kategoriLab.setLastUpdate(updateTime);
            kategoriLab.setCreatedDate(updateTime);
            kategoriLab.setLastUpdateWho(userLogin);
            kategoriLab.setAction("C");
            kategoriLab.setFlag("Y");

            kategoriLabBoProxy.saveAdd(kategoriLab);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriLabBoProxy.saveErrorMessage(e.getMessage(), "kategoriLabBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[kategorilabAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[kategoriLabAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultKategoriLab");

        logger.info("[labAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[KategoriLabAction.saveEdit] start process >>>");
        try {

            KategoriLab editKategoriLab = getKategoriLab();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editKategoriLab.setLastUpdateWho(userLogin);
            editKategoriLab.setLastUpdate(updateTime);
            editKategoriLab.setAction("U");
            editKategoriLab.setFlag("Y");

            kategoriLabBoProxy.saveEdit(editKategoriLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriLabBoProxy.saveErrorMessage(e.getMessage(), "KategoriLabBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[KategoriLabAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("Kategori[LabAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[KategoriLabAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[KategoriLabAction.saveDelete] start process >>>");
        try {

            KategoriLab deleteKategoriLab = getKategoriLab();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteKategoriLab.setLastUpdate(updateTime);
            deleteKategoriLab.setLastUpdateWho(userLogin);
            deleteKategoriLab.setAction("U");
            deleteKategoriLab.setFlag("N");

            kategoriLabBoProxy.saveDelete(deleteKategoriLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriLabBoProxy.saveErrorMessage(e.getMessage(), "KategoriLabBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[KategoriLabAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KategoriLabAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[KategoriLabAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    @Override
    public String search() {
        logger.info("[KategoriLabAction.search] start process >>>");

        KategoriLab searchKategoriLab = getKategoriLab();
        List<KategoriLab> listOfsearchKategoriLab = new ArrayList();

        try {
            listOfsearchKategoriLab = kategoriLabBoProxy.getByCriteria(searchKategoriLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriLabBoProxy.saveErrorMessage(e.getMessage(), "KategoriLabBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KategoriLabAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KategorLabAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultKategoriLab");
        session.setAttribute("listOfResultKategoriLab", listOfsearchKategoriLab);

        logger.info("[KategoriLabAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[KategoriLabAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultKategoriLab");
        logger.info("[KategoriLabAction.initForm] end process >>>");
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

    public String getListKategoriLab(){

        logger.info("[KategoriLabAction.getListKategoriLab] start process >>>");

        List<KategoriLab> kategoriLabList = new ArrayList<>();
        KategoriLab kategoriLab = new KategoriLab();

        try {
            kategoriLabList = kategoriLabBoProxy.getByCriteria(kategoriLab);
        }catch (GeneralBOException e){
            logger.error("[KategoriLabAction.getListKategoriLab] Error when get kategori lab ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfKategoriLab.addAll(kategoriLabList);
        logger.info("[KategoriLabAction.getListKategoriLab] end process <<<");
        return SUCCESS;

    }
}