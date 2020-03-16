package com.neurix.simrs.master.tindakan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakan.model.Tindakan;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface TindakanBo {
    public List<Tindakan> getByCriteria(Tindakan bean) throws GeneralBOException;
    public List<Tindakan> getComboBoxTindakan(Tindakan bean) throws GeneralBOException;
}
