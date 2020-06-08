package com.neurix.simrs.master.tindakanicd9.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakanicd9.model.ImSimrsTindakanICD9Entity;
import com.neurix.simrs.master.tindakanicd9.model.TindakanICD9;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TindakanICD9Dao extends GenericDao<ImSimrsTindakanICD9Entity, String> {
    @Override
    protected Class<ImSimrsTindakanICD9Entity> getEntityClass() {
        return ImSimrsTindakanICD9Entity.class;
    }

    @Override
    public List<ImSimrsTindakanICD9Entity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanICD9Entity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_icd9") != null) {
                criteria.add(Restrictions.eq("idIcd9", mapCriteria.get("id_icd9").toString()));
            }
            if (mapCriteria.get("nama_icd9") != null){
                criteria.add(Restrictions.ilike("namaIcd9", "%" + (String)mapCriteria.get("nama_icd9") + "%"));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }
        List<ImSimrsTindakanICD9Entity> result = criteria.list();
        return result;
    }

    public List<TindakanICD9> getSearchICD9(String key){
        List<TindakanICD9> tindakanICD9List = new ArrayList<>();
        if(!"".equalsIgnoreCase(key) && key != null){
            String id = "%"+key+"%";
            String SQL = "SELECT \n" +
                    "id_icd9, \n" +
                    "nama_icd9 \n" +
                    "FROM im_simrs_icd9\n" +
                    "WHERE id_icd9 ILIKE :id OR nama_icd9 ILIKE :id\n" +
                    "ORDER BY id_icd9 ASC";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .list();

            if(result.size() > 0){
                for (Object[] obj: result){
                    TindakanICD9 tindakanICD9 = new TindakanICD9();
                    tindakanICD9.setIdIcd9(obj[0] == null ? "" : obj[0].toString());
                    tindakanICD9.setNamaIcd9(obj[1] == null ? "" : obj[1].toString());
                    tindakanICD9List.add(tindakanICD9);
                }
            }

        }
        return tindakanICD9List;
    }

    public String getNextPelayananId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tindakan_icd9')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
