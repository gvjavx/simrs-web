package com.neurix.akuntansi.transaksi.kas.bo;

import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.kas.model.Kas;
import com.neurix.akuntansi.transaksi.kas.model.KasDetail;
import com.neurix.akuntansi.transaksi.kas.model.Lampiran;
import com.neurix.akuntansi.transaksi.koreksi.model.KoreksiDetail;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface KasBo extends BaseMasterBo<Kas> {

    public void saveDelete(Kas bean) throws GeneralBOException;

    List<KasDetail> getSearchNotaKas(String masterId, String transaksiId, String branchId, String divisiId, String coa) throws GeneralBOException;

    void postingJurnal(Kas bean) throws GeneralBOException;

    void addPrintCount(String noJurnal) throws GeneralBOException;

    String saveAddKas(Kas bean, List<KasDetail> kasDetailList, List<Lampiran> lampiranList) throws GeneralBOException;

    String saveEditKas(Kas bean, List<KasDetail> kasDetailList, List<Lampiran> lampiranList) throws GeneralBOException;

//    Kas saveEditKas(Kas bean) throws GeneralBOException;

    List<KasDetail> getKasDetail(String kasId) throws GeneralBOException;

    List<Lampiran> getLampiranList(String kasId) throws GeneralBOException;

    Map<String,Lampiran> getListLampiran(String transactionId);


    String getKodeRekeningKasJurnal(String noJurnal) throws GeneralBOException;

    public List<Kas> getWithUangMuka(Kas searchBean) throws GeneralBOException;

    ImTransEntity getTipeMaster(String transId) throws GeneralBOException;

    String getNamaRekeningKasJurnal(String noJurnal) throws GeneralBOException;

    Kas getKasById(String kasId) throws GeneralBOException;

    Trans getDisableTrans(String transId, String coaLawan) throws GeneralBOException;

    String getPosisiCoaDiMappingJurnal(String transId, String coa) throws GeneralBOException;

    List<Notifikasi> approveKas(Kas bean) throws GeneralBOException;

    List<KasDetail> searchPengajuanBiaya(String branchId) throws GeneralBOException;
}
