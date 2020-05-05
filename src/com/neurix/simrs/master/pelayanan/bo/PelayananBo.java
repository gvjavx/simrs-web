package com.neurix.simrs.master.pelayanan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;

import java.util.List;

/**
 * Created by Toshiba on 12/11/2019.
 */
public interface PelayananBo extends BaseMasterBo<Pelayanan> {
    public List<Pelayanan> getListAllPelayanan() throws GeneralBOException;
    public List<Pelayanan> getListApotek(String branch) throws GeneralBOException;
    public List<Pelayanan> getByCriteria(Pelayanan bean) throws GeneralBOException;
    public List<Pelayanan> getListPelayananPaketPeriksa(String branch) throws GeneralBOException;
    public ImSimrsPelayananEntity getPelayananById(String id) throws GeneralBOException;
}
