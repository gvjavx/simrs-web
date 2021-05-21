package com.neurix.simrs.master.telemedic.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kurir.bo.impl.KurirBoImpl;
import com.neurix.simrs.master.pasien.model.ImSImrsRekamMedicLamaEntity;
import com.neurix.simrs.master.telemedic.bo.RekeningTelemedicBo;
import com.neurix.simrs.master.telemedic.dao.RekeningTelemedicDao;
import com.neurix.simrs.master.telemedic.model.ImSimrsRekeningTelemedicEntity;
import com.neurix.simrs.master.telemedic.model.RekeningTelemedic;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gondok
 * Monday, 03/08/20 11:00
 */
public class RekeningTelemedicBoImpl implements RekeningTelemedicBo {
    protected static transient Logger logger = org.apache.log4j.Logger.getLogger(RekeningTelemedicBoImpl.class);

    private RekeningTelemedicDao rekeningTelemedicDao;

    public void setRekeningTelemedicDao(RekeningTelemedicDao rekeningTelemedicDao) {
        this.rekeningTelemedicDao = rekeningTelemedicDao;
    }

    @Override
    public List<RekeningTelemedic> getByCriteria(RekeningTelemedic bean) throws GeneralBOException{
        logger.info("[RekeningTelemedicBoImpl.getByCriteria] Start >>>>>>>");

        List<RekeningTelemedic> rekeningTelemedics = new ArrayList<>();
        if (bean != null) {
            List<ImSimrsRekeningTelemedicEntity> imSimrsRekeningTelemedicEntities = getEntityByCriteria(bean);

            if (!imSimrsRekeningTelemedicEntities.isEmpty()) {
                rekeningTelemedics = setTemplateRekeningTelemedic(imSimrsRekeningTelemedicEntities);
            }
        }

        logger.info("[RekeningTelemedicBoImpl.getByCriteria] End >>>>>>>");
        return rekeningTelemedics;
    }

    public List<ImSimrsRekeningTelemedicEntity> getEntityByCriteria(RekeningTelemedic bean) throws GeneralBOException{
        logger.info("[RekeningTelemedicBoImpl.getEntityByCriteria] Start >>>>>>>");
        List<ImSimrsRekeningTelemedicEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap<>();
        hsCriteria.put("flag", "Y");
        if (bean.getIdRekening() != null && !"".equalsIgnoreCase(bean.getIdRekening())) {
            hsCriteria.put("id_rekening", bean.getIdRekening());
        }
        if (bean.getPembayaranId() != null && !"".equalsIgnoreCase(bean.getPembayaranId())) {
            hsCriteria.put("pembayaran_id", bean.getPembayaranId());
        }
        if (bean.getPembayaranName() != null && !"".equalsIgnoreCase(bean.getPembayaranName())) {
            hsCriteria.put("pembayaran_name", bean.getPembayaranName());
        }
        if (bean.getNoRekening() != null && !"".equalsIgnoreCase(bean.getNoRekening())) {
            hsCriteria.put("no_rekening", bean.getNoRekening());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getTipeRekening() != null && !"".equalsIgnoreCase(bean.getTipeRekening())) {
            hsCriteria.put("tipe_rekening", bean.getTipeRekening());
        }

        List<ImSimrsRekeningTelemedicEntity> imSimrsRekeningTelemedicEntities = null;
        try {
            imSimrsRekeningTelemedicEntities = rekeningTelemedicDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[KurirBoImpl.getEntityByCriteria] Error when search rekeningr by criteria " + e.getMessage());
        }

        if (!imSimrsRekeningTelemedicEntities.isEmpty()) {
            results = imSimrsRekeningTelemedicEntities;
        }

        logger.info("[RekeningTelemedicBoImpl.getEntityByCriteria] End >>>>>>>");
        return results;
    }

