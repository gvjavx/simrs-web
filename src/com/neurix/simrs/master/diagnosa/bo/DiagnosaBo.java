package com.neurix.simrs.master.diagnosa.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;

import java.util.List;

public interface DiagnosaBo {
    public List<Diagnosa> getByCriteria(Diagnosa bean)throws GeneralBOException;
}