
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.payroll.model.ImPayrollBpjsEntity;
import com.neurix.hris.transaksi.payroll.model.ImPayrollBpjsEntity;
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
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PayrollBpjsDao extends GenericDao<ImPayrollBpjsEntity, String> {

    @Override
    protected Class<ImPayrollBpjsEntity> getEntityClass() {
        return ImPayrollBpjsEntity.class;
    }

    @Override
    public List<ImPayrollBpjsEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollBpjsEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("bpjs_id")!=null) {
                criteria.add(Restrictions.eq("bpjsId", (String) mapCriteria.get("bpjs_id")));
            }

            if (mapCriteria.get("bpjs_kesehatan_persen")!=null) {
                criteria.add(Restrictions.eq("bpjsKesehatanPersen", (String) mapCriteria.get("bpjs_kesehatan_persen")));
            }
            
            if (mapCriteria.get("bpjs_pensiun_persen")!=null) {
                criteria.add(Restrictions.eq("bpjsKesehatanPersen", (String) mapCriteria.get("bpjs_kesehatan_persen")));
            }
            
            if (mapCriteria.get("bpjs_jht_persen")!=null) {
                criteria.add(Restrictions.eq("bpjsKesehatanPersen", (String) mapCriteria.get("bpjs_kesehatan_persen")));
            }
            
        }

        // Order by
        criteria.addOrder(Order.desc("bpjs_id"));

        List<ImPayrollBpjsEntity> results = criteria.list();

        return results;
    }

    public List<ImPayrollBpjsEntity> getPersenBpjs() throws HibernateException {
        List<ImPayrollBpjsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollBpjsEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
    
}
