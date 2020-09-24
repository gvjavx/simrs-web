package com.neurix.simrs.transaksi.permintaanvendor.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.permintaanvendor.model.*;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsTransaksiObatDetailBatchEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatBatch;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.vividsolutions.jts.geom.LineSegment;
import org.json.JSONException;

import java.sql.Date;
import java.util.List;

/**
 * Created by Toshiba on 27/12/2019.
 */
public interface PermintaanVendorBo {
    public List<PermintaanVendor> getByCriteria(PermintaanVendor bean) throws GeneralBOException;
    public CheckResponse saveListObatPo(PermintaanVendor bean) throws GeneralBOException;
    public CheckResponse saveNewPabrik(TransaksiObatDetail bean, List<String> idJenisObats) throws GeneralBOException;
    public CheckObatResponse saveUpdateTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException;
    public CheckObatResponse saveConfirm(PermintaanVendor bean, List<TransaksiObatDetail> listObat, List<TransaksiObatDetail> listObatNew) throws GeneralBOException;
    public List<TransaksiObatDetail> getNewObatDetail(TransaksiObatDetail bean) throws GeneralBOException;
    public Integer getLastNoBatch(String idApproval) throws GeneralBOException;
    public List<BatchPermintaanObat> getListBatchObatByIdApproval(String idApproval) throws GeneralBOException;
    public List<TransaksiObatDetail> getListTransByBatchSorted(List<TransaksiObatDetail> obatDetails, Integer noBatch, String isApprove) throws GeneralBOException;
    public Boolean isNewBatchCheckByNoBatchAndExpDate(String idTransObatDetail, Integer noBatch, Date expDate) throws GeneralBOException;
    public List<TransaksiObatDetail> getListApprovedBatch(String idPermintaanObat, Integer noBatch) throws GeneralBOException;

    public CrudResponse tutupPurchaseOrder(String idPermintaanVendor, String noJurnal) throws GeneralBOException;
    public List getListPermintaanVendorDoc(String idPermintaanVendor) throws GeneralBOException;
    public void saveUpoadDocPermintaanVendor(PermintaanVendor bean) throws GeneralBOException;

    public List<TransaksiObatDetail> getListObatByBatch(String idPermintaan, Integer noBatch) throws GeneralBOException;
    public List<Obat> getSearchObat(String query, String branch) throws GeneralBOException;
    public ImUserVendorEntity getEntityUserVendorByIdUser(String userId) throws GeneralBOException;
    public MtSimrsPermintaanVendorEntity getPermintaanVendorEntityById(String idPermintaan) throws GeneralBOException;
    public void saveListBatch(List<MtSimrsTransaksiObatDetailBatchEntity> listBatchEntity) throws GeneralBOException;
    public TransaksiObatBatch getBatchByIdTransAndNoBatch(String idTrans, String noBatch) throws GeneralBOException;
    public List<TransaksiObatDetail> getListObatByBatchByDo(String idPermintaan, String noDo) throws GeneralBOException;
    public void saveListDocVendor(List<ItSimrsDocPoEntity> docPoEntities) throws GeneralBOException;
    public List<DocPo> getListItemDoc(String idPermintaanVendor, String noBatch) throws GeneralBOException;
    public List<DocPo> getListImgByItem(String idItem) throws GeneralBOException;
    public List<TransaksiObatBatch> getListBatchByJenisItem(String idItem, String jenis) throws GeneralBOException;
}
