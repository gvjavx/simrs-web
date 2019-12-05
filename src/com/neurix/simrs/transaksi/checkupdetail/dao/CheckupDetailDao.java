package com.neurix.simrs.transaksi.checkupdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class CheckupDetailDao extends GenericDao<ItSimrsHeaderDetailCheckupEntity, String>{
    @Override
    protected Class<ItSimrsHeaderDetailCheckupEntity> getEntityClass() {
        return ItSimrsHeaderDetailCheckupEntity.class;
    }

    @Override
    public List<ItSimrsHeaderDetailCheckupEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderDetailCheckupEntity.class);
        if (mapCriteria != null)

            if (mapCriteria.get("id_detail_checkup") != null)
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
            if (mapCriteria.get("no_checkup") != null)
                criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
            if (mapCriteria.get("id_pelayanan") != null)
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            if (mapCriteria.get("status_periksa") != null)
                criteria.add(Restrictions.eq("statusPeriksa", mapCriteria.get("status_periksa").toString()));
            if (mapCriteria.get("status_bayar") != null)
                criteria.add(Restrictions.eq("statusBayar", mapCriteria.get("status_bayar").toString()));
            if (mapCriteria.get("branch_id") != null)
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            if (mapCriteria.get("today") != null)
                criteria.add(Restrictions.eq("createdDate", (Date) mapCriteria.get("today")));

        criteria.addOrder(Order.asc("tglAntrian"));
        List<ItSimrsHeaderDetailCheckupEntity> result = criteria.list();
        return result;
    }

    public List<HeaderDetailCheckup> getSearchRawatJalan(HeaderDetailCheckup bean){
        List<HeaderDetailCheckup> checkupList = new ArrayList<>();
        if (bean != null){

            String idPasien = "%";
            String nama = "%";
            String idPelayanan = "%";
            String statusPeriksa = "%";

            String dateFrom = "";
            String dateTo = "";

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())){
                nama = bean.getNamaPasien();
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())){
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())){
                dateFrom = bean.getStDateFrom();
            }

            if (bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())){
                dateTo = bean.getStDateTo();
            }


            String SQL = "\n" +
                    "SELECT \n" +
                    "dt.id_detail_checkup,\n" +
                    "hd.no_checkup,\n" +
                    "hd.id_pasien,\n" +
                    "hd.nama,\n" +
                    "hd.jalan,\n" +
                    "hd.created_date,\n" +
                    "hd.desa_id,\n" +
                    "dt.status_periksa,\n" +
                    "st.keterangan,\n" +
                    "dt.keterangan_selesai\n" +
                    "FROM \n" +
                    "it_simrs_header_checkup hd\n" +
                    "INNER JOIN it_simrs_header_detail_checkup dt ON dt.no_checkup = hd.no_checkup\n" +
                    "INNER JOIN im_simrs_status_pasien st ON st.id_status_pasien = dt.status_periksa\n" +
                    "LEFT JOIN it_simrs_rawat_inap ri ON ri.id_detail_checkup = dt.id_detail_checkup\n" +
                    "WHERE ri.id_detail_checkup is null\n" +
                    "AND hd.id_pasien LIKE :idPasien \n" +
                    "AND hd.nama LIKE :nama \n" +
                    "AND dt.id_pelayanan LIKE :idPelayanan \n" +
                    "AND dt.status_periksa LIKE :status";

            List<Object[]> results = new ArrayList<>();
            if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)){

                SQL = SQL + "\n AND hd.created_date > :dateFrom AND hd.created_date < :dateTo " +
                        "\n ORDER BY dt.tgl_antrian ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("status", statusPeriksa)
                        .setParameter("dateFrom", dateFrom)
                        .setParameter("dateTo", dateTo)
                        .list();

            } else {

                SQL = SQL + "\n  ORDER BY dt.tgl_antrian ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("status", statusPeriksa)
                        .list();
            }

            if (!results.isEmpty()){
                HeaderDetailCheckup headerDetailCheckup;
                for (Object[] obj : results){
                    headerDetailCheckup = new HeaderDetailCheckup();
                    headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                    headerDetailCheckup.setNoCheckup(obj[1].toString());
                    headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                    headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                    String jalan = obj[4] == null ? "" : obj[4].toString();

                    headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                    headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                    headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                    headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                    headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());

                    if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())){
                        List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
                        if (!objDesaList.isEmpty()){
                            for (Object[] objDesa : objDesaList){

                                String alamatLengkap =
                                        "Desa. "+ objDesa[0].toString() +
                                        " Kec. " + objDesa[1].toString() +
                                        " " + objDesa[2].toString() +
                                        " Prov. " + objDesa[3].toString();

                                if (!"".equalsIgnoreCase(jalan)){
                                    jalan = jalan + ", " + alamatLengkap;
                                } else {
                                    jalan = alamatLengkap;
                                }
                            }
                        }
                    }

                    headerDetailCheckup.setAlamat(jalan);
                    checkupList.add(headerDetailCheckup);
                }
            }
        }
        return checkupList;
    }

    public List<Object[]> getListAlamatByDesaId(String desaId) {
        String SQL = "SELECT \n" +
                "ds.desa_name, \n" +
                "kec.kecamatan_name,\n" +
                "kot.kota_name,\n" +
                "prov.provinsi_name\n" +
                "FROM \n" +
                "im_hris_desa ds\n" +
                "INNER JOIN im_hris_kecamatan kec ON kec.kecamatan_id = ds.kecamatan_id\n" +
                "INNER JOIN im_hris_kota kot ON kot.kota_id = kec.kota_id\n" +
                "INNER JOIN im_hris_provinsi prov ON prov.provinsi_id = kot.provinsi_id\n" +
                "WHERE ds.desa_id = :id ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", desaId)
                .list();

        return results;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_checkup')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
