package com.neurix.simrs.transaksi.transaksiobat.bo.impl;

import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.vendor.dao.VendorDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.hargaobat.dao.HargaObatDao;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsObatPoliEntity;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsPermintaanObatPoliEntity;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.riwayatbarang.dao.TransaksiStokDao;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsTransaksiStokEntity;
import com.neurix.simrs.transaksi.riwayatbarang.model.TransaksiStok;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.dao.*;
import com.neurix.simrs.transaksi.transaksiobat.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


/**
 * Created by Toshiba on 11/12/2019.
 */
public class TransaksiObatBoImpl implements TransaksiObatBo {
    private static transient Logger logger = Logger.getLogger(TransaksiObatBoImpl.class);

    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private RiwayatTransaksiObatDao riwayatTransaksiObatDao;
    private TransaksiObatDetailDao transaksiObatDetailDao;
    private PelayananDao pelayananDao;
    private ObatDao obatDao;
    private PermintaanResepDao permintaanResepDao;
    private PasienDao pasienDao;
    private RiwayatTransPembelianObatDao riwayatTransPembelianObatDao;
    private ObatPoliDao obatPoliDao;
    private VendorDao vendorDao;
    private TransaksiObatDetailBatchDao batchDao;
    private HargaObatDao hargaObatDao;
    private TransaksiStokDao transaksiStokDao;
    private BatasTutupPeriodDao batasTutupPeriodDao;

    @Override
    public List<TransaksiObatDetail> getSearchObatTransaksiByCriteria(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getSearchObatTransaksiByCriteria] START >>>>>>");

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = getListEntityTransObatDetail(bean);

        if (obatDetailEntities.size() > 0) {
            TransaksiObatDetail transaksiObatDetail;
            for (ImtSimrsTransaksiObatDetailEntity obatDetailEntity : obatDetailEntities) {
                transaksiObatDetail = new TransaksiObatDetail();
                transaksiObatDetail.setIdTransaksiObatDetail(obatDetailEntity.getIdTransaksiObatDetail());
                transaksiObatDetail.setIdObat(obatDetailEntity.getIdObat());
                transaksiObatDetail.setIdApprovalObat(obatDetailEntity.getIdApprovalObat());
                transaksiObatDetail.setQty(obatDetailEntity.getQty());
                transaksiObatDetail.setQtyApprove(obatDetailEntity.getQtyApprove());
                transaksiObatDetail.setFlag(obatDetailEntity.getFlag());
                transaksiObatDetail.setAction(obatDetailEntity.getAction());
                transaksiObatDetail.setCreatedDate(obatDetailEntity.getCreatedDate());
                transaksiObatDetail.setCreatedWho(obatDetailEntity.getCreatedWho());
                transaksiObatDetail.setLastUpdate(obatDetailEntity.getLastUpdate());
                transaksiObatDetail.setLastUpdateWho(obatDetailEntity.getLastUpdateWho());
                transaksiObatDetail.setJenisSatuan(obatDetailEntity.getJenisSatuan());
                transaksiObatDetail.setFlagVerifikasi(obatDetailEntity.getFlagVerifikasi());


                ImSimrsObatEntity obatEntity = getObatById(obatDetailEntity.getIdObat());
                if (obatEntity != null) {

                    BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());

                    transaksiObatDetail.setNamaObat(obatEntity.getNamaObat());
                    transaksiObatDetail.setHarga(obatEntity.getHarga());
                    transaksiObatDetail.setIdPabrik(obatEntity.getIdPabrik());

                    HargaObat hargaObat = new HargaObat();
                    hargaObat.setIdObat(obatDetailEntity.getIdObat());
                    List<MtSimrsHargaObatEntity> hargaObatEntities = getListEntityHargaObat(hargaObat);
                    if (hargaObatEntities.size() > 0) {
                        MtSimrsHargaObatEntity hargaObatEntity = hargaObatEntities.get(0);

                        if ("box".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {
                            transaksiObatDetail.setHarga(hargaObatEntity.getHargaJual().multiply(new BigDecimal(cons)).toBigInteger());
                        }
                        if ("lembar".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {

                            BigInteger bHarga = hargaObatEntity.getHargaJual().toBigInteger();
                            BigInteger nHarga = obatEntity.getBijiPerLembar().multiply(bHarga);
                            transaksiObatDetail.setHarga(nHarga);
                        }
                        if ("biji".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {
                            transaksiObatDetail.setHarga(hargaObatEntity.getHargaJual().toBigInteger());
                        }

                    }

                    // harga*qty
                    BigInteger total = transaksiObatDetail.getHarga().multiply(transaksiObatDetail.getQty());
                    transaksiObatDetail.setTotalHarga(total);
                }

                PermintaanResep permintaanResep = new PermintaanResep();
                permintaanResep.setIdApprovalObat(transaksiObatDetail.getIdApprovalObat());
                ImSimrsPermintaanResepEntity resepEntity = getEntityPermintaanResepByCriteria(permintaanResep);
                if (resepEntity != null) {
                    transaksiObatDetail.setJenisResep(resepEntity.getJenisResep());
                    transaksiObatDetail.setIdPelayananTujuan(resepEntity.getTujuanPelayanan());
                    transaksiObatDetail.setIdPermintaanResep(resepEntity.getIdPermintaanResep());
                    ImSimrsPasienEntity pasienEntity = getEntityPasienById(resepEntity.getIdPasien());
                    if (pasienEntity != null) {
                        transaksiObatDetail.setNamaPasien(pasienEntity.getNama());
                    }
                }

                obatDetailList.add(transaksiObatDetail);
            }
        }

        logger.info("[TransaksiObatBoImpl.getSearchObatTransaksiByCriteria] END <<<<<");
        return obatDetailList;
    }

    @Override
    public void saveAdd(TransaksiObatDetail bean, List<TransaksiObatDetail> listOfObatResep, List<TransaksiObatDetail> listOfObat) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.saveAdd] START >>>>>>");

