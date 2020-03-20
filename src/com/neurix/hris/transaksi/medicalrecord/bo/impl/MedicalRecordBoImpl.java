package com.neurix.hris.transaksi.medicalrecord.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biayapengobatan.dao.BiayaPengobatanDao;
import com.neurix.hris.master.biayapengobatan.model.ImHrisBiayaPengobatan;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.keluarga.dao.KeluargaDao;
import com.neurix.hris.master.keluarga.model.ImKeluargaEntity;
import com.neurix.hris.master.rsKerjasama.dao.RsKerjasamaDao;
import com.neurix.hris.master.rsKerjasama.model.ImRsKerjasamaEntity;
import com.neurix.hris.master.rsKerjasama.model.RsKerjasama;
import com.neurix.hris.master.rskelas.dao.RsKelasDao;
import com.neurix.hris.master.rskelas.model.ImHrisRsKelas;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.master.notif.model.ItNotifikasiEntity;
import com.neurix.hris.transaksi.medicalrecord.bo.MedicalRecordBo;
import com.neurix.hris.transaksi.medicalrecord.dao.BuktiPengobatanDao;
import com.neurix.hris.transaksi.medicalrecord.dao.MedicalRecordDao;
import com.neurix.hris.transaksi.medicalrecord.dao.PengobatanDao;
import com.neurix.hris.transaksi.medicalrecord.model.*;

