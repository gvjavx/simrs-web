package com.neurix.simrs.transaksi.obatpoli.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Toshiba on 11/12/2019.
 */
public interface ObatPoliBo {
    public List<ObatPoli> getObatPoliByCriteria(ObatPoli bean) throws GeneralBOException;
    public List<PermintaanObatPoli> getSearchPermintaanObatPoli(PermintaanObatPoli bean, boolean isPoli) throws GeneralBOException;
    public void saveRequest(PermintaanObatPoli bean, List<TransaksiObatDetail> obatDetailList) throws GeneralBOException;
    public void saveReture(PermintaanObatPoli bean, List<TransaksiObatDetail> transaksiObatDetails) throws GeneralBOException, JSONException;

    public void saveApproveRequest(PermintaanObatPoli bean, List<TransaksiObatDetail> transList, boolean isPoli) throws GeneralBOException, JSONException;
    public void saveApproveReture(PermintaanObatPoli bean, boolean isPoli) throws GeneralBOException;

    public void saveApproveDiterima(PermintaanObatPoli bean, List<TransaksiObatDetail> obatDetailList) throws GeneralBOException;

    public List<ObatPoli> getTujuanPelayanan(ObatPoli bean) throws GeneralBOException;
    public List<PermintaanObatPoli> getDetailLitsPermintaan(PermintaanObatPoli bean, boolean isPoli) throws GeneralBOException;

    public CheckObatResponse checkObatStockLama(String idObat, String branchId) throws GeneralBOException;
    public List<PermintaanObatPoli> getCekListEntityObatPoli(PermintaanObatPoli bean) throws GeneralBOException;
    public List<TransaksiObatDetail> getListTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException;

    public void saveVerifikasiObat(List<Obat> obatList) throws GeneralBOException;
    public List<TransaksiObatDetail> getListObatTelahDiterima(String idPermintaan) throws GeneralBOException;
    public List<PermintaanObatPoli> getListObatDetailRequest(PermintaanObatPoli bean) throws GeneralBOException;

    public List<ObatPoli> getListObatPoliGroup(String idPelayanan, String branchId) throws GeneralBOException;
}
