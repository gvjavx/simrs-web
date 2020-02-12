
package com.neurix.akuntansi.transaksi.laporanAkuntansi.dao;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.ItLaporanAkuntansiEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class LaporanAkuntansiDao extends GenericDao<ItLaporanAkuntansiEntity, String> {

    @Override
    protected Class<ItLaporanAkuntansiEntity> getEntityClass() {
        return ItLaporanAkuntansiEntity.class;
    }

    @Override
    public List<ItLaporanAkuntansiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItLaporanAkuntansiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.asc("laporanAkuntansiId"));
        List<ItLaporanAkuntansiEntity> results = criteria.list();
        return results;
    }
}
