package com.neurix.simrs.transaksi.checkupdetail.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;

import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import com.neurix.simrs.master.keterangankeluar.bo.KeteranganKeluarBo;
import com.neurix.simrs.master.keterangankeluar.model.KeteranganKeluar;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;

import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckupDetailAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(CheckupDetailAction.class);
    private HeaderDetailCheckup headerDetailCheckup;
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;
    private DiagnosaBo diagnosaBoProxy;
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private TindakanBo tindakanBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private KelasRuanganBo kelasRuanganBoProxy;
    private RuanganBo ruanganBoProxy;
    private KeteranganKeluarBo keteranganKeluarBoProxy;
    private boolean enabledPoli = false;
    private boolean enabledAddPasien = false;
    private HeaderCheckup headerCheckup;

    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;

    private File fileUploadDoc;
    private String fileUploadDocFileName;
    private String fileUploadDocContentType;

    private String idResep;

    public String getIdResep() {
        return idResep;
    }

    public void setIdResep(String idResep) {
        this.idResep = idResep;
    }

    public File getFileUploadDoc() {
        return fileUploadDoc;
    }

    public void setFileUploadDoc(File fileUploadDoc) {
        this.fileUploadDoc = fileUploadDoc;
    }

    public String getFileUploadDocFileName() {
        return fileUploadDocFileName;
    }

    public void setFileUploadDocFileName(String fileUploadDocFileName) {
        this.fileUploadDocFileName = fileUploadDocFileName;
    }

    public String getFileUploadDocContentType() {
        return fileUploadDocContentType;
    }

    public void setFileUploadDocContentType(String fileUploadDocContentType) {
        this.fileUploadDocContentType = fileUploadDocContentType;
    }

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

    public HeaderCheckup getHeaderCheckup() {
        return headerCheckup;
    }

    public void setHeaderCheckup(HeaderCheckup headerCheckup) {
        this.headerCheckup = headerCheckup;
    }

    public boolean isEnabledAddPasien() {
        return enabledAddPasien;
    }

    public void setEnabledAddPasien(boolean enabledAddPasien) {
        this.enabledAddPasien = enabledAddPasien;
    }

    public boolean isEnabledPoli() {
        return enabledPoli;
    }

    public void setEnabledPoli(boolean enabledPoli) {
        this.enabledPoli = enabledPoli;
    }

    public void setKeteranganKeluarBoProxy(KeteranganKeluarBo keteranganKeluarBoProxy) {
        this.keteranganKeluarBoProxy = keteranganKeluarBoProxy;
    }

    public void setRuanganBoProxy(RuanganBo ruanganBoProxy) {
        this.ruanganBoProxy = ruanganBoProxy;
    }

    public void setKelasRuanganBoProxy(KelasRuanganBo kelasRuanganBoProxy) {
        this.kelasRuanganBoProxy = kelasRuanganBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public TindakanBo getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public KategoriTindakanBo getKategoriTindakanBoProxy() {
        return kategoriTindakanBoProxy;
    }

    public void setKategoriTindakanBoProxy(KategoriTindakanBo kategoriTindakanBoProxy) {
        this.kategoriTindakanBoProxy = kategoriTindakanBoProxy;
    }

    private String id;

    private List<Diagnosa> listOfComboDiagnosa = new ArrayList<>();
    private List<KategoriTindakan> listOfKategoriTindakan = new ArrayList<>();
    private List<KelasRuangan> listOfKelasRuangan = new ArrayList<>();
    private List<KeteranganKeluar> listOfKeterangan = new ArrayList<>();

    public List<KeteranganKeluar> getListOfKeterangan() {
        return listOfKeterangan;
    }

    public void setListOfKeterangan(List<KeteranganKeluar> listOfKeterangan) {
        this.listOfKeterangan = listOfKeterangan;
    }

    public List<KelasRuangan> getListOfKelasRuangan() {
        return listOfKelasRuangan;
    }

    public void setListOfKelasRuangan(List<KelasRuangan> listOfKelasRuangan) {
        this.listOfKelasRuangan = listOfKelasRuangan;
    }

    public List<KategoriTindakan> getListOfKategoriTindakan() {
        return listOfKategoriTindakan;
    }

    public void setListOfKategoriTindakan(List<KategoriTindakan> listOfKategoriTindakan) {
        this.listOfKategoriTindakan = listOfKategoriTindakan;
    }

    public List<Diagnosa> getListOfComboDiagnosa() {
        return listOfComboDiagnosa;
    }

    public void setListOfComboDiagnosa(List<Diagnosa> listOfComboDiagnosa) {
        this.listOfComboDiagnosa = listOfComboDiagnosa;
    }


    public void setDiagnosaBoProxy(DiagnosaBo diagnosaBoProxy) {
        this.diagnosaBoProxy = diagnosaBoProxy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public HeaderDetailCheckup getHeaderDetailCheckup() {
        return headerDetailCheckup;
    }

    public void setHeaderDetailCheckup(HeaderDetailCheckup headerDetailCheckup) {
        this.headerDetailCheckup = headerDetailCheckup;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CheckupDetailAction.logger = logger;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    @Override
    public String add() {
        logger.info("[CheckupDetailAction.add] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<HeaderDetailCheckup> listOfResult = (List) session.getAttribute("listOfResult");
        String id = getId();
        String jk = "";
        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (HeaderDetailCheckup detailCheckup : listOfResult) {
                    if (id.equalsIgnoreCase(detailCheckup.getNoCheckup())) {

                        detailCheckup.setStatusPeriksa("1");
                        detailCheckup.setFlag("Y");
                        detailCheckup.setAction("U");
                        detailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                        detailCheckup.setLastUpdateWho(CommonUtil.userLogin());

                        try {
                            checkupDetailBoProxy.saveEdit(detailCheckup);
                        } catch (GeneralBOException e){
                            logger.error("[CheckupDetailAction.add] Error when update checkup detail");
                        }

                        HeaderCheckup headerCheckup = getHeaderCheckup(detailCheckup.getNoCheckup());
                        detailCheckup.setIdPasien(headerCheckup.getIdPasien());
                        detailCheckup.setNamaPasien(headerCheckup.getNama());
                        detailCheckup.setAlamat(headerCheckup.getJalan());
                        detailCheckup.setDesa(headerCheckup.getNamaDesa());
                        detailCheckup.setKecamatan(headerCheckup.getNamaKecamatan());
                        detailCheckup.setKota(headerCheckup.getNamaKota());
                        detailCheckup.setProvinsi(headerCheckup.getNamaProvinsi());
                        detailCheckup.setIdPelayanan(headerCheckup.getIdPelayanan());
                        detailCheckup.setNamaPelayanan(headerCheckup.getNamaPelayanan());
                        if(headerCheckup.getJenisKelamin()!= null){
                            if("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())){
                                jk = "Perempuan";
                            }else{
                                jk = "laki-Laki";
                            }
                        }
                        detailCheckup.setJenisKelamin(jk);
                        detailCheckup.setTempatLahir(headerCheckup.getTempatLahir());
                        detailCheckup.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
                        detailCheckup.setTempatTglLahir(headerCheckup.getTempatLahir()+", "+headerCheckup.getTglLahir().toString());
                        detailCheckup.setNik(headerCheckup.getNoKtp());
                        detailCheckup.setIdJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
                        detailCheckup.setUrlKtp(headerCheckup.getUrlKtp());
                        detailCheckup.setTinggi(headerCheckup.getTinggi());
                        detailCheckup.setBerat(headerCheckup.getBerat());

                        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
                        detailCheckup.setJenisPeriksaPasien(jenisPriksaPasien.getKeterangan());

                        setHeaderDetailCheckup(detailCheckup);

                        break;
                    }
                }

            } else {
                setHeaderDetailCheckup(new HeaderDetailCheckup());
            }
        } else {
            setHeaderDetailCheckup(new HeaderDetailCheckup());
        }

        logger.info("[CheckupDetailAction.add] end process <<<");

        return "init_add";
    }



    @Override
    public String edit() {
        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {
        return "init_view";
    }

    @Override
    public String save() {
        return "init_save";
    }

    @Override
    public String search() {
        logger.info("[CheckupAction.search] start process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();

        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());

        try {
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getSearchRawatJalan(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        String userRoleLogin = CommonUtil.roleAsLogin();
        if(CommonConstant.ROLE_ADMIN_RS.equalsIgnoreCase(userRoleLogin)){
            setEnabledPoli(true);
        }

        if(CommonConstant.ROLE_ADMIN_IGD.equalsIgnoreCase(userRoleLogin)){
            setEnabledAddPasien(true);
        }

        if(CommonConstant.ROLE_ADMIN_POLI.equalsIgnoreCase(userRoleLogin)){
            HeaderDetailCheckup headerDetailCheckup1 = new HeaderDetailCheckup();
            headerDetailCheckup1.setIdPelayanan(CommonUtil.userPelayananIdLogin());
            setHeaderDetailCheckup(headerDetailCheckup1);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[CheckupAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[CheckupDetailAction.initForm] start process >>>");

        LocalDate today = LocalDate.now();
        String stId = today.toString();

        HeaderDetailCheckup checkupdetail = new HeaderDetailCheckup();
        String userRoleLogin = CommonUtil.roleAsLogin();
        String idPelayanan   = CommonUtil.userPelayananIdLogin();

        if(CommonConstant.ROLE_ADMIN_RS.equalsIgnoreCase(userRoleLogin)){
            setEnabledPoli(true);
        }

        if(CommonConstant.ROLE_ADMIN_POLI.equalsIgnoreCase(userRoleLogin)){
            checkupdetail.setIdPelayanan(idPelayanan);
        }

        if(CommonConstant.ROLE_ADMIN_IGD.equalsIgnoreCase(userRoleLogin)){
            checkupdetail.setIdPelayanan(idPelayanan);
            setEnabledAddPasien(true);
        }

        setHeaderDetailCheckup(checkupdetail);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupDetailAction.initForm] end process <<<");

        return "search";
    }

    private HeaderCheckup getHeaderCheckup(String noCheckup){
        logger.info("[CheckupDetailAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()){
            result = headerCheckupList.get(0);
        }

        logger.info("[CheckupDetailAction.getHeaderCheckup] end process <<<");
        return result;
    }

    public String getListComboDiagnosa(){
        logger.info("[CheckupDetailAction.getListComboDiagnosa] start process >>>");

        List<Diagnosa> diagnosaList = new ArrayList<>();
        Diagnosa diagnosa = new Diagnosa();

        try {
            diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListComboDiagnosa] Error when get diagnosa ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get diagnosa , please inform to your admin.\n" + e.getMessage());
        }

        listOfComboDiagnosa.addAll(diagnosaList);
        logger.info("[CheckupDetailAction.getListComboDiagnosa] end process <<<");
        return SUCCESS;
    }

    public String getListComboKategoriTindakan(){
        logger.info("[CheckupDetailAction.getListComboKategoriTIndakan] start process >>>");

        List<KategoriTindakan> kategoriTindakanList = new ArrayList<>();
        KategoriTindakan kategoriTindakan = new KategoriTindakan();

        try {
            kategoriTindakanList = kategoriTindakanBoProxy.getByCriteria(kategoriTindakan);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListComboKategoriTIndakan] Error when get kategori tindakan ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfKategoriTindakan.addAll(kategoriTindakanList);
        logger.info("[CheckupDetailAction.getListComboKategoriTIndakan] end process <<<");
        return SUCCESS;
    }

    public List<Tindakan> getListComboTindakan(String idKategoriTindakan){
        logger.info("[CheckupDetailAction.listOfDokter] start process >>>");

        List<Tindakan> tindakanList = new ArrayList<>();
        Tindakan tindakan = new Tindakan();
        tindakan.setIdKategoriTindakan(idKategoriTindakan);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");

        try {
            tindakanList = tindakanBo.getByCriteria(tindakan);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.listOfDokter] Error when searching data, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupDetailAction.listOfDokter] end process >>>");
        return tindakanList;
    }

    public String saveKeterangan (String noCheckup, String idDetailCheckup, String idKtg, String poli, String kelas, String kamar, String idDokter, String ket, String tglCekup, String ketCekup){
        logger.info("[CheckupDetailAction.saveKeterangan] start process >>>");

        String status = "error";
        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
        headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
        headerDetailCheckup.setStatusPeriksa("3");
        headerDetailCheckup.setFlag("Y");
        headerDetailCheckup.setAction("U");
        String tglCheckup = tglCekup;

        if(tglCheckup != null && !"".equalsIgnoreCase(tglCheckup)){

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                java.util.Date date = format.parse(tglCheckup);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                headerDetailCheckup.setTglCekup(sqlDate);
            } catch (ParseException e) {
                logger.error("[CheckupDetailAction.saveKeterangan] Error when format string to date for tgl cekup, ", e);
            }
        }

        headerDetailCheckup.setKeteranganCekupUlang(ketCekup);

        if ("selesai".equalsIgnoreCase(idKtg)){
            headerDetailCheckup.setKeteranganSelesai(ket);
            headerDetailCheckup.setStatus(idKtg);
        }
        if ("pindah".equalsIgnoreCase(idKtg)){
            headerDetailCheckup.setKeteranganSelesai("Pindah ke Poli Lain");
        }
        if ("rujuk".equalsIgnoreCase(idKtg)){
            headerDetailCheckup.setKeteranganSelesai("Rujuk Rawat Inap");
        }

        headerDetailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        headerDetailCheckup.setLastUpdateWho(CommonUtil.userLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        try {
            checkupDetailBo.saveEdit(headerDetailCheckup);
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.saveKeterangan] Error when saving data detail checkup, ", e);
        }

        if ("pindah".equalsIgnoreCase(idKtg)){
            pindahPoli(noCheckup, poli, idDokter);
            status = "sukses";
        } else if ("rujuk".equalsIgnoreCase(idKtg)){
            rujukRawatInap(noCheckup, idDetailCheckup, kelas, kamar);
            status = "sukses";
        } else {
            status = "sukses";
        }

        logger.info("[CheckupDetailAction.saveKeterangan] end process >>>");
        return status;
    }

    private void pindahPoli(String noCheckup, String idPoli, String idDokter){
        logger.info("[CheckupDetailAction.pindahPoli] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();

        if (!"".equalsIgnoreCase(noCheckup) &&
                !"".equalsIgnoreCase(idPoli) &&
                !"".equalsIgnoreCase(idDokter)){

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setNoCheckup(noCheckup);
            headerDetailCheckup.setIdPelayanan(idPoli);
            headerDetailCheckup.setStatusPeriksa("1");
            headerDetailCheckup.setCreatedDate(now);
            headerDetailCheckup.setCreatedWho(user);
            headerDetailCheckup.setLastUpdate(now);
            headerDetailCheckup.setLastUpdateWho(user);
            headerDetailCheckup.setIdDokter(idDokter);

            try {
                checkupDetailBo.saveAdd(headerDetailCheckup);
            } catch (GeneralBOException e){
                logger.error("[CheckupDetailAction.pindahPoli] Error when saving add new detail poli, ", e);
            }
        }
        logger.info("[CheckupDetailAction.pindahPoli] end process >>>");
    }

    private void rujukRawatInap(String noCheckup, String idDetailCheckup, String kelas, String kamar){
        logger.info("[CheckupDetailAction.rujukRawatInap] start process >>>");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();

        if (!"".equalsIgnoreCase(noCheckup) &&
                !"".equalsIgnoreCase(idDetailCheckup))
        {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

            List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(idDetailCheckup);

            try {
                detailCheckupList = checkupDetailBo.getByCriteria(detailCheckup);
            } catch (GeneralBOException e){
                logger.error("[CheckupDetailAction.rujukRawatInap] Error when geting data detail poli, ", e);
            }


            if (!detailCheckupList.isEmpty()){
                detailCheckup = detailCheckupList.get(0);
                HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                headerDetailCheckup.setNoCheckup(noCheckup);
                headerDetailCheckup.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                headerDetailCheckup.setIdPelayanan(detailCheckup.getIdPelayanan());
                headerDetailCheckup.setIdRuangan(kamar);
                headerDetailCheckup.setStatusPeriksa("1");
                headerDetailCheckup.setCreatedDate(now);
                headerDetailCheckup.setCreatedWho(user);
                headerDetailCheckup.setLastUpdate(now);
                headerDetailCheckup.setLastUpdateWho(user);
                headerDetailCheckup.setRawatInap(true);

                try {
                    checkupDetailBo.saveAdd(headerDetailCheckup);
                } catch (GeneralBOException e){
                    logger.error("[CheckupDetailAction.rujukRawatInap] Error when saving add new detail poli, ", e);
                }
            }
        }
        logger.info("[CheckupDetailAction.rujukRawatInap] end process >>>");

    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa){
        logger.info("[CheckupDetailAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()){
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[CheckupDetailAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }

    public String getListComboKelasRuangan(){
        logger.info("[CheckupDetailAction.getListComboKelasRuangan] start process >>>");

        List<KelasRuangan> kelasRuanganList = new ArrayList<>();
        KelasRuangan kelasRuangan = new KelasRuangan();

        try {
            kelasRuanganList = kelasRuanganBoProxy.getByCriteria(kelasRuangan);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListComboKelasRuangan] Error when get kelas ruangan ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfKelasRuangan.addAll(kelasRuanganList);
        logger.info("[CheckupDetailAction.getListComboKelasRuangan] end process <<<");
        return SUCCESS;
    }

    public List<Ruangan> listRuangan(String idkelas, boolean flag){

        logger.info("[TindakanRawatAction.listTindakanRawat] start process >>>");
        List<Ruangan> ruanganList = new ArrayList<>();
        Ruangan ruangan = new Ruangan();
        if (flag){
            ruangan.setStatusRuangan("Y");
        }
        ruangan.setIdKelasRuangan(idkelas);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");

            try {
                ruanganList = ruanganBo.getByCriteria(ruangan);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.listTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
            return ruanganList;

    }

    public String getListComboKeteranganKeluar(){
        logger.info("[CheckupDetailAction.getListComboKeteranganKeluar] start process >>>");

        List<KeteranganKeluar> keteranganKeluarList = new ArrayList<>();
        KeteranganKeluar keteranganKeluar = new KeteranganKeluar();

        try {
            keteranganKeluarList = keteranganKeluarBoProxy.getByCriteria(keteranganKeluar);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListComboKeteranganKeluar] Error when get keterangan keluar ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfKeterangan.addAll(keteranganKeluarList);
        logger.info("[CheckupDetailAction.getListComboKeteranganKeluar] end process <<<");
        return SUCCESS;
    }

    public String saveUpdateRuangan(String idRuangan, String idDetailCheckup){
        logger.info("[CheckupDetailAction.saveUpdateRuangan] start process >>>");

        if (idRuangan != null && !"".equalsIgnoreCase(idRuangan) && idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup))
        {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

            try {
                checkupDetailBo.updateRuanganInap(idRuangan, idDetailCheckup);
            } catch (GeneralBOException e){
                logger.error("[CheckupDetailAction.saveUpdateRuangan] Found problem when updating rawat inap, please inform to your admin.", e);
                return "ERROR, "+e.getMessage();
            }
        } else {
            return "ERROR, idRuangan OR idDetailCheckup Is NULL";
        }

        logger.info("[CheckupDetailAction.saveUpdateRuangan] end process >>>");
        return "SUCCESS";
    }

    public List<RawatInap> getListRuangInapByIdDetailCheckup(String idDetailCheckup){
        logger.info("[CheckupDetailAction.getListRuangInapByIdDetailCheckup] start process >>>");

        List<RawatInap> rawatInaps = new ArrayList<>();

        RawatInap rawatInap = new RawatInap();
        rawatInap.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        try {
            rawatInaps = rawatInapBo.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListRuangInapByIdDetailCheckup] Error when get data ruangan.", e);
            addActionError("Error Found problem when get  Error when get data ruangan , please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[CheckupDetailAction.getListRuangInapByIdDetailCheckup] end process >>>");
        return rawatInaps;
    }

    public String addRawatIgd() {

        logger.info("[CheckupDetailAction.add] start process >>>");

        HeaderCheckup checkup = new HeaderCheckup();

        if(CommonConstant.ROLE_ADMIN_IGD.equalsIgnoreCase(CommonUtil.roleAsLogin())){
            checkup.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        }

        setHeaderCheckup(checkup);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");


        logger.info("[CheckupDetailAction.add] end process <<<");

        return "init_daftar";
    }

    public String saveAddRawatIgd(){

        logger.info("[CheckupDetailAction.saveAdd] start process >>>");
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
            checkup.setIdPelayanan(CommonUtil.userPelayananIdLogin());
            checkup.setStatusPeriksa("1");

            String fileName = "";
            if (this.fileUpload != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadContentType)) {
                    if (this.fileUpload.length() <= 5242880 && this.fileUpload.length() > 0) {

                        // file name
                        fileName = checkup.getNoKtp()+"_"+this.fileUploadFileName;

                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_KTP_PASIEN;
//                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_KTP_PASIEN;
                        logger.info("[CheckupDetailAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, fileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            logger.info("[CheckupDetailAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlKtp(fileName);
                        } catch (IOException e) {
                            logger.error("[CheckupDetailAction.uploadImages] error, " + e.getMessage());
                            throw new GeneralBOException("[CheckupDetailAction.uploadImages] Error when copy images to directori "+e.getMessage());
                        }
                    }
                }
            }

            if (this.fileUploadDoc != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadDocContentType)) {
                    if (this.fileUploadDoc.length() <= 5242880 && this.fileUploadDoc.length() > 0) {

                        // file name
                        fileName = "SURAT_RUJUK_"+checkup.getNoKtp()+"_"+this.fileUploadDocFileName;

                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN;
                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, fileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUploadDoc, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlDocRujuk(fileName);
                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                        }
                    }
                }
            }

            if(checkup.getDiagnosa() != null && !"".equalsIgnoreCase(checkup.getDiagnosa())){

                List<Diagnosa> diagnosaList = new ArrayList<>();
                Diagnosa diagnosaResult = new Diagnosa();

                Diagnosa diagnosa = new Diagnosa();
                diagnosa.setIdDiagnosa(checkup.getDiagnosa());

                try {
                    diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
                }catch (GeneralBOException e){
                    logger.error("[DiagnosaRawatAction.saveDiagnosa] Error when search dec diagnosa by id ," + "Found problem when saving add data, please inform to your admin.", e);
                }
                if (!diagnosaList.isEmpty()){
                    diagnosaResult = diagnosaList.get(0);
                    checkup.setNamaDiagnosa(diagnosaResult.getDescOfDiagnosa());
                }
            }

            checkupBoProxy.saveAdd(checkup);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupDetailAction.saveAdd] end process >>>");
        return "search";

    }

    public String printResepPasien(){

        String idResep = getIdResep();
        String id = getId();

        reportParams.put("resepId", idResep);
        reportParams.put("logo", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY+CommonConstant.LOGO_NMU);
        reportParams.put("nik","");
        reportParams.put("nama","");
        reportParams.put("tglLahir","");
        reportParams.put("jenisKelamin","");
        reportParams.put("jenisPasien","");
        reportParams.put("poli","");
        reportParams.put("provinsi","");
        reportParams.put("kabupaten","");
        reportParams.put("kecamatan","");
        reportParams.put("desa","");


        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_resep";
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