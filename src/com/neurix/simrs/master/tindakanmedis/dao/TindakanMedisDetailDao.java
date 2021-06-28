package com.neurix.simrs.master.tindakanmedis.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsHeaderTindakanEntity;
import com.neurix.simrs.master.tindakanmedis.model.ImSimrsTindakanMedisDetailEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/** Created by fahmi, for handling Tindakan Medis Detail transaction between data and database  */
public class TindakanMedisDetailDao extends GenericDao<ImSimrsTindakanMedisDetailEntity, String>{

    @Override
    protected Class<ImSimrsTindakanMedisDetailEntity> getEntityClass() {
        return ImSimrsTindakanMedisDetailEntity.class;
    }

    @Override
    public List<ImSimrsTindakanMedisDetailEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanMedisDetailEntity.class);

        if (null!=mapCriteria)
        {
            if (mapCriteria.get("parentid") != null){
                criteria.add(Restrictions.eq("parentid", mapCriteria.get("parentid").toString()));
            }

            if (mapCriteria.get("iddetail") != null){
                criteria.add(Restrictions.eq("idDetail", mapCriteria.get("iddetail").toString()));
            }

            if (mapCriteria.get("infoname") != null){
                criteria.add(Restrictions.ilike("infoName", "%"+mapCriteria.get("infoname").toString()+"%"));
            }

            if (mapCriteria.get("infotype") != null){
                criteria.add(Restrictions.eq("infoType", mapCriteria.get("inf_type").toString()));
            }

            if (mapCriteria.get("infovalue") != null){
                criteria.add(Restrictions.ilike("infoValue", "%"+mapCriteria.get("infovalue").toString()+"%"));
            }

            if (mapCriteria.get("action") != null){
                criteria.add(Restrictions.eq("action", mapCriteria.get("action").toString()));
            }

            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }

        criteria.addOrder(Order.asc("idDetail"));
        List<ImSimrsTindakanMedisDetailEntity> result = criteria.list();
        return result;
    }
}
