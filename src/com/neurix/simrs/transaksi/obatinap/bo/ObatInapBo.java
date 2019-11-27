package com.neurix.simrs.transaksi.obatinap.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.obatinap.model.ObatInap;

import java.util.List;

public interface ObatInapBo {
    public List<ObatInap> getByCriteria(ObatInap bean) throws GeneralBOException;
    public void saveAdd(ObatInap bean)throws GeneralBOException;
}