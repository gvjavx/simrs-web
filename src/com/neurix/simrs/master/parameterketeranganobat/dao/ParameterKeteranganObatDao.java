package com.neurix.simrs.master.parameterketeranganobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;
import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParameterKeteranganObatDao extends GenericDao<ImSimrsParameterKeteranganObatEntity, String>{
    @Override
    protected Class<ImSimrsParameterKeteranganObatEntity> getEntityClass() {
        return ImSimrsParameterKeteranganObatEntity.class;
    }

    @Override
    public List<ImSimrsParameterKeteranganObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsParameterKeteranganObatEntity.class);
        if (mapCriteria != null){

            if (mapCriteria.get("id") != null){
                criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
            }
            if (mapCriteria.get("nama") != null){
                criteria.add(Restrictions.eq("nama", mapCriteria.get("nama").toString()));
            }
            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }

        }
        List<ImSimrsParameterKeteranganObatEntity> result = criteria.list();
        return result;
    }

    public List<ParameterKeteranganObat> getParameterKeterangan(String idJenis){
        List<ParameterKeteranganObat> keteranganObatList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "a.id,\n" +
                "a.nama\n" +
                "FROM im_simrs_paremeter_keterangan_obat a\n" +
                "INNER JOIN im_simrs_keterangan_obat b ON a.id = b.id_parameter_keterangan\n" +
                "WHERE b.id_sub_jenis = '"+idJenis+"' AND a.flag_label_waktu IS NULL \n" +
                "GROUP BY a.id, a.nama";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                ParameterKeteranganObat param = new ParameterKeteranganObat();
                param.setId(obj[0] != null ? obj[0].toString() : null);
                param.setNama(obj[1] != null ? obj[1].toString() : null);
                keteranganObatList.add(param);
            }
        }
        return keteranganObatList;
    }

    public List<ImSimrsParameterKeteranganObatEntity> getParameterKeteranganObat(String nama ) throws HibernateException {
        List<ImSimrsParameterKeteranganObatEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsParameterKeteranganObatEntity.class)
                .add(Restrictions.ilike("nama", nama))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }

    public List<KeteranganObat> getKeteranganObatWaktu(String idJenis) {
        List<KeteranganObat> keteranganObatList = new ArrayList<>();
        String SQL = "SELECT \n" +
                "b.id,\n" +
                "b.keterangan\n" +
                "FROM im_simrs_paremeter_keterangan_obat a\n" +
                "INNER JOIN im_simrs_keterangan_obat b ON a.id = b.id_parameter_keterangan\n" +
                "WHERE flag_label_waktu = 'Y'\n" +
                "AND b.id_sub_jenis = '" + idJenis + "'";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if (result.size() > 0) {
            for (Object[] obj : result) {
                KeteranganObat param = new KeteranganObat();
                param.setId(obj[0] != null ? obj[0].toString() : null);
                param.setKeterangan(obj[1] != null ? obj[1].toString() : null);
                keteranganObatList.add(param);
            }
        }
        return keteranganObatList;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_parameter_keterangan_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "PMO"+sId;
    }
}
