package com.neurix.hris.transaksi.absensi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.download.excel.CellDetail;
import com.neurix.common.download.excel.DownloadUtil;
import com.neurix.common.download.excel.RowData;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.department.bo.DepartmentBo;
import com.neurix.hris.master.department.model.Department;
import com.neurix.hris.master.jamkerja.bo.JamKerjaBo;
import com.neurix.hris.master.jamkerja.model.JamKerja;
import com.neurix.hris.master.libur.bo.LiburBo;
import com.neurix.hris.master.libur.model.Libur;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.model.PositionBagian;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.model.*;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.indisipliner.bo.IndisiplinerBo;
import com.neurix.hris.transaksi.indisipliner.model.Indisipliner;
import com.neurix.hris.transaksi.lembur.bo.LemburBo;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.sppd.bo.SppdBo;
import com.neurix.hris.transaksi.sppd.model.SppdPerson;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.io.*;
import java.math.BigDecimal;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class AbsensiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(AbsensiAction.class);
    private AbsensiBo absensiBoProxy;
    private LiburBo liburBo;
    private Lembur lembur;
    private AbsensiPegawai absensiPegawai;
    private MesinAbsensi mesinAbsensi;
    private MesinAbsensiDetail mesinAbsensiDetail;
    private String tglFrom;
    private String tglTo;
    private String branchId;
    private String divisiId;
    private String bagian;
    private String nip;

    public Lembur getLembur() {
        return lembur;
    }

    public void setLembur(Lembur lembur) {
        this.lembur = lembur;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public MesinAbsensiDetail getMesinAbsensiDetail() {
        return mesinAbsensiDetail;
    }

    public void setMesinAbsensiDetail(MesinAbsensiDetail mesinAbsensiDetail) {
        this.mesinAbsensiDetail = mesinAbsensiDetail;
    }

    public LiburBo getLiburBo() {
        return liburBo;
    }

    public void setLiburBo(LiburBo liburBo) {
        this.liburBo = liburBo;
    }

    public MesinAbsensi getMesinAbsensi() {
        return mesinAbsensi;
    }

    public void setMesinAbsensi(MesinAbsensi mesinAbsensi) {
        this.mesinAbsensi = mesinAbsensi;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        AbsensiAction.logger = logger;
    }

    public AbsensiPegawai getAbsensiPegawai() {
        return absensiPegawai;
    }

    public void setAbsensiPegawai(AbsensiPegawai absensiPegawai) {
        this.absensiPegawai = absensiPegawai;
    }

    public AbsensiBo getAbsensiBoProxy() {
        return absensiBoProxy;
    }

    public void setAbsensiBoProxy(AbsensiBo absensiBoProxy) {
        this.absensiBoProxy = absensiBoProxy;
    }

    public String cekKoneksi(){
        return "sukses";
    }
    public List inquiry(String tanggalAwal,String tanggalAkhir) throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultMesinAbsensi");
        logger.info("[AbsensiAction.inquiry] start process >>>");
        List<MesinAbsensi> mesinAbsensiList= new ArrayList<>();
        List<MesinAbsensi> mesinAbsensiListFinal= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");

        Date startDate = CommonUtil.convertToDate(tanggalAwal);
        Date endDate = CommonUtil.convertToDate(tanggalAkhir);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DATE,1);
        java.util.Date date;
        Boolean awalTanggal = false;
        for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            if (date==startDate){
                awalTanggal=true;
            }
            try {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String tanggal = df.format(date);
                mesinAbsensiList = absensiBo.inquiry(tanggal,awalTanggal,"", "");
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.inquiry");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.search] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.search] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            mesinAbsensiListFinal.addAll(mesinAbsensiList);
        }
        session.setAttribute("listOfResultMesinAbsensi", mesinAbsensiListFinal);
        session.removeAttribute("listOfResultAbsensiFinal");
        return mesinAbsensiListFinal;
    }

    public void cekCronJob() throws Exception{
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");

        try {
            absensiBo.getDataInquiryForCronJob();
        } catch (GeneralBOException e1) {
            logger.error("[AbsensiBO.cekCronJob] Error when cronJob,", e1);
        }
    }

    @Override
    public String downloadPdf() {
        return null;
    }
    //    @Override
