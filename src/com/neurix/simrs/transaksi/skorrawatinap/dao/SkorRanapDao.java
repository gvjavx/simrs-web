package com.neurix.simrs.transaksi.skorrawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.SkorRanap;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 10/02/20.
 */
public class SkorRanapDao extends GenericDao<ItSimrsSkorRanapEntity, String> {
    @Override
    protected Class<ItSimrsSkorRanapEntity> getEntityClass() {
        return ItSimrsSkorRanapEntity.class;
    }

    @Override
    public List<ItSimrsSkorRanapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsSkorRanapEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("group_id") != null)
            criteria.add(Restrictions.eq("groupId", mapCriteria.get("group_id").toString()));
        if (mapCriteria.get("id_kategori") != null)
            criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
        if (mapCriteria.get("no_checkup") != null)
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        if (mapCriteria.get("date") != null){
            String stDate = mapCriteria.get("date").toString();
            criteria.add(Restrictions.sqlRestriction("created_date = '"+stDate+"'"));
        }

        List<ItSimrsSkorRanapEntity> results = criteria.list();
        return results;
    }

    public String getNextGroupSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_group_skor_ranap')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_skor_ranap')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<SkorRanap> getListSumSkorRanap(String noCheckup, String idDetail, String kat){

        String nc = "%";
        String dt = "%";
        String kt = "%";

        if (noCheckup != null && !"".equalsIgnoreCase(noCheckup))
            nc = noCheckup;
        if (idDetail != null && !"".equalsIgnoreCase(idDetail))
            dt = idDetail;
        if (kat != null && !"".equalsIgnoreCase(kat))
            kt = kat;

        String SQL = "SELECT \n" +
                "its.group_id,\n" +
                "kat.id_kategori, \n" +
                "kat.nama_kategori,\n" +
                "SUM(its.skor) as skor,\n" +
                "its.created_date,\n" +
                "its.created_who\n" +
                "FROM it_simrs_skor_rawat_inap its\n" +
                "INNER JOIN im_simrs_kategori_skor_rawat_inap kat ON kat.id_kategori = its.id_kategori\n" +
                "WHERE its.no_checkup LIKE :nc \n" +
                "AND its.id_detail_checkup LIKE :dt \n" +
                "AND kat.id_kategori LIKE :kt \n" +
                "GROUP BY \n" +
                "its.group_id,\n" +
                "kat.id_kategori, \n" +
                "kat.nama_kategori,\n" +
                "its.created_date,\n" +
                "its.created_who";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("nc" , nc)
                .setParameter("dt", dt)
                .setParameter("kt", kt)
                .list();

        List<SkorRanap> skorRanaps = new ArrayList<>();
        if (results.size() > 0){
            SkorRanap skorRanap;
            for (Object[] obj : results){
                skorRanap = new SkorRanap();
                skorRanap.setGroupId(obj[0].toString());
                skorRanap.setIdKategori(obj[1].toString());
                skorRanap.setNamaKategori(obj[2].toString());
                skorRanap.setSkor(obj[3] == null ? "" : String.valueOf((BigInteger) obj[3]));
                skorRanap.setStDate(obj[4] == null ? "" : String.valueOf((Timestamp) obj[4]));
                skorRanap.setCreatedWho(obj[5].toString());
                skorRanaps.add(skorRanap);
            }
        }

        return skorRanaps;
    }

}
