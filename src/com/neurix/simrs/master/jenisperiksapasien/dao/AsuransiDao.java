package com.neurix.simrs.master.jenisperiksapasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AsuransiDao extends GenericDao<ImSimrsAsuransiEntity, String> {

    @Override
    protected Class<ImSimrsAsuransiEntity> getEntityClass() {
        return ImSimrsAsuransiEntity.class;
    }

    @Override
    public List<ImSimrsAsuransiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsAsuransiEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_asuransi") != null) {
                criteria.add(Restrictions.eq("idAsuransi", mapCriteria.get("id_asuransi").toString()));
            }
            if (mapCriteria.get("nama_asuransi") != null) {
                criteria.add(Restrictions.ilike("namaAsuransi", "%" + (String)mapCriteria.get("nama_asuransi") + "%"));
            }
            if (mapCriteria.get("no_master") != null) {
                criteria.add(Restrictions.eq("noMaster", mapCriteria.get("no_master").toString()));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }

            if(mapCriteria.get("is_laka") != null){
                criteria.add(Restrictions.eq("isLaka", mapCriteria.get("is_laka")));
            }
        }
        List<ImSimrsAsuransiEntity> result = criteria.list();
        return result;
    }

    public List<ImSimrsAsuransiEntity> getDataAsuransi(String noMaster,String idAsuransi) throws HibernateException {
        List<ImSimrsAsuransiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsAsuransiEntity.class)
                .add(Restrictions.eq("noMaster", noMaster))

                .add(Restrictions.ne("idAsuransi", idAsuransi))
                .add(Restrictions.eq("isLaka", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)

        return results;
    }

    public List<ImSimrsAsuransiEntity> cekData(String idAsuransi) throws HibernateException{
        List<ImSimrsAsuransiEntity> results = new ArrayList<>();

        String query = "SELECT a.id_asuransi, b.id_detail_checkup\n" +
                "FROM im_simrs_asuransi a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON b.id_asuransi = a.id_asuransi\n" +
                "WHERE a.id_asuransi = '"+idAsuransi+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    public String getNextAsuransuId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asuransi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "ASN" + sId;
    }
}
