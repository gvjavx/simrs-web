package com.neurix.simrs.master.tindakan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.poi.hssf.record.formula.functions.T;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface TindakanBo{
    public CrudResponse saveAdd(List<Tindakan> bean) throws GeneralBOException;
    public CrudResponse saveEdit(Tindakan bean) throws GeneralBOException;
    public List<Tindakan> getByCriteria(Tindakan bean) throws GeneralBOException;
    public List<Tindakan> getComboBoxTindakan(Tindakan bean) throws GeneralBOException;
    public ImSimrsTindakanEntity getEntityTindakanById(String idTindakan) throws GeneralBOException;

    public List<Tindakan> getDataTindakan(Tindakan bean) throws GeneralBOException;
}
