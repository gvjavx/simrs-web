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
}
