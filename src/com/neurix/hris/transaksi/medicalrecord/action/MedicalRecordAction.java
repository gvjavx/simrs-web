package com.neurix.hris.transaksi.medicalrecord.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.User;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biayapengobatan.bo.BiayaPengobatanBo;
import com.neurix.hris.master.biayapengobatan.model.BiayaPengobatan;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.keluarga.bo.KeluargaBo;
import com.neurix.hris.master.keluarga.model.Keluarga;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.model.positionBagian;
import com.neurix.hris.master.strukturJabatan.bo.StrukturJabatanBo;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.medicalrecord.bo.MedicalRecordBo;
import com.neurix.hris.transaksi.medicalrecord.model.BuktiPengobatan;
import com.neurix.hris.transaksi.medicalrecord.model.MedicalRecord;
import com.neurix.hris.transaksi.medicalrecord.model.Pengobatan;
import com.neurix.hris.transaksi.training.model.Training;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class MedicalRecordAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(MedicalRecordAction.class);

    private MedicalRecord medicalRecord;
    private Pengobatan pengobatan;
    private BuktiPengobatan buktiPengobatan;
    private MedicalRecordBo medicalRecordBoProxy;
    private PositionBagianBo positionBagianBoProxy;

    private File fileUploadDoc;
    private String fileUploadDocContentType;
    private String fileUploadDocFileName;

    private String id;
    private String view;
    private String delete;
    private String project;
    private String tglFrom;
    private String tglTo;
    private String branchId;
    private String nip;
    private String bagianId;

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public File getFileUploadDoc() {
        return fileUploadDoc;
    }

    public void setFileUploadDoc(File fileUploadDoc) {
        this.fileUploadDoc = fileUploadDoc;
    }

    public String getFileUploadDocContentType() {
        return fileUploadDocContentType;
    }

    public void setFileUploadDocContentType(String fileUploadDocContentType) {
        this.fileUploadDocContentType = fileUploadDocContentType;
    }

    public String getFileUploadDocFileName() {
        return fileUploadDocFileName;
    }

    public void setFileUploadDocFileName(String fileUploadDocFileName) {
        this.fileUploadDocFileName = fileUploadDocFileName;
    }

    public BuktiPengobatan getBuktiPengobatan() {
        return buktiPengobatan;
    }

    public void setBuktiPengobatan(BuktiPengobatan buktiPengobatan) {
        this.buktiPengobatan = buktiPengobatan;
    }

    public Pengobatan getPengobatan() {
        return pengobatan;
    }

    public void setPengobatan(Pengobatan pengobatan) {
        this.pengobatan = pengobatan;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    private List<BiayaPengobatan> listOfComboBiayaPengobatan = new ArrayList<BiayaPengobatan>();

    public List<BiayaPengobatan> getListOfComboBiayaPengobatan() {
        return listOfComboBiayaPengobatan;
    }

    public void setListOfComboBiayaPengobatan(List<BiayaPengobatan> listOfComboBiayaPengobatan) {
        this.listOfComboBiayaPengobatan = listOfComboBiayaPengobatan;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MedicalRecordAction.logger = logger;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public MedicalRecordBo getMedicalRecordBoProxy() {
        return medicalRecordBoProxy;
    }

    public void setMedicalRecordBoProxy(MedicalRecordBo medicalRecordBoProxy) {
        this.medicalRecordBoProxy = medicalRecordBoProxy;
    }

    public PositionBagianBo getPositionBagianBoProxy() {
        return positionBagianBoProxy;
    }

    public void setPositionBagianBoProxy(PositionBagianBo positionBagianBoProxy) {
        this.positionBagianBoProxy = positionBagianBoProxy;
    }

    @Override
    public String add() {
        logger.info("[BiayaPengobatanAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        MedicalRecord medicalRecord = new MedicalRecord();
        String branchId = CommonUtil.userBranchLogin();
        medicalRecord.setBranchId(branchId);
        setMedicalRecord(medicalRecord);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultBuktiPengobatan");
        session.removeAttribute("listOfResultPengobatan");
        logger.info("[BiayaPengobatanAction.search] end process <<<");
        return "init_add";
    }

    public String initAdd() {
        logger.info("[BiayaPengobatanAction.search] start process >>>");

        logger.info("[BiayaPengobatanAction.search] end process <<<");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[BiayaPengobatanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MedicalRecord> listOfMedicalRecord = (List<MedicalRecord>) session.getAttribute("listOfResult");
        List<Pengobatan> listOfPengobatan = new ArrayList<Pengobatan>();
        List<BuktiPengobatan> listOfBuktiPengobatan = new ArrayList<BuktiPengobatan>();
        List<MedicalRecord> medicalRecordList = new ArrayList<MedicalRecord>();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        String id = getId();
        String delete = getDelete();
        if (listOfMedicalRecord != null){
            for (MedicalRecord medicalRecord : listOfMedicalRecord){
                if (id.equalsIgnoreCase(medicalRecord.getMedicalRecordId())){
                    medicalRecord.setMedicalRecordId(id);

                    if (medicalRecord.getTanggalBerobat() != null){
                        String stDate = df.format(medicalRecord.getTanggalBerobat());
                        medicalRecord.setStTanggalBerobat(stDate);
                    }

                    setMedicalRecord(medicalRecord);
                    medicalRecordList.remove(medicalRecord);
                    medicalRecordList.add(medicalRecord);
                }
            }

            Pengobatan pengobatan = new Pengobatan();
            pengobatan.setMedicalRecordId(id);
            pengobatan.setFlag("Y");
            try {
                listOfPengobatan = medicalRecordBoProxy.searchPengobatan(pengobatan);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "MedicalRecordAction.edit");
                } catch (GeneralBOException e1) {
                    logger.error("[MedicalRecordAction.edit] Error when saving error,", e1);
                }
                logger.error("[MedicalRecordAction.edit] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return "failure";
            }




            if (listOfPengobatan != null){
                for (Pengobatan listData : listOfPengobatan){
                    listData.setStatus("ACTIVE");

                    List<BuktiPengobatan> listDataBuktiPengobatan = new ArrayList<BuktiPengobatan>();
                    BuktiPengobatan buktiPengobatan = new BuktiPengobatan();
                    buktiPengobatan.setPengobatanId(listData.getPengobatanId());
                    buktiPengobatan.setFlag("Y");


                    try {
                        listDataBuktiPengobatan = medicalRecordBoProxy.searchBuktiPengobatan(buktiPengobatan);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.searchPengobatan");
                        } catch (GeneralBOException e1) {
                            logger.error("[TrainingAction.initApprove] Error when saving error,", e1);
                        }
                        logger.error("[TrainingAction.initApprove] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                        return "failure";
                    }
                    if (listDataBuktiPengobatan != null){
                        for (BuktiPengobatan listDataBukti : listDataBuktiPengobatan){
                            BuktiPengobatan add = new BuktiPengobatan();
                            add.setBuktiId(listDataBukti.getBuktiId());
                            add.setPengobatanId(listDataBukti.getPengobatanId());
                            add.setFileName(listDataBukti.getFileName());
                            add.setKeterangan(listDataBukti.getKeterangan());
                            add.setTipeUpload(listDataBukti.getTipeUpload());
                            add.setFlag(listDataBukti.getFlag());
                            add.setAction(listDataBukti.getAction());
                            add.setCreatedDate(listDataBukti.getCreatedDate());
                            add.setCreatedWho(listDataBukti.getCreatedWho());
                            add.setLastUpdate(listDataBukti.getLastUpdate());
                            add.setLastUpdateWho(listDataBukti.getLastUpdateWho());
                            add.setStatus("ACTIVE");
                            listOfBuktiPengobatan.add(add);
                        }
                    }
                }
            }

        } else {
            setMedicalRecord(new MedicalRecord());
        }

        session.removeAttribute("listOfResultPengobatan");
        session.setAttribute("listOfResultPengobatan",listOfPengobatan);
        session.removeAttribute("listOfResultBuktiPengobatan");
        session.setAttribute("listOfResultBuktiPengobatan",listOfBuktiPengobatan);
        session.removeAttribute("listOfResultMedical");
        session.setAttribute("listOfResultMedical",medicalRecordList);

        if("Y".equalsIgnoreCase(delete)){
            logger.info("[BiayaPengobatanAction.iniView] end process >>>");
            return "init_delete";
        }else {
            logger.info("[BiayaPengobatanAction.initApprove] end process >>>");
            return "init_edit";
        }
    }

    public String initEdit(){
        logger.info("[TrainingAction.initEdit] start process >>>");

        logger.info("[TrainingAction.initEdit] end process <<<");
        return "init_edit";
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
        logger.info("[BiayaPengobatanAction.save] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pengobatan> listOfResultPengobatan = (List<Pengobatan>) session.getAttribute("listOfResultPengobatan");
        List<BuktiPengobatan> listOfBuktiPengobatan = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        String thn = date.substring(0,4);
        String bln = date.substring(5,7);
        String id = date.substring(2,4);

        MedicalRecord medicalRecord = getMedicalRecord();
        boolean flag = false;
        if (medicalRecord != null){
            medicalRecord.setCreatedDate(updateTime);
            medicalRecord.setCreatedWho(userLogin);
            medicalRecord.setLastUpdate(updateTime);
            medicalRecord.setLastUpdateWho(userLogin);

            String stDate = medicalRecord.getStTanggalBerobat();
            Date dataDate = CommonUtil.convertToDate(stDate);

            medicalRecord.setTanggalBerobat(dataDate);
            medicalRecord.setFlag("Y");
            medicalRecord.setAction("C");

            if("Y".equalsIgnoreCase(medicalRecord.getFlagSuratJaminan())){
                String idJaminan = "";
                idJaminan = medicalRecordBoProxy.getNextSuratJaminanId();
                String sj = idJaminan+"/"+medicalRecord.getTipePengobatan()+"/"+bln+"/"+thn;
                medicalRecord.setNoSuratJaminan(sj);
            }
            medicalRecord.setMedicalRecordId("MR"+id);
            try {
                medicalRecordBoProxy.saveMedicalRecord(medicalRecord, listOfResultPengobatan);
                flag = true;
            } catch (GeneralBOException e){
                logger.error("[TrainingAction.save] Error when saving medicalrecord,", e);
                return "failure_save";
            }

            if (flag){
                if (listOfBuktiPengobatan != null){
                    List<BuktiPengobatan> listOfBuktiNew = new ArrayList<BuktiPengobatan>();
                    for (BuktiPengobatan listData : listOfBuktiPengobatan){
                        BuktiPengobatan add = new BuktiPengobatan();

                        add.setBuktiId(listData.getBuktiId());
                        add.setPengobatanId(listData.getPengobatanId());
                        add.setKeterangan(listData.getKeterangan());
                        add.setFileName(listData.getFileName());
                        add.setTipeUpload(listData.getTipeUpload());
                        add.setCreatedDate(updateTime);
                        add.setCreatedWho(userLogin);
                        add.setLastUpdate(updateTime);
                        add.setLastUpdateWho(userLogin);
                        add.setAdd(true);
                        listOfBuktiNew.add(add);
                    }
                    try {
                        medicalRecordBoProxy.saveBuktiPengobatan(listOfBuktiNew);
                        flag = true;
                    } catch (GeneralBOException e){
                        logger.error("[TrainingAction.save] Error when saving medicalrecord,", e);
                        return "failure_save";
                    }
                }
            }
        }
        logger.info("[BiayaPengobatanAction.save] end process <<<");
        return "success_add";
    }

    @Override
    public String search() {
        logger.info("[BiayaPengobatanAction.search] start process >>>");

        MedicalRecord search = getMedicalRecord();
        List<MedicalRecord> listOfSearch = new ArrayList();

        String userRole = CommonUtil.roleAsLogin();
        String userLoginId = CommonUtil.userIdLogin();
        if ("ADMIN".equalsIgnoreCase(userRole)){
            search.setAdmin(true);
        }else if ("Admin Bagian".equalsIgnoreCase(userRole)){
        } else {
            search.setNip(userLoginId);
        }

        if(!("Admin Bagian").equalsIgnoreCase(userRole)){
            try {
                listOfSearch = medicalRecordBoProxy.getByCriteria(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "MedicalRecordAction.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[GroupShiftAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[GroupShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                return ERROR;
            }
        }else{
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            PositionBagianBo positionBagianBo = (PositionBagianBo) ctx.getBean("positionBagianBoProxy");

            positionBagian searchBagian = new positionBagian();
            searchBagian.setBagianName(CommonUtil.userLogin());
            List<positionBagian> positionBagianList = positionBagianBo.getByCriteria(searchBagian);
            for (positionBagian bagian : positionBagianList){
                List<MedicalRecord> medicalRecordList = new ArrayList<>();
                List<Biodata> biodataList = biodataBo.getBiodataByBagian(null,null,bagian.getBagianId(),null);
                for (Biodata biodata : biodataList){
                    if(!("").equalsIgnoreCase(search.getNip())){
                        if (biodata.getNip().equalsIgnoreCase(search.getNip())){
                            try {
                                medicalRecordList = medicalRecordBoProxy.getByCriteria(search);
                            } catch (GeneralBOException e) {
                                Long logId = null;
                                try {
                                    logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "MedicalRecordAction.getByCriteria");
                                } catch (GeneralBOException e1) {
                                    logger.error("[GroupShiftAction.search] Error when saving error,", e1);
                                    return ERROR;
                                }
                                logger.error("[GroupShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                                return ERROR;
                            }
                            listOfSearch.addAll(medicalRecordList);
                        }
                    }else{
                        search.setNip(biodata.getNip());
                        try {
                            medicalRecordList = medicalRecordBoProxy.getByCriteria(search);
                        } catch (GeneralBOException e) {
                            Long logId = null;
                            try {
                                logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "MedicalRecordAction.getByCriteria");
                            } catch (GeneralBOException e1) {
                                logger.error("[GroupShiftAction.search] Error when saving error,", e1);
                                return ERROR;
                            }
                            logger.error("[GroupShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                            return ERROR;
                        }
                        listOfSearch.addAll(medicalRecordList);
                        search.setNip("");
                    }
                }
            }
            Comparator<MedicalRecord> comparator = new Comparator<MedicalRecord>() {
                @Override
                public int compare(MedicalRecord left, MedicalRecord right) {
                    String awal =left.getMedicalRecordId().replaceAll("[a-zA-Z]", "");
                    String akhir =right.getMedicalRecordId().replaceAll("[a-zA-Z]", "");
                    Long angka1 = Long.parseLong(awal);
                    Long angka2 = Long.parseLong(akhir);
                    return (int) (angka2-angka1);
                }
            };
            Collections.sort(listOfSearch, comparator);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearch);

        logger.info("[BiayaPengobatanAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[BiayaPengobatanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultMedical");
        logger.info("[BiayaPengobatanAction.initForm] end process >>>");
        return INPUT;
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

    //update 13-11/2018
    public List initComboPersonilBagian(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

        try {
            listOfUser = biodataBo.getListOfPersonilBagian(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfUser;
    }

    public List<Biodata> initComboRsKelas(String query, String status, String nip, String golonganId,String statusRawat) {
        logger.info("[MedicalRecord.initComboRsKelas] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
//        HttpSession session = WebContextFactory.get().getSession();
        try {
            listOfUser = biodataBo.getListOfRsKelas(query,status,nip,golonganId,statusRawat);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "biodataBo.getListOfRsKelas");
            } catch (GeneralBOException e1) {
                logger.error("[MedicalRecord.initComboRsKelas] Error when saving error,", e1);
            }
            logger.error("[MedicalRecord.initComboRsKelas] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[MedicalRecord.initComboRsKelas] end process <<<");

        return listOfUser;
    }


    public List<Keluarga> initComboKeluarga(String query, String id) {
        logger.info("[PermohonanLahanAction.initComboKeluarga] start process >>>");

        List<Keluarga> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeluargaBo keluargaBo = (KeluargaBo) ctx.getBean("keluargaBoProxy");
//        HttpSession session = WebContextFactory.get().getSession();


        try {
            listOfUser = keluargaBo.getComboKeluargaByNip(query,id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = keluargaBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfUser;
    }

    public String initComboBiayaPengobatan() {

        BiayaPengobatan biayaPengobatan = new BiayaPengobatan();
        biayaPengobatan.setFlag("Y");

        List<BiayaPengobatan> listOfResult = new ArrayList<BiayaPengobatan>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiayaPengobatanBo biayaPengobatanBo = (BiayaPengobatanBo) ctx.getBean("biayaPengobatanBoProxy");
        try {
            listOfResult = biayaPengobatanBo.getByCriteria(biayaPengobatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPengobatanBo.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboBiayaPengobatan.addAll(listOfResult);

        return "init_combo_biaya_pengobatan";
    }

    public String addBiaya(){
        logger.info("[BiayaPengobatanAction.initForm] start process >>>");
        Pengobatan pengobatan = new Pengobatan();
        String project = getProject();
        logger.info("[BiayaPengobatanAction.initForm] end process >>>");
        return "init_add_biaya";
    }
    public String saveToSession(String branchId,String nip,String nama,String status,String golongan,String tanggal,String tipeBerobat,String sendiri,String keluarga,String tipepengobatan,String rsId,String namarumahsakit,String kelaslayananrs,String kelaslayananrsName,String flagSuratJaminan,String keluargaName){
        logger.info("[MedicalRecord.saveToSession] start process >>>");
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBranchId(branchId);
        medicalRecord.setNip(nip);
        medicalRecord.setNamaBerobat(nama);
        medicalRecord.setStatusPegawai(status);
        medicalRecord.setGolonganId(golongan);
        medicalRecord.setTanggalBerobat(CommonUtil.convertToDate(tanggal));
        medicalRecord.setTipeOrangBerobat(tipeBerobat);
        medicalRecord.setSendiri(sendiri);
        medicalRecord.setKeluargaId(keluarga);
        medicalRecord.setTipePengobatan(tipepengobatan);
        medicalRecord.setRsId(rsId);
        medicalRecord.setNamaRumasSakit(namarumahsakit);
        medicalRecord.setRsKelasId(kelaslayananrs);
        medicalRecord.setRsKelasName(kelaslayananrsName);
        medicalRecord.setFlagSuratJaminan(flagSuratJaminan);
        medicalRecord.setNamaKeluarga(keluargaName);
        medicalRecordList.add(medicalRecord);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultMedical", medicalRecordList);
        logger.info("[MedicalRecord.saveToSession] end process >>>");
        return "00";
    }
    public String addBiayaEdit(){
        logger.info("[BiayaPengobatanAction.initForm] start process >>>");
        Pengobatan pengobatan = new Pengobatan();
        pengobatan.setProject("edit");

        logger.info("[BiayaPengobatanAction.initForm] end process >>>");
        return "init_add_biaya_edit";
    }

    public String saveBiayaPengobatan(String biayaPengobatanId, String jumlah, String project){

        boolean isNew = false;
        boolean flag = false;
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pengobatan> listOfResultPengobatan = (List<Pengobatan>) session.getAttribute("listOfResultPengobatan");
        List<Pengobatan> listOfResultPengobatanNew = new ArrayList<Pengobatan>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalRecordBo medicalRecordBo = (MedicalRecordBo) ctx.getBean("medicalRecordBoProxy");

        if (biayaPengobatanId != null){
            BigDecimal dJumlah = new BigDecimal(jumlah);
            if (listOfResultPengobatan != null){
                String id = "";
                id = medicalRecordBo.getNextPengobatanId();
                if (id!= null){
                    Pengobatan pengobatan = new Pengobatan();
                    pengobatan.setPengobatanId("P"+id);
                    pengobatan.setBiayaPengobatanId(biayaPengobatanId);

                    BiayaPengobatan biayaPengobatan = new BiayaPengobatan();
                    biayaPengobatan.setBiayaPengobatanId(biayaPengobatanId);
                    List<BiayaPengobatan> biayaPengobatans = new ArrayList<>();
                    BiayaPengobatanBo biayaPengobatanBo = (BiayaPengobatanBo) ctx.getBean("biayaPengobatanBoProxy");
                    biayaPengobatans = biayaPengobatanBo.getByCriteria(biayaPengobatan);

                    if(biayaPengobatans != null){
                        for (BiayaPengobatan listBiaya : biayaPengobatans){
                            pengobatan.setNamaBiayaPengobatan(listBiaya.getBiayaPengobatanName());
                        }
                    }

                    pengobatan.setJumlah(dJumlah);
                    pengobatan.setAdd(true);
                    pengobatan.setStatus("ACTIVE");
                    listOfResultPengobatan.add(pengobatan);
                    flag = true;
                }
            } else {
                String id = "";
                id = medicalRecordBo.getNextPengobatanId();
                if (id!= null){
                    Pengobatan pengobatan = new Pengobatan();
                    pengobatan.setPengobatanId("P"+id);
                    pengobatan.setBiayaPengobatanId(biayaPengobatanId);

                    BiayaPengobatan biayaPengobatan = new BiayaPengobatan();
                    biayaPengobatan.setBiayaPengobatanId(biayaPengobatanId);
                    List<BiayaPengobatan> biayaPengobatans = new ArrayList<>();
                    BiayaPengobatanBo biayaPengobatanBo = (BiayaPengobatanBo) ctx.getBean("biayaPengobatanBoProxy");
                    biayaPengobatans = biayaPengobatanBo.getByCriteria(biayaPengobatan);

                    if(biayaPengobatans != null){
                        for (BiayaPengobatan listBiaya : biayaPengobatans){
                            pengobatan.setNamaBiayaPengobatan(listBiaya.getBiayaPengobatanName());
                        }
                    }

                    pengobatan.setJumlah(dJumlah);
                    pengobatan.setAdd(true);
                    pengobatan.setStatus("ACTIVE");
                    listOfResultPengobatanNew.add(pengobatan);
                    isNew = true;
                    flag = true;
                }
            }
        }


        if (flag){
            if (isNew){
                session.removeAttribute("listOfResultPengobatan");
                session.setAttribute("listOfResultPengobatan", listOfResultPengobatanNew);
                if ("edit".equalsIgnoreCase(project)){
                    return "02";
                } else {
                    return "00";
                }

            } else {
                session.removeAttribute("listOfResultPengobatan");
                session.setAttribute("listOfResultPengobatan", listOfResultPengobatan);
                if ("edit".equalsIgnoreCase(project)){
                    return "02";
                } else {
                    return "00";
                }
            }
        } else {
            return "01";
        }
    }

    public String uploadFile(){
        logger.info("[TrainingAction.uploadFile] start process >>>");
        BuktiPengobatan bukti = new BuktiPengobatan();
        String id = getId();
        String project = getProject();
        bukti.setPengobatanId(id);

        if("edit".equalsIgnoreCase(project)){
            bukti.setProject(project);
            setBuktiPengobatan(bukti);
            logger.info("[TrainingAction.uploadFile] start process >>>");
            return "init_upload_edit";
        } else {
            setBuktiPengobatan(bukti);
            logger.info("[TrainingAction.uploadFile] start process >>>");
            return "init_upload";
        }
    }

    public String cetakSuratMedicalRecord(){
        logger.info("[MedicalRecordAction.cetakSuratMedicalRecord] end process >>>");

        String id = getId();
        String flag = getFlag();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFlag(flag);
        medicalRecord.setMedicalRecordId(id);
        List<MedicalRecord> medicalRecordList= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalRecordBo medicalRecordBo = (MedicalRecordBo) ctx.getBean("medicalRecordBoProxy");
        try {
            medicalRecordList = medicalRecordBo.getByCriteria(medicalRecord);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalRecordBo.saveErrorMessage(e.getMessage(), "medicalRecordBo.cetakSuratMedicalRecord");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.cetakSuratMedicalRecord] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.cetakSuratMedicalRecord] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (medicalRecordList != null){
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            Date dataDate = new java.sql.Date(updateTime.getTime());
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            String stDate = dt1.format(dataDate);
            String nama="";
            //get biaya pengobatan By Id
            Pengobatan pengobatan = new Pengobatan();
            pengobatan.setMedicalRecordId(id);
            pengobatan.setFlag("Y");
            List<Pengobatan> listData = new ArrayList<>();
            List<Pengobatan> listDataFinal = new ArrayList<>();
            listData = medicalRecordBo.searchPengobatan(pengobatan);
            BigDecimal jumlah = BigDecimal.valueOf(0);
            Integer nomor = 1;
            for (Pengobatan pengobatan1 : listData){
                pengobatan1.setNomor(String.valueOf(nomor));
                pengobatan1.setBiaya(CommonUtil.numbericFormat(pengobatan1.getJumlah(),"###,###"));
                listDataFinal.add(pengobatan1);
                jumlah = jumlah.add(pengobatan1.getJumlah());
                nomor++;
            }
            pengobatan = new Pengobatan();
            pengobatan.setNomor("");
            pengobatan.setBiayaPengobatanId("");
            pengobatan.setNamaBiayaPengobatan("");
            pengobatan.setBiaya("");
            pengobatan.setMedicalRecordId("");
            listDataFinal.add(pengobatan);

            pengobatan = new Pengobatan();
            pengobatan.setNomor("");
            pengobatan.setBiayaPengobatanId("Jumlah uang");
            pengobatan.setMedicalRecordId("");
            pengobatan.setNamaBiayaPengobatan(CommonUtil.angkaToTerbilang(jumlah.longValue()));
            pengobatan.setBiaya(CommonUtil.numbericFormat(jumlah,"###,###"));
            listDataFinal.add(pengobatan);
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listDataFinal);
            for (MedicalRecord medicalRecord1 : medicalRecordList){
                SimpleDateFormat st = new SimpleDateFormat("dd-MM-yyyy");
                String stTanggal = st.format(medicalRecord1.getTanggalBerobat());
                String pembuat = CommonUtil.userLogin();
                if (pembuat == null){
                    pembuat ="(________________)";
                }

                BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
                Biodata searchBiodata = new Biodata();
                searchBiodata.setNip(getNip());
                searchBiodata.setFlag("Y");
                Biodata biodata = new Biodata();
                biodata = biodataBo.detailBiodataSys(medicalRecord1.getNip());
                if (biodata!=null){
                    nama=biodata.getNamaPegawai();
                }

                reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
                reportParams.put("titleReport","LAPORAN MEDICAL RECORD");
                reportParams.put("medicalRecordId", id);
                reportParams.put("nama",nama);
                reportParams.put("jabatan",medicalRecord1.getPositionName());
                reportParams.put("divisi",medicalRecord1.getDivisiName());
                reportParams.put("unit",medicalRecord1.getBranchName());
                reportParams.put("tanggal",stTanggal);
                reportParams.put("pembuat",pembuat);
                reportParams.put("date", stDate);
                reportParams.put("itemDataSource", itemData);
            }
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = medicalRecordBo.saveErrorMessage(e.getMessage(), "cetakSuratMedicalRecord");
                } catch (GeneralBOException e1) {
                    logger.error("[medicalRecordAction.cetakSuratMedicalRecord] Error when downloading ,", e1);
                }
                logger.error("[medicalRecordAction.cetakSuratMedicalRecord] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
                return "failure_print";
            }
        } else {
            logger.error("[medicalRecordAction.cetakSuratMedicalRecord] Error when print report realiassi bibit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list notification detail is empty, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[medicalRecordAction.cetakSuratMedicalRecord] end process <<<");
        return "cetak_surat_medicalRecord";
    }
    public String saveUpload(){
        boolean isNew = false;
        boolean flag = false;
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BuktiPengobatan> listOfBuktiPengobatan = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");
        List<BuktiPengobatan> listOfBuktiPengobatanNew = new ArrayList<BuktiPengobatan>();



        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalRecordBo medicalRecordBo = (MedicalRecordBo) ctx.getBean("medicalRecordBoProxy");

        BuktiPengobatan buktiPengobatan = getBuktiPengobatan();
        String id = buktiPengobatan.getPengobatanId();
        File file1 = this.fileUploadDoc;
        String fileContentType = this.fileUploadDocContentType;
        String fileName = this.fileUploadDocFileName;
        String note = buktiPengobatan.getKeterangan();
        String project = buktiPengobatan.getProject();


        if (listOfBuktiPengobatan != null){
            if (file1 != null){
                String idBukti = "";
                idBukti = medicalRecordBo.getNextBuktiPembayaranId();
                BuktiPengobatan buktiNew = new BuktiPengobatan();
                buktiNew.setBuktiId("BT"+idBukti);

                File fileToCreate = null;
                String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD_DOC;

                if ("image/jpeg".equalsIgnoreCase(fileContentType)) {
                    String fileDocName = "IMG_" + buktiNew.getBuktiId() + "_" + fileName;
                    fileToCreate = new File(filePath, fileDocName);
                    try {
                        FileUtils.copyFile(file1, fileToCreate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buktiNew.setFileName(fileDocName);
                    buktiNew.setTipeUpload("IMG");
                }

                if ("application/pdf".equalsIgnoreCase(fileContentType)) {
                    String fileDocName = "PDF_" + buktiNew.getBuktiId() + "_" + fileName;
                    fileToCreate = new File(filePath, fileDocName);
                    try {
                        FileUtils.copyFile(file1, fileToCreate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buktiNew.setFileName(fileDocName);
                    buktiNew.setTipeUpload("PDF");
                }

                if ("image/png".equalsIgnoreCase(fileContentType)) {
                    String fileDocName = "IMG_" + buktiNew.getBuktiId() + "_" + fileName;
                    fileToCreate = new File(filePath, fileDocName);
                    try {
                        FileUtils.copyFile(file1, fileToCreate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buktiNew.setFileName(fileDocName);
                    buktiNew.setTipeUpload("IMG");
                }

                buktiNew.setPengobatanId(id);
                buktiNew.setKeterangan(note);
                buktiNew.setAdd(true);
                buktiNew.setStatus("ACTIVE");
                listOfBuktiPengobatan.add(buktiNew);
                flag = true;
            }
        } else {
            BuktiPengobatan buktiPengobatan2 = new BuktiPengobatan();
            String idBukti = "";
            if (file1 != null){
                idBukti = medicalRecordBo.getNextBuktiPembayaranId();
                buktiPengobatan2.setBuktiId("BT"+idBukti);

                File fileToCreate = null;
                String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD_DOC;

                if ("image/jpeg".equalsIgnoreCase(fileContentType)) {
                    String fileDocName = "IMG_" + buktiPengobatan2.getBuktiId() + "_" + fileName;
                    fileToCreate = new File(filePath, fileDocName);
                    try {
                        FileUtils.copyFile(file1, fileToCreate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buktiPengobatan2.setFileName(fileDocName);
                    buktiPengobatan2.setTipeUpload("IMG");
                }

                if ("application/pdf".equalsIgnoreCase(fileContentType)) {
                    String fileDocName = "PDF_" + buktiPengobatan2.getBuktiId() + "_" + fileName;
                    fileToCreate = new File(filePath, fileDocName);
                    try {
                        FileUtils.copyFile(file1, fileToCreate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buktiPengobatan2.setFileName(fileDocName);
                    buktiPengobatan2.setTipeUpload("PDF");
                }

                if ("image/png".equalsIgnoreCase(fileContentType)) {
                    String fileDocName = "IMG_" + buktiPengobatan2.getBuktiId() + "_" + fileName;
                    fileToCreate = new File(filePath, fileDocName);
                    try {
                        FileUtils.copyFile(file1, fileToCreate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buktiPengobatan2.setFileName(fileDocName);
                    buktiPengobatan2.setTipeUpload("IMG");
                }

                buktiPengobatan2.setPengobatanId(id);
                buktiPengobatan2.setKeterangan(note);
                buktiPengobatan2.setAdd(true);
                buktiPengobatan2.setStatus("ACTIVE");
                listOfBuktiPengobatanNew.add(buktiPengobatan2);
                flag = true;
                isNew = true;
            }
        }



        if (flag){
            if (isNew){
                session.removeAttribute("listOfResultBuktiPengobatan");
                session.setAttribute("listOfResultBuktiPengobatan", listOfBuktiPengobatanNew);
            } else {
                session.removeAttribute("listOfResultBuktiPengobatan");
                session.setAttribute("listOfResultBuktiPengobatan", listOfBuktiPengobatan);
            }
        }

        if ("edit".equalsIgnoreCase(project)){
            List<MedicalRecord> listMedicalRecord = (List<MedicalRecord>) session.getAttribute("listOfResultMedical");
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            if (listMedicalRecord != null){
                for (MedicalRecord medicalRecord :listMedicalRecord){
                    if (medicalRecord.getTanggalBerobat() != null){
                        String stDate = df.format(medicalRecord.getTanggalBerobat());
                        medicalRecord.setStTanggalBerobat(stDate);
                    }
                    setMedicalRecord(medicalRecord);
                }
            }
            return "save_upload_edit";
        } else {
            List<MedicalRecord> listMedicalRecord = (List<MedicalRecord>) session.getAttribute("listOfResultMedical");
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            if (listMedicalRecord != null){
                for (MedicalRecord medicalRecord :listMedicalRecord){
                    if (medicalRecord.getTanggalBerobat() != null){
                        String stDate = df.format(medicalRecord.getTanggalBerobat());
                        medicalRecord.setStTanggalBerobat(stDate);
                    }
                    setMedicalRecord(medicalRecord);
                }
            }
            return "save_upload";
        }
    }

    public String initApprove(){
        logger.info("[BiayaPengobatanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MedicalRecord> listOfMedicalRecord = (List<MedicalRecord>) session.getAttribute("listOfResult");
        List<Pengobatan> listOfPengobatan = new ArrayList<Pengobatan>();
        List<BuktiPengobatan> listOfBuktiPengobatan = new ArrayList<BuktiPengobatan>();

        String id = getId();
        String view = getView();
        if (listOfMedicalRecord != null){
            for (MedicalRecord medicalRecord : listOfMedicalRecord){
                if (id.equalsIgnoreCase(medicalRecord.getMedicalRecordId())){
                    medicalRecord.setMedicalRecordId(id);
                    setMedicalRecord(medicalRecord);
                }
            }

            Pengobatan pengobatan = new Pengobatan();
            pengobatan.setMedicalRecordId(id);
            pengobatan.setFlag("Y");
            try {
                listOfPengobatan = medicalRecordBoProxy.searchPengobatan(pengobatan);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.searchPengobatan");
                } catch (GeneralBOException e1) {
                    logger.error("[TrainingAction.initApprove] Error when saving error,", e1);
                }
                logger.error("[TrainingAction.initApprove] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return "failure";
            }

            if (listOfPengobatan != null){
                for (Pengobatan listData : listOfPengobatan){
                    List<BuktiPengobatan> listDataBuktiPengobatan = new ArrayList<BuktiPengobatan>();
                    BuktiPengobatan buktiPengobatan = new BuktiPengobatan();
                    buktiPengobatan.setPengobatanId(listData.getPengobatanId());
                    buktiPengobatan.setFlag("Y");

                    try {
                        listDataBuktiPengobatan = medicalRecordBoProxy.searchBuktiPengobatan(buktiPengobatan);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.searchPengobatan");
                        } catch (GeneralBOException e1) {
                            logger.error("[TrainingAction.initApprove] Error when saving error,", e1);
                        }
                        logger.error("[TrainingAction.initApprove] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                        return "failure";
                    }
                    if (listDataBuktiPengobatan != null){
                        for (BuktiPengobatan listDataBukti : listDataBuktiPengobatan){
                            BuktiPengobatan add = new BuktiPengobatan();
                            add.setBuktiId(listDataBukti.getBuktiId());
                            add.setPengobatanId(listDataBukti.getPengobatanId());
                            add.setFileName(listDataBukti.getFileName());
                            add.setKeterangan(listDataBukti.getKeterangan());
                            add.setTipeUpload(listDataBukti.getTipeUpload());
                            add.setFlag(listDataBukti.getFlag());
                            add.setAction(listDataBukti.getAction());
                            add.setCreatedDate(listDataBukti.getCreatedDate());
                            add.setCreatedWho(listDataBukti.getCreatedWho());
                            add.setLastUpdate(listDataBukti.getLastUpdate());
                            add.setLastUpdateWho(listDataBukti.getLastUpdateWho());
                            listOfBuktiPengobatan.add(add);
                        }
                    }
                }
            }

        } else {
            setMedicalRecord(new MedicalRecord());
        }

        session.removeAttribute("listOfResultPengobatan");
        session.setAttribute("listOfResultPengobatan",listOfPengobatan);
        session.removeAttribute("listOfResultBuktiPengobatan");
        session.setAttribute("listOfResultBuktiPengobatan",listOfBuktiPengobatan);

        if("Y".equalsIgnoreCase(view)){
            logger.info("[BiayaPengobatanAction.iniView] end process >>>");
            return "init_view_data";
        }else {
            logger.info("[BiayaPengobatanAction.initApprove] end process >>>");
            return "init_approve";
        }
    }

    public String saveApprove(){
        logger.info("[TrainingAction.saveApprove] start process >>>");
        String userLogin = CommonUtil.userLogin();
        String idUser = CommonUtil.userIdLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        MedicalRecord medicalRecord = getMedicalRecord();
        medicalRecord.setLastUpdate(updateTime);
        medicalRecord.setLastUpdateWho(userLogin);
        medicalRecord.setApproveDate(updateTime);
        medicalRecord.setApprovedName(userLogin);
        medicalRecord.setApprovedId(idUser);

        if ("N".equalsIgnoreCase(medicalRecord.getFlagApprove())){
            medicalRecord.setApproved("N");
        } else {
            medicalRecord.setApproved("Y");
        }
        try {
            medicalRecordBoProxy.saveApprove(medicalRecord);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "medicalRecordBo.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        logger.info("[TrainingAction.saveApprove] end process >>>");
        return "save_approve";
    }

    public String viewDoc(){
        logger.info("[BiayaPengobatanAction.viewDoc] start process >>>");
        String id = getId();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BuktiPengobatan> listOfBukti = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");

        boolean isImg = false;

        for (BuktiPengobatan listData : listOfBukti){
            if (id.equalsIgnoreCase(listData.getBuktiId())){
                if ("IMG".equalsIgnoreCase(listData.getTipeUpload())){
                    listData.setFilePath("/hris/pages/upload/doc/"+listData.getFileName());
                    isImg = true;
                }
                if ("PDF".equalsIgnoreCase(listData.getTipeUpload())){
                    listData.setFilePath("/hris/pages/upload/doc/"+listData.getFileName());
                }
                setBuktiPengobatan(listData);
            }
        }

        logger.info("[BiayaPengobatanAction.viewDoc] start process >>>");
        if (isImg){
            return "init_view_img";
        } else {
            return "init_view_pdf";
        }
    }

    public String editAddBiayaPengobatan(){
        logger.info("[TrainingAction.editAddBiayaPengobatan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pengobatan> listOfPengobatan = (List<Pengobatan>) session.getAttribute("listOfResultPengobatan");

        String id = getId();
        String delete = getDelete();
        String project = getProject();

        for (Pengobatan listPengobatan : listOfPengobatan){
            if (id.equalsIgnoreCase(listPengobatan.getPengobatanId())){

                if ("Y".equalsIgnoreCase(delete)){
                    listPengobatan.setTipe("delete");
                } else {
                    listPengobatan.setTipe("edit");
                }
                setPengobatan(listPengobatan);
            }
        }

        if ("edit".equalsIgnoreCase(project)){
            logger.info("[TrainingAction.editAddBiayaPengobatan] end process >>>");
            return "init_edit_pengobatan_edit";
        } else {
            logger.info("[TrainingAction.editAddBiayaPengobatan] end process >>>");
            return "init_edit_pengobatan";
        }
    }

    public String saveEditBiayaPengobatan(String id,String biayaId, String stJumlah, String tipe){
        logger.info("[TrainingAction.saveEditBiayaPengobatan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pengobatan> listOfPengobatan = (List<Pengobatan>) session.getAttribute("listOfResultPengobatan");

        BigDecimal jumlah = new BigDecimal(stJumlah);

        boolean flag = false;
        for(Pengobatan listPengobatan : listOfPengobatan){
            if (id.equalsIgnoreCase(listPengobatan.getPengobatanId())){

                listPengobatan.setPengobatanId(id);
                listPengobatan.setJumlah(jumlah);
                listPengobatan.setBiayaPengobatanId(biayaId);

                BiayaPengobatan biayaPengobatan = new BiayaPengobatan();
                biayaPengobatan.setBiayaPengobatanId(biayaId);
                List<BiayaPengobatan> biayaPengobatans = new ArrayList<>();
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                BiayaPengobatanBo biayaPengobatanBo = (BiayaPengobatanBo) ctx.getBean("biayaPengobatanBoProxy");
                biayaPengobatans = biayaPengobatanBo.getByCriteria(biayaPengobatan);

                if(biayaPengobatans != null){
                    for (BiayaPengobatan listBiaya : biayaPengobatans){
                        listPengobatan.setNamaBiayaPengobatan(listBiaya.getBiayaPengobatanName());                    }
                }

                if ("delete".equalsIgnoreCase(tipe)){
                    listOfPengobatan.remove(listPengobatan);
                    flag = true;
                    break;
                } else {
                    listOfPengobatan.remove(listPengobatan);
                    listOfPengobatan.add(listPengobatan);
                    flag = true;
                }
            }
        }

        logger.info("[TrainingAction.saveEditBiayaPengobatan] end process >>>");

        if (flag){
            session.removeAttribute("listOfResultPengobatan");
            session.setAttribute("listOfResultPengobatan",listOfPengobatan);
            return "00";
        } else {
            return "01";
        }
    }

    public String saveDeleteBukti(String pengobatanId,String keterangan, String buktiId, String fileName){
        logger.info("[TrainingAction.saveEditBiayaPengobatan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BuktiPengobatan> listOfPengobatan = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");

//        Double jumlah = Double.parseDouble(stJumlah);

        boolean flag = false;
        for(BuktiPengobatan listPengobatan : listOfPengobatan){
            if (buktiId.equalsIgnoreCase(listPengobatan.getBuktiId())){

                listPengobatan.setBuktiId(buktiId);
                listPengobatan.setFileName(fileName);
                listPengobatan.setKeterangan(keterangan);
                listPengobatan.setPengobatanId(pengobatanId);
                listPengobatan.setStatus("NOT ACTIVE");
                listPengobatan.setFlag("N");

                listOfPengobatan.remove(listPengobatan);
                listOfPengobatan.add(listPengobatan);
                flag = true;
                break;
            }
        }

        logger.info("[TrainingAction.saveEditBiayaPengobatan] end process >>>");

        if (flag){
            session.removeAttribute("listOfResultBuktiPengobatan");
            session.setAttribute("listOfResultBuktiPengobatan",listOfPengobatan);
            return "00";
        } else {
            return "01";
        }
    }


    public String deleteAddBukti(){
        logger.info("[TrainingAction.deleteAddBukti] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BuktiPengobatan> listOfBuktiPengobatan = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");

        String id = getId();
        String delete = getDelete();
        String project = getProject();

        for (BuktiPengobatan listData : listOfBuktiPengobatan){
            if (id.equalsIgnoreCase(listData.getBuktiId())){
                setBuktiPengobatan(listData);
            }
        }

        if ("edit".equalsIgnoreCase(project)){
            logger.info("[TrainingAction.deleteAddBukti] end process >>>");
            return "delete_bukti_edit";
        } else {
            logger.info("[TrainingAction.deleteAddBukti] end process >>>");
            return "delete_add_bukti";
        }
    }

    public String saveDeleteBukti(){
        logger.info("[TrainingAction.deleteAddBukti] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BuktiPengobatan> listOfBuktiPengobatan = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");

        BuktiPengobatan buktiPengobatan = getBuktiPengobatan();
        String id = buktiPengobatan.getBuktiId();

        for (BuktiPengobatan listData : listOfBuktiPengobatan){
            if (id.equalsIgnoreCase(listData.getBuktiId())){
                listOfBuktiPengobatan.remove(listData);
                break;
            }
        }

        session.removeAttribute("listOfResultBuktiPengobatan");
        session.setAttribute("listOfResultBuktiPengobatan",listOfBuktiPengobatan);

        logger.info("[TrainingAction.deleteAddBukti] end process >>>");
        return "save_delete_bukti";
    }

    public String saveDeleteMedicalRecord(){
        logger.info("[TrainingAction.saveDeleteMedicalRecord] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pengobatan> listOfResultPengobatan = (List<Pengobatan>) session.getAttribute("listOfResultPengobatan");
        List<BuktiPengobatan> listOfBuktiPengobatan = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");

        boolean saved = false;
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        MedicalRecord medicalRecord = getMedicalRecord();
        medicalRecord.setLastUpdate(updateTime);
        medicalRecord.setLastUpdateWho(userLogin);
        medicalRecord.setFlag("N");
        medicalRecord.setAction("U");

        for (Pengobatan pengobatan :listOfResultPengobatan){
            pengobatan.setFlag("N");
            pengobatan.setAction("U");
            pengobatan.setLastUpdate(updateTime);
            pengobatan.setLastUpdateWho(userLogin);
        }

        try {
            medicalRecordBoProxy.saveUpdateMedicalRecord(medicalRecord, listOfResultPengobatan);
            saved = true;
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "medicalRecordBo.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (saved){
            if (listOfBuktiPengobatan != null){
                for (BuktiPengobatan buktiPengobatan : listOfBuktiPengobatan){
                    buktiPengobatan.setLastUpdate(updateTime);
                    buktiPengobatan.setLastUpdateWho(userLogin);
                    buktiPengobatan.setFlag("N");
                    buktiPengobatan.setAction("U");
                }
                try {
                    medicalRecordBoProxy.saveBuktiPengobatan(listOfBuktiPengobatan);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "medicalRecordBo.saveApprove");
                    } catch (GeneralBOException e1) {
                        logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
                    }
                    logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                    return "failure";
                }
            }
        }

        logger.info("[TrainingAction.saveDeleteMedicalRecord] end process >>>");
        return "success_delete";
    }

    public String saveAddPengobatanEdit(){
        return "00";
    }

    public String saveEditPengobatanEdit(String id,String biayaId, String stJumlah, String tipe){
        logger.info("[TrainingAction.saveEditBiayaPengobatan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pengobatan> listOfPengobatan = (List<Pengobatan>) session.getAttribute("listOfResultPengobatan");
        List<BuktiPengobatan> listOfBuktiPengobatan = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");


        BigDecimal jumlah = new BigDecimal(stJumlah);

        boolean flag = false;
        boolean found = false;
        for(Pengobatan listPengobatan : listOfPengobatan){
            if (id.equalsIgnoreCase(listPengobatan.getPengobatanId())){

                listPengobatan.setPengobatanId(id);
                listPengobatan.setJumlah(jumlah);
                listPengobatan.setBiayaPengobatanId(biayaId);

                BiayaPengobatan biayaPengobatan = new BiayaPengobatan();
                biayaPengobatan.setBiayaPengobatanId(biayaId);
                List<BiayaPengobatan> biayaPengobatans = new ArrayList<>();
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                BiayaPengobatanBo biayaPengobatanBo = (BiayaPengobatanBo) ctx.getBean("biayaPengobatanBoProxy");
                biayaPengobatans = biayaPengobatanBo.getByCriteria(biayaPengobatan);

                if(biayaPengobatans != null){
                    for (BiayaPengobatan listBiaya : biayaPengobatans){
                        listPengobatan.setNamaBiayaPengobatan(listBiaya.getBiayaPengobatanName());
                    }
                }
                if ("delete".equalsIgnoreCase(tipe)){
                    for (BuktiPengobatan listData : listOfBuktiPengobatan){
                        if (id.equalsIgnoreCase(listData.getPengobatanId())){
                            if ("N".equalsIgnoreCase(listData.getFlag())){
                                listPengobatan.setFlag("N");
                                listPengobatan.setStatus("NOT ACTIVE");
                                found = false;
                            } else {
                                found = true;
                                break;
                            }
                        } else {
                            listPengobatan.setFlag("N");
                            listPengobatan.setStatus("NOT ACTIVE");
                        }
                    }
                    listPengobatan.setFlagYes(false);
                    listPengobatan.setFlag("N");
                    listPengobatan.setStatus("NOT ACTIVE");
                }
                flag = true;
            }
        }

        logger.info("[TrainingAction.saveEditBiayaPengobatan] end process >>>");
        if (flag){
            if (found){
                return "02";
            } else {
                session.removeAttribute("listOfResultPengobatan");
                session.setAttribute("listOfResultPengobatan",listOfPengobatan);
                return "00";
            }
        } else {
            return "01";
        }
    }

    public String saveDeleteBuktiEdit(){
        logger.info("[TrainingAction.deleteAddBukti] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BuktiPengobatan> listOfBuktiPengobatan = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");

        BuktiPengobatan buktiPengobatan = getBuktiPengobatan();
        String id = buktiPengobatan.getBuktiId();

        for (BuktiPengobatan listData : listOfBuktiPengobatan){
            if (id.equalsIgnoreCase(listData.getBuktiId())){
                listData.setFlag("N");
                listData.setStatus("NOT ACTIVE");
                break;
            }
        }

        logger.info("[TrainingAction.deleteAddBukti] end process >>>");
        return "delete_bukti_edit";
    }

    public String saveEdit(){
        logger.info("[TrainingAction.saveEdit] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pengobatan> listOfResultPengobatan = (List<Pengobatan>) session.getAttribute("listOfResultPengobatan");
        List<BuktiPengobatan> listOfBuktiPengobatan = (List<BuktiPengobatan>) session.getAttribute("listOfResultBuktiPengobatan");

        boolean saved = false;
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        String thn = date.substring(0,4);
        String bln = date.substring(5,7);
        String id = date.substring(2,4);

        MedicalRecord medicalRecord = getMedicalRecord();

        String stDate = medicalRecord.getStTanggalBerobat();
        Date dataDate = CommonUtil.convertToDate(stDate);

        medicalRecord.setTanggalBerobat(dataDate);
        medicalRecord.setLastUpdate(updateTime);
        medicalRecord.setLastUpdateWho(userLogin);
        medicalRecord.setFlag("Y");
        medicalRecord.setAction("U");
        medicalRecord.setEdit(true);

        for (Pengobatan pengobatan :listOfResultPengobatan){
            if (pengobatan.isAdd()){
                pengobatan.setFlag("Y");
                pengobatan.setAction("C");
                pengobatan.setLastUpdate(updateTime);
                pengobatan.setLastUpdateWho(userLogin);
                pengobatan.setCreatedDate(updateTime);
                pengobatan.setCreatedWho(userLogin);
            } else if("N".equalsIgnoreCase(pengobatan.getFlag())){
                pengobatan.setFlag(pengobatan.getFlag());
                pengobatan.setAction("U");
                pengobatan.setLastUpdate(updateTime);
                pengobatan.setLastUpdateWho(userLogin);
            }else {
                pengobatan.setFlag("Y");
                pengobatan.setAction("U");
                pengobatan.setLastUpdate(updateTime);
                pengobatan.setLastUpdateWho(userLogin);
            }
        }

        if("Y".equalsIgnoreCase(medicalRecord.getFlagSuratJaminan())){
            String idJaminan = "";
            idJaminan = medicalRecordBoProxy.getNextSuratJaminanId();
            String sj = idJaminan+"/"+medicalRecord.getTipePengobatan()+"/"+bln+"/"+thn;
            medicalRecord.setNoSuratJaminan(sj);
        }

        try {
            medicalRecordBoProxy.saveUpdateMedicalRecord(medicalRecord, listOfResultPengobatan);
            saved = true;
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "medicalRecordBo.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (saved){
            if (listOfBuktiPengobatan != null){
                for (BuktiPengobatan buktiPengobatan : listOfBuktiPengobatan){
                    if (buktiPengobatan.isAdd()){
                        buktiPengobatan.setLastUpdate(updateTime);
                        buktiPengobatan.setCreatedWho(userLogin);
                        buktiPengobatan.setCreatedDate(updateTime);
                        buktiPengobatan.setLastUpdateWho(userLogin);
                        buktiPengobatan.setFlag("Y");
                        buktiPengobatan.setAction("C");
                    } else {
                        buktiPengobatan.setLastUpdate(updateTime);
                        buktiPengobatan.setLastUpdateWho(userLogin);
                        buktiPengobatan.setFlag(buktiPengobatan.getFlag());
                        buktiPengobatan.setAction("U");
                    }

                }
                try {
                    medicalRecordBoProxy.saveBuktiPengobatan(listOfBuktiPengobatan);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "medicalRecordBo.saveApprove");
                    } catch (GeneralBOException e1) {
                        logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
                    }
                    logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                    return "failure";
                }
            }
        }

        logger.info("[TrainingAction.saveEdit] end process >>>");
        return "success_edit";
    }

    public String printSuratJaminan(){
        logger.info("[TrainingAction.saveEdit] end process >>>");

        String id = getId();
        MedicalRecord medicalRecord = new MedicalRecord();

        try {
            medicalRecord = medicalRecordBoProxy.getPrintMedicalRecord(id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "medicalRecordBo.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (medicalRecord != null){

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            Date dataDate = new java.sql.Date(updateTime.getTime());
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            String stDate = dt1.format(dataDate);
            String namaBerobat = "";
            if(medicalRecord.getNamaBerobat() == null){
                namaBerobat = "-";
            }
            else{
                namaBerobat = medicalRecord.getNamaBerobat();
            }
            String paragraft1 = "Dengan ini kami beritahukan bahwa pasien atas nama <b>"+ medicalRecord.getNamaPasien()+
                    "</b> adalah karyawan kami di Kantor Direksi PT PG - <b>"+medicalRecord.getBranchAddress()+
                    "</b>. Oleh karena itu semua biaya pengobatan dan perawatan ybs selama di <b>"+medicalRecord.getNamaRumasSakit() +
                    "</b> menjadi Beban Perusahaan kami"+"\n\nDan perlu kami tambahkan bahwa ybs berhak mendapatkan perawatan di <b>"
                    + medicalRecord.getKelas()+"<b/>";
            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("medicalRecordId", id);
            reportParams.put("namaPasien", medicalRecord.getNamaPasien());
            reportParams.put("namaRs", medicalRecord.getNamaRumasSakit());
            reportParams.put("namaBerobat", namaBerobat);
            reportParams.put("alamat", medicalRecord.getAlamat());
            reportParams.put("kelas", medicalRecord.getKelas());
            reportParams.put("noSuratJaminan", medicalRecord.getNoSuratJaminan());
            reportParams.put("branchName", medicalRecord.getBranchName());
            reportParams.put("branchAddress", medicalRecord.getBranchAddress());
            reportParams.put("kabagSDM", medicalRecord.getKabagSdmName());
            reportParams.put("date", stDate);
            reportParams.put("paragraft1", paragraft1);
//            reportParams.put("paragraft2", paragraft2);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "printSuratJaminan");
                } catch (GeneralBOException e1) {
                    logger.error("[TrainingAction.printSuratJaminan] Error when downloading ,", e1);
                }
                logger.error("[TrainingAction.printReport] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
                return "failure_print";
            }

        } else {
            logger.error("[TrainingAction.printSuratJaminan] Error when print report realiassi bibit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list notification detail is empty, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[TrainingAction.printSuratJaminan] end process <<<");
        return "print_surat_jaminan";
    }

    public String koreksi(String medicalRecordId,String keterangan) {
        logger.info("[medicalRecordAction.koreksi] start process >>>");

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setLastUpdateWho(CommonUtil.userIdLogin());
        medicalRecord.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        medicalRecord.setApproved("N");
        medicalRecord.setFlagKoreksi("Y");
        medicalRecord.setApproved(null);
        medicalRecord.setKeteranganKoreksi(keterangan);
        medicalRecord.setMedicalRecordId(medicalRecordId);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MedicalRecordBo medicalRecordBo = (MedicalRecordBo) ctx.getBean("medicalRecordBoProxy");
        try {
            medicalRecordBo.koreksi(medicalRecord);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalRecordBo.saveErrorMessage(e.getMessage(), "medicalRecordBo.koreksi");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.koreksi] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.koreksi] Error when ," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[medicalRecordAction.koreksi] end process <<<");

        return "01";
    }

    public String printReportRekapitulasiMedical(){
        logger.info("[MedicalRecordAction.printReportRekapitulasiMedical] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MedicalRecord> result = new ArrayList<>();
        List<Biodata> biodataList= new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        BranchBo branchBo= (BranchBo) ctx.getBean("branchBoProxy");
        PositionBagianBo positionBagianBo= (PositionBagianBo) ctx.getBean("positionBagianBoProxy");
        Branch branch = new Branch();
        positionBagian positionBagian = new positionBagian();
        biodataList = biodataBo.getBiodataforAbsensi(getBranchId(),"",getBagianId(),getNip());

        for (Biodata biodata : biodataList){
            List<MedicalRecord> listData = new ArrayList();
            MedicalRecord search = new MedicalRecord();
            search.setFlag("Y");
            search.setStTanggalDari(getTglFrom());
            search.setStTanggalSampai(getTglTo());
            search.setNip(biodata.getNip());
            try {
                listData = medicalRecordBoProxy.getMedicalRecordUser(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "MedicalRecordAction.printReportRekapitulasiMedical");
                } catch (GeneralBOException e1) {
                    logger.error("[MedicalRecordAction.printReportRekapitulasiMedical] Error when saving error,", e1);
                }
                logger.error("[MedicalRecordAction.printReportRekapitulasiMedical] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
            Long jumlah= 0L;
            if (listData.size()!=0){
                for (MedicalRecord medicalRecord: listData ){
                    jumlah= jumlah + Long.valueOf(medicalRecord.getJumlahBiaya());
                }

                MedicalRecord hasilAkhir = new MedicalRecord();
                hasilAkhir.setJumlahBiaya(String.valueOf(jumlah));
                hasilAkhir.setNama(biodata.getNamaPegawai());
                hasilAkhir.setNip(biodata.getNip());
                hasilAkhir.setBagian(biodata.getBagianName());

                result.add(hasilAkhir);
            }
        }

        if (!("").equalsIgnoreCase(getBranchId())){
            try {
                branch = branchBo.getBranchById(getBranchId(),"Y");
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "MedicalRecordAction.printReportRekapitulasiMedical");
                } catch (GeneralBOException e1) {
                    logger.error("[MedicalRecordAction.printReportRekapitulasiMedical] Error when saving error,", e1);
                }
                logger.error("[MedicalRecordAction.printReportRekapitulasiMedical] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
        }
        if (!("").equalsIgnoreCase(getBagianId())){
            try {
                positionBagian = positionBagianBo.getBagianById(getBagianId(),"Y");
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "strukturJabatanBo.getBagianById");
                } catch (GeneralBOException e1) {
                    logger.error("[MedicalRecordAction.printReportRekapitulasiMedical] Error when saving error,", e1);
                }
                logger.error("[MedicalRecordAction.printReportRekapitulasiMedical] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
        }

        JRBeanCollectionDataSource itemData;


        String bagianPegawai="";
        Long jumlah= 0L;
        Long total= 0L;
        int a = 1;
        int x = 1;
        List<MedicalRecord> forReport = new ArrayList<>();
        for(MedicalRecord medicalRecord: result) {
            if (!bagianPegawai.equalsIgnoreCase(medicalRecord.getBagian())){
                MedicalRecord tmp;
                if (x!=1){
                    tmp = new MedicalRecord();
                    tmp.setNo("");
                    tmp.setNip("");
                    tmp.setNama("<b>JUMLAH</b>");
                    tmp.setStTanggalBerobat("");
                    tmp.setJumlahBiaya("<b>"+CommonUtil.numbericFormat(BigDecimal.valueOf(jumlah),"###,###")+"</b>");

                    total=total+jumlah;
                    jumlah=0L;
                    forReport.add(tmp);
                }

                tmp = new MedicalRecord();
                tmp.setNo("");
                tmp.setNip(medicalRecord.getBagian());
                tmp.setNama("");
                tmp.setStTanggalBerobat("");
                tmp.setJumlahBiaya("");
                forReport.add(tmp);
                bagianPegawai = medicalRecord.getBagian();
                a=1;

                x++;
            }
            if (bagianPegawai.equalsIgnoreCase(medicalRecord.getBagian())) {
                jumlah=jumlah+Long.valueOf(medicalRecord.getJumlahBiaya());

                medicalRecord.setNo(String.valueOf(a));
                medicalRecord.setJumlahBiaya(CommonUtil.numbericFormat(new BigDecimal(medicalRecord.getJumlahBiaya()),"###,###"));
                forReport.add(medicalRecord);
                a++;
            }
        }

        // add jumlah di akhir
        MedicalRecord tmp = new MedicalRecord();
        tmp.setNo("");
        tmp.setNip("");
        tmp.setNama("<b>JUMLAH</b>");
        tmp.setStTanggalBerobat("");
        tmp.setJumlahBiaya("<b>"+CommonUtil.numbericFormat(BigDecimal.valueOf(jumlah),"###,###")+"</b>");

        forReport.add(tmp);

        //add total seluruhnya di akhir
        total= total+jumlah;
        tmp = new MedicalRecord();
        tmp.setNo("");
        tmp.setNip("");
        tmp.setNama("<b>TOTAL SELURUHNYA</b>");
        tmp.setStTanggalBerobat("");
        tmp.setJumlahBiaya("<b>"+CommonUtil.numbericFormat(BigDecimal.valueOf(total),"###,###")+"</b>");

        forReport.add(tmp);

        itemData = new JRBeanCollectionDataSource(forReport);
        String bagian="-",unit="-";
        if (!("").equalsIgnoreCase(getBagianId())){
            bagian=positionBagian.getBagianName();
        }
        if (branch.getBranchName()!=null){
            unit=branch.getBranchName();
        }

        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport","REPORT REKAP MEDICAL");
        reportParams.put("bagian",bagian);
        reportParams.put("unit",unit);
        reportParams.put("tanggalDari",getTglFrom());
        reportParams.put("tanggalSelesai",getTglTo());
        reportParams.put("date", stDate);
        reportParams.put("itemDataSource", itemData);
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "printReport");
            } catch (GeneralBOException e1) {
                logger.error("[MedicalRecordAction.printReportRekapitulasiMedical] Error when downloading ,", e1);
            }
            logger.error("[MedicalRecordAction.printReportRekapitulasiMedical] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[MedicalRecordAction.printReportRekapitulasiMedical] end process <<<");
        return "print_report_rekapitulasi_medical";
    }
    public String printReportMedical() {
        logger.info("[ReportAction.printReportLembur] start process >>>");
        List<MedicalRecord> listDataMedical = new ArrayList();
        List<MedicalRecord> listDataFinal = new ArrayList();
        String unit = "";
        String nama="";
        String bagian ="";
        String golongan="";
        String posisi="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
//        StrukturJabatanBo strukturJabatanBo= (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");

        Branch searchBranch = new Branch();
        searchBranch.setFlag("Y");
        searchBranch.setBranchId(getBranchId());
        List<Branch> branchList = branchBo.getByCriteria(searchBranch);
        for (Branch branch:branchList){
            unit=branch.getBranchName();
        }

        MedicalRecord search = new MedicalRecord();
        search.setFlag("Y");
        search.setStTanggalDari(getTglFrom());
        search.setStTanggalSampai(getTglTo());
        if (!("").equalsIgnoreCase(getNip())){
            search.setNip(getNip());

            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            Biodata searchBiodata = new Biodata();
            searchBiodata.setNip(getNip());
            searchBiodata.setFlag("Y");
            Biodata biodata = new Biodata();
            biodata = biodataBo.detailBiodataSys(getNip());
            if (biodata!=null){
                nama=biodata.getNamaPegawai();
                golongan=biodata.getGolonganName();
                posisi=biodata.getPositionName();
            }
        }
        try {
            listDataMedical = medicalRecordBoProxy.getMedicalRecordUser(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteriaForLembur");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.printReportLembur] Error when saving error,", e1);
            }
            logger.error("[AbsensiAction.printReportLembur] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        BigDecimal hasilSemua = BigDecimal.ZERO;
        for (MedicalRecord medicalRecord : listDataMedical ){
            hasilSemua=hasilSemua.add(new BigDecimal(medicalRecord.getJumlahBiaya()));
            medicalRecord.setJumlahBiaya(CommonUtil.numbericFormat(new BigDecimal(medicalRecord.getJumlahBiaya()),"###,###"));
            listDataFinal.add(medicalRecord);
        }

        MedicalRecord hasil = new MedicalRecord();
        hasil.setStTanggalBerobat("");
        hasil.setTipeBerobatName("");
        hasil.setTipePengobatanName("");
        hasil.setJumlahBiaya(CommonUtil.numbericFormat(hasilSemua,"###,###"));
        hasil.setNote("");

        listDataFinal.add(hasil);

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listDataFinal);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "REPORT MEDICAL");
        reportParams.put("tanggalDari", getTglFrom());
        reportParams.put("tanggalSelesai", getTglTo());
        reportParams.put("itemDataSource", itemData);
        reportParams.put("nip", getNip());
        reportParams.put("nama", nama);
        reportParams.put("posisi", posisi);
        reportParams.put("golongan", golongan.replace("Golongan ",""));
        reportParams.put("date", stDate);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "printReportLembur");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printReportLembur] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printReportLembur] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print_report_lembur";

        }
        logger.info("[ReportAction.printReportLembur] end process <<<");

        return "print_report_medical";
    }
    public String reportMedical() {
        logger.info("[MedicalRecordAction.reportMedical] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultMedicalRecord");
        logger.info("[MedicalRecordAction.reportMedical] end process >>>");
        return "report_medical";
    }

    public String searchReportMedical(){
        logger.info("[MedicalRecordAction.searchReportMedical] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MedicalRecord> result = new ArrayList<>();
        List<Biodata> biodataList= new ArrayList();
        MedicalRecord search = getMedicalRecord();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        biodataList = biodataBo.getBiodataforAbsensi(search.getBranchId(),"",search.getBagian(),search.getNip());
        for (Biodata biodata : biodataList){
            List<MedicalRecord> listData = new ArrayList();
            MedicalRecord searchData = new MedicalRecord();
            searchData.setFlag("Y");
            searchData.setStTanggalDari(search.getStTanggalDari());
            searchData.setStTanggalSampai(search.getStTanggalSampai());
            searchData.setNip(biodata.getNip());
            try {
                listData = medicalRecordBoProxy.getMedicalRecordUser(searchData);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = medicalRecordBoProxy.saveErrorMessage(e.getMessage(), "MedicalRecordAction.printReportRekapitulasiMedical");
                } catch (GeneralBOException e1) {
                    logger.error("[MedicalRecordAction.searchReportMedical] Error when saving error,", e1);
                }
                logger.error("[MedicalRecordAction.searchReportMedical] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
            Long jumlah= 0L;
            if (listData.size()!=0){
                for (MedicalRecord medicalRecord: listData ){
                    jumlah= jumlah + Long.valueOf(medicalRecord.getJumlahBiaya());
                }

                MedicalRecord hasilAkhir = new MedicalRecord();
                hasilAkhir.setJumlahBiaya(String.valueOf(jumlah));
                hasilAkhir.setNama(biodata.getNamaPegawai());
                hasilAkhir.setNip(biodata.getNip());
                hasilAkhir.setBagian(biodata.getBagianName());
                hasilAkhir.setStTanggalDari(search.getStTanggalDari());
                hasilAkhir.setStTanggalSampai(search.getStTanggalSampai());
                hasilAkhir.setFlag("Y");
                result.add(hasilAkhir);
            }
        }

        String bagianPegawai="";
        Long jumlah= 0L;
        int a = 1;
        List<MedicalRecord> forReport = new ArrayList<>();
        for(MedicalRecord medicalRecord: result) {
            if (!bagianPegawai.equalsIgnoreCase(medicalRecord.getBagian())){
                bagianPegawai = medicalRecord.getBagian();
                a=1;

            }
            if (bagianPegawai.equalsIgnoreCase(medicalRecord.getBagian())) {
                jumlah=jumlah+Long.valueOf(medicalRecord.getJumlahBiaya());

                medicalRecord.setNo(String.valueOf(a));
                medicalRecord.setJumlahBiaya(CommonUtil.numbericFormat(new BigDecimal(medicalRecord.getJumlahBiaya()),"###,###"));
                forReport.add(medicalRecord);
                a++;
            }
        }

        session.removeAttribute("listOfResultMedical");
        session.setAttribute("listOfResultMedical", forReport);
        logger.info("[ReportAction.searchReportMedical] end process <<<");
        return "success_report_medical";
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
