package com.neurix.simrs.master.tindakanmedis.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakanmedis.model.TindakanMedis;

import java.util.List;

public interface TindakanMedisBo
{
    public List<TindakanMedis> getByCriteria(TindakanMedis bean) throws GeneralBOException;
}
