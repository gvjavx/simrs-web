package com.neurix.hris.master.smkIndikatorKinerja.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorKinerja.model.SmkIndikatorKinerja;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkIndikatorKinerjaBo extends BaseMasterBo<SmkIndikatorKinerja>{
    public void saveDelete(SmkIndikatorKinerja bean) throws GeneralBOException;

    public List<SmkIndikatorKinerja> getComboSmkIndikatorKinerjaWithCriteria(String query) throws GeneralBOException;

}
