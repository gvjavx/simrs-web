package com.neurix.hris.master.libur.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.libur.model.Libur;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface LiburBo extends BaseMasterBo<Libur>{
    public void saveDelete(Libur bean) throws GeneralBOException;

//    public List<Libur> getComboLiburWithCriteria(String query) throws GeneralBOException;

}
