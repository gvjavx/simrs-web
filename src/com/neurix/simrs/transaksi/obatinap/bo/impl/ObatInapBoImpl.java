package com.neurix.simrs.transaksi.obatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisobat.dao.JenisObatDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatinap.bo.ObatInapBo;
import com.neurix.simrs.transaksi.obatinap.dao.ObatInapDao;
import com.neurix.simrs.transaksi.obatinap.model.ItSimrsObatInapEntity;
import com.neurix.simrs.transaksi.obatinap.model.ObatInap;
import com.neurix.simrs.transaksi.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsObatPoliEntity;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObatInapBoImpl implements ObatInapBo {
    private static transient Logger logger = Logger.getLogger(ObatInapBoImpl.class);
    private ObatInapDao obatInapDao;
    private JenisObatDao jenisObatDao;
    private ObatDao obatDao;
    private ObatPoliDao obatPoliDao;

    public void setObatPoliDao(ObatPoliDao obatPoliDao) {
        this.obatPoliDao = obatPoliDao;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setJenisObatDao(JenisObatDao jenisObatDao) {
        this.jenisObatDao = jenisObatDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setObatInapDao(ObatInapDao obatInapDao) {
        this.obatInapDao = obatInapDao;
    }

    @Override
    public List<ObatInap> getByCriteria(ObatInap bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.getByCriteria] Start >>>>>>>");

        List<ObatInap> results = new ArrayList<>();

        if (bean != null) {
            List<ItSimrsObatInapEntity> obatInapEntities = getListEntity(bean);
            if (!obatInapEntities.isEmpty()) {
                results = setToTemplate(obatInapEntities);
            }
        }

        logger.info("[ObatInapBoImpl.getByCriteria] End <<<<<<");
        return results;
    }

    @Override
    public void saveAdd(ObatInap bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.saveAdd] Start >>>>>>>");

        if (bean != null) {
            String id = getNextId();
            if (id != null && !"".equalsIgnoreCase(id)) {
                ItSimrsObatInapEntity obatInapEntity = new ItSimrsObatInapEntity();
                obatInapEntity.setIdObatInap("OBI" + id);
                obatInapEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                obatInapEntity.setIdObat(bean.getIdObat());
                obatInapEntity.setNamaObat(bean.getNamaObat());
                obatInapEntity.setHarga(bean.getHarga());
                obatInapEntity.setQty(bean.getQty());
                obatInapEntity.setTotalHarga(bean.getTotalHarga());
                obatInapEntity.setFlag("Y");
                obatInapEntity.setAction("C");
                obatInapEntity.setCreatedDate(bean.getCreatedDate());
                obatInapEntity.setCreatedWho(bean.getCreatedWho());
                obatInapEntity.setLastUpdate(bean.getLastUpdate());
                obatInapEntity.setLastUpdateWho(bean.getLastUpdateWho());
                obatInapEntity.setJenisSatuan(bean.getJenisSatuan());

                ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                obatDetailEntity.setIdObat(bean.getIdObat());
                obatDetailEntity.setQtyApprove(bean.getQty());
                obatDetailEntity.setJenisSatuan(bean.getJenisSatuan());
                obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                obatDetailEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    obatInapDao.addAndSave(obatInapEntity);
                    updateSubstractStockObatPoli(obatDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[ObatInapBoImpl.saveAdd] Error when insert obat inap ", e);
                    throw new GeneralBOException("[ObatInapBoImpl.saveAdd] Error when insert obat inap " + e.getMessage());
                }


            }
        }
//        bean.setCekQty("new");
//        updateStokObat(bean);
        logger.info("[ObatInapBoImpl.saveAdd] End <<<<<<");
    }

    @Override
    public void saveEdit(ObatInap bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.saveEdit] Start >>>>>>>");

        if (bean != null) {

            ItSimrsObatInapEntity entity = null;

            try {
                entity = obatInapDao.getById("idObatInap", bean.getIdObatInap());
            } catch (HibernateException e) {
                logger.error("[TeamDokterBoImpl.saveEdit] Error when getById rawat inap ", e);
                throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit diagnosa rawat " + e.getMessage());
            }
            if (entity != null) {

                ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                obatDetailEntity.setIdObat(bean.getIdObat());
                obatDetailEntity.setJenisSatuan(bean.getJenisSatuan());
                obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                obatDetailEntity.setLastUpdate(bean.getLastUpdate());

                int font = bean.getQty().intValue();
                int end = entity.getQty().intValue();

                if (font > end) {
                    bean.setCekQty("mines");
                    BigInteger qty = bean.getQty().subtract(entity.getQty());
                    obatDetailEntity.setQtyApprove(qty);
                }

                if (end > font) {
                    bean.setCekQty("plus");
                    BigInteger qty = entity.getQty().subtract(bean.getQty());
                    obatDetailEntity.setQtyApprove(qty);

                }

                entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                entity.setIdObat(bean.getIdObat());
                entity.setNamaObat(bean.getNamaObat());
                entity.setQty(bean.getQty());
                entity.setAction(bean.getAction());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
//                entity.setHarga(bean.getHarga());
//                entity.setTotalHarga(bean.getTotalHarga());

                logger.info("[ObatInapBoImpl.saveEdit] FRONT --> " + font);
                logger.info("[ObatInapBoImpl.saveEdit] END   --> " + end);
                logger.info("[ObatInapBoImpl.saveEdit] QTY APPROVE   --> " + obatDetailEntity.getQtyApprove());

                try {

                    obatInapDao.updateAndSave(entity);

                    if ("mines".equalsIgnoreCase(bean.getCekQty())) {
                        updateSubstractStockObatPoli(obatDetailEntity);
                    }
                    if ("plus".equalsIgnoreCase(bean.getCekQty())) {
                        updateAddStockPoli(obatDetailEntity);
                    }

                } catch (HibernateException e) {
                    logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit obat inap ", e);
                    throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
                }
            }
        }

//        updateStokObat(bean);
        logger.info("[ObatInapBoImpl.saveEdit] End <<<<<<");
    }

    public String getNextId() {
        logger.info("[ObatInapBoImpl.getNextId] Start >>>>>>>");

        String id = "";
        try {
            id = obatInapDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[ObatInapBoImpl.getNextId] Error when get next id obat inap");
        }

        logger.info("[ObatInapBoImpl.getNextId] End <<<<<<");
        return id;
    }

    public List<ItSimrsObatInapEntity> getListEntity(ObatInap bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.getListEntity] Start >>>>>>>");
        List<ItSimrsObatInapEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdObatInap() != null && !"".equalsIgnoreCase(bean.getIdObatInap())) {
            hsCriteria.put("id_obat_inap", bean.getIdObatInap());
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        hsCriteria.put("flag", "Y");
        try {
            results = obatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatInapBoImpl.getListEntity] Erro when searching data obat inap ", e);
        }

        logger.info("[ObatInapBoImpl.getListEntityT] End <<<<<<");
        return results;
    }

    protected List<ObatInap> setToTemplate(List<ItSimrsObatInapEntity> entities) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.setToTemplate] Start >>>>>>>");
        List<ObatInap> results = new ArrayList<>();

        ObatInap obatInap;
        for (ItSimrsObatInapEntity entity : entities) {

            obatInap = new ObatInap();
            obatInap.setIdObatInap(entity.getIdObatInap());
            obatInap.setIdDetailCheckup(entity.getIdDetailCheckup());
            obatInap.setIdObat(entity.getIdObat());
            obatInap.setNamaObat(entity.getNamaObat());
            obatInap.setHarga(entity.getHarga());
            obatInap.setQty(entity.getQty());
            obatInap.setTotalHarga(entity.getTotalHarga());
            obatInap.setFlag(entity.getFlag());
            obatInap.setAction(entity.getAction());
            obatInap.setCreatedDate(entity.getCreatedDate());
            obatInap.setCreatedWho(entity.getCreatedWho());
            obatInap.setLastUpdate(entity.getLastUpdate());
            obatInap.setLastUpdateWho(entity.getLastUpdateWho());
            obatInap.setJenisSatuan(entity.getJenisSatuan());

//            Obat obat = new Obat();
//            obat.setIdObat(entity.getIdObat());
//            obat.setBranchId(CommonUtil.userBranchLogin());
//            List<ImSimrsObatEntity> obatEntityList = getListObatEntity(obat);

//            ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
//            if (!obatEntityList.isEmpty()) {
//                obatEntity = obatEntityList.get(0);
//            }
//
//            if (obatEntity != null) {
//                obatInap.setQtyBox(obatEntity.getQtyBox());
//                obatInap.setQtyLembar(obatEntity.getQtyLembar());
//                obatInap.setQtyBiji(obatEntity.getQtyBiji());
//                obatInap.setLembarPerBox(obatEntity.getLembarPerBox());
//                obatInap.setBijiPerLembar(obatEntity.getBijiPerLembar());
//            }

            ObatPoli obatPoli = new ObatPoli();
            obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
            obatPoli.setIdObat(entity.getIdObat());
            obatPoli.setBranchId(CommonUtil.userBranchLogin());
            obatPoli.setFlag("Y");
            MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);

            if (obatPoliEntity != null) {
                obatInap.setQtyBox(obatPoliEntity.getQtyBox());
                obatInap.setQtyLembar(obatPoliEntity.getQtyLembar());
                obatInap.setQtyBiji(obatPoliEntity.getQtyBiji());

                ImSimrsObatEntity obatEntity = getObatById(obatPoliEntity.getIdObat());
                if (obatEntity != null) {
                    obatInap.setLembarPerBox(obatEntity.getLembarPerBox());
                    obatInap.setBijiPerLembar(obatEntity.getBijiPerLembar());
                }
            }

            results.add(obatInap);
        }
        logger.info("[ObatInapBoImpl.setToTemplate] End <<<<<<");
        return results;
    }

    private List<ImSimrsObatEntity> getListObatEntity(Obat bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.getListObatEntity] Start >>>>>>>");
        List<ImSimrsObatEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }

        hsCriteria.put("flag", "Y");
        try {
            results = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatInapBoImpl.getListObatEntity] Erro when searching data obat ", e);
        }

        logger.info("[ObatInapBoImpl.getListObatEntity] End <<<<<<");
        return results;
    }

    private List<ImSimrsJenisObatEntity> getListJenisObatEntity(JenisObat bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.getListJenisObatEntity] Start >>>>>>>");
        List<ImSimrsJenisObatEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
            hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
        }

        hsCriteria.put("flag", "Y");
        try {
            results = jenisObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatInapBoImpl.getListJenisObatEntity] Erro when searching data jenis obat ", e);
        }

        logger.info("[ObatInapBoImpl.getListJenisObatEntity] End <<<<<<");
        return results;
    }

