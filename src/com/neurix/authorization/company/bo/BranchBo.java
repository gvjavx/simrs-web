package com.neurix.authorization.company.bo;

import com.neurix.authorization.company.model.Branch;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */
public interface BranchBo extends BaseMasterBo<Branch> {

    public Branch getBranchById(String branchId, String flag) throws GeneralBOException;
    public List<Branch> getComboBranchWithCriteria(String query) throws GeneralBOException;
    public List<Branch> findAllBranch() throws GeneralBOException;

    List<Branch> getBranchList() throws GeneralBOException;

    public List<Branch> getByCriteria(Branch searchBranch) throws GeneralBOException;

}