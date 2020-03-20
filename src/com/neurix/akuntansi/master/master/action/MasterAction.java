package com.neurix.akuntansi.master.master.action;
import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.master.master.model.Master;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
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

public class MasterAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(MasterAction.class);
    private MasterBo masterBoProxy;
    private Master master;
    private List<Master> listOfComboMaster = new ArrayList<Master>();

    @Override
    public String add() {
        logger.info("[MasterAction.add] start process >>>");
        Master addMaster = new Master();
        setMaster(addMaster);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[MasterAction.add] stop process >>>");
        return "init_add";
    }

    public String saveAdd(){
        logger.info("[MasterAction.saveAdd] start process >>>");

        try {
            Master master = getMaster();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            master.setCreatedWho(userLogin);
            master.setLastUpdate(updateTime);
            master.setCreatedDate(updateTime);
            master.setLastUpdateWho(userLogin);
            master.setAction("C");
            master.setFlag("Y");

            masterBoProxy.saveAdd(master);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterBoProxy.saveErrorMessage(e.getMessage(), "masterBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[MasterAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasterAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[MasterAction.saveAdd] end process >>>");
        return "success_save_add";
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
        logger.info("[MasterAction.search] start process >>>");

        Master searchMaster = getMaster();
        List<Master> listOfsearchMaster = new ArrayList();

        try {
            listOfsearchMaster = masterBoProxy.getByCriteria(searchMaster);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterBoProxy.saveErrorMessage(e.getMessage(), "masterBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasterAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasterAction.save] Error when searching by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchMaster);

        logger.info("[MasterAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[MasterAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[MasterAction.initForm] end process >>>");
        return INPUT;
    }

    public List<Master> initMasterList(String nilai) {
        logger.info("[MasterAction.initMasterList] start process >>>");

        Master searchMaster = new Master();
        searchMaster.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");
        List<Master> masterList = new ArrayList();

        try {
            masterList = masterBo.getByCriteria(searchMaster);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasterAction.initMasterList] Error when saving error,", e1);
            }
            logger.error("[MasterAction.initMasterList] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return masterList;
    }
    public List<Master> cekAvailableCoa(String nilai) {
        logger.info("[MasterAction.cekAvailableCoa] start process >>>");

        Master searchMaster = new Master();
        searchMaster.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");
        List<Master> masterList = new ArrayList();
        try {
            masterList = masterBo.getByCriteria(searchMaster);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasterAction.cekAvailableCoa] Error when saving error,", e1);
            }
            logger.error("[MasterAction.cekAvailableCoa] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return masterList;
    }

    public boolean cekAvailableParent(String nilai) {
        logger.info("[MasterAction.cekAvailableParent] start process >>>");
        boolean adaParent=true;

        String[] coa=nilai.split("\\.");
        String coaParent="";
        for (int i=0;i<coa.length;i++){
            if (i==0){
                coaParent=coaParent+coa[i];
            }else{
                Master searchMaster = new Master();
                searchMaster.setFlag("Y");

                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");
                List<Master> masterList = new ArrayList();
                try {
                    masterList = masterBo.getByCriteria(searchMaster);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = masterBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
                    } catch (GeneralBOException e1) {
                        logger.error("[MasterAction.cekAvailableParent] Error when saving error,", e1);
                    }
                    logger.error("[MasterAction.cekAvailableParent] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                }
                if (masterList.size()==0){
                    adaParent=false;
                    break;
                }
                coaParent=coaParent+"."+coa[i];
            }

        }
        return adaParent;
    }
    public List<Master> initTypeaheadMaster(String key) {
        logger.info("[MasterAction.initTypeaheadMaster] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");
        List<Master> masterList = new ArrayList();
        try {
            masterList = masterBo.typeaheadMaster(key);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasterAction.initTypeaheadMaster] Error when saving error,", e1);
            }
            logger.error("[MasterAction.initTypeaheadMaster] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return masterList;
    }

    public List<Master> initMasterSearch(String rekeningId, String namaRekening, String coa) {
        logger.info("[MasterAction.initMasterSearch] start process >>>");

        List<Master> listOfsearchMaster = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MasterBo masterBo= (MasterBo) ctx.getBean("masterBoProxy");

        Master search = new Master();
        search.setFlag("Y");

        try {
            listOfsearchMaster = masterBo.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterBo.saveErrorMessage(e.getMessage(), "masterBo.getDataStrukturCoa");
            } catch (GeneralBOException e1) {
                logger.error("[MasterAction.initMasterSearch] Error when saving error,", e1);
            }
            logger.error("[MasterAction.initMasterSearch] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[MasterAction.initMasterSearch] end process <<<");
        return listOfsearchMaster;
    }

    public Boolean saveEdit(String id, String masterName,String coa, String tipeRekeningId,String tipeEdit){
        logger.info("[MasterAction.saveEdit] start process >>>");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        Master edit = new Master();
        edit.setLastUpdateWho(userLogin);
        edit.setLastUpdate(updateTime);
        edit.setAction("U");
        if ("edit".equalsIgnoreCase(tipeEdit)){
            edit.setFlag("Y");
        }else if ("delete".equalsIgnoreCase(tipeEdit)){
            edit.setFlag("N");
        }
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");

        try {
            masterBo.saveEdit(edit);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MasterAction.saveEdit] Error when saving error,", e1);
                return false;
            }
            logger.error("[MasterAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return false;
        }
        logger.info("[MasterAction.saveEdit] end process <<<");
        return true;
    }
    public List<Master> searchMaster(String coa) {
        logger.info("[MasterAction.searchMaster] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Master> masterList = (List<Master>) session.getAttribute("listOfResultMaster");
        if (("").equalsIgnoreCase(coa)){
            session.setAttribute("listOfResultMaster",masterList);
        }else{
            List<Master> masters = new ArrayList<>();
            for (Master master:masters){
            }
        }
        logger.info("[MasterAction.searchMaster] end process >>>");
        return masterList;
    }
    public String cekStatusBeforeSave() {
        logger.info("[MasterAction.cekStatusBeforeSave] start process >>>");
        String status="";
        boolean adaKredit=false;
        boolean adaDebet=false;
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Master> masterList = (List<Master>) session.getAttribute("listOfResultMaster");
        if (masterList==null){
            status="Kode Rekening masih kosong tambahkan minimal 1 Kode Rekening Debet dan 1 Kode Rekening Kredit";
        }else{
            if (masterList.size()==0){
                status="Kode Rekening masih kosong tambahkan minimal 1 Kode Rekening Debet dan 1 Kode Rekening Kredit";
            }else{
                for (Master master : masterList){
                }
                if (!adaDebet){
                    status="Kode Rekening debet belum ada ";
                }
                if (!adaKredit){
                    status="Kode Rekening kredit belum ada ";
                }
            }
        }

        logger.info("[MasterAction.cekStatusBeforeSave] end process >>>");
        return status;
    }
    public void saveAddMaster(String master,String namaMaster,String posisi) {
        logger.info("[MasterAction.saveAddMaster] start process >>>");
        String posisiName = "";
        if ("D".equalsIgnoreCase(posisi)){
            posisiName="Debet";
        }else{
            posisiName="Kredit";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Master> masterList = (List<Master>) session.getAttribute("listOfResultMaster");
        List<Master> masterArrayList = new ArrayList<>();
        boolean ada=false;
        if (masterList==null){
            Master newData = new Master();

            masterArrayList.add(newData);
            session.setAttribute("listOfResultMaster",masterArrayList);
        }else{
            masterArrayList.addAll(masterList);
            for (Master master1:masterList){

            }
            if (!ada){
                Master newData = new Master();
                masterArrayList.add(newData);
                session.setAttribute("listOfResultMaster",masterArrayList);
            }
        }

        logger.info("[MasterAction.saveAddMaster] end process >>>");
    }
    public void saveDeleteMaster(String coa) {
        logger.info("[MasterAction.saveDeleteAnggota] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Master> masterList = (List<Master>) session.getAttribute("listOfResultMaster");
        List<Master> masterArrayList = new ArrayList<>();
        for (Master master:masterList){
        }
        session.setAttribute("listOfResultMaster",masterArrayList);
        logger.info("[MasterAction.saveDeleteAnggota] end process >>>");
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }

    public String paging(){
        return SUCCESS;
    }

    public List<Master> getListOfComboMaster() {
        return listOfComboMaster;
    }

    public void setListOfComboMaster(List<Master> listOfComboMaster) {
        this.listOfComboMaster = listOfComboMaster;
    }


    public MasterBo getMasterBoProxy() {
        return masterBoProxy;
    }

    public void setMasterBoProxy(MasterBo masterBoProxy) {
        this.masterBoProxy = masterBoProxy;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    private List<Master> initComboMaster;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MasterAction.logger = logger;
    }


    public List<Master> getInitComboMaster() {
        return initComboMaster;
    }

    public void setInitComboMaster(List<Master> initComboMaster) {
        this.initComboMaster = initComboMaster;
    }
}
