package com.neurix.simrs.transaksi.rekammedik.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;

import java.util.List;

public interface RekamMedikBo {
    public List<HeaderDetailCheckup> getListPasien(HeaderDetailCheckup bean) throws GeneralBOException;
    public List<HeaderDetailCheckup> getDetailListRekamMedis(String idPasien) throws GeneralBOException;
    public CrudResponse saveAdd(StatusPengisianRekamMedis bean) throws GeneralBOException;
}
