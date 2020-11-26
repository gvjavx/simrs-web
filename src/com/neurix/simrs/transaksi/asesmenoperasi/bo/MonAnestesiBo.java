package com.neurix.simrs.transaksi.asesmenoperasi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenoperasi.model.AsesmenOperasi;
import com.neurix.simrs.transaksi.asesmenoperasi.model.MonitoringAnestesi;

import java.util.List;

public interface MonAnestesiBo {
    public List<MonitoringAnestesi> getByCriteria(MonitoringAnestesi bean) throws GeneralBOException;
    public CrudResponse saveAdd(MonitoringAnestesi bean) throws GeneralBOException;
    public Boolean cekDataAnestesi(String id, String jam, String jenis) throws GeneralBOException;
    public CrudResponse saveDelete(MonitoringAnestesi bean) throws GeneralBOException;
}
