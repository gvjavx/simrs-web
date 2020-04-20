package com.neurix.simrs.transaksi.transaksiobat.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;


public class TransaksiObatAction extends BaseMasterAction {

    private static transient Logger logger = Logger.getLogger(TransaksiObatAction.class);

    private TransaksiObatDetail transaksiObatDetail;
    private TransaksiObatBo transaksiObatBoProxy;
    private PermintaanResep permintaanResep;
    private PermintaanResepBo permintaanResepBoProxy;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private BillingSystemBo billingSystemBoProxy;
    private ObatPoliBo obatPoliBoProxy;
    private BranchBo branchBoProxy;
    private PelayananBo pelayananBoProxy;
    private String id;
    private String idResep;
    private String idApprove;
    private String idPermintaan;

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public String getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public String getIdApprove() {
        return idApprove;
    }

    public void setIdApprove(String idApprove) {
        this.idApprove = idApprove;
    }

    public String getIdResep() {
        return idResep;
    }

    public void setIdResep(String idResep) {
        this.idResep = idResep;
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
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
        PermintaanResep resep = new PermintaanResep();
        setPermintaanResep(resep);

        logger.info("[TransaksiObatAction.initForm] END <<<<<<<");
        return "search";

    }

    public String searchResep() {
        logger.info("[TransaksiObatAction.searchResep] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<PermintaanResep> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();
        String idPermintaan = getIdPermintaan();

        HeaderCheckup checkup = new HeaderCheckup();
        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();

        String jk = "";

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found error when detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            PermintaanResep resep = new PermintaanResep();
            resep.setNoCheckup(checkup.getNoCheckup());
            resep.setIdDetailCheckup(checkup.getIdDetailCheckup());
            resep.setIdPasien(checkup.getIdPasien());
            resep.setNamaPasien(checkup.getNama());
            resep.setAlamat(checkup.getJalan());
            resep.setDesa(checkup.getNamaDesa());
            resep.setKecamatan(checkup.getNamaKecamatan());
            resep.setKota(checkup.getNamaKota());
            resep.setProvinsi(checkup.getNamaProvinsi());
            resep.setIdPelayanan(checkup.getIdPelayanan());
            resep.setNamaPelayanan(checkup.getNamaPelayanan());
            if (checkup.getJenisKelamin() != null) {
                if ("P".equalsIgnoreCase(checkup.getJenisKelamin())) {
                    jk = "Perempuan";
                } else {
                    jk = "laki-Laki";
                }
            }
            resep.setJenisKelamin(jk);
            resep.setTempatLahir(checkup.getTempatLahir());
            resep.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
            String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
            resep.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
            resep.setNik(checkup.getNoKtp());
            resep.setJenisPeriksaPasien(checkup.getIdJenisPeriksaPasien());
            resep.setUrlKtp(checkup.getUrlKtp());
            resep.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
            resep.setIdPermintaanResep(idPermintaan);
            setPermintaanResep(resep);

        } else {
            setPermintaanResep(new PermintaanResep());
        }

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        PermintaanResep permintaanResep = new PermintaanResep();
        permintaanResep.setIdPermintaanResep(idPermintaan);
        permintaanResep.setLastUpdate(time);
        permintaanResep.setLastUpdateWho(userLogin);

        try {
            transaksiObatBoProxy.updateAntrianResep(permintaanResep);
        } catch (GeneralBOException e) {
            logger.error("[TransaksiObatAction.searchResep] ERROR error update status antrian resep. ", e);
            addActionError("[TransaksiObatAction.searchResep] ERROR error update status antrian resep. " + e.getMessage());
        }


//        if (id != null && !"".equalsIgnoreCase(id)) {
//
//            if (listOfResult != null) {
//
//                for (PermintaanResep resep : listOfResult) {
//                    if (id.equalsIgnoreCase(resep.getIdPermintaanResep())) {
//
//                        HeaderDetailCheckup headerDetailCheckup = getDetailCheckup(resep.getIdDetailCheckup());
//                        resep.setNoCheckup(headerDetailCheckup.getNoCheckup());
//
//                        HeaderCheckup headerCheckup = getHeaderCheckup(headerDetailCheckup.getNoCheckup());
//                        resep.setIdPasien(headerCheckup.getIdPasien());
//                        resep.setNamaPasien(headerCheckup.getNama());
//                        resep.setAlamat(headerCheckup.getJalan());
//                        resep.setDesa(headerCheckup.getNamaDesa());
//                        resep.setKecamatan(headerCheckup.getNamaKecamatan());
//                        resep.setKota(headerCheckup.getNamaKota());
//                        resep.setProvinsi(headerCheckup.getNamaProvinsi());
//                        resep.setIdPelayanan(headerCheckup.getIdPelayanan());
//                        resep.setNamaPelayanan(headerCheckup.getNamaPelayanan());
//                        if (headerCheckup.getJenisKelamin() != null) {
//                            if ("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())) {
//                                jk = "Perempuan";
//                            } else {
//                                jk = "laki-Laki";
//                            }
//                        }
//                        resep.setJenisKelamin(jk);
//                        resep.setTempatLahir(headerCheckup.getTempatLahir());
//                        resep.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
//
//                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckup.getTglLahir());
//
//                        resep.setTempatTglLahir(headerCheckup.getTempatLahir() + ", " + formatDate);
//                        resep.setIdJenisPeriksa(headerCheckup.getIdJenisPeriksaPasien());
//                        resep.setNik(headerCheckup.getNoKtp());
//                        resep.setUrlKtp(headerCheckup.getUrlKtp());
//
//                        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
//                        resep.setJenisPeriksaPasien(jenisPriksaPasien.getKeterangan());
//
//                        setPermintaanResep(resep);
//
//                        String userLogin = CommonUtil.userLogin();
//                        Timestamp time = new Timestamp(System.currentTimeMillis());
//                        PermintaanResep permintaanResep = new PermintaanResep();
//                        permintaanResep.setIdPermintaanResep(resep.getIdPermintaanResep());
//                        permintaanResep.setLastUpdate(time);
//                        permintaanResep.setLastUpdateWho(userLogin);
//
//                        try {
//                            transaksiObatBoProxy.updateAntrianResep(permintaanResep);
//                        } catch (GeneralBOException e) {
//                            logger.error("[TransaksiObatAction.searchResep] ERROR error update status antrian resep. ", e);
//                            addActionError("[TransaksiObatAction.searchResep] ERROR error update status antrian resep. " + e.getMessage());
//                        }
//
//                        break;
//                    }
//                }
//
//            } else {
//                setPermintaanResep(new PermintaanResep());
//            }
//        } else {
//            setPermintaanResep(new PermintaanResep());
//        }

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
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

        BigInteger hitungTotalResep = hitungTotalBayar(obatDetailList);
        setTransaksiObatDetail(transaksiObatDetail);
        BigInteger jml = hitungTotalResep;

        if (jml != null && !jml.equals(0)) {
            transaksiObatDetail.setTotalBayar(jml);
        } else {
            transaksiObatDetail.setTotalBayar(new BigInteger(String.valueOf(0)));
        }

        PermintaanResep permintaan = new PermintaanResep();
        permintaan.setIdPermintaanResep(idPermintaan);

        List<PermintaanResep> permintaanResepList = new ArrayList<>();

        try {
            permintaanResepList = permintaanResepBoProxy.getByCriteria(permintaan);
        } catch (GeneralBOException e) {
            logger.error("[TransaksiObatAction.searchResep] ERROR error when get searh resep. ", e);
            addActionError("[TransaksiObatAction.searchResep] ERROR error when get searh resep. " + e.getMessage());
        }

        PermintaanResep resep = new PermintaanResep();
        if (!permintaanResepList.isEmpty()) {
            resep = permintaanResepList.get(0);
            if (resep != null) {
                transaksiObatDetail.setIdApprovalObat(resep.getIdApprovalObat());
            }
        }

        setTransaksiObatDetail(transaksiObatDetail);
        session.removeAttribute("listOfResultResep");
        session.setAttribute("listOfResultResep", obatDetailList);
        logger.info("[TransaksiObatAction.searchResep] END <<<<<<<");
        return "init_bayar";

    }

