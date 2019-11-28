package com.neurix.simrs.transaksi.periksalab.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;

import java.util.List;

/**
 * Created by Toshiba on 27/11/2019.
 */
public interface PeriksaLabBo {
    public List<PeriksaLab> getByCriteria(PeriksaLab bean) throws GeneralBOException;
    public void saveAdd(PeriksaLab bean) throws GeneralBOException;
    public void saveEdit(PeriksaLab bean) throws GeneralBOException;
    public void saveAddWithParameter(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException;
}
