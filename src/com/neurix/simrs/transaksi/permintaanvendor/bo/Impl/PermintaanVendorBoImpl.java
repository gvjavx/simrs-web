package com.neurix.simrs.transaksi.permintaanvendor.bo.Impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsObatPoliEntity;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.dao.PermintaanVendorDao;
import com.neurix.simrs.transaksi.permintaanvendor.dao.TempObatGejalaDao;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.ImSimrsTempObatGejalaEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.MtSimrsPermintaanVendorEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailBatchDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class PermintaanVendorBoImpl implements PermintaanVendorBo {
    private static transient Logger logger = Logger.getLogger(PermintaanVendorBoImpl.class);

    private PermintaanVendorDao permintaanVendorDao;
    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private TransaksiObatDetailDao transaksiObatDetailDao;
    private ObatDao obatDao;
    private TempObatGejalaDao tempObatGejalaDao;
    private TransaksiObatDetailBatchDao transaksiObatDetailBatchDao;


    @Override
    public List<PermintaanVendor> getByCriteria(PermintaanVendor bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getByCriteria] START >>>");

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        if (bean != null) {

            List<MtSimrsPermintaanVendorEntity> permintaanVendorEntities = getListEntityVendor(bean);
            if (permintaanVendorEntities != null && permintaanVendorEntities.size() > 0) {
                PermintaanVendor permintaanVendor;
                for (MtSimrsPermintaanVendorEntity permintaanVendorEntity : permintaanVendorEntities) {
                    permintaanVendor = new PermintaanVendor();
                    permintaanVendor.setIdPermintaanVendor(permintaanVendorEntity.getIdPermintaanVendor());
                    permintaanVendor.setIdApprovalObat(permintaanVendorEntity.getIdApprovalObat());
                    permintaanVendor.setIdVendor(permintaanVendorEntity.getIdVendor());
                    permintaanVendor.setFlag(permintaanVendorEntity.getFlag());
                    permintaanVendor.setAction(permintaanVendorEntity.getAction());

                    String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(permintaanVendorEntity.getCreatedDate());

                    permintaanVendor.setStCreatedDate(formatDate);
                    permintaanVendor.setCreatedDate(permintaanVendorEntity.getCreatedDate());
                    permintaanVendor.setCreatedWho(permintaanVendorEntity.getCreatedWho());
                    permintaanVendor.setLastUpdate(permintaanVendorEntity.getLastUpdate());
                    permintaanVendor.setLastUpdateWho(permintaanVendorEntity.getLastUpdateWho());


                    if (!"".equalsIgnoreCase(permintaanVendor.getIdApprovalObat())) {

                        // check if all list obat from vendor has cofirmed by staff gudang
                        permintaanVendor.setEnableApprove(isAvailNotConfirm(permintaanVendorEntity.getIdApprovalObat()));

                        ImtSimrsApprovalTransaksiObatEntity approvalEntity = getApprovalTransaksiById(permintaanVendor.getIdApprovalObat());
                        if (approvalEntity != null) {

                            if (permintaanVendor.getEnableApprove()) {
                                permintaanVendor.setKeterangan("Telah Dikonfirmasi");
                            } else {
                                permintaanVendor.setKeterangan("Prosess Verivikasi");
                            }

//                            permintaanVendor.setApprovalFlag(approvalEntity.getApprovalFlag());
//                            if ("Y".equalsIgnoreCase(approvalEntity.getApprovalFlag())) {
//                                permintaanVendor.setEnableApprove(false);
//                            }
                        }

                        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                        transaksiObatDetail.setIdApprovalObat(permintaanVendor.getIdApprovalObat());
                        if ("R".equalsIgnoreCase(bean.getNotFlagR())) {
                            transaksiObatDetail.setFlagDiterima("R");
                        }
                        // transaksi obat detail
                        List<ImtSimrsTransaksiObatDetailEntity> transaksiObatDetailEntities = getListEntityTransObatDetail(transaksiObatDetail);
                        if (transaksiObatDetailEntities.size() > 0) {
                            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
                            TransaksiObatDetail transaksiObatDetail1;
                            for (ImtSimrsTransaksiObatDetailEntity transaksiObatDetailEntity : transaksiObatDetailEntities) {

                                ImSimrsObatEntity obatEntity = getObatById(transaksiObatDetailEntity.getIdObat());

                                transaksiObatDetail = new TransaksiObatDetail();
                                transaksiObatDetail.setIdTransaksiObatDetail(transaksiObatDetailEntity.getIdTransaksiObatDetail());
                                transaksiObatDetail.setNamaObat(obatEntity.getNamaObat());
                                transaksiObatDetail.setIdApprovalObat(transaksiObatDetailEntity.getIdApprovalObat());
                                transaksiObatDetail.setIdObat(transaksiObatDetailEntity.getIdObat());
                                transaksiObatDetail.setFlag(transaksiObatDetailEntity.getFlag());
                                transaksiObatDetail.setAction(transaksiObatDetailEntity.getAction());
                                transaksiObatDetail.setCreatedDate(transaksiObatDetailEntity.getCreatedDate());
                                transaksiObatDetail.setLastUpdate(transaksiObatDetailEntity.getLastUpdate());
                                transaksiObatDetail.setCreatedWho(transaksiObatDetailEntity.getCreatedWho());
                                transaksiObatDetail.setLastUpdateWho(transaksiObatDetailEntity.getLastUpdateWho());
                                transaksiObatDetail.setKeterangan(transaksiObatDetailEntity.getKeterangan());
                                transaksiObatDetail.setQtyApprove(transaksiObatDetailEntity.getQtyApprove());
                                transaksiObatDetail.setQtyBox(transaksiObatDetailEntity.getQtyBox());
                                transaksiObatDetail.setQtyLembar(transaksiObatDetailEntity.getQtyLembar());
                                transaksiObatDetail.setQtyBiji(transaksiObatDetailEntity.getQtyBiji());
                                transaksiObatDetail.setQty(transaksiObatDetailEntity.getQty());
                                transaksiObatDetail.setLembarPerBox(transaksiObatDetailEntity.getLembarPerBox());
                                transaksiObatDetail.setBijiPerLembar(transaksiObatDetailEntity.getBijiPerLembar());
                                transaksiObatDetail.setAverageHargaBox(transaksiObatDetailEntity.getAverageHargaBox());
                                transaksiObatDetail.setAverageHargaLembar(transaksiObatDetailEntity.getAverageHargaLembar());
                                transaksiObatDetail.setAverageHargaBiji(transaksiObatDetailEntity.getAverageHargaBiji());
                                transaksiObatDetail.setFlagDiterima(transaksiObatDetailEntity.getFlagDiterima());
                                transaksiObatDetail.setJenisSatuan(transaksiObatDetailEntity.getJenisSatuan());
                                transaksiObatDetail.setIdPabrik(transaksiObatDetailEntity.getIdPabrik());
                                transaksiObatDetail.setMerek(transaksiObatDetailEntity.getMrek());

                                if ("box".equalsIgnoreCase(transaksiObatDetailEntity.getJenisSatuan())) {
                                    transaksiObatDetail.setHargaPo(transaksiObatDetailEntity.getAverageHargaBox());
                                }
                                if ("lembar".equalsIgnoreCase(transaksiObatDetailEntity.getJenisSatuan())) {
                                    transaksiObatDetail.setHargaPo(transaksiObatDetailEntity.getAverageHargaLembar());
                                }
                                if ("biji".equalsIgnoreCase(transaksiObatDetailEntity.getJenisSatuan())) {
                                    transaksiObatDetail.setHargaPo(transaksiObatDetailEntity.getAverageHargaBiji());
                                }
                                transaksiObatDetails.add(transaksiObatDetail);
                            }
                            permintaanVendor.setListOfTransaksiObatDetail(transaksiObatDetails);
                        }
                    }
                    permintaanVendorList.add(permintaanVendor);
                }
            }
        }

        logger.info("[PermintaanVendorBoImpl.getByCriteria] END <<<");
        return permintaanVendorList;
    }

    public List<MtSimrsPermintaanVendorEntity> getListEntityVendor(PermintaanVendor bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getListEntityVendor] START >>>");

        List<MtSimrsPermintaanVendorEntity> permintaanVendorEntityList = null;
        if (bean != null) {

            Map hsCriteria = new HashMap();
            if (bean.getIdPermintaanVendor() != null && !"".equalsIgnoreCase(bean.getIdPermintaanVendor())) {
                hsCriteria.put("id_permintaan_vendor", bean.getIdPermintaanVendor());
            }
            if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {
                hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            }
            if (bean.getIdVendor() != null && !"".equalsIgnoreCase(bean.getIdVendor())) {
                hsCriteria.put("id_vendor", bean.getIdVendor());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                permintaanVendorEntityList = permintaanVendorDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PermintaanVendorBoImpl.getListEntityVendor] ERROR when get data. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.getListEntityVendor] ERROR when get data. " + e.getMessage());
            }
        }

        logger.info("[PermintaanVendorBoImpl.getListEntityVendor] END <<<");
        return permintaanVendorEntityList;
    }

    private List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getListEntityTransObatDetail] START >>>>>>");

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();
        if (!"".equalsIgnoreCase(bean.getIdApprovalObat())) {
            Map hsCriteria = new HashMap();
            if (bean.getIdTransaksiObatDetail() != null && !"".equalsIgnoreCase(bean.getIdTransaksiObatDetail())) {
                hsCriteria.put("id_transaksi_obat_detail", bean.getIdTransaksiObatDetail());
            }

            if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {
                hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            if (bean.getFlagDiterima() != null && !"".equalsIgnoreCase(bean.getFlagDiterima())) {
                hsCriteria.put("flag_not_r", bean.getFlagDiterima());
            }

            try {
                obatDetailEntities = transaksiObatDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PermintaanVendorBoImpl.getListEntityTransObatDetail] ERROR when get data list entity of trans obat detail. ", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.getListEntityTransObatDetail] ERROR when get data list entity of trans obat detail. " + e.getMessage());
            }
        }

        logger.info("[PermintaanVendorBoImpl.getListEntityTransObatDetail] END <<<<<");
        return obatDetailEntities;
    }

    private ImtSimrsApprovalTransaksiObatEntity getApprovalTransaksiById(String id) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getApprovalTransaksiById] START >>>>>>>>>>");

        List<ImtSimrsApprovalTransaksiObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_approval_obat", id);
