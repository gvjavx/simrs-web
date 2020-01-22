package com.neurix.simrs.transaksi.obatpoli.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.obatpoli.dao.PermintaanObatPoliDao;
import com.neurix.simrs.transaksi.obatpoli.model.*;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailBatchDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class ObatPoliBoImpl implements ObatPoliBo {

    private static transient Logger logger = Logger.getLogger(ObatPoliBoImpl.class);
    private ObatPoliDao obatPoliDao;
    private PermintaanObatPoliDao permintaanObatPoliDao;
    private ObatDao obatDao;
    private PelayananDao pelayananDao;
    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private TransaksiObatDetailDao obatDetailDao;
    private TransaksiObatDetailBatchDao batchDao;

    @Override
    public List<ObatPoli> getObatPoliByCriteria(ObatPoli bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getObatPoliByCriteria] START >>>>>>>>>>");

        List<ObatPoli> obatPoliList = new ArrayList<>();
        if (bean != null) {
            List<MtSimrsObatPoliEntity> obatPoliEntities = getListEntityObatPoli(bean);
            if (!obatPoliEntities.isEmpty() && obatPoliEntities.size() > 0) {
                ObatPoli obatPoli;
                for (MtSimrsObatPoliEntity obatPoliEntity : obatPoliEntities) {
                    obatPoli = new ObatPoli();
                    obatPoli.setIdObat(obatPoliEntity.getIdObat());
                    obatPoli.setIdPelayanan(obatPoliEntity.getPrimaryKey().getIdPelayanan());
                    obatPoli.setFlag(obatPoliEntity.getFlag());
                    obatPoli.setQtyBox(obatPoliEntity.getQtyBox());
                    obatPoli.setQtyLembar(obatPoliEntity.getQtyLembar());
                    obatPoli.setQtyBiji(obatPoliEntity.getQtyBiji());
                    obatPoli.setQty(obatPoliEntity.getQty());
                    obatPoli.setAction(obatPoliEntity.getAction());
                    obatPoli.setCreatedDate(obatPoliEntity.getCreatedDate());
                    obatPoli.setCreatedWho(obatPoliEntity.getCreatedWho());
                    obatPoli.setLastUpdate(obatPoliEntity.getLastUpdate());
                    obatPoli.setLastUpdateWho(obatPoliEntity.getLastUpdateWho());
                    obatPoli.setBranchId(obatPoliEntity.getBranchId());
                    obatPoli.setIdPabrik(obatPoliEntity.getIdPabrik());
                    obatPoli.setExpiredDate(obatPoliEntity.getExpiredDate());
                    obatPoli.setIdBarang(obatPoliEntity.getPrimaryKey().getIdBarang());

                    ImSimrsObatEntity obatEntity = getObatById(obatPoliEntity.getIdObat());

                    if (obatEntity != null) {
                        obatPoli.setNamaObat(obatEntity.getNamaObat());
                        obatPoli.setLembarPerBox(obatEntity.getLembarPerBox());
                        obatPoli.setBijiPerLembar(obatEntity.getBijiPerLembar());
                    }

                    obatPoliList.add(obatPoli);
                }
            }
        }

        logger.info("[ObatPoliBoImpl.getObatPoliByCriteria] END <<<<<<<<<<");
        return obatPoliList;
    }

    @Override
    public List<PermintaanObatPoli> getSearchPermintaanObatPoli(PermintaanObatPoli bean, boolean isPoli) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getSearchPermintaanObatPoli] START >>>>>>>>>>");

        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        if ("002".equalsIgnoreCase(bean.getTipePermintaan())) {
            bean.setRequest(true);
        }
        if ("003".equalsIgnoreCase(bean.getTipePermintaan())) {
            bean.setRequest(false);
        }

        List<MtSimrsPermintaanObatPoliEntity> entities = null;

        try {
            entities = permintaanObatPoliDao.getListPermintaanObatPoliEntity(bean, isPoli);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getSearchPermintaanObatPoli] ERROR when get permintaan obat poli entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getSearchPermintaanObatPoli] ERROR when get permintaan obat poli entity by criteria. ", e);
        }

        if (!entities.isEmpty() && entities.size() > 0) {
            PermintaanObatPoli permintaanObatPoli;
            for (MtSimrsPermintaanObatPoliEntity permintaanObatPoliEntity : entities) {
                permintaanObatPoli = new PermintaanObatPoli();
                permintaanObatPoli.setIdPermintaanObatPoli(permintaanObatPoliEntity.getIdPermintaanObatPoli());
                permintaanObatPoli.setIdApprovalObat(permintaanObatPoliEntity.getIdApprovalObat());
                permintaanObatPoli.setIdObat(permintaanObatPoliEntity.getIdObat());
                permintaanObatPoli.setIdPelayanan(permintaanObatPoliEntity.getIdPelayanan());
                permintaanObatPoli.setQty(permintaanObatPoliEntity.getQty());
                permintaanObatPoli.setFlag(permintaanObatPoliEntity.getFlag());
                permintaanObatPoli.setAction(permintaanObatPoliEntity.getAction());
                permintaanObatPoli.setLastUpdate(permintaanObatPoliEntity.getLastUpdate());
                permintaanObatPoli.setLastUpdateWho(permintaanObatPoliEntity.getLastUpdateWho());
                permintaanObatPoli.setCreatedDate(permintaanObatPoliEntity.getCreatedDate());
                permintaanObatPoli.setCreatedWho(permintaanObatPoliEntity.getCreatedWho());
                permintaanObatPoli.setDiterimaFlag(permintaanObatPoli.getDiterimaFlag());
                permintaanObatPoli.setRetureFlag(permintaanObatPoli.getRetureFlag());
                permintaanObatPoli.setTujuanPelayanan(permintaanObatPoliEntity.getTujuanPelayanan());
                permintaanObatPoli.setDiterimaFlag(permintaanObatPoliEntity.getDiterimaFlag());
                permintaanObatPoli.setRetureFlag(permintaanObatPoliEntity.getRetureFlag());

                ImSimrsObatEntity simrsObatEntity = getObatById(permintaanObatPoli.getIdObat());
                if (simrsObatEntity != null) {
                    permintaanObatPoli.setNamaObat(simrsObatEntity.getNamaObat());
                    permintaanObatPoli.setQtyGudang(simrsObatEntity.getQty());
                }

                ImSimrsPelayananEntity pelayananEntity = getPoliById(permintaanObatPoliEntity.getIdPelayanan());
                if (pelayananEntity != null) {
                    permintaanObatPoli.setNamaPelayanan(pelayananEntity.getNamaPelayanan());
                }

                ImSimrsPelayananEntity tujuanPelayananEntity = getPoliById(permintaanObatPoliEntity.getTujuanPelayanan());
                if (tujuanPelayananEntity != null) {
                    permintaanObatPoli.setNamaTujuanPelayanan(tujuanPelayananEntity.getNamaPelayanan());
                }

                ImtSimrsApprovalTransaksiObatEntity approvalEntity = getApprovalTransaksiById(permintaanObatPoli.getIdApprovalObat());
                if (approvalEntity != null) {

                    if (approvalEntity.getApprovalFlag() != null && !"".equalsIgnoreCase(approvalEntity.getApprovalFlag())) {
                        permintaanObatPoli.setKeterangan("Telah Dikonfirmasi");
                    } else {
                        permintaanObatPoli.setKeterangan("Menunggu Konfirmasi");
                    }

                    permintaanObatPoli.setApprovalFlag(approvalEntity.getApprovalFlag());
                    permintaanObatPoli.setApprovePerson(approvalEntity.getApprovePerson());
                    permintaanObatPoli.setApprovalLastUpdate(approvalEntity.getLastUpdate());
                    permintaanObatPoli.setApprovalLastUpdateWho(approvalEntity.getLastUpdateWho());
                }

                String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(permintaanObatPoliEntity.getCreatedDate());

                permintaanObatPoli.setStCreatedDate(formatDate);

                permintaanObatPoli.setTipePermintaan(bean.getTipePermintaan());
                permintaanObatPoli.setRequest(bean.getRequest());

                permintaanObatPoliList.add(permintaanObatPoli);
            }
        }

        logger.info("[ObatPoliBoImpl.getSearchPermintaanObatPoli] END <<<<<<<<<<");
        return permintaanObatPoliList;
    }

