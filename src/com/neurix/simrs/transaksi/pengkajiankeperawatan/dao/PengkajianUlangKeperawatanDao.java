package com.neurix.simrs.transaksi.pengkajiankeperawatan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.model.ItSimrsPengkajianUlangKeperawatanEntity;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.model.PengkajianUlangKeperawatan;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PengkajianUlangKeperawatanDao extends GenericDao<ItSimrsPengkajianUlangKeperawatanEntity, String> {

    @Override
    protected Class<ItSimrsPengkajianUlangKeperawatanEntity> getEntityClass() {
        return ItSimrsPengkajianUlangKeperawatanEntity.class;
    }

    @Override
    public List<ItSimrsPengkajianUlangKeperawatanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPengkajianUlangKeperawatanEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_pengkajian_ulang_keperawatan")!=null) {
                criteria.add(Restrictions.eq("idPengkajianUlangKeperawatan", (String) mapCriteria.get("id_pengkajian_ulang_keperawatan")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
            if (mapCriteria.get("kode_parameter")!=null) {
                criteria.add(Restrictions.eq("kodeParameter", (String) mapCriteria.get("kode_parameter")));
            }
            if (mapCriteria.get("tanggal")!=null) {
                criteria.add(Restrictions.eq("tanggal", (Date) mapCriteria.get("tanggal")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idPengkajianUlangKeperawatan"));

        List<ItSimrsPengkajianUlangKeperawatanEntity> results = criteria.list();
        return results;
    }

    public Boolean cekPengkajianUlangkeperawatan(PengkajianUlangKeperawatan bean, String tipe){
        Boolean response = false;
        if(bean != null){
            String SQL = "SELECT \n" +
                    "id_pengkajian_ulang_keperawatan,\n" +
                    "tanggal,\n" +
                    "id_detail_checkup,\n" +
                    "pagi,\n" +
                    "siang,\n" +
                    "malam\n" +
                    "FROM it_simrs_pengkajian_ulang_keperawatan\n" +
                    "WHERE id_detail_checkup = :id\n" +
                    "AND tanggal = :tanggal\n" +
                    "AND keterangan = :keterangan\n" +
                    "AND jenis = :jenis";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", bean.getIdDetailCheckup())
                    .setParameter("tanggal",bean.getTanggal())
                    .setParameter("jenis",bean.getJenis())
                    .setParameter("keterangan",bean.getKeterangan())
                    .list();

            if("insert".equalsIgnoreCase(tipe)){
                if(result.size() > 0){
                    response = true;
                }
            }
            if("update".equalsIgnoreCase(tipe)){
                if(result.size() > 0){
                    for (Object[] obj: result){
                        if("pagi".equalsIgnoreCase(bean.getWaktu())){
                            if(obj[3] != null){
                                response = true;
                            }
                        }
                        if("siang".equalsIgnoreCase(bean.getWaktu())){
                            if(obj[4] != null){
                                response = true;
                            }
                        }
                        if("malam".equalsIgnoreCase(bean.getWaktu())){
                            if(obj[5] != null){
                                response = true;
                            }
                        }
                    }
                }
            }
        }
        return response;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengkajian_ulang_keperawatan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}