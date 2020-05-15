package com.neurix.simrs.master.jenisperiksapasien.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.model.Asuransi;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;

import java.util.List;

public interface AsuransiBo extends BaseMasterBo<Asuransi> {

    public List<Asuransi> getByCriteria(Asuransi bean) throws GeneralBOException;
    public ImSimrsAsuransiEntity getEntityAsuransiById(String idAsuransi) throws GeneralBOException;

}
