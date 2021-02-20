package com.neurix.simrs.master.pelayanan.bo.impl;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.dao.HeaderPelayananDao;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.dao.PelayananSpesialisDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsHeaderPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPoliSpesialisEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PelayananBoImpl implements PelayananBo {
    protected static transient Logger logger = Logger.getLogger(PelayananBoImpl.class);
    private PelayananDao pelayananDao;
    private PelayananSpesialisDao pelayananSpesialisDao;
    private PositionDao positionDao;
    private BranchDao branchDao;
    private HeaderPelayananDao headerPelayananDao;

    public void setHeaderPelayananDao(HeaderPelayananDao headerPelayananDao) {
        this.headerPelayananDao = headerPelayananDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {

        this.pelayananDao = pelayananDao;
    }

    public void setPelayananSpesialisDao(PelayananSpesialisDao pelayananSpesialisDao) {
        this.pelayananSpesialisDao = pelayananSpesialisDao;
    }

    public PelayananDao getPelayananDao() {
        return pelayananDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public List<Pelayanan> getListAllPelayanan() throws GeneralBOException {
        logger.info("[pelayananBoImpl.getListAllPelayanan] Start >>>>>>");
        List<Pelayanan> result = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");

        List<ImSimrsPelayananEntity> imSimrsPelayananEntities = null;
        List<ImSimrsPoliSpesialisEntity> imSimrsPoliSpesialisEntities = null;
        try {
            imSimrsPelayananEntities = pelayananDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[pelayananBoImpl.getListAllPelayanan] Error get pelayanan data " + e.getMessage());
        }


        try {
            imSimrsPoliSpesialisEntities = pelayananSpesialisDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[pelayananBoImpl.getListAllPelayanan] Error get pelayanan data " + e.getMessage());
        }

        if (!imSimrsPelayananEntities.isEmpty()) {
            Pelayanan pelayanan;
            for (ImSimrsPelayananEntity listEntity : imSimrsPelayananEntities) {
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(listEntity.getIdPelayanan());
                result.add(pelayanan);
            }
        }
        logger.info("[pelayananBoImpl.getListAllPelayanan] End <<<<<<");
        return result;
    }

    @Override
    public List<Pelayanan> getListApotek(String branch, String tipeApotek) throws GeneralBOException {

        String tipe = "apotek%";
        if (!"".equalsIgnoreCase(tipeApotek) && tipeApotek != null) {
            tipe = tipeApotek;
        }

        List<Pelayanan> listApotek = new ArrayList<>();
        try {
            listApotek = pelayananDao.getListApotek(branch, tipe);
        } catch (HibernateException e) {
            logger.error("[pelayananBoImpl.getListAllPelayanan] Error get pelayanan data apotek " + e.getMessage());
        }

        return listApotek;
    }

    @Override
    public void saveEdit(Pelayanan bean) throws GeneralBOException {
        logger.info("[pelayananBoImpl.saveEdit] start process >>>");
        if (bean != null) {
            String idPelayanan = bean.getIdPelayanan();
            ImSimrsPelayananEntity imSimrsPelayananEntity = null;
            try {
                imSimrsPelayananEntity = pelayananDao.getById("idPelayanan", idPelayanan);
            } catch (HibernateException e) {
                logger.error("[pelayananBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data pelayanan by Kode pelayanan, please inform to your admin...," + e.getMessage());
            }
            if (imSimrsPelayananEntity.getIdHeaderPelayanan().equalsIgnoreCase(bean.getIdHeaderPelayanan())) {
                if (imSimrsPelayananEntity != null) {
                    imSimrsPelayananEntity.setIdHeaderPelayanan(bean.getIdHeaderPelayanan());
                    imSimrsPelayananEntity.setFlag(bean.getFlag());
                    imSimrsPelayananEntity.setAction(bean.getAction());
                    imSimrsPelayananEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSimrsPelayananEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        pelayananDao.updateAndSave(imSimrsPelayananEntity);
                    } catch (HibernateException e) {
                        logger.error("[pelayananBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data pelayanan, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[pelayananBoImpl.saveEdit] Error, not found data pelayanan with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data pelayanan with request id, please check again your data ...");
                }
            } else {
                List<ImSimrsPelayananEntity> cekListPelayanan = new ArrayList<>();
                try {
                    cekListPelayanan = pelayananDao.getDataPelayanan(bean.getIdHeaderPelayanan());
                } catch (HibernateException e) {
                    logger.error(e.getMessage());
                }
                if (cekListPelayanan.size() > 0) {
                    throw new GeneralBOException("Maaf Data " + bean.getNamaPelayanan() + " Sudah Ada");
                } else {
                    if (imSimrsPelayananEntity != null) {
                        imSimrsPelayananEntity.setIdHeaderPelayanan(bean.getIdHeaderPelayanan());
                        imSimrsPelayananEntity.setFlag(bean.getFlag());
                        imSimrsPelayananEntity.setAction(bean.getAction());
                        imSimrsPelayananEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imSimrsPelayananEntity.setLastUpdate(bean.getLastUpdate());
                        try {
                            pelayananDao.updateAndSave(imSimrsPelayananEntity);
                        } catch (HibernateException e) {
                            logger.error("[pelayananBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data pelayanan, please info to your admin..." + e.getMessage());
                        }
                    } else {
                        logger.error("[pelayananBoImpl.saveEdit] Error, not found data pelayanan with request id, please check again your data ...");
                        throw new GeneralBOException("Error, not found data pelayanan with request id, please check again your data ...");
                    }
                }
            }
        }
        logger.info("[pelayananBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void saveAdd(Pelayanan bean) throws GeneralBOException {
        logger.info("[pelayananBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            List<ImSimrsPelayananEntity> cekList = new ArrayList<>();
            try {
                cekList = pelayananDao.getDataPelayanan(bean.getIdHeaderPelayanan());
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
            if (cekList.size() > 0) {
                throw new GeneralBOException("Nama Pelayanan " + bean.getNamaPelayanan() + " sudah ada...!");
            } else {
                String pelayananId;
                try {
                    pelayananId = pelayananDao.getNextPelayananId();
                } catch (HibernateException e) {
                    logger.error("[PelayananBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiId id, please info to your admin..." + e.getMessage());
                }

                ImSimrsPelayananEntity imSimrsPelayananEntity = new ImSimrsPelayananEntity();
                imSimrsPelayananEntity.setIdPelayanan(pelayananId);
                imSimrsPelayananEntity.setBranchId(bean.getBranchId());
                imSimrsPelayananEntity.setIdHeaderPelayanan(bean.getIdHeaderPelayanan());
                imSimrsPelayananEntity.setFlag(bean.getFlag());
                imSimrsPelayananEntity.setAction(bean.getAction());
                imSimrsPelayananEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsPelayananEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsPelayananEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsPelayananEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    pelayananDao.addAndSave(imSimrsPelayananEntity);
                } catch (HibernateException e) {
                    logger.error("[PelayananBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PelayananGaji, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[PelayananBoImpl.saveAdd] end process <<<");
    }

    @Override
    public void saveDelete(Pelayanan bean) throws GeneralBOException {
        logger.info("[pelayananBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            List<ImSimrsPelayananEntity> cekList = new ArrayList<>();
            try {
                cekList = pelayananDao.cekData(bean.getIdPelayanan());
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
            if (cekList.size() > 0) {
                throw new GeneralBOException("Pelayanan sendang melakukan transaksi.. tidak dapat dihapus @_@ !");
            } else {
                try {
                    ImSimrsPelayananEntity imSimrsPelayananEntity = pelayananDao.getById("idPelayanan", bean.getIdPelayanan());
                    imSimrsPelayananEntity.setFlag(bean.getFlag());
                    imSimrsPelayananEntity.setAction(bean.getAction());
                    imSimrsPelayananEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSimrsPelayananEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        pelayananDao.updateAndSave(imSimrsPelayananEntity);
                    } catch (HibernateException e) {
                        logger.error("[PelayananBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data PelayananGaji, please info to your admin..." + e.getMessage());
                    }
                }catch (Exception e){
                    logger.error("[PelayananBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PelayananGaji, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[PelayananBoImpl.saveAdd] end process <<<");
    }

    @Override
    public List<Pelayanan> getJustPelayananOnly(String branchId) throws GeneralBOException {
        return pelayananDao.getJutsPelayananOnly(branchId);
    }

    @Override
    public List<Pelayanan> getByCriteria(Pelayanan bean) throws GeneralBOException {
        logger.info("[PelayananBoImpl.getByCriteria] Start >>>>>>");
        List<Pelayanan> result = new ArrayList<>();

        if (bean != null) {

            Map hsCriteria = new HashMap();

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
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
            if (bean.getNotOwnBranch() != null) {
                hsCriteria.put("not_own_branch", bean.getNotOwnBranch());
            }
            if (bean.getKodePoliVclaim() != null) {
                hsCriteria.put("kode_poli_vclaim", bean.getKodePoliVclaim());
            }

            List<ImSimrsPelayananEntity> entityList = new ArrayList<>();

            try {
                entityList = pelayananDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PelayananBoImpl.getByCriteria] Error get pelayanan data " + e.getMessage());
            }

            if (!entityList.isEmpty()) {
                Pelayanan pelayanan;
                for (ImSimrsPelayananEntity entity : entityList) {
                    pelayanan = new Pelayanan();
                    pelayanan.setIdPelayanan(entity.getIdPelayanan());
                    pelayanan.setAction(entity.getAction());
                    pelayanan.setFlag(entity.getFlag());
                    pelayanan.setCreatedDate(entity.getCreatedDate());
                    pelayanan.setStCreatedDate(entity.getCreatedDate().toString());
                    pelayanan.setCreatedWho(entity.getCreatedWho());
                    pelayanan.setStLastUpdate(entity.getLastUpdate().toString());
                    pelayanan.setLastUpdate(entity.getLastUpdate());
                    pelayanan.setLastUpdateWho(entity.getLastUpdateWho());
                    pelayanan.setIdHeaderPelayanan(entity.getIdHeaderPelayanan());

                    ImSimrsHeaderPelayananEntity headerPelayananEntity = headerPelayananDao.getById("idHeaderPelayanan", entity.getIdHeaderPelayanan());
                    if (headerPelayananEntity != null) {
                        pelayanan.setNamaPelayanan(headerPelayananEntity.getNamaPelayanan());
                        pelayanan.setTipePelayanan(headerPelayananEntity.getTipePelayanan());
                        pelayanan.setKategoriPelayanan(headerPelayananEntity.getKategoriPelayanan());
                        pelayanan.setDivisiId(headerPelayananEntity.getDivisiId());
                        pelayanan.setKodePoliVclaim(headerPelayananEntity.getKodeVclaim());
                        pelayanan.setIsVaksin(headerPelayananEntity.getIsVaksin());
                    }

                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    if (entity.getBranchId() != null) {
                        Branch branch = new Branch();
                        BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
                        branch.setBranchId(entity.getBranchId());
                        branch.setFlag("Y");
                        List<Branch> branches = branchBo.getByCriteria(branch);
                        if (branches.size() > 0) {
                            String branchName = branches.get(0).getBranchName();
                            pelayanan.setBranchName(branchName);
                        }
                    }
                    result.add(pelayanan);
                }
            }
        }

        logger.info("[PelayananBoImpl.getByCriteria] End <<<<<<");
        return result;
    }

    @Override
    public Pelayanan getPelayananById(String id) throws GeneralBOException {
        Pelayanan result = new Pelayanan();
        logger.info("[PelayananBoImpl.getByCriteria] Start >>>>>>");
        try {
            Pelayanan pelayanan = new Pelayanan();
            pelayanan.setIdPelayanan(id);
            result = pelayananDao.getObjectPelayanan(pelayanan);
        } catch (HibernateException e) {
            throw new GeneralBOException("error" + e.getMessage());
        }
        logger.info("[PelayananBoImpl.getByCriteria] End <<<<<<");
        return result;
    }

    @Override
    public ImSimrsPelayananEntity getPelayananByDivisiId(String id, String branchId) throws GeneralBOException {
        ImSimrsPelayananEntity result = new ImSimrsPelayananEntity();
        Map criteria = new HashMap();
        criteria.put("branch_id", branchId);
        criteria.put("divisi_id", id);
        criteria.put("flag", "Y");

        List<ImSimrsPelayananEntity> pelayananEntityList = pelayananDao.getByCriteria(criteria);

        for (ImSimrsPelayananEntity pelayananEntity : pelayananEntityList) {
            result = pelayananEntity;
        }
        return result;
    }

    @Override
    public List<Pelayanan> getListPelayananPaketPeriksa(String branch) throws GeneralBOException {
        List<Pelayanan> pelayananList = new ArrayList<>();
        if (branch != null) {
            try {
                pelayananList = pelayananDao.getListPelayananPaket(branch);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return pelayananList;
    }

    public String cekStatus(String namaPelayanan) throws GeneralBOException {
        String status = "";
        List<ImSimrsPelayananEntity> entities = new ArrayList<>();
        try {
            entities = pelayananDao.getDataPelayanan(namaPelayanan);
        } catch (HibernateException e) {
            logger.error("[PelayananBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size() > 0) {
            status = "exist";
        } else {
            status = "notExits";
        }
        return status;
    }

    public String cekBeforeDelete(String idPelayanan) throws GeneralBOException {
        String status = "";
        List<ImSimrsPelayananEntity> entities = new ArrayList<>();
        try {
            entities = pelayananDao.cekData(idPelayanan);
        } catch (HibernateException e) {
            logger.error("[PelayananBoImpl.cekBeforeDelete] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size() > 0) {
            status = "exist";
        } else {
            status = "notExits";
        }
        return status;
    }

    @Override
    public List<Pelayanan> getListPelayananTelemedic(String branchId) {

        List<Pelayanan> pelayananList = new ArrayList<>();

        try {
           pelayananList = pelayananDao.getListPelayananTelemedic(branchId);
        } catch (GeneralBOException e) {
            logger.error("[PelayananBoImpl.getListPelayananTelemedic] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        return pelayananList;
    }

    @Override
    public List<Pelayanan> getListPelayananFarmasi(String branchId) throws GeneralBOException {
        return pelayananDao.getListPelayananFarmasi(branchId);
    }

    @Override
    public List<Pelayanan> getListPelayananWithLab(String tipe) throws GeneralBOException {
        return pelayananDao.getListPelayananWithLab(tipe);
    }

    @Override
    public List<ImSimrsPelayananEntity> getByCriteria(Map criteria) throws GeneralBOException {
        return pelayananDao.getByCriteria(criteria);
    }

    @Override
    public List<ImSimrsPelayananEntity> getPelayananByBranch(String branchId) throws GeneralBOException {
        return pelayananDao.getPelayananByBranch(branchId);
    }

    @Override
    public List<Pelayanan> getJustPelayananByBranch(String branchId) throws GeneralBOException {
        return pelayananDao.getJutsPelayananByBranch(branchId);
    }

    @Override
    public List<Pelayanan> getJustPelayananAndLab(String branchId) throws GeneralBOException {
        return pelayananDao.getJutsPelayananAndLab(branchId);
    }

    @Override
    public Pelayanan getObjectPelayanan(Pelayanan bean) throws GeneralBOException {
        return pelayananDao.getObjectPelayanan(bean);
    }

    @Override
    public List<Pelayanan> getListObjectPelayanan(Pelayanan bean) throws GeneralBOException {
        return pelayananDao.getListObjectPelayanan(bean);
    }
}
