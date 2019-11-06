
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.smk.model.ItSmkEvaluasiPegawaiAspekActivityEntity;
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
public class SmkEvaluasiPegawaiAspekActivityDao extends GenericDao<ItSmkEvaluasiPegawaiAspekActivityEntity, String> {

    @Override
    protected Class<ItSmkEvaluasiPegawaiAspekActivityEntity> getEntityClass() {
        return ItSmkEvaluasiPegawaiAspekActivityEntity.class;
    }

    @Override
    public List<ItSmkEvaluasiPegawaiAspekActivityEntity> getByCriteria(Map mapCriteria) {
       return null;
    }

    // Generate surrogate id from postgre
    public String getNextSmkEvaluasi_pegawai_aspek_activity() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_evaluasi_pegawai_aspek_activity')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "EAA" + sId;
    }

    public List<ItSmkEvaluasiPegawaiAspekActivityEntity> getListAspekActivity(String evaluasiPegawaiAspekDetailId) throws HibernateException {
        List<ItSmkEvaluasiPegawaiAspekActivityEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEvaluasiPegawaiAspekActivityEntity.class)
                .add(Restrictions.eq("evaluasiPegawaiAspekDetailId", evaluasiPegawaiAspekDetailId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

}
