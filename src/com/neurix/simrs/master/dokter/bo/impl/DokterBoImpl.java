package com.neurix.simrs.master.dokter.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.dao.DokterPelayananDao;
import com.neurix.simrs.master.dokter.model.*;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.dao.PelayananSpesialisDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPoliSpesialisEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.spesialis.model.ImSimrsSpesialisEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 14/11/2019.
 */
public class DokterBoImpl extends DokterSpesialisModuls implements DokterBo {
    private static transient Logger logger = Logger.getLogger(DokterBoImpl.class);
    private DokterDao dokterDao;
    private PositionDao positionDao;
    private BranchDao branchDao;
    private BiodataDao biodataDao;
    private PelayananSpesialisDao pelayananSpesialisDao;
    private DokterPelayananDao dokterPelayananDao;

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Override
    public void saveDelete(Dokter bean) throws GeneralBOException {
        logger.info("[saveDelete.PelayananBoImpl] start process >>>");

        if (bean!=null) {
            String idDokter = bean.getIdDokter();
            String status = cekBeforeDelete(idDokter);

            if (!status.equalsIgnoreCase("exist")){
                ImSimrsDokterEntity entity = null;

                try {
                    // Get data from database by ID
                    entity = dokterDao.getById("idDokter", idDokter);
                } catch (HibernateException e) {
                    logger.error("[DokterBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data Dokter by Kode Dokter, please inform to your admin...," + e.getMessage());
                }

                if (entity != null) {

                    // Modify from bean to entity serializable
                    entity.setIdDokter(bean.getIdDokter());
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Delete (Edit) into database
                        dokterDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[DokterBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Dokter, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[DokterBoImpl.saveDelete] Error, not found data Dokter with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Dokter with request id, please check again your data ...");
                }
            }else {
                throw new GeneralBOException("Maaf Data tidak dapat dihapus, karna masih digunakan pada data Transaksi");
            }
        }
        logger.info("[DokterBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Dokter bean) throws GeneralBOException {
        logger.info("[DokterBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String kodering, seqKodering;
            String dokterId = bean.getIdDokter();

            ImSimrsDokterEntity imSimrsDokterEntity = null;
            try {
                // Get data from database by ID
                imSimrsDokterEntity = dokterDao.getById("idDokter", dokterId);
            } catch (HibernateException e) {
                logger.error("[DokterBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Dokter by IdDokter, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsDokterEntity != null) {
                if (bean.getNamaDokter().equalsIgnoreCase(imSimrsDokterEntity.getNamaDokter())){
                    String kode = imSimrsDokterEntity.getKodering();
                    if (kode != null){
                        String[] arrOfStr = kode.split("\\.");
//                        seqKodering = arrOfStr[4];
                        int lastIndex = arrOfStr.length - 1;
                        seqKodering = String.valueOf(lastIndex);

                        Map map = new HashMap<>();
                        map.put("position_id", bean.getPositionId());
                        String koderingPosition = positionDao.getKodringPosition(map);

                        String branchId = CommonUtil.userBranchLogin();
                        Map map1 = new HashMap<>();
                        map1.put("branch_id", branchId);
                        String koderingBranch = branchDao.getKodringBranches(map1);

                        kodering = koderingBranch+"."+koderingPosition+"."+seqKodering;
                    }else {
                        seqKodering = dokterDao.getNextKodering();
                        Map map = new HashMap<>();
                        map.put("position_id", bean.getPositionId());
                        String koderingPosition = positionDao.getKodringPosition(map);

                        String branchId = CommonUtil.userBranchLogin();
                        Map map1 = new HashMap<>();
                        map1.put("branch_id", branchId);
                        String koderingBranch = branchDao.getKodringBranches(map1);
                        kodering = koderingBranch+"."+koderingPosition+"."+seqKodering;
                    }

                    imSimrsDokterEntity.setIdDokter(bean.getIdDokter());
                    imSimrsDokterEntity.setNamaDokter(bean.getNamaDokter());
                    imSimrsDokterEntity.setIdPelayanan(bean.getIdPelayanan());
                    imSimrsDokterEntity.setKuota(bean.getKuota());
                    imSimrsDokterEntity.setKodeDpjp(bean.getKodeDpjp());
                    imSimrsDokterEntity.setKodering(kodering);

                    imSimrsDokterEntity.setFlagTele(bean.getFlagTele());
                    imSimrsDokterEntity.setFlagCall(bean.getFlagCall());
                    imSimrsDokterEntity.setKuotaTele(bean.getKuotaTele());
                    imSimrsDokterEntity.setKuotaOnSite(bean.getKuotaOnSite());
                    imSimrsDokterEntity.setSip(bean.getSip());

                    imSimrsDokterEntity.setFlag(bean.getFlag());
                    imSimrsDokterEntity.setAction(bean.getAction());
                    imSimrsDokterEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSimrsDokterEntity.setLastUpdate(bean.getLastUpdate());

                    String flag;
                    try {
                        // Update into database
                        dokterDao.updateAndSave(imSimrsDokterEntity);
                        //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[DokterBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Dokter, please info to your admin..." + e.getMessage());
                    }
                }else {
                    String status = cekStatus(bean.getNamaDokter());
                    if (!status.equalsIgnoreCase("exist")){
                        String kode = imSimrsDokterEntity.getKodering();
                        if (kode != null){
                            String[] arrOfStr = kode.split("\\.");
                            seqKodering = arrOfStr[4];

                            Map map = new HashMap<>();
                            map.put("position_id", bean.getPositionId());
                            String koderingPosition = positionDao.getKodringPosition(map);

                            String branchId = CommonUtil.userBranchLogin();
                            Map map1 = new HashMap<>();
                            map1.put("branch_id", branchId);
                            String koderingBranch = branchDao.getKodringBranches(map1);

                            kodering = koderingBranch+"."+koderingPosition+"."+seqKodering;
                        }else {
                            seqKodering = dokterDao.getNextKodering();
                            Map map = new HashMap<>();
                            map.put("position_id", bean.getPositionId());
                            String koderingPosition = positionDao.getKodringPosition(map);

                            String branchId = CommonUtil.userBranchLogin();
                            Map map1 = new HashMap<>();
                            map1.put("branch_id", branchId);
                            String koderingBranch = branchDao.getKodringBranches(map1);

                            kodering = koderingBranch+"."+koderingPosition+"."+seqKodering;
                        }
                        imSimrsDokterEntity.setIdDokter(bean.getIdDokter());
                        imSimrsDokterEntity.setNamaDokter(bean.getNamaDokter());
                        imSimrsDokterEntity.setIdPelayanan(bean.getIdPelayanan());
                        imSimrsDokterEntity.setKuota(bean.getKuota());
                        imSimrsDokterEntity.setKodeDpjp(bean.getKodeDpjp());
                        imSimrsDokterEntity.setKodering(kodering);
                        imSimrsDokterEntity.setFlagTele(bean.getFlagTele());
                        imSimrsDokterEntity.setFlagCall(bean.getFlagCall());
                        imSimrsDokterEntity.setKuotaTele(bean.getKuotaTele());
                        imSimrsDokterEntity.setKuotaOnSite(bean.getKuotaOnSite());
                        imSimrsDokterEntity.setSip(bean.getSip());

                        imSimrsDokterEntity.setFlag(bean.getFlag());
                        imSimrsDokterEntity.setAction(bean.getAction());
                        imSimrsDokterEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imSimrsDokterEntity.setLastUpdate(bean.getLastUpdate());

                        String flag;
                        try {
                            // Update into database
                            dokterDao.updateAndSave(imSimrsDokterEntity);
                            //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                        } catch (HibernateException e) {
                            logger.error("[DokterBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data Dokter, please info to your admin..." + e.getMessage());
                        }
                    }else {
                        throw new GeneralBOException("Maaf Data dengan Nama Dokter Tersebut Sudah Ada");
                    }
                }
            } else {
                logger.error("[DokterBoImpl.saveEdit] Error, not found data Dokter with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Dokter with request id, please check again your data ...");
            }
        }
        logger.info("[DokterBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Dokter saveAdd(Dokter bean) throws GeneralBOException {
        logger.info("[DokterBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getNamaDokter());
            String dokterId, seqKodering;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    dokterId = dokterDao.getNextDokter();
                } catch (HibernateException e) {
                    logger.error("[DokterBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence DokterId id, please info to your admin..." + e.getMessage());
                }
                try {
                    // Generating ID, get from postgre sequence
                    seqKodering = dokterDao.getNextKodering();
                } catch (HibernateException e) {
                    logger.error("[DokterBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence Kodering, please info to your admin..." + e.getMessage());
                }
                Map map = new HashMap<>();
                map.put("position_id", bean.getPositionId());

                String koderingPositionRaw;
                try{
                    koderingPositionRaw = positionDao.getKodringPosition(map);
                }catch (HibernateException e) {
                    logger.error("[DokterBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting Kodering Position, please info to your admin..." + e.getMessage());
                }

                String koderingPosition = koderingPositionRaw.split("\\.")[2];

                String branchId = CommonUtil.userBranchLogin();
                Map map1 = new HashMap<>();
                map1.put("branch_id", branchId);
                String koderingBranch;
                try{
                    koderingBranch = branchDao.getKodringBranches(map1);
                }catch (HibernateException e) {
                    logger.error("[DokterBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting Kodering Branches, please info to your admin..." + e.getMessage());
                }

                String kodering = koderingBranch+"."+koderingPosition+"."+seqKodering;

                // creating object entity serializable
                ImSimrsDokterEntity entity = new ImSimrsDokterEntity();

                entity.setIdDokter(dokterId);
                entity.setNamaDokter(bean.getNamaDokter());
                entity.setIdPelayanan(bean.getIdPelayanan());
                entity.setKuota(bean.getKuota());
                entity.setKodeDpjp(bean.getKodeDpjp());
                entity.setKodering(kodering);
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    dokterDao.addAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[DokterBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Dokter, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Nama Dokter Tersebut Sudah Ada");
            }
        }

        logger.info("[DokterBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Dokter> getByCriteria(Dokter bean) throws GeneralBOException {
        logger.info("[DokterBoImpl.getByCriteria] Start >>>>>>>>");
        List<Dokter> results = new ArrayList<>();

        if (bean != null) {
            List<ImSimrsDokterEntity> entities = getListEntityDokter(bean);
            if (!entities.isEmpty()) {
                results = setToTemplateDokter(entities);
            }
        }

        logger.info("[DokterBoImpl.getByCriteria] End <<<<<<<<");
        return results;
    }

    @Override
    public List<Dokter> getAll() throws GeneralBOException {
        return null;
    }

    protected List<ImSimrsDokterEntity> getListEntityDokter(Dokter bean) throws GeneralBOException {
        logger.info("[DokterBoImpl.getListEntityDokter] Start >>>>>>>>");
        List<ImSimrsDokterEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("id_dokter", bean.getIdDokter());

        try {
            results = dokterDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[DokterBoImpl.getListEntityDokter] Error When search dokter data ");
        }

        logger.info("[DokterBoImpl.getListEntityDokter] End <<<<<<<<");
        return results;
    }

    private List<Dokter> setToTemplateDokter(List<ImSimrsDokterEntity> entityList) {
        logger.info("[DokterBoImpl.setToTemplateDokter] Start >>>>>>>>");
        List<Dokter> results = new ArrayList<>();

        Dokter dokter;
        for (ImSimrsDokterEntity entity : entityList) {
            dokter = new Dokter();
            dokter.setIdDokter(entity.getIdDokter());
            dokter.setNamaDokter(entity.getNamaDokter());
            dokter.setKuota(entity.getKuota());
            dokter.setKuotaTele(entity.getKuotaTele());
            dokter.setFlag(entity.getFlag());
            dokter.setAction(entity.getAction());
            dokter.setCreatedDate(entity.getCreatedDate());
            dokter.setCreatedWho(entity.getCreatedWho());
            dokter.setLastUpdate(entity.getLastUpdate());
            dokter.setLastUpdateWho(entity.getLastUpdateWho());
            dokter.setLat(entity.getLat());
            dokter.setLon(entity.getLon());
            dokter.setKodeDpjp(entity.getKodeDpjp());
            dokter.setFlagCall(entity.getFlagCall());
            dokter.setFlagTele(entity.getFlagTele());
            results.add(dokter);
        }

        logger.info("[DokterBoImpl.setToTemplateDokter] End <<<<<<<<");
        return results;
    }

    @Override
    public List<Dokter> getByIdPelayanan(String idPelayanan, String branchId) throws GeneralBOException {
        logger.info("[DokterBoImpl.getByIdPelayanan] Start >>>>>>>>");
        List<Dokter> results = new ArrayList<>();
        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)) {

            List<ImSimrsPoliSpesialisEntity> poliSpesialisEntities = null;
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_pelayanan", idPelayanan);

            try {
                poliSpesialisEntities = pelayananSpesialisDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.info("[DokterBoImpl.getByIdPelayanan] Error when get dokter by pelayanan ", e);
            }

            if (!poliSpesialisEntities.isEmpty()) {
                for (ImSimrsPoliSpesialisEntity poliEntity : poliSpesialisEntities) {
                    DokterSpesialis dokterSpesialis = new DokterSpesialis();
                    dokterSpesialis.setIdSpesialis(poliEntity.getPrimaryKey().getIdSpesialis());

                    List<DokterSpesialis> dokterSpesialisList = getListDokterSpesialis(dokterSpesialis);
                    if (!dokterSpesialisList.isEmpty()) {
                        Dokter dokter;
                        for (DokterSpesialis listDokter : dokterSpesialisList) {

                            dokter = new Dokter();
                            dokter.setIdDokter(listDokter.getIdDokter());
                            ImSimrsDokterEntity dokterData = getListEntityDokter(dokter).get(0);

                            dokter.setIdSpesialis(listDokter.getIdSpesialis());
                            dokter.setNamaSpesialis(listDokter.getSpesialisName());
                            dokter.setNamaDokter(dokterData.getNamaDokter());
                            dokter.setFlag(dokterData.getFlag());
                            dokter.setAction(dokterData.getAction());
                            dokter.setLastUpdate(dokterData.getLastUpdate());
                            dokter.setLastUpdateWho(dokterData.getLastUpdateWho());
                            dokter.setCreatedDate(dokterData.getCreatedDate());
                            dokter.setCreatedWho(dokterData.getCreatedWho());
                            results.add(dokter);
                        }
                    }
                }
            }
        }
        logger.info("[DokterBoImpl.getByIdPelayanan] End <<<<<<<<");
        return results;
    }

    @Override
    public boolean editKuota(String idDokter, String kuota, String kuotaTele) throws GeneralBOException {
        logger.info("[DokterBoImpl.editKuota] Start <<<<<<<<");

        ImSimrsDokterEntity dokter = null;
        boolean isSuccess = false;

        try {
            dokter = dokterDao.getById("idDokter", idDokter, "Y");
        } catch (GeneralBOException e) {
            logger.info("[DokterBoImpl.editKuota] Error when editKuota ", e);
        }

        if (dokter != null) {
            dokter.setKuota(kuota);
            dokter.setKuotaTele(kuotaTele);
            try {
                dokterDao.updateAndSave(dokter);
                isSuccess = true;
            } catch (GeneralBOException e) {
                logger.info("[DokterBoImpl.editKuota] Error when editKuota ", e);
            }
        }

        logger.info("[DokterBoImpl.editKuota] End <<<<<<<<");
        return isSuccess;
    }

    public boolean editLatLon(String idDokter, String lat, String lon) {
        logger.info("[DokterBoImpl.editKuota] Start <<<<<<<<");

        ImSimrsDokterEntity dokter = null;
        boolean isSuccess = false;

        try {
            dokter = dokterDao.getById("idDokter", idDokter, "Y");
        } catch (GeneralBOException e) {
            logger.info("[DokterBoImpl.editKuota] Error when editKuota ", e);
        }

        if (dokter != null) {
            dokter.setLat(lat);
            dokter.setLon(lon);
            try {
                dokterDao.updateAndSave(dokter);
                isSuccess = true;
            } catch (GeneralBOException e) {
                logger.info("[DokterBoImpl.editKuota] Error when editKuota ", e);
            }
        }

        logger.info("[DokterBoImpl.editKuota] End <<<<<<<<");
        return isSuccess;
    }

    public boolean editFlagCall(String idDokter, String flagCall) {
        logger.info("[DokterBoImpl.editFlagCall] Start <<<<<<<<");

        ImSimrsDokterEntity dokter = null;
        boolean isSuccess = false;

        try {
            dokter = dokterDao.getById("idDokter", idDokter, "Y");
        } catch (GeneralBOException e) {
            logger.info("[DokterBoImpl.editKuota] Error when editKuota ", e);
        }

        if (dokter != null) {
            dokter.setFlagCall(flagCall);
            try {
                dokterDao.updateAndSave(dokter);
                isSuccess = true;
            } catch (GeneralBOException e) {
                logger.info("[DokterBoImpl.editKuota] Error when editKuota ", e);
            }
        }

        logger.info("[DokterBoImpl.editFlagCall] End <<<<<<<<");
        return isSuccess;
    }

    public boolean editFlagTele(String idDokter, String flagTele) {
        logger.info("[DokterBoImpl.editFlagCall] Start <<<<<<<<");

        ImSimrsDokterEntity dokter = null;
        boolean isSuccess = false;

        try {
            dokter = dokterDao.getById("idDokter", idDokter, "Y");
        } catch (GeneralBOException e) {
            logger.info("[DokterBoImpl.editKuota] Error when editKuota ", e);
        }

        if (dokter != null) {
            dokter.setFlagTele(flagTele);
            try {
                dokterDao.updateAndSave(dokter);
                isSuccess = true;
            } catch (GeneralBOException e) {
                logger.info("[DokterBoImpl.editKuota] Error when editKuota ", e);
            }
        }

        logger.info("[DokterBoImpl.editFlagCall] End <<<<<<<<");
        return isSuccess;
    }

    @Override
    public List<Dokter> getDokterByPelayanan(String idPelayanan, String notLike) throws GeneralBOException {
        List<Dokter> dokterList = new ArrayList<>();

        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)) {
            try {
                dokterList = dokterDao.getListDokterByPelayanan(idPelayanan, notLike);
            } catch (HibernateException e) {
                logger.error("Found Error when search " + e.getMessage());
            }
        }
        return dokterList;
    }

