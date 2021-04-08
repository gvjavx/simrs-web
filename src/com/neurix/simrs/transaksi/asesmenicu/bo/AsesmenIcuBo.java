package com.neurix.simrs.transaksi.asesmenicu.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenicu.model.AsesmenIcu;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.PersetujuanTindakanMedis;

import java.util.List;

public interface AsesmenIcuBo {
    public List<AsesmenIcu> getByCriteria(AsesmenIcu bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<AsesmenIcu> list) throws GeneralBOException;
    public CrudResponse saveDelete(AsesmenIcu bean) throws GeneralBOException;
    public PersetujuanTindakanMedis getPersetujuanTindakan(AsesmenIcu bean) throws GeneralBOException;
}