    public List<TransaksiObatDetail> getListResepPasien(String noResep) {

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdPermintaanResep(noResep);

        if (transaksiObatDetail != null) {

            if (transaksiObatDetail.getIdPermintaanResep() != null && !"".equalsIgnoreCase(transaksiObatDetail.getIdPermintaanResep())) {
                try {
                    obatDetailList = transaksiObatBo.getSearchObatTransaksiByCriteria(transaksiObatDetail);
                } catch (GeneralBOException e) {
                    logger.error("[TransaksiObatAction.searchResep] ERROR error when get searh resep. ", e);
                    addActionError("[TransaksiObatAction.searchResep] ERROR error when get searh resep. " + e.getMessage());
                }
            }
        }

        return obatDetailList;
    }

    public String pembayaran() {
        logger.info("[TransaksiObatAction.savePembayaran] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TransaksiObatDetail> obatResepList = (List) session.getAttribute("listOfResultResep");
        List<TransaksiObatDetail> pembelianObatList = (List) session.getAttribute("listOfResultObat");

        String idPoli = CommonUtil.userPelayananIdLogin();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        TransaksiObatDetail transaksiObatDetail = getTransaksiObatDetail();
        transaksiObatDetail.setLastUpdate(time);
        transaksiObatDetail.setLastUpdateWho(userLogin);
        transaksiObatDetail.setBranchId(CommonUtil.userBranchLogin());
        transaksiObatDetail.setIdPelayanan(idPoli);

        try {
            transaksiObatBoProxy.saveAdd(transaksiObatDetail, obatResepList, pembelianObatList);
        } catch (GeneralBOException e) {
            logger.error("[TransaksiObatAction.savePembayaran] ERROR error when save pembayaran. ", e);
            addActionError("[TransaksiObatAction.savePembayaran] ERROR error when save pembayaran. " + e.getMessage());
        }

        logger.info("[TransaksiObatAction.savePembayaran] END <<<<<<<");
        return "search";
    }

    public String saveAddObat(String idObat, String qty, String jenisSatuan) {
        logger.info("[TransaksiObatAction.savePembayaran] START >>>>>>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<TransaksiObatDetail> obatResepList = (List) session.getAttribute("listOfResultResep");
        List<TransaksiObatDetail> pembelianObatList = (List) session.getAttribute("listOfResultObat");

        if (pembelianObatList == null) {
            pembelianObatList = new ArrayList<>();
        }

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

            HargaObat hargaObat = new HargaObat();
            hargaObat.setIdObat(obatData.getIdObat());
            hargaObat.setFlag("Y");

            List<MtSimrsHargaObatEntity> hargaObatEntities = transaksiObatBo.getListEntityHargaObat(hargaObat);

            if (hargaObatEntities.size() > 0) {
                MtSimrsHargaObatEntity hargaObatEntity = hargaObatEntities.get(0);

                TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                BigInteger cons = obatData.getLembarPerBox().multiply(obatData.getBijiPerLembar());
                if ("box".equalsIgnoreCase(jenisSatuan)) {
                    transaksiObatDetail.setHarga(hargaObatEntity.getHargaJual().multiply(new BigDecimal(cons)).toBigInteger());
                    transaksiObatDetail.setTotalHarga((hargaObatEntity.getHargaJual().multiply(new BigDecimal(cons)).toBigInteger()).multiply(new BigInteger(String.valueOf(qty))));
                }
                if ("lembar".equalsIgnoreCase(jenisSatuan)) {
                    BigInteger bHarga = hargaObatEntity.getHargaJual().toBigInteger();
                    BigInteger nHarga = obatData.getBijiPerLembar().multiply(bHarga);
                    transaksiObatDetail.setHarga(nHarga);
                    transaksiObatDetail.setTotalHarga(nHarga.multiply(new BigInteger(String.valueOf(qty))));
                }
                if ("biji".equalsIgnoreCase(jenisSatuan)) {
                    transaksiObatDetail.setHarga(hargaObatEntity.getHargaJual().toBigInteger());
                    transaksiObatDetail.setTotalHarga((hargaObatEntity.getHargaJual().toBigInteger()).multiply(new BigInteger(String.valueOf(qty))));
                }
                transaksiObatDetail.setIdObat(obatData.getIdObat());
                transaksiObatDetail.setNamaObat(obatData.getNamaObat());
                transaksiObatDetail.setQty(new BigInteger(String.valueOf(qty)));
                transaksiObatDetail.setQtyApprove(new BigInteger(String.valueOf(qty)));
                transaksiObatDetail.setJenisSatuan(jenisSatuan);
                transaksiObatDetail.setFlag("Y");
                transaksiObatDetail.setAction("C");
                pembelianObatList.add(transaksiObatDetail);
            }
        }

        // hitung total bayar
        BigInteger hitungTotalPembelian = hitungTotalBayar(pembelianObatList);
        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();

        transaksiObatDetail.setStTotalbayar(hitungTotalPembelian.toString());
        setTransaksiObatDetail(transaksiObatDetail);
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
        pembelianObat();
        logger.info("[TransaksiObatAction.resetobat] END <<<<<<<");
        return "init_pembelian_obat";
    }


    public String searchResepPasien() {

        logger.info("[CheckupAction.search] start process >>>");

        PermintaanResep permintaanResep = getPermintaanResep();
        List<PermintaanResep> listResep = new ArrayList();
        permintaanResep.setBranchId(CommonUtil.userBranchLogin());
        permintaanResep.setTujuanPelayanan(CommonUtil.userPelayananIdLogin());

        if ("4".equalsIgnoreCase(permintaanResep.getStatus())) {
            permintaanResep.setFlag("N");
            permintaanResep.setStatus("");
        }

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
        } catch (GeneralBOException e) {
            logger.error("[TransaksiObatAction.saveAntrianResep] ERROR error when get searh obat. ", e);
            addActionError("[TransaksiObatAction.saveAntrianResep] ERROR error when get searh obat. " + e.getMessage());
        }

        logger.info("[TransaksiObatAction.saveAntrianResep] END <<<<<<<");
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

        logger.info("[TransaksiObatAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }

    public List<ObatPoli> listObatPoliEntity(String idObat) {
        logger.info("[TransaksiObatAction.initApprovePermintaan] START process >>>");
        List<ObatPoli> obatPoliList = new ArrayList<>();

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdObat(idObat);
        obatPoli.setBranchId(CommonUtil.userBranchLogin());
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        obatPoli.setExp("Y");
        obatPoli.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        if (idObat != null && !"".equalsIgnoreCase(idObat)) {
            try {
                obatPoliList = obatPoliBo.getObatPoliByCriteria(obatPoli);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.getListObatEntity] ERROR when get data list obat, ", e);
                addActionError("[TransaksiObatAction.getListObatEntity] ERROR when get data list obat, " + e.getMessage());
            }
            logger.info("[TransaksiObatAction.initApprovePermintaan] END process <<<");
            return obatPoliList;
        } else {
            return null;
        }
    }

    public CheckObatResponse saveVerifikasiResep(String idTransaksi, String jsonString) throws JSONException {
        logger.info("[TransaksiObatAction.saveVerifikasiResep] START process >>>");

        CheckObatResponse response = new CheckObatResponse();

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();

        MtSimrsTransaksiObatDetailBatchEntity batchEntity;
        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = new ArrayList<>();
        if (jsonString != null && !"".equalsIgnoreCase(jsonString)) {
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                batchEntity = new MtSimrsTransaksiObatDetailBatchEntity();
                JSONObject obj = json.getJSONObject(i);

                if (!"".equalsIgnoreCase(obj.getString("Qty Approve"))) {
                    batchEntity.setIdTransaksiObatDetail(idTransaksi);
                    batchEntity.setIdBarang(obj.getString("ID Barang"));
                    batchEntity.setExpiredDate(Date.valueOf(obj.getString("Expired Date")));
                    batchEntity.setQtyApprove(new BigInteger(obj.getString("Qty Approve")));
                    batchEntity.setJenisSatuan(obj.getString("Jenis Satuan"));
                    batchEntity.setFlag("Y");
                    batchEntity.setAction("C");
                    batchEntity.setLastUpdate(time);
                    batchEntity.setLastUpdateWho(userLogin);
                    batchEntity.setCreatedDate(time);
                    batchEntity.setCreatedWho(userLogin);
                    batchEntities.add(batchEntity);
                }
            }
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

        try {
            transaksiObatBo.saveVerifikasiObat(batchEntities);
            response.setStatus(SUCCESS);
            response.setMessage("SUCCESS");
        } catch (GeneralBOException e) {
            response.setStatus(ERROR);
            response.setMessage("[TransaksiObatAction.saveVerifikasiResep] ERROR when save list obat, " + e.getMessage());

            logger.error("[TransaksiObatAction.saveVerifikasiResep] ERROR when save list obat, ", e);
            addActionError("[TransaksiObatAction.saveVerifikasiResep] ERROR when save list obat, " + e.getMessage());
        }

        logger.info("[TransaksiObatAction.saveVerifikasiResep] END process <<<");
        return response;
    }

    public CheckObatResponse saveApproveResepObatPoli(String idApproval) {
        logger.info("[TransaksiObatAction.saveVerifikasiResep] START process >>>");

        CheckObatResponse response = new CheckObatResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();

        TransaksiObatDetail obatDetail = new TransaksiObatDetail();
        obatDetail.setIdApprovalObat(idApproval);
        obatDetail.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        obatDetail.setBranchId(CommonUtil.userBranchLogin());
        obatDetail.setLastUpdate(time);
        obatDetail.setLastUpdateWho(userLogin);

        try {

            // create jurnal Pengeluaran Obat Apotik
            JurnalResponse jurnalResponse = createJurnalPengeluaranObatApotik(idApproval);
            if ("error".equalsIgnoreCase(jurnalResponse.getStatus())) {
                return response;
            } else {
                obatDetail.setNoJurnal(jurnalResponse.getNoJurnal());
            }

            transaksiObatBo.saveApproveResepPoli(obatDetail);

            response.setStatus(SUCCESS);
            response.setMessage("SUCCESS");

            Boolean obatKronis = false;

            try {
                obatKronis = transaksiObatBo.cekObatKronis(idApproval);
            }catch (GeneralBOException e){
                logger.error("found Error when search obat kronis ");
            }

            TransaksiObatDetail tarifResep = new TransaksiObatDetail();

            try {
                tarifResep = transaksiObatBo.getTarifApproveResep(idApproval);
            }catch (GeneralBOException e){
                logger.error("found error when search tarif resep");
            }

            if(tarifResep.getTotalHarga() != null){

                if(obatKronis){

                    RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                    riwayatTindakan.setIdTindakan(tarifResep.getIdPermintaanResep());
                    riwayatTindakan.setIdDetailCheckup(tarifResep.getIdDetailCheckup());
                    riwayatTindakan.setNamaTindakan("Tarif Resep dengan No. Resep " + tarifResep.getIdPermintaanResep());
                    riwayatTindakan.setTotalTarif(new BigDecimal(tarifResep.getTotalHarga()));
                    riwayatTindakan.setKeterangan("resep");
                    riwayatTindakan.setJenisPasien(tarifResep.getJenisPeriksaPasien());
                    riwayatTindakan.setAction("C");
                    riwayatTindakan.setFlag("Y");
                    riwayatTindakan.setCreatedWho(userLogin);
                    riwayatTindakan.setCreatedDate(time);
                    riwayatTindakan.setLastUpdate(time);
                    riwayatTindakan.setLastUpdateWho(userLogin);
                    riwayatTindakan.setTanggalTindakan(time);

                    try {

                        riwayatTindakanBo.saveAdd(riwayatTindakan);

                        String invoice = "";
                        JurnalResponse jurnalClosingResponse = closingJurnalNonTunai(tarifResep.getIdDetailCheckup(),tarifResep.getIdPelayanan(), tarifResep.getIdPasien());
                        if ("error".equalsIgnoreCase(jurnalClosingResponse.getStatus())) {
                            response.setMessage(jurnalClosingResponse.getMsg());
                            return response;
                        } else {
                            invoice = jurnalClosingResponse.getInvoice();
                        }

                        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                        detailCheckup.setIdDetailCheckup(tarifResep.getIdDetailCheckup());
                        detailCheckup.setStatusPeriksa("3");
                        detailCheckup.setKeteranganSelesai("Sembuh");
                        detailCheckup.setStatus("selesai");
                        detailCheckup.setLastUpdate(time);
                        detailCheckup.setLastUpdateWho(userLogin);
                        detailCheckup.setInvoice(invoice);
                        detailCheckup.setFlag("Y");
                        detailCheckup.setAction("U");

                        try {
                            checkupDetailBo.saveEdit(detailCheckup);
                        }catch (GeneralBOException e){
                            logger.error("Found Error "+e);
                        }

                    } catch (GeneralBOException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                    }

                }else{

                    RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                    riwayatTindakan.setIdDetailCheckup(tarifResep.getIdDetailCheckup());
                    riwayatTindakan.setIdTindakan(tarifResep.getIdPermintaanResep());
                    riwayatTindakan.setTotalTarif(new BigDecimal(tarifResep.getTotalHarga()));
                    riwayatTindakan.setLastUpdateWho(userLogin);
                    riwayatTindakan.setLastUpdate(time);

                    try {
                        riwayatTindakanBo.saveEdit(riwayatTindakan);
                    }catch (GeneralBOException e){
                        logger.error("found error "+e);
                    }
                }
            }
        } catch (GeneralBOException e) {

            response.setStatus(ERROR);
            response.setMessage("[TransaksiObatAction.saveVerifikasiResep] ERROR when save list obat, " + e.getMessage());
            logger.error("[TransaksiObatAction.saveApproveResepObatPoli] ERROR when save list obat, ", e);
            return response;
        }

        logger.info("[TransaksiObatAction.saveVerifikasiResep] END process <<<");
        return response;
    }

    private JurnalResponse createJurnalPengeluaranObatApotik(String idApprove) {

        JurnalResponse response = new JurnalResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        KelasRuanganBo kelasRuanganBo = (KelasRuanganBo) ctx.getBean("kelasRuanganBoProxy");
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

        String namaApotek = "";
        String divisiId = "";
        String idPasien = "";
        String idDetailCheckup = "";
        PermintaanResep permintaanResep = new PermintaanResep();
        permintaanResep.setIdApprovalObat(idApprove);
        List<PermintaanResep> permintaanReseps = permintaanResepBo.getByCriteria(permintaanResep);
        if (permintaanReseps.size() > 0){
            for (PermintaanResep dataPermintaan : permintaanReseps){

                if (dataPermintaan.getIdDetailCheckup() != null){

                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(dataPermintaan.getIdDetailCheckup());
                    if (detailCheckupEntity != null){

                        idDetailCheckup = detailCheckupEntity.getIdDetailCheckup();
                        ItSimrsHeaderChekupEntity chekupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
                        if (chekupEntity != null){
                            idPasien = chekupEntity.getIdPasien();
                        }
                        try {
                            ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());


                            if (pelayananEntity != null && pelayananEntity.getTipePelayanan() != null){

                                // jika poli selain rawat inap maka mengambil kodering dari pelayanan
                                // jika poli rawat rawat inap maka mengambil koderBing dari kelas ruangan , Sigit
                                if ("rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())){

                                    RawatInap rawatInap = new RawatInap();
                                    rawatInap.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                                    rawatInap.setFlag("Y");

                                    List<ItSimrsRawatInapEntity> rawatInapEntities = rawatInapBo.getListEntityByCriteria(rawatInap);
                                    if (rawatInapEntities.size() > 0){
                                        for (ItSimrsRawatInapEntity rawatInapEntity : rawatInapEntities){
                                            MtSimrsRuanganEntity ruanganEntity = ruanganBo.getEntityRuanganById(rawatInapEntity.getIdRuangan());
                                            if (ruanganEntity != null){
                                                ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganBo.getKelasRuanganById(ruanganEntity.getIdKelasRuangan());
                                                if (kelasRuanganEntity != null){

                                                    ImPosition position = positionBo.getPositionEntityById(kelasRuanganEntity.getDivisiId());
                                                    if (position != null){
                                                        divisiId = position.getKodering();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (pelayananEntity != null && pelayananEntity.getDivisiId() != null){

                                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                                        if (position != null){
                                            divisiId = position.getKodering();
                                        }

                                    } else {
                                        response.setStatus("error");
                                        response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] tidak ditemukan data kodering");
                                        return response;
                                    }
                                }
                            } else {
                                response.setStatus("error");
                                response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] tidak ditemukan data tipePelayanan");
                                return response;
                            }
                        } catch (GeneralBOException e){
                            response.setStatus("error");
                            response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] tidak ditemukan idPelayanan");
                            return response;
                        }
                    } else {
                        response.setStatus("error");
                        response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] tidak ditemukan idDetailCheckup");
                        return response;
                    }
                } else {
                    response.setStatus("error");
                    response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] tidak ditemukan idDetailCheckup");
                    return response;
                }

                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(dataPermintaan.getTujuanPelayanan());
                List<Pelayanan> pelayanans = pelayananBo.getByCriteria(pelayanan);
                if (pelayanans.size() > 0 ){
                    for (Pelayanan dataPelayanan : pelayanans){
                        namaApotek = dataPelayanan.getNamaPelayanan();
                    }
                }
            }
        }



        BigDecimal biayaPersediaan = new BigDecimal(0);
        List<Map> listMapPersediaan = new ArrayList<>();
        List<TransaksiObatDetail> transaksiObatDetails = transaksiObatBo.getListPermintaanBatch(idApprove, "");
        if (transaksiObatDetails.size() > 0) {
            for (TransaksiObatDetail trans : transaksiObatDetails) {

                BigDecimal hargaRata = new BigDecimal(0);
                ImSimrsObatEntity obatEntity = obatBo.getObatByIdBarang(trans.getIdBarang());
                if (obatEntity != null) {
                    if ("box".equalsIgnoreCase(trans.getJenisSatuan()))
                        hargaRata = obatEntity.getAverageHargaBox();
                    if ("lembar".equalsIgnoreCase(trans.getJenisSatuan()))
                        hargaRata = obatEntity.getAverageHargaLembar();
                    if ("biji".equalsIgnoreCase(trans.getJenisSatuan()))
                        hargaRata = obatEntity.getAverageHargaBiji();
                }

                BigDecimal hargaTotal = hargaRata.multiply(new BigDecimal(trans.getQtyApprove()));
                biayaPersediaan = biayaPersediaan.add(hargaTotal);

                Map mapPersediaanObat = new HashMap();
                mapPersediaanObat.put("kd_barang", trans.getIdBarang());
                mapPersediaanObat.put("nilai", hargaTotal);
                listMapPersediaan.add(mapPersediaanObat);
            }
        } else {
            response.setStatus("error");
            response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] ERROR data obat kosong");
            return response;
        }


