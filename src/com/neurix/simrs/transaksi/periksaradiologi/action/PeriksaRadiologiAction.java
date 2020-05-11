package com.neurix.simrs.transaksi.periksaradiologi.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.periksaradiologi.bo.PeriksaRadiologiBo;
import com.neurix.simrs.transaksi.periksaradiologi.model.PeriksaRadiologi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PeriksaRadiologiAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(PeriksaRadiologiAction.class);
    private PeriksaRadiologi periksaRadiologi;
    private PeriksaLab periksaLab;
    private PeriksaLabBo periksaLabBoProxy;
    private String id;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private PeriksaRadiologiBo periksaRadiologiBoProxy;
    private BranchBo branchBoProxy;
    private String idPeriksa;

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public String getIdPeriksa() {
        return idPeriksa;
    }

    public void setIdPeriksa(String idPeriksa) {
        this.idPeriksa = idPeriksa;
    }

    private String lab;

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public void setPeriksaRadiologiBoProxy(PeriksaRadiologiBo periksaRadiologiBoProxy) {
        this.periksaRadiologiBoProxy = periksaRadiologiBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
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

    public PeriksaLab getPeriksaLab() {
        return periksaLab;
    }

    public void setPeriksaLab(PeriksaLab periksaLab) {
        this.periksaLab = periksaLab;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PeriksaRadiologiAction.logger = logger;
    }

    public PeriksaRadiologi getPeriksaRadiologi() {
        return periksaRadiologi;
    }

    public void setPeriksaRadiologi(PeriksaRadiologi periksaRadiologi) {
        this.periksaRadiologi = periksaRadiologi;
    }


    @Override
    public String add() {
        logger.info("[PeriksaRadiologiAction.add] start process >>>");

        String id = getId();
        String lab = getLab();
        String userArea = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        String jk = "";
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
                        jk = "laki-Laki";
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

                PeriksaLab periksalb = new PeriksaLab();
                try {
                    periksalb = periksaLabBoProxy.getNamaLab(lab);
                }catch (GeneralBOException e){
                    logger.error("Found Error "+e.getMessage());
                }
                if(periksalb.getIdPeriksaLab() != null){
                    periksaLab.setKategoriLabName(periksalb.getKategoriLabName());
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

        logger.info("[PeriksaRadiologiAction.add] end process <<<");
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
        logger.info("[PeriksaRadiologiAction.search] start process >>>");

        PeriksaLab periksaLab = getPeriksaLab();
        List<PeriksaLab> listPeriksaLabList = new ArrayList();

        // hanya kategori lab radiologi saja
        periksaLab.setIdKategoriLab("KAL00000001");
        periksaLab.setBranchId(CommonUtil.userBranchLogin());

        try {
            listPeriksaLabList = periksaLabBoProxy.getSearchLab(periksaLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaRadiologiAction.search] Error when searching periksa radilogi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listPeriksaLabList);

        logger.info("[PeriksaRadiologiAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[RawatInapAction.initForm] start process >>>");

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setStDateFrom(tglToday);
        periksaLab.setStDateTo(tglToday);
        setPeriksaLab(periksaLab);

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

    public CheckResponse saveRadiologi(String idPeriksaRadiologi, String kesimpulan){

        logger.info("[PeriksaRadiologiAction.saveRadiologi] start process >>>");
        CheckResponse response = new CheckResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            PeriksaRadiologi periksaRadiologi = new PeriksaRadiologi();
            periksaRadiologi.setIdPeriksaRadiologi(idPeriksaRadiologi);
            periksaRadiologi.setKesimpulan(kesimpulan);
            periksaRadiologi.setLastUpdate(updateTime);
            periksaRadiologi.setLastUpdateWho(userLogin);
            periksaRadiologi.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");
            response = periksaRadiologiBo.saveEdit(periksaRadiologi);

        }catch (GeneralBOException e) {
            logger.error("Found Error");
            response.setStatus("error");
            response.setMessage("Found Error "+e.getMessage());
        }

        logger.info("[PeriksaRadiologiAction.saveRadiologi] end process >>>");
        return response;
    }

    public CheckResponse saveDokterRadiologi(String idPeriksaLab, String idDokter){

        logger.info("[PeriksaRadiologiAction.saveRadiologi] start process >>>");
        CheckResponse response = new CheckResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            PeriksaRadiologi periksaRadiologi = new PeriksaRadiologi();
            periksaRadiologi.setIdPeriksaLab(idPeriksaLab);
            periksaRadiologi.setIdDokterRadiologi(idDokter);
            periksaRadiologi.setLastUpdate(updateTime);
            periksaRadiologi.setLastUpdateWho(userLogin);
            periksaRadiologi.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");
            response = periksaRadiologiBo.saveDokterRadiologi(periksaRadiologi);

        }catch (GeneralBOException e) {
            logger.error("Found Error");
            response.setStatus("error");
            response.setMessage("Found Error "+e.getMessage());
        }

        logger.info("[PeriksaRadiologiAction.saveRadiologi] end process >>>");
        return response;
    }

    public List<PeriksaRadiologi> getIdPemeriksaRadiologi(String idPeriksaLab){

        logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
        List<PeriksaRadiologi> periksaRadiologilList = new ArrayList<>();

        PeriksaRadiologi periksaRadiologi = new PeriksaRadiologi();
        periksaRadiologi.setIdPeriksaLab(idPeriksaLab);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");

        if(!"".equalsIgnoreCase(idPeriksaLab)){
            try {
                periksaRadiologilList = periksaRadiologiBo.getListPeriksaRadioLogiByCriteria(periksaRadiologi);
            }catch (GeneralBOException e){
                logger.error("[PeriksaLabAction.listParameterPemeriksaan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
            return periksaRadiologilList;

        }else{
            return null;
        }
    }

    public String printRadiologi() {

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

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }

        return "print_radiologi";
    }

    public PeriksaRadiologiBo getPeriksaRadiologiBoProxy() {
        return periksaRadiologiBoProxy;
    }
}