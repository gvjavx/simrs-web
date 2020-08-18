package com.neurix.hris.transaksi.rekruitmen.action;

//import com.neurix.authorization.company.bo.AreaBo;

import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.statusRekruitment.bo.StatusRekruitmentBo;
import com.neurix.hris.master.statusRekruitment.model.StatusRekruitment;
import com.neurix.hris.master.strukturJabatan.bo.StrukturJabatanBo;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.rekruitmen.bo.RekruitmenBo;
import com.neurix.hris.transaksi.rekruitmen.dao.RekruitmenDao;
import com.neurix.hris.transaksi.rekruitmen.model.*;
import com.neurix.hris.transaksi.sppd.model.SppdReroute;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class RekruitmenAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(RekruitmenAction.class);
    private String statusId;
    private RekruitmenBo rekruitmenBoProxy;
    private Rekruitmen rekruitmen;
    private File fileUpload;
    private RekruitmenDao rekruitmenDao;
    private StudyCalonPegawai studyCalonPegawai;
    private RekruitmenUploadDoc rekruitmenUploadDoc;
    private CutiPegawaiBo cutiPegawaiBoProxy;
    private IjinKeluarBo ijinKeluarBoProxy;
    private NotifikasiBo notifikasiBoProxy;
    private BiodataBo biodataBoProxy;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private StatusRekruitment statusRekruitment;
    private StatusRekruitmentBo statusRekruitmentBoProxy;
    private RekruitmenStatus rekruitmenStatus;
    private StrukturJabatan strukturJabatan;
    private StrukturJabatanBo strukturJabatanBoProxy;
    private List listComboStatusRekruitmen = new ArrayList();
    private List listOfComboPeriode = new ArrayList();

    public BiodataBo getBiodataBoProxy() {
        return biodataBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public RekruitmenDao getRekruitmenDao() {
        return rekruitmenDao;
    }

    public void setRekruitmenDao(RekruitmenDao rekruitmenDao) {
        this.rekruitmenDao = rekruitmenDao;
    }

    public StrukturJabatanBo getStrukturJabatanBo() {
        return strukturJabatanBoProxy;
    }

    public StrukturJabatanBo getStrukturJabatanBoProxy() {
        return strukturJabatanBoProxy;
    }

    public void setStrukturJabatanBoProxy(StrukturJabatanBo strukturJabatanBoProxy) {
        this.strukturJabatanBoProxy = strukturJabatanBoProxy;
    }

    public List getListOfComboPeriode() {
        return listOfComboPeriode;
    }

    public void setListOfComboPeriode(List listOfComboPeriode) {
        this.listOfComboPeriode = listOfComboPeriode;
    }

    public void setStrukturJabatanBo(StrukturJabatanBo strukturJabatanBo) {
        this.strukturJabatanBoProxy = strukturJabatanBo;
    }

    public StrukturJabatan getStrukturJabatan() {
        return strukturJabatan;
    }

    public void setStrukturJabatan(StrukturJabatan strukturJabatan) {
        this.strukturJabatan = strukturJabatan;
    }

    public RekruitmenStatus getRekruitmenStatus() {
        return rekruitmenStatus;
    }

    public void setRekruitmenStatus(RekruitmenStatus rekruitmenStatus) {
        this.rekruitmenStatus = rekruitmenStatus;
    }

    public StatusRekruitmentBo getStatusRekruitmentBoProxy() {
        return statusRekruitmentBoProxy;
    }

    public void setStatusRekruitmentBoProxy(StatusRekruitmentBo statusRekruitmentBoProxy) {
        this.statusRekruitmentBoProxy = statusRekruitmentBoProxy;
    }

    public StatusRekruitment getStatusRekruitment() {
        return statusRekruitment;
    }

    public void setStatusRekruitment(StatusRekruitment statusRekruitment) {
        this.statusRekruitment = statusRekruitment;
    }

    public StudyCalonPegawai getStudyCalonPegawai() {
        return studyCalonPegawai;
    }

    public void setStudyCalonPegawai(StudyCalonPegawai studyCalonPegawai) {
        this.studyCalonPegawai = studyCalonPegawai;
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

    public RekruitmenUploadDoc getRekruitmenUploadDoc() {
        return rekruitmenUploadDoc;
    }

    public void setRekruitmenUploadDoc(RekruitmenUploadDoc rekruitmenUploadDoc) {
        this.rekruitmenUploadDoc = rekruitmenUploadDoc;
    }

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public CutiPegawaiBo getCutiPegawaiBoProxy() {
        return cutiPegawaiBoProxy;
    }

    public void setCutiPegawaiBoProxy(CutiPegawaiBo cutiPegawaiBoProxy) {
        this.cutiPegawaiBoProxy = cutiPegawaiBoProxy;
    }

    public IjinKeluarBo getIjinKeluarBoProxy() {
        return ijinKeluarBoProxy;
    }

    public void setIjinKeluarBoProxy(IjinKeluarBo ijinKeluarBoProxy) {
        this.ijinKeluarBoProxy = ijinKeluarBoProxy;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public RekruitmenBo getRekruitmenBoProxy() {
        return rekruitmenBoProxy;
    }

    public void setRekruitmenBoProxy(RekruitmenBo rekruitmenBoProxy) {
        this.rekruitmenBoProxy = rekruitmenBoProxy;
    }

    public Rekruitmen getRekruitmen() {
        return rekruitmen;
    }

    public void setRekruitmen(Rekruitmen rekruitmen) {
        this.rekruitmen = rekruitmen;
    }

    private List<Rekruitmen> initComboRekruitmen;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RekruitmenAction.logger = logger;
    }


    public List<Rekruitmen> getInitComboRekruitmen() {
        return initComboRekruitmen;
    }

    public void setInitComboRekruitmen(List<Rekruitmen> initComboRekruitmen) {
        this.initComboRekruitmen = initComboRekruitmen;
    }

    public List<StudyCalonPegawai> searchRekruitmenStudy() {
        logger.info("[RekruitmenAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<StudyCalonPegawai> listOfsearchRekruitmenStudy = (List<StudyCalonPegawai>) session.getAttribute("listOfResultRekruitmenStudy");

        return listOfsearchRekruitmenStudy;
    }
    public List<RekruitmenUraianKerjaan> searchUraianPekerjaan() {
        logger.info("[RekruitmenAction.searchUraianPekerjaan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenUraianKerjaan> rekruitmenUraianKerjaanList = (List<RekruitmenUraianKerjaan>) session.getAttribute("listOfResultRekruitmenUraianPekerjaan");

        return rekruitmenUraianKerjaanList;
    }
    public List<RekruitmenUploadDoc> searchRekruitmenDocument(String calonPegawaiId) {
        logger.info("[RekruitmenAction.search] start process >>>");
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        Rekruitmen uploadRekruitmen = new Rekruitmen();
        uploadRekruitmen.setCalonPegawaiId(calonPegawaiId);
        uploadRekruitmen.setFlag("Y");
        List<RekruitmenUploadDoc> listOfsearchRekruitmenDocument= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekruitmenBo rekruitmenBo = (RekruitmenBo) ctx.getBean("rekruitmenBoProxy");
        if (calonPegawaiId!=null){
            try {
                listOfsearchRekruitmenDocument = rekruitmenBo.getByCriteriaDocument(uploadRekruitmen);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[RekruitmenAction.search] Error when saving error,", e1);
                    return null;
                }
                logger.error("[RekruitmenAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                return null;
            }
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("ListOfResultRekruitmenDocument");
            session.setAttribute("ListOfResultRekruitmenDocument", listOfsearchRekruitmenDocument);

            if (listOfsearchRekruitmenDocument==null){
                return null;
            }
            else{
                return listOfsearchRekruitmenDocument;
            }
        }
        else {
            return null;
        }
    }
    public String saveAction(){
        logger.info("[RekruitmenAction.saveAction] start process >>>");

        try {
            RekruitmenStatus rekruitmenStatus = getRekruitmenStatus();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            rekruitmenStatus.setTanggalProses(updateTime);
            rekruitmenStatus.setCreatedWho(userLogin);
            rekruitmenStatus.setLastUpdate(updateTime);
            rekruitmenStatus.setCreatedDate(updateTime);
            rekruitmenStatus.setLastUpdateWho(userLogin);
            rekruitmenStatus.setAction("C");
            rekruitmenStatus.setFlag("Y");

            rekruitmenBoProxy.saveAction(rekruitmenStatus);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenStatusBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[rekruitmenAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[rekruitmenAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[rekruitmenAction.saveAdd] end process >>>");
        return "success_save_action";
    }
    public void saveAddStudy(String calonPegawaiId, String tipeStudy, String studyName, String stTahunAwal, String stTahunAkhir, String nilai){
        logger.info("[RekruimentAction.saveAddStudy] start process >>>");
        List<StudyCalonPegawai> listComboRekruitmenStudy = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            StudyCalonPegawai studyCalonPegawai = new StudyCalonPegawai();

            studyCalonPegawai.setCalonPegawaiId(calonPegawaiId);
            studyCalonPegawai.setTipeStudy(tipeStudy);
            studyCalonPegawai.setStudyName(studyName);
            studyCalonPegawai.setStTahunAwal(stTahunAwal);
            studyCalonPegawai.setStTahunAkhir(stTahunAkhir);
            studyCalonPegawai.setNilai(nilai);
            listComboRekruitmenStudy = (List<StudyCalonPegawai>) session.getAttribute("listOfResultRekruitmenStudy");
            if(listComboRekruitmenStudy != null){
                listComboRekruitmenStudy.add(studyCalonPegawai);
            }else{
                listComboRekruitmenStudy = new ArrayList();
                listComboRekruitmenStudy.add(studyCalonPegawai);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "RekruitmenBo.saveAddStudy");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenBo.saveAddStudy] Error when saving error,", e1);
            }
            logger.error("[RekruitmenBo.saveAddStudy] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("listOfResultRekruitmenStudy", listComboRekruitmenStudy);
    }

    public void saveAddUraianPekerjaan(String uraianPekerjaan){
        logger.info("[RekruimentAction.saveAddUraianPekerjaan] start process >>>");
        List<RekruitmenUraianKerjaan> uraianKerjaanList = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            RekruitmenUraianKerjaan rekruitmenUraianKerjaan = new RekruitmenUraianKerjaan();
            rekruitmenUraianKerjaan.setUraianPekerjaan(uraianPekerjaan);
            uraianKerjaanList = (List<RekruitmenUraianKerjaan>) session.getAttribute("listOfResultRekruitmenUraianPekerjaan");
            if(uraianKerjaanList != null){
                uraianKerjaanList.add(rekruitmenUraianKerjaan);
            }else{
                uraianKerjaanList = new ArrayList();
                uraianKerjaanList.add(rekruitmenUraianKerjaan);
            }
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "RekruitmenBo.saveAddStudy");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenBo.saveAddStudy] Error when saving error,", e1);
            }
            logger.error("[RekruitmenBo.saveAddStudy] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("listOfResultRekruitmenUraianPekerjaan", uraianKerjaanList);
    }

    public void saveDeleteUraianPekerjaan(String uraianPekerjaan){
        logger.info("[RekruimentAction.saveAddUraianPekerjaan] start process >>>");
        List<RekruitmenUraianKerjaan> uraianKerjaanList = null;
        List<RekruitmenUraianKerjaan> uraianKerjaanListFinal = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            uraianKerjaanList = (List<RekruitmenUraianKerjaan>) session.getAttribute("listOfResultRekruitmenUraianPekerjaan");
            for (RekruitmenUraianKerjaan rekruitmenUraianKerjaan : uraianKerjaanList){
                if (!rekruitmenUraianKerjaan.getUraianPekerjaan().equalsIgnoreCase(uraianPekerjaan)){
                    uraianKerjaanListFinal.add(rekruitmenUraianKerjaan);
                }
            }
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "RekruitmenBo.saveAddStudy");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenBo.saveAddStudy] Error when saving error,", e1);
            }
            logger.error("[RekruitmenBo.saveAddStudy] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("listOfResultRekruitmenUraianPekerjaan", uraianKerjaanListFinal);
    }

    public String uploadProfil(){
        List<Rekruitmen> listComboRekruitmen = new ArrayList<Rekruitmen>();
        String path = null;
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultProfil");
        try {
            Rekruitmen rekruitmen = getRekruitmen();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            if (this.fileUpload!=null) {
                String fileDocName = rekruitmen.getCalonPegawaiId()+"_"+this.fileUploadFileName;
                String fileContentType = this.fileUploadContentType;
                String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_PHOTO;
                java.io.File fileToCreate = new java.io.File(filePath, fileDocName);
                path = filePath+fileDocName;
                //create file to save to folder '/upload'
                byte[] contentFile = null;
                try {
                    FileUtils.copyFile(this.fileUpload, fileToCreate);
                    contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                } catch (IOException e) {
                    Long logId = null;
                    try {
                        logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "Rekruitmen.save");
                    } catch (GeneralBOException e1) {
                        logger.error("[Rekruitmen.saveDoc] Error when saving error,", e1);
                    }
                    logger.error("[Rekruitmen.saveDoc] Error when uploading and saving Documen," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                    return ERROR;
                }
                rekruitmen.setFotoUpload(fileDocName);
            }
            rekruitmen.setCreatedWho(userLogin);
            rekruitmen.setLastUpdate(updateTime);
            rekruitmen.setCreatedDate(updateTime);
            rekruitmen.setLastUpdateWho(userLogin);
            rekruitmen.setFlag("Y");
            rekruitmen.setAction("C");

            listComboRekruitmen.add(rekruitmen);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBo.AddDoc");
            } catch (GeneralBOException e1) {
                logger.error("[rekruitmenBo.AddDoc] Error when saving error,", e1);
            }
            logger.error("[rekruitmenBo.AddDoc] Error when adding item ," + "[" + logId + "] Found problem when add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when add data, please inform to your admin.\n" + e.getMessage());
        }
        session.setAttribute("listOfResultProfil", listComboRekruitmen);
        return null;
    }
    public String addRekruitmenDoc(){
        List<RekruitmenUploadDoc> listComboRekruitmenDocument = null;
        HttpSession session = ServletActionContext.getRequest().getSession();
        String path = null;
        try {
            RekruitmenUploadDoc rekruitmenUploadDoc = getRekruitmenUploadDoc();
            String idDoc = rekruitmenBoProxy.getNextDocId();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            if (this.fileUpload!=null) {
                String fileDocName = idDoc +"_"+ this.fileUploadFileName;
                String fileContentType = this.fileUploadContentType;
                String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_REKRUITMEN_FILE;
                java.io.File fileToCreate = new java.io.File(filePath, fileDocName);
                path = filePath+fileDocName;
                //create file to save to folder '/upload'
                byte[] contentFile = null;
                try {
                    FileUtils.copyFile(this.fileUpload, fileToCreate);
                    contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                } catch (IOException e) {
                    Long logId = null;
                    try {
                        logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "Rekruitmen.save");
                    } catch (GeneralBOException e1) {
                        logger.error("[Rekruitmen.saveDoc] Error when saving error,", e1);
                    }
                    logger.error("[Rekruitmen.saveDoc] Error when uploading and saving Documen," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                    return ERROR;
                }

                if (contentFile!=null) {
                    rekruitmenUploadDoc.setUploadFile(fileDocName);
                    if ("image/jpeg".equalsIgnoreCase(fileContentType)) {
                        rekruitmenUploadDoc.setFileType("IMG");
                    }else if("application/pdf".equalsIgnoreCase(fileContentType)) {
                        rekruitmenUploadDoc.setFileType("PDF");
                    }
                }
            }
            DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
            String formattedDate = df.format(Calendar.getInstance().getTime());
            if (rekruitmenUploadDoc.getCalonPegawaiId().substring(0,1).equals("C")){
                rekruitmenUploadDoc.setCalonPegawaiId(rekruitmenUploadDoc.getCalonPegawaiId());
            }
            else{
                rekruitmenUploadDoc.setCalonPegawaiId(rekruitmenUploadDoc.getCalonPegawaiId());
            }
            rekruitmenUploadDoc.setDocumentName(rekruitmenUploadDoc.getDocumentName());
            rekruitmenUploadDoc.setCalonPegawaiId(rekruitmenUploadDoc.getCalonPegawaiId());
            rekruitmenUploadDoc.setUploadDocRekId(idDoc);
            rekruitmenUploadDoc.setFilePath(path);
            rekruitmenUploadDoc.setCreatedWho(userLogin);
            rekruitmenUploadDoc.setLastUpdate(updateTime);
            rekruitmenUploadDoc.setCreatedDate(updateTime);
            rekruitmenUploadDoc.setLastUpdateWho(userLogin);
            rekruitmenUploadDoc.setFlag("Y");
            rekruitmenUploadDoc.setAction("C");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenBo rekruitmenBo= (RekruitmenBo) ctx.getBean("rekruitmenBoProxy");

            rekruitmenBo.addRekruitmenDoc(rekruitmenUploadDoc);

            listComboRekruitmenDocument = (List<RekruitmenUploadDoc>) session.getAttribute("listOfResultRekruitmenDocument");
            if(listComboRekruitmenDocument != null){
                listComboRekruitmenDocument.add(rekruitmenUploadDoc);
            }else{
                listComboRekruitmenDocument = new ArrayList();
                listComboRekruitmenDocument.add(rekruitmenUploadDoc);
            }
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBo.AddDoc");
            } catch (GeneralBOException e1) {
                logger.error("[rekruitmenBo.AddDoc] Error when saving error,", e1);
            }
            logger.error("[rekruitmenBo.AddDoc] Error when adding item ," + "[" + logId + "] Found problem when add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when add data, please inform to your admin.\n" + e.getMessage());
        }
        session.setAttribute("listOfResultRekruitmenDocument", listComboRekruitmenDocument);
        return null;
    }
    public String closed() {
        logger.info("[RekruitmenAction.delete] start process >>>");
        List<RekruitmenUploadDoc> listRekruitmenDocument = null;
        List<StudyCalonPegawai> listRekruitmenStudy = null;
        List<RekruitmenStatus> listRekruitmenStatus = null;
        String itemId = getId();
        String itemFlag = getFlag();
        Rekruitmen viewRekruitmen = new Rekruitmen();
        try {
            viewRekruitmen = init(itemId, itemFlag);
            listRekruitmenDocument = rekruitmenBoProxy.getListRekruitmenDocumentBo(itemId,itemFlag);
            listRekruitmenStudy = rekruitmenBoProxy.getListRekruitmenStudyBo(itemId,itemFlag);
            listRekruitmenStatus = rekruitmenBoProxy.getListRekruitmenStatusBo(itemId,itemFlag);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBo.viewRekruitmen");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.viewRekruitmen] Error when retrieving delete data,", e1);
            }
            logger.error("[RekruitmenAction.viewRekruitmen] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }
        setRekruitmen(viewRekruitmen);
        logger.info("[RekruitmenAction.delete] end process <<<");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("ListOfResultRekruitmenStudy");
        session.removeAttribute("listOfResultRekruitmenDocument");
        session.removeAttribute("listOfResultRekruitmenStatus");
        session.removeAttribute("listOfResultRekruitmenUraianPekerjaan");
        session.setAttribute("ListOfResultRekruitmenStudy", listRekruitmenStudy);
        session.setAttribute("listOfResultRekruitmenDocument", listRekruitmenDocument);
        session.setAttribute("listOfResultRekruitmenStatus", listRekruitmenStatus);
        return "init_closed_rekruitmen";
    }
    public String viewRekruitmen() {
        logger.info("[RekruitmenAction.delete] start process >>>");
        List<RekruitmenUploadDoc> listRekruitmenDocument = null;
        List<StudyCalonPegawai> listRekruitmenStudy = null;
        List<RekruitmenStatus> listRekruitmenStatus = null;
        String itemId = getId();
        String itemFlag = getFlag();
        Rekruitmen viewRekruitmen = new Rekruitmen();
        try {
            viewRekruitmen = init(itemId, itemFlag);
            listRekruitmenDocument = rekruitmenBoProxy.getListRekruitmenDocumentBo(itemId,itemFlag);
            listRekruitmenStudy = rekruitmenBoProxy.getListRekruitmenStudyBo(itemId,itemFlag);
            listRekruitmenStatus = rekruitmenBoProxy.getListRekruitmenStatusBo(itemId,itemFlag);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBo.viewRekruitmen");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.viewRekruitmen] Error when retrieving delete data,", e1);
            }
            logger.error("[RekruitmenAction.viewRekruitmen] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }
        setRekruitmen(viewRekruitmen);
        logger.info("[RekruitmenAction.delete] end process <<<");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("ListOfResultRekruitmenStudy");
        session.removeAttribute("listOfResultRekruitmenDocument");
        session.removeAttribute("listOfResultRekruitmenStatus");
        session.setAttribute("ListOfResultRekruitmenStudy", listRekruitmenStudy);
        session.setAttribute("listOfResultRekruitmenDocument", listRekruitmenDocument);
        session.setAttribute("listOfResultRekruitmenStatus", listRekruitmenStatus);
        return "init_view_rekruitmen";
    }
    public void searchStatusRekruitmen() {
        logger.info("[RekruitmenAction.search] start process >>>");

        StatusRekruitment statusRekruitment = new StatusRekruitment();
        statusRekruitment.setFlag("Y");
        List<StatusRekruitment> listOfSearchStatusRekruitmen = new ArrayList();

        try {
            listOfSearchStatusRekruitmen = statusRekruitmentBoProxy.getByCriteria(statusRekruitment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = statusRekruitmentBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.search] Error when saving error,", e1);
            }
            logger.error("[RekruitmenAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        listComboStatusRekruitmen.addAll(listOfSearchStatusRekruitmen);
    }

    public List getListComboStatusRekruitmen() {
        return listComboStatusRekruitmen;
    }

    public void setListComboStatusRekruitmen(List listComboStatusRekruitmen) {
        this.listComboStatusRekruitmen = listComboStatusRekruitmen;
    }

    public Rekruitmen init(String kode, String flag){
        logger.info("[RekruitmenAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Rekruitmen> listOfResult = (List<Rekruitmen>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Rekruitmen rekruitmen : listOfResult) {
                    if(kode.equalsIgnoreCase(rekruitmen.getCalonPegawaiId()) && flag.equalsIgnoreCase(rekruitmen.getFlag())){
                        setRekruitmen(rekruitmen);
                        break;
                    }
                }
            } else {
                setRekruitmen(new Rekruitmen());
            }
            logger.info("[RekruitmenAction.init] end process >>>");
        }
        return getRekruitmen();
    }


    public List initComboUser(String query) {
        logger.info("[UserAction.initComboUser] start process >>>");

        List<Rekruitmen> rekruitmenList = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekruitmenBo userBo = (RekruitmenBo) ctx.getBean("rekruitmenBoProxy");

        try {
            rekruitmenList = userBo.getListOfPersonilPosition(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBo.saveErrorMessage(e.getMessage(), "UserBO.getComboUserWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboUser] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboUser] Error when get combo User," + "[" + logId + "] Found problem when retrieving combo User data, please inform to your admin.", e);
        }

        logger.info("[UserAction.initComboUser] end process <<<");

        return rekruitmenList;
    }

    @Override
    public String add() {
        logger.info("[RekruitmenAction.add] start process >>>");
        Rekruitmen addRekruitmen = new Rekruitmen();
        String calonPegawaiId = rekruitmenBoProxy.getNextCalonPegawaiId();
        addRekruitmen.setCalonPegawaiId(calonPegawaiId);
        setRekruitmen(addRekruitmen);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultRekruitmenStudy");
        session.removeAttribute("listOfResultRekruitmenStatus");
        session.removeAttribute("listOfResultRekruitmenDocument");

        logger.info("[RekruitmenAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[RekruitmenAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Rekruitmen editRekruitmen = new Rekruitmen();

        if(itemFlag != null){
            try {
                editRekruitmen = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getPersonalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[RekruitmenAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[RekruitmenAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRekruitmen != null) {
                setRekruitmen(editRekruitmen);
            } else {
                editRekruitmen.setFlag(itemFlag);
//                editRekruitmen.setNip(itemId);
                setRekruitmen(editRekruitmen);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
//            editRekruitmen.setNip(itemId);
            editRekruitmen.setFlag(getFlag());
            setRekruitmen(editRekruitmen);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[RekruitmenAction.edit] end process >>>");
        return "init_add_user";
    }

    @Override
    public String delete() {
            logger.info("[RekruitmenAction.delete] start process >>>");
            List<RekruitmenUploadDoc> listRekruitmenDocument = null;
            List<StudyCalonPegawai> listRekruitmenStudy = null;
            List<RekruitmenStatus> listRekruitmenStatus = null;
            String itemId = getId();
            String itemFlag = getFlag();
            Rekruitmen viewRekruitmen = new Rekruitmen();
            try {
                viewRekruitmen = init(itemId, itemFlag);
                listRekruitmenDocument = rekruitmenBoProxy.getListRekruitmenDocumentBo(itemId,itemFlag);
                listRekruitmenStudy = rekruitmenBoProxy.getListRekruitmenStudyBo(itemId,itemFlag);
                listRekruitmenStatus = rekruitmenBoProxy.getListRekruitmenStatusBo(itemId,itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBo.viewRekruitmen");
                } catch (GeneralBOException e1) {
                    logger.error("[RekruitmenAction.viewRekruitmen] Error when retrieving delete data,", e1);
                }
                logger.error("[RekruitmenAction.viewRekruitmen] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }
            setRekruitmen(viewRekruitmen);
            logger.info("[RekruitmenAction.delete] end process <<<");
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("ListOfResultRekruitmenStudy");
            session.removeAttribute("listOfResultRekruitmenDocument");
            session.removeAttribute("listOfResultRekruitmenStatus");
            session.setAttribute("ListOfResultRekruitmenStudy", listRekruitmenStudy);
            session.setAttribute("listOfResultRekruitmenDocument", listRekruitmenDocument);
            session.setAttribute("listOfResultRekruitmenStatus", listRekruitmenStatus);
            return "init_delete_rekruitmen";
    }
    public String uploadDocument() {
        logger.info("[RekruitmenAction.closed] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Rekruitmen uploadRekruitmen = new Rekruitmen();
        List<RekruitmenUploadDoc> listOfsearchRekruitmenDocument = new ArrayList();
        if (itemFlag != null ) {
            try {
                uploadRekruitmen = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[RekruitmenAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[RekruitmenAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }
            try {
                listOfsearchRekruitmenDocument = rekruitmenBoProxy.getByCriteriaDocument(uploadRekruitmen);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[RekruitmenAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[RekruitmenAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                return ERROR;
            }
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("ListOfResultRekruitmenDocument");
            session.setAttribute("ListOfResultRekruitmenDocument", listOfsearchRekruitmenDocument);

            if (uploadRekruitmen != null) {
                setRekruitmen(uploadRekruitmen);

            } else {
//                deleteRekruitmen.setNip(itemId);
                uploadRekruitmen.setFlag(itemFlag);
                setRekruitmen(uploadRekruitmen);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
//            deleteRekruitmen.setNip(itemId);
            uploadRekruitmen.setFlag(itemFlag);
            setRekruitmen(uploadRekruitmen);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        setDelete(true);
        logger.info("[RekruitmenAction.delete] end process <<<");

        return "upload";
    }
    public String action() {
        logger.info("[RekruitmenAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Rekruitmen actionRekruitmen = new Rekruitmen();
        List<RekruitmenStatus> listOfsearchRekruitmenStatus = new ArrayList();
        if (itemFlag != null ) {

            try {
                actionRekruitmen = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[RekruitmenAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[RekruitmenAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }
            try {
                listOfsearchRekruitmenStatus = rekruitmenBoProxy.getByCriteriaStatus(actionRekruitmen);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[RekruitmenAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[RekruitmenAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                return ERROR;
            }
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("ListOfResultRekruitmenStatus");
            session.setAttribute("ListOfResultRekruitmenStatus", listOfsearchRekruitmenStatus);

            if (actionRekruitmen != null) {
                setRekruitmen(actionRekruitmen);

            } else {
//                deleteRekruitmen.setNip(itemId);
                actionRekruitmen.setFlag(itemFlag);
                setRekruitmen(actionRekruitmen);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
//            deleteRekruitmen.setNip(itemId);
            actionRekruitmen.setFlag(itemFlag);
            setRekruitmen(actionRekruitmen);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        setDelete(true);
        logger.info("[RekruitmenAction.delete] end process <<<");

        return "action";
    }
    @Override
    public String view() {
        return null;
    }

    public String closedAction() {
        logger.info("[RekruitmenAction.closedAction] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenUraianKerjaan> rekruitmenUraianKerjaanList = (List<RekruitmenUraianKerjaan>) session.getAttribute("listOfResultRekruitmenUraianPekerjaan");

        try {
            Rekruitmen closedRekruitmen = getRekruitmen();
            RekruitmenStatus closedRekruitmenStatus = getRekruitmenStatus();
            RekruitmenUraianKerjaan rekruitmenUraianKerjaan = new RekruitmenUraianKerjaan();

            String calonPegawaiId = closedRekruitmen.getCalonPegawaiId();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            if (closedRekruitmen.getStTanggalLahir() != null && !"".equalsIgnoreCase(closedRekruitmen.getStTanggalLahir())) {
                closedRekruitmen.setTanggalLahir(CommonUtil.convertToDate(closedRekruitmen.getStTanggalLahir()));
            }
            if (closedRekruitmen.getStTanggalAktif() != null && !"".equalsIgnoreCase(closedRekruitmen.getStTanggalAktif())) {
                closedRekruitmen.setTanggalAktif(CommonUtil.convertStringToDate(closedRekruitmen.getStTanggalAktif()));
            }
            if (closedRekruitmen.getStKontrakDari() != null && !"".equalsIgnoreCase(closedRekruitmen.getStKontrakDari())) {
                closedRekruitmen.setKontrakDari(CommonUtil.convertStringToDate(closedRekruitmen.getStKontrakDari()));
            }
            if (closedRekruitmen.getStKontrakSelesai() != null && !"".equalsIgnoreCase(closedRekruitmen.getStKontrakSelesai())) {
                closedRekruitmen.setKontrakSelesai(CommonUtil.convertStringToDate(closedRekruitmen.getStKontrakSelesai()));
            }
            closedRekruitmen.setCreatedWho(userLogin);
            closedRekruitmen.setLastUpdateWho(userLogin);
            closedRekruitmen.setCreatedDate(updateTime);
            closedRekruitmen.setLastUpdate(updateTime);
            closedRekruitmen.setAction("U");
            closedRekruitmen.setFlag("Y");

            closedRekruitmenStatus.setCalonPegawaiId(calonPegawaiId);
            closedRekruitmenStatus.setTanggalProses(updateTime);
            closedRekruitmenStatus.setLastUpdateWho(userLogin);
            closedRekruitmenStatus.setLastUpdate(updateTime);
            closedRekruitmenStatus.setCreatedWho(userLogin);
            closedRekruitmenStatus.setCreatedDate(updateTime);
            closedRekruitmenStatus.setAction("C");
            closedRekruitmenStatus.setFlag("Y");

            rekruitmenUraianKerjaan.setCalonPegawaiId(calonPegawaiId);
            rekruitmenUraianKerjaan.setLastUpdateWho(userLogin);
            rekruitmenUraianKerjaan.setLastUpdate(updateTime);
            rekruitmenUraianKerjaan.setCreatedWho(userLogin);
            rekruitmenUraianKerjaan.setCreatedDate(updateTime);
            rekruitmenUraianKerjaan.setAction("C");
            rekruitmenUraianKerjaan.setFlag("Y");

            rekruitmenBoProxy.saveClosed(closedRekruitmen);
            rekruitmenBoProxy.saveAddStatusClosed(closedRekruitmenStatus);
            rekruitmenBoProxy.saveAddUraianPekerjaan(rekruitmenUraianKerjaanList,rekruitmenUraianKerjaan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[RekruitmenAction.saveEdit] end process <<<");

        return "success_save_closed_action";
    }
    public List initComboDivisi(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<Position> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

        try {
            listOfAlat = positionBo.getComboDivisiWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }
    @Override
    public String save() {
        logger.info("[saveAdd] start process >>>");

        try {
            Rekruitmen rekruitmen = getRekruitmen();
            RekruitmenStatus rekruitmenStatus = getRekruitmenStatus();
            StudyCalonPegawai studyCalonPegawai = getStudyCalonPegawai();
            RekruitmenUploadDoc rekruitmenUploadDoc = getRekruitmenUploadDoc();

            if (rekruitmen.getStTanggalLahir() != null && !"".equalsIgnoreCase(rekruitmen.getStTanggalLahir())) {
                rekruitmen.setTanggalLahir(CommonUtil.convertToDate(rekruitmen.getStTanggalLahir()));
            }

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
            String formattedDate = df.format(Calendar.getInstance().getTime());

            rekruitmen.setCalonPegawaiId(rekruitmen.getCalonPegawaiId());
            rekruitmen.setCreatedWho(userLogin);
            rekruitmen.setLastUpdate(updateTime);
            rekruitmen.setCreatedDate(updateTime);
            rekruitmen.setLastUpdateWho(userLogin);
            rekruitmen.setAction("C");
            rekruitmen.setFlag("Y");
            rekruitmen.setClosed("N");

            rekruitmenStatus.setTanggalProses(updateTime);
            rekruitmenStatus.setLastUpdate(updateTime);
            rekruitmenStatus.setCreatedDate(updateTime);
            rekruitmenStatus.setLastUpdateWho(userLogin);
            rekruitmenStatus.setCreatedWho(userLogin);
            rekruitmenStatus.setAction("C");
            rekruitmenStatus.setFlag("Y");

            rekruitmenBoProxy.saveAddRekruitmen(rekruitmen,rekruitmenStatus);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
/*    @Override
    public String save() {
        if (isAddOrEdit()) {

            if (!isAdd()) {
                logger.info("[RekruitmenAction.saveEdit] start process >>>");
                try {

                    Rekruitmen editRekruitmen = getRekruitmen();

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    if (rekruitmen.getStTanggalLahir() != null && !"".equalsIgnoreCase(rekruitmen.getStTanggalLahir())) {
                        rekruitmen.setTanggalLahir(CommonUtil.convertToDate(rekruitmen.getStTanggalLahir()));
                    }

*//*                    if (this.fileUpload!=null) {

                        //note : for linux directory
                        //String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;

                        //note : for windows directory
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;
//                        String fileName = editRekruitmen.getNip() + ".jpg";
//                        File fileToCreate = new File(filePath, fileName);

                        //create file to save to folder '/upload'
                        byte[] contentFile = null;
                        try {
//                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                        } catch (IOException e) {
                            Long logId = null;
                            try {
                                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                            } catch (GeneralBOException e1) {
                                logger.error("[UserAction.save] Error when saving error,", e1);
                            }
                            logger.error("[UserAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                            return ERROR;
                        }

                        if (contentFile!=null) {
                            editRekruitmen.setContentFile(contentFile);
//                            editRekruitmen.setFotoUpload(fileName);
                        }
                    }*//*

                    editRekruitmen.setLastUpdateWho(userLogin);
                    editRekruitmen.setLastUpdate(updateTime);
                    editRekruitmen.setAction("U");
                    editRekruitmen.setFlag("Y");

                    rekruitmenBoProxy.saveEdit(editRekruitmen);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
                    } catch (GeneralBOException e1) {
                        logger.error("[RekruitmenAction.saveEdit] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[RekruitmenAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }

                logger.info("[RekruitmenAction.saveEdit] end process <<<");

                return "success_save_edit";
            } else {
                //add
                try {
                    Rekruitmen rekruitmen = getRekruitmen();
                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    if (rekruitmen.getStTanggalLahir() != null && !"".equalsIgnoreCase(rekruitmen.getStTanggalLahir())) {
//                        rekruitmen.setTanggalLahir(CommonUtil.convertToTimestamp(rekruitmen.getStTanggalLahir()));
                    }

                    if (this.fileUpload!=null) {

                        //note : for linux directory
                        //String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;

                        //note : for windows directory
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;
//                        String fileName = rekruitmen.getNip() + ".jpg";
//                        File fileToCreate = new File(filePath, fileName);

                        //create file to save to folder '/upload'
                        byte[] contentFile = null;
                        try {
//                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                        } catch (IOException e) {
                            Long logId = null;
                            try {
                                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                            } catch (GeneralBOException e1) {
                                logger.error("[UserAction.save] Error when saving error,", e1);
                            }
                            logger.error("[UserAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                            return ERROR;
                        }

                        if (contentFile!=null) {
                            rekruitmen.setContentFile(contentFile);
//                            rekruitmen.setFotoUpload(fileName);
                        }
                    }

                    rekruitmen.setCreatedWho(userLogin);
                    rekruitmen.setLastUpdate(updateTime);
                    rekruitmen.setCreatedDate(updateTime);
//                    rekruitmen.setTanggalAktif(updateTime);
//                    rekruitmen.setLastUpdateWho(userLogin);
//                    rekruitmen.setAction("C");
//                    rekruitmen.setStatusCaption("Online");
                    rekruitmen.setFlag("Y");

                    rekruitmenBoProxy.saveAdd(rekruitmen);
                }catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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

        } else if (isDelete()) {

            logger.info("[RekruitmenAction.saveDelete] start process >>>");
            try {

                Rekruitmen deleteRekruitmen = getRekruitmen();

                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                deleteRekruitmen.setLastUpdate(updateTime);
                deleteRekruitmen.setLastUpdateWho(userLogin);
                deleteRekruitmen.setAction("U");
                deleteRekruitmen.setFlag("N");

                rekruitmenBoProxy.saveDelete(deleteRekruitmen);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[RekruitmenAction.saveDelete] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[RekruitmenAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

            logger.info("[RekruitmenAction.saveDelete] end process <<<");

            return SUCCESS;

        }

        return null;
    }*/

    public String saveEdit(){
        logger.info("[RekruitmenAction.saveEdit] start process >>>");
        try {

            Rekruitmen editRekruitmen = getRekruitmen();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            editRekruitmen.setTanggalLahir(CommonUtil.convertStringToDate(editRekruitmen.getStTanggalLahir()));
            editRekruitmen.setLastUpdateWho(userLogin);
            editRekruitmen.setLastUpdate(updateTime);
            editRekruitmen.setAction("U");
            editRekruitmen.setFlag("Y");

            rekruitmenBoProxy.saveEdit(editRekruitmen);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[RekruitmenAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveCaption(String id, String Caption){
        logger.info("[RekruitmenAction.saveEdit] start process >>>");
        try {

            Rekruitmen editRekruitmen = new Rekruitmen();

//            editRekruitmen.setNip(id);
//            editRekruitmen.setStatusCaption(Caption);
            String userLogin = CommonUtil.userLogin();

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenBo rekruitmenBo = (RekruitmenBo) ctx.getBean("rekruitmenBoProxy");
            rekruitmenBo.saveEditCaption(editRekruitmen);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[RekruitmenAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[RekruitmenAction.saveDelete] start process >>>");
        try {

            Rekruitmen deleteRekruitmen = getRekruitmen();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteRekruitmen.setLastUpdate(updateTime);
            deleteRekruitmen.setLastUpdateWho(userLogin);
            deleteRekruitmen.setAction("U");
            deleteRekruitmen.setFlag("N");

            rekruitmenBoProxy.saveDelete(deleteRekruitmen);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[RekruitmenAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[RekruitmenAction.saveAdd] start process >>>");

        try {
            Rekruitmen rekruitmen = getRekruitmen();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if (rekruitmen.getStTanggalLahir() != null && !"".equalsIgnoreCase(rekruitmen.getStTanggalLahir())) {
//                rekruitmen.setTanggalLahir(CommonUtil.convertToTimestamp(rekruitmen.getStTanggalLahir()));
            }

            rekruitmen.setCreatedWho(userLogin);
            rekruitmen.setLastUpdate(updateTime);
            rekruitmen.setCreatedDate(updateTime);
//            rekruitmen.setTanggalAktif(updateTime);
//            rekruitmen.setLastUpdateWho(userLogin);
//            rekruitmen.setAction("C");
//            rekruitmen.setStatusCaption("Online");
            rekruitmen.setFlag("Y");

            rekruitmenBoProxy.saveAdd(rekruitmen);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        return null;
    }
    public void initComboPeriode() {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        year = year-10;
        int batas=20;
        List listOfPeriode = new ArrayList();
        for (int i=0;i<batas;i++){
            listOfPeriode.add(year-i);
        }

        listOfComboPeriode.addAll(listOfPeriode);
    }

    public void initComboPeriodeTahunSekarang10() {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int batas=10;
        List listOfPeriode = new ArrayList();
        for (int i=0;i<batas;i++){
            listOfPeriode.add(year-i);
        }
        listOfComboPeriode.addAll(listOfPeriode);
    }

    public void initComboPeriodeTahunSekarang5() {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int batas=5;
        List listOfPeriode = new ArrayList();
        for (int i=0;i<batas;i++){
            listOfPeriode.add(year-i);
        }
        listOfComboPeriode.addAll(listOfPeriode);
    }

    public void initComboPeriodeTahunKeatas5() {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int batas=5;
        List listOfPeriode = new ArrayList();
        for (int i=0;i<batas;i++){
            listOfPeriode.add(year+i);
        }
        listOfComboPeriode.addAll(listOfPeriode);
    }

    @Override
    public String search() {
        logger.info("[RekruitmenAction.search] start process >>>");

        Rekruitmen searchRekruitmen = getRekruitmen();
        List<Rekruitmen> listOfsearchRekruitmen = new ArrayList();
        List<RekruitmenStatus> listOfsearchRekruitmenStatus = new ArrayList();
        List<StudyCalonPegawai> listOfsearchRekruitmenStudy = new ArrayList();

        try {
            listOfsearchRekruitmen = rekruitmenBoProxy.getByCriteria(searchRekruitmen);
            listOfsearchRekruitmenStatus = rekruitmenBoProxy.getByCriteriaStatus(searchRekruitmen);
            listOfsearchRekruitmenStudy = rekruitmenBoProxy.getByCriteriaStudy(searchRekruitmen);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("ListOfResultRekruitmenStatus");
        session.removeAttribute("listOfResultRekruitmenStudy");
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchRekruitmen);
        session.setAttribute("listOfResultRekruitmenStudy", listOfsearchRekruitmenStudy);
        session.setAttribute("ListOfResultRekruitmenStatus", listOfsearchRekruitmenStatus);

        logger.info("[RekruitmenAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[RekruitmenAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[RekruitmenAction.initForm] end process >>>");
        return INPUT;
    }

    public String Form() {
        String userLogin = CommonUtil.userIdLogin();
        logger.info("[RekruitmenAction.search] start process >>>");

        Rekruitmen searchRekruitmen = new Rekruitmen();
//        searchRekruitmen.setNip(userLogin);
        searchRekruitmen.setFlag("Y");
        List<Rekruitmen> listOfsearchRekruitmen = new ArrayList();
        List<SppdReroute> sppdRerouteList = new ArrayList();

        try {
            listOfsearchRekruitmen = rekruitmenBoProxy.getByCriteria(searchRekruitmen);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchRekruitmen);
        session.removeAttribute("listSppdPersonAnggota");
        session.setAttribute("listSppdPersonAnggota", sppdRerouteList);

        logger.info("[RekruitmenAction.search] end process <<<");

        logger.info("[RekruitmenAction.edit] start process >>>");
        String itemId = userLogin;
        String itemFlag = "Y";

        Rekruitmen editRekruitmen = new Rekruitmen();

        if(itemFlag != null){
            try {
                editRekruitmen = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getPersonalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[RekruitmenAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[RekruitmenAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRekruitmen != null) {
//                editRekruitmen.setNamaPegawai(editRekruitmen.getGelarDepan() + ' ' + editRekruitmen.getNamaPegawai() + ' ' +editRekruitmen.getGelarBelakang());
//                editRekruitmen.setAlamat(editRekruitmen.getProvinsiId() + ' ' + editRekruitmen.getKabupatenId() + ' ' +editRekruitmen.getKecamatanId() + ' ' + editRekruitmen.getDesaId() + ' ' +editRekruitmen.getAlamat());
                editRekruitmen.setTempatLahir(editRekruitmen.getTempatLahir() + " / " +editRekruitmen.getStTanggalLahir());
                setRekruitmen(editRekruitmen);
            } else {
               /* editRekruitmen.setFlag(itemFlag);
                editRekruitmen.setNip(itemId);
                setRekruitmen(editRekruitmen);
                addActionError("Error, Unable to find data with id = " + itemId);*/
                return INPUT;
            }
        } else {
/*
            editRekruitmen.setNip(itemId);
*/
            editRekruitmen.setFlag(getFlag());
            setRekruitmen(editRekruitmen);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        //Search Training Person List

        String userId = CommonUtil.userIdLogin();
        TrainingPerson trainingPerson = new TrainingPerson();
        trainingPerson.setPersonId(userId);
        trainingPerson.setFlag("Y");

        CutiPegawai cutiPegawai = new CutiPegawai();
        cutiPegawai.setNip(userId);
        cutiPegawai.setFlag("Y");

        IjinKeluar ijinKeluar = new IjinKeluar();
        ijinKeluar.setNip(userId);
        ijinKeluar.setFlag("Y");

        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setNip(userId);
        notifikasi.setFlag("Y");

        List<TrainingPerson> trainingPersonList = new ArrayList<TrainingPerson>();
        List<CutiPegawai> cutiPegawaiList = new ArrayList<CutiPegawai>();
        List<IjinKeluar> ijinKeluarList = new ArrayList<IjinKeluar>();
        List<Notifikasi> notifikasiList = new ArrayList<Notifikasi>();

        try {

            trainingPersonList = rekruitmenBoProxy.getListTrainingPerson(trainingPerson);
            cutiPegawaiList = cutiPegawaiBoProxy.getByCriteria(cutiPegawai);
            ijinKeluarList = ijinKeluarBoProxy.getByCriteria(ijinKeluar);
            notifikasiList = notifikasiBoProxy.getByCriteria(notifikasi);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        if (trainingPersonList != null){
            session.removeAttribute("listOfResultPerson");
            session.setAttribute("listOfResultPerson", trainingPersonList);
        }
        if (cutiPegawaiList != null){
            session.removeAttribute("listOfResultCutiPegawai");
            session.setAttribute("listOfResultCutiPegawai", cutiPegawaiList);
        }
        if (ijinKeluarList != null){
            session.removeAttribute("listOfResultIjinKeluar");
            session.setAttribute("listOfResultIjinKeluar", ijinKeluarList);
        }
        if (notifikasiList != null){
            session.removeAttribute("listOfResultNotifikasi");
            session.setAttribute("listOfResultNotifikasi", notifikasiList);
        }


        setAddOrEdit(true);
        logger.info("[RekruitmenAction.edit] end process >>>");
        return "form_user";
    }

    public String initPersonal() {
        logger.info("[RekruitmenAction.search] start process >>>");

        Rekruitmen searchRekruitmen = new Rekruitmen();
        searchRekruitmen.setFlag("Y");
        List<Rekruitmen> listOfsearchRekruitmen = new ArrayList();

        try {
            listOfsearchRekruitmen = rekruitmenBoProxy.getByCriteria(searchRekruitmen);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPersonal");
        session.setAttribute("listOfResultPersonal", listOfsearchRekruitmen);

        logger.info("[RekruitmenAction.search] end process <<<");

        return "";
    }
    public String viewDoc(){
        logger.info("[BiayaPengobatanAction.viewDoc] start process >>>");
        String id = getId();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenUploadDoc> listRekruitmenDocument = (List<RekruitmenUploadDoc>) session.getAttribute("listOfResultRekruitmenDocument");

        boolean isImg = false;

        for (RekruitmenUploadDoc listData : listRekruitmenDocument){
            if (id.equalsIgnoreCase(listData.getUploadDocRekId())){
                if ("IMG".equalsIgnoreCase(listData.getFileType())){
                    listData.setFilePath("/hris/pages/upload/file/rekruitmen/"+listData.getUploadFile());
                    isImg = true;
                }
                if ("PDF".equalsIgnoreCase(listData.getFileType())){
                    listData.setFilePath("/hris/pages/upload/file/rekruitmen/"+listData.getUploadFile());
                }
                setRekruitmenUploadDoc(listData);
            }
        }

        if (isImg){
            return "init_view_img";
        } else {
            return "init_view_pdf";
        }
    }
    public List<StudyCalonPegawai> searchRekruitmenStudyPerson(String studyId) {
        logger.info("[RekruitmenPabrikAction.searchRekruitmenPabrikPerson] start process >>>");

        List<StudyCalonPegawai> rekruitmenStudyPersons = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenBo rekruitmenBo = (RekruitmenBo) ctx.getBean("rekruitmenBoProxy");

            rekruitmenStudyPersons = rekruitmenBo.getComboRekruitmenStudyPerson(studyId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return rekruitmenStudyPersons;
    }

    public String cetakKontrak(){
        logger.info("[TrainingAction.saveEdit] end process >>>");

        String id = getId();
        Rekruitmen rekruitmen = new Rekruitmen();
        Biodata direkturUtama = new Biodata();
        List<RekruitmenUraianKerjaan> listURaianPekerjaan = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakJangkaWaktu = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakUpah = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakFasilitas = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakKewajibanPihakPertama = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakKewajibanPihakKedua = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakCutiLembur = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakTatib = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakPemutusHubungan = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakPenutup = new ArrayList<>();
        List<RekruitmenKontrak> listRekruitmenKontrakPeraturan = new ArrayList<>();


        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        try {
            rekruitmen = rekruitmenBoProxy.getPrintRekruitmen(id);
            direkturUtama = biodataBo.getDirekturUtama();
            listURaianPekerjaan = rekruitmenBoProxy.getListUraianPekerjaan(rekruitmen.getCalonPegawaiId(),"Y");
            listRekruitmenKontrakFasilitas=rekruitmenBoProxy.getListRekruitmenKontrakFasilitas("Y",rekruitmen.getPositionId(),rekruitmen.getGolongan(),rekruitmen.getBranchId());
            listRekruitmenKontrakKewajibanPihakPertama=rekruitmenBoProxy.getListRekruitmenKontrakKewajibanPihakPertama("Y");
            listRekruitmenKontrakKewajibanPihakKedua=rekruitmenBoProxy.getListRekruitmenKontrakKewajibanPihakKedua("Y");
            listRekruitmenKontrakCutiLembur=rekruitmenBoProxy.getListRekruitmenKontrakCutiLembur("Y");
            listRekruitmenKontrakTatib=rekruitmenBoProxy.getListRekruitmenKontrakTatib("Y");
            listRekruitmenKontrakPemutusHubungan=rekruitmenBoProxy.getListRekruitmenKontrakPemutusanHubungan("Y");
            listRekruitmenKontrakPenutup=rekruitmenBoProxy.getListRekruitmenKontrakPenutup("Y");
            listRekruitmenKontrakPeraturan=rekruitmenBoProxy.getListRekruitmenKontrakPeraturan("Y");
            listRekruitmenKontrakJangkaWaktu=rekruitmenBoProxy.getListRekruitmenKontrakJangkaWaktu("Y",rekruitmen.getStKontrakDari(),rekruitmen.getStKontrakSelesai(),rekruitmen.getBagianName(),rekruitmen.getBranchName(),rekruitmen.getAlamatUnit());
            listRekruitmenKontrakUpah=rekruitmenBoProxy.getListRekruitmenKontrakUpah("Y",rekruitmen.getUpah());

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "medicalRecordBo.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (rekruitmen != null){

            String par1Hari = "Pada hari <b>"+CommonUtil.convertDateToDay(rekruitmen.getTanggalAktif())+"</b> , tanggal <b>"+CommonUtil.convertDateToTanggalTerbilang(rekruitmen.getTanggalAktif())+"</b> , bulan <b>"+CommonUtil.convertDateToMonth(rekruitmen.getTanggalAktif())+"</b> , tahun <b>"+CommonUtil.convertDateToYearTerbilang(rekruitmen.getTanggalAktif())+" ( "+CommonUtil.convertDateToString(rekruitmen.getTanggalAktif())+" )</b> , bertempat di Kantor Direksi PT Pabrik Gula Rajawali I, Jalan Undaan Kulon No. 57-59, Surabaya, yang bertanda tangan di bawah ini : ";
            String par1Direktur = "Direktur Utama PT Pabrik Gula Rajawali I, berdasarkan Surat Keputusan Pemegang Saham di Luar Rapat Umum Pemegang Saham PT Pabrik Gula Rajawali I Nomor: 36/Kep.PS/RNI.01/V/2018 tanggal 14 Mei 2018 tentang Pengukuhan Pelaksana Tugas Direktur Utama Direktur Utama PT Pabrik Gula Rajawali I, dalam hal ini sah bertindak dan mewakili untuk dan atas nama PT Pabrik Gula Rajawali I, yang berkedudukan di Jakarta Selatan dan berkantor di Jl. Undaan Kulon no. 57-59, Surabaya, 60274, Jawa Timur untuk selanjutnya disebut sebagai <b>Pihak Pertama</b>.";
            String par1Nama="Bertindak atas nama dan diri sendiri, dengan identitas Kartu Tanda Penduduk nomor "+rekruitmen.getNoKtp()+", tanggal lahir "+rekruitmen.getStTanggalLahir()+", bertempat tinggal di "+rekruitmen.getAlamat()+" "+rekruitmen.getRtRw()+" "+rekruitmen.getDesaName()+" "+rekruitmen.getKecamatanName()+" "+rekruitmen.getKotaName()+" "+rekruitmen.getProvinsiName()+", untuk selanjutnya dalam perjanjian ini disebut <b>Pihak Kedua</b>.";
            String par1Perkenalan="<b>Pihak Pertama</b> dan <b>Pihak Kedua</b> secara sendiri - sendiri disebut <b>Pihak</b> dan secara bersama - sama selanjutnya disebut <b>Para Pihak</b>.<br>\n" +
                    "<b>Para Pihak</b> dengan ini terlebih dahulu menerangkan hal-hal sebagai berikut:";
            String par1Menerangkan1="Bahwa <b>Pihak Pertama</b> adalah Perusahaan Industri gula tebu , yang mempunyai 2 ( dua ) unit usaha Pabrik Gula yaitu Pabrik Gula Krebet Baru dengan tempat usaha berlokasi di Desa Krebet, Bululawang , Kabupaten Malang dan Pabrik Gula Rejo Agung Baru dengan tempat usahanya berlokasi di Desa Patihan, Kecamatan Mangunhardjo,Kotamadya Madiun yang menghasilkan gula.";
            String par1Menerangkan2="Bahwa <b>Pihak Kedua</b> setuju untuk mengikatkan diri dengan <b>Pihak Pertama</b> untuk melaksanakan perjanjian kerja waktu tertentu di PT Pabrik Gula Rajawali 1.";
            String par1Menerangkan3="Selanjutnya, berdasarkan hal – hal tersebut diatas, <b>Para Pihak</b> bermaksud untuk membuat dan menandatangani Perjanjian Kerja Waktu Tertentu (PKWT), selanjutnya disebut <b>\"Perjanjian\"</b>, dengan syarat-syarat dan ketentuan-ketentuan sebagai berikut :";

            String par6NomorSurat="Lampiran SK PKWT No : "+rekruitmen.getNoKontrak();

            JRBeanCollectionDataSource itemUraianPekerjaan = new JRBeanCollectionDataSource(listURaianPekerjaan);
            JRBeanCollectionDataSource itemPasal2JangkaWaktu = new JRBeanCollectionDataSource(listRekruitmenKontrakJangkaWaktu);
            JRBeanCollectionDataSource itemPasal3Upah = new JRBeanCollectionDataSource(listRekruitmenKontrakUpah);
            JRBeanCollectionDataSource itemPasal4Fasilitas = new JRBeanCollectionDataSource(listRekruitmenKontrakFasilitas);
            JRBeanCollectionDataSource itemPasal5KewajibanPihakPertama = new JRBeanCollectionDataSource(listRekruitmenKontrakKewajibanPihakPertama);
            JRBeanCollectionDataSource itemPasal6KewajibanPihakKedua = new JRBeanCollectionDataSource(listRekruitmenKontrakKewajibanPihakKedua);
            JRBeanCollectionDataSource itemPasal7CutiDanLembur = new JRBeanCollectionDataSource(listRekruitmenKontrakCutiLembur);
            JRBeanCollectionDataSource itemPasal8TataTertib = new JRBeanCollectionDataSource(listRekruitmenKontrakTatib);
            JRBeanCollectionDataSource itemPasal9PemutusHubungan = new JRBeanCollectionDataSource(listRekruitmenKontrakPemutusHubungan);
            JRBeanCollectionDataSource itemPasal10Penutup = new JRBeanCollectionDataSource(listRekruitmenKontrakPenutup);
            JRBeanCollectionDataSource itemPeraturanDanTatibPerusahaan = new JRBeanCollectionDataSource(listRekruitmenKontrakPeraturan);

            reportParams.put("itemPasal1UraianPekerjaan", itemUraianPekerjaan);
            reportParams.put("itemPasal2JangkaWaktu", itemPasal2JangkaWaktu);
            reportParams.put("itemPasal3Upah", itemPasal3Upah);
            reportParams.put("itemPasal4Fasilitas", itemPasal4Fasilitas);
            reportParams.put("itemPasal5KewajibanPihakPertama", itemPasal5KewajibanPihakPertama);
            reportParams.put("itemPasal6KewajibanPihakKedua", itemPasal6KewajibanPihakKedua);
            reportParams.put("itemPasal7CutiDanLembur", itemPasal7CutiDanLembur);
            reportParams.put("itemPasal8TataTertib", itemPasal8TataTertib);
            reportParams.put("itemPasal9PemutusHubungan", itemPasal9PemutusHubungan);
            reportParams.put("itemPasal10Penutup", itemPasal10Penutup);
            reportParams.put("itemPeraturanDanTatibPerusahaan", itemPeraturanDanTatibPerusahaan);
            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("id", id);
            reportParams.put("nama", rekruitmen.getNamaCalonPegawai());
            reportParams.put("nomorSurat", "Nomor : "+rekruitmen.getNoKontrak());
            reportParams.put("namaDirektur", direkturUtama.getNamaPegawai() );
            reportParams.put("par1Hari", par1Hari);
            reportParams.put("par1Direktur", par1Direktur);
            reportParams.put("par1Nama", par1Nama);
            reportParams.put("par1Perkenalan", par1Perkenalan);
            reportParams.put("par1Menerangkan1", par1Menerangkan1);
            reportParams.put("par1Menerangkan2", par1Menerangkan2);
            reportParams.put("par1Menerangkan3", par1Menerangkan3);
            reportParams.put("par6NomorSurat", par6NomorSurat);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "printSuratJaminan");
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
        return "print_kontrak_rekruitmen";
    }

    public String searchProfilPhoto (String id){
        Rekruitmen rekruitmen = new Rekruitmen();
        List<Rekruitmen> searchRekruitmen = new ArrayList<>();
        rekruitmen.setCalonPegawaiId(id);
        String urlPhoto="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekruitmenBo rekruitmenBo = (RekruitmenBo) ctx.getBean("rekruitmenBoProxy");
        searchRekruitmen = rekruitmenBo.getByCriteria(rekruitmen);
        for (Rekruitmen rekruitmen1 : searchRekruitmen){
            urlPhoto= rekruitmen1.getFotoUpload();
        }
        return urlPhoto;
    }
    public Rekruitmen searchProfilPhotoBySession (String id){
        Rekruitmen rekruitmen = new Rekruitmen();
        List<Rekruitmen> searchRekruitmen = new ArrayList<>();
        rekruitmen.setCalonPegawaiId(id);
        String urlPhoto="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        searchRekruitmen = (List<Rekruitmen>) session.getAttribute("listOfResultProfil");
        for (Rekruitmen rekruitmen1 : searchRekruitmen){
            urlPhoto= "/hris/pages/upload/image/profile/"+rekruitmen1.getFotoUpload();
        }
        rekruitmen.setFotoUpload(urlPhoto);

        return rekruitmen;
    }

    public String printReportRekruitmen(){
        logger.info("[BiodataAction.printReportRekruitmen] start process >>>");

        if (getId() != null) {
            Rekruitmen result = new Rekruitmen();
            List<RekruitmenStatus> listStatusRekruitmen = new ArrayList<>();
            List<StudyCalonPegawai> listPendidikan = new ArrayList<>();
            List<RekruitmenUploadDoc> listDokumen= new ArrayList<>();

            try {
                listStatusRekruitmen = rekruitmenBoProxy.getListRekruitmenStatusBo(getId(),"Y");
                listPendidikan = rekruitmenBoProxy.getListRekruitmenStudyBo(getId(),"Y");
                listDokumen = rekruitmenBoProxy.getListRekruitmenDocumentBo(getId(),"Y");
                result = rekruitmenBoProxy.getPrintRekruitmen(getId());

            }catch (Exception e){
                Long logId = null;
                logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "printReportBiodata");
                logger.error("[BiodataAction.printReportRekruitmen] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            }

            JRBeanCollectionDataSource itemStatusRekruitmen = new JRBeanCollectionDataSource(listStatusRekruitmen);
            JRBeanCollectionDataSource itemPendidikan = new JRBeanCollectionDataSource(listPendidikan);
            JRBeanCollectionDataSource itemDokumen = new JRBeanCollectionDataSource(listDokumen);

            String foto = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() +
                    CommonConstant.RESOURCE_PATH_USER_UPLOAD + result.getFotoUpload();


            if (!new File(foto).exists()){
                foto = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() +
                        CommonConstant.RESOURCE_PATH_USER_UPLOAD + "img_avatar.png";
            }

            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("urlFoto", foto);
            reportParams.put("capegId", result.getCalonPegawaiId());
            reportParams.put("nama", result.getNamaCalonPegawai());
            reportParams.put("ttl", result.getTempatLahir()+","+result.getStTanggalLahir());
            reportParams.put("jkel", result.getJenisKelamin());
            reportParams.put("noHP", result.getNoTelp());
            reportParams.put("noKtp", result.getNoKtp());
            reportParams.put("pendidikanT", pendidikanTerakhir(listPendidikan));
            reportParams.put("alamatRumah", result.getAlamat());
            reportParams.put("provinsi", result.getProvinsiName());
            reportParams.put("kabupaten", result.getKotaName());
            reportParams.put("kecamatan", result.getKecamatanName());
            reportParams.put("desa", result.getDesaName());
            reportParams.put("rt", result.getRtRw());
            reportParams.put("calonJabatan", result.getPosisiName());
            reportParams.put("bagian", result.getBagianName());
            reportParams.put("statusKawin", result.getStatusKeluarga());
            reportParams.put("keterangan", "-");
            reportParams.put("titleReport", "REKAP REKRUITMEN");
            reportParams.put("itemStatusRekruitmen", itemStatusRekruitmen);
            reportParams.put("itemPendidikan", itemPendidikan);
            reportParams.put("itemDokumen", itemDokumen);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "printReportBiodata");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.printReportRekruitmen] Error when downloading ,", e1);
                }
                logger.error("[BiodataAction.printReportRekruitmen] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[BiodataAction.printReportBiodata] Error when print report, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, please inform to your admin.");
        }

        logger.info("[BiodataAction.printReportRekruitmen] end process <<<");

        return "print_report_rekruitmen";
    }
    public String printReportPerStatusRekruitmen(){
        logger.info("[BiodataAction.printReportRekruitmen] start process >>>");

        if (getId() != null) {
            Rekruitmen result = new Rekruitmen();
            RekruitmenStatus rekruitmenStatus = new RekruitmenStatus();
            List<StudyCalonPegawai> listPendidikan = new ArrayList<>();
            List<RekruitmenUploadDoc> listDokumen= new ArrayList<>();

            try {
                listPendidikan = rekruitmenBoProxy.getListRekruitmenStudyBo(getId(),"Y");
                listDokumen = rekruitmenBoProxy.getListRekruitmenDocumentBo(getId(),"Y");
                result = rekruitmenBoProxy.getPrintRekruitmen(getId());
                rekruitmenStatus = rekruitmenBoProxy.getStatusById(getId(),getStatusId());

            }catch (Exception e){
                Long logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "printReportBiodata");
                logger.error("[BiodataAction.printReportRekruitmen] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            }

            JRBeanCollectionDataSource itemPendidikan = new JRBeanCollectionDataSource(listPendidikan);
            JRBeanCollectionDataSource itemDokumen = new JRBeanCollectionDataSource(listDokumen);

            String foto = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() +
                    CommonConstant.RESOURCE_PATH_USER_UPLOAD + result.getFotoUpload();


            if (!new File(foto).exists()){
                foto = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() +
                        CommonConstant.RESOURCE_PATH_USER_UPLOAD + "img_avatar.png";
            }

            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("urlFoto", foto);
            reportParams.put("capegId", result.getCalonPegawaiId());
            reportParams.put("nama", result.getNamaCalonPegawai());
            reportParams.put("ttl", result.getTempatLahir()+","+result.getStTanggalLahir());
            reportParams.put("jkel", result.getJenisKelamin());
            reportParams.put("noHP", result.getNoTelp());
            reportParams.put("noKtp", result.getNoKtp());
            reportParams.put("pendidikanT", pendidikanTerakhir(listPendidikan));
            reportParams.put("alamatRumah", result.getAlamat());
            reportParams.put("provinsi", result.getProvinsiName());
            reportParams.put("kabupaten", result.getKotaName());
            reportParams.put("kecamatan", result.getKecamatanName());
            reportParams.put("desa", result.getDesaName());
            reportParams.put("rt", result.getRtRw());
            reportParams.put("calonJabatan", result.getPosisiName());
            reportParams.put("bagian", result.getBagianName());
            reportParams.put("statusKawin", result.getStatusKeluarga());
            reportParams.put("keterangan", rekruitmenStatus.getKeterangan());
            reportParams.put("statusSaatIni", rekruitmenStatus.getStatusRekruitmenName());
            reportParams.put("titleReport", "REPORT REKRUITMEN");
            reportParams.put("itemPendidikan", itemPendidikan);
            reportParams.put("itemDokumen", itemDokumen);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = rekruitmenBoProxy.saveErrorMessage(e.getMessage(), "printReportBiodata");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.printReportRekruitmen] Error when downloading ,", e1);
                }
                logger.error("[BiodataAction.printReportRekruitmen] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[BiodataAction.printReportBiodata] Error when print report, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, please inform to your admin.");
        }

        logger.info("[BiodataAction.printReportRekruitmen] end process <<<");

        return "print_report_per_status_rekruitmen";
    }

    private String pendidikanTerakhir ( List<StudyCalonPegawai> studyCalonPegawaiList ){
        String pendidikanTerakhir = "";
        int no = 0;

        for (StudyCalonPegawai studyCalonPegawai : studyCalonPegawaiList ){
            if (studyCalonPegawai.getTipeStudy().equalsIgnoreCase("SD")&&no<=1||studyCalonPegawai.getTipeStudy().equalsIgnoreCase("SD")&&no==0){
                pendidikanTerakhir="SD";
                no=1;
            }else if (studyCalonPegawai.getTipeStudy().equalsIgnoreCase("SMP")&&no<=2||studyCalonPegawai.getTipeStudy().equalsIgnoreCase("SMP")&&no==0){
                pendidikanTerakhir="SMP";
                no=2;
            }else if (studyCalonPegawai.getTipeStudy().equalsIgnoreCase("SMA")&&no<=3||studyCalonPegawai.getTipeStudy().equalsIgnoreCase("SMA")&&no==0){
                pendidikanTerakhir="SMA";
                no=3;
            }else if (studyCalonPegawai.getTipeStudy().equalsIgnoreCase("S1")&&no<=4||studyCalonPegawai.getTipeStudy().equalsIgnoreCase("S1")&&no==0){
                pendidikanTerakhir="S1";
                no=4;
            }else if (studyCalonPegawai.getTipeStudy().equalsIgnoreCase("S2")&&no<=5||studyCalonPegawai.getTipeStudy().equalsIgnoreCase("S2")&&no==0){
                pendidikanTerakhir="S2";
                no=5;
            }else if (studyCalonPegawai.getTipeStudy().equalsIgnoreCase("S3")&&no<=6||studyCalonPegawai.getTipeStudy().equalsIgnoreCase("S3")&&no==0){
                pendidikanTerakhir="S3";
                no=6;
            }
        }
        return pendidikanTerakhir;
    }

    public boolean cekNip ( String nip ){
        logger.info("[RekruitmenAction.cekNip] start process >>>");
        List<Biodata> biodataList = new ArrayList<>();
        boolean statusNip = false;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        Biodata biodata = new Biodata();
        biodata.setNip(nip);

        biodataList = biodataBo.getByCriteria(biodata);


        if (biodataList.size()!=0){
            statusNip=true;
        }

        logger.info("[RekruitmenAction.cekNip] end process <<<");
        return statusNip;
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
