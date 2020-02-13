package com.neurix.simrs.transaksi.rawatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.moncairan.dao.MonCairanDao;
import com.neurix.simrs.transaksi.moncairan.model.ItSimrsMonCairanEntity;
import com.neurix.simrs.transaksi.moncairan.model.MonCairan;
import com.neurix.simrs.transaksi.monpemberianobat.dao.MonPemberianObatDao;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import com.neurix.simrs.transaksi.monpemberianobat.model.MonPemberianObat;
import com.neurix.simrs.transaksi.monvitalsign.dao.MonVitalSignDao;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.rencanarawat.dao.ParameterRencanaRawatDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.KategoriSkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.MasterSkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.ParameterSkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.SkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class RawatInapBoImpl implements RawatInapBo {

    private static transient Logger logger = Logger.getLogger(RawatInapBoImpl.class);
    private RawatInapDao rawatInapDao;
    private KategoriSkorRanapDao kategoriSkorRanapDao;
    private ParameterSkorRanapDao parameterSkorRanapDao;
    private MasterSkorRanapDao masterSkorRanapDao;
    private SkorRanapDao skorRanapDao;
    private MonCairanDao monCairanDao;
    private MonPemberianObatDao monPemberianObatDao;
    private MonVitalSignDao monVitalSignDao;

    protected List<ItSimrsRawatInapEntity> getListEntityByCriteria(RawatInap bean) throws GeneralBOException{
        logger.info("[RawatInapBoImpl.getListEntityByCriteria] Start >>>>>>>");
        List<ItSimrsRawatInapEntity> entityList = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdRawatInap() != null && !"".equalsIgnoreCase(bean.getIdRawatInap())){
            hsCriteria.put("id_rawat_inap", bean.getIdRawatInap());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }
        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }

        hsCriteria.put("flag", "Y");

        try {
            entityList = rawatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getListEntityByCriteria] Error when get data detail checkup entity ",e);
            throw new GeneralBOException("Error when get data detail checkup entity "+e.getMessage());
        }

        logger.info("[RawatInapBoImpl.getListEntityByCriteria] End <<<<<<<<");
        return entityList;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    @Override
    public List<RawatInap> getSearchRawatInap(RawatInap bean) throws GeneralBOException {
        logger.info("[RawatInapBoImpl.getSearchRawatInap] Start >>>>>>>");
        List<RawatInap> results = new ArrayList<>();
        if (bean != null){
            try {
                results = rawatInapDao.getSearchRawatInap(bean);
            } catch (HibernateException e){
                logger.error("[RawatInapBoImpl.getSearchRawatInap] Error when get data rawat inap",e);
                throw new GeneralBOException("Error when get data detail checkup"+e.getMessage());
            }
        }
        logger.info("[RawatInapBoImpl.getSearchRawatInap] End <<<<<<<<");
        return results;
    }

    @Override
    public List<SkorRanap> getListSkorRanap(SkorRanap bean) throws GeneralBOException {
        logger.info("[RawatInapBoImpl.getListSkorRanap] Start >>>>>>>");

        List<SkorRanap> skorRanaps = new ArrayList<>();
        List<ItSimrsSkorRanapEntity> skorRanapEntities = new ArrayList<>();
        if (bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getNoCheckup() != null){
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }
            if (bean.getIdDetailCheckup() != null){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getIdKategori() != null){
                hsCriteria.put("id_kategori", bean.getIdKategori());
            }
            if (bean.getGroupId() != null){
                hsCriteria.put("group_id", bean.getGroupId());
            }
            if (bean.getStDate() != null){
                hsCriteria.put("date", bean.getStDate());
            }

            if (bean.getGroupId() != null && !"".equalsIgnoreCase(bean.getGroupId())){
                try {
                    skorRanapEntities = skorRanapDao.getByCriteria(hsCriteria);
                } catch (HibernateException e){
                    logger.error("[RawatInapBoImpl.getListSkorRanap] ERROR",e);
                    throw new GeneralBOException("[RawatInapBoImpl.getListSkorRanap] ERROR"+e.getMessage());
                }
            }


            if (skorRanapEntities.size() == 0){

                hsCriteria = new HashMap();
                hsCriteria.put("id_kategori", bean.getIdKategori());

                List<ImSimrsParameterSkorRanapEntity> parameterSkorRanapEntities = new ArrayList<>();
                try {
                    parameterSkorRanapEntities = parameterSkorRanapDao.getByCriteria(hsCriteria);
                } catch (HibernateException e){
                    logger.error("[RawatInapBoImpl.getListSkorRanap] ERROR",e);
                    throw new GeneralBOException("[RawatInapBoImpl.getListSkorRanap] ERROR"+e.getMessage());
                }

                if (parameterSkorRanapEntities.size() > 0){
                    SkorRanap skorRanap;
                    for (ImSimrsParameterSkorRanapEntity parameter : parameterSkorRanapEntities){
                        skorRanap = new SkorRanap();
                        skorRanap.setIdKategori(parameter.getIdKategori());
                        skorRanap.setIdParameter(parameter.getIdParameter());
                        skorRanap.setNamaParameter(parameter.getNamaParameter());
                        skorRanap.setType(parameter.getType());
                        skorRanaps.add(skorRanap);
                    }
                }
            } else {
                SkorRanap skorRanap;
                for (ItSimrsSkorRanapEntity skorRanapEntity : skorRanapEntities){
                    skorRanap = new SkorRanap();
                    skorRanap.setId(skorRanapEntity.getId());
                    skorRanap.setGroupId(skorRanapEntity.getGroupId());
                    skorRanap.setIdKategori(skorRanapEntity.getIdKategori());
                    skorRanap.setIdParameter(skorRanapEntity.getIdParameter());
                    skorRanap.setNamaParameter(skorRanapEntity.getNamaParameter());
                    skorRanap.setSkor(skorRanapEntity.getSkor().toString());
                    skorRanap.setKeterangan(skorRanapEntity.getKeterangan());
                    skorRanap.setFlag(skorRanapEntity.getFlag());
                    skorRanap.setAction(skorRanapEntity.getAction());
                    skorRanap.setCreatedDate(skorRanapEntity.getCreatedDate());
                    skorRanap.setCreatedWho(skorRanapEntity.getCreatedWho());
                    skorRanap.setLastUpdate(skorRanapEntity.getLastUpdate());
                    skorRanap.setLastUpdateWho(skorRanapEntity.getLastUpdateWho());
                    skorRanap.setStDate(skorRanapEntity.getCreatedDate().toString());
                    skorRanaps.add(skorRanap);
                }
            }
        }

        logger.info("[RawatInapBoImpl.getListSkorRanap] End <<<<<<<<");
        return skorRanaps;
    }

    @Override
    public ImSimrsKategoriSkorRanapEntity kategoriSkorRanap(String id) {
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_kategori", id);

        List<ImSimrsKategoriSkorRanapEntity> kategoriSkorRanapEntities = new ArrayList<>();
        try {
            kategoriSkorRanapEntities = kategoriSkorRanapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.ImSimrsKategoriSkorRanapEntity] ERROR",e);
            throw new GeneralBOException("[RawatInapBoImpl.ImSimrsKategoriSkorRanapEntity] ERROR"+e.getMessage());
        }

        ImSimrsKategoriSkorRanapEntity kategoriSkorRanapEntity = new ImSimrsKategoriSkorRanapEntity();
        if (kategoriSkorRanapEntities.size() > 0){
            kategoriSkorRanapEntity = kategoriSkorRanapEntities.get(0);
        }

        return kategoriSkorRanapEntity;
    }

    @Override
    public List<ImSimrsSkorRanapEntity> getListMasterSkor(String id) throws GeneralBOException {
        logger.info("[RawatInapBoImpl.getListMasterSkor] Start >>>>>>>");

        List<ImSimrsSkorRanapEntity> skorRanapEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_parameter", id);

        skorRanapEntities = masterSkorRanapDao.getByCriteria(hsCriteria);
        logger.info("[RawatInapBoImpl.getListMasterSkor] End <<<<<<<<");
        return skorRanapEntities;
    }

    @Override
    public CrudResponse saveAddSkorRanap(String noCheckup, String idDetail, List<ItSimrsSkorRanapEntity> skors) {

        CrudResponse response = new CrudResponse();
        if (skors.size() > 0){
            String groupId = "GRS"+getNextGroupSkorRanap();
            for (ItSimrsSkorRanapEntity entity : skors){
                entity.setId("SKI"+getNextSkorRanap());
                entity.setGroupId(groupId);

                try {
                    skorRanapDao.addAndSave(entity);
                    response.setStatus("success");
                } catch (HibernateException e){
                    logger.error("[RawatInapBoImpl.saveAddSkorRanap] ERROR ",e);
                    response.setStatus("error");
                    response.setMsg("[RawatInapBoImpl.saveAddSkorRanap] ERROR "+e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public List<MonVitalSign> getListMonVitalSign(MonVitalSign bean) {

        Map hsCriteria = new HashMap();
        if (bean.getNoCheckup() != null)
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        if (bean.getIdDetailCheckup() != null)
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());

        List<ItSimrsMonVitalSignEntity> monVitalSignEntities = new ArrayList<>();
        try {
            monVitalSignEntities = monVitalSignDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getListMonVitalSign] ERROR",e);
            throw new GeneralBOException("[RawatInapBoImpl.getListMonVitalSign] ERROR"+e.getMessage());
        }

        List<MonVitalSign> monVitalSigns = new ArrayList<>();
        if (monVitalSignEntities.size() > 0){
            MonVitalSign monVitalSign;
            for (ItSimrsMonVitalSignEntity entity : monVitalSignEntities){
                monVitalSign = new MonVitalSign();
                monVitalSign.setId(entity.getId());
                monVitalSign.setNoCheckup(entity.getNoCheckup());
                monVitalSign.setIdDetailCheckup(entity.getIdDetailCheckup());
                monVitalSign.setNadi(entity.getNadi());
                monVitalSign.setNafas(entity.getNafas());
                monVitalSign.setSuhu(entity.getSuhu());
                monVitalSign.setTensi(entity.getTensi());
                monVitalSign.setJam(entity.getJam());
                monVitalSign.setFlag(entity.getFlag());
                monVitalSign.setAction(entity.getAction());
                monVitalSign.setCreatedDate(entity.getCreatedDate());
                monVitalSign.setCreatedWho(entity.getCreatedWho());
                monVitalSign.setLastUpdate(entity.getLastUpdate());
                monVitalSign.setLastUpdateWho(entity.getLastUpdateWho());
                monVitalSign.setStDate(entity.getCreatedDate().toString());
                monVitalSigns.add(monVitalSign);
            }
        }
        return monVitalSigns;
    }

    @Override
    public CrudResponse saveMonVitalSign(ItSimrsMonVitalSignEntity bean) {
        CrudResponse response = new CrudResponse();
        response.setStatus("error");
        response.setMsg("[RawatInapBoImpl.saveMonVitalSign] bean is null");
        if (bean != null){
            bean.setId("OVS"+getNextMonVitalSign());
            try {
                monVitalSignDao.addAndSave(bean);
                response.setStatus("success");
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("[RawatInapBoImpl.saveMonVitalSign] ERROR "+ e.getMessage());
            }
        }
        return response;
    }

    @Override
    public List<MonCairan> getListMonCairan(MonCairan bean) {
        Map hsCriteria = new HashMap();
        if (bean.getNoCheckup() != null)
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        if (bean.getIdDetailCheckup() != null)
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());

        List<ItSimrsMonCairanEntity> monCairanEntities = new ArrayList<>();
        try {
            monCairanEntities = monCairanDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getListMonCairan] ERROR",e);
            throw new GeneralBOException("[RawatInapBoImpl.getListMonCairan] ERROR"+e.getMessage());
        }

        List<MonCairan> monCairans = new ArrayList<>();
        if (monCairanEntities.size() > 0){
            MonCairan monCairan;
            for (ItSimrsMonCairanEntity entity : monCairanEntities){
                monCairan = new MonCairan();
                monCairan.setId(entity.getId());
                monCairan.setNoCheckup(entity.getNoCheckup());
                monCairan.setIdDetailCheckup(entity.getIdDetailCheckup());
                monCairan.setMacamCairan(entity.getMacamCairan());
                monCairan.setMelalui(entity.getMelalui());
                monCairan.setJumlah(entity.getJumlah());
                monCairan.setJamMulai(entity.getJamMulai());
                monCairan.setJamSelesai(entity.getJamSelesai());
                monCairan.setCekTambahanObat(entity.getCekTambahanObat());
                monCairan.setJamUkurBuang(entity.getJamUkurBuang());
                monCairan.setDari(entity.getDari());
                monCairan.setBalanceCairan(entity.getBalanceCairan());
                monCairan.setKeterangan(entity.getKeterangan());
                monCairan.setFlag(entity.getFlag());
                monCairan.setAction(entity.getAction());
                monCairan.setCreatedDate(entity.getCreatedDate());
                monCairan.setCreatedWho(entity.getCreatedWho());
                monCairan.setLastUpdate(entity.getLastUpdate());
                monCairan.setLastUpdateWho(entity.getLastUpdateWho());
                monCairan.setStDate(entity.getCreatedDate().toString());
                monCairans.add(monCairan);
            }
        }
        return monCairans;
    }

    @Override
    public CrudResponse saveMonCairan(ItSimrsMonCairanEntity bean) {
        CrudResponse response = new CrudResponse();
        response.setStatus("error");
        response.setMsg("[RawatInapBoImpl.saveMonCairan] bean is null");
        if (bean != null){
            bean.setId("OCR"+getNextMonCairan());
            try {
                monCairanDao.addAndSave(bean);
                response.setStatus("success");
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("[RawatInapBoImpl.saveMonCairan] ERROR "+ e.getMessage());
            }
        }
        return response;
    }

    @Override
    public List<MonPemberianObat> getListPemberianObat(MonPemberianObat bean) {
        Map hsCriteria = new HashMap();
        if (bean.getNoCheckup() != null)
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        if (bean.getIdDetailCheckup() != null)
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());

        List<ItSimrsMonPemberianObatEntity> pemberianObatEntities = new ArrayList<>();
        try {
            pemberianObatEntities = monPemberianObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getListMonCairan] ERROR",e);
            throw new GeneralBOException("[RawatInapBoImpl.getListMonCairan] ERROR"+e.getMessage());
        }

        List<MonPemberianObat> monPemberianObats = new ArrayList<>();
        if (pemberianObatEntities.size() > 0){
            MonPemberianObat monPemberianObat;
            for (ItSimrsMonPemberianObatEntity entity : pemberianObatEntities){
                monPemberianObat = new MonPemberianObat();
                monPemberianObat.setId(entity.getId());
                monPemberianObat.setNoCheckup(entity.getNoCheckup());
                monPemberianObat.setIdDetailCheckup(entity.getIdDetailCheckup());
                monPemberianObat.setNamaObat(entity.getNamaObat());
                monPemberianObat.setCaraPemberian(entity.getCaraPemberian());
                monPemberianObat.setDosis(entity.getDosis());
                monPemberianObat.setSkinTes(entity.getSkinTes());
                monPemberianObat.setWaktu(entity.getWaktu());
                monPemberianObat.setKeterangan(entity.getKeterangan());
                monPemberianObat.setFlag(entity.getFlag());
                monPemberianObat.setAction(entity.getAction());
                monPemberianObat.setCreatedDate(entity.getCreatedDate());
                monPemberianObat.setCreatedWho(entity.getCreatedWho());
                monPemberianObat.setLastUpdate(entity.getLastUpdate());
                monPemberianObat.setLastUpdateWho(entity.getLastUpdateWho());
                monPemberianObat.setStDate(entity.getCreatedDate().toString());
                monPemberianObats.add(monPemberianObat);
            }
        }
        return monPemberianObats;
    }

    @Override
    public CrudResponse saveMonPemberianObat(ItSimrsMonPemberianObatEntity bean) {
        CrudResponse response = new CrudResponse();
        response.setStatus("error");
        response.setMsg("[RawatInapBoImpl.saveMonPemberianObat] bean is null");
        if (bean != null){
            bean.setId("POT"+getNextMonPemberianObat());
            try {
                monPemberianObatDao.addAndSave(bean);
                response.setStatus("success");
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("[RawatInapBoImpl.saveMonPemberianObat] ERROR "+ e.getMessage());
            }
        }
        return response;
    }

    @Override
    public List<Obat> getListObatParenteral(String idPelayanan) {
        return monPemberianObatDao.getListObatParenteral(idPelayanan);
    }

    @Override
    public List<Obat> getListObatNonParenteral(String idDetail, String kategori) {
        return monPemberianObatDao.getListObatNonParenteral(idDetail, kategori);
    }

    @Override
    public List<SkorRanap> getListSumSkorRanap(String noCheckup, String idDetail, String idkategori) {

        List<SkorRanap> skorRanaps = new ArrayList<>();
        try {
            skorRanaps = skorRanapDao.getListSumSkorRanap(noCheckup, idDetail, idkategori);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getListSumSkorRanap] ERROR ",e);
        }

        return skorRanaps;
    }

    private String getNextSkorRanap(){
        String id = "";
        try {
            id = skorRanapDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getNextSkorRanap] Error when get seq ",e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextSkorRanap] Error when get seq "+e.getMessage());
        }
        return id;
    }

    private String getNextGroupSkorRanap(){
        String id = "";
        try {
            id = skorRanapDao.getNextGroupSeq();
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getNextGroupSkorRanap] Error when get seq ",e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextGroupSkorRanap] Error when get seq "+e.getMessage());
        }
        return id;
    }

    private String getNextMonCairan(){
        String id = "";
        try {
            id = monCairanDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getNextMonCairan] Error when get seq ",e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextMonCairan] Error when get seq "+e.getMessage());
        }
        return id;
    }

    private String getNextMonPemberianObat(){
        String id = "";
        try {
            id = monPemberianObatDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getNextMonPemberianObat] Error when get seq ",e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextMonPemberianObat] Error when get seq "+e.getMessage());
        }
        return id;
    }

    private String getNextMonVitalSign(){
        String id = "";
        try {
            id = monVitalSignDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getNextMonVitalSign] Error when get seq ",e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextMonVitalSign] Error when get seq "+e.getMessage());
        }
        return id;
    }


    public void setKategoriSkorRanapDao(KategoriSkorRanapDao kategoriSkorRanapDao) {
        this.kategoriSkorRanapDao = kategoriSkorRanapDao;
    }

    public void setParameterSkorRanapDao(ParameterSkorRanapDao parameterSkorRanapDao) {
        this.parameterSkorRanapDao = parameterSkorRanapDao;
    }

    public void setMasterSkorRanapDao(MasterSkorRanapDao masterSkorRanapDao) {
        this.masterSkorRanapDao = masterSkorRanapDao;
    }

    public void setSkorRanapDao(SkorRanapDao skorRanapDao) {
        this.skorRanapDao = skorRanapDao;
    }

    public void setMonCairanDao(MonCairanDao monCairanDao) {
        this.monCairanDao = monCairanDao;
    }

    public void setMonPemberianObatDao(MonPemberianObatDao monPemberianObatDao) {
        this.monPemberianObatDao = monPemberianObatDao;
    }

    public void setMonVitalSignDao(MonVitalSignDao monVitalSignDao) {
        this.monVitalSignDao = monVitalSignDao;
    }
}
