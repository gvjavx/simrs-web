package com.neurix.simrs.master.kategoritindakan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;

import java.util.List;

public interface KategoriTindakanBo {

    public List<KategoriTindakan> getByCriteria(KategoriTindakan bean) throws GeneralBOException;

    public List<KategoriTindakan> getListKategoriTindakan(String idPelayanan) throws GeneralBOException;
}
