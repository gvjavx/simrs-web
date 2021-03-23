package com.neurix.simrs.transaksi.tindakanrawat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.dao.KategoriTindakanDao;
import com.neurix.simrs.master.kategoritindakan.model.ImSimrsKategoriTindakanEntity;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.konsultasigizi.dao.KonsultasiGiziDao;
import com.neurix.simrs.transaksi.konsultasigizi.model.ItSimrsKonsultasiGiziEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class TindakanRawatBoImpl implements TindakanRawatBo {
    private static transient Logger logger = Logger.getLogger(TindakanRawatBoImpl.class);
    private TindakanRawatDao tindakanRawatDao;
    private CheckupDetailDao checkupDetailDao;
    private DokterDao dokterDao;
    private TindakanDao tindakanDao;
    private KonsultasiGiziDao konsultasiGiziDao;

    @Override
    public List<TindakanRawat> getByCriteria(TindakanRawat bean) throws GeneralBOException {
        logger.info("[TindakanRawatBoImpl.getByCriteria] Start >>>>>>>");

        List<TindakanRawat> results = new ArrayList<>();

        if (bean != null){
            List<ItSimrsTindakanRawatEntity> tindakanRawatEntities = getListEntityTindakanRawat(bean);
            if (!tindakanRawatEntities.isEmpty()){
                results = setToTindakanRawatTemplate(tindakanRawatEntities);
            }
        }

        logger.info("[TindakanRawatBoImpl.getByCriteria] End <<<<<<");
        return results;
    }

    @Override
    public CrudResponse saveAdd(TindakanRawat bean) throws GeneralBOException {
        logger.info("[TindakanRawatBoImpl.saveAdd] Start >>>>>>>");
        CrudResponse response = new CrudResponse();

        if (bean != null ){
            String id = getNextTindakanRawatId();
            if (id != null && !"".equalsIgnoreCase(id)) {
                ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                tindakanRawatEntity.setIdTindakanRawat("TDR" + id);
                tindakanRawatEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                tindakanRawatEntity.setIdTindakan(bean.getIdTindakan());
                tindakanRawatEntity.setNamaTindakan(bean.getNamaTindakan());
                tindakanRawatEntity.setIdDokter(bean.getIdDokter());
                tindakanRawatEntity.setIdPerawat(bean.getIdPerawat());
                tindakanRawatEntity.setTarif(bean.getTarif());
                tindakanRawatEntity.setQty(bean.getQty());
                tindakanRawatEntity.setTarifTotal(bean.getTarif().multiply(bean.getQty()));
                tindakanRawatEntity.setFlag(bean.getFlag());
                tindakanRawatEntity.setAction(bean.getAction());
                tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                tindakanRawatEntity.setLastUpdate(bean.getLastUpdate());
                tindakanRawatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                tindakanRawatEntity.setApproveFlag(bean.getApproveFlag());
                tindakanRawatEntity.setIdPelayanan(bean.getIdPelayanan());
                tindakanRawatEntity.setIdRuangan(bean.getIdRuangan());

                try {
                    tindakanRawatDao.addAndSave(tindakanRawatEntity);

                    //sodiq, trigger menuju ke unit konsultasi gizi ketika flag gizi Y, 18,03,2021
                    if("Y".equalsIgnoreCase(bean.getFlagKonsulGizi())){
                        ItSimrsKonsultasiGiziEntity entity = new ItSimrsKonsultasiGiziEntity();
                        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
                        if(detailCheckupEntity != null){
                            entity.setIdKonsultasiGizi(konsultasiGiziDao.getNextSeq());
                            entity.setNoCheckup(detailCheckupEntity.getNoCheckup());
                            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                            entity.setStatus("0");
                            entity.setAction("C");
                            entity.setFlag("Y");
                            entity.setCreatedWho(bean.getCreatedWho());
                            entity.setCreatedDate(bean.getCreatedDate());
                            entity.setLastUpdateWho(bean.getLastUpdateWho());
                            entity.setLastUpdate(bean.getLastUpdate());
                            insertKonsultasiGizi(entity);
                        }else{
                            throw new GeneralBOException("No Checkup tidak ditemukan...!");
                        }
                    }
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    logger.error("[TindakanRawatBoImpl.saveAdd] Error when insert tindakan rawat ", e);
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            }
        }
        logger.info("[TindakanRawatBoImpl.saveAdd] End <<<<<<");
        return response;
    }

    private void insertKonsultasiGizi(ItSimrsKonsultasiGiziEntity bean){
        if(bean != null){
            try {
                konsultasiGiziDao.addAndSave(bean);
            }catch (HibernateException e){
                logger.error(e.getMessage());
                throw new GeneralBOException("[TindakanRawatBoImpl.insertKonsultasiGizi] Error, "+e.getMessage());
            }
        }
    }

    @Override
    public CrudResponse saveEdit(TindakanRawat bean) throws GeneralBOException {
        logger.info("[TindakanRawatBoImpl.saveEdit] Start >>>>>>>");
        CrudResponse response = new CrudResponse();
        if (bean != null && bean.getIdTindakanRawat() != null && !"".equalsIgnoreCase(bean.getIdTindakanRawat())){

            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdTindakanRawat(bean.getIdTindakanRawat());

            ItSimrsTindakanRawatEntity tindakanRawatEntity = getListEntityTindakanRawat(tindakanRawat).get(0);

            if (tindakanRawatEntity != null){

                tindakanRawatEntity.setIdTindakan(bean.getIdTindakan());
                tindakanRawatEntity.setNamaTindakan(bean.getNamaTindakan());
                tindakanRawatEntity.setIdDokter(bean.getIdDokter());
                tindakanRawatEntity.setIdPerawat(bean.getIdPerawat());
                tindakanRawatEntity.setTarif(bean.getTarif());
                tindakanRawatEntity.setQty(bean.getQty());
                tindakanRawatEntity.setTarifTotal(bean.getTarif().multiply(bean.getQty()));
                tindakanRawatEntity.setAction(bean.getAction());
                tindakanRawatEntity.setLastUpdate(bean.getLastUpdate());
                tindakanRawatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                tindakanRawatEntity.setIdPelayanan(bean.getIdPelayanan());

                try {
                    tindakanRawatDao.updateAndSave(tindakanRawatEntity);
                    //sodiq, trigger menuju ke unit konsultasi gizi ketika flag gizi Y, 18,03,2021
                    if("Y".equalsIgnoreCase(bean.getFlagKonsulGizi())){
                        ItSimrsKonsultasiGiziEntity entity = new ItSimrsKonsultasiGiziEntity();
                        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
                        if(detailCheckupEntity != null){
                            entity.setIdKonsultasiGizi(konsultasiGiziDao.getNextSeq());
                            entity.setNoCheckup(detailCheckupEntity.getNoCheckup());
                            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                            entity.setStatus("0");
                            entity.setAction("C");
                            entity.setFlag("Y");
                            entity.setCreatedWho(bean.getCreatedWho());
                            entity.setCreatedDate(bean.getCreatedDate());
                            entity.setLastUpdateWho(bean.getLastUpdateWho());
                            entity.setLastUpdate(bean.getLastUpdate());
                            insertKonsultasiGizi(entity);
                        }else{
                            throw new GeneralBOException("No Checkup tidak ditemukan...!");
                        }
                    }
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    logger.error("[TindakanRawatBoImpl.saveAdd] Error when update tindakan rawat ", e);
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            }
            // update total biaya pada di detail checkup
//            updateDetailCheckup(bean);
        }
        logger.info("[TindakanRawatBoImpl.saveEdit] End <<<<<<");
        return response;
    }

    @Override
    public List<TindakanRawat> cekTodayTindakanTarifKamar(String idDetail, String tanggal) throws GeneralBOException {
        logger.info("[TindakanRawatBoImpl.cekTodayTindakanTarifKamar] SATRT <<<<<<");
        List<TindakanRawat> result = new ArrayList<>();
        try {
            result = tindakanRawatDao.cekTodayTindakanTarifKamar(idDetail, tanggal);
        }catch (HibernateException e){
            logger.error("[TindakanRawatBoImpl.saveAdd] Error when cek tindakan tarif kamar hari ini ", e);
            throw new GeneralBOException("[TindakanRawatBoImpl.saveAdd] Error when cek tindakan tarif kamar hari ini " + e.getMessage());
        }
        logger.info("[TindakanRawatBoImpl.cekTodayTindakanTarifKamar] END <<<<<<");
        return result;
    }

    @Override
    public CheckResponse updateFlagApproveTindakan(TindakanRawat bean) throws GeneralBOException {
        logger.info("[TindakanRawatBoImpl.updateFlagApproveTindakan] START >>>>>>>");
        CheckResponse response = new CheckResponse();
        if(bean != null){
            List<ItSimrsTindakanRawatEntity> entityList = getListEntityTindakanRawat(bean);
            if(entityList.size() > 0){
                 for (ItSimrsTindakanRawatEntity entity: entityList){
                     entity.setApproveFlag("Y");
                     entity.setLastUpdate(bean.getLastUpdate());
                     entity.setLastUpdateWho(bean.getLastUpdateWho());

                     try {
                         tindakanRawatDao.updateAndSave(entity);
                         response.setStatus("success");
                         response.setMessage("Berhasil update tindakan");
                     }catch (HibernateException e){
                         response.setStatus("error");
                         response.setMessage("Error when update tindakan, Found Eror: "+e.getMessage());
                         logger.error("[TindakanRawatBoImpl.updateFlagApproveTindakan] Error when update approve flag ", e);
                         throw new GeneralBOException("[TindakanRawatBoImpl.updateFlagApproveTindakan] Error when update approve flag " + e.getMessage());
                     }
                 }
            }

        }
        logger.info("[TindakanRawatBoImpl.updateFlagApproveTindakan] END >>>>>>>");
        return response;
    }

    @Override
    public ItSimrsTindakanRawatEntity getTindakanRawatEntityById(String id) throws GeneralBOException {
        return tindakanRawatDao.getById("idTindakanRawat", id);
    }

    @Override
    public List<TindakanRawat> getListTindakanRawat(String noCheckup, String jenis) throws GeneralBOException {
        return tindakanRawatDao.getListTindakanRawat(noCheckup, jenis);
    }

    protected void updateDetailCheckup(TindakanRawat bean) throws GeneralBOException{
        logger.info("[TindakanRawatBoImpl.updateDetailCheckup] Start >>>>>>>");

        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            BigInteger totalTarif = new BigInteger(String.valueOf(0));
            try {
                totalTarif = tindakanRawatDao.getSumOfTarifTindakanByIdDetailCheckup(bean.getIdDetailCheckup());
            } catch (HibernateException e){
                logger.error("[TindakanRawatBoImpl.saveAdd] Error when get sum of total tarif tindakan ", e);
                throw new GeneralBOException("[TindakanRawatBoImpl.saveAdd] Error when insert tindakan rawat " + e.getMessage());
            }

            if (totalTarif != null){

                Map hsCriteria = new HashMap();
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());

                List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntityList = null;
                try {
                    detailCheckupEntityList = checkupDetailDao.getByCriteria(hsCriteria);
                } catch (HibernateException e){
                    logger.error("[TindakanRawatBoImpl.saveAdd] Error when get detail checkup data ", e);
                    throw new GeneralBOException("[TindakanRawatBoImpl.saveAdd] Error when get detail checkup data " + e.getMessage());
                }

                if (!detailCheckupEntityList.isEmpty()){
                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = detailCheckupEntityList.get(0);

                    detailCheckupEntity.setTotalBiaya(totalTarif);
                    detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                    detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        checkupDetailDao.updateAndSave(detailCheckupEntity);
                    } catch (HibernateException e){
                        logger.error("[TindakanRawatBoImpl.saveAdd] Error when update checkup detail data ", e);
                        throw new GeneralBOException("[TindakanRawatBoImpl.saveAdd] Error when update checkup detail data " + e.getMessage());
                    }
                }
            }
        }
        logger.info("[TindakanRawatBoImpl.updateDetailCheckup] End <<<<<<");
    }

    protected List<ItSimrsTindakanRawatEntity> getListEntityTindakanRawat(TindakanRawat bean) throws GeneralBOException{
        logger.info("[TindakanRawatBoImpl.getListEntityTindakanRawat] Start >>>>>>>");
        List<ItSimrsTindakanRawatEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdTindakanRawat() != null && !"".equalsIgnoreCase(bean.getIdTindakanRawat())){
            hsCriteria.put("id_tindakan_rawat", bean.getIdTindakanRawat());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getApproveFlag() != null && !"".equalsIgnoreCase(bean.getApproveFlag())){
            hsCriteria.put("approve_flag", bean.getApproveFlag());
        }

        hsCriteria.put("flag","Y");
        try {
            results = tindakanRawatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TindakanRawatBoImpl.getListEntityTindakanRawat] Erro when searching tindakan rawat ", e);
        }

        logger.info("[TindakanRawatBoImpl.getListEntityTindakanRawat] End <<<<<<");
        return results;
    }

    protected List<TindakanRawat> setToTindakanRawatTemplate(List<ItSimrsTindakanRawatEntity> entities) throws GeneralBOException{
        logger.info("[TindakanRawatBoImpl.setToTindakanRawatTemplate] Start >>>>>>>");
        List<TindakanRawat> results = new ArrayList<>();

        TindakanRawat tindakanRawat;
        for (ItSimrsTindakanRawatEntity entity : entities){

            tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdTindakanRawat(entity.getIdTindakanRawat());
            tindakanRawat.setIdDetailCheckup(entity.getIdDetailCheckup());
            tindakanRawat.setIdTindakan(entity.getIdTindakan());
            tindakanRawat.setNamaTindakan(entity.getNamaTindakan());
            tindakanRawat.setIdDokter(entity.getIdDokter());
            tindakanRawat.setIdPerawat(entity.getIdPerawat());
            tindakanRawat.setTarif(entity.getTarif());
            tindakanRawat.setQty(entity.getQty());
            tindakanRawat.setTarifTotal(entity.getTarifTotal());
            tindakanRawat.setFlag(entity.getFlag());
            tindakanRawat.setAction(entity.getAction());
            tindakanRawat.setCreatedDate(entity.getCreatedDate());
            tindakanRawat.setCreatedWho(entity.getCreatedWho());
            tindakanRawat.setLastUpdate(entity.getLastUpdate());
            tindakanRawat.setLastUpdateWho(entity.getLastUpdateWho());
            tindakanRawat.setApproveFlag(entity.getApproveFlag());
            tindakanRawat.setIdPelayanan(entity.getIdPelayanan());
            tindakanRawat.setIdRuangan(entity.getIdRuangan());

            if (entity.getIdDokter() != null && !"".equalsIgnoreCase(entity.getIdDokter())){
                List<ImSimrsDokterEntity> listDokter = getDokterList(entity.getIdDokter());
                if (!listDokter.isEmpty()){
                    ImSimrsDokterEntity dokterEntity = listDokter.get(0);
                    tindakanRawat.setNamaDokter(dokterEntity.getNamaDokter());
                }
            }

            List<ImSimrsTindakanEntity> tindakanEntityList = getTindakanList(entity.getIdTindakan());
            ImSimrsTindakanEntity tindakanEntity = new ImSimrsTindakanEntity();
            if (!tindakanEntityList.isEmpty()){
                tindakanEntity = tindakanEntityList.get(0);
            }
            if(tindakanEntity != null){
                tindakanRawat.setIdKategoriTindakan(tindakanEntity.getIdKategoriTindakan());
                tindakanRawat.setKategoriInaBpjs(tindakanEntity.getKategoriInaBpjs());
//                tindakanRawat.setTarifBpjs(tindakanEntity.getTarifBpjs());
//                tindakanRawat.setTarif(tindakanEntity.getTarif());
            }

            results.add(tindakanRawat);
        }

        logger.info("[TindakanRawatBoImpl.setToTindakanRawatTemplate] End <<<<<<");
        return results;
    }

    private List<ImSimrsDokterEntity> getDokterList(String dokterId){
        logger.info("[TindakanRawatBoImpl.getDokterList] Start >>>>>>>");
        List<ImSimrsDokterEntity> dokterList = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_dokter", dokterId);
        try {
            dokterList = dokterDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TindakanRawatBoImpl.getDokterList] Error when get data dokter ", e);
        }

        logger.info("[TindakanRawatBoImpl.getDokterList] End <<<<<<");
        return dokterList;
    }

    private List<ImSimrsTindakanEntity> getTindakanList(String idTindakan){
        logger.info("[TindakanRawatBoImpl.getTindakanList] Start >>>>>>>");
        List<ImSimrsTindakanEntity> tindakanEntityList = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_tindakan", idTindakan);
        try {
            tindakanEntityList = tindakanDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TindakanRawatBoImpl.getTindakanList] Error when get data tindakan ", e);
        }

        logger.info("[TindakanRawatBoImpl.getTindakanList] End <<<<<<");
        return tindakanEntityList;
    }

    public String getNextTindakanRawatId(){
        logger.info("[TindakanRawatBoImpl.getNextTindakanRawatId] Start >>>>>>>");

        String id = "";
        try {
            id = tindakanRawatDao.getNextTindakanRawatId();
        } catch (HibernateException e){
            logger.error("[TindakanRawatBoImpl.getNextTindakanRawatId] Error when get next id tindakan rawat");
        }

        logger.info("[TindakanRawatBoImpl.getNextTindakanRawatId] End <<<<<<");
        return id;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public void setKonsultasiGiziDao(KonsultasiGiziDao konsultasiGiziDao) {
        this.konsultasiGiziDao = konsultasiGiziDao;
    }
}
