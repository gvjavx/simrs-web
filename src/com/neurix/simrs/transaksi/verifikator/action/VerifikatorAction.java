package com.neurix.simrs.transaksi.verifikator.action;

import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
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
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.kategoritindakanina.model.ImSimrsKategoriTindakanInaEntity;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.keterangankeluar.bo.KeteranganKeluarBo;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.rekananops.bo.RekananOpsBo;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.ItSimrsPaketPasienEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsPaketEntity;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabEntity;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.periksaradiologi.bo.PeriksaRadiologiBo;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikator.bo.VerifikatorBo;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class VerifikatorAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(VerifikatorAction.class);
    private HeaderDetailCheckup headerDetailCheckup;
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;
    private DiagnosaBo diagnosaBoProxy;
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private TindakanBo tindakanBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private KelasRuanganBo kelasRuanganBoProxy;
    private RuanganBo ruanganBoProxy;
    private KeteranganKeluarBo keteranganKeluarBoProxy;
    private HeaderCheckup headerCheckup;
    private TindakanRawatBo tindakanRawatBoProxy;
    private VerifikatorBo verifikatorBoProxy;
    private EklaimBo eklaimBoProxy;
    private RawatInapBo rawatInapBoProxy;
    private RawatInap rawatInap;
    private BranchBo branchBoProxy;

    private String id;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public void setVerifikatorBoProxy(VerifikatorBo verifikatorBoProxy) {
        this.verifikatorBoProxy = verifikatorBoProxy;
    }

    public RawatInap getRawatInap() {
        return rawatInap;
    }

    public void setRawatInap(RawatInap rawatInap) {
        this.rawatInap = rawatInap;
    }

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    public void setEklaimBoProxy(EklaimBo eklaimBoProxy) {
        this.eklaimBoProxy = eklaimBoProxy;
    }

    public VerifikatorBo getVerifikatorBoProxy() {
        return verifikatorBoProxy;
    }

    public HeaderDetailCheckup getHeaderDetailCheckup() {
        return headerDetailCheckup;
    }

    public void setHeaderDetailCheckup(HeaderDetailCheckup headerDetailCheckup) {
        this.headerDetailCheckup = headerDetailCheckup;
    }

    public HeaderCheckup getHeaderCheckup() {
        return headerCheckup;
    }

    public void setHeaderCheckup(HeaderCheckup headerCheckup) {
        this.headerCheckup = headerCheckup;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public void setDiagnosaBoProxy(DiagnosaBo diagnosaBoProxy) {
        this.diagnosaBoProxy = diagnosaBoProxy;
    }

    public void setKategoriTindakanBoProxy(KategoriTindakanBo kategoriTindakanBoProxy) {
        this.kategoriTindakanBoProxy = kategoriTindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public void setKelasRuanganBoProxy(KelasRuanganBo kelasRuanganBoProxy) {
        this.kelasRuanganBoProxy = kelasRuanganBoProxy;
    }

    public void setRuanganBoProxy(RuanganBo ruanganBoProxy) {
        this.ruanganBoProxy = ruanganBoProxy;
    }

    public void setKeteranganKeluarBoProxy(KeteranganKeluarBo keteranganKeluarBoProxy) {
        this.keteranganKeluarBoProxy = keteranganKeluarBoProxy;
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
        logger.info("[VerifikatorAction.search] START process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();
        headerDetailCheckup.setIdJenisPeriksaPasien("bpjs");
        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());

        try {
            listOfsearchHeaderDetailCheckup = verifikatorBoProxy.getListVerifikasiRawatJalan(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[VerifikatorAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[VerifikatorAction.search] END process <<<");
        return "search";
    }

    public String searchRawatInap() {
        logger.info("[VerifikatorAction.searchRawatInap] START process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> rawatInapList = new ArrayList();
        rawatInap.setIdJenisPeriksa("bpjs");
        rawatInap.setBranchId(CommonUtil.userBranchLogin());

        try {
            rawatInapList = verifikatorBoProxy.getListVerifikasiRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[VerifikatorAction.searchRawatInap] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", rawatInapList);

        logger.info("[VerifikatorAction.searchRawatInap] END process <<<");
        return "search";
    }

    @Override
    public String initForm() {

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
        headerDetailCheckup.setStDateFrom(tglToday);
        headerDetailCheckup.setStDateTo(tglToday);

        RawatInap rawatInap = new RawatInap();
        rawatInap.setStTglFrom(tglToday);
        rawatInap.setStTglTo(tglToday);

        setRawatInap(rawatInap);
        setHeaderDetailCheckup(headerDetailCheckup);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        return "search";
    }

    public List<RiwayatTindakan> getListTindakanRawat(String noCheckup, String idDetail, String jenisPasien) {
        logger.info("[VerifikatorAction.getListTindakanRawat] START process <<<");

        List<RiwayatTindakan> result = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorBo riwayatTindakanBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");

        RiwayatTindakan tindakanRawat = new RiwayatTindakan();
        tindakanRawat.setNoCheckup(noCheckup);
        tindakanRawat.setIdDetailCheckup(idDetail);
        tindakanRawat.setBranchId(CommonUtil.userBranchLogin());

        CheckResponse response = new CheckResponse();
        response = cekAllTindakanRawat(idDetail);
        if ("success".equalsIgnoreCase(response.getStatus())) {
            saveAddToRiwayatTindakan(idDetail, jenisPasien);
            try {
                result = riwayatTindakanBo.getListAllTindakan(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorAction.getListTindakanRawat] Error when get data tindakan rawat ", e);
            }
        } else {
            RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
            riwayatTindakan.setIsPendingTindakan("Y");
            riwayatTindakan.setMsg(response.getMessage());
            result.add(riwayatTindakan);
        }

        logger.info("[VerifikatorAction.getListTindakanRawat] END process <<<");
        return result;
    }

    public CheckResponse updateApproveBpjsFlag(String idTindakan, String kategoriTindakanBpjs, String jenisPasien) {
        logger.info("[VerifikatorAction.updateApproveBpjsFlag] START process <<<");

        CheckResponse response = new CheckResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorBo verifikatorBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");

        RiwayatTindakan tindakanRawat = new RiwayatTindakan();
        tindakanRawat.setIdRiwayatTindakan(idTindakan);
        tindakanRawat.setKategoriTindakanBpjs(kategoriTindakanBpjs);
        tindakanRawat.setLastUpdate(time);
        tindakanRawat.setLastUpdateWho(userLogin);
        tindakanRawat.setJenisPasien(jenisPasien);

        if (!"".equalsIgnoreCase(idTindakan) && idTindakan != null) {

            try {
                response = verifikatorBo.updateApproveBpjsFlag(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorAction.updateApproveBpjsFlag] Error when update data flag approve tindakan rawat ", e);
            }

        }
        logger.info("[VerifikatorAction.updateApproveBpjsFlag] END process <<<");
        return response;
    }

    public CheckResponse saveApproveTindakan(String idDetailCheckup, String jsonData) {
        logger.info("[VerifikatorAction.saveApproveTindakan] START process <<<");

        CheckResponse response = new CheckResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        String unitId = CommonUtil.userBranchLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        VerifikatorBo verifikatorBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        try {
            List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
            JSONArray json = new JSONArray(jsonData);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                riwayatTindakan.setIdRiwayatTindakan(obj.getString("id_riwayat_tindakan"));
                riwayatTindakan.setJenisPasien(obj.getString("jenis_pasien"));
                riwayatTindakan.setKategoriTindakanBpjs(obj.getString("kategori"));
                riwayatTindakan.setIdDetailCheckup(idDetailCheckup);
                riwayatTindakan.setLastUpdateWho(userLogin);
                riwayatTindakan.setLastUpdate(time);
                riwayatTindakanList.add(riwayatTindakan);
            }

            if (riwayatTindakanList.size() > 0) {
                response = verifikatorBo.updateRiwayatTindakan(riwayatTindakanList);
                if ("success".equalsIgnoreCase(response.getStatus())) {
                    HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                    detailCheckup.setIdDetailCheckup(idDetailCheckup);

                    List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
                    try {
                        detailCheckupList = checkupDetailBo.getByCriteria(detailCheckup);
                    } catch (GeneralBOException e) {
                        logger.error("[VerifikatorAction.saveApproveTindakan] Error When Get Header Checkup Data", e);
                    }

                    HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();

                    if (!detailCheckupList.isEmpty()) {
                        headerDetailCheckup = detailCheckupList.get(0);

                        if (headerDetailCheckup != null) {

                            HeaderCheckup headerCheckup = new HeaderCheckup();
                            headerCheckup.setNoCheckup(headerDetailCheckup.getNoCheckup());

                            List<HeaderCheckup> headerCheckupList = new ArrayList<>();
                            List<Branch> branchList = new ArrayList<>();

                            try {
                                headerCheckupList = checkupBo.getByCriteria(headerCheckup);
                            } catch (GeneralBOException e) {
                                logger.error("[VerifikatorAction.saveApproveTindakan] Error When Get Header Checkup Data", e);
                            }

                            HeaderCheckup checkup = new HeaderCheckup();
                            if (!headerCheckupList.isEmpty()) {
                                checkup = headerCheckupList.get(0);

                                if (checkup != null) {

                                    Branch branch = new Branch();
                                    branch.setBranchId(unitId);
                                    branch.setFlag("Y");

                                    try {
                                        branchList = branchBo.getByCriteria(branch);
                                    } catch (GeneralBOException e) {
                                        logger.error("[CheckupAction.saveAdd] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
                                    }

                                    Branch getBranch = new Branch();

                                    if (branchList.size() > 0) {
                                        getBranch = branchList.get(0);

                                        if (getBranch.getCoderNik() != null) {

                                            DataPerKlaimResponse klaimResponse = new DataPerKlaimResponse();

                                            //search detail tindakan dari eklaim
                                            try {
                                                klaimResponse = eklaimBo.detailPerKlaimEklaim(headerDetailCheckup.getNoSep(), unitId);
                                            } catch (GeneralBOException e) {
                                                logger.error("[VerifikatorAction.saveApproveTindakan] Error When Get Header Checkup Data", e);
                                            }

                                            List<RiwayatTindakan> tindakanRawatList = new ArrayList<>();
                                            RiwayatTindakan tindakanRawat = new RiwayatTindakan();
                                            tindakanRawat.setIdDetailCheckup(idDetailCheckup);

                                            //search tindakan rawat by id detail checkup
                                            try {
                                                tindakanRawatList = riwayatTindakanBo.getByCriteria(tindakanRawat);
                                            } catch (GeneralBOException e) {
                                                logger.error("[VerifikatorAction.saveApproveTindakan] Error When Get Tindakan Rawat Data", e);
                                            }

                                            BigInteger tarifRsProsedurNonBedah = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsTenagaAhli = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsRadiologi = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsRehabilitasi = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsObat = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsAlkes = new BigInteger(String.valueOf(0));

                                            BigInteger tarifRsProsedurBedah = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsKeperawatan = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsLaboratorium = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsKamar = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsObatKronis = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsBmhp = new BigInteger(String.valueOf(0));

                                            BigInteger tarifRsKonsultasi = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsPenunjang = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsPelayananDarah = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsRawatIntensif = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsObatKemoterapi = new BigInteger(String.valueOf(0));
                                            BigInteger tarifRsSewaAlat = new BigInteger(String.valueOf(0));

                                            if (klaimResponse != null) {

                                                if (klaimResponse.getTarifRsProsedurNonBedah() != null) {
                                                    tarifRsProsedurNonBedah = new BigInteger(String.valueOf(klaimResponse.getTarifRsProsedurNonBedah()));
                                                }
                                                if (klaimResponse.getTarifRsTenagaAhli() != null) {
                                                    tarifRsTenagaAhli = new BigInteger(String.valueOf(klaimResponse.getTarifRsTenagaAhli()));
                                                }
                                                if (klaimResponse.getTarifRsRadiologi() != null) {
                                                    tarifRsRadiologi = new BigInteger(String.valueOf(klaimResponse.getTarifRsRadiologi()));
                                                }
                                                if (klaimResponse.getTarifRsRehabilitasi() != null) {
                                                    tarifRsRehabilitasi = new BigInteger(String.valueOf(klaimResponse.getTarifRsRehabilitasi()));
                                                }
                                                if (klaimResponse.getTarifRsObat() != null) {
                                                    tarifRsObat = new BigInteger(String.valueOf(klaimResponse.getTarifRsObat()));
                                                }
                                                if (klaimResponse.getTarifRsAlkes() != null) {
                                                    tarifRsAlkes = new BigInteger(String.valueOf(klaimResponse.getTarifRsAlkes()));
                                                }

                                                //-----------------------------------
                                                if (klaimResponse.getTarifRsProsedurBedah() != null) {
                                                    tarifRsProsedurBedah = new BigInteger(String.valueOf(klaimResponse.getTarifRsProsedurBedah()));
                                                }
                                                if (klaimResponse.getTarifRsKeperawatan() != null) {
                                                    tarifRsKeperawatan = new BigInteger(String.valueOf(klaimResponse.getTarifRsKeperawatan()));
                                                }
                                                if (klaimResponse.getTarifRsLaboratorium() != null) {
                                                    tarifRsLaboratorium = new BigInteger(String.valueOf(klaimResponse.getTarifRsLaboratorium()));
                                                }
                                                if (klaimResponse.getTarifRsKamar() != null) {
                                                    tarifRsKamar = new BigInteger(String.valueOf(klaimResponse.getTarifRsKamar()));
                                                }
                                                if (klaimResponse.getTarifRsObatKronis() != null) {
                                                    tarifRsObatKronis = new BigInteger(String.valueOf(klaimResponse.getTarifRsObatKronis()));
                                                }
                                                if (klaimResponse.getTarifRsBmhp() != null) {
                                                    tarifRsBmhp = new BigInteger(String.valueOf(klaimResponse.getTarifRsBmhp()));
                                                }

                                                //-----------------------------------
                                                if (klaimResponse.getTarifRsKonsultasi() != null) {
                                                    tarifRsKonsultasi = new BigInteger(String.valueOf(klaimResponse.getTarifRsKonsultasi()));
                                                }
                                                if (klaimResponse.getTarifRsPenunjang() != null) {
                                                    tarifRsPenunjang = new BigInteger(String.valueOf(klaimResponse.getTarifRsPenunjang()));
                                                }
                                                if (klaimResponse.getTarifRsPelayananDarah() != null) {
                                                    tarifRsPelayananDarah = new BigInteger(String.valueOf(klaimResponse.getTarifRsPelayananDarah()));
                                                }
                                                if (klaimResponse.getTarifRsRawatIntensif() != null) {
                                                    tarifRsRawatIntensif = new BigInteger(String.valueOf(klaimResponse.getTarifRsRawatIntensif()));
                                                }
                                                if (klaimResponse.getTarifRsObatKemotrapis() != null) {
                                                    tarifRsObatKemoterapi = new BigInteger(String.valueOf(klaimResponse.getTarifRsObatKemotrapis()));
                                                }
                                                if (klaimResponse.getTarifRsSewaAlat() != null) {
                                                    tarifRsSewaAlat = new BigInteger(String.valueOf(klaimResponse.getTarifRsSewaAlat()));
                                                }

                                            }

                                            boolean isRawatInap = false;

                                            for (RiwayatTindakan riwayatTindakan : tindakanRawatList) {
                                                if ("kamar".equalsIgnoreCase(riwayatTindakan.getKeterangan())) {
                                                    isRawatInap = true;
                                                }
                                            }

                                            for (RiwayatTindakan tindakan : tindakanRawatList) {

                                                BigDecimal ppnObat = new BigDecimal(String.valueOf(0));
                                                BigInteger tarifTotal = tindakan.getTotalTarif().toBigInteger();

                                                if ("resep".equalsIgnoreCase(tindakan.getKeterangan())) {
                                                    if (isRawatInap) {
                                                        tarifTotal = tarifTotal;
                                                    } else {
                                                        ppnObat = (tindakan.getTotalTarif().multiply(new BigDecimal(0.1))).setScale(2, BigDecimal.ROUND_HALF_UP);
                                                        tarifTotal = ppnObat.toBigInteger().add(tarifTotal);
                                                    }
                                                }

                                                if ("Y".equalsIgnoreCase(tindakan.getApproveBpjsFlag()) && tindakan.getTotalTarif() != null && !"Y".equalsIgnoreCase(tindakan.getFlagUpdateKlaim()) && "bpjs".equalsIgnoreCase(tindakan.getJenisPasien())) {

                                                    if ("prosedur_non_bedah".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsProsedurNonBedah = tarifRsProsedurNonBedah.add(new BigInteger(tarifTotal.toString()));
                                                    }
                                                    if ("tenaga_ahli".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsTenagaAhli = tarifRsTenagaAhli.add(new BigInteger(tarifTotal.toString()));
                                                    }
                                                    if ("radiologi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsRadiologi = tarifRsRadiologi.add(new BigInteger(tarifTotal.toString()));
                                                    }
                                                    if ("rehabilitasi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsRehabilitasi = tarifRsRehabilitasi.add(new BigInteger(tarifTotal.toString()));
                                                    }
                                                    if ("obat".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsObat = tarifRsObat.add(new BigInteger(tarifTotal.toString()));
                                                    }
                                                    if ("alkes".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsAlkes = tarifRsAlkes.add(new BigInteger(tarifTotal.toString()));

                                                    }

                                                    //--------------
                                                    if ("prosedur_bedah".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsProsedurBedah = tarifRsProsedurBedah.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("keperawatan".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsKeperawatan = tarifRsKeperawatan.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("laboratorium".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsLaboratorium = tarifRsLaboratorium.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("kamar_akomodasi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsKamar = tarifRsKamar.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("obat_kronis".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsObatKronis = tarifRsObatKronis.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("bmhp".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsBmhp = tarifRsBmhp.add(new BigInteger(tarifTotal.toString()));

                                                    }

                                                    //--------------
                                                    if ("konsultasi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsKonsultasi = tarifRsKonsultasi.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("penunjang".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsPenunjang = tarifRsPenunjang.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("pelayanan_darah".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsPelayananDarah = tarifRsPelayananDarah.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("rawat_intensif".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsRawatIntensif = tarifRsRawatIntensif.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("obat_kemoterapi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsObatKemoterapi = tarifRsObatKemoterapi.add(new BigInteger(tarifTotal.toString()));

                                                    }
                                                    if ("sewa_alat".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                                        tarifRsSewaAlat = tarifRsSewaAlat.add(new BigInteger(tarifTotal.toString()));

                                                    }

                                                }

                                                CheckResponse response1 = new CheckResponse();
                                                RiwayatTindakan riwayat = new RiwayatTindakan();
                                                riwayat.setIdRiwayatTindakan(tindakan.getIdRiwayatTindakan());
                                                riwayat.setLastUpdateWho(userLogin);
                                                riwayat.setLastUpdate(time);

                                                try {
                                                    response1 = verifikatorBo.updateFlagKlaim(riwayat);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[VerifikatorAction] Found error when update riwayat tindakan, " + e);
                                                }
                                            }

                                            KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                            klaimDetailRequest.setTglMasuk(checkup.getCreatedDate().toString());
                                            klaimDetailRequest.setTglPulang(time.toString());
                                            klaimDetailRequest.setNomorSep(klaimResponse.getNomorSep());
                                            klaimDetailRequest.setNomorKartu(klaimResponse.getNomorKartu());
                                            klaimDetailRequest.setJenisRawat(klaimResponse.getJenisRawat().toString());
                                            klaimDetailRequest.setKelasRawat(klaimResponse.getKelasRawat().toString());
                                            klaimDetailRequest.setAdlSubAcute(klaimResponse.getAdlSubAcute().toString());
                                            klaimDetailRequest.setAdlChronic(klaimResponse.getAdlChronic().toString());
                                            klaimDetailRequest.setIcuIndikator(klaimResponse.getIcuIndicator().toString());
                                            klaimDetailRequest.setIcuLos(klaimResponse.getIcuLos());
                                            klaimDetailRequest.setVentilatorHour(klaimResponse.getVentilatorHour());
                                            klaimDetailRequest.setUpgradeClassInd(klaimResponse.getUpgradeClassInd());
                                            klaimDetailRequest.setUpgradeClassClass(klaimResponse.getUpgradeClassClass());
                                            klaimDetailRequest.setUpgradeClassLos(klaimResponse.getUpgradeClassLos());
                                            klaimDetailRequest.setAddPaymentPct(klaimResponse.getAddPaymenPct());
                                            klaimDetailRequest.setBirthWeight(klaimResponse.getBeratLahir());
                                            klaimDetailRequest.setDischargeStatus(klaimResponse.getDischargeStatus().toString());
                                            klaimDetailRequest.setDiagnosa(klaimResponse.getDiagnosa());
                                            klaimDetailRequest.setProcedure(klaimResponse.getProcedure());

                                            //update tarif tindakan
                                            klaimDetailRequest.setTarifRsNonBedah(tarifRsProsedurNonBedah.toString());
                                            klaimDetailRequest.setTarifRsProsedurBedah(tarifRsProsedurBedah.toString());
                                            klaimDetailRequest.setTarifRsKonsultasi(tarifRsKonsultasi.toString());
                                            klaimDetailRequest.setTarifRsTenagaAhli(tarifRsTenagaAhli.toString());
                                            klaimDetailRequest.setTarifRsKeperawatan(tarifRsKeperawatan.toString());
                                            klaimDetailRequest.setTarifRsPenunjang(tarifRsPenunjang.toString());

                                            klaimDetailRequest.setTarifRsRadiologi(tarifRsRadiologi.toString());
                                            klaimDetailRequest.setTarifRsLaboratorium(tarifRsLaboratorium.toString());
                                            klaimDetailRequest.setTarifRsPelayananDarah(tarifRsPelayananDarah.toString());
                                            klaimDetailRequest.setTarifRsRehabilitasi(tarifRsRehabilitasi.toString());
                                            klaimDetailRequest.setTarifRsKamar(tarifRsKamar.toString());
                                            klaimDetailRequest.setTarifRsRawatIntensif(tarifRsRawatIntensif.toString());

                                            klaimDetailRequest.setTarifRsObat(tarifRsObat.toString());
                                            klaimDetailRequest.setTarifRsObatKronis(tarifRsObatKronis.toString());
                                            klaimDetailRequest.setTarifRsObatKemoterapi(tarifRsObatKemoterapi.toString());
                                            klaimDetailRequest.setTarifRsObatAlkes(tarifRsAlkes.toString());
                                            klaimDetailRequest.setTarifRsBmhp(tarifRsBmhp.toString());
                                            klaimDetailRequest.setTarifRsSewaAlat(tarifRsSewaAlat.toString());

                                            //------------
                                            klaimDetailRequest.setTarifPoliEks(klaimResponse.getTarifPoliEks());
                                            klaimDetailRequest.setNamaDokter(klaimResponse.getNamaDokter());
                                            klaimDetailRequest.setKodeTarif(klaimResponse.getKodeTarif());
                                            klaimDetailRequest.setTarifRsPayorId(klaimResponse.getPayorId());
                                            klaimDetailRequest.setPayorCd(klaimResponse.getKlaimStatusCd());
                                            klaimDetailRequest.setCobCd(klaimResponse.getKlaimStatusCd());
                                            klaimDetailRequest.setCoderNik(getBranch.getCoderNik());

                                            KlaimDetailResponse klaimDetailResponse = new KlaimDetailResponse();

                                            //update eklaim with new tarif tindakan
                                            try {
                                                klaimDetailResponse = eklaimBo.updateDataClaimEklaim(klaimDetailRequest, unitId);
                                                response.setStatus(klaimDetailResponse.getStatus());
                                                response.setMessage(klaimDetailResponse.getMessage());
                                            } catch (GeneralBOException e) {
                                                logger.error("[VerifikatorAction.saveApproveTindakan] Error When update tarif tindakan to eklaim", e);
                                                response.setStatus("error");
                                                response.setMessage("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
                                            }

                                            if (klaimDetailResponse != null) {
                                                if ("200".equalsIgnoreCase(klaimDetailResponse.getStatus())) {
                                                    Grouping1Response grouping1Response = new Grouping1Response();

                                                    //groper setelah update tarif tindakan
                                                    try {
                                                        grouping1Response = eklaimBo.groupingStage1Eklaim(headerDetailCheckup.getNoSep(), unitId);
                                                    } catch (GeneralBOException e) {
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item , Found problem when saving add data, please inform to your admin.", e);
                                                        response.setStatus("error");
                                                        response.setMessage("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
                                                    }

                                                    // jika ada special cmg maka proses grouping stage 2
                                                    if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                                        for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                            Grouping2Response grouping2Response = new Grouping2Response();
                                                            try {
                                                                grouping2Response = eklaimBo.groupingStage2Eklaim(headerDetailCheckup.getNoSep(), specialCmgResponse.getCode(), unitId);
                                                            } catch (GeneralBOException e) {
                                                                logger.error("[CheckupAction.saveAdd] Error when adding item ,Found problem when saving add data, please inform to your admin.", e);
                                                                response.setStatus("error");
                                                                response.setMessage("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                        } else {
                                            response.setStatus("error");
                                            response.setMessage("Coder Nik Petugas Bpjs Tidak ada..!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                response.setStatus("error");
                response.setMessage("Found Error. Tidak ada data");
            }
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMessage("Found Error. " + e.getMessage());
        }

        logger.info("[VerifikatorAction.saveApproveTindakan] END process <<<");
        return response;
    }

    public CheckResponse finalClaim(String idDetailCheckup, String idPasien) {
        logger.info("[VerifikatorAction.finalClaim] START process <<<");

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        CheckResponse response = new CheckResponse();

        String unitId = CommonUtil.userBranchLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        VerifikatorBo verifikatorBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");

        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        HeaderDetailCheckup detail = new HeaderDetailCheckup();
        detail.setIdDetailCheckup(idDetailCheckup);

        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
        List<Branch> branchList = new ArrayList<>();

        try {
            detailCheckupList = checkupDetailBo.getByCriteria(detail);
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorAction.finalClaim] Error When Get Header Checkup Data", e);
        }

        if (!detailCheckupList.isEmpty()) {
            detail = detailCheckupList.get(0);
            if (detail != null) {

                Branch branch = new Branch();
                branch.setBranchId(unitId);
                branch.setFlag("Y");

                try {
                    branchList = branchBo.getByCriteria(branch);
                } catch (GeneralBOException e) {
                    logger.error("[CheckupAction.saveAdd] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
                }

                Branch getBranch = new Branch();

                if (branchList.size() > 0) {
                    getBranch = branchList.get(0);

                    if (getBranch.getCoderNik() != null) {
                        try {
                            response = eklaimBo.finalisasiClaimEklaim(detail.getNoSep(), getBranch.getCoderNik(), unitId);
                        } catch (GeneralBOException e) {
                            logger.error("[VerifikatorAction.finalClaim] Error When final claim", e);
                        }

                        if ("200".equalsIgnoreCase(response.getStatus())) {

                            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                            detailCheckup.setIdDetailCheckup(detail.getIdDetailCheckup());
                            detailCheckup.setLastUpdateWho(userLogin);
                            detailCheckup.setLastUpdate(updateTime);

                            try {
                                response = verifikatorBo.updateKlaimBpjsFlag(detailCheckup);
                            } catch (HibernateException e) {
                                logger.error("[VerifikatorAction.finalClaim] Error When send data seneter per eklaim", e);
                            }
                        }
                    } else {
                        response.setStatus("error");
                        response.setMessage("Codek Nik Petugas Bpjs tidak ada...!");
                        return response;
                    }
                }
            }

            String noSep = "";
            ItSimrsHeaderDetailCheckupEntity headerDetailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
            if (headerDetailCheckupEntity != null) {
                noSep = headerDetailCheckupEntity.getNoSep();
            } else {
                logger.error("[VerifikatorAction.finalClaim] Error tidak dapat no_sep, ");
                response.setStatus("error");
                response.setMessage("[VerifikatorAction.finalClaim] Error tidak dapat no_sep, ");
                return response;
            }

            // create jurnal

            String typePelayanan = detail.getTipePelayanan();
            String branchId = CommonUtil.userBranchLogin();
            JurnalResponse jurnalResponse = closingJurnalPbjs(idDetailCheckup, typePelayanan, branchId);
            if ("success".equalsIgnoreCase(jurnalResponse.getStatus())) {

                // update invoice to detailcheckup
                CheckResponse updateResponse = updateDetailCheckupInvoice(idDetailCheckup, jurnalResponse.getInvoice());
                if ("error".equalsIgnoreCase(updateResponse.getStatus())) {
                    response.setMessage(updateResponse.getMessage());
                    response.setStatus(updateResponse.getStatus());
                    return response;
                }

                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setIdDetailCheckup(idDetailCheckup);
                detailCheckup.setAction("U");
                detailCheckup.setLastUpdate(updateTime);
                detailCheckup.setLastUpdateWho(userLogin);
                detailCheckup.setNoJurnal(jurnalResponse.getNoJurnal());
                detailCheckup.setInvoice(jurnalResponse.getInvoice());

                try {
                    checkupDetailBo.saveUpdateNoJuran(detailCheckup);
                } catch (GeneralBOException e) {
                    logger.error("[VerifikatorAction.finalClaim] ERROR when save no jurnal, ");
                    response.setStatus("error");
                    response.setMessage("[VerifikatorAction.finalClaim] ERROR when save no jurnal, ");
                    return response;
                }
            }

        }
        return response;
    }

    private JurnalResponse closingJurnalPbjs(String idDetailCheckup, String typePelayanan, String branchId) {

        JurnalResponse response = new JurnalResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        ItSimrsHeaderDetailCheckupEntity headerDetailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
        ItSimrsHeaderChekupEntity headerChekupEntity = checkupBo.getEntityCheckupById(headerDetailCheckupEntity.getNoCheckup());

        String idPasien = headerChekupEntity.getIdPasien();
        if ("ptpn".equalsIgnoreCase(headerDetailCheckupEntity.getIdJenisPeriksaPasien())) {

            // FILTER JIKA JENIS PASIEN ADALAH PTPN
            response = closingPasienPtpnBpjs(idDetailCheckup, branchId);
        } else {
            Map hsCriteria = new HashMap();

            // MENDAPATKAN TINDAKAN TRANSITORIS
            boolean isTransitoris = false;
            BigDecimal allTindakanTransBpjs = new BigDecimal(0);
            if (headerDetailCheckupEntity.getNoJurnalTrans() != null && !"".equalsIgnoreCase(headerDetailCheckupEntity.getNoJurnalTrans())) {

                // for bpjs;
                allTindakanTransBpjs = checkupDetailBo.getSumJumlajTindakanTransitorisByJenis(idDetailCheckup, "bpjs", "");

                Map mapTransitoris = new HashMap();
                mapTransitoris.put("nilai", allTindakanTransBpjs);
                mapTransitoris.put("bukti", headerDetailCheckupEntity.getNoSep());
                hsCriteria.put("piutang_transistoris_pasien_rawat_inap", mapTransitoris);
                isTransitoris = true;
            }

            String kode = "";
            String transId = "";
            String ketPoli = "";
            String ketObat = "";

            // MEDAPATKAN JENIS JURNAL BERDASARKAN TIPE PELAYANAN
            if (!"".equalsIgnoreCase(typePelayanan) && typePelayanan != null) {
                if ("rawat_jalan".equalsIgnoreCase(typePelayanan) || "igd".equalsIgnoreCase(typePelayanan)) {
                    kode = "JRJ";
                    ketPoli = "Rawat Jalan";
                }
                if ("rawat_inap".equalsIgnoreCase(typePelayanan)) {
                    kode = "JRI";
                    ketPoli = "Rawat Inap";
                }
            }

            List<Map> listOfMapTindakanBpjs = new ArrayList<>();
            List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
            if (listOfKeteranganRiwayat.size() > 0) {

                if ("JRI".equalsIgnoreCase(kode)){
                    for (String keterangan : listOfKeteranganRiwayat){

                        if ("kamar".equalsIgnoreCase(keterangan) || "tindakan".equalsIgnoreCase(keterangan)){
                            List<String> listRuangan = riwayatTindakanBo.getListRuanganRiwayatTindakan(idDetailCheckup, keterangan);
                            if (listRuangan.size() > 0) {
                                for (String ruangan : listRuangan) {
                                    Map mapTindakan = new HashMap();
                                    mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                                    mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "bpjs", keterangan, ruangan));
                                    mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", keterangan, ruangan));
                                    mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "bpjs", keterangan, kode, ruangan));
                                    listOfMapTindakanBpjs.add(mapTindakan);
                                }
                            }
                        } else {

                            Map mapTindakan = new HashMap();
                            mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                            mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "bpjs", keterangan, ""));
                            mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", keterangan, ""));
                            mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "bpjs", keterangan, kode, ""));
                            listOfMapTindakanBpjs.add(mapTindakan);
                        }
                    }
                } else {
                    for (String keterangan : listOfKeteranganRiwayat) {
                        Map mapTindakan = new HashMap();
                        mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                        mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "bpjs", keterangan, ""));
                        mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", keterangan, ""));
                        mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "bpjs", keterangan, kode, ""));
                        listOfMapTindakanBpjs.add(mapTindakan);
                    }
                }
            }

            BigDecimal jumlah = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "", "");

            // MENDAPATKAN SEMUA NILAI TINDAKAN DAN RESEP
            String isResep = checkupDetailBo.findResep(idDetailCheckup);
            // CREATE JURNAL OBAT JIKA RESEP / RAWAT INAP
            if ("Y".equalsIgnoreCase(isResep) || "rawat_inap".equalsIgnoreCase(typePelayanan)) {

                // hitung ppn
                BigDecimal jmlResep = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "resep", "");
                BigDecimal ppn = jmlResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);

                // KREDIT PPN
                // JIKA RAWAT INAP TIDAK MENYERTAKAN PPN;
                Map mapPPN = new HashMap();
//                mapPPN.put("bukti", billingSystemBo.createInvoiceNumber(kode, branchId));
                mapPPN.put("nilai", ppn);
                mapPPN.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                if (!"rawat_inap".equalsIgnoreCase(typePelayanan)) {
                    hsCriteria.put("ppn_keluaran", mapPPN);
                }

                // JUMLAH SEMUA PENDAPATAN;
                // JIKA RAWAT INAP TIDAK MENYERTAKAN PPN;
                if ("rawat_inap".equalsIgnoreCase(typePelayanan)) {
                    jumlah = jumlah.add(allTindakanTransBpjs);
                } else {
                    jumlah = jumlah.add(allTindakanTransBpjs).add(ppn);
                }

                ketObat = "dengan Obat";

            } else {
                // debit jumlah untuk piutang pasien bpjs
                jumlah = jumlah.add(allTindakanTransBpjs);
                ketObat = "tanpa Obat";
            }

            String invNumber = billingSystemBo.createInvoiceNumber(kode, branchId);


            Map mapPiutang = new HashMap();
//            mapPiutang.put("bukti", headerDetailCheckupEntity.getNoSep());
            mapPiutang.put("nilai", jumlah);
            mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));

            // JIKA RAWAT JALAN
            if ("JRJ".equalsIgnoreCase(kode)) {
                // debit jumlah untuk piutang pasien bpjs
                hsCriteria.put("piutang_pasien_bpjs", mapPiutang);
                // kredit jumlah tindakan
                hsCriteria.put("pendapatan_rawat_jalan_bpjs", listOfMapTindakanBpjs);

                ketPoli = "Rawat Jalan";
                if ("Y".equalsIgnoreCase(isResep))
                    transId = "13";
                if ("N".equalsIgnoreCase(isResep))
                    transId = "06";
            }

            // JIKA RAWAT INAP
            if ("JRI".equalsIgnoreCase(kode)) {

                // debit jumlah untuk piutang pasien bpjs
                hsCriteria.put("piutang_pasien_bpjs", mapPiutang);
                // kredit jumlah tindakan
                hsCriteria.put("pendapatan_rawat_inap_bpjs", listOfMapTindakanBpjs);

                if (isTransitoris) {
                    transId = "33";
                    ketPoli = "Rawat Inap Terhadap Transitoris";
                } else {
                    transId = "20";
                    ketPoli = "Rawat Inap";
                }
            }

            String catatan = "Closing Pasien " + ketPoli + " BPJS " + ketObat + ". No Detail Checkup " + idDetailCheckup + ". No Pasien " + idPasien;

            String noJurnal = "";
            try {
                Jurnal jurnal = billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
                noJurnal = jurnal.getNoJurnal();
                response.setStatus("success");
                response.setInvoice(jurnal.getNoJurnal());
                response.setNoJurnal(noJurnal);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorAction.closingJurnalPbjs] Error When send data seneter per eklaim", e);
                response.setStatus("error");
                response.setMsg("[VerifikatorAction.closingJurnalPbjs] Error When send data seneter per eklaim " + e);
                return response;
            }
        }

        logger.info("[VerifikatorAction.finalClaim] END process <<<");
        return response;
    }

    private BigDecimal hitungPPN(BigDecimal harga) {
        BigDecimal jumlah = new BigDecimal(0);
        if (harga != null) {
            jumlah = harga.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return jumlah;
    }

    private JurnalResponse closingPasienPtpnBpjs(String idDetailCheckup, String branchId) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        JurnalResponse response = new JurnalResponse();
        String kode = "";
        String transId = "";
        String ketPoli = "";
        String jenisPasien = "";
        String jenisPtpn = "murni";
        String invoice = "";
        String company = "";
        String noKartu = "";

        // PENGAMBILAN DETAIL CHECKUP SEBAGAI DATA UTAMA;
        // PENGAMBILAN COVER BIAYA;
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
        ItSimrsHeaderChekupEntity headerChekupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
        BigDecimal biayaCover = detailCheckupEntity.getTarifBpjs();
        noKartu = " No. Kartu PTPN " + detailCheckupEntity.getNoKartuAsuransi();

        ImMasterEntity masterEntity = masterBo.getEntityMasterById(detailCheckupEntity.getIdAsuransi());
        if (masterEntity != null) {
            company = " " + masterEntity.getNama() + " ";
        } else {
            logger.error("[VerivikatorAction.closingPasienPtpnBpjs] Error Master PTPN tidak ditemukan");
            response.setStatus("error");
            response.setMsg("[VerivikatorAction.closingPasienPtpnBpjs] Error Master PTPN tidak ditemukan");
            return response;
        }

        // MENDAPATKAN SEMUA NILAI TINDAKAN DAN RESEP
        String isResep = "N";
        BigDecimal jumlahResep = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, "", "resep");
        BigDecimal jumlahAllTindakan = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "", "", "");
        BigDecimal ppnObat = new BigDecimal(0);
        if (jumlahResep.compareTo(new BigDecimal(0)) == 1) {
            isResep = "Y";
            ppnObat = jumlahResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        // BANDINGKAN SEMUA TINDAKAN TERHADAP COVER UNTUK MENENTUKAN MURNI/NON MURNI BPJS
        if (jumlahAllTindakan.compareTo(biayaCover) == 1) {
            jenisPtpn = "non_murni";
        }

        // MENDAPATKAN JENIS JURNAL;
        ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());
        if ("rawat_jalan".equalsIgnoreCase(pelayananEntity.getTipePelayanan()) || "igd".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {
            kode = "JRJ";
            ketPoli = " Rawat Jalan ";
        }
        if ("rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {
            kode = "JRI";
            ketPoli = " Rawat Inap ";
        }

        // GENERATE INVOICE 1;
        invoice = billingSystemBo.createInvoiceNumber(kode, branchId);

        // INISIALISASI MAP UNTUK CREATE JURNAL;
        Map hsCriteria = new HashMap();

        // MENGAMBIL NILAI TINDAKAN YG TERTRANSITORIS;
        boolean isTransitoris = false;
        BigDecimal allTindakanTransBpjs = new BigDecimal(0);
        BigDecimal allTindakanTransPtpn = new BigDecimal(0);
        if (detailCheckupEntity.getNoJurnalTrans() != null && !"".equalsIgnoreCase(detailCheckupEntity.getNoJurnalTrans())) {

            // for bpjs;
            allTindakanTransBpjs = checkupDetailBo.getSumJumlajTindakanTransitorisByJenis(idDetailCheckup, "bpjs", "");

            // for ptpn;
            allTindakanTransPtpn = checkupDetailBo.getSumJumlajTindakanTransitorisByJenis(idDetailCheckup, "ptpn", "");

            Map mapTransitoris = new HashMap();
            mapTransitoris.put("nilai", allTindakanTransBpjs.add(allTindakanTransPtpn));
            mapTransitoris.put("bukti", detailCheckupEntity.getInvoiceTrans());
            hsCriteria.put("piutang_transistoris_pasien_rawat_inap", mapTransitoris);
            isTransitoris = true;
        }

        // MAPPING ALL TINDAKAN
        List<Map> listOfMapTindakanBpjs = new ArrayList<>();
        List<Map> listOfMapTindakanPtpn = new ArrayList<>();
        List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
        if (listOfKeteranganRiwayat.size() > 0) {

            if ("JRI".equalsIgnoreCase(kode)){
                for (String keterangan : listOfKeteranganRiwayat){

                    if ("kamar".equalsIgnoreCase(keterangan) || "tindakan".equalsIgnoreCase(keterangan)){
                        List<String> listRuangan = riwayatTindakanBo.getListRuanganRiwayatTindakan(idDetailCheckup, keterangan);
                        if (listRuangan.size() > 0) {
                            for (String ruangan : listRuangan) {
                                Map mapTindakan = new HashMap();
                                mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "bpjs", keterangan, ruangan));
                                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", keterangan, ruangan));
                                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "bpjs", keterangan, kode, ruangan));
                                listOfMapTindakanBpjs.add(mapTindakan);

                                mapTindakan = new HashMap();
                                mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "ptpn"));
                                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "ptpn", keterangan, ruangan));
                                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "ptpn", keterangan, ruangan));
                                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "ptpn", keterangan, kode, ruangan));
                                listOfMapTindakanPtpn.add(mapTindakan);
                            }
                        }
                    } else {

                        Map mapTindakan = new HashMap();
                        mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                        mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "bpjs", keterangan, ""));
                        mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", keterangan, ""));
                        mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "bpjs", keterangan, kode, ""));
                        listOfMapTindakanBpjs.add(mapTindakan);

                        mapTindakan = new HashMap();
                        mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "ptpn"));
                        mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "ptpn", keterangan, ""));
                        mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "ptpn", keterangan, ""));
                        mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "ptpn", keterangan, kode, ""));
                        listOfMapTindakanPtpn.add(mapTindakan);
                    }
                }
            } else {
                for (String keterangan : listOfKeteranganRiwayat) {
                    Map mapTindakan = new HashMap();
                    mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                    mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "bpjs", keterangan, ""));
                    mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", keterangan, ""));
                    mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "bpjs", keterangan, kode, ""));
                    listOfMapTindakanBpjs.add(mapTindakan);
                }
                for (String keterangan : listOfKeteranganRiwayat) {
                    Map mapTindakan = new HashMap();
                    mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "ptpn"));
                    mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "ptpn", keterangan, ""));
                    mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "ptpn", keterangan, ""));
                    mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "ptpn", keterangan, kode, ""));
                    listOfMapTindakanPtpn.add(mapTindakan);
                }
            }
        }

        String noInvoicePtpnMurni = billingSystemBo.createInvoiceNumber(kode, branchId);
        if ("murni".equalsIgnoreCase(jenisPtpn)) {

            //** BPJS MURNI **//

            // PENGURANGAN OLEH TRANSITORIS
            Map mapPajakObat = new HashMap();
//            mapPajakObat.put("bukti", invoice);
            mapPajakObat.put("nilai", ppnObat);
            mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

            if ("JRJ".equalsIgnoreCase(kode)) {

                // kredit jumlah tindakan PTPN
                hsCriteria.put("pendapatan_rawat_jalan_bpjs", listOfMapTindakanBpjs);

                if ("Y".equalsIgnoreCase(isResep)) {

                    // ppn obat PTPN
                    hsCriteria.put("ppn_keluaran", mapPajakObat);

                    // reate map piutang
                    Map mapPiutang = new HashMap();
                    mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "", "").add(ppnObat));
//                    mapPiutang.put("bukti", detailCheckupEntity.getNoSep());
                    mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));

                    // debit piutang pasien PTPN
                    hsCriteria.put("piutang_pasien_bpjs", mapPiutang);
                    transId = "16";
                    jenisPasien = " Murni Dengan Obat ";
                } else {

                    // create map piutang
                    Map mapPiutang = new HashMap();
//                    mapPiutang.put("bukti", detailCheckupEntity.getNoSep());
                    mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "", ""));
                    mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                    hsCriteria.put("piutang_pasien_bpjs", mapPiutang);
                    // debit piutang pasien PTPN
                    hsCriteria.put("piutang_pasien_bpjs", mapPiutang);
                    transId = "08";
                    jenisPasien = " Murni Tanpa Obat ";
                }
            }

            if ("JRI".equalsIgnoreCase(kode)) {

                // kredit jumlah tindakan PTPN
                hsCriteria.put("pendapatan_rawat_inap_bpjs", listOfMapTindakanBpjs);

                Map mapPiutang = new HashMap();
//                mapPiutang.put("bukti", noInvoicePtpnMurni);
                mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "", ""));
                mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));

                // debit piutang pasien PTPN
                hsCriteria.put("piutang_pasien_bpjs", mapPiutang);

                if (isTransitoris) {
                    transId = "40";
                    jenisPasien = " Murni Terhadap Transitoris";
                } else {
                    transId = "23";
                    jenisPasien = " Murni ";
                }

            }
        } else {

            //** NON MURNI BPJS / PTPN DAN BPJS **//

            jenisPasien = "  ";
            if ("JRJ".equalsIgnoreCase(kode)) {

                // PENGURANGAN OLEH TRANSITORIS
                Map mapPajakObat = new HashMap();
//                mapPajakObat.put("bukti", invoice);
                mapPajakObat.put("nilai", ppnObat);
                mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                Map mapPiutangBpjs = new HashMap();
//                mapPiutangBpjs.put("bukti", detailCheckupEntity.getNoSep());
                mapPiutangBpjs.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                mapPiutangBpjs.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "", ""));

                Map mapPiutangPtpn = new HashMap();
