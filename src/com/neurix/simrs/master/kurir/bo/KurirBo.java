package com.neurix.simrs.master.kurir.bo;

import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kurir.model.Kurir;

import java.util.List;

/**
 * @author gondok
 * Tuesday, 16/06/20 10:46
 */
public interface KurirBo extends GeneralBo {
    public List<Kurir> getByCriteria(Kurir bean) throws GeneralBOException;
    public void saveAdd(Kurir bean) throws GeneralBOException;
    public void saveEdit(Kurir bean) throws GeneralBOException;
    public Boolean isUserKurirById(String userId, String password) throws GeneralBOException;
}
