package com.neurix.hris.transaksi.sppd.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.sppd.bo.SppdBo;
import com.neurix.hris.transaksi.sppd.dao.SppdRerouteDao;
import com.neurix.hris.transaksi.sppd.model.*;
import com.neurix.hris.transaksi.training.model.Training;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.File;
import org.directwebremoting.io.FileTransfer;
import org.springframework.context.ApplicationContext;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class SppdAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SppdAction.class);
    private SppdBo sppdBoProxy;
    private Sppd sppd;
    private SppdPerson sppdPerson;
    private SppdDoc sppdDoc;
    private SppdRerouteDao sppdRerouteDao;
    private java.io.File fileUpload;
    private String dinas;

    private String fileUploadContentType;
    private String fileUploadFileName;

    public String getDinas() {
        return dinas;
    }

    public void setDinas(String dinas) {
        this.dinas = dinas;
    }

    public SppdRerouteDao getSppdRerouteDao() {
        return sppdRerouteDao;
    }

    public void setSppdRerouteDao(SppdRerouteDao sppdRerouteDao) {
        this.sppdRerouteDao = sppdRerouteDao;
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

    public SppdDoc getSppdDoc() {
        return sppdDoc;
    }

    public void setSppdDoc(SppdDoc sppdDoc) {
        this.sppdDoc = sppdDoc;
    }

    public java.io.File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(java.io.File fileUpload) {
        this.fileUpload = fileUpload;
    }

    private List<Sppd> listComboSppd = new ArrayList<Sppd>();

    public List<Sppd> getListComboSppd() {
        return listComboSppd;
    }

    public SppdPerson getSppdPerson() {
        return sppdPerson;
    }

    public void setSppdPerson(SppdPerson sppdPerson) {
        this.sppdPerson = sppdPerson;
    }

    public void setListComboSppd(List<Sppd> listComboSppd) {
        this.listComboSppd = listComboSppd;
    }

    public SppdBo getSppdBoProxy() {
        return sppdBoProxy;
    }

    public void setSppdBoProxy(SppdBo sppdBoProxy) {
        this.sppdBoProxy = sppdBoProxy;
    }

    public Sppd getSppd() {
        return sppd;
    }

    public void setSppd(Sppd sppd) {
        this.sppd = sppd;
    }

    private List<Sppd> initComboSppd;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SppdAction.logger = logger;
    }


    public List<Sppd> getInitComboSppd() {
        return initComboSppd;
    }

    public void setInitComboSppd(List<Sppd> initComboSppd) {
        this.initComboSppd = initComboSppd;
    }

    public Sppd init(String kode){
        logger.info("[SppdAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Sppd> listOfResult = (List<Sppd>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Sppd sppd: listOfResult) {
                    if(kode.equalsIgnoreCase(sppd.getSppdId())){
                        setSppd(sppd);
                        break;
                    }
                }
            } else {
                setSppd(new Sppd());
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        return getSppd();
    }

    public List<SppdPerson> initPerson(String kode){
        logger.info("[SppdAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdPerson> listOfResult = (List<SppdPerson>) session.getAttribute("listOfResultPerson");
        List<SppdPerson> listComboSppdPerson = new ArrayList();

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SppdPerson sppdPerson: listOfResult) {
                    if(kode.equalsIgnoreCase(sppdPerson.getSppdId())){
                        listComboSppdPerson.add(sppdPerson);
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        return listComboSppdPerson;
    }

    @Override
    public String add() {
        logger.info("[SppdAction.add] start process >>>");
        Sppd addSppd = new Sppd();
        setSppd(addSppd);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultSppdPerson");

        logger.info("[SppdAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SppdAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Sppd editSppd = new Sppd();

        if(itemFlag != null){
            try {
                editSppd = init(itemId);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getSppdByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SppdAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SppdAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSppd != null) {
                setSppd(editSppd);
            } else {
                editSppd.setFlag(itemFlag);
                editSppd.setSppdId(itemId);
                setSppd(editSppd);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSppd.setSppdId(itemId);
            editSppd.setFlag(getFlag());
            setSppd(editSppd);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SppdAction.edit] end process >>>");
        searchAnggota(editSppd.getSppdId());
        searchAnggotaApproved(editSppd.getSppdId());
        searchDataReroute(editSppd.getSppdId());
        searchDocument(editSppd.getSppdId());
        return "init_edit";
    }

    public List<SppdPerson> searchAnggota(String SppdId) {
        logger.info("[SppdAction.search] start process >>>");


        List<SppdPerson> listOfsearchSppd = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            listOfsearchSppd = sppdBo.getComboSppdPerson(SppdId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        return listOfsearchSppd;
    }

    public List<SppdPerson> searchAnggotaApproved(String SppdId) {
        logger.info("[SppdAction.search] start process >>>");


        List<SppdPerson> listOfsearchSppd = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            listOfsearchSppd = sppdBo.getComboSppdPersonApproved(SppdId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("sppdAnggota");
        session.setAttribute("sppdAnggota", listOfsearchSppd);
        return listOfsearchSppd;
    }

    public List<SppdReroute> searchDataReroute(String SppdId) {
        logger.info("[SppdAction.search] start process >>>");


        List<SppdReroute> sppdRerouteList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdRerouteList = sppdBo.getComboSppdReroute(SppdId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultSppdReroute");
        session.setAttribute("listOfResultSppdReroute", sppdRerouteList);
        return sppdRerouteList;
    }

    public List<SppdReroute> searchReroute(String SppdId) {
        logger.info("[SppdAction.search] start process >>>");


        List<SppdReroute> sppdRerouteList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdRerouteList = sppdBo.getComboSppdReroute(SppdId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSppdPersonReroute");
        session.setAttribute("listSppdPersonReroute", sppdRerouteList);
        return sppdRerouteList;
    }

    /*public String viewDoc(){
        logger.info("[BiayaPengobatanAction.viewDoc] start process >>>");
        String id = getId();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdDoc> sppdDocList = (List<SppdDoc>) session.getAttribute("listOfResultBuktiPengobatan");

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
    }*/

    public List<SppdDoc> searchDoc(String SppdId) {
        logger.info("[SppdAction.search] start process >>>");

        List<SppdDoc> sppdDocList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdDocList = sppdBo.getComboSppdDoc(SppdId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSppdPersonDoc");
        session.setAttribute("listSppdPersonDoc", sppdDocList);
        return sppdDocList;
    }

    public List<SppdReroute> searchEditReroute(String sppdId, String id) {
        logger.info("[SppdAction.search] start process >>>");


        List<SppdReroute> sppdRerouteList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdRerouteList = sppdBo.getComboSppdReroute2(sppdId, id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return sppdRerouteList;
    }

    public List<SppdPerson> searchAnggota2(String SppdId, String personId) {
        logger.info("[SppdAction.search] start process >>>");


        List<SppdPerson> sppdPersonList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdPersonList = sppdBo.getComboSppdPerson(SppdId, personId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSppdPersonAnggota");
        session.setAttribute("listSppdPersonAnggota", sppdPersonList);
        return sppdPersonList;
    }

    public List<SppdTanggal> searchSppdTanggal(String SppdPersonId) {
        logger.info("[SppdAction.searchTanggal] start process >>>");

        List<SppdTanggal> sppdTanggalList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdTanggalList = sppdBo.getSppdTanggal(SppdPersonId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSppdTanggal");
        session.setAttribute("listSppdTanggal", sppdTanggalList);
        return sppdTanggalList;
    }

    public void searchAnggotaAdd(String nipOld, String pengganti) {
        List<SppdPerson> listOfsearchSppdPerson = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<SppdPerson> listHasil =  new ArrayList();
        listOfsearchSppdPerson = (List<SppdPerson>) session.getAttribute("listSppdPersonAnggota");
        if(nipOld != null && !"".equalsIgnoreCase(nipOld)){
            if(listOfsearchSppdPerson != null){
                for (SppdPerson sppdPerson: listOfsearchSppdPerson) {
                    if(nipOld.equalsIgnoreCase(sppdPerson.getPersonId())){
                        sppdPerson.setPejabatSementara(pengganti);

                        listHasil.add(sppdPerson);
                    }else{
                        listHasil.add(sppdPerson);
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        session.removeAttribute("listSppdPersonAnggota");
        session.setAttribute("listSppdPersonAnggota", listHasil);
    }

    public String approve() {
        logger.info("[SppdAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        List<SppdPerson> listSppdPersonAnggota = null;

        Sppd editSppd = new Sppd();
        SppdPerson editSppdPerson = new SppdPerson();

        if(itemFlag != null){
            try {
                editSppd = init(itemId);
                listSppdPersonAnggota = searchAnggota(itemId);

                //listSppdPersonAnggota = initPerson(itemId);
                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listSppdPersonAnggota");
                session.setAttribute("listSppdPersonAnggota", listSppdPersonAnggota);

            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getSppdByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SppdAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SppdAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSppd != null) {
                setSppd(editSppd);
            } else {
                editSppd.setFlag(itemFlag);
                editSppd.setSppdId(itemId);
                setSppd(editSppd);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSppd.setSppdId(itemId);
            editSppd.setFlag(getFlag());
            setSppd(editSppd);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SppdAction.edit] end process >>>");
        return "init_edit";
    }

    public List<TrainingPerson> cekIdTraining(String trainingId){
        List<TrainingPerson> hasilTraining = null;
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");

            hasilTraining = sppdBo.cekIdTrainingSys(trainingId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return hasilTraining;
    }

    public List<Sppd> approveAtasan(String sppdId) {
        logger.info("[SppdAction.approveAtasan] start process >>>");
        String itemId = sppdId;
        List<SppdPerson> listSppdPersonAnggota = null;
        List<Sppd> sppdList = new ArrayList<Sppd>();

        Sppd editSppd = new Sppd();

        SppdPerson editSppdPerson = new SppdPerson();

        editSppd = init(itemId);
        sppdList.add(editSppd);

        if(editSppd != null) {
            setSppd(editSppd);
        } else {
            editSppd.setSppdId(itemId);
            setSppd(editSppd);
            addActionError("Error, Unable to find data with id = " + itemId);
        }
        setAddOrEdit(true);
        logger.info("[SppdAction.approveAtasan] end process >>>");
        return sppdList;
    }

    //mengecek apakah atasan sudah approve semua anggota SPPD
    public boolean cekApproveAtasan(String sppdId) {
        logger.info("[SppdAction.cekApproveAtasan] start process >>>");
        //default sudah approve true
        Boolean hasil = true;
        List<SppdPerson> listSppdPersonAnggota = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        hasil = sppdBo.cekApproveAtasanSys(sppdId);

        logger.info("[SppdAction.cekApproveAtasan] end process >>>");
        return hasil;
    }

    public void addAnggota(String sppdId, String nip, String name, String unit, String position, String divisi, String tanggalBerangkat, String tanggalKembali,
                           String berangkatDari, String berangkatKe, String berangkatVia, String kembaliVia, String penginapan, String dinas){
        SppdPerson sppdPerson = new SppdPerson();

        sppdPerson.setSppdId(sppdId);
        sppdPerson.setPersonId(nip);
        sppdPerson.setPersonName(name);
        sppdPerson.setBranchId(unit);
        sppdPerson.setPositionId(position);
        sppdPerson.setDivisiId(divisi);
        sppdPerson.setStTanggalBerangkat(tanggalBerangkat);
        sppdPerson.setStTanggalKembali(tanggalKembali);
        sppdPerson.setBerangkatDari(berangkatDari);
        sppdPerson.setTujuanKe(berangkatKe);
        sppdPerson.setBerangkatVia(berangkatVia);
        sppdPerson.setKembaliVia(kembaliVia);
        sppdPerson.setPenginapan(penginapan);
        sppdPerson.setDinas(dinas);

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        sppdPerson.setCreatedWho(userLogin);
        sppdPerson.setLastUpdate(updateTime);
        sppdPerson.setCreatedDate(updateTime);
        sppdPerson.setLastUpdateWho(userLogin);
        sppdPerson.setAction("C");
        sppdPerson.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        sppdBo.addAnggotaSys(sppdPerson);
    }

    public void deleteAnggota(String sppdId, String nip){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        sppdBo.deleteAnggotaSys(sppdId, nip);
    }

    public void deleteSppd(String sppdId){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        sppdBo.deleteSppdSys(sppdId);
    }

    public String approveSdm() {
        logger.info("[SppdAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        List<SppdPerson> listSppdPersonAnggota = null;

        Sppd editSppd = new Sppd();
        SppdPerson editSppdPerson = new SppdPerson();

        if(itemFlag != null){
            try {
                editSppd = init(itemId);
                listSppdPersonAnggota = searchAnggota(itemId);

                //listSppdPersonAnggota = initPerson(itemId);
                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listSppdPersonAnggota");
                session.setAttribute("listSppdPersonAnggota", listSppdPersonAnggota);

            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getSppdByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SppdAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SppdAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSppd != null) {
                setSppd(editSppd);
            } else {
                editSppd.setFlag(itemFlag);
                editSppd.setSppdId(itemId);
                setSppd(editSppd);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSppd.setSppdId(itemId);
            editSppd.setFlag(getFlag());
            setSppd(editSppd);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SppdAction.edit] end process >>>");
        return "init_edit_sdm";
    }

    @Override
    public String delete() {
        logger.info("[SppdAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Sppd deleteSppd = new Sppd();

        if (itemFlag != null ) {

            try {
                deleteSppd = init(itemId);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SppdAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SppdAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSppd != null) {
                setSppd(deleteSppd);

            } else {
                deleteSppd.setSppdId(itemId);
                deleteSppd.setFlag(itemFlag);
                setSppd(deleteSppd);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSppd.setSppdId(itemId);
            deleteSppd.setFlag(itemFlag);
            setSppd(deleteSppd);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SppdAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[saveAdd] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        try {
            Sppd sppd = getSppd();

            SppdPerson sppdPerson = getSppdPerson();

            if (sppd.getStTanggalSppdBerangkat() != null && !"".equalsIgnoreCase(sppdPerson.getStTanggalBerangkat())) {
                sppd.setTanggalSppdBerangkat(CommonUtil.convertStringToDate(sppd.getStTanggalSppdBerangkat()));
                sppd.setTanggalSppdKembali(CommonUtil.convertStringToDate(sppd.getStTanggalSppdKembali()));
            }

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sppd.setCreatedWho(userLogin);
            sppd.setLastUpdate(updateTime);
            sppd.setCreatedDate(updateTime);
            sppd.setLastUpdateWho(userLogin);
            sppd.setAction("C");
            sppd.setFlag("Y");

            sppdPerson.setDivisiId(sppd.getDivisiId());
            sppdPerson.setBranchId(sppd.getBranchId());
            sppdPerson.setTanggalBerangkat(sppd.getTanggalSppdBerangkat());
            sppdPerson.setTanggalKembali(sppd.getTanggalSppdKembali());

            notifikasiList = sppdBoProxy.saveAdd(sppd, sppdPerson);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        for (Notifikasi notifikasi : notifikasiList){
            notifikasiBo.sendNotif(notifikasi);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    // cek apakah user sudah ada sppd atau blm(add SPPD)
    public String cekAvailableSppd(String nip, String tanggal1, String tanggal2){
        String hasil = "";
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");

            hasil = sppdBo.cekAvailableSppdSys(nip, tanggal1, tanggal2);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return hasil;
    }

    // cek apakah user sudah ada sppd atau blm(edit SPPD)
    public String cekAvailableSppdEdit(String nip, String tanggal1, String tanggal2, String sppdId){
        String hasil = "";
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");

            hasil = sppdBo.cekAvailableSppdSys(nip, tanggal1, tanggal2, sppdId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return hasil;
    }

    public String saveApprove(String SppdId, String personId, String statusApprove, String who, String nipPengganti,
                              String noteTransportasi, String berangkatVia, String kembaliVia, String penginapan){
        logger.info("[SppdAction.saveEdit] start process >>>");
        try {

            SppdPerson sppdPerson = new SppdPerson();
            sppdPerson.setPejabatSementara(nipPengganti);
            sppdPerson.setSppdId(SppdId);
            sppdPerson.setSppdPersonId(personId);
            if(who.equals("atasan")){
                if(statusApprove.equals("Y")){
                    sppdPerson.setApprovalFlag(statusApprove);
                    if(!noteTransportasi.equals("")){
                        sppdPerson.setNoteAtasanTransport(noteTransportasi);
                    }
                }else{
                    sppdPerson.setApprovalFlag("N");
                    sppdPerson.setNotApprovalNote(statusApprove);
                    if(!noteTransportasi.equals("")){
                        sppdPerson.setNoteSdmTransport(noteTransportasi);
                    }
                }
            }else{
                if(statusApprove.equals("Y")){
                    sppdPerson.setApprovalSdmFlag(statusApprove);
                    sppdPerson.setBerangkatVia(berangkatVia);
                    sppdPerson.setKembaliVia(kembaliVia);
                    sppdPerson.setPenginapan(penginapan);
                    if(!noteTransportasi.equals("")){
                        sppdPerson.setNoteAtasanTransport(noteTransportasi);
                    }
                }else{
                    sppdPerson.setApprovalSdmFlag("N");
                    sppdPerson.setNotApprovalSdmNote(statusApprove);
                    sppdPerson.setBerangkatVia(berangkatVia);
                    sppdPerson.setKembaliVia(kembaliVia);
                    sppdPerson.setNoteSdmTransport(noteTransportasi);
                }
            }

            sppdPerson.setTmpApprove(who);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            sppdPerson.setUserIdActive(CommonUtil.userIdLogin());
            sppdPerson.setUserNameActive(CommonUtil.userLogin());
            sppdPerson.setLastUpdateWho(userLogin);
            sppdPerson.setLastUpdate(updateTime);
            sppdPerson.setAction("U");
            sppdPerson.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdBo.saveApprove(sppdPerson);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.saveEdit");
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

    public String saveEditSdm(){
        logger.info("[SppdAction.saveEdit] start process >>>");
        try {

            Sppd editSppd = getSppd();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSppd.setLastUpdateWho(userLogin);
            editSppd.setLastUpdate(updateTime);
            editSppd.setAction("U");
            editSppd.setFlag("Y");

            sppdBoProxy.saveEditSdm(editSppd);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.saveEdit");
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

    public String saveDelete(){
        logger.info("[SppdAction.saveDelete] start process >>>");
        try {

            Sppd deleteSppd = getSppd();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSppd.setLastUpdate(updateTime);
            deleteSppd.setLastUpdateWho(userLogin);
            deleteSppd.setAction("U");
            deleteSppd.setFlag("N");

            sppdBoProxy.saveDelete(deleteSppd);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SppdAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SppdAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public List<SppdPerson> searchSppdPerson(String personId) {
        logger.info("[SppdAction.search] start process >>>");

        List<SppdPerson> sppdPersons = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");

            sppdPersons = sppdBo.getComboSppdPerson2(personId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return sppdPersons;
    }

    public List<SppdPerson> searchSppdPerson() {
        logger.info("[KeluargaAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdPerson> listOfsearchSppdPerson = (List<SppdPerson>) session.getAttribute("listOfResultSppdPerson");

        return listOfsearchSppdPerson;
    }

    public List<SppdPerson> searchSppdPersonEdit(String nip) {
        logger.info("[KeluargaAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdPerson> listOfsearchSppdPerson = (List<SppdPerson>) session.getAttribute("listOfResultSppdPerson");
        List<SppdPerson> listHasil =  new ArrayList();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfsearchSppdPerson != null){
                for (SppdPerson sppdPerson: listOfsearchSppdPerson) {
                    if(nip.equalsIgnoreCase(sppdPerson.getPersonId())){
                        listHasil.add(sppdPerson);
                        break;
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        return listHasil;
    }

    public String saveEdit(){
        try {
            Sppd sppd = getSppd();

            if (sppd.getStTanggalSppdBerangkat() != null && !"".equalsIgnoreCase(sppd.getStTanggalSppdBerangkat())) {
                sppd.setTanggalSppdBerangkat(CommonUtil.convertStringToDate(sppd.getStTanggalSppdBerangkat()));
                sppd.setTanggalSppdKembali(CommonUtil.convertStringToDate(sppd.getStTanggalSppdKembali()));
            }

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sppd.setCreatedWho(userLogin);
            sppd.setLastUpdate(updateTime);
            sppd.setCreatedDate(updateTime);
            sppd.setLastUpdateWho(userLogin);
            sppd.setAction("U");
            sppd.setFlag("Y");

            sppdBoProxy.saveEdit(sppd);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SppdAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving Edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SppdAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public List<SppdPerson> saveSppdPersonDelete(String nip) {
        logger.info("[KeluargaAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdPerson> listOfsearchSppdPerson = (List<SppdPerson>) session.getAttribute("listOfResultSppdPerson");
        List<SppdPerson> listHasil =  new ArrayList();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfsearchSppdPerson != null){
                for (SppdPerson sppdPerson: listOfsearchSppdPerson) {
                    if(nip.equalsIgnoreCase(sppdPerson.getPersonId())){

                    }else{
                        listHasil.add(sppdPerson);
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        session.removeAttribute("listOfResultSppdPerson");
        session.setAttribute("listOfResultSppdPerson", listHasil);
        return listHasil;
    }

    public List<SppdPerson> saveEditPerson(String nipOld, String nip, String personName, String branchId, String branchName, String positionId,
                                           String positionName, String divisiId, String divisiName, String kotaDari, String kotaTujuan,
                                           String berangkatViaAnggota, String pulangViaAnggota, String penginapanAnggota, String kotaDariNama,
                                           String kotaTujuanNama, String tanggalBerangkat, String tanggalKembali) {

        logger.info("[KeluargaAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdPerson> listOfsearchSppdPerson = (List<SppdPerson>) session.getAttribute("listOfResultSppdPerson");
        List<SppdPerson> listHasil =  new ArrayList();

        if(nipOld != null && !"".equalsIgnoreCase(nipOld)){
            if(listOfsearchSppdPerson != null){
                for (SppdPerson sppdPerson: listOfsearchSppdPerson) {
                    if(nipOld.equalsIgnoreCase(sppdPerson.getPersonId())){
                        sppdPerson.setPersonId(nip);
                        sppdPerson.setPersonName(personName);
                        sppdPerson.setBranchName(branchId);
                        sppdPerson.setBranchName(branchName);
                        sppdPerson.setPositionId(positionId);
                        sppdPerson.setPositionName(positionName);
                        sppdPerson.setDivisiId(divisiId);
                        sppdPerson.setDivisiName(divisiName);

                        sppdPerson.setBerangkatDari(kotaDari);
                        sppdPerson.setTujuanKe(kotaTujuan);
                        sppdPerson.setBerangkatVia(berangkatViaAnggota);
                        sppdPerson.setKembaliVia(pulangViaAnggota);
                        sppdPerson.setPenginapan(penginapanAnggota);
                        sppdPerson.setBerangkatDariNama(kotaDariNama);
                        sppdPerson.setTujuanKeNama(kotaTujuanNama);
                        sppdPerson.setStTanggalBerangkat(tanggalBerangkat);
                        sppdPerson.setStTanggalKembali(tanggalKembali);

                        listHasil.add(sppdPerson);
                    }else{
                        listHasil.add(sppdPerson);
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        return listHasil;
    }

    public void saveAdd(String nip, String personName, String branchId, String branchName, String positionId, String positionName,
                        String divisiId, String divisiName, String kotaDari, String kotaTujuan, String berangkatViaAnggota,
                        String pulangViaAnggota, String penginapanAnggota, String kotaDariNama, String kotaTujuanNama,
                        String tanggalBerangkat, String tanggalKembali){
        logger.info("[SppdAction.saveAdd] start process >>>");
        List<SppdPerson> listComboSppdPerson = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            SppdPerson sppdPerson = new SppdPerson();

            sppdPerson.setPersonId(nip);
            sppdPerson.setPersonName(personName);
            sppdPerson.setBranchId(branchId);
            sppdPerson.setBranchName(branchName);
            sppdPerson.setPositionId(positionId);
            sppdPerson.setPositionName(positionName);
            sppdPerson.setDivisiId(divisiId);
            sppdPerson.setDivisiName(divisiName);
            sppdPerson.setBerangkatDari(kotaDari);
            sppdPerson.setTujuanKe(kotaTujuan);
            sppdPerson.setBerangkatVia(berangkatViaAnggota);
            sppdPerson.setKembaliVia(pulangViaAnggota);
            sppdPerson.setPenginapan(penginapanAnggota);
            sppdPerson.setBerangkatDariNama(kotaDariNama);
            sppdPerson.setTujuanKeNama(kotaTujuanNama);
            sppdPerson.setStTanggalBerangkat(tanggalBerangkat);
            sppdPerson.setStTanggalKembali(tanggalKembali);

            //HttpSession session = ServletActionContext.getRequest().getSession();
            listComboSppdPerson = (List<SppdPerson>) session.getAttribute("listOfResultSppdPerson");
            if(listComboSppdPerson != null){
                listComboSppdPerson.add(sppdPerson);
            }else{
                listComboSppdPerson = new ArrayList();
                listComboSppdPerson.add(sppdPerson);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("listOfResultSppdPerson", listComboSppdPerson);
    }

    //-----------------------------------------Sppd Upload Document Menejemen-------------------------------------------------------//
    public List<SppdDoc> searchDocument(String SppdId) {
        logger.info("[SppdAction.search] start process >>>");


        List<SppdDoc> sppdDocList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdDocList = sppdBo.getComboSppdDocument(SppdId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSppdDocument");
        session.setAttribute("listSppdDocument", sppdDocList);

        return sppdDocList;
    }

    public String viewDoc(String id){
        logger.info("[SppdAction.viewDoc] start process >>>");
        //String id = getId();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdDoc> listSppdDocument = (List<SppdDoc>) session.getAttribute("listSppdDocument");

        boolean isImg = false;
        String link = "";
        for (SppdDoc listData : listSppdDocument){
            if (id.equalsIgnoreCase(listData.getDocSppdId())){
                if ("IMG".equalsIgnoreCase(listData.getFileType())){
                    listData.setPath("/hris/pages/upload/file/sppd/"+listData.getFileUploadDoc());
                    isImg = true;
                }
                if ("PDF".equalsIgnoreCase(listData.getFileType())){
                    listData.setPath("/hris/pages/upload/file/sppd/"+listData.getFileUploadDoc());
                }
                setSppdDoc(listData);
                link = "/hris/pages/upload/file/sppd/"+listData.getFileUploadDoc();
            }
        }

        if (isImg){
            return link;
        } else {
            return link;
        }
    }

    public String addSppdDoc(){
        try {
            SppdDoc sppdDoc = getSppdDoc();
            String idDoc = sppdBoProxy.getNextId();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            if (this.fileUpload!=null) {
                String fileDocName = idDoc +"_"+ this.fileUploadFileName;
                String fileContentType = this.fileUploadContentType;
                String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_SPPD_FILE;
                java.io.File fileToCreate = new java.io.File(filePath, fileDocName);

                //create file to save to folder '/upload'
                byte[] contentFile = null;
                try {
                    FileUtils.copyFile(this.fileUpload, fileToCreate);
                    contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                } catch (IOException e) {
                    Long logId = null;
                    try {
                        logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                    } catch (GeneralBOException e1) {
                        logger.error("[UserAction.save] Error when saving error,", e1);
                    }
                    logger.error("[UserAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                    return ERROR;
                }

                if (contentFile!=null) {
                    sppdDoc.setFileUploadDoc(fileDocName);
                    if ("image/jpeg".equalsIgnoreCase(fileContentType)) {
                        sppdDoc.setFileType("IMG");
                    }else if("application/pdf".equalsIgnoreCase(fileContentType)) {
                        sppdDoc.setFileType("PDF");
                    }
                }
            }
            sppdDoc.setNote(sppdDoc.getNote());
            sppdDoc.setDocSppdId(idDoc);
            sppdDoc.setCreatedWho(userLogin);
            sppdDoc.setLastUpdate(updateTime);
            sppdDoc.setCreatedDate(updateTime);
            sppdDoc.setLastUpdateWho(userLogin);
            sppdDoc.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");

            sppdDoc.setAction("C");
            sppdBo.addSppdDoc(sppdDoc);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
        return "init_edit";
    }

    public List<SppdDoc> searchSppdDocumentEdit(String id) {
        logger.info("[Sppd.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdDoc> listSppdDocument = (List<SppdDoc>) session.getAttribute("listSppdDocument");
        List<SppdDoc> sppdDocList =  new ArrayList();

        if(id != null && !"".equalsIgnoreCase(id)){
            if(listSppdDocument != null){
                for (SppdDoc sppdDoc: listSppdDocument) {
                    if(id.equalsIgnoreCase(sppdDoc.getDocSppdId())){
                        sppdDocList.add(sppdDoc);
                        break;
                    }
                }
            }
            logger.info("[SppdAction.init] end process >>>");
        }
        return sppdDocList;
    }

    public String saveEditDoc(){
        try {
            SppdDoc sppdDoc = getSppdDoc();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            if (this.fileUpload!=null) {
                String fileDocName = sppdDoc.getSppdId()+"_"+ this.fileUploadFileName;
                String fileContentType = this.fileUploadContentType;

                String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_SPPD_FILE;
                java.io.File fileToCreate = new java.io.File(filePath, fileDocName);

                //create file to save to folder '/upload'
                byte[] contentFile = null;
                try {
                    FileUtils.copyFile(this.fileUpload, fileToCreate);
                    contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                } catch (IOException e) {
                    Long logId = null;
                    try {
                        logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                    } catch (GeneralBOException e1) {
                        logger.error("[UserAction.save] Error when saving error,", e1);
                    }
                    logger.error("[UserAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                    return ERROR;
                }

                if (contentFile!=null) {
                    sppdDoc.setFileUploadDoc(fileDocName);
                    if ("image/jpeg".equalsIgnoreCase(fileContentType)) {
                        sppdDoc.setFileType("IMG");
                    }else if("application/pdf".equalsIgnoreCase(fileContentType)) {
                        sppdDoc.setFileType("PDF");
                    }
                }
            }

            sppdDoc.setSppdId(sppdDoc.getSppdId());
            sppdDoc.setNote(sppdDoc.getNote());

            sppdDoc.setCreatedWho(userLogin);
            sppdDoc.setLastUpdate(updateTime);
            sppdDoc.setCreatedDate(updateTime);
            sppdDoc.setLastUpdateWho(userLogin);
            sppdDoc.setAction("U");
            sppdDoc.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");

            sppdBo.saveEdit(sppdDoc);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
        return "init_edit";
    }

    public String saveDeleteDoc(){
        try {
            SppdDoc sppdDoc = getSppdDoc();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sppdDoc.setLastUpdate(updateTime);
            sppdDoc.setLastUpdateWho(userLogin);
            sppdDoc.setAction("U");
            sppdDoc.setFlag("N");

            sppdBoProxy.saveEdit(sppdDoc);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
        return "init_edit";
    }

    public Boolean cekBiayaLokal(String lokalId){
        boolean hasil = false ;
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            hasil = sppdBo.cekBiayaLokalSys(lokalId);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[SppdAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
        return hasil;
    }

    public Boolean cekReroute(String personId, String tanggal){
        Boolean hasil = false;
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            hasil = sppdBo.cekRerouteSys(personId, tanggal);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[SppdAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
        return hasil;
    }

    //mengecek tidak boleh ada 2 reroute untuk 1 nip dalam 1 SPPD
    public Boolean cekNipReroute(String sppdPersonId){
        Boolean hasil = false;
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            hasil = sppdBo.cekNipRerouteSys(sppdPersonId);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[SppdAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
        return hasil;
    }

    public String cekTanggalReroute(String tanggalAwal, String tanggalAkhir, String nip){
        String hasil = "-";
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            hasil = sppdBo.cekTanggalRerouteSys(tanggalAwal, tanggalAkhir, nip);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "sppdBO.cekTanggalReroute");
            } catch (GeneralBOException e1) {
                logger.error("[sppdAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[SppdAction.cekTanggalReroute] Error when adding item ," + "[" + logId + "] Found problem when cekTanggalReroute, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when cekTanggalReroute, please inform to your admin.\n" + e.getMessage());
        }
        return hasil;
    }

    public String printReportSppd() {
        logger.info("[ReportAction.printReportSPPD] start process >>>");

        Sppd sppd = getSppd();
        String id = getId();
        String dinas = getDinas();
        String reportHasil = "";
        int jumlahHari = 0 ;

        if (id != null) {
            List<SppdPerson> listData = new ArrayList();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");

            if(dinas.equalsIgnoreCase("DN")){
                listData = sppdBo.getReportSppd(id, dinas);
                JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);
                reportHasil = "success_print_report_sppd";
                reportParams.put("itemDataSource", itemData);
            }else{
                jumlahHari = sppdBo.jumlahHari(id);
                reportParams.put("jumlahHari", jumlahHari);
                reportHasil = "success_print_view_report_sppd_LN";
            }

            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("titleReport", "Report Perjalanan Dinas");
            reportParams.put("noSppd", id);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "printReportSPPD");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportSPPD] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportSPPD] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
                return "failure";

            }

        } else {
            logger.error("[ReportAction.printReportKPIUnit] Error when print report SPPD, Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
            return "failure_print_report_kpi_unit";
        }

        logger.info("[ReportAction.printReportSPPD] end process <<<");
        return reportHasil;
    }

    public String printViewReportSppd() {
        logger.info("[ReportAction.printReportKPIUnit] start process >>>");
        //get data musim tanam
        Sppd sppd = getSppd();
        String id = getId();
        String tujuan = "";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        tujuan = sppdBo.getTujuanSppd(id);

        if (id != null) {
            List<SppdPerson> listAnggota = new ArrayList<>();
            listAnggota = sppdBo.anggotaSppd(id);
            JRBeanCollectionDataSource itemAnggota = new JRBeanCollectionDataSource(listAnggota);

            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("titleReport", "Surat Perintah Perjalanan Dinas");
            reportParams.put("tujuanSppd", tujuan);
            reportParams.put("noSppd", id);
            reportParams.put("itemAnggota", itemAnggota);


            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "printReportSuratSppd");
                } catch (GeneralBOException e1) {
                    logger.error("[SppdAction.printReportSuratSppd] Error when downloading ,", e1);
                }
                logger.error("[SppdAction.printReportSuratSppd] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
                return "failure_print_report_kpi_unit";

            }

        } else {
            logger.error("[SppdAction.printReportSuratSppd] Error when print report kpi unit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
            return "";
        }

        logger.info("[SppdAction.printReportSuratSppd] end process <<<");

        return "success_print_view_report_sppd";
    }

    //-----------------------------------------end Sppd Reroute Menejemen---------------------------------------//


    //-----------------------------------------Sppd Reroute Menejemen-------------------------------------------------------//
    public List<SppdReroute> searchSppdReroute() {
        logger.info("[SppdRerouteAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdReroute> sppdRerouteList = (List<SppdReroute>) session.getAttribute("listOfResultSppdReroute");

        return sppdRerouteList;
    }

    public String resetReroute() {
        logger.info("[SppdAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultSppdReroute");
        logger.info("[SppdAction.resetReroute] end process >>>");
        return INPUT;
    }

    public List<SppdReroute> searchSppdRerouteEdit(String nip) {
        logger.info("[KeluargaAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdReroute> listOfsearchSppdPerson = (List<SppdReroute>) session.getAttribute("listOfResultSppdReroute");
        List<SppdReroute> listHasil =  new ArrayList();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfsearchSppdPerson != null){
                for (SppdReroute sppdReroute: listOfsearchSppdPerson) {
                    if(nip.equalsIgnoreCase(sppdReroute.getSppdPersonId())){
                        listHasil.add(sppdReroute);
                        break;
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        return listHasil;
    }

    public void saveEditReroute(String id, String sppdPersonId, String personName, String rerouteDari, String rerouteKe, String rerouteDariName,
                                String rerouteKeName, String tanggalDari, String tanggalKe, String keterangan, String berangkatVia,
                                BigDecimal tiketPergi, BigDecimal tiketKembali, BigDecimal biayaTambahan, BigDecimal biayaLokal,
                                BigDecimal biayaTujuan, BigDecimal biayaKembali) {
        try {
            SppdReroute sppdReroute = new SppdReroute();
            sppdReroute.setSppdRerouteId(id);
            sppdReroute.setSppdPersonId(sppdPersonId);
            sppdReroute.setSppdPersonName(personName);
            sppdReroute.setRerouteDari(rerouteDari);
            sppdReroute.setRerouteKe(rerouteKe);
            sppdReroute.setRerouteDariName(rerouteDariName);
            sppdReroute.setRerouteKeName(rerouteKeName);
            sppdReroute.setKeterangan(keterangan);
            sppdReroute.setBerangkatVia(berangkatVia);

            sppdReroute.setTanggalRerouteDari(CommonUtil.convertStringToDate(tanggalDari));
            sppdReroute.setTanggalRerouteKe(CommonUtil.convertStringToDate(tanggalKe));

            sppdReroute.setTiketPergi(tiketPergi);
            sppdReroute.setTiketKembali(tiketKembali);
            sppdReroute.setBiayaTambahan(biayaTambahan);
            sppdReroute.setBiayaLokal(biayaLokal);
            sppdReroute.setBiayaTujuan(biayaTujuan);
            sppdReroute.setBiayaKembali(biayaKembali);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sppdReroute.setCreatedWho(userLogin);
            sppdReroute.setLastUpdate(updateTime);
            sppdReroute.setCreatedDate(updateTime);
            sppdReroute.setLastUpdateWho(userLogin);
            sppdReroute.setAction("U");
            sppdReroute.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdBo.saveEditReroute(sppdReroute);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    public void saveAddReroute(String sppdId, String sppdPersonId, String personName, String rerouteDari, String rerouteKe, String rerouteDariName,
                               String rerouteKeName, String tanggalDari, String tanggalKe, String berangkatVia, BigDecimal tiketPergi, BigDecimal tiketKembali,
                               BigDecimal biayaTambahan, BigDecimal biayaLokal, BigDecimal biayaTujuan, BigDecimal biayaKembali, String keterangan) {
        try {
            SppdReroute sppdReroute = new SppdReroute();
            sppdReroute.setSppdRerouteId(id);
            sppdReroute.setSppdId(sppdId);
            sppdReroute.setSppdPersonId(sppdPersonId);
            sppdReroute.setSppdPersonName(personName);
            sppdReroute.setRerouteDari(rerouteDari);
            sppdReroute.setRerouteKe(rerouteKe);
            sppdReroute.setRerouteDariName(rerouteDariName);
            sppdReroute.setRerouteKeName(rerouteKeName);
            sppdReroute.setBerangkatVia(berangkatVia);
            sppdReroute.setKeterangan(keterangan);
            sppdReroute.setTanggalRerouteDari(CommonUtil.convertStringToDate(tanggalDari));
            sppdReroute.setTanggalRerouteKe(CommonUtil.convertStringToDate(tanggalKe));

            sppdReroute.setTiketPergi(tiketPergi);
            sppdReroute.setTiketKembali(tiketKembali);
            sppdReroute.setBiayaTambahan(biayaTambahan);
            sppdReroute.setBiayaLokal(biayaLokal);
            sppdReroute.setBiayaTujuan(biayaTujuan);
            sppdReroute.setBiayaKembali(biayaKembali);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sppdReroute.setCreatedWho(userLogin);
            sppdReroute.setLastUpdate(updateTime);
            sppdReroute.setCreatedDate(updateTime);
            sppdReroute.setLastUpdateWho(userLogin);
            sppdReroute.setAction("C");
            sppdReroute.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdBo.saveAddReroute(sppdReroute);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    public void saveDeleteReroute(String id) {
        try {
            SppdReroute sppdReroute = new SppdReroute();
            sppdReroute.setSppdRerouteId(id);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sppdReroute.setCreatedWho(userLogin);
            sppdReroute.setLastUpdate(updateTime);
            sppdReroute.setCreatedDate(updateTime);
            sppdReroute.setLastUpdateWho(userLogin);
            sppdReroute.setAction("D");
            sppdReroute.setFlag("N");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdBo.saveDeleteReroute(sppdReroute);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }
    }

    public List<SppdReroute> saveSppdRerouteDelete(String nip) {
        logger.info("[SpddAction.reroute] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdReroute> sppdRerouteList = (List<SppdReroute>) session.getAttribute("listOfResultSppdReroute");
        List<SppdReroute> listHasil =  new ArrayList();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(sppdRerouteList != null){
                for (SppdReroute sppdReroute: sppdRerouteList) {
                    if(!nip.equalsIgnoreCase(sppdReroute.getSppdPersonId())){
                        listHasil.add(sppdReroute);
                    }else{
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        session.removeAttribute("listOfResultSppdReroute");
        session.setAttribute("listOfResultSppdReroute", listHasil);
        return listHasil;
    }
    //-----------------------------------------------------------------------end Sppd Reroute Menejemen--------//

    /*TIKET*/
    public List<SppdPerson> SaveTiket(String SppdId, String nip, BigDecimal hargaPergi, BigDecimal hargaKembali, BigDecimal biayaTambahan, BigDecimal biayaLokal, BigDecimal biayaTujuan) {
        logger.info("[SppdAction.saveEdit] start process >>>");
        try {
            SppdPerson sppdPerson = new SppdPerson();
            sppdPerson.setPersonId(nip);
            sppdPerson.setSppdId(SppdId);
            sppdPerson.setTiketPergi(hargaPergi);
            sppdPerson.setTiketKembali(hargaKembali);
            sppdPerson.setBiayaTambahan(biayaTambahan);
            sppdPerson.setBiayaLokalBerangkat(biayaLokal);
            sppdPerson.setBiayaTujuan(biayaTujuan);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sppdPerson.setLastUpdateWho(userLogin);
            sppdPerson.setLastUpdate(updateTime);
            sppdPerson.setAction("U");
            sppdPerson.setFlag("Y");
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdBo.saveEdit(sppdPerson);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.saveEdit] Error when saving error,", e1);
            }
            logger.error("[SppdAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[SppdAction.saveEdit] end process <<<");

        return null;
    }

    public List<SppdPerson> SaveTiket2(String SppdId, String nip, BigDecimal hargaPergi, BigDecimal hargaKembali, BigDecimal biayaTambahan, String tanggalBerangkat,
                                       String tanggalKembali, String berangkatVia, String kembaliVia, BigDecimal biayaLokal, BigDecimal biayaTujuan, BigDecimal biayaKembali,
                                       String berangkatDari, String tujuanKe) {
        logger.info("[SppdAction.saveEdit] start process >>>");
        try {
            SppdPerson sppdPerson = new SppdPerson();
            sppdPerson.setPersonId(nip);
            sppdPerson.setSppdId(SppdId);
            sppdPerson.setTiketPergi(hargaPergi);
            sppdPerson.setTiketKembali(hargaKembali);
            sppdPerson.setBiayaTambahan(biayaTambahan);
            sppdPerson.setBiayaLokalBerangkat(biayaLokal);
            sppdPerson.setBiayaTujuan(biayaTujuan);
            sppdPerson.setBiayaLokalKembali(biayaKembali);
            sppdPerson.setBerangkatVia(berangkatVia);
            sppdPerson.setKembaliVia(kembaliVia);
            sppdPerson.setStTanggalBerangkatRealisasi(tanggalBerangkat);
            sppdPerson.setStTanggalKembaliRealisasi(tanggalKembali);
            sppdPerson.setBerangkatDari(berangkatDari);
            sppdPerson.setTujuanKe(tujuanKe);

            /*sppdPerson.setSppdTanggalId1(tanggalBerangkat);
            sppdPerson.setSppdTanggalId2(sppdTanggalId2);*/

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sppdPerson.setLastUpdateWho(userLogin);
            sppdPerson.setLastUpdate(updateTime);
            sppdPerson.setAction("U");
            sppdPerson.setFlag("Y");
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdBo.saveEditBerangkat(sppdPerson);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.saveEdit] Error when saving error,", e1);
            }
            logger.error("[SppdAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[SppdAction.saveEdit] end process <<<");

        return null;
    }
    /*END TIKET*/
    @Override
    public String search() {
        logger.info("[SppdAction.search] start process >>>");

        Sppd searchSppd = getSppd();
        List<Sppd> listOfsearchSppd = new ArrayList();

        if("".equalsIgnoreCase(searchSppd.getPersonName()) || searchSppd.getPersonName() == null){
            searchSppd.setPersonNip("");
        }

        if (searchSppd.getStTanggalSppdBerangkat() != null && !"".equalsIgnoreCase(searchSppd.getStTanggalSppdBerangkat())) {
            searchSppd.setTanggalSppdBerangkat(CommonUtil.convertStringToDate(searchSppd.getStTanggalSppdBerangkat()));
            if (searchSppd.getStTanggalSppdKembali() != null && !"".equalsIgnoreCase(searchSppd.getStTanggalSppdKembali())) {
                searchSppd.setTanggalSppdKembali(CommonUtil.convertStringToDate(searchSppd.getStTanggalSppdKembali()));
            } else {
                Timestamp tanggalSekarang = new Timestamp(Calendar.getInstance().getTimeInMillis());
                searchSppd.setTanggalSppdKembali(CommonUtil.convertTimestampToDate(tanggalSekarang));
            }

        } else {
            if (searchSppd.getStTanggalSppdKembali() != null && !"".equalsIgnoreCase(searchSppd.getStTanggalSppdKembali())) {
                searchSppd.setTanggalSppdKembali(CommonUtil.convertStringToDate(searchSppd.getStTanggalSppdKembali()));
                searchSppd.setTanggalSppdBerangkat(searchSppd.getTanggalSppdKembali());
            }
        }

        try {
            listOfsearchSppd = sppdBoProxy.getByCriteria(searchSppd);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSppd);

        logger.info("[SppdAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSppd() {
        logger.info("[SppdAction.search] start process >>>");

        Sppd searchSppd = new Sppd();
        searchSppd.setFlag("Y");
        List<Sppd> listOfsearchSppd = new ArrayList();

        try {
            listOfsearchSppd = sppdBoProxy.getByCriteria(searchSppd);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSppd.addAll(listOfsearchSppd);
        return SUCCESS;
    }

    public List<Sppd> searchSppd(String SppdId) {
        logger.info("[SppdAction.search] start process >>>");
        Sppd sppd = new Sppd();
        sppd.setSppdId(SppdId);
        List<Sppd> sppdList = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
            sppdList = sppdBo.getByCriteria(sppd);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return sppdList;
    }

    @Override
    public String initForm() {
        logger.info("[SppdAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SppdAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSppd() {
        logger.info("[SppdAction.search] start process >>>");

        Sppd searchSppd = new Sppd();
        searchSppd.setFlag("Y");
        List<Sppd> listOfsearchSppd = new ArrayList();

        try {
            listOfsearchSppd = sppdBoProxy.getByCriteria(searchSppd);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SppdAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSppd");
        session.setAttribute("listOfResultSppd", listOfsearchSppd);

        logger.info("[SppdAction.search] end process <<<");

        return "";
    }

    public void removeTanggalSppd(String sppdPersonId){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo payrollBo = (SppdBo) ctx.getBean("sppdBoProxy");
        payrollBo.removeTanggalSppd(sppdPersonId);
    }

    public void saveTanggalSppd(String idSppdTanggal, int jumlahTanggal, int indexTanggal, String biayaLain, String biayaMakan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");

        BigDecimal lain = BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(biayaLain)));
        BigDecimal makan = BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(biayaMakan)));
        sppdBo.updateTanggalSppd(idSppdTanggal, jumlahTanggal, indexTanggal, lain, makan);
    }

    // cek kelompok position untuk menonaktifkan tomboll aprrove, jika buka sdm / admin
    public String cekKelompokPosition(){
        String hasil = "";

        hasil = CommonUtil.roleAsLogin();

        return hasil;
    }

    // <chart> mendapatkan data SPPD per tahun
    public List<Sppd> getSppdTahun(String tahun, String divisi){
        List<Sppd> hasilSppd = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        hasilSppd = sppdBo.getSppdTahun(tahun, divisi);

        return hasilSppd;
    }

    // <chart> mendapatkan data SPPD per tahun By bagian
    public List<Sppd> getSppdTahunByBagian(String tahun, String bagian){
        List<Sppd> hasilSppd = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        hasilSppd = sppdBo.getSppdTahunByBagian(tahun, bagian);

        return hasilSppd;
    }

    // <chart> mendapatkan data SPPD per tahun filter by divisi
    public List<Sppd> getSppdTahunByDivisi(String tahun, String bulan){
        List<Sppd> hasilSppd = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        hasilSppd = sppdBo.getSppdTahunByDivisi(tahun, bulan);

        return hasilSppd;
    }

    // <chart> mendapatkan data SPPD per tahun filter by NIP
    public List<Sppd> getSppdTahunByNip(String tahun, String bulan, String divisiName){
        List<Sppd> hasilSppd = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        hasilSppd = sppdBo.getSppdTahunByNip(tahun, bulan, divisiName);

        return hasilSppd;
    }

    // <chart> mendapatkan data SPPD per tahun filter by bagian
    public List<Sppd> getSppdTahunBagian(String tahun, String bulan){
        List<Sppd> hasilSppd = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        hasilSppd = sppdBo.getSppdTahunBagian(tahun, bulan);

        return hasilSppd;
    }

    // <chart> mendapatkan data SPPD per tahun filter by divisiId
    public List<Sppd> getSppdTahunBagian(String tahun, String bulan, String divisiId){
        List<Sppd> hasilSppd = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        hasilSppd = sppdBo.getSppdTahunBagian(tahun, bulan, divisiId);

        return hasilSppd;
    }

    //DWR cek upload data Training
    public String cekDokumenTraining(String sppdId){
        String hasil = "-";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        hasil = sppdBo.cekDokumenTraining(sppdId);

        return hasil;
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