package com.neurix.simrs.master.telemedic.bo;

import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kurir.model.Kurir;
import com.neurix.simrs.master.telemedic.model.RekeningTelemedic;

import java.util.List;

/**
 * @author gondok
 * Monday, 03/08/20 10:59
 */
public interface RekeningTelemedicBo {
    public List<RekeningTelemedic> getByCriteria(RekeningTelemedic bean) throws GeneralBOException;
    public void saveAdd(RekeningTelemedic rekeningTelemedic) throws GeneralBOException;
}
