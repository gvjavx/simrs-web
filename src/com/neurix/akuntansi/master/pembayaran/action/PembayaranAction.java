package com.neurix.akuntansi.master.pembayaran.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.pembayaran.bo.PembayaranBo;
import com.neurix.akuntansi.master.pembayaran.model.Pembayaran;
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

public class PembayaranAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PembayaranAction.class);
    private PembayaranBo pembayaranBoProxy;
    private Pembayaran pembayaran;

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public PembayaranBo getPembayaranBoProxy() {
        return pembayaranBoProxy;
    }

    public void setPembayaranBoProxy(PembayaranBo pembayaranBoProxy) {
        this.pembayaranBoProxy = pembayaranBoProxy;
    }

    public Pembayaran init(String kode, String flag){
        logger.info("[PembayaranAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pembayaran> listOfResult = (List<Pembayaran>) session.getAttribute("listOfResultPembayaran");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Pembayaran pembayaran: listOfResult) {
                    if(kode.equalsIgnoreCase(pembayaran.getPembayaranId()) && flag.equalsIgnoreCase(pembayaran.getFlag())){
                        setPembayaran(pembayaran);
                        break;
                    }
                }
            } else {
                setPembayaran(new Pembayaran());
            }

            logger.info("[PembayaranAction.init] end process >>>");
        }
        return getPembayaran();
    }

    @Override
    public String add() {
        logger.info("[PembayaranAction.add] start process >>>");
        Pembayaran addPembayaran = new Pembayaran();
        setPembayaran(addPembayaran);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[PembayaranAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PembayaranAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Pembayaran editPembayaran = new Pembayaran();

        if(itemFlag != null){
            try {
                editPembayaran = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pembayaranBoProxy.saveErrorMessage(e.getMessage(), "PembayaranBO.edit");
                } catch (GeneralBOException e1) {
                    logger.error("[PembayaranAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PembayaranAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPembayaran != null) {
                setPembayaran(editPembayaran);
            } else {
                editPembayaran.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setPembayaran(editPembayaran);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editPembayaran.setFlag(getFlag());
            setPembayaran(editPembayaran);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PembayaranAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PembayaranAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Pembayaran deletePembayaran = new Pembayaran();

        if (itemFlag != null ) {
            try {
                deletePembayaran = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pembayaranBoProxy.saveErrorMessage(e.getMessage(), "PembayaranBO.getPembayaranById");
                } catch (GeneralBOException e1) {
                    logger.error("[PembayaranAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PembayaranAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePembayaran != null) {
                setPembayaran(deletePembayaran);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deletePembayaran.setFlag(itemFlag);
                setPembayaran(deletePembayaran);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deletePembayaran.setFlag(itemFlag);
            setPembayaran(deletePembayaran);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PembayaranAction.delete] end process <<<");

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
        logger.info("[AkunPembayaranAction.search] start process >>>");

        Pembayaran searchPembayaran = getPembayaran();
        List<Pembayaran> listOfsearchPembayaran = new ArrayList();

        try {
            listOfsearchPembayaran = pembayaranBoProxy.getByCriteria(searchPembayaran);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranBoProxy.saveErrorMessage(e.getMessage(), "PembayaranBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPembayaran");
        session.setAttribute("listOfResultPembayaran", listOfsearchPembayaran);

        logger.info("[PembayaranAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipeJurnalAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[TipeJurnalAction.initForm] end process >>>");
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

    public List<KodeRekening> initTypeaheadKodeRekening(String coa) {
        logger.info("[KodeRekeningAction.initTypeaheadKodeRekening] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        List<KodeRekening> kodeRekeningList = new ArrayList();
        try {
            kodeRekeningList = kodeRekeningBo.typeaheadKodeRekening(coa);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kodeRekeningBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KodeRekeningAction.initTypeaheadKodeRekening] Error when saving error,", e1);
            }
            logger.error("[KodeRekeningAction.initTypeaheadKodeRekening] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return kodeRekeningList;
    }

    public String saveAdd(){
        logger.info("[PembayaranAction.saveAdd] start process >>>");

        try {
            Pembayaran pembayaran = getPembayaran();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pembayaran.setCreatedWho(userLogin);
            pembayaran.setLastUpdate(updateTime);
            pembayaran.setCreatedDate(updateTime);
            pembayaran.setLastUpdateWho(userLogin);
            pembayaran.setAction("C");
            pembayaran.setFlag("Y");

            pembayaranBoProxy.saveAdd(pembayaran);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranBoProxy.saveErrorMessage(e.getMessage(), "pembayaranBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[pembayaranAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[pemabayaranAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPembayaran");

        logger.info("[pembayaranAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[PembayaranAction.saveEdit] start process >>>");
        try {

            Pembayaran editPembayaran = getPembayaran();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPembayaran.setLastUpdateWho(userLogin);
            editPembayaran.setLastUpdate(updateTime);
            editPembayaran.setAction("U");
            editPembayaran.setFlag("Y");

            pembayaranBoProxy.saveEdit(editPembayaran);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranBoProxy.saveErrorMessage(e.getMessage(), "PembayaranBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[pembayaranAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PembayaranAction.saveDelete] start process >>>");
        try {

            Pembayaran deletePembayaran = getPembayaran();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePembayaran.setLastUpdate(updateTime);
            deletePembayaran.setLastUpdateWho(userLogin);
            deletePembayaran.setAction("U");
            deletePembayaran.setFlag("N");

            pembayaranBoProxy.saveDelete(deletePembayaran);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranBoProxy.saveErrorMessage(e.getMessage(), "PembayaranBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranAction.saveDelete] Error when editing item PembayaranId," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PembayaranAction.saveDelete] end process <<<");

        return "success_save_delete";
    }
}