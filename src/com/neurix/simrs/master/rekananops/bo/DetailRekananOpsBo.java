package com.neurix.simrs.master.rekananops.bo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;

import java.util.List;

public interface DetailRekananOpsBo {
    public List<DetailRekananOps> getSearchByCriteria (DetailRekananOps bean) throws GeneralBOException;
    public void saveAdd(DetailRekananOps bean) throws GeneralBOException;
    public void saveEdit (DetailRekananOps bean) throws  GeneralBOException;
    public void saveDelete (DetailRekananOps bean) throws  GeneralBOException;
    public List<DetailRekananOps> getParentDataById(String id) throws GeneralBOException;
    public List<DetailRekananOps> getDetailDataByIdParent(String idParent) throws GeneralBOException;
    public Pelayanan getPelayananByIdItem(String idItem) throws GeneralBOException;
    public List<Pelayanan> getListPelayananByBranchId(String branchId) throws GeneralBOException;
    public List<Tindakan> getListTindakanByPelayanan(String idPelayanan) throws GeneralBOException;
    public Tindakan getTindakanById(String idTindakan) throws GeneralBOException;
    public void saveAddDetail(DetailRekananOps bean) throws GeneralBOException;
    public void saveEditDetail(DetailRekananOps bean) throws GeneralBOException;
    public void saveDeleteDetail(DetailRekananOps bean) throws GeneralBOException;
}
