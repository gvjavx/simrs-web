package com.neurix.hris.master.biodata.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.dao.PelatihanJabatanUserDao;
import com.neurix.hris.master.biodata.model.*;
import com.neurix.hris.master.ijin.model.Ijin;
import com.neurix.hris.master.keluarga.model.Keluarga;
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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
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
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Biodata> listOfResult = (List<Biodata>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Biodata biodata : listOfResult) {
                    if(kode.equalsIgnoreCase(biodata.getNip()) && flag.equalsIgnoreCase(biodata.getFlag())){
                        setBiodata(biodata);
                        break;
                    }
                }
            } else {
                setBiodata(new Biodata());
            }

            logger.info("[BiodataAction.init] end process >>>");
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
        setBiodata(addBiodata);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        session.removeAttribute("historyJabatanForSmk");
        session.removeAttribute("branchForSmk");
        session.removeAttribute("positionIdForSmk");

        logger.info("[BiodataAction.add] stop process >>>");
        return "init_add_user";
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
        logger.info("[BiodataAction.edit] end process >>>");
        return "init_add_user";
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

        return "init_add_user";
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
        if (isAddOrEdit()) {

            if (!isAdd()) {
                logger.info("[BiodataAction.saveEdit] start process >>>");
                try {

                    Biodata editBiodata = getBiodata();
                    String golonganId = editBiodata.getGolongan().replace(",","");
                    editBiodata.setGolongan(golonganId);
                    editBiodata.setGolonganId(golonganId);
                    String golonganId2 = editBiodata.getGolongan().replace(" ","");
                    editBiodata.setGolongan(golonganId2);
                    editBiodata.setGolonganId(golonganId2);
                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    if (biodata.getStTanggalLahir() != null && !"".equalsIgnoreCase(biodata.getStTanggalLahir())) {
                        biodata.setTanggalLahir(CommonUtil.convertToDate(biodata.getStTanggalLahir()));
                    }

                    if (biodata.getStTanggalPensiun() != null && !"".equalsIgnoreCase(biodata.getStTanggalPensiun())) {
                        biodata.setTanggalPensiun(CommonUtil.convertToDate(biodata.getStTanggalPensiun()));
                    }

                    if (biodata.getStTanggalMasuk() != null && !"".equalsIgnoreCase(biodata.getStTanggalMasuk())) {
                        biodata.setTanggalMasuk(CommonUtil.convertToDate(biodata.getStTanggalMasuk()));
                    }

                    if (biodata.getStTanggalAktif() != null && !"".equalsIgnoreCase(biodata.getStTanggalAktif())) {
                        biodata.setTanggalAktif(CommonUtil.convertToDate(biodata.getStTanggalAktif()));
                    }

                    if (this.fileUpload!=null) {

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

                    editBiodata.setLastUpdateWho(userLogin);
                    editBiodata.setLastUpdate(updateTime);
                    editBiodata.setAction("U");
                    editBiodata.setFlag(editBiodata.getFlag());

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
            } else {
                //add
                try {
                    Biodata biodata = getBiodata();
                    String golonganId = biodata.getGolongan().replace(",","");
                    biodata.setGolongan(golonganId);
                    biodata.setGolonganId(golonganId);
                    String golonganId2 = biodata.getGolongan().replace(" ","");
                    biodata.setGolongan(golonganId2);
                    biodata.setGolonganId(golonganId2);
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

                    if (this.fileUpload!=null) {
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;
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
                    biodata.setTanggalAktif(CommonUtil.convertTimestampToDate(updateTime));
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
                        logger.error("[pengalamanKerjaAction.saveAdd] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[pengalamanKerjaAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }


                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResult");
                session.removeAttribute("listKeluarga");
                session.removeAttribute("listStudy");
                session.removeAttribute("listPengalamanKerja");
                session.removeAttribute("listReward");
                session.removeAttribute("listSertifikat");

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
            biodata.setTanggalAktif(CommonUtil.convertTimestampToDate(updateTime));
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
                logger.error("[pengalamanKerjaAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[pengalamanKerjaAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

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

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchBiodata);

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

        session.removeAttribute("listOfResult");
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
        session.removeAttribute("listOfResult");
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

    public PelatihanJabatanUser initSearchPelatihan(String kode){
        logger.info("[BiodataAction.initSearchPelatihan] start process >>>");
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
        return getPelatihanJabatanUser();
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

    public void saveAddPengalaman(String nip, String namaPerusahaan, String jabatan, String tanggalMasuk, String tanggalKeluar){
        logger.info("[StudyAction.saveAdd] start process >>>");

        try {
            PengalamanKerja pengalamanKerja = new PengalamanKerja();
            pengalamanKerja.setNip(nip);
            pengalamanKerja.setNamaPerusahaan(namaPerusahaan);
            pengalamanKerja.setJabatan(jabatan);

            if(tanggalMasuk != null && !"".equalsIgnoreCase(tanggalMasuk)){
                pengalamanKerja.setTahunMasuk(CommonUtil.convertStringToDate(tanggalMasuk));
                pengalamanKerja.setStTtahunMasuk(tanggalMasuk);
            }

            if(tanggalKeluar != null && !"".equalsIgnoreCase(tanggalKeluar)){
                pengalamanKerja.setTahunKeluar(CommonUtil.convertStringToDate(tanggalKeluar));
                pengalamanKerja.setStTahunKeluar(tanggalKeluar);
            }

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pengalamanKerja.setCreatedWho(userLogin);
            pengalamanKerja.setLastUpdate(updateTime);
            pengalamanKerja.setCreatedDate(updateTime);
            pengalamanKerja.setLastUpdateWho(userLogin);
            pengalamanKerja.setAction("C");
            pengalamanKerja.setFlag("Y");

            int id = 0;
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<PengalamanKerja> listOfResult = (List<PengalamanKerja>) session.getAttribute("listPengalamanKerja");
            if(listOfResult != null){
                for(PengalamanKerja pengalamanKerja1: listOfResult){
                    id = Integer.parseInt(pengalamanKerja1.getPengalamanId());
                }
                id++;
                pengalamanKerja.setPengalamanId(id + "");
                listOfResult.add(pengalamanKerja);
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

    public void saveAddSertifikat(String nip, String jenis, String tanggalPengesahan, String masaBerlaku, String masaBerakhir, String nama, String lembaga, String tempatPelaksana,
                                  String nilai, String lulus, String prestasi){
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
            sertifikat.setNilai(Double.parseDouble(nilai));
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

    public String initEditPengalaman(String id, String nip, String namaPerusahaan, String jabatan, String tanggalMasuk, String tanggalKeluar){
        logger.info("[BiodataAction.saveEdit] start process >>>");
        PengalamanKerja pengalamanKerja = new PengalamanKerja();

        pengalamanKerja.setPengalamanId(id);
        pengalamanKerja.setNip(nip);
        pengalamanKerja.setNamaPerusahaan(namaPerusahaan);
        pengalamanKerja.setJabatan(jabatan);
        pengalamanKerja.setStTtahunMasuk(tanggalMasuk);
        pengalamanKerja.setStTahunKeluar(tanggalKeluar);

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

    public void initEditSertifikat(String id, String nip, String jenis, String tanggalPengesahan, String masaBerlaku, String masaBerakhir, String nama, String lembaga,
                                   String tempatPelaksana, String nilai, String lulus, String prestasi){
        logger.info("[BiodataAction.saveEditSertifikat] start process >>>");
        Sertifikat sertifikat = new Sertifikat();
        sertifikat.setNip(nip);
        sertifikat.setJenis(jenis);
        sertifikat.setStTanggalPengesahan(tanggalPengesahan);
        sertifikat.setStMasaBerlaku(masaBerlaku);
        sertifikat.setStMasaBerakhir(masaBerakhir);
        sertifikat.setNama(nama);
        sertifikat.setLembaga(lembaga);
        sertifikat.setTempatPelaksana(tempatPelaksana);
        sertifikat.setNilai(Double.parseDouble(nilai));
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
                                            String tipePegawaiId, String golonganId,String pjsFlag, String perusahaanLain, String bidangLain, String jabatanLain, String flagAktif){
        logger.info("[BiodataAction.saveAdd] start process >>>");

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

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.addPengalamanKerja(historyJabatanPegawai);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "pengalamanKerjaBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[pengalamanKerjaAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[pengalamanKerjaAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
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

    public void saveAddDataPelatihan(String nip, String jenis, String lembaga, String angkatan, String tahun, String status, String nilai){
        logger.info("[BiodataAction.saveAddDataPelatihan] start process >>>");

        try {
            PelatihanJabatanUser pelatihanJabatanUser = new PelatihanJabatanUser();
            pelatihanJabatanUser.setNip(nip);
            pelatihanJabatanUser.setPelatihanJabatanId(jenis);
            pelatihanJabatanUser.setLembaga(lembaga);
            pelatihanJabatanUser.setAngkatan(angkatan);
            pelatihanJabatanUser.setTahun(tahun);
            pelatihanJabatanUser.setStatus(status);

            if(nilai != null && !nilai.equalsIgnoreCase("")){
                pelatihanJabatanUser.setNilai(Double.parseDouble(nilai));
            }else{
                pelatihanJabatanUser.setNilai(0);
            }

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pelatihanJabatanUser.setCreatedWho(userLogin);
            pelatihanJabatanUser.setLastUpdate(updateTime);
            pelatihanJabatanUser.setCreatedDate(updateTime);
            pelatihanJabatanUser.setLastUpdateWho(userLogin);
            pelatihanJabatanUser.setAction("C");
            pelatihanJabatanUser.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.addPelatihan(pelatihanJabatanUser);
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

    public void saveEditPelatihan(String id, String nip, String jenis, String lembaga, String angkatan, String tahun, String status, String nilai){
        logger.info("[BiodataAction.saveAddDataPelatihan] start process >>>");

        try {
            PelatihanJabatanUser pelatihanJabatanUser = new PelatihanJabatanUser();
            pelatihanJabatanUser.setPelatihanUserId(id);
            pelatihanJabatanUser.setNip(nip);
            pelatihanJabatanUser.setPelatihanJabatanId(jenis);
            pelatihanJabatanUser.setLembaga(lembaga);
            pelatihanJabatanUser.setAngkatan(angkatan);
            pelatihanJabatanUser.setTahun(tahun);
            pelatihanJabatanUser.setStatus(status);

            if(nilai != null && !nilai.equalsIgnoreCase("")){
                pelatihanJabatanUser.setNilai(Double.parseDouble(nilai));
            }else{
                pelatihanJabatanUser.setNilai(0);
            }

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pelatihanJabatanUser.setCreatedWho(userLogin);
            pelatihanJabatanUser.setLastUpdate(updateTime);
            pelatihanJabatanUser.setCreatedDate(updateTime);
            pelatihanJabatanUser.setLastUpdateWho(userLogin);
            pelatihanJabatanUser.setAction("C");
            pelatihanJabatanUser.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            userBo.saveEditPelatihan(pelatihanJabatanUser);
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
                                          String tipePegawaiId, String golonganId, String perusahaanLain, String bidangLain, String jabatanLain, String flagAktif){
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

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


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
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "BiodataBo.saveDeletePengalamanKerja");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiodataAction.saveDeletePengalamanKerja] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiodataAction.saveDeletePengalamanKerja] end process <<<");

        return "success_save_delete";
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

        session.removeAttribute("listOfResult");
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
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            listKeluarga = biodataBo.listKeluarga(getId());
            listRiwayatPekerjaan = biodataBo.listRiwayatPekerjaan(getId());
            listPendidikan = biodataBo.listStudy(getId());

            JRBeanCollectionDataSource itemKeluarga = new JRBeanCollectionDataSource(listKeluarga);
            JRBeanCollectionDataSource itemRiwayatPekerjaan = new JRBeanCollectionDataSource(listRiwayatPekerjaan);
            JRBeanCollectionDataSource itemPendidikan = new JRBeanCollectionDataSource(listPendidikan);

            String foto = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() +
                    CommonConstant.RESOURCE_PATH_USER_UPLOAD + getId() + ".jpg";


            if (!new File(foto).exists()){
                foto = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() +
                        CommonConstant.RESOURCE_PATH_USER_UPLOAD + "img_avatar.png";
            }
            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("urlFoto", foto);
            reportParams.put("nip", getId());
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