//    private void updateStokObat(ObatInap bean) throws GeneralBOException {
//        logger.info("[ObatInapBoImpl.updateStokObat] Start >>>>>>>");
//
//        if (bean != null) {

//            ImSimrsObatEntity entity = null;
//
//            try {
//                entity = obatDao.getById("idObat", bean.getIdObat());
//            } catch (HibernateException e){
//                logger.error("[TeamDokterBoImpl.updateStokObat] Error when update stok obatp ",e);
//                throw new GeneralBOException("[TeamDokterBoImpl.updateStokObat] Error when update stok obat"+e.getMessage());
//            }
//            ObatPoli obatPoli = new ObatPoli();
//            obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
//            obatPoli.setIdObat(bean.getIdObat());
//            obatPoli.setBranchId(CommonUtil.userBranchLogin());
//            obatPoli.setFlag("Y");
//
//            MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);
//
//            ImSimrsObatEntity obatEntity = getObatById(bean.getIdObat());
//
//            if(obatPoliEntity != null){
//
//                if(obatEntity != null){
//            ImtSimrsTransaksiObatDetailEntity entity = new ImtSimrsTransaksiObatDetailEntity();
//            if ("new".equalsIgnoreCase(bean.getCekQty())) {
//                updateAddStockPoli(entity);
//            }
//
//            if ("plus".equalsIgnoreCase(bean.getCekQty())) {
//                obatPoliEntity.setQty(obatPoliEntity.getQty().add(bean.getQty()));
//            }
//
//            if ("mines".equalsIgnoreCase(bean.getCekQty())) {
//                obatPoliEntity.setQty(obatPoliEntity.getQty().subtract(bean.getQty()));
//            }

