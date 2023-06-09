package com.neurix.simrs.master.obat.bo.impl;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.bentukbarang.dao.BentukBarangDao;
import com.neurix.simrs.master.bentukbarang.model.BentukBarang;
import com.neurix.simrs.master.bentukbarang.model.ImSimrsBentukBarangEntity;
import com.neurix.simrs.master.jenisobat.dao.JenisObatDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.kategoripersediaan.dao.KategoriPersedianDao;
import com.neurix.simrs.master.kategoripersediaan.model.ImSimrsKategoriPersediaanEntity;
import com.neurix.simrs.master.marginobat.dao.MarginObatDao;
import com.neurix.simrs.master.marginobat.model.ImSimrsMarginObatEntity;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.dao.*;
import com.neurix.simrs.master.obat.model.*;
import com.neurix.simrs.master.obatgejala.dao.ObatGejalaDao;
import com.neurix.simrs.master.obatgejala.model.ImSimrsObatGejalaEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.hargaobat.dao.HargaObatDao;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.obatinap.model.ItSimrsObatInapEntity;
import com.neurix.simrs.transaksi.permintaanvendor.dao.PermintaanVendorDao;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.permintaanvendor.model.MtSimrsPermintaanVendorEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.riwayatbarang.dao.TransaksiStokDao;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsTransaksiStokEntity;
import com.neurix.simrs.transaksi.riwayatbarang.model.TransaksiStok;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailBatchDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.*;
//import javafx.collections.ObservableList;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObatBoImpl implements ObatBo {

    protected static transient Logger logger = Logger.getLogger(ObatBoImpl.class);
    private ObatDao obatDao;
    private JenisObatDao jenisObatDao;
    private ObatGejalaDao obatGejalaDao;
    private TransaksiObatDetailBatchDao batchDao;
    private HargaObatDao hargaObatDao;
    private ReturObatDao returObatDao;
    private ReturObatDetailDao returObatDetailDao;
    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private PermintaanVendorDao permintaanVendorDao;
    private TransaksiObatDetailDao transaksiObatDetailDao;
    private TransaksiStokDao transaksiStokDao;
    private PelayananDao pelayananDao;
    private BatasTutupPeriodDao batasTutupPeriodDao;
    private KandunganObatDetailDao kandunganObatDetailDao;
    private KandunganObatDao kandunganObatDao;
    private BentukBarangDao bentukBarangDao;

    public BentukBarangDao getBentukBarangDao() {
        return bentukBarangDao;
    }

    public void setBentukBarangDao(BentukBarangDao bentukBarangDao) {
        this.bentukBarangDao = bentukBarangDao;
    }

    private HeaderObatDao headerObatDao;
    private KategoriPersedianDao kategoriPersedianDao;
    private MarginObatDao marginObatDao;

    public void setMarginObatDao(MarginObatDao marginObatDao) {
        this.marginObatDao = marginObatDao;
    }

    public void setKategoriPersedianDao(KategoriPersedianDao kategoriPersedianDao) {
        this.kategoriPersedianDao = kategoriPersedianDao;
    }

    public void setHeaderObatDao(HeaderObatDao headerObatDao) {
        this.headerObatDao = headerObatDao;
    }

