package com.neurix.hris.master.notif.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.group.model.Group;
import com.neurix.hris.master.notif.model.Notif;

import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public interface NotifBo extends BaseMasterBo<Notif> {
    public List<Notif> getCount() throws GeneralBOException;
    public void getReadNotif(Notif bean) throws GeneralBOException;
}
