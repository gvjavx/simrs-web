package com.neurix.hris.transaksi.ijinKeluar.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.jamkerja.bo.JamKerjaBo;
import com.neurix.hris.master.jamkerja.model.JamKerja;
import com.neurix.hris.master.libur.bo.LiburBo;
import com.neurix.hris.master.libur.model.Libur;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.model.positionBagian;
import com.neurix.hris.master.strukturJabatan.bo.StrukturJabatanBo;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
import com.neurix.hris.transaksi.ijinKeluar.bo.impl.IjinKeluarBoImpl;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarAnggota;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class IjinKeluarAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(IjinKeluarAction.class);
    private IjinKeluarBo ijinKeluarBoProxy;
    private IjinKeluar ijinKeluar;
    private PositionBagianBo positionBagianBoProxy;
    private boolean admin = false;
    private boolean dispenLahir = false;
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;

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

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    private List<IjinKeluar> initComboAlat;

    public PositionBagianBo getPositionBagianBoProxy() {
        return positionBagianBoProxy;
    }

    public void setPositionBagianBoProxy(PositionBagianBo positionBagianBoProxy) {
        this.positionBagianBoProxy = positionBagianBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        IjinKeluarAction.logger = logger;
    }

    public IjinKeluarBo getIjinKeluarBoProxy() {
        return ijinKeluarBoProxy;
    }

    public void setIjinKeluarBoProxy(IjinKeluarBo ijinKeluarBoProxy) {
        this.ijinKeluarBoProxy = ijinKeluarBoProxy;
    }

    public IjinKeluar getIjinKeluar() {
        return ijinKeluar;
    }

    public void setIjinKeluar(IjinKeluar ijinKeluar) {
        this.ijinKeluar = ijinKeluar;
    }

    public List<IjinKeluar> getInitComboAlat() {
        return initComboAlat;
    }

    public void setInitComboAlat(List<IjinKeluar> initComboAlat) {
        this.initComboAlat = initComboAlat;
    }

    public boolean isDispenLahir() {
        return dispenLahir;
    }

    public void setDispenLahir(boolean dispenLahir) {
        this.dispenLahir = dispenLahir;
    }

    public IjinKeluar init(String kode, String flag){
        logger.info("[IjinKeluar.init] start process >>>");
        List<IjinKeluar> listOfResultIjinKeluar = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (kode.length()==8){
            listOfResultIjinKeluar = (List<IjinKeluar>) session.getAttribute("listOfResultIjinKeluarKantor");
        }else {
            listOfResultIjinKeluar = (List<IjinKeluar>) session.getAttribute("listOfResultIjinKeluar");
        }
        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResultIjinKeluar != null){
                for (IjinKeluar ijinKeluar: listOfResultIjinKeluar) {
                    if(kode.equalsIgnoreCase(ijinKeluar.getIjinKeluarId()) && flag.equalsIgnoreCase(ijinKeluar.getFlag())){

                        if (ijinKeluar.getIjinId().equalsIgnoreCase("IJ013")){
                            setDispenLahir(true);
                            setIjinKeluar(ijinKeluar);
                        }else {
                            setIjinKeluar(ijinKeluar);
                        }
                        break;
                    }
                }
            } else {
                setIjinKeluar(new IjinKeluar());
            }


//            //mengambil data anggota ijin keluar kantor
////            HttpSession session = ServletActionContext.getRequest().getSession();
//            session.removeAttribute("listOfResultAnggotaIjinKeluarKantor");
//            List<IjinKeluarAnggota> daftarAnggota = new ArrayList<>();
//            try{
//                daftarAnggota = ijinKeluarBoProxy.getijinKeluarAnggota(kode);
//            }catch (GeneralBOException e1) {
//                logger.error("[IjinKeluarAction.edit] Error when retrieving edit data,", e1);
//            }
//            session.setAttribute("listOfResultAnggotaIjinKeluarKantor", daftarAnggota);

            logger.info("[IjinKeluar.init] end process >>>");
        }
        return getIjinKeluar();
    }
    public IjinKeluar init2(String kode, String flag){
        logger.info("[IjinKeluar.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<IjinKeluar> listOfResultIjinKeluar = (List<IjinKeluar>) session.getAttribute("listOfResultIjinKeluarIjinKeluar");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResultIjinKeluar != null){
                for (IjinKeluar ijinKeluar: listOfResultIjinKeluar) {
                    if(kode.equalsIgnoreCase(ijinKeluar.getIjinKeluarId()) && flag.equalsIgnoreCase(ijinKeluar.getFlag())){
                        setIjinKeluar(ijinKeluar);
                        break;
                    }
                }
            } else {
                setIjinKeluar(new IjinKeluar());
            }

            logger.info("[IjinKeluar.init] end process >>>");
        }
        return getIjinKeluar();
    }

    @Override
    public String add() {
        logger.info("[IjinKeluar.add] start process >>>");
        String role = CommonUtil.roleAsLogin();
        List<IjinKeluar> listOfsearchBiodata = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();

        if (("ADMIN").equalsIgnoreCase(role)||("Admin Bagian").equalsIgnoreCase(role)){

        }else{
            Biodata searchBiodata = new Biodata();
            IjinKeluar addIjinKeluar = new IjinKeluar();
            String user = CommonUtil.userIdLogin();
            addIjinKeluar.setNip(user);
            searchBiodata.setNip(user);
            listOfsearchBiodata = ijinKeluarBoProxy.getBiodatawithCriteria(user);
            session.removeAttribute("listOfResultIjinKeluarPersonil");
            session.setAttribute("listOfResultIjinKeluarPersonil", listOfsearchBiodata);
            List<IjinKeluar> listOfResultIjinKeluarPersonil = (List<IjinKeluar>) session.getAttribute("listOfResultIjinKeluarPersonil");

            if(listOfResultIjinKeluarPersonil != null){
                for (IjinKeluar ijinKeluar: listOfResultIjinKeluarPersonil) {
                    addIjinKeluar.setNip(ijinKeluar.getNip());
                    addIjinKeluar.setUnitId(ijinKeluar.getUnitId());
                    addIjinKeluar.setNamaPegawai(ijinKeluar.getNamaPegawai());
                    addIjinKeluar.setDivisiId(ijinKeluar.getDivisiId());
                    addIjinKeluar.setGolonganId(ijinKeluar.getGolonganId());
                    addIjinKeluar.setPositionId(ijinKeluar.getPositionId());
                    addIjinKeluar.setSelf("Y");
                    break;
                }
            } else {
                setIjinKeluar(new IjinKeluar());
            }
            setIjinKeluar(addIjinKeluar);
        }

        setAddOrEdit(true);
        setAdd(true);
        session.removeAttribute("listOfResultIjinKeluar");

        logger.info("[IjinKeluar.add] stop process >>>");
        return "init_add";
    }
