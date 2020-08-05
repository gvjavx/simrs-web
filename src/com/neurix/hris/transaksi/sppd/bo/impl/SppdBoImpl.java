package com.neurix.hris.transaksi.sppd.bo.impl;

import com.neurix.authorization.company.dao.CompanyDao;
import com.neurix.authorization.company.model.ImCompany;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.user.dao.UserDao;
import com.neurix.authorization.user.model.ImUsers;
import com.neurix.authorization.user.model.User;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.ExpoPushNotif;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.kelompokPosition.dao.KelompokPositionDao;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;
import com.neurix.hris.master.kelompokPosition.model.KelompokPosition;
import com.neurix.hris.master.perjalananDinas.dao.PerjalananDinasDao;
import com.neurix.hris.master.perjalananDinas.model.ImPerjalananDinasEntity;
import com.neurix.hris.master.perjalananDinas.model.ImPerjalananDinasPK;
import com.neurix.hris.master.smkPersenSmkNilai.model.ImSmkPersenSmkNilaiEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.master.transportLokal.dao.TransportLokalDao;
import com.neurix.hris.master.transportLokal.model.ImTransportLokalEntity;
import com.neurix.hris.master.transportLokal.model.TransportLokal;
import com.neurix.hris.transaksi.cutiPegawai.dao.CutiPegawaiDao;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiFcmDao;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.sppd.bo.SppdBo;
import com.neurix.hris.transaksi.notifikasi.model.*;
import com.neurix.hris.transaksi.sppd.dao.*;
import com.neurix.hris.transaksi.sppd.model.*;
import com.neurix.hris.transaksi.training.dao.TrainingDao;
import com.neurix.hris.transaksi.training.dao.TrainingPersonDao;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingEntity;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingPersonEntity;
import com.neurix.hris.transaksi.training.model.Training;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class SppdBoImpl implements SppdBo {
    protected static transient Logger logger = Logger.getLogger(SppdBoImpl.class);
    private CompanyDao companyDao;
    private BiodataDao biodataDao;
    private SppdDao sppdDao;
    private SppdPersonDao sppdPersonDao;
    private CutiPegawaiDao cutiPegawaiDao;
    private SppdTanggalDao sppdTanggalDao;
    private PerjalananDinasDao perjalananDinasDao;
    private TransportLokalDao transportLokalDao;
    private NotifikasiDao notifikasiDao;
    private StrukturJabatanDao strukturJabatanDao;
    private UserDao userDao;
    private DepartmentDao departmentDao;
    private PersonilPositionDao personilPositionDao;
    private SppdDocDao sppdDocDao;
    private SppdRerouteDao sppdRerouteDao ;
    private NotifikasiFcmDao notifikasiFcmDao;
    private PositionDao positionDao;
    private TrainingPersonDao trainingPersonDao;
    private TrainingDao trainingDao;
    private String CLICK_ACTION = "TASK_SPPD";

    public CutiPegawaiDao getCutiPegawaiDao() {
        return cutiPegawaiDao;
    }

    public void setCutiPegawaiDao(CutiPegawaiDao cutiPegawaiDao) {
        this.cutiPegawaiDao = cutiPegawaiDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public TrainingPersonDao getTrainingPersonDao() {
        return trainingPersonDao;
    }

    public void setTrainingPersonDao(TrainingPersonDao trainingPersonDao) {
        this.trainingPersonDao = trainingPersonDao;
    }

    public TrainingDao getTrainingDao() {
        return trainingDao;
    }

    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public SppdTanggalDao getSppdTanggalDao() {
        return sppdTanggalDao;
    }

    public void setSppdTanggalDao(SppdTanggalDao sppdTanggalDao) {
        this.sppdTanggalDao = sppdTanggalDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public void setNotifikasiFcmDao(NotifikasiFcmDao notifikasiFcmDao) {
        this.notifikasiFcmDao = notifikasiFcmDao;
    }

    public TransportLokalDao getTransportLokalDao() {
        return transportLokalDao;
    }

    public void setTransportLokalDao(TransportLokalDao transportLokalDao) {
        this.transportLokalDao = transportLokalDao;
    }

    public PerjalananDinasDao getPerjalananDinasDao() {
        return perjalananDinasDao;
    }

    public void setPerjalananDinasDao(PerjalananDinasDao perjalananDinasDao) {
        this.perjalananDinasDao = perjalananDinasDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public SppdRerouteDao getSppdRerouteDao() {
        return sppdRerouteDao;
    }

    public void setSppdRerouteDao(SppdRerouteDao sppdRerouteDao) {
        this.sppdRerouteDao = sppdRerouteDao;
    }

    public SppdDocDao getSppdDocDao() {
        return sppdDocDao;
    }

    public void setSppdDocDao(SppdDocDao sppdDocDao) {
        this.sppdDocDao = sppdDocDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    public NotifikasiDao getNotifikasiDao() {
        return notifikasiDao;
    }

    public void setNotifikasiDao(NotifikasiDao notifikasiDao) {
        this.notifikasiDao = notifikasiDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SppdBoImpl.logger = logger;
    }

    public SppdDao getSppdDao() {
        return sppdDao;
    }


    public SppdPersonDao getSppdPersonDao() {
        return sppdPersonDao;
    }

    public void setSppdPersonDao(SppdPersonDao sppdPersonDao) {
        this.sppdPersonDao = sppdPersonDao;
    }

    public void setSppdDao(SppdDao sppdDao) {
        this.sppdDao = sppdDao;
    }

    @Override
    public void saveDelete(Sppd bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String sppdId = bean.getSppdId();

            ImSppdEntity imSppdEntity = null;

            try {
                // Get data from database by ID
                imSppdEntity = sppdDao.getById("sppdId", sppdId);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSppdEntity != null) {

                // Modify from bean to entity serializable
                imSppdEntity.setSppdId(bean.getSppdId());
                imSppdEntity.setFlag(bean.getFlag());
                imSppdEntity.setAction(bean.getAction());
                imSppdEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSppdEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    sppdDao.updateAndSave(imSppdEntity);
                } catch (HibernateException e) {
                    logger.error("[SppdBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SppdBoImpl.saveDelete] Error, not found data Sppd with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Sppd with request id, please check again your data ...");
            }
        }
        logger.info("[SppdBoImpl.saveDelete] end process <<<");
    }

    public void saveDeleteDoc(SppdDoc bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String sppdDocId = bean.getDocSppdId();

            ItSppdDocEntity itSppdDocEntity = null;

            try {
                // Get data from database by ID
                itSppdDocEntity = sppdDocDao.getById("docSppdId", sppdDocId);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (itSppdDocEntity != null) {

                // Modify from bean to entity serializable
                itSppdDocEntity.setSppdId(bean.getSppdId());
                itSppdDocEntity.setFlag(bean.getFlag());
                itSppdDocEntity.setAction(bean.getAction());
                itSppdDocEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itSppdDocEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    sppdDocDao.updateAndSave(itSppdDocEntity);
                } catch (HibernateException e) {
                    logger.error("[SppdBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SppdBoImpl.saveDelete] Error, not found data Sppd with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Sppd with request id, please check again your data ...");

            }
        }
        logger.info("[SppdBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Sppd bean) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String historyId = "";
            String sppdId = bean.getSppdId();

            ImSppdEntity imSppdEntity = null;

            try {
                // Get data from database by ID
                imSppdEntity = sppdDao.getById("sppdId", sppdId);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Sppd by Kode Sppd, please inform to your admin...," + e.getMessage());
            }

            if (imSppdEntity != null) {
                String userLogin = CommonUtil.userLogin();
                imSppdEntity.setSppdId(bean.getSppdId());

                imSppdEntity.setFlag(bean.getFlag());
                if (bean.getStTanggalSppdBerangkat() != null && !"".equalsIgnoreCase(bean.getStTanggalSppdBerangkat())) {
                    bean.setTanggalSppdBerangkat(CommonUtil.convertStringToDate(bean.getStTanggalSppdBerangkat()));
                }
                if (bean.getStTanggalSppdKembali() != null && !"".equalsIgnoreCase(bean.getStTanggalSppdKembali())) {
                    bean.setTanggalSppdKembali(CommonUtil.convertStringToDate(bean.getStTanggalSppdKembali()));
                }
                imSppdEntity.setTanggalSppdBerangkat(bean.getTanggalSppdBerangkat());
                imSppdEntity.setTanggalSppdKembali(bean.getTanggalSppdKembali());
                imSppdEntity.setClosed(bean.getClosed());
                imSppdEntity.setTugasSppd(bean.getTugasSppd());
                imSppdEntity.setAction(bean.getAction());
                imSppdEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSppdEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    sppdDao.updateAndSave(imSppdEntity);
                    //session.removeAttribute("listOfResultSppdReroute");
                } catch (HibernateException e) {
                    logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SppdBoImpl.saveEdit] Error, not found data Sppd with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Sppd with request id, please check again your data ...");
//                condition = "Error, not found data Sppd with request id, please check again your data ...";
            }
        }
        logger.info("[SppdBoImpl.saveEdit] end process <<<");
    }

    public void saveEditReroute(SppdReroute bean) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String rerouteId = bean.getSppdRerouteId();

            ItSppdRerouteEntity itSppdRerouteEntity = null;

            try {
                itSppdRerouteEntity = sppdRerouteDao.getById("sppdRerouteId", rerouteId);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Sppd by Kode Sppd, please inform to your admin...," + e.getMessage());
            }

            if (itSppdRerouteEntity != null) {
                String userLogin = CommonUtil.userLogin();
                itSppdRerouteEntity.setKeterangan(bean.getKeterangan());
                itSppdRerouteEntity.setRerouteDari(bean.getRerouteDari());
                itSppdRerouteEntity.setRerouteKe(bean.getRerouteKe());
                itSppdRerouteEntity.setTanggalRerouteDari(bean.getTanggalRerouteDari());
                itSppdRerouteEntity.setTanggalRerouteKe(bean.getTanggalRerouteKe());
                itSppdRerouteEntity.setBerangkatVia(bean.getBerangkatVia());

                itSppdRerouteEntity.setTiketPergi(bean.getTiketPergi());
                itSppdRerouteEntity.setTiketKembali(bean.getTiketKembali());
                itSppdRerouteEntity.setBiayaTambahan(bean.getBiayaTambahan());
                itSppdRerouteEntity.setTransportLokalBerangkat(bean.getBiayaTujuan());
                itSppdRerouteEntity.setTransportTujuan(bean.getBiayaLokal());
                itSppdRerouteEntity.setTransportLokalKembali(bean.getBiayaKembali());

                itSppdRerouteEntity.setFlag(bean.getFlag());
                itSppdRerouteEntity.setAction(bean.getAction());
                itSppdRerouteEntity.setCreatedWho(bean.getCreatedWho());
                itSppdRerouteEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itSppdRerouteEntity.setCreatedDate(bean.getCreatedDate());
                itSppdRerouteEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    sppdRerouteDao.updateAndSave(itSppdRerouteEntity);
                } catch (HibernateException e) {
                    logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SppdBoImpl.saveEdit] Error, not found data Sppd with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Sppd with request id, please check again your data ...");
            }
        }
        logger.info("[SppdBoImpl.saveEdit] end process <<<");
    }

    public void saveAddReroute(SppdReroute bean) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String rerouteId = bean.getSppdRerouteId();

            ItSppdRerouteEntity itSppdRerouteEntity = new ItSppdRerouteEntity();
            String userLogin = CommonUtil.userLogin();

            itSppdRerouteEntity.setSppdRerouteId(sppdRerouteDao.getNextSppdReroute());
            itSppdRerouteEntity.setKeterangan(bean.getKeterangan());
            itSppdRerouteEntity.setRerouteDari(bean.getRerouteDari());
            itSppdRerouteEntity.setRerouteKe(bean.getRerouteKe());
            itSppdRerouteEntity.setSppdId(bean.getSppdId());
            itSppdRerouteEntity.setSppdPersonId(bean.getSppdPersonId());
            itSppdRerouteEntity.setTanggalRerouteDari(bean.getTanggalRerouteDari());
            itSppdRerouteEntity.setTanggalRerouteKe(bean.getTanggalRerouteKe());
            itSppdRerouteEntity.setBerangkatVia(bean.getBerangkatVia());

            itSppdRerouteEntity.setTiketPergi(bean.getTiketPergi());
            itSppdRerouteEntity.setTiketKembali(bean.getTiketKembali());
            itSppdRerouteEntity.setBiayaTambahan(bean.getBiayaTambahan());
            itSppdRerouteEntity.setTransportLokalBerangkat(bean.getBiayaTujuan());
            itSppdRerouteEntity.setTransportTujuan(bean.getBiayaLokal());
            itSppdRerouteEntity.setTransportLokalKembali(bean.getBiayaKembali());

            BigDecimal biayaMakan = new BigDecimal(0);
            BigDecimal biayaLain = new BigDecimal(0);
            List<ItSppdPersonEntity> sppdPersonEntity = sppdPersonDao.getListSppdPersonUsingId(bean.getSppdPersonId());
            if(sppdPersonEntity.size() > 0){
                for(ItSppdPersonEntity itSppdPersonEntity: sppdPersonEntity){
                    biayaMakan = biayaMakan.add(itSppdPersonEntity.getBiayaMakan());
                    biayaLain = biayaLain.add(itSppdPersonEntity.getBiayaLain());
                }
            }

            itSppdRerouteEntity.setTiketPergi(new BigDecimal(0));
            itSppdRerouteEntity.setTiketKembali(new BigDecimal(0));
            itSppdRerouteEntity.setBiayaTambahan(new BigDecimal(0));
            itSppdRerouteEntity.setBiayaMakan(biayaMakan);
            itSppdRerouteEntity.setBiayaLain(biayaLain);
            itSppdRerouteEntity.setTransportLokalBerangkat(new BigDecimal(0));
            itSppdRerouteEntity.setTransportLokalKembali(new BigDecimal(0));
            itSppdRerouteEntity.setTransportTujuan(new BigDecimal(0));

            itSppdRerouteEntity.setFlag(bean.getFlag());
            itSppdRerouteEntity.setAction(bean.getAction());
            itSppdRerouteEntity.setCreatedWho(bean.getCreatedWho());
            itSppdRerouteEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itSppdRerouteEntity.setCreatedDate(bean.getCreatedDate());
            itSppdRerouteEntity.setLastUpdate(bean.getLastUpdate());
            try {
                sppdRerouteDao.addAndSave(itSppdRerouteEntity);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[SppdBoImpl.saveEdit] end process <<<");
    }

    public void saveDeleteReroute(SppdReroute bean) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String rerouteId = bean.getSppdRerouteId();

            ItSppdRerouteEntity itSppdRerouteEntity = null;

            try {
                itSppdRerouteEntity = sppdRerouteDao.getById("sppdRerouteId", rerouteId);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Sppd by Kode Sppd, please inform to your admin...," + e.getMessage());
            }

            if (itSppdRerouteEntity != null) {
                itSppdRerouteEntity.setFlag(bean.getFlag());
                itSppdRerouteEntity.setAction(bean.getAction());
                itSppdRerouteEntity.setCreatedWho(bean.getCreatedWho());
                itSppdRerouteEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itSppdRerouteEntity.setCreatedDate(bean.getCreatedDate());
                itSppdRerouteEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    sppdRerouteDao.updateAndSave(itSppdRerouteEntity);
                } catch (HibernateException e) {
                    logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SppdBoImpl.saveEdit] Error, not found data Sppd with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Sppd with request id, please check again your data ...");
            }
        }
        logger.info("[SppdBoImpl.saveEdit] end process <<<");
    }

    public void saveEdit(SppdDoc bean) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String docSppdId = bean.getDocSppdId();
            ItSppdDocEntity itSppdDocEntity = null;

            try {
                // Get data from database by ID
                itSppdDocEntity = sppdDocDao.getById("docSppdId", docSppdId);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Sppd by Kode Sppd, please inform to your admin...," + e.getMessage());
            }

            if (itSppdDocEntity != null) {
                String userLogin = CommonUtil.userLogin();
                itSppdDocEntity.setSppdId(bean.getSppdId());
                itSppdDocEntity.setNote(bean.getNote());
                itSppdDocEntity.setFileUploadDoc(bean.getFileUploadDoc());

                itSppdDocEntity.setFlag(bean.getFlag());
                itSppdDocEntity.setAction(bean.getAction());
                itSppdDocEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itSppdDocEntity.setLastUpdate(bean.getLastUpdate());

            }
                try {
                    // Update into database
                    sppdDocDao.updateAndSave(itSppdDocEntity);
                } catch (HibernateException e) {
                    logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
                }
            }
        else {
                logger.error("[SppdBoImpl.saveEdit] Error, not found data Sppd with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Sppd with request id, please check again your data ...");
        }
    }

    public void saveEdit(SppdPerson bean) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String nip = bean.getPersonId();
            ImSppdEntity imSppdEntities = null;
            List<ItSppdPersonEntity> itSppdPersonEntities = null;
            Map hsCriteriaPerson    = new HashMap();
            hsCriteriaPerson.put("sppd_id", bean.getSppdId());
            hsCriteriaPerson.put("person_id", bean.getPersonId());

            try {
                // Get data from database by ID
                itSppdPersonEntities = sppdPersonDao.getByCriteria(hsCriteriaPerson);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Sppd by Kode Sppd, please inform to your admin...," + e.getMessage());
            }

            if (itSppdPersonEntities != null) {
                for(ItSppdPersonEntity itSppdPersonEntity : itSppdPersonEntities) {
                    String userLogin = CommonUtil.userLogin();
                    itSppdPersonEntity.setTiketPergi(bean.getTiketPergi());
                    itSppdPersonEntity.setTiketKembali(bean.getTiketKembali());
                    itSppdPersonEntity.setBiayaTambahan(bean.getBiayaTambahan());
                    itSppdPersonEntity.setBiayaLokalBerangkat(bean.getBiayaLokalBerangkat());
                    itSppdPersonEntity.setBiayaLokalKembali(bean.getBiayaLokalKembali());
                    itSppdPersonEntity.setBiayaTujuan(bean.getBiayaTujuan());
                    try {
                        sppdPersonDao.updateAndSave(itSppdPersonEntity);
                    } catch (HibernateException e) {
                        logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }
        else
        {
            logger.error("[SppdBoImpl.saveEdit] Error, not found data Sppd with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data Sppd with request id, please check again your data ...");
        }
    }

    @Override
    public void saveEditBerangkat(SppdPerson bean) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String nip = bean.getPersonId();
            ImSppdEntity imSppdEntities = null;
            List<ItSppdPersonEntity> itSppdPersonEntities = null;

            Map hsCriteriaPerson    = new HashMap();
            hsCriteriaPerson.put("sppd_id", bean.getSppdId());
            hsCriteriaPerson.put("person_id", bean.getPersonId());

            ImSppdEntity imSppdEntity = sppdDao.getById("sppdId", bean.getSppdId());

            try {
                itSppdPersonEntities = sppdPersonDao.getByCriteria(hsCriteriaPerson);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Sppd by Kode Sppd, please inform to your admin...," + e.getMessage());
            }

            if (bean.getStTanggalBerangkatRealisasi() != null && !"".equalsIgnoreCase(bean.getStTanggalBerangkatRealisasi())) {
                bean.setTanggalBerangkatRealisasi(CommonUtil.convertToDate(bean.getStTanggalBerangkatRealisasi()));
            }
            if (bean.getStTanggalKembaliRealisasi() != null && !"".equalsIgnoreCase(bean.getStTanggalKembaliRealisasi())) {
                bean.setTanggalKembaliRealisasi(CommonUtil.convertToDate(bean.getStTanggalKembaliRealisasi()));
            }

            String tanggalBerangkatRealisasi = "";
            if (itSppdPersonEntities != null) {
                for(ItSppdPersonEntity itSppdPersonEntity : itSppdPersonEntities) {
                    String userLogin = CommonUtil.userLogin();
                    tanggalBerangkatRealisasi = CommonUtil.convertDateToString(itSppdPersonEntity.getTanggalBerangkatRealisasi()) ;
                    DateTime tanggalBerangkat = new DateTime(bean.getTanggalBerangkatRealisasi().getTime());
                    DateTime tanggalKembali = new DateTime(bean.getTanggalKembaliRealisasi().getTime());
                    int jumlahHari = Days.daysBetween(tanggalBerangkat, tanggalKembali).getDays();

                    bean.setTanggalBerangkat(itSppdPersonEntity.getTanggalBerangkat());
                    bean.setTanggalKembali(itSppdPersonEntity.getTanggalKembali());

                    //bean.setTujuanKe(itSppdPersonEntity.getTujuanKe());
                    bean.setSppdPersonId(itSppdPersonEntity.getSppdPersonId());

                    bean.setDinas(itSppdPersonEntity.getImSppdEntity().getDinas());
                    bean.setGolonganId(itSppdPersonEntity.getGolonganId());
                    bean.setKelompokId(itSppdPersonEntity.getKelompokId());

                    editSppdTanggal(bean);

                    itSppdPersonEntity.setTanggalBerangkatRealisasi(CommonUtil.convertToDate(bean.getStTanggalBerangkatRealisasi()));
                    itSppdPersonEntity.setTanggalKembaliRealisasi(CommonUtil.convertToDate(bean.getStTanggalKembaliRealisasi()));
                    itSppdPersonEntity.setBerangkatVia(bean.getBerangkatVia());
                    itSppdPersonEntity.setKembaliVia(bean.getKembaliVia());

                    if(bean.getTiketPergi() != null){
                        itSppdPersonEntity.setTiketPergi(bean.getTiketPergi());
                    }else{
                        itSppdPersonEntity.setTiketPergi(BigDecimal.valueOf(0));
                    }
                    if(bean.getTiketKembali() != null){
                        itSppdPersonEntity.setTiketKembali(bean.getTiketKembali());
                    }else{
                        itSppdPersonEntity.setTiketKembali(BigDecimal.valueOf(0));
                    }
                    if(bean.getBiayaTambahan() != null){
                        itSppdPersonEntity.setBiayaTambahan(bean.getBiayaTambahan());
                    }else{
                        itSppdPersonEntity.setBiayaTambahan(BigDecimal.valueOf(0));
                    }

                    itSppdPersonEntity.setBerangkatDari(bean.getBerangkatDari());
                    itSppdPersonEntity.setTujuanKe(bean.getTujuanKe());

                    if(bean.getBiayaLokalBerangkat() != null){
                        itSppdPersonEntity.setBiayaLokalBerangkat(bean.getBiayaLokalBerangkat());
                    }else{
                        itSppdPersonEntity.setBiayaLokalBerangkat(BigDecimal.valueOf(0));
                    }
                    if(bean.getBiayaTujuan() != null){
                        itSppdPersonEntity.setBiayaTujuan(bean.getBiayaTujuan());
                    }else{
                        itSppdPersonEntity.setBiayaTujuan(BigDecimal.valueOf(0));
                    }
                    if(bean.getBiayaLokalKembali() != null){
                        itSppdPersonEntity.setBiayaLokalKembali(bean.getBiayaLokalKembali());
                    }else{
                        itSppdPersonEntity.setBiayaLokalKembali(BigDecimal.valueOf(0));
                    }

                    itSppdPersonEntity.setLastUpdate(bean.getLastUpdate());
                    itSppdPersonEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        sppdPersonDao.updateAndSave(itSppdPersonEntity);
                    } catch (HibernateException e) {
                        logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
                    }

                    if(imSppdEntity.getSppdId() != null){
                        imSppdEntity.setTanggalSppdBerangkat(itSppdPersonEntity.getTanggalBerangkatRealisasi());
                        imSppdEntity.setTanggalSppdKembali(itSppdPersonEntity.getTanggalKembaliRealisasi());

                        imSppdEntity.setLastUpdateWho(userLogin);
                        imSppdEntity.setLastUpdate(bean.getLastUpdate());

                        sppdDao.updateAndSave(imSppdEntity);
                    }
                }
            }
        }
    }

    public void  saveApprove(SppdPerson bean) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String note = "";
            String notePejabat = "";
            List<ItSppdPersonEntity> itSppdPersonEntities = null;
            ImSppdEntity imSppdEntities = null;
            Map hsCriteriaPerson    = new HashMap();
            hsCriteriaPerson.put("sppd_id", bean.getSppdId());
            hsCriteriaPerson.put("person_id", bean.getSppdPersonId());

            try {
                String DATE_FORMAT = "dd-MM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                itSppdPersonEntities = sppdPersonDao.getByCriteria(hsCriteriaPerson);
                imSppdEntities = sppdDao.getById("sppdId", bean.getSppdId());
                ImNotifikasiEntity itNotifEntity = new ImNotifikasiEntity();

                for(ItSppdPersonEntity itSppdPersonEntity : itSppdPersonEntities){
                    if(bean.getTmpApprove().equals("atasan")) {
                        Date berangkat = itSppdPersonEntity.getTanggalBerangkat();
                        Date pulang = itSppdPersonEntity.getTanggalKembali();
                        if (bean.getApprovalFlag().equals("Y")) {
                            itSppdPersonEntity.setApprovalFlag("Y");
                            if(!bean.getPejabatSementara().equals("")){
                                itSppdPersonEntity.setPejabatSementara(bean.getPejabatSementara());
                                notePejabat = "Tolong untuk bisa menggantikan sementara " + itSppdPersonEntity.getPersonName() + " Pada tanggal " +
                                        sdf.format(berangkat) + " s/d " + sdf.format(pulang);
                                kirimNotif("SPPD", bean.getSppdId(), "Approve Atasan SP[PD", bean.getPejabatSementara(), notePejabat, bean.getApprovalId(), bean.getUserNameActive());
                            }
                            note = "SPPD Telah di disetujui oleh atasan anda ";
                        } else {
                            itSppdPersonEntity.setNotApprovalNote(bean.getNotApprovalNote());
                            itSppdPersonEntity.setApprovalFlag("N");
                            note = "SPPD Tidak di disetujui oleh " + bean.getUserNameActive();
                        }

                        kirimNotif("SPPD", bean.getSppdId(), "Approve Atasan SPPD", itSppdPersonEntity.getPersonId(), note, bean.getUserIdActive(), bean.getUserNameActive());
                        itSppdPersonEntity.setNoteAtasanTransport(bean.getNoteAtasanTransport());
                        itSppdPersonEntity.setApprovalId(bean.getUserIdActive());
                        itSppdPersonEntity.setApprovalName(bean.getUserNameActive());
                        itSppdPersonEntity.setApprovalDate(updateTime);
                    }else {
                        if (bean.getApprovalSdmFlag().equals("Y")) {
                            itSppdPersonEntity.setApprovalSdmFlag("Y");
                            note = "SPPD Telah di disetujui oleh Bagian SDM";
                            imSppdEntities.setFlagEdit("Y");
                        } else {
                            itSppdPersonEntity.setNotApprovalSdmNote(bean.getNotApprovalSdmNote());
                            itSppdPersonEntity.setApprovalSdmFlag("N");
                            note = "SPPD Tidak di disetujui oleh Bagian SDM";
                        }
                        itSppdPersonEntity.setApprovalSdmId(bean.getUserIdActive());
                        itSppdPersonEntity.setApprovalSdmName(bean.getUserNameActive());
                        itSppdPersonEntity.setApprovalSdmDate(updateTime);
                        itSppdPersonEntity.setNoteSdmTransport(bean.getNoteAtasanTransport());
                    }
                    sppdDao.updateAndSave(imSppdEntities);
                    sppdPersonDao.updateAndSave(itSppdPersonEntity);

                    kirimNotif("SPPD", bean.getSppdId(), "Approve Atasan SPPD", itSppdPersonEntity.getPersonId(), note, bean.getUserIdActive(), bean.getUserNameActive());
                }
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Sppd by Kode Sppd, please inform to your admin...," + e.getMessage());
            }
        }
        logger.info("[SppdBoImpl.saveEdit] end process <<<");
    }

    private void kirimNotif(String idTipeNotif, String noRequest,String  notifName, String nipTo, String note, String nipFrom, String nameCreatedWho){
        String notifId = notifikasiDao.getNextNotifikasiId();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        ImNotifikasiEntity itNotifEntity = new ImNotifikasiEntity();

        itNotifEntity.setNotifId(notifId);
        itNotifEntity.setTipeNotifId(idTipeNotif);
        itNotifEntity.setTipeNotifName(notifName);
        itNotifEntity.setRead("Y");
        itNotifEntity.setFlag("Y");
        itNotifEntity.setFromPerson(nipFrom);
        itNotifEntity.setNoRequest(noRequest);
        itNotifEntity.setNip(nipTo);
        itNotifEntity.setNote(note);
        itNotifEntity.setAction("C");
        itNotifEntity.setCreatedWho(nameCreatedWho);
        itNotifEntity.setCreatedDate(updateTime);
        itNotifEntity.setLastUpdate(updateTime);
        itNotifEntity.setLastUpdateWho(nameCreatedWho);

        try {
            notifikasiDao.addAndSave(itNotifEntity);
        } catch (HibernateException e) {
            throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
        }
    }

    public void saveEditSdm(Sppd bean) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String sppdId = bean.getSppdId();

            ImSppdEntity imSppdEntity = null;

            try {
                imSppdEntity = sppdDao.getById("sppdId", sppdId);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Sppd by Kode Sppd, please inform to your admin...," + e.getMessage());
            }

            if (imSppdEntity != null) {
                String userLogin = CommonUtil.userLogin();
                imSppdEntity.setSppdId(bean.getSppdId());

                imSppdEntity.setFlag(bean.getFlag());
                imSppdEntity.setAction(bean.getAction());
                imSppdEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSppdEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    sppdDao.updateAndSave(imSppdEntity);
                } catch (HibernateException e) {
                    logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Sppd, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SppdBoImpl.saveEdit] Error, not found data Sppd with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Sppd with request id, please check again your data ...");
