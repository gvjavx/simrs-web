package com.neurix.common.bo;

import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 11/03/13
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public interface BaseMasterBo<T> extends GeneralBo {
    public void saveDelete(T bean) throws GeneralBOException;
    public void saveEdit(T bean) throws GeneralBOException;
    public T saveAdd(T bean) throws GeneralBOException;
    public List<T> getByCriteria(T searchBean) throws GeneralBOException;
    public List<T> getAll() throws GeneralBOException;
}
