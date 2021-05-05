package com.neurix.simrs.master.kelasruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
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

public class KelasRuanganDao extends GenericDao<ImSimrsKelasRuanganEntity, String> {
    @Override
    protected Class<ImSimrsKelasRuanganEntity> getEntityClass() {
        return ImSimrsKelasRuanganEntity.class;
    }

    @Override
    public List<ImSimrsKelasRuanganEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKelasRuanganEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_kelas_ruangan")!=null) {
                criteria.add(Restrictions.eq("idKelasRuangan", (String) mapCriteria.get("id_kelas_ruangan")));
            }
            if (mapCriteria.get("nama_kelas_ruangan")!=null) {
                criteria.add(Restrictions.ilike("namaKelasRuangan", "%" + (String)mapCriteria.get("nama_kelas_ruangan") + "%"));
            }
            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String)mapCriteria.get("divisi_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idKelasRuangan"));

        List<ImSimrsKelasRuanganEntity> results = criteria.list();

        return results;
    }

    public String getNextIdKelasRuangan(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kelas_ruangan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }

    public List<ImSimrsKelasRuanganEntity> getDataKelasRuangan(String namaKelasRuangan) throws HibernateException {
        List<ImSimrsKelasRuanganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKelasRuanganEntity.class)
                .add(Restrictions.eq("namaKelasRuangan", namaKelasRuangan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextKodering() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kodering')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%01d", iter.next());

        return sId;
    }

    public List<ImSimrsKelasRuanganEntity> cekData(String idKelasRuangan) throws HibernateException{
        List<ImSimrsKelasRuanganEntity> results = new ArrayList<>();

        String query = "SELECT a.id_kelas_ruangan, b.id_ruangan\n" +
                "FROM im_simrs_kelas_ruangan a\n" +
                "INNER JOIN mt_simrs_ruangan b ON b.id_kelas_ruangan = a.id_kelas_ruangan\n" +
                "WHERE a.id_kelas_ruangan = '"+idKelasRuangan+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    public List<KelasRuangan> getListKelasKamar(String kategori) throws HibernateException{
        List<KelasRuangan> results = new ArrayList<>();
        String kat = "%";
        if(kategori != null && !"".equalsIgnoreCase(kategori)){
            kat = kategori;
        }
        String query = "SELECT\n" +
                "id_kelas_ruangan,\n" +
                "nama_kelas_ruangan,\n" +
                "kategori\n" +
                "FROM im_simrs_kelas_ruangan\n" +
                "WHERE kategori LIKE :kategori";

        List<Object[]> objects = new ArrayList<>();

        objects = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("kategori", kat)
                .list();
        if(objects.size() > 0){
            for (Object[] obj: objects){
                KelasRuangan kelasRuangan = new KelasRuangan();
                kelasRuangan.setIdKelasRuangan(obj[0] == null ? null : obj[0].toString());
                kelasRuangan.setNamaKelasRuangan(obj[1] == null ? null : obj[1].toString());
                kelasRuangan.setKategori(obj[2] == null ? null : obj[2].toString());
                results.add(kelasRuangan);
            }
        }
        return results;
    }
}