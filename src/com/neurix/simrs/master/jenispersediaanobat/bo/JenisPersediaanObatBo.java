package com.neurix.simrs.master.jenispersediaanobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobat.model.JenisPersediaanObat;

import java.util.List;

public interface JenisPersediaanObatBo {
    public List<ImSimrsJenisPersediaanObatEntity> getListEntity(JenisPersediaanObat bean) throws GeneralBOException;
    public List<JenisPersediaanObat> getSearchByCriteria(JenisPersediaanObat bean) throws GeneralBOException;
    public List<JenisPersediaanObat> getAll() throws GeneralBOException;
    public List<JenisPersediaanObat> getListJenisPersediaanObat(String id) throws GeneralBOException;


    public void saveAdd(JenisPersediaanObat bean) throws GeneralBOException;
    public void saveEdit(JenisPersediaanObat bean) throws GeneralBOException;
    public void saveDelete(JenisPersediaanObat bean) throws GeneralBOException;
}
