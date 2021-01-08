package com.neurix.simrs.master.parameterketeranganobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;

import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;

import java.util.List;

public interface ParameterKeteranganObatBo {
    public List<ParameterKeteranganObat> getByCriteria (ParameterKeteranganObat bean) throws GeneralBOException;
    public List<ParameterKeteranganObat> getParameterKeterangan(String idJenis) throws GeneralBOException;
    public List<KeteranganObat> getParameterKeteranganWaktu(String idJenis) throws GeneralBOException;

    public void saveAdd(ParameterKeteranganObat bean) throws GeneralBOException;
    public void saveEdit(ParameterKeteranganObat bean) throws GeneralBOException;
    public void saveDelete(ParameterKeteranganObat bean) throws GeneralBOException;
}
