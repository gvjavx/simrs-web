package com.neurix.hris.transaksi.mutasi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.mutasi.model.Mutasi;
import com.neurix.hris.transaksi.mutasi.model.Mutasi;
import com.neurix.hris.transaksi.mutasi.model.MutasiDoc;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface MutasiBo extends BaseMasterBo<Mutasi>{
    public void saveDelete(Mutasi bean) throws GeneralBOException;
    public void saveMutasi(Mutasi bean) throws GeneralBOException;
//    public List<Mutasi> getKualifikasi(Mutasi bean) throws GeneralBOException;
    public List<Mutasi> getComboMutasi(String nip) throws GeneralBOException;
    public Mutasi getDataReportMutasi(String mutasiId) throws GeneralBOException;
    public List<MutasiDoc> getMutasiDoc(String mutasiId) throws GeneralBOException;
    public List<PersonilPosition> getAvailableJabatan(PersonilPosition bean) throws GeneralBOException;
    public MutasiDoc addMutasiDoc(MutasiDoc bean) throws GeneralBOException ;
    public String cekDataMutasiSys() throws GeneralBOException ;
    public String getDirektur() throws GeneralBOException;
    public BigDecimal getGajiPokok(String golonganId);
}
