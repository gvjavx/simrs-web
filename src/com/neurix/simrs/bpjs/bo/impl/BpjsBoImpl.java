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


    //----------------------------------------!! SEP !!-------------------------------------------//
    @Override
    public SepResponse insertSepBpjs(SepRequest sepRequest) throws GeneralBOException {
        logger.info("[BPJSBoImpl.insertSepBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/SEP/1.1/insert";
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
        try {
            result = GETRequest(feature,request);
            JSONObject myResponseCheck = new JSONObject(result);
            JSONObject response = myResponseCheck.getJSONObject("response");
            JSONObject sep = response.getJSONObject("sep");

            sepResponse.setCatatan(sep.getString("catatan"));
            sepResponse.setDiagnosa(sep.getString("diagnosa"));
            sepResponse.setJnsPelayanan(sep.getString("jnsPelayanan"));
            sepResponse.setKelasRawat(sep.getString("kelasRawat"));
            sepResponse.setNoSep(sep.getString("noSep"));
            sepResponse.setPenjamin(sep.getString("penjamin"));

            JSONObject peserta = response.getJSONObject("peserta");
            sepResponse.setAsuransi(peserta.getString("asuransi"));
            sepResponse.setHakKelas(peserta.getString("hakKelas"));
            sepResponse.setJnsPeserta(peserta.getString("jnsPeserta"));
            sepResponse.setKelamin(peserta.getString("kelamin"));
            sepResponse.setNama(peserta.getString("nama"));
            sepResponse.setNoKartu(peserta.getString("noKartu"));
            sepResponse.setNoMr(peserta.getString("noMr"));
            sepResponse.setTglLahir(peserta.getString("tglLahir"));

            JSONObject informasi = response.getJSONObject("informasi");
            sepResponse.setDinsos(informasi.getString("Dinsos"));
            sepResponse.setProlanisPrb(informasi.getString("prolanisPRB"));
            sepResponse.setNoSktm(informasi.getString("noSKTM"));

            sepResponse.setPoli(informasi.getString("poli"));
            sepResponse.setPoliEksekutif(informasi.getString("poliEksekutif"));
            sepResponse.setTglSep(informasi.getString("tglSep"));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.insertSepBpjs] End <<<<<<<");
        return sepResponse;
    }
    @Override
    public SepResponse updateSepBpjs(SepRequest sepRequest) throws GeneralBOException {
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
        try {
            result = GETRequest(feature,request);
            JSONObject myResponseCheck = new JSONObject(result);
            sepResponse.setNoSep(myResponseCheck.getString("response"));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.updateSepBpjs] End <<<<<<<");
        return sepResponse;
    }
    @Override
    public SepResponse deleteSepBpjs(SepRequest sepRequest) throws GeneralBOException {
        logger.info("[BPJSBoImpl.deleteSepBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/SEP/Delete";
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
        try {
            result = GETRequest(feature,request);
            JSONObject myResponseCheck = new JSONObject(result);
            sepResponse.setNoSep(myResponseCheck.getString("response"));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.deleteSepBpjs] End <<<<<<<");
        return sepResponse;
    }

    @Override
    public SepResponse GetSepBpjsByAPIBpjs(String noSep) throws GeneralBOException {
        logger.info("[BPJSBoImpl.GetSepBpjsByAPIBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/SEP/"+noSep;
        SepResponse finalResult = new SepResponse();
        try {
            String result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
            if (myResponseCheck.isNull("response")) {
                JSONObject response = myResponseCheck.getJSONObject("metaData");
                logger.error("[BPJSBoImpl.GetSepBpjsByAPIBpjs] : " + response.getString("message"));
            } else {
                JSONObject response = myResponseCheck.getJSONObject("response");
                finalResult.setCatatan(response.getString("catatan"));
                finalResult.setDiagnosa(response.getString("diagnosa"));
                finalResult.setJnsPelayanan(response.getString("jnsPelayanan"));
                finalResult.setKelasRawat(response.getString("kelasRawat"));
                finalResult.setNoSep(response.getString("noSep"));
                finalResult.setNoRujukan(response.getString("noRujukan"));
                finalResult.setPenjamin(response.getString("penjamin"));

                JSONObject peserta = response.getJSONObject("peserta");
                finalResult.setAsuransi(peserta.getString("asuransi"));
                finalResult.setHakKelas(peserta.getString("hakKelas"));
                finalResult.setJnsPeserta(peserta.getString("jnsPeserta"));
                finalResult.setKelamin(peserta.getString("kelamin"));
                finalResult.setNama(peserta.getString("nama"));
                finalResult.setNoKartu(peserta.getString("noKartu"));
                finalResult.setNoMr(peserta.getString("noMr"));
                finalResult.setTglLahir(peserta.getString("tglLahir"));

                finalResult.setPenjamin(response.getString("poli"));
                finalResult.setPenjamin(response.getString("poliEksekutif"));
                finalResult.setPenjamin(response.getString("tglSep"));
            }
        } catch (Exception e) {
            logger.error("[BPJSBoImpl.GetSepBpjsByAPIBpjs] Error when get data");
        }
        logger.info("[BPJSBoImpl.GetSepBpjsByAPIBpjs] End <<<<<<<");
        return finalResult;
    }
    @Override
    public SepResponse updateTglPulangSepBpjs(SepRequest sepRequest) throws GeneralBOException {
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
        try {
            result = GETRequest(feature,request);
            JSONObject myResponseCheck = new JSONObject(result);
            sepResponse.setNoSep(myResponseCheck.getString("response"));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[BPJSBoImpl.updateTglPulangSepBpjs] End <<<<<<<");
        return sepResponse;
    }
    @Override
    public SepResponse searchSepInacbgBpjs(String noSep) throws GeneralBOException {
        logger.info("[BPJSBoImpl.searchSepInacbgBpjs] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/sep/cbg/"+noSep;
        SepResponse finalResult = new SepResponse();
        try {
            String result = GET(feature);
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
        logger.info("[BPJSBoImpl.searchSepInacbgBpjs] End <<<<<<<");
        return finalResult;
    }
    //----------------------------------------!! END SEP !!-------------------------------------------//
}