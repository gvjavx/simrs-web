package com.neurix.simrs.bpjs.aplicare.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.bpjs.aplicare.bo.AplicareBo;
import com.neurix.simrs.bpjs.aplicare.model.KetersediaanKamarRequest;
import com.neurix.simrs.bpjs.aplicare.model.KetersediaanKamarResponse;
import com.neurix.simrs.bpjs.aplicare.model.RefKamarResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class AplicareBoImpl extends BpjsService implements AplicareBo {

    protected static transient Logger logger = Logger.getLogger(AplicareBoImpl.class);

    public AplicareBoImpl() throws GeneralSecurityException, IOException {
    }
    private BranchDao branchDao;

    public static void setLogger(Logger logger) {
        AplicareBoImpl.logger = logger;
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

    //----------------------------------------!! KETERSEDIAAN !!-------------------------------------------//

    @Override
    public List<RefKamarResponse> ketersediaanKamarAplicare (String unitId) throws GeneralBOException {
        logger.info("[AplicareBoImpl.ketersediaanKamaAplicare] Start >>>>>>>");
        List<RefKamarResponse> refKamarResponseList= new ArrayList<>();
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_APLICARE +"ref/kelas";
        String result;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[AplicareBoImpl.ketersediaanKamaAplicare] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[AplicareBoImpl.ketersediaanKamaAplicare] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray array = response.getJSONArray("list");
                    int length = array.length();
                    for (int i=0;i<length;i++) {
                        JSONObject obj= array.getJSONObject(i);
                        RefKamarResponse data = new RefKamarResponse();
                        data.setKodeKelas(obj.getString("kodekelas"));
                        data.setNamaKelas(obj.getString("namakelas"));

                        refKamarResponseList.add(data);
                    }
                }
            } catch (IOException | JSONException |GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[AplicareBoImpl.ketersediaanKamaAplicare] End <<<<<<<");
        return refKamarResponseList;
    }

    @Override
    public void updateKamarAplicare(KetersediaanKamarRequest requestKamar, String kodePPK, String unitId) throws GeneralBOException {
        logger.info("[AplicareBoImpl.ketersediaanKamaAplicare] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "bed/update/"+kodePPK;
        JSONObject request = null;
        String jsonData="{ \n" +
                "    \"kodekelas\":\""+requestKamar.getKodeKelas()+"\", \n" +
                "    \"koderuang\":\""+requestKamar.getKodeRuang()+"\", \n" +
                "    \"namaruang\":\""+requestKamar.getNamaRuang()+"\", \n" +
                "    \"kapasitas\":\""+requestKamar.getKapasitas()+"\", \n" +
                "    \"tersedia\":\""+requestKamar.getTersedia()+"\",\n" +
                "    \"tersediapria\":\""+requestKamar.getTersediaPria()+"\", \n" +
                "    \"tersediawanita\":\""+requestKamar.getTersediaWanita()+"\", \n" +
                "    \"tersediapriawanita\":\""+requestKamar.getTersediaPriaWanita()+"\"\n" +
                "   }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[AplicareBoImpl.ketersediaanKamaAplicare] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[AplicareBoImpl.ketersediaanKamaAplicare] End <<<<<<<");
    }

    @Override
    public void insertKamarAplicare(KetersediaanKamarRequest requestKamar, String kodePPK, String unitId) throws GeneralBOException {
        logger.info("[AplicareBoImpl.insertKamarAplicare] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "bed/create/"+kodePPK;
        JSONObject request = null;
        String jsonData="{ \n" +
                "    \"kodekelas\":\""+requestKamar.getKodeKelas()+"\", \n" +
                "    \"koderuang\":\""+requestKamar.getKodeRuang()+"\", \n" +
                "    \"namaruang\":\""+requestKamar.getNamaRuang()+"\", \n" +
                "    \"kapasitas\":\""+requestKamar.getKapasitas()+"\", \n" +
                "    \"tersedia\":\""+requestKamar.getTersedia()+"\",\n" +
                "    \"tersediapria\":\""+requestKamar.getTersediaPria()+"\", \n" +
                "    \"tersediawanita\":\""+requestKamar.getTersediaWanita()+"\", \n" +
                "    \"tersediapriawanita\":\""+requestKamar.getTersediaPriaWanita()+"\"\n" +
                "   }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[AplicareBoImpl.insertKamarAplicare] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[AplicareBoImpl.insertKamarAplicare] End <<<<<<<");
    }
    @Override
    public void deleteKamarAplicare(KetersediaanKamarRequest requestKamar, String kodePPK, String unitId) throws GeneralBOException {
        logger.info("[AplicareBoImpl.deleteKamarAplicare] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "bed/delete/"+kodePPK;
        JSONObject request = null;
        String jsonData="{ \"kodekelas\":\""+requestKamar.getKodeKelas()+"\", \n" +
                "    \"koderuang\":\""+requestKamar.getKodeRuang()+"\"\n" +
                "  }";
        try {
            request = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = null;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[AplicareBoImpl.deleteKamarAplicare] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null){
            try {
                result = GETRequest(feature,request,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
            } catch (IOException | JSONException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        logger.info("[AplicareBoImpl.deleteKamarAplicare] End <<<<<<<");
    }
    @Override
    public List<KetersediaanKamarResponse> ketersediaanKamarRsAplicare (String kodePPK,String start,String limit,String unitId) throws GeneralBOException {
        logger.info("[AplicareBoImpl.ketersediaanKamarRsAplicare] Start >>>>>>>");
        List<KetersediaanKamarResponse> ketersediaanKamarResponseList= new ArrayList<>();
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_APLICARE +"bed/read/"+kodePPK+"/"+start+"/"+limit;
        String result;
        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[AplicareBoImpl.ketersediaanKamarRsAplicare] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }
        if (resultBranch != null){
            try {
                result = GET(feature,resultBranch.getConstId(),resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[AplicareBoImpl.ketersediaanKamarRsAplicare] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONArray array = response.getJSONArray("list");
                    int length = array.length();
                    for (int i=0;i<length;i++) {
                        JSONObject obj= array.getJSONObject(i);
                        KetersediaanKamarResponse data = new KetersediaanKamarResponse();
                        ketersediaanKamarResponseList.add(data);
                    }
                }
            } catch (IOException | JSONException |GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        logger.info("[AplicareBoImpl.ketersediaanKamarRsAplicare] End <<<<<<<");
        return ketersediaanKamarResponseList;
    }
    //----------------------------------------!! END OF KETERSEDIAAN !!-------------------------------------------//

}