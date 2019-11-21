package com.neurix.simrs.transaksi.rawatinap.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface RawatInapBo {
    public List<RawatInap> getListEntityRawatInap(RawatInap bean) throws GeneralBOException;
}
