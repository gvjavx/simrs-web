package com.neurix.hris.master.cuti.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.cuti.model.Cuti;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface CutiBo extends BaseMasterBo<Cuti> {
    public void saveDelete(Cuti bean) throws GeneralBOException;
    public Cuti findCutiByIdcuti(String id) throws GeneralBOException;
    public List<Cuti> getComboCutiWithCriteria(String query) throws GeneralBOException;
    public List<Cuti> getComboCutiTipeWithCriteria(String query) throws GeneralBOException;

}
