package com.neurix.simrs.master.keteranganobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.keteranganobat.model.ImSimrsKeteranganObatEntity;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;

import java.util.List;

public interface KeteranganObatBo {
    public List<ImSimrsKeteranganObatEntity> getListEntitiyByCriteria(KeteranganObat bean) throws GeneralBOException;
    public List<KeteranganObat> getListSearchByCriteria(KeteranganObat bean) throws GeneralBOException;
}
