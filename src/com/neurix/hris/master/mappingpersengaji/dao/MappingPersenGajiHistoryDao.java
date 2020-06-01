package com.neurix.hris.master.mappingpersengaji.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.mappingpersengaji.model.ImHrisMappingPersenGaji;
import com.neurix.hris.master.mappingpersengaji.model.ImHrisMappingPersenGajiHistory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MappingPersenGajiHistoryDao extends GenericDao<ImHrisMappingPersenGajiHistory, String> {
    @Override
    protected Class<ImHrisMappingPersenGajiHistory> getEntityClass() {
        return ImHrisMappingPersenGajiHistory.class;
    }

    @Override
    public List<ImHrisMappingPersenGajiHistory> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImHrisMappingPersenGaji.class);
        if (mapCriteria != null){
            if (mapCriteria.get("mapping_persen_gaji_id") != null){
                criteria.add(Restrictions.eq("mappingPersenGajiId", mapCriteria.get("mapping_persen_gaji_id").toString()));
            }
            if (mapCriteria.get("nama_mapping_persen_gaji") != null){
                criteria.add(Restrictions.eq("namaMappingPersenGaji", mapCriteria.get("nama_mapping_persen_gaji").toString()));
            }
            if (mapCriteria.get("jenis_gaji") != null){
                criteria.add(Restrictions.eq("jenisGaji", mapCriteria.get("jenis_gaji").toString()));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        List<ImHrisMappingPersenGajiHistory> result = criteria.list();
        return result;
    }

    public String getNextMappingHistory() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mapping_persen_gaji_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "MPGH" + sId;
    }
}