//                }
//
//                obatPoliEntity.setLastUpdate(bean.getLastUpdate());
//                obatPoliEntity.setLastUpdateWho(bean.getLastUpdateWho());
//            }
//
//            try {
//                obatPoliDao.updateAndSave(obatPoliEntity);
//            } catch (HibernateException e){
//                logger.error("[DiagnosaRawatBoImpl.updateStokObat] Error when update stok obat ", e);
//                throw new GeneralBOException("Error when update stok obat " + e.getMessage());
//            }
//        }
//        logger.info("[ObatInapBoImpl.updateStokObat] End <<<<<<");
//    }

    private MtSimrsObatPoliEntity getObaPolitById(ObatPoli bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.getObaPolitById] START >>>>>>>>>>");
        List<MtSimrsObatPoliEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", bean.getIdObat());
        hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
        hsCriteria.put("branch_id", bean.getBranchId());
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = obatPoliDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatInapBoImpl.getObaPolitById] ERROR when get obat entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
            return obatEntities.get(0);
        }

        logger.info("[ObatInapBoImpl.getObaPolitById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsObatEntity getObatById(String id) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.getObatById] START >>>>>>>>>>");
        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", id);
        hsCriteria.put("branch_id", CommonUtil.userBranchLogin());
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatInapBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
            throw new GeneralBOException("[ObatInapBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
            return obatEntities.get(0);
        }

        logger.info("[ObatInapBoImpl.getObatById] END <<<<<<<<<<");
        return null;
    }

    private void updateAddStockPoli(ImtSimrsTransaksiObatDetailEntity bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.updateAddStockPoli] START >>>>>>>>>>");

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        obatPoli.setIdObat(bean.getIdObat());
        obatPoli.setBranchId(CommonUtil.userBranchLogin());
        obatPoli.setFlag("Y");

        MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);
        ImSimrsObatEntity obatEntity = getObatById(bean.getIdObat());

        if (obatPoliEntity != null && obatEntity != null) {

            //sodiq, antisipasi jikalau nilai qty terdapat null
            BigInteger qtyBox = new BigInteger(String.valueOf(0));
            BigInteger qtyLembar = new BigInteger(String.valueOf(0));
            BigInteger qtyBiji = new BigInteger(String.valueOf(0));

            if (obatPoliEntity.getQtyBox() != null) {
                qtyBox = obatPoliEntity.getQtyBox();
            }
            if (obatPoliEntity.getQtyLembar() != null) {
                qtyLembar = obatPoliEntity.getQtyLembar();
            }
            if (obatPoliEntity.getQtyBiji() != null) {
                qtyBiji = obatPoliEntity.getQtyBiji();
            }

            if ("box".equalsIgnoreCase(bean.getJenisSatuan())) {
                obatPoliEntity.setQtyBox(bean.getQtyApprove().add(qtyBox));
            }
            if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())) {
                if (bean.getQtyApprove().compareTo(obatEntity.getLembarPerBox()) == 1) {

                    BigInteger lembarToBox = bean.getQtyApprove().divide(obatEntity.getLembarPerBox());
                    BigInteger sisaLembar = bean.getQtyApprove().mod(obatEntity.getLembarPerBox());

                    obatPoliEntity.setQtyBox(qtyBox.add(lembarToBox));
                    obatPoliEntity.setQtyLembar(qtyLembar.add(sisaLembar));
                } else {
                    obatPoliEntity.setQtyLembar(qtyLembar.add(bean.getQtyApprove()));
                }
            }
            if ("biji".equalsIgnoreCase(bean.getJenisSatuan())) {
                obatPoliEntity.setQtyBiji(qtyBiji.add(bean.getQtyApprove()));
            }

            obatPoliEntity.setAction("U");
            obatPoliEntity.setLastUpdate(bean.getLastUpdate());
            obatPoliEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                obatPoliDao.updateAndSave(obatPoliEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.updateAddStockPoli] ERROR when update master obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.updateAddStockPoli] ERROR when update master obat. ", e);
            }
        }

        logger.info("[ObatPoliBoImpl.updateAddStockGudang] END <<<<<<<<<<");
    }

    private void updateSubstractStockObatPoli(ImtSimrsTransaksiObatDetailEntity bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.updateAddStockObatPoli] START >>>>>>>>>>");

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        obatPoli.setIdObat(bean.getIdObat());
        obatPoli.setBranchId(CommonUtil.userBranchLogin());
        obatPoli.setFlag("Y");

        MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);

        ImSimrsObatEntity obatEntity = getObatById(bean.getIdObat());


        if (obatPoliEntity != null) {

            //sodiq, antisipasi jikalau nilai qty terdapat null
            BigInteger qtyBox = new BigInteger(String.valueOf(0));
            BigInteger qtyLembar = new BigInteger(String.valueOf(0));
            BigInteger qtyBiji = new BigInteger(String.valueOf(0));

            if (obatPoliEntity.getQtyBox() != null) {
                qtyBox = obatPoliEntity.getQtyBox();
            }
            if (obatPoliEntity.getQtyLembar() != null) {
                qtyLembar = obatPoliEntity.getQtyLembar();
            }
            if (obatPoliEntity.getQtyBiji() != null) {
                qtyBiji = obatPoliEntity.getQtyBiji();
            }

            if ("box".equalsIgnoreCase(bean.getJenisSatuan())) {
                obatPoliEntity.setQtyBox(qtyBox.subtract(bean.getQtyApprove()));
            }

            if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())) {
                // jika stock lebih besar dari permintaan reture
                if (qtyLembar.compareTo(bean.getQtyApprove()) == 1) {
                    obatPoliEntity.setQtyLembar(qtyLembar.subtract(bean.getQtyApprove()));
                } else {

                    BigInteger lembarPermintaanToBox = bean.getQtyApprove().divide(obatEntity.getLembarPerBox());

                    if (lembarPermintaanToBox.compareTo(new BigInteger(String.valueOf(1))) == 0) {
                        obatPoliEntity.setQtyBox(qtyBox.subtract(new BigInteger(String.valueOf(1))));
                        BigInteger sisaPerLembar = obatEntity.getLembarPerBox().subtract(bean.getQtyApprove());
                        obatPoliEntity.setQtyLembar(sisaPerLembar);
                    } else {

                        BigInteger jmlLembarStock = (qtyBox.multiply(obatEntity.getLembarPerBox())).add(qtyLembar);
                        BigInteger jmlLembarPermintaan = bean.getQtyApprove();
                        BigInteger jmlStockPengurangan = jmlLembarStock.subtract(jmlLembarPermintaan);

                        if (jmlStockPengurangan.compareTo(new BigInteger(String.valueOf(0))) == 1) {
                            BigInteger lembarToBox = jmlStockPengurangan.divide(obatEntity.getLembarPerBox());
                            BigInteger sisaLembar = jmlStockPengurangan.mod(obatEntity.getLembarPerBox());

                            obatPoliEntity.setQtyBox(lembarToBox);
                            obatPoliEntity.setQtyLembar(sisaLembar);
                        } else {
                            logger.error("[ObatPoliBoImpl.updateSubstractStockObatPoli] jumlah yang diminta melebihi stock");
                            throw new GeneralBOException("[ObatPoliBoImpl.updateSubstractStockObatPoli] jumlah yang diminta melebihi stock");
                        }
                    }
                }
            }

            if ("biji".equalsIgnoreCase(bean.getJenisSatuan())) {
                if (qtyBiji.compareTo(bean.getQtyApprove()) == 1) {
                    obatPoliEntity.setQtyBiji(qtyBiji.subtract(bean.getQtyApprove()));
                } else {
                    if (obatEntity.getBijiPerLembar().compareTo(bean.getQtyApprove()) == 1 && obatPoliEntity.getQtyLembar().compareTo(new BigInteger(String.valueOf(0))) == 1) {
                        obatPoliEntity.setQtyLembar(qtyLembar.subtract(new BigInteger(String.valueOf(1))));
                        obatPoliEntity.setQtyBiji((qtyBiji.add(obatEntity.getBijiPerLembar())).subtract(bean.getQtyApprove()));
                    } else {
                        BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());
                        BigInteger jmlAllStockBiji = (qtyBox.multiply(cons))
                                .add(qtyLembar.multiply(obatEntity.getBijiPerLembar()))
                                .add(qtyBiji);

                        BigInteger sisaAllPengurangan = jmlAllStockBiji.subtract(bean.getQtyApprove());

                        if (sisaAllPengurangan.compareTo(new BigInteger(String.valueOf(0))) == 1) {

                            BigInteger bijiToBox = sisaAllPengurangan.divide(cons);
                            obatPoliEntity.setQtyBox(bijiToBox);

                            BigInteger boxToBiji = bijiToBox.multiply(cons);
                            BigInteger sisaBiji = sisaAllPengurangan.subtract(boxToBiji);
                            BigInteger sisaBijiToLembar = sisaBiji.divide(obatEntity.getBijiPerLembar());

                            BigInteger modSisaBiji = sisaBiji.mod(obatEntity.getBijiPerLembar());
                            obatPoliEntity.setQtyLembar(sisaBijiToLembar);
                            obatPoliEntity.setQtyBiji(modSisaBiji);


                        } else {
                            logger.error("[ObatPoliBoImpl.updateSubstractStockObatPoli] jumlah yang diminta melebihi stock");
                            throw new GeneralBOException("[ObatPoliBoImpl.updateSubstractStockObatPoli] jumlah yang diminta melebihi stock");
                        }
                    }
                }
            }

            obatPoliEntity.setAction("U");
            obatPoliEntity.setLastUpdate(bean.getLastUpdate());
            obatPoliEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                obatPoliDao.updateAndSave(obatPoliEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat poli. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat poli. ", e);
            }
        }
        logger.info("[ObatPoliBoImpl.updateAddStockObatPoli] END <<<<<<<<<<");
    }
}
