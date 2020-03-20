package com.neurix.hris.master.kelompokPosition.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.kelompokPosition.model.KelompokPosition;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface KelompokPositionBo extends BaseMasterBo<KelompokPosition>{
    public void saveDelete(KelompokPosition bean) throws GeneralBOException;

    public List<KelompokPosition> getComboKelompokWithCriteria(String query) throws GeneralBOException;

}