//    public String downloadXls() {
//        return null;
//    }
    @Override
    public String add() {
        logger.info("[absensiAction.add] start process >>>");
        AbsensiPegawai addAbsensiPegawai = new AbsensiPegawai();
        String branchId = CommonUtil.userBranchLogin();
        AbsensiPegawai data = new AbsensiPegawai();
        if (branchId!=null){
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        }else{
            data.setBranchId("");
        }

        setAbsensiPegawai(data);
        setAddOrEdit(true);
        setAdd(true);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultAbsensiPegawai");

        logger.info("[absensiAction.add] stop process >>>");
        return "init_add";
    }
    public String addTambahan() {
        logger.info("[absensiAction.addTambahan] start process >>>");
        AbsensiPegawai addAbsensiPegawai = new AbsensiPegawai();
        setAbsensiPegawai(addAbsensiPegawai);
        setAddOrEdit(true);
        setAdd(true);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultAbsensiPegawai");

        logger.info("[absensiAction.addTambahan] stop process >>>");
        return "init_add_tambahan";
    }
    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<>();
        AbsensiPegawai searchAbsensi = new AbsensiPegawai();
        searchAbsensi.setAbsensiPegawaiId(getId());
        searchAbsensi.setFlag(getFlag());
        absensiPegawaiList = absensiBoProxy.getByCriteria(searchAbsensi);
        for (AbsensiPegawai absensiPegawai : absensiPegawaiList){
            setAbsensiPegawai(absensiPegawai);
        }
        return "init_delete";
    }
    public String editAbsensi() {
        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<>();
        AbsensiPegawai searchAbsensi = new AbsensiPegawai();
        searchAbsensi.setAbsensiPegawaiId(getId());
        searchAbsensi.setFlag(getFlag());
        absensiPegawaiList = absensiBoProxy.getByCriteria(searchAbsensi);
        for (AbsensiPegawai absensiPegawai : absensiPegawaiList){
            setAbsensiPegawai(absensiPegawai);
        }
        return "init_edit";
    }
    public String saveEdit(){
        logger.info("[AbsensiAction.saveEdit] start process >>>");
        try {

            AbsensiPegawai deleteAbsensiPegawai = getAbsensiPegawai();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteAbsensiPegawai.setLastUpdate(updateTime);
            deleteAbsensiPegawai.setLastUpdateWho(userLogin);
            deleteAbsensiPegawai.setAction("U");
            deleteAbsensiPegawai.setFlag("Y");

            absensiBoProxy.saveEdit(deleteAbsensiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AbsensiAction.saveDelete] Error when editing item absensi," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AbsensiAction.saveDelete] end process <<<");

        return "success_save_edit";
    }
    public String saveDelete(){
        logger.info("[AbsensiAction.saveDelete] start process >>>");
        try {

            AbsensiPegawai deleteAbsensiPegawai = getAbsensiPegawai();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteAbsensiPegawai.setLastUpdate(updateTime);
            deleteAbsensiPegawai.setLastUpdateWho(userLogin);
            deleteAbsensiPegawai.setAction("U");
            deleteAbsensiPegawai.setFlag("N");

            absensiBoProxy.saveDelete(deleteAbsensiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AbsensiAction.saveDelete] Error when editing item absensi," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AbsensiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    @Override
    public String view() {
        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<>();
        AbsensiPegawai searchAbsensi = new AbsensiPegawai();
        searchAbsensi.setAbsensiPegawaiId(getId());
        searchAbsensi.setFlag(getFlag());

        absensiPegawaiList = absensiBoProxy.getByCriteria(searchAbsensi);

        for (AbsensiPegawai absensiPegawai : absensiPegawaiList){
            setAbsensiPegawai(absensiPegawai);
        }
        return "init_view";
    }
    public String addKeteranganById() {
        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<>();
        AbsensiPegawai searchAbsensi = new AbsensiPegawai();
        searchAbsensi.setAbsensiPegawaiId(getId());
        searchAbsensi.setFlag(getFlag());

        absensiPegawaiList = absensiBoProxy.getByCriteria(searchAbsensi);

        for (AbsensiPegawai absensiPegawai : absensiPegawaiList){
            setAbsensiPegawai(absensiPegawai);
        }
        return "init_add_keterangan";
    }

    public AbsensiPegawai init2(String kode, String flag) {
        logger.info("[AlatAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<AbsensiPegawai> listOfResultAbsensi = (List<AbsensiPegawai>) session.getAttribute("listOfResultAbsensi");

        if (kode != null && !"".equalsIgnoreCase(kode)) {
            if (listOfResultAbsensi != null) {
                for (AbsensiPegawai absensiPegawai : listOfResultAbsensi) {
                    if (kode.equalsIgnoreCase(absensiPegawai.getAbsensiPegawaiId()) && flag.equalsIgnoreCase(absensiPegawai.getFlag())) {
                        setAbsensiPegawai(absensiPegawai);
                        break;
                    }
                }
            } else {
                setAbsensiPegawai(new AbsensiPegawai());
            }
            logger.info("[AlatAction.init] end process >>>");
        }
        return getAbsensiPegawai();
    }

    public List<AbsensiPegawai> approveAtasan(String absensiPegawaiId) {
        logger.info("[AbsensiAction.approveAtasan] start process >>>");
        String itemId = absensiPegawaiId;
        String itemFlag = "Y";
        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<AbsensiPegawai>();
        List<Biodata> personList = new ArrayList<Biodata>();
        AbsensiPegawai editAbsensiPegawai = new AbsensiPegawai();
        if(itemFlag != null){
            try {
                editAbsensiPegawai = init2(itemId, itemFlag);
                absensiPegawaiList.add(editAbsensiPegawai);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getSppdByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.approveAtasan] Error when retrieving edit data,", e1);
                }
                logger.error("[AbsensiAction.approveAtasan] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            }
        }
        setAddOrEdit(true);
        logger.info("[CutiPegawaiAction.edit] end process >>>");
        return absensiPegawaiList;
    }

    @Override
    public String save() {
        return null;
    }

    public void saveAdd(){
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        List<AbsensiPegawai> listOfResult = new ArrayList<>();
        List<AbsensiOnCall> listOfResultOnCall = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        listOfResult = (List<AbsensiPegawai>) session.getAttribute("listOfResultAbsensiPegawai");
        listOfResultOnCall = (List<AbsensiOnCall>) session.getAttribute("listOfResultOnCall");

        if (listOfResult==null){
            listOfResult = new ArrayList<>();
        }
        if (listOfResultOnCall==null){
            listOfResultOnCall = new ArrayList<>();
        }

        AbsensiPegawai data = new AbsensiPegawai();
        data.setCreatedWho(userLogin);
        data.setLastUpdate(updateTime);
        data.setCreatedDate(updateTime);
        data.setLastUpdateWho(userLogin);
        data.setAction("C");
        data.setFlag("Y");

        absensiBo.saveAddAbsensi(listOfResult,listOfResultOnCall,data);

        session.removeAttribute("listOfResultAbsensiPegawai");
    }

    public void saveAddIndisipliner (String nip, String nama,String tipeIndisipliner,String stTanggal,String keteranganIndisipliner,String dampak,String tanggalAwalPantau,String tanggalAkhirPantau,String tanggalAwalBlokir,String tanggalAkhirBlokir){
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        Indisipliner saveIndisipliner = new Indisipliner();
        saveIndisipliner.setNip(nip);
        saveIndisipliner.setNama(nama);
        saveIndisipliner.setTipeIndisipliner(tipeIndisipliner);
        saveIndisipliner.setTanggal(CommonUtil.convertToDate(stTanggal));
        saveIndisipliner.setTanggalAwalPantau(CommonUtil.convertToDate(tanggalAwalPantau));
        saveIndisipliner.setTanggalAkhirPantau(CommonUtil.convertToDate(tanggalAkhirPantau));
        saveIndisipliner.setTanggalAwalBlokirAbsensi(CommonUtil.convertToDate(tanggalAwalBlokir));
        saveIndisipliner.setTanggalAkhirBlokirAbsensi(CommonUtil.convertToDate(tanggalAkhirBlokir));
        saveIndisipliner.setKeteranganPelanggaran(keteranganIndisipliner);
        saveIndisipliner.setDampak(dampak);
        saveIndisipliner.setCreatedDate(updateTime);
        saveIndisipliner.setLastUpdate(updateTime);
        saveIndisipliner.setFlag("Y");
        saveIndisipliner.setAction("C");
        saveIndisipliner.setCreatedWho(userLogin);
        saveIndisipliner.setLastUpdateWho(userLogin);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IndisiplinerBo indisiplinerBo = (IndisiplinerBo) ctx.getBean("indisiplinerBoProxy");
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        Biodata searchBiodata = new Biodata();
        searchBiodata.setNip(nip);
        searchBiodata.setFlag("Y");
        List<Biodata> biodataList = new ArrayList<>();
        biodataList = biodataBo.getByCriteria(searchBiodata);
        for (Biodata biodata:biodataList){
            saveIndisipliner.setDivisiId(biodata.getDivisi());
            saveIndisipliner.setBranchId(biodata.getBranch());
        }

        indisiplinerBo.saveAdd(saveIndisipliner);
    }
    public String saveApprove(String AbsensiPegawaiId, String statusApprove, String who,String nip){
        logger.info("[AbsensiAction.saveApprove] start process >>>");
        try {

            AbsensiPegawai editAbsensiPegawai = new AbsensiPegawai();

            editAbsensiPegawai.setAbsensiPegawaiId(AbsensiPegawaiId);
            if(who.equals("atasan")){
                if(statusApprove.equals("Y")){
                    editAbsensiPegawai.setApprovalFlag(statusApprove);
                }else{
                    editAbsensiPegawai.setApprovalFlag("N");
                    editAbsensiPegawai.setNotapprovalNote(statusApprove);
                }
            }
            editAbsensiPegawai.setNip(nip);
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editAbsensiPegawai.setUserIdActive(CommonUtil.userIdLogin());
            editAbsensiPegawai.setUserNameActive(CommonUtil.userLogin());
            editAbsensiPegawai.setLastUpdateWho(userLogin);
            editAbsensiPegawai.setLastUpdate(updateTime);
            editAbsensiPegawai.setAction("U");
            editAbsensiPegawai.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
            absensiBo.saveApprove(editAbsensiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiBO.saveApprove] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AbsensiBO.saveApprove] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AbsensiBO.saveApprove] end process <<<");

        return "success_save_edit";
    }

    public String saveAddKeterangan (String nip,String stTanggal,String keterangan, String flagUangMakan){
        logger.info("[AbsensiAction.saveAddKeterangan] start process >>>");
        String status ="";
        List<AbsensiPegawaiEntity> listOfAbsensiPegawai = new ArrayList<>();
        List<AbsensiPegawaiEntity> listOfResult = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        listOfAbsensiPegawai = (List<AbsensiPegawaiEntity>) session.getAttribute("listOfResultAbsensiFinal");
        session.removeAttribute("listOfResultAbsensiFinal");
        for (AbsensiPegawaiEntity absensiPegawaiEntity : listOfAbsensiPegawai){
            if (nip.equalsIgnoreCase(absensiPegawaiEntity.getNip())&&stTanggal.equalsIgnoreCase(absensiPegawaiEntity.getStTanggal())){
                absensiPegawaiEntity.setKeterangan(keterangan);
                absensiPegawaiEntity.setFlagUangMakan(flagUangMakan);
            }
            listOfResult.add(absensiPegawaiEntity);
            status="ok";
        }
        session.setAttribute("listOfResultAbsensiFinal", listOfResult);
        return status;
    }
    public String saveAddKeterangan (){
        logger.info("[AbsensiAction.saveAddKeterangan] start process >>>");
        AbsensiPegawai absensiPegawai = getAbsensiPegawai();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        absensiPegawai.setLastUpdateWho(userLogin);
        absensiPegawai.setLastUpdate(updateTime);
        absensiPegawai.setAction("U");
        absensiPegawai.setFlag("Y");
        try {
            absensiBoProxy.saveAddKeterangan(absensiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.saveAddKeterangan");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.saveAddKeterangan] Error when saving error,", e1);
            }
            logger.error("[AbsensiAction.saveAddKeterangan] Error when saving keterangan absensi with criteria," + "[" + logId + "] ", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving keterangan , please inform to your admin" );
        }
        return "success_save_add_keterangan";
    }
    public void saveTmp(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultAbsensiFinal");
        AbsensiPegawai absen = getAbsensiPegawai();
        String[] allData = absen.getCheckedValue().split(",");
        for (String anAllData : allData) {
            String[] parts = anAllData.split(":");
            String pin = parts[0];
            pin = pin.replaceAll("\\s+","");
            String stTanggal = parts[1];
            logger.info("[AbsensiAction.saveTmpAction] start process >>>");
            boolean flag = false;
            String tipeHari = "hari_kerja";
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            List<MesinAbsensi> listComboAbsensiPegawai = new ArrayList<>();
            listComboAbsensiPegawai = (List<MesinAbsensi>) session.getAttribute("listOfResultMesinAbsensi");
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            for (MesinAbsensi mesinAbsensi : listComboAbsensiPegawai) {
                if ((pin).equalsIgnoreCase(mesinAbsensi.getPin()) && mesinAbsensi.getStTanggal().equalsIgnoreCase(stTanggal)) {
                    Biodata searchBiodata = new Biodata();
                    searchBiodata.setNip(mesinAbsensi.getNip());
                    Biodata biodata = biodataBo.getShift(searchBiodata);

                    if (!"Y".equalsIgnoreCase(biodata.getShift())){
                        try {
                            AbsensiPegawai absensiPegawai = new AbsensiPegawai();
                            absensiPegawai.setJamMasuk(mesinAbsensi.getJamMasuk());
                            absensiPegawai.setJamKeluar(mesinAbsensi.getJamKeluar());
                            absensiPegawai.setTanggal(mesinAbsensi.getTanggal());
                            //search in libur
                            Libur searchLibur = new Libur();
                            searchLibur.setTanggal(new Timestamp(CommonUtil.convertStringToDate(mesinAbsensi.getStTanggal()).getTime()));
                            searchLibur.setFlag("Y");
                            List<Libur> liburList = new ArrayList<>();
                            LiburBo liburBo = (LiburBo) ctx.getBean("liburBoProxy");
                            liburList = liburBo.getByCriteria(searchLibur);
                            if (liburList.size() != 0) {
                                tipeHari = "hari_libur";
                            }
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(mesinAbsensi.getTanggal());
                            int hariSekarang = calendar.get(Calendar.DAY_OF_WEEK);
                            if (hariSekarang == 1 || hariSekarang == 7) {
                                tipeHari = "hari_libur";
                            }

                            absensiPegawai.setPin(mesinAbsensi.getPin());
                            absensiPegawai.setRealisasiJamLembur(mesinAbsensi.getRealisasiJamLembur());
                            absensiPegawai.setStatusAbsensi(mesinAbsensi.getStatus());
                            absensiPegawai.setNip(mesinAbsensi.getNip());
                            absensiPegawai.setStTanggal(mesinAbsensi.getStTanggal());
                            absensiPegawai.setNama(mesinAbsensi.getNama());
                            absensiPegawai.setStatusName(mesinAbsensi.getStatusName());
                            absensiPegawai.setTipePegawai(mesinAbsensi.getTipePegawai());
                            absensiPegawai.setStatusGiling(mesinAbsensi.getStatusGiling());
                            absensiPegawai.setJamMasukDb(mesinAbsensi.getJamMasukDb());
                            absensiPegawai.setJamPulangDb(mesinAbsensi.getJamPulangDb());


/*                        //SEARCH IN JADWAL SHIFT KERJA
                        List<PersonilPosition> personilPositionList = new ArrayList<>();
                        PersonilPosition searchPersonilPosition = new PersonilPosition();
                        searchPersonilPosition.setNip(mesinAbsensi.getNip());
                        searchPersonilPosition.setFlag("Y");
                        personilPositionList = biodataBo.getByCriteriaPersonilPosition(searchPersonilPosition);
                        if (personilPositionList.size()!=0){
                        }
*/

                            absensiBo.saveTmp(absensiPegawai, tipeHari);
                        } catch (GeneralBOException e) {
                            Long logId = null;
                            try {
                                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "absensiBoProxy.saveAdd");
                            } catch (GeneralBOException e1) {
                                logger.error("[AbsensiAction.saveAdd] Error when saving error,", e1);
                            }
                            logger.error("[AbsensiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                        }
                    } else {
                        try {
                            AbsensiPegawai absensiPegawai = new AbsensiPegawai();
                            absensiPegawai.setJamMasuk(mesinAbsensi.getJamMasuk());
                            absensiPegawai.setJamKeluar(mesinAbsensi.getJamKeluar());
                            absensiPegawai.setTanggal(mesinAbsensi.getTanggal());
                            //search in libur
                            Libur searchLibur = new Libur();
                            searchLibur.setTanggal(new Timestamp(CommonUtil.convertStringToDate(mesinAbsensi.getStTanggal()).getTime()));
                            searchLibur.setFlag("Y");
                            List<Libur> liburList = new ArrayList<>();
                            LiburBo liburBo = (LiburBo) ctx.getBean("liburBoProxy");
                            liburList = liburBo.getByCriteria(searchLibur);
                            if (liburList.size() != 0) {
                                tipeHari = "hari_libur";
                            }
                            AbsensiPegawai resultJadwal = absensiBo.getJadwalShiftKerja(mesinAbsensi.getNip(),mesinAbsensi.getTanggal());
                            if (resultJadwal.getJamMasuk() != null && resultJadwal.getJamPulang() != null) {
                                tipeHari="hari_kerja";
                            }else{
                                tipeHari="hari_libur";
                            }

                            absensiPegawai.setPin(mesinAbsensi.getPin());
                            absensiPegawai.setRealisasiJamLembur(mesinAbsensi.getRealisasiJamLembur());
                            absensiPegawai.setStatusAbsensi(mesinAbsensi.getStatus());
                            absensiPegawai.setNip(mesinAbsensi.getNip());
                            absensiPegawai.setStTanggal(mesinAbsensi.getStTanggal());
                            absensiPegawai.setNama(mesinAbsensi.getNama());
                            absensiPegawai.setStatusName(mesinAbsensi.getStatusName());
                            absensiPegawai.setTipePegawai(mesinAbsensi.getTipePegawai());
                            absensiPegawai.setStatusGiling(mesinAbsensi.getStatusGiling());
                            absensiPegawai.setJamMasukDb(mesinAbsensi.getJamMasukDb());
                            if (tipeHari.equalsIgnoreCase("hari_kerja")){
                                absensiPegawai.setJamPulangDb(mesinAbsensi.getJamPulangDb());
                            }else{
                                absensiPegawai.setJamPulangDb(mesinAbsensi.getJamMasuk());
                            }
                            absensiBo.saveTmp(absensiPegawai, tipeHari);
                        } catch (GeneralBOException e) {
                            Long logId = null;
                            try {
                                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "absensiBoProxy.saveAdd");
                            } catch (GeneralBOException e1) {
                                logger.error("[AbsensiAction.saveAdd] Error when saving error,", e1);
                            }
                            logger.error("[AbsensiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                        }
                    }
                }
            }
        }
    }
    public void saveTambahan(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PegawaiTambahanAbsensi> pegawaiTambahanAbsensiList = (List<PegawaiTambahanAbsensi>) session.getAttribute("listOfResultMesinAbsensi");
        for (PegawaiTambahanAbsensi pegawaiTambahanAbsensi : pegawaiTambahanAbsensiList) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            pegawaiTambahanAbsensi.setFlag("Y");
            pegawaiTambahanAbsensi.setAction("C");
            pegawaiTambahanAbsensi.setCreatedWho(CommonUtil.userIdLogin());
            pegawaiTambahanAbsensi.setLastUpdateWho(CommonUtil.userIdLogin());
            pegawaiTambahanAbsensi.setLastUpdate(updateTime);
            pegawaiTambahanAbsensi.setCreatedDate(updateTime);

            try {
                absensiBo.saveTambahan(pegawaiTambahanAbsensi);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "absensiBoProxy.saveAdd");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.saveAdd] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }
        }
    }
    public String searchStatusHari(String tanggal) throws ParseException {
        logger.info("[AbsensiAction.searchStatusHari] start process >>>");
        List<Libur> liburList = new ArrayList<>();
        Libur libur = new Libur();
        libur.setTanggal(CommonUtil.convertToTimestamp(tanggal));
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date today = df.parse(tanggal);
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if (day==1||day==7){
            return "hari_libur";
        }else{
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            LiburBo liburBo = (LiburBo) ctx.getBean("liburBoProxy");
            liburList = liburBo.getByCriteria(libur);

            if (liburList.size()!=0){
                return "hari_libur";
            }
            else {
                return "hari_kerja";
            }
        }
    }

    public List<AbsensiPegawai> searchAbsensiFinal(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        return (List<AbsensiPegawai>) session.getAttribute("listOfResultAbsensiPegawai");
    }

    public String goToInquiry(){
        AbsensiPegawai addAbsensiPegawai = new AbsensiPegawai();
        String unitId = CommonUtil.userBranchLogin();
//        Biodata data = new Biodata();
        if (unitId != null){
            addAbsensiPegawai.setBranchId(unitId);
        }else {
            addAbsensiPegawai.setBranchId("");
        }

        absensiPegawai = addAbsensiPegawai;
        setAbsensiPegawai(absensiPegawai);
        return "go_to_inquiry";
    }

    public String goToInquiryPegawaiTambahan(){
        return "go_to_inquiry_tambahan";
    }

    public String goToProses(){
        return "go_to_proses";
    }

    public Indisipliner addIndisipliner(String nip , String stTanggal){
        Biodata searchBiodata = new Biodata();
        Indisipliner result = new Indisipliner();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        searchBiodata.setNip(nip);
        searchBiodata.setFlag("Y");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        List<Biodata> biodataList = biodataBo.getByCriteria(searchBiodata);
        for (Biodata biodata : biodataList){
            result.setBranchId(biodata.getBranch());
            result.setDivisiId(biodata.getDivisi());
            result.setPositionId(biodata.getPositionId());
            result.setNama(biodata.getNamaPegawai());
        }
        result.setStTanggal(stTanggal);
        Date tanggalAbsen = CommonUtil.convertToDate(stTanggal);
        Calendar tanggalAwalPantau = Calendar.getInstance();
        Calendar tanggalAkhirPantau = Calendar.getInstance();
        Calendar tanggalAwalBlokir = Calendar.getInstance();
        Calendar tanggalAkhirBlokir = Calendar.getInstance();
        tanggalAwalPantau.setTime(tanggalAbsen);
        tanggalAkhirPantau.setTime(tanggalAbsen);
        tanggalAwalBlokir.setTime(tanggalAbsen);
        tanggalAkhirBlokir.setTime(tanggalAbsen);
        tanggalAwalPantau.add(Calendar.DATE, 1);
        tanggalAkhirPantau.add(Calendar.MONTH, 6);
        tanggalAwalBlokir.add(Calendar.DATE, 1);
        tanggalAkhirBlokir.add(Calendar.DATE, 4);

        result.setTanggalAwalPantau(new java.sql.Date(tanggalAwalPantau.getTimeInMillis()));
        result.setTanggalAkhirPantau(new java.sql.Date(tanggalAkhirPantau.getTimeInMillis()));
        result.setTanggalAwalBlokirAbsensi(new java.sql.Date(tanggalAwalBlokir.getTimeInMillis()));
        result.setTanggalAkhirBlokirAbsensi(new java.sql.Date(tanggalAkhirBlokir.getTimeInMillis()));

        result.setStTanggalAwalPantau(df.format(result.getTanggalAwalPantau()));
        result.setStTanggalAkhirPantau(df.format(result.getTanggalAkhirPantau()));
        result.setStTanggalAwalBlokirAbsensi(df.format(result.getTanggalAwalBlokirAbsensi()));
        result.setStTanggalAkhirBlokirAbsensi(df.format(result.getTanggalAkhirBlokirAbsensi()));

        IndisiplinerBo indisiplinerBo = (IndisiplinerBo) ctx.getBean("indisiplinerBoProxy");
        Indisipliner searchIndisipliner = new Indisipliner();
        Date tanggal = CommonUtil.convertToDate(stTanggal);
        String tipeIndisipliner="";
        List<Indisipliner> indisiplinerList = indisiplinerBo.getListIndisiplinerByPersonAndTanggal(nip,tanggal);
        if (indisiplinerList.size()!=0){
            for (Indisipliner indisipliner : indisiplinerList){
                switch (indisipliner.getTipeIndisipliner()) {
                    case "":
                        result.setTipeIndisipliner("SP0");
                        result.setDampak("Blokir absensi, ybs tidak dapat melakukan absen finger print");
                        break;
                    case "SP0":
                        result.setTipeIndisipliner(("SP1"));
                        result.setDampak("Tidak mendapatkan kenaikan berkala selama 1 tahun dan nilai prestasinya 0");
                        break;
                    case "SP1":
                        result.setTipeIndisipliner(("SP1"));
                        result.setDampak("Tidak mendapatkan kenaikan berkala selama 1 tahun dan nilai prestasinya 0");
                        break;
                }
            }
        } else{
            result.setTipeIndisipliner("SP0");
            result.setDampak("Blokir absensi, ybs tidak dapat melakukan absen finger print");
        }
        return result;
    }
    public Indisipliner addKeterangan(String nip , String stTanggal){
        Biodata searchBiodata = new Biodata();
        Indisipliner result = new Indisipliner();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        searchBiodata.setNip(nip);
        searchBiodata.setFlag("Y");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        List<Biodata> biodataList = biodataBo.getByCriteria(searchBiodata);
        for (Biodata biodata : biodataList){
            result.setBranchId(biodata.getBranch());
            result.setDivisiId(biodata.getDivisi());
            result.setPositionId(biodata.getPositionId());
            result.setNama(biodata.getNamaPegawai());
        }
        result.setStTanggal(stTanggal);
        return result;
    }
    @Override
    public String search() {
        logger.info("[AbsensiAction.search] start process >>>");
        AbsensiPegawai searchAbsensiPegawai = getAbsensiPegawai();
        List<AbsensiPegawai> listOfSearchAbsensiPegawai = new ArrayList();
        String branchId = CommonUtil.userBranchLogin();
        searchAbsensiPegawai.setBranchIdUser(branchId);

        try {
            listOfSearchAbsensiPegawai = absensiBoProxy.getByCriteria(searchAbsensiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AbsensiAction.search] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultAbsensi");
        session.setAttribute("listOfResultAbsensi", listOfSearchAbsensiPegawai);

        logger.info("[AbsensiAction.search] end process <<<");

        return SUCCESS;
    }

    public List<MesinAbsensiDetail> searchListAbsensi(String pin , String tanggal) {
        MesinAbsensiDetail mesinAbsensiDetail = new MesinAbsensiDetail();
        mesinAbsensiDetail.setPin(pin);
        logger.info("[AbsensiAction.searchListAbsensi] start process >>>");
        List<MesinAbsensiDetail> listOfMesinAbsensiDetail = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        try {
            listOfMesinAbsensiDetail = absensiBo.getByCriteriaAbsensiDetail(mesinAbsensiDetail,tanggal);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[AbsensiAction.searchListAbsensi] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[AbsensiAction.searchListAbsensi] end process <<<");
        return listOfMesinAbsensiDetail;
    }
    public List<Indisipliner> searchListIndisipliner(String nip , String stTanggal) {
        Indisipliner indisipliner = new Indisipliner();
        indisipliner.setNip(nip);
        indisipliner.setTanggal(CommonUtil.convertToDate(stTanggal));
        logger.info("[AbsensiAction.searchListIndisipliner] start process >>>");
        List<Indisipliner> indisiplinerList = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IndisiplinerBo indisiplinerBo = (IndisiplinerBo) ctx.getBean("indisiplinerBoProxy");
        try {
            indisiplinerList = indisiplinerBo.ListOfIndisipliner(indisipliner);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[AbsensiAction.searchListAbsensi] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[AbsensiAction.searchListAbsensi] end process <<<");

        return indisiplinerList;
    }
    public List<MesinAbsensiDetail> searchListAbsensiDetail(String pin , String tanggal) {
        MesinAbsensiDetail mesinAbsensiDetail = new MesinAbsensiDetail();
        mesinAbsensiDetail.setPin(pin);
        logger.info("[AbsensiAction.searchListAbsensi] start process >>>");
        List<MesinAbsensiDetail> listOfMesinAbsensiDetail = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        try {
            listOfMesinAbsensiDetail = absensiBo.getByCriteriaAbsensiDetailAll(mesinAbsensiDetail,tanggal);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[AbsensiAction.searchListAbsensi] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[AbsensiAction.searchListAbsensi] end process <<<");

        return listOfMesinAbsensiDetail;
    }
    public List<Lembur> searchListAbsensiLembur(String tanggal, String Nip) {
        Lembur lembur = new Lembur();
        lembur.setNip(Nip);
        logger.info("[AbsensiAction.searchListAbsensi] start process >>>");
        List<Lembur> lemburList = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LemburBo lemburBo = (LemburBo) ctx.getBean("lemburBoProxy");
        try {
            lemburList = lemburBo.getByCriteriaForAbsensi(lembur,tanggal);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[AbsensiAction.searchListAbsensi] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[AbsensiAction.searchListAbsensi] end process <<<");
        return lemburList;
    }

    public List<IjinKeluar> searchListAbsensiIjinKeluar(String tanggal, String Nip) {
        IjinKeluar ijinKeluar = new IjinKeluar();
        ijinKeluar.setNip(Nip);
        logger.info("[AbsensiAction.searchListAbsensi] start process >>>");
        List<IjinKeluar> ijinKeluarList = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        try {
            ijinKeluarList = ijinKeluarBo.getByCriteriaForAbsensi(ijinKeluar,tanggal);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[AbsensiAction.searchListAbsensi] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[AbsensiAction.searchListAbsensi] end process <<<");
        return ijinKeluarList;
    }
    public List<CutiPegawai> searchListAbsensiCuti(String tanggal, String Nip) {
        CutiPegawai cutiPegawai = new CutiPegawai();
        cutiPegawai.setNip(Nip);
        logger.info("[AbsensiAction.searchListAbsensiCuti] start process >>>");
        List<CutiPegawai> cutiPegawaiList = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        try {
            cutiPegawaiList = cutiPegawaiBo.getByCriteriaForAbsensi(cutiPegawai,tanggal);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[AbsensiAction.searchListAbsensiCuti] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[AbsensiAction.searchListAbsensiCuti] end process <<<");
        return cutiPegawaiList;
    }
    public List<SppdPerson> searchListAbsensiSppd(String tanggal, String Nip) {
        SppdPerson sppdPerson = new SppdPerson();
        sppdPerson.setPersonId(Nip);
        logger.info("[AbsensiAction.searchListAbsensiSppd] start process >>>");
        List<SppdPerson> sppdPersonList = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SppdBo sppdBo = (SppdBo) ctx.getBean("sppdBoProxy");
        try {
            sppdPersonList = sppdBo.getByCriteriaForAbsensi(sppdPerson,tanggal);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[AbsensiAction.searchListAbsensiSppd] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[AbsensiAction.searchListAbsensiSppd] end process <<<");
        return sppdPersonList;
    }
    public List<IjinKeluar> searchListAbsensiIjinTidakMasuk(String tanggal, String Nip) {
        IjinKeluar ijinKeluar = new IjinKeluar();
        ijinKeluar.setNip(Nip);
        logger.info("[AbsensiAction.searchListAbsensiIjinTidakMasuk] start process >>>");
        List<IjinKeluar> ijinKeluarList = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        try {
            ijinKeluarList = ijinKeluarBo.getByCriteriaForAbsensiIjinTidakMasuk(ijinKeluar,tanggal);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[AbsensiAction.searchListAbsensiIjinTidakMasuk] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        logger.info("[AbsensiAction.searchListAbsensiIjinTidakMasuk] end process <<<");
        return ijinKeluarList;
    }
    @Override
    public String initForm() {
        logger.info("[absensiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        AbsensiPegawai data = new AbsensiPegawai();
        if (branchId!=null){
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        }else{
            data.setBranchId("");
        }


        setAbsensiPegawai(data);
        session.removeAttribute("listOfResultAbsensi");
        session.removeAttribute("listOfResultMesinAbsensi");
        logger.info("[absensiAction.initForm] end process >>>");
        return INPUT;
    }
    public String printReportAbsensi() {
        logger.info("[ReportAction.printReportAbsensi] start process >>>");
        String unit ="-";
        String stBagian="-";
        String unitId = "";
        List<AbsensiPegawai> listDataFinal = new ArrayList();
        List<Biodata> biodataList= new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        IjinKeluarBo ijinKeluarBo = (IjinKeluarBo) ctx.getBean("ijinKeluarBoProxy");
        PositionBagianBo positionBagianBo= (PositionBagianBo) ctx.getBean("positionBagianBoProxy");
        PositionBagian positionBagian = new PositionBagian();
        if (!getBagian().equalsIgnoreCase("")){
            positionBagian = positionBagianBo.getBagianById(getBagian(),"Y");
            stBagian=positionBagian.getBagianName();
        }

        biodataList = biodataBo.getBiodataforUangMakan(getBranchId(),"",getBagian(),getNip());
        int x=1;
        int jumlahTerlambatKurang60=0,jumlahTerlambatLebih60=0,tidakAbsenMasuk=0,tidakAbsenPulang=0;
        String nama="";
        for (Biodata biodata : biodataList) {
            List<AbsensiPegawai> listData = new ArrayList();
            List<AbsensiPegawai> listDataAbsensi = new ArrayList();
            AbsensiPegawai search2 = new AbsensiPegawai();
            unitId = biodata.getBranch();
            search2.setStTanggalDari(getTglFrom());
            search2.setStTanggalSelesai(getTglTo());
            search2.setFlag("Y");
            search2.setNip(biodata.getNip());
            try {
                listData = absensiBo.getByCriteriaForReport(search2);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.inquiry");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.search] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.search] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            for (AbsensiPegawai absensiPegawai : listData ){
                absensiPegawai.setNama(biodata.getNamaPegawai());
                absensiPegawai.setPositionName(biodata.getPositionName());
                absensiPegawai.setDivisi(biodata.getDivisiName());
                absensiPegawai.setUnit(biodata.getBranchName());
                absensiPegawai.setBagian(biodata.getBagianName());
                listDataAbsensi.add(absensiPegawai);
            }
            listDataFinal.addAll(listDataAbsensi);
        }
        List<AbsensiPegawai> listDataFinalTmp = listDataFinal;
        listDataFinal = new ArrayList<>();
        for ( AbsensiPegawai absensiPegawai : listDataFinalTmp){
            if(absensiPegawai.getJamMasuk()==null){
                absensiPegawai.setJamMasuk("");
            }
            if(absensiPegawai.getJamKeluar()==null){
                absensiPegawai.setJamKeluar("");
            }
            if(absensiPegawai.getJamPulang()==null){
                absensiPegawai.setJamPulang("");
            }
            if (absensiPegawai.getTerlambatKurang60()==null){
                absensiPegawai.setTerlambatKurang60("");
            }
            if (absensiPegawai.getTerlambatLebih60()==null){
                absensiPegawai.setTerlambatLebih60("");
            }
            if (absensiPegawai.getTidakAbsenMasuk()==null){
                absensiPegawai.setTidakAbsenMasuk("");
            }
            if (absensiPegawai.getTidakAbsenPulang()==null){
                absensiPegawai.setTidakAbsenPulang("");
            }
            if (absensiPegawai.getRealisasiJamLembur()==0){
                absensiPegawai.setStRealisasiJamLembur("");
            }else{
                absensiPegawai.setStRealisasiJamLembur(String.valueOf(absensiPegawai.getRealisasiJamLembur()));
            }
            unit = absensiPegawai.getUnit();

            if (x==1){
                nama=absensiPegawai.getNama();
            }else if (!nama.equalsIgnoreCase(absensiPegawai.getNama())){
                AbsensiPegawai newAbsen= new AbsensiPegawai();
                newAbsen.setStTanggal("");
                newAbsen.setNip("");
                newAbsen.setNama("");
                newAbsen.setBagian("");
                newAbsen.setJamMasuk("");
                newAbsen.setJamPulang("");
                newAbsen.setMulaiIzin("");
                newAbsen.setPulangIzin("");
                newAbsen.setAwalLembur("");
                newAbsen.setSelesaiLembur("");
                newAbsen.setStRealisasiJamLembur("");
                newAbsen.setStatusName("");
                newAbsen.setTerlambatKurang60("");
                newAbsen.setTerlambatLebih60("");
                newAbsen.setTidakAbsenMasuk("");
                newAbsen.setTidakAbsenPulang("");
//                  jumlahTerlambatKurang60=0;jumlahTerlambatLebih60=0;tidakAbsenMasuk=0;tidakAbsenPulang=0;
                  listDataFinal.add(newAbsen);
                  nama=absensiPegawai.getNama();
            }

            if (("1").equalsIgnoreCase(absensiPegawai.getTerlambatKurang60())){
                jumlahTerlambatKurang60=jumlahTerlambatKurang60+1;
            }else if (("1").equalsIgnoreCase(absensiPegawai.getTerlambatLebih60())){
                jumlahTerlambatLebih60=jumlahTerlambatLebih60+1;
            }else if (("1").equalsIgnoreCase(absensiPegawai.getTidakAbsenMasuk())){
                tidakAbsenMasuk=tidakAbsenMasuk+1;
            }else if (("1").equalsIgnoreCase(absensiPegawai.getTidakAbsenPulang())){
                tidakAbsenPulang=tidakAbsenPulang+1;
            }

            absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
            absensiPegawai.setMulaiIzin("");
            absensiPegawai.setPulangIzin("");
            boolean sudah=false;
            if (("Y").equalsIgnoreCase(absensiPegawai.getIjin())){
                IjinKeluar searchIjinKeluar = new IjinKeluar();
                searchIjinKeluar.setNip(absensiPegawai.getNip());
                List<IjinKeluar> ijinKeluarList = ijinKeluarBo.getByCriteriaForAbsensi(searchIjinKeluar,absensiPegawai.getStTanggal());
                if (ijinKeluarList.size()==1){
                    for (IjinKeluar ijinKeluar : ijinKeluarList){
                        absensiPegawai.setMulaiIzin(ijinKeluar.getJamKeluar());
                        absensiPegawai.setPulangIzin(ijinKeluar.getJamKembali());
                    }
                }
                else if (ijinKeluarList.size()>1){
                    int noIjin=1;
                    for (IjinKeluar ijinKeluar : ijinKeluarList){
                        if (noIjin==1){
                            absensiPegawai.setMulaiIzin(ijinKeluar.getJamKeluar());
                            absensiPegawai.setPulangIzin(ijinKeluar.getJamKembali());
                            listDataFinal.add(absensiPegawai);
                            sudah=true;
                        }else{
                            AbsensiPegawai newAbsen= new AbsensiPegawai();
                            newAbsen.setStTanggal("");
                            newAbsen.setNip("");
                            newAbsen.setNama("");
                            newAbsen.setBagian("");
                            newAbsen.setJamMasuk("");
                            newAbsen.setJamPulang("");
                            newAbsen.setMulaiIzin(ijinKeluar.getJamKeluar());
                            newAbsen.setPulangIzin(ijinKeluar.getJamKembali());
                            newAbsen.setAwalLembur("");
                            newAbsen.setSelesaiLembur("");
                            newAbsen.setStRealisasiJamLembur("");
                            newAbsen.setStatusName("");
                            newAbsen.setTerlambatKurang60("");
                            newAbsen.setTerlambatLebih60("");
                            newAbsen.setTidakAbsenMasuk("");
                            newAbsen.setTidakAbsenPulang("");
                            listDataFinal.add(newAbsen);
                        }
                        noIjin++;
                    }
                }
            }
            if (!sudah){
                listDataFinal.add(absensiPegawai);
            }
            x++;
        }
        AbsensiPegawai newAbsen= new AbsensiPegawai();
        newAbsen.setStTanggal("");
        newAbsen.setNip("");
        newAbsen.setNama("");
        newAbsen.setBagian("");
        newAbsen.setJamMasuk("");
        newAbsen.setJamPulang("");
        newAbsen.setMulaiIzin("");
        newAbsen.setPulangIzin("");
        newAbsen.setAwalLembur("");
        newAbsen.setSelesaiLembur("");
        newAbsen.setStRealisasiJamLembur("");
        newAbsen.setStatusName("");
        newAbsen.setTerlambatKurang60("");
        newAbsen.setTerlambatLebih60("");
        newAbsen.setTidakAbsenMasuk("");
        newAbsen.setTidakAbsenPulang("");

        jumlahTerlambatKurang60=0;jumlahTerlambatLebih60=0;tidakAbsenMasuk=0;tidakAbsenPulang=0;
        listDataFinal.add(newAbsen);

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listDataFinal);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        Branch branch = new Branch();
        try{
            BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
            branch = branchBo.getBranchById(unitId,"Y");
        }catch( HibernateException e){

        }
        String logo ="";
        if (unitId.equalsIgnoreCase("RS01")){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
        }else if (unitId.equalsIgnoreCase("RS02")){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
        }else if (unitId.equalsIgnoreCase("RS03")){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
        }else{
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
        }
        String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
        reportParams.put("urlLogo", logo);
        reportParams.put("alamatSurat", branch.getAlamatSurat()+","+stTanggal);        reportParams.put("titleReport", "Laporan Absensi");
        reportParams.put("tanggalDari", getTglFrom());
        reportParams.put("tanggalSelesai", getTglTo());
        reportParams.put("unit", branch.getBranchName());
        reportParams.put("bagian", stBagian);
        reportParams.put("itemDataSource", itemData);
        reportParams.put("date", stDate);
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "printReportKPIUnit");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printReportKPIUnit] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printReportKPIUnit] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print_report_absensi";

        }
        logger.info("[ReportAction.printReportKPIUnit] end process <<<");

        return "success_print_report_absensi";
    }

    public String printReportUangMakan() {
        logger.info("[ReportAction.printReportUangMakan] start process >>>");
        List<AbsensiPegawai> listDataFinal = new ArrayList();
        List<Biodata> listPegawaiMess= new ArrayList();
        List<Biodata> biodataList = new ArrayList();
        String unit = "";
        String divisi="";
        String bagianSt="";
        int uangMakan=0;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        BranchBo branchBo= (BranchBo) ctx.getBean("branchBoProxy");
        PositionBagianBo positionBagianBo= (PositionBagianBo) ctx.getBean("positionBagianBoProxy");
        Branch searchBranch = new Branch();
        searchBranch.setFlag("Y");
        searchBranch.setBranchId(getBranchId());
        List<Branch> branchList = branchBo.getByCriteria(searchBranch);
        for (Branch branch:branchList){
            unit=branch.getBranchName();
            uangMakan=branch.getUangMakan().intValue();
        }
        PositionBagian positionBagian = new PositionBagian();
        if (!getBagian().equalsIgnoreCase("")){
            positionBagian = positionBagianBo.getBagianById(getBagian(),"Y");
            bagianSt=positionBagian.getBagianName();
        }

        listPegawaiMess = biodataBo.getPegawaiMess();
        biodataList = biodataBo.getBiodataforUangMakan(getBranchId(),"",getBagian(),getNip());

        for (Biodata biodata : biodataList){
            List<AbsensiPegawai> listData = new ArrayList();
            List<AbsensiPegawai> listDataAbsensi = new ArrayList();

            AbsensiPegawai search = new AbsensiPegawai();
            search.setFlag("Y");
            search.setNip(biodata.getNip());
            search.setStTanggalDari(getTglFrom());
            search.setStTanggalSelesai(getTglTo());

            try {
                listData = absensiBo.getByCriteriaForReportUangMakan(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteriaForReportUangMakan");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.printReportUangMakan] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.printReportUangMakan] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }

            //hanya 5 absensi saja
            if (biodata.getShift()!=null){
                if (listData.size()>6&& biodata.getShift().equalsIgnoreCase("Y")){
                    int jumlah = listData.size()-6;
                    boolean lebih=true;
                    List<AbsensiPegawai> listDataTmp = new ArrayList();
                    for (AbsensiPegawai absensiPegawai : listData ){
                        if (absensiPegawai.getStatusAbsensi().equalsIgnoreCase("02")||absensiPegawai.getStatusAbsensi().equalsIgnoreCase("03")||absensiPegawai.getStatusAbsensi().equalsIgnoreCase("14")){
                            if (!lebih){
                                listDataTmp.add(absensiPegawai);
                            }else if (jumlah!=0){
                                jumlah=jumlah-1;
                            }
                            if (jumlah==0)
                            {
                                lebih=false;
                            }
                        }else{
                            listDataTmp.add(absensiPegawai);
                        }
                    }
                    listData.clear();
                    listData.addAll(listDataTmp);
                }
            }

            //filter lagi
            if (biodata.getShift()!=null){
                if (listData.size()>6&& biodata.getShift().equalsIgnoreCase("Y")){
                    int jumlah = listData.size()-6;
                    boolean lebih=true;
                    List<AbsensiPegawai> listDataTmp = new ArrayList();
                    for (AbsensiPegawai absensiPegawai : listData ){
                        if (!lebih){
                            listDataTmp.add(absensiPegawai);
                        }else if (jumlah!=0){
                            jumlah=jumlah-1;
                        }
                        if (jumlah==0)
                        {
                            lebih=false;
                        }
                    }
                    listData.clear();
                    listData.addAll(listDataTmp);
                }
            }

            for (AbsensiPegawai absensiPegawai : listData ){
                absensiPegawai.setNama(biodata.getNamaPegawai());
                absensiPegawai.setPositionName(biodata.getPositionName());
                absensiPegawai.setDivisi(biodata.getDivisiName());
                absensiPegawai.setUnit(biodata.getBranchName());
                absensiPegawai.setBagian(biodata.getBagianName());
                listDataAbsensi.add(absensiPegawai);
            }
            listDataFinal.addAll(listDataAbsensi);
        }

        List<AbsensiPegawai> listDataFinalTmp = listDataFinal;

        listDataFinal = new ArrayList<>();
        String nip="";
        AbsensiPegawai tmp = new AbsensiPegawai();
        int index = 0;
        int totalAbsensi=0;
        String stTotal="";
        BigDecimal totalUangMakan= BigDecimal.valueOf(0);
        for ( AbsensiPegawai absensiPegawai : listDataFinalTmp){
            if (index!=0&&!nip.equalsIgnoreCase(absensiPegawai.getNip())){
                tmp.setNip(absensiPegawai.getNip());
                tmp.setNama(absensiPegawai.getNama());
                tmp.setDivisi(absensiPegawai.getDivisi());
                if (absensiPegawai.getBagian()==null){
                    tmp.setBagian("");
                }else{
                    tmp.setBagian(absensiPegawai.getBagian());
                }
                tmp.setJamMasuk(absensiPegawai.getJamMasuk());
                tmp.setJamPulang(absensiPegawai.getJamPulang());
                tmp.setStTanggal(absensiPegawai.getStTanggal());
                tmp.setStatusAbsensi(absensiPegawai.getStatusAbsensi());
                tmp.setFlagUangMakan(absensiPegawai.getFlagUangMakan());
                tmp.setApprovalFlag(absensiPegawai.getApprovalFlag());

                absensiPegawai.setNama("JUMLAH");
                absensiPegawai.setNip("");
                absensiPegawai.setDivisi("");
                absensiPegawai.setBagian("");
                absensiPegawai.setJamMasuk("");
                absensiPegawai.setJamPulang("");
                absensiPegawai.setStTanggal("");
                absensiPegawai.setStatusName("");
                absensiPegawai.setAbsensi(String.valueOf(totalAbsensi));
                stTotal=CommonUtil.numbericFormat(BigDecimal.valueOf(totalAbsensi*uangMakan), "###,###");
                absensiPegawai.setStUangmakan(stTotal);
                totalUangMakan = totalUangMakan.add(BigDecimal.valueOf(totalAbsensi*uangMakan));
                index++;
                listDataFinal.add(absensiPegawai);

                absensiPegawai = new AbsensiPegawai();
                absensiPegawai.setNama(tmp.getNama());
                absensiPegawai.setNip(tmp.getNip());
                absensiPegawai.setBagian(tmp.getBagian());
                absensiPegawai.setDivisi(tmp.getDivisi());
                absensiPegawai.setJamMasuk(tmp.getJamMasuk());
                absensiPegawai.setJamPulang(tmp.getJamPulang());
                absensiPegawai.setStTanggal(tmp.getStTanggal());
                absensiPegawai.setApprovalFlag(tmp.getApprovalFlag());
                absensiPegawai.setFlagUangMakan(tmp.getFlagUangMakan());
                absensiPegawai.setStatusAbsensi(tmp.getStatusAbsensi());
                absensiPegawai.setStatusName(CommonUtil.statusName(tmp.getStatusAbsensi()));
            }
            boolean mess = false;
            for (Biodata biodata : listPegawaiMess){
                if (absensiPegawai.getNip().equalsIgnoreCase(biodata.getNip())){
                    mess=true;
                    break;
                }
            }
            if (!absensiPegawai.getNip().equalsIgnoreCase(nip)) {

                if (mess){
                    totalAbsensi=0;
                    nip = absensiPegawai.getNip();
                    absensiPegawai.setAbsensi("");
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    absensiPegawai.setStUangmakan("MESS");
                    index++;
                    listDataFinal.add(absensiPegawai);
                }else if(("16-0431").equalsIgnoreCase(absensiPegawai.getNip())&&!("-").equalsIgnoreCase(absensiPegawai.getJamMasuk())&&!("-").equalsIgnoreCase(absensiPegawai.getJamPulang())){
                    totalAbsensi=1;
                    nip = absensiPegawai.getNip();
                    absensiPegawai.setAbsensi("1");
                    absensiPegawai.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(uangMakan), "###,###"));
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    index++;
                    listDataFinal.add(absensiPegawai);
                }else if(("01").equalsIgnoreCase(absensiPegawai.getStatusAbsensi())||("04").equalsIgnoreCase(absensiPegawai.getStatusAbsensi())||("09").equalsIgnoreCase(absensiPegawai.getStatusAbsensi())){
                    totalAbsensi=1;
                    nip = absensiPegawai.getNip();
                    absensiPegawai.setAbsensi("1");
                    absensiPegawai.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(uangMakan), "###,###"));
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    index++;
                    listDataFinal.add(absensiPegawai);
                }else if("Y".equalsIgnoreCase(absensiPegawai.getApprovalFlag())&&"Y".equalsIgnoreCase(absensiPegawai.getFlagUangMakan())){
                    totalAbsensi=1;
                    nip = absensiPegawai.getNip();
                    absensiPegawai.setAbsensi("1");
                    absensiPegawai.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(uangMakan), "###,###"));
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    index++;
                    listDataFinal.add(absensiPegawai);
                }else{
                    totalAbsensi=0;
                    nip = absensiPegawai.getNip();
                    absensiPegawai.setAbsensi("0");
                    absensiPegawai.setStUangmakan("0");
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    if(("Pengemudi").equalsIgnoreCase(tmp.getBagian())){
                        if (("00").equalsIgnoreCase(absensiPegawai.getStatusAbsensi())){
                            absensiPegawai.setStatusName("-");
                        }
                    }
                    index++;
                    listDataFinal.add(absensiPegawai);
                }
            }else if (nip.equalsIgnoreCase(absensiPegawai.getNip())){
                if (mess){
                    absensiPegawai.setNip("");
                    absensiPegawai.setNama("");
                    absensiPegawai.setDivisi("");
                    absensiPegawai.setBagian("");
                    absensiPegawai.setAbsensi("");
                    absensiPegawai.setStUangmakan("MESS");
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    totalAbsensi=0;
                    listDataFinal.add(absensiPegawai);
                    index++;
                } else if(("16-0431").equalsIgnoreCase(absensiPegawai.getNip())&&!("-").equalsIgnoreCase(absensiPegawai.getJamMasuk())&&!("-").equalsIgnoreCase(absensiPegawai.getJamPulang())){
                    absensiPegawai.setNip("");
                    absensiPegawai.setNama("");
                    absensiPegawai.setDivisi("");
                    absensiPegawai.setBagian("");
                    absensiPegawai.setAbsensi("1");
                    absensiPegawai.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(uangMakan), "###,###"));
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    totalAbsensi=totalAbsensi+1;
                    listDataFinal.add(absensiPegawai);
                    index++;
                } else if(("01").equalsIgnoreCase(absensiPegawai.getStatusAbsensi())||("04").equalsIgnoreCase(absensiPegawai.getStatusAbsensi())||("09").equalsIgnoreCase(absensiPegawai.getStatusAbsensi())){
                    absensiPegawai.setNip("");
                    absensiPegawai.setNama("");
                    absensiPegawai.setDivisi("");
                    absensiPegawai.setBagian("");
                    absensiPegawai.setAbsensi("1");
                    absensiPegawai.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(uangMakan), "###,###"));
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    totalAbsensi=totalAbsensi+1;
                    listDataFinal.add(absensiPegawai);
                    index++;
                }
                else if ("Y".equalsIgnoreCase(absensiPegawai.getApprovalFlag())&&"Y".equalsIgnoreCase(absensiPegawai.getFlagUangMakan())){
                    absensiPegawai.setNip("");
                    absensiPegawai.setNama("");
                    absensiPegawai.setDivisi("");
                    absensiPegawai.setBagian("");
                    absensiPegawai.setAbsensi("1");
                    absensiPegawai.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(uangMakan), "###,###"));
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    totalAbsensi=totalAbsensi+1;
                    listDataFinal.add(absensiPegawai);
                    index++;
                }else{
                    absensiPegawai.setNip("");
                    absensiPegawai.setNama("");
                    absensiPegawai.setDivisi("");
                    absensiPegawai.setBagian("");
                    absensiPegawai.setAbsensi("0");
                    absensiPegawai.setStUangmakan("0");
                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                    if(("Pengemudi").equalsIgnoreCase(tmp.getBagian())){
                        if (("00").equalsIgnoreCase(absensiPegawai.getStatusAbsensi())){
                            absensiPegawai.setStatusName("-");
                        }
                    }
                    listDataFinal.add(absensiPegawai);
                    index++;
                }
            }
        }
        AbsensiPegawai absensiPegawai = new AbsensiPegawai();
        absensiPegawai.setNama("JUMLAH");
        absensiPegawai.setNip("");
        absensiPegawai.setDivisi("");
        absensiPegawai.setBagian("");
        absensiPegawai.setJamMasuk("");
        absensiPegawai.setJamPulang("");
        absensiPegawai.setStTanggal("");
        absensiPegawai.setStatusName("");
        absensiPegawai.setAbsensi(String.valueOf(totalAbsensi));
        stTotal=CommonUtil.numbericFormat(BigDecimal.valueOf(totalAbsensi*uangMakan), "###,###");
        absensiPegawai.setStUangmakan(String.valueOf(stTotal));
        listDataFinal.add(absensiPegawai);

        totalUangMakan = totalUangMakan.add(BigDecimal.valueOf(totalAbsensi*uangMakan));

        absensiPegawai = new AbsensiPegawai();
        absensiPegawai.setNama("TOTAL UANG MAKAN ABSENSI :");
        absensiPegawai.setNip("");
        absensiPegawai.setDivisi("");
        absensiPegawai.setBagian("");
        absensiPegawai.setJamMasuk("");
        absensiPegawai.setJamPulang("");
        absensiPegawai.setStTanggal("");
        absensiPegawai.setAbsensi("");
        absensiPegawai.setStatusName("");
        stTotal = CommonUtil.numbericFormat(totalUangMakan, "###,###");
        absensiPegawai.setStUangmakan(stTotal);
        listDataFinal.add(absensiPegawai);

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listDataFinal);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "Laporan Uang Makan Absensi");
        reportParams.put("tanggalDari", getTglFrom());
        reportParams.put("tanggalSelesai", getTglTo());
        reportParams.put("itemDataSource", itemData);
        reportParams.put("branchId", unit);
        reportParams.put("divisi", divisi);
        reportParams.put("bagian", bagianSt);
        reportParams.put("date", stDate);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "printReportKPIUnit");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printReportKPIUnit] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printReportKPIUnit] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print_report_absensi";

        }
        logger.info("[ReportAction.printReportKPIUnit] end process <<<");

        return "success_print_report_uang_makan";
    }

    public String printReportUangMakanTambahan() {
        logger.info("[ReportAction.printReportUangMakanTambahan] start process >>>");
        List<PegawaiTambahanAbsensi> listDataFinal = new ArrayList();
        List<PegawaiTambahan> pegawaiTambahanList = new ArrayList();
        String unit = "Kantor Direksi";
        String divisi="";
        String bagianSt=getBagian();
        int uangMakan=0;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BranchBo branchBo= (BranchBo) ctx.getBean("branchBoProxy");
        AbsensiBo absensiBo= (AbsensiBo) ctx.getBean("absensiBoProxy");

        Branch searchBranch = new Branch();
        searchBranch.setFlag("Y");
        searchBranch.setBranchId("KD01");
        List<Branch> branchList = branchBo.getByCriteria(searchBranch);
        for (Branch branch:branchList){
            unit=branch.getBranchName();
            uangMakan=branch.getUangMakan().intValue();
        }

        pegawaiTambahanList = absensiBo.getDataPegawaiTambahan(bagianSt);

        for (PegawaiTambahan pegawaiTambahan : pegawaiTambahanList){
            List<PegawaiTambahanAbsensi> listData = new ArrayList();
            List<PegawaiTambahanAbsensi> listDataAbsensi = new ArrayList();

            PegawaiTambahanAbsensi search = new PegawaiTambahanAbsensi();
            search.setFlag("Y");
            search.setPin(pegawaiTambahan.getPin());
            search.setStTanggalDari(getTglFrom());
            search.setStTanggalSelesai(getTglTo());

            try {
                listData = absensiBo.getByCriteriaForReportUangMakanTambahan(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteriaForReportUangMakan");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.printReportUangMakan] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.printReportUangMakan] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            for (PegawaiTambahanAbsensi pegawaiTambahanAbsensi : listData ){
                pegawaiTambahanAbsensi.setNama(pegawaiTambahan.getNama());
                pegawaiTambahanAbsensi.setPositionName("");
                pegawaiTambahanAbsensi.setDivisi("");
                pegawaiTambahanAbsensi.setUnit(unit);
                pegawaiTambahanAbsensi.setBagian(pegawaiTambahan.getBagian());
                listDataAbsensi.add(pegawaiTambahanAbsensi);
            }
            listDataFinal.addAll(listDataAbsensi);
        }

        List<PegawaiTambahanAbsensi> listDataFinalTmp = listDataFinal;

        listDataFinal = new ArrayList<>();
        String nip="";
        PegawaiTambahanAbsensi tmp = new PegawaiTambahanAbsensi();
        int index = 0;
        int totalAbsensi=0;
        String stTotal="";
        BigDecimal totalUangMakan= BigDecimal.valueOf(0);
        for ( PegawaiTambahanAbsensi pegawaiTambahanAbsensi : listDataFinalTmp){
            if (index!=0&&!nip.equalsIgnoreCase(pegawaiTambahanAbsensi.getPin())){
                tmp.setPin(pegawaiTambahanAbsensi.getPin());
                tmp.setNama(pegawaiTambahanAbsensi.getNama());
                tmp.setDivisi(pegawaiTambahanAbsensi.getDivisi());
                if (pegawaiTambahanAbsensi.getBagian()==null){
                    tmp.setBagian("");
                }else{
                    tmp.setBagian(pegawaiTambahanAbsensi.getBagian());
                }
                tmp.setJamMasuk(pegawaiTambahanAbsensi.getJamMasuk());
                tmp.setJamPulang(pegawaiTambahanAbsensi.getJamPulang());
                tmp.setStTanggal(pegawaiTambahanAbsensi.getStTanggal());
                tmp.setStatusAbsensi(pegawaiTambahanAbsensi.getStatusAbsensi());

                pegawaiTambahanAbsensi.setNama("JUMLAH");
                pegawaiTambahanAbsensi.setPin("");
                pegawaiTambahanAbsensi.setDivisi("");
                pegawaiTambahanAbsensi.setBagian("");
                pegawaiTambahanAbsensi.setJamMasuk("");
                pegawaiTambahanAbsensi.setJamPulang("");
                pegawaiTambahanAbsensi.setStTanggal("");
                pegawaiTambahanAbsensi.setStatusAbsensiName("");
                pegawaiTambahanAbsensi.setAbsensi(String.valueOf(totalAbsensi));
                stTotal=CommonUtil.numbericFormat(BigDecimal.valueOf(totalAbsensi*uangMakan), "###,###");
                pegawaiTambahanAbsensi.setStUangmakan(stTotal);
                totalUangMakan = totalUangMakan.add(BigDecimal.valueOf(totalAbsensi*uangMakan));
                index++;
                listDataFinal.add(pegawaiTambahanAbsensi);

                pegawaiTambahanAbsensi = new PegawaiTambahanAbsensi();
                pegawaiTambahanAbsensi.setNama(tmp.getNama());
                pegawaiTambahanAbsensi.setPin(tmp.getPin());
                pegawaiTambahanAbsensi.setBagian(tmp.getBagian());
                pegawaiTambahanAbsensi.setDivisi(tmp.getDivisi());
                pegawaiTambahanAbsensi.setJamMasuk(tmp.getJamMasuk());
                pegawaiTambahanAbsensi.setJamPulang(tmp.getJamPulang());
                pegawaiTambahanAbsensi.setStTanggal(tmp.getStTanggal());
                pegawaiTambahanAbsensi.setStatusAbsensi(tmp.getStatusAbsensi());
                pegawaiTambahanAbsensi.setStatusAbsensiName(CommonUtil.statusName(tmp.getStatusAbsensi()));
            }

            if (!pegawaiTambahanAbsensi.getPin().equalsIgnoreCase(nip)) {

                if(("01").equalsIgnoreCase(pegawaiTambahanAbsensi.getStatusAbsensi())||("04").equalsIgnoreCase(pegawaiTambahanAbsensi.getStatusAbsensi())||("09").equalsIgnoreCase(pegawaiTambahanAbsensi.getStatusAbsensi())){
                    totalAbsensi=1;
                    nip = pegawaiTambahanAbsensi.getPin();
                    pegawaiTambahanAbsensi.setAbsensi("1");
                    pegawaiTambahanAbsensi.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(uangMakan), "###,###"));
                    pegawaiTambahanAbsensi.setStatusAbsensiName(CommonUtil.statusName(pegawaiTambahanAbsensi.getStatusAbsensi()));
                    index++;
                    listDataFinal.add(pegawaiTambahanAbsensi);
                }else{
                    totalAbsensi=0;
                    nip = pegawaiTambahanAbsensi.getPin();
                    pegawaiTambahanAbsensi.setAbsensi("0");
                    pegawaiTambahanAbsensi.setStUangmakan("0");
                    pegawaiTambahanAbsensi.setStatusAbsensiName(CommonUtil.statusName(pegawaiTambahanAbsensi.getStatusAbsensi()));

                    index++;
                    listDataFinal.add(pegawaiTambahanAbsensi);
                }
            }else if (nip.equalsIgnoreCase(pegawaiTambahanAbsensi.getPin())){
                if(("01").equalsIgnoreCase(pegawaiTambahanAbsensi.getStatusAbsensi())||("04").equalsIgnoreCase(pegawaiTambahanAbsensi.getStatusAbsensi())||("09").equalsIgnoreCase(pegawaiTambahanAbsensi.getStatusAbsensi())){
                    pegawaiTambahanAbsensi.setPin("");
                    pegawaiTambahanAbsensi.setNama("");
                    pegawaiTambahanAbsensi.setDivisi("");
                    pegawaiTambahanAbsensi.setBagian("");
                    pegawaiTambahanAbsensi.setAbsensi("1");
                    pegawaiTambahanAbsensi.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(uangMakan), "###,###"));
                    pegawaiTambahanAbsensi.setStatusAbsensiName(CommonUtil.statusName(pegawaiTambahanAbsensi.getStatusAbsensi()));
                    totalAbsensi=totalAbsensi+1;
                    listDataFinal.add(pegawaiTambahanAbsensi);
                    index++;
                }else{
                    pegawaiTambahanAbsensi.setPin("");
                    pegawaiTambahanAbsensi.setNama("");
                    pegawaiTambahanAbsensi.setDivisi("");
                    pegawaiTambahanAbsensi.setBagian("");
                    pegawaiTambahanAbsensi.setAbsensi("0");
                    pegawaiTambahanAbsensi.setStUangmakan("0");
                    pegawaiTambahanAbsensi.setStatusAbsensiName(CommonUtil.statusName(pegawaiTambahanAbsensi.getStatusAbsensi()));

                    listDataFinal.add(pegawaiTambahanAbsensi);
                    index++;
                }
            }
        }
        PegawaiTambahanAbsensi pegawaiTambahanAbsensi = new PegawaiTambahanAbsensi();
        pegawaiTambahanAbsensi.setNama("JUMLAH");
        pegawaiTambahanAbsensi.setPin("");
        pegawaiTambahanAbsensi.setDivisi("");
        pegawaiTambahanAbsensi.setBagian("");
        pegawaiTambahanAbsensi.setJamMasuk("");
        pegawaiTambahanAbsensi.setJamPulang("");
        pegawaiTambahanAbsensi.setStTanggal("");
        pegawaiTambahanAbsensi.setStatusAbsensiName("");
        pegawaiTambahanAbsensi.setAbsensi(String.valueOf(totalAbsensi));
        stTotal=CommonUtil.numbericFormat(BigDecimal.valueOf(totalAbsensi*uangMakan), "###,###");
        pegawaiTambahanAbsensi.setStUangmakan(String.valueOf(stTotal));
        listDataFinal.add(pegawaiTambahanAbsensi);

        totalUangMakan = totalUangMakan.add(BigDecimal.valueOf(totalAbsensi*uangMakan));

        pegawaiTambahanAbsensi = new PegawaiTambahanAbsensi();
        pegawaiTambahanAbsensi.setNama("TOTAL UANG MAKAN ABSENSI :");
        pegawaiTambahanAbsensi.setPin("");
        pegawaiTambahanAbsensi.setDivisi("");
        pegawaiTambahanAbsensi.setBagian("");
        pegawaiTambahanAbsensi.setJamMasuk("");
        pegawaiTambahanAbsensi.setJamPulang("");
        pegawaiTambahanAbsensi.setStTanggal("");
        pegawaiTambahanAbsensi.setAbsensi("");
        pegawaiTambahanAbsensi.setStatusAbsensiName("");
        stTotal = CommonUtil.numbericFormat(totalUangMakan, "###,###");
        pegawaiTambahanAbsensi.setStUangmakan(stTotal);
        listDataFinal.add(pegawaiTambahanAbsensi);

        List<AbsensiPegawai> forReport = new ArrayList<>();
        for (PegawaiTambahanAbsensi pegawaiTambahanAbsensi1 : listDataFinal){
            AbsensiPegawai data = new AbsensiPegawai();
            data.setNama(pegawaiTambahanAbsensi1.getNama());
            data.setNip("");
            data.setDivisi(pegawaiTambahanAbsensi1.getDivisi());
            data.setBagian(pegawaiTambahanAbsensi1.getBagian());
            data.setJamMasuk(pegawaiTambahanAbsensi1.getJamMasuk());
            data.setJamPulang(pegawaiTambahanAbsensi1.getJamPulang());
            data.setStTanggal(pegawaiTambahanAbsensi1.getStTanggal());
            data.setAbsensi(pegawaiTambahanAbsensi1.getAbsensi());
            data.setStatusName(pegawaiTambahanAbsensi1.getStatusAbsensiName());
            data.setStUangmakan(pegawaiTambahanAbsensi1.getStUangmakan());

            forReport.add(data);
        }

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(forReport);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "Laporan Uang Makan Absensi");
        reportParams.put("tanggalDari", getTglFrom());
        reportParams.put("tanggalSelesai", getTglTo());
        reportParams.put("itemDataSource", itemData);
        reportParams.put("branchId", unit);
        reportParams.put("divisi", divisi);
        reportParams.put("bagian", bagianSt);
        reportParams.put("date", stDate);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "printReportKPIUnit");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printReportKPIUnit] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printReportKPIUnit] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print_report_absensi";

        }
        logger.info("[ReportAction.printReportKPIUnit] end process <<<");

        return "success_print_report_uang_makan";
    }

    public String printReportAbsensiOutsourcing() {
        logger.info("[ReportAction.printReportUangMakanTambahan] start process >>>");
        List<PegawaiTambahanAbsensi> listDataFinal = new ArrayList();
        List<PegawaiTambahan> pegawaiTambahanList = new ArrayList();
        String unit = "Kantor Direksi";
        String nama="";
        int x = 1;
        String bagianSt=getBagian();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo= (AbsensiBo) ctx.getBean("absensiBoProxy");

        pegawaiTambahanList = absensiBo.getDataPegawaiTambahan(bagianSt);

        for (PegawaiTambahan pegawaiTambahan : pegawaiTambahanList){
            List<PegawaiTambahanAbsensi> listData = new ArrayList();
            List<PegawaiTambahanAbsensi> listDataAbsensi = new ArrayList();

            PegawaiTambahanAbsensi search = new PegawaiTambahanAbsensi();
            search.setFlag("Y");
            search.setPin(pegawaiTambahan.getPin());
            search.setStTanggalDari(getTglFrom());
            search.setStTanggalSelesai(getTglTo());

            try {
                listData = absensiBo.getByCriteriaForReportUangMakanTambahan(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteriaForReportUangMakan");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.printReportUangMakan] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.printReportUangMakan] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            for (PegawaiTambahanAbsensi pegawaiTambahanAbsensi : listData ){
                pegawaiTambahanAbsensi.setNama(pegawaiTambahan.getNama());
                pegawaiTambahanAbsensi.setPositionName("");
                pegawaiTambahanAbsensi.setDivisi("");
                pegawaiTambahanAbsensi.setUnit(unit);
                pegawaiTambahanAbsensi.setBagian(pegawaiTambahan.getBagian());
                listDataAbsensi.add(pegawaiTambahanAbsensi);
            }
            listDataFinal.addAll(listDataAbsensi);
        }

        List<PegawaiTambahanAbsensi> listDataFinalTmp = listDataFinal;
        listDataFinal = new ArrayList<>();
        for ( PegawaiTambahanAbsensi pegawaiTambahanAbsensi : listDataFinalTmp){
            if(pegawaiTambahanAbsensi.getJamMasuk()==null){
                pegawaiTambahanAbsensi.setJamMasuk("");
            }
            if(pegawaiTambahanAbsensi.getJamPulang()==null){
                pegawaiTambahanAbsensi.setJamPulang("");
            }
            if (x==1){
                nama=pegawaiTambahanAbsensi.getNama();
            }else if (!nama.equalsIgnoreCase(pegawaiTambahanAbsensi.getNama())){
                PegawaiTambahanAbsensi newAbsen= new PegawaiTambahanAbsensi();
                newAbsen.setStTanggal("JUMLAH");
                newAbsen.setPin("");
                newAbsen.setNama("");
                newAbsen.setBagian("");
                newAbsen.setJamMasuk("");
                newAbsen.setJamPulang("");
                listDataFinal.add(newAbsen);
                nama=pegawaiTambahanAbsensi.getNama();
            }

            pegawaiTambahanAbsensi.setStatusAbsensiName(CommonUtil.statusName(pegawaiTambahanAbsensi.getStatusAbsensi()));
            listDataFinal.add(pegawaiTambahanAbsensi);
        }
        PegawaiTambahanAbsensi newAbsen= new PegawaiTambahanAbsensi();
        newAbsen.setStTanggal("JUMLAH");
        newAbsen.setPin("");
        newAbsen.setNama("");
        newAbsen.setBagian("");
        newAbsen.setJamMasuk("");
        newAbsen.setJamPulang("");
        newAbsen.setStatusAbsensiName("");
        listDataFinal.add(newAbsen);


        List<AbsensiPegawai> reportAbsensi = new ArrayList<>();
        for (PegawaiTambahanAbsensi pegawaiTambahanAbsensi : listDataFinal){
            AbsensiPegawai data = new AbsensiPegawai();
            data.setNip(pegawaiTambahanAbsensi.getPin());
            data.setStTanggal(pegawaiTambahanAbsensi.getStTanggal());
            data.setStatusName(pegawaiTambahanAbsensi.getStatusAbsensiName());
            data.setBagian(pegawaiTambahanAbsensi.getBagian());
            data.setNama(pegawaiTambahanAbsensi.getNama());
            data.setJamMasuk(pegawaiTambahanAbsensi.getJamMasuk());
            data.setJamPulang(pegawaiTambahanAbsensi.getJamPulang());
            data.setTerlambatKurang60("");
            data.setTerlambatLebih60("");
            data.setTidakAbsenMasuk("");
            data.setTidakAbsenPulang("");
            reportAbsensi.add(data);
        }

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(reportAbsensi);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);
        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "Laporan Absensi");
        reportParams.put("tanggalDari", getTglFrom());
        reportParams.put("tanggalSelesai", getTglTo());
        reportParams.put("unit", unit);
        reportParams.put("bagian", bagianSt);
        reportParams.put("itemDataSource", itemData);
        reportParams.put("date", stDate);
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "printReportKPIUnit");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printReportKPIUnit] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printReportKPIUnit] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print_report_absensi";

        }
        logger.info("[ReportAction.printReportKPIUnit] end process <<<");

        return "success_print_report_absensi";
    }