    public List<RekeningTelemedic> setTemplateRekeningTelemedic(List<ImSimrsRekeningTelemedicEntity> listEntity) {
        logger.info("[RekeningTelemedicBoImpl.setTemplateRekeningTelemedic] Start >>>>>>>");
        List<RekeningTelemedic> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        RekeningTelemedic rekeningTelemedic;
        for (ImSimrsRekeningTelemedicEntity data : listEntity) {
            rekeningTelemedic = new RekeningTelemedic();
            rekeningTelemedic.setIdRekening(data.getIdRekening());
            rekeningTelemedic.setPembayaranId(data.getPembayaranId());
            rekeningTelemedic.setPembayaranName(data.getPembayaranName());
            rekeningTelemedic.setNoRekening(data.getNoRekening());
            rekeningTelemedic.setNamaRekening(data.getNamaRekening());
            rekeningTelemedic.setCoa(data.getCoa());
            rekeningTelemedic.setFlag(data.getFlag());
            rekeningTelemedic.setAction(data.getAction());
            rekeningTelemedic.setCreatedDate(data.getCreatedDate());
            rekeningTelemedic.setCreatedWho(data.getCreatedWho());
            rekeningTelemedic.setLastUpdate(data.getLastUpdate());
            rekeningTelemedic.setLastUpdateWho(data.getLastUpdateWho());
            rekeningTelemedic.setTipeRekening(data.getTipeRekening());
            rekeningTelemedic.setClientId(data.getClientId());
            rekeningTelemedic.setKeterangan(data.getKeterangan());

            rekeningTelemedic.setStCreatedDate(formatter.format(data.getCreatedDate()));
            rekeningTelemedic.setStLastUpdate(formatter.format(data.getLastUpdate()));

            list.add(rekeningTelemedic);
        }

        logger.info("[RekeningTelemedicBoImpl.setTemplateRekeningTelemedic] End >>>>>>>");
        return list;

    }

    @Override
    public void saveAdd (RekeningTelemedic rekeningTelemedic) throws GeneralBOException {
        logger.info("[RekeningTelemedicBoImpl.saveAdd] Start >>>>>>>");

        if(rekeningTelemedic != null) {
            ImSimrsRekeningTelemedicEntity rekeningTelemedicEntity = new ImSimrsRekeningTelemedicEntity();
            String id = getNextIdRekening();

            rekeningTelemedicEntity.setIdRekening("RKN"+id);
            rekeningTelemedicEntity.setPembayaranId(rekeningTelemedic.getPembayaranId());
            rekeningTelemedicEntity.setPembayaranName(rekeningTelemedic.getPembayaranName());
            rekeningTelemedicEntity.setNoRekening(rekeningTelemedic.getNoRekening());
            rekeningTelemedicEntity.setNamaRekening(rekeningTelemedic.getNamaRekening());
            rekeningTelemedicEntity.setBranchId(rekeningTelemedic.getBranchId());
            rekeningTelemedicEntity.setCoa(rekeningTelemedic.getCoa());
            rekeningTelemedicEntity.setFlag("Y");
            rekeningTelemedicEntity.setAction("C");
            rekeningTelemedicEntity.setCreatedDate(rekeningTelemedic.getCreatedDate());
            rekeningTelemedicEntity.setLastUpdate(rekeningTelemedic.getLastUpdate());
            rekeningTelemedicEntity.setCreatedWho(rekeningTelemedic.getCreatedWho());
            rekeningTelemedicEntity.setLastUpdateWho(rekeningTelemedic.getLastUpdateWho());

            try {
                rekeningTelemedicDao.addAndSave(rekeningTelemedicEntity);
            } catch (HibernateException e){
                logger.error("[RekeningTelemedicBoImpl.saveAdd] Error when saving data rekening", e);
                throw new GeneralBOException(" Error when saving data rekening " + e.getMessage());
            }
        } else {
            logger.error("[RekeningTelemedicBoImpl.saveAdd] Error when saving data is null");
            throw new GeneralBOException(" Error when saving data rekening is null");
        }

        logger.info("[RekeningTelemedicBoImpl.saveAdd] End >>>>>>>");
    }

    private String getNextIdRekening() {
        logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] Start >>>>>>>");
        String id="";

        try {
            id = rekeningTelemedicDao.getNextIdRekening();
        } catch (HibernateException e){
            logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] Error :"+ e );
        }

        logger.info("[RekeningTelemedicBoImpl.getNextIdRekening] End >>>>>>>");
        return id;
    }

    @Override
    public ImSimrsRekeningTelemedicEntity getRekeningTelemedicById(String id) throws GeneralBOException {
        logger.info("[RekeningTelemedicBoImpl.getRekeningTelemedicById] Start >>>>>>>");

        ImSimrsRekeningTelemedicEntity rekeningTelemedicEntity = new ImSimrsRekeningTelemedicEntity();

        try {
            rekeningTelemedicEntity = rekeningTelemedicDao.getById("idRekening", id);
        } catch (HibernateException e){
            logger.info("[RekeningTelemedicBoImpl.getRekeningTelemedicById] Error :"+ e );
        }

        logger.info("[RekeningTelemedicBoImpl.getRekeningTelemedicById] End <<<<<<<");
        return rekeningTelemedicEntity;
    }
}
