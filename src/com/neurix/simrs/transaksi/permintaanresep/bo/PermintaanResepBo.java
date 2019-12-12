package com.neurix.simrs.transaksi.permintaanresep.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Toshiba on 11/12/2019.
 */
public interface PermintaanResepBo {
    public List<PermintaanResep> getByCriteria(PermintaanResep bean) throws GeneralBOException;
    public void saveAdd(PermintaanResep bean, String resep) throws GeneralBOException, JSONException;
    public void saveObatResep(TransaksiObatDetail bean) throws GeneralBOException;
}
