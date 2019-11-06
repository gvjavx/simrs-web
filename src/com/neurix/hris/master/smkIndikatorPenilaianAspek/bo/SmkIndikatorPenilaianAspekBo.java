package com.neurix.hris.master.smkIndikatorPenilaianAspek.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkIndikatorPenilaianAspekBo extends BaseMasterBo<SmkIndikatorPenilaianAspek>{
    public void saveDelete(SmkIndikatorPenilaianAspek bean) throws GeneralBOException;
}
