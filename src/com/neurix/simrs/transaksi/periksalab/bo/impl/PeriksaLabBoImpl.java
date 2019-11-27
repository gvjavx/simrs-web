package com.neurix.simrs.transaksi.periksalab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDao;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDetailDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabDetailEntity;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabEntity;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 27/11/2019.
 */
public class PeriksaLabBoImpl implements PeriksaLabBo{
    private static transient Logger logger = Logger.getLogger(PeriksaLabBoImpl.class);
    private PeriksaLabDao periksaLabDao;
    private PeriksaLabDetailDao periksaLabDetailDao;
    private LabDetailDao labDetailDao;

    @Override
    public void saveAdd(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveAdd] START >>>>>>>>> ");
        ItSimrsPeriksaLabEntity entity = new ItSimrsPeriksaLabEntity();

        String id = getNextPeriksaLabId();
        entity.setIdPeriksaLab("PRL"+id);
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setIdDokterPengirim(bean.getIdDokterPengirim());
        entity.setStatusPeriksa("0");
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            periksaLabDao.addAndSave(entity);
        } catch (HibernateException e){
            logger.error("[PeriksaLabBoImpl.saveAdd] ERROR when saving data periksa lab ", e.getCause());
            throw new GeneralBOException("[PeriksaLabBoImpl.saveAdd] ERROR when saving data periksa lab "+ e.getCause());
        }
        logger.info("[PeriksaLabBoImpl.saveAdd] END <<<<<<<<< ");
    }

    @Override
    public void saveEdit(PeriksaLab bean) throws GeneralBOException {

    }

    @Override
    public void saveAddWithParameter(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveAddWithParameter] START >>>>>>>>> ");

        if (periksaLab != null)
        {
            ItSimrsPeriksaLabEntity entity = new ItSimrsPeriksaLabEntity();

            String id = getNextPeriksaLabId();
            entity.setIdPeriksaLab("PRL"+id);
            entity.setIdDetailCheckup(periksaLab.getIdDetailCheckup());
            entity.setIdDokterPengirim(periksaLab.getIdDokterPengirim());
            entity.setStatusPeriksa("0");
            entity.setFlag("Y");
            entity.setAction("C");
            entity.setCreatedDate(periksaLab.getCreatedDate());
            entity.setCreatedWho(periksaLab.getCreatedWho());
            entity.setLastUpdate(periksaLab.getLastUpdate());
            entity.setLastUpdateWho(periksaLab.getLastUpdateWho());

            try {
                periksaLabDao.addAndSave(entity);
            } catch (HibernateException e){
                logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa lab ", e.getCause());
                throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa lab "+ e.getCause());
            }

            if (entity.getIdPeriksaLab() != null && !"".equalsIgnoreCase(entity.getIdPeriksaLab())
                    && !labDetailIds.isEmpty() && labDetailIds.size() > 0)
            {
                for (String labDetailId : labDetailIds)
                {
                    ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();

                    id = getNextDetailLapId();
                    detailEntity.setIdPeriksaLabDetail("DPL"+id);
                    detailEntity.setIdPeriksaLab(entity.getIdPeriksaLab());
                    detailEntity.setIdLabDetail(labDetailId);

                    // get data from master lab detail
                    ImSimrsLabDetailEntity labDetailEntity = new ImSimrsLabDetailEntity();
                    if (labDetailId != null && !"".equalsIgnoreCase(labDetailId))
                    {
                        labDetailEntity = getDataMasterLabDetailByIdLab(labDetailId);
                        if (labDetailEntity != null)
                        {
                            detailEntity.setNamaDetailPeriksa(labDetailEntity.getNamaDetailPeriksa());
                            detailEntity.setKeteranganAcuan(labDetailEntity.getKetentuanAcuan());
                            detailEntity.setSatuan(labDetailEntity.getSatuan());
                        }
                    }

                    detailEntity.setFlag("Y");
                    detailEntity.setAction("C");

                    // from periksaLab
                    detailEntity.setCreatedDate(periksaLab.getCreatedDate());
                    detailEntity.setCreatedWho(periksaLab.getCreatedWho());
                    detailEntity.setLastUpdate(periksaLab.getLastUpdate());
                    detailEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());

                    try {
                        periksaLabDetailDao.addAndSave(detailEntity);
                    } catch (HibernateException e){
                        logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab ", e.getCause());
                        throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab "+ e.getCause());
                    }
                }
            }
        }

        logger.info("[PeriksaLabBoImpl.saveAddWithParameter] END <<<<<<<<< ");
    }

    private List<ItSimrsPeriksaLabEntity> getListEntityPeriksaLab(PeriksaLab bean) throws GeneralBOException{
        logger.info("[PeriksaLabBoImpl.getListEntityPeriksaLab] START >>>>>>>>> ");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_periksa_lab", bean.getIdPeriksaLab());
        hsCriteria.put("flag","Y");

        List<ItSimrsPeriksaLabEntity> periksaLabEntities = new ArrayList<>();
        try {
            periksaLabEntities = periksaLabDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab ", e);
            throw new GeneralBOException("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab "+ e.getCause());
        }

        logger.info("[PeriksaLabBoImpl.getListEntityPeriksaLab] END <<<<<<<<< ");
        return periksaLabEntities;
    }

    private ImSimrsLabDetailEntity getDataMasterLabDetailByIdLab(String id){
        logger.info("[PeriksaLabBoImpl.getDataMasterLabDetailByIdLab] START >>>>>>>>> ");
        List<ImSimrsLabDetailEntity> labDetailEntities = new ArrayList<>();

        if (id != null && !"".equalsIgnoreCase(id)){
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_lab_detail", id);
            hsCriteria.put("flag", "Y");

            try {
                labDetailEntities = labDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data master lab detail ", e);
            }

            logger.info("[PeriksaLabBoImpl.getDataMasterLabDetailByIdLab] END <<<<<<<<< ");
            if (!labDetailEntities.isEmpty() && labDetailEntities.size() > 0){
                return labDetailEntities.get(0);
            }
        }

        logger.info("[PeriksaLabBoImpl.getDataMasterLabDetailByIdLab] END <<<<<<<<< ");
        return new ImSimrsLabDetailEntity();
    }

    private String getNextPeriksaLabId(){
        String id = "";
        try {
            id = periksaLabDao.getNextId();
        } catch (HibernateException e){
            logger.error("[PeriksaLabBoImpl.getNextPeriksaLabId] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextDetailLapId(){
        String id = "";
        try {
            id = periksaLabDetailDao.getNextId();
        } catch (HibernateException e){
            logger.error("[PeriksaLabBoImpl.getNextDetailLapId] ERROR When create sequences", e);
        }
        return id;
    }

    public void setPeriksaLabDao(PeriksaLabDao periksaLabDao) {
        this.periksaLabDao = periksaLabDao;
    }

    public void setPeriksaLabDetailDao(PeriksaLabDetailDao periksaLabDetailDao) {
        this.periksaLabDetailDao = periksaLabDetailDao;
    }

    public void setLabDetailDao(LabDetailDao labDetailDao) {
        this.labDetailDao = labDetailDao;
    }
}
