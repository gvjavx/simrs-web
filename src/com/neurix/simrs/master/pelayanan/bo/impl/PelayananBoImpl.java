package com.neurix.simrs.master.pelayanan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.dao.PelayananSpesialisDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPoliSpesialisEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 12/11/2019.
 */
public class PelayananBoImpl implements PelayananBo{
    protected static transient Logger logger = Logger.getLogger(PelayananBoImpl.class);
    private PelayananDao pelayananDao;
    private PelayananSpesialisDao pelayananSpesialisDao;

    public void setPelayananDao(PelayananDao pelayananDao) {

        this.pelayananDao = pelayananDao;
    }

    public void setPelayananSpesialisDao(PelayananSpesialisDao pelayananSpesialisDao) {
        this.pelayananSpesialisDao = pelayananSpesialisDao;
    }

    public PelayananDao getPelayananDao() {
        return pelayananDao;
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
        } catch (HibernateException e){
            logger.error("[pelayananBoImpl.getListAllPelayanan] Error get pelayanan data "+e.getMessage());
        }


        try {
            imSimrsPoliSpesialisEntities = pelayananSpesialisDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[pelayananBoImpl.getListAllPelayanan] Error get pelayanan data "+e.getMessage());
        }

        if (!imSimrsPelayananEntities.isEmpty()){
            Pelayanan pelayanan;
            for (ImSimrsPelayananEntity listEntity : imSimrsPelayananEntities){
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(listEntity.getIdPelayanan());
                pelayanan.setNamaPelayanan(listEntity.getNamaPelayanan());
                result.add(pelayanan);
            }
        }
        logger.info("[pelayananBoImpl.getListAllPelayanan] End <<<<<<");
        return result;
    }

    @Override
    public List<Pelayanan> getListApotek() throws GeneralBOException {

        List<Pelayanan> listApotek = new ArrayList<>();
        try {
            listApotek =  pelayananDao.getListApotek();
        }catch (HibernateException e){
            logger.error("[pelayananBoImpl.getListAllPelayanan] Error get pelayanan data apotek "+e.getMessage());
        }

        return listApotek;
    }

    @Override
    public List<Pelayanan> getByCriteria(Pelayanan bean) throws GeneralBOException {
        logger.info("[PelayananBoImpl.getByCriteria] Start >>>>>>");
        List<Pelayanan> result = new ArrayList<>();

        if(bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            if (bean.getTipePelayanan() != null && !"".equalsIgnoreCase(bean.getTipePelayanan())){
                hsCriteria.put("tipe_pelayanan", bean.getTipePelayanan());
            }

            hsCriteria.put("flag","Y");

            List<ImSimrsPelayananEntity> entityList = new ArrayList<>();

            try {
                entityList = pelayananDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PelayananBoImpl.getByCriteria] Error get pelayanan data "+e.getMessage());
            }

            if (!entityList.isEmpty()){
                Pelayanan pelayanan;
                for (ImSimrsPelayananEntity entity : entityList){
                    pelayanan = new Pelayanan();
                    pelayanan.setIdPelayanan(entity.getIdPelayanan());
                    pelayanan.setNamaPelayanan(entity.getNamaPelayanan());
                    pelayanan.setAction(entity.getAction());
                    pelayanan.setFlag(entity.getFlag());
                    pelayanan.setCreatedDate(entity.getCreatedDate());
                    pelayanan.setCreatedWho(entity.getCreatedWho());
                    pelayanan.setLastUpdate(entity.getLastUpdate());
                    pelayanan.setLastUpdateWho(entity.getLastUpdateWho());
                    pelayanan.setTipePelayanan(entity.getTipePelayanan());
                    pelayanan.setNotPoli(entity.getIsPoli());
                    pelayanan.setBranchId(entity.getBranchId());
                    result.add(pelayanan);
                }
            }
        }

        logger.info("[PelayananBoImpl.getByCriteria] End <<<<<<");
        return result;
    }
}
