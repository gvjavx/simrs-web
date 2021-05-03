package com.neurix.hris.master.smkPersenSmkNilai.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkPersenSmkNilai.model.ImSmkPersenSmkNilaiEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkPersenSmkNilaiDao extends GenericDao<ImSmkPersenSmkNilaiEntity, String> {


    @Override
    protected Class<ImSmkPersenSmkNilaiEntity>  getEntityClass() {
        return ImSmkPersenSmkNilaiEntity.class;
    }

    @Override
    public List<ImSmkPersenSmkNilaiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkPersenSmkNilaiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("persen_id")!=null) {
                criteria.add(Restrictions.eq("smkNilaiId", (String) mapCriteria.get("umk_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("nama")!=null) {
                criteria.add(Restrictions.ilike("nama ", "%" + (String) mapCriteria.get("nama") + "%"));
            }

            if (mapCriteria.get("nilai_atas")!=null) {
                criteria.add(Restrictions.eq("nilaiAtas", (String) mapCriteria.get("nilai_atas")));
            }

            if (mapCriteria.get("nilai_bawah")!=null) {
                criteria.add(Restrictions.eq("nilaiBawah", (String) mapCriteria.get("nilai_bawah")));
            }
            
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.desc("smkNilaiId"));

        List<ImSmkPersenSmkNilaiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPersenNilaiSmk() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_persen_nilai_smk')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "PSN" + sId;
    }

    public List<ImSmkPersenSmkNilaiEntity> getListPersen(String branch) throws HibernateException {

        List<ImSmkPersenSmkNilaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSmkPersenSmkNilaiEntity.class)
                .add(Restrictions.eq("branchId", branch))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}
