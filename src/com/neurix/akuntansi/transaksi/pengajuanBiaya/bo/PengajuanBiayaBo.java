package com.neurix.akuntansi.transaksi.pengajuanBiaya.bo;

import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiayaDetail;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiayaRk;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    void postingJurnal(PengajuanBiaya bean) throws GeneralBOException;

    List<Notifikasi> saveAddPengajuanBiaya(PengajuanBiaya bean) throws GeneralBOException;

    List<Notifikasi> sendNotifikasiKeAdminAks(String branchId, String id, String keterangan, String createdWho);

    List<Notifikasi> saveAddPengajuan(PengajuanBiaya bean, List<PengajuanBiayaDetail> pengajuanBiayaDetailList) throws GeneralBOException;

    List<Notifikasi> saveApprove(PengajuanBiaya bean) throws GeneralBOException;

    List<PengajuanBiayaDetail> searchPengajuanDetail(String pengajuanId) throws GeneralBOException;

    List<PengajuanBiayaDetail> cariPengajuanBiayaDetail(String pengajuanDetailId) throws GeneralBOException;

    List<PengajuanBiayaDetail> cariPengajuanBiayaDetailDenganRkId(String rkId) throws GeneralBOException;

    List<PengajuanBiayaDetail> cariPengajuanBiayaDetailUangMuka(String pengajuanDetailId) throws GeneralBOException;

    List<Notifikasi> saveApproveAtasanPengajuan(PengajuanBiayaDetail bean) throws GeneralBOException;

    List<Notifikasi> saveApproveKeuanganPengajuan(PengajuanBiayaDetail bean) throws GeneralBOException;

    List<PengajuanBiayaDetail> getDetailPembayaran(String pengajuanBiayaId) throws GeneralBOException;

    List<Notifikasi> saveNotApprovePengajuanBiaya(PengajuanBiayaDetail bean) throws GeneralBOException;

    void cekApakahBisaDiClose(String pengajuanId) throws GeneralBOException;

    void setRkSudahDikirim(ArrayList pengajuanId, String coa) throws GeneralBOException;

    PengajuanBiaya cekApakahBolehRk(String pengajuanId) throws GeneralBOException;

    String batalkanPengajuanBiaya(PengajuanBiaya bean) throws GeneralBOException;

    String cekApakahSudahCloseSemua(String pengajuanDetailId) throws GeneralBOException;

    PengajuanBiayaDetail getDetailPembayaranForReport(String pengajuanBiayaDetailId) throws GeneralBOException;

    boolean cekApakahPengajuanBisaDiubah(String id, BigDecimal jumlah);

    PengajuanBiayaDetail getDetailById(String id);

    List<PengajuanBiayaDetail> getByCriteriaDetail(PengajuanBiayaDetail searchBean) throws GeneralBOException;

    PengajuanBiayaDetail modalPopUpDetail(String id);

    void setRkDiterima(ArrayList pengajuanId) throws GeneralBOException;

    String getNoKontrak(String keperluanId);

    String searchPengajuanDetailImage(String pengajuanId) throws GeneralBOException;

    PengajuanBiaya getPengajuanBiayaForRk(ArrayList pengajuanId, String status, String coaKas, String branchId) throws GeneralBOException;

    List<PengajuanBiayaRk> getDaftarPembayaranDo(PengajuanBiayaRk bean) throws GeneralBOException;

    void savePembayaranPengajuanDo(List<PengajuanBiayaRk> beanList) throws GeneralBOException;

    void approvePengajuanBiayaRk(List<PengajuanBiayaRk> beanList) throws GeneralBOException;

    void savePembayaranPengajuanDoFinal(List<PengajuanBiayaRk> beanList,String noJurnal,String metodeBayar) throws GeneralBOException;
}
