package com.neurix.akuntansi.transaksi.koreksi.bo;

import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.kas.model.Lampiran;
import com.neurix.akuntansi.transaksi.koreksi.model.Koreksi;
import com.neurix.akuntansi.transaksi.koreksi.model.KoreksiDetail;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by Aji Noor on 18/03/2021
 */
public interface KoreksiBo extends BaseMasterBo<Koreksi> {
    String saveAddKoreksi(Koreksi bean, List<KoreksiDetail> koreksiDetailList, List<Lampiran> lampiranList) throws GeneralBOException;

    Koreksi saveEditKoreksi(Koreksi bean, List<KoreksiDetail> koreksiDetailList, List<Lampiran> lampiranList) throws GeneralBOException;

    List<KoreksiDetail> getKoreksiDetail(String koreksiId) throws GeneralBOException;

    List<Lampiran> getLampiranList(String kasId) throws GeneralBOException;

    void approveKoreksi(Koreksi bean) throws GeneralBOException;

    void postingJurnal(Koreksi bean) throws GeneralBOException;

    void addPrintCount(String noJurnal) throws GeneralBOException;

    String getNamaRekeningKasJurnal(String noJurnal) throws GeneralBOException;

    Koreksi getById(String koreksiId);

    ImTransEntity getTipeMaster(String transId) throws GeneralBOException;

    Trans getDisableTrans(String transId, String coaLawan) throws GeneralBOException;

    String getPosisiCoaDiMappingJurnal(String transId, String coa) throws GeneralBOException;

    List<KoreksiDetail> getSearchNotaKoreksi(String masterId, String transaksiId, String branchId, String divisiId, String coa) throws GeneralBOException;

}
