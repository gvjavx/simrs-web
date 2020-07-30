package com.neurix.authorization.position.dao;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.ImPositionHistory;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 0:19
 * To change this template use File | Settings | File Templates.
 */
public class PositionDao extends GenericDao<ImPosition,String> {

    @Override
    protected Class getEntityClass() {
        return ImPosition.class;
    }

    @Override
    public List<ImPosition> getByCriteria(Map mapCriteria) throws HibernateException {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("kelompok_id")!=null) {
                criteria.add(Restrictions.eq("kelompokId", (String) mapCriteria.get("kelompok_id")));
            }
            if (mapCriteria.get("bagian_id")!=null) {
                criteria.add(Restrictions.eq("bagianId", (String) mapCriteria.get("bagian_id")));
            }
            if (mapCriteria.get("department_id")!=null) {
                criteria.add(Restrictions.eq("departmentId", (String) mapCriteria.get("department_id")));
            }
            if (mapCriteria.get("position_name")!=null) {
                criteria.add(Restrictions.ilike("positionName", "%" + (String)mapCriteria.get("position_name") + "%" ));
            }
            if (mapCriteria.get("kodering")!=null) {
                criteria.add(Restrictions.eq("kodering", (String) mapCriteria.get("kodering")));
            }
            if (mapCriteria.get("kategori")!=null) {
                criteria.add(Restrictions.eq("kategori", (String) mapCriteria.get("kategori")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.desc("positionId"));
//        criteria.addOrder(Order.asc("departmentId"));
//        criteria.addOrder(Order.asc("bagianId"));
//        criteria.addOrder(Order.asc("kelompokId"));

        List<ImPosition> results = criteria.list();

        return results;
    }

    public List<ImPosition> getListDivisi(String term) throws HibernateException {

        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("positionId", term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();

        return results;
    }

    public List<ImPosition> getListPosition(String term) throws HibernateException {

        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.ilike("positionName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();

        return results;
    }

    public List<ImPosition> getListPositionKodering(String term) throws HibernateException {

        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.ilike("kodering",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();

        return results;
    }

    public List<ImPosition> getListPositionByCriteria(String postionName, String department, String bagian, String kelompok){
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("positionName",postionName))
                .add(Restrictions.eq("departmentId",department))
                .add(Restrictions.eq("bagianId",bagian))
                .add(Restrictions.eq("kelompokId", kelompok))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();

        return results;
    }

    public List<ImPosition> getListPositionSppd(String positionId) throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();
        return results;
    }

    public List<ImPosition> getPositionById (String positionId) throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();
        return results;
    }

    public String getNextPosition() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_position')");
        Iterator<BigInteger> iter=query.list().iterator();
        return iter.next() + "";
    }

