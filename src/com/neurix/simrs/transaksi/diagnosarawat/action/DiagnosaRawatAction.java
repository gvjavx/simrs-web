package com.neurix.simrs.transaksi.diagnosarawat.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.profilrekammedisrj.bo.RekamMedisRawatJalanBo;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.RekamMedisRawatJalan;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.verifikator.bo.VerifikatorBo;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiagnosaRawatAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(DiagnosaRawatAction.class);
    private DiagnosaRawatBo diagnosaRawatBoProxy;
    private DiagnosaRawat diagnosaRawat;
    private DiagnosaBo diagnosaBoProxy;

    private List<Diagnosa> listOfComboDiagnosa = new ArrayList<>();

    public List<Diagnosa> getListOfComboDiagnosa() {
        return listOfComboDiagnosa;
    }

    public void setListOfComboDiagnosa(List<Diagnosa> listOfComboDiagnosa) {
        this.listOfComboDiagnosa = listOfComboDiagnosa;
    }

    public DiagnosaBo getDiagnosaBoProxy() {
        return diagnosaBoProxy;
    }

    public void setDiagnosaBoProxy(DiagnosaBo diagnosaBoProxy) {
        this.diagnosaBoProxy = diagnosaBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DiagnosaRawatAction.logger = logger;
    }

    public DiagnosaRawatBo getDiagnosaRawatBoProxy() {
        return diagnosaRawatBoProxy;
    }

    public void setDiagnosaRawatBoProxy(DiagnosaRawatBo diagnosaRawatBoProxy) {
        this.diagnosaRawatBoProxy = diagnosaRawatBoProxy;
    }

    public DiagnosaRawat getDiagnosaRawat() {
        return diagnosaRawat;
    }

    public void setDiagnosaRawat(DiagnosaRawat diagnosaRawat) {
        this.diagnosaRawat = diagnosaRawat;
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
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public CrudResponse saveDiagnosa(String idDetailCheckup, String idDiagnosa, String jenisDiagnosa, String ketDiagnosa, String jenisPasien) {
        logger.info("[DiagnosaRawatAction.saveDiagnosa] start process >>>");
        CrudResponse response = new CrudResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");
            DiagnosaBo diagnosaBo = (DiagnosaBo) ctx.getBean("diagnosaBoProxy");

            DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
            diagnosaRawat.setIdDetailCheckup(idDetailCheckup);
            diagnosaRawat.setIdDiagnosa(idDiagnosa);
            diagnosaRawat.setKeteranganDiagnosa(ketDiagnosa);
            diagnosaRawat.setJenisDiagnosa(jenisDiagnosa);
            diagnosaRawat.setCreatedWho(userLogin);
            diagnosaRawat.setLastUpdate(updateTime);
            diagnosaRawat.setCreatedDate(updateTime);
            diagnosaRawat.setLastUpdateWho(userLogin);
            diagnosaRawat.setAction("C");
            diagnosaRawat.setFlag("Y");

            if("diagnosa_utama".equalsIgnoreCase(jenisDiagnosa) || "diagnosa_awal".equalsIgnoreCase(jenisDiagnosa)){
                DiagnosaRawat dr = new DiagnosaRawat();
                dr.setIdDetailCheckup(idDetailCheckup);
                dr.setJenisDiagnosa(jenisDiagnosa);
                Boolean cek = diagnosaRawatBo.cekDiagnosa(dr);
                if(cek){
                    response.setStatus("error");
                    response.setMsg("Data "+jenisDiagnosa.replace("_", " ").toUpperCase()+" sudah ada ...!");
                }else{
                    if ("bpjs".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                        response = updateCoverBpjs(idDetailCheckup, idDiagnosa);
                        if ("success".equalsIgnoreCase(response.getStatus())) {
                            response = diagnosaRawatBo.saveAdd(diagnosaRawat);
                        }else{
                            response.setStatus("error");
                            response.setMsg("Found Error When "+response.getMsg());
                        }
                    } else {
                        response = diagnosaRawatBo.saveAdd(diagnosaRawat);
                    }
                }
            }else{
                if ("bpjs".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                    response = updateCoverBpjs(idDetailCheckup, idDiagnosa);
                    if ("success".equalsIgnoreCase(response.getStatus())) {
                        response = diagnosaRawatBo.saveAdd(diagnosaRawat);
                    }else{
                        response.setStatus("error");
                        response.setMsg("Found Error When "+response.getMsg());
                    }
                } else {
                    response = diagnosaRawatBo.saveAdd(diagnosaRawat);
                    if("success".equalsIgnoreCase(response.getStatus())){
                        insertProfilRJ(idDetailCheckup, idDiagnosa+"-"+ketDiagnosa);
                    }
                }
            }

        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Found Error When "+e.getMessage());
        }
        logger.info("[DiagnosaRawatAction.saveDiagnosa] end process >>>");

        return response;
    }

    public List<DiagnosaRawat> listDiagnosa(String idDetailCheckup) {

        logger.info("[DiagnosaRawatAction.listDiagnosa] start process >>>");
        List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();
        DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
        diagnosaRawat.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                diagnosaRawatList = diagnosaRawatBo.getByCriteria(diagnosaRawat);
            } catch (GeneralBOException e) {
                logger.error("[DiagnosaRawatAction.listDiagnosa] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[DiagnosaRawatAction.listDiagnosa] end process <<<");
            return diagnosaRawatList;
        } else {
            return null;
        }
    }

    public String getListComboDiagnosa() {
        logger.info("[DiagnosaRawatAction.getListComboDiagnosa] start process >>>");

        List<Diagnosa> diagnosaList = new ArrayList<>();
        Diagnosa diagnosa = new Diagnosa();

        try {
            diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
        } catch (GeneralBOException e) {
            logger.error("[DiagnosaRawatAction.getListComboDiagnosa] Error when get diagnosa ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get diagnosa , please inform to your admin.\n" + e.getMessage());
        }

        listOfComboDiagnosa.addAll(diagnosaList);
        logger.info("[DiagnosaRawatAction.getListComboDiagnosa] end process <<<");
        return SUCCESS;
    }

    public CrudResponse editDiagnosa(String idRawatDiagnosa, String idDiagnosa, String jenisDiagnosa, String ketDiagnosa, String jenisPasien, String idDetailCheckup) {
        CrudResponse response = new CrudResponse();
        logger.info("[DiagnosaRawatAction.editDiagnosa] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            DiagnosaBo diagnosaBo = (DiagnosaBo) ctx.getBean("diagnosaBoProxy");
            DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");

            DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
            diagnosaRawat.setIdDiagnosaRawat(idRawatDiagnosa);
            diagnosaRawat.setIdDiagnosa(idDiagnosa);
            diagnosaRawat.setKeteranganDiagnosa(ketDiagnosa);
            diagnosaRawat.setJenisDiagnosa(jenisDiagnosa);
            diagnosaRawat.setLastUpdate(updateTime);
            diagnosaRawat.setLastUpdateWho(userLogin);
            diagnosaRawat.setAction("U");

            if ("bpjs".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                response = updateCoverBpjs(idDetailCheckup, idDiagnosa);
                if ("success".equalsIgnoreCase(response.getStatus())) {
                    response = diagnosaRawatBo.saveEdit(diagnosaRawat);
                }else{
                    response.setStatus("error");
                    response.setMsg("Error when "+response.getMsg());
                }
            } else {
                response = diagnosaRawatBo.saveEdit(diagnosaRawat);
            }

        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Error when "+e.getMessage());
        }
        logger.info("[DiagnosaRawatAction.editDiagnosa] end process >>>");

        return response;
    }

    public CrudResponse updateCoverBpjs(String idDetailCheckup, String idDiagnosa) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String unitId = CommonUtil.userBranchLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");
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

                                if (klaimResponse.getNomorSep() != null) {

                                    KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                    klaimDetailRequest.setTglMasuk(checkup.getCreatedDate().toString());
                                    klaimDetailRequest.setTglPulang(updateTime.toString());
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
                                    klaimDetailRequest.setDiagnosa(idDiagnosa);
                                    klaimDetailRequest.setProcedure(klaimResponse.getProcedure());

                                    klaimDetailRequest.setTarifRsNonBedah(klaimResponse.getTarifRsProsedurNonBedah().toString());
                                    klaimDetailRequest.setTarifRsProsedurBedah(klaimResponse.getTarifRsProsedurBedah().toString());
                                    klaimDetailRequest.setTarifRsKonsultasi(klaimResponse.getTarifRsKonsultasi().toString());
                                    klaimDetailRequest.setTarifRsTenagaAhli(klaimResponse.getTarifRsTenagaAhli().toString());
                                    klaimDetailRequest.setTarifRsKeperawatan(klaimResponse.getTarifRsKeperawatan().toString());
                                    klaimDetailRequest.setTarifRsPenunjang(klaimResponse.getTarifRsPenunjang().toString());

                                    klaimDetailRequest.setTarifRsRadiologi(klaimResponse.getTarifRsRadiologi().toString());
                                    klaimDetailRequest.setTarifRsLaboratorium(klaimResponse.getTarifRsLaboratorium().toString());
                                    klaimDetailRequest.setTarifRsPelayananDarah(klaimResponse.getTarifRsPelayananDarah().toString());
                                    klaimDetailRequest.setTarifRsRehabilitasi(klaimResponse.getTarifRsRehabilitasi().toString());
                                    klaimDetailRequest.setTarifRsKamar(klaimResponse.getTarifRsKamar().toString());
                                    klaimDetailRequest.setTarifRsRawatIntensif(klaimResponse.getTarifRsRawatIntensif().toString());

                                    klaimDetailRequest.setTarifRsObat(klaimResponse.getTarifRsObat().toString());
                                    klaimDetailRequest.setTarifRsObatKronis(klaimResponse.getTarifRsObatKronis().toString());
                                    klaimDetailRequest.setTarifRsObatKemoterapi(klaimResponse.getTarifRsObatKemotrapis().toString());
                                    klaimDetailRequest.setTarifRsObatAlkes(klaimResponse.getTarifRsAlkes().toString());
                                    klaimDetailRequest.setTarifRsBmhp(klaimResponse.getTarifRsBmhp().toString());
                                    klaimDetailRequest.setTarifRsSewaAlat(klaimResponse.getTarifRsSewaAlat().toString());

                                    klaimDetailRequest.setTarifPoliEks(klaimResponse.getTarifPoliEks());
                                    klaimDetailRequest.setNamaDokter(klaimResponse.getNamaDokter());
                                    klaimDetailRequest.setKodeTarif(klaimResponse.getKodeTarif());
                                    klaimDetailRequest.setTarifRsPayorId(klaimResponse.getPayorId());
                                    klaimDetailRequest.setPayorCd(klaimResponse.getKlaimStatusCd());
                                    klaimDetailRequest.setCobCd(klaimResponse.getKlaimStatusCd());
                                    klaimDetailRequest.setCoderNik(getBranch.getCoderNik());

                                    KlaimDetailResponse klaimDetailResponse = new KlaimDetailResponse();

                                    try {
                                        klaimDetailResponse = eklaimBo.updateDataClaimEklaim(klaimDetailRequest, unitId);
                                        response.setStatus(klaimDetailResponse.getStatus());
                                        response.setMsg(klaimDetailResponse.getMessage());
                                    } catch (GeneralBOException e) {
                                        logger.error("[VerifikatorAction.saveApproveTindakan] Error When update tarif tindakan to eklaim", e);
                                        response.setStatus("error");
                                        response.setMsg("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
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
                                                response.setMsg("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
                                            }

                                            BigDecimal tarifCbg = new BigDecimal(0);
                                            if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                                if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {

                                                    tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());


                                                    //======START SET TARIF BPJS kode CBG INA=========

                                                    headerDetailCheckup.setTarifBpjs(tarifCbg);
                                                    headerDetailCheckup.setKodeCbg(grouping1Response.getCbgCode());
                                                    headerDetailCheckup.setLastUpdateWho(userLogin);
                                                    headerDetailCheckup.setLastUpdate(updateTime);

                                                    response = diagnosaRawatBo.updateCoverBpjs(headerDetailCheckup);

                                                    //======END SET TARIF BPJS=========


                                                } else {
                                                    response.setStatus("error");
                                                    response.setMsg("Error when get cover biaya BPJS, [" + grouping1Response.getMessage());
                                                }
                                            } else {
                                                response.setStatus("error");
                                                response.setMsg("Error when get cover biaya BPJS, [" + grouping1Response.getMessage());
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
                                                        response.setMsg("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
                                                    }
                                                }
                                            }
                                        }
                                    }

                                } else {
                                    response.setStatus("error");
                                    response.setMsg("Coder Nik Petugas Bpjs Tidak ada..!");
                                }
                            }
                        }
                    }
                }
            }
        }
        return response;
    }

    public void insertProfilRJ(String idDetailCheckup, String diagnosa) {
        CrudResponse response = new CrudResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            RekamMedisRawatJalanBo rekamMedisRawatJalanBo = (RekamMedisRawatJalanBo) ctx.getBean("rekamMedisRawatJalanBoProxy");
            List<RekamMedisRawatJalan> rekamMedisRawatJalanList = new ArrayList<>();
            try {
                RekamMedisRawatJalan rekamMedisRawatJalan = new RekamMedisRawatJalan();
                rekamMedisRawatJalan.setIdDetailCheckup(idDetailCheckup);
                rekamMedisRawatJalanList = rekamMedisRawatJalanBo.getByCriteria(rekamMedisRawatJalan);
                if (rekamMedisRawatJalanList.size() > 0) {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setDiagnosa(diagnosa);
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setAction("U");
                    response = rekamMedisRawatJalanBo.saveEdit(rawatJalan);
                } else {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setDiagnosa(diagnosa);
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setIdDetailCheckup(idDetailCheckup);
                    rawatJalan.setAction("C");
                    rawatJalan.setFlag("Y");
                    response = rekamMedisRawatJalanBo.saveAdd(rawatJalan);
                }
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("error");
            }
        }
    }
}