package com.neurix.akuntansi.transaksi.penyewaanLahan.bo;

import com.neurix.akuntansi.transaksi.penyewaanLahan.model.ItAkunPenyewaanLahanEntity;
import com.neurix.akuntansi.transaksi.penyewaanLahan.model.PenyewaanLahan;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PenyewaanLahanBo extends BaseMasterBo<PenyewaanLahan> {
    public void saveDelete(PenyewaanLahan bean) throws GeneralBOException;

    ItAkunPenyewaanLahanEntity getPenyewaanLahanById(String penyewaanLahanId);

    void batalkanPenyewaanLahan(PenyewaanLahan bean) throws GeneralBOException;

    void postingJurnal(PenyewaanLahan bean) throws GeneralBOException;
}
