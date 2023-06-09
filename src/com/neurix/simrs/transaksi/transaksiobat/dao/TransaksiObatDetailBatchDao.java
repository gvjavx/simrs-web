package com.neurix.simrs.transaksi.transaksiobat.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsTransaksiObatDetailBatchEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatBatch;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
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
            if (mapCriteria.get("exp_date")!=null) {
                criteria.add(Restrictions.eq("expiredDate", (Date) mapCriteria.get("exp_date")));
            }
            if (mapCriteria.get("approve_flag") != null){
                criteria.add(Restrictions.eq("approveFlag", (String) mapCriteria.get("approve_flag")));
            }
            if (mapCriteria.get("status") != null){
                criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
            }
            if (mapCriteria.get("id_barang") != null){
                criteria.add(Restrictions.eq("idBarang", (String) mapCriteria.get("id_barang")));
            }
            if (mapCriteria.get("jenis") != null){
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
            if (mapCriteria.get("approve_flag") != null){
                criteria.add(Restrictions.eq("approveFlag", (String) mapCriteria.get("approve_flag")));
            }
        }

        // Order by

        if (mapCriteria.get("order_by_no_batch") != null){
            criteria.addOrder(Order.desc("noBatch"));
            criteria.addOrder(Order.desc("createdDate"));
        } else if(mapCriteria.get("order_last_created_date") != null){
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
                "max(odb.last_update) as last_update,\n" +
                "url_doc,\n" +
                "no_faktur,\n" +
                "tanggal_faktur,\n" +
                "no_invoice,\n" +
                "no_do,\n" +
                "odb.jenis ,\n" +
                "odb.tgl_invoice,\n" +
                "odb.tgl_do\n" +
                "FROM mt_simrs_transaksi_obat_detail_batch odb\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail od ON od.id_transaksi_obat_detail = odb.id_transaksi_obat_detail\n" +
                "WHERE od.id_approval_obat = :idApproval\n" +
//                "GROUP BY no_batch, od.id_approval_obat, url_doc, no_faktur, tanggal_faktur, no_invoice, no_do, odb.jenis";
                "GROUP BY no_batch, od.id_approval_obat, url_doc, no_faktur, tanggal_faktur, no_invoice, no_do, odb.jenis, odb.tgl_invoice, odb.tgl_do";

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
                batchPermintaanObat.setUrlDoc(obj[3] == null ? "" : CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_DOC_PO+obj[3].toString());
                batchPermintaanObat.setNoFaktur(obj[4] == null ? "" : obj[4].toString());
                if(obj[5] != null){
                    String tglFaktur = new SimpleDateFormat("dd-MM-yyyy").format((Date) obj[5]);
                    batchPermintaanObat.setStTanggakFaktur(tglFaktur);
                }
                batchPermintaanObat.setTanggalFaktur(obj[5] != null ? Date.valueOf(obj[5].toString()) : null);
                batchPermintaanObat.setNoInvoice(obj[6] == null ? "" : obj[6].toString());
                batchPermintaanObat.setNoDo(obj[7] == null ? "" : obj[7].toString());
                batchPermintaanObat.setJenis(obj[8] == null ? "" : obj[8].toString());
                Date tglInvoice = obj[9] == null ? null : (Date) obj[9];
                Date tglDo = obj[10] == null ? null : (Date) obj[10];
                batchPermintaanObat.setStTglInvoice(tglInvoice != null ? CommonUtil.ddMMyyyyFormat(tglInvoice) : "");
                batchPermintaanObat.setStTglDo(tglDo != null ? CommonUtil.ddMMyyyyFormat(tglDo) : "");
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

    public Boolean checkBatchApproveFlagByIdApproval(String idApproval, Integer noBatch){

        String SQL = "SELECT odb.id, odb.status, odb.approve_flag\n" +
                "FROM mt_simrs_transaksi_obat_detail_batch odb\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail od ON od.id_transaksi_obat_detail = odb.id_transaksi_obat_detail\n" +
                "WHERE odb.approve_flag is null\n" +
                "AND no_batch = :noBatch\n" +
                "AND od.id_approval_obat = :id";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idApproval)
                .setParameter("noBatch", noBatch)
                .list();

        if (list.size() > 0){
            return true;
        } else {
            return false;
        }
    }

    public BigInteger getJumlahApprove(String idTrans){

        String SQL = "SELECT \n" +
                "id_transaksi_obat_detail,\n" +
                "SUM(qty_approve) qty_approve\n" +
                "FROM mt_simrs_transaksi_obat_detail_batch\n" +
                "WHERE id_transaksi_obat_detail = :id \n" +
                "GROUP BY id_transaksi_obat_detail";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idTrans)
                .list();

        if (results.size() > 0){
            for (Object[] obj : results){
                if (obj[1] != null){
                    BigDecimal nilai = (BigDecimal) obj[1];
                    return new BigInteger(nilai.toString());
                }
            }
        }
        return new BigInteger(String.valueOf(0));
    }


    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_transaksi_obat_batch')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
