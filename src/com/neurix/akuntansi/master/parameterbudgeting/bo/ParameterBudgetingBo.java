package com.neurix.akuntansi.master.parameterbudgeting.bo;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.*;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

public interface ParameterBudgetingBo {
    public List<ImAkunParameterBudgetingEntity> getListEntity(ParameterBudgeting bean) throws GeneralBOException;
    public List<ParameterBudgeting> getSearchByCriteria(ParameterBudgeting bean) throws GeneralBOException;
    public void saveAdd(ParameterBudgeting bean) throws GeneralBOException;
    public void saveEdit(ParameterBudgeting bean) throws GeneralBOException;
    public List<ImAkunJenisBudgetingEntity> getAllJenisBudgeting();
    public List<ImAkunKategoriParameterBudgetingEntity> getAllKategoriParameterBudgeting();
    public List<ImPosition> getAllPosition();
    public List<ImMasterEntity> getAllMaster();
    public List<ImAkunParameterBudgetingRekeningEntity> getAllParameterRekening();
    public List<KodeRekening> getListKodeRekeningByTipeCoa(String tipeCoa);
}
