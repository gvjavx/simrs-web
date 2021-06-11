package com.neurix.hris.transaksi.mutasi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.displaytag.LongDateWrapper;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.statusMutasi.bo.StatusMutasiBo;
import com.neurix.hris.master.statusMutasi.model.StatusMutasi;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.mutasi.bo.MutasiBo;
import com.neurix.hris.transaksi.mutasi.model.ItMutasiDocEntity;
import com.neurix.hris.transaksi.mutasi.model.ItMutasiEntity;
import com.neurix.hris.transaksi.mutasi.model.Mutasi;
import com.neurix.hris.transaksi.mutasi.model.MutasiDoc;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.sppd.model.SppdPerson;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

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
        session.removeAttribute("listOfMutasi");

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

    public CrudResponse saveMutasi(String tglMutasi){
        logger.info("[MutasiAction.saveMutasi] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MutasiBo mutasiBo = (MutasiBo) ctx.getBean("mutasiBoProxy");
        try {
            Mutasi mutasi = new Mutasi();
            mutasi.setStTanggalEfektif(tglMutasi);
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<Mutasi> mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");

            if(mutasiList.size()>0) {
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

                mutasiBo.saveMutasi(mutasi, mutasiList);
                response.setStatus("success");
                response.setMsg("Data berhasil simpan...!");
            }else {
                logger.error("ERROR, List Data Mutasi masih kosong !");
                response.setStatus("error");
                response.setMsg("Error, list mutasi masih kosong.");
//                throw new GeneralBOException("List Data Mutasi masih kosong, mohon cek kembali.");
            }
        }catch (Exception e) {
            logger.error("[mutasiAction.saveMutasi] Error when adding item , Found problem when saving add data, please inform to your admin.", e);
            response.setStatus("error");
            response.setMsg(e.getMessage());
//            throw new GeneralBOException(e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("saveMutasi");
        return response;
    }

    @Override
    public String search() {
        logger.info("[MutasiAction.search] start process >>>");

        Mutasi searchMutasi = getMutasi();

        List<Mutasi> listOfsearchMutasi = new ArrayList();

        if (searchMutasi.getStTanggalEfektif() != null && !"".equalsIgnoreCase(searchMutasi.getStTanggalEfektif())) {
            searchMutasi.setTanggalEfektif(CommonUtil.convertToTimestamp(searchMutasi.getStTanggalEfektif()));
        }

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchMutasi.setBranchIdUser(branchId);
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
        session.removeAttribute("listOfMutasi");
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
        if (id != null) {
            Mutasi searchMutasi = new Mutasi();

            searchMutasi.setMutasiId(id);
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            MutasiBo mutasiBo = (MutasiBo) ctx.getBean("mutasiBoProxy");
            BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

            try {
                searchMutasi = mutasiBo.getDataReportMutasi(id);
            } catch (GeneralBOException e){
                logger.error("[mutasiAction.printReportMutasi] Error when get data report mutasi. please inform to your admin.", e);
            }

            String tahun            = "2020";
            BigDecimal gajiPegawai  = mutasiBo.getGajiPokok(searchMutasi.getLevelBaru(),tahun);
            String stGajiPegawai    = CommonUtil.numbericFormat(gajiPegawai,"###,###");
            String noSurat          = searchMutasi.getNoSk();

            Branch branch = new Branch();
            try{
                branch = branchBo.getBranchById(CommonConstant.BRANCH_KP,"Y");
            }catch( HibernateException e){
                logger.error("[mutasiAction.printReportMutasi] Error when get data report mutasi. please inform to your admin.", e);
            }
            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU);
            String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
            reportParams.put("alamatUni", branch.getAlamatSurat()+","+stTanggal);
            reportParams.put("branchName", branch.getBranchName());
//            reportParams.put("titleReport", "Surat Mutasi");
//            reportParams.put("noSurat", noSurat);
//            reportParams.put("tanggalSk", searchMutasi.getStTanggalEfektif());

//            reportParams.put("namaPegawai", searchMutasi.getNama());
//            reportParams.put("jabatanLama", searchMutasi.getPositionLamaName());
//            reportParams.put("unitLama", searchMutasi.getBranchLamaName());
//            reportParams.put("jabatanBaru", searchMutasi.getPositionBaruName());
//            reportParams.put("unitBaru", searchMutasi.getBranchBaruName());
//            reportParams.put("gajiBaru","Rp. "+ stGajiPegawai);
            stTanggal = CommonUtil.convertDateToString( new java.util.Date());
            reportParams.put("date", stTanggal);

            reportParams.put("mutasiId", searchMutasi.getMutasiId());
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

    @Override
    public String initForm() {
        logger.info("[MutasiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Mutasi data = new Mutasi();
        if (branchId!=null){
            data.setBranchLamaId(branchId);
            data.setBranchIdUser(branchId);
        }else{
            data.setBranchLamaId("");
        }
        setMutasi(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfMutasi");
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

//    public String saveAnggotaAdd(String nip, String personName, String branchLamaId, String branchLamaName, String divisiLamaId, String divisiLamaName,
//                               String positionLamaId, String positionLamaName, String pjsLama, String menggantikanId, String menggantikanNama, String branchBaruId, String branchBaruName,
//                               String divisiBaruId, String divisiBaruName, String positionBaruId, String positionBaruName, String pjsBaru, String status, String levelLama,
//                                  String levelBaru, String levelLamaName, String levelBaruName, String profesiLamaId, String profesiLamaName, String profesiBaruId, String profesiBaruName, String tipePegawai){

    // Sigit 2020-01-08, perubahan dari set multi parameter menjadi 1 jsonString
    public String saveAnggotaAdd(String strObjJeson) throws JSONException{
        logger.info("[MutasiAction.saveAnggotaAdd] START process >>>");

        String statusSave="";
        List<Mutasi> mutasiList = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        JSONObject obj = new JSONObject(strObjJeson);
        Mutasi mutasi = new Mutasi();
        mutasi.setNip(obj.getString("nip"));
        mutasi.setPositionBaruId(obj.getString("positionbaruid"));

        // Sigit 2020-01-08, peribahan checkNip menjadi cekNipAndPositionId
        // agar validasi tidak hanya berdasarkan nip namun jg posisi,
        // karna kemungkinan 1 orng punya posisi > 1
        if(cekNipAndPositionId(mutasi.getNip(), mutasi.getPositionBaruId())){

            try {
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                StatusMutasiBo statusMutasiBo = (StatusMutasiBo) ctx.getBean("statusMutasiBoProxy");

                mutasi.setNip(obj.getString("nip"));
                mutasi.setNama(obj.getString("personname"));
                mutasi.setBranchLamaId(obj.getString("branchlamaid"));
                mutasi.setBranchLamaName(obj.getString("branchlamaname"));
                mutasi.setDivisiLamaId(obj.getString("divisilamaid"));
                mutasi.setDivisiLamaName(obj.getString("divisilamaname"));
                mutasi.setPositionLamaId(obj.getString("positionlamaid"));
                mutasi.setPositionLamaName(obj.getString("positionlamaname"));
                mutasi.setProfesiLamaId(obj.getString("profesilamaid"));
                mutasi.setProfesiLamaName(obj.getString("profesilamaname"));

                mutasi.setBranchBaruId(obj.getString("branchbaruid"));
                mutasi.setBranchBaruName(obj.getString("branchbaruname"));
                mutasi.setDivisiBaruId(obj.getString("divisibaruid"));
                mutasi.setDivisiBaruName(obj.getString("divisibaruname"));
                mutasi.setPositionBaruId(obj.getString("positionbaruid"));
                mutasi.setPositionBaruName(obj.getString("poisitionbaruname"));
                mutasi.setProfesiBaruId(obj.getString("profesibaruid"));
                mutasi.setProfesiBaruName(obj.getString("profesibaruname"));

                mutasi.setStatus(obj.getString("status"));
                mutasi.setJenisPegawaiId(obj.getString("jenispegawai"));
                mutasi.setJenisPegawaiName(obj.getString("jenispegawainame"));
                mutasi.setFlagDigaji(obj.getString("flagdigaji"));
                mutasi.setUpdatePosisiId(obj.getString("positionPengganti"));
                mutasi.setStTanggalKeluar(obj.getString("tanggalKeluar"));
                mutasi.setNoSk(obj.getString("nosk"));
                mutasi.setIdKetResign(obj.getString("idket"));
                mutasi.setKetResign(obj.getString("txtket"));

                if (!"".equalsIgnoreCase(mutasi.getNip())){
                    StatusMutasi search = new StatusMutasi();
                    search.setFlag("Y");
                    search.setStatusMutasiId(mutasi.getStatus());
                    List<StatusMutasi> statusMutasiList = statusMutasiBo.getByCriteria(search);
                    for (StatusMutasi statusMutasi : statusMutasiList){
                        mutasi.setStatusName(statusMutasi.getStatusMutasiName());
                    }
                }

                //save to session
                if (!"".equalsIgnoreCase(mutasi.getStatus())){
                    mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");
                    if(mutasiList != null){
                        mutasiList.add(mutasi);
                    }else{
                        mutasiList = new ArrayList();
                        mutasiList.add(mutasi);
                    }
                }

            }catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "MutasiAction.saveAnggotaAdd");
                } catch (GeneralBOException e1) {
                    logger.error("[MutasiAction.saveAnggotaAdd] Error when saving error,", e1);
                }
                logger.error("[MutasiAction.saveAnggotaAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            session.setAttribute("listOfMutasi", mutasiList);
        }else{
            statusSave="Pegawai sudah ditambahkan";
        }

        logger.info("[MutasiAction.saveAnggotaAdd] END process <<<");
        return statusSave;
    }

    private boolean cekNip(String nip){

        // untuk mendapatkan nip dari list session, jika ada maka mengembalikan false;
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

    // Sigit 2020-01-08, Check nip dan positionid jika sudah ada pada list session
    private boolean cekNipAndPositionId(String nip, String positionId){

        // untuk mendapatkan nip dari list session, jika ada maka mengembalikan false;
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Mutasi> mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");
        boolean hasil = true ;

        if (mutasiList != null && mutasiList.size() > 0){

            List<Mutasi> filteredList = mutasiList.stream().filter(
                    p-> p.getNip().equalsIgnoreCase(nip) &&
                            p.getPositionBaruId().equalsIgnoreCase(positionId)
            ).collect(Collectors.toList());

            if (filteredList != null && filteredList.size() > 0)
                hasil = false;
        }

        return hasil;
    }

    public boolean saveAnggotaEdit(String nipOld, String nip, String personName, String branchLamaId, String branchLamaName,String divisiLamaId, String divisiLamaName,
                                   String positionLamaId, String positionLamaName, String pjsLama, String menggantikanId, String menggantikanNama, String branchBaruId,
                                   String branchBaruName, String divisiBaruId, String divisiBaruName, String positionBaruId, String positionBaruName,String pjsBaru, String status,
                                   String levelLamaId, String levelBaruId, String levelLamaName, String levelBaruName, String profesiLamaId, String profesiLamName, String profesiBaruId, String profesiBaruName, String tipePegawai){
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

                            mutasi.setLevelLama(levelLamaId);
                            mutasi.setLevelLamaName(levelLamaName);
                            mutasi.setLevelBaru(levelBaruId);
                            mutasi.setLevelBaruName(levelBaruName);
                            mutasi.setProfesiLamaId(profesiLamaId);
                            mutasi.setProfesiLamaName(profesiLamName);
                            mutasi.setProfesiBaruId(profesiBaruId);
                            mutasi.setProfesiBaruName(profesiBaruName);
                            mutasi.setTipePegawai(tipePegawai);

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
                    if(!nip.equalsIgnoreCase(mutasi.getNip())){
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

    public String getButuhPengganti(String position) {
        String status ="N";
        logger.info("[MutasiAction.getButuhPengganti] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        List<Position> positionList = new ArrayList<>();
        Position search = new Position();
        search.setPositionId(position);
        search.setFlag("Y");
        try {
            PositionBo positionBo= (PositionBo) ctx.getBean("positionBoProxy");
            positionList=positionBo.getByCriteria(search);

            for (Position data : positionList){
                if (("Y").equalsIgnoreCase(data.getFlagDijabatSatuOrang())){
                    status="Y";
                }
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mutasiBoProxy.saveErrorMessage(e.getMessage(), "positionBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MutasiAction.getButuhPengganti] Error when saving error,", e1);
            }
            logger.error("[MutasiAction.getButuhPengganti] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return status;
    }

    public List getListPersonilByNameAndBranch(String query, String branchId) {
        logger.info("[MutasiAction.getListPersonilByNameAndBranch] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

        try {
            listOfUser = biodataBo.getListOfPersonilForMutasi(query,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "MutasiAction.getListPersonilByNameAndBranch");
            } catch (GeneralBOException e1) {
                logger.error("[MutasiAction.getListPersonilByNameAndBranch] Error Search,", e1);
            }
            logger.error("[MutasiAction.getListPersonilByNameAndBranch] Error when ," + "[" + logId + "] Found problem when retrieving combo data, please inform to your admin.", e);
        }

        logger.info("[MutasiAction.getListPersonilByNameAndBranch] end process <<<");

        return listOfUser;
    }

    // Sigit 2020-01-11, check data nip disession untuk menghindari proses mutasi lebih dari 1
    public CrudResponse checkIsAvailInSession(String nip){
        logger.info("[MutasiAction.checkIsAvailInSession] start process >>>");

        CrudResponse response   = new CrudResponse();
        HttpSession session     = ServletActionContext.getRequest().getSession();
        List<Mutasi> mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");

        if (mutasiList != null && mutasiList.size() > 0 && nip != null && !"".equalsIgnoreCase(nip)){
            List<Mutasi> filteredMutasi = mutasiList.stream().filter(p->p.getNip().equalsIgnoreCase(nip)).collect(Collectors.toList());
            if (filteredMutasi.size() > 0){
                Mutasi mutasiData = filteredMutasi.get(0);
                response.setStatus("error");
                response.setMsg("Data Atas Nama : " + mutasiData.getNama() + " Sudah Ada !. Cek kembali list Mutasi");
            }
        }

        if (response.getStatus() == null || "".equalsIgnoreCase(response.getStatus()))
            response.setStatus("success");

        logger.info("[MutasiAction.checkIsAvailInSession] end process <<<");
        return response;
    }

    public List<Position> getListOtherPosition(String positionId, String nip){
        logger.info("[MutasiAction.getListOtherPosition] start process >>>");

        List<Position> listOfPosition = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MutasiBo mutasiBo = (MutasiBo) ctx.getBean("mutasiBoProxy");

        Boolean isDefault = false;
        try {
            isDefault = mutasiBo.checkJenisPegawaiDefault(nip, positionId);
        } catch (GeneralBOException e){
            logger.error("[MutasiAction.getListOtherPosition] Error chek is default,", e);
        }

        if (isDefault){
            try {
                listOfPosition = mutasiBo.getListOtherPosition(positionId, nip);
            } catch (GeneralBOException e){
                logger.error("[MutasiAction.getListOtherPosition] Error search data other position,", e);
            }
        }

        logger.info("[MutasiAction.getListOtherPosition] end process <<<");
        return listOfPosition;
    }

    public List<Position> getListPositionJabatanLain(String positionId, String nip){
        logger.info("[MutasiAction.getListPositionJabatanLain] start process >>>");

        List<Position> listOfPosition = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MutasiBo mutasiBo = (MutasiBo) ctx.getBean("mutasiBoProxy");

        try {
            listOfPosition = mutasiBo.getListOtherPosition(positionId, nip);
        } catch (GeneralBOException e){
            logger.error("[MutasiAction.getListPositionJabatanLain] Error search data other position,", e);
        }

        logger.info("[MutasiAction.getListPositionJabatanLain] end process <<<");
        return listOfPosition;
    }

    public Boolean checkIsAvailJabatanUtama(String nip, String jenisPegawai){
        logger.info("[MutasiAction.checkIsAvailJabatanUtama] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MutasiBo mutasiBo = (MutasiBo) ctx.getBean("mutasiBoProxy");

        Boolean isDefault = false;
        try {
            isDefault = mutasiBo.checkPositionByJenisPegawai(nip, jenisPegawai);
        } catch (GeneralBOException e){
            logger.error("[MutasiAction.checkIsAvailJabatanUtama] Error chek is default,", e);
        }

        logger.info("[MutasiAction.checkIsAvailJabatanUtama] end process <<<");
        return isDefault;
    }
}
