package com.neurix.simrs.transaksi.diagnosarawat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;

import java.util.List;

public interface DiagnosaRawatBo {
    public List<DiagnosaRawat> getByCriteria(DiagnosaRawat bean) throws GeneralBOException;
    public void saveAdd(DiagnosaRawat bean) throws GeneralBOException;

}
