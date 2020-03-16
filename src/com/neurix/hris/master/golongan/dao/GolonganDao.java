package com.neurix.hris.master.golongan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.golongan.model.Golongan;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.golongan.model.ImGolonganHistoryEntity;
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
public class GolonganDao extends GenericDao<ImGolonganEntity, String> {

    @Override
    protected Class<ImGolonganEntity> getEntityClass() {
        return ImGolonganEntity.class;
    }

    @Override
    public List<ImGolonganEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImGolonganEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }
            if (mapCriteria.get("golongan_name")!=null) {
                criteria.add(Restrictions.ilike("golonganName", "%" + (String)mapCriteria.get("golongan_name") + "%"));
            }
            if (mapCriteria.get("grade_level")!=null) {
                criteria.add(Restrictions.eq("level", (Integer) mapCriteria.get("grade_level")));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("golonganId"));

        List<ImGolonganEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextGolonganId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_golongan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "G"+sId;
    }

    // Generate surrogate id from postgre
    public String getNextGolonganHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_golongan_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImGolonganEntity> getListGolongan(String term) throws HibernateException {

        List<ImGolonganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImGolonganEntity.class)
                .add(Restrictions.ilike("golonganName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganId"))
                .list();

        return results;
    }
    public List<ImGolonganEntity> cekGolongan(Integer term) throws HibernateException {

        List<ImGolonganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImGolonganEntity.class)
                .add(Restrictions.ilike("level",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganId"))
                .list();

        return results;
    }
    public void addAndSaveHistory(ImGolonganHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }
    public List<ImGolonganEntity> getGolonganById(String golonganId) throws HibernateException {

        List<ImGolonganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImGolonganEntity.class)
                .add(Restrictions.ilike("golonganId",golonganId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganId"))
                .list();
        return results;
    }
    public List<Golongan> getGolonganByNip(String nip){
        List<Golongan> listOfResult = new ArrayList<>();
        String query ="select im_hris_golongan.golongan_id, im_hris_golongan.golongan_name\n" +
                "from im_hris_golongan, im_hris_pegawai\n" +
                "where im_hris_pegawai.golongan_id = im_hris_golongan.golongan_id\n" +
                "and im_hris_pegawai.nip='"+nip+"'";

        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        Golongan golongan;
        for (Object[] row: results){
            golongan = new Golongan();
            golongan.setGolonganId(row[0].toString());
            golongan.setGolonganName(row[1].toString());
            listOfResult.add(golongan);
        }
        return listOfResult;
    }

    public String getStatus(Integer level){
        String status ="";
        String query = "select golongan_name from im_hris_golongan where grade_level ='"+level+"' and flag ='Y'";
        Object results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            status = "Exist";
        }else{
            status="NotExist";
        }
        return status;
    }

}
