package com.neurix.simrs.master.jenispersediaanobatsub.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobatsub.model.ImSimrsJenisPersediaanObatSubEntity;
import com.neurix.simrs.master.jenispersediaanobatsub.model.JenisPersediaanObatSub;

import java.util.List;

public interface JenisPersediaanObatSubBo {
    public List<ImSimrsJenisPersediaanObatSubEntity> getListEntity(JenisPersediaanObatSub bean) throws GeneralBOException;
    public List<JenisPersediaanObatSub> getSearchByCriteria(JenisPersediaanObatSub bean) throws GeneralBOException;
    public List<JenisPersediaanObatSub> getAll() throws GeneralBOException;

    public void saveAdd(JenisPersediaanObatSub bean) throws GeneralBOException;
    public void saveEdit(JenisPersediaanObatSub bean) throws GeneralBOException;
    public void saveDelete(JenisPersediaanObatSub bean) throws GeneralBOException;
}
