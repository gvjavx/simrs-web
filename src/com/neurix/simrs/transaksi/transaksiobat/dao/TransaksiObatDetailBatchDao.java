package com.neurix.simrs.transaksi.transaksiobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsTransaksiObatDetailBatchEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/01/20.
 */
public class TransaksiObatDetailBatchDao extends GenericDao<MtSimrsTransaksiObatDetailBatchEntity, BigInteger> {

    @Override
    protected Class<MtSimrsTransaksiObatDetailBatchEntity> getEntityClass() {
        return MtSimrsTransaksiObatDetailBatchEntity.class;
    }

    @Override
    public List<MtSimrsTransaksiObatDetailBatchEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(MtSimrsTransaksiObatDetailBatchEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_transaksi_obat_detail")!=null) {
                criteria.add(Restrictions.eq("idTransaksiObatDetail", (String) mapCriteria.get("id_transaksi_obat_detail")));
            }
            if (mapCriteria.get("no_batch") != null){
                criteria.add(Restrictions.eq("noBatch", (Integer) mapCriteria.get("no_batch")));
            }
            if (mapCriteria.get("id")!=null) {
                criteria.add(Restrictions.eq("id", (BigInteger) mapCriteria.get("id")));
            }
        }

        // Order by

        if (mapCriteria.get("order_by_no_batch") != null){
            criteria.addOrder(Order.desc("noBatch"));
            criteria.addOrder(Order.desc("createdDate"));
        } else {
            criteria.addOrder(Order.asc("createdDate"));
        }

        List<MtSimrsTransaksiObatDetailBatchEntity> results = criteria.list();

        return results;
    }

    public Integer getLastBatch(String idApproval){

        String SQL = "SELECT odb.id, odb.no_batch \n" +
                "FROM \n" +
                "mt_simrs_transaksi_obat_detail_batch odb\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail od ON odb.id_transaksi_obat_detail = od.id_transaksi_obat_detail\n" +
                "WHERE id_approval_obat = :idApproval\n" +
                "ORDER BY odb.created_date DESC\n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idApproval", idApproval)
                .list();

        Integer result = 0;
        if (results.size() > 0){
            for (Object[] obj : results){
                result = (Integer) obj[1];
            }
        }

        return result;
    }

    public List<BatchPermintaanObat> getListBatchByApprovalId(String idApproval){

        String SQL = "SELECT\n" +
                "no_batch,\n" +
                "od.id_approval_obat,\n" +
                "max(odb.last_update) as last_update\n" +
                "FROM mt_simrs_transaksi_obat_detail_batch odb\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail od ON od.id_transaksi_obat_detail = odb.id_transaksi_obat_detail\n" +
                "WHERE od.id_approval_obat = :idApproval\n" +
                "GROUP BY no_batch, od.id_approval_obat";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idApproval", idApproval)
                .list();

        List<BatchPermintaanObat> results = new ArrayList<>();
        BatchPermintaanObat batchPermintaanObat;
        if (list.size() > 0){
            for (Object[] obj : list){
                batchPermintaanObat = new BatchPermintaanObat();
                batchPermintaanObat.setNoBatch((Integer) obj[0]);
                batchPermintaanObat.setIdApproval((String) obj[1]);
                String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format((Timestamp) obj[2]);
                batchPermintaanObat.setStLastUpdateWho(formatDate);
                batchPermintaanObat.setLastUpdate((Timestamp) obj[2]);
                results.add(batchPermintaanObat);
            }
        }

        return results;
    }

    public BigInteger getSumQtyApproveOnBatch(String idTransObatDetail){
        String SQL = "SELECT\n" +
                "id_transaksi_obat_detail,\n" +
                "SUM(qty_approve) as jml_qty\n" +
                "FROM mt_simrs_transaksi_obat_detail_batch\n" +
                "WHERE id_transaksi_obat_detail = :id\n" +
                "GROUP BY id_transaksi_obat_detail";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idTransObatDetail)
                .list();

        BigInteger sum = new BigInteger(String.valueOf(0));
        if (list.size() > 0){
            for (Object[] obj : list){
                sum = new BigInteger(String.valueOf(obj[1]));
            }
        }

        return sum;
    }


    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_transaksi_obat_batch')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