//                mapPiutangPtpn.put("bukti", billingSystemBo.createInvoiceNumber(kode, branchId));
                mapPiutangPtpn.put("master_id", getMasterIdByTipe(idDetailCheckup, "ptpn"));
                mapPiutangPtpn.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "ptpn", "", "").add(ppnObat));

                if ("Y".equalsIgnoreCase(isResep)) {

                    hsCriteria.put("piutang_pasien_bpjs", mapPiutangBpjs);
                    hsCriteria.put("piutang_pasien_ptpn", mapPiutangPtpn);
                    hsCriteria.put("pendapatan_rawat_jalan_bpjs", listOfMapTindakanBpjs);
                    hsCriteria.put("pendapatan_rawat_jalan_ptpn", listOfMapTindakanPtpn);
                    hsCriteria.put("ppn_keluaran", mapPajakObat);
                    transId = "18";

                } else {
                    hsCriteria.put("piutang_pasien_bpjs", mapPiutangBpjs);
                    hsCriteria.put("piutang_pasien_ptpn", mapPiutangPtpn);
                    hsCriteria.put("pendapatan_rawat_jalan_bpjs", listOfMapTindakanBpjs);
                    hsCriteria.put("pendapatan_rawat_jalan_ptpn", listOfMapTindakanPtpn);
                    transId = "10";
                }
            }

            if ("JRI".equalsIgnoreCase(kode)) {

                Map mapPiutangBpjs = new HashMap();
//                mapPiutangBpjs.put("bukti", detailCheckupEntity.getNoSep());
                mapPiutangBpjs.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                mapPiutangBpjs.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "", "").add(allTindakanTransBpjs));

                Map mapPiutangPtpn = new HashMap();
