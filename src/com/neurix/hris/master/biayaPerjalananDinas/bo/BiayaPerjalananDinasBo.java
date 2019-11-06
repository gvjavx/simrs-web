package com.neurix.hris.master.biayaPerjalananDinas.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biayaPerjalananDinas.model.BiayaPerjalananDinas;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface BiayaPerjalananDinasBo extends BaseMasterBo<BiayaPerjalananDinas>{
    public void saveDelete(BiayaPerjalananDinas bean) throws GeneralBOException;

    public List<BiayaPerjalananDinas> getComboBiayaWithCriteria(String query) throws GeneralBOException;

}
