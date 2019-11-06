
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.smk.model.ItHistorySmkEvaluasiPegawaiEntity;
import com.neurix.hris.transaksi.smk.model.ItHistorySmkEvaluasiPegawaiEntity;
import com.neurix.hris.transaksi.smk.model.ItSmkEvaluasiPegawaiAspekDetailEntity;
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
public class SmkHistoryDao extends GenericDao<ItHistorySmkEvaluasiPegawaiEntity, String> {

    @Override
    protected Class<ItHistorySmkEvaluasiPegawaiEntity> getEntityClass() {
        return ItHistorySmkEvaluasiPegawaiEntity.class;
    }

    @Override
    public List<ItHistorySmkEvaluasiPegawaiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHistorySmkEvaluasiPegawaiEntity.class);
        List<ItHistorySmkEvaluasiPegawaiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSmkId(String per) throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_it_hris_history_smk_evaluasi_pegawai')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = iter.next() + "";

        return sId;
    }


}
