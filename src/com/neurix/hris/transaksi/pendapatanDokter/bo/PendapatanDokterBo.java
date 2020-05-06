package com.neurix.hris.transaksi.pendapatanDokter.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.pendapatanDokter.model.PendapatanDokter;

import java.util.List;

public interface PendapatanDokterBo extends BaseMasterBo<PendapatanDokter> {

    List<PendapatanDokter> getByCriteriaForPendapatanDokter(PendapatanDokter bean);

    List<PendapatanDokter> getDataPendapatanDokter(PendapatanDokter bean);
    List<PendapatanDokter> getDetailPendapatan(PendapatanDokter bean);

    List<PendapatanDokter> saveAddPendapatanDokter(PendapatanDokter bean) throws GeneralBOException;

    List<PendapatanDokter> getDetailPendapatanPph(String idPendapatan);
}