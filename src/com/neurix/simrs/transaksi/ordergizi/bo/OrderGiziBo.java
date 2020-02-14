package com.neurix.simrs.transaksi.ordergizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.obatinap.model.ObatInap;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;

import java.util.List;

public interface OrderGiziBo {
    public List<OrderGizi> getByCriteria(OrderGizi bean) throws GeneralBOException;
    public void saveAdd(OrderGizi bean)throws GeneralBOException;
    public void saveEdit(OrderGizi bean)throws GeneralBOException;
    public CheckResponse updateDiterimaFLag(OrderGizi bean) throws GeneralBOException;
}