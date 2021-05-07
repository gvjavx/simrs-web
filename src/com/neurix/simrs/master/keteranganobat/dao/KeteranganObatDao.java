package com.neurix.simrs.master.keteranganobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.keteranganobat.model.ImSimrsKeteranganObatEntity;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
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
        if (bean.getIdJenis() == null || "".equalsIgnoreCase(bean.getIdJenis()))
            bean.setIdJenis("%");
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
                "a.last_update_who,\n" +
                "d.id as id_jenis_obat,\n" +
                "d.nama as nama_jenis_obat,\n" +
                "a.warna_label,\n" +
                "a.warna_background\n" +
                "FROM im_simrs_keterangan_obat a\n" +
                "LEFT JOIN im_simrs_jenis_persediaan_obat_sub b ON b.id = a.id_sub_jenis\n" +
                "LEFT JOIN im_simrs_paremeter_keterangan_obat c ON c.id = a.id_parameter_keterangan\n" +
                "INNER JOIN im_simrs_jenis_persediaan_obat d ON d.id = b.id_jenis_obat\n" +
                "WHERE a.flag = :flag \n" +
                "AND a.id_sub_jenis LIKE :idSubJenis \n" +
                "AND a.id_parameter_keterangan LIKE :idParameterKeterangan \n" +
                "AND a.id LIKE :id \n" +
                "AND a.keterangan ILIKE :keterangan \n"+
                "AND d.id LIKE :idjenis \n" +
                "ORDER BY d.id, a.id_sub_jenis, c.id";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("flag", bean.getFlag())
                .setParameter("idSubJenis", bean.getIdSubJenis())
                .setParameter("idParameterKeterangan", bean.getIdParameterKeterangan())
                .setParameter("id", bean.getId())
                .setParameter("keterangan", bean.getKeterangan())
                .setParameter("idjenis", bean.getIdJenis())
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
                keteranganObat.setIdJenis(objToString(obj[12]));
                keteranganObat.setNamaJenis(objToString(obj[13]));
                keteranganObat.setWarnaLabel(objToString(obj[14]));
                keteranganObat.setWarnaBackground(objToString(obj[15]));
                keteranganObats.add(keteranganObat);
            }
        }

        return keteranganObats;
    }

    public List<KeteranganObat> getKeteranganObat(String idParam){
        List<KeteranganObat> keteranganObatList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "b.id,\n" +
                "b.keterangan\n" +
                "FROM im_simrs_paremeter_keterangan_obat a\n" +
                "INNER JOIN im_simrs_keterangan_obat b ON a.id = b.id_parameter_keterangan\n" +
                "WHERE b.id_parameter_keterangan = '"+idParam+"'";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                KeteranganObat param = new KeteranganObat();
                param.setId(obj[0] != null ? obj[0].toString() : null);
                param.setKeterangan(obj[1] != null ? obj[1].toString() : null);
                keteranganObatList.add(param);
            }
        }
        return keteranganObatList;
    }


    public boolean checkIfAvailableByCriteria(String idSubJenis, String idParam, String keterangan, String warnaLabel, String warnaBackground){

        String stWhere = "";
        if (warnaBackground != null && !"".equalsIgnoreCase(warnaBackground))
            stWhere += "AND warna_background = '"+warnaBackground+"' \n";
        if (warnaLabel != null && !"".equalsIgnoreCase(warnaLabel))
            stWhere += "AND warna_label = '"+warnaLabel+"' \n";

        String SQL = "SELECT id_sub_jenis, id_parameter_keterangan, keterangan \n" +
                "FROM im_simrs_keterangan_obat \n" +
                "WHERE id_sub_jenis = :idsubjenis\n" +
                "AND id_parameter_keterangan = :idparam\n" +
                "AND keterangan = :keterangan \n" + stWhere;

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idsubjenis", idSubJenis)
                .setParameter("idparam", idParam)
                .setParameter("keterangan", keterangan)
                .list();

        if (results != null && results.size() > 0)
            return true;
        return false;
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

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_keterangan_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%07d", iter.next());
        return "KTO" + sId;
    }

    public List<KeteranganObat> getListKeteranganObatBySubJenisObat(String id){

        String SQL = "SELECT id, id_sub_jenis, keterangan, level, parent_id, jenis\n" +
                "FROM im_simrs_keterangan_obat\n" +
                "WHERE id_sub_jenis = '"+id+"'\n" +
                "ORDER BY id_sub_jenis, id, level";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<KeteranganObat> keteranganObats = new ArrayList<>();

        if (list.size() > 0){
            for (Object[] obj : list){
                KeteranganObat keteranganObat = new KeteranganObat();
                keteranganObat.setId(obj[0].toString());
                keteranganObat.setIdSubJenis(obj[1].toString());
                keteranganObat.setKeterangan(obj[2].toString());
                keteranganObat.setLevel(obj[3].toString());
                keteranganObat.setParentId(obj[4] == null ? null : obj[4].toString());
                keteranganObat.setJenis(obj[5] == null ? null : obj[5].toString());
                keteranganObats.add(keteranganObat);
            }
        }

        return keteranganObats;
    }

    public List<KeteranganObat> getListKeteranganObatBySubJenisObatAndLevel(String id, String level){

        String SQL = "SELECT id, id_sub_jenis, keterangan, level, parent_id, jenis\n" +
                "FROM im_simrs_keterangan_obat\n" +
                "WHERE id_sub_jenis = '"+id+"'\n" +
                "AND level = '"+level+"'\n" +
                "ORDER BY id_sub_jenis, id, level";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<KeteranganObat> keteranganObats = new ArrayList<>();

        if (list.size() > 0){
            for (Object[] obj : list){
                KeteranganObat keteranganObat = new KeteranganObat();
                keteranganObat.setId(obj[0].toString());
                keteranganObat.setIdSubJenis(obj[1].toString());
                keteranganObat.setKeterangan(obj[2].toString());
                keteranganObat.setLevel(obj[3].toString());
                keteranganObat.setParentId(obj[4] == null ? null : obj[4].toString());
                keteranganObat.setJenis(obj[5] == null ? null : obj[5].toString());
                keteranganObats.add(keteranganObat);
            }
        }

        return keteranganObats;
    }

    public int getMaxLevelOfKeteranganBySubJenis(String id){

        String SQL = "SELECT MAX(level) as max_level, id_sub_jenis\n" +
                "FROM im_simrs_keterangan_obat\n" +
                "WHERE id_sub_jenis = '"+id+"'\n" +
                "GROUP BY id_sub_jenis\n" +
                "ORDER BY id_sub_jenis";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        if (list.size() > 0){
            Object[] obj = list.get(0);
            return Integer.valueOf(obj[0].toString());
        }

        return new Integer(0);
    }

    public String getIdSubJenisObat(String idObat){

        String SQL = "SELECT id_sub_jenis FROM im_simrs_header_obat WHERE id_obat = '"+idObat+"'\n";

        List<Object> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            return list.get(0).toString();
        }

        return null;
    }
}