//    public void setBentukBarangDao(BentukBarangDao bentukBarangDao) {
//        this.bentukBarangDao = bentukBarangDao;
//    }

    public void setKandunganObatDetailDao(KandunganObatDetailDao kandunganObatDetailDao) {
        this.kandunganObatDetailDao = kandunganObatDetailDao;
    }

    public void setKandunganObatDao(KandunganObatDao kandunganObatDao) {
        this.kandunganObatDao = kandunganObatDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setBatasTutupPeriodDao(BatasTutupPeriodDao batasTutupPeriodDao) {
        this.batasTutupPeriodDao = batasTutupPeriodDao;
    }

    public void setTransaksiStokDao(TransaksiStokDao transaksiStokDao) {
        this.transaksiStokDao = transaksiStokDao;
    }

    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setPermintaanVendorDao(PermintaanVendorDao permintaanVendorDao) {
        this.permintaanVendorDao = permintaanVendorDao;
    }

    public void setTransaksiObatDetailDao(TransaksiObatDetailDao transaksiObatDetailDao) {
        this.transaksiObatDetailDao = transaksiObatDetailDao;
    }

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
    public CheckObatResponse saveAdd(Obat bean, List<String> idJenisObats) throws GeneralBOException {
        logger.info("[ObatBoImpl.saveAdd] Start >>>>>>>");
        CheckObatResponse response = new CheckObatResponse();

        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
        ImSimrsHeaderObatEntity headerObatEntity = new ImSimrsHeaderObatEntity();

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();

        String id = getIdNextObat();
        String idSeqObat = getIdNextSeqObat();

        // header obat
        if (bean.getIdPabrik() != null && !"".equalsIgnoreCase(bean.getIdPabrik())){
            headerObatEntity.setIdObat(bean.getIdPabrik());
        } else {
            headerObatEntity.setIdObat("OBT" + id);
        }
        headerObatEntity.setIdPabrik(headerObatEntity.getIdObat());
        headerObatEntity.setNamaObat(bean.getNamaObat());
        headerObatEntity.setMerk(bean.getMerk());
        headerObatEntity.setLembarPerBox(bean.getLembarPerBox());
        headerObatEntity.setBijiPerLembar(bean.getBijiPerLembar());
        headerObatEntity.setFlag(bean.getFlag());
        headerObatEntity.setAction(bean.getAction());
        headerObatEntity.setCreatedDate(time);
        headerObatEntity.setCreatedWho(userLogin);
        headerObatEntity.setLastUpdate(time);
        headerObatEntity.setLastUpdateWho(userLogin);
        headerObatEntity.setLembarPerBox(bean.getLembarPerBox());
        headerObatEntity.setBijiPerLembar(bean.getBijiPerLembar());
        headerObatEntity.setMinStok(bean.getMinStok());
        headerObatEntity.setFlagKronis(bean.getFlagKronis());
        headerObatEntity.setFlagGeneric(bean.getFlagGeneric());
        headerObatEntity.setFlagBpjs(bean.getFlagBpjs());
        headerObatEntity.setIdKategoriPersediaan(bean.getIdKategoriPersediaan());
        headerObatEntity.setIdBentuk(bean.getIdBentuk());
        headerObatEntity.setIdJenisObat(bean.getIdJenisBentuk());
        headerObatEntity.setIdSubJenis(bean.getIdJenisSub());
        headerObatEntity.setFlagParenteral(bean.getFlagParenteral());
        headerObatEntity.setFlagIsFormularium(bean.getFlagFormula());

        try {
            headerObatDao.addAndSave(headerObatEntity);
            response.setStatus("success");
            response.setMessage("berhasil");
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.saveAdd] error when add data obat " + e.getMessage());
            response.setStatus("error");
            response.setMessage("[ObatBoImpl.saveAdd] Error when add data obat "+e.getMessage());
            return response;
        }

        ImSimrsMarginObatEntity marginObatEntity = new ImSimrsMarginObatEntity();
        marginObatEntity.setIdMarginObat(headerObatEntity.getIdObat()+"MGN");
        marginObatEntity.setIdObat(headerObatEntity.getIdObat());
        marginObatEntity.setStandarMargin(bean.getMargin());
        marginObatEntity.setFlag("Y");
        marginObatEntity.setAction("C");
        marginObatEntity.setCreatedDate(time);
        marginObatEntity.setCreatedDateWho(userLogin);
        marginObatEntity.setLastUpdate(time);
        marginObatEntity.setLastUpdateWho(userLogin);

        try {
            marginObatDao.addAndSave(marginObatEntity);
            response.setStatus("success");
            response.setMessage("berhasil");
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.saveAdd] error when add data margin obat " + e.getMessage());
            response.setStatus("error");
            response.setMessage("[ObatBoImpl.saveAdd] Error when add data margin obat "+e.getMessage());
            return response;
        }

        obatEntity.setIdSeqObat(idSeqObat);
        obatEntity.setIdObat(headerObatEntity.getIdObat());
        obatEntity.setNamaObat(bean.getNamaObat());
        obatEntity.setHarga(bean.getHarga());
        obatEntity.setQty(bean.getQty());
        obatEntity.setBranchId(bean.getBranchId());
        obatEntity.setFlag(bean.getFlag());
        obatEntity.setAction(bean.getAction());
        obatEntity.setCreatedDate(time);
        obatEntity.setCreatedWho(userLogin);
        obatEntity.setLastUpdate(time);
        obatEntity.setLastUpdateWho(userLogin);
        obatEntity.setLembarPerBox(bean.getLembarPerBox());
        obatEntity.setBijiPerLembar(bean.getBijiPerLembar());
        obatEntity.setQtyBox(new BigInteger("0"));
        obatEntity.setQtyLembar(new BigInteger("0"));
        obatEntity.setQtyBiji(new BigInteger("0"));
        obatEntity.setAverageHargaBox(new BigDecimal("0"));
        obatEntity.setAverageHargaLembar(new BigDecimal("0"));
        obatEntity.setAverageHargaBiji(new BigDecimal("0"));
        obatEntity.setIdPabrik(headerObatEntity.getIdObat());
        obatEntity.setMerk(bean.getMerk());
        obatEntity.setMinStok(bean.getMinStok());
        obatEntity.setHargaTerakhir(new BigDecimal("0"));

        try {
            obatDao.addAndSave(obatEntity);
            response.setStatus("success");
            response.setMessage("berhasil");
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.saveAdd] error when add data obat " + e.getMessage());
            response.setStatus("error");
            response.setMessage("[ObatBoImpl.saveAdd] Error "+e.getMessage());
            return response;
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
                    response.setStatus("success");
                    response.setMessage("berhasil");
                } catch (HibernateException e) {
                    logger.error("[ObatBoImpl.saveAdd] error when insert new obat gejala " + e.getMessage());
                    response.setStatus("error");
                    response.setMessage("[ObatBoImpl.saveAdd] Error "+e.getMessage());
                    return response;
                }
            }
        }

        // kandungan obat;
        if (bean.getKandunganObats() != null && bean.getKandunganObats().size() > 0){
            for (KandunganObat kandunganObat : bean.getKandunganObats()){
                ImSimrsKandunganObatDetailEntity kandunganObatDetailEntity = new ImSimrsKandunganObatDetailEntity();
                kandunganObatDetailEntity.setId("KDD"+kandunganObatDetailDao.getNextId());
                kandunganObatDetailEntity.setIdObat(obatEntity.getIdObat());
                kandunganObatDetailEntity.setIdKandungan(kandunganObat.getIdKandungan());
                kandunganObatDetailEntity.setSediaan(kandunganObat.getSediaan());
                kandunganObatDetailEntity.setSatuanSediaan(kandunganObat.getSatuanSediaan());
                kandunganObatDetailEntity.setFlag("Y");
                kandunganObatDetailEntity.setAction("C");
                kandunganObatDetailEntity.setCreatedDate(bean.getLastUpdate());
                kandunganObatDetailEntity.setCreatedWho(bean.getLastUpdateWho());
                kandunganObatDetailEntity.setLastUpdate(bean.getLastUpdate());
                kandunganObatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    kandunganObatDetailDao.addAndSave(kandunganObatDetailEntity);
                    response.setStatus("success");
                    response.setMessage("berhasil");
                } catch (HibernateException e){
                    response.setStatus("error");
                    response.setMessage("[ObatBoImpl.saveAdd] Error "+e.getMessage());
                    logger.error("[ObatBoImpl.saveAdd] error when add kandungan obat " + e.getMessage());
                    return response;
                }
            }
        }

        logger.info("[ObatBoImpl.saveAdd] End <<<<<<<");
        return response;
    }

    @Override
    public CheckObatResponse saveEdit(Obat bean, List<String> idJenisObats) throws GeneralBOException {
        logger.info("[ObatBoImpl.saveEdit] Start >>>>>>>");

        CheckObatResponse response = new CheckObatResponse();

        if (bean != null) {

            Obat obat = new Obat();
            obat.setIdObat(bean.getIdObat());
            List<ImSimrsObatEntity> entityList = getListObatEntity(obat);

            if (entityList.size() > 0) {

                ImSimrsHeaderObatEntity headerObatEntity = headerObatDao.getById("idObat", bean.getIdObat());
                if (headerObatEntity != null){

                    headerObatEntity.setNamaObat(bean.getNamaObat());
                    headerObatEntity.setLastUpdate(bean.getLastUpdate());
                    headerObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    headerObatEntity.setLembarPerBox(bean.getLembarPerBox());
                    headerObatEntity.setBijiPerLembar(bean.getBijiPerLembar());
                    headerObatEntity.setMerk(bean.getMerk());
                    headerObatEntity.setAction(bean.getAction());
                    headerObatEntity.setMinStok(bean.getMinStok());
                    headerObatEntity.setFlagGeneric(bean.getFlagGeneric());
                    headerObatEntity.setFlagKronis(bean.getFlagKronis());
                    headerObatEntity.setFlagBpjs(bean.getFlagBpjs());
                    headerObatEntity.setFlagIsFormularium(bean.getFlagFormula());
                    headerObatEntity.setFlagParenteral(bean.getFlagParenteral());
                    headerObatEntity.setIdKategoriPersediaan(bean.getIdKategoriPersediaan());
                    headerObatEntity.setIdBentuk(bean.getIdBentuk());
                    headerObatEntity.setIdJenisObat(bean.getIdJenisBentuk());
                    headerObatEntity.setIdSubJenis(bean.getIdJenisSub());

                    try {
                        headerObatDao.updateAndSave(headerObatEntity);
                        response.setStatus("success");
                        response.setMessage("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error when update header obat " + e.getMessage());
                        logger.error("[ObatBoImpl.saveEdit] error when update header obat " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.saveEdit] error when update header obat " + e.getMessage());
                    }
                }

                ImSimrsMarginObatEntity marginObatEntity = marginObatDao.getById("idObat", headerObatEntity.getIdObat());
                if (marginObatEntity != null){

                    marginObatEntity.setStandarMargin(bean.getMargin());
                    marginObatEntity.setAction("U");
                    marginObatEntity.setLastUpdate(bean.getLastUpdate());
                    marginObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        marginObatDao.updateAndSave(marginObatEntity);
                        response.setStatus("success");
                        response.setMessage("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error when update margin obat " + e.getMessage());
                        logger.error("[ObatBoImpl.saveEdit] error when update margin obat " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.saveEdit] error when update header obat " + e.getMessage());
                    }
                } else {
                    marginObatEntity = new ImSimrsMarginObatEntity();
                    marginObatEntity.setIdMarginObat("MRG"+headerObatEntity.getIdObat());
                    marginObatEntity.setIdObat(headerObatEntity.getIdObat());
                    marginObatEntity.setStandarMargin(bean.getStandarMargin());
                    marginObatEntity.setCreatedDate(bean.getLastUpdate());
                    marginObatEntity.setCreatedDateWho(bean.getLastUpdateWho());
                    marginObatEntity.setLastUpdate(bean.getLastUpdate());
                    marginObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    marginObatEntity.setStandarMargin(bean.getMargin());
                    marginObatEntity.setFlag("Y");
                    marginObatEntity.setAction("C");
                    try {
                        marginObatDao.addAndSave(marginObatEntity);
                        response.setStatus("success");
                        response.setMessage("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error when update margin obat " + e.getMessage());
                        logger.error("[ObatBoImpl.saveEdit] error when add margin obat " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.saveEdit] error when add header obat " + e.getMessage());
                    }
                }

                // loop jenis obat;
                for (ImSimrsObatEntity obatEntity : entityList) {

                    obatEntity.setNamaObat(bean.getNamaObat());
                    obatEntity.setLastUpdate(bean.getLastUpdate());
                    obatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    obatEntity.setLembarPerBox(bean.getLembarPerBox());
                    obatEntity.setBijiPerLembar(bean.getBijiPerLembar());
                    obatEntity.setIdPabrik(obatEntity.getIdPabrik());
                    obatEntity.setMerk(bean.getMerk());
                    obatEntity.setAction(bean.getAction());
                    obatEntity.setMinStok(bean.getMinStok());

                    try {
                        obatDao.updateAndSave(obatEntity);
                        response.setStatus("success");
                        response.setMessage("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error when update obat " + e.getMessage());
                        logger.error("[ObatBoImpl.saveEdit] error when update data obat " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.saveEdit] error when update data obat " + e.getMessage());
                    }
                }

                if (bean.getKandunganObats() != null && bean.getKandunganObats().size() > 0){
                    List<ImSimrsKandunganObatDetailEntity> kandunganObatList = getEntityKandunganObatDetail(bean.getIdObat());
                    if(kandunganObatList.size() > 0){
                        for (ImSimrsKandunganObatDetailEntity entity : kandunganObatList){
                            entity.setFlag("N");
                            entity.setAction("D");
                            entity.setLastUpdate(bean.getLastUpdate());
                            entity.setLastUpdateWho(bean.getLastUpdateWho());
                            try {
                                kandunganObatDetailDao.updateAndSave(entity);
                            }catch (HibernateException e){
                                logger.error(e.getMessage());
                            }
                        }
                    }

                    for (KandunganObat kandunganObat : bean.getKandunganObats()){
                        ImSimrsKandunganObatDetailEntity kandunganObatDetailEntity = new ImSimrsKandunganObatDetailEntity();
                        kandunganObatDetailEntity.setId("KDD"+kandunganObatDetailDao.getNextId());
                        kandunganObatDetailEntity.setIdObat(bean.getIdObat());
                        kandunganObatDetailEntity.setIdKandungan(kandunganObat.getIdKandungan());
                        kandunganObatDetailEntity.setBentuk(kandunganObat.getBentuk());
                        kandunganObatDetailEntity.setSediaan(kandunganObat.getSediaan());
                        kandunganObatDetailEntity.setSatuanSediaan(kandunganObat.getSatuanSediaan());
                        kandunganObatDetailEntity.setFlag("Y");
                        kandunganObatDetailEntity.setAction("C");
                        kandunganObatDetailEntity.setCreatedDate(bean.getLastUpdate());
                        kandunganObatDetailEntity.setCreatedWho(bean.getLastUpdateWho());
                        kandunganObatDetailEntity.setLastUpdate(bean.getLastUpdate());
                        kandunganObatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            kandunganObatDetailDao.addAndSave(kandunganObatDetailEntity);
                        } catch (HibernateException e){
                            response.setStatus("error");
                            response.setMessage("Found Error when add kandungan obat " + e.getMessage());
                            logger.error("[ObatBoImpl.saveEdit] error when add kandungan obat " + e.getMessage());
                            throw new GeneralBOException("[ObatBoImpl.saveEdit] error when add kandungan obat " + e.getMessage());
                        }
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
        if (bean.getIdPabrik() != null && !"".equalsIgnoreCase(bean.getIdPabrik()))
            hsCriteria.put("id_pabrik", bean.getIdPabrik());

        hsCriteria.put("branch_id", bean.getBranchId());
        hsCriteria.put("lembar_per_box", bean.getLembarPerBox());
        hsCriteria.put("biji_per_lembar", bean.getBijiPerLembar());
        hsCriteria.put("flag", "Y");

        if (bean.getNamaObat() != null)
            hsCriteria.put("nama_obat_fix", bean.getNamaObat());


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
    public CheckObatResponse checkFisikIdObatByIdPabrik(Obat bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.checkFisikIdObatByIdPabrik] START >>>>>>>>>>");

        CheckObatResponse response = new CheckObatResponse();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", bean.getIdPabrik());

        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.checkFisikIdObatByIdPabrik] error when check fisik obat" + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.checkFisikIdObatByIdPabrik] check fisik obat " + e.getMessage());
        }

        if (obatEntities.size() > 0) {
            ImSimrsObatEntity obatEntity = obatEntities.get(0);
            response.setStatus("warning");
            response.setMessage(obatEntity.getIdObat());
        } else {
            response.setStatus("success");
            response.setMessage("Silahkan dilanjutkan");
        }

        logger.info("[ObatPoliBoImpl.checkFisikIdObatByIdPabrik] END <<<<<<<<<<");
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
        if (bean.getIdBarang() != null && !"".equalsIgnoreCase(bean.getIdBarang())) {
            hsCriteria.put("id_barang", bean.getIdBarang());
        }

        hsCriteria.put("exp", "Y");

        List<ImSimrsObatEntity> obatEntityList = new ArrayList<>();

        try {
            obatEntityList = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getByCriteria] error when get data obat by get by criteria " + e.getMessage());
        }

        if (obatEntityList.size() > 0) {

            for (ImSimrsObatEntity entity : obatEntityList) {

                if(entity.getIdBarang() != null && !"".equalsIgnoreCase(entity.getIdBarang())){
                    Integer box = Integer.valueOf(entity.getQtyBox().toString());
                    Integer lembar = Integer.valueOf(entity.getQtyLembar().toString());
                    Integer biji = Integer.valueOf(entity.getQtyBiji().toString());
                    if(box > 0 || lembar > 0 || biji > 0){
                        Obat obat = new Obat();
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
                        obat.setFlagBpjs(entity.getFlagBpjs());
                        result.add(obat);
                    }
                }
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


        for (String idObat : listIdObat) {
            Obat obat = new Obat();

            try {
                Obat seqObat = obatDao.getLastIdSeqObat(idObat);
                if (seqObat != null){
                    obat.setIdSeqObat(seqObat.getIdSeqObat());
                    obat.setCreatedDate(seqObat.getCreatedDate());
                }
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
                        sumObat = obatDao.getSumStockObatGudangById(idObat, "stok", bean.getBranchId());
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
            id = headerObatDao.getNextId();
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
            hsCriteria.put("id_harga_obat", bean.getIdHargaObat());
            List<MtSimrsHargaObatEntity> hargaObatEntities = hargaObatDao.getByCriteria(hsCriteria);
            if (hargaObatEntities.size() > 0) {

                // update harga obat entities
                for (MtSimrsHargaObatEntity obatEntity : hargaObatEntities) {

                    // obat khusus
                    obatEntity.setHargaJual(bean.getHargaJual());
                    obatEntity.setDiskon(bean.getDiskon());
                    obatEntity.setHargaNet(bean.getHargaNet());

                    // obat umum
                    obatEntity.setHargaJualUmum(bean.getHargaJualUmum());
                    obatEntity.setDiskonUmum(bean.getDiskonUmum());
                    obatEntity.setHargaNetUmum(bean.getHargaNetUmum());

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

                // add harga obat entities
                Obat obat = new Obat();
                obat.setIdObat(bean.getIdObat());
                obat.setIdBarang(bean.getIdBarang());
                List<ImSimrsObatEntity> obatEntities = getListObatEntity(obat);
                if (obatEntities.size() > 0) {
                    ImSimrsObatEntity obatEntity = obatEntities.get(0);

                    MtSimrsHargaObatEntity hargaObatEntity = new MtSimrsHargaObatEntity();
                    hargaObatEntity.setIdHargaObat(bean.getIdHargaObat());
                    hargaObatEntity.setIdObat(obatEntity.getIdObat());
                    hargaObatEntity.setNamaObat(obatEntity.getNamaObat());
                    hargaObatEntity.setHargaBeli(obatEntity.getHargaTerakhir());
                    hargaObatEntity.setHargaRata(obatEntity.getAverageHargaBiji());

                    // harga obat khusus
                    hargaObatEntity.setHargaJual(bean.getHargaJual());
                    hargaObatEntity.setDiskon(bean.getDiskon());
                    hargaObatEntity.setHargaNet(bean.getHargaNet());

                    // harga obat umu,
                    hargaObatEntity.setHargaJualUmum(bean.getHargaJualUmum());
                    hargaObatEntity.setDiskonUmum(bean.getDiskonUmum());
                    hargaObatEntity.setHargaNetUmum(bean.getHargaNetUmum());

                    hargaObatEntity.setSatuan("biji");
                    hargaObatEntity.setFlag("Y");
                    hargaObatEntity.setAction("C");
                    hargaObatEntity.setCreatedDate(bean.getCreatedDate());
                    hargaObatEntity.setCreatedWho(bean.getCreatedWho());
                    hargaObatEntity.setLastUpdate(bean.getLastUpdate());
                    hargaObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    hargaObatEntity.setMargin(bean.getMargin());
                    hargaObatEntity.setBranchId(bean.getBranchId());

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

        if (bean != null) {


            ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
            approvalEntity.setIdApprovalObat("INV" + approvalTransaksiObatDao.getNextId());
            approvalEntity.setIdPelayanan(CommonUtil.userPelayananIdLogin());
            approvalEntity.setBranchId(bean.getBranchId());
            approvalEntity.setFlag(bean.getFlag());
            approvalEntity.setAction(bean.getAction());
            approvalEntity.setTipePermintaan("004"); //reture obat
            approvalEntity.setLastUpdate(bean.getCreatedDate());
            approvalEntity.setLastUpdateWho(bean.getCreatedWho());
            approvalEntity.setCreatedDate(bean.getCreatedDate());
            approvalEntity.setCreatedWho(bean.getCreatedWho());
            approvalEntity.setApprovalFlag("Y");
            approvalEntity.setApprovePerson(bean.getCreatedWho());

            try {
                approvalTransaksiObatDao.addAndSave(approvalEntity);
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error when insert into approval transaksi " + e.getMessage());
                logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when insert into approval transaksi. ", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when insert into approval transaksi. ", e);
            }

            MtSimrsPermintaanVendorEntity permintaanVendorEntity = new MtSimrsPermintaanVendorEntity();
            permintaanVendorEntity.setIdPermintaanVendor("PVM" + permintaanVendorDao.getNextSeq());
            permintaanVendorEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
            permintaanVendorEntity.setIdVendor(bean.getIdVendor());
            permintaanVendorEntity.setBranchId(bean.getBranchId());
            permintaanVendorEntity.setFlag("Y");
            permintaanVendorEntity.setAction("C");
            permintaanVendorEntity.setCreatedDate(bean.getCreatedDate());
            permintaanVendorEntity.setCreatedWho(bean.getCreatedWho());
            permintaanVendorEntity.setLastUpdate(bean.getLastUpdate());
            permintaanVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
            permintaanVendorEntity.setTipeTransaksi("reture");
//                  permintaanVendorEntity.setTglCair(Date.valueOf(bean.getTglCair()));

            try {
                permintaanVendorDao.addAndSave(permintaanVendorEntity);
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error when create permintaan vendor " + e.getMessage());
                logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create permintaan vendor. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create permintaan vendor. " + e.getMessage());
            }

            ItSimrsReturObatEntity returObatEntity = new ItSimrsReturObatEntity();
            returObatEntity.setIdReturObat("RTO" + returObatDao.getNextId());
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
            returObatEntity.setNoJurnal(bean.getNoJurnal());
            returObatEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());

            try {
                returObatDao.addAndSave(returObatEntity);
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error " + e.getMessage());
                logger.error("Found Errro " + e.getMessage());
            }

            if (obatList.size() > 0) {
                for (Obat obatDetail : obatList) {

                    Obat obatEntity = new Obat();
                    try{
                        obatEntity = obatDao.getObatByIdBarang(obatDetail.getIdBarang(), bean.getBranchId());
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMessage("Found Error when save add vendor " + e.getMessage());
                        logger.error(e.getMessage());
                    }

                    ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                    obatDetailEntity.setIdTransaksiObatDetail("ODT" + transaksiObatDetailDao.getNextId());
                    obatDetailEntity.setIdApprovalObat(permintaanVendorEntity.getIdApprovalObat());
                    obatDetailEntity.setQtyBox(obatDetail.getQtyBox());
                    obatDetailEntity.setIdObat(obatEntity.getIdObat());
                    obatDetailEntity.setLembarPerBox(obatEntity.getLembarPerBox());
                    obatDetailEntity.setBijiPerLembar(obatEntity.getBijiPerLembar());
                    obatDetailEntity.setQty(obatDetail.getQty());
//                    obatDetailEntity.setAverageHargaBox(obatEntity.getHargaTerakhir());
                    obatDetailEntity.setAverageHargaBiji(obatEntity.getHargaTerakhir());
                    obatDetailEntity.setFlag("Y");
                    obatDetailEntity.setAction("C");
                    obatDetailEntity.setCreatedDate(bean.getCreatedDate());
                    obatDetailEntity.setCreatedWho(bean.getCreatedWho());
                    obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                    obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    obatDetailEntity.setJenisSatuan("biji");
                    obatDetailEntity.setKeterangan("Reture Obat");
                    obatDetailEntity.setFlagObatBpjs(obatEntity.getFlagBpjs());

                    try {
                        transaksiObatDetailDao.addAndSave(obatDetailEntity);
                        response.setStatus("success");
                        response.setMessage("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error when save add vendor " + e.getMessage());
                        logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                        throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                    }

                    ItSimrsReturObatDetailEntity returObatDetailEntity = new ItSimrsReturObatDetailEntity();
                    returObatDetailEntity.setIdReturDetail("RTD" + returObatDetailDao.getNextId());
                    returObatDetailEntity.setIdReturObat(returObatEntity.getIdReturObat());
                    returObatDetailEntity.setIdObat(obatDetail.getIdObat());
                    returObatDetailEntity.setIdBarang(obatDetail.getIdBarang());
                    returObatDetailEntity.setQtyRetur(obatDetail.getQty());
                    returObatDetailEntity.setAction("C");
                    returObatDetailEntity.setFlag("Y");
                    returObatDetailEntity.setCreatedWho(bean.getCreatedWho());
                    returObatDetailEntity.setCreatedDate(bean.getCreatedDate());
                    returObatDetailEntity.setLastUpdate(bean.getLastUpdate());
                    returObatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {

                        returObatDetailDao.addAndSave(returObatDetailEntity);

                        Obat obt = new Obat();
                        obt.setIdBarang(obatDetail.getIdBarang());
                        obt.setIdObat(obatDetail.getIdObat());
                        obt.setQtyApprove(obatDetail.getQty());
                        obt.setJenisSatuan("biji");
                        obt.setLastUpdate(bean.getLastUpdate());
                        obt.setLastUpdateWho(bean.getLastUpdateWho());

                        response = updateSubstractStockGudang(obt);

                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error " + e.getMessage());
                        logger.error("Found error " + e.getMessage());
                    }
                }
            }
        }
        return response;
    }

    @Override
    public List<Obat> searchReturObat(Obat bean) throws GeneralBOException {
        List<Obat> obatList = new ArrayList<>();
        if (bean != null) {
            try {
                obatList = returObatDao.getListReturObat(bean);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return obatList;
    }

    @Override
    public List<Obat> detailReturObat(String idRetur) throws GeneralBOException {
        List<Obat> obatList = new ArrayList<>();
        if (idRetur != null) {
            try {
                obatList = returObatDao.getListDetailReturObat(idRetur);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return obatList;
    }

    @Override
    public List<Obat> searchObatByVendor(String idVendor, String branchId) throws GeneralBOException {
        List<Obat> obatList = new ArrayList<>();
        if (idVendor != null && branchId != null) {
            try {
                obatList = returObatDao.getListObatByVendor(idVendor, branchId);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
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

                    if (allStockBiji.compareTo(bean.getQtyApprove()) == 1 || allStockBiji.compareTo(bean.getQtyApprove()) == 0) {

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
                response.setMessage("Found Error, update master obat" + e.getMessage());
                logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
            }
        }
        return response;
    }

    @Override
    public List<TransaksiStok> getListReporTransaksiObat(String idPelayanan, String tahun, String bulan, String idObat) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_barang", idObat);
        hsCriteria.put("id_pelayanan", idPelayanan);
        hsCriteria.put("tahun", Integer.valueOf(tahun));
        hsCriteria.put("bulan", Integer.valueOf(bulan));

        List<ItSimrsTransaksiStokEntity> stokEntities = new ArrayList<>();
        try {
           stokEntities = transaksiStokDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR .", e);
            throw new GeneralBOException("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR .", e);
        }

        BigDecimal nol = new BigDecimal(0);
        BigInteger nolB = new BigInteger(String.valueOf(0));
        List<TransaksiStok> listOfTransaksi = new ArrayList<>();
        if (stokEntities.size() > 0){

            int n = 0;
            TransaksiStok trans;
            String namaObat = "";
            for (ItSimrsTransaksiStokEntity stok : stokEntities){

                // get nama obat
                if ("".equalsIgnoreCase(namaObat)){
                    ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
                    try {
                        obatEntity = obatDao.getById("idBarang", stok.getIdBarang());
                    } catch (HibernateException e){
                        logger.error("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR .", e);
                        throw new GeneralBOException("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR .", e);
                    }

                    if (obatEntity != null){
                        namaObat = obatEntity.getNamaObat();
                    }
                }


                try {
                    if (listOfTransaksi.size() == 0){

                        // saldo bulan lalu tanpa data pendukung
                        if (stok.getQtyLalu() != null && stok.getQtyLalu().compareTo(new BigInteger(String.valueOf(0))) == 1){

                            trans = new TransaksiStok();
                            trans.setNamaObat(namaObat);
                            trans.setQtyLalu(nolB);
                            trans.setTotalLalu(nol);
                            trans.setSubTotalLalu(nol);

                            trans.setQtyLalu(stok.getQtyLalu() == null ? new BigInteger(String.valueOf(0)) : stok.getQtyLalu());
                            trans.setTotalLalu(stok.getTotalLalu() == null ? new BigDecimal(0) : stok.getTotalLalu());
                            trans.setSubTotalLalu(stok.getSubTotalLalu() == null ? new BigDecimal(0) : stok.getSubTotalLalu());
                            listOfTransaksi.add(trans);
                            n++;
                        } else {
                            trans = new TransaksiStok();
                            trans.setNamaObat(namaObat);
                            trans.setQtyLalu(nolB);
                            trans.setTotalLalu(nol);
                            trans.setSubTotalLalu(nol);
                            listOfTransaksi.add(trans);
                            n++;
                        }

                        // data seletelah saldo bulan lalu dengan data pendukung
                        trans = new TransaksiStok();
                        trans.setNamaObat(namaObat);
                        trans.setRegisteredDate(stok.getRegisteredDate());
                        trans.setCreatedDate(stok.getCreatedDate());
                        trans.setKeterangan(stok.getIdBarang() +" - "+stok.getKeterangan());
                        trans.setTipe(stok.getTipe());

                        TransaksiStok minStok = listOfTransaksi.get(n-1);
                        if ("D".equalsIgnoreCase(stok.getTipe())){
                            trans.setQty(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                            trans.setTotal(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                            trans.setSubTotal(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());


                            trans.setQtySaldo(minStok.getQtyLalu().add(trans.getQty()));

                            // total saldo = sub total lalu + sub total / qty saldo
//                            BigDecimal qtySaldo = new BigDecimal(trans.getQtySaldo() == null ? new BigInteger(String.valueOf(0)) : trans.getQtySaldo());
//                            BigDecimal subTotalLalu =  minStok.getSubTotalLalu() == null ? new BigDecimal(0) : minStok.getSubTotalLalu();
//                            BigDecimal totalSubTotalLalu = subTotalLalu.add(trans.getSubTotal());
//                            BigDecimal totalSaldo = totalSubTotalLalu.compareTo(new BigDecimal(0)) == 1 ? totalSubTotalLalu.divide(qtySaldo, 2, BigDecimal.ROUND_HALF_UP) : new BigDecimal(0) ;

                            trans.setTotalSaldo(trans.getTotal());

                            // sub total saldo = total saldo * qty saldo
                            trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                        } else {

                            trans.setQtyKredit(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                            trans.setTotalKredit(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                            trans.setSubTotalKredit(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

                            // qty saldo = qty bulan lalu - qty masuk
                            trans.setQtySaldo(minStok.getQtyLalu().subtract(trans.getQtyKredit()));

                            // total saldo = total lalu
                            trans.setTotalSaldo(stok.getTotalLalu() == null ? new BigDecimal(0) : stok.getTotalLalu());

                            // sub total saldo = total saldo * qty saldo
                            trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                        }
                        listOfTransaksi.add(trans);
                        n++;
                    } else {

                        // data pendukung
                        trans = new TransaksiStok();
                        trans.setNamaObat(namaObat);
                        trans.setRegisteredDate(stok.getRegisteredDate());
                        trans.setCreatedDate(stok.getCreatedDate());
                        trans.setKeterangan(stok.getIdBarang() +" - "+stok.getKeterangan());
                        trans.setTipe(stok.getTipe());

                        TransaksiStok minStok = listOfTransaksi.get(n-1);

                        if ("D".equalsIgnoreCase(stok.getTipe())){
                            trans.setQty(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                            trans.setTotal(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                            trans.setSubTotal(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());


                            // qty saldo = qty saldo lalu + qty
                            trans.setQtySaldo(minStok.getQtySaldo().add(trans.getQty()));

                            // total saldo = sub total saldo lalu + sub total / qty saldo
//                            trans.setTotalSaldo(minStok.getSubTotalSaldo().add(trans.getSubTotal()).divide(new BigDecimal(trans.getQtySaldo()), 2, BigDecimal.ROUND_HALF_UP));
                            trans.setTotalSaldo(trans.getTotal());

                            // sub total saldo = sub total saldo
                            trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                        } else {

                            trans.setQtyKredit(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                            trans.setTotalKredit(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                            trans.setSubTotalKredit(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

                            // qty saldo = qty saldo - qty
                            trans.setQtySaldo((minStok.getQtySaldo() == null ? new BigInteger(String.valueOf(0)) : minStok.getQtySaldo()).subtract(trans.getQtyKredit()));

                            // total saldo = total saldo lalu
                            trans.setTotalSaldo(trans.getTotalKredit());

                            // sub total saldo = sub total saldo
                            trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                        }
                        listOfTransaksi.add(trans);
                        n++;
                    }
                } catch (GeneralBOException e){
                    logger.error("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR .", e);
                    throw new GeneralBOException("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR .", e);
                }
            }
        }

        return listOfTransaksi;
    }

    private TransaksiStok getSumSaldoBulanLaluStok(List<ItSimrsTransaksiStokEntity> transaksiStokEntities) {

        TransaksiStok transaksiStok = new TransaksiStok();
        BigInteger qtySaldo = new BigInteger(String.valueOf(0));
        BigDecimal totalSaldo = new BigDecimal(0);
        BigDecimal subTotalSaldo = new BigDecimal(0);
        int n = 0;
        for (ItSimrsTransaksiStokEntity stokEntity : transaksiStokEntities) {
            if (n == 0) {

                stokEntity.setQtyLalu(stokEntity.getQtyLalu() == null ? new BigInteger(String.valueOf(0)) : stokEntity.getQtyLalu());
                stokEntity.setTotalLalu(stokEntity.getTotalLalu() == null ? new BigDecimal(0) : stokEntity.getTotalLalu());
                stokEntity.setSubTotalLalu(stokEntity.getSubTotalLalu() == null ? new BigDecimal(0) : stokEntity.getSubTotalLalu());

                if ("D".equalsIgnoreCase(stokEntity.getTipe())) {
                    // qty saldo qty masuk + qty bulan lalu
                    qtySaldo = stokEntity.getQty().add(stokEntity.getQtyLalu());
                    // total saldo = sub total + sub total lalu / qty saldo
                    totalSaldo = stokEntity.getSubTotal().add(stokEntity.getSubTotalLalu()).divide(new BigDecimal(qtySaldo), 2, BigDecimal.ROUND_HALF_UP);
                    // sub total = total saldo * qty saldo
                    subTotalSaldo = totalSaldo.multiply(new BigDecimal(qtySaldo));

                } else {
                    // jika saldo keluar;
                    // qty saldo = qty lalu - qty keluar
                    qtySaldo = stokEntity.getQtyLalu().subtract(stokEntity.getQty());
                    // total saldo = total lalu
                    totalSaldo = stokEntity.getTotalLalu();
                    // sub total = total saldo * qty saldo
                    subTotalSaldo = totalSaldo.multiply(new BigDecimal(qtySaldo));
                }

                n++;
            } else {

                if ("D".equalsIgnoreCase(stokEntity.getTipe())) {

                    // qty saldo = qty saldo + qty masuk
                    qtySaldo = qtySaldo.add(stokEntity.getQty());

                    // total saldo = sub total saldo + sub total masuk / qty saldo
                    totalSaldo = subTotalSaldo.add(stokEntity.getSubTotal()).divide(new BigDecimal(qtySaldo), 2, BigDecimal.ROUND_HALF_UP);

                    // sub total saldo = total saldo * qty saldo
                    subTotalSaldo = totalSaldo.multiply(new BigDecimal(qtySaldo));
                } else {

                    // qty saldo = qty saldo + qty masuk
                    qtySaldo = qtySaldo.subtract(stokEntity.getQty());

                    // total saldo = total saldo;
                    totalSaldo = totalSaldo;

                    // sub total saldo = total saldo * qty saldo
                    subTotalSaldo = totalSaldo.multiply(new BigDecimal(qtySaldo));
                }

                n++;
            }
        }

        transaksiStok.setQtySaldo(qtySaldo);
        transaksiStok.setTotalSaldo(totalSaldo);
        transaksiStok.setSubTotalSaldo(subTotalSaldo);

        return transaksiStok;
    }


    private void saveTransaksiStok(TransaksiObatDetail bean){

        logger.info("[TransaksiObatBoImpl.saveTransaksiStok] START >>>");
        // SAVE TO STOCK TRANSAKSI

        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        String tahun = CommonUtil.getDateParted(date, "YEAR");
        String bulan = CommonUtil.getDateParted(date, "MONTH");

        TransaksiStok saldoBulanLalu = new TransaksiStok();
        boolean flagTutup = false;
        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriod = batasTutupPeriodDao.getBatasPeriodeDitutup(bean.getBranchId(), bulan, tahun);
        if (batasTutupPeriod.size() > 0){
            // jika sudah ditutup bulan ini
            flagTutup = true;
        }

        // cari apakah data baru
        Map hsCriteria = new HashMap();
        hsCriteria.put("branch_id", bean.getBranchId());
        hsCriteria.put("id_barang", bean.getIdObat());
        hsCriteria.put("bulan", Integer.valueOf(bulan));
        hsCriteria.put("tahun", Integer.valueOf(tahun));

        TransaksiStok stokBulanLalu = new TransaksiStok();
        List<ItSimrsTransaksiStokEntity> transaksiStokEntities = transaksiStokDao.getByCriteria(hsCriteria);
        if (transaksiStokEntities.size() == 0){

            // jika sudah ditutup bulan ini
            // maka hitung saldo bulan ini sebagai saldo bulan lalu
            if (flagTutup){
                saldoBulanLalu = getSumSaldoBulanLaluStok(transaksiStokEntities);
            } else {
                // jika data baru dibulan tersebut maka mengitung juga saldo bulan sebelumnya jika ada;
                // mencari data saldo bulan lalu
                // menghitung saldo lalu;

                Integer tahunLalu = new Integer(0);
                Integer bulanLalu = new Integer(0);

                if ("1".equalsIgnoreCase(bulan)){
                    bulanLalu = 12;
                    tahunLalu = Integer.valueOf(tahun) - 1;
                } else {
                    bulanLalu = Integer.valueOf(bulan) - 1;
                    tahunLalu = Integer.valueOf(tahun);
                }

                hsCriteria = new HashMap();
                hsCriteria.put("branch_id", bean.getBranchId());
                hsCriteria.put("id_barang", bean.getIdObat());
                hsCriteria.put("bulan", bulanLalu);
                hsCriteria.put("tahun", tahunLalu);

                transaksiStokEntities = new ArrayList<>();
                transaksiStokEntities = transaksiStokDao.getByCriteria(hsCriteria);
                stokBulanLalu = getSumSaldoBulanLaluStok(transaksiStokEntities);
            }
        }


        String pelayananTujuan = "";
        ImSimrsPelayananEntity pelayananEntity = pelayananDao.getById("idPelayanan", bean.getIdPelayanan());
        if (pelayananEntity != null){
            pelayananTujuan = pelayananEntity.getNamaPelayanan();
        }

        String namaObat = "";
        String idObat = "";
        BigInteger consBox = new BigInteger(String.valueOf(0));
        BigInteger consLembar = new BigInteger(String.valueOf(0));
        BigDecimal hargaBijian = new BigDecimal(String.valueOf(0));
        ImSimrsObatEntity obatEntity = obatDao.getById("idBarang", bean.getIdBarang());
        if (obatEntity != null){
            namaObat = obatEntity.getNamaObat();
            consLembar = obatEntity.getBijiPerLembar();
            consBox = obatEntity.getLembarPerBox().multiply(consLembar);
            idObat = obatEntity.getIdObat();
            hargaBijian = obatEntity.getAverageHargaBiji();
        }

        //BigDecimal hargaObat = new BigDecimal(0);
        BigInteger qty = new BigInteger(String.valueOf(0));

        if ("box".equalsIgnoreCase(bean.getJenisSatuan())){
            qty = bean.getQtyApprove().multiply(consBox);
        } else if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())){
            qty = bean.getQtyApprove().multiply(consLembar);
        } else {
            qty = bean.getQtyApprove();
        }


        java.util.Date now = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String seq = transaksiStokDao.getNextSeq();
        String idBarangMasuk = "RB"+ bean.getBranchId() + f.format(now) + seq;

        ItSimrsTransaksiStokEntity transaksiStokEntity = new ItSimrsTransaksiStokEntity();
        transaksiStokEntity.setIdTransaksi(idBarangMasuk);
        transaksiStokEntity.setIdObat(idObat);
        transaksiStokEntity.setKeterangan("Pengeluaran Obat Apotek " + pelayananTujuan);
        transaksiStokEntity.setTipe("K");
        transaksiStokEntity.setBranchId(bean.getBranchId());
        transaksiStokEntity.setQty(qty);
        transaksiStokEntity.setTotal(hargaBijian);
        transaksiStokEntity.setSubTotal(hargaBijian.multiply(new BigDecimal(qty)));
        transaksiStokEntity.setCreatedDate(bean.getCreatedDate());
        transaksiStokEntity.setCreatedWho(bean.getCreatedWho());
        transaksiStokEntity.setLastUpdate(bean.getCreatedDate());
        transaksiStokEntity.setLastUpdateWho(bean.getCreatedWho());
        transaksiStokEntity.setIdBarang(bean.getIdBarang());
        transaksiStokEntity.setIdPelayanan(bean.getIdPelayanan());
        if (flagTutup){
            // jika ditutup maka buat registered date bulan depan
            Integer tahunDepan = new Integer(0);
            Integer bulanDepan = new Integer(0);

            if ("12".equalsIgnoreCase(bulan)){
                tahunDepan = Integer.valueOf(tahun) + 1;
                bulanDepan = 1;
            } else {
                tahunDepan = Integer.valueOf(tahun);
                bulanDepan = Integer.valueOf(bulan) + 1;
            }

            String stDate = tahunDepan+"-"+bulanDepan+"-"+"1";
            transaksiStokEntity.setRegisteredDate(java.sql.Date.valueOf(stDate));
        } else {
            transaksiStokEntity.setRegisteredDate(new java.sql.Date(obatEntity.getLastUpdate().getTime()));
        }

        // jika ada saldo lalu
        if (saldoBulanLalu.getQtyLalu() != null && saldoBulanLalu.getQtyLalu().compareTo(new BigInteger(String.valueOf(0))) == 1){
            transaksiStokEntity.setQtyLalu(stokBulanLalu.getQtySaldo());
            transaksiStokEntity.setTotalLalu(saldoBulanLalu.getTotalSaldo());
            transaksiStokEntity.setSubTotalLalu(saldoBulanLalu.getSubTotal());
        } else {
            transaksiStokEntity.setQtyLalu(new BigInteger(String.valueOf(0)));
            transaksiStokEntity.setTotalLalu(new BigDecimal(0));
            transaksiStokEntity.setSubTotalLalu(new BigDecimal(0));
        }


        try {
            transaksiStokDao.addAndSave(transaksiStokEntity);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.saveTransaksiStok] ERROR.", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.saveTransaksiStok] ERROR." + e.getMessage());
        }

        logger.info("[TransaksiObatBoImpl.saveTransaksiStok] END <<<<");
    }

    @Override
    public void saveTransaksiStokOpname(Obat bean) throws GeneralBOException {

        logger.info("[ObatBoImpl.saveTransaksiStokOpname] START >>>");
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        String tahun = CommonUtil.getDateParted(date, "YEAR");
        String bulan = CommonUtil.getDateParted(date, "MONTH");

        boolean flagTutup = false;
        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriod = batasTutupPeriodDao.getBatasPeriodeDitutup(bean.getBranchId(), bulan, tahun);
        if (batasTutupPeriod.size() > 0){
            // jika sudah ditutup bulan ini
            flagTutup = true;
        }

        TransaksiStok saldoBulanLalu = new TransaksiStok();

        // cari apakah data baru
        if (flagTutup){

            Integer tahunDepan = new Integer(0);
            Integer bulanDepan = new Integer(0);

            if ("12".equalsIgnoreCase(bulan)){
                bulanDepan = Integer.valueOf(1);
                tahunDepan = Integer.valueOf(tahun) + 1;
            } else {
                bulanDepan = Integer.valueOf(bulan) + 1;
                tahunDepan = Integer.valueOf(tahun);
            }

            // cari apakah data baru
            Map hsCriteria = new HashMap();
            hsCriteria.put("branch_id", bean.getBranchId());
            hsCriteria.put("id_barang", bean.getIdObat());
            hsCriteria.put("bulan", bulanDepan);
            hsCriteria.put("tahun", tahunDepan);
            hsCriteria.put("id_pelayanan", bean.getIdPelayanan());

            List<ItSimrsTransaksiStokEntity> transaksiStokEntities = transaksiStokDao.getByCriteria(hsCriteria);
            if (transaksiStokEntities.size() == 0){


                // jika sudah ditutup bulan ini
                // maka hitung saldo bulan ini sebagai saldo bulan lalu
                if (flagTutup){
                    List<TransaksiStok> saldoBulanLaluList = getListReporTransaksiObat(bean.getIdPelayanan(), tahun, bulan, bean.getIdObat());
                    if (saldoBulanLaluList.size() > 0){
                        saldoBulanLalu = saldoBulanLaluList.get(saldoBulanLaluList.size() -1);
                    }
                }
            }
        }

        Map hsCriteria = new HashMap();
        hsCriteria.put("branch_id", bean.getBranchId());
        hsCriteria.put("id_barang", bean.getIdObat());
        hsCriteria.put("bulan", Integer.valueOf(bulan));
        hsCriteria.put("tahun", Integer.valueOf(tahun));

        TransaksiStok stokBulanLalu = new TransaksiStok();
        List<ItSimrsTransaksiStokEntity> transaksiStokEntities = transaksiStokDao.getByCriteria(hsCriteria);
        if (transaksiStokEntities.size() == 0){

            // jika sudah ditutup bulan ini
            // maka hitung saldo bulan ini sebagai saldo bulan lalu
            if (flagTutup){
                saldoBulanLalu = getSumSaldoBulanLaluStok(transaksiStokEntities);
            } else {
                // jika data baru dibulan tersebut maka mengitung juga saldo bulan sebelumnya jika ada;
                // mencari data saldo bulan lalu
                // menghitung saldo lalu;

                Integer tahunLalu = new Integer(0);
                Integer bulanLalu = new Integer(0);

                if ("1".equalsIgnoreCase(bulan)){
                    bulanLalu = 12;
                    tahunLalu = Integer.valueOf(tahun) - 1;
                } else {
                    bulanLalu = Integer.valueOf(bulan) - 1;
                    tahunLalu = Integer.valueOf(tahun);
                }

                hsCriteria = new HashMap();
                hsCriteria.put("branch_id", bean.getBranchId());
                hsCriteria.put("id_barang", bean.getIdObat());
                hsCriteria.put("bulan", bulanLalu);
                hsCriteria.put("tahun", tahunLalu);

                transaksiStokEntities = new ArrayList<>();
                transaksiStokEntities = transaksiStokDao.getByCriteria(hsCriteria);
                stokBulanLalu = getSumSaldoBulanLaluStok(transaksiStokEntities);
            }
        }


        String pelayananTujuan = "";
        ImSimrsPelayananEntity pelayananEntity = pelayananDao.getById("idPelayanan", bean.getIdPelayanan());
        if (pelayananEntity != null){
            pelayananTujuan = pelayananEntity.getNamaPelayanan();
        }

        String namaObat = "";
        String idObat = "";
        BigInteger consBox = new BigInteger(String.valueOf(0));
        BigInteger consLembar = new BigInteger(String.valueOf(0));
        BigDecimal hargaBijian = new BigDecimal(String.valueOf(0));
        ImSimrsObatEntity obatEntity = obatDao.getById("idBarang", bean.getIdBarang());
        if (obatEntity != null){
            namaObat = obatEntity.getNamaObat();
            consLembar = obatEntity.getBijiPerLembar();
            consBox = obatEntity.getLembarPerBox().multiply(consLembar);
            idObat = obatEntity.getIdObat();
            hargaBijian = obatEntity.getAverageHargaBiji();
        }


        java.util.Date now = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String seq = transaksiStokDao.getNextSeq();
        String idBarangMasuk = "RB"+ bean.getBranchId() + f.format(now) + seq;

        ItSimrsTransaksiStokEntity transaksiStokEntity = new ItSimrsTransaksiStokEntity();
        transaksiStokEntity.setIdTransaksi(idBarangMasuk);
        transaksiStokEntity.setIdObat(idObat);
        transaksiStokEntity.setKeterangan("Retur " + namaObat + ". dari "+pelayananTujuan+" ke Vendor " + bean.getNamaVendor());
        transaksiStokEntity.setTipe("K");
        transaksiStokEntity.setBranchId(bean.getBranchId());
        transaksiStokEntity.setQty(bean.getQty());
        transaksiStokEntity.setTotal(hargaBijian);
        transaksiStokEntity.setSubTotal(hargaBijian.multiply(new BigDecimal(bean.getQty())));
        transaksiStokEntity.setCreatedDate(bean.getCreatedDate());
        transaksiStokEntity.setCreatedWho(bean.getCreatedWho());
        transaksiStokEntity.setLastUpdate(bean.getCreatedDate());
        transaksiStokEntity.setLastUpdateWho(bean.getCreatedWho());
        transaksiStokEntity.setIdBarang(bean.getIdBarang());
        transaksiStokEntity.setIdPelayanan(bean.getIdPelayanan());
        if (flagTutup){
            // jika ditutup maka buat registered date bulan depan
            Integer tahunDepan = new Integer(0);
            Integer bulanDepan = new Integer(0);

            if ("12".equalsIgnoreCase(bulan)){
                tahunDepan = Integer.valueOf(tahun) + 1;
                bulanDepan = 1;
            } else {
                tahunDepan = Integer.valueOf(tahun);
                bulanDepan = Integer.valueOf(bulan) + 1;
            }

            String stDate = tahunDepan+"-"+bulanDepan+"-"+"1";
            transaksiStokEntity.setRegisteredDate(java.sql.Date.valueOf(stDate));
        } else {
            transaksiStokEntity.setRegisteredDate(new java.sql.Date(obatEntity.getLastUpdate().getTime()));
        }

        // jika ada saldo lalu
        if (saldoBulanLalu.getQtySaldo() != null && saldoBulanLalu.getQtySaldo().compareTo(new BigInteger(String.valueOf(0))) == 1){
            transaksiStokEntity.setQtyLalu(saldoBulanLalu.getQtySaldo());
            transaksiStokEntity.setTotalLalu(saldoBulanLalu.getTotalSaldo());
            transaksiStokEntity.setSubTotalLalu(saldoBulanLalu.getSubTotalSaldo());
        } else {
            transaksiStokEntity.setQtyLalu(new BigInteger(String.valueOf(0)));
            transaksiStokEntity.setTotalLalu(new BigDecimal(0));
            transaksiStokEntity.setSubTotalLalu(new BigDecimal(0));
        }


        try {
            transaksiStokDao.addAndSave(transaksiStokEntity);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.saveTransaksiStok] ERROR.", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.saveTransaksiStok] ERROR." + e.getMessage());
        }

        logger.info("[ObatBoImpl.saveTransaksiStokOpname] END <<<");
    }

    @Override
    public ImSimrsObatEntity getObatEntityByKodeBarang(String id) throws GeneralBOException {
        return obatDao.getById("idBarang", id);
    }

    @Override
    public List<TransaksiStok> getListReportSumaryTransaksiObat(String idPelayanan, String tahun, String bulan) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListReportSumaryTransaksiObat] START >>>");

        List<TransaksiStok> listObat = transaksiStokDao.getListOpnameByPeriode(idPelayanan, tahun, bulan);
        if (listObat.size() > 0){

            for (TransaksiStok obat : listObat){

                obat.setKeterangan(obat.getNamaObat());

                List<TransaksiStok> listTransaksi =  getListReporTransaksiObat(idPelayanan, tahun, bulan, obat.getIdObat());
                if (listTransaksi.size() > 0){
                    TransaksiStok saldoAkhir = listTransaksi.get(listTransaksi.size()-1);
                    if (saldoAkhir != null && saldoAkhir.getSubTotalSaldo().compareTo(new BigDecimal(0)) == 1){
                        obat.setQtySaldo(saldoAkhir.getQtySaldo());
                        obat.setTotalSaldo(saldoAkhir.getTotalSaldo());
                        obat.setSubTotalSaldo(saldoAkhir.getSubTotalSaldo());
                    }
                }

//                TransaksiStok saldoLalu = transaksiStokDao.getSaldoBulanLaluByPeriode(idPelayanan, obat.getIdObat(), tahun, bulan);
//                if (saldoLalu != null && saldoLalu.getSubTotalLalu().compareTo(new BigDecimal(0)) == 1){
//
//
//                } else {
//
//                    List<TransaksiStok> listSaldo = transaksiStokDao.getSaldoOpnameByPeriode(idPelayanan, obat.getIdObat(), tahun, bulan);
//                    if (listSaldo.size() > 0){
//                    }
//                }
            }
        }

        logger.info("[ObatBoImpl.getListReportSumaryTransaksiObat] END <<<");
        return listObat;
    }

    @Override
    public List<TransaksiStok> getListSummaryStok(String branchId,String idPelayanan, String tahun, String bulan,String namaObat) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListSummaryStok] START >>>");

        List<TransaksiStok> listObat = transaksiStokDao.getListObatForPengajuan(branchId,namaObat);
        if (listObat.size() > 0){

            for (TransaksiStok obat : listObat){
                //initial
                obat.setQtySaldo(BigInteger.ZERO);
                obat.setTotalSaldo(BigDecimal.ZERO);
                obat.setSubTotalSaldo(BigDecimal.ZERO);

                obat.setKeterangan(obat.getNamaObat());

                List<TransaksiStok> listTransaksi =  transaksiStokDao.getSaldoObatLalu(branchId,idPelayanan, tahun, bulan, obat.getIdObat());
                for (TransaksiStok transaksiStok : listTransaksi){
                    obat.setQtySaldo(transaksiStok.getQtySaldo());
                    obat.setTotalSaldo(transaksiStok.getTotalSaldo());
                    obat.setSubTotalSaldo(transaksiStok.getSubTotalSaldo());
                }
            }
        }

        logger.info("[ObatBoImpl.getListSummaryStok] END <<<");
        return listObat;
    }

    @Override
    public List<KandunganObat> getListKandunganObatDetail(String idObat) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListKandunganObat] START >>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat",idObat);
        hsCriteria.put("flag","Y");

        List<KandunganObat> kandunganObats = new ArrayList<>();
        List<ImSimrsKandunganObatDetailEntity> kandunganObatDetailEntities = kandunganObatDetailDao.getByCriteria(hsCriteria);
        if (kandunganObatDetailEntities.size() > 0){
            for (ImSimrsKandunganObatDetailEntity kandunganObatDetailEntity : kandunganObatDetailEntities){
                KandunganObat kandunganObat = new KandunganObat();

                ImSimrsKandunganObatEntity kandunganObatEntity = kandunganObatDao.getById("idKandungan", kandunganObatDetailEntity.getIdKandungan());
                if (kandunganObatEntity != null){
                    kandunganObat.setKandungan(kandunganObatEntity.getKandungan());
                }

                if (kandunganObatDetailEntity.getIdObat() != null && !"".equalsIgnoreCase(kandunganObatDetailEntity.getIdObat())){
                    ImSimrsObatEntity obatEntity = getObatByIdObat(kandunganObatDetailEntity.getIdObat());
                    if (obatEntity != null){
                        kandunganObat.setNamaObat(obatEntity.getNamaObat());
                    }
                }

                if (kandunganObatDetailEntity.getBentuk() != null && !"".equalsIgnoreCase(kandunganObatDetailEntity.getBentuk())){
                    ImSimrsBentukBarangEntity bentukBarangEntity = getBentukBarangById(kandunganObatDetailEntity.getBentuk());
                    if (bentukBarangEntity != null){
                        kandunganObat.setNamaBentuk(bentukBarangEntity.getBentuk());
                    }
                }

                kandunganObat.setId(kandunganObatDetailEntity.getId());
                kandunganObat.setIdKandungan(kandunganObatDetailEntity.getIdKandungan());
                kandunganObat.setIdObat(kandunganObatDetailEntity.getIdObat());
                kandunganObat.setBentuk(kandunganObatDetailEntity.getBentuk());
                kandunganObat.setSediaan(kandunganObatDetailEntity.getSediaan());
                kandunganObat.setSatuanSediaan(kandunganObatDetailEntity.getSatuanSediaan());
                kandunganObat.setFlag(kandunganObatDetailEntity.getFlag());
                kandunganObat.setAction(kandunganObatDetailEntity.getAction());
                kandunganObat.setCreatedDate(kandunganObatDetailEntity.getCreatedDate());
                kandunganObat.setCreatedWho(kandunganObatDetailEntity.getCreatedWho());
                kandunganObat.setLastUpdate(kandunganObatDetailEntity.getLastUpdate());
                kandunganObat.setLastUpdateWho(kandunganObatDetailEntity.getLastUpdateWho());
                kandunganObats.add(kandunganObat);
            }
        }

        logger.info("[ObatBoImpl.getListKandunganObat] END <<<");
        return kandunganObats;
    }

    public List<ImSimrsKandunganObatDetailEntity> getEntityKandunganObatDetail(String idObat) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListKandunganObat] START >>>");
        List<ImSimrsKandunganObatDetailEntity> kandunganObatDetailEntities = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat",idObat);
        try{
            kandunganObatDetailEntities = kandunganObatDetailDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        logger.info("[ObatBoImpl.getListKandunganObat] END <<<");
        return kandunganObatDetailEntities;
    }

    @Override
    public ImSimrsKandunganObatEntity getMasterKandunganObatById(String idKandunganObat) throws GeneralBOException {
        return kandunganObatDao.getById("idKandungan", idKandunganObat);
    }

    @Override
    public ImSimrsObatEntity getObatByIdObat(String idObat) throws GeneralBOException {

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", idObat);
        hsCriteria.put("desc", "Y");
        hsCriteria.put("limit","1");

        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();
        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getObatByIdObat] ERROR.", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getObatByIdObat] ERROR." + e.getMessage());
        }

        return obatEntities.size() > 0 ? obatEntities.get(0) : null;
    }

    @Override
    public List<ImSimrsKandunganObatEntity> getListAllKandunganObat() throws GeneralBOException {
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        return kandunganObatDao.getByCriteria(hsCriteria);
    }

    @Override
    public String generateIdKandungan() throws GeneralBOException {
        return "KDD" + kandunganObatDetailDao.getNextId();
    }

    @Override
    public List<ImSimrsBentukBarangEntity> getLitBentukBarangByFlag(String flag) throws GeneralBOException {

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", flag == null || "".equalsIgnoreCase(flag) ? "Y" : flag);

        List<ImSimrsBentukBarangEntity> bentukBarangEntities = new ArrayList<>();
        try {
            bentukBarangEntities = bentukBarangDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getBentukBarangByFlag] ERROR.", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getBentukBarangByFlag] ERROR." + e.getMessage());
        }

        return bentukBarangEntities;
    }

    @Override
    public ImSimrsBentukBarangEntity getBentukBarangById(String idBentuk) throws GeneralBOException {
        return bentukBarangDao.getById("idBentuk", idBentuk);
    }

    @Override
    public List<Aging> getListAging(String branchId, String idPelayanan, String periode) throws GeneralBOException {

        List<Aging> listAgingObat = obatDao.getAgingStokObat(branchId, idPelayanan, periode);
        if (listAgingObat != null && listAgingObat.size() > 0){
            for (Aging agingObat : listAgingObat){

                TransaksiStok transaksiStok = getSumKreditTransaksiStok(agingObat.getNoNota(), periode, branchId, agingObat.getMasterId());
                if (transaksiStok != null && transaksiStok.getQty() != null){
//                    BigDecimal totalKredit = new BigDecimal(transaksiStok.getQty());
//                    BigDecimal total = agingObat.getTotal().subtract(totalKredit);
//                    agingObat.setTotal(total);
                }
            }
        }
        return listAgingObat;
    }
    private TransaksiStok getSumKreditTransaksiStok(String idBarang, String periode, String branchId, String idPelayanan){
        return obatDao.getSumKreditByPeriodeTransaksiStok(branchId, idPelayanan, periode, idBarang);
    }

    @Override
    public List<ImSimrsKategoriPersediaanEntity> getAllKategoriPersediaan() throws GeneralBOException {
        logger.info("[ObatBoImpl.getAllKategoriPersediaan] START >>>");
        logger.info("[ObatBoImpl.getAllKategoriPersediaan] END <<<");
        return kategoriPersedianDao.getAll();
    }

    @Override
    public List<ImSimrsBentukBarangEntity> getAllBentukBarang() throws GeneralBOException {
        logger.info("[ObatBoImpl.getAllBentukBarang] START >>>");
        logger.info("[ObatBoImpl.getAllBentukBarang] END <<<");
        return bentukBarangDao.getAll();
    }

    @Override
    public ImSimrsHeaderObatEntity getHeaderObatById(String id) throws GeneralBOException {
        logger.info("[ObatBoImpl.getAllBentukBarang] START >>>");

        ImSimrsHeaderObatEntity headerObatEntity = new ImSimrsHeaderObatEntity();
        try {
            headerObatEntity = headerObatDao.getById("idObat", id);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getHeaderObatById] ERROR.", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getHeaderObatById] ERROR." + e.getMessage());
        }

        logger.info("[ObatBoImpl.getAllBentukBarang] END <<<");
        return headerObatEntity;
    }
}