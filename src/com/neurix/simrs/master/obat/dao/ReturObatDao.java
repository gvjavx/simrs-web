package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ItSimrsReturObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReturObatDao extends GenericDao<ItSimrsReturObatEntity, String> {
    @Override
    protected Class<ItSimrsReturObatEntity> getEntityClass() {
        return ItSimrsReturObatEntity.class;
    }

    @Override
    public List<ItSimrsReturObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsReturObatEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_retur_obat") != null) {
                criteria.add(Restrictions.eq("idReturObat", (String) mapCriteria.get("id_retur_obat")));
            }
            if (mapCriteria.get("id_vendor") != null) {
                criteria.add(Restrictions.eq("idVendor", mapCriteria.get("id_vendor")));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
        }

        List<ItSimrsReturObatEntity> results = criteria.list();

        return results;
    }

    public List<Obat> getListObatByVendor(String id, String branch) {

        String branchId = "%";
        if (branch != null && !"".equalsIgnoreCase(branch)) {
            branchId = branch;
        }

        String SQL = "SELECT \n" +
                "a.id_permintaan_obat_vendor,\n" +
                "a.id_approval_obat,\n" +
                "a.id_vendor,\n" +
                "a.created_date,\n" +
                "b.id_obat,\n" +
                "c.id_barang,\n" +
                "d.nama_obat,\n" +
                "d.qty_box,\n" +
                "d.qty_lembar,\n" +
                "d.qty_biji,\n" +
                "d.expired_date,\n" +
                "d.min_stok\n" +
                "FROM mt_simrs_permintaan_obat_vendor a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail_batch c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                "INNER JOIN im_simrs_obat d ON c.id_barang = d.id_barang\n" +
                "WHERE a.id_vendor = :id \n" +
                "AND a.branch_id = :branch \n" +
                "AND d.qty_box > 0 \n" +
                "ORDER BY d.expired_date ASC";

        List<Obat> obats = new ArrayList<>();

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .setParameter("branch", branchId)
                .list();

        if (!result.isEmpty() && result.size() > 0) {

            for (Object[] obj : result) {
                Obat obat = new Obat();
                obat.setIdPermintaanVendor(obj[0].toString());
                obat.setIdApprovalObat(obj[1].toString());
                obat.setIdVendor(obj[2].toString());
                if (obj[3] != null) {
                    obat.setCreatedDate((Timestamp) obj[3]);
                }
                obat.setIdObat(obj[4] == null ? "" : obj[4].toString());
                obat.setIdBarang(obj[5] == null ? "" : obj[5].toString());
                obat.setNamaObat(obj[6] == null ? "" : obj[6].toString());
                obat.setQtyBox(obj[7] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(obj[7].toString()));
                obat.setQtyLembar(obj[8] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(obj[8].toString()));
                obat.setQtyBiji(obj[9] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(obj[9].toString()));
                if (obj[10] != null) {
                    obat.setExpiredDate(Date.valueOf(obj[10].toString()));
                }
                obat.setMinStok(obj[11] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(obj[11].toString()));
                obats.add(obat);
            }
        }
        return obats;
    }

    public List<Obat> getListReturObat(Obat bean) {

        String branchId = "%";
        String nama = "%";
        String tgl = "";

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            branchId = bean.getBranchId();
        }

        if (bean.getNamaVendor() != null && !"".equalsIgnoreCase(bean.getNamaVendor())) {
            nama = "%" + bean.getNamaVendor() + "%";
        }

        if (bean.getStTglRetur() != null && !"".equalsIgnoreCase(bean.getStTglRetur())) {
            tgl = "AND CAST(a.tanggal_retur AS DATE) = to_date(" + bean.getStTglRetur() + ", 'dd-MM-yyyy') \n";
        }

        String SQL = "SELECT\n" +
                "a.id_retur_obat,\n" +
                "a.tanggal_retur,\n" +
                "a.jumlah_retur,\n" +
                "b.nama_vendor,\n" +
                "a.id_vendor,\n" +
                "c.id_permintaan_obat_vendor, \n" +
                "c.id_approval_obat \n" +
                "FROM it_simrs_retur_obat a\n" +
                "INNER JOIN im_simrs_vendor_obat b ON a.id_vendor = b.id_vendor\n" +
                "INNER JOIN mt_simrs_permintaan_obat_vendor c ON a.id_approval_obat = c.id_approval_obat\n" +
                "WHERE a.branch_id LIKE :branch \n" +
                "AND b.nama_vendor LIKE :nama \n" +
                "AND c.tipe_transaksi = 'reture'\n" +
                "ORDER BY a.tanggal_retur DESC";

        List<Obat> obats = new ArrayList<>();

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("nama", nama)
                .setParameter("branch", branchId)
                .list();

        if (!result.isEmpty() && result.size() > 0) {

            for (Object[] obj : result) {
                Obat obat = new Obat();
                obat.setIdRetureObat(obj[0].toString());
                if (obj[1] != null) {
                    obat.setTglRetur(Timestamp.valueOf(obj[1].toString()));
                    String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(obat.getTglRetur());
                    obat.setStTglRetur(formatDate);
                }
                obat.setQty(obj[2] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(obj[2].toString()));
                obat.setNamaVendor(obj[3] == null ? "" : obj[3].toString());
                obat.setIdVendor(obj[4] == null ? "" : obj[4].toString());
                obat.setIdPermintaanVendor(obj[5] == null ? "" : obj[5].toString());
                obat.setIdApprovalObat(obj[6] == null ? "" : obj[6].toString());
                Boolean isConfirm = confirmQtyApprove(obj[6].toString());
                if(isConfirm){
                    obat.setKeterangan("Telah Dikonfirmasi");
                }else{
                    obat.setKeterangan("Proses Retur");
                }
                obats.add(obat);
            }
        }
        return obats;
    }

    public List<Obat> getListDetailReturObat(String idRetur) {

        List<Obat> obats = new ArrayList<>();
        if (idRetur != null && !"".equalsIgnoreCase(idRetur)) {
            String SQL = "SELECT\n" +
                    "a.id_obat,\n" +
                    "a.id_barang,\n" +
                    "a.qty_retur,\n" +
                    "b.nama_obat\n" +
                    "FROM it_simrs_retur_obat_detail a\n" +
                    "INNER JOIN im_simrs_obat b ON a.id_barang = b.id_barang\n" +
                    "WHERE a.id_retur_obat = :id";

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idRetur)
                    .list();

            if (!result.isEmpty() && result.size() > 0) {

                for (Object[] obj : result) {
                    Obat obat = new Obat();
                    obat.setIdObat(obj[0] == null ? "" : obj[0].toString());
                    obat.setIdBarang(obj[1] == null ? "" : obj[1].toString());
                    obat.setQty(obj[2] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(obj[2].toString()));
                    obat.setNamaObat(obj[3] == null ? "" : obj[3].toString());
                    obats.add(obat);
                }
            }
        }
        return obats;
    }

    public Boolean confirmQtyApprove(String idApprove) {
        Boolean cek = false;
        if (idApprove != null && !"".equalsIgnoreCase(idApprove)) {
            String SQL = "SELECT \n" +
                    "a.id_transaksi_obat_detail, \n" +
                    "a.qty,\n" +
                    "b.qty_approve\n" +
                    "FROM mt_simrs_transaksi_obat_detail a\n" +
                    "INNER JOIN (\n" +
                    "SELECT SUM(qty_approve) as qty_approve, id_transaksi_obat_detail \n" +
                    "FROM mt_simrs_transaksi_obat_detail_batch\n" +
                    "GROUP BY id_transaksi_obat_detail) b \n" +
                    "ON a.id_transaksi_obat_detail = b.id_transaksi_obat_detail\n" +
                    "WHERE id_approval_obat = :id";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idApprove)
                    .list();

            if (result.size() > 0) {
                Object[] obj = result.get(0);
                if (obj[1] != null && !"".equalsIgnoreCase(obj[1].toString()) && obj[2] != null && !"".equalsIgnoreCase(obj[2].toString())) {
                    Integer qtyReq = Integer.valueOf(obj[1].toString());
                    Integer qtyApp = Integer.valueOf(obj[2].toString());
                    if (qtyReq == qtyApp) {
                        cek = true;
                    }
                }
            }
        }

        return cek;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_retur_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}