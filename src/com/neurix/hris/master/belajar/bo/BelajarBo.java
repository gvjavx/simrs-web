package com.neurix.hris.master.belajar.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.belajar.model.Belajar;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface BelajarBo extends BaseMasterBo<Belajar>{
    public void saveDelete(Belajar bean) throws GeneralBOException;

    public List<Belajar> getComboBelajarWithCriteria(String query) throws GeneralBOException;

}
