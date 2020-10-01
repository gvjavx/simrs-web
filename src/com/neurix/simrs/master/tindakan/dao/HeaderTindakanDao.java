package com.neurix.simrs.master.tindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsHeaderTindakanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 13/08/20.
 */
public class HeaderTindakanDao extends GenericDao<ImSimrsHeaderTindakanEntity, String> {
    @Override
    protected Class<ImSimrsHeaderTindakanEntity> getEntityClass() {
        return ImSimrsHeaderTindakanEntity.class;
    }

    @Override
    public List<ImSimrsHeaderTindakanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ImSimrsHeaderTindakanEntity.class);
        if (mapCriteria.get("id_header_tindakan") != null){
            criteria.add(Restrictions.ilike("idHeaderTindakan", "%"+mapCriteria.get("id_header_tindakan").toString()+"%"));
        }
        if (mapCriteria.get("nama_tindakan") != null){
            criteria.add(Restrictions.ilike("namaTindakan", "%"+mapCriteria.get("nama_tindakan").toString()+"%"));
        }
        if (mapCriteria.get("kategori_ina") != null){
            criteria.add(Restrictions.eq("kategoriInaBpjs", mapCriteria.get("kategori_ina").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_header_tindakan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "HTD"+sId;
    }
}
