package com.neurix.simrs.master.kelasruangan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.dao.KelasRuanganDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KelasRuanganBoImpl implements KelasRuanganBo {

    protected static transient Logger logger = Logger.getLogger(KelasRuanganBoImpl.class);
    private KelasRuanganDao kelasRuanganDao;

    public void setKelasRuanganDao(KelasRuanganDao kelasRuanganDao) {
        this.kelasRuanganDao = kelasRuanganDao;
    }

    public static void setLogger(Logger logger) {

        KelasRuanganBoImpl.logger = logger;
    }

    @Override
    public List<KelasRuangan> getByCriteria(KelasRuangan bean) throws GeneralBOException {
        logger.info("[KelasRuanganBoImpl.getByCriteria] Start >>>>>>");
        List<KelasRuangan> result = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())){
            hsCriteria.put("id_kelas_ruangan", bean.getIdKelasRuangan());
        }

        hsCriteria.put("flag","Y");

        List<ImSimrsKelasRuanganEntity> imSimrsKelasRuanganEntityList = null;
        try {
            imSimrsKelasRuanganEntityList = kelasRuanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[KelasRuanganBoImpl.getByCriteria] Error get kelas ruangan data "+e.getMessage());
        }

        if (!imSimrsKelasRuanganEntityList.isEmpty()){
            KelasRuangan kelasRuangan;
            for (ImSimrsKelasRuanganEntity listEntity : imSimrsKelasRuanganEntityList){
                kelasRuangan = new KelasRuangan();
                kelasRuangan.setIdKelasRuangan(listEntity.getIdKelasRuangan());
                kelasRuangan.setNamaKelasRuangan(listEntity.getNamaKelasRuangan());
                result.add(kelasRuangan);
            }
        }
        logger.info("[KelasRuanganBoImpl.getByCriteria] End <<<<<<");

        return result;
    }
}