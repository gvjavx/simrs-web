package com.neurix.simrs.transaksi.asesmenrawatjalan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatjalan.model.KeperawatanRawatJalan;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;

import java.util.List;

public interface KeperawatanRawatJalanBo {
    public List<KeperawatanRawatJalan> getByCriteria(KeperawatanRawatJalan bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<KeperawatanRawatJalan> list) throws GeneralBOException;
    public CrudResponse saveDelete(KeperawatanRawatJalan bean) throws GeneralBOException;
    public HeaderCheckup getDataResumeMedis(KeperawatanRawatJalan bean) throws GeneralBOException;
}
