package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.bo;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.Lampiran;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutang;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutangDetail;
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
public interface PembayaranUtangPiutangBo extends BaseMasterBo<PembayaranUtangPiutang> {
    public void saveDelete(PembayaranUtangPiutang bean) throws GeneralBOException;

    List<PembayaranUtangPiutangDetail> getSearchNotaPembayaran(String masterId,String transaksiId,String branchId,String divisiId,String coa) throws GeneralBOException;

    void postingJurnal(PembayaranUtangPiutang bean) throws GeneralBOException;

    void addPrintCount(String noJurnal) throws GeneralBOException;

    String saveAddPembayaran(PembayaranUtangPiutang bean, List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList, List<Lampiran> lampiranList) throws GeneralBOException;

    List<PembayaranUtangPiutangDetail> getDetailPembayaran(String pembayaranId) throws GeneralBOException;

    List<Lampiran> getLampiranList(String pembayaranId) throws GeneralBOException;

    String getKodeRekeningKasJurnal(String noJurnal) throws GeneralBOException;

    ImTransEntity getTipeMaster(String transId) throws GeneralBOException;

    String getNamaRekeningKasJurnal(String noJurnal) throws GeneralBOException;

    Trans getDisableTrans(String transId,String coaLawan) throws GeneralBOException;

    String getPosisiCoaDiMappingJurnal(String transId, String coa) throws GeneralBOException;

    List<Notifikasi> approvePembayaran(PembayaranUtangPiutang bean) throws GeneralBOException;

    List<PembayaranUtangPiutangDetail> searchPengajuanBiaya(String branchId) throws GeneralBOException;
}
