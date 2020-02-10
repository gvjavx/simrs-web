package com.neurix.simrs.transaksi.rawatinap.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.SkorRanap;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RawatInapAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(RawatInapAction.class);
    private RawatInap rawatInap;
    private RawatInapBo rawatInapBoProxy;
    private CheckupBo checkupBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private TindakanRawatBo tindakanRawatBoProxy;
    private RuanganBo ruanganBoProxy;
    private TeamDokterBo teamDokterBoProxy;

    private String id;
    private String idResep;

    public void setTeamDokterBoProxy(TeamDokterBo teamDokterBoProxy) {
        this.teamDokterBoProxy = teamDokterBoProxy;
    }

    public void setRuanganBoProxy(RuanganBo ruanganBoProxy) {
        this.ruanganBoProxy = ruanganBoProxy;
    }

    public void setTindakanRawatBoProxy(TindakanRawatBo tindakanRawatBoProxy) {
        this.tindakanRawatBoProxy = tindakanRawatBoProxy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getIdResep() {
        return idResep;
    }

    public void setIdResep(String idResep) {
        this.idResep = idResep;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RawatInapAction.logger = logger;
    }

    public RawatInap getRawatInap() {
        return rawatInap;
    }

    public void setRawatInap(RawatInap rawatInap) {
        this.rawatInap = rawatInap;
    }

    public RawatInapBo getRawatInapBoProxy() {
        return rawatInapBoProxy;
    }

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    @Override
    public String add() {
        logger.info("[RawatInapAction.add] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RawatInap> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();
        String jk = "";
        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (RawatInap rawatInap : listOfResult) {
                    if (id.equalsIgnoreCase(rawatInap.getNoCheckup())) {

                        HeaderCheckup headerCheckup = getHeaderCheckup(rawatInap.getNoCheckup());
                        rawatInap.setIdPasien(headerCheckup.getIdPasien());
                        rawatInap.setNamaPasien(headerCheckup.getNama());
                        rawatInap.setAlamat(headerCheckup.getJalan());
                        rawatInap.setDesa(headerCheckup.getNamaDesa());
                        rawatInap.setKecamatan(headerCheckup.getNamaKecamatan());
                        rawatInap.setKota(headerCheckup.getNamaKota());
                        rawatInap.setProvinsi(headerCheckup.getNamaProvinsi());
                        rawatInap.setIdPelayanan(headerCheckup.getIdPelayanan());
                        rawatInap.setNamaPelayanan(headerCheckup.getNamaPelayanan());
                        if(headerCheckup.getJenisKelamin()!= null){
                            if("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())){
                                jk = "Perempuan";
                            }else{
                                jk = "laki-Laki";
                            }
                        }
                        rawatInap.setJenisKelamin(jk);
                        rawatInap.setTempatLahir(headerCheckup.getTempatLahir());
                        rawatInap.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckup.getTglLahir());
                        rawatInap.setTempatTglLahir(headerCheckup.getTempatLahir()+", "+formatDate);
                        rawatInap.setIdJenisPeriksa(headerCheckup.getIdJenisPeriksaPasien());
                        rawatInap.setNik(headerCheckup.getNoKtp());
                        rawatInap.setUrlKtp(headerCheckup.getUrlKtp());

                        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
                        rawatInap.setJenisPeriksaPasien(jenisPriksaPasien.getKeterangan());

                        List<TindakanRawat> tindakanRawatList = new ArrayList<>();
                        long millis = System.currentTimeMillis();
                        java.util.Date dateToday = new java.util.Date(millis);
                        String today = new SimpleDateFormat("dd-MM-yyyy").format(dateToday);

                        try {
                            tindakanRawatList = tindakanRawatBoProxy.cekTodayTindakanTarifKamar(rawatInap.getIdDetailCheckup(), today);
                        }catch (GeneralBOException e){
                            logger.error("[RawatInapAction.add] Error When get cek tindakan tarif kamar", e);
                        }

                        if(tindakanRawatList.isEmpty()){

                            List<Ruangan> ruanganList = new ArrayList<>();
                            Ruangan ruangan = new Ruangan();
                            ruangan.setIdRuangan(rawatInap.getIdRuangan());

                            try {
                                ruanganList = ruanganBoProxy.getByCriteria(ruangan);
                            }catch (GeneralBOException e){
                                logger.error("[RawatInapAction.add] Error When search tarif kamar", e);
                            }

                            if(ruanganList.size() > 0){
                                ruangan = ruanganList.get(0);
                                if(ruangan != null){

                                    List<DokterTeam> dokterTeamList = new ArrayList<>();
                                    DokterTeam dokterTeam = new DokterTeam();
                                    dokterTeam.setIdDetailCheckup(rawatInap.getIdDetailCheckup());

                                    try {
                                        dokterTeamList = teamDokterBoProxy.getByCriteria(dokterTeam);
                                    }catch (GeneralBOException e){
                                        logger.error("[RawatInapAction.add] Error When search tarif kamar", e);
                                    }

                                    if(dokterTeamList.size() > 0){

                                        dokterTeam = dokterTeamList.get(0);

                                        String userName = CommonUtil.userLogin();
                                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                                        TindakanRawat tindakanRawat = new TindakanRawat();
                                        tindakanRawat.setIdTindakan(ruangan.getIdRuangan());
                                        tindakanRawat.setIdDokter(dokterTeam.getIdDokter());
                                        tindakanRawat.setNamaTindakan("Tarif Kamar "+ruangan.getNamaRuangan() + " No. " +ruangan.getNoRuangan());
                                        tindakanRawat.setIdDetailCheckup(rawatInap.getIdDetailCheckup());
                                        tindakanRawat.setTarif(ruangan.getTarif());
                                        tindakanRawat.setQty(new BigInteger(String.valueOf(1)));
                                        tindakanRawat.setCreatedDate(updateTime);
                                        tindakanRawat.setCreatedWho(userName);
                                        tindakanRawat.setLastUpdate(updateTime);
                                        tindakanRawat.setLastUpdateWho(userName);
                                        tindakanRawat.setAction("C");
                                        tindakanRawat.setFlag("Y");

                                        try {
                                            tindakanRawatBoProxy.saveAdd(tindakanRawat);
                                        }catch (GeneralBOException e){
                                            logger.error("[RawatInapAction.add] Error When insert tarif kamar to tindakan", e);
                                        }
                                    }
                                }
                            }

                        }

                        setRawatInap(rawatInap);

                        break;
                    }
                }

            } else {
                setRawatInap(new RawatInap());
            }
        } else {
            setRawatInap(new RawatInap());
        }

        logger.info("[RawatInapAction.add] end process <<<");
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

        logger.info("[RawatInapAction.search] start process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> listOfRawatInap = new ArrayList();

        try {
            listOfRawatInap = rawatInapBoProxy.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[RawatInapAction.save] Error when searching rawat inap by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[RawatInapAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[RawatInapAction.initForm] start process >>>");

        RawatInap rawatInap = new RawatInap();
        setRawatInap(rawatInap);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[RawatInapAction.initForm] end process <<<");

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

    private HeaderCheckup getHeaderCheckup(String noCheckup){
        logger.info("[RawatInapAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e){
            logger.error("[RawatInapAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()){
            result = headerCheckupList.get(0);
        }

        logger.info("[RawatInapAction.getHeaderCheckup] end process <<<");
        return result;
    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa){
        logger.info("[RawatInapAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e){
            logger.error("[RawatInapAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()){
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[RawatInapAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }

    public String printResepPasien(){

        String idResep = getIdResep();
        String id = getId();
        String jk = "";

        HeaderCheckup headerCheckup = getHeaderCheckup(id);
        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
        reportParams.put("resepId", idResep);
        reportParams.put("logo", CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU);
        reportParams.put("nik",headerCheckup.getNoKtp());
        reportParams.put("nama",headerCheckup.getNama());
        reportParams.put("tglLahir",headerCheckup.getTempatLahir()+", "+headerCheckup.getStTglLahir().toString());
        if("L".equalsIgnoreCase(headerCheckup.getJenisKelamin())){
            jk = "Laki-Laki";
        }else{
            jk = "Perempuan";
        }
        reportParams.put("jenisKelamin",jk);
        reportParams.put("jenisPasien",jenisPriksaPasien.getKeterangan());
        reportParams.put("poli",headerCheckup.getNamaPelayanan());
        reportParams.put("provinsi",headerCheckup.getNamaProvinsi());
        reportParams.put("kabupaten",headerCheckup.getNamaKota());
        reportParams.put("kecamatan",headerCheckup.getNamaKecamatan());
        reportParams.put("desa",headerCheckup.getNamaDesa());


        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_resep";
    }

    public List<ItSimrsSkorRanapEntity> getListSkorByKategori(String noCheckup, String idDetailCheckup, String kategori){
        logger.info("[RawatInapAction.getListSkorByKategori] start process >>>");

        List<ItSimrsSkorRanapEntity> skorRanapEntities = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        SkorRanap skorRanap = new SkorRanap();
        skorRanap.setNoCheckup(noCheckup);
        skorRanap.setIdDetailCheckup(idDetailCheckup);
        skorRanap.setIdKategori(kategori);

        skorRanapEntities = rawatInapBo.getListSkorRanap(skorRanap);

        logger.info("[RawatInapAction.getListSkorByKategori] end process <<<");
        return skorRanapEntities;
    }
}