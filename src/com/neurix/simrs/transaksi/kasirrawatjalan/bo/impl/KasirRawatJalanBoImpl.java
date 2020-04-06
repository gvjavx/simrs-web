package com.neurix.simrs.transaksi.kasirrawatjalan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.dao.FpkDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.Fpk;
import com.neurix.simrs.transaksi.checkup.model.ItSImrsFpkEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KasirRawatJalanBoImpl implements KasirRawatJalanBo {

    private RiwayatTindakanDao riwayatTindakanDao;
    private RawatInapDao rawatInapDao;
    private UangMukaDao uangMukaDao;
    private CheckupDetailDao checkupDetailDao;
    private FpkDao fpkDao;

    public void setFpkDao(FpkDao fpkDao) {
        this.fpkDao = fpkDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setUangMukaDao(UangMukaDao uangMukaDao) {
        this.uangMukaDao = uangMukaDao;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    private static transient Logger logger = Logger.getLogger(KasirRawatJalanBoImpl.class);

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[KasirRawatJalanBoImpl.getListAllTindakan] START process <<<");
        List<RiwayatTindakan> result = new ArrayList<>();
        if(bean != null){
            try {
                result = riwayatTindakanDao.getListTindakan(bean);
            }catch (HibernateException e){
                logger.error("[KasirRawatJalanBoImpl.getListAllTindakan] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[KasirRawatJalanBoImpl.getListAllTindakan] END process <<<");
        return result;
    }

    @Override
    public List<UangMuka> getListUangMuka(UangMuka bean) throws GeneralBOException {
        List<UangMuka> uangMukaList = new ArrayList<>();

        if(bean != null){

            Map hsCriterian = new HashMap();

            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriterian.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getStatusBayar() != null && !"".equalsIgnoreCase(bean.getStatusBayar())){
                hsCriterian.put("status_bayar", bean.getStatusBayar());
//                hsCriterian.put("nota_not_null", "Y");
            }

            List<ItSimrsUangMukaPendaftaranEntity> entityList = new ArrayList<>();
            try {
                entityList = uangMukaDao.getByCriteria(hsCriterian);
            }catch (HibernateException e){
                logger.error("Found Error when search uang muka");
            }

            if(entityList.size() > 0){
                UangMuka uangMuka;
                for (ItSimrsUangMukaPendaftaranEntity entity :entityList){
                    uangMuka = new UangMuka();
                    uangMuka.setIdDetailCheckup(entity.getIdDetailCheckup());
                    uangMuka.setId(entity.getId());
                    uangMuka.setJumlah(entity.getJumlah());
                    uangMuka.setDibayar(entity.getJumlahDibayar());
                    uangMuka.setCreatedDate(entity.getCreatedDate());
                    uangMuka.setCreatedWho(entity.getCreatedWho());
                    uangMuka.setFlag(entity.getFlag());
                    uangMuka.setStatusBayar(entity.getStatusBayar());
                    uangMuka.setNoNota(entity.getNoNota());
                    String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(entity.getCreatedDate());
                    uangMuka.setStCreatedDate(formatDate);
                    uangMuka.setStDate(entity.getCreatedDate().toString());
                    uangMukaList.add(uangMuka);
                }
            }
        }
        return uangMukaList;
    }

    @Override
    public void updateNotaUangMukaById(UangMuka bean) throws GeneralBOException {
        Map hsCriteria = new HashMap();
        hsCriteria.put("id", bean.getId());

        List<ItSimrsUangMukaPendaftaranEntity> uangMukaPendaftaranEntities = new ArrayList<>();
        try {
            uangMukaPendaftaranEntities = uangMukaDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error ", e);
            new GeneralBOException("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error  ", e);
        }

        if (uangMukaPendaftaranEntities.size() > 0){
            for (ItSimrsUangMukaPendaftaranEntity pendaftaranEntity : uangMukaPendaftaranEntities){
                pendaftaranEntity.setStatusBayar("Y");
                pendaftaranEntity.setAction("U");
                pendaftaranEntity.setJumlahDibayar(bean.getDibayar());
                pendaftaranEntity.setLastUpdate(bean.getLastUpdate());
                pendaftaranEntity.setLastUpdateWho(bean.getLastUpdateWho());
                pendaftaranEntity.setNoJurnal(bean.getNoJurnal());

                try {
                    uangMukaDao.updateAndSave(pendaftaranEntity);
                } catch (HibernateException e){
                    logger.error("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error ", e);
                    new GeneralBOException("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error  ", e);
                }
            }
        }

    }

    @Override
    public List<HeaderDetailCheckup> getSearchFPK(HeaderDetailCheckup bean) throws GeneralBOException {

        List<HeaderDetailCheckup> list = new ArrayList<>();
        if(bean != null){

            try {
                list = checkupDetailDao.getSearchFPK(bean);
            }catch (HibernateException e){
                logger.error("Found Error "+e.getMessage());
            }
        }
        return list;
    }

    @Override
    public CrudResponse saveNoFPK(List<Fpk> listData) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(listData != null){

            for (Fpk list: listData){
                ItSImrsFpkEntity entity = new ItSImrsFpkEntity();
                entity.setIdFpk("FPK"+getNextIdFpk());
                entity.setNoSep(list.getNoSep());
                entity.setNoFpk(list.getNoFpk());
                entity.setIdDetailCheckup(list.getIdDetailCheckup());
                entity.setTanggalFpk(list.getTanggalFpk());
                entity.setFlag("Y");
                entity.setAction("C");
                entity.setCreatedWho(CommonUtil.userLogin());
                entity.setLastUpdateWho(CommonUtil.userLogin());
                entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));

                try {
                    fpkDao.addAndSave(entity);
                    response.setStatus("success");
                }catch (HibernateException e){
                    logger.error("Found Error");
                    response.setStatus("error");
                    response.setMsg("Found Error "+e);
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse pembayaranFPK(List<Fpk> listData) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(listData != null){

            for (Fpk list: listData){
                ItSImrsFpkEntity entity = new ItSImrsFpkEntity();

                try {
                    entity = fpkDao.getById("idFpk", list.getIdFpk());
                }catch (HibernateException e){
                    logger.error("Found Error");
                    response.setStatus("error");
                    response.setMsg("Found Errror "+e);
                }

                if(entity != null){

                    entity.setStatusBayar("Y");
                    entity.setNoSlip(list.getNoSlip());
                    entity.setAction("U");
                    entity.setLastUpdateWho(CommonUtil.userLogin());
                    entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));

                    try {
                        fpkDao.updateAndSave(entity);
                        response.setStatus("success");
                    }catch (HibernateException e){
                        logger.error("Found Error");
                        response.setStatus("error");
                        response.setMsg("Found Error "+e);
                    }
                }
            }
        }
        return response;
    }

    @Override
    public CheckResponse saveRefund(String id) throws GeneralBOException {
        CheckResponse response = new CheckResponse();

        ItSimrsUangMukaPendaftaranEntity entity = new ItSimrsUangMukaPendaftaranEntity();

        try {

            entity = uangMukaDao.getById("id", id);

            if(entity != null){

                entity.setFlagRefund("Y");
                entity.setLastUpdateWho(CommonUtil.userLogin());
                entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));

                try {
                    uangMukaDao.updateAndSave(entity);
                    response.setStatus("success");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMessage("Found Error "+e.getMessage());
                    logger.error("Found error "+e.getMessage());
                }
            }

        }catch (HibernateException e){
            response.setStatus("error");
            response.setMessage("Found Error "+e.getMessage());
            logger.error("Found error "+e.getMessage());
        }

        return response;
    }

    private String getNextIdFpk() {
        String id = "";
        try {
            id = fpkDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdRiwayatTindakan] ERROR When create sequences", e);
        }
        return id;
    }

    @Override
    public List<ItSimrsHeaderDetailCheckupEntity> getSearchCheckupBySep(String noSep) throws GeneralBOException {
        List<ItSimrsHeaderDetailCheckupEntity> list = new ArrayList<>();
        List<ItSimrsHeaderDetailCheckupEntity> listOfResult = new ArrayList<>();
        try {
            list = checkupDetailDao.getSearchCheckupBySep(noSep);
        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
        }

        for (ItSimrsHeaderDetailCheckupEntity headerDetailCheckupEntity : list){
            Map hscriteria = new HashMap();
            hscriteria.put("no_sep",noSep);
            hscriteria.put("flag","Y");
            List<ItSImrsFpkEntity> fpkEntityList = fpkDao.getByCriteria(hscriteria);

            headerDetailCheckupEntity.setStatusBayar(null);
            for (ItSImrsFpkEntity fpkEntity : fpkEntityList){
                if (("Y").equalsIgnoreCase(fpkEntity.getStatusBayar())){
                    headerDetailCheckupEntity.setStatusBayar("Y");
                }
            }
            listOfResult.add(headerDetailCheckupEntity);
        }
        return listOfResult;
    }
}
