package com.neurix.simrs.transaksi.rawatinap.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsKategoriSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.SkorRanap;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface RawatInapBo {
    public List<RawatInap> getSearchRawatInap(RawatInap bean) throws GeneralBOException;
    public List<SkorRanap> getListSkorRanap(SkorRanap bean) throws GeneralBOException;
    public List<ImSimrsSkorRanapEntity> getListMasterSkor(String id) throws GeneralBOException;
    public CrudResponse saveAddSkorRanap(String noCheckup, String idDetail, List<ItSimrsSkorRanapEntity> skors);
    public List<SkorRanap> getListSumSkorRanap(String noCheckup, String idDetail, String idkategori);
    public ImSimrsKategoriSkorRanapEntity kategoriSkorRanap(String id);
    public List<MonVitalSign> getListMonVitalSign(MonVitalSign bean);
    public CrudResponse saveMonVitalSign(ItSimrsMonVitalSignEntity bean);
}
