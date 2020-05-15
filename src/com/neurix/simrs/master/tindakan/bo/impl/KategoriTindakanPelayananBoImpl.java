package com.neurix.simrs.master.tindakan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.tindakan.bo.KategoriTindakanPelayananBo;
import com.neurix.simrs.master.tindakan.dao.KategoriPelayananDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsKategoriTindakanPelayananEntity;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.KategoriTindakanPelayanan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KategoriTindakanPelayananBoImpl implements KategoriTindakanPelayananBo {
    private static transient Logger logger = Logger.getLogger(KategoriTindakanPelayananBoImpl.class);

    private KategoriPelayananDao kategoriPelayananDao;

    public void setKategoriPelayananDao(KategoriPelayananDao kategoriPelayananDao) {
        this.kategoriPelayananDao = kategoriPelayananDao;
    }

    @Override
    public void saveDelete(KategoriTindakanPelayanan bean) throws GeneralBOException {
        logger.info("[saveDelete.KategoriTindakanPelayananBoImpl] start process >>>");

        if (bean!=null) {
            String idKategoriPelayanan = bean.getIdKategoriPelayanan();
            ImSimrsKategoriTindakanPelayananEntity entity = null;

            try {
                // Get data from database by ID
                entity = kategoriPelayananDao.getById("idKategoriPelayanan", idKategoriPelayanan);
            } catch (HibernateException e) {
                logger.error("[KategoriTindakanPelayananBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode Kategori Pelayanan, please inform to your admin...," + e.getMessage());
            }

            if (entity != null) {

                // Modify from bean to entity serializable
                entity.setIdKategoriPelayanan(idKategoriPelayanan);
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    kategoriPelayananDao.updateAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[KategoriTindakanPelayananBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data KategoriTindakanPelayanan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[KategoriTindakanPelayananBoImpl.saveDelete] Error, not found data KategoriTindakanPelayanan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data KategoriTindakanPelayanan with request id, please check again your data ...");

            }
        }
        logger.info("[KategoriTindakanPelayananBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(KategoriTindakanPelayanan bean) throws GeneralBOException {
        logger.info("[KategoriTindakanPelayananBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String kategoriPelayananId = bean.getIdKategoriPelayanan();

            ImSimrsKategoriTindakanPelayananEntity tindakanPelayananEntity = null;
            try {
                // Get data from database by ID
                tindakanPelayananEntity = kategoriPelayananDao.getById("idKategoriPelayanan", kategoriPelayananId);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[KategoriTindakanPelayananBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data KategoriTindakanPelayanan by Kode idKategoriPelayanan, please inform to your admin...," + e.getMessage());
            }

            if (tindakanPelayananEntity != null) {
                tindakanPelayananEntity.setIdKategoriPelayanan(kategoriPelayananId);
                tindakanPelayananEntity.setIdKategori(bean.getIdKategori());
                tindakanPelayananEntity.setIdPelayanan(bean.getIdPelayanan());
                tindakanPelayananEntity.setFlag(bean.getFlag());
                tindakanPelayananEntity.setAction(bean.getAction());
                tindakanPelayananEntity.setLastUpdateWho(bean.getLastUpdateWho());
                tindakanPelayananEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    kategoriPelayananDao.updateAndSave(tindakanPelayananEntity);
                    //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[KategoriTindakanPelayananBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data KategoriTindakanPelayanan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KategoriTindakanPelayananBoImpl.saveEdit] Error, not found data KategoriTindakanPelayanan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
            }
        }
        logger.info("[KategoriTindakanPelayananBoImpl.saveEdit] end process <<<");
    }

    @Override
    public KategoriTindakanPelayanan saveAdd(KategoriTindakanPelayanan bean) throws GeneralBOException {
        logger.info("[KategoriTindakanPelayananBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getIdKategori(),bean.getIdPelayanan());
            String tindakanPelayananId;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    tindakanPelayananId = kategoriPelayananDao.getNextTindakanPelayananId();
                } catch (HibernateException e) {
                    logger.error("[KategoriTindakanPelayananBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence kategori tindakan pelayanan Id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsKategoriTindakanPelayananEntity entity = new ImSimrsKategoriTindakanPelayananEntity();
                entity.setIdKategoriPelayanan(tindakanPelayananId);
                entity.setIdKategori(bean.getIdKategori());
                entity.setIdPelayanan(bean.getIdPelayanan());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    kategoriPelayananDao.addAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[KategoriTindakanPelayananBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Kategori Tindakan Pelayanan, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Kategori Tindakan dan Pelayanan Tersebut Sudah Ada");
            }
        }

        logger.info("[TindakanImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<KategoriTindakanPelayanan> getByCriteria(KategoriTindakanPelayanan bean) throws GeneralBOException {
        logger.info("[KategoriTindakanPelayananBoImpl.getByCriteria] Start >>>>>>");
        List<KategoriTindakanPelayanan> result = new ArrayList<>();

        if(bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getIdKategoriPelayanan() != null && !"".equalsIgnoreCase(bean.getIdKategoriPelayanan())){
                hsCriteria.put("id_kategori_pelayanan", bean.getIdKategoriPelayanan());
            }
            if (bean.getIdKategori() != null && !"".equalsIgnoreCase(bean.getIdKategori())){
                hsCriteria.put("id_kategori", bean.getIdKategori());
            }
            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
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

            List<ImSimrsKategoriTindakanPelayananEntity> entityList = new ArrayList<>();

            try {
                entityList = kategoriPelayananDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[KategoriTindakanPelayananBoImpl.getByCriteria] Error get KategoriTindakanPelayanan data "+e.getMessage());
            }

            if (!entityList.isEmpty()){
                KategoriTindakanPelayanan kategoriTindakanPelayanan;
                for (ImSimrsKategoriTindakanPelayananEntity entity : entityList){
                    kategoriTindakanPelayanan = new KategoriTindakanPelayanan();
                    kategoriTindakanPelayanan.setIdKategoriPelayanan(entity.getIdKategoriPelayanan());
                    kategoriTindakanPelayanan.setIdKategori(entity.getIdKategori());
                    kategoriTindakanPelayanan.setIdPelayanan(entity.getIdPelayanan());
                    kategoriTindakanPelayanan.setAction(entity.getAction());
                    kategoriTindakanPelayanan.setFlag(entity.getFlag());
                    kategoriTindakanPelayanan.setCreatedDate(entity.getCreatedDate());
                    kategoriTindakanPelayanan.setStCreatedDate(entity.getCreatedDate().toString());
                    kategoriTindakanPelayanan.setCreatedWho(entity.getCreatedWho());
                    kategoriTindakanPelayanan.setStLastUpdate(entity.getLastUpdate().toString());
                    kategoriTindakanPelayanan.setLastUpdate(entity.getLastUpdate());
                    kategoriTindakanPelayanan.setLastUpdateWho(entity.getLastUpdateWho());

                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    if (entity.getIdKategori() != null){
                        KategoriTindakan kategoriTindakan = new KategoriTindakan();
                        KategoriTindakanBo kategoriTindakanBo = (KategoriTindakanBo) context.getBean("kategoriTindakanBoProxy");
                        kategoriTindakan.setIdKategoriTindakan(entity.getIdKategori());
                        kategoriTindakan.setFlag("Y");
                        List<KategoriTindakan> kategoriTindakans = kategoriTindakanBo.getByCriteria(kategoriTindakan);
                        String kategoriTindakanName = kategoriTindakans.get(0).getKategoriTindakan();
                        kategoriTindakanPelayanan.setKategoriName(kategoriTindakanName);
                    }else {
                        kategoriTindakanPelayanan.setKategoriName("-");
                    }

                    if (entity.getIdPelayanan() != null){
                        Pelayanan pelayanan = new Pelayanan();
                        PelayananBo pelayananBo = (PelayananBo) context.getBean("pelayananBoProxy");
                        pelayanan.setIdPelayanan(entity.getIdPelayanan());
                        pelayanan.setFlag("Y");
                        List<Pelayanan> pelayanans = pelayananBo.getByCriteria(pelayanan);
                        String pelayananName = pelayanans.get(0).getNamaPelayanan();
                        kategoriTindakanPelayanan.setPelayananName(pelayananName);
                    }

                    result.add(kategoriTindakanPelayanan);
                }
            }
        }

        logger.info("[KategoriTindakanPelayananBoImpl.getByCriteria] End <<<<<<");
        return result;
    }

    @Override
    public List<KategoriTindakanPelayanan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String kategoriTindakan, String pelayanan)throws GeneralBOException{
        String status ="";
        List<ImSimrsKategoriTindakanPelayananEntity> entities = new ArrayList<>();
        try {
            entities = kategoriPelayananDao.getDataTindakanPelayanan(kategoriTindakan, pelayanan);
        } catch (HibernateException e) {
            logger.error("[KategoriTindakanPelayananBoImpl.cekStatus] Error, " + e.getMessage());
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