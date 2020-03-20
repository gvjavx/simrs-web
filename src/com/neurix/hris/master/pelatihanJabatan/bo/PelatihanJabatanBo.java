package com.neurix.hris.master.pelatihanJabatan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.pelatihanJabatan.model.PelatihanJabatan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PelatihanJabatanBo extends BaseMasterBo<PelatihanJabatan>{
    public void saveDelete(PelatihanJabatan bean) throws GeneralBOException;
    public List<PelatihanJabatan> getComboKelompokWithCriteria(String query) throws GeneralBOException;

}
