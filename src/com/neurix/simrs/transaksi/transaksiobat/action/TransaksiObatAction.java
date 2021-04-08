package com.neurix.simrs.transaksi.transaksiobat.action;

import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
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
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.reseponline.bo.ResepOnlineBo;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.*;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import io.agora.recording.common.Common;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.directwebremoting.dwrp.Batch;
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
import java.util.*;

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
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");

        String id = getId();
        String idPermintaan = getIdPermintaan();
        HeaderCheckup checkup = new HeaderCheckup();

        // mencari data telemedic
        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = null;
        ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(idPermintaan);
        if (permintaanResepEntity != null) {
            ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(permintaanResepEntity.getIdTransaksiOnline());
            if (pembayaranOnlineEntity != null) {
                antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
            }
        }


        String jk = "";

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found error when detail pasien " + e.getMessage());
        }

        if (checkup != null && checkup.getNoCheckup() != null && !"".equalsIgnoreCase(checkup.getNoCheckup())) {

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
                    jk = "Laki-Laki";
                }
            }
            resep.setJenisKelamin(jk);
            resep.setTempatLahir(checkup.getTempatLahir());
            resep.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
            String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
            resep.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
            resep.setNik(checkup.getNoKtp());
            resep.setIdJenisPeriksa(checkup.getIdJenisPeriksaPasien());
            resep.setUrlKtp(checkup.getUrlKtp());
            resep.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
            resep.setIdPermintaanResep(idPermintaan);
            setPermintaanResep(resep);

        } else {

            if (antrianTelemedicEntity != null && antrianTelemedicEntity.getIdPasien() != null) {

                Pasien pasien = new Pasien();
                pasien.setIdPasien(antrianTelemedicEntity.getIdPasien());

                List<Pasien> pasienList = pasienBo.getByCriteria(pasien);
                if (pasienList.size() > 0) {
                    Pasien pasenData = pasienList.get(0);
                    PermintaanResep resep = new PermintaanResep();
                    resep.setIdPasien(pasenData.getIdPasien());
                    resep.setNamaPasien(pasenData.getNama());
                    resep.setAlamat(pasenData.getJalan());
                    resep.setDesa(pasenData.getDesa());
                    resep.setKecamatan(pasenData.getKecamatan());
                    resep.setKota(pasenData.getKota());
                    resep.setProvinsi(pasenData.getProvinsi());
                    resep.setIdPelayanan(antrianTelemedicEntity.getIdPelayanan());
                    if (pasenData.getJenisKelamin() != null) {
                        if ("P".equalsIgnoreCase(pasenData.getJenisKelamin())) {
                            jk = "Perempuan";
                        } else {
                            jk = "Laki-Laki";
                        }
                    }
                    resep.setJenisKelamin(jk);
                    resep.setTempatLahir(pasenData.getTempatLahir());
                    resep.setTglLahir(pasenData.getTglLahir() == null ? null : pasenData.getTglLahir());
                    if (!"".equalsIgnoreCase(resep.getTglLahir())) {
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(Date.valueOf(resep.getTglLahir()));
                        resep.setTempatTglLahir(pasenData.getTempatLahir() + ", " + formatDate);
                    }
                    resep.setNik(pasenData.getNoKtp());
                    resep.setJenisPeriksaPasien(antrianTelemedicEntity.getIdJenisPeriksaPasien());
                    resep.setUrlKtp(pasenData.getUrlKtp());
                    resep.setIdPermintaanResep(idPermintaan);
                    resep.setFlagEresep(antrianTelemedicEntity.getFlagEresep());
                    setPermintaanResep(resep);
                }
            } else {
                setPermintaanResep(new PermintaanResep());
            }
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

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdPermintaanResep(idPermintaan);
        transaksiObatDetail.setIsOrder("Y");
        transaksiObatDetail.setJenisPeriksaPasien(checkup.getIdJenisPeriksaPasien());
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

    public String searchResepReture() {
        logger.info("[TransaksiObatAction.searchResep] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");

        String id = getId();
        String idPermintaan = getIdPermintaan();
        HeaderCheckup checkup = new HeaderCheckup();

        // mencari data telemedic
        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = null;
        ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(idPermintaan);
        if (permintaanResepEntity != null) {
            ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(permintaanResepEntity.getIdTransaksiOnline());
            if (pembayaranOnlineEntity != null) {
                antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
            }
        }

        String jk = "";

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found error when detail pasien " + e.getMessage());
        }

        if (checkup != null && checkup.getNoCheckup() != null && !"".equalsIgnoreCase(checkup.getNoCheckup())) {

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
            resep.setIdPermintaanResep(idPermintaan);
            setPermintaanResep(resep);

        } else {

            if (antrianTelemedicEntity != null && antrianTelemedicEntity.getIdPasien() != null) {

                Pasien pasien = new Pasien();
                pasien.setIdPasien(antrianTelemedicEntity.getIdPasien());

                List<Pasien> pasienList = pasienBo.getByCriteria(pasien);
                if (pasienList.size() > 0) {
                    Pasien pasenData = pasienList.get(0);
                    PermintaanResep resep = new PermintaanResep();
                    resep.setIdPasien(pasenData.getIdPasien());
                    resep.setNamaPasien(pasenData.getNama());
                    resep.setAlamat(pasenData.getJalan());
                    resep.setDesa(pasenData.getDesa());
                    resep.setKecamatan(pasenData.getKecamatan());
                    resep.setKota(pasenData.getKota());
                    resep.setProvinsi(pasenData.getProvinsi());
                    resep.setIdPelayanan(antrianTelemedicEntity.getIdPelayanan());
//                    resep.setNamaPelayanan(pasenData.getNamaPelayanan());
                    if (pasenData.getJenisKelamin() != null) {
                        if ("P".equalsIgnoreCase(pasenData.getJenisKelamin())) {
                            jk = "Perempuan";
                        } else {
                            jk = "laki-Laki";
                        }
                    }
                    resep.setJenisKelamin(jk);
                    resep.setTempatLahir(pasenData.getTempatLahir());
                    resep.setTglLahir(pasenData.getTglLahir() == null ? null : pasenData.getTglLahir());
                    if (!"".equalsIgnoreCase(resep.getTglLahir())) {
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(Date.valueOf(resep.getTglLahir()));
                        resep.setTempatTglLahir(pasenData.getTempatLahir() + ", " + formatDate);
                    }
                    resep.setNik(pasenData.getNoKtp());
                    resep.setJenisPeriksaPasien(antrianTelemedicEntity.getIdJenisPeriksaPasien());
                    resep.setUrlKtp(pasenData.getUrlKtp());
                    resep.setIdPermintaanResep(idPermintaan);
                    resep.setFlagEresep(antrianTelemedicEntity.getFlagEresep());
                    setPermintaanResep(resep);
                }
            } else {
                setPermintaanResep(new PermintaanResep());
            }
        }

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdPermintaanResep(idPermintaan);
        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if (idPermintaan != null && !"".equalsIgnoreCase(idPermintaan)) {

            try {
                obatDetailList = transaksiObatBoProxy.getListTransaksiObatDetailBatchByIdResepAndIdBarang(idPermintaan, "");
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.searchResepReture] ERROR error when get searh resep. ", e);
                addActionError("[TransaksiObatAction.searchResepReture] ERROR error when get searh resep. " + e.getMessage());
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
            logger.error("[TransaksiObatAction.searchResepReture] ERROR error when get searh resep. ", e);
            addActionError("[TransaksiObatAction.searchResepReture] ERROR error when get searh resep. " + e.getMessage());
        }

        PermintaanResep resep = new PermintaanResep();
        if (!permintaanResepList.isEmpty()) {
            resep = permintaanResepList.get(0);
            if (resep != null) {
                transaksiObatDetail.setIdApprovalObat(resep.getIdApprovalObat());
            }
        }

        setTransaksiObatDetail(transaksiObatDetail);
        session.removeAttribute("listOfResultRetureResep");
        session.setAttribute("listOfResultRetureResep", obatDetailList);
        logger.info("[TransaksiObatAction.searchResepReture] END <<<<<<<");
        return "init_reture";
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
                trans.setTotalHarga(trans.getTotalHarga() == null ? new BigInteger(String.valueOf(0)) : trans.getTotalHarga());
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

    public List<ObatPoli> listObatPoliEntity(String idObat, String jenisObat) {
        logger.info("[TransaksiObatAction.initApprovePermintaan] START process >>>");
        List<ObatPoli> obatPoliList = new ArrayList<>();

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdObat(idObat);
        obatPoli.setBranchId(CommonUtil.userBranchLogin());
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        obatPoli.setExp("Y");
        obatPoli.setFlag("Y");

        if ("bpjs".equalsIgnoreCase(jenisObat) || "bpjs_rekanan".equalsIgnoreCase(jenisObat))
            obatPoli.setFlagBpjs("Y");
        else
            obatPoli.setFlagBpjs("N");

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

                if (!"".equalsIgnoreCase(obj.getString("qty_approve"))) {
                    batchEntity.setIdTransaksiObatDetail(idTransaksi);
                    batchEntity.setIdBarang(obj.getString("id_barang"));
                    batchEntity.setExpiredDate(Date.valueOf(obj.getString("expired_date")));
                    batchEntity.setQtyApprove(new BigInteger(obj.getString("qty_approve")));
                    batchEntity.setJenisSatuan(obj.getString("jenis_satuan"));
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

    public CheckObatResponse saveApproveResepObatPoli(String data) {
        logger.info("[TransaksiObatAction.saveVerifikasiResep] START process >>>");
        CheckObatResponse response = new CheckObatResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();
        try {
            if(data != null && !"".equalsIgnoreCase(data)){
                JSONObject object = new JSONObject(data);
                String idApproval = object.getString("id_approve");
                String idDetailCheckup = object.getString("id_detail_checkup");
                String idResep = object.getString("id_resep");
                String jenisPasien = object.getString("jenis_pasien");
                TransaksiObatDetail obatDetail = new TransaksiObatDetail();
                obatDetail.setIdApprovalObat(idApproval);
                obatDetail.setIdPelayanan(CommonUtil.userPelayananIdLogin());
                obatDetail.setBranchId(CommonUtil.userBranchLogin());
                obatDetail.setLastUpdate(time);
                obatDetail.setLastUpdateWho(userLogin);

                try {
                    if(object.getString("keterangan") != null && !"".equalsIgnoreCase(object.getString("keterangan"))){
                        JSONArray json = new JSONArray(object.getString("keterangan"));
                        List<TransaksiObatDetail> obatDetails = new ArrayList<>();
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject obj = json.getJSONObject(i);
                            TransaksiObatDetail detail = new TransaksiObatDetail();
                            detail.setIdObat(obj.getString("id_obat"));
                            detail.setIdRacik(obj.getString("id_racik"));
                            detail.setKeterangan(obj.getString("ket"));
                            obatDetails.add(detail);
                        }
                        if(obatDetails.size() > 0){
                            obatDetail.setObatDetailList(obatDetails);
                        }
                    }
                }catch (Exception e){
                    response.setStatus(ERROR);
                    response.setMessage("Terjadi kesalah saat parse JSON object, "+e.getMessage());
                    return response;
                }

                try {
                    if(object.getString("biaya_tambahan") != null && !"".equalsIgnoreCase(object.getString("biaya_tambahan"))){
                        JSONArray json = new JSONArray(object.getString("biaya_tambahan"));
                        List<ItSimrsRiwayatTindakanEntity> entityArrayList = new ArrayList<>();
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject obj = json.getJSONObject(i);
                            ItSimrsRiwayatTindakanEntity tindakanEntity = new ItSimrsRiwayatTindakanEntity();
                            tindakanEntity.setIdTindakan(obj.getString("id_jenis_biaya"));
                            tindakanEntity.setNamaTindakan(obj.getString("jenis_biaya"));
                            tindakanEntity.setTotalTarif(new BigDecimal(obj.getString("total")));
                            tindakanEntity.setIdDetailCheckup(idDetailCheckup);
                            tindakanEntity.setCreatedDate(time);
                            tindakanEntity.setCreatedWho(userLogin);
                            tindakanEntity.setLastUpdate(time);
                            tindakanEntity.setLastUpdateWho(userLogin);
                            tindakanEntity.setTanggalTindakan(time);
                            tindakanEntity.setFlag("Y");
                            tindakanEntity.setAction("C");
                            tindakanEntity.setKeterangan("tindakan");
                            tindakanEntity.setJenisPasien(jenisPasien);
                            entityArrayList.add(tindakanEntity);
                        }
                        if(entityArrayList.size() > 0){
                            obatDetail.setBiayaTambahanList(entityArrayList);
                        }
                    }
                }catch (Exception e){
                    response.setStatus(ERROR);
                    response.setMessage("Terjadi kesalah saat parse JSON object, "+e.getMessage());
                    return response;
                }

                try {
                    // create jurnal Pengeluaran Obat Apotik
                    JurnalResponse jurnalResponse = createJurnalPengeluaranObatApotik(idApproval);
                    if ("error".equalsIgnoreCase(jurnalResponse.getStatus())) {
                        return response;
                    } else {
                        obatDetail.setNoJurnal(jurnalResponse.getNoJurnal());
                    }

                    response = transaksiObatBo.saveApproveResepPoli(obatDetail);
                    Boolean obatKronis = false;

                    try {
                        obatKronis = transaksiObatBo.cekObatKronis(idApproval);
                    } catch (GeneralBOException e) {
                        logger.error("found Error when search obat kronis ");
                    }

                    TransaksiObatDetail tarifResep = new TransaksiObatDetail();

                    try {
                        tarifResep = transaksiObatBo.getTarifApproveResep(idApproval);
                    } catch (GeneralBOException e) {
                        logger.error("found error when search tarif resep");
                    }

                    if (tarifResep.getTotalHarga() != null) {

                        if (obatKronis) {

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
                                JurnalResponse jurnalClosingResponse = closingJurnalNonTunai(tarifResep.getIdDetailCheckup(), tarifResep.getIdPelayanan(), tarifResep.getIdPasien());
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
                                } catch (GeneralBOException e) {
                                    logger.error("Found Error " + e);
                                }

                            } catch (GeneralBOException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                            }

                        } else {

                            // jika permintaan berasal dari telemedic
                            ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(tarifResep.getIdPermintaanResep());
                            if (permintaanResepEntity != null && permintaanResepEntity.getIdTransaksiOnline() != null && tarifResep.getIdDetailCheckup() != null) {

                                ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(permintaanResepEntity.getIdTransaksiOnline());
                                if (pembayaranOnlineEntity != null) {
                                    ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
                                    if (antrianTelemedicEntity != null) {

                                        String idRiwayatTindakan = "";
                                        if (!"".equalsIgnoreCase(tarifResep.getIdDetailCheckup()) && !"".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())) {
                                            CheckResponse checkResponse = saveApproveAllTindakanRawatJalan(tarifResep.getIdDetailCheckup(), antrianTelemedicEntity.getIdJenisPeriksaPasien());
                                            if ("success".equalsIgnoreCase(checkResponse.getStatus())) {

                                                RiwayatTindakan tindakan = new RiwayatTindakan();
                                                tindakan.setIdDetailCheckup(tarifResep.getIdDetailCheckup());
                                                if ("konsultasi".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())) {
                                                    tindakan.setKeterangan("tindakan");
                                                } else {
                                                    tindakan.setKeterangan("resep");
                                                }
                                                List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(tindakan);
                                                if (riwayatTindakanEntities.size() > 0) {
                                                    ItSimrsRiwayatTindakanEntity tindakanEntity = riwayatTindakanEntities.get(0);
                                                    if (tindakanEntity != null) {
                                                        idRiwayatTindakan = tindakanEntity.getIdRiwayatTindakan();
                                                    }
                                                }
                                            }
                                        }

                                        pembayaranOnlineEntity.setIdRiwayatTindakan(idRiwayatTindakan);
                                        pembayaranOnlineEntity.setLastUpdate(time);
                                        pembayaranOnlineEntity.setLastUpdateWho(userLogin);
                                        try {
                                            verifikatorPembayaranBo.saveEdit(pembayaranOnlineEntity);
                                            response.setStatus("success");

                                        } catch (GeneralBOException e) {
                                            logger.error("[TransaksiObatAction.approveTransaksi] ERROR. ", e);
                                            response.setStatus("error");
                                            response.setMessage("[TransaksiObatAction.approveTransaksi] ERROR. " + e);
                                            return response;
                                        }

                                        if (!"Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagEresep()) && "umum".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())) {
                                            // create jurnal for telemedic
                                            jurnalResponse = new JurnalResponse();
                                            jurnalResponse = closingJurnalNonTunaiTelemedic(tarifResep.getIdDetailCheckup(), permintaanResepEntity.getIdTransaksiOnline(), antrianTelemedicEntity.getIdPelayanan(), antrianTelemedicEntity.getIdPasien(), "Y");
                                            if ("success".equalsIgnoreCase(jurnalResponse.getStatus())) {
                                                pembayaranOnlineEntity.setIdRiwayatTindakan(idRiwayatTindakan);
                                                pembayaranOnlineEntity.setLastUpdate(time);
                                                pembayaranOnlineEntity.setLastUpdateWho(userLogin);
                                                try {
                                                    verifikatorPembayaranBo.saveEdit(pembayaranOnlineEntity);
                                                    response.setStatus("success");

                                                } catch (GeneralBOException e) {
                                                    logger.error("[TransaksiObatAction.approveTransaksi] ERROR. ", e);
                                                    response.setStatus("error");
                                                    response.setMessage("[TransaksiObatAction.approveTransaksi] ERROR. " + e);
                                                    return response;
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                            riwayatTindakan.setIdDetailCheckup(tarifResep.getIdDetailCheckup());
                            riwayatTindakan.setIdTindakan(tarifResep.getIdPermintaanResep());
                            riwayatTindakan.setTotalTarif(new BigDecimal(tarifResep.getTotalHarga()));
                            riwayatTindakan.setLastUpdateWho(userLogin);
                            riwayatTindakan.setLastUpdate(time);

                            try {
                                riwayatTindakanBo.saveEdit(riwayatTindakan);
                            } catch (GeneralBOException e) {
                                logger.error("found error " + e);
                            }
                        }
                    }
                } catch (GeneralBOException e) {
                    response.setStatus(ERROR);
                    response.setMessage("[TransaksiObatAction.saveVerifikasiResep] ERROR when save list obat, " + e.getMessage());
                    logger.error("[TransaksiObatAction.saveApproveResepObatPoli] ERROR when save list obat, ", e);
                    return response;
                }
            }else{
                response.setStatus(ERROR);
                response.setMessage("Data object tidak ada...!");
            }
        }catch (Exception e){
            response.setStatus(ERROR);
            response.setMessage(e.getMessage());
            return response;
        }

        logger.info("[TransaksiObatAction.saveVerifikasiResep] END process <<<");
        return response;
    }

    public CheckResponse saveApproveAllTindakanRawatJalan(String idDetailCheckup, String jenisPasien) {

        logger.info("[TransaksiObatAction.saveApproveAllTindakanRawatJalan] START process >>>");
        CheckResponse response = new CheckResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
            RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
            headerDetailCheckup.setLastUpdate(updateTime);
            headerDetailCheckup.setLastUpdateWho(user);

            try {
                response = checkupDetailBo.saveApproveAllTindakanRawatJalan(headerDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.saveApproveAllTindakanRawatJalan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

            if ("success".equalsIgnoreCase(response.getStatus())) {
                saveAddToRiwayatTindakan(idDetailCheckup, jenisPasien);
            }

//            if ("asuransi".equalsIgnoreCase(jenisPasien)) {
//
//                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
//                if (detailCheckupEntity != null) {
//                    BigDecimal cover = detailCheckupEntity.getCoverBiaya();
//                    BigDecimal jumlahAllTindakanAsuransi = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, "");
//                    if (jumlahAllTindakanAsuransi.compareTo(cover) == 1) {
//                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
//                        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);
//                        riwayatTindakan.setJenisPasien(jenisPasien);
////                        riwayatTindakan.setNotResep("Y");
//                        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
//
//                        if (riwayatTindakanEntities.size() > 0) {
//                            BigDecimal jumlahBiaya = new BigDecimal(0);
//                            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {
//
//                                jumlahBiaya = jumlahBiaya.add(riwayatTindakanEntity.getTotalTarif());
//
//                                // jika jumlahBiaya Lebih besar dari pada yg di cover maka;
//                                // tindakan dialihkan ke umum;
//                                if (jumlahBiaya.compareTo(cover) == 1) {
//
//                                    // newTarif = cover - (total tarif melebihi - tarif tindakan)
//                                    BigDecimal newTarif = cover.subtract(jumlahBiaya.subtract(riwayatTindakanEntity.getTotalTarif()));
//
//                                    // jika newTarif lebih besar dari 0
//                                    // maka update tindakan dengan tarif tindakan sisa (newTarif)
//                                    // membuat tindakan umum baru dari tindakan tarif - newTarif
//                                    if (newTarif.compareTo(BigDecimal.ZERO) == 1) {
//
//                                        BigDecimal tarifAwal = riwayatTindakanEntity.getTotalTarif();
//
//                                        riwayatTindakanEntity.setTotalTarif(newTarif);
//                                        riwayatTindakanEntity.setAction("U");
//                                        riwayatTindakanEntity.setLastUpdate(updateTime);
//                                        riwayatTindakanEntity.setLastUpdateWho(user);
//
//                                        try {
//                                            riwayatTindakanBo.updateByEntity(riwayatTindakanEntity);
//                                        } catch (GeneralBOException e) {
//                                            logger.error("[TransaksiObatAction.saveApproveAllTindakanRawatJalan] ERROR. ", e);
//                                            response.setStatus("error");
//                                            response.setMessage("[TransaksiObatAction.saveApproveAllTindakanRawatJalan] ERROR. " + e);
//                                        }
//
//                                        // sisa tarif masuk ke umum adalah tindakan asli / tarifAwal - newTarif
//                                        BigDecimal newTarifTindakanUmum = tarifAwal.subtract(newTarif);
//
//                                        RiwayatTindakan riwayatTindakanEntityNew = new RiwayatTindakan();
//                                        riwayatTindakanEntityNew.setIdTindakan(riwayatTindakanEntity.getIdTindakan());
//                                        riwayatTindakanEntityNew.setNamaTindakan(riwayatTindakanEntity.getNamaTindakan());
//                                        riwayatTindakanEntityNew.setKeterangan(riwayatTindakanEntity.getKeterangan());
//                                        riwayatTindakanEntityNew.setJenisPasien("umum");
//                                        riwayatTindakanEntityNew.setTotalTarif(newTarifTindakanUmum);
//                                        riwayatTindakanEntityNew.setTanggalTindakan(riwayatTindakanEntity.getTanggalTindakan());
//                                        riwayatTindakanEntityNew.setIdDetailCheckup(riwayatTindakanEntity.getIdDetailCheckup());
//                                        riwayatTindakanEntityNew.setKategoriTindakanBpjs(riwayatTindakanEntity.getKategoriTindakanBpjs());
//                                        riwayatTindakanEntityNew.setApproveBpjsFlag(riwayatTindakanEntity.getApproveBpjsFlag());
//                                        riwayatTindakanEntityNew.setFlag("Y");
//                                        riwayatTindakanEntityNew.setAction("C");
//                                        riwayatTindakanEntityNew.setCreatedDate(updateTime);
//                                        riwayatTindakanEntityNew.setCreatedWho(user);
//                                        riwayatTindakanEntityNew.setLastUpdate(updateTime);
//                                        riwayatTindakanEntityNew.setLastUpdateWho(user);
//
//                                        try {
//                                            riwayatTindakanBo.saveAdd(riwayatTindakanEntityNew);
//                                        } catch (GeneralBOException e) {
//                                            logger.error("[TransaksiObatAction.saveApproveAllTindakanRawatJalan] ERROR. ", e);
//                                            response.setStatus("error");
//                                            response.setMessage("[TransaksiObatAction.saveApproveAllTindakanRawatJalan] ERROR. " + e);
//                                        }
//                                    } else {
//
//                                        // jika tindakan newTarif == tindakan tarif || newTarif > tindakan tarif
//                                        // maka hanya mengupdate jenis pasien menjadi umum
//
//                                        riwayatTindakanEntity.setJenisPasien("umum");
//                                        riwayatTindakanEntity.setAction("U");
//                                        riwayatTindakanEntity.setLastUpdate(updateTime);
//                                        riwayatTindakanEntity.setLastUpdateWho(user);
//
//                                        try {
//                                            riwayatTindakanBo.updateByEntity(riwayatTindakanEntity);
//                                        } catch (GeneralBOException e) {
//                                            logger.error("[TransaksiObatAction.saveApproveAllTindakanRawatJalan] ERROR. ", e);
//                                            response.setStatus("error");
//                                            response.setMessage("[TransaksiObatAction.saveApproveAllTindakanRawatJalan] ERROR. " + e);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }

        logger.info("[TransaksiObatAction.saveApproveAllTindakanRawatJalan] END process >>>");
        return response;
    }

    public void saveAddToRiwayatTindakan(String idDetail, String jenisPasien) {
        logger.info("[TransaksiObatAction.saveAddToRiwayatTindakan] START process >>>");
        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
            RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
            PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
            RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

            String jenPasien = "";
            if ("ptpn".equalsIgnoreCase(jenisPasien)) {
                jenPasien = "bpjs";
            } else {
                jenPasien = jenisPasien;
            }

            String idPaket = "";
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetail);
            if (detailCheckupEntity != null) {
                idPaket = detailCheckupEntity.getIdPaket();
            }

            List<TindakanRawat> listTindakan = new ArrayList<>();
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetail);
            tindakanRawat.setApproveFlag("Y");

            try {
                listTindakan = tindakanRawatBo.getByCriteria(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (listTindakan.size() > 0) {
                for (TindakanRawat entity : listTindakan) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdTindakanRawat());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {
                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdTindakanRawat());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan(entity.getNamaTindakan());

                        if (!"".equalsIgnoreCase(idPaket)) {

                            // mengambil berdasarkan idPaket dan idTindakan;
                            MtSimrsItemPaketEntity itemPaketEntity = riwayatTindakanBo.getItemPaketEntity(idPaket, entity.getIdTindakan());
                            if (itemPaketEntity != null) {

                                // jika ada paket;
                                riwayatTindakan.setTotalTarif(new BigDecimal(itemPaketEntity.getHarga()));
                            } else {

                                // jika tidak ada item paket namun golongan paket, maka tarif tindakan asli yang dipakai
                                riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                            }

                        } else {

                            // jika bukan paket maka tarif tindakan asli
                            riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                        }

                        riwayatTindakan.setKeterangan("tindakan");
                        riwayatTindakan.setJenisPasien(jenPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                        try {
                            riwayatTindakanBo.saveAdd(riwayatTindakan);
                        } catch (GeneralBOException e) {
                            logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                        }
                    }
                }
            }

            List<PeriksaLab> periksaLabList = new ArrayList<>();
            PeriksaLab periksaLab = new PeriksaLab();
            periksaLab.setIdDetailCheckup(idDetail);
            periksaLab.setApproveFlag("Y");

            try {
                periksaLabList = periksaLabBo.getByCriteria(periksaLab);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
            }

            if (periksaLabList.size() > 0) {
                for (PeriksaLab entity : periksaLabList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPeriksaLab());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        BigDecimal totalTarif = null;

                        try {
                            totalTarif = periksaLabBo.getTarifTotalPemeriksaan(entity.getIdPeriksaLab());
                        } catch (HibernateException e) {
                            logger.error("Found Error " + e.getMessage());
                        }

                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdPeriksaLab());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan("Periksa " + entity.getKategoriLabName() + " " + entity.getLabName());

                        // paket lab
                        if (!"".equalsIgnoreCase(idPaket)) {

                            // mencari berdasarkan id paket dan id lab
                            ItemPaket itemPaket = riwayatTindakanBo.getTarifPaketLab(idPaket, entity.getIdLab());
                            if (itemPaket != null) {

                                // jika terdapat tarif paket maka menggunakan tarif paket
                                riwayatTindakan.setTotalTarif(itemPaket.getTarif());
                            } else {

                                // jika tidak ada tarif paket menggunakan tarif asli
                                riwayatTindakan.setTotalTarif(totalTarif);
                            }
                        } else {

                            // jika bukan paket maka pakai tarif asli
                            riwayatTindakan.setTotalTarif(totalTarif);
                        }

                        riwayatTindakan.setKeterangan(entity.getKategori());
                        riwayatTindakan.setJenisPasien(jenPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());
                        String ktb = "";
                        if("lab".equalsIgnoreCase(entity.getKategori())){
                            ktb = "laboratorium";
                        }else{
                            ktb = "radiologi";
                        }
                        riwayatTindakan.setKategoriTindakanBpjs(ktb);

                        try {
                            riwayatTindakanBo.saveAdd(riwayatTindakan);
                        } catch (GeneralBOException e) {
                            logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                        }
                    }
                }
            }

            List<PermintaanResep> resepList = new ArrayList<>();
            PermintaanResep resep = new PermintaanResep();
            resep.setIdDetailCheckup(idDetail);

            try {
                resepList = permintaanResepBo.getByCriteria(resep);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (resepList.size() > 0) {
                for (PermintaanResep entity : resepList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPermintaanResep());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        TransaksiObatDetail obatDetailList = new TransaksiObatDetail();

                        try {
                            obatDetailList = transaksiObatBo.getTotalHargaResep(entity.getIdPermintaanResep());
                        } catch (HibernateException e) {
                            logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                        }

                        if (obatDetailList.getTotalHarga() != null && !"".equalsIgnoreCase(obatDetailList.getTotalHarga().toString())) {
                            RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                            riwayatTindakan.setIdTindakan(entity.getIdPermintaanResep());
                            riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                            riwayatTindakan.setNamaTindakan("Tarif Resep dengan No. Resep " + entity.getIdPermintaanResep());
                            riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()));
                            riwayatTindakan.setKeterangan("resep");
                            riwayatTindakan.setJenisPasien(obatDetailList.getJenisResep());
                            riwayatTindakan.setAction("C");
                            riwayatTindakan.setFlag("Y");
                            riwayatTindakan.setCreatedWho(user);
                            riwayatTindakan.setCreatedDate(updateTime);
                            riwayatTindakan.setLastUpdate(updateTime);
                            riwayatTindakan.setLastUpdateWho(user);
                            riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                            try {
                                riwayatTindakanBo.saveAdd(riwayatTindakan);
                            } catch (GeneralBOException e) {
                                logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                            }
                        }
                    }
                }
            }

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(idDetail);
            List<RawatInap> rawatInapList = new ArrayList<>();

            try {
                rawatInapList = rawatInapBo.getByCriteria(rawatInap);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }

            if (rawatInapList.size() > 0) {

                rawatInap = rawatInapList.get(0);

                if (rawatInap != null) {

                    OrderGizi orderGizi = new OrderGizi();
                    orderGizi.setIdRawatInap(rawatInap.getIdRawatInap());
                    orderGizi.setDiterimaFlag("Y");
                    List<OrderGizi> giziList = new ArrayList<>();

                    try {
                        giziList = orderGiziBo.getByCriteria(orderGizi);
                    } catch (GeneralBOException e) {
                        logger.error("Found Error" + e.getMessage());
                    }

                    if (giziList.size() > 0) {

                        for (OrderGizi gizi : giziList) {

                            List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                            RiwayatTindakan tindakan = new RiwayatTindakan();
                            tindakan.setIdTindakan(gizi.getIdOrderGizi());

                            try {
                                riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                            } catch (HibernateException e) {
                                logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                            }

                            if (riwayatTindakanList.isEmpty()) {

                                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                                riwayatTindakan.setIdTindakan(gizi.getIdOrderGizi());
                                riwayatTindakan.setIdDetailCheckup(rawatInap.getIdDetailCheckup());
                                riwayatTindakan.setNamaTindakan("Tarif Gizi dengan No. Gizi " + gizi.getIdOrderGizi());
                                riwayatTindakan.setTotalTarif(gizi.getTarifTotal());
                                riwayatTindakan.setKeterangan("gizi");
                                riwayatTindakan.setJenisPasien(jenPasien);
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setCreatedWho(user);
                                riwayatTindakan.setCreatedDate(updateTime);
                                riwayatTindakan.setLastUpdate(updateTime);
                                riwayatTindakan.setLastUpdateWho(user);
                                riwayatTindakan.setTanggalTindakan(gizi.getCreatedDate());

                                try {
                                    riwayatTindakanBo.saveAdd(riwayatTindakan);
                                } catch (GeneralBOException e) {
                                    logger.error("[TransaksiObatAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("[TransaksiObatAction.saveAddToRiwayatTindakan] END process >>>");
    }


    private JurnalResponse closingJurnalNonTunaiTelemedic(String idDetailCheckup, String idTransaksiOnline, String idPoli, String idPasien, String flagResep) {

        JurnalResponse response = new JurnalResponse();
        String invoice = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");
        MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");

        String kode = "";
        String transId = "";
        String jenisPasien = "Umum ";
        String kodeBank = "1.1.01.02.01";
        String idJenisPeriksaPasien = "";
        String noRekening = CommonConstant.REK_BANK_BRI_TELE;
        BigDecimal biayaCover = new BigDecimal(0);
        BigDecimal tarifTele = new BigDecimal(0);
        BigDecimal nominalUnik = new BigDecimal(0);
        String withResep = "N";

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
        if (detailCheckupEntity != null) {
            idJenisPeriksaPasien = detailCheckupEntity.getIdJenisPeriksaPasien();
        }

        ItSimrsHeaderChekupEntity checkupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
        if (checkupEntity != null) {
            idPasien = checkupEntity.getIdPasien();
        }

        String branchId = "";
        String keterangan = "tindakan";
        if (idTransaksiOnline != null && !"".equalsIgnoreCase(idTransaksiOnline)) {
            ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(idTransaksiOnline);
            if (pembayaranOnlineEntity != null) {
                kodeBank = pembayaranOnlineEntity.getKodeBank();
                tarifTele = pembayaranOnlineEntity.getNominal();

                if ("resep".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())) {
                    keterangan = pembayaranOnlineEntity.getKeterangan();
                }

                ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
                if (antrianTelemedicEntity != null) {
                    branchId = antrianTelemedicEntity.getBranchId();
                    if ("Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagResep())) {
                        withResep = "Y";
                    }
                }
            }
        }

        String masterId = jenisPriksaPasienBo.getJenisPerikasEntityById(detailCheckupEntity.getIdJenisPeriksaPasien()).getMasterId();
        if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)) {

            ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
            if (asuransiEntity != null) {
                masterId = asuransiEntity.getNoMaster();
                jenisPasien = "Asuransi " + asuransiEntity.getNamaAsuransi() + " No. Kartu " + detailCheckupEntity.getNoKartuAsuransi();
            } else {
                logger.error("[VerifikatorPembayaranAction.closingJurnalNonTunaiTelemedic] Error Asuransi tidak ditemukan");
                response.setStatus("error");
                response.setMsg("[VerifikatorPembayaranAction.closingJurnalNonTunaiTelemedic] Error Asuransi tidak ditemukan");
                return response;
            }
        }

        // MAP ALL TINDAKAN BY KETERANGAN
        List<Map> listOfTindakan = new ArrayList<>();
        Map mapTindakan = new HashMap();

        if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)) {
            List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
            if (listOfKeteranganRiwayat.size() > 0) {

                for (String keteranganRiwayat : listOfKeteranganRiwayat) {
                    mapTindakan = new HashMap();
                    mapTindakan.put("master_id", masterId);
                    mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, idJenisPeriksaPasien, keteranganRiwayat));
                    mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, keteranganRiwayat));
                    mapTindakan.put("activity", getAcitivityList(idDetailCheckup, idJenisPeriksaPasien, keteranganRiwayat, kode));
                    listOfTindakan.add(mapTindakan);
                }
            }
        } else {
            //BigDecimal jumlahBiaya = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, keterangan);
            //BigDecimal tarifLain = tarifTele.subtract(jumlahBiaya);
            BigDecimal tarifTindakan = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, keterangan);
            nominalUnik = tarifTele.subtract(tarifTindakan);
            mapTindakan.put("master_id", masterId);
            mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, idJenisPeriksaPasien, keterangan));
            mapTindakan.put("nilai", tarifTindakan);
            mapTindakan.put("activity", getAcitivityList(idDetailCheckup, idJenisPeriksaPasien, keterangan, kode));
            listOfTindakan.add(mapTindakan);
        }

        // pendapatan lain dari nominal unik
        Map mapPendapatanLain = new HashMap();
        mapPendapatanLain.put("nilai", nominalUnik);

        // MENDAPATKAN SEMUA BIAYA RAWAT;
