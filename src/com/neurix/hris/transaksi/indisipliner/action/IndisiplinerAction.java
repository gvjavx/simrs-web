package com.neurix.hris.transaksi.indisipliner.action;

//import com.neurix.authorization.company.bo.AreaBo;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.cutiPanjang.model.CutiPanjang;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.model.positionBagian;
import com.neurix.hris.master.strukturJabatan.bo.StrukturJabatanBo;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.indisipliner.bo.IndisiplinerBo;
import com.neurix.hris.transaksi.indisipliner.model.Indisipliner;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class IndisiplinerAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(IndisiplinerAction.class);
    private String tglFrom;
    private String tglTo;
    private String branchId;
    private String bagian;
    private String nip;
    private IndisiplinerBo indisiplinerBoProxy;
    private Indisipliner indisipliner;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTglFrom() {
        return tglFrom;
    }

    public void setTglFrom(String tglFrom) {
        this.tglFrom = tglFrom;
    }

    public String getTglTo() {
        return tglTo;
    }

    public void setTglTo(String tglTo) {
        this.tglTo = tglTo;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        IndisiplinerAction.logger = logger;
    }

    public IndisiplinerBo getIndisiplinerBoProxy() {
        return indisiplinerBoProxy;
    }

    public void setIndisiplinerBoProxy(IndisiplinerBo indisiplinerBoProxy) {
        this.indisiplinerBoProxy = indisiplinerBoProxy;
    }

    public Indisipliner getIndisipliner() {
        return indisipliner;
    }

    public void setIndisipliner(Indisipliner indisipliner) {
        this.indisipliner = indisipliner;
    }



    public String paging(){
        return SUCCESS;
    }

    public Indisipliner init(String kode, String flag){
        logger.info("[IndisiplinerAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Indisipliner> listOfResultIndisipliner = (List<Indisipliner>) session.getAttribute("listOfResultIndisipliner");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResultIndisipliner != null){
                for (Indisipliner indisipliner: listOfResultIndisipliner) {
                    if(kode.equalsIgnoreCase(indisipliner.getIndisiplinerId()) && flag.equalsIgnoreCase(indisipliner.getFlag())){
                        setIndisipliner(indisipliner);
                        break;
                    }
                }
            } else {
                setIndisipliner(new Indisipliner());
            }
            logger.info("[IndisiplinerAction.init] end process >>>");
        }
        return getIndisipliner();
    }
    public List<Indisipliner> approveAtasan(String indisiplinerId) {
        logger.info("[IndisiplinerAction.edit] start process >>>");
        String itemFlag = "Y";
        List<Indisipliner> indisiplinerList = new ArrayList<Indisipliner>();
        List<Biodata> personList = new ArrayList<Biodata>();
        Indisipliner editIndisipliner = new Indisipliner();
        editIndisipliner.setIndisiplinerId(indisiplinerId);
        editIndisipliner.setFlag("Y");
        if(itemFlag != null){
            try {
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                IndisiplinerBo indisiplinerBo = (IndisiplinerBo) ctx.getBean("indisiplinerBoProxy");
                indisiplinerList=indisiplinerBo.getByCriteria(editIndisipliner);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "IndisiplinerBO.getSppdByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IndisiplinerAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[IndisiplinerAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            }
        }

        setAddOrEdit(true);
        logger.info("[IndisiplinerAction.edit] end process >>>");
        return indisiplinerList;
    }
    @Override
    public String add() {
        logger.info("[IndisiplinerAction.add] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setIndisipliner(new Indisipliner());
        setAddOrEdit(true);
        setAdd(true);
        session.removeAttribute("listOfResultIndisipliner");

        logger.info("[IndisiplinerAction.add] stop process >>>");
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
        logger.info("[AlatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Indisipliner viewIndisipliner = new Indisipliner();

        if (itemFlag != null ) {

            try {
                viewIndisipliner = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "IndisiplinerAction.view");
                } catch (GeneralBOException e1) {
                    logger.error("[IndisiplinerAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[IndisiplinerAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (viewIndisipliner != null) {
                setIndisipliner(viewIndisipliner);

            } else {
                viewIndisipliner.setIndisiplinerId(itemId);
                viewIndisipliner.setFlag(itemFlag);
                setIndisipliner(viewIndisipliner);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            viewIndisipliner.setIndisiplinerId(itemId);
            viewIndisipliner.setFlag(itemFlag);
            setIndisipliner(viewIndisipliner);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[IndisiplinerAction.view] end process <<<");

        return "init_view";
    }

    @Override
    public String save() {
        logger.info("[AlatAction.saveAdd] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        Indisipliner indisipliner = getIndisipliner();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date awalPantau = CommonUtil.convertToDate(indisipliner.getStTanggalAwalPantau());
        java.sql.Date akhirPantau = CommonUtil.convertToDate(indisipliner.getStTanggalAkhirPantau());
        java.sql.Date awalBlokir = CommonUtil.convertToDate(indisipliner.getStTanggalAwalBlokirAbsensi());
        java.sql.Date akhirBlokir = CommonUtil.convertToDate(indisipliner.getStTanggalAkhirBlokirAbsensi());
        java.sql.Date tanggal = CommonUtil.convertToDate(indisipliner.getStTanggal());
        indisipliner.setTanggalAwalPantau(awalPantau);
        indisipliner.setTanggalAkhirPantau(akhirPantau);
        indisipliner.setTanggalAwalBlokirAbsensi(awalBlokir);
        indisipliner.setTanggalAkhirBlokirAbsensi(akhirBlokir);
        indisipliner.setTanggal(tanggal);
        indisipliner.setCreatedWho(userLogin);
        indisipliner.setLastUpdate(updateTime);
        indisipliner.setCreatedDate(updateTime);
        indisipliner.setLastUpdateWho(userLogin);
        indisipliner.setAction("C");
        indisipliner.setFlag("Y");

        try {
            notifikasiList = indisiplinerBoProxy.saveAddIndisipliner(indisipliner);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "indisiplinerBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[indisiplinerAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[indisiplinerAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        for (Notifikasi notifikasi : notifikasiList){
            notifikasiBo.sendNotif(notifikasi);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultIndisipliner");

        logger.info("[indisiplinerAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[IndisiplinerAction.search] start process >>>");

        Indisipliner searchAlat = getIndisipliner();
        List<Indisipliner> listOfSearchIndisipliner = new ArrayList();
        try {
            listOfSearchIndisipliner = indisiplinerBoProxy.getByCriteria(searchAlat);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "IndisiplinerAction.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IndisiplinerAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[IndisiplinerAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultIndisipliner");
        session.setAttribute("listOfResultIndisipliner", listOfSearchIndisipliner);

        logger.info("[IndisiplinerAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[IndisiplinerAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultIndisipliner");
        logger.info("[IndisiplinerAction.initForm] end process >>>");
        return INPUT;
    }
    public List initComboPersonil(String query, String branchId) {
        logger.info("[IndisiplinerAction.initComboPersonil] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        try {
            listOfUser = biodataBo.getListOfPersonil(query,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IndisiplinerAction.initComboPersonil] Error when saving error,", e1);
            }
            logger.error("[IndisiplinerAction.initComboPersonil] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[IndisiplinerAction.initComboPersonil] end process <<<");

        return listOfUser;
    }
    public String saveClosed(String IndisiplinerId, String statusApprove,String nip,String keteranganClosed){
        logger.info("[SppdAction.saveEdit] start process >>>");
        try {
            Indisipliner editIndisipliner = new Indisipliner();
            editIndisipliner.setIndisiplinerId(IndisiplinerId);
            if(statusApprove.equals("Y")){
                editIndisipliner.setClosedFlag(statusApprove);
            }
            editIndisipliner.setNip(nip);
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editIndisipliner.setLastUpdateWho(userLogin);
            editIndisipliner.setLastUpdate(updateTime);
            editIndisipliner.setClosedNote(keteranganClosed);
            editIndisipliner.setAction("U");
            editIndisipliner.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            IndisiplinerBo indisiplinerBo = (IndisiplinerBo) ctx.getBean("indisiplinerBoProxy");
            indisiplinerBo.saveClosed(editIndisipliner);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "IndisiplinerBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SppdAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SppdAction.saveEdit] end process <<<");

        return "success_save_edit";
    }
    public String saveApprove(String IndisiplinerId, String statusApprove, String who,String nip,String tanggal){
        logger.info("[SppdAction.saveEdit] start process >>>");
        try {
            Indisipliner editIndisipliner = new Indisipliner();
            editIndisipliner.setIndisiplinerId(IndisiplinerId);
            if(who.equals("atasan")){
                if(statusApprove.equals("Y")){
                    editIndisipliner.setApprovalFlag(statusApprove);
                }else{
                    editIndisipliner.setApprovalFlag("N");
                    editIndisipliner.setNotApprovalNote(statusApprove);
                }
            }
            editIndisipliner.setNip(nip);
            editIndisipliner.setTmpApprove(who);
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if (!tanggal.equalsIgnoreCase("1-01-1970")){
                editIndisipliner.setTanggalAkhirBlokirSetuju(CommonUtil.convertToDate(tanggal));
            }
            editIndisipliner.setLastUpdateWho(userLogin);
            editIndisipliner.setLastUpdate(updateTime);
            editIndisipliner.setAction("U");
            editIndisipliner.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            IndisiplinerBo indisiplinerBo = (IndisiplinerBo) ctx.getBean("indisiplinerBoProxy");
            indisiplinerBo.saveApprove(editIndisipliner);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "IndisiplinerBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SppdAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SppdAction.saveEdit] end process <<<");

        return "success_save_edit";
    }
    public List<Indisipliner> searchHistoryIndisipliner (String nip){
        logger.info("[IndisiplinerAction.searchHistoryIndisipliner] start process >>>");
        List<Indisipliner> result =new ArrayList<>();
        Indisipliner searchIndisipliner = new Indisipliner();
        searchIndisipliner.setNip(nip);
        searchIndisipliner.setFlag("Y");
        searchIndisipliner.setApprovalFlag("Y");
        searchIndisipliner.setClosedFlag("Y");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IndisiplinerBo indisiplinerBo = (IndisiplinerBo) ctx.getBean("indisiplinerBoProxy");
        try {
            result = indisiplinerBo.getByCriteria(searchIndisipliner);
        } catch (GeneralBOException e1) {
            logger.error("[SppdAction.searchHistoryIndisipliner] Error when saving error,", e1);
        }
        return result;
    }
    public List<Indisipliner> searchIndisipliner (String nip){
        logger.info("[IndisiplinerAction.searchIndisipliner] start process >>>");
        List<Indisipliner> result =new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IndisiplinerBo indisiplinerBo = (IndisiplinerBo) ctx.getBean("indisiplinerBoProxy");
        try {
            result = indisiplinerBo.getListIndisipliner(nip);
        } catch (GeneralBOException e1) {
            logger.error("[SppdAction.searchIndisipliner] Error when saving error,", e1);
        }
        return result;
    }
    public String cetakSurat(){
        logger.info("[IndisiplinerAction.cetakSurat] end process >>>");
        String id = getId();
        Indisipliner indisipliner = new Indisipliner();
        Biodata kabagSDM = new Biodata();
        indisipliner.setIndisiplinerId(id);
        List<Indisipliner> indisiplinerList= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IndisiplinerBo indisiplinerBo = (IndisiplinerBo) ctx.getBean("indisiplinerBoProxy");
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        try {
            indisiplinerList = indisiplinerBo.getByCriteria(indisipliner);
            kabagSDM = biodataBo.getKabagSdm();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = indisiplinerBo.saveErrorMessage(e.getMessage(), "indisiplinerAction.cetakSurat");
            } catch (GeneralBOException e1) {
                logger.error("[indisiplinerAction.cetakSurat] Error when saving error,", e1);
            }
            logger.error("[indisiplinerAction.cetakSurat] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (indisiplinerList != null){
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            String stDate = dt1.format(dataDate);
            for (Indisipliner indisipliner1 : indisiplinerList){
                if (("SP0").equalsIgnoreCase(indisipliner1.getTipeIndisipliner())){
                    indisipliner1.setTipeIndisipliner("Teguran");
                }
                reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
                reportParams.put("indisiplinerId", id);
                reportParams.put("nama",indisipliner1.getNama());
                reportParams.put("nip",indisipliner1.getNip());
                reportParams.put("dampak",indisipliner1.getDampak());
                reportParams.put("jabatan",indisipliner1.getPositionName());
                reportParams.put("bagianName",indisipliner1.getBagianName());
                reportParams.put("kabagSdm",kabagSDM.getNamaPegawai());
                reportParams.put("keteranganPelanggaran",indisipliner1.getKeteranganPelanggaran());
                reportParams.put("tipeIndisipliner",indisipliner1.getTipeIndisipliner());
                reportParams.put("date", stDate);
            }
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = indisiplinerBo.saveErrorMessage(e.getMessage(), "cetakSurat");
                } catch (GeneralBOException e1) {
                    logger.error("[indisiplinerAction.cetakSurat] Error when downloading ,", e1);
                }
                logger.error("[indisiplinerAction.cetakSurat] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
                return "failure_print";
            }

        } else {
            logger.error("[indisiplinerAction.cetakSurat] Error when print report realiassi bibit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list notification detail is empty, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[TrainingAction.printSuratJaminan] end process <<<");
        return "print_surat_indisipliner";
    }
    public String printReportRekapitulasiIndisipliner(){
        logger.info("[IndisiplinerAction.printReportRekapitulasiIndisipliner] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Indisipliner> result = new ArrayList<>();
        List<Biodata> biodataList= new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        BranchBo branchBo= (BranchBo) ctx.getBean("branchBoProxy");
        PositionBagianBo positionBagianBo= (PositionBagianBo) ctx.getBean("positionBagianBoProxy");
        Branch branch = new Branch();
        positionBagian positionBagian = new positionBagian();
        biodataList = biodataBo.getBiodataforAbsensi(getBranchId(),"","",getNip());

        for (Biodata biodata : biodataList){
            List<Indisipliner> listData = new ArrayList();
            List<Indisipliner> listDataIndisipliner = new ArrayList();
            Indisipliner search = new Indisipliner();
            search.setFlag("Y");
            search.setStTanggalDari(getTglFrom());
            search.setStTanggalSelesai(getTglTo());
            search.setNip(biodata.getNip());
            try {
                listData = indisiplinerBoProxy.getIndisiplinerUser(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "IndisiplinerAction.getCutiUser");
                } catch (GeneralBOException e1) {
                    logger.error("[IndisiplinerAction.printReportRekapitulasiIndisipliner] Error when saving error,", e1);
                }
                logger.error("[IndisiplinerAction.printReportRekapitulasiIndisipliner] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
            for (Indisipliner indisipliner: listData ){
                indisipliner.setNama(biodata.getNamaPegawai());
                indisipliner.setPositionName(biodata.getPositionName());
                indisipliner.setBagianName(biodata.getBagianName());
                indisipliner.setBranchName(biodata.getBranchName());
                indisipliner.setBagianName(biodata.getBagianName());
                listDataIndisipliner.add(indisipliner);
            }
            result.addAll(listDataIndisipliner);
        }

        if (!("").equalsIgnoreCase(getBranchId())){
            try {
                branch = branchBo.getBranchById(getBranchId(),"Y");
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "IndisiplinerAction.getBranchById");
                } catch (GeneralBOException e1) {
                    logger.error("[IndisiplinerAction.printReportRekapitulasiIndisipliner] Error when saving error,", e1);
                }
                logger.error("[IndisiplinerAction.printReportRekapitulasiIndisipliner] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
        }
        if (!("").equalsIgnoreCase(getBagian())){
            try {
                positionBagian = positionBagianBo.getBagianById(getBagian(),"Y");
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "strukturJabatanBo.getBranchById");
                } catch (GeneralBOException e1) {
                    logger.error("[IndisiplinerAction.printReportRekapitulasiIndisipliner] Error when saving error,", e1);
                }
                logger.error("[IndisiplinerAction.printReportRekapitulasiIndisipliner] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
        }

        JRBeanCollectionDataSource itemData;


        String bagianPegawai="";
        String nama="";
        int a = 1;
        List<Indisipliner> forReport = new ArrayList<>();
        for(Indisipliner indisipliner: result) {
            if (!bagianPegawai.equalsIgnoreCase(indisipliner.getBagianName())){
                Indisipliner tmp = new Indisipliner();
                tmp.setNo("");
                tmp.setNip(indisipliner.getBagianName());
                tmp.setNama("");
                tmp.setStTanggal("");
                tmp.setTipeIndisipliner("");
                tmp.setKeteranganPelanggaran("");
                tmp.setBagianName("");
                forReport.add(tmp);
                bagianPegawai = indisipliner.getBagianName();
                a=1;
            }
            if (nama.equalsIgnoreCase(indisipliner.getNama())){
                Indisipliner tmp = new Indisipliner();
                tmp.setNip("");
                tmp.setNama("");
                tmp.setNo(String.valueOf(a));
                tmp.setStTanggal(indisipliner.getStTanggal());
                tmp.setTipeIndisipliner(indisipliner.getTipeIndisipliner());
                tmp.setKeteranganPelanggaran(indisipliner.getKeteranganPelanggaran());
                tmp.setBagianName("");
                forReport.add(tmp);
                a++;
            } else if (bagianPegawai.equalsIgnoreCase(indisipliner.getBagianName())) {
                indisipliner.setNo(String.valueOf(a));
                forReport.add(indisipliner);
                a++;
            }
            nama=indisipliner.getNama();
        }

        itemData = new JRBeanCollectionDataSource(forReport);
        String bagian="-",unit="-";
        if (!("").equalsIgnoreCase(getBagian())){
            bagian=positionBagian.getBagianName();
        }
        if (branch.getBranchName()!=null){
            unit=branch.getBranchName();
        }

        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);
        String stTanggalDari="-";
        String stTanggalSampai="-";

        if (!("").equalsIgnoreCase(getTglFrom())){
            Date tanggalDari = CommonUtil.convertStringToDate(getTglFrom());
            stTanggalDari=CommonUtil.convertDateToString(tanggalDari);
        }

        if (!("").equalsIgnoreCase(getTglTo())){
            Date tanggalSampai = CommonUtil.convertStringToDate(getTglTo());
            stTanggalSampai=CommonUtil.convertDateToString(tanggalSampai);
        }

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport","REPORT REKAP INDISIPLINER");
        reportParams.put("bagian",bagian);
        reportParams.put("unit",unit);
        reportParams.put("tanggalDari",stTanggalDari);
        reportParams.put("tanggalSelesai",stTanggalSampai);
        reportParams.put("date", stDate);
        reportParams.put("itemDataSource", itemData);
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = indisiplinerBoProxy.saveErrorMessage(e.getMessage(), "printReport");
            } catch (GeneralBOException e1) {
                logger.error("[IndisiplinerAction.printReportRekapitulasiIndisipliner] Error when downloading ,", e1);
            }
            logger.error("[IndisiplinerAction.printReportRekapitulasiIndisipliner] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[IndisiplinerAction.printReportRekapitulasiIndisipliner] end process <<<");
        return "success_print_report_rekapitulasi_indisipliner";
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
