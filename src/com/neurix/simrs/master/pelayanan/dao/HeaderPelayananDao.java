package com.neurix.simrs.master.pelayanan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsHeaderPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HeaderPelayananDao extends GenericDao<ImSimrsHeaderPelayananEntity, String> {

    @Override
    protected Class<ImSimrsHeaderPelayananEntity> getEntityClass() {
        return ImSimrsHeaderPelayananEntity.class;
    }

    @Override
    public List<ImSimrsHeaderPelayananEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsHeaderPelayananEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_header_pelayanan") != null){
                criteria.add(Restrictions.eq("idHeaderPelayanan", mapCriteria.get("id_header_pelayanan").toString()));
            }
            if (mapCriteria.get("nama_pelayanan") != null){
                criteria.add(Restrictions.ilike("namaPelayanan", "%" + (String)mapCriteria.get("nama_pelayanan") + "%"));
            }
            if(mapCriteria.get("tipe_pelayanan") != null){
                criteria.add(Restrictions.eq("tipePelayanan",  mapCriteria.get("tipe_pelayanan").toString()));
            }
            if(mapCriteria.get("divisi_id") != null){
                criteria.add(Restrictions.eq("divisiId",  mapCriteria.get("divisi_id").toString()));
            }
            if (mapCriteria.get("kode_vclaim") != null){
                criteria.add(Restrictions.eq("kodeVclaim", mapCriteria.get("kode_vclaim")));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }

        List<ImSimrsHeaderPelayananEntity> result = criteria.list();
        return result;
    }

    public List<ImSimrsHeaderPelayananEntity> getData(String namaPelayanan) throws HibernateException {
        List<ImSimrsHeaderPelayananEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsHeaderPelayananEntity.class)
                .add(Restrictions.ilike("namaPelayanan", namaPelayanan))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ImSimrsPelayananEntity> getDataPelayanan(String idHeaderPelayanan) throws HibernateException {
        List<ImSimrsPelayananEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPelayananEntity.class)
                .add(Restrictions.eq("idHeaderPelayanan", idHeaderPelayanan))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_header_pelayanan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "HPN" + sId;
    }
}
