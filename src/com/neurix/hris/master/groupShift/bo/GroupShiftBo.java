package com.neurix.hris.master.groupShift.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.groupShift.model.GroupShift;

import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public interface GroupShiftBo extends BaseMasterBo<GroupShift> {

    List<GroupShift> getByCriteriaShift(GroupShift searchBean) throws GeneralBOException;
}
