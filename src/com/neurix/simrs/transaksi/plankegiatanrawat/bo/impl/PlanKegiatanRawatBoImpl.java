package com.neurix.simrs.transaksi.plankegiatanrawat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.moncairan.dao.MonCairanDao;
import com.neurix.simrs.transaksi.moncairan.model.ItSimrsMonCairanEntity;
import com.neurix.simrs.transaksi.moncairan.model.MonCairan;
import com.neurix.simrs.transaksi.monpemberianobat.dao.MonPemberianObatDao;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import com.neurix.simrs.transaksi.monpemberianobat.model.MonPemberianObat;
import com.neurix.simrs.transaksi.monvitalsign.dao.MonVitalSignDao;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.plankegiatanrawat.bo.PlanKegiatanRawatBo;
import com.neurix.simrs.transaksi.plankegiatanrawat.dao.PlanKegiatanRawatDao;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.ItSimrsPlanKegiatanRawatEntity;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.PlanKegiatanRawat;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 06/03/20.
 */
public class PlanKegiatanRawatBoImpl implements PlanKegiatanRawatBo {

    protected static transient Logger logger = Logger.getLogger(PlanKegiatanRawatBoImpl.class);

    private PlanKegiatanRawatDao planKegiatanRawatDao;
    private CheckupDetailDao checkupDetailDao;
    private PasienDao pasienDao;
    private PelayananDao pelayananDao;
    private RawatInapDao rawatInapDao;
    private RuanganDao ruanganDao;
    private MonVitalSignDao monVitalSignDao;
    private MonCairanDao monCairanDao;
    private MonPemberianObatDao monPemberianObatDao;

    @Override
    public List<PlanKegiatanRawat> getSearchByCritria(PlanKegiatanRawat bean) throws GeneralBOException {

        List<ItSimrsPlanKegiatanRawatEntity> planKegiatanRawatEntities = getListEntityPlanKegiatan(bean);
        List<PlanKegiatanRawat> planKegiatanRawats = new ArrayList<>();
        if (planKegiatanRawatEntities.size() > 0){
            PlanKegiatanRawat kegiatanRawat;
            for (ItSimrsPlanKegiatanRawatEntity planKegiatan : planKegiatanRawatEntities){
                kegiatanRawat = new PlanKegiatanRawat();
                kegiatanRawat.setId(planKegiatan.getId());
                kegiatanRawat.setIdKategori(planKegiatan.getIdKategori());
                kegiatanRawat.setIdDetailCheckup(planKegiatan.getIdDetailCheckup());
                kegiatanRawat.setJamMulai(planKegiatan.getJamMulai());
                kegiatanRawat.setJamSelesai(planKegiatan.getJamSelesai());
                kegiatanRawat.setTglMulai(planKegiatan.getTglMulai());
                kegiatanRawat.setTglSelesai(planKegiatan.getTglSelesai());
                kegiatanRawat.setBranchId(planKegiatan.getBranchId());
                kegiatanRawat.setWaktu(planKegiatan.getWaktu());
                kegiatanRawat.setJenisKegiatan(planKegiatan.getJenisKegiatan());
                kegiatanRawat.setKeterangan(planKegiatan.getKeterangan());
                kegiatanRawat.setFlag(planKegiatan.getFlag());
                kegiatanRawat.setAction(planKegiatan.getAction());
                kegiatanRawat.setCreatedDate(planKegiatan.getCreatedDate());
                kegiatanRawat.setCreatedWho(planKegiatan.getCreatedWho());
                kegiatanRawat.setLastUpdate(planKegiatan.getLastUpdate());
                kegiatanRawat.setLastUpdateWho(planKegiatan.getLastUpdateWho());
                kegiatanRawat.setStTglMulai(planKegiatan.getTglMulai().toString());
                planKegiatanRawats.add(kegiatanRawat);
            }
        }

        return planKegiatanRawats;
    }

