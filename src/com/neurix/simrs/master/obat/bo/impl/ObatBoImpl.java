package com.neurix.simrs.master.obat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisobat.dao.JenisObatDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.dao.ReturObatDao;
import com.neurix.simrs.master.obat.dao.ReturObatDetailDao;
import com.neurix.simrs.master.obat.model.*;
import com.neurix.simrs.master.obatgejala.dao.ObatGejalaDao;
import com.neurix.simrs.master.obatgejala.model.ImSimrsObatGejalaEntity;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.hargaobat.dao.HargaObatDao;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.obatinap.model.ItSimrsObatInapEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailBatchDao;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsTransaksiObatDetailBatchEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatBatch;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
//import javafx.collections.ObservableList;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObatBoImpl implements ObatBo {

    protected static transient Logger logger = Logger.getLogger(ObatBoImpl.class);
    private ObatDao obatDao;
    private JenisObatDao jenisObatDao;
    private ObatGejalaDao obatGejalaDao;
    private TransaksiObatDetailBatchDao batchDao;
    private HargaObatDao hargaObatDao;
    private ReturObatDao returObatDao;
    private ReturObatDetailDao returObatDetailDao;

    public void setReturObatDao(ReturObatDao returObatDao) {
        this.returObatDao = returObatDao;
    }

    public void setReturObatDetailDao(ReturObatDetailDao returObatDetailDao) {
        this.returObatDetailDao = returObatDetailDao;
    }

    public void setBatchDao(TransaksiObatDetailBatchDao batchDao) {
        this.batchDao = batchDao;
    }

    public void setJenisObatDao(JenisObatDao jenisObatDao) {
        this.jenisObatDao = jenisObatDao;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setObatGejalaDao(ObatGejalaDao obatGejalaDao) {
        this.obatGejalaDao = obatGejalaDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setHargaObatDao(HargaObatDao hargaObatDao) {
        this.hargaObatDao = hargaObatDao;
    }

    @Override
    public List<Obat> getByCriteria(Obat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getByCriteria] Start >>>>>>>");

        List<Obat> result = new ArrayList<>();
        if (bean != null) {

            List<ImSimrsObatEntity> obatEntityList = getListEntityObat(bean);

            if (!obatEntityList.isEmpty()) {
                Obat obat;
                for (ImSimrsObatEntity obatEntity : obatEntityList) {
                    obat = new Obat();
                    obat.setIdObat(obatEntity.getIdObat());
                    obat.setIdSeqObat(obatEntity.getIdSeqObat());
                    obat.setIdJenisObat(obatEntity.getIdJenisObat());
                    obat.setNamaObat(obatEntity.getNamaObat());
                    obat.setHarga(obatEntity.getHarga());
                    obat.setFlag(obatEntity.getFlag());
                    obat.setAction(obatEntity.getAction());
                    obat.setCreatedDate(obatEntity.getCreatedDate());
                    obat.setCreatedWho(obatEntity.getCreatedWho());
                    obat.setLastUpdate(obatEntity.getLastUpdate());
                    obat.setLastUpdateWho(obatEntity.getLastUpdateWho());
                    obat.setQty(obatEntity.getQty());
                    obat.setBranchId(obatEntity.getBranchId());
                    obat.setQtyBox(obatEntity.getQtyBox());
                    obat.setLembarPerBox(obatEntity.getLembarPerBox());
                    obat.setQtyLembar(obatEntity.getQtyLembar());
                    obat.setBijiPerLembar(obatEntity.getBijiPerLembar());
                    obat.setQtyBiji(obatEntity.getQtyBiji());
                    obat.setAverageHargaBox(obatEntity.getAverageHargaBox());
                    obat.setAverageHargaLembar(obatEntity.getAverageHargaLembar());
                    obat.setAverageHargaBiji(obatEntity.getAverageHargaBiji());
                    obat.setIdPabrik(obatEntity.getIdPabrik());
                    obat.setMerk(obatEntity.getMerk());
                    obat.setExpiredDate(obatEntity.getExpiredDate());
                    obat.setIdBarang(obatEntity.getIdBarang());
                    obat.setIdSeqObat(obatEntity.getIdSeqObat());

                    obat.setIdTransaksiDetail(bean.getIdTransaksiDetail());

                    List<ImSimrsObatGejalaEntity> obatGejalaEntities = new ArrayList<>();

                    Map hsCriteria = new HashMap();
                    hsCriteria.put("id_obat", obatEntity.getIdObat());
                    hsCriteria.put("flag", "Y");

                    try {
                        obatGejalaEntities = obatGejalaDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[ObatBoImpl.updateObatGejala] error when get data obat gejala " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when get data obat gejala " + e.getMessage());
                    }

                    if (!obatGejalaEntities.isEmpty() && obatGejalaEntities.size() > 0) {
                        String listJenisObat = "";
                        for (ImSimrsObatGejalaEntity gejalaEntity : obatGejalaEntities) {
                            StringBuilder addedScript = new StringBuilder();
                            JenisObat jenisObat = new JenisObat();
                            jenisObat.setIdJenisObat(gejalaEntity.getIdJenisObat());
                            List<ImSimrsJenisObatEntity> jenisObatEntityList = getListEntityJenisObat(jenisObat);

                            if (!jenisObatEntityList.isEmpty()) {
                                ImSimrsJenisObatEntity jenisObatEntity = jenisObatEntityList.get(0);
                                if (jenisObatEntity != null) {
                                    listJenisObat = listJenisObat + addedScript.append("<label class=\"label label-primary\">" + jenisObatEntity.getNamaJenisObat() + "</label>");
                                }

                            }
                        }
                        obat.setJenisObat(listJenisObat.toString());
                    }
                    result.add(obat);
                }
            }
            logger.info("[ObatBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[ObatBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }

    public List<ImSimrsJenisObatEntity> getListEntityJenisObat(JenisObat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListEntityJenisObat] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
                hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsJenisObatEntity> jenisObatEntityList = new ArrayList<>();

            try {
                jenisObatEntityList = jenisObatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ObatBoImpl.getListEntityJenisObat] error when get data jenis obat by get by criteria " + e.getMessage());
            }

            logger.info("[ObatBoImpl.getListEntityJenisObat] End <<<<<<<");
            return jenisObatEntityList;
        }
        logger.info("[ObatBoImpl.getListEntityJenisObat] End <<<<<<<");
        return null;
    }

    @Override
    public List<Obat> getListObatByJenisObat(String idObat, String branchId) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListObatByJenisObat] Start >>>>>>>");

        List<Obat> obats = new ArrayList<>();

        try {
            obats = obatDao.getListObatByJenisObat(idObat, branchId);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getListEntityJenisObat] error when get data obat by jenis obat " + e.getMessage());
        }

        logger.info("[ObatBoImpl.getListObatByJenisObat] End <<<<<<<");
        return obats;
    }

    @Override
    public List<Obat> getJenisObat(Obat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getJenisObat] Start >>>>>>>");

        List<Obat> obats = new ArrayList<>();

        try {
            obats = obatDao.getJenisObat(bean);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getJenisObat] error when get data obat by jenis obat " + e.getMessage());
        }

        logger.info("[ObatBoImpl.getJenisObat] End <<<<<<<");
        return obats;
    }

    @Override
    public List<ImSimrsObatEntity> getListEntityObat(Obat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListEntityObat] Start >>>>>>>");

        Map hsCriteria = new HashMap();

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
            hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
        }
        if (bean.getNamaObat() != null && !"".equalsIgnoreCase(bean.getNamaObat())) {
            hsCriteria.put("nama_obat", bean.getNamaObat());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            hsCriteria.put("flag", bean.getFlag());
        }
        if (bean.getIdPabrik() != null && !"".equalsIgnoreCase(bean.getIdPabrik())) {
            hsCriteria.put("id_pabrik", bean.getIdPabrik());
        }
        if (bean.getIdSeqObat() != null && !"".equalsIgnoreCase(bean.getIdSeqObat())) {
            hsCriteria.put("id_squen", bean.getIdSeqObat());
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_barang", bean.getIdObat());
        }

        List<ImSimrsObatEntity> obatEntityList = new ArrayList<>();
        try {
            obatEntityList = obatDao.getListObatByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getByCriteria] error when get data obat by get by criteria " + e.getMessage());
        }

        logger.info("[ObatBoImpl.getListEntityObat] End <<<<<<<");
        return obatEntityList;
    }

    private List<ImSimrsObatEntity> getListObatEntity(Obat bean) {
        logger.info("[ObatBoImpl.getListEntityObat] Start >>>>>>>");

        List<ImSimrsObatEntity> simrsObatEntityList = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdSeqObat() != null && !"".equalsIgnoreCase(bean.getIdSeqObat())) {
            hsCriteria.put("id_seq_obat", bean.getIdSeqObat());
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getIdBarang() != null && !"".equalsIgnoreCase(bean.getIdBarang())) {
            hsCriteria.put("id_barang", bean.getIdBarang());
        }

        hsCriteria.put("flag", "Y");

        try {
            simrsObatEntityList = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getListObatEntity] error when get data obat by get by criteria " + e.getMessage());
        }

        logger.info("[ObatBoImpl.getListEntityObat] End <<<<<<<");
        return simrsObatEntityList;
    }


    @Override
    public void saveAdd(Obat bean, List<String> idJenisObats) throws GeneralBOException {
        logger.info("[ObatBoImpl.saveAdd] Start >>>>>>>");

        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();

        String id = getIdNextObat();
        String idSeqObat = getIdNextSeqObat();

        obatEntity.setIdSeqObat(idSeqObat);
        obatEntity.setIdObat("OBT" + id);
        obatEntity.setNamaObat(bean.getNamaObat());
        obatEntity.setHarga(bean.getHarga());
        obatEntity.setQty(bean.getQty());
        obatEntity.setBranchId(bean.getBranchId());
        obatEntity.setMerk(bean.getMerk());
        obatEntity.setIdPabrik(bean.getIdPabrik());
        obatEntity.setQtyBox(bean.getQtyBox());
        obatEntity.setQtyLembar(bean.getQtyLembar());
        obatEntity.setLembarPerBox(bean.getLembarPerBox());
        obatEntity.setBijiPerLembar(bean.getBijiPerLembar());
        obatEntity.setQtyBiji(bean.getQtyBiji());
        obatEntity.setFlag(bean.getFlag());
        obatEntity.setAction(bean.getAction());
        obatEntity.setCreatedDate(time);
        obatEntity.setCreatedWho(userLogin);
        obatEntity.setLastUpdate(time);
        obatEntity.setLastUpdateWho(userLogin);
        obatEntity.setQtyBox(bean.getQtyBox());
        obatEntity.setLembarPerBox(bean.getLembarPerBox());
        obatEntity.setQtyLembar(bean.getQtyLembar());
        obatEntity.setBijiPerLembar(bean.getBijiPerLembar());
        obatEntity.setQtyBiji(bean.getQtyBiji());
        obatEntity.setAverageHargaBox(bean.getAverageHargaBox());
        obatEntity.setAverageHargaLembar(bean.getAverageHargaLembar());
        obatEntity.setAverageHargaBiji(bean.getAverageHargaBiji());
        obatEntity.setIdPabrik(bean.getIdPabrik());
        obatEntity.setMerk(bean.getMerk());
        obatEntity.setMinStok(bean.getMinStok());

        try {
            obatDao.addAndSave(obatEntity);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.saveAdd] error when add data obat " + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.saveAdd] error when add data obat " + e.getMessage());
        }

        if (!idJenisObats.isEmpty() && idJenisObats.size() > 0) {
            for (String idJenisObat : idJenisObats) {
                ImSimrsObatGejalaEntity obatGejalaEntity = new ImSimrsObatGejalaEntity();

                id = getIdNextObatGejala();

                obatGejalaEntity.setIdObatGejala("OGJ" + id);
                obatGejalaEntity.setIdObat(obatEntity.getIdObat());
                obatGejalaEntity.setIdJenisObat(idJenisObat);
                obatGejalaEntity.setFlag(bean.getFlag());
                obatGejalaEntity.setAction(bean.getAction());
                obatGejalaEntity.setCreatedDate(time);
                obatGejalaEntity.setCreatedWho(userLogin);
                obatGejalaEntity.setLastUpdate(time);
                obatGejalaEntity.setLastUpdateWho(userLogin);

                try {
                    obatGejalaDao.addAndSave(obatGejalaEntity);
                } catch (HibernateException e) {
                    logger.error("[ObatBoImpl.saveAdd] error when insert new obat gejala " + e.getMessage());
                    throw new GeneralBOException("[ObatBoImpl.saveAdd] error when insert new obat gejala " + e.getMessage());
                }
            }
        }
        logger.info("[ObatBoImpl.saveAdd] End <<<<<<<");
    }

    @Override
    public CheckObatResponse saveEdit(Obat bean, List<String> idJenisObats) throws GeneralBOException {
        logger.info("[ObatBoImpl.saveEdit] Start >>>>>>>");

        CheckObatResponse response = new CheckObatResponse();

        if (bean != null) {

            Obat obat = new Obat();
            obat.setIdObat(bean.getIdObat());
            List<ImSimrsObatEntity> entityList = getListObatEntity(obat);

            if(entityList.size()>0){
                for (ImSimrsObatEntity obatEntity: entityList){

                    obatEntity.setNamaObat(bean.getNamaObat());
                    obatEntity.setLastUpdate(bean.getLastUpdate());
                    obatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    obatEntity.setLembarPerBox(bean.getLembarPerBox());
                    obatEntity.setBijiPerLembar(bean.getBijiPerLembar());
                    obatEntity.setIdPabrik(bean.getIdPabrik());
                    obatEntity.setMerk(bean.getMerk());
                    obatEntity.setAction(bean.getAction());
                    obatEntity.setMinStok(bean.getMinStok());

                    try {
                        obatDao.updateAndSave(obatEntity);
                        response.setStatus("success");
                        response.setMessage("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error when update obat "+e.getMessage());
                        logger.error("[ObatBoImpl.saveEdit] error when update data obat " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.saveEdit] error when update data obat " + e.getMessage());
                    }
                }
                updateObatGejala(idJenisObats, bean.getIdObat());
            }
        }

        return response;
    }

    @Override
    public List<Obat> getListNamaObat(Obat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListNamaObat] Start >>>>>>>");
        List<Obat> listOfResult = new ArrayList();
        try {
            listOfResult = obatDao.getListNamaObat(bean);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getListNamaObat Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        logger.info("[ObatBoImpl.getListNamaObat] End >>>>>>>");
        return listOfResult;

    }

    private void updateObatGejala(List<String> idJenisObats, String idObat) throws GeneralBOException {
        logger.info("[ObatBoImpl.updateObatGejala] Start >>>>>>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();

        List<ImSimrsObatGejalaEntity> obatGejalaEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", idObat);
        hsCriteria.put("flag", "Y");

        try {
            obatGejalaEntities = obatGejalaDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.updateObatGejala] error when get data obat gejala " + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when get data obat gejala " + e.getMessage());
        }

        if (!obatGejalaEntities.isEmpty() && obatGejalaEntities.size() > 0) {
            for (ImSimrsObatGejalaEntity gejalaEntity : obatGejalaEntities) {
                gejalaEntity.setFlag("N");
                gejalaEntity.setAction("U");
                gejalaEntity.setCreatedDate(time);
                gejalaEntity.setCreatedWho(userLogin);
                gejalaEntity.setLastUpdate(time);
                gejalaEntity.setLastUpdateWho(userLogin);

                try {
                    obatGejalaDao.updateAndSave(gejalaEntity);
                } catch (HibernateException e) {
                    logger.error("[ObatBoImpl.updateObatGejala] error when update flag N obat gejala " + e.getMessage());
                    throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when update flag N obat gejala " + e.getMessage());
                }
            }
        }

        for (String idJenisObat : idJenisObats) {
            obatGejalaEntities = new ArrayList<>();
            hsCriteria = new HashMap();

            hsCriteria.put("id_obat", idObat);
            hsCriteria.put("id_jenis_obat", idJenisObat);

            try {
                obatGejalaEntities = obatGejalaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ObatBoImpl.updateObatGejala] error when get data obat gejala " + e.getMessage());
                throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when get data obat gejala " + e.getMessage());
            }

            if (!obatGejalaEntities.isEmpty() && obatGejalaEntities.size() > 0) {
                ImSimrsObatGejalaEntity entity = obatGejalaEntities.get(0);

                entity.setFlag("Y");
                entity.setAction("U");
                entity.setCreatedDate(time);
                entity.setCreatedWho(userLogin);
                entity.setLastUpdate(time);
                entity.setLastUpdateWho(userLogin);

                try {
                    obatGejalaDao.updateAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[ObatBoImpl.updateObatGejala] error when update flag Y obat gejala " + e.getMessage());
                    throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when when update flag Y obat gejala " + e.getMessage());
                }
            } else {
                ImSimrsObatGejalaEntity entity = new ImSimrsObatGejalaEntity();

                String id = getIdNextObatGejala();

                entity.setIdObatGejala("OGJ" + id);
                entity.setIdObat(idObat);
                entity.setIdJenisObat(idJenisObat);
                entity.setFlag("Y");
                entity.setAction("U");
                entity.setCreatedDate(time);
                entity.setCreatedWho(userLogin);
                entity.setLastUpdate(time);
                entity.setLastUpdateWho(userLogin);

                try {
                    obatGejalaDao.addAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[ObatBoImpl.updateObatGejala] error when insert new obat gejala " + e.getMessage());
                    throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when insert new obat gejala " + e.getMessage());
                }
            }
        }
        logger.info("[ObatBoImpl.updateObatGejala] End <<<<<<<");
    }

    @Override
    public CheckObatResponse checkObatStockLama(String idObat, String branchId) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.checkObatStockLama] START >>>>>>>>>>");
        logger.info("[ObatPoliBoImpl.checkObatStockLama] END <<<<<<<<<<");
        return null;
    }

    @Override
    public CheckObatResponse checkFisikObat(Obat bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.checkFisikObat] START >>>>>>>>>>");

        CheckObatResponse response = new CheckObatResponse();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", bean.getIdObat());
        hsCriteria.put("id_pabrik", bean.getIdPabrik());
        hsCriteria.put("branch_id", bean.getBranchId());
        hsCriteria.put("desc", "Y");
        hsCriteria.put("flag", "Y");


        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();
        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.checkFisikObat] error when check fisik obat" + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.checkFisikObat] check fisik obat " + e.getMessage());
        }

        if (obatEntities.size() > 0) {
            ImSimrsObatEntity obatEntity = obatEntities.get(0);

            if (obatEntity.getLembarPerBox().compareTo(bean.getLembarPerBox()) == 0 && obatEntity.getBijiPerLembar().compareTo(bean.getBijiPerLembar()) == 0) {
                response.setStatus("success");
                response.setMessage("Obat Sesuai");
            } else {
                response.setStatus("warning");

                StringBuilder r = new StringBuilder();
                r.append("Obat teridentifikasi berubah fisik");
                r.append("<br>");
                r.append("Kode produksi : " + obatEntity.getIdPabrik());
                r.append("<br>");
                r.append("Nama : " + obatEntity.getNamaObat());
                r.append("<br>");
                r.append("Merk : " + obatEntity.getMerk());
                r.append("<br>");
                r.append("Lembar/Box : " + obatEntity.getLembarPerBox());
                r.append("<br>");
                r.append("Biji/Lembar : " + obatEntity.getBijiPerLembar());
                r.append("<br>");
                r.append("Tgl diterima : " + obatEntity.getCreatedDate());

                response.setMessage(String.valueOf(r));
            }
        } else {
            response.setStatus("new");

            StringBuilder r = new StringBuilder();
            r.append("Obat teridentifikasi baru");
            r.append("Karna tidak ditemukan kecocokan kode produksi pada data master");
            response.setMessage(String.valueOf(r));
        }

        logger.info("[ObatPoliBoImpl.checkFisikObat] END <<<<<<<<<<");
        return response;
    }

    @Override
    public CheckObatResponse checkFisikObatByIdPabrik(Obat bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.checkFisikObatByIdPabrik] START >>>>>>>>>>");

        CheckObatResponse response = new CheckObatResponse();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pabrik", bean.getIdPabrik());
        hsCriteria.put("branch_id", bean.getBranchId());
        hsCriteria.put("lembar_per_box", bean.getLembarPerBox());
        hsCriteria.put("biji_per_lembar", bean.getBijiPerLembar());
        hsCriteria.put("flag", "Y");


        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.checkFisikObat] error when check fisik obat" + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.checkFisikObatByIdPabrik] check fisik obat " + e.getMessage());
        }

        if (obatEntities.size() > 0) {
            ImSimrsObatEntity obatEntity = obatEntities.get(0);
            response.setStatus("warning");
            response.setMessage(obatEntity.getIdObat());
        } else {
            response.setStatus("success");
            response.setMessage("Silahkan dilanjutkan");
        }

        logger.info("[ObatPoliBoImpl.checkFisikObatByIdPabrik] END <<<<<<<<<<");
        return response;
    }

    @Override
    public List<Obat> sortedListObat(List<Obat> obatList) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.sortedListObat] START >>>>>>>>>>");

        List<Obat> obats = new ArrayList<>();
        for (Obat obat : obatList) {

            TransaksiObatBatch obatBatch = new TransaksiObatBatch();
            obatBatch.setIdTransaksiObatDetail(obat.getIdTransaksiDetail());
            obatBatch.setExpiredDate(obat.getExpiredDate());

            List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatch(obatBatch);
            if (batchEntities.size() > 0) {
                MtSimrsTransaksiObatDetailBatchEntity batchEntity = new MtSimrsTransaksiObatDetailBatchEntity();
                obat.setQtyApprove(batchEntity.getQtyApprove());
                obat.setNoBatch(1);
            }

            obats.add(obat);
        }

        logger.info("[ObatPoliBoImpl.sortedListObat] END <<<<<<<<<<");
        return obats;
    }

    @Override
    public List<Obat> getEntityObatByCriteria(Obat bean) throws GeneralBOException {

        List<Obat> result = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getNamaObat() != null && !"".equalsIgnoreCase(bean.getNamaObat())) {
            hsCriteria.put("nama_obat", bean.getNamaObat());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            hsCriteria.put("flag", bean.getFlag());
        }
        if (bean.getIdPabrik() != null && !"".equalsIgnoreCase(bean.getIdPabrik())) {
            hsCriteria.put("id_pabrik", bean.getIdPabrik());
        }

        hsCriteria.put("exp", "Y");

        List<ImSimrsObatEntity> obatEntityList = new ArrayList<>();

        try {
            obatEntityList = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getByCriteria] error when get data obat by get by criteria " + e.getMessage());
        }

        if (obatEntityList.size() > 0) {

            Obat obat;
            for (ImSimrsObatEntity entity : obatEntityList) {
                obat = new Obat();
                obat.setIdSeqObat(entity.getIdSeqObat());
                obat.setIdObat(entity.getIdObat());
                obat.setNamaObat(entity.getNamaObat());
                obat.setBranchId(entity.getBranchId());
                obat.setMerk(entity.getMerk());
                obat.setIdPabrik(entity.getIdPabrik());
                obat.setQtyBox(entity.getQtyBox());
                obat.setQtyLembar(entity.getQtyLembar());
                obat.setQtyBiji(entity.getQtyBiji());
                obat.setLembarPerBox(entity.getLembarPerBox());
                obat.setBijiPerLembar(entity.getBijiPerLembar());
                obat.setAverageHargaBox(entity.getAverageHargaBox());
                obat.setAverageHargaLembar(entity.getAverageHargaLembar());
                obat.setAverageHargaBiji(entity.getAverageHargaBiji());
                obat.setHargaTerakhir(entity.getHargaTerakhir());
                obat.setExpiredDate(entity.getExpiredDate());
                obat.setIdBarang(entity.getIdBarang());
                result.add(obat);
            }

        }

        return result;
    }

    private List<MtSimrsTransaksiObatDetailBatchEntity> getListEntityBatch(TransaksiObatBatch bean) {
        logger.info("[ObatPoliBoImpl.getListEntityBatch] START >>>>>>>>>>");

        Map hsCriteria = new HashMap();

        if (bean.getIdTransaksiObatDetail() != null) {
            hsCriteria.put("id_transaksi_obat_detail", bean.getIdTransaksiObatDetail());
        }

        if (bean.getExpiredDate() != null) {
            hsCriteria.put("exp_date", bean.getExpiredDate());
        }

        if (bean.getNoBatch() != null) {
            hsCriteria.put("no_batch", bean.getNoBatch());
        }

        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = new ArrayList<>();

        try {
            batchEntities = batchDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getIdNextObatGejala] ERROR, " + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getIdNextObatGejala] ERROR, " + e.getMessage());
        }

        logger.info("[ObatPoliBoImpl.getListEntityBatch] END <<<<<<<<<<");
        return batchEntities;
    }

    @Override
    public List<Obat> getListObatGroup(Obat bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListObatGroup] START >>>>>>>>>>");

        List<Obat> obatList = new ArrayList<>();

        List<String> listIdObat = new ArrayList<>();
        try {
            listIdObat = obatDao.getListIdObatGroupByBranchId(bean.getBranchId());
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getListObatGroup] ERROR, " + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getListObatGroup] ERROR, " + e.getMessage());
        }

        Obat obat;
        for (String idObat : listIdObat) {
            obat = new Obat();

            try {
                obat = obatDao.getLastIdSeqObat(idObat);
            } catch (HibernateException e) {
                logger.error("[ObatBoImpl.getListObatGroup] ERROR, " + e.getMessage());
                throw new GeneralBOException("[ObatBoImpl.getListObatGroup] ERROR, " + e.getMessage());
            }

            if (obat != null) {
                obat.setIdObat(idObat);
                List<ImSimrsObatEntity> obatEntities = getListObatEntity(obat);

                if (obatEntities.size() > 0) {
                    ImSimrsObatEntity obatEntity = obatEntities.get(0);

                    obat.setIdSeqObat(obatEntity.getIdSeqObat());
                    obat.setIdPabrik(obatEntity.getIdPabrik());
                    obat.setNamaObat(obatEntity.getNamaObat());
                    obat.setIdBarang(obatEntity.getIdBarang());
                    obat.setLembarPerBox(obatEntity.getLembarPerBox());
                    obat.setBijiPerLembar(obatEntity.getBijiPerLembar());
                    obat.setAverageHargaBox(obatEntity.getAverageHargaBox());
                    obat.setAverageHargaLembar(obatEntity.getAverageHargaLembar());
                    obat.setAverageHargaBiji(obatEntity.getAverageHargaBiji());
                    obat.setBranchId(obatEntity.getBranchId());

                    Obat sumObat = new Obat();
                    try {
                        sumObat = obatDao.getSumStockObatGudangById(idObat);
                    } catch (HibernateException e) {
                        logger.error("[ObatBoImpl.getListObatGroup] ERROR, " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.getListObatGroup] ERROR, " + e.getMessage());
                    }

                    if (sumObat != null) {
                        obat.setQtyBox(sumObat.getQtyBox());
                        obat.setQtyLembar(sumObat.getQtyLembar());
                        obat.setQtyBiji(sumObat.getQtyBiji());
                    }
                }
            }

            obatList.add(obat);
        }

        logger.info("[ObatPoliBoImpl.getListObatGroup] END <<<<<<<<<<");
        return obatList;
    }

    @Override
    public ImSimrsObatEntity getObatByIdBarang(String idBarang) throws GeneralBOException {
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_barang", idBarang);
        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();
        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getObatByIdBarang] ERROR, " + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getObatByIdBarang] ERROR, " + e.getMessage());
        }

        if (obatEntities.size() > 0) {
            return obatEntities.get(0);
        }
        return null;
    }

    private String getIdNextObatGejala() throws GeneralBOException {
        String id = "";

        try {
            id = obatGejalaDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getIdNextObatGejala] ERROR WHEN GET data id obat gejala, " + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getIdNextObatGejala] ERROR WHEN GET data id obat gejala, " + e.getMessage());
        }

        return id;
    }

    private String getIdNextObat() throws GeneralBOException {
        String id = "";

        try {
            id = obatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getIdNextObat] ERROR WHEN GET data id obat, " + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getIdNextObat] ERROR WHEN GET data id obat, " + e.getMessage());
        }

        return id;
    }

    private String getIdNextSeqObat() throws GeneralBOException {
        String id = "";

        try {
            id = obatDao.getNextIdSeqObat();
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getIdNextObat] ERROR WHEN GET data id seq obat, " + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getIdNextObat] ERROR WHEN GET data id seq obat, " + e.getMessage());
        }

        return id;
    }

    @Override
    public List<Obat> getListHargaObat(Obat bean) throws GeneralBOException {
        return hargaObatDao.listObatForHargaJual(bean);
    }

    @Override
    public void saveHargaObat(HargaObat bean) throws GeneralBOException {
        if (bean.getIdObat() != null && bean.getIdBarang() != null) {

            Map hsCriteria = new HashMap();
            hsCriteria.put("id_obat", bean.getIdObat());
            List<MtSimrsHargaObatEntity> hargaObatEntities = hargaObatDao.getByCriteria(hsCriteria);
            if (hargaObatEntities.size() > 0) {
                for (MtSimrsHargaObatEntity obatEntity : hargaObatEntities) {
                    obatEntity.setHargaJual(bean.getHargaJual());
                    obatEntity.setDiskon(bean.getDiskon());
                    obatEntity.setHargaNet(bean.getHargaNet());
                    obatEntity.setFlag("Y");
                    obatEntity.setAction("U");
                    obatEntity.setLastUpdate(bean.getLastUpdate());
                    obatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    try {
                        hargaObatDao.updateAndSave(obatEntity);
                    } catch (HibernateException e) {
                        logger.error("[ObatBoImpl.saveHargaObat] ERROR save update harga obat, " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.saveHargaObat] ERROR WHEN save update harga obat, " + e.getMessage());
                    }
                }
            } else {
                Obat obat = new Obat();
                obat.setIdObat(bean.getIdObat());
                obat.setIdBarang(bean.getIdBarang());
                List<ImSimrsObatEntity> obatEntities = getListObatEntity(obat);
                if (obatEntities.size() > 0) {
                    ImSimrsObatEntity obatEntity = obatEntities.get(0);

                    MtSimrsHargaObatEntity hargaObatEntity = new MtSimrsHargaObatEntity();
                    hargaObatEntity.setIdObat(obatEntity.getIdObat());
                    hargaObatEntity.setNamaObat(obatEntity.getNamaObat());
                    hargaObatEntity.setHargaBeli(obatEntity.getHargaTerakhir());
                    hargaObatEntity.setHargaJual(bean.getHargaJual());
                    hargaObatEntity.setHargaRata(obatEntity.getAverageHargaBiji());
                    hargaObatEntity.setDiskon(bean.getDiskon());
                    hargaObatEntity.setHargaNet(bean.getHargaNet());
                    hargaObatEntity.setSatuan("biji");
                    hargaObatEntity.setFlag("Y");
                    hargaObatEntity.setAction("C");
                    hargaObatEntity.setCreatedDate(bean.getCreatedDate());
                    hargaObatEntity.setCreatedWho(bean.getCreatedWho());
                    hargaObatEntity.setLastUpdate(bean.getLastUpdate());
                    hargaObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        hargaObatDao.addAndSave(hargaObatEntity);
                    } catch (HibernateException e) {
                        logger.error("[ObatBoImpl.saveHargaObat] ERROR save add harga obat, " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.saveHargaObat] ERROR WHEN save add harga obat, " + e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public List<Obat> getListObatByGroup(Obat bean) throws GeneralBOException {
        List<Obat> obatList = new ArrayList<>();

        if (bean != null) {
            try {
                obatList = obatDao.getListObatGroup(bean);
            } catch (HibernateException e) {
                logger.error("Found Error when search obat " + e.getMessage());
            }

        }
        return obatList;
    }

    @Override
    public List<Obat> getListObatDetail(Obat bean) throws GeneralBOException {
        List<Obat> obatList = new ArrayList<>();

        if (bean != null) {
            try {
                obatList = obatDao.getListObatDetail(bean);
            } catch (HibernateException e) {
                logger.error("Found Error when search obat " + e.getMessage());
            }

        }
        return obatList;
    }

    @Override
    public CheckResponse saveReturObat(Obat bean, List<Obat> obatList) throws GeneralBOException {
        CheckResponse response = new CheckResponse();

        if(bean != null){

            ItSimrsReturObatEntity returObatEntity = new ItSimrsReturObatEntity();
            returObatEntity.setIdReturObat("RTO"+returObatDao.getNextId());
            returObatEntity.setIdVendor(bean.getIdVendor());
            returObatEntity.setJumlahRetur(bean.getQty());
            returObatEntity.setTanggalRetur(bean.getTglRetur());
            returObatEntity.setBranchId(bean.getBranchId());
            returObatEntity.setAction("C");
            returObatEntity.setFlag("Y");
            returObatEntity.setCreatedWho(bean.getCreatedWho());
            returObatEntity.setCreatedDate(bean.getCreatedDate());
            returObatEntity.setLastUpdate(bean.getLastUpdate());
            returObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                returObatDao.addAndSave(returObatEntity);
                response.setStatus("success");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("Found Error "+e.getMessage());
                logger.error("Found Errro "+e.getMessage());
            }

            if(obatList.size() > 0){

                for (Obat obat: obatList){

                    ItSimrsReturObatDetailEntity returObatDetailEntity = new ItSimrsReturObatDetailEntity();
                    returObatDetailEntity.setIdReturDetail("RTD"+returObatDetailDao.getNextId());
                    returObatDetailEntity.setIdReturObat(returObatEntity.getIdReturObat());
                    returObatDetailEntity.setIdObat(obat.getIdObat());
                    returObatDetailEntity.setIdBarang(obat.getIdBarang());
                    returObatDetailEntity.setQtyRetur(obat.getQty());
                    returObatDetailEntity.setAction("C");
                    returObatDetailEntity.setFlag("Y");
                    returObatDetailEntity.setCreatedWho(bean.getCreatedWho());
                    returObatDetailEntity.setCreatedDate(bean.getCreatedDate());
                    returObatDetailEntity.setLastUpdate(bean.getLastUpdate());
                    returObatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {

                        returObatDetailDao.addAndSave(returObatDetailEntity);

                        Obat obt = new Obat();
                        obt.setIdBarang(obat.getIdBarang());
                        obt.setIdObat(obat.getIdObat());
                        obt.setQtyApprove(obat.getQty());
                        obt.setJenisSatuan("box");
                        obt.setLastUpdate(bean.getLastUpdate());
                        obt.setLastUpdateWho(bean.getLastUpdateWho());

                        response = updateSubstractStockGudang(obt);

                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMessage("Found Error "+e.getMessage());
                        logger.error("Found error "+e.getMessage());
                    }
                }
            }
        }
        return response;
    }

    @Override
    public List<Obat> searchReturObat(Obat bean) throws GeneralBOException {
        List<Obat> obatList = new ArrayList<>();
        if(bean != null){
            try {
                obatList = returObatDao.getListReturObat(bean);
            }catch (HibernateException e){
                logger.error("Found Error "+e.getMessage());
            }
        }
        return obatList;
    }

    @Override
    public List<Obat> detailReturObat(String idRetur) throws GeneralBOException {
        List<Obat> obatList = new ArrayList<>();
        if(idRetur != null){
            try {
                obatList = returObatDao.getListDetailReturObat(idRetur);
            }catch (HibernateException e){
                logger.error("Found Error "+e.getMessage());
            }
        }
        return obatList;
    }

    @Override
    public List<Obat> searchObatByVendor(String idVendor, String branchId) throws GeneralBOException {
        List<Obat> obatList = new ArrayList<>();
        if(idVendor != null && branchId != null){
            try {
                obatList = returObatDao.getListObatByVendor(idVendor, branchId);
            }catch (HibernateException e){
                logger.error("Found Error "+e.getMessage());
            }
        }
        return obatList;
    }

    private CheckResponse updateSubstractStockGudang(Obat bean) {

        CheckResponse response = new CheckResponse();

        Obat obat = new Obat();
        obat.setIdBarang(bean.getIdBarang());
        obat.setIdObat(bean.getIdObat());

        List<ImSimrsObatEntity> obatEntities = getListObatEntity(obat);

        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
        if (obatEntities.size() > 0) {
            obatEntity = obatEntities.get(0);
        }

        if (obatEntity != null) {

            //sodiq, 09-01-2019, antisipasi nilai qtybox, lembar, biji sama dengan null
            BigInteger qtyBox = new BigInteger(String.valueOf(0));
            BigInteger qtyLembar = new BigInteger(String.valueOf(0));
            BigInteger qtyBiji = new BigInteger(String.valueOf(0));

            if (obatEntity.getQtyBox() != null) {
                qtyBox = obatEntity.getQtyBox();
            }
            if (obatEntity.getQtyLembar() != null) {
                qtyLembar = obatEntity.getQtyLembar();
            }
            if (obatEntity.getQtyBiji() != null) {
                qtyBiji = obatEntity.getQtyBiji();
            }

            // BigInteger jmlh = obatEntity.getQty().subtract(transaksiObatDetail.getQtyApprove());
            if ("box".equalsIgnoreCase(bean.getJenisSatuan())) {
                BigInteger jml = qtyBox.subtract(bean.getQtyApprove());
                obatEntity.setQtyBox(jml);
            }
            if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())) {

                // jika lembar permintaan lebih kecil dari pada jumlah lembar dalam stock maka langsung mengurangi stock lembar
                if (qtyLembar.compareTo(bean.getQtyApprove()) == 1 || qtyLembar.compareTo(bean.getQtyApprove()) == 0) {
                    BigInteger jml = qtyLembar.subtract(bean.getQtyApprove());
                    obatEntity.setQtyLembar(jml);
                } else {

                    BigInteger jmlLembarStock = (obatEntity.getQtyBox().multiply(obatEntity.getLembarPerBox()))
                            .add(qtyLembar);

                    if (jmlLembarStock.compareTo(bean.getQtyApprove()) == 1) {
                        BigInteger jmlLembarPengurangan = jmlLembarStock.subtract(bean.getQtyApprove());

                        BigInteger lembarToBox = jmlLembarPengurangan.divide(obatEntity.getLembarPerBox());
                        BigInteger sisaLembar = jmlLembarPengurangan.mod(obatEntity.getLembarPerBox());

                        obatEntity.setQtyBox(lembarToBox);
                        obatEntity.setQtyLembar(sisaLembar);

                    } else {
                        response.setStatus("error");
                        response.setMessage("Found Error, jumlah yang diminta melebihi stock ");
                        logger.error("[ObatPoliBoImpl.updateSubstractStockGudang] jumlah yang diminta melebihi stock");
                        throw new GeneralBOException("[ObatPoliBoImpl.updateSubstractStockGudang] jumlah yang diminta melebihi stock");
                    }
                }
            }
            if ("biji".equalsIgnoreCase(bean.getJenisSatuan())) {

                // jika jumlah stock obat bijian lebih besar atau sama dari jumlah permintaan
                // maka hanya perlu mengurangi stock biji
                if (qtyBiji.compareTo(bean.getQtyApprove()) == 1 || qtyBiji.compareTo(bean.getQtyApprove()) == 0) {
                    obatEntity.setQtyBiji(qtyBiji.subtract(bean.getQtyApprove()));
                } else {

                    BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());

                    BigInteger allStockBiji = (obatEntity.getQtyBox().multiply(cons))
                            .add(qtyLembar.multiply(obatEntity.getBijiPerLembar()))
                            .add(qtyBiji);

                    if (allStockBiji.compareTo(bean.getQtyApprove()) == 1) {

                        BigInteger sisaPenguranganAllBiji = allStockBiji.subtract(bean.getQtyApprove());

                        BigInteger bijiToLembar = sisaPenguranganAllBiji.divide(obatEntity.getBijiPerLembar());
                        BigInteger modBijiToLembar = sisaPenguranganAllBiji.mod(obatEntity.getBijiPerLembar());
                        BigInteger lembarToBox = bijiToLembar.divide(obatEntity.getLembarPerBox());
                        BigInteger modLembarToBox = bijiToLembar.mod(obatEntity.getLembarPerBox());

                        obatEntity.setQtyBiji(modBijiToLembar);
                        obatEntity.setQtyLembar(modLembarToBox);
                        obatEntity.setQtyBox(lembarToBox);

                    } else {
                        response.setStatus("error");
                        response.setMessage("Found Error, jumlah yang diminta melebihi stock ");
                        logger.error("[ObatPoliBoImpl.updateSubstractStockGudang] jumlah yang diminta melebihi stock");
                        throw new GeneralBOException("[ObatPoliBoImpl.updateSubstractStockGudang] jumlah yang diminta melebihi stock");
                    }
                }
            }

            obatEntity.setAction("U");
            obatEntity.setLastUpdate(bean.getLastUpdate());
            obatEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                obatDao.updateAndSave(obatEntity);
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error, update master obat"+e.getMessage());
                logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
            }
        }
        return response;
    }
}