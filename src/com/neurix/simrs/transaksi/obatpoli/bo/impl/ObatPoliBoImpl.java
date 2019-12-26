package com.neurix.simrs.transaksi.obatpoli.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.obatpoli.dao.PermintaanObatPoliDao;
import com.neurix.simrs.transaksi.obatpoli.model.*;
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
                    obatPoli.setQty(obatPoliEntity.getQty());
                    obatPoli.setAction(obatPoliEntity.getAction());
                    obatPoli.setCreatedDate(obatPoliEntity.getCreatedDate());
                    obatPoli.setCreatedWho(obatPoliEntity.getCreatedWho());
                    obatPoli.setLastUpdate(obatPoliEntity.getLastUpdate());
                    obatPoli.setLastUpdateWho(obatPoliEntity.getLastUpdateWho());

                    ImSimrsObatEntity obatEntity = getObatById(obatPoliEntity.getPrimaryKey().getIdObat());
                    if (obatEntity != null) {
                        obatPoli.setNamaObat(obatEntity.getNamaObat());
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

                ImSimrsObatEntity simrsObatEntity = getObatById(permintaanObatPoli.getIdObat());
                if (simrsObatEntity != null) {
                    permintaanObatPoli.setNamaObat(simrsObatEntity.getNamaObat());
                    permintaanObatPoli.setQtyGudang(simrsObatEntity.getQty());
                }

                ImSimrsPelayananEntity pelayananEntity = getPoliById(permintaanObatPoli.getIdPelayanan());
                if (pelayananEntity != null) {
                    permintaanObatPoli.setNamaPelayanan(pelayananEntity.getNamaPelayanan());
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
    public void saveRequest(PermintaanObatPoli bean, String request) throws GeneralBOException, JSONException {
        logger.info("[ObatPoliBoImpl.saveAddWithRequest] START >>>>>>>>>>");
        List<PermintaanObatPoli> obatPoliEntity = new ArrayList<>();

        if (request != null && !"".equalsIgnoreCase(request)) {
            JSONArray json = new JSONArray(request);

            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);

                PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
                permintaanObatPoli.setIdObat(obj.getString("ID"));
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

            // save to table detail transaksi

            if (request != null && !"".equalsIgnoreCase(request)) {
                JSONArray json = new JSONArray(request);
                for (int i = 0; i < json.length(); i++) {

                    JSONObject obj = json.getJSONObject(i);
                    ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();

                    id = getNextTransaksiObatDetail();
                    obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
                    obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                    obatDetailEntity.setIdObat(obj.getString("ID"));
                    obatDetailEntity.setQty(new BigInteger(obj.getString("Qty")));
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
                    obatPoli.setIdObat(obj.getString("ID"));
                    obatPoli.setIdPelayanan(bean.getIdPelayanan());
                    obatPoli.setBranchId(bean.getBranchId());
                    MtSimrsObatPoliEntity obatPoliEntityList = getObaPolitById(obatPoli);

                    if(obatPoliEntityList != null){

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

                    }else{
                        // save to table obat poli
                        MtSimrsObatPoliEntity newObatPoli = new MtSimrsObatPoliEntity();
                        ObatPoliPk obatPoliPk = new ObatPoliPk();
                        obatPoliPk.setIdObat(obj.getString("ID"));
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
        }

        logger.info("[ObatPoliBoImpl.saveAddWithRequest] END <<<<<<<<<<");
    }
//
//    @Override
//    public void saveRequest(PermintaanObatPoli bean) throws GeneralBOException {
//        logger.info("[ObatPoliBoImpl.saveRequest] START >>>>>>>>>>");
//
//        List<MtSimrsPermintaanObatPoliEntity> permintaanEntity = getListEntityPermintaanObat(bean);
//        if (permintaanEntity.size() > 0) {
//            logger.error("[ObatPoliBoImpl.saveRequest] WARNING data telah ada pada transaksi untuk transaksi request. ");
//            throw new GeneralBOException("[ObatPoliBoImpl.saveRequest] WARNING data telah ada pada transaksi untuk transaksi request. ");
//        } else {
//            // save to table approve
//            ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
//            String id = getNextApprovalObatId();
//            approvalEntity.setIdApprovalObat("INV" + id);
//            approvalEntity.setIdPelayanan(bean.getIdPelayanan());
//            approvalEntity.setBranchId(bean.getBranchId());
//            approvalEntity.setFlag("Y");
//            approvalEntity.setAction("C");
//            approvalEntity.setTipePermintaan("002");
//            approvalEntity.setLastUpdate(bean.getCreatedDate());
//            approvalEntity.setLastUpdateWho(bean.getCreatedWho());
//            approvalEntity.setCreatedDate(bean.getCreatedDate());
//            approvalEntity.setCreatedWho(bean.getCreatedWho());
//
//            try {
//                approvalTransaksiObatDao.addAndSave(approvalEntity);
//            } catch (HibernateException e) {
//                logger.error("[ObatPoliBoImpl.saveRequest] ERROR when insert into approval transaksi. ", e);
//                throw new GeneralBOException("[ObatPoliBoImpl.saveRequest] ERROR when insert into approval transaksi. ", e);
//            }
//
//            // save to table detail transaksi
//            ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
//
//            id = getNextTransaksiObatDetail();
//            obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
//            obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
//            obatDetailEntity.setIdObat(bean.getIdObat());
//            obatDetailEntity.setQty(bean.getQty());
//            obatDetailEntity.setFlag("Y");
//            obatDetailEntity.setAction("C");
//            obatDetailEntity.setCreatedDate(bean.getCreatedDate());
//            obatDetailEntity.setCreatedWho(bean.getCreatedWho());
//            obatDetailEntity.setLastUpdate(bean.getCreatedDate());
//            obatDetailEntity.setLastUpdateWho(bean.getCreatedWho());
//            obatDetailEntity.setKeterangan("Permintaan Obat");
//            try {
//                obatDetailDao.addAndSave(obatDetailEntity);
//            } catch (HibernateException e) {
//                logger.error("[PermintaanResepBoImpl.saveRequest]  ERROR when insert into transaksi obat detail. ", e);
//                throw new GeneralBOException("[PermintaanResepBoImpl.saveRequest]  ERROR when insert into transaksi obat detail. ", e);
//            }
//
//            // save to table permintaan transaksi
//            MtSimrsPermintaanObatPoliEntity newPermintaanEntity = new MtSimrsPermintaanObatPoliEntity();
//            id = getNextPermintaanObatId();
//            newPermintaanEntity.setIdPermintaanObatPoli("POP" + id);
//            newPermintaanEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
//            newPermintaanEntity.setIdObat(bean.getIdObat());
//            newPermintaanEntity.setIdPelayanan(bean.getIdPelayanan());
//            newPermintaanEntity.setBranchId(bean.getBranchId());
//            newPermintaanEntity.setQty(bean.getQty());
//            newPermintaanEntity.setFlag("Y");
//            newPermintaanEntity.setAction("C");
//            newPermintaanEntity.setLastUpdate(bean.getCreatedDate());
//            newPermintaanEntity.setLastUpdateWho(bean.getCreatedWho());
//            newPermintaanEntity.setCreatedDate(bean.getCreatedDate());
//            newPermintaanEntity.setCreatedWho(bean.getCreatedWho());
//
//            try {
//                permintaanObatPoliDao.addAndSave(newPermintaanEntity);
//            } catch (HibernateException e) {
//                logger.error("[ObatPoliBoImpl.saveRequest] ERROR when insert into permintaan obat. ", e);
//                throw new GeneralBOException("[ObatPoliBoImpl.saveRequest] ERROR when insert into permintaan obat. ", e);
//            }
//        }
//
//        logger.info("[ObatPoliBoImpl.saveRequest] END <<<<<<<<<<");
//    }

    @Override
    public void saveReture(PermintaanObatPoli bean, String reture) throws GeneralBOException, JSONException {
        logger.info("[ObatPoliBoImpl.saveReture] START >>>>>>>>>>");

//        List<MtSimrsPermintaanObatPoliEntity> permintaanEntity = getListEntityPermintaanObat(bean);
        List<PermintaanObatPoli> permintaanEntityList = new ArrayList<>();

        if (reture != null && !"".equalsIgnoreCase(reture)) {
            JSONArray json = new JSONArray(reture);

            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);

                PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
                permintaanObatPoli.setIdObat(obj.getString("ID"));
                permintaanObatPoli.setIdPelayanan(bean.getIdPelayanan());
                permintaanObatPoli.setBranchId(bean.getBranchId());
                permintaanEntityList = getCekListEntityObatPoli(permintaanObatPoli);

                PermintaanObatPoli permintaanEntity = new PermintaanObatPoli();
                if (!permintaanEntityList.isEmpty()) {
                    permintaanEntity = permintaanEntityList.get(0);
                    if (permintaanEntity != null) {
                        PermintaanObatPoli poli = new PermintaanObatPoli();
                        poli.setIdObat(permintaanEntity.getIdObat());
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
//            newPermintaanEntity.setIdObat(bean.getIdObat());
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
            if (reture != null && !"".equalsIgnoreCase(reture)) {
                JSONArray json = new JSONArray(reture);
                for (int i = 0; i < json.length(); i++) {

                    JSONObject obj = json.getJSONObject(i);
                    ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();

                    id = getNextTransaksiObatDetail();
                    obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
                    obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                    obatDetailEntity.setIdObat(obj.getString("ID"));
                    obatDetailEntity.setQty(new BigInteger(obj.getString("Qty")));
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
                    obatPoli.setIdObat(obj.getString("ID"));
                    obatPoli.setIdPelayanan(bean.getIdPelayanan());
                    obatPoli.setBranchId(bean.getBranchId());
                    MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);

                    if(obatPoliEntity != null){

                        BigInteger jmlh = obatPoliEntity.getQty().subtract(new BigInteger(obj.getString("Approve")));
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

//            List<MtSimrsObatPoliEntity> obatPoliEntityList = new ArrayList<>();
//            ObatPoli obatPoli = new ObatPoli();
//            obatPoli.setIdObat(bean.getIdObat());
//            obatPoli.setIdPelayanan(bean.getIdPelayanan());
//            obatPoli.setBranchId(bean.getBranchId());
//
//            try {
//                obatPoliEntityList = getListEntityObatPoli(obatPoli);
//            }catch (HibernateException e){
//                logger.error("[ObatPoliBoImpl.saveReture] ERROR when update stok obat poli. ",e);
//                throw new GeneralBOException("[ObatPoliBoImpl.saveReture] ERROR when update stok obat poli. ",e);
//            }

//            MtSimrsObatPoliEntity obatPoliEntity = new MtSimrsObatPoliEntity();
//
//            if(!obatPoliEntityList.isEmpty()){
//                obatPoliEntity = obatPoliEntityList.get(0);
//                if(obatPoliEntity != null){
//                    obatPoliEntity.setQty(obatPoliEntity.getQty().subtract(bean.getQty()));
//                    obatPoliEntity.setLastUpdateWho(bean.getLastUpdateWho());
//                    obatPoliEntity.setLastUpdate(bean.getLastUpdate());
//                    obatPoliEntity.setAction("U");
//
//                    try {
//                        obatPoliDao.updateAndSave(obatPoliEntity);
//                    }catch (HibernateException e){
//                        logger.error("[ObatPoliBoImpl.saveReture] ERROR when update save stok obat poli. ",e);
//                        throw new GeneralBOException("[ObatPoliBoImpl.saveReture] ERROR when update save stok obat poli. ",e);
//                    }
//                }
//
//            }
        }
        logger.info("[ObatPoliBoImpl.saveReture] END <<<<<<<<<<");
    }

    @Override
    public void saveApproveRequest(PermintaanObatPoli bean, String request, boolean isPoli) throws GeneralBOException, JSONException {
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

                    TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdApprovalObat(approvalTransaksiObatEntity.getIdApprovalObat());
                    transaksiObatDetail.setFlag("Y");
                    List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = getListEntityObatDetail(transaksiObatDetail);

                    if (!obatDetailEntities.isEmpty() && obatDetailEntities.size() > 0) {
                        for (ImtSimrsTransaksiObatDetailEntity obatDetailEntity : obatDetailEntities) {
                            if (request != null && !"".equalsIgnoreCase(request)) {
                                JSONArray json = new JSONArray(request);
                                for (int i = 0; i < json.length(); i++) {
                                    JSONObject obj = json.getJSONObject(i);
                                    if (obatDetailEntity.getIdObat().equalsIgnoreCase(obj.getString("ID"))) {

                                        obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                                        obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                        obatDetailEntity.setAction("U");
                                        obatDetailEntity.setQtyApprove(new BigInteger(obj.getString("Approve")));

                                        try {
                                            obatDetailDao.updateAndSave(obatDetailEntity);
                                        } catch (HibernateException e) {
                                            logger.error("[ObatPoliBoImpl.saveApproveRequest] ERROR when update obat Detail. ", e);
                                            throw new GeneralBOException("[ObatPoliBoImpl.saveApproveRequest] ERROR when update obat Detai. ", e);
                                        }

                                        if(isPoli){
                                            // updating qty obat poli after konfirmasi
                                            ObatPoli obatPoli = new ObatPoli();
                                            obatPoli.setIdObat(obatDetailEntity.getIdObat());
                                            obatPoli.setIdPelayanan(approvalTransaksiObatEntity.getIdPelayanan());
                                            obatPoli.setBranchId(approvalTransaksiObatEntity.getBranchId());
                                            MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);
                                            if(obatPoliEntity != null){

                                                BigInteger jmlh = obatPoliEntity.getQty().subtract(new BigInteger(obj.getString("Approve")));
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
                                        }else{
                                            // updating qty obat master after konfirmasi
                                            ImSimrsObatEntity obatEntity = getObatById(obatDetailEntity.getIdObat());
                                            if (obatEntity != null) {
                                                if (obatEntity.getIdObat().equalsIgnoreCase(obj.getString("ID"))) {

                                                    BigInteger jmlh = obatEntity.getQty().subtract(new BigInteger(obj.getString("Approve")));
                                                    obatEntity.setAction("U");
                                                    obatEntity.setQty(jmlh);
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

    @Override
    public void saveApproveReture(PermintaanObatPoli bean, boolean isPoli) throws GeneralBOException{
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

                            if(isPoli){
                                // updating qty obat poli after konfirmasi
                                ObatPoli obatPoli = new ObatPoli();
                                obatPoli.setIdObat(obatDetailEntity.getIdObat());
                                obatPoli.setIdPelayanan(approvalTransaksiObatEntity.getIdPelayanan());
                                obatPoli.setBranchId(approvalTransaksiObatEntity.getBranchId());
                                MtSimrsObatPoliEntity obatPoliEntity = getObaPolitById(obatPoli);
                                if(obatPoliEntity != null){

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
                            }else{
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
    public void saveApproveDiterima(PermintaanObatPoli bean, String request) throws GeneralBOException, JSONException {
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

            ObatPoli obatPoli = new ObatPoli();
            obatPoli.setIdObat(bean.getIdObat());
            obatPoli.setIdPelayanan(bean.getIdPelayanan());
            obatPoli.setBranchId(bean.getBranchId());
            List<MtSimrsObatPoliEntity> obatPoliEntityList = getListEntityObatPoli(obatPoli);

            if (!obatPoliEntityList.isEmpty()) {

                for (MtSimrsObatPoliEntity entityObat : obatPoliEntityList) {

                    if (request != null && !"".equalsIgnoreCase(request)) {
                        JSONArray json = new JSONArray(request);
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject obj = json.getJSONObject(i);

                            if (entityObat.getPrimaryKey().getIdObat().equalsIgnoreCase(obj.getString("ID"))) {

                                entityObat.setQty(entityObat.getQty().add(new BigInteger(obj.getString("Approve"))));
                                entityObat.setLastUpdate(bean.getLastUpdate());
                                entityObat.setLastUpdateWho(bean.getLastUpdateWho());
                                entityObat.setAction(bean.getAction());

                                try {
                                    obatPoliDao.updateAndSave(entityObat);
                                } catch (HibernateException e) {
                                    logger.error("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update obat poli. ", e);
                                    throw new GeneralBOException("[ObatPoliBoImpl.saveApproveDiterima] ERROR when update obat poli. ", e);
                                }
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
        hsCriteria.put("flag", "Y");

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
