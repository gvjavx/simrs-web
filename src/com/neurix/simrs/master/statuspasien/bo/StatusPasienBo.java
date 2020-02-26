package com.neurix.simrs.master.statuspasien.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.statuspasien.model.ImSimrsStatusPasienEntity;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface StatusPasienBo {
    public List<ImSimrsStatusPasienEntity> getListEntityStatusPasien(StatusPasien bean) throws GeneralBOException;
}