//    public String printReportLembur() {
//        logger.info("[ReportAction.printReportLembur] start process >>>");
//        List<AbsensiPegawai> listDataFinal = new ArrayList();
//        String unit = "";
//        String nama="";
//        String bagian ="";
//        String golongan="";
//        String posisi="";
//        String branchIdForReport="";
//        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
//        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
////        StrukturJabatanBo strukturJabatanBo= (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");
//
//        Branch searchBranch = new Branch();
//        searchBranch.setFlag("Y");
//        searchBranch.setBranchId(getBranchId());
//        List<Branch> branchList = branchBo.getByCriteria(searchBranch);
//        for (Branch branch:branchList){
//            unit=branch.getBranchName();
//        }
////        List<StrukturJabatan> strukturJabatanEntityList = strukturJabatanBo.getPerBagianSys();
////        List<StrukturJabatan> strukturJabatanEntityList2 = strukturJabatanBo.getPerBagianSisa();
////        strukturJabatanEntityList.addAll(strukturJabatanEntityList2);
////        for (StrukturJabatan strukturJabatan : strukturJabatanEntityList){
////            if (strukturJabatan.getNip().equalsIgnoreCase(getNip())){
////                bagian = strukturJabatan.getBagian();
////                break;
////            }
////        }
//
//        AbsensiPegawai search = new AbsensiPegawai();
//        search.setFlag("Y");
//        search.setTanggalAwal(CommonUtil.convertToDate(getTglFrom()));
//        search.setTanggalAkhir(CommonUtil.convertToDate(getTglTo()));
//        if (!("").equalsIgnoreCase(getNip())){
//            search.setNip(getNip());
//
//            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
//            Biodata searchBiodata = new Biodata();
//            searchBiodata.setNip(getNip());
//            searchBiodata.setFlag("Y");
//            Biodata biodata = new Biodata();
//            biodata = biodataBo.detailBiodataSys(getNip());
//            if (biodata!=null){
//                golongan=biodata.getGolonganName();
//                posisi=biodata.getPositionName();
//            }
//        }
//        try {
//            listDataFinal = absensiBo.getByCriteriaForLembur(search);
//        } catch (GeneralBOException e) {
//            Long logId = null;
//            try {
//                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteriaForLembur");
//            } catch (GeneralBOException e1) {
//                logger.error("[AbsensiAction.printReportLembur] Error when saving error,", e1);
//            }
//            logger.error("[AbsensiAction.printReportLembur] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//        }
//        List<AbsensiPegawai> listDataFinalTmp = listDataFinal;
//        listDataFinal = new ArrayList<>();
//        Double hasilLamaLembur= (double) 0,hasilhariKerja15= (double) 0,hasilhariKerja20= (double) 0,hasilhariLibur20= (double) 0,hasilhariLibur30= (double) 0,hasilhariLibur40= (double) 0,hasilJamLembur= (double)0 ,hasilBiayaLembur= (double) 0;
//
//        DecimalFormat df = new DecimalFormat("0.00");
//        for ( AbsensiPegawai absensiPegawai : listDataFinalTmp){
//            branchIdForReport = absensiPegawai.getBranchId();
//            nama=absensiPegawai.getNama();
//            double realisasi;
//            if (absensiPegawai.getLamaLembur()<=absensiPegawai.getRealisasiJamLembur()){
//                realisasi= absensiPegawai.getLamaLembur();
//            }else{
//                realisasi= absensiPegawai.getRealisasiJamLembur();
//            }
//            AbsensiPegawai tmp = new AbsensiPegawai();
//            AbsensiPegawai resultSetHari;
//            resultSetHari = absensiBo.getJamLembur(realisasi,absensiPegawai.getTipeHari());
//            tmp.setStTanggal(absensiPegawai.getStTanggal());
//            tmp.setStLamaLembur(df.format(realisasi));
//            tmp.setStHariKerja15(df.format(resultSetHari.getHariKerja15()));
//            tmp.setStHariKerja20(df.format(resultSetHari.getHariKerja20()));
//            tmp.setStHariLibur20(df.format(resultSetHari.getHariLibur20()));
//            tmp.setStHariLibur30(df.format(resultSetHari.getHariLibur30()));
//            tmp.setStHariLibur40(df.format(resultSetHari.getHariLibur40()));
//            tmp.setStJamLembur(df.format(absensiPegawai.getJamLembur()));
//            tmp.setJamLembur(absensiPegawai.getJamLembur());
//
//            if (absensiPegawai.getBiayaLembur()==0d){
//                tmp.setStBiayaLemburPerjam(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
//            }else{
//                tmp.setStBiayaLemburPerjam(CommonUtil.numbericFormat(BigDecimal.valueOf(absensiPegawai.getBiayaLembur()/absensiPegawai.getJamLembur()),"###,###"));
//            }
//
//            tmp.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(absensiPegawai.getBiayaLembur()),"###,###"));
//            tmp.setBiayaLembur(absensiPegawai.getBiayaLembur());
//            hasilBiayaLembur=hasilBiayaLembur+absensiPegawai.getBiayaLembur();
//            hasilLamaLembur=hasilLamaLembur+realisasi;
//            hasilJamLembur=hasilJamLembur+absensiPegawai.getJamLembur();
//
//            hasilhariKerja15 =hasilhariKerja15+resultSetHari.getHariKerja15();
//            hasilhariKerja20 =hasilhariKerja20+resultSetHari.getHariKerja20();
//            hasilhariLibur20 =hasilhariLibur20+resultSetHari.getHariLibur20();
//            hasilhariLibur30 =hasilhariLibur30+resultSetHari.getHariLibur30();
//            hasilhariLibur40 =hasilhariLibur40+resultSetHari.getHariLibur40();
//
//            listDataFinal.add(tmp);
//        }
//        AbsensiPegawai hasil = new AbsensiPegawai();
//        hasil.setStTanggal("");
//        hasil.setStLamaLembur(df.format(hasilLamaLembur));
//        hasil.setStHariKerja15(df.format(hasilhariKerja15));
//        hasil.setStHariKerja20(df.format(hasilhariKerja20));
//        hasil.setStHariLibur20(df.format(hasilhariLibur20));
//        hasil.setStHariLibur30(df.format(hasilhariLibur30));
//        hasil.setStHariLibur40(df.format(hasilhariLibur40));
//        hasil.setStJamLembur(df.format(hasilJamLembur));
//        hasil.setStBiayaLemburPerjam("");
//        hasil.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLembur),"###,###"));
//        listDataFinal.add(hasil);
//
//        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listDataFinal);
//        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
//        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
//        String stDate = dt1.format(dataDate);
//
//        Branch branch = new Branch();
//        try{
//            branch = branchBo.getBranchById(branchIdForReport,"Y");
//        }catch( HibernateException e){
//        }
//        if (branchIdForReport.equalsIgnoreCase("RS01")){
//            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01);
//        }else if (branchIdForReport.equalsIgnoreCase("RS02")){
//            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02);
//        }else if (branchIdForReport.equalsIgnoreCase("RS03")){
//            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03);
//        }else{
//            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU);
//        }
//        String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
//        reportParams.put("alamatUni", branch.getAlamatSurat()+","+stTanggal);
//        reportParams.put("branchName", branch.getBranchName());
//        reportParams.put("titleReport", "DAFTAR LEMBUR");
//        reportParams.put("tanggalDari", getTglFrom());
//        reportParams.put("tanggalSelesai", getTglTo());
//        reportParams.put("itemDataSource", itemData);
//        reportParams.put("nip", getNip());
//        reportParams.put("date", stTanggal);
//        reportParams.put("nama", nama);
//        reportParams.put("posisi", posisi);
//        if (golongan!=null){
//            reportParams.put("golongan", golongan.replace("Golongan ",""));
//        }else{
//            reportParams.put("golongan", "-");
//        }
//        reportParams.put("date", stDate);
//
//        try {
//            preDownload();
//        } catch (SQLException e) {
//            Long logId = null;
//            try {
//                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "printReportLembur");
//            } catch (GeneralBOException e1) {
//                logger.error("[ReportAction.printReportLembur] Error when downloading ,", e1);
//            }
//            logger.error("[ReportAction.printReportLembur] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
//            return "failure_print_report_lembur";
//
//        }
//        logger.info("[ReportAction.printReportLembur] end process <<<");
//
//        return "success_print_report_lembur";
//    }

    public String printReportLembur() {
        logger.info("[ReportAction.printReportLembur] start process >>>");
        List<AbsensiPegawai> listDataFinal = new ArrayList();
        String unit = "";
        String nama="";
        String bagian ="";
        String golongan="";
        String posisi="";
        String branchIdForReport="";
        String keteranga="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        Branch searchBranch = new Branch();
        searchBranch.setFlag("Y");
        searchBranch.setBranchId(getBranchId());
        List<Branch> branchList = branchBo.getByCriteria(searchBranch);
        for (Branch branch:branchList){
            unit=branch.getBranchName();
        }

        AbsensiPegawai search = new AbsensiPegawai();
        search.setFlag("Y");
        search.setTanggalAwal(CommonUtil.convertToDate(getTglFrom()));
        search.setTanggalAkhir(CommonUtil.convertToDate(getTglTo()));
        if (!("").equalsIgnoreCase(getNip())){
            search.setNip(getNip());

            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
            Biodata searchBiodata = new Biodata();
            searchBiodata.setNip(getNip());
            searchBiodata.setFlag("Y");
            Biodata biodata = new Biodata();
            biodata = biodataBo.detailBiodataSys(getNip());
            if (biodata!=null){
                golongan=biodata.getGolonganName();
                posisi=biodata.getPositionName();
            }
        }
        try {
            listDataFinal = absensiBo.getByCriteriaForLembur(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteriaForLembur");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.printReportLembur] Error when saving error,", e1);
            }
            logger.error("[AbsensiAction.printReportLembur] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        List<AbsensiPegawai> listDataFinalTmp = listDataFinal;
        listDataFinal = new ArrayList<>();
        Double hasilLamaLembur= (double) 0,hasilhariKerja15= (double) 0,hasilhariKerja20= (double) 0,hasilhariLibur20= (double) 0,hasilhariLibur30= (double) 0,hasilhariLibur40= (double) 0,hasilJamLembur= (double)0 ,hasilBiayaLembur= (double) 0;

        DecimalFormat df = new DecimalFormat("0.00");
        for ( AbsensiPegawai absensiPegawai : listDataFinalTmp){
            branchIdForReport = absensiPegawai.getBranchId();
            nama=absensiPegawai.getNama();
            double realisasi;
            if (absensiPegawai.getLamaLembur()<=absensiPegawai.getRealisasiJamLembur()){
                realisasi= absensiPegawai.getLamaLembur();
            }else{
                realisasi= absensiPegawai.getRealisasiJamLembur();
            }
            AbsensiPegawai tmp = new AbsensiPegawai();
            AbsensiPegawai resultSetHari;
            resultSetHari = absensiBo.getJamLembur(realisasi,absensiPegawai.getTipeHari());
            tmp.setStTanggal(absensiPegawai.getStTanggal());
            tmp.setStLamaLembur(df.format(realisasi));
            tmp.setStHariKerja15(df.format(resultSetHari.getHariKerja15()));
            tmp.setStHariKerja20(df.format(resultSetHari.getHariKerja20()));
            tmp.setStHariLibur20(df.format(resultSetHari.getHariLibur20()));
            tmp.setStHariLibur30(df.format(resultSetHari.getHariLibur30()));
            tmp.setStHariLibur40(df.format(resultSetHari.getHariLibur40()));
            tmp.setStJamLembur(df.format(absensiPegawai.getJamLembur()));
            tmp.setJamLembur(absensiPegawai.getJamLembur());

            //keterangan lembur
            tmp.setKeterangan(absensiPegawai.getKeterangan());

            if (absensiPegawai.getBiayaLembur()==0d){
                tmp.setStBiayaLemburPerjam(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
            }else{
                tmp.setStBiayaLemburPerjam(CommonUtil.numbericFormat(BigDecimal.valueOf(absensiPegawai.getBiayaLembur()/absensiPegawai.getJamLembur()),"###,###"));
            }

            tmp.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(absensiPegawai.getBiayaLembur()),"###,###"));
            tmp.setBiayaLembur(absensiPegawai.getBiayaLembur());
            hasilBiayaLembur=hasilBiayaLembur+absensiPegawai.getBiayaLembur();
            hasilLamaLembur=hasilLamaLembur+realisasi;
            hasilJamLembur=hasilJamLembur+absensiPegawai.getJamLembur();

            hasilhariKerja15 =hasilhariKerja15+resultSetHari.getHariKerja15();
            hasilhariKerja20 =hasilhariKerja20+resultSetHari.getHariKerja20();
            hasilhariLibur20 =hasilhariLibur20+resultSetHari.getHariLibur20();
            hasilhariLibur30 =hasilhariLibur30+resultSetHari.getHariLibur30();
            hasilhariLibur40 =hasilhariLibur40+resultSetHari.getHariLibur40();

            listDataFinal.add(tmp);
        }
        AbsensiPegawai hasil = new AbsensiPegawai();
        hasil.setStTanggal("");
        hasil.setStLamaLembur(df.format(hasilLamaLembur));
        hasil.setStHariKerja15(df.format(hasilhariKerja15));
        hasil.setStHariKerja20(df.format(hasilhariKerja20));
        hasil.setStHariLibur20(df.format(hasilhariLibur20));
        hasil.setStHariLibur30(df.format(hasilhariLibur30));
        hasil.setStHariLibur40(df.format(hasilhariLibur40));
        hasil.setStJamLembur(df.format(hasilJamLembur));
        hasil.setStBiayaLemburPerjam("");
        hasil.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLembur),"###,###"));
        listDataFinal.add(hasil);

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listDataFinal);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        Branch branch = new Branch();
        try{
            branch = branchBo.getBranchById(branchIdForReport,"Y");
        }catch( HibernateException e){
        }
        if (branchIdForReport.equalsIgnoreCase("RS01")){
            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01);
        }else if (branchIdForReport.equalsIgnoreCase("RS02")){
            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02);
        }else if (branchIdForReport.equalsIgnoreCase("RS03")){
            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03);
        }else{
            reportParams.put("urlLogo",CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU);
        }
        String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
        reportParams.put("alamatUni", branch.getAlamatSurat()+","+stTanggal);
        reportParams.put("branchName", branch.getBranchName());
        reportParams.put("titleReport", "DAFTAR LEMBUR");
        reportParams.put("tanggalDari", getTglFrom());
        reportParams.put("tanggalSelesai", getTglTo());
        reportParams.put("itemDataSource", itemData);
        reportParams.put("nip", getNip());
        reportParams.put("date", stTanggal);
        reportParams.put("nama", nama);
        reportParams.put("posisi", posisi);
        if (golongan!=null){
            reportParams.put("golongan", golongan.replace("Golongan ",""));
        }else{
            reportParams.put("golongan", "-");
        }
        reportParams.put("date", stDate);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "printReportLembur");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printReportLembur] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printReportLembur] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print_report_lembur";

        }
        logger.info("[ReportAction.printReportLembur] end process <<<");

        return "success_print_report_lembur";
    }

    public String printReportRekapitulasiLembur() throws ParseException {
        logger.info("[ReportAction.printReportRekapitulasiLembur] start process >>>");
        List<AbsensiPegawai> listDataFinal = new ArrayList();
        List<AbsensiPegawai> listDataAbsensi = new ArrayList();
        String unit = "",bagian ="",golongan="", unitId="";
        bagian=getBagian();


        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        PositionBagianBo positionBagianBo= (PositionBagianBo) ctx.getBean("positionBagianBoProxy");

        List<Biodata> biodataList= biodataBo.getBiodataforAbsensi(getBranchId(),"",getBagian(),getNip());

        PositionBagian positionBagian = new PositionBagian();
        if (bagian!=null){
            if (!bagian.equalsIgnoreCase("")){
                positionBagian = positionBagianBo.getBagianById(getBagian(),"Y");
                bagian=positionBagian.getBagianName();
            }
        }
        Branch searchBranch = new Branch();
        searchBranch.setFlag("Y");
        searchBranch.setBranchId(getBranchId());
        List<Branch> branchList = branchBo.getByCriteria(searchBranch);
        for (Branch branch:branchList){
            unit=branch.getBranchName();
            unitId=branch.getBranchId();
        }
        for (Biodata biodata :biodataList){
            AbsensiPegawai search = new AbsensiPegawai();
            search.setNip(biodata.getNip());
            search.setFlag("Y");
            search.setLembur("Y");
            search.setTanggalAwal(CommonUtil.convertToDate(getTglFrom()));
            search.setTanggalAkhir(CommonUtil.convertToDate(getTglTo()));

            try {
                listDataAbsensi = absensiBo.getByCriteriaForRekapLembur(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteriaForLembur");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.printReportRekapitulasiLembur] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.printReportRekapitulasiLembur] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            List<AbsensiPegawai> absensiPegawaiList = new ArrayList<>();
            for (AbsensiPegawai absensiPegawai : listDataAbsensi){
                absensiPegawai.setNama(biodata.getNamaPegawai());
                absensiPegawai.setPositionName(biodata.getPositionName());
                absensiPegawai.setDivisi(biodata.getDivisiName());
                absensiPegawai.setUnit(biodata.getBranchName());
                absensiPegawai.setBagian(biodata.getBagianName());
                absensiPegawaiList.add(absensiPegawai);
            }

            listDataFinal.addAll(absensiPegawaiList);
        }

        List<AbsensiPegawai> listDataFinalTmp = listDataFinal;
        listDataFinal = new ArrayList<>();
        Double hasilLamaLembur= (double) 0,hasilhariKerja15= (double) 0,hasilhariKerja20= (double) 0,hasilJumlahhariKerja= (double) 0,hasilhariLibur20= (double) 0,hasilhariLibur30= (double) 0,hasilhariLibur40= (double) 0,hasilJumlahHariLibur= (double) 0,hasilJamLembur= (double)0 ,hasilBiayaLemburPerJam= (double) 0,hasilBiayaLembur= (double) 0;
        DecimalFormat df = new DecimalFormat("0.00");
        int i = 1;
        String nip="",nama="",bagianName="";
        for ( AbsensiPegawai absensiPegawai : listDataFinalTmp){
            //inisialisasi
            if (i==1){
                nip=absensiPegawai.getNip();
                nama=absensiPegawai.getNama();
                bagianName=absensiPegawai.getBagian();
            }
            if (nip.equalsIgnoreCase(absensiPegawai.getNip())){
                //grouping jam lembur
                double realisasi;
                if (absensiPegawai.getLamaLembur()<=absensiPegawai.getRealisasiJamLembur()){
                    realisasi= absensiPegawai.getLamaLembur();
                }else{
                    realisasi= absensiPegawai.getRealisasiJamLembur();
                }
                AbsensiPegawai resultSetHari;
                resultSetHari = absensiBo.getJamLembur(realisasi,absensiPegawai.getTipeHari());

                hasilLamaLembur = hasilLamaLembur+realisasi;
                hasilhariKerja15 = hasilhariKerja15+resultSetHari.getHariKerja15();
                hasilhariKerja20 = hasilhariKerja20+resultSetHari.getHariKerja20();
                hasilJumlahhariKerja = hasilhariKerja15+hasilhariKerja20;
                hasilhariLibur20 = hasilhariLibur20+resultSetHari.getHariLibur20();
                hasilhariLibur30 = hasilhariLibur30+resultSetHari.getHariLibur30();
                hasilhariLibur40 = hasilhariLibur40+resultSetHari.getHariLibur40();
                hasilJumlahHariLibur = hasilhariLibur20+hasilhariLibur30+hasilhariLibur40;
                hasilJamLembur = hasilJamLembur+absensiPegawai.getJamLembur();
                hasilBiayaLembur = hasilBiayaLembur+absensiPegawai.getBiayaLembur();

                if (hasilBiayaLembur==0){
                    hasilBiayaLemburPerJam = 0d;
                }else{
                    hasilBiayaLemburPerJam = hasilBiayaLembur/hasilJamLembur;
                }

                bagianName=absensiPegawai.getBagian();
            }else{
                AbsensiPegawai tmp = new AbsensiPegawai();

                tmp.setNo(String.valueOf(i));
                tmp.setNip(nip);
                tmp.setNama(nama);
                tmp.setBagian(bagianName);
                tmp.setStLamaLembur(df.format(hasilLamaLembur));
                tmp.setStHariKerja15(df.format(hasilhariKerja15));
                tmp.setStHariKerja20(df.format(hasilhariKerja20));
                tmp.setsJumlahHariKerja(df.format(hasilJumlahhariKerja));
                tmp.setStHariLibur20(df.format(hasilhariLibur20));
                tmp.setStHariLibur30(df.format(hasilhariLibur30));
                tmp.setStHariLibur40(df.format(hasilhariLibur40));
                tmp.setsJumlahHariLibur(df.format(hasilJumlahHariLibur));
                tmp.setStJamLembur(df.format(hasilJamLembur));
                tmp.setStBiayaLemburPerjam(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLemburPerJam),"###,###"));
                tmp.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLembur),"###,###"));

                listDataFinal.add(tmp);
                hasilLamaLembur = (double) 0;
                hasilhariKerja15 = (double) 0;
                hasilhariKerja20 = (double) 0;
                hasilJumlahhariKerja = (double) 0;
                hasilhariLibur20 = (double) 0;
                hasilhariLibur30 = (double) 0;
                hasilhariLibur40 = (double) 0;
                hasilJumlahHariLibur = (double) 0;
                hasilJamLembur = (double) 0;
                hasilBiayaLembur = (double) 0;
                hasilBiayaLemburPerJam = (double) 0;

                nip=absensiPegawai.getNip();
                nama=absensiPegawai.getNama();
                bagianName=absensiPegawai.getBagian();
                double realisasi;
                if (absensiPegawai.getLamaLembur()<=absensiPegawai.getRealisasiJamLembur()){
                    realisasi= absensiPegawai.getLamaLembur();
                }else{
                    realisasi= absensiPegawai.getRealisasiJamLembur();
                }
                AbsensiPegawai resultSetHari;
                resultSetHari = absensiBo.getJamLembur(realisasi,absensiPegawai.getTipeHari());

                hasilLamaLembur = hasilLamaLembur+realisasi;
                hasilhariKerja15 = hasilhariKerja15+resultSetHari.getHariKerja15();
                hasilhariKerja20 = hasilhariKerja20+resultSetHari.getHariKerja20();
                hasilJumlahhariKerja = hasilhariKerja15+hasilhariKerja20;
                hasilhariLibur20 = hasilhariLibur20+resultSetHari.getHariLibur20();
                hasilhariLibur30 = hasilhariLibur30+resultSetHari.getHariLibur30();
                hasilhariLibur40 = hasilhariLibur40+resultSetHari.getHariLibur40();
                hasilJumlahHariLibur = hasilhariLibur20+hasilhariLibur30+hasilhariLibur40;
                hasilJamLembur = hasilJamLembur+absensiPegawai.getJamLembur();
                hasilBiayaLembur = hasilBiayaLembur+absensiPegawai.getBiayaLembur();

                if (hasilBiayaLembur==0){
                    hasilBiayaLemburPerJam = 0d;
                }else{
                    hasilBiayaLemburPerJam = hasilBiayaLembur/hasilJamLembur;
                }
            }
            i++;
        }
        AbsensiPegawai data = new AbsensiPegawai();
        data.setNo(String.valueOf(i));
        data.setNip(nip);
        data.setNama(nama);
        data.setBagian(bagianName);
        data.setStLamaLembur(df.format(hasilLamaLembur));
        data.setStHariKerja15(df.format(hasilhariKerja15));
        data.setStHariKerja20(df.format(hasilhariKerja20));
        data.setsJumlahHariKerja(df.format(hasilJumlahhariKerja));
        data.setStHariLibur20(df.format(hasilhariLibur20));
        data.setStHariLibur30(df.format(hasilhariLibur30));
        data.setStHariLibur40(df.format(hasilhariLibur40));
        data.setsJumlahHariLibur(df.format(hasilJumlahHariLibur));
        data.setStJamLembur(df.format(hasilJamLembur));
