package com.neurix.simrs.transaksi.transaksiobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsRiwayatPembelianObat;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsTransaksiObatDetailBatchEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.RiwayatTransaksiObat;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;

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
    public void saveVerifikasiObat(List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities) throws GeneralBOException;
    public void saveApproveResepPoli(TransaksiObatDetail bean) throws GeneralBOException;
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

}
