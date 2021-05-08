package com.neurix.hris.transaksi.lembur.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;

import java.sql.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface LemburBo extends BaseMasterBo<Lembur> {
    List<Lembur> getBiodatawithCriteria(String query) throws GeneralBOException;

    List<Lembur> getByCriteriaForAbsensi(Lembur bean, String tanggal);

    List<Notifikasi> saveAddLembur(Lembur bean) throws GeneralBOException;

    List<Notifikasi> saveApprove(Lembur bean) throws GeneralBOException;

    String testTanggal(Date tanggalAwal, Date tanggalAkhir, String nip);
    List<Lembur> getCekLembur(String nip, Date tanggal);
    List<Object[]> findInfoLembur(String idLembur, String nip) throws GeneralBOException;
    List<Object[]> findAllConfirmByAtasan(String id, String flag) throws GeneralBOException;

    public void saveCancel(Lembur bean) throws GeneralBOException;

    public Boolean cekHakLembur(String nip) throws GeneralBOException;
}
