
package com.neurix.hris.transaksi.rekruitmen.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.rekruitmen.model.ItRekruitmenUraianKerjaanEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class RekruitmenUraianKerjaanDao extends GenericDao<ItRekruitmenUraianKerjaanEntity, String> {

    @Override
    protected Class<ItRekruitmenUraianKerjaanEntity> getEntityClass() {
        return ItRekruitmenUraianKerjaanEntity.class;
    }

    @Override
    public List<ItRekruitmenUraianKerjaanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenUraianKerjaanEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("calon_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("calonPegawaiId", (String) mapCriteria.get("calon_pegawai_id")));
            }
/*            if (mapCriteria.get("nama_pegawai")!=null) {
                criteria.add(Restrictions.ilike("namaPegawai", "%" + (String)mapCriteria.get("nama_pegawai") + "%"));
            }
            if (mapCriteria.get("divisi")!=null) {
                criteria.add(Restrictions.eq("divisi", (String) mapCriteria.get("divisi")));
            }
            if (mapCriteria.get("branch")!=null) {
                criteria.add(Restrictions.eq("branch", (String) mapCriteria.get("branch")));
            }
            if (mapCriteria.get("tipe_pegawai")!=null) {
                criteria.add(Restrictions.eq("tipePegawai", (String) mapCriteria.get("tipe_pegawai")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }*/
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("no"));

        List<ItRekruitmenUraianKerjaanEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextRekruitmenUraianKerjaanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekruitmen_uraian_kerjaan')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String sId = String.format("%05d", iter.next());

        return "RUK"+formattedDate+sId;
    }

    public String getNextPersonalHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }


//    public void addAndSaveHistory(ItRekruitmenUraianKerjaanHistoryEntity entity) throws HibernateException {
//        this.sessionFactory.getCurrentSession().save(entity);
//
//    }
}
