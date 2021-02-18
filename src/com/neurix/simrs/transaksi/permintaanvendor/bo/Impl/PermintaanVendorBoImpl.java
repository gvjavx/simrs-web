package com.neurix.simrs.transaksi.permintaanvendor.bo.Impl;

import com.google.gson.Gson;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.hargaterakhir.dao.HargaTerakhirDao;
import com.neurix.simrs.master.hargaterakhir.model.MtSimrsHargaTerakhirEntity;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.obatgejala.dao.ObatGejalaDao;
import com.neurix.simrs.master.obatgejala.model.ImSimrsObatGejalaEntity;
import com.neurix.simrs.master.pabrikobat.model.PabrikObat;
import com.neurix.simrs.master.vendor.dao.VendorDao;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.master.vendor.model.Vendor;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.hargaobat.dao.HargaObatDao;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsObatPoliEntity;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.dao.DocPoDao;
import com.neurix.simrs.transaksi.permintaanvendor.dao.PermintaanVendorDao;
import com.neurix.simrs.transaksi.permintaanvendor.dao.TempObatGejalaDao;
import com.neurix.simrs.transaksi.permintaanvendor.dao.UserVendorDao;
import com.neurix.simrs.transaksi.permintaanvendor.model.*;
import com.neurix.simrs.transaksi.riwayatbarang.dao.RiwayatBarangDao;
import com.neurix.simrs.transaksi.riwayatbarang.dao.TransaksiStokDao;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsRiwayatBarangMasukEntity;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsTransaksiStokEntity;
import com.neurix.simrs.transaksi.riwayatbarang.model.TransaksiStok;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailBatchDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.*;
import io.agora.recording.common.Common;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
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
import java.util.*;

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
    private VendorDao vendorDao;
    private ObatGejalaDao obatGejalaDao;
    private RiwayatBarangDao riwayatBarangDao;
    private TransaksiStokDao transaksiStokDao;
    private BranchDao branchDao;
    private BatasTutupPeriodDao batasTutupPeriodDao;
    private UserVendorDao userVendorDao;
    private DocPoDao docPoDao;
    private HargaObatDao hargaObatDao;
    private HargaTerakhirDao hargaTerakhirDao;

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
                    permintaanVendor.setTipeTransaksi(permintaanVendorEntity.getTipeTransaksi());
                    permintaanVendor.setBranchId(permintaanVendorEntity.getBranchId());

                    if (permintaanVendorEntity.getBranchId() != null){
                        ImBranchesPK branchesPK = new ImBranchesPK();
                        branchesPK.setId(permintaanVendorEntity.getBranchId());

                        Map hsCriteria = new HashMap();
                        hsCriteria.put("branch_id", permintaanVendorEntity.getBranchId());
                        hsCriteria.put("flag", "Y");

                        List<ImBranches> branches = branchDao.getByCriteria(hsCriteria);
                        if (branches != null && branches.size() > 0){
                            ImBranches imBranches = branches.get(0);
                            permintaanVendor.setBranchName(imBranches.getBranchName());
                        }
                    }

                    Vendor vendor = new Vendor();
                    vendor.setIdVendor(permintaanVendorEntity.getIdVendor());
                    ImSimrsVendorEntity vendorEntity = getListEntityVendorObat(vendor);
                    permintaanVendor.setNamaVendor(vendorEntity.getNamaVendor());

                    String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(permintaanVendorEntity.getCreatedDate());

                    permintaanVendor.setStCreatedDate(formatDate);
                    permintaanVendor.setCreatedDate(permintaanVendorEntity.getCreatedDate());
                    permintaanVendor.setCreatedWho(permintaanVendorEntity.getCreatedWho());
                    permintaanVendor.setLastUpdate(permintaanVendorEntity.getLastUpdate());
                    permintaanVendor.setLastUpdateWho(permintaanVendorEntity.getLastUpdateWho());
                    if (permintaanVendorEntity.getTglCair() != null && !"".equalsIgnoreCase(permintaanVendorEntity.getTglCair().toString())) {
                        String tglCair = new SimpleDateFormat("dd-MM-yyyy").format(permintaanVendorEntity.getTglCair());
                        permintaanVendor.setTglCair(tglCair);
                    }

                    if (!"".equalsIgnoreCase(permintaanVendor.getIdApprovalObat())) {

                        // check if all list obat from vendor has cofirmed by staff gudang
                        permintaanVendor.setEnableApprove(isAvailNotConfirm(permintaanVendorEntity.getIdApprovalObat()));

                        ImtSimrsApprovalTransaksiObatEntity approvalEntity = getApprovalTransaksiById(permintaanVendor.getIdApprovalObat());
                        if (approvalEntity != null) {

                            if (permintaanVendor.getEnableApprove()) {
                                permintaanVendor.setKeterangan("Telah Dikonfirmasi");
                            } else {
                                permintaanVendor.setKeterangan("Proses Verifikasi");

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
                                if (obatEntity != null) {
                                    transaksiObatDetail.setNamaObat(obatEntity.getNamaObat());
                                }
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
                                transaksiObatDetail.setQtyAfter(transaksiObatDetailEntity.getQty().subtract(getQtyApprove(transaksiObatDetailEntity.getIdTransaksiObatDetail())));
                                transaksiObatDetail.setLembarPerBox(transaksiObatDetailEntity.getLembarPerBox());
                                transaksiObatDetail.setBijiPerLembar(transaksiObatDetailEntity.getBijiPerLembar());
                                transaksiObatDetail.setAverageHargaBox(transaksiObatDetailEntity.getAverageHargaBox());
                                transaksiObatDetail.setAverageHargaLembar(transaksiObatDetailEntity.getAverageHargaLembar());
                                transaksiObatDetail.setAverageHargaBiji(transaksiObatDetailEntity.getAverageHargaBiji());
                                transaksiObatDetail.setFlagDiterima(transaksiObatDetailEntity.getFlagDiterima());
                                transaksiObatDetail.setJenisSatuan(transaksiObatDetailEntity.getJenisSatuan());
                                transaksiObatDetail.setIdPabrik(obatEntity.getIdPabrik());
                                transaksiObatDetail.setMerek(transaksiObatDetailEntity.getMrek());
                                transaksiObatDetail.setNamaObatBaru(transaksiObatDetailEntity.getNamaObatBaru());

                                if ("box".equalsIgnoreCase(transaksiObatDetailEntity.getJenisSatuan())) {
                                    transaksiObatDetail.setHargaPo(transaksiObatDetailEntity.getAverageHargaBox());
                                }
                                if ("lembar".equalsIgnoreCase(transaksiObatDetailEntity.getJenisSatuan())) {
                                    transaksiObatDetail.setHargaPo(transaksiObatDetailEntity.getAverageHargaLembar());
                                }
                                if ("biji".equalsIgnoreCase(transaksiObatDetailEntity.getJenisSatuan())) {
                                    transaksiObatDetail.setHargaPo(transaksiObatDetailEntity.getAverageHargaBiji());
                                }

                                if("reture".equalsIgnoreCase(permintaanVendorEntity.getTipeTransaksi())){
                                    TransaksiObatDetail detail = new TransaksiObatDetail();
                                    try {
                                        detail = permintaanVendorDao.getDiskonBrutoNetto(transaksiObatDetailEntity.getIdObat());
                                    }catch (HibernateException e){
                                        logger.error("Found Error when "+e.getMessage());
                                    }

                                    if(detail.getIdObat() != null){
                                        transaksiObatDetail.setDiskon(detail.getDiskon());
                                        transaksiObatDetail.setBruto(detail.getBruto());
                                        transaksiObatDetail.setNetto(detail.getNetto());
                                    }
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

    private BigInteger getQtyApprove(String idTrans){
        return transaksiObatDetailBatchDao.getJumlahApprove(idTrans);
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
            if (bean.getTipeTransaksi() != null && !"".equalsIgnoreCase(bean.getTipeTransaksi())) {
                hsCriteria.put("tipe_transaksi", bean.getTipeTransaksi());
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

    public List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
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

        Obat obat = new Obat();

        try {
            obat = obatDao.getLastIdSeqObat(id);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getObatById] ERROR. ", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getObatById] ERROR. ", e);
        }

        if (obat.getIdSeqObat() != null) {
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_seq_obat", obat.getIdSeqObat());
            hsCriteria.put("flag", "Y");
//            hsCriteria.put("asc", "Y");

            try {
                obatEntities = obatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PermintaanVendorBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
            }

            if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
                return obatEntities.get(0);
            }
        }

        logger.info("[TransaksiObatBoImpl.getObatById] END <<<<<<<<<<");
        return null;
    }

    @Override
    public CheckResponse saveListObatPo(PermintaanVendor bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.saveListObatPo] START >>>");
        CheckResponse response = new CheckResponse();

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
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error when insert into approval transaksi " + e.getMessage());
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
            permintaanVendorEntity.setUrlDocPo(bean.getUrlDocPo());
            permintaanVendorEntity.setTglCair(Date.valueOf(bean.getTglCair()));
            permintaanVendorEntity.setTipeTransaksi("request");

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
                    obatDetailEntity.setFlagObatBpjs(obatDetail.getTipeObat());
                    obatDetailEntity.setIdPabrikObat(obatDetail.getIdPabrikObat());

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
                }
            }
        }
        logger.info("[PermintaanVendorBoImpl.saveListObatPo] END <<<");
        return response;
    }

    @Override
    public CheckObatResponse saveUpdateTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] START >>>");

        CheckObatResponse response = new CheckObatResponse();

        ImtSimrsTransaksiObatDetailEntity transaksiObatDetailEntity = transaksiObatDetailDao.getById("idTransaksiObatDetail", bean.getIdTransaksiObatDetail());

        if (bean.getNoBatch() != null && bean.getNoBatch().compareTo(0) == 1) {

            MtSimrsTransaksiObatDetailBatchEntity batchEntity = getEntityObatBatchByIdTransObat(bean.getIdTransaksiObatDetail(), bean.getNoBatch(), bean.getExpDate());

            Boolean isNew = true;
            if (batchEntity.getId() != null) {
                isNew = false;
            }

            if (isNew) {

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
                obatDetailBatchEntity.setFlagObatBpjs(transaksiObatDetailEntity.getFlagObatBpjs());
                obatDetailBatchEntity.setDiskon(bean.getDiskon());
                obatDetailBatchEntity.setBruto(bean.getBruto());
                obatDetailBatchEntity.setNetto(bean.getNetto());

                try {
                    transaksiObatDetailBatchDao.addAndSave(obatDetailBatchEntity);
                    response.setStatus("success");
                    response.setMessage("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Error when " + e.getMessage());
                    logger.error("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when add obat detail batch. " + e.getMessage());
                    throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when add obat detail batch. " + e.getMessage());
                }

            } else {
                batchEntity.setQtyApprove(batchEntity.getQtyApprove().add(bean.getQtyApprove()));
                batchEntity.setAction("U");
                batchEntity.setLastUpdate(bean.getLastUpdate());
                batchEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    transaksiObatDetailBatchDao.updateAndSave(batchEntity);
                    response.setStatus("success");
                    response.setMessage("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Error when " + e.getMessage());
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
            obatDetailBatchEntity.setFlagObatBpjs(transaksiObatDetailEntity.getFlagObatBpjs());
            obatDetailBatchEntity.setDiskon(bean.getDiskon());
            obatDetailBatchEntity.setBruto(bean.getBruto());
            obatDetailBatchEntity.setNetto(bean.getNetto());

            try {
                transaksiObatDetailBatchDao.addAndSave(obatDetailBatchEntity);
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Error when " + e.getMessage());
                logger.error("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when add obat detail batch. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when add obat detail batch. " + e.getMessage());
            }
        }

        if (transaksiObatDetailEntity != null) {

            transaksiObatDetailEntity.setIdPabrik(bean.getIdPabrik());
            transaksiObatDetailEntity.setAction("U");
            transaksiObatDetailEntity.setKeterangan(bean.getKeterangan());

            try {
                transaksiObatDetailDao.updateAndSave(transaksiObatDetailEntity);
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Error when " + e.getMessage());
                logger.error("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when update obat detail. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when update obat detail. " + e.getMessage());
            }
        }
        logger.info("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] END <<<");
        return response;
    }

    @Override
    public CheckResponse saveNewPabrik(TransaksiObatDetail bean, List<String> idJenisObats) throws GeneralBOException {

        CheckResponse response = new CheckResponse();

        if (bean != null) {

            ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();

            Timestamp time = new Timestamp(System.currentTimeMillis());
            String userLogin = CommonUtil.userLogin();

            obatEntity.setIdSeqObat(obatDao.getNextIdSeqObat());
            obatEntity.setIdObat("OBT" + obatDao.getNextId());
            obatEntity.setNamaObat(bean.getNamaObat());
            obatEntity.setQty(new BigInteger(String.valueOf("0")));
            obatEntity.setBranchId(bean.getBranchId());
            obatEntity.setMerk(bean.getMerek());
            obatEntity.setIdPabrik(bean.getIdPabrik());
            obatEntity.setFlag(bean.getFlag());
            obatEntity.setAction(bean.getAction());
            obatEntity.setCreatedDate(time);
            obatEntity.setCreatedWho(userLogin);
            obatEntity.setLastUpdate(time);
            obatEntity.setLastUpdateWho(userLogin);
            obatEntity.setQtyBox(new BigInteger(String.valueOf("0")));
            obatEntity.setLembarPerBox(bean.getLembarPerBox());
            obatEntity.setQtyLembar(new BigInteger(String.valueOf("0")));
            obatEntity.setBijiPerLembar(bean.getBijiPerLembar());
            obatEntity.setQtyBiji(new BigInteger(String.valueOf("0")));
            obatEntity.setAverageHargaBox(new BigDecimal(String.valueOf("0")));
            obatEntity.setAverageHargaLembar(new BigDecimal(String.valueOf("0")));
            obatEntity.setAverageHargaBiji(new BigDecimal(String.valueOf("0")));
            obatEntity.setMinStok(bean.getMinStok());
            obatEntity.setHargaTerakhir(new BigDecimal(String.valueOf(0)));

            try {
                obatDao.addAndSave(obatEntity);
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error " + e.getMessage());
                logger.error("[ObatBoImpl.saveAdd] error when add data obat " + e.getMessage());
                throw new GeneralBOException("[ObatBoImpl.saveAdd] error when add data obat " + e.getMessage());
            }

            if (!idJenisObats.isEmpty() && idJenisObats.size() > 0) {
                for (String idJenisObat : idJenisObats) {
                    ImSimrsObatGejalaEntity obatGejalaEntity = new ImSimrsObatGejalaEntity();

                    obatGejalaEntity.setIdObatGejala("OGJ" + obatGejalaDao.getNextId());
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
                        response.setMessage("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error " + e.getMessage());
                        logger.error("[ObatBoImpl.saveAdd] error when insert new obat gejala " + e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.saveAdd] error when insert new obat gejala " + e.getMessage());
                    }
                }
            }

            ImtSimrsTransaksiObatDetailEntity entityList = new ImtSimrsTransaksiObatDetailEntity();

            try {
                entityList = transaksiObatDetailDao.getById("idTransaksiObatDetail", bean.getIdTransaksiObatDetail());
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error " + e.getMessage());
                logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
            }

            if (entityList != null) {

                entityList.setFlagDiterima("R");
                entityList.setIdPabrik(bean.getIdPabrik());
                entityList.setLastUpdate(bean.getLastUpdate());
                entityList.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    transaksiObatDetailDao.updateAndSave(entityList);
                    response.setStatus("success");
                    response.setMessage("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Found Error " + e.getMessage());
                    logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                    throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                }

                ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                obatDetailEntity.setIdTransaksiObatDetail("ODT" + getNextTransaksiObatDetail());
                obatDetailEntity.setIdApprovalObat(bean.getIdApprovalObat());
                obatDetailEntity.setQtyBox(bean.getQtyBox());
                obatDetailEntity.setIdObat(obatEntity.getIdObat());
                obatDetailEntity.setNamaObatBaru(obatEntity.getNamaObat());
                obatDetailEntity.setLembarPerBox(obatEntity.getLembarPerBox());
                obatDetailEntity.setBijiPerLembar(obatEntity.getBijiPerLembar());
                obatDetailEntity.setQty(bean.getQty());
                obatDetailEntity.setQtyApprove(obatEntity.getQty());
                obatDetailEntity.setFlag(bean.getFlag());
                obatDetailEntity.setAction(bean.getAction());
                obatDetailEntity.setCreatedDate(bean.getCreatedDate());
                obatDetailEntity.setCreatedWho(bean.getCreatedWho());
                obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                obatDetailEntity.setJenisSatuan(bean.getJenisSatuan());
                obatDetailEntity.setKeterangan("Permintaan PO");
                obatDetailEntity.setIdPabrik(bean.getIdPabrik());
                obatDetailEntity.setMrek(bean.getMerek());
                obatDetailEntity.setFlagObatBpjs(entityList.getFlagObatBpjs());

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
                    response.setStatus("success");
                    response.setMessage("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Found Error " + e.getMessage());
                    logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                    throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. " + e.getMessage());
                }
            }
        }

        return response;
    }

    public CheckObatResponse saveConfirm(PermintaanVendor bean, List<TransaksiObatDetail> listObat, List<TransaksiObatDetail> listObatNew) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.saveConfirm] START >>>");
        CheckObatResponse response = new CheckObatResponse();
