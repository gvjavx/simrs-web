package com.neurix.hris.master.updateGolongan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.updateGolongan.model.UpdateGolongan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface UpdateGolonganBo extends BaseMasterBo<UpdateGolongan>{
    public void saveDelete(UpdateGolongan bean) throws GeneralBOException;

    public List<UpdateGolongan> getComboGolonganWithCriteria(String query) throws GeneralBOException;
    public List<UpdateGolongan> getDataEdit(String id, String periode) throws GeneralBOException;

    // save Data dari session
    public void saveData() throws GeneralBOException;

    // print report
    public List<UpdateGolongan> printReport(String id, String periode) throws GeneralBOException;

    // Approve
    public void saveApprove(String updateGolonganId) throws GeneralBOException;
}
