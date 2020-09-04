package com.neurix.simrs.transaksi.antriantelemedic.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsVideoRmEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author gondok
 * Thursday, 27/08/20 13:39
 */
public class VideoRmDao extends GenericDao<ItSimrsVideoRmEntity, String> {

    @Override
    protected Class<ItSimrsVideoRmEntity> getEntityClass() {
        return ItSimrsVideoRmEntity.class;
    }

    @Override
    public List<ItSimrsVideoRmEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsVideoRmEntity.class);
        if (mapCriteria.get("id_detail_checkup") != null) {
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        criteria.add(Restrictions.eq("flag", "Y"));
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_video_rm')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }

}