    public List<ItSimrsPlanKegiatanRawatEntity> getListEntityPlanKegiatan(PlanKegiatanRawat bean){

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup()))
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        if (bean.getTglMulai() != null)
            hsCriteria.put("tgl_mulai", bean.getTglMulai());

        List<ItSimrsPlanKegiatanRawatEntity> planKegiatanRawatEntities = new ArrayList<>();

        try {
            planKegiatanRawatEntities = planKegiatanRawatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PlanKegiatanRawatBoImpl.getListEntityPlanKegiatan] ERROR. ",e);
            throw new GeneralBOException("[PlanKegiatanRawatBoImpl.getListEntityPlanKegiatan] ERROR. "+e);
        }

        return planKegiatanRawatEntities;
    }

    @Override
    public List<PlanKegiatanRawat> getListKegiatanRawat(PlanKegiatanRawat bean) throws GeneralBOException {
        return planKegiatanRawatDao.getSearchPlanKegiataRawat(bean);
    }

    @Override
    public List<PlanKegiatanRawat> getListPlanKegitatanRawat(PlanKegiatanRawat bean) throws GeneralBOException {
        return planKegiatanRawatDao.getListPlanGroup(bean);
    }


    @Override
    public void saveAddPlanKegiatan(PlanKegiatanRawat bean, List<MonVitalSign> vitalSignList, List<MonCairan> cairanList, List<MonPemberianObat> pemberianObatList) throws GeneralBOException {

        if (bean != null){

            List<ItSimrsPlanKegiatanRawatEntity> planEntities = new ArrayList<>();

            if (vitalSignList.size() > 0){
                for (MonVitalSign vitalSign : vitalSignList){
                    ItSimrsMonVitalSignEntity monVitalSignEntity = new ItSimrsMonVitalSignEntity();
                    monVitalSignEntity.setId(getNextMonVitalSign());
                    monVitalSignEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    monVitalSignEntity.setJam(vitalSign.getJam());
                    monVitalSignEntity.setFlag(bean.getFlag());
                    monVitalSignEntity.setAction(bean.getAction());
                    monVitalSignEntity.setCreatedDate(bean.getCreatedDate());
                    monVitalSignEntity.setCreatedWho(bean.getCreatedWho());
                    monVitalSignEntity.setLastUpdate(bean.getLastUpdate());
                    monVitalSignEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        monVitalSignDao.addAndSave(monVitalSignEntity);
                    } catch (HibernateException e){
                        logger.error("[PlanKegiatanRawatBoImpl.saveAddPlanKegiatan] ERROR. ",e);
                        throw new GeneralBOException("[PlanKegiatanRawatBoImpl.saveAddPlanKegiatan] ERROR. "+e);
                    }

                    ItSimrsPlanKegiatanRawatEntity planEntity = new ItSimrsPlanKegiatanRawatEntity();
                    planEntity.setIdKategori(monVitalSignEntity.getId());
                    planEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    planEntity.setId(getNextPlanId());
                    planEntity.setWaktu(vitalSign.getWaktu());
                    planEntity.setTglMulai(bean.getTglMulai());
                    planEntity.setTglSelesai(bean.getTglSelesai());
                    planEntity.setKeterangan(vitalSign.getCatatanDokter());
                    planEntity.setJenisKegiatan("vitalsign");
                    planEntity.setBranchId(bean.getBranchId());
                    planEntity.setFlag(bean.getFlag());
                    planEntity.setAction(bean.getAction());
                    planEntity.setCreatedDate(bean.getCreatedDate());
                    planEntity.setCreatedWho(bean.getCreatedWho());
                    planEntity.setLastUpdate(bean.getLastUpdate());
                    planEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    planEntities.add(planEntity);
                }
            }

            if (cairanList.size() > 0){
                for (MonCairan monCairan : cairanList){
                    ItSimrsMonCairanEntity monCairanEntity = new ItSimrsMonCairanEntity();
                    monCairanEntity.setId(getNextMonCairan());
                    monCairanEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    monCairanEntity.setMacamCairan(monCairan.getMacamCairan());
                    monCairanEntity.setMelalui(monCairan.getMelalui());
                    monCairanEntity.setJumlah(monCairan.getJumlah());
                    monCairanEntity.setJamMulai(monCairan.getJamMulai());
                    monCairanEntity.setJamSelesai(monCairan.getJamSelesai());
                    monCairanEntity.setCekTambahanObat(monCairan.getCekTambahanObat());
                    monCairanEntity.setSisa(monCairan.getSisa());
                    monCairanEntity.setJamUkurBuang(monCairan.getJamUkurBuang());
                    monCairanEntity.setDari(monCairan.getDari());
                    monCairanEntity.setBalanceCairan(monCairan.getBalanceCairan());
                    monCairanEntity.setFlag(bean.getFlag());
                    monCairanEntity.setAction(bean.getAction());
                    monCairanEntity.setCreatedDate(bean.getCreatedDate());
                    monCairanEntity.setCreatedWho(bean.getCreatedWho());
                    monCairanEntity.setLastUpdate(bean.getLastUpdate());
                    monCairanEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        monCairanDao.addAndSave(monCairanEntity);
                    } catch (HibernateException e){
                        logger.error("[PlanKegiatanRawatBoImpl.saveAddPlanKegiatan] ERROR. ",e);
                        throw new GeneralBOException("[PlanKegiatanRawatBoImpl.saveAddPlanKegiatan] ERROR. "+e);
                    }

                    ItSimrsPlanKegiatanRawatEntity planEntity = new ItSimrsPlanKegiatanRawatEntity();
                    planEntity.setIdKategori(monCairanEntity.getId());
                    planEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    planEntity.setId(getNextPlanId());
                    planEntity.setWaktu(monCairan.getWaktu());
                    planEntity.setTglMulai(bean.getTglMulai());
                    planEntity.setTglSelesai(bean.getTglSelesai());
                    planEntity.setKeterangan(monCairan.getCatatanDokter());
                    planEntity.setBranchId(bean.getBranchId());
                    planEntity.setJenisKegiatan("cairan");
                    planEntity.setFlag(bean.getFlag());
                    planEntity.setAction(bean.getAction());
                    planEntity.setCreatedDate(bean.getCreatedDate());
                    planEntity.setCreatedWho(bean.getCreatedWho());
                    planEntity.setLastUpdate(bean.getLastUpdate());
                    planEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    planEntities.add(planEntity);

                }
            }

            if (pemberianObatList.size() > 0){
                for (MonPemberianObat monPemberianObat : pemberianObatList){
                    ItSimrsMonPemberianObatEntity monPemberianObatEntity = new ItSimrsMonPemberianObatEntity();
                    monPemberianObatEntity.setId(getNextMonPemberianObat());
                    monPemberianObatEntity.setIdDetailCheckup(bean.getIdDetailCheckup());

                    monPemberianObatEntity.setNamaObat(monPemberianObat.getNamaObat());
                    monPemberianObatEntity.setCaraPemberian(monPemberianObat.getCaraPemberian() == null ? "" : monPemberianObat.getCaraPemberian());
                    monPemberianObatEntity.setDosis(monPemberianObat.getDosis());
                    monPemberianObatEntity.setSkinTes(monPemberianObat.getSkinTes() == null ? "" : monPemberianObat.getSkinTes());
                    monPemberianObatEntity.setWaktu(monPemberianObat.getWaktu());
                    monPemberianObatEntity.setKategori(monPemberianObat.getKategori());
                    monPemberianObatEntity.setFlag(bean.getFlag());
                    monPemberianObatEntity.setAction(bean.getAction());
                    monPemberianObatEntity.setCreatedDate(bean.getCreatedDate());
                    monPemberianObatEntity.setCreatedWho(bean.getCreatedWho());
                    monPemberianObatEntity.setLastUpdate(bean.getLastUpdate());
                    monPemberianObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        monPemberianObatDao.addAndSave(monPemberianObatEntity);
                    } catch (HibernateException e){
                        logger.error("[PlanKegiatanRawatBoImpl.saveAddPlanKegiatan] ERROR. ",e);
                        throw new GeneralBOException("[PlanKegiatanRawatBoImpl.saveAddPlanKegiatan] ERROR. "+e);
                    }

                    ItSimrsPlanKegiatanRawatEntity planEntity = new ItSimrsPlanKegiatanRawatEntity();
                    planEntity.setIdKategori(monPemberianObat.getId());
                    planEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    planEntity.setId(getNextPlanId());
                    planEntity.setWaktu(monPemberianObat.getWaktu());
                    planEntity.setTglMulai(bean.getTglMulai());
                    planEntity.setTglSelesai(bean.getTglSelesai());
                    planEntity.setKeterangan(monPemberianObat.getCatatanDokter());
                    planEntity.setBranchId(bean.getBranchId());
                    planEntity.setJenisKegiatan(monPemberianObat.getKategori());
                    planEntity.setFlag(bean.getFlag());
                    planEntity.setAction(bean.getAction());
                    planEntity.setCreatedDate(bean.getCreatedDate());
                    planEntity.setCreatedWho(bean.getCreatedWho());
                    planEntity.setLastUpdate(bean.getLastUpdate());
                    planEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    planEntities.add(planEntity);

                }
            }


            // save to plan kegiatan rawat
            if (planEntities.size() > 0){
                for (ItSimrsPlanKegiatanRawatEntity planKegiatanRawatEntity : planEntities){
                    try {
                        planKegiatanRawatDao.addAndSave(planKegiatanRawatEntity);
                    } catch (HibernateException e){
                        logger.error("[PlanKegiatanRawatBoImpl.saveAddPlanKegiatan] ERROR. ",e);
                        throw new GeneralBOException("[PlanKegiatanRawatBoImpl.saveAddPlanKegiatan] ERROR. "+e);
                    }
                }
            }


        } else {
            logger.error("[PlanKegiatanRawatBoImpl.getListEntityPlanKegiatan] ERROR. data kosong.");
            throw new GeneralBOException("[PlanKegiatanRawatBoImpl.getListEntityPlanKegiatan] ERROR. data kosong.");
        }
    }

    private String getNextPlanId(){
        String id = "";
        try {
            id = planKegiatanRawatDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getNextPlanId] Error when get seq ",e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextPlanId] Error when get seq "+e.getMessage());
        }

        if (!"".equalsIgnoreCase(id)){
            id = "PLR"+id;
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
        if (!"".equalsIgnoreCase(id)){
            id = "OCR"+id;
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
        if (!"".equalsIgnoreCase(id)){
            id = "POT"+id;
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
        if (!"".equalsIgnoreCase(id)){
            id = "OVS"+id;
        }
        return id;
    }

    public void setPlanKegiatanRawatDao(PlanKegiatanRawatDao planKegiatanRawatDao) {
        this.planKegiatanRawatDao = planKegiatanRawatDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    public void setMonVitalSignDao(MonVitalSignDao monVitalSignDao) {
        this.monVitalSignDao = monVitalSignDao;
    }

    public void setMonCairanDao(MonCairanDao monCairanDao) {
        this.monCairanDao = monCairanDao;
    }

    public void setMonPemberianObatDao(MonPemberianObatDao monPemberianObatDao) {
        this.monPemberianObatDao = monPemberianObatDao;
    }
}