//        data.setStLamaLembur(hasilLamaLembur.toString());
//        data.setStHariKerja15(hasilhariKerja15.toString());
//        data.setStHariKerja20(hasilhariKerja20.toString());
//        data.setsJumlahHariKerja(hasilJumlahhariKerja.toString());
//        data.setStHariLibur20(hasilhariLibur20.toString());
//        data.setStHariLibur30(hasilhariLibur30.toString());
//        data.setStHariLibur40(hasilhariLibur40.toString());
//        data.setsJumlahHariLibur(hasilJumlahHariLibur.toString());
//        data.setStJamLembur(hasilJamLembur.toString());
        data.setStBiayaLemburPerjam(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLemburPerJam),"###,###"));
        data.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLembur),"###,###"));
        listDataFinal.add(data);

        List<AbsensiPegawai> forReport = new ArrayList<>();
        String bagianPegawai = "";
        int a = 0;
        Double jJumlahJamSeluruhnya=(double)0, jJamKerja15=(double)0, jJamKerja20=(double)0, jJumlahJamKerja=(double)0, jJamlibur20=(double)0, jJamlibur30=(double)0, jJamlibur40=(double)0, jJumlahLibur=(double)0, jJumlahJamLemburPerhitungan=(double)0, jJumlahUpahLembur=(double)0;
        Double jJumlahJamSeluruhnyaAll=(double)0, jJamKerja15All=(double)0, jJamKerja20All=(double)0, jJumlahJamKerjaAll=(double)0, jJamlibur20All=(double)0, jJamlibur30All=(double)0, jJamlibur40All=(double)0, jJumlahLiburAll=(double)0, jJumlahJamLemburPerhitunganAll=(double)0, jJumlahUpahLemburAll=(double)0;

        for(AbsensiPegawai absensiPegawai: listDataFinal) {
            if (!bagianPegawai.equalsIgnoreCase(absensiPegawai.getBagian())){
                AbsensiPegawai tmp = new AbsensiPegawai();
                if (a!=0){
                    //Set Jumlah
                    tmp = new AbsensiPegawai();
                    tmp.setNo("");
                    tmp.setNip("JUMLAH");
                    tmp.setNama("");
                    tmp.setStLamaLembur(String.valueOf(jJumlahJamSeluruhnya));
                    tmp.setStHariKerja15(String.valueOf(jJamKerja15));
                    tmp.setStHariKerja20(String.valueOf(jJamKerja20));
                    tmp.setsJumlahHariKerja(String.valueOf(jJumlahJamKerja));
                    tmp.setStHariLibur20(String.valueOf(jJamlibur20));
                    tmp.setStHariLibur30(String.valueOf(jJamlibur30));
                    tmp.setStHariLibur40(String.valueOf(jJamlibur40));
                    tmp.setsJumlahHariLibur(String.valueOf(jJumlahLibur));
                    tmp.setStJamLembur(String.valueOf(jJumlahJamLemburPerhitungan));
                    tmp.setStBiayaLemburPerjam("");
                    tmp.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(jJumlahUpahLembur),"###,###"));
                    forReport.add(tmp);
                    jJumlahJamSeluruhnyaAll=jJumlahJamSeluruhnyaAll+jJumlahJamSeluruhnya;jJamKerja15All=jJamKerja15All+jJamKerja15;jJamKerja20All=jJamKerja20All+jJamKerja20;jJumlahJamKerjaAll=jJumlahJamKerjaAll+jJumlahJamKerja;jJamlibur20All=jJamlibur20All+jJamlibur20;jJamlibur30All=jJamlibur30All+jJamlibur30;jJamlibur40All=jJamlibur40All+jJamlibur40;jJumlahLiburAll=jJumlahLiburAll+jJumlahLibur;jJumlahJamLemburPerhitunganAll=jJumlahJamLemburPerhitunganAll+jJumlahJamLemburPerhitungan;
                    jJumlahUpahLemburAll=jJumlahUpahLemburAll+jJumlahUpahLembur;
                    jJumlahJamSeluruhnya=(double)0;jJamKerja15=(double)0;jJamKerja20=(double)0;jJumlahJamKerja=(double)0;jJamlibur20=(double)0;jJamlibur30=(double)0;jJamlibur40=(double)0;jJumlahLibur=(double)0;jJumlahJamLemburPerhitungan=(double)0;jJumlahUpahLembur=(double)0;
                }
                tmp = new AbsensiPegawai();
                tmp.setNo("");
                tmp.setNip(absensiPegawai.getBagian());
                tmp.setNama("");
                tmp.setStLamaLembur("");
                tmp.setStHariKerja15("");
                tmp.setStHariKerja20("");
                tmp.setsJumlahHariKerja("");
                tmp.setStHariLibur20("");
                tmp.setStHariLibur30("");
                tmp.setStHariLibur40("");
                tmp.setsJumlahHariLibur("");
                tmp.setStJamLembur("");
                tmp.setStBiayaLemburPerjam("");
                tmp.setStBiayaLembur("");
                forReport.add(tmp);
                bagianPegawai = absensiPegawai.getBagian();
            }
            forReport.add(absensiPegawai);
            NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
            NumberFormat nf1 = NumberFormat.getInstance(Locale.US);
