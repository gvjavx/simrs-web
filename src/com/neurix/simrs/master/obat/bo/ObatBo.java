package com.neurix.simrs.master.obat.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;

import java.util.List;

public interface ObatBo{
    public List<Obat> getByCriteria(Obat searchBean) throws GeneralBOException;
    public List<Obat> getListObatByJenisObat(String idObat, String branchId) throws GeneralBOException;
    public List<Obat> getJenisObat(Obat bean) throws GeneralBOException;
    public void saveAdd(Obat bean, List<String> idJenisObats) throws GeneralBOException;
    public void saveEdit(Obat bean, List<String> idJenisObats) throws GeneralBOException;

    public List<Obat> getListNamaObat(Obat bean) throws GeneralBOException;

    public CheckObatResponse checkObatStockLama(String idObat, String branchId) throws GeneralBOException;
    public CheckObatResponse checkFisikObat(Obat bean) throws GeneralBOException;
    public CheckObatResponse checkFisikObatByIdPabrik(Obat bean) throws GeneralBOException;
    public List<Obat> sortedListObat(List<Obat> obatList) throws GeneralBOException;

    public List<Obat> getEntityObatByCriteria(Obat bean) throws GeneralBOException;
    public List<Obat> getListObatGroup(Obat bean) throws GeneralBOException;
    public List<ImSimrsObatEntity> getListEntityObat(Obat bean) throws GeneralBOException;
    public ImSimrsObatEntity getObatByIdBarang(String idBarang) throws GeneralBOException;
    public List<Obat> getListHargaObat(Obat bean) throws GeneralBOException;
}