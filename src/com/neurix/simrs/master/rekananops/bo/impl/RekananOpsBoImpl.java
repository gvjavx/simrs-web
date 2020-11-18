package com.neurix.simrs.master.rekananops.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.exception.GeneralBOException;
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


    @Override
    public List<RekananOps> getByCriteria(RekananOps bean) throws GeneralBOException {
        logger.info("[RekananOpsBoImpl.getByCriteria] Start >>>>>>");
        List<RekananOps> listOfResultRekananOps = new ArrayList<>();
        if(bean != null){
            Map hsCriteria = new HashMap();
            if (bean.getIdRekananOps() != null && !"".equalsIgnoreCase(bean.getIdRekananOps())) {
                hsCriteria.put("id_rekanan_ops", bean.getIdRekananOps());
            }
            if (bean.getNomorMaster() != null && !"".equalsIgnoreCase(bean.getNomorMaster())) {
                hsCriteria.put("nomor_master", bean.getNomorMaster());
            }
            if (bean.getIsBpjs() != null && !"".equalsIgnoreCase(bean.getIsBpjs())) {
                hsCriteria.put("is_bpjs", bean.getIsBpjs());
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
        logger.info("[RekananOpsBoImpl.getByCriteria] Start >>>>>>");
        List<DetailRekananOps> listOfResultRekananOps = new ArrayList<>();
        if(bean != null) {
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

            List<ImSimrsDetailRekananOpsEntity> listOfDetail = null;
            try {
                listOfDetail = detailRekananOpsDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
            }

            if (listOfDetail.size() > 0){
                for (ImSimrsDetailRekananOpsEntity detail :listOfDetail){
                    DetailRekananOps detailRekananOps = new DetailRekananOps();
                    detailRekananOps.setIdDetailRekananOps(detail.getIdDetailRekananOps());
                    detailRekananOps.setDiskon(detail.getDiskon());
                    detailRekananOps.setIsBpjs(detail.getIsBpjs());
                    detailRekananOps.setBranchId(detail.getBranchId());
                    detailRekananOps.setCreatedDate(detail.getCreatedDate());
                    detailRekananOps.setLastUpdate(detail.getLastUpdate());
                    detailRekananOps.setLastUpdateWho(detail.getLastUpdateWho());

                    // mengambil dari RekananOps
                    hsCriteria = new HashMap();
                    hsCriteria.put("id_detail_rekanan_ops)", detail.getIdDetailRekananOps());
                    List<ImSimrsRekananOpsEntity> listOfHead = null ;
                    try {
                        listOfHead = rekananOpsDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
                    }
                    if(listOfHead.size()>0){
                        for(ImSimrsRekananOpsEntity head : listOfHead){
                            detailRekananOps.setNamaRekanan(head.getNamaRekanan());
                            detailRekananOps.setNomorMaster(head.getNomorMaster());
                            detailRekananOps.setTipe(head.getTipe());
                        }
                    }

                    hsCriteria = new HashMap();
                    hsCriteria.put("branch_id", detail.getBranchId());

                    List<ImBranches> listOfBranch = null;
                    try {
                        listOfBranch = branchDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
                    }
                    if(listOfBranch.size()>0){
                        for(ImBranches branch : listOfBranch){
                            detailRekananOps.setBranchName(branch.getBranchName());
                        }
                    }

                    listOfResultRekananOps.add(detailRekananOps);
                }
            }
        }
        logger.info("[RekananOpsBoImpl.getByCriteria] End <<<<<<");
        return listOfResultRekananOps;
    }

    @Override
    public CrudResponse saveAdd(RekananOps bean) throws GeneralBOException {
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
