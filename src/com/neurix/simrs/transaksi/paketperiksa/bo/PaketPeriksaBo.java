package com.neurix.simrs.transaksi.paketperiksa.bo;

import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.paketperiksa.model.*;

import java.util.List;

/**
 * Created by reza on 12/03/20.
 */
public interface PaketPeriksaBo{

    public List<PaketPeriksa> getListPaketPeriksa(PaketPeriksa bean) throws GeneralBOException;
    public List<PaketPasien> getListPaketPasien(PaketPasien bean) throws GeneralBOException;
    public List<ItemPaket> getListItemPaket(ItemPaket bean) throws GeneralBOException;
    public CrudResponse savePaketPeriksa(MtSimrsPaketEntity bean, List<MtSimrsItemPaketEntity> listItem, List<MtSimrsDetailPaketEntity> detailPaket) throws GeneralBOException;
    public CheckResponse savePaketPasien(PaketPasien bean) throws GeneralBOException;
    public void saveItemPaket(ItemPaket bean) throws GeneralBOException;

    public List<ImSimrsKelasPaketEntity> getListEntityKelasPaket(PaketPeriksa bean) throws GeneralBOException;

    public List<PaketPeriksa> getListDaftarPaketPasien(PaketPeriksa bean) throws GeneralBOException;
    public List<PaketPeriksa> getListDetailDaftarPaketPasien(String idPaket, String idPerusahaan, String branchId) throws GeneralBOException;
    public List<PaketPeriksa> getDetailPaket(String idPaket) throws GeneralBOException;
    public Boolean cekPaketWithIdPasien(String idPasien) throws GeneralBOException;
    public List<PaketPeriksa> getDetailItemPaket(String idLab, String idPaket) throws GeneralBOException;

    public List<PaketPeriksa> getListPaketRawatJalan(String branchId) throws GeneralBOException;
    public List<PaketPeriksa> getListPaketIgd(String branchId) throws GeneralBOException;
    public MtSimrsPaketEntity getPaketEntityById(String id) throws GeneralBOException;
    public ItSimrsPaketPasienEntity getPaketPasienEntityByIdPaket(String id, String pasien) throws GeneralBOException;

    public List<MasterVendor> getListPerusahaan() throws GeneralBOException;

}
