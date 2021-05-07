package com.neurix.hris.master.updateGolongan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.updateGolongan.bo.UpdateGolonganBo;
import com.neurix.hris.master.updateGolongan.model.UpdateGolongan;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class UpdateGolonganAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(UpdateGolonganAction.class);
    private UpdateGolonganBo updateGolonganBoProxy;
    private UpdateGolongan updateGolongan;

    private List<UpdateGolongan> listComboGolongan = new ArrayList<UpdateGolongan>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        UpdateGolonganAction.logger = logger;
    }

    public UpdateGolonganBo getUpdateGolonganBoProxy() {
        return updateGolonganBoProxy;
    }

    public void setUpdateGolonganBoProxy(UpdateGolonganBo updateGolonganBoProxy) {
        this.updateGolonganBoProxy = updateGolonganBoProxy;
    }

    public UpdateGolongan getUpdateGolongan() {
        return updateGolongan;
    }

    public void setUpdateGolongan(UpdateGolongan updateGolongan) {
        this.updateGolongan = updateGolongan;
    }

    public List<UpdateGolongan> getListComboGolongan() {
        return listComboGolongan;
    }

    public void setListComboGolongan(List<UpdateGolongan> listComboGolongan) {
        this.listComboGolongan = listComboGolongan;
    }

    public String initComboGolongan() {
        logger.info("[BranchAction.search] start process >>>");

        UpdateGolongan searchGolongan = new UpdateGolongan();
        List<UpdateGolongan> listOfSearchGolongan = new ArrayList();
        searchGolongan.setFlag("Y");
        try {
            listOfSearchGolongan = updateGolonganBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = updateGolonganBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboGolongan.addAll(listOfSearchGolongan);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public UpdateGolongan init(String kode, String flag){
        logger.info("[UpdateGolonganAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<UpdateGolongan> listOfResult = (List<UpdateGolongan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (UpdateGolongan golongan: listOfResult) {
                    if(kode.equalsIgnoreCase(golongan.getUpdateGolonganId()) && flag.equalsIgnoreCase(golongan.getFlag())){
                        setUpdateGolongan(golongan);
                        break;
                    }
                }
            } else {
                setUpdateGolongan(new UpdateGolongan());
            }

            logger.info("[UpdateGolonganAction.init] end process >>>");
        }
        return getUpdateGolongan();
    }

    @Override
    public String add() {
        logger.info("[UpdateGolonganAction.add] start process >>>");
        UpdateGolongan addGolongan = new UpdateGolongan();
        setUpdateGolongan(addGolongan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[UpdateGolonganAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[UpdateGolonganAction.edit] start process >>>");
        String itemId = getId();
        int periode = Integer.parseInt(getPeriode()) - 1;

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<UpdateGolongan> listDataGolongan = (List<UpdateGolongan>) session.getAttribute("listDataGolongan");

        UpdateGolongan editGolongan = new UpdateGolongan();

        try {
            //session.setAttribute("dataPayroll", payroll);
            if(listDataGolongan == null){
                listDataGolongan = updateGolonganBoProxy.getDataEdit(itemId, periode + "");
            }

            session.setAttribute("listDataGolongan", listDataGolongan);
            editGolongan = init(itemId, "Y");
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = updateGolonganBoProxy.saveErrorMessage(e.getMessage(), "UpdateGolonganBO.getGolonganByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GolonganAction.edit] Error when retrieving edit data,", e1);
            }
            logger.error("[UpdateGolonganAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            return "failure";
        }

        if(editGolongan != null) {
            setUpdateGolongan(editGolongan);
        } else {
            editGolongan.setUpdateGolonganId(itemId);
            setUpdateGolongan(editGolongan);
            addActionError("Error, Unable to find data with id = " + itemId);
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[UpdateGolonganAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[GolonganAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        UpdateGolongan deleteGolongan = new UpdateGolongan();

        if (itemFlag != null ) {

            try {
                deleteGolongan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = updateGolonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[GolonganAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[GolonganAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteGolongan != null) {
                setUpdateGolongan(deleteGolongan);

            } else {
                deleteGolongan.setUpdateGolonganId(itemId);
                deleteGolongan.setFlag(itemFlag);
                setUpdateGolongan(deleteGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteGolongan.setUpdateGolonganId(itemId);
            deleteGolongan.setFlag(itemFlag);
            setUpdateGolongan(deleteGolongan);
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
        logger.info("[GolonganAction.saveEdit] start process >>>");
        try {

            UpdateGolongan editGolongan = getUpdateGolongan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editGolongan.setLastUpdateWho(userLogin);
            editGolongan.setLastUpdate(updateTime);
            editGolongan.setAction("U");
            editGolongan.setFlag("Y");

            updateGolonganBoProxy.saveEdit(editGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = updateGolonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[GolonganAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GolonganAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[GolonganAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[GolonganAction.saveDelete] start process >>>");
        try {

            UpdateGolongan deleteGolongan = getUpdateGolongan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteGolongan.setLastUpdate(updateTime);
            deleteGolongan.setLastUpdateWho(userLogin);
            deleteGolongan.setAction("U");
            deleteGolongan.setFlag("N");

            updateGolonganBoProxy.saveDelete(deleteGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = updateGolonganBoProxy.saveErrorMessage(e.getMessage(), "LiburBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AlatAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AlatAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[AlatAction.saveAdd] start process >>>");

        try {
            UpdateGolongan updateGolongan = getUpdateGolongan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            updateGolongan.setPeriode(updateGolongan.getPeriode());
            updateGolongan.setBranchId(updateGolongan.getBranchId());

            updateGolongan.setCreatedWho(userLogin);
            updateGolongan.setLastUpdate(updateTime);
            updateGolongan.setCreatedDate(updateTime);
            updateGolongan.setLastUpdateWho(userLogin);
            updateGolongan.setAction("C");
            updateGolongan.setFlag("Y");

            updateGolonganBoProxy.saveAdd(updateGolongan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = updateGolonganBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[GolonganAction.search] start process >>>");

        UpdateGolongan searchGolongan = getUpdateGolongan();
        List<UpdateGolongan> listOfsearchGolongan = new ArrayList();

        try {
            listOfsearchGolongan = updateGolonganBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = updateGolonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GolonganAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GolonganAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchGolongan);

        logger.info("[AlatAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[GolonganAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listDataGolongan");
        logger.info("[GolonganAction.initForm] end process >>>");
        return INPUT;
    }

    public void updateGolonganBySession(String nip, String golonganId, String golonganName, String poin, String poinLebih){
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<UpdateGolongan> listOfResult = (List<UpdateGolongan>) session.getAttribute("listDataGolongan");
        List<UpdateGolongan> hasil = new ArrayList<>();

        if(nip != null && !"".equalsIgnoreCase(nip)) {
            if (listOfResult != null) {
                for (UpdateGolongan golonganLoop : listOfResult) {
                    if (nip.equalsIgnoreCase(golonganLoop.getNip())) {
                        String status = "";

                        golonganLoop.setGolonganId(golonganId);
                        golonganLoop.setPoin(poin);
                        golonganLoop.setPoinLebih(poinLebih);

                        String golonganBaru = "-" ;
                        if(golonganName != null){
                            golonganBaru = golonganName.substring(9);
                        }

                        if(!golonganLoop.getPoinLebih().equalsIgnoreCase("0")){
                            golonganLoop.setStrGolongan(golonganBaru + " / " + golonganLoop.getPoin() + "+"
                                    + golonganLoop.getPoinLebih());
                        }else{
                            golonganLoop.setStrGolongan(golonganBaru + " / " + golonganLoop.getPoin());
                        }

                        // menentukan status
                        if(golonganLoop.getGolonganIdBefore() != null && !golonganLoop.getGolonganIdBefore().equalsIgnoreCase("") &&
                                golonganLoop.getGolonganId() != null && !golonganLoop.getGolonganId().equalsIgnoreCase("")){
                            int golLama = Integer.parseInt(golonganLoop.getGolonganIdBefore().substring(1));
                            int golBaru = Integer.parseInt(golonganLoop.getGolonganId().substring(1));
                            if(golBaru > golLama){
                                golonganLoop.setStatus("Naik Golongan");
                            }else{
                                golonganLoop.setStatus("Tetap");
                            }
                        }
                    }

                    hasil.add(golonganLoop);
                }
            }
        }

        session.removeAttribute("listDataGolongan");
        session.setAttribute("listDataGolongan", hasil);

    }

    public void saveData(){
        HttpSession session = ServletActionContext.getRequest().getSession();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UpdateGolonganBo updateGolonganBo = (UpdateGolonganBo) ctx.getBean("updateGolonganBoProxy");
        updateGolonganBo.saveData();
        session.removeAttribute("listDataGolongan");
    }


    public String paging(){
        return SUCCESS;
    }

    public String printGolongan(){
        String id = getId();
        int periode = Integer.parseInt(getPeriode()) - 1;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UpdateGolonganBo updateGolonganBo = (UpdateGolonganBo) ctx.getBean("updateGolonganBoProxy");

        List<UpdateGolongan> listData = updateGolonganBo.printReport(id, periode + "");
        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "Report Kenaikan Golongan");
        reportParams.put("itemDataSource", itemData);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId =updateGolonganBoProxy.saveErrorMessage(e.getMessage(), "searchReport");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.searchReport] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.searchReport] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }

        return "print_report_golongan";
    }

    public void saveApprove(String updateGolonganId){
        HttpSession session = ServletActionContext.getRequest().getSession();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UpdateGolonganBo updateGolonganBo = (UpdateGolonganBo) ctx.getBean("updateGolonganBoProxy");
        updateGolonganBo.saveApprove(updateGolonganId);
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
