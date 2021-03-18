package com.neurix.simrs.transaksi.konsultasigizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.konsultasigizi.model.ItSimrsKonsultasiGiziEntity;
import com.neurix.simrs.transaksi.konsultasigizi.model.KonsultasiGizi;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.*;

public class KonsultasiGiziDao extends GenericDao<ItSimrsKonsultasiGiziEntity, String> {

    @Override
    protected Class<ItSimrsKonsultasiGiziEntity> getEntityClass() {
        return ItSimrsKonsultasiGiziEntity.class;
    }

    @Override
    public List<ItSimrsKonsultasiGiziEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsKonsultasiGiziEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_konsultasi_gizi") != null) {
                criteria.add(Restrictions.eq("idKonsultasiGizi", (String) mapCriteria.get("id_konsultasi_gizi")));
            }
            if (mapCriteria.get("no_checkup") != null) {
                criteria.add(Restrictions.eq("noCheckup", (String) mapCriteria.get("no_checkup")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
        }
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ItSimrsKonsultasiGiziEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_konsultasi_gizi')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "KON" + sId;
    }

    public List<KonsultasiGizi> pushNotif(String branchId){
        List<KonsultasiGizi> konsultasiGiziList = new ArrayList<>();
        String SQL = "SELECT \n" +
                "a.no_checkup,\n" +
                "b.id_detail_checkup,\n" +
                "a.nama,\n" +
                "a.id_pasien,\n" +
                "d.nama_pelayanan\n" +
                "FROM it_simrs_header_checkup a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN im_simrs_pelayanan c ON b.id_pelayanan = c.id_pelayanan\n" +
                "INNER JOIN im_simrs_header_pelayanan d ON c.id_header_pelayanan = d.id_header_pelayanan\n" +
                "INNER JOIN it_simrs_konsultasi_gizi e ON b.id_detail_checkup = e.id_detail_checkup\n" +
                "WHERE e.status = '0'\n" +
                "AND a.branch_id = '"+branchId+"'";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        if(results.size() > 0){
            for (Object[] obj: results){
                KonsultasiGizi gizi = new KonsultasiGizi();
                gizi.setNoCheckup(obj[0] != null ? obj[0].toString() : "");
                gizi.setIdDetailCheckup(obj[1] != null ? obj[1].toString() : "");
                gizi.setNama(obj[2] != null ? obj[2].toString() : "");
                gizi.setIdPasien(obj[3] != null ? obj[3].toString() : "");
                gizi.setNamaPelayanan(obj[4] != null ? obj[4].toString() : "");
                konsultasiGiziList.add(gizi);
            }
        }
        return konsultasiGiziList;
    }
}
