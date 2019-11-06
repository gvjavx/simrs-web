
package com.neurix.hris.master.smkJabatan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkJabatan.model.ImtSmkJabatanEntity;
import com.neurix.hris.master.smkJabatan.model.SmkJabatan;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
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
public class SmkJabatanDao extends GenericDao<ImtSmkJabatanEntity, String> {

    @Override
    protected Class<ImtSmkJabatanEntity> getEntityClass() {
        return ImtSmkJabatanEntity.class;
    }

    @Override
    public List<ImtSmkJabatanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtSmkJabatanEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("positionId")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("positionId")));
            }
            if (mapCriteria.get("divisiId")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisiId")));
            }
            if (mapCriteria.get("branchId")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branchId")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.desc("jabatanSmkId"));
        List<ImtSmkJabatanEntity> results = criteria.list();
        return results;
    }

    public List<ImtSmkJabatanEntity> getDataSearch(String positionId, String branchId){
        List<ImtSmkJabatanEntity> listOfResult = new ArrayList<ImtSmkJabatanEntity>();
        String posisi = " != '' ";
        String branch = " != '' ";

        if(!positionId.equals("")){
            posisi = " = '" + positionId +"' ";
        }
        if(!branchId.equals("")){
            branch = " = '" + branchId + "' ";
        }

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tjabatan.position_id, posisi.position_name, jabatan.divisi_id, divisi.department_name, jabatan.branch_id, unit.branch_name, sum(bobot) as bobot\n" +
                "from \n" +
                "\timt_hris_smk_jabatan as jabatan\n" +
                "left join im_position posisi on posisi.position_id = jabatan.position_id\n" +
                "left join im_hris_department divisi on divisi.department_id = jabatan.divisi_id\n" +
                "left join im_branches unit on unit.branch_id = jabatan.branch_id\n" +
                "where jabatan.position_id "+posisi+" and jabatan.branch_id "+branch+" and jabatan.flag = 'Y'\n" +
                "group by\n" +
                "\tjabatan.position_id, posisi.position_name, jabatan.divisi_id, divisi.department_name, jabatan.branch_id, unit.branch_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImtSmkJabatanEntity result  = new ImtSmkJabatanEntity();
            result.setPositionId((String) row[0]);
            result.setPositionName((String) row[1]);
            result.setDivisiId((String) row[2]);
            result.setDivisiName((String) row[3]);
            result.setBranchId((String) row[4]);
            result.setBranchName((String) row[5]);
            result.setBobot(Double.valueOf(row[6].toString()));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImtSmkJabatanEntity> getDataAspek(String positionId, String branchId){
        List<ImtSmkJabatanEntity> listOfResult = new ArrayList<ImtSmkJabatanEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\t* \n" +
                "from \n" +
                "\timt_hris_smk_jabatan\n" +
                "where position_id = '"+positionId+"' and branch_id = '"+branchId+"' and flag = 'Y';";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImtSmkJabatanEntity result  = new ImtSmkJabatanEntity();
            result.setJabatanSmkId((String) row[0]);
            result.setPositionId((String) row[1]);
            result.setDivisiId((String) row[2]);
            result.setBranchId((String) row[3]);
            result.setBobot(Double.valueOf(row[11].toString()));
            result.setTipeAspekId((String) row[12]);

            listOfResult.add(result);
        }
        return listOfResult;
    }


    /*public List<ImtSmkJabatanEntity> getDataSearch(String positionId, String branchId){
        List<ImtSmkJabatanEntity> listOfResult = new ArrayList<ImtSmkJabatanEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\t* \n" +
                "from \n" +
                "\timt_hris_smk_jabatan\n" +
                "where position_id = '"+positionId+"' and branch_id = '"+branchId+"';";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImtSmkJabatanEntity result  = new ImtSmkJabatanEntity();
            result.setJabatanSmkId((String) row[0]);
            result.setPositionId((String) row[1]);
            result.setDivisiId((String) row[2]);
            result.setBranchId((String) row[3]);
            result.setBobot(Integer.valueOf(row[11].toString()));
            result.setBranchId((String) row[12]);

            listOfResult.add(result);
        }
        return listOfResult;
    }*/

    public String getNextSmkJabatanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_jabatan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "JS"+sId;
    }

    public List<ImtSmkJabatanEntity> getListView(String posisi, String divisi, String branch) throws HibernateException {
        List<ImtSmkJabatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtSmkJabatanEntity.class)
                .add(Restrictions.eq("positionId", posisi))
                .add(Restrictions.eq("divisiId", divisi))
                .add(Restrictions.eq("branchId", branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("jabatanSmkId"))
                .list();

        return results;
    }

    public List<ImtSmkJabatanEntity> cekListView(String branchId, String positionId, String aspek) throws HibernateException {
        List<ImtSmkJabatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtSmkJabatanEntity.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.ilike("tipeAspekId", aspek, MatchMode.ANYWHERE))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("jabatanSmkId"))
                .list();

        return results;
    }

    //Digunakan untuk mengambil data berdasarkan BranchId, positionId dan tipeAspekId
    public List<ImtSmkJabatanEntity> getDataJabatan(String branch, String posisi, String tipeAspek) throws HibernateException {
        List<ImtSmkJabatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtSmkJabatanEntity.class)
                .add(Restrictions.eq("positionId", posisi))
                .add(Restrictions.eq("tipeAspekId", tipeAspek))
                .add(Restrictions.eq("branchId", branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("jabatanSmkId"))
                .list();

        return results;
    }
}
