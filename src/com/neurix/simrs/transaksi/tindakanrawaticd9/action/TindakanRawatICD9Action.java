package com.neurix.simrs.transaksi.tindakanrawaticd9.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.tindakanrawaticd9.bo.TindakanRawatICD9Bo;
import com.neurix.simrs.transaksi.tindakanrawaticd9.model.TindakanRawatICD9;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TindakanRawatICD9Action {

    protected static transient Logger logger = Logger.getLogger(TindakanRawatICD9Action.class);

    public CrudResponse save(String data) throws JSONException {
        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanRawatICD9Bo tindakanRawatICD9Bo = (TindakanRawatICD9Bo) ctx.getBean("tindakanRawatICD9BoProxy");

        JSONObject obj = new JSONObject(data);
        String jenisPasien = obj.getString("jenis_pasien");
        String idDetailCheckup = obj.getString("id_detail_checkup");
        String idIcd9 = obj.getString("id_icd9");
        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)
           && idIcd9 != null && !"".equalsIgnoreCase(idIcd9)){
            try {

                TindakanRawatICD9 tindakanRawatICD9 = new TindakanRawatICD9();
                tindakanRawatICD9.setIdIcd9(idIcd9);
                tindakanRawatICD9.setNamaIcd9(obj.getString("nama_icd9"));
                tindakanRawatICD9.setIdDetailCheckup(idDetailCheckup);
                tindakanRawatICD9.setFlag("Y");
                tindakanRawatICD9.setAction("C");
                tindakanRawatICD9.setCreatedWho(userLogin);
                tindakanRawatICD9.setCreatedDate(time);
                tindakanRawatICD9.setLastUpdate(time);
                tindakanRawatICD9.setLastUpdateWho(userLogin);

                if ("bpjs".equalsIgnoreCase(jenisPasien) || "ptpn".equalsIgnoreCase(jenisPasien)) {
                    response = updateCoverBpjs(idDetailCheckup, idIcd9, "");
                    if("success".equalsIgnoreCase(response.getStatus())){
                        response = tindakanRawatICD9Bo.saveAdd(tindakanRawatICD9);
                    }
                }else{
                    response = tindakanRawatICD9Bo.saveAdd(tindakanRawatICD9);
                }

            }catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg("Found Error When"+ e.getMessage());
            }
        }else{
            response.setStatus("error");
            response.setMsg("ID Detail Chekup dan Id ICD9  tidak dietmukan");
        }
        return response;
    }

    public CrudResponse edit(String data) throws JSONException {
        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanRawatICD9Bo tindakanRawatICD9Bo = (TindakanRawatICD9Bo) ctx.getBean("tindakanRawatICD9BoProxy");

        JSONObject obj = new JSONObject(data);
        String jenisPasien = obj.getString("jenis_pasien");
        String idTindakanRawatIcd9 = obj.getString("id_tindakan_rawat_icd9");
        String idDetailCheckup = obj.getString("id_detail_checkup");
        String idIcd9 = obj.getString("id_icd9");
        String idEdit = obj.getString("id_edit_icd9");

        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)
                && idIcd9 != null && !"".equalsIgnoreCase(idIcd9)){
            try {

                TindakanRawatICD9 tindakanRawatICD9 = new TindakanRawatICD9();
                tindakanRawatICD9.setIdTindakanRawatIcd9(idTindakanRawatIcd9);
                tindakanRawatICD9.setIdIcd9(idIcd9);
                tindakanRawatICD9.setNamaIcd9(obj.getString("nama_icd9"));
                tindakanRawatICD9.setIdDetailCheckup(idDetailCheckup);
                tindakanRawatICD9.setLastUpdate(time);
                tindakanRawatICD9.setLastUpdateWho(userLogin);

                if ("bpjs".equalsIgnoreCase(jenisPasien) || "ptpn".equalsIgnoreCase(jenisPasien)) {
                    response = updateCoverBpjs(idDetailCheckup, idIcd9, idEdit);
                    if("success".equalsIgnoreCase(response.getStatus())){
                        response = tindakanRawatICD9Bo.saveEdit(tindakanRawatICD9);
                    }
                }else{
                    response = tindakanRawatICD9Bo.saveEdit(tindakanRawatICD9);
                }

            }catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg("Found Error When"+ e.getMessage());
            }
        }else{
            response.setStatus("error");
            response.setMsg("ID Detail Chekup dan Id ICD9  tidak dietmukan");
        }
        return response;
    }

    public List<TindakanRawatICD9> getListICD9(String idDetailCheckup) {

        logger.info("[DiagnosaRawatAction.listDiagnosa] start process >>>");
        List<TindakanRawatICD9> tindakanRawatICD9s = new ArrayList<>();

        TindakanRawatICD9 tindakanRawatICD9 = new TindakanRawatICD9();
        tindakanRawatICD9.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanRawatICD9Bo tindakanRawatICD9Bo = (TindakanRawatICD9Bo) ctx.getBean("tindakanRawatICD9BoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                tindakanRawatICD9s = tindakanRawatICD9Bo.getByCriteria(tindakanRawatICD9);
            } catch (GeneralBOException e) {
                logger.error("Found Error "+e.getMessage());
            }

            logger.info("[DiagnosaRawatAction.listDiagnosa] end process <<<");
        }
        return tindakanRawatICD9s;
    }


    public CrudResponse updateCoverBpjs(String idDetailCheckup, String idIcd9, String idEdit) {
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
                                    klaimDetailRequest.setDiagnosa(klaimResponse.getDiagnosa());
                                    String prosedure = "";
                                    if(!"".equalsIgnoreCase(idEdit)){
                                        if(klaimResponse.getProcedure() != null &&
                                           !"".equalsIgnoreCase(klaimResponse.getProcedure())){
                                            String str = klaimResponse.getProcedure();
                                            logger.info("ISI STR : "+str);
                                            List<String> icd9 = Arrays.asList(str.split("#"));
                                            for (String list: icd9){
                                                String id = list;
                                                if(idEdit.equalsIgnoreCase(list)){
                                                    id = idIcd9;
                                                }

                                                if("".equalsIgnoreCase(prosedure)){
                                                    prosedure = id;
                                                }else{
                                                    prosedure = prosedure+"#"+id;
                                                }
                                                logger.info("LIST INFO : "+id);
                                            }
                                        }
                                    }else{
                                        if(klaimResponse.getProcedure() != null &&
                                           !"".equalsIgnoreCase(klaimResponse.getProcedure())){
                                            prosedure = klaimResponse.getProcedure()+"#"+idIcd9;
                                        }else{
                                            prosedure = idIcd9;
                                        }
                                    }

                                    klaimDetailRequest.setProcedure(prosedure);

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

    public static Logger getLogger() {
        return logger;
    }
}