        List<ImtSimrsRiwayatTransaksiObatEntity> listOfTransaksi = new ArrayList<>();
        if (bean != null) {
            if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())) {
                // update flag list of detail obat for resep
                if (listOfObatResep != null && listOfObatResep.size() > 0) {
                    for (TransaksiObatDetail resep : listOfObatResep) {
                        ImtSimrsTransaksiObatDetailEntity obatDetailEntity = getEntityTransObatDetailById(resep.getIdTransaksiObatDetail());
                        obatDetailEntity.setQty(resep.getQty());
                        obatDetailEntity.setFlag("N");
                        obatDetailEntity.setAction("U");
                        obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                        obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {
                            transaksiObatDetailDao.updateAndSave(obatDetailEntity);
                        } catch (HibernateException e) {
                            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ", e);
                            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. " + e.getMessage());
                        }

                        // kurangi stock obat
                        obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                        obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        TransaksiObatBatch batch = new TransaksiObatBatch();
                        batch.setIdTransaksiObatDetail(resep.getIdTransaksiObatDetail());
                        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchByCriteria(batch);
                        if (batchEntities.size() > 0) {

                            for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntities) {

                                if ("Y".equalsIgnoreCase(batchEntity.getStatus())) {
                                    TransaksiObatDetail newTrans = new TransaksiObatDetail();
                                    newTrans.setIdTransaksiObatDetail(resep.getIdTransaksiObatDetail());
                                    newTrans.setQtyApprove(batchEntity.getQtyApprove());
                                    newTrans.setIdBarang(batchEntity.getIdBarang());
                                    newTrans.setJenisSatuan(batchEntity.getJenisSatuan());

                                    updateSubstractStockObatApotek(newTrans, bean.getIdPelayanan(), bean.getBranchId());

                                    batchEntity.setApproveFlag("Y");
                                    batchEntity.setDiterimaFlag("Y");
                                } else {
                                    batchEntity.setApproveFlag("N");
                                    batchEntity.setDiterimaFlag("N");
                                }

                                batchEntity.setAction("U");
                                batchEntity.setLastUpdate(bean.getLastUpdate());
                                batchEntity.setLastUpdateWho(bean.getLastUpdateWho());

                                try {
                                    batchDao.updateAndSave(batchEntity);
                                } catch (HibernateException e) {
                                    logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when update obat batch. ", e);
                                    throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when update obat batch. " + e.getMessage());
                                }
                            }
                        }
                    }

                    PermintaanResep permintaanResep = new PermintaanResep();
                    permintaanResep.setIdPermintaanResep(bean.getIdPermintaanResep());
                    // update flag permintaan resep data
                    ImSimrsPermintaanResepEntity resepEntity = getEntityPermintaanResepByCriteria(permintaanResep);
                    if (resepEntity != null) {
                        resepEntity.setFlag("N");
                        resepEntity.setAction("U");
                        resepEntity.setLastUpdate(bean.getLastUpdate());
                        resepEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {
                            permintaanResepDao.updateAndSave(resepEntity);
                        } catch (HibernateException e) {
                            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ", e);
                            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. " + e.getMessage());
                        }
                    }
                    // updarte approval transaksi for resep
                    ImtSimrsApprovalTransaksiObatEntity approvalEntity = getApprovalTransaksiById(resepEntity.getIdApprovalObat());
                    if (approvalEntity != null) {
                        approvalEntity.setApprovalFlag("Y");
                        approvalEntity.setApprovePerson(bean.getLastUpdateWho());
                        approvalEntity.setFlag("N");
                        approvalEntity.setAction("U");
                        approvalEntity.setLastUpdate(bean.getLastUpdate());
                        approvalEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            approvalTransaksiObatDao.updateAndSave(approvalEntity);
                        } catch (HibernateException e) {
                            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ", e);
                            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. " + e.getMessage());
                        }
                    }
                    // record to list riwayat transaksi
                    ImtSimrsRiwayatTransaksiObatEntity transaksiObatEntity = new ImtSimrsRiwayatTransaksiObatEntity();
                    transaksiObatEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                    transaksiObatEntity.setTipePermintaan(approvalEntity.getTipePermintaan());
                    transaksiObatEntity.setFlag("Y");
                    transaksiObatEntity.setAction("C");
                    transaksiObatEntity.setCreatedDate(bean.getLastUpdate());
                    transaksiObatEntity.setCreatedWho(bean.getLastUpdateWho());
                    transaksiObatEntity.setLastUpdate(bean.getLastUpdate());
                    transaksiObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    listOfTransaksi.add(transaksiObatEntity);
                }
            }

            // if list obat pembelian not null
            if (listOfObat != null && listOfObat.size() > 0) {
                ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
                String id = getNextApprovalObatId();
                approvalEntity.setIdApprovalObat("INV" + id);
                approvalEntity.setApprovalFlag("Y");
                approvalEntity.setTipePermintaan("000");
                approvalEntity.setApprovePerson(bean.getLastUpdateWho());
                approvalEntity.setFlag("N");
                approvalEntity.setAction("U");
                approvalEntity.setCreatedDate(bean.getLastUpdate());
                approvalEntity.setCreatedWho(bean.getLastUpdateWho());
                approvalEntity.setLastUpdate(bean.getLastUpdate());
                approvalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                approvalEntity.setBranchId(bean.getBranchId());

                try {
                    approvalTransaksiObatDao.addAndSave(approvalEntity);
                } catch (HibernateException e) {
                    logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ", e);
                    throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. " + e.getMessage());
                }

                for (TransaksiObatDetail obatDetail : listOfObat) {
                    ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                    id = getNextTransaksiObatDetail();
                    obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
                    obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                    obatDetailEntity.setIdObat(obatDetail.getIdObat());
                    obatDetailEntity.setFlag("N");
                    obatDetailEntity.setAction("C");
                    obatDetailEntity.setQty(obatDetail.getQty());
                    obatDetailEntity.setCreatedDate(bean.getLastUpdate());
                    obatDetailEntity.setCreatedWho(bean.getLastUpdateWho());
                    obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                    obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        transaksiObatDetailDao.addAndSave(obatDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ", e);
                        throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. " + e.getMessage());
                    }

                    // kurangi stock obat
                    obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                    obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
//                    updateSubstractStockObatApotek(obatDetailEntity, CommonUtil.userPelayananIdLogin(), CommonUtil.userBranchLogin());
//                    ImSimrsObatEntity obatEntity = getObatById(obatDetail.getIdObat());
//                    if (obatEntity != null){
//
//                        obatEntity.setQty(obatEntity.getQty().subtract(obatDetail.getQty()));
//                        obatEntity.setAction("U");
//                        obatEntity.setLastUpdate(bean.getLastUpdate());
//                        obatEntity.setLastUpdateWho(bean.getLastUpdateWho());
//                        try {
//
//                        } catch (HibernateException e)
//                        {
//                            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ",e);
//                            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. "+ e.getMessage());
//                        }
//                    }
                }

                // record to list riwayat transaksi
                ImtSimrsRiwayatTransaksiObatEntity transaksiObatEntity = new ImtSimrsRiwayatTransaksiObatEntity();
                transaksiObatEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                transaksiObatEntity.setTipePermintaan(approvalEntity.getTipePermintaan());
                transaksiObatEntity.setFlag("Y");
                transaksiObatEntity.setAction("C");
                transaksiObatEntity.setCreatedDate(bean.getLastUpdate());
                transaksiObatEntity.setCreatedWho(bean.getLastUpdateWho());
                transaksiObatEntity.setLastUpdate(bean.getLastUpdate());
                transaksiObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                listOfTransaksi.add(transaksiObatEntity);
            }
        }

        // save riwayat transaksi
        saveRiwayatTransaksi(listOfTransaksi, bean);
        logger.info("[TransaksiObatBoImpl.saveAdd] END <<<<<");
    }

    private void saveRiwayatTransaksi(List<ImtSimrsRiwayatTransaksiObatEntity> beans, TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.saveRiwayatTransaksi] START >>>>>>");

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String stDate = dateFormat.format(now);
        String id = getNextTransPembelian();
        String noPemebelian = bean.getBranchId() + stDate + id;

        Integer compare = bean.getTotalBayar().compareTo(bean.getNominal());

        if (bean.getNominal() == null)
            bean.setNominal(new BigInteger(String.valueOf(0)));
        if (bean.getKembalian() == null)
            bean.setKembalian(new BigInteger(String.valueOf(0)));
        if (compare == -1)
            bean.setKembalian(bean.getNominal().subtract(bean.getTotalBayar()));

        MtSimrsRiwayatPembelianObat pembelianObat = new MtSimrsRiwayatPembelianObat();
        pembelianObat.setNoPembelianObat(noPemebelian);
        pembelianObat.setTotalBayar(bean.getTotalBayar());
        pembelianObat.setNominal(bean.getNominal());
        pembelianObat.setTotalDibayar(bean.getNominal().subtract(bean.getKembalian()));
        pembelianObat.setKembalian(bean.getKembalian());
        pembelianObat.setFlag("Y");
        pembelianObat.setAction("C");
        pembelianObat.setCreatedDate(bean.getLastUpdate());
        pembelianObat.setCreatedWho(bean.getLastUpdateWho());

        try {
            riwayatTransPembelianObatDao.addAndSave(pembelianObat);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. " + e.getMessage());
        }

        for (ImtSimrsRiwayatTransaksiObatEntity obatEntity : beans) {
            id = getNextRiwayatTransaksiId();
            obatEntity.setIdRiwayatTransaksiObat("RTO" + id);
            obatEntity.setNoTransaksiPembelian(pembelianObat.getNoPembelianObat());

            try {
                riwayatTransaksiObatDao.addAndSave(obatEntity);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. ", e);
                throw new GeneralBOException("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. " + e.getMessage());
            }
        }
        logger.info("[TransaksiObatBoImpl.saveRiwayatTransaksi] END <<<<<");
    }

    private void updateSubstractStockObatApotek(TransaksiObatDetail bean, String idPoli, String branchId) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.updateSubstractStockObatApotek] START >>>>>>>>>>");

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdPelayanan(idPoli);
        obatPoli.setIdBarang(bean.getIdBarang());
        obatPoli.setIdObat(bean.getIdObat());
        obatPoli.setBranchId(branchId);
        obatPoli.setFlag("Y");

        List<MtSimrsObatPoliEntity> obatPoliEntities = getListEntityObatPoli(obatPoli);

        MtSimrsObatPoliEntity obatPoliEntity = new MtSimrsObatPoliEntity();
        if (obatPoliEntities.size() > 0) {
            obatPoliEntity = obatPoliEntities.get(0);
        }

        Obat obat = new Obat();
        obat.setIdBarang(bean.getIdBarang());
        obat.setBranchId(bean.getBranchId());

        List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);
        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();

        if (obatEntities.size() > 0) {
            obatEntity = obatEntities.get(0);
        }


        if (obatPoliEntity.getPrimaryKey() != null && obatEntity.getIdBarang() != null) {

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
                            logger.error("[ObatPoliBoImpl.updateSubstractStockObatApotek] jumlah yang diminta melebihi stock");
                            throw new GeneralBOException("[ObatPoliBoImpl.updateSubstractStockObatApotek] jumlah yang diminta melebihi stock");
                        }
                    }
                }
            }

            if ("biji".equalsIgnoreCase(bean.getJenisSatuan())) {
                if (qtyBiji.compareTo(bean.getQtyApprove()) == 1) {
                    obatPoliEntity.setQtyBiji(qtyBiji.subtract(bean.getQtyApprove()));
                } else {
                    if (obatEntity.getBijiPerLembar().compareTo(bean.getQtyApprove()) == 1 && obatPoliEntity.getQtyLembar().compareTo(new BigInteger(String.valueOf(0))) == 1) {
                        obatPoliEntity.setQtyLembar(obatPoliEntity.getQtyLembar().subtract(new BigInteger(String.valueOf(1))));
                        obatPoliEntity.setQtyBiji((qtyBiji.add(obatEntity.getBijiPerLembar())).subtract(bean.getQtyApprove()));
                    } else {
                        BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());
                        BigInteger jmlAllStockBiji = (qtyBox.multiply(cons))
                                .add(obatPoliEntity.getQtyLembar().multiply(obatEntity.getBijiPerLembar()))
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
                            logger.error("[ObatPoliBoImpl.updateSubstractStockObatApotek] jumlah yang diminta melebihi stock");
                            throw new GeneralBOException("[ObatPoliBoImpl.updateSubstractStockObatApotek] jumlah yang diminta melebihi stock");
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
                logger.error("[ObatPoliBoImpl.updateSubstractStockObatApotek] ERROR when update master obat poli. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.updateSubstractStockObatApotek] ERROR when update master obat poli. ", e);
            }

            //saveTransaksiStok(bean, bean.getJenisSatuan(), bean.getIdPelayanan(), bean.getIdPermintaanResep());
        }
        logger.info("[ObatPoliBoImpl.updateSubstractStockObatApotek] END <<<<<<<<<<");
    }

