package com.neurix.simrs.transaksi.permintaanresep.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsApprovalTransaksiObatEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.transketeranganobat.dao.TransKeteranganObatDao;
import com.neurix.simrs.transaksi.transketeranganobat.model.ItSimrsKeteranganResepEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
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
    private TransKeteranganObatDao transKeteranganObatDao;

    @Override
    public List<PermintaanResep> getByCriteria(PermintaanResep bean) throws GeneralBOException {
        logger.info("[PermintaanResepBoImpl.getByCriteria] START >>>>>>>");

        List<PermintaanResep> listOfResults = new ArrayList<>();

        if (bean != null) {

            List<ImSimrsPermintaanResepEntity> resepEntities = getListEntityResep(bean);
            if (!resepEntities.isEmpty() && resepEntities.size() > 0) {
                PermintaanResep permintaanResep;
                for (ImSimrsPermintaanResepEntity resepEntity : resepEntities) {
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
                    permintaanResep.setTujuanPelayanan(resepEntity.getTujuanPelayanan());

                    ImSimrsPasienEntity pasienEntity = getDataPasienById(resepEntity.getIdPasien());
                    if (pasienEntity != null) {
                        permintaanResep.setNamaPasien(pasienEntity.getNama());
                    }

                    if(resepEntity.getIdApprovalObat() != null){
                        ImtSimrsApprovalTransaksiObatEntity approve = getListApprovalEntity(resepEntity.getIdApprovalObat());
                        if(approve.getIdApprovalObat() != null){
                            permintaanResep.setApproveFlag(approve.getApprovalFlag());
                        }
                    }

                    permintaanResep.setStatus(resepEntity.getStatus());
                    listOfResults.add(permintaanResep);
                }
            }
        }

        logger.info("[PermintaanResepBoImpl.getByCriteria] END <<<<<<<");
        return listOfResults;
    }

    private ImSimrsPasienEntity getDataPasienById(String id) throws GeneralBOException {
        logger.info("[PermintaanResepBoImpl.getDataPasienById] START >>>>>>>");

        if (id != null && !"".equalsIgnoreCase(id)) {
            List<ImSimrsPasienEntity> pasienEntityList = null;
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_pasien", id);
            hsCriteria.put("flag", "Y");
            try {
                pasienEntityList = pasienDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PermintaanResepBoImpl.getDataPasienById] ERROR get list data pasien. ", e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getDataPasienById] ERROR get list data pasien. ", e);
            }

            logger.info("[PermintaanResepBoImpl.getDataPasienById] END <<<<<<<");
            if (!pasienEntityList.isEmpty() && pasienEntityList.size() > 0) {
                return pasienEntityList.get(0);
            }
        }
        return null;
    }

    public ImtSimrsApprovalTransaksiObatEntity getListApprovalEntity(String idApproval){
        ImtSimrsApprovalTransaksiObatEntity entity = new ImtSimrsApprovalTransaksiObatEntity();
        List<ImtSimrsApprovalTransaksiObatEntity> list = new ArrayList<>();
        if(idApproval != null && !"".equalsIgnoreCase(idApproval)){
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_approval_obat", idApproval);

            try {
                list = approvalTransaksiObatDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("error" +e.getMessage());
            }

            if(list.size() > 0){
                entity = list.get(0);
            }
        }
        return entity;
    }

    @Override
    public List<ImSimrsPermintaanResepEntity> getListEntityResep(PermintaanResep bean) throws GeneralBOException {
        logger.info("[PermintaanResepBoImpl.getListEntityResep] START >>>>>>>");

        List<ImSimrsPermintaanResepEntity> permintaanResepEntityList = new ArrayList<>();

        if (bean != null) {

            Map hsCriteria = new HashMap();

            if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())) {
                hsCriteria.put("id_permintaan_resep", bean.getIdPermintaanResep());
            }
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                hsCriteria.put("id_pasien", bean.getIdPasien());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {
                hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                permintaanResepEntityList = permintaanResepDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ", e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ", e);
            }
        }

        logger.info("[PermintaanResepBoImpl.getListEntityResep] END <<<<<<<");
        return permintaanResepEntityList;
    }


    @Override
    public CrudResponse saveAdd(PermintaanResep bean, List<TransaksiObatDetail> detailList) throws GeneralBOException {
        logger.info("[PermintaanResepBoImpl.saveAdd] START >>>>>>>");
        CrudResponse response = new CrudResponse();
        String id = getNextApprovalObatId();
        ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
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
            response.setStatus("success");
            response.setMsg("Berhasil");
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.saveAdd] ERROR when insert into approval transaksi. ", e);
            response.setStatus("error");
            response.setMsg("[PermintaanResepBoImpl.saveAdd]  ERROR when insert into permintaan resep "+e.getMessage());
            return response;
        }

        id = getNextPermintaanResepId();
        ImSimrsPermintaanResepEntity permintaanEntity = new ImSimrsPermintaanResepEntity();
        permintaanEntity.setIdPermintaanResep("RSP" + id);
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
        permintaanEntity.setBranchId(bean.getBranchId());
        permintaanEntity.setTujuanPelayanan(bean.getTujuanPelayanan());
        permintaanEntity.setTtdPasien(bean.getTtdPasien());
        permintaanEntity.setTtdDokter(bean.getTtdDokter());
        permintaanEntity.setStatus("0");
        permintaanEntity.setIsUmum("N");
        permintaanEntity.setTglAntrian(bean.getCreatedDate());
        permintaanEntity.setJenisResep(bean.getJenisResep());

        try {
            permintaanResepDao.addAndSave(permintaanEntity);
            response.setStatus("success");
            response.setMsg("Berhasil");
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.saveAdd]  ERROR when insert into permintaan resep. ", e);
            response.setStatus("error");
            response.setMsg("[PermintaanResepBoImpl.saveAdd]  ERROR when insert into permintaan resep "+e.getMessage());
            return response;
        }

        if (detailList.size() > 0) {
            for (TransaksiObatDetail detailObat : detailList) {
                TransaksiObatDetail detail = new TransaksiObatDetail();
                detail.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                detail.setIdObat(detailObat.getIdObat());
                detail.setQty(detailObat.getQty());
                detail.setJenisResep(detailObat.getJenisResep());
                detail.setQtyApprove(detailObat.getQty());
                detail.setJenisSatuan(detailObat.getJenisSatuan());
                detail.setKeterangan(detailObat.getKeterangan());
                detail.setCreatedDate(bean.getCreatedDate());
                detail.setCreatedWho(bean.getCreatedWho());
                detail.setLastUpdate(bean.getCreatedDate());
                detail.setLastUpdateWho(bean.getCreatedWho());
                detail.setFrekuensi(detailObat.getFrekuensi());

                if(detailObat.getFlagRacik() != null && !"".equalsIgnoreCase(detailObat.getFlagRacik())){
                    detail.setFlagRacik(detailObat.getFlagRacik());
                    detail.setNamaRacik(detailObat.getNamaRacik());
                    detail.setIdRacik(detailObat.getIdRacik());
                }
                if (detailObat.getHariKronis() != null && !"".equalsIgnoreCase(detailObat.getHariKronis().toString())){
                    detail.setHariKronis(detailObat.getHariKronis());
                }
                if(detailObat.getKeteranganResepEntityList().size() > 0){
                    for (ItSimrsKeteranganResepEntity keteranganResepEntity: detailObat.getKeteranganResepEntityList()){
                        keteranganResepEntity.setId(transKeteranganObatDao.getNextSeq());
                        keteranganResepEntity.setIdObat(detailObat.getIdObat());
                        keteranganResepEntity.setIdPermintaanResep(permintaanEntity.getIdPermintaanResep());
                        try{
                            transKeteranganObatDao.addAndSave(keteranganResepEntity);
                        }catch (HibernateException e){
                            logger.error(e.getMessage());
                            response.setStatus("error");
                            response.setMsg("Error insert keterangan resep, "+e.getMessage());
                            return response;
                        }
                    }
                }
                saveObatResep(detail);
            }
        }
        logger.info("[PermintaanResepBoImpl.saveAdd] END <<<<<<<");
        return response;
    }

    @Override
    public CrudResponse saveObatResep(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[PermintaanResepBoImpl.saveObatResep] START >>>>>>>");
        CrudResponse response = new CrudResponse();
        ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
        String id = getNextTransaksiObatDetail();
        obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
        obatDetailEntity.setIdApprovalObat(bean.getIdApprovalObat());
        obatDetailEntity.setIdObat(bean.getIdObat());
        obatDetailEntity.setQty(bean.getQty());
        obatDetailEntity.setQtyApprove(bean.getQtyApprove());
        obatDetailEntity.setJenisSatuan(bean.getJenisSatuan());
        obatDetailEntity.setJenisResep(bean.getJenisResep());
        obatDetailEntity.setFlag("Y");
        obatDetailEntity.setAction("C");
        obatDetailEntity.setCreatedDate(bean.getCreatedDate());
        obatDetailEntity.setCreatedWho(bean.getCreatedWho());
        obatDetailEntity.setLastUpdate(bean.getCreatedDate());
        obatDetailEntity.setLastUpdateWho(bean.getCreatedWho());
        obatDetailEntity.setKeterangan(bean.getKeterangan());
        obatDetailEntity.setFlagRacik(bean.getFlagRacik());
        obatDetailEntity.setNamaRacik(bean.getNamaRacik());
        obatDetailEntity.setIdRacik(bean.getIdRacik());
        if (bean.getHariKronis() != null && bean.getHariKronis().compareTo(0) == 1){
            obatDetailEntity.setHariKronis(bean.getHariKronis());
        }
        obatDetailEntity.setFrekuensi(bean.getFrekuensi());
        try {
            transaksiObatDetailDao.addAndSave(obatDetailEntity);
            response.setStatus("success");
            response.setMsg("Berhasil");
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.saveObatResep]  ERROR when insert into transaksi obat detail. ", e);
            response.setStatus("error");
            response.setMsg("[PermintaanResepBoImpl.saveObatResep]  ERROR when insert into transaksi obat detail "+e.getMessage());
        }
        logger.info("[PermintaanResepBoImpl.saveObatResep] END <<<<<<<");
        return response;
    }

    @Override
    public ImSimrsPermintaanResepEntity getEntityPermintaanResepById(String id) throws GeneralBOException {
        return permintaanResepDao.getById("idPermintaanResep", id);
    }

    @Override
    public List<PermintaanResep> getListResepPasien(String noCheckup, String jenis) throws GeneralBOException {
        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        try{
            permintaanResepList = permintaanResepDao.getListResepPasien(noCheckup, jenis);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return permintaanResepList;
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

    private String getNextApprovalObatId() throws GeneralBOException {
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getNextApprovalObatId] ERROR when get next id approval obat. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getNextApprovalObatId] ERROR when get next id approval obat. ", e);
        }
        return id;
    }

    private String getNextTransaksiObatDetail() throws GeneralBOException {
        String id = "";
        try {
            id = transaksiObatDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getNextTransaksiObatDetail] ERROR when get next id transaksi obat detail. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getNextTransaksiObatDetail] ERROR when get next id transaksi obat detail. ", e);
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

    public void setTransKeteranganObatDao(TransKeteranganObatDao transKeteranganObatDao) {
        this.transKeteranganObatDao = transKeteranganObatDao;
    }
}
