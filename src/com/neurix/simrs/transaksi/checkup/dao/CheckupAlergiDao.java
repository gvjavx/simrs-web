package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.ItSImrsCheckupAlergiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 23/12/2019.
 */
public class CheckupAlergiDao extends GenericDao<ItSImrsCheckupAlergiEntity, String> {
    @Override
    protected Class<ItSImrsCheckupAlergiEntity> getEntityClass() {
        return ItSImrsCheckupAlergiEntity.class;
    }

    @Override
    public List<ItSImrsCheckupAlergiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSImrsCheckupAlergiEntity.class);
        if (mapCriteria.get("id_alergi") != null){
            criteria.add(Restrictions.eq("idAlergi", mapCriteria.get("id_alergi").toString()));
        }
        if (mapCriteria.get("no_checkup") != null){
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        }
        if (mapCriteria.get("id_pasien") != null){
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        }
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ItSImrsCheckupAlergiEntity> list = criteria.list();
        return list;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_checkup_alergi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
