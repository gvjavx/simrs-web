package com.neurix.hris.master.smkSkalaNilaiItem.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkSkalaNilai.model.SmkSkalaNilai;
import com.neurix.hris.master.smkSkalaNilaiItem.model.SmkSkalaNilaiItem;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkSkalaNilaiItemBo extends BaseMasterBo<SmkSkalaNilaiItem>{
    public void saveDelete(SmkSkalaNilaiItem bean) throws GeneralBOException;
}
