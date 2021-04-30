package com.neurix.hris.master.dashboard.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.dashboard.model.Dashboard;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface DashboardBo extends BaseMasterBo<Dashboard>{
    public void saveDelete(Dashboard bean) throws GeneralBOException;

    public List<Dashboard> getComboGolonganWithCriteria(String query) throws GeneralBOException;
    public List<Dashboard> getDataEdit(String id, String periode) throws GeneralBOException;

    // save Data dari session
    public void saveData() throws GeneralBOException;

    // print report
    public List<Dashboard> printReport(String id, String periode) throws GeneralBOException;
}
