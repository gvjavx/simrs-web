package com.neurix.simrs.transaksi.tindakanrawat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.KlaimDetailRequest;
import com.neurix.simrs.bpjs.eklaim.model.KlaimDetailResponse;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TindakanRawatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(TindakanRawatAction.class);
    private TindakanRawatBo tindakanRawatBoProxy;
    private TindakanRawat tindakanRawat;
    private TindakanBo tindakanBoProxy;
    private CheckupBo checkupBoProxy;
    private EklaimBo eklaimBoProxy;
    private DokterBo dokterBoProxy;

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    public void setEklaimBoProxy(EklaimBo eklaimBoProxy) {
        this.eklaimBoProxy = eklaimBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TindakanRawatAction.logger = logger;
    }

    public TindakanBo getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public TindakanRawatBo getTindakanRawatBoProxy() {
        return tindakanRawatBoProxy;
    }

    public void setTindakanRawatBoProxy(TindakanRawatBo tindakanRawatBoProxy) {
        this.tindakanRawatBoProxy = tindakanRawatBoProxy;
    }

    public TindakanRawat getTindakanRawat() {
        return tindakanRawat;
    }

    public void setTindakanRawat(TindakanRawat tindakanRawat) {
        this.tindakanRawat = tindakanRawat;
    }

    @Override
    public String add() {
        return "init_add";
    }

    @Override
    public String edit() {
        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {
        return "init_view";
    }

    @Override
    public String save() {
        return "init_add";
    }

    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
        return "search";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public String saveAdd(){
        return "success_add";

    }

    public String getComboJenisPeriksaPasien(){
        return "init_add";
    }

    public String getComboPelayanan(){
        return "init_add";
    }

    public String saveTindakanRawat(String idDetailCheckup, String idTindakan, String idDokter, String idPerawat, BigInteger qty, String jenisTransaksi){
        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);
            tindakanRawat.setIdTindakan(idTindakan);

            List<Tindakan> tindakanList = new ArrayList<>();
            Tindakan tindakan = new Tindakan();
            tindakan.setIdTindakan(idTindakan);
            Tindakan tindakanResult = new Tindakan();

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");

            try {
                tindakanList = tindakanBo.getByCriteria(tindakan);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.saveTindakanRawat] Error when search tarif dan decs tindakan by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            if (!tindakanList.isEmpty()){
                tindakanResult = tindakanList.get(0);
            }

            tindakanRawat.setNamaTindakan(tindakanResult.getTindakan());
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(userLogin);
            tindakanRawat.setQty(qty);

            if ("bpjs".equalsIgnoreCase(jenisTransaksi)){
                tindakanRawat.setTarif(tindakanResult.getTarifBpjs());
            } else {
                tindakanRawat.setTarif(tindakanResult.getTarif());
            }

            tindakanRawat.setTarif(tindakanResult.getTarif());
            tindakanRawat.setTarifTotal(tindakanRawat.getQty().multiply(tindakanRawat.getTarif()));
            tindakanRawat.setCreatedWho(userLogin);
            tindakanRawat.setLastUpdate(updateTime);
            tindakanRawat.setCreatedDate(updateTime);
            tindakanRawat.setLastUpdateWho(userLogin);
            tindakanRawat.setAction("C");
            tindakanRawat.setFlag("Y");

            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

            tindakanRawatBo.saveAdd(tindakanRawat);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    private void saveUpdateTindakanToInaCbg(String noCheckup, String idTindakan, BigInteger tarif) throws GeneralBOException{
        logger.info("[TindakanRawatAction.saveUpdateTindakanToInaCbg] START process >>>");

        List<HeaderCheckup> headerCheckups = new ArrayList<>();
        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");

        try {
            headerCheckups = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e){
            Long logId = null;
            logger.error("[TindakanRawatAction.saveUpdateTindakanToInaCbg] Error when adding item ," + "[" + logId + "] Found problem when get data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when get data, please inform to your admin.\n" + e.getMessage());
        }

        if (headerCheckups.size() > 0){
            HeaderCheckup checkup = headerCheckups.get(0);

            if (!"".equalsIgnoreCase(checkup.getNoSep()) && !"".equalsIgnoreCase(checkup.getNoCheckup())){

                KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                klaimDetailRequest.setNomorSep(checkup.getNoSep());
                klaimDetailRequest.setNomorKartu(checkup.getNoKtp());
                klaimDetailRequest.setTglMasuk(checkup.getCreatedDate().toString());
                klaimDetailRequest.setTglPulang(checkup.getCreatedDate().toString());
                klaimDetailRequest.setJenisRawat("1");
                klaimDetailRequest.setKelasRawat("2");
                klaimDetailRequest.setAdlChronic("");
                klaimDetailRequest.setIcuIndikator("");
                klaimDetailRequest.setIcuLos("");
                klaimDetailRequest.setVentilatorHour("");
                klaimDetailRequest.setUpgradeClassInd("");
                klaimDetailRequest.setUpgradeClassClass("");
                klaimDetailRequest.setUpgradeClassLos("");
                klaimDetailRequest.setAddPaymentPct("");
                klaimDetailRequest.setBirthWeight("0");
                klaimDetailRequest.setDischargeStatus("1");
                klaimDetailRequest.setDiagnosa(checkup.getDiagnosa());
                klaimDetailRequest.setProcedure("");

//                if (!"".equalsIgnoreCase(idTindakan)){
//                    List<Tindakan> tindakans = new ArrayList<>();
//                    try {
//                        tindakans = tind
//                    }
//
//                }

                klaimDetailRequest.setTarifRsNonBedah("");
                klaimDetailRequest.setTarifRsProsedurBedah("");
                klaimDetailRequest.setTarifRsKonsultasi("300000");
                klaimDetailRequest.setTarifRsTenagaAhli("");
                klaimDetailRequest.setTarifRsKeperawatan("");
                klaimDetailRequest.setTarifRsPenunjang("");
                klaimDetailRequest.setTarifRsRadiologi("");
                klaimDetailRequest.setTarifRsLaboratorium("");
                klaimDetailRequest.setTarifRsPelayananDarah("");
                klaimDetailRequest.setTarifRsRehabilitasi("");
                klaimDetailRequest.setTarifRsKamar("");
                klaimDetailRequest.setTarifRsRawatIntensif("");
                klaimDetailRequest.setTarifRsObat("");
                klaimDetailRequest.setTarifRsObatKronis("");
                klaimDetailRequest.setTarifRsObatKemoterapi("");
                klaimDetailRequest.setTarifRsAlkes("");
                klaimDetailRequest.setTarifRsBmhp("");
                klaimDetailRequest.setTarifRsSewaAlat("");
                klaimDetailRequest.setTarifPoliEks("");


                List<Dokter> dokterList = new ArrayList<>();
                Dokter dokter = new Dokter();
                dokter.setIdDokter(checkup.getIdDokter());
                dokter.setFlag("Y");
                try {
                    dokterList = dokterBoProxy.getByCriteria(dokter);
                } catch (GeneralBOException e){
                    Long logId = null;
                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                }

                String namaDokter = "";
                if (dokterList.size() > 0){
                    namaDokter = dokterList.get(0).getNamaDokter();
                }

                klaimDetailRequest.setNamaDokter(namaDokter);
                klaimDetailRequest.setKodeTarif("AP");
                klaimDetailRequest.setTarifRsPayorId("3");
                klaimDetailRequest.setPayorCd("JKN");
                klaimDetailRequest.setCobCd("");
                klaimDetailRequest.setCoderNik("123456");

                KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                try {
                    claimEklaimResponse = eklaimBoProxy.updateDataClaimEklaim(klaimDetailRequest, CommonUtil.userBranchLogin());
                } catch (GeneralBOException e){
                    Long logId = null;
                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                }
            }

        }

        logger.info("[TindakanRawatAction.saveUpdateTindakanToInaCbg] END process <<<");
    }

    private String getKategoriBpjs(String idKategori){
        logger.info("[TindakanRawatAction.getKategoriBpjs] START process >>>");
        String kategoriBpjs = "";

        if ("03".equalsIgnoreCase(idKategori)
                || "01".equalsIgnoreCase(idKategori)
                || "02".equalsIgnoreCase(idKategori)
                || "2".equalsIgnoreCase(idKategori))
        {
          kategoriBpjs = "prosedur_non_bedah";
        }

        logger.info("[TindakanRawatAction.getKategoriBpjs] END process <<<");
        return kategoriBpjs;
    }

    public List<TindakanRawat> listTindakanRawat(String idDetailCheckup){

        logger.info("[TindakanRawatAction.listTindakanRawat] start process >>>");
        List<TindakanRawat> tindakanRawatList = new ArrayList<>();
        TindakanRawat tindakanRawat = new TindakanRawat();
        tindakanRawat.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

        if(!"".equalsIgnoreCase(idDetailCheckup)){
            try {
                tindakanRawatList = tindakanRawatBo.getByCriteria(tindakanRawat);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.listTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
            return tindakanRawatList;

        }else{
            return null;
        }
    }

    public String editTindakanRawat(String idTindakanRawat, String idDetailCheckup, String idTindakan, String idDokter, String idPerawat, BigInteger qty){
        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdTindakanRawat(idTindakanRawat);
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);
            tindakanRawat.setIdTindakan(idTindakan);

            List<Tindakan> tindakanList = new ArrayList<>();
            Tindakan tindakan = new Tindakan();
            tindakan.setIdTindakan(idTindakan);
            Tindakan tindakanResult = new Tindakan();

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");

            try {
                tindakanList = tindakanBo.getByCriteria(tindakan);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.saveTindakanRawat] Error when search tarif dan decs tindakan by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            if (!tindakanList.isEmpty()){
                tindakanResult = tindakanList.get(0);
            }

            tindakanRawat.setNamaTindakan(tindakanResult.getTindakan());
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(userLogin);
            tindakanRawat.setQty(qty);
            tindakanRawat.setTarif(tindakanResult.getTarif());
            tindakanRawat.setLastUpdate(updateTime);
            tindakanRawat.setLastUpdateWho(userLogin);
            tindakanRawat.setAction("U");

            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

            tindakanRawatBo.saveEdit(tindakanRawat);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
}