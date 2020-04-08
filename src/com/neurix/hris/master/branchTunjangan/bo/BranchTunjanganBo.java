package com.neurix.hris.master.branchTunjangan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.branchTunjangan.model.BranchTunjangan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface BranchTunjanganBo extends BaseMasterBo<BranchTunjangan>{
    public void saveDelete(BranchTunjangan bean) throws GeneralBOException;

    public List<BranchTunjangan> getComboBranchTunjanganWithCriteria(String query) throws GeneralBOException;

}
