package com.neurix.simrs.transaksi.riwayatbarang.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsRiwayatBarangMasukEntity;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsTransaksiStokEntity;
import com.neurix.simrs.transaksi.riwayatbarang.model.TransaksiStok;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/05/20.
 */
public class TransaksiStokDao extends GenericDao<ItSimrsTransaksiStokEntity, String> {
    @Override
    protected Class<ItSimrsTransaksiStokEntity> getEntityClass() {
        return ItSimrsTransaksiStokEntity.class;
    }

    @Override
    public List<ItSimrsTransaksiStokEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsTransaksiStokEntity.class);

        if (mapCriteria != null){
            if (mapCriteria.get("id_transaksi") != null){
                criteria.add(Restrictions.eq("idTransaksi", mapCriteria.get("id_transaksi").toString()));
            }
            if (mapCriteria.get("id_barang") != null){
                criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_barang").toString()));
            }
            if (mapCriteria.get("id_pelayanan") != null){
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }
            if (mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("tipe") != null){
                criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
            }
            if (mapCriteria.get("id_vendor") != null){
                criteria.add(Restrictions.eq("idVendor", mapCriteria.get("id_vendor").toString()));
            }

            if (mapCriteria.get("tahun") != null){
                criteria.add(Restrictions.sqlRestriction("EXTRACT(YEAR FROM registered_date) = ?", (Integer) mapCriteria.get("tahun"), Hibernate.INTEGER));
            }

            if (mapCriteria.get("bulan") != null){
                criteria.add(Restrictions.sqlRestriction("EXTRACT(MONTH FROM registered_date) = ?", (Integer) mapCriteria.get("bulan"), Hibernate.INTEGER));
            }
        }

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_riwayat_barang')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<TransaksiStok> getListOpnameByPeriode(String idPelayanan, String tahun, String bulan){

        String SQL = "SELECT\n" +
                "  a.id_obat,\n" +
                "  b.nama_obat\n" +
                "  FROM it_simrs_transaksi_stok a\n" +
                "INNER JOIN im_simrs_obat b ON b.id_obat = a.id_obat\n" +
                "WHERE a.id_pelayanan = :poli \n" +
                "AND extract(YEAR FROM a.registered_date) = :tahun \n" +
                "AND extract(MONTH FROM a.registered_date) = :bulan \n" +
                "GROUP BY a.id_obat, b.nama_obat\n" +
                "ORDER BY b.nama_obat;";

        List<TransaksiStok> list = new ArrayList<>();
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("poli", idPelayanan)
                .setParameter("tahun", new Integer(tahun))
                .setParameter("bulan", new Integer(bulan))
                .list();

        if (results.size() > 0){

            TransaksiStok transaksiStok;
            for (Object[] obj : results){
                transaksiStok = new TransaksiStok();
                transaksiStok.setIdObat(obj[0].toString());
                transaksiStok.setNamaObat(obj[1].toString());
                list.add(transaksiStok);
            }
        }

        return list;
    }

    public List<TransaksiStok> getSaldoOpnameByPeriode(String idPelayanan, String idObat, String tahun, String bulan){

        String SQL = "SELECT\n" +
                "  a.id_obat,\n" +
                "  a.tipe,\n" +
                "  sum(a.qty) AS qty,\n" +
                "  sum(a.sub_total) AS sub_total\n" +
                "  FROM it_simrs_transaksi_stok a\n" +
                "WHERE a.id_pelayanan = :poli \n" +
                "AND a.id_obat = :obat \n" +
                "AND extract(YEAR FROM a.registered_date) = :tahun \n" +
                "AND extract(MONTH FROM a.registered_date) = :bulan \n" +
                "GROUP BY a.id_obat, a.tipe;";

        List<TransaksiStok> list = new ArrayList<>();
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("poli", idPelayanan)
                .setParameter("obat", idObat)
                .setParameter("tahun", new Integer(tahun))
                .setParameter("bulan", new Integer(bulan))
                .list();

        if (results.size() > 0){

            TransaksiStok transaksiStok;
            for (Object[] obj : results){
                transaksiStok = new TransaksiStok();
                transaksiStok.setIdObat(obj[0].toString());
                transaksiStok.setTipe(obj[1].toString());
                transaksiStok.setQtySaldo((BigInteger) obj[2]);
                transaksiStok.setSubTotalSaldo((BigDecimal) obj[3]);
                list.add(transaksiStok);
            }
        }

        return list;
    }

    public TransaksiStok getSaldoBulanLaluByPeriode(String idPelayanan, String idObat, String tahun, String bulan){

        String SQL = "SELECT\n" +
                "  a.id_obat,\n" +
                "  sum(a.qty_lalu) AS qty_lalu,\n" +
                "  sum(a.total_lalu) AS total_lalu,\n" +
                "  sum(a.sub_total_lalu) AS sub_total_lalu\n" +
                "  FROM it_simrs_transaksi_stok a\n" +
                "WHERE\n" +
                "  a.id_pelayanan = :poli \n" +
                "  AND a.id_obat = :obat \n" +
                "  AND extract(YEAR FROM a.registered_date) = :tahun \n" +
                "  AND extract(MONTH FROM a.registered_date) = :bulan \n" +
                "  OR a.qty_lalu > 0\n" +
                "  OR a.total_lalu > 0\n" +
                "  OR a.sub_total_lalu > 0\n" +
                "  GROUP BY a.id_obat;";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("poli", idPelayanan)
                .setParameter("obat", idObat)
                .setParameter("tahun", new Integer(tahun))
                .setParameter("bulan", new Integer(bulan))
                .list();

        TransaksiStok transaksiStok = new TransaksiStok();
        if (results.size() > 0){
            for (Object[] obj : results){
                transaksiStok = new TransaksiStok();
                transaksiStok.setIdObat(obj[0].toString());
                transaksiStok.setQtyLalu((BigInteger)(obj[1]));
                transaksiStok.setTotalLalu((BigDecimal) obj[2]);
                transaksiStok.setSubTotalLalu((BigDecimal) obj[3]);
            }
        }

        return transaksiStok;
    }
}
