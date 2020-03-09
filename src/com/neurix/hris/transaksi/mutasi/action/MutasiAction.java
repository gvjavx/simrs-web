package com.neurix.hris.transaksi.mutasi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.displaytag.LongDateWrapper;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.transaksi.mutasi.bo.MutasiBo;
import com.neurix.hris.transaksi.mutasi.model.ItMutasiDocEntity;
import com.neurix.hris.transaksi.mutasi.model.ItMutasiEntity;
import com.neurix.hris.transaksi.mutasi.model.Mutasi;
import com.neurix.hris.transaksi.mutasi.model.MutasiDoc;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.sppd.model.SppdPerson;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class MutasiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(MutasiAction.class);
    private MutasiBo mutasiBoProxy;
    private Mutasi mutasi;
    private MutasiDoc mutasiDoc;

    private java.io.File fileUpload;
    private java.io.File fileUpload1;
    private String fileUpload1ContentType;
    private String fileUpload1FileName;

    private String fileUploadContentType;
    private String fileUploadFileName;
    private String idMutasi;
    private String noSurat;

    public String getIdMutasi() {
        return idMutasi;
    }

    public void setIdMutasi(String idMutasi) {
        this.idMutasi = idMutasi;
    }

    public String getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public MutasiDoc getMutasiDoc() {
        return mutasiDoc;
    }

    public void setMutasiDoc(MutasiDoc mutasiDoc) {
        this.mutasiDoc = mutasiDoc;
    }

    public File getFileUpload1() {
        return fileUpload1;
    }

    public void setFileUpload1(File fileUpload1) {
        this.fileUpload1 = fileUpload1;
    }

    public String getFileUpload1ContentType() {
        return fileUpload1ContentType;
    }

    public void setFileUpload1ContentType(String fileUpload1ContentType) {
        this.fileUpload1ContentType = fileUpload1ContentType;
    }

    public String getFileUpload1FileName() {
        return fileUpload1FileName;
    }

    public void setFileUpload1FileName(String fileUpload1FileName) {
        this.fileUpload1FileName = fileUpload1FileName;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    private List<Mutasi> listComboMutasi = new ArrayList<Mutasi>();

    public List<Mutasi> getListComboMutasi() {
        return listComboMutasi;
    }

    public void setListComboMutasi(List<Mutasi> listComboMutasi) {
        this.listComboMutasi = listComboMutasi;
    }

    public MutasiBo getMutasiBoProxy() {
        return mutasiBoProxy;
    }

    public void setMutasiBoProxy(MutasiBo mutasiBoProxy) {
        this.mutasiBoProxy = mutasiBoProxy;
    }

    public Mutasi getMutasi() {
        return mutasi;
    }

    public void setMutasi(Mutasi mutasi) {
        this.mutasi = mutasi;
    }

    private List<Mutasi> initComboMutasi;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MutasiAction.logger = logger;
    }


    public List<Mutasi> getInitComboMutasi() {
        return initComboMutasi;
    }

    public void setInitComboMutasi(List<Mutasi> initComboMutasi) {
        this.initComboMutasi = initComboMutasi;
    }

    public Mutasi init(String kode, String flag){
        logger.info("[MutasiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Mutasi> listOfResult = (List<Mutasi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Mutasi mutasi: listOfResult) {
                    if(kode.equalsIgnoreCase(mutasi.getMutasiId()) && flag.equalsIgnoreCase(mutasi.getFlag())){
                        setMutasi(mutasi);
                        break;
                    }
                }
            } else {
                setMutasi(new Mutasi());
            }

            logger.info("[MutasiAction.init] end process >>>");
        }
        return getMutasi();
    }

    @Override
    public String add() {
        logger.info("[MutasiAction.add] start process >>>");
        Mutasi addMutasi = new Mutasi();
        setMutasi(addMutasi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[MutasiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[MutasiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();


        Mutasi editMutasi = new Mutasi();

        if(itemFlag != null){
            try {
                editMutasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "MutasiBO.getMutasiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[MutasiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[MutasiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editMutasi != null) {
                setMutasi(editMutasi);
            } else {
                editMutasi.setFlag(itemFlag);
                editMutasi.setMutasiId(itemId);
                setMutasi(editMutasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editMutasi.setMutasiId(itemId);
            editMutasi.setFlag(getFlag());
            setMutasi(editMutasi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[MutasiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[MutasiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Mutasi deleteMutasi = new Mutasi();

        if (itemFlag != null ) {

            try {
                deleteMutasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "MutasiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[MutasiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[MutasiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteMutasi != null) {
                setMutasi(deleteMutasi);

            } else {
                deleteMutasi.setMutasiId(itemId);
                deleteMutasi.setFlag(itemFlag);
                setMutasi(deleteMutasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteMutasi.setMutasiId(itemId);
            deleteMutasi.setFlag(itemFlag);
            setMutasi(deleteMutasi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[MutasiAction.delete] end process <<<");

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

    public String cekDataMutasi(){
        String hasil = "success";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MutasiBo mutasiBo = (MutasiBo) ctx.getBean("mutasiBoProxy");

        hasil = mutasiBo.cekDataMutasiSys();

        return hasil;
    }

    public String saveMutasi(){
        logger.info("[MutasiAction.saveAdd] start process >>>");
        try {
            Mutasi mutasi = getMutasi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if (mutasi.getStTanggalEfektif() != null && !"".equalsIgnoreCase(mutasi.getStTanggalEfektif())) {
                mutasi.setTanggalEfektif(CommonUtil.convertToTimestamp(mutasi.getStTanggalEfektif()));
            }

            mutasi.setCreatedWho(userLogin);
            mutasi.setLastUpdate(updateTime);
            mutasi.setCreatedDate(updateTime);
            mutasi.setLastUpdateWho(userLogin);
            mutasi.setAction("C");
            mutasi.setFlag("Y");

            mutasiBoProxy.saveMutasi(mutasi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[mutasiAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            //logger.error("[mutasiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            //addActionError("Error, mohon periksa inputan anda kembali");
            mutasi.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
            //addActionMessage("Error, mohon periksa inputan anda kembali");
            return "input";
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfMutasi");

        return INPUT;
    }

    @Override
    public String search() {
        logger.info("[MutasiAction.search] start process >>>");

        Mutasi searchMutasi = getMutasi();

        List<Mutasi> listOfsearchMutasi = new ArrayList();

        if (searchMutasi.getStTanggalEfektif() != null && !"".equalsIgnoreCase(searchMutasi.getStTanggalEfektif())) {
            searchMutasi.setTanggalEfektif(CommonUtil.convertToTimestamp(searchMutasi.getStTanggalEfektif()));
        }

        try {
            listOfsearchMutasi = mutasiBoProxy.getByCriteria(searchMutasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "MutasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MutasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MutasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );

            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchMutasi);

        logger.info("[MutasiAction.search] end process <<<");

        return SUCCESS;
    }

    public String viewDoc(){
        logger.info("[MutasiAction.viewDoc] start process >>>");
        String id = getId();
        List<MutasiDoc> listOfsearchMutasiDoc = new ArrayList();

        listOfsearchMutasiDoc = mutasiBoProxy.getMutasiDoc(id);
        boolean isImg = false;

        for (MutasiDoc listData : listOfsearchMutasiDoc){
            if (id.equalsIgnoreCase(listData.getMutasiId())){
                if ("IMG".equalsIgnoreCase(listData.getFileType())){
                    listData.setPath("/hris/pages/upload/file/mutasi/"+listData.getFileUpload());
                    isImg = true;
                }
                if ("PDF".equalsIgnoreCase(listData.getFileType())){
                    listData.setPath("/hris/pages/upload/file/mutasi/"+listData.getFileUpload());
                }
                setMutasiDoc(listData);
            }
        }

        if (isImg){
            return "init_view_img";
        } else {
            return "init_view_pdf";
        }
    }

    public String printReportMutasi() {
        logger.info("[ReportAction.printReportMutasi] start process >>>");
        String id = getIdMutasi();
        String noSurat = getNoSurat();
        if (id != null) {
            Mutasi searchMutasi = new Mutasi();

            searchMutasi.setMutasiId(id);
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            MutasiBo mutasiBo = (MutasiBo) ctx.getBean("mutasiBoProxy");
            searchMutasi = mutasiBo.getDataReportMutasi(id);
            Branch branch = new Branch();
            BigDecimal gajiPegawai = mutasiBo.getGajiPokok(searchMutasi.getLevelBaru());
            String stGajiPegawai = CommonUtil.numbericFormat(gajiPegawai,"###,###");
            try{
                BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                branch = branchBo.getBranchById("KP","Y");
            }catch( HibernateException e){
            }
            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU);
            String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
            reportParams.put("alamatUni", branch.getAlamatSurat()+","+stTanggal);
            reportParams.put("branchName", branch.getBranchName());
            reportParams.put("titleReport", "Surat Keterangan");
            reportParams.put("noSurat", noSurat);
            reportParams.put("tanggalSk", searchMutasi.getStTanggalEfektif());


            reportParams.put("namaPegawai", searchMutasi.getNama());
            reportParams.put("jabatanLama", searchMutasi.getPositionLamaName());
            reportParams.put("unitLama", searchMutasi.getBranchLamaName());
            reportParams.put("jabatanBaru", searchMutasi.getPositionBaruName());
            reportParams.put("unitBaru", searchMutasi.getBranchBaruName());
            reportParams.put("levelBaru", searchMutasi.getLevelBaruName());
            reportParams.put("gajiBaru","Rp. "+ stGajiPegawai);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "printReportKPIUnit");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportKPIUnit] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportKPIUnit] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportKPIUnit] Error when print report kpi unit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }
        logger.info("[ReportAction.printReportKPIUnit] end process <<<");
        return "success_print_report_mutasi";
    }

    public String searchMutasi() {
        logger.info("[MutasiAction.search] start process >>>");

        Mutasi searchMutasi = new Mutasi();
        searchMutasi.setFlag("Y");
        List<Mutasi> listOfsearchMutasi = new ArrayList();

        try {
            listOfsearchMutasi = mutasiBoProxy.getByCriteria(searchMutasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "MutasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MutasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MutasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboMutasi.addAll(listOfsearchMutasi);
        return SUCCESS;
    }

    /*public String searchMutasiKualifikasi() {
        logger.info("[MutasiAction.search] start process >>>");

        Mutasi searchMutasi = getMutasi();
        searchMutasi.setFlag("Y");
        List<Mutasi> listOfsearchMutasi = new ArrayList();
        List<Mutasi> finalSearchMutasi = new ArrayList();
        try {
            listOfsearchMutasi = mutasiBoProxy.getKualifikasi(searchMutasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "MutasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MutasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MutasiAction.save] ," + "[" + logId + "] please inform to your admin.", e);
            addActionError("Error, " + e + ", please inform to your admin" );
            return ERROR;
        }

        if (listOfsearchMutasi.size()!=0){
            Comparator<Mutasi> comparator = new Comparator<Mutasi>() {
                @Override
                public int compare(Mutasi left, Mutasi right) {
                    String awal =left.getNip().replace("-","");
                    String akhir =right.getNip().replace("-","");
                    Long angka1 = Long.parseLong(awal);
                    Long angka2 = Long.parseLong(akhir);
                    return (int) (angka1-angka2);
                }
            };
            Collections.sort(listOfsearchMutasi, comparator);
            String nip="";
            for (Mutasi mutasi:listOfsearchMutasi){
                if (!mutasi.getNip().equalsIgnoreCase(nip)){
                    finalSearchMutasi.add(mutasi);
                    nip=mutasi.getNip();
                }
            }
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfKualifikasi");
        session.setAttribute("listOfKualifikasi", finalSearchMutasi);

        return "input_kualifikasi";
    }*/

    @Override
    public String initForm() {
        logger.info("[MutasiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[MutasiAction.initForm] end process >>>");
        return INPUT;
    }

    public String initFormKualifikasi() {
        logger.info("[MutasiAction.initFormKualifikasi] start process >>>");
        logger.info("[MutasiAction.initFormKualifikasi] end process >>>");
        return "input_kualifikasi";
    }

    public String initMutasi() {
        logger.info("[MutasiAction.search] start process >>>");

        Mutasi searchMutasi = new Mutasi();
        searchMutasi.setFlag("Y");
        List<Mutasi> listOfsearchMutasi = new ArrayList();

        try {
            listOfsearchMutasi = mutasiBoProxy.getByCriteria(searchMutasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "MutasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MutasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MutasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultMutasi");
        session.setAttribute("listOfResultMutasi", listOfsearchMutasi);

        logger.info("[MutasiAction.search] end process <<<");

        return "";
    }

    //----------------------------------------------------------Menejemen Session Daftar Mutasi -----------------------------------------------------
    public List<Mutasi> searchMutasiPerson() {
        logger.info("[MutasiAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Mutasi> mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");

        return mutasiList;
    }

    public List<PersonilPosition> getAvailableJabatan(String branch, String divisi, String position) {
        logger.info("[MutasiAction.search] start process >>>");
        PersonilPosition personilPosition = new PersonilPosition();
        personilPosition.setBranchId(branch);

        String stPosition = String.valueOf(position);

        personilPosition.setPositionId(stPosition);
        List<PersonilPosition> personilPositionList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            MutasiBo mutasiBo = (MutasiBo) ctx.getBean("mutasiBoProxy");
            personilPositionList = mutasiBo.getAvailableJabatan(personilPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "MutasiBO.getAvailableJabatan");
            } catch (GeneralBOException e1) {
                logger.error("[DepartmentAction.search] Error when saving error,", e1);
            }
            logger.error("[DepartmentAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return personilPositionList;
    }

    public List<Mutasi> searchMutasiPersonEdit(String nip) {
        logger.info("[MutasiAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Mutasi> mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");
        List<Mutasi> listHasil =  new ArrayList();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(mutasiList != null){
                for (Mutasi mutasi: mutasiList) {
                    if(nip.equalsIgnoreCase(mutasi.getNip())){
                        listHasil.add(mutasi);
                        break;
                    }
                }
            }

            logger.info("[MutasiAction.init] end process >>>");
        }
        return listHasil;
    }

    public boolean saveAnggotaAdd(String nip, String personName, String branchLamaId, String branchLamaName, String divisiLamaId, String divisiLamaName,
                               String positionLamaId, String positionLamaName, String pjsLama, String menggantikanId, String menggantikanNama, String branchBaruId, String branchBaruName,
                               String divisiBaruId, String divisiBaruName, String positionBaruId, String positionBaruName, String pjsBaru, String status, String tipe, String levelLama,
                                  String levelBaru, String levelLamaName, String levelBaruName){
        logger.info("[SppdAction.saveAdd] start process >>>");
        List<Mutasi> mutasiList = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        boolean hasil = true;

        if(cekNip(nip)){
            try {
                Mutasi mutasi = new Mutasi();

                mutasi.setNip(nip);
                mutasi.setNama(personName);
                mutasi.setBranchLamaId(branchLamaId);
                mutasi.setBranchLamaName(branchLamaName);
                mutasi.setDivisiLamaId(divisiLamaId);
                mutasi.setDivisiLamaName(divisiLamaName);
                mutasi.setPositionLamaId(positionLamaId);
                mutasi.setPositionLamaName(positionLamaName);
                mutasi.setLevelLama(levelLama);
                mutasi.setLevelLamaName(levelLamaName);
                mutasi.setPjsLama(pjsLama);

                if (menggantikanId!=null){
                    if (!menggantikanId.equalsIgnoreCase("-")){
                        mutasi.setPenggantiNip(menggantikanId);
                    }
                    else {
                        mutasi.setPenggantiNip("-");
                    }
                }else{
                    mutasi.setPenggantiNip("-");

                }
                mutasi.setPenggantiNama(menggantikanNama);
                mutasi.setBranchBaruId(branchBaruId);
                mutasi.setBranchBaruName(branchBaruName);
                mutasi.setDivisiBaruId(divisiBaruId);
                mutasi.setDivisiBaruName(divisiBaruName);
                mutasi.setPositionBaruId(positionBaruId);
                mutasi.setPositionBaruName(positionBaruName);
                mutasi.setLevelBaru(levelBaru);
                mutasi.setLevelBaruName(levelBaruName);
                mutasi.setPjs(pjsBaru);

                mutasi.setStatus(status);

                if (status!=""){
                    if ("M".equalsIgnoreCase(status)){
                        mutasi.setStatusName("Move");
                    }
                    else if ("R".equalsIgnoreCase(status)){
                        mutasi.setStatusName("Resign");
                    }
                    else if ("P".equalsIgnoreCase(status)){
                        mutasi.setStatusName("Pensiun");
                    }
                    else{
                        mutasi.setStatusName("Move Holding");
                    }
                }
                mutasi.setTipeMutasi(tipe);
                if (tipe!=""){
                    if ("MT".equalsIgnoreCase(tipe)){
                        mutasi.setTipeMutasiName("Mutasi");
                    }else if ("RT".equalsIgnoreCase(tipe)){
                        mutasi.setTipeMutasiName("Rotasi");
                    }
                }

                //HttpSession session = ServletActionContext.getRequest().getSession();
                mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");
                if(mutasiList != null){
                    mutasiList.add(mutasi);
                }else{
                    mutasiList = new ArrayList();
                    mutasiList.add(mutasi);
                }
                hasil = true;
            }catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
                } catch (GeneralBOException e1) {
                    logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                }
                logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            session.setAttribute("listOfMutasi", mutasiList);
        }else{
            hasil = false;
        }

        return hasil;
    }

    private boolean cekNip(String nip){
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Mutasi> mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");
        boolean hasil = true ;

        if(mutasiList != null){
            for(Mutasi mutasi: mutasiList){
                if(mutasi.getNip().equalsIgnoreCase(nip)){
                    hasil = false;
                    break;
                }
            }
        }

        return hasil;
    }

    public boolean saveAnggotaEdit(String nipOld, String nip, String personName, String branchLamaId, String branchLamaName,String divisiLamaId, String divisiLamaName,
                                   String positionLamaId, String positionLamaName, String pjsLama, String menggantikanId, String menggantikanNama, String branchBaruId,
                                   String branchBaruName, String divisiBaruId, String divisiBaruName, String positionBaruId, String positionBaruName,String pjsBaru, String status){
        logger.info("[KeluargaAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Mutasi> mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");
        List<Mutasi> listHasil =  new ArrayList();

        boolean hasil = true;

        if(nipOld.equalsIgnoreCase(nip)){
            if(nipOld != null && !"".equalsIgnoreCase(nipOld)){
                if(mutasiList != null){
                    for (Mutasi mutasi: mutasiList) {
                        if(nipOld.equalsIgnoreCase(mutasi.getNip())){

                            mutasi.setNip(nip);
                            mutasi.setNama(personName);
                            mutasi.setBranchLamaId(branchLamaId);
                            mutasi.setBranchLamaName(branchLamaName);
                            mutasi.setDivisiLamaId(divisiLamaId);
                            mutasi.setDivisiLamaName(divisiLamaName);
                            mutasi.setPositionLamaId(positionLamaId);
                            mutasi.setPositionLamaName(positionLamaName);
                            mutasi.setPjsLama(pjsLama);

                            mutasi.setPenggantiNip(menggantikanId);
                            mutasi.setPenggantiNama(menggantikanNama);
                            mutasi.setBranchBaruId(branchBaruId);
                            mutasi.setBranchBaruName(branchBaruName);
                            mutasi.setDivisiBaruId(divisiBaruId);
                            mutasi.setDivisiBaruName(divisiBaruName);
                            mutasi.setPositionBaruId(positionBaruId);
                            mutasi.setPositionBaruName(positionBaruName);
                            mutasi.setPjs(pjsBaru);
                            mutasi.setStatus(status);

                            listHasil.add(mutasi);
                        }else{
                            listHasil.add(mutasi);
                        }
                    }
                }

                logger.info("[SppdAction.init] end process >>>");
            }
            session.removeAttribute("listOfMutasi");
            session.setAttribute("listOfMutasi", listHasil);
        }else{
            if(cekNip(nip)){
                if(nipOld != null && !"".equalsIgnoreCase(nipOld)){
                    if(mutasiList != null){
                        for (Mutasi mutasi: mutasiList) {
                            if(nipOld.equalsIgnoreCase(mutasi.getNip())){

                                mutasi.setNip(nip);
                                mutasi.setNama(personName);
                                mutasi.setBranchLamaId(branchLamaId);
                                mutasi.setBranchLamaName(branchLamaName);
                                mutasi.setDivisiLamaId(divisiLamaId);
                                mutasi.setDivisiLamaName(divisiLamaName);
                                mutasi.setPositionLamaId(positionLamaId);
                                mutasi.setPositionLamaName(positionLamaName);
                                mutasi.setPjsLama(positionLamaName);

                                mutasi.setBranchBaruId(branchBaruId);
                                mutasi.setBranchBaruName(branchBaruName);
                                mutasi.setDivisiBaruId(divisiBaruId);
                                mutasi.setDivisiBaruName(divisiBaruName);
                                mutasi.setPositionBaruId(positionBaruId);
                                mutasi.setPositionBaruName(positionBaruName);
                                mutasi.setPjs(pjsBaru);
                                mutasi.setStatus(status);

                                listHasil.add(mutasi);
                            }else{
                                listHasil.add(mutasi);
                            }
                        }
                    }

                    logger.info("[SppdAction.init] end process >>>");
                }
                session.removeAttribute("listOfMutasi");
                session.setAttribute("listOfMutasi", listHasil);
            }else{
                hasil = false;
            }
        }

        return hasil;
    }

    public List<Mutasi> saveAnggotaDelete(String nip) {
        logger.info("[MutasiAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Mutasi> mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");
        List<Mutasi> listHasil =  new ArrayList();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(mutasiList != null){
                for (Mutasi mutasi: mutasiList) {
                    if(nip.equalsIgnoreCase(mutasi.getNip())){

                    }else{
                        listHasil.add(mutasi);
                    }
                }
            }

            logger.info("[MutasiAction.init] end process >>>");
        }
        session.removeAttribute("listOfMutasi");
        session.setAttribute("listOfMutasi", listHasil);
        return listHasil;
    }
    //----------------------------------------------------------Menejemen Session Daftar Mutasi -----------------------------------------------------

    public List<Mutasi> searchMutasi(String nip) {
        logger.info("[DepartmentAction.search] start process >>>");


        List<Mutasi> mutasiList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            MutasiBo mutasiBo = (MutasiBo) ctx.getBean("mutasiBoProxy");
            mutasiList = mutasiBo.getComboMutasi(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "DepartmentBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[DepartmentAction.search] Error when saving error,", e1);
            }
            logger.error("[DepartmentAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listMutasi");
        session.setAttribute("listMutasi", mutasiList);
        return mutasiList;
    }

    public String addMutasiDoc(){
        try {
            MutasiDoc mutasiDoc = getMutasiDoc();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if (this.fileUpload!=null) {
                //String[] parts = fileUploadFileName.split(".");
                String fileDocName = mutasiDoc.getMutasiId()+fileUploadFileName;
                String fileContentType = this.fileUploadContentType;

                String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_MUTASI_FILE;
                java.io.File fileToCreate = new java.io.File(filePath, fileDocName);

                //create file to save to folder '/upload'
                byte[] contentFile = null;
                try {
                    FileUtils.copyFile(this.fileUpload, fileToCreate);
                    contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                } catch (IOException e) {
                    Long logId = null;
                    try {
                        logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                    } catch (GeneralBOException e1) {
                        logger.error("[UserAction.save] Error when saving error,", e1);
                    }
                    logger.error("[UserAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                    return ERROR;
                }

                if (contentFile!=null) {
                    mutasiDoc.setFileUpload(fileDocName);
                    if ("image/jpeg".equalsIgnoreCase(fileContentType)) {
                        mutasiDoc.setFileType("IMG");
                    }else if("application/pdf".equalsIgnoreCase(fileContentType)) {
                        mutasiDoc.setFileType("PDF");
                    }
                }
            }


            mutasiDoc.setCreatedWho(userLogin);
            mutasiDoc.setLastUpdate(updateTime);
            mutasiDoc.setCreatedDate(updateTime);
            mutasiDoc.setLastUpdateWho(userLogin);
            mutasiDoc.setFlag("Y");

            mutasiBoProxy.addMutasiDoc(mutasiDoc);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
        return "init_edit";
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

    public List initComboPersonil(String query, String branchId) {
        logger.info("[MedicalRecordAction.initComboPersonil] start process >>>");

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
                logger.error("[MedicalRecordAction.initComboPersonil] Error when saving error,", e1);
            }
            logger.error("[MedicalRecordAction.initComboPersonil] Error when ," + "[" + logId + "] Found problem when retrieving combo data, please inform to your admin.", e);
        }

        logger.info("[MedicalRecordAction.initComboPersonil] end process <<<");

        return listOfUser;
    }
}