//        hsCriteria.put("flag", "Y");

        try {
            obatEntities = approvalTransaksiObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ", e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
            return obatEntities.get(0);
        }
        logger.info("[PermintaanVendorBoImpl.getApprovalTransaksiById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsObatEntity getObatById(String id) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getObatById] START >>>>>>>>>>");
        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", id);
        hsCriteria.put("flag", "Y");
        hsCriteria.put("asc", "Y");

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
            return obatEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getObatById] END <<<<<<<<<<");
        return null;
    }

    @Override
    public void saveListObatPo(PermintaanVendor bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.saveListObatPo] START >>>");

        if (bean != null) {
            ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
            String id = getNextApprovalObatId();
            approvalEntity.setIdApprovalObat("INV" + id);
            approvalEntity.setIdPelayanan(bean.getIdPelayanan());
            approvalEntity.setBranchId(bean.getBranchId());
            approvalEntity.setFlag(bean.getFlag());
            approvalEntity.setAction(bean.getAction());
            approvalEntity.setTipePermintaan("004");
            approvalEntity.setLastUpdate(bean.getCreatedDate());
            approvalEntity.setLastUpdateWho(bean.getCreatedWho());
            approvalEntity.setCreatedDate(bean.getCreatedDate());
            approvalEntity.setCreatedWho(bean.getCreatedWho());

            try {
                approvalTransaksiObatDao.addAndSave(approvalEntity);
            } catch (HibernateException e) {
                logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when insert into approval transaksi. ", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when insert into approval transaksi. ", e);
            }

            MtSimrsPermintaanVendorEntity permintaanVendorEntity = new MtSimrsPermintaanVendorEntity();
            permintaanVendorEntity.setIdPermintaanVendor("PVM" + nextIdPermintanVendor());
            permintaanVendorEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
            permintaanVendorEntity.setIdVendor(bean.getIdVendor());
            permintaanVendorEntity.setBranchId(bean.getBranchId());
            permintaanVendorEntity.setFlag("Y");
            permintaanVendorEntity.setAction("C");
            permintaanVendorEntity.setCreatedDate(bean.getCreatedDate());
            permintaanVendorEntity.setCreatedWho(bean.getCreatedWho());
            permintaanVendorEntity.setLastUpdate(bean.getLastUpdate());
            permintaanVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                permintaanVendorDao.addAndSave(permintaanVendorEntity);
            } catch (HibernateException e) {
                logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create permintaan vendor. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create permintaan vendor. " + e.getMessage());
            }

            if (bean.getListOfTransaksiObatDetail().size() > 0) {
                for (TransaksiObatDetail obatDetail : bean.getListOfTransaksiObatDetail()) {
                    ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                    obatDetailEntity.setIdTransaksiObatDetail("ODT" + getNextTransaksiObatDetail());
                    obatDetailEntity.setIdApprovalObat(permintaanVendorEntity.getIdApprovalObat());
                    obatDetailEntity.setQtyBox(obatDetail.getQtyBox());
                    obatDetailEntity.setIdObat(obatDetail.getIdObat());
                    obatDetailEntity.setLembarPerBox(obatDetail.getLembarPerBox());
                    obatDetailEntity.setQtyLembar(obatDetail.getQtyLembar());
                    obatDetailEntity.setBijiPerLembar(obatDetail.getBijiPerLembar());
                    obatDetailEntity.setQtyBiji(obatDetail.getQtyBiji());
                    obatDetailEntity.setQty(obatDetail.getQty());
                    obatDetailEntity.setAverageHargaBox(obatDetail.getAverageHargaBox());
                    obatDetailEntity.setFlag("Y");
                    obatDetailEntity.setAction("C");
                    obatDetailEntity.setCreatedDate(bean.getCreatedDate());
                    obatDetailEntity.setCreatedWho(bean.getCreatedWho());
                    obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                    obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    obatDetailEntity.setJenisSatuan(obatDetail.getJenisSatuan());
                    obatDetailEntity.setKeterangan("Permintaan PO");

                    try {
                        transaksiObatDetailDao.addAndSave(obatDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                        throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                    }
                }
            }
        }
        logger.info("[PermintaanVendorBoImpl.saveListObatPo] END <<<");
    }

    @Override
    public void saveUpdateTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] START >>>");

