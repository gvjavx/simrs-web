package com.neurix.simrs.master.dietgizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dietgizi.model.DietGizi;
import com.neurix.simrs.master.dietgizi.model.JenisDiet;

import java.util.List;

public interface DietGiziBo {

    public List<DietGizi> getByCriteria(DietGizi bean) throws GeneralBOException;
    public List<JenisDiet> getJenisDietByCiteria(JenisDiet bean) throws GeneralBOException;
}
