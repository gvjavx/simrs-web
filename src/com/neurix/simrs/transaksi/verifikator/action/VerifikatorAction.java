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
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
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
import com.neurix.simrs.transaksi.verifikator.bo.VerifikatorBo;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
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

    public List<RiwayatTindakan> getListTindakanRawat(String noCheckup, String idDetail) {
        logger.info("[VerifikatorAction.getListTindakanRawat] START process <<<");

        List<RiwayatTindakan> result = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorBo riwayatTindakanBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");

        RiwayatTindakan tindakanRawat = new RiwayatTindakan();
        tindakanRawat.setNoCheckup(noCheckup);
        tindakanRawat.setIdDetailCheckup(idDetail);
        tindakanRawat.setBranchId(CommonUtil.userBranchLogin());

        try {
            result = riwayatTindakanBo.getListAllTindakan(tindakanRawat);
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorAction.getListTindakanRawat] Error when get data tindakan rawat ", e);
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

    public CheckResponse saveApproveTindakan(String idDetailCheckup) {
        logger.info("[VerifikatorAction.saveApproveTindakan] START process <<<");

        CheckResponse response = new CheckResponse();

        String unitId = CommonUtil.userBranchLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        VerifikatorBo verifikatorBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");


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

                                for (RiwayatTindakan riwayatTindakan: tindakanRawatList){
                                    if("kamar".equalsIgnoreCase(riwayatTindakan.getKeterangan())){
                                        isRawatInap = true;
                                    }
                                }

                                for (RiwayatTindakan tindakan : tindakanRawatList) {

                                    BigDecimal ppnObat = new BigDecimal(String.valueOf(0));
                                    BigInteger tarifTotal = tindakan.getTotalTarif().toBigInteger();

                                    if("resep".equalsIgnoreCase(tindakan.getKeterangan())){
                                        if(isRawatInap){
                                            tarifTotal = tarifTotal;
                                        }else{
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
                                    String userLogin = CommonUtil.userLogin();
                                    Timestamp time = new Timestamp(System.currentTimeMillis());
                                    RiwayatTindakan riwayat = new RiwayatTindakan();
                                    riwayat.setIdRiwayatTindakan(tindakan.getIdRiwayatTindakan());
                                    riwayat.setLastUpdateWho(userLogin);
                                    riwayat.setLastUpdate(time);

                                    try {
                                        response1 = verifikatorBo.updateFlagKlaim(riwayat);
                                    }catch (GeneralBOException e){
                                        logger.error("[VerifikatorAction] Found error when update riwayat tindakan, "+e);
                                    }
                                }

                                Timestamp time = new Timestamp(System.currentTimeMillis());
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

                            }else{
                              response.setStatus("error");
                              response.setMessage("Coder Nik Petugas Bpjs Tidak ada..!");
                            }
                        }
                    }
                }
            }
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
            if ("success".equalsIgnoreCase(jurnalResponse.getStatus())){

                // update invoice to detailcheckup
                CheckResponse updateResponse = updateDetailCheckupInvoice(idDetailCheckup, jurnalResponse.getInvoice());
                if ("error".equalsIgnoreCase(updateResponse.getStatus())){
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
                } catch (GeneralBOException e){
                    logger.error("[VerifikatorAction.finalClaim] ERROR when save no jurnal, ");
                    response.setStatus("error");
                    response.setMessage("[VerifikatorAction.finalClaim] ERROR when save no jurnal, ");
                    return response;
                }
            }

        }
        return response;
    }

    private JurnalResponse closingJurnalPbjs(String idDetailCheckup, String typePelayanan, String branchId){

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
        if ("ptpn".equalsIgnoreCase(headerDetailCheckupEntity.getIdJenisPeriksaPasien())){

            // FILTER JIKA JENIS PASIEN ADALAH PTPN
            response = closingPasienPtpnBpjs(idDetailCheckup, branchId);
        } else {
            Map hsCriteria = new HashMap();

            // MENDAPATKAN TINDAKAN TRANSITORIS
            boolean isTransitoris = false;
            BigDecimal allTindakanTransBpjs = new BigDecimal(0);
            if (headerDetailCheckupEntity.getNoJurnalTrans() != null && !"".equalsIgnoreCase(headerDetailCheckupEntity.getNoJurnalTrans())){

                // for bpjs;
                allTindakanTransBpjs = checkupDetailBo.getSumJumlajTindakanTransitorisByJenis(idDetailCheckup, "bpjs","");

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
            if (!"".equalsIgnoreCase(typePelayanan) && typePelayanan != null){
                if ("rawat_jalan".equalsIgnoreCase(typePelayanan) || "igd".equalsIgnoreCase(typePelayanan)){
                    kode = "JRJ"; ketPoli = "Rawat Jalan";
                }
                if ("rawat_inap".equalsIgnoreCase(typePelayanan)){
                    kode = "JRI"; ketPoli = "Rawat Inap";
                }
            }

            List<Map> listOfMapTindakanBpjs = new ArrayList<>();
            List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
            if (listOfKeteranganRiwayat.size() > 0){

                for (String keterangan : listOfKeteranganRiwayat){
                    Map mapTindakan = new HashMap();
                    mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                    mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "bpjs", keterangan));
                    mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", keterangan));
                    mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "bpjs", keterangan, kode));
                    listOfMapTindakanBpjs.add(mapTindakan);
                }
            }

            BigDecimal jumlah = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "");

            // MENDAPATKAN SEMUA NILAI TINDAKAN DAN RESEP
            String isResep = checkupDetailBo.findResep(idDetailCheckup);
            // CREATE JURNAL OBAT JIKA RESEP / RAWAT INAP
            if ("Y".equalsIgnoreCase(isResep) || "rawat_inap".equalsIgnoreCase(typePelayanan)) {

                // hitung ppn
                BigDecimal jmlResep = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "resep");
                BigDecimal ppn = jmlResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);

                // KREDIT PPN
                // JIKA RAWAT INAP TIDAK MENYERTAKAN PPN;
                Map mapPPN = new HashMap();
                mapPPN.put("bukti", billingSystemBo.createInvoiceNumber(kode, branchId));
                mapPPN.put("nilai", ppn);
                mapPPN.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                if (!"rawat_inap".equalsIgnoreCase(typePelayanan)){
                    hsCriteria.put("ppn_keluaran", mapPPN);
                }

                // JUMLAH SEMUA PENDAPATAN;
                // JIKA RAWAT INAP TIDAK MENYERTAKAN PPN;
                if ("rawat_inap".equalsIgnoreCase(typePelayanan)){
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
            mapPiutang.put("bukti", headerDetailCheckupEntity.getNoSep());
            mapPiutang.put("nilai", jumlah );
            mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));

            // JIKA RAWAT JALAN
            if ("JRJ".equalsIgnoreCase(kode)){
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

                if (isTransitoris){
                    transId = "33";
                    ketPoli = "Rawat Inap Terhadap Transitoris";
                } else {
                    transId = "20";
                    ketPoli = "Rawat Inap";
                }
            }

            String catatan = "Closing Pasien "+ketPoli+" BPJS "+ketObat+". No Detail Checkup "+idDetailCheckup+". No Pasien "+idPasien;

            String noJurnal = "";
            try {
                Jurnal jurnal = billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
                noJurnal = jurnal.getNoJurnal();
                response.setStatus("success");
                response.setInvoice(invNumber);
                response.setNoJurnal(noJurnal);
            } catch (GeneralBOException e){
                logger.error("[VerifikatorAction.closingJurnalPbjs] Error When send data seneter per eklaim", e);
                response.setStatus("error");
                response.setMsg("[VerifikatorAction.closingJurnalPbjs] Error When send data seneter per eklaim "+ e);
                return response;
            }
        }

        logger.info("[VerifikatorAction.finalClaim] END process <<<");
        return response;
    }

    private BigDecimal hitungPPN(BigDecimal harga){
        BigDecimal jumlah = new BigDecimal(0);
        if (harga != null){
            jumlah = harga.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return jumlah;
    }

    private JurnalResponse closingPasienPtpnBpjs(String idDetailCheckup, String branchId){

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
            company = " "+ masterEntity.getNama()+ " ";
        } else {
            logger.error("[VerivikatorAction.closingPasienPtpnBpjs] Error Master PTPN tidak ditemukan");
            response.setStatus("error");
            response.setMsg("[VerivikatorAction.closingPasienPtpnBpjs] Error Master PTPN tidak ditemukan");
            return response;
        }

        // MENDAPATKAN SEMUA NILAI TINDAKAN DAN RESEP
        String isResep = "N";
        BigDecimal jumlahResep = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, "", "resep");
        BigDecimal jumlahAllTindakan = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "", "");
        BigDecimal ppnObat = new BigDecimal(0);
        if (jumlahResep.compareTo(new BigDecimal(0)) == 1) {
            isResep = "Y";
            ppnObat = jumlahResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        // BANDINGKAN SEMUA TINDAKAN TERHADAP COVER UNTUK MENENTUKAN MURNI/NON MURNI BPJS
        if (jumlahAllTindakan.compareTo(biayaCover) == 1){
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
        if (detailCheckupEntity.getNoJurnalTrans() != null && !"".equalsIgnoreCase(detailCheckupEntity.getNoJurnalTrans())){

            // for bpjs;
            allTindakanTransBpjs = checkupDetailBo.getSumJumlajTindakanTransitorisByJenis(idDetailCheckup, "bpjs","");

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
        if (listOfKeteranganRiwayat.size() > 0){

            for (String keterangan : listOfKeteranganRiwayat){
                Map mapTindakan = new HashMap();
                mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "bpjs", keterangan));
                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", keterangan));
                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "bpjs", keterangan, kode));
                listOfMapTindakanBpjs.add(mapTindakan);
            }
            for (String keterangan : listOfKeteranganRiwayat){
                Map mapTindakan = new HashMap();
                mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "ptpn"));
                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "ptpn", keterangan));
                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "ptpn", keterangan));
                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "ptpn", keterangan, kode));
                listOfMapTindakanPtpn.add(mapTindakan);
            }
        }

        String noInvoicePtpnMurni = billingSystemBo.createInvoiceNumber(kode, branchId);
        if ("murni".equalsIgnoreCase(jenisPtpn)){

            //** BPJS MURNI **//

            // PENGURANGAN OLEH TRANSITORIS
            Map mapPajakObat = new HashMap();
            mapPajakObat.put("bukti", invoice);
            mapPajakObat.put("nilai", ppnObat);
            mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

            if ("JRJ".equalsIgnoreCase(kode)){

                // kredit jumlah tindakan PTPN
                hsCriteria.put("pendapatan_rawat_jalan_bpjs", listOfMapTindakanBpjs);

                if ("Y".equalsIgnoreCase(isResep)){

                    // ppn obat PTPN
                    hsCriteria.put("ppn_keluaran", mapPajakObat);

                    // reate map piutang
                    Map mapPiutang = new HashMap();
                    mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "").add(ppnObat));
                    mapPiutang.put("bukti", detailCheckupEntity.getNoSep());
                    mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));

                    // debit piutang pasien PTPN
                    hsCriteria.put("piutang_pasien_bpjs", mapPiutang);
                    transId = "16";
                    jenisPasien = " Murni Dengan Obat ";
                } else {

                    // create map piutang
                    Map mapPiutang = new HashMap();
                    mapPiutang.put("bukti", detailCheckupEntity.getNoSep());
                    mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", ""));
                    mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
hsCriteria.put("piutang_pasien_bpjs", mapPiutang);
                    // debit piutang pasien PTPN
                    hsCriteria.put("piutang_pasien_bpjs", mapPiutang);
                    transId = "08";
                    jenisPasien = " Murni Tanpa Obat ";
                }
            }

            if ("JRI".equalsIgnoreCase(kode)){

                // kredit jumlah tindakan PTPN
                hsCriteria.put("pendapatan_rawat_inap_bpjs", listOfMapTindakanBpjs);

                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", noInvoicePtpnMurni);
                mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", ""));
                mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));

                // debit piutang pasien PTPN
                hsCriteria.put("piutang_pasien_bpjs", mapPiutang);

                if (isTransitoris){
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
            if ("JRJ".equalsIgnoreCase(kode)){

                // PENGURANGAN OLEH TRANSITORIS
                Map mapPajakObat = new HashMap();
                mapPajakObat.put("bukti", invoice);
                mapPajakObat.put("nilai", ppnObat);
                mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                Map mapPiutangBpjs = new HashMap();
                mapPiutangBpjs.put("bukti", detailCheckupEntity.getNoSep());
                mapPiutangBpjs.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                mapPiutangBpjs.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", ""));

                Map mapPiutangPtpn = new HashMap();
                mapPiutangPtpn.put("bukti", billingSystemBo.createInvoiceNumber(kode, branchId));
                mapPiutangPtpn.put("master_id", getMasterIdByTipe(idDetailCheckup, "ptpn"));
                mapPiutangPtpn.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "ptpn", "").add(ppnObat));

                if ("Y".equalsIgnoreCase(isResep)){

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

            if ("JRI".equalsIgnoreCase(kode)){

                Map mapPiutangBpjs = new HashMap();
                mapPiutangBpjs.put("bukti", detailCheckupEntity.getNoSep());
                mapPiutangBpjs.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));
                mapPiutangBpjs.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "bpjs", "").add(allTindakanTransBpjs));

                Map mapPiutangPtpn = new HashMap();
                mapPiutangPtpn.put("bukti", billingSystemBo.createInvoiceNumber(kode, branchId));
                mapPiutangPtpn.put("master_id", getMasterIdByTipe(idDetailCheckup, "ptpn"));
                mapPiutangPtpn.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "ptpn", "").add(allTindakanTransPtpn));

                hsCriteria.put("piutang_pasien_bpjs", mapPiutangBpjs);
                hsCriteria.put("piutang_pasien_ptpn", mapPiutangPtpn);
                hsCriteria.put("pendapatan_rawat_inap_bpjs", listOfMapTindakanBpjs);
                hsCriteria.put("pendapatan_rawat_inap_ptpn", listOfMapTindakanPtpn);

                if (isTransitoris){
                    jenisPasien = jenisPasien + "Terhadap Transitoris";
                    transId = "42";
                } else {
                    transId = "25";
                }
            }
        }

        String catatan = "Closing" + ketPoli + company + jenisPasien + " No Detail Checkup "+ idDetailCheckup + " No. RM "+ headerChekupEntity.getIdPasien() + noKartu ;
        String noJurnal = "";
        try {
            Jurnal jurnal = billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
            noJurnal = jurnal.getNoJurnal();
            response.setStatus("success");
            response.setNoJurnal(noJurnal);
            response.setInvoice(noInvoicePtpnMurni);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorAction.closingPasienPtpnBpjs] ERROR Create Jurnal. ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorAction.closingPasienPtpnBpjs] ERROR Create Jurnal. "+ e);
            return response;
        }

        return response;
    }

    private BigDecimal getJumlahNilaiBiayaByKeterangan(String idDetailCheckup, String jenisPasien, String keterangan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        BigDecimal nilai = new BigDecimal(0);
        try {
            nilai = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, keterangan);
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
            throw new GeneralBOException("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);

        }
        return nilai;
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

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
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

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
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

    private String getMasterIdByTipe(String idDetailCheckup, String jenis){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
        String masterId = "";
        if ("bpjs".equalsIgnoreCase(jenis)){

            ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById(jenis);
            if (jenisPeriksaPasienEntity != null){
                masterId = jenisPeriksaPasienEntity.getMasterId();
            }
        } else if ("asuransi".equalsIgnoreCase(jenis)){
            // jika asuransi
            ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
            if (asuransiEntity != null){
                masterId = asuransiEntity.getNoMaster();
            } else {
            }

        } else if ("ptpn".equalsIgnoreCase(jenis)){
            masterId =  detailCheckupEntity.getIdAsuransi();
        } else {
            ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById("umum");
            if (jenisPeriksaPasienEntity != null){
                masterId = jenisPeriksaPasienEntity.getMasterId();
            }
        }

        return masterId;
    }

    private CheckResponse updateDetailCheckupInvoice(String idDetailCheckup, String invNumber){
        CheckResponse response = new CheckResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        try {
            response = checkupDetailBo.updateInvoiceBpjs(idDetailCheckup, invNumber);
            response.setStatus("200");
        } catch (GeneralBOException e){
            logger.error("[VerifikatorAction.updateDetailCheckupInvoice] Error ", e);
            response.setStatus("error");
            response.setMessage("[VerifikatorAction.updateDetailCheckupInvoice] Error "+ e);
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

                    if("200".equalsIgnoreCase(detailResponse.getStatus())){

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

                        if(response != null){
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

                if(riwayatTindakanList.size()>0){
                    for (RiwayatTindakan riwayatTindakan: riwayatTindakanList){
                        if("resep".equalsIgnoreCase(riwayatTindakan.getKeterangan())){
                            BigDecimal ppn = (riwayatTindakan.getTotalTarif().multiply(new BigDecimal(0.1))).setScale(2, BigDecimal.ROUND_HALF_UP);
                            ppnObat = ppnObat.add(ppn);
                        }
                    }
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

    public List<ImSimrsKategoriTindakanInaEntity> getListKategoriTindakanBpjs(){
        List<ImSimrsKategoriTindakanInaEntity> katTindakanBpjsList = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorBo verifikatorBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");

        katTindakanBpjsList = verifikatorBo.getAllKatTindakanInaList();

        return katTindakanBpjsList;
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