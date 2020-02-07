package com.neurix.akuntansi.transaksi.laporanAkuntansi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.LaporanAkuntansiBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.LaporanAkuntansi;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class LaporanAkuntansiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(LaporanAkuntansiAction.class);
    private LaporanAkuntansiBo laporanAkuntansiBoProxy;
    private LaporanAkuntansi laporanAkuntansi;

    private List<LaporanAkuntansi> listOfComboLaporanAkuntansi = new ArrayList<LaporanAkuntansi>();

    public List<LaporanAkuntansi> getListOfComboLaporanAkuntansi() {
        return listOfComboLaporanAkuntansi;
    }

    public void setListOfComboLaporanAkuntansi(List<LaporanAkuntansi> listOfComboLaporanAkuntansi) {
        this.listOfComboLaporanAkuntansi = listOfComboLaporanAkuntansi;
    }

    private List<LaporanAkuntansi> listComboLaporanAkuntansi = new ArrayList<LaporanAkuntansi>();

    public List<LaporanAkuntansi> getListComboLaporanAkuntansi() {
        return listComboLaporanAkuntansi;
    }

    public void setListComboLaporanAkuntansi(List<LaporanAkuntansi> listComboLaporanAkuntansi) {
        this.listComboLaporanAkuntansi = listComboLaporanAkuntansi;
    }

    public LaporanAkuntansiBo getLaporanAkuntansiBoProxy() {
        return laporanAkuntansiBoProxy;
    }

    public void setLaporanAkuntansiBoProxy(LaporanAkuntansiBo laporanAkuntansiBoProxy) {
        this.laporanAkuntansiBoProxy = laporanAkuntansiBoProxy;
    }

    public LaporanAkuntansi getLaporanAkuntansi() {
        return laporanAkuntansi;
    }

    public void setLaporanAkuntansi(LaporanAkuntansi laporanAkuntansi) {
        this.laporanAkuntansi = laporanAkuntansi;
    }

    private List<LaporanAkuntansi> initComboLaporanAkuntansi;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LaporanAkuntansiAction.logger = logger;
    }


    public List<LaporanAkuntansi> getInitComboLaporanAkuntansi() {
        return initComboLaporanAkuntansi;
    }

    public void setInitComboLaporanAkuntansi(List<LaporanAkuntansi> initComboLaporanAkuntansi) {
        this.initComboLaporanAkuntansi = initComboLaporanAkuntansi;
    }

    public LaporanAkuntansi init(String kode, String flag){
       /* logger.info("[LaporanAkuntansiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<LaporanAkuntansi> listOfResult = (List<LaporanAkuntansi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (LaporanAkuntansi laporanAkuntansi: listOfResult) {
                    if(kode.equalsIgnoreCase(laporanAkuntansi.getLaporanAkuntansiId()) && flag.equalsIgnoreCase(laporanAkuntansi.getFlag())){
                        setLaporanAkuntansi(laporanAkuntansi);
                        break;
                    }
                }
            } else {
                setLaporanAkuntansi(new LaporanAkuntansi());
            }

            logger.info("[LaporanAkuntansiAction.init] end process >>>");
        }*/
        return getLaporanAkuntansi();
    }

    @Override
    public String add() {
        logger.info("[LaporanAkuntansiAction.add] start process >>>");
        LaporanAkuntansi addLaporanAkuntansi = new LaporanAkuntansi();
        setLaporanAkuntansi(addLaporanAkuntansi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[LaporanAkuntansiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[LaporanAkuntansiAction.edit] start process >>>");

        return "init_edit";
    }

    @Override
    public String delete() {


        logger.info("[LaporanAkuntansiAction.delete] end process <<<");

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

    public String saveLaporanAkuntansi(){
        logger.info("[LaporanAkuntansiAction.saveAdd] start process >>>");
        try {
            LaporanAkuntansi laporanAkuntansi = getLaporanAkuntansi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[laporanAkuntansiAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            //logger.error("[laporanAkuntansiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            //addActionError("Error, mohon periksa inputan anda kembali");
            laporanAkuntansi.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
            //addActionMessage("Error, mohon periksa inputan anda kembali");
            return "input";
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfLaporanAkuntansi");

        return INPUT;
    }

    public String searchLaporanAkuntansi() {
        logger.info("[LaporanAkuntansiAction.search] start process >>>");

        LaporanAkuntansi searchLaporanAkuntansi = new LaporanAkuntansi();
        searchLaporanAkuntansi.setFlag("Y");
        List<LaporanAkuntansi> listOfSearchLaporanAkuntansi = new ArrayList();
        try {
            listOfSearchLaporanAkuntansi = laporanAkuntansiBoProxy.getByCriteria(searchLaporanAkuntansi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "laporanAkuntansiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.search] Error when saving error,", e1);
            }
            logger.error("[LaporanAkuntansiAction.save] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboLaporanAkuntansi.addAll(listOfSearchLaporanAkuntansi);
        return SUCCESS;
    }

    @Override
    public String search() {
        logger.info("[LaporanAkuntansiAction.search] start process >>>");

        LaporanAkuntansi searchLaporanAkuntansi = getLaporanAkuntansi();
        List<LaporanAkuntansi> listOfsearchLaporanAkuntansi = new ArrayList();

        try {
            listOfsearchLaporanAkuntansi = laporanAkuntansiBoProxy.getByCriteria(searchLaporanAkuntansi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "LaporanAkuntansiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LaporanAkuntansiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );

            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchLaporanAkuntansi);

        logger.info("[LaporanAkuntansiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[LaporanAkuntansiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[LaporanAkuntansiAction.initForm] end process >>>");
        return INPUT;
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
