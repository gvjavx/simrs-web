package com.neurix.simrs.master.tindakanmedis.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakanmedis.bo.TindakanMedisDetailBo;
import com.neurix.simrs.master.tindakanmedis.dao.TindakanMedisDetailDao;
import com.neurix.simrs.master.tindakanmedis.model.ImSimrsTindakanMedisDetailEntity;

import com.neurix.simrs.master.tindakanmedis.model.TindakanMedisDetail;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** for handling process tindakan medis */
public class TindakanMedisDetailBoImpl implements TindakanMedisDetailBo
{
    /** Logger */
    private static transient Logger logger = Logger.getLogger(TindakanMedisDetailBoImpl.class);

    /** Database */
    private TindakanMedisDetailDao tmdDao;

    public void setTindakanMedisDetailDao(TindakanMedisDetailDao tmDao) {
        this.tmdDao = tmDao;
    }


    @Override
    public List<TindakanMedisDetail> getByCriteria(TindakanMedisDetail bean) throws GeneralBOException {

        List<ImSimrsTindakanMedisDetailEntity> tempRresults = new ArrayList<>();
        List<TindakanMedisDetail> results = new ArrayList<>();
        Map hsCiteria = new HashMap();
        if (bean.getParentid() != null && !"".equalsIgnoreCase(bean.getParentid())) {
            hsCiteria.put("parentid", bean.getParentid());
        }
        if (bean.getIdDetail() != null && !"".equalsIgnoreCase(bean.getIdDetail())) {
            hsCiteria.put("iddetail", bean.getInfoName());
        }
        if (bean.getInfoName() != null && !"".equalsIgnoreCase(bean.getInfoName())) {
            hsCiteria.put("infoname", bean.getInfoName());
        }
        if (bean.getInfoType() != null && !"".equalsIgnoreCase(bean.getInfoType())) {
            hsCiteria.put("infotype", bean.getInfoType());
        }
        if (bean.getInfoValue() != null && !"".equalsIgnoreCase(bean.getInfoValue())) {
            hsCiteria.put("infovalue", bean.getInfoValue());
        }

        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            hsCiteria.put("flag", bean.getFlag());
        } else {
            hsCiteria.put("flag", "Y");
        }

        try {
            tempRresults = tmdDao.getByCriteria(hsCiteria);
        } catch (HibernateException e) {
            logger.error("[TindakanMedisDetailBoImpl.getByCriteria] Error when get data tindakan ", e);
        }

        if(tempRresults.size() > 0){
            TindakanMedisDetail tmd;

            for (ImSimrsTindakanMedisDetailEntity entity: tempRresults){
                tmd = new TindakanMedisDetail();
                tmd.setParentid(entity.getParentid());
                tmd.setIdDetail(entity.getIdDetail());
                tmd.setInfoName(entity.getInfoName());
                tmd.setInfoType(entity.getInfoType());
                tmd.setInfoValue(entity.getInfoValue());
                tmd.setAction(entity.getAction());
                tmd.setFlag(entity.getFlag());
                tmd.setCreatedDate(entity.getCreatedDate());
                tmd.setCreatedWho(entity.getCreatedWho());
                tmd.setLastUpdate(entity.getLastUpdate());
                tmd.setLastUpdateWho(entity.getLastUpdateWho());
                results.add(tmd);
            }
        }

        return results;
    }
}
