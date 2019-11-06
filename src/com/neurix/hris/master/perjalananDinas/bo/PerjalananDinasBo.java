package com.neurix.hris.master.perjalananDinas.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.perjalananDinas.model.PerjalananDinas;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PerjalananDinasBo extends BaseMasterBo<PerjalananDinas>{
    public void saveDelete(PerjalananDinas bean) throws GeneralBOException;

    public List<PerjalananDinas> getComboPerjalananDinasWithCriteria(String query) throws GeneralBOException;

}
