package com.neurix.akuntansi.transaksi.pengajuanBiaya.bo;

import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiayaDetail;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;

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

    List<Notifikasi> saveApproveAtasanPengajuan(PengajuanBiayaDetail bean) throws GeneralBOException;

    List<Notifikasi> saveApproveKeuanganPengajuan(PengajuanBiayaDetail bean) throws GeneralBOException;
}
