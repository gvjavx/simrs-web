package com.neurix.simrs.master.rekananops.dao;

import com.neurix.common.dao.GenericDao;

import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.ImSimrsDetailRekananOpsEntity;
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

public class DetailRekananOpsDao extends GenericDao<ImSimrsDetailRekananOpsEntity, String> {

    @Override
    protected Class<ImSimrsDetailRekananOpsEntity> getEntityClass() {
        return ImSimrsDetailRekananOpsEntity.class;
    }

    @Override
    public List<ImSimrsDetailRekananOpsEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDetailRekananOpsEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_detail_rekanan_ops") != null) {
                criteria.add(Restrictions.eq("idDetailRekananOps", mapCriteria.get("id_detail_rekanan_ops").toString()));
            }

            if (mapCriteria.get("id_rekanan_ops") != null) {
                criteria.add(Restrictions.eq("idRekananOps", mapCriteria.get("id_rekanan_ops").toString()));
            }

            if (mapCriteria.get("is_bpjs") != null) {
                criteria.add(Restrictions.eq("isBpjs", mapCriteria.get("is_bpjs").toString()));
            }

            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }
        criteria.addOrder(Order.desc("createdDate"));
        List<ImSimrsDetailRekananOpsEntity> results = criteria.list();
        return results;
    }
    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_rekanan_ops')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "DRK"+sId;
    }

    public List<ImSimrsDetailRekananOpsEntity> getDetailRekananOps(String idRekananOps, String branchId ) throws HibernateException {
        List<ImSimrsDetailRekananOpsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDetailRekananOpsEntity.class)
                .add(Restrictions.eq("idRekananOps", idRekananOps))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)

        return results;
    }

    // Sigit 2021-03-10
    // mengambil data detail rekanan ops yg ber status parent saja dengan id
    public List<DetailRekananOps> getParentRekananOpsById(String id){

        String SQL = "SELECT \n" +
                "dro.id_detail_rekanan_ops, \n" +
                "ro.nama_rekanan\n" +
                "FROM im_simrs_detail_rekanan_ops dro\n" +
                "INNER JOIN im_simrs_rekanan_ops ro ON ro.id_rekanan_ops = dro.id_rekanan_ops\n" +
                "WHERE flag_parent = 'Y'\n" +
                "AND flag = 'Y'\n" +
                "AND dro.id_detail_rekanan_ops = '"+id+"'";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<DetailRekananOps> detailRekananOps = new ArrayList<>();

        if (result.size() > 0){

            for (Object[] obj : result){
                DetailRekananOps detail = new DetailRekananOps();
                detail.setIdDetailRekananOps(obj[0].toString());
                detail.setNamaRekanan(obj[1].toString());
                detailRekananOps.add(detail);

            }
        }

        return detailRekananOps;
    }

    // Sigit 2021-03-10
    // mengambil data detail rekanan ops yang berstatus selain parent Berdasarkan Parent id
    // yang dimana ditail nya ada tarif per tindakannya
    public List<DetailRekananOps> getListDetailRekananOpsByIdParent(String idParent){

        String SQL = "SELECT  \n" +
                "dro.id_detail_rekanan_ops,\n" +
                "dro.id_item,\n" +
                "ht.nama_tindakan, \n" +
                "dro.tarif,\n" +
                "dro.parent_id,\n" +
                "dro.flag\n" +
                "FROM (SELECT * FROM im_simrs_detail_rekanan_ops WHERE flag = 'Y' AND flag_parent != 'Y') dro\n" +
                "INNER JOIN (SELECT * FROM im_simrs_tindakan WHERE flag = 'Y') t ON t.id_tindakan = dro.id_item\n" +
                "INNER JOIN (SELECT * FROM  im_simrs_header_tindakan WHERE flag = 'Y') ht ON ht.id_header_tindakan = t.id_header_tindakan\n" +
                "WHERE parent_id = '"+idParent+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<DetailRekananOps> detailRekananOpsList = new ArrayList<>();

        if (list.size() > 0){
            for (Object[] obj : list){
                DetailRekananOps detail = new DetailRekananOps();
                detail.setIdDetailRekananOps(obj[0].toString());
                detail.setIdItem(obj[1].toString());
                detail.setNamaTindakan(obj[2].toString());
                detail.setTarif(obj[3] == null ? null : (BigDecimal) obj[3]);
                detail.setParentId(obj[4].toString());
                detail.setFlag(obj[5].toString());
                detailRekananOpsList.add(detail);
            }
        }

        return detailRekananOpsList;
    }
}
