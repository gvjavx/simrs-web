package com.neurix.hris.transaksi.notifikasi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.ijin.model.Ijin;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.indisipliner.model.Indisipliner;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.RekruitmenPabrik;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.RekruitmenPabrikDetail;
import com.neurix.hris.transaksi.sppd.bo.SppdBo;
import com.neurix.hris.transaksi.sppd.model.ImSppdEntity;
import com.neurix.hris.transaksi.sppd.model.Sppd;
import com.neurix.hris.transaksi.sppd.model.SppdPerson;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class NotifikasiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(NotifikasiAction.class);
    private NotifikasiBo notifikasiBoProxy;
    private SppdBo sppdBoProxy;
    private Notifikasi notifikasi;
    private TrainingPerson trainingPerson;
    private IjinKeluar ijinKeluar;
    private CutiPegawai cutiPegawai;
    private Lembur lembur;
    private Sppd sppd;
    private SppdPerson sppdPerson;
    private RekruitmenPabrikDetail rekruitmenPabrikDetail;
    private Indisipliner indisipliner;
    private AbsensiPegawai absensiPegawai;
    private PengajuanBiaya pengajuanBiaya;

    private String id;
    private String request;
    private String tipeNotif;
    private String notif;

    public PengajuanBiaya getPengajuanBiaya() {
        return pengajuanBiaya;
    }

    public void setPengajuanBiaya(PengajuanBiaya pengajuanBiaya) {
        this.pengajuanBiaya = pengajuanBiaya;
    }

    public AbsensiPegawai getAbsensiPegawai() {
        return absensiPegawai;
    }

    public void setAbsensiPegawai(AbsensiPegawai absensiPegawai) {
        this.absensiPegawai = absensiPegawai;
    }

    public Indisipliner getIndisipliner() {
        return indisipliner;
    }

    public void setIndisipliner(Indisipliner indisipliner) {
        this.indisipliner = indisipliner;
    }

    public RekruitmenPabrikDetail getRekruitmenPabrikDetail() {
        return rekruitmenPabrikDetail;
    }

    public void setRekruitmenPabrikDetail(RekruitmenPabrikDetail rekruitmenPabrikDetail) {
        this.rekruitmenPabrikDetail = rekruitmenPabrikDetail;
    }

    public Lembur getLembur() {
        return lembur;
    }

    public void setLembur(Lembur lembur) {
        this.lembur = lembur;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public CutiPegawai getCutiPegawai() {
        return cutiPegawai;
    }

    public void setCutiPegawai(CutiPegawai cutiPegawai) {
        this.cutiPegawai = cutiPegawai;
    }

    public IjinKeluar getIjinKeluar() {
        return ijinKeluar;
    }

    public void setIjinKeluar(IjinKeluar ijinKeluar) {
        this.ijinKeluar = ijinKeluar;
    }

    public Sppd getSppd() {
        return sppd;
    }

    public void setSppd(Sppd sppd) {
        this.sppd = sppd;
    }

    public SppdPerson getSppdPerson() {
        return sppdPerson;
    }

    public void setSppdPerson(SppdPerson sppdPerson) {
        this.sppdPerson = sppdPerson;
    }

    public SppdBo getSppdBoProxy() {
        return sppdBoProxy;
    }

    public void setSppdBoProxy(SppdBo sppdBoProxy) {
        this.sppdBoProxy = sppdBoProxy;
    }

    public TrainingPerson getTrainingPerson() {
        return trainingPerson;
    }

    public void setTrainingPerson(TrainingPerson trainingPerson) {
        this.trainingPerson = trainingPerson;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getTipeNotif() {
        return tipeNotif;
    }

    public void setTipeNotif(String tipeNotif) {
        this.tipeNotif = tipeNotif;
    }

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public Notifikasi getNotifikasi() {
        return notifikasi;
    }

    public void setNotifikasi(Notifikasi notifikasi) {
        this.notifikasi = notifikasi;
    }

    private List<Notifikasi> initComboNotifikasi;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        NotifikasiAction.logger = logger;
    }


    public List<Notifikasi> getInitComboNotifikasi() {
        return initComboNotifikasi;
    }

    public void setInitComboNotifikasi(List<Notifikasi> initComboNotifikasi) {
        this.initComboNotifikasi = initComboNotifikasi;
    }

    public Notifikasi init(String kode, String flag){
        logger.info("[NotifikasiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Notifikasi> listOfResult = (List<Notifikasi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Notifikasi notifikasi : listOfResult) {
                    if(kode.equalsIgnoreCase(notifikasi.getNotifId()) && flag.equalsIgnoreCase(notifikasi.getFlag())){
                        setNotifikasi(notifikasi);
                        break;
                    }
                }
            } else {
                setNotifikasi(new Notifikasi());
            }

            logger.info("[NotifikasiAction.init] end process >>>");
        }
        return getNotifikasi();
    }

    @Override
    public String add() {
        logger.info("[NotifikasiAction.add] start process >>>");
        Notifikasi addNotifikasi = new Notifikasi();
        setNotifikasi(addNotifikasi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[NotifikasiAction.add] stop process >>>");
        return "init_add";
    }


    @Override
    public String edit() {
        logger.info("[NotifikasiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Notifikasi editNotifikasi = new Notifikasi();

        if(itemFlag != null){
            try {
                editNotifikasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getPersonalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[NotifikasiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editNotifikasi != null) {
                setNotifikasi(editNotifikasi);
            } else {
                editNotifikasi.setFlag(itemFlag);
                editNotifikasi.setNip(itemId);
                setNotifikasi(editNotifikasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editNotifikasi.setNip(itemId);
            editNotifikasi.setFlag(getFlag());
            setNotifikasi(editNotifikasi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[NotifikasiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[NotifikasiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Notifikasi deleteNotifikasi = new Notifikasi();

        if (itemFlag != null ) {

            try {
                deleteNotifikasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[NotifikasiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteNotifikasi != null) {
                setNotifikasi(deleteNotifikasi);

            } else {
                deleteNotifikasi.setNip(itemId);
                deleteNotifikasi.setFlag(itemFlag);
                setNotifikasi(deleteNotifikasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteNotifikasi.setNip(itemId);
            deleteNotifikasi.setFlag(itemFlag);
            setNotifikasi(deleteNotifikasi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        setDelete(true);
        logger.info("[NotifikasiAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[NotifikasiAction.add] start process >>>");
        Notifikasi addNotifikasi = new Notifikasi();
        setNotifikasi(addNotifikasi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[NotifikasiAction.add] stop process >>>");
        return "init_add_user";
    }

    @Override
    public String save() {
        if (isAddOrEdit()) {

            if (!isAdd()) {
                logger.info("[NotifikasiAction.saveEdit] start process >>>");
                try {

                    Notifikasi editNotifikasi = getNotifikasi();

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    editNotifikasi.setLastUpdateWho(userLogin);
                    editNotifikasi.setLastUpdate(updateTime);
                    editNotifikasi.setAction("U");
                    editNotifikasi.setFlag("Y");

                    notifikasiBoProxy.saveEdit(editNotifikasi);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
                    } catch (GeneralBOException e1) {
                        logger.error("[NotifikasiAction.saveEdit] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[NotifikasiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }

                logger.info("[NotifikasiAction.saveEdit] end process <<<");

                return "success_save_edit";
            } else {
                //add
                try {
                    Notifikasi notifikasi = getNotifikasi();
                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

//                    notifikasi.setCreatedWho(userLogin);
//                    notifikasi.setLastUpdate(updateTime);
//                    notifikasi.setCreatedDate(updateTime);
//                    notifikasi.setTanggalAktif(updateTime);
//                    notifikasi.setLastUpdateWho(userLogin);
//                    notifikasi.setAction("C");
//                    notifikasi.setStatusCaption("Online");
//                    notifikasi.setFlag("Y");

                    notifikasiBoProxy.saveAdd(notifikasi);
                }catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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

            logger.info("[NotifikasiAction.saveDelete] start process >>>");
            try {

                Notifikasi deleteNotifikasi = getNotifikasi();

                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                deleteNotifikasi.setLastUpdate(updateTime);
                deleteNotifikasi.setLastUpdateWho(userLogin);
                deleteNotifikasi.setAction("U");
                deleteNotifikasi.setFlag("N");

                notifikasiBoProxy.saveDelete(deleteNotifikasi);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.saveDelete] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

            logger.info("[NotifikasiAction.saveDelete] end process <<<");

            return SUCCESS;

        }

        return null;
    }

    public String saveEdit(){
        logger.info("[NotifikasiAction.saveEdit] start process >>>");
        try {

            Notifikasi editNotifikasi = getNotifikasi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editNotifikasi.setLastUpdateWho(userLogin);
            editNotifikasi.setLastUpdate(updateTime);
            editNotifikasi.setAction("U");
            editNotifikasi.setFlag("Y");

            notifikasiBoProxy.saveEdit(editNotifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[NotifikasiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[NotifikasiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[NotifikasiAction.saveDelete] start process >>>");
        try {

            Notifikasi deleteNotifikasi = getNotifikasi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteNotifikasi.setLastUpdate(updateTime);
            deleteNotifikasi.setLastUpdateWho(userLogin);
            deleteNotifikasi.setAction("U");
            deleteNotifikasi.setFlag("N");

            notifikasiBoProxy.saveDelete(deleteNotifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[NotifikasiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[NotifikasiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[NotifikasiAction.saveAdd] start process >>>");

        try {
            Notifikasi notifikasi = getNotifikasi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            notifikasi.setCreatedWho(userLogin);
            notifikasi.setLastUpdate(updateTime);
            notifikasi.setCreatedDate(updateTime);
            notifikasi.setLastUpdateWho(userLogin);
            notifikasi.setAction("C");
            notifikasi.setFlag("Y");

            notifikasiBoProxy.saveAdd(notifikasi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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

    @Override
    public String search() {
        logger.info("[NotifikasiAction.search] start process >>>");

        Notifikasi searchNotifikasi = getNotifikasi();
        List<Notifikasi> listOfsearchNotifikasi = new ArrayList();

        try {
            listOfsearchNotifikasi = notifikasiBoProxy.getByCriteria(searchNotifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchNotifikasi);

        logger.info("[NotifikasiAction.search] end process <<<");

        return SUCCESS;
    }


    //
    //
    //
    public List<Notifikasi> searchData() {
        logger.info("[NotifikasiAction.search] start process >>>");

        Notifikasi searchNotifikasi = new Notifikasi();
        searchNotifikasi.setFlag("Y");
        List<Notifikasi> listOfsearchNotifikasi = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

            listOfsearchNotifikasi = notifikasiBo.getByCriteria(searchNotifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiAction.search] Error when saving error,", e1);
            }
            logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return listOfsearchNotifikasi;
    }
    public List<Notifikasi> searchData2(String nip,String id) {
        logger.info("[NotifikasiAction.search] start process >>>");

        Notifikasi searchNotifikasi = new Notifikasi();
        searchNotifikasi.setNip(nip);
        searchNotifikasi.setFlag("Y");
        List<Notifikasi> listOfsearchNotifikasi = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

            listOfsearchNotifikasi = notifikasiBo.getByCriteria(searchNotifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiAction.search] Error when saving error,", e1);
            }
            logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return listOfsearchNotifikasi;
    }
    //
    //


    @Override
    public String initForm() {
        logger.info("[NotifikasiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[NotifikasiAction.initForm] end process >>>");
        return INPUT;
    }

    public String Form() {
        String userLogin = CommonUtil.userIdLogin();
        logger.info("[NotifikasiAction.search] start process >>>");

        Notifikasi searchNotifikasi = new Notifikasi();
        searchNotifikasi.setNip(userLogin);
        searchNotifikasi.setFlag("Y");
        List<Notifikasi> listOfsearchNotifikasi = new ArrayList();

        try {
            listOfsearchNotifikasi = notifikasiBoProxy.getByCriteria(searchNotifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchNotifikasi);

        logger.info("[NotifikasiAction.search] end process <<<");

        logger.info("[NotifikasiAction.edit] start process >>>");
        String itemId = userLogin;
        String itemFlag = "Y";

        Notifikasi editNotifikasi = new Notifikasi();

        if(itemFlag != null){
            try {
                editNotifikasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getPersonalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[NotifikasiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            /*if(editNotifikasi != null) {
                editNotifikasi.setNamaPegawai(editNotifikasi.getGelarDepan() + ' ' + editNotifikasi.getNamaPegawai() + ' ' +editNotifikasi.getGelarBelakang());
                editNotifikasi.setAlamat(editNotifikasi.getProvinsi() + ' ' + editNotifikasi.getKabupaten() + ' ' +editNotifikasi.getKecamatan() + ' ' + editNotifikasi.getDesa() + ' ' +editNotifikasi.getAlamat());
                editNotifikasi.setTempatLahir(editNotifikasi.getTempatLahir() + " / " +editNotifikasi.getTanggalLahir());
                setNotifikasi(editNotifikasi);
            } else {
                editNotifikasi.setFlag(itemFlag);
                editNotifikasi.setNip(itemId);
                setNotifikasi(editNotifikasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }*/
        } else {
            editNotifikasi.setNip(itemId);
            editNotifikasi.setFlag(getFlag());
            setNotifikasi(editNotifikasi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[NotifikasiAction.edit] end process >>>");
        return "form_user";
    }

    public String initPersonal() {
        logger.info("[NotifikasiAction.search] start process >>>");

        Notifikasi searchNotifikasi = new Notifikasi();
        searchNotifikasi.setFlag("Y");
        List<Notifikasi> listOfsearchNotifikasi = new ArrayList();

        try {
            listOfsearchNotifikasi = notifikasiBoProxy.getByCriteria(searchNotifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPersonal");
        session.setAttribute("listOfResultPersonal", listOfsearchNotifikasi);

        logger.info("[NotifikasiAction.search] end process <<<");

        return "";
    }
    public String searchUser() {

        return CommonUtil.roleAsLogin();
    }
    public List<Notifikasi> searchNotif(){
        String userLoginId = CommonUtil.userIdLogin();

        List<Notifikasi> listOfResult = new ArrayList<Notifikasi>();
        Notifikasi notif = new Notifikasi();
        notif.setFlag("Y");
        notif.setRead("Y");
        notif.setNip(userLoginId);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        try {
            listOfResult = notifikasiBo.getByCriteriaForNotif(notif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBo.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftAction.search] Error when saving error,", e1);
//                return ERROR;
            }
            logger.error("[GroupShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
        }

        return listOfResult;
    }
    public List<Notifikasi> searchCutiPensiun(){
        List<Notifikasi> listOfResult = new ArrayList<Notifikasi>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        try {
            listOfResult = notifikasiBo.getCutiPensiun();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiBo.searchCutiPensiun");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiBo.searchCutiPensiun] Error when saving error,", e1);
//                return ERROR;
            }
            logger.error("[NotifikasiBo.searchCutiPensiun] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
        }
        return listOfResult;
    }


    public List<Notifikasi> searchJubilium(){
        List<Notifikasi> listOfResult = new ArrayList<Notifikasi>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        try {
            listOfResult = notifikasiBo.getJubilium();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiBo.searchCutiPensiun");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiBo.searchCutiPensiun] Error when saving error,", e1);
//                return ERROR;
            }
            logger.error("[NotifikasiBo.searchCutiPensiun] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
        }
        return listOfResult;
    }



    public List<Notifikasi> searchCutiTahunan(){
        List<Notifikasi> listOfResult = new ArrayList<Notifikasi>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        try {
            listOfResult = notifikasiBo.getCutiTahunan();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiBo.searchCutiPensiun");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiBo.searchCutiPensiun] Error when saving error,", e1);
//                return ERROR;
            }
            logger.error("[NotifikasiBo.searchCutiPensiun] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
        }
        return listOfResult;
    }
    public List<Notifikasi> searchCutiPanjang(){
        List<Notifikasi> listOfResult = new ArrayList<Notifikasi>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        try {
            listOfResult = notifikasiBo.getCutiPanjang();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiBo.searchCutiPensiun");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiBo.searchCutiPanjang] Error when saving error,", e1);
//                return ERROR;
            }
            logger.error("[NotifikasiBo.searchCutiPanjang] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
        }
        return listOfResult;
    }
    public String viewNotifikasi(){

        String function = "";
        String tipe = getTipeNotif();
        String notifId = getNotif();
        String id = getId();
        String request = getRequest();

        TrainingPerson trainingPerson = getTrainingPerson();

        IjinKeluar ijinKeluar = getIjinKeluar();
        IjinKeluar ijinKeluarKantor = getIjinKeluar();
        CutiPegawai cutiPegawai = getCutiPegawai();
        AbsensiPegawai absensiPegawai = getAbsensiPegawai();
        RekruitmenPabrikDetail rekruitmenPabrikDetail = getRekruitmenPabrikDetail();
        Lembur lembur = getLembur();
        Indisipliner indisipliner = getIndisipliner();
        PengajuanBiaya pengajuanBiaya = getPengajuanBiaya();
        List<TrainingPerson> listOfResult = new ArrayList<TrainingPerson>();
        List<IjinKeluar> listOfResultIK = new ArrayList<IjinKeluar>();
        List<IjinKeluar> listOfResultIKK = new ArrayList<IjinKeluar>();
        List<Lembur> listOfResultLB = new ArrayList<Lembur>();
        List<AbsensiPegawai> listOfResultABS = new ArrayList<AbsensiPegawai>();
        List<CutiPegawai> listOfResultCP = new ArrayList<CutiPegawai>();
        List<Indisipliner> listOfResultID = new ArrayList<Indisipliner>();
        List<RekruitmenPabrikDetail> listOfResultRPD = new ArrayList<RekruitmenPabrikDetail>();
        List<PengajuanBiaya> listOfResultPB = new ArrayList<PengajuanBiaya>();
        List<Notifikasi> listOfResultAll = new ArrayList<Notifikasi>();
        SppdPerson sppdPerson ;

        List<SppdPerson> sppdPersons = new ArrayList<SppdPerson>();

        if ("TN23".equalsIgnoreCase(tipe)){
            trainingPerson = new TrainingPerson();
            trainingPerson.setPersonId(id);
            trainingPerson.setTrainingId(request);
        }
        if ("TN44".equalsIgnoreCase(tipe)){
            String personId = getId();
            String indisiplinerId = getRequest();

            indisipliner = new Indisipliner();
            indisipliner.setNip(personId);
            indisipliner.setIndisiplinerId(indisiplinerId);
        }
        if ("TN01".equalsIgnoreCase(tipe)){
            String pengajuanBiayaId = getRequest();

            pengajuanBiaya = new PengajuanBiaya();
            pengajuanBiaya.setPengajuanBiayaId(pengajuanBiayaId);
        }
        if ("TN55".equalsIgnoreCase(tipe)){
            String personId = getId();
            String ijinKeluarId = getRequest();

            ijinKeluar = new IjinKeluar();
            ijinKeluar.setNip(personId);
            ijinKeluar.setIjinKeluarId(ijinKeluarId);
            ijinKeluar.setFrom("ijinKeluar");
        }
        if ("TN66".equalsIgnoreCase(tipe)){
            String personId = getId();
            String cutiPegawaiId = getRequest();

            cutiPegawai = new CutiPegawai();
            cutiPegawai.setNip(personId);
            cutiPegawai.setCutiPegawaiId(cutiPegawaiId);
        }
        if ("TN33".equalsIgnoreCase(tipe)){
            String personId = getId();
            String absensiId = getRequest();

            absensiPegawai = new AbsensiPegawai();
            absensiPegawai.setNip(personId);
            absensiPegawai.setAbsensiPegawaiId(absensiId);
        }
        if ("TN77".equalsIgnoreCase(tipe)){
            String personId = getId();
            String lemburId = getRequest();
            Lembur search = getLembur();
            if (search!=null){
                lemburId = search.getLemburId();
                personId = search.getNip();
            }
            lembur = new Lembur();
            lembur.setNip(personId);
            lembur.setLemburId(lemburId);
        }
        if ("TN88".equalsIgnoreCase(tipe)){
            String personId = getId();
            String ijinKeluarId = getRequest();

            ijinKeluarKantor = new IjinKeluar();
            ijinKeluarKantor.setNip(personId);
            ijinKeluarKantor.setFrom("ijinKeluarKantor");
            ijinKeluarKantor.setIjinKeluarId(ijinKeluarId);
        }
        if ("TN99".equalsIgnoreCase(tipe)){
            String rekruitmenPabrikId = getRequest();

            rekruitmenPabrikDetail = new RekruitmenPabrikDetail();
            rekruitmenPabrikDetail.setRekruitmenPabrikId(rekruitmenPabrikId);
        }
        if ("TI".equalsIgnoreCase(tipe)){
            String personId = getId();
            String sppdId = getRequest();

            List<Sppd> sppdList = new ArrayList<Sppd>();
            Sppd sppd = new Sppd();
            sppdPerson = new SppdPerson();
            sppd.setSppdId(sppdId);
            sppdPerson.setSppdId(sppdId);

            if (sppdPerson != null){
                try {
                    sppdList = sppdBoProxy.getByCriteria(sppd);
                    sppdPersons = sppdBoProxy.getComboSppdPerson(sppdId, personId);
                    setSppdPerson(sppdPerson);
                    if(sppdList != null){
                        for (Sppd sppd1 : sppdList) {
                            setSppd(sppd1);
                        }
                    }
                    function = "sppd";
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                    } catch (GeneralBOException e1) {
                        logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                    return ERROR;
                }
            }
        }


        if (trainingPerson != null){
            try {
                setTrainingPerson(trainingPerson);
                listOfResult = notifikasiBoProxy.searchTrainingPerson(trainingPerson);
                function = "training";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                return ERROR;
            }
        }

        if (ijinKeluar != null) {
            try {
                setIjinKeluar(ijinKeluar);
                listOfResultIK = notifikasiBoProxy.searchIjinKeluarPerson(ijinKeluar);
                function = "ijinKeluar";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return ERROR;
            }
        }
        if (absensiPegawai != null) {
            try {
                setAbsensiPegawai(absensiPegawai);
                listOfResultABS = notifikasiBoProxy.searchAbsensiPegawaiPerson(absensiPegawai);
                function = "absensiPegawai";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.searchAbsensiPegawai] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.searchAbsensiPegawai] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return ERROR;
            }
        }
//        if (ijinKeluarKantor != null) {
//            try {
//                setIjinKeluar(ijinKeluarKantor);
//                listOfResultIKK = notifikasiBoProxy.searchIjinKeluarPerson(ijinKeluarKantor);
//                function = "ijinKeluarKantor";
//            } catch (GeneralBOException e) {
//                Long logId = null;
//                try {
//                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
//                } catch (GeneralBOException e1) {
//                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
//                    return ERROR;
//                }
//                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
//                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
//                return ERROR;
//            }
//        }
        if (lembur != null) {
            try {
                setLembur(lembur);
                listOfResultLB = notifikasiBoProxy.searchLemburPerson(lembur);
                function = "lembur";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return ERROR;
            }
        }
        if (indisipliner != null) {
            try {
                setIndisipliner(indisipliner);
                listOfResultID = notifikasiBoProxy.searchIndisiplinerPerson(indisipliner);
                function = "indisipliner";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return ERROR;
            }
        }
        if (cutiPegawai != null){
            try {
                setCutiPegawai(cutiPegawai);
                listOfResultCP = notifikasiBoProxy.searchCutiPegawaiPerson(cutiPegawai);
                function = "cutiPegawai";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return ERROR;
            }
        }
        if (pengajuanBiaya != null){
            try {
                setPengajuanBiaya(pengajuanBiaya);
                listOfResultPB = notifikasiBoProxy.searchPengajuanBiaya(pengajuanBiaya);
                function = "pengajuanBiaya";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return ERROR;

            }
        }
        if (rekruitmenPabrikDetail != null) {
            try {
                setRekruitmenPabrikDetail(rekruitmenPabrikDetail);
                listOfResultRPD = notifikasiBoProxy.searchRekruitmenPabrikDetailPerson(rekruitmenPabrikDetail);
                function = "rekruitmenPabrikDetail";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return ERROR;
            }
        }
        String userLoginId = CommonUtil.userIdLogin();
        if (listOfResult != null){
            List<TrainingPerson> listOfResultTmp = new ArrayList<TrainingPerson>();
            listOfResultTmp = listOfResult;
            listOfResult = new ArrayList<TrainingPerson>();
            for (TrainingPerson listPerson : listOfResultTmp){
                if (listPerson.getPersonId().equalsIgnoreCase(getId())){
                    ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                    BiodataBo userBo = (BiodataBo) ctx.getBean("biodataBoProxy");

                    Biodata biodata = userBo.detailBiodataSys(userLoginId);
                    if (listPerson.getApprovalFlag() == null || listPerson.getApprovalFlag().equalsIgnoreCase("")) {
                        listPerson.setApproveAtasan(true);
                    }

                    if (("KL03").equalsIgnoreCase(biodata.getKelompokId())){
                        if (("Y").equalsIgnoreCase(listPerson.getApprovalFlag())){
                            if (listPerson.getApprovalBosFlag() == null || listPerson.getApprovalBosFlag().equalsIgnoreCase("")){
                                listPerson.setApproveKepala(true);
                            }
                        }
                    }else if(userLoginId.equalsIgnoreCase("0001")){
                        if (("Y").equalsIgnoreCase(listPerson.getApprovalFlag())){
                            if (listPerson.getApprovalBosFlag() == null || listPerson.getApprovalBosFlag().equalsIgnoreCase("")){
                                listPerson.setApproveKepala(true);
                            }
                        }
                    }
                    listOfResult.add(listPerson);
                }


//                if (userLoginId.equalsIgnoreCase(listPerson.getApprovalId())){
//
//                }
//                if (userLoginId.equalsIgnoreCase(listPerson.getApprovalBosId())){
//
//                }
            }
        }
        if (listOfResultCP != null){
            for (CutiPegawai listCutiPegawai : listOfResultCP){
                    if ("Y".equalsIgnoreCase(listCutiPegawai.getApprovalFlag())){
                        listCutiPegawai.setCutiPegawaiApprove(true);
                        listCutiPegawai.setCutiPegawaiApproveStatus(true);
                    }
            }
        }
        if (listOfResultPB != null){
            for (PengajuanBiaya listPengajuanBiaya : listOfResultPB){
                if ("Y".equalsIgnoreCase(listPengajuanBiaya.getAprovalFlag())){
                    listPengajuanBiaya.setApprovePengajuanBiaya(true);
                }
            }
        }
        if (listOfResultABS != null){
            for (AbsensiPegawai listAbsensiPegawai : listOfResultABS){
                if ("Y".equalsIgnoreCase(listAbsensiPegawai.getApprovalFlag())){
                    listAbsensiPegawai.setAbsensiApprove(true);
                }
            }
        }
        if (listOfResultID != null){
            for (Indisipliner listIndisipliner : listOfResultID){
                if ("Y".equalsIgnoreCase(listIndisipliner.getApprovalFlag())){
                    listIndisipliner.setIndisiplinerApprove(true);
                }
            }
        }
        if (listOfResultIK != null){
            for (IjinKeluar listIjinKeluar : listOfResultIK){
                if ("Y".equalsIgnoreCase(listIjinKeluar.getApprovalFlag())){
                    listIjinKeluar.setIjinKeluarApprove(true);
                    listIjinKeluar.setIjinKeluarApproveStatus(true);
                }
            }
        }
        if (listOfResultIKK != null){
            for (IjinKeluar listIjinKeluarKantor : listOfResultIKK){
                if ("Y".equalsIgnoreCase(listIjinKeluarKantor.getApprovalFlag())){
                    listIjinKeluarKantor.setIjinKeluarApprove(true);
                    listIjinKeluarKantor.setIjinKeluarApproveStatus(true);
                }
            }
        }
        if (listOfResultLB != null){
            for (Lembur listLembur : listOfResultLB){
                if ("Y".equalsIgnoreCase(listLembur.getApprovalFlag())){
                    listLembur.setLemburApprove(true);
                    listLembur.setLemburApproveStatus(true);
                }else if ("N".equalsIgnoreCase(listLembur.getApprovalFlag())){
                    listLembur.setNotApprove(true);
                }
            }
        }
        if (listOfResultRPD != null){
            for (RekruitmenPabrikDetail rekruitmenPabrikDetail1 : listOfResultRPD){
                if ("Y".equalsIgnoreCase(rekruitmenPabrikDetail1.getApprovalGmFlag())){
                    rekruitmenPabrikDetail1.setRekruitmenPabrikDetailApprove(true);
                    rekruitmenPabrikDetail1.setRekruitmenPabrikDetailStatus(true);
                }
            }
        }

        // set read to N on table notification
        if (notifId != null){
            readNotification(notifId);
        }

        if ("training".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultPerson");
            session.setAttribute("listOfResultPerson", listOfResult);
            return "approval_atasan_training";
        }
        else if ("ijinKeluar".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultIjinKeluar");
            session.setAttribute("listOfResultIjinKeluar", listOfResultIK);
            return "approval_atasan_ijin_keluar";
        }
        else if ("ijinKeluarKantor".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultIjinKeluarKantor");
            session.setAttribute("listOfResultIjinKeluarKantor", listOfResultIKK);
            return "approval_atasan_ijin_keluar_kantor";
        }
        else if ("cutiPegawai".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultCutiPegawai");
            session.setAttribute("listOfResultCutiPegawai", listOfResultCP);
            return "approval_atasan_cuti_pegawai";
        }
        else if ("absensiPegawai".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultAbsensi");
            session.setAttribute("listOfResultAbsensi", listOfResultABS);
            return "approval_atasan_absensi";
        }
        else if ("lembur".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultLembur");
            session.setAttribute("listOfResultLembur", listOfResultLB);
            return "approval_atasan_lembur";
        }
        else if ("indisipliner".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultIndisipliner");
            session.setAttribute("listOfResultIndisipliner", listOfResultID);
            return "approval_atasan_indisipliner";
        }
        else if ("pengajuanBiaya".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultPengajuanBiaya");
            session.setAttribute("listOfResultPengajuanBiaya", listOfResultPB);
            return "approval_pengajuan_biaya";
        }
        else if ("rekruitmenPabrikDetail".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultRekruitmenPabrikDetail");
            session.setAttribute("listOfResultRekruitmenPabrikDetail", listOfResultRPD);
            return "approval_atasan_rekruitmen_pabrik";
        }
        else if ("sppd".equalsIgnoreCase(function)){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResultPerson");
            session.setAttribute("listOfResultPerson", sppdPersons);
            return "approval_atasan_sppd";
        }
        else {
            Notifikasi notifikasi = new Notifikasi();
            notifikasi.setNotifId(notifId);
            List<Notifikasi> notifikasiList = new ArrayList<Notifikasi>();
            try {
                notifikasiList = notifikasiBoProxy.getByCriteria(notifikasi);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return ERROR;
            }

            if (notifikasiList != null) {
                for (Notifikasi listNotif : notifikasiList) {
                    setNotifikasi(listNotif);
                }
            }
            return "view_notifikasi";
        }
    }

    public String viewNotifikasiSppd(){

        Sppd sppd = getSppd();
        SppdPerson sppdPerson ;
        List<SppdPerson> sppdPersons = new ArrayList<SppdPerson>();
        String sppdId = sppd.getSppdId();

        List<Sppd> sppdList = new ArrayList<Sppd>();
        sppdPerson = new SppdPerson();
        sppdPerson.setSppdId(sppdId);
        if (sppdPerson != null){
            try {
                sppdList = sppdBoProxy.getByCriteria(sppd);
                if(CommonUtil.roleAsLogin().equalsIgnoreCase("ADMIN")){
                    sppdPersons = sppdBoProxy.getComboSppdPerson(sppdId);
                }else{
                    sppdPersons = sppdBoProxy.getComboSppdApproveBawahan(sppdId);
                }
                setSppdPerson(sppdPerson);
                if(sppdList.size() != 0){
                    for (Sppd sppd1 : sppdList) {
                        setSppd(sppd1);
                    }
                }else{
                    Sppd sppd2 = new Sppd();
                    sppd2.setSppdId(sppdId);
                    setSppd(sppd2);
                }
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                return ERROR;
            }
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPerson");
        session.setAttribute("listOfResultPerson", sppdPersons);
        return "approval_atasan_sppd";
    }


    public String readNotification(String notifId){
        logger.info("[TrainingAction.readNotification] start process >>>");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setNotifId(notifId);
        List<Notifikasi> listOfNotifikasi = new ArrayList<Notifikasi>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        try {
            listOfNotifikasi =  notifikasiBo.getByCriteria(notifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBo.saveErrorMessage(e.getMessage(), "trainingBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.readNotification] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.readNotification] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        if (listOfNotifikasi != null){
            for (Notifikasi listNotif :listOfNotifikasi){
                if (notifId.equalsIgnoreCase(listNotif.getNotifId())){
                    listNotif.setRead("N");
                    listNotif.setLastUpdate(updateTime);
                    listNotif.setLastUpdateWho(userLogin);
                    listNotif.setAction("U");
                    try {
                        notifikasiBo.setReadNotif(listNotif);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = notifikasiBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
                        } catch (GeneralBOException e1) {
                            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
                        }
                        logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
                    }
                }
            }
        }
        logger.info("[TrainingAction.readNotification] start process >>>");
        return "00";
    }
    public List<Notifikasi> searchApproval(){
        String pesan="";
        int nomor=1;
        List<Notifikasi> result= new ArrayList<>();
        Notifikasi res = new Notifikasi();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        List<TrainingPerson> listOfResult = new ArrayList<TrainingPerson>();
        List<IjinKeluar> listOfResultIK = new ArrayList<IjinKeluar>();
        List<IjinKeluar> listOfResultIKK = new ArrayList<IjinKeluar>();
        List<Lembur> listOfResultLB = new ArrayList<Lembur>();
        List<AbsensiPegawai> listOfResultABS = new ArrayList<AbsensiPegawai>();
        List<CutiPegawai> listOfResultCP = new ArrayList<CutiPegawai>();
        List<Indisipliner> listOfResultID = new ArrayList<Indisipliner>();
        List<RekruitmenPabrikDetail> listOfResultRPD = new ArrayList<RekruitmenPabrikDetail>();
        List<SppdPerson> sppdPersons = new ArrayList<SppdPerson>();

            /*try {
                sppdList = sppdBoProxy.getByCriteria(sppd);
                sppdPersons = sppdBoProxy.getComboSppdPerson(sppdId, personId);
                setSppdPerson(sppdPerson);
                if(sppdList != null){
                    for (Sppd sppd1 : sppdList) {
                        setSppd(sppd1);
                    }
                }
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                return ERROR;
            }*/

            try {
                TrainingPerson trainingPerson = new TrainingPerson();
                trainingPerson.setFlag("Y");
                listOfResult = notifikasiBo.searchTrainingPerson(trainingPerson);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiAction.searchApproval");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.searchApproval] Error when saving error,", e1);
                }
                logger.error("[NotifikasiAction.searchApproval] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            try {
                IjinKeluar ijinKeluar =new IjinKeluar();
                ijinKeluar.setFlag("Y");
                ijinKeluar.setFrom("ijinKeluar");
                listOfResultIK = notifikasiBo.searchIjinKeluarPerson(ijinKeluar);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiAction.searchApproval");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.searchApproval] Error when saving error,", e1);
                }
                logger.error("[NotifikasiAction.searchApproval] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            }
            /*try {
                AbsensiPegawai absensiPegawai = new AbsensiPegawai();
                absensiPegawai.setFlag("Y");
                listOfResultABS = notifikasiBo.searchAbsensiPegawaiPerson(absensiPegawai);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiAction.searchApproval");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.searchApproval] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[NotifikasiAction.searchApproval] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return ERROR;
            }*/
            try {
                IjinKeluar ijinKeluarKantor =new IjinKeluar();
                ijinKeluarKantor.setFlag("Y");
                ijinKeluarKantor.setFrom("ijinKeluarKantor");
                listOfResultIKK = notifikasiBo.searchIjinKeluarPerson(ijinKeluarKantor);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiAction.searchApproval");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.searchApproval] Error when saving error,", e1);
                }
                logger.error("[NotifikasiAction.searchApproval] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            }
            try {
                Lembur lembur = new Lembur();
                lembur.setFlag("Y");
                listOfResultLB = notifikasiBo.searchLemburPerson(lembur);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBo.saveErrorMessage(e.getMessage(), "PersonalBO.searchApproval");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.searchApproval] Error when saving error,", e1);
                }
                logger.error("[NotifikasiAction.searchApproval] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            }
            try {
                Indisipliner indisipliner = new Indisipliner();
                indisipliner.setFlag("Y");
                listOfResultID = notifikasiBo.searchIndisiplinerPerson(indisipliner);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBo.saveErrorMessage(e.getMessage(), "PersonalBO.searchApproval");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.searchApproval] Error when saving error,", e1);
                }
                logger.error("[NotifikasiAction.searchApproval] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            }
            try {
                CutiPegawai cutiPegawai = new CutiPegawai();
                cutiPegawai.setFlag("Y");
                listOfResultCP = notifikasiBo.searchCutiPegawaiPerson(cutiPegawai);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiAction.searchApproval");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.searchApproval] Error when saving error,", e1);
                }
                logger.error("[NotifikasiAction.searchApproval] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            }
            try {
                RekruitmenPabrikDetail rekruitmenPabrikDetail = new RekruitmenPabrikDetail();
                rekruitmenPabrikDetail.setFlag("Y");
                listOfResultRPD = notifikasiBo.searchRekruitmenPabrikDetailPerson(rekruitmenPabrikDetail);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBo.saveErrorMessage(e.getMessage(), "NotifikasiAction.searchApproval");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiAction.searchApproval] Error when saving error,", e1);
                }
                logger.error("[NotifikasiAction.searchApproval] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            }

        if (listOfResult != null){
            nomor=1;
            for (TrainingPerson listPerson : listOfResult){
                res = new Notifikasi();
                res.setNotifId(String.valueOf(nomor)+".");
                if (listPerson.getApprovalFlag() == null ||("").equalsIgnoreCase(listPerson.getApprovalFlag())){
                    res.setNote("data training "+listPerson.getPersonName()+" dengan ID "+listPerson.getTrainingPersonId()+" belum di Approve Atasan");
                    nomor++;
                    result.add(res);
                }else if (listPerson.getApprovalBosFlag() == null ||("").equalsIgnoreCase(listPerson.getApprovalBosFlag())){
                    res.setNote("data training "+listPerson.getPersonName()+" dengan ID "+listPerson.getTrainingPersonId()+" belum di Approve Direktur Utama");
                    nomor++;
                    result.add(res);
                }else if (listPerson.getApprovalSdm() == null ||("").equalsIgnoreCase(listPerson.getApprovalSdm())){
                    res.setNote("data training "+listPerson.getPersonName()+" dengan ID "+listPerson.getTrainingPersonId()+" belum di Approve admin SDM");
                    nomor++;
                    result.add(res);
                }

            }
        }
        if (listOfResultCP != null){
            nomor=1;
            for (CutiPegawai listCutiPegawai : listOfResultCP){
                res = new Notifikasi();
                res.setNotifId(String.valueOf(nomor)+".");
                if (listCutiPegawai.getApprovalFlag() == null ||("").equalsIgnoreCase(listCutiPegawai.getApprovalFlag())){
                    res.setNote("data cuti "+listCutiPegawai.getNamaPegawai()+" dengan ID "+listCutiPegawai.getCutiPegawaiId()+" belum di Approve Atasan");
                    nomor++;
                    result.add(res);
                }/*else if (listCutiPegawai.getApprovalSdmFlag() == null ||("").equalsIgnoreCase(listCutiPegawai.getApprovalSdmFlag())){
                    res.setNote("data cuti "+listCutiPegawai.getNamaPegawai()+" dengan ID "+listCutiPegawai.getCutiPegawaiId()+" belum di Approve admin SDM");
                    nomor++;
                    result.add(res);
                }*/

            }
        }
        if (listOfResultABS != null){
            nomor=1;
            for (AbsensiPegawai listAbsensiPegawai : listOfResultABS){
                res = new Notifikasi();
                res.setNotifId(String.valueOf(nomor)+".");
                if (listAbsensiPegawai.getApprovalFlag() == null ||("").equalsIgnoreCase(listAbsensiPegawai.getApprovalFlag())){
                    res.setNote("data absensi "+listAbsensiPegawai.getNama()+" dengan ID "+listAbsensiPegawai.getAbsensiPegawaiId()+" belum di Approve Atasan");
                    nomor++;
                    result.add(res);
                }
            }
        }
        if (listOfResultID != null){
            nomor=1;
            for (Indisipliner listIndisipliner : listOfResultID){
                res = new Notifikasi();
                res.setNotifId(String.valueOf(nomor)+".");
                if (listIndisipliner.getApprovalFlag() == null ||("").equalsIgnoreCase(listIndisipliner.getApprovalFlag())){
                    res.setNote("data indisipliner "+listIndisipliner.getNama()+" dengan ID "+listIndisipliner.getIndisiplinerId()+" belum di Approve Atasan");
                    nomor++;
                    result.add(res);
                }
            }
        }
        if (listOfResultIK != null){
            nomor=1;
            for (IjinKeluar listIjinKeluar : listOfResultIK){
                res = new Notifikasi();
                res.setNotifId(String.valueOf(nomor)+".");
                if (listIjinKeluar.getApprovalFlag() == null ||("").equalsIgnoreCase(listIjinKeluar.getApprovalFlag())){
                    res.setNote("data dispensasi "+listIjinKeluar.getNamaPegawai()+" dengan ID "+listIjinKeluar.getIjinKeluarId()+" belum di Approve Atasan");
                    nomor++;
                    result.add(res);
                }else if (listIjinKeluar.getApprovalSdmFlag() == null ||("").equalsIgnoreCase(listIjinKeluar.getApprovalSdmFlag())){
                    res.setNote("data  dispensasi "+listIjinKeluar.getNamaPegawai()+" dengan ID "+listIjinKeluar.getIjinKeluarId()+" belum di Approve admin SDM");
                    nomor++;
                    result.add(res);
                }
            }
        }
        if (listOfResultIKK != null){
            nomor=1;
            for (IjinKeluar listIjinKeluarKantor : listOfResultIKK){
                res = new Notifikasi();
                res.setNotifId(String.valueOf(nomor)+".");
                if (listIjinKeluarKantor.getApprovalFlag() == null ||("").equalsIgnoreCase(listIjinKeluarKantor.getApprovalFlag())){
                    res.setNote("data ijin keluar kantor "+listIjinKeluarKantor.getNamaPegawai()+" dengan ID "+listIjinKeluarKantor.getIjinKeluarId()+" belum di Approve Atasan");
                    nomor++;
                    result.add(res);
                }
            }
        }
        if (listOfResultLB != null){
            nomor=1;
            for (Lembur listLembur : listOfResultLB){

                res = new Notifikasi();
                res.setNotifId(String.valueOf(nomor)+".");
                if (listLembur.getApprovalFlag() == null ||("").equalsIgnoreCase(listLembur.getApprovalFlag())){
                    res.setNote("data lembur "+listLembur.getPegawaiName()+" dengan ID "+listLembur.getLemburId()+" belum di Approve Atasan");
                    nomor++;
                    result.add(res);
                }
            }
        }
        if (listOfResultRPD != null){
            nomor=1;
            for (RekruitmenPabrikDetail rekruitmenPabrikDetail1 : listOfResultRPD){
                res = new Notifikasi();
                res.setNotifId(String.valueOf(nomor)+".");
                if (rekruitmenPabrikDetail1.getApprovalGmFlag() == null ||("").equalsIgnoreCase(rekruitmenPabrikDetail1.getApprovalGmFlag())){
                    res.setNote("data ijin keluar kantor "+rekruitmenPabrikDetail1.getNamaPegawai()+" dengan ID "+rekruitmenPabrikDetail1.getRekruitmenPabrikId()+" belum di GM");
                    nomor++;
                    result.add(res);
                }
            }
        }
            return result;
    }

    public String paging(){
        return "approve";
    }

    public String pagingMasterNotif(){
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