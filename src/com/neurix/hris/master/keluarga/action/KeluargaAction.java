package com.neurix.hris.master.keluarga.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.keluarga.bo.KeluargaBo;
import com.neurix.hris.master.keluarga.model.Keluarga;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class KeluargaAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(KeluargaAction.class);
    private KeluargaBo keluargaBoProxy;
    private Keluarga keluarga;

    private List<Keluarga> listComboKeluarga = new ArrayList<Keluarga>();

    public List<Keluarga> getListComboKeluarga() {
        return listComboKeluarga;
    }

    public void setListComboKeluarga(List<Keluarga> listComboKeluarga) {
        this.listComboKeluarga = listComboKeluarga;
    }

    public KeluargaBo getKeluargaBoProxy() {
        return keluargaBoProxy;
    }

    public void setKeluargaBoProxy(KeluargaBo keluargaBoProxy) {
        this.keluargaBoProxy = keluargaBoProxy;
    }

    public Keluarga getKeluarga() {
        return keluarga;
    }

    public void setKeluarga(Keluarga keluarga) {
        this.keluarga = keluarga;
    }

    private List<Keluarga> initComboKeluarga;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KeluargaAction.logger = logger;
    }


    public List<Keluarga> getInitComboKeluarga() {
        return initComboKeluarga;
    }

    public void setInitComboKeluarga(List<Keluarga> initComboKeluarga) {
        this.initComboKeluarga = initComboKeluarga;
    }

    public Keluarga init(String kode, String flag){
        logger.info("[KeluargaAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Keluarga> listOfResult = (List<Keluarga>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Keluarga keluarga: listOfResult) {
                    if(kode.equalsIgnoreCase(keluarga.getKeluargaId()) && flag.equalsIgnoreCase(keluarga.getFlag())){
                        setKeluarga(keluarga);
                        break;
                    }
                }
            } else {
                setKeluarga(new Keluarga());
            }

            logger.info("[KeluargaAction.init] end process >>>");
        }
        return getKeluarga();
    }

    public Keluarga initSearch(String kode){
        logger.info("[KeluargaAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Keluarga> listOfResult = (List<Keluarga>) session.getAttribute("listKeluarga");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Keluarga keluarga: listOfResult) {
                    if(kode.equalsIgnoreCase(keluarga.getStatusKeluargaId())){
                        setKeluarga(keluarga);
                        break;
                    }
                }
            } else {
                setKeluarga(new Keluarga());
            }

            logger.info("[KeluargaAction.init] end process >>>");
        }
        return getKeluarga();
    }

    public void initDelete(String kode){
        logger.info("[KeluargaAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Keluarga> listKeluarga = new ArrayList<>();
        List<Keluarga> listOfResult = (List<Keluarga>) session.getAttribute("listKeluarga");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Keluarga keluarga: listOfResult) {
                    if(kode.equalsIgnoreCase(keluarga.getStatusKeluargaId())){
                    }else{
                        listKeluarga.add(keluarga);
                    }
                }
            }
            logger.info("[KeluargaAction.init] end process >>>");
        }
        session.removeAttribute("listKeluarga");
        session.setAttribute("listKeluarga", listKeluarga);
    }

    public void initEdit(String idLama, String Name, String statusKeluarga, String statusKeluargaName, String tanggalLahir, String gender, String tgPtkp){
        logger.info("[KeluargaAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Keluarga> listKeluarga = new ArrayList<>();
        List<Keluarga> listOfResult = (List<Keluarga>) session.getAttribute("listKeluarga");

        if(idLama != null && !"".equalsIgnoreCase(idLama)){
            if(listOfResult != null){
                for (Keluarga keluarga: listOfResult) {
                    if(idLama.equalsIgnoreCase(keluarga.getKeluargaId())){
                        keluarga.setName(Name);
                        keluarga.setStatusKeluargaId(statusKeluarga);
                        keluarga.setStatusKeluargaName(statusKeluargaName);
                        keluarga.setTanggalLahir(CommonUtil.convertToTimestamp(tanggalLahir));
                        keluarga.setGender(gender);
                        keluarga.setTanggunganPtkp(tgPtkp);

                        listKeluarga.add(keluarga);
                    }else{
                        listKeluarga.add(keluarga);
                    }
                }
            }
            logger.info("[KeluargaAction.init] end process >>>");
        }
        session.removeAttribute("listKeluarga");
        session.setAttribute("listKeluarga", listKeluarga);
    }

    @Override
    public String add() {
        logger.info("[KeluargaAction.add] start process >>>");
        Keluarga addKeluarga = new Keluarga();
        setKeluarga(addKeluarga);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[KeluargaAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[KeluargaAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Keluarga editKeluarga = new Keluarga();

        if(itemFlag != null){
            try {
                editKeluarga = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getKeluargaByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[KeluargaAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[KeluargaAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editKeluarga != null) {
                setKeluarga(editKeluarga);
            } else {
                editKeluarga.setFlag(itemFlag);
                editKeluarga.setKeluargaId(itemId);
                setKeluarga(editKeluarga);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editKeluarga.setKeluargaId(itemId);
            editKeluarga.setFlag(getFlag());
            setKeluarga(editKeluarga);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[KeluargaAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[KeluargaAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Keluarga deleteKeluarga = new Keluarga();

        if (itemFlag != null ) {

            try {
                deleteKeluarga = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[KeluargaAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[KeluargaAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteKeluarga != null) {
                setKeluarga(deleteKeluarga);

            } else {
                deleteKeluarga.setKeluargaId(itemId);
                deleteKeluarga.setFlag(itemFlag);
                setKeluarga(deleteKeluarga);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteKeluarga.setKeluargaId(itemId);
            deleteKeluarga.setFlag(itemFlag);
            setKeluarga(deleteKeluarga);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[KeluargaAction.delete] end process <<<");

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

    public String saveEdit(String id, String name, String statusKeluarga, String gender, String tanggalLahir, String tgPtkp){
        logger.info("[KeluargaAction.saveEdit] start process >>>");
        try {

            Keluarga editKeluarga = new Keluarga();

            editKeluarga.setKeluargaId(id);
            editKeluarga.setName(name);
            editKeluarga.setGender(gender);
            editKeluarga.setStatusKeluargaId(statusKeluarga);
            editKeluarga.setStTanggalLahir(tanggalLahir);
            editKeluarga.setTanggunganPtkp(tgPtkp);

            if (editKeluarga.getStTanggalLahir() != null && !"".equalsIgnoreCase(editKeluarga.getStTanggalLahir())) {
                Date tanggal = CommonUtil.convertStringToDate(tanggalLahir);
                editKeluarga.setTanggalLahir(tanggal);
            }

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editKeluarga.setLastUpdateWho(userLogin);
            editKeluarga.setLastUpdate(updateTime);
            editKeluarga.setAction("U");
            editKeluarga.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KeluargaBo keluargaBo = (KeluargaBo) ctx.getBean("keluargaBoProxy");

            keluargaBo.saveEdit(editKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KeluargaAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[KeluargaAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(String id){
        logger.info("[KeluargaAction.saveDelete] start process >>>");
        try {

            Keluarga deleteKeluarga = new Keluarga();
            deleteKeluarga.setKeluargaId(id);
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteKeluarga.setLastUpdate(updateTime);
            deleteKeluarga.setLastUpdateWho(userLogin);
            deleteKeluarga.setAction("U");
            deleteKeluarga.setFlag("N");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KeluargaBo keluargaBo = (KeluargaBo) ctx.getBean("keluargaBoProxy");
            keluargaBo.saveDelete(deleteKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KeluargaAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[KeluargaAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(String name, String statusKeluarga, String statusKeluargaName, String tanggalLahir, String gender, String tgPtkp){
        logger.info("[KeluargaAction.saveAdd] start process >>>");

        try {
            Keluarga keluarga = new Keluarga();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            keluarga.setName(name);
            keluarga.setStTanggalLahir(tanggalLahir);
            keluarga.setStatusKeluargaId(statusKeluarga);
            keluarga.setStatusKeluargaName(statusKeluargaName);
            keluarga.setGender(gender);
            keluarga.setTanggunganPtkp(tgPtkp);

            keluarga.setCreatedWho(userLogin);
            keluarga.setLastUpdate(updateTime);
            keluarga.setCreatedDate(updateTime);
            keluarga.setLastUpdateWho(userLogin);
            keluarga.setAction("C");
            keluarga.setFlag("Y");

            if (keluarga.getStTanggalLahir() != null && !"".equalsIgnoreCase(keluarga.getStTanggalLahir())) {
                keluarga.setTanggalLahir(CommonUtil.convertToTimestamp(keluarga.getStTanggalLahir()));
            }

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KeluargaBo keluargaBo = (KeluargaBo) ctx.getBean("keluargaBoProxy");

            int id = 0;
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<Keluarga> listOfResult = (List<Keluarga>) session.getAttribute("listKeluarga");

            if(listOfResult != null){
                for(Keluarga keluarga1: listOfResult){
                    id = Integer.parseInt(keluarga1.getKeluargaId());
                }
                id++;
                keluarga.setKeluargaId(id +"");

                listOfResult.add(keluarga);
            }else{
                listOfResult = new ArrayList<>();
                keluarga.setKeluargaId(id +"");
                listOfResult.add(keluarga);
            }
            session.removeAttribute("listKeluarga");
            session.setAttribute("listKeluarga", listOfResult);
            //keluargaBo.saveAdd(keluarga);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        return "";
    }

    public String saveAddData(String nip, String name, String statusKeluarga, String gender, String tanggalLahir, String tgPtkp){
        logger.info("[KeluargaAction.saveAdd] start process >>>");

        try {
            Keluarga keluarga = new Keluarga();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            keluarga.setNip(nip);
            keluarga.setName(name);
            keluarga.setStTanggalLahir(tanggalLahir);
            keluarga.setStatusKeluargaId(statusKeluarga);
            keluarga.setGender(gender);
            keluarga.setTanggunganPtkp(tgPtkp);

            keluarga.setCreatedWho(userLogin);
            keluarga.setLastUpdate(updateTime);
            keluarga.setCreatedDate(updateTime);
            keluarga.setLastUpdateWho(userLogin);
            keluarga.setAction("C");
            keluarga.setFlag("Y");

            if (keluarga.getStTanggalLahir() != null && !"".equalsIgnoreCase(keluarga.getStTanggalLahir())) {
                keluarga.setTanggalLahir(CommonUtil.convertToTimestamp(keluarga.getStTanggalLahir()));
            }

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KeluargaBo keluargaBo = (KeluargaBo) ctx.getBean("keluargaBoProxy");

            keluargaBo.saveAdd(keluarga);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        return "";
    }

    @Override
    public String search() {
        logger.info("[KeluargaAction.search] start process >>>");

        Keluarga searchKeluarga = getKeluarga();
        List<Keluarga> listOfsearchKeluarga = new ArrayList();

        try {
            listOfsearchKeluarga = keluargaBoProxy.getByCriteria(searchKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchKeluarga);

        logger.info("[KeluargaAction.search] end process <<<");

        return SUCCESS;
    }

    public List<Keluarga> searchData(String nip, String id) {
        logger.info("[KeluargaAction.search] start process >>>");

        Keluarga searchKeluarga = new Keluarga();
        searchKeluarga.setNip(nip);
        searchKeluarga.setKeluargaId(id);
        searchKeluarga.setFlag("Y");
        List<Keluarga> listOfsearchKeluarga = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KeluargaBo keluargaBo = (KeluargaBo) ctx.getBean("keluargaBoProxy");

            listOfsearchKeluarga = keluargaBo.getByCriteria(searchKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfsearchKeluarga;
    }

    public List<Keluarga> searchDataSession() {
        logger.info("[KeluargaAction.search] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Keluarga> listOfResult = (List<Keluarga>) session.getAttribute("listKeluarga");

        return listOfResult;
    }

    public String searchKeluarga() {
        logger.info("[KeluargaAction.search] start process >>>");

        Keluarga searchKeluarga = new Keluarga();
        searchKeluarga.setFlag("Y");
        List<Keluarga> listOfsearchKeluarga = new ArrayList();

        try {
            listOfsearchKeluarga = keluargaBoProxy.getByCriteria(searchKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboKeluarga.addAll(listOfsearchKeluarga);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[KeluargaAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[KeluargaAction.initForm] end process >>>");
        return INPUT;
    }

    public String initKeluarga() {
        logger.info("[KeluargaAction.search] start process >>>");

        Keluarga searchKeluarga = new Keluarga();
        searchKeluarga.setFlag("Y");
        List<Keluarga> listOfsearchKeluarga = new ArrayList();

        try {
            listOfsearchKeluarga = keluargaBoProxy.getByCriteria(searchKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = keluargaBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultKeluarga");
        session.setAttribute("listOfResultKeluarga", listOfsearchKeluarga);

        logger.info("[KeluargaAction.search] end process <<<");

        return "";
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
