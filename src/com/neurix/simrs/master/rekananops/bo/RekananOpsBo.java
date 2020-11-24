package com.neurix.simrs.master.rekananops.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.transaksi.CrudResponse;

import java.util.List;

public interface RekananOpsBo {
    public List<RekananOps> getByCriteria(RekananOps bean)throws GeneralBOException;
    public CrudResponse saveAdd(RekananOps bean) throws GeneralBOException;
    public CrudResponse saveEdit(RekananOps bean) throws GeneralBOException;
    public CrudResponse saveDelete(RekananOps bean) throws GeneralBOException;
    public RekananOps getDetailRekananOps(String id, String branchId) throws GeneralBOException;
    public RekananOps getDetailRekananOpsByDetail(String id, String branchId) throws GeneralBOException;
    public List<RekananOps> getComboRekananOps(String branchId) throws GeneralBOException;
    public ImSimrsRekananOpsEntity getRekananEntityById(String id) throws GeneralBOException;
    public List<DetailRekananOps> getSearchByCriteria (RekananOps bean) throws GeneralBOException;
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException;

}
