package com.neurix.akuntansi.master.master.bo;

import com.neurix.akuntansi.master.master.model.Master;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface MasterBo extends BaseMasterBo<Master> {

    List<Master> typeaheadMaster(String coa) throws GeneralBOException;
}
