package com.neurix.simrs.transaksi.makananpendamping.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.makananpendamping.model.HeaderPendampingMakanan;
import com.neurix.simrs.transaksi.makananpendamping.model.ItSimrsHeaderPendampingMakananEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.*;

public class HeaderPendampingMakananDao extends GenericDao<ItSimrsHeaderPendampingMakananEntity, String> {
    @Override
    protected Class<ItSimrsHeaderPendampingMakananEntity> getEntityClass() {
        return ItSimrsHeaderPendampingMakananEntity.class;
    }

    @Override
    public List<ItSimrsHeaderPendampingMakananEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderPendampingMakananEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_header_pendamping_makanan") != null) {
                criteria.add(Restrictions.eq("idHeaderPendampingMakanan", mapCriteria.get("id_header_pendamping_makanan").toString()));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
            }
            if (mapCriteria.get("id_ruangan") != null) {
                criteria.add(Restrictions.eq("idRuangan", mapCriteria.get("id_ruangan").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }
        criteria.addOrder(Order.asc("idHeaderPendampingMakanan"));
        List<ItSimrsHeaderPendampingMakananEntity> resilt = criteria.list();
        return resilt;
    }

    public List<HeaderPendampingMakanan> getListSearch(HeaderPendampingMakanan bean){
        List<HeaderPendampingMakanan> headerPendampingMakananList = new ArrayList<>();
        if(bean != null){
            String idRuangan = "%";
            String idHead = "%";
            String idDet = "%";
            String idPas = "%";
            String nama = "%";
            String status = "%";
            String idKelas = "%";

            if(bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
                idRuangan = bean.getIdRuangan();
            }
            if(bean.getIdHeaderPendampingMakanan() != null && !"".equalsIgnoreCase(bean.getIdHeaderPendampingMakanan())){
                idHead = bean.getIdHeaderPendampingMakanan();
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                idDet = bean.getIdDetailCheckup();
            }
            if(bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
                idPas = bean.getIdPasien();
            }
            if(bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())){
                nama = "%"+bean.getNama()+"%";
            }
            if(bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())){
                status = bean.getStatus();
            }
            if(bean.getIdKelas() != null && !"".equalsIgnoreCase(bean.getIdKelas())){
                idKelas = bean.getIdKelas();
            }

            String SQL = "SELECT\n" +
                    "a.id_header_pendamping_makanan,\n" +
                    "b.id_detail_checkup,\n" +
                    "c.id_pasien,\n" +
                    "c.nama,\n" +
                    "e.no_ruangan,\n" +
                    "e.nama_ruangan,\n" +
                    "a.status\n" +
                    "FROM it_simrs_header_pendamping_makanan a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                    "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                    "INNER JOIN mt_simrs_ruangan_tempat_tidur d ON a.id_ruangan = d.id_tempat_tidur\n" +
                    "INNER JOIN mt_simrs_ruangan e ON d.id_ruangan = e.id_ruangan\n" +
                    "WHERE a.flag = 'Y'\n" +
                    "AND e.id_ruangan LIKE :idRuangan\n" +
                    "AND e.id_kelas_ruangan LIKE :idKelas\n" +
                    "AND a.id_header_pendamping_makanan LIKE :idHead\n" +
                    "AND a.id_detail_checkup LIKE :idDet\n" +
                    "AND c.id_pasien LIKE :idPas \n" +
                    "AND a.status LIKE :sts \n" +
                    "AND c.nama ILIKE :nama \n";
            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idRuangan", idRuangan)
                    .setParameter("idHead", idHead)
                    .setParameter("idDet", idDet)
                    .setParameter("idPas", idPas)
                    .setParameter("nama", nama)
                    .setParameter("sts", status)
                    .setParameter("idKelas", idKelas)
                    .list();

            if(result.size() > 0){
                for (Object[] obj: result){
                    HeaderPendampingMakanan makanan = new HeaderPendampingMakanan();
                    makanan.setIdHeaderPendampingMakanan(obj[0] != null ? obj[0].toString() : "");
                    makanan.setIdDetailCheckup(obj[1] != null ? obj[1].toString() : "");
                    makanan.setIdPasien(obj[2] != null ? obj[2].toString() : "");
                    makanan.setNama(obj[3] != null ? obj[3].toString() : "");
                    makanan.setNoRuangan(obj[4] != null ? obj[4].toString() : "");
                    makanan.setNamaRuangan(obj[5] != null ? obj[5].toString() : "");
                    makanan.setStatus(obj[6] != null ? obj[6].toString() : "");
                    headerPendampingMakananList.add(makanan);
                }
            }
        }
        return headerPendampingMakananList;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_header_pendamping_makanan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "HPM"+sId;
    }
}
