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
import com.neurix.simrs.transaksi.transaksiobat.model.ApprovalTransaksiObat;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsApprovalTransaksiObatEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

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
                    permintaanVendor.setCreatedDate(permintaanVendorEntity.getCreatedDate());
                    permintaanVendor.setCreatedWho(permintaanVendorEntity.getCreatedWho());
                    permintaanVendor.setLastUpdate(permintaanVendorEntity.getLastUpdate());
                    permintaanVendor.setLastUpdateWho(permintaanVendorEntity.getLastUpdateWho());

                    if (!"".equalsIgnoreCase(permintaanVendor.getIdApprovalObat()))
                    {
                        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                        transaksiObatDetail.setIdApprovalObat(permintaanVendor.getIdApprovalObat());

                        // transaksi obat detail
                        List<ImtSimrsTransaksiObatDetailEntity> transaksiObatDetailEntities = getListEntityTransObatDetail(transaksiObatDetail);
                        if (transaksiObatDetailEntities.size() > 0)
                        {
                            permintaanVendor.setTransaksiObatDetails(transaksiObatDetailEntities);
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
        hsCriteria.put("flag", "Y");

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

            ImtSimrsApprovalTransaksiObatEntity approvalTransaksiObatEntity = new ImtSimrsApprovalTransaksiObatEntity();
            approvalTransaksiObatEntity.setIdApprovalObat("INV"+nextIdPermintanVendor());
            approvalTransaksiObatEntity.setTipePermintaan("004");
            approvalTransaksiObatEntity.setBranchId(bean.getBranchId());
            approvalTransaksiObatEntity.setFlag("Y");
            approvalTransaksiObatEntity.setAction("C");
            approvalTransaksiObatEntity.setCreatedDate(bean.getCreatedDate());
            approvalTransaksiObatEntity.setCreatedWho(bean.getCreatedWho());
            approvalTransaksiObatEntity.setLastUpdate(bean.getLastUpdate());
            approvalTransaksiObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                approvalTransaksiObatDao.addAndSave(approvalTransaksiObatEntity);
            } catch (HibernateException e){
                logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create approval obat. ",e);
                throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when when create approval obat. ",e);
            }

            MtSimrsPermintaanVendorEntity permintaanVendorEntity = new MtSimrsPermintaanVendorEntity();
            permintaanVendorEntity.setIdPermintaanVendor("PVM"+nextIdPermintanVendor());
            permintaanVendorEntity.setIdVendor(bean.getIdVendor());
            permintaanVendorEntity.setIdApprovalObat(approvalTransaksiObatEntity.getIdApprovalObat());
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

            if (bean.getTransaksiObatDetails().size() > 0){
                for (ImtSimrsTransaksiObatDetailEntity obatDetailEntity : bean.getTransaksiObatDetails())
                {
                    obatDetailEntity.setIdTransaksiObatDetail("ODT"+getNextTransaksiObatDetail());
                    obatDetailEntity.setIdApprovalObat(approvalTransaksiObatEntity.getIdApprovalObat());
                    obatDetailEntity.setFlag("Y");
                    obatDetailEntity.setAction("C");
                    obatDetailEntity.setCreatedDate(bean.getCreatedDate());
                    obatDetailEntity.setCreatedWho(bean.getCreatedWho());
                    obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                    obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        transaksiObatDetailDao.addAndSave(obatDetailEntity);
                    } catch (HibernateException e){
                        logger.error("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. "+e.getMessage());
                        throw new GeneralBOException("[PermintaanVendorBoImpl.saveListObatPo] ERROR when create obat detail. "+e.getMessage());
                    }
                }
            }
        }
        logger.info("[PermintaanVendorBoImpl.saveListObatPo] END <<<");
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

    public PermintaanVendorDao getPermintaanVendorDao() {
        return permintaanVendorDao;
    }

    public void setPermintaanVendorDao(PermintaanVendorDao permintaanVendorDao) {
        this.permintaanVendorDao = permintaanVendorDao;
    }

    public static void setLogger(Logger logger) {
        PermintaanVendorBoImpl.logger = logger;
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
