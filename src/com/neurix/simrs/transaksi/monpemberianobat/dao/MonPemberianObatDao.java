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

    public List<Obat> getListObatNonParenteral(String id, String kategori){

        String SQL = "SELECT \n" +
                "ob.id_obat,\n" +
                "ob.nama_obat,\n" +
                "res.id_detail_checkup\n" +
                "FROM\n" +
                "mt_simrs_permintaan_resep res\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail det ON det.id_approval_obat = res.id_approval_obat\n" +
                "INNER JOIN im_simrs_obat ob ON ob.id_obat = det.id_obat\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail_batch batch ON batch.id_transaksi_obat_detail = det.id_transaksi_obat_detail\n" +
                "INNER JOIN im_simrs_obat_gejala gj ON gj.id_obat = ob.id_obat\n" +
                "INNER JOIN im_simrs_jenis_obat jno ON jno.id_jenis_obat = gj.id_jenis_obat\n" +
                "WHERE res.id_detail_checkup LIKE :id\n" +
                "AND res.is_umum = 'N'\n" +
                "AND batch.approve_flag = 'Y'\n" +
                "AND jno.nama_jenis_obat ILIKE :kategori\n" +
                "GROUP BY \n" +
                "ob.id_obat,\n" +
                "ob.nama_obat,\n" +
                "res.id_detail_checkup\n";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .setParameter("kategori", kategori)
                .list();

        List<Obat> obats = new ArrayList<>();
        Obat obat;
        for (Object[] obj : results){
            obat = new Obat();
            obat.setIdObat(obj[0].toString());
            obat.setNamaObat(obj[1].toString());
            obats.add(obat);
        }
        return obats;
    }

    public List<Obat> getListObatParenteral(String idPelayanan){

        String SQL = "SELECT \n" +
                "ob.id_obat,\n" +
                "ob.nama_obat\n" +
                "FROM mt_simrs_obat_poli op\n" +
                "INNER JOIN im_simrs_obat ob ON ob.id_obat = op.id_obat\n" +
                "WHERE op.id_pelayanan = :id \n" +
                "GROUP BY\n" +
                "ob.id_obat,\n" +
                "ob.nama_obat";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idPelayanan)
                .list();

        List<Obat> obats = new ArrayList<>();
        Obat obat;
        for (Object[] obj : results){
            obat = new Obat();
            obat.setIdObat(obj[0].toString());
            obat.setNamaObat(obj[1].toString());
            obats.add(obat);
        }
        return obats;
    }
}
