package com.neurix.simrs.transaksi.rawatinap.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.SkorRanap;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface RawatInapBo {
    public List<RawatInap> getSearchRawatInap(RawatInap bean) throws GeneralBOException;
    public List<ItSimrsSkorRanapEntity> getListSkorRanap(SkorRanap bean) throws GeneralBOException;
}
