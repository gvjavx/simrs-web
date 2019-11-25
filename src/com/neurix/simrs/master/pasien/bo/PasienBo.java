package com.neurix.simrs.master.pasien.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.model.Pasien;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface PasienBo {
    public List<Pasien> getByCriteria(Pasien bean) throws GeneralBOException;
    public void saveAdd(Pasien pasien) throws GeneralBOException;
    public void saveEdit(Pasien pasien) throws GeneralBOException;
    public List<Pasien> getListComboPasien(String query) throws GeneralBOException;
}
