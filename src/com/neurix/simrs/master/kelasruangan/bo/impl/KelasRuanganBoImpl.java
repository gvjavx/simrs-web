package com.neurix.simrs.master.kelasruangan.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.dao.KelasRuanganDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KelasRuanganBoImpl implements KelasRuanganBo {

    protected static transient Logger logger = Logger.getLogger(KelasRuanganBoImpl.class);
    private KelasRuanganDao kelasRuanganDao;
    private BranchDao branchDao;
    private PositionDao positionDao;

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setKelasRuanganDao(KelasRuanganDao kelasRuanganDao) {
        this.kelasRuanganDao = kelasRuanganDao;
    }

    public static void setLogger(Logger logger) {

        KelasRuanganBoImpl.logger = logger;
    }

    @Override
    public List<KelasRuangan> getByCriteria(KelasRuangan bean) throws GeneralBOException {
        logger.info("[KelasRuanganBoImpl.getByCriteria] Start >>>>>>");
        List<KelasRuangan> result = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())){
            hsCriteria.put("id_kelas_ruangan", bean.getIdKelasRuangan());
        }
        if (bean.getNamaKelasRuangan() != null && !"".equalsIgnoreCase(bean.getNamaKelasRuangan())){
            hsCriteria.put("nama_kelas_ruangan", bean.getNamaKelasRuangan());
        }
        if (bean.getPositionId() != null && !"".equalsIgnoreCase(bean.getPositionId())){
            hsCriteria.put("divisi_id", bean.getPositionId());
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

        List<ImSimrsKelasRuanganEntity> imSimrsKelasRuanganEntities = null;
        try {
            imSimrsKelasRuanganEntities = kelasRuanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RuanganBoImpl.getByCriteria] Error get ruangan data "+e.getMessage());
        }

        if (!imSimrsKelasRuanganEntities.isEmpty()){
            KelasRuangan kelasRuangan;
            for (ImSimrsKelasRuanganEntity listEntity : imSimrsKelasRuanganEntities){
                kelasRuangan = new KelasRuangan();
                kelasRuangan.setIdKelasRuangan(listEntity.getIdKelasRuangan());
                kelasRuangan.setNamaKelasRuangan(listEntity.getNamaKelasRuangan());
                kelasRuangan.setKodering(listEntity.getKodering());
                kelasRuangan.setPositionId(listEntity.getDivisiId());
                kelasRuangan.setFlag(listEntity.getFlag());
                kelasRuangan.setAction(listEntity.getAction());
                kelasRuangan.setStCreatedDate(listEntity.getCreatedDate().toString());
                kelasRuangan.setCreatedDate(listEntity.getCreatedDate());
                kelasRuangan.setCreatedWho(listEntity.getCreatedWho());
                kelasRuangan.setStLastUpdate(listEntity.getLastUpdate().toString());
                kelasRuangan.setLastUpdate(listEntity.getLastUpdate());
                kelasRuangan.setLastUpdateWho(listEntity.getLastUpdateWho());

                ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                Position position = new Position();
                PositionBo positionBo = (PositionBo) context.getBean("positionBoProxy");
                position.setPositionId(listEntity.getDivisiId());
                position.setFlag("Y");
                List<Position> positions = positionBo.getByCriteria(position);
                if(positions.size() > 0){
                    String positionName = positions.get(0).getPositionName();
                    kelasRuangan.setDivisiName(positionName);
                }

                result.add(kelasRuangan);
            }
        }
        logger.info("[KelasRuanganBoImpl.getByCriteria] End <<<<<<");

        return result;
    }

    @Override
    public void saveAdd(KelasRuangan kelasRuangan) throws GeneralBOException {
        logger.info("[KelasRuanganBoImpl.saveAdd] Start >>>>>>>");

        if (kelasRuangan != null){
            String status = cekStatus(kelasRuangan.getNamaKelasRuangan());
            String kelasRuanganId;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    kelasRuanganId = kelasRuanganDao.getNextIdKelasRuangan();
                } catch (HibernateException e) {
                    logger.error("[KelasRuangan.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiId id, please info to your admin..."
                            + e.getMessage());
                }

                ImSimrsKelasRuanganEntity imSimrsKelasRuanganEntity = new ImSimrsKelasRuanganEntity();
                imSimrsKelasRuanganEntity.setIdKelasRuangan("KR"+kelasRuanganId);
                imSimrsKelasRuanganEntity.setNamaKelasRuangan(kelasRuangan.getNamaKelasRuangan());
                imSimrsKelasRuanganEntity.setDivisiId(kelasRuangan.getPositionId());
                imSimrsKelasRuanganEntity.setFlag(kelasRuangan.getFlag());
                imSimrsKelasRuanganEntity.setAction(kelasRuangan.getAction());
                imSimrsKelasRuanganEntity.setCreatedDate(kelasRuangan.getCreatedDate());
                imSimrsKelasRuanganEntity.setCreatedWho(kelasRuangan.getCreatedWho());
                imSimrsKelasRuanganEntity.setLastUpdate(kelasRuangan.getLastUpdate());
                imSimrsKelasRuanganEntity.setLastUpdateWho(kelasRuangan.getLastUpdateWho());

                try {
                    kelasRuanganDao.addAndSave(imSimrsKelasRuanganEntity);
                } catch (HibernateException e){
                    logger.error("[KelasRuanganBoImpl.saveAdd] Error when saving data pasien", e);
                    throw new GeneralBOException(" Error when saving data kelas ruangan "+e.getMessage());
                }
            }else {
                throw new GeneralBOException("Maaf Data dengan Nama Kelas Ruangan Tersebut Sudah Ada");
            }
        } else {
            logger.error("[KelasRuanganBoImpl.saveAdd] Error when saving data kelas ruangan data is null");
            throw new GeneralBOException(" Error when saving data kelas ruangan data is null");
        }

        logger.info("[KelasRuanganBoImpl.saveAdd] End <<<<<<<");
    }

    @Override
    public void saveEdit(KelasRuangan kelasRuangan) throws GeneralBOException {
        logger.info("[KelasRuanganBoImpl.saveEdit] Start >>>>>>>");

        if (kelasRuangan != null && kelasRuangan.getIdKelasRuangan() != null && !"".equalsIgnoreCase(kelasRuangan.getIdKelasRuangan())){
            ImSimrsKelasRuanganEntity imSimrsPelayananEntity = null;
            try {
                // Get data from database by ID
                imSimrsPelayananEntity = kelasRuanganDao.getById("idKelasRuangan", kelasRuangan.getIdKelasRuangan());
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[KelasRuangan.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGaji by Kode PayrollSkalaGaji, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsPelayananEntity != null){
                if (kelasRuangan.getNamaKelasRuangan().equalsIgnoreCase(imSimrsPelayananEntity.getNamaKelasRuangan())){
                    String kode = imSimrsPelayananEntity.getKodering();
                    imSimrsPelayananEntity.setNamaKelasRuangan(kelasRuangan.getNamaKelasRuangan());
                    imSimrsPelayananEntity.setDivisiId(kelasRuangan.getPositionId());
                    imSimrsPelayananEntity.setFlag(kelasRuangan.getFlag());
                    imSimrsPelayananEntity.setAction("U");
                    imSimrsPelayananEntity.setLastUpdate(kelasRuangan.getLastUpdate());
                    imSimrsPelayananEntity.setLastUpdateWho(kelasRuangan.getLastUpdateWho());

                    try {
                        kelasRuanganDao.updateAndSave(imSimrsPelayananEntity);
                    } catch (HibernateException e){
                        logger.error("[KelasRuanganBoImpl.saveAdd] Error when Updating data ruangan", e);
                        throw new GeneralBOException(" Error when Updating data kelas ruangan "+e.getMessage());
                    }
                }else {
                    String status = cekStatus(kelasRuangan.getNamaKelasRuangan());
                    if (!status.equalsIgnoreCase("exist")){
                        imSimrsPelayananEntity.setNamaKelasRuangan(kelasRuangan.getNamaKelasRuangan());
                        imSimrsPelayananEntity.setDivisiId(kelasRuangan.getPositionId());
                        imSimrsPelayananEntity.setFlag(kelasRuangan.getFlag());
                        imSimrsPelayananEntity.setAction("U");
                        imSimrsPelayananEntity.setLastUpdate(kelasRuangan.getLastUpdate());
                        imSimrsPelayananEntity.setLastUpdateWho(kelasRuangan.getLastUpdateWho());

                        try {
                            kelasRuanganDao.updateAndSave(imSimrsPelayananEntity);
                        } catch (HibernateException e){
                            logger.error("[KelasRuanganBoImpl.saveAdd] Error when Updating data ruangan", e);
                            throw new GeneralBOException(" Error when Updating data kelas ruangan "+e.getMessage());
                        }
                    }else {
                        throw new GeneralBOException("Maaf Data dengan Nama Kelas Ruangan Tersebut Sudah Ada");
                    }
                }
            } else {
                logger.error("[Kelas RuanganBoImpl.saveAdd] Error when get entity kelas ruangan is null");
                throw new GeneralBOException("  Error when get entity kelas ruangan is null");
            }

        } else {
            logger.error("[Kelas RuanganBoImpl.saveAdd] Error when saving data kelas ruangan data is null");
            throw new GeneralBOException(" Error when saving data kelasa ruangan data is null");
        }

        logger.info("[KelasRuanganBoImpl.saveEdit] End <<<<<<<");
    }

    @Override
    public void saveDelete(KelasRuangan kelasRuangan) throws GeneralBOException {
        logger.info("[KelasRuanganBoImpl.saveDelete] start process");

        if (kelasRuangan!=null) {
            String status =cekBeforeDelete(kelasRuangan.getIdKelasRuangan());
            if (!status.equalsIgnoreCase("exist")){
                String idKelasRuangan = kelasRuangan.getIdKelasRuangan();

                ImSimrsKelasRuanganEntity entity = null;
                try {
                    // Get data from database by ID
                    entity = kelasRuanganDao.getById("idKelasRuangan", idKelasRuangan);
                } catch (HibernateException e) {
                    logger.error("[KelasRuanganBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data ruangan by Kelas Ruangan id, please inform to your admin...," + e.getMessage());
                }

                if (entity != null) {
                    // Modify from bean to entity serializable
                    entity.setNamaKelasRuangan(kelasRuangan.getNamaKelasRuangan());
                    entity.setFlag(kelasRuangan.getFlag());
                    entity.setAction("U");
                    entity.setLastUpdate(kelasRuangan.getLastUpdate());
                    entity.setLastUpdateWho(kelasRuangan.getLastUpdateWho());

                    try {
                        // Delete (Edit) into database
                        kelasRuanganDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[KelasRuanganBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Kelas Ruangan, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[KelasRuanganBoImpl.saveDelete] Error, not found data Ruangan with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Kelas Ruangan with request id, please check again your data ...");
                }
            }else {
                throw new GeneralBOException("Maaf Data tidak dapat dihapus, karna masih digunakan di Master Ruangan");
            }
        }

        logger.info("[KelasRuanganBoImpl.saveEdit] End <<<<<<<");
    }

    @Override
    public ImSimrsKelasRuanganEntity getKelasRuanganById(String id) throws GeneralBOException {
        return kelasRuanganDao.getById("idKelasRuangan", id);
    }

    @Override
    public List<KelasRuangan> getListKelasKamar(String kategori) throws GeneralBOException {
        return kelasRuanganDao.getListKelasKamar(kategori);
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String getIdKelasRuangan(){
        logger.info("[KelasRuanganBoImpl.getIdKelasRuangan] Start >>>>>>>");
        String id = "";

        try {
            id = kelasRuanganDao.getNextIdKelasRuangan();
        } catch (HibernateException e){
            logger.error("[KelasBoImpl.getIdPasien] Error when get next id kelas ruangan");
        }

        logger.info("[KelasBoImpl.getIdKelasRuangan] End <<<<<<<");
        return id;
    }

    public List<ImSimrsKelasRuanganEntity> getEntityByCriteria(KelasRuangan bean) throws GeneralBOException{
        logger.info("[KelasRuanganBoImpl.getEntityByCriteria] Start >>>>>>>");
        List<ImSimrsKelasRuanganEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())){
            hsCriteria.put("id_kelas_ruangan", bean.getIdKelasRuangan());
        }
        if (bean.getNamaKelasRuangan() != null && !"".equalsIgnoreCase(bean.getNamaKelasRuangan())){
            hsCriteria.put("nama_kelas_ruangan", bean.getNamaKelasRuangan());
        }

        List<ImSimrsKelasRuanganEntity> imSimrsKelasRuanganEntities = null;
        try {
            imSimrsKelasRuanganEntities = kelasRuanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RuanganBoImpl.getByByCriteria] Error when search pasien by criteria "+e.getMessage());
        }

        if (!imSimrsKelasRuanganEntities.isEmpty()){
            results = imSimrsKelasRuanganEntities;
        }

        logger.info("[RuanganBoImpl.getEntityByCriteria] End <<<<<<<");
        return results;
    }

    public String cekStatus(String namaKelasRuang)throws GeneralBOException{
        String status ="";
        List<ImSimrsKelasRuanganEntity> entities = new ArrayList<>();
        try {
            entities = kelasRuanganDao.getDataKelasRuangan(namaKelasRuang);
        } catch (HibernateException e) {
            logger.error("[KelasRuanganBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

    public String cekBeforeDelete(String idKelasRuangan)throws GeneralBOException{
        String status ="";
        List<ImSimrsKelasRuanganEntity> entities = new ArrayList<>();
        try {
            entities = kelasRuanganDao.cekData(idKelasRuangan);
        } catch (HibernateException e) {
            logger.error("[KelasRuanganBoImpl.cekBeforeDelete] Error, " + e.getMessage());
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