package com.neurix.authorization.company.bo;

import com.neurix.authorization.company.model.Area;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public interface AreaBo extends BaseMasterBo<Area> {
    public Area getAreaById(String areaId, String flag) throws GeneralBOException;
    public List<Area> getComboAreaWithCriteria(String query) throws GeneralBOException;
}