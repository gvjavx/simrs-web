package com.neurix.simrs.transaksi.checkup.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckupAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(CheckupAction.class);
    private CheckupBo checkupBoProxy;
    private PelayananBo pelayananBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private DokterBo dokterBoProxy;

    public DokterBo getDokterBoProxy() {
        return dokterBoProxy;
    }

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    private List<JenisPriksaPasien> listOfJenisPriksaPasien = new ArrayList<>();
    private List<Pelayanan> listOfPelayanan = new ArrayList<>();

    private HeaderCheckup headerCheckup;
    private String id;

    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CheckupAction.logger = logger;
    }

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public HeaderCheckup getHeaderCheckup() {
        return headerCheckup;
    }

    public void setHeaderCheckup(HeaderCheckup headerCheckup) {
        this.headerCheckup = headerCheckup;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public JenisPriksaPasienBo getJenisPriksaPasienBoProxy() {
        return jenisPriksaPasienBoProxy;
    }

    public List<JenisPriksaPasien> getListOfJenisPriksaPasien() {
        return listOfJenisPriksaPasien;
    }

    public void setListOfJenisPriksaPasien(List<JenisPriksaPasien> listOfJenisPriksaPasien) {
        this.listOfJenisPriksaPasien = listOfJenisPriksaPasien;
    }

    public List<Pelayanan> getListOfPelayanan() {
        return listOfPelayanan;
    }

    public void setListOfPelayanan(List<Pelayanan> listOfPelayanan) {
        this.listOfPelayanan = listOfPelayanan;
    }

    @Override
    public String add() {

        logger.info("[CheckupAction.add] start process >>>");

        HeaderCheckup checkup = new HeaderCheckup();
        setHeaderCheckup(checkup);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupAction.add] end process <<<");

        return "init_add";
    }

    @Override
    public String edit() {

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<HeaderCheckup> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();
        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (HeaderCheckup headerCheckup : listOfResult) {
                    if (id.equalsIgnoreCase(headerCheckup.getNoCheckup())) {
                        setHeaderCheckup(headerCheckup);
                        break;
                    }
                }

            } else {
                setHeaderCheckup(new HeaderCheckup());
            }
        } else {
            setHeaderCheckup(new HeaderCheckup());
        }

        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {

        logger.info("[CheckupAction.view] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<HeaderCheckup> listOfResult = (List) session.getAttribute("listOfResult");
        List<HeaderDetailCheckup> listOfsearchDetailCheckup = new ArrayList();
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (HeaderCheckup headerCheckup : listOfResult) {
                    if (id.equalsIgnoreCase(headerCheckup.getNoCheckup())) {
                        setHeaderCheckup(headerCheckup);
                        detailCheckup.setNoCheckup(headerCheckup.getNoCheckup());
                        break;
                    }
                }

            } else {
                setHeaderCheckup(new HeaderCheckup());
            }
        } else {
            setHeaderCheckup(new HeaderCheckup());
        }

        try {
            listOfsearchDetailCheckup = checkupDetailBoProxy.getByCriteria(detailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.view] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        session.removeAttribute("listOfRiwayat");
        session.setAttribute("listOfRiwayat", listOfsearchDetailCheckup);

        logger.info("[CheckupAction.view] DATA YANG DI PARAM ID: "+getId());
        logger.info("[CheckupAction.view] end process <<<");

        return "init_view";
    }

    @Override
    public String save() {
        return "save";
    }

    @Override
    public String search() {
        logger.info("[CheckupAction.search] start process >>>");

        HeaderCheckup headerCheckup = getHeaderCheckup();
        List<HeaderCheckup> listOfsearchHeaderCheckup = new ArrayList();

        try {
            listOfsearchHeaderCheckup = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = checkupBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CheckupAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CheckupAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderCheckup);

        logger.info("[CheckupAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[CheckupAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();



        session.removeAttribute("listOfResult");
        logger.info("[CheckupAction.initForm] end process >>>");
        return "search";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public String saveAdd(){

        logger.info("[CheckupAction.saveAdd] start process >>>");
        try {
            HeaderCheckup checkup = getHeaderCheckup();
            String userLogin = CommonUtil.userLogin();
            String userArea  = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            String tgl_lahir = checkup.getStTglLahir();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                java.util.Date date = format.parse(tgl_lahir);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                checkup.setTglLahir(sqlDate);
            } catch (ParseException e) {

            }

            checkup.setBranchId(userArea);
            checkup.setCreatedWho(userLogin);
            checkup.setLastUpdate(updateTime);
            checkup.setCreatedDate(updateTime);
            checkup.setLastUpdateWho(userLogin);
            checkup.setAction("C");
            checkup.setFlag("Y");

            String fileName = "";
            if (this.fileUpload != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadContentType)) {
                    if (this.fileUpload.length() <= 5242880 && this.fileUpload.length() > 0) {

                        // file name
                        fileName = checkup.getNoKtp()+"_"+this.fileUploadFileName;

                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_KTP_PASIEN;

                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, fileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlKtp(fileName);
                            checkupBoProxy.saveAdd(checkup);
                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                        }
                    }
                }
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupAction.saveAdd] end process >>>");
        return "success_add";

    }

    public String getComboJenisPeriksaPasien(){
        List<JenisPriksaPasien> lisJenisPeriksa = new ArrayList<>();
        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();

        try {
            lisJenisPeriksa = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (HibernateException e){
            logger.error("[CheckupAction.getComboJenisPeriksaPasien] Error when get data for combo listOfJenisPriksaPasien", e);
            addActionError(" Error when get data for combo listOfJenisPriksaPasien" + e.getMessage());
        }

        listOfJenisPriksaPasien.addAll(lisJenisPeriksa);
        return "init_add";
    }

    public String getComboPelayanan(){
        List<Pelayanan> pelayananList = new ArrayList<>();

        try {
            pelayananList = pelayananBoProxy.getListAllPelayanan();
        } catch (HibernateException e){
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfPelayanan.addAll(pelayananList);
        return "init_add";
    }

    public List<HeaderCheckup> listDataPasien(String noCheckup){
        logger.info("[CheckupAction.listDataPasien] start process >>>");

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
           headerCheckupList = checkupBo.getByCriteria(headerCheckup);
        }catch (GeneralBOException e){
            logger.error("[CheckupAction.listDataPasien] Error when searching detail pasien, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupAction.listDataPasien] end process >>>");
        return headerCheckupList;
    }

    public List<HeaderDetailCheckup> listRiwayatPasien(String noCheckup){
        logger.info("[CheckupAction.listRiwayatPasien] start process >>>");

        List<HeaderDetailCheckup> headerDetailCheckupList = new ArrayList<>();
        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
        headerDetailCheckup.setNoCheckup(noCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        try {
            headerDetailCheckupList = checkupDetailBo.getByCriteria(headerDetailCheckup);
        }catch (GeneralBOException e){
            logger.error("[CheckupAction.listDataPasien] Error when searching detail pasien, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupAction.listRiwayatPasien] end process >>>");
        return headerDetailCheckupList;
    }

    public List<Dokter> listOfDokter(String idPelayanan){
        logger.info("[CheckupAction.listOfDokter] start process >>>");

        List<Dokter> dokterList = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");

        try {
            dokterList = dokterBo.getByIdPelayanan(idPelayanan, "");
        }catch (GeneralBOException e){
            logger.error("[CheckupAction.listOfDokter] Error when searching data, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupAction.listOfDokter] end process >>>");
        return dokterList;
    }

    public String saveEdit(){

        logger.info("[CheckupAction.saveEdit] start process >>>");
        try {
            HeaderCheckup checkup = getHeaderCheckup();
            String userLogin = CommonUtil.userLogin();
            String userArea  = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            String tgl_lahir = checkup.getStTglLahir();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                java.util.Date date = format.parse(tgl_lahir);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                checkup.setTglLahir(sqlDate);
            } catch (ParseException e) {

            }

            checkup.setBranchId(userArea);
            checkup.setLastUpdate(updateTime);
            checkup.setLastUpdateWho(userLogin);

            String fileName = "";
            if (this.fileUpload != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadContentType)) {
                    if (this.fileUpload.length() <= 5242880 && this.fileUpload.length() > 0) {

                        // file name
                        fileName = checkup.getNoKtp()+"_"+this.fileUploadFileName;

                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_KTP_PASIEN;

                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, fileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlKtp(fileName);
                            checkupBoProxy.saveEdit(checkup);

                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                        }
                    }
                }
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CheckupAction.saveEdit] end process >>>");
        return "search";

    }

}