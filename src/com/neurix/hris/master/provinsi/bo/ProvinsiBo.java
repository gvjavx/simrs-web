package com.neurix.hris.master.provinsi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.provinsi.model.Desa;
import com.neurix.hris.master.provinsi.model.Kecamatan;
import com.neurix.hris.master.provinsi.model.Kota;
import com.neurix.hris.master.provinsi.model.Provinsi;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface ProvinsiBo extends BaseMasterBo<Provinsi>{
    public void saveDelete(Provinsi bean) throws GeneralBOException;

    public List<Provinsi> getComboProvinsiWithCriteria(String query) throws GeneralBOException;

    String getKotaName(String query) throws GeneralBOException;

    public List<Kota> getComboKotaWithCriteria(String query, String prov) throws GeneralBOException;
    public List<Kecamatan> getComboKecamatanWithCriteria(String query, String kota) throws GeneralBOException;
    public List<Desa> getComboDesaWithCriteria(String query, String kecamatan) throws GeneralBOException;
    /*public List<Kota> getComboKotaWithCriteria(String query, String prov) throws GeneralBOException;*/

}
