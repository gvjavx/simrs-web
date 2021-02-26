package com.neurix.simrs.transaksi.monpemberianobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/02/20.
 */
public class MonPemberianObatDao extends GenericDao<ItSimrsMonPemberianObatEntity, String>{
    @Override
    protected Class<ItSimrsMonPemberianObatEntity> getEntityClass() {
        return ItSimrsMonPemberianObatEntity.class;
    }

    @Override
    public List<ItSimrsMonPemberianObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsMonPemberianObatEntity.class);
        if (mapCriteria.get("id") != null) {
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("no_checkup") != null) {
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        }
        if (mapCriteria.get("id_detail_checkup") != null){
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        if (mapCriteria.get("kategori") != null) {
            criteria.add(Restrictions.eq("kategori", mapCriteria.get("kategori").toString()));
        }

        criteria.addOrder(Order.desc("createdDate"));
        List<ItSimrsMonPemberianObatEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_mon_pemberian_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<Obat> getListObatNonParenteral(String idDetailCheckup){
        String SQL = "SELECT\n" +
                "b.id_obat,\n" +
                "c.nama_obat,\n" +
                "d.bentuk\n" +
                "FROM mt_simrs_permintaan_resep a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                "INNER JOIN im_simrs_header_obat c ON b.id_obat = c.id_obat\n" +
                "INNER JOIN im_simrs_bentuk_barang d ON c.id_bentuk = d.id_bentuk\n" +
                "WHERE a.id_detail_checkup = '"+idDetailCheckup+"'\n" +
                "AND a.status = '3'\n" +
                "AND c.flag_parenteral IS NULL OR c.flag_parenteral = 'N' \n" +
                "GROUP BY b.id_obat,\n" +
                "c.nama_obat,\n" +
                "d.bentuk";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();

        List<Obat> obats = new ArrayList<>();
        for (Object[] obj : results){
            Obat obat = new Obat();
            obat.setIdObat(obj[0].toString());
            obat.setNamaObat(obj[1].toString());
            obat.setBentuk(obj[2].toString());
            obats.add(obat);
        }
        return obats;
    }

    public List<Obat> getListObatParenteral(String idDetailCheckup){
        String SQL = "SELECT\n" +
                "b.id_obat,\n" +
                "c.nama_obat,\n" +
                "d.bentuk\n" +
                "FROM mt_simrs_permintaan_resep a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                "INNER JOIN im_simrs_header_obat c ON b.id_obat = c.id_obat\n" +
                "INNER JOIN im_simrs_bentuk_barang d ON c.id_bentuk = d.id_bentuk\n" +
                "WHERE a.id_detail_checkup = '"+idDetailCheckup+"'\n" +
                "AND a.status = '3'\n" +
                "AND c.flag_parenteral = 'Y' \n" +
                "GROUP BY b.id_obat,\n" +
                "c.nama_obat,\n" +
                "d.bentuk";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();

        List<Obat> obats = new ArrayList<>();
        for (Object[] obj : results){
            Obat obat = new Obat();
            obat.setIdObat(obj[0].toString());
            obat.setNamaObat(obj[1].toString());
            obat.setBentuk(obj[2].toString());
            obats.add(obat);
        }
        return obats;
    }
}
