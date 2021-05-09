package com.neurix.simrs.bpjs.aplicare.bo;


import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.aplicare.model.KetersediaanKamarRequest;
import com.neurix.simrs.bpjs.aplicare.model.KetersediaanKamarResponse;
import com.neurix.simrs.bpjs.aplicare.model.RefKamarResponse;

import java.util.List;

public interface AplicareBo{


    List<RefKamarResponse> ketersediaanKamarAplicare(String unitId) throws GeneralBOException;

    void updateKamarAplicare (KetersediaanKamarRequest requestKamar, String kodePPK, String unitId) throws GeneralBOException;

    void insertKamarAplicare(KetersediaanKamarRequest requestKamar, String kodePPK, String unitId) throws GeneralBOException;

    void deleteKamarAplicare(KetersediaanKamarRequest requestKamar, String kodePPK, String unitId) throws GeneralBOException;

    List<KetersediaanKamarResponse> ketersediaanKamarRsAplicare(String kodePPK, String start, String limit, String unitId) throws GeneralBOException;
}