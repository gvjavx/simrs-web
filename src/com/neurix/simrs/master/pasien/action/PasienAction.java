package com.neurix.simrs.master.pasien.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.ImSImrsRekamMedicLamaEntity;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.ImSimrsUploadRekamMedicLamaEntity;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.hemodialisa.model.Hemodialisa;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.method.P;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PasienAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(PasienAction.class);

    private Pasien pasien;
    private String userId;
    private PasienBo pasienBoProxy;
    private List<Pasien> listOfpasien = new ArrayList<>();

    private File[] fileUploadImage;
    private String[] fileUploadImageContentType;
    private String[] fileUploadImageFileName;

    private String tipe;

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

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PasienAction.logger = logger;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PasienBo getPasienBoProxy() {
        return pasienBoProxy;
    }

    public List<Pasien> getListOfpasien() {
        return listOfpasien;
    }

    public void setListOfpasien(List<Pasien> listOfpasien) {
        this.listOfpasien = listOfpasien;
    }

    public File[] getFileUploadImage() {
        return fileUploadImage;
    }

    public void setFileUploadImage(File[] fileUploadImage) {
        this.fileUploadImage = fileUploadImage;
    }

    public String[] getFileUploadImageContentType() {
        return fileUploadImageContentType;
    }

    public void setFileUploadImageContentType(String[] fileUploadImageContentType) {
        this.fileUploadImageContentType = fileUploadImageContentType;
    }

    public String[] getFileUploadImageFileName() {
        return fileUploadImageFileName;
    }

    public void setFileUploadImageFileName(String[] fileUploadImageFileName) {
        this.fileUploadImageFileName = fileUploadImageFileName;
    }

    public Pasien init(String kode, String flag) {
        logger.info("[PasienAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pasien> listOfResult = (List<Pasien>) session.getAttribute("listOfResult");
        List<Pasien> listPasien = new ArrayList<>();

        if (kode != null && !"".equalsIgnoreCase(kode)) {
            if (listOfResult != null) {
                for (Pasien pasien : listOfResult) {
                    if (kode.equalsIgnoreCase(pasien.getIdPasien()) && flag.equalsIgnoreCase(pasien.getFlag())) {
                        String desaId = pasien.getDesaId();
                        listPasien = pasienBoProxy.getDataPasien(desaId);

                        for (Pasien data : listPasien) {
                            pasien.setProvinsiId(data.getProvinsi());
                            pasien.setKotaId(data.getKota());
                            pasien.setKecamatanId(data.getKecamatan());
                            pasien.setDesa(data.getDesa());
                            pasien.setDesaId(data.getDesaId());
                        }
                        setPasien(pasien);
                        break;
                    }
                }
            } else {
                setPasien(new Pasien());
            }
            logger.info("[PasienAction.init] end process >>>>>");
        }
        return getPasien();
    }

    @Override
    public String add() {
        logger.info("[PasienAction.add] start process");

        Pasien addPasien = new Pasien();
        setPasien(addPasien);
        setAdd(true);
        setAddOrEdit(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[Pasien.add] stop process");
        return "add";
    }

    @Override
    public String edit() {
        logger.info("[PasienAction.edit] start process >>>>");
        String pasienId = getId();
        String pasienFlag = getFlag();

        Pasien editPasien = new Pasien();

        if (pasienFlag != null) {
            try {
                editPasien = init(pasienId, pasienFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "pasienBO.getBelajarByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PasienAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PasienAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editPasien != null) {
                setPasien(editPasien);
            } else {
                editPasien.setFlag(pasienFlag);
                editPasien.setIdPasien(pasienId);
                setPasien(editPasien);
                addActionError("Error, Unable to find data with id = " + pasienId);
                return "failure";
            }
        } else {
            editPasien.setIdPasien(pasienId);
            editPasien.setFlag(pasienFlag);
            setPasien(editPasien);
            addActionError("Error, Unable to find data with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PasienAction.edit] end process >>>>>");
        return "edit";
    }

    @Override
    public String delete() {
        logger.info("[PasienAction.delete] start process");

        String idPasien = getId();
        String flag = getFlag();
        Pasien deletePasien = new Pasien();

        if (flag != null) {
            try {
                deletePasien = init(idPasien, flag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PasienAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PasienAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePasien != null) {
                setPasien(deletePasien);
            } else {
                deletePasien.setIdPasien(idPasien);
                deletePasien.setFlag(flag);
                setPasien(deletePasien);
                addActionError("Error, Unable to find data with id = " + idPasien);
                return "failure";
            }
        } else {
            deletePasien.setIdPasien(idPasien);
            deletePasien.setFlag(flag);
            setPasien(deletePasien);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PasienAction.delete] end process <<<<<<");
        return "delete";
    }


    @Override
    public String view() {
        logger.info("[PasienAction.view] start process >>>>");

        Pasien pasien = init(this.id, "Y");
        setPasien(pasien);

        if ("edit".equalsIgnoreCase(getTipe())) {
            return "edit_password";
        }
        logger.info("[PasienAction.view] end process <<<<<<");
        return "create_user";
    }

    @Override
    public String save() {
        logger.info("[PasienAction.save] start process >>>>");

        Pasien pasien = getPasien();
        pasien.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        pasien.setLastUpdateWho(CommonUtil.userLogin());

        if (!"".equalsIgnoreCase(pasien.getPassword()) && pasien.getPassword() != null) {
            try {
                pasienBoProxy.saveEditPassword(pasien);
            } catch (GeneralBOException e) {
                logger.error("[PasienAction.save] Error, Unable to save password. ", e);
                addActionError("Error, Unable to save password. " + e.getMessage());
            }

            logger.info("[PasienAction.save] end process <<<<<<");
            return "edit_password";
        } else {

            try {
                pasienBoProxy.saveCreateUserPasien(pasien);
            } catch (GeneralBOException e) {
                logger.error("[PasienAction.save] Error, Unable to save create user. ", e);
                addActionError("Error, Unable to save create user. " + e.getMessage());
            }
        }

        logger.info("[PasienAction.save] end process <<<<<<");
        return "create_user";
    }

    @Override
    public String search() {
        logger.info("[PasienAction.search] start process");

        Pasien searchPesien = getPasien();
        List<Pasien> listOfPasien = new ArrayList<>();

        try {
            listOfPasien = pasienBoProxy.getSearchForMaster(searchPesien);
        } catch (GeneralBOException e) {
            logger.error("[PasienAction.getByCriteria] Error when get by criteria pasien, please inform to your admin.", e);
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfPasien);

        logger.info("[BelajarAction.search] end process <<<");

        return "search";
    }

    public String saveAdd() {
        logger.info(("[PasienAction.saveAdd] start process"));

        try {
            Pasien pasien = getPasien();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pasien.setFlag("Y");
            pasien.setAction("C");
            pasien.setCreatedDate(updateTime);
            pasien.setCreatedWho(userLogin);
            pasien.setLastUpdate(updateTime);
            pasien.setLastUpdateWho(userLogin);

            if (this.fileUpload != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadContentType)) {
                    if (this.fileUpload.length() <= 5242880 && this.fileUpload.length() > 0) {
                        // file name
                        String fileName = this.fileUploadFileName;
                        String fileNameReplace = fileName.replace(" ", "_");
                        String newFileName = pasien.getNoKtp() + "-" + dateFormater("MM") + dateFormater("yy") + "-" + fileNameReplace;
                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_KTP_PASIEN;
                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, newFileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            pasien.setUrlKtp(newFileName);
                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                            throw new GeneralBOException("[CheckupAction.uploadImages] error, " + e.getMessage());
                        }
                    }else{
                        logger.error("[CheckupAction.uploadImages] error image size more then 5MB, ");
                        throw new GeneralBOException("[CheckupAction.uploadImages] error image size more then 5MB");
                    }
                }
            }

            Boolean cekData = pasienBoProxy.cekNikPasien(pasien.getNoKtp());
            if(cekData){
                logger.error("NiK "+pasien.getNoKtp()+"Sudah ada");
                throw new GeneralBOException("NIK : "+pasien.getNoKtp()+" Sudah ada...!");
            }else{
                pasienBoProxy.saveAdd(pasien);
            }
        } catch (GeneralBOException e) {
            logger.error("Error when save pasien, "+e.getMessage());
            throw new GeneralBOException("Error when save pasien, "+ e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        logger.info("[pasienAction.saveAdd] end process >>>>");
        return "add";
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public String saveEdit() {
        logger.info("[PasienAction.saveEdit] start process >>>");
        try {

            Pasien editPasien = getPasien();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPasien.setLastUpdateWho(userLogin);
            editPasien.setLastUpdate(updateTime);
            editPasien.setAction("U");
            editPasien.setFlag("Y");

            pasienBoProxy.saveEdit(editPasien);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PasienAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PasienAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PasienAction.saveEdit] end process <<<");
        return "edit";
    }

    public String saveDelete() {
        logger.info("[pasienAction.saveDelete] start process >>>>");

        try {
            Pasien deletePasien = getPasien();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePasien.setLastUpdate(updateTime);
            deletePasien.setLastUpdateWho(userLogin);
            deletePasien.setAction("U");
            deletePasien.setFlag("N");

            pasienBoProxy.saveDelete(deletePasien);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PasienAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PasienAction.saveDelete] Error when editing item pasien," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[PasienAction.saveDelete] end process <<<");
        return "delete";
    }

    public String printCard() {
        logger.info("[pasienAction.printCard] start process >>>>");

        Pasien pasien = init(this.id, "Y");

        reportParams.put("id", this.id);
        reportParams.put("nama", pasien.getNama());
        reportParams.put("notelp", pasien.getNoTelp());
        reportParams.put("alamat", pasien.getJalan());
        reportParams.put("prov", pasien.getProvinsi());
        reportParams.put("kota", pasien.getKota());
        reportParams.put("kec", pasien.getKecamatan());
        reportParams.put("desa", pasien.getDesa());
        reportParams.put("photo", CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.IMAGE_CARD);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "printCard");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printCard] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "search";

        }

        logger.info("[PasienAction.printCard] end process <<<");
        return "print_card";
    }

    public List listPasienWithId(String query) {
        logger.info("[PasienAction.listPasienWithId] start process >>>");

        List<Pasien> pasienList = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        try {
            pasienList = pasienBo.getListOfPasienByQuery(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pasienBo.saveErrorMessage(e.getMessage(), "PasienAction.listPasienWithId");
            } catch (GeneralBOException e1) {
                logger.error("[PasienAction.listPasienWithId] Error when saving error,", e1);
            }
            logger.error("[PasienAction.listPasienWithId] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PasienAction.listPasienWithId] end process <<<");
        return pasienList;
    }

    @Override
    public String initForm() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Pasien pasien = new Pasien();
        setPasien(pasien);
        session.removeAttribute("listOfResult");
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

    public List getListComboPasien(String query, String tipe) {
        logger.info("[PasienAction.getListComboPasien] start process >>>");

        List<Pasien> listOfPasien = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");

        if ("paket_perusahaan".equalsIgnoreCase(tipe)) {
            try {
                listOfPasien = pasienBo.getListPasienWithPaket(query);
            } catch (GeneralBOException e) {
                logger.error("[PasienAction.getListComboPasien] Error when get combo pasien, please inform to your admin.", e);
            }
        } else {
            try {
                listOfPasien = pasienBo.getComboRmLama(query);
            } catch (GeneralBOException e) {
                logger.error("[PasienAction.getListComboPasien] Error when get combo pasien, please inform to your admin.", e);
            }
        }

        logger.info("[PasienAction.getListComboPasien] end process <<<");
        return listOfPasien;
    }

    public List getTypeAheadPasienByIdAndName(String query) {
        logger.info("[PasienAction.getTypeAheadPasienByIdAndName] start process >>>");

        List<Pasien> listOfPasien = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");

        try {
            listOfPasien = pasienBo.getTypeAheadPasienByIdAndName(query);
        } catch (GeneralBOException e) {
            logger.error("[PasienAction.getTypeAheadPasienByIdAndName] Error when get combo pasien, please inform to your admin.", e);
        }

        logger.info("[PasienAction.getListComboPasien] end process <<<");
        return listOfPasien;
    }

    public List getListComboPasienByBpjs(String query) {
        logger.info("[PasienAction.getListComboPasienByBpjs] start process >>>");

        List<Pasien> listOfPasien = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");

        try {
            listOfPasien = pasienBo.getListComboPasienByBpjs(query);
        } catch (GeneralBOException e) {
            logger.error("[PasienAction.getListComboPasienByBpjs] Error when get combo pasien, please inform to your admin.", e);
        }

        logger.info("[PasienAction.getListComboPasienByBpjs] end process <<<");
        return listOfPasien;
    }

    public String getListComboSelectPasien() {
        logger.info("[PasienAction.getListComboSelectPasien] start process >>>");

        List<Pasien> pasienList = new ArrayList<>();
        Pasien pasien = new Pasien();

        try {
            pasienList = pasienBoProxy.getByCriteria(pasien);
        } catch (GeneralBOException e) {
            logger.error("[PasienAction.getListComboSelectPasien] Error when get data pasien ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfpasien.addAll(pasienList);
        logger.info("[PasienAction.getListComboSelectPasien] end process <<<");
        return SUCCESS;
    }

    public String saveUploadRmLama() throws IOException {

        File fileToCreate = null;
        Pasien pasien = getPasien();

        String branchId = CommonUtil.userBranchLogin();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ImSImrsRekamMedicLamaEntity rekamMedicLamaEntity = new ImSImrsRekamMedicLamaEntity();
        rekamMedicLamaEntity.setIdPasien(pasien.getIdPasien());
        rekamMedicLamaEntity.setBranchId(branchId);
        rekamMedicLamaEntity.setFlag("Y");
        rekamMedicLamaEntity.setAction("C");
        rekamMedicLamaEntity.setCreatedDate(time);
        rekamMedicLamaEntity.setLastUpdate(time);
        rekamMedicLamaEntity.setCreatedWho(userLogin);
        rekamMedicLamaEntity.setLastUpdateWho(userLogin);

        List<ImSimrsUploadRekamMedicLamaEntity> uploads = new ArrayList<>();

        ImSimrsUploadRekamMedicLamaEntity uploadRekamMedicLamaEntity;
        if (fileUploadImage != null) {
            for (int i = 0; i < fileUploadImage.length; i++) {
                if (fileUploadImage[i] != null) {
                    String fileName = fileUploadImageFileName[i];
                    String fileNameReplace = fileName.replace(" ", "_");
                    String seqImg = pasienBoProxy.getNextIdImg();
                    File imagePath = fileUploadImage[i];
                    String fileTipe = fileUploadImageContentType[i];

                    if ("image/jpeg".equalsIgnoreCase(fileTipe)) {

                        // set new file path and file name to copy
                        String filePathToCopy = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + File.separator + CommonConstant.URL_IMG_RM + File.separator;
                        String newFileName = branchId + "_" + pasien.getIdPasien() + "_" + seqImg + "_" + fileNameReplace;

                        // set new file then copying to new path directory
                        fileToCreate = new File(filePathToCopy, newFileName);
                        FileUtils.copyFile(imagePath, fileToCreate);

                        logger.info("fileName : " + fileNameReplace);
                        logger.info("imagePath : " + imagePath);
                        logger.info("fileTipe : " + fileTipe);

                        uploadRekamMedicLamaEntity = new ImSimrsUploadRekamMedicLamaEntity();
                        uploadRekamMedicLamaEntity.setUrlImg(newFileName);
                        uploadRekamMedicLamaEntity.setId("URM" + seqImg);
                        uploads.add(uploadRekamMedicLamaEntity);

                    } else {
                        logger.error("[PasienAction.saveUploadRmLama] Error when saving rekam medic lama, (content type is not image file), please inform to your admin.");
                        addActionError("Error,  Error when saving rekam medic lama, (content type is not image file), please inform to your admin.");
                        return ERROR;
                    }
                }
            }

            try {
                pasienBoProxy.saveUploadRekamMedicLama(rekamMedicLamaEntity, uploads);
            } catch (GeneralBOException e) {
                logger.error("[PasienAction.saveUploadRmLama] Error when saving rekam medic lama, please inform to your admin.");
                addActionError("Error,  Error when saving rekam medic lama, please inform to your admin.");
                return ERROR;
            }
        }

        return "search";
    }

    public CrudResponse saveUploadRmLama(String data, String idPasien, String noRmLama){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        CrudResponse response = new CrudResponse();
        String branchId = CommonUtil.userBranchLogin();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ImSImrsRekamMedicLamaEntity rekamMedicLamaEntity = new ImSImrsRekamMedicLamaEntity();
        rekamMedicLamaEntity.setIdPasien(idPasien);
        rekamMedicLamaEntity.setBranchId(branchId);
        rekamMedicLamaEntity.setFlag("Y");
        rekamMedicLamaEntity.setAction("C");
        rekamMedicLamaEntity.setCreatedDate(time);
        rekamMedicLamaEntity.setLastUpdate(time);
        rekamMedicLamaEntity.setCreatedWho(userLogin);
        rekamMedicLamaEntity.setLastUpdateWho(userLogin);
        rekamMedicLamaEntity.setNoRmLama(noRmLama);

        try {
            JSONArray json = new JSONArray(data);
            List<ImSimrsUploadRekamMedicLamaEntity> uploads = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                ImSimrsUploadRekamMedicLamaEntity uploadRekamMedicLamaEntity = new ImSimrsUploadRekamMedicLamaEntity();
                if(obj.has("gambar")){
                    if(!"".equalsIgnoreCase(obj.getString("gambar"))){
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(obj.getString("gambar"));
                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            String fileName = idPasien + "-"+i+ "-" + patten + ".png";
                            String uploadFile =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.URL_IMG_RM + fileName;
                            if(!"".equalsIgnoreCase(uploadFile)){
                                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                                if (image == null) {
                                    logger.error("Buffered Image is null");
                                    response.setStatus("error");
                                    response.setMsg("Buffered Image is null");
                                } else {
                                    CrudResponse pon = CommonUtil.compressImage(image, "png", uploadFile);
                                    if("success".equalsIgnoreCase(pon.getStatus())){
                                        uploadRekamMedicLamaEntity.setUrlImg(fileName);
                                    }else{
                                        response.setStatus(pon.getStatus());
                                        response.setMsg(pon.getMsg());
                                        return pon;
                                    }
//                                    File f = new File(uploadFile);
//                                    // write the image
//                                    ImageIO.write(image, "png", f);
//                                    uploadRekamMedicLamaEntity.setUrlImg(fileName);
                                }
                            }
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg("Found Error, "+e.getMessage());
                        }
                    }
                }
                uploads.add(uploadRekamMedicLamaEntity);
            }
            if(uploads.size() > 0){
                try {
                    response = pasienBo.saveUploadRekamMedicLama(rekamMedicLamaEntity, uploads);
                } catch (GeneralBOException e) {
                    logger.error("[PasienAction.saveUploadRmLama] Error when saving rekam medic lama, please inform to your admin.");
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            }
        }catch (JSONException e){
            response.setStatus("error");
            response.setMsg("Found Error, "+e.getMessage());
        }
        return response;
    }

    public Pasien getDataPasien(String idPasien) {

        Pasien pasien = new Pasien();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        List<Pasien> pasienList = new ArrayList<>();

        if (!"".equalsIgnoreCase(idPasien) && idPasien != null) {

            Pasien listPasien = new Pasien();
            listPasien.setIdPasien(idPasien);

            try {
                pasienList = pasienBo.getByCriteria(listPasien);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data pasien " + e.getMessage());
            }

            if (pasienList.size() > 0) {
                pasien = pasienList.get(0);

                if (!"".equalsIgnoreCase(pasien.getIdPasien()) && pasien.getIdPasien() != null) {
                    setPasien(pasien);
                }
            }
        }

        return pasien;
    }

    public CheckResponse setPasswordPasien(String idPasien, String password) {

        CheckResponse response = new CheckResponse();

        Pasien pasien = new Pasien();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        List<Pasien> pasienList = new ArrayList<>();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        if (!"".equalsIgnoreCase(idPasien) && idPasien != null) {

            Pasien listPasien = new Pasien();
            listPasien.setIdPasien(idPasien);
            listPasien.setPassword(password);
            listPasien.setLastUpdate(time);
            listPasien.setLastUpdateWho(userLogin);

            try {
                pasienBo.saveEditPassword(listPasien);
                response.setStatus("success");
                response.setMessage("Berhasil menyimpan password");
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data pasien " + e.getMessage());
                response.setStatus("error");
                response.setMessage("Found Error when update password " + e.getMessage());
            }
        }

        return response;
    }

    public CrudResponse saveEditPasien(String jsonString) {

        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");

        try {
            JSONObject obj = new JSONObject(jsonString);
            Pasien dataPasien = new Pasien();
            dataPasien.setIdPasien(obj.getString("id_pasien"));
            dataPasien.setNoKtp(obj.getString("nik"));
            dataPasien.setNoBpjs(obj.getString("no_bpjs"));
            dataPasien.setNama(obj.getString("nama"));
            dataPasien.setJenisKelamin(obj.getString("jk"));
            dataPasien.setTempatLahir(obj.getString("tempat_lahir"));
            dataPasien.setTglLahir(obj.getString("tanggal_lahir"));
            dataPasien.setAgama(obj.getString("agama"));
            dataPasien.setProfesi(obj.getString("profesi"));
            dataPasien.setSuku(obj.getString("suku"));
            dataPasien.setJalan(obj.getString("alamat"));
            dataPasien.setDesaId(obj.getString("desa_id"));
            dataPasien.setFlag(obj.getString("flag"));
            dataPasien.setNoTelp(obj.getString("no_telp"));
            dataPasien.setLastUpdate(time);
            dataPasien.setLastUpdateWho(userLogin);

            if (obj.getString("img_ktp") != null && !"".equalsIgnoreCase(obj.getString("img_ktp"))) {
                try {
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes = decoder.decodeBuffer(obj.getString("img_ktp"));
                    logger.info("Decoded upload data : " + decodedBytes.length);
                    String fileName = dataPasien.getNoKtp() + "-" + dateFormater("MM") + dateFormater("yy") + ".png";
                    String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_KTP_PASIEN + fileName;
                    logger.info("File save path : " + uploadFile);
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                    if (image == null) {
                        logger.error("Buffered Image is null");
                    } else {
                        File f = new File(uploadFile);
                        ImageIO.write(image, "png", f);
                        dataPasien.setUrlKtp(fileName);
                    }
                } catch (IOException e) {
                    response.setStatus("error");
                    response.setMsg("Error " + e.getMessage());
                    logger.error("Found Error " + e.getMessage());
                }
            }

            try {
//                Boolean cekData = pasienBo.cekNikPasien(obj.getString("nik"));
//                if(cekData){
//                    response.setStatus("error");
//                    response.setMsg("NIK : "+obj.getString("nik")+" sudah ada");
//                }else{
//
//                }
                response = pasienBo.saveEdit(dataPasien);
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("Error " + e.getMessage());
                logger.error("Found Error " + e.getMessage());
            }

        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Error " + e.getMessage());
            logger.error("Found Error " + e.getMessage());
        }
        return response;
    }

    public List getListComboPasienByRmLama(String rm) {
        logger.info("[PasienAction.getListComboPasienByRmLama] start process >>>");
        List<Pasien> listOfPasien = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        try {
            listOfPasien = pasienBo.getComboRmLama(rm);
        } catch (GeneralBOException e) {
            logger.error("[PasienAction.getListComboPasienByRmLama] Error when get combo pasien, please inform to your admin.", e);
        }
        logger.info("[PasienAction.getListComboPasienByRmLama] end process <<<");
        return listOfPasien;
    }

    public Pasien detailPasien(String idPasien) {
        logger.info("[PasienAction.detailPasien] start process >>>");
        Pasien pasien = new Pasien();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        try {
            pasien = pasienBo.getDetailPasien(idPasien);
        } catch (GeneralBOException e) {
            logger.error("[PasienAction.detailPasien] Error when get combo pasien, please inform to your admin.", e);
        }
        logger.info("[PasienAction.detailPasien] end process <<<");
        return pasien;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public void setPasienBoProxy(PasienBo pasienBoProxy) {
        this.pasienBoProxy = pasienBoProxy;
    }
}