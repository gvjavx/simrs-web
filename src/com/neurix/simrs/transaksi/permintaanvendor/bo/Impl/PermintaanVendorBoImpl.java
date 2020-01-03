package com.neurix.simrs.transaksi.permintaanvendor.bo.Impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.dao.PermintaanVendorDao;
import com.neurix.simrs.transaksi.permintaanvendor.model.MtSimrsPermintaanVendorEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsApprovalTransaksiObatEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
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


    @Override
    public List<PermintaanVendor> getByCriteria(PermintaanVendor bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getByCriteria] START >>>");

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        if (bean != null){

            List<MtSimrsPermintaanVendorEntity> permintaanVendorEntities = getListEntityVendor(bean);
            if (permintaanVendorEntities != null && permintaanVendorEntities.size() > 0)
            {
                PermintaanVendor permintaanVendor;
                for (MtSimrsPermintaanVendorEntity permintaanVendorEntity : permintaanVendorEntities)
                {
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

                    if (!"".equalsIgnoreCase(permintaanVendor.getIdApprovalObat()))
                    {
                        ImtSimrsApprovalTransaksiObatEntity approvalEntity = getApprovalTransaksiById(permintaanVendor.getIdApprovalObat());
                        if (approvalEntity != null) {

                            if (approvalEntity.getApprovalFlag() != null && !"".equalsIgnoreCase(approvalEntity.getApprovalFlag())) {
                                permintaanVendor.setKeterangan("Telah Dikonfirmasi");
                            } else {
                                permintaanVendor.setKeterangan("Menunggu Konfirmasi");
                            }

                            permintaanVendor.setApprovalFlag(approvalEntity.getApprovalFlag());
                        }

                        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                        transaksiObatDetail.setIdApprovalObat(permintaanVendor.getIdApprovalObat());

                        // transaksi obat detail
                        List<ImtSimrsTransaksiObatDetailEntity> transaksiObatDetailEntities = getListEntityTransObatDetail(transaksiObatDetail);
                        if (transaksiObatDetailEntities.size() > 0)
                        {
                            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
                            TransaksiObatDetail transaksiObatDetail1;
                            for (ImtSimrsTransaksiObatDetailEntity transaksiObatDetailEntity : transaksiObatDetailEntities){

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
                                transaksiObatDetail.setLembarPerBox(transaksiObatDetailEntity.getLembarPerBox());
                                transaksiObatDetail.setBijiPerLembar(transaksiObatDetailEntity.getBijiPerLembar());
                                transaksiObatDetail.setAverageHargaBox(transaksiObatDetailEntity.getAverageHargaBox());
                                transaksiObatDetail.setAverageHargaLembar(transaksiObatDetailEntity.getAverageHargaLembar());
                                transaksiObatDetail.setAverageHargaBiji(transaksiObatDetailEntity.getAverageHargaBiji());
                                transaksiObatDetail.setFlagDiterima(transaksiObatDetailEntity.getFlagDiterima());
                                transaksiObatDetail.setJenisSatuan(transaksiObatDetailEntity.getJenisSatuan());
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

    public List<MtSimrsPermintaanVendorEntity> getListEntityVendor(PermintaanVendor bean)throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.getListEntityVendor] START >>>");

        List<MtSimrsPermintaanVendorEntity> permintaanVendorEntityList = null;
        if (bean != null){

            Map hsCriteria = new HashMap();
            if (bean.getIdPermintaanVendor() != null && !"".equalsIgnoreCase(bean.getIdPermintaanVendor())){
                hsCriteria.put("id_permintaan_vendor", bean.getIdPermintaanVendor());
            }
            if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())){
                hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            }
            if (bean.getIdVendor() != null && !"".equalsIgnoreCase(bean.getIdVendor())){
                hsCriteria.put("id_vendor", bean.getIdVendor());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                permintaanVendorEntityList = permintaanVendorDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.getListEntityVendor] ERROR when get data. "+e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.getListEntityVendor] ERROR when get data. "+e.getMessage());
            }
        }

        logger.info("[PermintaanVendorBoImpl.getListEntityVendor] END <<<");
        return permintaanVendorEntityList;
    }

    private List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.getListEntityTransObatDetail] START >>>>>>");

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();
        if (!"".equalsIgnoreCase(bean.getIdApprovalObat())){
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            hsCriteria.put("flag", bean.getFlag());

            try {
                obatDetailEntities = transaksiObatDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.getListEntityTransObatDetail] ERROR when get data list entity of trans obat detail. ",e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.getListEntityTransObatDetail] ERROR when get data list entity of trans obat detail. "+ e.getMessage());
            }
        }

        logger.info("[PermintaanVendorBoImpl.getListEntityTransObatDetail] END <<<<<");
        return obatDetailEntities;
    }

    private ImtSimrsApprovalTransaksiObatEntity getApprovalTransaksiById(String id) throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.getApprovalTransaksiById] START >>>>>>>>>>");

        List<ImtSimrsApprovalTransaksiObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_approval_obat", id);
