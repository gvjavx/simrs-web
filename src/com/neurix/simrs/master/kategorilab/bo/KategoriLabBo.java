package com.neurix.simrs.master.kategorilab.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;
import com.neurix.simrs.master.kategorilab.model.KategoriLab;

import java.util.List;

public interface KategoriLabBo extends BaseMasterBo<KategoriLab>{
    public List<KategoriLab> getByCriteria(KategoriLab bean) throws GeneralBOException;
    public List<KategoriLab> getKategoriLab(String idLab, String branchId) throws GeneralBOException;
    public ImSimrsKategoriLabEntity getDataLab(String idKategori) throws GeneralBOException;
}