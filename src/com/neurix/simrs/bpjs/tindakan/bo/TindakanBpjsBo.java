package com.neurix.simrs.bpjs.tindakan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.model.KabupatenBpjs;
import com.neurix.simrs.bpjs.model.KecamatanBpjs;
import com.neurix.simrs.bpjs.model.KelasRawatBpjs;
import com.neurix.simrs.bpjs.model.ProvinsiBpjs;
import com.neurix.simrs.bpjs.tindakan.model.*;

import java.util.List;

public interface TindakanBpjsBo{
    public List<TindakanBpjs> getByCriteria(TindakanBpjs searchBean) throws GeneralBOException;

    void GetTindakanByAPIBpjs(TindakanBpjs bean) throws GeneralBOException;
}