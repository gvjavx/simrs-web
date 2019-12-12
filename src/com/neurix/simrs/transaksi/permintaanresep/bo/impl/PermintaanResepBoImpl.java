package com.neurix.simrs.transaksi.permintaanresep.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
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
 * Created by Toshiba on 11/12/2019.
 */
public class PermintaanResepBoImpl implements PermintaanResepBo {
    private static transient Logger logger = Logger.getLogger(PermintaanResepBoImpl.class);
    private PermintaanResepDao permintaanResepDao;
    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private PasienDao pasienDao;
    private TransaksiObatDetailDao transaksiObatDetailDao;

    @Override
    public List<PermintaanResep> getByCriteria(PermintaanResep bean) throws GeneralBOException {
        logger.info("[PermintaanResepBoImpl.getByCriteria] START >>>>>>>");

        List<PermintaanResep> listOfResults = new ArrayList<>();

        if (bean != null){

            List<ImSimrsPermintaanResepEntity> resepEntities = getListEntityResep(bean);
            if (!resepEntities.isEmpty() && resepEntities.size() > 0)
            {
                PermintaanResep permintaanResep;
                for (ImSimrsPermintaanResepEntity resepEntity : resepEntities)
                {
                    permintaanResep = new PermintaanResep();
                    permintaanResep.setIdPermintaanResep(resepEntity.getIdPermintaanResep());
                    permintaanResep.setIdPasien(resepEntity.getIdPasien());
                    permintaanResep.setIdDetailCheckup(resepEntity.getIdDetailCheckup());
                    permintaanResep.setIdApprovalObat(resepEntity.getIdApprovalObat());
                    permintaanResep.setKeterangan(resepEntity.getKeterangan());
                    permintaanResep.setFlag(resepEntity.getFlag());
                    permintaanResep.setAction(resepEntity.getAction());
                    permintaanResep.setCreatedDate(resepEntity.getCreatedDate());
                    permintaanResep.setCreatedWho(resepEntity.getCreatedWho());
                    permintaanResep.setLastUpdate(resepEntity.getLastUpdate());
                    permintaanResep.setLastUpdateWho(resepEntity.getLastUpdateWho());
                    permintaanResep.setIdDokter(resepEntity.getIdDokter());

                    ImSimrsPasienEntity pasienEntity = getDataPasienById(resepEntity.getIdPasien());
                    if (pasienEntity != null)
                    {
                        permintaanResep.setNamaPasien(pasienEntity.getNama());
                    }

                    listOfResults.add(permintaanResep);
                }
            }
        }

        logger.info("[PermintaanResepBoImpl.getByCriteria] END <<<<<<<");
        return listOfResults;
    }

    private ImSimrsPasienEntity getDataPasienById(String id) throws GeneralBOException{
        logger.info("[PermintaanResepBoImpl.getDataPasienById] START >>>>>>>");

        if (id != null && !"".equalsIgnoreCase(id))
        {
            List<ImSimrsPasienEntity> pasienEntityList = null;
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_pasien", id);
            hsCriteria.put("flag", "Y");
            try {
                pasienEntityList = pasienDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PermintaanResepBoImpl.getDataPasienById] ERROR get list data pasien. ",e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getDataPasienById] ERROR get list data pasien. ",e);
            }

