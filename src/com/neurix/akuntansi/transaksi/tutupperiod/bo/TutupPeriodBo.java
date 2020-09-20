package com.neurix.akuntansi.transaksi.tutupperiod.bo;

import com.neurix.akuntansi.transaksi.tutupperiod.model.BatasTutupPeriod;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by reza on 18/03/20.
 */
public interface TutupPeriodBo {

    public List<ItSimrsBatasTutupPeriodEntity> getListEntityBatasTutupPeriode(BatasTutupPeriod bean) throws GeneralBOException;
    public void saveSettingPeriod(List<ItSimrsBatasTutupPeriodEntity> batasList) throws GeneralBOException;
    public void saveUpdateTutupPeriod(TutupPeriod bean) throws GeneralBOException;
    public void saveUpdateLockPeriod(TutupPeriod bean) throws GeneralBOException;
    public void saveUpdateLockPeriodKoreksi(TutupPeriod bean) throws GeneralBOException;
    public BatasTutupPeriod getLastBulanBerjalanSaldoAkhir(String tahun, String branchId) throws GeneralBOException;

}
