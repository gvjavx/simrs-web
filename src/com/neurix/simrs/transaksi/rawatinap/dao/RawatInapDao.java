package com.neurix.simrs.transaksi.rawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class RawatInapDao extends GenericDao<ItSimrsRawatInapEntity, String> {
    @Override
    protected Class<ItSimrsRawatInapEntity> getEntityClass() {
        return ItSimrsRawatInapEntity.class;
    }

    @Override
    public List<ItSimrsRawatInapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRawatInapEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_rawat_inap") != null){
                criteria.add(Restrictions.eq("idRawatInap", mapCriteria.get("id_rawat_inap").toString()));
            }
            if (mapCriteria.get("id_detail_checkup") != null){
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
            }
            if (mapCriteria.get("no_checkup") != null){
                criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
            }
            if (mapCriteria.get("id_ruangan") != null){
                criteria.add(Restrictions.eq("idRuangan", mapCriteria.get("id_ruangan").toString()));
            }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        List<ItSimrsRawatInapEntity> result = criteria.list();
        return result;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rawat_inap')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<Object[]> getListAlamatByDesaId(String desaId) {
        String SQL = "SELECT \n" +
                "ds.desa_name, \n" +
                "kec.kecamatan_name,\n" +
                "kot.kota_name,\n" +
                "prov.provinsi_name\n" +
                "FROM \n" +
                "im_hris_desa ds\n" +
                "INNER JOIN im_hris_kecamatan kec ON kec.kecamatan_id = ds.kecamatan_id\n" +
                "INNER JOIN im_hris_kota kot ON kot.kota_id = kec.kota_id\n" +
                "INNER JOIN im_hris_provinsi prov ON prov.provinsi_id = kot.provinsi_id\n" +
                "WHERE ds.desa_id = :id ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", desaId)
                .list();

        return results;
    }
}
