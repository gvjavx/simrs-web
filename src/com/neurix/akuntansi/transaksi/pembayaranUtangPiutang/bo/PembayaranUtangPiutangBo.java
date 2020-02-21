package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.bo;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutang;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutangDetail;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

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

    List<PembayaranUtangPiutangDetail> getSearchNotaPembayaran(String masterId,String transaksiId) throws GeneralBOException;

    void postingJurnal(PembayaranUtangPiutang bean) throws GeneralBOException;
}