            logger.info("[PermintaanResepBoImpl.getDataPasienById] END <<<<<<<");
            if (!pasienEntityList.isEmpty() && pasienEntityList.size() > 0){
                return pasienEntityList.get(0);
            }
        }
        return null;
    }

    private List<ImSimrsPermintaanResepEntity> getListEntityResep(PermintaanResep bean) throws GeneralBOException{
        logger.info("[PermintaanResepBoImpl.getListEntityResep] START >>>>>>>");

        List<ImSimrsPermintaanResepEntity> permintaanResepEntityList = new ArrayList<>();

        if (bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())){
                hsCriteria.put("id_permintaan_resep", bean.getIdPermintaanResep());
            }
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
                hsCriteria.put("id_pasien", bean.getIdPasien());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())){
                hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                permintaanResepEntityList = permintaanResepDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ",e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ",e);
            }
        }

        logger.info("[PermintaanResepBoImpl.getListEntityResep] END <<<<<<<");
        return permintaanResepEntityList;
    }



    @Override
    public void saveAdd(PermintaanResep bean) throws GeneralBOException {
        logger.info("[PermintaanResepBoImpl.saveAdd] START >>>>>>>");

        String id = getNextApprovalObatId();

        ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
        approvalEntity.setIdApprovalObat("INV"+id);
        approvalEntity.setIdPelayanan(bean.getIdPelayanan());
        approvalEntity.setFlag("Y");
        approvalEntity.setAction("C");
        approvalEntity.setTipePermintaan("001");
        approvalEntity.setLastUpdate(bean.getCreatedDate());
        approvalEntity.setLastUpdateWho(bean.getCreatedWho());
        approvalEntity.setCreatedDate(bean.getCreatedDate());
        approvalEntity.setCreatedWho(bean.getCreatedWho());

        try {
            approvalTransaksiObatDao.addAndSave(approvalEntity);
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.saveAdd] ERROR when insert into approval transaksi. ",e);
            throw new GeneralBOException("[PermintaanResepBoImpl.saveAdd] ERROR when insert into approval transaksi. ",e);
        }

        id = getNextPermintaanResepId();
        ImSimrsPermintaanResepEntity permintaanEntity = new ImSimrsPermintaanResepEntity();
        permintaanEntity.setIdPermintaanResep("RSP"+id);
        permintaanEntity.setIdDokter(bean.getIdDokter());
        permintaanEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
        permintaanEntity.setIdPasien(bean.getIdPasien());
        permintaanEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
        permintaanEntity.setFlag("Y");
        permintaanEntity.setAction("C");
        permintaanEntity.setCreatedDate(bean.getCreatedDate());
        permintaanEntity.setCreatedWho(bean.getCreatedWho());
        permintaanEntity.setLastUpdate(bean.getCreatedDate());
        permintaanEntity.setLastUpdateWho(bean.getCreatedWho());

        try {
            permintaanResepDao.addAndSave(permintaanEntity);
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.saveAdd]  ERROR when insert into permintaan resep. ",e);
            throw new GeneralBOException("[PermintaanResepBoImpl.saveAdd]  ERROR when insert into permintaan resep. ",e);
        }

        logger.info("[PermintaanResepBoImpl.saveAdd] END <<<<<<<");
    }

    @Override
    public void saveObatResep(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[PermintaanResepBoImpl.saveObatResep] START >>>>>>>");

        ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();

        String id = getNextTransaksiObatDetail();
        obatDetailEntity.setIdTransaksiObatDetail("ODT"+id);
        obatDetailEntity.setIdApprovalObat(bean.getIdApprovalObat());
        obatDetailEntity.setIdObat(bean.getIdObat());
        obatDetailEntity.setQty(bean.getQty());
        obatDetailEntity.setFlag("Y");
        obatDetailEntity.setAction("C");
        obatDetailEntity.setCreatedDate(bean.getCreatedDate());
        obatDetailEntity.setCreatedWho(bean.getCreatedWho());
        obatDetailEntity.setLastUpdate(bean.getCreatedDate());
        obatDetailEntity.setLastUpdateWho(bean.getCreatedWho());
        obatDetailEntity.setKeterangan(bean.getKeterangan());
        try {
            transaksiObatDetailDao.addAndSave(obatDetailEntity);
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.saveObatResep]  ERROR when insert into transaksi obat detail. ",e);
            throw new GeneralBOException("[PermintaanResepBoImpl.saveObatResep]  ERROR when insert into transaksi obat detail. ",e);
        }
        logger.info("[PermintaanResepBoImpl.saveObatResep] END <<<<<<<");
    }

    private String getNextPermintaanResepId() throws GeneralBOException{
        String id = "";
        try {
            id = permintaanResepDao.getNextId();
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.getNextPermintaanResepId] ERROR when get next id resep. ",e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getNextPermintaanResepId] ERROR when get next id resep. ",e);
        }
        return id;
    }

    private String getNextApprovalObatId() throws GeneralBOException{
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.getNextApprovalObatId] ERROR when get next id approval obat. ",e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getNextApprovalObatId] ERROR when get next id approval obat. ",e);
        }
        return id;
    }

    private String getNextTransaksiObatDetail() throws GeneralBOException{
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.getNextTransaksiObatDetail] ERROR when get next id transaksi obat detail. ",e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getNextTransaksiObatDetail] ERROR when get next id transaksi obat detail. ",e);
        }
        return id;
    }

    public void setPermintaanResepDao(PermintaanResepDao permintaanResepDao) {
        this.permintaanResepDao = permintaanResepDao;
    }

    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setTransaksiObatDetailDao(TransaksiObatDetailDao transaksiObatDetailDao) {
        this.transaksiObatDetailDao = transaksiObatDetailDao;
    }
}
