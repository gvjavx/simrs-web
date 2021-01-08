package com.neurix.akuntansi.master.parameterbudgeting.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.parameterbudgeting.bo.KategoriParameterBudgetingBo;
import com.neurix.akuntansi.master.parameterbudgeting.dao.JenisBudgetingDao;
import com.neurix.akuntansi.master.parameterbudgeting.dao.KategoriParameterBudgetingDao;
import com.neurix.akuntansi.master.parameterbudgeting.dao.ParameterBudgetingDao;
import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunJenisBudgetingEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunKategoriParameterBudgetingEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.KategoriParameterBudgeting;
import com.neurix.akuntansi.master.parameterbudgeting.model.ParameterBudgeting;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KategoriParameterBudgetingBoImpl implements KategoriParameterBudgetingBo {
    protected static transient Logger logger = Logger.getLogger(KategoriParameterBudgetingBoImpl.class);
    private KategoriParameterBudgetingDao kategoriParameterBudgetingDao;
    private JenisBudgetingDao jenisBudgetingDao;
    private ParameterBudgeting parameterBudgeting;
    private ParameterBudgetingDao parameterBudgetingDao;

    public ParameterBudgetingDao getParameterBudgetingDao() {
        return parameterBudgetingDao;
    }

    public void setParameterBudgetingDao(ParameterBudgetingDao parameterBudgetingDao) {
        this.parameterBudgetingDao = parameterBudgetingDao;
    }

    public ParameterBudgeting getParameterBudgeting() {
        return parameterBudgeting;
    }

    public void setParameterBudgeting(ParameterBudgeting parameterBudgeting) {
        this.parameterBudgeting = parameterBudgeting;
    }

    public JenisBudgetingDao getJenisBudgetingDao() {
        return jenisBudgetingDao;
    }

    public void setJenisBudgetingDao(JenisBudgetingDao jenisBudgetingDao) {
        this.jenisBudgetingDao = jenisBudgetingDao;
    }

    public KategoriParameterBudgetingDao getKategoriParameterBudgetingDao() {
        return kategoriParameterBudgetingDao;
    }

    public void setKategoriParameterBudgetingDao(KategoriParameterBudgetingDao kategoriParameterBudgetingDao) {
        this.kategoriParameterBudgetingDao = kategoriParameterBudgetingDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public List<KategoriParameterBudgeting> getByCriteria(KategoriParameterBudgeting bean) throws GeneralBOException {
        logger.info("[KategoriParameterBudgetingBoImpl.getByCriteria] Start >>>>>>>");
        List<KategoriParameterBudgeting> listOfResultKategoriParameterBudgeting = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())) {
                hsCriteria.put("id", bean.getId());
            }
            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
                hsCriteria.put("nama", bean.getNama());
            }
            if (bean.getIdJenisBudgeting() != null && !"".equalsIgnoreCase(bean.getIdJenisBudgeting())) {
                hsCriteria.put("id_jenis_budgeting", bean.getIdJenisBudgeting());
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

            List<ImAkunKategoriParameterBudgetingEntity> imAkunKategoriParameterBudgetingEntities = null;
            try {
                imAkunKategoriParameterBudgetingEntities = kategoriParameterBudgetingDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[KategoriParameterBudgetingBoImpl.getByCriteria] error when get data jenis obat by get by criteria " + e.getMessage());
            }

            if (imAkunKategoriParameterBudgetingEntities.size() > 0) {
                for (ImAkunKategoriParameterBudgetingEntity kategoriParameterBudgetingEntity : imAkunKategoriParameterBudgetingEntities) {
                    KategoriParameterBudgeting kategoriParameterBudgeting = new KategoriParameterBudgeting();
                    kategoriParameterBudgeting.setId(kategoriParameterBudgetingEntity.getId());
                    kategoriParameterBudgeting.setNama(kategoriParameterBudgetingEntity.getNama());
                    kategoriParameterBudgeting.setIdJenisBudgeting(kategoriParameterBudgetingEntity.getIdJenisBudgeting());

                    ImAkunJenisBudgetingEntity jenisBudgetingEntity = jenisBudgetingDao.getById("id", kategoriParameterBudgetingEntity.getIdJenisBudgeting());
                    if(jenisBudgetingEntity != null){
                        kategoriParameterBudgeting.setJenisBudgeting(jenisBudgetingEntity.getNamaJenis());
                    }

                    kategoriParameterBudgeting.setFlag(kategoriParameterBudgetingEntity.getFlag());
                    kategoriParameterBudgeting.setAction(kategoriParameterBudgetingEntity.getAction());
                    kategoriParameterBudgeting.setCreatedDate(kategoriParameterBudgetingEntity.getCreatedDate());
                    kategoriParameterBudgeting.setCreatedWho(kategoriParameterBudgetingEntity.getCreatedWho());
                    kategoriParameterBudgeting.setLastUpdate(kategoriParameterBudgetingEntity.getLastUpdate());
                    kategoriParameterBudgeting.setLastUpdateWho(kategoriParameterBudgetingEntity.getLastUpdateWho());
                    listOfResultKategoriParameterBudgeting.add(kategoriParameterBudgeting);
                }
            }

        }
        return listOfResultKategoriParameterBudgeting;

    }

    @Override
    public void saveAdd(KategoriParameterBudgeting bean) throws GeneralBOException {


            String KategoriParameterBudgetingId;
                try {
                    // Generating ID, get from postgre sequence
                    KategoriParameterBudgetingId = kategoriParameterBudgetingDao.getNextSeq();
                } catch (HibernateException e) {
                    logger.error("[Parameter Budgeting RekeningDaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting Parameter Budgeting Rekening id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImAkunKategoriParameterBudgetingEntity imAkunKategoriParameterBudgetingEntity = new ImAkunKategoriParameterBudgetingEntity();
                imAkunKategoriParameterBudgetingEntity.setId(KategoriParameterBudgetingId);
                imAkunKategoriParameterBudgetingEntity.setNama(bean.getNama());
                imAkunKategoriParameterBudgetingEntity.setIdJenisBudgeting(bean.getIdJenisBudgeting());

                imAkunKategoriParameterBudgetingEntity.setFlag(bean.getFlag());
                imAkunKategoriParameterBudgetingEntity.setAction(bean.getAction());
                imAkunKategoriParameterBudgetingEntity.setCreatedWho(bean.getCreatedWho());
                imAkunKategoriParameterBudgetingEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imAkunKategoriParameterBudgetingEntity.setCreatedDate(bean.getCreatedDate());
                imAkunKategoriParameterBudgetingEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    kategoriParameterBudgetingDao.addAndSave(imAkunKategoriParameterBudgetingEntity);
                } catch (HibernateException e) {
                    logger.error("[Parameter Budgeting Rekening BoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Parameter Budgeting Rekening, please info to your admin..." + e.getMessage());
                }
            }




    @Override
    public void saveEdit(KategoriParameterBudgeting bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatSubBoImpl.saveEdit] start process >>>");

                String id = bean.getId();
                ImAkunKategoriParameterBudgetingEntity imAkunKategoriParameterBudgetingEntity = null;
                try {
                    // Get data from database by ID
                    imAkunKategoriParameterBudgetingEntity = kategoriParameterBudgetingDao.getById("id", id);

                } catch (HibernateException e) {
                    logger.error("[parameter Budgeting Rekening BoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data parameter Budgeting Rekening by Kode parameter Budgeting Rekening, " +
                            "please inform to your admin...," + e.getMessage());
                }
                if (imAkunKategoriParameterBudgetingEntity != null) {
                    imAkunKategoriParameterBudgetingEntity.setNama(bean.getNama());
                    imAkunKategoriParameterBudgetingEntity.setIdJenisBudgeting(bean.getIdJenisBudgeting());

                    imAkunKategoriParameterBudgetingEntity.setFlag(bean.getFlag());
                    imAkunKategoriParameterBudgetingEntity.setAction(bean.getAction());
                    imAkunKategoriParameterBudgetingEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imAkunKategoriParameterBudgetingEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        kategoriParameterBudgetingDao.updateAndSave(imAkunKategoriParameterBudgetingEntity);
                    } catch (HibernateException e) {
                        logger.error("[KategoriParameterBudgetingBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update KategoriParameterBudgeting, please info to your admin..." + e.getMessage());
                    }
                }
            }



    @Override
    public void saveDelete(KategoriParameterBudgeting bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean != null) {

                boolean cekdata = false;
                try {
                    cekdata = parameterBudgetingDao.cekdataByCriteria(bean.getId(), bean.getFlag());
                } catch (HibernateException e){
                    logger.error("[KategoriparameterBoImpl.saveAdd] ERROR. ", e);
                    throw new GeneralBOException("[KategoriparameterBoImpl.saveAdd] ERROR. ]", e);
                }
                if (cekdata){
                    throw new GeneralBOException("data sedang di gunakan");
                }

            String id = bean.getId();
            ImAkunKategoriParameterBudgetingEntity imAkunKategoriParameterBudgetingEntity = null;

            try {
                // Get data from database by ID
                imAkunKategoriParameterBudgetingEntity = kategoriParameterBudgetingDao.getById("id", id);
            } catch (HibernateException e) {
                logger.error("[KategoriParameterBudgetingBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data KategoriParameterBudgeting by Kode KategoriParameterBudgeting, please inform to your admin...," + e.getMessage());
            }

            if (imAkunKategoriParameterBudgetingEntity != null) {
                imAkunKategoriParameterBudgetingEntity.setId(bean.getId());

                imAkunKategoriParameterBudgetingEntity.setFlag(bean.getFlag());
                imAkunKategoriParameterBudgetingEntity.setAction(bean.getAction());
                imAkunKategoriParameterBudgetingEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imAkunKategoriParameterBudgetingEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    kategoriParameterBudgetingDao.updateAndSave(imAkunKategoriParameterBudgetingEntity);
                } catch (HibernateException e) {
                    logger.error("[imAkunKategoriParameterBudgetingEntityBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data imAkunKategoriParameterBudgetingEntity, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[imAkunKategoriParameterBudgetingEntityBoImpl.saveDelete] Error, not found data imAkunKategoriParameterBudgetingEntity with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data imAkunKategoriParameterBudgetingEntity with request id, please check again your data ...");
            }
        }
        logger.info("[imAkunKategoriParameterBudgetingEntityBoImpl.saveDelete] end process <<<");
    }
}
