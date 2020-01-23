package com.neurix.simrs.master.pasien.bo;

import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.belajar.model.Belajar;
import com.neurix.simrs.master.pasien.model.Pasien;

import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface PasienBo extends GeneralBo {
    public List<Pasien> getByCriteria(Pasien bean) throws GeneralBOException;
    public void saveAdd(Pasien pasien) throws GeneralBOException;
    public void saveEdit(Pasien pasien) throws GeneralBOException;
    public void saveDelete(Pasien bean) throws GeneralBOException;
    public List<Pasien> getListComboPasien(String query) throws GeneralBOException;
    public List<Pasien> getDataPasien(String desaId) throws GeneralBOException;

    public void saveEditPassword(Pasien bean) throws GeneralBOException;
    public void saveCreateUserPasien(Pasien bean) throws GeneralBOException;
    public Boolean isUserPasienById(String userId, String password) throws GeneralBOException;
}
