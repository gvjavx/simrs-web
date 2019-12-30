package com.neurix.simrs.transaksi.transaksiobat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransaksiObatAction extends BaseMasterAction {

    private static transient Logger logger = Logger.getLogger(TransaksiObatAction.class);

    private TransaksiObatDetail transaksiObatDetail;
    private TransaksiObatBo transaksiObatBoProxy;
    private PermintaanResep permintaanResep;
    private PermintaanResepBo permintaanResepBoProxy;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private String id;

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

    public void setPermintaanResepBoProxy(PermintaanResepBo permintaanResepBoProxy) {
        this.permintaanResepBoProxy = permintaanResepBoProxy;
    }

    public PermintaanResep getPermintaanResep() {
        return permintaanResep;
    }

    public void setPermintaanResep(PermintaanResep permintaanResep) {
        this.permintaanResep = permintaanResep;
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
        searchResep();
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[TransaksiObatAction.initForm] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultResep");
        session.removeAttribute("listOfResultObat");
        session.removeAttribute("listOfResult");

        setPermintaanResep(new PermintaanResep());

        logger.info("[TransaksiObatAction.initForm] END <<<<<<<");
        return "search";

    }

    public String searchResep() {
        logger.info("[TransaksiObatAction.searchResep] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PermintaanResep> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();
        String jk = "";
        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (PermintaanResep resep : listOfResult) {
                    if (id.equalsIgnoreCase(resep.getIdPermintaanResep())) {

                        HeaderDetailCheckup headerDetailCheckup = getDetailCheckup(resep.getIdDetailCheckup());
                        resep.setNoCheckup(headerDetailCheckup.getNoCheckup());

                        HeaderCheckup headerCheckup = getHeaderCheckup(headerDetailCheckup.getNoCheckup());
                        resep.setIdPasien(headerCheckup.getIdPasien());
                        resep.setNamaPasien(headerCheckup.getNama());
                        resep.setAlamat(headerCheckup.getJalan());
                        resep.setDesa(headerCheckup.getNamaDesa());
                        resep.setKecamatan(headerCheckup.getNamaKecamatan());
                        resep.setKota(headerCheckup.getNamaKota());
                        resep.setProvinsi(headerCheckup.getNamaProvinsi());
                        resep.setIdPelayanan(headerCheckup.getIdPelayanan());
                        resep.setNamaPelayanan(headerCheckup.getNamaPelayanan());
                        if(headerCheckup.getJenisKelamin()!= null){
                            if("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())){
                                jk = "Perempuan";
                            }else{
                                jk = "laki-Laki";
                            }
                        }
                        resep.setJenisKelamin(jk);
                        resep.setTempatLahir(headerCheckup.getTempatLahir());
                        resep.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
                        resep.setTempatTglLahir(headerCheckup.getTempatLahir()+", "+headerCheckup.getTglLahir().toString());
                        resep.setIdJenisPeriksa(headerCheckup.getIdJenisPeriksaPasien());
                        resep.setNik(headerCheckup.getNoKtp());
                        resep.setUrlKtp(headerCheckup.getUrlKtp());

                        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
                        resep.setJenisPeriksaPasien(jenisPriksaPasien.getKeterangan());

                        setPermintaanResep(resep);

                        break;
                    }
                }

            } else {
                setPermintaanResep(new PermintaanResep());
            }
        } else {
            setPermintaanResep(new PermintaanResep());
        }

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        String idPermintaan = getId();
        transaksiObatDetail.setIdPermintaanResep(idPermintaan);
        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if (transaksiObatDetail != null) {

            if (transaksiObatDetail.getIdPermintaanResep() != null && !"".equalsIgnoreCase(transaksiObatDetail.getIdPermintaanResep())) {
                try {
                    obatDetailList = transaksiObatBoProxy.getSearchObatTransaksiByCriteria(transaksiObatDetail);
                } catch (GeneralBOException e) {
                    logger.error("[TransaksiObatAction.searchResep] ERROR error when get searh resep. ", e);
                    addActionError("[TransaksiObatAction.searchResep] ERROR error when get searh resep. " + e.getMessage());
                }
            }
        }

        session = ServletActionContext.getRequest().getSession();
        List<TransaksiObatDetail> pembelianObatList = (List) session.getAttribute("listOfResultObat");

        // hitung total bayar
        BigInteger hitungTotalResep = hitungTotalBayar(obatDetailList);
        BigInteger hitungTotalPembelian = hitungTotalBayar(pembelianObatList);

        transaksiObatDetail.setTotalBayar(hitungTotalResep.add(hitungTotalPembelian));
        setTransaksiObatDetail(transaksiObatDetail);

        BigInteger jml = hitungTotalResep.add(hitungTotalPembelian);

        if (jml != null && !jml.equals(0)) {
            transaksiObatDetail.setTotalBayar(new BigInteger(String.valueOf(0)));
        } else {
            transaksiObatDetail.setTotalBayar(jml);
        }

        setTransaksiObatDetail(transaksiObatDetail);
        session.removeAttribute("listOfResultResep");
        session.setAttribute("listOfResultResep", obatDetailList);
        logger.info("[TransaksiObatAction.searchResep] END <<<<<<<");
        return "init_bayar";

    }

    public String pembayaran() {
        logger.info("[TransaksiObatAction.savePembayaran] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TransaksiObatDetail> obatResepList = (List) session.getAttribute("listOfResultResep");
        List<TransaksiObatDetail> pembelianObatList = (List) session.getAttribute("listOfResultObat");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        TransaksiObatDetail transaksiObatDetail = getTransaksiObatDetail();
        transaksiObatDetail.setLastUpdate(time);
        transaksiObatDetail.setLastUpdateWho(userLogin);
        transaksiObatDetail.setBranchId(CommonUtil.userBranchLogin());

        try {
            transaksiObatBoProxy.saveAdd(transaksiObatDetail, obatResepList, pembelianObatList);
        } catch (GeneralBOException e) {
            logger.error("[TransaksiObatAction.savePembayaran] ERROR error when save pembayaran. ", e);
            addActionError("[TransaksiObatAction.savePembayaran] ERROR error when save pembayaran. " + e.getMessage());
        }

        logger.info("[TransaksiObatAction.savePembayaran] END <<<<<<<");
        return initForm();
    }

    public String saveAddObat(String idObat, String qty) {
        logger.info("[TransaksiObatAction.savePembayaran] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TransaksiObatDetail> obatResepList = (List) session.getAttribute("listOfResultResep");
        List<TransaksiObatDetail> pembelianObatList = (List) session.getAttribute("listOfResultObat");

        if (pembelianObatList == null) {
            pembelianObatList = new ArrayList<>();
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setFlag("Y");

        List<Obat> obatList = new ArrayList<>();
        try {
            obatList = obatBo.getByCriteria(obat);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatAction.saveAddObat] ERROR error when get searh obat. ", e);
            addActionError("[TransaksiObatAction.saveAddObat] ERROR error when get searh obat. " + e.getMessage());
        }

        if (obatList.size() > 0) {
            Obat obatData = obatList.get(0);

            BigInteger bQty = new BigInteger(String.valueOf(qty));
            BigInteger jml = obatData.getHarga().multiply(bQty);

            TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
            transaksiObatDetail.setIdObat(obatData.getIdObat());
            transaksiObatDetail.setNamaObat(obatData.getNamaObat());
            transaksiObatDetail.setQty(bQty);
            transaksiObatDetail.setHarga(obatData.getHarga());
            transaksiObatDetail.setTotalHarga(jml);
            transaksiObatDetail.setFlag("Y");
            transaksiObatDetail.setAction("C");
            pembelianObatList.add(transaksiObatDetail);
        }

        // hitung total bayar
        BigInteger hitungTotalResep = hitungTotalBayar(obatResepList);
        BigInteger hitungTotalPembelian = hitungTotalBayar(pembelianObatList);
        TransaksiObatDetail newTransaksi = new TransaksiObatDetail();

        String idResep = "";
        if (obatResepList != null && obatResepList.size() > 0) {
            idResep = obatResepList.get(0).getIdPermintaanResep();
        }

        newTransaksi.setIdPermintaanResep(idResep);
        newTransaksi.setTotalBayar(hitungTotalResep.add(hitungTotalPembelian));

        setTransaksiObatDetail(newTransaksi);
        session.setAttribute("listOfResultObat", pembelianObatList);
        logger.info("[TransaksiObatAction.savePembayaran] END <<<<<<<");
        return "success";
    }

    private BigInteger hitungTotalBayar(List<TransaksiObatDetail> transaksiObatDetails) {

        BigInteger total = new BigInteger(String.valueOf("0"));
        if (transaksiObatDetails != null && transaksiObatDetails.size() > 0) {
            for (TransaksiObatDetail trans : transaksiObatDetails) {
                total = total.add(trans.getTotalHarga());
            }
        }
        return total;
    }

    public String init() {
        logger.info("[TransaksiObatAction.init] START >>>>>>>");
        logger.info("[TransaksiObatAction.init] END <<<<<<<");
        return "search";
    }

    public String resetobat() {
        logger.info("[TransaksiObatAction.resetobat] START >>>>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultObat");
        searchResep();
        logger.info("[TransaksiObatAction.resetobat] END <<<<<<<");
        return "search";
    }


    public String searchResepPasien() {

        logger.info("[CheckupAction.search] start process >>>");

        PermintaanResep permintaanResep = getPermintaanResep();
        List<PermintaanResep> listResep = new ArrayList();
        permintaanResep.setBranchId(CommonUtil.userBranchLogin());
        permintaanResep.setTujuanPelayanan(CommonUtil.userPelayananIdLogin());

        try {
            listResep = transaksiObatBoProxy.getListResepPasien(permintaanResep);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listResep);

        logger.info("[CheckupAction.search] end process <<<");
        return "search";

    }

    public String saveAntrianResep(String idResep, String isUmum) {

        logger.info("[TransaksiObatAction.saveAntrianResep] START >>>>>>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        PermintaanResep permintaanResep = new PermintaanResep();
        permintaanResep.setLastUpdateWho(userLogin);
        permintaanResep.setLastUpdate(time);
        permintaanResep.setTglAntrian(time);
        permintaanResep.setAction("U");
        permintaanResep.setIsUmum(isUmum);
        permintaanResep.setIdPermintaanResep(idResep);

        try {
            transaksiObatBo.saveAntrianResep(permintaanResep);
        }catch (GeneralBOException e){
            logger.error("[TransaksiObatAction.saveAntrianResep] ERROR error when get searh obat. ", e);
            addActionError("[TransaksiObatAction.saveAntrianResep] ERROR error when get searh obat. " + e.getMessage());
        }

        logger.info("[TransaksiObatAction.saveAntrianResep] END <<<<<<<");
        return SUCCESS;
    }

    private HeaderCheckup getHeaderCheckup(String noCheckup){
        logger.info("[PeriksaLabAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e){
            logger.error("[PeriksaLabAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()){
            result = headerCheckupList.get(0);
        }

        logger.info("[PeriksaLabAction.getHeaderCheckup] end process <<<");
        return result;
    }

    private HeaderDetailCheckup getDetailCheckup(String idDetailCheckup){
        logger.info("[PeriksaLabAction.getDetailCheckup] start process >>>");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);

        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
        try {
            detailCheckupList = checkupDetailBoProxy.getByCriteria(detailCheckup);
        } catch (GeneralBOException e){
            logger.error("[PeriksaLabAction.getDetailCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderDetailCheckup result = new HeaderDetailCheckup();
        if (!detailCheckupList.isEmpty()){
            result = detailCheckupList.get(0);
        }

        logger.info("[PeriksaLabAction.getDetailCheckup] end process <<<");
        return result;
    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa){
        logger.info("[PeriksaLabAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e){
            logger.error("[PeriksaLabAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()){
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[PeriksaLabAction.getListJenisPeriksaPasien] end process <<<");
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


    public TransaksiObatDetail getTransaksiObatDetail() {
        return transaksiObatDetail;
    }

    public void setTransaksiObatDetail(TransaksiObatDetail transaksiObatDetail) {
        this.transaksiObatDetail = transaksiObatDetail;
    }

    public void setTransaksiObatBoProxy(TransaksiObatBo transaksiObatBoProxy) {
        this.transaksiObatBoProxy = transaksiObatBoProxy;
    }

}