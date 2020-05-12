package com.neurix.simrs.transaksi.profilrekammedisrj.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.RekamMedisRawatJalan;

import java.util.List;

public interface RekamMedisRawatJalanBo {

    public List<RekamMedisRawatJalan> getByCriteria(RekamMedisRawatJalan bean) throws GeneralBOException;
    public CrudResponse saveAdd(RekamMedisRawatJalan bean) throws GeneralBOException;
}
