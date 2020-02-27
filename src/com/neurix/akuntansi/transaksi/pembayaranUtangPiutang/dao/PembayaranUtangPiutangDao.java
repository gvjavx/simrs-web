package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.dao;

import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.ImPembayaranUtangPiutangEntity;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutangDetail;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
public class PembayaranUtangPiutangDao extends GenericDao<ImPembayaranUtangPiutangEntity, String> {

    @Override
    protected Class<ImPembayaranUtangPiutangEntity> getEntityClass() {
        return ImPembayaranUtangPiutangEntity.class;
    }

    @Override
    public List<ImPembayaranUtangPiutangEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPembayaranUtangPiutangEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pembayaran_id")!=null) {
                criteria.add(Restrictions.eq("pembayaranUtangPiutangId", (String) mapCriteria.get("pembayaran_id")));
            }
            if (mapCriteria.get("no_jurnal")!=null) {
                criteria.add(Restrictions.eq("noJurnal", (String) mapCriteria.get("no_jurnal")));
            }
            if (mapCriteria.get("tipe_transaksi")!=null) {
                criteria.add(Restrictions.eq("tipeTransaksi", (String) mapCriteria.get("tipe_transaksi")));
            }
            if (mapCriteria.get("tanggal")!=null) {
                criteria.add(Restrictions.eq("tanggal", (String) mapCriteria.get("tanggal")));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("tanggal",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggal",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal_selesai")));
                }
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("pembayaranUtangPiutangId"));
        List<ImPembayaranUtangPiutangEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPembayaranUtangPiutangId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pembayaran_utang_piutang_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PUP"+sId;
    }
    public List<PembayaranUtangPiutangDetail> getSearchNotaPembayaran(String masterId,String tipeTransaksi,String branchId) throws HibernateException {
        List<PembayaranUtangPiutangDetail> listOfResult = new ArrayList<PembayaranUtangPiutangDetail>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "  master_id, \n" +
                "  rekening_id, \n" +
                "  noNota, \n" +
                "  to_char(debit, '99G999G999G999G999') as debit \n" +
                "from \n" +
                "  (\n" +
                "    select \n" +
                "      b.master_id, \n" +
                "      b.rekening_id, \n" +
                "      a.mata_uang_id as mataUangId, \n" +
                "      b.no_nota as noNota, \n" +
                "      sum(jumlah_debit - jumlah_kredit) as debit \n" +
                "    from \n" +
                "      it_akun_jurnal a \n" +
                "      inner join (\n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          it_akun_jurnal_detail \n" +
                "        where \n" +
                "          rekening_id IN (\n" +
                "\t\t  select \n" +
                "\t\t\t  rekening_id\n" +
                "\t\t  from \n" +
                "\t\t\t  im_akun_kode_rekening kr INNER JOIN (\n" +
                "\t\t\t  \tSELECT \n" +
                "\t\t\t\t  kode_rekening\n" +
                "\t\t\t\tFROM \n" +
                "\t\t\t\t  im_akun_mapping_jurnal\n" +
                "\t\t\t\tWHERE\n" +
                "\t\t\t\t  trans_id='"+tipeTransaksi+"' AND\n" +
                "\t\t\t\t  flag='Y'\n" +
                "\t\t\t  ) mapping\n" +
                "\t\t\t  ON kr.kode_rekening=mapping.kode_rekening\n" +
                "\t\t  ) \n" +
                "      ) b on a.no_jurnal = b.no_jurnal \n" +
                "      /* and b.no_nota in (\n" +
                "        select \n" +
                "          *\n" +
                "        from \n" +
                "          it_akun_jurnal \n" +
                "        where \n" +
                "          tipe_jurnal_id IN (\n" +
                "\t\t  \tSELECT \n" +
                "\t\t\t\t  tipe_jurnal_id\n" +
                "\t\t\t\tFROM \n" +
                "\t\t\t\t  im_akun_mapping_jurnal\n" +
                "\t\t\t\tWHERE\n" +
                "\t\t\t\t  trans_id='11' AND\n" +
                "\t\t\t\t  flag='Y'\n" +
                "\t\t\t  \tGROUP BY\n" +
                "\t\t\t  \t\ttipe_jurnal_id\n" +
                "\t\t  )\n" +
                "      ) */\n" +
                "      and b.no_nota != '' \n" +
                "    where \n" +
                "      a.flag = 'Y' \n" +
                "      and a.registered_flag = 'Y' \n" +
                "      and a.branch_id='"+branchId+"'" +
                "    group by \n" +
                "      b.no_nota, \n" +
                "      a.mata_uang_id, \n" +
                "      b.master_id, \n" +
                "      b.rekening_id\n" +
                "  ) foo \n" +
                "where \n" +
                "  foo.debit > 0 \n" +
                "  and master_id like '%' || '"+masterId+"' || '%' ";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PembayaranUtangPiutangDetail data= new PembayaranUtangPiutangDetail();
            data.setMasterId((String) row[0]);
            data.setRekeningId((String) row[1]);
            data.setNoNota((String) row[2]);
            data.setStJumlahPembayaran((String) row[3]);

            listOfResult.add(data);
        }
        return listOfResult;
    }
}
