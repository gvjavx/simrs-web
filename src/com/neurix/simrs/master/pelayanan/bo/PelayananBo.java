package com.neurix.simrs.master.pelayanan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 12/11/2019.
 */
public interface PelayananBo extends BaseMasterBo<Pelayanan> {
    public List<Pelayanan> getListAllPelayanan() throws GeneralBOException;
    public List<Pelayanan> getListApotek(String branch, String tipeApotek) throws GeneralBOException;
    public List<Pelayanan> getByCriteria(Pelayanan bean) throws GeneralBOException;

    ImSimrsPelayananEntity getPelayananByDivisiId(String id,String branchId) throws GeneralBOException;

    public List<Pelayanan> getListPelayananPaketPeriksa(String branch) throws GeneralBOException;
    public Pelayanan getPelayananById(String id) throws GeneralBOException;
    public List<Pelayanan> getListPelayananFarmasi(String branchId) throws GeneralBOException;

    public List<Pelayanan> getListPelayananWithLab(String tipe) throws GeneralBOException;
    public List<ImSimrsPelayananEntity> getByCriteria(Map criteria) throws GeneralBOException;

    public List<ImSimrsPelayananEntity> getPelayananByBranch(String branchId) throws GeneralBOException;
    public List<Pelayanan> getJustPelayananByBranch(String branchId) throws GeneralBOException;
    public List<Pelayanan> getJustPelayananAndLab(String branchId) throws GeneralBOException;
}
