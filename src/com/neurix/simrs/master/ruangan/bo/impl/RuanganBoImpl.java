package com.neurix.simrs.master.ruangan.bo.impl;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.dao.KelasRuanganDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuanganBoImpl implements RuanganBo {
    protected static transient Logger logger = Logger.getLogger(RuanganBoImpl.class);
    private RuanganDao ruanganDao;
    private KelasRuanganDao kelasRuanganDao;
    private BranchDao branchDao;

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setKelasRuanganDao(KelasRuanganDao kelasRuanganDao) {
        this.kelasRuanganDao = kelasRuanganDao;
    }

    public static void setLogger(Logger logger) {
        RuanganBoImpl.logger = logger;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    @Override
    public List<Ruangan> getByCriteria(Ruangan bean) throws GeneralBOException {
        logger.info("[RuanganBoImpl.getByCriteria] Start >>>>>>");
        List<Ruangan> result = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }
        if (bean.getNamaRuangan() != null && !"".equalsIgnoreCase(bean.getNamaRuangan())) {
            hsCriteria.put("nama_ruangan", bean.getNamaRuangan());
        }
        if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())) {
            hsCriteria.put("id_kelas_ruangan", bean.getIdKelasRuangan());
        }
//        if (bean.getStatusRuangan() != null && !"".equalsIgnoreCase(bean.getStatusRuangan())) {
//            hsCriteria.put("status_ruangan", bean.getStatusRuangan());
//        }
        if (bean.getNoRuangan() != null && !"".equalsIgnoreCase(bean.getNoRuangan())) {
            hsCriteria.put("no_ruangan", bean.getNoRuangan());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())) {
            hsCriteria.put("id_kelas_ruangan", bean.getIdKelasRuangan());
        }
