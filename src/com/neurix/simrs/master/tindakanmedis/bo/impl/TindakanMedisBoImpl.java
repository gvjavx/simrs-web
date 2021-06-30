package com.neurix.simrs.master.tindakanmedis.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoritindakanina.model.ImSimrsKategoriTindakanInaEntity;
import com.neurix.simrs.master.tindakan.bo.impl.HeaderTindakanBoImpl;
import com.neurix.simrs.master.tindakan.model.HeaderTindakan;
import com.neurix.simrs.master.tindakan.model.ImSimrsHeaderTindakanEntity;
import com.neurix.simrs.master.tindakanmedis.bo.TindakanMedisBo;
import com.neurix.simrs.master.tindakanmedis.dao.TindakanMedisDao;
import com.neurix.simrs.master.tindakanmedis.dao.TindakanMedisDetailDao;
import com.neurix.simrs.master.tindakanmedis.model.ImSimrsTindakanMedisDetailEntity;
import com.neurix.simrs.master.tindakanmedis.model.ImSimrsTindakanMedisEntity;
import com.neurix.simrs.master.tindakanmedis.model.TindakanMedis;
import com.neurix.simrs.master.tindakanmedis.model.TindakanMedisDetail;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** for handling process tindakan medis */
public class TindakanMedisBoImpl implements TindakanMedisBo {

    /** Logger */
    private static transient Logger logger = Logger.getLogger(TindakanMedisBoImpl.class);

    /** Database */
    private TindakanMedisDao tmDao;
    private TindakanMedisDetailDao tmdDao;

    /** Start Set by Spring Bean*/
    public void setTindakanMedisDao(TindakanMedisDao tmDao) {
        this.tmDao = tmDao;
    }

    public void setTindakanMedisDetailDao(TindakanMedisDetailDao tmDao) {
        this.tmdDao = tmDao;
    }
    /** End Set by Spring Bean*/

    @Override
    public List<TindakanMedis> getByCriteria(TindakanMedis bean) throws GeneralBOException {

        List<ImSimrsTindakanMedisEntity> tempRresults = new ArrayList<>();
        List<TindakanMedis> results = new ArrayList<>();
        Map hsCiteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())) {
            hsCiteria.put("id", bean.getId());
        }
        if (bean.getName() != null && !"".equalsIgnoreCase(bean.getName())) {
            hsCiteria.put("name", bean.getName());
        }
        if (bean.getType() != null && !"".equalsIgnoreCase(bean.getType())) {
            hsCiteria.put("type", bean.getType());
        }
        if (bean.getAction() != null && !"".equalsIgnoreCase(bean.getAction())) {
            hsCiteria.put("action", bean.getAction());
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            hsCiteria.put("flag", bean.getFlag());
        } else {
            hsCiteria.put("flag", "Y");
        }

        try {
            tempRresults = tmDao.getByCriteria(hsCiteria);
        } catch (HibernateException e) {
            logger.error("[TindakanMedisBoImpl.getByCriteria] Error when get data tindakan ", e);
        }

        if(tempRresults.size() > 0){
            TindakanMedis tm;
            TindakanMedisDetail tmd;
            List<TindakanMedisDetail> listDetail;
            Map<String, String> map = new HashMap<>();

            List<ImSimrsTindakanMedisDetailEntity> tmp;

            for (ImSimrsTindakanMedisEntity entity: tempRresults){
                tm = new TindakanMedis();
                tm.setId(entity.getId());
                tm.setName(entity.getName());
                tm.setType(entity.getType());
                tm.setAction(entity.getAction());
                tm.setFlag(entity.getFlag());
                tm.setCreatedDate(entity.getCreatedDate());
                tm.setCreatedWho(entity.getCreatedWho());
                tm.setLastUpdate(entity.getLastUpdate());
                tm.setLastUpdateWho(entity.getLastUpdateWho());

                // getting details
//                map.put("parentid", entity.getId());
//                tmp = tmdDao.getByCriteria(map);
//                if(null!=tmp && tmp.size() > 0)
//                {
//                    listDetail = new ArrayList<>();
//                    for (ImSimrsTindakanMedisDetailEntity temp : tmp)
//                    {
//                        tmd = new TindakanMedisDetail();
//                        tmd.setParentid(temp.getParentid());
//                        tmd.setInfoName(temp.getInfoName());
//                        tmd.setInfoType(temp.getInfoType());
//                        tmd.setInfoValue(temp.getInfoValue());
//                        tmd.setAction(temp.getAction());
//                        tmd.setFlag(temp.getFlag());
//                        tmd.setCreatedDate(temp.getCreatedDate());
//                        tmd.setCreatedWho(temp.getCreatedWho());
//                        tmd.setLastUpdate(temp.getLastUpdate());
//                        tmd.setLastUpdateWho(temp.getLastUpdateWho());
//
//                        listDetail.add(tmd);
//                    }
//
//                    tm.setDetails(listDetail);
//                }

                results.add(tm);
            }
        }

        return results;
    }
}
