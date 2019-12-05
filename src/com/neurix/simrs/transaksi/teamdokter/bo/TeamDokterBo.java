package com.neurix.simrs.transaksi.teamdokter.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;

import java.util.List;

/**
 * Created by Toshiba on 18/11/2019.
 */
public interface TeamDokterBo {
    public List<DokterTeam> getByCriteria(DokterTeam bean) throws GeneralBOException;
    public void savaAdd(DokterTeam bean) throws GeneralBOException;
    public void saveEdit(DokterTeam bean) throws GeneralBOException;
}
