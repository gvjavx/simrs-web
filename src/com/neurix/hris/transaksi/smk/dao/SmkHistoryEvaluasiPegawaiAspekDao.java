
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.smk.model.ItHistorySmkEvaluasiPegawaiAspekEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
public class SmkHistoryEvaluasiPegawaiAspekDao extends GenericDao<ItHistorySmkEvaluasiPegawaiAspekEntity, String> {

    @Override
    protected Class<ItHistorySmkEvaluasiPegawaiAspekEntity> getEntityClass() {
        return ItHistorySmkEvaluasiPegawaiAspekEntity.class;
    }

    @Override
    public List<ItHistorySmkEvaluasiPegawaiAspekEntity> getByCriteria(Map mapCriteria) {
       return null;
    }

    // Generate surrogate id from postgre
    public String getNextSmkEvaluasiPegawaiAspekId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_it_hris_history_smk_evaluasi_pegawai_aspek')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = iter.next() +"";

        return sId;
    }

}
