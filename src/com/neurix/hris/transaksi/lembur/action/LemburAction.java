package com.neurix.hris.transaksi.lembur.action;

import com.neurix.common.action.BaseMasterAction;
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
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.lembur.bo.LemburBo;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class LemburAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(LemburAction.class);
    private LemburBo lemburBoProxy;
    private PositionBagianBo positionBagianBoProxy;
    private Lembur lembur;
    private String nip;
    private String stTanggal;

    public PositionBagianBo getPositionBagianBoProxy() {
        return positionBagianBoProxy;
    }

    public void setPositionBagianBoProxy(PositionBagianBo positionBagianBoProxy) {
        this.positionBagianBoProxy = positionBagianBoProxy;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LemburAction.logger = logger;
    }

    public LemburBo getLemburBoProxy() {
        return lemburBoProxy;
    }

    public void setLemburBoProxy(LemburBo lemburBoProxy) {
        this.lemburBoProxy = lemburBoProxy;
    }

    public Lembur getLembur() {
        return lembur;
    }

    public void setLembur(Lembur lembur) {
        this.lembur = lembur;
    }
    
    public String paging(){
        return SUCCESS;
    }
    public Lembur init(String kode, String flag){
        logger.info("[LemburAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lembur> listOfResultLembur = (List<Lembur>) session.getAttribute("listOfResultLembur");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResultLembur != null){
                for (Lembur lembur: listOfResultLembur) {
                    if(kode.equalsIgnoreCase(lembur.getLemburId()) && flag.equalsIgnoreCase(lembur.getFlag())){
                        setLembur(lembur);
                        break;
                    }
                }
            } else {
                setLembur(new Lembur());
            }
            logger.info("[LemburAction.init] end process >>>");
        }
        return getLembur();
    }
    @Override
    public String add() {
        logger.info("[LemburAction.add] start process >>>");
        String role = CommonUtil.roleAsLogin();
        Lembur addLembur = new Lembur();
        List<Lembur> listOfsearchBiodata = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();

        if (("ADMIN").equalsIgnoreCase(role)||("Admin Bagian").equalsIgnoreCase(role)){

        }else{
            Biodata searchBiodata = new Biodata();
            String user = CommonUtil.userIdLogin();
            addLembur.setNip(user);
            searchBiodata.setNip(user);
            listOfsearchBiodata = lemburBoProxy.getBiodatawithCriteria(user);

            session.removeAttribute("listOfResultLemburPersonil");
            session.setAttribute("listOfResultLemburPersonil", listOfsearchBiodata);
            List<Lembur> listOfResultLemburPersonil = (List<Lembur>) session.getAttribute("listOfResultLemburPersonil");

            if(listOfResultLemburPersonil != null){
                for (Lembur lembur: listOfResultLemburPersonil) {
                    addLembur.setNip(lembur.getNip());
                    addLembur.setPegawaiName(lembur.getPegawaiName());
                    addLembur.setPositionId(lembur.getPositionId());
                    addLembur.setDivisiId(lembur.getDivisiId());
                    addLembur.setGolonganId(lembur.getGolonganId());
                    addLembur.setStatusGiling(lembur.getStatusGiling());
                    addLembur.setTipePegawaiId(lembur.getTipePegawaiId());
                    addLembur.setBranchId(lembur.getBranchId());
                    addLembur.setStatusGiling(lembur.getStatusGiling());
                    addLembur.setSelf("Y");
                    break;
                }
            } else {
                setLembur(new Lembur());
            }
            setLembur(addLembur);
        }
        setAddOrEdit(true);
        setAdd(true);
        session.removeAttribute("listOfResultLembur");

        logger.info("[LemburAction.add] end process >>>");
        return "init_add";
    }
    /*public String add1() {
        logger.info("[AlatAction.add] start process >>>");
        Biodata searchBiodata = new Biodata();

        HttpSession session = ServletActionContext.getRequest().getSession();
        setAddOrEdit(true);
        setAdd(true);
        session.removeAttribute("listOfResultLembur");

        logger.info("[AlatAction.add] stop process >>>");
        return "init_add";
    }*/
    @Override
    public String edit() {
        logger.info("[LemburAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        Lembur editLembur = new Lembur();
        if(itemFlag != null){
            try {
                editLembur = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburAction.editLembur");
                } catch (GeneralBOException e1) {
                    logger.error("[LemburAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[LemburAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }
            if(editLembur != null) {
                setLembur(editLembur);
            } else {
                editLembur.setFlag(itemFlag);
                editLembur.setLemburId(itemId);
                setLembur(editLembur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editLembur.setLemburId(itemId);
            editLembur.setFlag(getFlag());
            setLembur(editLembur);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        setAddOrEdit(true);
        logger.info("[LemburAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[LemburAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        Lembur deleteLembur = new Lembur();
        if(itemFlag != null){
            try {
                deleteLembur = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburAction.delete");
                } catch (GeneralBOException e1) {
                    logger.error("[LemburAction.delete] Error when retrieving edit data,", e1);
                }
                logger.error("[LemburAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }
            if(deleteLembur != null) {
                setLembur(deleteLembur);
            } else {
                deleteLembur.setFlag(itemFlag);
                deleteLembur.setLemburId(itemId);
                setLembur(deleteLembur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteLembur.setLemburId(itemId);
            deleteLembur.setFlag(getFlag());
            setLembur(deleteLembur);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        setAddOrEdit(true);
        logger.info("[LemburAction.delete] end process >>>");
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

    @Override
    public String search() {
        logger.info("[LemburAction.search] start process >>>");
        List<Lembur> listOfsearchBiodata = new ArrayList();
        Lembur searchLembur = getLembur();
        List<Lembur> listOfSearchLembur = new ArrayList();
        List<Lembur> listOfSearchLemburBagian = new ArrayList();
        List<Lembur> listOfSearchLemburFinal = new ArrayList();
        String role = CommonUtil.roleAsLogin();
        if ("ADMIN".equalsIgnoreCase(role)||"Admin bagian".equalsIgnoreCase(role)){
        }
        else{
            searchLembur.setNip(CommonUtil.userIdLogin());
        }

        if(!("Admin Bagian").equalsIgnoreCase(CommonUtil.roleAsLogin())){
            listOfSearchLembur = lemburBoProxy.getByCriteria(searchLembur);
        }else{
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

            positionBagian searchBagian = new positionBagian();
            searchBagian.setBagianName(CommonUtil.userLogin());
            List<positionBagian> positionBagianList = positionBagianBoProxy.getByCriteria(searchBagian);
            for (positionBagian bagian : positionBagianList){
                List<Lembur> lemburList ;
                List<Biodata> biodataList = biodataBo.getBiodataByBagian(null,null,bagian.getBagianId(),null);
                for (Biodata biodata : biodataList){
                    if(!("").equalsIgnoreCase(searchLembur.getNip())){
                        if (biodata.getNip().equalsIgnoreCase(searchLembur.getNip())){
                            lemburList = lemburBoProxy.getByCriteria(searchLembur);

                            listOfSearchLembur.addAll(lemburList);
                        }
                    }else{
                        searchLembur.setNip(biodata.getNip());
                        lemburList = lemburBoProxy.getByCriteria(searchLembur);
                        listOfSearchLembur.addAll(lemburList);
                        searchLembur.setNip("");
                    }
                }
            }
            Comparator<Lembur> comparator = new Comparator<Lembur>() {
                @Override
                public int compare(Lembur left, Lembur right) {
                    String awal =left.getLemburId().replaceAll("[a-zA-Z]", "");
                    String akhir =right.getLemburId().replaceAll("[a-zA-Z]", "");
                    Long angka1 = Long.parseLong(awal);
                    Long angka2 = Long.parseLong(akhir);
                    return (int) (angka2-angka1);
                }
            };
            Collections.sort(listOfSearchLembur, comparator);
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultLembur");
        session.setAttribute("listOfResultLembur", listOfSearchLembur);
        logger.info("[AlatAction.search] end process <<<");
        return SUCCESS;
    }
    public String refreshLembur(String nip,String stTanggal,String jamAwal,String jamAkhir) {
        logger.info("[LemburAction.refreshLembur] start process >>>");
        String status;
        Date tanggal = CommonUtil.convertStringToDate(stTanggal);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        try {
            status=absensiBo.refreshDataLembur(nip,tanggal,jamAwal,jamAkhir);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburBO.refreshLembur");
            } catch (GeneralBOException e1) {
                logger.error("[LemburAction.refreshLembur] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LemburAction.refreshLembur] Error when searching lembur by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        logger.info("[LemburAction.refreshLembur] end process <<<");
        return status;
    }
    public String sesuaikanLembur(String nip,String stTanggal,String jamAwal,String jamAkhir,String keterangan) {
        logger.info("[LemburAction.sesuaikanLembur] start process >>>");
        String status;
        Date tanggal = CommonUtil.convertStringToDate(stTanggal);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        try {
            status=absensiBo.sesuaikanDataLembur(nip,tanggal,jamAwal,jamAkhir,keterangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburBO.refreshLembur");
            } catch (GeneralBOException e1) {
                logger.error("[LemburAction.sesuaikanLembur] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LemburAction.sesuaikanLembur] Error when searching lembur by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        logger.info("[LemburAction.sesuaikanLembur] end process <<<");
        return status;
    }

    public String addAbsensiLembur(String nip,String stTanggal,String jamAwal,String jamAkhir,String pengajuan) {
        logger.info("[LemburAction.addAbsensiLembur] start process >>>");
        String status;
        Date tanggal = CommonUtil.convertStringToDate(stTanggal);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        try {
            status=absensiBo.addAbsensiLembur(nip,tanggal,jamAwal,jamAkhir,pengajuan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburBO.addAbsensiLembur");
            } catch (GeneralBOException e1) {
                logger.error("[LemburAction.addAbsensiLembur] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LemburAction.addAbsensiLembur] Error when searching lembur by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        logger.info("[LemburAction.addAbsensiLembur] end process <<<");
        return status;
    }
    @Override
    public String initForm() {
        logger.info("[lemburAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultLembur");
        logger.info("[lemburAction.initForm] end process >>>");
        return INPUT;
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
        logger.info("[AlatAction.saveAdd] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        Lembur lembur = getLembur();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dateStart = CommonUtil.convertToDate(lembur.getStTanggalAwal());
        java.sql.Date dateEnd = CommonUtil.convertToDate(lembur.getStTanggalAkhir());
        if (lembur.getLamaJam()>20){
            if (lembur.getLamaJam()>100){
                lembur.setLamaJam(lembur.getLamaJam()/100);
            }else{
                lembur.setLamaJam(lembur.getLamaJam()/10);
            }
        }
        lembur.setTanggalAwal(dateStart);
        lembur.setTanggalAkhir(dateEnd);
        lembur.setCreatedWho(userLogin);
        lembur.setLastUpdate(updateTime);
        lembur.setCreatedDate(updateTime);
        lembur.setLastUpdateWho(userLogin);
        lembur.setAction("C");
        lembur.setFlag("Y");
        lembur.setApprovalFlag("N");

        try {
            List<Notifikasi> notifCuti = lemburBoProxy.saveAddLembur(lembur);
            for (Notifikasi notifikasi : notifCuti ){
                notifikasiBo.sendNotif(notifikasi);
            }
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "lemburBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[lemburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[lemburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultLembur");

        logger.info("[lemburAction.saveAdd] end process >>>");
        return "success_save_add";
    }
    public List initComboPersonil(String query, String branchId) {
        logger.info("[LemburAction.initComboPersonil] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        try {
            listOfUser = biodataBo.getListOfPersonil(query,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "biodataBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LemburAction.initComboPersonil] Error when saving error,", e1);
            }
            logger.error("[LemburAction.initComboPersonil] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }
        logger.info("[LemburAction.initComboPersonil] end process <<<");
        return listOfUser;
    }
    public String saveApprove(String LemburId, String statusApprove, String who,String nip,String tglAwal,String tglAkhir,String lama,String keterangan,String jamAwal,String jamAkhir){
        logger.info("[LemburAction.saveEdit] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        java.sql.Date dateStart = CommonUtil.convertToDate(tglAwal);
        java.sql.Date dateEnd = CommonUtil.convertToDate(tglAkhir);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LemburBo lemburBo = (LemburBo) ctx.getBean("lemburBoProxy");
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        Lembur editLembur = new Lembur();
        editLembur.setLemburId(LemburId);
        if(who.equals("atasan")){
            if(statusApprove.equals("Y")){
                editLembur.setApprovalFlag(statusApprove);
            }else{
                editLembur.setApprovalFlag("N");
            }
        }
        if (!("").equalsIgnoreCase(keterangan)){
            editLembur.setNotApprovalNote(keterangan);
        }
        if (lama == null){
            lama= String.valueOf(0);
        }
        editLembur.setTmpApprove(who);
        editLembur.setNip(nip);
        editLembur.setTanggalAwalSetuju(dateStart);
        editLembur.setTanggalAkhirSetuju(dateEnd);
        editLembur.setStTanggalAwal(tglAwal);
        editLembur.setStTanggalAkhir(tglAkhir);
        editLembur.setJamAwal(jamAwal);
        editLembur.setJamAkhir(jamAkhir);
        editLembur.setLamaJam(Double.valueOf(lama));
        editLembur.setLastUpdateWho(userLogin);
        editLembur.setLastUpdate(updateTime);
        editLembur.setAction("U");
        editLembur.setFlag("Y");

        try {
            notifikasiList = lemburBo.saveApprove(editLembur);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "lemburBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[LemburAction.saveApprove] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LemburAction.saveApprove] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        for (Notifikasi notifikasi : notifikasiList){
            notifikasiBo.sendNotif(notifikasi);
        }

        logger.info("[LemburAction.saveApprove] end process <<<");

        return "success_save_edit";
    }

    public String testTanggal (String stTanggalAwal,String stTanggalAkhir,String nip){
        logger.info("[LemburAction.testTanggal] start process >>>");
        String status="";
        if (!("").equalsIgnoreCase(stTanggalAwal)&&!("").equalsIgnoreCase(stTanggalAkhir)&&!("").equalsIgnoreCase(nip)) {
            try {
                Date tanggalAwal = CommonUtil.convertToDate(stTanggalAwal);
                Date tanggalAkhir = CommonUtil.convertToDate(stTanggalAkhir);
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                LemburBo lemburBo = (LemburBo) ctx.getBean("lemburBoProxy");
                status = lemburBo.testTanggal(tanggalAwal,tanggalAkhir,nip);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[LemburAction.approveAtasan] Error when retrieving edit data,", e1);
                }
                logger.error("[LemburAction.approveAtasan] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            }
        }

        return status;
    }


    public List<Lembur> approveAtasan(String lemburId) {
        logger.info("[LemburAction.approveAtasan] start process >>>");
        String itemId = lemburId;
        String itemFlag = "Y";
        List<Lembur> lemburList = new ArrayList<Lembur>();

        Lembur editLembur = new Lembur();

        if (itemFlag != null) {
            try {
                editLembur = init(itemId, itemFlag);
                lemburList.add(editLembur);

            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[LemburAction.approveAtasan] Error when retrieving edit data,", e1);
                }
                logger.error("[LemburAction.approveAtasan] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            }

            if (editLembur != null) {
                setLembur(editLembur);
            } else {
                editLembur.setFlag(itemFlag);
                editLembur.setLemburId(itemId);
                setLembur(editLembur);
                addActionError("Error, Unable to find data with id = " + itemId);
            }
        }
        setAddOrEdit(true);
        logger.info("[LemburAction.edit] end process >>>");
        return lemburList;
    }
    public String saveEdit(){
        logger.info("[LemburAction.saveEdit] start process >>>");
        Lembur editLembur = getLembur();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dateStart = CommonUtil.convertToDate(editLembur.getStTanggalAwal());
        java.sql.Date dateEnd = CommonUtil.convertToDate(editLembur.getStTanggalAkhir());
        editLembur.setTanggalAwal(dateStart);
        editLembur.setTanggalAkhir(dateEnd);
        editLembur.setLastUpdateWho(userLogin);
        editLembur.setLastUpdate(updateTime);
        editLembur.setAction("U");
        editLembur.setFlag("Y");
        try {
            lemburBoProxy.saveEdit(editLembur);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[LemburAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LemburAction.saveEdit] Error when editing item lembur," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[LemburAction.saveEdit] end process <<<");

        return "success_save_edit";
    }
    public String saveDelete(){
        logger.info("[LemburAction.saveDelete] start process >>>");
        Lembur deleteLembur = getLembur();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        deleteLembur.setLastUpdateWho(userLogin);
        deleteLembur.setLastUpdate(updateTime);
        deleteLembur.setAction("U");
        deleteLembur.setFlag("N");
        try {
            lemburBoProxy.saveEdit(deleteLembur);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[LemburAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LemburAction.saveDelete] Error when editing item lembur," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[LemburAction.saveDelete] end process <<<");

        return "success_save_delete";
    }
    public String calcJamLembur(String nip,String tglAwal,String tglAkhir,String jamAwal,String jamAkhir) throws ParseException {
        logger.info("[LemburAction.calcJamLembur] start process >>>");
        String hariKerja ="hari_libur";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LiburBo liburBo = (LiburBo) ctx.getBean("liburBoProxy");
        JamKerjaBo jamKerjaBo = (JamKerjaBo) ctx.getBean("jamKerjaBoProxy");
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

        Double hasil = (double) 0;
        String sHasil;
        String sJamKerjaAwalDb="",sJamKerjaAkhirDb="";
        int iJamAwalKerja=Integer.parseInt(jamAwal.replace(":",""));
        int iJamAkhirKerja=Integer.parseInt(jamAkhir.replace(":",""));
        int iJamAwalDb = 0,iJamAkhirDb=0;
        Date startDate = CommonUtil.convertToDate(tglAwal);
        Date endDate = CommonUtil.convertToDate(tglAkhir);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DATE,1);
        java.util.Date date;

        for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int hari = c.get(Calendar.DAY_OF_WEEK);
            if (hari>1 && hari<7) {
                hariKerja = "hari_kerja";
            }
            Timestamp tanggal = new Timestamp(date.getTime());
            Libur libur = new Libur();
            libur.setTanggal(tanggal);
            libur.setFlag("Y");
            List<Libur> liburList = liburBo.getByCriteria(libur);
            if (liburList.size() != 0) {
                hariKerja = "hari_libur";
            }
        }
        Biodata searchBiodata = new Biodata();
        searchBiodata.setNip(nip);
        searchBiodata.setFlag("Y");
        Biodata result = biodataBo.getShift(searchBiodata);
        if (("Y").equalsIgnoreCase(result.getShift())){
            hariKerja="hari_libur";
        }

        if (hariKerja=="hari_kerja"){
            JamKerja search = new JamKerja();
            search.setFlag("Y");
            List<JamKerja> jamKerjaList = jamKerjaBo.getByCriteria(search);
            for (JamKerja jamKerja : jamKerjaList){
                sJamKerjaAwalDb=jamKerja.getJamAwalKerja();
                sJamKerjaAkhirDb=jamKerja.getJamAkhirKerja();
                iJamAwalDb=Integer.parseInt(sJamKerjaAwalDb.replace(":",""));
                iJamAkhirDb=Integer.parseInt(sJamKerjaAkhirDb.replace(":",""));
                break;
            }
            if (iJamAwalKerja<iJamAwalDb){
                hasil=hasil+SubtractJamAwalDanJamAkhir (jamAwal,sJamKerjaAwalDb,"positif");
                if (iJamAkhirKerja>iJamAkhirDb){
                    hasil=hasil+SubtractJamAwalDanJamAkhir (sJamKerjaAkhirDb,jamAkhir,"positif");
                }
            }
            if (iJamAwalKerja>=iJamAkhirDb){
                hasil=hasil+SubtractJamAwalDanJamAkhir (jamAwal,jamAkhir,"positif");
            }
        }else{
            hasil=SubtractJamAwalDanJamAkhir (jamAwal,jamAkhir,"positif");
        }
        sHasil = hasil.toString();
        return sHasil;
    }

    public Double SubtractJamAwalDanJamAkhir (String jamAwal,String jamAkhir,String status) throws ParseException {
        java.text.DateFormat df = new java.text.SimpleDateFormat("dd:MM:yyyy HH:mm");
        java.util.Date date1 = df.parse("01:01:2000 "+jamAwal);
        java.util.Date date2 = df.parse("01:01:2000 "+jamAkhir);
        long diff = date2.getTime() - date1.getTime();
        if (diff<0&&status.equalsIgnoreCase("positif")){
            date2 = df.parse("02:01:2000 "+jamAkhir);
            diff = date2.getTime() - date1.getTime();
        }
        int timeInSeconds = (int) (diff / 1000);
        int hours, minutes;
        hours = timeInSeconds / 3600;
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        double hasil=hours;
        if (minutes<15){hasil=hasil+0;}
        else if (minutes<30){hasil=hasil+0.25;}
        else if (minutes<45){hasil=hasil+0.50;}
        else if (minutes<60){hasil=hasil+0.75;}
        return hasil;
    }
    public Double cekLembur (String nip,String stTanggal){
        Double hasil= (double) 0;
        Date tanggal = CommonUtil.convertStringToDate(stTanggal);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LemburBo lemburBo= (LemburBo) ctx.getBean("lemburBoProxy");
        List<Lembur> lemburList = lemburBo.getCekLembur(nip,tanggal);
        for (Lembur lembur : lemburList){
            hasil = hasil+lembur.getLamaJam();
        }
        return hasil;
    }

}