        Map mapBiaya = new HashMap();
        mapBiaya.put("divisi_id", divisiId);
        mapBiaya.put("nilai", biayaPersediaan);

        Map mapJurnal = new HashMap();
        mapJurnal.put("biaya_persediaan_obat", mapBiaya);
        mapJurnal.put("persediaan_apotik", listMapPersediaan);

        String branchId = CommonUtil.userBranchLogin();
        String branchName = CommonUtil.userBranchNameLogin();

        String catatan = "Pengeluaran Obat "+namaApotek+" "+branchName+" No. Detail Checkup : "+idDetailCheckup+" No. RM "+idPasien;

        String noJurnal = "";

        try {
            noJurnal = billingSystemBo.createJurnal("30", mapJurnal, branchId, catatan, "Y");
            response.setNoJurnal(noJurnal);
            response.setStatus("success");
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] ERROR when search list obat, " + e.getMessage());
            logger.error("[TransaksiObatAction.createJurnalPengeluaranObatApotik] ERROR when search list obat, ", e);
            return response;
        }
        return response;
    }

    private JurnalResponse closingJurnalNonTunai(String idDetailCheckup, String idPoli, String idPasien) {

        JurnalResponse response = new JurnalResponse();
        String branchId = CommonUtil.userBranchLogin();
        String invoice = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setIdPelayanan(idPoli);

        List<Pelayanan> pelayanans = new ArrayList<>();
        try {
            pelayanans = pelayananBo.getByCriteria(pelayanan);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error when pelayanan, ", e);
        }

        String kode = "";
        String transId = "";
        String ketPoli = "";
        String ketResep = "";
        if (pelayanans.size() > 0) {
            Pelayanan pelayananData = pelayanans.get(0);

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
            headerDetailCheckup.setStatusBayar("Y");
            List<HeaderDetailCheckup> detailCheckupUangMuka = checkupDetailBo.getListUangPendaftaran(headerDetailCheckup);


            // mencari jumlah um dan no bukti uang muka
            BigDecimal jumlahUm = new BigDecimal(0);
            String idUm = "";
            if (detailCheckupUangMuka.size() > 0) {
                for (HeaderDetailCheckup detailCheckup : detailCheckupUangMuka) {
                    jumlahUm = new BigDecimal(detailCheckup.getJumlahUangMukaDibayar());
                    idUm = detailCheckup.getNoUangMuka();
                }
            }

            // get all sum tindakan, sum resep
            String isResep = checkupDetailBo.findResep(idDetailCheckup);
            BigDecimal jumlahResep = checkupDetailBo.getSumJumlahTindakanNonBpjs(idDetailCheckup, "resep");
            BigDecimal jumlahTindakan = checkupDetailBo.getSumJumlahTindakanNonBpjs(idDetailCheckup, "");
            BigDecimal ppnObat = new BigDecimal(0);
            if (jumlahResep.compareTo(new BigDecimal(0)) == 1) {
                ppnObat = jumlahResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            // jumlah tindakan saja. tindakan total - jumlah resep
            jumlahTindakan = jumlahTindakan.subtract(jumlahResep);

            Map mapUangMuka = new HashMap();
            mapUangMuka.put("bukti", idUm);
            mapUangMuka.put("nilai", jumlahUm);

            Map hsCriteria = new HashMap();
            hsCriteria.put("pasien_id", idPasien);
            // jumlah debit uang muka
            hsCriteria.put("uang_muka", mapUangMuka);

            BigDecimal jumlah = new BigDecimal(0);

            if ("Y".equalsIgnoreCase(isResep) || "rawat_inap".equalsIgnoreCase(pelayananData.getTipePelayanan())) {
                ketResep = "Dengan Obat";

                // kredit jumlah obat
                hsCriteria.put("pendapatan_obat_non_bpjs", jumlahResep);
                // kredit ppn
                hsCriteria.put("ppn_keluaran", ppnObat);

                // jika ada resep dan ppn untuk debit piutang
                jumlah = jumlah.add(jumlahResep.add(ppnObat));
            } else {
                ketResep = "Tanpa Obat";
            }

            if ("rawat_jalan".equalsIgnoreCase(pelayananData.getTipePelayanan()) || "igd".equalsIgnoreCase(pelayananData.getTipePelayanan())) {
                kode = "JRJ";
                ketPoli = "Rawat Jalan";
            }
            if ("rawat_inap".equalsIgnoreCase(pelayananData.getTipePelayanan())) {
                kode = "JRI";
                ketPoli = "Rawat Inap";
            }

            // tambahkan jumlah tindakan juga untuk debit piutang
            jumlah = jumlah.add(jumlahTindakan);

            // create invoice nummber
            invoice = billingSystemBo.createInvoiceNumber(kode, branchId);
            if ("JRJ".equalsIgnoreCase(kode)) {

                // create list map piutang
                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));

                // debit piutang pasien
                hsCriteria.put("piutang_pasien_non_bpjs", mapPiutang);

                // kredit jumlah tindakan
                hsCriteria.put("pendapatan_rawat_jalan_non_bpjs", jumlahTindakan);

                if ("Y".equalsIgnoreCase(isResep)) {
                    transId = "05";
                } else {
                    transId = "04";
                }

            }
            if ("JRI".equalsIgnoreCase(kode)) {

                // create map piutang
                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));

                // debit piutang pasien
                hsCriteria.put("piutang_pasien_non_bpjs", mapPiutang);

                // kredit jumlah tindakan
                hsCriteria.put("pendapatan_rawat_inap_non_bpjs", jumlahTindakan);
                transId = "07";
            }

            String catatan = "Closing Pasien " + ketPoli + " Umum " + ketResep + " Piutang No Pasien " + idPasien;

            try {
                billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
                response.setStatus("success");
                response.setMsg("[Berhasil]");
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error, ", e);
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error, " + e);
                return response;
            }
        }

        response.setInvoice(invoice);
        return response;
    }

    public String pembelianObat() {
        logger.info("[TransaksiObatAction.pembelianObat] START process >>>");
        String id = getId();
        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        List<TransaksiObatDetail> pembelianObatList = new ArrayList<>();
        if (!"".equalsIgnoreCase(id) && id != null) {
            try {
                pembelianObatList = transaksiObatBoProxy.getListPembelianObat(id);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.pembelianObat] ERROR when search list obat, ", e);
                addActionError("[TransaksiObatAction.pembelianObat] ERROR when search list obat, " + e.getMessage());
            }
            BigInteger hitungTotalPembelian = hitungTotalBayar(pembelianObatList);
            transaksiObatDetail.setIdApprovalObat(id);
            transaksiObatDetail.setTotalBayar(hitungTotalPembelian);

        } else {
            transaksiObatDetail = new TransaksiObatDetail();
        }
        setTransaksiObatDetail(transaksiObatDetail);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultResep");
        session.setAttribute("listOfResultObat", pembelianObatList);
        logger.info("[TransaksiObatAction.pembelianObat] END process >>>");
        return "init_pembelian_obat";
    }

    public String printStrukTransaksiObat() {
        logger.info("[TransaksiObatAction.printStrukTransaksiObat] START process >>>");

        String id = getId();

        logger.info("[TransaksiObatAction.printStrukTransaksiObat] END process <<<");
        return "print_struk";
    }

    public List<ObatPoli> listObatPoliEntityByIdPabrik(String idPabrik) {
        logger.info("[TransaksiObatAction.listObatPoliEntityByIdPabrik] START process >>>");
        List<ObatPoli> obatPoliList = new ArrayList<>();

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdPabrik(idPabrik);
        obatPoli.setBranchId(CommonUtil.userBranchLogin());
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        obatPoli.setExp("Y");
        obatPoli.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        if (idPabrik != null && !"".equalsIgnoreCase(idPabrik)) {
            try {
                obatPoliList = obatPoliBo.getObatPoliByCriteria(obatPoli);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.listObatPoliEntityByIdPabrik] ERROR when get data list obat, ", e);
                addActionError("[TransaksiObatAction.listObatPoliEntityByIdPabrik] ERROR when get data list obat, " + e.getMessage());
            }
            logger.info("[TransaksiObatAction.listObatPoliEntityByIdPabrik] END process <<<");
            return obatPoliList;
        } else {
            return null;
        }
    }

    public List<ObatPoli> listObatPoliEntityByIdObat(String idObat) {
        logger.info("[TransaksiObatAction.listObatPoliEntityByIdObat] START process >>>");
        List<ObatPoli> obatPoliList = new ArrayList<>();

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdObat(idObat);
        obatPoli.setBranchId(CommonUtil.userBranchLogin());
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        obatPoli.setExp("Y");
        obatPoli.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        if (idObat != null && !"".equalsIgnoreCase(idObat)) {
            try {
                obatPoliList = obatPoliBo.getObatPoliByCriteria(obatPoli);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.listObatPoliEntityByIdObat] ERROR when get data list obat, ", e);
                addActionError("[TransaksiObatAction.listObatPoliEntityByIdObat] ERROR when get data list obat, " + e.getMessage());
            }
            logger.info("[TransaksiObatAction.listObatPoliEntityByIdObat] END process <<<");
            return obatPoliList;
        } else {
            return null;
        }
    }

    public CheckObatResponse saveListObatPembelian(String jsonString, String idApprove) throws JSONException {
        logger.info("[TransaksiObatAction.saveListObatPembelian] START process >>>");
        CheckObatResponse response = new CheckObatResponse();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

        List<TransaksiObatDetail> batchList = new ArrayList<>();
        TransaksiObatDetail batchEntity;

        if (jsonString != null && !"".equalsIgnoreCase(jsonString)) {
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                batchEntity = new TransaksiObatDetail();
                JSONObject obj = json.getJSONObject(i);

                if (!"".equalsIgnoreCase(obj.getString("Qty Approve"))) {
                    batchEntity.setIdBarang(obj.getString("ID Barang"));
                    batchEntity.setIdObat(obj.getString("ID Obat"));
                    batchEntity.setExpDate(Date.valueOf(obj.getString("Expired Date")));
                    batchEntity.setQtyApprove(new BigInteger(obj.getString("Qty Approve")));
                    batchEntity.setJenisSatuan(obj.getString("Jenis Satuan"));
                    batchEntity.setFlag("Y");
                    batchEntity.setAction("C");
                    batchEntity.setLastUpdate(time);
                    batchEntity.setLastUpdateWho(userLogin);
                    batchEntity.setCreatedDate(time);
                    batchEntity.setCreatedWho(userLogin);
                    batchList.add(batchEntity);
                }
            }
        }

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setBranchId(CommonUtil.userBranchLogin());
        transaksiObatDetail.setCreatedDate(time);
        transaksiObatDetail.setCreatedWho(userLogin);
        transaksiObatDetail.setLastUpdate(time);
        transaksiObatDetail.setLastUpdateWho(userLogin);
        transaksiObatDetail.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        transaksiObatDetail.setIdApprovalObat(idApprove);

        try {
            response = transaksiObatBo.saveListObatPembelian(transaksiObatDetail, batchList);
        } catch (GeneralBOException e) {
            logger.error("[TransaksiObatAction.saveListObatPembelian] ERROR when save data list obat, ", e);
            addActionError("[TransaksiObatAction.saveListObatPembelian] ERROR when save data list obat, " + e.getMessage());
        }

        logger.info("[TransaksiObatAction.saveListObatPembelian] END process >>>");
        return response;
    }

    public String pembayaranObatBaru() {

        logger.info("[TransaksiObatAction.pembayaranObatBaru] START >>>>>>>");

        String idPoli = CommonUtil.userPelayananIdLogin();
        String userLogin = CommonUtil.userLogin();
        String branchId = CommonUtil.userBranchLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        TransaksiObatDetail transaksiObatDetail = getTransaksiObatDetail();
        transaksiObatDetail.setLastUpdate(time);
        transaksiObatDetail.setLastUpdateWho(userLogin);
        transaksiObatDetail.setBranchId(branchId);
        transaksiObatDetail.setIdPelayanan(idPoli);

        // create jurnal
        JurnalResponse jurnalResponse = createJurnalPembayaranObatbaru(transaksiObatDetail);
        if ("error".equalsIgnoreCase(jurnalResponse.getStatus())) {
            logger.error(jurnalResponse.getMsg());
            addActionError(jurnalResponse.getMsg());
            return "init_pembelian_obat";
        } else {
            transaksiObatDetail.setNoJurnal(jurnalResponse.getNoJurnal());
        }

        try {
            transaksiObatBoProxy.pembayaranObatBaru(transaksiObatDetail);
        } catch (GeneralBOException e) {
            logger.error("[TransaksiObatAction.pembayaranObatBaru] ERROR error when save pembayaran. ", e);
            addActionError("[TransaksiObatAction.pembayaranObatBaru] ERROR error when save pembayaran. " + e.getMessage());
            return "init_pembelian_obat";
        }

        logger.info("[TransaksiObatAction.pembayaranObatBaru] END <<<<<<<");
        return "search";
    }

    private JurnalResponse createJurnalPembayaranObatbaru(TransaksiObatDetail trans) {

        String branchId = CommonUtil.userBranchLogin();
        String pelayananId = CommonUtil.userPelayananIdLogin();

        BigDecimal pendapatan = new BigDecimal(trans.getTotalBayar().add(trans.getPpnBayar()).toString());
        BigDecimal ppn = new BigDecimal(trans.getPpnBayar().toString());

        JurnalResponse jurnalResponse = new JurnalResponse();

        String divisiId = "";
        String masterId = "01.000";
        ImSimrsPelayananEntity pelayananEntity = pelayananBoProxy.getPelayananById(pelayananId);
        if (pelayananEntity != null){

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
            ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());

            if (position != null){
                divisiId = position.getKodering();
            }

        } else {
            jurnalResponse.setStatus("error");
            jurnalResponse.setMsg("[TransaksiObatAction.createJurnalPembayaranObatbaru] ERROR. tidak dapat divisi_id. ");
            return jurnalResponse;
        }

        Map mapPPN = new HashMap();
        mapPPN.put("bukti", billingSystemBoProxy.createInvoiceNumber("JPD", branchId));
        mapPPN.put("nilai", ppn);

        Map mapKas = new HashMap();
        mapKas.put("metode_bayar", "tunai");
        mapKas.put("nilai", pendapatan);

        // create jurnal
        Map hsCriteria = new HashMap();
        hsCriteria.put("metode_bayar", "tunai");
        hsCriteria.put("kas",  mapKas);

        Map mapPendapatan = new HashMap();
        mapPendapatan.put("nilai", new BigDecimal(trans.getTotalBayar()));
        mapPendapatan.put("master_id", masterId);
        mapPendapatan.put("divisi_id", divisiId);

        hsCriteria.put("pendapatan_obat_umum", mapPendapatan);
        hsCriteria.put("ppn_keluaran", mapPPN);

        String noJurnal = "";
        try {
            noJurnal = billingSystemBoProxy.createJurnal("29", hsCriteria, branchId, "Penjualan Obat Apotik Langsung " + branchId, "Y");
            jurnalResponse.setStatus("success");
            jurnalResponse.setNoJurnal(noJurnal);
        } catch (GeneralBOException e) {
            jurnalResponse.setStatus("error");
            jurnalResponse.setMsg("[TransaksiObatAction.createJurnalPembayaranObatbaru] ERROR. " + e);
            return jurnalResponse;
        }

        return jurnalResponse;
    }

    public String printPembelianObat() {

        String id = getId();
        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        List<TransaksiObatDetail> pembelianObatList = new ArrayList<>();
        List<RiwayatTransaksiObat> riwayatList = new ArrayList<>();

        if (!"".equalsIgnoreCase(id) && id != null) {

            try {
                pembelianObatList = transaksiObatBoProxy.getListRiwayatPembelianObat(id);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.pembelianObat] ERROR when search list obat, ", e);
                addActionError("[TransaksiObatAction.pembelianObat] ERROR when search list obat, " + e.getMessage());
            }

            BigInteger hitungTotalPembelian = hitungTotalBayar(pembelianObatList);
            transaksiObatDetail.setIdApprovalObat(id);
            transaksiObatDetail.setTotalBayar(hitungTotalPembelian);

            List<MtSimrsRiwayatPembelianObat> pembelianObats = new ArrayList<>();
            try {
                pembelianObats = transaksiObatBoProxy.getRiwayatPembelianObat(id);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatAction.pembelianObat] ERROR when search riwayat transaksi obat, ", e);
                addActionError("[TransaksiObatAction.pembelianObat] ERROR when search riwayat transaksi obat, " + e.getMessage());
            }

            MtSimrsRiwayatPembelianObat riwayatPembelianObat = new MtSimrsRiwayatPembelianObat();
            if (pembelianObats.size() > 0) {
                riwayatPembelianObat = pembelianObats.get(0);
                if (riwayatPembelianObat != null) {

                    reportParams.put("permintaanId", id);
                    reportParams.put("totalBayar", riwayatPembelianObat.getTotalBayar());
                    reportParams.put("nominal", riwayatPembelianObat.getNominal());
                    reportParams.put("kembalian", riwayatPembelianObat.getKembalian());
                    reportParams.put("totalDibayar", riwayatPembelianObat.getTotalDibayar());
                }
            }

            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(pembelianObatList);
            String branch = CommonUtil.userBranchLogin();
            String logo = "";

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

            reportParams.put("logo", logo);
            reportParams.put("itemDataSource", itemData);

        }

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ObatPoliAction.printReturePermintaanObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_pembelian_obat";
    }

    public String printStrukResepPasien() {

        String idResep = getIdResep();
        String id = getId();
        String idApprove = getIdApprove();
        String jk = "";

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        List<TransaksiObatDetail> pembelianObatList = new ArrayList<>();
        List<RiwayatTransaksiObat> riwayatList = new ArrayList<>();
        HeaderCheckup checkup = new HeaderCheckup();
        PermintaanResep permintaanResep = new PermintaanResep();

        if (!"".equalsIgnoreCase(idApprove) && idApprove != null) {

            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data detail pasien " + e.getMessage());
            }

            if (checkup != null) {

                try {
                    permintaanResep = checkupDetailBoProxy.getDataDokter(idResep);
                }catch (HibernateException e){
                    logger.error("Found Error "+e.getMessage());
                }

                try {
                    pembelianObatList = transaksiObatBoProxy.listObatResepApprove(idApprove);
                } catch (GeneralBOException e) {
                    logger.error("[TransaksiObatAction.pembelianObat] ERROR when search list obat, ", e);
                    addActionError("[TransaksiObatAction.pembelianObat] ERROR when search list obat, " + e.getMessage());
                }

                List<MtSimrsRiwayatPembelianObat> pembelianObats = new ArrayList<>();

                try {
                    pembelianObats = transaksiObatBoProxy.getRiwayatPembelianObat(idApprove);
                } catch (HibernateException e) {
                    logger.error("[TransaksiObatAction.pembelianObat] ERROR when search riwayat transaksi obat, ", e);
                    addActionError("[TransaksiObatAction.pembelianObat] ERROR when search riwayat transaksi obat, " + e.getMessage());
                }

                MtSimrsRiwayatPembelianObat riwayatPembelianObat = new MtSimrsRiwayatPembelianObat();
                if (pembelianObats.size() > 0) {
                    riwayatPembelianObat = pembelianObats.get(0);
                    if (riwayatPembelianObat != null) {

                        reportParams.put("permintaanId", idResep);
                        reportParams.put("totalBayar", riwayatPembelianObat.getTotalBayar());
                        reportParams.put("nominal", riwayatPembelianObat.getNominal());
                        reportParams.put("kembalian", riwayatPembelianObat.getKembalian());
                        reportParams.put("totalDibayar", riwayatPembelianObat.getTotalDibayar());
                    }
                }

                JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(pembelianObatList);

                String branch = CommonUtil.userBranchLogin();
                Branch branches = new Branch();

                try {
                    branches = branchBoProxy.getBranchById(branch, "Y");
                } catch (GeneralBOException e) {
                    logger.error("Found Error when searhc branch logo");
                }

                String logo = "";

                if (branches != null) {
                    logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
                }

                reportParams.put("logo", logo);
                reportParams.put("unit", CommonUtil.userBranchNameLogin());
                reportParams.put("area", CommonUtil.userAreaName());
                reportParams.put("itemDataSource", itemData);

            }

            reportParams.put("dokter", permintaanResep.getNamaDokter());
            reportParams.put("ttdDokter", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_DOKTER+permintaanResep.getTtdDokter());
            reportParams.put("resepId", idResep);
            reportParams.put("petugas", permintaanResep.getNamaApoteker());
            reportParams.put("ttdPasien", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_PASIEN+permintaanResep.getTtdPasien());
            reportParams.put("ttdApoteker", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_APOTEKER+permintaanResep.getTtdApoteker());
            reportParams.put("idPasien", checkup.getIdPasien());
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

        }

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_struk_resep_pasien";
    }

    public CheckResponse saveTtdPasien(String ttdPasien, String idResep, String ttdApoteker) throws IOException {
        logger.info("base64 "+ttdPasien);
        CheckResponse response = new CheckResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        BASE64Decoder decoder = new BASE64Decoder();

        byte[] decodedBytes1 = decoder.decodeBuffer(ttdPasien);
        byte[] decodedBytes2 = decoder.decodeBuffer(ttdApoteker);

        logger.info("Decoded upload data : " + decodedBytes1.length);
        String fileName1 = "ttd_pasien-"+idResep+"-"+dateFormater("MM")+dateFormater("yy")+".png";
        String fileName2 = "ttd_apoteker-"+idResep+"-"+dateFormater("MM")+dateFormater("yy")+".png";
        String uploadFile1 = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_PASIEN+fileName1;
        String uploadFile2 = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_PASIEN+fileName2;
        logger.info("File save path : " + uploadFile1);
        BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(decodedBytes1));
        BufferedImage image2 = ImageIO.read(new ByteArrayInputStream(decodedBytes2));

        if (image1 == null) {
            logger.error("Buffered Image is null");
            response.setStatus("error");
            return response;
        }else{
            File f1 = new File(uploadFile1);
            File f2 = new File(uploadFile2);
            // write the image
            ImageIO.write(image1, "png", f1);
            ImageIO.write(image2, "png", f2);
            response.setStatus("success");
            response = transaksiObatBo.setTtdPasien(idResep, fileName1, fileName2);
        }

        return response;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
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