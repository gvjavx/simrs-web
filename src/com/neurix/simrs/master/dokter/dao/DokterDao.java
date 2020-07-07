package com.neurix.simrs.master.dokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
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

/**
 * Created by Toshiba on 07/11/2019.
 */
public class DokterDao extends GenericDao<ImSimrsDokterEntity, String> {

    @Override
    protected Class<ImSimrsDokterEntity> getEntityClass() {
        return ImSimrsDokterEntity.class;
    }

    @Override
    public List<ImSimrsDokterEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class);
        if (mapCriteria != null){
            if (mapCriteria.get("id_dokter") != null){
                criteria.add(Restrictions.eq("idDokter", mapCriteria.get("id_dokter").toString()));
            }
            if (mapCriteria.get("nama_dokter") != null){
                criteria.add(Restrictions.ilike("namaDokter", "%" + (String)mapCriteria.get("nama_dokter") + "%"));
            }
            if (mapCriteria.get("id_pelayanan") != null){
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        List<ImSimrsDokterEntity> result = criteria.list();
        return result;
    }

    public List<Dokter> getListDokterByPelayanan(String idPelayanan){

        List<Dokter> list = new ArrayList<>();

        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)){

            String SQL = "SELECT \n" +
                    "a.id_dokter, \n" +
                    "a.nama_dokter, \n" +
                    "a.kode_dpjp \n" +
                    "FROM im_simrs_dokter a\n" +
                    "INNER JOIN im_simrs_dokter_pelayanan b ON a.id_dokter = b.id_dokter\n" +
                    "WHERE b.id_pelayanan = :id";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPelayanan)
                    .list();

            if(result.size() > 0){

                for (Object[] obj: result){
                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(obj[0] == null ? "" : obj[0].toString());
                    dokter.setNamaDokter(obj[1] == null ? "" : obj[1].toString());
                    dokter.setKodeDpjp(obj[2] == null ? "" : obj[2].toString());
                    list.add(dokter);
                }
            }
        }
        return list;
    }

    //for typeahead
    public List<ImSimrsDokterEntity> getDokterListByLike(String name) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("kodering", name + "%"),
                        Restrictions.ilike("namaDokter", "%"+name+"%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idDokter"));

        List<ImSimrsDokterEntity> results = criteria.list();
        return results;
    }

    public List<ImSimrsDokterEntity> getDataDokter(String namaDokter) throws HibernateException {
        List<ImSimrsDokterEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class)
                .add(Restrictions.eq("namaDokter", namaDokter))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ImSimrsDokterEntity> getDataDokterByKodering(String kodering) throws HibernateException {
        List<ImSimrsDokterEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class)
                .add(Restrictions.eq("kodering", kodering))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public String getNextDokter() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_dokter')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "DKR" + sId;
    }

    public String getNextKodering() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kodering')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%01d", iter.next());

        return sId;
    }

    public List<ImSimrsDokterEntity> cekData(String idDokter) throws HibernateException{
        List<ImSimrsDokterEntity> results = new ArrayList<>();

        String query = "SELECT a.id_team_dokter, b.id_dokter\n" +
                "FROM it_simrs_dokter_team a\n" +
                "INNER JOIN im_simrs_dokter b ON b.id_dokter = a.id_dokter\n" +
                "WHERE a.id_dokter = '"+idDokter+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    //for typeahead
    public List<ImSimrsDokterEntity> getDokterListByLikeDokterName(String dokterName) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("idDokter", dokterName + "%"),
                        Restrictions.ilike("namaDokter", "%"+dokterName+"%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idDokter"));

        List<ImSimrsDokterEntity> results = criteria.list();
        return results;
    }
}
