package com.neurix.simrs.transaksi.makananpendamping.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.makananpendamping.model.HeaderPendampingMakanan;

import java.util.List;

public interface HeaderPendampingMakananBo {
    public List<HeaderPendampingMakanan> getByCriteria(HeaderPendampingMakanan bean) throws GeneralBOException;
    public void saveAdd(HeaderPendampingMakanan bean) throws GeneralBOException;
    public List<HeaderPendampingMakanan> getListSearch(HeaderPendampingMakanan bean) throws GeneralBOException;
}
