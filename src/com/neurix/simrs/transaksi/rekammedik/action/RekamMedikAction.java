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
            detailCheckup.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
            String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
            detailCheckup.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
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
            detailCheckup.setVideoRm(checkup.getVideoRm());
            setDetailCheckup(detailCheckup);

        } else {
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdPasien(idPasien);
            setDetailCheckup(detailCheckup);
        }

        if (idPasien != null && !"".equalsIgnoreCase(idPasien)) {
            List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
            try {
                detailCheckupList = rekamMedikBoProxy.getDetailListRekamMedis(idPasien);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search rekam medis " + e.getMessage());
            }
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("listOfRekamMedis", detailCheckupList);
        }

        logger.info("[CheckupDetailAction.add] end process <<<");
        return "init_detail";
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
}
