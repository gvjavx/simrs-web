package com.neurix.simrs.bpjs.eklaim.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.eklaim.bo.model.*;
import com.neurix.simrs.bpjs.vclaim.model.DiagnosaResponse;
import com.neurix.simrs.bpjs.vclaim.model.TindakanResponse;

import java.util.List;

public interface EklaimBo{
    KlaimResponse insertNewClaimEklaim(KlaimRequest klaimRequest, String unitId) throws GeneralBOException;

    KlaimResponse updateDataPasienEklaim(KlaimRequest klaimRequest, String unitId) throws GeneralBOException;

    KlaimResponse deleteDataPasienEklaim(KlaimRequest klaimRequest, String unitId) throws GeneralBOException;

    KlaimDetailResponse updateDataClaimEklaim(KlaimDetailRequest klaimDetailRequest, String unitId) throws GeneralBOException;

    Grouping1Response groupingStage1Eklaim(String noSep, String unitId) throws GeneralBOException;

    Grouping2Response groupingStage2Eklaim(String noSep, String specialCmg, String unitId) throws GeneralBOException;

    void finalisasiClaimEklaim(String noSep, String coderNik, String unitId) throws GeneralBOException;

    void reeditClaimEklaim(String noSep, String unitId) throws GeneralBOException;

    List<KlaimDataCenterResponse> kirimKeDataCenterPerTanggalEklaim(String startDate, String stopDate, String jenisRawat, String dateType, String unitId) throws GeneralBOException;

    List<KlaimDataCenterResponse> kirimKeDataCenterPerSepEklaim(String noSep, String unitId) throws GeneralBOException;

    DataPerKlaimResponse detailPerKlaimEklaim(String noSep, String unitId) throws GeneralBOException;

    ClaimStatusResponse cekStatusKlaimVclaimPerSepEklaim(String noSep, String unitId) throws GeneralBOException;

    void deleteKlaimPerSepEklaim(String noSep, String coderNik, String unitId) throws GeneralBOException;

    String printKlaimPerSepEklaim(String noSep, String unitId) throws GeneralBOException;

    List<TindakanResponse> getProsedureEklaim(String keyword, String unitId) throws GeneralBOException;

    List<DiagnosaResponse> getDiagnosaEklaim(String keyword, String unitId) throws GeneralBOException;
}