package com.neurix.simrs.transaksi.asesmenicu.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenicu.model.KeseimbanganIcu;

import java.util.List;

public interface KeseimbanganIcuBo {
    public List<KeseimbanganIcu> getByCriteria(KeseimbanganIcu bean) throws GeneralBOException;
    public void saveAdd(List<KeseimbanganIcu> list) throws GeneralBOException;
    public void saveDelete(KeseimbanganIcu bean) throws GeneralBOException;
}
