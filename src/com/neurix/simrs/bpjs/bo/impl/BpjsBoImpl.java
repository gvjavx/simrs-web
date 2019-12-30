package com.neurix.simrs.bpjs.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.bpjs.bo.BpjsBo;
import com.neurix.simrs.bpjs.model.*;
import org.apache.log4j.Logger;
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

    public static void setLogger(Logger logger) {
        BpjsBoImpl.logger = logger;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public List<TindakanResponse> GetTindakanByAPIBpjs(String tindakanId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.getListEntityJenisTindakan] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/procedure/"+tindakanId;
        String result = null;
        List<TindakanResponse> tindakanResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.getListEntityJenisTindakan] End <<<<<<<");
        return tindakanResponseList;
    }

    @Override
    public List<ProvinsiResponse> GetProvinsiByAPIBpjs() throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetProvinsiByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/propinsi";
        String result = null;
        List<ProvinsiResponse> provinsiBpjsList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetProvinsiByAPIBpjs] End <<<<<<<");
        return provinsiBpjsList;
    }

    @Override
    public List<KabupatenResponse> GetKabupatenByAPIBpjs(String provinsiId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetKabupatenByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/kabupaten/propinsi/"+provinsiId;
        String result = null;
        List<KabupatenResponse> kabupatenBpjsList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetKabupatenByAPIBpjs] End <<<<<<<");
        return kabupatenBpjsList;
    }
    @Override
    public List<KecamatanResponse> GetKecamatanByAPIBpjs(String kabupatenId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetKecamatanByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/kecamatan/kabupaten/"+kabupatenId;
        String result = null;
        List<KecamatanResponse> kecamatanBpjsList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetKecamatanByAPIBpjs] End <<<<<<<");
        return kecamatanBpjsList;
    }
    @Override
    public List<KelasRawatResponse> GetKelasRawatByAPIBpjs() throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetKelasRawatByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/kelasrawat";
        String result = null;
        List<KelasRawatResponse> kelasRawatBpjsList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetKelasRawatByAPIBpjs] End <<<<<<<");
        return kelasRawatBpjsList;
    }
    @Override
    public List<DiagnosaResponse> GetDiagnosaByAPIBpjs(String diagnosaId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetDiagnosaByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/diagnosa/"+diagnosaId;
        String result = null;
        List<DiagnosaResponse> diagnosaResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
            JSONObject response = myResponseCheck.getJSONObject("response");
            JSONArray arrResponse = response.getJSONArray("list");

            int length = arrResponse.length();
            for (int i=0;i<length;i++){
                JSONObject obj = arrResponse.getJSONObject(i);
                DiagnosaResponse diagnosaResponse = new DiagnosaResponse();
                diagnosaResponse.setKodeDiagnosaBpjs(obj.getString("kode"));
                diagnosaResponse.setNamaDiagnosaBpjs(obj.getString("nama"));

                diagnosaResponseList.add(diagnosaResponse);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetDiagnosaByAPIBpjs] End <<<<<<<");
        return diagnosaResponseList;
    }
    @Override
    public List<PoliResponse> GetPoliByAPIBpjs(String poliId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetDiagnosaByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "referensi/poli/"+poliId;
        String result = null;
        List<PoliResponse> poliResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
            JSONObject response = myResponseCheck.getJSONObject("response");
            JSONArray arrResponse = response.getJSONArray("list");

            int length = arrResponse.length();
            for (int i=0;i<length;i++){
                JSONObject obj = arrResponse.getJSONObject(i);
                PoliResponse poliResponse = new PoliResponse();
                poliResponse.setKodePoliBpjs(obj.getString("kode"));
                poliResponse.setNamaPoliBpjs(obj.getString("nama"));

                poliResponseList.add(poliResponse);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetDiagnosaByAPIBpjs] End <<<<<<<");
        return poliResponseList;
    }
    @Override
    public List<FaskesResponse> GetFaskesByAPIBpjs(String faskesId, String jenisFaskes) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetFaskesByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/faskes/"+faskesId+"/"+jenisFaskes;
        String result = null;
        List<FaskesResponse> faskesResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetFaskesByAPIBpjs] End <<<<<<<");
        return faskesResponseList;
    }
    @Override
    public List<DpjpResponse> GetDpjpByAPIBpjs(String jenisPelayanan, String tglPelayanan, String dpjpId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetDpjpByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/dokter/pelayanan/"+jenisPelayanan+"/tglPelayanan/"+tglPelayanan+"/Spesialis/"+dpjpId;
        String result = null;
        List<DpjpResponse> dpjpResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetDpjpByAPIBpjs] End <<<<<<<");
        return dpjpResponseList;
    }
    @Override
    public List<DokterResponse> GetDokterByAPIBpjs(String dpjpId) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetDokterByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/dokter/"+dpjpId;
        String result = null;
        List<DokterResponse> dokterResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetDokterByAPIBpjs] End <<<<<<<");
        return dokterResponseList;
    }
    @Override
    public List<SpesialistikResponse> GetSpesialistikByAPIBpjs() throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetSpesialistikByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/spesialistik";
        String result = null;
        List<SpesialistikResponse> spesialistikResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetSpesialistikByAPIBpjs] End <<<<<<<");
        return spesialistikResponseList;
    }
    @Override
    public List<RuangRawatResponse> GetRuangRawatByAPIBpjs() throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetRuangRawatByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/ruangrawat";
        String result = null;
        List<RuangRawatResponse> ruangRawatResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetRuangRawatByAPIBpjs] End <<<<<<<");
        return ruangRawatResponseList;
    }
    @Override
    public List<CaraKeluarResponse> GetCaraKeluarByAPIBpjs() throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetCaraKeluarByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/carakeluar";
        String result = null;
        List<CaraKeluarResponse> caraKeluarResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetCaraKeluarByAPIBpjs] End <<<<<<<");
        return caraKeluarResponseList;
    }
    @Override
    public List<PascaPulangResponse> GetPascaPulangByAPIBpjs() throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetPascaPulangByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/pascapulang";
        String result = null;
        List<PascaPulangResponse> pascaPulangResponseList = new ArrayList<>();
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.GetPascaPulangByAPIBpjs] End <<<<<<<");
        return pascaPulangResponseList;
    }
    @Override
    public PesertaResponse GetPesertaBpjsByAPIBpjs(String noBpjs, String tanggalSep) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetPesertaBpjsByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Peserta/nokartu/"+noBpjs+"/tglSEP/"+tanggalSep;
        PesertaResponse finalResult = new PesertaResponse();
        try {
            String result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
            if (myResponseCheck.isNull("response")) {
                JSONObject response = myResponseCheck.getJSONObject("metaData");
                logger.error("[BPJSBoImpl.completeBpjs] : " + response.getString("message"));
            } else {
                JSONObject response = myResponseCheck.getJSONObject("response");

                JSONObject peserta = response.getJSONObject("peserta");

                JSONObject cob = peserta.getJSONObject("cob");
                finalResult.setNmAsuransi(cob.getString("nmAsuransi"));
                finalResult.setNoAsuransi(cob.getString("noAsuransi"));
                finalResult.setTglTAT(cob.getString("tglTAT"));
                finalResult.setTglTMT(cob.getString("tglTMT"));

                JSONObject hakKelas = peserta.getJSONObject("hakKelas");
                finalResult.setKeteranganKelas(hakKelas.getString("keterangan"));
                finalResult.setKodeKelas(hakKelas.getString("kode"));

                JSONObject informasi = peserta.getJSONObject("informasi");
                finalResult.setDinsos(informasi.getString("dinsos"));
                finalResult.setNoSKTM(informasi.getString("noSKTM"));
                finalResult.setProlanisPRB(informasi.getString("prolanisPRB"));

                JSONObject jenisPeserta = peserta.getJSONObject("jenisPeserta");
                finalResult.setKeteranganJenisPeserta(jenisPeserta.getString("keterangan"));
                finalResult.setKodeJenisPeserta(jenisPeserta.getString("kode"));

                JSONObject mr = peserta.getJSONObject("mr");
                finalResult.setNoMr(mr.getString("noMR"));
                finalResult.setNoTelp(mr.getString("noTelepon"));

                finalResult.setNama(peserta.getString("nama"));
                finalResult.setNik(peserta.getString("nik"));
                finalResult.setNoKartu(peserta.getString("noKartu"));
                finalResult.setPisa(peserta.getString("pisa"));

                JSONObject provUmum = peserta.getJSONObject("provUmum");
                finalResult.setKodeProvider(provUmum.getString("kdProvider"));
                finalResult.setNamaProvider(provUmum.getString("nmProvider"));

                finalResult.setSex(peserta.getString("sex"));

                JSONObject statusPeserta = peserta.getJSONObject("statusPeserta");
                finalResult.setKeteranganStatusPeserta(statusPeserta.getString("keterangan"));
                finalResult.setKodeStatusPeserta(statusPeserta.getString("kode"));

                finalResult.setStTglCetakKartu(peserta.getString("tglCetakKartu"));
                finalResult.setStTglLahir(peserta.getString("tglLahir"));
                finalResult.setStTglTAT(peserta.getString("tglTAT"));
                finalResult.setStTglTMT(peserta.getString("tglTMT"));

                JSONObject umur = peserta.getJSONObject("umur");
                finalResult.setUmurSaatPelayanan(umur.getString("umurSaatPelayanan"));
                finalResult.setUmurSekarang(umur.getString("umurSekarang"));

            }
        } catch (Exception e) {
            logger.error("[BPJSBoImpl.completeBpjs] Error when get data");
        }
        logger.info("[BPJSBoImpl.GetPesertaBpjsByAPIBpjs] End <<<<<<<");
        return finalResult;
    }
    @Override
    public PesertaResponse GetPesertaNikByAPIBpjs(String nik, String tanggalSep) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetPesertaNikByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Peserta/nik/"+nik+"/tglSEP/"+tanggalSep;
        PesertaResponse finalResult = new PesertaResponse();
        try {
            String result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
            if (myResponseCheck.isNull("response")) {
                JSONObject response = myResponseCheck.getJSONObject("metaData");
                logger.error("[BPJSBoImpl.GetPesertaNikByAPIBpjs] : " + response.getString("message"));
            } else {
                JSONObject response = myResponseCheck.getJSONObject("response");

                JSONObject peserta = response.getJSONObject("peserta");

                JSONObject cob = peserta.getJSONObject("cob");
                finalResult.setNmAsuransi(cob.getString("nmAsuransi"));
                finalResult.setNoAsuransi(cob.getString("noAsuransi"));
                finalResult.setTglTAT(cob.getString("tglTAT"));
                finalResult.setTglTMT(cob.getString("tglTMT"));

                JSONObject hakKelas = peserta.getJSONObject("hakKelas");
                finalResult.setKeteranganKelas(hakKelas.getString("keterangan"));
                finalResult.setKodeKelas(hakKelas.getString("kode"));

                JSONObject informasi = peserta.getJSONObject("informasi");
                finalResult.setDinsos(informasi.getString("dinsos"));
                finalResult.setNoSKTM(informasi.getString("noSKTM"));
                finalResult.setProlanisPRB(informasi.getString("prolanisPRB"));

                JSONObject jenisPeserta = peserta.getJSONObject("jenisPeserta");
                finalResult.setKeteranganJenisPeserta(jenisPeserta.getString("keterangan"));
                finalResult.setKodeJenisPeserta(jenisPeserta.getString("kode"));

                JSONObject mr = peserta.getJSONObject("mr");
                finalResult.setNoMr(mr.getString("noMR"));
                finalResult.setNoTelp(mr.getString("noTelepon"));

                finalResult.setNama(peserta.getString("nama"));
                finalResult.setNik(peserta.getString("nik"));
                finalResult.setNoKartu(peserta.getString("noKartu"));
                finalResult.setPisa(peserta.getString("pisa"));

                JSONObject provUmum = peserta.getJSONObject("provUmum");
                finalResult.setKodeProvider(provUmum.getString("kdProvider"));
                finalResult.setNamaProvider(provUmum.getString("nmProvider"));

                finalResult.setSex(peserta.getString("sex"));

                JSONObject statusPeserta = peserta.getJSONObject("statusPeserta");
                finalResult.setKeteranganStatusPeserta(statusPeserta.getString("keterangan"));
                finalResult.setKodeStatusPeserta(statusPeserta.getString("kode"));

                finalResult.setStTglCetakKartu(peserta.getString("tglCetakKartu"));
                finalResult.setStTglLahir(peserta.getString("tglLahir"));
                finalResult.setStTglTAT(peserta.getString("tglTAT"));
                finalResult.setStTglTMT(peserta.getString("tglTMT"));

                JSONObject umur = peserta.getJSONObject("umur");
                finalResult.setUmurSaatPelayanan(umur.getString("umurSaatPelayanan"));
                finalResult.setUmurSekarang(umur.getString("umurSekarang"));

            }
        } catch (Exception e) {
            logger.error("[BPJSBoImpl.GetPesertaNikByAPIBpjs] Error when get data");
        }
        logger.info("[BPJSBoImpl.GetPesertaNikByAPIBpjs] End <<<<<<<");
        return finalResult;
    }
}