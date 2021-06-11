package com.neurix.simrs.transaksi.periksaradiologi.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.action.CheckupDetailAction;
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
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
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
        String ket = getKet();
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
                        jk = "Laki-Laki";
                    }
                }
                periksaLab.setJenisKelamin(jk);
                periksaLab.setTempatLahir(checkup.getTempatLahir());
                periksaLab.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());

                if(checkup.getTglLahir() != null){
                    String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                    periksaLab.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
                    periksaLab.setUmur(CommonUtil.calculateAge(checkup.getTglLahir(), true));
                }

                periksaLab.setIdJenisPeriksa(checkup.getIdJenisPeriksaPasien());
                periksaLab.setNik(checkup.getNoKtp());
                periksaLab.setUrlKtp(checkup.getUrlKtp());
                periksaLab.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
                periksaLab.setDiagnosa(checkup.getDiagnosa()+"-"+checkup.getNamaDiagnosa());
                periksaLab.setMetodePembayaran(checkup.getMetodePembayaran());

                List<PeriksaLab> periksaLabList = new ArrayList<>();
                PeriksaLab labData = new PeriksaLab();
                labData.setIdHeaderPemeriksaan(lab);
                try {
                    periksaLabList = periksaLabBoProxy.getByCriteriaHeaderPemeriksaan(labData);
                } catch (HibernateException e) {
                    logger.error("Found Error " + e.getMessage());
                }

                if(periksaLabList.size() > 0){
                    labData = periksaLabList.get(0);
                }
                periksaLab.setIsPeriksaLuar(labData.getIsPeriksaLuar());
                periksaLab.setIdHeaderPemeriksaan(lab);
                periksaLab.setKeterangan(labData.getIsJustLab());
                periksaLab.setIsJustLab(labData.getIsJustLab());
                periksaLab.setNamaDokterPengirim(labData.getNamaDokterPengirim());
                periksaLab.setIsCito(labData.getIsCito());
                String hetero = "";
                String auto = "";
                String nadi = "";
                String suhu = "";
                String tensi = "";
                String rr = "";
                String klinis= "";

                if(checkup.getHeteroanamnesis() != null && !"".equalsIgnoreCase(checkup.getHeteroanamnesis())){
                    hetero = "Heteroanamnesis: "+checkup.getHeteroanamnesis();
                }
                if(checkup.getAutoanamnesis() != null && !"".equalsIgnoreCase(checkup.getAutoanamnesis())){
                    auto = ", Autoanamnesis: "+checkup.getAutoanamnesis();
                }
                if(checkup.getNadi() != null && !"".equalsIgnoreCase(checkup.getNadi())){
                    nadi = ", Nadi: "+checkup.getNadi();
                }
                if(checkup.getSuhu() != null && !"".equalsIgnoreCase(checkup.getSuhu())){
                    suhu = ", Suhu: "+checkup.getSuhu();
                }
                if(checkup.getTensi() != null && !"".equalsIgnoreCase(checkup.getTensi())){
                    tensi = ", Tensi: "+checkup.getTensi();
                }
                if(checkup.getPernafasan() != null && !"".equalsIgnoreCase(checkup.getPernafasan())){
                    rr = ", RR: "+checkup.getPernafasan();
                }
                if(checkup.getCatatanKlinis() != null && !"".equalsIgnoreCase(checkup.getCatatanKlinis())){
                    klinis = ", Catatan Klinis: "+checkup.getCatatanKlinis();
                }

                periksaLab.setCatatanKlinis(hetero+auto+nadi+suhu+tensi+rr+klinis);
                setPeriksaLab(periksaLab);

                PeriksaLab periksa = new PeriksaLab();
                periksa.setIdHeaderPemeriksaan(lab);
                periksa.setLastUpdate(updateTime);
                periksa.setLastUpdateWho(userArea);
                periksa.setTanggalMasukLab(updateTime);

                try {
                    periksaLabBoProxy.saveEditStatusPeriksa(periksa);
                } catch (GeneralBOException e) {
                    logger.error("Found Error when " + e.getMessage());
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
        periksaLab.setIdKategoriLab("radiologi");
        periksaLab.setBranchId(CommonUtil.userBranchLogin());

        try {
            listPeriksaLabList = periksaLabBoProxy.getSearchLab(periksaLab);
        } catch (GeneralBOException e) {
            logger.error("[PeriksaRadiologiAction.search] Error when searching periksa radilogi by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listPeriksaLabList);
        setPeriksaLab(periksaLab);
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

    public CheckResponse saveRadiologi(String idPeriksaRadiologi, String kesimpulan) {

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

        } catch (GeneralBOException e) {
            logger.error("Found Error");
            response.setStatus("error");
            response.setMessage("Found Error " + e.getMessage());
        }

        logger.info("[PeriksaRadiologiAction.saveRadiologi] end process >>>");
        return response;
    }

    public CheckResponse saveDokterRadiologi(String idPeriksaLab, String idDokter, String urlImg, String keterangan, String data) {

        logger.info("[PeriksaRadiologiAction.saveRadiologi] start process >>>");
        CheckResponse response = new CheckResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");

            PeriksaRadiologi periksaRadiologi = new PeriksaRadiologi();
            periksaRadiologi.setIdPeriksaLab(idPeriksaLab);
            periksaRadiologi.setIdDokterRadiologi(idDokter);
            periksaRadiologi.setLastUpdate(updateTime);
            periksaRadiologi.setLastUpdateWho(userLogin);
            periksaRadiologi.setAction("U");
            periksaRadiologi.setIdPemeriksa(userLogin);

            if(urlImg != null && !"".equalsIgnoreCase(urlImg)){
                try {
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes = decoder.decodeBuffer(urlImg);
                    String patten = updateTime.toString().replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                    String fileName = idPeriksaLab+"-"+patten+".png";
                    String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                    if (image == null) {
                        logger.error("Buffered Image is null");
                        response.setStatus("error");
                        response.setMessage("Buffered Image is null");
                    } else {
                        File f = new File(uploadFile);
                        ImageIO.write(image, "png", f);
                        periksaRadiologi.setUrlImg(fileName);
                    }
                }catch (IOException e){
                    response.setStatus("error");
                    response.setMessage("IO Error"+e.getMessage());
                    return response;
                }
            }

            response = periksaRadiologiBo.saveDokterRadiologi(periksaRadiologi);
            if ("just_lab".equalsIgnoreCase(keterangan)){
                if("success".equalsIgnoreCase(response.getStatus())){
                    CheckupDetailAction detailAction = new CheckupDetailAction();
                    CrudResponse res = new CrudResponse();
                    res = detailAction.closeTraksaksiPasien(data);
                    if("success".equalsIgnoreCase(res.getStatus())){
                        response.setStatus("success");
                        response.setMessage("Berhasil");
                    }else{
                        response.setStatus("error");
                        response.setMessage("Error"+res.getMsg());
                    }
                }
            }
        } catch (GeneralBOException e) {
            logger.error("Found Error");
            response.setStatus("error");
            response.setMessage("Found Error " + e.getMessage());
        }

        logger.info("[PeriksaRadiologiAction.saveRadiologi] end process >>>");
        return response;
    }

    public List<PeriksaRadiologi> getIdPemeriksaRadiologi(String idPeriksaLab) {

        logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
        List<PeriksaRadiologi> periksaRadiologilList = new ArrayList<>();

        PeriksaRadiologi periksaRadiologi = new PeriksaRadiologi();
        periksaRadiologi.setIdPeriksaLab(idPeriksaLab);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");

        if (!"".equalsIgnoreCase(idPeriksaLab)) {
            try {
                periksaRadiologilList = periksaRadiologiBo.getListPeriksaRadioLogiByCriteria(periksaRadiologi);
            } catch (GeneralBOException e) {
                logger.error("[PeriksaLabAction.listParameterPemeriksaan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
            return periksaRadiologilList;

        } else {
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

            List<PeriksaLab> periksaLabList = new ArrayList<>();
            PeriksaLab labData = new PeriksaLab();
            labData.setIdHeaderPemeriksaan(lab);
            try {
                periksaLabList = periksaLabBoProxy.getByCriteriaHeaderPemeriksaan(labData);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }

            if(periksaLabList.size() > 0) {
                labData = periksaLabList.get(0);
            }

            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("idPeriksaLab", lab);
            reportParams.put("id", lab);
            reportParams.put("logo", logo);
            reportParams.put("nik", checkup.getNoKtp());
            reportParams.put("nama", checkup.getNama());
            if(checkup.getTglLahir() != null){
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                reportParams.put("tglLahir", checkup.getTempatLahir() + ", " + formatDate);
                reportParams.put("tgllahir", formatDate);
                reportParams.put("divisi", "Radiologi");
            }
            if(labData.getCreatedDate() != null){
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(labData.getCreatedDate());
                reportParams.put("tglFoto", formatDate);
            }
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
            reportParams.put("idDokterPengirim", labData.getIdDokterPengirim());
            reportParams.put("dokterPengirim", labData.getNamaDokterPengirim());
            reportParams.put("idPetugas", labData.getIdPetugas());
            reportParams.put("namaPetugas", labData.getNamaPetugas());
            reportParams.put("idValidator", labData.getIdValidator());
            reportParams.put("namaValidator", labData.getNamaValidator());
            reportParams.put("ttdPetugas", labData.getTtdPetugas());
            reportParams.put("ttdValidator", labData.getTtdValidator());
            reportParams.put("umur", calculateAge(checkup.getTglLahir(), true));

            String namaLab = "";
            List<PeriksaLab> periksaLabs = new ArrayList<>();
            PeriksaLab periksa = new PeriksaLab();
            periksa.setIdHeaderPemeriksaan(lab);
            try {
                periksaLabs = periksaLabBoProxy.getByCriteria(periksa);
            } catch (HibernateException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
            }

            if(periksaLabs.size() > 0){
                for (PeriksaLab pb: periksaLabs){
                    if("".equalsIgnoreCase(namaLab)){
                        namaLab = pb.getNamaPemeriksaan();
                    }else{
                        namaLab = namaLab+", "+pb.getNamaPemeriksaan();
                    }
                }
            }
            reportParams.put("pemeriksaan", namaLab);

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }

        if("label".equalsIgnoreCase(getKet())){
            return "print_label";
        }else{
            return "print_radiologi";
        }
    }

    private String calculateAge(Date birthDate, boolean justTahun) {
        String umur = "";
        if (birthDate != null && !"".equalsIgnoreCase(birthDate.toString())) {
            int years = 0;
            int months = 0;
            int days = 0;

            //create calendar object for birth day
            Calendar birthDay = Calendar.getInstance();
            birthDay.setTimeInMillis(birthDate.getTime());

            //create calendar object for current day
            long currentTime = System.currentTimeMillis();
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(currentTime);

            //Get difference between years
            years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
            int currMonth = now.get(Calendar.MONTH) + 1;
            int birthMonth = birthDay.get(Calendar.MONTH) + 1;

            //Get difference between months
            months = currMonth - birthMonth;

            //if month difference is in negative then reduce years by one
            //and calculate the number of months.
            if (months < 0) {
                years--;
                months = 12 - birthMonth + currMonth;
                if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                    months--;
            } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                years--;
                months = 11;
            }

            //Calculate the days
            if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
                days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
            else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                int today = now.get(Calendar.DAY_OF_MONTH);
                now.add(Calendar.MONTH, -1);
                days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
            } else {
                days = 0;
                if (months == 12) {
                    years++;
                    months = 0;
                }
            }

            if(justTahun){
                umur = years+" Tahun";
            }else{
                if (days > 0) {
                    umur = years + " Tahun, " + months + " Bulan, " + days + " Hari";
                } else if (months > 0) {
                    umur = years + " Tahun, " + months + " Bulan";
                } else {
                    umur = years + " Tahun";
                }
            }

        }

        return umur;
    }

    public PeriksaRadiologiBo getPeriksaRadiologiBoProxy() {
        return periksaRadiologiBoProxy;
    }
}