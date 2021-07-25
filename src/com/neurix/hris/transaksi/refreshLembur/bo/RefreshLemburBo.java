package com.neurix.hris.transaksi.refreshLembur.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.refreshLembur.model.RefreshLembur;

import java.sql.Date;
import java.util.List;

/**
 * Created by Aji Noor on 22/07/2021
 */
public interface RefreshLemburBo extends BaseMasterBo<RefreshLembur> {
    public String refreshAbsensiLembur(List<Lembur> lemburList, Date tanggal, Boolean chance) throws GeneralBOException;
    public List<RefreshLembur> getPerGroupLembur(RefreshLembur searchBean) throws GeneralBOException;
    public List<RefreshLembur> getByCriteriaByGroup(RefreshLembur searchBean) throws GeneralBOException;
    public void approveRefresh(RefreshLembur bean) throws GeneralBOException;
    public List<PersonilPosition> getPersonOnPosition (String positionId, String branchId) throws GeneralBOException;

    }
