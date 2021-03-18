package com.neurix.simrs.transaksi.konsultasigizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.konsultasigizi.model.KonsultasiGizi;

import java.util.List;

public interface KonsultasiGiziBo {
    public List<KonsultasiGizi> getByCriteria(KonsultasiGizi bean) throws GeneralBOException;
    public void saveEdit(KonsultasiGizi bean) throws GeneralBOException;
    public List<KonsultasiGizi> pushNotif(String branchId) throws GeneralBOException;
}
