package com.neurix.simrs.master.rekananops.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.RekananOps;

import java.util.List;

public interface DetailRekananOpsBo {
    public List<DetailRekananOps> getSearchByCriteria (RekananOps bean) throws GeneralBOException;

}
