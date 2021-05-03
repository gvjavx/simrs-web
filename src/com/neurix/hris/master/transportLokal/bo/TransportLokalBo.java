package com.neurix.hris.master.transportLokal.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.transportLokal.model.TransportLokal;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface TransportLokalBo extends BaseMasterBo<TransportLokal>{
    public void saveDelete(TransportLokal bean) throws GeneralBOException;

    public List<TransportLokal> getComboTransportLokalWithCriteria(String query) throws GeneralBOException;

}
