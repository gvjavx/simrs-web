package com.neurix.simrs.master.kategoritindakan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface KategoriTindakanBo {
    public List<KategoriTindakan> getByCriteria(KategoriTindakan bean) throws GeneralBOException;
}
