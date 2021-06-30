package com.neurix.simrs.master.tindakanmedis.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakanmedis.model.TindakanMedis;
import com.neurix.simrs.master.tindakanmedis.model.TindakanMedisDetail;

import java.util.List;

public interface TindakanMedisDetailBo
{
    public List<TindakanMedisDetail> getByCriteria(TindakanMedisDetail bean) throws GeneralBOException;
}
