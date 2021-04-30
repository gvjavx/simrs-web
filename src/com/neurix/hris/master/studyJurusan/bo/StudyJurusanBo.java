package com.neurix.hris.master.studyJurusan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.studyJurusan.model.StudyJurusan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface StudyJurusanBo extends BaseMasterBo<StudyJurusan>{
    public void saveDelete(StudyJurusan bean) throws GeneralBOException;

    public List<StudyJurusan> getComboJurusanWithCriteria(String query) throws GeneralBOException;

}
