package com.neurix.hris.master.positionBagian.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.department.model.Department;
import com.neurix.hris.master.positionBagian.model.PositionBagian;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PositionBagianBo extends BaseMasterBo<PositionBagian> {
    public void saveDelete(PositionBagian bean) throws GeneralBOException;

    public List<PositionBagian> getComboKelompokWithCriteria(String query) throws GeneralBOException;

    List<PositionBagian> getBagian(PositionBagian bean);

    PositionBagian getBagianById(String id, String flag);

    public List<PositionBagian> searchPositionBagian(String divisiId) throws GeneralBOException;

    public List<PositionBagian> getDataDevisiId(PositionBagian bean) throws GeneralBOException;

    public List<Department> getHead(PositionBagian positionBagian) throws GeneralBOException;

    public PositionBagian getPositionBagianById(String id) throws GeneralBOException;
}