//    private void saveTransaksiStok(TransaksiObatDetail bean, String jenisSatuan, String idPelayanan, String idPermintanResep){
//
//        String pelayananAsal = "";
//        ImSimrsPelayananEntity pelayananEntity = pelayananDao.getById("idPelayanan",idPelayanan);
//        if (pelayananEntity != null){
//            pelayananAsal = pelayananEntity.getNamaPelayanan();
//        }
//
//        String namaObat = "";
//        String idObat = "";
//        BigInteger consBox = new BigInteger(String.valueOf(0));
//        BigInteger consLembar = new BigInteger(String.valueOf(0));
//        BigDecimal hargaBijian = new BigDecimal(String.valueOf(0));
//        ImSimrsObatEntity obatEntity = obatDao.getById("idBarang", bean.getIdBarang());
//        if (obatEntity != null){
//            namaObat = obatEntity.getNamaObat();
//            consLembar = obatEntity.getBijiPerLembar();
//            consBox = obatEntity.getLembarPerBox().multiply(consLembar);
//            idObat = obatEntity.getIdObat();
//            hargaBijian = obatEntity.getAverageHargaBiji();
//        }
//
//        //BigDecimal hargaObat = new BigDecimal(0);
//        BigInteger qty = new BigInteger(String.valueOf(0));
//
//        if ("box".equalsIgnoreCase(jenisSatuan)){
//            qty = bean.getQtyApprove().multiply(consBox);
//        } else if ("lembar".equalsIgnoreCase(jenisSatuan)){
//            qty = bean.getQtyApprove().multiply(consLembar);
//        } else {
//            qty = bean.getQtyApprove();
//        }
//
//
//        java.util.Date now = new java.util.Date();
//        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//        String seq = transaksiStokDao.getNextSeq();
//        String idBarangMasuk = "RB"+ bean.getBranchId() + f.format(now) + seq;
//
//        ItSimrsTransaksiStokEntity transaksiStokEntity = new ItSimrsTransaksiStokEntity();
//        transaksiStokEntity.setIdTransaksi(idBarangMasuk);
//        transaksiStokEntity.setIdObat(idObat);
//        transaksiStokEntity.setKeterangan("Pengeluaran Obat "+pelayananAsal+" Obat "+namaObat+" No. Transaksi Resep "+bean.getIdPermintaanResep());
//        transaksiStokEntity.setTipe("K");
//        transaksiStokEntity.setBranchId(bean.getBranchId());
//        transaksiStokEntity.setQty(qty);
//        transaksiStokEntity.setTotal(hargaBijian);
//        transaksiStokEntity.setSubTotal(hargaBijian.multiply(new BigDecimal(qty)));
//        transaksiStokEntity.setRegisteredDate(new java.sql.Date(obatEntity.getLastUpdate().getTime()));
//        transaksiStokEntity.setCreatedDate(obatEntity.getLastUpdate());
//        transaksiStokEntity.setCreatedWho(obatEntity.getLastUpdateWho());
//        transaksiStokEntity.setLastUpdate(obatEntity.getLastUpdate());
//        transaksiStokEntity.setLastUpdateWho(obatEntity.getLastUpdateWho());
//        transaksiStokEntity.setIdBarang(bean.getIdBarang());
//        transaksiStokEntity.setIdPelayanan(bean.getIdPelayanan());
//        transaksiStokEntity.setBranchId(bean.getBranchId());
//        try {
//            transaksiStokDao.addAndSave(transaksiStokEntity);
//        } catch (HibernateException e){
//            logger.error("[PermintaanVendorBoImpl.saveToRiwayatBarangMasuk] ERROR.", e);
//            throw new GeneralBOException("[PermintaanVendorBoImpl.saveToRiwayatBarangMasuk] ERROR." + e.getMessage());
//        }
//    }

    private List<MtSimrsObatPoliEntity> getListEntityObatPoli(ObatPoli bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListEntityObatPoli] START >>>>>>>>>>");
        List<MtSimrsObatPoliEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
            hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getIdBarang() != null && !"".equalsIgnoreCase(bean.getIdBarang())) {
            hsCriteria.put("id_barang", bean.getIdBarang());
        }
        if (bean.getIdPabrik() != null && !"".equalsIgnoreCase(bean.getIdPabrik())) {
            hsCriteria.put("id_pabrik", bean.getIdPabrik());
        }

        hsCriteria.put("flag", "Y");

        try {
            results = obatPoliDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data obat poli entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data obat poli entity by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.getListEntityObatPoli] END <<<<<<<<<<");
        return results;
    }

    private List<ImSimrsObatEntity> getListEntityObat(Obat bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListEntityObat] START >>>>>>>>>>");
        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdBarang() != null) {
            hsCriteria.put("id_barang", bean.getIdBarang());
        }
        if (bean.getIdObat() != null) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getBranchId() != null) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when get obat entity by criteria. ", e);
            throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when get obat entity by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.getListEntityObat] END <<<<<<<<<<");
        return obatEntities;
    }

    public List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getListEntityTransObatDetail] START >>>>>>");

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();

        try {
            obatDetailEntities = transaksiObatDetailDao.getListEntityTransObatDetails(bean);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when get data list entity of trans obat detail. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when get data list entity of trans obat detail. " + e.getMessage());
        }

        logger.info("[TransaksiObatBoImpl.getListEntityTransObatDetail] END <<<<<");
        return obatDetailEntities;
    }

    private MtSimrsObatPoliEntity getObaPolitById(ObatPoli bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getObaPolitById] START >>>>>>>>>>");
        List<MtSimrsObatPoliEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", bean.getIdObat());
        hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
        hsCriteria.put("branch_id", bean.getBranchId());
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = obatPoliDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getObaPolitById] ERROR when get obat entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
            return obatEntities.get(0);
        }

        logger.info("[ObatPoliBoImpl.getObaPolitById] END <<<<<<<<<<");
        return null;
    }

    private ImtSimrsTransaksiObatDetailEntity getEntityTransObatDetailById(String id) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getEntityTransObatDetailById] START >>>>>>");

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_transaksi_obat_detail", id);
        hsCriteria.put("flag", "Y");

        try {
            obatDetailEntities = transaksiObatDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getEntityTransObatDetailById] ERROR when get data entity of trans obat detail. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getEntityTransObatDetailById] ERROR when get data entity of trans obat detail. " + e.getMessage());
        }

        if (!obatDetailEntities.isEmpty() && obatDetailEntities.size() > 0) {
            logger.info("[TransaksiObatBoImpl.getEntityTransObatDetailById] END <<<<<");
            return obatDetailEntities.get(0);
        }
        logger.info("[TransaksiObatBoImpl.getEntityTransObatDetailById] END <<<<<");
        return null;
    }

    private ImtSimrsApprovalTransaksiObatEntity getApprovalTransaksiById(String id) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getApprovalTransaksiById] START >>>>>>>>>>");

        List<ImtSimrsApprovalTransaksiObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_approval_obat", id);
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = approvalTransaksiObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ", e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
            return obatEntities.get(0);
        }
        logger.info("[TransaksiObatBoImpl.getApprovalTransaksiById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsObatEntity getObatById(String id) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getObatById] START >>>>>>>>>>");
        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", id);
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
            return obatEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getObatById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsPelayananEntity getPoliById(String id) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getPoliById] START >>>>>>>>>>");
        List<ImSimrsPelayananEntity> pelayananEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pelayanan", id);
        hsCriteria.put("flag", "Y");

        try {
            pelayananEntities = pelayananDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getPoliById] ERROR when get data poli by criteria. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getPoliById] ERROR when get data poli by criteria. ", e);
        }

        if (!pelayananEntities.isEmpty() && pelayananEntities.size() > 0) {
            return pelayananEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getPoliById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsPermintaanResepEntity getEntityPermintaanResepByCriteria(PermintaanResep bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getEntityPermintaanResepById] START >>>>>>>>>>");
        List<ImSimrsPermintaanResepEntity> resepEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {
            hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
        }
        if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())) {
            hsCriteria.put("id_permintaan_resep", bean.getIdPermintaanResep());
        }
        hsCriteria.put("flag", "Y");

        try {
            resepEntities = permintaanResepDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getEntityPermintaanResepById] ERROR when get data resep header by criteria. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getEntityPermintaanResepById] ERROR when get data resep header  by criteria. ", e);
        }

        if (!resepEntities.isEmpty() && resepEntities.size() > 0) {
            return resepEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getEntityPermintaanResepById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsPasienEntity getEntityPasienById(String id) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getEntityPasienById] START >>>>>>>>>>");
        List<ImSimrsPasienEntity> pasienEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pasien", id);
        hsCriteria.put("flag", "Y");

        try {
            pasienEntities = pasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getEntityPasienById] ERROR when get data pasien by criteria. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getEntityPasienById] ERROR when get data pasien by criteria. ", e);
        }

        if (!pasienEntities.isEmpty() && pasienEntities.size() > 0) {
            return pasienEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getEntityPasienById] END <<<<<<<<<<");
        return null;
    }

    @Override
    public void saveVerifikasiObat(List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.saveVerifikasiObat] START >>>>>>>>>>");

        if (batchEntities.size() > 0) {
            for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntities) {

                TransaksiObatBatch obatBatch = new TransaksiObatBatch();
                obatBatch.setIdBarang(batchEntity.getIdBarang());
                obatBatch.setIdTransaksiObatDetail(batchEntity.getIdTransaksiObatDetail());

                List<MtSimrsTransaksiObatDetailBatchEntity> newBatchEntities = getListEntityBatchByCriteria(obatBatch);
                MtSimrsTransaksiObatDetailBatchEntity newBatchEntity = new MtSimrsTransaksiObatDetailBatchEntity();

                if (newBatchEntities.size() > 0) {
                    newBatchEntity = newBatchEntities.get(0);
                }

                if (newBatchEntity.getId() != null) {

                    newBatchEntity.setAction("U");
                    newBatchEntity.setLastUpdate(batchEntity.getLastUpdate());
                    newBatchEntity.setLastUpdateWho(batchEntity.getLastUpdateWho());

                    try {
                        batchDao.updateAndSave(newBatchEntity);
                    } catch (HibernateException e) {
                        logger.error("[TransaksiObatBoImpl.saveVerifikasiObat] ERROR when insert data batch. ", e);
                        throw new GeneralBOException("[TransaksiObatBoImpl.getEntityPasienById] ERROR when insert data batch. ", e);
                    }

                } else {

                    String seqBatch = batchDao.getNextId();
                    batchEntity.setId(new BigInteger(seqBatch));
                    batchEntity.setStatus("Y");
                    batchEntity.setNoBatch(1);

                    try {
                        batchDao.addAndSave(batchEntity);
                    } catch (HibernateException e) {
                        logger.error("[TransaksiObatBoImpl.saveVerifikasiObat] ERROR when update data batch. ", e);
                        throw new GeneralBOException("[TransaksiObatBoImpl.getEntityPasienById] ERROR when update data batch. ", e);
                    }
                }
            }

            MtSimrsTransaksiObatDetailBatchEntity entity = new MtSimrsTransaksiObatDetailBatchEntity();
            entity = batchEntities.get(0);

            if (entity != null) {

                ImtSimrsTransaksiObatDetailEntity entities = new ImtSimrsTransaksiObatDetailEntity();

                try {
                    entities = transaksiObatDetailDao.getById("idTransaksiObatDetail", entity.getIdTransaksiObatDetail());

                    if (entities != null) {

                        entities.setFlagVerifikasi("Y");
                        entities.setLastUpdate(entity.getLastUpdate());
                        entities.setLastUpdateWho(entity.getLastUpdateWho());

                        try {
                            transaksiObatDetailDao.updateAndSave(entities);
                        } catch (HibernateException e) {
                            logger.error("[TransaksiObatBoImpl.saveVerifikasiObat] ERROR when update data batch. ", e);
                            throw new GeneralBOException("[TransaksiObatBoImpl.getEntityPasienById] ERROR when update data batch. ", e);
                        }
                    }
                } catch (HibernateException e) {
                    logger.error("[TransaksiObatBoImpl.saveVerifikasiObat] ERROR when update data batch. ", e);
                    throw new GeneralBOException("[TransaksiObatBoImpl.getEntityPasienById] ERROR when update data batch. ", e);
                }
            }
        }

        logger.info("[TransaksiObatBoImpl.saveVerifikasiObat] END <<<<<<<<<<");
    }

    private String getNextApprovalObatId() throws GeneralBOException {
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getNextApprovalObatId] ERROR when get next seq. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getNextApprovalObatId] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextTransaksiObatDetail() throws GeneralBOException {
        String id = "";
        try {
            id = transaksiObatDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getNextTransaksiObatDetail] ERROR when get next seq. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getNextTransaksiObatDetail] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextRiwayatTransaksiId() throws GeneralBOException {
        String id = "";
        try {
            id = riwayatTransaksiObatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getNextRiwayatTransaksiId] ERROR when get next seq. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getNextRiwayatTransaksiId] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextTransPembelian() throws GeneralBOException {
        String id = "";
        try {
            id = riwayatTransPembelianObatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getNextTransPembelian] ERROR when get next seq. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getNextTransPembelian] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextPermintaanResepId() throws GeneralBOException {
        String id = "";
        try {
            id = permintaanResepDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getNextPermintaanResepId] ERROR when get next id resep. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getNextPermintaanResepId] ERROR when get next id resep. ", e);
        }
        return id;
    }


    @Override
    public List<TransaksiObatDetail> getByCriteria(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getByCriteria] START >>>>>>>");

        List<TransaksiObatDetail> listOfResults = new ArrayList<>();

        if (bean != null) {

            List<ImtSimrsTransaksiObatDetailEntity> resepDetail = getListEntityResepDetail(bean);
            if (!resepDetail.isEmpty() && resepDetail.size() > 0) {
                TransaksiObatDetail transaksiObatDetail;
                for (ImtSimrsTransaksiObatDetailEntity resepEntity : resepDetail) {
                    transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdTransaksiObatDetail(resepEntity.getIdTransaksiObatDetail());
                    transaksiObatDetail.setIdApprovalObat(resepEntity.getIdApprovalObat());
                    transaksiObatDetail.setIdObat(resepEntity.getIdObat());
                    transaksiObatDetail.setQty(resepEntity.getQty());
                    transaksiObatDetail.setKeterangan(resepEntity.getKeterangan());
                    transaksiObatDetail.setFlag(resepEntity.getFlag());
                    transaksiObatDetail.setAction(resepEntity.getAction());
                    transaksiObatDetail.setCreatedDate(resepEntity.getCreatedDate());
                    transaksiObatDetail.setCreatedWho(resepEntity.getCreatedWho());
                    transaksiObatDetail.setLastUpdate(resepEntity.getLastUpdate());
                    transaksiObatDetail.setLastUpdateWho(resepEntity.getLastUpdateWho());

                    ImSimrsObatEntity obatEntity = getObatById(resepEntity.getIdObat());
                    if (obatEntity != null) {
                        transaksiObatDetail.setNamaObat(obatEntity.getNamaObat());
                    }

                    listOfResults.add(transaksiObatDetail);
                }
            }
        }

        logger.info("[TransaksiObatBoImpl.getByCriteria] END <<<<<<<");
        return listOfResults;
    }

    @Override
    public void saveEditDetail(TransaksiObatDetail bean) throws GeneralBOException {

        logger.info("[DiagnosaRawatBoImpl.saveEditDetail] Start >>>>>>>>>");

        if (bean != null) {

            ImtSimrsTransaksiObatDetailEntity entity = null;

            try {
                entity = transaksiObatDetailDao.getById("idTransaksiObatDetail", bean.getIdTransaksiObatDetail());
            } catch (HibernateException e) {
                logger.error("[TeamDokterBoImpl.saveEdit] Error when getById diagnosa rawat ", e);
                throw new GeneralBOException("[TeamDokterBoImpl.saveEditDetail] Error when save edit diagnosa rawat " + e.getMessage());
            }
            if (entity != null) {
                entity.setIdObat(bean.getIdObat());
                entity.setQty(bean.getQty());
                entity.setKeterangan(bean.getKeterangan());
                entity.setAction(bean.getAction());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
            }

            try {
                transaksiObatDetailDao.updateAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit diagnosa ", e);
                throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
            }
        }

        logger.info("[DiagnosaRawatBoImpl.saveEditDetail] End <<<<<<<<<");
    }

    @Override
    public List<PermintaanResep> getListResepPasien(PermintaanResep bean) throws GeneralBOException {
        List<PermintaanResep> permintaanResepList = new ArrayList<>();

        if (bean != null) {

            if ("Y".equalsIgnoreCase(bean.getIsTelemedic())){
                // untuk eresep telemedic
                try {
                    permintaanResepList = transaksiObatDetailDao.getListResepPasienEresep(bean);
                } catch (HibernateException e) {
                    logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit diagnosa ", e);
                    throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
                }
            } else {
                try {
                    permintaanResepList = transaksiObatDetailDao.getListResepPasien(bean);
                } catch (HibernateException e) {
                    logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit diagnosa ", e);
                    throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
                }
            }
        }

        return permintaanResepList;
    }

    @Override
    public void saveAntrianResep(PermintaanResep bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.saveAntrianResep] START >>>>>>>>>");

        ImSimrsPermintaanResepEntity entity = new ImSimrsPermintaanResepEntity();

        try {
            entity = permintaanResepDao.getById("idPermintaanResep", bean.getIdPermintaanResep());
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.saveAntrianResep] Error when get by id resep ", e);
            throw new GeneralBOException("Error when get by ud resep " + e.getMessage());
        }

        if (entity != null) {

            if ("Y".equalsIgnoreCase(bean.getIsUmum())) {
                entity.setStatus("0");
            } else {
                entity.setStatus("3");
            }

            entity.setLastUpdateWho(bean.getLastUpdateWho());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setAction(bean.getAction());
            entity.setIsUmum(bean.getIsUmum());
            entity.setTglAntrian(bean.getTglAntrian());

            try {
                permintaanResepDao.updateAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.saveAntrianResep] Error when update resep ", e);
                throw new GeneralBOException("Error when update resep " + e.getMessage());
            }
        }
        logger.info("[TransaksiObatBoImpl.saveAntrianResep] END >>>>>>>>>");

    }

    @Override
    public void updateAntrianResep(PermintaanResep bean) throws GeneralBOException {

        logger.info("[TransaksiObatBoImpl.updateAntrianResep] START >>>>>>>>>");

        ImSimrsPermintaanResepEntity entity = new ImSimrsPermintaanResepEntity();

        try {
            entity = permintaanResepDao.getById("idPermintaanResep", bean.getIdPermintaanResep());
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.updateAntrianResep] Error when get by id resep ", e);
            throw new GeneralBOException("Error when get by ud resep " + e.getMessage());
        }

        if (entity != null) {

            entity.setLastUpdateWho(bean.getLastUpdateWho());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setAction("U");
            entity.setStatus("1");
            entity.setIsRead("Y");

            try {
                permintaanResepDao.updateAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.updateAntrianResep] Error when update resep ", e);
                throw new GeneralBOException("Error when update resep " + e.getMessage());
            }
        }
        logger.info("[TransaksiObatBoImpl.updateAntrianResep] END >>>>>>>>>");
    }

    private List<ImtSimrsTransaksiObatDetailEntity> getListEntityResepDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getListEntityResep] START >>>>>>>");

        List<ImtSimrsTransaksiObatDetailEntity> detailEntityList = new ArrayList<>();

        if (bean != null) {

            Map hsCriteria = new HashMap();

            if (bean.getIdTransaksiObatDetail() != null && !"".equalsIgnoreCase(bean.getIdTransaksiObatDetail())) {
                hsCriteria.put("id_transaksi_obat_detail", bean.getIdTransaksiObatDetail());
            }
            if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {
                hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            }
            if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
                hsCriteria.put("id_obat", bean.getIdObat());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                detailEntityList = transaksiObatDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ", e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ", e);
            }
        }

        logger.info("[TransaksiObatBoImpl.getListEntityResep] END <<<<<<<");
        return detailEntityList;
    }

    private List<MtSimrsTransaksiObatDetailBatchEntity> getListEntityBatchByCriteria(TransaksiObatBatch bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.saveVerifikasiObat] START >>>>>>>>>>");

        Map hsCriteria = new HashMap();

        if (bean.getIdBarang() != null) {
            hsCriteria.put("id_barang", bean.getIdBarang());
        }

        if (bean.getIdTransaksiObatDetail() != null) {
            hsCriteria.put("id_transaksi_obat_detail", bean.getIdTransaksiObatDetail());
        }


        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = new ArrayList<>();
        try {
            batchEntities = batchDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getListEntityBatchByCriteria] ERROR when get by criteria. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityBatchByCriteria] ERROR when get by criteria. ", e);
        }

        logger.info("[TransaksiObatBoImpl.saveVerifikasiObat] END <<<<<<<<<<");
        return batchEntities;
    }

    @Override
    public void saveApproveResepPoli(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.saveApproveResepPoli] START >>>>>>>>>>");

        PermintaanResep permintaanResep = new PermintaanResep();
        permintaanResep.setIdApprovalObat(bean.getIdApprovalObat());

        List<ImSimrsPermintaanResepEntity> resepEntities = getListEntityPermintaanResep(permintaanResep);
        if (resepEntities.size() > 0) {

            ImSimrsPermintaanResepEntity resepEntity = resepEntities.get(0);
            // update to permintaan resep
            if (resepEntity.getIdPermintaanResep() != null) {

                resepEntity.setStatus("3");
                resepEntity.setFlag("N");
                resepEntity.setAction("U");
                resepEntity.setLastUpdate(bean.getLastUpdate());
                resepEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    permintaanResepDao.updateAndSave(resepEntity);
                } catch (HibernateException e) {
                    logger.error("[TransaksiObatBoImpl.saveApproveResepPoli] ERROR when update permintaan resep by criteria. ", e);
                    throw new GeneralBOException("[TransaksiObatBoImpl.saveApproveResepPoli] ERROR when update permintaan resep get by criteria. ", e);
                }

                TransaksiObatDetail obatDetail = new TransaksiObatDetail();
                obatDetail.setIdApprovalObat(bean.getIdApprovalObat());

                ImtSimrsApprovalTransaksiObatEntity approvalEntity = getApprovalTransaksiById(bean.getIdApprovalObat());
                // update to approval permintaan resep
                if (approvalEntity != null && approvalEntity.getIdApprovalObat() != null) {

                    approvalEntity.setApprovalFlag("Y");
                    approvalEntity.setFlag("N");
                    approvalEntity.setAction("U");
                    approvalEntity.setApprovePerson(bean.getLastUpdateWho());
                    approvalEntity.setLastUpdate(bean.getLastUpdate());
                    approvalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    approvalEntity.setNoJurnal(bean.getNoJurnal());

                    try {
                        approvalTransaksiObatDao.updateAndSave(approvalEntity);
                    } catch (HibernateException e) {
                        logger.error("[TransaksiObatBoImpl.saveApproveResepPoli] ERROR when update approve by criteria. ", e);
                        throw new GeneralBOException("[TransaksiObatBoImpl.saveApproveResepPoli] ERROR when update approve get by criteria. ", e);
                    }
                }

                List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = getListEntityTransaksiObat(obatDetail);

                // update to transaksi detail
                if (obatDetailEntities.size() > 0) {
                    for (ImtSimrsTransaksiObatDetailEntity obatDetailEntity : obatDetailEntities) {

                        TransaksiObatBatch batch = new TransaksiObatBatch();
                        batch.setIdTransaksiObatDetail(obatDetailEntity.getIdTransaksiObatDetail());

                        Obat lastHargaObat = obatDao.getLastEveragePricePerBiji(obatDetailEntity.getIdObat(), bean.getBranchId());

                        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchByCriteria(batch);

                        // update to transaksi obat detail batch
                        if (batchEntities.size() > 0) {
                            for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntities) {

                                batchEntity.setFlag("N");
                                batchEntity.setAction("U");
                                batchEntity.setLastUpdate(bean.getLastUpdate());
                                batchEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                batchEntity.setHargaRata(lastHargaObat.getAverageHargaBiji() != null ? lastHargaObat.getAverageHargaBiji() : null);
                                batchEntity.setHargaJual(lastHargaObat.getHargaJual() != null ? lastHargaObat.getHargaJual() : null);

                                if ("Y".equalsIgnoreCase(batchEntity.getStatus())) {

                                    TransaksiObatDetail newObatDetail = new TransaksiObatDetail();

                                    newObatDetail.setIdTransaksiObatDetail(batchEntity.getIdTransaksiObatDetail());
                                    newObatDetail.setIdObat(obatDetailEntity.getIdObat());
                                    newObatDetail.setIdBarang(batchEntity.getIdBarang());
                                    newObatDetail.setQtyApprove(batchEntity.getQtyApprove());
                                    newObatDetail.setJenisSatuan(batchEntity.getJenisSatuan());
                                    newObatDetail.setExpDate(batchEntity.getExpiredDate());
                                    newObatDetail.setIdPermintaanResep(resepEntity.getIdPermintaanResep());
                                    newObatDetail.setIdPelayanan(resepEntity.getTujuanPelayanan());
                                    newObatDetail.setBranchId(CommonUtil.userBranchLogin());
                                    newObatDetail.setCreatedDate(bean.getCreatedDate());
                                    newObatDetail.setCreatedWho(bean.getCreatedWho());

                                    saveTransaksiStok(newObatDetail);

                                    // update substract stock in apotik
                                    updateSubstractStockObatApotek(newObatDetail, bean.getIdPelayanan(), bean.getBranchId());
                                    batchEntity.setApproveFlag("Y");

                                } else {
                                    batchEntity.setApproveFlag("N");
                                }

                                try {
                                    batchDao.updateAndSave(batchEntity);
                                } catch (HibernateException e) {
                                    logger.error("[TransaksiObatBoImpl.saveApproveResepPoli] ERROR when update batch by criteria. ", e);
                                    throw new GeneralBOException("[TransaksiObatBoImpl.saveApproveResepPoli] ERROR when update batch by criteria. ", e);
                                }
                            }
                        }

                        obatDetailEntity.setFlagDiterima("Y");
                        obatDetailEntity.setFlag("N");
                        obatDetailEntity.setAction("U");
                        obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                        obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            transaksiObatDetailDao.updateAndSave(obatDetailEntity);
                        } catch (HibernateException e) {
                            logger.error("[TransaksiObatBoImpl.saveApproveResepPoli] ERROR when update obat detail by criteria. ", e);
                            throw new GeneralBOException("[TransaksiObatBoImpl.saveApproveResepPoli] ERROR when update obat detail by criteria. ", e);
                        }
                    }
                }
            }
        }

        logger.info("[TransaksiObatBoImpl.saveApproveResepPoli] END <<<<<<<<<<");
    }

    private List<TransaksiStok> getListTransaksiObat(String idPelayanan, Integer tahun, Integer bulan, String idObat) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_barang", idObat);
        hsCriteria.put("id_pelayanan", idPelayanan);
        hsCriteria.put("tahun", tahun);
        hsCriteria.put("bulan", bulan);

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
                    trans.setKeterangan(stok.getKeterangan());
                    trans.setTipe(stok.getTipe());

                    TransaksiStok minStok = listOfTransaksi.get(n-1);
                    if ("D".equalsIgnoreCase(stok.getTipe())){
                        trans.setQty(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                        trans.setTotal(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                        trans.setSubTotal(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

//                        trans.setQtyKredit(nolB);
//                        trans.setTotalKredit(nol);
//                        trans.setSubTotalKredit(nol);

                        // qty saldo = qty masuk + qty lalu;
                        trans.setQtySaldo(minStok.getQtyLalu().add(trans.getQty()));

                        // total saldo = sub total lalu + sub total / qty saldo
                        trans.setTotalSaldo(minStok.getSubTotalLalu().add(stok.getSubTotal()).divide(new BigDecimal(trans.getQtySaldo()), 2, BigDecimal.ROUND_HALF_UP));

                        // sub total saldo = total saldo * qty saldo
//                        trans.setSubTotalSaldo(trans.getTotal().multiply(new BigDecimal(trans.getQtySaldo())));
                        trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                    } else {

//                        trans.setQty(nolB);
//                        trans.setTotal(nol);
//                        trans.setSubTotal(nol);

                        trans.setQtyKredit(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                        trans.setTotalKredit(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                        trans.setSubTotalKredit(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

                        // qty saldo = qty bulan lalu - qty masuk
                        trans.setQtySaldo(minStok.getQtyLalu().subtract(trans.getQtyKredit()));

                        // total saldo = total lalu
                        trans.setTotalSaldo(stok.getTotalLalu());

                        // sub total saldo = total saldo * qty saldo
//                        trans.setSubTotalSaldo(trans.getTotal().multiply(new BigDecimal(trans.getQtySaldo())));
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
                    trans.setKeterangan(stok.getKeterangan());
                    trans.setTipe(stok.getTipe());

                    TransaksiStok minStok = listOfTransaksi.get(n-1);

                    if ("D".equalsIgnoreCase(stok.getTipe())){
                        trans.setQty(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                        trans.setTotal(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                        trans.setSubTotal(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

//                        trans.setQtyKredit(nolB);
//                        trans.setTotalKredit(nol);
//                        trans.setSubTotalKredit(nol);

                        // qty saldo = qty saldo lalu + qty
                        trans.setQtySaldo(minStok.getQtySaldo().add(trans.getQty()));

                        // total saldo = sub total saldo lalu + sub total / qty saldo
                        trans.setTotalSaldo(minStok.getSubTotalSaldo().add(trans.getSubTotal()).divide(new BigDecimal(trans.getQtySaldo()), 2, BigDecimal.ROUND_HALF_UP));

                        // sub total saldo = sub total saldo
                        trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                    } else {

//                        trans.setQty(nolB);
//                        trans.setTotal(nol);
//                        trans.setSubTotal(nol);

                        trans.setQtyKredit(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                        trans.setTotalKredit(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                        trans.setSubTotalKredit(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

                        // qty saldo = qty saldo - qty
                        trans.setQtySaldo(minStok.getQtySaldo().subtract(trans.getQty()));

                        // total saldo = total saldo lalu
                        trans.setTotalSaldo(minStok.getTotalSaldo());

                        // sub total saldo = sub total saldo
                        trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                    }
                    listOfTransaksi.add(trans);
                    n++;
                }
            }
        }

        return listOfTransaksi;
    }

    private void saveTransaksiStok(TransaksiObatDetail bean){

//        logger.info("[TransaksiObatBoImpl.saveTransaksiStok] START >>>");
//        // SAVE TO STOCK TRANSAKSI
//
//        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
//        String tahun = CommonUtil.getDateParted(date, "YEAR");
//        String bulan = CommonUtil.getDateParted(date, "MONTH");
//
//        boolean flagTutup = false;
//        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriod = batasTutupPeriodDao.getBatasPeriodeDitutup(bean.getBranchId(), bulan, tahun);
//        if (batasTutupPeriod.size() > 0){
//            // jika sudah ditutup bulan ini
//            flagTutup = true;
//        }
//
//        TransaksiStok saldoBulanLalu = new TransaksiStok();
//        if (flagTutup){
//
//            Integer tahunDepan = new Integer(0);
//            Integer bulanDepan = new Integer(0);
//
//            if ("12".equalsIgnoreCase(bulan)){
//                bulanDepan = Integer.valueOf(1);
//                tahunDepan = Integer.valueOf(tahun) + 1;
//            } else {
//                bulanDepan = Integer.valueOf(bulan) + 1;
//                tahunDepan = Integer.valueOf(tahun);
//            }
//
//            // cari apakah data baru
//            Map hsCriteria = new HashMap();
//            hsCriteria.put("branch_id", bean.getBranchId());
//            hsCriteria.put("id_barang", bean.getIdObat());
//            hsCriteria.put("bulan", bulanDepan);
//            hsCriteria.put("tahun", tahunDepan);
//            hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
//
//            List<ItSimrsTransaksiStokEntity> transaksiStokEntities = transaksiStokDao.getByCriteria(hsCriteria);
//            if (transaksiStokEntities.size() == 0){
//
//
//                // jika sudah ditutup bulan ini
//                // maka hitung saldo bulan ini sebagai saldo bulan lalu
//                if (flagTutup){
//                    List<TransaksiStok> saldoBulanLaluList = getListTransaksiObat(bean.getIdPelayanan(), Integer.valueOf(tahun), Integer.valueOf(bulan), bean.getIdObat());
//                    if (saldoBulanLaluList.size() > 0){
//                        saldoBulanLalu = saldoBulanLaluList.get(saldoBulanLaluList.size() -1);
//                    }
//                }
//            }
//        }
//
//        String pelayananTujuan = "";
//        ImSimrsPelayananEntity pelayananEntity = pelayananDao.getById("idPelayanan", bean.getIdPelayanan());
//        if (pelayananEntity != null){
//            pelayananTujuan = pelayananEntity.getNamaPelayanan();
//        }
//
//        String namaObat = "";
//        String idObat = "";
//        BigInteger consBox = new BigInteger(String.valueOf(0));
//        BigInteger consLembar = new BigInteger(String.valueOf(0));
//        BigDecimal hargaBijian = new BigDecimal(String.valueOf(0));
//        ImSimrsObatEntity obatEntity = obatDao.getById("idBarang", bean.getIdBarang());
//        if (obatEntity != null){
//            namaObat = obatEntity.getNamaObat();
//            consLembar = obatEntity.getBijiPerLembar();
//            consBox = obatEntity.getLembarPerBox().multiply(consLembar);
//            idObat = obatEntity.getIdObat();
//            hargaBijian = obatEntity.getAverageHargaBiji();
//        }
//
//        //BigDecimal hargaObat = new BigDecimal(0);
//        BigInteger qty = new BigInteger(String.valueOf(0));
//
//        if ("box".equalsIgnoreCase(bean.getJenisSatuan())){
//            qty = bean.getQtyApprove().multiply(consBox);
//        } else if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())){
//            qty = bean.getQtyApprove().multiply(consLembar);
//        } else {
//            qty = bean.getQtyApprove();
//        }
//
//
//        java.util.Date now = new java.util.Date();
//        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//        String seq = transaksiStokDao.getNextSeq();
//        String idBarangMasuk = "RB"+ bean.getBranchId() + f.format(now) + seq;
//
//        ItSimrsTransaksiStokEntity transaksiStokEntity = new ItSimrsTransaksiStokEntity();
//        transaksiStokEntity.setIdTransaksi(idBarangMasuk);
//        transaksiStokEntity.setIdObat(idObat);
//        transaksiStokEntity.setKeterangan("Pengeluaran Obat Apotek " + pelayananTujuan);
//        transaksiStokEntity.setTipe("K");
//        transaksiStokEntity.setBranchId(bean.getBranchId());
//        transaksiStokEntity.setQty(qty);
//        transaksiStokEntity.setTotal(hargaBijian);
//        transaksiStokEntity.setSubTotal(hargaBijian.multiply(new BigDecimal(qty)));
//        transaksiStokEntity.setCreatedDate(bean.getCreatedDate());
//        transaksiStokEntity.setCreatedWho(bean.getCreatedWho());
//        transaksiStokEntity.setLastUpdate(bean.getCreatedDate());
//        transaksiStokEntity.setLastUpdateWho(bean.getCreatedWho());
//        transaksiStokEntity.setIdBarang(bean.getIdBarang());
//        transaksiStokEntity.setIdPelayanan(bean.getIdPelayanan());
//        if (flagTutup){
//            // jika ditutup maka buat registered date bulan depan
//            Integer tahunDepan = new Integer(0);
//            Integer bulanDepan = new Integer(0);
//
//            if ("12".equalsIgnoreCase(bulan)){
//                tahunDepan = Integer.valueOf(tahun) + 1;
//                bulanDepan = 1;
//            } else {
//                tahunDepan = Integer.valueOf(tahun);
//                bulanDepan = Integer.valueOf(bulan) + 1;
//            }
//
//            String stDate = tahunDepan+"-"+bulanDepan+"-"+"1";
//            transaksiStokEntity.setRegisteredDate(java.sql.Date.valueOf(stDate));
//        } else {
//            transaksiStokEntity.setRegisteredDate(new java.sql.Date(obatEntity.getLastUpdate().getTime()));
//        }
//
//        // jika ada saldo lalu
//        if (saldoBulanLalu.getQtySaldo() != null && saldoBulanLalu.getQtySaldo().compareTo(new BigInteger(String.valueOf(0))) == 1){
//            transaksiStokEntity.setQtyLalu(saldoBulanLalu.getQtySaldo());
//            transaksiStokEntity.setTotalLalu(saldoBulanLalu.getTotalSaldo());
//            transaksiStokEntity.setSubTotalLalu(saldoBulanLalu.getSubTotalSaldo());
//        } else {
//            transaksiStokEntity.setQtyLalu(new BigInteger(String.valueOf(0)));
//            transaksiStokEntity.setTotalLalu(new BigDecimal(0));
//            transaksiStokEntity.setSubTotalLalu(new BigDecimal(0));
//        }
//
//
//        try {
//            transaksiStokDao.addAndSave(transaksiStokEntity);
//        } catch (HibernateException e){
//            logger.error("[TransaksiObatBoImpl.saveTransaksiStok] ERROR.", e);
//            throw new GeneralBOException("[TransaksiObatBoImpl.saveTransaksiStok] ERROR." + e.getMessage());
//        }
//
//        logger.info("[TransaksiObatBoImpl.saveTransaksiStok] END <<<<");
    }



    private List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransaksiObat(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListEntityTransaksiObat] START >>>>>>>>>>");

        Map hsCriteria = new HashMap();
        if (bean.getIdTransaksiObatDetail() != null) {
            hsCriteria.put("id_transaksi_obat_detail", bean.getIdTransaksiObatDetail());
        }
        if (bean.getIdApprovalObat() != null) {
            hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
        }
        if (bean.getFlag() != null) {
            hsCriteria.put("flag", bean.getFlag());
        }

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();

        try {
            obatDetailEntities = transaksiObatDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getListEntityTransaksiObat] ERROR when get by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityTransaksiObat] ERROR when get by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.getListEntityTransaksiObat] END <<<<<<<<<<");
        return obatDetailEntities;
    }

    private List<ImSimrsPermintaanResepEntity> getListEntityPermintaanResep(PermintaanResep bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListEntityPermintaanResep] START >>>>>>>>>>");

        Map hsCriteria = new HashMap();
        if (bean.getIdPermintaanResep() != null) {
            hsCriteria.put("id_permintaan_resep", bean.getIdPermintaanResep());
        }
        if (bean.getIdApprovalObat() != null) {
            hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
        }
        if (bean.getBranchId() != null) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getFlag() != null) {
            hsCriteria.put("flag", bean.getFlag());
        }

        List<ImSimrsPermintaanResepEntity> resepEntities = new ArrayList<>();

        try {
            resepEntities = permintaanResepDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getListEntityPermintaanResep] ERROR when get by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityPermintaanResep] ERROR when get by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.getListEntityPermintaanResep] END <<<<<<<<<<");
        return resepEntities;
    }

    @Override
    public List<MtSimrsHargaObatEntity> getListEntityHargaObat(HargaObat bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListEntityHargaObat] START >>>>>>>>>>");

        Map hsCriteria = new HashMap();
        if (bean.getIdObat() != null) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getFlag() != null) {
            hsCriteria.put("flag", bean.getFlag());
        }

        List<MtSimrsHargaObatEntity> hargaObatEntities = new ArrayList<>();

        try {
            hargaObatEntities = hargaObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getListEntityHargaObat] ERROR when get by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityHargaObat] ERROR when get by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.getListEntityHargaObat] END <<<<<<<<<<");
        return hargaObatEntities;
    }

    @Override
    public CheckObatResponse saveListObatPembelian(TransaksiObatDetail bean, List<TransaksiObatDetail> listBatch) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.saveListObatPembelian] START >>>>>>>");

        CheckObatResponse response = new CheckObatResponse();
        ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();

        if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {

            approvalEntity = approvalTransaksiObatDao.getById("idApprovalObat", bean.getIdApprovalObat());

            approvalEntity.setAction("U");
            approvalEntity.setLastUpdate(bean.getLastUpdate());
            approvalEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                approvalTransaksiObatDao.addAndSave(approvalEntity);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.saveListObatPembelian] ERROR when update into approval transaksi. ", e);
                throw new GeneralBOException("[TransaksiObatBoImpl.saveListObatPembelian] ERROR when update into approval transaksi. ", e);
            }

        } else {

            String id = getNextApprovalObatId();
            approvalEntity.setIdApprovalObat("INV" + id);
            approvalEntity.setIdPelayanan(bean.getIdPelayanan());
            approvalEntity.setFlag("Y");
            approvalEntity.setAction("C");
            approvalEntity.setTipePermintaan("001");
            approvalEntity.setLastUpdate(bean.getCreatedDate());
            approvalEntity.setLastUpdateWho(bean.getCreatedWho());
            approvalEntity.setCreatedDate(bean.getCreatedDate());
            approvalEntity.setCreatedWho(bean.getCreatedWho());
            approvalEntity.setBranchId(bean.getBranchId());

            try {
                approvalTransaksiObatDao.addAndSave(approvalEntity);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.saveListObatPembelian] ERROR when insert into approval transaksi. ", e);
                throw new GeneralBOException("[TransaksiObatBoImpl.saveListObatPembelian] ERROR when insert into approval transaksi. ", e);
            }
        }

        ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();

        String id = getNextTransaksiObatDetail();
        obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
        obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
        obatDetailEntity.setIdObat(listBatch.get(0).getIdObat());

        BigInteger total = new BigInteger(String.valueOf("0"));
        if (listBatch != null && listBatch.size() > 0) {
            for (TransaksiObatDetail trans : listBatch) {
                total = total.add(trans.getQtyApprove());
            }
        }

        obatDetailEntity.setQty(total);
        obatDetailEntity.setJenisSatuan(listBatch.get(0).getJenisSatuan());
        obatDetailEntity.setFlag("Y");
        obatDetailEntity.setAction("C");
        obatDetailEntity.setCreatedDate(bean.getCreatedDate());
        obatDetailEntity.setCreatedWho(bean.getCreatedWho());
        obatDetailEntity.setLastUpdate(bean.getCreatedDate());
        obatDetailEntity.setLastUpdateWho(bean.getCreatedWho());
        obatDetailEntity.setKeterangan("Pembelian Obat");

        try {
            transaksiObatDetailDao.addAndSave(obatDetailEntity);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.saveListObatPembelian]  ERROR when insert into transaksi obat detail. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.saveListObatPembelian]  ERROR when insert into transaksi obat detail. ", e);
        }

        if (listBatch.size() > 0) {

            for (TransaksiObatDetail obatDetail : listBatch) {
                MtSimrsTransaksiObatDetailBatchEntity batchEntity = new MtSimrsTransaksiObatDetailBatchEntity();

                String seqBatch = batchDao.getNextId();
                batchEntity.setId(new BigInteger(seqBatch));
                batchEntity.setIdBarang(obatDetail.getIdBarang());
                batchEntity.setIdTransaksiObatDetail(obatDetailEntity.getIdTransaksiObatDetail());
                batchEntity.setQtyApprove(obatDetail.getQtyApprove());
                batchEntity.setJenisSatuan(obatDetail.getJenisSatuan());
                batchEntity.setExpiredDate(obatDetail.getExpDate());
                batchEntity.setNoBatch(1);
                batchEntity.setStatus("Y");
                batchEntity.setFlag("Y");
                batchEntity.setAction("C");
                batchEntity.setCreatedDate(bean.getCreatedDate());
                batchEntity.setCreatedWho(bean.getCreatedWho());
                batchEntity.setLastUpdate(bean.getCreatedDate());
                batchEntity.setLastUpdateWho(bean.getCreatedWho());

                TransaksiObatDetail dataTransaksiStok = new TransaksiObatDetail();
                dataTransaksiStok.setBranchId(bean.getBranchId());
                dataTransaksiStok.setIdPelayanan(bean.getIdPelayanan());
                dataTransaksiStok.setQtyApprove(batchEntity.getQtyApprove());
                dataTransaksiStok.setJenisSatuan(batchEntity.getJenisSatuan());
                dataTransaksiStok.setCreatedDate(bean.getCreatedDate());
                dataTransaksiStok.setCreatedWho(bean.getCreatedWho());
                dataTransaksiStok.setIdObat(obatDetail.getIdObat());

                // save to transaksi stok
                saveTransaksiStok(dataTransaksiStok);


                try {
                    batchDao.addAndSave(batchEntity);
                } catch (HibernateException e) {
                    logger.error("[TransaksiObatBoImpl.saveListObatPembelian]  ERROR when insert into transaksi obat detail batch. ", e);
                    throw new GeneralBOException("[TransaksiObatBoImpl.saveListObatPembelian]  ERROR when insert into transaksi obat detail batch. ", e);
                }

                updateSubstractStockObatPoli(obatDetail, bean.getIdPelayanan(), bean.getBranchId());
            }
        }

        logger.info("[TransaksiObatBoImpl.saveListObatPembelian] END <<<<<<<");
        response.setMessage("success");
        response.setMessage(approvalEntity.getIdApprovalObat());
        return response;
    }


    @Override
    public List<TransaksiObatDetail> getListPembelianObat(String idApprove) throws GeneralBOException {

        logger.info("[TransaksiObatBoImpl.getListPembelianObat] START >>>>>>>>>>");
        List<TransaksiObatDetail> result = new ArrayList<>();

        List<TransaksiObatDetail> detailList = new ArrayList<>();
        try {
            detailList = transaksiObatDetailDao.getListPembelianObat(idApprove);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getListPembelianObat] ERROR when search list pembelian obat. ", e);
            throw new GeneralBOException("[ObatPoliBoImpl.getListPembelianObat] ERROR when search list pembelian obat. ", e);
        }

        if (detailList.size() > 0) {

            TransaksiObatDetail detail;
            for (TransaksiObatDetail transaksiObatDetail : detailList) {

                detail = new TransaksiObatDetail();
                detail.setIdApprovalObat(transaksiObatDetail.getIdApprovalObat());
                detail.setIdTransaksiObatDetail(transaksiObatDetail.getIdTransaksiObatDetail());
                detail.setIdBarang(transaksiObatDetail.getIdBarang());
                detail.setIdObat(transaksiObatDetail.getIdObat());
                detail.setQtyApprove(transaksiObatDetail.getQtyApprove());
                detail.setJenisSatuan(transaksiObatDetail.getJenisSatuan());

                Obat obat = new Obat();
                obat.setIdBarang(transaksiObatDetail.getIdBarang());
                obat.setIdObat(transaksiObatDetail.getIdObat());
                obat.setBranchId(CommonUtil.userBranchLogin());

                List<ImSimrsObatEntity> obatEntityList = getListEntityObat(obat);

                ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
                if (obatEntityList.size() > 0) {
                    obatEntity = obatEntityList.get(0);
                    if (obatEntity != null) {
                        detail.setNamaObat(obatEntity.getNamaObat());

                        HargaObat hargaObat = new HargaObat();
                        hargaObat.setIdObat(obatEntity.getIdObat());
                        hargaObat.setFlag("Y");
                        List<MtSimrsHargaObatEntity> hargaObatEntities = getListEntityHargaObat(hargaObat);

                        if (hargaObatEntities.size() > 0) {
                            MtSimrsHargaObatEntity hargaObatEntity = hargaObatEntities.get(0);
                            BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());
                            if ("box".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {
                                detail.setHarga(hargaObatEntity.getHargaJual().multiply(new BigDecimal(cons)).toBigInteger());
                                detail.setTotalHarga((hargaObatEntity.getHargaJual().multiply(new BigDecimal(cons)).toBigInteger()).multiply(new BigInteger(String.valueOf(transaksiObatDetail.getQtyApprove()))));
                            }
                            if ("lembar".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {
                                BigInteger bHarga = hargaObatEntity.getHargaJual().toBigInteger();
                                BigInteger nHarga = obatEntity.getBijiPerLembar().multiply(bHarga);
                                detail.setHarga(nHarga);
                                detail.setTotalHarga(nHarga.multiply(new BigInteger(String.valueOf(transaksiObatDetail.getQtyApprove()))));
                            }
                            if ("biji".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {
                                detail.setHarga(hargaObatEntity.getHargaJual().toBigInteger());
                                detail.setTotalHarga((hargaObatEntity.getHargaJual().toBigInteger()).multiply(new BigInteger(String.valueOf(transaksiObatDetail.getQtyApprove()))));
                            }
                        }
                    }
                }

                result.add(detail);
            }
        }
        logger.info("[TransaksiObatBoImpl.getListPembelianObat] END >>>>>>>>>>");
        return result;
    }

    @Override
    public void pembayaranObatBaru(TransaksiObatDetail bean) throws GeneralBOException {

        if (bean != null) {
            ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();

            try {
                approvalEntity = approvalTransaksiObatDao.getById("idApprovalObat", bean.getIdApprovalObat());
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when get approve. ", e);
                throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when sget approve. " + e.getMessage());
            }

            if (approvalEntity != null) {

                approvalEntity.setApprovePerson(bean.getLastUpdateWho());
                approvalEntity.setApprovalFlag("Y");
                approvalEntity.setFlag("N");
                approvalEntity.setAction("U");
                approvalEntity.setLastUpdate(bean.getLastUpdate());
                approvalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                approvalEntity.setNoJurnal(bean.getNoJurnal());

                try {
                    approvalTransaksiObatDao.updateAndSave(approvalEntity);
                } catch (HibernateException e) {
                    logger.error("[TransaksiObatBoImpl.pembayaranObatBaru] ERROR when update approve. ", e);
                    throw new GeneralBOException("[TransaksiObatBoImpl.pembayaranObatBaru] ERROR when supdate approve. " + e.getMessage());
                }
            }

            TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
            transaksiObatDetail.setIdApprovalObat(bean.getIdApprovalObat());
            List<ImtSimrsTransaksiObatDetailEntity> detailEntityList = getListEntityTransaksiObat(transaksiObatDetail);

            for (ImtSimrsTransaksiObatDetailEntity entity : detailEntityList) {

                entity.setFlag("N");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    transaksiObatDetailDao.updateAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[TransaksiObatBoImpl.pembayaranObatBaru] ERROR when update transaksi detail obat. ", e);
                    throw new GeneralBOException("[TransaksiObatBoImpl.pembayaranObatBaru] ERROR when supdate transaksi detail obat. " + e.getMessage());
                }

                TransaksiObatBatch batch = new TransaksiObatBatch();
                batch.setIdTransaksiObatDetail(entity.getIdTransaksiObatDetail());
                List<MtSimrsTransaksiObatDetailBatchEntity> batchEntityList = getListEntityBatchByCriteria(batch);

                for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntityList) {

                    batchEntity.setFlag("N");
                    batchEntity.setApproveFlag("Y");
                    batchEntity.setLastUpdate(bean.getLastUpdate());
                    batchEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        batchDao.updateAndSave(batchEntity);
                    } catch (HibernateException e) {
                        logger.error("[TransaksiObatBoImpl.pembayaranObatBaru] ERROR when update batch transaksi detail obat. ", e);
                        throw new GeneralBOException("[TransaksiObatBoImpl.pembayaranObatBaru] ERROR when supdate batch transaksi detail obat. " + e.getMessage());
                    }

                }
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date now = new Date();
            String stDate = dateFormat.format(now);
            String id = getNextTransPembelian();
            String noPemebelian = bean.getBranchId() + stDate + id;
            Integer compare = bean.getTotalBayar().compareTo(bean.getNominal());

            if (bean.getNominal() == null)
                bean.setNominal(new BigInteger(String.valueOf(0)));
            if (bean.getKembalian() == null)
                bean.setKembalian(new BigInteger(String.valueOf(0)));
            if (compare == -1)
                bean.setKembalian(bean.getNominal().subtract(bean.getTotalBayar()));

            MtSimrsRiwayatPembelianObat pembelianObat = new MtSimrsRiwayatPembelianObat();
            pembelianObat.setNoPembelianObat(noPemebelian);
            pembelianObat.setTotalBayar(bean.getTotalBayar());
            pembelianObat.setNominal(bean.getNominal());
            pembelianObat.setTotalDibayar(bean.getNominal().subtract(bean.getKembalian()));
            pembelianObat.setKembalian(bean.getKembalian());
            pembelianObat.setFlag("Y");
            pembelianObat.setAction("C");
            pembelianObat.setCreatedDate(bean.getLastUpdate());
            pembelianObat.setCreatedWho(bean.getLastUpdateWho());

            try {
                riwayatTransPembelianObatDao.addAndSave(pembelianObat);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. ", e);
                throw new GeneralBOException("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. " + e.getMessage());
            }

            ImtSimrsRiwayatTransaksiObatEntity riwayatTransaksiObatEntity = new ImtSimrsRiwayatTransaksiObatEntity();
            id = getNextRiwayatTransaksiId();
            riwayatTransaksiObatEntity.setIdRiwayatTransaksiObat("RTO" + id);
            riwayatTransaksiObatEntity.setNoTransaksiPembelian(pembelianObat.getNoPembelianObat());
            riwayatTransaksiObatEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
            riwayatTransaksiObatEntity.setTipePermintaan(approvalEntity.getTipePermintaan());
            riwayatTransaksiObatEntity.setFlag("Y");
            riwayatTransaksiObatEntity.setAction("C");
            riwayatTransaksiObatEntity.setCreatedDate(bean.getLastUpdate());
            riwayatTransaksiObatEntity.setCreatedWho(bean.getLastUpdateWho());
            riwayatTransaksiObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
            riwayatTransaksiObatEntity.setLastUpdate(bean.getLastUpdate());

            try {
                riwayatTransaksiObatDao.addAndSave(riwayatTransaksiObatEntity);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. ", e);
                throw new GeneralBOException("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. " + e.getMessage());
            }
        }
    }

    @Override
    public List<TransaksiObatDetail> getListRiwayatPembelianObat(String idApprove) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getListRiwayatPembelianObat] START >>>>>>>>>>");
        List<TransaksiObatDetail> result = new ArrayList<>();

        List<TransaksiObatDetail> detailList = new ArrayList<>();
        try {
            detailList = transaksiObatDetailDao.getListRiwayatPembelianObat(idApprove);
        } catch (HibernateException e) {
            logger.error("[TransaksiObatBoImpl.getListRiwayatPembelianObat] ERROR when search list pembelian obat. ", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getListRiwayatPembelianObat] ERROR when search list pembelian obat. ", e);
        }

        if (detailList.size() > 0) {

            TransaksiObatDetail detail;
            for (TransaksiObatDetail transaksiObatDetail : detailList) {

                detail = new TransaksiObatDetail();
                detail.setIdApprovalObat(transaksiObatDetail.getIdApprovalObat());
                detail.setIdTransaksiObatDetail(transaksiObatDetail.getIdTransaksiObatDetail());
                detail.setIdBarang(transaksiObatDetail.getIdBarang());
                detail.setIdObat(transaksiObatDetail.getIdObat());
                detail.setQtyApprove(transaksiObatDetail.getQtyApprove());
                detail.setJenisSatuan(transaksiObatDetail.getJenisSatuan());

                Obat obat = new Obat();
                obat.setIdBarang(transaksiObatDetail.getIdBarang());
                obat.setIdObat(transaksiObatDetail.getIdObat());
                obat.setBranchId(CommonUtil.userBranchLogin());

                List<ImSimrsObatEntity> obatEntityList = getListEntityObat(obat);

                ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
                if (obatEntityList.size() > 0) {
                    obatEntity = obatEntityList.get(0);
                    if (obatEntity != null) {
                        detail.setNamaObat(obatEntity.getNamaObat());

                        HargaObat hargaObat = new HargaObat();
                        hargaObat.setIdObat(obatEntity.getIdObat());
                        hargaObat.setFlag("Y");
                        List<MtSimrsHargaObatEntity> hargaObatEntities = getListEntityHargaObat(hargaObat);

                        if (hargaObatEntities.size() > 0) {
                            MtSimrsHargaObatEntity hargaObatEntity = hargaObatEntities.get(0);
                            BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());
                            if ("box".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {
                                detail.setHarga(hargaObatEntity.getHargaJual().multiply(new BigDecimal(cons)).toBigInteger());
                                detail.setTotalHarga((hargaObatEntity.getHargaJual().multiply(new BigDecimal(cons)).toBigInteger()).multiply(new BigInteger(String.valueOf(transaksiObatDetail.getQtyApprove()))));
                            }
                            if ("lembar".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {
                                BigInteger bHarga = hargaObatEntity.getHargaJual().toBigInteger();
                                BigInteger nHarga = obatEntity.getBijiPerLembar().multiply(bHarga);
                                detail.setHarga(nHarga);
                                detail.setTotalHarga(nHarga.multiply(new BigInteger(String.valueOf(transaksiObatDetail.getQtyApprove()))));
                            }
                            if ("biji".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {
                                detail.setHarga(hargaObatEntity.getHargaJual().toBigInteger());
                                detail.setTotalHarga((hargaObatEntity.getHargaJual().toBigInteger()).multiply(new BigInteger(String.valueOf(transaksiObatDetail.getQtyApprove()))));
                            }
                        }
                    }
                }

                result.add(detail);
            }
        }
        logger.info("[TransaksiObatBoImpl.getListRiwayatPembelianObat] END >>>>>>>>>>");
        return result;
    }

    @Override
    public List<MtSimrsRiwayatPembelianObat> getRiwayatPembelianObat(String idApprove) throws GeneralBOException {

        logger.info("[TransaksiObatBoImpl.getRiwayatPembelianObat] START >>>>>>>>>>");
        List<MtSimrsRiwayatPembelianObat> result = new ArrayList<>();

        if (idApprove != null) {
            try {
                result = riwayatTransPembelianObatDao.getRiwayatPembelianObat(idApprove);
            } catch (HibernateException e) {
                logger.error("[TransaksiObatBoImpl.getListRiwayatPembelianObat] ERROR when search list pembelian obat. ", e);
                throw new GeneralBOException("[TransaksiObatBoImpl.getListRiwayatPembelianObat] ERROR when search list pembelian obat. ", e);
            }
        }
        logger.info("[TransaksiBoImpl.getRiwayatPembelianObat] END >>>>>>>>>>");
        return result;
    }

    private void updateSubstractStockObatPoli(TransaksiObatDetail bean, String idPoli, String branchId) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.updateAddStockObatPoli] START >>>>>>>>>>");

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdPelayanan(idPoli);
        obatPoli.setIdBarang(bean.getIdBarang());
        obatPoli.setIdObat(bean.getIdObat());
        obatPoli.setBranchId(branchId);
        obatPoli.setFlag("Y");

        List<MtSimrsObatPoliEntity> obatPoliEntities = getListEntityObatPoli(obatPoli);

        MtSimrsObatPoliEntity obatPoliEntity = new MtSimrsObatPoliEntity();
        if (obatPoliEntities.size() > 0) {
            obatPoliEntity = obatPoliEntities.get(0);
        }

        Obat obat = new Obat();
        obat.setIdBarang(bean.getIdBarang());
        obat.setBranchId(bean.getBranchId());

        List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);
        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();

        if (obatEntities.size() > 0) {
            obatEntity = obatEntities.get(0);
        }


        if (obatPoliEntity.getPrimaryKey() != null && obatEntity.getIdBarang() != null) {

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
                        obatPoliEntity.setQtyLembar(obatPoliEntity.getQtyLembar().subtract(new BigInteger(String.valueOf(1))));
                        obatPoliEntity.setQtyBiji((qtyBiji.add(obatEntity.getBijiPerLembar())).subtract(bean.getQtyApprove()));
                    } else {
                        BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());
                        BigInteger jmlAllStockBiji = (qtyBox.multiply(cons))
                                .add(obatPoliEntity.getQtyLembar().multiply(obatEntity.getBijiPerLembar()))
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

    @Override
    public void saveEditFlagPengambilan(String idTrans) throws GeneralBOException {

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_transaksi_obat_detail", idTrans);

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();
        try {
            obatDetailEntities = transaksiObatDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[ObatPoliBoImpl.saveEditFlagPengambilan] ERROR. ", e);
            throw new GeneralBOException("[ObatPoliBoImpl.saveEditFlagPengambilan] ERROR. ", e);
        }

        if (obatDetailEntities.size() > 0) {
            ImtSimrsTransaksiObatDetailEntity transaksiObatDetailEntity = obatDetailEntities.get(0);
            transaksiObatDetailEntity.setFlagKronisDiambil("Y");
            transaksiObatDetailEntity.setAction("U");
            transaksiObatDetailEntity.setCreatedDate(time);
            transaksiObatDetailEntity.setCreatedWho(userLogin);
            transaksiObatDetailEntity.setLastUpdate(time);
            transaksiObatDetailEntity.setLastUpdateWho(userLogin);

            try {
                transaksiObatDetailDao.updateAndSave(transaksiObatDetailEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveEditFlagPengambilan] ERROR. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveEditFlagPengambilan] ERROR. ", e);
            }
        }
    }

    @Override
    public List<TransaksiObatDetail> getListPermintaanBatch(String idApproval, String flagDiterima) throws GeneralBOException {
        return transaksiObatDetailDao.getListOfObatBatchPermintaan(idApproval, flagDiterima);
    }

    @Override
    public Boolean cekObatKronis(String idApproval) throws GeneralBOException {
        Boolean response = false;
        if (idApproval != null && !"".equalsIgnoreCase(idApproval)) {
            try {
                response = transaksiObatDetailDao.cekObatKronis(idApproval);
            } catch (HibernateException e) {
                logger.error("Found Error when cek obat kronis");
            }
        }
        return response;
    }

    @Override
    public TransaksiObatDetail getTarifApproveResep(String idApproval) throws GeneralBOException {

        TransaksiObatDetail response = new TransaksiObatDetail();
        if (idApproval != null && !"".equalsIgnoreCase(idApproval)) {
            try {
                response = transaksiObatDetailDao.getTarifResepApprove(idApproval);
            } catch (HibernateException e) {
                logger.error("Found Error when cek obat kronis");
            }
        }

        return response;
    }

    @Override
    public TransaksiObatDetail getTotalHargaResep(String idPermintaan) throws GeneralBOException {
        TransaksiObatDetail response = new TransaksiObatDetail();
        if (idPermintaan != null && !"".equalsIgnoreCase(idPermintaan)) {
            try {
                response = transaksiObatDetailDao.getTotalHargaResepApprove(idPermintaan);
            } catch (HibernateException e) {
                logger.error("Found Error when cek obat kronis");
            }
        }

        return response;
    }

    @Override
    public CheckResponse setTtdPasien(String idPermintaan, String ttdPasien, String ttdApoteker) throws GeneralBOException {
        CheckResponse response = new CheckResponse();

        if (idPermintaan != null && !"".equalsIgnoreCase(idPermintaan)) {
            try {

                ImSimrsPermintaanResepEntity entity = new ImSimrsPermintaanResepEntity();

                try {
                    entity = permintaanResepDao.getById("idPermintaanResep", idPermintaan);
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Found Error" + e.getMessage());
                }

                if (entity != null) {

                    entity.setTtdPasien(ttdPasien);
                    entity.setTtdApoteker(ttdApoteker);
                    entity.setIdApoteker(CommonUtil.userIdLogin());
                    entity.setAction("U");

                    try {
                        permintaanResepDao.updateAndSave(entity);
                        response.setStatus("success");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error" + e.getMessage());
                    }
                }

            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error" + e.getMessage());
            }
        }
        return response;
    }

    @Override
    public List<TransaksiObatDetail> listObatResepApprove(String idApprove) throws GeneralBOException {
        List<TransaksiObatDetail> list = new ArrayList<>();

        if (idApprove != null && !"".equalsIgnoreCase(idApprove)) {

            try {
                list = transaksiObatDetailDao.getListObatResepApprove(idApprove);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }

        return list;
    }

    @Override
    public List<PermintaanResep> getListNotifResep(String idPelayanan, String branchId) throws GeneralBOException {
        List<PermintaanResep> list = new ArrayList<>();
        try {
            list = transaksiObatDetailDao.getListNotifResep(idPelayanan, branchId);
        } catch (HibernateException e) {
            logger.error("Found Error" + e.getMessage());
        }
        return list;
    }

    @Override
    public void saveUpdateRetureObat(List<TransaksiObatDetail> listReture, TransaksiObatDetail bean) throws GeneralBOException {

        if (listReture != null && listReture.size() > 0){
            for (TransaksiObatDetail reture : listReture){

                MtSimrsTransaksiObatDetailBatchEntity batchEntity = batchEntityByIdTransaksiIdBarang(reture.getIdTransaksiObatDetail(), reture.getIdBarang());
                if (batchEntity != null){

                    batchEntity.setQtyReture(reture.getQtyApprove());
                    batchEntity.setLastUpdate(bean.getLastUpdate());
                    batchEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    batchEntity.setAction("U");

                    try {
                        batchDao.updateAndSave(batchEntity);
                    } catch (HibernateException e){
                        logger.error("[PermintaanVendorBoImpl.saveUpdateRetureObat] ERROR.", e);
                        throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateRetureObat] ERROR." + e.getMessage());
                    }
                }
            }
        }
    }

    private MtSimrsTransaksiObatDetailBatchEntity batchEntityByIdTransaksiIdBarang(String idTransaksi, String idBarang) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_transaksi_obat_detail", idTransaksi);
        hsCriteria.put("id_barang", idBarang);
        hsCriteria.put("approve_flag", "Y");

        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = new ArrayList<>();
        try {
            batchEntities = batchDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.batchEntityByIdTransaksiIdBarang] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.batchEntityByIdTransaksiIdBarang] ERROR." + e.getMessage());
        }

        if (batchEntities.size() > 0){
            return batchEntities.get(0);
        }

        return null;
    }

    @Override
    public void saveUpdateHargaRataBarangMasukKarnaReture(TransaksiObatDetail bean) throws GeneralBOException {

        Obat sumObat = new Obat();
        try {
            sumObat = obatDao.getSumStockObatGudangById(bean.getIdObat(), "", bean.getBranchId());
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.saveUpdateHargaRataBarangMasukKarnaReture] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateHargaRataBarangMasukKarnaReture] ERROR." + e.getMessage());
        }

        ImSimrsObatEntity obatEntity = getObatById(bean.getIdObat());
        BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());

        BigInteger allStockToBiji = new BigInteger(String.valueOf(0));
        if (sumObat.getIdObat() != null) {
            allStockToBiji = (sumObat.getQtyBox().multiply(cons))
                    .add(sumObat.getQtyLembar().multiply(obatEntity.getBijiPerLembar()))
                    .add(sumObat.getQtyBiji());
        }

        BigInteger ttlQtyPermintaan = new BigInteger(String.valueOf(0));
        BigDecimal ttlAvgHargaPermintaan = new BigDecimal(0);

        ImSimrsObatEntity newObatEntity = new ImSimrsObatEntity();
        newObatEntity.setIdBarang(bean.getIdBarang());
        newObatEntity.setIdObat(bean.getIdObat());

        if ("biji".equalsIgnoreCase(bean.getJenisSatuan())) {
            ttlQtyPermintaan = bean.getQtyApprove();
            ttlAvgHargaPermintaan = bean.getAverageHargaBiji();
        }

        BigDecimal ttlStockInBijian = new BigDecimal(0);
        if (obatEntity.getAverageHargaBiji().compareTo(new BigDecimal(0)) == 1 && allStockToBiji.compareTo(new BigInteger(String.valueOf(0))) == 0) {
            ttlStockInBijian = obatEntity.getAverageHargaBiji();
        } else {
            ttlStockInBijian = obatEntity.getAverageHargaBiji().multiply(new BigDecimal(allStockToBiji));
        }

        // hitung average
        BigDecimal ttlHargaBijian = ttlStockInBijian.add(ttlAvgHargaPermintaan);
        BigInteger ttlQty = allStockToBiji.add(ttlQtyPermintaan);
        BigDecimal newAvgHargaBijian = ttlHargaBijian.divide(new BigDecimal(ttlQty), 2, RoundingMode.HALF_UP);

        if (obatEntity.getLembarPerBox().compareTo(new BigInteger(String.valueOf(0))) == 1) {
            newObatEntity.setAverageHargaBox(newAvgHargaBijian.multiply(new BigDecimal(cons)));
            newObatEntity.setAverageHargaLembar(newAvgHargaBijian.multiply(new BigDecimal(obatEntity.getBijiPerLembar())));
        }
        if (obatEntity.getBijiPerLembar().compareTo(new BigInteger(String.valueOf(0))) == 1) {
            newObatEntity.setAverageHargaBiji(newAvgHargaBijian);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        try {
            obatPoliBo.updateAddStockPoli(bean, bean.getIdPelayananTujuan());
        } catch (GeneralBOException e){
            logger.error("[PermintaanVendorBoImpl.saveUpdateHargaRataBarangMasukKarnaReture] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateHargaRataBarangMasukKarnaReture] ERROR." + e.getMessage());
        }

        permintaanVendorBo.updateAllNewAverageHargaByObatId(bean.getIdObat(), newObatEntity.getAverageHargaBox(), newObatEntity.getAverageHargaLembar(), newObatEntity.getAverageHargaBiji(), bean.getBranchId());
        saveTransaksiStokObatMasukKarnaReture(bean);
    }

    @Override
    public List<TransaksiObatDetail> getListTransaksiObatDetailBatchByIdResep(String idResep) throws GeneralBOException {

        List<TransaksiObatDetail> listObatBatch = transaksiObatDetailDao.getListTransaksiObatDetailBatchByIdResep(idResep);

        if (listObatBatch.size() > 0){
            for (TransaksiObatDetail obatBatch : listObatBatch){



                ImSimrsObatEntity obatEntity = getObatById(obatBatch.getIdObat());
                if (obatEntity != null) {

                    BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());

                    obatBatch.setNamaObat(obatEntity.getNamaObat());
                    obatBatch.setHarga(obatEntity.getHarga());
                    obatBatch.setIdPabrik(obatEntity.getIdPabrik());

                    HargaObat hargaObat = new HargaObat();
                    hargaObat.setIdObat(obatBatch.getIdObat());
                    List<MtSimrsHargaObatEntity> hargaObatEntities = getListEntityHargaObat(hargaObat);
                    if (hargaObatEntities.size() > 0) {
                        MtSimrsHargaObatEntity hargaObatEntity = hargaObatEntities.get(0);

                        if ("box".equalsIgnoreCase(obatBatch.getJenisSatuan())) {
                            obatBatch.setHarga(hargaObatEntity.getHargaJual().multiply(new BigDecimal(cons)).toBigInteger());
                        }
                        if ("lembar".equalsIgnoreCase(obatBatch.getJenisSatuan())) {

                            BigInteger bHarga = hargaObatEntity.getHargaJual().toBigInteger();
                            BigInteger nHarga = obatEntity.getBijiPerLembar().multiply(bHarga);
                            obatBatch.setHarga(nHarga);
                        }
                        if ("biji".equalsIgnoreCase(obatBatch.getJenisSatuan())) {
                            obatBatch.setHarga(hargaObatEntity.getHargaJual().toBigInteger());
                        }

                    }

                    // harga*qty
                    BigInteger total = obatBatch.getHarga().multiply(obatBatch.getQty());
                    obatBatch.setTotalHarga(total);
                }

                PermintaanResep permintaanResep = new PermintaanResep();
                permintaanResep.setIdApprovalObat(obatBatch.getIdApprovalObat());
                ImSimrsPermintaanResepEntity resepEntity = getEntityPermintaanResepByCriteria(permintaanResep);
                if (resepEntity != null) {
                    obatBatch.setJenisResep(resepEntity.getJenisResep());
                    obatBatch.setIdPelayananTujuan(resepEntity.getTujuanPelayanan());
                    obatBatch.setIdPermintaanResep(resepEntity.getIdPermintaanResep());
                    ImSimrsPasienEntity pasienEntity = getEntityPasienById(resepEntity.getIdPasien());
                    if (pasienEntity != null) {
                        obatBatch.setNamaPasien(pasienEntity.getNama());
                    }
                }

            }
        }


        return listObatBatch;
    }

    private void saveTransaksiStokObatMasukKarnaReture(TransaksiObatDetail bean){

        logger.info("[TransaksiObatBoImpl.saveTransaksiStokRequestObatPoli] START >>>");
        // SAVE TO STOCK TRANSAKSI

        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        String tahun = CommonUtil.getDateParted(date, "YEAR");
        String bulan = CommonUtil.getDateParted(date, "MONTH");

        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriod = batasTutupPeriodDao.getBatasPeriodeDitutup(bean.getBranchId(), bulan, tahun);
        boolean flagTutup = batasTutupPeriod.size() > 0;
//        flagTutup = batasTutupPeriod.size() > 0;
//        if (batasTutupPeriod.size() > 0){
//            // jika sudah ditutup bulan ini
//            flagTutup = true;
//        }

        TransaksiStok saldoBulanLalu = new TransaksiStok();
        TransaksiStok saldoBulanLaluTujuan = new TransaksiStok();

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
                List<TransaksiStok> saldoBulanLaluList = getListTransaksiObat(bean.getIdPelayanan(), Integer.valueOf(tahun), Integer.valueOf(bulan), bean.getIdObat());
                if (saldoBulanLaluList.size() > 0){
                    saldoBulanLalu = saldoBulanLaluList.get(saldoBulanLaluList.size() -1);
                }
            }

            // cari apakah data baru pada pelayanan tujuan
            hsCriteria = new HashMap();
            hsCriteria.put("branch_id", bean.getBranchId());
            hsCriteria.put("id_barang", bean.getIdObat());
            hsCriteria.put("bulan", bulanDepan);
            hsCriteria.put("tahun", tahunDepan);
            hsCriteria.put("id_pelayanan", bean.getIdPelayananTujuan());

            transaksiStokEntities = new ArrayList<>();
            transaksiStokEntities = transaksiStokDao.getByCriteria(hsCriteria);
            if (transaksiStokEntities.size() == 0){

                // jika transaksi dengan obat dan pelayanan tujuan kosong
                // jika sudah ditutup bulan ini
                // maka hitung saldo bulan ini sebagai saldo bulan lalu
                List<TransaksiStok> saldoBulanLaluList = getListTransaksiObat(bean.getIdPelayananTujuan(), Integer.valueOf(tahun), Integer.valueOf(bulan), bean.getIdObat());
                if (saldoBulanLaluList.size() > 0){
                    saldoBulanLaluTujuan = saldoBulanLaluList.get(saldoBulanLaluList.size() -1);
                }
            }
        }

        String pelayananAsal = "";
        String pelayananTujuan = "";
        ImSimrsPelayananEntity pelayananEntity = pelayananDao.getById("idPelayanan", bean.getIdPelayanan());
        if (pelayananEntity != null){
            pelayananAsal = pelayananEntity.getNamaPelayanan();

            // pelayanan tujuan
            pelayananEntity = pelayananDao.getById("idPelayanan", bean.getIdPelayananTujuan());
            if (pelayananEntity != null){
                pelayananTujuan = pelayananEntity.getNamaPelayanan();
            }
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

        // pelayanan Asal
        ItSimrsTransaksiStokEntity transaksiStokEntity = new ItSimrsTransaksiStokEntity();
        transaksiStokEntity.setIdTransaksi(idBarangMasuk);
        transaksiStokEntity.setIdObat(idObat);
        transaksiStokEntity.setKeterangan("Pengembalian Obat " + namaObat + " No. Resep " + bean.getIdPermintaanResep());
        transaksiStokEntity.setTipe("D");
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
            logger.error("[TransaksiObatBoImpl.saveTransaksiStokObatMasukKarnaReture] ERROR.", e);
            throw new GeneralBOException("[TransaksiObatBoImpl.saveTransaksiStokObatMasukKarnaReture] ERROR." + e.getMessage());
        }

        logger.info("[TransaksiObatBoImpl.saveTransaksiStokObatMasukKarnaReture] END <<<<");
    }

    private MtSimrsObatPoliEntity getObatPoliEntityByIdBarang(String idBarang, String idPelayanan) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_barang", idBarang);
        hsCriteria.put("id_pelayanan", idPelayanan);
        hsCriteria.put("flag", "Y");

        List<MtSimrsObatPoliEntity> obatPoliEntities = new ArrayList<>();
        try {
            obatPoliEntities = obatPoliDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getObatPoliEntityByIdBarang] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getObatPoliEntityByIdBarang] ERROR." + e.getMessage());
        }

        if (obatPoliEntities.size() > 0){
            return obatPoliEntities.get(0);
        }

        return null;
    }

    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setRiwayatTransaksiObatDao(RiwayatTransaksiObatDao riwayatTransaksiObatDao) {
        this.riwayatTransaksiObatDao = riwayatTransaksiObatDao;
    }

    public void setTransaksiObatDetailDao(TransaksiObatDetailDao transaksiObatDetailDao) {
        this.transaksiObatDetailDao = transaksiObatDetailDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setPermintaanResepDao(PermintaanResepDao permintaanResepDao) {
        this.permintaanResepDao = permintaanResepDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setRiwayatTransPembelianObatDao(RiwayatTransPembelianObatDao riwayatTransPembelianObatDao) {
        this.riwayatTransPembelianObatDao = riwayatTransPembelianObatDao;
    }

    public void setObatPoliDao(ObatPoliDao obatPoliDao) {
        this.obatPoliDao = obatPoliDao;
    }

    public void setVendorDao(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public void setBatchDao(TransaksiObatDetailBatchDao batchDao) {
        this.batchDao = batchDao;
    }

    public void setHargaObatDao(HargaObatDao hargaObatDao) {
        this.hargaObatDao = hargaObatDao;
    }

    public void setTransaksiStokDao(TransaksiStokDao transaksiStokDao) {
        this.transaksiStokDao = transaksiStokDao;
    }

    public void setBatasTutupPeriodDao(BatasTutupPeriodDao batasTutupPeriodDao) {
        this.batasTutupPeriodDao = batasTutupPeriodDao;
    }
}