//                condition = "Error, not found data Sppd with request id, please check again your data ...";
            }
        }
        logger.info("[SppdBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Sppd saveAdd(Sppd bean) throws GeneralBOException {
        return null;
    }

    BigDecimal biayaTransportTujuan(String tujuan){
        List<ImTransportLokalEntity> imTransportLokalEntities = transportLokalDao.getListTransportLokalLokasiSppd(tujuan);
        BigDecimal hasil = new BigDecimal(0);

        for (ImTransportLokalEntity imTransportLokalEntity : imTransportLokalEntities) {
            hasil = imTransportLokalEntity.getJumlahBiaya();
        }

        return hasil;
    }

    BigDecimal biayaTransportLokal(String transport){
        List<ImTransportLokalEntity> imTransportLokalEntities = null;
        BigDecimal biayaTransport = new BigDecimal(0);

        //Transport Lokal Pesawat
        if(transport.equalsIgnoreCase("Pesawat")){
            imTransportLokalEntities = transportLokalDao.getListTransportLokalSppd("TL06");
            for (ImTransportLokalEntity imTransportLokalEntity : imTransportLokalEntities) {
                biayaTransport = imTransportLokalEntity.getJumlahBiaya().divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
            }
        }else if(transport.equalsIgnoreCase("Bis")){
            imTransportLokalEntities = transportLokalDao.getListTransportLokalSppd("TL07");
            for (ImTransportLokalEntity imTransportLokalEntity : imTransportLokalEntities) {
                biayaTransport = imTransportLokalEntity.getJumlahBiaya().divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
            }
        }else if(transport.equalsIgnoreCase("Kereta Api")){
            imTransportLokalEntities = transportLokalDao.getListTransportLokalSppd("TL08");
            for (ImTransportLokalEntity imTransportLokalEntity : imTransportLokalEntities) {
                biayaTransport = imTransportLokalEntity.getJumlahBiaya().divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
            }
        }

        return biayaTransport;
    }

    Date [] insertSppdTanggal(SppdPerson sppdPerson){
        DateTime tanggalBerangkat = new DateTime();
        DateTime tanggalKembali = new DateTime();
        int jumlahHari = 0;

        tanggalBerangkat = new DateTime(sppdPerson.getTanggalBerangkatRealisasi().getTime());
        tanggalKembali = new DateTime(sppdPerson.getTanggalKembaliRealisasi().getTime());
        jumlahHari = Days.daysBetween(tanggalBerangkat, tanggalKembali).getDays();

        Date[] hasil = new Date[jumlahHari + 1];

        for(int a = 0 ; a <= jumlahHari; a++){
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
            String dtStr = fmt.print(tanggalBerangkat);

            hasil[a] = CommonUtil.convertStringToDate(dtStr);
            tanggalBerangkat = new DateTime(tanggalBerangkat);
            tanggalBerangkat = tanggalBerangkat.plusDays(1);

            String sppdTanggalId = sppdTanggalDao.getNextSppdTanggalId();
            ItSppdTanggalEntity itSppdTanggalEntity = new ItSppdTanggalEntity();

            itSppdTanggalEntity.setIdSppdTanggal(sppdTanggalId);
            itSppdTanggalEntity.setSppdPersonId(sppdPerson.getSppdPersonId());
            itSppdTanggalEntity.setSppdTanggal(hasil[a]);
            itSppdTanggalEntity.setSppdTanggalApprove("Y");

            BigDecimal biayaMakan = BigDecimal.valueOf(0);
            BigDecimal biayaLain = BigDecimal.valueOf(0);

            biayaMakan = getBiayaMakan(sppdPerson.getGolonganId(), sppdPerson.getKelompokId(), sppdPerson.getDinas());
            biayaLain = getBiayaLain(sppdPerson.getGolonganId(), sppdPerson.getKelompokId(), sppdPerson.getDinas());

            itSppdTanggalEntity.setBiayaLain(biayaLain);
            itSppdTanggalEntity.setBiayaMakan(biayaMakan);


            itSppdTanggalEntity.setFlag("Y");
            itSppdTanggalEntity.setAction("C");
            itSppdTanggalEntity.setCreatedWho(CommonUtil.userLogin());
            itSppdTanggalEntity.setLastUpdateWho(CommonUtil.userLogin());
            itSppdTanggalEntity.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            itSppdTanggalEntity.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

            try {
                sppdTanggalDao.addAndSave(itSppdTanggalEntity);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.getSearchSppdByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
        }
        return hasil;
    }

    Date [] editSppdTanggal(SppdPerson sppdPerson){
        DateTime tanggalBerangkatUpdate = new DateTime();
        DateTime tanggalKembaliUpdate = new DateTime();
        DateTime tanggalBerangkat = new DateTime();
        DateTime tanggalKembali = new DateTime();

        int jumlahHariKembali = 0;
        int jumlahHariBerangkat = 0;

        tanggalBerangkat = new DateTime(sppdPerson.getTanggalBerangkat().getTime());
        tanggalKembali = new DateTime(sppdPerson.getTanggalKembali().getTime());
        tanggalBerangkatUpdate = new DateTime(CommonUtil.convertStringToDate(sppdPerson.getStTanggalBerangkatRealisasi()).getTime());
        tanggalKembaliUpdate = new DateTime(CommonUtil.convertStringToDate(sppdPerson.getStTanggalKembaliRealisasi()).getTime());

        jumlahHariBerangkat = Days.daysBetween(tanggalBerangkatUpdate, tanggalKembaliUpdate).getDays();

        Date[] hasil = new Date[Math.abs(jumlahHariKembali) + 1];
        Date[] hasilBerangkat = new Date[Math.abs(jumlahHariBerangkat) + 1];

        DateTime tmpTanggal = new DateTime(tanggalBerangkat);
        String sppdPersonId = "";

        // update flag N untuk semua tanggal pada user tertentu
        List<ItSppdPersonEntity> itSppdPersonEntities = sppdPersonDao.getListSppdPerson1(sppdPerson.getSppdId(), sppdPerson.getPersonId());
        if(itSppdPersonEntities.size() > 0){
            for(ItSppdPersonEntity itSppdPersonEntity: itSppdPersonEntities){
                sppdPersonId = itSppdPersonEntity.getSppdPersonId();
            }
        }
        List<ItSppdTanggalEntity> itSppdTanggalEntity2 = sppdTanggalDao.getDataTanggal(sppdPersonId);
        java.util.Date []tanggalUnCheck = new java.util.Date[itSppdTanggalEntity2.size()];
        int jmlTglUnCheck = 0;
        if(itSppdTanggalEntity2.size() > 0){
            for(ItSppdTanggalEntity itSppdTanggalEntity1: itSppdTanggalEntity2){
                itSppdTanggalEntity1.setFlag("N");
                itSppdTanggalEntity1.setAction("U");
                itSppdTanggalEntity1.setCreatedWho(CommonUtil.userLogin());
                itSppdTanggalEntity1.setLastUpdateWho(CommonUtil.userLogin());
                itSppdTanggalEntity1.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                itSppdTanggalEntity1.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                if(itSppdTanggalEntity1.getSppdTanggalApprove().equalsIgnoreCase("N")){
                    tanggalUnCheck[jmlTglUnCheck] = itSppdTanggalEntity1.getSppdTanggal();
                    jmlTglUnCheck++;
                }
                try {
                    sppdTanggalDao.updateAndSave(itSppdTanggalEntity1);
                } catch (HibernateException e) {
                    logger.error("[SppdBoImpl.getSearchSppdByCriteria] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
            }
        }

        // insert tanggal baru
        tmpTanggal = new DateTime(tanggalBerangkatUpdate);
        for(int a = 0 ; a <= Math.abs(jumlahHariBerangkat); a++){
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");

            String dtStr = fmt.print(tmpTanggal);
            hasilBerangkat[a] = CommonUtil.convertStringToDate(dtStr);

            String sppdTanggalId = sppdTanggalDao.getNextSppdTanggalId();
            ItSppdTanggalEntity itSppdTanggalEntity = new ItSppdTanggalEntity();

            itSppdTanggalEntity.setIdSppdTanggal(sppdTanggalId);
            itSppdTanggalEntity.setSppdPersonId(sppdPerson.getSppdPersonId());
            itSppdTanggalEntity.setSppdTanggal(hasilBerangkat[a]);
            itSppdTanggalEntity.setSppdTanggalApprove("Y");

            for(int tgl = 0 ; tgl < jmlTglUnCheck; tgl++){
                if(tanggalUnCheck[tgl].compareTo(hasilBerangkat[a]) == 0){
                    itSppdTanggalEntity.setSppdTanggalApprove("N");
                    break;
                }else{
                    itSppdTanggalEntity.setSppdTanggalApprove("Y");
                }
            }
            BigDecimal biayaMakan = BigDecimal.valueOf(0);
            BigDecimal biayaLain = BigDecimal.valueOf(0);

            biayaMakan = getBiayaMakan(sppdPerson.getGolonganId(), sppdPerson.getKelompokId(), sppdPerson.getDinas());
            biayaLain = getBiayaLain(sppdPerson.getGolonganId(), sppdPerson.getKelompokId(), sppdPerson.getDinas());
            itSppdTanggalEntity.setBiayaLain(biayaLain);
            itSppdTanggalEntity.setBiayaMakan(biayaMakan);


            itSppdTanggalEntity.setFlag("Y");
            itSppdTanggalEntity.setAction("C");
            itSppdTanggalEntity.setCreatedWho(CommonUtil.userLogin());
            itSppdTanggalEntity.setLastUpdateWho(CommonUtil.userLogin());
            itSppdTanggalEntity.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            itSppdTanggalEntity.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

            try {
                sppdTanggalDao.addAndSave(itSppdTanggalEntity);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.getSearchSppdByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            tmpTanggal = tmpTanggal.plusDays(1);
        }

        return hasil;
    }

    @Override
    public List<Notifikasi> saveAdd(Sppd bean, SppdPerson bean2) throws GeneralBOException {
        logger.info("[SppdBoImpl.saveAdd] start process >>>");
        List<ImPerjalananDinasEntity> imPerjalananDinasEntities = new ArrayList<>();
        List<Notifikasi> notifikasiList = new ArrayList<>();

        if (bean!=null) {

            String sppdId;
            String sppdPersonId;
            String notifId;

            String id;
            String kelompokId = "";
            String idSurat;
            String[] parts = bean.getStTanggalSppdBerangkat().split("-");
            String thn = parts[2].substring(Math.max(parts[2].length() - 2, 0));

            BigDecimal biayaMakan = new BigDecimal(0) ;
            BigDecimal biayaLain = new BigDecimal(0) ;

            try {
                // Generating ID, get from postgre sequence
                sppdId = sppdDao.getNextSppdId();
                sppdPersonId = sppdPersonDao.getNextSppdPersonId();
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence sppdId id, please info to your admin..." + e.getMessage());
            }

            id = "SPPD"+thn+parts[1]+sppdId;
            idSurat = sppdId+"/SPPD."+bean.getBranchId()+"/"+parts[1]+"/"+parts[2];
            // creating object entity serializable
            ImSppdEntity imSppdEntity = new ImSppdEntity();
            ItSppdPersonEntity itSppdPersonEntity = new ItSppdPersonEntity();


            imSppdEntity.setSppdId(id);
            imSppdEntity.setNoSurat(idSurat);
            imSppdEntity.setPemberiTugas(bean.getPemberiTugas());
            imSppdEntity.setClosed("N");
            imSppdEntity.setFlagEdit("N");
            imSppdEntity.setDinas(bean.getDinas());
            imSppdEntity.setBranchId(bean.getBranchId());
            imSppdEntity.setDivisiId(bean.getDivisiId());
            imSppdEntity.setTanggalSppdBerangkat(bean.getTanggalSppdBerangkat());
            imSppdEntity.setTanggalSppdKembali(bean.getTanggalSppdKembali());

            imSppdEntity.setTugasSppd(bean.getTugasSppd());

            ImBiodataEntity detailBiodata = biodataDao.getById("nip", bean2.getPersonId());

            List<ItPersonilPositionEntity> personilPositionEntity = personilPositionDao.getListPersonilPosition(bean2.getPersonId());
            if(personilPositionEntity.size() > 0){
                String golonganId = "";
                if(!"".equalsIgnoreCase(detailBiodata.getGolongan()) && detailBiodata.getGolongan() != null){
                    golonganId = detailBiodata.getGolongan();
                }

                for(ItPersonilPositionEntity itPersonilPositionEntity: personilPositionEntity){
                    kelompokId = itPersonilPositionEntity.getImPosition().getKelompokId();
                }
                //Biaya makan BPD01
                biayaMakan = getBiayaMakan(golonganId, kelompokId, bean.getDinas());

                //Biaya Lain BPD02
                biayaLain = getBiayaLain(golonganId, kelompokId, bean.getDinas());

                bean2.setGolonganId(golonganId);
                bean2.setKelompokId(kelompokId);
                bean2.setDinas(bean.getDinas());

                itSppdPersonEntity.setGolonganId(golonganId);
                itSppdPersonEntity.setKelompokId(kelompokId);
            }

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<SppdPerson> listOfsearchSppdPerson = (List<SppdPerson>) session.getAttribute("listOfResultSppdPerson");

            itSppdPersonEntity.setSppdPersonId(sppdPersonId);
            bean2.setSppdPersonId(sppdPersonId);
            itSppdPersonEntity.setSppdId(id);

            itSppdPersonEntity.setPersonId(bean2.getPersonId());
            itSppdPersonEntity.setPersonName(bean2.getPersonName());
            itSppdPersonEntity.setTipePerson("Ketua");
            itSppdPersonEntity.setBranchId(bean2.getBranchId());
            itSppdPersonEntity.setDivisiId(bean2.getDivisiId());
            itSppdPersonEntity.setPositionId(bean2.getPositionId());
            itSppdPersonEntity.setBerangkatDari(bean2.getBerangkatDari());
            itSppdPersonEntity.setTujuanKe(bean2.getTujuanKe());
            itSppdPersonEntity.setTanggalBerangkat(bean.getTanggalSppdBerangkat());
            itSppdPersonEntity.setTanggalKembali(bean.getTanggalSppdKembali());
            itSppdPersonEntity.setTanggalBerangkatRealisasi(bean.getTanggalSppdBerangkat());
            itSppdPersonEntity.setTanggalKembaliRealisasi(bean.getTanggalSppdKembali());
            bean2.setTanggalBerangkatRealisasi(bean2.getTanggalBerangkat());
            bean2.setTanggalKembaliRealisasi(bean2.getTanggalKembali());
            bean2.setDinas(bean.getDinas());
            itSppdPersonEntity.setBerangkatVia(bean2.getBerangkatVia());
            itSppdPersonEntity.setKembaliVia(bean2.getKembaliVia());
            itSppdPersonEntity.setPenginapan(bean2.getPenginapan());
            itSppdPersonEntity.setFlag(bean.getFlag());
            itSppdPersonEntity.setAction(bean.getAction());
            itSppdPersonEntity.setCreatedWho(bean.getCreatedWho());
            itSppdPersonEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itSppdPersonEntity.setCreatedDate(bean.getCreatedDate());
            itSppdPersonEntity.setLastUpdate(bean.getLastUpdate());
            itSppdPersonEntity.setApprovalFlag("N");
            itSppdPersonEntity.setApprovalSdmFlag("N");

            itSppdPersonEntity.setBiayaLain(biayaLain);
            itSppdPersonEntity.setBiayaMakan(biayaMakan);

            itSppdPersonEntity.setBiayaLokalBerangkat(biayaTransportLokal(bean2.getBerangkatVia()));
            itSppdPersonEntity.setBiayaTujuan(biayaTransportTujuan(bean2.getTujuanKe()));
            itSppdPersonEntity.setBiayaLokalKembali(biayaTransportLokal(bean2.getBerangkatVia()));

            itSppdPersonEntity.setTiketPergi(BigDecimal.valueOf(0));
            itSppdPersonEntity.setTiketKembali(BigDecimal.valueOf(0));
            itSppdPersonEntity.setBiayaTambahan(BigDecimal.valueOf(0));

            itSppdPersonEntity.setTrainingFlag(bean.getFlagTraining());
            itSppdPersonEntity.setTrainingId(bean.getIdTraining());

            imSppdEntity.setFlag(bean.getFlag());
            imSppdEntity.setAction(bean.getAction());
            imSppdEntity.setCreatedWho(bean.getCreatedWho());
            imSppdEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSppdEntity.setCreatedDate(bean.getCreatedDate());
            imSppdEntity.setLastUpdate(bean.getLastUpdate());



            ImBiodataEntity imBiodataEntity;

            try {
                imBiodataEntity =  biodataDao.getById("nip", bean2.getPersonId(), "Y");
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            //Send notif ke atasan
            Notifikasi notifAtasan= new Notifikasi();
            notifAtasan.setNip(bean2.getPersonId());
            notifAtasan.setNoRequest(id);
            notifAtasan.setTipeNotifId("TI");
            notifAtasan.setTipeNotifName("SPPD");
            notifAtasan.setNote("Data Dari User : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
            notifAtasan.setCreatedWho(bean2.getPersonId());
            notifAtasan.setTo("atasan");

            notifikasiList.add(notifAtasan);

            try {
                // insert into database
                sppdDao.addAndSave(imSppdEntity);
                sppdPersonDao.addAndSave(itSppdPersonEntity);
                insertSppdTanggal(bean2);

            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Sppd, please info to your admin..." + e.getMessage());
            }

            if(listOfsearchSppdPerson != null){
                for (SppdPerson sppdPerson: listOfsearchSppdPerson) {
                    sppdPersonId = sppdPersonDao.getNextSppdPersonId();
                    ItSppdPersonEntity itSppdPersonEntityAnggota = new ItSppdPersonEntity();

                    if (sppdPerson.getStTanggalBerangkat() != null && !"".equalsIgnoreCase(sppdPerson.getStTanggalBerangkat())) {
                        sppdPerson.setTanggalBerangkat(CommonUtil.convertToDate(sppdPerson.getStTanggalBerangkat()));
                        sppdPerson.setTanggalKembali(CommonUtil.convertToDate(sppdPerson.getStTanggalKembali()));
                    }

                    sppdPerson.setTanggalBerangkatRealisasi(sppdPerson.getTanggalBerangkat());
                    sppdPerson.setTanggalKembaliRealisasi(sppdPerson.getTanggalKembali());

                    itSppdPersonEntityAnggota.setSppdId(id);
                    itSppdPersonEntityAnggota.setSppdPersonId(sppdPersonId);
                    sppdPerson.setSppdPersonId(sppdPersonId);
                    sppdPerson.setDinas(bean.getDinas());
                    itSppdPersonEntityAnggota.setPersonId(sppdPerson.getPersonId());
                    itSppdPersonEntityAnggota.setPersonName(sppdPerson.getPersonName());
                    itSppdPersonEntityAnggota.setBranchId(sppdPerson.getBranchId());
                    itSppdPersonEntityAnggota.setBranchName(sppdPerson.getBranchName());
                    itSppdPersonEntityAnggota.setPositionId(sppdPerson.getPositionId());
                    itSppdPersonEntityAnggota.setPositionName(sppdPerson.getPositionName());
                    itSppdPersonEntityAnggota.setDivisiId(sppdPerson.getDivisiId());
                    itSppdPersonEntityAnggota.setDivisiName(sppdPerson.getDivisiName());
                    itSppdPersonEntityAnggota.setTipePerson("Anggota");
                    itSppdPersonEntityAnggota.setApprovalFlag("N");
                    itSppdPersonEntityAnggota.setApprovalSdmFlag("N");
                    itSppdPersonEntityAnggota.setTrainingFlag(bean.getFlagTraining());
                    itSppdPersonEntityAnggota.setTrainingId(bean.getIdTraining());

                    itSppdPersonEntityAnggota.setBerangkatDari(sppdPerson.getBerangkatDari());
                    itSppdPersonEntityAnggota.setTujuanKe(sppdPerson.getTujuanKe());
                    itSppdPersonEntityAnggota.setTanggalBerangkat(sppdPerson.getTanggalBerangkat());
                    itSppdPersonEntityAnggota.setTanggalKembali(sppdPerson.getTanggalKembali());
                    itSppdPersonEntityAnggota.setTanggalBerangkatRealisasi(sppdPerson.getTanggalBerangkat());
                    itSppdPersonEntityAnggota.setTanggalKembaliRealisasi(sppdPerson.getTanggalKembali());
                    itSppdPersonEntityAnggota.setBerangkatVia(sppdPerson.getBerangkatVia());
                    itSppdPersonEntityAnggota.setKembaliVia(sppdPerson.getKembaliVia());
                    itSppdPersonEntityAnggota.setPenginapan(sppdPerson.getPenginapan());

                    detailBiodata = biodataDao.getById("nip", sppdPerson.getPersonId());

                    personilPositionEntity = personilPositionDao.getListPersonilPosition(sppdPerson.getPersonId());

                    String golonganId = "";
                    if(!"".equalsIgnoreCase(detailBiodata.getGolongan()) && detailBiodata != null){
                        golonganId = detailBiodata.getGolongan();
                    }

                    if(personilPositionEntity.size() > 0){
                        for(ItPersonilPositionEntity itPersonilPositionEntity: personilPositionEntity){
                            kelompokId = itPersonilPositionEntity.getImPosition().getKelompokId();
                        }
                        //Biaya makan BPD01
                        biayaMakan = getBiayaMakan(golonganId, kelompokId, bean.getDinas());

                        //Biaya Lain BPD02
                        biayaLain = getBiayaLain(golonganId, kelompokId, bean.getDinas());

                        sppdPerson.setGolonganId(golonganId);
                        sppdPerson.setKelompokId(kelompokId);
                        sppdPerson.setDinas(bean.getDinas());

                        itSppdPersonEntityAnggota.setGolonganId(golonganId);
                        itSppdPersonEntityAnggota.setKelompokId(kelompokId);
                    }

                    itSppdPersonEntityAnggota.setBiayaLain(biayaLain);
                    itSppdPersonEntityAnggota.setBiayaMakan(biayaMakan);

                    itSppdPersonEntityAnggota.setBiayaLokalBerangkat(biayaTransportLokal(sppdPerson.getBerangkatVia()));
                    itSppdPersonEntityAnggota.setBiayaTujuan(BigDecimal.valueOf(0));
                    itSppdPersonEntityAnggota.setBiayaLokalKembali(biayaTransportLokal(sppdPerson.getBerangkatVia()));

                    itSppdPersonEntityAnggota.setTiketPergi(BigDecimal.valueOf(0));
                    itSppdPersonEntityAnggota.setTiketKembali(BigDecimal.valueOf(0));
                    itSppdPersonEntityAnggota.setBiayaTambahan(BigDecimal.valueOf(0));

                    itSppdPersonEntityAnggota.setFlag(bean.getFlag());
                    itSppdPersonEntityAnggota.setAction(bean.getAction());
                    itSppdPersonEntityAnggota.setCreatedWho(bean.getCreatedWho());
                    itSppdPersonEntityAnggota.setLastUpdateWho(bean.getLastUpdateWho());
                    itSppdPersonEntityAnggota.setCreatedDate(bean.getCreatedDate());
                    itSppdPersonEntityAnggota.setLastUpdate(bean.getLastUpdate());
                    sppdPersonDao.addAndSave(itSppdPersonEntityAnggota);
                    insertSppdTanggal(sppdPerson);

                    try {
                        imBiodataEntity =  biodataDao.getById("nip", sppdPerson.getPersonId(), "Y");
                    } catch (HibernateException e) {
                        logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                    }

                    //Send notif ke atasan
                    notifAtasan= new Notifikasi();
                    notifAtasan.setNip(sppdPerson.getPersonId());
                    notifAtasan.setNoRequest(id);
                    notifAtasan.setTipeNotifId("TI");
                    notifAtasan.setTipeNotifName("SPPD");
                    notifAtasan.setNote("Data Dari User : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
                    notifAtasan.setCreatedWho(sppdPerson.getPersonId());
                    notifAtasan.setTo("atasan");

                    notifikasiList.add(notifAtasan);

                }
            }
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultSppdPerson");
        logger.info("[SppdBoImpl.saveAdd] end process <<<");
        return notifikasiList;
    }

    @Override
    public List<SppdTanggal> getSppdTanggal(String sppdPersonId) throws GeneralBOException {
        List<SppdTanggal> resultSppdTanggal = new ArrayList<>();
        List<ItSppdTanggalEntity> itSppdTanggalEntities = new ArrayList<>();

        itSppdTanggalEntities = sppdTanggalDao.getDataSppdTanggal(sppdPersonId);

        if(itSppdTanggalEntities.size() > 0){
            for(ItSppdTanggalEntity itSppdTanggalEntity: itSppdTanggalEntities){
                SppdTanggal sppdTanggal = new SppdTanggal();
                sppdTanggal.setIdSppdTanggal(itSppdTanggalEntity.getIdSppdTanggal());
                sppdTanggal.setSppdTanggal(itSppdTanggalEntity.getSppdTanggal());
                sppdTanggal.setStSppdTanggal(CommonUtil.convertDateToString(itSppdTanggalEntity.getSppdTanggal()));
                String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(itSppdTanggalEntity.getSppdTanggal());
                sppdTanggal.setHari(dayOfWeek);

                sppdTanggal.setSppdTanggalApprove(itSppdTanggalEntity.getSppdTanggalApprove());
                sppdTanggal.setBiayaLain(itSppdTanggalEntity.getBiayaLain());
                sppdTanggal.setBiayaMakan(itSppdTanggalEntity.getBiayaMakan());

                resultSppdTanggal.add(sppdTanggal);
            }
        }

        return resultSppdTanggal;
    }

    @Override
    public List<Sppd> getByCriteria(Sppd searchBean) throws GeneralBOException {
        logger.info("[SppdBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Sppd> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria          = new HashMap();
            Map hsCriteriaPerson    = new HashMap();

            if (searchBean.getSppdId() != null && !"".equalsIgnoreCase(searchBean.getSppdId())) {
                hsCriteria.put("sppd_id", searchBean.getSppdId());
            }

            if (searchBean.getDinas() != null && !"".equalsIgnoreCase(searchBean.getDinas())) {
                hsCriteria.put("dinas", searchBean.getDinas());
            }

            if (searchBean.getDivisiId() != null && !"".equalsIgnoreCase(searchBean.getDivisiId())) {
                hsCriteria.put("divisi_id", searchBean.getDivisiId());
            }

            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }

            if (searchBean.getNoSurat() != null && !"".equalsIgnoreCase(searchBean.getNoSurat())) {
                hsCriteria.put("no_surat", searchBean.getNoSurat());
            }

            if (searchBean.getTanggalSppdBerangkat() != null) {
                hsCriteria.put("tanggal_sppd_berangkat", searchBean.getTanggalSppdBerangkat());
            }

            if (searchBean.getTanggalSppdKembali() != null) {
                hsCriteria.put("tanggal_sppd_kembali", searchBean.getTanggalSppdKembali());
            }

            if (searchBean.getFlag() != null) {
                hsCriteria.put("flag", searchBean.getFlag());
            }

            List<ImSppdEntity> imSppdEntity = null;
            List<ItSppdPersonEntity> itSppdPersonEntities = null;
            List<SppdPerson> sppdPersons = new ArrayList();;
            try {
                imSppdEntity = sppdDao.getByCriteria(hsCriteria);
                SppdPerson sppdPerson;
                itSppdPersonEntities = sppdPersonDao.getByCriteria(hsCriteriaPerson);
                for(ItSppdPersonEntity sppdPersonEntity : itSppdPersonEntities){
                    sppdPerson = new SppdPerson();
                    sppdPerson.setPersonId(sppdPersonEntity.getPersonId());
                    sppdPerson.setPositionId(sppdPersonEntity.getPositionId());
                    sppdPerson.setPersonName(sppdPersonEntity.getPersonName());
                    sppdPerson.setDivisiId(sppdPersonEntity.getImSppdEntity().getDivisiId());
                    sppdPerson.setBranchId(sppdPersonEntity.getImSppdEntity().getBranchId());
                    sppdPerson.setTipePerson(sppdPersonEntity.getTipePerson());
                    sppdPerson.setBerangkatDari(sppdPersonEntity.getImKotaEntity().getKotaName());
                    if(sppdPersonEntity.getTujuanKe().length() <= 4){
                        sppdPerson.setTujuanKe(sppdPersonEntity.getImKotaEntity2().getKotaName());
                    }else{
                        sppdPerson.setTujuanKe(sppdPersonEntity.getTujuanKe());
                    }
                    sppdPerson.setSppdId(sppdPersonEntity.getSppdId());
                    sppdPersons.add(sppdPerson);
                }

                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResultPerson");
                session.setAttribute("listOfResultPerson", sppdPersons);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.getSearchSppdByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            /*if(imSppdEntity != null){
                if(!"".equalsIgnoreCase(searchBean.getPersonNip()) && searchBean.getPersonNip() != null){
                    List<ItSppdPersonEntity> hasilFilterByNip = sppdPersonDao.getListSppdPerson2(searchBean.getPersonNip());
                    if(hasilFilterByNip != null){
                        for(ItSppdPersonEntity itSppdPersonEntity: hasilFilterByNip){
                            for(ImSppdEntity imSppdEntity1: imSppdEntity){
                                if(itSppdPersonEntity.getPersonId().equalsIgnoreCase(imSppdEntity1.getPers)){
                                    hasilImSppdEntity.add(imSppdEntity1);
                                    break;
                                }
                            }
                        }
                    }
                }else{
                    hasilImSppdEntity.addAll(imSppdEntity);
                }
            }*/

            if(imSppdEntity != null){
                Sppd returnSppd;
                // Looping from dao to object and save in collection
                for(ImSppdEntity sppdEntity : imSppdEntity){
                    returnSppd = new Sppd();

                    if(!"".equalsIgnoreCase(searchBean.getPersonNip()) && searchBean.getPersonNip() != null){
                        hsCriteriaPerson.put("sppd_id", sppdEntity.getSppdId());
                        hsCriteriaPerson.put("person_id", searchBean.getPersonNip());
                    }else{
                        hsCriteriaPerson.put("sppd_id", sppdEntity.getSppdId());
                        hsCriteriaPerson.put("tipe_person", "Ketua");
                    }

                    itSppdPersonEntities = sppdPersonDao.getByCriteria(hsCriteriaPerson);
                    for(ItSppdPersonEntity sppdPersonEntity : itSppdPersonEntities){
                        if(sppdPersonEntity.getTipePerson().equalsIgnoreCase("Ketua")){
                            returnSppd.setPersonNip(sppdPersonEntity.getPersonId());
                            returnSppd.setPersonName(sppdPersonEntity.getPersonName());
                            returnSppd.setPositionId(sppdPersonEntity.getPositionId() + "");
                            returnSppd.setBerangkatVia(sppdPersonEntity.getBerangkatVia());
                            returnSppd.setPulangVia(sppdPersonEntity.getKembaliVia());
                            returnSppd.setBerangkatDariId(sppdPersonEntity.getImKotaEntity().getKotaId());
                            returnSppd.setBerangkatDari(sppdPersonEntity.getImKotaEntity().getKotaName());
                            if(sppdPersonEntity.getTujuanKe().length() <= 4){
                                returnSppd.setTujuanKeId(sppdPersonEntity.getImKotaEntity2().getKotaId());
                                returnSppd.setTujuanKe(sppdPersonEntity.getImKotaEntity2().getKotaName());
                            }else{
                                returnSppd.setTujuanKe(sppdPersonEntity.getTujuanKe());
                            }
                        }
                    }

                    String DATE_FORMAT = "dd-MM-yyyy";
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                    //System.out.println("Formated Date " + sdf.format(sppdEntity.getTanggalSppdBerangkat()));


                    returnSppd.setSppdId(sppdEntity.getSppdId());
                    returnSppd.setDinas(sppdEntity.getDinas());
                    returnSppd.setBranchId(sppdEntity.getBranchId());
                    returnSppd.setNoSurat(sppdEntity.getNoSurat());

                    returnSppd.setTugasSppd(sppdEntity.getTugasSppd());
                    returnSppd.setTanggalSppdBerangkat(sppdEntity.getTanggalSppdBerangkat());
                    returnSppd.setStTanggalSppdBerangkat(sdf.format(sppdEntity.getTanggalSppdBerangkat()));
                    returnSppd.setStTanggalSppdKembali(sdf.format(sppdEntity.getTanggalSppdKembali()));
                    returnSppd.setTanggalSppdKembali(sppdEntity.getTanggalSppdKembali());
                    returnSppd.setClosed(sppdEntity.getClosed());

                    List<ItSppdPersonEntity> sppdPersons1 = sppdPersonDao.getListSppdPerson(sppdEntity.getSppdId());
                    returnSppd.setSppdEditStatusAtasan(cekApproveAtasanSys(sppdEntity.getSppdId()));

                    if(sppdPersons1 != null){
                        String flag = "Y";
                        for(ItSppdPersonEntity sppdPersonEntity: sppdPersons1){
                            if(sppdPersonEntity.getApprovalSdmId() == null && "Y".equalsIgnoreCase(sppdPersonEntity.getFlag())){
                                flag = "N";
                                break;
                            }
                        }
                        if(flag.equalsIgnoreCase("Y")){
                            if("ADMIN".equalsIgnoreCase(CommonUtil.roleAsLogin()) || "Admin Bagian".equalsIgnoreCase(CommonUtil.roleAsLogin())){
                                returnSppd.setSppdEditStatus(true);
                            }
                        }
                    }

                    if(sppdEntity.getClosed().equals("Y")){
                        returnSppd.setSppdClosed(true);
                    }if(sppdEntity.getClosed().equals("Y")){
                        returnSppd.setSppdClosed(true);
                    }

                    returnSppd.setDivisiId(sppdEntity.getDivisiId());
                    //returnSppd.setDivisiName(sppdEntity.getImDepartmentEntity().getDepartmentName());
                    returnSppd.setCreatedWho(sppdEntity.getCreatedWho());
                    returnSppd.setCreatedDate(sppdEntity.getCreatedDate());
                    returnSppd.setLastUpdate(sppdEntity.getLastUpdate());
                    returnSppd.setAction(sppdEntity.getAction());
                    returnSppd.setFlag(sppdEntity.getFlag());

                    if(itSppdPersonEntities.size() > 0){
                        listOfResult.add(returnSppd);
                    }

                }
            }
        }
        logger.info("[SppdBoImpl.getByCriteria] end process <<<");
        return listOfResult;
    }

    @Override
    public List<SppdPerson> getByCriteriaSppdPerson(SppdPerson searchBean) throws GeneralBOException {
        logger.info("[SppdBoImpl.getByCriteria] start process >>>");
        List<SppdPerson> sppdPersons = new ArrayList<>();
        // Mapping with collection and put
        if (searchBean != null) {
            Map hsCriteriaPerson    = new HashMap();
            List<ItSppdPersonEntity> itSppdPersonEntities = null;
            hsCriteriaPerson.put("person_id", searchBean.getPersonId());
            hsCriteriaPerson.put("flag", "Y");
            itSppdPersonEntities = sppdPersonDao.getByCriteria(hsCriteriaPerson);
            for(ItSppdPersonEntity sppdPersonEntity : itSppdPersonEntities){
                SppdPerson returnSppdPerson = new SppdPerson();
                returnSppdPerson.setPersonId(sppdPersonEntity.getPersonId());
                returnSppdPerson.setPersonName(sppdPersonEntity.getPersonName());
                returnSppdPerson.setPositionId(sppdPersonEntity.getPositionId());
                returnSppdPerson.setTipePerson(sppdPersonEntity.getTipePerson());
                returnSppdPerson.setBerangkatVia(sppdPersonEntity.getBerangkatVia());
                returnSppdPerson.setKembaliVia(sppdPersonEntity.getKembaliVia());
                returnSppdPerson.setBerangkatDari(sppdPersonEntity.getImKotaEntity().getKotaName());
                returnSppdPerson.setTujuanKe(sppdPersonEntity.getImKotaEntity2().getKotaName());
                returnSppdPerson.setTanggalBerangkat(sppdPersonEntity.getTanggalBerangkat());

                sppdPersons.add(returnSppdPerson);
            }
        }
        logger.info("[SppdBoImpl.getByCriteria] end process <<<");

        return sppdPersons;
    }
    @Override
    public List<Sppd> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Sppd> getComboSppdWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Sppd> listComboSppd = new ArrayList();

        List<ImSppdEntity> listSppd = null;
        try {
            listSppd = sppdDao.getListSppd(query);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSppd != null) {
            for (ImSppdEntity imSppdEntity : listSppd) {
                Sppd itemComboSppd = new Sppd();
                itemComboSppd.setSppdId(imSppdEntity.getSppdId());

                listComboSppd.add(itemComboSppd);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSppd;
    }

    public List<SppdPerson> getComboSppdPerson2(String personId) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SppdPerson> listComboSppd = new ArrayList();

        List<ItSppdPersonEntity> listSppd = null;
        try {
            listSppd = sppdPersonDao.getListSppdPerson2(personId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSppd != null) {
            for (ItSppdPersonEntity imSppdEntity : listSppd) {
                SppdPerson itemComboSppd = new SppdPerson();
                //itemComboSppd.setTiket(imSppdEntity.getTiket());
                itemComboSppd.setSppdPersonId(imSppdEntity.getSppdPersonId());
                itemComboSppd.setPersonId(imSppdEntity.getPersonId());
                itemComboSppd.setPositionId(imSppdEntity.getPositionId());
                itemComboSppd.setPositionName(imSppdEntity.getImPosition().getPositionName());
                itemComboSppd.setBranchId(imSppdEntity.getBranchId());
                itemComboSppd.setBranchName(imSppdEntity.getImBranches().getBranchName());
                itemComboSppd.setDivisiId(imSppdEntity.getDivisiId());
                itemComboSppd.setDivisiName(imSppdEntity.getImDepartmentEntity().getDepartmentName());
                itemComboSppd.setPersonName(imSppdEntity.getPersonName());
                itemComboSppd.setTipePerson(imSppdEntity.getTipePerson());
                itemComboSppd.setTanggalBerangkat(imSppdEntity.getTanggalBerangkat());
                itemComboSppd.setTanggalKembali(imSppdEntity.getTanggalKembali());
                itemComboSppd.setSppdId(imSppdEntity.getSppdId());
                itemComboSppd.setApprovalSdmFlag(imSppdEntity.getApprovalSdmFlag());
                itemComboSppd.setDinas(imSppdEntity.getImSppdEntity().getDinas());
                itemComboSppd.setBerangkatVia(imSppdEntity.getBerangkatVia());
                itemComboSppd.setKembaliVia(imSppdEntity.getKembaliVia());
                itemComboSppd.setBerangkatDari(imSppdEntity.getImKotaEntity().getKotaName());
                itemComboSppd.setTujuanKe(imSppdEntity.getImKotaEntity2().getKotaName());
                itemComboSppd.setNotApprovalNote(imSppdEntity.getNotApprovalNote());
                if(imSppdEntity.getApprovalId() != null){
                    itemComboSppd.setSppdApprove(true);
                }

                if(imSppdEntity.getApprovalSdmId() != null){
                    itemComboSppd.setSppdApproveSdm(true);
                }

                if(imSppdEntity.getImSppdEntity().getClosed().equals("Y")){
                    itemComboSppd.setSppdClosed(true);
                    itemComboSppd.setTmpClosed(contextPath + "/pages/images/icon_success.ico");
                }

                if(imSppdEntity.getApprovalFlag().equals("Y")){
                    itemComboSppd.setSppdApproveStatus(true);
                    itemComboSppd.setLinkAtasan(contextPath + "/pages/images/icon_success.ico");
                }else{
                    itemComboSppd.setLinkAtasan(contextPath + "/pages/images/icon_failure.ico");
                }

                if(imSppdEntity.getApprovalSdmFlag().equals("Y")){
                    itemComboSppd.setSppdApproveSdmStatus(true);
                    itemComboSppd.setLinkSdm(contextPath + "/pages/images/icon_success.ico");
                }else{
                    itemComboSppd.setLinkSdm(contextPath + "/pages/images/icon_failure.ico");
                }

                if(imSppdEntity.getPejabatSementara() != null){
                    itemComboSppd.setPejabatSementara(imSppdEntity.getPejabatSementara());
                    itemComboSppd.setPejabatSementaraNama(imSppdEntity.getImBiodataEntity().getNamaPegawai());
                }else{
                    itemComboSppd.setPejabatSementara("");
                    itemComboSppd.setPejabatSementaraNama("");
                }


                listComboSppd.add(itemComboSppd);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSppd;
    }

    public List<SppdReroute> getComboSppdReroute(String sppdId) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SppdReroute> sppdRerouteList = new ArrayList();

        List<ItSppdRerouteEntity> itSppdRerouteEntityList = null;
        try {
            itSppdRerouteEntityList = sppdRerouteDao.getListSppdReroute(sppdId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (itSppdRerouteEntityList != null) {
            for (ItSppdRerouteEntity itSppdRerouteEntity : itSppdRerouteEntityList) {
                SppdReroute sppdReroute = new SppdReroute();
                sppdReroute.setSppdRerouteId(itSppdRerouteEntity.getSppdRerouteId());
                sppdReroute.setSppdPersonId(itSppdRerouteEntity.getSppdPersonId());
                sppdReroute.setTanggalRerouteKe(itSppdRerouteEntity.getTanggalRerouteKe());
                sppdReroute.setStTanggalRerouteKe(CommonUtil.convertDateToString(itSppdRerouteEntity.getTanggalRerouteKe()));
                sppdReroute.setRerouteKe(itSppdRerouteEntity.getRerouteKe());
                sppdReroute.setKota(itSppdRerouteEntity.getKota());
                sppdReroute.setBerangkatVia(itSppdRerouteEntity.getBerangkatVia());
                sppdReroute.setNip(itSppdRerouteEntity.getNip());
                sppdReroute.setNama(itSppdRerouteEntity.getNama());
                sppdReroute.setPosisi(itSppdRerouteEntity.getPosisiName());
                sppdReroute.setKeterangan(itSppdRerouteEntity.getKeterangan());
                sppdReroute.setSppdId(itSppdRerouteEntity.getSppdId());

                sppdReroute.setTransportLokalBerangkat(itSppdRerouteEntity.getTransportLokalBerangkat());
                sppdReroute.setTransportTujuan(itSppdRerouteEntity.getTransportTujuan());
                sppdReroute.setTransportLokalKembali(itSppdRerouteEntity.getTransportLokalKembali());

                sppdRerouteList.add(sppdReroute);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return sppdRerouteList;
    }

    public List<SppdDoc> getComboSppdDoc(String sppdId) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SppdDoc> sppdDocList = new ArrayList();

        List<ItSppdDocEntity> itSppdRerouteEntityList = null;
        try {
            itSppdRerouteEntityList = sppdDocDao.getListSppdPerson(sppdId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (itSppdRerouteEntityList != null) {
            for (ItSppdDocEntity itSppdDocEntity : itSppdRerouteEntityList) {
                SppdDoc sppdDoc = new SppdDoc();
                sppdDoc.setDocSppdId(itSppdDocEntity.getDocSppdId());
                sppdDoc.setSppdId(itSppdDocEntity.getSppdId());
                sppdDoc.setSppdPersonId(itSppdDocEntity.getSppdPersonId());
                sppdDoc.setFileUploadDoc(itSppdDocEntity.getFileUploadDoc());
                sppdDoc.setNote(itSppdDocEntity.getNote());
                sppdDocList.add(sppdDoc);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return sppdDocList;
    }

    public List<SppdReroute> getComboSppdReroute2(String sppdId, String id) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SppdReroute> sppdRerouteList = new ArrayList();

        List<ItSppdRerouteEntity> itSppdRerouteEntity = null;
        try {
            itSppdRerouteEntity = sppdRerouteDao.getListSppdReroute(sppdId, id);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (itSppdRerouteEntity != null) {
            for (ItSppdRerouteEntity itSppdRerouteEntity1 : itSppdRerouteEntity) {
                SppdReroute sppdReroute = new SppdReroute();
                sppdReroute.setSppdRerouteId(itSppdRerouteEntity1.getSppdRerouteId());
                sppdReroute.setSppdPersonId(itSppdRerouteEntity1.getSppdPersonId());
                sppdReroute.setTanggalRerouteKe(itSppdRerouteEntity1.getTanggalRerouteKe());
                sppdReroute.setStTanggalRerouteKe(CommonUtil.convertDateToString(itSppdRerouteEntity1.getTanggalRerouteKe()));
                sppdReroute.setStTanggalRerouteDari(CommonUtil.convertDateToString(itSppdRerouteEntity1.getTanggalRerouteDari()));
                sppdReroute.setRerouteDari(itSppdRerouteEntity1.getRerouteDari());
                sppdReroute.setRerouteKe(itSppdRerouteEntity1.getRerouteKe());
                sppdReroute.setKotaDari(itSppdRerouteEntity1.getKotaDari());
                sppdReroute.setKota(itSppdRerouteEntity1.getKota());
                sppdReroute.setBerangkatVia(itSppdRerouteEntity1.getBerangkatVia());
                sppdReroute.setNip(itSppdRerouteEntity1.getNip());
                sppdReroute.setNama(itSppdRerouteEntity1.getNama());
                sppdReroute.setPosisi(itSppdRerouteEntity1.getPosisiName());
                sppdReroute.setKeterangan(itSppdRerouteEntity1.getKeterangan());
                sppdReroute.setSppdId(itSppdRerouteEntity1.getSppdId());

                sppdReroute.setTransportLokalBerangkat(itSppdRerouteEntity1.getTransportLokalBerangkat());
                sppdReroute.setTransportTujuan(itSppdRerouteEntity1.getTransportTujuan());
                sppdReroute.setTransportLokalKembali(itSppdRerouteEntity1.getTransportLokalKembali());
                sppdReroute.setBiayaTambahan(itSppdRerouteEntity1.getBiayaTambahan());
                sppdReroute.setTiketPergi(itSppdRerouteEntity1.getTiketPergi());
                sppdReroute.setTiketKembali(itSppdRerouteEntity1.getTiketKembali());

                sppdRerouteList.add(sppdReroute);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return sppdRerouteList;
    }

    @Override
    public int jumlahHari(String sppdId) throws GeneralBOException {
        int hasil = 0;
        List<ItSppdPersonEntity> sppdPersonEntities = sppdPersonDao.getListSppdPerson(sppdId, "Ketua");

        if(sppdPersonEntities.size() > 0){
            DateTime tanggalBerangkat;
            DateTime tanggalKembali;
            for(ItSppdPersonEntity itSppdPersonEntity : sppdPersonEntities){
                tanggalBerangkat = new DateTime(itSppdPersonEntity.getTanggalBerangkatRealisasi());
                tanggalKembali = new DateTime(itSppdPersonEntity.getTanggalKembaliRealisasi());

                hasil = Days.daysBetween(tanggalBerangkat, tanggalKembali).getDays();
            }
        }
        return hasil;
    }

    @Override
    public List<SppdPerson> getReportSppd(String sppdId, String dinas) throws GeneralBOException {
        List<SppdPerson> sppdPersonList = new ArrayList();
        List<ItSppdPersonEntity> listSppd = null;
        List<ImPerjalananDinasEntity> imPerjalananDinasEntities = null;
        List<ImPosition> imPositions = null;
        List<ImTransportLokalEntity> imTransportLokalEntities = null;
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy");
        BigDecimal hargaDolar = new BigDecimal(0);
        Date tanggalReroute = null;
        String nipReroute = "";

        try {
            //listSppd = sppdPersonDao.getListSppdPerson(sppdId); //yg lama
            listSppd = sppdPersonDao.getDataPersonReport(sppdId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSppd != null) {
            for (ItSppdPersonEntity itSppdPersonEntity : listSppd) {
                SppdPerson itemComboSppd = new SppdPerson();
                itemComboSppd.setStTanggalBerangkat(itSppdPersonEntity.getPersonName());
                itemComboSppd.setBiayaLain(" Dinas Ke ");
                itemComboSppd.setBiayaMakan(itSppdPersonEntity.getTujuanKeName());
                itemComboSppd.setTransportLokal("");
                itemComboSppd.setTransportLokasi("");
                itemComboSppd.setTiket("");
                itemComboSppd.setBiayaTambahanSppd("");
                if(dinas.equalsIgnoreCase("LN")){
                    itemComboSppd.setJumlah("1 US = Rp ");
                    ImCompany imCompany = companyDao.getCompanyInfo("Y");
                    if(imCompany != null){
                        hargaDolar = imCompany.getKursDolar();
                    }
                    itemComboSppd.setKeterangan("");
                }else{
                    itemComboSppd.setJumlah("");
                    itemComboSppd.setKeterangan("");
                }

                sppdPersonList.add(itemComboSppd);

                itemComboSppd = new SppdPerson();
                itemComboSppd.setStTanggalBerangkat("Tanggal");
                itemComboSppd.setBiayaLain("Biaya Lain");
                itemComboSppd.setBiayaMakan("Biaya Makan");
                itemComboSppd.setTransportLokal("Trans. Lokal");
                itemComboSppd.setTransportLokasi("Trans. Tujuan");
                itemComboSppd.setTiket("Tiket");
                itemComboSppd.setBiayaTambahanSppd("Biaya Tambahan");
                itemComboSppd.setJumlah("Jumlah");
                itemComboSppd.setKeterangan("Keterangan");
                sppdPersonList.add(itemComboSppd);

                BigDecimal jumlahTotal = new BigDecimal(0);
                List<ItSppdTanggalEntity> listTanggalSppd = sppdTanggalDao.getListDataTanggal(itSppdPersonEntity.getSppdPersonId());
                if(listTanggalSppd != null){
                    int index = 1;
                    for(ItSppdTanggalEntity listSppdTanggal: listTanggalSppd){

                        //jika Ada reroute
                        if(itSppdPersonEntity.getPersonId().equalsIgnoreCase(nipReroute)){
                            int selisih = listSppdTanggal.getSppdTanggal().compareTo(tanggalReroute);
                            if(selisih == 0){
                                break;
                            }
                        }

                        itemComboSppd = new SppdPerson();
                        BigDecimal hasilTotal = new BigDecimal(0);
                        if(listTanggalSppd.size() == 1){
                            hasilTotal = hasilTotal.add(listSppdTanggal.getBiayaLain());
                            hasilTotal = hasilTotal.add(listSppdTanggal.getBiayaMakan());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getBiayaLokalBerangkat());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getBiayaLokalKembali());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getTiketPergi());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getTiketKembali());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getBiayaTujuan());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getBiayaTambahan());

                            itemComboSppd.setBiayaMakan(NumberFormat.getInstance().format(listSppdTanggal.getBiayaMakan()) + "");
                            itemComboSppd.setTransportLokal(NumberFormat.getInstance().format((itSppdPersonEntity.getBiayaLokalBerangkat().
                                    add(itSppdPersonEntity.getBiayaLokalKembali()))) + "");
                            itemComboSppd.setTransportLokasi(NumberFormat.getInstance().format((itSppdPersonEntity.getBiayaTujuan())) + "");

                            itemComboSppd.setTiket(NumberFormat.getInstance().format(itSppdPersonEntity.getTiketPergi().add(itSppdPersonEntity.getTiketKembali())) + "");
                            itemComboSppd.setBiayaTambahanSppd(NumberFormat.getInstance().format(itSppdPersonEntity.getBiayaTambahan()) + "");
                            itemComboSppd.setJumlah(NumberFormat.getInstance().format(hasilTotal) + "");
                            itemComboSppd.setKeterangan("Naik "+itSppdPersonEntity.getBerangkatVia());

                            jumlahTotal = jumlahTotal.add(hasilTotal);
                        }
                        else if(index == 1){
                            //Jika ada reroute,
                            List<ItSppdRerouteEntity> listReroute = sppdRerouteDao.getListSppdRerouteBySppdIdNNip(sppdId, itSppdPersonEntity.getSppdPersonId());
                            if(listReroute.size() > 0){
                                for(ItSppdRerouteEntity itSppdRerouteEntity: listReroute){
                                    tanggalReroute = itSppdRerouteEntity.getTanggalRerouteDari();
                                    nipReroute = itSppdRerouteEntity.getNip();
                                }
                            }

                            hasilTotal = hasilTotal.add(listSppdTanggal.getBiayaLain());
                            hasilTotal = hasilTotal.add(listSppdTanggal.getBiayaMakan());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getBiayaLokalBerangkat());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getTiketPergi());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getBiayaTujuan());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getBiayaTambahan());

                            itemComboSppd.setBiayaTambahanSppd(NumberFormat.getInstance().format(itSppdPersonEntity.getBiayaTambahan()) + "");
                            itemComboSppd.setBiayaMakan(NumberFormat.getInstance().format(listSppdTanggal.getBiayaMakan()) + "");
                            if(listTanggalSppd.size() == 1){
                                itemComboSppd.setTransportLokal(NumberFormat.getInstance().format((itSppdPersonEntity.getBiayaLokalBerangkat())) + "");
                                itemComboSppd.setTransportLokasi(NumberFormat.getInstance().format((itSppdPersonEntity.getBiayaTujuan())) + "");


                            }else{
                                itemComboSppd.setTransportLokal(NumberFormat.getInstance().format((itSppdPersonEntity.getBiayaLokalBerangkat())) + "");
                                itemComboSppd.setTransportLokasi(NumberFormat.getInstance().format((itSppdPersonEntity.getBiayaTujuan())) + "");


                            }

                            itemComboSppd.setTiket(NumberFormat.getInstance().format(itSppdPersonEntity.getTiketPergi()) + "");
                            itemComboSppd.setJumlah(NumberFormat.getInstance().format(hasilTotal) + "");
                            itemComboSppd.setKeterangan("Naik "+itSppdPersonEntity.getBerangkatVia());

                            jumlahTotal = jumlahTotal.add(hasilTotal);
                        }else if(index == listTanggalSppd.size()){
                            hasilTotal = hasilTotal.add(listSppdTanggal.getBiayaLain());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getTiketKembali());
                            hasilTotal = hasilTotal.add(listSppdTanggal.getBiayaMakan());
                            hasilTotal = hasilTotal.add(itSppdPersonEntity.getBiayaLokalKembali());

                            jumlahTotal = jumlahTotal.add(hasilTotal);

                            itemComboSppd.setBiayaTambahanSppd("0");
                            itemComboSppd.setBiayaMakan(NumberFormat.getInstance().format(listSppdTanggal.getBiayaMakan()) + "");
                            itemComboSppd.setTransportLokal(NumberFormat.getInstance().format(itSppdPersonEntity.getBiayaLokalKembali()) + "");
                            itemComboSppd.setTransportLokasi(NumberFormat.getInstance().format(0) + "");
                            itemComboSppd.setTiket(NumberFormat.getInstance().format(itSppdPersonEntity.getTiketKembali()) + "");
                            itemComboSppd.setJumlah(NumberFormat.getInstance().format(hasilTotal) + "");
                            itemComboSppd.setKeterangan("Naik "+itSppdPersonEntity.getKembaliVia());
                        }else{
                            hasilTotal = hasilTotal.add(listSppdTanggal.getBiayaLain());
                            hasilTotal = hasilTotal.add(listSppdTanggal.getBiayaMakan());
                            jumlahTotal = jumlahTotal.add(hasilTotal);

                            itemComboSppd.setBiayaMakan(NumberFormat.getInstance().format(listSppdTanggal.getBiayaMakan()) + "");
                            itemComboSppd.setTransportLokal("0");
                            itemComboSppd.setTransportLokasi("0");
                            itemComboSppd.setTiket("0");
                            itemComboSppd.setTiketKembali(BigDecimal.valueOf(0));
                            itemComboSppd.setBiayaTambahanSppd("0");
                            itemComboSppd.setJumlah(NumberFormat.getInstance().format(hasilTotal) + "");
                            itemComboSppd.setKeterangan("");
                        }

                        if(index <= 15 ){
                            itemComboSppd.setBiayaLain(NumberFormat.getInstance().format(listSppdTanggal.getBiayaLain()) + "");
                        }else{
                            BigDecimal biayaLain = new BigDecimal(0);
                            biayaLain = listSppdTanggal.getBiayaLain().multiply(BigDecimal.valueOf(60));
                            biayaLain = biayaLain.divide(BigDecimal.valueOf(100));
                            itemComboSppd.setBiayaLain(NumberFormat.getInstance().format(((biayaLain))));
                        }

                        itemComboSppd.setStTanggalBerangkat(CommonUtil.convertDateToString(listSppdTanggal.getSppdTanggal()));
                        itemComboSppd.setTotal(NumberFormat.getInstance().format(0) + "");

                        sppdPersonList.add(itemComboSppd);
                        index++;
                    }
                }

                itemComboSppd = new SppdPerson();
                itemComboSppd.setStTanggalBerangkat("");
                itemComboSppd.setBiayaLain("");
                itemComboSppd.setBiayaMakan("");
                itemComboSppd.setTransportLokal("");
                itemComboSppd.setTransportLokasi("");
                itemComboSppd.setTiket("");
                itemComboSppd.setBiayaTambahanSppd("Total :");
                itemComboSppd.setJumlah(NumberFormat.getInstance().format(jumlahTotal) + "");
                itemComboSppd.setKeterangan("");
                sppdPersonList.add(itemComboSppd);

                sppdPersonList.addAll(getReportSppdReroute(sppdId, itSppdPersonEntity.getSppdPersonId(), tanggalReroute));
            }

        }

        return sppdPersonList;
    }


    public List<SppdPerson> getReportSppdReroute(String sppdId, String nipReroute, Date tanggalReroute) throws GeneralBOException {
        List<SppdPerson> sppdPersonList = new ArrayList();
        List<ItSppdRerouteEntity> listSppd = null;
        List<ImPerjalananDinasEntity> imPerjalananDinasEntities = null;
        List<ImPosition> imPositions = null;
        List<ImTransportLokalEntity> imTransportLokalEntities = null;
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy");

        BigDecimal hargaDolar = new BigDecimal(0);


        try {
            //listSppd = sppdPersonDao.getListSppdPerson(sppdId); //yg lama
            listSppd = sppdRerouteDao.getListSppdRerouteBySppdIdNNip(sppdId, nipReroute);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSppd != null) {
            for (ItSppdRerouteEntity itSppdRerouteEntity : listSppd) {
                SppdPerson itemComboSppd = new SppdPerson();
                itemComboSppd.setStTanggalBerangkat(itSppdRerouteEntity.getNama());
                String kota = itSppdRerouteEntity.getKotaDari() + " Ke";
                itemComboSppd.setBiayaLain(kota);
                itemComboSppd.setBiayaMakan(itSppdRerouteEntity.getKota());
                itemComboSppd.setTransportLokal("");
                itemComboSppd.setTransportLokasi("");
                itemComboSppd.setTiket("");
                itemComboSppd.setBiayaTambahanSppd("");

                itemComboSppd.setJumlah("");
                itemComboSppd.setKeterangan("");

                sppdPersonList.add(itemComboSppd);

                itemComboSppd = new SppdPerson();
                itemComboSppd.setStTanggalBerangkat("Tanggal");
                itemComboSppd.setBiayaLain("Biaya Lain");
                itemComboSppd.setBiayaMakan("Biaya Makan");
                itemComboSppd.setTransportLokal("Trans. Lokal");
                itemComboSppd.setTransportLokasi("Trans. Tujuan");
                itemComboSppd.setTiket("Tiket");
                itemComboSppd.setBiayaTambahanSppd("Biaya Tambahan");
                itemComboSppd.setJumlah("Jumlah");
                itemComboSppd.setKeterangan("Keterangan");
                sppdPersonList.add(itemComboSppd);

                BigDecimal jumlahTotal = new BigDecimal(0);
                List<ItSppdTanggalEntity> listTanggalSppd = sppdTanggalDao.getListDataTanggal(itSppdRerouteEntity.getSppdPersonId(), tanggalReroute);
                if(listTanggalSppd != null){
                    int index = 1;
                    for(ItSppdTanggalEntity listSppdTanggal: listTanggalSppd){
                        itemComboSppd = new SppdPerson();
                        BigDecimal hasilTotal = new BigDecimal(0);
                        if(listTanggalSppd.size() == 1){
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getBiayaLain());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getBiayaMakan());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTransportLokalBerangkat());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTransportLokalKembali());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTiketPergi());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTiketKembali());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTransportTujuan());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getBiayaTambahan());

                            itemComboSppd.setBiayaMakan(NumberFormat.getInstance().format(itSppdRerouteEntity.getBiayaMakan()) + "");
                            itemComboSppd.setTransportLokal(NumberFormat.getInstance().format((itSppdRerouteEntity.getTransportLokalBerangkat().
                                    add(itSppdRerouteEntity.getTransportLokalKembali()))) + "");
                            itemComboSppd.setTransportLokasi(NumberFormat.getInstance().format((itSppdRerouteEntity.getTransportTujuan())) + "");

                            itemComboSppd.setTiket(NumberFormat.getInstance().format(itSppdRerouteEntity.getTiketPergi().add(itSppdRerouteEntity.getTiketKembali())) + "");
                            itemComboSppd.setBiayaTambahanSppd(NumberFormat.getInstance().format(itSppdRerouteEntity.getBiayaTambahan()) + "");
                            itemComboSppd.setJumlah(NumberFormat.getInstance().format(hasilTotal) + "");
                            itemComboSppd.setKeterangan("Naik "+itSppdRerouteEntity.getBerangkatVia());

                            jumlahTotal = jumlahTotal.add(hasilTotal);
                        }
                        else if(index == 1){
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getBiayaLain());
                            //hasilTotal = hasilTotal.add(itSppdRerouteEntity.getBiayaMakan());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTransportLokalBerangkat());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTiketPergi());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTransportTujuan());

                            //itemComboSppd.setBiayaMakan(NumberFormat.getInstance().format(itSppdRerouteEntity.getBiayaMakan()) + "");
                            if(listTanggalSppd.size() == 1){
                                itemComboSppd.setTransportLokal(NumberFormat.getInstance().format((itSppdRerouteEntity.getTransportLokalBerangkat())) + "");
                                itemComboSppd.setTransportLokasi(NumberFormat.getInstance().format((itSppdRerouteEntity.getTransportTujuan())) + "");


                            }else{
                                itemComboSppd.setTransportLokal(NumberFormat.getInstance().format((itSppdRerouteEntity.getTransportLokalBerangkat())) + "");
                                itemComboSppd.setTransportLokasi(NumberFormat.getInstance().format((itSppdRerouteEntity.getTransportTujuan())) + "");


                            }

                            itemComboSppd.setTiket(NumberFormat.getInstance().format(itSppdRerouteEntity.getTiketPergi()) + "");
                            itemComboSppd.setBiayaTambahanSppd("0");
                            itemComboSppd.setBiayaMakan("0");
                            itemComboSppd.setJumlah(NumberFormat.getInstance().format(hasilTotal) + "");
                            itemComboSppd.setKeterangan("Naik "+itSppdRerouteEntity.getBerangkatVia());

                            jumlahTotal = jumlahTotal.add(hasilTotal);
                        }else if(index == listTanggalSppd.size()){
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getBiayaLain());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTiketKembali());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getBiayaMakan());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getTransportLokalKembali());
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getBiayaTambahan());

                            jumlahTotal = jumlahTotal.add(hasilTotal);

                            itemComboSppd.setBiayaMakan(NumberFormat.getInstance().format(itSppdRerouteEntity.getBiayaMakan()) + "");
                            itemComboSppd.setTransportLokal(NumberFormat.getInstance().format(itSppdRerouteEntity.getTransportLokalKembali()) + "");
                            itemComboSppd.setTransportLokasi(NumberFormat.getInstance().format(0) + "");
                            itemComboSppd.setTiket(NumberFormat.getInstance().format(itSppdRerouteEntity.getTiketKembali()) + "");
                            itemComboSppd.setBiayaTambahanSppd(NumberFormat.getInstance().format(itSppdRerouteEntity.getBiayaTambahan()) + "");
                            itemComboSppd.setJumlah(NumberFormat.getInstance().format(hasilTotal) + "");
                            //itemComboSppd.setKeterangan("Naik "+itSppdRerouteEntity.getKembaliVia());
                            itemComboSppd.setKeterangan("Naik "+itSppdRerouteEntity.getBerangkatVia());
                        }else{
                            hasilTotal = hasilTotal.add(itSppdRerouteEntity.getBiayaLain());
                            jumlahTotal = jumlahTotal.add(hasilTotal);

                            itemComboSppd.setBiayaMakan("0");
                            itemComboSppd.setTransportLokal("0");
                            itemComboSppd.setTransportLokasi("0");
                            itemComboSppd.setTiket("0");
                            itemComboSppd.setTiketKembali(BigDecimal.valueOf(0));
                            itemComboSppd.setBiayaTambahanSppd("0");
                            itemComboSppd.setJumlah(NumberFormat.getInstance().format(hasilTotal) + "");
                            itemComboSppd.setKeterangan("");
                        }

                        if(index <= 15 ){
                            itemComboSppd.setBiayaLain(NumberFormat.getInstance().format(itSppdRerouteEntity.getBiayaLain()) + "");
                        }else{
                            BigDecimal biayaLain = new BigDecimal(0);
                            biayaLain = itSppdRerouteEntity.getBiayaLain().multiply(BigDecimal.valueOf(60));
                            biayaLain = biayaLain.divide(BigDecimal.valueOf(100));
                            itemComboSppd.setBiayaLain(NumberFormat.getInstance().format(((biayaLain))));
                        }

                        itemComboSppd.setStTanggalBerangkat(CommonUtil.convertDateToString(listSppdTanggal.getSppdTanggal()));
                        itemComboSppd.setTotal(NumberFormat.getInstance().format(0) + "");

                        sppdPersonList.add(itemComboSppd);
                        index++;
                    }
                }

                itemComboSppd = new SppdPerson();
                itemComboSppd.setStTanggalBerangkat("");
                itemComboSppd.setBiayaLain("");
                itemComboSppd.setBiayaMakan("");
                itemComboSppd.setTransportLokal("");
                itemComboSppd.setTransportLokasi("");
                itemComboSppd.setTiket("");
                itemComboSppd.setBiayaTambahanSppd("Total :");
                itemComboSppd.setJumlah(NumberFormat.getInstance().format(jumlahTotal) + "");
                itemComboSppd.setKeterangan("");
                sppdPersonList.add(itemComboSppd);
            }
        }

        return sppdPersonList;
    }

    /*private List<SppdPerson> reportLuarNegri(String sppdId){
        List<ItSppdPersonEntity> listSppd = new ArrayList<>();

        try {
            listSppd = sppdPersonDao.getDataPersonReport(sppdId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        DateTime tanggalBerangkat = new DateTime();
        DateTime tanggalKembali = new DateTime();
        int jumlahHari = 0;
        String kelompokId = "";
        String berangkatVia = "";
        String kembaliVia = "";
        String nama = "";
        String transportLokasi = "";
        Long tiketPergi ;
        Long tiketKembali ;
        Long biayaTambahan ;
        String keterangan;

        double biayaMakan = 0;
        double biayaLain = 0;
        double biayaTransportLokasi = 0;
        double biayaTransportPulang = 0;
        double biayaTransport = 0;
        double jumlah = 0;

        if(listSppd.size() > 0){
            for (ItSppdPersonEntity itSppdPersonEntity : listSppd) {
                tanggalBerangkat = new DateTime(itSppdPersonEntity.getTanggalBerangkat().getTime());
                tanggalKembali =new DateTime(itSppdPersonEntity.getTanggalKembali().getTime());

                jumlahHari = Days.daysBetween(tanggalBerangkat, tanggalKembali).getDays();
                kelompokId = itSppdPersonEntity.getKelompokId();
                berangkatVia = itSppdPersonEntity.getBerangkatVia();
                kembaliVia = itSppdPersonEntity.getKembaliVia();
                nama = itSppdPersonEntity.getPersonName();
                transportLokasi = itSppdPersonEntity.getTujuanKe();
                tiketPergi = itSppdPersonEntity.getTiketPergi();
                tiketKembali = itSppdPersonEntity.getTiketKembali();
                biayaTambahan = itSppdPersonEntity.getBiayaTambahan();
                keterangan = itSppdPersonEntity.getTujuanKeName();

                //Biaya makan BPD01
                List<ImPerjalananDinasEntity> imPerjalananDinasEntities = perjalananDinasDao.getListPerjalananDinasSppd(kelompokId, "BPD01", "LN");
                for (ImPerjalananDinasEntity imPerjalananDinasEntity : imPerjalananDinasEntities) {
                    biayaMakan += imPerjalananDinasEntity.getJumlahBiaya();
                    jumlah += biayaMakan;
                }

                //Biaya Lain BPD02
                imPerjalananDinasEntities = perjalananDinasDao.getListPerjalananDinasSppd(kelompokId, "BPD02", dinas);
                for (ImPerjalananDinasEntity imPerjalananDinasEntity : imPerjalananDinasEntities) {
                    biayaLain += imPerjalananDinasEntity.getJumlahBiaya();
                    jumlah += biayaLain;
                }

                List<ImTransportLokalEntity> imTransportLokalEntities = transportLokalDao.getListTransportLokalLokasiSppd(transportLokasi[b]);
                for (ImTransportLokalEntity imTransportLokalEntity : imTransportLokalEntities) {
                    biayaTransportLokasi += imTransportLokalEntity.getJumlahBiaya();
                }

                //Transport Lokal Pesawat
                if(berangkatVia.equalsIgnoreCase("Pesawat") || kembaliVia.equalsIgnoreCase("Pesawat")){
                    imTransportLokalEntities = transportLokalDao.getListTransportLokalSppd("TL06");
                    for (ImTransportLokalEntity imTransportLokalEntity : imTransportLokalEntities) {
                        biayaTransport += imTransportLokalEntity.getJumlahBiaya();
                        biayaTransportPulang += imTransportLokalEntity.getJumlahBiaya();
                    }
                }//Transport Lokal Bis
                else if(transport[b].equalsIgnoreCase("Bis") || transportPulang[b].equalsIgnoreCase("Bis")){
                    imTransportLokalEntities = transportLokalDao.getListTransportLokalSppd("TL07");
                    for (ImTransportLokalEntity imTransportLokalEntity : imTransportLokalEntities) {
                        biayaTransport[b] += imTransportLokalEntity.getJumlahBiaya();
                        biayaTransportPulang[b] += imTransportLokalEntity.getJumlahBiaya();
                    }
                }//Transport Lokal Kereta Api
                else if(transport[b].equalsIgnoreCase("Kereta Api") || transportPulang[b].equalsIgnoreCase("Kereta Api")){
                    imTransportLokalEntities = transportLokalDao.getListTransportLokalSppd("TL08");
                    for (ImTransportLokalEntity imTransportLokalEntity : imTransportLokalEntities) {
                        biayaTransport[b] += imTransportLokalEntity.getJumlahBiaya();
                        biayaTransportPulang[b] += imTransportLokalEntity.getJumlahBiaya();
                    }
                }//Transport Lokal Mobil
                else{
                    biayaTransport[b] += 0;
                    biayaTransportPulang[b] += 0;
                }
            }
        }

        return null;
    }*/

    @Override
    public boolean cekBiayaLokalSys(String lokalId) throws GeneralBOException {
        boolean hasil = false;
        List<ImTransportLokalEntity> transport = transportLokalDao.getListTransportLokalLokasiSppd(lokalId);
        if(transport.size() > 0){
            hasil = true;
        }
        return hasil;
    }

    @Override
    public boolean cekApproveAtasanSys(String sppdId) throws GeneralBOException {
        //default jika sudah terapprove semua adalah true
        boolean hasil = true;
        List<ItSppdPersonEntity> itSppdPersonEntities = sppdPersonDao.getListPerson(sppdId, "Y");
        if(itSppdPersonEntities.size() > 0){
            hasil = false;
        }
        return hasil;
    }

    public Boolean cekRerouteSys(String personId, String tanggal) throws GeneralBOException {
        Boolean hasil = false;
        List<ItSppdPersonEntity> listSppd = null;
        List<ItSppdRerouteEntity> itSppdRerouteEntityList = null;
        try {
            listSppd = sppdPersonDao.cekRerouteSys(personId);
        } catch (HibernateException e) {
            logger.error("[SppdBoImpl.cekRerouteSys] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        int selisihHari = 0;
        if (listSppd != null) {
            for (ItSppdPersonEntity imSppdEntity : listSppd) {
                java.sql.Date tmpTanggal = CommonUtil.convertStringToDate(tanggal);
                DateTime tanggalReroute = new DateTime(tmpTanggal);
                DateTime tanggalSppd = new DateTime(imSppdEntity.getTanggalKembaliRealisasi());
                Period p = new Period(tanggalSppd, tanggalReroute, PeriodType.yearMonthDayTime());
                selisihHari = p.getDays();

                //reroute Dao
                itSppdRerouteEntityList = sppdRerouteDao.getListSppdReroute(imSppdEntity.getSppdId());
            }
        }

        if(selisihHari >= 0){
            if(itSppdRerouteEntityList.size() >= 0){
                for(ItSppdRerouteEntity rerouteEntity : itSppdRerouteEntityList){
                    java.sql.Date tmpTanggal = CommonUtil.convertStringToDate(tanggal);
                    DateTime tanggalReroute = new DateTime(tmpTanggal);
                    DateTime tanggalSppd = new DateTime(rerouteEntity.getTanggalRerouteKe());
                    Period p = new Period(tanggalSppd, tanggalReroute, PeriodType.yearMonthDayTime());
                    selisihHari = p.getDays();
                    break;
                }
                if(selisihHari >= 0){
                    hasil = true;
                }
            }else{
                hasil = true;
            }
        }

        logger.info("[SppdBoImpl.cekRerouteSys] end process <<<");
        return hasil;
    }

    @Override
    public Boolean cekNipRerouteSys(String sppdPersonId) throws GeneralBOException {
        //true tidak ada sppd reroute
        Boolean hasil = true;
        List<ItSppdRerouteEntity> sppdRerouteEntities = sppdRerouteDao.getListSppdReroutePersonId(sppdPersonId);
        if(sppdRerouteEntities.size() > 0){
            hasil = false;
        }

        return hasil;
    }

    @Override
    public String cekTanggalRerouteSys(String tanggalAwal, String tanggalAkhir, String personId) throws GeneralBOException {
        java.util.Date tglAwal = CommonUtil.convertToDate(tanggalAwal);
        java.util.Date tglAkhir = CommonUtil.convertToDate(tanggalAkhir);
        String hasil = "-";

        List<ItSppdPersonEntity> sppdPersonList = sppdPersonDao.cekRerouteSys(personId);
        SppdPerson sppdPerson = new SppdPerson();
        if(sppdPersonList.size() > 0){
            for(ItSppdPersonEntity itSppdPersonEntity: sppdPersonList){
                sppdPerson.setTanggalBerangkatRealisasi(itSppdPersonEntity.getTanggalBerangkatRealisasi());
                sppdPerson.setTanggalKembaliRealisasi(itSppdPersonEntity.getTanggalKembaliRealisasi());
            }
        }

        if(tglAwal.compareTo(sppdPerson.getTanggalBerangkatRealisasi()) >= 0 && tglAwal.compareTo(sppdPerson.getTanggalKembaliRealisasi()) <= 0
                && tglAkhir.compareTo(sppdPerson.getTanggalBerangkatRealisasi()) >= 0 && tglAkhir.compareTo(sppdPerson.getTanggalKembaliRealisasi()) <= 0){
            //ok
        }else{
            //error
            hasil = "Tanggal reroute dari / tanggal reroute ke tidak boleh melebihi / kurang dari tanggal realisasi";
        }

        return hasil;
    }

    public List<SppdPerson> getComboSppdPerson(String sppdId) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SppdPerson> listComboSppd = new ArrayList();

        List<ItSppdPersonEntity> listSppd = null;
        try {
            listSppd = sppdPersonDao.getListPerson(sppdId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSppd != null) {
            for (ItSppdPersonEntity imSppdEntity : listSppd) {
                SppdPerson itemComboSppd = new SppdPerson();
                itemComboSppd.setSppdPersonId(imSppdEntity.getSppdPersonId());
                itemComboSppd.setPersonId(imSppdEntity.getPersonId());
                itemComboSppd.setPositionId(imSppdEntity.getPositionId());
                itemComboSppd.setPositionName(imSppdEntity.getImPosition().getPositionName());
                itemComboSppd.setBranchId(imSppdEntity.getBranchId());
                itemComboSppd.setBranchName(imSppdEntity.getImBranches().getBranchName());
                itemComboSppd.setDivisiId(imSppdEntity.getDivisiId());
                if (!("").equalsIgnoreCase(imSppdEntity.getDivisiId())){
                    itemComboSppd.setDivisiName(imSppdEntity.getImDepartmentEntity().getDepartmentName());
                }
                itemComboSppd.setPersonName(imSppdEntity.getPersonName());
                itemComboSppd.setTipePerson(imSppdEntity.getTipePerson());
                itemComboSppd.setTanggalBerangkat(imSppdEntity.getTanggalBerangkat());
                itemComboSppd.setTanggalKembali(imSppdEntity.getTanggalKembali());
                itemComboSppd.setSppdId(imSppdEntity.getSppdId());
                itemComboSppd.setBerangkatVia(imSppdEntity.getBerangkatVia());
                itemComboSppd.setKembaliVia(imSppdEntity.getKembaliVia());
                itemComboSppd.setPenginapan(imSppdEntity.getPenginapan());
                itemComboSppd.setTiketPergi(imSppdEntity.getTiketPergi());
                itemComboSppd.setTiketKembali(imSppdEntity.getTiketKembali());
                itemComboSppd.setBiayaTambahan(imSppdEntity.getBiayaTambahan());
                itemComboSppd.setBiayaLokalBerangkat(imSppdEntity.getBiayaLokalBerangkat());
                itemComboSppd.setBiayaLokalKembali(imSppdEntity.getBiayaLokalKembali());
                itemComboSppd.setBiayaTujuan(imSppdEntity.getBiayaTujuan());
                if(imSppdEntity.getNoteAtasanTransport() != null){
                    itemComboSppd.setNoteAtasanTransport(imSppdEntity.getNoteAtasanTransport());
                }else{
                    itemComboSppd.setNoteAtasanTransport("");
                }
                if(imSppdEntity.getNoteSdmTransport() != null ){
                    itemComboSppd.setNoteSdmTransport(imSppdEntity.getNoteSdmTransport());
                }else{
                    itemComboSppd.setNoteSdmTransport("");
                }

                if(imSppdEntity.getApprovalId() != null){
                    itemComboSppd.setSppdApprove(true);
                    itemComboSppd.setApprovalName(imSppdEntity.getApprovalName());
                }else{
                    itemComboSppd.setApprovalName("");
                }

                if(imSppdEntity.getApprovalSdmId() != null){
                    itemComboSppd.setSppdApproveSdm(true);
                    itemComboSppd.setApprovalSdmName(imSppdEntity.getApprovalSdmName());
                }else{
                    itemComboSppd.setNotApprovalSdmNote("");
                    itemComboSppd.setApprovalSdmName("");
                }

                if(imSppdEntity.getApprovalFlag().equals("Y")){
                    itemComboSppd.setSppdApproveStatus(true);
                    itemComboSppd.setLinkAtasan(contextPath + "/pages/images/icon_success.ico");
                    itemComboSppd.setNotApprovalNote("");
                }else{
                    itemComboSppd.setLinkAtasan(contextPath + "/pages/images/icon_failure.ico");
                    itemComboSppd.setNotApprovalNote(imSppdEntity.getNotApprovalNote());
                }

                if(imSppdEntity.getApprovalSdmFlag().equals("Y")){
                    itemComboSppd.setSppdApproveSdmStatus(true);
                    itemComboSppd.setNotApprovalSdmNote("");
                    itemComboSppd.setLinkSdm(contextPath + "/pages/images/icon_success.ico");
                }else{
                    itemComboSppd.setLinkSdm(contextPath + "/pages/images/icon_failure.ico");
                    itemComboSppd.setNotApprovalSdmNote(imSppdEntity.getNotApprovalSdmNote());
                }

                if(imSppdEntity.getPejabatSementara() != null){
                    itemComboSppd.setPejabatSementara(imSppdEntity.getPejabatSementara());
                    itemComboSppd.setPejabatSementaraNama(imSppdEntity.getImBiodataEntity().getNamaPegawai());
                }else{
                    itemComboSppd.setPejabatSementara("");
                    itemComboSppd.setPejabatSementaraNama("");
                }

                if(imSppdEntity.getNotApprovalNote() == null){
                    itemComboSppd.setNotApprovalNote("");
                }
                if(imSppdEntity.getNotApprovalSdmNote() == null){
                    itemComboSppd.setNotApprovalSdmNote("");
                }


                listComboSppd.add(itemComboSppd);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");

        /*// tool untuk mengupdate table itSppd
        int aa = 0;
        List<ItSppdPersonEntity> itSppdPerson = sppdPersonDao.getListSppdPerson();
        for(ItSppdPersonEntity itSppdPersonEntity: itSppdPerson){
            ImBiodataEntity detailBiodata = biodataDao.getById("nip", itSppdPersonEntity.getPersonId());
            List<ItPersonilPositionEntity> personilPositionEntity = personilPositionDao.getListPersonilPosition(itSppdPersonEntity.getPersonId());
            if(personilPositionEntity .size() > 0){
                for(ItPersonilPositionEntity itPersonilPositionEntity: personilPositionEntity){
                    itSppdPersonEntity.setKelompokId(itPersonilPositionEntity.getImPosition().getKelompokId());
                }
            }
            itSppdPersonEntity.setGolonganId(detailBiodata.getGolongan());
            try {
                sppdPersonDao.updateAndSave(itSppdPersonEntity);
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }


            List<ItSppdTanggalEntity> itSppdTanggalEntities = sppdTanggalDao.getDataSppdTanggal(itSppdPersonEntity.getSppdPersonId());
            if(itSppdTanggalEntities.size() > 0){
                for(ItSppdTanggalEntity itSppdTanggalEntity: itSppdTanggalEntities){
                    itSppdTanggalEntity.setBiayaLain(itSppdPersonEntity.getBiayaLain());
                    itSppdTanggalEntity.setBiayaMakan(itSppdPersonEntity.getBiayaMakan());

                    try {
                        sppdTanggalDao.updateAndSave(itSppdTanggalEntity);
                    } catch (HibernateException e) {
                        logger.error("[SppdBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
                    }

                }
            }
            aa++;
        }*/

        return listComboSppd;
    }

    public List<SppdPerson> getComboSppdApproveBawahan(String SppdId) throws GeneralBOException{
        List<SppdPerson> personAll = getComboSppdPerson(SppdId);
        List<SppdPerson> resultPerson = new ArrayList<>();

        getBawahanJabatan(CommonUtil.userBranchLogin(), CommonUtil.userPosisiId());
        for(SppdPerson sppdPerson : personAll){
            for(StrukturJabatan strukturJabatan: strukturJabatanList){
                if(strukturJabatan.getNip().equalsIgnoreCase(sppdPerson.getPersonId())){
                    resultPerson.add(sppdPerson);
                    break;
                }
            }
        }

        return resultPerson;
    }

    //Digunakan untuk mengambil bawahan jabatan
    private List<StrukturJabatan> strukturJabatanList = new ArrayList();
    private void getBawahanJabatan(String branchId, String posisiId){
        List<ImStrukturJabatanEntity> strukturJabatans = null;
        strukturJabatans = strukturJabatanDao.getIdStrukturJabatanAtasan(branchId, posisiId);
        String idParent = "";
        String []uraian ;

        if(strukturJabatans.size() > 0){
            for(ImStrukturJabatanEntity strukturJabatanEntity : strukturJabatans){
                idParent = strukturJabatanEntity.getStrukturJabatanId();
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

                hasil = imStrukturJabatanEntity.getStrukturJabatanId();
                strukturJabatanList.add(strukturJabatan1);
                if(imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL02") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL03") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL04") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL05") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL06") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL07")){
                    strukturJabatanList.add(strukturJabatan1);
                }
                getListStruktur(getListStruktur(hasil));
            }
        }
        return hasil;
    }

    public List<SppdPerson> getComboSppdPersonApproved(String sppdId) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SppdPerson> listComboSppd = new ArrayList();

        List<ItSppdPersonEntity> listSppd = null;
        try {
            listSppd = sppdPersonDao.getListPersonApproved(sppdId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSppd != null) {
            for (ItSppdPersonEntity imSppdEntity : listSppd) {
                SppdPerson itemComboSppd = new SppdPerson();
                itemComboSppd.setSppdPersonId(imSppdEntity.getSppdPersonId());
                itemComboSppd.setPersonId(imSppdEntity.getPersonId());
                itemComboSppd.setPositionId(imSppdEntity.getPositionId());
                itemComboSppd.setPositionName(imSppdEntity.getImPosition().getPositionName());
                itemComboSppd.setBranchId(imSppdEntity.getBranchId());
                itemComboSppd.setBranchName(imSppdEntity.getImBranches().getBranchName());
                itemComboSppd.setDivisiId(imSppdEntity.getDivisiId());
                itemComboSppd.setDivisiName(imSppdEntity.getImDepartmentEntity().getDepartmentName());
                itemComboSppd.setPersonName(imSppdEntity.getPersonName());
                itemComboSppd.setTipePerson(imSppdEntity.getTipePerson());
                itemComboSppd.setTanggalBerangkat(imSppdEntity.getTanggalBerangkat());
                itemComboSppd.setTanggalKembali(imSppdEntity.getTanggalKembali());
                itemComboSppd.setSppdId(imSppdEntity.getSppdId());
                itemComboSppd.setBerangkatVia(imSppdEntity.getBerangkatVia());
                itemComboSppd.setKembaliVia(imSppdEntity.getKembaliVia());
                itemComboSppd.setPenginapan(imSppdEntity.getPenginapan());
                itemComboSppd.setTiketPergi(imSppdEntity.getTiketPergi());
                itemComboSppd.setTiketKembali(imSppdEntity.getTiketKembali());
                itemComboSppd.setBiayaLokalBerangkat(imSppdEntity.getBiayaLokalBerangkat());
                itemComboSppd.setBiayaLokalKembali(imSppdEntity.getBiayaLokalKembali());
                itemComboSppd.setBiayaTujuan(imSppdEntity.getBiayaTujuan());
                itemComboSppd.setBiayaTambahan(imSppdEntity.getBiayaTambahan());
                if(imSppdEntity.getNoteAtasanTransport() != null){
                    itemComboSppd.setNoteAtasanTransport(imSppdEntity.getNoteAtasanTransport());
                }else{
                    itemComboSppd.setNoteAtasanTransport("");
                }
                if(imSppdEntity.getNoteSdmTransport() != null ){
                    itemComboSppd.setNoteSdmTransport(imSppdEntity.getNoteSdmTransport());
                }else{
                    itemComboSppd.setNoteSdmTransport("");
                }

                if(imSppdEntity.getApprovalId() != null){
                    itemComboSppd.setSppdApprove(true);
                    itemComboSppd.setApprovalName(imSppdEntity.getApprovalName());
                }else{
                    itemComboSppd.setApprovalName("");
                }

                if(imSppdEntity.getApprovalSdmId() != null){
                    itemComboSppd.setSppdApproveSdm(true);
                    itemComboSppd.setApprovalSdmName(imSppdEntity.getApprovalSdmName());
                }else{
                    itemComboSppd.setNotApprovalSdmNote("");
                    itemComboSppd.setApprovalSdmName("");
                }

                if(imSppdEntity.getApprovalFlag().equals("Y")){
                    itemComboSppd.setSppdApproveStatus(true);
                    itemComboSppd.setLinkAtasan(contextPath + "/pages/images/icon_success.ico");
                    itemComboSppd.setNotApprovalNote("");
                }else{
                    itemComboSppd.setLinkAtasan(contextPath + "/pages/images/icon_failure.ico");
                    itemComboSppd.setNotApprovalNote(imSppdEntity.getNotApprovalNote());
                }

                if(imSppdEntity.getApprovalSdmFlag().equals("Y")){
                    itemComboSppd.setSppdApproveSdmStatus(true);
                    itemComboSppd.setNotApprovalSdmNote("");
                    itemComboSppd.setLinkSdm(contextPath + "/pages/images/icon_success.ico");
                }else{
                    itemComboSppd.setLinkSdm(contextPath + "/pages/images/icon_failure.ico");
                    itemComboSppd.setNotApprovalSdmNote(imSppdEntity.getNotApprovalSdmNote());
                }

                if(imSppdEntity.getPejabatSementara() != null){
                    itemComboSppd.setPejabatSementara(imSppdEntity.getPejabatSementara());
                    itemComboSppd.setPejabatSementaraNama(imSppdEntity.getImBiodataEntity().getNamaPegawai());
                }else{
                    itemComboSppd.setPejabatSementara("");
                    itemComboSppd.setPejabatSementaraNama("");
                }

                if(imSppdEntity.getNotApprovalNote() == null){
                    itemComboSppd.setNotApprovalNote("");
                }
                if(imSppdEntity.getNotApprovalSdmNote() == null){
                    itemComboSppd.setNotApprovalSdmNote("");
                }


                listComboSppd.add(itemComboSppd);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSppd;
    }

    public List<SppdPerson> getComboSppdPerson(String sppdId, String personId) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SppdPerson> listComboSppd = new ArrayList();

        List<ItSppdPersonEntity> listSppd = null;
        List<ItSppdTanggalEntity> listSppdTanggal = new ArrayList<>();

        String sppdTanggalId1 = "";
        String sppdTanggalId2 = "";

        try {
            listSppd = sppdPersonDao.getListSppdPerson1(sppdId, personId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSppd != null) {
            int a = 0;
            for (ItSppdPersonEntity imSppdEntity : listSppd) {

                SppdPerson itemComboSppd = new SppdPerson();
                itemComboSppd.setSppdTanggalId1(sppdTanggalId1);
                itemComboSppd.setSppdTanggalId2(sppdTanggalId2);

                itemComboSppd.setSppdPersonId(imSppdEntity.getSppdPersonId());
                itemComboSppd.setPersonId(imSppdEntity.getPersonId());

                itemComboSppd.setTiketPergi(imSppdEntity.getTiketPergi());
                itemComboSppd.setTiketKembali(imSppdEntity.getTiketKembali());
                itemComboSppd.setBiayaTambahan(imSppdEntity.getBiayaTambahan());
                itemComboSppd.setBiayaLokalBerangkat(imSppdEntity.getBiayaLokalBerangkat());
                itemComboSppd.setBiayaTujuan(imSppdEntity.getBiayaTujuan());
                itemComboSppd.setBiayaLokalKembali(imSppdEntity.getBiayaLokalKembali());

                itemComboSppd.setPositionId(imSppdEntity.getPositionId());
                itemComboSppd.setPositionName(imSppdEntity.getImPosition().getPositionName());
                itemComboSppd.setBranchId(imSppdEntity.getBranchId());
                itemComboSppd.setBranchName(imSppdEntity.getImBranches().getBranchName());
                itemComboSppd.setDivisiId(imSppdEntity.getDivisiId());
                if(imSppdEntity.getDivisiId() != null || "".equalsIgnoreCase(imSppdEntity.getDivisiId())){
                    itemComboSppd.setDivisiName(imSppdEntity.getImDepartmentEntity().getDepartmentName());
                }
                itemComboSppd.setPersonName(imSppdEntity.getPersonName());
                itemComboSppd.setTipePerson(imSppdEntity.getTipePerson());
                itemComboSppd.setTanggalBerangkat(imSppdEntity.getTanggalBerangkat());
                itemComboSppd.setTanggalKembali(imSppdEntity.getTanggalKembali());
                itemComboSppd.setSppdId(imSppdEntity.getSppdId());
                if(imSppdEntity.getSppdId() != null && !"".equalsIgnoreCase(imSppdEntity.getSppdId())){
                    if(imSppdEntity.getImSppdEntity().getDinas().equalsIgnoreCase("DN")){
                        itemComboSppd.setDinas("Dalam Negeri");
                    }else{
                        itemComboSppd.setDinas("Luar Negeri");
                    }
                }
                itemComboSppd.setBerangkatDariNama(imSppdEntity.getImKotaEntity().getKotaName());
                itemComboSppd.setBerangkatDari(imSppdEntity.getBerangkatDari());
                if(imSppdEntity.getTujuanKe().length() <= 4){
                    itemComboSppd.setTujuanKe(imSppdEntity.getTujuanKe());
                    itemComboSppd.setTujuanKeNama(imSppdEntity.getImKotaEntity2().getKotaName());
                }else{
                    itemComboSppd.setTujuanKe(imSppdEntity.getTujuanKe());
                    itemComboSppd.setTujuanKeNama(imSppdEntity.getTujuanKe());
                }
                itemComboSppd.setBerangkatVia(imSppdEntity.getBerangkatVia());
                itemComboSppd.setKembaliVia(imSppdEntity.getKembaliVia());
                itemComboSppd.setNoteAtasanTransport(imSppdEntity.getNoteAtasanTransport());
                itemComboSppd.setNoteSdmTransport(imSppdEntity.getNoteSdmTransport());
                itemComboSppd.setPenginapan(imSppdEntity.getPenginapan());
                itemComboSppd.setStTanggalBerangkat(CommonUtil.convertDateToString(imSppdEntity.getTanggalBerangkat()));
                itemComboSppd.setStTanggalKembali(CommonUtil.convertDateToString(imSppdEntity.getTanggalKembali()));
                itemComboSppd.setStTanggalBerangkatRealisasi(CommonUtil.convertDateToString(imSppdEntity.getTanggalBerangkatRealisasi()));
                itemComboSppd.setStTanggalKembaliRealisasi(CommonUtil.convertDateToString(imSppdEntity.getTanggalKembaliRealisasi()));
                itemComboSppd.setTipePerson(imSppdEntity.getTipePerson());
                if(imSppdEntity.getApprovalId() != null){
                    itemComboSppd.setSppdApprove(true);
                    if(imSppdEntity.getPejabatSementara() != null){
                        ItPersonilPositionEntity itPersonilPositionEntity = null;
                        itPersonilPositionEntity = personilPositionDao.getById("nip",imSppdEntity.getPejabatSementara(),"Y" );
                        itemComboSppd.setPejabatSementaraNama(itPersonilPositionEntity.getImBiodataEntity().getNamaPegawai());
                        itemComboSppd.setPejabatSementaraBranch(itPersonilPositionEntity.getBranchId());

                        ImPosition imPosition = positionDao.getById("positionId",itPersonilPositionEntity.getPositionId());
                        itemComboSppd.setPejabatSementaraDivisi(imPosition.getDepartmentId());
                        itemComboSppd.setPejabatSementaraPosition(itPersonilPositionEntity.getPositionId());
                    }
                    if(imSppdEntity.getNotApprovalNote() != null){
                        itemComboSppd.setNotApprovalNote(imSppdEntity.getNotApprovalNote());
                    }
                }
                if(imSppdEntity.getApprovalSdmId() != null){
                    itemComboSppd.setSppdApproveSdm(true);
                }
                if(imSppdEntity.getApprovalFlag().equals("Y")){
                    itemComboSppd.setSppdApproveStatus(true);
                }
                if(imSppdEntity.getApprovalSdmFlag().equals("Y")){
                    itemComboSppd.setSppdApproveSdmStatus(true);
                }

                if(imSppdEntity.getPejabatSementara() != null){
                    itemComboSppd.setPejabatSementara(imSppdEntity.getPejabatSementara());
                    itemComboSppd.setPejabatSementaraNama(imSppdEntity.getImBiodataEntity().getNamaPegawai());
                }else{
                    itemComboSppd.setPejabatSementara("");
                    itemComboSppd.setPejabatSementaraNama("");
                }


                listComboSppd.add(itemComboSppd);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSppd;
    }

    public List<SppdDoc> getComboSppdDocument(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SppdDoc> listComboSppd = new ArrayList();

        List<ItSppdDocEntity> itSppdDocEntities = null;
        try {
            itSppdDocEntities = sppdDocDao.getListSppdPerson(query);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (itSppdDocEntities != null) {
            for (ItSppdDocEntity itSppdDocEntity : itSppdDocEntities) {
                SppdDoc sppdDoc = new SppdDoc();
                sppdDoc.setDocSppdId(itSppdDocEntity.getDocSppdId());
                sppdDoc.setSppdId(itSppdDocEntity.getSppdId());
                sppdDoc.setFileType(itSppdDocEntity.getFileType());
                sppdDoc.setFileUploadDoc(itSppdDocEntity.getFileUploadDoc());
                sppdDoc.setNote(itSppdDocEntity.getNote());

                listComboSppd.add(sppdDoc);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSppd;
    }

    @Override
    public String getNextId() throws GeneralBOException {
        String docId;
        try {
            // Generating ID, get from postgre sequence
            docId = sppdDocDao.getNextSppdDoc();
        } catch (HibernateException e) {
            logger.error("[DepartmentBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence departmentId id, please info to your admin..." + e.getMessage());
        }
        return docId;
    }

    @Override
    public List<Object[]> findInfoSppd(String idSppd, String nip) throws GeneralBOException {
        logger.info("[SppdBoImpl.findInfoSppd] start process >>>");
        List<Object[]> listSppd = null;

        try {
            listSppd = sppdDao.findInfoSppd(idSppd, nip);
        } catch (HibernateException e) {
            logger.error("[SppdBoImpl.findInfoSppd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[SppdBoImpl.findInfoSppd] end process <<<");
        return listSppd;
    }

    @Override
    public List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws GeneralBOException {
        logger.info("[SppdBoImpl.findAllConfirmByIdAtasan] start process >>>");
        List<Object[]> listConfirmStatus = null;

        try {
            listConfirmStatus = sppdDao.findAllConfirmByIdAtasan(nip, flag);
        } catch (HibernateException e) {
            logger.error("[SppdBoImpl.findAllConfirmByIdAtasan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[SppdBoImpl.findAllConfirmByIdAtasan] end process <<<");
        return listConfirmStatus;
    }

    public SppdDoc addSppdDoc(SppdDoc bean) throws GeneralBOException {
        logger.info("[DepartmentBoImpl.saveAdd] start process >>>");

        if (bean!=null) {



            // creating object entity serializable
            ItSppdDocEntity itSppdDocEntity = new ItSppdDocEntity();

            itSppdDocEntity.setDocSppdId(bean.getDocSppdId());
            itSppdDocEntity.setFileUploadDoc(bean.getFileUploadDoc());
            itSppdDocEntity.setSppdId(bean.getSppdId());
            itSppdDocEntity.setSppdPersonId(bean.getSppdPerson());
            itSppdDocEntity.setNote(bean.getNote());
            itSppdDocEntity.setFileType(bean.getFileType());
            itSppdDocEntity.setFlag(bean.getFlag());
            itSppdDocEntity.setAction(bean.getAction());
            itSppdDocEntity.setCreatedWho(bean.getCreatedWho());
            itSppdDocEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itSppdDocEntity.setCreatedDate(bean.getCreatedDate());
            itSppdDocEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                sppdDocDao.addAndSave(itSppdDocEntity);
            } catch (HibernateException e) {
                logger.error("[DepartmentBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Department, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[DepartmentBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SppdPerson> getByCriteriaForAbsensi(SppdPerson bean, String tanggal){
        logger.info("[LemburBoImpl.saveAdd] start process >>>");
        List<SppdPerson> listOfResult = new ArrayList();

        if (bean != null) {
            List<ItSppdPersonEntity> sppdPersonEntityList = new ArrayList<>();
            sppdPersonEntityList = sppdPersonDao.getListPersonalFromNip(bean.getPersonId(),CommonUtil.convertToDate(tanggal));
            for (ItSppdPersonEntity itSppdPersonEntity:sppdPersonEntityList){
                SppdPerson returnSppdPerson = new SppdPerson();
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                returnSppdPerson.setStTanggalBerangkat(df.format(itSppdPersonEntity.getTanggalBerangkat()));
                returnSppdPerson.setStTanggalKembali(df.format(itSppdPersonEntity.getTanggalKembali()));

                listOfResult.add(returnSppdPerson);
            }
        }
        return listOfResult;
    }

    @Override
    public void removeTanggalSppd(String sppdPersonId) throws GeneralBOException {
        List<ItSppdTanggalEntity> itSppdTanggalEntities = sppdTanggalDao.getDataSppdTanggal(sppdPersonId);

        if(itSppdTanggalEntities != null){
            for(ItSppdTanggalEntity itSppdTanggalEntity : itSppdTanggalEntities){
                itSppdTanggalEntity.setSppdTanggalApprove("N");

                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                itSppdTanggalEntity.setCreatedDate(updateTime);
                itSppdTanggalEntity.setCreatedWho(userLogin);
                itSppdTanggalEntity.setLastUpdateWho(userLogin);
                itSppdTanggalEntity.setLastUpdate(updateTime);

                sppdTanggalDao.updateAndSave(itSppdTanggalEntity);
            }
        }
    }

    @Override
    public void updateTanggalSppd(String idTanggalSppd, int jumlahTanggal, int indexTanggal, BigDecimal biayaLain, BigDecimal biayaMakan) throws GeneralBOException {
        ItSppdTanggalEntity itSppdTanggalEntities = sppdTanggalDao.getById("idSppdTanggal", idTanggalSppd);

        if(itSppdTanggalEntities != null){
            itSppdTanggalEntities.setSppdTanggalApprove("Y");
            itSppdTanggalEntities.setBiayaLain(biayaLain);
            itSppdTanggalEntities.setBiayaMakan(biayaMakan);
            itSppdTanggalEntities.setSppdTanggalApprove("Y");
            itSppdTanggalEntities.setFlag("Y");
            itSppdTanggalEntities.setAction("U");

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            itSppdTanggalEntities.setCreatedDate(updateTime);
            itSppdTanggalEntities.setCreatedWho(userLogin);
            itSppdTanggalEntities.setLastUpdateWho(userLogin);
            itSppdTanggalEntities.setLastUpdate(updateTime);
            sppdTanggalDao.updateAndSave(itSppdTanggalEntities);
        }
    }

    @Override
    public List<TrainingPerson> cekIdTrainingSys(String idTraining) throws GeneralBOException {
        List<ItHrisTrainingEntity>listTraining = new ArrayList<>();
        List<ItHrisTrainingEntity> itDetailTraining = new ArrayList<>();
        List<ItHrisTrainingPersonEntity>cekApproveSdm= new ArrayList<>();
        List<ItHrisTrainingPersonEntity>listTrainingPerson = new ArrayList<>();
        List<TrainingPerson>hasilTrainingPerson = new ArrayList<>();
        List<SppdPerson>listComboSppdPerson = new ArrayList<>();
        List<ItPersonilPositionEntity>itPersonilPosition = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();

        itDetailTraining = trainingDao.getListTrainingById(idTraining);
        cekApproveSdm = trainingPersonDao.cekPersonApprove(idTraining);
        listTrainingPerson = trainingPersonDao.getListTrainingById(idTraining);

        int index = 0;
        if(cekApproveSdm.size() == 0){
            for(ItHrisTrainingEntity detailTraining: itDetailTraining){
                if(listTrainingPerson != null){
                    for(ItHrisTrainingPersonEntity resultTrainingPerson : listTrainingPerson ){
                        itPersonilPosition = personilPositionDao.getListPersonilPosition(resultTrainingPerson.getPersonId());
                        String posisiId = "";
                        String posisiName = "";
                        String divisiId = "";
                        String divisiName = "";
                        if(itPersonilPosition.size() > 0){
                            for(ItPersonilPositionEntity detailPerson: itPersonilPosition){
                                posisiId = detailPerson.getPositionId();
                                posisiName = detailPerson.getImPosition().getPositionName();
                                divisiId = detailPerson.getImPosition().getDepartmentId();
                                divisiName = detailPerson.getImPosition().getImDepartmentEntity().getDepartmentName();
                            }
                        }

                        TrainingPerson trainingPerson = new TrainingPerson();
                        trainingPerson.setPersonId(resultTrainingPerson.getPersonId());
                        trainingPerson.setPersonName(resultTrainingPerson.getPersonName());
                        trainingPerson.setUnitId(detailTraining.getUnitId());
                        trainingPerson.setUnitName(detailTraining.getImBranches().getBranchName());
                        trainingPerson.setDivisiId(divisiId);
                        trainingPerson.setDivisiName(divisiName);
                        trainingPerson.setPositionId(posisiId);
                        trainingPerson.setPositionName(posisiName);
                        trainingPerson.setStrTanggalBerangkat(CommonUtil.convertDateToString(detailTraining.getTrainingStartDate()));
                        trainingPerson.setStrTanggalKembali(CommonUtil.convertDateToString(detailTraining.getTrainingEndDate()));
                        trainingPerson.setSppdBerangkatDari("3578");//berangkat dari Kota Surabaya
                        trainingPerson.setSppdBerangkatDariName("Kota Surabaya");//berangkat dari Kota Surabaya
                        trainingPerson.setKota(detailTraining.getKota());
                        trainingPerson.setSppdTujuanName(detailTraining.getImKotaEntity().getKotaName());//Tujuan Name
                        trainingPerson.setSppdBerangkatVia("Mobil");
                        trainingPerson.setSppdKembaliVia("Mobil");
                        trainingPerson.setSppdKeperluan(detailTraining.getTrainingName());

                        if(index == 0){
                            trainingPerson.setPersonSppdName(resultTrainingPerson.getPersonId() + " || " + resultTrainingPerson.getPersonName());
                            trainingPerson.setSppdStatus("Ketua");
                        }else{//Jika anggota sppd, masukkan kedalam session
                            trainingPerson.setSppdStatus("Anggota");

                            SppdPerson sppdPerson = new SppdPerson();

                            sppdPerson.setPersonId(trainingPerson.getPersonId());
                            sppdPerson.setPersonName(trainingPerson.getPersonName());
                            sppdPerson.setBranchId(trainingPerson.getUnitId());
                            sppdPerson.setBranchName(trainingPerson.getUnitName());
                            sppdPerson.setPositionId(trainingPerson.getPositionId());
                            sppdPerson.setPositionName(trainingPerson.getPositionName());
                            sppdPerson.setDivisiId(trainingPerson.getDivisiId());
                            sppdPerson.setDivisiName(trainingPerson.getDivisiName());
                            sppdPerson.setBerangkatDari(trainingPerson.getSppdBerangkatDari());
                            sppdPerson.setBerangkatDariNama(trainingPerson.getSppdBerangkatDariName());
                            sppdPerson.setTujuanKe(trainingPerson.getKota());
                            sppdPerson.setTujuanKeNama(trainingPerson.getSppdTujuanName());
                            sppdPerson.setBerangkatVia("Mobil");
                            sppdPerson.setKembaliVia("Mobil");
                            sppdPerson.setPenginapan("");
                            sppdPerson.setStTanggalBerangkat(trainingPerson.getStrTanggalBerangkat());
                            sppdPerson.setStTanggalKembali(trainingPerson.getStrTanggalKembali());

                            //HttpSession session = ServletActionContext.getRequest().getSession();
                            listComboSppdPerson = (List<SppdPerson>) session.getAttribute("listOfResultSppdPerson");
                            if(listComboSppdPerson != null){
                                listComboSppdPerson.add(sppdPerson);
                            }else{
                                listComboSppdPerson = new ArrayList();
                                listComboSppdPerson.add(sppdPerson);
                            }
                        }

                        hasilTrainingPerson.add(trainingPerson);
                        index++;
                    }
                }
            }
        }

        session.setAttribute("listOfResultSppdPerson", listComboSppdPerson);
        return hasilTrainingPerson;
    }

    @Override
    public String cekAvailableSppdSys(String nip, String tanggal1, String tanggal2) throws GeneralBOException {
        List<SppdPerson>cekSppdAvailable = new ArrayList<>();
        List<ItSppdPersonEntity>itSppdPerson = new ArrayList<>();
        List<ItCutiPegawaiEntity>itCutiPegawaiEntities = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SppdPerson> listOfsearchSppdPerson = (List<SppdPerson>) session.getAttribute("listOfResultSppdPerson");

        String hasil = "-";
        String hasilCuti = "-";
        String tgl1Cuti = "-";
        String tgl2Cuti = "-";
        Date date1 = CommonUtil.convertStringToDate(tanggal1);
        Date date2 = CommonUtil.convertStringToDate(tanggal2);
        itSppdPerson = sppdPersonDao.getListSppdPerson(nip, date1, date2);
        itCutiPegawaiEntities = cutiPegawaiDao.cekCutiPegawaiForSPPD(nip, date1, date2);

        if(itSppdPerson.size() > 0){
            for(ItSppdPersonEntity itSppdPersonEntity : itSppdPerson){
                hasil = itSppdPersonEntity.getPersonName() ;
            }
        }

        if(listOfsearchSppdPerson != null){
            for(SppdPerson sppdPerson: listOfsearchSppdPerson){
                itSppdPerson = sppdPersonDao.getListSppdPerson(sppdPerson.getPersonId(),
                        CommonUtil.convertStringToDate(sppdPerson.getStTanggalBerangkat()),
                        CommonUtil.convertStringToDate(sppdPerson.getStTanggalKembali()));
                if(itSppdPerson.size() > 0){
                    for(ItSppdPersonEntity itSppdPersonEntity: itSppdPerson){
                        hasil += ", " + itSppdPersonEntity.getPersonName();
                    }
                }
            }
        }

        if(!"-".equalsIgnoreCase(hasil)){
            hasil += " Sudah terdaftar SPPD antara tanggal " + tanggal1 + " dan " + tanggal2;
        }

        if(itCutiPegawaiEntities.size() > 0){
            for(ItCutiPegawaiEntity itCutiPegawaiEntity : itCutiPegawaiEntities){
                hasilCuti = itCutiPegawaiEntity.getImBiodataEntity().getNamaPegawai() ;
                tgl1Cuti = CommonUtil.convertDateToString(itCutiPegawaiEntity.getTanggalDari()) ;
                tgl2Cuti = CommonUtil.convertDateToString(itCutiPegawaiEntity.getTanggalSelesai()) ;
            }
        }

        if("-".equalsIgnoreCase(hasil)){
            if(!"-".equalsIgnoreCase(hasilCuti)){
                hasil = hasilCuti +" Sedang mengajukan cuti pada tanggal " + tgl1Cuti + " sampai dengan " + tgl2Cuti;
            }
        }

        return hasil;
    }

    // mengecek SPPD tersedia pada tanggal tertentu untuk edit tanggal pada menu edit SPPD
    public String cekAvailableSppdSys(String nip, String tanggal1, String tanggal2, String sppdId){
        List<SppdPerson>cekSppdAvailable = new ArrayList<>();
        List<ItSppdPersonEntity>itSppdPerson = new ArrayList<>();
        List<ItCutiPegawaiEntity>itCutiPegawaiEntities = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();

        String hasil = "-";
        String hasilCuti = "-";
        String tgl1Cuti = "-";
        String tgl2Cuti = "-";
        Date date1 = CommonUtil.convertStringToDate(tanggal1);
        Date date2 = CommonUtil.convertStringToDate(tanggal2);
        itSppdPerson = sppdPersonDao.getListSppdPerson(nip, date1, date2, sppdId);
        itCutiPegawaiEntities = cutiPegawaiDao.cekCutiPegawaiForSPPD(nip, date1, date2);

        if(itSppdPerson.size() > 0){
            for(ItSppdPersonEntity itSppdPersonEntity : itSppdPerson){
                hasil = itSppdPersonEntity.getPersonName() ;
            }
        }

        if(!"-".equalsIgnoreCase(hasil)){
            hasil += " Sudah terdaftar di SPPD Lain antara tanggal " + tanggal1 + " dan " + tanggal2;
        }

        if(itCutiPegawaiEntities.size() > 0){
            for(ItCutiPegawaiEntity itCutiPegawaiEntity : itCutiPegawaiEntities){
                hasilCuti = itCutiPegawaiEntity.getImBiodataEntity().getNamaPegawai() ;
                tgl1Cuti = CommonUtil.convertDateToString(itCutiPegawaiEntity.getTanggalDari()) ;
                tgl2Cuti = CommonUtil.convertDateToString(itCutiPegawaiEntity.getTanggalSelesai()) ;
            }
        }

        if("-".equalsIgnoreCase(hasil)){
            if(!"-".equalsIgnoreCase(hasilCuti)){
                hasil = hasilCuti +" Sedang mengajukan cuti pada tanggal " + tgl1Cuti + " sampai dengan " + tgl2Cuti;
            }
        }

        return hasil;
    }

    @Override
    public void addAnggotaSys(SppdPerson sppdPerson) throws GeneralBOException {
        String sppdPersonId = sppdPersonDao.getNextSppdPersonId();
        ItSppdPersonEntity itSppdPersonEntityAnggota = new ItSppdPersonEntity();

        //mengambil data dari struktur jabatan
        Map hashMap         = new HashMap();
        if (sppdPerson.getBranchId() != null && !"".equalsIgnoreCase(sppdPerson.getBranchId())) {
            hashMap.put("branch_id", sppdPerson.getBranchId());
        }
        if (sppdPerson.getPositionId() != null ) {
            hashMap.put("position_id", sppdPerson.getPositionId());
        }
        hashMap.put("flag", "Y");

        List<ImStrukturJabatanEntity> imStrukturJabatanEntities = null;
        try {
            imStrukturJabatanEntities = strukturJabatanDao.getByCriteriaNotif(hashMap);
        } catch (HibernateException e) {
            logger.error("[SppdBoImpl.getSearchSppdByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        String kelompokId = "";
        if(imStrukturJabatanEntities != null){
            Map hsCriteriaUser = new HashMap();
            for (ImStrukturJabatanEntity imStrukturJabatanEntity: imStrukturJabatanEntities) {
                hsCriteriaUser.put("branch_id", imStrukturJabatanEntity.getBranchId());
                hsCriteriaUser.put("position_id", imStrukturJabatanEntity.getPositionId());
                hsCriteriaUser.put("flag", "Y");
                List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
                itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteriaUser);
                if(imStrukturJabatanEntities != null){
                    for (ItPersonilPositionEntity itPersonilPositionEntity: itPersonilPositionEntities) {
                        String notifId = notifikasiDao.getNextNotifikasiId();
                        ImNotifikasiEntity itNotifEntity = new ImNotifikasiEntity();

                        itNotifEntity.setNotifId(notifId);
                        itNotifEntity.setTipeNotifId("TI");
                        itNotifEntity.setTipeNotifName("SPPD");
                        itNotifEntity.setNote("SPPD dengan No " + sppdPerson.getSppdId());
                        itNotifEntity.setRead("Y");
                        itNotifEntity.setFromPerson(sppdPerson.getPersonId());
                        itNotifEntity.setNoRequest(sppdPerson.getSppdId());
                        itNotifEntity.setFlag(sppdPerson.getFlag());
                        itNotifEntity.setAction(sppdPerson.getAction());
                        itNotifEntity.setCreatedWho(sppdPerson.getCreatedWho());
                        itNotifEntity.setLastUpdateWho(sppdPerson.getLastUpdateWho());
                        itNotifEntity.setCreatedDate(sppdPerson.getCreatedDate());
                        itNotifEntity.setLastUpdate(sppdPerson.getLastUpdate());
                        itNotifEntity.setNip( itPersonilPositionEntity.getNip());

                        itNotifEntity.setFlag(sppdPerson.getFlag());
                        itNotifEntity.setAction(sppdPerson.getAction());
                        itNotifEntity.setCreatedWho(sppdPerson.getCreatedWho());
                        itNotifEntity.setCreatedDate(sppdPerson.getCreatedDate());
                        itNotifEntity.setLastUpdate(sppdPerson.getLastUpdate());
                        itNotifEntity.setLastUpdateWho(sppdPerson.getLastUpdateWho());

                        List<ItNotifikasiFcmEntity> notifikasiFcm = null;

                        try {
                            notifikasiDao.addAndSave(itNotifEntity);
                            notifikasiFcm = notifikasiFcmDao.getAll();
                        } catch (HibernateException e) {
                            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }

                        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
                            if(entity.getUserId().equals(itPersonilPositionEntity.getNip())){
                                FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), itNotifEntity.getTipeNotifName(), itNotifEntity.getNote(), CLICK_ACTION, entity.getOs(), null);
                                break;
                            }
                        }

//                        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                            if(entity.getUserId().equals(itPersonilPositionEntity.getNip())){
//                                ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), itNotifEntity.getTipeNotifName(), itNotifEntity.getNote(), entity.getOs());
//                                break;
//                            }
//                        }


                    }
                }
            }
        }
        //end notifikasi


        if (sppdPerson.getStTanggalBerangkat() != null && !"".equalsIgnoreCase(sppdPerson.getStTanggalBerangkat())) {
            sppdPerson.setTanggalBerangkat(CommonUtil.convertToDate(sppdPerson.getStTanggalBerangkat()));
            sppdPerson.setTanggalKembali(CommonUtil.convertToDate(sppdPerson.getStTanggalKembali()));
        }

        sppdPerson.setTanggalBerangkatRealisasi(sppdPerson.getTanggalBerangkat());
        sppdPerson.setTanggalKembaliRealisasi(sppdPerson.getTanggalKembali());

        itSppdPersonEntityAnggota.setSppdId(sppdPerson.getSppdId());
        itSppdPersonEntityAnggota.setSppdPersonId(sppdPersonId);
        sppdPerson.setSppdPersonId(sppdPersonId);
        sppdPerson.setDinas(sppdPerson.getDinas());
        itSppdPersonEntityAnggota.setPersonId(sppdPerson.getPersonId());
        itSppdPersonEntityAnggota.setPersonName(sppdPerson.getPersonName());
        itSppdPersonEntityAnggota.setBranchId(sppdPerson.getBranchId());
        itSppdPersonEntityAnggota.setBranchName(sppdPerson.getBranchName());
        itSppdPersonEntityAnggota.setPositionId(sppdPerson.getPositionId());
        itSppdPersonEntityAnggota.setPositionName(sppdPerson.getPositionName());
        itSppdPersonEntityAnggota.setDivisiId(sppdPerson.getDivisiId());
        itSppdPersonEntityAnggota.setDivisiName(sppdPerson.getDivisiName());
        itSppdPersonEntityAnggota.setTipePerson("Anggota");
        itSppdPersonEntityAnggota.setApprovalFlag("N");
        itSppdPersonEntityAnggota.setApprovalSdmFlag("N");
        itSppdPersonEntityAnggota.setTrainingFlag("N");
        itSppdPersonEntityAnggota.setTrainingId("");

        itSppdPersonEntityAnggota.setBerangkatDari(sppdPerson.getBerangkatDari());
        itSppdPersonEntityAnggota.setTujuanKe(sppdPerson.getTujuanKe());
        itSppdPersonEntityAnggota.setTanggalBerangkat(sppdPerson.getTanggalBerangkat());
        itSppdPersonEntityAnggota.setTanggalKembali(sppdPerson.getTanggalKembali());
        itSppdPersonEntityAnggota.setTanggalBerangkatRealisasi(sppdPerson.getTanggalBerangkat());
        itSppdPersonEntityAnggota.setTanggalKembaliRealisasi(sppdPerson.getTanggalKembali());
        itSppdPersonEntityAnggota.setBerangkatVia(sppdPerson.getBerangkatVia());
        itSppdPersonEntityAnggota.setKembaliVia(sppdPerson.getKembaliVia());
        itSppdPersonEntityAnggota.setPenginapan(sppdPerson.getPenginapan());

        ImBiodataEntity detailBiodata = biodataDao.getById("nip", sppdPerson.getPersonId());

        List<ItPersonilPositionEntity> personilPositionEntity = personilPositionDao.getListPersonilPosition(sppdPerson.getPersonId());

        String golonganId = "";
        if(!"".equalsIgnoreCase(detailBiodata.getGolongan()) && detailBiodata != null){
            golonganId = detailBiodata.getGolongan();
        }

        BigDecimal biayaMakan = new BigDecimal(0);
        BigDecimal biayaLain = new BigDecimal(0);

        if(personilPositionEntity.size() > 0){
            for(ItPersonilPositionEntity itPersonilPositionEntity: personilPositionEntity){
                kelompokId = itPersonilPositionEntity.getImPosition().getKelompokId();
            }
            //Biaya makan BPD01
            biayaMakan = getBiayaMakan(golonganId, kelompokId, sppdPerson.getDinas());

            //Biaya Lain BPD02
            biayaLain = getBiayaLain(golonganId, kelompokId, sppdPerson.getDinas());

            sppdPerson.setGolonganId(golonganId);
            sppdPerson.setKelompokId(kelompokId);

            itSppdPersonEntityAnggota.setGolonganId(golonganId);
            itSppdPersonEntityAnggota.setKelompokId(kelompokId);
        }

        itSppdPersonEntityAnggota.setBiayaLain(biayaLain);
        itSppdPersonEntityAnggota.setBiayaMakan(biayaMakan);

        itSppdPersonEntityAnggota.setBiayaLokalBerangkat(biayaTransportLokal(sppdPerson.getBerangkatVia()));
        itSppdPersonEntityAnggota.setBiayaTujuan(BigDecimal.valueOf(0));
        itSppdPersonEntityAnggota.setBiayaLokalKembali(biayaTransportLokal(sppdPerson.getBerangkatVia()));

        itSppdPersonEntityAnggota.setTiketPergi(BigDecimal.valueOf(0));
        itSppdPersonEntityAnggota.setTiketKembali(BigDecimal.valueOf(0));
        itSppdPersonEntityAnggota.setBiayaTambahan(BigDecimal.valueOf(0));

        itSppdPersonEntityAnggota.setFlag(sppdPerson.getFlag());
        itSppdPersonEntityAnggota.setAction(sppdPerson.getAction());
        itSppdPersonEntityAnggota.setCreatedWho(sppdPerson.getCreatedWho());
        itSppdPersonEntityAnggota.setLastUpdateWho(sppdPerson.getLastUpdateWho());
        itSppdPersonEntityAnggota.setCreatedDate(sppdPerson.getCreatedDate());
        itSppdPersonEntityAnggota.setLastUpdate(sppdPerson.getLastUpdate());
        sppdPersonDao.addAndSave(itSppdPersonEntityAnggota);
        insertSppdTanggal(sppdPerson);
    }

    @Override
    public void deleteAnggotaSys(String sppdId, String nip) throws GeneralBOException {
        List<ItSppdPersonEntity>sppdPersonList = new ArrayList<>();
        ItSppdPersonEntity itSppdPersonEntity = new ItSppdPersonEntity();
        String sppdPersonId = "";

        sppdPersonList = sppdPersonDao.getListSppdPerson1(sppdId, nip);
        if(sppdPersonList.size() > 0){
            for(ItSppdPersonEntity itSppdPersonEntity1 : sppdPersonList){
                sppdPersonId = itSppdPersonEntity1.getSppdPersonId();
            }
            itSppdPersonEntity = sppdPersonDao.getById("sppdPersonId", sppdPersonId);
            itSppdPersonEntity.setFlag("N");

            sppdPersonDao.updateAndSave(itSppdPersonEntity);

            //delete from notif
            if (("N").equalsIgnoreCase(itSppdPersonEntity.getFlag())){
                List<ImNotifikasiEntity> notifikasiEntityList = notifikasiDao.getDataByNoRequest(itSppdPersonEntity.getSppdId(),itSppdPersonEntity.getPersonId());

                if (notifikasiEntityList!=null){
                    for (ImNotifikasiEntity notifikasiEntity : notifikasiEntityList){
                        notifikasiEntity.setFlag("N");

                        try {
                            // Update into database
                            notifikasiDao.updateAndSave(notifikasiEntity);
                        } catch (HibernateException e) {
                            logger.error("[SppdBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    //ambil kota tujuan SPPD, termasuk reroute
    @Override
    public String getTujuanSppd(String sppdId) throws GeneralBOException {
        int i = 1 ;
        String hasil = "";
        List<ItSppdPersonEntity> itSppdPersonEntities = sppdPersonDao.getListSppdPerson(sppdId, "Ketua");
        if(itSppdPersonEntities.size() > 0){
            for(ItSppdPersonEntity itSppdPersonEntity: itSppdPersonEntities){
                hasil = itSppdPersonEntity.getImKotaEntity2().getKotaName();
                i++;
            }
        }

        List<ItSppdRerouteEntity> itSppdRerouteEntityList = sppdRerouteDao.getTujuanReroute(sppdId);
        if(itSppdRerouteEntityList.size() > 0){
            hasil = "1. " + hasil;
            for(ItSppdRerouteEntity itSppdRerouteEntity: itSppdRerouteEntityList){
                hasil = hasil + ", " + i + ". " + itSppdRerouteEntity.getKota();
                i++;
            }
        }
        return hasil;
    }

    @Override
    public void deleteSppdSys(String sppdId) throws GeneralBOException {
        ImSppdEntity sppdEntity = new ImSppdEntity();
        List<ItSppdPersonEntity> sppdPersonEntity = new ArrayList<>();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        sppdEntity = sppdDao.getById("sppdId", sppdId);
        if(sppdEntity != null){
            sppdPersonEntity = sppdPersonDao.getListPerson(sppdId);
            if(sppdPersonEntity.size() > 0){
                for(ItSppdPersonEntity itSppdPersonEntity: sppdPersonEntity){
                    itSppdPersonEntity.setFlag("N");
                    sppdPersonDao.updateAndSave(itSppdPersonEntity);
                }
            }

            sppdEntity.setFlag("N");
            sppdEntity.setAction("U");
            sppdEntity.setLastUpdateWho(CommonUtil.userLogin());
            sppdEntity.setLastUpdate(updateTime);

            sppdDao.updateAndSave(sppdEntity);

            //delete from notif
            if (("N").equalsIgnoreCase(sppdEntity.getFlag())){
                List<ImNotifikasiEntity> notifikasiEntityList = notifikasiDao.getDataByNoRequest(sppdEntity.getSppdId());

                if (notifikasiEntityList!=null){
                    for (ImNotifikasiEntity notifikasiEntity : notifikasiEntityList){
                        notifikasiEntity.setFlag("N");
                        try {
                            // Update into database
                            notifikasiDao.updateAndSave(notifikasiEntity);
                        } catch (HibernateException e) {
                            logger.error("[SppdBoImpl.deleteSppdSys] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<SppdPerson> anggotaSppd(String sppdId) throws GeneralBOException {
        List<SppdPerson> sppdPersons = new ArrayList<>();

        sppdPersons = sppdDao.anggotaSppdDao(sppdId);

        return sppdPersons;
    }

    private BigDecimal getBiayaLain(String golongan, String kelompok, String dinas){
        BigDecimal biayaLain = new BigDecimal(0);
        List<ImPerjalananDinasEntity> imPerjalananDinasEntities = new ArrayList<>();

        if(!"KL07".equalsIgnoreCase(kelompok) && !"KL08".equalsIgnoreCase(kelompok)){
            imPerjalananDinasEntities = perjalananDinasDao.getListBiaya(kelompok, golongan, "BPD02", dinas);
        }else{
            if("KL08".equalsIgnoreCase(kelompok)){
                golongan = "G01";
            }
            imPerjalananDinasEntities = perjalananDinasDao.getListBiaya(golongan, "BPD02", dinas);
        }

        for (ImPerjalananDinasEntity imPerjalananDinasEntity : imPerjalananDinasEntities) {
            biayaLain = imPerjalananDinasEntity.getJumlahBiaya();
        }
        return biayaLain;
    }

    private BigDecimal getBiayaMakan(String golongan, String kelompok, String dinas){
        BigDecimal biayaMakan = new BigDecimal(0);
        List<ImPerjalananDinasEntity> imPerjalananDinasEntities = new ArrayList<>();

        if(!"KL07".equalsIgnoreCase(kelompok) && !"KL08".equalsIgnoreCase(kelompok)){
            imPerjalananDinasEntities = perjalananDinasDao.getListBiaya(kelompok, golongan, "BPD01", dinas);
        }else{
            if("KL08".equalsIgnoreCase(kelompok)){
                golongan = "G01";
            }
            imPerjalananDinasEntities = perjalananDinasDao.getListBiaya(golongan, "BPD01", dinas);
        }

        for (ImPerjalananDinasEntity imPerjalananDinasEntity : imPerjalananDinasEntities) {
            biayaMakan = imPerjalananDinasEntity.getJumlahBiaya();
        }
        return biayaMakan;
    }

    @Override
    public List<Sppd> getSppdTahun(String tahun) throws GeneralBOException {
        List<Sppd> hasilSppd = new ArrayList<>();

        for(int bulan = 1; bulan <= 12; bulan++){
            List<Sppd> sppdList = new ArrayList<>();
            sppdList = sppdDao.getSppdTahun(tahun, bulan + "");

            hasilSppd.addAll(sppdList);
        }

        return hasilSppd;
    }

    @Override
    public List<Sppd> getSppdTahun(String tahun, String divisi) throws GeneralBOException {
        List<Sppd> hasilSppd = new ArrayList<>();

        for(int bulan = 1; bulan <= 12; bulan++){
            List<Sppd> sppdList = new ArrayList<>();
            sppdList = sppdDao.getSppdTahun(tahun, bulan + "", divisi);

            hasilSppd.addAll(sppdList);
        }

        return hasilSppd;
    }

    @Override
    public List<Sppd> getSppdTahunByDivisi(String tahun, String bulan) throws GeneralBOException {
        List<Sppd> hasilSppd = new ArrayList<>();

        List<Sppd> sppdList = new ArrayList<>();
        sppdList = sppdDao.getSppdTahunDivisi(tahun, bulan);

        hasilSppd.addAll(sppdList);

        return hasilSppd;
    }

    @Override
    public List<Sppd> getSppdTahunByNip(String tahun, String bulan, String divisiName) throws GeneralBOException {
        List<Sppd> hasilSppd = new ArrayList<>();
        String divisiId = "";
        if(divisiName != null && !divisiName.equalsIgnoreCase("")){
            if(!divisiName.equalsIgnoreCase("Kandir")){
                //divisiId =
                Map hsCriteria = new HashMap();
                List<ImDepartmentEntity> imDepartmentEntity = departmentDao.getListDepartment(divisiName);
                if(imDepartmentEntity.size() > 0){
                    for(ImDepartmentEntity departmentLoop: imDepartmentEntity){
                        divisiId = departmentLoop.getDepartmentId();
                    }
                }
            }
        }

        List<Sppd> sppdList = new ArrayList<>();
        sppdList = sppdDao.getSppdTahunPerson(tahun, bulan, divisiId);

        hasilSppd.addAll(sppdList);

        return hasilSppd;
    }

    @Override
    public List<Sppd> getSppdTahunBagian(String tahun, String bulan) throws GeneralBOException {
        List<Sppd> hasilSppd = new ArrayList<>();

        List<Sppd> sppdList = new ArrayList<>();
        sppdList = sppdDao.getSppdTahunBagian(tahun, bulan);

        hasilSppd.addAll(sppdList);

        return hasilSppd;
    }

    @Override
    public List<Sppd> getSppdTahunBagian(String tahun, String bulan, String divisiId) throws GeneralBOException {
        List<Sppd> hasilSppd = new ArrayList<>();

        List<Sppd> sppdList = new ArrayList<>();
        sppdList = sppdDao.getSppdTahunBagian(tahun, bulan, divisiId);

        hasilSppd.addAll(sppdList);

        return hasilSppd;
    }

    @Override
    public List<Sppd> getSppdTahunByBagian(String tahun, String bagian) throws GeneralBOException {
        List<Sppd> hasilSppd = new ArrayList<>();

        for(int bulan = 1; bulan <= 12; bulan++){
            List<Sppd> sppdList = new ArrayList<>();
            sppdList = sppdDao.getSppdTahunByBagian(tahun, bulan + "", bagian);

            hasilSppd.addAll(sppdList);
        }

        return hasilSppd;
    }

    @Override
    public List<Sppd> getBagian(String tahun, String divisiId) throws GeneralBOException {
        List<Sppd> hasilSppd = new ArrayList<>();
        for(int bulan = 1; bulan <= 12; bulan++){
            List<Sppd> sppdList = new ArrayList<>();
            sppdList = sppdDao.getSppdTahun(tahun, bulan + "", divisiId);

            hasilSppd.addAll(sppdList);
        }
        return hasilSppd;
    }

    // Cek Dokumen Training
    @Override
    public String cekDokumenTraining(String sppdId) throws GeneralBOException {
        List<SppdPerson> sppdList = new ArrayList<>();
        //sppdList = sppdDao.getDokumenTraining(sppdId);

        //if(getDokumenTraining)
        return "";
    }
}