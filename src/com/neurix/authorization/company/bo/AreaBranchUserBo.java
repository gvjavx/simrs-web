package com.neurix.authorization.company.bo;

import com.neurix.authorization.company.model.AreaBranchUser;
import com.neurix.authorization.user.model.User;
import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */
public interface AreaBranchUserBo extends GeneralBo {
    public List<AreaBranchUser> getByCriteria(AreaBranchUser searchAreaBranchUser) throws GeneralBOException;
    public List<User> getDefaultUser() throws GeneralBOException;
    public AreaBranchUser saveAdd(AreaBranchUser areaBranchUser) throws GeneralBOException;
    public void saveDelete(AreaBranchUser areaBranchUser) throws GeneralBOException;
    public void saveEdit(AreaBranchUser areaBranchUser) throws GeneralBOException;
}