//        List<ImtSimrsTransaksiObatDetailEntity> transaksiObatDetailEntities = getListEntityTransObatDetail(bean);
//        if (transaksiObatDetailEntities.size() > 0) {
//            ImtSimrsTransaksiObatDetailEntity transaksiObatDetailEntity = transaksiObatDetailEntities.get(0);

        ImtSimrsTransaksiObatDetailEntity transaksiObatDetailEntity = transaksiObatDetailDao.getById("idTransaksiObatDetail", bean.getIdTransaksiObatDetail());

        if (bean.getNoBatch() != null && bean.getNoBatch().compareTo(0) == 1){

            Boolean isNew = isNewBatchCheckByNoBatchAndExpDate(bean.getIdTransaksiObatDetail(), bean.getNoBatch(), bean.getExpDate());

            if (isNew){

                MtSimrsTransaksiObatDetailBatchEntity obatDetailBatchEntity = new MtSimrsTransaksiObatDetailBatchEntity();
                obatDetailBatchEntity.setId(new BigInteger(getNextIdBatchObat()));
                obatDetailBatchEntity.setIdTransaksiObatDetail(bean.getIdTransaksiObatDetail());
                obatDetailBatchEntity.setNoBatch(bean.getNoBatch());
                obatDetailBatchEntity.setQtyApprove(bean.getQtyApprove());
                obatDetailBatchEntity.setJenisSatuan(transaksiObatDetailEntity.getJenisSatuan());
                obatDetailBatchEntity.setFlag("Y");
                obatDetailBatchEntity.setAction("C");
                obatDetailBatchEntity.setStatus(bean.getFlagDiterima());
                obatDetailBatchEntity.setExpiredDate(bean.getExpDate());
                obatDetailBatchEntity.setCreatedDate(bean.getLastUpdate());
                obatDetailBatchEntity.setCreatedWho(bean.getLastUpdateWho());
                obatDetailBatchEntity.setLastUpdate(bean.getLastUpdate());
                obatDetailBatchEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    transaksiObatDetailBatchDao.addAndSave(obatDetailBatchEntity);
                } catch (HibernateException e){
                    logger.error("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when add obat detail batch. " + e.getMessage());
                    throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when add obat detail batch. " + e.getMessage());
                }

            } else {
                MtSimrsTransaksiObatDetailBatchEntity batchEntity = getEntityObatBatchByIdTransObat(bean.getIdTransaksiObatDetail(), bean.getNoBatch(), bean.getExpDate());
                batchEntity.setQtyApprove(batchEntity.getQtyApprove().add(bean.getQtyApprove()));
                batchEntity.setAction("U");
                batchEntity.setLastUpdate(bean.getLastUpdate());
                batchEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    transaksiObatDetailBatchDao.updateAndSave(batchEntity);
                } catch (HibernateException e){
                    logger.error("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when update obat detail batch. " + e.getMessage());
                    throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when update obat detail batch. " + e.getMessage());
                }
            }

        } else {

            MtSimrsTransaksiObatDetailBatchEntity obatDetailBatchEntity = new MtSimrsTransaksiObatDetailBatchEntity();
            obatDetailBatchEntity.setId(new BigInteger(getNextIdBatchObat()));
            obatDetailBatchEntity.setIdTransaksiObatDetail(bean.getIdTransaksiObatDetail());
            obatDetailBatchEntity.setNoBatch(1);
            obatDetailBatchEntity.setQtyApprove(bean.getQtyApprove());
            obatDetailBatchEntity.setJenisSatuan(transaksiObatDetailEntity.getJenisSatuan());
            obatDetailBatchEntity.setFlag("Y");
            obatDetailBatchEntity.setAction("C");
            obatDetailBatchEntity.setStatus(bean.getFlagDiterima());
            obatDetailBatchEntity.setExpiredDate(bean.getExpDate());
            obatDetailBatchEntity.setCreatedDate(bean.getLastUpdate());
            obatDetailBatchEntity.setCreatedWho(bean.getLastUpdateWho());
            obatDetailBatchEntity.setLastUpdate(bean.getLastUpdate());
            obatDetailBatchEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                transaksiObatDetailBatchDao.addAndSave(obatDetailBatchEntity);
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when add obat detail batch. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when add obat detail batch. " + e.getMessage());
            }
        }


        if (transaksiObatDetailEntity != null) {

            //transaksiObatDetailEntity.setQtyApprove(bean.getQty());
            //transaksiObatDetailEntity.setFlagDiterima(bean.getFlagDiterima());
            transaksiObatDetailEntity.setIdPabrik(bean.getIdPabrik());
            transaksiObatDetailEntity.setAction("U");
            //transaksiObatDetailEntity.setLastUpdate(bean.getLastUpdate());
            //transaksiObatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
            transaksiObatDetailEntity.setKeterangan(bean.getKeterangan());
            //transaksiObatDetailEntity.setLembarPerBox(bean.getLembarPerBox());
            //transaksiObatDetailEntity.setBijiPerLembar(bean.getBijiPerLembar());

            try {
                transaksiObatDetailDao.updateAndSave(transaksiObatDetailEntity);
            } catch (HibernateException e) {
                logger.error("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when update obat detail. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when update obat detail. " + e.getMessage());
            }
        }
        logger.info("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] END <<<");
    }

    @Override
    public void saveNewPabrik(TransaksiObatDetail bean, List<String> idJenisObats) throws GeneralBOException {

        if (bean != null) {

            ImtSimrsTransaksiObatDetailEntity entityList = new ImtSimrsTransaksiObatDetailEntity();

            try {
                entityList = transaksiObatDetailDao.getById("idTransaksiObatDetail", bean.getIdTransaksiObatDetail());
            } catch (HibernateException e) {
                logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
            }

            if (entityList != null) {

                entityList.setFlagDiterima("N");
                entityList.setIdPabrik(bean.getIdPabrik());
                entityList.setLastUpdate(bean.getLastUpdate());
                entityList.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    transaksiObatDetailDao.updateAndSave(entityList);
                } catch (HibernateException e) {
                    logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                    throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                }

                ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                obatDetailEntity.setIdTransaksiObatDetail("ODT" + getNextTransaksiObatDetail());
                obatDetailEntity.setIdApprovalObat(bean.getIdApprovalObat());
                obatDetailEntity.setQtyBox(bean.getQtyBox());
                obatDetailEntity.setIdObat(bean.getIdObat());
                obatDetailEntity.setNamaObatBaru(bean.getNamaObat());
                obatDetailEntity.setLembarPerBox(bean.getLembarPerBox());
                obatDetailEntity.setQtyLembar(bean.getQtyLembar());
                obatDetailEntity.setBijiPerLembar(bean.getBijiPerLembar());
                obatDetailEntity.setQtyBiji(bean.getQtyBiji());
                obatDetailEntity.setQty(bean.getQty());
                obatDetailEntity.setQtyApprove(bean.getQty());
                obatDetailEntity.setAverageHargaBox(bean.getAverageHargaBox());
                obatDetailEntity.setFlag(bean.getFlag());
                obatDetailEntity.setAction(bean.getAction());
                obatDetailEntity.setCreatedDate(bean.getCreatedDate());
                obatDetailEntity.setCreatedWho(bean.getCreatedWho());
                obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                obatDetailEntity.setJenisSatuan(bean.getJenisSatuan());
                obatDetailEntity.setFlagDiterima(bean.getFlagDiterima());
                obatDetailEntity.setKeterangan("Permintaan PO");
                obatDetailEntity.setIdPabrik(bean.getIdPabrik());
                obatDetailEntity.setMrek(bean.getMerek());

                if ("box".equalsIgnoreCase(bean.getJenisSatuan())) {
                    obatDetailEntity.setAverageHargaBox(bean.getHargaPo());
                }

                if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())) {
                    obatDetailEntity.setAverageHargaLembar(bean.getHargaPo());
                }

                if ("biji".equalsIgnoreCase(bean.getJenisSatuan())) {
                    obatDetailEntity.setAverageHargaBiji(bean.getHargaPo());
                }

                try {
                    transaksiObatDetailDao.addAndSave(obatDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                    throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                }

                if (!idJenisObats.isEmpty() && idJenisObats.size() > 0) {
                    for (String idJenisObat : idJenisObats) {
                        ImSimrsTempObatGejalaEntity tempObatGejalaEntity = new ImSimrsTempObatGejalaEntity();
                        tempObatGejalaEntity.setIdTempObatGejala("OGJ" + getNextIdTmpObatGejala());
                        tempObatGejalaEntity.setIdTransObatDetail(bean.getIdTransaksiObatDetail());
                        tempObatGejalaEntity.setIdJenisObat(idJenisObat);
                        tempObatGejalaEntity.setFlag(bean.getFlag());
                        tempObatGejalaEntity.setAction(bean.getAction());
                        tempObatGejalaEntity.setCreatedDate(bean.getCreatedDate());
                        tempObatGejalaEntity.setCreatedWho(bean.getCreatedWho());
                        tempObatGejalaEntity.setLastUpdate(bean.getLastUpdate());
                        tempObatGejalaEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            tempObatGejalaDao.addAndSave(tempObatGejalaEntity);
                        } catch (HibernateException e) {
                            logger.error("[ObatBoImpl.saveAdd] error when insert new obat gejala " + e.getMessage());
                            throw new GeneralBOException("[ObatBoImpl.saveAdd] error when insert new obat gejala " + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    public void saveConfirm(PermintaanVendor bean, List<TransaksiObatDetail> listObat, List<TransaksiObatDetail> listObatNew) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.saveConfirm] START >>>");

//         update to master obat k/k j
        if (listObat != null && listObat.size() > 0)
        {
            for (TransaksiObatDetail obatDetail : listObat) {
                if ("Y".equalsIgnoreCase(obatDetail.getFlagDiterima())) {

                    TransaksiObatBatch obatBatch = new TransaksiObatBatch();
                    obatBatch.setIdTransaksiObatDetail(obatDetail.getIdTransaksiObatDetail());
                    obatBatch.setNoBatch(obatDetail.getNoBatch());


                    List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchObat(obatBatch);

                    if (batchEntities.size() > 0){
                        for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntities){

                            if ("Y".equalsIgnoreCase(batchEntity.getStatus())){

                                obatDetail.setQtyApprove(batchEntity.getQtyApprove());

                                Obat obat = new Obat();
                                obat.setIdObat(obatDetail.getIdObat());
                                obat.setExpiredDate(batchEntity.getExpiredDate());
                                obat.setBranchId(CommonUtil.userBranchLogin());
                                List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);

                                if (obatEntities.size() > 0){
                                    obatDetail.setStatus("Y");
                                    obatDetail.setIdSeqObat(obatEntities.get(0).getIdSeqObat());
                                } else {
                                    obatDetail.setStatus("R");
                                }

                                obatDetail.setExpDate(batchEntity.getExpiredDate());

                                //update stock and new harga rata-rata
                                updateAddStockGudang(obatDetail);
                            }

                            batchEntity.setApproveFlag("Y");
                            batchEntity.setAction("U");
                            batchEntity.setLastUpdate(bean.getLastUpdate());
                            batchEntity.setLastUpdateWho(bean.getLastUpdateWho());

                            try {
                                transaksiObatDetailBatchDao.updateAndSave(batchEntity);
                            } catch (HibernateException e){
                                logger.error("[PermintaanVendorBoImpl.saveConfirm] ERROR.", e);
                                throw new GeneralBOException("[PermintaanVendorBoImpl.saveConfirm] ERROR." + e.getMessage());
                            }
                        }
                    }
                }
            }
        }

        // insert new to master obat
        if (listObatNew != null && listObatNew.size() > 0) {
            for (TransaksiObatDetail obatDetail : listObatNew) {
                if ("R".equalsIgnoreCase(obatDetail.getFlagDiterima())) {
                    ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();

                    obatEntity.setIdObat("OBT" + getNextIdObat());
                    obatEntity.setNamaObat(obatDetail.getNamaObat());
                    obatEntity.setLembarPerBox(obatDetail.getLembarPerBox());
                    obatEntity.setBijiPerLembar(obatDetail.getBijiPerLembar());
                    obatEntity.setBranchId(bean.getBranchId());
                    obatEntity.setFlag("Y");
                    obatEntity.setAction("U");
                    obatEntity.setCreatedDate(bean.getLastUpdate());
                    obatEntity.setLastUpdate(bean.getLastUpdate());
                    obatEntity.setCreatedWho(bean.getLastUpdateWho());
                    obatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    BigInteger nullBigInt = new BigInteger(String.valueOf(0));
                    obatEntity.setAverageHargaBox(new BigDecimal(nullBigInt));
                    obatEntity.setAverageHargaLembar(new BigDecimal(nullBigInt));
                    obatEntity.setAverageHargaBiji(new BigDecimal(nullBigInt));

                    if ("box".equalsIgnoreCase(obatDetail.getJenisSatuan())) {
                        obatEntity.setAverageHargaBox(obatDetail.getAverageHargaBox());
                        obatEntity.setQtyBox(obatDetail.getQtyApprove());
                    }
                    if ("lembar".equalsIgnoreCase(obatDetail.getJenisSatuan())) {
                        obatEntity.setAverageHargaLembar(obatDetail.getAverageHargaLembar());
                        obatEntity.setQtyLembar(obatDetail.getQtyApprove());
                    }
                    if ("biji".equalsIgnoreCase(obatDetail.getJenisSatuan())) {
                        obatEntity.setAverageHargaBiji(obatDetail.getAverageHargaBiji());
                        obatEntity.setQtyBiji(obatDetail.getQtyApprove());
                    }

                    try {
                        obatDao.addAndSave(obatEntity);
                    } catch (HibernateException e) {
                        logger.error("[PermintaanVendorBoImpl.saveConfirm] ERROR.", e);
                        throw new GeneralBOException("[PermintaanVendorBoImpl.saveConfirm] ERROR." + e.getMessage());
                    }
                }
            }
        }

        logger.info("[PermintaanVendorBoImpl.saveConfirm] END <<<");
    }

    private List<ImSimrsObatEntity> getListEntityObat(Obat bean) throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.getListEntityObat] START >>>");

        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean.getIdSeqObat() != null && !"".equalsIgnoreCase(bean.getIdSeqObat())){
            hsCriteria.put("id_seq_obat", bean.getIdSeqObat());
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getExpiredDate() != null){
            hsCriteria.put("exp_date", bean.getExpiredDate());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            hsCriteria.put("branch_id", bean.getBranchId());
        }

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getListEntityObat] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getListEntityObat] ERROR." + e.getMessage());
        }

        logger.info("[PermintaanVendorBoImpl.getListEntityObat] END <<<");
        return obatEntities;
    }

    private void updateAddStockGudang(TransaksiObatDetail bean) throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.updateAddStockGudang] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();

        Obat sumObat = new Obat();

        Obat obat = new Obat();
        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();
        if ("Y".equalsIgnoreCase(bean.getStatus())){

            obat.setIdSeqObat(bean.getIdSeqObat());
            obatEntities = getListEntityObat(obat);
            if (obatEntities.size() > 0){
                obatEntity = obatEntities.get(0);
            }

        } else {
            obatEntity = getObatById(bean.getIdObat());
        }

        try {
            sumObat = obatDao.getSumStockObatGudangById(bean.getIdObat());
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR." + e.getMessage());
        }

        BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());

        BigInteger allStockToBiji = (sumObat.getQtyBox().multiply(cons))
                .add(sumObat.getQtyLembar().multiply(obatEntity.getBijiPerLembar()))
                .add(sumObat.getQtyBiji());

        BigInteger ttlQtyPermintaan = new BigInteger(String.valueOf(0));
        BigDecimal ttlAvgHargaPermintaan = new BigDecimal(0);

        if (bean != null && "R".equalsIgnoreCase(bean.getStatus())){

            ImSimrsObatEntity newObatEntity = new ImSimrsObatEntity();
            newObatEntity.setIdSeqObat(getIdNextSeqObat());
            newObatEntity.setIdObat(bean.getIdObat());
            newObatEntity.setNamaObat(bean.getNamaObat());
            newObatEntity.setIdPabrik(bean.getIdPabrik());
            newObatEntity.setExpiredDate(bean.getExpDate());
            newObatEntity.setLembarPerBox(obatEntity.getLembarPerBox());
            newObatEntity.setBijiPerLembar(obatEntity.getBijiPerLembar());
            newObatEntity.setMerk(obatEntity.getMerk());

            if ("box".equalsIgnoreCase(bean.getJenisSatuan())){
                newObatEntity.setQtyBox(bean.getQtyApprove());

                ttlQtyPermintaan = bean.getQtyApprove().multiply(cons);
                ttlAvgHargaPermintaan = (bean.getAverageHargaBox().divide(new BigDecimal(cons), 2, RoundingMode.HALF_UP))
                        .multiply(new BigDecimal(ttlQtyPermintaan));
            }
            if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())){
                newObatEntity.setQtyLembar(bean.getQtyApprove());

                ttlQtyPermintaan = bean.getQtyApprove().multiply(obatEntity.getBijiPerLembar());
                ttlAvgHargaPermintaan = (bean.getAverageHargaLembar().divide(new BigDecimal(obatEntity.getBijiPerLembar()),2, RoundingMode.HALF_UP ))
                        .multiply(new BigDecimal(ttlQtyPermintaan));
            }
            if ("biji".equalsIgnoreCase(bean.getJenisSatuan())){
                newObatEntity.setQtyBiji(bean.getQtyApprove());

                ttlQtyPermintaan = bean.getQtyApprove();
                ttlAvgHargaPermintaan = bean.getAverageHargaBiji().multiply(new BigDecimal(ttlQtyPermintaan));
            }

            BigDecimal ttlHargaBijian = (obatEntity.getAverageHargaBiji().multiply(new BigDecimal(allStockToBiji))).add(ttlAvgHargaPermintaan);
            BigInteger ttlQty = allStockToBiji.add(ttlQtyPermintaan);
            BigDecimal newAvgHargaBijian = ttlHargaBijian.divide(new BigDecimal(ttlQty), 2, RoundingMode.HALF_UP);

            if (obatEntity.getLembarPerBox().compareTo(new BigInteger(String.valueOf(0))) == 1){
                newObatEntity.setAverageHargaBox(newAvgHargaBijian.multiply(new BigDecimal(cons)));
                newObatEntity.setAverageHargaLembar(newAvgHargaBijian.multiply(new BigDecimal(obatEntity.getBijiPerLembar())));
            }
            if (obatEntity.getBijiPerLembar().compareTo(new BigInteger(String.valueOf(0))) == 1){
                newObatEntity.setAverageHargaBiji(newAvgHargaBijian);
            }

            newObatEntity.setFlag("Y");
            newObatEntity.setAction("C");
            newObatEntity.setCreatedDate(time);
            newObatEntity.setCreatedWho(userLogin);
            newObatEntity.setLastUpdate(time);
            newObatEntity.setLastUpdateWho(userLogin);
            newObatEntity.setBranchId(CommonUtil.userBranchLogin());

            try {
                obatDao.addAndSave(newObatEntity);
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR.", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR." + e.getMessage());
            }

            // update all harga id obat tersebut
            updateAllNewAverageHargaByObatId(bean.getIdObat(), newObatEntity.getAverageHargaBox(), newObatEntity.getAverageHargaLembar(), newObatEntity.getAverageHargaBiji());

        } else {

            if ("box".equalsIgnoreCase(bean.getJenisSatuan())){
                obatEntity.setQtyBox(obatEntity.getQtyBox().add(bean.getQtyApprove()));

                ttlQtyPermintaan = bean.getQtyApprove().multiply(cons);
                ttlAvgHargaPermintaan = (bean.getAverageHargaBox().divide(new BigDecimal(cons), 2, RoundingMode.HALF_UP))
                        .multiply(new BigDecimal(ttlQtyPermintaan));
            }
            if ("lembar".equalsIgnoreCase(bean.getJenisSatuan()) && obatEntity.getLembarPerBox().compareTo(new BigInteger(String.valueOf(0))) == 1){
                obatEntity.setQtyLembar(obatEntity.getQtyLembar().add(bean.getQtyApprove()));

                ttlQtyPermintaan = bean.getQtyApprove().multiply(obatEntity.getBijiPerLembar());
                ttlAvgHargaPermintaan = (bean.getAverageHargaLembar().divide(new BigDecimal(obatEntity.getBijiPerLembar()),2, RoundingMode.HALF_UP ))
                        .multiply(new BigDecimal(ttlQtyPermintaan));
            }
            if ("biji".equalsIgnoreCase(bean.getJenisSatuan()) && obatEntity.getBijiPerLembar().compareTo(new BigInteger(String.valueOf(0))) == 1){
                obatEntity.setQtyBiji(obatEntity.getQtyBiji().add(bean.getQtyApprove()));

                ttlQtyPermintaan = bean.getQtyApprove();
                ttlAvgHargaPermintaan = bean.getAverageHargaBiji().multiply(new BigDecimal(ttlQtyPermintaan));
            }

            BigDecimal ttlHargaBijian = (obatEntity.getAverageHargaBiji().multiply(new BigDecimal(allStockToBiji))).add(ttlAvgHargaPermintaan);
            BigInteger ttlQty = allStockToBiji.add(ttlQtyPermintaan);
            BigDecimal newAvgHargaBijian = ttlHargaBijian.divide(new BigDecimal(ttlQty), 2, RoundingMode.HALF_UP);

            if (obatEntity.getLembarPerBox().compareTo(new BigInteger(String.valueOf(0))) == 1){
                obatEntity.setAverageHargaBox(newAvgHargaBijian.multiply(new BigDecimal(cons)));
                obatEntity.setAverageHargaLembar(newAvgHargaBijian.multiply(new BigDecimal(obatEntity.getBijiPerLembar())));
            }
            if (obatEntity.getBijiPerLembar().compareTo(new BigInteger(String.valueOf(0))) == 1){
                obatEntity.setAverageHargaBiji(newAvgHargaBijian);
            }

            obatEntity.setFlag("Y");
            obatEntity.setAction("U");
            obatEntity.setLastUpdate(time);
            obatEntity.setLastUpdateWho(userLogin);

            try {
                obatDao.updateAndSave(obatEntity);
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR.", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR." + e.getMessage());
            }

            // update all harga id obat tersebut
            updateAllNewAverageHargaByObatId(bean.getIdObat(), obatEntity.getAverageHargaBox(), obatEntity.getAverageHargaLembar(), obatEntity.getAverageHargaBiji());
        }

