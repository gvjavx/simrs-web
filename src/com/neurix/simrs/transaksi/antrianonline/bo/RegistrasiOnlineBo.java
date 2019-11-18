package com.neurix.simrs.transaksi.antrianonline.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.neurix.simrs.transaksi.antrianonline.model.RegistrasiOnline;

import java.util.List;

/**
 * @author gondok
 * Monday, 18/11/19 10:35
 */
public interface RegistrasiOnlineBo {
    public List<RegistrasiOnline> getByCriteria(RegistrasiOnline bean)throws GeneralBOException;
    public void saveAdd(RegistrasiOnline bean)throws GeneralBOException;
}
