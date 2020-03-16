package com.neurix.hris.master.carrerPath.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.carrerPath.model.CarrerPath;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface CarrerPathBo extends BaseMasterBo<CarrerPath>{
    public void saveDelete(CarrerPath bean) throws GeneralBOException;
}
