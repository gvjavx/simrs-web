package com.neurix.simrs.transaksi.transaksiobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsRiwayatPembelianObat;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 14/12/2019.
 */
public class RiwayatTransPembelianObatDao extends GenericDao<MtSimrsRiwayatPembelianObat, String> {
    @Override
    protected Class<MtSimrsRiwayatPembelianObat> getEntityClass() {
        return MtSimrsRiwayatPembelianObat.class;
    }

    @Override
    public List<MtSimrsRiwayatPembelianObat> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRiwayatPembelianObat.class);
        if (mapCriteria.get("no_transaksi_obat") != null)
            criteria.add(Restrictions.eq("noPembelianObat", mapCriteria.get("no_transaksi_obat").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flaf", mapCriteria.get("flag").toString()));

        List<MtSimrsRiwayatPembelianObat> list = criteria.list();
        return list;
    }

    public List<MtSimrsRiwayatPembelianObat> getRiwayatPembelianObat(String idApproval){

        String SQL = "SELECT \n" +
                "a.id_approval_obat,\n" +
                "b.total_bayar,\n" +
                "b.total_dibayar,\n" +
                "b.nominal,\n" +
                "b.kembalian,\n" +
                "b.created_date,\n" +
                "b.created_who\n" +
                "FROM mt_simrs_riwayat_transaksi_obat a\n" +
                "INNER JOIN mt_simrs_riwayat_pembelian_obat b ON a.no_transaksi_pembelian = b.no_transaksi_pembelian\n" +
                "WHERE a.id_approval_obat = :idApprov";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idApprov", idApproval)
                .list();

        List<MtSimrsRiwayatPembelianObat> riwayatPembelianObatList = new ArrayList<>();

        if (results.size() > 0)
        {
            MtSimrsRiwayatPembelianObat riwayatPembelianObat;
            for (Object[] obj : results)
            {
                riwayatPembelianObat = new MtSimrsRiwayatPembelianObat();
                riwayatPembelianObat.setTotalBayar(new BigInteger(obj[1].toString()));
                riwayatPembelianObat.setTotalDibayar(new BigInteger(obj[2].toString()));
                riwayatPembelianObat.setNominal(new BigInteger(obj[3].toString()));
                riwayatPembelianObat.setKembalian(new BigInteger(obj[4].toString()));
                riwayatPembelianObat.setCreatedDate((Timestamp) obj[5]);
                riwayatPembelianObat.setCreatedWho(obj[6].toString());
                riwayatPembelianObatList.add(riwayatPembelianObat);
            }
        }

        return riwayatPembelianObatList;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_transaksi_pembelian_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

}
