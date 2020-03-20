package com.neurix.simrs.transaksi.permintaanvendor.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanvendor.model.MtSimrsPermintaanVendorEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class PermintaanVendorDao extends GenericDao<MtSimrsPermintaanVendorEntity, String> {

    @Override
    protected Class<MtSimrsPermintaanVendorEntity> getEntityClass() {
        return MtSimrsPermintaanVendorEntity.class;
    }

    @Override
    public List<MtSimrsPermintaanVendorEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsPermintaanVendorEntity.class);
        if (mapCriteria.get("id_permintaan_vendor") != null) {
            criteria.add(Restrictions.eq("idPermintaanVendor", mapCriteria.get("id_permintaan_vendor").toString()));
        }
        if (mapCriteria.get("id_approval_obat") != null) {
            criteria.add(Restrictions.eq("idApprovalObat", mapCriteria.get("id_approval_obat").toString()));
        }
        if (mapCriteria.get("branch_id") != null) {
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<MtSimrsPermintaanVendorEntity> list = criteria.list();
        return list;
    }

    public Boolean isAvailNotConfirm(String idApprovalObat) {

        Boolean check = true;
        if (!"".equalsIgnoreCase(idApprovalObat)) {
            String SQL = "SELECT odb.id_transaksi_obat_detail, odb.id\n" +
                    "FROM mt_simrs_transaksi_obat_detail od\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail_batch odb ON odb.id_transaksi_obat_detail = od.id_transaksi_obat_detail\n" +
                    "WHERE odb.approve_flag is null\n" +
                    "AND od.id_approval_obat = :id";

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idApprovalObat)
                    .list();

            if (result.size() > 0) {
                check = false;
            } else {
                String SQL2 = "SELECT odb.id_transaksi_obat_detail, odb.id\n" +
                        "FROM mt_simrs_transaksi_obat_detail od\n" +
                        "INNER JOIN mt_simrs_transaksi_obat_detail_batch odb ON odb.id_transaksi_obat_detail = od.id_transaksi_obat_detail\n" +
                        "WHERE od.id_approval_obat = :id2";

                List<Object[]> result2 = this.sessionFactory.getCurrentSession().createSQLQuery(SQL2)
                        .setParameter("id2", idApprovalObat)
                        .list();

                if (result2.size() == 0) {
                    check = false;
                }
            }
        }
        return check;
    }

    public List<TransaksiObatDetail> getListObatByBatch(String idPermintaan, Integer noBatch) {

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if (!"".equalsIgnoreCase(idPermintaan) && idPermintaan != null && !"".equalsIgnoreCase(noBatch.toString()) && noBatch != null) {

            String SQL = "SELECT \n" +
                    "b.id_obat,\n" +
                    "d.nama_obat,\n" +
                    "b.qty,\n" +
                    "c.qty_approve,\n" +
                    "b.jenis_satuan,\n" +
                    "CASE \n" +
                    "    WHEN b.jenis_satuan = 'box' THEN b.average_harga_box \n" +
                    "    WHEN b.jenis_satuan = 'lembar' THEN b.average_harga_lembar\n" +
                    "    WHEN b.jenis_satuan = 'biji' THEN b.average_harga_biji\n" +
                    "    ELSE null END as harga\n" +
                    "FROM mt_simrs_permintaan_obat_vendor a\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "INNER JOIN (\n" +
                    "SELECT id_transaksi_obat_detail, no_batch, SUM(qty_approve) as qty_approve \n" +
                    "FROM mt_simrs_transaksi_obat_detail_batch GROUP BY id_transaksi_obat_detail, no_batch\n" +
                    ") c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                    "INNER JOIN (\n" +
                    "SELECT id_obat, nama_obat FROM im_simrs_obat GROUP BY id_obat, nama_obat\n" +
                    ") d ON b.id_obat = d.id_obat\n" +
                    "WHERE a.id_permintaan_obat_vendor = :id\n" +
                    "AND c.no_batch = :no";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPermintaan)
                    .setParameter("no", noBatch)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    TransaksiObatDetail detail = new TransaksiObatDetail();
                    detail.setIdObat(obj[0] == null ? "" : obj[0].toString());
                    detail.setNamaObat(obj[1] == null ? "" : obj[1].toString());
                    detail.setQty(obj[2] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[2].toString()));
                    detail.setQtyApprove(obj[3] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[3].toString()));
                    detail.setJenisSatuan(obj[4] == null ? "" : obj[4].toString());
                    detail.setHargaPo(obj[5] == null ? new BigDecimal(String.valueOf(0)) : new BigDecimal(obj[5].toString()));
                    obatDetailList.add(detail);
                }
            }
        }
        return obatDetailList;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_permintaan_vendor')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
