package com.neurix.simrs.transaksi.daftarulang.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.daftarulang.bo.DaftarUlangBo;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DaftarUlangAction extends BaseTransactionAction {

    public static transient Logger logger = Logger.getLogger(DaftarUlangAction.class);
    private HeaderDetailCheckup headerDetailCheckup;
    private CheckupDetailBo checkupDetailBoProxy;
    private DaftarUlangBo daftarUlangBoProxy;
    private CheckupBo checkupBoProxy;
    private String id;

    @Override
    public String search() {
        HeaderDetailCheckup detailCheckup = getHeaderDetailCheckup();
        detailCheckup.setBranchId(CommonUtil.userBranchLogin());
        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

        try {
            detailCheckupList = daftarUlangBoProxy.getListDaftarUlangPasien(detailCheckup);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search status periksa " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", detailCheckupList);
        return "search";
    }

    @Override
    public String initForm() {
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setStDateFrom(tglToday);
        detailCheckup.setStDateTo(tglToday);
        setHeaderDetailCheckup(detailCheckup);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "search";
    }

    public String add() {

        String id = getId();
        HeaderCheckup checkup = new HeaderCheckup();
        String jk = "";

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found error when detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setNoCheckup(checkup.getNoCheckup());
            detailCheckup.setIdDetailCheckup(checkup.getIdDetailCheckup());
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
            detailCheckup.setNoBpjs(checkup.getNoBpjs());
            setHeaderDetailCheckup(detailCheckup);

        } else {
            setHeaderDetailCheckup(new HeaderDetailCheckup());
        }
        return "init_add";
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public void setDaftarUlangBoProxy(DaftarUlangBo daftarUlangBoProxy) {
        this.daftarUlangBoProxy = daftarUlangBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public HeaderDetailCheckup getHeaderDetailCheckup() {
        return headerDetailCheckup;
    }

    public void setHeaderDetailCheckup(HeaderDetailCheckup headerDetailCheckup) {
        this.headerDetailCheckup = headerDetailCheckup;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
