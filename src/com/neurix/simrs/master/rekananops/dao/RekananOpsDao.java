package com.neurix.simrs.master.rekananops.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RekananOpsDao extends GenericDao<ImSimrsRekananOpsEntity, String> {

    @Override
    protected Class<ImSimrsRekananOpsEntity> getEntityClass() {
        return ImSimrsRekananOpsEntity.class;
    }

    @Override
    public List<ImSimrsRekananOpsEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsRekananOpsEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_rekanan_ops") != null) {
                criteria.add(Restrictions.eq("idRekananOps", mapCriteria.get("id_rekanan_ops").toString()));
            }

            if (mapCriteria.get("nomor_master") != null) {
                criteria.add(Restrictions.eq("nomorMaster", mapCriteria.get("nomor_master").toString()));
            }

            if (mapCriteria.get("is_bpjs") != null) {
                criteria.add(Restrictions.eq("isBpjs", mapCriteria.get("is_bpjs")));
            }

            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        criteria.addOrder(Order.desc("createdDate"));
        List<ImSimrsRekananOpsEntity> results = criteria.list();
        return results;
    }

    public RekananOps getDetailRekananOps(String id){
        RekananOps ops = new RekananOps();
        if(id != null && !"".equalsIgnoreCase(id)){
            String SQL = "SELECT \n" +
                    "id_rekanan_ops,\n" +
                    "nomor_master,\n" +
                    "nama_rekanan,\n" +
                    "is_bpjs,\n" +
                    "ROUND((((100 - diskon) / 100)), 2) as sisa_persen\n" +
                    "FROM im_simrs_rekanan_ops\n" +
                    "WHERE id_rekanan_ops = :id ";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .list();

            if(result.size() > 0){
                Object[] obj = result.get(0);
                ops.setIdRekananOps(obj[0] != null ? obj[0].toString() : "");
                ops.setNomorMaster(obj[1] != null ? obj[1].toString() : "");
                ops.setNamaRekanan(obj[2] != null ? obj[2].toString() : "");
                ops.setIsBpjs(obj[3] != null ? obj[3].toString() : "");
                ops.setDiskon(obj[4] != null ? new BigDecimal(obj[4].toString()) : null);
            }
        }
        return ops;
    }

    public RekananOps getRekananOpsByIdDetail(String id){
        RekananOps ops = new RekananOps();
        if(id != null && !"".equalsIgnoreCase(id)){
            String SQL = "SELECT \n" +
                    "a.id_rekanan_ops,\n" +
                    "a.nomor_master,\n" +
                    "a.nama_rekanan,\n" +
                    "a.is_bpjs,\n" +
                    "ROUND((((100 - a.diskon) / 100)), 2) as sisa_persen\n" +
                    "FROM im_simrs_rekanan_ops a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.id_rekanan_ops = b.id_asuransi\n" +
                    "WHERE b.id_detail_checkup = :id";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .list();

            if(result.size() > 0){
                Object[] obj = result.get(0);
                ops.setIdRekananOps(obj[0] != null ? obj[0].toString() : "");
                ops.setNomorMaster(obj[1] != null ? obj[1].toString() : "");
                ops.setNamaRekanan(obj[2] != null ? obj[2].toString() : "");
                ops.setIsBpjs(obj[3] != null ? obj[3].toString() : "");
                ops.setDiskon(obj[4] != null ? new BigDecimal(obj[4].toString()) : null);
            }
        }
        return ops;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekanan_ops')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }
}
