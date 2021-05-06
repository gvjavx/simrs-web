package com.neurix.authorization.user.dao;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.user.model.ImUsers;
import com.neurix.authorization.user.model.ImUsersHistory;
import com.neurix.authorization.user.model.User;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
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
            if (mapCriteria.get("list_user_id")!=null) {
                criteria.add(Restrictions.in("primaryKey.id", (ArrayList<String>) mapCriteria.get("list_user_id")));
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
                "\tu.flag='Y' AND\n" +
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
                "\tu.flag = 'Y' AND \n" +
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
                "\tp.flag='Y' AND\n" +
                "\tpp.flag='Y' AND\n" +
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

    public List<String> getListUserIdByCriteria(Map mapCriteria){
        List<String> results = new ArrayList<>();

        String where = "";
        if (mapCriteria.get("user_id") != null)
            where += "AND u.user_id = '" +mapCriteria.get("user_id")+ "'\n ";

        if (mapCriteria.get("user_name") != null)
            where += "AND u.user_name LIKE '%" +mapCriteria.get("user_name")+ "%' \n ";

        if (mapCriteria.get("position_id") != null)
            where += "AND u.position_id = '" +mapCriteria.get("position_id")+ "' \n ";

        if (mapCriteria.get("department_id") != null)
            where += "AND u.department_id = '" +mapCriteria.get("department_id")+ "' \n ";

        if (mapCriteria.get("id_pelayanan") != null)
            where += "AND u.id_pelayanan = '" +mapCriteria.get("id_pelayanan")+ "' \n ";

        if (mapCriteria.get("id_ruangan") != null)
            where += "AND u.id_ruangan = '" +mapCriteria.get("id_ruangan")+ "' \n ";

        if (mapCriteria.get("id_device") != null)
            where += "AND u.id_device = '" +mapCriteria.get("id_device")+ "' \n ";

        if (mapCriteria.get("role_id") != null)
            where += "AND ur.role_id = '" +mapCriteria.get("role_id")+ "' \n ";

        if (mapCriteria.get("area_id") != null)
            where += "AND abu.area_id = '" +mapCriteria.get("area_id")+ "' \n ";

        if (mapCriteria.get("branch_id") != null)
            where += "AND abu.branch_id = '" +mapCriteria.get("branch_id")+ "' \n ";

        String SQL = "SELECT \n" +
                "u.user_id \n" +
                "FROM im_users u\n" +
                "INNER JOIN (SELECT * FROM im_users_roles WHERE flag = 'Y') ur ON ur.user_id = u.user_id \n" +
                "INNER JOIN (SELECT * FROM im_areas_branches_users WHERE flag = 'Y') abu ON abu.user_id = u.user_id \n" +
                "WHERE u.flag LIKE :flag \n" + where;

        List<Object> listObj = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("flag", mapCriteria.get("flag"))
                .list();

        if (listObj.size() > 0){
            for (Object obj : listObj){
                results.add(obj.toString());
            }
        }
        return results;
    }

    public List<User> getListUserByQuery(User user){
        List<User> results = new ArrayList<>();
        String condition = "";
        String flag = "%";

        if (user.getFlag() != null && !"".equalsIgnoreCase(user.getFlag())){
            flag = user.getFlag();
        }
        if (user.getUserId() != null && !"".equalsIgnoreCase(user.getUserId())){
            condition += "AND a.user_id LIKE '"+user.getUserId()+"' \n";
        }
        if (user.getUsername() != null && !"".equalsIgnoreCase(user.getUsername())){
            condition += "AND a.user_name LIKE '%"+user.getUsername()+"%' \n";
        }
        if (user.getAreaId() != null && !"".equalsIgnoreCase(user.getAreaId())){
            condition += "AND b.area_id = '"+user.getAreaId()+"' \n";
        }
        if (user.getBranchId() != null && !"".equalsIgnoreCase(user.getBranchId())){
            condition += "AND b.branch_id = '"+user.getBranchId()+"' \n";
        }
        if (user.getDepartmentId() != null && !"".equalsIgnoreCase(user.getDepartmentId())){
            condition += "AND a.department_id = '"+user.getDepartmentId()+"' \n";
        }
        if (user.getPositionId() != null && !"".equalsIgnoreCase(user.getPositionId())){
            condition += "AND a.position_id = '"+user.getPositionId()+"' \n";
        }
        if (user.getRoleId() != null && !"".equalsIgnoreCase(user.getRoleId())){
            condition += "AND c.role_id = "+user.getRoleId()+" \n";
        }
        if (user.getEmail() != null && !"".equalsIgnoreCase(user.getEmail())){
            condition += "AND a.email LIKE '%"+user.getEmail()+"%' \n";
        }

        String SQL = "SELECT\n" +
                "a.user_id,\n" +
                "a.user_name,\n" +
                "a.email,\n" +
                "a.photo_url,\n" +
                "a.created_date,\n" +
                "a.created_who,\n" +
                "a.last_update,\n" +
                "a.last_update_who,\n" +
                "a.department_id,\n" +
                "a.position_id,\n" +
                "a.flag,\n" +
                "a.password,\n" +
                "b.branch_id,\n" +
                "c.role_id,\n" +
                "d.branch_name,\n" +
                "e.role_name,\n" +
                "f.area_name,\n" +
                "g.department_name,\n" +
                "h.position_name,\n" +
                "b.area_id,\n" +
                "a.id_pelayanan,\n" +
                "a.id_ruangan,\n" +
                "a.id_vendor\n"+
                "FROM im_users a\n" +
                "INNER JOIN im_areas_branches_users b ON a.user_id = b.user_id\n" +
                "INNER JOIN im_users_roles c ON a.user_id = c.user_id\n" +
                "INNER JOIN im_branches d ON b.branch_id = d.branch_id\n" +
                "INNER JOIN im_roles e ON c.role_id = e.role_id\n" +
                "INNER JOIN im_areas f ON b.area_id = f.area_id\n" +
                "LEFT JOIN im_hris_department g ON a.department_id = g.department_id\n" +
                "LEFT JOIN im_position h ON a.position_id = h.position_id\n" +
                "WHERE a.flag LIKE :flag \n" +condition;

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("flag", flag)
                .list();

        if (result.size() > 0){
            for (Object[] obj : result){
                User use = new User();
                use.setUserId(obj[0] != null ? obj[0].toString() : null);
                use.setUsername(obj[1] != null ? obj[1].toString() : null);
                use.setEmail(obj[2] != null ? obj[2].toString() : null);
                if(obj[3] != null && !"".equalsIgnoreCase(obj[3].toString())){
                    use.setPhotoUserUrl(CommonConstant.RESOURCE_PATH_USER_UPLOAD+obj[3].toString());
                    use.setPreviewPhoto(obj[3].toString());
                }else{
                    use.setPhotoUserUrl(CommonConstant.RESOURCE_PATH_USER_UPLOAD+CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                    use.setPreviewPhoto(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                }
                use.setCreatedDate(obj[4] != null ? (Timestamp) obj[4] : null);
                use.setCreatedWho(obj[5] != null ? obj[5].toString() : null);
                use.setLastUpdate(obj[6] != null ? (Timestamp) obj[6] : null);
                use.setLastUpdateWho(obj[7] != null ? obj[7].toString() : null);
                use.setDepartmentId(obj[8] != null ? obj[8].toString() : null);
                use.setPositionId(obj[9] != null ? obj[9].toString() : null);
                use.setFlag(obj[10] != null ? obj[10].toString() : null);
                use.setPassword(obj[11] != null ? obj[11].toString() : null);
                use.setBranchId(obj[12] != null ? obj[12].toString() : null);
                use.setRoleId(obj[13] != null ? obj[13].toString() : null);
                use.setBranchName(obj[14] != null ? obj[14].toString() : null);
                use.setRoleName(obj[15] != null ? obj[15].toString() : null);
                use.setAreaName(obj[16] != null ? obj[16].toString() : null);
                use.setDivisiName(obj[17] != null ? obj[17].toString() : null);
                use.setPositionName(obj[18] != null ? obj[18].toString() : null);
                use.setAreaId(obj[19] != null ? obj[19].toString() : null);
                use.setIdPelayanan(obj[20] != null ? obj[20].toString() : null);
                use.setIdRuangan(obj[21] != null ? obj[21].toString() : null);
                use.setIdVendor(obj[22] != null ? obj[22].toString() : null);
                results.add(use);
            }
        }
        return results;
    }
}
