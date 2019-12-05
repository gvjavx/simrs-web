package com.neurix.simrs.master.obat.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.model.Obat;

import java.util.List;

public interface ObatBo{
    public List<Obat> getByCriteria(Obat searchBean) throws GeneralBOException;
    public List<Obat> getListObatByJenisObat(String idObat, String branchId) throws GeneralBOException;
    public List<Obat> getJenisObat(Obat bean) throws GeneralBOException;
    public void saveAdd(Obat bean, List<String> idJenisObats) throws GeneralBOException;
    public void saveEdit(Obat bean, List<String> idJenisObats) throws GeneralBOException;
}