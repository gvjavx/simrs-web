package com.neurix.simrs.master.dietgizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dietgizi.model.JenisDiet;

import java.util.List;

public interface JenisDietBo {
    public List<JenisDiet> getByCriteria(JenisDiet searchBean) throws GeneralBOException;
    public List<JenisDiet> getJenisDietByCriteria(JenisDiet searchBean) throws GeneralBOException;

    public void saveAdd(JenisDiet bean) throws GeneralBOException;
    public void saveEdit(JenisDiet bean) throws GeneralBOException;
    public void saveDelete(JenisDiet bean) throws GeneralBOException;

}