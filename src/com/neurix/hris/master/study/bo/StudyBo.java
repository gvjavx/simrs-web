package com.neurix.hris.master.study.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.study.model.Study;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface StudyBo extends BaseMasterBo<Study>{
    public void saveDelete(Study bean) throws GeneralBOException;

    public List<Study> getComboStudyWithCriteria(String query) throws GeneralBOException;

}
