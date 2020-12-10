package com.neurix.simrs.master.rekananops.bo.impl;



import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.exception.GeneralBOException;


import com.neurix.hris.master.payrollPtkp.model.ImHrisDetailRekananOpsEntity;
import com.neurix.simrs.master.rekananops.bo.DetailRekananOpsBo;
import com.neurix.simrs.master.rekananops.dao.DetailRekananOpsDao;
import com.neurix.simrs.master.rekananops.dao.RekananOpsDao;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.ImSimrsDetailRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;

import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailRekananOpsBoImpl implements DetailRekananOpsBo {
    protected static transient Logger logger = Logger.getLogger(DetailRekananOpsBoImpl.class);

    private RekananOpsDao rekananOpsDao;
    private DetailRekananOpsDao detailRekananOpsDao;
    private BranchDao branchDao;

    public void setRekananOpsDao(RekananOpsDao rekananOpsDao) {
        this.rekananOpsDao = rekananOpsDao;
    }

    public void setDetailRekananOpsDao(DetailRekananOpsDao detailRekananOpsDao) {
        this.detailRekananOpsDao = detailRekananOpsDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public List<DetailRekananOps> getSearchByCriteria(DetailRekananOps bean) throws GeneralBOException {
        logger.info("[RekananOpsBoImpl.getByCriteria] Start >>>>>>");
        List<DetailRekananOps> listOfResultRekananOps = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdRekananOps() != null && !"".equalsIgnoreCase(bean.getIdRekananOps())) {
                hsCriteria.put("id_rekanan_ops", bean.getIdRekananOps());
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
            if (bean.getTipe() != null && !"".equalsIgnoreCase(bean.getTipe())) {
                if ("no".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("tipe", "no");
                } else {
                    hsCriteria.put("tipe", bean.getFlag());
                }
            } else {
                hsCriteria.put("tipe", "ptpn");
            }

            List<ImSimrsDetailRekananOpsEntity> listOfDetail = null;
            try {
                listOfDetail = detailRekananOpsDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
            }

            if (listOfDetail.size() > 0) {
                for (ImSimrsDetailRekananOpsEntity detail : listOfDetail) {
                    DetailRekananOps detailRekananOps = new DetailRekananOps();
                    detailRekananOps.setIdDetailRekananOps(detail.getIdDetailRekananOps());
                    detailRekananOps.setIdRekananOps(detail.getIdRekananOps());
                    detailRekananOps.setDiskon(detail.getDiskon());
                    detailRekananOps.setIsBpjs(detail.getIsBpjs());
                    detailRekananOps.setBranchId(detail.getBranchId());
                    detailRekananOps.setCreatedWho(detail.getCreatedWho());
                    detailRekananOps.setCreatedDate(detail.getCreatedDate());
                    detailRekananOps.setLastUpdate(detail.getLastUpdate());
                    detailRekananOps.setLastUpdateWho(detail.getLastUpdateWho());
                    detailRekananOps.setFlag(detail.getFlag());
                    detailRekananOps.setAction(detail.getAction());

                    // mengambil dari RekananOps
                    hsCriteria = new HashMap();
                    hsCriteria.put("id_rekanan_ops", detail.getIdRekananOps());
                    if (bean.getNomorMaster() != null && !"".equalsIgnoreCase(bean.getNomorMaster())) {
                        hsCriteria.put("nomor_master", bean.getNomorMaster());
                    }
                    List<ImSimrsRekananOpsEntity> listOfHead = null;
                    try {
                        listOfHead = rekananOpsDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
                    }
                    if (listOfHead.size() > 0) {
                        for (ImSimrsRekananOpsEntity head : listOfHead) {
                            detailRekananOps.setNamaRekanan(head.getNamaRekanan());
                            detailRekananOps.setNomorMaster(head.getNomorMaster());
                            detailRekananOps.setTipe(head.getTipe());
                        }
                    }
                    // END
                    // mengambil data dari branch
                    hsCriteria = new HashMap();
                    hsCriteria.put("branch_id", detail.getBranchId());
                    hsCriteria.put("flag", "Y");

                    List<ImBranches> listOfBranch = null;
                    try {
                        listOfBranch = branchDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
                    }
                    if (listOfBranch.size() > 0) {
                        for (ImBranches branch : listOfBranch) {
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
    public CrudResponse saveAdd(DetailRekananOps bean) throws GeneralBOException {
        if (bean != null) {
            List<ImSimrsDetailRekananOpsEntity> cekList = new ArrayList<>();
            try {
                cekList = detailRekananOpsDao.getDetailRekananOps(bean.getIdRekananOps() , bean.getBranchId());
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
            if (cekList.size() > 0) {
                throw new GeneralBOException("nama Detail rekanan ops dan branch sudah ada sudah ada...!");
            } else {
                String detailrekanan;
                try {
                    // Generating ID, get from postgre sequence
                    detailrekanan = detailRekananOpsDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[AsuransiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence Asuransi id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsDetailRekananOpsEntity imSimrsDetailRekananOpsEntity = new ImSimrsDetailRekananOpsEntity();

                imSimrsDetailRekananOpsEntity.setIdDetailRekananOps(detailrekanan);
                //cari ke dao akun Master berdasarkan no master

                imSimrsDetailRekananOpsEntity.setIdRekananOps(bean.getIdRekananOps());
                imSimrsDetailRekananOpsEntity.setIsBpjs(bean.getIsBpjs());
                imSimrsDetailRekananOpsEntity.setDiskon(bean.getDiskon());
                imSimrsDetailRekananOpsEntity.setBranchId(bean.getBranchId());

                imSimrsDetailRekananOpsEntity.setFlag(bean.getFlag());
                imSimrsDetailRekananOpsEntity.setAction(bean.getAction());
                imSimrsDetailRekananOpsEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsDetailRekananOpsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsDetailRekananOpsEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsDetailRekananOpsEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    detailRekananOpsDao.addAndSave(imSimrsDetailRekananOpsEntity);
                } catch (HibernateException e) {
                    logger.error("[AsuransiiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Asuransi, please info to your admin..." + e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public CrudResponse saveEdit(DetailRekananOps bean) throws GeneralBOException {
        logger.info("[DetailRekananOps.saveEdit] start process >>>");
        if (bean != null) {

//            List<ImSimrsDetailRekananOpsEntity> cekList = new ArrayList<>();
//            try {
//                cekList = detailRekananOpsDao.getDetailRekananOps(bean.getIdRekananOps() , bean.getBranchId());
//            } catch (HibernateException e) {
//                logger.error(e.getMessage());
//            }
//            if (cekList.size() > 0) {
//                throw new GeneralBOException("nama Detail rekanan ops dan branch sudah ada sudah ada...!");
//            } else {

            String idDetailRekananOps = bean.getIdDetailRekananOps();
            ImSimrsDetailRekananOpsEntity imSimrsDetailRekananOpsEntity = null;
            try {
                // Get data from database by ID
                imSimrsDetailRekananOpsEntity = detailRekananOpsDao.getById("idDetailRekananOps", idDetailRekananOps);
            } catch (HibernateException e) {
                logger.error("[DetailRekananBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data DetailRekananOps by Kode DetailRekananOps, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsDetailRekananOpsEntity != null) {
//                imSimrsDetailRekananOpsEntity.setIdDetailRekananOps(bean.getIdDetailRekananOps());
                imSimrsDetailRekananOpsEntity.setBranchId(bean.getBranchId());
                imSimrsDetailRekananOpsEntity.setIdRekananOps(bean.getIdRekananOps());
                imSimrsDetailRekananOpsEntity.setDiskon(bean.getDiskon());
                imSimrsDetailRekananOpsEntity.setIsBpjs(bean.getIsBpjs());

                imSimrsDetailRekananOpsEntity.setFlag(bean.getFlag());
                imSimrsDetailRekananOpsEntity.setAction(bean.getAction());
                imSimrsDetailRekananOpsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsDetailRekananOpsEntity.setLastUpdate(bean.getLastUpdate());


                try {
                    // Update into database
                    detailRekananOpsDao.updateAndSave(imSimrsDetailRekananOpsEntity);
                } catch (HibernateException e) {
                    logger.error("[CutiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Cuti, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[DetailRekananOpsimpl.saveEdit] Error, not found data DetailRekananOps with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data DetailRekananOps with request id, please check again your data ...");
//                condition = "Error, not found data Cuti with request id, please check again your data ...";
            }
        }
//        }
        logger.info("[DetailImpl.saveEdit] end process <<<");
        return null;
    }

    @Override
    public CrudResponse saveDelete(DetailRekananOps bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {

            String idDetailRekananOps = bean.getIdDetailRekananOps();
            ImSimrsDetailRekananOpsEntity imSimrsDetailRekananOpsEntity = null;

            try {
                // Get data from database by ID
                imSimrsDetailRekananOpsEntity = detailRekananOpsDao.getById("idDetailRekananOps", idDetailRekananOps);
            } catch (HibernateException e) {
                logger.error("[DetailRekananOpsBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsDetailRekananOpsEntity != null) {

                // Modify from bean to entity serializable
                imSimrsDetailRekananOpsEntity.setIdDetailRekananOps(bean.getIdDetailRekananOps());
                imSimrsDetailRekananOpsEntity.setIdRekananOps(bean.getIdRekananOps());
                imSimrsDetailRekananOpsEntity.setIsBpjs(bean.getIsBpjs());
                imSimrsDetailRekananOpsEntity.setDiskon(bean.getDiskon());
                imSimrsDetailRekananOpsEntity.setBranchId(bean.getBranchId());

                imSimrsDetailRekananOpsEntity.setFlag(bean.getFlag());
                imSimrsDetailRekananOpsEntity.setAction(bean.getAction());
                imSimrsDetailRekananOpsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsDetailRekananOpsEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    detailRekananOpsDao.updateAndSave(imSimrsDetailRekananOpsEntity);
                } catch (HibernateException e) {
                    logger.error("[DetailRekananOpsBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data DetailRekananOps, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[DetailRekananOpsBoImpl.saveDelete] Error, not found data DetailRekananOps with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data DetailRekananOps with request id, please check again your data ...");

            }
        }
        logger.info("[DetailRekananOpsBoImpl.saveDelete] end process <<<");
        return null;
    }
}

