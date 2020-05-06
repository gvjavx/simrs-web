package com.neurix.simrs.master.kategoritindakanina.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoritindakanina.model.KategoriTindakanIna;

import java.util.List;

public interface KategoriTindakanInaBo extends BaseMasterBo<KategoriTindakanIna> {

    public List<KategoriTindakanIna> getByCriteria(KategoriTindakanIna bean) throws GeneralBOException;

}