package com.neurix.simrs.transaksi.paketperiksa.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.dao.ItemPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.KelasPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.PaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.PaketPasienDao;
import com.neurix.simrs.transaksi.paketperiksa.model.ImSimrsKelasPaketEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPasien;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by reza on 12/03/20.
 */
public class PaketPeriksaBoImpl implements PaketPeriksaBo {

    private static transient Logger logger = Logger.getLogger(PaketPeriksaBoImpl.class);

    private ItemPaketDao itemPaketDao;
    private KelasPaketDao kelasPaketDao;
    private PaketDao paketDao;
    private PaketPasienDao paketPasienDao;

    @Override
    public List<PaketPeriksa> getListPaketPeriksa(PaketPeriksa bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<PaketPasien> getListPaketPasien(PaketPasien bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<ItemPaket> getListItemPaket(ItemPaket bean) throws GeneralBOException {
        return null;
    }

    @Override
    public void savePaketPeriksa(PaketPeriksa bean) throws GeneralBOException {

    }

    @Override
    public void savePaketPasien(PaketPasien bean) throws GeneralBOException {

    }

    @Override
    public void saveItemPaket(ItemPaket bean) throws GeneralBOException {

    }

    @Override
    public List<ImSimrsKelasPaketEntity> getListEntityKelasPaket() throws GeneralBOException {
        return null;
    }

    public void setItemPaketDao(ItemPaketDao itemPaketDao) {
        this.itemPaketDao = itemPaketDao;
    }

    public void setKelasPaketDao(KelasPaketDao kelasPaketDao) {
        this.kelasPaketDao = kelasPaketDao;
    }

    public void setPaketDao(PaketDao paketDao) {
        this.paketDao = paketDao;
    }

    public void setPaketPasienDao(PaketPasienDao paketPasienDao) {
        this.paketPasienDao = paketPasienDao;
    }
}
