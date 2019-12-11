package com.neurix.simrs.bpjs.tindakan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.tindakan.model.TindakanBpjs;

import java.util.List;

public interface TindakanBpjsBo{
    public List<TindakanBpjs> getByCriteria(TindakanBpjs searchBean) throws GeneralBOException;

    void GetTindakanByAPIBpjs(TindakanBpjs bean) throws GeneralBOException;
}