package com.neurix.simrs.master.finger.bo;

import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.finger.model.FingerPrint;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface FingerPrintBo extends GeneralBo {
    public List<FingerPrint> getByCriteria(FingerPrint bean) throws GeneralBOException;
    public void saveAdd(FingerPrint pasien) throws GeneralBOException;
    public void saveEdit(FingerPrint pasien) throws GeneralBOException;
    public void saveDelete(FingerPrint bean) throws GeneralBOException;
    public List<FingerPrint> getListComboFingerPrint(String query) throws GeneralBOException;
}
