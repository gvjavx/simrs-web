package com.neurix.simrs.transaksi.resikojatuh.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.resikojatuh.model.ItSImrsResikoJatuhEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 05/02/20.
 */
public class ResikoJatuhDao extends GenericDao<ItSImrsResikoJatuhEntity, String> {

    @Override
    protected Class<ItSImrsResikoJatuhEntity> getEntityClass() {
        return ItSImrsResikoJatuhEntity.class;
    }

    @Override
    public List<ItSImrsResikoJatuhEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSImrsResikoJatuhEntity.class);

        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("no_checkup") != null){
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        }

        if (mapCriteria.get("id_parameter") != null){
            criteria.add(Restrictions.eq("idParameter", mapCriteria.get("id_parameter").toString()));
        }
        if (mapCriteria.get("id_kategori") != null){
            criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
        }

        criteria.addOrder(Order.asc("id"));
        List<ItSImrsResikoJatuhEntity> result = criteria.list();
        return result;
    }

    public String getSumOfResikoJatuh(String noCheckup, String idKategori){

        String SQL = "SELECT no_checkup, SUM(skor) as skor FROM it_simrs_resiko_jatuh\n" +
                "WHERE \n" +
                "no_checkup = :id\n" +
                "AND id_kategori = :kat\n" +
                "GROUP BY no_checkup";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id" , noCheckup)
                .setParameter("kat", idKategori)
                .list();


        String sumSkor = "";
        if (results.size() > 0){
            for (Object[] obj : results){
                BigInteger skor = (BigInteger) obj[1];
                sumSkor = skor.toString();
            }
        }

        return sumSkor;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_resiko_jatuh')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
