package com.neurix.authorization.position.dao;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.ImPositionHistory;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.positionBagian.action.PositionBagianAction;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
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
            if (mapCriteria.get("flag_cost_unit")!=null) {
                if(!"all".equalsIgnoreCase((String) mapCriteria.get("flag_cost_unit"))) {
                    criteria.add(Restrictions.eq("flagCostUnit", (String) mapCriteria.get("flag_cost_unit")));
                }else if("N".equalsIgnoreCase((String) mapCriteria.get("flag_cost_unit"))){
                    criteria.add(Restrictions.or(Restrictions.eq("flagCostUnit", "N"),Restrictions.isNull("flagCostUnit")));
                }
            }else{
                criteria.add(Restrictions.or(Restrictions.eq("flagCostUnit", "N"),Restrictions.isNull("flagCostUnit")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.asc("kodering"));
        criteria.addOrder(Order.desc("flagCostUnit"));
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
                .addOrder(Order.asc("kodering"))
                .setMaxResults(1)
                .list();

        return results;
    }

    public List<ImPosition> getListPosition(String term) throws HibernateException {

        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.ilike("positionName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kodering"))
                .list();

        return results;
    }

    public List<ImPosition> getListPositionKodering(String term) throws HibernateException {

        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.ilike("kodering",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kodering"))
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
                .addOrder(Order.asc("kodering"))
                .list();

        return results;
    }

    public List<ImPosition> getListPositionSppd(String positionId) throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kodering"))
                .list();
        return results;
    }

    public List<ImPosition> getPositionById (String positionId) throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kodering"))
                .list();
        return results;
    }

    public String getNextPosition() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_position')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        String output =  "PS" + sId + "";
        return output;
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
                "\tim_position.position_name,\n" +
                "\tkodering\n" +
                "from\n" +
                "\tim_position, it_hris_pegawai_position\n" +
                "where\n" +
                "\tbranch_id is not null\n" + unit + bagian + "\n" +
                "\tand nip is not null\n" +
                "\tand im_position.position_id = it_hris_pegawai_position.position_id\n" +
                "\torder by kodering";

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
        String query = "select\n" +
                "\tposition_id,\n" +
                "\tposition_name,\n" +
                "\tkodering\n" +
                "from\n" +
                "\tim_position\n" +
                "where\n" + bagian + " and flag='Y'\n" +
                "\tand (flag_cost_unit IS NULL or flag_cost_unit != 'Y')\n" +
                "\torder by kodering";

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
        String query = "select \n" +
                "\tdepartment_id,\n" +
                "\tdepartment_name,\n" +
                "\tkodering\n" +
                "from\n" +
                "\tim_hris_department where flag='Y'\n" +
                "order by kodering";

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
                "\tposition_name,\n" +
                "\tkodering\n" +
                "from\n" +
                "\tim_position\n" +
                "where\n" +
                "\tposition_id is not null\n" + bagian + "\n" +
                "\tAND (flag_cost_unit = 'N' OR flag_cost_unit IS NULL) \n" +
                "order by\n" +
                "\tkodering";

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
                "\tposition_name,\n" +
                "\tkodering\n" +
                "from\n" +
                "\tim_position\n" +
                "where\n" +
                "\tposition_id is not null\n" + bagian + "\n" +
                "\tAND flag_cost_unit = 'N' OR flag_cost_unit IS NULL \n" +
                "order by\n" +
                "\tkodering";

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
                "\tposition_name,\n" +
                "\tkodering\n" +
                "from\n" +
                "\tstruktur_jabatan\n" +
                "where\n" +
                "\tbranch_id is not null\n" + unit + " order by kodering";

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
                .add(Restrictions.eq("bagianId", CommonConstant.BAGIAN_ID_BOD_BOC))
                .add(Restrictions.eq("kelompokId", CommonConstant.KELOMPOK_ID_BOC))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kodering"))
                .list();
        return results;
    }

    public List<ImPosition> getListPositionSelainDekomDanDirkom () throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.ne("bagianId", CommonConstant.BAGIAN_ID_BOD_BOC))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kodering"))
                .list();
        return results;
    }
    public List<ImPosition> getListPositionDireksi () throws HibernateException {
        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("bagianId", CommonConstant.BAGIAN_ID_BOD_BOC))
                .add(Restrictions.eq("kelompokId", CommonConstant.KELOMPOK_ID_BOD))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kodering"))
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
                .addOrder(Order.asc("kodering"))
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
                .addOrder(Order.asc("kodering"))
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
        criteria.addOrder(Order.asc("kodering"));

        List<ImPosition> results = criteria.list();
        return results;
    }

    public List<ImPosition> getListByKelompokId(String kelompok) throws HibernateException {

        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("kelompokId", kelompok))
                .addOrder(Order.asc("kodering"))
                .list();

        return results;
    }

    public List<ImPosition> getListByBagianId(String id) throws HibernateException {

        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.eq("bagianId", id))
                .addOrder(Order.asc("kodering"))
                .list();
        return results;
    }

    public List<ImPosition> getListPositionBagianByDivisi(String id) throws HibernateException {

        List<ImPosition> results = this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class)
                .add(Restrictions.ilike("departmentId",id))
                .list();

        return results;
    }

    public boolean checkIsMultiplePersonByPositionId(String positionId){

        String SQL = "SELECT \n" +
                "a.position_id, b.kelompok_id, a.position_name \n" +
                "FROM im_position a\n" +
                "INNER JOIN (\n" +
                "\tSELECT * FROM im_hris_kelompok_position WHERE flag = 'Y' AND flag_is_multiple_person = 'Y'\n" +
                ") b ON b.kelompok_id = a.kelompok_id\n" +
                "WHERE a.position_id = :positionid ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("positionid", positionId)
                .list();

        if (results.size() > 0)
            return true;
        return false;
    }

    public PersonilPosition getPersonilPositionAktif(String branchId, String positionId){

        String SQL = "SELECT a.nip, b.nama_pegawai \n" +
                "FROM (SELECT * FROM it_hris_pegawai_position WHERE flag = 'Y') a\n" +
                "INNER JOIN (SELECT nip, nama_pegawai FROM im_hris_pegawai WHERE flag = 'Y') b ON b.nip = a.nip\n" +
                "WHERE a.position_id LIKE :positionid \n" +
                "AND a.branch_id LIKE :unit ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("positionid", positionId)
                .setParameter("unit", branchId)
                .list();

        PersonilPosition personilPosition = new PersonilPosition();
        if (results.size() > 0){
            for (Object[] obj : results){
                personilPosition.setNip(obj[0].toString());
                personilPosition.setPersonName(obj[1].toString());
            }

            return personilPosition;
        }

        return null;
    }

    public List<Position> getListUnitCost(String bagianId){

        String id = bagianId == null || "".equalsIgnoreCase(bagianId) ? "%" : bagianId;

        String SQL = "SELECT \n" +
                "position_id, \n" +
                "position_name, \n" +
                "kodering \n" +
                "FROM im_position\n" +
                "WHERE bagian_id LIKE '"+id+"'\n" +
                "AND flag_cost_unit = 'Y'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<Position> positionList = new ArrayList<>();
        if (list.size() > 0){
            for (Object[] obj : list){
                Position position = new Position();
                position.setPositionId(obj[0].toString());
                position.setPositionName(obj[1].toString());
                position.setKodering(obj[2] == null ? "" : obj[2].toString());
                positionList.add(position);
            }
        }

        return positionList;
    }

    public Position getOnePositionByKodering(String kodering){

        String SQL = "SELECT \n" +
                "position_id, \n" +
                "position_name,\n" +
                "kodering\n" +
                "FROM im_position \n" +
                "WHERE kodering = '"+kodering+"'\n" +
                "ORDER BY flag_cost_unit\n";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            Object[] obj = list.get(0);
            Position position = new Position();
            position.setPositionId(obj[0].toString());
            position.setPositionName(obj[1].toString());
            position.setKodering(obj[2] == null ? "" : obj[2].toString());
            return position;
        }
        return null;

    }

    public String getLastKodering(String koderingsubbid){

        String kodering = koderingsubbid + "%";

        String SQL = "SELECT \n" +
                "RIGHT(a.kodering, 2) as kodering\n" +
                "FROM (\n" +
                "\tSELECT kodering FROM im_position \n" +
                "\tWHERE kodering LIKE '"+kodering+"' ORDER BY kodering DESC LIMIT 1\n" +
                ") a";

        List<Object> objects = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        String stCount = "";
        if (objects.size() > 0){
            stCount = objects.get(0).toString();
        } else {
            stCount = "0";
        }

        int count = Integer.parseInt(stCount);
        int finalCount = count + 1;
        String stFinalCount = String.valueOf(finalCount);

        if (stFinalCount.length() == 1){
            stFinalCount = "0" + stFinalCount;
        }

        return stFinalCount;
    }
}