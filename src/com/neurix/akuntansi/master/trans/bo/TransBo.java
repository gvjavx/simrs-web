package com.neurix.akuntansi.master.trans.bo;

import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface TransBo extends BaseMasterBo<Trans> {
    public void saveDelete(Trans bean) throws GeneralBOException;
}
