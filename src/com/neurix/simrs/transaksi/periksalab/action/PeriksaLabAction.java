package com.neurix.simrs.transaksi.periksalab.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLabDetail;
import com.neurix.simrs.transaksi.profilrekammedisrj.bo.RekamMedisRawatJalanBo;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.RekamMedisRawatJalan;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsRiwayatPembelianObat;
import com.neurix.simrs.transaksi.transaksiobat.model.RiwayatTransaksiObat;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PeriksaLabAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PeriksaLabAction.class);
    private PeriksaLab periksaLab;
    private PeriksaLabBo periksaLabBoProxy;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private BranchBo branchBoProxy;
    private String id;
    private String lab;
    private String ket;

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
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

    public void setPeriksaLabBoProxy(PeriksaLabBo periksaLabBoProxy) {
        this.periksaLabBoProxy = periksaLabBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PeriksaLabAction.logger = logger;
    }

    public PeriksaLab getPeriksaLab() {
        return periksaLab;
    }

    public void setPeriksaLab(PeriksaLab periksaLab) {
        this.periksaLab = periksaLab;
    }

    @Override
    public String add() {
        logger.info("[PeriksaLabAction.add] start process >>>");

        String id = getId();
        String lab = getLab();
        String keterangan = getKet();
        String jk = "";
        String userArea = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        if (id != null && !"".equalsIgnoreCase(id)) {

            HeaderCheckup checkup = new HeaderCheckup();

            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found error when searc data detail " + e.getMessage());
            }

            if (checkup != null) {

                PeriksaLab periksaLab = new PeriksaLab();
                periksaLab.setNoCheckup(checkup.getNoCheckup());
                periksaLab.setIdDetailCheckup(checkup.getIdDetailCheckup());
                periksaLab.setIdPasien(checkup.getIdPasien());
                periksaLab.setNamaPasien(checkup.getNama());
                periksaLab.setAlamat(checkup.getJalan());
                periksaLab.setDesa(checkup.getNamaDesa());
                periksaLab.setKecamatan(checkup.getNamaKecamatan());
                periksaLab.setKota(checkup.getNamaKota());
                periksaLab.setProvinsi(checkup.getNamaProvinsi());
                periksaLab.setIdPelayanan(checkup.getIdPelayanan());
                periksaLab.setNamaPelayanan(checkup.getNamaPelayanan());
                if (checkup.getJenisKelamin() != null) {
                    if ("P".equalsIgnoreCase(checkup.getJenisKelamin())) {
                        jk = "Perempuan";
                    } else {
                        jk = "Laki-Laki";
                    }
                }
                periksaLab.setJenisKelamin(jk);
                periksaLab.setTempatLahir(checkup.getTempatLahir());
                periksaLab.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                periksaLab.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
                periksaLab.setIdJenisPeriksa(checkup.getIdJenisPeriksaPasien());
                periksaLab.setNik(checkup.getNoKtp());
                periksaLab.setUrlKtp(checkup.getUrlKtp());
                periksaLab.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
                periksaLab.setIdPeriksaLab(lab);
                periksaLab.setKeterangan(keterangan);
                periksaLab.setMetodePembayaran(checkup.getMetodePembayaran());

                PeriksaLab periksalb = new PeriksaLab();
                try {
                    periksalb = periksaLabBoProxy.getNamaLab(lab);
                }catch (HibernateException e){
                    logger.error("Found Error "+e.getMessage());
                }
                if(periksalb.getIdPeriksaLab() != null){
                    periksaLab.setKategoriLabName(periksalb.getKategoriLabName());
                    periksaLab.setIdLab(periksalb.getIdLab());
                }

                setPeriksaLab(periksaLab);

                long millis = System.currentTimeMillis();
                java.util.Date date = new java.util.Date(millis);
                String tglToday = new SimpleDateFormat("yyyy-MM-dd").format(date);
                PeriksaLab periksa = new PeriksaLab();
                periksa.setIdPeriksaLab(lab);
                periksa.setLastUpdate(updateTime);
                periksa.setLastUpdateWho(userArea);
                periksa.setTanggalMasukLab(Date.valueOf(tglToday));

                try {
                    periksaLabBoProxy.saveEditStatusPeriksa(periksa);
                }catch (GeneralBOException e){
                    logger.error("Found Error when "+e.getMessage());
                }

            } else {
                setPeriksaLab(new PeriksaLab());
            }
        }

        logger.info("[PeriksaLabAction.add] end process <<<");
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
        logger.info("[PeriksaLabAction.search] start process >>>");

        PeriksaLab periksaLab = getPeriksaLab();
        List<PeriksaLab> listPeriksaLabList = new ArrayList();

        // hanya kategori lab laboratorium saja
        periksaLab.setIdKategoriLab("KAL00000002");
        periksaLab.setBranchId(CommonUtil.userBranchLogin());

        try {
            listPeriksaLabList = periksaLabBoProxy.getSearchLab(periksaLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaLabAction.save] Error when searching periksa lab by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listPeriksaLabList);

        logger.info("[PeriksaLabAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[PeriksaLabAction.initForm] start process >>>");

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setStDateFrom(tglToday);
        periksaLab.setStDateTo(tglToday);
        setPeriksaLab(periksaLab);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PeriksaLabAction.initForm] end process <<<");

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

    public String saveOrderLab(String idDetailCheckup, String idLab, List<String> idParameter) {
        logger.info("[PeriksaLabAction.saveOrderLab] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            PeriksaLab periksaLab = new PeriksaLab();


            periksaLab.setIdDetailCheckup(idDetailCheckup);
            periksaLab.setIdLab(idLab);
            periksaLab.setCreatedWho(userLogin);
            periksaLab.setLastUpdate(updateTime);
            periksaLab.setCreatedDate(updateTime);
            periksaLab.setLastUpdateWho(userLogin);
            periksaLab.setAction("C");
            periksaLab.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            periksaLabBo.saveAddWithParameter(periksaLab, idParameter);
            insertProfilRJ(idDetailCheckup);

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaLabAction.saveOrderLab] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PeriksaLabAction.saveOrderLab] End process >>>");
        return SUCCESS;
    }

    public CrudResponse insertProfilRJ(String idDetailCheckup){
        CrudResponse response = new CrudResponse();
        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            RekamMedisRawatJalanBo rekamMedisRawatJalanBo = (RekamMedisRawatJalanBo) ctx.getBean("rekamMedisRawatJalanBoProxy");
            List<RekamMedisRawatJalan> rekamMedisRawatJalanList = new ArrayList<>();
            try {
                RekamMedisRawatJalan rekamMedisRawatJalan = new RekamMedisRawatJalan();
                rekamMedisRawatJalan.setIdDetailCheckup(idDetailCheckup);
                rekamMedisRawatJalanList = rekamMedisRawatJalanBo.getByCriteria(rekamMedisRawatJalan);
                if (rekamMedisRawatJalanList.size() > 0) {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setPemeriksaanFisik(checkupBo.getPenunjangMedis(idDetailCheckup));
                    rawatJalan.setDiagnosa(checkupBo.getDiagnosaPasien(idDetailCheckup));
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setAction("U");
                    response = rekamMedisRawatJalanBo.saveEdit(rawatJalan);
                } else {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setPemeriksaanFisik(checkupBo.getPenunjangMedis(idDetailCheckup));
                    rawatJalan.setDiagnosa(checkupBo.getDiagnosaPasien(idDetailCheckup));
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setIdDetailCheckup(idDetailCheckup);
                    rawatJalan.setAction("C");
                    rawatJalan.setFlag("Y");
                    response = rekamMedisRawatJalanBo.saveAdd(rawatJalan);
                }
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("error");
            }
        }
        return response;
    }

    public List<PeriksaLab> listOrderLab(String idDetailCheckup, String lab) {

        logger.info("[PeriksaLabAction.listOrderLab] start process >>>");
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setIdDetailCheckup(idDetailCheckup);
        periksaLab.setIdPeriksaLab(lab);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                periksaLabList = periksaLabBo.getByCriteria(periksaLab);
            } catch (GeneralBOException e) {
                logger.error("[PeriksaLabAction.listOrderLab] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PeriksaLabAction.listOrderLab] start process >>>");
            return periksaLabList;

        } else {
            return null;
        }
    }

    public List<Dokter> getListDokterTeamByNoDetail(String idDetailCheckup){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        return checkupDetailBo.getListDokterByDetailCheckup(idDetailCheckup);
    }

    public List<Dokter> getListDokterLabRadiologi(String tipe){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        return periksaLabBo.getListDokterLabRadiologi(tipe);
    }

    public List<PeriksaLabDetail> listParameterPemeriksaan(String idPeriksaLab, String kategoriName) {

        logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
        List<PeriksaLabDetail> periksaLabDetailList = new ArrayList<>();

        PeriksaLabDetail periksaLabDetail = new PeriksaLabDetail();
        periksaLabDetail.setIdPeriksaLab(idPeriksaLab);
        periksaLabDetail.setKategoriName(kategoriName);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        if (!"".equalsIgnoreCase(idPeriksaLab) && !"".equalsIgnoreCase(kategoriName)) {
            try {
                periksaLabDetailList = periksaLabBo.getListParameterLab(periksaLabDetail);
            } catch (GeneralBOException e) {
                logger.error("[PeriksaLabAction.listParameterPemeriksaan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
            return periksaLabDetailList;

        } else {
            return null;
        }
    }

    public String saveEditParameterLab(String idPeriksaLabDetail, String hasil, String keterangan) {
        logger.info("[PeriksaLabAction.saveEditParameterLab] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            PeriksaLabDetail periksaLabDetail = new PeriksaLabDetail();

            periksaLabDetail.setIdPeriksaLabDetail(idPeriksaLabDetail);
            periksaLabDetail.setHasil(hasil);
            periksaLabDetail.setKeteranganPeriksa(keterangan);
            periksaLabDetail.setCreatedWho(userLogin);
            periksaLabDetail.setLastUpdate(updateTime);
            periksaLabDetail.setLastUpdateWho(userLogin);
            periksaLabDetail.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            periksaLabBo.saveUpdateHasilLab(periksaLabDetail);

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaLabAction.saveEditParameterLab] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PeriksaLabAction.saveEditParameterLab] End process >>>");
        return SUCCESS;
    }

    private HeaderCheckup getHeaderCheckup(String noCheckup) {
        logger.info("[PeriksaLabAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[PeriksaLabAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()) {
            result = headerCheckupList.get(0);
        }

        logger.info("[PeriksaLabAction.getHeaderCheckup] end process <<<");
        return result;
    }

    private HeaderDetailCheckup getDetailCheckup(String idDetailCheckup) {
        logger.info("[PeriksaLabAction.getDetailCheckup] start process >>>");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);

        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
        try {
            detailCheckupList = checkupDetailBoProxy.getByCriteria(detailCheckup);
        } catch (GeneralBOException e) {
            logger.error("[PeriksaLabAction.getDetailCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderDetailCheckup result = new HeaderDetailCheckup();
        if (!detailCheckupList.isEmpty()) {
            result = detailCheckupList.get(0);
        }

        logger.info("[PeriksaLabAction.getDetailCheckup] end process <<<");
        return result;
    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa) {
        logger.info("[PeriksaLabAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e) {
            logger.error("[PeriksaLabAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()) {
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[PeriksaLabAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }

    public String editOrderLab(String idPeriksaLab, String idLab, List<String> idParameter) {
        logger.info("[PeriksaLabAction.editOrderLab] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            PeriksaLab periksaLab = new PeriksaLab();

            periksaLab.setIdPeriksaLab(idPeriksaLab);
            periksaLab.setIdLab(idLab);
            periksaLab.setCreatedDate(updateTime);
            periksaLab.setCreatedWho(userLogin);
            periksaLab.setLastUpdate(updateTime);
            periksaLab.setLastUpdateWho(userLogin);
            periksaLab.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            periksaLabBo.saveEdit(periksaLab, idParameter);

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaLabAction.editOrderLab] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PeriksaLabAction.editOrderLab] End process >>>");
        return SUCCESS;
    }

    public CheckResponse saveEditDokterLab(String idPeriksaLab, String idDokter) {
        logger.info("[PeriksaLabAction.saveEditDokterLab] start process >>>");
        CheckResponse response = new CheckResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            PeriksaLab periksaLab = new PeriksaLab();

            periksaLab.setIdPeriksaLab(idPeriksaLab);
            periksaLab.setIdDokter(idDokter);
            periksaLab.setLastUpdate(updateTime);
            periksaLab.setLastUpdateWho(userLogin);
            periksaLab.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            periksaLabBo.saveDokterLab(periksaLab);
            response.setStatus("success");
            response.setMessage("Berhasil");

        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMessage("Error"+e.getMessage());
        }

        logger.info("[PeriksaLabAction.saveOrderLab] End process >>>");
        return response;
    }

    public String printPeriksaLab() {

        String id = getId();
        String jk = "";

        HeaderDetailCheckup headerDetailCheckup = getDetailCheckup(id);

        String branch = CommonUtil.userBranchLogin();
        String logo = "";

        String branchName = CommonUtil.userBranchNameLogin();
        Branch branches = new Branch();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        HeaderCheckup headerCheckup = getHeaderCheckup(headerDetailCheckup.getNoCheckup());
        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
        if ("L".equalsIgnoreCase(headerCheckup.getJenisKelamin())) {
            jk = "Laki-Laki";
        } else {
            jk = "Perempuan";
        }
        reportParams.put("logo", logo);
        reportParams.put("nama", headerCheckup.getNama());
        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckup.getTglLahir());
        reportParams.put("tglLahir", headerCheckup.getTempatLahir() + ", " + formatDate);
        reportParams.put("namaDokter", "");
        reportParams.put("diagnosa", jk);
        String alamat = "Desa " + headerCheckup.getNamaDesa() + ", Kec. " + headerCheckup.getNamaKecamatan() + "\n" + "Kab. " + headerCheckup.getNamaKota() + ", " + headerCheckup.getNamaProvinsi();
        reportParams.put("alamat", alamat);
        reportParams.put("noLab", "");
        reportParams.put("noRegistrasi", "");
        reportParams.put("noRm", "");
        reportParams.put("tglPeriksa", "");
        reportParams.put("tglSelesai", "");
        reportParams.put("ruangKelas", "");
        reportParams.put("idDetailCheckup", id);
        reportParams.put("noCheckup", headerCheckup.getNoCheckup());
        reportParams.put("namaRs", "Rumah Sakit Toeloengredjo");
        reportParams.put("jalanRs", "Jln. Kusuma Bangsa No. 01 Kanigoro Blitar. Telepon (0549) 7878 89890 900");
        reportParams.put("fox", "Fox : (0354) 383883");


        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_periksa_lab";
    }

    public String printLab() {

        HeaderCheckup checkup = new HeaderCheckup();
        String lab = getLab();
        String id = getId();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String logo = "";
        Branch branches = new Branch();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search data detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            PeriksaLab periksalb = new PeriksaLab();
            try {
                periksalb = periksaLabBoProxy.getNamaLab(lab);
            }catch (HibernateException e){
                logger.error("Found Error "+e.getMessage());
            }

            if(periksalb.getIdPeriksaLab() != null){
                reportParams.put("title", "Hasil Periksa Lab "+periksalb.getKategoriLabName());
            }

            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("idPeriksaLab", lab);
            reportParams.put("logo", logo);
            reportParams.put("nik", checkup.getNoKtp());
            reportParams.put("nama", checkup.getNama());
            String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
            reportParams.put("tglLahir", checkup.getTempatLahir() + ", " + formatDate);
            if ("L".equalsIgnoreCase(checkup.getJenisKelamin())) {
                jk = "Laki-Laki";
            } else {
                jk = "Perempuan";
            }
            reportParams.put("jenisKelamin", jk);
            reportParams.put("jenisPasien", checkup.getStatusPeriksaName());
            reportParams.put("poli", checkup.getNamaPelayanan());
            reportParams.put("provinsi", checkup.getNamaProvinsi());
            reportParams.put("kabupaten", checkup.getNamaKota());
            reportParams.put("kecamatan", checkup.getNamaKecamatan());
            reportParams.put("desa", checkup.getNamaDesa());
            reportParams.put("diagnosa", checkup.getNamaDiagnosa());
            reportParams.put("petugas", periksalb.getNamaPetugas());
            reportParams.put("dokter", periksalb.getNamaDokter());
            reportParams.put("ttdDokter", periksalb.getTtdDokter());
            reportParams.put("ttdPetugas", periksalb.getTtdPetugas());

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }

        return "print_lab";
    }

    public CrudResponse saveUpdatePemeriksaan(String idLabPeriksa, List<String> idParameter, String ket) {
        CrudResponse response = new CrudResponse();
        logger.info("[PeriksaLabAction.saveOrderLab] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            PeriksaLab periksaLab = new PeriksaLab();
            periksaLab.setIdPeriksaLab(idLabPeriksa);
            periksaLab.setCreatedWho(userLogin);
            periksaLab.setLastUpdate(updateTime);
            periksaLab.setCreatedDate(updateTime);
            periksaLab.setLastUpdateWho(userLogin);
            periksaLab.setAction("C");
            periksaLab.setFlag("Y");
            periksaLab.setKeterangan(ket);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            response = periksaLabBo.saveUpdateParameter(periksaLab, idParameter);

        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Found error");
        }

        logger.info("[PeriksaLabAction.saveOrderLab] End process >>>");
        return response;
    }
}