package com.neurix.hris.master.golongan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.golongan.model.Golongan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface GolonganBo extends BaseMasterBo<Golongan>{
    public void saveDelete(Golongan bean) throws GeneralBOException;

    public List<Golongan> getComboGolonganWithCriteria(String query) throws GeneralBOException;
    public List<Golongan> getRangeMasaGol(String golPen) throws GeneralBOException;

}