    @Override
    public List<Dokter> getDokterById(String idDokter) throws GeneralBOException {
        List<Dokter> dokterList = new ArrayList<>();

        if (idDokter != null && !"".equalsIgnoreCase(idDokter)) {
            try {
                dokterList = dokterDao.getListDokterById(idDokter);
            } catch (HibernateException e) {
                logger.error("Found Error when search " + e.getMessage());
            }
        }
        return dokterList;
    }

    @Override
    public List<Dokter> getSearchByCriteria(Dokter bean) throws GeneralBOException {
        List<Dokter> dokterList = new ArrayList<>();
        if(bean != null){

            Map hsCriteria = new HashMap();
            if(bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter())){
                hsCriteria.put("id_dokter", bean.getIdDokter());
            }
            if(bean.getNamaDokter() != null && !"".equalsIgnoreCase(bean.getNamaDokter())){
                hsCriteria.put("nama_dokter", bean.getNamaDokter());
            }
            if(bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
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

            List<ImSimrsDokterEntity> simrsDokterEntities = new ArrayList<>();
            try {
                simrsDokterEntities = dokterDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("Found Error when search asuransi "+e.getMessage());
            }

            if(simrsDokterEntities.size() > 0){
                for (ImSimrsDokterEntity entity: simrsDokterEntities){
                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(entity.getIdDokter());
                    dokter.setNamaDokter(entity.getNamaDokter());
                    dokter.setIdPelayanan(entity.getIdPelayanan());
                    dokter.setFlag(entity.getFlag());
                    dokter.setAction(entity.getAction());
                    dokter.setCreatedWho(entity.getCreatedWho());
                    dokter.setStCreatedDate(entity.getCreatedDate().toString());
                    dokter.setCreatedDate(entity.getCreatedDate());
                    dokter.setStLastUpdate(entity.getLastUpdate().toString());
                    dokter.setLastUpdate(entity.getLastUpdate());
                    dokter.setLastUpdateWho(entity.getLastUpdateWho());
                    dokter.setKuota(entity.getKuota());
                    dokter.setKodeDpjp(entity.getKodeDpjp());
                    dokter.setKodering(entity.getKodering());
                    dokter.setKuotaTele(entity.getKuotaTele());
                    dokter.setFlagCall(entity.getFlagCall());
                    dokter.setFlagTele(entity.getFlagTele());
                    dokter.setSip(entity.getSip());
                    dokter.setKuotaOnSite(entity.getKuotaOnSite());
                    dokter.setLat(entity.getLat());
                    dokter.setLon(entity.getLon());
//                    dokter.setKuotaBpjs(entity.getKuotaBpjs());

                    if (entity.getIdPelayanan() != null){
                        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                        Pelayanan pelayanan = new Pelayanan();
                        PelayananBo pelayananBo = (PelayananBo) context.getBean("pelayananBoProxy");
                        pelayanan.setIdPelayanan(entity.getIdPelayanan());
                        pelayanan.setFlag("Y");
                        List<Pelayanan> pelayanans = pelayananBo.getByCriteria(pelayanan);
                        String pelayananName = pelayanans.get(0).getNamaPelayanan();
                        dokter.setNamaPelayanan(pelayananName);
                    }
//                    else {
//                        dokter.setNamaPelayanan("-");
//                    }

                    dokterList.add(dokter);
                }
            }
        }

        return dokterList;
    }

