package com.neurix.simrs.transaksi.checkupdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsKontrolUlangEntity;
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

public class KontrolUlangDao extends GenericDao<ItSimrsKontrolUlangEntity, String> {

    @Override
    protected Class<ItSimrsKontrolUlangEntity> getEntityClass() {
        return ItSimrsKontrolUlangEntity.class;
    }

    @Override
    public List<ItSimrsKontrolUlangEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsKontrolUlangEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_kontrol_ulang") != null) {
                criteria.add(Restrictions.eq("idKontrolUlang", (String) mapCriteria.get("id_kontrol_ulang")));
            }
            if (mapCriteria.get("no_checkup") != null) {
                criteria.add(Restrictions.eq("noCheckup", (String) mapCriteria.get("no_checkup")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }
        List<ItSimrsKontrolUlangEntity> results = criteria.list();
        return results;
    }

    public List<HeaderDetailCheckup> getKontrolUlang(HeaderDetailCheckup bean) {
        List<HeaderDetailCheckup> headerDetailCheckupList = new ArrayList<>();
        if(bean != null){
            String idPasien = "%";
            String namaPasien = "%";
            String idPelayanan = "%";

            if(bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
                idPasien = bean.getIdPasien();
            }
            if(bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())){
                namaPasien = bean.getNamaPasien();
            }
            if(bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                idPelayanan = bean.getIdPelayanan();
            }

            String SQL = "SELECT\n" +
                    "b.id_kontrol_ulang,\n" +
                    "a.id_pasien,\n" +
                    "a.nama,\n" +
                    "b.tgl_kontrol,\n" +
                    "b.id_pelayanan,\n" +
                    "c.nama_pelayanan,\n" +
                    "b.id_dokter,\n" +
                    "d.nama_dokter,\n" +
                    "e.desa_name, \n"+
                    "a.no_telp, \n"+
                    "CAST(EXTRACT(YEAR FROM (AGE(a.tgl_lahir))) AS VARCHAR) as umur\n"+
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_kontrol_ulang b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN (\n" +
                    " SELECT\n" +
                    " b.branch_id,\n" +
                    " b.id_pelayanan,\n" +
                    " a.nama_pelayanan\n" +
                    " FROM im_simrs_header_pelayanan a\n" +
                    " INNER JOIN im_simrs_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                    ") c ON b.id_pelayanan = c.id_pelayanan\n" +
                    "INNER JOIN im_simrs_dokter d ON b.id_dokter = d.id_dokter\n" +
                    "INNER JOIN im_hris_desa e ON CAST(a.desa_id AS VARCHAR) = e.desa_id\n" +
                    "WHERE b.flag = 'Y'\n" +
                    "AND a.id_pasien ILIKE :idPasien \n" +
                    "AND a.nama ILIKE :namaPasien \n" +
                    "AND c.id_pelayanan ILIKE :idPelayanan ";

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPasien", idPasien)
                    .setParameter("namaPasien", namaPasien)
                    .setParameter("idPelayanan", idPelayanan)
                    .list();

            if (result.size() > 0) {
                for (Object[] obj: result){
                    HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                    detailCheckup.setIdKontrolUlang(obj[0] != null ? obj[0].toString() : null);
                    detailCheckup.setIdPasien(obj[1] != null ? obj[1].toString() : null);
                    detailCheckup.setNamaPasien(obj[2] != null ? obj[2].toString() : null);
                    detailCheckup.setTglCekup(obj[3] != null ? (Date) obj[3] : null);
                    detailCheckup.setIdPelayanan(obj[4] != null ? obj[4].toString() : null);
                    detailCheckup.setNamaPelayanan(obj[5] != null ? obj[5].toString() : null);
                    detailCheckup.setIdDokter(obj[6] != null ? obj[6].toString() : null);
                    detailCheckup.setNamaDokter(obj[7] != null ? obj[7].toString() : null);
                    detailCheckup.setDesa(obj[8] != null ? obj[8].toString() : null);
                    detailCheckup.setNoTelp(obj[9] != null ? obj[9].toString() : null);
                    detailCheckup.setUmur(obj[10] != null ? obj[10].toString() : null);
                    headerDetailCheckupList.add(detailCheckup);
                }
            }
        }
        return headerDetailCheckupList;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kontrol_ulang')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "KUL"+sId;
    }
}
