package com.neurix.simrs.master.keteranganobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.keteranganobat.model.ImSimrsKeteranganObatEntity;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeteranganObatDao extends GenericDao<ImSimrsKeteranganObatEntity, String>{

    @Override
    protected Class<ImSimrsKeteranganObatEntity> getEntityClass() {
        return ImSimrsKeteranganObatEntity.class;
    }

    @Override
    public List<ImSimrsKeteranganObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKeteranganObatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_sub_jenis") != null)
            criteria.add(Restrictions.eq("idSubJenis", mapCriteria.get("id_sub_jenis").toString()));
        if (mapCriteria.get("id_parameter_keterangan") != null)
            criteria.add(Restrictions.eq("idParameterKeterangan", mapCriteria.get("id_parameter_keterangan").toString()));
        if (mapCriteria.get("keterangan") != null)
            criteria.add(Restrictions.ilike("keterangan",  "%" + mapCriteria.get("keterangan").toString() + "%"));
        return criteria.list();

    }

    public List<KeteranganObat> getListSearchKeteranganObat(KeteranganObat bean) throws HibernateException{

        if (bean.getId() == null || "".equalsIgnoreCase(bean.getId()))
            bean.setId("%");
        if (bean.getIdSubJenis() == null || "".equalsIgnoreCase(bean.getIdSubJenis()))
            bean.setIdSubJenis("%");
        if (bean.getIdParameterKeterangan() == null || "".equalsIgnoreCase(bean.getIdParameterKeterangan()))
            bean.setIdParameterKeterangan("%");
        if (bean.getKeterangan() == null || "".equalsIgnoreCase(bean.getKeterangan()))
            bean.setKeterangan("%");
        if (bean.getFlag() == null || "".equalsIgnoreCase(bean.getFlag()))
            bean.setFlag("Y");

        String SQL = "SELECT \n" +
                "a.id,\n" +
                "a.id_sub_jenis,\n" +
                "b.nama as nama_sub_jenis,\n" +
                "a.id_parameter_keterangan,\n" +
                "c.nama as nama_parameter_keterangan,\n" +
                "a.keterangan,\n" +
                "a.flag,\n" +
                "a.action,\n" +
                "a.created_date,\n" +
                "a.created_who,\n" +
                "a.last_update,\n" +
                "a.last_update_who\n" +
                "FROM im_simrs_keterangan_obat a\n" +
                "LEFT JOIN im_simrs_jenis_persediaan_obat_sub b ON b.id = a.id_sub_jenis\n" +
                "LEFT JOIN im_simrs_paremeter_keterangan_obat c ON c.id = a.id_parameter_keterangan\n" +
                "WHERE a.flag = :flag \n" +
                "AND a.id_sub_jenis LIKE idSubJenis \n" +
                "AND a.id_parameter_keterangan LIKE :idParameterKeterangan \n" +
                "AND a.id LIKE :id \n" +
                "AND a.keterangan ILIKE :keterangan \n";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("flag", bean.getFlag())
                .setParameter("idSubJenis", bean.getIdSubJenis())
                .setParameter("idParameterKeterangan", bean.getIdParameterKeterangan())
                .setParameter("id", bean.getId())
                .setParameter("keterangan", bean.getKeterangan())
                .list();

        List<KeteranganObat> keteranganObats = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                KeteranganObat keteranganObat = new KeteranganObat();
                keteranganObat.setId(objToString(obj[0]));
                keteranganObat.setIdSubJenis(objToString(obj[1]));
                keteranganObat.setNamaSubJenis(objToString(obj[2]));
                keteranganObat.setIdParameterKeterangan(objToString(obj[3]));
                keteranganObat.setNamaParameterKeterangan(objToString(obj[4]));
                keteranganObat.setKeterangan(objToString(obj[5]));
                keteranganObat.setFlag(objToString(obj[6]));
                keteranganObat.setAction(objToString(obj[7]));
                keteranganObat.setCreatedDate(objToTimestamp(obj[8]));
                keteranganObat.setCreatedWho(objToString(obj[9]));
                keteranganObat.setLastUpdate(objToTimestamp(obj[10]));
                keteranganObat.setLastUpdateWho(objToString(obj[11]));
                keteranganObats.add(keteranganObat);
            }
        }

        return keteranganObats;
    }

    private String objToString(Object obj){
        if (obj != null)
            return obj.toString();
        return "";
    }

    private Timestamp objToTimestamp(Object obj){
        if (obj != null)
            return (Timestamp) obj;
        return null;
    }
}
