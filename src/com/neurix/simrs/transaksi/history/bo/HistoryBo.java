package com.neurix.simrs.transaksi.history.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.history.model.HistoryPelayanan;
import com.neurix.simrs.transaksi.history.model.HistoryRuangan;

import java.util.List;

/**
 * @author gondok
 * Monday, 18/11/19 10:24
 */
public interface HistoryBo {
   List<HistoryPelayanan> getPelayananByCriteria(HistoryPelayanan bean)throws GeneralBOException;
   List<HistoryRuangan> getRuanganByCriteria(HistoryRuangan bean) throws GeneralBOException;
   void saveAddPelayanan(HistoryPelayanan bean)throws GeneralBOException;
   void saveAddRugangan(HistoryRuangan bean) throws GeneralBOException;
}