//                mapPiutangPtpn.put("bukti", billingSystemBo.createInvoiceNumber(kode, branchId));
                mapPiutangPtpn.put("master_id", getMasterIdByTipe(idDetailCheckup, "ptpn"));
                mapPiutangPtpn.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "ptpn", "", "").add(allTindakanTransPtpn));

                hsCriteria.put("piutang_pasien_bpjs", mapPiutangBpjs);
                hsCriteria.put("piutang_pasien_ptpn", mapPiutangPtpn);
                hsCriteria.put("pendapatan_rawat_inap_bpjs", listOfMapTindakanBpjs);
                hsCriteria.put("pendapatan_rawat_inap_ptpn", listOfMapTindakanPtpn);

                if (isTransitoris) {
                    jenisPasien = jenisPasien + "Terhadap Transitoris";
                    transId = "42";
                } else {
                    transId = "25";
                }
            }
        }

        String catatan = "Closing" + ketPoli + company + jenisPasien + " No Detail Checkup " + idDetailCheckup + " No. RM " + headerChekupEntity.getIdPasien() + noKartu;
        String noJurnal = "";
        try {
            Jurnal jurnal = billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
            noJurnal = jurnal.getNoJurnal();
            response.setStatus("success");
            response.setNoJurnal(noJurnal);
            response.setInvoice(noJurnal);
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorAction.closingPasienPtpnBpjs] ERROR Create Jurnal. ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorAction.closingPasienPtpnBpjs] ERROR Create Jurnal. " + e);
            return response;
        }

        return response;
    }

    private BigDecimal getJumlahNilaiBiayaByKeterangan(String idDetailCheckup, String jenisPasien, String keterangan, String idRuangan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        BigDecimal nilai = new BigDecimal(0);
        try {
            if (idRuangan == null || "".equalsIgnoreCase(idRuangan)) {
                nilai = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, keterangan);
            } else {
                nilai = checkupDetailBo.getSumJumlahTindakanByJenisRI(idDetailCheckup, jenisPasien, keterangan, idRuangan);
            }
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
            throw new GeneralBOException("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);

        }
        return nilai;
    }


    public String getDivisiId(String idDetailCheckup, String jenisPasien, String keterangan, String idRuangan) {

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

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);

        if ("resep".equalsIgnoreCase(keterangan)) {
            ItSimrsRiwayatTindakanEntity riwayatTindakanEntity = riwayatTindakanBo.getRiwayatTindakanResep(idDetailCheckup, jenisPasien);
            if (riwayatTindakanEntity != null) {
                ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(riwayatTindakanEntity.getIdTindakan());
                if (permintaanResepEntity != null) {
                    ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(permintaanResepEntity.getTujuanPelayanan());
                    if (pelayananEntity != null) {
                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }
                    }
                }
            }
        } else if ("laboratorium".equalsIgnoreCase(keterangan) || "radiologi".equalsIgnoreCase(keterangan)){
            divisiId = periksaLabBo.getDivisiIdKodering(idDetailCheckup, keterangan);
        } else if ("gizi".equalsIgnoreCase(keterangan)){

            detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
            if (detailCheckupEntity != null){

                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setBranchId(detailCheckupEntity.getBranchId());
                pelayanan.setTipePelayanan("gizi");

                List<Pelayanan> pelayananList = pelayananBo.getByCriteria(pelayanan);
                if (pelayananList.size() > 0){
                    Pelayanan pelayananData = pelayananList.get(0);

                    ImPosition position = positionBo.getPositionEntityById(pelayananData.getDivisiId());
                    if (position != null) {
                        divisiId = position.getKodering();
                    }
                }
            }

        } else {

            if (detailCheckupEntity != null && detailCheckupEntity.getIdPelayanan() != null) {
                try {
                    ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());

                    // jika poli selain rawat inap maka mengambil kodering dari pelayanan
                    // jika poli rawat rawat inap maka mengambil kodering dari kelas ruangan , Sigit
                    if (pelayananEntity != null && !"rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {

                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }

                    } else {

                        if (idRuangan != null && !"".equalsIgnoreCase(idRuangan)){
                            MtSimrsRuanganEntity ruanganEntity = ruanganBo.getEntityRuanganById(idRuangan);
                            if (ruanganEntity != null) {
                                ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganBo.getKelasRuanganById(ruanganEntity.getIdKelasRuangan());
                                if (kelasRuanganEntity != null) {
                                    ImPosition position = positionBo.getPositionEntityById(kelasRuanganEntity.getDivisiId());
                                    if (position != null) {
                                        divisiId = position.getKodering();
                                    }
                                }
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

    private List<Map> getAcitivityList(String idDetailCheckup, String jenisPasien, String ket, String type, String idRuangan) {
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

        if (idRuangan != null && !"".equalsIgnoreCase(idRuangan))
            riwayatTindakan.setIdRuangan(idRuangan);

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
        if (riwayatTindakanEntities.size() > 0) {
            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {

                // cari id dokter per keterangan riwayat tindakan
                String idDokterTindakan = getIdDokter(riwayatTindakanEntity.getIdTindakan(), riwayatTindakanEntity.getKeterangan());

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
                    activityMap.put("person_id", idDokterTindakan != null && !"".equalsIgnoreCase(idDokterTindakan) ? idDokterTindakan : idDokter);
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

    private String getMasterIdByTipe(String idDetailCheckup, String jenis) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");
        RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");

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

        } else if ("rekanan".equalsIgnoreCase(jenis)) {

            ImSimrsRekananOpsEntity rekananOpsEntity = rekananOpsBo.getRekananEntityById(detailCheckupEntity.getIdAsuransi());
            if (rekananOpsEntity != null){
                masterId = rekananOpsEntity.getNomorMaster();
            }

        } else {
            ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById("umum");
            if (jenisPeriksaPasienEntity != null) {
                masterId = jenisPeriksaPasienEntity.getMasterId();
            }
        }

        return masterId;
    }

    private CheckResponse updateDetailCheckupInvoice(String idDetailCheckup, String invNumber) {
        CheckResponse response = new CheckResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        try {
            response = checkupDetailBo.updateInvoiceBpjs(idDetailCheckup, invNumber);
            response.setStatus("200");
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorAction.updateDetailCheckupInvoice] Error ", e);
            response.setStatus("error");
            response.setMessage("[VerifikatorAction.updateDetailCheckupInvoice] Error " + e);
            return response;
        }
        return response;
    }

    public KlaimDataCenterResponse sendClaimOnline(String noCheckup) {
        logger.info("[VerifikatorAction.sendClaimOnline] START process <<<");

        KlaimDataCenterResponse dataCenterResponse = new KlaimDataCenterResponse();
        String unitId = CommonUtil.userBranchLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        VerifikatorBo verifikatorBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();

        try {
            headerCheckupList = checkupBo.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorAction.sendClaimOnline] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup checkup = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()) {
            checkup = headerCheckupList.get(0);
            if (checkup != null) {
                List<KlaimDataCenterResponse> klaimDataCenterResponses = new ArrayList<>();

                try {
                    klaimDataCenterResponses = eklaimBo.kirimKeDataCenterPerSepEklaim(checkup.getNoSep(), unitId);
                } catch (GeneralBOException e) {
                    logger.error("[VerifikatorAction.finalClaim] Error When send data seneter per eklaim", e);
                }

                KlaimDataCenterResponse detailResponse = new KlaimDataCenterResponse();
                if (!klaimDataCenterResponses.isEmpty()) {
                    detailResponse = klaimDataCenterResponses.get(0);

                    if (detailResponse != null) {
                        dataCenterResponse.setBpjsDcStatus(detailResponse.getBpjsDcStatus());
                        dataCenterResponse.setCobDcStatus(detailResponse.getCobDcStatus());
                        dataCenterResponse.setKemkesDcStatus(detailResponse.getKemkesDcStatus());
                        dataCenterResponse.setKemkesDcStatus(detailResponse.getKemkesDcStatus());
                        dataCenterResponse.setSEP(detailResponse.getSEP());
                        dataCenterResponse.setTglPulang(detailResponse.getTglPulang());
                        dataCenterResponse.setStatus(detailResponse.getStatus());
                        dataCenterResponse.setMessage(detailResponse.getMessage());
                    }

                    if ("200".equalsIgnoreCase(detailResponse.getStatus())) {

                        String userLogin = CommonUtil.userLogin();
                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                        HeaderCheckup header = new HeaderCheckup();
                        header.setNoCheckup(checkup.getNoCheckup());
                        header.setLastUpdateWho(userLogin);
                        header.setLastUpdate(updateTime);

                        CheckResponse response = new CheckResponse();

//                        try {
//                            response = verifikatorBo.updateKlaimBpjsFlag(header);
//                        }catch (HibernateException e){
//                            logger.error("[VerifikatorAction.finalClaim] Error When send data seneter per eklaim", e);
//                        }

                        if (response != null) {
                            dataCenterResponse.setStatus(response.getStatus());
                            dataCenterResponse.setMessage(response.getMessage());
                        }
                    }
                }
            }
        }

        logger.info("[VerifikatorAction.sendClaimOnline] END process <<<");
        return dataCenterResponse;
    }

    public String printFinalClaim() {

        String id = getId();
        String jk = "";
        BigDecimal ppnObat = new BigDecimal(String.valueOf(0));
        Boolean isRawatInap = false;

        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
        HeaderCheckup checkup = new HeaderCheckup();

        if (!"".equalsIgnoreCase(id) && id != null) {

            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data detail pasien " + e.getMessage());
            }

            if (checkup != null) {

                try {
                    riwayatTindakanList = verifikatorBoProxy.getListTindakanApprove(id);
                } catch (GeneralBOException e) {
                    logger.error("[TransaksiObatAction.pembelianObat] ERROR when search list obat, ", e);
                    addActionError("[TransaksiObatAction.pembelianObat] ERROR when search list obat, " + e.getMessage());
                }

                if (riwayatTindakanList.size() > 0) {
                    for (RiwayatTindakan riwayatTindakan : riwayatTindakanList) {
                        if ("resep".equalsIgnoreCase(riwayatTindakan.getKeterangan())) {
                            BigDecimal ppn = (riwayatTindakan.getTotalTarif().multiply(new BigDecimal(0.1))).setScale(2, BigDecimal.ROUND_HALF_UP);
                            ppnObat = ppnObat.add(ppn);
                        }
                        if("kamar".equalsIgnoreCase(riwayatTindakan.getKeterangan())){
                            isRawatInap = true;
                        }
                    }
                }

                if(isRawatInap){
                    ppnObat = new BigDecimal(String.valueOf(0));
                }

                JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(riwayatTindakanList);

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

            reportParams.put("idDetailCheckup", id);
            reportParams.put("ppnObat", ppnObat);
            reportParams.put("sep", checkup.getNoSep());
            reportParams.put("petugas", CommonUtil.userLogin());
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

        return "print_final_claim";
    }

    public List<ImSimrsKategoriTindakanInaEntity> getListKategoriTindakanBpjs() {
        List<ImSimrsKategoriTindakanInaEntity> katTindakanBpjsList = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorBo verifikatorBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");

        katTindakanBpjsList = verifikatorBo.getAllKatTindakanInaList();

        return katTindakanBpjsList;
    }

    public String searchVerif() {
        logger.info("[VerifikatorAction.search] START process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();
        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());

        try {
            listOfsearchHeaderDetailCheckup = verifikatorBoProxy.getListVerifTransaksi(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[VerifikatorAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[VerifikatorAction.search] END process <<<");
        return "search";
    }

    private CheckResponse cekAllTindakanRawat(String idDetailCheckup) {
        CheckResponse response = new CheckResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

        String cekTindakan = "Y";
        String cekLab = "Y";
        String cekGizi = "Y";
        String cekResep = "Y";

        List<TindakanRawat> tindakanRawatList = new ArrayList<>();
        TindakanRawat tindakanRawat = new TindakanRawat();
        tindakanRawat.setIdDetailCheckup(idDetailCheckup);

        try {
            tindakanRawatList = tindakanRawatBo.getByCriteria(tindakanRawat);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
            response.setStatus("error");
            response.setMessage("Found Error, " + e.getMessage());
        }

        if (tindakanRawatList.size() > 0) {
            for (TindakanRawat rawat : tindakanRawatList) {
                if (!"Y".equalsIgnoreCase(rawat.getApproveFlag())) {
                    cekTindakan = "N";
                }
            }
        } else {
            cekTindakan = "N";
        }

        List<PeriksaLab> periksaLabList = new ArrayList<>();
        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setIdDetailCheckup(idDetailCheckup);

        try {
            periksaLabList = periksaLabBo.getByCriteria(periksaLab);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
            response.setStatus("error");
            response.setMessage("Found Error, " + e.getMessage());
        }

        if (periksaLabList.size() > 0) {
            for (PeriksaLab lab : periksaLabList) {
                if (!"Y".equalsIgnoreCase(lab.getApproveFlag())) {
                    cekLab = "N";
                }
            }
        }

        List<RawatInap> rawatInapList = new ArrayList<>();
        RawatInap rawatInap = new RawatInap();
        rawatInap.setIdDetailCheckup(idDetailCheckup);

        try {
            rawatInapList = rawatInapBo.getByCriteria(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
            response.setStatus("error");
            response.setMessage("Found Error, " + e.getMessage());
        }

        if (rawatInapList.size() > 0) {

            rawatInap = rawatInapList.get(0);

            if (rawatInap.getIdRawatInap() != null) {

                OrderGizi orderGizi = new OrderGizi();
                orderGizi.setIdRawatInap(rawatInap.getIdRawatInap());
                List<OrderGizi> giziList = new ArrayList<>();

                try {
                    giziList = orderGiziBo.getByCriteria(orderGizi);
                } catch (GeneralBOException e) {
                    logger.error("Found Error, " + e.getMessage());
                    response.setStatus("error");
                    response.setMessage("Found Error, " + e.getMessage());
                }

                if (giziList.size() > 0) {
                    for (OrderGizi gizi : giziList) {
                        if (!"Y".equalsIgnoreCase(gizi.getDiterimaFlag())) {
                            cekGizi = "N";
                        }
                    }
                }
            }
        }

        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        PermintaanResep permintaanResep = new PermintaanResep();
        permintaanResep.setIdDetailCheckup(idDetailCheckup);

        try {
            permintaanResepList = permintaanResepBo.getByCriteria(permintaanResep);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
            response.setStatus("error");
            response.setMessage("Found Error, " + e.getMessage());
        }

        if (permintaanResepList.size() > 0) {
            for (PermintaanResep resep : permintaanResepList) {
                if (!"Y".equalsIgnoreCase(resep.getApproveFlag())) {
                    cekResep = "N";
                }
            }
        }

        if ("Y".equalsIgnoreCase(cekTindakan) && "Y".equalsIgnoreCase(cekLab) && "Y".equalsIgnoreCase(cekGizi) && "Y".equalsIgnoreCase(cekResep)) {
            response.setStatus("success");
            response.setMessage("Berhasil");
        } else {
            String msg = "Traksaksi tidak dapat di close dikarenakan, ";
            if ("N".equalsIgnoreCase(cekTindakan)) {
                msg = msg + " Tindakan tidak boleh kosong, dan silahkan klik tombol Save All Tindakan untuk meverifikasi tindakan yang sudah dilakukan, ";
            }
            if ("N".equalsIgnoreCase(cekLab)) {
                msg = msg + "Tindakan lab atau radiologi belum dilakukan, ";
            }
            if ("N".equalsIgnoreCase(cekGizi)) {
                msg = msg + " Order gizi belum diterima pasien, ";
            }
            if ("N".equalsIgnoreCase(cekResep)) {
                msg = msg + " Order resep belum diambil diapotek";
            }
            response.setStatus("error");
            response.setMessage(msg);
        }

        return response;
    }

    public String saveAddToRiwayatTindakan(String idDetail, String jenisPasien) {
        logger.info("[CheckupDetailAction.saveAddToRiwayatTindakan] START process >>>");
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
            RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");
            String userBranch = CommonUtil.userBranchLogin();

            RekananOps ops = new RekananOps();
            if("rekanan".equalsIgnoreCase(jenisPasien)){
                try {
                    ops = rekananOpsBo.getDetailRekananOpsByDetail(idDetail, userBranch);
                }catch (GeneralBOException e){
                    logger.error("Error, "+e.getMessage());
                }
            }

            String jenPasien = "";
            if ("rekanan".equalsIgnoreCase(jenisPasien) && "Y".equalsIgnoreCase(ops.getIsBpjs())) {
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
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (listTindakan.size() > 0) {
                for (TindakanRawat entity : listTindakan) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdTindakanRawat());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {
                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdTindakanRawat());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan(entity.getNamaTindakan());

                        if (!"".equalsIgnoreCase(idPaket) && idPaket != null) {

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
                        riwayatTindakan.setIdRuangan(entity.getIdRuangan());

                        try {
                            riwayatTindakanBo.saveAdd(riwayatTindakan);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
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
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
            }

            if (periksaLabList.size() > 0) {
                for (PeriksaLab entity : periksaLabList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPeriksaLab());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        PeriksaLab lab = new PeriksaLab();

                        try {
                            lab = periksaLabBo.getTarifTotalPemeriksaan(entity.getIdLab(), entity.getIdPeriksaLab());
                        } catch (HibernateException e) {
                            logger.error("Found Error " + e.getMessage());
                        }

                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdPeriksaLab());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        String namaLab = "";
                        if ("radiologi".equalsIgnoreCase(lab.getKategoriLabName())) {
                            namaLab = "Radiologi";
                        } else {
                            namaLab = "Laboratorium";
                        }
                        riwayatTindakan.setNamaTindakan("Periksa " + namaLab + " " + entity.getLabName());

                        // paket lab
                        if (!"".equalsIgnoreCase(idPaket) && idPaket != null) {

                            // mencari berdasarkan id paket dan id lab
                            ItemPaket itemPaket = riwayatTindakanBo.getTarifPaketLab(idPaket, entity.getIdLab());
                            if (itemPaket != null) {

                                // jika terdapat tarif paket maka menggunakan tarif paket
                                riwayatTindakan.setTotalTarif(itemPaket.getTarif());
                            } else {

                                // jika tidak ada tarif paket menggunakan tarif asli
                                riwayatTindakan.setTotalTarif(lab.getTarif());
                            }
                        } else {

                            // jika bukan paket maka pakai tarif asli
                            if("rekanan".equalsIgnoreCase(jenisPasien)){
                                if(ops.getDiskon() != null){
                                    riwayatTindakan.setTotalTarif(lab.getTarif().multiply(ops.getDiskon()));
                                }else{
                                    riwayatTindakan.setTotalTarif(lab.getTarif());
                                }
                            }else{
                                riwayatTindakan.setTotalTarif(lab.getTarif());
                            }
                        }

                        riwayatTindakan.setKeterangan(lab.getKategoriLabName());
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
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
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
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (resepList.size() > 0) {
                for (PermintaanResep entity : resepList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPermintaanResep());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        TransaksiObatDetail obatDetailList = new TransaksiObatDetail();

                        try {
                            obatDetailList = transaksiObatBo.getTotalHargaResep(entity.getIdPermintaanResep());
                        } catch (HibernateException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                        }

                        if (obatDetailList.getTotalHarga() != null && !"".equalsIgnoreCase(obatDetailList.getTotalHarga().toString())) {
                            RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                            riwayatTindakan.setIdTindakan(entity.getIdPermintaanResep());
                            riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                            riwayatTindakan.setNamaTindakan("Tarif Resep dengan No. Resep " + entity.getIdPermintaanResep());
                            if("rekanan".equalsIgnoreCase(jenisPasien)){
                                if(ops.getDiskon() != null){
                                    riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()).multiply(ops.getDiskon()));
                                }else{
                                    riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()));
                                }
                            }else{
                                riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()));
                            }
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
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
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
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                            }

                            if (riwayatTindakanList.isEmpty()) {

                                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                                riwayatTindakan.setIdTindakan(gizi.getIdOrderGizi());
                                riwayatTindakan.setIdDetailCheckup(rawatInap.getIdDetailCheckup());
                                riwayatTindakan.setNamaTindakan("Tarif Gizi dengan No. Gizi " + gizi.getIdOrderGizi());
                                if("rekanan".equalsIgnoreCase(jenisPasien)){
                                    if(ops.getDiskon() != null){
                                        riwayatTindakan.setTotalTarif(gizi.getTarifTotal().multiply(ops.getDiskon()));
                                    }else{
                                        riwayatTindakan.setTotalTarif(gizi.getTarifTotal());
                                    }
                                }else{
                                    riwayatTindakan.setTotalTarif(gizi.getTarifTotal());
                                }
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
                                    logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
        }

        logger.info("[CheckupDetailAction.saveAddToRiwayatTindakan] END process >>>");
        return SUCCESS;
    }

    public String initVerif() {
        RawatInap rawatInap = new RawatInap();
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        setHeaderDetailCheckup(detailCheckup);
        setRawatInap(rawatInap);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "search";
    }

    public String searchVerifAs() {
        logger.info("[VerifikatorAction.searchVerifCover] START process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();
        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());
        headerDetailCheckup.setIsCover("Y");

        try {
            listOfsearchHeaderDetailCheckup = verifikatorBoProxy.getListVerifTransaksi(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[VerifikatorAction.searchVerifCover] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[VerifikatorAction.searchVerifCover] END process <<<");
        return "search";
    }

    public CrudResponse updateCoverAsuransi(String data, String dataDetail) {
        logger.info("[VerifikatorAction.updateCoverAsuransi] START process <<<");
        CrudResponse response = new CrudResponse();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String user = CommonUtil.userLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorBo verifikatorBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");
        try {
            List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            JSONObject object = new JSONObject(dataDetail);
            JSONArray json = new JSONArray(data);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                riwayatTindakan.setIdRiwayatTindakan(obj.getString("id_riwayat_tindakan"));
                riwayatTindakan.setJenisPasien(obj.getString("jenis_pasien"));
                riwayatTindakanList.add(riwayatTindakan);
            }
            if (object != null) {
                String idDetailCheckup = object.getString("id_detail_checkup");
                String idPelayanan = object.getString("id_pelayanan");
                String idPasien = object.getString("id_pasien");

                detailCheckup.setIdDetailCheckup(idDetailCheckup);
                detailCheckup.setLastUpdateWho(user);
                detailCheckup.setLastUpdate(updateTime);
                if (object.getString("cover_biaya") != null && !"".equalsIgnoreCase(object.getString("cover_biaya"))) {
                    detailCheckup.setCoverBiaya(new BigDecimal(object.getString("cover_biaya")));
                } else {
                    response.setStatus("error");
                    response.setMsg("Found Error, Cover Biaya tidak boleh null & kosong...!");
                    return response;
                }
                if (object.getString("ditanggung_pasien") != null && !"".equalsIgnoreCase(object.getString("ditanggung_pasien"))) {
                    detailCheckup.setPasienBayar(new BigDecimal((object.getString("ditanggung_pasien"))));
                } else {
                    response.setStatus("error");
                    response.setMsg("Found Error, Biaya Ditanggung Pasien tidak boleh null & kosong...!");
                    return response;
                }
                if (riwayatTindakanList.size() > 0) {
                    response = verifikatorBo.updateCoverAsuransi(riwayatTindakanList, detailCheckup);
                    if ("success".equalsIgnoreCase(response.getStatus())) {
                        JurnalResponse jurnalResponse = closingJurnalNonTunai(idDetailCheckup, idPelayanan, idPasien, "");
                        if (!"ptpn".equalsIgnoreCase(jurnalResponse.getStatus())) {
                            if ("error".equalsIgnoreCase(jurnalResponse.getStatus())) {
                                response.setStatus("error");
                                response.setMsg(jurnalResponse.getMsg());
                                return response;
                            } else if (!"".equalsIgnoreCase(jurnalResponse.getInvoice())) {
                                detailCheckup.setInvoice(jurnalResponse.getInvoice());
                                detailCheckup.setFlagCover("Y");
                                response = verifikatorBo.updateInvoice(detailCheckup);
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Found Error. " + e.getMessage());
        }
        logger.info("[VerifikatorAction.updateCoverAsuransi] END process <<<");
        return response;
    }

    private JurnalResponse closingJurnalNonTunai(String idDetailCheckup, String idPoli, String idPasien, String noCheckup) {

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
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");
        MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");

        String kode = "";
        String transId = "";
        String ketPoli = "";
        String ketResep = "";
        String divisiId = "";
        String masterId = "";
        String jenisPasien = "Umum ";
        String divisiResep = "";
        String noKartu = "";
        String idJenisPeriksaPasien = "";
        BigDecimal biayaCover = new BigDecimal(0);
        boolean isNoCheckup = noCheckup != null && !"".equalsIgnoreCase(noCheckup);
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
        if (detailCheckupEntity != null) {
            idJenisPeriksaPasien = detailCheckupEntity.getIdJenisPeriksaPasien();
        }
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

                String masterPerusahaan = "";
                if ("paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                    ItSimrsPaketPasienEntity paketPasienEntity = paketPeriksaBo.getPaketPasienEntityByIdPaket(detailCheckupEntity.getIdPaket(), idPasien);
                    if (paketPasienEntity != null) {
                        masterPerusahaan = paketPasienEntity.getIdPerusahaan();
                    }
                }

                ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());
                if (pelayananEntity != null) {

                    // MENDAPATKAN DIVISI ID TINDAKAN / PENDAPATAN RAWAT;
                    divisiId = getDivisiId(idDetailCheckup, "", "", "");

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
                            if (!"umum".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
                                if ("R".equalsIgnoreCase(detailCheckup.getFlagRefund())) {
                                    response.setStatus("error");
                                    response.setMsg("Silahkan lakukan Refund Uang Muka Pasien terlebih dahulu...!");
                                    return response;
                                }
                            }
                            jumlahUm = new BigDecimal(detailCheckup.getJumlahUangMukaDibayar());
                            idUm = detailCheckup.getNoUangMuka();
                        }
                        if (!"umum".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
                            jumlahUm = new BigDecimal(0);
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
//                    mapPajakObat.put("bukti", invoice);
                    mapPajakObat.put("nilai", ppnObat);
                    mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

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

                        // jika rawat inap
                        if ("JRI".equalsIgnoreCase(kode)){
                            for (String keterangan : listOfKeteranganRiwayat) {
                                if ("kamar".equalsIgnoreCase(keterangan) || "tindakan".equalsIgnoreCase(keterangan)){

                                    // mencari list ruangan
                                    List<String> listRuangan = riwayatTindakanBo.getListRuanganRiwayatTindakan(idDetailCheckup, keterangan);
                                    if (listRuangan.size() > 0){
                                        for (String ruangan : listRuangan){
                                            Map mapTindakan = new HashMap();
                                            mapTindakan.put("master_id", masterId);
                                            mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ruangan));
                                            mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ruangan));
                                            mapTindakan.put("activity", getAcitivityList(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode, ruangan));
                                            listOfTindakan.add(mapTindakan);
                                        }
                                    }
                                } else {
                                    Map mapTindakan = new HashMap();
                                    mapTindakan.put("master_id", masterId);
                                    mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ""));
                                    mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan,""));
                                    mapTindakan.put("activity", getAcitivityList(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode, ""));
                                    listOfTindakan.add(mapTindakan);
                                }
                            }
                        } else {

                            if (isNoCheckup){
                                List<String> listIdDetailCheckup = riwayatTindakanBo.getListIdDetailCheckup(noCheckup);
                                if (listIdDetailCheckup.size() > 0){
                                    for (String idDetail : listIdDetailCheckup){

                                        List<String> listRiwayatTindakan = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetail);
                                        if (listOfKeteranganRiwayat.size() > 0){

                                            for (String keterangan : listRiwayatTindakan){
                                                Map mapTindakan = new HashMap();
                                                mapTindakan.put("master_id", masterId);
                                                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ""));
                                                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan,""));
                                                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode, ""));
                                                listOfTindakan.add(mapTindakan);

                                            }
                                        }
                                    }
                                }
                            } else {

                                // jika rawat jalan
                                for (String keterangan : listOfKeteranganRiwayat) {
                                    Map mapTindakan = new HashMap();
                                    mapTindakan.put("master_id", masterId);
                                    mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ""));
                                    mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan,""));
                                    mapTindakan.put("activity", getAcitivityList(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode, ""));
                                    listOfTindakan.add(mapTindakan);
                                }
                            }
                        }
                    }

                    // MENDAPATKAN SEMUA BIAYA RAWAT;
                    jumlah = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "", "", "");

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

                            if (!"paket_individu".equalsIgnoreCase(idJenisPeriksaPasien) && !"paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                hsCriteria.put("uang_muka", mapUangMuka);
                            }

                            if ("Y".equalsIgnoreCase(isResep)) {

                                // kredit ppn obat umum
                                hsCriteria.put("ppn_keluaran", mapPajakObat);

                                // jika ada resep dan ppn untuk debit piutang
                                jumlah = jumlah.add(ppnObat);

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                if (!"paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    mapPiutang.put("pasien_id", idPasien);
                                } else {
                                    mapPiutang.put("master_id", masterPerusahaan);
                                }

                                // debit piutang pasien
                                hsCriteria.put("piutang_pasien_umum", mapPiutang);

                                if ("paket_individu".equalsIgnoreCase(idJenisPeriksaPasien) || "paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    transId = "62";
                                } else {
                                    transId = "14";
                                }
                            } else {

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                if (!"paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    mapPiutang.put("pasien_id", idPasien);
                                } else {
                                    mapPiutang.put("master_id", masterPerusahaan);
                                }

                                // debit piutang pasien
                                hsCriteria.put("piutang_pasien_umum", mapPiutang);
                                if ("paket_individu".equalsIgnoreCase(idJenisPeriksaPasien) || "paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    transId = "61";
                                } else {
                                    transId = "07";
                                }
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

                            // kredit jumlah tindakan asuransis
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

                    String catatan = "";
                    if ("paket_individu".equalsIgnoreCase(idJenisPeriksaPasien) || "paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {

                        MtSimrsPaketEntity paketEntity = paketPeriksaBo.getPaketEntityById(detailCheckupEntity.getIdPaket());
                        String namaPaket = "";
                        if (paketEntity != null) {
                            namaPaket = paketEntity.getNamaPaket() + " ";
                        }

                        // if paket perusahaan
                        if ("paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {

                            String namaPerusahaan = "";
                            ImMasterEntity masterEntity = masterBo.getEntityMasterById(masterPerusahaan);
                            if (masterEntity != null) {
                                namaPerusahaan = masterEntity.getNama();
                            }

                            catatan = "Closing Pasien Paket " + namaPaket + ketResep + " " + namaPerusahaan + " No.Detail Checkup " + idDetailCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;
                        } else {
                            catatan = "Closing Pasien Paket " + namaPaket + ketResep + "No.Detail Checkup " + idDetailCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;
                        }

                    } else {
                        catatan = "Closing Pasien " + ketPoli + jenisPasien + ketResep + "No.Detail Checkup " + idDetailCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;
                    }

                    try {
                        Jurnal noJurnal = billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
                        response.setInvoice(noJurnal.getNoJurnal());
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

        return response;
    }

    private String getIdDokter(String idTindakan, String keterangan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

        String idDokter = "";
        if ("tindakan".equalsIgnoreCase(keterangan)){
            ItSimrsTindakanRawatEntity tindakanRawatEntity = tindakanRawatBo.getTindakanRawatEntityById(idTindakan);
            if (tindakanRawatEntity != null){
                idDokter = tindakanRawatEntity.getIdDokter();
            }
        }
        if ("laboratorium".equalsIgnoreCase(keterangan)){
            ItSimrsPeriksaLabEntity periksaLabEntity = periksaLabBo.getPeriksaLabEntityById(idTindakan);
            if (periksaLabEntity != null){
                idDokter = periksaLabEntity.getIdDokter();
            }
        }
        if ("radiologi".equalsIgnoreCase(keterangan)){
            ItSimrsPeriksaRadiologiEntity periksaRadiologiEntity = periksaRadiologiBo.getEntityPeriksaRadiologi(idTindakan);
            if (periksaRadiologiEntity != null){
                idDokter = periksaRadiologiEntity.getIdDokterRadiologi();
            }
        }
        if ("resep".equalsIgnoreCase(keterangan)){
            ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(idTindakan);
            if (permintaanResepEntity != null){
                idDokter = permintaanResepEntity.getIdDokter();
            }
        }
        return idDokter;
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