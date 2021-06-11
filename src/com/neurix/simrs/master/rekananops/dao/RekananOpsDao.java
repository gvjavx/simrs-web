package com.neurix.simrs.master.rekananops.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.ImSimrsDetailRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
            if (mapCriteria.get("tipe") != null) {
                criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        criteria.addOrder(Order.desc("createdDate"));
        List<ImSimrsRekananOpsEntity> results = criteria.list();
        return results;
    }

    public List<RekananOps> getComboRekananOps(String branchId, String isBpjs) {
        List<RekananOps> opsList = new ArrayList<>();
        if (branchId != null && !"".equalsIgnoreCase(branchId)) {
            String bpjs = "";
            if(isBpjs != null && !"".equalsIgnoreCase(isBpjs)){
                bpjs = "AND b.is_bpjs = '"+isBpjs+"' \n";
            }

            String SQL = "SELECT \n" +
                    "a.id_rekanan_ops,\n" +
                    "a.nomor_master,\n" +
                    "a.nama_rekanan,\n" +
                    "a.tipe,\n" +
                    "b.is_bpjs,\n" +
                    "ROUND((((100 - b.diskon) / 100)), 2) as sisa_persen\n" +
                    "FROM im_simrs_rekanan_ops a\n" +
                    "INNER JOIN im_simrs_detail_rekanan_ops b ON a.id_rekanan_ops = b.id_rekanan_ops\n" +
                    "WHERE b.branch_id = :branchId \n" +bpjs;

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branchId", branchId)
                    .list();

            if (result.size() > 0) {
                for (Object[] obj : result) {
                    RekananOps rekananOps = new RekananOps();
                    rekananOps.setIdRekananOps(obj[0] != null ? obj[0].toString() : "");
                    rekananOps.setNomorMaster(obj[1] != null ? obj[1].toString() : "");
                    rekananOps.setNamaRekanan(obj[2] != null ? obj[2].toString() : "");
                    rekananOps.setTipe(obj[3] != null ? obj[3].toString() : "");
                    rekananOps.setIsBpjs(obj[4] != null ? obj[4].toString() : "");
                    rekananOps.setDiskon(obj[5] != null ? new BigDecimal(obj[5].toString()) : null);
                    opsList.add(rekananOps);
                }
            }
        }
        return opsList;
    }

    public RekananOps getDetailRekananOps(String id, String branchId) {
        RekananOps ops = new RekananOps();
        if (id != null && !"".equalsIgnoreCase(id)) {
            String SQL = "SELECT \n" +
                    "a.id_rekanan_ops,\n" +
                    "a.nomor_master,\n" +
                    "a.nama_rekanan,\n" +
                    "b.is_bpjs,\n" +
                    "ROUND((((100 - b.diskon) / 100)), 2) as sisa_persen,\n" +
                    "a.tipe \n"+
                    "FROM im_simrs_rekanan_ops a\n" +
                    "INNER JOIN im_simrs_detail_rekanan_ops b ON a.id_rekanan_ops = b.id_rekanan_ops\n" +
                    "WHERE a.id_rekanan_ops = :id \n" +
                    "AND b.branch_id = :branchId \n";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .setParameter("branchId", branchId)
                    .list();

            if (result.size() > 0) {
                Object[] obj = result.get(0);
                ops.setIdRekananOps(obj[0] != null ? obj[0].toString() : "");
                ops.setNomorMaster(obj[1] != null ? obj[1].toString() : "");
                ops.setNamaRekanan(obj[2] != null ? obj[2].toString() : "");
                ops.setIsBpjs(obj[3] != null ? obj[3].toString() : "");
                ops.setDiskon(obj[4] != null ? new BigDecimal(obj[4].toString()) : null);
                ops.setTipe(obj[5] != null ? obj[5].toString() : "");
            }
        }
        return ops;
    }

    public RekananOps getRekananOpsByIdDetail(String id, String branchId) {
        RekananOps ops = new RekananOps();
        if (id != null && !"".equalsIgnoreCase(id)) {
            String SQL = "SELECT \n" +
                    "a.id_rekanan_ops,\n" +
                    "a.nomor_master,\n" +
                    "a.nama_rekanan,\n" +
                    "b.is_bpjs,\n" +
                    "ROUND((((100 - b.diskon) / 100)), 2) as sisa_persen\n" +
                    "FROM im_simrs_rekanan_ops a\n" +
                    "INNER JOIN (SELECT * FROM im_simrs_detail_rekanan_ops WHERE flag_parent = 'Y') b ON a.id_rekanan_ops = b.id_rekanan_ops\n" +
                    "INNER JOIN it_simrs_header_detail_checkup c ON a.id_rekanan_ops = c.id_asuransi\n" +
                    "WHERE c.id_detail_checkup = :id \n"+
                    "AND b.branch_id = :branchId \n" +
                    "AND b.flag_parent = 'Y' \n";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .setParameter("branchId", branchId)
                    .list();

            if (result.size() > 0) {
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

    public DetailRekananOps getTarifRekananByIdRekanan(String idRekananOps, String branchId, String idTindakan){

        String SQL = "SELECT \n" +
                "diskon_non_bpjs,\n" +
                "diskon_bpjs,\n" +
                "tarif,\n" +
                "tarif_bpjs\n" +
                "FROM im_simrs_detail_rekanan_ops\n" +
                "WHERE id_item = '"+idTindakan+"'\n" +
                "AND branch_id = '"+branchId+"'\n" +
                "AND id_rekanan_ops = '"+idRekananOps+"'\n";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        if (results.size() > 0){
            Object[] obj = results.get(0);
            DetailRekananOps detail = new DetailRekananOps();
            detail.setDiskonNonBpjs(objToBigDecimal(obj[0]));
            detail.setDiskonBpjs(objToBigDecimal(obj[1]));
            detail.setTarif(objToBigDecimal(obj[2]));
            detail.setTarifBpjs(objToBigDecimal(obj[3]));
            return detail;
        }
        return null;
    }

    public BigDecimal objToBigDecimal(Object obj){
        if (obj == null){
            return new BigDecimal(0);
        } else {
            return new BigDecimal(obj.toString());
        }
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekanan_ops')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "RKN" +sId;
    }

    public List<ImSimrsRekananOpsEntity> getRekananOps(String nomorMaster) throws HibernateException {
        List<ImSimrsRekananOpsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsRekananOpsEntity.class)
                .add(Restrictions.eq("nomorMaster", nomorMaster))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }
}
