package com.neurix.simrs.transaksi.fisioterapi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.fisioterapi.model.ItSimrsFisioterapiEntity;
import com.neurix.simrs.transaksi.fisioterapi.model.MonitoringFisioterapi;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FisioterapiDao extends GenericDao<ItSimrsFisioterapiEntity, String> {

    @Override
    protected Class<ItSimrsFisioterapiEntity> getEntityClass() {
        return ItSimrsFisioterapiEntity.class;
    }

    @Override
    public List<ItSimrsFisioterapiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsFisioterapiEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_fisioterapi")!=null) {
                criteria.add(Restrictions.eq("idFisioterapi", (String) mapCriteria.get("id_fisioterapi")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idFisioterapi"));

        List<ItSimrsFisioterapiEntity> results = criteria.list();
        return results;
    }

    public List<MonitoringFisioterapi> getListMon(String idPasien, String branchId){
        List<MonitoringFisioterapi> monitoringFisioterapis = new ArrayList<>();
        String SQL = "SELECT\n" +
                "a.created_date,\n" +
                "a.nama_tindakan,\n" +
                "b.nama_dokter\n" +
                "FROM\n" +
                "it_simrs_tindakan_rawat a\n" +
                "INNER JOIN im_simrs_dokter b ON a.id_dokter = b.id_dokter\n" +
                "INNER JOIN it_simrs_header_detail_checkup c ON a.id_detail_checkup = c.id_detail_checkup\n" +
                "INNER JOIN im_simrs_pelayanan d ON c.id_pelayanan = d.id_pelayanan\n" +
                "INNER JOIN it_simrs_header_checkup e ON c.no_checkup = e.no_checkup\n" +
                "WHERE d.kategori_pelayanan = 'fisioterapi'\n" +
                "AND e.id_pasien = :id \n" +
                "AND e.branch_id = :branchId";
        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idPasien)
                .setParameter("branchId", branchId)
                .list();
        for (Object[] obj: result){
            MonitoringFisioterapi mon = new MonitoringFisioterapi();
            mon.setCreatedDate(obj[0] != null ? (Timestamp) obj[0] : null);
            mon.setTindakan(obj[1] != null ? obj[1].toString() : null);
            mon.setFisioterapi(obj[2] != null ? obj[2].toString() : null);
            monitoringFisioterapis.add(mon);
        }
        return monitoringFisioterapis;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_fisioterapi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}