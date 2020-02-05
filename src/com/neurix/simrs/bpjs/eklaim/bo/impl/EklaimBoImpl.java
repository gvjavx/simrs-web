package com.neurix.simrs.bpjs.eklaim.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.bpjs.vclaim.model.DiagnosaResponse;
import com.neurix.simrs.bpjs.vclaim.model.TindakanResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class EklaimBoImpl extends BpjsService implements EklaimBo {

    protected static transient Logger logger = Logger.getLogger(EklaimBoImpl.class);

    public EklaimBoImpl() throws GeneralSecurityException, IOException {
    }

    private BranchDao branchDao;

    public static void setLogger(Logger logger) {
        EklaimBoImpl.logger = logger;
    }

    public static Logger getLogger() {
        return logger;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public KlaimResponse insertNewClaimEklaim(KlaimRequest klaimRequest, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.insertNewClaimEklaim] Start >>>>>>>");
        KlaimResponse finalResponse = new KlaimResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.insertNewClaimEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\": \"new_claim\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_kartu\": \""+klaimRequest.getNoKartu()+"\",\n" +
                    " \"nomor_sep\": \""+klaimRequest.getNoSep()+"\",\n" +
                    " \"nomor_rm\": \""+klaimRequest.getNoRm()+"\",\n" +
                    " \"nama_pasien\": \""+klaimRequest.getNamaPasien()+"\",\n" +
                    " \"tgl_lahir\": \""+klaimRequest.getTglLahir()+"\",\n" +
                    " \"gender\": \""+klaimRequest.getGender()+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    finalResponse.setPatientId(response.getString("patient_id"));

                    String admissionId = String.valueOf(response.getInt("admission_id"));
                    String hospitalId = String.valueOf(response.getInt("hospital_admission_id"));

                    finalResponse.setAdmissionId(admissionId);
                    finalResponse.setHospitalAdmissionId(hospitalId);
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.insertNewClaimEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.insertNewClaimEklaim] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public KlaimResponse updateDataPasienEklaim(KlaimRequest klaimRequest, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.updateDataPasienEklaim] Start >>>>>>>");
        KlaimResponse finalResponse = new KlaimResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.updateDataPasienEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\": \"update_patient\",\n" +
                    " \"nomor_rm\": \""+klaimRequest.getNoRm()+"\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_kartu\": \""+klaimRequest.getNoKartu()+"\",\n" +
                    " \"nomor_rm\": \""+klaimRequest.getNoRm()+"\",\n" +
                    " \"nama_pasien\": \""+klaimRequest.getNamaPasien()+"\",\n" +
                    " \"tgl_lahir\": \""+klaimRequest.getTglLahir()+"\",\n" +
                    " \"gender\": \""+klaimRequest.getGender()+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    logger.info("[EklaimBoImpl.updateDataPasienEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.updateDataPasienEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.updateDataPasienEklaim] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public KlaimResponse deleteDataPasienEklaim(KlaimRequest klaimRequest, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.deleteDataPasienEklaim] Start >>>>>>>");
        KlaimResponse finalResponse = new KlaimResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.deleteDataPasienEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\": \"delete_patient\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_rm\": \""+klaimRequest.getNoRm()+"\",\n" +
                    " \"coder_nik\": \""+klaimRequest.getCoderNik()+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    logger.info("[EklaimBoImpl.deleteDataPasienEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.deleteDataPasienEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.deleteDataPasienEklaim] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public KlaimDetailResponse updateDataClaimEklaim(KlaimDetailRequest klaimDetailRequest, String unitId) throws GeneralBOException {
        //UNTUK MENGHAPUS GUNAKAN TANDA # dan jika tidak ada perubahan maka gunakan tanda "";
        logger.info("[EklaimBoImpl.updateDataClaimEklaim] Start >>>>>>>");
        KlaimDetailResponse finalResponse = new KlaimDetailResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.updateDataClaimEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\": \"set_claim_data\",\n" +
                    " \"nomor_sep\": \""+klaimDetailRequest.getNomorSep()+"\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\": \""+klaimDetailRequest.getNomorSep()+"\",\n" +
                    " \"nomor_kartu\": \""+klaimDetailRequest.getNomorKartu()+"\",\n" +
                    " \"tgl_masuk\": \""+klaimDetailRequest.getTglMasuk()+"\",\n" +
                    " \"tgl_pulang\": \""+klaimDetailRequest.getTglPulang()+"\",\n" +
                    " \"jenis_rawat\": \""+klaimDetailRequest.getJenisRawat()+"\",\n" +
                    " \"kelas_rawat\": \""+klaimDetailRequest.getKelasRawat()+"\",\n" +
                    " \"adl_sub_acute\": \""+klaimDetailRequest.getAdlSubAcute()+"\",\n" +
                    " \"adl_chronic\": \""+klaimDetailRequest.getAdlChronic()+"\",\n" +
                    " \"icu_indikator\": \""+klaimDetailRequest.getIcuIndikator()+"\",\n" +
                    " \"icu_los\": \""+klaimDetailRequest.getIcuLos()+"\",\n" +
                    " \"ventilator_hour\": \""+klaimDetailRequest.getVentilatorHour()+"\",\n" +
                    " \"upgrade_class_ind\": \""+klaimDetailRequest.getUpgradeClassInd()+"\",\n" +
                    " \"upgrade_class_class\": \""+klaimDetailRequest.getUpgradeClassClass()+"\",\n" +
                    " \"upgrade_class_los\": \""+klaimDetailRequest.getUpgradeClassLos()+"\",\n" +
                    " \"add_payment_pct\": \""+klaimDetailRequest.getAddPaymentPct()+"\",\n" +
                    " \"birth_weight\": \""+klaimDetailRequest.getBirthWeight()+"\",\n" +
                    " \"discharge_status\": \""+klaimDetailRequest.getDischargeStatus()+"\",\n" +
                    " \"diagnosa\": \""+klaimDetailRequest.getDiagnosa()+"\",\n" +
                    " \"procedure\": \""+klaimDetailRequest.getProcedure()+"\",\n" +
                    " \"tarif_rs\": {\n" +
                    " \"prosedur_non_bedah\": \""+klaimDetailRequest.getTarifRsNonBedah()+"\",\n" +
                    " \"prosedur_bedah\": \""+klaimDetailRequest.getTarifRsProsedurBedah()+"\",\n" +
                    " \"konsultasi\": \""+klaimDetailRequest.getTarifRsKonsultasi()+"\",\n" +
                    " \"tenaga_ahli\": \""+klaimDetailRequest.getTarifRsTenagaAhli()+"\",\n" +
                    " \"keperawatan\": \""+klaimDetailRequest.getTarifRsKeperawatan()+"\",\n" +
                    " \"penunjang\": \""+klaimDetailRequest.getTarifRsPenunjang()+"\",\n" +
                    " \"radiologi\": \""+klaimDetailRequest.getTarifRsRadiologi()+"\",\n" +
                    " \"laboratorium\": \""+klaimDetailRequest.getTarifRsLaboratorium()+"\",\n" +
                    " \"pelayanan_darah\": \""+klaimDetailRequest.getTarifRsPelayananDarah()+"\",\n" +
                    " \"rehabilitasi\": \""+klaimDetailRequest.getTarifRsRehabilitasi()+"\",\n" +
                    " \"kamar\": \""+klaimDetailRequest.getTarifRsKamar()+"\",\n" +
                    " \"rawat_intensif\": \""+klaimDetailRequest.getTarifRsRawatIntensif()+"\",\n" +
                    " \"obat\": \""+klaimDetailRequest.getTarifRsObat()+"\",\n" +
                    " \"obat_kronis\": \""+klaimDetailRequest.getTarifRsObatKronis()+"\",\n" +
                    " \"obat_kemoterapi\": \""+klaimDetailRequest.getTarifRsObatKemoterapi()+"\",\n" +
                    " \"alkes\": \""+klaimDetailRequest.getTarifRsAlkes()+"\",\n" +
                    " \"bmhp\": \""+klaimDetailRequest.getTarifRsBmhp()+"\",\n" +
                    " \"sewa_alat\": \""+klaimDetailRequest.getTarifRsSewaAlat()+"\"\n" +
                    " },\n" +
                    " \"tarif_poli_eks\": \""+klaimDetailRequest.getTarifPoliEks()+"\",\n" +
                    " \"nama_dokter\": \""+klaimDetailRequest.getNamaDokter()+"\",\n" +
                    " \"kode_tarif\": \""+klaimDetailRequest.getKodeTarif()+"\",\n" +
                    " \"payor_id\": \""+klaimDetailRequest.getTarifRsPayorId()+"\",\n" +
                    " \"payor_cd\": \""+klaimDetailRequest.getPayorCd()+"\",\n" +
                    " \"cob_cd\": \""+klaimDetailRequest.getCobCd()+"\",\n" +
                    " \"coder_nik\": \""+klaimDetailRequest.getCoderNik()+"\"\n" +
                    " } }";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    logger.info("[EklaimBoImpl.updateDataClaimEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.updateDataClaimEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }

                finalResponse.setStatus(metaData.getString("code"));
                finalResponse.setMessage(metaData.getString("message"));

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.updateDataClaimEklaim] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public Grouping1Response groupingStage1Eklaim(String noSep, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.groupingStage1Eklaim] Start >>>>>>>");
        Grouping1Response finalResponse = new Grouping1Response();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.groupingStage1Eklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\":\"grouper\",\n" +
                    " \"stage\":\"1\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\":\""+noSep+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    JSONObject response = myResponseCheck.getJSONObject("response");

                    JSONObject cbg = response.getJSONObject("cbg");
                    finalResponse.setCbgCode(cbg.getString("code"));
                    finalResponse.setCbgDescription(cbg.getString("description"));
                    finalResponse.setCbgTarif(cbg.getString("tariff"));

                    JSONObject subAcute = response.getJSONObject("sub_acute");
                    finalResponse.setSubAcuteCode(subAcute.getString("code"));
                    finalResponse.setSubAcuteDescription(subAcute.getString("description"));
                    finalResponse.setSubAcuteTarif(subAcute.getString("tariff"));

                    JSONObject chronic = response.getJSONObject("chronic");
                    finalResponse.setChronicCode(chronic.getString("code"));
                    finalResponse.setChronicDescription(chronic.getString("description"));
                    finalResponse.setChronicTarif(chronic.getString("tariff"));

                    finalResponse.setKelas(response.getString("kelas"));
                    finalResponse.setAddPaymentAmt(response.getString("add_payment_amt"));
                    finalResponse.setInacbgVersion(response.getString("inacbg_version"));

                    List<Grouping1SpecialCmgResponse> specialCmgResponseList = new ArrayList<>();
                    JSONArray specialCmgOption = myResponseCheck.getJSONArray("special_cmg_option");
                    int length = specialCmgOption.length();
                    for (int i=0;i<length;i++) {
                        JSONObject obj= specialCmgOption.getJSONObject(i);
                        Grouping1SpecialCmgResponse data = new Grouping1SpecialCmgResponse();
                        data.setCode(obj.getString("code"));
                        data.setDescription(obj.getString("description"));
                        data.setType(obj.getString("type"));
                        specialCmgResponseList.add(data);
                    }

                    List<Grouping1TarifAltResponse> tarifAltResponseList = new ArrayList<>();
                    JSONArray tarifAlt = myResponseCheck.getJSONArray("tarif_alt");
                    int lengthtarifAlt = tarifAlt.length();
                    for (int i=0;i<lengthtarifAlt;i++) {
                        JSONObject obj= tarifAlt.getJSONObject(i);
                        Grouping1TarifAltResponse data = new Grouping1TarifAltResponse();
                        data.setTarifInacbg(obj.getString("tarif_inacbg"));
                        data.setKelas(obj.getString("kelas"));
                        tarifAltResponseList.add(data);
                    }

                    finalResponse.setSpecialCmgResponseList(specialCmgResponseList);
                    finalResponse.setTarifAltResponseList(tarifAltResponseList);

                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.groupingStage1Eklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.groupingStage1Eklaim] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public Grouping2Response groupingStage2Eklaim(String noSep, String specialCmg, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.groupingStage2Eklaim] Start >>>>>>>");
        Grouping2Response finalResponse = new Grouping2Response();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.groupingStage2Eklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\":\"grouper\",\n" +
                    " \"stage\":\"2\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\":\""+noSep+"\",\n" +
                    " \"special_cmg\": \""+specialCmg+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    JSONObject response = myResponseCheck.getJSONObject("response");

                    JSONObject cbg = response.getJSONObject("cbg");
                    finalResponse.setCbgCode(cbg.getString("code"));
                    finalResponse.setCbgDescription(cbg.getString("description"));
                    finalResponse.setCbgTarif(cbg.getString("tariff"));

                    finalResponse.setKelas(response.getString("kelas"));
                    finalResponse.setAddPaymentAmt(response.getString("add_payment_amt"));
                    finalResponse.setInacbgVersion(response.getString("inacbg_version"));

                    List<Grouping2SpesialCmgResponse> grouping2SpesialCmgResponseList = new ArrayList<>();
                    JSONArray specialCmg1 = response.getJSONArray("special_cmg");
                    int lengthSpecialCmg = specialCmg1.length();
                    for (int i=0;i<lengthSpecialCmg;i++) {
                        JSONObject obj= specialCmg1.getJSONObject(i);
                        Grouping2SpesialCmgResponse data = new Grouping2SpesialCmgResponse();
                        data.setCode(obj.getString("code"));
                        data.setDescription(obj.getString("description"));
                        data.setType(obj.getString("type"));
                        data.setTarif(obj.getString("tariff"));
                        grouping2SpesialCmgResponseList.add(data);
                    }

                    List<Grouping1SpecialCmgResponse> specialCmgResponseList = new ArrayList<>();
                    JSONArray specialCmgOption = myResponseCheck.getJSONArray("special_cmg_option");
                    int length = specialCmgOption.length();
                    for (int i=0;i<length;i++) {
                        JSONObject obj= specialCmgOption.getJSONObject(i);
                        Grouping1SpecialCmgResponse data = new Grouping1SpecialCmgResponse();
                        data.setCode(obj.getString("code"));
                        data.setDescription(obj.getString("description"));
                        data.setType(obj.getString("type"));
                        specialCmgResponseList.add(data);
                    }

                    List<Grouping1TarifAltResponse> tarifAltResponseList = new ArrayList<>();
                    JSONArray tarifAlt = myResponseCheck.getJSONArray("tarif_alt");
                    int lengthtarifAlt = tarifAlt.length();
                    for (int i=0;i<lengthtarifAlt;i++) {
                        JSONObject obj= tarifAlt.getJSONObject(i);
                        Grouping1TarifAltResponse data = new Grouping1TarifAltResponse();
                        data.setTarifInacbg(obj.getString("tarif_inacbg"));
                        data.setKelas(obj.getString("kelas"));
                        tarifAltResponseList.add(data);
                    }

                    finalResponse.setGrouping1SpecialCmgResponseList(specialCmgResponseList);
                    finalResponse.setGrouping1TarifAltResponseList(tarifAltResponseList);
                    finalResponse.setGrouping2SpesialCmgResponseList(grouping2SpesialCmgResponseList);

                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.groupingStage2Eklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.groupingStage2Eklaim] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public void finalisasiClaimEklaim(String noSep, String coderNik, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.finalisasiClaimEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.finalisasiClaimEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\":\"claim_final\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\":\""+noSep+"\",\n" +
                    " \"coder_nik\": \""+coderNik+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    logger.info("[EklaimBoImpl.finalisasiClaimEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.finalisasiClaimEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.finalisasiClaimEklaim] End <<<<<<<");
    }
    @Override
    public void reeditClaimEklaim(String noSep, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.reeditClaimEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.reeditClaimEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\":\"reedit_claim\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\":\""+noSep+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    logger.info("[EklaimBoImpl.reeditClaimEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.reeditClaimEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.reeditClaimEklaim] End <<<<<<<");
    }
    @Override
    public List<KlaimDataCenterResponse> kirimKeDataCenterPerTanggalEklaim(String startDate, String stopDate, String jenisRawat, String dateType, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.kirimKeDataCenterPerTanggalEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        List<KlaimDataCenterResponse> finalResponse = new ArrayList<>();
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.kirimKeDataCenterPerTanggalEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\":\"send_claim\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"start_dt\":\""+startDate+"\",\n" +
                    " \"stop_dt\":\""+stopDate+"\",\n" +
                    " \"jenis_rawat\":\""+jenisRawat+"\",\n" +
                    " \"date_type\":\""+dateType+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray list = response.getJSONArray("data");
                    int length = list.length();
                    for (int i=0;i<length;i++) {
                        JSONObject obj= list.getJSONObject(i);
                        KlaimDataCenterResponse data = new KlaimDataCenterResponse();
                        data.setSEP(obj.getString("SEP"));
                        data.setTglPulang(obj.getString("tgl_pulang"));
                        data.setKemkesDcStatus(obj.getString("kemkes_dc_Status"));
                        data.setBpjsDcStatus(obj.getString("bpjs_dc_Status"));
                        finalResponse.add(data);
                    }
                    logger.info("[EklaimBoImpl.kirimKeDataCenterPerTanggalEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.kirimKeDataCenterPerTanggalEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.kirimKeDataCenterPerTanggalEklaim] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public List<KlaimDataCenterResponse> kirimKeDataCenterPerSepEklaim(String noSep, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.kirimKeDataCenterPerSepEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        List<KlaimDataCenterResponse> finalResponse = new ArrayList<>();
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.kirimKeDataCenterPerSepEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\":\"send_claim_individual\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\":\""+noSep+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray list = response.getJSONArray("data");
                    int length = list.length();
                    for (int i=0;i<length;i++) {
                        JSONObject obj= list.getJSONObject(i);
                        KlaimDataCenterResponse data = new KlaimDataCenterResponse();
                        data.setSEP(obj.getString("SEP"));
                        data.setTglPulang(obj.getString("tgl_pulang"));
                        data.setKemkesDcStatus(obj.getString("kemkes_dc_Status"));
                        data.setBpjsDcStatus(obj.getString("bpjs_dc_Status"));
                        data.setCobDcStatus(obj.getString("cob_dc_status"));
                        finalResponse.add(data);
                    }
                    logger.info("[EklaimBoImpl.kirimKeDataCenterPerSepEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.kirimKeDataCenterPerSepEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.kirimKeDataCenterPerSepEklaim] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public DataPerKlaimResponse detailPerKlaimEklaim(String noSep, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.detailPerKlaimEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        DataPerKlaimResponse finalResponse = new DataPerKlaimResponse();
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.detailPerKlaimEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\":\"get_claim_data\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\":\""+noSep+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    JSONObject response = myResponseCheck.getJSONObject("response");

                    JSONObject data = response.getJSONObject("data");
                    if (!data.isNull("kode_rs")){
                        finalResponse.setKodeRs(data.getString("kode_rs"));
                    }
                    if (!data.isNull("kelas_rs")){
                        finalResponse.setKelasRs(data.getString("kelas_rs"));
                    }
                    if (!data.isNull("kelas_rawat")){
                        finalResponse.setKelasRawat(data.getInt("kelas_rawat"));
                    }
                    if (!data.isNull("kode_tarif")){
                        finalResponse.setKodeTarif(data.getString("kode_tarif"));
                    }
                    if (!data.isNull("jenis_rawat")){
                        finalResponse.setJenisRawat(data.getInt("jenis_rawat"));
                    }
                    if (!data.isNull("tgl_masuk")){
                        finalResponse.setTglMasuk(data.getString("tgl_masuk"));
                    }
                    if (!data.isNull("tgl_pulang")){
                        finalResponse.setTglPulang(data.getString("tgl_pulang"));
                    }
                    if (!data.isNull("tgl_lahir")){
                        finalResponse.setTglLahir(data.getString("tgl_lahir"));
                    }
                    if (!data.isNull("berat_lahir")){
                        finalResponse.setBeratLahir(data.getString("berat_lahir"));
                    }
                    if (!data.isNull("gender")){
                        finalResponse.setGender(data.getInt("gender"));
                    }
                    if (!data.isNull("discharge_status")){
                        finalResponse.setDischargeStatus(data.getInt("discharge_status"));
                    }
                    if (!data.isNull("diagnosa")){
                        finalResponse.setDiagnosa(data.getString("diagnosa"));
                    }
                    if (!data.isNull("procedure")){
                        finalResponse.setProcedure(data.getString("procedure"));
                    }
                    if (!data.isNull("adl_sub_acute")){
                        finalResponse.setAdlSubAcute(data.getInt("adl_sub_acute"));
                    }
                    if (!data.isNull("adl_chronic")){
                        finalResponse.setAdlChronic(data.getInt("adl_chronic"));
                    }

                    JSONObject tarifRs = data.getJSONObject("tarif_rs");
                    if (!tarifRs.isNull("prosedur_non_bedah")){
                        finalResponse.setTarifRsProsedurNonBedah(tarifRs.getInt("prosedur_non_bedah"));
                    }
                    if (!tarifRs.isNull("prosedur_bedah")){
                        finalResponse.setTarifRsProsedurBedah(tarifRs.getInt("prosedur_bedah"));
                    }
                    if (!tarifRs.isNull("konsultasi")){
                        finalResponse.setTarifRsKonsultasi(tarifRs.getInt("konsultasi"));
                    }
                    if (!tarifRs.isNull("tenaga_ahli")){
                        finalResponse.setTarifRsTenagaAhli(tarifRs.getInt("tenaga_ahli"));
                    }
                    if (!tarifRs.isNull("keperawatan")){
                        finalResponse.setTarifRsKeperawatan(tarifRs.getInt("keperawatan"));
                    }
                    if (!tarifRs.isNull("penunjang")){
                        finalResponse.setTarifRsPenunjang(tarifRs.getInt("penunjang"));
                    }
                    if (!tarifRs.isNull("radiologi")){
                        finalResponse.setTarifRsRadiologi(tarifRs.getInt("radiologi"));
                    }
                    if (!tarifRs.isNull("laboratorium")){
                        finalResponse.setTarifRsLaboratorium(tarifRs.getInt("laboratorium"));
                    }
                    if (!tarifRs.isNull("pelayanan_darah")){
                        finalResponse.setTarifRsPelayananDarah(tarifRs.getInt("pelayanan_darah"));
                    }
                    if (!tarifRs.isNull("rehabilitasi")){
                        finalResponse.setTarifRsRehabilitasi(tarifRs.getInt("rehabilitasi"));
                    }
                    if (!tarifRs.isNull("kamar")){
                        finalResponse.setTarifRsKamar(tarifRs.getInt("kamar"));
                    }
                    if (!tarifRs.isNull("rawat_intensif")){
                        finalResponse.setTarifRsRawatIntensif(tarifRs.getInt("rawat_intensif"));
                    }
                    if (!tarifRs.isNull("obat")){
                        finalResponse.setTarifRsObat(tarifRs.getInt("obat"));
                    }
                    if (!tarifRs.isNull("obat_kronis")){
                        finalResponse.setTarifRsObatKronis(tarifRs.getInt("obat_kronis"));
                    }
                    if (!tarifRs.isNull("obat_kemoterapi")){
                        finalResponse.setTarifRsObatKemotrapis(tarifRs.getInt("obat_kemoterapi"));
                    }
                    if (!tarifRs.isNull("alkes")){
                        finalResponse.setTarifRsAlkes(tarifRs.getInt("alkes"));
                    }
                    if (!tarifRs.isNull("bmhp")){
                        finalResponse.setTarifRsBmhp(tarifRs.getInt("bmhp"));
                    }
                    if (!tarifRs.isNull("sewa_alat")){
                        finalResponse.setTarifRsSewaAlat(tarifRs.getInt("sewa_alat"));
                    }

                    if (!data.isNull("los")){
                        finalResponse.setLos(data.getString("los"));
                    }
                    if (!data.isNull("icu_indikator")){
                        finalResponse.setIcuIndicator(data.getInt("icu_indikator"));
                    }
                    if (!data.isNull("icu_los")){
                        finalResponse.setIcuLos(data.getString("icu_los"));
                    }
                    if (!data.isNull("ventilator_hour")){
                        finalResponse.setVentilatorHour(data.getString("ventilator_hour"));
                    }
                    if (!data.isNull("upgrade_class_ind")){
                        finalResponse.setUpgradeClassInd(data.getString("upgrade_class_ind"));
                    }
                    if (!data.isNull("upgrade_class_class")){
                        finalResponse.setUpgradeClassClass(data.getString("upgrade_class_class"));
                    }
                    if (!data.isNull("upgrade_class_los")){
                        finalResponse.setUpgradeClassLos(data.getString("upgrade_class_los"));
                    }
                    if (!data.isNull("add_payment_pct")){
                        finalResponse.setAddPaymenPct(data.getString("add_payment_pct"));
                    }
                    if (!data.isNull("add_payment_amt")){
                        finalResponse.setAddPaymentAmt(data.getString("add_payment_amt"));
                    }
                    if (!data.isNull("nama_pasien")){
                        finalResponse.setNamaPasien(data.getString("nama_pasien"));
                    }
                    if (!data.isNull("nomor_rm")){
                        finalResponse.setNomorRm(data.getString("nomor_rm"));
                    }
                    if (!data.isNull("umur_tahun")){
                        finalResponse.setUmurTahun(data.getInt("umur_tahun"));
                    }
                    if (!data.isNull("umur_hari")){
                        finalResponse.setUmurHari(data.getString("umur_hari"));
                    }
                    if (!data.isNull("tarif_poli_eks")){
                        finalResponse.setTarifPoliEks(data.getString("tarif_poli_eks"));
                    }
                    if (!data.isNull("nama_dokter")){
                        finalResponse.setNamaDokter(data.getString("nama_dokter"));
                    }
                    if (!data.isNull("nomor_sep")){
                        finalResponse.setNomorSep(data.getString("nomor_sep"));
                    }
                    if (!data.isNull("nomor_kartu")){
                        finalResponse.setNomorKartu(data.getString("nomor_kartu"));
                    }
                    if (!data.isNull("payor_id")){
                        finalResponse.setPayorId(data.getString("payor_id"));
                    }
                    if (!data.isNull("payor_nm")){
                        finalResponse.setPayorNm(data.getString("payor_nm"));
                    }
                    if (!data.isNull("coder_nik")){
                        finalResponse.setCoderNik(data.getString("coder_nik"));
                    }
                    if (!data.isNull("coder_nm")){
                        finalResponse.setCoderNm(data.getString("coder_nm"));
                    }
                    if (!data.isNull("patient_id")){
                        finalResponse.setPatientId(data.getString("patient_id"));
                    }
                    if (!data.isNull("admission_id")){
                        finalResponse.setAdmissionId(data.getString("admission_id"));
                    }
                    if (!data.isNull("hospital_admission_id")){
                        finalResponse.setHospitalAdmissionId(data.getString("hospital_admission_id"));
                    }
                    if (!data.isNull("grouping_count")){
                        finalResponse.setGroupingCount(data.getString("grouping_count"));
                    }

                    JSONObject grouper = data.getJSONObject("grouper");
                    JSONObject responseGrouper = grouper.getJSONObject("response");
                    JSONObject cbg = responseGrouper.getJSONObject("cbg");
                    if (!cbg.isNull("code")){
                        finalResponse.setCbgCode(cbg.getString("code"));
                    }
                    if (!cbg.isNull("description")){
                        finalResponse.setCbgDescription(cbg.getString("description"));
                    }
                    if (!cbg.isNull("tariff")){
                        finalResponse.setCbgTarif(cbg.getString("tariff"));
                    }

                    List<Grouping2SpesialCmgResponse> grouping2SpesialCmgResponseList = new ArrayList<>();
                    /*JSONArray listSpecialCmg = responseGrouper.getJSONArray("special_cmg");
                    int lengthSpecialCmg = listSpecialCmg.length();
                    for (int i=0;i<lengthSpecialCmg;i++) {
                        JSONObject obj= listSpecialCmg.getJSONObject(i);
                        Grouping2SpesialCmgResponse dataSpecialCmg = new Grouping2SpesialCmgResponse();
                        if (!obj.isNull("code")){
                            dataSpecialCmg.setCode(obj.getString("code"));
                        }
                        if (!obj.isNull("description")){
                            dataSpecialCmg.setDescription(obj.getString("description"));
                        }
                        if (!obj.isNull("tariff")){
                            dataSpecialCmg.setTarif(obj.getString("tariff"));
                        }
                        if (!obj.isNull("type")){
                            dataSpecialCmg.setType(obj.getString("type"));
                        }
                        grouping2SpesialCmgResponseList.add(dataSpecialCmg);
                    }*/
                    finalResponse.setGrouping2SpesialCmgResponseList(grouping2SpesialCmgResponseList);

                    finalResponse.setInaCbgVersion(responseGrouper.getString("inacbg_version"));

                    List<Grouping1TarifAltResponse> grouping1TarifAltResponseList = new ArrayList<>();
                    /*JSONArray listTarifAlt = responseGrouper.getJSONArray("grouper");
                    int lengthTarifAlt = listTarifAlt.length();
                    for (int i=0;i<lengthTarifAlt;i++) {
                        JSONObject obj= listTarifAlt.getJSONObject(i);
                        Grouping1TarifAltResponse dataTarifAlt = new Grouping1TarifAltResponse();
                        if (!obj.isNull("kelas")){
                            dataTarifAlt.setKelas(obj.getString("kelas"));
                        }
                        if (!obj.isNull("tarif_inacbg")){
                            dataTarifAlt.setTarifInacbg(obj.getString("tarif_inacbg"));
                        }
                        if (!obj.isNull("tarif_sp")){
                            dataTarifAlt.setTarifSp(obj.getString("tarif_sp"));
                        }
                        if (!obj.isNull("tarif_sr")){
                            dataTarifAlt.setTarifSr(obj.getString("tarif_sr"));
                        }
                        grouping1TarifAltResponseList.add(dataTarifAlt);
                    }*/
                    finalResponse.setGrouping1TarifAltResponseList(grouping1TarifAltResponseList);

                    if (!data.isNull("kemenkes_dc_status_cd")){
                        finalResponse.setKemenkesDcStatusCd(data.getString("kemenkes_dc_status_cd"));
                    }
                    if (!data.isNull("kemenkes_dc_sent_dttm")){
                        finalResponse.setKemenkesDcSentDttm(data.getString("kemenkes_dc_sent_dttm"));
                    }
                    if (!data.isNull("bpjs_dc_status_cd")){
                        finalResponse.setBpjsDcStatusCd(data.getString("bpjs_dc_status_cd"));
                    }
                    if (!data.isNull("bpjs_dc_sent_dttm")){
                        finalResponse.setBpjsDcSentDttm(data.getString("bpjs_dc_sent_dttm"));
                    }
                    if (!data.isNull("klaim_status_cd")){
                        finalResponse.setKlaimStatusCd(data.getString("klaim_status_cd"));
                    }
                    if (!data.isNull("bpjs_klaim_status_cd")){
                        finalResponse.setBpjsKlaimStatusCd(data.getString("bpjs_klaim_status_cd"));
                    }
                    if (!data.isNull("bpjs_klaim_status_nm")){
                        finalResponse.setBpjsKlaimStatusNm(data.getString("bpjs_klaim_status_nm"));
                    }

                    logger.info("[EklaimBoImpl.detailPerKlaimEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.detailPerKlaimEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.detailPerKlaimEklaim] End <<<<<<<");
        return finalResponse;
    }

    @Override
    public ClaimStatusResponse cekStatusKlaimVclaimPerSepEklaim(String noSep, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.cekStatusKlaimVclaimPerSepEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        ClaimStatusResponse finalResponse = new ClaimStatusResponse();
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.cekStatusKlaimVclaimPerSepEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\":\"get_claim_status\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\":\""+noSep+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    finalResponse.setKdStatusSep(response.getString("kdStatusSep"));
                    finalResponse.setNmStatusSep(response.getString("nmStatusSep"));

                    logger.info("[EklaimBoImpl.cekStatusKlaimVclaimPerSepEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.cekStatusKlaimVclaimPerSepEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.cekStatusKlaimVclaimPerSepEklaim] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public void deleteKlaimPerSepEklaim(String noSep, String coderNik, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.deleteKlaimPerSepEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.deleteKlaimPerSepEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\":\"delete_claim\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\":\""+noSep+"\",\n" +
                    " \"coder_nik\":\""+coderNik+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    logger.info("[EklaimBoImpl.deleteKlaimPerSepEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.deleteKlaimPerSepEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.deleteKlaimPerSepEklaim ] End <<<<<<<");
    }
    @Override
    public String printKlaimPerSepEklaim(String noSep, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.printKlaimPerSepEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        String resultPdfBase64 ="";
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.printKlaimPerSepEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\": \"claim_print\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"nomor_sep\": \""+noSep+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    resultPdfBase64 = myResponseCheck.getString("data");
                    logger.info("[EklaimBoImpl.printKlaimPerSepEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.printKlaimPerSepEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.printKlaimPerSepEklaim ] End <<<<<<<");
        return resultPdfBase64;
    }
    @Override
    public List<TindakanResponse> getProsedureEklaim(String keyword, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.getProsedureEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        List<TindakanResponse> finalResponse = new ArrayList<>();
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.getProsedureEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\": \"search_procedures\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"keyword\": \""+keyword+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray data = response.getJSONArray("data");
                    int lengthProcedure = data.length();
                    for (int i=0;i<lengthProcedure;i++) {
                        JSONArray obj= data.getJSONArray(i);
                        TindakanResponse tindakanResponse = new TindakanResponse();
                        tindakanResponse.setKodeTindakanBpjs(String.valueOf(obj.get(1)));
                        tindakanResponse.setNamaTindakanBpjs(String.valueOf(obj.get(0)));
                        finalResponse.add(tindakanResponse);
                    }
                    logger.info("[EklaimBoImpl.getProsedureEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.getProsedureEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.getProsedureEklaim ] End <<<<<<<");
        return finalResponse;
    }
    @Override
    public List<DiagnosaResponse> getDiagnosaEklaim(String keyword, String unitId) throws GeneralBOException {
        logger.info("[EklaimBoImpl.getDiagnosaEklaim] Start >>>>>>>");
        ImBranches resultBranch = null;
        List<DiagnosaResponse> finalResponse = new ArrayList<>();
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[EklaimBoImpl.getDiagnosaEklaim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch!=null){
            String feature = resultBranch.getEklaimAddress() + CommonConstant.EKLAIM_SERVICE_DEBUG;
            JSONObject request = null;
            String jsonData="{\n" +
                    " \"metadata\": {\n" +
                    " \"method\": \"search_diagnosis\"\n" +
                    " },\n" +
                    " \"data\": {\n" +
                    " \"keyword\": \""+keyword+"\"\n" +
                    " }\n" +
                    "}";
            try {
                request = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = ReqEklaim(feature,request,"GET",resultBranch.getKeyEklaim());
                JSONObject myResponseCheck = new JSONObject(result);
                JSONObject metaData = myResponseCheck.getJSONObject("metadata");
                if (200==metaData.getInt("code")){
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray data = response.getJSONArray("data");
                    int lengthProcedure = data.length();
                    for (int i=0;i<lengthProcedure;i++) {
                        JSONArray obj= data.getJSONArray(i);
                        DiagnosaResponse diagnosaResponse = new DiagnosaResponse();
                        diagnosaResponse.setKodeDiagnosaBpjs(String.valueOf(obj.get(1)));
                        diagnosaResponse.setNamaDiagnosaBpjs(String.valueOf(obj.get(0)));
                        finalResponse.add(diagnosaResponse);
                    }
                    logger.info("[EklaimBoImpl.getDiagnosaEklaim] : "+metaData.getString("message"));
                }else{
                    String errorNo = metaData.getString("error_no");
                    logger.error("[EklaimBoImpl.getDiagnosaEklaim] : " + errorNo +" : "+metaData.getString("message"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info("[EklaimBoImpl.getDiagnosaEklaim ] End <<<<<<<");
        return finalResponse;
    }
}