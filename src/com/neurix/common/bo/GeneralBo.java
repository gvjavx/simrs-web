package com.neurix.common.bo;

import com.neurix.common.exception.GeneralBOException;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 28/02/13
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public interface GeneralBo {
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException;
}
