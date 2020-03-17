package com.neurix.simrs.master.pasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pasien.model.ImSimrsFingerDataEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class FingerDataDao extends GenericDao<ImSimrsFingerDataEntity,String> {

    @Override
    protected Class<ImSimrsFingerDataEntity> getEntityClass() {
        return ImSimrsFingerDataEntity.class;
    }

    @Override
    public List<ImSimrsFingerDataEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    public String getNextIdFingerData(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_finger_data')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%015d", iter.next());
        return "SFD"+sId;
    }
    public List<ImSimrsFingerDataEntity> getFingerData(String fingerData) throws HibernateException {
        List<ImSimrsFingerDataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsFingerDataEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("fingerData", fingerData))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }
    public List<ImSimrsFingerDataEntity> getFingerByPasien(String idPasien) throws HibernateException {
        List<ImSimrsFingerDataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsFingerDataEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("idPasien", idPasien))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public Boolean cekFingerData(String idPasien){
        Boolean response = false;
        if(idPasien != null && !"".equalsIgnoreCase(idPasien)){
            String SQL = "SELECT id_finger_data, id_pasien \n" +
                    "FROM im_simrs_finger_data\n" +
                    "WHERE id_pasien = :id";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPasien)
                    .list();
            if(result.size() > 0){
                response = true;
            }
        }
        return response;
    }
}
