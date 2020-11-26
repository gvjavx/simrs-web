package com.neurix.simrs.master.lab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.kategorilab.bo.KategoriLabBo;
import com.neurix.simrs.master.kategorilab.model.KategoriLab;
import com.neurix.simrs.master.lab.action.LabAction;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.dao.LabDao;
import com.neurix.simrs.master.lab.model.ImSimrsLabEntity;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.operatorlab.bo.OperatorLabBo;
import com.neurix.simrs.master.operatorlab.model.OperatorLab;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabBoImpl implements LabBo {

    protected static transient Logger logger = Logger.getLogger(LabBoImpl.class);
    private LabDao labDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setLabDao(LabDao labDao) {
        this.labDao = labDao;
    }

    @Override
    public void saveDelete(Lab bean) throws GeneralBOException {
        logger.info("[saveDelete.LabBoImpl] start process >>>");

        if (bean!=null) {
            String idLab = bean.getIdLab();
            String status = cekBeforeDelete(idLab);
            if (!status.equalsIgnoreCase("exist")){
                ImSimrsLabEntity entity = null;

                try {
                    // Get data from database by ID
                    entity = labDao.getById("idLab", idLab);
                } catch (HibernateException e) {
                    logger.error("[LabBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data Lab by IdLab, please inform to your admin...," + e.getMessage());
                }

                if (entity != null) {

                    // Modify from bean to entity serializable
                    entity.setIdLab(bean.getIdLab());
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Delete (Edit) into database
                        labDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[LabBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Lab, please info to your admin..." + e.getMessage());
                    }


                } else {
                    logger.error("[LabBoImpl.saveDelete] Error, not found data Lab with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Lab with request id, please check again your data ...");

                }
            }else {
                throw new GeneralBOException("Maaf Data tidak dapat dihapus, karna masih digunakan pada data Transaksi");
            }
        }
        logger.info("[LabBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Lab bean) throws GeneralBOException {
        logger.info("[LabBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String labId = bean.getIdLab();

            ImSimrsLabEntity imSimrsLabEntity = null;
            try {
                // Get data from database by ID
                imSimrsLabEntity = labDao.getById("idLab", labId);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[LabBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Lab by IdLab, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsLabEntity != null) {
                if (bean.getNamaLab().equalsIgnoreCase(imSimrsLabEntity.getNamaLab())){
                    imSimrsLabEntity.setIdLab(bean.getIdLab());
                    imSimrsLabEntity.setNamaLab(bean.getNamaLab());
//                    imSimrsLabEntity.setIdOperatorLab(bean.getIdOperatorLab());
                    imSimrsLabEntity.setIdDokter(bean.getIdDokter());
                    imSimrsLabEntity.setIdKategoriLab(bean.getIdKategoriLab());
                    imSimrsLabEntity.setFlag(bean.getFlag());
                    imSimrsLabEntity.setAction(bean.getAction());
                    imSimrsLabEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSimrsLabEntity.setLastUpdate(bean.getLastUpdate());

                    String flag;
                    try {
                        // Update into database
                        labDao.updateAndSave(imSimrsLabEntity);
                        //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[LabBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Lab, please info to your admin..." + e.getMessage());
                    }
                }else {
                    String status = cekStatus(bean.getNamaLab());
                    if (!status.equalsIgnoreCase("exist")){
                        imSimrsLabEntity.setIdLab(bean.getIdLab());
                        imSimrsLabEntity.setNamaLab(bean.getNamaLab());
//                        imSimrsLabEntity.setIdOperatorLab(bean.getIdOperatorLab());
                        imSimrsLabEntity.setIdDokter(bean.getIdDokter());
                        imSimrsLabEntity.setIdKategoriLab(bean.getIdKategoriLab());
                        imSimrsLabEntity.setFlag(bean.getFlag());
                        imSimrsLabEntity.setAction(bean.getAction());
                        imSimrsLabEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imSimrsLabEntity.setLastUpdate(bean.getLastUpdate());

                        String flag;
                        try {
                            // Update into database
                            labDao.updateAndSave(imSimrsLabEntity);
                            //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                        } catch (HibernateException e) {
                            logger.error("[LabBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data Lab, please info to your admin..." + e.getMessage());
                        }
                    }else {
                        throw new GeneralBOException("Maaf Data dengan Nama Lab Tersebut Sudah Ada");
                    }
                }
            } else {
                logger.error("[LabBoImpl.saveEdit] Error, not found data Lab with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Lab with request id, please check again your data ...");
            }
        }
        logger.info("[LabBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Lab saveAdd(Lab bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getNamaLab());
            String labId;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    labId = labDao.getNextLabId();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiId id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsLabEntity imSimrsLabEntity = new ImSimrsLabEntity();
                imSimrsLabEntity.setIdLab(labId);
                imSimrsLabEntity.setNamaLab(bean.getNamaLab());
//                imSimrsLabEntity.setIdOperatorLab(bean.getIdOperatorLab());
                imSimrsLabEntity.setIdDokter(bean.getIdDokter());
                imSimrsLabEntity.setIdKategoriLab(bean.getIdKategoriLab());
                imSimrsLabEntity.setFlag(bean.getFlag());
                imSimrsLabEntity.setAction(bean.getAction());
                imSimrsLabEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsLabEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsLabEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsLabEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    labDao.addAndSave(imSimrsLabEntity);
                } catch (HibernateException e) {
                    logger.error("[LabBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Lab, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Nama Lab Tersebut Sudah Ada");
            }
        }

        logger.info("[LabBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Lab> getByCriteria(Lab bean) throws GeneralBOException {
        logger.info("[LabBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdLab() != null && !"".equalsIgnoreCase(bean.getIdLab())) {
                hsCriteria.put("id_lab", bean.getIdLab());
            }
            if (bean.getNamaLab() != null && !"".equalsIgnoreCase(bean.getNamaLab())) {
                hsCriteria.put("nama_lab", bean.getNamaLab());
            }
//            if (bean.getIdOperatorLab() != null && !"".equalsIgnoreCase(bean.getIdOperatorLab())) {
//                hsCriteria.put("id_operator_lab", bean.getIdOperatorLab());
//            }
            if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter())) {
                hsCriteria.put("id_dokter", bean.getIdDokter());
            }
            if (bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())) {
                hsCriteria.put("id_kategori_lab", bean.getIdKategoriLab());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                hsCriteria.put("branch_id", bean.getBranchId());
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

            List<ImSimrsLabEntity> labEntityList = new ArrayList<>();
            List<Lab> result = new ArrayList<>();
            try {
                labEntityList = labDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[LabBoImpl.getByCriteria] error when get data lab by get by criteria "+ e.getMessage());
            }

            if (!labEntityList.isEmpty()){
                Lab lab;
                for (ImSimrsLabEntity labEntity : labEntityList){
                    lab = new Lab();
                    lab.setIdLab(labEntity.getIdLab());
                    lab.setNamaLab(labEntity.getNamaLab());
//                    lab.setIdOperatorLab(labEntity.getIdOperatorLab());
                    lab.setIdDokter(labEntity.getIdDokter());
                    lab.setIdKategoriLab(labEntity.getIdKategoriLab());
                    lab.setFlag(labEntity.getFlag());
                    lab.setAction(labEntity.getAction());
                    lab.setStCreatedDate(labEntity.getCreatedDate().toString());
                    lab.setCreatedDate(labEntity.getCreatedDate());
                    lab.setCreatedWho(labEntity.getCreatedWho());
                    lab.setStLastUpdate(labEntity.getLastUpdate().toString());
                    lab.setLastUpdate(labEntity.getLastUpdate());
                    lab.setLastUpdateWho(labEntity.getLastUpdateWho());

//                    if (labEntity.getIdOperatorLab() != null){
//                        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
//                        OperatorLab operatorLab = new OperatorLab();
//                        OperatorLabBo operatorLabBo = (OperatorLabBo) context.getBean("operatorLabBoProxy");
//                        operatorLab.setIdOperatorLab(labEntity.getIdOperatorLab());
//                        operatorLab.setFlag("Y");
//                        List<OperatorLab> operatorLabs = operatorLabBo.getByCriteria(operatorLab);
//                        String operatorLabName = operatorLabs.get(0).getNamaOperator();
//                        lab.setNamaOperatorLab(operatorLabName);
//                    }else {
//                        lab.setNamaOperatorLab("-");
//                    }

                    if (labEntity.getIdDokter() != null){
                        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                        Dokter dokter = new Dokter();
                        DokterBo dokterBo = (DokterBo) context.getBean("dokterBoProxy");
                        dokter.setIdDokter(labEntity.getIdDokter());
                        dokter.setFlag("Y");
                        List<Dokter> dokters = dokterBo.getByCriteria(dokter);
                        String dokterName = dokters.get(0).getNamaDokter();
                        lab.setNamaDokter(dokterName);
                    }else {
                        lab.setNamaDokter("-");
                    }

                    if (labEntity.getIdKategoriLab() != null){
                        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                        KategoriLab kategoriLab = new KategoriLab();
                        KategoriLabBo kategoriLabBo = (KategoriLabBo) context.getBean("kategoriLabBoProxy");
                        kategoriLab.setIdKategoriLab(labEntity.getIdKategoriLab());
                        kategoriLab.setFlag("Y");
                        List<KategoriLab> kategoriLabs = kategoriLabBo.getByCriteria(kategoriLab);
                        String kategoriLabName = kategoriLabs.get(0).getNamaKategori();
                        lab.setNamaKategoriLab(kategoriLabName);
                    }else {
                        lab.setNamaKategoriLab("-");
                    }

                    result.add(lab);
                }
            }

            logger.info("[LabBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[LabBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }

    @Override
    public List<Lab> getDataLab(String id, String branch) throws GeneralBOException {
        List<Lab> labList = new ArrayList<>();
        try {
            labList = labDao.getListDataLab(id, branch);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return labList;
    }

    @Override
    public List<Lab> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String namaPelayanan)throws GeneralBOException{
        String status ="";
        List<ImSimrsLabEntity> entities = new ArrayList<>();
        try {
            entities = labDao.getDataLab(namaPelayanan);
        } catch (HibernateException e) {
            logger.error("[LabBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

    public String cekBeforeDelete(String idLab)throws GeneralBOException{
        String status ="";
        List<ImSimrsLabEntity> entities = new ArrayList<>();
        List<ImSimrsLabEntity> entityList = new ArrayList<>();
        try {
            entities = labDao.cekData(idLab);
            entityList = labDao.cekDataRadiologi(idLab);
        } catch (HibernateException e) {
            logger.error("[LabBoImpl.cekBeforeDelete] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            if (entityList.size()>0)
                status = "exist";
            else
                status="notExits";
        }
        return status;
    }
}