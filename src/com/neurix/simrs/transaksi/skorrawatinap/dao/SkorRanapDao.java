package com.neurix.simrs.transaksi.skorrawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 10/02/20.
 */
public class SkorRanapDao extends GenericDao<ItSimrsSkorRanapEntity, String> {
    @Override
    protected Class<ItSimrsSkorRanapEntity> getEntityClass() {
        return ItSimrsSkorRanapEntity.class;
    }

    @Override
    public List<ItSimrsSkorRanapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsSkorRanapEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("group_id") != null)
            criteria.add(Restrictions.eq("groupId", mapCriteria.get("group_id").toString()));
        if (mapCriteria.get("id_kategori") != null)
            criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
        if (mapCriteria.get("no_checkup") != null)
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        if (mapCriteria.get("date") != null){
            String stDate = mapCriteria.get("date").toString();
            criteria.add(Restrictions.sqlRestriction("created_date = '"+stDate+"'"));
        }

        List<ItSimrsSkorRanapEntity> results = criteria.list();
        return results;
    }

    public String getNextGroupSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_group_skor_ranap')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_skor_ranap')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

}
