package com.neurix.hris.master.libur.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.libur.bo.LiburBo;
import com.neurix.hris.master.libur.model.Libur;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class LiburAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(LiburAction.class);
    private LiburBo liburBoProxy;
    private Libur libur;


    private List<Libur> initComboAlat;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LiburAction.logger = logger;
    }

    public LiburBo getLiburBoProxy() {
        return liburBoProxy;
    }

    public void setLiburBoProxy(LiburBo liburBoProxy) {
        this.liburBoProxy = liburBoProxy;
    }

    public Libur getLibur() {
        return libur;
    }

    public void setLibur(Libur libur) {
        this.libur = libur;
    }

    public List<Libur> getInitComboAlat() {
        return initComboAlat;
    }

    public void setInitComboAlat(List<Libur> initComboAlat) {
        this.initComboAlat = initComboAlat;
    }

    public Libur init(String kode, String flag){
        logger.info("[AlatAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Libur> listOfResult = (List<Libur>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Libur libur: listOfResult) {
                    if(kode.equalsIgnoreCase(libur.getLiburId()) && flag.equalsIgnoreCase(libur.getFlag())){
                        setLibur(libur);
                        break;
                    }
                }
            } else {
                setLibur(new Libur());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getLibur();
    }

    @Override
    public String add() {
        logger.info("[AlatAction.add] start process >>>");
        Libur addAlat = new Libur();
        setLibur(addAlat);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[AlatAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[AlatAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Libur editLibur = new Libur();

        if(itemFlag != null){
            try {
                editLibur = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = liburBoProxy.saveErrorMessage(e.getMessage(), "LiburBO.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[LiburAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[AlatAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            editLibur.setStTanggal(new SimpleDateFormat("dd-MM-yyyy").format(editLibur.getTanggal()));

            if(editLibur != null) {
                setLibur(editLibur);
            } else {
                editLibur.setFlag(itemFlag);
                editLibur.setLiburId(itemId);
                setLibur(editLibur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editLibur.setLiburId(itemId);
            editLibur.setFlag(getFlag());
            setLibur(editLibur);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[LiburAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[AlatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Libur deleteLibur = new Libur();

        if (itemFlag != null ) {

            try {
                deleteLibur = init(itemId, itemFlag);
                deleteLibur.setStTanggal(new SimpleDateFormat("dd-MM-yyyy").format(deleteLibur.getTanggal()));
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = liburBoProxy.saveErrorMessage(e.getMessage(), "LiburBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AlatAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[LiburAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteLibur != null) {
                setLibur(deleteLibur);

            } else {
                deleteLibur.setLiburId(itemId);
                deleteLibur.setFlag(itemFlag);
                setLibur(deleteLibur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteLibur.setLiburId(itemId);
            deleteLibur.setFlag(itemFlag);
            setLibur(deleteLibur);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[AlatAction.delete] end process <<<");

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

    public String saveEdit(){
        logger.info("[AlatAction.saveEdit] start process >>>");
        try {

            Libur editLibur = getLibur();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if (libur.getStTanggal() != null && !"".equalsIgnoreCase(libur.getStTanggal())) {
                libur.setTanggal(CommonUtil.convertToTimestamp(libur.getStTanggal()));
            }

            editLibur.setLastUpdateWho(userLogin);
            editLibur.setLastUpdate(updateTime);
            editLibur.setAction("U");
            editLibur.setFlag("Y");

//            String condition;

            liburBoProxy.saveEdit(editLibur);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = liburBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[LiburAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LiburAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[LiburAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[LiburAction.saveDelete] start process >>>");

        try {
            Libur deleteLibur = getLibur();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteLibur.setLastUpdate(updateTime);
            deleteLibur.setLastUpdateWho(userLogin);
            deleteLibur.setAction("U");
            deleteLibur.setFlag("N");

            liburBoProxy.saveDelete(deleteLibur);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = liburBoProxy.saveErrorMessage(e.getMessage(), "LiburBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AlatAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[LiburAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[LiburAction.saveAdd] start process >>>");

        try {
            Libur libur = getLibur();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if (libur.getStTanggal() != null && !"".equalsIgnoreCase(libur.getStTanggal())) {
                libur.setTanggal(CommonUtil.convertToTimestamp(libur.getStTanggal()));

            }

            libur.setCreatedWho(userLogin);
            libur.setLastUpdate(updateTime);
            libur.setCreatedDate(updateTime);
            libur.setLastUpdateWho(userLogin);
            libur.setAction("C");
            libur.setFlag("Y");
//            String fl = libur.getFlag();
//            int st = Integer.parseInt(fl);

            liburBoProxy.saveAdd(libur);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = liburBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[AlatAction.search] start process >>>");

        Libur searchAlat = getLibur();
        List<Libur> listOfSearchLibur = new ArrayList();

        try {
            listOfSearchLibur = liburBoProxy.getByCriteria(searchAlat);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = liburBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AlatAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchLibur);

        logger.info("[AlatAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[AlatAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[AlatAction.initForm] end process >>>");
        return INPUT;
    }

    /*public List initComboAlat(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<Libur> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LiburBo liburBo = (LiburBo) ctx.getBean("alatBoProxy");

        try {
            listOfAlat = liburBo.getComboLiburWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = liburBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }*/

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
