package com.neurix.akuntansi.transaksi.penyewaanLahan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.penyewaanLahan.bo.PenyewaanLahanBo;
import com.neurix.akuntansi.transaksi.penyewaanLahan.model.ItAkunPenyewaanLahanEntity;
import com.neurix.akuntansi.transaksi.penyewaanLahan.model.PenyewaanLahan;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class PenyewaanLahanAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PenyewaanLahanAction.class);
    private PenyewaanLahanBo penyewaanLahanBoProxy;
    private PenyewaanLahan penyewaanLahan;
    private List<PenyewaanLahan> listOfComboPenyewaanLahan = new ArrayList<PenyewaanLahan>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PenyewaanLahanAction.logger = logger;
    }

    public PenyewaanLahanBo getPenyewaanLahanBoProxy() {
        return penyewaanLahanBoProxy;
    }

    public void setPenyewaanLahanBoProxy(PenyewaanLahanBo penyewaanLahanBoProxy) {
        this.penyewaanLahanBoProxy = penyewaanLahanBoProxy;
    }

    public PenyewaanLahan getPenyewaanLahan() {
        return penyewaanLahan;
    }

    public void setPenyewaanLahan(PenyewaanLahan penyewaanLahan) {
        this.penyewaanLahan = penyewaanLahan;
    }

    public List<PenyewaanLahan> getListOfComboPenyewaanLahan() {
        return listOfComboPenyewaanLahan;
    }

    public void setListOfComboPenyewaanLahan(List<PenyewaanLahan> listOfComboPenyewaanLahan) {
        this.listOfComboPenyewaanLahan = listOfComboPenyewaanLahan;
    }

    public PenyewaanLahan init(String kode, String flag){
        logger.info("[PenyewaanLahanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PenyewaanLahan> listOfResult = (List<PenyewaanLahan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PenyewaanLahan penyewaanLahan: listOfResult) {
                    if(kode.equalsIgnoreCase(penyewaanLahan.getPenyewaanLahanId()) && flag.equalsIgnoreCase(penyewaanLahan.getFlag())){
                        setPenyewaanLahan(penyewaanLahan);
                        break;
                    }
                }
            } else {
                setPenyewaanLahan(new PenyewaanLahan());
            }

            logger.info("[PenyewaanLahanAction.init] end process >>>");
        }
        return getPenyewaanLahan();
    }

    @Override
    public String add() {
        logger.info("[PenyewaanLahanAction.add] start process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PenyewaanLahanAction.edit] start process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PenyewaanLahanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PenyewaanLahan deletePenyewaanLahan = new PenyewaanLahan();

        if (itemFlag != null ) {

            try {
                deletePenyewaanLahan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PenyewaanLahanAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PenyewaanLahanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePenyewaanLahan != null) {
                setPenyewaanLahan(deletePenyewaanLahan);

            } else {
                deletePenyewaanLahan.setPenyewaanLahanId(itemId);
                deletePenyewaanLahan.setFlag(itemFlag);
                setPenyewaanLahan(deletePenyewaanLahan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePenyewaanLahan.setPenyewaanLahanId(itemId);
            deletePenyewaanLahan.setFlag(itemFlag);
            setPenyewaanLahan(deletePenyewaanLahan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PenyewaanLahanAction.delete] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[PenyewaanLahanAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PenyewaanLahan deletePenyewaanLahan = new PenyewaanLahan();

        if (itemFlag != null ) {
            try {
                deletePenyewaanLahan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PenyewaanLahanAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[PenyewaanLahanAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePenyewaanLahan != null) {
                setPenyewaanLahan(deletePenyewaanLahan);

            } else {
                deletePenyewaanLahan.setPenyewaanLahanId(itemId);
                deletePenyewaanLahan.setFlag(itemFlag);
                setPenyewaanLahan(deletePenyewaanLahan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePenyewaanLahan.setPenyewaanLahanId(itemId);
            deletePenyewaanLahan.setFlag(itemFlag);
            setPenyewaanLahan(deletePenyewaanLahan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[PenyewaanLahanAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[PenyewaanLahanAction.saveEdit] start process >>>");
        try {
            PenyewaanLahan editPenyewaanLahan = getPenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPenyewaanLahan.setLastUpdateWho(userLogin);
            editPenyewaanLahan.setLastUpdate(updateTime);
            editPenyewaanLahan.setAction("U");
            editPenyewaanLahan.setFlag("Y");

            penyewaanLahanBoProxy.saveEdit(editPenyewaanLahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PenyewaanLahanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PenyewaanLahanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PenyewaanLahanAction.saveDelete] start process >>>");
        try {
            PenyewaanLahan deletePenyewaanLahan = getPenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePenyewaanLahan.setLastUpdate(updateTime);
            deletePenyewaanLahan.setLastUpdateWho(userLogin);
            deletePenyewaanLahan.setAction("U");
            deletePenyewaanLahan.setFlag("N");

            penyewaanLahanBoProxy.saveDelete(deletePenyewaanLahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PenyewaanLahanAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[PenyewaanLahanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public void saveAdd(String branchId,String namaPenyewa,String stTanggal,String stNilai,String metodeBayar,String keterangan,String bank){
        logger.info("[PenyewaanLahanAction.saveAdd] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PenyewaanLahanBo penyewaanLahanBo= (PenyewaanLahanBo) ctx.getBean("penyewaanLahanBoProxy");

        try {
            PenyewaanLahan penyewaanLahan = new PenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            penyewaanLahan.setBranchId(branchId);
            penyewaanLahan.setNamaPenyewa(namaPenyewa);
            penyewaanLahan.setTanggalBayar(CommonUtil.convertStringToDate(stTanggal));
            penyewaanLahan.setNilai(BigDecimal.valueOf(Double.valueOf(stNilai.replace(".",""))));
            penyewaanLahan.setMetodeBayar(metodeBayar);
            penyewaanLahan.setBank(bank);
            penyewaanLahan.setKeterangan(keterangan);
            penyewaanLahan.setCancelFlag("N");
            penyewaanLahan.setCreatedWho(userLogin);
            penyewaanLahan.setLastUpdate(updateTime);
            penyewaanLahan.setCreatedDate(updateTime);
            penyewaanLahan.setLastUpdateWho(userLogin);
            penyewaanLahan.setAction("C");
            penyewaanLahan.setFlag("Y");

            penyewaanLahanBo.saveAdd(penyewaanLahan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "penyewaanLahanBoProxy.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[PenyewaanLahanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
    }

    @Override
    public String search() {
        logger.info("[PenyewaanLahanAction.search] start process >>>");
        PenyewaanLahan searchPenyewaanLahan = getPenyewaanLahan();
        List<PenyewaanLahan> listOfsearchPenyewaanLahan = new ArrayList();
        try {
            listOfsearchPenyewaanLahan = penyewaanLahanBoProxy.getByCriteria(searchPenyewaanLahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PenyewaanLahanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPenyewaanLahan);

        logger.info("[PenyewaanLahanAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PenyewaanLahanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PenyewaanLahan data = new PenyewaanLahan();

        if (branchId!=null){
            data.setBranchId(branchId);
        }else{
            data.setBranchId("");
        }

        setPenyewaanLahan(data);

        session.removeAttribute("listOfResult");

        logger.info("[PenyewaanLahanAction.initForm] end process >>>");
        return INPUT;
    }


    public PenyewaanLahan getForModalPopUp(String penyewaanLahanId) {
        logger.info("[PenyewaanLahanAction.getForModalPopUp] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PenyewaanLahanBo penyewaanLahanBo= (PenyewaanLahanBo) ctx.getBean("penyewaanLahanBoProxy");
        String itemFlag = "Y";
        PenyewaanLahan modalPopUp = new PenyewaanLahan();
        try {
            modalPopUp = init(penyewaanLahanId, itemFlag);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBo.saveErrorMessage(e.getMessage(), "PenyewaanLahanAction.getForModalPopUp");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.getForModalPopUp] Error when retrieving delete data,", e1);
            }
            logger.error("[PenyewaanLahanAction.getForModalPopUp] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
        }
        return modalPopUp;
    }

    public String postingJurnal(String penyewaanLahanId){
        logger.info("[PenyewaanLahanAction.postingJurnal] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PenyewaanLahanBo penyewaanLahanBo = (PenyewaanLahanBo) ctx.getBean("penyewaanLahanBoProxy");
            BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

            PenyewaanLahan data = new PenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ItAkunPenyewaanLahanEntity penyewaanLahanEntity = penyewaanLahanBo.getPenyewaanLahanById(penyewaanLahanId);

            Map dataPostingJurnal = new HashMap();
            Map kas = new HashMap();
            kas.put("metode_bayar",penyewaanLahanEntity.getMetodeBayar());
            kas.put("bank", penyewaanLahanEntity.getBank());
            kas.put("nilai",penyewaanLahanEntity.getNilai());

            Map mapPpn = new HashMap();
//            mapPpn.put("master_id",);
            mapPpn.put("nilai",penyewaanLahanEntity.getNilaiPpn());

            Map pendapatanPenyewaan = new HashMap();
            pendapatanPenyewaan.put("nilai",penyewaanLahanEntity.getNilaiNetto());

            dataPostingJurnal.put("kas",kas);
            dataPostingJurnal.put("ppn",mapPpn);
            dataPostingJurnal.put("pendapatan_sewa_lahan",pendapatanPenyewaan);

            //disini untuk posting jurnal untuk mendapat nojurnal
            Jurnal jurnal = billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_PENYEWAAN_LAHAN,dataPostingJurnal,penyewaanLahanEntity.getBranchId(),penyewaanLahanEntity.getKeterangan(),"Y");
            data.setPenyewaanLahanId(penyewaanLahanId);
            data.setApprovalDate(updateTime);
            data.setApprovalFlag("Y");
            data.setApprovalWho(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setNoJurnal(jurnal.getNoJurnal());
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            penyewaanLahanBo.postingJurnal(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanAction.postingJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.postingJurnal] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PenyewaanLahanAction.postingJurnal] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PenyewaanLahanAction.postingJurnal] end process <<<");

        return "Sukses Posting Jurnal";
    }

    public String cancelPenyewaanLahan(String penyewaanLahanId){
        logger.info("[PenyewaanLahanAction.cancelPenyewaanLahan] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PenyewaanLahanBo penyewaanLahanBo = (PenyewaanLahanBo) ctx.getBean("penyewaanLahanBoProxy");
            PenyewaanLahan data = new PenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setPenyewaanLahanId(penyewaanLahanId);
            data.setCancelDate(updateTime);
            data.setCancelFlag("Y");
            data.setCancelWho(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            penyewaanLahanBo.batalkanPenyewaanLahan(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanAction.cancelPenyewaanLahan");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.cancelPenyewaanLahan] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PenyewaanLahanAction.cancelPenyewaanLahan] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PenyewaanLahanAction.cancelPenyewaanLahan] end process <<<");

        return "Berhasil Membatalkan Penyewaan Lahan";
    }

    public String paging(){
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }
}