//         update to master obat k/k j
        if (listObat != null && listObat.size() > 0) {
            for (TransaksiObatDetail obatDetail : listObat) {
                if ("Y".equalsIgnoreCase(obatDetail.getFlagDiterima())) {

                    TransaksiObatBatch obatBatch = new TransaksiObatBatch();
                    obatBatch.setIdTransaksiObatDetail(obatDetail.getIdTransaksiObatDetail());
                    obatBatch.setNoBatch(obatDetail.getNoBatch());

                    List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchObat(obatBatch);

                    if (batchEntities.size() > 0) {
                        for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntities) {

                            String idBarang = "";
                            if ("Y".equalsIgnoreCase(batchEntity.getStatus())) {

                                java.util.Date now = new java.util.Date();
                                SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");

                                String seq = getIdNextSeqObat();
                                idBarang = f.format(now) + seq;

                                obatDetail.setIdSeqObat(seq);
                                obatDetail.setIdBarang(idBarang);
                                obatDetail.setQtyApprove(batchEntity.getQtyApprove());
                                obatDetail.setExpDate(batchEntity.getExpiredDate());
                                obatDetail.setBranchId(bean.getBranchId());
                                obatDetail.setTipeObat(batchEntity.getFlagObatBpjs());
                                obatDetail.setNetto(batchEntity.getNetto());
                                obatDetail.setIdVendor(bean.getIdVendor());
                                obatDetail.setIdPelayanan(bean.getIdPelayanan());
                                obatDetail.setIdPabrikObat(obatDetail.getIdPabrikObat());
                                //update stock and new harga rata-rata
                                updateAddStockGudang(obatDetail);
                            }

                            if (!"".equalsIgnoreCase(idBarang)) {
                                batchEntity.setIdBarang(idBarang);
                            }

                            batchEntity.setApproveFlag("Y");
                            batchEntity.setAction("U");
                            batchEntity.setLastUpdate(bean.getLastUpdate());
                            batchEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            batchEntity.setNoFaktur(bean.getNoFaktur());
                            batchEntity.setTanggalFaktur(bean.getTanggalFaktur());
                            batchEntity.setNoInvoice(bean.getNoInvoice());
                            batchEntity.setNoDo(bean.getNoDo());
                            batchEntity.setUrlDoc(bean.getUrlDoc());
                            batchEntity.setTglDo(bean.getTglDo());
                            batchEntity.setTglInvoice(bean.getTglInvoice());

                            try {
                                transaksiObatDetailBatchDao.updateAndSave(batchEntity);
                                response.setStatus("success");
                                response.setMessage("Berhasil");
                            } catch (HibernateException e) {
                                response.setStatus("error");
                                response.setMessage("Found Error " + e.getMessage());
                                logger.error("[PermintaanVendorBoImpl.saveConfirm] ERROR.", e);
                                throw new GeneralBOException("[PermintaanVendorBoImpl.saveConfirm] ERROR." + e.getMessage());
                            }
                        }
                    }
                }
            }
        }

        // insert new to master obat