import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class MedicalRecordBoImpl implements MedicalRecordBo {
    protected static transient Logger logger = Logger.getLogger(MedicalRecordBoImpl.class);

    private MedicalRecordDao medicalRecordDao;
    private BuktiPengobatanDao buktiPengobatanDao;
    private PengobatanDao pengobatanDao;
    private NotifikasiDao notifikasiDao;
    private BiodataDao biodataDao;
    private RsKerjasamaDao rsKerjasamaDao;
    private RsKelasDao rsKelasDao;
    private KeluargaDao keluargaDao;
    private BiayaPengobatanDao biayaPengobatanDao;
    private PersonilPositionDao personilPositionDao;
    private PositionDao positionDao;
    private DepartmentDao departmentDao;
    private BranchDao branchDao;

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public BiayaPengobatanDao getBiayaPengobatanDao() {
        return biayaPengobatanDao;
    }

    public void setBiayaPengobatanDao(BiayaPengobatanDao biayaPengobatanDao) {
        this.biayaPengobatanDao = biayaPengobatanDao;
    }

    public KeluargaDao getKeluargaDao() {
        return keluargaDao;
    }

    public void setKeluargaDao(KeluargaDao keluargaDao) {
        this.keluargaDao = keluargaDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public RsKerjasamaDao getRsKerjasamaDao() {
        return rsKerjasamaDao;
    }

    public void setRsKerjasamaDao(RsKerjasamaDao rsKerjasamaDao) {
        this.rsKerjasamaDao = rsKerjasamaDao;
    }

    public RsKelasDao getRsKelasDao() {
        return rsKelasDao;
    }

    public void setRsKelasDao(RsKelasDao rsKelasDao) {
        this.rsKelasDao = rsKelasDao;
    }

    public NotifikasiDao getNotifikasiDao() {
        return notifikasiDao;
    }

    public void setNotifikasiDao(NotifikasiDao notifikasiDao) {
        this.notifikasiDao = notifikasiDao;
    }

    public BuktiPengobatanDao getBuktiPengobatanDao() {
        return buktiPengobatanDao;
    }

    public void setBuktiPengobatanDao(BuktiPengobatanDao buktiPengobatanDao) {
        this.buktiPengobatanDao = buktiPengobatanDao;
    }

    public PengobatanDao getPengobatanDao() {
        return pengobatanDao;
    }

    public void setPengobatanDao(PengobatanDao pengobatanDao) {
        this.pengobatanDao = pengobatanDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MedicalRecordBoImpl.logger = logger;
    }

    public MedicalRecordDao getMedicalRecordDao() {
        return medicalRecordDao;
    }

    public void setMedicalRecordDao(MedicalRecordDao medicalRecordDao) {
        this.medicalRecordDao = medicalRecordDao;
    }

    @Override
    public void saveDelete(MedicalRecord bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(MedicalRecord bean) throws GeneralBOException {

    }

    @Override
    public MedicalRecord saveAdd(MedicalRecord bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<MedicalRecord> getByCriteria(MedicalRecord searchBean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.getByCriteria] start process >>>");
        // Mapping with collection and put
        List<MedicalRecord> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getMedicalRecordId() != null && !"".equalsIgnoreCase(searchBean.getMedicalRecordId())) {
                hsCriteria.put("medical_record_id", searchBean.getMedicalRecordId());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getTipePengobatan() != null && !"".equalsIgnoreCase(searchBean.getTipePengobatan())) {
                hsCriteria.put("tipe_pengobatan", searchBean.getTipePengobatan());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSampai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSampai()))) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalSampai());
                hsCriteria.put("tanggal_sampai", tanggalSelesai);
            }
            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ItHrisMedicalRecordEntity> itHrisMedicalRecordEntities = null;
            try {
                itHrisMedicalRecordEntities = medicalRecordDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itHrisMedicalRecordEntities != null){
                MedicalRecord returnData;
                for(ItHrisMedicalRecordEntity listEntity : itHrisMedicalRecordEntities){
                    returnData = new MedicalRecord();
                    returnData.setMedicalRecordId(listEntity.getMedicalRecordId());
                    returnData.setNamaBerobat(listEntity.getNamaBerobat());
                    returnData.setTipeOrangBerobat(listEntity.getTipeOrangBerobat());
                    returnData.setNip(listEntity.getNip());
                    returnData.setNote(listEntity.getNote());

                    returnData.setCreatedDate(listEntity.getCreatedDate());
                    returnData.setCreatedWho(listEntity.getCreateDateWho());
                    returnData.setFlag(listEntity.getFlag());
                    returnData.setAction(listEntity.getAction());
                    returnData.setRsKelasId(listEntity.getRsKelasId());
                    returnData.setRsId(listEntity.getRsId());

                    List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getListNip(listEntity.getNip());
                    if (personilPositionEntityList!=null){
                        for (ItPersonilPositionEntity personilPosition : personilPositionEntityList){
                            ImPosition imPosition = positionDao.getById("positionId",personilPosition.getPositionId());
                            if (imPosition!=null){
                                returnData.setPositionName(imPosition.getPositionName());
                                ImDepartmentEntity imDepartmentEntity = departmentDao.getById("departmentId",imPosition.getDepartmentId());
                                if (imDepartmentEntity!=null){
                                    returnData.setDivisiName(imDepartmentEntity.getDepartmentName());
                                }
                            }
                            hsCriteria = new HashMap();
                            hsCriteria.put("branch_id",personilPosition.getBranchId());
                            hsCriteria.put("flag","Y");
                            List<ImBranches> branchesList = branchDao.getByCriteria(hsCriteria);
                            if (branchesList!=null){
                                for(ImBranches imBranches : branchesList){
                                    returnData.setBranchName(imBranches.getBranchName());
                                }
                            }
                        }
                    }

                    if ("RI".equalsIgnoreCase(listEntity.getTipePengobatan())){
                        if (listEntity.getRsKelasId() != null){
                            hsCriteria = new HashMap();
                            hsCriteria.put("rs_kelas_id", listEntity.getRsKelasId());
                            hsCriteria.put("flag","Y");
                            List<ImHrisRsKelas> rsKelases = null;
                            try {
                                rsKelases = rsKelasDao.getByCriteria(hsCriteria);
                            } catch (HibernateException e) {
                                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                            if (rsKelases != null){
                                for (ImHrisRsKelas listKelas : rsKelases){
                                    returnData.setRsKelasName(listKelas.getKelas());
                                }
                            }
                        }
                        if (listEntity.getRsId() != null){
                            hsCriteria = new HashMap();
                            hsCriteria.put("rs_id",listEntity.getRsId());
                            hsCriteria.put("flag","Y");
                            List<ImRsKerjasamaEntity> rsKerjasamaEntities = null;
                            try {
                                rsKerjasamaEntities = rsKerjasamaDao.getByCriteria(hsCriteria);
                            } catch (HibernateException e) {
                                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }

                            if (rsKerjasamaEntities != null){
                                for (ImRsKerjasamaEntity listRs : rsKerjasamaEntities){
                                    returnData.setRsName(listRs.getRsName());
                                }
                            }
                        }
                    }else if("RJ".equalsIgnoreCase(listEntity.getTipePengobatan())){
                        returnData.setRsName(listEntity.getRsId());
                    }
                    if (listEntity.getKeluargaId() != null){
                        hsCriteria = new HashMap();
                        hsCriteria.put("keluarga_id",listEntity.getKeluargaId());
                        hsCriteria.put("flag","Y");

                        List<ImKeluargaEntity> keluargaEntities = null;

                        try {
                            keluargaEntities = keluargaDao.getByCriteria(hsCriteria);
                        } catch (HibernateException e) {
                            logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }

                        if (keluargaEntities != null){
                            for (ImKeluargaEntity listKeluarga : keluargaEntities){
                                returnData.setNamaKeluarga(listKeluarga.getName());
                            }
                        }
                    }
                    if (listEntity.getTanggalBerobat() != null){
                        returnData.setTanggalBerobat(listEntity.getTanggalBerobat());
                    }

                    if ("S".equalsIgnoreCase(listEntity.getTipeOrangBerobat())){
                        returnData.setTipeBerobatName("SENDIRI");
                    }
                    if ("K".equalsIgnoreCase(listEntity.getTipeOrangBerobat())){
                        returnData.setTipeBerobatName("KELUARGA");
                    }

                    returnData.setKeluargaId(listEntity.getKeluargaId());
                    returnData.setTipePengobatan(listEntity.getTipePengobatan());

                    if ("RI".equalsIgnoreCase(listEntity.getTipePengobatan())){
                        returnData.setTipePengobatanName("RAWAT INAP");
                    }
                    if ("RJ".equalsIgnoreCase(listEntity.getTipePengobatan())){
                        returnData.setTipePengobatanName("RAWAT JALAN");
                    }

                    returnData.setFlagSuratJaminan(listEntity.getFlagSuratJaminan());
                    if (listEntity.getNoSuratJaminan() != null && !"".equalsIgnoreCase(listEntity.getNoSuratJaminan())){
                        if ("Y".equalsIgnoreCase(listEntity.getFlagSuratJaminan())){
                            returnData.setNoSuratJaminan(listEntity.getNoSuratJaminan());
                            returnData.setPrint(true);
                        }
                    }



                    if("Y".equalsIgnoreCase(listEntity.getApproved())){
                        returnData.setEdit(false);
                        returnData.setPrint(false);
                        returnData.setKoreksi(true);
                        returnData.setEdit(true);
                        returnData.setIconApprove("<img src='\\"+ CommonConstant.APP_NAME +"\\pages\\images\\icon_success.ico'/>");
                    } else if ("N".equalsIgnoreCase(listEntity.getApproved())){
                        returnData.setApprove(true);
                        returnData.setIconApprove("<img src='\\"+ CommonConstant.APP_NAME +"\\pages\\images\\icon_failure.ico'/>");
                    } else {
                        returnData.setApprove(true);
                        returnData.setEdit(true);
                    }
                    if("Y".equalsIgnoreCase(listEntity.getFlagPayroll())){
                        returnData.setKoreksi(false);
                    }
                    if (!searchBean.isAdmin()){
                        returnData.setApprove(false);
                    }
                    if (searchBean.isAdmin()){
                        returnData.setAdmin(true);
                    }

                    List<ItHrisPengobatanEntity> itHrisPengobatanEntity = pengobatanDao.getPengobatanByMedicalid(listEntity.getMedicalRecordId());

                    if(itHrisPengobatanEntity.size() == 0){
                        returnData.setPrintSuratMedical(false);
                    }
                    if("Y".equalsIgnoreCase(listEntity.getFlagKoreksi())){
                        returnData.setDelete(false);
                        returnData.setEdit(false);
                    }
                    returnData.setBranchId(listEntity.getBranchId());
                    listOfResult.add(returnData);
                }
            }

        }
        logger.info("[ShiftBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<MedicalRecord> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public String getNextPengobatanId() throws GeneralBOException {
        String id = "";
        id = pengobatanDao.getNextPengobatanId();
        return id;
    }

    @Override
    public String getNextBuktiPembayaranId() throws GeneralBOException {
        String id = "";
        id = buktiPengobatanDao.getNextBuktiId();
        return id;
    }

    @Override
    public String getNextSuratJaminanId() throws GeneralBOException {
        String id = "";
        id = medicalRecordDao.getNextjaminanId();
        return id;
    }

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord, List<Pengobatan> pengobatans) throws GeneralBOException {
        boolean saved = false;
        if (medicalRecord != null){
            ItHrisMedicalRecordEntity add = new ItHrisMedicalRecordEntity();
            String id = medicalRecordDao.getNextMedicalId();
            add.setMedicalRecordId(medicalRecord.getMedicalRecordId()+id);
            add.setNip(medicalRecord.getNip());
            add.setTanggalBerobat(medicalRecord.getTanggalBerobat());
            add.setNamaBerobat(medicalRecord.getNamaBerobat());
            add.setTipeOrangBerobat(medicalRecord.getTipeOrangBerobat());
            add.setKeluargaId(medicalRecord.getKeluargaId());
            add.setTipePengobatan(medicalRecord.getTipePengobatan());
            add.setFlagSuratJaminan(medicalRecord.getFlagSuratJaminan());
            add.setNoSuratJaminan(medicalRecord.getNoSuratJaminan());
            add.setBranchId(medicalRecord.getBranchId());
            add.setCreatedDate(medicalRecord.getCreatedDate());
            add.setLastUpdate(medicalRecord.getLastUpdate());
            add.setCreateDateWho(medicalRecord.getCreatedWho());
            add.setLastUpdateWho(medicalRecord.getLastUpdateWho());
            add.setFlag("Y");
            add.setAction("C");
            add.setRsKelasId(medicalRecord.getRsKelasId());
            add.setRsId(medicalRecord.getRsId());
            try {
                medicalRecordDao.addAndSave(add);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if (saved){
                if (pengobatans != null){
                    for (Pengobatan listPengobatan : pengobatans){
                        ItHrisPengobatanEntity addPengobatan = new ItHrisPengobatanEntity();
                        addPengobatan.setPengobatanId(listPengobatan.getPengobatanId());
                        addPengobatan.setBiayaPengobatanId(listPengobatan.getBiayaPengobatanId());
                        addPengobatan.setMedicalRecordId(add.getMedicalRecordId());
                        addPengobatan.setJumlah(listPengobatan.getJumlah());
                        addPengobatan.setCreatedDate(medicalRecord.getCreatedDate());
                        addPengobatan.setLastUpdate(medicalRecord.getLastUpdate());
                        addPengobatan.setCreateDateWho(medicalRecord.getCreatedWho());
                        addPengobatan.setLastUpdateWho(medicalRecord.getLastUpdateWho());
                        addPengobatan.setFlag("Y");
                        addPengobatan.setAction("C");
                        try {
                            pengobatanDao.addAndSave(addPengobatan);
                            saved = true;
                        } catch (HibernateException e) {
                            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                    }
                }
            }
            if(medicalRecord.getMedicalRecordId() != null){
                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                String idNotif = notifikasiDao.getNextNotifikasiId();
//                Long notifId = Long.parseLong(idNotif);

                addNotif.setNotifId(idNotif);
                addNotif.setNote("Menunggu Untuk Approve Medical Record untuk id Medical record : "+medicalRecord.getMedicalRecordId()+id);
                addNotif.setTipeNotifName("Medical Record");
                addNotif.setRead("Y");
                addNotif.setFlag("Y");
                addNotif.setAction("C");
                addNotif.setTipeNotifId("TN22");
                addNotif.setNip(medicalRecord.getNip());
                addNotif.setFromPerson(medicalRecord.getNip());
                addNotif.setNoRequest(medicalRecord.getMedicalRecordId());
                addNotif.setCreatedDate(medicalRecord.getCreatedDate());
                addNotif.setCreatedWho(medicalRecord.getCreatedWho());
                addNotif.setLastUpdate(medicalRecord.getLastUpdate());
                addNotif.setLastUpdateWho(medicalRecord.getLastUpdateWho());

                try {
                    notifikasiDao.addAndSave(addNotif);
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
            }
        }
    }

    @Override
    public void saveBuktiPengobatan(List<BuktiPengobatan> buktiPengobatans) throws GeneralBOException {
        if (buktiPengobatans != null){
            for (BuktiPengobatan listData : buktiPengobatans){
                ItHrisBuktiPengobatanEntity add = new ItHrisBuktiPengobatanEntity();
                add.setBuktiId(listData.getBuktiId());
                add.setPengobatanId(listData.getPengobatanId());
                add.setFileName(listData.getFileName());
                add.setTipeUpload(listData.getTipeUpload());
                add.setKeterangan(listData.getKeterangan());
                add.setCreatedDate(listData.getCreatedDate());
                add.setCreateDateWho(listData.getCreatedWho());
                add.setLastUpdate(listData.getLastUpdate());
                add.setLastUpdateWho(listData.getLastUpdateWho());

                if (listData.isAdd()){
                    add.setFlag("Y");
                    add.setAction("C");
                    try {
                        buktiPengobatanDao.addAndSave(add);
                    } catch (HibernateException e) {
                        logger.error("[TrainingBoImpl.saveBuktiPengobatan] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                } else  {
                    add.setFlag(listData.getFlag());
                    add.setAction("U");
                    try {
                        buktiPengobatanDao.updateAndSave(add);
                    } catch (HibernateException e) {
                        logger.error("[TrainingBoImpl.saveBuktiPengobatan] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }
    }
    @Override
    public void koreksi(MedicalRecord bean) throws GeneralBOException {
        if (bean != null){
            ItHrisMedicalRecordEntity update = new ItHrisMedicalRecordEntity();
            List<ItHrisMedicalRecordEntity> medicalRecordEntityList = new ArrayList<>();
            medicalRecordEntityList = medicalRecordDao.getListMedicalRecord(bean.getMedicalRecordId());
            for (ItHrisMedicalRecordEntity medicalRecordEntity : medicalRecordEntityList){
                update = medicalRecordEntity;
            }

            update.setApproved(bean.getApproved());
            update.setFlagKoreksi(bean.getFlagKoreksi());
            update.setKeteranganKoreksi(bean.getKeteranganKoreksi());
            update.setLastUpdate(bean.getLastUpdate());
            update.setLastUpdateWho(bean.getLastUpdateWho());
            update.setApproved(bean.getApproved());
            try {
                medicalRecordDao.updateAndSave(update);
            } catch (HibernateException e) {
                logger.error("[MedicalRecordBo.koreksi] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
        }
    }

    @Override
    public void saveApprove(MedicalRecord bean) throws GeneralBOException {
        boolean saved = false;
        if (bean != null){
            ItHrisMedicalRecordEntity update = new ItHrisMedicalRecordEntity();
            update.setMedicalRecordId(bean.getMedicalRecordId());

            String getJumlah = "";
            if (bean.getMedicalRecordId() != null){
                getJumlah = getJumlahBiaya(bean.getMedicalRecordId());
            }

            update.setNip(bean.getNip());
            update.setNamaBerobat(bean.getNamaBerobat());
            update.setTanggalBerobat(bean.getTanggalBerobat());
            update.setTipeOrangBerobat(bean.getTipeOrangBerobat());
            update.setKeluargaId(bean.getKeluargaId());
            update.setTipePengobatan(bean.getTipePengobatan());
            update.setBranchId(bean.getBranchId());
            update.setApproved(bean.getApproved());
            update.setApprovedId(bean.getApprovedId());
            update.setApprovedName(bean.getApprovedName());
            update.setApproveDate(bean.getApproveDate());
            update.setNote(bean.getNote());
            update.setFlagSuratJaminan(bean.getFlagSuratJaminan());
            update.setNoSuratJaminan(bean.getNoSuratJaminan());
            update.setFlag("Y");
            update.setAction("U");
            update.setFlagKoreksi(null);
            update.setCreatedDate(bean.getCreatedDate());
            update.setCreateDateWho(bean.getCreatedWho());
            update.setLastUpdate(bean.getLastUpdate());
            update.setLastUpdateWho(bean.getLastUpdateWho());
            update.setRsId(bean.getRsId());
            update.setRsKelasId(bean.getRsKelasId());
            update.setJumlahBiaya(getJumlah);
            try {
                medicalRecordDao.updateAndSave(update);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveApprove] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (saved){
                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                String idNotif = notifikasiDao.getNextNotifikasiId();
//                Long notifId = Long.parseLong(idNotif);

                addNotif.setNotifId(idNotif);
                if ("N".equalsIgnoreCase(bean.getApproved())){
                    addNotif.setNote("Data Medical di Tolak untuk id Medical record : "+bean.getMedicalRecordId()+". dengan Catatan "+bean.getNote());
                } else {
                    addNotif.setNote("Data Medical Record Telah di Approve untuk id Medical record : "+bean.getMedicalRecordId());
                }
                addNotif.setTipeNotifName("Medical Record");
                addNotif.setRead("Y");
                addNotif.setFlag("Y");
                addNotif.setAction("C");
                addNotif.setTipeNotifId("umum");
                addNotif.setNip(bean.getNip());
                addNotif.setFromPerson("Admin SDM");
                addNotif.setNoRequest(bean.getMedicalRecordId());
                addNotif.setCreatedDate(bean.getCreatedDate());
                addNotif.setCreatedWho(bean.getCreatedWho());
                addNotif.setLastUpdate(bean.getLastUpdate());
                addNotif.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    notifikasiDao.addAndSave(addNotif);
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
            }
        }
    }

    @Override
    public List<Pengobatan> searchPengobatan(Pengobatan bean) throws GeneralBOException {
        List<Pengobatan> listOfResult = new ArrayList<Pengobatan>();
        if (bean != null){
            Map hsCriteria = new HashMap();

            if (bean.getMedicalRecordId() != null && !"".equalsIgnoreCase(bean.getMedicalRecordId())) {
                hsCriteria.put("medical_record_id", bean.getMedicalRecordId());
            }
            if (bean.getPengobatanId() != null && !"".equalsIgnoreCase(bean.getPengobatanId())) {
                hsCriteria.put("pengobatan_id", bean.getPengobatanId());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ItHrisPengobatanEntity> itHrisPengobatanEntities = null;
            try {
                itHrisPengobatanEntities = pengobatanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itHrisPengobatanEntities != null){
                Pengobatan returnData;
                for (ItHrisPengobatanEntity entityData : itHrisPengobatanEntities){
                    returnData = new Pengobatan();
                    returnData.setMedicalRecordId(entityData.getMedicalRecordId());
                    returnData.setPengobatanId(entityData.getPengobatanId());
                    returnData.setBiayaPengobatanId(entityData.getBiayaPengobatanId());
                    returnData.setJumlah(entityData.getJumlah());

                    returnData.setCreatedDate(entityData.getCreatedDate());
                    returnData.setCreatedWho(entityData.getCreateDateWho());
                    returnData.setLastUpdate(entityData.getLastUpdate());
                    returnData.setLastUpdateWho(entityData.getLastUpdateWho());
                    returnData.setFlag(entityData.getFlag());
                    returnData.setAction(entityData.getAction());

                    hsCriteria = new HashMap();
                    hsCriteria.put("biaya_pengobatan_id", entityData.getBiayaPengobatanId());
                    hsCriteria.put("flag","Y");

                    List<ImHrisBiayaPengobatan> biayaPengobatans = null;
                    biayaPengobatans = biayaPengobatanDao.getByCriteria(hsCriteria);

                    if (biayaPengobatans != null){
                        for (ImHrisBiayaPengobatan listBiaya : biayaPengobatans){
                            returnData.setNamaBiayaPengobatan(listBiaya.getBiayaPengobatanName());
                        }
                    }

                    listOfResult.add(returnData);
                }
            }
        }
        return listOfResult;
    }

    @Override
    public List<BuktiPengobatan> searchBuktiPengobatan(BuktiPengobatan bean) throws GeneralBOException {
        List<BuktiPengobatan> listOfResult = new ArrayList<BuktiPengobatan>();
        if (bean != null){
            Map hsCriteria = new HashMap();

            if (bean.getBuktiId() != null && !"".equalsIgnoreCase(bean.getBuktiId())) {
                hsCriteria.put("bukti_id", bean.getBuktiId());
            }
            if (bean.getPengobatanId() != null && !"".equalsIgnoreCase(bean.getPengobatanId())) {
                hsCriteria.put("pengobatan_id", bean.getPengobatanId());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ItHrisBuktiPengobatanEntity> itHrisBuktiPengobatanEntities = null;
            try {
                itHrisBuktiPengobatanEntities = buktiPengobatanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itHrisBuktiPengobatanEntities != null){
                BuktiPengobatan returnData;
                for (ItHrisBuktiPengobatanEntity entityData : itHrisBuktiPengobatanEntities){
                    returnData = new BuktiPengobatan();
                    returnData.setBuktiId(entityData.getBuktiId());
                    returnData.setPengobatanId(entityData.getPengobatanId());
                    returnData.setFileName(entityData.getFileName());
                    returnData.setTipeUpload(entityData.getTipeUpload());
                    returnData.setKeterangan(entityData.getKeterangan());
                    returnData.setCreatedDate(entityData.getCreatedDate());
                    returnData.setCreatedWho(entityData.getCreateDateWho());
                    returnData.setLastUpdate(entityData.getLastUpdate());
                    returnData.setLastUpdateWho(entityData.getLastUpdateWho());
                    returnData.setFlag(entityData.getFlag());
                    returnData.setAction(entityData.getAction());
                    listOfResult.add(returnData);
                }
            }
        }
        return listOfResult;
    }

    @Override
    public void saveUpdateMedicalRecord(MedicalRecord medicalRecord, List<Pengobatan> pengobatans) throws GeneralBOException {
        boolean saved = false;
        if (medicalRecord != null){
            ItHrisMedicalRecordEntity add = new ItHrisMedicalRecordEntity();
            add.setMedicalRecordId(medicalRecord.getMedicalRecordId());
            add.setNip(medicalRecord.getNip());
            add.setTanggalBerobat(medicalRecord.getTanggalBerobat());
            add.setNamaBerobat(medicalRecord.getNamaBerobat());
            add.setTipeOrangBerobat(medicalRecord.getTipeOrangBerobat());
            add.setKeluargaId(medicalRecord.getKeluargaId());
            add.setTipePengobatan(medicalRecord.getTipePengobatan());

            if (medicalRecord.getTipePengobatan().equalsIgnoreCase("RI")){
                add.setNoSuratJaminan(medicalRecord.getNoSuratJaminan());
                add.setRsId(medicalRecord.getRsId());
                add.setRsKelasId(medicalRecord.getRsKelasId());
            }
            if (medicalRecord.getTipePengobatan().equalsIgnoreCase("RJ")){
                add.setNoSuratJaminan(medicalRecord.getNoSuratJaminan());
                add.setRsId(medicalRecord.getRsId());
            }
            add.setFlagSuratJaminan(medicalRecord.getFlagSuratJaminan());
            add.setBranchId(medicalRecord.getBranchId());
            add.setCreatedDate(medicalRecord.getCreatedDate());
            add.setLastUpdate(medicalRecord.getLastUpdate());
            add.setCreateDateWho(medicalRecord.getCreatedWho());
            add.setLastUpdateWho(medicalRecord.getLastUpdateWho());
            add.setFlag(medicalRecord.getFlag());
            add.setAction(medicalRecord.getAction());

            try {
                medicalRecordDao.updateAndSave(add);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if (saved){
                if (pengobatans != null){
                    for (Pengobatan listPengobatan : pengobatans){
                        ItHrisPengobatanEntity addPengobatan = new ItHrisPengobatanEntity();
                        addPengobatan.setPengobatanId(listPengobatan.getPengobatanId());
                        addPengobatan.setBiayaPengobatanId(listPengobatan.getBiayaPengobatanId());
                        addPengobatan.setMedicalRecordId(add.getMedicalRecordId());
                        addPengobatan.setJumlah(listPengobatan.getJumlah());

                        if (listPengobatan.isAdd()){
                            addPengobatan.setCreatedDate(medicalRecord.getLastUpdate());
                            addPengobatan.setCreateDateWho(medicalRecord.getLastUpdateWho());
                            addPengobatan.setLastUpdate(medicalRecord.getLastUpdate());
                            addPengobatan.setLastUpdateWho(medicalRecord.getLastUpdateWho());
                            addPengobatan.setFlag("Y");
                            addPengobatan.setAction("C");
                            try {
                                pengobatanDao.addAndSave(addPengobatan);
                                saved = true;
                            } catch (HibernateException e) {
                                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        } else {
                            addPengobatan.setCreatedDate(listPengobatan.getCreatedDate());
                            addPengobatan.setCreateDateWho(listPengobatan.getCreatedWho());
                            addPengobatan.setLastUpdate(medicalRecord.getLastUpdate());
                            addPengobatan.setLastUpdateWho(medicalRecord.getLastUpdateWho());
                            addPengobatan.setFlag(listPengobatan.getFlag());
                            addPengobatan.setAction("U");
                            try {
                                pengobatanDao.updateAndSave(addPengobatan);
                                saved = true;
                            } catch (HibernateException e) {
                                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                }
            }
            if (medicalRecord.isEdit()){
                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                String idNotif = notifikasiDao.getNextNotifikasiId();
//                Long notifId = Long.parseLong(idNotif);

                addNotif.setNotifId(idNotif);
                addNotif.setNote("Data Telah di Update dan Menunggu Untuk Approve Medical Record untuk id Medical record : "+medicalRecord.getMedicalRecordId());
                addNotif.setTipeNotifName("Medical Record");
                addNotif.setRead("Y");
                addNotif.setFlag("Y");
                addNotif.setAction("C");
                addNotif.setTipeNotifId("TN22");
                addNotif.setNip(medicalRecord.getNip());
                addNotif.setFromPerson(medicalRecord.getNip());
                addNotif.setNoRequest(medicalRecord.getMedicalRecordId());
                addNotif.setCreatedDate(medicalRecord.getCreatedDate());
                addNotif.setCreatedWho(medicalRecord.getCreatedWho());
                addNotif.setLastUpdate(medicalRecord.getLastUpdate());
                addNotif.setLastUpdateWho(medicalRecord.getLastUpdateWho());

                try {
                    notifikasiDao.addAndSave(addNotif);
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
            }
        }
    }

    @Override
    public MedicalRecord getPrintMedicalRecord(String id) throws GeneralBOException {
        List<ItPersonilPositionEntity> personilPositionEntityList=new ArrayList<>();
        MedicalRecord data = null;
        if (id != null){
            String medicalRecordId = id;

            try {
                data = medicalRecordDao.getSearchPrintSuratJaminan(medicalRecordId);
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            // set Nama Passien = Nama Pegawai if nama pasien is Null
            if (data != null){
                if (data.getStatusKeluarga() == null){
                    data.setNamaPasien(data.getNama());
                } else {
                    //set namaBerobat with string "status nama"
                    data.setNamaBerobat(data.getStatusKeluarga()+" "+data.getNama());
                }
                try {
                    Map hsCriteria = new HashMap();
                    hsCriteria.put("position_id","24");
                    hsCriteria.put("flag","Y");
                    personilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
                for (ItPersonilPositionEntity personilPositionEntity : personilPositionEntityList){
                    List<Biodata> biodataList = new ArrayList<>();
                    try {
                        biodataList = biodataDao.getBiodataByNip(personilPositionEntity.getNip());
                    } catch (HibernateException e) {
                        logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                    for (Biodata biodata:biodataList){
                        data.setKabagSdmName(biodata.getNamaPegawai());
                    }
                }
            }
        }
        return data;
    }

    @Override
    public String getJumlahBiaya(String id) throws GeneralBOException {
        List<ItHrisPengobatanEntity> pengobatanEntities = null;
        Map hsCriteria = new HashMap();
        hsCriteria.put("medical_record_id",id);
        hsCriteria.put("flag","Y");

        Integer jumlah = 0;

        pengobatanEntities = pengobatanDao.getByCriteria(hsCriteria);

        if (pengobatanEntities != null){
            for (ItHrisPengobatanEntity listData : pengobatanEntities){
                Integer intJumlah = listData.getJumlah().intValue();
                jumlah = jumlah + intJumlah;
            }
        }

        String stJumlah = String.valueOf(jumlah);

        return stJumlah;
    }

    @Override
    public List<MedicalRecord> getMedicalRecordUser(MedicalRecord searchBean){
        List<MedicalRecord> listOfResultMedicalRecord = new ArrayList<>();
        List<ItHrisMedicalRecordEntity> listOfMedicalRecord= new ArrayList();
        Map hsCriteria = new HashMap();
        if (searchBean!=null){
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Date tanggalDari = CommonUtil.convertToDate(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSampai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSampai()))) {
                Date tanggalSelesai = CommonUtil.convertToDate(searchBean.getStTanggalSampai());
                hsCriteria.put("tanggal_sampai", tanggalSelesai);
            }
            hsCriteria.put("flag", "Y");
            hsCriteria.put("approval_flag", "Y");
        }

        try {
            listOfMedicalRecord = medicalRecordDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[MedicalRecordBoImpl.getMedicalRecordUser] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
        }
        for (ItHrisMedicalRecordEntity pegawai : listOfMedicalRecord){
            MedicalRecord result = new MedicalRecord();
            result.setNip(pegawai.getNip());
            result.setNama(pegawai.getNamaBerobat());
            result.setTanggalBerobat(pegawai.getTanggalBerobat());
            if (pegawai.getTanggalBerobat()!=null){
                result.setStTanggalBerobat(CommonUtil.convertDateToString(pegawai.getTanggalBerobat()));
            }
            result.setFlag(pegawai.getFlag());
            result.setNote(pegawai.getNote());
            result.setJumlahBiaya(pegawai.getJumlahBiaya());
            switch (pegawai.getTipePengobatan()){
                case "RJ":
                    result.setTipePengobatanName("Rawat Jalan");
                    break;
                case "RI":
                    result.setTipePengobatanName("Rawat Inap");
                    break;
                default:
                    result.setTipePengobatanName("-");
            }
            switch (pegawai.getTipeOrangBerobat()){
                case "K":
                    result.setTipeBerobatName("Keluarga");
                    break;
                case "S":
                    result.setTipeBerobatName("Sendiri");
                    break;
                default:
                    result.setTipeBerobatName("-");
            }

//            hsCriteria = new HashMap();
//            hsCriteria.put("medical_record_id",pegawai.getMedicalRecordId());
//            hsCriteria.put("flag","Y");
//            List<ItHrisPengobatanEntity> hrisPengobatanEntityList = pengobatanDao.getByCriteria(hsCriteria);
//            BigDecimal total=BigDecimal.ZERO;
//            for (ItHrisPengobatanEntity pengobatan : hrisPengobatanEntityList){
//                total=total.add(pengobatan.getJumlah());
//            }
//            result.setJumlahBiaya(total.toString());
            listOfResultMedicalRecord.add(result);
        }
        return listOfResultMedicalRecord;
    }
}