/*    public String add1() {
        logger.info("[IjinKeluar.add] start process >>>");
        Biodata searchBiodata = new Biodata();
        HttpSession session = ServletActionContext.getRequest().getSession();
        setAddOrEdit(true);
        setAdd(true);
        session.removeAttribute("listOfResultIjinKeluar");

        logger.info("[IjinKeluar.add] stop process >>>");
        return "init_add";
    }*/
    public String addKantor() {
        logger.info("[IjinKeluar.addKantor] start process >>>");
        String role = CommonUtil.roleAsLogin();
        HttpSession session = ServletActionContext.getRequest().getSession();
        IjinKeluar addIjinKeluar = new IjinKeluar();
        List<IjinKeluar> listOfsearchBiodata = new ArrayList();
        session.removeAttribute("listOfResultAnggotaIjinKeluarKantor");
        if (("ADMIN").equalsIgnoreCase(role)||("Admin Bagian").equalsIgnoreCase(role)) {
        }else{
            Biodata searchBiodata = new Biodata();
            String user = CommonUtil.userIdLogin();
            addIjinKeluar.setNip(user);
            searchBiodata.setNip(user);
            listOfsearchBiodata = ijinKeluarBoProxy.getBiodatawithCriteria(user);
            session.removeAttribute("listOfResultIjinKeluarPersonil");
            session.setAttribute("listOfResultIjinKeluarPersonil", listOfsearchBiodata);
            List<IjinKeluar> listOfResultIjinKeluarPersonil = (List<IjinKeluar>) session.getAttribute("listOfResultIjinKeluarPersonil");
            if(listOfResultIjinKeluarPersonil != null){
                for (IjinKeluar ijinKeluar: listOfResultIjinKeluarPersonil) {
                    addIjinKeluar.setNip(ijinKeluar.getNip());
                    addIjinKeluar.setUnitId(ijinKeluar.getUnitId());
                    addIjinKeluar.setNamaPegawai(ijinKeluar.getNamaPegawai());
                    addIjinKeluar.setDivisiId(ijinKeluar.getDivisiId());
                    addIjinKeluar.setGolonganId(ijinKeluar.getGolonganId());
                    addIjinKeluar.setPositionId(ijinKeluar.getPositionId());
                    addIjinKeluar.setSelf("Y");
                    break;
                }
            } else {
                setIjinKeluar(new IjinKeluar());
            }
            setIjinKeluar(addIjinKeluar);
        }

        setAddOrEdit(true);
        setAdd(true);
        session.removeAttribute("listOfResultIjinKeluarKantor");
        logger.info("[IjinKeluar.addKantor] stop process >>>");
        return "init_add_kantor";
    }
    /*public String addKantor1() {
        logger.info("[IjinKeluar.addKantor] start process >>>");
        Biodata searchBiodata = new Biodata();
        HttpSession session = ServletActionContext.getRequest().getSession();
        setAddOrEdit(true);
        setAdd(true);
        session.removeAttribute("listOfResultIjinKeluarKantor");

        logger.info("[IjinKeluar.addKantor] stop process >>>");
        return "init_add_kantor";
    }*/
    @Override
    public String edit() {
        logger.info("[IjinKeluar.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        IjinKeluar editIjinKeluar = new IjinKeluar();

        if(itemFlag != null){
            try {
                editIjinKeluar = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarBO.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinKeluarAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinKeluar.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }
            if(editIjinKeluar != null) {
                setIjinKeluar(editIjinKeluar);
            } else {
                editIjinKeluar.setFlag(itemFlag);
                editIjinKeluar.setIjinKeluarId(itemId);
                setIjinKeluar(editIjinKeluar);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editIjinKeluar.setIjinKeluarId(itemId);
            editIjinKeluar.setFlag(getFlag());
            setIjinKeluar(editIjinKeluar);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[IjinKeluarAction.edit] end process >>>");
        return "init_edit";
    }

    //search anggota saat view
    public List<IjinKeluarAnggota> searchAnggota(String ijinId) {
        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listAnggotaIjinKeluarKantor");
        List<IjinKeluarAnggota> daftarAnggota = new ArrayList<>();
        try{
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
            daftarAnggota = ijinKeluarBo.getijinKeluarAnggota(ijinId);
        }catch (GeneralBOException e1) {
            logger.error("[IjinKeluarAction.edit] Error when retrieving edit data,", e1);
        }
        session.setAttribute("listAnggotaIjinKeluarKantor", daftarAnggota);
        return daftarAnggota;
    }



    @Override
    public String delete() {
        logger.info("[IjinKeluar.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        IjinKeluar deleteIjinKeluar = new IjinKeluar();

        if (itemFlag != null ) {

            try {
                deleteIjinKeluar = init(itemId, itemFlag);
                /*deleteIjinKeluar.setStIjinKeluarFrom(new SimpleDateFormat("dd-MM-yyyy").format(deleteIjinKeluar.getIjinKeluarFrom()));
                deleteIjinKeluar.setStIjinKeluarTo(new SimpleDateFormat("dd-MM-yyyy").format(deleteIjinKeluar.getIjinKeluarTo()));*/
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinKeluar.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[IjinKeluarAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteIjinKeluar != null) {
                setIjinKeluar(deleteIjinKeluar);

            } else {
                deleteIjinKeluar.setIjinKeluarId(itemId);
                deleteIjinKeluar.setFlag(itemFlag);
                setIjinKeluar(deleteIjinKeluar);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteIjinKeluar.setIjinKeluarId(itemId);
            deleteIjinKeluar.setFlag(itemFlag);
            setIjinKeluar(deleteIjinKeluar);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[IjinKeluar.delete] end process <<<");

        return "init_delete";
    }
    public String deleteKantor() {
        logger.info("[IjinKeluar.deleteKantor] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listAnggotaIjinKeluarKantor");
        String itemId = getId();
        String itemFlag = getFlag();
        IjinKeluar deleteIjinKeluar = new IjinKeluar();

        //mengambil data anggota ijin keluar kantor
        searchAnggota(itemId);

        if (itemFlag != null ) {

            try {
                deleteIjinKeluar = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinKeluar.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[IjinKeluarAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteIjinKeluar != null) {
                setIjinKeluar(deleteIjinKeluar);

            } else {
                deleteIjinKeluar.setIjinKeluarId(itemId);
                deleteIjinKeluar.setFlag(itemFlag);
                setIjinKeluar(deleteIjinKeluar);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteIjinKeluar.setIjinKeluarId(itemId);
            deleteIjinKeluar.setFlag(itemFlag);
            setIjinKeluar(deleteIjinKeluar);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[IjinKeluar.delete] end process <<<");

        return "init_delete_kantor";
    }
    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[IjinKeluar.saveEdit] start process >>>");

        try {
            IjinKeluar editIjinKeluar = getIjinKeluar();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            java.sql.Date dateEnd = CommonUtil.convertToDate(editIjinKeluar.getStTanggalAkhir());
            editIjinKeluar.setTanggalAkhir(dateEnd);
            editIjinKeluar.setLastUpdateWho(userLogin);
            editIjinKeluar.setLastUpdate(updateTime);
            editIjinKeluar.setAction("U");
            editIjinKeluar.setFlag("Y");
            if (isDispenLahir())
                editIjinKeluar.setDispenLahir(true);
            else
                editIjinKeluar.setDispenLahir(false);

            if (this.fileUpload != null){
                String idSuratDokter = ijinKeluarBoProxy.getNextSuratDokterId();
                String fileName = idSuratDokter+"_"+this.fileUploadFileName;
                String fileContentType = this.fileUploadContentType;
                String filePath = CommonConstant.RESOURCE_PATH_USER_UPLOAD_SURAT_DOKTER;
                File fileToCreate = new File(filePath, fileName);
                String path = filePath+fileName;

                byte[] contentFile = null;
                try{
                    FileUtils.copyFile(this.fileUpload, fileToCreate);
                    contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                } catch (IOException e) {
                    Long logId = null;
                    try{
                        logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluar.saveEdit");
                    }catch (GeneralBOException e1){
                        logger.error("[IjinKeluar.saveEdit] Error when saving error, ", e1);
                    }
                    logger.error("[IjinKeluar.saveEdit] Error when uploading and saving Study," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                    return ERROR;
                }

                if (contentFile != null){
                    editIjinKeluar.setUploadFile(fileName);
                    if ("image/jpeg".equalsIgnoreCase(fileContentType)) {
                        editIjinKeluar.setFileType("IMG");
                    }else if ("application/pdf".equalsIgnoreCase(fileContentType)){
                        editIjinKeluar.setFileType("PDF");
                    }
                }
                editIjinKeluar.setFilePath(path);
            }

//            String condition;
            ijinKeluarBoProxy.saveEdit(editIjinKeluar);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[IjinKeluarAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[IjinKeluarAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[IjinKeluarAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[IjinKeluarAction.saveDelete] start process >>>");
        try {

            IjinKeluar deleteIjinKeluar = getIjinKeluar();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteIjinKeluar.setLastUpdate(updateTime);
            deleteIjinKeluar.setLastUpdateWho(userLogin);
            deleteIjinKeluar.setAction("U");
            deleteIjinKeluar.setFlag("N");

            ijinKeluarBoProxy.saveDelete(deleteIjinKeluar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[IjinKeluar.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[IjinKeluar.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[IjinKeluar.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[IjinKeluar.saveAdd] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        IjinKeluar ijinKeluar = getIjinKeluar();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dateStart = CommonUtil.convertToDate(ijinKeluar.getStTanggalAwal());
        java.sql.Date dateEnd = CommonUtil.convertToDate(ijinKeluar.getStTanggalAkhir());
        ijinKeluar.setTanggalAwal(dateStart);
        ijinKeluar.setTanggalAkhir(dateEnd);
        ijinKeluar.setCreatedWho(userLogin);
        ijinKeluar.setLastUpdate(updateTime);
        ijinKeluar.setCreatedDate(updateTime);
        ijinKeluar.setLastUpdateWho(userLogin);
        ijinKeluar.setAction("C");
        ijinKeluar.setFlag("Y");
        ijinKeluar.setApprovalFlag("N");
        ijinKeluar.setRoleId(CommonUtil.roleIdAsLogin());

        String path = null;
        if (this.fileUpload != null){
            String idSuratDokter = ijinKeluarBoProxy.getNextSuratDokterId();
            String fileName = idSuratDokter+"_"+this.fileUploadFileName;
            String fileContentType = this.fileUploadContentType;
            String filePath = CommonConstant.RESOURCE_PATH_USER_UPLOAD_SURAT_DOKTER;
            File fileToCreate = new File(filePath, fileName);
            path = filePath+fileName;

            byte[] contentFile = null;
            try{
                FileUtils.copyFile(this.fileUpload, fileToCreate);
                contentFile = FileUtils.readFileToByteArray(this.fileUpload);
            }catch (IOException e){
                Long logId = null;
                try{
                    logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarAction.saveAdd");
                }catch (GeneralBOException e1){
                    logger.error("[IjinKeluar.addIjinKeluar] Error when saving error, ", e1);
                }
                logger.error("[IjinKeluar.addIjinKeluar] Error when uploading and saving IjinKeluar," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                return ERROR;
            }

            if (contentFile != null){
                ijinKeluar.setUploadFile(fileName);
                if ("image/jpeg".equalsIgnoreCase(fileContentType)) {
                    ijinKeluar.setFileType("IMG");
                }else if ("application/pdf".equalsIgnoreCase(fileContentType)){
                    ijinKeluar.setFileType("PDF");
                }
            }
            ijinKeluar.setFilePath(path);
        }

        try {
            notifikasiList = ijinKeluarBoProxy.saveAddIjinKeluar(ijinKeluar);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "ijinKeluarBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[ijinKeluarAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[ijinKeluarAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        for (Notifikasi notifikasi : notifikasiList){
            notifikasiBo.sendNotif(notifikasi);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultIjinKeluar");

        logger.info("[ijinKeluarAction.saveAdd] end process >>>");
        return "success_save_add";
    }
    public String saveAddKantor(){
        logger.info("[IjinKeluar.saveAddKantor] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        IjinKeluar ijinKeluar = getIjinKeluar();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dateStart = CommonUtil.convertToDate(ijinKeluar.getStTanggalAwal());
        ijinKeluar.setTanggalAwal(dateStart);
        ijinKeluar.setCreatedWho(userLogin);
        ijinKeluar.setLastUpdate(updateTime);
        ijinKeluar.setCreatedDate(updateTime);
        ijinKeluar.setLastUpdateWho(userLogin);
        ijinKeluar.setIjinId("IJ001");
        ijinKeluar.setIjinName("Ijin keluar kantor");
        ijinKeluar.setAction("C");
        ijinKeluar.setFlag("Y");
        ijinKeluar.setApprovalFlag("");

        try {
            notifikasiList = ijinKeluarBoProxy.saveAddIjinKeluar(ijinKeluar);
        }catch (GeneralBOException e) {
            Long logId;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "ijinKeluarBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[ijinKeluarAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ijinKeluarAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        for (Notifikasi notifikasi : notifikasiList){
            notifikasiBo.sendNotif(notifikasi);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultIjinKeluarkantor");

        logger.info("[ijinKeluarAction.saveAddKantor] end process >>>");
        return "success_save_add_kantor";
    }
    @Override
    public String search() {
        logger.info("[IjinKeluar.search] start process >>>");

        IjinKeluar searchAlat = getIjinKeluar();
        List<IjinKeluar> listOfSearchIjinKeluar = new ArrayList();
        String role = CommonUtil.roleAsLogin();
        searchAlat.setRoleId(CommonUtil.roleIdAsLogin());
        searchAlat.setFrom("ijinKeluar");

        if ("ADMIN".equalsIgnoreCase(role)){
            setAdmin(true);
        }else if ("Admin bagian".equalsIgnoreCase(role)){

        } else{
            searchAlat.setNip(CommonUtil.userIdLogin());
        }

        if(!("Admin Bagian").equalsIgnoreCase(CommonUtil.roleAsLogin())){
            listOfSearchIjinKeluar = ijinKeluarBoProxy.getByCriteria(searchAlat);
        }else{
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

            positionBagian searchBagian = new positionBagian();
            searchBagian.setBagianName(CommonUtil.userLogin());
            List<positionBagian> positionBagianList = positionBagianBoProxy.getByCriteria(searchBagian);
            for (positionBagian bagian : positionBagianList){
                List<IjinKeluar> ijinKeluarList ;
                List<Biodata> biodataList = biodataBo.getBiodataByBagian(null,null,bagian.getBagianId(),null);
                for (Biodata biodata : biodataList){
                    if(!("").equalsIgnoreCase(searchAlat.getNip())){
                        if (biodata.getNip().equalsIgnoreCase(searchAlat.getNip())){
                            ijinKeluarList = ijinKeluarBoProxy.getByCriteria(searchAlat);

                            listOfSearchIjinKeluar.addAll(ijinKeluarList);
                        }
                    }else{
                        searchAlat.setNip(biodata.getNip());
                        ijinKeluarList = ijinKeluarBoProxy.getByCriteria(searchAlat);
                        listOfSearchIjinKeluar.addAll(ijinKeluarList);
                        searchAlat.setNip("");
                    }
                }
            }
            Comparator<IjinKeluar> comparator = new Comparator<IjinKeluar>() {
                @Override
                public int compare(IjinKeluar left, IjinKeluar right) {
                    String awal =left.getIjinKeluarId().replaceAll("[a-zA-Z]", "");
                    String akhir =right.getIjinKeluarId().replaceAll("[a-zA-Z]", "");
                    Long angka1 = Long.parseLong(awal);
                    Long angka2 = Long.parseLong(akhir);
                    return (int) (angka2-angka1);
                }
            };
            Collections.sort(listOfSearchIjinKeluar, comparator);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultIjinKeluar");
        session.setAttribute("listOfResultIjinKeluar", listOfSearchIjinKeluar);
        logger.info("[IjinKeluar.search] end process <<<");

        return SUCCESS;
    }

    public String searchKantor() {
        logger.info("[IjinKeluar.searchKantor] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultIjinKeluarKantor");
        IjinKeluar searchAlat = getIjinKeluar();
        List<IjinKeluar> listOfSearchIjinKeluarKantor = new ArrayList();
        String role = CommonUtil.roleAsLogin();
        searchAlat.setFrom("ijinKeluarKantor");
        if ("ADMIN".equalsIgnoreCase(role)||"Admin bagian".equalsIgnoreCase(role)){
        }
        else{
            searchAlat.setNip(CommonUtil.userIdLogin());
        }

        if(!("Admin Bagian").equalsIgnoreCase(CommonUtil.roleAsLogin())){
            listOfSearchIjinKeluarKantor = ijinKeluarBoProxy.getByCriteria(searchAlat);
        }else{
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

            positionBagian searchBagian = new positionBagian();
            searchBagian.setBagianName(CommonUtil.userLogin());
            List<positionBagian> positionBagianList = positionBagianBoProxy.getByCriteria(searchBagian);
            for (positionBagian bagian : positionBagianList){
                List<IjinKeluar> ijinKeluarList ;
                List<Biodata> biodataList = biodataBo.getBiodataByBagian(null,null,bagian.getBagianId(),null);
                for (Biodata biodata : biodataList){
                    if(!("").equalsIgnoreCase(searchAlat.getNip())){
                        if (biodata.getNip().equalsIgnoreCase(searchAlat.getNip())){
                            ijinKeluarList = ijinKeluarBoProxy.getByCriteria(searchAlat);

                            listOfSearchIjinKeluarKantor.addAll(ijinKeluarList);
                        }
                    }else{
                        searchAlat.setNip(biodata.getNip());
                        ijinKeluarList = ijinKeluarBoProxy.getByCriteria(searchAlat);
                        listOfSearchIjinKeluarKantor.addAll(ijinKeluarList);
                        searchAlat.setNip("");
                    }
                }
            }
            Comparator<IjinKeluar> comparator = new Comparator<IjinKeluar>() {
                @Override
                public int compare(IjinKeluar left, IjinKeluar right) {
                    String awal =left.getIjinKeluarId().replaceAll("[a-zA-Z]", "");
                    String akhir =right.getIjinKeluarId().replaceAll("[a-zA-Z]", "");
                    Long angka1 = Long.parseLong(awal);
                    Long angka2 = Long.parseLong(akhir);
                    return (int) (angka2-angka1);
                }
            };
            Collections.sort(listOfSearchIjinKeluarKantor, comparator);
        }


        session.setAttribute("listOfResultIjinKeluarKantor", listOfSearchIjinKeluarKantor);

        logger.info("[IjinKeluar.search] end process <<<");

        return "success_search_kantor";
    }
    @Override
    public String initForm() {
        logger.info("[IjinKeluar.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();


        if (("ADMIN").equalsIgnoreCase(CommonUtil.roleAsLogin())){
            setAdmin(true);
        }

        session.removeAttribute("listOfResultIjinKeluar");
        logger.info("[IjinKeluar.initForm] end process >>>");
        return INPUT;
    }
    public String initFormKantor() {
        logger.info("[IjinKeluar.initFormKantor] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultIjinKeluarKantor");
        logger.info("[IjinKeluar.initFormKantor] end process >>>");
        return "input_ijin_keluar_kantor";
    }

    public List initComboSisaIjinKeluarId(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<IjinKeluar> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");

        try {
            listOfAlat = ijinKeluarBo.getComboSisaIjinKeluarWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }




    public List initComboIjinKeluarId(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<IjinKeluar> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");

        try {
            listOfAlat = ijinKeluarBo.getComboIjinKeluarWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }



    public List initComboAlat(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<IjinKeluar> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("alatBoProxy");

        try {
            listOfAlat = ijinKeluarBo.getComboIjinKeluarWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }

    public List<IjinKeluar> approveAtasan(String ijinKeluarId) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listAnggotaIjinKeluarKantor");
        logger.info("[IjinKeluarAction.edit] start process >>>");
        String itemId = ijinKeluarId;
        String itemFlag = "Y";
        List<IjinKeluar> ijinKeluarList = new ArrayList<IjinKeluar>();
        IjinKeluar editIjinKeluar = new IjinKeluar();

        if (itemFlag != null) {
            try {
                editIjinKeluar = init(itemId, itemFlag);
                ijinKeluarList.add(editIjinKeluar);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarBO.getSppdByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinKeluarAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinKeluarAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            }

            if (editIjinKeluar != null) {
                setIjinKeluar(editIjinKeluar);
            } else {
                editIjinKeluar.setFlag(itemFlag);
                editIjinKeluar.setIjinKeluarId(itemId);
                setIjinKeluar(editIjinKeluar);
                addActionError("Error, Unable to find data with id = " + itemId);
            }
        }
//        searchAnggota(editIjinKeluar.getIjinKeluarId());
        setAddOrEdit(true);
        logger.info("[IjinKeluarAction.edit] end process >>>");
        return ijinKeluarList;
    }
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    public  Integer calculateLibur (String stTanggalAwal , String stTanggalAkhir) throws ParseException {
        Integer jumlahHari = 0;

        Libur libur = new Libur();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date tanggalAwal = new java.sql.Date(sdf1.parse(stTanggalAwal).getTime());
        java.sql.Date tanggalAkhir = new java.sql.Date(sdf1.parse(stTanggalAkhir).getTime());

        Calendar start = Calendar.getInstance();
        start.setTime(tanggalAwal);
        Calendar end = Calendar.getInstance();
        end.setTime(tanggalAkhir);
        end.add(Calendar.DATE,1);
        java.util.Date date;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LiburBo liburBo = (LiburBo) ctx.getBean("liburBoProxy");
        List<Libur> liburList = new ArrayList<>();
        for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            java.sql.Timestamp sq = new java.sql.Timestamp(date.getTime());
            libur.setTanggal(sq);
            libur.setFlag("Y");
            liburList=liburBo.getByCriteria(libur);
            if (liburList.size()!=0){
                jumlahHari=jumlahHari+1;
            }
        }
        return jumlahHari;
    }
    public String saveApprove(String IjinKeluarId, String statusApprove, String who,String nip){
        logger.info("[SppdAction.saveEdit] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        try {
            IjinKeluar editIjinKeluar = new IjinKeluar();

            editIjinKeluar.setIjinKeluarId(IjinKeluarId);
            if(who.equals("atasan")){
                if(statusApprove.equals("Y")){
                    editIjinKeluar.setApprovalFlag(statusApprove);
                }else{
                    editIjinKeluar.setApprovalFlag("N");
                    editIjinKeluar.setNotApprovalNote(statusApprove);
                }
            }else{
                if(statusApprove.equals("Y")){
                    editIjinKeluar.setApprovalSdmFlag(statusApprove);
                }else{
                    editIjinKeluar.setApprovalSdmFlag("N");
                    editIjinKeluar.setNotApprovalSdmNote(statusApprove);
                }
            }

            editIjinKeluar.setTmpApprove(who);
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editIjinKeluar.setUserIdActive(nip);
            editIjinKeluar.setUserNameActive(userLogin);
            editIjinKeluar.setNip(nip);
            editIjinKeluar.setLastUpdateWho(userLogin);
            editIjinKeluar.setLastUpdate(updateTime);
            editIjinKeluar.setAction("U");
            editIjinKeluar.setFlag("Y");

            try {
                notifikasiList=ijinKeluarBo.saveApprove(editIjinKeluar);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarBO.saveApprove");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinKeluarAction.saveApprove] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinKeluarAction.saveApprove] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            }


            if (notifikasiList.size()!=0){
                for (Notifikasi notifikasi:notifikasiList){
                    notifikasiBo.sendNotif(notifikasi);
                }
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarBO.saveEdit");
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
    public List initComboPersonil(String query, String branchId) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
//        HttpSession session = WebContextFactory.get().getSession();


        try {
            listOfUser = biodataBo.getListOfPersonil(query,branchId);
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

    public List<IjinKeluar> searchData(String nip, String id) {
        logger.info("[IjinKeluarAction.search] start process >>>");

        IjinKeluar searchIjinKeluar = new IjinKeluar();
        searchIjinKeluar.setNip(nip);
        searchIjinKeluar.setIjinKeluarId(id);
        searchIjinKeluar.setFlag("Y");
        List<IjinKeluar> listOfsearchIjinKeluar = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");

            listOfsearchIjinKeluar = ijinKeluarBo.getByCriteria(searchIjinKeluar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IjinKeluarAction.search] Error when saving error,", e1);
            }
            logger.error("[IjinKeluarAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfsearchIjinKeluar;
    }

    public String initComboTestTanggal(String nip, String tanggalAwal, String tanggalSelesai) {

        List listOfAlat = null;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        try {
            listOfAlat = ijinKeluarBo.getComboTestTanggal(nip,tanggalAwal,tanggalSelesai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IjinKeluarAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[IjinKeluarAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[IjinKeluarAction.initComboLokasiKebun] end process <<<");
        String status = null;
        if (listOfAlat!=null){
            if (listOfAlat.size()!=0){
                status="ada";
            }
            else {
                status="kosong";
            }
        }
        else {
            status="kosong";
        }
        return status;
    }
    
    public String paging(){
        return SUCCESS;
    }
    public String paging_kantor(){
        return SUCCESS;
    }
    public String paging_anggota(){
        return SUCCESS;
    }

    public String cetakSurat(){
        logger.info("[IjinKeluarAction.cetakSurat] end process >>>");

        String id = getId();
        IjinKeluar ijinKeluar = new IjinKeluar();
        ijinKeluar.setIjinKeluarId(id);
        ijinKeluar.setFrom("ijinKeluarKantor");
        List<IjinKeluar> ijinKeluarList= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        try {
            ijinKeluarList = ijinKeluarBo.getByCriteria(ijinKeluar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "medicalRecordBo.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (ijinKeluarList != null){
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            Date dataDate = new java.sql.Date(updateTime.getTime());
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            String stDate = dt1.format(dataDate);
            for (IjinKeluar ijinKeluar1 : ijinKeluarList){
                List<IjinKeluarAnggota> ijinKeluarAnggotaList = new ArrayList<>();
                List<IjinKeluarAnggota> ijinKeluarAnggotaListForReport = new ArrayList<>();
                if (("K").equalsIgnoreCase(ijinKeluar1.getKeperluan())){
                    ijinKeluarAnggotaList=ijinKeluarBo.getijinKeluarAnggota(ijinKeluar1.getIjinKeluarId());
                    int no = 1;
                    for (IjinKeluarAnggota ijinKeluarAnggota : ijinKeluarAnggotaList){
                        ijinKeluarAnggota.setNo(String.valueOf(no)+". ");
                        no++;
                        ijinKeluarAnggotaListForReport.add(ijinKeluarAnggota);
                    }
                }
                JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(ijinKeluarAnggotaList);
                reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
                reportParams.put("ijinKeluarKantorId", id);
                reportParams.put("nama",ijinKeluar1.getNamaPegawai());
                reportParams.put("nip",ijinKeluar1.getNip());
                reportParams.put("jabatan",ijinKeluar1.getPositionName());
                reportParams.put("divisi",ijinKeluar1.getDivisiName());
                reportParams.put("unit",ijinKeluar1.getUnitName());
                reportParams.put("keterangan",ijinKeluar1.getKeterangan());
                reportParams.put("date", stDate);
                reportParams.put("tanggal",ijinKeluar1.getStTanggalAwal());
                reportParams.put("lama",ijinKeluar1.getLamaIjin());
                reportParams.put("jamAwal",ijinKeluar1.getJamKeluar());
                reportParams.put("jamAkhir",ijinKeluar1.getJamKembali());
                reportParams.put("itemDataSource", itemData);
            }
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "printSuratJaminan");
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
        return "print_surat_ijin";
    }
    public String cetakSuratKembali(){
        logger.info("[IjinKeluarAction.cetakSuratKembali] end process >>>");

        String id = getId();
        IjinKeluar ijinKeluar = new IjinKeluar();
        ijinKeluar.setIjinKeluarId(id);
        ijinKeluar.setFrom("ijinKeluarKantor");
        List<IjinKeluar> ijinKeluarList= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        try {
            ijinKeluarList = ijinKeluarBo.getByCriteria(ijinKeluar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "IjinKeluarAction.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (ijinKeluarList != null){
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            Date dataDate = new java.sql.Date(updateTime.getTime());
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            String stDate = dt1.format(dataDate);
            for (IjinKeluar ijinKeluar1 : ijinKeluarList){
                List<IjinKeluarAnggota> ijinKeluarAnggotaList = new ArrayList<>();
                List<IjinKeluarAnggota> ijinKeluarAnggotaListForReport = new ArrayList<>();
                if (("K").equalsIgnoreCase(ijinKeluar1.getKeperluan())){
                    ijinKeluarAnggotaList=ijinKeluarBo.getijinKeluarAnggota(ijinKeluar1.getIjinKeluarId());
                    int no = 1;
                    for (IjinKeluarAnggota ijinKeluarAnggota : ijinKeluarAnggotaList){
                        ijinKeluarAnggota.setNo(String.valueOf(no)+". ");
                        no++;
                        ijinKeluarAnggotaListForReport.add(ijinKeluarAnggota);
                    }
                }
                JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(ijinKeluarAnggotaList);
                reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
                reportParams.put("ijinKeluarKantorId", id);
                reportParams.put("nama",ijinKeluar1.getNamaPegawai());
                reportParams.put("nip",ijinKeluar1.getNip());
                reportParams.put("jabatan",ijinKeluar1.getPositionName());
                reportParams.put("divisi",ijinKeluar1.getDivisiName());
                reportParams.put("unit",ijinKeluar1.getUnitName());
                reportParams.put("keterangan",ijinKeluar1.getKeterangan());
                reportParams.put("date", stDate);
                reportParams.put("tanggal",ijinKeluar1.getStTanggalAwal());
                reportParams.put("lama",ijinKeluar1.getLamaIjin());
                reportParams.put("jamAwal",ijinKeluar1.getJamKeluar());
                reportParams.put("jamAkhir",ijinKeluar1.getJamKembali());
                reportParams.put("itemDataSource", itemData);
            }
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "printSuratJaminan");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinKeluarAction.cetakSuratKembali] Error when downloading ,", e1);
                }
                logger.error("[IjinKeluarAction.cetakSuratKembali] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
                return "failure_print";
            }

        } else {
            logger.error("[TrainingAction.printSuratJaminan] Error when print report realiassi bibit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list notification detail is empty, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[TrainingAction.printSuratJaminan] end process <<<");
        return "print_surat_ijin_kembali";
    }
    public String cetakSuratIjinTidakMasuk(){
        logger.info("[IjinKeluarAction.cetakSurat] end process >>>");

        String id = getId();
        IjinKeluar ijinKeluar = new IjinKeluar();
        ijinKeluar.setIjinKeluarId(id);
        ijinKeluar.setDivisiId("");
        ijinKeluar.setFrom("ijinKeluar");
        List<IjinKeluar> ijinKeluarList= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        try {
            ijinKeluarList = ijinKeluarBo.getByCriteria(ijinKeluar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "ijinKeluarBo.cetakSuratIjinTidakMasuk");
            } catch (GeneralBOException e1) {
                logger.error("[ijinKeluarBo.cetakSuratIjinTidakMasuk] Error when saving error,", e1);
            }
            logger.error("[ijinKeluarBo.cetakSuratIjinTidakMasuk] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (ijinKeluarList != null){
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            String stDate = dt1.format(dataDate);
            for (IjinKeluar ijinKeluar1 : ijinKeluarList){
                Branch branch = new Branch();
                try{
                    BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                    branch = branchBo.getBranchById(ijinKeluar1.getUnitId(),"Y");
                }catch( HibernateException e){
                }

                String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
                reportParams.put("alamatUni", branch.getAlamatSurat()+","+stTanggal);
                reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
                reportParams.put("ijinTidakMasukId", id);
                reportParams.put("nama",ijinKeluar1.getNamaPegawai());
                reportParams.put("nip",ijinKeluar1.getNip());
                reportParams.put("jabatan",ijinKeluar1.getPositionName());
                if (ijinKeluar1.getDivisiName() != null)
                    reportParams.put("divisi",ijinKeluar1.getDivisiName());
                else
                    reportParams.put("divisi","-");
                reportParams.put("unit",ijinKeluar1.getUnitName());
                reportParams.put("ijin",ijinKeluar1.getIjinName());
                if ("IJ013".equalsIgnoreCase(ijinKeluar1.getIjinId())){
                    reportParams.put("lama",ijinKeluar1.getLamaIjinBaru());
                    reportParams.put("tanggalDari",ijinKeluar1.getStTanggalAwal());
                    reportParams.put("tanggalSelesai",ijinKeluar1.getTanggalAkhirBaru());
                }else {
                    reportParams.put("lama",ijinKeluar1.getLamaIjin());
                    reportParams.put("tanggalDari",ijinKeluar1.getStTanggalAwal());
                    reportParams.put("tanggalSelesai",ijinKeluar1.getStTanggalAkhir());
                }
                reportParams.put("date", stDate);
            }
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "printSuratJaminan");
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
        return "print_surat_ijin_tidak_masuk";
    }
    public String calcHour(String unit,String nip,String stTanggal,String jamAwal,String jamAkhir) throws ParseException {
        logger.info("[IjinKeluarAction.calcHour] start process >>>");
        JamKerja jamKerja = new JamKerja();
        List<JamKerja> jamKerjaList = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        java.util.Date istirahatAwal= new java.util.Date();
        java.util.Date istirahatAkhir = new java.util.Date();
        long hasil = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(CommonUtil.convertToDate(stTanggal));
        int hariSekarang = calendar.get(Calendar.DAY_OF_WEEK);

        jamKerja.setBranchId(unit);
        jamKerja.setHariKerja(hariSekarang);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JamKerjaBo jamKerjaBo = (JamKerjaBo) ctx.getBean("jamKerjaBoProxy");
        jamKerjaList = jamKerjaBo.getByCriteria(jamKerja);

        // For search in jadwal istirahat
        for(JamKerja jamKerja1 : jamKerjaList){
            istirahatAwal =df.parse(jamKerja1.getIstirahatAwal());
            istirahatAkhir =df.parse(jamKerja1.getIstirahatAkhir());
        }
        java.util.Date jamMulai =df.parse(jamAwal);
        java.util.Date jamBerakhir =df.parse(jamAkhir);

        if (jamBerakhir.before(jamMulai)){
            hasil = 0;
        }else if (jamBerakhir.before(istirahatAwal)){
            hasil = jamBerakhir.getTime() - jamMulai.getTime();
        }else if(jamMulai.after(istirahatAkhir)){
            hasil = jamBerakhir.getTime() - jamMulai.getTime();
        } else if(jamMulai.before(istirahatAwal)&&jamBerakhir.after(istirahatAkhir)){
            hasil = (jamBerakhir.getTime() - jamMulai.getTime())-(istirahatAkhir.getTime()-istirahatAwal.getTime());
        }else if(jamMulai.before(istirahatAwal)&&jamBerakhir.before(istirahatAkhir)){
            hasil= (jamBerakhir.getTime() - jamMulai.getTime())-(jamBerakhir.getTime()-istirahatAwal.getTime());
        }else if (jamMulai.after(istirahatAwal)&&jamBerakhir.after(istirahatAkhir)){
            hasil = (jamBerakhir.getTime() - jamMulai.getTime())-(istirahatAkhir.getTime()-jamMulai.getTime());
        }else{
            hasil=0;
        }
        java.util.Date result = new java.util.Date(hasil);
        result.setTime(hasil);
        result = new Date(hasil - TimeUnit.HOURS.toMillis(7));
        result = toNearestWholeHour(result);

        return String.valueOf(result.getHours());
    }

    static java.util.Date toNearestWholeHour(java.util.Date d) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);

        if (c.get(Calendar.MINUTE) >= 30)
            c.add(Calendar.HOUR, 1);

        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    public String cancel() {
        logger.info("[IjinKeluarAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        IjinKeluar cancelIjinKeluar = new IjinKeluar();

        if (itemFlag != null ) {
            try {
                cancelIjinKeluar = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarAction.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinKeluarAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[IjinKeluarAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (cancelIjinKeluar != null) {
                setIjinKeluar(cancelIjinKeluar);

            } else {
                cancelIjinKeluar.setIjinKeluarId(itemId);
                cancelIjinKeluar.setFlag(itemFlag);
                setIjinKeluar(cancelIjinKeluar);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            cancelIjinKeluar.setIjinKeluarId(itemId);
            cancelIjinKeluar.setFlag(itemFlag);
            setIjinKeluar(cancelIjinKeluar);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[AlatAction.delete] end process <<<");
        return "init_cancel";
    }

    public String pengajuanBatal() {
        logger.info("[IjinKeluarAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        IjinKeluar cancelIjinKeluar = new IjinKeluar();

        if (itemFlag != null ) {
            try {
                cancelIjinKeluar = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarAction.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinKeluarAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[IjinKeluarAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (cancelIjinKeluar != null) {
                setIjinKeluar(cancelIjinKeluar);

            } else {
                cancelIjinKeluar.setIjinKeluarId(itemId);
                cancelIjinKeluar.setFlag(itemFlag);
                setIjinKeluar(cancelIjinKeluar);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            cancelIjinKeluar.setIjinKeluarId(itemId);
            cancelIjinKeluar.setFlag(itemFlag);
            setIjinKeluar(cancelIjinKeluar);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[AlatAction.delete] end process <<<");
        return "init_pengajuan";
    }

    public String cancelIjinKeluarKantor() {
        logger.info("[IjinKeluarKantorAction.cancelIjinKeluarKantor] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        IjinKeluar cancelIjinKeluarKantor = new IjinKeluar();

        if (itemFlag != null ) {
            try {
                cancelIjinKeluarKantor = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarKantorAction.cancelIjinKeluarKantor");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinKeluarAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[IjinKeluarKantorAction.cancelIjinKeluarKantor] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (cancelIjinKeluarKantor != null) {
                setIjinKeluar(cancelIjinKeluarKantor);

            } else {
                cancelIjinKeluarKantor.setIjinKeluarId(itemId);
                cancelIjinKeluarKantor.setFlag(itemFlag);
                setIjinKeluar(cancelIjinKeluarKantor);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            cancelIjinKeluarKantor.setIjinKeluarId(itemId);
            cancelIjinKeluarKantor.setFlag(itemFlag);
            setIjinKeluar(cancelIjinKeluarKantor);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[IjinKeluarKantorAction.cancelIjinKeluarKantor] end process <<<");
        return "init_cancel_ijin_keluar_kantor";
    }
    public String saveCancel(){
        logger.info("[AlatAction.saveEdit] start process >>>");
        try {
            IjinKeluar cancelIjinKeluar = getIjinKeluar();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            cancelIjinKeluar.setCancelFlag("Y");
            cancelIjinKeluar.setCancelDate(updateTime);
            cancelIjinKeluar.setCancelPerson(userLogin);
            cancelIjinKeluar.setLastUpdateWho(userLogin);
            cancelIjinKeluar.setLastUpdate(updateTime);
            cancelIjinKeluar.setAction("U");
            cancelIjinKeluar.setFlag("Y");

            ijinKeluarBoProxy.saveEdit(cancelIjinKeluar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPegawaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiPegawaiAction.saveEdit] end process <<<");

        return "success_save_cancel";
    }

    public String savePengajuanBatal(){
        logger.info("[AlatAction.saveEdit] start process >>>");
        try {
            IjinKeluar cancelIjinKeluar = getIjinKeluar();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            cancelIjinKeluar.setFlagPengajuanBatal("Y");
            cancelIjinKeluar.setLastUpdateWho(userLogin);
            cancelIjinKeluar.setLastUpdate(updateTime);
            cancelIjinKeluar.setAction("U");
            cancelIjinKeluar.setFlag("Y");

            ijinKeluarBoProxy.savePengajuanBatal(cancelIjinKeluar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPegawaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiPegawaiAction.saveEdit] end process <<<");

        return "success_save_pengajuan_batal";
    }

    public String saveTolakPengajuan(String ijinId){
        logger.info("[AlatAction.saveEdit] start process >>>");
        try {
            IjinKeluar cancelIjinKeluar = new IjinKeluar();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            cancelIjinKeluar.setIjinKeluarId(ijinId);
            cancelIjinKeluar.setFlagPengajuanBatal("N");
            cancelIjinKeluar.setLastUpdateWho(userLogin);
            cancelIjinKeluar.setLastUpdate(updateTime);
            cancelIjinKeluar.setAction("U");
            cancelIjinKeluar.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");

            ijinKeluarBo.saveTolakPengajuanBatal(cancelIjinKeluar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPegawaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiPegawaiAction.saveEdit] end process <<<");

        return "success_save_cancel";
    }

    public String saveCancelIjinKeluarKantor(){
        logger.info("[AlatAction.saveEdit] start process >>>");
        try {
            IjinKeluar cancelIjinKeluarKantor = getIjinKeluar();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            cancelIjinKeluarKantor.setCancelFlag("Y");
            cancelIjinKeluarKantor.setCancelDate(updateTime);
            cancelIjinKeluarKantor.setCancelPerson(userLogin);
            cancelIjinKeluarKantor.setLastUpdateWho(userLogin);
            cancelIjinKeluarKantor.setLastUpdate(updateTime);
            cancelIjinKeluarKantor.setAction("U");
            cancelIjinKeluarKantor.setFlag("Y");

            ijinKeluarBoProxy.saveEditIjinKeluarKantor(cancelIjinKeluarKantor);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPegawaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiPegawaiAction.saveEdit] end process <<<");

        return "success_save_cancel_ijin_keluar_kantor";
    }
    public List<IjinKeluar> searchAnggotaIjin(String nip) {
        logger.info("[IjinKeluar.searchAnggotaIjin] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<IjinKeluar> ijinKeluarList = (List<IjinKeluar>) session.getAttribute("listOfResultAnggotaIjinKeluarKantor");
        if (("").equalsIgnoreCase(nip)){
            session.setAttribute("listOfResultAnggotaIjinKeluarKantor",ijinKeluarList);
        }else{
            List<IjinKeluar> ijinKeluarListNip = new ArrayList<>();
            for (IjinKeluar ijinKeluar:ijinKeluarList){
                if (nip.equalsIgnoreCase(ijinKeluar.getNip())){
                    ijinKeluarListNip.add(ijinKeluar);
                    session.setAttribute("listOfResultAnggotaIjinKeluarKantor",ijinKeluarListNip);
                }
            }
        }
        logger.info("[IjinKeluar.searchAnggotaIjin] end process >>>");
        return ijinKeluarList;
    }
    public void saveAddAnggota(String nip,String nama,String posisi) {
        logger.info("[IjinKeluar.saveAddAnggota] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<IjinKeluar> ijinKeluarList = (List<IjinKeluar>) session.getAttribute("listOfResultAnggotaIjinKeluarKantor");
        List<IjinKeluar> ijinKeluarListNip = new ArrayList<>();
        boolean ada=false;
        if (ijinKeluarList==null){
            IjinKeluar newData = new IjinKeluar();
            newData.setNip(nip);
            newData.setNamaPegawai(nama);
            newData.setPositionId(posisi);
            ijinKeluarListNip.add(newData);
            session.setAttribute("listOfResultAnggotaIjinKeluarKantor",ijinKeluarListNip);
        }else{
            ijinKeluarListNip.addAll(ijinKeluarList);
            for (IjinKeluar ijinKeluar:ijinKeluarList){
                if (ijinKeluar.getNip().equalsIgnoreCase(nip)){
                    ada=true;
                    break;
                }
            }
            if (!ada){
                IjinKeluar newData = new IjinKeluar();
                newData.setNip(nip);
                newData.setNamaPegawai(nama);
                newData.setPositionId(posisi);
                ijinKeluarListNip.add(newData);
                session.setAttribute("listOfResultAnggotaIjinKeluarKantor",ijinKeluarListNip);
            }
        }

        logger.info("[IjinKeluar.saveAddAnggota] end process >>>");
    }
    public void saveDeleteAnggota(String nip) {
        logger.info("[IjinKeluar.saveDeleteAnggota] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<IjinKeluar> ijinKeluarList = (List<IjinKeluar>) session.getAttribute("listOfResultAnggotaIjinKeluarKantor");
        List<IjinKeluar> ijinKeluarListNip = new ArrayList<>();
        for (IjinKeluar ijinKeluar:ijinKeluarList){
            if (ijinKeluar.getNip().equalsIgnoreCase(nip)){
            }else{
                ijinKeluarListNip.add(ijinKeluar);
            }
        }
        session.setAttribute("listOfResultAnggotaIjinKeluarKantor",ijinKeluarListNip);
        logger.info("[IjinKeluar.saveDeleteAnggota] end process >>>");
    }
    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }


    public List listDispensasiMasal(String unit){
        List listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        try {
            listOfResult = ijinKeluarBo.getListDispensasiMasal(unit);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "ijinKeluarAction.listDispensasiMasal");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.searchCutiBersama] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.searchCutiBersama] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[ijinKeluarAction.listDispensasiMasal] end process <<<");

        return listOfResult;
    }

    public void saveDispensasiMasal(){
        logger.info("[IjinKeluar.searchAnggotaIjin] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        IjinKeluar ijinKeluar = getIjinKeluar();

        String userLogin = CommonUtil.userLogin();
        java.sql.Date dateStart = CommonUtil.convertToDate(ijinKeluar.getStTanggalAwal());
        java.sql.Date dateEnd = CommonUtil.convertToDate(ijinKeluar.getStTanggalAkhir());

        String allData = ijinKeluar.getCheckedValue();
        String[]data = allData.split(", ");
        for (String dataLoop: data){

            ijinKeluar.setNip(dataLoop);
            ijinKeluar.setTanggalAwal(dateStart);
            ijinKeluar.setTanggalAkhir(dateEnd);

            ijinKeluar.setCreatedWho(userLogin);
            ijinKeluar.setLastUpdate(updateTime);
            ijinKeluar.setCreatedDate(updateTime);
            ijinKeluar.setLastUpdateWho(userLogin);
            ijinKeluar.setAction("C");
            ijinKeluar.setFlag("Y");

            ijinKeluar.setApprovalFlag("Y");
            ijinKeluar.setApprovalId("0001");
            ijinKeluar.setApprovalName("Admin");
            ijinKeluar.setApprovalDate(updateTime);

            ijinKeluar.setApprovalSdmFlag("Y");
            ijinKeluar.setApprovalSdmId("0001");
            ijinKeluar.setApprovalSdmName("Admin");
            ijinKeluar.setApprovalSdmDate(updateTime);

            try {
                ijinKeluarBo.saveAddDispensasiMasal(ijinKeluar);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "ijinKeluarAction.listDispensasiMasal");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.searchCutiBersama] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.searchCutiBersama] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
            }

            logger.info("[ijinKeluarAction.listDispensasiMasal] end process <<<");
        }
    }

    public String cekIfAbsensi(String nip, String tglDari, String tglSelesai){
        String status ="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");

        try{
            status = ijinKeluarBo.cekIfAbsensi(nip, tglDari,tglSelesai);
        }catch (GeneralBOException e1) {
            logger.error("[TrainingAction.printSuratJaminan] Error when downloading ,", e1);
        }
        return status;
    }

    public String cekNipIjinKeluar(String nip) {
        logger.info("[cutiPegawaiAction.cekNipCuti] start process >>>");

        List<IjinKeluar> listOfIjinKeluar = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        try {
            listOfIjinKeluar = ijinKeluarBo.getListCekNipIjinKeluar(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBo.saveErrorMessage(e.getMessage(), "IjinKeluarAction.cekNipIjinKeluar");
            } catch (GeneralBOException e1) {
                logger.error("[IjinKeluarAction.cekNipIjinKeluar] Error when saving error,", e1);
            }
            logger.error("[IjinKeluarAction.cekNipIjinKeluar] Error when search data," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[IjinKeluarAction.cekNipIjinKeluar] end process <<<");

        if (listOfIjinKeluar.size()!=0){
            return "00";
        }else{
            return "";
        }
    }
}
