package com.neurix.simrs.transaksi.checkupdetail.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.statuspasien.bo.StatusPasienBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface CheckupDetailBo {
    public List<HeaderDetailCheckup> getByCriteria(HeaderDetailCheckup bean) throws GeneralBOException;

    public List<HeaderDetailCheckup> getSearchRawatJalan(HeaderDetailCheckup bean) throws GeneralBOException;

    public void updateRuanganInap(String idRuangan, String idDetailCheckup) throws GeneralBOException;

    public void saveEdit(HeaderDetailCheckup bean) throws GeneralBOException;

    public void saveAdd(HeaderDetailCheckup bean) throws GeneralBOException;

    public BigInteger getSumOfTindakanByNoCheckup(String noCheckup) throws GeneralBOException;
}
