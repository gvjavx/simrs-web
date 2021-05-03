package com.neurix.simrs.master.keteranganobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobatsub.model.ImSimrsJenisPersediaanObatSubEntity;
import com.neurix.simrs.master.keteranganobat.model.ImSimrsKeteranganObatEntity;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;

import java.util.List;

public interface KeteranganObatBo {
    public List<ImSimrsKeteranganObatEntity> getListEntitiyByCriteria(KeteranganObat bean) throws GeneralBOException;
    public List<KeteranganObat> getListSearchByCriteria(KeteranganObat bean) throws GeneralBOException;
    public void saveAdd(KeteranganObat bean) throws GeneralBOException;
    public void saveEdit(KeteranganObat bean) throws GeneralBOException;
    public List<KeteranganObat> getKeteranganObat(String idParam) throws GeneralBOException;
    public List<ImSimrsParameterKeteranganObatEntity> getAllParameterKeterangan() throws GeneralBOException;
    public List<ImSimrsJenisPersediaanObatEntity> getAllJenisPersedian() throws GeneralBOException;
    public List<ImSimrsJenisPersediaanObatSubEntity> getAllJenisPersediaanSub() throws GeneralBOException;
    public List<KeteranganObat> getListKeteranganObatBySubJenis(String id);
    public String getIdSubJenisObat(String idObat);
}