//    @Override
//    public void saveAdd(ObatPoli bean) throws GeneralBOException {
//        logger.info("[ObatPoliBoImpl.saveAdd] START >>>>>>>>>>");
//
//        List<MtSimrsObatPoliEntity> obatPoliEntity = getListEntityObatPoli(bean);
//        if (obatPoliEntity.size() > 0) {
//            logger.error("[ObatPoliBoImpl.saveAdd] WARNING data telah ada pada. ");
//            throw new GeneralBOException("[ObatPoliBoImpl.saveAdd] WARNING data telah ada pada. ");
//        } else {
//            MtSimrsObatPoliEntity newObatPoli = new MtSimrsObatPoliEntity();
//            ObatPoliPk obatPoliPk = new ObatPoliPk();
//            obatPoliPk.setIdObat(bean.getIdObat());
//            obatPoliPk.setIdPelayanan(bean.getIdPelayanan());
//            newObatPoli.setBranchId(bean.getBranchId());
//            newObatPoli.setPrimaryKey(obatPoliPk);
//            newObatPoli.setQty(new BigInteger(String.valueOf(0)));
//            newObatPoli.setFlag("Y");
//            newObatPoli.setAction("C");
//            newObatPoli.setLastUpdate(bean.getCreatedDate());
//            newObatPoli.setLastUpdateWho(bean.getCreatedWho());
//            newObatPoli.setCreatedDate(bean.getCreatedDate());
//            newObatPoli.setCreatedWho(bean.getCreatedWho());
//
//            try {
//                obatPoliDao.addAndSave(newObatPoli);
//            } catch (HibernateException e) {
//                logger.error("[ObatPoliBoImpl.saveAdd] ERROR when insert into obat poli. ", e);
//                throw new GeneralBOException("[ObatPoliBoImpl.saveAdd] ERROR when insert into obat poli. ", e);
//            }
//        }
//
//        logger.info("[ObatPoliBoImpl.saveAdd] END <<<<<<<<<<");
//    }

    @Override
    public void saveRequest(PermintaanObatPoli bean, List<TransaksiObatDetail> obatDetailList) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.saveAddWithRequest] START >>>>>>>>>>");
        List<PermintaanObatPoli> obatPoliEntity = new ArrayList<>();

        for (TransaksiObatDetail obatDetail : obatDetailList) {
            PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
            permintaanObatPoli.setIdObat(obatDetail.getIdObat());
            permintaanObatPoli.setIdPelayanan(bean.getIdPelayanan());
            permintaanObatPoli.setBranchId(bean.getBranchId());
            obatPoliEntity = getCekListEntityObatPoli(permintaanObatPoli);

            PermintaanObatPoli permintaanEntity = new PermintaanObatPoli();
            if (!obatPoliEntity.isEmpty()) {
                permintaanEntity = obatPoliEntity.get(0);
                if (permintaanEntity != null) {
                    PermintaanObatPoli poli = new PermintaanObatPoli();
                    poli.setIdObat(permintaanEntity.getIdObat());
                    obatPoliEntity.add(poli);
                }
            }
        }

        if (obatPoliEntity.size() > 0) {
            logger.error("[ObatPoliBoImpl.saveAddWithRequest] WARNING data telah ada pada transaksi. ");
            throw new GeneralBOException("[ObatPoliBoImpl.saveAddWithRequest] WARNING data telah ada pada transaksi. ");
        } else {
            // save to table approve
            ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
            String id = getNextApprovalObatId();
            approvalEntity.setIdApprovalObat("INV" + id);
            approvalEntity.setIdPelayanan(bean.getIdPelayanan());
            approvalEntity.setBranchId(bean.getBranchId());
            approvalEntity.setFlag("Y");
            approvalEntity.setAction("C");
            approvalEntity.setTipePermintaan("002");
            approvalEntity.setLastUpdate(bean.getCreatedDate());
            approvalEntity.setLastUpdateWho(bean.getCreatedWho());
            approvalEntity.setCreatedDate(bean.getCreatedDate());
            approvalEntity.setCreatedWho(bean.getCreatedWho());

            try {
                approvalTransaksiObatDao.addAndSave(approvalEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveAddWithRequest] ERROR when insert into approval transaksi. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveAddWithRequest] ERROR when insert into approval transaksi. ", e);
            }

            // save to table permintaan transaksi
            MtSimrsPermintaanObatPoliEntity permintaanEntity = new MtSimrsPermintaanObatPoliEntity();
            id = getNextPermintaanObatId();
            permintaanEntity.setIdPermintaanObatPoli("POP" + id);
            permintaanEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
            permintaanEntity.setIdPelayanan(bean.getIdPelayanan());
            permintaanEntity.setBranchId(bean.getBranchId());
            permintaanEntity.setTujuanPelayanan(bean.getTujuanPelayanan());
            permintaanEntity.setFlag("Y");
            permintaanEntity.setAction("C");
            permintaanEntity.setLastUpdate(bean.getCreatedDate());
            permintaanEntity.setLastUpdateWho(bean.getCreatedWho());
            permintaanEntity.setCreatedDate(bean.getCreatedDate());
            permintaanEntity.setCreatedWho(bean.getCreatedWho());

            try {
                permintaanObatPoliDao.addAndSave(permintaanEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveAddWithRequest] ERROR when insert into permintaan obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveAddWithRequest] ERROR when insert into permintaan obat. ", e);
            }

            for (TransaksiObatDetail obatListDetail : obatDetailList) {

                ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();

                id = getNextTransaksiObatDetail();
                obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
                obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                obatDetailEntity.setIdObat(obatListDetail.getIdObat());
                obatDetailEntity.setQty(obatListDetail.getQty());
                obatDetailEntity.setJenisSatuan(obatListDetail.getJenisSatuan());
                obatDetailEntity.setFlag("Y");
                obatDetailEntity.setAction("C");
                obatDetailEntity.setCreatedDate(bean.getCreatedDate());
                obatDetailEntity.setCreatedWho(bean.getCreatedWho());
                obatDetailEntity.setLastUpdate(bean.getCreatedDate());
                obatDetailEntity.setLastUpdateWho(bean.getCreatedWho());
                obatDetailEntity.setKeterangan("Request Obat");

                try {
                    obatDetailDao.addAndSave(obatDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[PermintaanResepBoImpl.saveAddWithRequest]  ERROR when insert into transaksi obat detail. ", e);
                    throw new GeneralBOException("[PermintaanResepBoImpl.saveAddWithRequest]  ERROR when insert into transaksi obat detail. ", e);
                }

                //cek in table obat poli
//                ObatPoli obatPoli = new ObatPoli();
//                obatPoli.setIdObat(obatListDetail.getIdObat());
//                obatPoli.setIdPelayanan(bean.getIdPelayanan());
//                obatPoli.setBranchId(bean.getBranchId());
//                MtSimrsObatPoliEntity obatPoliEntityList = getObaPolitById(obatPoli);
//
//                if (obatPoliEntityList != null) {
//
//                    //update obat poli
//                    obatPoliEntityList.setAction("U");
//                    obatPoliEntityList.setLastUpdate(bean.getLastUpdate());
//                    obatPoliEntityList.setLastUpdateWho(bean.getLastUpdateWho());
//
//                    try {
//                        obatPoliDao.updateAndSave(obatPoliEntityList);
//                    } catch (HibernateException e) {
//                        logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
//                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
//                    }
//
//                } else {

                // save to table obat poli
//                    MtSimrsObatPoliEntity newObatPoli = new MtSimrsObatPoliEntity();
//                    ObatPoliPk obatPoliPk = new ObatPoliPk();
//                    obatPoliPk.setIdBarang(obatListDetail.getIdBarang());
//                    obatPoliPk.setIdPelayanan(bean.getIdPelayanan());
//                    newObatPoli.setBranchId(bean.getBranchId());
//                    newObatPoli.setPrimaryKey(obatPoliPk);
//                    newObatPoli.setQty(new BigInteger(String.valueOf(0)));
//                    newObatPoli.setFlag("Y");
//                    newObatPoli.setAction("C");
//                    newObatPoli.setLastUpdate(bean.getCreatedDate());
//                    newObatPoli.setLastUpdateWho(bean.getCreatedWho());
//                    newObatPoli.setCreatedDate(bean.getCreatedDate());
//                    newObatPoli.setCreatedWho(bean.getCreatedWho());
//
//                    try {
//                        obatPoliDao.addAndSave(newObatPoli);
//                    } catch (HibernateException e) {
//                        logger.error("[ObatPoliBoImpl.saveAdd] ERROR when insert into obat poli. ", e);
//                        throw new GeneralBOException("[ObatPoliBoImpl.saveAdd] ERROR when insert into obat poli. ", e);
//                    }
//                }
            }
        }

        logger.info("[ObatPoliBoImpl.saveAddWithRequest] END <<<<<<<<<<");
    }

    @Override
    public void saveReture(PermintaanObatPoli bean, List<TransaksiObatDetail> obatDetails) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.saveReture] START >>>>>>>>>>");

        List<PermintaanObatPoli> permintaanEntityList = new ArrayList<>();

        if (permintaanEntityList != null && permintaanEntityList.size() > 0) {
            for (TransaksiObatDetail obatDetail : obatDetails) {

                PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
                permintaanObatPoli.setIdObat(obatDetail.getIdObat());
                permintaanObatPoli.setIdPelayanan(obatDetail.getIdPelayanan());
                permintaanObatPoli.setBranchId(obatDetail.getBranchId());

                permintaanEntityList = getCekListEntityObatPoli(permintaanObatPoli);

                PermintaanObatPoli permintaanEntity = new PermintaanObatPoli();
                if (!permintaanEntityList.isEmpty()) {
                    permintaanEntity = permintaanEntityList.get(0);
                    if (permintaanEntity != null) {
                        PermintaanObatPoli poli = new PermintaanObatPoli();
                        poli.setIdObat(permintaanEntity.getIdObat());
                        poli.setQty(permintaanEntity.getQty());
                        permintaanEntityList.add(poli);
                    }
                }
            }
        }

        if (permintaanEntityList.size() > 0) {
            logger.error("[ObatPoliBoImpl.saveReture] WARNING data telah ada pada transaksi untuk transaksi reture. ");
            throw new GeneralBOException("[ObatPoliBoImpl.saveReture] WARNING data telah ada pada transaksi untuk transaksi reture. ");
        } else {

            MtSimrsPermintaanObatPoliEntity entity = permintaanObatPoliDao.getById("idPermintaanObatPoli", bean.getIdPermintaanObatPoli());

            if(entity != null){

                entity.setRetureFlag("Y");

                try {
                    permintaanObatPoliDao.updateAndSave(entity);
                }catch (HibernateException e){
                    logger.error("[ObatPoliBoImpl.saveReture] ERROR when insert into approval transaksi. ", e);
                    throw new GeneralBOException("[ObatPoliBoImpl.saveReture] ERROR when insert into approval transaksi. ", e);
                }
            }

            // save to table approve
            ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
            String id = getNextApprovalObatId();
            approvalEntity.setIdApprovalObat("INV" + id);
            approvalEntity.setIdPelayanan(bean.getIdPelayanan());
            approvalEntity.setBranchId(bean.getBranchId());
            approvalEntity.setFlag("Y");
            approvalEntity.setAction("C");
            approvalEntity.setTipePermintaan("003");
            approvalEntity.setLastUpdate(bean.getCreatedDate());
            approvalEntity.setLastUpdateWho(bean.getCreatedWho());
            approvalEntity.setCreatedDate(bean.getCreatedDate());
            approvalEntity.setCreatedWho(bean.getCreatedWho());

            try {
                approvalTransaksiObatDao.addAndSave(approvalEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveReture] ERROR when insert into approval transaksi. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveReture] ERROR when insert into approval transaksi. ", e);
            }

            // save to table permintaan transaksi
            MtSimrsPermintaanObatPoliEntity newPermintaanEntity = new MtSimrsPermintaanObatPoliEntity();
            id = getNextPermintaanObatId();
            newPermintaanEntity.setIdPermintaanObatPoli("POP" + id);
            newPermintaanEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
            newPermintaanEntity.setIdPelayanan(bean.getIdPelayanan());
            newPermintaanEntity.setBranchId(bean.getBranchId());
            newPermintaanEntity.setTujuanPelayanan(bean.getTujuanPelayanan());
            newPermintaanEntity.setFlag("Y");
            newPermintaanEntity.setAction("C");
            newPermintaanEntity.setLastUpdate(bean.getCreatedDate());
            newPermintaanEntity.setLastUpdateWho(bean.getCreatedWho());
            newPermintaanEntity.setCreatedDate(bean.getCreatedDate());
            newPermintaanEntity.setCreatedWho(bean.getCreatedWho());

            try {
                permintaanObatPoliDao.addAndSave(newPermintaanEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveReture] ERROR when insert into permintaan obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveReture] ERROR when insert into permintaan obat. ", e);
            }

            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
            List<TransaksiObatDetail> transaksiBatch = new ArrayList<>();

            // pilah jika sama
            int i = 0;
            for (TransaksiObatDetail obatDetail : obatDetails) {
                if (obatDetail.getQtyApprove().compareTo(new BigInteger(String.valueOf(0))) == 1){

                    TransaksiObatDetail oldObatDetail = new TransaksiObatDetail();

                    if (transaksiObatDetails.size() == 0){
                        obatDetail.setIdTransaksiObatDetail("ODT"+getNextTransaksiObatDetail());
                        transaksiObatDetails.add(obatDetail);
                        transaksiBatch.add(obatDetail);
                    } else if (transaksiObatDetails.get(i-1).getIdObat().equalsIgnoreCase(obatDetail.getIdObat())){
                        obatDetail.setIdTransaksiObatDetail(transaksiObatDetails.get(i-1).getIdTransaksiObatDetail());
                        transaksiObatDetails.add(obatDetail);
                        transaksiBatch.add(obatDetail);
                    } else {
                        obatDetail.setIdTransaksiObatDetail("ODT"+getNextTransaksiObatDetail());
                        transaksiObatDetails.add(obatDetail);
                        transaksiBatch.add(obatDetail);
                    }
                    i++;
                }
            }

            // save to table detail transaksi
            if (transaksiObatDetails.size() > 0){
                for (TransaksiObatDetail obatDetail : transaksiObatDetails){
                    ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();

//                    id = getNextTransaksiObatDetail();
//                    obatDetailEntity.setIdTransaksiObatDetail("ODT"+id);
                    obatDetailEntity.setIdTransaksiObatDetail(obatDetail.getIdTransaksiObatDetail());
                    obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                    obatDetailEntity.setIdObat(obatDetail.getIdObat());
                    obatDetailEntity.setQty(obatDetail.getQtyApprove());
                    obatDetailEntity.setQtyApprove(obatDetail.getQtyApprove());
                    obatDetailEntity.setJenisSatuan(obatDetail.getJenisSatuan());
                    obatDetailEntity.setFlag("Y");
                    obatDetailEntity.setAction("C");
                    obatDetailEntity.setCreatedDate(bean.getCreatedDate());
                    obatDetailEntity.setCreatedWho(bean.getCreatedWho());
                    obatDetailEntity.setLastUpdate(bean.getCreatedDate());
                    obatDetailEntity.setLastUpdateWho(bean.getCreatedWho());
                    obatDetailEntity.setKeterangan("Reture Obat");

                    try {
                        obatDetailDao.addAndSave(obatDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[PermintaanResepBoImpl.saveReture]  ERROR when insert into transaksi obat detail. ", e);
                        throw new GeneralBOException("[PermintaanResepBoImpl.saveReture]  ERROR when insert into transaksi obat detail. ", e);
                    }
                }
            }

            if (transaksiBatch.size() > 0){
                for (TransaksiObatDetail obatDetail : transaksiBatch){
                    MtSimrsTransaksiObatDetailBatchEntity batchEntity = new MtSimrsTransaksiObatDetailBatchEntity();

                    String seqBatch = batchDao.getNextId();
                    batchEntity.setId(new BigInteger(seqBatch));
                    batchEntity.setIdBarang(obatDetail.getIdBarang());
                    batchEntity.setIdTransaksiObatDetail(obatDetail.getIdTransaksiObatDetail());
                    batchEntity.setQtyApprove(obatDetail.getQtyApprove());
                    batchEntity.setJenisSatuan(obatDetail.getJenisSatuan());
                    batchEntity.setExpiredDate(obatDetail.getExpDate());

                    Obat obat = new Obat();
                    obat.setIdBarang(obatDetail.getIdBarang());
                    List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);
                    if (obatEntities.size() > 0){
                        batchEntity.setExpiredDate(obatEntities.get(0).getExpiredDate());
                    }

                    batchEntity.setNoBatch(1);
                    batchEntity.setStatus("Y");
                    batchEntity.setApproveFlag("Y");
                    batchEntity.setFlag("Y");
                    batchEntity.setAction("C");
                    batchEntity.setApproveFlag("Y");
                    batchEntity.setCreatedDate(bean.getCreatedDate());
                    batchEntity.setCreatedWho(bean.getCreatedWho());
                    batchEntity.setLastUpdate(bean.getCreatedDate());
                    batchEntity.setLastUpdateWho(bean.getCreatedWho());

                    try {
                        batchDao.addAndSave(batchEntity);
                    } catch (HibernateException e){
                        logger.error("[PermintaanResepBoImpl.saveAddWithRequest]  ERROR when insert into transaksi obat detail batch. ", e);
                        throw new GeneralBOException("[PermintaanResepBoImpl.saveAddWithRequest]  ERROR when insert into transaksi obat detail batch. ", e);
                    }

                    updateSubstractStockObatPoli(obatDetail, bean.getIdPelayanan(), bean.getBranchId());
                }
            }
        }
        logger.info("[ObatPoliBoImpl.saveReture] END <<<<<<<<<<");
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
        if (obatPoliEntities.size() > 0){
            obatPoliEntity = obatPoliEntities.get(0);
        }

        Obat obat = new Obat();
        obat.setIdBarang(bean.getIdBarang());
        obat.setBranchId(bean.getBranchId());

        List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);
        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();

        if (obatEntities.size() > 0){
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

    private void updateAddStockGudang(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.updateAddStockGudang] START >>>>>>>>>>");


        Obat obat = new Obat();
        obat.setIdBarang(bean.getIdBarang());
        obat.setIdObat(bean.getIdObat());
        obat.setBranchId(bean.getBranchId());

        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
        List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);
        if (obatEntities.size() > 0){
            obatEntity = obatEntities.get(0);
        }

        //sodiq, antisipasi jikalau nilai qty terdapat null
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

        if (obatEntity.getIdBarang() != null) {
            if ("box".equalsIgnoreCase(bean.getJenisSatuan())) {
                obatEntity.setQtyBox(bean.getQtyApprove().add(qtyBox));
            }
            if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())) {
                if (bean.getQtyApprove().compareTo(obatEntity.getLembarPerBox()) == 1) {
                    BigInteger lembarToBox = bean.getQtyApprove().divide(obatEntity.getLembarPerBox());
                    BigInteger sisaLembar = bean.getQtyApprove().mod(obatEntity.getLembarPerBox());

                    obatEntity.setQtyBox(qtyBox.add(lembarToBox));
                    obatEntity.setQtyLembar(qtyLembar.add(sisaLembar));
                } else {
                    obatEntity.setQtyLembar(qtyLembar.add(bean.getQtyLembar()));
                }
            }
            if ("biji".equalsIgnoreCase(bean.getJenisSatuan())) {
                obatEntity.setQtyBiji(qtyBiji.add(bean.getQtyApprove()));
            }

            obatEntity.setAction("U");
            obatEntity.setLastUpdate(bean.getLastUpdate());
            obatEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                obatDao.updateAndSave(obatEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.updateAddStockGudang] ERROR when update master obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.updateAddStockGudang] ERROR when update master obat. ", e);
            }
        }

        logger.info("[ObatPoliBoImpl.updateAddStockGudang] END <<<<<<<<<<");
    }

    private void updateAddStockPoli(TransaksiObatDetail bean, String idPoli, String branchId) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.updateAddStockPoli] START >>>>>>>>>>");

        Obat obat = new Obat();
        obat.setIdBarang(bean.getIdBarang());
        obat.setIdObat(bean.getIdObat());
        obat.setBranchId(bean.getBranchId());

        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
        List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);

        if (obatEntities.size() > 0) {
            obatEntity = obatEntities.get(0);
        }

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdPelayanan(idPoli);
        obatPoli.setIdBarang(bean.getIdBarang());
