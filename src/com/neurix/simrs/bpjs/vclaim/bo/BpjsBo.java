package com.neurix.simrs.bpjs.vclaim.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.vclaim.model.*;

import java.util.List;

public interface BpjsBo{


    List<TindakanResponse> GetTindakanByAPIBpjs(String tindakanId, String unitId) throws GeneralBOException;

    List<ProvinsiResponse> GetProvinsiByAPIBpjs(String unitId) throws GeneralBOException;

    List<KabupatenResponse> GetKabupatenByAPIBpjs(String provinsiId, String unitId) throws GeneralBOException;

    List<KecamatanResponse> GetKecamatanByAPIBpjs(String kabupatenId, String unitId) throws GeneralBOException;

    List<KelasRawatResponse> GetKelasRawatByAPIBpjs(String unitId) throws GeneralBOException;

    List<DiagnosaResponse> getDiagnosaByAPIBpjs(String diagnosaId, String unitId) throws GeneralBOException;

    List<PoliResponse> GetPoliByAPIBpjs(String poliId, String unitId) throws GeneralBOException;

    List<FaskesResponse> GetFaskesByAPIBpjs(String faskesId, String jenisFaskes, String unitId) throws GeneralBOException;

    List<DpjpResponse> GetDpjpByAPIBpjs(String jenisPelayanan, String tglPelayanan, String dpjpId, String unitId) throws GeneralBOException;

    List<DokterResponse> GetDokterByAPIBpjs(String dpjpId, String unitId) throws GeneralBOException;

    List<SpesialistikResponse> GetSpesialistikByAPIBpjs(String unitId) throws GeneralBOException;

    List<RuangRawatResponse> GetRuangRawatByAPIBpjs(String unitId) throws GeneralBOException;

    List<CaraKeluarResponse> GetCaraKeluarByAPIBpjs(String unitId) throws GeneralBOException;

    List<PascaPulangResponse> GetPascaPulangByAPIBpjs(String unitId) throws GeneralBOException;

    PesertaResponse GetPesertaBpjsByAPIBpjs(String noBpjs, String tanggalSep, String unitId) throws GeneralBOException;

    PesertaResponse GetPesertaNikByAPIBpjs(String nik, String tanggalSep, String unitId) throws GeneralBOException;

    //----------------------------------------!! SEP !!-------------------------------------------//
    SepResponse insertSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException;

    SepResponse updateSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException;

    SepResponse deleteSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException;

    SepResponse GetSepBpjsByAPIBpjs(String noSep, String unitId) throws GeneralBOException;

    SepResponse updateTglPulangSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException;

    SepResponse searchSepInacbgBpjs(String noSep, String unitId) throws GeneralBOException;

    List<SepResponse> suplesiJasaRaharjaBpjs(String noKartuBpjs,String tglPelayanan, String unitId) throws GeneralBOException;

    SepResponse pengajuanPenjaminanSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException;

    SepResponse ApprovalPengajuanSepBpjs(SepRequest sepRequest, String unitId) throws GeneralBOException;

    //----------------------------------------!! END SEP !!-------------------------------------------//

    //----------------------------------------!! RUJUKAN !!-------------------------------------------//
    RujukanResponse caraRujukanBerdasarNomorBpjs(String noRujukan,String jenisCari, String unitId) throws GeneralBOException;

    RujukanResponse caraRujukanBerdasarNomorkartuBpjs(String noKartu, String jenisCari, String unitId) throws GeneralBOException;

    List<RujukanResponse> caraRujukanBerdasarNomorkartuBpjsList(String noRujukan, String jenisCari, String unitId) throws GeneralBOException;

    RujukanResponse insertRujukanBpjs(RujukanRequest rujukanRequest, String unitId) throws GeneralBOException;

    RujukanResponse updateRujukanBpjs(RujukanRequest rujukanRequest, String unitId) throws GeneralBOException;

    RujukanResponse deleteRujukanBpjs(RujukanRequest rujukanRequest, String unitId) throws GeneralBOException;
    //----------------------------------------!! END OF RUJUKAN !!-------------------------------------------//

    //----------------------------------------!! LPK !!-------------------------------------------//
    LPKResponse insertLPKBpjs(LPKRequest lpkRequest,List<DiagnosaResponse> diagnosaResponseList,List<TindakanResponse> tindakanResponseList, String unitId) throws GeneralBOException;

    //----------------------------------------!! END OF LPK !!-------------------------------------------//

    LPKResponse updateLPKBpjs(LPKRequest lpkRequest, List<DiagnosaResponse> diagnosaResponseList, List<TindakanResponse> tindakanResponseList, String unitId) throws GeneralBOException;

    LPKResponse deleteLPKBpjs(LPKRequest lpkRequest, String unitId) throws GeneralBOException;

    List<LPKResponse> dataLPKBpjs(String tglMasuk, String jnsPelayanan, String unitId) throws GeneralBOException;

    //----------------------------------------!! MONITORING !!-------------------------------------------//
    List<MonitoringDataKunjunganResponse> monitoringDataKunjunganBpjs(String tglMasuk, String jnsPelayanan, String unitId) throws GeneralBOException;

    List<DataKlaimResponse> monitoringDataKlaimBpjs(String tglMasuk, String jnsPelayanan, String unitId) throws GeneralBOException;

    List<HistoryPelayananPesertaResponse> monitoringHistoryPelayananBpjs(String noKartu, String tglMulaiPencarian, String tglAkhirPencarian, String unitId) throws GeneralBOException;

    List<KlaimJasaRaharjaResponse> monitoringKlaimJasaRaharjaBpjs(String tglMulai, String tglAkhir, String unitId) throws GeneralBOException;

    String coba();
}