package com.neurix.simrs.transaksi.kasirrawatjalan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.RiwayatTransaksiObat;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class KasirRawatJalanAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KasirRawatJalanAction.class);
    private HeaderDetailCheckup headerDetailCheckup;
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private RiwayatTindakanBo riwayatTindakanBoProxy;
    private KasirRawatJalanBo kasirRawatJalanBoProxy;
    private TransaksiObatBo transaksiObatBoProxy;
    private String id;
    private String idDetailCheckup;

    public void setTransaksiObatBoProxy(TransaksiObatBo transaksiObatBoProxy) {
        this.transaksiObatBoProxy = transaksiObatBoProxy;
    }

    public void setKasirRawatJalanBoProxy(KasirRawatJalanBo kasirRawatJalanBoProxy) {
        this.kasirRawatJalanBoProxy = kasirRawatJalanBoProxy;
    }

    public void setRiwayatTindakanBoProxy(RiwayatTindakanBo riwayatTindakanBoProxy) {
        this.riwayatTindakanBoProxy = riwayatTindakanBoProxy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
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

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
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
        logger.info("[KasirRawatJalanAction.search] start process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();

        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());

        try {
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getSearchRawatJalan(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[KasirRawatJalanAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[KasirRawatJalanAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[KasirRawatJalanAction.initForm] start process >>>");

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        HeaderDetailCheckup checkupdetail = new HeaderDetailCheckup();
        checkupdetail.setStDateFrom(tglToday);
        checkupdetail.setStDateTo(tglToday);

        setHeaderDetailCheckup(checkupdetail);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[KasirRawatJalanAction.initForm] end process <<<");
        return "search";
    }

    public List<RiwayatTindakan> getListTindakanRawat(String idDetail) {
        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {
            List<RiwayatTindakan> result = new ArrayList<>();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");

            RiwayatTindakan tindakanRawat = new RiwayatTindakan();
            tindakanRawat.setIdDetailCheckup(idDetail);
            tindakanRawat.setBranchId(CommonUtil.userBranchLogin());

            try {
                riwayatTindakanList = kasirRawatJalanBo.getListAllTindakan(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorAction.getListTindakanRawat] Error when get data tindakan rawat ", e);
            }
        }
        return riwayatTindakanList;

    }

    public List<TransaksiObatDetail> getListDetailResep(String idResep) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdPermintaanResep(idResep);
        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if (idResep != null && !"".equalsIgnoreCase(idResep)) {
            try {
                obatDetailList = transaksiObatBo.getSearchObatTransaksiByCriteria(transaksiObatDetail);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.searchResep] ERROR error when get searh resep. ", e);
                addActionError("[TransaksiObatAction.searchResep] ERROR error when get searh resep. " + e.getMessage());
            }
        }

        return obatDetailList;
    }

    public String printInvoice() {

        String id = getId();
        String idDetail = getIdDetailCheckup();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String logo = "";
        String unit = CommonUtil.userBranchNameLogin();
        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        switch (branch) {
            case CommonConstant.BRANCH_RS01:
                logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_RS01;
                break;
            case CommonConstant.BRANCH_RS02:
                logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_RS02;
                break;
            case CommonConstant.BRANCH_RS03:
                logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_RS03;
                break;
        }

        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
        riwayatTindakan.setIdDetailCheckup(idDetail);
        riwayatTindakan.setBranchId(CommonUtil.userBranchLogin());

        try {
            riwayatTindakanList = kasirRawatJalanBoProxy.getListAllTindakan(riwayatTindakan);
        } catch (GeneralBOException e) {
            logger.error("Foun error when search riwayat tindakan " + e.getMessage());
        }

        RiwayatTindakan result = new RiwayatTindakan();
        List<TransaksiObatDetail> resultListObat = new ArrayList<>();

        if (riwayatTindakanList.size() > 0) {
            result = riwayatTindakanList.get(0);

            for (RiwayatTindakan riwayat : riwayatTindakanList) {
                if ("resep".equalsIgnoreCase(riwayat.getKeterangan())) {
                    TransaksiObatDetail detail = new TransaksiObatDetail();
                    detail.setIdPermintaanResep(riwayat.getIdTindakan());

                    try {
                        obatDetailList = transaksiObatBoProxy.getSearchObatTransaksiByCriteria(detail);
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                    }

                    resultListObat.addAll(obatDetailList);
                }
            }

        }

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(riwayatTindakanList);
        JRBeanCollectionDataSource itemDataObat = new JRBeanCollectionDataSource(resultListObat);

        HeaderCheckup headerCheckup = getHeaderCheckup(id);
        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());


        reportParams.put("itemDataSource", itemData);
        reportParams.put("listObatDetail", itemDataObat);
        reportParams.put("unit", unit);
        reportParams.put("idDetailCheckup", idDetail);
        reportParams.put("noCheckup", id);
        reportParams.put("logo", logo);
        reportParams.put("nik", headerCheckup.getNoKtp());
        reportParams.put("nama", headerCheckup.getNama());
        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckup.getTglLahir());
        reportParams.put("tglLahir", headerCheckup.getTempatLahir() + ", " + formatDate);
        if ("L".equalsIgnoreCase(headerCheckup.getJenisKelamin())) {
            jk = "Laki-Laki";
        } else {
            jk = "Perempuan";
        }
        reportParams.put("jenisKelamin", jk);
        reportParams.put("jenisPasien", jenisPriksaPasien.getKeterangan());
        reportParams.put("poli", headerCheckup.getNamaPelayanan());
        reportParams.put("provinsi", headerCheckup.getNamaProvinsi());
        reportParams.put("kabupaten", headerCheckup.getNamaKota());
        reportParams.put("kecamatan", headerCheckup.getNamaKecamatan());
        reportParams.put("desa", headerCheckup.getNamaDesa());


        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_invoice";
    }

    private HeaderCheckup getHeaderCheckup(String noCheckup) {
        logger.info("[CheckupDetailAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()) {
            result = headerCheckupList.get(0);
        }

        logger.info("[CheckupDetailAction.getHeaderCheckup] end process <<<");
        return result;
    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa) {
        logger.info("[CheckupDetailAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()) {
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[CheckupDetailAction.getListJenisPeriksaPasien] end process <<<");
        return result;
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