//        obatPoli.setIdObat(bean.getIdObat());
        obatPoli.setBranchId(branchId);
        obatPoli.setFlag("Y");

        List<MtSimrsObatPoliEntity> obatPoliEntities = getListEntityObatPoli(obatPoli);

        MtSimrsObatPoliEntity obatPoliEntity = new MtSimrsObatPoliEntity();

        if (obatPoliEntities.size() > 0) {
            obatPoliEntity = obatPoliEntities.get(0);
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

            obatPoliEntity.setExpiredDate(bean.getExpDate());
            obatPoliEntity.setAction("U");
            obatPoliEntity.setLastUpdate(bean.getLastUpdate());
            obatPoliEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                obatPoliDao.updateAndSave(obatPoliEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.updateAddStockPoli] ERROR when update master obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.updateAddStockPoli] ERROR when update master obat. ", e);
            }
        } else {

            MtSimrsObatPoliEntity newObatPoli = new MtSimrsObatPoliEntity();
            ObatPoliPk pk = new ObatPoliPk();
            pk.setIdPelayanan(idPoli);
            pk.setIdBarang(bean.getIdBarang());

            newObatPoli.setPrimaryKey(pk);
            newObatPoli.setIdObat(bean.getIdObat());
            newObatPoli.setExpiredDate(bean.getExpDate());
            newObatPoli.setBranchId(branchId);
            newObatPoli.setLastUpdate(bean.getLastUpdate());
            newObatPoli.setLastUpdateWho(bean.getLastUpdateWho());
            newObatPoli.setCreatedDate(bean.getLastUpdate());
            newObatPoli.setCreatedWho(bean.getLastUpdateWho());
            newObatPoli.setIdPabrik(obatEntity.getIdPabrik());
            newObatPoli.setFlag("Y");
            newObatPoli.setAction("C");

            if ("box".equalsIgnoreCase(bean.getJenisSatuan())) {
                newObatPoli.setQtyBox(bean.getQtyApprove());
            } else {
                newObatPoli.setQtyBox(new BigInteger(String.valueOf(0)));
            }
            if ("lembar".equalsIgnoreCase(bean.getJenisSatuan())) {
                newObatPoli.setQtyLembar(bean.getQtyApprove());
            } else {
                newObatPoli.setQtyLembar(new BigInteger(String.valueOf(0)));
            }
            if ("biji".equalsIgnoreCase(bean.getJenisSatuan())) {
                newObatPoli.setQtyBiji(bean.getQtyApprove());
            } else {
                newObatPoli.setQtyBiji(new BigInteger(String.valueOf(0)));
            }

            try {
                obatPoliDao.addAndSave(newObatPoli);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.updateAddStockPoli] ERROR when insert master obat poli. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.updateAddStockPoli] ERROR when insert master obat poli. ", e);
            }
        }

        logger.info("[ObatPoliBoImpl.updateAddStockGudang] END <<<<<<<<<<");
    }

    @Override
    public void saveApproveRequest(PermintaanObatPoli bean, List<TransaksiObatDetail> transList, boolean isPoli) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.saveApproveRequest] START >>>>>>>>>>");

        if (bean != null) {
            List<MtSimrsPermintaanObatPoliEntity> permintaanObatPoliEntities = getListEntityPermintaanObat(bean);
            if (!permintaanObatPoliEntities.isEmpty() && permintaanObatPoliEntities.size() > 0) {
                MtSimrsPermintaanObatPoliEntity permintaanObatPoliEntity = permintaanObatPoliEntities.get(0);
                ImtSimrsApprovalTransaksiObatEntity approvalTransaksiObatEntity = getApprovalTransaksiById(permintaanObatPoliEntity.getIdApprovalObat());
                if (approvalTransaksiObatEntity != null) {

                    // set aproval flag and person
                    approvalTransaksiObatEntity.setApprovalFlag("Y");
                    approvalTransaksiObatEntity.setAction("U");
                    approvalTransaksiObatEntity.setApprovePerson(bean.getLastUpdateWho());
                    approvalTransaksiObatEntity.setLastUpdate(bean.getLastUpdate());
                    approvalTransaksiObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        approvalTransaksiObatDao.updateAndSave(approvalTransaksiObatEntity);
                    } catch (HibernateException e) {
                        logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update approval. ", e);
                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update approval. ", e);
                    }

                    for (TransaksiObatDetail transaksiObatDetail : transList) {

                        transaksiObatDetail.setIdApprovalObat(approvalTransaksiObatEntity.getIdApprovalObat());
                        transaksiObatDetail.setFlag("Y");

                        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = getListEntityObatDetail(transaksiObatDetail);

                        if (obatDetailEntities.size() > 0) {

                            ImtSimrsTransaksiObatDetailEntity obatDetailEntity = obatDetailEntities.get(0);
                            obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                            obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            obatDetailEntity.setAction("U");
                            obatDetailEntity.setQtyApprove(transaksiObatDetail.getQtyApprove());

                            try {
                                obatDetailDao.updateAndSave(obatDetailEntity);
                            } catch (HibernateException e) {
                                logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update obat Detail. ", e);
                                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update obat Detai. ", e);
                            }

                            if (isPoli) {

                                // updating qty obat poli after konfirmasi
                                obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                                obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                ObatPoli obatPoli = new ObatPoli();
                                obatPoli.setIdPelayanan(bean.getTujuanPelayanan());
                                obatPoli.setBranchId(bean.getBranchId());

//                                updateSubstractStockObatPoli(obatDetailEntity, bean.getTujuanPelayanan(), bean.getBranchId());

                            } else {

                                TransaksiObatBatch batch = new TransaksiObatBatch();
                                batch.setIdTransaksiObatDetail(obatDetailEntity.getIdTransaksiObatDetail());

                                List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchByCriteria(batch);

                                if (batchEntities.size() > 0) {
                                    for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntities) {
                                        TransaksiObatDetail newTrans = new TransaksiObatDetail();
                                        newTrans.setIdObat(obatDetailEntity.getIdObat());
                                        newTrans.setJenisSatuan(batchEntity.getJenisSatuan());
                                        newTrans.setIdBarang(batchEntity.getIdBarang());
                                        newTrans.setExpDate(batchEntity.getExpiredDate());
                                        newTrans.setQtyApprove(batchEntity.getQtyApprove());
                                        newTrans.setLastUpdate(bean.getLastUpdate());
                                        newTrans.setLastUpdateWho(bean.getLastUpdateWho());

                                        updateSubstractStockGudang(newTrans);

                                        batchEntity.setApproveFlag("Y");
                                        batchEntity.setFlag("N");
                                        batchEntity.setLastUpdate(bean.getLastUpdate());
                                        batchEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                        try {
                                            batchDao.updateAndSave(batchEntity);
                                        } catch (HibernateException e){
                                            logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update obat Detail batch. ", e);
                                            throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update obat Detai batch. ", e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("[ObatPoliBoImpl.saveApproveRequest] END <<<<<<<<<<");
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

    private void updateSubstractStockGudang(TransaksiObatDetail bean) {

        Obat obat = new Obat();
        obat.setIdBarang(bean.getIdBarang());
        obat.setIdObat(bean.getIdObat());

        List<ImSimrsObatEntity> obatEntities = getListEntityObat(obat);

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
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
            }
        }
    }

    private void updatePerhitunganStockPoli(ImtSimrsTransaksiObatDetailEntity transaksiObatDetail, ObatPoli bean) {

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdObat(transaksiObatDetail.getIdObat());
        obatPoli.setIdPelayanan(bean.getIdPelayanan());
        obatPoli.setBranchId(bean.getBranchId());

        MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);

        if (obatPoliEntity != null) {

            ImSimrsObatEntity obatEntity = getObatById(obatPoliEntity.getIdObat());

            if (obatEntity != null) {

                //sodiq, 09-01-2019, antisipasi nilai qtybox, lembar, biji sama dengan null
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

                // BigInteger jmlh = obatEntity.getQty().subtract(transaksiObatDetail.getQtyApprove());
                if ("box".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {
                    BigInteger jml = qtyBox.subtract(transaksiObatDetail.getQtyApprove());
                    obatPoliEntity.setQtyBox(jml);
                }
                if ("lembar".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {

                    // jika lembar permintaan lebih kecil dari pada jumlah lembar dalam stock maka langsung mengurangi stock lembar
                    if (obatPoliEntity.getQtyLembar().compareTo(transaksiObatDetail.getQtyApprove()) == 1) {
                        BigInteger jml = qtyLembar.subtract(transaksiObatDetail.getQtyApprove());
                        obatPoliEntity.setQtyLembar(jml);
                    } else {

                        // jika lembar permintaan lebih besar dari pada jumlah lembar dalam stock maka jumlah permintaan
                        // dikurangin jumlah stock sehingga menghasilkan sisa lembar

                        BigInteger sisaLembar = transaksiObatDetail.getQtyApprove().subtract(obatPoliEntity.getQtyLembar());

                        // cek apakah stock box lebih dari 0
                        if (obatPoliEntity.getQtyBox().compareTo(new BigInteger(String.valueOf(0))) == 1) {

                            // jika jumlah lembar dalam box lebih besar dari sisa pengurangan permintaan lembar
                            // maka qty box dikurangin 1
                            if (obatEntity.getLembarPerBox().compareTo(sisaLembar) == 1) {
                                obatPoliEntity.setQtyBox(obatPoliEntity.getQtyBox().subtract(new BigInteger(String.valueOf(1))));

                                // sisa dari pengurangan pada box menjadi qty lembar
                                BigInteger sisaLembarPadaBox = obatEntity.getLembarPerBox().subtract(sisaLembar);
                                obatPoliEntity.setQtyLembar(sisaLembarPadaBox);
                            } else {

                                // konfersi dari satuan box ke lembar pada stock
                                BigInteger boxToLembar = obatPoliEntity.getQtyBox().multiply(obatEntity.getLembarPerBox());

                                // jumlah seluruh lembar pada stock dikurangi dengan sisaLembar
                                BigInteger jmlDikuranginSisaLembar = boxToLembar.subtract(sisaLembar);

                                // jika sisa stock sluruh lembar setelah pengurangan lebih besar dari jumlah lembar per box
                                if (jmlDikuranginSisaLembar.compareTo(obatEntity.getLembarPerBox()) == 1) {
                                    // jumlah hasil pengurangan dibagi dengan lembar perbox untuk mendapatkan
                                    // jumlah box yang tersisa
                                    BigInteger jmlBox = jmlDikuranginSisaLembar.divide(obatEntity.getLembarPerBox());

                                    // sisa pembagian akan dimasukan pada qty lembar
                                    BigInteger jmlLembarSisa = jmlDikuranginSisaLembar.mod(obatEntity.getLembarPerBox());

                                    obatPoliEntity.setQtyBox(jmlBox);
                                    obatPoliEntity.setQtyLembar(jmlLembarSisa);
                                } else {
                                    // jika sisa stock sluruh lembar setelah pengurangan lebih kecil dari jumlah lembar per box
                                    // maka sisa pengurangan menjadi stock untuk lembar
                                    // stock box menjadi kosong karna lebih kecil dari jumlah jumlah lembar per box
                                    obatPoliEntity.setQtyBox(new BigInteger(String.valueOf(0)));
                                    obatPoliEntity.setQtyBox(jmlDikuranginSisaLembar);
                                }
                            }
                        }
                    }
                }
                if ("biji".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())) {

                    // jika jumlah stock obat bijian lebih besar atau sama dari jumlah permintaan
                    // maka hanya perlu mengurangi stock biji
                    if (qtyBiji.compareTo(transaksiObatDetail.getQtyApprove()) == 1 || qtyBiji.compareTo(transaksiObatDetail.getQtyApprove()) == 0) {
                        obatPoliEntity.setQtyBiji(qtyBiji.subtract(transaksiObatDetail.getQtyApprove()));
                    } else {

                        // jika jumlah stock obat bijian lebih besar dari jumlah permintaan
                        // maka dilakukan pengurangan perlembarnya

                        // pengurangan jumlah permintaan dengan jumlah stock untuk mendapatkan sisa biji obat
                        BigInteger sisaBiji = transaksiObatDetail.getQtyApprove().subtract(qtyBiji);

                        // konfersi dari lembar ke biji
                        BigInteger lembarToBiji = obatPoliEntity.getQtyLembar().multiply(obatEntity.getBijiPerLembar());

                        // jika permintaan lebih besar dari pada seluruh penjumlahan biji per lembar
                        // maka dilakukan pengurangan mulai dari box
                        if (transaksiObatDetail.getQtyApprove().compareTo(lembarToBiji) == 1) {


                        } else {

                            // jika
                            if (obatEntity.getBijiPerLembar().compareTo(sisaBiji) == 1) {
                                obatPoliEntity.setQtyLembar(obatPoliEntity.getQtyLembar().subtract(new BigInteger(String.valueOf(1))));

                                BigInteger sisBijiPadaLembar = obatEntity.getBijiPerLembar().subtract(sisaBiji);
                                obatPoliEntity.setQtyBiji(sisBijiPadaLembar);

                            } else {


                                BigInteger jmlDikurangiSisaBiji = lembarToBiji.subtract(sisaBiji);

                                // jika sisa stock sluruh biji setelah pengurangan lebih besar dari jumlah biji per lembar
                                if (jmlDikurangiSisaBiji.compareTo(obatEntity.getBijiPerLembar()) == 1) {
                                    BigInteger jmlLembar = jmlDikurangiSisaBiji.divide(obatEntity.getBijiPerLembar());
                                    BigInteger jmlBiji = jmlDikurangiSisaBiji.mod(obatEntity.getBijiPerLembar());

                                    obatPoliEntity.setQtyLembar(jmlLembar);
                                    obatPoliEntity.setQtyBiji(jmlBiji);
                                } else {
                                    // jika sisa stock sluruh biji setelah pengurangan lebih kecil dari jumlah biji per lembar
                                    // maka sisa pengurangan menjadi stock untuk biji
                                    // stock menjadi kosong karna lebih kecil dari jumlah biji per lembar
                                    obatPoliEntity.setQtyLembar(new BigInteger(String.valueOf(0)));
                                    obatPoliEntity.setQtyBiji(jmlDikurangiSisaBiji);
                                }
                            }
                        }
                    }
                }

                obatPoliEntity.setAction("U");
                obatPoliEntity.setLastUpdate(transaksiObatDetail.getLastUpdate());
                obatPoliEntity.setLastUpdateWho(transaksiObatDetail.getLastUpdateWho());

                try {
                    obatPoliDao.updateAndSave(obatPoliEntity);
                } catch (HibernateException e) {
                    logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                    throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                }
            }
        }
    }

    @Override
    public void saveApproveReture(PermintaanObatPoli bean, boolean isPoli) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.saveApproveReture] START >>>>>>>>>>");

        if (bean != null) {
            List<MtSimrsPermintaanObatPoliEntity> permintaanObatPoliEntities = getListEntityPermintaanObat(bean);
            if (!permintaanObatPoliEntities.isEmpty() && permintaanObatPoliEntities.size() > 0) {
                // set reture flag and flag permintaan obat poli
                MtSimrsPermintaanObatPoliEntity permintaanObatPoliEntity = permintaanObatPoliEntities.get(0);
                permintaanObatPoliEntity.setRetureFlag("Y");
                permintaanObatPoliEntity.setFlag("N");
                permintaanObatPoliEntity.setAction("U");
                permintaanObatPoliEntity.setLastUpdate(bean.getLastUpdate());
                permintaanObatPoliEntity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    permintaanObatPoliDao.updateAndSave(permintaanObatPoliEntity);
                } catch (HibernateException e) {
                    logger.error("[ObatPoliBoImpl.saveApproveReture] ERROR when update permintaan obat poli. ", e);
                    throw new GeneralBOException("[ObatPoliBoImpl.saveApproveReture] ERROR when update permintaan obat poli. ", e);
                }

                ImtSimrsApprovalTransaksiObatEntity approvalTransaksiObatEntity = getApprovalTransaksiById(permintaanObatPoliEntity.getIdApprovalObat());
                if (approvalTransaksiObatEntity != null) {
                    // set aproval flag and person
                    approvalTransaksiObatEntity.setApprovalFlag("N");
                    approvalTransaksiObatEntity.setFlag("N");
                    approvalTransaksiObatEntity.setAction("U");
                    approvalTransaksiObatEntity.setApprovePerson(bean.getLastUpdateWho());
                    approvalTransaksiObatEntity.setLastUpdate(bean.getLastUpdate());
                    approvalTransaksiObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        approvalTransaksiObatDao.updateAndSave(approvalTransaksiObatEntity);
                    } catch (HibernateException e) {
                        logger.error("[ObatPoliBoImpl.saveApproveReture] ERROR when update approval. ", e);
                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveReture] ERROR when update approval. ", e);
                    }

                    TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdApprovalObat(approvalTransaksiObatEntity.getIdApprovalObat());
                    transaksiObatDetail.setFlag("Y");
                    List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = getListEntityObatDetail(transaksiObatDetail);

                    if (!obatDetailEntities.isEmpty() && obatDetailEntities.size() > 0) {
                        for (ImtSimrsTransaksiObatDetailEntity obatDetailEntity : obatDetailEntities) {

                            obatDetailEntity.setFlag("N");
                            obatDetailEntity.setAction("U");
                            obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                            obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            obatDetailEntity.setQtyApprove(obatDetailEntity.getQty());

                            try {
                                obatDetailDao.updateAndSave(obatDetailEntity);
                            } catch (HibernateException e) {
                                logger.error("[ObatPoliBoImpl.saveApproveReture] ERROR when update obat Detail. ", e);
                                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveReture] ERROR when update obat Detail. ", e);
                            }

                            if (isPoli) {
                                // updating qty obat poli after konfirmasi
//                                updateAddStockPoli(obatDetailEntity, bean.getTujuanPelayanan(), bean.getBranchId());
                            } else {

                                TransaksiObatBatch batch = new TransaksiObatBatch();
                                batch.setIdTransaksiObatDetail(obatDetailEntity.getIdTransaksiObatDetail());

                                List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchByCriteria(batch);
                                if (batchEntities.size() > 0){
                                    for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntities){

                                        if (batchEntity.getQtyApprove().compareTo(new BigInteger(String.valueOf(0))) == 1){
                                            TransaksiObatDetail obatDetail = new TransaksiObatDetail();
                                            obatDetail.setIdTransaksiObatDetail(batchEntity.getIdTransaksiObatDetail());
                                            obatDetail.setIdBarang(batchEntity.getIdBarang());
                                            obatDetail.setIdObat(obatDetailEntity.getIdObat());
                                            obatDetail.setQtyApprove(batchEntity.getQtyApprove());
                                            obatDetail.setJenisSatuan(batchEntity.getJenisSatuan());
                                            obatDetail.setExpDate(batchEntity.getExpiredDate());
                                            obatDetail.setBranchId(bean.getBranchId());
                                            obatDetail.setLastUpdate(bean.getLastUpdate());
                                            obatDetail.setLastUpdateWho(bean.getLastUpdateWho());

                                            // update add stock gudang
                                            updateAddStockGudang(obatDetail);
                                        }

                                        batchEntity.setApproveFlag("Y");
                                        batchEntity.setFlag("N");
                                        batchEntity.setAction("U");
                                        batchEntity.setLastUpdate(bean.getLastUpdate());
                                        batchEntity.setLastUpdateWho(bean.getLastUpdateWho());

                                        try {
                                            batchDao.updateAndSave(batchEntity);
                                        } catch (HibernateException e){
                                            logger.error("[ObatPoliBoImpl.saveApproveReture] ERROR when update batch obat Detail. ", e);
                                            throw new GeneralBOException("[ObatPoliBoImpl.saveApproveReture] ERROR when update batch obat Detail. ", e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("[ObatPoliBoImpl.saveApproveReture] END <<<<<<<<<<");
    }

    @Override
    public void saveApproveDiterima(PermintaanObatPoli bean, List<TransaksiObatDetail> listObatDetail) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.saveApproveDiterima] START >>>>>>>>>>");

        if (bean != null) {

            ImtSimrsApprovalTransaksiObatEntity approvalObatEntity = new ImtSimrsApprovalTransaksiObatEntity();

            try {
                approvalObatEntity = approvalTransaksiObatDao.getById("idApprovalObat", bean.getIdApprovalObat());
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveApproveDiterima] ERROR when search approval obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveDiterima] ERROR when search approval obat. ", e);
            }

            if (approvalObatEntity != null) {
                approvalObatEntity.setFlag("N");
                approvalObatEntity.setAction(bean.getAction());
                approvalObatEntity.setLastUpdate(bean.getLastUpdate());
                approvalObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    approvalTransaksiObatDao.updateAndSave(approvalObatEntity);
                } catch (HibernateException e) {
                    logger.error("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update approval obat. ", e);
                    throw new GeneralBOException("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update approval obat. ", e);
                }
            }

            MtSimrsPermintaanObatPoliEntity permintaanObatEntity = new MtSimrsPermintaanObatPoliEntity();

            try {
                permintaanObatEntity = permintaanObatPoliDao.getById("idPermintaanObatPoli", bean.getIdPermintaanObatPoli());
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveApproveDiterima] ERROR when search permintaan obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveDiterima] ERROR when search permintaan obat. ", e);
            }

            if (permintaanObatEntity != null) {
                permintaanObatEntity.setFlag("N");
                permintaanObatEntity.setDiterimaFlag("Y");
                permintaanObatEntity.setAction(bean.getAction());
                permintaanObatEntity.setLastUpdate(bean.getLastUpdate());
                permintaanObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    permintaanObatPoliDao.updateAndSave(permintaanObatEntity);
                } catch (HibernateException e) {
                    logger.error("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update permintaan obat. ", e);
                    throw new GeneralBOException("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update permintaan obat. ", e);
                }
            }

//            TransaksiObatDetail obatDetail = new TransaksiObatDetail();
//            obatDetail.setIdApprovalObat(bean.getIdApprovalObat());
//            obatDetail.setFlag("Y");
//            List<ImtSimrsTransaksiObatDetailEntity> obatDetailList = getListEntityObatDetail(obatDetail);
//
//            if (!obatDetailList.isEmpty()) {
//
//                for (ImtSimrsTransaksiObatDetailEntity entity : obatDetailList) {
//                    entity.setFlag("N");
//                    entity.setAction(bean.getAction());
//                    entity.setLastUpdate(bean.getLastUpdate());
//                    entity.setLastUpdateWho(bean.getLastUpdateWho());
//
//                    try {
//                        obatDetailDao.updateAndSave(entity);
//                    } catch (HibernateException e) {
//                        logger.error("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update detail obat. ", e);
//                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update detail obat. ", e);
//                    }
//                }
//            }

            for (TransaksiObatDetail detail : listObatDetail) {
                // save add stock obat poli if diterima

                if (!"N".equalsIgnoreCase(detail.getStatus())) {

                    TransaksiObatBatch transaksiObatBatch = new TransaksiObatBatch();
                    transaksiObatBatch.setIdTransaksiObatDetail(detail.getIdTransaksiObatDetail());
                    transaksiObatBatch.setIdBarang(detail.getIdBarang());

                    List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchByCriteria(transaksiObatBatch);

                    if (batchEntities.size() > 0) {
                        for (MtSimrsTransaksiObatDetailBatchEntity batchEntity : batchEntities) {

                            detail.setIdBarang(batchEntity.getIdBarang());
                            detail.setQtyApprove(batchEntity.getQtyApprove());
                            detail.setJenisSatuan(batchEntity.getJenisSatuan());
                            detail.setExpDate(batchEntity.getExpiredDate());
                            detail.setCreatedWho(bean.getLastUpdateWho());
                            detail.setCreatedDate(bean.getLastUpdate());
                            detail.setLastUpdate(bean.getLastUpdate());
                            detail.setLastUpdateWho(bean.getLastUpdateWho());

                            updateAddStockPoli(detail, bean.getIdPelayanan(), bean.getBranchId());

                            batchEntity.setFlag("N");
                            batchEntity.setAction("U");
                            batchEntity.setLastUpdate(bean.getLastUpdate());
                            batchEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            try {
                                batchDao.updateAndSave(batchEntity);
                            } catch (HibernateException e) {
                                logger.error("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update batch obat. ", e);
                                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update batch obat. ", e);
                            }
                        }
                    }
                }
            }
        }
        logger.info("[ObatPoliBoImpl.saveApproveDiterima] END >>>>>>>>>>");
    }

    @Override
    public List<ObatPoli> getTujuanPelayanan(ObatPoli bean) throws GeneralBOException {

        logger.info("[ObatPoliBoImpl.getTujuanPelayanan] START >>>>>>>>>>");
        List<ObatPoli> obatPoliList = new ArrayList<>();

        if (bean != null) {
            try {
                obatPoliList = obatPoliDao.getTujuanPelyanan(bean);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.getTujuanPelayanan] ERROR when searc tujuan pelayanan. ", e);
                throw new GeneralBOException("[getTujuanPelayanan.saveApproveReture] ERROR when searc tujuan pelayanan. ", e);
            }
        }
        logger.info("[ObatPoliBoImpl.getTujuanPelayanan] END >>>>>>>>>>");
        return obatPoliList;
    }

    @Override
    public List<PermintaanObatPoli> getDetailLitsPermintaan(PermintaanObatPoli bean, boolean isPole) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getDetailLitspermintaan] START >>>>>>>>>>");
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        if (bean != null) {
            try {
                permintaanObatPoliList = permintaanObatPoliDao.getDetailListPermintaan(bean, isPole);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.getDetailLitspermintaan] ERROR when searc detail permintaan obat ke gudang. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.getDetailLitspermintaan] ERROR when searc  permintaan obat ke gudang. ", e);
            }
        }
        logger.info("[ObatPoliBoImpl.getDetailLitspermintaan] END >>>>>>>>>>");
        return permintaanObatPoliList;
    }

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

    @Override
    public List<PermintaanObatPoli> getCekListEntityObatPoli(PermintaanObatPoli bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListEntityObatPoli] START >>>>>>>>>>");
        List<PermintaanObatPoli> results = new ArrayList<>();

        try {
            results = obatPoliDao.cekIdObatInTransaksi(bean);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data obat poli entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data obat poli entity by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.getListEntityObatPoli] END <<<<<<<<<<");
        return results;
    }

    private List<MtSimrsPermintaanObatPoliEntity> getListEntityPermintaanObat(PermintaanObatPoli bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListEntityPermintaanObat] START >>>>>>>>>>");
        List<MtSimrsPermintaanObatPoliEntity> obatPoliEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
            hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
        }
        if (bean.getTujuanPelayanan() != null && !"".equalsIgnoreCase(bean.getTujuanPelayanan())) {
            hsCriteria.put("tujuan_pelayanan", bean.getTujuanPelayanan());
        }
        if (bean.getIdPermintaanObatPoli() != null && !"".equalsIgnoreCase(bean.getIdPermintaanObatPoli())) {
            hsCriteria.put("id_permintaan_obat_poli", bean.getIdPermintaanObatPoli());
        }
        if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {
            hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
        }
        if (bean.getDiterimaFlag() != null && !"".equalsIgnoreCase(bean.getDiterimaFlag())) {
            hsCriteria.put("diterima_flag", bean.getDiterimaFlag());
        }
        if (bean.getRetureFlag() != null && !"".equalsIgnoreCase(bean.getRetureFlag())) {
            hsCriteria.put("reture_flag", bean.getRetureFlag());
        }

        hsCriteria.put("flag", bean.getFlag());

        try {
            obatPoliEntities = permintaanObatPoliDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getListEntityPermintaanObat] ERROR when get permintaan obat poli entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityPermintaanObat] ERROR when get permintaan obat poli entity by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.getListEntityPermintaanObat] END <<<<<<<<<<");
        return obatPoliEntities;
    }

    private ImtSimrsApprovalTransaksiObatEntity getApprovalTransaksiById(String id) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getApprovalTransaksiById] START >>>>>>>>>>");

        List<ImtSimrsApprovalTransaksiObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_approval_obat", id);
