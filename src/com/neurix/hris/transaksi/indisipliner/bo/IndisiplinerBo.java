package com.neurix.hris.transaksi.indisipliner.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.indisipliner.model.Indisipliner;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;

import java.sql.Date;
import java.util.List;

public interface IndisiplinerBo extends BaseMasterBo<Indisipliner> {
    List<Notifikasi> saveAddIndisipliner(Indisipliner bean) throws GeneralBOException;

    List<Indisipliner> ListOfIndisipliner(Indisipliner searchBean) throws GeneralBOException;

    List<Indisipliner> getListIndisiplinerByPersonAndTanggal(String nip, Date tanggal) throws GeneralBOException;

    List<Indisipliner> getListIndisipliner(String nip) throws GeneralBOException;

    List<Notifikasi> saveApprove(Indisipliner bean) throws GeneralBOException;

    void saveClosed(Indisipliner bean) throws GeneralBOException;

    List<Indisipliner> getIndisiplinerUser(Indisipliner bean);
}
