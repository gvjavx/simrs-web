package com.neurix.simrs.master.kandunganObat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kandunganObat.model.KandunganObat;

import java.util.List;

public interface KandunganObatBo {
    public List<KandunganObat> getByCriteria(KandunganObat bean) throws GeneralBOException;
    public void saveEdit(KandunganObat bean) throws GeneralBOException;
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException;
    public KandunganObat saveAdd(KandunganObat bean) throws GeneralBOException;
}
