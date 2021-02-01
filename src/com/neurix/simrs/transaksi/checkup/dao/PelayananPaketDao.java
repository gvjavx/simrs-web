package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsPelayananPaketEntity;
import com.neurix.simrs.transaksi.checkup.model.PelayananPaket;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PelayananPaketDao extends GenericDao<ItSimrsPelayananPaketEntity, String> {
    @Override
    protected Class<ItSimrsPelayananPaketEntity> getEntityClass() {
        return ItSimrsPelayananPaketEntity.class;
    }

    @Override
    public List<ItSimrsPelayananPaketEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPelayananPaketEntity.class);
        if (mapCriteria.get("id_pelayanan_paket") != null) {
            criteria.add(Restrictions.eq("idPelayananPaket", mapCriteria.get("id_pelayanan_paket").toString()));
        }
        if (mapCriteria.get("id_paket") != null) {
            criteria.add(Restrictions.eq("idPaket", mapCriteria.get("id_paket").toString()));
        }
        if (mapCriteria.get("id_detail_checkup") != null) {
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        List<ItSimrsPelayananPaketEntity> list = criteria.list();
        return list;
    }

    public List<PelayananPaket> getListPelayananPaket(String noCheckup) {
        List<PelayananPaket> paketList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "a.id_pelayanan_paket,\n" +
                "a.no_checkup,\n" +
                "a.id_detail_checkup,\n" +
                "a.urutan,\n" +
                "a.is_periksa,\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "a.id_paket\n" +
                "FROM it_simrs_pelayanan_paket a\n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "a.branch_id,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) b ON a.id_pelayanan = b.id_pelayanan\n" +
                "WHERE a.no_checkup = :id \n" +
                "ORDER BY a.urutan ASC";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", noCheckup)
                .list();
        if (result.size() > 0) {
            for (Object[] obj : result) {
                PelayananPaket paket = new PelayananPaket();
                paket.setIdPelayananPaket(obj[0] != null ? obj[0].toString() : "");
                paket.setNoCheckup(obj[1] != null ? obj[1].toString() : "");
                paket.setIdDetailCheckup(obj[2] != null ? obj[2].toString() : "");
                paket.setUrutan(obj[3] != null ? (BigInteger) obj[3] : null);
                paket.setIsPeriksa(obj[4] != null ? obj[4].toString() : "");
                paket.setIdPelayanan(obj[5] != null ? obj[5].toString() : "");
                paket.setNamaPelayanan(obj[6] != null ? obj[6].toString() : "");
                paket.setIdPaket(obj[7] != null ? obj[7].toString() : "");
                paketList.add(paket);
            }
        }
        return paketList;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pelayanan_paket')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
