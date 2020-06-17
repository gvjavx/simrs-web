package com.neurix.akuntansi.transaksi.pengajuanBiaya.bo;

import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiayaDetail;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PengajuanBiayaBo extends BaseMasterBo<PengajuanBiaya> {
    public void saveDelete(PengajuanBiaya bean) throws GeneralBOException;

    List<Notifikasi> saveAddPengajuanBiaya(PengajuanBiaya bean) throws GeneralBOException;

    List<Notifikasi> saveAddPengajuan(PengajuanBiaya bean, List<PengajuanBiayaDetail> pengajuanBiayaDetailList) throws GeneralBOException;

    List<Notifikasi> saveApprove(PengajuanBiaya bean) throws GeneralBOException;

    List<PengajuanBiayaDetail> searchPengajuanDetail(String pengajuanId) throws GeneralBOException;

    List<PengajuanBiayaDetail> cariPengajuanBiayaDetail(String pengajuanDetailId, String divisiId) throws GeneralBOException;

    List<Notifikasi> saveApproveAtasanPengajuan(PengajuanBiayaDetail bean) throws GeneralBOException;

    List<Notifikasi> saveApproveKeuanganPengajuan(PengajuanBiayaDetail bean) throws GeneralBOException;

    List<PengajuanBiayaDetail> getDetailPembayaran(String pengajuanBiayaId) throws GeneralBOException;

    List<Notifikasi> saveNotApprovePengajuanBiaya(PengajuanBiayaDetail bean) throws GeneralBOException;

    PengajuanBiaya getPengajuanBiayaForRk(String pengajuanId,String status) throws GeneralBOException;

    void cekApakahBisaDiClose(String pengajuanId) throws GeneralBOException;

    void setRkSudahDikirim(String pengajuanId,String coa) throws GeneralBOException;

    PengajuanBiaya cekApakahBolehRk(String pengajuanId) throws GeneralBOException;

    String batalkanPengajuanBiaya(PengajuanBiaya bean) throws GeneralBOException;

    String cekApakahSudahCloseSemua(String pengajuanDetailId) throws GeneralBOException;

    PengajuanBiayaDetail getDetailPembayaranForReport(String pengajuanBiayaDetailId) throws GeneralBOException;

    boolean cekApakahPengajuanBisaDiubah(String id, BigDecimal jumlah);
}
