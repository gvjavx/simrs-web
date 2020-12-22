package com.neurix.simrs.master.parameterketeranganobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;
import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;

import java.util.List;

public interface ParameterKeteranganObatBo {
    public List<ImSimrsParameterKeteranganObatEntity> getListEntitiyByCriteria(ParameterKeteranganObat bean) throws GeneralBOException;
    public List<ParameterKeteranganObat> getListSearchByCriteria(ParameterKeteranganObat bean) throws GeneralBOException;
    public List<ParameterKeteranganObat> getParameterKeterangan(String idJenis) throws GeneralBOException;
    public List<KeteranganObat> getParameterKeteranganWaktu(String idJenis) throws GeneralBOException;
}
