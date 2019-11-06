package com.neurix.hris.master.tipeAspek.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.tipeAspek.model.TipeAspek;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface TipeAspekBo extends BaseMasterBo<TipeAspek>{
    public void saveDelete(TipeAspek bean) throws GeneralBOException;

    public List<TipeAspek> getComboTipeAspekWithCriteria(String query) throws GeneralBOException;

}
