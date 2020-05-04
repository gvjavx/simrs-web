package com.neurix.simrs.transaksi.rekonsiliasiobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.RekonsiliasiObat;

import java.util.List;

public interface RekonsiliasiObatBo {
    public List<RekonsiliasiObat> getByCriteria(RekonsiliasiObat bean) throws GeneralBOException;
    public CrudResponse saveAdd(RekonsiliasiObat bean) throws GeneralBOException;
}
