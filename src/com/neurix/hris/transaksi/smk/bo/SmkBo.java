package com.neurix.hris.transaksi.smk.bo;

import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkJabatan.model.SmkJabatanDetail;
import com.neurix.hris.transaksi.smk.model.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkBo extends BaseMasterBo<Smk>{
    public void saveDelete(Smk bean) throws GeneralBOException;
    public List<SmkHistoryEvaluasiPegawai> smkHistorySys(String nip, String tahun) throws GeneralBOException;
    public List<SmkJabatanDetail> getDataIndikator(String branchId, String positionId, String tipeAspek) throws GeneralBOException;
    public List<SmkJabatanDetail> getDataIndikatorA(String nip, String positionId, String branchId, String periode) throws GeneralBOException;
    public List<Smk> getSearch(Smk searchBean) throws GeneralBOException;
    public Smk getSearchEdit(Smk searchBean) throws GeneralBOException;
    public List<Smk> getEditAspekA(Smk searchBean) throws GeneralBOException;
    public List<Smk> getEditAspek(Smk searchBean) throws GeneralBOException;
    public Smk getAspekDetail(Smk searchBean) throws GeneralBOException;
    public Smk getAspekADetail(Smk searchBean) throws GeneralBOException;
    public List<SmkAspekActivityMonthly> getAspekDetailMonthly(String aspekDetailId) throws GeneralBOException;
    public List<Smk> getAspekDetailMonthlyCheckList(String aspekDetailId) throws GeneralBOException;
    public List<SmkEvaluasiPegawaiAspekActivityPeristiwa> getAspekDetailPeristiwa(String monthlyId) throws GeneralBOException;
    public void saveAddPeristiwa(SmkEvaluasiPegawaiAspekActivityPeristiwa bean) throws GeneralBOException;
    public void saveEditPeristiwa(SmkEvaluasiPegawaiAspekActivityPeristiwa bean) throws GeneralBOException;
    public void saveEditMonthly(SmkAspekActivityMonthly bean) throws GeneralBOException;
    public void saveUpdateRataRata(SmkAspekActivityMonthly bean) throws GeneralBOException;
    public void saveEditMonthlySub(SmkAspekActivityMonthly bean) throws GeneralBOException;
    public void saveEditAspekDetail(Smk bean) throws GeneralBOException;
    public void saveEditAspekADetail(Smk bean) throws GeneralBOException;
    public void saveEditAspek(SmkEvaluasiPegawaiAspek bean) throws GeneralBOException;

    public SmkEvaluasiPegawaiAspekActivityPeristiwa getItemPeristiwaSys(String idPeristiwa) throws GeneralBOException;
    public void saveDeletePeristiwaSys(String idPeristiwa) throws GeneralBOException;
    public void updatePoinPrestasi(String smkId) throws GeneralBOException;
    public boolean cekApproveSys(String branchId, String positionId, String periode) throws GeneralBOException;
    public boolean cekUserSmkSys(String nip, String periode) throws GeneralBOException;
    public Smk getNilaiPrestasiSys(String smkId) throws GeneralBOException;
    public BigDecimal getNilaiShareSys(Smk smk) throws GeneralBOException;
    public String getKlasifikasiNilaiSys(String nilai) throws GeneralBOException;
    public String getIdMonthlySys(String activityId, String tanggal) throws GeneralBOException;
    public List<SmkEvaluasiPegawaiAspekActivityPeristiwa> aspekPeristiwaSys(String activityId) throws GeneralBOException;
    public List<Biodata> comboUserSmkSys(String query, String unit, String jabatan) throws GeneralBOException;
    public void cancelSmkSys() throws GeneralBOException;
    public void applySmk(Smk smk) throws GeneralBOException;

    public List<Branch> listBranch(String nip, String periode) throws GeneralBOException;
    public List<ImPosition> listPosisi(String nip, String periode) throws GeneralBOException;
    public List<ImPosition> listPosisi(String nip, String periode, String branchId) throws GeneralBOException;
    public String masaKerjaBulan(String nip, String periode, String branchId, String positionId) throws GeneralBOException;

    public List<Smk> printReportSmkSys(String periode, String unit) throws GeneralBOException;
}
