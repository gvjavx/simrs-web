package com.neurix.hris.transaksi.rekruitmen.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.perjalananDinas.dao.PerjalananDinasDao;
import com.neurix.hris.master.perjalananDinas.model.ImPerjalananDinasEntity;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;
import com.neurix.hris.master.provinsi.model.ImProvinsiEntity;
import com.neurix.hris.master.rsKerjasama.dao.RsKerjasamaDao;
import com.neurix.hris.master.rsKerjasama.model.ImRsKerjasamaEntity;
import com.neurix.hris.master.rskelas.dao.RsKelasDao;
import com.neurix.hris.master.rskelas.model.ImHrisRsKelas;
import com.neurix.hris.master.statusRekruitment.dao.StatusRekruitmentDao;
import com.neurix.hris.master.statusRekruitment.model.ImStatusRekruitmentEntity;
import com.neurix.hris.master.study.dao.StudyDao;
import com.neurix.hris.master.study.model.ImStudyEntity;
import com.neurix.hris.transaksi.ijinKeluar.dao.IjinKeluarDao;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.rekruitmen.bo.RekruitmenBo;
import com.neurix.hris.transaksi.rekruitmen.dao.*;
import com.neurix.hris.transaksi.rekruitmen.model.*;
import com.neurix.hris.transaksi.training.dao.TrainingDao;
import com.neurix.hris.transaksi.training.dao.TrainingPersonDao;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingEntity;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingPersonEntity;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class RekruitmenBoImpl implements RekruitmenBo {

    protected static transient Logger logger = Logger.getLogger(RekruitmenBoImpl.class);
    private RekruitmenDao rekruitmenDao;
    private RekruitmenUploadDocDao rekruitmenUploadDocDao;
    private RekruitmenStatusDao rekruitmenStatusDao;
    private StudyCalonPegawaiDao studyCalonPegawaiDao;
    private TrainingPersonDao trainingPersonDao;
    private BiodataDao biodataDao;
    private TrainingDao trainingDao;
    private RsKelasDao rsKelasDao;
    private PositionDao positionDao;
    private RsKerjasamaDao rsKerjasamaDao;
    private PersonilPositionDao personilPositionDao;
    private IjinKeluarDao ijinKeluarDao;
    private RsKerjasamaDao cutiPegawai;
    private RsKerjasamaDao notifikasi;
    private StudyDao studyDao;
    private StatusRekruitmentDao statusRekruitmentDao;
    private RekruitmenUraianKerjaanDao rekruitmenUraianKerjaanDao;
    private PositionBagianDao positionBagianDao;
    private RekruitmenKontrakDao rekruitmenKontrakDao;
    private BranchDao branchDao;
    private PerjalananDinasDao perjalananDinasDao;

    public PerjalananDinasDao getPerjalananDinasDao() {
        return perjalananDinasDao;
    }

    public void setPerjalananDinasDao(PerjalananDinasDao perjalananDinasDao) {
        this.perjalananDinasDao = perjalananDinasDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public RekruitmenKontrakDao getRekruitmenKontrakDao() {
        return rekruitmenKontrakDao;
    }

    public void setRekruitmenKontrakDao(RekruitmenKontrakDao rekruitmenKontrakDao) {
        this.rekruitmenKontrakDao = rekruitmenKontrakDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public RekruitmenUraianKerjaanDao getRekruitmenUraianKerjaanDao() {
        return rekruitmenUraianKerjaanDao;
    }

    public void setRekruitmenUraianKerjaanDao(RekruitmenUraianKerjaanDao rekruitmenUraianKerjaanDao) {
        this.rekruitmenUraianKerjaanDao = rekruitmenUraianKerjaanDao;
    }

    public StatusRekruitmentDao getStatusRekruitmentDao() {
        return statusRekruitmentDao;
    }

    public void setStatusRekruitmentDao(StatusRekruitmentDao statusRekruitmentDao) {
        this.statusRekruitmentDao = statusRekruitmentDao;
    }

    public RekruitmenStatusDao getRekruitmenStatusDao() {
        return rekruitmenStatusDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public void setRekruitmenStatusDao(RekruitmenStatusDao rekruitmenStatusDao) {
        this.rekruitmenStatusDao = rekruitmenStatusDao;
    }

    public StudyCalonPegawaiDao getStudyCalonPegawaiDao() {
        return studyCalonPegawaiDao;
    }

    public void setStudyCalonPegawaiDao(StudyCalonPegawaiDao studyCalonPegawaiDao) {
        this.studyCalonPegawaiDao = studyCalonPegawaiDao;
    }

    public RekruitmenUploadDocDao getRekruitmenUploadDocDao() {
        return rekruitmenUploadDocDao;
    }

    public void setRekruitmenUploadDocDao(RekruitmenUploadDocDao rekruitmenUploadDocDao) {
        this.rekruitmenUploadDocDao = rekruitmenUploadDocDao;
    }

    public RsKerjasamaDao getRsKerjasamaDao() {
        return rsKerjasamaDao;
    }

    public void setRsKerjasamaDao(RsKerjasamaDao rsKerjasamaDao) {
        this.rsKerjasamaDao = rsKerjasamaDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public RsKelasDao getRsKelasDao() {
        return rsKelasDao;
    }

    public void setRsKelasDao(RsKelasDao rsKelasDao) {
        this.rsKelasDao = rsKelasDao;
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
    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RekruitmenBoImpl.logger = logger;
    }

    public RekruitmenDao getRekruitmenDao() {
        return rekruitmenDao;
    }

    public void setRekruitmenDao(RekruitmenDao rekruitmenDao) {
        this.rekruitmenDao = rekruitmenDao;
    }

    @Override
    public List<StudyCalonPegawai> getComboRekruitmenStudyPerson(String studyName) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<StudyCalonPegawai> listComboRekruitmenStudy = new ArrayList();
        List<StudyCalonPegawai> listComboRekruitmenStudyFix = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {
            listComboRekruitmenStudy = (List<StudyCalonPegawai>) session.getAttribute("listOfResultRekruitmenStudy");
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listComboRekruitmenStudy != null) {
            for (StudyCalonPegawai rekruitmenStudyDetail : listComboRekruitmenStudy) {
                if (rekruitmenStudyDetail.getStudyName().equals(studyName)) {
                    StudyCalonPegawai itemComboRekruitmenStudy = new StudyCalonPegawai();
                    itemComboRekruitmenStudy.setStudyCalonPegawaiId(rekruitmenStudyDetail.getStudyCalonPegawaiId());
                    itemComboRekruitmenStudy.setTahunAkhir(rekruitmenStudyDetail.getTahunAkhir());
                    itemComboRekruitmenStudy.setTahunAwal(rekruitmenStudyDetail.getTahunAwal());
                    itemComboRekruitmenStudy.setStudyName(rekruitmenStudyDetail.getStudyName());
                    itemComboRekruitmenStudy.setTipeStudy(rekruitmenStudyDetail.getTipeStudy());
                    itemComboRekruitmenStudy.setCalonPegawaiId(rekruitmenStudyDetail.getCalonPegawaiId());
                    itemComboRekruitmenStudy.setNilai(rekruitmenStudyDetail.getNilai());
                    itemComboRekruitmenStudy.setStTahunAwal(rekruitmenStudyDetail.getStTahunAwal());
                    itemComboRekruitmenStudy.setStTahunAkhir(rekruitmenStudyDetail.getStTahunAkhir());

                    listComboRekruitmenStudyFix.add(itemComboRekruitmenStudy);
                } else {
                    logger.info("Error Di getComboRekruitmenPabrikPerson2");
                }
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboRekruitmenStudyFix;
    }

    @Override
    public List<RekruitmenUploadDoc> getListRekruitmenDocumentBo(String calonPegawaiId, String flag) throws GeneralBOException {
        logger.info("[BelajarBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RekruitmenUploadDoc> listOfResultUploadDoc = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("calon_pegawai_id",calonPegawaiId);

            List<ItRekruitmenUploadDocEntity> itRekruitmenUploadDocEntities= null;
            try {

                itRekruitmenUploadDocEntities = rekruitmenUploadDocDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[BelajarBoImpl.getSearchBelajarByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itRekruitmenUploadDocEntities != null){
                RekruitmenUploadDoc returnRekruitmenUploadDoc;
                // Looping from dao to object and save in collection
                int no=1;
                for(ItRekruitmenUploadDocEntity rekruitmenUploadDocEntity : itRekruitmenUploadDocEntities){
                    returnRekruitmenUploadDoc = new RekruitmenUploadDoc();
                    returnRekruitmenUploadDoc.setNo(String.valueOf(no));
                    returnRekruitmenUploadDoc.setUploadDocRekId(rekruitmenUploadDocEntity.getUploadDocRekId());
                    returnRekruitmenUploadDoc.setFilePath(rekruitmenUploadDocEntity.getFilePath());
                    returnRekruitmenUploadDoc.setUploadFile(rekruitmenUploadDocEntity.getUploadFile());
                    returnRekruitmenUploadDoc.setFileType(rekruitmenUploadDocEntity.getFileType());
                    returnRekruitmenUploadDoc.setCalonPegawaiId(rekruitmenUploadDocEntity.getCalonPegawaiId());
                    returnRekruitmenUploadDoc.setDocumentName(rekruitmenUploadDocEntity.getDocumentName());

                    returnRekruitmenUploadDoc.setCreatedWho(rekruitmenUploadDocEntity.getCreatedWho());
                    returnRekruitmenUploadDoc.setCreatedDate(rekruitmenUploadDocEntity.getCreatedDate());
                    returnRekruitmenUploadDoc.setLastUpdate(rekruitmenUploadDocEntity.getLastUpdate());
                    returnRekruitmenUploadDoc.setAction(rekruitmenUploadDocEntity.getAction());
                    returnRekruitmenUploadDoc.setFlag(rekruitmenUploadDocEntity.getFlag());
                    listOfResultUploadDoc.add(returnRekruitmenUploadDoc);
                    no++;
                }
            }
        logger.info("[BelajarBoImpl.getByCriteria] end process <<<");

        return listOfResultUploadDoc;
    }


    @Override
    public List<StudyCalonPegawai> getListRekruitmenStudyBo(String calonPegawaiId, String flag) throws GeneralBOException {
        logger.info("[BelajarBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<StudyCalonPegawai> listOfResultRekruitmenStudy = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("calon_pegawai_id",calonPegawaiId);

        List<ItStudyCalonPegawaiEntity> itStudyCalonPegawaiEntities= null;
        try {

            itStudyCalonPegawaiEntities = studyCalonPegawaiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[BelajarBoImpl.getSearchBelajarByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(itStudyCalonPegawaiEntities != null){
            StudyCalonPegawai returnRekruitmenStudy;
            // Looping from dao to object and save in collection
            for(ItStudyCalonPegawaiEntity rekruitmenStudyEntity : itStudyCalonPegawaiEntities){
                returnRekruitmenStudy = new StudyCalonPegawai();
                returnRekruitmenStudy.setCalonPegawaiId(rekruitmenStudyEntity.getCalonPegawaiId());
                returnRekruitmenStudy.setStudyName(rekruitmenStudyEntity.getStudyName());
                returnRekruitmenStudy.setNilai(rekruitmenStudyEntity.getNilai());
                returnRekruitmenStudy.setStudyCalonPegawaiId(rekruitmenStudyEntity.getStudyCalonPegawaiId());
                returnRekruitmenStudy.setTipeStudy(rekruitmenStudyEntity.getTipeStudy());
                returnRekruitmenStudy.setTahunAwal(rekruitmenStudyEntity.getTahunAwal());
                returnRekruitmenStudy.setTahunAkhir(rekruitmenStudyEntity.getTahunAkhir());

                Calendar cal = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal.setTime(rekruitmenStudyEntity.getTahunAwal());
                cal2.setTime(rekruitmenStudyEntity.getTahunAkhir());
                returnRekruitmenStudy.setStTahunAwal(String.valueOf(cal.get(Calendar.YEAR)));
                returnRekruitmenStudy.setStTahunAkhir(String.valueOf(cal2.get(Calendar.YEAR)));

                returnRekruitmenStudy.setCreatedWho(rekruitmenStudyEntity.getCreatedWho());
                returnRekruitmenStudy.setCreatedDate(rekruitmenStudyEntity.getCreatedDate());
                returnRekruitmenStudy.setLastUpdate(rekruitmenStudyEntity.getLastUpdate());
                returnRekruitmenStudy.setAction(rekruitmenStudyEntity.getAction());
                returnRekruitmenStudy.setFlag(rekruitmenStudyEntity.getFlag());
                listOfResultRekruitmenStudy.add(returnRekruitmenStudy);
            }
        }
        logger.info("[BelajarBoImpl.getByCriteria] end process <<<");

        return listOfResultRekruitmenStudy;
    }
    @Override
    public List<RekruitmenStatus> getListRekruitmenStatusBo(String calonPegawaiId, String flag) throws GeneralBOException {
        logger.info("[BelajarBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RekruitmenStatus> listOfResultRekruitmenStatus = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("calon_pegawai_id",calonPegawaiId);

        List<ItRekruitmenStatusEntity> itRekruitmenStatusEntities= null;
        try {

            itRekruitmenStatusEntities = rekruitmenStatusDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[BelajarBoImpl.getSearchBelajarByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(itRekruitmenStatusEntities != null){
            RekruitmenStatus returnRekruitmenStatus;
            // Looping from dao to object and save in collection
            for(ItRekruitmenStatusEntity rekruitmenStatusEntity : itRekruitmenStatusEntities){
                returnRekruitmenStatus = new RekruitmenStatus();
                returnRekruitmenStatus.setCalonPegawaiId(rekruitmenStatusEntity.getCalonPegawaiId());
                returnRekruitmenStatus.setStatusRekruitmenName(rekruitmenStatusEntity.getImStatusRekruitmentEntity().getStatusRekruitmentName());
                returnRekruitmenStatus.setStatusRekruitmen(rekruitmenStatusEntity.getStatusRekruitmen());
                returnRekruitmenStatus.setKeterangan(rekruitmenStatusEntity.getKeterangan());
                returnRekruitmenStatus.setRekruitmenStatusId(rekruitmenStatusEntity.getRekruitmenStatusId());
                returnRekruitmenStatus.setTanggalProses(rekruitmenStatusEntity.getTanggalProses());
                returnRekruitmenStatus.setStTanggalProses(CommonUtil.convertDateToString(rekruitmenStatusEntity.getTanggalProses()));

                returnRekruitmenStatus.setCreatedWho(rekruitmenStatusEntity.getCreatedWho());
                returnRekruitmenStatus.setCreatedDate(rekruitmenStatusEntity.getCreatedDate());
                returnRekruitmenStatus.setLastUpdate(rekruitmenStatusEntity.getLastUpdate());
                returnRekruitmenStatus.setAction(rekruitmenStatusEntity.getAction());
                returnRekruitmenStatus.setFlag(rekruitmenStatusEntity.getFlag());
                listOfResultRekruitmenStatus.add(returnRekruitmenStatus);
            }
        }
        logger.info("[BelajarBoImpl.getByCriteria] end process <<<");

        return listOfResultRekruitmenStatus;
    }
    @Override
    public List<RekruitmenUraianKerjaan> getListUraianPekerjaan(String calonPegawaiId, String flag) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.getListUraianPekerjaan] start process >>>");

        // Mapping with collection and put
        List<RekruitmenUraianKerjaan> rekruitmenUraianKerjaanList = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("calon_pegawai_id",calonPegawaiId);

        List<ItRekruitmenUraianKerjaanEntity> itRekruitmenUraianKerjaanEntityList= null;
        try {

            itRekruitmenUraianKerjaanEntityList = rekruitmenUraianKerjaanDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RekruitmenBoImpl.getListUraianPekerjaan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(itRekruitmenUraianKerjaanEntityList != null){

            // Looping from dao to object and save in collection
            for(ItRekruitmenUraianKerjaanEntity rekruitmenUraianKerjaanEntity : itRekruitmenUraianKerjaanEntityList){
                RekruitmenUraianKerjaan rekruitmenUraianKerjaan = new RekruitmenUraianKerjaan();
                rekruitmenUraianKerjaan.setCalonPegawaiId(rekruitmenUraianKerjaanEntity.getCalonPegawaiId());
                rekruitmenUraianKerjaan.setNo(rekruitmenUraianKerjaanEntity.getNo());
                rekruitmenUraianKerjaan.setUraianPekerjaan(rekruitmenUraianKerjaanEntity.getUraianPekerjaan());

                rekruitmenUraianKerjaan.setCreatedWho(rekruitmenUraianKerjaanEntity.getCreatedWho());
                rekruitmenUraianKerjaan.setCreatedDate(rekruitmenUraianKerjaanEntity.getCreatedDate());
                rekruitmenUraianKerjaan.setLastUpdate(rekruitmenUraianKerjaanEntity.getLastUpdate());
                rekruitmenUraianKerjaan.setAction(rekruitmenUraianKerjaanEntity.getAction());
                rekruitmenUraianKerjaan.setFlag(rekruitmenUraianKerjaanEntity.getFlag());
                rekruitmenUraianKerjaanList.add(rekruitmenUraianKerjaan);
            }
        }
        logger.info("[RekruitmenBoImpl.getListUraianPekerjaan] end process <<<");

        return rekruitmenUraianKerjaanList;
    }
    @Override
    public void addRekruitmenDoc(RekruitmenUploadDoc bean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.saveAddDoc] start process >>>");

        if (bean!=null) {
            // creating object entity serializable
            ItRekruitmenUploadDocEntity itRekruitmenUploadDocEntity = new ItRekruitmenUploadDocEntity();

            itRekruitmenUploadDocEntity.setUploadDocRekId(bean.getUploadDocRekId());
            itRekruitmenUploadDocEntity.setUploadFile(bean.getUploadFile());
            itRekruitmenUploadDocEntity.setCalonPegawaiId(bean.getCalonPegawaiId());
            itRekruitmenUploadDocEntity.setDocumentName(bean.getDocumentName());
            itRekruitmenUploadDocEntity.setFilePath(bean.getFilePath());
            itRekruitmenUploadDocEntity.setFileType(bean.getFileType());
            itRekruitmenUploadDocEntity.setFlag(bean.getFlag());
            itRekruitmenUploadDocEntity.setAction(bean.getAction());
            itRekruitmenUploadDocEntity.setCreatedWho(bean.getCreatedWho());
            itRekruitmenUploadDocEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itRekruitmenUploadDocEntity.setCreatedDate(bean.getCreatedDate());
            itRekruitmenUploadDocEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                rekruitmenUploadDocDao.addAndSave(itRekruitmenUploadDocEntity);
            } catch (HibernateException e) {
                logger.error("[rekruitmenUploadDocBoImpl.saveAddDoc] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Department, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[DepartmentBoImpl.saveAdd] end process <<<");
    }
    @Override
    public String getNextDocId() throws GeneralBOException {
        String docId;
        try {
            // Generating ID, get from postgre sequence
            docId = rekruitmenUploadDocDao.getNextRekruitmenDoc();
        } catch (HibernateException e) {
            logger.error("[DepartmentBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence departmentId id, please info to your admin..." + e.getMessage());
        }
        return docId;
    }

    @Override
    public String getNextCalonPegawaiId(){
        String calonPegawaiId = rekruitmenDao.getNextCalonPegawaiId();

        return calonPegawaiId;
    }

    @Override
    public void saveDelete(Rekruitmen bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String personalId = bean.getCalonPegawaiId();

            ImRekruitmenEntity imRekruitmenEntity = null;

            try {
                // Get data from database by ID
                imRekruitmenEntity = rekruitmenDao.getById("calonPegawaiId", personalId);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imRekruitmenEntity != null) {

                // Modify from bean to entity serializable
                imRekruitmenEntity.setCalonPegawaiId(bean.getCalonPegawaiId());
                imRekruitmenEntity.setClosed(bean.getClosed());
                imRekruitmenEntity.setAlamat(bean.getAlamat());
                imRekruitmenEntity.setNamaCalonPegawai(bean.getNamaCalonPegawai());
                imRekruitmenEntity.setBranchId(bean.getBranchId());
                imRekruitmenEntity.setDepartmentId(bean.getDepartmentId());
                imRekruitmenEntity.setPositionId(bean.getPositionId());
                imRekruitmenEntity.setDesaId(bean.getDesaId());
                imRekruitmenEntity.setKecamatanId(bean.getKecamatanId());
                imRekruitmenEntity.setKotaId(bean.getKotaId());
                imRekruitmenEntity.setProvinsiId(bean.getProvinsiId());
                imRekruitmenEntity.setRtRw(bean.getRtRw());
                imRekruitmenEntity.setTempatLahir(bean.getTempatLahir());
//                imRekruitmenEntity.setTanggalLahir(bean.getTanggalLahir());
                imRekruitmenEntity.setFotoUpload(bean.getFotoUpload());
                imRekruitmenEntity.setJenisKelamin(bean.getJenisKelamin());
                imRekruitmenEntity.setJumlahAnak(bean.getJumlahAnak());
                imRekruitmenEntity.setStatusGiling(bean.getStatusGiling());
                imRekruitmenEntity.setStatusKeluarga(bean.getStatusKeluarga());
                imRekruitmenEntity.setStatusPegawai(bean.getStatusPegawai());
                imRekruitmenEntity.setGelarBelakang(bean.getGelarBelakang());
                imRekruitmenEntity.setGelarDepan(bean.getGelarDepan());
                imRekruitmenEntity.setNoKtp(bean.getNoKtp());
                imRekruitmenEntity.setNoTelp(bean.getNoTelp());
                imRekruitmenEntity.setTipePegawai(bean.getTipePegawai());
                imRekruitmenEntity.setStatusInput("R");

                imRekruitmenEntity.setFlag(bean.getFlag());
                imRekruitmenEntity.setAction(bean.getAction());
                imRekruitmenEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imRekruitmenEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Delete (Edit) into database
                    rekruitmenDao.updateAndSave(imRekruitmenEntity);
                } catch (HibernateException e) {
                    logger.error("[RekruitmenBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Rekruitmen, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[RekruitmenBoImpl.saveDelete] Error, not found data Rekruitmen with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Rekruitmen with request id, please check again your data ...");

            }
        }
        logger.info("[RekruitmenBoImpl.saveDelete] end process <<<");
    }
    @Override
    public void saveClosed(Rekruitmen bean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.saveEdit] start process >>>");
        String foto = null;
        if (bean!=null) {
//            String historyId = "";
            String calonPegawaiId = bean.getCalonPegawaiId();
            ImRekruitmenEntity imRekruitmenEntity = null;
            if (bean.getCalonPegawaiId()!=null){
                Map HsCriteria = new HashMap();
                HsCriteria.put("calon_pegawai_id",calonPegawaiId);
                HsCriteria.put("flag","Y");
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                List<ItStudyCalonPegawaiEntity> listOfStudyCalonPegawai = new ArrayList<ItStudyCalonPegawaiEntity>();
                listOfStudyCalonPegawai = studyCalonPegawaiDao.getByCriteria(HsCriteria);
                for (ItStudyCalonPegawaiEntity itStudyCalonPegawaiEntity : listOfStudyCalonPegawai){
                    ImStudyEntity returnStudyCalonPegawai = new ImStudyEntity();
                    String studyId = studyDao.getNextStudyId();
                    returnStudyCalonPegawai.setStudyId(studyId);
                    returnStudyCalonPegawai.setStudyName(itStudyCalonPegawaiEntity.getStudyName());
                    returnStudyCalonPegawai.setTypeStudy(itStudyCalonPegawaiEntity.getTipeStudy());
                    returnStudyCalonPegawai.setTahunAwal(String.valueOf(itStudyCalonPegawaiEntity.getTahunAwal()).substring(0,4));
                    returnStudyCalonPegawai.setTahunAkhir(String.valueOf(itStudyCalonPegawaiEntity.getTahunAkhir()).substring(0,4));
                    returnStudyCalonPegawai.setNip(bean.getNip());

                    returnStudyCalonPegawai.setCreatedWho(bean.getCreatedWho());
                    returnStudyCalonPegawai.setCreatedDate(updateTime);
                    returnStudyCalonPegawai.setFlag(itStudyCalonPegawaiEntity.getFlag());
                    returnStudyCalonPegawai.setLastUpdate(updateTime);
                    returnStudyCalonPegawai.setLastUpdateWho(bean.getLastUpdateWho());
                    returnStudyCalonPegawai.setAction("U");

                    studyDao.addAndSave(returnStudyCalonPegawai);
                }
            }

//            ImRekruitmenHistoryEntity imRekruitmenHistoryEntity = new ImRekruitmenHistoryEntity();
            try {
//                 Get data from database by ID
                imRekruitmenEntity = rekruitmenDao.getById("calonPegawaiId", calonPegawaiId);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Rekruitmen by Kode Rekruitmen, please inform to your admin...," + e.getMessage());
            }
            if (imRekruitmenEntity != null) {
                foto = imRekruitmenEntity.getFotoUpload();
                imRekruitmenEntity.setNip(bean.getNip());
                imRekruitmenEntity.setGolongan(bean.getGolongan());
                imRekruitmenEntity.setTanggalAktif(bean.getTanggalAktif());
                imRekruitmenEntity.setKontrakDari(bean.getKontrakDari());
                imRekruitmenEntity.setKontrakSelesai(bean.getKontrakSelesai());
                imRekruitmenEntity.setPoin(bean.getPoin());
                imRekruitmenEntity.setNoKontrak(bean.getNoKontrak());
                imRekruitmenEntity.setUpah(bean.getUpah());
                imRekruitmenEntity.setClosed("Y");

                imRekruitmenEntity.setFlag(bean.getFlag());
                imRekruitmenEntity.setAction(bean.getAction());
                imRekruitmenEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imRekruitmenEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    rekruitmenDao.updateAndSave(imRekruitmenEntity);

                } catch (HibernateException e) {
                    logger.error("[RekruitmenBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Rekruitmen, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[RekruitmenBoImpl.saveEdit] Error, not found data Rekruitmen with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Rekruitmen with request id, please check again your data ...");

            }

            String personPosition;

            personPosition =  personilPositionDao.getNextPersonilPositionId();
            // creating object entity serializable
            ImBiodataEntity imBiodataEntity = new ImBiodataEntity();
            ItPersonilPositionEntity itPersonilPositionEntity = new ItPersonilPositionEntity();

            itPersonilPositionEntity.setPersonilPositionId(personPosition);
            itPersonilPositionEntity.setNip(bean.getNip());
            itPersonilPositionEntity.setBranchId(bean.getBranchId());
//            itPersonilPositionEntity.setDivisiId(bean.getDepartmentId());
            itPersonilPositionEntity.setPositionId(bean.getPositionId());
            itPersonilPositionEntity.setFlag(bean.getFlag());
            itPersonilPositionEntity.setAction(bean.getAction());
            itPersonilPositionEntity.setCreatedWho(bean.getCreatedWho());
            itPersonilPositionEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itPersonilPositionEntity.setCreatedDate(bean.getCreatedDate());
            itPersonilPositionEntity.setLastUpdate(bean.getLastUpdate());

            imBiodataEntity.setNip(bean.getCalonPegawaiId());
            imBiodataEntity.setNamaPegawai(bean.getNamaCalonPegawai());
            imBiodataEntity.setGelarDepan(bean.getGelarDepan());
            imBiodataEntity.setGelarBelakang(bean.getGelarBelakang());
            imBiodataEntity.setNoKtp(bean.getNoKtp());
            imBiodataEntity.setAlamat(bean.getAlamat());
            imBiodataEntity.setRtRw(bean.getRtRw());
            imBiodataEntity.setDesa(bean.getDesaId());
            imBiodataEntity.setKecamatan(bean.getKecamatanId());
            imBiodataEntity.setNoTelp(bean.getNoTelp());
            imBiodataEntity.setPoint(bean.getPoin().intValue());
            imBiodataEntity.setKotaId(bean.getKotaId());
            imBiodataEntity.setProvinsi(bean.getProvinsiId());
            imBiodataEntity.setProvinsiId(bean.getProvinsiId());
            imBiodataEntity.setKecamatanId(bean.getKecamatanId());
            imBiodataEntity.setDesaId(bean.getDesaId());
            imBiodataEntity.setKotaId(bean.getKotaId());
            String DATE_FORMAT = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            //Timestamp tanggalLahir = CommonUtil.convertToTimestamp(sdf.format(bean.getTanggalLahir()));
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            imBiodataEntity.setTanggalLahir(bean.getTanggalLahir());
            imBiodataEntity.setTempatLahir(bean.getTempatLahir());
            imBiodataEntity.setTipePegawai(bean.getTipePegawai());
            imBiodataEntity.setFotoUpload(foto);
            imBiodataEntity.setStatusCaption("");
            imBiodataEntity.setKeterangan("");
            imBiodataEntity.setTanggalAktif(CommonUtil.convertTimestampToDate(updateTime));
            imBiodataEntity.setStatusPegawai(bean.getStatusPegawai());
            imBiodataEntity.setStatusKeluarga(bean.getStatusKeluarga());
            imBiodataEntity.setGolongan("");
            imBiodataEntity.setGaji(BigDecimal.ZERO);
            BigInteger bi = BigInteger.valueOf(bean.getJumlahAnak().intValue());
            imBiodataEntity.setJumlahAnak(bi);
            imBiodataEntity.setGender(bean.getJenisKelamin());
            imBiodataEntity.setMasaGiling(bean.getStatusGiling());
            imBiodataEntity.setNip(bean.getNip());
            imBiodataEntity.setGolongan(bean.getGolongan());
            imBiodataEntity.setTanggalAktif(bean.getTanggalAktif());
            imBiodataEntity.setPin(null);

            imBiodataEntity.setFlag(bean.getFlag());
            imBiodataEntity.setAction(bean.getAction());
            imBiodataEntity.setCreatedWho(bean.getCreatedWho());
            imBiodataEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imBiodataEntity.setCreatedDate(updateTime);
            imBiodataEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                personilPositionDao.addAndSave(itPersonilPositionEntity);
                biodataDao.addAndSave(imBiodataEntity);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Biodata, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[RekruitmenBoImpl.saveEdit] end process <<<");
    }
    @Override
    public void saveEdit(Rekruitmen bean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.saveEdit] start process >>>");
//        String condition = null;
        if (bean!=null) {
//            String historyId = "";
            String calonPegawaiId = bean.getCalonPegawaiId();
            ImRekruitmenEntity imRekruitmenEntity = null;
//            ImRekruitmenHistoryEntity imRekruitmenHistoryEntity = new ImRekruitmenHistoryEntity();
            try {
//                 Get data from database by ID
                imRekruitmenEntity = rekruitmenDao.getById("calonPegawaiId", calonPegawaiId);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Rekruitmen by Kode Rekruitmen, please inform to your admin...," + e.getMessage());
            }
            if (imRekruitmenEntity != null) {

                imRekruitmenEntity.setCalonPegawaiId(bean.getCalonPegawaiId());
                imRekruitmenEntity.setClosed("N");
                imRekruitmenEntity.setNamaCalonPegawai(bean.getNamaCalonPegawai());
                imRekruitmenEntity.setGelarDepan(bean.getGelarDepan());
                imRekruitmenEntity.setGelarBelakang(bean.getGelarBelakang());
                imRekruitmenEntity.setNoKtp(bean.getNoKtp());
                imRekruitmenEntity.setAlamat(bean.getAlamat());
                imRekruitmenEntity.setRtRw(bean.getRtRw());
                imRekruitmenEntity.setDesaId(bean.getDesaId());
                imRekruitmenEntity.setKecamatanId(bean.getKecamatanId());
                imRekruitmenEntity.setNoTelp(bean.getNoTelp());
                imRekruitmenEntity.setKotaId(bean.getKotaId());
                imRekruitmenEntity.setProvinsiId(bean.getProvinsiId());
                imRekruitmenEntity.setTanggalLahir(bean.getTanggalLahir());
                imRekruitmenEntity.setTempatLahir(bean.getTempatLahir());
                imRekruitmenEntity.setPositionId(bean.getPositionId());
                imRekruitmenEntity.setStatusPegawai(bean.getStatusPegawai());
                imRekruitmenEntity.setTipePegawai(bean.getTipePegawai());
//                imRekruitmenEntity.setFotoUpload(bean.getFotoUpload());
                imRekruitmenEntity.setDepartmentId(bean.getDepartmentId());
                imRekruitmenEntity.setBranchId(bean.getBranchId());
                imRekruitmenEntity.setFotoUpload(bean.getFotoUpload());
                imRekruitmenEntity.setStatusKeluarga(bean.getStatusKeluarga());
                imRekruitmenEntity.setJenisKelamin(bean.getJenisKelamin());
                imRekruitmenEntity.setJumlahAnak(bean.getJumlahAnak());
                imRekruitmenEntity.setStatusInput("R");
                imRekruitmenEntity.setStatusGiling(bean.getStatusGiling());
                imRekruitmenEntity.setNoKontrak(bean.getNoKontrak());

                imRekruitmenEntity.setFlag(bean.getFlag());
                imRekruitmenEntity.setAction(bean.getAction());
                imRekruitmenEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imRekruitmenEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    rekruitmenDao.updateAndSave(imRekruitmenEntity);

                } catch (HibernateException e) {
                    logger.error("[RekruitmenBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Rekruitmen, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[RekruitmenBoImpl.saveEdit] Error, not found data Rekruitmen with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Rekruitmen with request id, please check again your data ...");

            }
        }
        logger.info("[RekruitmenBoImpl.saveEdit] end process <<<");
    }

    public void saveEditCaption(Rekruitmen bean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
//            String personalId = bean.getNip();

            ImRekruitmenEntity imRekruitmenEntity = null;
            ImRekruitmenHistoryEntity imRekruitmenHistoryEntity = new ImRekruitmenHistoryEntity();
            try {
                // Get data from database by ID
//                imRekruitmenEntity = rekruitmenDao.getById("nip", personalId);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Rekruitmen by Kode Rekruitmen, please inform to your admin...," + e.getMessage());
            }

            if (imRekruitmenEntity != null) {

//                imRekruitmenEntity.setNip(bean.getNip());
//                imRekruitmenEntity.setStatusCaption(bean.getStatusCaption());
                try {
                    // Update into database
                    rekruitmenDao.updateAndSave(imRekruitmenEntity);

                } catch (HibernateException e) {
                    logger.error("[RekruitmenBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Rekruitmen, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[RekruitmenBoImpl.saveEdit] Error, not found data Rekruitmen with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Rekruitmen with request id, please check again your data ...");

            }
        }
        logger.info("[RekruitmenBoImpl.saveEdit] end process <<<");
    }
    @Override
    public Rekruitmen saveAction(RekruitmenStatus bean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String rekruitmenStatusId;

            rekruitmenStatusId =  rekruitmenStatusDao.getNextRekruitmenStatusId();
            // creating object entity serializable
            ItRekruitmenStatusEntity itRekruitmenStatusEntity = new ItRekruitmenStatusEntity();

            itRekruitmenStatusEntity.setRekruitmenStatusId(rekruitmenStatusId);
            itRekruitmenStatusEntity.setTanggalProses(bean.getTanggalProses());
            itRekruitmenStatusEntity.setKeterangan(bean.getKeterangan());
            itRekruitmenStatusEntity.setStatusRekruitmen(bean.getStatusRekruitmen());
            itRekruitmenStatusEntity.setCalonPegawaiId(bean.getCalonPegawaiId());

            itRekruitmenStatusEntity.setFlag(bean.getFlag());
            itRekruitmenStatusEntity.setAction(bean.getAction());
            itRekruitmenStatusEntity.setCreatedWho(bean.getCreatedWho());
            itRekruitmenStatusEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itRekruitmenStatusEntity.setCreatedDate(bean.getCreatedDate());
            itRekruitmenStatusEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                rekruitmenStatusDao.addAndSave(itRekruitmenStatusEntity);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.saveAction] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Rekruitmen, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[RekruitmenBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public RekruitmenStatus saveAddStatusClosed(RekruitmenStatus bean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String rekruitmenStatusId;

            rekruitmenStatusId =  rekruitmenStatusDao.getNextRekruitmenStatusId();
            // creating object entity serializable
            ItRekruitmenStatusEntity itRekruitmenStatusEntity = new ItRekruitmenStatusEntity();

            itRekruitmenStatusEntity.setRekruitmenStatusId(rekruitmenStatusId);
            itRekruitmenStatusEntity.setTanggalProses(bean.getTanggalProses());
            itRekruitmenStatusEntity.setKeterangan(bean.getKeterangan());
            itRekruitmenStatusEntity.setStatusRekruitmen("11");
            itRekruitmenStatusEntity.setCalonPegawaiId(bean.getCalonPegawaiId());

            itRekruitmenStatusEntity.setFlag(bean.getFlag());
            itRekruitmenStatusEntity.setAction(bean.getAction());
            itRekruitmenStatusEntity.setCreatedWho(bean.getCreatedWho());
            itRekruitmenStatusEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itRekruitmenStatusEntity.setCreatedDate(bean.getCreatedDate());
            itRekruitmenStatusEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                rekruitmenStatusDao.addAndSave(itRekruitmenStatusEntity);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Rekruitmen, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[RekruitmenBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public void saveAddUraianPekerjaan(List<RekruitmenUraianKerjaan> beanList, RekruitmenUraianKerjaan bean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.saveAdd] start process >>>");
        if (beanList!=null) {
            int no = 1;
            for (RekruitmenUraianKerjaan rekruitmenUraianKerjaan : beanList){
                String rekruitmenUraianKerjaanId;
                rekruitmenUraianKerjaanId =  rekruitmenUraianKerjaanDao.getNextRekruitmenUraianKerjaanId();

                ItRekruitmenUraianKerjaanEntity itRekruitmenUraianKerjaanEntity = new ItRekruitmenUraianKerjaanEntity();

                itRekruitmenUraianKerjaanEntity.setRekruitmenUraianKerjaanId(rekruitmenUraianKerjaanId);
                itRekruitmenUraianKerjaanEntity.setCalonPegawaiId(bean.getCalonPegawaiId());
                itRekruitmenUraianKerjaanEntity.setUraianPekerjaan(rekruitmenUraianKerjaan.getUraianPekerjaan());
                itRekruitmenUraianKerjaanEntity.setNo(String.valueOf(no));
                itRekruitmenUraianKerjaanEntity.setCalonPegawaiId(bean.getCalonPegawaiId());

                itRekruitmenUraianKerjaanEntity.setFlag(bean.getFlag());
                itRekruitmenUraianKerjaanEntity.setAction(bean.getAction());
                itRekruitmenUraianKerjaanEntity.setCreatedWho(bean.getCreatedWho());
                itRekruitmenUraianKerjaanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itRekruitmenUraianKerjaanEntity.setCreatedDate(bean.getCreatedDate());
                itRekruitmenUraianKerjaanEntity.setLastUpdate(bean.getLastUpdate());

                no++;
                try {
                    // insert into database
                    rekruitmenUraianKerjaanDao.addAndSave(itRekruitmenUraianKerjaanEntity);
                } catch (HibernateException e) {
                    logger.error("[RekruitmenBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Rekruitmen, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[RekruitmenBoImpl.saveAdd] end process <<<");
    }
    @Override
    public Rekruitmen saveAddRekruitmen(Rekruitmen bean, RekruitmenStatus bean2) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.saveAdd] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (bean!=null) {
            String rekruitmenId;
            String profilPhoto = null;
            List<Rekruitmen> listOfsearchRekruitmen = (List<Rekruitmen>) session.getAttribute("listOfResultProfil");
            for (Rekruitmen rekruitmen : listOfsearchRekruitmen){
                if (rekruitmen.getCalonPegawaiId().equals(bean.getCalonPegawaiId())){
                    profilPhoto = rekruitmen.getFotoUpload();
                }
            }
            // creating object entity serializable
            ImRekruitmenEntity imRekruitmenEntity = new ImRekruitmenEntity();

            imRekruitmenEntity.setCalonPegawaiId(bean.getCalonPegawaiId());
            imRekruitmenEntity.setClosed(bean.getClosed());
            imRekruitmenEntity.setAlamat(bean.getAlamat());
            imRekruitmenEntity.setNamaCalonPegawai(bean.getNamaCalonPegawai());
            imRekruitmenEntity.setBranchId(bean.getBranchId());
            imRekruitmenEntity.setDepartmentId(bean.getDepartmentId());
            imRekruitmenEntity.setPositionId(bean.getPositionId());
            imRekruitmenEntity.setDesaId(bean.getDesaId());
            imRekruitmenEntity.setKecamatanId(bean.getKecamatanId());
            imRekruitmenEntity.setKotaId(bean.getKotaId());
            imRekruitmenEntity.setProvinsiId(bean.getProvinsiId());
            imRekruitmenEntity.setRtRw(bean.getRtRw());
            imRekruitmenEntity.setTempatLahir(bean.getTempatLahir());
            imRekruitmenEntity.setTanggalLahir(bean.getTanggalLahir());
            imRekruitmenEntity.setFotoUpload(profilPhoto);
            imRekruitmenEntity.setJenisKelamin(bean.getJenisKelamin());
            imRekruitmenEntity.setJumlahAnak(bean.getJumlahAnak());
            imRekruitmenEntity.setStatusGiling(bean.getStatusGiling());
            imRekruitmenEntity.setStatusKeluarga(bean.getStatusKeluarga());
            imRekruitmenEntity.setStatusPegawai(bean.getStatusPegawai());
            imRekruitmenEntity.setGelarBelakang(bean.getGelarBelakang());
            imRekruitmenEntity.setGelarDepan(bean.getGelarDepan());
            imRekruitmenEntity.setNoKtp(bean.getNoKtp());
            imRekruitmenEntity.setNoTelp("0"+bean.getNoTelp());
            imRekruitmenEntity.setTipePegawai(bean.getTipePegawai());
            imRekruitmenEntity.setStatusInput("R");
            imRekruitmenEntity.setUpah(BigInteger.ZERO);

            imRekruitmenEntity.setFlag(bean.getFlag());
            imRekruitmenEntity.setAction(bean.getAction());
            imRekruitmenEntity.setCreatedWho(bean.getCreatedWho());
            imRekruitmenEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imRekruitmenEntity.setCreatedDate(bean.getCreatedDate());
            imRekruitmenEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                rekruitmenDao.addAndSave(imRekruitmenEntity);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Rekruitmen, please info to your admin..." + e.getMessage());
            }
        }
        if (bean2!=null) {
            String rekruitmenStatusId;

            rekruitmenStatusId =  rekruitmenStatusDao.getNextRekruitmenStatusId();
            // creating object entity serializable
            ItRekruitmenStatusEntity itRekruitmenStatusEntity = new ItRekruitmenStatusEntity();

            itRekruitmenStatusEntity.setRekruitmenStatusId(rekruitmenStatusId);
            itRekruitmenStatusEntity.setTanggalProses(bean2.getTanggalProses());
            itRekruitmenStatusEntity.setKeterangan(bean2.getKeterangan());
            itRekruitmenStatusEntity.setStatusRekruitmen(bean2.getStatusRekruitmen());
            itRekruitmenStatusEntity.setCalonPegawaiId(bean.getCalonPegawaiId());

            itRekruitmenStatusEntity.setFlag(bean2.getFlag());
            itRekruitmenStatusEntity.setAction(bean2.getAction());
            itRekruitmenStatusEntity.setCreatedWho(bean2.getCreatedWho());
            itRekruitmenStatusEntity.setLastUpdateWho(bean2.getLastUpdateWho());
            itRekruitmenStatusEntity.setCreatedDate(bean2.getCreatedDate());
            itRekruitmenStatusEntity.setLastUpdate(bean2.getLastUpdate());

            try {
                // insert into database
                rekruitmenStatusDao.addAndSave(itRekruitmenStatusEntity);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Rekruitmen, please info to your admin..." + e.getMessage());
            }
        }
        List<StudyCalonPegawai> listOfsearchStudyCalon = (List<StudyCalonPegawai>) session.getAttribute("listOfResultRekruitmenStudy");
        if(listOfsearchStudyCalon != null){
            for (StudyCalonPegawai studyCalonPegawai: listOfsearchStudyCalon) {
                String studyCalonId = studyCalonPegawaiDao.getNextStudyCalonId();
                ItStudyCalonPegawaiEntity itStudyCalonPegawaiEntity = new ItStudyCalonPegawaiEntity();

                itStudyCalonPegawaiEntity.setCalonPegawaiId(bean.getCalonPegawaiId());
                itStudyCalonPegawaiEntity.setStudyCalonPegawaiId(studyCalonId);
                itStudyCalonPegawaiEntity.setTipeStudy(studyCalonPegawai.getTipeStudy());
                itStudyCalonPegawaiEntity.setNilai(studyCalonPegawai.getNilai());
                itStudyCalonPegawaiEntity.setStudyName(studyCalonPegawai.getStudyName());
                if (studyCalonPegawai.getStTahunAwal() != null && !"".equalsIgnoreCase(studyCalonPegawai.getStTahunAwal())) {
                    studyCalonPegawai.setStTahunAwal("01/01/"+studyCalonPegawai.getStTahunAwal());
                    itStudyCalonPegawaiEntity.setTahunAwal(CommonUtil.convertToDate(studyCalonPegawai.getStTahunAwal()));
                }
                if (studyCalonPegawai.getStTahunAkhir() != null && !"".equalsIgnoreCase(studyCalonPegawai.getStTahunAkhir())) {
                    studyCalonPegawai.setStTahunAkhir("01/01/"+studyCalonPegawai.getStTahunAkhir());
                    itStudyCalonPegawaiEntity.setTahunAkhir(CommonUtil.convertToDate(studyCalonPegawai.getStTahunAkhir()));
                }

                itStudyCalonPegawaiEntity.setFlag(bean.getFlag());
                itStudyCalonPegawaiEntity.setAction(bean.getAction());
                itStudyCalonPegawaiEntity.setCreatedWho(bean.getCreatedWho());
                itStudyCalonPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itStudyCalonPegawaiEntity.setCreatedDate(bean.getCreatedDate());
                itStudyCalonPegawaiEntity.setLastUpdate(bean.getLastUpdate());
                studyCalonPegawaiDao.addAndSave(itStudyCalonPegawaiEntity);
            }
        }

        logger.info("[RekruitmenBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public Rekruitmen saveAdd(Rekruitmen bean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String personPosition;

            personPosition =  personilPositionDao.getNextPersonilPositionId();
            // creating object entity serializable
            ImRekruitmenEntity imRekruitmenEntity = new ImRekruitmenEntity();
            ItPersonilPositionEntity itPersonilPositionEntity = new ItPersonilPositionEntity();

            itPersonilPositionEntity.setPersonilPositionId(personPosition);
//            itPersonilPositionEntity.setNip(bean.getNip());
//            itPersonilPositionEntity.setBranchId(bean.getBranch());
//            itPersonilPositionEntity.setDivisiId(bean.getDivisi());
            itPersonilPositionEntity.setPositionId(bean.getPositionId());
            itPersonilPositionEntity.setFlag(bean.getFlag());
            itPersonilPositionEntity.setAction(bean.getAction());
            itPersonilPositionEntity.setCreatedWho(bean.getCreatedWho());
            itPersonilPositionEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itPersonilPositionEntity.setCreatedDate(bean.getCreatedDate());
            itPersonilPositionEntity.setLastUpdate(bean.getLastUpdate());

//            imRekruitmenEntity.setNip(bean.getNip());
//            imRekruitmenEntity.setNamaPegawai(bean.getNamaPegawai());
            imRekruitmenEntity.setGelarDepan(bean.getGelarDepan());
            imRekruitmenEntity.setGelarBelakang(bean.getGelarBelakang());
            imRekruitmenEntity.setNoKtp(bean.getNoKtp());
            imRekruitmenEntity.setAlamat(bean.getAlamat());
            imRekruitmenEntity.setRtRw(bean.getRtRw());
            imRekruitmenEntity.setDesaId(bean.getDesaId());
            imRekruitmenEntity.setKecamatanId(bean.getKecamatanId());
            imRekruitmenEntity.setNoTelp(bean.getNoTelp());
            imRekruitmenEntity.setKotaId(bean.getKotaId());
            imRekruitmenEntity.setProvinsiId(bean.getProvinsiId());
            imRekruitmenEntity.setTanggalLahir(bean.getTanggalLahir());
            imRekruitmenEntity.setTempatLahir(bean.getTempatLahir());
            imRekruitmenEntity.setTipePegawai(bean.getTipePegawai());
            imRekruitmenEntity.setFotoUpload(bean.getFotoUpload());
            imRekruitmenEntity.setStatusPegawai(bean.getStatusPegawai());
            imRekruitmenEntity.setStatusKeluarga("");

            imRekruitmenEntity.setFlag(bean.getFlag());
            imRekruitmenEntity.setAction(bean.getAction());
            imRekruitmenEntity.setCreatedWho(bean.getCreatedWho());
            imRekruitmenEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imRekruitmenEntity.setCreatedDate(bean.getCreatedDate());
            imRekruitmenEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                personilPositionDao.addAndSave(itPersonilPositionEntity);
                rekruitmenDao.addAndSave(imRekruitmenEntity);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Rekruitmen, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[RekruitmenBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public List<RekruitmenUploadDoc> getByCriteriaDocument(Rekruitmen searchBean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RekruitmenUploadDoc> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getCalonPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getCalonPegawaiId())) {
                hsCriteria.put("calon_pegawai_id", searchBean.getCalonPegawaiId());
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


            List<ItRekruitmenUploadDocEntity> itRekruitmenUploadDocEntities = null;
            try {

                itRekruitmenUploadDocEntities = rekruitmenUploadDocDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itRekruitmenUploadDocEntities != null){
                RekruitmenUploadDoc returnRekruitmenUploadDoc;
                // Looping from dao to object and save in collection
                for(ItRekruitmenUploadDocEntity itRekruitmenUploadDocEntity : itRekruitmenUploadDocEntities){
                    returnRekruitmenUploadDoc = new RekruitmenUploadDoc();

                    returnRekruitmenUploadDoc.setCalonPegawaiId(itRekruitmenUploadDocEntity.getCalonPegawaiId());
                    returnRekruitmenUploadDoc.setDocumentName(itRekruitmenUploadDocEntity.getDocumentName());
                    returnRekruitmenUploadDoc.setUploadDocRekId(itRekruitmenUploadDocEntity.getUploadDocRekId());
                    returnRekruitmenUploadDoc.setFilePath(itRekruitmenUploadDocEntity.getFilePath());
                    returnRekruitmenUploadDoc.setFileType(itRekruitmenUploadDocEntity.getFileType());
                    returnRekruitmenUploadDoc.setUploadFile(itRekruitmenUploadDocEntity.getUploadFile());

                    listOfResult.add(returnRekruitmenUploadDoc);
                }
            }
        }
        logger.info("[RekruitmenStatusBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<StudyCalonPegawai> getByCriteriaStudy(Rekruitmen searchBean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<StudyCalonPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getCalonPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getCalonPegawaiId())) {
                hsCriteria.put("calon_pegawai_id", searchBean.getCalonPegawaiId());
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


            List<ItStudyCalonPegawaiEntity> itStudyCalonPegawaiEntities = null;
            try {

                itStudyCalonPegawaiEntities = studyCalonPegawaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itStudyCalonPegawaiEntities != null){
                StudyCalonPegawai returnRekruitmenStudy;
                // Looping from dao to object and save in collection
                for(ItStudyCalonPegawaiEntity itStudyCalonPegawaiEntity : itStudyCalonPegawaiEntities){
                    returnRekruitmenStudy = new StudyCalonPegawai();

                    returnRekruitmenStudy.setCalonPegawaiId(itStudyCalonPegawaiEntity.getCalonPegawaiId());
                    returnRekruitmenStudy.setTahunAwal(itStudyCalonPegawaiEntity.getTahunAwal());
                    returnRekruitmenStudy.setTahunAkhir(itStudyCalonPegawaiEntity.getTahunAkhir());
                    returnRekruitmenStudy.setTipeStudy(itStudyCalonPegawaiEntity.getTipeStudy());
                    returnRekruitmenStudy.setNilai(itStudyCalonPegawaiEntity.getNilai());
                    returnRekruitmenStudy.setStudyName(itStudyCalonPegawaiEntity.getStudyName());
                    returnRekruitmenStudy.setStudyCalonPegawaiId(itStudyCalonPegawaiEntity.getStudyCalonPegawaiId());

                    listOfResult.add(returnRekruitmenStudy);
                }
            }
        }
        logger.info("[RekruitmenStatusBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<RekruitmenStatus> getByCriteriaStatus(Rekruitmen searchBean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RekruitmenStatus> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getCalonPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getCalonPegawaiId())) {
                hsCriteria.put("calon_pegawai_id", searchBean.getCalonPegawaiId());
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


            List<ItRekruitmenStatusEntity> itRekruitmenStatusEntities = null;
            try {

                itRekruitmenStatusEntities = rekruitmenStatusDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itRekruitmenStatusEntities != null){
                RekruitmenStatus returnRekruitmenStatus;
                // Looping from dao to object and save in collection
                for(ItRekruitmenStatusEntity itRekruitmenStatusEntity : itRekruitmenStatusEntities){
                    returnRekruitmenStatus = new RekruitmenStatus();

                    returnRekruitmenStatus.setCalonPegawaiId(itRekruitmenStatusEntity.getCalonPegawaiId());
                    returnRekruitmenStatus.setRekruitmenStatusId(itRekruitmenStatusEntity.getRekruitmenStatusId());
                    returnRekruitmenStatus.setStatusRekruitmen(itRekruitmenStatusEntity.getStatusRekruitmen());
                    returnRekruitmenStatus.setKeterangan(itRekruitmenStatusEntity.getKeterangan());
                    returnRekruitmenStatus.setStatusRekruitmenName(itRekruitmenStatusEntity.getImStatusRekruitmentEntity().getStatusRekruitmentName());
                    returnRekruitmenStatus.setTanggalProses(itRekruitmenStatusEntity.getTanggalProses());

                    listOfResult.add(returnRekruitmenStatus);
                }
            }
        }
        logger.info("[RekruitmenStatusBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<Rekruitmen> getByCriteria(Rekruitmen searchBean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.getByCriteria] start process >>>");
        // Mapping with collection and put
        List<Rekruitmen> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getCalonPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getCalonPegawaiId())) {
                hsCriteria.put("calon_pegawai_id", searchBean.getCalonPegawaiId());
            }
            if (searchBean.getNamaCalonPegawai() != null && !"".equalsIgnoreCase(searchBean.getNamaCalonPegawai())) {
                hsCriteria.put("nama_calon_pegawai", searchBean.getNamaCalonPegawai());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getDepartmentId() != null && !"".equalsIgnoreCase(searchBean.getDepartmentId())) {
                hsCriteria.put("department_id", searchBean.getDepartmentId());
            }
            if (searchBean.getPositionId() != null && !"".equalsIgnoreCase(searchBean.getPositionId())) {
                hsCriteria.put("position_id", searchBean.getPositionId());
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


            List<ImRekruitmenEntity> imRekruitmenEntity = null;
            try {

                imRekruitmenEntity = rekruitmenDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imRekruitmenEntity != null){
                Rekruitmen returnRekruitmen;
                // Looping from dao to object and save in collection
                for(ImRekruitmenEntity imRekruitmenEntity1 : imRekruitmenEntity){
                    returnRekruitmen = new Rekruitmen();
                    String calonpegawaiId;
                    String statusterakhir = "1";
                    List<ItRekruitmenStatusEntity> statusAkhir;
                    calonpegawaiId = imRekruitmenEntity1.getCalonPegawaiId();
                    statusAkhir = rekruitmenStatusDao.getStatusAkhir(calonpegawaiId);
                    if (statusAkhir!=null){
                        for (ItRekruitmenStatusEntity itRekruitmenStatusEntity:statusAkhir){
                            statusterakhir =  itRekruitmenStatusEntity.getStatusRekruitmen();
                        }
                    }
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    if(imRekruitmenEntity1.getTanggalLahir() != null){
                        String stringTanggal  = dateFormat.format(imRekruitmenEntity1.getTanggalLahir());
                        returnRekruitmen.setStTanggalLahir(stringTanggal);
                    }else{
                        returnRekruitmen.setStTanggalLahir("");
                    }
                    if (statusterakhir.equals("10")){
                        returnRekruitmen.setReadyclosed(true);
                    }

                    returnRekruitmen.setCalonPegawaiId(imRekruitmenEntity1.getCalonPegawaiId());
                    returnRekruitmen.setNamaCalonPegawai(imRekruitmenEntity1.getNamaCalonPegawai());
                    returnRekruitmen.setGelarDepan(imRekruitmenEntity1.getGelarDepan());
                    returnRekruitmen.setGelarBelakang(imRekruitmenEntity1.getGelarBelakang());
                    returnRekruitmen.setNoKtp(imRekruitmenEntity1.getNoKtp());
                    returnRekruitmen.setAlamat(imRekruitmenEntity1.getAlamat());
                    returnRekruitmen.setRtRw(imRekruitmenEntity1.getRtRw());
                    returnRekruitmen.setNoTelp(imRekruitmenEntity1.getNoTelp());
                    returnRekruitmen.setJenisKelamin(imRekruitmenEntity1.getJenisKelamin());
                    returnRekruitmen.setJumlahAnak(imRekruitmenEntity1.getJumlahAnak());
                    returnRekruitmen.setBranchId(imRekruitmenEntity1.getBranchId());
                    returnRekruitmen.setStatusInput(imRekruitmenEntity1.getStatusInput());
                    returnRekruitmen.setPositionId(imRekruitmenEntity1.getPositionId());
                    returnRekruitmen.setDepartmentId(imRekruitmenEntity1.getDepartmentId());
                    returnRekruitmen.setTanggalLahir(imRekruitmenEntity1.getTanggalLahir());
                    returnRekruitmen.setTempatLahir(imRekruitmenEntity1.getTempatLahir());
                    returnRekruitmen.setTipePegawai(imRekruitmenEntity1.getTipePegawai());
                    returnRekruitmen.setFotoUpload("/hris/pages/upload/image/profile/"+imRekruitmenEntity1.getFotoUpload());
                    returnRekruitmen.setStatusGiling(imRekruitmenEntity1.getStatusGiling());
                    returnRekruitmen.setNoKontrak(imRekruitmenEntity1.getNoKontrak());
                    returnRekruitmen.setStatusPegawai(imRekruitmenEntity1.getStatusPegawai());
                    returnRekruitmen.setStatusKeluarga(imRekruitmenEntity1.getStatusKeluarga());
                    returnRekruitmen.setStatus(imRekruitmenEntity1.getStatusPegawai());

                    if(imRekruitmenEntity1.getProvinsiId()!=null){
                        returnRekruitmen.setProvinsiId(imRekruitmenEntity1.getImProvinsiEntity().getProvinsiId());
                        returnRekruitmen.setProvinsiName(imRekruitmenEntity1.getImProvinsiEntity().getProvinsiName());
                    }else{
                        returnRekruitmen.setProvinsiId("");
                        returnRekruitmen.setProvinsiName("");
                    }

                    if(imRekruitmenEntity1.getKotaId() != null){
                        returnRekruitmen.setKotaName(imRekruitmenEntity1.getImKotaEntity().getKotaName());
                        returnRekruitmen.setKotaId(imRekruitmenEntity1.getImKotaEntity().getKotaId());
                    }else{
                        returnRekruitmen.setKotaId("");
                        returnRekruitmen.setKotaName("");
                    }
                    if(imRekruitmenEntity1.getKecamatanId() != null){
                        returnRekruitmen.setKecamatanName(imRekruitmenEntity1.getImKecamatanEntity().getKecamatanName());
                        returnRekruitmen.setKecamatanId(imRekruitmenEntity1.getImKecamatanEntity().getKecamatanId());
                    }else{
                        returnRekruitmen.setKecamatanId("");
                        returnRekruitmen.setKecamatanName("");
                    }
                    if(imRekruitmenEntity1.getDesaId() != null){
                        returnRekruitmen.setDesaId(imRekruitmenEntity1.getImDesaEntity().getDesaId());
                        returnRekruitmen.setDesaName(imRekruitmenEntity1.getImDesaEntity().getDesaName());
                    }else{
                        returnRekruitmen.setDesaId("");
                        returnRekruitmen.setDesaName("");
                    }
                    if(imRekruitmenEntity1.getTipePegawai() != null){
                        returnRekruitmen.setTipePegawai(imRekruitmenEntity1.getImHrisTipePegawai().getTipePegawaiId());
                    }else{
                        returnRekruitmen.setTipePegawai("");
                    }
                    if (imRekruitmenEntity1.getClosed().equals("Y")){
                        returnRekruitmen.setRekruitmenClosed(true);
                    }

                    returnRekruitmen.setPosisiName(imRekruitmenEntity1.getImPosition().getPositionName());
                    returnRekruitmen.setDivisiName(imRekruitmenEntity1.getImDepartmentEntity().getDepartmentName());
                    returnRekruitmen.setBranchName(imRekruitmenEntity1.getImBranches().getBranchName());
                    returnRekruitmen.setCreatedWho(imRekruitmenEntity1.getCreatedWho());
                    returnRekruitmen.setCreatedDate(imRekruitmenEntity1.getCreatedDate());
                    returnRekruitmen.setLastUpdate(imRekruitmenEntity1.getLastUpdate());
                    returnRekruitmen.setAction(imRekruitmenEntity1.getAction());
                    returnRekruitmen.setFlag(imRekruitmenEntity1.getFlag());
                    returnRekruitmen.setStatusPegawai(imRekruitmenEntity1.getStatusPegawai());


                    List<ItRekruitmenStatusEntity> statusEntityList = new ArrayList<>();
                    statusEntityList=rekruitmenStatusDao.getStatusSaatIni(returnRekruitmen.getCalonPegawaiId());
                    for (ItRekruitmenStatusEntity rekruitmenStatusEntity : statusEntityList){
                        if (rekruitmenStatusEntity.getStatusRekruitmen()!=null){
                            BigInteger status = new BigInteger(rekruitmenStatusEntity.getStatusRekruitmen());
                            List<ImStatusRekruitmentEntity> statusRekruitmentEntity = statusRekruitmentDao.getListStatusRekruitment(status);
                            for (ImStatusRekruitmentEntity statusRekruitmentEntity1 : statusRekruitmentEntity){
                                returnRekruitmen.setStatus(rekruitmenStatusEntity.getStatusRekruitmen());
                                returnRekruitmen.setStatusSaatIni(statusRekruitmentEntity1.getStatusRekruitmentName());
                            }
                        }
                    }
                    if (searchBean.getStatus()!=null){
                        if (!("").equalsIgnoreCase(searchBean.getStatus())){
                            if (searchBean.getStatus().equalsIgnoreCase(returnRekruitmen.getStatus())){
                                listOfResult.add(returnRekruitmen);
                            }
                        }else{
                            if (returnRekruitmen.getStatus().equalsIgnoreCase("12")){
                            }else {
                                listOfResult.add(returnRekruitmen);
                            }
                        }
                    }else{
                        listOfResult.add(returnRekruitmen);
                    }
                }
            }
        }
        logger.info("[RekruitmenBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Rekruitmen> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Rekruitmen> getComboRekruitmenWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Rekruitmen> listComboRekruitmen = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImRekruitmenEntity> listPersonal = null;
        try {
            listPersonal = rekruitmenDao.getListPersonal(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImRekruitmenEntity imRekruitmenEntity : listPersonal) {
                Rekruitmen itemComboRekruitmen = new Rekruitmen();
//                itemComboRekruitmen.setNip(imRekruitmenEntity.getNip());
//                itemComboRekruitmen.setNamaPegawai(imRekruitmenEntity.getNamaPegawai());
                listComboRekruitmen.add(itemComboRekruitmen);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboRekruitmen;
    }

    @Override
    public List<Rekruitmen> getListOfPersonil(String query, String branchId) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Rekruitmen> listComboRekruitmen = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImRekruitmenEntity> listPersonal = null;
        try {
            if (!"".equalsIgnoreCase(branchId)){
                listPersonal = rekruitmenDao.getListPersonalByBranch(query, branchId);
            } else {
                listPersonal = rekruitmenDao.getListPersonal(query);
            }
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImRekruitmenEntity imRekruitmenEntity : listPersonal) {
                Rekruitmen itemComboRekruitmen = new Rekruitmen();
                String date = "";
//                if(imRekruitmenEntity.getTanggalAktif() != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                    date = dateFormat.format(imRekruitmenEntity.getTanggalAktif());
//                    itemComboRekruitmen.setStTanggalAktif(date);
                }
//                itemComboRekruitmen.setNip(imRekruitmenEntity.getNip());
//                itemComboRekruitmen.setNamaPegawai(imRekruitmenEntity.getNamaPegawai());
//                itemComboRekruitmen.setStatusPegawai(imRekruitmenEntity.getStatusPegawai());
//                itemComboRekruitmen.setBranch(imRekruitmenEntity.getBranch());

                Map hsCriteria = new HashMap();
//                hsCriteria.put("nip",imRekruitmenEntity.getNip());
                hsCriteria.put("flag","Y");

                List<ItPersonilPositionEntity> itPersonilPositionEntityList = null;
                try {
                    itPersonilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                }
                if (itPersonilPositionEntityList!=null){
                    for ( ItPersonilPositionEntity listOfPersonil:itPersonilPositionEntityList){
//                        itemComboRekruitmen.setDivisi(listOfPersonil.getDivisiId());
//                        itemComboRekruitmen.setPositionId(listOfPersonil.getPositionId());
//                        itemComboRekruitmen.setBranch(listOfPersonil.getBranchId());
                    }
                }

//                itemComboRekruitmen.setDivisi(imRekruitmenEntity.getDivisi());
//                itemComboRekruitmen.setTanggalAktif(imRekruitmenEntity.getTanggalAktif());
//                itemComboRekruitmen.setGolonganId(imRekruitmenEntity.getGolongan());
//                itemComboRekruitmen.setPositionId(imRekruitmenEntity.getPositionId());
//                listComboRekruitmen.add(itemComboRekruitmen);

            }

        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboRekruitmen;
    }


    public List<Rekruitmen> getListOfPersonilPosition(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Rekruitmen> listComboRekruitmen = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImRekruitmenEntity> listPersonal = null;
        try {
            listPersonal = rekruitmenDao.getListPersonalByBranch2(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImRekruitmenEntity imRekruitmenEntity : listPersonal) {
                Rekruitmen itemComboRekruitmen = new Rekruitmen();
//                itemComboRekruitmen.setNip(imRekruitmenEntity.getNip());
//                itemComboRekruitmen.setNamaPegawai(imRekruitmenEntity.getNamaPegawai());
                itemComboRekruitmen.setStatusPegawai(imRekruitmenEntity.getStatusPegawai());
//                ItPersonilPositionEntity itPersonilPositionEntity = (ItPersonilPositionEntity) personilPositionDao.getById("nip",imRekruitmenEntity.getNip(),"Y");

//                itemComboRekruitmen.setDivisi(itPersonilPositionEntity.getDivisiId());
//                itemComboRekruitmen.setBranch(itPersonilPositionEntity.getBranchId());
//                itemComboRekruitmen.setPositionId(itPersonilPositionEntity.getPositionId());
                listComboRekruitmen.add(itemComboRekruitmen);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboRekruitmen;
    }

    @Override
    public List<TrainingPerson> getListTrainingPerson(TrainingPerson bean) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.getListTrainingPerson] start process <<<");
        List<TrainingPerson> result = new ArrayList<TrainingPerson>();
        if (bean != null){
            Map hsCriteria = new HashMap();
            if (bean.getPersonId() != null && !"".equalsIgnoreCase(bean.getPersonId())){
                hsCriteria.put("person_id", bean.getPersonId());
                hsCriteria.put("flag", bean.getFlag());
            }

            List<ItHrisTrainingPersonEntity> trainingPersonEntities = null;

            try {
                trainingPersonEntities = trainingPersonDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }

            if (trainingPersonEntities != null){
                TrainingPerson addData;
                for (ItHrisTrainingPersonEntity listData : trainingPersonEntities){
                    if (listData.getApprovalFlag() != null && listData.getApprovalBosFlag() != null){
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

                        if (listData.getTrainingId() != null){
                            hsCriteria = new HashMap();
                            hsCriteria.put("training_id",listData.getTrainingId());
                            hsCriteria.put("flag","Y");
                            List<ItHrisTrainingEntity> dataTraining = null;
                            try {
                                dataTraining = trainingDao.getByCriteria(hsCriteria);
                            } catch (HibernateException e) {
                                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                            }

                            if (dataTraining != null){
                                for (ItHrisTrainingEntity listTraining : dataTraining){
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
        }

        logger.info("[RekruitmenBoImpl.getListTrainingPerson] end process <<<");

        return result;
    }

    @Override
    public List<Rekruitmen> getListOfRsKelas(String query, String status, String nip, String golonganId) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Rekruitmen> result = new ArrayList<Rekruitmen>();

        List<Rekruitmen> listComboRekruitmen = new ArrayList();
        String criteria = "%" + query + "%";

        boolean notFound = false;
        String kelompokId = "";
        if (status.equalsIgnoreCase("KS")){
            Map hsCriteria = new HashMap();
            hsCriteria.put("nip",nip);
            hsCriteria.put("flag","Y");

            List<ImRekruitmenEntity> listRekruitmen = null;
            try {
                listRekruitmen = rekruitmenDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }

            String id = "";
            if (listRekruitmen != null){
                for (ImRekruitmenEntity listBio :listRekruitmen){
//                    id = listBio.getNip();
                }
            }

            if (nip != ""){
                hsCriteria = new HashMap();
                hsCriteria.put("nip",id);
                hsCriteria.put("flag","Y");

                List<ItPersonilPositionEntity>personilPositionEntities = null;
                try {
                    personilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                }

                String position = "";
                if (personilPositionEntities != null){
                    for (ItPersonilPositionEntity listPersoniPosition : personilPositionEntities){
                        position = listPersoniPosition.getPositionId();
                    }
                }

                if (position != null){
                    hsCriteria = new HashMap();
                    hsCriteria.put("position_id", position);
                    hsCriteria.put("flag","Y");

                    List<ImPosition> positions = null;
                    try {
                        positions = positionDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                    }

                    if (positions != null){
                        for (ImPosition listPosition : positions){
                            kelompokId = listPosition.getKelompokId();
                        }
                    }

                    if (kelompokId != null){
                        List<ImHrisRsKelas> rsKelases = null;
                        try {
                            rsKelases = rsKelasDao.getListRskelasByKelompok(criteria, kelompokId);
                        } catch (HibernateException e) {
                            logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                        }

                        if (rsKelases != null){
                            Rekruitmen rekruitmen;
                            for (ImHrisRsKelas listKelas : rsKelases){
                                rekruitmen = new Rekruitmen();
//                                rekruitmen.setRsKerjaSama(listKelas.getRsId());

                                hsCriteria = new HashMap();
                                hsCriteria.put("rs_id",listKelas.getRsId());
                                hsCriteria.put("flag","Y");

                                List<ImRsKerjasamaEntity> imRsKerjasamaEntityList = null;

                                try {
                                    imRsKerjasamaEntityList = rsKerjasamaDao.getByCriteria(hsCriteria);
                                } catch (HibernateException e) {
                                    logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                                }

                                if (imRsKerjasamaEntityList != null){
                                    for (ImRsKerjasamaEntity listRs : imRsKerjasamaEntityList){
//                                        rekruitmen.setRsName(listRs.getRsName());
                                    }
                                }

//                                rekruitmen.setRsKelas(listKelas.getRsKelasId());
//                                rekruitmen.setRsKelasName(listKelas.getKelas());
                                result.add(rekruitmen);
                            }
                        }
                    }
                }
            }
        }
        if (result.isEmpty()){
            notFound = true;
        }

        if (notFound){
            List<ImHrisRsKelas> rsKelases = null;
            try {
                rsKelases = rsKelasDao.getListRskelasByGolongan(criteria, golonganId);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }

            if (rsKelases != null){
                Rekruitmen rekruitmen;
                for (ImHrisRsKelas listKelas : rsKelases){
                    rekruitmen = new Rekruitmen();
//                    rekruitmen.setRsKerjaSama(listKelas.getRsId());

                    Map hsCriteria = new HashMap();
                    hsCriteria.put("rs_id",listKelas.getRsId());
                    hsCriteria.put("flag","Y");

                    List<ImRsKerjasamaEntity> imRsKerjasamaEntityList = null;

                    try {
                        imRsKerjasamaEntityList = rsKerjasamaDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                    }

                    if (imRsKerjasamaEntityList != null){
                        for (ImRsKerjasamaEntity listRs : imRsKerjasamaEntityList){
//                            rekruitmen.setRsName(listRs.getRsName());
                        }
                    }

//                    rekruitmen.setRsKelas(listKelas.getRsKelasId());
//                    rekruitmen.setRsKelasName(listKelas.getKelas());
                    result.add(rekruitmen);
                }
            }
        }


//        if("KNS".equalsIgnoreCase(kelompokId) && !"".equalsIgnoreCase(golonganId)){
//
//        }

        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return result;
    }
    @Override
    public Rekruitmen getPrintRekruitmen(String id) throws GeneralBOException {
        Rekruitmen hasil = new Rekruitmen();
        List<ImRekruitmenEntity> data = new ArrayList<>();
        if (id != null){
            String rekruitmenId = id;
            Map hsCriteria = new HashMap();
            hsCriteria.put("calon_pegawai_id",rekruitmenId);
            hsCriteria.put("flag","Y");
            try {
                data = rekruitmenDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if(data!=null){
                for(ImRekruitmenEntity imRekruitmenEntity:data){
                    hasil.setCalonPegawaiId(imRekruitmenEntity.getCalonPegawaiId());
                    hasil.setNamaCalonPegawai(imRekruitmenEntity.getNamaCalonPegawai());
                    hasil.setGelarDepan(imRekruitmenEntity.getGelarDepan());
                    hasil.setGelarBelakang(imRekruitmenEntity.getGelarBelakang());
                    hasil.setNoKtp(imRekruitmenEntity.getNoKtp());
                    hasil.setAlamat(imRekruitmenEntity.getAlamat());
                    hasil.setRtRw(imRekruitmenEntity.getRtRw());
                    hasil.setTanggalLahir(imRekruitmenEntity.getTanggalLahir());
                    hasil.setTempatLahir(imRekruitmenEntity.getTempatLahir());
                    hasil.setFotoUpload(imRekruitmenEntity.getFotoUpload());
                    hasil.setNoTelp(imRekruitmenEntity.getNoTelp());
                    hasil.setJenisKelamin(imRekruitmenEntity.getJenisKelamin());
                    hasil.setJumlahAnak(imRekruitmenEntity.getJumlahAnak());
                    hasil.setBranchId(imRekruitmenEntity.getBranchId());
                    hasil.setStatusInput(imRekruitmenEntity.getStatusInput());
                    hasil.setPositionId(imRekruitmenEntity.getPositionId());
                    hasil.setDepartmentId(imRekruitmenEntity.getDepartmentId());
                    hasil.setStatusPegawai(imRekruitmenEntity.getStatusPegawai());
                    hasil.setTipePegawai(imRekruitmenEntity.getTipePegawai());
                    hasil.setStatusGiling(imRekruitmenEntity.getStatusGiling());
                    hasil.setNoKontrak(imRekruitmenEntity.getNoKontrak());
                    hasil.setClosed(imRekruitmenEntity.getClosed());
                    hasil.setProvinsiId(imRekruitmenEntity.getProvinsiId());
                    hasil.setKotaId(imRekruitmenEntity.getKotaId());
                    hasil.setKecamatanId(imRekruitmenEntity.getKecamatanId());
                    hasil.setDesaId(imRekruitmenEntity.getDesaId());
                    hasil.setUpah(imRekruitmenEntity.getUpah());
                    hasil.setStTanggalLahir(CommonUtil.convertDateToString(imRekruitmenEntity.getTanggalLahir()));
                    if (imRekruitmenEntity.getKontrakDari()!=null){
                        hasil.setStKontrakDari(CommonUtil.convertDateToString(imRekruitmenEntity.getKontrakDari()));
                    }
                    if (imRekruitmenEntity.getKontrakSelesai()!=null){
                        hasil.setStKontrakSelesai(CommonUtil.convertDateToString(imRekruitmenEntity.getKontrakSelesai()));
                    }
                    if (imRekruitmenEntity.getProvinsiId()!=null){
                        hasil.setProvinsiName(imRekruitmenEntity.getImProvinsiEntity().getProvinsiName());
                    }
                    if (imRekruitmenEntity.getKotaId()!=null){
                        hasil.setKotaName(imRekruitmenEntity.getImKotaEntity().getKotaName());
                    }
                    if (imRekruitmenEntity.getKecamatanId()!=null){
                        hasil.setKecamatanName(imRekruitmenEntity.getImKecamatanEntity().getKecamatanName());
                    }
                    if (imRekruitmenEntity.getDesaId()!=null){
                        hasil.setDesaName(imRekruitmenEntity.getImDesaEntity().getDesaName());
                    }
                    if (imRekruitmenEntity.getPositionId()!=null){
                        hasil.setPosisiName(imRekruitmenEntity.getImPosition().getPositionName());

                        ImPositionBagianEntity positionBagianEntity = positionBagianDao.getById("bagianId",imRekruitmenEntity.getImPosition().getBagianId());
                        hasil.setBagianName(positionBagianEntity.getBagianName());

                    }
                    if (imRekruitmenEntity.getBranchId()!=null){
                        ImBranches imBranches = null;
                        ImBranchesPK primaryKey = new ImBranchesPK();
                        primaryKey.setId(imRekruitmenEntity.getBranchId());
                        imBranches = branchDao.getById(primaryKey, "Y");

                        hasil.setBranchName(imBranches.getBranchName());
                        hasil.setAlamatUnit(imBranches.getBranchAddress());
                    }
                    if (imRekruitmenEntity.getStatusKeluarga()!=null){
                        switch (imRekruitmenEntity.getStatusKeluarga()){
                            case "K":
                                hasil.setStatusKeluarga("Kawin");
                                break;
                            case "B":
                                hasil.setStatusKeluarga("Belum Kawin");
                                break;
                            default:
                                hasil.setStatusKeluarga("-");
                        }
                    }
                    if (imRekruitmenEntity.getJenisKelamin()!=null){
                        switch (imRekruitmenEntity.getJenisKelamin()){
                            case "L":
                                hasil.setJenisKelamin("Laki - Laki");
                                break;
                            case "P":
                                hasil.setJenisKelamin("Perempuan");
                                break;

                            default:
                                hasil.setJenisKelamin("-");

                        }
                    }

                    hasil.setNip(imRekruitmenEntity.getNip());
                    hasil.setTanggalAktif(imRekruitmenEntity.getTanggalAktif());
                    hasil.setPoin(imRekruitmenEntity.getPoin());
                    hasil.setGolongan(imRekruitmenEntity.getGolongan());
                    hasil.setKontrakDari(imRekruitmenEntity.getKontrakDari());
                    hasil.setKontrakSelesai(imRekruitmenEntity.getKontrakSelesai());
                }
            }
        }
        return hasil;
    }
    @Override
    public RekruitmenStatus getStatusById(String capegId , String statusId) throws GeneralBOException {
        logger.info("[RekruitmenBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RekruitmenStatus> listOfResult = new ArrayList();
        RekruitmenStatus result = new RekruitmenStatus();
        if (statusId != null) {
            Map hsCriteria = new HashMap();
            hsCriteria.put("calon_pegawai_id",capegId);
            hsCriteria.put("status_rekruitmen",statusId);
            hsCriteria.put("flag","Y");
            List<ItRekruitmenStatusEntity> itRekruitmenStatusEntities = null;
            try {
                itRekruitmenStatusEntities = rekruitmenStatusDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenBoImpl.getStatusById] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itRekruitmenStatusEntities != null){
                // Looping from dao to object and save in collection
                for(ItRekruitmenStatusEntity itRekruitmenStatusEntity : itRekruitmenStatusEntities){

                    result.setCalonPegawaiId(itRekruitmenStatusEntity.getCalonPegawaiId());
                    result.setRekruitmenStatusId(itRekruitmenStatusEntity.getRekruitmenStatusId());
                    result.setStatusRekruitmen(itRekruitmenStatusEntity.getStatusRekruitmen());
                    result.setKeterangan(itRekruitmenStatusEntity.getKeterangan());
                    result.setStatusRekruitmenName(itRekruitmenStatusEntity.getImStatusRekruitmentEntity().getStatusRekruitmentName());
                    result.setTanggalProses(itRekruitmenStatusEntity.getTanggalProses());
                }
            }
        }
        logger.info("[RekruitmenStatusBoImpl.getByCriteria] end process <<<");

        return result;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakJangkaWaktu(String flag, String kontrakDari, String kontrakSelesai, String bagianName, String branchName, String alamatUnit) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakJangkaWaktu] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "2");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakJangkaWaktu] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());
                rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());
                rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$tanggalDari",kontrakDari));
                rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$tanggalSelesai",kontrakSelesai));
                rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$bagian",bagianName));
                rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$unit",branchName));
                rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$alamatUnit",alamatUnit));
                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);

            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakJangkaWaktu] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakUpah(String flag,BigInteger upah) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakUpah] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "3");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakUpah] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());
                rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());

                String stUpah = CommonUtil.numbericFormat(new BigDecimal(upah), "###,###");
                String upahTerbilang = CommonUtil.angkaToTerbilang(upah.longValue());

                rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$upah",stUpah));
                rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$terbilang",upahTerbilang));

                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);
            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakUpah] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakFasilitas(String flag, String positionId, String golongan, String branchId) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakFasilitas] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "4");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakFasilitas] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());
                rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());

                if (!("").equalsIgnoreCase(positionId)){
                    ImPosition imPosition = new ImPosition();
                    try {
                        imPosition = positionDao.getById("positionId",positionId);
                    } catch (HibernateException e) {
                        logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakFasilitas] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                    String stKelompok = imPosition.getKelompokId();
                    if (stKelompok != null) {
                        List<ImPerjalananDinasEntity> biayaMakanList = new ArrayList<>();
                        try {
                            biayaMakanList = perjalananDinasDao.getListPerjalananDinasSppd(stKelompok,golongan,"BPD01","DN");
                        } catch (HibernateException e) {
                            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakFasilitas] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }

                        List<ImPerjalananDinasEntity> biayaLainlainList = new ArrayList<>();
                        try {
                            biayaLainlainList = perjalananDinasDao.getListPerjalananDinasSppd(stKelompok,golongan,"BPD01","DN");
                        } catch (HibernateException e) {
                            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakFasilitas] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }

                        String biayaMakan = "";
                        String biayaMakanTerbilang = "";
                        for (ImPerjalananDinasEntity perjalananDinasEntity:biayaMakanList){
                            biayaMakan = CommonUtil.numbericFormat(perjalananDinasEntity.getJumlahBiaya(), "###,###");
                            biayaMakanTerbilang = CommonUtil.angkaToTerbilang(perjalananDinasEntity.getJumlahBiaya().longValue());
                        }
                        rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$uangmakansppd",biayaMakan));
                        rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$terbilangmakansppd",biayaMakanTerbilang));

                        String biayaLainLain = "";
                        String biayaLainLainTerbilang = "";
                        for (ImPerjalananDinasEntity perjalananDinasEntity:biayaLainlainList){
                            biayaLainLain = CommonUtil.numbericFormat(perjalananDinasEntity.getJumlahBiaya(), "###,###");
                            biayaLainLainTerbilang = CommonUtil.angkaToTerbilang(perjalananDinasEntity.getJumlahBiaya().longValue());
                        }
                        rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$upahsppd",biayaLainLain));
                        rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$terbilangsppd",biayaLainLainTerbilang));
                    }
                }

                if (!("").equalsIgnoreCase(branchId)){
                    List<ImBranches> branchesList = new ArrayList<>();
                    try {
                        branchesList = branchDao.getListBranchById(branchId);
                    } catch (HibernateException e) {
                        logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakFasilitas] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                    String uangMakan = "";
                    String uangMakanTerbilang = "";
                    for (ImBranches branches:branchesList){
                        uangMakan = CommonUtil.numbericFormat(branches.getUangMakan(), "###,###");
                        uangMakanTerbilang = CommonUtil.angkaToTerbilang(branches.getUangMakan().longValue());
                    }
                    rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$uangmakanabsen",uangMakan));
                    rekruitmenKontrak.setIsi(rekruitmenKontrak.getIsi().replace("$terbilanguangmakanabsen",uangMakanTerbilang));
                }

                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);
            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakFasilitas] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakKewajibanPihakPertama(String flag) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakKewajibanPihakPertama] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "5");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakKewajibanPihakPertama] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());
                rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());
                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);
            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakKewajibanPihakPertama] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakKewajibanPihakKedua(String flag) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakKewajibanPihakKedua] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "6");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakKewajibanPihakKedua] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());
                rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());
                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);
            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakKewajibanPihakKedua] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakCutiLembur(String flag) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakCutiLembur] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "7");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakCutiLembur] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());
                rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());
                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);
            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakCutiLembur] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakTatib(String flag) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakTatib] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "8");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakTatib] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());

                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());
                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);
            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakTatib] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakPemutusanHubungan(String flag) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakPemutusanHubungan] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "9");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakPemutusanHubungan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());
                rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());
                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);
            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakPemutusanHubungan] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakPenutup(String flag) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakPenutup] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "10");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakPenutup] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());
                rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());
                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);
            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakPenutup] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    @Override
    public List<RekruitmenKontrak> getListRekruitmenKontrakPeraturan(String flag) throws GeneralBOException {
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakPeraturan] start process >>>");

        // Mapping with collection and put
        List<RekruitmenKontrak> listOfResultRekruitmenKontrak = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("pasal", "11");

        List<ItRekruitmenKontrakEntity> rekruitmenKontrakEntityList= null;
        try {
            rekruitmenKontrakEntityList = rekruitmenKontrakDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[rekruitmenBoImpl.getListRekruitmenKontrakPeraturan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(rekruitmenKontrakEntityList != null){
            // Looping from dao to object and save in collection
            for(ItRekruitmenKontrakEntity rekruitmenKontrakEntity : rekruitmenKontrakEntityList){
                RekruitmenKontrak rekruitmenKontrak = new RekruitmenKontrak();
                rekruitmenKontrak.setRekruitmenKontrakId(rekruitmenKontrakEntity.getRekruitmenKontrakId());
                rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                rekruitmenKontrak.setIsi(rekruitmenKontrakEntity.getIsi());
                rekruitmenKontrak.setPasal(rekruitmenKontrakEntity.getPasal());

                if (rekruitmenKontrakEntity.getNo().equalsIgnoreCase("8a")||rekruitmenKontrakEntity.getNo().equalsIgnoreCase("7a")){
                    rekruitmenKontrak.setNo("");
                }else{
                    rekruitmenKontrak.setNo(rekruitmenKontrakEntity.getNo());
                }

                rekruitmenKontrak.setCreatedWho(rekruitmenKontrakEntity.getCreatedWho());
                rekruitmenKontrak.setCreatedDate(rekruitmenKontrakEntity.getCreatedDate());
                rekruitmenKontrak.setLastUpdate(rekruitmenKontrakEntity.getLastUpdate());
                rekruitmenKontrak.setAction(rekruitmenKontrakEntity.getAction());
                rekruitmenKontrak.setFlag(rekruitmenKontrakEntity.getFlag());
                listOfResultRekruitmenKontrak.add(rekruitmenKontrak);
            }
        }
        logger.info("[rekruitmenBoImpl.getListRekruitmenKontrakPeraturan] end process <<<");

        return listOfResultRekruitmenKontrak;
    }
    public void setIjinKeluarDao(IjinKeluarDao ijinKeluarDao) {
        this.ijinKeluarDao = ijinKeluarDao;
    }

    public IjinKeluarDao getIjinKeluarDao() {
        return ijinKeluarDao;
    }

    public void setCutiPegawai(RsKerjasamaDao cutiPegawai) {
        this.cutiPegawai = cutiPegawai;
    }

    public RsKerjasamaDao getCutiPegawai() {
        return cutiPegawai;
    }

    public void setNotifikasi(RsKerjasamaDao notifikasi) {
        this.notifikasi = notifikasi;
    }

    public RsKerjasamaDao getNotifikasi() {
        return notifikasi;
    }
}