package com.neurix.hris.transaksi.absensi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.absensi.bo.MesinAbsensiDetailBo;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetail;
import com.neurix.hris.transaksi.absensi.bo.MesinAbsensiDetailBo;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetail;
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

public class MesinAbsensiDetailAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(MesinAbsensiDetailAction.class);
    private MesinAbsensiDetailBo mesinAbsensiDetailBoProxy;
    private MesinAbsensiDetail mesinAbsensiDetail;
    private List<MesinAbsensiDetail> listComboMesinAbsensiDetail = new ArrayList<MesinAbsensiDetail>();

    public List<MesinAbsensiDetail> getListComboMesinAbsensiDetail() {
        return listComboMesinAbsensiDetail;
    }

    public void setListComboMesinAbsensiDetail(List<MesinAbsensiDetail> listComboMesinAbsensiDetail) {
        this.listComboMesinAbsensiDetail = listComboMesinAbsensiDetail;
    }

    public MesinAbsensiDetailBo getMesinAbsensiDetailBoProxy() {
        return mesinAbsensiDetailBoProxy;
    }

    public void setMesinAbsensiDetailBoProxy(MesinAbsensiDetailBo mesinAbsensiDetailBoProxy) {
        this.mesinAbsensiDetailBoProxy = mesinAbsensiDetailBoProxy;
    }

    public MesinAbsensiDetail getMesinAbsensiDetail() {
        return mesinAbsensiDetail;
    }

    public void setMesinAbsensiDetail(MesinAbsensiDetail mesinAbsensiDetail) {
        this.mesinAbsensiDetail = mesinAbsensiDetail;
    }

    private List<MesinAbsensiDetail> initComboMesinAbsensiDetail;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MesinAbsensiDetailAction.logger = logger;
    }


    public List<MesinAbsensiDetail> getInitComboMesinAbsensiDetail() {
        return initComboMesinAbsensiDetail;
    }

    public void setInitComboMesinAbsensiDetail(List<MesinAbsensiDetail> initComboMesinAbsensiDetail) {
        this.initComboMesinAbsensiDetail = initComboMesinAbsensiDetail;
    }

    public MesinAbsensiDetail init(String kode, String flag){
        logger.info("[MesinAbsensiDetailAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MesinAbsensiDetail> listOfResult = (List<MesinAbsensiDetail>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (MesinAbsensiDetail mesinAbsensiDetail: listOfResult) {
                    if(kode.equalsIgnoreCase(mesinAbsensiDetail.getMesinAbsensiDetailId()) && flag.equalsIgnoreCase(mesinAbsensiDetail.getFlag())){
                        setMesinAbsensiDetail(mesinAbsensiDetail);
                        break;
                    }
                }
            } else {
                setMesinAbsensiDetail(new MesinAbsensiDetail());
            }

            logger.info("[MesinAbsensiDetailAction.init] end process >>>");
        }
        return getMesinAbsensiDetail();
    }

    @Override
    public String add() {
        logger.info("[MesinAbsensiDetailAction.add] start process >>>");
        MesinAbsensiDetail addMesinAbsensiDetail = new MesinAbsensiDetail();
        setMesinAbsensiDetail(addMesinAbsensiDetail);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[MesinAbsensiDetailAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[MesinAbsensiDetailAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        MesinAbsensiDetail editMesinAbsensiDetail = new MesinAbsensiDetail();

        if(itemFlag != null){
            try {
                editMesinAbsensiDetail = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mesinAbsensiDetailBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiDetailBO.getMesinAbsensiDetailByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[MesinAbsensiDetailAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[MesinAbsensiDetailAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editMesinAbsensiDetail != null) {
                setMesinAbsensiDetail(editMesinAbsensiDetail);
            } else {
                editMesinAbsensiDetail.setFlag(itemFlag);
                editMesinAbsensiDetail.setMesinAbsensiDetailId(itemId);
                setMesinAbsensiDetail(editMesinAbsensiDetail);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editMesinAbsensiDetail.setMesinAbsensiDetailId(itemId);
            editMesinAbsensiDetail.setFlag(getFlag());
            setMesinAbsensiDetail(editMesinAbsensiDetail);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[MesinAbsensiDetailAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[MesinAbsensiDetailAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MesinAbsensiDetail deleteMesinAbsensiDetail = new MesinAbsensiDetail();

        if (itemFlag != null ) {

            try {
                deleteMesinAbsensiDetail = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mesinAbsensiDetailBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiDetailBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[MesinAbsensiDetailAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[MesinAbsensiDetailAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteMesinAbsensiDetail != null) {
                setMesinAbsensiDetail(deleteMesinAbsensiDetail);

            } else {
                deleteMesinAbsensiDetail.setMesinAbsensiDetailId(itemId);
                deleteMesinAbsensiDetail.setFlag(itemFlag);
                setMesinAbsensiDetail(deleteMesinAbsensiDetail);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteMesinAbsensiDetail.setMesinAbsensiDetailId(itemId);
            deleteMesinAbsensiDetail.setFlag(itemFlag);
            setMesinAbsensiDetail(deleteMesinAbsensiDetail);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[MesinAbsensiDetailAction.delete] end process <<<");

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
        logger.info("[MesinAbsensiDetailAction.saveEdit] start process >>>");
        try {

            MesinAbsensiDetail editMesinAbsensiDetail = getMesinAbsensiDetail();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editMesinAbsensiDetail.setLastUpdateWho(userLogin);
            editMesinAbsensiDetail.setLastUpdate(updateTime);
            editMesinAbsensiDetail.setAction("U");
            editMesinAbsensiDetail.setFlag("Y");

            mesinAbsensiDetailBoProxy.saveEdit(editMesinAbsensiDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiDetailBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiDetailBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MesinAbsensiDetailAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MesinAbsensiDetailAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MesinAbsensiDetailAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[MesinAbsensiDetailAction.saveDelete] start process >>>");
        try {

            MesinAbsensiDetail deleteMesinAbsensiDetail = getMesinAbsensiDetail();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteMesinAbsensiDetail.setLastUpdate(updateTime);
            deleteMesinAbsensiDetail.setLastUpdateWho(userLogin);
            deleteMesinAbsensiDetail.setAction("U");
            deleteMesinAbsensiDetail.setFlag("N");

            mesinAbsensiDetailBoProxy.saveDelete(deleteMesinAbsensiDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiDetailBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiDetailBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[MesinAbsensiDetailAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MesinAbsensiDetailAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MesinAbsensiDetailAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[MesinAbsensiDetailAction.saveAdd] start process >>>");

        try {
            MesinAbsensiDetail mesinAbsensiDetail = getMesinAbsensiDetail();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            mesinAbsensiDetail.setCreatedWho(userLogin);
            mesinAbsensiDetail.setLastUpdate(updateTime);
            mesinAbsensiDetail.setCreatedDate(updateTime);
            mesinAbsensiDetail.setLastUpdateWho(userLogin);
            mesinAbsensiDetail.setAction("C");
            mesinAbsensiDetail.setFlag("Y");

            mesinAbsensiDetailBoProxy.saveAdd(mesinAbsensiDetail);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiDetailBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[MesinAbsensiDetailAction.search] start process >>>");

        MesinAbsensiDetail searchMesinAbsensiDetail = getMesinAbsensiDetail();
        if (!("").equalsIgnoreCase(searchMesinAbsensiDetail.getStTanggalDari())||searchMesinAbsensiDetail.getStTanggalDari()!=null){
            searchMesinAbsensiDetail.setTanggalDari(CommonUtil.convertStringToDate(searchMesinAbsensiDetail.getStTanggalDari()+" 00:00:00"));
        }
        if (!("").equalsIgnoreCase(searchMesinAbsensiDetail.getStTanggalSelesai())||searchMesinAbsensiDetail.getStTanggalSelesai()!=null){
            searchMesinAbsensiDetail.setTanggalSelesai(CommonUtil.convertStringToDate(searchMesinAbsensiDetail.getStTanggalSelesai()+" 00:00:00"));
        }
        List<MesinAbsensiDetail> listOfsearchMesinAbsensiDetail = new ArrayList();

        try {
            listOfsearchMesinAbsensiDetail = mesinAbsensiDetailBoProxy.getByCriteria(searchMesinAbsensiDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiDetailBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiDetailBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MesinAbsensiDetailAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MesinAbsensiDetailAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchMesinAbsensiDetail);

        logger.info("[MesinAbsensiDetailAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[MesinAbsensiDetailAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[MesinAbsensiDetailAction.initForm] end process >>>");
        return INPUT;
    }

    public String initMesinAbsensiDetail() {
        logger.info("[MesinAbsensiDetailAction.search] start process >>>");

        MesinAbsensiDetail searchMesinAbsensiDetail = new MesinAbsensiDetail();
        searchMesinAbsensiDetail.setFlag("Y");
        List<MesinAbsensiDetail> listOfsearchMesinAbsensiDetail = new ArrayList();

        try {
            listOfsearchMesinAbsensiDetail = mesinAbsensiDetailBoProxy.getByCriteria(searchMesinAbsensiDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiDetailBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiDetailBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MesinAbsensiDetailAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MesinAbsensiDetailAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultMesinAbsensiDetail");
        session.setAttribute("listOfResultMesinAbsensiDetail", listOfsearchMesinAbsensiDetail);

        logger.info("[MesinAbsensiDetailAction.search] end process <<<");

        return "";
    }

    public String initComboMesinAbsensiDetail() {
        logger.info("[BranchAction.search] start process >>>");

        MesinAbsensiDetail searchMesinAbsensiDetail = new MesinAbsensiDetail();
        List<MesinAbsensiDetail> listOfSearchMesinAbsensiDetail = new ArrayList();
        searchMesinAbsensiDetail.setFlag("Y");
        try {
            listOfSearchMesinAbsensiDetail = mesinAbsensiDetailBoProxy.getByCriteria(searchMesinAbsensiDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mesinAbsensiDetailBoProxy.saveErrorMessage(e.getMessage(), "MesinAbsensiDetailBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MesinAbsensiDetailAction.search] Error when saving error,", e1);
            }
            logger.error("[MesinAbsensiDetailAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboMesinAbsensiDetail.addAll(listOfSearchMesinAbsensiDetail);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

//    public List initComboMesinAbsensiDetailTipe(String query) {
//        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");
//
//        List<MesinAbsensiDetail> listOfAlat = new ArrayList();
//
//        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        MesinAbsensiDetailBo mesinAbsensiDetailBo = (MesinAbsensiDetailBo) ctx.getBean("mesinAbsensiDetailBoProxy");
//
//        try {
//            listOfAlat = mesinAbsensiDetailBo.getComboMesinAbsensiDetailTipeWithCriteria(query);
//        } catch (GeneralBOException e) {
//            Long logId = null;
//            try {
//                logId = mesinAbsensiDetailBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
//            } catch (GeneralBOException e1) {
//                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
//            }
//            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
//        }
//
//        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");
//
//        return listOfAlat;
//    }

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