//        if (listObatNew != null && listObatNew.size() > 0) {
//            for (TransaksiObatDetail obatDetail : listObatNew) {
//                if ("R".equalsIgnoreCase(obatDetail.getFlagDiterima())) {
//                    ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
//
//                    obatEntity.setIdObat("OBT" + getNextIdObat());
//                    obatEntity.setNamaObat(obatDetail.getNamaObat());
//                    obatEntity.setLembarPerBox(obatDetail.getLembarPerBox());
//                    obatEntity.setBijiPerLembar(obatDetail.getBijiPerLembar());
//                    obatEntity.setBranchId(bean.getBranchId());
//                    obatEntity.setFlag("Y");
//                    obatEntity.setAction("U");
//                    obatEntity.setCreatedDate(bean.getLastUpdate());
//                    obatEntity.setLastUpdate(bean.getLastUpdate());
//                    obatEntity.setCreatedWho(bean.getLastUpdateWho());
//                    obatEntity.setLastUpdateWho(bean.getLastUpdateWho());
//
//                    BigInteger nullBigInt = new BigInteger(String.valueOf(0));
//                    obatEntity.setAverageHargaBox(new BigDecimal(nullBigInt));
//                    obatEntity.setAverageHargaLembar(new BigDecimal(nullBigInt));
//                    obatEntity.setAverageHargaBiji(new BigDecimal(nullBigInt));
//
//                    if ("box".equalsIgnoreCase(obatDetail.getJenisSatuan())) {
//                        obatEntity.setAverageHargaBox(obatDetail.getAverageHargaBox());
//                        obatEntity.setQtyBox(obatDetail.getQtyApprove());
//                    }
//                    if ("lembar".equalsIgnoreCase(obatDetail.getJenisSatuan())) {
//                        obatEntity.setAverageHargaLembar(obatDetail.getAverageHargaLembar());
//                        obatEntity.setQtyLembar(obatDetail.getQtyApprove());
//                    }
//                    if ("biji".equalsIgnoreCase(obatDetail.getJenisSatuan())) {
//                        obatEntity.setAverageHargaBiji(obatDetail.getAverageHargaBiji());
//                        obatEntity.setQtyBiji(obatDetail.getQtyApprove());
//                    }
//
//                    try {
//                        obatDao.addAndSave(obatEntity);
//                    } catch (HibernateException e) {
//                        logger.error("[PermintaanVendorBoImpl.saveConfirm] ERROR.", e);
//                        throw new GeneralBOException("[PermintaanVendorBoImpl.saveConfirm] ERROR." + e.getMessage());
//                    }
//                }
//            }
//        }

        logger.info("[PermintaanVendorBoImpl.saveConfirm] END <<<");
        return response;
    }

    private List<ImSimrsObatEntity> getListEntityObat(Obat bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getListEntityObat] START >>>");

        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean.getIdSeqObat() != null && !"".equalsIgnoreCase(bean.getIdSeqObat())) {
            hsCriteria.put("id_seq_obat", bean.getIdSeqObat());
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getExpiredDate() != null) {
            hsCriteria.put("exp_date", bean.getExpiredDate());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getListEntityObat] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getListEntityObat] ERROR." + e.getMessage());
        }

        logger.info("[PermintaanVendorBoImpl.getListEntityObat] END <<<");
        return obatEntities;
    }

    private void updateAddStockGudang(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.updateAddStockGudang] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String branchId;

        if (bean.getBranchId() != null) {
            branchId = bean.getBranchId();
        } else branchId = CommonUtil.userBranchLogin();
