package com.neurix.hris.master.smkKategoriAspek.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkKategoriAspek.model.SmkKategoriAspek;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkKategoriAspekBo extends BaseMasterBo<SmkKategoriAspek>{
    public void saveDelete(SmkKategoriAspek bean) throws GeneralBOException;
    public void saveEdit(SmkKategoriAspek bean, List<SmkIndikatorPenilaianAspek> bean2) throws GeneralBOException;
    public String getIdIndikator() throws GeneralBOException;
    public SmkKategoriAspek saveAdd(SmkKategoriAspek bean) throws GeneralBOException ;

    public List<SmkKategoriAspek> getComboSmkKategoriWithCriteria(String query) throws GeneralBOException;

}
