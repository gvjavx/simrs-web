package com.neurix.simrs.master.ruangan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kelasruangan.dao.KelasRuanganDao;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuanganBoImpl implements RuanganBo {
    protected static transient Logger logger = Logger.getLogger(RuanganBoImpl.class);
    private RuanganDao ruanganDao;

    public static void setLogger(Logger logger) {
        RuanganBoImpl.logger = logger;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    @Override
    public List<Ruangan> getByCriteria(Ruangan bean) throws GeneralBOException {
        logger.info("[RuanganBoImpl.getByCriteria] Start >>>>>>");
        List<Ruangan> result = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())){
            hsCriteria.put("id_kelas_ruangan", bean.getIdKelasRuangan());
        }
        if (bean.getStatusRuangan() != null && !"".equalsIgnoreCase(bean.getStatusRuangan())){
            hsCriteria.put("status_ruangan", bean.getStatusRuangan());
        }

        hsCriteria.put("flag","Y");

        List<MtSimrsRuanganEntity> mtSimrsRuanganEntityList = null;
        try {
            mtSimrsRuanganEntityList = ruanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RuanganBoImpl.getByCriteria] Error get ruangan data "+e.getMessage());
        }

        if (!mtSimrsRuanganEntityList.isEmpty()){
            Ruangan ruangan;
            for (MtSimrsRuanganEntity listEntity : mtSimrsRuanganEntityList){
                ruangan = new Ruangan();
                ruangan.setIdKelasRuangan(listEntity.getIdKelasRuangan());
                ruangan.setIdRuangan(listEntity.getIdRuangan());
                ruangan.setNoRuangan(listEntity.getNoRuangan());
                ruangan.setNamaRuangan(listEntity.getNamaRuangan());
                ruangan.setTarif(listEntity.getTarif());
                ruangan.setStatusRuangan(listEntity.getStatusRuangan());
                result.add(ruangan);
            }
        }
        logger.info("[RuanganBoImpl.getByCriteria] End <<<<<<");

        return result;
    }
}