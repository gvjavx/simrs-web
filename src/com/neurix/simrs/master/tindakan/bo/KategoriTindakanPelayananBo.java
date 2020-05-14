package com.neurix.simrs.master.tindakan.bo;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakan.model.KategoriTindakanPelayanan;

import java.util.List;

public interface KategoriTindakanPelayananBo extends BaseMasterBo<KategoriTindakanPelayanan> {
    public List<KategoriTindakanPelayanan> getByCriteria(KategoriTindakanPelayanan bean) throws GeneralBOException;
}