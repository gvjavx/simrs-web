package com.neurix.hris.master.dokterKso.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.dokterKso.model.DokterKso;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.tindakan.model.Tindakan;

import java.util.List;

public interface DokterKsoBo extends BaseMasterBo<DokterKso> {
    public List<DokterKso> getByCriteria(DokterKso bean) throws GeneralBOException;
    public List<Pelayanan> getPelayananDokter(String idDokter) throws GeneralBOException;
    public List<Tindakan> getTindakanPelayanan(String idPelayanan, String idDokter) throws GeneralBOException;
}