package com.neurix.simrs.master.kategoritindakan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;

import java.util.List;

public interface KategoriTindakanBo extends BaseMasterBo<KategoriTindakan>{
    public List<KategoriTindakan> getByCriteria(KategoriTindakan bean) throws GeneralBOException;
    public List<KategoriTindakan> getListKategoriTindakan(String idPelayanan, String kategori, String branchId) throws GeneralBOException;
    public List<KategoriTindakan> getDataByCriteria(KategoriTindakan bean) throws GeneralBOException;

    public KategoriTindakan saveAdd(KategoriTindakan bean) throws GeneralBOException;
////    public void saveEdit(KategoriTindakan bean) throws GeneralBOException;
////    public void saveDelete(KategoriTindakan bean) throws GeneralBOException;
}
