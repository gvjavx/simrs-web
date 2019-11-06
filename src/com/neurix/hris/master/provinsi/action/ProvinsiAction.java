package com.neurix.hris.master.provinsi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.provinsi.bo.ProvinsiBo;
import com.neurix.hris.master.provinsi.model.Desa;
import com.neurix.hris.master.provinsi.model.Kecamatan;
import com.neurix.hris.master.provinsi.model.Kota;
import com.neurix.hris.master.provinsi.model.Provinsi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class ProvinsiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(ProvinsiAction.class);
    private ProvinsiBo provinsiBoProxy;
    private Provinsi provinsi;

    private List<Provinsi> listComboProvinsi = new ArrayList<Provinsi>();

    public List<Provinsi> getListComboProvinsi() {
        return listComboProvinsi;
    }

    public void setListComboProvinsi(List<Provinsi> listComboProvinsi) {
        this.listComboProvinsi = listComboProvinsi;
    }

    public ProvinsiBo getProvinsiBoProxy() {
        return provinsiBoProxy;
    }

    public void setProvinsiBoProxy(ProvinsiBo provinsiBoProxy) {
        this.provinsiBoProxy = provinsiBoProxy;
    }

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }

    private List<Provinsi> initComboProvinsi;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ProvinsiAction.logger = logger;
    }


    public List<Provinsi> getInitComboProvinsi() {
        return initComboProvinsi;
    }

    public void setInitComboProvinsi(List<Provinsi> initComboProvinsi) {
        this.initComboProvinsi = initComboProvinsi;
    }

    public Provinsi init(String kode, String flag){
        logger.info("[ProvinsiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Provinsi> listOfResult = (List<Provinsi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Provinsi provinsi: listOfResult) {
                    if(kode.equalsIgnoreCase(provinsi.getProvinsiId()) && flag.equalsIgnoreCase(provinsi.getFlag())){
                        setProvinsi(provinsi);
                        break;
                    }
                }
            } else {
                setProvinsi(new Provinsi());
            }

            logger.info("[ProvinsiAction.init] end process >>>");
        }
        return getProvinsi();
    }

    @Override
    public String add() {
        logger.info("[ProvinsiAction.add] start process >>>");
        Provinsi addProvinsi = new Provinsi();
        setProvinsi(addProvinsi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ProvinsiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[ProvinsiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Provinsi editProvinsi = new Provinsi();

        if(itemFlag != null){
            try {
                editProvinsi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = provinsiBoProxy.saveErrorMessage(e.getMessage(), "ProvinsiBO.getProvinsiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[ProvinsiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[ProvinsiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editProvinsi != null) {
                setProvinsi(editProvinsi);
            } else {
                editProvinsi.setFlag(itemFlag);
                editProvinsi.setProvinsiId(itemId);
                setProvinsi(editProvinsi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editProvinsi.setProvinsiId(itemId);
            editProvinsi.setFlag(getFlag());
            setProvinsi(editProvinsi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[ProvinsiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[ProvinsiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Provinsi deleteProvinsi = new Provinsi();

        if (itemFlag != null ) {

            try {
                deleteProvinsi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = provinsiBoProxy.saveErrorMessage(e.getMessage(), "ProvinsiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[ProvinsiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[ProvinsiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteProvinsi != null) {
                setProvinsi(deleteProvinsi);

            } else {
                deleteProvinsi.setProvinsiId(itemId);
                deleteProvinsi.setFlag(itemFlag);
                setProvinsi(deleteProvinsi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteProvinsi.setProvinsiId(itemId);
            deleteProvinsi.setFlag(itemFlag);
            setProvinsi(deleteProvinsi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[ProvinsiAction.delete] end process <<<");

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
        logger.info("[ProvinsiAction.saveEdit] start process >>>");
        try {

            Provinsi editProvinsi = getProvinsi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editProvinsi.setLastUpdateWho(userLogin);
            editProvinsi.setLastUpdate(updateTime);
            editProvinsi.setAction("U");
            editProvinsi.setFlag("Y");

            provinsiBoProxy.saveEdit(editProvinsi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = provinsiBoProxy.saveErrorMessage(e.getMessage(), "ProvinsiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[ProvinsiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ProvinsiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[ProvinsiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[ProvinsiAction.saveDelete] start process >>>");
        try {

            Provinsi deleteProvinsi = getProvinsi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteProvinsi.setLastUpdate(updateTime);
            deleteProvinsi.setLastUpdateWho(userLogin);
            deleteProvinsi.setAction("U");
            deleteProvinsi.setFlag("N");

            provinsiBoProxy.saveDelete(deleteProvinsi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = provinsiBoProxy.saveErrorMessage(e.getMessage(), "ProvinsiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[ProvinsiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ProvinsiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[ProvinsiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[ProvinsiAction.saveAdd] start process >>>");

        try {
            Provinsi provinsi = getProvinsi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            provinsi.setCreatedWho(userLogin);
            provinsi.setLastUpdate(updateTime);
            provinsi.setCreatedDate(updateTime);
            provinsi.setLastUpdateWho(userLogin);
            provinsi.setAction("C");
            provinsi.setFlag("Y");

            provinsiBoProxy.saveAdd(provinsi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = provinsiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[ProvinsiAction.search] start process >>>");

        Provinsi searchProvinsi = getProvinsi();
        List<Provinsi> listOfsearchProvinsi = new ArrayList();

        try {
            listOfsearchProvinsi = provinsiBoProxy.getByCriteria(searchProvinsi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = provinsiBoProxy.saveErrorMessage(e.getMessage(), "ProvinsiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ProvinsiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ProvinsiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchProvinsi);

        logger.info("[ProvinsiAction.search] end process <<<");

        return SUCCESS;
    }

    public List initComboProvinsi(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<Provinsi> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ProvinsiBo alatBo = (ProvinsiBo) ctx.getBean("provinsiBoProxy");

        try {
            listOfAlat = alatBo.getComboProvinsiWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = alatBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }

    public String findKota (String query){
        logger.info("[ProvinsiAction.findKota] start process >>>");
        Kota kota = new Kota();
        kota.setKotaId(query);
        kota.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ProvinsiBo kotaBo = (ProvinsiBo) ctx.getBean("provinsiBoProxy");

        String kotaName="";

        try {
            kotaName = kotaBo.getKotaName(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kotaBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ProvinsiAction.findKota] Error when saving error,", e1);
            }
            logger.error("[ProvinsiAction.findKota] Error when get kota name," + "[" + logId + "] Found problem when retrieving kota name, please inform to your admin.", e);
        }
        return kotaName;
    };
    public List initComboKota(String query, String prov) {
            logger.info("[ProvinsiAction.initComboKota] start process >>>");

        List<Kota> listOfKota = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ProvinsiBo kotaBo = (ProvinsiBo) ctx.getBean("provinsiBoProxy");

        try {
            listOfKota = kotaBo.getComboKotaWithCriteria(query, prov);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kotaBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ProvinsiAction.initComboKota] Error when saving error,", e1);
            }
            logger.error("[ProvinsiAction.initComboKota] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[ProvinsiAction.initComboKota] end process <<<");

        return listOfKota;
    }


    public List initComboKecamatan(String query, String kota) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<Kecamatan> listOfKota = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ProvinsiBo kotaBo = (ProvinsiBo) ctx.getBean("provinsiBoProxy");

        try {
            listOfKota = kotaBo.getComboKecamatanWithCriteria(query, kota);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kotaBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfKota;
    }

    public List initComboDesa(String query, String kecamatan) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<Desa> listOfKota = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ProvinsiBo kotaBo = (ProvinsiBo) ctx.getBean("provinsiBoProxy");

        try {
            listOfKota = kotaBo.getComboDesaWithCriteria(query, kecamatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kotaBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfKota;
    }

    @Override
    public String initForm() {
        logger.info("[ProvinsiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[ProvinsiAction.initForm] end process >>>");
        return INPUT;
    }

    public String initProvinsi() {
        logger.info("[ProvinsiAction.search] start process >>>");

        Provinsi searchProvinsi = new Provinsi();
        searchProvinsi.setFlag("Y");
        List<Provinsi> listOfsearchProvinsi = new ArrayList();

        try {
            listOfsearchProvinsi = provinsiBoProxy.getByCriteria(searchProvinsi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = provinsiBoProxy.saveErrorMessage(e.getMessage(), "ProvinsiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ProvinsiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ProvinsiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultProvinsi");
        session.setAttribute("listOfResultProvinsi", listOfsearchProvinsi);

        logger.info("[ProvinsiAction.search] end process <<<");

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
