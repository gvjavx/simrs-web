package com.neurix.simrs.transaksi.tindakanrawat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface TindakanRawatBo {
    public List<TindakanRawat> getByCriteria(TindakanRawat bean)throws GeneralBOException;
    public void saveAdd(TindakanRawat bean)throws GeneralBOException;
    public void saveEdit(TindakanRawat bean)throws GeneralBOException;
    public List<TindakanRawat> cekTodayTindakanTarifKamar(String idDetail, String tanggal) throws GeneralBOException;
    public CheckResponse updateFlagApproveTindakan(TindakanRawat bean) throws GeneralBOException;
}