    public void addAndSaveHistory(ImPositionHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImPosition> getDataPosisi(String branchId, String divisiId){
        String unit = "";
        String bagian = "";

        if(!branchId.equalsIgnoreCase("")){
            unit = " and branch_id = '"+branchId+"'  ";
        }
        if(!divisiId.equalsIgnoreCase("")){
            bagian = " and department_id = '"+divisiId+"' ";
        }

        List<ImPosition> listOfResult = new ArrayList<ImPosition>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select DISTINCT \n" +
                "\tim_position.position_id,\n" +
                "\tim_position.position_name\n" +
                "from\n" +
                "\tim_position, it_hris_pegawai_position\n" +
                "where\n" +
                "\tbranch_id is not null\n" + unit + bagian + "\n" +
                "\tand nip is not null\n" +
                "\tand im_position.position_id = it_hris_pegawai_position.position_id\n" +
                "\torder by position_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPosition result  = new ImPosition();
            result.setPositionId((String) row[0]);
            result.setPositionName((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }
    public List<ImPosition> getDataPosisi2(String branchId, String divisiId){
        String unit = "";
        String bagian = "";

        if(!branchId.equalsIgnoreCase("")){
            unit = " and branch_id = '"+branchId+"'  ";
        }
        if(!divisiId.equalsIgnoreCase("")){
            bagian = "department_id = '"+divisiId+"' ";
        }

        List<ImPosition> listOfResult = new ArrayList<ImPosition>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select DISTINCT \n" +
                "\tposition_id,\n" +
                "\tposition_name\n" +
                "from\n" +
                "\tim_position\n" +
                "where\n" + bagian + " and flag='Y'\n" +
                "\torder by position_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPosition result  = new ImPosition();
            result.setPositionId((String) row[0]);
            result.setPositionName((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<Position> getDataDevisi(String branchId){
        String unit = "";
        String bagian = "";

        if(!branchId.equalsIgnoreCase("")){
            unit = " and branch_id = '"+branchId+"'  ";
        }

        List<Position> listOfResult = new ArrayList<Position>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select DISTINCT \n" +
                "\tdepartment_id,\n" +
                "\tdepartment_name\n" +
                "from\n" +
                "\tim_hris_department where flag='Y'\n" +
                "order by department_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Position result  = new Position();
            result.setDepartmentId((String) row[0]);
            result.setDepartmentName((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPosition> getDataPosisiBiodata(String divisiId){

        String bagian = "";


        if(!divisiId.equalsIgnoreCase("")){
            bagian = " and department_id = '"+divisiId+"' ";
        }

        List<ImPosition> listOfResult = new ArrayList<ImPosition>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposition_id,\n" +
                "\tposition_name\n" +
                "from\n" +
                "\tim_position\n" +
                "where\n" +
                "\tposition_id is not null\n" + bagian + "\n" +
                "order by\n" +
                "\tposition_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPosition result  = new ImPosition();
            result.setPositionId((String) row[0]);
            result.setPositionName((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }
    public List<ImPosition> getDataPosisiBiodataHistory(String divisiId){

        String bagian = "";


        if(!divisiId.equalsIgnoreCase("")){
            bagian = " and department_id = '"+divisiId+"' ";
        }

        List<ImPosition> listOfResult = new ArrayList<ImPosition>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposition_id,\n" +
                "\tposition_name\n" +
                "from\n" +
                "\tim_position\n" +
                "where\n" +
                "\tposition_id is not null\n" + bagian + "\n" +
                "order by\n" +
                "\tposition_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPosition result  = new ImPosition();
            result.setPositionId((String) row[0]);
            result.setPositionName((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPosition> getDataPosisi(String branchId){
        String unit = "";

        if(!branchId.equalsIgnoreCase("")){
            unit = " and branch_id = '"+branchId+"'  ";
        }

        List<ImPosition> listOfResult = new ArrayList<ImPosition>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select DISTINCT \n" +
                "\tposition_id,\n" +
                "\tposition_name\n" +
                "from\n" +
                "\tstruktur_jabatan\n" +
                "where\n" +
                "\tbranch_id is not null\n" + unit + " order by position_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPosition result  = new ImPosition();
            result.setPositionId((String) row[0]);
            result.setPositionName((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPosition> getListPositionDekom () throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("bagianId", "PB02"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();
        return results;
    }

    public List<ImPosition> getListPositionSelainDekomDanDirkom () throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(
                        Restrictions.or(
                                Restrictions.or(
                                        Restrictions.ne("bagianId", "PB01"),
                                        Restrictions.ne("bagianId", "PB02")
                                ),
                                Restrictions.ne("bagianId", "PB03")
                                )
                )
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();
        return results;
    }
    public List<ImPosition> getListPositionDireksi () throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(
                        Restrictions.or(
                                Restrictions.eq("bagianId", "PB01"),
                                Restrictions.eq("bagianId", "PB03")
                        )
                )
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();
        return results;
    }

    public List<ImPosition> getTypeAheadPosition (String key) throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(
                        Restrictions.or(
                                Restrictions.ilike("kodering", key + "%"),
                                Restrictions.ilike("positionName", "%" + key + "%")
                        )
                )
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();
        return results;
    }

    public String getKodringPosition(Map mapCriteria){
        String kode = null;
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class);

        if (mapCriteria != null){
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImPosition> results = criteria.list();

        if (results != null){
            for (ImPosition position : results){
                kode = position.getKodering();
            }
        }

        return kode;
    }

    public List<ImPosition> getListPositionKoderingNKelompokPosition(String term,String kelompok) throws HibernateException {

        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.ilike("kodering", term))
                .add(Restrictions.eq("kelompokId", kelompok))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();

        return results;
    }

    public List<ImPosition> getDataKelompokId(String positionId){

        List<ImPosition> listOfResult = new ArrayList<ImPosition>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT kelompok_id, position_id FROM im_position WHERE position_id = '"+positionId+"' AND flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPosition result  = new ImPosition();
            result.setKelompokId((String) row[0]);
            result.setPositionId((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPosition> getPositionBodBoc() {
        String idBod = "KL44";
        String idBoc = "KL43";
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.eq("kelompokId", "" + idBod + ""),
                        Restrictions.eq("kelompokId", "" + idBoc + "")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("positionId"));

        List<ImPosition> results = criteria.list();
        return results;
    }
}