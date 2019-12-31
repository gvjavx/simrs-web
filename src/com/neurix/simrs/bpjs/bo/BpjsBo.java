package com.neurix.simrs.bpjs.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.model.*;
import com.neurix.simrs.bpjs.tindakan.model.*;

import java.util.List;

public interface BpjsBo{

    List<TindakanResponse> GetTindakanByAPIBpjs(String tindakanId) throws GeneralBOException;

    List<ProvinsiResponse> GetProvinsiByAPIBpjs() throws GeneralBOException;

    List<KabupatenResponse> GetKabupatenByAPIBpjs(String provinsiId) throws GeneralBOException;

    List<KecamatanResponse> GetKecamatanByAPIBpjs(String kabupatenId) throws GeneralBOException;

    List<KelasRawatResponse> GetKelasRawatByAPIBpjs() throws GeneralBOException;

    List<DiagnosaResponse> GetDiagnosaByAPIBpjs(String diagnosaId) throws GeneralBOException;

    List<PoliResponse> GetPoliByAPIBpjs(String poliId) throws GeneralBOException;

    List<FaskesResponse> GetFaskesByAPIBpjs(String faskesId, String jenisFaskes) throws GeneralBOException;

    List<DpjpResponse> GetDpjpByAPIBpjs(String jenisPelayanan, String tglPelayanan, String dpjpId) throws GeneralBOException;

    List<DokterResponse> GetDokterByAPIBpjs(String dpjpId) throws GeneralBOException;

    List<SpesialistikResponse> GetSpesialistikByAPIBpjs() throws GeneralBOException;

    List<RuangRawatResponse> GetRuangRawatByAPIBpjs() throws GeneralBOException;

    List<CaraKeluarResponse> GetCaraKeluarByAPIBpjs() throws GeneralBOException;

    List<PascaPulangResponse> GetPascaPulangByAPIBpjs() throws GeneralBOException;

    PesertaResponse GetPesertaBpjsByAPIBpjs(String noBpjs, String tanggalSep) throws GeneralBOException;

    PesertaResponse GetPesertaNikByAPIBpjs(String nik, String tanggalSep) throws GeneralBOException;

    //----------------------------------------!! SEP !!-------------------------------------------//
    SepResponse insertSepBpjs(SepRequest sepRequest) throws GeneralBOException;

    SepResponse updateSepBpjs(SepRequest sepRequest) throws GeneralBOException;

    SepResponse deleteSepBpjs(SepRequest sepRequest) throws GeneralBOException;

    SepResponse GetSepBpjsByAPIBpjs(String noSep) throws GeneralBOException;

    SepResponse updateTglPulangSepBpjs(SepRequest sepRequest) throws GeneralBOException;

    SepResponse searchSepInacbgBpjs(String noSep) throws GeneralBOException;
}