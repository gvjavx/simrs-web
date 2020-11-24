package com.neurix.simrs.master.rekananops.bo.impl;

import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.rekananops.bo.RekananOpsBo;
import com.neurix.simrs.master.rekananops.dao.DetailRekananOpsDao;
import com.neurix.simrs.master.rekananops.dao.RekananOpsDao;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.ImSimrsDetailRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RekananOpsBoImpl implements RekananOpsBo {
    protected static transient Logger logger = Logger.getLogger(RekananOpsBoImpl.class);
    private RekananOpsDao rekananOpsDao;
    private DetailRekananOpsDao detailRekananOpsDao;
    private BranchDao branchDao;
    private MasterDao masterDao;

    public MasterDao getMasterDao() {
        return masterDao;
    }

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public void setDetailRekananOpsDao(DetailRekananOpsDao detailRekananOpsDao) {
        this.detailRekananOpsDao = detailRekananOpsDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public List<RekananOps> getByCriteria(RekananOps bean) throws GeneralBOException {
        logger.info("[RekananOpsBoImpl.getByCriteria] Start >>>>>>");
        List<RekananOps> listOfResultRekananOps = new ArrayList<>();
        if(bean != null){
            Map hsCriteria = new HashMap();
            if (bean.getIdRekananOps() != null && !"".equalsIgnoreCase(bean.getIdRekananOps())) {
                hsCriteria.put("id_rekanan_ops", bean.getIdRekananOps());
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

            List<ImSimrsRekananOpsEntity> imSimrsRekananOpsEntities = null;
            try {
                imSimrsRekananOpsEntities = rekananOpsDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
            }

            if (imSimrsRekananOpsEntities.size() > 0) {
                for (ImSimrsRekananOpsEntity listEntity : imSimrsRekananOpsEntities) {
                    RekananOps rekananOps = new RekananOps();
                    rekananOps.setIdRekananOps(listEntity.getIdRekananOps());
                    rekananOps.setNomorMaster(listEntity.getNomorMaster());
                    rekananOps.setNamaRekanan(listEntity.getNamaRekanan());

                    rekananOps.setAction(listEntity.getAction());
                    rekananOps.setFlag(listEntity.getFlag());
                    rekananOps.setCreatedDate(listEntity.getCreatedDate());
                    rekananOps.setCreatedWho(listEntity.getCreatedWho());
                    rekananOps.setLastUpdate(listEntity.getLastUpdate());
                    rekananOps.setLastUpdateWho(listEntity.getLastUpdateWho());
                    rekananOps.setTipe(listEntity.getTipe());
                    listOfResultRekananOps.add(rekananOps);
                }
            }
        }
        logger.info("[RekananOpsBoImpl.getByCriteria] End <<<<<<");
        return listOfResultRekananOps;

    }

    @Override
    public List<DetailRekananOps> getSearchByCriteria(RekananOps bean) throws GeneralBOException {
        return null;
    }
//    @Override
//    public List<DetailRekananOps> getSearchByCriteria(RekananOps bean) throws GeneralBOException {
//        logger.info("[RekananOpsBoImpl.getByCriteria] Start >>>>>>");
//        List<DetailRekananOps> listOfResultRekananOps = new ArrayList<>();
//        if(bean != null) {
//            Map hsCriteria = new HashMap();
//            if (bean.getIdRekananOps() != null && !"".equalsIgnoreCase(bean.getIdRekananOps())) {
//                hsCriteria.put("id_rekanan_ops", bean.getIdRekananOps());
//            }
//            if (bean.getNomorMaster() != null && !"".equalsIgnoreCase(bean.getNomorMaster())) {
//                hsCriteria.put("nomor_master", bean.getNomorMaster());
//            }
//            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
//                if ("N".equalsIgnoreCase(bean.getFlag())) {
//                    hsCriteria.put("flag", "N");
//                } else {
//                    hsCriteria.put("flag", bean.getFlag());
//                }
//            } else {
//                hsCriteria.put("flag", "Y");
//            }
//            if (bean.getTipe() != null && !"".equalsIgnoreCase(bean.getTipe())) {
//                if ("no".equalsIgnoreCase(bean.getFlag())) {
//                    hsCriteria.put("tipe", "no");
//                } else {
//                    hsCriteria.put("tipe", bean.getFlag());
//                }
//            } else {
//                hsCriteria.put("tipe", "ptpn");
//            }
//
//            List<ImSimrsDetailRekananOpsEntity> listOfDetail = null;
//            try {
//                listOfDetail = detailRekananOpsDao.getByCriteria(hsCriteria);
//            } catch (HibernateException e) {
//                logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
//            }
//
//            if (listOfDetail.size() > 0){
//                for (ImSimrsDetailRekananOpsEntity detail :listOfDetail){
//                    DetailRekananOps detailRekananOps = new DetailRekananOps();
//                    detailRekananOps.setIdDetailRekananOps(detail.getIdDetailRekananOps());
//                    detailRekananOps.setIdRekananOps(detail.getIdRekananOps());
//                    detailRekananOps.setDiskon(detail.getDiskon());
//                    detailRekananOps.setIsBpjs(detail.getIsBpjs());
//                    detailRekananOps.setBranchId(detail.getBranchId());
//                    detailRekananOps.setCreatedWho(detail.getCreatedWho());
//                    detailRekananOps.setCreatedDate(detail.getCreatedDate());
//                    detailRekananOps.setLastUpdate(detail.getLastUpdate());
//                    detailRekananOps.setLastUpdateWho(detail.getLastUpdateWho());
//                    detailRekananOps.setFlag(detail.getFlag());
//                    detailRekananOps.setAction(detail.getAction());
//
//                    // mengambil dari RekananOps
//                    hsCriteria = new HashMap();
//                    hsCriteria.put("id_rekanan_ops", detail.getIdRekananOps());
//                    if (bean.getNomorMaster() != null && !"".equalsIgnoreCase(bean.getNomorMaster())) {
//                        hsCriteria.put("nomor_master", bean.getNomorMaster());
//                    }
//                    List<ImSimrsRekananOpsEntity> listOfHead = null ;
//                    try {
//                        listOfHead = rekananOpsDao.getByCriteria(hsCriteria);
//                    } catch (HibernateException e) {
//                        logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
//                    }
//                    if(listOfHead.size()>0){
//                        for(ImSimrsRekananOpsEntity head : listOfHead){
//                            detailRekananOps.setNamaRekanan(head.getNamaRekanan());
//                            detailRekananOps.setNomorMaster(head.getNomorMaster());
//                            detailRekananOps.setTipe(head.getTipe());
//                        }
//                    }
//
//                    // END
//
//                    // mengambil data dari branch
//                    hsCriteria = new HashMap();
//                    hsCriteria.put("branch_id", detail.getBranchId());
//                    hsCriteria.put("flag", "Y");
//
//                    List<ImBranches> listOfBranch = null;
//                    try {
//                        listOfBranch = branchDao.getByCriteria(hsCriteria);
//                    } catch (HibernateException e) {
//                        logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
//                    }
//                    if(listOfBranch.size()>0){
//                        for(ImBranches branch : listOfBranch){
//                            detailRekananOps.setBranchName(branch.getBranchName());
//                        }
//                    }
//
//                    listOfResultRekananOps.add(detailRekananOps);
//                }
//            }
//        }
//        logger.info("[RekananOpsBoImpl.getByCriteria] End <<<<<<");
//        return listOfResultRekananOps;
//    }

//    @Override
//    public CrudResponse saveAdd(RekananOps bean) throws GeneralBOException {
//        return null;
//    }

    @Override
    public CrudResponse saveAdd(RekananOps bean) throws GeneralBOException{
        if (bean!=null) {
            List<ImSimrsRekananOpsEntity> cekList = new ArrayList<>();
            try {
                cekList = rekananOpsDao.getRekananOps(bean.getNomorMaster());
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
            if(cekList.size() > 0){
                throw new GeneralBOException("Nama Asuransi sudah ada...!");
            }else{
                String rekananId;
                try {
                    // Generating ID, get from postgre sequence
                    rekananId = rekananOpsDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[rekananOpsDaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence Asuransi id, please info to your admin..." + e.getMessage());
                }
                // mengambil nama master / nama asuransi dari masterdao berdasarkan no master;
                ImMasterEntity masterEntity = new ImMasterEntity();
                try {
                    masterEntity = masterDao.getById("primaryKey.nomorMaster", bean.getNomorMaster());
                }catch (HibernateException e){
                    logger.error("Found Error when search nomorMaster "+e.getMessage());
                }
                // creating object entity serializable
                ImSimrsRekananOpsEntity imSimrsRekananOpsEntity = new ImSimrsRekananOpsEntity();

                imSimrsRekananOpsEntity.setIdRekananOps(rekananId);
                //cari ke dao akun Master berdasarkan no master

                imSimrsRekananOpsEntity.setNomorMaster(bean.getNomorMaster());

                imSimrsRekananOpsEntity.setFlag(bean.getFlag());
                imSimrsRekananOpsEntity.setAction(bean.getAction());
                imSimrsRekananOpsEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsRekananOpsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsRekananOpsEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsRekananOpsEntity.setLastUpdate(bean.getLastUpdate());
                if (masterEntity != null){
                    imSimrsRekananOpsEntity.setNamaRekanan(masterEntity.getNama());
                    //set berdasarkan kebutuhan fild yang di ambil dari tabel lain
                }
                try {
                    // insert into database
                    rekananOpsDao.addAndSave(imSimrsRekananOpsEntity);
                } catch (HibernateException e) {
                    logger.error("[AsuransiiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Asuransi, please info to your admin..." + e.getMessage());
                }
            }

        }
        return null;
    }

    @Override
    public CrudResponse saveEdit(RekananOps bean) throws GeneralBOException {
        return null;
    }

    @Override
    public CrudResponse saveDelete(RekananOps bean) throws GeneralBOException {
        return null;
    }

    @Override
    public RekananOps getDetailRekananOps(String id, String branchId) throws GeneralBOException {
        RekananOps rekananOps = new RekananOps();
        try {
            rekananOps = rekananOpsDao.getDetailRekananOps(id, branchId);
        }catch (HibernateException e){
            logger.error("Error when search detail rekanan ops,"+e.getMessage());
        }
        return rekananOps;
    }

    @Override
    public RekananOps getDetailRekananOpsByDetail(String id, String branchId) throws GeneralBOException {
        RekananOps rekananOps = new RekananOps();
        try {
            rekananOps = rekananOpsDao.getRekananOpsByIdDetail(id, branchId);
        }catch (HibernateException e){
            logger.error("Error when search detail rekanan ops,"+e.getMessage());
        }
        return rekananOps;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public List<RekananOps> getComboRekananOps(String branchId) throws GeneralBOException {
        List<RekananOps> rekananOps = new ArrayList<>();
        try {
            rekananOps = rekananOpsDao.getComboRekananOps(branchId);
        }catch (HibernateException e){
            logger.error("Error when search detail rekanan ops,"+e.getMessage());
        }
        return rekananOps;
    }

    @Override
    public ImSimrsRekananOpsEntity getRekananEntityById(String id) throws GeneralBOException {
        return rekananOpsDao.getById("idRekananOps", id);
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setRekananOpsDao(RekananOpsDao rekananOpsDao) {
        this.rekananOpsDao = rekananOpsDao;
    }
}