//        hsCriteria.put("flag", "Y");

        try {
            obatEntities = approvalTransaksiObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ", e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
            return obatEntities.get(0);
        }
        logger.info("[ObatPoliBoImpl.getApprovalTransaksiById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsObatEntity getObatById(String id) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getObatById] START >>>>>>>>>>");
        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", id);
        hsCriteria.put("branch_id", CommonUtil.userBranchLogin());
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getObatById] ERROR when get obat entity by criteria. ", e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0) {
            return obatEntities.get(0);
        }

        logger.info("[ObatPoliBoImpl.getObatById] END <<<<<<<<<<");
        return null;
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

    private ImSimrsPelayananEntity getPoliById(String id) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getPoliById] START >>>>>>>>>>");
        List<ImSimrsPelayananEntity> pelayananEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pelayanan", id);
        hsCriteria.put("flag", "Y");

        try {
            pelayananEntities = pelayananDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getPoliById] ERROR when get data poli by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getPoliById] ERROR when get data poli by criteria. ", e);
        }

        if (!pelayananEntities.isEmpty() && pelayananEntities.size() > 0) {
            return pelayananEntities.get(0);
        }

        logger.info("[ObatPoliBoImpl.getPoliById] END <<<<<<<<<<");
        return null;
    }

    private List<ImtSimrsTransaksiObatDetailEntity> getListEntityObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getEntityObatDetailById] START >>>>>>>>>>");
        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();

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
            obatDetailEntities = obatDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getListEntityObatDetail] ERROR when get data obat detail entity by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityObatDetail] ERROR when get data obat detail entity by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.getEntityObatDetailById] END <<<<<<<<<<");
        return obatDetailEntities;
    }

    @Override
    public CheckObatResponse checkObatStockLama(String idPabrik, String branchId) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.checkObatStockLama] START >>>>>>>>>>");

        CheckObatResponse response = new CheckObatResponse();

        Map hsCriteria = new HashMap();

        hsCriteria.put("branch_id", branchId);
        hsCriteria.put("id_pabrik", idPabrik);
        hsCriteria.put("flag", "Y");

        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();
        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.checkObatStockLama] ERROR when get data obat by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.checkObatStockLama] ERROR when get data obat by criteria.");
        }

        if (obatEntities.size() > 1) {
            ImSimrsObatEntity obatEntity = obatEntities.get(0);
            response.setStatus("error");
            response.setMessage("Silahkan dihabiskan dulu stok obat lama, karena obat yang dipilih terdapat berubahan bentuk..!");
        } else {
            response.setStatus("success");
            response.setMessage("Silahkan dilanjutkan..!");
        }

        logger.info("[ObatPoliBoImpl.checkObatStockLama] END <<<<<<<<<<");
        return response;
    }

    @Override
    public List<TransaksiObatDetail> getListTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.checkObatStockLama] START >>>>>>>>>>");

        List<TransaksiObatDetail> obatDetails = new ArrayList<>();

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = getListEntityObatDetail(bean);
        if (obatDetailEntities.size() > 0) {
            TransaksiObatDetail obatDetail;
            for (ImtSimrsTransaksiObatDetailEntity obatDetailEntity : obatDetailEntities) {
                obatDetail = new TransaksiObatDetail();

                ImSimrsObatEntity obatEntity = getObatById(obatDetailEntity.getIdObat());

                obatDetail.setIdTransaksiObatDetail(obatDetailEntity.getIdTransaksiObatDetail());
                obatDetail.setNamaObat(obatEntity.getNamaObat());
                obatDetail.setIdApprovalObat(obatDetailEntity.getIdApprovalObat());
                obatDetail.setIdObat(obatDetailEntity.getIdObat());
                obatDetail.setFlag(obatDetailEntity.getFlag());
                obatDetail.setAction(obatDetailEntity.getAction());
                obatDetail.setCreatedDate(obatDetailEntity.getCreatedDate());
                obatDetail.setLastUpdate(obatDetailEntity.getLastUpdate());
                obatDetail.setCreatedWho(obatDetailEntity.getCreatedWho());
                obatDetail.setLastUpdateWho(obatDetailEntity.getLastUpdateWho());
                obatDetail.setKeterangan(obatDetailEntity.getKeterangan());
                obatDetail.setQtyApprove(obatDetailEntity.getQtyApprove());
                obatDetail.setQtyBox(obatDetailEntity.getQtyBox());
                obatDetail.setQtyLembar(obatDetailEntity.getQtyLembar());
                obatDetail.setQtyBiji(obatDetailEntity.getQtyBiji());
                obatDetail.setQty(obatDetailEntity.getQty());
                obatDetail.setLembarPerBox(obatDetailEntity.getLembarPerBox());
                obatDetail.setBijiPerLembar(obatDetailEntity.getBijiPerLembar());
                obatDetail.setAverageHargaBox(obatDetailEntity.getAverageHargaBox());
                obatDetail.setAverageHargaLembar(obatDetailEntity.getAverageHargaLembar());
                obatDetail.setAverageHargaBiji(obatDetailEntity.getAverageHargaBiji());
                obatDetail.setFlagDiterima(obatDetailEntity.getFlagDiterima());
                obatDetail.setJenisSatuan(obatDetailEntity.getJenisSatuan());
                obatDetail.setIdPabrik(obatDetailEntity.getIdPabrik());
                obatDetail.setMerek(obatDetailEntity.getMrek());
                obatDetails.add(obatDetail);
            }
        }

        logger.info("[ObatPoliBoImpl.checkObatStockLama] END <<<<<<<<<<");
        return obatDetails;
    }

    @Override
    public void saveVerifikasiObat(List<Obat> obatList) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.saveVerifikasiObat] START >>>>>>>>>>");

        for (Obat obat : obatList) {

            TransaksiObatBatch batch = new TransaksiObatBatch();
            batch.setIdBarang(obat.getIdBarang());
            batch.setIdTransaksiObatDetail(obat.getIdTransaksiDetail());

            List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = getListEntityBatchByCriteria(batch);
            MtSimrsTransaksiObatDetailBatchEntity batchEntity = new MtSimrsTransaksiObatDetailBatchEntity();
            if (batchEntities.size() > 0) {
                batchEntity = batchEntities.get(0);
                batchEntity.setQtyApprove(obat.getQtyApprove());
                batchEntity.setAction("U");
                batchEntity.setLastUpdate(obat.getLastUpdate());
                batchEntity.setLastUpdateWho(obat.getLastUpdateWho());

                try {
                    batchDao.updateAndSave(batchEntity);
                } catch (HibernateException e) {
                    logger.error("[PermintaanResepBoImpl.checkObatStockLama] ERROR when update batch. ", e);
                    throw new GeneralBOException("[PermintaanResepBoImpl.checkObatStockLama] ERROR when update batch.");
                }
            } else {
                batchEntity.setIdBarang(obat.getIdBarang());
                batchEntity.setIdTransaksiObatDetail(obat.getIdTransaksiDetail());
                batchEntity.setId(new BigInteger(batchDao.getNextId()));
                batchEntity.setApproveFlag("Y");
                batchEntity.setStatus("Y");
                batchEntity.setQtyApprove(obat.getQtyApprove());
                batchEntity.setJenisSatuan(obat.getJenisSatuan());
                batchEntity.setNoBatch(1);
                batchEntity.setFlag("Y");
                batchEntity.setAction("U");
                batchEntity.setLastUpdate(obat.getLastUpdate());
                batchEntity.setLastUpdateWho(obat.getLastUpdateWho());
                batchEntity.setCreatedDate(obat.getCreatedDate());
                batchEntity.setCreatedWho(obat.getCreatedWho());
                batchEntity.setExpiredDate(obat.getExpiredDate());

                try {
                    batchDao.addAndSave(batchEntity);
                } catch (HibernateException e) {
                    logger.error("[PermintaanResepBoImpl.checkObatStockLama] ERROR when insert batch. ", e);
                    throw new GeneralBOException("[PermintaanResepBoImpl.checkObatStockLama] ERROR when insert batch.");
                }
            }
        }

        logger.info("[ObatPoliBoImpl.saveVerifikasiObat] END <<<<<<<<<<");
    }

    @Override
    public List<PermintaanObatPoli> getListObatDetailRequest(PermintaanObatPoli bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListObatDetailRequest] START >>>>>>>>>>");
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        if (bean != null) {
            try {
                permintaanObatPoliList = permintaanObatPoliDao.getListObatDetailRequest(bean);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.getListObatDetailRequest] ERROR when searc detail permintaan obat ke gudang. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.getListObatDetailRequest] ERROR when searc  permintaan obat ke gudang. ", e);
            }
        }
        logger.info("[ObatPoliBoImpl.getListObatDetailRequest] END >>>>>>>>>>");
        return permintaanObatPoliList;
    }

    private List<MtSimrsTransaksiObatDetailBatchEntity> getListEntityBatchByCriteria(TransaksiObatBatch bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.saveVerifikasiObat] START >>>>>>>>>>");

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
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.getListEntityBatchByCriteria] ERROR when get by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityBatchByCriteria] ERROR when get by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.saveVerifikasiObat] END <<<<<<<<<<");
        return batchEntities;
    }

    @Override
    public List<TransaksiObatDetail> getListObatTelahDiterima(String idPermintaan) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.saveVerifikasiObat] START >>>>>>>>>>");

        List<TransaksiObatDetail> obatDetails = new ArrayList<>();
        try {
            obatDetails = permintaanObatPoliDao.getListOldPermintaan(idPermintaan);
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.getListObatTelahDiterima] ERROR when get by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListObatTelahDiterima] ERROR when get by criteria. ", e);
        }

        logger.info("[ObatPoliBoImpl.getListObatTelahDiterima] END <<<<<<<<<<");
        return obatDetails;
    }

    @Override
    public List<ObatPoli> getListObatPoliGroup(String idPelayanan, String branchId) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.getListObatPoliGroup] START >>>>>>>>>>");

        List<String> listIdObat = new ArrayList<>();
        try {
            listIdObat = obatPoliDao.getIdObatGroup(idPelayanan, branchId);
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.getListObatPoliGroup] ERROR when get by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getListObatPoliGroup] ERROR when get by criteria. ", e);
        }

        List<ObatPoli> obatPoliList = new ArrayList<>();
        if (listIdObat.size() > 0){
            ObatPoli obatPoli;
            for (String idObat : listIdObat){
                obatPoli = new ObatPoli();
                obatPoli.setIdObat(idObat);

                Obat obat = new Obat();
                try {
                    obat = obatDao.getLastIdSeqObat(idObat);
                } catch (HibernateException e){
                    logger.error("[PermintaanResepBoImpl.getListObatPoliGroup] ERROR when get by criteria. ", e);
                    throw new GeneralBOException("[PermintaanResepBoImpl.getListObatPoliGroup] ERROR when get by criteria. ", e);
                }

                List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

                Map hsCriteria = new HashMap();
                hsCriteria.put("id_seq_obat", obat.getIdSeqObat());

                try {
                    obatEntities = obatDao.getByCriteria(hsCriteria);
                } catch (HibernateException e){
                    logger.error("[PermintaanResepBoImpl.getListObatPoliGroup] ERROR when get by criteria. ", e);
                    throw new GeneralBOException("[PermintaanResepBoImpl.getListObatPoliGroup] ERROR when get by criteria. ", e);
                }

                if (obatEntities.size() > 0){
                    for (ImSimrsObatEntity obatEntity : obatEntities){
                        obatPoli.setNamaObat(obatEntity.getNamaObat());
                    }
                }

                ObatPoli sumObatPoli = new ObatPoli();
                try {
                    sumObatPoli = obatPoliDao.getSumStockObatPoliById(idObat, idPelayanan);
                } catch (HibernateException e){
                    logger.error("[PermintaanResepBoImpl.getListObatPoliGroup] ERROR when get by sum criteria. ", e);
                    throw new GeneralBOException("[PermintaanResepBoImpl.getListObatPoliGroup] ERROR when get by sum criteria. ", e);
                }

                if (sumObatPoli.getIdObat() != null){
                    obatPoli.setQtyBox(sumObatPoli.getQtyBox());
                    obatPoli.setQtyLembar(sumObatPoli.getQtyLembar());
                    obatPoli.setQtyBiji(sumObatPoli.getQtyBiji());
                }

                obatPoliList.add(obatPoli);
            }
        }

        logger.info("[ObatPoliBoImpl.getListObatPoliGroup] END <<<<<<<<<<");
        return obatPoliList;
    }

    // list method seq

    private String getNextPermintaanObatId() throws GeneralBOException {
        String id = "";
        try {
            id = permintaanObatPoliDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getNextPermintaanObatId] ERROR when get next seq. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getNextPermintaanObatId] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextApprovalObatId() throws GeneralBOException {
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getNextApprovalObatId] ERROR when get next seq. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getNextApprovalObatId] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    private String getNextTransaksiObatDetail() throws GeneralBOException {
        String id = "";
        try {
            id = obatDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PermintaanResepBoImpl.getNextTransaksiObatDetail] ERROR when get next seq. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getNextTransaksiObatDetail] ERRO Rwhen get next seq. ", e);
        }
        return id;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setPermintaanObatPoliDao(PermintaanObatPoliDao permintaanObatPoliDao) {
        this.permintaanObatPoliDao = permintaanObatPoliDao;
    }

    public void setObatPoliDao(ObatPoliDao obatPoliDao) {
        this.obatPoliDao = obatPoliDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setObatDetailDao(TransaksiObatDetailDao obatDetailDao) {
        this.obatDetailDao = obatDetailDao;
    }

    public void setBatchDao(TransaksiObatDetailBatchDao batchDao) {
        this.batchDao = batchDao;
    }
}
