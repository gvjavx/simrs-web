
package com.neurix.hris.transaksi.personilPosition.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
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
public class PersonilPositionDao extends GenericDao<ItPersonilPositionEntity, String> {

    @Override
    protected Class<ItPersonilPositionEntity> getEntityClass() {
        return ItPersonilPositionEntity.class;
    }

    @Override
    public List<ItPersonilPositionEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPersonilPositionEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.asc("personilPositionId"));

        List<ItPersonilPositionEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPersonilPositionId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personil_position')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "PP"+sId;
    }

    public List<ItPersonilPositionEntity> getListPersonilPosition(String term) throws HibernateException {
        List<ItPersonilPositionEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPersonilPositionEntity.class)
                .add(Restrictions.eq("nip", term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("personilPositionId"))
                .list();
        return results;
    }

    public List<ItPersonilPositionEntity> getListNip(String nip) throws HibernateException {
        List<ItPersonilPositionEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPersonilPositionEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("personilPositionId"))
                .list();
        return results;
    }

    public List<ItPersonilPositionEntity> getListPersonilPosition(String branch, String divisi, String position) throws HibernateException {
        List<ItPersonilPositionEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPersonilPositionEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("branchId", branch))
                .add(Restrictions.eq("positionId", position))
                .addOrder(Order.asc("personilPositionId"))
                .list();
        return results;
    }

    public List<ItPersonilPositionEntity> getPosisi(String nip, String position) throws HibernateException {
        List<ItPersonilPositionEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPersonilPositionEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("positionId", position))
                .addOrder(Order.asc("personilPositionId"))
                .list();
        return results;
    }

    public List<ItPersonilPositionEntity> getDataPersonil(String branchId, String nip){
        List<ItPersonilPositionEntity> listOfResult = new ArrayList<ItPersonilPositionEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tstruktur.* \n" +
                "from \n" +
                "\tstruktur_jabatan struktur \n" +
                "where\n" +
                "\tnip = '"+nip+"'" +
                " and branch_id = '"+branchId+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItPersonilPositionEntity result  = new ItPersonilPositionEntity();
            result.setBranchName((String)(row[4]));
            result.setPositionId((String)(row[5]));
            result.setPositionName((String)(row[6]));

            listOfResult.add(result);
        }
        return listOfResult;
    }
    public List<ItPersonilPositionEntity> getPersonilPosition(String nip){
        List<ItPersonilPositionEntity> listOfResult = new ArrayList<ItPersonilPositionEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tstruktur.* \n" +
                "from \n" +
                "\tstruktur_jabatan struktur \n" +
                "where\n" +
                "\tnip = '"+nip+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPersonilPositionEntity result  = new ItPersonilPositionEntity();
            result.setPositionId((String)(row[5]));
            result.setPositionName((String)(row[6]));
            listOfResult.add(result);
        }
        return listOfResult;
    }
    public Integer getKelompokPosition(String nip){
        String stKelompok="";
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "\tposition.kelompok_id,\n" +
                "\tposition.position_id\n" +
                "FROM \n" +
                "\tit_hris_pegawai_position posisi LEFT JOIN \n" +
                "\tim_position position ON posisi.position_id=position.position_id\n" +
                "WHERE\n" +
                "\tnip='"+nip+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            stKelompok=(String)(row[0]);
        }

        return Integer.parseInt(stKelompok.replace("KL",""));
    }

    public List<ItPersonilPositionEntity> getPersonilPositionByUnitdanPosisi(String branch, String position) throws HibernateException {
        List<ItPersonilPositionEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPersonilPositionEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("branchId", branch))
                .add(Restrictions.eq("positionId", position))
                .addOrder(Order.asc("personilPositionId"))
                .list();
        return results;
    }
}
