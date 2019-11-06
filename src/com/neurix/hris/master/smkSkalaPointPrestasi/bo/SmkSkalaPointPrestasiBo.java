package com.neurix.hris.master.smkSkalaPointPrestasi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkSkalaPointPrestasi.model.SmkSkalaPointPrestasi;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkSkalaPointPrestasiBo extends BaseMasterBo<SmkSkalaPointPrestasi>{
    public void saveDelete(SmkSkalaPointPrestasi bean) throws GeneralBOException;
}
