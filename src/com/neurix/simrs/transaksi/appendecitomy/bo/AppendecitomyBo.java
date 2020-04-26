package com.neurix.simrs.transaksi.appendecitomy.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.appendecitomy.model.Appendecitomy;

import java.util.List;

public interface AppendecitomyBo {
    public List<Appendecitomy> getByCriteria(Appendecitomy bean) throws GeneralBOException;
    public CrudResponse saveAdd(Appendecitomy bean) throws GeneralBOException;
}
