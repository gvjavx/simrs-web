package com.neurix.simrs.master.kelasruangan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import com.neurix.simrs.master.ruangan.model.Ruangan;

import java.util.List;

public interface KelasRuanganBo  extends GeneralBo {
    public List<KelasRuangan> getByCriteria(KelasRuangan bean)throws GeneralBOException;
    public void saveAdd(KelasRuangan kelasRuangan) throws GeneralBOException;
    public void saveEdit(KelasRuangan kelasRuangan) throws GeneralBOException;
    public void saveDelete(KelasRuangan kelasRuangan) throws GeneralBOException;
    public ImSimrsKelasRuanganEntity getKelasRuanganById(String id) throws GeneralBOException;
}