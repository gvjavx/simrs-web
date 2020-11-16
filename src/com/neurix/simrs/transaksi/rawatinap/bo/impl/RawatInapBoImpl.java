package com.neurix.simrs.transaksi.rawatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.rekananops.dao.RekananOpsDao;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.dao.TempatTidurDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganTempatTidurEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.ruangan.model.TempatTidur;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.moncairan.dao.MonCairanDao;
import com.neurix.simrs.transaksi.moncairan.model.ItSimrsMonCairanEntity;
import com.neurix.simrs.transaksi.moncairan.model.MonCairan;
import com.neurix.simrs.transaksi.monpemberianobat.dao.MonPemberianObatDao;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import com.neurix.simrs.transaksi.monpemberianobat.model.MonPemberianObat;
import com.neurix.simrs.transaksi.monvitalsign.dao.MonVitalSignDao;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.plankegiatanrawat.dao.PlanKegiatanRawatDao;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.ItSimrsPlanKegiatanRawatEntity;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.PlanKegiatanRawat;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.rencanarawat.dao.ParameterRencanaRawatDao;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.skorrawatinap.dao.KategoriSkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.MasterSkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.ParameterSkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.SkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.joda.time.Days;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
    private PlanKegiatanRawatDao planKegiatanRawatDao;
    private RuanganDao ruanganDao;
    private HeaderCheckupDao headerCheckupDao;
    private CheckupDetailDao checkupDetailDao;
    private RiwayatTindakanDao riwayatTindakanDao;
    private RekananOpsDao rekananOpsDao;
    private TempatTidurDao tempatTidurDao;
    private UangMukaDao uangMukaDao;

    public List<ItSimrsRawatInapEntity> getListEntityByCriteria(RawatInap bean) throws GeneralBOException {
        logger.info("[RawatInapBoImpl.getListEntityByCriteria] Start >>>>>>>");
        List<ItSimrsRawatInapEntity> entityList = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdRawatInap() != null && !"".equalsIgnoreCase(bean.getIdRawatInap())) {
            hsCriteria.put("id_rawat_inap", bean.getIdRawatInap());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }
        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            hsCriteria.put("flag", bean.getFlag());
        }

        try {
            entityList = rawatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getListEntityByCriteria] Error when get data detail checkup entity ", e);
            throw new GeneralBOException("Error when get data detail checkup entity " + e.getMessage());
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
        if (bean != null) {
            try {
                results = rawatInapDao.getSearchRawatInap(bean);
            } catch (HibernateException e) {
                logger.error("[RawatInapBoImpl.getSearchRawatInap] Error when get data rawat inap", e);
                throw new GeneralBOException("Error when get data detail checkup" + e.getMessage());
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
        if (bean != null) {

            Map hsCriteria = new HashMap();

            if (bean.getNoCheckup() != null) {
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }
            if (bean.getIdDetailCheckup() != null) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getIdKategori() != null) {
                hsCriteria.put("id_kategori", bean.getIdKategori());
            }
            if (bean.getGroupId() != null) {
                hsCriteria.put("group_id", bean.getGroupId());
            }
            if (bean.getStDate() != null) {
                hsCriteria.put("date", bean.getStDate());
            }

            if (bean.getGroupId() != null && !"".equalsIgnoreCase(bean.getGroupId())) {
                try {
                    skorRanapEntities = skorRanapDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[RawatInapBoImpl.getListSkorRanap] ERROR", e);
                    throw new GeneralBOException("[RawatInapBoImpl.getListSkorRanap] ERROR" + e.getMessage());
                }
            }


            if (skorRanapEntities.size() == 0) {

                hsCriteria = new HashMap();
                hsCriteria.put("id_kategori", bean.getIdKategori());

                List<ImSimrsParameterSkorRanapEntity> parameterSkorRanapEntities = new ArrayList<>();
                try {
                    parameterSkorRanapEntities = parameterSkorRanapDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[RawatInapBoImpl.getListSkorRanap] ERROR", e);
                    throw new GeneralBOException("[RawatInapBoImpl.getListSkorRanap] ERROR" + e.getMessage());
                }

                if (parameterSkorRanapEntities.size() > 0) {
                    SkorRanap skorRanap;
                    for (ImSimrsParameterSkorRanapEntity parameter : parameterSkorRanapEntities) {
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
                for (ItSimrsSkorRanapEntity skorRanapEntity : skorRanapEntities) {
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
                    skorRanap.setStDate(stringDate(skorRanapEntity.getCreatedDate()));
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
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.ImSimrsKategoriSkorRanapEntity] ERROR", e);
            throw new GeneralBOException("[RawatInapBoImpl.ImSimrsKategoriSkorRanapEntity] ERROR" + e.getMessage());
        }

        ImSimrsKategoriSkorRanapEntity kategoriSkorRanapEntity = new ImSimrsKategoriSkorRanapEntity();
        if (kategoriSkorRanapEntities.size() > 0) {
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
        if (skors.size() > 0) {
            String groupId = "GRS" + getNextGroupSkorRanap();
            for (ItSimrsSkorRanapEntity entity : skors) {
                entity.setId("SKI" + getNextSkorRanap());
                entity.setGroupId(groupId);

                try {
                    skorRanapDao.addAndSave(entity);
                    response.setStatus("success");
                } catch (HibernateException e) {
                    logger.error("[RawatInapBoImpl.saveAddSkorRanap] ERROR ", e);
                    response.setStatus("error");
                    response.setMsg("[RawatInapBoImpl.saveAddSkorRanap] ERROR " + e.getMessage());
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
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getListMonVitalSign] ERROR", e);
            throw new GeneralBOException("[RawatInapBoImpl.getListMonVitalSign] ERROR" + e.getMessage());
        }

        List<MonVitalSign> monVitalSigns = new ArrayList<>();
        if (monVitalSignEntities.size() > 0) {
            MonVitalSign monVitalSign;
            for (ItSimrsMonVitalSignEntity entity : monVitalSignEntities) {

                boolean isShow = isShowDataByPlanKegiatan(entity.getId());

                if (isShow) {
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
                    monVitalSign.setStDate(stringDate(entity.getCreatedDate()));
                    monVitalSign.setTb(entity.getTb());
                    monVitalSign.setBb(entity.getBb());
                    monVitalSigns.add(monVitalSign);
                } else if (bean.getIsMobile().equalsIgnoreCase("Y")) {
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
                    monVitalSign.setStDate(stringDate(entity.getCreatedDate()));
                    monVitalSign.setTb(entity.getTb());
                    monVitalSign.setBb(entity.getBb());
                    monVitalSigns.add(monVitalSign);
                }
            }
        }
        return monVitalSigns;
    }

    @Override
    public CrudResponse saveMonVitalSign(ItSimrsMonVitalSignEntity bean) {
        CrudResponse response = new CrudResponse();
        response.setStatus("error");
        response.setMsg("[RawatInapBoImpl.saveMonVitalSign] bean is null");
        if (bean != null) {
            bean.setId("OVS" + getNextMonVitalSign());
            try {
                monVitalSignDao.addAndSave(bean);
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("[RawatInapBoImpl.saveMonVitalSign] ERROR " + e.getMessage());
            }
        }
        return response;
    }

    @Override
    public void saveUpdateMonVitalSign(ItSimrsMonVitalSignEntity bean) throws GeneralBOException {

        Map hsCriteria = new HashMap();
        hsCriteria.put("id", bean.getId());

        List<ItSimrsMonVitalSignEntity> monVitalSignEntities = new ArrayList<>();
        try {
            monVitalSignEntities = monVitalSignDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.saveUpdateMonVitalSign] ERROR. ", e);
            throw new GeneralBOException("[RawatInapBoImpl.saveUpdateMonVitalSign] ERROR. " + e);
        }

        if (monVitalSignEntities.size() > 0) {
            for (ItSimrsMonVitalSignEntity vitalSignEntity : monVitalSignEntities) {
                vitalSignEntity.setJam(bean.getJam());
                vitalSignEntity.setNadi(bean.getNadi());
                vitalSignEntity.setNafas(bean.getNafas());
                vitalSignEntity.setSuhu(bean.getSuhu());
                vitalSignEntity.setTensi(bean.getTensi());
                vitalSignEntity.setTb(bean.getTb());
                vitalSignEntity.setBb(bean.getBb());
                vitalSignEntity.setAction(bean.getAction());
                vitalSignEntity.setLastUpdate(bean.getLastUpdate());
                vitalSignEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    monVitalSignDao.updateAndSave(vitalSignEntity);
                } catch (HibernateException e) {
                    logger.error("[RawatInapBoImpl.saveUpdateMonVitalSign] ERROR. ", e);
                    throw new GeneralBOException("[RawatInapBoImpl.saveUpdateMonVitalSign] ERROR. " + e);
                }

                updateFlagKegiatanRawat(vitalSignEntity.getId(), bean.getLastUpdateWho(), bean.getLastUpdate());
            }
        }
    }

    private boolean isShowDataByPlanKegiatan(String idKategori) {
        boolean isShow = false;

        ItSimrsPlanKegiatanRawatEntity plan = new ItSimrsPlanKegiatanRawatEntity();
        try {
            plan = planKegiatanRawatDao.getById("idKategori", idKategori);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.saveUpdateMonVitalSign] ERROR. ", e);
            throw new GeneralBOException("[RawatInapBoImpl.saveUpdateMonVitalSign] ERROR. " + e);
        }

        if (plan != null && plan.getId() != null) {
            if ("Y".equalsIgnoreCase(plan.getFlagDikerjakan())) {
                isShow = true;
            }
        } else {
            isShow = true;
        }

        return isShow;
    }

    private ItSimrsPlanKegiatanRawatEntity getPlanKegiatanRawatByIdKategori(String idKategori) throws GeneralBOException {

        ItSimrsPlanKegiatanRawatEntity planKegiatanRawatEntity = new ItSimrsPlanKegiatanRawatEntity();

        try {
            planKegiatanRawatEntity = planKegiatanRawatDao.getById("idKategori", idKategori);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.saveUpdateMonVitalSign] ERROR. ", e);
            throw new GeneralBOException("[RawatInapBoImpl.saveUpdateMonVitalSign] ERROR. " + e);
        }

        return planKegiatanRawatEntity;
    }

    private void updateFlagKegiatanRawat(String idKategori, String user, Timestamp time) {

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_kategori", idKategori);

        List<ItSimrsPlanKegiatanRawatEntity> plans = new ArrayList<>();
        try {
            plans = planKegiatanRawatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.updateFlagKegiatanRawat] ERROR. ", e);
            throw new GeneralBOException("[RawatInapBoImpl.updateFlagKegiatanRawat] ERROR. " + e);
        }

        if (plans.size() > 0) {
            for (ItSimrsPlanKegiatanRawatEntity planEntity : plans) {
                planEntity.setLastUpdate(time);
                planEntity.setLastUpdateWho(user);
                planEntity.setAction("U");
                planEntity.setFlagDikerjakan("Y");

                try {
                    planKegiatanRawatDao.updateAndSave(planEntity);
                } catch (HibernateException e) {
                    logger.error("[RawatInapBoImpl.updateFlagKegiatanRawat] ERROR. ", e);
                    throw new GeneralBOException("[RawatInapBoImpl.updateFlagKegiatanRawat] ERROR. " + e);
                }
            }
        }
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
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getListMonCairan] ERROR", e);
            throw new GeneralBOException("[RawatInapBoImpl.getListMonCairan] ERROR" + e.getMessage());
        }

        List<MonCairan> monCairans = new ArrayList<>();
        if (monCairanEntities.size() > 0) {
            MonCairan monCairan;
            for (ItSimrsMonCairanEntity entity : monCairanEntities) {

                boolean isShow = isShowDataByPlanKegiatan(entity.getId());
                if (isShow) {
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
                    monCairan.setStDate(stringDate(entity.getCreatedDate()));
                    monCairans.add(monCairan);
                } else if (bean.getIsMobile().equalsIgnoreCase("Y")) {
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
                    monCairan.setStDate(stringDate(entity.getCreatedDate()));
                    monCairans.add(monCairan);
                }
            }
        }
        return monCairans;
    }

    @Override
    public CrudResponse saveMonCairan(ItSimrsMonCairanEntity bean) {
        CrudResponse response = new CrudResponse();
        response.setStatus("error");
        response.setMsg("[RawatInapBoImpl.saveMonCairan] bean is null");
        if (bean != null) {
            bean.setId("OCR" + getNextMonCairan());
            try {
                monCairanDao.addAndSave(bean);
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("[RawatInapBoImpl.saveMonCairan] ERROR " + e.getMessage());
            }
        }
        return response;
    }

    @Override
    public void saveUpdateMonCairan(ItSimrsMonCairanEntity bean) throws GeneralBOException {

        Map hsCriteria = new HashMap();
        hsCriteria.put("id", bean.getId());

        List<ItSimrsMonCairanEntity> monCairanEntities = new ArrayList<>();

        try {
            monCairanEntities = monCairanDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.saveUpdateMonCairan] ERROR", e);
            throw new GeneralBOException("[RawatInapBoImpl.saveUpdateMonCairan] ERROR" + e.getMessage());
        }

        if (monCairanEntities.size() > 0) {
            for (ItSimrsMonCairanEntity monCairanEntity : monCairanEntities) {

                monCairanEntity.setMacamCairan(bean.getMacamCairan());
                monCairanEntity.setMelalui(bean.getMelalui());
                monCairanEntity.setJumlah(bean.getJumlah());
                monCairanEntity.setJamMulai(bean.getJamMulai());
                monCairanEntity.setJamSelesai(bean.getJamSelesai());
                monCairanEntity.setCekTambahanObat(bean.getCekTambahanObat());
                monCairanEntity.setJamUkurBuang(bean.getJamUkurBuang());
                monCairanEntity.setDari(bean.getDari());
                monCairanEntity.setBalanceCairan(bean.getBalanceCairan());
                monCairanEntity.setKeterangan(bean.getKeterangan());
                monCairanEntity.setSisa(bean.getSisa());
                monCairanEntity.setAction(bean.getAction());
                monCairanEntity.setLastUpdate(bean.getLastUpdate());
                monCairanEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    monCairanDao.updateAndSave(monCairanEntity);
                } catch (HibernateException e) {
                    logger.error("[RawatInapBoImpl.saveUpdateMonCairan] ERROR", e);
                    throw new GeneralBOException("[RawatInapBoImpl.saveUpdateMonCairan] ERROR" + e.getMessage());
                }

                updateFlagKegiatanRawat(monCairanEntity.getId(), bean.getLastUpdateWho(), bean.getLastUpdate());
            }
        }

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
        if (bean.getKategori() != null)
            hsCriteria.put("kategori", bean.getKategori());

        List<ItSimrsMonPemberianObatEntity> pemberianObatEntities = new ArrayList<>();
        try {
            pemberianObatEntities = monPemberianObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getListMonCairan] ERROR", e);
            throw new GeneralBOException("[RawatInapBoImpl.getListMonCairan] ERROR" + e.getMessage());
        }

        List<MonPemberianObat> monPemberianObats = new ArrayList<>();
        if (pemberianObatEntities.size() > 0) {
            MonPemberianObat monPemberianObat;
            for (ItSimrsMonPemberianObatEntity entity : pemberianObatEntities) {

                boolean isShow = isShowDataByPlanKegiatan(entity.getId());
                if (isShow) {
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
                    monPemberianObat.setStDate(stringDate(entity.getCreatedDate()));
                    monPemberianObat.setKategori(entity.getKategori());
                    monPemberianObats.add(monPemberianObat);
                } else if (bean.getIsMobile().equalsIgnoreCase("Y")) {
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
                    monPemberianObat.setStDate(stringDate(entity.getCreatedDate()));
                    monPemberianObat.setKategori(entity.getKategori());
                    monPemberianObats.add(monPemberianObat);
                }
            }
        }
        return monPemberianObats;
    }

    @Override
    public CrudResponse saveMonPemberianObat(ItSimrsMonPemberianObatEntity bean) {
        CrudResponse response = new CrudResponse();
        response.setStatus("error");
        response.setMsg("[RawatInapBoImpl.saveMonPemberianObat] bean is null");
        if (bean != null) {
            bean.setId("POT" + getNextMonPemberianObat());
            try {
                monPemberianObatDao.addAndSave(bean);
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("[RawatInapBoImpl.saveMonPemberianObat] ERROR " + e.getMessage());
            }
        }
        return response;
    }

    @Override
    public void saveUpdateMonPemberianObat(ItSimrsMonPemberianObatEntity bean) throws GeneralBOException {

        Map hsCriteria = new HashMap();
        hsCriteria.put("id", bean.getId());

        List<ItSimrsMonPemberianObatEntity> monPemberianObatEntities = new ArrayList<>();
        try {
            monPemberianObatEntities = monPemberianObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.saveUpdateMonPemberianObat] ERROR", e);
            throw new GeneralBOException("[RawatInapBoImpl.saveUpdateMonPemberianObat] ERROR" + e.getMessage());
        }

        if (monPemberianObatEntities.size() > 0) {
            for (ItSimrsMonPemberianObatEntity obatEntity : monPemberianObatEntities) {

                obatEntity.setCaraPemberian(bean.getCaraPemberian());
                obatEntity.setDosis(bean.getDosis());
                obatEntity.setSkinTes(bean.getSkinTes());
                obatEntity.setWaktu(bean.getWaktu());
                obatEntity.setKeterangan(bean.getKeterangan());
                obatEntity.setAction(bean.getAction());
                obatEntity.setLastUpdate(bean.getLastUpdate());
                obatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    monPemberianObatDao.updateAndSave(obatEntity);
                } catch (HibernateException e) {
                    logger.error("[RawatInapBoImpl.saveUpdateMonPemberianObat] ERROR", e);
                    throw new GeneralBOException("[RawatInapBoImpl.saveUpdateMonPemberianObat] ERROR" + e.getMessage());
                }

                updateFlagKegiatanRawat(obatEntity.getId(), bean.getLastUpdateWho(), bean.getLastUpdate());
            }
        }
    }

    @Override
    public List<MonVitalSign> getListGraf(MonVitalSign bean) {

        List<MonVitalSign> monVitalSigns = monVitalSignDao.getListGraf(bean);
        if (monVitalSigns.size() > 0) {
            for (MonVitalSign monVitalSign : monVitalSigns) {
//                SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat f = new SimpleDateFormat("MM/dd");
                String stDate = f.format(monVitalSign.getCreatedDate());
                monVitalSign.setStDate("tgl : " + stDate + ", jam : " + monVitalSign.getJam());
            }
        }
        return monVitalSigns;
    }

    @Override
    public List<ImSimrsKategoriSkorRanapEntity> getListKategoriSkorRanapByHead(String head) {
        Map hsCriteria = new HashMap();
        hsCriteria.put("head", head);
        return kategoriSkorRanapDao.getByCriteria(hsCriteria);
    }

    @Override
    public List<RawatInap> getByCriteria(RawatInap bean) throws GeneralBOException {
        List<RawatInap> rawatInapList = new ArrayList<>();
        if (bean != null) {
            List<ItSimrsRawatInapEntity> list = getListEntityByCriteria(bean);
            if (list.size() > 0) {
                RawatInap rawatInap;
                for (ItSimrsRawatInapEntity entity : list) {
                    rawatInap = new RawatInap();
                    rawatInap.setIdDetailCheckup(entity.getIdDetailCheckup());
                    rawatInap.setIdRawatInap(entity.getIdRawatInap());
                    rawatInap.setNoCheckup(entity.getNoCheckup());
                    rawatInap.setIdRuangan(entity.getIdRuangan());
                    rawatInap.setNamaRangan(entity.getNamaRangan());
                    rawatInap.setTglMasuk(entity.getTglMasuk());
                    rawatInap.setTglKeluar(entity.getTglKeluar());
                    rawatInapList.add(rawatInap);
                }
            }
        }
        return rawatInapList;
    }

    @Override
    public MonVitalSign getMonVitalSignById(String id) throws GeneralBOException {

        ItSimrsMonVitalSignEntity entity = new ItSimrsMonVitalSignEntity();

        try {
            entity = monVitalSignDao.getById("id", id);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getMonVitalSignById] ERROR", e);
            throw new GeneralBOException("[RawatInapBoImpl.getMonVitalSignById] ERROR" + e.getMessage());
        }

        MonVitalSign monVitalSign = new MonVitalSign();
        if (entity != null && entity.getId() != null) {
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
            monVitalSign.setStDate(stringDate(entity.getCreatedDate()));
            monVitalSign.setTb(entity.getTb());
            monVitalSign.setBb(entity.getBb());
        }

        return monVitalSign;
    }

    @Override
    public MonCairan getMonCairanById(String id) throws GeneralBOException {

        ItSimrsMonCairanEntity entity = new ItSimrsMonCairanEntity();

        try {
            entity = monCairanDao.getById("id", id);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getMonCairanById] ERROR", e);
            throw new GeneralBOException("[RawatInapBoImpl.getMonCairanById] ERROR" + e.getMessage());
        }

        MonCairan monCairan = new MonCairan();
        if (entity != null && entity.getId() != null) {
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
            monCairan.setStDate(stringDate(entity.getCreatedDate()));
        }
        return monCairan;
    }

    @Override
    public MonPemberianObat getMonPemberianObatById(String id) throws GeneralBOException {

        ItSimrsMonPemberianObatEntity entity = new ItSimrsMonPemberianObatEntity();

        try {
            entity = monPemberianObatDao.getById("id", id);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getMonCairanById] ERROR", e);
            throw new GeneralBOException("[RawatInapBoImpl.getMonCairanById] ERROR" + e.getMessage());
        }

        MonPemberianObat monPemberianObat = new MonPemberianObat();
        if (entity != null && entity.getId() != null) {
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
            monPemberianObat.setStDate(stringDate(entity.getCreatedDate()));
            monPemberianObat.setKategori(entity.getKategori());
        }
        return monPemberianObat;
    }

    private String stringDate(Timestamp datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(datetime);
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
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getListSumSkorRanap] ERROR ", e);
        }

        return skorRanaps;
    }

    @Override
    public RawatInap getLastUsedRoom(String id) throws GeneralBOException {
        return rawatInapDao.getLastRuanganById(id);
    }

    @Override
    public List<RawatInap> getListTppri(RawatInap bean) throws GeneralBOException {
        return rawatInapDao.getListTppri(bean);
    }

    @Override
    public CrudResponse saveAdd(RawatInap bean) throws GeneralBOException {
        logger.info("[RawatInapBoImpl.saveAdd] Start >>>>>>>>");
        CrudResponse response = new CrudResponse();
        if ("rawat_intensif".equalsIgnoreCase(bean.getTindakLanjut()) ||
                "rawat_isolasi".equalsIgnoreCase(bean.getTindakLanjut()) ||
                "kamar_operasi".equalsIgnoreCase(bean.getTindakLanjut()) ||
                "ruang_bersalin".equalsIgnoreCase(bean.getTindakLanjut()) ||
                "rawat_inap".equalsIgnoreCase(bean.getTindakLanjut()) ||
                "kembali_ke_inap".equalsIgnoreCase(bean.getTindakLanjut()) ||
                "rr".equalsIgnoreCase(bean.getTindakLanjut())) {

            if ("rawat_inap".equalsIgnoreCase(bean.getTindakLanjut())) {
                response = updateRawatInap(bean);
            }

            if (!"Y".equalsIgnoreCase(bean.getIsStay())) {
                response = updateRawatInap(bean);
            }

            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(bean.getIdDetailCheckup());
            detailCheckup.setTindakLanjut(bean.getTindakLanjut());
            detailCheckup.setLastUpdate(bean.getLastUpdate());
            detailCheckup.setLastUpdateWho(bean.getLastUpdateWho());
            if("Y".equalsIgnoreCase(bean.getIsStay())){
                detailCheckup.setIsStay(bean.getIsStay());
            }
            if("kembali_ke_inap".equalsIgnoreCase(bean.getTindakLanjut())){
                detailCheckup.setIsStay("N");
            }
            response = updateDetail(detailCheckup);
            if ("success".equalsIgnoreCase(response.getStatus())) {
                if (!"kembali_ke_inap".equalsIgnoreCase(bean.getTindakLanjut())){
                    ItSimrsRawatInapEntity entity = new ItSimrsRawatInapEntity();
                    TempatTidur tempatTidur = new TempatTidur();
                    if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
                        TempatTidur tt = new TempatTidur();
                        tt.setIdTempatTidur(bean.getIdRuangan());
                        List<TempatTidur> tempatTidurList = tempatTidurDao.getDataTempatTidur(tt);
                        if (tempatTidurList.size() > 0) {
                            tempatTidur = tempatTidurList.get(0);
                            if (tempatTidur != null) {
                                entity.setIdRawatInap("RNP" + rawatInapDao.getNextId());
                                entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                                entity.setNoCheckup(bean.getNoCheckup());
                                entity.setIdRuangan(tempatTidur.getIdTempatTidur());
                                entity.setTarif(tempatTidur.getTarif());
                                entity.setNamaRangan(tempatTidur.getNamaRuangan());
                                entity.setNoRuangan(tempatTidur.getNoRuangan());
                                entity.setFlag("Y");
                                entity.setAction("C");
                                entity.setCreatedDate(bean.getCreatedDate());
                                entity.setLastUpdate(bean.getCreatedDate());
                                entity.setCreatedWho(bean.getCreatedWho());
                                entity.setLastUpdateWho(bean.getCreatedWho());
                                entity.setTglMasuk(bean.getCreatedDate());
                                entity.setStatus("1");
                                try {
                                    rawatInapDao.addAndSave(entity);
                                    MtSimrsRuanganTempatTidurEntity tempatTidurEntity = tempatTidurDao.getById("idTempatTidur", tempatTidur.getIdTempatTidur());
                                    if(tempatTidurEntity != null) {
                                        tempatTidurEntity.setStatus("N");
                                        tempatTidurEntity.setAction("U");
                                        tempatTidurEntity.setLastUpdate(bean.getCreatedDate());
                                        tempatTidurEntity.setLastUpdateWho(bean.getCreatedWho());
                                        try {
                                            tempatTidurDao.updateAndSave(tempatTidurEntity);
                                            response.setStatus("success");
                                            response.setMsg("berhasil");
                                        } catch (HibernateException e) {
                                            response.setStatus("error");
                                            response.setMsg("Error When Saving data detail checkup " + e.getMessage());
                                            logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan ", e);
                                        }
                                    }

                                } catch (HibernateException e) {
                                    response.setStatus("error");
                                    response.setMsg(" Error when save add Rawat Inap " + e.getMessage());
                                    logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when save add Rawat Inap ", e);
                                }
                            }
                        }
                    }else{
                        response.setStatus("error");
                        response.setMsg("Errro Id Ruangan tidak ada...!");
                    }
                }
            }else{
                response.setStatus("error");
                response.setMsg("Errro When update detail checkup...!");
            }
        } else {
            HeaderCheckup checkup = new HeaderCheckup();
            checkup.setNoCheckup(bean.getNoCheckup());
            checkup.setLastUpdateWho(bean.getLastUpdateWho());
            checkup.setLastUpdate(bean.getLastUpdate());
            response = updateCheckup(checkup);
            if ("success".equalsIgnoreCase(response.getStatus())) {
                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setIdDetailCheckup(bean.getIdDetailCheckup());
                detailCheckup.setLastUpdateWho(bean.getLastUpdateWho());
                detailCheckup.setLastUpdate(bean.getLastUpdate());
                detailCheckup.setTindakLanjut(bean.getTindakLanjut());
                detailCheckup.setCatatan(bean.getCatatan());
                detailCheckup.setKeteranganSelesai(bean.getKeteranganSelesai());
                detailCheckup.setStatusPeriksa("3");
                response = updateDetail(detailCheckup);
                if ("success".equalsIgnoreCase(response.getStatus())) {
                    response = updateRawatInap(bean);
                }
            }
        }
        if (bean.getIdRuangLama() != null && !"".equalsIgnoreCase(bean.getIdRuangLama())) {
            if (!"Y".equalsIgnoreCase(bean.getIsStay())) {
                MtSimrsRuanganTempatTidurEntity tempatTidurEntity = tempatTidurDao.getById("idTempatTidur", bean.getIdRuangLama());
                if(tempatTidurEntity != null) {
                    tempatTidurEntity.setStatus("Y");
                    tempatTidurEntity.setAction("U");
                    tempatTidurEntity.setLastUpdate(bean.getCreatedDate());
                    tempatTidurEntity.setLastUpdateWho(bean.getCreatedWho());
                    try {
                        tempatTidurDao.updateAndSave(tempatTidurEntity);
                        response.setStatus("success");
                        response.setMsg("berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Error When Saving data detail checkup " + e.getMessage());
                        logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan ", e);
                    }
                }
            }
        }
        logger.info("[RawatInapBoImpl.saveAdd] End <<<<<<<<");
        return response;
    }

    @Override
    public List<RawatInap> getRuanganRawatInap(RawatInap bean) throws GeneralBOException {
        return rawatInapDao.getRuanganRawatInap(bean);
    }

    private CrudResponse updateCheckup(HeaderCheckup bean) {
        logger.info("[RawatInapBoImpl.saveAdd] Start <<<<<<<<");
        CrudResponse response = new CrudResponse();
        ItSimrsHeaderChekupEntity entity = new ItSimrsHeaderChekupEntity();
        try {
            entity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
            if (entity != null) {
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setAction("U");
                entity.setTglKeluar(bean.getLastUpdate());
                try {
                    headerCheckupDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Error, " + e.getMessage());
                }
            } else {
                response.setStatus("error");
                response.setMsg("No Checkup Tidak ditemukan...!");
            }
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Error, " + e.getMessage());
        }
        logger.info("[RawatInapBoImpl.saveAdd] End <<<<<<<<");
        return response;
    }

    private CrudResponse updateDetail(HeaderDetailCheckup bean) {
        logger.info("[RawatInapBoImpl.updateDetail] Start <<<<<<<<");
        CrudResponse response = new CrudResponse();
        ItSimrsHeaderDetailCheckupEntity entity = new ItSimrsHeaderDetailCheckupEntity();
        try {
            entity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
            if (entity != null) {
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setAction("U");
                if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                    entity.setStatusPeriksa(bean.getStatusPeriksa());
                }
                if (bean.getTindakLanjut() != null && !"".equalsIgnoreCase(bean.getTindakLanjut())) {
                    entity.setTindakLanjut(bean.getTindakLanjut());
                }
                if (bean.getCatatan() != null && !"".equalsIgnoreCase(bean.getCatatan())) {
                    entity.setCatatan(bean.getCatatan());
                }
                if (bean.getKeteranganSelesai() != null && !"".equalsIgnoreCase(bean.getKeteranganSelesai())) {
                    entity.setKeteranganSelesai(bean.getKeteranganSelesai());
                }
                if(bean.getIsStay() != null && !"".equalsIgnoreCase(bean.getIsStay())){
                    if("N".equalsIgnoreCase(bean.getIsStay())){
                        entity.setIsStay(null);
                    }else{
                        entity.setIsStay(bean.getIsStay());
                    }
                }
                try {
                    checkupDetailDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Error, " + e.getMessage());
                }
            } else {
                response.setStatus("error");
                response.setMsg("ID Checkup Tidak ditemukan...!");
            }
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Error, " + e.getMessage());
        }
        logger.info("[RawatInapBoImpl.updateDetail] End <<<<<<<<");
        return response;
    }

    private CrudResponse updateRawatInap(RawatInap bean) {
        logger.info("[RawatInapBoImpl.updateRawatInap] Start <<<<<<<<");
        CrudResponse response = new CrudResponse();
        ItSimrsRawatInapEntity entity = new ItSimrsRawatInapEntity();
        try {
            entity = rawatInapDao.getById("idRawatInap", bean.getIdRawatInap());
            if (entity != null) {
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setAction("U");
                entity.setTglKeluar(bean.getLastUpdate());
                entity.setStatus("3");

                try {
                    rawatInapDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Error, " + e.getMessage());
                }
            } else {
                response.setStatus("error");
                response.setMsg("ID rawat inap Tidak ditemukan...!");
            }
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Error, " + e.getMessage());
        }
        logger.info("[RawatInapBoImpl.updateRawatInap] End <<<<<<<<");
        return response;
    }

    public CrudResponse updateRiwayatKamar(RawatInap bean) {
        logger.info("[RawatInapBoImpl.saveAdd] Start <<<<<<<<");
        CrudResponse response = new CrudResponse();
        List<RawatInap> rawatInapList = new ArrayList<>();
        try {
            rawatInapList = rawatInapDao.getRuanganRawatInap(bean.getIdDetailCheckup());
            if (rawatInapList.size() > 0) {
                for (RawatInap rawatInap: rawatInapList){
                    Map hsCriteria = new HashMap();
                    hsCriteria.put("id_tindakan", rawatInap.getIdRawatInap());
                    List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntityList = new ArrayList<>();
                    try {
                        riwayatTindakanEntityList = riwayatTindakanDao.getByCriteria(hsCriteria);
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMsg("When insert to riwayat unutk kamar...!, "+e.getMessage());
                    }
                    if(riwayatTindakanEntityList.isEmpty()){
                        ItSimrsRiwayatTindakanEntity entity = new ItSimrsRiwayatTindakanEntity();
                        RekananOps ops = new RekananOps();
                        entity.setIdRiwayatTindakan("RWT"+riwayatTindakanDao.getNextSeq());
                        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                        entity.setIdTindakan(rawatInap.getIdRawatInap());
                        entity.setNamaTindakan("Kamar "+rawatInap.getNamaRangan()+" ("+rawatInap.getLamakamar()+" hari @Rp."+rawatInap.getTarif()+")");
                        entity.setKeterangan("kamar");

                        try {
                            ops = rekananOpsDao.getRekananOpsByIdDetail(rawatInap.getIdDetailCheckup(), CommonUtil.userBranchLogin());
                        }catch (HibernateException e){
                            response.setStatus("error");
                            response.setMsg("When insert to riwayat unutk kamar...!, "+e.getMessage());
                        }

                        if("rekanan".equalsIgnoreCase(bean.getIdJenisPeriksa()) && "Y".equalsIgnoreCase(ops.getIsBpjs())){
                            entity.setJenisPasien("bpjs");
                        }else{
                            entity.setJenisPasien(bean.getIdJenisPeriksa());
                        }

                        if("rekanan".equalsIgnoreCase(bean.getIdJenisPeriksa())){
                            if(ops.getDiskon() != null){
                                BigDecimal desimal = (new BigDecimal(rawatInap.getTarif().multiply(rawatInap.getLamakamar())).multiply(ops.getDiskon()));
                                entity.setTotalTarif(desimal);
                            }else{
                                entity.setTotalTarif(new BigDecimal(rawatInap.getTarif().multiply(rawatInap.getLamakamar())));
                            }
                        }else{
                            entity.setTotalTarif(new BigDecimal(rawatInap.getTarif().multiply(rawatInap.getLamakamar())));
                        }

                        entity.setAction(bean.getAction());
                        entity.setFlag(bean.getFlag());
                        entity.setCreatedDate(bean.getCreatedDate());
                        entity.setCreatedWho(bean.getCreatedWho());
                        entity.setLastUpdate(bean.getLastUpdate());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        entity.setTanggalTindakan(rawatInap.getCreatedDate());
                        entity.setIsKamar("Y");
                        entity.setIdRuangan(rawatInap.getIdRuangan());

                        try {
                            riwayatTindakanDao.addAndSave(entity);
                        }catch (HibernateException e){
                            logger.error("[RiwayatTindakanBoImpl.saveAdd] error when insert riwayat tindakan, Found error :["+e.getMessage()+"]");
                        }
                    }
                }
            } else {
                response.setStatus("error");
                response.setMsg("ID Detail Checkup Tidak ditemukan...!");
            }
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Error, " + e.getMessage());
        }
        logger.info("[RawatInapBoImpl.saveAdd] End <<<<<<<<");
        return response;
    }

    @Override
    public CrudResponse saveUangMuka(UangMuka bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = new ItSimrsUangMukaPendaftaranEntity();
            uangMukaPendaftaranEntity.setId("UM" + bean.getBranchId() + dateFormater("MM") + dateFormater("yy") + uangMukaDao.getNextId());
            uangMukaPendaftaranEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            uangMukaPendaftaranEntity.setFlag("Y");
            uangMukaPendaftaranEntity.setAction("C");
            uangMukaPendaftaranEntity.setCreatedDate(bean.getCreatedDate());
            uangMukaPendaftaranEntity.setCreatedWho(bean.getCreatedWho());
            uangMukaPendaftaranEntity.setLastUpdate(bean.getCreatedDate());
            uangMukaPendaftaranEntity.setLastUpdateWho(bean.getCreatedWho());
            uangMukaPendaftaranEntity.setJumlah(bean.getJumlah());

            try {
                uangMukaDao.addAndSave(uangMukaPendaftaranEntity);
                response.setStatus("success");
                response.setMsg("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Error ketika insert data ke databse, "+e.getMessage());
                logger.error("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
            }
        }
        return response;
    }

    @Override
    public List<UangMuka> getAllListUangMuka(UangMuka bean) throws GeneralBOException {
        return rawatInapDao.getAllListUangMuka(bean);
    }

    private String dateFormater(String type) {
        Date date = new Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    protected List<MtSimrsRuanganEntity> getListEntityRuangan(Ruangan bean) {
        logger.info("[RawatInapBoImpl.getListRuangan] Start >>>>>>>>>");

        List<MtSimrsRuanganEntity> results = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }
//        if (bean.getStatusRuangan() != null && !"".equalsIgnoreCase(bean.getStatusRuangan())) {
//            hsCriteria.put("status_ruangan", bean.getStatusRuangan());
//
//        }
        hsCriteria.put("flag", "Y");

        try {
            results = ruanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getListRuangan] Error When get data ruangan");
        }

        logger.info("[RawatInapBoImpl.getListRuangan] End <<<<<<<<<");
        return results;
    }

    private String getNextSkorRanap() {
        String id = "";
        try {
            id = skorRanapDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getNextSkorRanap] Error when get seq ", e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextSkorRanap] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextGroupSkorRanap() {
        String id = "";
        try {
            id = skorRanapDao.getNextGroupSeq();
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getNextGroupSkorRanap] Error when get seq ", e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextGroupSkorRanap] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextMonCairan() {
        String id = "";
        try {
            id = monCairanDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getNextMonCairan] Error when get seq ", e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextMonCairan] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextMonPemberianObat() {
        String id = "";
        try {
            id = monPemberianObatDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getNextMonPemberianObat] Error when get seq ", e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextMonPemberianObat] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextMonVitalSign() {
        String id = "";
        try {
            id = monVitalSignDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RawatInapBoImpl.getNextMonVitalSign] Error when get seq ", e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextMonVitalSign] Error when get seq " + e.getMessage());
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

    public void setPlanKegiatanRawatDao(PlanKegiatanRawatDao planKegiatanRawatDao) {
        this.planKegiatanRawatDao = planKegiatanRawatDao;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setRekananOpsDao(RekananOpsDao rekananOpsDao) {
        this.rekananOpsDao = rekananOpsDao;
    }

    public void setTempatTidurDao(TempatTidurDao tempatTidurDao) {
        this.tempatTidurDao = tempatTidurDao;
    }

    public void setUangMukaDao(UangMukaDao uangMukaDao) {
        this.uangMukaDao = uangMukaDao;
    }
}
