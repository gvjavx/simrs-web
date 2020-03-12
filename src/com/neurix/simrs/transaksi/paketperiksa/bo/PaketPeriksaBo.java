package com.neurix.simrs.transaksi.paketperiksa.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.paketperiksa.model.ImSimrsKelasPaketEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPasien;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;

import java.util.List;

/**
 * Created by reza on 12/03/20.
 */
public interface PaketPeriksaBo{

    public List<PaketPeriksa> getListPaketPeriksa(PaketPeriksa bean) throws GeneralBOException;
    public List<PaketPasien> getListPaketPasien(PaketPasien bean) throws GeneralBOException;
    public List<ItemPaket> getListItemPaket(ItemPaket bean) throws GeneralBOException;
    public void savePaketPeriksa(PaketPeriksa bean) throws GeneralBOException;
    public void savePaketPasien(PaketPasien bean) throws GeneralBOException;
    public void saveItemPaket(ItemPaket bean) throws GeneralBOException;

    public List<ImSimrsKelasPaketEntity> getListEntityKelasPaket() throws GeneralBOException;

}
