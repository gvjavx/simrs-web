package com.neurix.simrs.transaksi.obatpoli.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.obatpoli.dao.PermintaanObatPoliDao;
import com.neurix.simrs.transaksi.obatpoli.model.*;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsApprovalTransaksiObatEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
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
                    obatPoli.setIdObat(obatPoliEntity.getPrimaryKey().getIdObat());
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

                    ImSimrsObatEntity obatEntity = getObatById(obatPoliEntity.getPrimaryKey().getIdObat());

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
                obatDetailEntity.setKeterangan("Permintaan Obat");

                try {
                    obatDetailDao.addAndSave(obatDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[PermintaanResepBoImpl.saveAddWithRequest]  ERROR when insert into transaksi obat detail. ", e);
                    throw new GeneralBOException("[PermintaanResepBoImpl.saveAddWithRequest]  ERROR when insert into transaksi obat detail. ", e);
                }

                //cek in table obat poli
                ObatPoli obatPoli = new ObatPoli();
                obatPoli.setIdObat(obatListDetail.getIdObat());
                obatPoli.setIdPelayanan(bean.getIdPelayanan());
                obatPoli.setBranchId(bean.getBranchId());
                MtSimrsObatPoliEntity obatPoliEntityList = getObaPolitById(obatPoli);

                if (obatPoliEntityList != null) {

                    //update obat poli
                    obatPoliEntityList.setAction("U");
                    obatPoliEntityList.setLastUpdate(bean.getLastUpdate());
                    obatPoliEntityList.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        obatPoliDao.updateAndSave(obatPoliEntityList);
                    } catch (HibernateException e) {
                        logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                    }

                } else {

                    // save to table obat poli
                    MtSimrsObatPoliEntity newObatPoli = new MtSimrsObatPoliEntity();
                    ObatPoliPk obatPoliPk = new ObatPoliPk();
                    obatPoliPk.setIdObat(obatListDetail.getIdObat());
                    obatPoliPk.setIdPelayanan(bean.getIdPelayanan());
                    newObatPoli.setBranchId(bean.getBranchId());
                    newObatPoli.setPrimaryKey(obatPoliPk);
                    newObatPoli.setQty(new BigInteger(String.valueOf(0)));
                    newObatPoli.setFlag("Y");
                    newObatPoli.setAction("C");
                    newObatPoli.setLastUpdate(bean.getCreatedDate());
                    newObatPoli.setLastUpdateWho(bean.getCreatedWho());
                    newObatPoli.setCreatedDate(bean.getCreatedDate());
                    newObatPoli.setCreatedWho(bean.getCreatedWho());

                    try {
                        obatPoliDao.addAndSave(newObatPoli);
                    } catch (HibernateException e) {
                        logger.error("[ObatPoliBoImpl.saveAdd] ERROR when insert into obat poli. ", e);
                        throw new GeneralBOException("[ObatPoliBoImpl.saveAdd] ERROR when insert into obat poli. ", e);
                    }
                }
            }
        }

        logger.info("[ObatPoliBoImpl.saveAddWithRequest] END <<<<<<<<<<");
    }

    @Override
    public void saveReture(PermintaanObatPoli bean, List<PermintaanObatPoli> permintaanObatPoliList) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.saveReture] START >>>>>>>>>>");

        List<PermintaanObatPoli> permintaanEntityList = new ArrayList<>();

        if (permintaanEntityList != null && permintaanEntityList.size() > 0) {
            for (PermintaanObatPoli obatPoli : permintaanObatPoliList) {

                permintaanEntityList = getCekListEntityObatPoli(obatPoli);

                PermintaanObatPoli permintaanEntity = new PermintaanObatPoli();
                if (!permintaanEntityList.isEmpty()) {
                    permintaanEntity = permintaanEntityList.get(0);
                    if (permintaanEntity != null) {
                        PermintaanObatPoli poli = new PermintaanObatPoli();
                        poli.setIdObat(permintaanEntity.getIdObat());
                        poli.setQty(obatPoli.getQty());
                        permintaanEntityList.add(poli);
                    }
                }
            }
        }

        if (permintaanEntityList.size() > 0) {
            logger.error("[ObatPoliBoImpl.saveReture] WARNING data telah ada pada transaksi untuk transaksi reture. ");
            throw new GeneralBOException("[ObatPoliBoImpl.saveReture] WARNING data telah ada pada transaksi untuk transaksi reture. ");
        } else {
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

            // save to table detail transaksi
            for (PermintaanObatPoli permintaanObatPoli : permintaanObatPoliList){

                ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();

                id = getNextTransaksiObatDetail();
                obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
                obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                obatDetailEntity.setIdObat(permintaanObatPoli.getIdObat());
                obatDetailEntity.setQty(permintaanObatPoli.getQty());
                obatDetailEntity.setFlag("Y");
                obatDetailEntity.setAction("C");
                obatDetailEntity.setCreatedDate(bean.getCreatedDate());
                obatDetailEntity.setCreatedWho(bean.getCreatedWho());
                obatDetailEntity.setLastUpdate(bean.getCreatedDate());
                obatDetailEntity.setLastUpdateWho(bean.getCreatedWho());
                obatDetailEntity.setKeterangan("Permintaan Obat");
                try {
                    obatDetailDao.addAndSave(obatDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[PermintaanResepBoImpl.saveAddWithRequest]  ERROR when insert into transaksi obat detail. ", e);
                    throw new GeneralBOException("[PermintaanResepBoImpl.saveAddWithRequest]  ERROR when insert into transaksi obat detail. ", e);
                }

                ObatPoli obatPoli = new ObatPoli();
                obatPoli.setIdObat(permintaanObatPoli.getIdObat());
                obatPoli.setIdPelayanan(bean.getIdPelayanan());
                obatPoli.setBranchId(bean.getBranchId());
                MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);

                if (obatPoliEntity != null) {

                    BigInteger jmlh = obatPoliEntity.getQty().subtract(permintaanObatPoli.getQty());
                    obatPoliEntity.setAction("U");
                    obatPoliEntity.setQty(jmlh);
                    obatPoliEntity.setLastUpdate(bean.getLastUpdate());
                    obatPoliEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        obatPoliDao.updateAndSave(obatPoliEntity);
                    } catch (HibernateException e) {
                        logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                    }
                }

            }
        }
        logger.info("[ObatPoliBoImpl.saveReture] END <<<<<<<<<<");
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

                        TransaksiObatDetail transaksiObatDetail1 = new TransaksiObatDetail();
                        transaksiObatDetail.setIdApprovalObat(approvalTransaksiObatEntity.getIdApprovalObat());
                        transaksiObatDetail.setFlag("Y");
                        transaksiObatDetail.setIdObat(transaksiObatDetail.getIdObat());

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
                                ObatPoli obatPoli = new ObatPoli();
                                obatPoli.setIdObat(obatDetailEntity.getIdObat());
                                obatPoli.setIdPelayanan(bean.getTujuanPelayanan());
                                obatPoli.setBranchId(bean.getBranchId());
                                MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);
                                if (obatPoliEntity != null) {

                                    BigInteger jmlh = obatPoliEntity.getQty().subtract(transaksiObatDetail.getQtyApprove());
                                    obatPoliEntity.setAction("U");
                                    obatPoliEntity.setQty(jmlh);
                                    obatPoliEntity.setLastUpdate(bean.getLastUpdate());
                                    obatPoliEntity.setLastUpdateWho(bean.getLastUpdateWho());

                                    try {
                                        obatPoliDao.updateAndSave(obatPoliEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                                    }
                                }

                            }else {

                                obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                                obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                                updatePerhitunganStockGudang(obatDetailEntity);
                            }
                        }
                    }
                }
            }
        }
        logger.info("[ObatPoliBoImpl.saveApproveRequest] END <<<<<<<<<<");
    }

    private void updatePerhitunganStockGudang(ImtSimrsTransaksiObatDetailEntity transaksiObatDetail){

        ImSimrsObatEntity obatEntity = getObatById(transaksiObatDetail.getIdObat());
        if (obatEntity != null) {

            // BigInteger jmlh = obatEntity.getQty().subtract(transaksiObatDetail.getQtyApprove());

            if ("box".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())){
                BigInteger jml = obatEntity.getQtyBox().subtract(transaksiObatDetail.getQtyApprove());
                obatEntity.setQtyBox(jml);
            }
            if ("lembar".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())){

                // jika lembar permintaan lebih kecil dari pada jumlah lembar dalam stock maka langsung mengurangi stock lembar
                if (obatEntity.getQtyLembar().compareTo(transaksiObatDetail.getQtyApprove()) == 1){
                    BigInteger jml = obatEntity.getQtyLembar().subtract(transaksiObatDetail.getQtyApprove());
                    obatEntity.setQtyLembar(jml);
                } else {

                    // jika lembar permintaan lebih besar dari pada jumlah lembar dalam stock maka jumlah permintaan
                    // dikurangin jumlah stock sehingga menghasilkan sisa lembar

                    BigInteger sisaLembar = transaksiObatDetail.getQtyApprove().subtract(obatEntity.getQtyLembar());

                    // cek apakah stock box lebih dari 0
                    if (obatEntity.getQtyBox().compareTo(new BigInteger(String.valueOf(0))) == 1){

                        // jika jumlah lembar dalam box lebih besar dari sisa pengurangan permintaan lembar
                        // maka qty box dikurangin 1
                        if (obatEntity.getLembarPerBox().compareTo(sisaLembar) == 1){
                            obatEntity.setQtyBox(obatEntity.getQtyBox().subtract( new BigInteger(String.valueOf(1))));

                            // sisa dari pengurangan pada box menjadi qty lembar
                            BigInteger sisaLembarPadaBox = obatEntity.getLembarPerBox().subtract(sisaLembar);
                            obatEntity.setQtyLembar(sisaLembarPadaBox);
                        } else {

                            // konfersi dari satuan box ke lembar pada stock
                            BigInteger boxToLembar = obatEntity.getQtyBox().multiply(obatEntity.getLembarPerBox());

                            // jumlah seluruh lembar pada stock dikurangi dengan sisaLembar
                            BigInteger jmlDikuranginSisaLembar = boxToLembar.subtract(sisaLembar);

                            // jika sisa stock sluruh lembar setelah pengurangan lebih besar dari jumlah lembar per box
                            if (jmlDikuranginSisaLembar.compareTo(obatEntity.getLembarPerBox()) == 1){
                                // jumlah hasil pengurangan dibagi dengan lembar perbox untuk mendapatkan
                                // jumlah box yang tersisa
                                BigInteger jmlBox = jmlDikuranginSisaLembar.divide(obatEntity.getLembarPerBox());

                                // sisa pembagian akan dimasukan pada qty lembar
                                BigInteger jmlLembarSisa = jmlDikuranginSisaLembar.mod(obatEntity.getLembarPerBox());

                                obatEntity.setQtyBox(jmlBox);
                                obatEntity.setQtyLembar(jmlLembarSisa);
                            } else {
                                // jika sisa stock sluruh lembar setelah pengurangan lebih kecil dari jumlah lembar per box
                                // maka sisa pengurangan menjadi stock untuk lembar
                                // stock box menjadi kosong karna lebih kecil dari jumlah jumlah lembar per box
                                obatEntity.setQtyBox(new BigInteger(String.valueOf(0)));
                                obatEntity.setQtyBox(jmlDikuranginSisaLembar);
                            }
                        }
                    }
                }
            }
            if ("biji".equalsIgnoreCase(transaksiObatDetail.getJenisSatuan())){

                // jika jumlah stock obat bijian lebih besar atau sama dari jumlah permintaan
                // maka hanya perlu mengurangi stock biji
                if (obatEntity.getQtyBiji().compareTo(transaksiObatDetail.getQtyApprove()) == 1 || obatEntity.getQtyBiji().compareTo(transaksiObatDetail.getQtyApprove()) == 0){
                    obatEntity.setQtyBiji(obatEntity.getQtyBiji().subtract(transaksiObatDetail.getQtyApprove()));
                } else {

                    // jika jumlah stock obat bijian lebih besar dari jumlah permintaan
                    // maka dilakukan pengurangan perlembarnya

                    // pengurangan jumlah permintaan dengan jumlah stock untuk mendapatkan sisa biji obat
                    BigInteger sisaBiji = transaksiObatDetail.getQtyApprove().subtract(obatEntity.getQtyBiji());

                    // konfersi dari lembar ke biji
                    BigInteger lembarToBiji = obatEntity.getQtyLembar().multiply(obatEntity.getBijiPerLembar());

                    // jika permintaan lebih besar dari pada seluruh penjumlahan biji per lembar
                    // maka dilakukan pengurangan mulai dari box
                    if (transaksiObatDetail.getQtyApprove().compareTo(lembarToBiji) == 1){



                    } else {

                        // jika
                        if (obatEntity.getBijiPerLembar().compareTo(sisaBiji) == 1){
                            obatEntity.setQtyLembar(obatEntity.getQtyLembar().subtract(new BigInteger(String.valueOf(1))));

                            BigInteger sisBijiPadaLembar = obatEntity.getBijiPerLembar().subtract(sisaBiji);
                            obatEntity.setQtyBiji(sisBijiPadaLembar);

                        } else {


                            BigInteger jmlDikurangiSisaBiji = lembarToBiji.subtract(sisaBiji);

                            // jika sisa stock sluruh biji setelah pengurangan lebih besar dari jumlah biji per lembar
                            if (jmlDikurangiSisaBiji.compareTo(obatEntity.getBijiPerLembar()) == 1){
                                BigInteger jmlLembar = jmlDikurangiSisaBiji.divide(obatEntity.getBijiPerLembar());
                                BigInteger jmlBiji = jmlDikurangiSisaBiji.mod(obatEntity.getBijiPerLembar());

                                obatEntity.setQtyLembar(jmlLembar);
                                obatEntity.setQtyBiji(jmlBiji);
                            } else {
                                // jika sisa stock sluruh biji setelah pengurangan lebih kecil dari jumlah biji per lembar
                                // maka sisa pengurangan menjadi stock untuk biji
                                // stock menjadi kosong karna lebih kecil dari jumlah biji per lembar
                                obatEntity.setQtyLembar(new BigInteger(String.valueOf(0)));
                                obatEntity.setQtyBiji(jmlDikurangiSisaBiji);
                            }
                        }
                    }
                }
            }

            obatEntity.setAction("U");
            obatEntity.setLastUpdate(transaksiObatDetail.getLastUpdate());
            obatEntity.setLastUpdateWho(transaksiObatDetail.getLastUpdateWho());

            try {
                obatDao.updateAndSave(obatEntity);
            } catch (HibernateException e) {
                logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
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
                                ObatPoli obatPoli = new ObatPoli();
                                obatPoli.setIdObat(obatDetailEntity.getIdObat());
                                obatPoli.setIdPelayanan(bean.getTujuanPelayanan());
                                obatPoli.setBranchId(bean.getBranchId());
                                MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);
                                if (obatPoliEntity != null) {

                                    BigInteger jmlh = obatPoliEntity.getQty().add(obatDetailEntity.getQty());
                                    obatPoliEntity.setAction("U");
                                    obatPoliEntity.setQty(jmlh);
                                    obatPoliEntity.setLastUpdate(bean.getLastUpdate());
                                    obatPoliEntity.setLastUpdateWho(bean.getLastUpdateWho());

                                    try {
                                        obatPoliDao.updateAndSave(obatPoliEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update master obat. ", e);
                                    }
                                }
                            } else {
                                // updating qty obat master after konfirmasi
                                ImSimrsObatEntity obatEntity = getObatById(obatDetailEntity.getIdObat());
                                if (obatEntity != null) {

                                    BigInteger jmlh = obatEntity.getQty().add(obatDetailEntity.getQty());

                                    obatEntity.setQty(jmlh);
                                    obatEntity.setAction("U");
                                    obatEntity.setLastUpdate(bean.getLastUpdate());
                                    obatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                                    try {
                                        obatDao.updateAndSave(obatEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[ObatPoliBoImpl.saveApproveReture] ERROR when update master obat. ", e);
                                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveReture] ERROR when update master obat. ", e);
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

            TransaksiObatDetail obatDetail = new TransaksiObatDetail();
            obatDetail.setIdApprovalObat(bean.getIdApprovalObat());
            obatDetail.setFlag("Y");
            List<ImtSimrsTransaksiObatDetailEntity> obatDetailList = getListEntityObatDetail(obatDetail);

            if (!obatDetailList.isEmpty()) {

                for (ImtSimrsTransaksiObatDetailEntity entity : obatDetailList) {
                    entity.setFlag("N");
                    entity.setAction(bean.getAction());
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        obatDetailDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update detail obat. ", e);
                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update detail obat. ", e);
                    }
                }
            }

            for (TransaksiObatDetail entity : listObatDetail) {

                ObatPoli obatPoli = new ObatPoli();
                obatPoli.setIdObat(entity.getIdObat());
                obatPoli.setIdPelayanan(bean.getIdPelayanan());
                obatPoli.setBranchId(bean.getBranchId());
                List<MtSimrsObatPoliEntity> obatPoliEntityList = getListEntityObatPoli(obatPoli);

                if (obatPoliEntityList.size() > 0) {

                    MtSimrsObatPoliEntity entityObatPoli = obatPoliEntityList.get(0);
                    ImSimrsObatEntity obatEntity = getObatById(entity.getIdObat());

                    BigInteger jmlApprove = entity.getQtyApprove();

                    if ("box".equalsIgnoreCase(entity.getJenisSatuan())){
                        entityObatPoli.setQtyBox(entity.getQtyBox().add(jmlApprove));
                    }

                    if ("lembar".equalsIgnoreCase(entity.getJenisSatuan()) && !obatEntity.getLembarPerBox().equals(new BigInteger(String.valueOf(0)))){

                        if (jmlApprove.compareTo(obatEntity.getLembarPerBox()) == 1){

                            BigInteger jmlBox = jmlApprove.divide(obatEntity.getLembarPerBox());
                            BigInteger jmlLembar = jmlApprove.mod(obatEntity.getLembarPerBox());

                            entityObatPoli.setQtyBox(jmlBox);
                            entityObatPoli.setQtyLembar(jmlLembar);

                        } else {
                            BigInteger jmlLembar = jmlApprove.add(entityObatPoli.getQtyLembar());
                            entityObatPoli.setQtyLembar(jmlLembar);
                        }
                    }

                    if ("biji".equalsIgnoreCase(entity.getJenisSatuan()) && !obatEntity.getBijiPerLembar().equals(new BigInteger(String.valueOf(0)))){
                        entityObatPoli.setQtyBiji(entity.getQtyBiji());
                    }

                    //entityObatPoli.setQty(entityObatPoli.getQty().add(entity.getQtyApprove()));
                    entityObatPoli.setLastUpdate(bean.getLastUpdate());
                    entityObatPoli.setLastUpdateWho(bean.getLastUpdateWho());
                    entityObatPoli.setAction(bean.getAction());

                    try {
                        obatPoliDao.updateAndSave(entityObatPoli);
                    } catch (HibernateException e) {
                        logger.error("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update obat poli. ", e);
                        throw new GeneralBOException("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update obat poli. ", e);
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

    private List<PermintaanObatPoli> getCekListEntityObatPoli(PermintaanObatPoli bean) throws GeneralBOException {
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
        hsCriteria.put("asc","Y");

        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();
        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PermintaanResepBoImpl.checkObatStockLama] ERROR when get data obat by criteria. ", e);
            throw new GeneralBOException("[PermintaanResepBoImpl.checkObatStockLama] ERROR when get data obat by criteria.");
        }

        if (obatEntities.size() > 1){
            ImSimrsObatEntity obatEntity = obatEntities.get(0);


        }

        logger.info("[ObatPoliBoImpl.checkObatStockLama] END <<<<<<<<<<");
        return response;
    }

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
}
