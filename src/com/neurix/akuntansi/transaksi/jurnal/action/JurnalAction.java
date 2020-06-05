package com.neurix.akuntansi.transaksi.jurnal.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.transaksi.jurnal.bo.JurnalBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.jurnal.model.JurnalDetail;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class JurnalAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(JurnalAction.class);
    private JurnalBo jurnalBoProxy;
    private Jurnal jurnal;
    private List<Jurnal> listOfComboJurnal = new ArrayList<Jurnal>();

    public List<Jurnal> getListOfComboJurnal() {
        return listOfComboJurnal;
    }

    public void setListOfComboJurnal(List<Jurnal> listOfComboJurnal) {
        this.listOfComboJurnal = listOfComboJurnal;
    }


    public JurnalBo getJurnalBoProxy() {
        return jurnalBoProxy;
    }

    public void setJurnalBoProxy(JurnalBo jurnalBoProxy) {
        this.jurnalBoProxy = jurnalBoProxy;
    }

    public Jurnal getJurnal() {
        return jurnal;
    }

    public void setJurnal(Jurnal jurnal) {
        this.jurnal = jurnal;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JurnalAction.logger = logger;
    }


    public String initComboJurnal() {
        logger.info("[JurnalAction.initComboJurnal] start process >>>");

        Jurnal search = new Jurnal();
        List<Jurnal> jurnalList = new ArrayList();
        search.setFlag("Y");
        try {
            jurnalList = jurnalBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jurnalBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JurnalAction.initComboJurnal] Error when saving error,", e1);
            }
            logger.error("[JurnalAction.initComboJurnal] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboJurnal.addAll(jurnalList);
        logger.info("[JurnalAction.initComboJurnal] end process <<<");
        return SUCCESS;
    }

    public Jurnal init(String kode, String flag){
        logger.info("[JurnalAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Jurnal> listOfResult = (List<Jurnal>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Jurnal jurnal: listOfResult) {
                    if(kode.equalsIgnoreCase(jurnal.getNoJurnal()) && flag.equalsIgnoreCase(jurnal.getFlag())){
                        setJurnal(jurnal);
                        break;
                    }
                }
            } else {
                setJurnal(new Jurnal());
            }

            logger.info("[JurnalAction.init] end process >>>");
        }
        return getJurnal();
    }

    @Override
    public String add() {
        logger.info("[JurnalAction.add] start process >>>");
        Jurnal addJurnal = new Jurnal();
        setJurnal(addJurnal);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[JurnalAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[JurnalAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Jurnal editJurnal = new Jurnal();

        if(itemFlag != null){
            try {
                editJurnal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jurnalBoProxy.saveErrorMessage(e.getMessage(), "JurnalBO.getJurnalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[JurnalAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[JurnalAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editJurnal != null) {
                setJurnal(editJurnal);
            } else {
                editJurnal.setFlag(itemFlag);
                editJurnal.setNoJurnal(itemId);
                setJurnal(editJurnal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJurnal.setNoJurnal(itemId);
            editJurnal.setFlag(getFlag());
            setJurnal(editJurnal);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        HttpSession session = ServletActionContext.getRequest().getSession();

        logger.info("[JurnalAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[JurnalAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Jurnal deleteJurnal = new Jurnal();

        if (itemFlag != null ) {

            try {
                deleteJurnal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jurnalBoProxy.saveErrorMessage(e.getMessage(), "JurnalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[JurnalAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[JurnalAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteJurnal != null) {
                setJurnal(deleteJurnal);

            } else {
                deleteJurnal.setNoJurnal(itemId);
                deleteJurnal.setFlag(itemFlag);
                setJurnal(deleteJurnal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteJurnal.setNoJurnal(itemId);
            deleteJurnal.setFlag(itemFlag);
            setJurnal(deleteJurnal);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[JurnalAction.delete] end process <<<");
        HttpSession session = ServletActionContext.getRequest().getSession();
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[JurnalAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Jurnal deleteJurnal = new Jurnal();

        if (itemFlag != null ) {
            try {
                deleteJurnal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jurnalBoProxy.saveErrorMessage(e.getMessage(), "JurnalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[JurnalAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[JurnalAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteJurnal != null) {
                setJurnal(deleteJurnal);

            } else {
                deleteJurnal.setNoJurnal(itemId);
                deleteJurnal.setFlag(itemFlag);
                setJurnal(deleteJurnal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteJurnal.setNoJurnal(itemId);
            deleteJurnal.setFlag(itemFlag);
            setJurnal(deleteJurnal);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[JurnalAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[JurnalAction.saveEdit] start process >>>");
        try {
            Jurnal editJurnal = getJurnal();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editJurnal.setLastUpdateWho(userLogin);
            editJurnal.setLastUpdate(updateTime);
            editJurnal.setAction("U");
            editJurnal.setFlag("Y");

            jurnalBoProxy.saveEdit(editJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jurnalBoProxy.saveErrorMessage(e.getMessage(), "JurnalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[JurnalAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JurnalAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[JurnalAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[JurnalAction.saveDelete] start process >>>");
        try {
            Jurnal deleteJurnal = getJurnal();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteJurnal.setLastUpdate(updateTime);
            deleteJurnal.setLastUpdateWho(userLogin);
            deleteJurnal.setAction("U");
            deleteJurnal.setFlag("N");

            jurnalBoProxy.saveDelete(deleteJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jurnalBoProxy.saveErrorMessage(e.getMessage(), "JurnalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[JurnalAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JurnalAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[JurnalAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[JurnalAction.saveAdd] start process >>>");

        try {
            Jurnal jurnal = getJurnal();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jurnal.setCreatedWho(userLogin);
            jurnal.setLastUpdate(updateTime);
            jurnal.setCreatedDate(updateTime);
            jurnal.setLastUpdateWho(userLogin);
            jurnal.setAction("C");
            jurnal.setFlag("Y");

            jurnalBoProxy.saveAdd(jurnal);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jurnalBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[JurnalAction.search] start process >>>");
        Jurnal searchJurnal = getJurnal();
        List<Jurnal> listOfsearchJurnal = new ArrayList();
        try {
            listOfsearchJurnal = jurnalBoProxy.getByCriteria(searchJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jurnalBoProxy.saveErrorMessage(e.getMessage(), "JurnalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JurnalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JurnalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.setAttribute("listOfResult", listOfsearchJurnal);

        logger.info("[JurnalAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[JurnalAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");
        logger.info("[JurnalAction.initForm] end process >>>");
        return INPUT;
    }

    public String deleteDetailPembayaran(String kodeVendor) {
        logger.info("[JurnalAction.deleteDetailPembayaran] start process >>>");
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JurnalDetail> piutangDetailList = (List<JurnalDetail>) session.getAttribute("listOfResultPembayaranDetail");
        List<JurnalDetail> piutangDetailArrayList = new ArrayList<>();
        for (JurnalDetail jurnalDetail:piutangDetailList){
            if (jurnalDetail.getMasterId().equalsIgnoreCase(kodeVendor)){
                break;
            }else{
                piutangDetailArrayList.add(jurnalDetail);
            }
        }
        session.setAttribute("listOfResultPembayaranDetail",piutangDetailArrayList);
        logger.info("[JurnalAction.deleteDetailPembayaran] end process >>>");
        return status;
    }
    public List<JurnalDetail> searchDetailPembayaran() {
        logger.info("[JurnalAction.searchDetailPembayaran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JurnalDetail> jurnalDetailList = (List<JurnalDetail>) session.getAttribute("listOfResultPembayaranDetail");

        logger.info("[JurnalAction.searchDetailPembayaran] end process >>>");
        return jurnalDetailList;
    }
    public String cekBeforeSave(String tipeTransaksi,String tanggal,String kodeRekeningKas,String bayar,String keterangan,String noslipBank){
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JurnalDetail> jurnalDetailList = (List<JurnalDetail>) session.getAttribute("listOfResultPembayaranDetail");

        if (tipeTransaksi.equalsIgnoreCase("")||tanggal.equalsIgnoreCase("")||kodeRekeningKas.equalsIgnoreCase("")||bayar.equalsIgnoreCase("")||keterangan.equalsIgnoreCase("")||noslipBank.equalsIgnoreCase("")){
            if (tipeTransaksi.equalsIgnoreCase("")){
                status+="Tipe transaksi masih kosong <br>";
            }
            if (tanggal.equalsIgnoreCase("")){
                status+="Tanggal masih kosong <br>";
            }
            if (kodeRekeningKas.equalsIgnoreCase("")){
                status+="Kode rekening kas masih kosong <br>";
            }
            if (bayar.equalsIgnoreCase("")){
                status+="Jumlah pembayaran masih kosong <br>";
            }
            /*if (keterangan.equalsIgnoreCase("")){
                status+="Keterangan masih kosong <br>";
            }
            if (noslipBank.equalsIgnoreCase("")){
                status+="No. Slip masih kosong <br>";
            }*/
        }else if(jurnalDetailList==null||jurnalDetailList.size()==0){
            status+="Detail pembayaran belum ada <br>";
        }else{
            BigDecimal totalBayar = BigDecimal.valueOf(Double.valueOf(bayar.replace(".","")));
            BigDecimal bayarDetail = BigDecimal.ZERO;
            for (JurnalDetail data : jurnalDetailList){
                bayarDetail= bayarDetail.add( new BigDecimal(data.getBiaya().replace(".","")));
            }
            if (!totalBayar.equals(bayarDetail)){
                if (totalBayar.compareTo(bayarDetail)>0){
                    status="Jumlah pembayaran masih kurang dari total bayar";
                }else if (totalBayar.compareTo(bayarDetail)<0){
                    status="Jumlah pembayaran melebihi total bayar";
                }
            }
        }
        return status;
    }


    public JurnalDetail getBudgetTerpakai(String branchId,String divisiId,String tanggal,String noBudgetting,String budget){
        JurnalDetail data = new JurnalDetail();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JurnalBo jurnalBo = (JurnalBo) ctx.getBean("jurnalBoProxy");
        String[] arrTanggal = tanggal.split("-");
        String[] coa = noBudgetting.split("-");
        data = jurnalBo.getBudgetTerpakai(branchId,divisiId,arrTanggal[0],arrTanggal[1],coa[3],budget);

        return data;
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
