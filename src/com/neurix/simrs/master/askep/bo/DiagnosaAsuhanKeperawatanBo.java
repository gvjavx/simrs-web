package com.neurix.simrs.master.askep.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.askep.model.DiagnosaAsuhanKeperawatan;

import java.util.List;

public interface DiagnosaAsuhanKeperawatanBo {
    public List<DiagnosaAsuhanKeperawatan> getByCriteria(DiagnosaAsuhanKeperawatan bean) throws GeneralBOException;
    public List<DiagnosaAsuhanKeperawatan> getListDiagnosa(String key, String tipe) throws GeneralBOException;
}
