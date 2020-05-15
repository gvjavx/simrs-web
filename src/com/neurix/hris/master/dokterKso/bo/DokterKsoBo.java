package com.neurix.hris.master.dokterKso.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.dokterKso.model.DokterKso;

import java.util.List;

public interface DokterKsoBo extends BaseMasterBo<DokterKso> {
    public List<DokterKso> getByCriteria(DokterKso bean) throws GeneralBOException;
}