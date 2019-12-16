package com.neurix.simrs.master.jenisobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.model.JenisObat;

import java.util.List;

public interface JenisObatBo{
    public List<JenisObat> getByCriteria(JenisObat searchBean) throws GeneralBOException;
}