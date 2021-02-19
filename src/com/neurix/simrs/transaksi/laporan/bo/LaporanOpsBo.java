package com.neurix.simrs.transaksi.laporan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.laporan.model.LaporanOps;

import java.util.List;

public interface LaporanOpsBo {

    public List<LaporanOps> getByCriteria(LaporanOps bean) throws GeneralBOException;
    public List<LaporanOps> getLaporanRjRi(LaporanOps bean) throws GeneralBOException;
    public List<LaporanOps> getLaporanPlyUnggulan(LaporanOps bean) throws GeneralBOException;
    public List<LaporanOps> getListTahunByOps() throws GeneralBOException;
    public List<LaporanOps> getListDiagnosaTerbanyak(LaporanOps bean) throws GeneralBOException;
}
