package com.neurix.hris.master.tipeNotif.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.tipeNotif.model.TipeNotif;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface TipeNotifBo extends BaseMasterBo<TipeNotif>{
    public void saveDelete(TipeNotif bean) throws GeneralBOException;

    public List<TipeNotif> getComboTipeNotifWithCriteria(String query) throws GeneralBOException;

}
