package com.neurix.simrs.transaksi.plankegiatanrawat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.PlanKegiatanRawat;

import java.util.List;

/**
 * Created by reza on 06/03/20.
 */
public interface PlanKegiatanRawatBo {
    public List<PlanKegiatanRawat> getSearchByCritria(PlanKegiatanRawat bean) throws GeneralBOException;
}
