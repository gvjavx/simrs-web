package com.neurix.hris.master.smkJabatan.bo;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkJabatan.model.SmkJabatan;
import com.neurix.hris.master.smkJabatan.model.SmkJabatanDetail;
import com.neurix.hris.master.smkKategoriAspek.model.SmkKategoriAspek;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkJabatanBo extends BaseMasterBo<SmkJabatan>{
    public void saveDelete(SmkJabatan bean) throws GeneralBOException;
    public SmkJabatan getEditSmkJabatan(String id) throws GeneralBOException;
    public List<Position> cekJabatan(String branchId, String id) throws GeneralBOException;
    public List<SmkJabatan> getViewSmkJabatan(SmkJabatan bean) throws GeneralBOException;
    public List<SmkJabatanDetail> getAspek(String branchId, String tipeAspek) throws GeneralBOException;
    public List<SmkJabatanDetail> getViewSmkJabatanDetail(SmkJabatanDetail bean) throws GeneralBOException;
    public List<SmkJabatanDetail> getListSmkJabatanDetail(SmkJabatanDetail bean) throws GeneralBOException;
    public String cekSudahTerinput(String branchId, String positionId, String aspek) throws GeneralBOException;


    public SmkJabatan getNilaiAspek(String branchId, String positionId) throws GeneralBOException;
}
