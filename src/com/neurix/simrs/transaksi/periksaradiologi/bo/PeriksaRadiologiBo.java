package com.neurix.simrs.transaksi.periksaradiologi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.periksaradiologi.action.PeriksaRadiologiAction;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import com.neurix.simrs.transaksi.periksaradiologi.model.PeriksaRadiologi;

import java.util.List;

/**
 * Created by Toshiba on 29/11/2019.
 */
public interface PeriksaRadiologiBo {
    public List<PeriksaRadiologi> getListPeriksaRadioLogiByCriteria(PeriksaRadiologi bean) throws GeneralBOException;
    public void saveAdd(PeriksaRadiologi bean) throws GeneralBOException;
    public CheckResponse saveEdit(PeriksaRadiologi bean) throws GeneralBOException;
    public CheckResponse saveDokterRadiologi(PeriksaRadiologi bean) throws GeneralBOException;
    public ItSimrsPeriksaRadiologiEntity getEntityPeriksaRadiologi(String id) throws GeneralBOException;

}
