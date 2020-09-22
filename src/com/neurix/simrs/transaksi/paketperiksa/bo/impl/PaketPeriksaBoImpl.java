package com.neurix.simrs.transaksi.paketperiksa.bo.impl;

import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
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
        if (paketEntities.size() > 0) {

            PaketPeriksa paketPeriksa;
            for (MtSimrsPaketEntity paketEntity : paketEntities) {
                paketPeriksa = new PaketPeriksa();
                paketPeriksa.setIdPaket(paketEntity.getIdPaket());
                paketPeriksa.setNamaPaket(paketEntity.getNamaPaket());
                paketPeriksa.setIdKelasPaket(paketEntity.getIdKelasPaket());

//                PaketPeriksa kelas = new PaketPeriksa();
//                kelas.setIdKelasPaket(paketEntity.getIdKelasPaket());
//                List<ImSimrsKelasPaketEntity> kelasPaketEntities = getListEntityKelasPaket(kelas);
//                if (kelasPaketEntities.size() > 0){
//                    for (ImSimrsKelasPaketEntity kelasPaketEntity : kelasPaketEntities){
//                        paketPeriksa.setNamaKelasPaket(kelasPaketEntity.getNamaKelasPaket());
//                    }
//                }

                paketPeriksa.setFlag(paketEntity.getFlag());
                paketPeriksa.setAction(paketEntity.getAction());
                paketPeriksa.setCreatedDate(paketEntity.getCreatedDate());
                paketPeriksa.setCreatedWho(paketEntity.getCreatedWho());
                paketPeriksa.setLastUpdate(paketEntity.getLastUpdate());
                paketPeriksa.setLastUpdateWho(paketEntity.getLastUpdateWho());
                paketPeriksa.setIdPelayanan(paketEntity.getIdPelayanan());
                paketPeriksa.setTarif(paketEntity.getTarif());
                paketPeriksaList.add(paketPeriksa);
            }
        }

        return paketPeriksaList;
    }

    public List<MtSimrsPaketEntity> getListEntityPaket(PaketPeriksa bean) throws GeneralBOException {

        List<MtSimrsPaketEntity> paketEntities = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdPaket() != null && !"".equalsIgnoreCase(bean.getIdPaket())) {
                hsCriteria.put("id_paket", bean.getIdPaket());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            if (bean.getNamaPaket() != null && !"".equalsIgnoreCase(bean.getNamaPaket())) {
                hsCriteria.put("nama_paket", bean.getNamaPaket());
            }

            hsCriteria.put("flag","Y");

            try {
                paketEntities = paketDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PaketPeriksaBoImpl.getListEntityPaket] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.getListEntityPaket] ERROR. " + e);
            }
        }
        return paketEntities;
    }

    @Override
    public List<PaketPasien> getListPaketPasien(PaketPasien bean) throws GeneralBOException {
        return null;
    }


    public List<ItSimrsPaketPasienEntity> getListEntityPaketPasien(PaketPasien bean) throws GeneralBOException {

        List<ItSimrsPaketPasienEntity> paketPasienEntities = new ArrayList<>();
        if (bean != null) {

            Map hsCriteria = new HashMap();
            if (bean.getIdPasien() != null) {
                hsCriteria.put("id_pasien", bean.getIdPasien());
            }

            if (bean.getIdPaket() != null) {
                hsCriteria.put("id_paket", bean.getIdPaket());
            }

            try {
                paketPasienEntities = paketPasienDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PaketPeriksaBoImpl.getListEntityPaketPasien] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.getListEntityPaketPasien] ERROR. " + e);
            }
        }
        return paketPasienEntities;
    }

    @Override
    public List<ItemPaket> getListItemPaket(ItemPaket bean) throws GeneralBOException {
        return null;
    }

    public List<MtSimrsItemPaketEntity> getListEntityItemPaket(ItemPaket bean) throws GeneralBOException {

        List<MtSimrsItemPaketEntity> itemPaketEntities = new ArrayList<>();
        if (bean != null) {

            Map hsCriteria = new HashMap();
            if (bean.getIdItemPaket() != null) {
                hsCriteria.put("id_item_paket", bean.getIdItemPaket());
            }

            if (bean.getIdPaket() != null) {
                hsCriteria.put("id_paket", bean.getIdPaket());
            }

            if (bean.getIdItem() != null) {
                hsCriteria.put("id_item", bean.getIdItem());
            }

            if (bean.getIdKategoriItem() != null) {
                hsCriteria.put("id_kategori_item", bean.getIdKategoriItem());
            }

            if (bean.getJenisItem() != null) {
                hsCriteria.put("jenis_item", bean.getJenisItem());
            }

            try {
                itemPaketEntities = itemPaketDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PaketPeriksaBoImpl.getListEntityItemPaket] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.getListEntityItemPaket] ERROR. " + e);
            }
        }

        return itemPaketEntities;
    }

    @Override
    public CrudResponse savePaketPeriksa(MtSimrsPaketEntity bean, List<MtSimrsItemPaketEntity> listItem) throws GeneralBOException {

        logger.info("[PaketPeriksaBoImpl.savePaketPeriksa] START >>>");
        CrudResponse response = new CrudResponse();

        if (bean != null) {
            bean.setIdPaket(getNextItemPaketId());
            try {
                paketDao.addAndSave(bean);
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg(e.getMessage());
                logger.error("[PaketPeriksaBoImpl.savePaketPeriksa] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.savePaketPeriksa] ERROR. " + e);
            }

            if (listItem.size() > 0) {

                for (MtSimrsItemPaketEntity itemPaketEntity : listItem) {
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
                        response.setStatus("success");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
                        logger.error("[PaketPeriksaBoImpl.savePaketPeriksa] ERROR. ", e);
                        new GeneralBOException("[PaketPeriksaBoImpl.savePaketPeriksa] ERROR. " + e);
                    }
                }
            }
        }

        logger.info("[PaketPeriksaBoImpl.savePaketPeriksa] END <<<");
        return response;
    }

    @Override
    public CheckResponse savePaketPasien(PaketPasien bean) throws GeneralBOException {
        CheckResponse response = new CheckResponse();

        if (bean != null) {

            ItSimrsPaketPasienEntity entity = new ItSimrsPaketPasienEntity();

            entity.setId(getNextIdPaketPasien());
            entity.setIdPasien(bean.getIdPasien());
            entity.setIdPaket(bean.getIdPaket());
            entity.setIdPerusahaan(bean.getIdPerusahaan());
            entity.setFlag("Y");
            entity.setAction("C");
            entity.setCreatedDate(bean.getCreatedDate());
            entity.setCreatedWho(bean.getCreatedWho());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                paketPasienDao.addAndSave(entity);
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error " + e.getMessage());
                logger.error("Found Error " + e.getMessage());
            }
        }

        return response;
    }

    @Override
    public void saveItemPaket(ItemPaket bean) throws GeneralBOException {

    }

    @Override
    public List<ImSimrsKelasPaketEntity> getListEntityKelasPaket(PaketPeriksa bean) throws GeneralBOException {
        List<ImSimrsKelasPaketEntity> kelasPaketEntities = new ArrayList<>();
        if (bean != null) {

            Map hsCriteria = new HashMap();
            if (bean.getIdKelasPaket() != null) {
                hsCriteria.put("id_kelas_paket", bean.getIdKelasPaket());
            }

            try {
                kelasPaketEntities = kelasPaketDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PaketPeriksaBoImpl.getListEntityKelasPaket] ERROR. ", e);
                new GeneralBOException("[PaketPeriksaBoImpl.getListEntityKelasPaket] ERROR. " + e);
            }

        }
        return kelasPaketEntities;
    }

    @Override
    public List<PaketPeriksa> getListDaftarPaketPasien(PaketPeriksa bean) throws GeneralBOException {
        List<PaketPeriksa> list = new ArrayList<>();
        if (bean != null) {
            try {
                list = paketPasienDao.getListDaftarPaketPasien(bean);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return list;
    }

    @Override
    public List<PaketPeriksa> getListDetailDaftarPaketPasien(String idPaket, String idPerusahaan, String branchId) throws GeneralBOException {
        List<PaketPeriksa> list = new ArrayList<>();
        if (idPaket != null && idPerusahaan != null && branchId != null) {
            try {
                list = paketPasienDao.getDetailDaftarPaketPasien(idPaket, idPerusahaan, branchId);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return list;
    }

    @Override
    public List<PaketPeriksa> getDetailPaket(String idPaket) throws GeneralBOException {
        List<PaketPeriksa> list = new ArrayList<>();
        if (idPaket != null) {
            try {
                list = paketPasienDao.getDetailPaket(idPaket);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return list;
    }

    @Override
    public Boolean cekPaketWithIdPasien(String idPasien) throws GeneralBOException {
        Boolean response = false;

        try {
            response = paketPasienDao.cekPaketWithIdPasien(idPasien);
        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
        }

        return response;
    }

    @Override
    public List<PaketPeriksa> getDetailItemPaket(String idLab, String idPaket) throws GeneralBOException {
        List<PaketPeriksa> list = new ArrayList<>();
        if (idPaket != null && idLab != null) {
            try {
                list = paketPasienDao.getDetailItemPaket(idLab, idPaket);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return list;
    }

    @Override
    public List<PaketPeriksa> getListPaketRawatJalan(String branchId) throws GeneralBOException {
        List<PaketPeriksa> list = new ArrayList<>();
        if (branchId != null && !"".equalsIgnoreCase(branchId)) {
            try {
                list = paketPasienDao.getPaketPeriksaRawatJalan(branchId);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return list;
    }

    @Override
    public List<PaketPeriksa> getListPaketIgd(String branchId) throws GeneralBOException {
        List<PaketPeriksa> list = new ArrayList<>();
        if (branchId != null && !"".equalsIgnoreCase(branchId)) {
            try {
                list = paketPasienDao.getPaketPeriksaIgd(branchId);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return list;
    }

    @Override
    public MtSimrsPaketEntity getPaketEntityById(String id) throws GeneralBOException {
        return paketDao.getById("idPaket", id);
    }

    @Override
    public ItSimrsPaketPasienEntity getPaketPasienEntityByIdPaket(String id, String idpasien) throws GeneralBOException {

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_paket", id);
        hsCriteria.put("id_pasien", idpasien);
        hsCriteria.put("flag_selesai_null", "Y");

        List<ItSimrsPaketPasienEntity> paketPasienEntities = paketPasienDao.getByCriteria(hsCriteria);
        if (paketPasienEntities.size() > 0){
            return paketPasienEntities.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<MasterVendor> getListPerusahaan() throws GeneralBOException {
        return paketPasienDao.getListPerusahaan();
    }

    private String getNextPaketPeriksaId() {

        String id = "";

        try {
            id = paketDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[PaketPeriksaBoImpl.getNextPaketPeriksaId] ERROR. ", e);
        }

        if (!"".equalsIgnoreCase(id)) {
            id = "PKT" + id;
        }

        return id;
    }

    private String getNextItemPaketId() {
        String id = "";
        try {
            id = itemPaketDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[PaketPeriksaBoImpl.getNextItemPaketId] ERROR. ", e);
        }

        if (!"".equalsIgnoreCase(id)) {
            id = "ITP" + id;
        }
        return id;
    }

    private String getNextIdPaketPasien() {
        String id = "";
        try {
            id = paketPasienDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[PaketPeriksaBoImpl.getNextItemPaketId] ERROR. ", e);
        }

        if (!"".equalsIgnoreCase(id)) {
            id = "PKP" + id;
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
