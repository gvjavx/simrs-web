
package com.neurix.hris.transaksi.rekruitmen.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.rekruitmen.model.ItRekruitmenStatusEntity;
import com.neurix.hris.transaksi.rekruitmen.model.ImRekruitmenHistoryEntity;
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
public class RekruitmenStatusDao extends GenericDao<ItRekruitmenStatusEntity, String> {

    @Override
    protected Class<ItRekruitmenStatusEntity> getEntityClass() {
        return ItRekruitmenStatusEntity.class;
    }

    @Override
    public List<ItRekruitmenStatusEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenStatusEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("calon_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("calonPegawaiId", (String) mapCriteria.get("calon_pegawai_id")));
            }
            if (mapCriteria.get("status_rekruitmen")!=null) {
                criteria.add(Restrictions.eq("statusRekruitmen", (String) mapCriteria.get("status_rekruitmen")));
            }
/*            if (mapCriteria.get("nama_pegawai")!=null) {
                criteria.add(Restrictions.ilike("namaPegawai", "%" + (String)mapCriteria.get("nama_pegawai") + "%"));
            }
            if (mapCriteria.get("divisi")!=null) {
                criteria.add(Restrictions.eq("divisi", (String) mapCriteria.get("divisi")));
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
        criteria.addOrder(Order.desc("tanggalProses"));

        List<ItRekruitmenStatusEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextRekruitmenStatusId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekruitmen_status')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String sId = String.format("%06d", iter.next());

        return "CPS"+formattedDate+sId;
    }

    public String getNextPersonalHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ItRekruitmenStatusEntity> getListPersonal(String term) throws HibernateException {

        List<ItRekruitmenStatusEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenStatusEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }
    public List<ItRekruitmenStatusEntity> getListPersonalByBranch(String term, String branch) throws HibernateException {

        List<ItRekruitmenStatusEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenStatusEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public List<ItRekruitmenStatusEntity> getListPersonalByBranch2(String term) throws HibernateException {
        List<ItRekruitmenStatusEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenStatusEntity.class)
                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.ilike("nip",term))
                //.add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImRekruitmenHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }
    public List<ItRekruitmenStatusEntity> getStatusAkhir(String term) throws HibernateException {
        List<ItRekruitmenStatusEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenStatusEntity.class)
                .add(Restrictions.eq("calonPegawaiId", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("statusRekruitmen","10"))
                .addOrder(Order.desc("tanggalProses"))
                .setMaxResults(1)
                .list();

        return results;
    }
    public List<ItRekruitmenStatusEntity> getStatusSaatIni(String term) throws HibernateException {
        List<ItRekruitmenStatusEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenStatusEntity.class)
                .add(Restrictions.eq("calonPegawaiId", term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("tanggalProses"))
                .setMaxResults(1)
                .list();

        return results;
    }
}
