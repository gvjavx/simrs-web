package com.neurix.simrs.transaksi.rekammedik.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RekamMedikAction extends BaseTransactionAction {

    protected static transient Logger logger = Logger.getLogger(RekamMedikAction.class);
    private HeaderDetailCheckup detailCheckup;
    private RekamMedikBo rekamMedikBoProxy;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private PasienBo pasienBoProxy;
    private BranchBo branchBoProxy;
    private String id;
    private String idPasien;
    private String imgKtp;
    private String tipe;
    private String idx;
    private String url;

    @Override
    public String search() {
        logger.info("[RekamMedikAction.search] start process >>>");

        HeaderDetailCheckup detailCheckup = getDetailCheckup();
        detailCheckup.setBranchId(CommonUtil.userBranchLogin());
        List<HeaderDetailCheckup> detailCheckupList = new ArrayList();

        try {
            detailCheckupList = rekamMedikBoProxy.getListPasien(detailCheckup);
        } catch (GeneralBOException e) {
            logger.error("[RekamMedikAction.search] found error when rekam medik, please inform to your admin.");
            throw new GeneralBOException("searcj found error when rekam medik", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", detailCheckupList);

        logger.info("[RekamMedikAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[RekamMedikAction.initForm] start process >>>");
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        setDetailCheckup(detailCheckup);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        logger.info("[RekamMedikAction.initForm] end process >>>");
        return "search";
    }

    public String detail() {
        logger.info("[CheckupDetailAction.add] start process >>>");

        String id = getId();
        String idPasien = getIdPasien();

        HeaderCheckup checkup = new HeaderCheckup();
        String jk = "";

        List<Pasien> pasienList = new ArrayList<>();
        Pasien pasien = new Pasien();
        pasien.setIdPasien(idPasien);

        try {
            pasienList = pasienBoProxy.getByCriteria(pasien);
        }catch (GeneralBOException e){
            logger.error("Found error when detail pasien " + e.getMessage());
        }

        if(pasienList.size() > 0){
            pasien = pasienList.get(0);
            setImgKtp(pasien.getUrlKtp());
        }

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found error when detail pasien " + e.getMessage());
        }

        if (checkup.getIdPasien() != null) {

            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setNoCheckup(checkup.getNoCheckup());
            detailCheckup.setIdDetailCheckup(checkup.getIdDetailCheckup());
            detailCheckup.setLastUpdateWho(CommonUtil.userLogin());
            detailCheckup.setIdPasien(checkup.getIdPasien());
            detailCheckup.setNamaPasien(checkup.getNama());
            detailCheckup.setAlamat(checkup.getJalan());
            detailCheckup.setDesa(checkup.getNamaDesa());
            detailCheckup.setKecamatan(checkup.getNamaKecamatan());
            detailCheckup.setKota(checkup.getNamaKota());
            detailCheckup.setProvinsi(checkup.getNamaProvinsi());
            detailCheckup.setIdPelayanan(checkup.getIdPelayanan());
            detailCheckup.setNamaPelayanan(checkup.getNamaPelayanan());
            if (checkup.getJenisKelamin() != null) {
                if ("P".equalsIgnoreCase(checkup.getJenisKelamin())) {
                    jk = "Perempuan";
                } else {
                    jk = "Laki-Laki";
                }
            }
            detailCheckup.setJenisKelamin(jk);
            detailCheckup.setTempatLahir(checkup.getTempatLahir());
            if(checkup.getTglLahir() != null){
                detailCheckup.setUmur(calculateAge(checkup.getTglLahir(), true));
                detailCheckup.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                detailCheckup.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
            }
            detailCheckup.setNik(checkup.getNoKtp());
            detailCheckup.setIdJenisPeriksaPasien(checkup.getIdJenisPeriksaPasien());
            detailCheckup.setUrlKtp(checkup.getUrlKtp());
            detailCheckup.setTinggi(checkup.getTinggi());
            detailCheckup.setBerat(checkup.getBerat());
            detailCheckup.setNoSep(checkup.getNoSep());
            detailCheckup.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
            detailCheckup.setMetodePembayaran(checkup.getMetodePembayaran());
            detailCheckup.setNoRujukan(checkup.getNoRujukan());
            detailCheckup.setTglRujukan(checkup.getTglRujukan());
            detailCheckup.setSuratRujukan(checkup.getUrlDocRujuk());
            detailCheckup.setIdAsuransi(checkup.getIdAsuransi());
            detailCheckup.setNamaAsuransi(checkup.getNamaAsuransi());
            detailCheckup.setCoverBiaya(checkup.getCoverBiaya());
            detailCheckup.setVideoRm(CommonConstant.EXTERNAL_IMG_URI+checkup.getVideoRm());
            if(checkup.getCreatedDate() != null){
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getCreatedDate());
                detailCheckup.setStTanggalMasuk(formatDate);
            }
            detailCheckup.setKategoriPelayanan(checkup.getKategoriPelayanan());
            detailCheckup.setDiagnosa(checkup.getDiagnosa());
            detailCheckup.setNamaDiagnosa(checkup.getNamaDiagnosa());
            String label = checkup.getNamaPelayanan().replace("Poli Spesialis", "");
            detailCheckup.setAsesmenLabel("Asesmen " + label);
            detailCheckup.setKategoriPelayanan(checkup.getKategoriPelayanan());
            detailCheckup.setTipePelayanan(checkup.getTipePelayanan());
            detailCheckup.setIdx(getIdx());
            detailCheckup.setUrl(getUrl());
            setDetailCheckup(detailCheckup);
        }
        logger.info("[CheckupDetailAction.add] end process <<<");
        return "init_detail";
    }

    private String calculateAge(java.sql.Date birthDate, boolean justTahun) {
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

            if (justTahun) {
                umur = String.valueOf(years);
            } else {
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

    public static Logger getLogger() {
        return logger;
    }

    public HeaderDetailCheckup getDetailCheckup() {
        return detailCheckup;
    }

    public void setDetailCheckup(HeaderDetailCheckup detailCheckup) {
        this.detailCheckup = detailCheckup;
    }

    public void setRekamMedikBoProxy(RekamMedikBo rekamMedikBoProxy) {
        this.rekamMedikBoProxy = rekamMedikBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getImgKtp() {
        return imgKtp;
    }

    public void setImgKtp(String imgKtp) {
        this.imgKtp = imgKtp;
    }

    public PasienBo getPasienBoProxy() {
        return pasienBoProxy;
    }

    public void setPasienBoProxy(PasienBo pasienBoProxy) {
        this.pasienBoProxy = pasienBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