//        if (bean.getStatusRuangan() != null && !"".equalsIgnoreCase(bean.getStatusRuangan())) {
//            hsCriteria.put("status_ruangan", bean.getStatusRuangan());
//        }
//        if (bean.getSisaKuota() != null && !"".equalsIgnoreCase(bean.getSisaKuota().toString())) {
//            hsCriteria.put("sisa_kuota", bean.getSisaKuota());
//        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            if ("N".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", "N");
            } else {
                hsCriteria.put("flag", bean.getFlag());
            }
        } else {
            hsCriteria.put("flag", "Y");
        }

        List<MtSimrsRuanganEntity> mtSimrsRuanganEntityList = null;
        try {
            mtSimrsRuanganEntityList = ruanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RuanganBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
        }

        if (!mtSimrsRuanganEntityList.isEmpty()) {
            Ruangan ruangan;
            for (MtSimrsRuanganEntity listEntity : mtSimrsRuanganEntityList) {
                ruangan = new Ruangan();
                ruangan.setIdRuangan(listEntity.getIdRuangan());
                ruangan.setNamaRuangan(listEntity.getNamaRuangan());
                ruangan.setNoRuangan(listEntity.getNoRuangan());

//                ruangan.setStatusRuangan(listEntity.getStatusRuangan());
//                if (listEntity.getStatusRuangan().equalsIgnoreCase("Y")){
//                    ruangan.setStatusRuanganName("Tersedia");
//                } else{
//                    ruangan.setStatusRuanganName("Tidak Tersedia");
//                }
                ruangan.setIdKelasRuangan(listEntity.getIdKelasRuangan());
                ruangan.setKeterangan(listEntity.getKeterangan());
                ruangan.setTarif(listEntity.getTarif());
                ruangan.setStTarif(CommonUtil.numbericFormat(new BigDecimal( listEntity.getTarif()),"###,###"));
                ruangan.setBranchId(listEntity.getBranchId());
//                ruangan.setSisaKuota(listEntity.getSisaKuota());
//                ruangan.setKuota(listEntity.getKuota());
                ruangan.setFlag(listEntity.getFlag());
                ruangan.setAction(listEntity.getAction());
                ruangan.setStCreatedDate(listEntity.getCreatedDate().toString());
                ruangan.setCreatedWho(listEntity.getCreatedWho());
                ruangan.setStLastUpdate(listEntity.getLastUpdate().toString());
                ruangan.setLastUpdateWho(listEntity.getLastUpdateWho());
                ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganDao.getById("idKelasRuangan", listEntity.getIdKelasRuangan());
                if(kelasRuanganEntity != null){
                    ruangan.setNamaKelasRuangan(kelasRuanganEntity.getNamaKelasRuangan());
                }

                ImBranchesPK primaryKey = new ImBranchesPK();
                primaryKey.setId(listEntity.getBranchId());
                ImBranches branches = branchDao.getById(primaryKey, "Y");
                if(branches != null){
                    ruangan.setBranchName(branches.getBranchName());
                }

                result.add(ruangan);
            }
        }
        logger.info("[RuanganBoImpl.getByCriteria] End <<<<<<");
        return result;
    }

    @Override
    public void saveAdd(Ruangan ruangan) throws GeneralBOException {
        logger.info("[RuanganBoImpl.saveAdd] Start >>>>>>>");

        if (ruangan != null) {
            String status = cekStatus(ruangan.getNamaRuangan());
            if (!status.equalsIgnoreCase("exist")){
                MtSimrsRuanganEntity mtSimrsRuanganEntity = new MtSimrsRuanganEntity();
                String id = getIdRuangan();

                mtSimrsRuanganEntity.setIdRuangan("RUA" + id);
                mtSimrsRuanganEntity.setNamaRuangan(ruangan.getNamaRuangan());
                mtSimrsRuanganEntity.setNoRuangan(ruangan.getNoRuangan());
//                mtSimrsRuanganEntity.setStatusRuangan(ruangan.getStatusRuangan());
                mtSimrsRuanganEntity.setIdKelasRuangan(ruangan.getIdKelasRuangan());
                mtSimrsRuanganEntity.setKeterangan(ruangan.getKeterangan());
                mtSimrsRuanganEntity.setTarif(ruangan.getTarif());
                mtSimrsRuanganEntity.setBranchId(ruangan.getBranchId());
//                mtSimrsRuanganEntity.setKuota(ruangan.getKuota());
//                mtSimrsRuanganEntity.setSisaKuota(ruangan.getSisaKuota());

                mtSimrsRuanganEntity.setFlag("Y");
                mtSimrsRuanganEntity.setAction("C");
                mtSimrsRuanganEntity.setCreatedDate(ruangan.getCreatedDate());
                mtSimrsRuanganEntity.setLastUpdate(ruangan.getLastUpdate());
                mtSimrsRuanganEntity.setCreatedWho(ruangan.getCreatedWho());
                mtSimrsRuanganEntity.setLastUpdateWho(ruangan.getLastUpdateWho());

                try {
                    ruanganDao.addAndSave(mtSimrsRuanganEntity);
                } catch (HibernateException e) {
                    logger.error("[RuanganBoImpl.saveAdd] Error when saving data ruangan", e);
                    throw new GeneralBOException(" Error when saving data ruangan " + e.getMessage());
                }
            }else {
                throw new GeneralBOException("Maaf Data dengan Nama Ruangan Tersebut Sudah Ada");
            }
        } else {
            logger.error("[RuanganBoImpl.saveAdd] Error when saving data pasien data is null");
            throw new GeneralBOException(" Error when saving data pasien data is null");
        }

        logger.info("[PasienBoImpl.saveAdd] End <<<<<<<");
    }

    @Override
    public void saveEdit(Ruangan ruangan) throws GeneralBOException {
        logger.info("[RuanganBoImpl.saveEdit] Start >>>>>>>");
        if (ruangan != null) {

            String ruanganid = ruangan.getIdRuangan();
            MtSimrsRuanganEntity mtSimrsRuanganEntity = null;
            try {
                // Get data from database by ID
                mtSimrsRuanganEntity = ruanganDao.getById("idRuangan", ruanganid);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGaji by Kode PayrollSkalaGaji, please inform to your admin...," + e.getMessage());
            }

//            Ruangan editRuangan = new Ruangan();
//            editRuangan.setIdRuangan(ruangan.getIdRuangan());
//            MtSimrsRuanganEntity mtSimrsRuanganEntity = getEntityByCriteria(editRuangan).get(0);

            if (mtSimrsRuanganEntity != null) {
                if (ruangan.getNamaRuangan().equalsIgnoreCase(mtSimrsRuanganEntity.getNamaRuangan())){
                    mtSimrsRuanganEntity.setIdRuangan(ruangan.getIdRuangan());
                    mtSimrsRuanganEntity.setNamaRuangan(ruangan.getNamaRuangan());
                    mtSimrsRuanganEntity.setNoRuangan(ruangan.getNoRuangan());
//                    mtSimrsRuanganEntity.setStatusRuangan(ruangan.getStatusRuangan());
                    mtSimrsRuanganEntity.setIdKelasRuangan(ruangan.getIdKelasRuangan());
                    mtSimrsRuanganEntity.setKeterangan(ruangan.getKeterangan());
                    mtSimrsRuanganEntity.setTarif(ruangan.getTarif());
                    mtSimrsRuanganEntity.setBranchId(ruangan.getBranchId());
//                    mtSimrsRuanganEntity.setKuota(ruangan.getKuota());
//                    mtSimrsRuanganEntity.setSisaKuota(ruangan.getSisaKuota());

                    mtSimrsRuanganEntity.setFlag(ruangan.getFlag());
                    mtSimrsRuanganEntity.setAction("U");
                    mtSimrsRuanganEntity.setLastUpdate(ruangan.getLastUpdate());
                    mtSimrsRuanganEntity.setLastUpdateWho(ruangan.getLastUpdateWho());

                    try {
                        ruanganDao.updateAndSave(mtSimrsRuanganEntity);
                    } catch (HibernateException e) {
                        logger.error("[RuanganBoImpl.saveAdd] Error when Updating data ruangan", e);
                        throw new GeneralBOException(" Error when Updating data ruangan " + e.getMessage());
                    }
                }else {
                    String status = cekStatus(ruangan.getNamaRuangan());
                    if (!status.equalsIgnoreCase("exist")){
                        mtSimrsRuanganEntity.setIdRuangan(ruangan.getIdRuangan());
                        mtSimrsRuanganEntity.setNamaRuangan(ruangan.getNamaRuangan());
                        mtSimrsRuanganEntity.setNoRuangan(ruangan.getNoRuangan());
//                        mtSimrsRuanganEntity.setStatusRuangan(ruangan.getStatusRuangan());
                        mtSimrsRuanganEntity.setIdKelasRuangan(ruangan.getIdKelasRuangan());
                        mtSimrsRuanganEntity.setKeterangan(ruangan.getKeterangan());
                        mtSimrsRuanganEntity.setTarif(ruangan.getTarif());
                        mtSimrsRuanganEntity.setBranchId(ruangan.getBranchId());
//                        mtSimrsRuanganEntity.setKuota(ruangan.getKuota());
//                        mtSimrsRuanganEntity.setSisaKuota(ruangan.getSisaKuota());

                        mtSimrsRuanganEntity.setFlag(ruangan.getFlag());
                        mtSimrsRuanganEntity.setAction("U");
                        mtSimrsRuanganEntity.setLastUpdate(ruangan.getLastUpdate());
                        mtSimrsRuanganEntity.setLastUpdateWho(ruangan.getLastUpdateWho());

                        try {
                            ruanganDao.updateAndSave(mtSimrsRuanganEntity);
                        } catch (HibernateException e) {
                            logger.error("[RuanganBoImpl.saveAdd] Error when Updating data ruangan", e);
                            throw new GeneralBOException(" Error when Updating data ruangan " + e.getMessage());
                        }
                    }else {
                        throw new GeneralBOException("Maaf Data dengan Nama Ruangan Tersebut Sudah Ada");
                    }
                }
            } else {
                logger.error("[RuanganBoImpl.saveAdd] Error when get entity ruangan is null");
                throw new GeneralBOException("  Error when get entity ruangan is null");
            }

        } else {
            logger.error("[RuanganBoImpl.saveAdd] Error when saving data ruangan data is null");
            throw new GeneralBOException(" Error when saving data ruangan data is null");
        }

        logger.info("[RuanganBoImpl.saveEdit] End <<<<<<<");
    }

    @Override
    public void saveDelete(Ruangan ruangan) throws GeneralBOException {
        logger.info("[RuanganBoImpl.saveDelete] start process");

        if (ruangan != null) {
            String idRuangan = ruangan.getIdRuangan();
            String status = cekBeforeDelete(idRuangan);
            if (!status.equalsIgnoreCase("exist")){
                MtSimrsRuanganEntity entity = null;
                try {
                    // Get data from database by ID
                    entity = ruanganDao.getById("idRuangan", idRuangan);
                } catch (HibernateException e) {
                    logger.error("[RuanganBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data ruangan by Ruangan id, please inform to your admin...," + e.getMessage());
                }

                if (entity != null) {
                    // Modify from bean to entity serializable
                    entity.setIdRuangan(idRuangan);
//                entity.setNamaRuangan(ruangan.getNamaRuangan());
//                entity.setNoRuangan(ruangan.getNoRuangan());
//                entity.setIdKelasRuangan(ruangan.getIdKelasRuangan());
//                entity.setKeterangan(ruangan.getKeterangan()
// );
//                entity.setTarif(ruangan.getTarif());
//                entity.setBranchId(ruangan.getBranchId());

                    entity.setFlag(ruangan.getFlag());
                    entity.setAction("U");
                    entity.setLastUpdate(ruangan.getLastUpdate());
                    entity.setLastUpdateWho(ruangan.getLastUpdateWho());

                    try {
                        // Delete (Edit) into database
                        ruanganDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[RuanganBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Ruangan, please info to your admin..." + e.getMessage());
                    }


                } else {
                    logger.error("[RuanganBoImpl.saveDelete] Error, not found data Ruangan with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Ruangan with request id, please check again your data ...");
                }
            }else {
                throw new GeneralBOException("Maaf Data tidak dapat dihapus, karna masih digunakan pada data Transaksi");
            }
        }

        logger.info("[RuanganBoImpl.saveDelete] End <<<<<<<");
    }

    @Override
    public CheckResponse updateRuangan(Ruangan bean) throws GeneralBOException {
        CheckResponse response = new CheckResponse();
        if (bean.getIdRuangan() != null) {

            MtSimrsRuanganEntity entity = new MtSimrsRuanganEntity();

            try {
                entity = ruanganDao.getById("idRuangan", bean.getIdRuangan());
            }catch (HibernateException e){
                logger.error("Found Error when seach mt ruangan "+e.getMessage());
            }

            if(entity != null){

                Timestamp now = new Timestamp(System.currentTimeMillis());
                String user = CommonUtil.userLogin();

                entity.setSisaKuota(entity.getSisaKuota() + 1);
                entity.setLastUpdate(now);
                entity.setLastUpdateWho(user);

                if(entity.getSisaKuota() > 0){
                    entity.setStatusRuangan("Y");
                }

                try {
                    ruanganDao.updateAndSave(entity);
                }catch (HibernateException e){
                    logger.error("Found Error when update ruangan "+e.getMessage());
                }

            }

        }
        return response;
    }

    @Override
    public MtSimrsRuanganEntity getEntityRuanganById(String id) throws GeneralBOException {
        return ruanganDao.getById("idRuangan", id);
    }

    @Override
    public List<Ruangan> getListRuangan(Ruangan bean) throws GeneralBOException {
        return ruanganDao.getListRuanganKamar(bean);
    }

    @Override
    public List<Ruangan> getJustListRuangan(String idKelas, String branchId) throws GeneralBOException {
        return ruanganDao.getListJustRuanganKamar(idKelas, branchId);
    }

    public String getIdRuangan() {
        logger.info("[RuanganBoImpl.getIdRuangan] Start >>>>>>>");
        String id = "";

        try {
            id = ruanganDao.getNextIdRuangan();
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getIdPasien] Error when get next id pasien");
        }

        logger.info("[PasienBoImpl.getIdPasien] End <<<<<<<");
        return id;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<MtSimrsRuanganEntity> getEntityByCriteria(Ruangan bean) throws GeneralBOException {
        logger.info("[RuanganBoImpl.getEntityByCriteria] Start >>>>>>>");
        List<MtSimrsRuanganEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }
        if (bean.getNamaRuangan() != null && !"".equalsIgnoreCase(bean.getNamaRuangan())) {
            hsCriteria.put("nama_ruangan", bean.getNamaRuangan());
        }
        if (bean.getNoRuangan() != null && !"".equalsIgnoreCase(bean.getNoRuangan())) {
            hsCriteria.put("no_ruangan", bean.getNoRuangan());
        }
//        if (bean.getStatusRuangan() != null && !"".equalsIgnoreCase(bean.getStatusRuangan())) {
//            hsCriteria.put("status_ruangan", bean.getStatusRuangan());
//        }
        if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())) {
            hsCriteria.put("id_kelas_ruangan", bean.getIdKelasRuangan());
        }
        if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
            hsCriteria.put("keterangan", bean.getKeterangan());
        }
        if (bean.getTarif() != null && !"".equalsIgnoreCase(String.valueOf(bean.getTarif()))) {
            hsCriteria.put("tarif", bean.getTarif());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }

        List<MtSimrsRuanganEntity> mtSimrsRuanganEntities = null;
        try {
            mtSimrsRuanganEntities = ruanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RuanganBoImpl.getByByCriteria] Error when search pasien by criteria " + e.getMessage());
        }

        if (!mtSimrsRuanganEntities.isEmpty()) {
            results = mtSimrsRuanganEntities;
        }

        logger.info("[RuanganBoImpl.getEntityByCriteria] End <<<<<<<");
        return results;
    }

    public String cekStatus(String namaRuangan)throws GeneralBOException{
        String status ="";
        List<MtSimrsRuanganEntity> entities = new ArrayList<>();
        try {
            entities = ruanganDao.getDataPelayanan(namaRuangan);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

    public String cekBeforeDelete(String idRuangan)throws GeneralBOException{
        String status ="";
        List<MtSimrsRuanganEntity> entities = new ArrayList<>();
        try {
            entities = ruanganDao.cekData(idRuangan);
        } catch (HibernateException e) {
            logger.error("[PelayananBoImpl.cekBeforeDelete] Error, " + e.getMessage());
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