package com.neurix.simrs.transaksi.hemodinamika.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodinamika.model.Hemodinamika;

import java.util.List;

public interface HemodinamikaBo {
    public List<Hemodinamika> getByCriteria(Hemodinamika bean) throws GeneralBOException;
    public CrudResponse saveAdd(Hemodinamika bean) throws GeneralBOException;
    public Boolean cekData(String id, String waktu) throws GeneralBOException;
}
