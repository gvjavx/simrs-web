package com.neurix.simrs.master.askep.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.askep.bo.DetailAsuhanKeperawatanBo;
import com.neurix.simrs.master.askep.dao.DetailAsuhanKeperawatanDao;
import com.neurix.simrs.master.askep.model.DetailAsuhanKeperawatan;
import com.neurix.simrs.master.askep.model.ImSimrsDetailAsuhanKeperawatanEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.rencanaasuhankeperawatan.model.ItSimrsRencanaAsuhanKeperawatanEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailAsuhanKeperawatanBoImpl implements DetailAsuhanKeperawatanBo {

    private static transient Logger logger = Logger.getLogger(DetailAsuhanKeperawatanBoImpl.class);
    private DetailAsuhanKeperawatanDao detailAsuhanKeperawatanDao;

    @Override
    public List<DetailAsuhanKeperawatan> getByCriteria(DetailAsuhanKeperawatan bean) throws GeneralBOException {
        List<DetailAsuhanKeperawatan> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();

            if(bean.getIdDetailAsuhanKeperawatan() != null && !"".equalsIgnoreCase(bean.getIdDetailAsuhanKeperawatan())){
                hsCriteria.put("id_detail_askep", bean.getIdDetailAsuhanKeperawatan());
            }
            if(bean.getIdDiagnosaAsuhanKeperawatan() != null && !"".equalsIgnoreCase(bean.getIdDiagnosaAsuhanKeperawatan())){
                hsCriteria.put("id_diagnosa_askep", bean.getIdDiagnosaAsuhanKeperawatan());
            }

            List<ImSimrsDetailAsuhanKeperawatanEntity> entityList = new ArrayList<>();

            try {
                entityList = detailAsuhanKeperawatanDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ImSimrsDetailAsuhanKeperawatanEntity entity: entityList){
                    DetailAsuhanKeperawatan detailAsuhanKeperawatan = new DetailAsuhanKeperawatan();
                    detailAsuhanKeperawatan.setIdDetailAsuhanKeperawatan(entity.getIdDetailAsuhanKeperawatan());
                    detailAsuhanKeperawatan.setIdDiagnosaAsuhanKeperawatan(entity.getIdDiagnosaAsuhanKeperawatan());
                    detailAsuhanKeperawatan.setDiagnosis(entity.getDiagnosis());
                    detailAsuhanKeperawatan.setKeteranganDiagnosis(entity.getKeteranganDiagnosis());
                    detailAsuhanKeperawatan.setHasil(entity.getHasil());
                    detailAsuhanKeperawatan.setKeteranganHasil(entity.getKeteranganHasil());
                    detailAsuhanKeperawatan.setIntervensi(entity.getIntervensi());
                    detailAsuhanKeperawatan.setKeteranganIntervensi(entity.getKeteranganIntervensi());
                    detailAsuhanKeperawatan.setImplementasi(entity.getImplementasi());
                    detailAsuhanKeperawatan.setKeteranganImplementasi(entity.getKeteranganImplementasi());
                    detailAsuhanKeperawatan.setEvaluasi(entity.getEvaluasi());
                    detailAsuhanKeperawatan.setKeteranganEvaluasi(entity.getKeteranganEvaluasi());
                    detailAsuhanKeperawatan.setAction(entity.getAction());
                    detailAsuhanKeperawatan.setFlag(entity.getFlag());
                    detailAsuhanKeperawatan.setCreatedDate(entity.getCreatedDate());
                    detailAsuhanKeperawatan.setCreatedWho(entity.getCreatedWho());
                    detailAsuhanKeperawatan.setLastUpdate(entity.getLastUpdate());
                    detailAsuhanKeperawatan.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(detailAsuhanKeperawatan);
                }
            }
        }
        return list;
    }

    public void setDetailAsuhanKeperawatanDao(DetailAsuhanKeperawatanDao detailAsuhanKeperawatanDao) {
        this.detailAsuhanKeperawatanDao = detailAsuhanKeperawatanDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