//            DecimalFormat df1 =  new DecimalFormat("#.00",DecimalFormatSymbols.getInstance(Locale.US));
            jJumlahJamSeluruhnya+=nf.parse(absensiPegawai.getStLamaLembur()).doubleValue();
            jJamKerja15+=nf.parse(absensiPegawai.getStHariKerja15()).doubleValue();
            jJamKerja20+=nf.parse(absensiPegawai.getStHariKerja20()).doubleValue();
            jJumlahJamKerja+=nf.parse(absensiPegawai.getsJumlahHariKerja()).doubleValue();
            jJamlibur20+=nf.parse(absensiPegawai.getStHariLibur20()).doubleValue();
            jJamlibur30+=nf.parse(absensiPegawai.getStHariLibur30()).doubleValue();
            jJamlibur40+=nf.parse(absensiPegawai.getStHariLibur40()).doubleValue();
            jJumlahLibur+=nf.parse(absensiPegawai.getsJumlahHariLibur()).doubleValue();
            jJumlahJamLemburPerhitungan+=nf.parse(absensiPegawai.getStJamLembur()).doubleValue();
            jJumlahUpahLembur+=nf1.parse(absensiPegawai.getStBiayaLembur()).doubleValue();

            a++;
        }
        jJumlahJamSeluruhnyaAll=jJumlahJamSeluruhnyaAll+jJumlahJamSeluruhnya;jJamKerja15All=jJamKerja15All+jJamKerja15;jJamKerja20All=jJamKerja20All+jJamKerja20;jJumlahJamKerjaAll=jJumlahJamKerjaAll+jJumlahJamKerja;jJamlibur20All=jJamlibur20All+jJamlibur20;jJamlibur30All=jJamlibur30All+jJamlibur30;jJamlibur40All=jJamlibur40All+jJamlibur40;jJumlahLiburAll=jJumlahLiburAll+jJumlahLibur;jJumlahJamLemburPerhitunganAll=jJumlahJamLemburPerhitunganAll+jJumlahJamLemburPerhitungan;
        jJumlahUpahLemburAll=jJumlahUpahLemburAll+jJumlahUpahLembur;
        AbsensiPegawai tmp = new AbsensiPegawai();
        tmp.setNo("");
        tmp.setNip("JUMLAH");
        tmp.setNama("");
        tmp.setStLamaLembur(df.format(jJumlahJamSeluruhnya));
        tmp.setStHariKerja15(df.format(jJamKerja15));
        tmp.setStHariKerja20(df.format(jJamKerja20));
        tmp.setsJumlahHariKerja(df.format(jJumlahJamKerja));
        tmp.setStHariLibur20(df.format(jJamlibur20));
        tmp.setStHariLibur30(df.format(jJamlibur30));
        tmp.setStHariLibur40(df.format(jJamlibur40));
        tmp.setsJumlahHariLibur(df.format(jJumlahLibur));
        tmp.setStJamLembur(df.format(jJumlahJamLemburPerhitungan));
        tmp.setStBiayaLemburPerjam("");
        tmp.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(jJumlahUpahLembur),"###,###"));
        forReport.add(tmp);
        //memberi nomor
        List<AbsensiPegawai>finalRekapLembur=new ArrayList<>();
        int no=1;
        String nipTmp="";
        for (AbsensiPegawai hasilAkhir:forReport){
            if (!(nipTmp).equalsIgnoreCase(hasilAkhir.getNip())){
                if (("").equalsIgnoreCase(hasilAkhir.getNama())){
//                    hasilAkhir.setNip(hasilAkhir.getNip().replace("Bagian ",""));
                    no=1;
                }else{
                    hasilAkhir.setNo(String.valueOf(no));
                    no++;
                }
                finalRekapLembur.add(hasilAkhir);
                nipTmp=hasilAkhir.getNip();
            }
        }

        tmp = new AbsensiPegawai();
        tmp.setNo("");
        tmp.setNip("TOTAL SELURUHNYA");
        tmp.setNama("");
        tmp.setStLamaLembur(df.format(jJumlahJamSeluruhnyaAll));
        tmp.setStHariKerja15(df.format(jJamKerja15All));
        tmp.setStHariKerja20(df.format(jJamKerja20All));
        tmp.setsJumlahHariKerja(df.format(jJumlahJamKerjaAll));
        tmp.setStHariLibur20(df.format(jJamlibur20All));
        tmp.setStHariLibur30(df.format(jJamlibur30All));
        tmp.setStHariLibur40(df.format(jJamlibur40All));
        tmp.setsJumlahHariLibur(df.format(jJumlahLiburAll));
        tmp.setStJamLembur(df.format(jJumlahJamLemburPerhitunganAll));
        tmp.setStBiayaLemburPerjam("");
        tmp.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(jJumlahUpahLemburAll),"###,###"));
        finalRekapLembur.add(tmp);
        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(finalRekapLembur);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        if (bagian==null){
            bagian="";
        }
        Branch branch = new Branch();

        try{
            branch = branchBo.getBranchById(unitId,"Y");
        }catch( HibernateException e){

        }
        String logo ="";
        if (unitId.equalsIgnoreCase("RS01")){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
        }else if (unitId.equalsIgnoreCase("RS02")){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
        }else if (unitId.equalsIgnoreCase("RS03")){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
        }else{
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
        }
        String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
        reportParams.put("urlLogo", logo);
        reportParams.put("titleReport", "REKAPITULASI LEMBUR");
        reportParams.put("tanggalDari", getTglFrom());
        reportParams.put("tanggalSelesai", getTglTo());
        reportParams.put("itemDataSource", itemData);
        reportParams.put("date", stDate);
        reportParams.put("unit", unit);
        reportParams.put("bagian", bagian);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "printReportLembur");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printReportLembur] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printReportLembur] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print_report_lembur";

        }
        logger.info("[ReportAction.printReportLembur] end process <<<");

        return "success_print_report_rekap_lembur";
    }

    public String searchReportLembur() {
        logger.info("[AbsensiAction.searchReportLembur] start process >>>");
        List<AbsensiPegawai> listDataFinal = new ArrayList();
        List<Biodata> biodataList= new ArrayList();
        String unit = "";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        Branch searchBranch = new Branch();
        searchBranch.setFlag("Y");
        searchBranch.setBranchId(getBranchId());
        List<Branch> branchList = new ArrayList<>();
        try {
            branchList = branchBo.getByCriteria(searchBranch);
        }catch(GeneralBOException e){
            logger.error("[AbsensiAction.searchReportLembur] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving Branch List using criteria. \n" + e.getMessage());
        }
        for (Branch branch:branchList){
            unit=branch.getBranchName();
        }

        Lembur lembur = getLembur();
        try {
            biodataList = biodataBo.getBiodataforAbsensi(getBranchId(), "", lembur.getBagian(), lembur.getNip());
        } catch(GeneralBOException e){
            logger.error("[AbsensiAction.searchReportLembur] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving Biodata for Absensi, \n" + e.getMessage());
        }

        for (Biodata biodata : biodataList){
            List<AbsensiPegawai> listData = new ArrayList();
            List<AbsensiPegawai> listDataAbsensiPegawai = new ArrayList();
            AbsensiPegawai search = new AbsensiPegawai();
            search.setFlag("Y");
            search.setLembur("Y");
            search.setNip(biodata.getNip());
            search.setTanggalAwal(CommonUtil.convertToDate(lembur.getStTanggalAwal()));
            search.setTanggalAkhir(CommonUtil.convertToDate(lembur.getStTanggalAkhir()));
            try {
                listData = absensiBo.getByCriteriaForRekapLembur(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteriaForRekapLembur");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.searchReportLembur] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.searchReportLembur] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            for (AbsensiPegawai absensiPegawai : listData ){
                absensiPegawai.setNama(biodata.getNamaPegawai());
                absensiPegawai.setPositionName(biodata.getPositionName());
                absensiPegawai.setDivisi(biodata.getDivisiName());
                absensiPegawai.setUnit(biodata.getBranchName());
                absensiPegawai.setBagian(biodata.getBagianName());
                listDataAbsensiPegawai.add(absensiPegawai);
            }
            listDataFinal.addAll(listDataAbsensiPegawai);
        }

        List<AbsensiPegawai> listDataFinalTmp = listDataFinal;
        listDataFinal = new ArrayList<>();
        Double hasilLamaLembur= (double) 0,hasilhariKerja15= (double) 0,hasilhariKerja20= (double) 0,hasilJumlahhariKerja= (double) 0,hasilhariLibur20= (double) 0,hasilhariLibur30= (double) 0,hasilhariLibur40= (double) 0,hasilJumlahHariLibur= (double) 0,hasilJamLembur= (double)0 ,hasilBiayaLemburPerJam= (double) 0,hasilBiayaLembur= (double) 0;
        DecimalFormat df = new DecimalFormat("0.00");
        int i = 1;
        int length = listDataFinalTmp.size();
        String nip="",nama="", keterangan = "";
        for ( AbsensiPegawai absensiPegawai : listDataFinalTmp){
            //inisialisasi
            if (i==1){
                nip=absensiPegawai.getNip();
                nama=absensiPegawai.getNama();
            }
            if (nip.equalsIgnoreCase(absensiPegawai.getNip())){
                //grouping jam lembur
                double realisasi;
                if (absensiPegawai.getLamaLembur()<=absensiPegawai.getRealisasiJamLembur()){
                    realisasi= absensiPegawai.getLamaLembur();
                }else{
                    realisasi= absensiPegawai.getRealisasiJamLembur();
                }
                AbsensiPegawai resultSetHari;
                resultSetHari = absensiBo.getJamLembur(realisasi,absensiPegawai.getTipeHari());

                hasilLamaLembur = hasilLamaLembur+realisasi;
                hasilhariKerja15 = hasilhariKerja15+resultSetHari.getHariKerja15();
                hasilhariKerja20 = hasilhariKerja20+resultSetHari.getHariKerja20();
                hasilJumlahhariKerja = hasilhariKerja15+hasilhariKerja20;
                hasilhariLibur20 = hasilhariLibur20+resultSetHari.getHariLibur20();
                hasilhariLibur30 = hasilhariLibur30+resultSetHari.getHariLibur30();
                hasilhariLibur40 = hasilhariLibur40+resultSetHari.getHariLibur40();
                hasilJumlahHariLibur = hasilhariLibur20+hasilhariLibur30+hasilhariLibur40;
                hasilJamLembur = hasilJamLembur+absensiPegawai.getJamLembur();
                hasilBiayaLembur = hasilBiayaLembur+absensiPegawai.getBiayaLembur();
                if (hasilBiayaLembur==0){
                    hasilBiayaLemburPerJam= 0d;
                }else{
                    hasilBiayaLemburPerJam = hasilBiayaLembur/hasilJamLembur;
                }
            }else{
                AbsensiPegawai tmp = new AbsensiPegawai();

                tmp.setNo(String.valueOf(i));
                tmp.setNip(nip);
                tmp.setNama(nama);
                tmp.setStLamaLembur(df.format(hasilLamaLembur));
                tmp.setStHariKerja15(df.format(hasilhariKerja15));
                tmp.setStHariKerja20(df.format(hasilhariKerja20));
                tmp.setsJumlahHariKerja(df.format(hasilJumlahhariKerja));
                tmp.setStHariLibur20(df.format(hasilhariLibur20));
                tmp.setStHariLibur30(df.format(hasilhariLibur30));
                tmp.setStHariLibur40(df.format(hasilhariLibur40));
                tmp.setsJumlahHariLibur(df.format(hasilJumlahHariLibur));
                tmp.setStJamLembur(df.format(hasilJamLembur));
                tmp.setStBiayaLemburPerjam(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLemburPerJam),"###,###"));
                tmp.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLembur),"###,###"));
                tmp.setStTanggalDari(lembur.getStTanggalAwal());
                tmp.setStTanggalSelesai(lembur.getStTanggalAkhir());
                listDataFinal.add(tmp);
                hasilLamaLembur = (double) 0;
                hasilhariKerja15 = (double) 0;
                hasilhariKerja20 = (double) 0;
                hasilJumlahhariKerja = (double) 0;
                hasilhariLibur20 = (double) 0;
                hasilhariLibur30 = (double) 0;
                hasilhariLibur40 = (double) 0;
                hasilJumlahHariLibur = (double) 0;
                hasilJamLembur = (double) 0;
                hasilBiayaLembur = (double) 0;
                hasilBiayaLemburPerJam = (double) 0;

                nip=absensiPegawai.getNip();
                nama=absensiPegawai.getNama();

                double realisasi;
                if (absensiPegawai.getLamaLembur()<=absensiPegawai.getRealisasiJamLembur()){
                    realisasi= absensiPegawai.getLamaLembur();
                }else{
                    realisasi= absensiPegawai.getRealisasiJamLembur();
                }
                AbsensiPegawai resultSetHari;
                resultSetHari = absensiBo.getJamLembur(realisasi,absensiPegawai.getTipeHari());

                hasilLamaLembur = hasilLamaLembur+realisasi;
                hasilhariKerja15 = hasilhariKerja15+resultSetHari.getHariKerja15();
                hasilhariKerja20 = hasilhariKerja20+resultSetHari.getHariKerja20();
                hasilJumlahhariKerja = hasilhariKerja15+hasilhariKerja20;
                hasilhariLibur20 = hasilhariLibur20+resultSetHari.getHariLibur20();
                hasilhariLibur30 = hasilhariLibur30+resultSetHari.getHariLibur30();
                hasilhariLibur40 = hasilhariLibur40+resultSetHari.getHariLibur40();
                hasilJumlahHariLibur = hasilhariLibur20+hasilhariLibur30+hasilhariLibur40;
                hasilJamLembur = hasilJamLembur+absensiPegawai.getJamLembur();
                hasilBiayaLembur = hasilBiayaLembur+absensiPegawai.getBiayaLembur();
                if (hasilBiayaLembur==0){
                    hasilBiayaLemburPerJam = 0d;
                }else{
                    hasilBiayaLemburPerJam = hasilBiayaLembur/hasilJamLembur;
                }
            }
