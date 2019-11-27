package com.neurix.simrs.master.obat.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.model.Obat;

import java.util.List;

public interface ObatBo{
    public List<Obat> getByCriteria(Obat searchBean) throws GeneralBOException;
}