package com.neurix.simrs.master.kandunganObat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kandunganObat.bo.KandunganObatBo;
import com.neurix.simrs.master.kandunganObat.model.*;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

public class KandunganObatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KandunganObatAction.class);
    private KandunganObatBo kandunganObatBoProxy;
    private KandunganObat kandunganObat;
    private List<KandunganObat> listOfKandunganObat = new ArrayList<>();

    public KandunganObatBo getKandunganObatBoProxy() {
        return kandunganObatBoProxy;
    }

    public void setKandunganObatBoProxy(KandunganObatBo kandunganObatBoProxy) {
        this.kandunganObatBoProxy = kandunganObatBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KandunganObatAction.logger = logger;
    }

    public KandunganObat getKandunganObat() {
        return kandunganObat;
    }

    public void setKandunganObat(KandunganObat kandunganObat) {
        this.kandunganObat = kandunganObat;
    }

    public List<KandunganObat> getListOfKandunganObat() {
        return listOfKandunganObat;
    }

    public void setListOfKandunganObat(List<KandunganObat> listOfKandunganObat) {
        this.listOfKandunganObat = listOfKandunganObat;
    }

    public String saveEdit(String idKandungan, String kandunganObat, String tipeEdit){
        logger.info("[KandunganObatAction.saveEdit] start process >>>");
        String status="";
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        KandunganObat edit = new KandunganObat();
        edit.setIdKandungan(idKandungan);
        edit.setKandungan(kandunganObat);
        edit.setLastUpdateWho(userLogin);
        edit.setLastUpdate(updateTime);
        edit.setAction("U");
        if ("edit".equalsIgnoreCase(tipeEdit)){
            edit.setFlag("Y");
        }else if ("delete".equalsIgnoreCase(tipeEdit)){
            edit.setFlag("N");
        }
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KandunganObatBo kandunganObatBo = (KandunganObatBo) ctx.getBean("kandunganObatBoProxy");

        try {
            kandunganObatBo.saveEdit(edit);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kandunganObatBo.saveErrorMessage(e.getMessage(), "kandunganObatBoProxy.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[KandunganObatAction.saveEdit] Error when saving error,", e1);
                status="Gagal melakukan edit , Error : "+e1.getMessage();
                return status;
            }
            logger.error("[KandunganObatAction.saveEdit] Error when editing kandungan obat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            status="Gagal melakukan edit , Error : "+e.getMessage();
            return status;
        }
        logger.info("[KandunganObatAction.saveEdit] end process <<<");
        return status;
    }

    public String saveAdd(){
        logger.info("[KandunganObatAction.saveAdd] start process >>>");

        try {
            KandunganObat kandunganObat = getKandunganObat();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            kandunganObat.setCreatedWho(userLogin);
            kandunganObat.setLastUpdate(updateTime);
            kandunganObat.setCreatedDate(updateTime);
            kandunganObat.setLastUpdateWho(userLogin);
            kandunganObat.setAction("C");
            kandunganObat.setFlag("Y");

            kandunganObatBoProxy.saveAdd(kandunganObat);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kandunganObatBoProxy.saveErrorMessage(e.getMessage(), "kandunganObatBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[KandunganObatAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KandunganObatAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            throw new GeneralBOException(e.getMessage());
            //addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            //return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[KandunganObatAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public List<KandunganObat> initKandunganObatSearch(String idKandungan, String kandungan) {
        logger.info("[KandunganObatAction.initKandunganObatSearch] start process >>>");

        List<KandunganObat> listOfsearchKandunganObat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KandunganObatBo kandunganObatBo= (KandunganObatBo) ctx.getBean("kandunganObatBoProxy");

        KandunganObat search = new KandunganObat();
        search.setIdKandungan(idKandungan);
        search.setKandungan(kandungan);
        search.setFlag("Y");

        try {
            listOfsearchKandunganObat = kandunganObatBo.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kandunganObatBo.saveErrorMessage(e.getMessage(), "kandunganObatBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KandunganObatAction.initKandunganObatSearch] Error when saving error,", e1);
            }
            logger.error("[KandunganObatAction.initKandunganObatSearch] Error when searching kandungan obat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[KandunganObatAction.initKandunganObatSearch] end process <<<");
        return listOfsearchKandunganObat;
    }



    @Override
    public String add() {

        logger.info("[KandunganObatAction.add] start process >>>");

        KandunganObat kandunganObat = new KandunganObat();
        setKandunganObat(kandunganObat);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[KandunganObatAction.add] end process <<<");
        return "init_add";
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
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
        logger.info("[KandunganObatAction.search] START >>>>>>>");

        KandunganObat kandunganObat = getKandunganObat();
        kandunganObat.setBranchId(CommonUtil.userBranchLogin());

        List<KandunganObat> kandunganObatList = new ArrayList<>();
        try {
            kandunganObatList = kandunganObatBoProxy.getByCriteria(kandunganObat);
        } catch (HibernateException e) {
            logger.error("[KandunganObatAction.search] ERROR when get data list kandungan obat, ", e);
            addActionError("[KandunganObatAction.search] ERROR when get data list kandungan obat, " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", kandunganObatList);

        logger.info("[KandunganObatAction.search] END <<<<<<<");
        return "search";

    }

    @Override
    public String initForm() {
        logger.info("[KandunganObatAction.initForm] START >>>>>>>");
        String userBranch = CommonUtil.userBranchLogin();

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        KandunganObat kandunganObat = new KandunganObat();
        if (CommonConstant.BRANCH_KP.equalsIgnoreCase(userBranch)){
            kandunganObat.setIsKp("Y");
        }

        setKandunganObat(kandunganObat);

        logger.info("[KandunganObatAction.initForm] END <<<<<<<");
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