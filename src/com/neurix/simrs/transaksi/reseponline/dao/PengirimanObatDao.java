package com.neurix.simrs.transaksi.reseponline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsPengirimanObatEntity;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        if (mapCriteria.get("alamat") != null)
            criteria.add(Restrictions.ilike("alamat", "%" + mapCriteria.get("alamat").toString() + "%"));
        if (mapCriteria.get("flag_pickup") != null){
            criteria.add(Restrictions.eq("flagPickup", mapCriteria.get("flag_pickup").toString()));
            criteria.add(Restrictions.isNull("flagDiterimaPasien"));
        }
        if (mapCriteria.get("flag_diterima") != null)
            criteria.add(Restrictions.eq("flagDiterimaPasien", mapCriteria.get("flag_diterima").toString()));
        if (mapCriteria.get("branch_id") != null)
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengiriman_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<PengirimanObat> getHistoryPengiriman(String idKurir) {
        String query = "";



        List<PengirimanObat> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        query = "SELECT a.id, a.id_kurir, a.id_pasien, a.id_pelayanan, a.branch_id, a.alamat, a.no_telp, a.flag_pickup, a.flag_diterima_pasien, b.nama, b.no_polisi, b.no_telp as no_telp_kurir, c.nama as nama_pasien, d.branch_name, e.nama_pelayanan, a.id_resep, a.lat, a.lon, a.foto_kirim, a.keterangan, a.created_date\n" +
                "FROM it_simrs_pengiriman_obat a\n" +
                "INNER JOIN im_simrs_kurir b ON a.id_kurir = b.id_kurir\n" +
                "INNER JOIN im_simrs_pasien c ON a.id_pasien = c.id_pasien\n" +
                "INNER JOIN im_branches d ON a.branch_id = d.branch_id\n" +
                "INNER JOIN im_simrs_pelayanan e ON a.id_pelayanan = e.id_pelayanan\n" +
                "WHERE a.id_kurir = :idKurir\n"+
                "ORDER BY a.last_update DESC";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("idKurir", idKurir)
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
            result.setFotoKirim((String) row[18]);
            result.setKeterangan((String) row[19]);
            try{
                result.setCreatedDate(row[20] != null ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(row[20].toString()).getTime()) : null);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            listOfResult.add(result);
        }

        return listOfResult;
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
        query = "SELECT a.id, a.id_kurir, a.id_pasien, a.id_pelayanan, a.branch_id, a.alamat, a.no_telp, a.flag_pickup, a.flag_diterima_pasien, b.nama, b.no_polisi, b.no_telp as no_telp_kurir, c.nama as nama_pasien, d.branch_name, e.nama_pelayanan, a.id_resep, a.lat, a.lon, a.foto_kirim, a.keterangan\n" +
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
           result.setFotoKirim((String) row[18]);
           result.setKeterangan((String) row[19]);

           listOfResult.add(result);
       }

       return listOfResult;
    }
    public List<PermintaanResep> getListObatTelemedicApproved(String branchId){

        String SQL = "SELECT\n" +
                "    mspr.id_permintaan_resep,\n" +
                "    mspr.id_detail_checkup,\n" +
                "    isp.nama,\n" +
                "    msato.id_approval_obat,\n" +
                "    isat.id_jenis_periksa_pasien,\n" +
                "    mspr.flag,\n" +
                "    mspr.id_transaksi_online,\n" +
                "    isp.id_pasien,\n" +
                "    mspr.tujuan_pelayanan,\n" +
                "    mspr.id_transaksi_online,\n" +
                "    isat.alamat,\n" +
                "   isat.no_telp,\n" +
                "   isat.lat,\n" +
                "   isat.lon,\n" +
                "   isat.jenis_pengambilan,\n" +
                "   isat.flag_bayar_konsultasi,\n" +
                "   isat.flag_bayar_resep\n" +
                "FROM mt_simrs_approval_transaksi_obat msato\n" +
                "INNER JOIN (SELECT * FROM mt_simrs_permintaan_resep WHERE id_transaksi_online is NOT NULL ) mspr ON mspr.id_approval_obat = msato.id_approval_obat\n" +
                "INNER JOIN it_simrs_pembayaran_online ispo2 ON ispo2.id = mspr.id_transaksi_online\n" +
                "INNER JOIN it_simrs_antrian_telemedic isat ON isat.id = ispo2.id_antrian_telemedic\n" +
                "INNER JOIN im_simrs_pasien isp ON isp.id_pasien = isat.id_pasien\n" +
                "LEFT JOIN it_simrs_pengiriman_obat ispo ON ispo.id_resep = mspr.id_permintaan_resep\n" +
                "WHERE msato.approval_flag = 'Y'\n" +
                "AND mspr.branch_id = :branchId \n" +
                "AND isat.jenis_pengambilan = 'kirim' \n" +
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
                permintaanResep.setIdDetailCheckup(obj[1] == null ? "" : obj[1].toString());
                permintaanResep.setNamaPasien(obj[2].toString());
                permintaanResep.setIdApprovalObat(obj[3].toString());;
                permintaanResep.setIdJenisPeriksa(obj[4].toString());
                permintaanResep.setFlag(obj[5].toString());
                permintaanResep.setKetJenisAntrian(obj[6] == null ? "Resep RS" : "Telemedic");
                permintaanResep.setIdPasien(obj[7].toString());
                permintaanResep.setIdPelayanan(obj[8].toString());
                permintaanResep.setIdTransaksiOnline(obj[9].toString());
                permintaanResep.setAlamat(obj[10] == null ? "" : obj[10].toString());
                permintaanResep.setNoTelp(obj[11] == null ? "" : obj[11].toString());
                permintaanResep.setLat(obj[12] == null ? "" : obj[12].toString());
                permintaanResep.setLon(obj[13] == null ? "" : obj[13].toString());
                permintaanResep.setJenisPengambilan(obj[14] == null ? "" : obj[14].toString());

                String flagBayarKonsul = obj[15] == null ? null : obj[15].toString();
                String flagBayarResep = obj[16] == null ? null : obj[16].toString();
                if (flagBayarKonsul == null && flagBayarResep == null){
                    permintaanResep.setFlagDelayAsuransi("Y");
                }
                permintaanResepList.add(permintaanResep);
            }
        }
        return permintaanResepList;
    }
}
