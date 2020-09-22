package com.neurix.simrs.transaksi.periksalab.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.periksalab.model.OrderPeriksaLab;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLabDetail;

import java.util.List;

public interface OrderPeriksaLabBo {
    public List<OrderPeriksaLab> getByCriteria(OrderPeriksaLab bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<OrderPeriksaLab> list) throws GeneralBOException;
}
