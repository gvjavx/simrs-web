package com.neurix.simrs.transaksi.verifikator.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.keterangankeluar.bo.KeteranganKeluarBo;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.verifikator.bo.VerifikatorBo;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getSearchRawatJalan(headerDetailCheckup);
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

    public String searchFinalClaim() {
        logger.info("[VerifikatorAction.searchFinalClaim] START process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();
        headerDetailCheckup.setIdJenisPeriksaPasien("bpjs");
        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());

        try {
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getSearchRawatJalan(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[VerifikatorAction.searchFinalClaim] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[VerifikatorAction.searchFinalClaim] END process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
        setHeaderDetailCheckup(headerDetailCheckup);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "search";
    }

    public List<TindakanRawat> getListTindakanRawat(String idDetailCheckup) {
        logger.info("[VerifikatorAction.getListTindakanRawat] START process <<<");

        List<TindakanRawat> result = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
        TindakanRawat tindakanRawat = new TindakanRawat();
        tindakanRawat.setIdDetailCheckup(idDetailCheckup);

        try {
            result = tindakanRawatBo.getByCriteria(tindakanRawat);
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorAction.getListTindakanRawat] Error when get data tindakan rawat ", e);
        }

        logger.info("[VerifikatorAction.getListTindakanRawat] END process <<<");
        return result;
    }

    public CheckResponse updateApproveBpjsFlag(String idTindakan, String kategoriTindakanBpjs) {
        logger.info("[VerifikatorAction.updateApproveBpjsFlag] START process <<<");

        CheckResponse response = new CheckResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorBo verifikatorBo = (VerifikatorBo) ctx.getBean("verifikatorBoProxy");

        TindakanRawat tindakanRawat = new TindakanRawat();
        tindakanRawat.setIdTindakan(idTindakan);
        tindakanRawat.setLastUpdate(time);
        tindakanRawat.setLastUpdateWho(userLogin);
        tindakanRawat.setKategoriTindakanBpjs(kategoriTindakanBpjs);

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
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");


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

                try {
                    headerCheckupList = checkupBo.getByCriteria(headerCheckup);
                } catch (GeneralBOException e) {
                    logger.error("[VerifikatorAction.saveApproveTindakan] Error When Get Header Checkup Data", e);
                }

                HeaderCheckup checkup = new HeaderCheckup();
                if (!headerCheckupList.isEmpty()) {
                    checkup = headerCheckupList.get(0);

                    if (checkup != null) {

                        DataPerKlaimResponse klaimResponse = new DataPerKlaimResponse();

                        //search detail tindakan dari eklaim
                        try {
                            klaimResponse = eklaimBo.detailPerKlaimEklaim(checkup.getNoSep(), unitId);
                        } catch (GeneralBOException e) {
                            logger.error("[VerifikatorAction.saveApproveTindakan] Error When Get Header Checkup Data", e);
                        }

                        List<TindakanRawat> tindakanRawatList = new ArrayList<>();
                        TindakanRawat tindakanRawat = new TindakanRawat();
                        tindakanRawat.setIdDetailCheckup(idDetailCheckup);
                        tindakanRawat.setApproveBpjsFlag("Y");

                        //search tindakan rawat by id detail checkup
                        try {
                            tindakanRawatList = tindakanRawatBo.getByCriteria(tindakanRawat);
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

                        for (TindakanRawat tindakan : tindakanRawatList) {

                            if ("Y".equalsIgnoreCase(tindakan.getApproveBpjsFlag()) && tindakan.getTarif() != null) {

                                if ("prosedur_non_bedah".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsProsedurNonBedah = tarifRsProsedurNonBedah.add(tindakan.getTarifTotal());
                                }
                                if ("tenaga_ahli".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsTenagaAhli = tarifRsTenagaAhli.add(tindakan.getTarifTotal());
                                }
                                if ("radiologi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsRadiologi = tarifRsRadiologi.add(tindakan.getTarifTotal());
                                }
                                if ("rehabilitasi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsRehabilitasi = tarifRsRehabilitasi.add(tindakan.getTarifTotal());
                                }
                                if ("obat".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsObat = tarifRsObat.add(tindakan.getTarifTotal());
                                }
                                if ("alkes".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsAlkes = tarifRsAlkes.add(tindakan.getTarifTotal());

                                }

                                //--------------
                                if ("prosedur_bedah".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsProsedurBedah = tarifRsProsedurBedah.add(tindakan.getTarifTotal());

                                }
                                if ("keperawatan".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsKeperawatan = tarifRsKeperawatan.add(tindakan.getTarifTotal());

                                }
                                if ("laboratorium".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsLaboratorium = tarifRsLaboratorium.add(tindakan.getTarifTotal());

                                }
                                if ("kamar_akomodasi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsKamar = tarifRsKamar.add(tindakan.getTarifTotal());

                                }
                                if ("obat_kronis".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsObatKronis = tarifRsObatKronis.add(tindakan.getTarifTotal());

                                }
                                if ("bmhp".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsBmhp = tarifRsBmhp.add(tindakan.getTarifTotal());

                                }

                                //--------------
                                if ("konsultasi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsKonsultasi = tarifRsKonsultasi.add(tindakan.getTarifTotal());

                                }
                                if ("penunjang".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsPenunjang = tarifRsPenunjang.add(tindakan.getTarifTotal());

                                }
                                if ("pelayanan_darah".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsPelayananDarah = tarifRsPelayananDarah.add(tindakan.getTarifTotal());

                                }
                                if ("rawat_intensif".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsRawatIntensif = tarifRsRawatIntensif.add(tindakan.getTarifTotal());

                                }
                                if ("obat_kemoterapi".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsObatKemoterapi = tarifRsObatKemoterapi.add(tindakan.getTarifTotal());

                                }
                                if ("sewa_alat".equalsIgnoreCase(tindakan.getKategoriTindakanBpjs())) {
                                    tarifRsSewaAlat = tarifRsSewaAlat.add(tindakan.getTarifTotal());

                                }

                            }
                        }

                        KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                        long millis = System.currentTimeMillis();
                        java.util.Date date = new java.util.Date(millis);
                        String tglMasuk = new SimpleDateFormat("yyyy-MM-dd").format(checkup.getCreatedDate());
                        String tglKeluar = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        klaimDetailRequest.setTglMasuk(tglMasuk);
                        klaimDetailRequest.setTglPulang(tglKeluar);
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
                        klaimDetailRequest.setCoderNik("123456");

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
                                    grouping1Response = eklaimBo.groupingStage1Eklaim(checkup.getNoSep(), unitId);
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
                                            grouping2Response = eklaimBo.groupingStage2Eklaim(checkup.getNoSep(), specialCmgResponse.getCode(), unitId);
                                        } catch (GeneralBOException e) {
                                            logger.error("[CheckupAction.saveAdd] Error when adding item ,Found problem when saving add data, please inform to your admin.", e);
                                            response.setStatus("error");
                                            response.setMessage("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        logger.info("[VerifikatorAction.saveApproveTindakan] END process <<<");
        return response;
    }

    public CheckResponse finalClaim(String noCheckup) {
        logger.info("[VerifikatorAction.finalClaim] START process <<<");

        CheckResponse response = new CheckResponse();

        String unitId = CommonUtil.userBranchLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();

        try {
            headerCheckupList = checkupBo.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorAction.finalClaim] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup checkup = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()) {
            checkup = headerCheckupList.get(0);
            if (checkup != null) {

                try {
                    response = eklaimBo.finalisasiClaimEklaim(checkup.getNoSep(), "123456", unitId);
                } catch (GeneralBOException e) {
                    logger.error("[VerifikatorAction.finalClaim] Error When final claim", e);
                }
            }
        }

        logger.info("[VerifikatorAction.finalClaim] END process <<<");
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

                        try {
                            response = verifikatorBo.updateKlaimBpjsFlag(header);
                        }catch (HibernateException e){
                            logger.error("[VerifikatorAction.finalClaim] Error When send data seneter per eklaim", e);
                        }

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

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}