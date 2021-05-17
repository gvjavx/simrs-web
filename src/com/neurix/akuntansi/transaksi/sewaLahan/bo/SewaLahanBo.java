package com.neurix.akuntansi.transaksi.sewaLahan.bo;

import com.neurix.akuntansi.transaksi.sewaLahan.model.ItAkunSewaLahanEntity;
import com.neurix.akuntansi.transaksi.sewaLahan.model.SewaLahan;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SewaLahanBo extends BaseMasterBo<SewaLahan> {
    public void saveDelete(SewaLahan bean) throws GeneralBOException;

    ItAkunSewaLahanEntity getPenyewaanLahanById(String sewaLahanId);

    void batalkanSewaLahan(SewaLahan bean) throws GeneralBOException;

    void postingJurnal(SewaLahan bean) throws GeneralBOException;
}
