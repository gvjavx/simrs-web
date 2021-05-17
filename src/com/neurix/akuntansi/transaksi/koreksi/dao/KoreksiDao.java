package com.neurix.akuntansi.transaksi.koreksi.dao;

import com.neurix.akuntansi.transaksi.koreksi.model.ItAkunKoreksiEntity;
import com.neurix.akuntansi.transaksi.koreksi.model.KoreksiDetail;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Aji Noor on 18/03/2021
 */
public class KoreksiDao extends GenericDao<ItAkunKoreksiEntity, String> {

    @Override
    protected Class<ItAkunKoreksiEntity> getEntityClass() {
        return ItAkunKoreksiEntity.class;
    }

    @Override
    public List<ItAkunKoreksiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItAkunKoreksiEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("koreksi_id")!=null) {
                criteria.add(Restrictions.eq("koreksiId", (String) mapCriteria.get("koreksi_id")));
            }
            if (mapCriteria.get("no_jurnal")!=null) {
                criteria.add(Restrictions.eq("noJurnal", (String) mapCriteria.get("no_jurnal")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("tipe_transaksi")!=null) {
                criteria.add(Restrictions.eq("tipeTransaksi", (String) mapCriteria.get("tipe_transaksi")));
            }
            if (mapCriteria.get("tanggal")!=null) {
                criteria.add(Restrictions.eq("tanggalKoreksi", (String) mapCriteria.get("tanggal")));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("tanggalKoreksi",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggalKoreksi",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("tanggalKoreksi",mapCriteria.get("tanggal_selesai")));
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.desc("koreksiId"));
        List<ItAkunKoreksiEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextKoreksiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_koreksi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());
        return "KRH"+sId; //--> perlu diganti akan terkena length constrain - noted by Aji
    }

    public String getNextKoreksiDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_koreksi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());
        return "KRD"+sId; //--> perlu diganti akan terkena length constrain - noted by Aji
    }

    public List<KoreksiDetail> getSearchNotaKoreksi(String masterId, String branchId, String tipeBayar, String divisiId, String rekeningId) throws HibernateException {
        List<KoreksiDetail> listOfResult = new ArrayList<KoreksiDetail>();
        String whereBranch ="";
        String whereDivisi = "";
        String wheremaster = "";
        String wherePosisi ="";

        if (!("").equalsIgnoreCase(branchId)){
            whereBranch=" and a.branch_id IN ("+branchId+") ";
        }
        if (!("").equalsIgnoreCase(divisiId)){
            whereDivisi=" and divisi_id = '"+divisiId+"' ";
        }
        if (!("").equalsIgnoreCase(masterId)){
            wheremaster=" and master_id = '"+masterId+"' ";
        }
        switch (tipeBayar){
            case "JKK":
                wherePosisi="foo.debit < 0 \n";
                break;
            case "JKM":
                wherePosisi="foo.debit > 0 \n";
                break;
            case "JKR":
                wherePosisi="foo.debit < 0 \n";
        }

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select " +
                "master_id, " +
                "rekening_id,  " +
                "noNota, " +
                "to_char(debit, '99G999G999G999G999') as debit ," +
                "divisi_id " +
                "from  " +
                "( select \n" +
                "      b.master_id, \n" +
                "      b.rekening_id, \n" +
                "      a.mata_uang_id as mataUangId, \n" +
                "      b.no_nota as noNota, \n" +
                "\t  b.divisi_id,\n" +
                "      sum(jumlah_debit - jumlah_kredit) as debit \n" +
                "    from \n" +
                "      it_akun_jurnal a \n" +
                "      inner join (\n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          it_akun_jurnal_detail \n" +
                "        where \n" +
                "          rekening_id = '"+rekeningId+"'\n" +
                "      ) b on a.no_jurnal = b.no_jurnal and b.no_nota != '' \n" +
                "    where \n" +
                "      a.flag = 'Y' \n" +
                "      and a.registered_flag = 'Y' \n" +
                whereBranch +
                "    group by \n" +
                "      b.no_nota, \n" +
                "      a.mata_uang_id, \n" +
                "      b.master_id, \n" +
                "      b.rekening_id,\n" +
                "\t  b.divisi_id\n" +
                "  ) foo \n" +
                "where \n" +
                wherePosisi+
                wheremaster+
                whereDivisi;

        try {
            Connection conn = this.sessionFactory.getCurrentSession().connection();
            PreparedStatement prepStmt = conn.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            KoreksiDetail data= new KoreksiDetail();
            data.setMasterId((String) row[0]);
            data.setKodeCoa((String) row[1]);
            data.setNoNota((String) row[2]);
            data.setStJumlahPembayaran((String) row[3]);
            data.setDivisiId((String) row[4]);

            listOfResult.add(data);
        }
        return listOfResult;
    }
}
