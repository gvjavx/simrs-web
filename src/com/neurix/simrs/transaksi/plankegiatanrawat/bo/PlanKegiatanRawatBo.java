package com.neurix.simrs.transaksi.plankegiatanrawat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.moncairan.model.MonCairan;
import com.neurix.simrs.transaksi.monpemberianobat.model.MonPemberianObat;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.PlanKegiatanRawat;

import java.util.List;

/**
 * Created by reza on 06/03/20.
 */
public interface PlanKegiatanRawatBo {
    public List<PlanKegiatanRawat> getSearchByCritria(PlanKegiatanRawat bean) throws GeneralBOException;
    public List<PlanKegiatanRawat> getListKegiatanRawat(PlanKegiatanRawat bean) throws GeneralBOException;
    public List<PlanKegiatanRawat> getListPlanKegitatanRawat(PlanKegiatanRawat bean) throws GeneralBOException;
    public void saveAddPlanKegiatan(PlanKegiatanRawat bean, List<MonVitalSign> vitalSignList, List<MonCairan> cairanList, List<MonPemberianObat> pemberianObatList) throws GeneralBOException;
}
