package com.neurix.hris.master.profesi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.profesi.model.Profesi;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface ProfesiBo extends BaseMasterBo<Profesi>{
    public void saveDelete(Profesi bean) throws GeneralBOException;

    public List<Profesi> getComboProfesiWithCriteria(String query) throws GeneralBOException;

}
