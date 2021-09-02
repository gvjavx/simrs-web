package com.neurix.hris.transaksi.refreshLembur.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.refreshLembur.model.ItHrisRefreshLemburEntity;
import com.neurix.hris.transaksi.refreshLembur.model.RefreshLembur;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Aji Noor on 22/07/2021
 */
public class RefreshLemburDao extends GenericDao<ItHrisRefreshLemburEntity, String> {

    @Override
    protected Class<ItHrisRefreshLemburEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItHrisRefreshLemburEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItHrisRefreshLemburEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("refresh_id") != null) {
                criteria.add(Restrictions.ilike("refreshLemburId", (String) mapCriteria.get("refresh_id")));
            }

            if (mapCriteria.get("group_id") != null) {
                criteria.add(Restrictions.ilike("groupRefreshId", (String) mapCriteria.get("group_id")));
            }
            if (mapCriteria.get("nama") != null) {
                criteria.add(Restrictions.ilike("nama", "%" + (String) mapCriteria.get("nama") + "%"));
            }
            if (mapCriteria.get("nip") != null) {
                criteria.add(Restrictions.ilike("nip", "%" + (String) mapCriteria.get("nip") + "%"));
            }
            if (mapCriteria.get("lembur_id") != null) {
                criteria.add(Restrictions.ilike("lembur_id", "%" + (String) mapCriteria.get("lembur_id") + "%"));
            }

            if (mapCriteria.get("tanggal_dari") != null && mapCriteria.get("tanggal_selesai") != null) {
                criteria.add(Restrictions.between("tanggal", mapCriteria.get("tanggal_dari"), mapCriteria.get("tanggal_selesai")));
                criteria.add(Restrictions.between("tanggal", mapCriteria.get("tanggal_dari"), mapCriteria.get("tanggal_selesai")));
            } else {
                if (mapCriteria.get("tanggal_dari") != null) {
                    criteria.add(Restrictions.ge("tanggal", mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai") != null) {
                    criteria.add(Restrictions.lt("tanggal", mapCriteria.get("tanggal_selesai")));
                }
            }

            if (mapCriteria.get("approval_flag") != null) {
                criteria.add(Restrictions.ilike("flagApprove", "%" + (String) mapCriteria.get("approval_flag") + "%"));
            }

        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("tanggal"));

        List<ItHrisRefreshLemburEntity> results = criteria.list();

        return results;
    }

    public String getNextRefreshLemburId() throws HibernateException {

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_refresh_lembur')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%06d", ((Iterator) iter).next());

        return "RL" + sId;
    }

    public String getNextGroupLemburId() throws HibernateException {

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_group_lembur')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%06d", ((Iterator) iter).next());

        return "GL" + sId;
    }

    public List<RefreshLembur> getPerGroupLembur(Map mapCriteria) {
        List<RefreshLembur> listOfResult = new ArrayList<RefreshLembur>();
        List<Object[]> results = new ArrayList<Object[]>();
        String groupId = "";
        String tanggalAwal = "";
        String tanggalAkhir = "";

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("group_id") != null) {
                groupId = " AND group_refresh_id = '" + mapCriteria.get("group_id") + "'\n";
            }
            if (mapCriteria.get("tanggal_dari") != null) {
                tanggalAwal = " AND tanggal >= '" + mapCriteria.get("tanggal_dari") + "'\n";
            }
            if (mapCriteria.get("tanggal_selesai") != null) {
                tanggalAkhir = " AND tanggal <= '" + mapCriteria.get("tanggal_selesai") + "'\n";
            }


            String query = "SELECT group_refresh_id, tanggal, tipe_hari, branch_id, COUNT(group_refresh_id)\n" +
                    "FROM it_hris_refresh_lembur\n" +
                    "WHERE flag = 'Y' \n" + groupId + tanggalAwal + tanggalAkhir +
                    "GROUP BY group_refresh_id, tanggal, tipe_hari, branch_id;";
            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                RefreshLembur refreshLembur = new RefreshLembur();

                refreshLembur.setGroupRefreshId((String) row[0]);
                refreshLembur.setTanggal((Date) row[1]);
                refreshLembur.setStTanggal(CommonUtil.convertDateToString(refreshLembur.getTanggal()));

                refreshLembur.setTipeHari((String) row[2]);
                refreshLembur.setBranchId((String) row[3]);

                refreshLembur.setFlagApprove((String) row[4]);
                refreshLembur.setApprovalwho((String) row[5]);

                refreshLembur.setJmlPegawai((String) row[6]);

                listOfResult.add(refreshLembur);

            }
        }
        return listOfResult;
    }

}
