package com.neurix.hris.master.positionBagian.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.department.model.Department;
import com.neurix.hris.master.positionBagian.model.positionBagian;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PositionBagianBo extends BaseMasterBo<positionBagian> {
    public void saveDelete(positionBagian bean) throws GeneralBOException;

    public List<positionBagian> getComboKelompokWithCriteria(String query) throws GeneralBOException;

    List<positionBagian> getBagian(positionBagian bean);

    positionBagian getBagianById(String id, String flag);

    public List<positionBagian> searchPositionBagian(String divisiId) throws GeneralBOException;

    public List<positionBagian> getDataDevisiId(positionBagian bean) throws GeneralBOException;

    public List<Department> getHead(positionBagian positionBagian) throws GeneralBOException;
}

