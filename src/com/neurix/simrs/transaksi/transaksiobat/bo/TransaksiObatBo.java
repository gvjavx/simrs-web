package com.neurix.simrs.transaksi.transaksiobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.obatracik.model.ObatRacik;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.transaksiobat.model.*;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Toshiba on 11/12/2019.
 */
public interface TransaksiObatBo {
    public List<TransaksiObatDetail> getSearchObatTransaksiByCriteria(TransaksiObatDetail bean) throws GeneralBOException;
    public void saveAdd(TransaksiObatDetail bean, List<TransaksiObatDetail> listOfObatResep, List<TransaksiObatDetail> listOfObat) throws GeneralBOException;

    public List<TransaksiObatDetail> getByCriteria(TransaksiObatDetail bean) throws GeneralBOException;
    public void saveEditDetail(TransaksiObatDetail bean) throws GeneralBOException;

    public List<PermintaanResep> getListResepPasien(PermintaanResep bean) throws GeneralBOException;
    public void saveAntrianResep(PermintaanResep bean) throws GeneralBOException;
    public void updateAntrianResep(PermintaanResep bean) throws GeneralBOException;
    public void saveVerifikasiObat(List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities, BigInteger qtyApprove) throws GeneralBOException;
    public CheckObatResponse saveApproveResepPoli(TransaksiObatDetail bean) throws GeneralBOException;
    public List<MtSimrsHargaObatEntity> getListEntityHargaObat(HargaObat bean) throws GeneralBOException;

    public CheckObatResponse saveListObatPembelian(TransaksiObatDetail bean, List<TransaksiObatDetail> listBatch) throws GeneralBOException;
    public List<TransaksiObatDetail> getListPembelianObat(String idApprove) throws GeneralBOException;
    public void pembayaranObatBaru(TransaksiObatDetail bean) throws GeneralBOException;
    public List<TransaksiObatDetail> getListRiwayatPembelianObat(String idApprove) throws GeneralBOException;
    public List<MtSimrsRiwayatPembelianObat> getRiwayatPembelianObat(String idApprove) throws GeneralBOException;
    public List<TransaksiObatDetail> getListPermintaanBatch(String idApproval, String flagDiterima) throws GeneralBOException;
    public void saveEditFlagPengambilan(String idTrans) throws GeneralBOException;

    public Boolean cekObatKronis(String idApproval) throws GeneralBOException;
    public TransaksiObatDetail getTarifApproveResep(String idApproval) throws GeneralBOException;
    public TransaksiObatDetail getTotalHargaResep(String idPermintaan) throws GeneralBOException;

    public CheckResponse setTtdPasien(String idPermintaan, String ttdPasien, String ttdApoteker) throws GeneralBOException;

    public List<TransaksiObatDetail> listObatResepApprove(String idApprove) throws GeneralBOException;

    public List<PermintaanResep> getListNotifResep(String idPelayanan, String branchId) throws GeneralBOException;
    public List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException;
    public void saveUpdateRetureObat(List<TransaksiObatDetail> listReture, TransaksiObatDetail bean) throws GeneralBOException;
    public void saveUpdateHargaRataBarangMasukKarnaReture(TransaksiObatDetail bean) throws GeneralBOException;
    public List<TransaksiObatDetail> getListTransaksiObatDetailBatchByIdResepAndIdBarang(String idResep, String idBarang) throws GeneralBOException;
    public String getFlagIsRacikInTransaksiObatDetail(String idPermintaan) throws GeneralBOException;
    public List<ObatRacik> getListNamaRacik(String idRacik) throws GeneralBOException;

}
