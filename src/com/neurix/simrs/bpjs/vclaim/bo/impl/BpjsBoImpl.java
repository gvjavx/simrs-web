package com.neurix.simrs.bpjs.vclaim.bo.impl;

import com.google.gson.Gson;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class BpjsBoImpl extends BpjsService implements BpjsBo {

    protected static transient Logger logger = Logger.getLogger(BpjsBoImpl.class);

    public BpjsBoImpl() throws GeneralSecurityException, IOException {
    }
    private BranchDao branchDao;

    public static void setLogger(Logger logger) {
        BpjsBoImpl.logger = logger;
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
    public List<TindakanResponse> GetTindakanByAPIBpjs(String tindakanId, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.getListEntityJenisTindakan] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/procedure/"+tindakanId;
        String result;
        List<TindakanResponse> tindakanResponseList = new ArrayList<>();

        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.getListEntityJenisTindakan] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("procedure");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        TindakanResponse tindakanResponse = new TindakanResponse();
                        tindakanResponse.setKodeTindakanBpjs(obj.getString("kode"));
                        tindakanResponse.setNamaTindakanBpjs(obj.getString("nama"));

                        tindakanResponseList.add(tindakanResponse);
                    }
                }
            } catch (IOException | JSONException |GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[BPJSBoImpl.getListEntityJenisTindakan] End <<<<<<<");
        return tindakanResponseList;
    }

    @Override
    public List<ProvinsiResponse> GetProvinsiByAPIBpjs(String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetProvinsiByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/propinsi";
        String result = null;
        List<ProvinsiResponse> provinsiBpjsList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetProvinsiByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        ProvinsiResponse provinsiBpjs = new ProvinsiResponse();
                        provinsiBpjs.setKodeProvinsiBpjs(obj.getString("kode"));
                        provinsiBpjs.setNamaProvinsiBpjs(obj.getString("nama"));

                        provinsiBpjsList.add(provinsiBpjs);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetProvinsiByAPIBpjs] End <<<<<<<");
        return provinsiBpjsList;
    }

    @Override
    public List<KabupatenResponse> GetKabupatenByAPIBpjs(String provinsiId, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetKabupatenByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/kabupaten/propinsi/"+provinsiId;
        String result = null;
        List<KabupatenResponse> kabupatenBpjsList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetKabupatenByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        KabupatenResponse kabupatenBpjs = new KabupatenResponse();
                        kabupatenBpjs.setKodeKabupatenBpjs(obj.getString("kode"));
                        kabupatenBpjs.setNamaKabupatenBpjs(obj.getString("nama"));

                        kabupatenBpjsList.add(kabupatenBpjs);
                    }
                }
            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetKabupatenByAPIBpjs] End <<<<<<<");
        return kabupatenBpjsList;
    }
    @Override
    public List<KecamatanResponse> GetKecamatanByAPIBpjs(String kabupatenId, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetKecamatanByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/kecamatan/kabupaten/"+kabupatenId;
        String result = null;
        List<KecamatanResponse> kecamatanBpjsList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetKecamatanByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        KecamatanResponse kecamatanBpjs = new KecamatanResponse();
                        kecamatanBpjs.setKodeKecamatanBpjs(obj.getString("kode"));
                        kecamatanBpjs.setNamaKecamatanBpjs(obj.getString("nama"));

                        kecamatanBpjsList.add(kecamatanBpjs);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetKecamatanByAPIBpjs] End <<<<<<<");
        return kecamatanBpjsList;
    }
    @Override
    public List<KelasRawatResponse> GetKelasRawatByAPIBpjs(String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetKelasRawatByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/kelasrawat";
        String result = null;
        List<KelasRawatResponse> kelasRawatBpjsList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetKelasRawatByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        KelasRawatResponse kelasRawatBpjs = new KelasRawatResponse();
                        kelasRawatBpjs.setKodeKelasRawatBpjs(obj.getString("kode"));
                        kelasRawatBpjs.setNamaKelasRawatBpjs(obj.getString("nama"));

                        kelasRawatBpjsList.add(kelasRawatBpjs);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetKelasRawatByAPIBpjs] End <<<<<<<");
        return kelasRawatBpjsList;
    }
    @Override
    public List<DiagnosaResponse> getDiagnosaByAPIBpjs(String diagnosaId, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetDiagnosaByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/diagnosa/"+diagnosaId;
        String result = null;
        List<DiagnosaResponse> diagnosaResponseList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetDiagnosaByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("diagnosa");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        DiagnosaResponse diagnosaResponse = new DiagnosaResponse();
                        diagnosaResponse.setKodeDiagnosaBpjs(obj.getString("kode"));
                        diagnosaResponse.setNamaDiagnosaBpjs(obj.getString("nama"));

                        diagnosaResponseList.add(diagnosaResponse);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetDiagnosaByAPIBpjs] End <<<<<<<");
        return diagnosaResponseList;
    }

    @Override
    public List<PoliResponse> GetPoliByAPIBpjs(String poliId, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetDiagnosaByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "referensi/poli/"+poliId;
        String result = null;
        List<PoliResponse> poliResponseList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetDiagnosaByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("poli");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        PoliResponse poliResponse = new PoliResponse();
                        poliResponse.setKodePoliBpjs(obj.getString("kode"));
                        poliResponse.setNamaPoliBpjs(obj.getString("nama"));

                        poliResponseList.add(poliResponse);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetDiagnosaByAPIBpjs] End <<<<<<<");
        return poliResponseList;
    }
    @Override
    public List<FaskesResponse> GetFaskesByAPIBpjs(String faskesId, String jenisFaskes, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetFaskesByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/faskes/"+faskesId+"/"+jenisFaskes;
        String result = null;
        List<FaskesResponse> faskesResponseList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetFaskesByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        FaskesResponse faskesResponse = new FaskesResponse();
                        faskesResponse.setKodeFaskesBpjs(obj.getString("kode"));
                        faskesResponse.setNamaFaskesBpjs(obj.getString("nama"));

                        faskesResponseList.add(faskesResponse);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetFaskesByAPIBpjs] End <<<<<<<");
        return faskesResponseList;
    }
    @Override
    public List<DpjpResponse> GetDpjpByAPIBpjs(String jenisPelayanan, String tglPelayanan, String dpjpId, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetDpjpByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/dokter/pelayanan/"+jenisPelayanan+"/tglPelayanan/"+tglPelayanan+"/Spesialis/"+dpjpId;
        String result = null;
        List<DpjpResponse> dpjpResponseList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetDpjpByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        DpjpResponse dpjpResponse = new DpjpResponse();
                        dpjpResponse.setKodeDpjpBpjs(obj.getString("kode"));
                        dpjpResponse.setNamaDpjpBpjs(obj.getString("nama"));

                        dpjpResponseList.add(dpjpResponse);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetDpjpByAPIBpjs] End <<<<<<<");
        return dpjpResponseList;
    }
    @Override
    public List<DokterResponse> GetDokterByAPIBpjs(String dpjpId, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetDokterByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/dokter/"+dpjpId;
        String result = null;
        List<DokterResponse> dokterResponseList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetDokterByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        DokterResponse dokterResponse = new DokterResponse();
                        dokterResponse.setKodeDokterBpjs(obj.getString("kode"));
                        dokterResponse.setNamaDokterBpjs(obj.getString("nama"));

                        dokterResponseList.add(dokterResponse);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetDokterByAPIBpjs] End <<<<<<<");
        return dokterResponseList;
    }
    @Override
    public List<SpesialistikResponse> GetSpesialistikByAPIBpjs(String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetSpesialistikByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/spesialistik";
        String result = null;
        List<SpesialistikResponse> spesialistikResponseList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){

        }
        try {
            result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
            JSONObject myResponseCheck = new JSONObject(result);
            if (myResponseCheck.isNull("response")) {
                JSONObject response = myResponseCheck.getJSONObject("metaData");
                logger.error("[BPJSBoImpl.GetSpesialistikByAPIBpjs] : " + response.getString("message"));
            } else {
                JSONObject response = myResponseCheck.getJSONObject("response");
                JSONArray arrResponse = response.getJSONArray("list");

                int length = arrResponse.length();
                for (int i=0;i<length;i++){
                    JSONObject obj = arrResponse.getJSONObject(i);
                    SpesialistikResponse spesialistikResponse = new SpesialistikResponse();
                    spesialistikResponse.setKodeSpesialistikBpjs(obj.getString("kode"));
                    spesialistikResponse.setNamaSpesialistikBpjs(obj.getString("nama"));

                    spesialistikResponseList.add(spesialistikResponse);
                }
            }

        } catch (IOException | JSONException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetSpesialistikByAPIBpjs] End <<<<<<<");
        return spesialistikResponseList;
    }
    @Override
    public List<RuangRawatResponse> GetRuangRawatByAPIBpjs(String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetRuangRawatByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/ruangrawat";
        String result = null;
        List<RuangRawatResponse> ruangRawatResponseList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetRuangRawatByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        RuangRawatResponse ruangRawatResponse = new RuangRawatResponse();
                        ruangRawatResponse.setKodeRuangRawatBpjs(obj.getString("kode"));
                        ruangRawatResponse.setNamaRuangRawatBpjs(obj.getString("nama"));

                        ruangRawatResponseList.add(ruangRawatResponse);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetRuangRawatByAPIBpjs] End <<<<<<<");
        return ruangRawatResponseList;
    }
    @Override
    public List<CaraKeluarResponse> GetCaraKeluarByAPIBpjs(String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetCaraKeluarByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/carakeluar";
        String result = null;
        List<CaraKeluarResponse> caraKeluarResponseList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetCaraKeluarByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        CaraKeluarResponse caraKeluarResponse = new CaraKeluarResponse();
                        caraKeluarResponse.setKodeCaraKeluarBpjs(obj.getString("kode"));
                        caraKeluarResponse.setNamaCaraKeluarBpjs(obj.getString("nama"));

                        caraKeluarResponseList.add(caraKeluarResponse);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.GetCaraKeluarByAPIBpjs] End <<<<<<<");
        return caraKeluarResponseList;
    }
    @Override
    public List<PascaPulangResponse> GetPascaPulangByAPIBpjs(String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetPascaPulangByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/pascapulang";
        String result = null;
        List<PascaPulangResponse> pascaPulangResponseList = new ArrayList<>();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetPascaPulangByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("list");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        PascaPulangResponse pascaPulangResponse = new PascaPulangResponse();
                        pascaPulangResponse.setKodePascaPulangBpjs(obj.getString("kode"));
                        pascaPulangResponse.setNamaPascaPulangBpjs(obj.getString("nama"));

                        pascaPulangResponseList.add(pascaPulangResponse);
                    }
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[BPJSBoImpl.GetPascaPulangByAPIBpjs] End <<<<<<<");
        return pascaPulangResponseList;
    }
    @Override
    public PesertaResponse GetPesertaBpjsByAPIBpjs(String noBpjs, String tanggalSep, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetPesertaBpjsByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Peserta/nokartu/"+noBpjs+"/tglSEP/"+tanggalSep;
        PesertaResponse finalResult = new PesertaResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                String result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetPesertaBpjsByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");

                    JSONObject peserta = response.getJSONObject("peserta");

                    JSONObject cob = peserta.getJSONObject("cob");
                    if (!cob.isNull("nmAsuransi")){
                        finalResult.setNmAsuransi(cob.getString("nmAsuransi"));
                    }
                    if (!cob.isNull("noAsuransi")){
                        finalResult.setNoAsuransi(cob.getString("noAsuransi"));
                    }
                    if (!cob.isNull("tglTAT")){
                        finalResult.setTglTAT(cob.getString("tglTAT"));
                    }
                    if (!cob.isNull("tglTMT")){
                        finalResult.setTglTMT(cob.getString("tglTMT"));
                    }

                    JSONObject hakKelas = peserta.getJSONObject("hakKelas");
                    if (!hakKelas.isNull("kode")){
                        finalResult.setKeteranganKelas(hakKelas.getString("keterangan"));
                    }
                    if (!hakKelas.isNull("keterangan")){
                        finalResult.setKodeKelas(hakKelas.getString("kode"));
                    }

                    JSONObject informasi = peserta.getJSONObject("informasi");
                    if (!informasi.isNull("dinsos")){
                        finalResult.setDinsos(informasi.getString("dinsos"));
                    }
                    if (!informasi.isNull("noSKTM")){
                        finalResult.setNoSKTM(informasi.getString("noSKTM"));
                    }
                    if (!informasi.isNull("prolanisPRB")){
                        finalResult.setProlanisPRB(informasi.getString("prolanisPRB"));
                    }

                    JSONObject jenisPeserta = peserta.getJSONObject("jenisPeserta");
                    if (!jenisPeserta.isNull("keterangan")){
                        finalResult.setKeteranganJenisPeserta(jenisPeserta.getString("keterangan"));
                    }
                    if (!jenisPeserta.isNull("kode")){
                        finalResult.setKodeJenisPeserta(jenisPeserta.getString("kode"));
                    }

                    JSONObject mr = peserta.getJSONObject("mr");
                    if (!mr.isNull("noMR")){
                        finalResult.setNoMr(mr.getString("noMR"));
                    }
                    if (!mr.isNull("noTelepon")){
                        finalResult.setNoTelp(mr.getString("noTelepon"));
                    }

                    if (!peserta.isNull("nama")){
                        finalResult.setNama(peserta.getString("nama"));
                    }
                    if (!peserta.isNull("nik")){
                        finalResult.setNik(peserta.getString("nik"));
                    }
                    if (!peserta.isNull("noKartu")){
                        finalResult.setNoKartu(peserta.getString("noKartu"));
                    }
                    if (!peserta.isNull("pisa")){
                        finalResult.setPisa(peserta.getString("pisa"));
                    }

                    JSONObject provUmum = peserta.getJSONObject("provUmum");
                    if (!provUmum.isNull("kdProvider")){
                        finalResult.setKodeProvider(provUmum.getString("kdProvider"));
                    }
                    if (!provUmum.isNull("kdProvider")){
                        finalResult.setNamaProvider(provUmum.getString("nmProvider"));
                    }

                    if (!peserta.isNull("sex")){
                        finalResult.setSex(peserta.getString("sex"));
                    }

                    JSONObject statusPeserta = peserta.getJSONObject("statusPeserta");
                    if (!statusPeserta.isNull("keterangan")){
                        finalResult.setKeteranganStatusPeserta(statusPeserta.getString("keterangan"));
                    }
                    if (!statusPeserta.isNull("kode")){
                        finalResult.setKodeStatusPeserta(statusPeserta.getString("kode"));
                    }

                    if (!peserta.isNull("tglCetakKartu")){
                        finalResult.setStTglCetakKartu(peserta.getString("tglCetakKartu"));
                    }
                    if (!peserta.isNull("tglLahir")){
                        finalResult.setStTglLahir(peserta.getString("tglLahir"));
                    }
                    if (!peserta.isNull("tglTAT")){
                        finalResult.setStTglTAT(peserta.getString("tglTAT"));
                    }
                    if (!peserta.isNull("tglTMT")){
                        finalResult.setStTglTMT(peserta.getString("tglTMT"));
                    }

                    JSONObject umur = peserta.getJSONObject("umur");
                    if (!umur.isNull("umurSaatPelayanan")){
                        finalResult.setUmurSaatPelayanan(umur.getString("umurSaatPelayanan"));
                    }
                    if (!umur.isNull("umurSekarang")){
                        finalResult.setUmurSekarang(umur.getString("umurSekarang"));
                    }
                }
            } catch (Exception e) {
                logger.error("[BPJSBoImpl.GetPesertaBpjsByAPIBpjs] Error when get data");
            }
        }

        logger.info("[BPJSBoImpl.GetPesertaBpjsByAPIBpjs] End <<<<<<<");
        return finalResult;
    }
    @Override
    public PesertaResponse GetPesertaNikByAPIBpjs(String nik, String tanggalSep, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetPesertaNikByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Peserta/nik/"+nik+"/tglSEP/"+tanggalSep;
        PesertaResponse finalResult = new PesertaResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                String result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetPesertaNikByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");

                    JSONObject peserta = response.getJSONObject("peserta");

                    JSONObject cob = peserta.getJSONObject("cob");
                    if (!cob.isNull("nmAsuransi")){
                        finalResult.setNmAsuransi(cob.getString("nmAsuransi"));
                    }
                    if (!cob.isNull("noAsuransi")){
                        finalResult.setNoAsuransi(cob.getString("noAsuransi"));
                    }
                    if (!cob.isNull("tglTAT")){
                        finalResult.setTglTAT(cob.getString("tglTAT"));
                    }
                    if (!cob.isNull("tglTMT")){
                        finalResult.setTglTMT(cob.getString("tglTMT"));
                    }

                    JSONObject hakKelas = peserta.getJSONObject("hakKelas");
                    if (!hakKelas.isNull("kode")){
                        finalResult.setKeteranganKelas(hakKelas.getString("keterangan"));
                    }
                    if (!hakKelas.isNull("keterangan")){
                        finalResult.setKodeKelas(hakKelas.getString("kode"));
                    }

                    JSONObject informasi = peserta.getJSONObject("informasi");
                    if (!informasi.isNull("dinsos")){
                        finalResult.setDinsos(informasi.getString("dinsos"));
                    }
                    if (!informasi.isNull("noSKTM")){
                        finalResult.setNoSKTM(informasi.getString("noSKTM"));
                    }
                    if (!informasi.isNull("prolanisPRB")){
                        finalResult.setProlanisPRB(informasi.getString("prolanisPRB"));
                    }

                    JSONObject jenisPeserta = peserta.getJSONObject("jenisPeserta");
                    if (!jenisPeserta.isNull("keterangan")){
                        finalResult.setKeteranganJenisPeserta(jenisPeserta.getString("keterangan"));
                    }
                    if (!jenisPeserta.isNull("kode")){
                        finalResult.setKodeJenisPeserta(jenisPeserta.getString("kode"));
                    }

                    JSONObject mr = peserta.getJSONObject("mr");
                    if (!mr.isNull("noMR")){
                        finalResult.setNoMr(mr.getString("noMR"));
                    }
                    if (!mr.isNull("noTelepon")){
                        finalResult.setNoTelp(mr.getString("noTelepon"));
                    }

                    if (!peserta.isNull("nama")){
                        finalResult.setNama(peserta.getString("nama"));
                    }
                    if (!peserta.isNull("nik")){
                        finalResult.setNik(peserta.getString("nik"));
                    }
                    if (!peserta.isNull("noKartu")){
                        finalResult.setNoKartu(peserta.getString("noKartu"));
                    }
                    if (!peserta.isNull("pisa")){
                        finalResult.setPisa(peserta.getString("pisa"));
                    }

                    JSONObject provUmum = peserta.getJSONObject("provUmum");
                    if (!provUmum.isNull("kdProvider")){
                        finalResult.setKodeProvider(provUmum.getString("kdProvider"));
                    }
                    if (!provUmum.isNull("kdProvider")){
                        finalResult.setNamaProvider(provUmum.getString("nmProvider"));
                    }

                    if (!peserta.isNull("sex")){
                        finalResult.setSex(peserta.getString("sex"));
                    }

                    JSONObject statusPeserta = peserta.getJSONObject("statusPeserta");
                    if (!statusPeserta.isNull("keterangan")){
                        finalResult.setKeteranganStatusPeserta(statusPeserta.getString("keterangan"));
                    }
                    if (!statusPeserta.isNull("kode")){
                        finalResult.setKodeStatusPeserta(statusPeserta.getString("kode"));
                    }

                    if (!peserta.isNull("tglCetakKartu")){
                        finalResult.setStTglCetakKartu(peserta.getString("tglCetakKartu"));
                    }
                    if (!peserta.isNull("tglLahir")){
                        finalResult.setStTglLahir(peserta.getString("tglLahir"));
                    }
                    if (!peserta.isNull("tglTAT")){
                        finalResult.setStTglTAT(peserta.getString("tglTAT"));
                    }
                    if (!peserta.isNull("tglTMT")){
                        finalResult.setStTglTMT(peserta.getString("tglTMT"));
                    }

                    JSONObject umur = peserta.getJSONObject("umur");
                    if (!umur.isNull("umurSaatPelayanan")){
                        finalResult.setUmurSaatPelayanan(umur.getString("umurSaatPelayanan"));
                    }
                    if (!umur.isNull("umurSekarang")){
                        finalResult.setUmurSekarang(umur.getString("umurSekarang"));
                    }

                }
            } catch (Exception e) {
                logger.error("[BPJSBoImpl.GetPesertaNikByAPIBpjs] Error when get data");
            }
        }

        logger.info("[BPJSBoImpl.GetPesertaNikByAPIBpjs] End <<<<<<<");
        return finalResult;
    }


    //----------------------------------------!! SEP !!-------------------------------------------//
    @Override
    public SepResponse insertSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.insertSepBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "SEP/1.1/insert";
        JSONObject request = null;
        String jsonData="        {\n" +
                "           \"request\": {\n" +
                "              \"t_sep\": {\n" +
                "                 \"noKartu\": \""+sepRequest.getNoKartu()+"\",\n" +
                "                 \"tglSep\": \""+sepRequest.getTglSep()+"\",\n" +
                "                 \"ppkPelayanan\": \""+sepRequest.getPpkPelayanan()+"\",\n" +
                "                 \"jnsPelayanan\": \""+sepRequest.getJnsPelayanan()+"\",\n" +
                "                 \"klsRawat\": \""+sepRequest.getKlsRawat()+"\",\n" +
                "                 \"noMR\": \""+sepRequest.getNoMr()+"\",\n" +
                "                 \"rujukan\": {\n" +
                "                    \"asalRujukan\": \""+sepRequest.getAsalRujukan()+"\",\n" +
                "                    \"tglRujukan\": \""+sepRequest.getTglRujukan()+"\",\n" +
                "                    \"noRujukan\": \""+sepRequest.getNoRujukan()+"\",\n" +
                "                    \"ppkRujukan\": \""+sepRequest.getPpkRujukan()+"\"\n" +
                "                 },\n" +
                "                 \"catatan\": \""+sepRequest.getCatatan()+"\",\n" +
                "                 \"diagAwal\": \""+sepRequest.getDiagAwal()+"\",\n" +
                "                 \"poli\": {\n" +
                "                    \"tujuan\": \""+sepRequest.getPoliTujuan()+"\",\n" +
                "                    \"eksekutif\": \""+sepRequest.getPoliEksekutif()+"\"\n" +
                "                 },\n" +
                "                 \"cob\": {\n" +
                "                    \"cob\": \""+sepRequest.getCob()+"\"\n" +
                "                 },\n" +
                "                 \"katarak\": {\n" +
                "                    \"katarak\": \""+sepRequest.getKatarak()+"\"\n" +
                "                 },\n" +
                "                 \"jaminan\": {\n" +
                "                    \"lakaLantas\": \""+sepRequest.getLakaLantas()+"\",\n" +
                "                    \"penjamin\": {\n" +
                "                        \"penjamin\": \""+sepRequest.getPenjamin()+"\",\n" +
                "                        \"tglKejadian\": \""+sepRequest.getTglKejadian()+"\",\n" +
                "                        \"keterangan\": \""+sepRequest.getKeterangan()+"\",\n" +
                "                        \"suplesi\": {\n" +
                "                            \"suplesi\": \""+sepRequest.getSuplesi()+"\",\n" +
                "                            \"noSepSuplesi\": \""+sepRequest.getNoSepSuplesi()+"\",\n" +
                "                            \"lokasiLaka\": {\n" +
                "                                \"kdPropinsi\": \""+sepRequest.getKdProvinsiLakaLantas()+"\",\n" +
                "                                \"kdKabupaten\": \""+sepRequest.getKdKabupatenLakaLantas()+"\",\n" +
                "                                \"kdKecamatan\": \""+sepRequest.getKdKecamatanLakaLantas()+"\"\n" +
                "                                }\n" +
                "                        }\n" +
                "                    }\n" +
                "                 },\n" +
                "                 \"skdp\": {\n" +
                "                    \"noSurat\": \""+sepRequest.getNoSuratSkdp()+"\",\n" +
                "                    \"kodeDPJP\": \""+sepRequest.getKodeDpjp()+"\"\n" +
                "                 },\n" +
                "                 \"noTelp\": \""+sepRequest.getNoTelp()+"\",\n" +
                "                 \"user\": \""+sepRequest.getUserPembuatSep()+"\"\n" +
                "              }\n" +
                "           }\n" +
                "        }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        SepResponse sepResponse = new SepResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.insertSepBpjs] : " + response.getString("message"));
                    sepResponse.setMessage(response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONObject sep = response.getJSONObject("sep");

                    sepResponse.setCatatan(sep.getString("catatan"));
                    sepResponse.setDiagnosa(sep.getString("diagnosa"));
                    sepResponse.setJnsPelayanan(sep.getString("jnsPelayanan"));
                    sepResponse.setKelasRawat(sep.getString("kelasRawat"));
                    sepResponse.setNoSep(sep.getString("noSep"));
                    sepResponse.setPenjamin(sep.getString("penjamin"));

                    JSONObject peserta = sep.getJSONObject("peserta");
                    sepResponse.setAsuransi(peserta.getString("asuransi"));
                    sepResponse.setHakKelas(peserta.getString("hakKelas"));
                    sepResponse.setJnsPeserta(peserta.getString("jnsPeserta"));
                    sepResponse.setKelamin(peserta.getString("kelamin"));
                    sepResponse.setNama(peserta.getString("nama"));
                    sepResponse.setNoKartu(peserta.getString("noKartu"));
                    sepResponse.setNoMr(peserta.getString("noMr"));
                    sepResponse.setTglLahir(peserta.getString("tglLahir"));

                    JSONObject informasi = sep.getJSONObject("informasi");
                    if (!informasi.isNull("Dinsos")){
                        sepResponse.setDinsos(informasi.getString("Dinsos"));
                    }
                    if (!informasi.isNull("prolanisPRB")){
                        sepResponse.setProlanisPrb(informasi.getString("prolanisPRB"));
                    }
                    if (!informasi.isNull("noSKTM")){
                        sepResponse.setNoSktm(informasi.getString("noSKTM"));
                    }

                    sepResponse.setPoli(sep.getString("poli"));
                    sepResponse.setPoliEksekutif(sep.getString("poliEksekutif"));
                    sepResponse.setTglSep(sep.getString("tglSep"));
                }
            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
                sepResponse.setMessage("Found Error "+e);
            }
        }

        logger.info("[BPJSBoImpl.insertSepBpjs] End <<<<<<<");
        return sepResponse;
    }
    @Override
    public SepResponse updateSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.updateSepBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/SEP/1.1/Update";
        JSONObject request = null;
        String jsonData="{\n" +
                "       \"request\": {\n" +
                "          \"t_sep\": {\n" +
                "             \"noSep\": \""+sepRequest.getNoSep()+"\",\n" +
                "             \"klsRawat\": \""+sepRequest.getKlsRawat()+"\",\n" +
                "             \"noMR\": \""+sepRequest.getNoMr()+"\",\n" +
                "             \"rujukan\": {\n" +
                "                \"asalRujukan\": \""+sepRequest.getAsalRujukan()+"\",\n" +
                "                \"tglRujukan\": \""+sepRequest.getTglRujukan()+"\",\n" +
                "                \"noRujukan\": \""+sepRequest.getNoRujukan()+"\",\n" +
                "                \"ppkRujukan\": \""+sepRequest.getPpkRujukan()+"\"\n" +
                "             },\n" +
                "             \"catatan\": \""+sepRequest.getCatatan()+"\",\n" +
                "             \"diagAwal\": \""+sepRequest.getDiagAwal()+"\",\n" +
                "             \"poli\": {\n" +
                "                \"eksekutif\": \""+sepRequest.getPoliEksekutif()+"\"\n" +
                "             },\n" +
                "             \"cob\": {\n" +
                "                \"cob\": \""+sepRequest.getCob()+"\"\n" +
                "             },\n" +
                "             \"katarak\":{\n" +
                "                \"katarak\":\""+sepRequest.getKatarak()+"\"\n" +
                "             },\n" +
                "             \"skdp\":{\n" +
                "                \"noSurat\":\""+sepRequest.getNoSuratSkdp()+"\",\n" +
                "                \"kodeDPJP\":\""+sepRequest.getKodeDpjp()+"\"\n" +
                "             },\n" +
                "             \"jaminan\": {\n" +
                "                \"lakaLantas\":\""+sepRequest.getLakaLantas()+"\",\n" +
                "                \"penjamin\":\n" +
                "                {\n" +
                "                    \"penjamin\":\""+sepRequest.getPenjamin()+"\",\n" +
                "                    \"tglKejadian\":\""+sepRequest.getTglKejadian()+"\",\n" +
                "                    \"keterangan\":\""+sepRequest.getKeterangan()+"\",\n" +
                "                    \"suplesi\":\n" +
                "                        {\n" +
                "                            \"suplesi\":\""+sepRequest.getSuplesi()+"\",\n" +
                "                            \"noSepSuplesi\":\""+sepRequest.getNoSepSuplesi()+"\",\n" +
                "                            \"lokasiLaka\": \n" +
                "                                {\n" +
                "                                \"kdPropinsi\":\""+sepRequest.getKdProvinsiLakaLantas()+"\",\n" +
                "                                \"kdKabupaten\":\""+sepRequest.getKdKabupatenLakaLantas()+"\",\n" +
                "                                \"kdKecamatan\":\""+sepRequest.getKdKecamatanLakaLantas()+"\"\n" +
                "                                }\n" +
                "                        }\t\t\t\t\t\n" +
                "                }\n" +
                "             },             \n" +
                "             \"noTelp\": \""+sepRequest.getNoTelp()+"\",\n" +
                "             \"user\": \""+sepRequest.getUserPembuatSep()+"\"\n" +
                "          }\n" +
                "       }\n" +
                "    } ";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        SepResponse sepResponse = new SepResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.updateSepBpjs] : " + response.getString("message"));
                } else {
                    sepResponse.setNoSep(myResponseCheck.getString("response"));
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.updateSepBpjs] End <<<<<<<");
        return sepResponse;
    }
    @Override
    public SepResponse deleteSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.deleteSepBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "SEP/Delete";
        JSONObject request = null;
        String jsonData="{\n" +
                "       \"request\": {\n" +
                "          \"t_sep\": {\n" +
                "             \"noSep\": \""+sepRequest.getNoSep()+"\",\n" +
                "             \"user\": \""+sepRequest.getUserPembuatSep()+"\"\n" +
                "          }\n" +
                "       }\n" +
                "    }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        SepResponse sepResponse = new SepResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                if (!"method not allowed".equalsIgnoreCase(result)){
                    JSONObject myResponseCheck = new JSONObject(result);
                    if (myResponseCheck.isNull("response")) {
                        JSONObject response = myResponseCheck.getJSONObject("metaData");
                        logger.error("[BPJSBoImpl.deleteSepBpjs] : " + response.getString("message"));
                    } else {
                        sepResponse.setNoSep(myResponseCheck.getString("response"));
                    }
                } else {
                    logger.error("[BPJSBoImpl.deleteSepBpjs] : Method Not Allowed");
                }


            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.deleteSepBpjs] End <<<<<<<");
        return sepResponse;
    }

    @Override
    public SepResponse GetSepBpjsByAPIBpjs(String noSep, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetSepBpjsByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/SEP/"+noSep;
        SepResponse finalResult = new SepResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                String result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.GetSepBpjsByAPIBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    if (!response.isNull("catatan")){
                        finalResult.setCatatan(response.getString("catatan"));
                    }
                    if (!response.isNull("diagnosa")){
                        finalResult.setDiagnosa(response.getString("diagnosa"));
                    }
                    if (!response.isNull("jnsPelayanan")){
                        finalResult.setJnsPelayanan(response.getString("jnsPelayanan"));
                    }
                    if (!response.isNull("kelasRawat")){
                        finalResult.setKelasRawat(response.getString("kelasRawat"));
                    }
                    if (!response.isNull("noSep")){
                        finalResult.setNoSep(response.getString("noSep"));
                    }
                    if (!response.isNull("noRujukan")){
                        finalResult.setNoRujukan(response.getString("noRujukan"));
                    }
                    if (!response.isNull("penjamin")){
                        finalResult.setPenjamin(response.getString("penjamin"));
                    }

                    JSONObject peserta = response.getJSONObject("peserta");
                    if (!peserta.isNull("asuransi")){
                        finalResult.setAsuransi(peserta.getString("asuransi"));
                    }
                    if (!peserta.isNull("hakKelas")){
                        finalResult.setHakKelas(peserta.getString("hakKelas"));
                    }
                    if (!peserta.isNull("jnsPeserta")){
                        finalResult.setJnsPeserta(peserta.getString("jnsPeserta"));
                    }
                    if (!peserta.isNull("kelamin")){
                        finalResult.setKelamin(peserta.getString("kelamin"));
                    }
                    if (!peserta.isNull("nama")){
                        finalResult.setNama(peserta.getString("nama"));
                    }
                    if (!peserta.isNull("noKartu")){
                        finalResult.setNoKartu(peserta.getString("noKartu"));
                    }
                    if (!peserta.isNull("noMr")){
                        finalResult.setNoMr(peserta.getString("noMr"));
                    }
                    if (!peserta.isNull("tglLahir")){
                        finalResult.setTglLahir(peserta.getString("tglLahir"));
                    }

                    if (!response.isNull("poli")){
                        finalResult.setPenjamin(response.getString("poli"));
                    }
                    if (!response.isNull("poliEksekutif")){
                        finalResult.setPenjamin(response.getString("poliEksekutif"));
                    }
                    if (!response.isNull("tglSep")){
                        finalResult.setPenjamin(response.getString("tglSep"));
                    }
                }
            } catch (Exception e) {
                logger.error("[BPJSBoImpl.GetSepBpjsByAPIBpjs] Error when get data");
            }
        }

        logger.info("[BPJSBoImpl.GetSepBpjsByAPIBpjs] End <<<<<<<");
        return finalResult;
    }
    @Override
    public SepResponse updateTglPulangSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.updateTglPulangSepBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/SEP/Delete";
        JSONObject request = null;
        String jsonData="{  \n" +
                "            \"request\": \n" +
                "                {    \n" +
                "                \"t_sep\":\n" +
                "                    {\n" +
                "                        \"noSep\":\""+sepRequest.getNoSep()+"\",\n" +
                "                        \"tglPulang\":\""+sepRequest.getTglPulang()+"\",\n" +
                "                        \"user\":\""+sepRequest.getUserPembuatSep()+"\"\t\n" +
                "                    }\n" +
                "                }\n" +
                "        }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        SepResponse sepResponse = new SepResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.updateTglPulangSepBpjs] : " + response.getString("message"));
                } else {
                    sepResponse.setNoSep(myResponseCheck.getString("response"));
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.updateTglPulangSepBpjs] End <<<<<<<");
        return sepResponse;
    }
    @Override
    public SepResponse searchSepInacbgBpjs(String noSep, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.searchSepInacbgBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/sep/cbg/"+noSep;
        SepResponse finalResult = new SepResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.getListEntityJenisTindakan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                String result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.searchSepInacbgBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONObject peserta = response.getJSONObject("pesertasep");
                    finalResult.setKelamin(peserta.getString("kelamin"));
                    finalResult.setKelasRawat(peserta.getString("klsRawat"));
                    finalResult.setNama(peserta.getString("nama"));
                    finalResult.setNoKartu(peserta.getString("noKartuBpjs"));
                    finalResult.setNoMr(peserta.getString("noMr"));
                    finalResult.setNoRujukan(peserta.getString("noRujukan"));
                    finalResult.setTglLahir(peserta.getString("tglLahir"));
                    finalResult.setTglPelayanan(peserta.getString("tglPelayanan"));
                    finalResult.setTktPelayanan(peserta.getString("tktPelayanan"));
                }
            } catch (Exception e) {
                logger.error("[BPJSBoImpl.searchSepInacbgBpjs] Error when get data");
            }
        }

        logger.info("[BPJSBoImpl.searchSepInacbgBpjs] End <<<<<<<");
        return finalResult;
    }

    @Override
    public List<SepResponse> suplesiJasaRaharjaBpjs(String noKartuBpjs, String tglPelayanan, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.suplesiJasaRaharjaBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/sep/JasaRaharja/Suplesi/"+noKartuBpjs+"/tglPelayanan/"+tglPelayanan;
        String result;
        List<SepResponse> responseList = new ArrayList<>();

        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.suplesiJasaRaharjaBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.suplesiJasaRaharjaBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray arrResponse = response.getJSONArray("jaminan");

                    int length = arrResponse.length();
                    for (int i=0;i<length;i++){
                        JSONObject obj = arrResponse.getJSONObject(i);
                        SepResponse sepResponse = new SepResponse();
                        sepResponse.setNoRegister(obj.getString("noRegister"));
                        sepResponse.setNoSep(obj.getString("noSep"));
                        sepResponse.setNoSepAwal(obj.getString("noSepAwal"));
                        sepResponse.setNoSuratJaminan(obj.getString("noSuratJaminan"));
                        sepResponse.setTglKejadian(obj.getString("tglKejadian"));
                        sepResponse.setTglSep(obj.getString("tglSep"));

                        responseList.add(sepResponse);
                    }
                }
            } catch (IOException | JSONException |GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[BPJSBoImpl.suplesiJasaRaharjaBpjs] End <<<<<<<");
        return responseList;
    }

    @Override
    public SepResponse pengajuanPenjaminanSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.pengajuanPenjaminanSepBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Sep/pengajuanSEP";
        JSONObject request = null;
        String jsonData="{\n" +
                "       \"request\": {\n" +
                "          \"t_sep\": {\n" +
                "             \"noKartu\": \""+sepRequest.getNoKartu()+"\",\n" +
                "             \"tglSep\": \""+sepRequest.getTglSep()+"\",\n" +
                "             \"jnsPelayanan\": \""+sepRequest.getJnsPelayanan()+"\",\n" +
                "             \"keterangan\": \""+sepRequest.getKeterangan()+"\",\n" +
                "             \"user\": \""+sepRequest.getUserPembuatSep()+"\"\n" +
                "          }\n" +
                "       }\n" +
                "    }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        SepResponse sepResponse = new SepResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.pengajuanPenjaminanSepBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.pengajuanPenjaminanSepBpjs] : " + response.getString("message"));
                } else {
                    sepResponse.setNoSep(myResponseCheck.getString("response"));
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.pengajuanPenjaminanSepBpjs] End <<<<<<<");
        return sepResponse;
    }

    @Override
    public SepResponse ApprovalPengajuanSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.ApprovalPengajuanSepBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/SEP/Sep/aprovalSEP";
        JSONObject request = null;
        String jsonData="{\n" +
                "       \"request\": {\n" +
                "          \"t_sep\": {\n" +
                "             \"noKartu\": \""+sepRequest.getNoKartu()+"\",\n" +
                "             \"tglSep\": \""+sepRequest.getTglSep()+"\",\n" +
                "             \"jnsPelayanan\": \""+sepRequest.getJnsPelayanan()+"\",\n" +
                "             \"keterangan\": \""+sepRequest.getKeterangan()+"\",\n" +
                "             \"user\": \""+sepRequest.getUserPembuatSep()+"\"\n" +
                "          }\n" +
                "       }\n" +
                "    }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        SepResponse sepResponse = new SepResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.ApprovalPengajuanSepBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.ApprovalPengajuanSepBpjs] : " + response.getString("message"));
                } else {
                    sepResponse.setNoSep(myResponseCheck.getString("response"));
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.ApprovalPengajuanSepBpjs] End <<<<<<<");
        return sepResponse;
    }

    //----------------------------------------!! END SEP !!-------------------------------------------//

    //----------------------------------------!! RUJUKAN !!-------------------------------------------//
    @Override
    public RujukanResponse caraRujukanBerdasarNomorBpjs(String noRujukan,String jenisCari, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.caraRujukanBerdasarNomorBpjs] Start >>>>>>>");
        String feature = "";
        RujukanResponse rujukanResponse = new RujukanResponse();
        //Jenis Cari
        //R = rumah sakit ----rumah sakit
        //P = Pcare ----untuk klinik
        switch (jenisCari){
            case "R":
                feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"Rujukan/RS/"+noRujukan;
            break;
            case "P":
                feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"Rujukan/"+noRujukan;
            break;
        }
        if (!feature.equalsIgnoreCase("")){
            String result;

            ImBranches resultBranch = null;
            try {
                // Get data from database by ID
                resultBranch = branchDao.getConsSecrBranchById(unitId);
            } catch (HibernateException e) {
                logger.error("[BPJSBoImpl.caraRujukanBerdasarNomorBpjs] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
            }
            if (resultBranch != null){
                try {
                    result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                    JSONObject myResponseCheck = new JSONObject(result);
                    if (myResponseCheck.isNull("response")) {
                        JSONObject response = myResponseCheck.getJSONObject("metaData");
                        logger.error("[BPJSBoImpl.caraRujukanBerdasarNomorBpjs] : " + response.getString("message"));
                        rujukanResponse.setStatus(response.getString("code"));
                        rujukanResponse.setMessage(response.getString("message"));
                    } else {
                        JSONObject responseStatus = myResponseCheck.getJSONObject("metaData");
                        JSONObject response = myResponseCheck.getJSONObject("response");
                        JSONObject rujukan = response.getJSONObject("rujukan");

                        JSONObject diagnosa = rujukan.getJSONObject("diagnosa");
                        if (!diagnosa.isNull("kode")){
                            rujukanResponse.setKodeDiagnosa(diagnosa.getString("kode"));
                        }
                        if (!diagnosa.isNull("nama")){
                            rujukanResponse.setNamaDiagnosa(diagnosa.getString("nama"));
                        }

                        if (!rujukan.isNull("keluhan")){
                            rujukanResponse.setKeluhan(rujukan.getString("keluhan"));
                        }
                        if (!rujukan.isNull("noKunjungan")){
                            rujukanResponse.setNoKunjungan(rujukan.getString("noKunjungan"));
                        }

                        JSONObject pelayanan = rujukan.getJSONObject("pelayanan");
                        if (!pelayanan.isNull("kode")){
                            rujukanResponse.setKodePelayanan(pelayanan.getString("kode"));
                        }
                        if (!pelayanan.isNull("nama")){
                            rujukanResponse.setNamaPelayanan(pelayanan.getString("nama"));
                        }

                        JSONObject peserta = rujukan.getJSONObject("peserta");
                        JSONObject cob = peserta.getJSONObject("cob");
                        if (!cob.isNull("nmAsuransi")){
                            rujukanResponse.setNmAsuransiCob(cob.getString("nmAsuransi"));
                        }
                        if (!cob.isNull("noAsuransi")){
                            rujukanResponse.setNoAsuransiCob(cob.getString("noAsuransi"));
                        }
                        if (!cob.isNull("tglTAT")){
                            rujukanResponse.setTglTatCob(cob.getString("tglTAT"));
                        }
                        if (!cob.isNull("tglTMT")){
                            rujukanResponse.setTglTmtCob(cob.getString("tglTMT"));
                        }

                        JSONObject hakKelas = peserta.getJSONObject("hakKelas");
                        if (!hakKelas.isNull("keterangan")){
                            rujukanResponse.setKeteranganHakKelas(hakKelas.getString("keterangan"));
                        }
                        if (!hakKelas.isNull("kode")){
                            rujukanResponse.setKodeHakKelas(hakKelas.getString("kode"));
                        }

                        JSONObject informasi = peserta.getJSONObject("informasi");
                        if (!informasi.isNull("dinsos")){
                            rujukanResponse.setDinsos(informasi.getString("dinsos"));
                        }
                        if (!informasi.isNull("noSKTM")){
                            rujukanResponse.setNoSktm(informasi.getString("noSKTM"));
                        }
                        if (!informasi.isNull("prolanisPRB")){
                            rujukanResponse.setProlanisPrb(informasi.getString("prolanisPRB"));
                        }


                        JSONObject jenisPeserta = peserta.getJSONObject("jenisPeserta");
                        if (!jenisPeserta.isNull("keterangan")){
                            rujukanResponse.setKeteranganJenisPeserta(jenisPeserta.getString("keterangan"));
                        }
                        if (!jenisPeserta.isNull("kode")){
                            rujukanResponse.setKodeJenisPeserta(jenisPeserta.getString("kode"));
                        }

                        JSONObject mr = peserta.getJSONObject("mr");
                        if (!mr.isNull("noMR")){
                            rujukanResponse.setNoMr(mr.getString("noMR"));
                        }
                        if (!mr.isNull("noTelepon")){
                            rujukanResponse.setNoTelp(mr.getString("noTelepon"));
                        }

                        if (!peserta.isNull("nama")){
                            rujukanResponse.setNama(peserta.getString("nama"));
                        }
                        if (!peserta.isNull("nik")){
                            rujukanResponse.setNik(peserta.getString("nik"));
                        }
                        if (!peserta.isNull("noKartu")){
                            rujukanResponse.setNoKartu(peserta.getString("noKartu"));
                        }
                        if (!peserta.isNull("pisa")){
                            rujukanResponse.setPisa(peserta.getString("pisa"));
                        }

                        JSONObject provUmum = peserta.getJSONObject("provUmum");
                        if (!provUmum.isNull("kdProvider")){
                            rujukanResponse.setKdProviderProvUmum(provUmum.getString("kdProvider"));
                        }
                        if (!provUmum.isNull("nmProvider")){
                            rujukanResponse.setNmProviderProvUmum(provUmum.getString("nmProvider"));
                        }

                        if (!peserta.isNull("sex")){
                            rujukanResponse.setSex(peserta.getString("sex"));
                        }

                        JSONObject statusPeserta = peserta.getJSONObject("statusPeserta");
                        if (!statusPeserta.isNull("keterangan")){
                            rujukanResponse.setKeteranganStatusPeserta(statusPeserta.getString("keterangan"));
                        }
                        if (!statusPeserta.isNull("kode")){
                            rujukanResponse.setKodeStatusPeserta(statusPeserta.getString("kode"));
                        }

                        if (!peserta.isNull("tglCetakKartu")){
                            rujukanResponse.setTglCetakKartu(peserta.getString("tglCetakKartu"));
                        }
                        if (!peserta.isNull("tglLahir")){
                            rujukanResponse.setTglLahir(peserta.getString("tglLahir"));
                        }
                        if (!peserta.isNull("tglTAT")){
                            rujukanResponse.setTglTat(peserta.getString("tglTAT"));
                        }
                        if (!peserta.isNull("tglTMT")){
                            rujukanResponse.setTglTmt(peserta.getString("tglTMT"));
                        }

                        JSONObject umur = peserta.getJSONObject("umur");
                        if (!umur.isNull("umurSekarang")){
                            rujukanResponse.setUmurSekarang(umur.getString("umurSekarang"));
                        }
                        if (!umur.isNull("umurSaatPelayanan")){
                            rujukanResponse.setUmurSaatPelayanan(umur.getString("umurSaatPelayanan"));
                        }

                        JSONObject poliRujukan = rujukan.getJSONObject("poliRujukan");
                        if (!poliRujukan.isNull("kode")){
                            rujukanResponse.setKodePoliRujukan(poliRujukan.getString("kode"));
                        }
                        if (!poliRujukan.isNull("nama")){
                            rujukanResponse.setNamaPoliRujukan(poliRujukan.getString("nama"));
                        }

                        JSONObject provPerujuk = rujukan.getJSONObject("provPerujuk");
                        if (!provPerujuk.isNull("kode")){
                            rujukanResponse.setKodeProvPerujuk(provPerujuk.getString("kode"));
                        }
                        if (!provPerujuk.isNull("nama")){
                            rujukanResponse.setNamaProvPerujuk(provPerujuk.getString("nama"));
                        }

                        rujukanResponse.setTglKunjungan(rujukan.getString("tglKunjungan"));

                        rujukanResponse.setStatus(responseStatus.getString("code"));
                        rujukanResponse.setMessage(responseStatus.getString("message"));
                    }
                } catch (IOException | JSONException |GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
        }

        logger.info("[BPJSBoImpl.caraRujukanBerdasarNomorBpjs] End <<<<<<<");
        return rujukanResponse;
    }
    @Override
    public RujukanResponse caraRujukanBerdasarNomorkartuBpjs(String noRujukan, String jenisCari, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjs] Start >>>>>>>");
        String feature = "";
        RujukanResponse rujukanResponse = new RujukanResponse();
        //Jenis Cari
        //R = rumah sakit
        //P = Pcare
        switch (jenisCari){
            case "R":
                feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"Rujukan/RS/Peserta/"+noRujukan;
                break;
            case "P":
                feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"Rujukan/Peserta/"+noRujukan;
                break;
        }
        if (!feature.equalsIgnoreCase("")){
            String result;

            ImBranches resultBranch = null;
            try {
                // Get data from database by ID
                resultBranch = branchDao.getConsSecrBranchById(unitId);
            } catch (HibernateException e) {
                logger.error("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjs] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
            }
            if (resultBranch != null){
                try {
                    result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                    JSONObject myResponseCheck = new JSONObject(result);
                    if (myResponseCheck.isNull("response")) {
                        JSONObject response = myResponseCheck.getJSONObject("metaData");
                        logger.error("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjs] : " + response.getString("message"));
                    } else {
                        JSONObject response = myResponseCheck.getJSONObject("response");
                        JSONObject rujukan = response.getJSONObject("rujukan");

                        JSONObject diagnosa = rujukan.getJSONObject("diagnosa");
                        if (!diagnosa.isNull("kode")){
                            rujukanResponse.setKodeDiagnosa(diagnosa.getString("kode"));
                        }
                        if (!diagnosa.isNull("nama")){
                            rujukanResponse.setNamaDiagnosa(diagnosa.getString("nama"));
                        }

                        if (!rujukan.isNull("keluhan")){
                            rujukanResponse.setKeluhan(rujukan.getString("keluhan"));
                        }
                        if (!rujukan.isNull("noKunjungan")){
                            rujukanResponse.setNoKunjungan(rujukan.getString("noKunjungan"));
                        }

                        JSONObject pelayanan = rujukan.getJSONObject("pelayanan");
                        if (!pelayanan.isNull("kode")){
                            rujukanResponse.setKodePelayanan(pelayanan.getString("kode"));
                        }
                        if (!pelayanan.isNull("nama")){
                            rujukanResponse.setNamaPelayanan(pelayanan.getString("nama"));
                        }

                        JSONObject peserta = rujukan.getJSONObject("peserta");
                        JSONObject cob = peserta.getJSONObject("cob");
                        if (!cob.isNull("nmAsuransi")){
                            rujukanResponse.setNmAsuransiCob(cob.getString("nmAsuransi"));
                        }
                        if (!cob.isNull("noAsuransi")){
                            rujukanResponse.setNoAsuransiCob(cob.getString("noAsuransi"));
                        }
                        if (!cob.isNull("tglTAT")){
                            rujukanResponse.setTglTatCob(cob.getString("tglTAT"));
                        }
                        if (!cob.isNull("tglTMT")){
                            rujukanResponse.setTglTmtCob(cob.getString("tglTMT"));
                        }

                        JSONObject hakKelas = peserta.getJSONObject("hakKelas");
                        if (!hakKelas.isNull("keterangan")){
                            rujukanResponse.setKeteranganHakKelas(hakKelas.getString("keterangan"));
                        }
                        if (!hakKelas.isNull("kode")){
                            rujukanResponse.setKodeHakKelas(hakKelas.getString("kode"));
                        }

                        JSONObject informasi = peserta.getJSONObject("informasi");
                        if (!informasi.isNull("dinsos")){
                            rujukanResponse.setDinsos(informasi.getString("dinsos"));
                        }
                        if (!informasi.isNull("noSKTM")){
                            rujukanResponse.setNoSktm(informasi.getString("noSKTM"));
                        }
                        if (!informasi.isNull("prolanisPRB")){
                            rujukanResponse.setProlanisPrb(informasi.getString("prolanisPRB"));
                        }


                        JSONObject jenisPeserta = peserta.getJSONObject("jenisPeserta");
                        if (!jenisPeserta.isNull("keterangan")){
                            rujukanResponse.setKeteranganJenisPeserta(jenisPeserta.getString("keterangan"));
                        }
                        if (!jenisPeserta.isNull("kode")){
                            rujukanResponse.setKodeJenisPeserta(jenisPeserta.getString("kode"));
                        }

                        JSONObject mr = peserta.getJSONObject("mr");
                        if (!mr.isNull("noMR")){
                            rujukanResponse.setNoMr(mr.getString("noMR"));
                        }
                        if (!mr.isNull("noTelepon")){
                            rujukanResponse.setNoTelp(mr.getString("noTelepon"));
                        }

                        if (!peserta.isNull("nama")){
                            rujukanResponse.setNama(peserta.getString("nama"));
                        }
                        if (!peserta.isNull("nik")){
                            rujukanResponse.setNik(peserta.getString("nik"));
                        }
                        if (!peserta.isNull("noKartu")){
                            rujukanResponse.setNoKartu(peserta.getString("noKartu"));
                        }
                        if (!peserta.isNull("pisa")){
                            rujukanResponse.setPisa(peserta.getString("pisa"));
                        }

                        JSONObject provUmum = peserta.getJSONObject("provUmum");
                        if (!provUmum.isNull("kdProvider")){
                            rujukanResponse.setKdProviderProvUmum(provUmum.getString("kdProvider"));
                        }
                        if (!provUmum.isNull("nmProvider")){
                            rujukanResponse.setNmProviderProvUmum(provUmum.getString("nmProvider"));
                        }

                        if (!peserta.isNull("sex")){
                            rujukanResponse.setSex(peserta.getString("sex"));
                        }

                        JSONObject statusPeserta = peserta.getJSONObject("statusPeserta");
                        if (!statusPeserta.isNull("keterangan")){
                            rujukanResponse.setKeteranganStatusPeserta(statusPeserta.getString("keterangan"));
                        }
                        if (!statusPeserta.isNull("kode")){
                            rujukanResponse.setKodeStatusPeserta(statusPeserta.getString("kode"));
                        }

                        if (!peserta.isNull("tglCetakKartu")){
                            rujukanResponse.setTglCetakKartu(peserta.getString("tglCetakKartu"));
                        }
                        if (!peserta.isNull("tglLahir")){
                            rujukanResponse.setTglLahir(peserta.getString("tglLahir"));
                        }
                        if (!peserta.isNull("tglTAT")){
                            rujukanResponse.setTglTat(peserta.getString("tglTAT"));
                        }
                        if (!peserta.isNull("tglTMT")){
                            rujukanResponse.setTglTmt(peserta.getString("tglTMT"));
                        }

                        JSONObject umur = peserta.getJSONObject("umur");
                        if (!umur.isNull("umurSekarang")){
                            rujukanResponse.setUmurSekarang(umur.getString("umurSekarang"));
                        }
                        if (!umur.isNull("umurSaatPelayanan")){
                            rujukanResponse.setUmurSaatPelayanan(umur.getString("umurSaatPelayanan"));
                        }

                        JSONObject poliRujukan = rujukan.getJSONObject("poliRujukan");
                        if (!poliRujukan.isNull("kode")){
                            rujukanResponse.setKodePoliRujukan(poliRujukan.getString("kode"));
                        }
                        if (!poliRujukan.isNull("nama")){
                            rujukanResponse.setNamaPoliRujukan(poliRujukan.getString("nama"));
                        }

                        JSONObject provPerujuk = rujukan.getJSONObject("provPerujuk");
                        if (!provPerujuk.isNull("kode")){
                            rujukanResponse.setKodeProvPerujuk(provPerujuk.getString("kode"));
                        }
                        if (!provPerujuk.isNull("nama")){
                            rujukanResponse.setNamaProvPerujuk(provPerujuk.getString("nama"));
                        }

                        rujukanResponse.setTglKunjungan(rujukan.getString("tglKunjungan"));
                    }
                } catch (IOException | JSONException |GeneralSecurityException e) {
                    e.printStackTrace();
                    throw new GeneralBOException("Cons ID dan Secret Key Tidak ada...!");
                }
            }
        }

        logger.info("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjs] End <<<<<<<");
        return rujukanResponse;
    }

    @Override
    public List<RujukanResponse> caraRujukanBerdasarNomorkartuBpjsList(String noRujukan, String jenisCari, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjsList] Start >>>>>>>");
        List<RujukanResponse> rujukanResponseList = new ArrayList<>();
        String feature = "";
        //Jenis Cari
        //R = rumah sakit
        //P = Pcare
        switch (jenisCari){
            case "R":
                feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"/Rujukan/RS/List/Peserta/"+noRujukan;
                break;
            case "P":
                feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"/Rujukan/List/Peserta/"+noRujukan;
                break;
        }
        if (!feature.equalsIgnoreCase("")){
            String result;

            ImBranches resultBranch = null;
            try {
                // Get data from database by ID
                resultBranch = branchDao.getConsSecrBranchById(unitId);
            } catch (HibernateException e) {
                logger.error("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjsList] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
            }
            if (resultBranch != null){
                try {
                    result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                    JSONObject myResponseCheck = new JSONObject(result);
                    if (myResponseCheck.isNull("response")) {
                        JSONObject response = myResponseCheck.getJSONObject("metaData");
                        logger.error("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjsList] : " + response.getString("message"));
                    } else {
                        JSONObject response = myResponseCheck.getJSONObject("response");
                        JSONArray rujukan = response.getJSONArray("rujukan");
                        int length = rujukan.length();
                        for (int i=0;i<length;i++) {
                            JSONObject obj = rujukan.getJSONObject(i);
                            RujukanResponse rujukanResponse = new RujukanResponse();
                            JSONObject diagnosa = obj.getJSONObject("diagnosa");
                            if (!diagnosa.isNull("kode")){
                                rujukanResponse.setKodeDiagnosa(diagnosa.getString("kode"));
                            }
                            if (!diagnosa.isNull("nama")){
                                rujukanResponse.setNamaDiagnosa(diagnosa.getString("nama"));
                            }

                            if (!obj.isNull("keluhan")){
                                rujukanResponse.setKeluhan(obj.getString("keluhan"));
                            }
                            if (!obj.isNull("noKunjungan")){
                                rujukanResponse.setNoKunjungan(obj.getString("noKunjungan"));
                            }

                            JSONObject pelayanan = obj.getJSONObject("pelayanan");
                            if (!pelayanan.isNull("kode")){
                                rujukanResponse.setKodePelayanan(pelayanan.getString("kode"));
                            }
                            if (!pelayanan.isNull("nama")){
                                rujukanResponse.setNamaPelayanan(pelayanan.getString("nama"));
                            }

                            JSONObject peserta = obj.getJSONObject("peserta");
                            JSONObject cob = peserta.getJSONObject("cob");
                            if (!cob.isNull("nmAsuransi")){
                                rujukanResponse.setNmAsuransiCob(cob.getString("nmAsuransi"));
                            }
                            if (!cob.isNull("noAsuransi")){
                                rujukanResponse.setNoAsuransiCob(cob.getString("noAsuransi"));
                            }
                            if (!cob.isNull("tglTAT")){
                                rujukanResponse.setTglTatCob(cob.getString("tglTAT"));
                            }
                            if (!cob.isNull("tglTMT")){
                                rujukanResponse.setTglTmtCob(cob.getString("tglTMT"));
                            }

                            JSONObject hakKelas = peserta.getJSONObject("hakKelas");
                            if (!hakKelas.isNull("keterangan")){
                                rujukanResponse.setKeteranganHakKelas(hakKelas.getString("keterangan"));
                            }
                            if (!hakKelas.isNull("kode")){
                                rujukanResponse.setKodeHakKelas(hakKelas.getString("kode"));
                            }

                            JSONObject informasi = peserta.getJSONObject("informasi");
                            if (!informasi.isNull("dinsos")){
                                rujukanResponse.setDinsos(informasi.getString("dinsos"));
                            }
                            if (!informasi.isNull("noSKTM")){
                                rujukanResponse.setNoSktm(informasi.getString("noSKTM"));
                            }
                            if (!informasi.isNull("prolanisPRB")){
                                rujukanResponse.setProlanisPrb(informasi.getString("prolanisPRB"));
                            }


                            JSONObject jenisPeserta = peserta.getJSONObject("jenisPeserta");
                            if (!jenisPeserta.isNull("keterangan")){
                                rujukanResponse.setKeteranganJenisPeserta(jenisPeserta.getString("keterangan"));
                            }
                            if (!jenisPeserta.isNull("kode")){
                                rujukanResponse.setKodeJenisPeserta(jenisPeserta.getString("kode"));
                            }

                            JSONObject mr = peserta.getJSONObject("mr");
                            if (!mr.isNull("noMR")){
                                rujukanResponse.setNoMr(mr.getString("noMR"));
                            }
                            if (!mr.isNull("noTelepon")){
                                rujukanResponse.setNoTelp(mr.getString("noTelepon"));
                            }

                            if (!peserta.isNull("nama")){
                                rujukanResponse.setNama(peserta.getString("nama"));
                            }
                            if (!peserta.isNull("nik")){
                                rujukanResponse.setNik(peserta.getString("nik"));
                            }
                            if (!peserta.isNull("noKartu")){
                                rujukanResponse.setNoKartu(peserta.getString("noKartu"));
                            }
                            if (!peserta.isNull("pisa")){
                                rujukanResponse.setPisa(peserta.getString("pisa"));
                            }

                            JSONObject provUmum = peserta.getJSONObject("provUmum");
                            if (!provUmum.isNull("kdProvider")){
                                rujukanResponse.setKdProviderProvUmum(provUmum.getString("kdProvider"));
                            }
                            if (!provUmum.isNull("nmProvider")){
                                rujukanResponse.setNmProviderProvUmum(provUmum.getString("nmProvider"));
                            }

                            if (!peserta.isNull("sex")){
                                rujukanResponse.setSex(peserta.getString("sex"));
                            }

                            JSONObject statusPeserta = peserta.getJSONObject("statusPeserta");
                            if (!statusPeserta.isNull("keterangan")){
                                rujukanResponse.setKeteranganStatusPeserta(statusPeserta.getString("keterangan"));
                            }
                            if (!statusPeserta.isNull("kode")){
                                rujukanResponse.setKodeStatusPeserta(statusPeserta.getString("kode"));
                            }

                            if (!peserta.isNull("tglCetakKartu")){
                                rujukanResponse.setTglCetakKartu(peserta.getString("tglCetakKartu"));
                            }
                            if (!peserta.isNull("tglLahir")){
                                rujukanResponse.setTglLahir(peserta.getString("tglLahir"));
                            }
                            if (!peserta.isNull("tglTAT")){
                                rujukanResponse.setTglTat(peserta.getString("tglTAT"));
                            }
                            if (!peserta.isNull("tglTMT")){
                                rujukanResponse.setTglTmt(peserta.getString("tglTMT"));
                            }

                            JSONObject umur = peserta.getJSONObject("umur");
                            if (!umur.isNull("umurSekarang")){
                                rujukanResponse.setUmurSekarang(umur.getString("umurSekarang"));
                            }
                            if (!umur.isNull("umurSaatPelayanan")){
                                rujukanResponse.setUmurSaatPelayanan(umur.getString("umurSaatPelayanan"));
                            }

                            JSONObject poliRujukan = obj.getJSONObject("poliRujukan");
                            if (!poliRujukan.isNull("kode")){
                                rujukanResponse.setKodePoliRujukan(poliRujukan.getString("kode"));
                            }
                            if (!poliRujukan.isNull("nama")){
                                rujukanResponse.setNamaPoliRujukan(poliRujukan.getString("nama"));
                            }

                            JSONObject provPerujuk = obj.getJSONObject("provPerujuk");
                            if (!provPerujuk.isNull("kode")){
                                rujukanResponse.setKodeProvPerujuk(provPerujuk.getString("kode"));
                            }
                            if (!provPerujuk.isNull("nama")){
                                rujukanResponse.setNamaProvPerujuk(provPerujuk.getString("nama"));
                            }

                            if (!obj.isNull("tglKunjungan")){
                                rujukanResponse.setTglKunjungan(obj.getString("tglKunjungan"));
                            }

                            rujukanResponseList.add(rujukanResponse);
                        }

                    }
                } catch (IOException | JSONException |GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
        }

        logger.info("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjsList] End <<<<<<<");
        return rujukanResponseList;
    }

    @Override
    public RujukanResponse insertRujukanBpjs(RujukanRequest rujukanRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.insertRujukanBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Rujukan/insert";
        JSONObject request = null;
        String jsonData="{\n" +
                "       \"request\": {\n" +
                "          \"t_rujukan\": {\n" +
                "             \"noSep\": \""+rujukanRequest.getNoSep()+"\",\n" +
                "             \"tglRujukan\": \""+rujukanRequest.getTglRujukan()+"\",\n" +
                "             \"ppkDirujuk\": \""+rujukanRequest.getPpkDirujuk()+"\",\n" +
                "             \"jnsPelayanan\": \""+rujukanRequest.getJnsPelayanan()+"\",\n" +
                "             \"catatan\": \""+rujukanRequest.getCatatan()+"\",\n" +
                "             \"diagRujukan\": \""+rujukanRequest.getDiagRujukan()+"\",\n" +
                "             \"tipeRujukan\": \""+rujukanRequest.getTipeRujukan()+"\",\n" +
                "             \"poliRujukan\": \""+rujukanRequest.getPoliRujukan()+"\",\n" +
                "             \"user\": \""+rujukanRequest.getUserPembuatRujukan()+"\"\n" +
                "          }\n" +
                "       }\n" +
                "    }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        RujukanResponse rujukanResponse = new RujukanResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.insertRujukanBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.insertRujukanBpjs] : " + response.getString("message"));
                    rujukanResponse.setStatus(response.getString("code"));
                    rujukanResponse.setMessage(response.getString("message"));
                } else {
                    JSONObject status = myResponseCheck.getJSONObject("metaData");
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONObject rujukan = response.getJSONObject("rujukan");
                    JSONObject AsalRujukan = rujukan.getJSONObject("AsalRujukan");
                    if (!AsalRujukan.isNull("kode")){
                        rujukanResponse.setKodeAsalRujukan(AsalRujukan.getString("kode"));
                    }
                    if (!AsalRujukan.isNull("nama")){
                        rujukanResponse.setNamaAsalRujukan(AsalRujukan.getString("nama"));
                    }

                    JSONObject diagnosa = rujukan.getJSONObject("diagnosa");
                    if (!diagnosa.isNull("kode")){
                        rujukanResponse.setKodeDiagnosa(diagnosa.getString("kode"));
                    }
                    if (!diagnosa.isNull("nama")){
                        rujukanResponse.setNamaDiagnosa(diagnosa.getString("nama"));
                    }

                    JSONObject peserta = rujukan.getJSONObject("peserta");
                    if (!peserta.isNull("asuransi")){
                        rujukanResponse.setAsuransi(peserta.getString("asuransi"));
                    }
                    if (!peserta.isNull("hakKelas")){
                        rujukanResponse.setKodeHakKelas(peserta.getString("hakKelas"));
                    }
                    if (!peserta.isNull("jnsPeserta")){
                        rujukanResponse.setKodeJenisPeserta(peserta.getString("jnsPeserta"));
                    }
                    if (!peserta.isNull("kelamin")){
                        rujukanResponse.setSex(peserta.getString("kelamin"));
                    }
                    if (!peserta.isNull("nama")){
                        rujukanResponse.setNama(peserta.getString("nama"));
                    }
                    if (!peserta.isNull("noKartu")){
                        rujukanResponse.setNoKartu(peserta.getString("noKartu"));
                    }
                    if (!peserta.isNull("noMr")){
                        rujukanResponse.setNoMr(peserta.getString("noMr"));
                    }
                    if (!peserta.isNull("tglLahir")){
                        rujukanResponse.setTglLahir(peserta.getString("tglLahir"));
                    }

                    JSONObject poliTujuan = rujukan.getJSONObject("poliTujuan");
                    if (!poliTujuan.isNull("kode")){
                        rujukanResponse.setKodePoliRujukan(poliTujuan.getString("kode"));
                    }
                    if (!poliTujuan.isNull("nama")){
                        rujukanResponse.setNamaPoliRujukan(poliTujuan.getString("nama"));
                    }

                    if (!rujukan.isNull("tglRujukan")){
                        rujukanResponse.setTglRujukan(rujukan.getString("tglRujukan"));
                    }
                    if (!rujukan.isNull("noRujukan")){
                        rujukanResponse.setNoRujukan(rujukan.getString("noRujukan"));
                    }

                    JSONObject tujuanRujukan = rujukan.getJSONObject("tujuanRujukan");
                    if (!tujuanRujukan.isNull("kode")){
                        rujukanResponse.setKodeTujuanRujukan(tujuanRujukan.getString("kode"));
                    }
                    if (!tujuanRujukan.isNull("nama")){
                        rujukanResponse.setNamaTujuanRujukan(tujuanRujukan.getString("nama"));
                    }
                    if(status.has("code")){
                        rujukanResponse.setStatus(status.getString("code"));
                    }
                    if(status.has("message")){
                        rujukanResponse.setMessage(status.getString("message"));
                    }

                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.insertRujukanBpjs] End <<<<<<<");
        return rujukanResponse;
    }

    @Override
    public RujukanResponse updateRujukanBpjs(RujukanRequest rujukanRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.updateRujukanBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Rujukan/update";
        JSONObject request = null;
        String jsonData="{\n" +
                "       \"request\": {\n" +
                "          \"t_rujukan\": {\n" +
                "             \"noRujukan\": \""+rujukanRequest.getNoRujukan()+"\",\n" +
                "             \"ppkDirujuk\": \""+rujukanRequest.getPpkDirujuk()+"\",\n" +
                "             \"tipe\": \""+rujukanRequest.getTipeRujukan()+"\",\n" +
                "             \"jnsPelayanan\": \""+rujukanRequest.getJnsPelayanan()+"\",\n" +
                "             \"catatan\": \""+rujukanRequest.getCatatan()+"\",\n" +
                "             \"diagRujukan\": \""+rujukanRequest.getDiagRujukan()+"\",\n" +
                "             \"tipeRujukan\": \""+rujukanRequest.getTipeRujukan()+"\",\n" +
                "             \"poliRujukan\": \""+rujukanRequest.getPoliRujukan()+"\",\n" +
                "             \"user\": \""+rujukanRequest.getUserPembuatRujukan()+"\"\n" +
                "          }\n" +
                "       }\n" +
                "    }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        RujukanResponse rujukanResponse = new RujukanResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.updateRujukanBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.updateRujukanBpjs] : " + response.getString("message"));
                } else {
                    rujukanResponse.setNoRujukan(myResponseCheck.getString("response"));
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.updateRujukanBpjs] End <<<<<<<");
        return rujukanResponse;
    }
    @Override
    public RujukanResponse deleteRujukanBpjs(RujukanRequest rujukanRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.deleteRujukanBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Rujukan/delete";
        JSONObject request = null;
        String jsonData="{\n" +
                "        \"request\": {\n" +
                "            \"t_rujukan\": {\n" +
                "                \"noRujukan\": \""+rujukanRequest.getNoRujukan()+"\",\n" +
                "                \"user\": \""+rujukanRequest.getUserPembuatRujukan()+"\"\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        RujukanResponse rujukanResponse = new RujukanResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.deleteRujukanBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.deleteRujukanBpjs] : " + response.getString("message"));
                } else {
                    rujukanResponse.setNoRujukan(myResponseCheck.getString("response"));
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.deleteRujukanBpjs] End <<<<<<<");
        return rujukanResponse;
    }

    //----------------------------------------!! END OF RUJUKAN !!-------------------------------------------//

    //----------------------------------------!! LPK !!-------------------------------------------//
    @Override
    public LPKResponse insertLPKBpjs(LPKRequest lpkRequest, List<DiagnosaResponse> diagnosaResponseList, List<TindakanResponse> tindakanResponseList, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.insertLPKBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/LPK/insert";
        JSONObject request = null;
        List<TindakanLPK> tindakanLPKList = new ArrayList<>();
        List<DiagnosaLPK> diagnosaLPKList = new ArrayList<>();

        for (DiagnosaResponse diagnosa : diagnosaResponseList){
            DiagnosaLPK data = new DiagnosaLPK();
            data.setKode(diagnosa.getKodeDiagnosaBpjs());
            data.setLevel(diagnosa.getLevelDiagnosa());
            diagnosaLPKList.add(data);
        }
        for (TindakanResponse tindakan : tindakanResponseList){
            TindakanLPK data = new TindakanLPK();
            data.setKode(tindakan.getKodeTindakanBpjs());
        }
        String jsonDiagnosa = new Gson().toJson(diagnosaLPKList);
        String jsonTindakan = new Gson().toJson(tindakanLPKList);

        String jsonData="{\n" +
                "       \"request\": {\n" +
                "          \"t_lpk\": {\n" +
                "             \"noSep\": \""+lpkRequest.getNoSep()+"\",\n" +
                "             \"tglMasuk\": \""+lpkRequest.getTglMasuk()+"\",\n" +
                "             \"tglKeluar\": \""+lpkRequest.getTglKeluar()+"\",\n" +
                "             \"jaminan\": \""+lpkRequest.getJaminan()+"\",\n" +
                "             \"poli\": {\n" +
                "                \"poli\": \""+lpkRequest.getKdPoli()+"\"\n" +
                "             },\n" +
                "             \"perawatan\": {\n" +
                "                \"ruangRawat\": \""+lpkRequest.getRuangRawat()+"\",\n" +
                "                \"kelasRawat\": \""+lpkRequest.getKelasRawat()+"\",\n" +
                "                \"spesialistik\": \""+lpkRequest.getSpesialistik()+"\",\n" +
                "                \"caraKeluar\": \""+lpkRequest.getCaraKeluar()+"\",\n" +
                "                \"kondisiPulang\": \""+lpkRequest.getKondisiPulang()+"\"\n" +
                "             },\n" +
                "             \"diagnosa\": "+jsonDiagnosa+",\n" +
                "             \"procedure\": "+jsonTindakan+",\n" +
                "             \"rencanaTL\": {\n" +
                "                \"tindakLanjut\": \""+lpkRequest.getTindakLanjut()+"\",\n" +
                "                \"dirujukKe\": {\n" +
                "                   \"kodePPK\": \""+lpkRequest.getKodePPKDirujuk()+"\"\n" +
                "                },\n" +
                "                \"kontrolKembali\": {\n" +
                "                   \"tglKontrol\": \""+lpkRequest.getTglKontrolKembali()+"\",\n" +
                "                   \"poli\": \""+lpkRequest.getPoliKontrolKembali()+"\"\n" +
                "                }\n" +
                "             },\n" +
                "             \"DPJP\": \""+lpkRequest.getKodeDpjp()+"\",\n" +
                "             \"user\": \""+lpkRequest.getUserPembuat()+"\"\n" +
                "          }\n" +
                "       }\n" +
                "    }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        LPKResponse lpkResponse = new LPKResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.insertLPKBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.insertLPKBpjs] : " + response.getString("message"));
                } else {
                    lpkResponse.setNoSep(myResponseCheck.getString("response"));
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.insertLPKBpjs] End <<<<<<<");
        return lpkResponse;
    }
    @Override
    public LPKResponse updateLPKBpjs(LPKRequest lpkRequest, List<DiagnosaResponse> diagnosaResponseList, List<TindakanResponse> tindakanResponseList, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.updateLPKBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/LPK/update";
        JSONObject request = null;
        List<TindakanLPK> tindakanLPKList = new ArrayList<>();
        List<DiagnosaLPK> diagnosaLPKList = new ArrayList<>();

        for (DiagnosaResponse diagnosa : diagnosaResponseList){
            DiagnosaLPK data = new DiagnosaLPK();
            data.setKode(diagnosa.getKodeDiagnosaBpjs());
            data.setLevel(diagnosa.getLevelDiagnosa());
            diagnosaLPKList.add(data);
        }
        for (TindakanResponse tindakan : tindakanResponseList){
            TindakanLPK data = new TindakanLPK();
            data.setKode(tindakan.getKodeTindakanBpjs());
        }
        String jsonDiagnosa = new Gson().toJson(diagnosaLPKList);
        String jsonTindakan = new Gson().toJson(tindakanLPKList);

        String jsonData="{\n" +
                "       \"request\": {\n" +
                "          \"t_lpk\": {\n" +
                "             \"noSep\": \""+lpkRequest.getNoSep()+"\",\n" +
                "             \"tglMasuk\": \""+lpkRequest.getTglMasuk()+"\",\n" +
                "             \"tglKeluar\": \""+lpkRequest.getTglKeluar()+"\",\n" +
                "             \"jaminan\": \""+lpkRequest.getJaminan()+"\",\n" +
                "             \"poli\": {\n" +
                "                \"poli\": \""+lpkRequest.getKdPoli()+"\"\n" +
                "             },\n" +
                "             \"perawatan\": {\n" +
                "                \"ruangRawat\": \""+lpkRequest.getRuangRawat()+"\",\n" +
                "                \"kelasRawat\": \""+lpkRequest.getKelasRawat()+"\",\n" +
                "                \"spesialistik\": \""+lpkRequest.getSpesialistik()+"\",\n" +
                "                \"caraKeluar\": \""+lpkRequest.getCaraKeluar()+"\",\n" +
                "                \"kondisiPulang\": \""+lpkRequest.getKondisiPulang()+"\"\n" +
                "             },\n" +
                "             \"diagnosa\": "+jsonDiagnosa+",\n" +
                "             \"procedure\": "+jsonTindakan+",\n" +
                "             \"rencanaTL\": {\n" +
                "                \"tindakLanjut\": \""+lpkRequest.getTindakLanjut()+"\",\n" +
                "                \"dirujukKe\": {\n" +
                "                   \"kodePPK\": \""+lpkRequest.getKodePPKDirujuk()+"\"\n" +
                "                },\n" +
                "                \"kontrolKembali\": {\n" +
                "                   \"tglKontrol\": \""+lpkRequest.getTglKontrolKembali()+"\",\n" +
                "                   \"poli\": \""+lpkRequest.getPoliKontrolKembali()+"\"\n" +
                "                }\n" +
                "             },\n" +
                "             \"DPJP\": \""+lpkRequest.getKodeDpjp()+"\",\n" +
                "             \"user\": \""+lpkRequest.getUserPembuat()+"\"\n" +
                "          }\n" +
                "       }\n" +
                "    }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        LPKResponse lpkResponse = new LPKResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.updateLPKBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.updateLPKBpjs] : " + response.getString("message"));
                } else {
                    lpkResponse.setNoSep(myResponseCheck.getString("response"));
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.updateLPKBpjs] End <<<<<<<");
        return lpkResponse;
    }
    @Override
    public LPKResponse deleteLPKBpjs(LPKRequest lpkRequest, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.deleteLPKBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/LPK/delete";
        JSONObject request = null;

        String jsonData="{\n" +
                "       \"request\": {\n" +
                "          \"t_lpk\": {\n" +
                "             \"noSep\": \""+lpkRequest.getNoSep()+"\"\n" +
                "          }\n" +
                "       }\n" +
                "    }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        LPKResponse lpkResponse = new LPKResponse();
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.deleteLPKBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.deleteLPKBpjs] : " + response.getString("message"));
                } else {
                    lpkResponse.setNoSep(myResponseCheck.getString("response"));
                }

            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[BPJSBoImpl.deleteLPKBpjs] End <<<<<<<");
        return lpkResponse;
    }

    @Override
    public List<LPKResponse> dataLPKBpjs(String tglMasuk, String jnsPelayanan, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.dataLPKBpjs] Start >>>>>>>");
        List<LPKResponse> lpkResponseList= new ArrayList<>();
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"/LPK/TglMasuk/"+tglMasuk+"/JnsPelayanan/"+jnsPelayanan;
        String result;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjsList] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjsList] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONObject lpk = response.getJSONObject("lpk");
                    JSONArray list = lpk.getJSONArray("list");
                    int length = list.length();
                    for (int i=0;i<length;i++) {
                        JSONObject obj = list.getJSONObject(i);
                        LPKResponse lpkResponse = new LPKResponse();
                        JSONObject DPJP = obj.getJSONObject("DPJP");
                        JSONObject dokter = DPJP.getJSONObject("dokter");
                        lpkResponse.setKodeDokterDpjp(dokter.getString("kode"));
                        lpkResponse.setNamaDokterDpjp(dokter.getString("nama"));

                        List<DiagnosaResponse> diagnosaResponses = new ArrayList<>();
                        JSONObject diagnosa = obj.getJSONObject("diagnosa");
                        JSONArray listDiagnosa = diagnosa.getJSONArray("list");
                        int lengthDiagnosa = listDiagnosa.length();
                        for (int j=0;j<lengthDiagnosa;j++) {
                            JSONObject objDiagnosa = list.getJSONObject(j);
                            DiagnosaResponse diagnosaResponse = new DiagnosaResponse();
                            diagnosaResponse.setLevelDiagnosa(objDiagnosa.getString("level"));
                            JSONObject diagnosaObj = objDiagnosa.getJSONObject("list");
                            diagnosaResponse.setKodeDiagnosaBpjs(diagnosaObj.getString("kode"));
                            diagnosaResponse.setNamaDiagnosaBpjs(diagnosaObj.getString("nama"));
                            diagnosaResponses.add(diagnosaResponse);
                        }
                        lpkResponse.setDiagnosaResponseList(diagnosaResponses);

                        lpkResponse.setJnsPelayanan(obj.getString("jnsPelayanan"));
                        lpkResponse.setNoSep(obj.getString("noSep"));

                        JSONObject perawatan = obj.getJSONObject("perawatan");
                        JSONObject caraKeluar = perawatan.getJSONObject("perawatan");
                        lpkResponse.setKodeCaraKeluar(caraKeluar.getString("kode"));
                        lpkResponse.setNamaCaraKeluar(caraKeluar.getString("nama"));

                        JSONObject kelasRawat = perawatan.getJSONObject("kelasRawat");
                        lpkResponse.setKodeKelasRawat(kelasRawat.getString("kode"));
                        lpkResponse.setNamaKelasRawat(kelasRawat.getString("nama"));

                        JSONObject ruangRawat = perawatan.getJSONObject("ruangRawat");
                        lpkResponse.setKodeRuangRawat(ruangRawat.getString("kode"));
                        lpkResponse.setNamaRuangRawat(ruangRawat.getString("nama"));

                        JSONObject spesialistik = perawatan.getJSONObject("spesialistik");
                        lpkResponse.setKodeSpesialistik(spesialistik.getString("kode"));
                        lpkResponse.setNamaSpesialistik(spesialistik.getString("nama"));

                        JSONObject peserta = obj.getJSONObject("peserta");
                        lpkResponse.setKelamin(peserta.getString("kelamin"));
                        lpkResponse.setNama(peserta.getString("nama"));
                        lpkResponse.setNoKartu(peserta.getString("noKartu"));
                        lpkResponse.setNoMr(peserta.getString("noMR"));
                        lpkResponse.setTglLahir(peserta.getString("tglLahir"));

                        JSONObject poli = obj.getJSONObject("poli");
                        lpkResponse.setPoliEksekutif(poli.getString("eksekutif"));
                        JSONObject poliPoli = poli.getJSONObject("poli");
                        lpkResponse.setKodePoli(poliPoli.getString("kode"));

                        List<TindakanResponse> tindakanResponses = new ArrayList<>();
                        JSONObject tindakan = obj.getJSONObject("procedure");
                        JSONArray listTindakan = tindakan.getJSONArray("list");
                        int lengthTindakan = listTindakan.length();
                        for (int k=0;k<lengthTindakan;k++) {
                            JSONObject objTindakan = list.getJSONObject(k);
                            TindakanResponse tindakanResponse = new TindakanResponse();
                            JSONObject diagnosaObj = objTindakan.getJSONObject("list");
                            tindakanResponse.setKodeTindakanBpjs(diagnosaObj.getString("kode"));
                            tindakanResponse.setNamaTindakanBpjs(diagnosaObj.getString("nama"));
                            tindakanResponses.add(tindakanResponse);
                        }
                        lpkResponse.setTindakanResponseList(tindakanResponses);

                        if (!obj.isNull("rencanaTL")){
                            lpkResponse.setRencanaTl(obj.getString("rencanaTL"));
                        }

                        lpkResponse.setTglKeluar(obj.getString("tglKeluar"));
                        lpkResponse.setTglMasuk(obj.getString("tglMasuk"));

                        lpkResponseList.add(lpkResponse);
                    }
                }
            } catch (IOException | JSONException |GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[BPJSBoImpl.caraRujukanBerdasarNomorkartuBpjsList] End <<<<<<<");
        return lpkResponseList;
    }

    //----------------------------------------!! END OF LPK !!-------------------------------------------//

    //----------------------------------------!! MONITORING !!-------------------------------------------//
    @Override
    public List<MonitoringDataKunjunganResponse> monitoringDataKunjunganBpjs(String tglMasuk, String jnsPelayanan, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.monitoringDataKunjunganBpjs] Start >>>>>>>");
        List<MonitoringDataKunjunganResponse> monitoringDataKunjunganResponseList= new ArrayList<>();
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"/Monitoring/Kunjungan/Tanggal/"+tglMasuk+"/JnsPelayanan/"+jnsPelayanan;
        String result;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.monitoringDataKunjunganBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.monitoringDataKunjunganBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray array = response.getJSONArray("sep");
                    int lengthMonitoring = array.length();
                    for (int i=0;i<lengthMonitoring;i++) {
                        JSONObject obj= array.getJSONObject(i);
                        MonitoringDataKunjunganResponse data = new MonitoringDataKunjunganResponse();
                        data.setDiagnosa(obj.getString("diagnosa"));
                        data.setJnsPelayanan(obj.getString("jnsPelayanan"));
                        data.setKelasRawat(obj.getString("kelasRawat"));
                        data.setNama(obj.getString("nama"));
                        data.setNoKartu(obj.getString("noKartu"));
                        data.setNoSep(obj.getString("noSep"));
                        data.setNoRujukan(obj.getString("noRujukan"));
                        data.setPoli(obj.getString("poli"));
                        data.setTglPlgSep(obj.getString("tglPlgSep"));
                        data.setTglSep(obj.getString("tglSep"));

                        monitoringDataKunjunganResponseList.add(data);
                    }
                }
            } catch (IOException | JSONException |GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[BPJSBoImpl.monitoringDataKunjunganBpjs] End <<<<<<<");
        return monitoringDataKunjunganResponseList;
    }

    @Override
    public List<DataKlaimResponse> monitoringDataKlaimBpjs(String tglMasuk, String jnsPelayanan, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.monitoringDataKlaimBpjs] Start >>>>>>>");
        List<DataKlaimResponse> dataKlaimResponseList= new ArrayList<>();
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"/Monitoring/Klaim/Tanggal/"+tglMasuk+"/JnsPelayanan/"+jnsPelayanan;
        String result;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.monitoringDataKlaimBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.monitoringDataKlaimBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray array = response.getJSONArray("klaim");
                    int lengthMonitoring = array.length();
                    for (int i=0;i<lengthMonitoring;i++) {
                        JSONObject obj= array.getJSONObject(i);
                        DataKlaimResponse data = new DataKlaimResponse();

                        JSONObject Inacbg = obj.getJSONObject("Inacbg");
                        data.setKodeInaCbg(Inacbg.getString("kode"));
                        data.setNamaInaCbg(Inacbg.getString("nama"));

                        JSONObject biaya = obj.getJSONObject("biaya");
                        data.setByPengajuan(biaya.getString("byPengajuan"));
                        data.setBySetujui(biaya.getString("bySetujui"));
                        data.setByTarifGrouper(biaya.getString("byTarifGruper"));
                        data.setByTarifRs(biaya.getString("byTarifRS"));
                        data.setByTopUp(biaya.getString("byTopup"));

                        data.setKelasRawat(obj.getString("kelasRawat"));
                        data.setNoFPK(obj.getString("noFPK"));
                        data.setNoSep(obj.getString("noSEP"));

                        JSONObject peserta = obj.getJSONObject("peserta");
                        data.setNamaPeserta(peserta.getString("nama"));
                        data.setNoKartu(peserta.getString("noKartu"));
                        data.setNoMr(peserta.getString("noMR"));

                        data.setPoli(obj.getString("poli"));
                        data.setStatus(obj.getString("status"));
                        data.setTglPulang(obj.getString("tglPulang"));
                        data.setTglSep(obj.getString("tglSep"));

                        dataKlaimResponseList.add(data);
                    }
                }
            } catch (IOException | JSONException |GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[BPJSBoImpl.monitoringDataKlaimBpjs] End <<<<<<<");
        return dataKlaimResponseList;
    }

    @Override
    public List<HistoryPelayananPesertaResponse> monitoringHistoryPelayananBpjs(String noKartu, String tglMulaiPencarian, String tglAkhirPencarian, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.monitoringHistoryPelayananBpjs] Start >>>>>>>");
        List<HistoryPelayananPesertaResponse> historyPelayananPesertaResponseList= new ArrayList<>();
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"/monitoring/HistoriPelayanan/NoKartu/"+noKartu+"/tglMulai/"+tglMulaiPencarian+"/tglAkhir/"+tglAkhirPencarian;
        String result;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.monitoringHistoryPelayananBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.monitoringHistoryPelayananBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray array = response.getJSONArray("histori");
                    int lengthMonitoring = array.length();
                    for (int i=0;i<lengthMonitoring;i++) {
                        JSONObject obj= array.getJSONObject(i);
                        HistoryPelayananPesertaResponse data = new HistoryPelayananPesertaResponse();
                        data.setDiagnosa(obj.getString("diagnosa"));
                        data.setJnsPelayanan(obj.getString("jnsPelayanan"));
                        data.setKelasRawat(obj.getString("kelasRawat"));
                        data.setNamaPeserta(obj.getString("namaPeserta"));
                        data.setNoKartu(obj.getString("noKartu"));
                        data.setNoSep(obj.getString("noSep"));
                        data.setNoRujukan(obj.getString("noRujukan"));
                        data.setPoli(obj.getString("poli"));
                        data.setPpkPelayanan(obj.getString("ppkPelayanan"));
                        data.setTglPlgSep(obj.getString("tglPlgSep"));
                        data.setTglSep(obj.getString("tglSep"));

                        historyPelayananPesertaResponseList.add(data);
                    }
                }
            } catch (IOException | JSONException |GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[BPJSBoImpl.monitoringHistoryPelayananBpjs] End <<<<<<<");
        return historyPelayananPesertaResponseList;
    }

    @Override
    public List<KlaimJasaRaharjaResponse> monitoringKlaimJasaRaharjaBpjs(String tglMulai,String tglAkhir, String unitId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.monitoringKlaimJasaRaharjaBpjs] Start >>>>>>>");
        List<KlaimJasaRaharjaResponse> klaimJasaRaharjaResponseList= new ArrayList<>();
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM +"/monitoring/JasaRaharja/tglMulai/"+tglMulai+"/tglAkhir/"+tglAkhir;
        String result;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[BPJSBoImpl.monitoringKlaimJasaRaharjaBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[BPJSBoImpl.monitoringKlaimJasaRaharjaBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray array = response.getJSONArray("histori");
                    int lengthMonitoring = array.length();
                    for (int i=0;i<lengthMonitoring;i++) {
                        JSONObject obj= array.getJSONObject(i);
                        KlaimJasaRaharjaResponse data = new KlaimJasaRaharjaResponse();
                        JSONObject sep = obj.getJSONObject("sep");
                        data.setNoSep(sep.getString("noSEP"));
                        data.setTglSep(sep.getString("tglSEP"));
                        data.setTglPlgSep(sep.getString("tglPlgSEP"));
                        data.setNoMr(sep.getString("noMr"));
                        data.setJnsPelayanan(sep.getString("jnsPelayanan"));
                        data.setPoli(sep.getString("poli"));
                        data.setDiagnosa(sep.getString("diagnosa"));
                        JSONObject peserta = obj.getJSONObject("peserta");
                        data.setNoKartu(peserta.getString("noKartu"));
                        data.setNama(peserta.getString("nama"));
                        data.setNoMrPeserta(peserta.getString("noMR"));

                        JSONObject jasaRaharja = obj.getJSONObject("jasaRaharja");
                        data.setTglKejadian(jasaRaharja.getString("tglKejadian"));
                        data.setNoRegister(jasaRaharja.getString("noRegister"));
                        data.setKetStatusDijamin(jasaRaharja.getString("ketStatusDijamin"));
                        data.setKetStatusDikirim(jasaRaharja.getString("ketStatusDikirim"));
                        data.setBiayaDijamin(jasaRaharja.getString("biayaDijamin"));
                        data.setPlafon(jasaRaharja.getString("plafon"));
                        data.setJmlDibayar(jasaRaharja.getString("jmlDibayar"));
                        data.setResultJasaRaharja(jasaRaharja.getString("resultsJasaRaharja"));

                        klaimJasaRaharjaResponseList.add(data);
                    }
                }
            } catch (IOException | JSONException |GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[BPJSBoImpl.monitoringKlaimJasaRaharjaBpjs] End <<<<<<<");
        return klaimJasaRaharjaResponseList;
    }
    //----------------------------------------!! END OF MONITORING !!-------------------------------------------//

    @Override
    public String coba(){
        return "Sukses";
    }
}