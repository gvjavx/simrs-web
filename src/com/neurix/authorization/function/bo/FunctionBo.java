package com.neurix.authorization.function.bo;

import com.neurix.authorization.function.model.Functions;
import com.neurix.authorization.role.model.Roles;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 18/01/13
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public interface FunctionBo extends BaseMasterBo<Functions> {

    public List<Roles> getRolesByFunctionURL(String url) throws GeneralBOException;
    public Functions getFunctionById(long functionId, String flag) throws GeneralBOException;
    public Long getParentLevel(Long parentId) throws GeneralBOException;
    public List<Functions> getComboParent() throws GeneralBOException;
    public List<Functions> getComboParentWithCriteria(String query) throws GeneralBOException;
    public List<Functions> getComboFunctionWithCriteria(String query) throws GeneralBOException;

}
