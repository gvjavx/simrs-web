package com.neurix.simrs.transaksi.tindakanrawat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface TindakanRawatBo {
    public List<TindakanRawat> getByCriteria(TindakanRawat bean)throws GeneralBOException;
    public CrudResponse saveAdd(TindakanRawat bean)throws GeneralBOException;
    public CrudResponse saveEdit(TindakanRawat bean)throws GeneralBOException;
    public List<TindakanRawat> cekTodayTindakanTarifKamar(String idDetail, String tanggal) throws GeneralBOException;
    public CheckResponse updateFlagApproveTindakan(TindakanRawat bean) throws GeneralBOException;
    public ItSimrsTindakanRawatEntity getTindakanRawatEntityById(String id) throws GeneralBOException;

    public List<TindakanRawat> getListTindakanRawat(String noCheckup, String jenis)throws GeneralBOException;
}
