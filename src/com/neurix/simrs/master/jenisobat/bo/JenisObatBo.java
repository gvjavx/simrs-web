package com.neurix.simrs.master.jenisobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.ruangan.model.Ruangan;

import java.util.List;

public interface JenisObatBo{
    public List<JenisObat> getByCriteria(JenisObat searchBean) throws GeneralBOException;
    public void saveAdd(JenisObat bean) throws GeneralBOException;
    public void saveEdit(JenisObat bean) throws GeneralBOException;
    public void saveDelete(JenisObat bean) throws GeneralBOException;

}