package com.neurix.hris.master.mappingpersengaji.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;

import java.util.List;

/**
 * Created by Toshiba on 14/11/2019.
 */
public interface MappingPersenGajiBo extends BaseMasterBo<Dokter> {
    public List<Dokter> getByCriteria(Dokter bean) throws GeneralBOException;
}
