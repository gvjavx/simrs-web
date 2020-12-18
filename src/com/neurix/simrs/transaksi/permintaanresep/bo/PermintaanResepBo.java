package com.neurix.simrs.transaksi.permintaanresep.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Toshiba on 11/12/2019.
 */
public interface PermintaanResepBo {
    public List<PermintaanResep> getByCriteria(PermintaanResep bean) throws GeneralBOException;
    public List<ImSimrsPermintaanResepEntity> getListEntityResep(PermintaanResep bean) throws GeneralBOException;
    public CrudResponse saveAdd(PermintaanResep bean, List<TransaksiObatDetail> detailList) throws GeneralBOException;
    public CrudResponse saveObatResep(TransaksiObatDetail bean) throws GeneralBOException;
    public ImSimrsPermintaanResepEntity getEntityPermintaanResepById(String id) throws GeneralBOException;

    public List<PermintaanResep> getListResepPasien(String noCheckup) throws GeneralBOException;
}
