package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.bo.impl;

import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.bo.PendaftaranJasaRekananBo;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.dao.PendaftaranJasaRekananDao;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.ItAkunPendaftaranJasaEntity;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.PendaftaranJasa;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendaftaranJasaRekananBoImpl implements PendaftaranJasaRekananBo {
    public static transient Logger logger = Logger.getLogger(PendaftaranJasaRekananBoImpl.class);

    private PendaftaranJasaRekananDao pendaftaranJasaRekananDao;
    private MasterDao masterDao;


    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public void setPendaftaranJasaRekananDao(PendaftaranJasaRekananDao pendaftaranJasaRekananDao) {
        this.pendaftaranJasaRekananDao = pendaftaranJasaRekananDao;
    }

    @Override
    public List<PendaftaranJasa> getSearchByCriteria(PendaftaranJasa bean) throws GeneralBOException {
        logger.info("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Start >>>");

        List<ItAkunPendaftaranJasaEntity> listEntity = getPendaftaranJasaEntity(bean);

        List<PendaftaranJasa> listOfResult = new ArrayList<>();
        if (listEntity != null && listEntity.size() > 0){

            BeanUtils.copyProperties(listEntity, listOfResult);

            for (PendaftaranJasa pendaftaranJasa : listOfResult){
                if (pendaftaranJasa.getIdVendor() != null && !"".equalsIgnoreCase(pendaftaranJasa.getIdVendor())){
                    ImMasterEntity masterEntity = masterDao.getById("primaryKey.nomorMaster", pendaftaranJasa.getIdVendor());
                    if (masterEntity != null){
                        pendaftaranJasa.setNamaVendor(masterEntity.getNama());
                    }

                    if ("1".equalsIgnoreCase(pendaftaranJasa.getStatus())){
                        pendaftaranJasa.setKeteranganStatus("Menunggu Approval Unit");
                    } else if ("2".equalsIgnoreCase(pendaftaranJasa.getStatus())){
                        pendaftaranJasa.setKeteranganStatus("Sudah Terapprove Oleh Unit Menunggu Approval Keuangan");
                    } else if ("Y".equalsIgnoreCase(pendaftaranJasa.getApproveKeu())){
                        pendaftaranJasa.setKeteranganStatus("Sudah Terapprove Oleh Keuangan Menunggu Approval Kasub Keuangan");
                    } else if ("Y".equalsIgnoreCase(pendaftaranJasa.getApproveKeu())){
                        pendaftaranJasa.setKeteranganStatus("Sudah Terapprove Oleh Kasub Keuangan Menunggu Approval Kepala Keuangan");
                    } else if ("Y".equalsIgnoreCase(pendaftaranJasa.getApproveKeu())){
                        pendaftaranJasa.setKeteranganStatus("Selesai");
                    } else {
                        pendaftaranJasa.setKeteranganStatus("Dibatalkan dengan alasan : "+ pendaftaranJasa.getAlasanBatal());
                    }
                }
            }
        }

        logger.info("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] End <<<");
        return listOfResult;
    }

    @Override
    public void saveAdd(PendaftaranJasa bean) throws GeneralBOException {
        logger.info("[PendaftaranJasaRekananBoImpl.saveAdd] Start >>>");

        ItAkunPendaftaranJasaEntity pendaftaranJasaEntity = new ItAkunPendaftaranJasaEntity();

        BeanUtils.copyProperties(bean, pendaftaranJasaEntity);

        if (pendaftaranJasaEntity == null){
            logger.error("[PendaftaranRekananBoImpl.saveAdd] ERROR. Entity Data Is NULL");
            throw new GeneralBOException("[PendaftaranRekananBoImpl.saveAdd] ERROR. Entity Data Is NULL");
        }

        try {
            pendaftaranJasaEntity.setId(pendaftaranJasaRekananDao.getNextId());
            pendaftaranJasaRekananDao.addAndSave(pendaftaranJasaEntity);
        } catch (HibernateException e){
            logger.error("[PendaftaranRekananBoImpl.saveAdd] ERROR. ", e);
            throw new GeneralBOException("[PendaftaranRekananBoImpl.saveAdd] ERROR. "+ e.getCause());
        }

        logger.info("[PendaftaranJasaRekananBoImpl.saveAdd] End <<<");
    }

    @Override
    public void saveEdit(PendaftaranJasa bean) throws GeneralBOException {
        logger.info("[PendaftaranJasaRekananBoImpl.saveEdit] Start >>>");

        ItAkunPendaftaranJasaEntity entity = pendaftaranJasaRekananDao.getById("id", bean.getId());

        if (entity != null){

            entity.setNamaJasa(bean.getNamaJasa());
            entity.setBiaya(bean.getBiaya());
            entity.setStatus(bean.getStatus());
            entity.setUrlDoc(bean.getUrlDoc() != null ? bean.getUrlDoc() : entity.getUrlDoc());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            entity.setFlag(bean.getFlag());
            if ("N".equalsIgnoreCase(bean.getFlag())){
                entity.setAlasanBatal(bean.getAlasanBatal());
            }
            entity.setFlag(bean.getFlag());
            entity.setAction("U");

            try {
                pendaftaranJasaRekananDao.updateAndSave(entity);
            } catch (HibernateException e){
                logger.error("[PendaftaranRekananBoImpl.saveEdit] ERROR. ", e);
                throw new GeneralBOException("[PendaftaranRekananBoImpl.saveEdit] ERROR. "+ e.getCause());
            }
        }

        logger.info("[PendaftaranJasaRekananBoImpl.saveEdit] End <<<");
    }

    @Override
    public void saveApprove(PendaftaranJasa bean) throws GeneralBOException {
        logger.info("[PendaftaranJasaRekananBoImpl.saveApprove] Start >>>");

        ItAkunPendaftaranJasaEntity entity = pendaftaranJasaRekananDao.getById("id", bean.getId());

        if (entity != null){

            if ("N".equalsIgnoreCase(bean.getFlagApprove())){
                entity.setAlasanBatal(bean.getAlasanBatal());
                entity.setFlag("N");
            }

            if ("keu".equalsIgnoreCase(bean.getJenisJabatan())){
                entity.setApproveKeu(bean.getFlagApprove());
            } else if ("kasubkeu".equalsIgnoreCase(bean.getJenisJabatan())){
                entity.setApproveKasubKeu(bean.getFlagApprove());
            } else {
                entity.setApproveKaKeu(bean.getFlagApprove());
                entity.setFlag("N");
            }

            entity.setLastUpdateWho(bean.getLastUpdateWho());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setAction("U");

            try {
                pendaftaranJasaRekananDao.updateAndSave(entity);
            } catch (HibernateException e){
                logger.error("[PendaftaranRekananBoImpl.saveApprove] ERROR. ", e);
                throw new GeneralBOException("[PendaftaranRekananBoImpl.saveApprove] ERROR. "+ e.getCause());
            }
        }

        logger.info("[PendaftaranJasaRekananBoImpl.saveApprove] End <<<");
    }

    private List<ItAkunPendaftaranJasaEntity> getPendaftaranJasaEntity(PendaftaranJasa bean) throws GeneralBOException{
        logger.info("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Start >>>");

        if (bean != null){

            Map hsCriteria = new HashMap();
            if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())){
                hsCriteria.put("id", bean.getId());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            if (bean.getApproveKaKeu() != null && !"".equalsIgnoreCase(bean.getApproveKaKeu())){
                hsCriteria.put("approve_ka_keu", bean.getApproveKaKeu());
            }
            if (bean.getApproveKasubKeu() != null && !"".equalsIgnoreCase(bean.getApproveKasubKeu())){
                hsCriteria.put("approve_kasub_keu", bean.getApproveKasubKeu());
            }
            if (bean.getApproveKeu() != null && !"".equalsIgnoreCase(bean.getApproveKeu())){
                hsCriteria.put("approve_keu", bean.getApproveKeu());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
                hsCriteria.put("flag", bean.getFlag());
            }

            List<ItAkunPendaftaranJasaEntity> itAkunPendaftaranJasaEntities = null;
            try {
                itAkunPendaftaranJasaEntities = pendaftaranJasaRekananDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Error.", e);
                throw new GeneralBOException("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Error.", e);

            }

            return itAkunPendaftaranJasaEntities;
        }

        logger.info("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Start >>>");
        return null;
    }
}
