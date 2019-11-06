package com.neurix.authorization.role.bo;

import com.neurix.authorization.role.model.Roles;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 18/01/13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public interface RoleBo extends BaseMasterBo<Roles> {

    public Roles getRoleById(long roleId, String flag) throws GeneralBOException;
    public List<Roles> getComboRoleWithCriteria(String query) throws GeneralBOException;

}