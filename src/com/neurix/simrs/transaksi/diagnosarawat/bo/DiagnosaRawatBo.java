package com.neurix.simrs.transaksi.diagnosarawat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;

import java.util.List;

public interface DiagnosaRawatBo {
    public List<DiagnosaRawat> getByCriteria(DiagnosaRawat bean) throws GeneralBOException;
    public List<ItSimrsDiagnosaRawatEntity> getListEntityDiagnosaRawat(DiagnosaRawat bean) throws GeneralBOException;
    public void saveAdd(DiagnosaRawat bean) throws GeneralBOException;

    public void saveEdit(DiagnosaRawat bean) throws GeneralBOException;

}
