package com.neurix.hris.master.ijin.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.ijin.model.Ijin;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface IjinBo extends BaseMasterBo<Ijin> {
    public void saveDelete(Ijin bean) throws GeneralBOException;

    public List<Ijin> getComboIjinWithCriteria(String query) throws GeneralBOException;
    public List<Ijin> getComboLamaIjinWithCriteria(String query) throws GeneralBOException;
    public List<Ijin> getComboIjinIdWithCriteria(String query) throws GeneralBOException;

    List<Ijin> getComboIjinIdWithKelamin(String nip) throws GeneralBOException;
}
