package com.neurix.hris.master.biodata.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.dao.PelatihanJabatanUserDao;
import com.neurix.hris.master.biodata.model.*;
import com.neurix.hris.master.ijin.model.Ijin;
import com.neurix.hris.master.jenisPegawai.model.JenisPegawai;
import com.neurix.hris.master.keluarga.model.Keluarga;
import com.neurix.hris.master.profesi.bo.ProfesiBo;
import com.neurix.hris.master.profesi.model.Profesi;
import com.neurix.hris.master.reward.model.Reward;
import com.neurix.hris.master.sertifikat.model.Sertifikat;
import com.neurix.hris.master.study.bo.StudyBo;
import com.neurix.hris.master.study.model.Study;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.lembur.bo.LemburBo;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.mutasi.bo.MutasiBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.payroll.bo.PayrollBo;
import com.neurix.hris.transaksi.payroll.model.Payroll;
import com.neurix.hris.transaksi.payroll.model.PayrollPendidikan;
import com.neurix.hris.transaksi.personilPosition.model.HistoryJabatanPegawai;
import com.neurix.hris.transaksi.personilPosition.model.ImtHrisHistoryJabatanPegawaiEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.sppd.bo.SppdBo;
import com.neurix.hris.transaksi.sppd.model.Notif;
import com.neurix.hris.transaksi.sppd.model.Sppd;
import com.neurix.hris.transaksi.sppd.model.SppdPerson;
import com.neurix.hris.transaksi.training.model.Training;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import com.neurix.hris.transaksi.sppd.model.SppdReroute;
import com.neurix.simrs.transaksi.CrudResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.json.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.taglibs.standard.lang.jpath.example.Person;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class BiodataAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(BiodataAction.class);
    private BiodataBo biodataBoProxy;
    private Biodata biodata;
    private File fileUpload;
    private CutiPegawaiBo cutiPegawaiBoProxy;
    private IjinKeluarBo ijinKeluarBoProxy;
    private NotifikasiBo notifikasiBoProxy;
    private LemburBo lemburBoProxy;
    private AbsensiBo absensiBoProxy;
    private StudyBo studyBoProxy;
    private SppdBo sppdBoProxy;
    private PayrollBo payrollBoProxy;
    private PengalamanKerja pengalamanKerja;
    private Reward reward;
    private Sertifikat sertifikat;
    private PelatihanJabatanUser pelatihanJabatanUser;
    private String tipe;
    private List<JenisPegawai> listOfComboJenisPegawai = new ArrayList<>();

    public List<JenisPegawai> getListOfComboJenisPegawai() {
        return listOfComboJenisPegawai;
    }

    public void setListOfComboJenisPegawai(List<JenisPegawai> listOfComboJenisPegawai) {
        this.listOfComboJenisPegawai = listOfComboJenisPegawai;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public PelatihanJabatanUser getPelatihanJabatanUser() {
        return pelatihanJabatanUser;
    }

    public void setPelatihanJabatanUser(PelatihanJabatanUser pelatihanJabatanUser) {
        this.pelatihanJabatanUser = pelatihanJabatanUser;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public PengalamanKerja getPengalamanKerja() {
        return pengalamanKerja;
    }

    public void setPengalamanKerja(PengalamanKerja pengalamanKerja) {
        this.pengalamanKerja = pengalamanKerja;
    }

    public PayrollBo getPayrollBoProxy() {
        return payrollBoProxy;
    }

    public void setPayrollBoProxy(PayrollBo payrollBoProxy) {
        this.payrollBoProxy = payrollBoProxy;
    }

    public SppdBo getSppdBoProxy() {
        return sppdBoProxy;
    }

    public void setSppdBoProxy(SppdBo sppdBoProxy) {
        this.sppdBoProxy = sppdBoProxy;
    }

    public StudyBo getStudyBoProxy() {
        return studyBoProxy;
    }

    public void setStudyBoProxy(StudyBo studyBoProxy) {
        this.studyBoProxy = studyBoProxy;
    }

    public AbsensiBo getAbsensiBoProxy() {
        return absensiBoProxy;
    }

    public void setAbsensiBoProxy(AbsensiBo absensiBoProxy) {
        this.absensiBoProxy = absensiBoProxy;
    }

    public LemburBo getLemburBoProxy() {
        return lemburBoProxy;
    }

    public void setLemburBoProxy(LemburBo lemburBoProxy) {
        this.lemburBoProxy = lemburBoProxy;
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

    public BiodataBo getBiodataBoProxy() {
        return biodataBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public Biodata getBiodata() {
        return biodata;
    }

    public void setBiodata(Biodata biodata) {
        this.biodata = biodata;
    }

    private List<Biodata> initComboBiodata;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BiodataAction.logger = logger;
    }


    public List<Biodata> getInitComboBiodata() {
        return initComboBiodata;
    }

    public void setInitComboBiodata(List<Biodata> initComboBiodata) {
        this.initComboBiodata = initComboBiodata;
    }

    public Biodata init(String kode, String flag){
        logger.info("[BiodataAction.init] start process >>>");

        Biodata searchBiodata = new Biodata();
        searchBiodata.setFlag(flag);
        searchBiodata.setNip(kode);
        List<Biodata> listOfsearchBiodata = new ArrayList();

        try {
            listOfsearchBiodata = biodataBoProxy.getByCriteria(searchBiodata);
            for (Biodata biodata : listOfsearchBiodata){
                setBiodata(biodata);
            }
        }catch (GeneralBOException e){
            throw new GeneralBOException(e.getMessage());
        }

        return getBiodata();
    }

    public List<HistoryJabatanPegawai> historyJabatan(String nip){
        ImtHrisHistoryJabatanPegawaiEntity imtJabatan = null;
        List<HistoryJabatanPegawai> historyJabatan = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        historyJabatan = userBo.historyJabatanSys(nip);
        return historyJabatan;
    }

    public List<Payroll>
    searchPayroll(String nip){
        List <Payroll> imPayroll = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        imPayroll = userBo.searchPayrollSys(nip);
        return imPayroll;
    }

    public List initComboUser(String query) {
        logger.info("[UserAction.initComboUser] start process >>>");

        List<Biodata> biodataList = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");

        try {
            biodataList = userBo.getListOfPersonilPosition(query);
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

        return biodataList;
    }

    @Override
    public String add() {
        logger.info("[BiodataAction.add] start process >>>");
        Biodata addBiodata = new Biodata();

        String branchId = CommonUtil.userBranchLogin();
        if (branchId != null){
            addBiodata.setBranch(branchId);
        }else {
            addBiodata.setBranch("");
        }

        setBiodata(addBiodata);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

//        session.removeAttribute("historyJabatanForSmk");
//        session.removeAttribute("branchForSmk");
//        session.removeAttribute("positionIdForSmk");

        clearAllSession();

        logger.info("[BiodataAction.add] stop process >>>");
        if ("dokter".equalsIgnoreCase(getTipe())){
            return "init_add_dokter";
        }else{
            return "init_add_user";
        }
    }

    @Override
    public String edit() {
        logger.info("[BiodataAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Biodata editBiodata = new Biodata();

        if(itemFlag != null){
            try {
                editBiodata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getPersonalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[BiodataAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editBiodata != null) {
                setBiodata(editBiodata);
            } else {
                editBiodata.setFlag(itemFlag);
                editBiodata.setNip(itemId);
                setBiodata(editBiodata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editBiodata.setNip(itemId);
            editBiodata.setFlag(getFlag());
            setBiodata(editBiodata);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);

        //remove session
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSertifikat");
        session.removeAttribute("listOfPersonilPosition");

        logger.info("[BiodataAction.edit] end process >>>");

        if ("N".equalsIgnoreCase(editBiodata.getFlagDokterKso())){
            return "init_add_user";
        }else{
            return "init_add_dokter";
        }
    }

    @Override
    public String delete() {
        logger.info("[BiodataAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Biodata deleteBiodata = new Biodata();

        if (itemFlag != null ) {

            try {
                deleteBiodata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[BiodataAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteBiodata != null) {
                setBiodata(deleteBiodata);

            } else {
                deleteBiodata.setNip(itemId);
                deleteBiodata.setFlag(itemFlag);
                setBiodata(deleteBiodata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteBiodata.setNip(itemId);
            deleteBiodata.setFlag(itemFlag);
            setBiodata(deleteBiodata);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        setDelete(true);
        logger.info("[BiodataAction.delete] end process <<<");

        if ("N".equalsIgnoreCase(deleteBiodata.getFlagDokterKso())){
            return "init_add_user";
        }else{
            return "init_add_dokter";
        }
    }

    @Override
    public String view() {
        logger.info("[BiodataAction.saveUpload] start process >>>");
        try {
            Biodata editBiodata = getBiodata();
            if (this.fileUpload!=null) {

                //note : for linux directory
                //String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;

                //note : for windows directory
                String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;
                String fileName = editBiodata.getNip() + ".jpg";
                File fileToCreate = new File(filePath, fileName);

                //create file to save to folder '/upload'
                byte[] contentFile = null;
                try {
                    FileUtils.copyFile(this.fileUpload, fileToCreate);
                    contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                } catch (IOException e) {
                    Long logId = null;
                    try {
                        logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                    } catch (GeneralBOException e1) {
                        logger.error("[UserAction.save] Error when saving error,", e1);
                    }
                    logger.error("[UserAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                    return ERROR;
                }

                if (contentFile!=null) {
                    editBiodata.setContentFile(contentFile);
                    editBiodata.setFotoUpload(fileName);
                }
            }

            biodataBoProxy.saveUploadImage(editBiodata);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveUpload] end process <<<");

        return "success_save_edit";
    }

    public String uploadImage(){

        return "success_save_edit";
    }

    public List<PengalamanKerja> searchDataSessionPengalamanKerja() {
        logger.info("[BiodataAction.searchDataSessionPengalamanKerja] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengalamanKerja> listOfResult = (List<PengalamanKerja>) session.getAttribute("listPengalamanKerja");

        logger.info("[BiodataAction.searchDataSessionPengalamanKerja] end process >>>");
        return listOfResult;
    }

    public List<Reward> searchDataSessionReward() {
        logger.info("[BiodataAction.searchDataSessionReward] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Reward> listOfResult = (List<Reward>) session.getAttribute("listReward");

        logger.info("[BiodataAction.searchDataSessionReward] end process >>>");
        return listOfResult;
    }

    public List<Sertifikat> searchDataSessionSertifikat() {
        logger.info("[BiodataAction.searchDataSessionSertifikat] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Sertifikat> listOfResult = (List<Sertifikat>) session.getAttribute("listSertifikat");

        logger.info("[BiodataAction.searchDataSessionSertifikat] end process >>>");
        return listOfResult;
    }

    // Pengalaman Kerja Luar RNI (Lama 23/07/2019)
    /*public List<PengalamanKerja> searchDataPengalamanKerja(String nip) {
        logger.info("[BiodataAction.searchDataPengalamanKerja] start process >>>");

        List<PengalamanKerja> listOfsearchPengalamanKerja = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listOfsearchPengalamanKerja = biodataBo.searchDataPengalamanKerja(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.searchDataPengalaman");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchDataPengalaman] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.searchDataPengalaman] Error when Search Data Pengalaman Kerja," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[BiodataAction.searchDataPengalaman] end process >>>");
        return listOfsearchPengalamanKerja;
    }*/

    public List<PengalamanKerja> searchDataPengalamanKerja(String nip) {
        logger.info("[BiodataAction.searchDataPengalamanKerja] start process >>>");

        List<ImtHrisHistoryJabatanPegawaiEntity> listOfsearchPengalamanKerja = new ArrayList();
        List<PengalamanKerja> pengalamanKerjas = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            pengalamanKerjas = biodataBo.searchDataRiwayatKerja(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.searchDataPengalaman");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchDataPengalaman] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.searchDataPengalaman] Error when Search Data Pengalaman Kerja," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[BiodataAction.searchDataPengalaman] end process >>>");
        return pengalamanKerjas;
    }

    public List<Reward> searchDataReward(String nip) {
        logger.info("[BiodataAction.searchDataReward] start process >>>");

        List<Reward> listOfsearchReward = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listOfsearchReward = biodataBo.searchDataReward(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.searchDataPengalaman");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchDataReward] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.searchDataPengalaman] Error when Search Data Pengalaman Kerja," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[BiodataAction.searchDataReward] end process >>>");
        return listOfsearchReward;
    }

    public List<Sertifikat> searchDataSertifikat(String nip) {
        logger.info("[BiodataAction.searchDataSertifikat] start process >>>");

        List<Sertifikat> listOfsearchSertifikat = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listOfsearchSertifikat = biodataBo.searchDataSertifikat(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.searchDataPengalaman");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchDataReward] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.searchDataSertifikat] Error when Search Data Pengalaman Kerja," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[BiodataAction.searchDataSertifikat] end process >>>");
        return listOfsearchSertifikat;
    }

    public List<PelatihanJabatanUser> searchDataPelatihanJabatan(String nip) {
        logger.info("[BiodataAction.searchDataPelatihanJabatan] start process >>>");

        List<PelatihanJabatanUser> listOfsearchSertifikat = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listOfsearchSertifikat = biodataBo.searchDataPelatihanjabatanUser(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.searchDataPengalaman");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchDataReward] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.searchDataSertifikat] Error when Search Data Pengalaman Kerja," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[BiodataAction.searchDataPelatihanJabatan] end process >>>");
        return listOfsearchSertifikat;
    }

    @Override
    public String save() {

        // Sigit 2021-01-18, Mengambil Session ListOfPersonilPosition
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PersonilPosition> listOfResultPersonil = (List<PersonilPosition>) session.getAttribute("listOfPersonilPosition");

        if (isAddOrEdit()) {
            if (!isAdd()) {
                logger.info("[BiodataAction.saveEdit] start process >>>");
                try {
                    Biodata editBiodata = getBiodata();

                    // Sigit, 2020-01-06 Penamhan filter golongan != null, Start
                    if (editBiodata.getGolongan() != null && !"".equalsIgnoreCase(editBiodata.getGolongan())){
                        String golonganId = editBiodata.getGolongan().replace(",","");
                        editBiodata.setGolongan(golonganId);
                        editBiodata.setGolonganId(golonganId);
                    }

                    if (editBiodata.getGolongan() != null && !"".equalsIgnoreCase(editBiodata.getGolongan())){
                        String golonganId2 = editBiodata.getGolongan().replace(" ","");
                        editBiodata.setGolongan(golonganId2);
                        editBiodata.setGolonganId(golonganId2);
                    }
                    // END

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    if (editBiodata.getStTanggalLahir() != null && !"".equalsIgnoreCase(editBiodata.getStTanggalLahir())) {
                        editBiodata.setTanggalLahir(CommonUtil.convertToDate(editBiodata.getStTanggalLahir().replace(",","").replace(" ","")));
                    }

                    if (editBiodata.getStTanggalPensiun() != null && !"".equalsIgnoreCase(editBiodata.getStTanggalPensiun())) {
                        editBiodata.setTanggalPensiun(CommonUtil.convertToDate(editBiodata.getStTanggalPensiun().replace(",","").replace(" ","")));
                    }

                    if (editBiodata.getStTanggalMasuk() != null && !"".equalsIgnoreCase(editBiodata.getStTanggalMasuk())) {
                        editBiodata.setTanggalMasuk(CommonUtil.convertToDate(editBiodata.getStTanggalMasuk().replace(",","").replace(" ","")));
                    }

                    if (editBiodata.getStTanggalAktif() != null && !"".equalsIgnoreCase(editBiodata.getStTanggalAktif())) {
                        editBiodata.setTanggalAktif(CommonUtil.convertToDate(editBiodata.getStTanggalAktif().replace(",","").replace(" ","")));
                    }

                    if (editBiodata.getStTanggalPraPensiun() != null && !"".equalsIgnoreCase(editBiodata.getStTanggalPraPensiun())){
                        editBiodata.setTanggalPraPensiun(CommonUtil.convertToDate(editBiodata.getStTanggalPraPensiun()));
                    }

                    //RAKA-11JAN2021 ==> Menonaktifkan Cuti Diluar Tanggungan
                    if ("N".equalsIgnoreCase(editBiodata.getFlagCutiDiluarTanggungan())) {
                        editBiodata.setTanggalCutiDiluarTanggunganAwal(null);
                        editBiodata.setTanggalCutiDiluarTanggunganAkhir(null);
                    }
                    //RAKA-end

                    if (this.fileUpload!=null) {

//                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_PHOTO_PROFILE;
                        String fileName = editBiodata.getNip() + ".jpg";
                        File fileToCreate = new File(filePath, fileName);

                        //create file to save to folder '/upload'
                        byte[] contentFile = null;
                        try {
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                        } catch (IOException e) {
                            Long logId = null;
                            try {
                                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                            } catch (GeneralBOException e1) {
                                logger.error("[BiodataAction.save] Error when saving error,", e1);
                            }
                            logger.error("[BiodataAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                            return ERROR;
                        }

                        if (contentFile!=null) {
                            editBiodata.setContentFile(contentFile);
                            editBiodata.setFotoUpload(fileName);
                        }
                    }

                    editBiodata.setLastUpdateWho(userLogin);
                    editBiodata.setLastUpdate(updateTime);
                    editBiodata.setAction("U");
                    editBiodata.setFlag(editBiodata.getFlag());

                    // Sigit 2021-01-18, Set PersonilPosition pada object editBiodata
                    editBiodata.setListOfPersonilPosition(listOfResultPersonil);
                    // END

                    if ("Y".equalsIgnoreCase(editBiodata.getFlagDokterKso())){
                        biodataBoProxy.saveEditDokterKso(editBiodata);
                    } else {
                        biodataBoProxy.saveEdit(editBiodata);
                    }

                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
                    } catch (GeneralBOException e1) {
                        logger.error("[BiodataAction.saveEdit] Error when saving error,", e1);
                        throw new GeneralBOException(e1.getMessage());
                    }
                    logger.error("[BiodataAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                    throw new GeneralBOException(e.getMessage());
                }

                clearAllSession();
                logger.info("[BiodataAction.saveEdit] end process <<<");
                return "success_save_edit";
            } else {
                //add
                try {
                    Biodata biodata = getBiodata();
                    String golonganId ="";
                    if ("N".equalsIgnoreCase(biodata.getFlagDokterKso())){
                        golonganId = biodata.getGolongan().replace(",","");
                    }

                    biodata.setGolongan(golonganId);
                    biodata.setGolonganId(golonganId);
                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    if (biodata.getStTanggalLahir() != null && !"".equalsIgnoreCase(biodata.getStTanggalLahir())) {
                        biodata.setTanggalLahir(CommonUtil.convertToDate(biodata.getStTanggalLahir()));
                    }

                    if (biodata.getStTanggalPensiun() != null && !"".equalsIgnoreCase(biodata.getStTanggalPensiun())) {
                        biodata.setTanggalPensiun(CommonUtil.convertToDate(biodata.getStTanggalPensiun()));
                    }

                    if (biodata.getStTanggalAktif() != null && !"".equalsIgnoreCase(biodata.getStTanggalAktif())) {
                        biodata.setTanggalAktif(CommonUtil.convertToDate(biodata.getStTanggalAktif()));
                    }

                    if (biodata.getStTanggalPraPensiun() != null && !"".equalsIgnoreCase(biodata.getStTanggalPraPensiun())){
                        biodata.setTanggalPraPensiun(CommonUtil.convertToDate(biodata.getStTanggalPraPensiun()));
                    }

                    if (this.fileUpload!=null) {
//                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_PHOTO_PROFILE;
                        String fileName = biodata.getNip() + ".jpg";
                        File fileToCreate = new File(filePath, fileName);

                        //create file to save to folder '/upload'
                        byte[] contentFile = null;
                        try {
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                        } catch (IOException e) {
                            Long logId = null;
                            try {
                                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                            } catch (GeneralBOException e1) {
                                logger.error("[UserAction.save] Error when saving error,", e1);
                            }
                            logger.error("[UserAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                            return ERROR;
                        }

                        if (contentFile!=null) {
                            biodata.setContentFile(contentFile);
                            biodata.setFotoUpload(fileName);
                        }
                    }

                    biodata.setCreatedWho(userLogin);
                    biodata.setLastUpdate(updateTime);
                    biodata.setCreatedDate(updateTime);
                    biodata.setLastUpdateWho(userLogin);
                    biodata.setAction("C");
                    biodata.setStatusCaption("Online");
                    biodata.setFlag("Y");

                    biodata.setListOfPersonilPosition(listOfResultPersonil);

                    biodataBoProxy.saveAdd(biodata);
                }catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "pengalamanKerjaBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[BiodataAction.saveAdd] Error when saving error,", e1);
                        throw new GeneralBOException(e1.getMessage());
                    }
                    logger.error("[BiodataAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    throw new GeneralBOException(e.getMessage());
                }


                clearAllSession();
                logger.info("[pengalamanKerjaAction.saveAdd] end process >>>");
                return "success_save_add";
            }

        } else if (isDelete()) {

            logger.info("[BiodataAction.saveDelete] start process >>>");
            try {

                Biodata deleteBiodata = getBiodata();

                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                deleteBiodata.setLastUpdate(updateTime);
                deleteBiodata.setLastUpdateWho(userLogin);
                deleteBiodata.setAction("U");
                deleteBiodata.setFlag("N");

                biodataBoProxy.saveDelete(deleteBiodata);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.saveDelete] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[BiodataAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

            logger.info("[BiodataAction.saveDelete] end process <<<");

            return SUCCESS;

        }

        return null;
    }

    private void clearAllSession(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultBiodata");
        session.removeAttribute("historyJabatanForSmk");
        session.removeAttribute("branchForSmk");
        session.removeAttribute("positionIdForSmk");
        session.removeAttribute("listKeluarga");
        session.removeAttribute("listStudy");
        session.removeAttribute("listPengalamanKerja");
        session.removeAttribute("listReward");
        session.removeAttribute("listSertifikat");
        session.removeAttribute("listOfPersonilPosition");
    }

    public String saveEdit(){
        logger.info("[BiodataAction.saveEdit] start process >>>");
        try {

            Biodata editBiodata = getBiodata();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editBiodata.setLastUpdateWho(userLogin);
            editBiodata.setLastUpdate(updateTime);
            editBiodata.setAction("U");
            editBiodata.setFlag("Y");

            biodataBoProxy.saveEdit(editBiodata);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveCaption(String id, String Caption){
        logger.info("[BiodataAction.saveEdit] start process >>>");
        try {

            Biodata editBiodata = new Biodata();

            editBiodata.setNip(id);
            editBiodata.setStatusCaption(Caption);
            String userLogin = CommonUtil.userLogin();

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            biodataBo.saveEditCaption(editBiodata);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[BiodataAction.saveDelete] start process >>>");
        try {

            Biodata deleteBiodata = getBiodata();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteBiodata.setLastUpdate(updateTime);
            deleteBiodata.setLastUpdateWho(userLogin);
            deleteBiodata.setAction("U");
            deleteBiodata.setFlag("N");

            biodataBoProxy.saveDelete(deleteBiodata);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[BiodataAction.saveAdd] start process >>>");

        try {
            Biodata biodata = getBiodata();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if (biodata.getStTanggalLahir() != null && !"".equalsIgnoreCase(biodata.getStTanggalLahir())) {
                biodata.setTanggalLahir(CommonUtil.convertToDate(biodata.getStTanggalLahir()));
            }

            biodata.setCreatedWho(userLogin);
            biodata.setLastUpdate(updateTime);
            biodata.setCreatedDate(updateTime);
            biodata.setLastUpdateWho(userLogin);
            biodata.setAction("C");
            biodata.setStatusCaption("Online");
            biodata.setFlag("Y");

            biodataBoProxy.saveAdd(biodata);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "pengalamanKerjaBO.saveAdd");
            } catch (GeneralBOException e1) {
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[pengalamanKerjaAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultBiodata");

        logger.info("[pengalamanKerjaAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[BiodataAction.search] start process >>>");

        Biodata searchBiodata = getBiodata();
        List<Biodata> listOfsearchBiodata = new ArrayList();

        try {
            listOfsearchBiodata = biodataBoProxy.getByCriteria(searchBiodata);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        String branchId = CommonUtil.userBranchLogin();
        Biodata data = new Biodata();
        if (branchId != null){
            data.setBranch(branchId);
        }else {
            data.setBranch("");
        }

        biodata = data;

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultBiodata");
        session.setAttribute("listOfResultBiodata", listOfsearchBiodata);

        logger.info("[BiodataAction.search] end process <<<");

        return SUCCESS;
    }

    public Biodata detailBiodata(String nip){
        Biodata biodata = new Biodata();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        biodata = userBo.detailBiodataSys(nip);

        return biodata;
    }

    @Override
    public String initForm() {
        logger.info("[BiodataAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        String branchId = CommonUtil.userBranchLogin();
        Biodata data = new Biodata();
        if (branchId != null){
            data.setBranch(branchId);
        }else {
            data.setBranch("");
        }

        biodata = data;

        session.removeAttribute("listOfResultBiodata");
        logger.info("[BiodataAction.initForm] end process >>>");
        return INPUT;
    }

    public String Form() {
        String userLogin = CommonUtil.userIdLogin();
        logger.info("[BiodataAction.search] start process >>>");

        Biodata searchBiodata = new Biodata();
        searchBiodata.setNip(userLogin);
        searchBiodata.setNamaPegawai("");
        searchBiodata.setBranch("");
        searchBiodata.setDivisi("");
        searchBiodata.setTipePegawai("");
        searchBiodata.setFlag("Y");
        Biodata bio = null;
        List<SppdReroute> sppdRerouteList = new ArrayList();

        try {
            bio = biodataBoProxy.detailBiodataSys(userLogin);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listKeluarga");
        session.removeAttribute("listOfResultBiodata");
        session.removeAttribute("listSppdPersonAnggota");
        session.setAttribute("listSppdPersonAnggota", sppdRerouteList);

        logger.info("[BiodataAction.search] end process <<<");

        logger.info("[BiodataAction.edit] start process >>>");
        String itemId = userLogin;
        String itemFlag = "Y";

        if(bio != null) {
            String gelarDepan = "";
            String namaPegawai = "";
            String gelarBelakang = "";
            if(bio.getGelarDepan() != null){
                gelarDepan = bio.getGelarDepan() + " ";
            }
            if(bio.getNamaPegawai() != null){
                namaPegawai = bio.getNamaPegawai() + " ";
            }
            if(bio.getGelarBelakang() != null){
                gelarBelakang = bio.getGelarBelakang() + " ";
            }
            bio.setNamaPegawai(gelarDepan + namaPegawai + gelarBelakang);

            String provinsi = "";
            String kab = "";
            String kec = "";
            String desa = "";
            String alamat = "";
            String tempatLahir = "";
            String tanggalLahir = "";
            if(bio.getProvinsiName() != null){
                provinsi = bio.getProvinsiName() + " ";
            }
            if(bio.getKotaName() != null){
                kab = bio.getKotaName() +" ";
            }
            if(bio.getKecamatanName() != null){
                kec = bio.getKecamatanName() + " ";
            }
            if(bio.getDesaName() != null){
                desa = bio.getDesaName() + " ";
            }
            if(bio.getAlamat() != null){
                alamat = bio.getAlamat() + " ";
            }
            if(bio.getTempatLahir() != null){
                tempatLahir = bio.getTempatLahir();
            }
            if(bio.getStTanggalLahir() != null){
                tanggalLahir = CommonUtil.convertDateToString(bio.getTanggalLahir());
            }

            bio.setAlamat(provinsi + kab + kec + desa + alamat);
            bio.setTempatLahir(tempatLahir + " / " + tanggalLahir);
            setBiodata(bio);
        }

        //Search Training Person List

        String userId = CommonUtil.userIdLogin();
        TrainingPerson trainingPerson = new TrainingPerson();
        trainingPerson.setPersonId(userId);
        trainingPerson.setFlag("Y");

        CutiPegawai cutiPegawai = new CutiPegawai();
        cutiPegawai.setNip(userId);
        cutiPegawai.setFlag("Y");

        Lembur lembur = new Lembur();
        lembur.setNip(userId);
        lembur.setFlag("Y");

        AbsensiPegawai absensiPegawai = new AbsensiPegawai();
        absensiPegawai.setNip(userId);
        absensiPegawai.setFlag("Y");

        PersonilPosition personilPosition = new PersonilPosition();
        personilPosition.setNip(userId);
        personilPosition.setFlag("Y");

        IjinKeluar ijinKeluarKantor = new IjinKeluar();
        ijinKeluarKantor.setNip(userId);
        ijinKeluarKantor.setFlag("Y");
        ijinKeluarKantor.setFrom("ijinKeluarKantor");

        IjinKeluar ijinKeluar = new IjinKeluar();
        ijinKeluar.setNip(userId);
        ijinKeluar.setFlag("Y");
        ijinKeluar.setFrom("ijinKeluar");

        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setNip(userId);
        notifikasi.setFlag("Y");

        List<TrainingPerson> trainingPersonList = new ArrayList<TrainingPerson>();
        List<CutiPegawai> cutiPegawaiList = new ArrayList<CutiPegawai>();
        List<Lembur> lemburList = new ArrayList<Lembur>();
        List<IjinKeluar> ijinKeluarList = new ArrayList<IjinKeluar>();
        List<IjinKeluar> ijinKeluarKantorList = new ArrayList<IjinKeluar>();
        List<Notifikasi> notifikasiList = new ArrayList<Notifikasi>();
        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<AbsensiPegawai>();
        List<PersonilPosition> personilPositionList = new ArrayList<PersonilPosition>();

        try {

            trainingPersonList = biodataBoProxy.getListTrainingPerson(trainingPerson);
            cutiPegawaiList = cutiPegawaiBoProxy.getByCriteria(cutiPegawai);
            ijinKeluarList = ijinKeluarBoProxy.getByCriteria(ijinKeluar);
            ijinKeluarKantorList = ijinKeluarBoProxy.getByCriteria(ijinKeluarKantor);
            lemburList = lemburBoProxy.getByCriteria(lembur);
            notifikasiList = notifikasiBoProxy.getByCriteria(notifikasi);
            absensiPegawaiList = absensiBoProxy.getByCriteria(absensiPegawai);
            personilPositionList = biodataBoProxy.getByCriteriaPersonilPosition(personilPosition);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
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
        if (ijinKeluarKantorList != null){
            session.removeAttribute("listOfResultIjinKeluarKantor");
            session.setAttribute("listOfResultIjinKeluarKantor", ijinKeluarKantorList);
        }
        if (lemburList != null){
            session.removeAttribute("listOfResultLembur");
            session.setAttribute("listOfResultLembur", lemburList);
        }
        if (notifikasiList != null){
            session.removeAttribute("listOfResultNotifikasi");
            session.setAttribute("listOfResultNotifikasi", notifikasiList);
        }
        if (absensiPegawaiList != null){
            session.removeAttribute("listOfResultAbsensi");
            session.setAttribute("listOfResultAbsensi", absensiPegawaiList);
        }
        if (personilPositionList != null){
            session.removeAttribute("listOfResultJabatan");
            session.setAttribute("listOfResultJabatan", personilPositionList);
        }

        setAddOrEdit(true);
        logger.info("[BiodataAction.edit] end process >>>");
        return "form_user";
    }

    public String initPersonal() {
        logger.info("[BiodataAction.search] start process >>>");

        Biodata searchBiodata = new Biodata();
        searchBiodata.setFlag("Y");
        List<Biodata> listOfsearchBiodata = new ArrayList();

        try {
            listOfsearchBiodata = biodataBoProxy.getByCriteria(searchBiodata);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPersonal");
        session.setAttribute("listOfResultPersonal", listOfsearchBiodata);

        logger.info("[BiodataAction.search] end process <<<");

        return "";
    }
    public String viewCuti() {
        logger.info("[AlatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Biodata viewBiodata = new Biodata();

        if (itemFlag != null ) {

            try {
                viewBiodata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AlatAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[CutiPegawaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (viewBiodata != null) {
                setBiodata(viewBiodata);

            } else {
                viewBiodata.setNip(itemId);
                viewBiodata.setFlag(itemFlag);
                setBiodata(viewBiodata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            viewBiodata.setNip(itemId);
            viewBiodata.setFlag(itemFlag);
            setBiodata(viewBiodata);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        List<CutiPegawai> cutiPegawaiList = new ArrayList<>();
        CutiPegawai searchCutiPegawai = new CutiPegawai();
        searchCutiPegawai.setNip(itemId);
        searchCutiPegawai.setFlag(itemFlag);
        try {
            cutiPegawaiList = cutiPegawaiBoProxy.getByCriteria(searchCutiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getAlatById");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.delete] Error when retrieving delete data,", e1);
            }
            logger.error("[CutiPegawaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultCutiPegawai");
        session.setAttribute("listOfResultCutiPegawai", cutiPegawaiList);

        logger.info("[AlatAction.delete] end process <<<");
        return "init_view_cuti";
    }
    public String viewAbsensi() {
        logger.info("[AlatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Biodata viewBiodata = new Biodata();

        if (itemFlag != null ) {

            try {
                viewBiodata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AlatAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[CutiPegawaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }
            if (viewBiodata != null) {
                setBiodata(viewBiodata);
            } else {
                viewBiodata.setNip(itemId);
                viewBiodata.setFlag(itemFlag);
                setBiodata(viewBiodata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            viewBiodata.setNip(itemId);
            viewBiodata.setFlag(itemFlag);
            setBiodata(viewBiodata);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<>();
        AbsensiPegawai searchAbsensiPegawai = new AbsensiPegawai();
        searchAbsensiPegawai.setNip(itemId);
        searchAbsensiPegawai.setFlag(itemFlag);
        try {
            absensiPegawaiList = absensiBoProxy.getByCriteria(searchAbsensiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getAlatById");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.delete] Error when retrieving delete data,", e1);
            }
            logger.error("[CutiPegawaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultAbsensiPegawai");
        session.setAttribute("listOfResultAbsensiPegawai", absensiPegawaiList);

        logger.info("[AlatAction.delete] end process <<<");
        return "init_view_absensi";
    }
    public String viewStudy() {
        logger.info("[BiodataAction.viewStudy] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Biodata viewBiodata = new Biodata();

        if (itemFlag != null ) {

            try {
                viewBiodata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.viewStudy");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.viewStudy] Error when retrieving delete data,", e1);
                }
                logger.error("[BiodataAction.viewStudy] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (viewBiodata != null) {
                setBiodata(viewBiodata);

            } else {
                viewBiodata.setNip(itemId);
                viewBiodata.setFlag(itemFlag);
                setBiodata(viewBiodata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            viewBiodata.setNip(itemId);
            viewBiodata.setFlag(itemFlag);
            setBiodata(viewBiodata);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        List<Study> studyList = new ArrayList<>();
        Study searchStudy = new Study();
        searchStudy.setNip(itemId);
        searchStudy.setFlag(itemFlag);
        try {
            studyList = studyBoProxy.getByCriteria(searchStudy);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.viewStudy");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.viewStudy] Error when retrieving delete data,", e1);
            }
            logger.error("[BiodataAction.viewStudy] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultStudy");
        session.setAttribute("listOfResultStudy", studyList);

        logger.info("[AlatAction.viewStudy] end process <<<");
        return "init_view_study";
    }
    public String viewSppd() {
        logger.info("[BiodataAction.viewSppd] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        Biodata viewBiodata = new Biodata();

        if (itemFlag != null ) {
            try {
                viewBiodata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.viewSppd");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.viewSppd] Error when retrieving delete data,", e1);
                }
                logger.error("[BiodataAction.viewSppd] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (viewBiodata != null) {
                setBiodata(viewBiodata);

            } else {
                viewBiodata.setNip(itemId);
                viewBiodata.setFlag(itemFlag);
                setBiodata(viewBiodata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            viewBiodata.setNip(itemId);
            viewBiodata.setFlag(itemFlag);
            setBiodata(viewBiodata);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        List<SppdPerson> sppdPersonList = new ArrayList<>();
        SppdPerson searchSppd = new SppdPerson();
        searchSppd.setPersonId(itemId);
        searchSppd.setFlag(itemFlag);
        try {
            sppdPersonList = sppdBoProxy.getByCriteriaSppdPerson(searchSppd);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.viewSppd");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.viewSppd] Error when retrieving delete data,", e1);
            }
            logger.error("[BiodataAction.viewSppd] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultSppd");
        session.setAttribute("listOfResultSppd", sppdPersonList);

        logger.info("[AlatAction.viewStudy] end process <<<");
        return "init_view_sppd";
    }
    public String viewPayroll() {
        logger.info("[BiodataAction.viewSppd] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        Biodata viewBiodata = new Biodata();

        if (itemFlag != null ) {
            try {
                viewBiodata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.viewSppd");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.viewSppd] Error when retrieving delete data,", e1);
                }
                logger.error("[BiodataAction.viewSppd] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (viewBiodata != null) {
                setBiodata(viewBiodata);

            } else {
                viewBiodata.setNip(itemId);
                viewBiodata.setFlag(itemFlag);
                setBiodata(viewBiodata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            viewBiodata.setNip(itemId);
            viewBiodata.setFlag(itemFlag);
            setBiodata(viewBiodata);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        List<Payroll> payrollList = new ArrayList<>();
        Payroll searchPayroll = new Payroll();
        searchPayroll.setNip(itemId);
        searchPayroll.setFlag(itemFlag);
        try {
            payrollList = payrollBoProxy.getByCriteria(searchPayroll);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.viewSppd");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.viewSppd] Error when retrieving delete data,", e1);
            }
            logger.error("[BiodataAction.viewSppd] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPayroll");
        session.setAttribute("listOfResultPayroll", payrollList);

        logger.info("[AlatAction.viewStudy] end process <<<");
        return "init_view_payroll";
    }
    public String viewJabatan() {
        logger.info("[BiodataAction.viewSppd] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        Biodata viewBiodata = new Biodata();

        if (itemFlag != null ) {
            try {
                viewBiodata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.viewSppd");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.viewSppd] Error when retrieving delete data,", e1);
                }
                logger.error("[BiodataAction.viewSppd] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (viewBiodata != null) {
                setBiodata(viewBiodata);

            } else {
                viewBiodata.setNip(itemId);
                viewBiodata.setFlag(itemFlag);
                setBiodata(viewBiodata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            viewBiodata.setNip(itemId);
            viewBiodata.setFlag(itemFlag);
            setBiodata(viewBiodata);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        List<PersonilPosition> personilPositionList = new ArrayList<>();
        PersonilPosition searchPersonilPosition = new PersonilPosition();
        searchPersonilPosition.setNip(itemId);
        searchPersonilPosition.setFlag(itemFlag);
        try {
            personilPositionList = biodataBoProxy.getByCriteriaPersonilPosition(searchPersonilPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "BiodataAction.viewSppd");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.viewSppd] Error when retrieving delete data,", e1);
            }
            logger.error("[BiodataAction.viewSppd] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultJabatan");
        session.setAttribute("listOfResultJabatan", personilPositionList);

        logger.info("[AlatAction.viewStudy] end process <<<");
        return "init_view_jabatan";
    }

    public PengalamanKerja initSearchPengalamanKerja(String kode){
        logger.info("[BiodataAction.initSearchPengalamanKerja] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengalamanKerja> listOfResult = (List<PengalamanKerja>) session.getAttribute("listPengalamanKerja");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PengalamanKerja pengalamanKerja: listOfResult) {
                    if(kode.equalsIgnoreCase(pengalamanKerja.getPengalamanId())){
                        setPengalamanKerja(pengalamanKerja);
                        break;
                    }
                }
            } else {
                setPengalamanKerja(new PengalamanKerja());
            }
        }

        logger.info("[BiodataAction.initSearchPengalamanKerja] start process >>>");
        return getPengalamanKerja();
    }

    public Reward initSearchReward(String kode){
        logger.info("[BiodataAction.initSearchReward] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Reward> listOfResult = (List<Reward>) session.getAttribute("listReward");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Reward reward: listOfResult) {
                    if(kode.equalsIgnoreCase(reward.getRewardId())){
                        setReward(reward);
                        break;
                    }
                }
            } else {
                setReward(new Reward());
            }
        }

        logger.info("[BiodataAction.initSearchReward] start process >>>");
        return getReward();
    }

    public Sertifikat initSearchSertifikat(String kode){
        logger.info("[BiodataAction.initSearchSertifikat] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Sertifikat> listOfResult = (List<Sertifikat>) session.getAttribute("listSertifikat");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Sertifikat sertifikat: listOfResult) {
                    if(kode.equalsIgnoreCase(sertifikat.getSertifikatId())){
                        setSertifikat(sertifikat);
                        break;
                    }
                }
            } else {
                setSertifikat(new Sertifikat());
            }
        }

        logger.info("[BiodataAction.initSearchSertifikat] start process >>>");
        return getSertifikat();
    }

    public Sertifikat initSearchPelatihan(String kode){
        logger.info("[BiodataAction.initSearchPelatihan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Sertifikat> listOfResult = (List<Sertifikat>) session.getAttribute("listSertifikat");
        Sertifikat data= new Sertifikat();
        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Sertifikat sertifikat: listOfResult) {
                    if(kode.equalsIgnoreCase(sertifikat.getSertifikatId())){
                        setSertifikat(sertifikat);
                        data=sertifikat;
                        break;
                    }
                }
            } else {
                setSertifikat(new Sertifikat());
                data=new Sertifikat();
            }
        }

        logger.info("[BiodataAction.initSearchSertifikat] start process >>>");
        return data;
    }

    public PengalamanKerja searchDataEditPengalamanKerja(String id) {
        logger.info("[BiodataAction.searchDataEditPengalamanKerja] start process >>>");

        PengalamanKerja listOfsearch = null;

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listOfsearch = userBo.searchPengalamanKerja(id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "biodataBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchDataEditPengalamanKerja] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.save] Error when searching  by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[BiodataAction.searchDataEditPengalamanKerja] end process >>>");
        return listOfsearch;
    }

    public Reward searchDataEditReward(String id) {
        logger.info("[BiodataAction.searchDataEditReward] start process >>>");

        Reward listOfsearch = null;

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listOfsearch = userBo.searchReward(id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "biodataBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchDataEditPengalamanKerja] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.save] Error when searching  by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[BiodataAction.searchDataEditReward] end process >>>");
        return listOfsearch;
    }

    public Sertifikat searchDataEditSertifikat(String id) {
        logger.info("[BiodataAction.searchDataEditSertifikat] start process >>>");
        Sertifikat listOfsearch = null;

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listOfsearch = userBo.searchSertifikat(id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "biodataBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchDataEditPengalamanKerja] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.save] Error when searching  by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[BiodataAction.searchDataEditSertifikat] end process >>>");
        return listOfsearch;
    }

    public PelatihanJabatanUser searchDataEditPelatihan(String id) {
        logger.info("[BiodataAction.searchDataEditPelatihan] start process >>>");
        PelatihanJabatanUser listOfsearch = null;

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listOfsearch = userBo.searchPelatihan(id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "biodataBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchDataEditPengalamanKerja] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.save] Error when searching  by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[BiodataAction.searchDataEditPelatihan] end process >>>");
        return listOfsearch;
    }

    public void initDeletePengalamanKerja(String kode){
        logger.info("[BiodataAction.initDeletePengalamanKerja] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengalamanKerja> listPengalaman = new ArrayList<>();
        List<PengalamanKerja> listOfResult = (List<PengalamanKerja>) session.getAttribute("listPengalamanKerja");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PengalamanKerja pengalamanKerja: listOfResult) {
                    if(kode.equalsIgnoreCase(pengalamanKerja.getPengalamanId())){
                    }else{
                        listPengalaman.add(pengalamanKerja);
                    }
                }
            }
            logger.info("[BiodataAction.initDeletePengalamanKerja] end process >>>");
        }
        session.removeAttribute("listPengalamanKerja");
        session.setAttribute("listPengalamanKerja", listPengalaman);
    }

    public void initDeleteReward(String kode){
        logger.info("[BiodataAction.initDeleteReward] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Reward> listReward = new ArrayList<>();
        List<Reward> listOfResult = (List<Reward>) session.getAttribute("listReward");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Reward reward: listOfResult) {
                    if(kode.equalsIgnoreCase(reward.getRewardId())){
                    }else{
                        listReward.add(reward);
                    }
                }
            }
            logger.info("[BiodataAction.initDeleteReward] end process >>>");
        }
        session.removeAttribute("listReward");
        session.setAttribute("listReward", listReward);
    }

    public void initDeleteSertifikat(String kode){
        logger.info("[BiodataAction.initDeleteSertifikat] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Sertifikat> listSertifikat = new ArrayList<>();
        List<Sertifikat> listOfResult = (List<Sertifikat>) session.getAttribute("listSertifikat");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Sertifikat sertifikat: listOfResult) {
                    if(kode.equalsIgnoreCase(sertifikat.getSertifikatId())){
                    }else{
                        listSertifikat.add(sertifikat);
                    }
                }
            }
            logger.info("[BiodataAction.initDeleteSertifikat] end process >>>");
        }
        session.removeAttribute("listSertifikat");
        session.setAttribute("listSertifikat", listSertifikat);
    }

    public void saveAddPengalaman(String nip, String branchId, String jabatan, String devisiId, String profesiId,
                                  String tanggalMasuk, String tanggalKeluar, String tipePegawai, String golongan,
                                  String pjs, String aktifFlag, String jenisPegawaiId, String flagDigaji
                                  ){
        logger.info("[StudyAction.saveAdd] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

        try {
            PengalamanKerja pengalamanKerja = new PengalamanKerja();

            ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
            Branch branch = new Branch();
            BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
            branch.setBranchId(branchId);
            branch.setFlag("Y");
            List<Branch> branches = branchBo.getByCriteria(branch);
            String branchName = branches.get(0).getBranchName();
            pengalamanKerja.setBranchName(branchName);

            Position position = new Position();
            PositionBo positionBo = (PositionBo) context.getBean("positionBoProxy");
            position.setPositionId(jabatan);
            position.setFlag("Y");
            List<Position> positions = positionBo.getByCriteria(position);
            String positionName = positions.get(0).getPositionName();
            pengalamanKerja.setJabatanName(positionName);

            pengalamanKerja.setNip(nip);
            pengalamanKerja.setBranchId(branchId);
            pengalamanKerja.setJabatan(jabatan);
            pengalamanKerja.setDivisiId(devisiId);
            pengalamanKerja.setProfesiId(profesiId);

            if(tanggalMasuk != null && !"".equalsIgnoreCase(tanggalMasuk)){
                pengalamanKerja.setTahunMasuk(CommonUtil.convertStringToDate(tanggalMasuk));
                pengalamanKerja.setStTtahunMasuk(tanggalMasuk);
            }else {
                pengalamanKerja.setStTtahunMasuk("");
            }

            if(tanggalKeluar != null && !"".equalsIgnoreCase(tanggalKeluar)){
                pengalamanKerja.setTahunKeluar(CommonUtil.convertStringToDate(tanggalKeluar));
                pengalamanKerja.setStTahunKeluar(tanggalKeluar);
            }else {
                pengalamanKerja.setStTahunKeluar("");
            }

            pengalamanKerja.setTipePegawaiId(tipePegawai);
            pengalamanKerja.setGolonganId(golongan);
            pengalamanKerja.setPjsFlag(pjs);
            pengalamanKerja.setFlagJabatanAktif(aktifFlag);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pengalamanKerja.setCreatedWho(userLogin);
            pengalamanKerja.setLastUpdate(updateTime);
            pengalamanKerja.setCreatedDate(updateTime);
            pengalamanKerja.setLastUpdateWho(userLogin);
            pengalamanKerja.setAction("C");
            pengalamanKerja.setFlag("Y");

            // Sigit 2020-01-07, Penambahan jenis pegawai dan flag gaji
            pengalamanKerja.setJenisPegawaiId(jenisPegawaiId);
            pengalamanKerja.setFlagDigaji(flagDigaji);
            //END

            int id = 0;
            int jumlah = 0;
            boolean status = true;
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<PengalamanKerja> listOfResult = (List<PengalamanKerja>) session.getAttribute("listPengalamanKerja");

            if(listOfResult != null){
                if (listOfResult.size() > 0){

                    // Sigit 2020-01-07, melososkan jabatan aktif > 1
                    for(PengalamanKerja pengalamanKerja1: listOfResult){
                        id = Integer.parseInt(pengalamanKerja1.getPengalamanId());
                    }
                    id++;
                    pengalamanKerja.setPengalamanId(id + "");
                    listOfResult.add(pengalamanKerja);
                    // END

//                    for (PengalamanKerja pengalamanKerja2 : listOfResult){
//                        if (aktifFlag.equalsIgnoreCase(pengalamanKerja2.getFlagJabatanAktif()) && !"Y".equalsIgnoreCase(aktifFlag)){
//                            status = false;
//                            break;
//                        }
//                    }
//
//                    if (status){
//                        for(PengalamanKerja pengalamanKerja1: listOfResult){
//                            id = Integer.parseInt(pengalamanKerja1.getPengalamanId());
//                        }
//                        id++;
//                        pengalamanKerja.setPengalamanId(id + "");
//                        listOfResult.add(pengalamanKerja);
//                    }else {
//                        throw new GeneralBOException("Perhatian!!! Jabatan aktif sudah ada");
//                    }
                }else {
                    listOfResult = new ArrayList<>();
                    pengalamanKerja.setPengalamanId(id + "");
                    listOfResult.add(pengalamanKerja);
                }
            }else{
                listOfResult = new ArrayList<>();
                pengalamanKerja.setPengalamanId(id + "");
                listOfResult.add(pengalamanKerja);
            }
            session.removeAttribute("listPengalamanKerja");
            session.setAttribute("listPengalamanKerja", listOfResult);

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataBO.saveAddPengalaman");
            } catch (GeneralBOException e1) {
                logger.error("[pengalamanKerjaAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[pengalamanKerjaAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    private String cekStatus(List<PengalamanKerja> listOfResult, Date tanggalMasuk, Date tanggalKeluar){
        String status = "true";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (PengalamanKerja pengalamanKerja : listOfResult){
            if ("Y".equalsIgnoreCase(pengalamanKerja.getFlagJabatanAktif())){
                Date listDate = pengalamanKerja.getTahunMasuk();
                if (listDate.compareTo(tanggalMasuk) > 0){
                    status = "true";
                }else if (listDate.compareTo(tanggalMasuk) < 0){
                    status = "false";
                }else if (listDate.compareTo(tanggalMasuk) == 0){
                    status = "false";
                }

                if (listDate.compareTo(tanggalKeluar) > 0){
                    status = "true";
                }else if (listDate.compareTo(tanggalKeluar) < 0){
                    status = "false";
                }else if (listDate.compareTo(tanggalKeluar) == 0){
                    status = "true";
                }
            }
        }

        return status;
    }

    public void saveAddReward(String nip, String tanggal, String jenis, String keterangan){
        logger.info("[BiodataAction.saveAddReward] start process >>>");

        try {
            Reward reward = new Reward();
            reward.setNip(nip);
            reward.setStTanggal(tanggal);
            reward.setJenis(jenis);
            reward.setKeterangan(keterangan);

            if(tanggal != null && !"".equalsIgnoreCase(tanggal)){
                reward.setTanggal(CommonUtil.convertStringToDate(tanggal));
            }

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            reward.setCreatedWho(userLogin);
            reward.setLastUpdate(updateTime);
            reward.setCreatedDate(updateTime);
            reward.setLastUpdateWho(userLogin);
            reward.setAction("C");
            reward.setFlag("Y");

            int id = 0;
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<Reward> listOfResult = (List<Reward>) session.getAttribute("listReward");
            if(listOfResult != null){
                for(Reward reward1: listOfResult){
                    id = Integer.parseInt(reward1.getRewardId());
                }
                id++;
                reward.setRewardId(id + "");
                listOfResult.add(reward);
            }else{
                listOfResult = new ArrayList<>();
                reward.setRewardId(id + "");
                listOfResult.add(reward);
            }
            session.removeAttribute("listReward");
            session.setAttribute("listReward", listOfResult);

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataBO.saveAddReward");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.saveAddReward] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    public void saveAddSertifikat(String nip, String namaPelatihan, String judulPelatihan, String penyelenggara, String jumlahJamPelatihan,
                                  String sertifikatPelatihan,String tanggalPelatihan,String masaBerlakuSertifikat,String gambar){
        logger.info("[BiodataAction.saveAddSertifikat] start process >>>");

        try {
            Sertifikat sertifikat = new Sertifikat();
            sertifikat.setNip(nip);
            sertifikat.setJenis(namaPelatihan);
            sertifikat.setNama(judulPelatihan);
            sertifikat.setLembaga(penyelenggara);
            sertifikat.setJumlahHari(Integer.parseInt(jumlahJamPelatihan));
            sertifikat.setLulus(sertifikatPelatihan);
            sertifikat.setTanggalPengesahan(CommonUtil.convertStringToDate(tanggalPelatihan));
            sertifikat.setStTanggalPengesahan(tanggalPelatihan);
            sertifikat.setPrestasiGrade(masaBerlakuSertifikat);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            try {
                String fileName1 = "";
                if (judulPelatihan.length()>20){
                    fileName1=judulPelatihan.replace(" ","").substring(0,19);
                }else{
                    fileName1=judulPelatihan.replace(" ","");
                }

                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(gambar);
                logger.info("Decoded upload data : " + decodedBytes.length);
                String fileName = fileName1+"-"+dateFormater("dd")+dateFormater("MM")+dateFormater("yy")+".png";
                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_SERTIFIKAT+fileName;
                logger.info("File save path : " + uploadFile);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                if (image == null) {
                    logger.error("Buffered Image is null");
                }else{
                    File f = new File(uploadFile);
                    // write the image
                    ImageIO.write(image, "png", f);
                    sertifikat.setTempatPelaksana(fileName);
                }
            }catch (Exception e){
                logger.error(e);
            }

            sertifikat.setCreatedWho(userLogin);
            sertifikat.setLastUpdate(updateTime);
            sertifikat.setCreatedDate(updateTime);
            sertifikat.setLastUpdateWho(userLogin);
            sertifikat.setAction("C");
            sertifikat.setFlag("Y");

            int id = 0;
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<Sertifikat> listOfResult = (List<Sertifikat>) session.getAttribute("listSertifikat");
            if(listOfResult != null){
                for(Sertifikat sertifikat1: listOfResult){
                    id = Integer.parseInt(sertifikat1.getSertifikatId());
                }
                id++;
                sertifikat.setSertifikatId(id + "");
                listOfResult.add(sertifikat);
            }else{
                listOfResult = new ArrayList<>();
                sertifikat.setSertifikatId(id + "");
                listOfResult.add(sertifikat);
            }
            session.removeAttribute("listSertifikat");
            session.setAttribute("listSertifikat", listOfResult);

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataBO.saveAddReward");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.saveAddSertifikat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    public String initEditPengalaman(String id, String nip, String branchId, String jabatan, String devisiId, String profesiId,
                                     String tanggalMasuk, String tanggalKeluar, String tipePegawai, String golongan, String pjs, String aktifFlag){
        logger.info("[BiodataAction.saveEdit] start process >>>");
        PengalamanKerja pengalamanKerja = new PengalamanKerja();

        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        Branch branch = new Branch();
        BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
        branch.setBranchId(branchId);
        branch.setFlag("Y");
        List<Branch> branches = branchBo.getByCriteria(branch);
        String branchName = branches.get(0).getBranchName();
        pengalamanKerja.setBranchName(branchName);

        Position position = new Position();
        PositionBo positionBo = (PositionBo) context.getBean("positionBoProxy");
        position.setPositionId(jabatan);
        position.setFlag("Y");
        List<Position> positions = positionBo.getByCriteria(position);
        String positionName = positions.get(0).getPositionName();
        pengalamanKerja.setJabatanName(positionName);

        pengalamanKerja.setPengalamanId(id);
        pengalamanKerja.setNip(nip);
        pengalamanKerja.setBranchId(branchId);
//        pengalamanKerja.setNamaPerusahaan(namaPerusahaan);
        pengalamanKerja.setJabatan(jabatan);

        pengalamanKerja.setDivisiId(devisiId);
        pengalamanKerja.setProfesiId(profesiId);

//        pengalamanKerja.setStTtahunMasuk(tanggalMasuk);
//        pengalamanKerja.setStTahunKeluar(tanggalKeluar);
        if(tanggalMasuk != null && !"".equalsIgnoreCase(tanggalMasuk)){
            pengalamanKerja.setTahunMasuk(CommonUtil.convertStringToDate(tanggalMasuk));
            pengalamanKerja.setStTtahunMasuk(tanggalMasuk);
        }else {
            pengalamanKerja.setStTtahunMasuk("");
        }

        if(tanggalKeluar != null && !"".equalsIgnoreCase(tanggalKeluar)){
            pengalamanKerja.setTahunKeluar(CommonUtil.convertStringToDate(tanggalKeluar));
            pengalamanKerja.setStTahunKeluar(tanggalKeluar);
        }else {
            pengalamanKerja.setStTahunKeluar("");
        }

        pengalamanKerja.setTipePegawaiId(tipePegawai);
        pengalamanKerja.setGolonganId(golongan);
        pengalamanKerja.setPjsFlag(pjs);
        pengalamanKerja.setFlagJabatanAktif(aktifFlag);

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        pengalamanKerja.setCreatedWho(userLogin);
        pengalamanKerja.setLastUpdate(updateTime);
        pengalamanKerja.setCreatedDate(updateTime);
        pengalamanKerja.setLastUpdateWho(userLogin);
        pengalamanKerja.setAction("C");
        pengalamanKerja.setFlag("Y");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengalamanKerja> listPengalamanKerja= new ArrayList<>();
        List<PengalamanKerja> listOfResult = (List<PengalamanKerja>) session.getAttribute("listPengalamanKerja");

        if (id != null && !"".equalsIgnoreCase(id)) {
            if (listOfResult != null) {
                for (PengalamanKerja pengalamanKerja1 : listOfResult) {
                    if (id.equalsIgnoreCase(pengalamanKerja1.getPengalamanId())) {
                        listPengalamanKerja.add(pengalamanKerja);
                    } else {
                        listPengalamanKerja.add(pengalamanKerja1);
                    }
                }
            }
            logger.info("[PengalamanKerjaAction.init] end process >>>");
        }
        session.removeAttribute("listPengalamanKerja");
        session.setAttribute("listPengalamanKerja", listPengalamanKerja);
        return "";
    }

    public void initEditReward(String id, String nip, String tanggal, String jenis, String keterangan){
        logger.info("[BiodataAction.saveEditReward] start process >>>");
        Reward reward = new Reward();

        reward.setRewardId(id);
        reward.setNip(nip);
        reward.setStTanggal(tanggal);
        reward.setJenis(jenis);
        reward.setKeterangan(keterangan);

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Reward> listReward= new ArrayList<>();
        List<Reward> listOfResult = (List<Reward>) session.getAttribute("listReward");

        if (id != null && !"".equalsIgnoreCase(id)) {
            if (listOfResult != null) {
                for (Reward reward1 : listOfResult) {
                    if (id.equalsIgnoreCase(reward1.getRewardId())) {
                        listReward.add(reward);
                    } else {
                        listReward.add(reward);
                    }
                }
            }
            logger.info("[BiodataAction.initReward] end process >>>");
        }
        session.removeAttribute("listReward");
        session.setAttribute("listReward", listReward);
    }

    public void initEditSertifikat(String id, String nip, String namaPelatihan, String judulPelatihan, String penyelenggara, String jumlahJamPelatihan,
                                   String sertifikatPelatihan,String tanggalPelatihan,String masaBerlakuSertifikat,String gambar){
        logger.info("[BiodataAction.saveEditSertifikat] start process >>>");
        Sertifikat sertifikat = new Sertifikat();
        sertifikat.setNip(nip);
        sertifikat.setJenis(namaPelatihan);
        sertifikat.setNama(judulPelatihan);
        sertifikat.setLembaga(penyelenggara);
        sertifikat.setJumlahHari(Integer.parseInt(jumlahJamPelatihan));
        sertifikat.setLulus(sertifikatPelatihan);
        sertifikat.setTanggalPengesahan(CommonUtil.convertStringToDate(tanggalPelatihan));
        sertifikat.setStTanggalPengesahan(tanggalPelatihan);
        sertifikat.setPrestasiGrade(masaBerlakuSertifikat);

        try {
            String fileName1 = "";
            if (judulPelatihan.length()>20){
                fileName1=judulPelatihan.replace(" ","").substring(0,19);
            }else{
                fileName1=judulPelatihan.replace(" ","");
            }

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes = decoder.decodeBuffer(gambar);
            logger.info("Decoded upload data : " + decodedBytes.length);
            String fileName = fileName1+"-"+dateFormater("dd")+dateFormater("MM")+dateFormater("yy")+".png";
            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_SERTIFIKAT+fileName;
            logger.info("File save path : " + uploadFile);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

            if (image == null) {
                logger.error("Buffered Image is null");
            }else{
                File f = new File(uploadFile);
                // write the image
                ImageIO.write(image, "png", f);
                sertifikat.setTempatPelaksana(fileName);
            }
        }catch (Exception e){
            logger.error(e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Sertifikat> listSertifikat= new ArrayList<>();
        List<Sertifikat> listOfResult = (List<Sertifikat>) session.getAttribute("listSertifikat");

        if (id != null && !"".equalsIgnoreCase(id)) {
            if (listOfResult != null) {
                for (Sertifikat sertifikat1 : listOfResult) {
                    if (id.equalsIgnoreCase(sertifikat1.getSertifikatId())) {
                        listSertifikat.add(sertifikat);
                    } else {
                        listSertifikat.add(sertifikat);
                    }
                }
            }
            logger.info("[BiodataAction.initSertifikat] end process >>>");
        }
        session.removeAttribute("listSertifikat");
        session.setAttribute("listSertifikat", listSertifikat);
    }

    public String saveAddDataPengalamaKerja(String nip, String branchId, String divisiId, String positionId, String tanggal, String tanggalKeluar,
                                            String tipePegawaiId, String golonganId,String pjsFlag, String perusahaanLain, String bidangLain, String jabatanLain, String flagAktif, String profesiId){
        logger.info("[BiodataAction.saveAdd] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String hasilProfesi="";
        ProfesiBo profesiBo = (ProfesiBo) ctx.getBean("profesiBoProxy");
        Profesi searchProfesi = new Profesi();
        searchProfesi.setProfesiId(profesiId);
        searchProfesi.setFlag("Y");
        List<Profesi> profesis = profesiBo.getByCriteria(searchProfesi);
        for (Profesi profesi : profesis){
            hasilProfesi = profesi.getProfesiName();
        }


        try {
            HistoryJabatanPegawai historyJabatanPegawai = new HistoryJabatanPegawai();
            historyJabatanPegawai.setNip(nip);
            historyJabatanPegawai.setBranchId(branchId);
            if (branchId.equalsIgnoreCase("0")){
                if (perusahaanLain!=null){
                    historyJabatanPegawai.setBranchName(perusahaanLain);
                }
            }

            historyJabatanPegawai.setDivisiId(divisiId);
            if (divisiId.equalsIgnoreCase("0")){
                if (bidangLain!=null){
                    historyJabatanPegawai.setDivisiName(bidangLain);
                }
            }

            historyJabatanPegawai.setPositionId(positionId);
            if (positionId.equalsIgnoreCase("0")){
                if (jabatanLain!=null){
                    historyJabatanPegawai.setPositionName(jabatanLain);
                }
            }

            historyJabatanPegawai.setProfesiId(profesiId);
            historyJabatanPegawai.setTanggal(tanggal);
            historyJabatanPegawai.setTanggalKeluar(tanggalKeluar);
            historyJabatanPegawai.setTipePegawaiId(tipePegawaiId);
            historyJabatanPegawai.setGolonganId(golonganId);
            historyJabatanPegawai.setPjsFlag(pjsFlag);
            historyJabatanPegawai.setFlagJabatanAktif(flagAktif);


            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            historyJabatanPegawai.setCreatedWho(userLogin);
            historyJabatanPegawai.setLastUpdate(updateTime);
            historyJabatanPegawai.setCreatedDate(updateTime);
            historyJabatanPegawai.setLastUpdateWho(userLogin);
            historyJabatanPegawai.setAction("C");
            historyJabatanPegawai.setFlag("Y");

            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.addPengalamanKerja(historyJabatanPegawai);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "pengalamanKerjaBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[pengalamanKerjaAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[pengalamanKerjaAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }
        return "";
    }

    public void saveAddDataReward(String nip, String tanggal, String jenis, String keterangan){
        logger.info("[BiodataAction.saveAddReward] start process >>>");

        try {
            Reward reward = new Reward();
            reward.setNip(nip);
            reward.setStTanggal(tanggal);
            reward.setJenis(jenis);
            reward.setKeterangan(keterangan);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            reward.setCreatedWho(userLogin);
            reward.setLastUpdate(updateTime);
            reward.setCreatedDate(updateTime);
            reward.setLastUpdateWho(userLogin);
            reward.setAction("C");
            reward.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.addReward(reward);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "pengalamanKerjaBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[biodataAction.saveAddReward] Error when saving error,", e1);
            }
            logger.error("[biodataAction.saveAddReward] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    public void saveAddDataSertifikat(String nip, String jenis, String tanggalPengesahan, String masaBerlaku, String masaBerakhir, String nama, String lembaga, String tempatPelaksana,
                                      String nilai, String lulus, String prestasi, String jumlahHari){
        logger.info("[BiodataAction.saveAddSertifikat] start process >>>");

        try {
            Sertifikat sertifikat = new Sertifikat();
            sertifikat.setNip(nip);
            sertifikat.setJenis(jenis);
            sertifikat.setStTanggalPengesahan(tanggalPengesahan);
            sertifikat.setStMasaBerlaku(masaBerlaku);
            sertifikat.setStMasaBerakhir(masaBerakhir);
            sertifikat.setNama(nama);
            sertifikat.setLembaga(lembaga);
            sertifikat.setTempatPelaksana(tempatPelaksana);
            if(jumlahHari == null && jumlahHari.equalsIgnoreCase("")){
                sertifikat.setJumlahHari(0);
            }else{
                sertifikat.setJumlahHari(Integer.parseInt(jumlahHari));
            }
            if(nilai != null && !nilai.equalsIgnoreCase("")){
                sertifikat.setNilai(Double.parseDouble(nilai));
            }else{
                sertifikat.setNilai(0);
            }
            sertifikat.setLulus(lulus);
            sertifikat.setPrestasiGrade(prestasi);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sertifikat.setCreatedWho(userLogin);
            sertifikat.setLastUpdate(updateTime);
            sertifikat.setCreatedDate(updateTime);
            sertifikat.setLastUpdateWho(userLogin);
            sertifikat.setAction("C");
            sertifikat.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.addSertifikat(sertifikat);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "pengalamanKerjaBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[biodataAction.saveAddReward] Error when saving error,", e1);
            }
            logger.error("[biodataAction.saveAddReward] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    public void saveAddDataPelatihan(String nip, String namaPelatihan, String judulPelatihan, String penyelenggara, String jumlahJamPelatihan,
                                     String sertifikatPelatihan,String tanggalPelatihan,String masaBerlakuSertifikat,String gambar){
        logger.info("[BiodataAction.saveAddDataPelatihan] start process >>>");

        try {
            Sertifikat sertifikat = new Sertifikat();
            sertifikat.setNip(nip);
            sertifikat.setJenis(namaPelatihan);
            sertifikat.setNama(judulPelatihan);
            sertifikat.setLembaga(penyelenggara);
            sertifikat.setJumlahHari(Integer.parseInt(jumlahJamPelatihan));
            sertifikat.setLulus(sertifikatPelatihan);
            sertifikat.setTanggalPengesahan(CommonUtil.convertStringToDate(tanggalPelatihan));
            sertifikat.setStTanggalPengesahan(tanggalPelatihan);
            sertifikat.setPrestasiGrade(masaBerlakuSertifikat);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            try {
                String fileName1 = "";
                if (judulPelatihan.length()>20){
                    fileName1=judulPelatihan.replace(" ","").substring(0,19);
                }else{
                    fileName1=judulPelatihan.replace(" ","");
                }

                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(gambar);
                logger.info("Decoded upload data : " + decodedBytes.length);
                String fileName = fileName1+"-"+dateFormater("dd")+dateFormater("MM")+dateFormater("yy")+".png";
                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_SERTIFIKAT+fileName;
                logger.info("File save path : " + uploadFile);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                if (image == null) {
                    logger.error("Buffered Image is null");
                }else{
                    File f = new File(uploadFile);
                    // write the image
                    ImageIO.write(image, "png", f);
                    sertifikat.setTempatPelaksana(fileName);
                }
            }catch (Exception e){
                logger.error(e);
            }

            sertifikat.setCreatedWho(userLogin);
            sertifikat.setLastUpdate(updateTime);
            sertifikat.setCreatedDate(updateTime);
            sertifikat.setLastUpdateWho(userLogin);
            sertifikat.setAction("C");
            sertifikat.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.addSertifikat(sertifikat);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "biodataBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[biodataAction.saveAddDataPelatihan] Error when saving error,", e1);
            }
            logger.error("[biodataAction.saveAddDataPelatihan] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    public void saveEditPelatihan(String id, String nip, String namaPelatihan, String judulPelatihan, String penyelenggara, String jumlahJamPelatihan,
                                  String sertifikatPelatihan,String tanggalPelatihan,String masaBerlakuSertifikat,String gambar){
        logger.info("[BiodataAction.saveAddDataPelatihan] start process >>>");

        try {
            Sertifikat sertifikat = new Sertifikat();
            sertifikat.setSertifikatId(id);
            sertifikat.setNip(nip);
            sertifikat.setJenis(namaPelatihan);
            sertifikat.setNama(judulPelatihan);
            sertifikat.setLembaga(penyelenggara);
            sertifikat.setJumlahHari(Integer.parseInt(jumlahJamPelatihan));
            sertifikat.setLulus(sertifikatPelatihan);
            sertifikat.setTanggalPengesahan(CommonUtil.convertStringToDate(tanggalPelatihan));
            sertifikat.setStTanggalPengesahan(tanggalPelatihan);
            sertifikat.setPrestasiGrade(masaBerlakuSertifikat);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            try {
                String fileName1 = "";
                if (judulPelatihan.length()>20){
                    fileName1=judulPelatihan.replace(" ","").substring(0,19);
                }else{
                    fileName1=judulPelatihan.replace(" ","");
                }

                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(gambar);
                logger.info("Decoded upload data : " + decodedBytes.length);
                String fileName = fileName1+"-"+dateFormater("dd")+dateFormater("MM")+dateFormater("yy")+".png";
                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_SERTIFIKAT+fileName;
                logger.info("File save path : " + uploadFile);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                if (image == null) {
                    logger.error("Buffered Image is null");
                }else{
                    File f = new File(uploadFile);
                    // write the image
                    ImageIO.write(image, "png", f);
                    sertifikat.setTempatPelaksana(fileName);
                }
            }catch (Exception e){
                logger.error(e);
            }

            sertifikat.setCreatedWho(userLogin);
            sertifikat.setLastUpdate(updateTime);
            sertifikat.setCreatedDate(updateTime);
            sertifikat.setLastUpdateWho(userLogin);
            sertifikat.setAction("C");
            sertifikat.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.saveEditSertifikat(sertifikat);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "biodataBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[biodataAction.saveAddDataPelatihan] Error when saving error,", e1);
            }
            logger.error("[biodataAction.saveAddDataPelatihan] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    public String saveEditPengalamanKerja(String id,String nip, String branchId, String divisiId, String positionId, String tanggal, String tanggalKeluar,
                                          String tipePegawaiId, String golonganId, String perusahaanLain, String bidangLain, String jabatanLain, String flagAktif, String profesiId, String pjsFlag){
        logger.info("[PengalamanKerjaAction.saveEdit] start process >>>");
        try {

            PengalamanKerja pengalamanKerja = new PengalamanKerja();
            HistoryJabatanPegawai historyJabatanPegawai = new HistoryJabatanPegawai();

            historyJabatanPegawai.setHistoryJabatanId(id);
            historyJabatanPegawai.setNip(nip);
            historyJabatanPegawai.setBranchId(branchId);
            if (branchId.equalsIgnoreCase("0")){
                if (perusahaanLain!=null){
                    historyJabatanPegawai.setBranchName(perusahaanLain);
                }
            }

            historyJabatanPegawai.setDivisiId(divisiId);
            if (divisiId.equalsIgnoreCase("0")){
                if (bidangLain!=null){
                    historyJabatanPegawai.setDivisiName(bidangLain);
                }
            }

            historyJabatanPegawai.setPositionId(positionId);
            if (positionId.equalsIgnoreCase("0")){
                if (jabatanLain!=null){
                    historyJabatanPegawai.setPositionName(jabatanLain);
                }
            }
            historyJabatanPegawai.setTanggal(tanggal);
            historyJabatanPegawai.setTanggalKeluar(tanggalKeluar);
            historyJabatanPegawai.setTipePegawaiId(tipePegawaiId);
            historyJabatanPegawai.setGolonganId(golonganId);
            historyJabatanPegawai.setFlagJabatanAktif(flagAktif);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            historyJabatanPegawai.setDivisiId(divisiId);
            historyJabatanPegawai.setPositionId(positionId);
            historyJabatanPegawai.setProfesiId(profesiId);
            historyJabatanPegawai.setTanggal(tanggal);
            historyJabatanPegawai.setTanggalKeluar(tanggalKeluar);
            historyJabatanPegawai.setTipePegawaiId(tipePegawaiId);
            historyJabatanPegawai.setGolonganId(golonganId);
            historyJabatanPegawai.setPjsFlag(pjsFlag);
            historyJabatanPegawai.setFlagJabatanAktif(flagAktif);

            historyJabatanPegawai.setCreatedWho(userLogin);
            historyJabatanPegawai.setLastUpdate(updateTime);
            historyJabatanPegawai.setCreatedDate(updateTime);
            historyJabatanPegawai.setLastUpdateWho(userLogin);
            historyJabatanPegawai.setAction("C");
            historyJabatanPegawai.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.saveEditPengalamanKerja(historyJabatanPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[StudyAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveEditReward(String id, String nip, String tanggal, String jenis, String keterangan){
        logger.info("[BiodataAction.saveEditReward] start process >>>");
        try {
            Reward reward = new Reward();
            reward.setRewardId(id);
            reward.setNip(nip);
            reward.setJenis(jenis);
            reward.setKeterangan(keterangan);
            reward.setStTanggal(tanggal);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            reward.setLastUpdateWho(userLogin);
            reward.setLastUpdate(updateTime);
            reward.setAction("U");
            reward.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.saveEditReward(reward);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveEditSertifikat(String id, String nip, String jenis, String tanggalPengesahan, String masaBerlaku, String masaBerakhir, String nama, String lembaga,
                                     String tempatPelaksana, String nilai, String lulus, String prestasi, String jumlahHari){
        logger.info("[BiodataAction.saveEditReward] start process >>>");
        try {
            Sertifikat sertifikat = new Sertifikat();
            sertifikat.setSertifikatId(id);
            sertifikat.setNip(nip);
            sertifikat.setJenis(jenis);
            sertifikat.setStTanggalPengesahan(tanggalPengesahan);
            sertifikat.setStMasaBerlaku(masaBerlaku);
            sertifikat.setStMasaBerakhir(masaBerakhir);
            sertifikat.setNama(nama);
            sertifikat.setLembaga(lembaga);
            sertifikat.setTempatPelaksana(tempatPelaksana);
            if(nilai != null && !nilai.equalsIgnoreCase("")){
                sertifikat.setNilai(Double.parseDouble(nilai));
            }else{
                sertifikat.setNilai(0);
            }
            if(jumlahHari == null || jumlahHari.equalsIgnoreCase("")){
                sertifikat.setJumlahHari(0);
            }else{
                sertifikat.setJumlahHari(Integer.parseInt(jumlahHari));
            }
            sertifikat.setLulus(lulus);
            sertifikat.setPrestasiGrade(prestasi);

            if(tanggalPengesahan != null && !"".equalsIgnoreCase(tanggalPengesahan)){
                sertifikat.setTanggalPengesahan(CommonUtil.convertStringToDate(tanggalPengesahan));
            }

            if(masaBerlaku != null && !"".equalsIgnoreCase(masaBerlaku)){
                sertifikat.setMasaBerlaku(CommonUtil.convertStringToDate(masaBerlaku));
            }

            if(masaBerakhir!= null && !"".equalsIgnoreCase(masaBerakhir)){
                sertifikat.setMasaBerakhir(CommonUtil.convertStringToDate(masaBerakhir));
            }

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sertifikat.setLastUpdateWho(userLogin);
            sertifikat.setLastUpdate(updateTime);
            sertifikat.setAction("U");
            sertifikat.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.saveEditSertifikat(sertifikat);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDeletePengalamanKerja(String id){
        logger.info("[BiodataAction.saveDeletePengalamanKerja] start process >>>");
        try {

            PengalamanKerja deletePengalaman = new PengalamanKerja();

            deletePengalaman.setPengalamanId(id);
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePengalaman.setLastUpdate(updateTime);
            deletePengalaman.setLastUpdateWho(userLogin);
            deletePengalaman.setAction("D");
            deletePengalaman.setFlag("N");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

            biodataBo.saveDeletePengalamanKerja(deletePengalaman);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "BiodataBo.saveDeletePengalamanKerja");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[BiodataAction.saveDeletePengalamanKerja] end process <<<");

//        return "success_save_delete";
        return "";
    }

    public String saveDeleteReward(String id){
        logger.info("[BiodataAction.saveDeleteReward] start process >>>");
        try {

            Reward deleteReward = new Reward();

            deleteReward.setRewardId(id);
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteReward.setLastUpdate(updateTime);
            deleteReward.setLastUpdateWho(userLogin);
            deleteReward.setAction("D");
            deleteReward.setFlag("N");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

            biodataBo.saveDeleteReward(deleteReward);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataBo.saveDeletePengalamanKerja");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveDeleteReward] end process <<<");

        return "success_save_delete";
    }

    public String saveDeleteSertifikat(String id){
        logger.info("[BiodataAction.saveDeleteSertifikat] start process >>>");
        try {

            Sertifikat deleteSertifikat = new Sertifikat();

            deleteSertifikat.setSertifikatId(id);
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSertifikat.setLastUpdate(updateTime);
            deleteSertifikat.setLastUpdateWho(userLogin);
            deleteSertifikat.setAction("D");
            deleteSertifikat.setFlag("N");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

            biodataBo.saveDeleteSertifikat(deleteSertifikat);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataBo.saveDeleteSertifikat");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveDeleteSertifikat] end process <<<");

        return "success_save_delete";
    }

    public String saveDeletePelatihanJabatan(String id){
        logger.info("[BiodataAction.saveDeletePelatihanJabatan] start process >>>");
        try {

            PelatihanJabatanUser pelatihanJabatanUser = new PelatihanJabatanUser();

            pelatihanJabatanUser.setPelatihanUserId(id);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pelatihanJabatanUser.setLastUpdate(updateTime);
            pelatihanJabatanUser.setLastUpdateWho(userLogin);
            pelatihanJabatanUser.setAction("D");
            pelatihanJabatanUser.setFlag("N");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

            biodataBo.saveDeletePelatihan(pelatihanJabatanUser);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataBo.saveDeleteSertifikat");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveDeletePelatihanJabatan] end process <<<");

        return "success_save_delete";
    }

    public String initFormKaryawanDanBatih() {
        logger.info("[BiodataAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultBiodata");
        logger.info("[BiodataAction.initForm] end process >>>");
        return "bidataDanBatih";
    }

    public String searchKaryawanDanBatih() {
        logger.info("[BiodataAction.searchKaryawanDanBatih] start process >>>");

        Biodata searchPensiun = getBiodata();
        List<ImBiodataEntity> biodataList = new ArrayList();

        try {
            biodataList = biodataBoProxy.searchKaryawanDanBatihSys(searchPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "biodataBO.searchKaryawanDanBatihSys");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.searchKaryawanDanBatih] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.searchKaryawanDanBatih] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultBiodataBatih");
        session.setAttribute("listOfResultBiodataBatih", biodataList);

        return "bidataDanBatih";
    }

    public String printReportBiodata(){
        logger.info("[BiodataAction.printReportBiodata] start process >>>");

        if (getId() != null) {
            List<Keluarga> listKeluarga = new ArrayList<>();
            List<HistoryJabatanPegawai> listRiwayatPekerjaan = new ArrayList<>();
            List<Study> listPendidikan = new ArrayList<>();

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listKeluarga = biodataBo.listKeluarga(getId());
            listRiwayatPekerjaan = biodataBo.listRiwayatPekerjaan(getId());
            listPendidikan = biodataBo.listStudy(getId());
            Biodata search = new Biodata();
            search.setNip(getId());
            search.setFlag("Y");
            List<Biodata> biodataList = biodataBo.getByCriteria(search);
            String branchId ="";
            for (Biodata biodata:biodataList){
                branchId=biodata.getBranch();
            }
            Branch branch = branchBo.getBranchById(branchId,"Y");
            JRBeanCollectionDataSource itemKeluarga = new JRBeanCollectionDataSource(listKeluarga);
            JRBeanCollectionDataSource itemRiwayatPekerjaan = new JRBeanCollectionDataSource(listRiwayatPekerjaan);
            JRBeanCollectionDataSource itemPendidikan = new JRBeanCollectionDataSource(listPendidikan);

            String foto = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() +
                    CommonConstant.RESOURCE_PATH_USER_UPLOAD + getId() + ".jpg";


            if (!new File(foto).exists()){
                foto = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() +
                        CommonConstant.RESOURCE_PATH_USER_UPLOAD + "img_avatar.png";
            }

            reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
            reportParams.put("urlFoto", foto);
            reportParams.put("nip", getId());
            reportParams.put("kota",branch.getBranchName());
            reportParams.put("areaId", CommonUtil.userAreaName());
            reportParams.put("titleReport", "CURICULUM VITAE");
            reportParams.put("itemKeluarga", itemKeluarga);
            reportParams.put("itemRiwayatPekerjaan", itemRiwayatPekerjaan);
            reportParams.put("itemPendidikan", itemPendidikan);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportBiodata");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.printReportBiodata] Error when downloading ,", e1);
                }
                logger.error("[BiodataAction.printReportBiodata] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[BiodataAction.printReportBiodata] Error when print report, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, please inform to your admin.");
        }

        logger.info("[BiodataAction.printReportBiodata] end process <<<");

        return "print_report";
    }

    //digunakan untuk SMK
    public List initComboPersonil(String query, String periode) {
        logger.info("[BiodataAction.initComboPersonil] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

        try {
            listOfUser = biodataBo.getListOfPersonilForSmk(query, periode);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "BiodataBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.initComboPersonil] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.initComboPersonil] Error when ," + "[" + logId + "] Found problem when retrieving combo data, please inform to your admin.", e);
        }

        logger.info("[BiodataAction.initComboPersonil] end process <<<");

        return listOfUser;
    }

    public List initComboAllPersonil(String query, String branchId) {
        logger.info("[BiodataAction.initComboAllPersonil] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
//        HttpSession session = WebContextFactory.get().getSession();


        try {
            listOfUser = biodataBo.getAllListOfPersonil(query,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.initComboAllPersonil] Error when saving error,", e1);
            }
            logger.error("[BiodataAction.initComboAllPersonil] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[BiodataAction.initComboLokasiKebun] end process <<<");

        return listOfUser;
    }

    public String loadImageSertifikat(String sertifikat){
        return CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_SERTIFIKAT+sertifikat;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    // Sigit 2020-01-07, untuk check jika sudah ada jabatan utama pada session listPengalamanKerja / jabatan
    // maka tidak bisa lanjut
    public CrudResponse checkAvailJenisPegawaiDefault(){
        logger.info("[BiodataAction.checkAvailJenisPegawaiDefault] START >>>");
        CrudResponse response = new CrudResponse();

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengalamanKerja> listOfResult = (List<PengalamanKerja>) session.getAttribute("listPengalamanKerja");

        // collecting data jenis pegawai yg disimpan pada session, disimpan pada stringListJenisPegawaiId
        List<String> stringListJenisPegawaiId = new ArrayList<>();
        if (listOfResult != null && listOfResult.size() > 0){
            for (PengalamanKerja pengalamanKerja : listOfResult){
                stringListJenisPegawaiId.add(pengalamanKerja.getJenisPegawaiId());
            }
        }

        if (stringListJenisPegawaiId.size() > 0){
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

            // cari berdasarkan list string jenis pegawai pada session;
            try {
                Boolean foundData = biodataBo.checkAvailJenisPegawaiDefault(stringListJenisPegawaiId);
                if (foundData){
                    response.setStatus("error");
                    response.setMsg("Telah Ada Jabatan Utama. piih jenis pegawai lain !");
                } else
                    response.setStatus("success");
            } catch (GeneralBOException e){
                logger.error("[BiodataAction.checkAvailJenisPegawaiDefault] ERROR, ", e);
            }
        } else
            response.setStatus("success");

        logger.info("[BiodataAction.checkAvailJenisPegawaiDefault] END <<<");
        return response;
    }

    // Sigit 2020-01-07
    public String initComboJenisPegawai(){
        logger.info("[BiodataAction.initComboJenisPegawai] START >>>");
        List<JenisPegawai> jenisPegawais = new ArrayList<>();

        try {
            jenisPegawais = biodataBoProxy.getAllJenisPegawai();
        } catch (GeneralBOException e){
            logger.error("[BiodataAction.initComboJenisPegawai] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, Found problem when searching data by criteria, please inform to your admin" );
        }

        listOfComboJenisPegawai.addAll(jenisPegawais);
        logger.info("[BiodataAction.initComboJenisPegawai] END <<<");
        return SUCCESS;
    }

    public List<PersonilPosition> listPersonilPosition(String nip){
        logger.info("[BiodataAction.listPersonilPosition] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PersonilPosition> listOfResultPersonil = (List<PersonilPosition>) session.getAttribute("listOfPersonilPosition");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

        if (listOfResultPersonil == null)
        {
            try {
                listOfResultPersonil = biodataBo.getListPesonilPosition(nip);
            } catch (GeneralBOException e){
                logger.error("[BiodataAction.listPersonilPosition] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, Found problem when searching data by criteria, please inform to your admin" );
            }
        }

        session.removeAttribute("listOfPersonilPosition");
        session.setAttribute("listOfPersonilPosition", listOfResultPersonil);

        logger.info("[BiodataAction.listPersonilPosition] END <<<");
        return listOfResultPersonil;
    }

    public CrudResponse saveAddToPersonilPositionSession(String stJson) throws JSONException{
        logger.info("[BiodataAction.saveAddToPersonilPositionSession] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PersonilPosition> listOfResultPersonil = (List<PersonilPosition>) session.getAttribute("listOfPersonilPosition");

        CrudResponse response = new CrudResponse();
        if (stJson == null || "".equalsIgnoreCase(stJson))
        {
            response.setStatus("error");
            response.setMsg("Tidak dapat mendapatkan JSON");
            return response;
        }

        // kasus - kasus validasi
        response = validationPersonilPosition(stJson);
        if ("error".equalsIgnoreCase(response.getStatus())){
            return response;
        }
        // END

        JSONObject jsonObject = new JSONObject(stJson);
        PersonilPosition personilPosition = new PersonilPosition();
        personilPosition.setNip(jsonObject.getString("nip")); // nip sementara default 1;
        personilPosition.setPositionId(jsonObject.getString("positionid"));

        if (listOfResultPersonil != null)
        {
            List<PersonilPosition> filteredPersonil = listOfResultPersonil.stream().filter(
                    p-> p.getPositionId().equalsIgnoreCase(personilPosition.getPositionId())
                    && p.getFlag().equalsIgnoreCase("Y")
            ).collect(Collectors.toList());

            if (filteredPersonil != null && filteredPersonil.size() > 0)
            {
                response.setStatus("error");
                response.setMsg("Data Sudah Ada Pada List");
                return response;
            }
        }

        if (listOfResultPersonil == null)
            listOfResultPersonil = new ArrayList<>();

        // add to session list of Personil Position;
        personilPosition.setPositionName(jsonObject.getString("positionname"));
        personilPosition.setProfesiId(jsonObject.getString("profesiid"));
        personilPosition.setProfesiName(jsonObject.getString("profesiname"));
        personilPosition.setBranchId(jsonObject.getString("branchid"));
        personilPosition.setBranchName(jsonObject.getString("branchname"));
        personilPosition.setJenisPegawai(jsonObject.getString("jenispegawai"));
        personilPosition.setJenisPegawaiName(jsonObject.getString("jenispegawainame"));
        personilPosition.setFlagDigaji(jsonObject.getString("flagdigaji"));
        personilPosition.setDivisiId(jsonObject.getString("divisiid"));
        personilPosition.setDivisiName(jsonObject.getString("divisiname"));
        personilPosition.setFlag(jsonObject.getString("flag"));
        listOfResultPersonil.add(personilPosition);

        session.removeAttribute("listOfPersonilPosition");
        session.setAttribute("listOfPersonilPosition", listOfResultPersonil);
        // END

        logger.info("[BiodataAction.saveAddToPersonilPositionSession] END <<<");
        return response;
    }

    public CrudResponse saveEditToPersonilPositionSession(String stJson) throws JSONException{
        logger.info("[BiodataAction.saveEditToPersonilPositionSession] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PersonilPosition> listOfResultPersonil = (List<PersonilPosition>) session.getAttribute("listOfPersonilPosition");

        CrudResponse response = new CrudResponse();
        if (stJson == null || "".equalsIgnoreCase(stJson))
        {
            response.setStatus("error");
            response.setMsg("Tidak dapat mendapatkan JSON");
            return response;
        }

        // kasus - kasus validasi
        response = validationPersonilPosition(stJson);
        if ("error".equalsIgnoreCase(response.getStatus())){
            return response;
        }
        // END

        JSONObject jsonObject = new JSONObject(stJson);

        String nip                  = jsonObject.getString("nip");
        String positionId           = jsonObject.getString("positionid");
        String idPersonilPosition   = jsonObject.getString("idpersonilposition");
        String positionIdLama       = jsonObject.getString("positionidlama");
        String flagEdited           = "";

        // jika yg diedit adalah position yg sudah tersimpan
        if (idPersonilPosition != null && !"".equalsIgnoreCase(idPersonilPosition))
            flagEdited = "Y";
        // END

        List<PersonilPosition> personilPositoinNew = listOfResultPersonil;
        for (PersonilPosition editPersonilPosition : personilPositoinNew)
        {

            // jika ditemukan position nip dan position id lama; maka input nilai-nilai nya
            if (editPersonilPosition.getNip().equalsIgnoreCase(nip) &&
                    editPersonilPosition.getPositionId().equalsIgnoreCase(positionIdLama))
            {
                editPersonilPosition.setNip(nip);
                editPersonilPosition.setPositionId(positionId);
                editPersonilPosition.setPersonilPositionId(idPersonilPosition);
                editPersonilPosition.setFlagEdited(flagEdited);
                editPersonilPosition.setPositionIdLama(positionIdLama);
                editPersonilPosition.setPositionName(jsonObject.getString("positionname"));
                editPersonilPosition.setProfesiId(jsonObject.getString("profesiid"));
                editPersonilPosition.setProfesiName(jsonObject.getString("profesiname"));
                editPersonilPosition.setBranchId(jsonObject.getString("branchid"));
                editPersonilPosition.setBranchName(jsonObject.getString("branchname"));
                editPersonilPosition.setJenisPegawai(jsonObject.getString("jenispegawai"));
                editPersonilPosition.setJenisPegawaiName(jsonObject.getString("jenispegawainame"));
                editPersonilPosition.setFlagDigaji(jsonObject.getString("flagdigaji"));
                editPersonilPosition.setFlag(jsonObject.getString("flag"));
                editPersonilPosition.setDivisiId(jsonObject.getString("divisiid"));
                editPersonilPosition.setDivisiName(jsonObject.getString("divisiname"));
            }
            // END
        }

        // validasi jika ditemukan nip dan position baru yang sama
        List<PersonilPosition> filterdPersonilPosition = personilPositoinNew.stream().filter(
                p -> p.getNip().equalsIgnoreCase(nip) &&
                        p.getPositionId().equalsIgnoreCase(positionId)
        ).collect(Collectors.toList());

        if (filterdPersonilPosition.size() > 1){
            response.setStatus("error");
            response.setMsg("Data Nip dan Position Tersebut Sudah ada.");
            return response;
        }
        // END;

        session.removeAttribute("listOfPersonilPosition");
        session.setAttribute("listOfPersonilPosition", personilPositoinNew);

        logger.info("[BiodataAction.saveEditToPersonilPositionSession] END <<<");
        return response;
    }

    public PersonilPosition initEditSessionPosition(String nip, String positionId){
        logger.info("[BiodataAction.initEditSessionPosition] START >>>");

        PersonilPosition personilPosition = new PersonilPosition();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PersonilPosition> listOfResultPersonil = (List<PersonilPosition>) session.getAttribute("listOfPersonilPosition");

        if (listOfResultPersonil == null || listOfResultPersonil.size() == 0)
            logger.error("[BiodataAction.initEditSessionPosition] ERROR Session tidak ditemukan");
        else
        {
            // mencari pada session list berdasarkan nip dan position id
            List<PersonilPosition> filteredPosition = listOfResultPersonil.stream().filter(
                    p-> p.getNip().equalsIgnoreCase(nip) &&
                            p.getPositionId().equalsIgnoreCase(positionId)
            ).collect(Collectors.toList());
            // END

            // set ke object personilPosition untuk direturn dan print log error bila tidak ditemukan;
            if (filteredPosition == null || filteredPosition.size() == 0)
                logger.error("[BiodataAction.initEditSessionPosition] ERROR data tidak ditemukan pada session");
            else
                personilPosition = filteredPosition.get(0);
        }

        return personilPosition;
    }

    private CrudResponse validationPersonilPosition(String stJson) throws JSONException{
        logger.info("[BiodataAction.validationPersonilPosition] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PersonilPosition> listOfResultPersonil = (List<PersonilPosition>) session.getAttribute("listOfPersonilPosition");

        // membuat object baru Crudesponses dan men-set nilai status default = "error"
        CrudResponse response = new CrudResponse();
        response.setStatus("error");
        // END

        JSONObject jsonObject = new JSONObject(stJson);

        String nip                  = jsonObject.getString("nip");
        String positionId           = jsonObject.getString("positionid");
        String branchId             = jsonObject.getString("branchid");
        String flag                 = jsonObject.getString("flag");
        String jenisPegawaiId       = jsonObject.getString("jenispegawai");

        ApplicationContext ctx  = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo   = (PositionBo) ctx.getBean("positionBoProxy");

        Boolean isJenisPegawaiDefault   = checkIsJenisPegawaiDefault(jenisPegawaiId);
        boolean isDelete                = "N".equalsIgnoreCase(flag);
        String jenisPegawaiIdDefault    = getJenisPegawaiDefault().getJenisPegawaiId();

        // jika delete
        if (isDelete)
        {
            if (isJenisPegawaiDefault)
            {
                List<PersonilPosition> filteredPersonilPosition = listOfResultPersonil.stream().filter(
                        p->p.getJenisPegawai().equalsIgnoreCase(jenisPegawaiIdDefault)
                                && p.getNip().equalsIgnoreCase(nip)
                                && p.getFlag().equalsIgnoreCase("Y")
                                && !p.getPositionId().equalsIgnoreCase(positionId)
                ).collect(Collectors.toList());

                if (filteredPersonilPosition == null || filteredPersonilPosition.size() == 0)
                {
                    response.setMsg("Tidak ditemukan posisi utama lain pada list posisi jika di hapus. ");
                    return response;
                }
            }
        }

        else // bukan delete
        {
            // check jika sudah ada pada session
            List<PersonilPosition> filteredPersonilPosition = listOfResultPersonil.stream().filter(
                    p->p.getPositionId().equalsIgnoreCase(positionId)
                            && p.getNip().equalsIgnoreCase(nip)
                            && p.getJenisPegawai().equalsIgnoreCase(jenisPegawaiId)
                            && p.getFlag().equalsIgnoreCase("Y")
            ).collect(Collectors.toList());

            if (filteredPersonilPosition != null && filteredPersonilPosition.size() > 0)
            {
                response.setMsg("Data Sudah Terlist. Silahkan Check List Jabatan. ");
                return response;
            }
            // END

            // check jika jabatan sudah terpakai dan tidak boleh rangkap pada position tersebut
            try {
                PersonilPosition personilPosition = positionBo.getAndCheckJabatanTerpakai(positionId, branchId);
                if (personilPosition != null){
                    response.setMsg("ditemukan pegawai aktif pada jabatan tersebut : "+personilPosition.getPersonName());
                    return response;
                }
            } catch (GeneralBOException e){
                logger.info("[BiodataAction.validationPersonilPosition] ERROR. ", e);
                response.setMsg("[BiodataAction.validationPersonilPosition] ERROR. " + e);
                return response;
            }
            // END

            List<PersonilPosition> filteredForJenisPegawaiDefaultAktif = listOfResultPersonil.stream().filter(
                    p->p.getJenisPegawai().equalsIgnoreCase(jenisPegawaiIdDefault)
                            && p.getNip().equalsIgnoreCase(nip)
                            && p.getFlag().equalsIgnoreCase("Y")
            ).collect(Collectors.toList());

            if (!isJenisPegawaiDefault)
            {
                // jika bukan jenis pegawai default yang dipilih. maka check jika tidak ada posisi utama.
                if (filteredForJenisPegawaiDefaultAktif == null || filteredForJenisPegawaiDefaultAktif.size() == 0)
                {
                    response.setMsg("Tidak Ada Jabatan Utama Aktif Pada List. Tambahkan / Edit Terlebih Dahulu.");
                    return response;
                } //END
            }
            else
            {
                // jika bukan jenis pegawai default yang dipilih. maka check jika tidak ada posisi utama.
                if (filteredForJenisPegawaiDefaultAktif != null && filteredForJenisPegawaiDefaultAktif.size() > 0)
                {
                    response.setMsg("Sudah Ada Jabatan Utama Aktif.");
                    return response;
                } //END
            }
        }



        response.setStatus("success");
        logger.info("[BiodataAction.validationPersonilPosition] END <<<");
        return response;
    }

    private Boolean checkIsJenisPegawaiDefault(String jenisPegawai){
        logger.info("[BiodataAction.checkIsJenisPegawaiDefault] START >>>");

        ApplicationContext ctx  = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo     = (BiodataBo) ctx.getBean("biodataBoProxy");
        Boolean isDefault       = false;

        List<JenisPegawai> jenisPegawais = new ArrayList<>();

        try {
            jenisPegawais = biodataBo.getAllJenisPegawai();
        } catch (GeneralBOException e){
            logger.info("[BiodataAction.checkIsJenisPegawaiDefault] ERROR. ",e);
        }

        if (jenisPegawais != null && jenisPegawais.size() > 0)
        {
            List<JenisPegawai> filteredJenisPegawai = jenisPegawais.stream().filter(
                    p->p.getJenisPegawaiId().equalsIgnoreCase(jenisPegawai)
                    && p.getFlagDefault().equalsIgnoreCase("Y")
            ).collect(Collectors.toList());

            if (filteredJenisPegawai != null && filteredJenisPegawai.size() > 0)
                isDefault = true;
        }


        logger.info("[BiodataAction.checkIsJenisPegawaiDefault] END <<<");
        return isDefault;
    }

    private JenisPegawai getJenisPegawaiDefault(){
        logger.info("[BiodataAction.getJenisPegawaiDefault] START >>>");

        ApplicationContext ctx  = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo     = (BiodataBo) ctx.getBean("biodataBoProxy");

        List<JenisPegawai> jenisPegawais = new ArrayList<>();

        try {
            jenisPegawais = biodataBo.getAllJenisPegawai();
        } catch (GeneralBOException e){
            logger.info("[BiodataAction.getJenisPegawaiDefault] ERROR. ",e);
        }

        JenisPegawai jenisPegawai = new JenisPegawai();

        if (jenisPegawais != null && jenisPegawais.size() > 0)
        {
            List<JenisPegawai> filteredJenisPegawai = jenisPegawais.stream().filter(
                    p->p.getFlagDefault().equalsIgnoreCase("Y")
            ).collect(Collectors.toList());

            if (filteredJenisPegawai != null && filteredJenisPegawai.size() > 0)
                jenisPegawai = filteredJenisPegawai.get(0);
        }

        logger.info("[BiodataAction.getJenisPegawaiDefault] END <<<");
        return jenisPegawai;
    }

    public List<JenisPegawai> getAllJenisPegawai(){
        logger.info("[BiodataAction.getJenisPegawaiDefault] START >>>");

        ApplicationContext ctx  = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo     = (BiodataBo) ctx.getBean("biodataBoProxy");

        List<JenisPegawai> jenisPegawais = new ArrayList<>();

        try {
            jenisPegawais = biodataBo.getAllJenisPegawai();
        } catch (GeneralBOException e){
            logger.info("[BiodataAction.checkIsJenisPegawaiDefault] ERROR. ",e);
        }


        logger.info("[BiodataAction.getJenisPegawaiDefault] END <<<");
        return jenisPegawais;
    }

    public void clearSessionJabatan(){
        logger.info("[BiodataAction.clearSessionJabatan] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfPersonilPosition");

        logger.info("[BiodataAction.clearSessionJabatan] END <<<");
    }

    //RAKA-09JAN2021 ==> Mendapatkan Seq untuk NIP
    public String getSeqNip() {
        logger.info("[BiodataAction.getSeqNip] START >>>>>>");
        String seq = "";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        try {
            seq = biodataBo.getSeqNip();
        } catch (GeneralBOException e) {
            logger.error("[BiodataAction.getSeqNip] Failed to get Sequence.");
        }

        logger.info("[BiodataAction.getSeqNip] END >>>>>>");
        return seq;
    }

    public String ksoToKaryawan() {
        logger.info("[BiodataAction.ksoToKaryawan] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Biodata transBiodata = new Biodata();

        if(itemFlag != null){
            try {
                transBiodata = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getPersonalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BiodataAction.ksoToKaryawan] Error when retrieving edit data,", e1);
                }
                logger.error("[BiodataAction.ksoToKaryawan] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(transBiodata != null) {
                transBiodata.setFlagDokterKso("N");
                setBiodata(transBiodata);
            } else {
                transBiodata.setFlag(itemFlag);
                transBiodata.setNip(itemId);
                setBiodata(transBiodata);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            transBiodata.setNip(itemId);
            transBiodata.setFlag(getFlag());
            setBiodata(transBiodata);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);

        //remove session
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSertifikat");
        session.removeAttribute("listOfPersonilPosition");

        logger.info("[BiodataAction.ksoToKaryawan] end process >>>");

        return "init_add_user";
    }

    public String paging(){
        return SUCCESS;
    }

    public String pagingBatih(){
        return "bidataDanBatih";
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
