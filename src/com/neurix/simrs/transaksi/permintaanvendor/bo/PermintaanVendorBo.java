package com.neurix.simrs.transaksi.permintaanvendor.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.vividsolutions.jts.geom.LineSegment;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Toshiba on 27/12/2019.
 */
public interface PermintaanVendorBo {
    public List<PermintaanVendor> getByCriteria(PermintaanVendor bean) throws GeneralBOException;
    public void saveListObatPo(PermintaanVendor bean) throws GeneralBOException;
    public void saveNewPabrik(TransaksiObatDetail bean, List<String> idJenisObats) throws GeneralBOException;
    public void saveUpdateTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException;
    public void saveConfirm(PermintaanVendor bean, List<TransaksiObatDetail> listObat, List<TransaksiObatDetail> listObatNew) throws GeneralBOException;
    public List<TransaksiObatDetail> getNewObatDetail(TransaksiObatDetail bean) throws GeneralBOException;
    public Integer getLastNoBatch(String idApproval) throws GeneralBOException;
    public List<BatchPermintaanObat> getListBatchObatByIdApproval(String idApproval) throws GeneralBOException;
    public List<TransaksiObatDetail> getListTransByBatchSorted(List<TransaksiObatDetail> obatDetails, Integer noBatch) throws GeneralBOException;
    }
