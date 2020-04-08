package com.neurix.hris.master.statusRekruitment.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.statusRekruitment.model.StatusRekruitment;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface StatusRekruitmentBo extends BaseMasterBo<StatusRekruitment>{
    public void saveDelete(StatusRekruitment bean) throws GeneralBOException;

    public List<StatusRekruitment> getComboStatusRekruitmentWithCriteria(String query) throws GeneralBOException;

}
