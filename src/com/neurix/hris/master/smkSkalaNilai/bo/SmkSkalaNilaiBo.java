package com.neurix.hris.master.smkSkalaNilai.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkSkalaNilai.model.SmkSkalaNilai;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkSkalaNilaiBo extends BaseMasterBo<SmkSkalaNilai>{
    public void saveDelete(SmkSkalaNilai bean) throws GeneralBOException;
}
