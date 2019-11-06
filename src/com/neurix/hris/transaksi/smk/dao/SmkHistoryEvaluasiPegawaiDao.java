
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.smk.model.ItSmkEvaluasiPegawaiAspekEntity;
import com.neurix.hris.transaksi.smk.model.ItSmkHistoryEvaluasiPegawaiEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
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
public class SmkHistoryEvaluasiPegawaiDao extends GenericDao<ItSmkHistoryEvaluasiPegawaiEntity, String> {

    @Override
    protected Class<ItSmkHistoryEvaluasiPegawaiEntity> getEntityClass() {
        return ItSmkHistoryEvaluasiPegawaiEntity.class;
    }

    @Override
    public List<ItSmkHistoryEvaluasiPegawaiEntity> getByCriteria(Map mapCriteria) {
       return null;
    }

    // Generate surrogate id from postgre
    public String getNextHistory() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_evaluasi_pegawai_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "HE" + sId;
    }

    public List<ItSmkHistoryEvaluasiPegawaiEntity> getHistorySmk(String nip) throws HibernateException {
        List<ItSmkHistoryEvaluasiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkHistoryEvaluasiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .list();
        return results;
    }
    
}
