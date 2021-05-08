package com.neurix.hris.master.department.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.department.model.Department;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface DepartmentBo extends BaseMasterBo<Department>{
    public void saveDelete(Department bean) throws GeneralBOException;

    public List<Department> getComboDepartmentWithCriteria(String query) throws GeneralBOException;
    public List<Department> getListDepartmentByDepartmenId(String id) throws GeneralBOException;

}
