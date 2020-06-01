package com.neurix.hris.master.mappingpersengaji.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.mappingpersengaji.bo.MappingPersenGajiBo;
import com.neurix.hris.master.mappingpersengaji.dao.MappingPersenGajiDao;
import com.neurix.hris.master.mappingpersengaji.dao.MappingPersenGajiHistoryDao;
import com.neurix.hris.master.mappingpersengaji.model.ImHrisMappingPersenGaji;
import com.neurix.hris.master.mappingpersengaji.model.ImHrisMappingPersenGajiHistory;
import com.neurix.hris.master.mappingpersengaji.model.MappingPersenGaji;
import com.neurix.simrs.master.dokter.model.Dokter;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingPersenGajiBoImpl implements MappingPersenGajiBo {
    private static transient Logger logger = Logger.getLogger(MappingPersenGajiBoImpl.class);
    private MappingPersenGajiDao mappingPersenGajiDao;
    private MappingPersenGajiHistoryDao mappingPersenGajiHistoryDao;

    public MappingPersenGajiHistoryDao getMappingPersenGajiHistoryDao() {
        return mappingPersenGajiHistoryDao;
    }

    public void setMappingPersenGajiHistoryDao(MappingPersenGajiHistoryDao mappingPersenGajiHistoryDao) {
        this.mappingPersenGajiHistoryDao = mappingPersenGajiHistoryDao;
    }

    public MappingPersenGajiDao getMappingPersenGajiDao() {
        return mappingPersenGajiDao;
    }

    public void setMappingPersenGajiDao(MappingPersenGajiDao mappingPersenGajiDao) {
        this.mappingPersenGajiDao = mappingPersenGajiDao;
    }

    @Override
    public void saveDelete(MappingPersenGaji bean) throws GeneralBOException {

        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String mappingPersenGaji = bean.getMappingPersenGajiId();

            ImHrisMappingPersenGaji imHrisMappingPersenGaji = null;
            ImHrisMappingPersenGajiHistory historyEntity = new ImHrisMappingPersenGajiHistory();
            try {
                // Get data from database by ID
                imHrisMappingPersenGaji = mappingPersenGajiDao.getById("mappingPersenGajiId", mappingPersenGaji);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imHrisMappingPersenGaji != null) {
                //entity history
                String mappingPersenGajiIdHistory;
                try {
                    // Generating ID, get from postgre sequence
                    mappingPersenGajiIdHistory = mappingPersenGajiHistoryDao.getNextMappingHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }
                historyEntity.setMappingPersenGajiHistoryId(mappingPersenGajiIdHistory);
                historyEntity.setMappingPersenGajiId(imHrisMappingPersenGaji.getMappingPersenGajiId());
                historyEntity.setNamaMappingPersenGaji(imHrisMappingPersenGaji.getNamaMappingPersenGaji());
                historyEntity.setJenisGaji(imHrisMappingPersenGaji.getJenisGaji());
                historyEntity.setPresentase(imHrisMappingPersenGaji.getPresentase());
                historyEntity.setCreatedDate(imHrisMappingPersenGaji.getCreatedDate());
                historyEntity.setCreatedWho(imHrisMappingPersenGaji.getCreatedWho());
                historyEntity.setLastUpdate(imHrisMappingPersenGaji.getLastUpdate());
                historyEntity.setLastUpdateWho(imHrisMappingPersenGaji.getLastUpdateWho());
                historyEntity.setFlag("Y");
                historyEntity.setAction(imHrisMappingPersenGaji.getAction());

                try {
                    // insert into database
                    mappingPersenGajiHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }

                // Modify from bean to entity serializable
                imHrisMappingPersenGaji.setMappingPersenGajiId(bean.getMappingPersenGajiId());
                imHrisMappingPersenGaji.setFlag(bean.getFlag());
                imHrisMappingPersenGaji.setAction(bean.getAction());
                imHrisMappingPersenGaji.setLastUpdate(bean.getLastUpdate());
                imHrisMappingPersenGaji.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    // Delete (Edit) into database
                    mappingPersenGajiDao.updateAndSave(imHrisMappingPersenGaji);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGaji, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.saveDelete] end process <<<");

    }

    @Override
    public void saveEdit(MappingPersenGaji bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String mappingPersenGajiId = bean.getMappingPersenGajiId();

            ImHrisMappingPersenGaji imHrisMappingPersenGajiEntity = null;
            ImHrisMappingPersenGajiHistory historyEntity = new ImHrisMappingPersenGajiHistory();
            try {
                // Get data from database by ID
                imHrisMappingPersenGajiEntity = mappingPersenGajiDao.getById("mappingPersenGajiId", mappingPersenGajiId);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGaji by Kode PayrollSkalaGaji, please inform to your admin...," + e.getMessage());
            }

            if (imHrisMappingPersenGajiEntity != null) {
                //entity history
                String imHrisMappingPersenGajiHistoryId;
                try {
                    // Generating ID, get from postgre sequence
                    imHrisMappingPersenGajiHistoryId = mappingPersenGajiHistoryDao.getNextMappingHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }

                historyEntity.setMappingPersenGajiHistoryId(imHrisMappingPersenGajiHistoryId);
                historyEntity.setMappingPersenGajiId(imHrisMappingPersenGajiEntity.getMappingPersenGajiId());
                historyEntity.setNamaMappingPersenGaji(imHrisMappingPersenGajiEntity.getNamaMappingPersenGaji());
                historyEntity.setJenisGaji(imHrisMappingPersenGajiEntity.getJenisGaji());
                historyEntity.setPresentase(imHrisMappingPersenGajiEntity.getPresentase());
                historyEntity.setCreatedDate(imHrisMappingPersenGajiEntity.getCreatedDate());
                historyEntity.setCreatedWho(imHrisMappingPersenGajiEntity.getCreatedWho());
                historyEntity.setLastUpdate(imHrisMappingPersenGajiEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imHrisMappingPersenGajiEntity.getLastUpdateWho());
                historyEntity.setFlag("Y");
                historyEntity.setAction(imHrisMappingPersenGajiEntity.getAction());

                try {
                    // insert into database
                    mappingPersenGajiHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }


                imHrisMappingPersenGajiEntity.setMappingPersenGajiId(bean.getMappingPersenGajiId());
                imHrisMappingPersenGajiEntity.setNamaMappingPersenGaji(bean.getNamaMappingPersenGaji());
                imHrisMappingPersenGajiEntity.setJenisGaji(bean.getJenisGaji());
                imHrisMappingPersenGajiEntity.setPresentase(bean.getPresentase());
                imHrisMappingPersenGajiEntity.setFlag(bean.getFlag());
                imHrisMappingPersenGajiEntity.setAction(bean.getAction());
                imHrisMappingPersenGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imHrisMappingPersenGajiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    mappingPersenGajiDao.updateAndSave(imHrisMappingPersenGajiEntity);
                    //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGaji, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public MappingPersenGaji saveAdd(MappingPersenGaji bean) throws GeneralBOException {
        logger.info("[DokterBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getNamaMappingPersenGaji());
            String mappingId;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    mappingId = mappingPersenGajiDao.getNextMapping();
                } catch (HibernateException e) {
                    logger.error("[DokterBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence DokterId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImHrisMappingPersenGaji entity = new ImHrisMappingPersenGaji();

                entity.setMappingPersenGajiId(mappingId);
                entity.setNamaMappingPersenGaji(bean.getNamaMappingPersenGaji());
                entity.setJenisGaji(bean.getJenisGaji());
                entity.setPresentase(bean.getPresentase());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    mappingPersenGajiDao.addAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[DokterBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Dokter, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Nama Mapping Persen Gaji Tersebut Sudah Ada");
            }
        }

        logger.info("[DokterBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<MappingPersenGaji> getByCriteria(MappingPersenGaji bean) throws GeneralBOException {
        List<MappingPersenGaji> mappingPersenGajis = new ArrayList<>();
        if(bean != null){

            Map hsCriteria = new HashMap();

            if(bean.getMappingPersenGajiId() != null && !"".equalsIgnoreCase(bean.getMappingPersenGajiId())){
                hsCriteria.put("mapping_persen_gaji_id", bean.getMappingPersenGajiId());
            }
            if(bean.getNamaMappingPersenGaji() != null && !"".equalsIgnoreCase(bean.getNamaMappingPersenGaji())){
                hsCriteria.put("nama_mapping_persen_gaji", bean.getNamaMappingPersenGaji());
            }
            if(bean.getJenisGaji() != null && !"".equalsIgnoreCase(bean.getJenisGaji())){
                hsCriteria.put("jenis_gaji", bean.getJenisGaji());
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

            List<ImHrisMappingPersenGaji> imHrisMappingPersenGajis = new ArrayList<>();
            try {
                imHrisMappingPersenGajis = mappingPersenGajiDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("Found Error when search asuransi "+e.getMessage());
            }

            if(imHrisMappingPersenGajis.size() > 0){
                for (ImHrisMappingPersenGaji entity: imHrisMappingPersenGajis){
                    MappingPersenGaji mappingPersenGaji = new MappingPersenGaji();
                    mappingPersenGaji.setMappingPersenGajiId(entity.getMappingPersenGajiId());
                    mappingPersenGaji.setNamaMappingPersenGaji(entity.getNamaMappingPersenGaji());
                    mappingPersenGaji.setJenisGaji(entity.getJenisGaji());
                    mappingPersenGaji.setPresentase(entity.getPresentase());
                    mappingPersenGaji.setFlag(entity.getFlag());
                    mappingPersenGaji.setAction(entity.getAction());
                    mappingPersenGaji.setFlag(entity.getFlag());
                    mappingPersenGaji.setAction(entity.getAction());
                    mappingPersenGaji.setCreatedWho(entity.getCreatedWho());
                    mappingPersenGaji.setStCreatedDate(entity.getCreatedDate().toString());
                    mappingPersenGaji.setCreatedDate(entity.getCreatedDate());
                    mappingPersenGaji.setStLastUpdate(entity.getLastUpdate().toString());
                    mappingPersenGaji.setLastUpdate(entity.getLastUpdate());
                    mappingPersenGaji.setLastUpdateWho(entity.getLastUpdateWho());

                    if ("plt".equalsIgnoreCase(entity.getNamaMappingPersenGaji()))
                        mappingPersenGaji.setStrNamaMappingPersenGaji("PLT");
                    else if ("pjs".equalsIgnoreCase(entity.getNamaMappingPersenGaji()))
                        mappingPersenGaji.setStrNamaMappingPersenGaji("PJS");
                    else if ("percobaan".equalsIgnoreCase(entity.getNamaMappingPersenGaji()))
                        mappingPersenGaji.setStrNamaMappingPersenGaji("Percobaan");

                    if ("gaji_golongan".equalsIgnoreCase(entity.getJenisGaji()))
                        mappingPersenGaji.setStrJenisGaji("Gaji Pokok");
                    else if ("tunjangan_umk".equalsIgnoreCase(entity.getJenisGaji()))
                        mappingPersenGaji.setStrJenisGaji("Santunan Khusus");
                    else if ("tunjangan_jabatan".equalsIgnoreCase(entity.getJenisGaji()))
                        mappingPersenGaji.setStrJenisGaji("Tunjangan Jabatan");
                    else if ("tunjangan_jabatan_struktural".equalsIgnoreCase(entity.getJenisGaji()))
                        mappingPersenGaji.setStrJenisGaji("Tunjangan Struktural");
                    else if ("tunjangan_strategis".equalsIgnoreCase(entity.getJenisGaji()))
                        mappingPersenGaji.setStrJenisGaji("Tunjangan Fungsional");
                    else if ("tunjangan_tambahan".equalsIgnoreCase(entity.getJenisGaji()))
                        mappingPersenGaji.setStrJenisGaji("Tunjangan Tambahan");

                    mappingPersenGajis.add(mappingPersenGaji);
                }
            }
        }

        return mappingPersenGajis;
    }

    @Override
    public List<MappingPersenGaji> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String namaMapping)throws GeneralBOException{
        String status ="";
        List<ImHrisMappingPersenGaji> entities = new ArrayList<>();
        try {
            entities = mappingPersenGajiDao.getDataMapping(namaMapping);
        } catch (HibernateException e) {
            logger.error("[DokterBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }
}