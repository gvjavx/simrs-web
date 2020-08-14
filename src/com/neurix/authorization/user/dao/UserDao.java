package com.neurix.authorization.user.dao;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.user.model.ImUsers;
import com.neurix.authorization.user.model.ImUsersHistory;
import com.neurix.authorization.user.model.User;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 14/01/13
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */

public class UserDao extends GenericDao<ImUsers,String> {
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    protected Class getEntityClass() {
        return ImUsers.class;
    }

    @Override
    public List<ImUsers> getByCriteria(Map mapCriteria) throws HibernateException {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("user_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.id", (String) mapCriteria.get("user_id")));
            }
            if (mapCriteria.get("user_name")!=null) {
                criteria.add(Restrictions.ilike("userName", "%" + (String)mapCriteria.get("user_name") + "%"));
            }
            if (mapCriteria.get("email")!=null) {
                criteria.add(Restrictions.ilike("email", "%" + (String) mapCriteria.get("email") + "%"));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (Long) mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }
            if (mapCriteria.get("lokasi_kebun")!=null) {
                criteria.add(Restrictions.eq("lokasiKebun", (Long) mapCriteria.get("lokasi_kebun")));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.asc("primaryKey.id"));

        List<ImUsers> results = criteria.list();

        return results;
    }

    public boolean isExistUser(String userId) throws HibernateException {

        List<ImUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.eq("primaryKey.id", userId))
                .list();


        return results.size() > 0 ? true : false;
    }

    public ImUsers getUserByUsername(String userName, String activeFlag) throws HibernateException {

        List<ImUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.eq("primaryKey.id", userName))
                .add(Restrictions.eq("flag", activeFlag))
                .list();


         return results.size() > 0 ? (ImUsers) results.get(0) : null;
    }

    public ImUsers getUserByUsername(String userName) throws HibernateException {

        List<ImUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.eq("primaryKey.id", userName))
                .list();


        return results.size() > 0 ? (ImUsers) results.get(0) : null;
    }

    public ImUsers getUserByIdPosition(String userId, long positionId) throws HibernateException {

        List<ImUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.eq("primaryKey.id", userId))
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("flag", "Y"))
                .list();


        return results.size() > 0 ? (ImUsers) results.get(0) : null;
    }


    public ImUsers getUserByLokasiLahan(Long lokasiLahan, Long positionId) throws HibernateException {

        List<ImUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.eq("lokasiKebun", lokasiLahan))
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("flag", "Y"))
                .list();


        return results.size() > 0 ? (ImUsers) results.get(0) : null;
    }

    public List<ImUsers> getListUser(String term) throws HibernateException {
        List<ImUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                //.add(Restrictions.ilike("userName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("primaryKey.id"))
                .list();
        return results;
    }

    public List<ImUsers> getListUser2(String term) throws HibernateException {
        List<ImUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("userName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("primaryKey.id"))
                .list();
        return results;
    }

    public List<ImUsers> getListOpsGps(String term) throws HibernateException {

        List<ImUsers> resultsByOpsGpsName = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("userName", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("positionId", Long.valueOf("4")))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        List<ImUsers> resultsByOpsGpsId = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("primaryKey.id", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("positionId", Long.valueOf("4")))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        resultsByOpsGpsName.addAll(resultsByOpsGpsId);

        return resultsByOpsGpsName;
    }

    public List<ImUsers> getListAsmud(String term) throws HibernateException {

        List<ImUsers> resultsByAsmudName = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("userName", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("positionId", Long.valueOf("7")))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        List<ImUsers> resultsByAsmudId = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("primaryKey.id", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("positionId", Long.valueOf("7")))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        resultsByAsmudName.addAll(resultsByAsmudId);

        return resultsByAsmudName;
    }

    public List<ImUsers> getListAsman(String term) throws HibernateException {

        List<ImUsers> resultsByAsmudName = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("userName", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("positionId", Long.valueOf("8")))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        List<ImUsers> resultsByAsmudId = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("primaryKey.id", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("positionId", Long.valueOf("8")))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        resultsByAsmudName.addAll(resultsByAsmudId);

        return resultsByAsmudName;
    }

    public List<ImUsers> getListMantan(String term) throws HibernateException {

        List<ImUsers> resultsByMantanName = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("userName", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("positionId", Long.valueOf("1")))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        List<ImUsers> resultsByMantanId = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("primaryKey.id", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("positionId", Long.valueOf("1")))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        resultsByMantanName.addAll(resultsByMantanId);

        return resultsByMantanName;
    }

    public List<ImUsers> getListTanamanPerson(String term) throws HibernateException {

        //updated by ferdi 01-12-2016, tanaman person ( asmud = 7, asman = 8, mantan =1 )
        List<ImUsers> resultsByTanamanName = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("userName", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.or(Restrictions.or(Restrictions.eq("positionId", Long.valueOf("7")),Restrictions.eq("positionId", Long.valueOf("8"))),Restrictions.eq("positionId",Long.valueOf("1") )))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        List<ImUsers> resultsByTanamanId = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("primaryKey.id", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.or(Restrictions.or(Restrictions.eq("positionId", Long.valueOf("7")),Restrictions.eq("positionId", Long.valueOf("8"))),Restrictions.eq("positionId", Long.valueOf("1"))))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        resultsByTanamanName.addAll(resultsByTanamanId);

        return resultsByTanamanName;
    }

    public List<ImUsers> getListApprovalPerson(String term) throws HibernateException {

        //updated by ferdi 01-12-2016, approval person ( asmud = 7, asman = 8, mantan =1 )
        List<ImUsers> resultsByApproveName = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("userName", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.or(Restrictions.or(Restrictions.eq("positionId", Long.valueOf("7")),Restrictions.eq("positionId", Long.valueOf("8"))),Restrictions.eq("positionId", Long.valueOf("1"))))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        List<ImUsers> resultsByApproveId = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("primaryKey.id", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.or(Restrictions.or(Restrictions.eq("positionId", Long.valueOf("7")),Restrictions.eq("positionId", Long.valueOf("8"))),Restrictions.eq("positionId", Long.valueOf("1"))))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        resultsByApproveName.addAll(resultsByApproveId);

        return resultsByApproveName;
    }
    public List<ImUsers> getListUserId(String term) throws HibernateException {

        List<ImUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.ilike("primaryKey.id", term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        return results;
    }
    public void addAndSaveHistory(ImUsersHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }

    public List<ImUsers> getUserByIdDevice(String idDevice){
        List<ImUsers> result = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.eq("idDevice", idDevice))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return result;
    }

    public List<User> getUserByBranchAndRole(String branchId, String roleId){
        List<User> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tu.user_id,\n" +
                "\tu.user_name,\n" +
                "\tur.role_id,\n" +
                "\tub.branch_id\n" +
                "from \n" +
                "\tim_users u \n" +
                "\tLEFT JOIN im_users_roles ur ON u.user_id=ur.user_id \n" +
                "\tLEFT JOIN im_areas_branches_users ub ON u.user_id = ub.user_id\n" +
                "WHERE\n" +
                "\tbranch_id='"+branchId+"' AND\n" +
                "\trole_id = '"+roleId+"'\n" +
                "ORDER BY\n" +
                "\tu.user_id asc";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            User data= new User();
            data.setUserId((String) row[0]);
            data.setUsername((String) row[1]);
            data.setBranchId((String) row[3]);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<User> getUserByBranchAndPositionAndRole(String branchId, String positionId,String roleId){
        List<User> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tu.user_id,\n" +
                "\tu.user_name,\n" +
                "\tur.role_id,\n" +
                "\tub.branch_id\n" +
                "from \n" +
                "\tim_users u \n" +
                "\tLEFT JOIN im_users_roles ur ON u.user_id=ur.user_id \n" +
                "\tLEFT JOIN im_areas_branches_users ub ON u.user_id = ub.user_id\n" +
                "WHERE\n" +
                "\tbranch_id='"+branchId+"' AND\n" +
                "\tposition_id = '"+positionId+"' AND \n" +
                "\trole_id = '"+roleId+"'\n" +
                "ORDER BY\n" +
                "\tu.user_id asc";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            User data= new User();
            data.setUserId((String) row[0]);
            data.setUsername((String) row[1]);
            data.setBranchId((String) row[3]);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<User> getUserPegawaiByBranchAndPositionAndRole(String branchId, String positionId){
        List<User> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\tp.nip,\n" +
                "\tp.nama_pegawai,\n" +
                "\tpp.branch_id\n" +
                "FROM\n" +
                "\tim_hris_pegawai p\n" +
                "\tINNER JOIN it_hris_pegawai_position pp ON p.nip = pp.nip\n" +
                "WHERE\n" +
                "\tbranch_id='"+branchId+"' AND\n" +
                "\tposition_id = '"+positionId+"' \n" +
                "ORDER BY\n" +
                "\tp.nip asc";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            User data= new User();
            data.setUserId((String) row[0]);
            data.setUsername((String) row[1]);
            data.setBranchId((String) row[2]);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public ImUsers getUserByIdPelayanan (String idPelayanan) throws HibernateException {

        List<ImUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImUsers.class)
                .add(Restrictions.eq("idPelayanan", idPelayanan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results.size() > 0 ? (ImUsers) results.get(0) : null;
    }
}
