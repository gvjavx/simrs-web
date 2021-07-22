package com.neurix.hris.transaksi.refreshLembur.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.lembur.model.Lembur;

import java.sql.Date;
import java.util.List;

/**
 * Created by Aji Noor on 22/07/2021
 */
public interface RefreshLemburBo {
    public void refreshAbsensiLembur(List<Lembur> lemburList, Date tanggal, Boolean chance) throws GeneralBOException;

}
