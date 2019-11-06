package com.neurix.hris.master.smkIndikatorPenilaianCheckList.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.SmkIndikatorPenilaianCheckList;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkIndikatorPenilaianCheckListBo extends BaseMasterBo<SmkIndikatorPenilaianCheckList>{
    public void saveDelete(SmkIndikatorPenilaianCheckList bean) throws GeneralBOException;
}