//            keterangan = absensiPegawai.getKeterangan();
            i++;
        }
        AbsensiPegawai data = new AbsensiPegawai();
        data.setNo(String.valueOf(i));
        data.setNip(nip);
        data.setNama(nama);
        data.setStLamaLembur(df.format(hasilLamaLembur));
        data.setStHariKerja15(df.format(hasilhariKerja15));
        data.setStHariKerja20(df.format(hasilhariKerja20));
        data.setsJumlahHariKerja(df.format(hasilJumlahhariKerja));
        data.setStHariLibur20(df.format(hasilhariLibur20));
        data.setStHariLibur30(df.format(hasilhariLibur30));
        data.setStHariLibur40(df.format(hasilhariLibur40));
        data.setsJumlahHariLibur(df.format(hasilJumlahHariLibur));
        data.setStJamLembur(df.format(hasilJamLembur));
        data.setStBiayaLemburPerjam(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLemburPerJam),"###,###"));
        data.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(hasilBiayaLembur),"###,###"));
        data.setStTanggalDari(lembur.getStTanggalAwal());
        data.setStTanggalSelesai(lembur.getStTanggalAkhir());
        data.setKeterangan(keterangan);
        listDataFinal.add(data);

        List<AbsensiPegawai> forReport = new ArrayList<>();
        int x=1;
        for(AbsensiPegawai absensiPegawai: listDataFinal) {
            if (!"".equalsIgnoreCase(absensiPegawai.getNip()) && absensiPegawai.getNip() != null) {
                absensiPegawai.setNo(String.valueOf(x));
                forReport.add(absensiPegawai);
            }
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultLembur");
        session.setAttribute("listOfResultLembur", forReport);
        logger.info("[ReportAction.searchReportLembur] end process <<<");
        return "success_report_lembur";
    }

    public List<AbsensiPegawai> getListUangMakan(String tanggalDari , String tanggalSampai , String unitId,String bidangId) {
        logger.info("[ReportAction.printReportKPIUnit] start process >>>");
        List<AbsensiPegawai> listDataFinal = new ArrayList();
        String unit = "";
        String divisi="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        DepartmentBo departmentBo = (DepartmentBo) ctx.getBean("departmentBoProxy");

        if (!bidangId.equalsIgnoreCase("")){
            Department searchDepartment=new Department();
            searchDepartment.setDepartmentId(bidangId);
            searchDepartment.setFlag("Y");
            List<Department> departmentList = departmentBo.getByCriteria(searchDepartment);
            for (Department department:departmentList){
                divisi = department.getDepartmentName();
            }
        }
        Branch searchBranch = new Branch();
        searchBranch.setFlag("Y");
        searchBranch.setBranchId(unitId);
        List<Branch> branchList = branchBo.getByCriteria(searchBranch);
        for (Branch branch:branchList){
            unit=branch.getBranchName();
        }
        Date startDate = CommonUtil.convertToDate(tanggalDari);
        Date endDate = CommonUtil.convertToDate(tanggalSampai);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DATE,1);
        java.util.Date date;
        for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            List<AbsensiPegawai> listData = new ArrayList();
            AbsensiPegawai search = new AbsensiPegawai();
            search.setTanggal(new Date(date.getTime()));
            search.setFlag("Y");
            search.setBranchId(unitId);
            if (!bidangId.equalsIgnoreCase("")){
                search.setDivisiId(bidangId);
            }
            try {
                listData = absensiBo.getByCriteriaForReportUangMakan(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.inquiry");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.search] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.search] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            listDataFinal.addAll(listData);
        }
        Comparator<AbsensiPegawai> comparator = new Comparator<AbsensiPegawai>() {
            @Override
            public int compare(AbsensiPegawai left, AbsensiPegawai right) {
                String awal =left.getNip().replace("-","");
                String akhir =right.getNip().replace("-","");
                Long angka1 = Long.parseLong(awal);
                Long angka2 = Long.parseLong(akhir);
                return (int) (angka1-angka2);
            }
        };
        Collections.sort(listDataFinal, comparator);
        List<AbsensiPegawai> listDataFinalTmp = listDataFinal;

        listDataFinal = new ArrayList<>();
        String nip="";
        AbsensiPegawai tmp = new AbsensiPegawai();
        int index = 0;
        int totalAbsensi=0;
        int jamMasukDb = 0;
        int jamPulangDb = 0;
        BigDecimal totalUangMakan= BigDecimal.valueOf(0);
        for ( AbsensiPegawai absensiPegawai : listDataFinalTmp){
            Date tanggal = CommonUtil.convertToDate(absensiPegawai.getStTanggal());
            Calendar cal = Calendar.getInstance();
            cal.setTime(tanggal);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            JamKerjaBo jamKerjaBo = (JamKerjaBo) ctx.getBean("jamKerjaBoProxy");
            JamKerja jamKerja = new JamKerja();
            jamKerja.setHariKerja(day);
            jamKerja.setFlag("Y");
            List<JamKerja>jamKerjaList = jamKerjaBo.getByCriteria(jamKerja);
            for (JamKerja jamKerja1 : jamKerjaList){
                jamMasukDb = Integer.valueOf(jamKerja1.getJamAwalKerja().replace(":",""));
                jamPulangDb = Integer.valueOf(jamKerja1.getJamAwalKerja().replace(":",""));
            }
            if (index!=0&&!nip.equalsIgnoreCase(absensiPegawai.getNip())){
                tmp.setNip(absensiPegawai.getNip());
                tmp.setNama(absensiPegawai.getNama());
                tmp.setDivisi(absensiPegawai.getDivisi());
                tmp.setJamMasuk(absensiPegawai.getJamMasuk());
                tmp.setJamPulang(absensiPegawai.getJamPulang());
                tmp.setStTanggal(absensiPegawai.getStTanggal());
                tmp.setFlagUangMakan(absensiPegawai.getFlagUangMakan());
                tmp.setApprovalFlag(absensiPegawai.getApprovalFlag());

                absensiPegawai.setNama("JUMLAH");
                absensiPegawai.setNip("");
                absensiPegawai.setDivisi("");
                absensiPegawai.setJamMasuk("");
                absensiPegawai.setJamPulang("");
                absensiPegawai.setStTanggal("");
                absensiPegawai.setAbsensi(String.valueOf(totalAbsensi));
                absensiPegawai.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(totalAbsensi*17500),"###,###"));
                totalUangMakan = totalUangMakan.add(BigDecimal.valueOf(totalAbsensi*17500));
                index++;
                listDataFinal.add(absensiPegawai);

                absensiPegawai = new AbsensiPegawai();
                absensiPegawai.setNama(tmp.getNama());
                absensiPegawai.setNip(tmp.getNip());
                absensiPegawai.setDivisi(tmp.getDivisi());
                absensiPegawai.setJamMasuk(tmp.getJamMasuk());
                absensiPegawai.setJamPulang(tmp.getJamPulang());
                absensiPegawai.setStTanggal(tmp.getStTanggal());
                absensiPegawai.setApprovalFlag(tmp.getApprovalFlag());
                absensiPegawai.setFlagUangMakan(tmp.getFlagUangMakan());
            }
            if (!absensiPegawai.getNip().equalsIgnoreCase(nip)) {
                if (!absensiPegawai.getJamMasuk().equalsIgnoreCase("-")&&!absensiPegawai.getJamPulang().equalsIgnoreCase("-")&&absensiPegawai.getApprovalFlag()==null&&absensiPegawai.getFlagUangMakan()==null){
                    if (absensiPegawai.getJamMasuk()!=null||absensiPegawai.getJamKeluar()!=null||absensiPegawai.getApprovalFlag()==null){
                        if (!("").equalsIgnoreCase(absensiPegawai.getJamMasuk())&&!("").equalsIgnoreCase(absensiPegawai.getJamKeluar())&&absensiPegawai.getApprovalFlag()==null){
                            if (Integer.parseInt(absensiPegawai.getJamMasuk().replace(":",""))<=jamMasukDb&&Integer.parseInt(absensiPegawai.getJamPulang().replace(":",""))>=jamPulangDb){
                                totalAbsensi=1;
                                nip = absensiPegawai.getNip();
                                absensiPegawai.setAbsensi("1");
                                absensiPegawai.setStUangmakan("17,500");
                                index++;
                                listDataFinal.add(absensiPegawai);
                            }else if ("Y".equalsIgnoreCase(absensiPegawai.getFlagUangMakan())&&"Y".equalsIgnoreCase(absensiPegawai.getApprovalFlag())){
                                totalAbsensi=1;
                                nip = absensiPegawai.getNip();
                                absensiPegawai.setAbsensi("1");
                                absensiPegawai.setStUangmakan("17,500");
                                index++;
                                listDataFinal.add(absensiPegawai);
                            }
                            else{
                                totalAbsensi=0;
                                nip = absensiPegawai.getNip();
                                absensiPegawai.setAbsensi("0");
                                absensiPegawai.setStUangmakan("0");
                                index++;
                                listDataFinal.add(absensiPegawai);
                            }
                        }
                    }
                }else if("Y".equalsIgnoreCase(absensiPegawai.getApprovalFlag())&&"Y".equalsIgnoreCase(absensiPegawai.getFlagUangMakan())){
                    totalAbsensi=1;
                    nip = absensiPegawai.getNip();
                    absensiPegawai.setAbsensi("1");
                    absensiPegawai.setStUangmakan("17,500");
                    index++;
                    listDataFinal.add(absensiPegawai);
                }else{
                    totalAbsensi=0;
                    nip = absensiPegawai.getNip();
                    absensiPegawai.setAbsensi("0");
                    absensiPegawai.setStUangmakan("0");
                    index++;
                    listDataFinal.add(absensiPegawai);
                }

            }else if (nip.equalsIgnoreCase(absensiPegawai.getNip())){
                if (!absensiPegawai.getJamMasuk().equalsIgnoreCase("-")&&!absensiPegawai.getJamPulang().equalsIgnoreCase("-")&&absensiPegawai.getApprovalFlag()==null&&absensiPegawai.getFlagUangMakan()==null){
                    if (Integer.parseInt(absensiPegawai.getJamMasuk().replace(":",""))<=jamMasukDb&&Integer.parseInt(absensiPegawai.getJamPulang().replace(":",""))>=jamPulangDb){
                        absensiPegawai.setNip("");
                        absensiPegawai.setNama("");
                        absensiPegawai.setDivisi("");
                        absensiPegawai.setAbsensi("1");
                        absensiPegawai.setStUangmakan("17,500");
                        totalAbsensi=totalAbsensi+1;
                        listDataFinal.add(absensiPegawai);
                        index++;
                    }else if ("Y".equalsIgnoreCase(absensiPegawai.getApprovalFlag())&&"Y".equalsIgnoreCase(absensiPegawai.getFlagUangMakan())){
                        absensiPegawai.setNip("");
                        absensiPegawai.setNama("");
                        absensiPegawai.setDivisi("");
                        absensiPegawai.setAbsensi("1");
                        absensiPegawai.setStUangmakan("17,500");
                        totalAbsensi=totalAbsensi+1;
                        listDataFinal.add(absensiPegawai);
                        index++;
                    }
                    else{
                        absensiPegawai.setNip("");
                        absensiPegawai.setNama("");
                        absensiPegawai.setDivisi("");
                        absensiPegawai.setAbsensi("0");
                        absensiPegawai.setStUangmakan("0");
                        listDataFinal.add(absensiPegawai);
                        index++;
                    }
                }else if ("Y".equalsIgnoreCase(absensiPegawai.getApprovalFlag())&&"Y".equalsIgnoreCase(absensiPegawai.getFlagUangMakan())){
                    absensiPegawai.setNip("");
                    absensiPegawai.setNama("");
                    absensiPegawai.setDivisi("");
                    absensiPegawai.setAbsensi("1");
                    absensiPegawai.setStUangmakan("17,500");
                    totalAbsensi=totalAbsensi+1;
                    listDataFinal.add(absensiPegawai);
                    index++;
                }else{
                    absensiPegawai.setNip("");
                    absensiPegawai.setNama("");
                    absensiPegawai.setDivisi("");
                    absensiPegawai.setAbsensi("0");
                    absensiPegawai.setStUangmakan("0");
                    listDataFinal.add(absensiPegawai);
                    index++;
                }
            }
        }
        AbsensiPegawai absensiPegawai = new AbsensiPegawai();
        absensiPegawai.setNama("JUMLAH");
        absensiPegawai.setNip("");
        absensiPegawai.setDivisi("");
        absensiPegawai.setJamMasuk("");
        absensiPegawai.setJamPulang("");
        absensiPegawai.setStTanggal("");
        absensiPegawai.setAbsensi(String.valueOf(totalAbsensi));
        absensiPegawai.setStUangmakan(CommonUtil.numbericFormat(BigDecimal.valueOf(totalAbsensi*17500),"###,###"));
        listDataFinal.add(absensiPegawai);

        totalUangMakan = totalUangMakan.add(BigDecimal.valueOf(totalAbsensi*17500));

        absensiPegawai = new AbsensiPegawai();
        absensiPegawai.setNama("TOTAL UANG MAKAN ABSENSI :");
        absensiPegawai.setNip("");
        absensiPegawai.setDivisi("");
        absensiPegawai.setJamMasuk("");
        absensiPegawai.setJamPulang("");
        absensiPegawai.setStTanggal("");
        absensiPegawai.setAbsensi("");

        absensiPegawai.setStUangmakan(CommonUtil.numbericFormat(totalUangMakan,"###,###"));
        listDataFinal.add(absensiPegawai);

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listDataFinal);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "Laporan Uang Makan Absensi");
        reportParams.put("tanggalDari", tanggalDari);
        reportParams.put("tanggalSelesai", tanggalSampai);
        reportParams.put("itemDataSource", itemData);
        reportParams.put("branchId", unit);
        reportParams.put("divisi", divisi);
        reportParams.put("date", stDate);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "printReportKPIUnit");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printReportKPIUnit] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printReportKPIUnit] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[ReportAction.printReportKPIUnit] end process <<<");

        return listDataFinal;
    }

    public String refreshAbsensi(String nip,String stTanggal) {
        logger.info("[AbsensiAction.refreshAbsensi start process >>>");
        Date tanggal = CommonUtil.convertStringToDate(stTanggal);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo= (AbsensiBo) ctx.getBean("absensiBoProxy");
        String status ="";
        try {
            status= absensiBo.refreshDataAbsensi(nip,tanggal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "absensiBoProxy.refreshDataAbsensi");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.refreshAbsensi] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AbsensiAction.refreshAbsensi] Error when searching lembur by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        logger.info("[AbsensiAction.refreshAbsensi] end process <<<");
        return status;
    }
    public String getDataFromMesin() throws Exception {
        logger.info("[AbsensiAction.getDataFromMesin] start process >>>");
        String status="00";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        MesinAbsensi mesinAbsensi = new MesinAbsensi();
        mesinAbsensi.setCreatedWho(CommonUtil.userIdLogin());
        mesinAbsensi.setLastUpdate(updateTime);
        try {
            absensiBo.getDataFromMesin(mesinAbsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            status="fail";
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.inquiry");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.search] Error when saving error,", e1);
            }
            logger.error("[AbsensiAction.search] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
        }
        return status;
    }

    public String getAllDataFromMesin() throws Exception {
        logger.info("[AbsensiAction.getDataFromMesin] start process >>>");
        String status="00";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        MesinAbsensi mesinAbsensi = new MesinAbsensi();
        mesinAbsensi.setCreatedWho(CommonUtil.userIdLogin());
        mesinAbsensi.setLastUpdate(updateTime);
        try {
            absensiBo.getAllDataFromMesin(mesinAbsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            status="fail";
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.inquiry");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.search] Error when saving error,", e1);
            }
            logger.error("[AbsensiAction.search] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
        }
        return status;
    }
    public void inquiry() throws Exception {
        AbsensiPegawai absen = getAbsensiPegawai();
        String tanggalAwal = absen.getStTanggalDari();
        String tanggalAkhir = absen.getStTanggalAkhir();
        String branchId = absen.getBranchId();
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultMesinAbsensi");
        session.removeAttribute("listOfResultAbsensiFinal");
        logger.info("[AbsensiAction.inquiry] start process >>>");
        List<MesinAbsensi> mesinAbsensiList= new ArrayList<>();
        List<MesinAbsensi> mesinAbsensiFinalList= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        Date startDate = CommonUtil.convertToDate(tanggalAwal);
        Date endDate = CommonUtil.convertToDate(tanggalAkhir);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DATE,1);
        java.util.Date date;
        Boolean awalTanggal=false;
        for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            if (date==startDate){
                awalTanggal=true;
            }
            try {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String tanggal = df.format(date);
                mesinAbsensiList = absensiBo.inquiry(tanggal,awalTanggal, absen.getCekPegawaiStatus(), branchId);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.inquiry");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.inquiry] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.inquiry] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            mesinAbsensiFinalList.addAll(mesinAbsensiList);
        }

        session.setAttribute("listOfResultMesinAbsensi", mesinAbsensiFinalList);
        session.removeAttribute("listOfResultAbsensiFinal");
    }
    public void inquiryTambahan() throws Exception {
        logger.info("[AbsensiAction.inquiry] start process >>>");
        AbsensiPegawai absen = getAbsensiPegawai();
        String tanggalAwal = absen.getStTanggalDari();
        String tanggalAkhir = absen.getStTanggalAkhir();
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultMesinAbsensi");
        session.removeAttribute("listOfResultAbsensiFinal");
        List<PegawaiTambahanAbsensi> pegawaiTambahanAbsensiList= new ArrayList<>();
        List<PegawaiTambahanAbsensi> pegawaiTambahanAbsensiFinalList= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        Date startDate = CommonUtil.convertToDate(tanggalAwal);
        Date endDate = CommonUtil.convertToDate(tanggalAkhir);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DATE,1);
        java.util.Date date;
        Boolean awalTanggal=false;
        for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            if (date==startDate){
                awalTanggal=true;
            }
            try {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String tanggal = df.format(date);
                pegawaiTambahanAbsensiList = absensiBo.inquiryTambahan(tanggal,awalTanggal);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.inquiry");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.inquiry] Error when saving error,", e1);
                }
                logger.error("[AbsensiAction.inquiry] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            }
            pegawaiTambahanAbsensiFinalList.addAll(pegawaiTambahanAbsensiList);
        }
        session.setAttribute("listOfResultMesinAbsensi", pegawaiTambahanAbsensiFinalList);
        session.removeAttribute("listOfResultAbsensiFinal");
    }

    public List<MesinAbsensi> loadAbsensi(){
        List<MesinAbsensi> mesinAbsensiList= new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        mesinAbsensiList= (List<MesinAbsensi>) session.getAttribute("listOfResultMesinAbsensi");

        return mesinAbsensiList;
    }
    public List<PegawaiTambahanAbsensi> loadAbsensiTambahan(){
        List<PegawaiTambahanAbsensi> pegawaiTambahanAbsensiList= new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        pegawaiTambahanAbsensiList= (List<PegawaiTambahanAbsensi>) session.getAttribute("listOfResultMesinAbsensi");

        return pegawaiTambahanAbsensiList;
    }
    public List<AbsensiPegawai> cariAbseni(String nip, String tanggal, String statusabsensi) throws Exception {
        logger.info("[AbsensiAction.inquiry] start process >>>");
        List<AbsensiPegawai> absensiPegawai= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");

        try {
            absensiPegawai = absensiBo.cariAbseniSys(nip, tanggal, statusabsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.inquiry");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.search] Error when saving error,", e1);
            }
            logger.error("[AbsensiAction.search] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return absensiPegawai;
    }
    public List<AbsensiPegawai> searchDetailLembur(String nip, String tanggal) throws Exception {
        logger.info("[AbsensiAction.searchDetailLembur] start process >>>");
        List<AbsensiPegawai> absensiPegawai= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");

        try {
            absensiPegawai = absensiBo.searchDetailLembur(nip, tanggal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.searchDetailLembur");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.searchDetailLembur] Error when saving error,", e1);
            }
            logger.error("[AbsensiAction.searchDetailLembur] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return absensiPegawai;
    }
    @Override
    public String downloadXls() {
        logger.info("[FunctionAction.downloadXls] start process >>>");
        List<AbsensiPegawai> listDataFinal = new ArrayList<>() ;
        String tglTo = getTglTo();
        String tglFrom = getTglFrom();
        String branchId = getBranchId();
        String divisiId = getDivisiId();
        listDataFinal = getListUangMakan(tglFrom,tglTo,branchId,divisiId);
        AbsensiPegawai absensiPegawai = new AbsensiPegawai();

        CellDetail cellDetail;
        RowData rowData;
        List listOfData = new ArrayList();
        List listOfCell;
        List listOfColumn = new ArrayList();
        String titleReport = "Report Absensi";

        listOfColumn.add("Nip");
        listOfColumn.add("Nama");
        listOfColumn.add("Bagian");
        listOfColumn.add("Tanggal");
        listOfColumn.add("Datang");
        listOfColumn.add("Pulang");
        listOfColumn.add("Absensi");
        listOfColumn.add("Uang Makan");

        for (AbsensiPegawai data : listDataFinal) {
            rowData = new RowData();
            listOfCell = new ArrayList();

            //Nip
            cellDetail = new CellDetail();
            cellDetail.setCellID(0);
            cellDetail.setValueCell(data.getNip());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Nama
            cellDetail = new CellDetail();
            cellDetail.setCellID(1);
            cellDetail.setValueCell(data.getNama());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Bagian
            cellDetail = new CellDetail();
            cellDetail.setCellID(2);
            cellDetail.setValueCell(data.getDivisi());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Tanggal
            cellDetail = new CellDetail();
            cellDetail.setCellID(3);
            cellDetail.setValueCell(data.getStTanggal());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Datang
            cellDetail = new CellDetail();
            cellDetail.setCellID(4);
            cellDetail.setValueCell(data.getJamMasuk());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Pulang
            cellDetail = new CellDetail();
            cellDetail.setCellID(5);
            cellDetail.setValueCell(data.getJamPulang());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Absensi
            cellDetail = new CellDetail();
            cellDetail.setCellID(6);
            cellDetail.setValueCell(data.getAbsensi());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Uang Makan
            cellDetail = new CellDetail();
            cellDetail.setCellID(7);
            cellDetail.setValueCell(data.getStUangmakan());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);


            rowData.setListOfCell(listOfCell);
            listOfData.add(rowData);
        }
        HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, null, listOfColumn, listOfData, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            wb.write(baos);
        } catch (IOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.downloadXls");
            } catch (GeneralBOException e1) {
                logger.error("[FunctionAction.downloadXls] Error when downloading,", e1);
            }
            logger.error("[FunctionAction.downloadXls] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
            return ERROR;
        }

        setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
        setContentDisposition("filename=\"" + "ReportAbsensi.${documentFormat}\"");
        logger.info("[FunctionAction.downloadXls] end process <<<");
        return "downloadXls";
    }

    public String reportLembur() {
        logger.info("[AbsensiAction.report] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultLembur");
        logger.info("[AbsensiAction.report] end process >>>");
        return "report_lembur";
    }

    public String printReportAbsensiTriwulan() {
        logger.info("[ReportAction.printReportAbsensiTriwulan] start process >>>");
        List<AbsensiTriwulanDTO> listDataFinal = new ArrayList<>();
        String unit = "",bagian =getBagian(),nip=getNip(),stTanggalAwal=getTglFrom(),stTanggalAkhir=getTglTo();
        Branch searchBranch = new Branch();
        searchBranch.setFlag("Y");
        searchBranch.setBranchId(getBranchId());

        AbsensiPegawai search = new AbsensiPegawai();
        search.setFlag("Y");
        search.setLembur("Y");
        search.setTanggalAwal(CommonUtil.convertToDate(getTglFrom()));
        search.setTanggalAkhir(CommonUtil.convertToDate(getTglTo()));

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        PositionBagianBo positionBagianBo= (PositionBagianBo) ctx.getBean("positionBagianBoProxy");

        List<PositionBagian> positionBagians = new ArrayList<>();
        PositionBagian positionBagian2 = new PositionBagian();
        positionBagian2.setBranchId(getBranchId());
        positionBagians=positionBagianBo.getBagian(positionBagian2);

        List<Branch> branchList = branchBo.getByCriteria(searchBranch);
        for (Branch branch:branchList){
            unit=branch.getBranchName();
        }
        PositionBagian positionBagian = new PositionBagian();
        if (!getBagian().equalsIgnoreCase("")){
            positionBagian = positionBagianBo.getBagianById(getBagian(),"Y");
            bagian=positionBagian.getBagianName();
        }
        try {
            listDataFinal = absensiBo.searchBiodataForTriwulan(getBranchId(),nip,stTanggalAwal,stTanggalAkhir,getBagian());
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.searchDetailLembur");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.searchDetailLembur] Error when saving error,", e1);
            }
            logger.error("[AbsensiAction.searchDetailLembur] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        List<AbsensiTriwulanDTO> forReport = new ArrayList<>();
        if (!("").equalsIgnoreCase(getBagian())){
            String bagianPegawai = getBagian();
            int a =1;
            int z=1;
            for(AbsensiTriwulanDTO absensi: listDataFinal) {
                if (bagianPegawai.equalsIgnoreCase(absensi.getBagian())){
                    absensi.setNo(String.valueOf(a));
                    forReport.add(absensi);
                    a++;
                }
            }
        }else{
            String bagianPegawai = "";
            char x='A';
            int a =1;
            int z=1;
            for (PositionBagian positionBagian1: positionBagians){
                for(AbsensiTriwulanDTO absensi: listDataFinal) {
                    if (!bagianPegawai.equalsIgnoreCase(positionBagian1.getBagianName())){
                        AbsensiTriwulanDTO tmp = new AbsensiTriwulanDTO();
                        if (z!=1){
                            tmp.setNo("");
                            tmp.setNama("");
                            tmp.setJabatan("");
                            tmp.setJatahCuti("");
                            tmp.setHariKerja("");
                            tmp.setMasukKurangJadwal("");
                            tmp.setTerlambatKurang60("");
                            tmp.setTerlambatLebih60("");
                            tmp.setMasukKerjaKantor("");
                            tmp.setKeteranganTerlambat("");
                            tmp.setTidakAbsenMasuk("");
                            tmp.setTidakAbsenPulang("");
                            tmp.setJmlSakit("");
                            tmp.setJmlCuti("");
                            tmp.setJmlDinas("");
                            tmp.setJmlLain2("");
                            tmp.setTotalTidakMasuk("");
                            tmp.setKetLain2("");
                            tmp.setTanpaKeterangan("");
                            tmp.setPulangTidakSesuai("");
                            forReport.add(tmp);
                        }

                        tmp = new AbsensiTriwulanDTO();
                        tmp.setNo(String.valueOf(x));
                        tmp.setNama(positionBagian1.getBagianName());
                        tmp.setJabatan("");
                        tmp.setJatahCuti("");
                        tmp.setHariKerja("");
                        tmp.setMasukKurangJadwal("");
                        tmp.setTerlambatKurang60("");
                        tmp.setTerlambatLebih60("");
                        tmp.setMasukKerjaKantor("");
                        tmp.setKeteranganTerlambat("");
                        tmp.setTidakAbsenMasuk("");
                        tmp.setTidakAbsenPulang("");
                        tmp.setJmlSakit("");
                        tmp.setJmlCuti("");
                        tmp.setJmlDinas("");
                        tmp.setJmlLain2("");
                        tmp.setTotalTidakMasuk("");
                        tmp.setKetLain2("");
                        tmp.setTanpaKeterangan("");
                        tmp.setPulangTidakSesuai("");
                        forReport.add(tmp);
                        bagianPegawai = positionBagian1.getBagianName();
                        x++;
                        a=1;
                    }
                    if (positionBagian1.getBagianName().equalsIgnoreCase(absensi.getBagianName())){
                        absensi.setNo(String.valueOf(a));
                        if (absensi.getJabatan()==null){
                            absensi.setJabatan("-");
                        }
                        forReport.add(absensi);
                        a++;
                    }
                    z++;
                }
            }
        }
        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(forReport);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "DAFTAR EVALUASI ABSENSI DAN TIDAK MASUK KERJA");
        reportParams.put("tanggalDari", getTglFrom());
        reportParams.put("tanggalSelesai", getTglTo());
        reportParams.put("itemDataSource", itemData);
        reportParams.put("date", stDate);
        reportParams.put("unit", unit);
        reportParams.put("bagian", bagian);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "printReportLembur");
            } catch (GeneralBOException e1) {
                logger.error("[ReportAction.printReportLembur] Error when downloading ,", e1);
            }
            logger.error("[ReportAction.printReportLembur] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print_report_lembur";

        }
        logger.info("[ReportAction.printReportLembur] end process <<<");

        return "success_print_report_rekap_absensi";
    }

    public String reportAbsensi() {
        logger.info("[CutiPegawaiAction.reportAbsensi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultAbsensi");
        logger.info("[CutiPegawaiAction.reportAbsensi] end process >>>");
        return "report_absensi";
    }

    public String refreshAllAbsensi( String unit, String stTanggalAwal , String stTanggalSampai ){
        logger.info("[AbsensiAction.refreshAbsensi start process >>>");
        String status ="";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo= (AbsensiBo) ctx.getBean("absensiBoProxy");

        AbsensiPegawai searchAbsensiPegawai = new AbsensiPegawai();
        searchAbsensiPegawai.setUnit(unit);
        searchAbsensiPegawai.setStTanggalDari(stTanggalAwal);
        searchAbsensiPegawai.setStTanggalAkhir(stTanggalSampai);

        List<AbsensiPegawai> listOfSearchAbsensiPegawai = new ArrayList();
        try {
            listOfSearchAbsensiPegawai = absensiBoProxy.getByCriteria(searchAbsensiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiAction.refreshAbsensi] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AbsensiAction.refreshAbsensi] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        for (AbsensiPegawai data : listOfSearchAbsensiPegawai){
            try {
                status= absensiBo.refreshDataAbsensi(data.getNip(),data.getTanggal());
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[AbsensiAction.refreshAbsensi] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[AbsensiAction.refreshAbsensi] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                return ERROR;
            }
        }

        logger.info("[AbsensiAction.refreshAbsensi] end process <<<");
        return status;
    }

    public void cronInquiry()  {
        logger.info("[AbsensiAction.cronInquiry] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultAbsensiPegawai");
        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<>();
        AbsensiPegawai absen = getAbsensiPegawai();
        String tanggalAwal = absen.getStTanggalDari();
        String tanggalAkhir = absen.getStTanggalAkhir();
        String branchId = absen.getBranchId();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        Date startDate = CommonUtil.convertToDate(tanggalAwal);
        Date endDate = CommonUtil.convertToDate(tanggalAkhir);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DATE,1);
        java.util.Date date;

        if (!"".equalsIgnoreCase(tanggalAwal)&&!"".equalsIgnoreCase(tanggalAkhir)&&!"".equalsIgnoreCase(branchId)){
            for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                try {
                    AbsensiPegawai data = new AbsensiPegawai();
                    data.setTanggalUtil(date);
                    data.setBranchId(branchId);
                    data.setNip("");

                    absensiPegawaiList=absensiBo.cronInquiry(data);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = absensiBo.saveErrorMessage(e.getMessage(), "AbsensiBO.inquiry");
                    } catch (GeneralBOException e1) {
                        logger.error("[AbsensiAction.inquiry] Error when saving error,", e1);
                    }
                    logger.error("[AbsensiAction.inquiry] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                }
            }
        }else{
            String status="ERROR : [AbsensiAction.cronInquiry] ";
            if ("".equalsIgnoreCase(tanggalAwal)){
                status += "Tanggal Awal Belum diisi \n";
            }
            if ("".equalsIgnoreCase(tanggalAkhir)){
                status += "Tanggal Akhir Belum diisi \n";
            }
            if ("".equalsIgnoreCase(branchId)){
                status += "Unit Belum diisi \n";
            }
            logger.error(status);
            throw new GeneralBOException(status);
        }
        session.setAttribute("listOfResultAbsensiPegawai",absensiPegawaiList);

        logger.info("[AbsensiAction.cronInquiry] end process <<<");
    }


    public List<AbsensiOnCall> searchAbsensiOnCallSession() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<AbsensiOnCall> absensiOnCallList = (List<AbsensiOnCall>) session.getAttribute("listOfResultAbsensiOnCall");

        return absensiOnCallList;
    }

    public void searchAbsensiOnCall(String nip,String tanggal) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        AbsensiOnCall absensiOnCall= new AbsensiOnCall();
        absensiOnCall.setFlag("Y");
        absensiOnCall.setTanggal(CommonUtil.convertStringToDate(tanggal));
        absensiOnCall.setNip(nip);

        List<AbsensiOnCall> absensiOnCallList = absensiBo.getAbsensiOnCall(absensiOnCall);
        session.setAttribute("listOfResultAbsensiOnCall",absensiOnCallList);
    }
}