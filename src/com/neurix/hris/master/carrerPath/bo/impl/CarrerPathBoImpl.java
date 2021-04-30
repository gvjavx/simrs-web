package com.neurix.hris.master.carrerPath.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.carrerPath.bo.CarrerPathBo;
import com.neurix.hris.master.carrerPath.dao.CarrerPathDao;
import com.neurix.hris.master.carrerPath.model.CarrerPath;
import com.neurix.hris.master.kualifikasiCalonPejabat.dao.KualifikasiCalonPejabatDao;
import com.neurix.hris.master.kualifikasiCalonPejabat.model.ImHrisKualifikasiCalonPejabatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class CarrerPathBoImpl implements CarrerPathBo {

    protected static transient Logger logger = Logger.getLogger(CarrerPathBoImpl.class);
    private CarrerPathDao studyJurusanDao;
    private KualifikasiCalonPejabatDao kualifikasiCalonPejabatDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CarrerPathBoImpl.logger = logger;
    }

    public CarrerPathDao getStudyJurusanDao() {
        return studyJurusanDao;
    }

    public void setStudyJurusanDao(CarrerPathDao studyJurusanDao) {
        this.studyJurusanDao = studyJurusanDao;
    }

    public KualifikasiCalonPejabatDao getKualifikasiCalonPejabatDao() {
        return kualifikasiCalonPejabatDao;
    }

    public void setKualifikasiCalonPejabatDao(KualifikasiCalonPejabatDao kualifikasiCalonPejabatDao) {
        this.kualifikasiCalonPejabatDao = kualifikasiCalonPejabatDao;
    }

    @Override
    public void saveDelete(CarrerPath bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String studyJurusanId = bean.getCarrerPathId();

            ImHrisKualifikasiCalonPejabatEntity imHrisKualifikasiCalonPejabatEntity = null;

            try {
                // Get data from database by ID
                imHrisKualifikasiCalonPejabatEntity = kualifikasiCalonPejabatDao.getById("kualifikasiId", studyJurusanId);
            } catch (HibernateException e) {
                logger.error("[StudyJurusanBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imHrisKualifikasiCalonPejabatEntity != null) {

                imHrisKualifikasiCalonPejabatEntity.setFlag(bean.getFlag());
                imHrisKualifikasiCalonPejabatEntity.setAction(bean.getAction());
                imHrisKualifikasiCalonPejabatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imHrisKualifikasiCalonPejabatEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    kualifikasiCalonPejabatDao.updateAndSave(imHrisKualifikasiCalonPejabatEntity);
                } catch (HibernateException e) {
                    logger.error("[StudyJurusanBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data StudyJurusan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[StudyJurusanBoImpl.saveDelete] Error, not found data StudyJurusan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data StudyJurusan with request id, please check again your data ...");

            }
        }
        logger.info("[StudyJurusanBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(CarrerPath bean) throws GeneralBOException {
        logger.info("[StudyJurusanBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String studyJurusanId = bean.getCarrerPathId();
            String idHistory = "";
            ImHrisKualifikasiCalonPejabatEntity imStudyJurusanEntity = null;
            try {
                // Get data from database by ID
                imStudyJurusanEntity = kualifikasiCalonPejabatDao.getById("kualifikasiId", studyJurusanId);
            } catch (HibernateException e) {
                logger.error("[StudyJurusanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data StudyJurusan by Kode StudyJurusan, please inform to your admin...," + e.getMessage());
            }

            if (imStudyJurusanEntity != null) {

                imStudyJurusanEntity.setJabatanId(bean.getJabatanId());
                imStudyJurusanEntity.setDivisiId(bean.getDivisiId());
                imStudyJurusanEntity.setBagianId(bean.getBagianId());
                imStudyJurusanEntity.setThMinKerja(bean.getThMinKerja());
                imStudyJurusanEntity.setThMinBidang(bean.getThMinBidang());
                imStudyJurusanEntity.setThMinBagian(bean.getThMinBagian());
                imStudyJurusanEntity.setBranchId(bean.getBranchId());
                imStudyJurusanEntity.setPendidikan(bean.getPendidikan());
                imStudyJurusanEntity.setPelatihanJabatan(bean.getPelatihanJabatan());
                imStudyJurusanEntity.setJurusanId(bean.getJurusanIdAdd());
                imStudyJurusanEntity.setKelompokPositionId(bean.getKelompokPositionId());
                imStudyJurusanEntity.setGolonganId(bean.getGolonganId());
                imStudyJurusanEntity.setKeterangan(bean.getKeterangan());

                imStudyJurusanEntity.setFlag(bean.getFlag());
                imStudyJurusanEntity.setAction(bean.getAction());
                imStudyJurusanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStudyJurusanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    kualifikasiCalonPejabatDao.updateAndSave(imStudyJurusanEntity);
                } catch (HibernateException e) {
                    logger.error("[StudyJurusanBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data StudyJurusan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[StudyJurusanBoImpl.saveEdit] Error, not found data StudyJurusan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data StudyJurusan with request id, please check again your data ...");
            }
        }
        logger.info("[StudyJurusanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public CarrerPath saveAdd(CarrerPath bean) throws GeneralBOException {
        logger.info("[StudyJurusanBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String studyJurusanId;
            try {
                // Generating ID, get from postgre sequence
                studyJurusanId = kualifikasiCalonPejabatDao.getNextCarrerPathId();
            } catch (HibernateException e) {
                logger.error("[StudyJurusanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence studyJurusanId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImHrisKualifikasiCalonPejabatEntity kualifikasi = new ImHrisKualifikasiCalonPejabatEntity();

            kualifikasi.setKualifikasiId(studyJurusanId);
            kualifikasi.setJabatanId(bean.getJabatanId());
            kualifikasi.setDivisiId(bean.getDivisiId());
            kualifikasi.setBagianId(bean.getBagianId());
            kualifikasi.setThMinKerja(bean.getThMinKerja());
            kualifikasi.setThMinBidang(bean.getThMinBidang());
            kualifikasi.setThMinBagian(bean.getThMinBagian());
            kualifikasi.setBranchId(bean.getBranchId());
            kualifikasi.setPendidikan(bean.getPendidikan());
            kualifikasi.setJurusanId(bean.getJurusanIdAdd());
            kualifikasi.setPelatihanJabatan(bean.getPelatihanJabatan());
            kualifikasi.setKelompokPositionId(bean.getKelompokPositionId());
            kualifikasi.setGolonganId(bean.getGolonganId());
            kualifikasi.setKeterangan(bean.getKeterangan());

            kualifikasi.setFlag(bean.getFlag());
            kualifikasi.setAction(bean.getAction());
            kualifikasi.setCreateWho(bean.getCreatedWho());
            kualifikasi.setLastUpdateWho(bean.getLastUpdateWho());
            kualifikasi.setCreatedDate(bean.getCreatedDate());
            kualifikasi.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                kualifikasiCalonPejabatDao.addAndSave(kualifikasi);
            } catch (HibernateException e) {
                logger.error("[StudyJurusanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data StudyJurusan, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[StudyJurusanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<CarrerPath> getByCriteria(CarrerPath searchBean) throws GeneralBOException {
        logger.info("[StudyJurusanBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<CarrerPath> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getCarrerPathId() != null && !"".equalsIgnoreCase(searchBean.getCarrerPathId())) {
                hsCriteria.put("kualifikasi_id", searchBean.getCarrerPathId());
            }

            if (searchBean.getJurusanId() != null && !"".equalsIgnoreCase(searchBean.getJurusanId())) {
                hsCriteria.put("jurusan_id", searchBean.getJurusanId());
            }

            if (searchBean.getJabatanId() != null && !"".equalsIgnoreCase(searchBean.getJabatanId())) {
                hsCriteria.put("jabatan_id", searchBean.getJabatanId());
            }

            if (searchBean.getDivisiId() != null && !"".equalsIgnoreCase(searchBean.getDivisiId())) {
                hsCriteria.put("divisi_id", searchBean.getDivisiId());
            }

            if (searchBean.getBagianId() != null && !"".equalsIgnoreCase(searchBean.getBagianId())) {
                hsCriteria.put("bagian_id", searchBean.getBagianId());
            }

            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
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


            List<ImHrisKualifikasiCalonPejabatEntity> kualifikasiCalonPejabatEntities = null;
            try {

                kualifikasiCalonPejabatEntities = kualifikasiCalonPejabatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[StudyJurusanBoImpl.getSearchStudyJurusanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(kualifikasiCalonPejabatEntities != null){
                CarrerPath returnStudyJurusan;
                // Looping from dao to object and save in collection
                for(ImHrisKualifikasiCalonPejabatEntity studyJurusanEntity : kualifikasiCalonPejabatEntities){
                    returnStudyJurusan = new CarrerPath();
                    returnStudyJurusan.setCarrerPathId(studyJurusanEntity.getKualifikasiId());

                    returnStudyJurusan.setJabatanId(studyJurusanEntity.getJabatanId());
                    if(studyJurusanEntity.getImPosition() != null){
                        returnStudyJurusan.setJabatanName(studyJurusanEntity.getImPosition().getPositionName());
                    }else{
                        returnStudyJurusan.setJabatanName("-");
                    }

                    returnStudyJurusan.setDivisiId(studyJurusanEntity.getDivisiId());
                    if(studyJurusanEntity.getDivisiId() != null && !studyJurusanEntity.getDivisiId().equalsIgnoreCase("")){
                        returnStudyJurusan.setDivisiName(studyJurusanEntity.getImDepartmentEntity().getDepartmentName());
                    }else{
                        returnStudyJurusan.setDivisiName("-");
                    }

                    returnStudyJurusan.setBagianId(studyJurusanEntity.getBagianId());
                    if(studyJurusanEntity.getBagianId() != null && !studyJurusanEntity.getBagianId().equalsIgnoreCase("")){
                        returnStudyJurusan.setBagianName(studyJurusanEntity.getImPositionBagianEntity().getBagianName());
                    }else{
                        returnStudyJurusan.setBagianName("-");
                    }

                    if(studyJurusanEntity.getThMinKerja() != null && !studyJurusanEntity.getThMinKerja().equalsIgnoreCase("")){
                        returnStudyJurusan.setThMinKerja(studyJurusanEntity.getThMinKerja());
                    }else{
                        returnStudyJurusan.setThMinKerja("-");
                    }

                    if(studyJurusanEntity.getThMinBidang() != null && !studyJurusanEntity.getThMinBidang().equalsIgnoreCase("")){
                        returnStudyJurusan.setThMinBidang(studyJurusanEntity.getThMinBidang());
                    }else{
                        returnStudyJurusan.setThMinBidang("-");
                    }

                    if(studyJurusanEntity.getThMinBagian() != null && !studyJurusanEntity.getThMinBagian().equalsIgnoreCase("")){
                        returnStudyJurusan.setThMinBagian(studyJurusanEntity.getThMinBagian());
                    }else{
                        returnStudyJurusan.setThMinBagian("-");
                    }

                    returnStudyJurusan.setBranchId(studyJurusanEntity.getBranchId());
                    if(studyJurusanEntity.getImBranches() != null){
                        returnStudyJurusan.setBranchName(studyJurusanEntity.getImBranches().getBranchName());
                    }else{
                        returnStudyJurusan.setBranchName("-");
                    }

                    if(studyJurusanEntity.getPendidikan() != null && !studyJurusanEntity.getPendidikan().equalsIgnoreCase("")){
                        returnStudyJurusan.setPendidikan(studyJurusanEntity.getPendidikan());
                    }else{
                        returnStudyJurusan.setPendidikan("-");
                    }

                    returnStudyJurusan.setJurusanId(studyJurusanEntity.getJurusanId());
                    if(studyJurusanEntity.getJurusanId() != null && !studyJurusanEntity.getJurusanId().equalsIgnoreCase("")){
                        returnStudyJurusan.setJurusanName(studyJurusanEntity.getImStudyJurusanEntity().getJurusanName());
                    }else{
                        returnStudyJurusan.setJurusanName("-");
                    }

                    returnStudyJurusan.setKelompokPositionId(studyJurusanEntity.getKelompokPositionId());
                    if(studyJurusanEntity.getKelompokPositionId() != null && !studyJurusanEntity.getKelompokPositionId().equalsIgnoreCase("")){
                        returnStudyJurusan.setKelompokPositionName(studyJurusanEntity.getImKelompokPositionEntity().getKelompokName());
                    }else{
                        returnStudyJurusan.setKelompokPositionName("-");
                    }

                    returnStudyJurusan.setGolonganId(studyJurusanEntity.getGolonganId());
                    if(studyJurusanEntity.getGolonganId() != null && !studyJurusanEntity.getGolonganId().equalsIgnoreCase("")){
                        returnStudyJurusan.setGolonganName(studyJurusanEntity.getImGolonganEntity().getGolonganName());
                    }else{
                        returnStudyJurusan.setGolonganName("-");
                    }

                    returnStudyJurusan.setPelatihanJabatan(studyJurusanEntity.getPelatihanJabatan());
                    returnStudyJurusan.setKeterangan(studyJurusanEntity.getKeterangan());

                    returnStudyJurusan.setCreatedWho(studyJurusanEntity.getCreateWho());
                    returnStudyJurusan.setCreatedDate(studyJurusanEntity.getCreatedDate());
                    returnStudyJurusan.setLastUpdate(studyJurusanEntity.getLastUpdate());

                    returnStudyJurusan.setAction(studyJurusanEntity.getAction());
                    returnStudyJurusan.setFlag(studyJurusanEntity.getFlag());
                    listOfResult.add(returnStudyJurusan);
                }
            }
        }
        logger.info("[StudyJurusanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<CarrerPath> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

}
