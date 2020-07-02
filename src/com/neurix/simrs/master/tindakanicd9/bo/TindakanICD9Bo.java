package com.neurix.simrs.master.tindakanicd9.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.master.tindakanicd9.model.TindakanICD9;

import java.util.List;

public interface TindakanICD9Bo{
    public List<TindakanICD9> getByCriteria(TindakanICD9 bean) throws GeneralBOException;
    public List<TindakanICD9> getSearchICD9(String key) throws GeneralBOException;
}
