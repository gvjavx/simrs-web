package com.neurix.authorization.role.bo;

import com.neurix.authorization.function.model.Menus;
import com.neurix.authorization.role.model.RoleFunc;
import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by Ferdi on 26/01/2015.
 */
public interface RoleFuncBo extends GeneralBo {
    public List<RoleFunc> getByCriteria(RoleFunc searchRoleFunc) throws GeneralBOException;
    public List<Menus> getDefaultMenu() throws GeneralBOException;
    public RoleFunc saveAdd(RoleFunc roleFunc) throws GeneralBOException;
    public void saveDelete(RoleFunc roleFunc) throws GeneralBOException;
    public void saveEdit(RoleFunc roleFunc) throws GeneralBOException;
}
