package com.neurix.akuntansi.transaksi.laporanAkuntansi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.settingReportArusKas.model.AkunSettingReportKeuanganArusKas;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
import com.neurix.akuntansi.master.settingReportUser.bo.SettingReportUserBo;
import com.neurix.akuntansi.master.settingReportUser.model.SettingReportUser;
import com.neurix.akuntansi.master.tipeJurnal.bo.TipeJurnalBo;
import com.neurix.akuntansi.master.tipeJurnal.model.TipeJurnal;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.budgeting.model.BudgettingDTO;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.LaporanAkuntansiBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.*;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerja;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerjaDetail;
import com.neurix.hris.transaksi.laporan.model.Laporan;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class LaporanAkuntansiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(LaporanAkuntansiAction.class);
    private LaporanAkuntansiBo laporanAkuntansiBoProxy;
    private SettingReportUserBo settingReportUserBoProxy;
    private BranchBo branchBoProxy;
    private LaporanAkuntansi laporanAkuntansi;
    private String tipeLaporan;
    private JRBeanCollectionDataSource dataPrint;
    private List<Aging> myList = new ArrayList<>() ;
    private List<NeracaSaldoDTO> listNeracaSaldo = new ArrayList<>() ;
    private List<IkhtisarBukuBesarDTO> listIkhtisarBukuBesar = new ArrayList<>() ;
    private List<PendapatanDTO> listPendapatanDokter = new ArrayList<>();
    private List<KartuBukuBesarPerBukuBantuDTO> listBukuBesar = new ArrayList<>();
    private List<AkunSettingReportKeuanganKonsol> listKonsol= new ArrayList<>() ;
    private List<AkunSettingReportKeuanganArusKas> listArusKas= new ArrayList<>() ;
    private List<ArusKasDTO> arusKasDTOList = new ArrayList<>();
    private List<BudgettingDTO> budgettingDTOList = new ArrayList<>();

    public List<KartuBukuBesarPerBukuBantuDTO> getListBukuBesar() {
        return listBukuBesar;
    }

    public void setListBukuBesar(List<KartuBukuBesarPerBukuBantuDTO> listBukuBesar) {
        this.listBukuBesar = listBukuBesar;
    }

    public List<IkhtisarBukuBesarDTO> getListIkhtisarBukuBesar() {
        return listIkhtisarBukuBesar;
    }

    public void setListIkhtisarBukuBesar(List<IkhtisarBukuBesarDTO> listIkhtisarBukuBesar) {
        this.listIkhtisarBukuBesar = listIkhtisarBukuBesar;
    }

    public List<NeracaSaldoDTO> getListNeracaSaldo() {
        return listNeracaSaldo;
    }

    public void setListNeracaSaldo(List<NeracaSaldoDTO> listNeracaSaldo) {
        this.listNeracaSaldo = listNeracaSaldo;
    }

    public List<BudgettingDTO> getBudgettingDTOList() {
        return budgettingDTOList;
    }

    public void setBudgettingDTOList(List<BudgettingDTO> budgettingDTOList) {
        this.budgettingDTOList = budgettingDTOList;
    }

    public List<ArusKasDTO> getArusKasDTOList() {
        return arusKasDTOList;
    }

    public void setArusKasDTOList(List<ArusKasDTO> arusKasDTOList) {
        this.arusKasDTOList = arusKasDTOList;
    }

    public List<PendapatanDTO> getListPendapatanDokter() {
        return listPendapatanDokter;
    }

    public void setListPendapatanDokter(List<PendapatanDTO> listPendapatanDokter) {
        this.listPendapatanDokter = listPendapatanDokter;
    }

    public BranchBo getBranchBoProxy() {
        return branchBoProxy;
    }

    public List<AkunSettingReportKeuanganKonsol> getListKonsol() {
        return listKonsol;
    }

    public void setListKonsol(List<AkunSettingReportKeuanganKonsol> listKonsol) {
        this.listKonsol = listKonsol;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public SettingReportUserBo getSettingReportUserBoProxy() {
        return settingReportUserBoProxy;
    }

    public void setSettingReportUserBoProxy(SettingReportUserBo settingReportUserBoProxy) {
        this.settingReportUserBoProxy = settingReportUserBoProxy;
    }

    public List<Aging> getMyList() {
        return myList;
    }

    public void setMyList(List<Aging> myList) {
        this.myList = myList;
    }

    public JRBeanCollectionDataSource getDataPrint() {
        return dataPrint;
    }

    public void setDataPrint(JRBeanCollectionDataSource dataPrint) {
        this.dataPrint = dataPrint;
    }

    public String getTipeLaporan() {
        return tipeLaporan;
    }

    public void setTipeLaporan(String tipeLaporan) {
        this.tipeLaporan = tipeLaporan;
    }

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
        logger.info("[LaporanAkuntansiAction.init] start process >>>");
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
        }
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
        String itemId = getId();
        String itemFlag = getFlag();

        LaporanAkuntansi editLaporanAkuntansi = new LaporanAkuntansi();

        if(itemFlag != null){
            try {
                editLaporanAkuntansi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "LaporanAkuntansiBO.getLaporanAkuntansiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[LaporanAkuntansiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[LaporanAkuntansiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editLaporanAkuntansi != null) {
                setLaporanAkuntansi(editLaporanAkuntansi);
            } else {
                editLaporanAkuntansi.setFlag(itemFlag);
                editLaporanAkuntansi.setLaporanAkuntansiId(itemId);
                setLaporanAkuntansi(editLaporanAkuntansi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editLaporanAkuntansi.setLaporanAkuntansiId(itemId);
            editLaporanAkuntansi.setFlag(getFlag());
            setLaporanAkuntansi(editLaporanAkuntansi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[LaporanAkuntansiAction.delete] end process <<<");
        String itemId = getId();
        String itemFlag = getFlag();
        LaporanAkuntansi deleteLaporanAkuntansi = new LaporanAkuntansi();

        if (itemFlag != null ) {

            try {
                deleteLaporanAkuntansi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "LaporanAkuntansiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[LaporanAkuntansiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[LaporanAkuntansiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteLaporanAkuntansi != null) {
                setLaporanAkuntansi(deleteLaporanAkuntansi);

            } else {
                deleteLaporanAkuntansi.setLaporanAkuntansiId(itemId);
                deleteLaporanAkuntansi.setFlag(itemFlag);
                setLaporanAkuntansi(deleteLaporanAkuntansi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteLaporanAkuntansi.setLaporanAkuntansiId(itemId);
            deleteLaporanAkuntansi.setFlag(itemFlag);
            setLaporanAkuntansi(deleteLaporanAkuntansi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[LaporanAkuntansiAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        LaporanAkuntansi deleteLaporanAkuntansi = new LaporanAkuntansi();

        if (itemFlag != null ) {
            try {
                deleteLaporanAkuntansi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "LaporanAkuntansiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[LaporanAkuntansiAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[LaporanAkuntansiAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteLaporanAkuntansi != null) {
                setLaporanAkuntansi(deleteLaporanAkuntansi);

            } else {
                deleteLaporanAkuntansi.setLaporanAkuntansiId(itemId);
                deleteLaporanAkuntansi.setFlag(itemFlag);
                setLaporanAkuntansi(deleteLaporanAkuntansi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteLaporanAkuntansi.setLaporanAkuntansiId(itemId);
            deleteLaporanAkuntansi.setFlag(itemFlag);
            setLaporanAkuntansi(deleteLaporanAkuntansi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[LaporanAkuntansiAction.view] end process <<<");
        return "init_view";
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
        List<LaporanAkuntansi> finalList = new ArrayList();

        LaporanAkuntansi searchLaporanAkuntansi = new LaporanAkuntansi();
        searchLaporanAkuntansi.setFlag("Y");
        SettingReportUser searchSettingReportUser = new SettingReportUser();
        searchSettingReportUser.setFlag("Y");
        searchSettingReportUser.setUserId(CommonUtil.userIdLogin());
        List<LaporanAkuntansi> listOfSearchLaporanAkuntansi = new ArrayList();
        List<SettingReportUser> settingReportUserList = new ArrayList<>();
        try {
            listOfSearchLaporanAkuntansi = laporanAkuntansiBoProxy.getByCriteria(searchLaporanAkuntansi);
            settingReportUserList = settingReportUserBoProxy.getByCriteria(searchSettingReportUser);
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

        for (LaporanAkuntansi laporanAkuntansi : listOfSearchLaporanAkuntansi){
            for (SettingReportUser settingReportUser : settingReportUserList){
                if (laporanAkuntansi.getLaporanAkuntansiId().equalsIgnoreCase(settingReportUser.getReportId())){
                    finalList.add(laporanAkuntansi);
                    break;
                }
            }
        }
        listComboLaporanAkuntansi.addAll(finalList);
        return SUCCESS;
    }

    public String searchSelectReport() {
        logger.info("[LaporanAkuntansiAction.searchSelectReport] start process >>>");

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
                logger.error("[PositionAction.searchSelectReport] Error when saving error,", e1);
            }
            logger.error("[LaporanAkuntansiAction.searchSelectReport] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
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

    public String initForm2() {
        logger.info("[LaporanAkuntansiAction.initForm2] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[LaporanAkuntansiAction.initForm2] end process >>>");
        return "input2";
    }

    public String searchReportNeracaSaldo() {
        logger.info("[LaporanAkuntansiAction.searchReportNeracaSaldo] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_neraca_saldo";
    }

    public String searchReportNeracaMutasi() {
        logger.info("[LaporanAkuntansiAction.searchReportNeracaMutasi] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_neraca_mutasi";
    }

    public String searchReportIkhtisarBukuBesar() {
        logger.info("[LaporanAkuntansiAction.searchReportIkhitisarBukuBesar] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_ikhitisar_buku_besar";
    }
    public String searchReportArusKas() {
        logger.info("[LaporanAkuntansiAction.searchReportArusKas] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_arus_kas";
    }
    public String searchReportKartuBukuBesar() {
        logger.info("[LaporanAkuntansiAction.searchReportKartuBukuBesar] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_kartu_buku_besar";
    }

    public String searchReportMutasiJurnal() {
        logger.info("[LaporanAkuntansiAction.searchReportMutasiJurnal] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_mutasi_jurnal";
    }
    public String searchClosingKasir() {
        logger.info("[LaporanAkuntansiAction.searchReportMutasiJurnal] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_closing_kasir";
    }
    public String searchReportIkhtisarPendapatan() {
        logger.info("[LaporanAkuntansiAction.searchReportIkhtisarPendapatan] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_ikhtisar_pendapatan";
    }

    public String searchReportPersediaan() {
        logger.info("[LaporanAkuntansiAction.searchReportPersediaan] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_persediaan";
    }

    public String searchReportBiaya() {
        logger.info("[LaporanAkuntansiAction.searchReportBiaya] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_biaya";
    }
    public String searchReportBudgetting() {
        logger.info("[LaporanAkuntansiAction.searchReportBudgetting] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_budgetting";
    }
    public String searchReportIkhtisar() {
        logger.info("[LaporanAkuntansiAction.searchReportIkhtisarSubBukuBesar] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_ikhitisar_sub_buku_besar";
    }

    public String searchReportAging() {
        logger.info("[LaporanAkuntansiAction.searchReportAging] start process >>>");
        String branchId = CommonUtil.userBranchLogin();
        LaporanAkuntansi data = new LaporanAkuntansi();
        if (branchId!=null){
            data.setUnit(branchId);
        }else{
            data.setUnit("");
        }
        laporanAkuntansi=data;
        return "search_laporan_aging";
    }

    public String printReportNeracaSaldo(){
        logger.info("[LaporanAkuntansiAction.printReportNeracaSaldo] start process >>>");
        String reportId="RPT01";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String unit = "";
        if (("All").equalsIgnoreCase(data.getUnit())){
            List<Branch> branchList = new ArrayList<>();
            branchList = branchBo.getAll();
            int i = 1;
            for (Branch dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
            branch.setBranchName("Semua");
        }else{
            unit="'"+data.getUnit()+"'";
        }

        listNeracaSaldo = laporanAkuntansiBo.getListNeracaSaldo(reportId,data.getBulan()+"-"+data.getTahun(),unit);

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("judulGm", dataAtasan.getJudulGeneralManager());
        reportParams.put("judulManagerKeu", dataAtasan.getJudulManagerKeuangan());
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportNeracaSaldo");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportNeracaSaldo] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportNeracaSaldo] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportNeracaSaldo] end process <<<");
        return "print_report_akuntansi_neraca_saldo";
    }
    public String printReportNeracaMutasi(){
        logger.info("[LaporanAkuntansiAction.printReportNeracaMutasi] start process >>>");
        String reportId="RPT02";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String unit = "";
        if (("All").equalsIgnoreCase(data.getUnit())){
            List<Branch> branchList = new ArrayList<>();
            branchList = branchBo.getAll();
            int i = 1;
            for (Branch dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
            branch.setBranchName("Semua");
        }else{
            unit="'"+data.getUnit()+"'";
        }

        listNeracaSaldo = laporanAkuntansiBo.getListNeracaMutasi(reportId,data.getBulan()+"-"+data.getTahun(),unit);

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("judulGm", dataAtasan.getJudulGeneralManager());
        reportParams.put("judulManagerKeu", dataAtasan.getJudulManagerKeuangan());
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportNeracaMutasi");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportNeracaMutasi] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportNeracaMutasi] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportNeracaMutasi] end process <<<");
        return "print_report_akuntansi_neraca_mutasi";
    }

    public String printReportIkhtisarBukuBesar(){
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarBukuBesar] start process >>>");
        String reportId ="RPT03";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String unit = "";
        if (("All").equalsIgnoreCase(data.getUnit())){
            List<Branch> branchList = new ArrayList<>();
            branchList = branchBo.getAll();
            int i = 1;
            for (Branch dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
            branch.setBranchName("Semua");
        }else{
            unit="'"+data.getUnit()+"'";
        }

        listIkhtisarBukuBesar = laporanAkuntansiBo.getListIkhitisarBukuBesar(reportId,data.getBulan()+"-"+data.getTahun(),unit);

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("judulGm", dataAtasan.getJudulGeneralManager());
        reportParams.put("judulManagerKeu", dataAtasan.getJudulManagerKeuangan());
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportNeracaSaldo");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportNeracaSaldo] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportNeracaSaldo] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarBukuBesar] end process <<<");
        return "print_report_akuntansi_ikhitisar_buku_besar";
    }

    public String printReportKartuBukuBesar(){
        logger.info("[LaporanAkuntansiAction.printReportKartuBukuBesar] start process >>>");
        String reportId="RPT10";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String unit = "";
        if (("All").equalsIgnoreCase(data.getUnit())){
            List<Branch> branchList = new ArrayList<>();
            branchList = branchBo.getAll();
            int i = 1;
            for (Branch dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
            branch.setBranchName("Semua");
        }else{
            unit="'"+data.getUnit()+"'";
        }

        java.sql.Date tanggalAwal= new java.sql.Date(new Date().getTime());
        java.sql.Date tanggalAkhir= new java.sql.Date(new Date().getTime());

        if (data.getStTanggalAwal()!=null){
            if (!data.getStTanggalAwal().equalsIgnoreCase("")){
                tanggalAwal = CommonUtil.convertStringToDate(data.getStTanggalAwal());
            }
        }
        if (data.getStTanggalAkhir()!=null){
            if (!data.getStTanggalAkhir().equalsIgnoreCase("")){
                tanggalAkhir = CommonUtil.convertStringToDate(data.getStTanggalAkhir());
            }
        }

        listBukuBesar = laporanAkuntansiBo.getListKartuBukuBesar(reportId,data.getBulan()+"-"+data.getTahun(),unit,data.getKodeRekening(),data.getMasterId(),data.getTipeTanggal(),tanggalAwal,tanggalAkhir);
        BigDecimal saldoAwal ;

        if ("P".equalsIgnoreCase(data.getTipeTanggal())){
            saldoAwal= laporanAkuntansiBo.saldoAwalKodeRekening(unit,data.getKodeRekening(),data.getMasterId(),data.getBulan()+"-"+data.getTahun());
        }else{
            String[] tanggal = data.getStTanggalAwal().split("-");
            String periode = tanggal[1]+"-"+tanggal[2];

            saldoAwal= laporanAkuntansiBo.saldoAwalKodeRekeningByTanggal(unit,data.getKodeRekening(),data.getMasterId(),periode,data.getStTanggalAwal());
        }

        BigDecimal saldoAkhir = saldoAwal;
        String posisiSaldoAwal ="";
        String posisiSaldoAkhir ="";

        if (listBukuBesar.size()>0){
            for (KartuBukuBesarPerBukuBantuDTO dataKartu : listBukuBesar){
                BigDecimal saldo = dataKartu.getJumlahdebit().subtract(dataKartu.getJumlahkredit());
                saldoAkhir = saldoAkhir.add(saldo);
            }
        }

        if (saldoAwal.compareTo(BigDecimal.ZERO)<0){
            posisiSaldoAwal="K";
        }else{
            posisiSaldoAwal="D";
        }
        if (saldoAkhir.compareTo(BigDecimal.ZERO)<0){
            posisiSaldoAkhir="K";
        }else{
            posisiSaldoAkhir="D";
        }

        //jika daftar kartu buku besar kosong tetapi saldo ada maka tetep dicetak
        if (listBukuBesar.size()==0){
            if (saldoAwal.compareTo(BigDecimal.ZERO)<0||saldoAwal.compareTo(BigDecimal.ZERO)>0){
                KartuBukuBesarPerBukuBantuDTO newData = new KartuBukuBesarPerBukuBantuDTO();
                newData.setJumlahdebit(BigDecimal.ZERO);
                newData.setJumlahkredit(BigDecimal.ZERO);
                listBukuBesar.add(newData);
            }
        }

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        Date now = new Date();
        if ("P".equalsIgnoreCase(data.getTipeTanggal())){
            reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        }else{
            reportParams.put("periodeTitle", data.getStTanggalAwal()+" s/d "+data.getStTanggalAkhir());
        }
        reportParams.put("saldoAwal",saldoAwal);
        reportParams.put("posisiSaldoAwal",posisiSaldoAwal);
        reportParams.put("saldoAkhir",saldoAkhir);
        reportParams.put("posisiSaldoAkhir",posisiSaldoAkhir);
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("judulGm", dataAtasan.getJudulGeneralManager());
        reportParams.put("judulManagerKeu", dataAtasan.getJudulManagerKeuangan());
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("kode_rekening",data.getKodeRekening());
        reportParams.put("nomor_master",data.getMasterId());
        reportParams.put("nama_kode_rekening",data.getNamaKodeRekening());
        reportParams.put("nama_master",data.getNamaMaster());
        reportParams.put("branch_id",data.getUnit());
        reportParams.put("rekening_id",data.getRekeningId());
        reportParams.put("reportTitle", "KARTU BUKU BESAR PER BUKU BANTU");
        reportParams.put("areaId", CommonUtil.userAreaName());

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportKartuBukuBesar");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportKartuBukuBesar] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportKartuBukuBesar] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportKartuBukuBesar] end process <<<");
//        if (("T").equalsIgnoreCase(data.getTipeTanggal())){
//            java.sql.Date tanggalAwal = CommonUtil.convertStringToDate(data.getStTanggalAwal());
//            java.sql.Date tanggalAkhir = CommonUtil.convertStringToDate(data.getStTanggalAkhir());
//
//            String[] stTanggalAwalBulan = data.getStTanggalAwal().split("-");
//
//            Calendar c = Calendar.getInstance();
//            c.setTime(tanggalAkhir);
//            c.add(Calendar.DATE, 1);
//
//            java.sql.Date tanggalAkhirBaru = new java.sql.Date(c.getTimeInMillis());
//
//            reportParams.put("tanggalAwal",tanggalAwal );
//            reportParams.put("tanggalAwalBulan",CommonUtil.convertStringToDate2(stTanggalAwalBulan[2]+"-"+stTanggalAwalBulan[1]+"-01" ));
//            reportParams.put("periode",stTanggalAwalBulan[1]+"-"+stTanggalAwalBulan[2] );
//            reportParams.put("tanggalAkhir", tanggalAkhirBaru);
//            reportParams.put("periodeTitle", data.getStTanggalAwal()+" s/d "+data.getStTanggalAkhir());
//            if (!("").equalsIgnoreCase(data.getMasterId())){
//                return "print_report_akuntansi_kartu_buku_besar_tanggal";
//            }else{
//                return "print_report_akuntansi_kartu_buku_besar_tanggal_tanpa_master";
//            }
//        }else if (("P").equalsIgnoreCase(data.getTipeTanggal())){
//            reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
//            reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
//            reportParams.put("SUBREPORT_DIR", "C:\\tomcat\\webapps\\go-medsys\\pages\\report\\com\\neurix\\akuntansi\\");
//            if (!("").equalsIgnoreCase(data.getMasterId())){
//                return "print_report_akuntansi_kartu_buku_besar";
//            }else{
//                return "print_report_akuntansi_kartu_buku_besar_tanpa_master";
//            }
//        }else{
//            return ERROR;
//        }
        return "print_report_akuntansi_kartu_buku_besar";
    }

    public String printReportMutasiJurnal(){
        logger.info("[LaporanAkuntansiAction.printReportMutasiJurnal] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        TipeJurnalBo tipeJurnalBo= (TipeJurnalBo) ctx.getBean("tipeJurnalBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        TipeJurnal tipeJurnal= tipeJurnalBo.getTipeJurnalById(data.getTipeJurnalId());
        String titleReport="";
        String unit = "";
        String result ="";
        if (("All").equalsIgnoreCase(data.getUnit())){
            List<Branch> branchList = new ArrayList<>();
            branchList = branchBo.getAll();
            int i = 1;
            for (Branch dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
            branch.setBranchName("Semua");
        }else{
            unit="'"+data.getUnit()+"'";
        }

        if (("M").equalsIgnoreCase(data.getTipeLaporan())){
            result = "print_report_mutasi_jurnal";
            titleReport="MUTASI "+tipeJurnal.getTipeJurnalName().toUpperCase();
        }else if (("da").equalsIgnoreCase(data.getTipeLaporan())){
            titleReport="MUTASI DETAIL ACTIVITY "+tipeJurnal.getTipeJurnalName().toUpperCase();
            result = "print_report_mutasi_jurnal_activity";
        }

        reportParams.put("reportTitle", titleReport);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("jurnalType",data.getTipeJurnalId());
        reportParams.put("areaId",CommonUtil.userAreaName());
//        System.out.println(CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportMutasiJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportMutasiJurnal] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportMutasiJurnal] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportMutasiJurnal] end process <<<");
        return result;
    }

    public String printReportClosingKasir(){
        logger.info("[LaporanAkuntansiAction.printReportClosingKasir] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        Biodata biodata = biodataBo.getBiodataByNip(data.getNip());
        String titleReport="LAPORAN CLOSING KASIR TUNAI";
        java.sql.Date tglFrom = CommonUtil.convertStringToDate(data.getStTanggalAwal());
        java.sql.Date tglTo = CommonUtil.convertStringToDate(data.getStTanggalAkhir());
        String kodeRekeningKas = laporanAkuntansiBo.getKodeRekeningkas();
        Date now = new Date();

        reportParams.put("reportTitle", titleReport);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("periodeTitle", data.getStTanggalAwal() +" s/d "+data.getStTanggalAkhir());
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("jurnalType",data.getTipeJurnalId());
        reportParams.put("areaId",CommonUtil.userAreaName());
        reportParams.put("kasirName",biodata.getNamaPegawai());
        reportParams.put("kasirNip",data.getNip());
        reportParams.put("tglFrom",tglFrom);
        reportParams.put("tglTo",tglTo);
        reportParams.put("kodeRekeningKas",kodeRekeningKas);
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportMutasiJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportMutasiJurnal] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportMutasiJurnal] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportClosingKasir] end process <<<");
        return "print_report_closing_kasir";
    }

    public String printReportIkhtisarSubBukuBesar(){
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarSubBukuBesar] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String titleReport="";
        String reportId="RPT04";
        String report="";
        String unit = "";
        if (("All").equalsIgnoreCase(data.getUnit())){
            List<Branch> branchList = new ArrayList<>();
            branchList = branchBo.getAll();
            int i = 1;
            for (Branch dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
            branch.setBranchName("Semua");
        }else{
            unit="'"+data.getUnit()+"'";
        }

        switch (data.getTipeLaporan()){
            case ("hutang_usaha"):
                titleReport="IKHTISAR HUTANG USAHA";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar";
                break;
            case("piutang_usaha"):
                titleReport="IKHTISAR PIUTANG USAHA";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar";
                break;
            case ("uang_muka"):
                titleReport="IKHTISAR UANG MUKA";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar";
                break;
            case ("uang_muka_p"):
                titleReport="IKHTISAR UANG MUKA PASIEN";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar_pasien";
                break;
            case ("piutang_pasien"):
                titleReport="IKHTISAR PIUTANG PASIEN";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar_pasien";
                break;
            case ("persediaan"):
                titleReport="IKHTISAR PERSEDIAAN";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar_persediaan";
                break;
                default:
                    titleReport="";
        }

        reportParams.put("reportTitle", titleReport);
        reportParams.put("reportId", reportId);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("judulGm", dataAtasan.getJudulGeneralManager());
        reportParams.put("judulManagerKeu", dataAtasan.getJudulManagerKeuangan());
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("tipeLaporan",data.getTipeLaporan());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportIkhtisarSubBukuBesar");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportIkhtisarSubBukuBesar] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportIkhtisarSubBukuBesar] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarSubBukuBesar] end process <<<");
        return report;
    }

    public String printReportIkhtisarPendapatan(){
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarPendapatan] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String titleReport="";
        String reportId="RPT12";
        String unit = "";
        if (("All").equalsIgnoreCase(data.getUnit())){
            List<Branch> branchList = new ArrayList<>();
            branchList = branchBo.getAll();
            int i = 1;
            for (Branch dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
            branch.setBranchName("Semua");
        }else{
            unit="'"+data.getUnit()+"'";
        }

        String result="";

        switch (data.getTipeLaporan()){
            case "PD":
                titleReport="LAPORAN PENDAPATAN";
                result="print_report_akuntansi_ikhitisar_pendapatan";
                break;
            case "PDM":
                titleReport="LAPORAN PENDAPATAN PER MASTER";
                result="print_report_akuntansi_ikhitisar_pendapatan_master";
                break;
            case "PDDOK":
                titleReport="LAPORAN PENDAPATAN PER DOKTER PER TINDAKAN";
                result="print_report_akuntansi_ikhitisar_pendapatan_dokter";
                listPendapatanDokter = laporanAkuntansiBo.getPendapatan(reportId,unit,data.getBulan()+"-"+data.getTahun(),"PDDOK");
                break;
            case "PDA":
                titleReport="LAPORAN PENDAPATAN PER ACTIVITY";
                result="print_report_akuntansi_ikhitisar_pendapatan_activity";
                listPendapatanDokter = laporanAkuntansiBo.getPendapatan(reportId,unit,data.getBulan()+"-"+data.getTahun(),"PDA");
                break;
            case "PDD":
                titleReport="LAPORAN PENDAPATAN PER DIVISI";
                result="print_report_akuntansi_ikhitisar_pendapatan_divisi";
                break;
        }

        reportParams.put("reportTitle", titleReport);
        reportParams.put("reportId", reportId);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportIkhtisarPendapatan");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportIkhtisarPendapatan] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportIkhtisarPendapatan] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarPendapatan] end process <<<");
        return result;
    }

    public String printReportBiaya(){
        logger.info("[LaporanAkuntansiAction.printReportBiaya] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String titleReport="";
        String reportId="RPT23";
        String unit = "";
        if (("All").equalsIgnoreCase(data.getUnit())){
            List<Branch> branchList = new ArrayList<>();
            branchList = branchBo.getAll();
            int i = 1;
            for (Branch dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
            branch.setBranchName("Semua");
        }else{
            unit="'"+data.getUnit()+"'";
        }

        String result="";

        switch (data.getTipeLaporan()){
            case "B":
                titleReport="LAPORAN BIAYA";
                result="print_report_akuntansi_biaya";
                break;
            case "BD":
                titleReport="LAPORAN BIAYA PER DIVISI";
                result="print_report_akuntansi_biaya_divisi";
                break;
        }

        reportParams.put("reportTitle", titleReport);
        reportParams.put("reportId", reportId);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportBiaya");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportBiaya] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportBiaya] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportBiaya] end process <<<");
        return result;
    }

    public String printReportPersediaan(){
        logger.info("[LaporanAkuntansiAction.printReportPersediaan] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String titleReport="";
        String unit = "";
        if (("All").equalsIgnoreCase(data.getUnit())){
            List<Branch> branchList = new ArrayList<>();
            branchList = branchBo.getAll();
            int i = 1;
            for (Branch dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
            branch.setBranchName("Semua");
        }else{
            unit="'"+data.getUnit()+"'";
        }

        String result="";

        switch (data.getTipeLaporan()){
            case "B":
                titleReport="LAPORAN BIAYA";
                result="print_report_akuntansi_biaya";
                break;
            case "BD":
                titleReport="LAPORAN BIAYA PER DIVISI";
                result="print_report_akuntansi_biaya_divisi";
                break;
        }

        reportParams.put("reportTitle", titleReport);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportPersediaan");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportPersediaan] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportPersediaan] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportPersediaan] end process <<<");
        return result;
    }

    public String printReportBudgetting(){
        logger.info("[LaporanAkuntansiAction.printReportBudgetting] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String titleReport="";
        String unit = "'"+data.getUnit()+"'";
        String result="";

        switch (data.getTipeLaporan()){
            case "B":
                titleReport="LAPORAN BUDGETTING";
                result="print_report_budgetting";
                break;
            case "BPS":
                titleReport="LAPORAN BUDGETTING PER SEMESTER";
                result="print_report_budgetting_per_semester";
                break;
            case "BPQ":
                titleReport="LAPORAN BUDGETTING PER QUARTAL";
                result="print_report_budgetting_per_quartal";
                break;
            case "BPB":
                titleReport="LAPORAN BUDGETTING PER BULAN";
                result="print_report_budgetting_per_bulan";
                break;
            case "BCR":
                titleReport="LAPORAN BUDGETTING KOMPARASI REALISASI";
                result="print_report_budgetting_komparasi_realisasi";
                break;
            case "BPD":
                titleReport="LAPORAN BUDGETTING PER DIVISI";
                result="print_report_budgetting_per_divisi";
                break;
            case "BCPT":
                titleReport="LAPORAN BUDGETTING KOMPARASI PER TAHUN";
                result="print_report_budgetting_komparasi_tahun";
                reportParams.put("tahunIni", data.getTahun());
                reportParams.put("tahunLalu", String.valueOf(Integer.valueOf(data.getTahun())-1));
                reportParams.put("tahun2Lalu", String.valueOf(Integer.valueOf(data.getTahun())-2));
                break;
        }
        budgettingDTOList = laporanAkuntansiBo.getBudgetting(data.getTipeLaporan(),data.getUnit(),data.getTahun());

        reportParams.put("reportTitle", titleReport);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", unit);
        reportParams.put("periodeTitle", data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportBudgetting");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportBudgetting] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportBudgetting] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportBudgetting] end process <<<");
        return result;
    }

    public String printReportAging(){
        logger.info("[LaporanAkuntansiAction.printReportAging] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi dataLaporan = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(dataLaporan.getUnit());
        Branch branch = branchBo.getBranchById(dataLaporan.getUnit(),"Y");
        String periode =dataLaporan.getBulan()+"-"+dataLaporan.getTahun();
        String titleReport="";
        String reportId="RPT07";
        String tipeAging ="";
        String unit = "";
//        if ((CommonConstant.ID_KANPUS).equalsIgnoreCase(dataLaporan.getUnit())){
//            List<Branch> branchList = new ArrayList<>();
//            branchList = branchBo.getAll();
//            int i = 1;
//            for (Branch dataUnit : branchList){
//                if (i==1){
//                    unit="'"+dataUnit.getBranchId()+"'";
//                }else{
//                    unit=unit+",'"+dataUnit.getBranchId()+"'";
//                }
//                i++;
//            }
//            branch.setLogoName(CommonConstant.IMAGE_LOGO_KP);
//            branch.setBranchName("Semua");
//        }else{
//            unit="'"+dataLaporan.getUnit()+"'";
//        }
        unit="'"+dataLaporan.getUnit()+"'";

        switch (dataLaporan.getTipeLaporan()){
            case("hutang_usaha") :
                titleReport="AGING HUTANG USAHA";
                tipeAging ="usaha";
                break;
            case ("piutang_usaha"):
                titleReport="AGING PIUTANG USAHA";
                tipeAging ="usaha";
                break;
            case ("uang_muka"):
                titleReport="AGING UANG MUKA";
                tipeAging ="usaha";
                break;
            case ("uang_muka_p"):
                titleReport="AGING UANG MUKA PASIEN";
                tipeAging ="pasien";
                break;
            case ("piutang_pasien"):
                titleReport="AGING PIUTANG PASIEN";
                tipeAging ="pasien";
                break;
            case ("pph_dokter_kso"):
                titleReport="AGING PPH21 DOKTER KSO";
                tipeAging ="dokter";
                break;
            case ("pph_pegawai"):
                titleReport="AGING PPH21 PEGAWAI";
                tipeAging ="pegawai";
                break;
            case ("pph_rekanan"):
                titleReport="AGING REKANAN";
                tipeAging ="usaha";
                break;
        }

        String periodeAging = dataLaporan.getTahun()+"-"+dataLaporan.getBulan();
        List<Aging> agingList = laporanAkuntansiBo.getAging(unit,periodeAging,dataLaporan.getMasterId(),tipeAging,reportId,dataLaporan.getTipeLaporan());
        List<Aging> listOfAgingTemp = new ArrayList<>();
        if (agingList.size()==0){
            return "print_report_akuntansi_aging";
        }
        String []jumlahTanggalTahunKabisat = {"","31","29","31","30","31","30","31","31","30","31","30","31"};
        String []jumlahTanggalTahunBiasa = {"","31","28","31","30","31","30","31","31","30","31","30","31"};
        String jumlahTanggalTemp="";

        String periodeTitle[] = periode.split("-");

        if(Integer.parseInt(periodeTitle[0]) % 4 == 0){
            jumlahTanggalTemp=jumlahTanggalTahunKabisat[Integer.parseInt(periodeTitle[0])];
        }else{
            jumlahTanggalTemp=jumlahTanggalTahunBiasa[Integer.parseInt(periodeTitle[0])];
        }

        if(agingList.size()>0){
            Aging listAgingTempLast=null;
            Aging listAgingTempNewRecord=null;
            listOfAgingTemp=new ArrayList();

            for (Aging objMod : agingList){
                if(cekIsObjectIsDoubel(objMod,listOfAgingTemp)){
                    listAgingTempLast=getSameItem(objMod,listOfAgingTemp);

                    //Cache baru dibuka untuk menampung data aging yang baru
                    listAgingTempNewRecord = new Aging();
                    listAgingTempNewRecord.setNoNota(listAgingTempLast.getNoNota());
                    listAgingTempNewRecord.setTglJurnal(listAgingTempLast.getTglJurnal());
                    listAgingTempNewRecord.setKurs(objMod.getKurs());
                    listAgingTempNewRecord.setMataUang(listAgingTempLast.getMataUang());
                    //
                    listAgingTempNewRecord.setTotal(objMod.getTotal().add(listAgingTempLast.getTotal()));
                    listAgingTempNewRecord.setKodeRekening(objMod.getKodeRekening());
                    listAgingTempNewRecord.setNamaRekening(objMod.getNamaRekening());
                    listAgingTempNewRecord.setMasterId(listAgingTempLast.getMasterId());
                    listAgingTempNewRecord.setNamaMaster(listAgingTempLast.getNamaMaster());
                    listAgingTempNewRecord.setPeriode(getPeriode());

                    //hapus record yang lama
                    listOfAgingTemp.remove(listAgingTempLast);

                    //tambahkan record yang baru
                    listOfAgingTemp.add(listAgingTempNewRecord);
                }else{
                    listOfAgingTemp.add(objMod);
                }
            }
        }else{
            return "print_report_akuntansi_aging";
        }

        if(listOfAgingTemp.size()>0){
            myList=new ArrayList();
            for(Aging data:listOfAgingTemp){
                data.setTotal(data.getTotal().abs());
                data.setJumlah1(new BigDecimal(0));
                data.setJumlah2(new BigDecimal(0));
                data.setJumlah3(new BigDecimal(0));
                data.setJumlah4(new BigDecimal(0));
                data.setJumlah5(new BigDecimal(0));
                data.setJumlah6(new BigDecimal(0));
                data.setJumlah7(new BigDecimal(0));

                //Tentukan posisi umur aging.
                //Yaitu dengan mengammbil periode sedang berjalan (tanggal aging di cetak) dan periode tanggal dari jurnal tersebut
                int agingPosition=0;
                String periodeJurnal =CommonUtil.convertDateToString(data.getTglJurnal());
                String periodeJurnalArrTemp[]= periodeJurnal.split("-");

                Integer tglCetak = Integer.parseInt(jumlahTanggalTemp);
                Integer tglJurnal =Integer.parseInt(periodeJurnalArrTemp[0]);
                Integer blnCetak = Integer.parseInt(periodeTitle[0]);
                Integer blnJurnal =Integer.parseInt(periodeJurnalArrTemp[1]);
                Integer thnCetak = Integer.parseInt(periodeTitle[1]);
                Integer thnJurnal =Integer.parseInt(periodeJurnalArrTemp[2]);

                if(thnCetak > thnJurnal){
                    agingPosition=(((thnCetak-thnJurnal)*12)-blnJurnal)+blnCetak;
                    if(tglCetak > tglJurnal) agingPosition+=1;
                }else{
                    agingPosition=blnCetak-blnJurnal;
                    if(tglCetak > tglJurnal) agingPosition+=1;
                }

                //umur 0 s/d 1 bulan
                if(agingPosition<=1){
                    data.setJumlah1(data.getTotal().abs());
                }
                //umur 1 s/d 2 bulan
                else if(agingPosition<=2 && agingPosition>1){
                    data.setJumlah2(data.getTotal().abs());
                }
                //umur 2 s/d 3 bulan
                else if(agingPosition<=3 && agingPosition>2){
                    data.setJumlah3(data.getTotal().abs());
                }
                //umur 3 s/d 6 bulan
                else if(agingPosition<=6 && agingPosition>3){
                    data.setJumlah4(data.getTotal().abs());
                }
                //umur 6 s/d 12 bulan
                else if(agingPosition<=12 && agingPosition>6){
                    data.setJumlah5(data.getTotal().abs());
                }
                //umur 1 s/d 2 tahun
                else if(agingPosition<=24 && agingPosition>12){
                    data.setJumlah6(data.getTotal().abs());
                }
                //umur > 2 tahun
                else{
                    data.setJumlah7(data.getTotal().abs());
                }

                data.setStrKurs(getMataUangKurs("033"));
                if(data.getTotal().doubleValue()!=0){
                    myList.add(data);
                }
            }
        }else{
            return "print_report_akuntansi_aging";
        }

        //JIka list yang akan dicetak di report tidak ada, maka tidak usah dilanjutkan ke pencetakan report
        if(myList.size()>0){
            List<Aging> forReport = new ArrayList<>();

//            myList.addAll(forReport);
            reportParams.put("reportTitle", titleReport);
            reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
            reportParams.put("cabangId", dataLaporan.getUnit());
            reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(dataLaporan.getBulan())+" "+dataLaporan.getTahun());
            Date now = new Date();
            reportParams.put("tanggal", CommonUtil.convertDateToString(now));
            reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
            reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
            reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
            reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
            reportParams.put("periode",periode);
            reportParams.put("kota",branch.getBranchName());
            reportParams.put("alamatSurat",branch.getAlamatSurat());
            reportParams.put("areaId",CommonUtil.userAreaName());
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportAging");
                } catch (GeneralBOException e1) {
                    logger.error("[LaporanAkuntansiAction.printReportAging] Error when downloading ,", e1);
                }
                logger.error("[LaporanAkuntansiAction.printReportAging] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }
            logger.info("[LaporanAkuntansiAction.printReportAging] end process <<<");
            return "print_report_akuntansi_aging";
        }else{
            return "print_report_akuntansi_aging";
        }
    }

    public boolean cekIsObjectIsDoubel(Aging data, List<Aging> listData){
        //cek ada yg no nota sama ato tidak, jika ada yg sama return true, else false
        boolean flag = false;
        Aging aging=null;
        for (int i = 0; i < listData.size() && !flag; i++){
            aging = listData.get(i);
            if (aging.getNoNota().equalsIgnoreCase(data.getNoNota()) && data.getMataUang().equalsIgnoreCase(aging.getMataUang())&& data.getMasterId().equalsIgnoreCase(aging.getMasterId())){
                flag = true;
            }
        }
        return flag;
    }

    private Aging getSameItem(Aging record, List<Aging> listData){
        boolean flag = false;
        Aging dataReturn = null;
        for (int i = 0; i < listData.size() && !flag; i++){
            dataReturn = listData.get(i);
            if (dataReturn.getNoNota().equalsIgnoreCase(record.getNoNota())&& dataReturn.getMataUang().equalsIgnoreCase(record.getMataUang())&&dataReturn.getMasterId().equalsIgnoreCase(record.getMasterId())){
                flag = true;
            }
        }
        return dataReturn;
    }

    public String saveAdd(){
        logger.info("[LaporanAkuntansiAction.saveAdd] start process >>>");

        try {
            LaporanAkuntansi laporanAkuntansi = getLaporanAkuntansi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            laporanAkuntansi.setCreatedWho(userLogin);
            laporanAkuntansi.setLastUpdate(updateTime);
            laporanAkuntansi.setCreatedDate(updateTime);
            laporanAkuntansi.setLastUpdateWho(userLogin);
            laporanAkuntansi.setAction("C");
            laporanAkuntansi.setFlag("Y");

            laporanAkuntansiBoProxy.saveAdd(laporanAkuntansi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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

    public String saveEdit(){
        logger.info("[LaporanAkuntansiAction.saveEdit] start process >>>");
        try {
            LaporanAkuntansi editLaporanAkuntansi = getLaporanAkuntansi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editLaporanAkuntansi.setLastUpdateWho(userLogin);
            editLaporanAkuntansi.setLastUpdate(updateTime);
            editLaporanAkuntansi.setAction("U");
            editLaporanAkuntansi.setFlag("Y");

            laporanAkuntansiBoProxy.saveEdit(editLaporanAkuntansi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "LaporanAkuntansiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LaporanAkuntansiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[LaporanAkuntansiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[LaporanAkuntansiAction.saveDelete] start process >>>");
        try {
            LaporanAkuntansi deleteLaporanAkuntansi = getLaporanAkuntansi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteLaporanAkuntansi.setLastUpdate(updateTime);
            deleteLaporanAkuntansi.setLastUpdateWho(userLogin);
            deleteLaporanAkuntansi.setAction("U");
            deleteLaporanAkuntansi.setFlag("N");

            laporanAkuntansiBoProxy.saveDelete(deleteLaporanAkuntansi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "LaporanAkuntansiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[LaporanAkuntansiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[LaporanAkuntansiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public List<JadwalShiftKerjaDetail> listKasirByBranch(String branchId, String stTglFrom ,String stTglTo){
        logger.info("[LaporanAkuntansiAction.listKasirByBranch] start process >>>");
        List<JadwalShiftKerjaDetail> detailList= new ArrayList<>();
        List<JadwalShiftKerjaDetail> resultFinal= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");
        java.sql.Date tglFrom = CommonUtil.convertStringToDate(stTglFrom);
        java.sql.Date tglTo = CommonUtil.convertStringToDate(stTglTo);
        String profesiId="SB54"; //Bagian Keuangan
        try {
            detailList=jadwalShiftKerjaBo.getJadwalShiftKerjaByUnitAndProfesiAndTanggal(branchId,tglFrom,tglTo,profesiId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "LaporanAkuntansiBO.listKasirByBranch");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.listKasirByBranch] Error when saving error,", e1);
            }
            logger.error("[LaporanAkuntansiAction.listKasirByBranch] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
        }

        Comparator<JadwalShiftKerjaDetail> comparator = new Comparator<JadwalShiftKerjaDetail>() {
            @Override
            public int compare(JadwalShiftKerjaDetail left, JadwalShiftKerjaDetail right) {
                Long angka1 = Long.parseLong(left.getNip());
                Long angka2 = Long.parseLong(right.getNip());
                return (int) (angka1-angka2);
            }
        };

        detailList.sort(comparator);
        String nama="";
        for (JadwalShiftKerjaDetail jadwalShiftKerjaDetail : detailList){
            if (!nama.equalsIgnoreCase(jadwalShiftKerjaDetail.getNamaPegawai())){
                nama=jadwalShiftKerjaDetail.getNamaPegawai();
                resultFinal.add(jadwalShiftKerjaDetail);
            }
        }
        logger.info("[LaporanAkuntansiAction.listKasirByBranch] end process <<<");

        return resultFinal;
    }
    public String getAdaTipeLaporan ( String reportId ){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");

        LaporanAkuntansi laporanAkuntansi = laporanAkuntansiBo.getById(reportId);

        return laporanAkuntansi.getAdaTipeLaporan();
    }

    public List<LaporanAkuntansi> searchTipeLaporan(){
        List<LaporanAkuntansi> laporanAkuntansiList = new ArrayList<>();
        LaporanAkuntansi laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setTipeLaporan("hutang_usaha");
        laporanAkuntansi.setTipeLaporanName("Hutang Usaha");
        laporanAkuntansiList.add(laporanAkuntansi);

        laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setTipeLaporan("piutang_usaha");
        laporanAkuntansi.setTipeLaporanName("Piutang Usaha");
        laporanAkuntansiList.add(laporanAkuntansi);

        laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setTipeLaporan("uang_muka");
        laporanAkuntansi.setTipeLaporanName("Uang Muka");
        laporanAkuntansiList.add(laporanAkuntansi);

        laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setTipeLaporan("piutang_pasien");
        laporanAkuntansi.setTipeLaporanName("Piutang Pasien");
        laporanAkuntansiList.add(laporanAkuntansi);

        laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setTipeLaporan("uang_muka_p");
        laporanAkuntansi.setTipeLaporanName("Uang Muka Pasien");
        laporanAkuntansiList.add(laporanAkuntansi);

        laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setTipeLaporan("persediaan");
        laporanAkuntansi.setTipeLaporanName("Persediaan");
        laporanAkuntansiList.add(laporanAkuntansi);

        laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setTipeLaporan("pph_dokter_kso");
        laporanAkuntansi.setTipeLaporanName("PPH21 Dokter");
        laporanAkuntansiList.add(laporanAkuntansi);

        laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setTipeLaporan("pph_pegawai");
        laporanAkuntansi.setTipeLaporanName("PPH21 Pegawai");
        laporanAkuntansiList.add(laporanAkuntansi);

        laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setTipeLaporan("pph_rekanan");
        laporanAkuntansi.setTipeLaporanName("PPH21 Rekanan");
        laporanAkuntansiList.add(laporanAkuntansi);

        return laporanAkuntansiList;
    }

    public String printLaporanArusKas(){
        logger.info("[LaporanAkuntansiAction.printLaporanArusKas] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String titleReport="";
        String reportId="RPT21";
        String result="";

        titleReport="LAPORAN ARUS KAS";
        result="print_report_arus_kas";
        listArusKas = laporanAkuntansiBo.getArusKas(reportId,data.getUnit(),data.getBulan()+"-"+data.getTahun());

        reportParams.put("reportTitle", titleReport);
        reportParams.put("reportId", reportId);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("periodeTitle", data.getStTanggalAwal());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("periode", data.getStTanggalAwal());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printLaporanArusKas");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printLaporanArusKas] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printLaporanArusKas] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printLaporanArusKas] end process <<<");
        return result;
    }

    public String searchLaporanArusKas(){
        logger.info("[LaporanAkuntansiAction.searchLaporanArusKas] start process >>>");

        String branchId = CommonUtil.userBranchLogin();

        LaporanAkuntansi laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setUnit(branchId);

        setLaporanAkuntansi(laporanAkuntansi);

        logger.info("[LaporanAkuntansiAction.searchLaporanArusKas] end process <<<");
        return "search_arus_kas";
    }
    public String searchReportKompilasi(){
        logger.info("[LaporanAkuntansiAction.searchLaporanKompilasi] start process >>>");

        String branchId = CommonUtil.userBranchLogin();

        LaporanAkuntansi laporanAkuntansi = new LaporanAkuntansi();
        laporanAkuntansi.setUnit(branchId);

        setLaporanAkuntansi(laporanAkuntansi);

        logger.info("[LaporanAkuntansiAction.searchLaporanKompilasi] end process <<<");
        return "search_kompilasi";
    }
    public String printLaporanKompilasi(){
        logger.info("[LaporanAkuntansiAction.printLaporanKompilasi] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        String titleReport="";
        String reportId="RPT24";
        String result="";
        String periode= data.getBulan()+"-"+data.getTahun();
        String tahunLalu = String.valueOf(Integer.parseInt(data.getTahun())-1);
        String duaTahunLalu = String.valueOf(Integer.parseInt(data.getTahun())-2);
        String periodeTitle = CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun();
        String periodeTitleTahunLalu = CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+tahunLalu;
        String periodeTitle2TahunLalu = CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+duaTahunLalu;
        List<String> branchId = new ArrayList<>();

        if (("K").equalsIgnoreCase(data.getTipeLaporan())){
            data.setUnit(CommonConstant.BRANCH_KP);
            titleReport="LAPORAN KEUANGAN KOMPILASI";
            result="print_report_kompilasi_all";

            //mencari semua branch dahulu
            List<Branch> branchList = branchBo.getBranchList();
            int i =0;
            String unit="";
            for (Branch dataUnit : branchList){
                branchId.add(dataUnit.getBranchId());
                reportParams.put("branch_name_"+(i+1), dataUnit.getBranchName());
                if (i==0){
                    unit="'"+dataUnit.getBranchId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getBranchId()+"'";
                }
                i++;
            }
            listKonsol = laporanAkuntansiBo.getLaporanAkuntansiKonsol(periode,branchId.get(0),branchId.get(1),branchId.get(2),branchId.get(3),branchId.get(4),branchId.get(5),branchId.get(6),unit);
        }else{
            titleReport="LAPORAN POSISI KEUANGAN";
            result="print_report_kompilasi";
            listKonsol = laporanAkuntansiBo.getLaporanAkuntansiKonsolUnit(periode,data.getUnit());
        }

        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        Date now = new Date();
        reportParams.put("reportTitle", titleReport);
        reportParams.put("reportId", reportId);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("branchName", branch.getBranchName());
        reportParams.put("periodeTitle",periodeTitle);
        reportParams.put("periodeTitleTahunLalu",periodeTitleTahunLalu);
        reportParams.put("periodeTitle2TahunLalu",periodeTitle2TahunLalu);
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("periode", periode);
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printLaporanKompilasi");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printLaporanKompilasi] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printLaporanKompilasi] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printLaporanKompilasi] end process <<<");
        return result;
    }

    private String getMataUangKurs(String mataUangId) {
        return "0";
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
