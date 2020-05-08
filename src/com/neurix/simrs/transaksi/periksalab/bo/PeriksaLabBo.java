package com.neurix.simrs.transaksi.periksalab.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLabDetail;

import java.util.List;

/**
 * Created by Toshiba on 27/11/2019.
 */
public interface PeriksaLabBo {
    public List<PeriksaLab> getByCriteria(PeriksaLab bean) throws GeneralBOException;
    public List<PeriksaLab> getSearchLab(PeriksaLab bean) throws GeneralBOException;
    public void saveAdd(PeriksaLab bean) throws GeneralBOException;
    public void saveEdit(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException;
    public void saveAddWithParameter(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException;
    public void saveUpdateHasilLab(PeriksaLabDetail bean) throws GeneralBOException;
    public List<PeriksaLabDetail> getListParameterLab(PeriksaLabDetail bean) throws GeneralBOException;
    public CheckResponse saveDokterLab(PeriksaLab bean) throws GeneralBOException;

    public CheckResponse updateFlagApprovePeriksaLab (PeriksaLab bean) throws GeneralBOException;

    public PeriksaLab getTarifTotalPemeriksaan(String idLab, String idPeriksaan) throws GeneralBOException;
    public String getDivisiIdKodering(String idDetailCheckup, String tipeLab) throws GeneralBOException;
}
