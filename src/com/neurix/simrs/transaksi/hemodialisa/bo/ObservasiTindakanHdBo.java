package com.neurix.simrs.transaksi.hemodialisa.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.model.ObservasiTindakanHd;

import java.util.List;

public interface ObservasiTindakanHdBo {
    public List<ObservasiTindakanHd> getByCriteria(ObservasiTindakanHd bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<ObservasiTindakanHd> list) throws GeneralBOException;
    public CrudResponse saveDetele(ObservasiTindakanHd bean) throws GeneralBOException;
}
