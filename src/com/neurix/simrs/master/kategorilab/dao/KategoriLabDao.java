package com.neurix.simrs.master.kategorilab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;
import com.neurix.simrs.master.kategorilab.model.KategoriLab;
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

public class KategoriLabDao extends GenericDao<ImSimrsKategoriLabEntity,String> {

    @Override
    protected Class<ImSimrsKategoriLabEntity> getEntityClass() {
        return ImSimrsKategoriLabEntity.class;
    }

    @Override
    public List<ImSimrsKategoriLabEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriLabEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_kategori_lab")!=null) {
                criteria.add(Restrictions.eq("idKategoriLab", (String) mapCriteria.get("id_kategori_lab")));
            }
            if (mapCriteria.get("nama_kategori")!=null) {
                criteria.add(Restrictions.ilike("namaKategori", "%" + (String)mapCriteria.get("nama_kategori") + "%"));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.asc("idKategoriLab"));
        List<ImSimrsKategoriLabEntity> results = criteria.list();
        return results;

    }

    public List<ImSimrsKategoriLabEntity> getDataKategoriLab(String namaKategoriLab) throws HibernateException {
        List<ImSimrsKategoriLabEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriLabEntity.class)
                .add(Restrictions.eq("namaKategori", namaKategoriLab))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextLabKategoriId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kategori_lab')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "KAL" + sId;
    }

    public List<ImSimrsKategoriLabEntity> cekData(String idKategoriLab) throws HibernateException{
        List<ImSimrsKategoriLabEntity> results = new ArrayList<>();

        String query = "SELECT a.id_lab, b.id_kategori_lab\n" +
                "FROM im_simrs_lab a\n" +
                "INNER JOIN im_simrs_kategori_lab b ON b.id_kategori_lab = a.id_kategori_lab\n" +
                "WHERE a.id_kategori_lab = '"+idKategoriLab+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    public List<KategoriLab> getKategoriLabByLab(String idLab, String branchId){
        List<KategoriLab> kategoriLabs = new ArrayList<>();
        if(idLab != null && !"".equalsIgnoreCase(idLab) && branchId != null && !"".equalsIgnoreCase(branchId)){
            String SQL = "SELECT \n" +
                    "c.id_kategori_lab,\n" +
                    "d.nama_kategori,\n" +
                    "d.kategori\n" +
                    "FROM im_simrs_lab_detail a\n" +
                    "INNER JOIN im_simrs_lab b ON a.id_lab = b.id_lab\n" +
                    "INNER JOIN im_simrs_parameter_pemeriksaan c ON a.id_parameter_pemeriksaan = c.id_parameter_pemeriksaan\n" +
                    "INNER JOIN im_simrs_kategori_lab d ON c.id_kategori_lab = d.id_kategori_lab\n" +
                    "WHERE a.id_lab = :id \n" +
                    "AND a.branch_id = :branch \n" +
                    "GROUP BY\n" +
                    "c.id_kategori_lab,\n" +
                    "d.nama_kategori,\n" +
                    "d.kategori";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idLab)
                    .setParameter("branch", branchId)
                    .list();
            if(result.size() > 0){
                for (Object[] obj: result){
                    KategoriLab kategoriLab = new KategoriLab();
                    kategoriLab.setIdKategoriLab(obj[0] != null ? (String)obj[0] : null);
                    kategoriLab.setNamaKategori(obj[1] != null ? (String)obj[1] : null);
                    kategoriLab.setKategori(obj[2] != null ? (String)obj[2] : null);
                    kategoriLabs.add(kategoriLab);
                }
            }
        }
        return kategoriLabs;
    }
}