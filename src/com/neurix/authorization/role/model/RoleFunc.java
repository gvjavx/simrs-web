package com.neurix.authorization.role.model;

import com.neurix.authorization.function.model.Functions;
import com.neurix.authorization.function.model.Menus;
import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ferdi on 26/01/2015.
 */
public class RoleFunc extends BaseModel implements Serializable {
    private Long roleId;
    private Long funcId;
    private String roleName;
    private String stRoleId;
    private String stFuncId;
    private List<Menus> listOfMenu;
    private List<Functions> listOfAllFunctions;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStRoleId() {
        return stRoleId;
    }

    public void setStRoleId(String stRoleId) {
        this.stRoleId = stRoleId;
    }

    public String getStFuncId() {
        return stFuncId;
    }

    public void setStFuncId(String stFuncId) {
        this.stFuncId = stFuncId;
    }

    public List<Menus> getListOfMenu() {
        return listOfMenu;
    }

    public void setListOfMenu(List<Menus> listOfMenu) {
        this.listOfMenu = listOfMenu;
    }

    public List<Functions> getListOfAllFunctions() {
        return listOfAllFunctions;
    }

    public void setListOfAllFunctions(List<Functions> listOfAllFunctions) {
        this.listOfAllFunctions = listOfAllFunctions;
    }
}
