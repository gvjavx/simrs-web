package com.neurix.simrs.master.pelayanan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class PelayananDao extends GenericDao<ImSimrsPelayananEntity, String> {

    @Override
    protected Class<ImSimrsPelayananEntity> getEntityClass() {
        return ImSimrsPelayananEntity.class;
    }

    @Override
    public List<ImSimrsPelayananEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPelayananEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_pelayanan") != null){
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }
            if (mapCriteria.get("nama_pelayanan") != null){
                criteria.add(Restrictions.ilike("namaPelayanan", "%" + (String)mapCriteria.get("nama_pelayanan") + "%"));
            }
            if(mapCriteria.get("tipe_pelayanan") != null){
                criteria.add(Restrictions.eq("tipePelayanan",  mapCriteria.get("tipe_pelayanan").toString()));
            }
            if(mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId",  mapCriteria.get("branch_id").toString()));
            }
            if(mapCriteria.get("divisi_id") != null){
                criteria.add(Restrictions.eq("divisiId",  mapCriteria.get("divisi_id").toString()));
            }
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        List<ImSimrsPelayananEntity> result = criteria.list();
        return result;
    }

    public List<Pelayanan> getListApotek(String branch, String tipeApotek){

        String SQL = "SELECT \n" +
                "id_pelayanan, \n" +
                "nama_pelayanan \n" +
                "FROM im_simrs_pelayanan\n" +
                "WHERE LIKE :tipe \n" +
                "AND branch_id = :branchId\n" +
                "AND flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
                .setParameter("tipe", tipeApotek)
                .list();

        List<Pelayanan> pelayananList = new ArrayList<>();

        if (results.size() > 0)
        {
            Pelayanan pelayanan;
            for (Object[] obj : results)
            {
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayananList.add(pelayanan);
            }
        }

        return pelayananList;
    }

    public List<Pelayanan> getListPelayananPaket(String branch){

        String SQL = "SELECT \n" +
                "id_pelayanan, \n" +
                "nama_pelayanan \n" +
                "FROM im_simrs_pelayanan\n" +
                "WHERE tipe_pelayanan IN ('rawat_jalan', 'igd')\n" +
                "AND branch_id = :branchId\n" +
                "AND flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
                .list();

        List<Pelayanan> pelayananList = new ArrayList<>();

        if (results.size() > 0)
        {
            Pelayanan pelayanan;
            for (Object[] obj : results) {
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayananList.add(pelayanan);
            }
        }

        return pelayananList;
    }

    public List<ImSimrsPelayananEntity> getDataPelayanan(String namaPelayanan) throws HibernateException {
        List<ImSimrsPelayananEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPelayananEntity.class)
                .add(Restrictions.eq("namaPelayanan", namaPelayanan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextPelayananId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pelayanan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "SG" + sId;
    }

    public String getNextKodering() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kodering')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%01d", iter.next());

        return sId;
    }

}
