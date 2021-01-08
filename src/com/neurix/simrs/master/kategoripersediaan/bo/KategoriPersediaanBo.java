package com.neurix.simrs.master.kategoripersediaan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoripersediaan.model.KategoriPersediaan;

import java.util.List;

/**
 * Created by reza on 30/09/20.
 */
public interface KategoriPersediaanBo {
    public List<KategoriPersediaan> getByCriteria (KategoriPersediaan bean) throws GeneralBOException;
    public void saveAdd(KategoriPersediaan bean) throws GeneralBOException;
    public void saveEdit(KategoriPersediaan bean) throws GeneralBOException;
    public void saveDelete(KategoriPersediaan bean) throws GeneralBOException;
}
