package com.neurix.simrs.transaksi.paketperiksa.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.dao.ItemPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.KelasPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.PaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.PaketPasienDao;
import com.neurix.simrs.transaksi.paketperiksa.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<MtSimrsPaketEntity> paketEntities = getListEntityPaket(bean);
        List<PaketPeriksa> paketPeriksaList = new ArrayList<>();
        if (paketEntities.size() > 0){

            PaketPeriksa paketPeriksa;
            for (MtSimrsPaketEntity paketEntity : paketEntities){
                paketPeriksa = new PaketPeriksa();
                paketPeriksa.setIdPaket(paketEntity.getIdPaket());
                paketPeriksa.setNamaPaket(paketEntity.getNamaPaket());
                paketPeriksa.setIdKelasPaket(paketEntity.getIdKelasPaket());

                PaketPeriksa kelas = new PaketPeriksa();
                kelas.setIdKelasPaket(paketEntity.getIdKelasPaket());
                List<ImSimrsKelasPaketEntity> kelasPaketEntities = getListEntityKelasPaket(kelas);
                if (kelasPaketEntities.size() > 0){
                    for (ImSimrsKelasPaketEntity kelasPaketEntity : kelasPaketEntities){
                        paketPeriksa.setNamaKelasPaket(kelasPaketEntity.getNamaKelasPaket());
                    }
                }

                paketPeriksa.setFlag(paketEntity.getFlag());
                paketPeriksa.setAction(paketEntity.getAction());
                paketPeriksa.setCreatedDate(paketEntity.getCreatedDate());
                paketPeriksa.setCreatedWho(paketEntity.getCreatedWho());
                paketPeriksa.setLastUpdate(paketEntity.getLastUpdate());
                paketPeriksa.setLastUpdateWho(paketEntity.getLastUpdateWho());
                paketPeriksaList.add(paketPeriksa);
            }
        }

        return paketPeriksaList;
    }

    public List<MtSimrsPaketEntity> getListEntityPaket(PaketPeriksa bean) throws GeneralBOException{

        List<MtSimrsPaketEntity> paketEntities = new ArrayList<>();
        if (bean != null){
            Map hsCriteria = new HashMap();
            if (bean.getIdPaket() != null)
                hsCriteria.put("id_paket", bean.getIdPaket());
            if (bean.getIdKelasPaket() != null)
                hsCriteria.put("id_kelas_paket", bean.getIdKelasPaket());
            if (bean.getIdPerusahaan() != null)
                hsCriteria.put("id_perusahaan", bean.getIdPerusahaan());

            try {
                paketEntities = paketDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PaketPeriksaBoImpl.getListEntityPaket] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.getListEntityPaket] ERROR. "+e);
            }
        }
        return paketEntities;
    }

    @Override
    public List<PaketPasien> getListPaketPasien(PaketPasien bean) throws GeneralBOException {
        return null;
    }



    public List<ItSimrsPaketPasienEntity> getListEntityPaketPasien(PaketPasien bean) throws GeneralBOException{

        List<ItSimrsPaketPasienEntity> paketPasienEntities = new ArrayList<>();
        if (bean != null){

            Map hsCriteria = new HashMap();
            if (bean.getIdPasien() != null){
                hsCriteria.put("id_pasien", bean.getIdPasien());
            }

            if (bean.getIdPaket() != null){
                hsCriteria.put("id_paket", bean.getIdPaket());
            }

            try {
                paketPasienEntities = paketPasienDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PaketPeriksaBoImpl.getListEntityPaketPasien] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.getListEntityPaketPasien] ERROR. "+e);
            }
        }
        return paketPasienEntities;
    }

    @Override
    public List<ItemPaket> getListItemPaket(ItemPaket bean) throws GeneralBOException {
        return null;
    }

    public List<MtSimrsItemPaketEntity> getListEntityItemPaket(ItemPaket bean) throws GeneralBOException{

        List<MtSimrsItemPaketEntity> itemPaketEntities = new ArrayList<>();
        if (bean != null){

            Map hsCriteria = new HashMap();
            if (bean.getIdItemPaket() != null){
                hsCriteria.put("id_item_paket", bean.getIdItemPaket());
            }

            if (bean.getIdPaket() != null){
                hsCriteria.put("id_paket", bean.getIdPaket());
            }

            if (bean.getIdItem() != null){
                hsCriteria.put("id_item", bean.getIdItem());
            }

            if (bean.getIdKategoriItem() != null){
                hsCriteria.put("id_kategori_item", bean.getIdKategoriItem());
            }

            if (bean.getJenisItem() != null){
                hsCriteria.put("jenis_item", bean.getJenisItem());
            }

            try {
                itemPaketEntities = itemPaketDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PaketPeriksaBoImpl.getListEntityItemPaket] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.getListEntityItemPaket] ERROR. "+e);
            }
        }

        return itemPaketEntities;
    }

    @Override
    public void savePaketPeriksa(MtSimrsPaketEntity bean, List<MtSimrsItemPaketEntity> listItem) throws GeneralBOException {

        logger.info("[PaketPeriksaBoImpl.savePaketPeriksa] START >>>");

        if (bean != null){
            bean.setIdPaket(getNextItemPaketId());
            try {
                paketDao.addAndSave(bean);
            } catch (HibernateException e){
                logger.error("[PaketPeriksaBoImpl.savePaketPeriksa] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.savePaketPeriksa] ERROR. "+e);
            }

            if (listItem.size() > 0){

                for (MtSimrsItemPaketEntity itemPaketEntity : listItem){
                    itemPaketEntity.setIdItemPaket(getNextItemPaketId());
                    itemPaketEntity.setIdPaket(bean.getIdPaket());
                    itemPaketEntity.setFlag(bean.getFlag());
                    itemPaketEntity.setAction(bean.getAction());
                    itemPaketEntity.setCreatedDate(bean.getCreatedDate());
                    itemPaketEntity.setCreatedWho(bean.getCreatedWho());
                    itemPaketEntity.setLastUpdate(bean.getLastUpdate());
                    itemPaketEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        itemPaketDao.addAndSave(itemPaketEntity);
                    } catch (HibernateException e){
                        logger.error("[PaketPeriksaBoImpl.savePaketPeriksa] ERROR. ", e);
                        new GeneralBOException("[PaketPeriksaBoImpl.savePaketPeriksa] ERROR. "+e);
                    }
                }

            }
        }

        logger.info("[PaketPeriksaBoImpl.savePaketPeriksa] END <<<");
    }

    @Override
    public void savePaketPasien(PaketPasien bean) throws GeneralBOException {

    }

    @Override
    public void saveItemPaket(ItemPaket bean) throws GeneralBOException {

    }

    @Override
    public List<ImSimrsKelasPaketEntity> getListEntityKelasPaket(PaketPeriksa bean) throws GeneralBOException {
        List<ImSimrsKelasPaketEntity> kelasPaketEntities = new ArrayList<>();
        if (bean != null){

            Map hsCriteria = new HashMap();
            if (bean.getIdKelasPaket() != null){
                hsCriteria.put("id_kelas_paket", bean.getIdKelasPaket());
            }

            try {
                kelasPaketEntities = kelasPaketDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PaketPeriksaBoImpl.getListEntityKelasPaket] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.getListEntityKelasPaket] ERROR. "+e);
            }

        }
        return kelasPaketEntities;
    }

    private String getNextPaketPeriksaId(){

        String id = "";

        try {
            id = paketDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[PaketPeriksaBoImpl.getNextPaketPeriksaId] ERROR. ", e);
        }

        if (!"".equalsIgnoreCase(id)){
            id = "PKT"+id;
        }

        return id;
    }

    private String getNextItemPaketId(){
        String id = "";
        try {
            id = itemPaketDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[PaketPeriksaBoImpl.getNextItemPaketId] ERROR. ", e);
        }

        if (!"".equalsIgnoreCase(id)){
            id = "ITP"+id;
        }
        return id;
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
