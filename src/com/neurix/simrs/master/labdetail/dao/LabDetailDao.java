package com.neurix.simrs.master.labdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
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

public class LabDetailDao extends GenericDao<ImSimrsLabDetailEntity, String> {

    @Override
    protected Class<ImSimrsLabDetailEntity> getEntityClass() {
        return ImSimrsLabDetailEntity.class;
    }

    @Override
    public List<ImSimrsLabDetailEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsLabDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_lab_detail")!=null) {
                criteria.add(Restrictions.eq("idLabDetail", (String) mapCriteria.get("id_lab_detail")));
            }
            if (mapCriteria.get("id_lab")!=null) {
                criteria.add(Restrictions.eq("idLab", (String) mapCriteria.get("id_lab")));
            }
            if (mapCriteria.get("nama_detail_periksa")!=null) {
                criteria.add(Restrictions.ilike("namaDetailPeriksa", "%" + (String)mapCriteria.get("nama_detail_periksa") + "%"));
            }
//            if (mapCriteria.get("satuan")!=null) {
//                criteria.add(Restrictions.eq("satuan", (String) mapCriteria.get("satuan")));
//            }
//            if (mapCriteria.get("keterangan_acuan")!=null) {
//                criteria.add(Restrictions.eq("ketentuanAcuan", (String) mapCriteria.get("keterangan_acuan")));
//            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idLabDetail"));
        List<ImSimrsLabDetailEntity> results = criteria.list();
        return results;
    }

    public List<ImSimrsLabDetailEntity> getDataLabDetail(String namaDetailPeriksa) throws HibernateException {
        List<ImSimrsLabDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsLabDetailEntity.class)
                .add(Restrictions.eq("namaDetailPeriksa", namaDetailPeriksa))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextLabDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_lab_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "LDB" + sId;
    }

    public List<ImSimrsLabDetailEntity> cekData(String idLabDetail) throws HibernateException{
        List<ImSimrsLabDetailEntity> results = new ArrayList<>();

        String query = "SELECT a.id_lab_detail, b.id_periksa_lab_detail\n" +
                "FROM im_simrs_lab_detail a\n" +
                "INNER JOIN it_simrs_periksa_lab_detail b ON b.id_lab_detail = a.id_lab_detail\n" +
                "WHERE a.id_lab_detail = '"+idLabDetail+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    public List<LabDetail> getDetailLab(String idLab, String branchId){
        List<LabDetail> labDetailList = new ArrayList<>();
        if(idLab != null && !"".equalsIgnoreCase(idLab)){
            String SQL = "SELECT \n" +
                    "a.id_lab,\n" +
                    "b.id_lab_detail,\n" +
                    "b.nama_detail_periksa,\n" +
                    "b.satuan,\n" +
                    "b.keterangan_acuan,\n" +
                    "b.tarif\n" +
                    "FROM im_simrs_lab a\n" +
                    "INNER JOIN im_simrs_lab_detail b ON a.id_lab = b.id_lab\n" +
                    "WHERE b.id_lab = :id\n" +
                    "AND a.branch_id = :branchId";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idLab)
                    .setParameter("branchId", branchId)
                    .list();

            if(result.size() > 0){
                for (Object[] obj: result){
                    LabDetail labDetail = new LabDetail();
                    labDetail.setIdLab(obj[0] != null ? obj[0].toString() : "");
                    labDetail.setIdLabDetail(obj[1] != null ? obj[1].toString() : "");
                    labDetail.setNamaDetailPeriksa(obj[2] != null ? obj[2].toString() : "");
                    labDetail.setSatuan(obj[3] != null ? obj[3].toString() : "");
                    labDetail.setKetentuanAcuan(obj[4] != null ? obj[4].toString() : "");
                    labDetail.setTarif(obj[5] != null ? (BigDecimal) obj[5] : null);
                    labDetailList.add(labDetail);
                }
            }
        }
        return labDetailList;
    }
}