//        if (bean != null){
//
////            ImSimrsObatEntity obatEntity = getObatById(bean.getIdObat());
//            if (obatEntity != null){
//
////                BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());
////                BigInteger allStockToBiji = (obatEntity.getQtyBox().multiply(cons))
////                        .add(obatEntity.getQtyLembar().multiply(obatEntity.getBijiPerLembar()))
////                        .add(obatEntity.getQtyBiji());
//
////                BigInteger ttlQtyPermintaan = new BigInteger(String.valueOf(0));
////                BigDecimal ttlAvgHargaPermintaan = new BigDecimal(0);
//
//                if ("box".equalsIgnoreCase(bean.getJenisSatuan())){
//                    obatEntity.setQtyBox(obatEntity.getQtyBox().add(bean.getQtyApprove()));
//
//                    ttlQtyPermintaan = bean.getQtyApprove().multiply(cons);
//                    ttlAvgHargaPermintaan = (bean.getAverageHargaBox().divide(new BigDecimal(cons), 2, RoundingMode.HALF_UP))
//                            .multiply(new BigDecimal(ttlQtyPermintaan));
//
//                } else {
//
//                    if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())){
//                        if ("lembar".equalsIgnoreCase(bean.getJenisSatuan()) && obatEntity.getLembarPerBox().compareTo(new BigInteger(String.valueOf(0))) == 1){
//                            if (bean.getQtyApprove().compareTo(obatEntity.getLembarPerBox()) == 1){
//                                BigInteger lembarPermintaanToBox = bean.getQtyApprove().divide(obatEntity.getLembarPerBox());
//                                BigInteger modLembarPermintaanToBox = bean.getQtyApprove().mod(obatEntity.getLembarPerBox());
//
//                                obatEntity.setQtyBox(obatEntity.getQtyBox().add(lembarPermintaanToBox));
//                                obatEntity.setQtyLembar(obatEntity.getQtyLembar().add(modLembarPermintaanToBox));
//                            } else {
//                                obatEntity.setQtyLembar(obatEntity.getQtyLembar().add(bean.getQtyApprove()));
//                            }
//                        } else {
//                            obatEntity.setQtyLembar(obatEntity.getQtyLembar().add(bean.getQtyApprove()));
//                        }
//
//                        ttlQtyPermintaan = bean.getQtyApprove().multiply(obatEntity.getBijiPerLembar());
//                        ttlAvgHargaPermintaan = (bean.getAverageHargaLembar().divide(new BigDecimal(obatEntity.getBijiPerLembar()),2, RoundingMode.HALF_UP ))
//                                .multiply(new BigDecimal(ttlQtyPermintaan));
//                    }
//
//                    if ("biji".equalsIgnoreCase(bean.getJenisSatuan())){
//
//                        if ("biji".equalsIgnoreCase(bean.getJenisSatuan()) && obatEntity.getBijiPerLembar().compareTo(new BigInteger(String.valueOf(0))) == 1){
//                            if (bean.getQtyApprove().compareTo(obatEntity.getBijiPerLembar()) == 1){
//
//                                if (cons.compareTo(bean.getQtyApprove()) == 1){
//                                    BigInteger bijiToLembar = bean.getQtyApprove().divide(bean.getBijiPerLembar());
//                                    BigInteger modBijiToLembar = bean.getQtyApprove().mod(bean.getBijiPerLembar());
//
//                                    obatEntity.setQtyLembar(obatEntity.getQtyLembar().add(bijiToLembar));
//                                    obatEntity.setQtyBiji(obatEntity.getQtyBiji().add(modBijiToLembar));
//                                } else {
//                                    BigInteger bijiPermintaanToBox = bean.getQtyApprove().divide(cons);
//                                    BigInteger modBijiPermintaanToBox = bean.getQtyApprove().mod(cons);
//                                    BigInteger sisaBijiToLembar = modBijiPermintaanToBox.divide(bean.getBijiPerLembar());
//                                    BigInteger modSisaBijiToLembar = modBijiPermintaanToBox.mod(bean.getBijiPerLembar());
//
//                                    obatEntity.setQtyBox(obatEntity.getQtyBox().add(bijiPermintaanToBox));
//                                    obatEntity.setQtyLembar(obatEntity.getQtyLembar().add(sisaBijiToLembar));
//                                    obatEntity.setQtyBiji(obatEntity.getQtyBiji().add(modSisaBijiToLembar));
//                                }
//
//                            } else {
//                                obatEntity.setQtyBiji(obatEntity.getQtyBiji().add(bean.getQtyApprove()));
//                            }
//                        } else{
//                            obatEntity.setQtyBiji(obatEntity.getQtyBiji().add(bean.getQtyApprove()));
//                        }
//
//                        ttlQtyPermintaan = bean.getQtyApprove();
//                        ttlAvgHargaPermintaan = bean.getAverageHargaBiji().multiply(new BigDecimal(ttlQtyPermintaan));
//                    }
//                }
//
//                BigDecimal ttlHargaBijian = (obatEntity.getAverageHargaBiji().multiply(new BigDecimal(allStockToBiji))).add(ttlAvgHargaPermintaan);
//                BigInteger ttlQty = allStockToBiji.add(ttlQtyPermintaan);
//                BigDecimal newAvgHargaBijian = ttlHargaBijian.divide(new BigDecimal(ttlQty), 2, RoundingMode.HALF_UP);
//
//                if (obatEntity.getLembarPerBox().compareTo(new BigInteger(String.valueOf(0))) == 1){
//                    obatEntity.setAverageHargaBox(newAvgHargaBijian.multiply(new BigDecimal(cons)));
//                    obatEntity.setAverageHargaLembar(newAvgHargaBijian.multiply(new BigDecimal(obatEntity.getBijiPerLembar())));
//                }
//                if (obatEntity.getBijiPerLembar().compareTo(new BigInteger(String.valueOf(0))) == 1){
//                    obatEntity.setAverageHargaBiji(newAvgHargaBijian);
//                }
//
//                obatEntity.setAction("U");
//                obatEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
//                obatEntity.setLastUpdateWho(CommonUtil.userLogin());
//                try {
//                    obatDao.updateAndSave(obatEntity);
//                } catch (HibernateException e){
//                    logger.error("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR.", e);
//                    throw new GeneralBOException("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR." + e.getMessage());
//                }
//            }
//        }

        logger.info("[PermintaanVendorBoImpl.updateAddStockGudang] END <<<");
    }

    private void updateAllNewAverageHargaByObatId(String idObat, BigDecimal avgBox, BigDecimal avgLembar, BigDecimal avgBiji) throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.updateAllNewAverageHargaByObatId] START >>>");

        Obat obat = new Obat();
        obat.setIdObat(idObat);

        List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);

        if (obatEntities.size() > 0){
            for (ImSimrsObatEntity obatEntity : obatEntities){
                obatEntity.setAverageHargaBox(avgBox);
                obatEntity.setAverageHargaLembar(avgLembar);
                obatEntity.setAverageHargaBiji(avgBiji);

                try {
                    obatDao.updateAndSave(obatEntity);
                } catch (HibernateException e){
                    logger.error("[PermintaanVendorBoImpl.updateAllNewAverageHargaByObatId] ERROR.", e);
                    throw new GeneralBOException("[PermintaanVendorBoImpl.updateAllNewAverageHargaByObatId] ERROR." + e.getMessage());
                }
            }
        }

        logger.info("[PermintaanVendorBoImpl.updateAllNewAverageHargaByObatId] END <<<");
    }

    @Override
    public List<TransaksiObatDetail> getNewObatDetail(TransaksiObatDetail bean) throws GeneralBOException {

        List<TransaksiObatDetail> result = new ArrayList<>();

        if (bean != null) {

            Map hsCriteria = new HashMap();
            if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {
                hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            }

            if (bean.getFlagDiterima() != null && !"".equalsIgnoreCase(bean.getFlagDiterima())) {
                hsCriteria.put("flag_diterima", bean.getFlagDiterima());
            }

            List<ImtSimrsTransaksiObatDetailEntity> detailEntityList = new ArrayList<>();

            try {
                detailEntityList = transaksiObatDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PermintaanVendorBoImpl.saveConfirm] ERROR.", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveConfirm] ERROR." + e.getMessage());
            }

            if (!detailEntityList.isEmpty()) {

                TransaksiObatDetail transaksiDetail;
                for (ImtSimrsTransaksiObatDetailEntity entity : detailEntityList) {

                    transaksiDetail = new TransaksiObatDetail();
                    transaksiDetail.setIdTransaksiObatDetail(entity.getIdTransaksiObatDetail());
                    transaksiDetail.setIdApprovalObat(entity.getIdApprovalObat());
                    transaksiDetail.setIdObat(entity.getIdObat());
                    transaksiDetail.setNamaObat(entity.getNamaObatBaru());
                    transaksiDetail.setQty(entity.getQty());
                    transaksiDetail.setQtyApprove(entity.getQtyApprove());
                    transaksiDetail.setQtyBox(entity.getQtyBox());
                    transaksiDetail.setQtyLembar(entity.getQtyLembar());
                    transaksiDetail.setLembarPerBox(entity.getLembarPerBox());
                    transaksiDetail.setBijiPerLembar(entity.getBijiPerLembar());
                    transaksiDetail.setIdPabrik(entity.getIdPabrik());
                    transaksiDetail.setMerek(entity.getMrek());
                    transaksiDetail.setJenisSatuan(entity.getJenisSatuan());

                    if ("box".equalsIgnoreCase(entity.getJenisSatuan())) {
                        transaksiDetail.setHargaPo(entity.getAverageHargaBox());
                    }

                    if ("lembar".equalsIgnoreCase(entity.getJenisSatuan())) {
                        transaksiDetail.setHargaPo(entity.getAverageHargaLembar());
                    }

                    if ("biji".equalsIgnoreCase(entity.getJenisSatuan())) {
                        transaksiDetail.setHargaPo(entity.getAverageHargaBiji());
                    }

                    result.add(transaksiDetail);
                }
            }
        }

        return result;
    }

    private Boolean isAvailNotConfirm(String idApproval) {
        logger.info("[PermintaanVendorBoImpl.isNotAvailNotConfirm] START >>>");

        Boolean check = true;
        try {
            check = permintaanVendorDao.isAvailNotConfirm(idApproval);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.isNotAvailNotConfirm] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.isNotAvailNotConfirm] ERROR." + e.getMessage());
        }
        logger.info("[PermintaanVendorBoImpl.isNotAvailNotConfirm] END <<<");
        return check;
    }

    @Override
    public Integer getLastNoBatch(String idApproval) throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.getLastNoBatch] START >>>");

        Integer noBatch = 0;

        try {
            noBatch = transaksiObatDetailBatchDao.getLastBatch(idApproval);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getLastNoBatch] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getLastNoBatch] ERROR." + e.getMessage());
        }

        logger.info("[PermintaanVendorBoImpl.getLastNoBatch] END <<<");
        return noBatch;
    }

    @Override
    public List<BatchPermintaanObat> getListBatchObatByIdApproval(String idApproval) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getLastNoBatch] START >>>");

        List<BatchPermintaanObat> batchList = new ArrayList<>();

        try{
            batchList = transaksiObatDetailBatchDao.getListBatchByApprovalId(idApproval);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getListBatchObatByIdApproval] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getListBatchObatByIdApproval] ERROR." + e.getMessage());
        }

        logger.info("[PermintaanVendorBoImpl.getLastNoBatch] END <<<");
        return batchList;
    }

    @Override
    public List<TransaksiObatDetail> getListTransByBatchSorted(List<TransaksiObatDetail> obatDetails, Integer noBatch) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getListTransByBatchSorted] START >>>");

        List<TransaksiObatDetail> results = new ArrayList<>();
        for (TransaksiObatDetail obatDetail : obatDetails){

            MtSimrsTransaksiObatDetailBatchEntity batchEntity = getEntityObatBatchByIdTransObat(obatDetail.getIdTransaksiObatDetail(), noBatch, null);

            // to enable or disable input box;
            obatDetail.setIsFullOfQty(compareQtyRequestToQtyBatch(obatDetail.getQty(), obatDetail.getIdTransaksiObatDetail()));
            obatDetail.setSumQtyApprove(getSumQtyApproveOnBatchByIdTransDetail(obatDetail.getIdTransaksiObatDetail()));
            obatDetail.setFlagDiterima(batchEntity.getStatus());
            obatDetail.setNoBatch(noBatch);

            if (batchEntity != null){
                obatDetail.setQtyApprove(batchEntity.getQtyApprove());
            }
            results.add(obatDetail);
        }
        logger.info("[PermintaanVendorBoImpl.getListTransByBatchSorted] END <<<");
        return results;
    }

    private String compareQtyRequestToQtyBatch(BigInteger qty, String idTransObatDetail){
        logger.info("[PermintaanVendorBoImpl.compareQtyRequestToQtyBatch] START >>>");
        BigInteger sum = new BigInteger(String.valueOf(0));

        try {
            sum = transaksiObatDetailBatchDao.getSumQtyApproveOnBatch(idTransObatDetail);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.compareQtyRequestToQtyBatch] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.compareQtyRequestToQtyBatch] ERROR." + e.getMessage());
        }

        String arg = "";
        if (sum != null){
            if (qty.compareTo(sum) == 1){
                arg = "N";
            } else {
                arg = "Y";
            }
        }

        logger.info("[PermintaanVendorBoImpl.compareQtyRequestToQtyBatch] END <<<");
        return arg;
    }

    private BigInteger getSumQtyApproveOnBatchByIdTransDetail(String idTransaksiObatDetail){
        logger.info("[PermintaanVendorBoImpl.getSumQtyApproveOnBatchByIdTransDetail] START >>>");
        BigInteger sum = new BigInteger(String.valueOf(0));

        try {
            sum = transaksiObatDetailBatchDao.getSumQtyApproveOnBatch(idTransaksiObatDetail);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getSumQtyApproveOnBatchByIdTransDetail] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getSumQtyApproveOnBatchByIdTransDetail] ERROR." + e.getMessage());
        }

        logger.info("[PermintaanVendorBoImpl.getSumQtyApproveOnBatchByIdTransDetail] END <<<");
        return sum;
    }

    private List<MtSimrsTransaksiObatDetailBatchEntity> getListEntityBatchObat(TransaksiObatBatch bean) throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.getListEntityBatchObat] START >>>");
        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdTransaksiObatDetail() != null && !"".equalsIgnoreCase(bean.getIdTransaksiObatDetail())){
            hsCriteria.put("id_transaksi_obat_detail", bean.getIdTransaksiObatDetail());
        }
        if (bean.getNoBatch() != null){
            hsCriteria.put("no_batch", bean.getNoBatch());
        }
        if (bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())){
            hsCriteria.put("status", bean.getStatus());
        }
        if (bean.getApproveFlag() != null && !"".equalsIgnoreCase(bean.getApproveFlag())){
            hsCriteria.put("approve_flag", bean.getApproveFlag());
        }

        try {
            batchEntities = transaksiObatDetailBatchDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getListEntityBatchObat] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getListEntityBatchObat] ERROR." + e.getMessage());
        }


        logger.info("[PermintaanVendorBoImpl.getListEntityBatchObat] END <<<");
        return batchEntities;
    }

    private MtSimrsTransaksiObatDetailBatchEntity getEntityObatBatchByIdTransObat(String idTransaksiObatDetail, Integer noBatch, Date expDate){
        logger.info("[PermintaanVendorBoImpl.getEntityObatBatchByIdTransObat] START >>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_transaksi_obat_detail", idTransaksiObatDetail);
        hsCriteria.put("no_batch", noBatch);
        if (expDate != null){
            hsCriteria.put("exp_date", expDate);
        } else {
            hsCriteria.put("order_last_created_date", "Y");
        }

        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = null;
        MtSimrsTransaksiObatDetailBatchEntity batchEntity = new MtSimrsTransaksiObatDetailBatchEntity();

        try {
            batchEntities = transaksiObatDetailBatchDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getEntityObatBatchByIdTransObat] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getEntityObatBatchByIdTransObat] ERROR." + e.getMessage());
        }

        if (batchEntities.size() > 0 && batchEntities != null){
            batchEntity = batchEntities.get(0);
        }

        logger.info("[PermintaanVendorBoImpl.getEntityObatBatchByIdTransObat] END <<<");
        return batchEntity;
    }

    @Override
    public Boolean isNewBatchCheckByNoBatchAndExpDate(String idTransObatDetail, Integer noBatch, Date expDate) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.isNewBatchCheckByNoBatchAndExpDate] START >>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_trans_obat_detail", idTransObatDetail);
        hsCriteria.put("no_batch", noBatch);
        hsCriteria.put("exp_date", expDate);

        List<MtSimrsTransaksiObatDetailBatchEntity> obatDetailBatchEntities = new ArrayList<>();
        try {
            obatDetailBatchEntities = transaksiObatDetailBatchDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.isNewBatchCheckByNoBatchAndExpDate] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.isNewBatchCheckByNoBatchAndExpDate] ERROR." + e.getMessage());
        }

        Boolean isNew = true;
        if (obatDetailBatchEntities.size() > 0){
            isNew = false;
        }

        logger.info("[PermintaanVendorBoImpl.isNewBatchCheckByNoBatchAndExpDate] END <<<");
        return isNew;
    }

    // for get sequence id

    private String nextIdPermintanVendor() {
        logger.info("[PermintaanVendorBoImpl.nextIdPermintanVendor] START >>>");
        String id = "";

        try {
            id = permintaanVendorDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.nextIdPermintanVendor] ERROR when get data. " + e.getMessage());
            throw new GeneralBOException("[PermintaanVendorBoImpl.nextIdPermintanVendor] ERROR when get data. " + e.getMessage());
        }

        logger.info("[PermintaanVendorBoImpl.nextIdPermintanVendor] END <<<");
        return id;
    }

    private String getNextApprovalObatId() throws GeneralBOException {
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getNextApprovalObatId] ERROR when get next seq. ", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getNextApprovalObatId] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextTransaksiObatDetail() throws GeneralBOException {
        String id = "";
        try {
            id = transaksiObatDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getNextTransaksiObatDetail] ERROR when get next seq. ", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getNextTransaksiObatDetail] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextIdTmpObatGejala() throws GeneralBOException {
        String id = "";
        try {
            id = tempObatGejalaDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getNextIdTmpObatGejala] ERROR when get next seq. ", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getNextIdTmpObatGejala] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextIdObat() throws GeneralBOException {
        String id = "";
        try {
            id = obatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getNextIdObat] ERROR when get next seq. ", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getNextIdObat] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextIdBatchObat() throws GeneralBOException {
        String id = "";
        try {
            id = transaksiObatDetailBatchDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getNextIdBatchObat] ERROR when get next seq. ", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getNextIdBatchObat] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getIdNextSeqObat() throws GeneralBOException{
        String id = "";

        try {
            id = obatDao.getNextIdSeqObat();
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.getNextIdBatchObat] ERROR WHEN GET data id seq obat, "+e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getNextIdBatchObat] ERROR WHEN GET data id seq obat, "+e.getMessage());
        }

        return id;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PermintaanVendorBoImpl.logger = logger;
    }

    public void setPermintaanVendorDao(PermintaanVendorDao permintaanVendorDao) {
        this.permintaanVendorDao = permintaanVendorDao;
    }

    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setTransaksiObatDetailDao(TransaksiObatDetailDao transaksiObatDetailDao) {
        this.transaksiObatDetailDao = transaksiObatDetailDao;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setTempObatGejalaDao(TempObatGejalaDao tempObatGejalaDao) {
        this.tempObatGejalaDao = tempObatGejalaDao;
    }

    public void setTransaksiObatDetailBatchDao(TransaksiObatDetailBatchDao transaksiObatDetailBatchDao) {
        this.transaksiObatDetailBatchDao = transaksiObatDetailBatchDao;
    }
}