    @Override
    public List<Dokter> typeaheadDokter(String dokterName) throws GeneralBOException {
        logger.info("[DokterBoImpl.typeaheadDokter] start process >>>");

        // Mapping with collection and put
        List<Dokter> listOfResult = new ArrayList();
        List<ImSimrsDokterEntity> imDokterEntityList = null;
        try {
            imDokterEntityList = dokterDao.getDokterListByLikeDokterName(dokterName);
        } catch (HibernateException e) {
            logger.error("[DokterBoImpl.typeaheadDokter] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(imDokterEntityList != null){
            ImBiodataEntity biodata;
            Dokter returnDokter;
            // Looping from dao to object and save in collection
            for(ImSimrsDokterEntity imSimrsDokterEntity : imDokterEntityList) {
                returnDokter = new Dokter();
                //RAKA-17MAR2021==>Hanya Dokter KSO (Tamu)
                try {
                    biodata = biodataDao.getById("nip", imSimrsDokterEntity.getIdDokter());
                } catch (HibernateException e) {
                    logger.error("[DokterBoImpl.typeaheadDokter] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
                if (biodata != null) {
                    if ("Y".equalsIgnoreCase(biodata.getFlagDokterKso())) {
                        //RAKA-17MAR2021 ==> mendapatkan Branch ID
                        List<PersonilPosition> positionList = new ArrayList<>();
                        try{
                            positionList = biodataDao.getListPersonilPositionByNip(imSimrsDokterEntity.getIdDokter());
                        }catch (HibernateException e) {
                            logger.error("[DokterBoImpl.typeaheadDokter] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                        for(PersonilPosition position : positionList){
                            returnDokter.setBranchId(position.getBranchId());
                        }
                        //RAKA-end
                        returnDokter.setIdDokter(imSimrsDokterEntity.getIdDokter());
                        returnDokter.setNamaDokter(imSimrsDokterEntity.getNamaDokter());

                        returnDokter.setCreatedWho(imSimrsDokterEntity.getCreatedWho());
                        returnDokter.setCreatedDate(imSimrsDokterEntity.getCreatedDate());
                        returnDokter.setLastUpdate(imSimrsDokterEntity.getLastUpdate());
                        returnDokter.setAction(imSimrsDokterEntity.getAction());
                        returnDokter.setFlag(imSimrsDokterEntity.getFlag());
                        listOfResult.add(returnDokter);
                    }
                }
                //RAKA-end
            }
        }
        logger.info("[DokterBoImpl.typeaheadDokter] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Dokter> getListDokterByBranchId(String branchId, String idDokter) throws GeneralBOException {
        List<Dokter> dokterList = new ArrayList<>();
        try {
            dokterList = dokterDao.getListDokterByBranchId(branchId, idDokter);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return dokterList;
    }

    @Override
    public List<Dokter> getListDokterByIdDetailCheckup(String idDetailChekcup, String approve) throws GeneralBOException {
        List<Dokter> dokterList = new ArrayList<>();
        try {
            dokterList = dokterDao.getListDokterByIdDetailCheckup(idDetailChekcup, approve);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return dokterList;
    }

    @Override
    public List<Dokter> getListDokterByNoCheckup(String noChekcup) throws GeneralBOException {
        List<Dokter> dokterList = new ArrayList<>();
        try {
            dokterList = dokterDao.getListDokterByNoCheckup(noChekcup);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return dokterList;
    }

    @Override
    public List<Dokter> searchByQuery(Dokter bean) throws GeneralBOException {
        List<Dokter> dokterList = new ArrayList<>();
        try {
            dokterList = dokterDao.getListDokterByQuery(bean);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return dokterList;
    }

    @Override
    public void saveEditDokter(Dokter bean) throws GeneralBOException {
        if(bean != null){
            ImSimrsDokterEntity dokterEntity = new ImSimrsDokterEntity();
            try {
                dokterEntity = dokterDao.getById("idDokter", bean.getIdDokter());
                if(dokterEntity != null){
                    if(bean.getNamaDokter() != null){
                        dokterEntity.setNamaDokter(bean.getNamaDokter());
                    }
                    if(bean.getKodeDpjp() != null){
                        dokterEntity.setKodeDpjp(bean.getKodeDpjp());
                    }
                    if(bean.getSip() != null){
                        dokterEntity.setSip(bean.getSip());
                    }
                    if(bean.getKuota() != null){
                        dokterEntity.setKuota(bean.getKuota());
                    }
                    if(bean.getKuotaTele() != null){
                        dokterEntity.setKuotaTele(bean.getKuotaTele());
                    }
                    if(bean.getKuotaOnSite() != null){
                        dokterEntity.setKuotaOnSite(bean.getKuotaOnSite());
                    }
                    if(bean.getKuotaBpjs() != null){
                        dokterEntity.setKuotaBpjs(bean.getKuotaBpjs());
                    }
                    if(bean.getFlagCall() != null){
                        dokterEntity.setFlagCall(bean.getFlagCall());
                    }
                    if(bean.getFlagTele() != null){
                        dokterEntity.setFlagTele(bean.getFlagTele());
                    }
                    dokterEntity.setFlag(bean.getFlag());
                    dokterEntity.setAction(bean.getAction());
                    dokterEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    dokterEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        dokterDao.updateAndSave(dokterEntity);
                    }catch (HibernateException e){
                        logger.error(e.getMessage());
                        throw new GeneralBOException("Error when search data dokter, "+e.getMessage());
                    }

                    if(bean.getPelayananList().size() > 0){
                        HashMap hsCriteria = new HashMap();
                        hsCriteria.put("id_dokter", bean.getIdDokter());
                        List<ImSimrsDokterPelayananEntity> dokterPelayananEntity = new ArrayList<>();
                        try {
                            dokterPelayananEntity = dokterPelayananDao.getByCriteria(hsCriteria);
                        }catch (HibernateException e){
                            logger.error(e.getMessage());
                            throw new GeneralBOException("Error when search data dokter pelayanan, "+e.getMessage());
                        }
                        if(dokterPelayananEntity.size() > 0){
                            for (ImSimrsDokterPelayananEntity entity: dokterPelayananEntity){
                                entity.setLastUpdate(bean.getLastUpdate());
                                entity.setLastUpdateWho(bean.getLastUpdateWho());
                                entity.setAction("D");
                                entity.setFlag("N");
                                try {
                                    dokterPelayananDao.deleteAndSave(entity);
                                }catch (HibernateException e){
                                    logger.error(e.getMessage());
                                    throw new GeneralBOException("Error when search data dokter pelayanan, "+e.getMessage());
                                }
                            }
                        }

                        for (DokterPelayanan list: bean.getPelayananList()){
                            ImSimrsDokterPelayananEntity entity = new ImSimrsDokterPelayananEntity();
                            entity.setIdDokterPelayanan(dokterPelayananDao.getNextId());
                            entity.setIdDokter(bean.getIdDokter());
                            entity.setIdPelayanan(list.getIdPelayanan());
                            entity.setAction("C");
                            entity.setFlag("Y");
                            entity.setCreatedDate(bean.getLastUpdate());
                            entity.setCreatedWho(bean.getLastUpdateWho());
                            entity.setLastUpdateWho(bean.getLastUpdateWho());
                            entity.setLastUpdate(bean.getLastUpdate());
                            try {
                                dokterPelayananDao.addAndSave(entity);
                            }catch (HibernateException e){
                                logger.error(e.getMessage());
                                throw new GeneralBOException("Error when search data dokter pelayanan, "+e.getMessage());
                            }
                        }
                    }
                }
            }catch (HibernateException e){
                logger.error(e.getMessage());
                throw new GeneralBOException("Error when search data dokter, "+e.getMessage());
            }
        }
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    public void setPelayananSpesialisDao(PelayananSpesialisDao pelayananSpesialisDao) {
        this.pelayananSpesialisDao = pelayananSpesialisDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String namaDokter)throws GeneralBOException{
        String status ="";
        List<ImSimrsDokterEntity> entities = new ArrayList<>();
        try {
            entities = dokterDao.getDataDokter(namaDokter);
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

    public String cekBeforeDelete(String idDokter)throws GeneralBOException{
        String status ="";
        List<ImSimrsDokterEntity> entities = new ArrayList<>();
        try {
            entities = dokterDao.cekData(idDokter);
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

    public void setDokterPelayananDao(DokterPelayananDao dokterPelayananDao) {
        this.dokterPelayananDao = dokterPelayananDao;
    }
}
