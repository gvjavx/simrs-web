package com.neurix.hris.master.smkCheckList.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkCheckList.model.SmkCheckList;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.SmkIndikatorPenilaianCheckList;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkCheckListBo extends BaseMasterBo<SmkCheckList>{
    public void saveDelete(SmkCheckList bean) throws GeneralBOException;
    public void saveEdit(SmkCheckList bean, List<SmkIndikatorPenilaianCheckList> bean2) throws GeneralBOException;
    public SmkCheckList saveAdd(SmkCheckList bean) throws GeneralBOException ;

    public List<SmkCheckList> getComboSmkCheckListWithCriteria(String query) throws GeneralBOException;

}
