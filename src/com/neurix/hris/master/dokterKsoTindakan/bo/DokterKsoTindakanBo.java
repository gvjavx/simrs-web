package com.neurix.hris.master.dokterKsoTindakan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.dokterKsoTindakan.model.DokterKsoTindakan;

import java.util.List;

public interface DokterKsoTindakanBo extends BaseMasterBo<DokterKsoTindakan>{
    public List<DokterKsoTindakan> getByCriteria(DokterKsoTindakan bean) throws GeneralBOException;
}