//        String userLogin = CommonUtil.userLogin();

        Obat sumObat = new Obat();
        try {
            sumObat = obatDao.getSumStockObatGudangById(bean.getIdObat(), "", branchId);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR." + e.getMessage());
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

//        java.util.Date now = new java.util.Date();
//        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//        f.format(now);
//
//        String seq = getIdNextSeqObat();

        ImSimrsObatEntity newObatEntity = new ImSimrsObatEntity();
        newObatEntity.setIdSeqObat(bean.getIdSeqObat());
        newObatEntity.setIdBarang(bean.getIdBarang());
        newObatEntity.setIdObat(bean.getIdObat());
        newObatEntity.setNamaObat(bean.getNamaObat());
        newObatEntity.setIdPabrik(bean.getIdPabrik());
        newObatEntity.setExpiredDate(bean.getExpDate());
        newObatEntity.setLembarPerBox(obatEntity.getLembarPerBox());
        newObatEntity.setBijiPerLembar(obatEntity.getBijiPerLembar());
        newObatEntity.setMerk(obatEntity.getMerk());
        newObatEntity.setMinStok(obatEntity.getMinStok());

        BigInteger qtyBox = new BigInteger(String.valueOf(0));
        BigInteger qtyLembar = new BigInteger(String.valueOf(0));
        BigInteger qtyBiji = new BigInteger(String.valueOf(0));

        if ("box".equalsIgnoreCase(bean.getJenisSatuan())) {
            qtyBox = bean.getQtyApprove();

            ttlQtyPermintaan = bean.getQtyApprove().multiply(cons);
            ttlAvgHargaPermintaan = bean.getNetto().divide(new BigDecimal(ttlQtyPermintaan), 2, RoundingMode.HALF_UP);
            newObatEntity.setHargaTerakhir(bean.getNetto().divide(new BigDecimal(ttlQtyPermintaan), 2, RoundingMode.HALF_UP));
        }
        if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())) {
            qtyLembar = bean.getQtyApprove();

            ttlQtyPermintaan = bean.getQtyApprove().multiply(obatEntity.getBijiPerLembar());
            ttlAvgHargaPermintaan = bean.getNetto().divide(new BigDecimal(ttlQtyPermintaan), 2, RoundingMode.HALF_UP);
            newObatEntity.setHargaTerakhir(bean.getNetto().divide(new BigDecimal(ttlQtyPermintaan), 2, RoundingMode.HALF_UP));
        }
        if ("biji".equalsIgnoreCase(bean.getJenisSatuan())) {
            qtyBiji = bean.getQtyApprove();

            ttlQtyPermintaan = bean.getQtyApprove();
            ttlAvgHargaPermintaan = bean.getNetto().divide(new BigDecimal(ttlQtyPermintaan), 2, RoundingMode.HALF_UP);
            newObatEntity.setHargaTerakhir(bean.getNetto());
        }

        BigDecimal ttlStockInBijian = new BigDecimal(0);
        if (obatEntity.getAverageHargaBiji().compareTo(new BigDecimal(0)) == 1 && allStockToBiji.compareTo(new BigInteger(String.valueOf(0))) == 0) {
            ttlStockInBijian = obatEntity.getAverageHargaBiji();
        } else {
            ttlStockInBijian = obatEntity.getAverageHargaBiji().multiply(new BigDecimal(allStockToBiji));
        }

        ttlAvgHargaPermintaan = ttlAvgHargaPermintaan.multiply(new BigDecimal(ttlQtyPermintaan));

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

        newObatEntity.setQtyBox(qtyBox);
        newObatEntity.setQtyLembar(qtyLembar);
        newObatEntity.setQtyBiji(qtyBiji);

        newObatEntity.setFlag("Y");
        newObatEntity.setAction("C");
        newObatEntity.setCreatedDate(time);
        newObatEntity.setCreatedWho(bean.getCreatedWho());
        newObatEntity.setLastUpdate(time);
        newObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
        newObatEntity.setBranchId(branchId);
        newObatEntity.setFlagBpjs(bean.getTipeObat());
        newObatEntity.setFlagKronis(obatEntity.getFlagKronis());
        newObatEntity.setIdPabrikObat(bean.getIdPabrikObat());

        try {
            obatDao.addAndSave(newObatEntity);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.updateAddStockGudang] ERROR." + e.getMessage());
        }

        updateAllNewAverageHargaByObatId(bean.getIdObat(), newObatEntity.getAverageHargaBox(), newObatEntity.getAverageHargaLembar(), newObatEntity.getAverageHargaBiji(), bean.getBranchId());
        saveTransaksiStok(newObatEntity, bean.getIdVendor(), bean.getIdPelayanan());

        // Sigit 2020-12-08, update harga terakhir, Start
        HargaObat hargaObat = new HargaObat();
        hargaObat.setIdObat(newObatEntity.getIdObat());
        hargaObat.setBranchId(newObatEntity.getBranchId());
        hargaObat.setHargaBeli(newObatEntity.getHargaTerakhir());
        hargaObat.setHargaRata(newObatEntity.getAverageHargaBiji());
        hargaObat.setLastUpdate(newObatEntity.getLastUpdate());
        hargaObat.setLastUpdateWho(newObatEntity.getLastUpdateWho());

        updateHargaBeliHargaObat(hargaObat);
        // END


        logger.info("[PermintaanVendorBoImpl.updateAddStockGudang] END <<<");
    }

    private void updateHargaBeliHargaObat(HargaObat hargaObat){
        logger.info("[PermintaanVendorBoImpl.updateHargaBeliHargaObat] Start >>>");

        if (hargaObat != null){

            Map hsCriteria = new HashMap();
            hsCriteria.put("branch_id", hargaObat.getBranchId());
            hsCriteria.put("id_obat", hargaObat.getIdObat());

            List<MtSimrsHargaTerakhirEntity> hargaTerakhirEntities = new ArrayList<>();

            try {
                hargaTerakhirEntities = hargaTerakhirDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.updateHargaBeliHargaObat] ERROR.", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.updateHargaBeliHargaObat] ERROR." + e.getMessage());
            }

            if (hargaTerakhirEntities.size() > 0){

                // update jika harga terakhir beli lebih besar dari pada harga terakhir pada table
                MtSimrsHargaTerakhirEntity hargaTerakhirEntity = hargaTerakhirEntities.get(0);
                if (hargaObat.getHargaBeli().compareTo(hargaTerakhirEntity.getHargaTerakhir()) == 1){

                    hargaTerakhirEntity.setHargaTerakhir(hargaObat.getHargaBeli());
                    hargaTerakhirEntity.setAction("U");
                    hargaTerakhirEntity.setLastUpdate(hargaObat.getLastUpdate());
                    hargaTerakhirEntity.setLastUpdateWho(hargaObat.getLastUpdateWho());

                    try {
                        hargaTerakhirDao.updateAndSave(hargaTerakhirEntity);
                    } catch (HibernateException e){
                        logger.error("[PermintaanVendorBoImpl.updateHargaBeliHargaObat] ERROR.", e);
                        throw new GeneralBOException("[PermintaanVendorBoImpl.updateHargaBeliHargaObat] ERROR." + e.getMessage());
                    }
                }
            } else {

                String idseq = hargaTerakhirDao.getNextId();
                MtSimrsHargaTerakhirEntity hargaTerakhirEntity = new MtSimrsHargaTerakhirEntity();
                hargaTerakhirEntity.setId(idseq);
                hargaTerakhirEntity.setIdObat(hargaObat.getIdObat());
                hargaTerakhirEntity.setBranchId(hargaObat.getBranchId());
                hargaTerakhirEntity.setHargaTerakhir(hargaObat.getHargaBeli());
                hargaTerakhirEntity.setFlag("Y");
                hargaTerakhirEntity.setAction("C");
                hargaTerakhirEntity.setCreatedDate(hargaObat.getLastUpdate());
                hargaTerakhirEntity.setCreatedWho(hargaObat.getLastUpdateWho());
                hargaTerakhirEntity.setLastUpdate(hargaObat.getLastUpdate());
                hargaTerakhirEntity.setLastUpdateWho(hargaObat.getLastUpdateWho());

                try {
                    hargaTerakhirDao.addAndSave(hargaTerakhirEntity);
                } catch (HibernateException e){
                    logger.error("[PermintaanVendorBoImpl.updateHargaBeliHargaObat] ERROR.", e);
                    throw new GeneralBOException("[PermintaanVendorBoImpl.updateHargaBeliHargaObat] ERROR." + e.getMessage());
                }
            }
        }

        logger.info("[PermintaanVendorBoImpl.updateAddStockGudang] End <<<");
    }

    private TransaksiStok getSumSaldoBulanLaluStok(List<ItSimrsTransaksiStokEntity> transaksiStokEntities){

        TransaksiStok transaksiStok = new TransaksiStok();
        BigInteger qtySaldo = new BigInteger(String.valueOf(0));
        BigDecimal totalSaldo = new BigDecimal(0);
        BigDecimal subTotalSaldo = new BigDecimal(0);
        int n = 0;
        for (ItSimrsTransaksiStokEntity stokEntity : transaksiStokEntities){
            if (n == 0){

                stokEntity.setQtyLalu(stokEntity.getQtyLalu() == null ? new BigInteger(String.valueOf(0)) : stokEntity.getQtyLalu());
                stokEntity.setTotalLalu(stokEntity.getTotalLalu() == null ? new BigDecimal(0) : stokEntity.getTotalLalu());
                stokEntity.setSubTotalLalu(stokEntity.getSubTotalLalu() == null ? new BigDecimal(0) : stokEntity.getSubTotalLalu());

                if ("D".equalsIgnoreCase(stokEntity.getTipe())){
                    // qty saldo qty masuk + qty bulan lalu
                    qtySaldo = stokEntity.getQty().add(stokEntity.getQtyLalu());
                    // total saldo = sub total + sub total lalu / qty saldo
                    totalSaldo = stokEntity.getSubTotal().add(stokEntity.getSubTotalLalu()).divide(new BigDecimal(qtySaldo),2, BigDecimal.ROUND_HALF_UP);
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

                if ("D".equalsIgnoreCase(stokEntity.getTipe())){

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

                        trans.setQtyLalu(stok.getQtyLalu());
                        trans.setTotalLalu(stok.getTotalLalu());
                        trans.setSubTotalLalu(stok.getSubTotalLalu());
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
                        trans.setQty(stok.getQty());
                        trans.setTotal(stok.getTotal());
                        trans.setSubTotal(stok.getSubTotal());

//                        trans.setQtyKredit(nolB);
//                        trans.setTotalKredit(nol);
//                        trans.setSubTotalKredit(nol);

                        // qty saldo = qty masuk + qty lalu;
                        trans.setQtySaldo(minStok.getQtyLalu().add(stok.getQty()));

                        // total saldo = sub total lalu + sub total / qty saldo
                        trans.setTotalSaldo(minStok.getSubTotalLalu().add(stok.getSubTotal()).divide(new BigDecimal(trans.getQtySaldo()), 2, BigDecimal.ROUND_HALF_UP));

                        // sub total saldo = total saldo * qty saldo
                        trans.setSubTotalSaldo(trans.getTotal().multiply(new BigDecimal(trans.getQtySaldo())));
                    } else {

//                        trans.setQty(nolB);
//                        trans.setTotal(nol);
//                        trans.setSubTotal(nol);

                        trans.setQtyKredit(stok.getQty());
                        trans.setTotalKredit(stok.getTotal());
                        trans.setSubTotalKredit(stok.getSubTotal());

                        // qty saldo = qty bulan lalu - qty masuk
                        trans.setQtySaldo(minStok.getQtyLalu().subtract(stok.getQty()));

                        // total saldo = total lalu
                        trans.setTotalSaldo(stok.getTotalLalu());

                        // sub total saldo = total saldo * qty saldo
                        trans.setSubTotalSaldo(trans.getTotal().multiply(new BigDecimal(trans.getQtySaldo())));
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
                        trans.setQty(stok.getQty());
                        trans.setTotal(stok.getTotal());
                        trans.setSubTotal(stok.getSubTotal());

//                        trans.setQtyKredit(nolB);
//                        trans.setTotalKredit(nol);
//                        trans.setSubTotalKredit(nol);

                        // qty saldo = qty saldo lalu + qty
                        trans.setQtySaldo(minStok.getQtySaldo().add(stok.getQty()));

                        // total saldo = sub total saldo lalu + sub total / qty saldo
                        trans.setTotalSaldo(minStok.getSubTotalSaldo().add(stok.getSubTotal()).divide(new BigDecimal(trans.getQtySaldo()), 2, BigDecimal.ROUND_HALF_UP));

                        // sub total saldo = sub total saldo
                        trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                    } else {

//                        trans.setQty(nolB);
//                        trans.setTotal(nol);
//                        trans.setSubTotal(nol);

                        trans.setQtyKredit(stok.getQty());
                        trans.setTotalKredit(stok.getTotal());
                        trans.setSubTotalKredit(stok.getSubTotal());

                        // qty saldo = qty saldo - qty
                        trans.setQtySaldo(minStok.getQtySaldo().subtract(stok.getQty()));

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

    private void saveTransaksiStok(ImSimrsObatEntity obatEntity, String idVendor, String idPelayanan) throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.saveToRiwayatBarangMasuk] START >>>");

        if (obatEntity != null){

            Date date = new Date(System.currentTimeMillis());
            String tahun = CommonUtil.getDateParted(date, "YEAR");
            String bulan = CommonUtil.getDateParted(date, "MONTH");

            boolean flagTutup = false;
            List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriod = batasTutupPeriodDao.getBatasPeriodeDitutup(obatEntity.getBranchId(), bulan, tahun);
            if (batasTutupPeriod.size() > 0){
                // jika sudah ditutup bulan ini
                flagTutup = true;
            }

            TransaksiStok saldoBulanLalu = new TransaksiStok();
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
                hsCriteria.put("branch_id", obatEntity.getBranchId());
                hsCriteria.put("id_barang", obatEntity.getIdObat());
                hsCriteria.put("bulan", bulanDepan);
                hsCriteria.put("tahun", tahunDepan);
                hsCriteria.put("id_pelayanan", idPelayanan);

                List<ItSimrsTransaksiStokEntity> transaksiStokEntities = transaksiStokDao.getByCriteria(hsCriteria);
                if (transaksiStokEntities.size() == 0){


                    // jika sudah ditutup bulan ini
                    // maka hitung saldo bulan ini sebagai saldo bulan lalu
                    if (flagTutup){
                        List<TransaksiStok> saldoBulanLaluList = getListTransaksiObat(idPelayanan, Integer.valueOf(tahun), Integer.valueOf(bulan), obatEntity.getIdObat());
                        if (saldoBulanLaluList.size() > 0){
                            saldoBulanLalu = saldoBulanLaluList.get(saldoBulanLaluList.size() -1);
                        }
                    }
                }
            }

            ImBranchesPK branchesPK = new ImBranchesPK();
            branchesPK.setId(obatEntity.getBranchId());

            ImBranches branches = branchDao.getById("primaryKey", branchesPK);
            String branchName = "";
            if (branches != null){
                branchName = branches.getBranchName();
            }

            String vendorName = "";
            if (idVendor != null && !"".equalsIgnoreCase(idVendor)){
                ImSimrsVendorEntity vendorEntity = vendorDao.getById("idVendor", idVendor);
                if (vendorEntity != null){
                    vendorName = vendorEntity.getNamaVendor();
                }
            }

            BigInteger cons = obatEntity.getBijiPerLembar().multiply(obatEntity.getLembarPerBox());
            BigInteger boxToBiji = obatEntity.getQtyBox().multiply(cons);
            BigInteger lembarToBiji = obatEntity.getQtyLembar().multiply(obatEntity.getBijiPerLembar());
            BigInteger qty = obatEntity.getQtyBiji().add(lembarToBiji).add(boxToBiji);


//            BigDecimal hargaBarang = obatEntity.getHargaTerakhir().divide(new BigDecimal(cons) ,2, RoundingMode.HALF_UP);
            BigDecimal hargaBarang = obatEntity.getAverageHargaBiji();

            java.util.Date now = new java.util.Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
            String seq = transaksiStokDao.getNextSeq();
            String idBarangMasuk = "RB"+ obatEntity.getBranchId() + f.format(now) + seq;

            ItSimrsTransaksiStokEntity transaksiStokEntity = new ItSimrsTransaksiStokEntity();
            transaksiStokEntity.setIdTransaksi(idBarangMasuk);
            transaksiStokEntity.setIdObat(obatEntity.getIdObat());
            transaksiStokEntity.setKeterangan("Barang Masuk Pada Gudang Farmasi "+ branchName + ". Nama Barang " + obatEntity.getNamaObat() + ". Dari Vendor " + vendorName);
            transaksiStokEntity.setTipe("D");
            transaksiStokEntity.setBranchId(obatEntity.getBranchId());
            transaksiStokEntity.setQty(qty);
            transaksiStokEntity.setTotal(hargaBarang);
            transaksiStokEntity.setSubTotal(hargaBarang.multiply(new BigDecimal(qty)));


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
                transaksiStokEntity.setRegisteredDate(Date.valueOf(stDate));
            } else {
                transaksiStokEntity.setRegisteredDate(new Date(obatEntity.getLastUpdate().getTime()));
            }
            transaksiStokEntity.setCreatedDate(obatEntity.getLastUpdate());
            transaksiStokEntity.setCreatedWho(obatEntity.getLastUpdateWho());
            transaksiStokEntity.setLastUpdate(obatEntity.getLastUpdate());
            transaksiStokEntity.setLastUpdateWho(obatEntity.getLastUpdateWho());
            transaksiStokEntity.setIdVendor(idVendor);
            transaksiStokEntity.setIdBarang(obatEntity.getIdBarang());
            transaksiStokEntity.setIdPelayanan(idPelayanan);
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
                logger.error("[PermintaanVendorBoImpl.saveToRiwayatBarangMasuk] ERROR.", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveToRiwayatBarangMasuk] ERROR." + e.getMessage());
            }
        }

        logger.info("[PermintaanVendorBoImpl.saveToRiwayatBarangMasuk] END <<<");
    }

    @Override
    public void updateAllNewAverageHargaByObatId(String idObat, BigDecimal avgBox, BigDecimal avgLembar, BigDecimal avgBiji, String branchId) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.updateAllNewAverageHargaByObatId] START >>>");

        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setBranchId(branchId);

        List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);

        if (obatEntities.size() > 0) {
            for (ImSimrsObatEntity obatEntity : obatEntities) {
                obatEntity.setAverageHargaBox(avgBox);
                obatEntity.setAverageHargaLembar(avgLembar);
                obatEntity.setAverageHargaBiji(avgBiji);

                try {
                    obatDao.updateAndSave(obatEntity);
                } catch (HibernateException e) {
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
    public Integer getLastNoBatch(String idApproval) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getLastNoBatch] START >>>");

        Integer noBatch = 0;

        try {
            noBatch = transaksiObatDetailBatchDao.getLastBatch(idApproval);
        } catch (HibernateException e) {
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

        try {
            batchList = transaksiObatDetailBatchDao.getListBatchByApprovalId(idApproval);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getListBatchObatByIdApproval] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getListBatchObatByIdApproval] ERROR." + e.getMessage());
        }

        for (BatchPermintaanObat permintaanObat : batchList) {

            Boolean allowApprove = false;

            try {
                allowApprove = transaksiObatDetailBatchDao.checkBatchApproveFlagByIdApproval(idApproval, permintaanObat.getNoBatch());
            } catch (HibernateException e) {
                logger.error("[PermintaanVendorBoImpl.getListBatchObatByIdApproval] ERROR.", e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.getListBatchObatByIdApproval] ERROR." + e.getMessage());
            }

            if (allowApprove) {
                permintaanObat.setIsApprove("Y");
            } else {
                permintaanObat.setIsApprove("N");
            }
        }

        logger.info("[PermintaanVendorBoImpl.getLastNoBatch] END <<<");
        return batchList;
    }

    @Override
    public List<TransaksiObatDetail> getListTransByBatchSorted(List<TransaksiObatDetail> obatDetails, Integer noBatch, String isApproval) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getListTransByBatchSorted] START >>>");

        List<TransaksiObatDetail> results = new ArrayList<>();
        List<TransaksiObatDetail> resultsNew = new ArrayList<>();
        for (TransaksiObatDetail obatDetail : obatDetails) {

            MtSimrsTransaksiObatDetailBatchEntity batchEntity = getEntityObatBatchByIdTransObat(obatDetail.getIdTransaksiObatDetail(), noBatch, null);

            // to enable or disable input box;
            obatDetail.setIsFullOfQty(compareQtyRequestToQtyBatch(obatDetail.getQty(), obatDetail.getIdTransaksiObatDetail()));
            obatDetail.setSumQtyApprove(getSumQtyApproveOnBatchByIdTransDetail(obatDetail.getIdTransaksiObatDetail()));
            obatDetail.setFlagDiterima(batchEntity.getStatus());
            obatDetail.setNoBatch(noBatch);

            if (batchEntity != null) {
                obatDetail.setQtyApprove(batchEntity.getQtyApprove());

                // sorting for approval
                if ("Y".equalsIgnoreCase(isApproval)) {
                    if (noBatch.equals(batchEntity.getNoBatch())) {
                        resultsNew.add(obatDetail);
                    }
                }
            }


            results.add(obatDetail);
        }

        logger.info("[PermintaanVendorBoImpl.getListTransByBatchSorted] END <<<");
        if ("Y".equalsIgnoreCase(isApproval)) {
            return resultsNew;
        } else {
            return results;
        }
    }

    private String compareQtyRequestToQtyBatch(BigInteger qty, String idTransObatDetail) {
        logger.info("[PermintaanVendorBoImpl.compareQtyRequestToQtyBatch] START >>>");
        BigInteger sum = new BigInteger(String.valueOf(0));

        try {
            sum = transaksiObatDetailBatchDao.getSumQtyApproveOnBatch(idTransObatDetail);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.compareQtyRequestToQtyBatch] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.compareQtyRequestToQtyBatch] ERROR." + e.getMessage());
        }

        String arg = "";
        if (sum != null) {
            if (qty.compareTo(sum) == 1) {
                arg = "N";
            } else {
                arg = "Y";
            }
        }

        logger.info("[PermintaanVendorBoImpl.compareQtyRequestToQtyBatch] END <<<");
        return arg;
    }

    private BigInteger getSumQtyApproveOnBatchByIdTransDetail(String idTransaksiObatDetail) {
        logger.info("[PermintaanVendorBoImpl.getSumQtyApproveOnBatchByIdTransDetail] START >>>");
        BigInteger sum = new BigInteger(String.valueOf(0));

        try {
            sum = transaksiObatDetailBatchDao.getSumQtyApproveOnBatch(idTransaksiObatDetail);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getSumQtyApproveOnBatchByIdTransDetail] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getSumQtyApproveOnBatchByIdTransDetail] ERROR." + e.getMessage());
        }

        logger.info("[PermintaanVendorBoImpl.getSumQtyApproveOnBatchByIdTransDetail] END <<<");
        return sum;
    }

    private List<MtSimrsTransaksiObatDetailBatchEntity> getListEntityBatchObat(TransaksiObatBatch bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getListEntityBatchObat] START >>>");
        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdTransaksiObatDetail() != null && !"".equalsIgnoreCase(bean.getIdTransaksiObatDetail())) {
            hsCriteria.put("id_transaksi_obat_detail", bean.getIdTransaksiObatDetail());
        }
        if (bean.getNoBatch() != null) {
            hsCriteria.put("no_batch", bean.getNoBatch());
        }
        if (bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())) {
            hsCriteria.put("status", bean.getStatus());
        }
        if (bean.getApproveFlag() != null && !"".equalsIgnoreCase(bean.getApproveFlag())) {
            hsCriteria.put("approve_flag", bean.getApproveFlag());
        }
        if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
            hsCriteria.put("jenis", bean.getJenis());
        }

        try {
            batchEntities = transaksiObatDetailBatchDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getListEntityBatchObat] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getListEntityBatchObat] ERROR." + e.getMessage());
        }


        logger.info("[PermintaanVendorBoImpl.getListEntityBatchObat] END <<<");
        return batchEntities;
    }

    private MtSimrsTransaksiObatDetailBatchEntity getEntityObatBatchByIdTransObat(String idTransaksiObatDetail, Integer noBatch, Date expDate) {
        logger.info("[PermintaanVendorBoImpl.getEntityObatBatchByIdTransObat] START >>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_transaksi_obat_detail", idTransaksiObatDetail);
        hsCriteria.put("no_batch", noBatch);
        if (expDate != null) {
            hsCriteria.put("exp_date", expDate);
        } else {
            hsCriteria.put("order_last_created_date", "Y");
        }

        List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = null;
        MtSimrsTransaksiObatDetailBatchEntity batchEntity = new MtSimrsTransaksiObatDetailBatchEntity();

        try {
            batchEntities = transaksiObatDetailBatchDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getEntityObatBatchByIdTransObat] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getEntityObatBatchByIdTransObat] ERROR." + e.getMessage());
        }

        if (batchEntities.size() > 0 && batchEntities != null) {
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
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.isNewBatchCheckByNoBatchAndExpDate] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.isNewBatchCheckByNoBatchAndExpDate] ERROR." + e.getMessage());
        }

        Boolean isNew = true;
        if (obatDetailBatchEntities.size() > 0) {
            isNew = false;
        }

        logger.info("[PermintaanVendorBoImpl.isNewBatchCheckByNoBatchAndExpDate] END <<<");
        return isNew;
    }

    @Override
    public List<TransaksiObatDetail> getListApprovedBatch(String idPermintaanObat, Integer noBatch) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getListApprovedBatch] START >>>");

        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(idPermintaanObat);

        List<MtSimrsPermintaanVendorEntity> permintaanVendorEntities = getListEntityVendor(permintaanVendor);
        MtSimrsPermintaanVendorEntity permintaanEntity = new MtSimrsPermintaanVendorEntity();
        if (permintaanVendorEntities.size() > 0) {
            permintaanEntity = permintaanVendorEntities.get(0);
        }

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if (permintaanEntity.getIdApprovalObat() != null) {

            TransaksiObatDetail trans = new TransaksiObatDetail();
            trans.setIdApprovalObat(permintaanEntity.getIdApprovalObat());

            List<ImtSimrsTransaksiObatDetailEntity> detailEntities = getListEntityTransObatDetail(trans);

            if (detailEntities.size() > 0) {
                for (ImtSimrsTransaksiObatDetailEntity obatDetailEntity : detailEntities) {

                    TransaksiObatBatch batch = new TransaksiObatBatch();
                    batch.setIdTransaksiObatDetail(obatDetailEntity.getIdTransaksiObatDetail());
                    batch.setNoBatch(noBatch);

                    List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchObat(batch);

                    if (batchEntities.size() > 0) {
                        for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntities) {
                            if ("Y".equalsIgnoreCase(batchEntity.getApproveFlag()) && !"".equalsIgnoreCase(batchEntity.getIdBarang()) && batchEntity.getIdBarang() != null) {
                                TransaksiObatDetail obatDetail = new TransaksiObatDetail();
                                obatDetail.setIdTransaksiObatDetail(batchEntity.getIdTransaksiObatDetail());
                                obatDetail.setIdObat(obatDetailEntity.getIdObat());
                                obatDetail.setQtyApprove(batchEntity.getQtyApprove());
                                obatDetail.setJenisSatuan(batchEntity.getJenisSatuan());
                                obatDetail.setIdBarang(batchEntity.getIdBarang());
                                obatDetail.setLastUpdateWho(batchEntity.getLastUpdateWho());
                                obatDetail.setLastUpdate(batchEntity.getLastUpdate());

                                Obat obat = new Obat();
                                obat.setIdBarang(batchEntity.getIdBarang());
                                obat.setIdObat(obatDetailEntity.getIdObat());

                                List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);
                                if (obatEntities.size() > 0) {
                                    ImSimrsObatEntity obatEntity = obatEntities.get(0);
                                    obatDetail.setNamaObat(obatEntity.getNamaObat());
                                }
                                obatDetailList.add(obatDetail);
                            }
                        }
                    }
                }
            }
        }

        logger.info("[PermintaanVendorBoImpl.getListApprovedBatch] END <<<");
        return obatDetailList;
    }

    @Override
    public CrudResponse tutupPurchaseOrder(String idPermintaanVendor, String noJurnal) throws GeneralBOException {

        CrudResponse response = new CrudResponse();

        MtSimrsPermintaanVendorEntity entity = new MtSimrsPermintaanVendorEntity();

        try {
            entity = permintaanVendorDao.getById("idPermintaanVendor", idPermintaanVendor);
            response.setStatus("success");
            response.setMsg("Berhasil");
        } catch (HibernateException e) {
            logger.error("Found Error when update permintaan vendor " + e.getMessage());
            response.setStatus("error");
            response.setMsg("Found Error " + e.getMessage());
        }

        if (entity != null) {
            entity.setFlag("N");
            entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            entity.setLastUpdateWho(CommonUtil.userLogin());

            try {
                permintaanVendorDao.updateAndSave(entity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e) {
                logger.error("Found Error when update add " + e.getMessage());
                response.setStatus("error");
                response.setMsg("Found Error " + e.getMessage());
            }

            if (entity.getIdApprovalObat() != null) {
                ImtSimrsApprovalTransaksiObatEntity approveEntity = new ImtSimrsApprovalTransaksiObatEntity();

                try {
                    approveEntity = approvalTransaksiObatDao.getById("idApprovalObat", entity.getIdApprovalObat());
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    logger.error("Found Error " + e.getMessage());
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                }

                if (approveEntity != null) {

                    approveEntity.setApprovalFlag("Y");
                    approveEntity.setFlag("N");
                    approveEntity.setApprovePerson(CommonUtil.userLogin());
                    approveEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    approveEntity.setLastUpdateWho(CommonUtil.userLogin());
                    approveEntity.setNoJurnal(noJurnal);

                    try {
                        approvalTransaksiObatDao.updateAndSave(approveEntity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        logger.error("Found Error when update add " + e.getMessage());
                        response.setStatus("error");
                        response.setMsg("Found Error " + e.getMessage());
                    }
                }
            }
        }

        return response;
    }

    @Override
    public List getListPermintaanVendorDoc(String idPermintaanVendor) throws GeneralBOException {
        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(idPermintaanVendor);

        List<MtSimrsPermintaanVendorEntity> permintaanVendorEntities = getListEntityVendor(permintaanVendor);
        if (permintaanVendorEntities.size() > 0) {
            MtSimrsPermintaanVendorEntity vendorEntity = permintaanVendorEntities.get(0);
            if (vendorEntity.getUrlDocPo() != null && !"".equalsIgnoreCase(vendorEntity.getUrlDocPo()) && vendorEntity.getNotaVendor() != null && !"".equalsIgnoreCase(vendorEntity.getNotaVendor())) {
                return permintaanVendorEntities;
            }
        }
        return new ArrayList();
    }

    @Override
    public void saveUpoadDocPermintaanVendor(PermintaanVendor bean) throws GeneralBOException {

        if (bean.getIdPermintaanVendor() != null) {
            PermintaanVendor permintaanVendor = new PermintaanVendor();
            permintaanVendor.setIdPermintaanVendor(bean.getIdPermintaanVendor());
            List<MtSimrsPermintaanVendorEntity> permintaanVendorEntities = getListEntityVendor(permintaanVendor);
            if (permintaanVendorEntities.size() > 0) {
                MtSimrsPermintaanVendorEntity permintaanVendorEntity = permintaanVendorEntities.get(0);
                permintaanVendorEntity.setAction(bean.getAction());
                permintaanVendorEntity.setLastUpdate(bean.getLastUpdate());
                permintaanVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                permintaanVendorEntity.setNotaVendor(bean.getNotaVendor());
                permintaanVendorEntity.setUrlDocPo(bean.getUrlDocPo());

                try {
                    permintaanVendorDao.updateAndSave(permintaanVendorEntity);
                } catch (HibernateException e) {
                    logger.error("[PermintaanVendorBoImpl.saveUpoadDocPermintaanVendor] ERROR ", e);
                    throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpoadDocPermintaanVendor] ERROR ", e);
                }
            }
        } else {
            logger.error("[PermintaanVendorBoImpl.saveUpoadDocPermintaanVendor] ERROR ID Perintaan is null");
            throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpoadDocPermintaanVendor] ERROR ID Perintaan is null");
        }

    }

    @Override
    public List<TransaksiObatDetail> getListObatByBatch(String idPermintaan, Integer noBatch) throws GeneralBOException {
        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

        if (idPermintaan != null && !"".equalsIgnoreCase(idPermintaan) && noBatch != null && !"".equalsIgnoreCase(noBatch.toString())) {
            try {
                transaksiObatDetails = permintaanVendorDao.getListObatByBatch(idPermintaan, noBatch);
            } catch (HibernateException e) {
                logger.error("Found error when search obat list " + e.getMessage());
            }
        }
        return transaksiObatDetails;
    }

    @Override
    public List<Obat> getSearchObat(String query, String branch) throws GeneralBOException {
        List<Obat> obatArrayList = new ArrayList<>();

        if (query != null && !"".equalsIgnoreCase(query) && branch != null && !"".equalsIgnoreCase(branch)) {
            try {
                obatArrayList = obatDao.getSearchObat(query, branch);
            } catch (HibernateException e) {
                logger.error("Found error when search obat list " + e.getMessage());
            }
        }
        return obatArrayList;
    }

    private ImSimrsVendorEntity getListEntityVendorObat(Vendor bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getListEntityVendorObat] START >>>");

        List<ImSimrsVendorEntity> vendorEntityList = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean.getIdVendor() != null && !"".equalsIgnoreCase(bean.getIdVendor())) {
            hsCriteria.put("id_vendor", bean.getIdVendor());
        }
        if (bean.getNamaVendor() != null && !"".equalsIgnoreCase(bean.getNamaVendor())) {
            hsCriteria.put("nama_vendor", bean.getNamaVendor());
        }
        if (bean.getNpwp() != null && !"".equalsIgnoreCase(bean.getNpwp())) {
            hsCriteria.put("npwp", bean.getNpwp());
        }

        hsCriteria.put("flag", "Y");

        try {
            vendorEntityList = vendorDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorBoImpl.getListEntityVendorObat] ERROR.", e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getListEntityVendorObat] ERROR." + e.getMessage());
        }

        ImSimrsVendorEntity vendor = new ImSimrsVendorEntity();

        if (vendorEntityList.size() > 0) {
            vendor = vendorEntityList.get(0);

            if (vendor != null) {
                return vendor;
            }
        }

        logger.info("[PermintaanVendorBoImpl.getListEntityVendorObat] END <<<");
        return vendor;
    }

    @Override
    public ImUserVendorEntity getEntityUserVendorByIdUser(String userId) throws GeneralBOException {
        return userVendorDao.getById("userId", userId);
    }

    @Override
    public MtSimrsPermintaanVendorEntity getPermintaanVendorEntityById(String idPermintaan) throws GeneralBOException {
        return permintaanVendorDao.getById("idPermintaanVendor", idPermintaan);
    }

    private ImtSimrsTransaksiObatDetailEntity getTransaksiObatDetailEntityById(String idTrans) throws GeneralBOException{
        return transaksiObatDetailDao.getById("idTransaksiObatDetail", idTrans);
    }

    @Override
    public void saveListBatch(List<MtSimrsTransaksiObatDetailBatchEntity> listBatchEntity) throws GeneralBOException {

        if (listBatchEntity != null && listBatchEntity.size() > 0){
            for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : listBatchEntity){

                batchEntity.setId(new BigInteger(getNextIdBatchObat()));
                ImtSimrsTransaksiObatDetailEntity transEntity = getTransaksiObatDetailEntityById(batchEntity.getIdTransaksiObatDetail());
                if (transEntity != null){
                    batchEntity.setJenisSatuan(transEntity.getJenisSatuan());
                }

                try {
                    transaksiObatDetailBatchDao.addAndSave(batchEntity);
                } catch (HibernateException e){
                    logger.error("[PermintaanVendorBoImpl.saveListBatch] ERROR when get data. " + e.getMessage());
                    throw new GeneralBOException("[PermintaanVendorBoImpl.saveListBatch] ERROR when get data. " + e.getMessage());
                }
            }
        }
    }

    @Override
    public TransaksiObatBatch getBatchByIdTransAndNoBatch(String idTrans, String noBatch) throws GeneralBOException {

        if (noBatch != null){
            TransaksiObatBatch obatBatch = new TransaksiObatBatch();
            obatBatch.setIdTransaksiObatDetail(idTrans);
            obatBatch.setNoBatch(Integer.valueOf(noBatch));
            obatBatch.setJenis("do");
            List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchObat(obatBatch);
            if (batchEntities.size() > 0){
                MtSimrsTransaksiObatDetailBatchEntity batchEntity = batchEntities.get(0);
                obatBatch.setStExpDate(CommonUtil.ddMMyyyyFormat(batchEntity.getExpiredDate()));
                obatBatch.setDiskon(batchEntity.getDiskon());
                obatBatch.setBruto(batchEntity.getBruto());
                obatBatch.setNetto(batchEntity.getNetto());
                obatBatch.setQtyApprove(batchEntity.getQtyApprove());
                obatBatch.setId(batchEntity.getId());
                obatBatch.setNoFaktur(batchEntity.getNoFaktur());
                obatBatch.setNoDo(batchEntity.getNoDo());
                obatBatch.setNoInvoice(batchEntity.getNoInvoice());
                obatBatch.setStTglFaktur(batchEntity.getTanggalFaktur() != null ? CommonUtil.yyyyMMddFormat(batchEntity.getTanggalFaktur()) : null);
                obatBatch.setStTglInvoice(batchEntity.getTglInvoice() != null ? CommonUtil.yyyyMMddFormat(batchEntity.getTglInvoice()) : null);
                obatBatch.setStTglDo(batchEntity.getTglDo() != null ? CommonUtil.yyyyMMddFormat(batchEntity.getTglDo()) : null);
                return obatBatch;
            }
        }
        return null;
    }

    @Override
    public List<TransaksiObatDetail> getListObatByBatchByDo(String idPermintaan, String noDo) throws GeneralBOException {
        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

        if (idPermintaan != null && !"".equalsIgnoreCase(idPermintaan) && noDo != null && !"".equalsIgnoreCase(noDo)) {
            try {
                transaksiObatDetails = permintaanVendorDao.getListObatByBatchByDo(idPermintaan, noDo);
            } catch (HibernateException e) {
                logger.error("Found error when search obat list " + e.getMessage());
            }
        }
        return transaksiObatDetails;
    }

    @Override
    public void saveListDocVendor(List<ItSimrsDocPoEntity> docPoEntities) throws GeneralBOException {
        if (docPoEntities.size() > 0){
            for (ItSimrsDocPoEntity docPoEntity : docPoEntities){
                docPoEntity.setId(getNextIdDocPo());

                try {
                    docPoDao.addAndSave(docPoEntity);
                } catch (HibernateException e){
                    logger.error("Found error when save doc po " , e);
                }
            }
        }
    }

    @Override
    public List<DocPo> getListItemDoc(String idPermintaanVendor, String noBatch) throws GeneralBOException {
        return permintaanVendorDao.getListDaftarDoc(idPermintaanVendor, noBatch);
    }

    @Override
    public List<DocPo> getListImgByItem(String idItem) throws GeneralBOException {
        return permintaanVendorDao.getListImg(idItem);
    }

    @Override
    public List<TransaksiObatBatch> getListBatchByJenisItem(String idItem, String jenis) throws GeneralBOException {
        return permintaanVendorDao.getListBatchByJenis(idItem, jenis);
    }

    @Override
    public List<TransaksiObatBatch> getListBatchByJenisItem(String idItem, String jenis, String idApproval, String batch) throws GeneralBOException {
        return permintaanVendorDao.getListBatchByJenis(idItem, jenis, idApproval, batch);
    }

    private MtSimrsHargaObatEntity getHargaObatByIdObatAndBranch(String idObat, String branchId) throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.getHargaObatByCriteria] Start >>>");

        if (idObat != null && branchId != null && !"".equalsIgnoreCase(idObat) && !"".equalsIgnoreCase(branchId)){

            Map hsCriteria = new HashMap();
            hsCriteria.put("id_obat", idObat);
            hsCriteria.put("branch_id", branchId);

            List<MtSimrsHargaObatEntity> hargaObatEntities = new ArrayList<>();
            try {
                hargaObatEntities = hargaObatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.getHargaObatByCriteria] ERROR when get data. " + e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.getHargaObatByCriteria] ERROR when get data. " + e.getMessage());
            }

            logger.info("[PermintaanVendorBoImpl.getHargaObatByCriteria] End <<<");
            if (hargaObatEntities.size() > 0)
                return hargaObatEntities.get(0);
        }

        logger.info("[PermintaanVendorBoImpl.getHargaObatByCriteria] End <<<");
        return null;
    }

    @Override
    public List<PabrikObat> getListPabrikObatByIdObatForPo(String idObat, String tipePencarian) {
        logger.info("[PermintaanVendorBoImpl.getListPabrikObatByIdObatForPo] Start >>>");

        List<PabrikObat> pabrikObatList = new ArrayList<>();

        try {
            pabrikObatList = permintaanVendorDao.getListDataPabrikObatForPO(idObat, tipePencarian);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getListPabrikObatByIdObatForPo] ERROR when get data. " + e.getMessage());
            throw new GeneralBOException("[PermintaanVendorBoImpl.getListPabrikObatByIdObatForPo] ERROR when get data. " + e.getMessage());
        }

        logger.info("[PermintaanVendorBoImpl.getListPabrikObatByIdObatForPo] End <<<");
        return pabrikObatList;
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

    private String getIdNextSeqObat() throws GeneralBOException {
        String id = "";

        try {
            id = obatDao.getNextIdSeqObat();
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getNextIdBatchObat] ERROR WHEN GET data id seq obat, " + e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getNextIdBatchObat] ERROR WHEN GET data id seq obat, " + e.getMessage());
        }

        return id;
    }

    private String getNextIdDocPo(){
        String curDate = CommonUtil.stDateSeq();
        return "DOC" + curDate + docPoDao.getNextSeq();
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

    public void setVendorDao(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public void setObatGejalaDao(ObatGejalaDao obatGejalaDao) {
        this.obatGejalaDao = obatGejalaDao;
    }

    public void setRiwayatBarangDao(RiwayatBarangDao riwayatBarangDao) {
        this.riwayatBarangDao = riwayatBarangDao;
    }

    public void setTransaksiStokDao(TransaksiStokDao transaksiStokDao) {
        this.transaksiStokDao = transaksiStokDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setBatasTutupPeriodDao(BatasTutupPeriodDao batasTutupPeriodDao) {
        this.batasTutupPeriodDao = batasTutupPeriodDao;
    }

    public void setUserVendorDao(UserVendorDao userVendorDao) {
        this.userVendorDao = userVendorDao;
    }

    public void setDocPoDao(DocPoDao docPoDao) {
        this.docPoDao = docPoDao;
    }

    public void setHargaObatDao(HargaObatDao hargaObatDao) {
        this.hargaObatDao = hargaObatDao;
    }

    public void setHargaTerakhirDao(HargaTerakhirDao hargaTerakhirDao) {
        this.hargaTerakhirDao = hargaTerakhirDao;
    }
}
