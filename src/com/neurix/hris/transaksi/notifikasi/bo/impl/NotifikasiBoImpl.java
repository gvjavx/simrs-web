package com.neurix.hris.transaksi.notifikasi.bo.impl;

import com.neurix.akuntansi.transaksi.pengajuanBiaya.dao.PengajuanBiayaDao;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.dao.PengajuanBiayaDetailDao;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ImPengajuanBiayaEntity;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ItPengajuanBiayaDetailEntity;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.ExpoPushNotif;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.cuti.dao.CutiDao;
import com.neurix.hris.master.cuti.model.ImCutiEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.master.tipeNotif.dao.TipeNotifDao;
import com.neurix.hris.master.tipeNotif.model.ImTipeNotifEntity;
import com.neurix.hris.mobileapi.model.*;
import com.neurix.hris.transaksi.absensi.dao.AbsensiPegawaiDao;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawaiEntity;
import com.neurix.hris.transaksi.cutiPegawai.dao.CutiPegawaiDao;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
import com.neurix.hris.transaksi.ijinKeluar.dao.IjinKeluarDao;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarEntity;
import com.neurix.hris.transaksi.indisipliner.dao.IndisiplinerDao;
import com.neurix.hris.transaksi.indisipliner.model.Indisipliner;
import com.neurix.hris.transaksi.indisipliner.model.ItIndisiplinerEntity;
import com.neurix.hris.transaksi.lembur.dao.LemburDao;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.lembur.model.LemburEntity;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiFcmDao;
import com.neurix.hris.transaksi.notifikasi.model.*;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.rekruitmenPabrik.dao.RekruitmenPabrikDetailDao;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.ItRekruitmenPabrikDetailEntity;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.RekruitmenPabrikDetail;
import com.neurix.hris.transaksi.training.dao.TrainingDao;
import com.neurix.hris.transaksi.training.dao.TrainingPersonDao;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingEntity;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingPersonEntity;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class NotifikasiBoImpl implements NotifikasiBo {

    protected static transient Logger logger = Logger.getLogger(NotifikasiBoImpl.class);
    private NotifikasiDao notifikasiDao;
    private TrainingPersonDao trainingPersonDao;
    private TrainingDao trainingDao;
    private IjinKeluarDao ijinKeluarDao;
    private CutiPegawaiDao cutiPegawaiDao;
    private LemburDao lemburDao;
    private RekruitmenPabrikDetailDao rekruitmenPabrikDetailDao;
    private IndisiplinerDao indisiplinerDao;
    private PositionDao positionDao;
    private PersonilPositionDao personilPositionDao;
    private DepartmentDao departmentDao;
    private StrukturJabatanDao strukturJabatanDao;
    private AbsensiPegawaiDao absensiPegawaiDao;
    private BiodataDao biodataDao;
    private BranchDao branchDao;
    private NotifikasiFcmDao notifikasiFcmDao;
    private CutiDao cutiDao;
    private TipeNotifDao tipeNotifDao;
    private PengajuanBiayaDao pengajuanBiayaDao;
    private PengajuanBiayaDetailDao pengajuanBiayaDetailDao;

    public PengajuanBiayaDetailDao getPengajuanBiayaDetailDao() {
        return pengajuanBiayaDetailDao;
    }

    public void setPengajuanBiayaDetailDao(PengajuanBiayaDetailDao pengajuanBiayaDetailDao) {
        this.pengajuanBiayaDetailDao = pengajuanBiayaDetailDao;
    }

    public PengajuanBiayaDao getPengajuanBiayaDao() {
        return pengajuanBiayaDao;
    }

    public void setPengajuanBiayaDao(PengajuanBiayaDao pengajuanBiayaDao) {
        this.pengajuanBiayaDao = pengajuanBiayaDao;
    }

    public TipeNotifDao getTipeNotifDao() {
        return tipeNotifDao;
    }

    public void setTipeNotifDao(TipeNotifDao tipeNotifDao) {
        this.tipeNotifDao = tipeNotifDao;
    }

//    private String CLICK_IJIN = "TASK_IJIN";
    private String CLICK_IJIN = null;
    private String action;

    public CutiDao getCutiDao() {
        return cutiDao;
    }

    public void setCutiDao(CutiDao cutiDao) {
        this.cutiDao = cutiDao;
    }

    public NotifikasiFcmDao getNotifikasiFcmDao() {
        return notifikasiFcmDao;
    }

    public void setNotifikasiFcmDao(NotifikasiFcmDao notifikasiFcmDao) {
        this.notifikasiFcmDao = notifikasiFcmDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    private List<StrukturJabatan> strukturJabatanList = new ArrayList();
    String hasilUraian = "";

    public AbsensiPegawaiDao getAbsensiPegawaiDao() {
        return absensiPegawaiDao;
    }

    public void setAbsensiPegawaiDao(AbsensiPegawaiDao absensiPegawaiDao) {
        this.absensiPegawaiDao = absensiPegawaiDao;
    }

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
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

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public IndisiplinerDao getIndisiplinerDao() {
        return indisiplinerDao;
    }

    public void setIndisiplinerDao(IndisiplinerDao indisiplinerDao) {
        this.indisiplinerDao = indisiplinerDao;
    }

    public RekruitmenPabrikDetailDao getRekruitmenPabrikDetailDao() {
        return rekruitmenPabrikDetailDao;
    }

    public void setRekruitmenPabrikDetailDao(RekruitmenPabrikDetailDao rekruitmenPabrikDetailDao) {
        this.rekruitmenPabrikDetailDao = rekruitmenPabrikDetailDao;
    }

    public LemburDao getLemburDao() {
        return lemburDao;
    }

    public void setLemburDao(LemburDao lemburDao) {
        this.lemburDao = lemburDao;
    }

    public CutiPegawaiDao getCutiPegawaiDao() {
        return cutiPegawaiDao;
    }

    public void setCutiPegawaiDao(CutiPegawaiDao cutiPegawaiDao) {
        this.cutiPegawaiDao = cutiPegawaiDao;
    }

    public IjinKeluarDao getIjinKeluarDao() {
        return ijinKeluarDao;
    }

    public void setIjinKeluarDao(IjinKeluarDao ijinKeluarDao) {
        this.ijinKeluarDao = ijinKeluarDao;
    }

    public TrainingDao getTrainingDao() {
        return trainingDao;
    }

    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public TrainingPersonDao getTrainingPersonDao() {
        return trainingPersonDao;
    }

    public void setTrainingPersonDao(TrainingPersonDao trainingPersonDao) {
        this.trainingPersonDao = trainingPersonDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        NotifikasiBoImpl.logger = logger;
    }

    public NotifikasiDao getNotifikasiDao() {
        return notifikasiDao;
    }

    public void setNotifikasiDao(NotifikasiDao notifikasiDao) {
        this.notifikasiDao = notifikasiDao;
    }

    @Override
    public void saveDelete(Notifikasi bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String notifid = bean.getNotifId();

            ImNotifikasiEntity imNotifikasiEntity = null;

            try {
                // Get data from database by ID
                imNotifikasiEntity = notifikasiDao.getById("notifId", notifid);
            } catch (HibernateException e) {
                logger.error("[NotifikasiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imNotifikasiEntity != null) {

                // Modify from bean to entity serializable
                imNotifikasiEntity.setNotifId(bean.getNotifId());
                imNotifikasiEntity.setNip(bean.getNip());
                imNotifikasiEntity.setTipeNotifId(bean.getTipeNotifId());

                ImTipeNotifEntity tipeNotifEntity = tipeNotifDao.getById("tipeNotifId",bean.getTipeNotifId());
                imNotifikasiEntity.setTipeNotifName(tipeNotifEntity.getTipeNotifName());

                imNotifikasiEntity.setNote(bean.getNote());
                imNotifikasiEntity.setRead(bean.getRead());
                imNotifikasiEntity.setFromPerson(bean.getFromPerson());
                imNotifikasiEntity.setNoRequest(bean.getNoRequest());

                imNotifikasiEntity.setFlag(bean.getFlag());
                imNotifikasiEntity.setAction(bean.getAction());
                imNotifikasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imNotifikasiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    notifikasiDao.updateAndSave(imNotifikasiEntity);
                } catch (HibernateException e) {
                    logger.error("[NotifikasiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Notifikasi, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[NotifikasiBoImpl.saveDelete] Error, not found data Notifikasi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Notifikasi with request id, please check again your data ...");

            }
        }
        logger.info("[NotifikasiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEditMobile(String nip, String typeNotifId, String notifId) throws GeneralBOException {
        logger.info("NotifikasiBoImpl.saveEditMobile start process >>>");

        if (notifId != null){
            ImNotifikasiEntity imNotifikasiEntity = null;
            try{
                imNotifikasiEntity = notifikasiDao.getById("notifId", notifId);
            }catch (HibernateException e){
                logger.error("[NotifikasiBoImpl.saveEdit] Error, "+e.getMessage());
                throw new GeneralBOException("Found problem when searching data Notifikasi by Kode Notifikasi, please inform to your admin...," + e.getMessage());
            }

            if (imNotifikasiEntity != null){
                imNotifikasiEntity.setNotifId(notifId);
                imNotifikasiEntity.setRead("N");
                imNotifikasiEntity.setAction("U");

                try{
                    notifikasiDao.updateAndSave(imNotifikasiEntity);
                }catch (HibernateException e){
                    logger.error("[NotifikasiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Notifikasi, please info to your admin..." + e.getMessage());
                }
            }else {
                logger.error("[NotifikasiBoImpl.saveEdit] Error, not found data Notifikasi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Notifikasi with request id, please check again your data ...");
            }
        }

        logger.info("[NotifikasiBoImpl.saveEditMobile] end process <<<");
    }

    @Override
    public void saveEdit(Notifikasi bean) throws GeneralBOException {
        logger.info("[NotifikasiBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String notifId = bean.getNotifId();

            ImNotifikasiEntity imNotifikasiEntity = null;
            ImNotifikasiHistoryEntity imNotifikasiHistoryEntity = new ImNotifikasiHistoryEntity();
            try {
                // Get data from database by ID
                imNotifikasiEntity = notifikasiDao.getById("notifId", notifId);
            } catch (HibernateException e) {
                logger.error("[NotifikasiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Notifikasi by Kode Notifikasi, please inform to your admin...," + e.getMessage());
            }

            if (imNotifikasiEntity != null) {

                imNotifikasiEntity.setNotifId(bean.getNotifId());

                ImTipeNotifEntity tipeNotifEntity = tipeNotifDao.getById("tipeNotifId",bean.getTipeNotifId());
                imNotifikasiEntity.setTipeNotifName(tipeNotifEntity.getTipeNotifName());

                imNotifikasiEntity.setTipeNotifId(bean.getTipeNotifId());
                imNotifikasiEntity.setNote(bean.getNote());
                imNotifikasiEntity.setRead(bean.getRead());
                imNotifikasiEntity.setNip(bean.getNip());
                imNotifikasiEntity.setFromPerson(bean.getFromPerson());
                imNotifikasiEntity.setNoRequest(bean.getNoRequest());

                imNotifikasiEntity.setFlag(bean.getFlag());
                imNotifikasiEntity.setAction(bean.getAction());
                imNotifikasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imNotifikasiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    notifikasiDao.updateAndSave(imNotifikasiEntity);

                } catch (HibernateException e) {
                    logger.error("[NotifikasiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Notifikasi, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[NotifikasiBoImpl.saveEdit] Error, not found data Notifikasi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Notifikasi with request id, please check again your data ...");

            }
        }
        logger.info("[NotifikasiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Notifikasi saveAdd(Notifikasi bean) throws GeneralBOException {
        logger.info("[NotifikasiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            // creating object entity serializables
            ImNotifikasiEntity imNotifikasiEntity = new ImNotifikasiEntity();
            String idNotif = notifikasiDao.getNextNotifikasiId();
            imNotifikasiEntity.setNotifId(idNotif);
            imNotifikasiEntity.setTipeNotifId(bean.getTipeNotifId());

            ImTipeNotifEntity tipeNotifEntity = tipeNotifDao.getById("tipeNotifId",bean.getTipeNotifId());
            imNotifikasiEntity.setTipeNotifName(tipeNotifEntity.getTipeNotifName());


            imNotifikasiEntity.setNote(bean.getNote());
            imNotifikasiEntity.setRead(bean.getRead());
            imNotifikasiEntity.setNip(bean.getNip());
            imNotifikasiEntity.setFromPerson(bean.getFromPerson());
            imNotifikasiEntity.setNoRequest(bean.getNoRequest());

            imNotifikasiEntity.setFlag(bean.getFlag());
            imNotifikasiEntity.setAction(bean.getAction());
            imNotifikasiEntity.setCreatedWho(bean.getCreatedWho());
            imNotifikasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imNotifikasiEntity.setCreatedDate(bean.getCreatedDate());
            imNotifikasiEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                notifikasiDao.addAndSave(imNotifikasiEntity);
            } catch (HibernateException e) {
                logger.error("[NotifikasiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Notifikasi, please info to your admin..." + e.getMessage());
            }

            List<ItNotifikasiFcmEntity> notifikasiFcm = null;

            try {
                notifikasiFcm = notifikasiFcmDao.getAll();
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

//            for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                if(entity.getUserId().equals(bean.getNip())){
//                    ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), bean.getTipeNotifName(), bean.getNote(), bean.getOs());
//                    break;
//                }
//            }

            for (ItNotifikasiFcmEntity entity : notifikasiFcm){
                if(entity.getUserId().equals(bean.getNip())){
                    FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), bean.getTipeNotifName(), bean.getNote(), CLICK_IJIN, bean.getOs(), null);
                    break;
                }
            }

//            for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                if(entity.getUserId().equals(bean.getNip())){
//                    FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), bean.getTipeNotifName(), bean.getNote(), CLICK_IJIN);
//                    break;
//                }
//            }
        }

        logger.info("[NotifikasiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Notifikasi> getByCriteria(Notifikasi searchBean) throws GeneralBOException {
        logger.info("[NotifikasiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Notifikasi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getNotifId() != null && !"".equalsIgnoreCase(searchBean.getNotifId())) {
                hsCriteria.put("notifId", searchBean.getNotifId());
            }

            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }

            if (searchBean.getTipeNotifId() != null && !"".equalsIgnoreCase(searchBean.getTipeNotifId())) {
                hsCriteria.put("tipeNotifId", searchBean.getTipeNotifId());
            }

//            if (searchBean.getTipeNotifName() != null && !"".equalsIgnoreCase(searchBean.getTipeNotifName())) {
//                hsCriteria.put("tipeNotifName", searchBean.getNotifId());
//            }

//            if (searchBean.getNote() != null && !"".equalsIgnoreCase(searchBean.getNote())) {
//                hsCriteria.put("note", searchBean.getNip());
//            }

            if (searchBean.getRead() != null && !"".equalsIgnoreCase(searchBean.getRead())) {
                hsCriteria.put("read", searchBean.getRead());
            }

            if (searchBean.getFromPerson() != null && !"".equalsIgnoreCase(searchBean.getFromPerson())) {
                hsCriteria.put("fromPerson", searchBean.getFromPerson());
            }

            if (searchBean.getNoRequest() != null && !"".equalsIgnoreCase(searchBean.getNoRequest())) {
                hsCriteria.put("noRequest", searchBean.getNoRequest());
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


            List<ImNotifikasiEntity> imNotifikasiEntityPemberitahuan = null;
            List<ImNotifikasiEntity> imNotifikasiEntityApproval = null;
            List<ImNotifikasiEntity> imNotifikasiEntity = null;
            try {
                //AKIRA
                //jika di enable maka semua notif tampil
                imNotifikasiEntity = notifikasiDao.getByCriteria(hsCriteria);
//                imNotifikasiEntityPemberitahuan = notifikasiDao.getByCriteriaPemberitahuan(hsCriteria);
//                imNotifikasiEntityApproval = notifikasiDao.getByCriteriaApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[NotifikasiBoImpl.getSearchPersonalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imNotifikasiEntity != null){
//                Integer notifJumlah = imNotifikasiEntityApproval.size();
//                String jml = String.valueOf(notifJumlah);
                Notifikasi returnNotifikasi;
                // Looping from dao to object and save in collection
                for(ImNotifikasiEntity notifikasiEntity : imNotifikasiEntity){
                    returnNotifikasi = new Notifikasi();
                    returnNotifikasi.setNotifId(notifikasiEntity.getNotifId());
                    returnNotifikasi.setNip(notifikasiEntity.getNip());
                    returnNotifikasi.setRead(notifikasiEntity.getRead());
                    returnNotifikasi.setNote(notifikasiEntity.getNote());
                    returnNotifikasi.setTipeNotifId(notifikasiEntity.getTipeNotifId());
                    returnNotifikasi.setTipeNotifName(notifikasiEntity.getTipeNotifName());
                    returnNotifikasi.setNoRequest(notifikasiEntity.getNoRequest());
                    returnNotifikasi.setFromPerson(notifikasiEntity.getFromPerson());

                    returnNotifikasi.setCreatedWho(notifikasiEntity.getCreatedWho());
                    returnNotifikasi.setCreatedDate(notifikasiEntity.getCreatedDate());
                    returnNotifikasi.setLastUpdate(notifikasiEntity.getLastUpdate());
                    returnNotifikasi.setAction(notifikasiEntity.getAction());
                    returnNotifikasi.setFlag(notifikasiEntity.getFlag());
//                    returnNotifikasi.setJmlApproval(jml);
                    listOfResult.add(returnNotifikasi);
                }
            }

            /*if(imNotifikasiEntityApproval != null){
                Integer notifJumlah = imNotifikasiEntityApproval.size();
                String jml = String.valueOf(notifJumlah);
                Notifikasi returnNotifikasi;
                // Looping from dao to object and save in collection
                for(ImNotifikasiEntity notifikasiEntity : imNotifikasiEntityApproval){
                    returnNotifikasi = new Notifikasi();
                    returnNotifikasi.setNotifId(notifikasiEntity.getNotifId());
                    returnNotifikasi.setNip(notifikasiEntity.getNip());
                    returnNotifikasi.setRead(notifikasiEntity.getRead());
                    returnNotifikasi.setNote(notifikasiEntity.getNote());
                    returnNotifikasi.setTipeNotifId(notifikasiEntity.getTipeNotifId());
                    returnNotifikasi.setTipeNotifName(notifikasiEntity.getTipeNotifName());
                    returnNotifikasi.setNoRequest(notifikasiEntity.getNoRequest());
                    returnNotifikasi.setFromPerson(notifikasiEntity.getFromPerson());

                    returnNotifikasi.setCreatedWho(notifikasiEntity.getCreatedWho());
                    returnNotifikasi.setCreatedDate(notifikasiEntity.getCreatedDate());
                    returnNotifikasi.setLastUpdate(notifikasiEntity.getLastUpdate());
                    returnNotifikasi.setAction(notifikasiEntity.getAction());
                    returnNotifikasi.setFlag(notifikasiEntity.getFlag());
                    returnNotifikasi.setJmlApproval(jml);
                    listOfResult.add(returnNotifikasi);
                }
            }
            if(imNotifikasiEntityPemberitahuan != null){
                Integer notifJumlah = imNotifikasiEntityPemberitahuan.size();
                String jml = String.valueOf(notifJumlah);
                Notifikasi returnNotifikasi;
                // Looping from dao to object and save in collection
                for(ImNotifikasiEntity notifikasiEntity : imNotifikasiEntityPemberitahuan){
                    returnNotifikasi = new Notifikasi();
                    returnNotifikasi.setNotifId(notifikasiEntity.getNotifId());
                    returnNotifikasi.setNip(notifikasiEntity.getNip());
                    returnNotifikasi.setRead(notifikasiEntity.getRead());
                    returnNotifikasi.setNote(notifikasiEntity.getNote());
                    returnNotifikasi.setTipeNotifId(notifikasiEntity.getTipeNotifId());
                    returnNotifikasi.setTipeNotifName(notifikasiEntity.getTipeNotifName());
                    returnNotifikasi.setNoRequest(notifikasiEntity.getNoRequest());
                    returnNotifikasi.setFromPerson(notifikasiEntity.getFromPerson());

                    returnNotifikasi.setCreatedWho(notifikasiEntity.getCreatedWho());
                    returnNotifikasi.setCreatedDate(notifikasiEntity.getCreatedDate());
                    returnNotifikasi.setLastUpdate(notifikasiEntity.getLastUpdate());
                    returnNotifikasi.setAction(notifikasiEntity.getAction());
                    returnNotifikasi.setFlag(notifikasiEntity.getFlag());
                    returnNotifikasi.setJmlPemberitahuan(jml);
                    listOfResult.add(returnNotifikasi);
                }
            }*/
        }
        logger.info("[NotifikasiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<Notifikasi> getByCriteriaForNotif(Notifikasi searchBean) throws GeneralBOException {
        logger.info("[NotifikasiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Notifikasi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getNotifId() != null && !"".equalsIgnoreCase(searchBean.getNotifId())) {
                hsCriteria.put("notifId", searchBean.getNotifId());
            }

            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }

            if (searchBean.getTipeNotifId() != null && !"".equalsIgnoreCase(searchBean.getTipeNotifId())) {
                hsCriteria.put("tipeNotifId", searchBean.getRead());
            }

            if (searchBean.getTipeNotifName() != null && !"".equalsIgnoreCase(searchBean.getTipeNotifName())) {
                hsCriteria.put("tipeNotifName", searchBean.getNotifId());
            }

            if (searchBean.getNote() != null && !"".equalsIgnoreCase(searchBean.getNote())) {
                hsCriteria.put("note", searchBean.getNip());
            }

            if (searchBean.getRead() != null && !"".equalsIgnoreCase(searchBean.getRead())) {
                hsCriteria.put("read", searchBean.getRead());
            }

            if (searchBean.getFromPerson() != null && !"".equalsIgnoreCase(searchBean.getFromPerson())) {
                hsCriteria.put("fromPerson", searchBean.getNotifId());
            }

            if (searchBean.getNoRequest() != null && !"".equalsIgnoreCase(searchBean.getNoRequest())) {
                hsCriteria.put("noRequest", searchBean.getNip());
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


            List<ImNotifikasiEntity> imNotifikasiEntityPemberitahuan = null;
            List<ImNotifikasiEntity> imNotifikasiEntityApproval = null;
            List<ImNotifikasiEntity> imNotifikasiEntity = null;
            try {
                //AKIRA
                //jika di enable maka semua notif tampil
//                imNotifikasiEntity = notifikasiDao.getByCriteria(hsCriteria);
                imNotifikasiEntityPemberitahuan = notifikasiDao.getByCriteriaPemberitahuan(hsCriteria);
                imNotifikasiEntityApproval = notifikasiDao.getByCriteriaApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[NotifikasiBoImpl.getSearchPersonalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imNotifikasiEntity != null){
//                Integer notifJumlah = imNotifikasiEntityApproval.size();
//                String jml = String.valueOf(notifJumlah);
                Notifikasi returnNotifikasi;
                // Looping from dao to object and save in collection
                for(ImNotifikasiEntity notifikasiEntity : imNotifikasiEntity){
                    returnNotifikasi = new Notifikasi();
                    returnNotifikasi.setNotifId(notifikasiEntity.getNotifId());
                    returnNotifikasi.setNip(notifikasiEntity.getNip());
                    returnNotifikasi.setRead(notifikasiEntity.getRead());
                    returnNotifikasi.setNote(notifikasiEntity.getNote());
                    returnNotifikasi.setTipeNotifId(notifikasiEntity.getTipeNotifId());
                    returnNotifikasi.setTipeNotifName(notifikasiEntity.getTipeNotifName());
                    returnNotifikasi.setNoRequest(notifikasiEntity.getNoRequest());
                    returnNotifikasi.setFromPerson(notifikasiEntity.getFromPerson());

                    returnNotifikasi.setCreatedWho(notifikasiEntity.getCreatedWho());
                    returnNotifikasi.setCreatedDate(notifikasiEntity.getCreatedDate());
                    returnNotifikasi.setLastUpdate(notifikasiEntity.getLastUpdate());
                    returnNotifikasi.setAction(notifikasiEntity.getAction());
                    returnNotifikasi.setFlag(notifikasiEntity.getFlag());
//                    returnNotifikasi.setJmlApproval(jml);
                    listOfResult.add(returnNotifikasi);
                }
            }

            if(imNotifikasiEntityApproval != null){
                Integer notifJumlah = imNotifikasiEntityApproval.size();
                String jml = String.valueOf(notifJumlah);
                Notifikasi returnNotifikasi;
                // Looping from dao to object and save in collection
                for(ImNotifikasiEntity notifikasiEntity : imNotifikasiEntityApproval){
                    returnNotifikasi = new Notifikasi();
                    returnNotifikasi.setNotifId(notifikasiEntity.getNotifId());
                    returnNotifikasi.setNip(notifikasiEntity.getNip());
                    returnNotifikasi.setRead(notifikasiEntity.getRead());
                    returnNotifikasi.setNote(notifikasiEntity.getNote());
                    returnNotifikasi.setTipeNotifId(notifikasiEntity.getTipeNotifId());
                    returnNotifikasi.setTipeNotifName(notifikasiEntity.getTipeNotifName());
                    returnNotifikasi.setNoRequest(notifikasiEntity.getNoRequest());
                    returnNotifikasi.setFromPerson(notifikasiEntity.getFromPerson());

                    returnNotifikasi.setCreatedWho(notifikasiEntity.getCreatedWho());
                    returnNotifikasi.setCreatedDate(notifikasiEntity.getCreatedDate());
                    returnNotifikasi.setLastUpdate(notifikasiEntity.getLastUpdate());
                    returnNotifikasi.setAction(notifikasiEntity.getAction());
                    returnNotifikasi.setFlag(notifikasiEntity.getFlag());
                    returnNotifikasi.setJmlApproval(jml);
                    listOfResult.add(returnNotifikasi);
                }
            }
            if(imNotifikasiEntityPemberitahuan != null){
                Integer notifJumlah = imNotifikasiEntityPemberitahuan.size();
                String jml = String.valueOf(notifJumlah);
                Notifikasi returnNotifikasi;
                // Looping from dao to object and save in collection
                for(ImNotifikasiEntity notifikasiEntity : imNotifikasiEntityPemberitahuan){
                    returnNotifikasi = new Notifikasi();
                    returnNotifikasi.setNotifId(notifikasiEntity.getNotifId());
                    returnNotifikasi.setNip(notifikasiEntity.getNip());
                    returnNotifikasi.setRead(notifikasiEntity.getRead());
                    returnNotifikasi.setNote(notifikasiEntity.getNote());
                    returnNotifikasi.setTipeNotifId(notifikasiEntity.getTipeNotifId());
                    returnNotifikasi.setTipeNotifName(notifikasiEntity.getTipeNotifName());
                    returnNotifikasi.setNoRequest(notifikasiEntity.getNoRequest());
                    returnNotifikasi.setFromPerson(notifikasiEntity.getFromPerson());

                    returnNotifikasi.setCreatedWho(notifikasiEntity.getCreatedWho());
                    returnNotifikasi.setCreatedDate(notifikasiEntity.getCreatedDate());
                    returnNotifikasi.setLastUpdate(notifikasiEntity.getLastUpdate());
                    returnNotifikasi.setAction(notifikasiEntity.getAction());
                    returnNotifikasi.setFlag(notifikasiEntity.getFlag());
                    returnNotifikasi.setJmlPemberitahuan(jml);
                    listOfResult.add(returnNotifikasi);
                }
            }
        }
        logger.info("[NotifikasiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<Notifikasi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Notifikasi> getComboNotifikasiWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Notifikasi> listComboNotifikasi = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImNotifikasiEntity> listPersonal = null;
        try {
            listPersonal = notifikasiDao.getListPersonal(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImNotifikasiEntity imNotifikasiEntity : listPersonal) {
                Notifikasi itemComboNotifikasi = new Notifikasi();
                itemComboNotifikasi.setNotifId(imNotifikasiEntity.getNotifId());
                itemComboNotifikasi.setNip(imNotifikasiEntity.getNip());
                itemComboNotifikasi.setNote(imNotifikasiEntity.getNote());
                itemComboNotifikasi.setRead(imNotifikasiEntity.getRead());
                itemComboNotifikasi.setTipeNotifName(imNotifikasiEntity.getTipeNotifName());
                itemComboNotifikasi.setTipeNotifId(imNotifikasiEntity.getTipeNotifId());
                itemComboNotifikasi.setFromPerson(imNotifikasiEntity.getFromPerson());
                itemComboNotifikasi.setNoRequest(imNotifikasiEntity.getNoRequest());
                listComboNotifikasi.add(itemComboNotifikasi);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboNotifikasi;
    }
    @Override
    public void sendNotif(Notifikasi notifikasi) {
        String nip=notifikasi.getNip();
        String id=notifikasi.getNoRequest();
        String tipeNotifId=notifikasi.getTipeNotifId();
        String tipeNotifName=notifikasi.getTipeNotifName();
        String note=notifikasi.getNote();
        String createdWho=notifikasi.getCreatedWho();
        String to=notifikasi.getTo();
        String pengganti = notifikasi.getNip();
        String os = notifikasi.getOs();

        if (to!=null){
            if (("atasan").equalsIgnoreCase(to)){
                SendNotifKeAtasanLangsung(nip,id,tipeNotifId,tipeNotifName,note,createdWho, os);
            }else if (("kabag").equalsIgnoreCase(to)){
                SendNotifKeKabag(nip,id,tipeNotifId,tipeNotifName,note,createdWho, os);
            }else if (("kabid").equalsIgnoreCase(to)){
                SendNotifKeKabid(nip,id,tipeNotifId,tipeNotifName,note,createdWho, os);
            }else if (("self").equalsIgnoreCase(to)){
                SendNotifSelf(nip,id,tipeNotifName,note,createdWho, os);
            }else if (("plt").equalsIgnoreCase(to)){
                SendNotifPlt(nip,id,tipeNotifName,note,createdWho,pengganti, os);
            }else if (("ditentukan").equalsIgnoreCase(to)){
                sendNotifDitentukan(nip,id,tipeNotifId,tipeNotifName,note,createdWho);
            }
        }
    }

    @Override
    public void SendNotifKeAtasanLangsung(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho, String os){
        List<PersonilPosition> personilPositionList;
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Notifikasi dataUntukAtasan = new Notifikasi();
        dataUntukAtasan.setNip(nip);
        dataUntukAtasan.setTipeNotifId(tipeNotifId);
        personilPositionList = daftarAtasanLangsung(dataUntukAtasan);

//        if (tipeNotifId.equals("TN66"))
//            action = "TASK_CUTI";
//        if (tipeNotifId.equals("TN55"))
//            action = "TASK_IJIN";
        if (tipeNotifId.equals("TI"))
            action = "TASK_SPPD";
        if (tipeNotifId.equals("TN23"))
            action = "TASK_TRAINING";

        if (personilPositionList.size() > 0) {
            for (PersonilPosition personilPosition : personilPositionList){
                // Send Notification web
                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                String idNotif = notifikasiDao.getNextNotifikasiId();
                addNotif.setNotifId(idNotif);
                addNotif.setNote(note);
                addNotif.setTipeNotifId(tipeNotifId);
                addNotif.setTipeNotifName(tipeNotifName);
                addNotif.setRead("Y");
                addNotif.setFlag("Y");
                addNotif.setAction("C");
                addNotif.setNip(personilPosition.getNip());
                addNotif.setFromPerson(nip);
                addNotif.setNoRequest(id);
                addNotif.setCreatedDate(updateTime);
                addNotif.setCreatedWho(createdWho);
                addNotif.setLastUpdate(updateTime);
                addNotif.setLastUpdateWho(createdWho);
                try {
                    notifikasiDao.addAndSave(addNotif);
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }

                //send notification android
                List<ItNotifikasiFcmEntity> notifikasiFcm = null;
                try {
                    notifikasiFcm = notifikasiFcmDao.getAll();
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }

//                for (ItNotifikasiFcmEntity entity : notifikasiFcm) {
//                    if (entity.getUserId().equals(personilPosition.getNip())) {
//                        ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), note, entity.getOs());
//                        break;
//                    }
//                }

                for (ItNotifikasiFcmEntity entity : notifikasiFcm) {
                    if (entity.getUserId().equals(personilPosition.getNip())) {
                        FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), note, action, entity.getOs(), null);
                        break;
                    }
                }
            }
        }else {
            ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
            String idNotif = notifikasiDao.getNextNotifikasiId();
            addNotif.setNotifId(idNotif);
            addNotif.setNote(note);
            addNotif.setTipeNotifId(tipeNotifId);
            addNotif.setTipeNotifName(tipeNotifName);
            addNotif.setRead("Y");
            addNotif.setFlag("Y");
            addNotif.setAction("C");
            addNotif.setNip("0001");
            addNotif.setFromPerson(nip);
            addNotif.setNoRequest(id);
            addNotif.setCreatedDate(updateTime);
            addNotif.setCreatedWho(createdWho);
            addNotif.setLastUpdate(updateTime);
            addNotif.setLastUpdateWho(createdWho);
            try {
                notifikasiDao.addAndSave(addNotif);
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            //send notification android
            List<ItNotifikasiFcmEntity> notifikasiFcm = null;
            try {
                notifikasiFcm = notifikasiFcmDao.getAll();
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

//            for (ItNotifikasiFcmEntity entity : notifikasiFcm) {
//                if (entity.getUserId().equals("0001")) {
//                    ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), note, entity.getOs());
//                    break;
//                }
//            }

            for (ItNotifikasiFcmEntity entity : notifikasiFcm) {
                if (entity.getUserId().equals("0001")) {
                    FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), note, action, entity.getOs(), null);
                    break;
                }
            }

        }

    }


    @Override
    public void SendNotifSelf(String nip, String id, String tipeNotifName, String note, String createdWho, String os){
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        // Send Notification
        ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
        String idNotif = notifikasiDao.getNextNotifikasiId();
        addNotif.setNotifId(idNotif);
        addNotif.setTipeNotifId("umum");
        addNotif.setTipeNotifName(tipeNotifName);
        addNotif.setRead("Y");
        addNotif.setFlag("Y");
        addNotif.setAction("C");
        addNotif.setNip(nip);
        addNotif.setNote(note);
        addNotif.setFromPerson(createdWho);
        addNotif.setNoRequest(id);
        addNotif.setCreatedDate(updateTime);
        addNotif.setCreatedWho(createdWho);
        addNotif.setLastUpdate(updateTime);
        addNotif.setLastUpdateWho(createdWho);

        try {
            notifikasiDao.addAndSave(addNotif);
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        List<ItNotifikasiFcmEntity> notifikasiFcm = null;

        try {
            notifikasiFcm = notifikasiFcmDao.getAll();
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

//        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//            if(entity.getUserId().equals(nip)){
//                ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), addNotif.getNote(), entity.getOs());
//                break;
//            }
//        }

        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
            if(entity.getUserId().equals(nip)){
                FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), addNotif.getNote(), CLICK_IJIN, entity.getOs(), null);
                break;
            }
        }
    }

    @Override
    public void SendNotifPlt(String nip, String id, String tipeNotifName, String note, String createdWho,String pengganti, String os){
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        // Send Notification
        ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
        String idNotif = notifikasiDao.getNextNotifikasiId();
        addNotif.setNotifId(idNotif);
        addNotif.setTipeNotifId("umum");
        addNotif.setTipeNotifName(tipeNotifName);
        addNotif.setRead("Y");
        addNotif.setFlag("Y");
        addNotif.setAction("C");
        addNotif.setNip(nip);
        addNotif.setNote(note);
        addNotif.setFromPerson(createdWho);
        addNotif.setNoRequest(id);
        addNotif.setCreatedDate(updateTime);
        addNotif.setCreatedWho(createdWho);
        addNotif.setLastUpdate(updateTime);
        addNotif.setLastUpdateWho(createdWho);

        try {
            notifikasiDao.addAndSave(addNotif);
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        List<ItNotifikasiFcmEntity> notifikasiFcm = null;

        try {
            notifikasiFcm = notifikasiFcmDao.getAll();
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

//        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//            if(entity.getUserId().equals(nip)){
//               ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), addNotif.getNote(), entity.getOs());
//                break;
//            }
//        }

        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
            if(entity.getUserId().equals(nip)){
                FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), addNotif.getNote(), CLICK_IJIN, entity.getOs(), null);
                break;
            }
        }
    }

    @Override
    public void SendNotifKeKabag(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho, String os){
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        List<PersonilPosition> personilPositionList = new ArrayList<>();
//        action = "TASK_LEMBUR";
        action = null;
        personilPositionList = daftarKabag(nip);
        if (personilPositionList.size()!=0){
            for (PersonilPosition personilPosition : personilPositionList ){
                // Send Notification
                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                String idNotif = notifikasiDao.getNextNotifikasiId();
                addNotif.setNotifId(idNotif);
                addNotif.setNote(note);
                addNotif.setTipeNotifId(tipeNotifId);
                addNotif.setTipeNotifName(tipeNotifName);
                addNotif.setRead("Y");
                addNotif.setFlag("Y");
                addNotif.setAction("C");
                addNotif.setNip(personilPosition.getNip());
                addNotif.setFromPerson(nip);
                addNotif.setNoRequest(id);
                addNotif.setCreatedDate(updateTime);
                addNotif.setCreatedWho(createdWho);
                addNotif.setLastUpdate(updateTime);
                addNotif.setLastUpdateWho(createdWho);
                try {
                    notifikasiDao.addAndSave(addNotif);
                } catch (HibernateException e) {
                    logger.error("[NotifikasiBoImpl.SendNotifKeKabag] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
                List<ItNotifikasiFcmEntity> notifikasiFcm = null;

                try {
                    notifikasiFcm = notifikasiFcmDao.getAll();
                } catch (HibernateException e) {
                    logger.error("[NotifikasiBoImpl.SendNotifKeKabag] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }

//                for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                    if(entity.getUserId().equals(personilPosition.getNip())){
//                        ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), note, entity.getOs());
//                        break;
//                    }
//                }

                for (ItNotifikasiFcmEntity entity : notifikasiFcm){
                    if(entity.getUserId().equals(personilPosition.getNip())){
                        FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), note, action, entity.getOs(), null);
                        break;
                    }
                }
            }
        }else {
            SendNotifKeKabid(nip,id,tipeNotifId,tipeNotifName,note,createdWho, os);
        }

    }
    @Override
    public void SendNotifKeKabid(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho, String os){
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        List<String> posisiStrukturJabatan = getKabid(nip);
        String nipKabid = "";
        if (posisiStrukturJabatan.size()>0){
            // Send Notification
            for (String posisiIdLoop: posisiStrukturJabatan){
                List<ItPersonilPositionEntity> personilEntity = new ArrayList<>();
                personilEntity = personilPositionDao.getAllPegawaiOnSamePosisi(posisiIdLoop);
                if (personilEntity.size()>0){
                    for (ItPersonilPositionEntity personilLoop: personilEntity){
                        ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                        String idNotif = notifikasiDao.getNextNotifikasiId();
                        addNotif.setNotifId(idNotif);
                        addNotif.setNote(note);
                        addNotif.setTipeNotifId(tipeNotifId);
                        addNotif.setTipeNotifName(tipeNotifName);
                        addNotif.setRead("Y");
                        addNotif.setFlag("Y");
                        addNotif.setAction("C");
                        addNotif.setNip(personilLoop.getNip());
                        addNotif.setFromPerson(nip);
                        addNotif.setNoRequest(id);
                        addNotif.setCreatedDate(updateTime);
                        addNotif.setCreatedWho(createdWho);
                        addNotif.setLastUpdate(updateTime);
                        addNotif.setLastUpdateWho(createdWho);
                        try {
                            notifikasiDao.addAndSave(addNotif);
                        } catch (HibernateException e) {
                            logger.error("[NotifikasiBoimpl.SendNotifKeKabid] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                        List<ItNotifikasiFcmEntity> notifikasiFcm = null;
                        try {
                            notifikasiFcm = notifikasiFcmDao.getAll();
                        } catch (HibernateException e) {
                            logger.error("[NotifikasiBoimpl.SendNotifKeKabid] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }

//                        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                            if(entity.getUserId().equals(nipKabid)){
//                                ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), note, entity.getOs());
//                                break;
//                            }
//                        }

                        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
                            if(entity.getUserId().equals(nipKabid)){
                                FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), note, CLICK_IJIN, entity.getOs(), null);
                                break;
                            }
                        }
                    }
                }else{
                    ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                    String idNotif = notifikasiDao.getNextNotifikasiId();
                    addNotif.setNotifId(idNotif);
                    addNotif.setNote(note);
                    addNotif.setTipeNotifId(tipeNotifId);
                    addNotif.setTipeNotifName(tipeNotifName);
                    addNotif.setRead("Y");
                    addNotif.setFlag("Y");
                    addNotif.setAction("C");
                    addNotif.setNip("0001");
                    addNotif.setFromPerson(nip);
                    addNotif.setNoRequest(id);
                    addNotif.setCreatedDate(updateTime);
                    addNotif.setCreatedWho(createdWho);
                    addNotif.setLastUpdate(updateTime);
                    addNotif.setLastUpdateWho(createdWho);
                    try {
                        notifikasiDao.addAndSave(addNotif);
                    } catch (HibernateException e) {
                        logger.error("[NotifikasiBoimpl.SendNotifKeKabid] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }else {
            ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
            String idNotif = notifikasiDao.getNextNotifikasiId();
            addNotif.setNotifId(idNotif);
            addNotif.setNote(note);
            addNotif.setTipeNotifId(tipeNotifId);
            addNotif.setTipeNotifName(tipeNotifName);
            addNotif.setRead("Y");
            addNotif.setFlag("Y");
            addNotif.setAction("C");
            addNotif.setNip("0001");
            addNotif.setFromPerson(nip);
            addNotif.setNoRequest(id);
            addNotif.setCreatedDate(updateTime);
            addNotif.setCreatedWho(createdWho);
            addNotif.setLastUpdate(updateTime);
            addNotif.setLastUpdateWho(createdWho);
            try {
                notifikasiDao.addAndSave(addNotif);
            } catch (HibernateException e) {
                logger.error("[NotifikasiBoimpl.SendNotifKeKabid] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
        }

    }

//    @Override
//    public void SendNotifKeAllAtasan(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho){
//        List<StrukturJabatan> strukturJabatanList = new ArrayList<>();
//        String branchId = null;
//        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//        List<ItPersonilPositionEntity> personilPositionEntityList= new ArrayList<>();
//
//        personilPositionEntityList=personilPositionDao.getListPersonilPosition(nip);
//
//        for (ItPersonilPositionEntity personilPositionEntity:personilPositionEntityList){
//            branchId=personilPositionEntity.getBranchId();
//        }
//        try {
//            strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(nip,branchId);
//        } catch (HibernateException e) {
//            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//        }
//
//        for (StrukturJabatan strukturJabatan : strukturJabatanList){
//            // Search Leader
//            if (strukturJabatan != null){
//                String[] parts = strukturJabatan.getParentId().split("-");
//                String parent = parts[0];
//                int batas=1;
//                if (parent != null){
//                    //cek apakah parent adalah kabid
//                    StrukturJabatan hasilStruktur=new StrukturJabatan();
//                    List<StrukturJabatan> resultStruktur = new ArrayList<>();
//                    do {
//                        try {
//                            resultStruktur = strukturJabatanDao.cekKelompok(parent,branchId);
//                        } catch (HibernateException e) {
//                            logger.error("[LemburBoimpl.saveAdd] Error, " + e.getMessage());
//                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                        }
//                        for (StrukturJabatan search : resultStruktur){
//                            hasilStruktur.setPositionKelompokId(search.getPositionKelompokId());
//                            hasilStruktur.setNip(search.getNip());
//                            hasilStruktur.setParentId(search.getParentId());
//                            parent=search.getParentId();
//                        }
//                        batas++;
//                    }while (!("KL03").equalsIgnoreCase(hasilStruktur.getPositionKelompokId())||batas==10);
//                    // Send Notification
//                    ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
//                    String idNotif = notifikasiDao.getNextNotifikasiId();
//                    addNotif.setNotifId(idNotif);
//                    addNotif.setNote(note);
//                    addNotif.setTipeNotifId(tipeNotifId);
//                    addNotif.setTipeNotifName(tipeNotifName);
//                    addNotif.setRead("Y");
//                    addNotif.setFlag("Y");
//                    addNotif.setAction("C");
//                    addNotif.setNip(hasilStruktur.getNip());
//                    addNotif.setFromPerson(nip);
//                    addNotif.setNoRequest(id);
//                    addNotif.setCreatedDate(updateTime);
//                    addNotif.setCreatedWho(createdWho);
//                    addNotif.setLastUpdate(updateTime);
//                    addNotif.setLastUpdateWho(createdWho);
//                    try {
//                        notifikasiDao.addAndSave(addNotif);
//                    } catch (HibernateException e) {
//                        logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                    }
//                    List<ItNotifikasiFcmEntity> notifikasiFcm = null;
//
//                    try {
//                        notifikasiFcm = notifikasiFcmDao.getAll();
//                    } catch (HibernateException e) {
//                        logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                    }
//
//                    for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                        if(entity.getUserId().equals(hasilStruktur.getNip())){
//                            FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), note);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }
//    @Override
//    public void SendNotifKeAllBawahan(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho){
//        List<StrukturJabatan> strukturJabatanList = new ArrayList<>();
//        String branchId = null;
//        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//        List<ItPersonilPositionEntity> personilPositionEntityList= new ArrayList<>();
//
//        personilPositionEntityList=personilPositionDao.getListPersonilPosition(nip);
//
//        for (ItPersonilPositionEntity personilPositionEntity:personilPositionEntityList){
//            branchId=personilPositionEntity.getBranchId();
//        }
//        try {
//            strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(nip,branchId);
//        } catch (HibernateException e) {
//            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//        }
//
//        for(StrukturJabatan strukturJabatan : strukturJabatanList){
//            // Search Leader
//            if (strukturJabatan != null){
//                String[] parts = strukturJabatan.getParentId().split("-");
//                String parent = parts[0];
//                int batas=1;
//                if (parent != null){
//                    //cek apakah parent adalah kabid
//                    StrukturJabatan hasilStruktur=new StrukturJabatan();
//                    List<StrukturJabatan> resultStruktur = new ArrayList<>();
//                    do {
//                        try {
//                            resultStruktur = strukturJabatanDao.cekKelompok(parent,branchId);
//                        } catch (HibernateException e) {
//                            logger.error("[LemburBoimpl.saveAdd] Error, " + e.getMessage());
//                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                        }
//                        for (StrukturJabatan search : resultStruktur){
//                            hasilStruktur.setPositionKelompokId(search.getPositionKelompokId());
//                            hasilStruktur.setNip(search.getNip());
//                            hasilStruktur.setParentId(search.getParentId());
//                            parent=search.getParentId();
//                        }
//                        batas++;
//                    }while (!("KL03").equalsIgnoreCase(hasilStruktur.getPositionKelompokId())||batas==10);
//                    // Send Notification
//                    ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
//                    String idNotif = notifikasiDao.getNextNotifikasiId();
//                    addNotif.setNotifId(idNotif);
//                    addNotif.setNote(note);
//                    addNotif.setTipeNotifId(tipeNotifId);
//                    addNotif.setTipeNotifName(tipeNotifName);
//                    addNotif.setRead("Y");
//                    addNotif.setFlag("Y");
//                    addNotif.setAction("C");
//                    addNotif.setNip(hasilStruktur.getNip());
//                    addNotif.setFromPerson(nip);
//                    addNotif.setNoRequest(id);
//                    addNotif.setCreatedDate(updateTime);
//                    addNotif.setCreatedWho(createdWho);
//                    addNotif.setLastUpdate(updateTime);
//                    addNotif.setLastUpdateWho(createdWho);
//                    try {
//                        notifikasiDao.addAndSave(addNotif);
//                    } catch (HibernateException e) {
//                        logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                    }
//                    List<ItNotifikasiFcmEntity> notifikasiFcm = null;
//
//                    try {
//                        notifikasiFcm = notifikasiFcmDao.getAll();
//                    } catch (HibernateException e) {
//                        logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                    }
//
//                    for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                        if(entity.getUserId().equals(hasilStruktur.getNip())){
//                            FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), note);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }

    @Override
    public List<TrainingPerson> searchTrainingPerson(TrainingPerson bean) throws GeneralBOException {
        List<TrainingPerson> result = new ArrayList<TrainingPerson>();
        String nip = CommonUtil.userIdLogin();
        List<ItHrisTrainingPersonEntity> trainingList = null;

        if (bean != null){
            String posisi=null,branch=null,kelompok="";
            List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getListPersonilPosition(nip);
            for (ItPersonilPositionEntity personilPositionEntity:personilPositionEntityList){
                posisi=personilPositionEntity.getPositionId();
                branch = personilPositionEntity.getBranchId();
            }
            Map hsCriteria = new HashMap();
            if (!"".equalsIgnoreCase(bean.getTrainingId())){
                hsCriteria.put("training_id", bean.getTrainingId());
            }

            if (!"".equalsIgnoreCase(bean.getPersonId())){
                hsCriteria.put("person_id", bean.getPersonId());
            }
            if (!"".equalsIgnoreCase(nip)){
                hsCriteria.put("nip_atasan",nip);
            }
            hsCriteria.put("flag","Y");

            try {
                trainingList = trainingPersonDao.getListTrainingForApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
            List<ImPosition> positionList = positionDao.getListDivisi(posisi);
            for (ImPosition position:positionList){
                kelompok=position.getKelompokId();
            }

            if (trainingList != null) {
                TrainingPerson addData;
                for (ItHrisTrainingPersonEntity listData : trainingList) {
                    addData = new TrainingPerson();
                    addData.setTrainingPersonId(listData.getTrainingPersonId());
                    addData.setTrainingId(listData.getTrainingId());
                    addData.setPersonId(listData.getPersonId());
                    addData.setPersonName(listData.getPersonName());
                    addData.setDivisiId(listData.getDivisiId());
                    addData.setApprovalId(listData.getApprovalId());
                    addData.setApprovalName(listData.getApprovalName());
                    addData.setApprovalDate(listData.getApprovalDate());
                    addData.setApprovalFlag(listData.getApprovalFlag());
                    addData.setNotApprovalNote(listData.getNotApprovalNote());
                    addData.setApprovalBosId(listData.getApprovalBosId());
                    addData.setApprovalBosName(listData.getApprovalBosName());
                    addData.setApprovalBosDate(listData.getApprovalBosDate());
                    addData.setApprovalBosFlag(listData.getApprovalBosFlag());
                    addData.setNotApprovalBosNote(listData.getNotApprovalBosNote());
                    addData.setCreatedDate(listData.getCreatedDate());
                    addData.setCreatedWho(listData.getCreateDateWho());
                    addData.setLastUpdate(listData.getLastUpdate());
                    addData.setLastUpdateWho(listData.getLastUpdateWho());
                    addData.setFlag(listData.getFlag());
                    addData.setAction(listData.getAction());
                    addData.setApprovalSdm(listData.getApprovalSdm());
                    addData.setApprovalSdmDate(listData.getApprovalSdmDate());
                    addData.setNotApprovalSdmNote(listData.getNotApprovalSdmNote());

                    if ("Y".equalsIgnoreCase(listData.getApprovalFlag())) {
                        addData.setIconApproveAtasan("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
                    } else if ("N".equalsIgnoreCase(listData.getApprovalFlag())) {
                        addData.setIconApproveAtasan("<img src='\\hris\\pages\\images\\icon_failure.ico'/>");
                    }

                    if ("Y".equalsIgnoreCase(listData.getApprovalBosFlag())) {
                        addData.setIconApproveKepala("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
                    } else if ("N".equalsIgnoreCase(listData.getApprovalBosFlag())) {
                        addData.setIconApproveKepala("<img src='\\hris\\pages\\images\\icon_failure.ico'/>");
                    }

                    if ("Y".equalsIgnoreCase(listData.getApprovalSdm())) {
                        addData.setIconApproveSdm("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
                    } else if ("N".equalsIgnoreCase(listData.getApprovalBosFlag())) {
                        addData.setIconApproveSdm("<img src='\\hris\\pages\\images\\icon_failure.ico'/>");
                    }

                    if (listData.getTrainingId() != null) {
                        hsCriteria = new HashMap();
                        hsCriteria.put("training_id", listData.getTrainingId());
                        hsCriteria.put("flag", "Y");
                        List<ItHrisTrainingEntity> dataTraining = null;
                        try {
                            dataTraining = trainingDao.getByCriteria(hsCriteria);
                        } catch (HibernateException e) {
                            logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                        }

                        if (dataTraining != null) {
                            for (ItHrisTrainingEntity listTraining : dataTraining) {
                                addData.setTrainingName(listTraining.getTrainingName());
                                addData.setTrainingStartdate(listTraining.getTrainingStartDate());
                                addData.setTrainingEndDate(listTraining.getTrainingEndDate());
                            }
                        }
                    }
                    result.add(addData);

                }
            }
        }
        return result;
    }

    @Override
    public void setReadNotif(Notifikasi bean) throws GeneralBOException {
        if (bean != null) {
            ImNotifikasiEntity notif = new ImNotifikasiEntity();
            notif.setNotifId(bean.getNotifId());
            notif.setNip(bean.getNip());
            notif.setFromPerson(bean.getFromPerson());
            notif.setNote(bean.getNote());
            notif.setNoRequest(bean.getNoRequest());
            notif.setRead(bean.getRead());
            notif.setTipeNotifId(bean.getTipeNotifId());
            notif.setTipeNotifName(bean.getTipeNotifName());
            notif.setFlag(bean.getFlag());
            notif.setAction(bean.getAction());
            notif.setCreatedDate(bean.getCreatedDate());
            notif.setCreatedWho(bean.getCreatedWho());
            notif.setLastUpdate(bean.getLastUpdate());
            notif.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                notifikasiDao.updateAndSave(notif);
            } catch (HibernateException e) {
                logger.error("[NotifikasiBoImpl.setReadNotif] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
        }
    }

    public List<IjinKeluar> searchIjinKeluarPerson(IjinKeluar bean) throws GeneralBOException {
        List<IjinKeluar> result = new ArrayList<IjinKeluar>();
        List<IjinKeluarEntity> ijinKeluarList = null;
        String nip = CommonUtil.userIdLogin();

        if (bean != null){
            Map hsCriteria = new HashMap();

            if (!"".equalsIgnoreCase(bean.getIjinKeluarId())){
                hsCriteria.put("ijin_keluar_id", bean.getIjinKeluarId());
            }

            if (!"".equalsIgnoreCase(bean.getNip())){
                hsCriteria.put("nip", bean.getNip());
            }
            if (!"".equalsIgnoreCase(CommonUtil.userIdLogin())){
                hsCriteria.put("nip_atasan", CommonUtil.userIdLogin());
            }

            hsCriteria.put("flag","Y");

            try {
                ijinKeluarList = ijinKeluarDao.getListIjinKeluarForApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
            String posisi=null,branch=null,kelompok="";
            List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getListPersonilPosition(nip);
            for (ItPersonilPositionEntity personilPositionEntity:personilPositionEntityList){
                posisi=personilPositionEntity.getPositionId();
                branch = personilPositionEntity.getBranchId();
            }
            List<ImPosition> positionList = positionDao.getListDivisi(posisi);
            for (ImPosition position:positionList){
                kelompok=position.getKelompokId();
            }
            if (ijinKeluarList != null){
                IjinKeluar addData;
                for (IjinKeluarEntity listData : ijinKeluarList){
                    if (bean.getFrom().equals("ijinKeluar")){
                        if (listData.getIjinKeluarId().length()==9){
                            addData = new IjinKeluar();
                            addData.setIjinKeluarId(listData.getIjinKeluarId());
                            addData.setNotApprovalNote(listData.getNotApprovalNote());
                            addData.setNip(listData.getNip());
                            addData.setNamaPegawai(listData.getNamaPegawai());
                            addData.setApprovalDate(listData.getApprovalDate());
                            addData.setApprovalFlag(listData.getApprovalFlag());
                            addData.setApprovalId(listData.getApprovalId());
                            addData.setApprovalName(listData.getApprovalName());
                            addData.setIjinId(listData.getIjinId());
                            addData.setApprovalSdmFlag(listData.getApprovalSdmFlag());
                            addData.setIjinName(listData.getIjinName());
                            addData.setTanggalAkhir(listData.getTanggalAkhir());
                            addData.setJamKeluar(listData.getJamKeluar());
                            addData.setJamKembali(listData.getJamKembali());
                            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            addData.setStTanggalAwal(df.format(listData.getTanggalAwal()));
                            addData.setStTanggalAkhir(df.format(listData.getTanggalAkhir()));
                            addData.setTanggalAwal(listData.getTanggalAwal());
                            addData.setKeterangan(listData.getKeterangan());
                            addData.setLamaIjin(listData.getLamaIjin());
                            addData.setAction(listData.getAction());
                            if (listData.getApprovalFlag()!=null){
                                if(listData.getApprovalFlag().equals("N")){
                                    addData.setNotApprove(true);
                                }
                            }
                            addData.setUnitId(listData.getUnitId());
                            addData.setCreatedDate(listData.getCreatedDate());
                            addData.setCreatedWho(listData.getCreatedWho());
                            addData.setFlag(listData.getFlag());
                            addData.setLastUpdate(listData.getLastUpdate());
                            addData.setLastUpdateWho(listData.getLastUpdateWho());
                            addData.setIjinName(listData.getIjinName());
                            addData.setTanggalAwal(listData.getTanggalAwal());
                            addData.setTanggalAkhir(listData.getTanggalAkhir());
                            result.add(addData);

                        }
                    }
                    else{
                        if (bean.getFrom().equals("ijinKeluarKantor")){
                            if (listData.getIjinKeluarId().length()==8){
                                addData = new IjinKeluar();
                                addData.setIjinKeluarId(listData.getIjinKeluarId());
                                addData.setNotApprovalNote(listData.getNotApprovalNote());
                                addData.setNip(listData.getNip());
                                addData.setNamaPegawai(listData.getNamaPegawai());
                                addData.setApprovalDate(listData.getApprovalDate());
                                addData.setApprovalFlag(listData.getApprovalFlag());
                                addData.setApprovalId(listData.getApprovalId());
                                addData.setApprovalName(listData.getApprovalName());
                                addData.setPositionName(listData.getPositionName());
                                addData.setPositionId(listData.getPositionId());

                                addData.setIjinId(listData.getIjinId());
                                addData.setIjinName(listData.getIjinName());
                                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                addData.setStTanggalAwal(df.format(listData.getTanggalAwal()));
                                addData.setTanggalAkhir(listData.getTanggalAkhir());
                                addData.setJamKeluar(listData.getJamKeluar());
                                addData.setJamKembali(listData.getJamKembali());
                                addData.setTanggalAwal(listData.getTanggalAwal());
                                addData.setKeterangan(listData.getKeterangan());
                                addData.setLamaIjin(listData.getLamaIjin());
                                if (listData.getApprovalFlag()!=null){
                                    if(listData.getApprovalFlag().equals("N")){
                                        addData.setNotApprove(true);
                                    }
                                }
                                Map hsCriteria3 = new HashMap();
                                hsCriteria3.put("position_id",listData.getPositionId());
                                hsCriteria3.put("flag","Y");
                                List<ImPosition> positionListDaftar = new ArrayList<>();
                                positionListDaftar = positionDao.getByCriteria(hsCriteria3);
                                for (ImPosition imPosition : positionListDaftar){
                                    addData.setPositionName(imPosition.getPositionName());
                                    addData.setDivisiId(imPosition.getDepartmentId());
                                }
                                if (addData.getDivisiId()!=null){
                                    Map hsCriteria5 = new HashMap();
                                    hsCriteria5.put("department_id",addData.getDivisiId());
                                    hsCriteria5.put("flag","Y");
                                    List<ImDepartmentEntity> imDepartmentEntityList = new ArrayList<>();
                                    imDepartmentEntityList = departmentDao.getByCriteria(hsCriteria5);
                                    for (ImDepartmentEntity imDepartmentEntity : imDepartmentEntityList){
                                        addData.setDivisiName(imDepartmentEntity.getDepartmentName());
                                    }
                                }
                                addData.setAction(listData.getAction());
                                addData.setUnitId(listData.getUnitId());
                                addData.setCreatedDate(listData.getCreatedDate());
                                addData.setCreatedWho(listData.getCreatedWho());
                                addData.setFlag(listData.getFlag());
                                addData.setLastUpdate(listData.getLastUpdate());
                                addData.setLastUpdateWho(listData.getLastUpdateWho());
                                addData.setIjinName(listData.getIjinName());
                                addData.setTanggalAwal(listData.getTanggalAwal());
                                addData.setTanggalAkhir(listData.getTanggalAkhir());

                                result.add(addData);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<Lembur> searchLemburPerson(Lembur bean) throws GeneralBOException {
        List<Lembur> result = new ArrayList<>();
        List<LemburEntity> lemburList;

        if (bean != null){
            Map hsCriteria = new HashMap();
            if (!"".equalsIgnoreCase(bean.getLemburId())){
                hsCriteria.put("lembur_id", bean.getLemburId());
            }
            if (!"".equalsIgnoreCase(bean.getNip())){
                hsCriteria.put("nip", bean.getNip());
            }
            if (!"".equalsIgnoreCase(CommonUtil.userIdLogin())){
                hsCriteria.put("nip_atasan", CommonUtil.userIdLogin());
            }
            hsCriteria.put("flag","Y");
            try {
                lemburList = lemburDao.getListLemburForApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
            if (lemburList != null){
                Lembur addData;
                for (LemburEntity listData : lemburList){
                    addData = new Lembur();
                    addData.setLemburId(listData.getLemburId());
                    addData.setNip(listData.getNip());
                    addData.setPegawaiName(listData.getPegawaiName());
                    addData.setDivisiId(listData.getDivisiId());
                    addData.setDivisiName(listData.getDivisiName());
                    addData.setPositionId(listData.getPositionId());
                    addData.setPositionName(listData.getPositionName());
                    addData.setGolonganId(listData.getGolonganId());
                    addData.setGolonganName(listData.getGolonganName());
                    addData.setTipePegawaiId(listData.getTipePegawaiId());
                    addData.setStatusGiling(listData.getStatusGiling());
                    if((listData.getTipeLembur().equalsIgnoreCase("I"))){
                        addData.setTipeLembur("Non Rutin");
                    }else if((listData.getTipeLembur().equalsIgnoreCase("R"))){
                        addData.setTipeLembur("Rutin");
                    }
                    addData.setTanggalAwal(listData.getTanggalAwal());
                    addData.setTanggalAkhir(listData.getTanggalAkhir());
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    if(("Y").equalsIgnoreCase(listData.getApprovalFlag())){
                        addData.setStTanggalAwal(df.format(listData.getTanggalAwalSetuju()));
                        addData.setStTanggalAkhir(df.format(listData.getTanggalAkhirSetuju()));
                    }else{
                        addData.setStTanggalAwal(df.format(listData.getTanggalAwal()));
                        addData.setStTanggalAkhir(df.format(listData.getTanggalAkhir()));
                    }
                    addData.setJamAwal(listData.getJamAwal());
                    addData.setJamAkhir(listData.getJamAkhir());
                    addData.setLamaJam(listData.getLamaJam());
                    addData.setKeterangan(listData.getKeterangan());
                    addData.setApprovalId(listData.getApprovalId());
                    addData.setApprovalFlag(listData.getApprovalFlag());
                    addData.setApprovalDate(listData.getApprovalDate());
                    addData.setApprovalName(listData.getApprovalName());

                    addData.setCreatedDate(listData.getCreatedDate());
                    addData.setCreatedWho(listData.getCreatedWho());
                    addData.setFlag(listData.getFlag());
                    addData.setLastUpdate(listData.getLastUpdate());
                    addData.setLastUpdateWho(listData.getLastUpdateWho());
                    result.add(addData);
                }
            }
        }
        return result;
    }
    @Override
    public List<Indisipliner> searchIndisiplinerPerson(Indisipliner bean) throws GeneralBOException {
        List<Indisipliner> result = new ArrayList<Indisipliner>();
        List<ItIndisiplinerEntity> indisiplinerEntityList = null;
        String nip = CommonUtil.userIdLogin();

        if (bean != null){
            Map hsCriteria = new HashMap();
            if (!"".equalsIgnoreCase(bean.getIndisiplinerId())){
                hsCriteria.put("indisipliner_id", bean.getIndisiplinerId());
            }
            if (!"".equalsIgnoreCase(bean.getNip())){
                hsCriteria.put("nip", bean.getNip());
            }
            if (!"".equalsIgnoreCase(nip)){
                hsCriteria.put("nip_atasan", nip);
            }
            hsCriteria.put("flag","Y");

            try {
                indisiplinerEntityList = indisiplinerDao.getListIndisiplinerForApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
            if (indisiplinerEntityList != null){
                Indisipliner addData;
                for (ItIndisiplinerEntity listData : indisiplinerEntityList){
                    addData = new Indisipliner();
                    addData.setIndisiplinerId(listData.getIndisiplinerId());
                    addData.setNip(listData.getNip());
                    addData.setNama(listData.getNama());
                    addData.setBagianId(listData.getBagianId());
                    addData.setBagianName(listData.getBagianName());
                    addData.setTipeIndisipliner(listData.getTipeIndisipliner());
                    if (listData.getTipeIndisipliner().equalsIgnoreCase("SP0")){
                        addData.setTipeIndisipliner("Teguran");
                    }
                    addData.setKeteranganPelanggaran(listData.getKeteranganPelanggaran());
                    addData.setDampak(listData.getDampak());
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    if (listData.getTanggalAwalBlokirAbsensi()!=null){
                        addData.setStTanggalAwalBlokirAbsensi(df.format(listData.getTanggalAwalBlokirAbsensi()));
                    }
                    if (listData.getTanggalAwalBlokirAbsensi()!=null) {
                        addData.setStTanggalAkhirBlokirAbsensi(df.format(listData.getTanggalAkhirBlokirAbsensi()));
                    }

                    addData.setCreatedDate(listData.getCreatedDate());
                    addData.setCreatedWho(listData.getCreatedWho());
                    addData.setFlag(listData.getFlag());
                    addData.setLastUpdate(listData.getLastUpdate());
                    addData.setLastUpdateWho(listData.getLastUpdateWho());

                    if (listData.getApprovalFlag()!=null){
                        if(listData.getApprovalFlag().equals("Y")){
                            addData.setIndisiplinerApprove(true);
                        }else if(listData.getApprovalFlag().equals("N")){
                            addData.setNotApprove(true);
                        }
                    }
                    if (listData.getClosedFlag()!=null){
                        if(listData.getClosedFlag().equals("Y")){
                            addData.setClosed(true);
                        }
                    }

                    result.add(addData);
                }
            }
        }
        return result;
    }
    @Override
    public List<RekruitmenPabrikDetail> searchRekruitmenPabrikDetailPerson(RekruitmenPabrikDetail bean) throws GeneralBOException {
        List<RekruitmenPabrikDetail> result = new ArrayList<RekruitmenPabrikDetail>();
        List<ItRekruitmenPabrikDetailEntity> rekruitmenPabrikDetailEntityList = null;

        String nip = CommonUtil.userIdLogin();

        if (bean != null){
            Map hsCriteria = new HashMap();

            if (!"".equalsIgnoreCase(bean.getRekruitmenPabrikId())){
                hsCriteria.put("rekruitmen_pabrik_id", bean.getRekruitmenPabrikId());
            }
            if (!"".equalsIgnoreCase(bean.getNip())){
                hsCriteria.put("nip", bean.getNip());
            }
            if (!"".equalsIgnoreCase(nip)){
                hsCriteria.put("nip_atasan", nip);
            }
            hsCriteria.put("flag","Y");

            try {
                rekruitmenPabrikDetailEntityList = rekruitmenPabrikDetailDao.getListRekruitmenPabrikForApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
            if (rekruitmenPabrikDetailEntityList != null){
                RekruitmenPabrikDetail addData;
                for (ItRekruitmenPabrikDetailEntity listData : rekruitmenPabrikDetailEntityList){
                    addData = new RekruitmenPabrikDetail();

                    addData.setRekruitmenPabrikDetailId(listData.getRekruitmenPabrikDetailId());
                    addData.setRekruitmenPabrikId(listData.getRekruitmenPabrikId());
                    addData.setNip(listData.getNip());
                    addData.setNamaPegawai(listData.getNamaPegawai());
                    addData.setPosisiLama(listData.getPosisiLama());
                    addData.setPosisiBaru(listData.getPosisiBaru());
                    ImPosition posisiLama= positionDao.getById("positionId",listData.getPosisiLama());
                    ImPosition posisiBaru= positionDao.getById("positionId",listData.getPosisiBaru());
                    addData.setPosisiLamaName(posisiLama.getPositionName());
                    addData.setPosisiBaruName(posisiBaru.getPositionName());
                    addData.setTanggalAktif(listData.getTanggalAktif());
                    addData.setStatusGiling(listData.getStatusGiling());
                    addData.setApprovalGmId(listData.getApprovalGmId());
                    addData.setApprovalGmFlag(listData.getApprovalGmFlag());
                    addData.setApprovalGmName(listData.getApprovalGmName());
                    addData.setApprovalGmdate(listData.getApprovalGmdate());
                    addData.setApprovalSdmId(listData.getApprovalSdmId());
                    addData.setApprovalSdmFlag(listData.getApprovalSdmFlag());
                    addData.setApprovalSdmName(listData.getApprovalSdmName());
                    addData.setApprovalSdmdate(listData.getApprovalSdmdate());
                    addData.setNoKontrak(listData.getNoKontrak());
                    addData.setCreatedDate(listData.getCreatedDate());
                    addData.setCreatedWho(listData.getCreatedWho());
                    addData.setFlag(listData.getFlag());
                    addData.setLastUpdate(listData.getLastUpdate());
                    addData.setLastUpdateWho(listData.getLastUpdateWho());

                    result.add(addData);
                }
            }
        }
        return result;
    }
    @Override
    public List<CutiPegawai> searchCutiPegawaiPerson(CutiPegawai bean) throws GeneralBOException {
        List<CutiPegawai> result = new ArrayList<CutiPegawai>();
        List<ItCutiPegawaiEntity> cutiPegawaiList = null;
        String nip = CommonUtil.userIdLogin();

        if (bean != null){
            Map hsCriteria = new HashMap();

            if (!"".equalsIgnoreCase(bean.getCutiPegawaiId())){
                hsCriteria.put("cuti_pegawai_id", bean.getCutiPegawaiId());
            }

            if (!"".equalsIgnoreCase(bean.getNip())){
                hsCriteria.put("nip", bean.getNip());
            }
            if (!"".equalsIgnoreCase(nip)){
                hsCriteria.put("nip_atasan", nip);
            }
            hsCriteria.put("flag","Y");

            try {
                cutiPegawaiList = cutiPegawaiDao.getListCutiForApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
            if (cutiPegawaiList != null){
                CutiPegawai addData;
                for (ItCutiPegawaiEntity listData : cutiPegawaiList){
                    addData = new CutiPegawai();
                    addData.setCutiPegawaiId(listData.getCutiPegawaiId());
                    addData.setNip(listData.getNip());
                    addData.setSisaCutiHari(listData.getSisaCutiHari());
                    addData.setTanggalDari(listData.getTanggalDari());
                    addData.setTanggalSelesai(listData.getTanggalSelesai());
                    addData.setLamaHariCuti(listData.getLamaHariCuti());
                    addData.setApprovalDate(listData.getApprovalDate());
                    addData.setApprovalFlag(listData.getApprovalFlag());
                    addData.setCutiId(listData.getCutiId());

                    ImCutiEntity cutiEntity;
                    try{
                        cutiEntity = cutiDao.getById("cutiId",listData.getCutiId(),"Y");
                    }catch (HibernateException e){
                        logger.error("[NotifikasiBoImpl.searchCutiPegawaiPerson] Error, " + e.getMessage());
                        throw new GeneralBOException("Problem when receiving Cuti by ID, " + e.getMessage());
                    }
                    addData.setCutiName(cutiEntity.getCutiName());

                    List<ItPersonilPositionEntity> personilPositionEntityListAsli =new ArrayList<>();
                    try{
                        personilPositionEntityListAsli = personilPositionDao.getListPersonilPosition(listData.getNip());
                    }catch (HibernateException e){
                        logger.error("[NotifikasiBoImpl.searchCutiPegawaiPerson] Error, " + e.getMessage());
                        throw new GeneralBOException("Problem when receiving Personil Position by NIP, " + e.getMessage());
                    }

                    if (personilPositionEntityListAsli!=null){
                        for (ItPersonilPositionEntity personilPosition : personilPositionEntityListAsli){
                            ImPosition imPosition;
                            try{
                                imPosition = positionDao.getById("positionId",personilPosition.getPositionId());
                            }catch (HibernateException e){
                                logger.error("[NotifikasiBoImpl.searchCutiPegawaiPerson] Error, " + e.getMessage());
                                throw new GeneralBOException("Problem when receiving Position by ID, " + e.getMessage());
                            }
                            if (imPosition!=null){
                                addData.setPosisiName(imPosition.getPositionName());
                                addData.setPosisiId(imPosition.getPositionId());
                                addData.setDivisiId(imPosition.getDepartmentId());
                                addData.setUnitId(personilPosition.getBranchId());
                                ImDepartmentEntity imDepartmentEntity;
                                try{
                                    imDepartmentEntity = departmentDao.getById("departmentId",imPosition.getDepartmentId());
                                }catch (HibernateException e){
                                    logger.error("[NotifikasiBoImpl.searchCutiPegawaiPerson] Error, " + e.getMessage());
                                    throw new GeneralBOException("Problem when receiving Department by NIP, " + e.getMessage());
                                }
                                if (imDepartmentEntity!=null){
                                    addData.setDivisiName(imDepartmentEntity.getDepartmentName());
                                }
                            }
                            hsCriteria = new HashMap();
                            hsCriteria.put("branch_id",personilPosition.getBranchId());
                            hsCriteria.put("flag","Y");
                            List<ImBranches> branchesList = new ArrayList<>();
                            try{
                                branchesList = branchDao.getByCriteria(hsCriteria);
                            }catch (HibernateException e){
                                logger.error("[NotifikasiBoImpl.searchCutiPegawaiPerson] Error, " + e.getMessage());
                                throw new GeneralBOException("Problem when receiving Branch using Criteria, " + e.getMessage());
                            }
                            if (branchesList!=null){
                                for(ImBranches imBranches : branchesList){
                                    addData.setUnitName(imBranches.getBranchName());
                                }
                            }
                        }
                    }

                    addData.setApprovalId(listData.getApprovalId());
                    if ("Y".equalsIgnoreCase(listData.getApprovalFlag())){
                        addData.setCutiPegawaiApprove(true);
                    }
                    if ("N".equalsIgnoreCase(listData.getApprovalFlag())){
                        addData.setNotApprove(true);
                    }
                    addData.setTanggalDari(listData.getTanggalDari());
                    addData.setTanggalSelesai(listData.getTanggalSelesai());
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    addData.setStTanggalDari(df.format(listData.getTanggalDari()));
                    addData.setStTanggalSelesai(df.format(listData.getTanggalSelesai()));
                    ImBiodataEntity biodataEntity;
                    try{
                        biodataEntity = biodataDao.getById("nip",listData.getNip(),"Y");
                    }catch (HibernateException e){
                        logger.error("[NotifikasiBoImpl.searchCutiPegawaiPerson] Error, " + e.getMessage());
                        throw new GeneralBOException("Problem when receiving Biodata by ID, " + e.getMessage());
                    }
                    addData.setNamaPegawai(biodataEntity.getNamaPegawai());
                    addData.setNote(listData.getNote());
                    addData.setNoteApproval(listData.getNoteApproval());
                    addData.setPegawaiPenggantiSementara(listData.getPegawaiPenggantiSementara());

                    addData.setAction(listData.getAction());
                    addData.setCreatedDate(listData.getCreatedDate());
                    addData.setCreatedWho(listData.getCreatedWho());
                    addData.setFlag(listData.getFlag());
                    addData.setLastUpdate(listData.getLastUpdate());
                    addData.setLastUpdateWho(listData.getLastUpdateWho());
                    result.add(addData);
                }
            }
        }
        return result;
    }
    @Override
    public List<AbsensiPegawai> searchAbsensiPegawaiPerson(AbsensiPegawai bean) throws GeneralBOException {
        String nip = CommonUtil.userIdLogin();
        List<AbsensiPegawai> result = new ArrayList<AbsensiPegawai>();
        List<AbsensiPegawaiEntity> absensiPegawaiEntityList = null;

        if (bean != null){
            Map hsCriteria = new HashMap();
            if (!"".equalsIgnoreCase(bean.getAbsensiPegawaiId())){
                hsCriteria.put("absensi_pegawai_id", bean.getAbsensiPegawaiId());
            }
            if (!"".equalsIgnoreCase(bean.getNip())){
                hsCriteria.put("nip", bean.getNip());
            }
            if (!"".equalsIgnoreCase(nip)){
                hsCriteria.put("nip_atasan", nip);
            }
            hsCriteria.put("flag","Y");
            try {
                absensiPegawaiEntityList = absensiPegawaiDao.getListAbsensiForApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[notifikasiBoImpl.searchAbsensiPegawaiPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list absensi with criteria, please info to your admin..." + e.getMessage());
            }
            List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getListPersonilPosition(nip);

            if (absensiPegawaiEntityList != null){
                AbsensiPegawai addData;
                for (AbsensiPegawaiEntity listData : absensiPegawaiEntityList){
                    addData = new AbsensiPegawai();
                    addData.setAbsensiPegawaiId(listData.getAbsensiPegawaiId());
                    addData.setNip(listData.getNip());
                    addData.setBranchId(listData.getBranchId());
                    addData.setKeterangan(listData.getKeterangan());
                    ImBiodataEntity imBiodataEntity = biodataDao.getById("nip",listData.getNip(),"Y");
                    addData.setNama(imBiodataEntity.getNamaPegawai());
                    for (ItPersonilPositionEntity personilPositionEntity : personilPositionEntityList){
                        ImPosition imPosition = positionDao.getById("positionId",personilPositionEntity.getPositionId());
                        addData.setPosisiId(imPosition.getPositionId());
                        addData.setPositionName(imPosition.getPositionName());
                        addData.setDivisiId(imPosition.getDepartmentId());
                        if (imPosition.getDepartmentId()!=null){
                            ImDepartmentEntity imDepartmentEntity = departmentDao.getById("departmentId",imPosition.getDepartmentId());
                            addData.setDivisi(imDepartmentEntity.getDepartmentName());
                        }
                    }
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    addData.setStTanggal(df.format(listData.getTanggal()));
                    if ("Y".equalsIgnoreCase(listData.getApprovalFlag())){
                        addData.setAbsensiApprove(true);
                    }else if ("N".equalsIgnoreCase(listData.getApprovalFlag())){
                        addData.setNotApprove(true);
                    }
                    addData.setApprovalFlag(listData.getApprovalFlag());

                    addData.setAction(listData.getAction());
                    addData.setCreatedDate(listData.getCreatedDate());
                    addData.setCreatedWho(listData.getCreatedWho());
                    addData.setFlag(listData.getFlag());
                    addData.setLastUpdate(listData.getLastUpdate());
                    addData.setLastUpdateWho(listData.getLastUpdateWho());

                    result.add(addData);
                }
            }
        }
        return result;
    }
    @Override
    public List<Object[]> findAllNotifByNip(String nip, String typeNotifId) throws GeneralBOException {
        List<Object[]> listCuti = null;

        try {
            listCuti = notifikasiDao.findByNipAndTypeNotif(nip, typeNotifId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.findAllNotifByNip] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        return listCuti;
    }

    //Digunakan untuk mengambil bawahan jabatan
    private void getBawahanJabatan(String branchId, String posisiId){
        List<ImStrukturJabatanEntity> strukturJabatans = null;
        strukturJabatans = strukturJabatanDao.getIdStrukturJabatanAtasan(branchId, posisiId);
        String idParent = "";
        String []uraian ;

        if(strukturJabatans.size() > 0){
            for(ImStrukturJabatanEntity strukturJabatanEntity : strukturJabatans){
                idParent = strukturJabatanEntity.getStrukturJabatanId();
                uraian = strukturJabatanEntity.getPositionName().split(" ");
                this.hasilUraian = uraian[0] + " " +uraian[1];
            }
        }
        strukturJabatanList.clear();
        getListStruktur(idParent);
    }


    private String getListStruktur(String id){
        //List<StrukturJabatan> strukturJabatans = new ArrayList();
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities = null;
        String hasil = "";
        String []posistionName ;
        imStrukturJabatanEntities = strukturJabatanDao.getIdStrukturJabatan(id);
        if(imStrukturJabatanEntities.size() > 0){
            for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){
                StrukturJabatan strukturJabatan1 = new StrukturJabatan();
                strukturJabatan1.setNip(imStrukturJabatanEntity.getNip());
                strukturJabatan1.setPositionName(imStrukturJabatanEntity.getPositionName());
                strukturJabatan1.setUraian(hasilUraian);

                hasil = imStrukturJabatanEntity.getStrukturJabatanId();
                strukturJabatanList.add(strukturJabatan1);
//                getListStruktur(getListStruktur(hasil));
            }
        }
        return hasil;
    }

    private void getBawahanJabatanTraining(String branchId, String posisiId){
        List<ImStrukturJabatanEntity> strukturJabatans = null;
        strukturJabatans = strukturJabatanDao.getIdStrukturJabatanAtasan(branchId, posisiId);
        String idParent = "";
        String []uraian ;

        if(strukturJabatans.size() > 0){
            for(ImStrukturJabatanEntity strukturJabatanEntity : strukturJabatans){
                idParent = strukturJabatanEntity.getStrukturJabatanId();
                uraian = strukturJabatanEntity.getPositionName().split(" ");
                this.hasilUraian = uraian[0] + " " +uraian[1];
            }
        }
        strukturJabatanList.clear();
        getListStrukturTraining(idParent);
    }
    private String getListStrukturTraining(String id){
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities = null;
        List<ImStrukturJabatanEntity> resultStruktur = new ArrayList<>();
        String strukturJabatanId=id;
        String strukturJabatanTmp="";
        boolean finish=false;
        String kelompokId="";
        int batas=1;
        do {
            imStrukturJabatanEntities = strukturJabatanDao.getIdStrukturJabatan(strukturJabatanId);
            if(imStrukturJabatanEntities.size() > 0){
                for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){
                    StrukturJabatan strukturJabatan1 = new StrukturJabatan();
                    strukturJabatan1.setNip(imStrukturJabatanEntity.getNip());
                    strukturJabatan1.setPositionName(imStrukturJabatanEntity.getPositionName());
                    strukturJabatan1.setUraian(hasilUraian);

                    strukturJabatanId = imStrukturJabatanEntity.getStrukturJabatanId();
                    resultStruktur.add(imStrukturJabatanEntity);
                }
            }
            batas++;
            if (strukturJabatanId.equalsIgnoreCase(strukturJabatanTmp)){
                finish=true;
            }
            strukturJabatanTmp=strukturJabatanId;
        }
        while (batas<4||finish);
        for (ImStrukturJabatanEntity result : resultStruktur){
            StrukturJabatan strukturJabatan1 = new StrukturJabatan();
            strukturJabatan1.setNip(result.getNip());
            strukturJabatan1.setPositionName(result.getPositionName());
            strukturJabatan1.setUraian(hasilUraian);
            strukturJabatanId = result.getStrukturJabatanId();
            strukturJabatanList.add(strukturJabatan1);
        }
        return strukturJabatanId;
    }
    @Override
    public List<Object[]> findAllNotifTypeNotif(String nip, String typeNotifId) throws GeneralBOException {
        List<Object[]> listCuti = null;

        try {
            listCuti = notifikasiDao.findIjinByNipAndTypeNotif(nip, typeNotifId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.findAllNotifTypeNotif] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        return listCuti;
    }

    @Override
    public List<Object[]> findAllNotifSppd(String nip, String typeNotifId) throws GeneralBOException {
        List<Object[]> listSppd = null;

        try {
            listSppd = notifikasiDao.findSppdByNipAndTypeNotif(nip, typeNotifId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.findAllNotifSppd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        return listSppd;
    }

    @Override
    public List<Object[]> findAllNotifTraining(String nip, String typeNotifId) throws GeneralBOException {
        List<Object[]> listSppd = null;

        try {
            listSppd = notifikasiDao.findTrainingByNipAndTypeNotif(nip, typeNotifId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.findAllNotifTraining] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        return listSppd;
    }

    @Override
    public List<Object[]> findAllNotifLembur(String nip, String typeNotifId) throws GeneralBOException {
        List<Object[]> daftarlembur = null;

        try {
            daftarlembur = notifikasiDao.findLemburByNipAndTypeNotif(nip, typeNotifId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.findAllNotifTraining] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        return daftarlembur;
    }

    @Override
    public List<Object[]> findNotifByTypeNotif(String nip, String typeNotifId) throws GeneralBOException {
        List<Object[]> daftarNotifUmum = null;

        try {
            daftarNotifUmum = notifikasiDao.findNotifikasiUmum(nip, typeNotifId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.findAllNotifTraining] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        return daftarNotifUmum;
    }

    @Override
    public List<Object[]> updateNotifikasiFlag(String nip, String typeNotifId, String notifId) throws GeneralBOException {
//        logger.info("NotifikasiBoImpl.saveEditMobile start process >>>");
//        List<Object[]> daftarNotifUmum = null;
//
//        if (notifId != null){
//            ImNotifikasiEntity imNotifikasiEntity = null;
//            try{
//                imNotifikasiEntity = notifikasiDao.getById("notifId", notifId);
//            }catch (HibernateException e){
//                logger.error("[NotifikasiBoImpl.updateNotifikasiFlag] Error, "+e.getMessage());
//                throw new GeneralBOException("Found problem when searching data Notifikasi by Notifikasi ID, please inform to your admin...," + e.getMessage());
//            }
//
//            if (imNotifikasiEntity != null){
//                imNotifikasiEntity.setNotifId(notifId);
//                imNotifikasiEntity.setRead("N");
//                imNotifikasiEntity.setAction("U");
//
//                try{
//                    notifikasiDao.updateAndSave(imNotifikasiEntity);
//                }catch (HibernateException e){
//                    logger.error("[NotifikasiBoImpl.saveEdit] Error, " + e.getMessage());
//                    throw new GeneralBOException("Found problem when saving update data Notifikasi, please info to your admin..." + e.getMessage());
//                }
//            }else {
//                logger.error("[NotifikasiBoImpl.saveEdit] Error, not found data Notifikasi with request id, please check again your data ...");
//                throw new GeneralBOException("Error, not found data Notifikasi with request id, please check again your data ...");
//            }
//
//            try {
//                typeNotifId = "umum";
//                daftarNotifUmum = notifikasiDao.findNotifikasiUmum(nip, typeNotifId);
//            } catch (HibernateException e) {
//                logger.error("[UserBoImpl.findAllNotifTraining] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
//            }
//        }
//
//        logger.info("[NotifikasiBoImpl.saveEditMobile] end process <<<");
        return null;
    }

    @Override
    public List<Notifikasi> getCutiPensiun(){
        List<ImBiodataEntity> biodataEntityList = new ArrayList<>();
        List<Notifikasi> notifikasiList = new ArrayList<>();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date tanggalSekarang = new java.sql.Date(utilDate.getTime());
        String stTanggalSekarang= CommonUtil.convertDateToString(tanggalSekarang);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggalSekarang);
        cal.add(Calendar.MONTH, 6);
        Date bulanke6 = cal.getTime();

        java.sql.Date tanggalBulan6 = new java.sql.Date(bulanke6.getTime());
        biodataEntityList = biodataDao.getPegawaiPensiun6bulan(tanggalSekarang,tanggalBulan6);

        if(biodataEntityList.size() > 0){
            for(ImBiodataEntity biodataEntity : biodataEntityList){
                Notifikasi notifikasi = new Notifikasi();
                notifikasi.setNip(biodataEntity.getNip());
                notifikasi.setNamaPegawai(biodataEntity.getNamaPegawai());
                notifikasi.setJmlApproval(String.valueOf(biodataEntityList.size()));
                notifikasi.setStTanggalPensiun(CommonUtil.convertDateToString(biodataEntity.getTanggalPensiun()));
                int selisih=0;
                try {
                    selisih= CommonUtil.countAllDays(stTanggalSekarang,notifikasi.getStTanggalPensiun());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (selisih/30>0){
                    selisih=selisih/30;
                    notifikasi.setStSelisih("("+selisih+" bulan)");
                }else{
                    notifikasi.setStSelisih("("+selisih+" hari)");
                }
                notifikasiList.add(notifikasi);
            }
        }
        return notifikasiList;
    }


    public List<Notifikasi> getJubilium(){
        List<ImBiodataEntity> biodataEntityList = new ArrayList<>();
        List<Notifikasi> notifikasiList = new ArrayList<>();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date tanggalSekarang = new java.sql.Date(utilDate.getTime());
        String stTanggalSekarang= CommonUtil.convertDateToString(tanggalSekarang);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggalSekarang);
        cal.add(Calendar.MONTH, 12);
        Date bulanke6 = cal.getTime();
        java.sql.Date tanggalBulan6 = new java.sql.Date(bulanke6.getTime());



        String stTSekarang = tanggalSekarang.toString();
        String stTBulan6 = tanggalBulan6.toString();

        String[] arrTSekarang = stTSekarang.split("-");
        String[] arrTBulan6 = stTBulan6.split("-");

        int tahunAwal = Integer.parseInt(arrTSekarang[0]) - 25;
        int tahunBulan6 = Integer.parseInt(arrTBulan6[0]) - 25;

        String finalTSekarang = String.valueOf(tahunAwal)+"-"+arrTSekarang[1]+"-"+arrTSekarang[2];
        String finalTBulan6 = String.valueOf(tahunBulan6)+"-"+arrTBulan6[1]+"-"+arrTBulan6[2];


        biodataEntityList = biodataDao.getPegawaiJubilium6bulan(finalTSekarang,finalTBulan6);

        if(biodataEntityList.size() > 0){
            for(ImBiodataEntity biodataEntity : biodataEntityList){
                Notifikasi notifikasi = new Notifikasi();
                notifikasi.setNip(biodataEntity.getNip());
                notifikasi.setNamaPegawai(biodataEntity.getNamaPegawai());
                notifikasi.setJmlApproval(String.valueOf(biodataEntityList.size()));
                notifikasi.setStTanggalAktif(CommonUtil.convertDateToString(biodataEntity.getTanggalAktif()));
                String[] arrtglAktif = notifikasi.getStTanggalAktif().split("-");
                String finalTGlJuilium = arrtglAktif[0]+"-"+arrtglAktif[1]+"-"+arrTSekarang[0];
                notifikasi.setStTglJubilium(finalTGlJuilium);
                int selisih=0;
                try {
                    selisih= CommonUtil.countAllDays(stTanggalSekarang,finalTGlJuilium);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (selisih/30>0){
                    selisih=selisih/30;
                    notifikasi.setStSelisih("("+selisih+" bulan)");
                }else{
                    notifikasi.setStSelisih("("+selisih+" hari)");
                }
                notifikasiList.add(notifikasi);
            }
        }
        return notifikasiList;
    }


    @Override
    public List<Notifikasi> getCutiTahunan(){
        List<ImBiodataEntity> biodataEntityList = new ArrayList<>();
        List<Notifikasi> notifikasiList = new ArrayList<>();

        biodataEntityList = biodataDao.getPegawaiCutiTahunan();

        if(biodataEntityList.size() > 0){
            for(ImBiodataEntity biodataEntity : biodataEntityList){
                Notifikasi notifikasi = new Notifikasi();
                notifikasi.setNip(biodataEntity.getNip());
                notifikasi.setNamaPegawai(biodataEntity.getNamaPegawai());
                notifikasi.setJmlApproval(String.valueOf(biodataEntityList.size()));
                notifikasi.setStTanggalAktif(CommonUtil.convertDateToString(biodataEntity.getTanggalAktif()));

                notifikasiList.add(notifikasi);
            }
        }
        return notifikasiList;
    }

    @Override
    public List<Notifikasi> getPengajuanBiayaMenggantung(String branchId){
        List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList= new ArrayList<>();
        List<Notifikasi> notifikasiList = new ArrayList<>();

        pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getPengajuanBiayaMenggantung(branchId);

        if(pengajuanBiayaDetailEntityList.size() > 0){
            for(ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : pengajuanBiayaDetailEntityList){
                Notifikasi notifikasi = new Notifikasi();
                notifikasi.setPengajuanBiayaDetailId(pengajuanBiayaDetailEntity.getPengajuanBiayaDetailId());
                notifikasi.setStTanggalRealisasi(CommonUtil.convertDateToString(pengajuanBiayaDetailEntity.getTanggalRealisasi()));
                notifikasi.setKeperluan(pengajuanBiayaDetailEntity.getKeperluan());
                notifikasi.setDivisiName(pengajuanBiayaDetailEntity.getDivisiId());
                notifikasi.setJmlApproval(String.valueOf(pengajuanBiayaDetailEntityList.size()));
                notifikasiList.add(notifikasi);
            }
        }
        return notifikasiList;
    }

    @Override
    public List<Notifikasi> getTerimaRkPengajuanBiaya(String branchId){
        List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList= new ArrayList<>();
        List<Notifikasi> notifikasiList = new ArrayList<>();

        pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getTerimaRkPengajuanBiaya(branchId);

        if(pengajuanBiayaDetailEntityList.size() > 0){
            for(ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : pengajuanBiayaDetailEntityList){
                Notifikasi notifikasi = new Notifikasi();
                notifikasi.setRkId(pengajuanBiayaDetailEntity.getRkId());
                notifikasi.setJmlApproval(String.valueOf(pengajuanBiayaDetailEntityList.size()));
                notifikasiList.add(notifikasi);
            }
        }
        return notifikasiList;
    }

    @Override
    public List<Notifikasi> getCutiPanjang() {
        List<ImBiodataEntity> biodataEntityList = new ArrayList<>();
        List<Notifikasi> notifikasiList = new ArrayList<>();

        biodataEntityList = biodataDao.getPegawaiCutiPanjang();

        if(biodataEntityList.size() > 0){
            for(ImBiodataEntity biodataEntity : biodataEntityList){
                Notifikasi notifikasi = new Notifikasi();
                notifikasi.setNip(biodataEntity.getNip());
                notifikasi.setNamaPegawai(biodataEntity.getNamaPegawai());
                notifikasi.setJmlApproval(String.valueOf(biodataEntityList.size()));
                notifikasi.setStTanggalAktif(CommonUtil.convertDateToString(biodataEntity.getTanggalAktif()));

                notifikasiList.add(notifikasi);
            }
        }
        return notifikasiList;
    }

    @Override
    public List<PersonilPosition> daftarAtasanLangsung(Notifikasi bean){
        logger.info("[NotifikasiBoImpl.daftarAtasanLangsung] start process >>>");
        String nip = bean.getNip();
        List<PersonilPosition> listOfResult = new ArrayList<>();
        String branchId=null;

        List<ItPersonilPositionEntity> personilPositionEntityList= new ArrayList<>();

        personilPositionEntityList=personilPositionDao.getListPersonilPosition(nip);

        for (ItPersonilPositionEntity personilPositionEntity:personilPositionEntityList){
            branchId=personilPositionEntity.getBranchId();
        }
        //Coba Reza

//        List<Biodata> biodataList2 = new ArrayList();
//
//        try{
//            biodataList2 = biodataDao.searchBiodataByCriteria(nip);
//        }catch (HibernateException e) {
//            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//        }
//        if (biodataList2 != null){
//            for (Biodata biodata2 : biodataList2){
//                String positionId = biodata2.getPositionPltId();
//
//                Map hsCriteriaMap2 = new HashMap();
//                hsCriteriaMap2.put("branch_id", branchId);
//                hsCriteriaMap2.put("position_id", positionId);
//                hsCriteriaMap2.put("flag", "Y");
//                List<ItPersonilPositionEntity> itPersonilPositionEntities2 = null;
//                try {
//                    itPersonilPositionEntities2 = personilPositionDao.getByCriteria(hsCriteriaMap2);
//                } catch (HibernateException e) {
//                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                }
//
//                if (itPersonilPositionEntities2 != null){
//                    if (itPersonilPositionEntities2.size() != 0){
//                        for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities2) {
//                            PersonilPosition personilPosition = new PersonilPosition();
//                            personilPosition.setNip(listPersonilPosition.getNip());
//                            listOfResult.add(personilPosition);
//                        }
//                    }else {
//
//                        try {
//                            strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(nip,branchId);
//                        } catch (HibernateException e) {
//                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                        }
//
//                        for (StrukturJabatan strukturJabatan:strukturJabatanList) {
//                            // Search Leader
//                            if (strukturJabatan != null) {
//                                String[] parts = strukturJabatan.getParentId().split("-");
//                                String parent = parts[0];
//
//                                if (parent != null) {
//                                    // search data postion_id from struktur jabatan by parameter parent
//                                    Map hsCriteria = new HashMap();
//                                    hsCriteria.put("branch_id", branchId);
//                                    hsCriteria.put("struktur_jabatan_id", parent);
//                                    hsCriteria.put("flag", "Y");
//                                    List<ImStrukturJabatanEntity> strukturJabatanEntities = null;
//                                    try {
//                                        strukturJabatanEntities = strukturJabatanDao.getByCriteria(hsCriteria);
//                                    } catch (HibernateException e) {
//                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                    }
//
//                                    if (strukturJabatanEntities != null) {
//                                        for (ImStrukturJabatanEntity listStruktur : strukturJabatanEntities) {
//
//                                            // search data nip from personil by parameter position_id from struktur jabatan
//                                            String stPosition = "";
//                                            if (listStruktur.getPositionId() != null) {
//                                                stPosition = String.valueOf(listStruktur.getPositionId());
//                                            }
//                                            hsCriteria = new HashMap();
//                                            hsCriteria.put("branch_id", branchId);
//                                            hsCriteria.put("position_id", stPosition);
//                                            hsCriteria.put("flag", "Y");
//                                            List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
//                                            try {
//
//                                                itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
//                                            } catch (HibernateException e) {
//                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                            }
//
//                                            if (itPersonilPositionEntities.size() == 0){
//                                                List<Biodata> biodataList1 = new ArrayList();
//
//                                                try{
//                                                    biodataList1 = biodataDao.searchBiodataUser(stPosition);
//                                                }catch (HibernateException e) {
//                                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                }
//
//                                                if (biodataList1 != null){
//                                                    for (Biodata biodata : biodataList1){
//                                                        String nipUser = biodata.getNip();
////                                                        String positionId = biodata.getPositionPltId();
//
//                                                        Map hsCriteriaMap1 = new HashMap();
//                                                        hsCriteriaMap1 = new HashMap();
//                                                        hsCriteriaMap1.put("nip", nipUser);
//                                                        hsCriteriaMap1.put("branch_id", branchId);
//                                                        hsCriteriaMap1.put("flag", "Y");
//
//                                                        try {
//                                                            itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap1);
//                                                        } catch (HibernateException e) {
//                                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                        }
//                                                    }
//
//                                                    //coba reza
//                                                    while (itPersonilPositionEntities.size() == 0){
//                                                        try {
//                                                            strukturJabatanList = strukturJabatanDao.searchStrukturRelationUser(parent, branchId);
//                                                        } catch (HibernateException e) {
//                                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                        }
//
//                                                        if (strukturJabatanList != null){
//                                                            parent = strukturJabatanList.get(0).getParentId();
//
//                                                            Map hsCriteriaMap = new HashMap();
//                                                            hsCriteriaMap.put("branch_id", branchId);
//                                                            hsCriteriaMap.put("struktur_jabatan_id", parent);
//                                                            hsCriteriaMap.put("flag", "Y");
//                                                            List<ImStrukturJabatanEntity> strukturJabatanEntity = null;
//                                                            try {
//                                                                strukturJabatanEntity = strukturJabatanDao.getByCriteria(hsCriteriaMap);
//                                                            } catch (HibernateException e) {
//                                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                            }
//
//                                                            if (strukturJabatanEntity != null){
//                                                                for (ImStrukturJabatanEntity entity : strukturJabatanEntity){
//                                                                    // search data nip from personil by parameter position_id from struktur jabatan
//                                                                    String stPositionId = "";
//                                                                    if (entity.getPositionId() != null) {
//                                                                        stPositionId = String.valueOf(entity.getPositionId());
//                                                                    }
//                                                                    hsCriteriaMap = new HashMap();
//                                                                    hsCriteriaMap.put("branch_id", branchId);
//                                                                    hsCriteriaMap.put("position_id", stPositionId);
//                                                                    hsCriteriaMap.put("flag", "Y");
////                                            List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
//                                                                    try {
//                                                                        itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
//                                                                    } catch (HibernateException e) {
//                                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                                    }
//
//                                                                    if (itPersonilPositionEntities.size() == 0){
//                                                                        List<Biodata> biodataList = new ArrayList();
//
//                                                                        try{
//                                                                            biodataList = biodataDao.searchBiodataUser(stPositionId);
//                                                                        }catch (HibernateException e) {
//                                                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                                        }
//
//                                                                        if (biodataList != null){
//                                                                            for (Biodata biodata : biodataList){
//                                                                                String nipUser = biodata.getNip();
////                                                                                String positionId = biodata.getPositionPltId();
//
//                                                                                hsCriteriaMap = new HashMap();
//                                                                                hsCriteriaMap.put("nip", nipUser);
//                                                                                hsCriteriaMap.put("branch_id", branchId);
//                                                                                hsCriteriaMap.put("flag", "Y");
//
//                                                                                try {
//                                                                                    itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
//                                                                                } catch (HibernateException e) {
//                                                                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//
//                                            for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities) {
//                                                PersonilPosition personilPosition = new PersonilPosition();
//                                                personilPosition.setNip(listPersonilPosition.getNip());
//                                                listOfResult.add(personilPosition);
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//
//                    }
//                }
//            }
//        }else {
//
//            try {
//                strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(nip,branchId);
//            } catch (HibernateException e) {
//                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//            }
//
//            for (StrukturJabatan strukturJabatan:strukturJabatanList) {
//                // Search Leader
//                if (strukturJabatan != null) {
//                    String[] parts = strukturJabatan.getParentId().split("-");
//                    String parent = parts[0];
//
//                    if (parent != null) {
//                        // search data postion_id from struktur jabatan by parameter parent
//                        Map hsCriteria = new HashMap();
//                        hsCriteria.put("branch_id", branchId);
//                        hsCriteria.put("struktur_jabatan_id", parent);
//                        hsCriteria.put("flag", "Y");
//                        List<ImStrukturJabatanEntity> strukturJabatanEntities = null;
//                        try {
//                            strukturJabatanEntities = strukturJabatanDao.getByCriteria(hsCriteria);
//                        } catch (HibernateException e) {
//                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                        }
//
//                        if (strukturJabatanEntities != null) {
//                            for (ImStrukturJabatanEntity listStruktur : strukturJabatanEntities) {
//
//                                // search data nip from personil by parameter position_id from struktur jabatan
//                                String stPosition = "";
//                                if (listStruktur.getPositionId() != null) {
//                                    stPosition = String.valueOf(listStruktur.getPositionId());
//                                }
//                                hsCriteria = new HashMap();
//                                hsCriteria.put("branch_id", branchId);
//                                hsCriteria.put("position_id", stPosition);
//                                hsCriteria.put("flag", "Y");
//                                List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
//                                try {
//
//                                    itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
//                                } catch (HibernateException e) {
//                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                }
//
//                                if (itPersonilPositionEntities.size() == 0){
//                                    List<Biodata> biodataList1 = new ArrayList();
//
//                                    try{
//                                        biodataList1 = biodataDao.searchBiodataUser(stPosition);
//                                    }catch (HibernateException e) {
//                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                    }
//
//                                    if (biodataList1 != null){
//                                        for (Biodata biodata : biodataList1){
//                                            String nipUser = biodata.getNip();
//                                            String positionId = biodata.getPositionPltId();
//
//                                            Map hsCriteriaMap1 = new HashMap();
//                                            hsCriteriaMap1 = new HashMap();
//                                            hsCriteriaMap1.put("nip", nipUser);
//                                            hsCriteriaMap1.put("branch_id", branchId);
//                                            hsCriteriaMap1.put("flag", "Y");
//
//                                            try {
//                                                itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap1);
//                                            } catch (HibernateException e) {
//                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                            }
//                                        }
//
//                                        //coba reza
//                                        while (itPersonilPositionEntities.size() == 0){
//                                            try {
//                                                strukturJabatanList = strukturJabatanDao.searchStrukturRelationUser(parent, branchId);
//                                            } catch (HibernateException e) {
//                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                            }
//
//                                            if (strukturJabatanList != null){
//                                                parent = strukturJabatanList.get(0).getParentId();
//
//                                                Map hsCriteriaMap = new HashMap();
//                                                hsCriteriaMap.put("branch_id", branchId);
//                                                hsCriteriaMap.put("struktur_jabatan_id", parent);
//                                                hsCriteriaMap.put("flag", "Y");
//                                                List<ImStrukturJabatanEntity> strukturJabatanEntity = null;
//                                                try {
//                                                    strukturJabatanEntity = strukturJabatanDao.getByCriteria(hsCriteriaMap);
//                                                } catch (HibernateException e) {
//                                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                }
//
//                                                if (strukturJabatanEntity != null){
//                                                    for (ImStrukturJabatanEntity entity : strukturJabatanEntity){
//                                                        // search data nip from personil by parameter position_id from struktur jabatan
//                                                        String stPositionId = "";
//                                                        if (entity.getPositionId() != null) {
//                                                            stPositionId = String.valueOf(entity.getPositionId());
//                                                        }
//                                                        hsCriteriaMap = new HashMap();
//                                                        hsCriteriaMap.put("branch_id", branchId);
//                                                        hsCriteriaMap.put("position_id", stPositionId);
//                                                        hsCriteriaMap.put("flag", "Y");
////                                            List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
//                                                        try {
//                                                            itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
//                                                        } catch (HibernateException e) {
//                                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                        }
//
//                                                        if (itPersonilPositionEntities.size() == 0){
//                                                            List<Biodata> biodataList = new ArrayList();
//
//                                                            try{
//                                                                biodataList = biodataDao.searchBiodataUser(stPositionId);
//                                                            }catch (HibernateException e) {
//                                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                            }
//
//                                                            if (biodataList != null){
//                                                                for (Biodata biodata : biodataList){
//                                                                    String nipUser = biodata.getNip();
//                                                                    String positionId = biodata.getPositionPltId();
//
//                                                                    hsCriteriaMap = new HashMap();
//                                                                    hsCriteriaMap.put("nip", nipUser);
//                                                                    hsCriteriaMap.put("branch_id", branchId);
//                                                                    hsCriteriaMap.put("flag", "Y");
//
//                                                                    try {
//                                                                        itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
//                                                                    } catch (HibernateException e) {
//                                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                                    }
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//
//                                for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities) {
//                                    PersonilPosition personilPosition = new PersonilPosition();
//                                    personilPosition.setNip(listPersonilPosition.getNip());
//                                    listOfResult.add(personilPosition);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
        //Batas

        List<Biodata> biodataList2 = new ArrayList();
        try{
            biodataList2 = biodataDao.searchBiodataByCriteria(nip);
        }catch (HibernateException e) {
            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (biodataList2 != null){
            for (Biodata biodata2 : biodataList2){
                String positionPlt = biodata2.getPositionPltId();
                if (positionPlt != null && !"".equalsIgnoreCase(positionPlt)&& "umum".equalsIgnoreCase(bean.getTipeNotifId())){
                    strukturJabatanList = strukturJabatanDao.searchStruktur(positionPlt, branchId);
                    for (StrukturJabatan strukturJabatan : strukturJabatanList){
                        // Search Leader
                        if (strukturJabatan != null) {
                            String[] parts = strukturJabatan.getParentId().split("-");
                            String parent = parts[0];

                            if (parent != null) {
                                // search data postion_id from struktur jabatan by parameter parent
                                Map hsCriteria = new HashMap();
                                hsCriteria.put("branch_id", branchId);
                                hsCriteria.put("struktur_jabatan_id", parent);
                                hsCriteria.put("flag", "Y");
                                List<ImStrukturJabatanEntity> strukturJabatanEntities = null;
                                try {
                                    strukturJabatanEntities = strukturJabatanDao.getByCriteria(hsCriteria);
                                } catch (HibernateException e) {
                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                }

                                if (strukturJabatanEntities != null) {
                                    for (ImStrukturJabatanEntity listStruktur : strukturJabatanEntities) {

                                        // search data nip from personil by parameter position_id from struktur jabatan
                                        String stPosition = "";
                                        if (listStruktur.getPositionId() != null) {
                                            stPosition = String.valueOf(listStruktur.getPositionId());
                                        }
                                        hsCriteria = new HashMap();
                                        hsCriteria.put("branch_id", branchId);
                                        hsCriteria.put("position_id", stPosition);
                                        hsCriteria.put("flag", "Y");
                                        List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
                                        try {

                                            itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
                                        } catch (HibernateException e) {
                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }

                                        if (itPersonilPositionEntities.size() == 0){
                                            List<Biodata> biodataList1 = new ArrayList();

                                            try{
                                                biodataList1 = biodataDao.searchBiodataUser(stPosition);
                                            }catch (HibernateException e) {
                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                            }

                                            if (biodataList1 != null){
                                                for (Biodata biodata : biodataList1){
                                                    String nipUser = biodata.getNip();
                                                    String positionId = biodata.getPositionPltId();

                                                    Map hsCriteriaMap1 = new HashMap();
                                                    hsCriteriaMap1 = new HashMap();
                                                    hsCriteriaMap1.put("nip", nipUser);
                                                    hsCriteriaMap1.put("branch_id", branchId);
                                                    hsCriteriaMap1.put("flag", "Y");

                                                    try {
                                                        itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap1);
                                                    } catch (HibernateException e) {
                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                    }
                                                }

                                                //coba reza
                                                while (itPersonilPositionEntities.size() == 0){
                                                    try {
                                                        strukturJabatanList = strukturJabatanDao.searchStrukturRelationUser(parent, branchId);
                                                    } catch (HibernateException e) {
                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                    }

                                                    if (strukturJabatanList != null){
                                                        parent = strukturJabatanList.get(0).getParentId();

                                                        Map hsCriteriaMap = new HashMap();
                                                        hsCriteriaMap.put("branch_id", branchId);
                                                        hsCriteriaMap.put("struktur_jabatan_id", parent);
                                                        hsCriteriaMap.put("flag", "Y");
                                                        List<ImStrukturJabatanEntity> strukturJabatanEntity = null;
                                                        try {
                                                            strukturJabatanEntity = strukturJabatanDao.getByCriteria(hsCriteriaMap);
                                                        } catch (HibernateException e) {
                                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                        }

                                                        if (strukturJabatanEntity != null){
                                                            for (ImStrukturJabatanEntity entity : strukturJabatanEntity){
                                                                // search data nip from personil by parameter position_id from struktur jabatan
                                                                String stPositionId = "";
                                                                if (entity.getPositionId() != null) {
                                                                    stPositionId = String.valueOf(entity.getPositionId());
                                                                }
                                                                hsCriteriaMap = new HashMap();
                                                                hsCriteriaMap.put("branch_id", branchId);
                                                                hsCriteriaMap.put("position_id", stPositionId);
                                                                hsCriteriaMap.put("flag", "Y");
//                                            List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
                                                                try {
                                                                    itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
                                                                } catch (HibernateException e) {
                                                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                                }

                                                                if (itPersonilPositionEntities.size() == 0){
                                                                    List<Biodata> biodataList = new ArrayList();

                                                                    try{
                                                                        biodataList = biodataDao.searchBiodataUser(stPositionId);
                                                                    }catch (HibernateException e) {
                                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                                    }

                                                                    if (biodataList != null){
                                                                        for (Biodata biodata : biodataList){
                                                                            String nipUser = biodata.getNip();
                                                                            String positionId = biodata.getPositionPltId();

                                                                            hsCriteriaMap = new HashMap();
                                                                            hsCriteriaMap.put("nip", nipUser);
                                                                            hsCriteriaMap.put("branch_id", branchId);
                                                                            hsCriteriaMap.put("flag", "Y");

                                                                            try {
                                                                                itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
                                                                            } catch (HibernateException e) {
                                                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
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

                                        for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities) {
                                            PersonilPosition personilPosition = new PersonilPosition();
                                            personilPosition.setNip(listPersonilPosition.getNip());
                                            listOfResult.add(personilPosition);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }else {
                    try {
                        strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(nip,branchId);
                    } catch (HibernateException e) {
                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }

                    for (StrukturJabatan strukturJabatan:strukturJabatanList) {
                        // Search Leader
                        if (strukturJabatan != null) {
                            String[] parts = strukturJabatan.getParentId().split("-");
                            String parent = parts[0];

                            if (parent != null) {
                                // search data postion_id from struktur jabatan by parameter parent
                                Map hsCriteria = new HashMap();
                                hsCriteria.put("branch_id", branchId);
                                hsCriteria.put("struktur_jabatan_id", parent);
                                hsCriteria.put("flag", "Y");
                                List<ImStrukturJabatanEntity> strukturJabatanEntities = null;
                                try {
                                    strukturJabatanEntities = strukturJabatanDao.getByCriteria(hsCriteria);
                                } catch (HibernateException e) {
                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                }

                                if (strukturJabatanEntities != null) {
                                    for (ImStrukturJabatanEntity listStruktur : strukturJabatanEntities) {

                                        // search data nip from personil by parameter position_id from struktur jabatan
                                        String stPosition = "";
                                        if (listStruktur.getPositionId() != null) {
                                            stPosition = String.valueOf(listStruktur.getPositionId());
                                        }
                                        hsCriteria = new HashMap();
                                        hsCriteria.put("branch_id", branchId);
                                        hsCriteria.put("position_id", stPosition);
                                        hsCriteria.put("flag", "Y");
                                        List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
                                        try {

                                            itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
                                        } catch (HibernateException e) {
                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }

                                        if (itPersonilPositionEntities.size() == 0){
                                            List<Biodata> biodataList1 = new ArrayList();

                                            try{
                                                biodataList1 = biodataDao.searchBiodataUser(stPosition);
                                            }catch (HibernateException e) {
                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                            }

                                            if (biodataList1 != null){
                                                for (Biodata biodata : biodataList1){
                                                    String nipUser = biodata.getNip();
                                                    String positionId = biodata.getPositionPltId();

                                                    Map hsCriteriaMap1 = new HashMap();
                                                    hsCriteriaMap1 = new HashMap();
                                                    hsCriteriaMap1.put("nip", nipUser);
                                                    hsCriteriaMap1.put("branch_id", branchId);
                                                    hsCriteriaMap1.put("flag", "Y");

                                                    try {
                                                        itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap1);
                                                    } catch (HibernateException e) {
                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                    }
                                                }

                                                //coba reza
                                                while (itPersonilPositionEntities.size() == 0){
                                                    try {
                                                        strukturJabatanList = strukturJabatanDao.searchStrukturRelationUser(parent, branchId);
                                                    } catch (HibernateException e) {
                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                    }

                                                    if (strukturJabatanList != null){
                                                        parent = strukturJabatanList.get(0).getParentId();

                                                        Map hsCriteriaMap = new HashMap();
                                                        hsCriteriaMap.put("branch_id", branchId);
                                                        hsCriteriaMap.put("struktur_jabatan_id", parent);
                                                        hsCriteriaMap.put("flag", "Y");
                                                        List<ImStrukturJabatanEntity> strukturJabatanEntity = null;
                                                        try {
                                                            strukturJabatanEntity = strukturJabatanDao.getByCriteria(hsCriteriaMap);
                                                        } catch (HibernateException e) {
                                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                        }

                                                        if (strukturJabatanEntity != null){
                                                            for (ImStrukturJabatanEntity entity : strukturJabatanEntity){
                                                                // search data nip from personil by parameter position_id from struktur jabatan
                                                                String stPositionId = "";
                                                                if (entity.getPositionId() != null) {
                                                                    stPositionId = String.valueOf(entity.getPositionId());
                                                                }
                                                                hsCriteriaMap = new HashMap();
                                                                hsCriteriaMap.put("branch_id", branchId);
                                                                hsCriteriaMap.put("position_id", stPositionId);
                                                                hsCriteriaMap.put("flag", "Y");
//                                            List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
                                                                try {
                                                                    itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
                                                                } catch (HibernateException e) {
                                                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                                }

                                                                if (itPersonilPositionEntities.size() == 0){
                                                                    List<Biodata> biodataList = new ArrayList();

                                                                    try{
                                                                        biodataList = biodataDao.searchBiodataUser(stPositionId);
                                                                    }catch (HibernateException e) {
                                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                                    }

                                                                    if (biodataList != null){
                                                                        for (Biodata biodata : biodataList){
                                                                            String nipUser = biodata.getNip();
                                                                            String positionId = biodata.getPositionPltId();

                                                                            hsCriteriaMap = new HashMap();
                                                                            hsCriteriaMap.put("nip", nipUser);
                                                                            hsCriteriaMap.put("branch_id", branchId);
                                                                            hsCriteriaMap.put("flag", "Y");

                                                                            try {
                                                                                itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
                                                                            } catch (HibernateException e) {
                                                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
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

                                        for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities) {
                                            PersonilPosition personilPosition = new PersonilPosition();
                                            personilPosition.setNip(listPersonilPosition.getNip());
                                            listOfResult.add(personilPosition);
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
//Batas

//        try {
//            strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(nip,branchId);
//        } catch (HibernateException e) {
//            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//        }
//
//        for (StrukturJabatan strukturJabatan:strukturJabatanList) {
//            // Search Leader
//            if (strukturJabatan != null) {
//                String[] parts = strukturJabatan.getParentId().split("-");
//                String parent = parts[0];
//
//                if (parent != null) {
//                    // search data postion_id from struktur jabatan by parameter parent
//                    Map hsCriteria = new HashMap();
//                    hsCriteria.put("branch_id", branchId);
//                    hsCriteria.put("struktur_jabatan_id", parent);
//                    hsCriteria.put("flag", "Y");
//                    List<ImStrukturJabatanEntity> strukturJabatanEntities = null;
//                    try {
//                        strukturJabatanEntities = strukturJabatanDao.getByCriteria(hsCriteria);
//                    } catch (HibernateException e) {
//                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                    }
//
//                    if (strukturJabatanEntities != null) {
//                        for (ImStrukturJabatanEntity listStruktur : strukturJabatanEntities) {
//
//                            // search data nip from personil by parameter position_id from struktur jabatan
//                            String stPosition = "";
//                            if (listStruktur.getPositionId() != null) {
//                                stPosition = String.valueOf(listStruktur.getPositionId());
//                            }
//                            hsCriteria = new HashMap();
//                            hsCriteria.put("branch_id", branchId);
//                            hsCriteria.put("position_id", stPosition);
//                            hsCriteria.put("flag", "Y");
//                            List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
//                            try {
//
//                                itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
//                            } catch (HibernateException e) {
//                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                            }
//
//                            if (itPersonilPositionEntities.size() == 0){
//                                List<Biodata> biodataList1 = new ArrayList();
//
//                                try{
//                                    biodataList1 = biodataDao.searchBiodataUser(stPosition);
//                                }catch (HibernateException e) {
//                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                }
//
//                                if (biodataList1 != null){
//                                    for (Biodata biodata : biodataList1){
//                                        String nipUser = biodata.getNip();
//                                        String positionId = biodata.getPositionPltId();
//
//                                        Map hsCriteriaMap1 = new HashMap();
//                                        hsCriteriaMap1 = new HashMap();
//                                        hsCriteriaMap1.put("nip", nipUser);
//                                        hsCriteriaMap1.put("branch_id", branchId);
//                                        hsCriteriaMap1.put("flag", "Y");
//
//                                        try {
//                                            itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap1);
//                                        } catch (HibernateException e) {
//                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                        }
//                                    }
//
//                                    //coba reza
//                                    while (itPersonilPositionEntities.size() == 0){
//                                        try {
//                                            strukturJabatanList = strukturJabatanDao.searchStrukturRelationUser(parent, branchId);
//                                        } catch (HibernateException e) {
//                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                        }
//
//                                        if (strukturJabatanList != null){
//                                            parent = strukturJabatanList.get(0).getParentId();
//
//                                            Map hsCriteriaMap = new HashMap();
//                                            hsCriteriaMap.put("branch_id", branchId);
//                                            hsCriteriaMap.put("struktur_jabatan_id", parent);
//                                            hsCriteriaMap.put("flag", "Y");
//                                            List<ImStrukturJabatanEntity> strukturJabatanEntity = null;
//                                            try {
//                                                strukturJabatanEntity = strukturJabatanDao.getByCriteria(hsCriteriaMap);
//                                            } catch (HibernateException e) {
//                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                            }
//
//                                            if (strukturJabatanEntity != null){
//                                                for (ImStrukturJabatanEntity entity : strukturJabatanEntity){
//                                                    // search data nip from personil by parameter position_id from struktur jabatan
//                                                    String stPositionId = "";
//                                                    if (entity.getPositionId() != null) {
//                                                        stPositionId = String.valueOf(entity.getPositionId());
//                                                    }
//                                                    hsCriteriaMap = new HashMap();
//                                                    hsCriteriaMap.put("branch_id", branchId);
//                                                    hsCriteriaMap.put("position_id", stPositionId);
//                                                    hsCriteriaMap.put("flag", "Y");
////                                            List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
//                                                    try {
//                                                        itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
//                                                    } catch (HibernateException e) {
//                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                    }
//
//                                                    if (itPersonilPositionEntities.size() == 0){
//                                                        List<Biodata> biodataList = new ArrayList();
//
//                                                        try{
//                                                            biodataList = biodataDao.searchBiodataUser(stPositionId);
//                                                        }catch (HibernateException e) {
//                                                            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                        }
//
//                                                        if (biodataList != null){
//                                                            for (Biodata biodata : biodataList){
//                                                                String nipUser = biodata.getNip();
//                                                                String positionId = biodata.getPositionPltId();
//
//                                                                hsCriteriaMap = new HashMap();
//                                                                hsCriteriaMap.put("nip", nipUser);
//                                                                hsCriteriaMap.put("branch_id", branchId);
//                                                                hsCriteriaMap.put("flag", "Y");
//
//                                                                try {
//                                                                    itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaMap);
//                                                                } catch (HibernateException e) {
//                                                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                                }
//                                                            }
//                                                        }
//
//                                                    }
//
//                                                }
//                                            }
//                                        }
//                                    }
//
//
//                                }
//                            }
//
//                            for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities) {
//                                PersonilPosition personilPosition = new PersonilPosition();
//                                personilPosition.setNip(listPersonilPosition.getNip());
//                                listOfResult.add(personilPosition);
//                            }
//                        }
//                    }
//                }
//            }
//        }
        logger.info("[NotifikasiBoImpl.daftarAtasanLangsung] end process <<<");
        return listOfResult;
    }

    @Override
    public List<PersonilPosition> daftarKabag(String nip){
        List<PersonilPosition> listOfResult=new ArrayList<>();

        List<StrukturJabatan> strukturJabatanList = new ArrayList<>();
        String branchId = null;
        List<ItPersonilPositionEntity> personilPositionEntityList= new ArrayList<>();

        personilPositionEntityList=personilPositionDao.getListPersonilPosition(nip);

        for (ItPersonilPositionEntity personilPositionEntity:personilPositionEntityList){
            branchId=personilPositionEntity.getBranchId();
        }
        try {
            strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(nip,branchId);
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        for (StrukturJabatan strukturJabatan : strukturJabatanList) {
            // Search Leader
            if (strukturJabatan != null) {
                String[] parts = strukturJabatan.getParentId().split("-");
                String parent = parts[0];
                int batas = 1;
                boolean sudah=true;
                if (parent != null) {
                    //cek apakah parent adalah kabag
                    StrukturJabatan hasilStruktur = new StrukturJabatan();
                    List<StrukturJabatan> resultStruktur = new ArrayList<>();
                    do {
                        try {
                            resultStruktur = strukturJabatanDao.cekKelompok(parent, branchId);
                        } catch (HibernateException e) {
                            logger.error("[NotifikasiBoImpl.daftarKabag] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                        for (StrukturJabatan search : resultStruktur) {
                            hasilStruktur.setPositionKelompokId(search.getPositionKelompokId());
                            hasilStruktur.setNip(search.getNip());
                            hasilStruktur.setParentId(search.getParentId());
                            parent = search.getParentId();
                        }
                        batas++;

                        if (("KL04").equalsIgnoreCase(hasilStruktur.getPositionKelompokId())){
                            sudah=false;
                        }else if (batas==4){
                            sudah=false;
                        }
                    } while (sudah);

                    if (("KL04").equalsIgnoreCase(hasilStruktur.getPositionKelompokId())){
                        PersonilPosition personilPosition = new PersonilPosition();
                        personilPosition.setNip(hasilStruktur.getNip());
                        listOfResult.add(personilPosition);
                    }
                }
            }
        }
        return listOfResult;
    }
    @Override
    public List<PersonilPosition> daftarKabid(String nip){
        List<PersonilPosition> listOfResult=new ArrayList<>();

        List<StrukturJabatan> strukturJabatanList = new ArrayList<>();
        String branchId = null;
        List<ItPersonilPositionEntity> personilPositionEntityList= new ArrayList<>();

        personilPositionEntityList=personilPositionDao.getListPersonilPosition(nip);

        for (ItPersonilPositionEntity personilPositionEntity:personilPositionEntityList){
            branchId=personilPositionEntity.getBranchId();
        }
        try {
            strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(nip,branchId);
        } catch (HibernateException e) {
            logger.error("[NotifikasiBoImpl.daftarKabid] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        for (StrukturJabatan strukturJabatan : strukturJabatanList) {
            // Search Leader
            if (strukturJabatan != null) {
                String[] parts = strukturJabatan.getParentId().split("-");
                String parent = parts[0];
                int batas = 1;
                boolean sudah = true;
                if (parent != null) {
                    //cek apakah parent adalah kabid
                    StrukturJabatan hasilStruktur = new StrukturJabatan();
                    List<StrukturJabatan> resultStruktur = new ArrayList<>();
                    do {
                        try {
                            resultStruktur = strukturJabatanDao.cekKelompok(parent, branchId);
                        } catch (HibernateException e) {
                            logger.error("[NotifikasiBoImpl.daftarKabid] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                        for (StrukturJabatan search : resultStruktur) {
                            hasilStruktur.setPositionKelompokId(search.getPositionKelompokId());
                            hasilStruktur.setNip(search.getNip());
                            hasilStruktur.setParentId(search.getParentId());
                            parent = search.getParentId();
                        }
                        batas++;
                        if (("KL03").equalsIgnoreCase(hasilStruktur.getPositionKelompokId())){
                            sudah=false;
                        }else if (batas == 5){
                            sudah=false;
                        }
                    } while (sudah);
                    if (("KL03").equalsIgnoreCase(hasilStruktur.getPositionKelompokId())){
                        PersonilPosition personilPosition = new PersonilPosition();
                        personilPosition.setNip(hasilStruktur.getNip());
                        listOfResult.add(personilPosition);
                    }
                }
            }
        }
        return listOfResult;
    }

    public List<String> getKabid(String nip){
        List<String> results= new ArrayList<>();
        String result="";
        Long level= Long.valueOf(0);
            try{
                //mengambil posisi pegawai berdasarkan nip
                ItPersonilPositionEntity posisiPegawai = new ItPersonilPositionEntity();
                posisiPegawai = personilPositionDao.getById("nip",nip);

                //mengambil level pegawai dari struktur jabatan berdasarkan posisi dan branch pegawai
                List<ImStrukturJabatanEntity> levelJabatanPegawai = new ArrayList<>();
                levelJabatanPegawai = strukturJabatanDao.getPegawaiLevel(posisiPegawai.getPositionId(),posisiPegawai.getBranchId());
                if (levelJabatanPegawai.size()>0){
                    for (ImStrukturJabatanEntity strukturJabatanLoop: levelJabatanPegawai){
                        level = strukturJabatanLoop.getLevel();
                    }
                }

                //mengambil jabatan berdasarkan level
                List<ImStrukturJabatanEntity> atasan = new ArrayList<>();
                atasan = strukturJabatanDao.get2UpLevel(level-2,posisiPegawai.getBranchId());
                if (atasan.size()>0){
                    for (ImStrukturJabatanEntity strukturJabatanLoop: atasan){
                        result = strukturJabatanLoop.getPositionId();
                        results.add(result);
                    }
                }


            }catch (GeneralBOException e) {
                logger.error("[NotifikasiBoImpl.daftarKabid] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
        return results;
    }

    @Override
    public void sendNotifDitentukan(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho){
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        // Send Notification
        ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
        String idNotif = notifikasiDao.getNextNotifikasiId();
        addNotif.setNotifId(idNotif);
        addNotif.setTipeNotifId(tipeNotifId);
        addNotif.setTipeNotifName(tipeNotifName);
        addNotif.setRead("Y");
        addNotif.setFlag("Y");
        addNotif.setAction("C");
        addNotif.setNip(nip);
        addNotif.setNote(note);
        addNotif.setFromPerson(createdWho);
        addNotif.setNoRequest(id);
        addNotif.setCreatedDate(updateTime);
        addNotif.setCreatedWho(createdWho);
        addNotif.setLastUpdate(updateTime);
        addNotif.setLastUpdateWho(createdWho);

        try {
            notifikasiDao.addAndSave(addNotif);
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        List<ItNotifikasiFcmEntity> notifikasiFcm = null;

        try {
            notifikasiFcm = notifikasiFcmDao.getAll();
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
            if(entity.getUserId().equals(nip)){
                ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), addNotif.getNote(), entity.getOs());
                break;
            }
        }

//        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//            if(entity.getUserId().equals(nip)){
//                FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), addNotif.getNote(), CLICK_IJIN);
//                break;
//            }
//        }
    }

    @Override
    public List<PengajuanBiaya> searchPengajuanBiaya(PengajuanBiaya bean) throws GeneralBOException {
        List<PengajuanBiaya> result = new ArrayList<PengajuanBiaya>();
        String nip = CommonUtil.userIdLogin();

        if (bean != null){
            Map hsCriteria = new HashMap();
            if (!"".equalsIgnoreCase(bean.getPengajuanBiayaId())){
                hsCriteria.put("pengajuan_biaya_id", bean.getPengajuanBiayaId());
            }
            if (!"".equalsIgnoreCase(nip)){
                hsCriteria.put("nip_atasan", CommonUtil.userIdLogin());
            }
            hsCriteria.put("flag","Y");

            try {
                result = pengajuanBiayaDao.getListPengajuanBiayaForApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
            for (PengajuanBiaya pengajuanBiaya : result){
                pengajuanBiaya.setStTanggal(CommonUtil.convertDateToString(pengajuanBiaya.getTanggal()));
                pengajuanBiaya.setStTotalBiaya(CommonUtil.numbericFormat(pengajuanBiaya.getTotalBiaya(),"###,###"));

                List<ImBranches> branchesList = branchDao.getListBranchById(pengajuanBiaya.getBranchId());
                for (ImBranches branches : branchesList){
                    pengajuanBiaya.setBranchName(branches.getBranchName());
                }

                ImPosition position = positionDao.getById("positionId",pengajuanBiaya.getDivisiId());
                pengajuanBiaya.setDivisiName(position.getPositionName());
            }
        }
        return result;
    }
    @Override
    public List<PengajuanBiaya> searchPengajuanBiayaRk(PengajuanBiaya bean) throws GeneralBOException {
        List<PengajuanBiaya> result = new ArrayList<PengajuanBiaya>();
        String nip = CommonUtil.userIdLogin();

        if (bean != null){
            Map hsCriteria = new HashMap();
            if (!"".equalsIgnoreCase(bean.getPengajuanBiayaId())){
                hsCriteria.put("pengajuan_biaya_id", bean.getPengajuanBiayaId());
            }
            if (!"".equalsIgnoreCase(nip)){
                hsCriteria.put("nip_atasan", CommonUtil.userIdLogin());
            }
            hsCriteria.put("flag","Y");

            try {
                result = pengajuanBiayaDao.getListPengajuanBiayaRkForApproval(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
        }
        return result;
    }
}