//        hsCriteria.put("flag", "Y");

        try {
            obatEntities = approvalTransaksiObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ",e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ",e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0)
        {
            return obatEntities.get(0);
        }
        logger.info("[PermintaanVendorBoImpl.getApprovalTransaksiById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsObatEntity getObatById(String id) throws GeneralBOException{
        logger.info("[PermintaanVendorBoImpl.getObatById] START >>>>>>>>>>");
        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", id);
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getObatById] ERROR when get obat entity by criteria. ",e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getObatById] ERROR when get obat entity by criteria. ",e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0)
        {
            return obatEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getObatById] END <<<<<<<<<<");
        return null;
    }

    @Override
    public void saveListObatPo(PermintaanVendor bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.saveListObatPo] START >>>");

        if (bean != null){
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
            permintaanVendorEntity.setIdPermintaanVendor("PVM"+nextIdPermintanVendor());
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
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create permintaan vendor. "+e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create permintaan vendor. "+e.getMessage());
            }

            if (bean.getListOfTransaksiObatDetail().size() > 0){
                for (TransaksiObatDetail obatDetail : bean.getListOfTransaksiObatDetail())
                {
                    ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                    obatDetailEntity.setIdTransaksiObatDetail("ODT"+getNextTransaksiObatDetail());
                    obatDetailEntity.setIdApprovalObat(permintaanVendorEntity.getIdApprovalObat());
                    obatDetailEntity.setQtyBox(obatDetail.getQtyBox());
                    obatDetailEntity.setLembarPerBox(obatDetail.getLembarPerBox());
                    obatDetailEntity.setQtyLembar(obatDetail.getQtyLembar());
                    obatDetailEntity.setBijiPerLembar(obatDetail.getBijiPerLembar());
                    obatDetailEntity.setQtyBiji(obatDetail.getQtyBiji());
                    obatDetailEntity.setAverageHargaBox(obatDetail.getAverageHargaBox());
                    obatDetailEntity.setFlag("Y");
                    obatDetailEntity.setAction("C");
                    obatDetailEntity.setCreatedDate(bean.getCreatedDate());
                    obatDetailEntity.setCreatedWho(bean.getCreatedWho());
                    obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                    obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    obatDetailEntity.setKeterangan("Permintaan PO");

                    try {
                        transaksiObatDetailDao.addAndSave(obatDetailEntity);
                    } catch (HibernateException e){
                        logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. "+e.getMessage());
                        throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. "+e.getMessage());
                    }
                }
            }

//            if (po != null && !"".equalsIgnoreCase(po)) {
//                JSONArray json = new JSONArray(po);
//                for (int i = 0; i < json.length(); i++) {
//
//                    JSONObject obj = json.getJSONObject(i);
//                    ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
//
//                    id = getNextTransaksiObatDetail();
//                    obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
//                    obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
//                    obatDetailEntity.setIdObat(obj.getString("ID"));
//                    obatDetailEntity.setQty(new BigInteger(obj.getString("Qty")));
//                    obatDetailEntity.setFlag(bean.getFlag());
//                    obatDetailEntity.setAction(bean.getAction());
//                    obatDetailEntity.setCreatedDate(bean.getCreatedDate());
//                    obatDetailEntity.setCreatedWho(bean.getCreatedWho());
//                    obatDetailEntity.setLastUpdate(bean.getCreatedDate());
//                    obatDetailEntity.setLastUpdateWho(bean.getCreatedWho());
//                    obatDetailEntity.setKeterangan("Permintaan PO");
//
//                    try {
//                        transaksiObatDetailDao.addAndSave(obatDetailEntity);
//                    } catch (HibernateException e) {
//                        logger.error("[PermintaanVendorBoImpl.saveListObatPo]  ERROR when insert into transaksi obat detail. ", e);
//                        throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo]  ERROR when insert into transaksi obat detail. ", e);
//                    }
//                }
//            }
        }
        logger.info("[PermintaanVendorBoImpl.saveListObatPo] END <<<");
    }

    @Override
    public void saveUpdateTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] START >>>");

        List<ImtSimrsTransaksiObatDetailEntity> transaksiObatDetailEntities = getListEntityTransObatDetail(bean);
        if (transaksiObatDetailEntities.size() > 0){
            ImtSimrsTransaksiObatDetailEntity transaksiObatDetailEntity = transaksiObatDetailEntities.get(0);

            transaksiObatDetailEntity.setQtyApprove(bean.getQty());
//            if ("box".equalsIgnoreCase(bean.getJenisSatuan())){
//                transaksiObatDetailEntity.setQtyBox(bean.getQty());
//            } else if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())){
//                transaksiObatDetailEntity.setQtyLembar(bean.getQty());
//            } else {
//                transaksiObatDetailEntity.setQtyBiji(bean.getQty());
//            }
            transaksiObatDetailEntity.setFlagDiterima("Y");
            transaksiObatDetailEntity.setAction("U");
            transaksiObatDetailEntity.setLastUpdate(bean.getLastUpdate());
            transaksiObatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                transaksiObatDetailDao.updateAndSave(transaksiObatDetailEntity);
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when update obat detail. "+e.getMessage());
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] ERROR when update obat detail. "+e.getMessage());
            }
        }
        logger.info("[PermintaanVendorBoImpl.saveUpdateTransObatDetail] END <<<");
    }

    private String nextIdPermintanVendor(){
        logger.info("[PermintaanVendorBoImpl.nextIdPermintanVendor] START >>>");
        String id = "";

        try {
            id = permintaanVendorDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.nextIdPermintanVendor] ERROR when get data. "+e.getMessage());
            throw new GeneralBOException("[PermintaanVendorBoImpl.nextIdPermintanVendor] ERROR when get data. "+e.getMessage());
        }

        logger.info("[PermintaanVendorBoImpl.nextIdPermintanVendor] END <<<");
        return id;
    }

    private String getNextApprovalObatId() throws GeneralBOException{
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getNextApprovalObatId] ERROR when get next seq. ",e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getNextApprovalObatId] ERRO Rwhen get next seq. ",e);
        }
        return id;
    }

    private String getNextTransaksiObatDetail() throws GeneralBOException{
        String id = "";
        try {
            id = transaksiObatDetailDao.getNextId();
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getNextTransaksiObatDetail] ERROR when get next seq. ",e);
            throw new GeneralBOException("[PermintaanVendorBoImpl.getNextTransaksiObatDetail] ERRO Rwhen get next seq. ",e);
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
}
