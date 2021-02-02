package com.neurix.simrs.master.pelayanan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pelayanan.model.HeaderPelayanan;
import com.neurix.simrs.master.pelayanan.model.ImSimrsHeaderPelayananEntity;

import java.util.List;

public interface HeaderPelayananBo {
    public List<HeaderPelayanan> getByCriteria(HeaderPelayanan bean) throws GeneralBOException;
    public List<ImSimrsHeaderPelayananEntity> getListEntity(HeaderPelayanan bean) throws GeneralBOException;
    public void saveAdd(HeaderPelayanan bean) throws GeneralBOException;
    public void saveEdit(HeaderPelayanan bean) throws GeneralBOException;
    public void saveDelete(HeaderPelayanan bean) throws GeneralBOException;
}
