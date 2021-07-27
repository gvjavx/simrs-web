package com.neurix.hris.master.dokterKso.bo.Impl;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.dokterKso.bo.DokterKsoBo;
import com.neurix.hris.master.dokterKso.dao.DokterKsoDao;
import com.neurix.hris.master.dokterKso.model.DokterKso;
import com.neurix.hris.master.dokterKso.model.ImSimrsDokterKso;
import com.neurix.hris.master.dokterKsoTindakan.bo.DokterKsoTindakanBo;
import com.neurix.hris.master.dokterKsoTindakan.dao.DokterKsoTindakanDao;
import com.neurix.hris.master.dokterKsoTindakan.model.DokterKsoTindakan;
import com.neurix.hris.master.dokterKsoTindakan.model.ImSimrsDokterKsoTindakan;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.DokterPelayanan;
import com.neurix.simrs.master.dokter.dao.DokterPelayananDao;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterPelayananEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DokterKsoBoImpl implements DokterKsoBo {
    protected static transient Logger logger = Logger.getLogger(DokterKsoBoImpl.class);
    DokterKsoDao dokterKsoDao;
    DokterKsoTindakanDao dokterKsoTindakanDao;
    DokterPelayananDao dokterPelayananDao;
    PelayananDao pelayananDao;
    TindakanDao tindakanDao;
    PersonilPositionDao personilPositionDao;
    private BranchDao branchDao;
    private PositionDao positionDao;

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setDokterPelayananDao(DokterPelayananDao dokterPelayananDao) {
        this.dokterPelayananDao = dokterPelayananDao;
    }

    public DokterKsoTindakanDao getDokterKsoTindakanDao() {
        return dokterKsoTindakanDao;
    }

    public void setDokterKsoTindakanDao(DokterKsoTindakanDao dokterKsoTindakanDao) {
        this.dokterKsoTindakanDao = dokterKsoTindakanDao;
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

    public DokterKsoDao getDokterKsoDao() {
        return dokterKsoDao;
    }

    public void setDokterKsoDao(DokterKsoDao dokterKsoDao) {
        this.dokterKsoDao = dokterKsoDao;
    }

    @Override
    public void saveDelete(DokterKso bean) throws GeneralBOException {
        logger.info("[saveDelete.DokterKsoBoImpl] start process >>>");

        if (bean != null) {
            String idDokter = bean.getDokterKsoId();
            ImSimrsDokterKso entity1 = null;
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<DokterKsoTindakan> listOfResult = (List<DokterKsoTindakan>) session.getAttribute("listOfResultDokterKsoTindakan");
            try {
                // Get data from database by ID
                entity1 = dokterKsoDao.getById("dokterKsoId", idDokter);
            } catch (HibernateException e) {
                logger.error("[DokterBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Dokter by Kode Dokter, please inform to your admin...," + e.getMessage());
            }

            if (entity1 != null) {

                // Modify from bean to entity serializable
                entity1.setDokterKsoId(bean.getDokterKsoId());
                entity1.setFlag(bean.getFlag());
                entity1.setAction(bean.getAction());
                entity1.setLastUpdateWho(bean.getLastUpdateWho());
                entity1.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    dokterKsoDao.updateAndSave(entity1);
                } catch (HibernateException e) {
                    logger.error("[DokterKsoBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Dokter, please info to your admin..." + e.getMessage());
                }

                if (listOfResult != null) {
                    for (int i = 0; i < listOfResult.size(); i++) {
                        if (listOfResult.get(i).getDokterKsoTindakanId() != null) {
                            ImSimrsDokterKsoTindakan entity = null;
                            String dokterKsoTindakanId = listOfResult.get(i).getDokterKsoTindakanId();

                            try {
                                entity = dokterKsoTindakanDao.getById("dokterKsoTindakanId", dokterKsoTindakanId);
                            } catch (HibernateException e) {
                                logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data Dokter KSO Tindakan by ID reportKonsolId, please inform to your admin...," + e.getMessage());
                            }

                            if (entity != null) {
                                entity.setDokterKsoTindakanId(dokterKsoTindakanId);
                                entity.setFlag(listOfResult.get(i).getFlag());
                                entity.setAction(bean.getAction());
                                entity.setLastUpdateWho(bean.getLastUpdateWho());
                                entity.setLastUpdate(bean.getLastUpdate());

                                try {
                                    dokterKsoTindakanDao.updateAndSave(entity);
                                } catch (HibernateException e) {
                                    logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when saving new data Dokter Kso Tindakan, please info to your admin..." + e.getMessage());
                                }
                            }
                        } else {
                            ImSimrsDokterKsoTindakan entity = new ImSimrsDokterKsoTindakan();
                            String dokterKsoTindakanId = dokterKsoDao.getNextDokterKsoTindakanId();

                            entity.setDokterKsoTindakanId(dokterKsoTindakanId);
                            entity.setFlag(bean.getFlag());
                            entity.setAction(bean.getAction());
                            entity.setLastUpdateWho(bean.getLastUpdateWho());
                            entity.setLastUpdate(bean.getLastUpdate());

                            try {
                                dokterKsoTindakanDao.addAndSave(entity);
                            } catch (HibernateException e) {
                                logger.error("[DokterKsoTindakanBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data Dokter Kso Tindakan, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                } else {
                    logger.error("[DokterKsoBoImpl.saveDelete] Error, not found data Dokter KSO Tindakan with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Dokter KSO Tindakan with request id, please check again your data ...");
                }
            } else {
                logger.error("[DokterKsoBoImpl.saveDelete] Error, not found data Dokter with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Dokter with request id, please check again your data ...");
            }
        }
        logger.info("[DokterKsoBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(DokterKso bean) throws GeneralBOException {
        logger.info("[DokterBoImpl.saveEdit] start process >>>");
        if (bean != null) {
            String historyId = "";
            String dokterKsoId = bean.getDokterKsoId();
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<DokterKsoTindakan> listOfResult = (List<DokterKsoTindakan>) session.getAttribute("listOfResultDokterKsoTindakanEdit");

            ImSimrsDokterKso imSimrsDokterKso = null;
            try {
                // Get data from database by ID
                imSimrsDokterKso = dokterKsoDao.getById("dokterKsoId", dokterKsoId);
            } catch (HibernateException e) {
                logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Dokter by IdDokter, please inform to your admin...," + e.getMessage());
            }

            String kode = imSimrsDokterKso.getKodering();
            String[] arrOfStr = kode.split("\\.");
            String seqKodering = arrOfStr[4];

            Map map = new HashMap<>();
            map.put("position_id", bean.getPositionId());
            String koderingPosition;
            try {
                koderingPosition = positionDao.getKodringPosition(map);
            } catch (HibernateException e) {
                logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Dokter by IdDokter, please inform to your admin...," + e.getMessage());
            }

//            String branchId = CommonUtil.userBranchLogin();
            String branchId = bean.getBranchId();
            Map map1 = new HashMap<>();
            map1.put("branch_id", branchId);
            String koderingBranch;
            try {
                koderingBranch = branchDao.getKodringBranches(map1);
            } catch (HibernateException e) {
                logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Dokter by IdDokter, please inform to your admin...," + e.getMessage());
            }

            String kodering = koderingBranch + "." + koderingPosition + "." + seqKodering;

            if (imSimrsDokterKso != null) {
                imSimrsDokterKso.setDokterKsoId(bean.getDokterKsoId());
                imSimrsDokterKso.setNip(bean.getNip());
                imSimrsDokterKso.setJenisKso(bean.getJenisKso());
                imSimrsDokterKso.setMasterId(bean.getMasterId());
                imSimrsDokterKso.setTarifIna(bean.getTarifIna());
                imSimrsDokterKso.setPersenKso(bean.getPersenKso());
                imSimrsDokterKso.setPersenKs(bean.getPersenKs());
                imSimrsDokterKso.setBranchId(bean.getBranchId());
                imSimrsDokterKso.setPositionId(bean.getPositionId());
                imSimrsDokterKso.setKodering(kodering);
                imSimrsDokterKso.setFlag(bean.getFlag());
                imSimrsDokterKso.setAction(bean.getAction());
                imSimrsDokterKso.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsDokterKso.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    dokterKsoDao.updateAndSave(imSimrsDokterKso);
                    //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Dokter, please info to your admin..." + e.getMessage());
                }

                if ("tindakan".equalsIgnoreCase(bean.getJenisKso())) {
                    if (listOfResult != null && listOfResult.size() > 0) {
                        for (int i = 0; i < listOfResult.size(); i++) {
                            if (listOfResult.get(i).getDokterKsoTindakanId() != null) {
                                ImSimrsDokterKsoTindakan entity = null;
                                String dokterKsoTindakanId = listOfResult.get(i).getDokterKsoTindakanId();

                                try {
                                    entity = dokterKsoTindakanDao.getById("dokterKsoTindakanId", dokterKsoTindakanId);
                                } catch (HibernateException e) {
                                    logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data Dokter KSO Tindakan by ID reportKonsolId, please inform to your admin...," + e.getMessage());
                                }

                                if (entity != null) {
                                    entity.setDokterKsoTindakanId(dokterKsoTindakanId);
                                    entity.setDokterKsoId(bean.getDokterKsoId());
                                    entity.setTindakanId(listOfResult.get(i).getTindakanId());
                                    entity.setPersenKso(listOfResult.get(i).getPersenKso());
                                    entity.setFlag(listOfResult.get(i).getFlag());
                                    entity.setAction(bean.getAction());
                                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                                    entity.setLastUpdate(bean.getLastUpdate());

                                    try {
                                        dokterKsoTindakanDao.updateAndSave(entity);
                                    } catch (HibernateException e) {
                                        logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when saving new data Dokter Kso Tindakan, please info to your admin..." + e.getMessage());
                                    }
                                }
                            } else {
                                ImSimrsDokterKsoTindakan entity = new ImSimrsDokterKsoTindakan();
                                String dokterKsoTindakanId = dokterKsoDao.getNextDokterKsoTindakanId();

                                entity.setDokterKsoTindakanId(dokterKsoTindakanId);
                                entity.setDokterKsoId(bean.getDokterKsoId());
                                entity.setTindakanId(listOfResult.get(i).getTindakanId());
                                entity.setPersenKso(listOfResult.get(i).getPersenKso());
                                if (listOfResult.get(i).getFlag() != null)
                                    entity.setFlag(listOfResult.get(i).getFlag());
                                else
                                    entity.setFlag(bean.getFlag());
                                entity.setAction(bean.getAction());
                                entity.setCreatedWho(bean.getCreatedWho());
                                entity.setCreatedDate(bean.getCreatedDate());
                                entity.setLastUpdateWho(bean.getLastUpdateWho());
                                entity.setLastUpdate(bean.getLastUpdate());

                                try {
                                    dokterKsoTindakanDao.addAndSave(entity);
                                } catch (HibernateException e) {
                                    logger.error("[DokterKsoTindakanBoImpl.saveAdd] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when saving new data Dokter Kso Tindakan, please info to your admin..." + e.getMessage());
                                }
                            }
                        }
                    } else {
                        throw new GeneralBOException("Maaf, Data Dokter Kso Tindakan tidak ada. Harus ada Tindakan untuk Jenis KSO Tindakan.");
                    }
                } else {

                    Map criteria = new HashMap();
                    criteria.put("dokter_kso_id", bean.getDokterKsoId());
                    criteria.put("flag", "Y");
                    List<ImSimrsDokterKsoTindakan> dokterKsoTindakanList = new ArrayList<>();
                    try {
                        dokterKsoTindakanList = dokterKsoTindakanDao.getByCriteria(criteria);
                    } catch (HibernateException e) {
                        logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data Dokter KSO Tindakan by ID reportKonsolId, please inform to your admin...," + e.getMessage());
                    }

                    if (dokterKsoTindakanList.size() != 0) {
                        for (ImSimrsDokterKsoTindakan ksoTindakan : dokterKsoTindakanList) {
                            ksoTindakan.setFlag("N");
                            ksoTindakan.setAction("U");
                            ksoTindakan.setLastUpdateWho(bean.getLastUpdateWho());
                            ksoTindakan.setLastUpdate(bean.getLastUpdate());

                            try {
                                dokterKsoTindakanDao.updateAndSave(ksoTindakan);
                            } catch (HibernateException e) {
                                logger.error("[DokterKsoBoImpl.saveEdit] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data Dokter Kso Tindakan, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                }
            }
        } else {
            logger.error("[DokterKsoBoImpl.saveEdit] Error, not found data Dokter with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data Dokter with request id, please check again your data ...");
        }

        logger.info("[DokterKsoBoImpl.saveEdit] end process <<<");
    }

    @Override
    public DokterKso saveAdd(DokterKso bean) throws GeneralBOException {
        logger.info("[DokterKsoBoImpl.BoImpl.saveAdd] start process >>>");
        if (bean != null) {
            String status = cekStatus(bean.getNip(), bean.getJenisKso(), bean.getMasterId());
            String dokterKsoId, seqKodering, dokterKsoTindakanId;
            if (!status.equalsIgnoreCase("exist")) {
                HttpSession session = ServletActionContext.getRequest().getSession();
                List<DokterKsoTindakan> listOfResult = (List<DokterKsoTindakan>) session.getAttribute("listOfResultDokterKsoTindakan");
//                if (listOfResult != null){
                // Generating ID, get from postgre sequence
                try {
                    dokterKsoId = dokterKsoDao.getNextDokterKsoId();
                } catch (HibernateException e) {
                    logger.error("[DokterKsoBoImpl.BoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Problem when retrieving Next Dokter KSO ID, " + e.getMessage());
                }
                try {
                    seqKodering = dokterKsoDao.getNextKodering();
                } catch (HibernateException e) {
                    logger.error("[DokterKsoBoImpl.BoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Problem when retrieving Next Kodering, " + e.getMessage());
                }

                Map map = new HashMap<>();
                map.put("position_id", bean.getPositionId());
                String koderingPosition;
                try {
                    koderingPosition = positionDao.getKodringPosition(map);
                } catch (HibernateException e) {
                    logger.error("[DokterKsoBoImpl.BoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Problem when retrieving Kodering Position, " + e.getMessage());
                }

//                String branchId = CommonUtil.userBranchLogin();
                String branchId = bean.getBranchId();
                Map map1 = new HashMap<>();
                map1.put("branch_id", branchId);
                String koderingBranch;
                try {
                    koderingBranch = branchDao.getKodringBranches(map1);
                } catch (HibernateException e) {
                    logger.error("[DokterKsoBoImpl.BoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Problem when retrieving Kodering Branches, " + e.getMessage());
                }

                String kodering = koderingBranch + "." + koderingPosition + "." + seqKodering;

                // creating object entity serializable
                ImSimrsDokterKso entity = new ImSimrsDokterKso();
                entity.setDokterKsoId(dokterKsoId);
                entity.setNip(bean.getNip());
                entity.setJenisKso(bean.getJenisKso());
                entity.setMasterId(bean.getMasterId());
                entity.setTarifIna(bean.getTarifIna());
                entity.setPersenKso(bean.getPersenKso());
                entity.setPersenKs(bean.getPersenKs());
                entity.setBranchId(bean.getBranchId());
                entity.setPositionId(bean.getPositionId());
                entity.setKodering(kodering);
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    dokterKsoDao.addAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[DokterKsoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Tindakan, please info to your admin..." + e.getMessage());
                }

                if ("tindakan".equalsIgnoreCase(bean.getJenisKso())) {
                    if (listOfResult != null && listOfResult.size() > 0) {
                        for (DokterKsoTindakan ksoTindakanEntity : listOfResult) {
                            ImSimrsDokterKsoTindakan ksoTindakan = new ImSimrsDokterKsoTindakan();
                            try {
                                dokterKsoTindakanId = dokterKsoDao.getNextDokterKsoTindakanId();
                            } catch (HibernateException e) {
                                logger.error("[DokterKsoBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data Tindakan, please info to your admin..." + e.getMessage());
                            }
                            ksoTindakan.setDokterKsoTindakanId(dokterKsoTindakanId);
                            ksoTindakan.setDokterKsoId(dokterKsoId);
                            ksoTindakan.setTindakanId(ksoTindakanEntity.getTindakanId());
                            ksoTindakan.setPersenKso(ksoTindakanEntity.getPersenKso());
                            ksoTindakan.setFlag(bean.getFlag());
                            ksoTindakan.setAction(bean.getAction());
                            ksoTindakan.setCreatedWho(bean.getCreatedWho());
                            ksoTindakan.setLastUpdateWho(bean.getLastUpdateWho());
                            ksoTindakan.setCreatedDate(bean.getCreatedDate());
                            ksoTindakan.setLastUpdate(bean.getLastUpdate());

                            try {
                                // insert into database
                                dokterKsoTindakanDao.addAndSave(ksoTindakan);
                            } catch (HibernateException e) {
                                logger.error("[DokterKsoBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data Tindakan, please info to your admin..." + e.getMessage());
                            }
                        }
                    } else {
                        throw new GeneralBOException("Maaf, Data Dokter Kso Tindakan tidak ada. Harus ada Tindakan untuk Jenis KSO Tindakan.");
                    }
                }
//                }else {
//                    throw new GeneralBOException("Maaf Data Dokter Kso Tindakan tidak ada");
//                }
            } else {
                throw new GeneralBOException("Maaf, Data dengan ID Dokter, Jenis KSO, dan Master tersebut sudah ada.");
            }
        }

        logger.info("[DokterKsoBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<DokterKso> getByCriteria(DokterKso bean) throws GeneralBOException {
        logger.info("[PelayananBoImpl.getByCriteria] Start >>>>>>");
        List<DokterKso> result = new ArrayList<>();

        if (bean != null) {

            Map hsCriteria = new HashMap();

            if (bean.getDokterKsoId() != null && !"".equalsIgnoreCase(bean.getDokterKsoId())) {
                hsCriteria.put("dokter_kso_id", bean.getDokterKsoId());
            }
            if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                hsCriteria.put("nip", bean.getNip());
            }

            if (bean.getMasterId() != null && !"".equalsIgnoreCase(bean.getMasterId())) {
                hsCriteria.put("master_id", bean.getMasterId());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            if (bean.getJenisKso() != null && !"".equalsIgnoreCase(bean.getJenisKso())) {
                hsCriteria.put("jenis_kso", bean.getBranchId());
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

            List<ImSimrsDokterKso> entityList = new ArrayList<>();

            try {
                entityList = dokterKsoDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PelayananBoImpl.getByCriteria] Error get pelayanan data " + e.getMessage());
            }

            if (!entityList.isEmpty()) {
                DokterKso dokterKso;
                for (ImSimrsDokterKso entity : entityList) {
                    dokterKso = new DokterKso();
                    dokterKso.setDokterKsoId(entity.getDokterKsoId());
                    dokterKso.setNip(entity.getNip());
                    dokterKso.setJenisKso(entity.getJenisKso());
                    dokterKso.setMasterId(entity.getMasterId());
                    dokterKso.setTarifIna(entity.getTarifIna());
                    dokterKso.setPersenKso(entity.getPersenKso());
                    dokterKso.setPersenKs(entity.getPersenKs());
                    dokterKso.setBranchId(entity.getBranchId());
                    dokterKso.setPositionId(entity.getPositionId());
                    dokterKso.setKodering(entity.getKodering());
                    dokterKso.setAction(entity.getAction());
                    dokterKso.setFlag(entity.getFlag());
                    dokterKso.setCreatedDate(entity.getCreatedDate());
                    dokterKso.setStCreatedDate(entity.getCreatedDate().toString());
                    dokterKso.setCreatedWho(entity.getCreatedWho());
                    dokterKso.setStLastUpdate(entity.getLastUpdate().toString());
                    dokterKso.setLastUpdate(entity.getLastUpdate());
                    dokterKso.setLastUpdateWho(entity.getLastUpdateWho());

                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    if (entity.getBranchId() != null) {
                        Branch branch = new Branch();
                        BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
                        branch.setBranchId(entity.getBranchId());
                        branch.setFlag("Y");
                        List<Branch> branches = branchBo.getByCriteria(branch);
                        String branchName = branches.get(0).getBranchName();
                        dokterKso.setBranchName(branchName);
                    }
                    if (entity.getNip() != null) {
                        Dokter dokter = new Dokter();
                        DokterBo dokterBo = (DokterBo) context.getBean("dokterBoProxy");
                        dokter.setIdDokter(entity.getNip());
                        dokter.setFlag("Y");
                        List<Dokter> dokters = dokterBo.getByCriteria(dokter);
                        // Fahmi 2021-07-27, Tambah pengecheckkan untuk data yang kosong.
                        if(null!=dokters && dokters.size() > 0)
                        {
                            String dokterName = dokters.get(0).getNamaDokter();
                            dokterKso.setNamaDokter(dokterName);
                        }

                    }
//                    if (entity.getDokterKsoId() != null){
//                        DokterKsoTindakan dokterKsoTindakan = new DokterKsoTindakan();
//                        DokterKsoTindakanBo dokterKsoTindakanBo = (DokterKsoTindakanBo) context.getBean("dokterKsoTindakanBoProxy");
//                        dokterKsoTindakan.setDokterKsoId(entity.getDokterKsoId());
//                        dokterKsoTindakan.setFlag("Y");
//                        List<DokterKsoTindakan> dokterKsoTindakans = dokterKsoTindakanBo.getByCriteria(dokterKsoTindakan);
//                        String dokterKsoTindakanId = dokterKsoTindakans.get(0).getDokterKsoTindakanId();
//                        String tindakanId = dokterKsoTindakans.get(0).getTindakanId();
//                        BigDecimal persenKsoTindakan = dokterKsoTindakans.get(0).getPersenKso();
//                        String tindakanName = dokterKsoTindakans.get(0).getTindakanName();
//                        dokterKso.setDokterKsoTindakanId(dokterKsoTindakanId);
//                        dokterKso.setTindakan(tindakanId);
//                        dokterKso.setPersenKsoTindakan(persenKsoTindakan);
//                        dokterKso.setTindakanName(tindakanName);
//                    }

                    result.add(dokterKso);
                }
            }
        }

        logger.info("[PelayananBoImpl.getByCriteria] End <<<<<<");
        return result;
    }

    @Override
    public List<DokterKso> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    private String cekStatus(String nip, String jenis, String masterId) throws GeneralBOException {
        String status = "";
        List<ImSimrsDokterKso> entities = new ArrayList<>();
        try {
            entities = dokterKsoDao.getDataDokterKso(nip, jenis, masterId);
        } catch (HibernateException e) {
            logger.error("[DokterBoImpl.cekStatus] Error, " + e.getMessage());
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
    public List<Pelayanan> getPelayananDokter(String idDokter) throws GeneralBOException {
        logger.info("[DokterKsoBoImpl.getPelayananDokter] start process >>>");
        List<Pelayanan> listOfResult = new ArrayList<>();
        List<ImSimrsDokterPelayananEntity> dokterPelayananEntities = new ArrayList<>();

        HashMap hsCriteria = new HashMap<>();
        hsCriteria.put("id_dokter", idDokter);
        hsCriteria.put("flag", "Y");

        try {
            dokterPelayananEntities = dokterPelayananDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[DokterKsoBoImpl.getPelayananDokter] Error, " + e.getMessage());
            throw new GeneralBOException("Problem when retrieving Dokter Pelayanan using Criteria, " + e.getMessage());
        }

        for (ImSimrsDokterPelayananEntity dokterPelayanan : dokterPelayananEntities) {
            Pelayanan pelayanan;
            try {
                pelayanan = pelayananDao.getPelayananById("idPelayanan", dokterPelayanan.getIdPelayanan());
            } catch (HibernateException e) {
                logger.error("[DokterKsoBoImpl.getPelayananDokter] Error, " + e.getMessage());
                throw new GeneralBOException("Problem when retrieving Pelayanan By ID, " + e.getMessage());
            }
            listOfResult.add(pelayanan);
        }

        logger.info("[DokterKsoBoImpl.getPelayananDokter] end process <<<");
        return listOfResult;
    }

    @Override
    public List<Tindakan> getTindakanPelayanan(String idPelayanan, String idDokter) throws GeneralBOException {
        List<Tindakan> tindakanList = new ArrayList<>();

        ItPersonilPositionEntity personil;

        try {
            personil = personilPositionDao.getById("nip", idDokter);
        } catch (HibernateException e) {
            logger.error("[DokterKsoBoImpl.getTindakanPelayanan] Error, " + e.getMessage());
            throw new GeneralBOException("Problem when retrieving Personil Position ID, " + e.getMessage());
        }

        try {
            tindakanList = tindakanDao.getTindakanPelayanan(idPelayanan, personil.getBranchId());
        } catch (HibernateException e) {
            logger.error("[DokterKsoBoImpl.getTindakanPelayanan] Error, " + e.getMessage());
            throw new GeneralBOException("Problem when retrieving Tindakan by Pelayanan ID, " + e.getMessage());
        }

        return tindakanList;
    }
}
