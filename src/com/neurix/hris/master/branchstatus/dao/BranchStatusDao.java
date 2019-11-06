package com.neurix.hris.master.branchstatus.dao;

import com.neurix.authorization.company.model.Branch;
import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.branchstatus.model.BranchStatus;
import com.neurix.hris.master.branchstatus.model.ImtHrisBranchStatus;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class BranchStatusDao extends GenericDao<ImtHrisBranchStatus, String> {
    @Override
    protected Class<ImtHrisBranchStatus> getEntityClass() {
        return null;
    }

    @Override
    public List<ImtHrisBranchStatus> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtHrisBranchStatus.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("tipePegawaiId", (String) mapCriteria.get("tipe_pegawai_id")));
            }
            if (mapCriteria.get("tipe_pegawai_name")!=null) {
                criteria.add(Restrictions.ilike("tipePegawaiName", "%" + (String)mapCriteria.get("tipe_pegawai_name") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("tipePegawaiId"));

        List<ImtHrisBranchStatus> results = criteria.list();

        return results;
    }

    public List<BranchStatus> getSearchDataBranchStatus(String id) throws HibernateException{

        List<BranchStatus> listOfResult = new ArrayList<BranchStatus>();
        String query;
        String branchId;
        if (id != "" && !"".equalsIgnoreCase(id)){
            branchId = id;
        } else {
            branchId = "%";
        }
        query = "SELECT status_id, branch_id, status_pabrik, tanggal_awal, tanggal_akhir, \n" +
                "       created_date, created_who, last_update, last_update_who, flag, \n" +
                "       action\n" +
                "  FROM imt_branches_status\n" +
                "  WHERE branch_id = :branchId \n" +
                "  AND flag = 'Y'\n" +
                "  ORDER BY created_date desc limit 1";

        List<Object[]> results = new ArrayList<Object[]>();

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("branchId",branchId)
                .list();

        BranchStatus resultData;
        for (Object[] row : results) {
            resultData = new BranchStatus();
            resultData.setStatusId((Integer) row[0]);
            resultData.setBranchId((String) row[1]);
            resultData.setStatusPabrik((String) row[2]);
            resultData.setTanggalAwal((Date) row[3]);
            resultData.setTanggalAkhir((Date) row[4]);
            resultData.setCreatedDate((Timestamp) row[5]);
            resultData.setCreatedWho((String) row[6]);
            resultData.setLastUpdate((Timestamp) row[7]);
            resultData.setLastUpdateWho((String) row[8]);
            resultData.setFlag((String) row[9]);
            resultData.setAction((String) row[10]);
            listOfResult.add(resultData);
        }

        return listOfResult;
    }

    // Generate surrogate id from postgre
    public String getStatusId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_branch_status')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }

    // Generate surrogate id from postgre
//    public String getTipePegawaiHistoryId() throws HibernateException {
//        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_pegawai_history')");
//        Iterator<BigInteger> iter=query.list().iterator();
//        return String.valueOf(iter.next().longValue());
//    }

}
