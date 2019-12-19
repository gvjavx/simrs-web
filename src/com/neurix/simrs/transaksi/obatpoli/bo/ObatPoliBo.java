package com.neurix.simrs.transaksi.obatpoli.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Toshiba on 11/12/2019.
 */
public interface ObatPoliBo {
    public List<ObatPoli> getObatPoliByCriteria(ObatPoli bean) throws GeneralBOException;
    public List<PermintaanObatPoli> getSearchPermintaanObatPoli(PermintaanObatPoli bean) throws GeneralBOException;
    public void saveAdd(ObatPoli bean) throws GeneralBOException;
    public void saveAddWithRequest(ObatPoli bean, String request) throws GeneralBOException, JSONException;
    public void saveRequest(PermintaanObatPoli bean) throws GeneralBOException;
    public void saveReture(PermintaanObatPoli bean, String reture) throws GeneralBOException, JSONException;

    public void saveApproveRequest(PermintaanObatPoli bean) throws GeneralBOException;
    public void saveApproveReture(PermintaanObatPoli bean) throws GeneralBOException;

    public void saveApproveDiterima(PermintaanObatPoli bean) throws GeneralBOException;
}
