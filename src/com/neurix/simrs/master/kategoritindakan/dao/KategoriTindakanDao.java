package com.neurix.simrs.master.kategoritindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.kategoritindakan.model.ImSimrsKategoriTindakanEntity;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class KategoriTindakanDao extends GenericDao<ImSimrsKategoriTindakanEntity, String> {
    @Override
    protected Class<ImSimrsKategoriTindakanEntity> getEntityClass() {
        return ImSimrsKategoriTindakanEntity.class;
    }

    @Override
    public List<ImSimrsKategoriTindakanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriTindakanEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_kategori_tindakan") != null) {
                criteria.add(Restrictions.eq("idKategoriTindakan", mapCriteria.get("id_kategori_tindakan").toString()));
            }
            if (mapCriteria.get("kategori_tindakan") != null) {
                criteria.add(Restrictions.ilike("kategoriTindakan", "%" + (String) mapCriteria.get("kategori_tindakan") + "%"));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }

        List<ImSimrsKategoriTindakanEntity> result = criteria.list();
        return result;
    }

    public List<KategoriTindakan> getListKategoriTindakan(String idPelayanan, String kategori, String branchId) {

        List<KategoriTindakan> tindakanList = new ArrayList<>();
        String pelayanan = "%";
        String SQL = "";

        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)) {
            pelayanan = idPelayanan;
        }
        if (kategori != null && !"".equalsIgnoreCase(kategori)) {
            SQL = "SELECT \n" +
                    "a.id_kategori_tindakan,\n" +
                    "a.kategori_tindakan\n" +
                    "FROM im_simrs_kategori_tindakan a\n" +
                    "INNER JOIN im_simrs_tindakan b ON a.id_kategori_tindakan = b.id_kategori_tindakan\n" +
                    "INNER JOIN (SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "a.branch_id,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "b.kode_vclaim\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) c ON b.id_pelayanan = c.id_pelayanan\n" +
                    "WHERE c.tipe_pelayanan IN ('"+kategori+"', 'rawat_jalan', 'igd')\n" +
                    "AND b.branch_id = '"+branchId+"'\n" +
                    "GROUP BY a.id_kategori_tindakan,\n" +
                    "a.kategori_tindakan";
        }else{
            SQL = "SELECT \n" +
                    "a.id_kategori_tindakan,\n" +
                    "a.kategori_tindakan\n" +
                    "FROM im_simrs_kategori_tindakan a\n" +
                    "INNER JOIN im_simrs_tindakan b ON a.id_kategori_tindakan = b.id_kategori_tindakan\n" +
                    "WHERE b.id_pelayanan = '"+pelayanan+"' \n" +
                    "GROUP BY a.id_kategori_tindakan,\n" +
                    "a.kategori_tindakan";
        }

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if (results.size() > 0) {
            for (Object[] obj : results) {
                KategoriTindakan tindakan = new KategoriTindakan();
                tindakan.setIdKategoriTindakan(obj[0] == null ? "" : obj[0].toString());
                tindakan.setKategoriTindakan(obj[1] == null ? "" : obj[1].toString());
                tindakanList.add(tindakan);
            }
        }

        return tindakanList;
    }

    public List<ImSimrsKategoriTindakanEntity> getKategoriTindakan(String kategoriTindakan ) throws HibernateException {
        List<ImSimrsKategoriTindakanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriTindakanEntity.class)
                .add(Restrictions.ilike("kategoriTindakan", kategoriTindakan))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kategori_tindakan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "KT"+sId;
    }

}
