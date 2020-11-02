package com.neurix.simrs.transaksi.asesmenrawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.AsesmenRawatInap;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ItSimrsAsesmenRawatInapEntity;
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

public class AsesmenRawatInapDao extends GenericDao<ItSimrsAsesmenRawatInapEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenRawatInapEntity> getEntityClass() {
        return ItSimrsAsesmenRawatInapEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenRawatInapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenRawatInapEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_asesmen_keperawatan_rawat_inap") != null) {
                criteria.add(Restrictions.eq("idAsesmenKeperawatanRawatInap", (String) mapCriteria.get("id_asesmen_keperawatan_rawat_inap")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan") != null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("jenis") != null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
            if (mapCriteria.get("created_date") != null) {
                criteria.add(Restrictions.eq("createdDate", (Timestamp) mapCriteria.get("created_date")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idAsesmenKeperawatanRawatInap"));

        List<ItSimrsAsesmenRawatInapEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_rawat_inap')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public Boolean cekHandOver(AsesmenRawatInap bean) {
        Boolean response = false;
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup()) &&
                bean.getJawaban() != null && !"".equalsIgnoreCase(bean.getJawaban()) &&
                bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {

            String SQL = "SELECT \n" +
                    "id_asesmen_keperawatan_rawat_inap,\n" +
                    "id_detail_checkup\n" +
                    "FROM it_simrs_asesmen_keperawatan_rawat_inap\n" +
                    "WHERE jenis = :jenis\n" +
                    "AND jawaban = :waktu\n" +
                    "AND id_detail_checkup = :id\n" +
                    "AND CAST(created_date AS DATE) = CURRENT_DATE";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("waktu", bean.getJawaban())
                    .setParameter("id", bean.getIdDetailCheckup())
                    .setParameter("jenis", bean.getJenis())
                    .list();
            if (result.size() > 0) {
                response = true;
            }
        }
        return response;
    }

    public List<AsesmenRawatInap> getListAsesmenRI(String id, String jenis){
        List<AsesmenRawatInap> riList = new ArrayList<>();
        if(id != null && !"".equalsIgnoreCase(id) && jenis != null && !"".equalsIgnoreCase(jenis)){
            String SQL = "SELECT \n" +
                    "a.id_asesmen_keperawatan_rawat_inap,\n" +
                    "c.no_checkup,\n" +
                    "a.id_detail_checkup,\n" +
                    "a.parameter,\n" +
                    "a.jawaban,\n" +
                    "a.keterangan,\n" +
                    "a.jenis,\n" +
                    "a.skor,\n" +
                    "a.informasi,\n" +
                    "a.tipe,\n" +
                    "a.nama_terang,\n" +
                    "a.sip\n" +
                    "FROM it_simrs_asesmen_keperawatan_rawat_inap a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                    "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                    "WHERE c.no_checkup = :id\n" +
                    "AND a.jenis = :jenis AND a.flag = 'Y'";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .setParameter("jenis", jenis)
                    .list();
            if(result.size() > 0){
                for (Object[] obj: result){
                    AsesmenRawatInap ar = new AsesmenRawatInap();
                    ar.setIdAsesmenKeperawatanRawatInap(obj[0] != null ? obj[0].toString() : null);
                    ar.setNoCheckup(obj[1] != null ? obj[1].toString() : null);
                    ar.setIdDetailCheckup(obj[2] != null ? obj[2].toString() : null);
                    ar.setParameter(obj[3] != null ? obj[3].toString() : null);
                    ar.setJawaban(obj[4] != null ? obj[4].toString() : null);
                    ar.setKeterangan(obj[5] != null ? obj[5].toString() : null);
                    ar.setJenis(obj[6] != null ? obj[6].toString() : null);
                    ar.setSkor(obj[7] != null ? Integer.valueOf(obj[7].toString()) : null);
                    ar.setInformasi(obj[8] != null ? obj[8].toString() : null);
                    ar.setTipe(obj[9] != null ? obj[9].toString() : null);
                    ar.setNamaTerang(obj[10] != null ? obj[10].toString() : null);
                    ar.setSip(obj[11] != null ? obj[11].toString() : null);
                    riList.add(ar);
                }
            }
        }
        return riList;
    }
}