//        BigDecimal jumlah = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "", "");
        BigDecimal ppnObat = new BigDecimal(0);
        Map mapJurnal = new HashMap();
        if ("Y".equalsIgnoreCase(flagResep)) {

            BigDecimal jumlahResep = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, "resep");
            if (jumlahResep != null && jumlahResep.compareTo(new BigDecimal(0)) == 1) {

                if (jumlahResep.compareTo(new BigDecimal(0)) == 1) {
                    ppnObat = jumlahResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                }

                Map mapPajakObat = new HashMap();
//                mapPajakObat.put("bukti", invoice);
                mapPajakObat.put("nilai", ppnObat);
                mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                if ("umum".equalsIgnoreCase(idJenisPeriksaPasien)) {

                    // create list map piutang
                    Map mapkas = new HashMap();
                    mapkas.put("nilai", jumlahResep.add(ppnObat).add(nominalUnik));
                    mapkas.put("metode_bayar", "transfer");
                    mapkas.put("bank", kodeBank);
                    mapkas.put("nomor_rekening", noRekening);

                    mapJurnal.put("ppn_keluaran", mapPajakObat);
                    mapJurnal.put("pendapatan_rawat_jalan_umum", listOfTindakan);
                    mapJurnal.put("pendapatan_lain", mapPendapatanLain);
                    mapJurnal.put("kas", mapkas);
                    transId = "91";

                } else if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)) {

                    // create list map piutang
                    Map mapPiutang = new HashMap();
//                    mapPiutang.put("bukti", invoice);
                    mapPiutang.put("nilai", jumlahResep.add(ppnObat));
                    mapPiutang.put("master_id", masterId);
//                                mapPiutang.put("pasien_id", idPasien);
                    // debit piutang pasien asuransi
                    mapJurnal.put("ppn_keluaran", mapPajakObat);
                    mapJurnal.put("pendapatan_rawat_jalan_asuransi", listOfTindakan);
                    mapJurnal.put("piutang_pasien_asuransi", mapPiutang);
                    transId = "17";
                }
            }
        }


        String catatan = "Closing Jurnal Resep Telemedic " + jenisPasien + " Id Detail Checkup " + idDetailCheckup;

        try {

            Jurnal noJurnal = billingSystemBo.createJurnal(transId, mapJurnal, branchId, catatan, "Y");

            // --- update no invoice;
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(idDetailCheckup);
            detailCheckup.setInvoice(noJurnal.getNoJurnal());
            detailCheckup.setNoJurnal(noJurnal.getNoJurnal());

            checkupDetailBo.saveUpdateNoJuran(detailCheckup);
            // ---

            response.setStatus("success");
            response.setMsg("[Berhasil]");

        } catch (GeneralBOException e) {
            logger.error("[VerifikatorPembayaranAction.closingJurnalNonTunaiTelemedic] Error, ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorPembayaranAction.closingJurnalNonTunaiTelemedic] Error, " + e);
            return response;
        }

        response.setInvoice(invoice);
        return response;
    }

    private String getMasterIdByTipe(String idDetailCheckup, String jenis) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
        String masterId = "";
        if ("bpjs".equalsIgnoreCase(jenis)) {

            ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById(jenis);
            if (jenisPeriksaPasienEntity != null) {
                masterId = jenisPeriksaPasienEntity.getMasterId();
            }
        } else if ("asuransi".equalsIgnoreCase(jenis)) {
            // jika asuransi
            ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
            if (asuransiEntity != null) {
                masterId = asuransiEntity.getNoMaster();
            } else {
            }

        } else if ("ptpn".equalsIgnoreCase(jenis)) {
            masterId = detailCheckupEntity.getIdAsuransi();
        } else {
            ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById("umum");
            if (jenisPeriksaPasienEntity != null) {
                masterId = jenisPeriksaPasienEntity.getMasterId();
            }
        }

        return masterId;
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
        if (permintaanReseps.size() > 0) {
            for (PermintaanResep dataPermintaan : permintaanReseps) {

                if (dataPermintaan.getIdDetailCheckup() != null) {

                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(dataPermintaan.getIdDetailCheckup());
                    if (detailCheckupEntity != null) {
                        idDetailCheckup = detailCheckupEntity.getIdDetailCheckup();
                        ItSimrsHeaderChekupEntity chekupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
                        if (chekupEntity != null) {
                            idPasien = chekupEntity.getIdPasien();
                        }
                    } else {
                        response.setStatus("error");
                        response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] tidak ditemukan idDetailCheckup");
                        return response;
                    }
                }
//                else {
//                    response.setStatus("error");
//                    response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] tidak ditemukan idDetailCheckup");
//                    return response;
//                }

                if (dataPermintaan.getTujuanPelayanan() != null) {

                    Pelayanan pelayananEntity = pelayananBo.getPelayananById(dataPermintaan.getTujuanPelayanan());
                    if (pelayananEntity != null) {
                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }
                    }
                }


                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(dataPermintaan.getTujuanPelayanan());
                List<Pelayanan> pelayanans = pelayananBo.getByCriteria(pelayanan);
                if (pelayanans.size() > 0) {
                    for (Pelayanan dataPelayanan : pelayanans) {
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

        String catatan = "Pengeluaran Obat " + namaApotek + " " + branchName + " No. Detail Checkup : " + idDetailCheckup + " No. RM " + idPasien;

        String noJurnal = "";

        try {
            Jurnal jurnal = billingSystemBo.createJurnal("30", mapJurnal, branchId, catatan, "Y");
            noJurnal = jurnal.getNoJurnal();
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

    public String getDivisiId(String idDetailCheckup, String jenisPasien, String keterangan) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        KelasRuanganBo kelasRuanganBo = (KelasRuanganBo) ctx.getBean("kelasRuanganBoProxy");
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        String divisiId = "";

        if ("resep".equalsIgnoreCase(keterangan)) {
            ItSimrsRiwayatTindakanEntity riwayatTindakanEntity = riwayatTindakanBo.getRiwayatTindakanResep(idDetailCheckup, jenisPasien);
            if (riwayatTindakanEntity != null) {
                ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(riwayatTindakanEntity.getIdTindakan());
                if (permintaanResepEntity != null) {
                    Pelayanan pelayananEntity = pelayananBo.getPelayananById(permintaanResepEntity.getTujuanPelayanan());
                    if (pelayananEntity != null) {
                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }
                    }
                }
            }
        } else if ("laboratorium".equalsIgnoreCase(keterangan) || "radiologi".equalsIgnoreCase(keterangan)) {
            divisiId = periksaLabBo.getDivisiIdKodering(idDetailCheckup, keterangan);
        } else if ("gizi".equalsIgnoreCase(keterangan)) {

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
            if (detailCheckupEntity != null) {

                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setBranchId(detailCheckupEntity.getBranchId());
                pelayanan.setTipePelayanan("gizi");

                List<Pelayanan> pelayananList = pelayananBo.getByCriteria(pelayanan);
                if (pelayananList.size() > 0) {
                    Pelayanan pelayananData = pelayananList.get(0);

                    ImPosition position = positionBo.getPositionEntityById(pelayananData.getDivisiId());
                    if (position != null) {
                        divisiId = position.getKodering();
                    }
                }
            }

        } else {

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
            if (detailCheckupEntity != null && detailCheckupEntity.getIdPelayanan() != null) {
                try {
                    Pelayanan pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());

                    // jika poli selain rawat inap maka mengambil kodering dari pelayanan
                    // jika poli rawat rawat inap maka mengambil kodering dari kelas ruangan , Sigit
                    if (pelayananEntity != null && !"rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {

                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }

                    } else {

                        RawatInap lastRuangan = rawatInapBo.getLastUsedRoom(idDetailCheckup);
                        if (lastRuangan != null) {
                            MtSimrsRuanganEntity ruanganEntity = ruanganBo.getEntityRuanganById(lastRuangan.getIdRuang());
                            if (ruanganEntity != null) {
                                ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganBo.getKelasRuanganById(ruanganEntity.getIdKelasRuangan());
                                if (kelasRuanganEntity != null) {
                                    ImPosition position = positionBo.getPositionEntityById(kelasRuanganEntity.getDivisiId());
                                    if (position != null) {
                                        divisiId = position.getKodering();
                                    }
                                }
                            }
                        }
                    }
                } catch (GeneralBOException e) {
                    throw new GeneralBOException("[getDivisiId] ERROR " + e);
                }
            } else {
                throw new GeneralBOException("[getDivisiId] ERROR gagal mendapakatkan divisi_id atau data detail checkup");
            }
        }
        return divisiId;
    }

    private BigDecimal getJumlahNilaiBiayaByKeterangan(String idDetailCheckup, String jenisPasien, String keterangan) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        BigDecimal nilai = new BigDecimal(0);
        try {
            nilai = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, keterangan);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
            throw new GeneralBOException("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);

        }
        return nilai;
    }

    private List<Map> getAcitivityList(String idDetailCheckup, String jenisPasien, String ket, String type) {
        logger.info("[CheckupDetailAction.getAcitivityList] START >>>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        //** mencari tindakan dan dimasukan ke jurnal detail activity. START **//
        // dokter team

        List<Map> activityList = new ArrayList<>();

        String idDokter = "";
        DokterTeam dokterTeam = new DokterTeam();
        dokterTeam.setIdDetailCheckup(idDetailCheckup);
        List<ItSimrsDokterTeamEntity> dokterTeamEntities = teamDokterBo.getListEntityTeamDokter(dokterTeam);
        if (dokterTeamEntities.size() > 0) {
            ItSimrsDokterTeamEntity dokterTeamEntity = dokterTeamEntities.get(0);
            idDokter = dokterTeamEntity.getIdDokter();
        }

        // riwayat tindakan list
        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);
        riwayatTindakan.setJenisPasien(jenisPasien);

        if ("".equalsIgnoreCase(ket)) {
            riwayatTindakan.setNotResep("Y");
        } else {
            riwayatTindakan.setKeterangan(ket);
        }

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
        if (riwayatTindakanEntities.size() > 0) {
            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {

                // jika selain JRJ
                // maka obat dikenakan PPN
                BigDecimal ppn = new BigDecimal(0);

                // mencari apakah tindakan transitoris
                boolean nonTransitoris = true;
                if ("JRI".equalsIgnoreCase(type)) {
                    ItSimrsTindakanTransitorisEntity transitorisEntity = riwayatTindakanBo.getTindakanTransitorisById(riwayatTindakanEntity.getIdRiwayatTindakan());
                    if (transitorisEntity != null) {
                        // jika ditemukan transitoris
                        // maka transitoris;
                        nonTransitoris = false;
                    }
                }

                // jika bukan Transitoris
                // maka ditambahkan activity
                if (nonTransitoris) {
                    Map activityMap = new HashMap();
                    activityMap.put("activity_id", riwayatTindakanEntity.getIdTindakan());
                    activityMap.put("person_id", idDokter);
                    activityMap.put("nilai", riwayatTindakanEntity.getTotalTarif().add(ppn));
                    activityMap.put("no_trans", riwayatTindakanEntity.getIdDetailCheckup());
                    activityMap.put("tipe", riwayatTindakanEntity.getJenisPasien());
                    activityList.add(activityMap);
                }
            }
        }
        //** mencari tindakan dan dimasukan ke jurnal detail activity. END **//
        logger.info("[CheckupDetailAction.getAcitivityList] END <<<");
        return activityList;
    }

    private JurnalResponse closingJurnalNonTunai(String idDetailCheckup, String idPoli, String idPasien) {

        JurnalResponse response = new JurnalResponse();
        String branchId = CommonUtil.userBranchLogin();
        String invoice = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        String kode = "";
        String transId = "";
        String ketPoli = "";
        String ketResep = "";
        String divisiId = "";
        String masterId = "";
        String jenisPasien = "Umum ";
        String divisiResep = "";
        String noKartu = "";
        BigDecimal biayaCover = new BigDecimal(0);
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
        ItSimrsHeaderChekupEntity checkupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
        if (checkupEntity != null) {
            idPasien = checkupEntity.getIdPasien();
        }

        if (!"ptpn".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
            if (!"bpjs".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
                if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {

                    biayaCover = detailCheckupEntity.getCoverBiaya();

                    ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
                    if (asuransiEntity != null) {
                        masterId = asuransiEntity.getNoMaster();
                        jenisPasien = "Asuransi " + asuransiEntity.getNamaAsuransi() + " ";
                    } else {
                        logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                        response.setStatus("error");
                        response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                        return response;
                    }

                    noKartu = " No. Kartu Asuransi " + detailCheckupEntity.getNoKartuAsuransi();

                } else {
                    masterId = jenisPriksaPasienBo.getJenisPerikasEntityById(detailCheckupEntity.getIdJenisPeriksaPasien()).getMasterId();
                }

                Pelayanan pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());
                if (pelayananEntity != null) {

                    // MENDAPATKAN DIVISI ID TINDAKAN / PENDAPATAN RAWAT;
                    divisiId = getDivisiId(idDetailCheckup, "", "");

                    // MENDAPATKAN NILAI UANG MUKA;
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
                    mapUangMuka.put("master_id", masterId);

                    Map mapPajakObat = new HashMap();
                    mapPajakObat.put("bukti", invoice);
                    mapPajakObat.put("nilai", ppnObat);

                    Map hsCriteria = new HashMap();
                    BigDecimal jumlah = new BigDecimal(0);

                    if ("rawat_jalan".equalsIgnoreCase(pelayananEntity.getTipePelayanan()) || "igd".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {
                        kode = "JRJ";
                        ketPoli = "Rawat Jalan ";
                    }
                    if ("rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {
                        kode = "JRI";
                        ketPoli = "Rawat Inap ";
                    }

                    // untuk transitoris
                    boolean isTransitoris = false;
                    BigDecimal allTindakanTrans = new BigDecimal(0);
                    if (detailCheckupEntity.getNoJurnalTrans() != null && !"".equalsIgnoreCase(detailCheckupEntity.getNoJurnalTrans())) {

                        allTindakanTrans = checkupDetailBo.getSumJumlahTindakanTransitoris(idDetailCheckup, "");
                        Map mapTransitoris = new HashMap();
                        mapTransitoris.put("nilai", allTindakanTrans);
                        mapTransitoris.put("bukti", detailCheckupEntity.getInvoiceTrans());
                        hsCriteria.put("piutang_transistoris_pasien_rawat_inap", mapTransitoris);
                        isTransitoris = true;
                    }

                    // tambahkan jumlah tindakan juga untuk debit piutang
                    //jumlah = jumlah.add(jumlahTindakan);

                    if ("Y".equalsIgnoreCase(isResep)) {
                        ketResep = "Dengan Obat ";
                    } else {
                        ketResep = "Tanpa Obat ";
                    }

                    // MAP ALL TINDAKAN BY KETERANGAN
                    List<Map> listOfTindakan = new ArrayList<>();
                    List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
                    if (listOfKeteranganRiwayat.size() > 0) {

                        for (String keterangan : listOfKeteranganRiwayat) {
                            Map mapTindakan = new HashMap();
                            mapTindakan.put("master_id", masterId);
                            mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan));
                            mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan));
                            mapTindakan.put("activity", getAcitivityList(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode));
                            listOfTindakan.add(mapTindakan);
                        }
                    }

                    // MENDAPATKAN SEMUA BIAYA RAWAT;
                    jumlah = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "", "");

                    // create invoice nummber
                    invoice = billingSystemBo.createInvoiceNumber(kode, branchId);
                    if ("JRJ".equalsIgnoreCase(kode)) {

                        if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {

                            // dengan pembayaran tunai di luar cover asuransi
                            if (jumlahTindakan.compareTo(biayaCover) == 1) {
                                response.setStatus("success");
                                return response;
                            }

                            // kredit jumlah tindakan asuransi
                            hsCriteria.put("pendapatan_rawat_jalan_asuransi", listOfTindakan);

                            if ("Y".equalsIgnoreCase(isResep)) {

                                // ppn obat asuransi
                                hsCriteria.put("ppn_keluaran", mapPajakObat);

                                // jika ada resep dan ppn untuk debit piutang
                                jumlah = jumlah.add(ppnObat);

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                mapPiutang.put("master_id", masterId);

                                // debit piutang pasien asuransi
                                hsCriteria.put("piutang_pasien_asuransi", mapPiutang);

                                transId = "17";

                            } else {

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                mapPiutang.put("master_id", masterId);
//                                mapPiutang.put("pasien_id", idPasien);

                                // debit piutang pasien asuransi
                                hsCriteria.put("piutang_pasien_asuransi", mapPiutang);

                                transId = "09";
                            }

                        } else {


                            //**** UMUM ***//
                            // kredit jumlah tindakan
                            hsCriteria.put("pendapatan_rawat_jalan_umum", listOfTindakan);

                            // jumlah debit uang muka
                            hsCriteria.put("uang_muka", mapUangMuka);

                            if ("Y".equalsIgnoreCase(isResep)) {

                                // kredit ppn obat umum
                                hsCriteria.put("ppn_keluaran", mapPajakObat);

                                // jika ada resep dan ppn untuk debit piutang
                                jumlah = jumlah.add(ppnObat);

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                mapPiutang.put("pasien_id", idPasien);

                                // debit piutang pasien
                                hsCriteria.put("piutang_pasien_umum", mapPiutang);

                                transId = "14";
                            } else {

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                mapPiutang.put("pasien_id", idPasien);

                                // debit piutang pasien
                                hsCriteria.put("piutang_pasien_umum", mapPiutang);
                                transId = "07";
                            }
                        }
                    }

                    // Untuk Rawat Inap
                    if ("JRI".equalsIgnoreCase(kode)) {
                        if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {

                            //**** ASURANSI ***//

                            // dengan pembayaran tunai di luar cover asuransi
                            if (jumlahTindakan.compareTo(biayaCover) == 1) {
                                response.setStatus("success");
                                return response;
                            }

                            // kredit jumlah tindakan asuransi
                            hsCriteria.put("pendapatan_rawat_inap_asuransi", listOfTindakan);

                            // create map piutang asuransi
                            Map mapPiutang = new HashMap();
//                            mapPiutang.put("bukti", invoice);
                            mapPiutang.put("nilai", jumlah.add(allTindakanTrans).subtract(jumlahUm));
                            mapPiutang.put("master_id", masterId);

                            // debit piutang pasien asuransi
                            hsCriteria.put("piutang_pasien_asuransi", mapPiutang);

                            if (isTransitoris) {
                                jenisPasien = jenisPasien + "Terhadap Transitoris ";
                                transId = "41";
                            } else {
                                transId = "24";
                            }
                        } else {

                            //**** UMUM ***//

                            // create map piutang
                            Map mapPiutang = new HashMap();
//                            mapPiutang.put("bukti", invoice);
                            mapPiutang.put("nilai", jumlah.add(allTindakanTrans).subtract(jumlahUm));
                            mapPiutang.put("pasien_id", idPasien);

                            // debit piutang pasien
                            hsCriteria.put("piutang_pasien_umum", mapPiutang);
                            hsCriteria.put("uang_muka", mapUangMuka);

                            // kredit jumlah tindakan
                            hsCriteria.put("pendapatan_rawat_inap_umum", listOfTindakan);

                            if (isTransitoris) {
                                jenisPasien = jenisPasien + "Terhadap Transitoris ";
                                transId = "39";
                            } else {
                                transId = "21";
                            }
                        }
                    }

                    String catatan = "Closing Pasien " + ketPoli + jenisPasien + ketResep + "No.Detail Checkup " + idDetailCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;


                    try {
                        billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
                        response.setStatus("success");
                        response.setMsg("[Berhasil]");
                    } catch (GeneralBOException e) {
                        logger.info("pendapatan rawat K: " + jumlahTindakan);
                        logger.info("pendapatan obat K: " + jumlahResep);
                        logger.info("piutang transitoris K: " + allTindakanTrans);
                        logger.info("piutang rawat inap D: " + jumlah);
                        logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error, ", e);
                        response.setStatus("error");
                        response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error, " + e);
                        return response;
                    }
                }
            }
        } else {
            response.setStatus("ptpn");
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
        String masterId = "";
        Pelayanan pelayananEntity = pelayananBoProxy.getPelayananById(pelayananId);
        if (pelayananEntity != null) {

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
            ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());

            if (position != null) {
                divisiId = position.getKodering();
            }

        } else {
            jurnalResponse.setStatus("error");
            jurnalResponse.setMsg("[TransaksiObatAction.createJurnalPembayaranObatbaru] ERROR. tidak dapat divisi_id. ");
            return jurnalResponse;
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById("umum");
        if (jenisPeriksaPasienEntity != null) {
            masterId = jenisPeriksaPasienEntity.getMasterId();
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
        hsCriteria.put("kas", mapKas);

        Map mapPendapatan = new HashMap();
        mapPendapatan.put("nilai", new BigDecimal(trans.getTotalBayar()));
        mapPendapatan.put("master_id", masterId);
        mapPendapatan.put("divisi_id", divisiId);

        hsCriteria.put("pendapatan_obat_umum", mapPendapatan);
        hsCriteria.put("ppn_keluaran", mapPPN);

        String noJurnal = "";
        try {
            Jurnal jurnal = billingSystemBoProxy.createJurnal("29", hsCriteria, branchId, "Penjualan Obat Apotik Langsung " + branchId, "Y");
            noJurnal = jurnal.getNoJurnal();
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

            if (id != null) {
                try {
                    checkup = checkupBoProxy.getDataDetailPasien(id);
                } catch (GeneralBOException e) {
                    logger.error("Found Error when search data detail pasien " + e.getMessage());
                }
            }

            if (checkup != null || idResep != null) {

                try {
                    permintaanResep = checkupDetailBoProxy.getDataDokter(idResep);
                } catch (HibernateException e) {
                    logger.error("Found Error " + e.getMessage());
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

            Pelayanan pelayanan = new Pelayanan();
            pelayanan.setIdPelayanan(checkup.getIdPelayanan());
            List<Pelayanan> pelayananList = new ArrayList<>();

            try {
                pelayananList = pelayananBoProxy.getByCriteria(pelayanan);
            } catch (GeneralBOException e) {
                logger.error("Found Error " + e.getMessage());
            }

            if (pelayananList.size() > 0) {
                Pelayanan plyn = pelayananList.get(0);
                reportParams.put("tipePelayanan", plyn.getTipePelayanan());
            }

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
            PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
            VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
            TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");

            Pasien pasienEntity = new Pasien();
            ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = new ItSimrsAntrianTelemedicEntity();
            ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(idResep);
            if (permintaanResepEntity != null) {
                ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(permintaanResepEntity.getIdTransaksiOnline());
                if (pembayaranOnlineEntity != null) {
                    antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
                    if (antrianTelemedicEntity != null) {

                        Pasien pasien = new Pasien();
                        pasien.setIdPasien(antrianTelemedicEntity.getIdPasien());

                        List<Pasien> pasienList = pasienBo.getByCriteria(pasien);
                        if (pasienList.size() > 0) {
                            pasienEntity = pasienList.get(0);
                        }
                    }
                }
            }

            reportParams.put("idDokter", permintaanResep.getIdDokter());
            reportParams.put("dokter", permintaanResep.getNamaDokter());
            reportParams.put("ttdDokter", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + permintaanResep.getTtdDokter());
            reportParams.put("resepId", idResep);
            reportParams.put("petugas", permintaanResep.getNamaApoteker());
            reportParams.put("ttdPasien", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_PASIEN + permintaanResep.getTtdPasien());
            reportParams.put("ttdApoteker", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_APOTEKER + permintaanResep.getTtdApoteker());
            reportParams.put("idPasien", checkup.getIdPasien() == null ? pasienEntity.getIdPasien() : checkup.getIdPasien());
            reportParams.put("nik", checkup.getNoKtp() == null ? pasienEntity.getNoKtp() : checkup.getNoKtp());
            reportParams.put("nama", checkup.getNama() == null ? pasienEntity.getNama() : checkup.getNama());

            String formatDate = "";
            if (checkup.getTglLahir() != null || pasienEntity.getTglLahir() != null) {
                if (pasienEntity.getTglLahir() != null) {
                    Date dTgl = Date.valueOf(pasienEntity.getTglLahir());
                    formatDate = new SimpleDateFormat("dd-MM-yyyy").format(dTgl);
                } else {
                    formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                }
            }

            reportParams.put("tglLahir", checkup.getTempatLahir() == null ? pasienEntity.getTempatLahir() : checkup.getTempatLahir() + ", " + formatDate);

            if ("L".equalsIgnoreCase(checkup.getJenisKelamin() == null ? pasienEntity.getJenisKelamin() : checkup.getJenisKelamin())) {
                jk = "Laki-Laki";
            } else {
                jk = "Perempuan";
            }

            reportParams.put("jenisKelamin", jk);
            reportParams.put("jenisPasien", checkup.getStatusPeriksaName() == null ? antrianTelemedicEntity.getIdJenisPeriksaPasien() : checkup.getStatusPeriksaName());
            reportParams.put("poli", checkup.getNamaPelayanan() == null ? "E-Obat" : checkup.getNamaPelayanan());
            reportParams.put("provinsi", checkup.getNamaProvinsi() == null ? pasienEntity.getProvinsi() : checkup.getNamaProvinsi());
            reportParams.put("kabupaten", checkup.getNamaKota() == null ? pasienEntity.getKota() : checkup.getNamaKota());
            reportParams.put("kecamatan", checkup.getNamaKecamatan() == null ? pasienEntity.getKecamatan() : checkup.getNamaKecamatan());
            reportParams.put("desa", checkup.getNamaDesa() == null ? pasienEntity.getDesa() : checkup.getNamaDesa());

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

    public String printLabelResepPasien() {
        String idResep = getIdResep();
        String branch = CommonUtil.userBranchLogin();
        Branch branches = new Branch();
        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            String logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
            reportParams.put("logo", logo);
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("id", idResep);
            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                return "search";
            }
        }

        return "print_label_resep";
    }

    public CheckResponse saveTtdPasien(String ttdPasien, String idResep, String ttdApoteker) {
        logger.info("base64 " + ttdPasien);
        CheckResponse response = new CheckResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

        try {
            BASE64Decoder decoder = new BASE64Decoder();

            byte[] decodedBytes1 = decoder.decodeBuffer(ttdPasien);
            byte[] decodedBytes2 = decoder.decodeBuffer(ttdApoteker);

            logger.info("Decoded upload data : " + decodedBytes1.length);
            String fileName1 = "ttd_pasien-" + idResep + "-" + dateFormater("MM") + dateFormater("yy") + ".png";
            String fileName2 = "ttd_apoteker-" + idResep + "-" + dateFormater("MM") + dateFormater("yy") + ".png";
            String uploadFile1 = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_PASIEN + fileName1;
            String uploadFile2 = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_APOTEKER + fileName2;
            logger.info("File save path : " + uploadFile1);
            BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(decodedBytes1));
            BufferedImage image2 = ImageIO.read(new ByteArrayInputStream(decodedBytes2));

            if (image1 == null) {
                logger.error("Buffered Image is null");
                response.setStatus("error");
                return response;
            } else {
                File f1 = new File(uploadFile1);
                File f2 = new File(uploadFile2);
                // write the image
                ImageIO.write(image1, "png", f1);
                ImageIO.write(image2, "png", f2);

                response.setStatus("success");
                response = transaksiObatBo.setTtdPasien(idResep, fileName1, fileName2);
            }
        } catch (IOException e) {
            response.setStatus("error");
            response.setMessage("TTD tidak bisa disimpan...!" + e.getMessage());
        }

        return response;
    }

    public List<PermintaanResep> pushNotifResep() {
        List<PermintaanResep> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        try {
            list = transaksiObatBo.getListNotifResep(CommonUtil.userPelayananIdLogin(), CommonUtil.userBranchLogin());
        } catch (GeneralBOException e) {
            logger.error("Found Error " + e.getMessage());
        }
        return list;
    }

    public String cekRole() {
        return CommonUtil.roleAsLogin();
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

    // jsonString {[idobat : value, idbarang : value, qtyreture : value, jenissatuan : value]}
    public CrudResponse retureResep(String idResep, String idApprovalObat, String jsonString) throws JSONException {

        String userLogin = CommonUtil.userLogin();
        Timestamp time = CommonUtil.getCurrentDateTimes();
        String branchId = CommonUtil.userBranchLogin();
        List<TransaksiObatDetail> listBatchReture = null;
        List<TransaksiObatDetail> listObatTidakDitanggung = null;
        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

        // colect dari jsoString dan insert ke listObatreture dan listObatTidakDitanggung;
        if (jsonString != null && !"".equalsIgnoreCase(jsonString)) {
            JSONArray json = new JSONArray(jsonString);
            listBatchReture = new ArrayList<>();
            listObatTidakDitanggung = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {

                JSONObject obj = json.getJSONObject(i);
                TransaksiObatDetail retureObat = new TransaksiObatDetail();

                boolean qtyIsNull = obj.getString("qtyreture") == null || "".equalsIgnoreCase(obj.getString("qtyreture")) || "null".equalsIgnoreCase(obj.getString("qtyreture"));
                BigInteger qtyReture = qtyIsNull ? new BigInteger(String.valueOf(0)) : new BigInteger(obj.getString("qtyreture"));

                // jika ada yang di reture;
                if (qtyReture.compareTo(new BigInteger(String.valueOf(0))) == 1) {
                    retureObat.setIdObat(obj.getString("idobat"));
                    retureObat.setIdBarang(obj.getString("idbarang"));
                    retureObat.setQtyApprove(new BigInteger(obj.getString("qtyreture")));
                    retureObat.setJenisSatuan(obj.getString("jenissatuan"));
                }

                List<TransaksiObatDetail> listTransaksiObatDetail = transaksiObatBo.getListTransaksiObatDetailBatchByIdResepAndIdBarang(idResep, retureObat.getIdBarang());
                if (listTransaksiObatDetail.size() > 0) {
                    TransaksiObatDetail searchTransaksi = listTransaksiObatDetail.get(0);

                    retureObat.setIdTransaksiObatDetail(searchTransaksi.getIdTransaksiObatDetail());
                    retureObat.setIdPelayananTujuan(searchTransaksi.getIdPelayananTujuan());
                    retureObat.setIdPermintaanResep(searchTransaksi.getIdPermintaanResep());
                    retureObat.setAverageHargaBiji(searchTransaksi.getHargaRata());
                    retureObat.setHarga(searchTransaksi.getHarga());
                    retureObat.setHargaRata(searchTransaksi.getHargaRata());
                    retureObat.setHargaJual(searchTransaksi.getHargaJual());

                    if ("umum".equalsIgnoreCase(searchTransaksi.getJenisResep())) {
                        listObatTidakDitanggung.add(retureObat);
                    }
                }


                listBatchReture.add(retureObat);
            }
        }

        TransaksiObatDetail beanUpdate = new TransaksiObatDetail();
        beanUpdate.setLastUpdate(time);
        beanUpdate.setLastUpdateWho(userLogin);

        // save update jumlah direture
        try {
            transaksiObatBo.saveUpdateRetureObat(listBatchReture, beanUpdate);
            response.setStatus("success");
        } catch (GeneralBOException e) {
            logger.info("[TransaksiObatAction.retureResep] ERROR. ", e);
            response.setMsg("[TransaksiObatAction.retureResep] ERROR. " + e);
            response.setStatus("error");
            return response;
        }


        // create jurnal persediaan barang masuk;
        // hitung harga rata-rata barang masuk;
        if (listBatchReture.size() > 0) {

            createJurnalPemasukanApotik(idApprovalObat);
            for (TransaksiObatDetail retureObat : listBatchReture) {
                retureObat.setBranchId(branchId);
                retureObat.setLastUpdate(time);
                retureObat.setLastUpdateWho(userLogin);

                transaksiObatBo.saveUpdateHargaRataBarangMasukKarnaReture(retureObat);
            }
        }

        // create jurnal kas kluar untuk obat yang direture;
        if (listObatTidakDitanggung.size() > 0) {
            createJurnalKasKeluar(idApprovalObat, branchId, idResep, listObatTidakDitanggung);
        }

//        TransaksiObatDetail beanUpdate = new TransaksiObatDetail();
//        beanUpdate.setLastUpdate(time);
//        beanUpdate.setLastUpdateWho(userLogin);
//
//        // save update jumlah direture
//        try {
//            transaksiObatBo.saveUpdateRetureObat(listBatchReture, beanUpdate);
//            response.setStatus("success");
//        } catch (GeneralBOException e){
//            logger.info("[TransaksiObatAction.retureResep] ERROR. ", e);
//            response.setMsg("[TransaksiObatAction.retureResep] ERROR. "+ e);
//            response.setStatus("error");
//            return response;
//        }

        return response;
    }

    private JurnalResponse createJurnalPemasukanApotik(String idApprove) {

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
        String idResep = "";
        PermintaanResep permintaanResep = new PermintaanResep();
        permintaanResep.setIdApprovalObat(idApprove);
        List<PermintaanResep> permintaanReseps = permintaanResepBo.getByCriteria(permintaanResep);
        if (permintaanReseps.size() > 0) {
            for (PermintaanResep dataPermintaan : permintaanReseps) {

                idResep = dataPermintaan.getIdPermintaanResep();
                if (dataPermintaan.getIdDetailCheckup() != null) {

                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(dataPermintaan.getIdDetailCheckup());
                    if (detailCheckupEntity != null) {
                        idDetailCheckup = detailCheckupEntity.getIdDetailCheckup();
                        ItSimrsHeaderChekupEntity chekupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
                        if (chekupEntity != null) {
                            idPasien = chekupEntity.getIdPasien();
                        }
                    } else {
                        response.setStatus("error");
                        response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] tidak ditemukan idDetailCheckup");
                        return response;
                    }
                }
//                else {
//                    response.setStatus("error");
//                    response.setMsg("[TransaksiObatAction.createJurnalPengeluaranObatApotik] tidak ditemukan idDetailCheckup");
//                    return response;
//                }

                if (dataPermintaan.getTujuanPelayanan() != null) {

                    Pelayanan pelayananEntity = pelayananBo.getPelayananById(dataPermintaan.getTujuanPelayanan());
                    if (pelayananEntity != null) {
                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }
                    }
                }


                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(dataPermintaan.getTujuanPelayanan());
                List<Pelayanan> pelayanans = pelayananBo.getByCriteria(pelayanan);
                if (pelayanans.size() > 0) {
                    for (Pelayanan dataPelayanan : pelayanans) {
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

                if (trans.getQtyReture().compareTo(new BigInteger(String.valueOf(0))) == 1) {
                    BigDecimal hargaRata = new BigDecimal(0);
                    ImSimrsObatEntity obatEntity = obatBo.getObatByIdBarang(trans.getIdBarang());
                    if (obatEntity != null) {
                        hargaRata = trans.getHargaRata();
//                        if ("box".equalsIgnoreCase(trans.getJenisSatuan()))
//                            hargaRata = obatEntity.getAverageHargaBox();
//                        if ("lembar".equalsIgnoreCase(trans.getJenisSatuan()))
//                            hargaRata = obatEntity.getAverageHargaLembar();
//                        if ("biji".equalsIgnoreCase(trans.getJenisSatuan()))
//                            hargaRata = obatEntity.getAverageHargaBiji();
                    }

                    BigDecimal hargaTotal = hargaRata.multiply(new BigDecimal(trans.getQtyReture()));
                    biayaPersediaan = biayaPersediaan.add(hargaTotal);

                    Map mapPersediaanObat = new HashMap();
                    mapPersediaanObat.put("kd_barang", trans.getIdBarang());
                    mapPersediaanObat.put("nilai", hargaTotal);
                    listMapPersediaan.add(mapPersediaanObat);
                }
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

        String catatan = "Pemasukan Obat Karna Reture " + namaApotek + " " + branchName + " No. Detail Checkup : " + idDetailCheckup + " No. RM " + idPasien;

        String noJurnal = "";

        try {
            Jurnal jurnal = billingSystemBo.createJurnal("80", mapJurnal, branchId, catatan, "Y");
            noJurnal = jurnal.getNoJurnal();
            response.setNoJurnal(noJurnal);
            response.setStatus("success");
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("[TransaksiObatAction.createJurnalPemasukanApotik] ERROR when search list obat, " + e.getMessage());
            logger.error("[TransaksiObatAction.createJurnalPemasukanApotik] ERROR when search list obat, ", e);
            return response;
        }
        return response;
    }

    private String createJurnalKasKeluar(String idApprove, String branchId, String idPermintaan, List<TransaksiObatDetail> obatDetails) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

        String divisiId = "";
        ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(idPermintaan);
        if (permintaanResepEntity != null) {

            Pelayanan pelayananEntity = pelayananBo.getPelayananById(permintaanResepEntity.getTujuanPelayanan());
            if (pelayananEntity != null) {
                ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());

                if (position != null) {
                    divisiId = position.getKodering();
                }

            }
        }

        List<Map> listMapPendapatan = new ArrayList<>();
        BigDecimal nilai = new BigDecimal(0);
        for (TransaksiObatDetail obatDetail : obatDetails) {
            BigDecimal hargaJual = obatDetail.getHargaJual() != null ? obatDetail.getHargaJual() : new BigDecimal(0);
            BigInteger qtyReture = obatDetail.getQtyApprove() != null ? obatDetail.getQtyApprove() : new BigInteger(String.valueOf(0));
            BigDecimal total = hargaJual.multiply(new BigDecimal(qtyReture));
            nilai = nilai.add(total);
        }

        Map mapPendapatan = new HashMap();
        mapPendapatan.put("nilai", nilai);
        mapPendapatan.put("divisi_id", divisiId);
        listMapPendapatan.add(mapPendapatan);

        Map mapKas = new HashMap();
        mapKas.put("nilai", nilai);
        mapKas.put("metode_bayar", "tunai");

        Map mapJurnal = new HashMap();
        mapJurnal.put("pendapatan_rawat_jalan_umum", listMapPendapatan);
        mapJurnal.put("kas", mapKas);


        String catatan = "Reture Obat Untuk Resep " + idPermintaan;

        String noJurnal = "";
        try {
            Jurnal jurnal = billingSystemBo.createJurnal("92", mapJurnal, branchId, catatan, "Y");
            noJurnal = jurnal.getNoJurnal();
        } catch (GeneralBOException e) {
            logger.error("[TransaksiObatAction.createJurnalKasKeluar] ERROR when search list obat, ", e);
            return "";
        }

        return noJurnal;
    }

}