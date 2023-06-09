package com.neurix.simrs.transaksi.rawatinap.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.moncairan.model.ItSimrsMonCairanEntity;
import com.neurix.simrs.transaksi.moncairan.model.MonCairan;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import com.neurix.simrs.transaksi.monpemberianobat.model.MonPemberianObat;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
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
    public List<ItSimrsRawatInapEntity> getListEntityByCriteria(RawatInap bean) throws GeneralBOException;
    public List<RawatInap> getSearchRawatInap(RawatInap bean) throws GeneralBOException;
    public List<SkorRanap> getListSkorRanap(SkorRanap bean) throws GeneralBOException;
    public List<ImSimrsSkorRanapEntity> getListMasterSkor(String id) throws GeneralBOException;
    public CrudResponse saveAddSkorRanap(String noCheckup, String idDetail, List<ItSimrsSkorRanapEntity> skors);
    public List<SkorRanap> getListSumSkorRanap(String noCheckup, String idDetail, String idkategori);
    public ImSimrsKategoriSkorRanapEntity kategoriSkorRanap(String id);
    public List<MonVitalSign> getListMonVitalSign(MonVitalSign bean);
    public CrudResponse saveMonVitalSign(ItSimrsMonVitalSignEntity bean);
    public void saveUpdateMonVitalSign(ItSimrsMonVitalSignEntity bean) throws GeneralBOException;
    public List<MonCairan> getListMonCairan(MonCairan bean);
    public CrudResponse saveMonCairan(ItSimrsMonCairanEntity bean);
    public void saveUpdateMonCairan(ItSimrsMonCairanEntity bean) throws GeneralBOException;
    public List<MonPemberianObat> getListPemberianObat(MonPemberianObat bean);
    public CrudResponse saveMonPemberianObat(ItSimrsMonPemberianObatEntity bean);
    public void saveUpdateMonPemberianObat(ItSimrsMonPemberianObatEntity bean) throws GeneralBOException;
    public List<Obat> getListObatParenteral(String idDetailCheckup);
    public List<Obat> getListObatNonParenteral(String idDetailCheckup);
    public List<MonVitalSign> getListGraf(MonVitalSign bean);
    public List<ImSimrsKategoriSkorRanapEntity> getListKategoriSkorRanapByHead(String head);
    public List<RawatInap> getByCriteria(RawatInap bean) throws GeneralBOException;

    public MonVitalSign getMonVitalSignById(String id) throws GeneralBOException;
    public MonCairan getMonCairanById(String id) throws GeneralBOException;
    public MonPemberianObat getMonPemberianObatById(String id) throws GeneralBOException;
    public RawatInap getLastUsedRoom(String id) throws GeneralBOException;

    public List<RawatInap> getListTppri(RawatInap bean) throws GeneralBOException;
    public CrudResponse saveAdd(RawatInap bean) throws GeneralBOException;

    public List<RawatInap> getRuanganRawatInap(RawatInap bean) throws GeneralBOException;
    public CrudResponse updateRiwayatKamar(RawatInap bean) throws GeneralBOException;
    public CrudResponse saveUangMuka(UangMuka bean) throws GeneralBOException;
    public List<UangMuka> getAllListUangMuka(UangMuka bean) throws GeneralBOException;
    public HeaderCheckup getDetailGelang(String noCheckup) throws GeneralBOException;
}
