package com.neurix.authorization.company.bo;

import com.neurix.authorization.company.model.Company;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 18/01/13
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyBo extends BaseMasterBo<Company> {
    public Company getById(String companyId) throws GeneralBOException;
}
