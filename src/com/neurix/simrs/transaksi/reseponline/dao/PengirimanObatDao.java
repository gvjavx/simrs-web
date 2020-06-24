package com.neurix.simrs.transaksi.reseponline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsPengirimanObatEntity;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 18/06/20.
 */
public class PengirimanObatDao extends GenericDao<ItSimrsPengirimanObatEntity, String> {

    @Override
    protected Class<ItSimrsPengirimanObatEntity> getEntityClass() {
        return ItSimrsPengirimanObatEntity.class;
    }

    @Override
    public List<ItSimrsPengirimanObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPengirimanObatEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_pasien") != null)
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        if (mapCriteria.get("id_pelayanan") != null)
            criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
        if (mapCriteria.get("id_kurir") != null)
            criteria.add(Restrictions.eq("idKurir", mapCriteria.get("id_kurir").toString()));
        if (mapCriteria.get("id_resep") != null)
            criteria.add(Restrictions.eq("idResep", mapCriteria.get("id_resep").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengiriman_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<PengirimanObat> getPengirimanById(String idKurir, String idPasien) {

       String query = "";

       String searchIdPasien = "";
       String searchIdKurir = "";

       if (idPasien != null && !idPasien.equalsIgnoreCase("")) {
           searchIdPasien = " and a.id_pasien = '" + idPasien + "' ";
       }
       if (idKurir != null && !idKurir.equalsIgnoreCase("")) {
           searchIdKurir = " and a.id_kurir = '" + idKurir + "' ";
       }

       List<PengirimanObat> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        query = "SELECT a.id, a.id_kurir, a.id_pasien, a.id_pelayanan, a.branch_id, a.alamat, a.no_telp, a.flag_pickup, a.flag_diterima_pasien, b.nama, b.no_polisi, b.no_telp as no_telp_kurir, c.nama as nama_pasien, d.branch_name, e.nama_pelayanan, a.id_resep, a.lat, a.lon\n" +
                "FROM it_simrs_pengiriman_obat a\n" +
                "INNER JOIN im_simrs_kurir b ON a.id_kurir = b.id_kurir\n" +
                "INNER JOIN im_simrs_pasien c ON a.id_pasien = c.id_pasien\n" +
                "INNER JOIN im_branches d ON a.branch_id = d.branch_id\n" +
                "INNER JOIN im_simrs_pelayanan e ON a.id_pelayanan = e.id_pelayanan\n" +
                "WHERE a.flag = 'Y'\n" + searchIdKurir + searchIdPasien +
                "ORDER BY a.last_update ";

       results = this.sessionFactory.getCurrentSession()
               .createSQLQuery(query)
               .list();

       for (Object[] row : results) {
           PengirimanObat result = new PengirimanObat();
           result.setId((String) row[0]);
           result.setIdKurir((String) row[1]);
           result.setIdPasien((String) row[2]);
           result.setIdPelayanan((String) row[3]);
           result.setBranchId((String) row[4]);
           result.setAlamat((String) row[5]);
           result.setNoTelp((String) row[6]);
           result.setFlagPickup((String) row[7]);
           result.setFlagDiterimaPasien((String) row[8]);
           result.setKurirName((String) row[9]);
           result.setNoPolisi((String) row[10]);
           result.setNoTelpKurir((String) row[11]);
           result.setPasienName((String) row[12]);
           result.setBranchName((String) row[13]);
           result.setPelayananName((String) row[14]);
           result.setIdResep((String) row[15]);
           result.setLat((String) row[16]);
           result.setLon((String) row[17]);

           listOfResult.add(result);
       }

       return listOfResult;
    }
    public List<PermintaanResep> getListObatTelemedicApproved(String branchId){

        String SQL = "SELECT\n" +
                "    mspr.id_permintaan_resep,\n" +
                "    ishdc.id_detail_checkup,\n" +
                "    isp.nama,\n" +
                "    msato.id_approval_obat,\n" +
                "    ishdc.id_jenis_periksa_pasien,\n" +
                "    mspr.flag,\n" +
                "    mspr.id_transaksi_online,\n" +
                "    ishc.id_pasien,\n" +
                "    mspr.tujuan_pelayanan\n" +
                "FROM mt_simrs_approval_transaksi_obat msato\n" +
                "INNER JOIN (SELECT * FROM mt_simrs_permintaan_resep WHERE id_transaksi_online is NOT NULL ) mspr ON mspr.id_approval_obat = msato.id_approval_obat\n" +
                "INNER JOIN it_simrs_header_detail_checkup ishdc ON ishdc.id_detail_checkup = mspr.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup ishc ON ishc.no_checkup = ishdc.no_checkup\n" +
                "INNER JOIN im_simrs_pasien isp ON isp.id_pasien = ishc.id_pasien\n" +
                "LEFT JOIN it_simrs_pengiriman_obat ispo ON ispo.id_resep = mspr.id_permintaan_resep\n" +
                "WHERE msato.approval_flag = 'Y'\n" +
                "AND mspr.branch_id = :branchId \n" +
                "AND ispo.id_resep is NULL";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .list();

        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        if (results.size() > 0)
        {
            PermintaanResep permintaanResep;
            for (Object[] obj : results)
            {
                permintaanResep = new PermintaanResep();
                permintaanResep.setIdPermintaanResep(obj[0].toString());
                permintaanResep.setIdDetailCheckup(obj[1].toString());
                permintaanResep.setNamaPasien(obj[2].toString());
                permintaanResep.setIdApprovalObat(obj[3].toString());;
                permintaanResep.setIdJenisPeriksa(obj[4].toString());
                permintaanResep.setFlag(obj[5].toString());
                permintaanResep.setKetJenisAntrian(obj[6] == null ? "Resep RS" : "Telemedic");
                permintaanResep.setIdPasien(obj[7].toString());
                permintaanResep.setIdPelayanan(obj[8].toString());
                permintaanResepList.add(permintaanResep);
            }
        }
        return permintaanResepList;
    }
}
