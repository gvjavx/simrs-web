package com.neurix.simrs.master.pasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class PasienDao extends GenericDao<ImSimrsPasienEntity,String> {

    @Override
    protected Class<ImSimrsPasienEntity> getEntityClass() {
        return ImSimrsPasienEntity.class;
    }

    @Override
    public List<ImSimrsPasienEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPasienEntity.class);

        if (mapCriteria != null){
            if(mapCriteria.get("id_pasien") != null){
                criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
            }
            if (mapCriteria.get("nama") != null){
                criteria.add(Restrictions.ilike("nama", "%"+ mapCriteria.get("nama").toString()+ "%"));
            }
            if (mapCriteria.get("desa_id") != null){
                criteria.add(Restrictions.eq("desaId", mapCriteria.get("desa_id").toString()));
            }
            if (mapCriteria.get("no_ktp") != null){
                criteria.add(Restrictions.eq("noKtp", mapCriteria.get("no_ktp").toString()));
            }
            if (mapCriteria.get("no_bpjs") != null){
                criteria.add(Restrictions.eq("noBpjs", mapCriteria.get("no_bpjs").toString()));
            }
            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }

        List<ImSimrsPasienEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    public List<ImSimrsPasienEntity> getListPasienByTmp(String tmp){

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPasienEntity.class);
        criteria.add(Restrictions.ilike("nama", tmp));
        criteria.add(Restrictions.eq("flag", "Y"));

        List<ImSimrsPasienEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    public List<Object[]> getListAlamat(String desaId){

        String sql = "SELECT \n" +
                "\t\tdesa.desa_id,\n" +
                "                desa.desa_name, \n" +
                "                kecamatan.kecamatan_name, \n" +
                "                kota.kota_name, \n" +
                "                provinsi.provinsi_name \n" +
                "                FROM \n" +
                "                (SELECT * FROM im_hris_desa) desa INNER JOIN  \n" +
                "                (SELECT * FROM im_hris_kecamatan) kecamatan ON desa.kecamatan_id = kecamatan.kecamatan_id INNER JOIN \n" +
                "                (SELECT * FROM im_hris_kota) kota ON kecamatan.kota_id = kota.kota_id INNER JOIN \n" +
                "                (SELECT * FROM im_hris_provinsi) provinsi ON kota.provinsi_id = provinsi.provinsi_id \n" +
                "                WHERE desa.desa_id = :desaId";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setParameter("desaId", desaId)
                .list();

        return result;
    }

    public String getNextIdPasien(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pasien')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
