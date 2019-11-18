package com.neurix.simrs.transaksi.antrianonline.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;

import java.util.List;

/**
 * @author gondok
 * Friday, 15/11/19 14:46
 */
public interface AntrianOnlineBo {
    public List<AntianOnline> getByCriteria(AntianOnline bean)throws GeneralBOException;
    public void saveAdd(AntianOnline bean)throws GeneralBOException;
}
