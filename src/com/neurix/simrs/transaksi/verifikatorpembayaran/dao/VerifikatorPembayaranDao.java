package com.neurix.simrs.transaksi.verifikatorpembayaran.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by reza on 10/06/20.
 */
public class VerifikatorPembayaranDao extends GenericDao<ItSimrsPembayaranOnlineEntity, String> {

    @Override
    protected Class<ItSimrsPembayaranOnlineEntity> getEntityClass() {
        return ItSimrsPembayaranOnlineEntity.class;
    }

    @Override
    public List<ItSimrsPembayaranOnlineEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPembayaranOnlineEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("id_antrian_telemedic") != null){
            criteria.add(Restrictions.eq("idAntrianTelemedic", mapCriteria.get("id_antrian_telemedic").toString()));
        }
        if (mapCriteria.get("keterangan") != null){
            criteria.add(Restrictions.eq("keterangan", mapCriteria.get("keterangan").toString()));
        }
        if (mapCriteria.get("approve_flag") != null){
            criteria.add(Restrictions.eq("approveFlag", mapCriteria.get("approve_flag").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_header_checkup')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }

    public List<AntrianTelemedic> getListLaporanKasMasukTelemedic(String shiftId, String stDate, String branchId, String status, String jenisPasien){

        String SQL = "SELECT\n" +
                "po.id,\n" +
                "po.keterangan,\n" +
                "at.id_jenis_periksa_pasien,\n" +
                "kr.nama_kode_rekening,\n" +
                "ps.id_pasien,\n" +
                "ps.nama,\n" +
                "po.nominal,\n" +
                "po.last_update,\n" +
                "po.last_update_who,\n" +
                "at.flag_resep,\n" +
                "at.flag_bayar_konsultasi,\n" +
                "at.flag_bayar_resep,\n" +
                "po.approved_flag,\n" +
                "hpl.nama_pelayanan,\n" +
                "dk.nama_dokter,\n" +
                "po.url_foto_bukti \n" +
                "FROM (SELECT * FROM it_simrs_pembayaran_online WHERE approved_flag = 'Y') po\n" +
                "INNER JOIN (SELECT id, branch_id, id_pasien , id_jenis_periksa_pasien, flag_bayar_konsultasi, flag_bayar_resep, flag_resep, id_pelayanan, id_dokter FROM it_simrs_antrian_telemedic WHERE status = 'SK')at ON at.id = po.id_antrian_telemedic\n" +
                "INNER JOIN (SELECT kode_rekening, nama_kode_rekening FROM im_akun_kode_rekening) kr ON kr.kode_rekening = po.kode_bank\n" +
                "INNER JOIN (SELECT id_pasien, nama FROM im_simrs_pasien) ps ON ps.id_pasien = at.id_pasien\n" +
                "INNER JOIN (SELECT id_pelayanan, id_header_pelayanan FROM im_simrs_pelayanan) pl ON pl.id_pelayanan = at.id_pelayanan\n" +
                "INNER JOIN (SELECT id_header_pelayanan, nama_pelayanan FROM im_simrs_header_pelayanan) hpl ON hpl.id_header_pelayanan = pl.id_header_pelayanan\n" +
                "INNER JOIN (SELECT id_dokter, nama_dokter FROM im_simrs_dokter) dk ON dk.id_dokter = at.id_dokter\n" +
                "WHERE DATE(po.last_update) = '"+stDate+"'\n" +
                "AND at.branch_id = '"+branchId+"' \n" +
                "AND po.shift_id = '"+shiftId+"' \n" +
                "AND at.id_jenis_periksa_pasien = '"+jenisPasien+"' \n" +
                "ORDER BY po.last_update ASC";

        List<Object[]> objects = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<AntrianTelemedic> antrianTelemedics = new ArrayList<>();
        if (objects != null && objects.size() > 0){
            for (Object[] obj : objects){
                AntrianTelemedic antritanTelemedicData = new AntrianTelemedic();
                antritanTelemedicData.setId(obj[0].toString());
                antritanTelemedicData.setKeterangan(obj[1].toString().toUpperCase());
                antritanTelemedicData.setIdJenisPeriksaPasien(obj[2].toString());
                antritanTelemedicData.setNamaBank(obj[3].toString());
                antritanTelemedicData.setIdPasien(obj[4].toString());
                antritanTelemedicData.setNamaPasien(obj[5].toString());
                antritanTelemedicData.setNominal(obj[6] == null ? new BigDecimal("0") : (BigDecimal) obj[6]);
                antritanTelemedicData.setLastUpdate(obj[7] == null ? null : (Timestamp) obj[7]);
                antritanTelemedicData.setLastUpdateWho(obj[8] == null ? null : obj[8].toString());
                antritanTelemedicData.setFlagResep(obj[9] == null ? null : obj[9].toString());
                antritanTelemedicData.setFlagBayarKonsultasi(obj[10] == null ? null : obj[10].toString());
                antritanTelemedicData.setFlagBayarResep(obj[11] == null ? null : obj[11].toString());
                antritanTelemedicData.setApprovedFlag(obj[12] == null ? null : obj[12].toString());
                antritanTelemedicData.setNamaPelayanan(obj[13] == null ? null : obj[13].toString());
                antritanTelemedicData.setNamaDokter(obj[14] == null ? null : obj[14].toString());
                antritanTelemedicData.setStLastUpdate(antritanTelemedicData.getLastUpdate().toString());
                antritanTelemedicData.setUrlFotoStruk(obj[15] == null ? "" : obj[15].toString());

                // filter status transaksi
                if ("asuransi".equalsIgnoreCase(antritanTelemedicData.getIdJenisPeriksaPasien())){
                    if ("Y".equalsIgnoreCase(antritanTelemedicData.getFlagBayarKonsultasi())){
                        antritanTelemedicData.setStatusTransaksi("finish");
                        antritanTelemedicData.setLabelStatusAsuransi("Terverifikasi");
                    }
                    if (antritanTelemedicData.getFlagBayarKonsultasi() == null) {
                        if ("Y".equalsIgnoreCase(antritanTelemedicData.getApprovedFlag())){
                            antritanTelemedicData.setStatusTransaksi("finish");
                        } else {
                            antritanTelemedicData.setStatusTransaksi("exist");
                        }
                    }
                } else {
                    if (!"Y".equalsIgnoreCase(antritanTelemedicData.getFlagResep()) && antritanTelemedicData.getApprovedFlag() != null) {
                        antritanTelemedicData.setStatusTransaksi("finish");
                    } else if ("Y".equalsIgnoreCase(antritanTelemedicData.getFlagResep()) && antritanTelemedicData.getApprovedFlag() != null) {
                        antritanTelemedicData.setStatusTransaksi("finish");
                    } else if ("Y".equalsIgnoreCase(antritanTelemedicData.getApprovedFlag()) && "Y".equalsIgnoreCase(antritanTelemedicData.getFlagBayarResep())){
                        antritanTelemedicData.setStatusTransaksi("finish");
                    } else {
                        antritanTelemedicData.setStatusTransaksi("exist");
                    }
                }

                antrianTelemedics.add(antritanTelemedicData);
            }
        }

        // kembalikan berdasarkan status transaksi
        if (status == null && !"".equalsIgnoreCase(status)) {
            return antrianTelemedics;
        } else {
            final String statusTransaksi = status;
            return antrianTelemedics.stream().filter(p->p.getStatusTransaksi().equalsIgnoreCase(statusTransaksi)).collect(Collectors.toList());
        }
    }

    public String getIdDetailCheckupByIdTransaksi(String idTransaksi){

        String SQL = "SELECT\n" +
                "dc.id_detail_checkup\n" +
                "FROM \n" +
                "it_simrs_header_detail_checkup dc\n" +
                "WHERE\n" +
                "dc.id_transaksi_online = '"+idTransaksi+"'";

        List<Object> obj = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (obj.size() > 0){
            return obj.toString();
        }

        return null;
    }

    public String getJenisPasien(String idTransaksi){

        String SQL = "  SELECT\n" +
                "    dc.id_jenis_periksa_pasien\n" +
                "            FROM\n" +
                "    it_simrs_header_detail_checkup dc\n" +
                "    WHERE\n" +
                "    dc.id_transaksi_online = '"+idTransaksi+"'";

        List<Object> obj = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (obj.size() > 0){
            return obj.toString();
        }

        return null;
    }

    public List<AntrianTelemedic> reportVa (String dateFrom, String dateTo, String branchId){
        String rangeFrom = "";
        String rangeTo = "";

        if(dateFrom != null){
            rangeFrom = "AND DATE(po.last_update) >= '"+dateFrom+"'\n";
        }
        if(dateTo != null){
            rangeTo = "AND DATE(po.last_update) <= '"+dateTo+"'\n";
        }

        String SQL = "SELECT\n" +
                "po.id,\n" +
                "po.keterangan,\n" +
                "at.id_jenis_periksa_pasien,\n" +
                "kr.nama_kode_rekening,\n" +
                "ps.id_pasien,\n" +
                "ps.nama,\n" +
                "po.nominal,\n" +
                "po.last_update,\n" +
                "po.last_update_who,\n" +
                "at.flag_resep,\n" +
                "at.flag_bayar_konsultasi,\n" +
                "at.flag_bayar_resep,\n" +
                "po.approved_flag,\n" +
                "hpl.nama_pelayanan,\n" +
                "dk.nama_dokter,\n" +
                "po.url_foto_bukti \n" +
                "FROM (SELECT * FROM it_simrs_pembayaran_online WHERE approved_flag = 'Y' AND tipe_pembayaran = 'virtual_account') po\n" +
                "INNER JOIN (SELECT id, branch_id, id_pasien , id_jenis_periksa_pasien, flag_bayar_konsultasi, flag_bayar_resep, flag_resep, id_pelayanan, id_dokter FROM it_simrs_antrian_telemedic)at ON at.id = po.id_antrian_telemedic\n" +
                "INNER JOIN (SELECT kode_rekening, nama_kode_rekening FROM im_akun_kode_rekening) kr ON kr.kode_rekening = po.kode_bank\n" +
                "INNER JOIN (SELECT id_pasien, nama FROM im_simrs_pasien) ps ON ps.id_pasien = at.id_pasien\n" +
                "INNER JOIN (SELECT id_pelayanan, id_header_pelayanan FROM im_simrs_pelayanan) pl ON pl.id_pelayanan = at.id_pelayanan\n" +
                "INNER JOIN (SELECT id_header_pelayanan, nama_pelayanan FROM im_simrs_header_pelayanan) hpl ON hpl.id_header_pelayanan = pl.id_header_pelayanan\n" +
                "INNER JOIN (SELECT id_dokter, nama_dokter FROM im_simrs_dokter) dk ON dk.id_dokter = at.id_dokter\n" +
                "WHERE at.branch_id = '"+branchId+"' \n" +
                rangeFrom + rangeTo +
                "ORDER BY po.last_update ASC";

        List<Object[]> objects = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<AntrianTelemedic> antrianTelemedics = new ArrayList<>();
        if (objects != null && objects.size() > 0){
            for (Object[] obj : objects){
                AntrianTelemedic antritanTelemedicData = new AntrianTelemedic();
                antritanTelemedicData.setId(obj[0].toString());
                antritanTelemedicData.setKeterangan(obj[1].toString().toUpperCase());
                antritanTelemedicData.setIdJenisPeriksaPasien(obj[2].toString());
                antritanTelemedicData.setNamaBank(obj[3].toString());
                antritanTelemedicData.setIdPasien(obj[4].toString());
                antritanTelemedicData.setNamaPasien(obj[5].toString());
                antritanTelemedicData.setNominal(obj[6] == null ? new BigDecimal("0") : (BigDecimal) obj[6]);
                antritanTelemedicData.setLastUpdate(obj[7] == null ? null : (Timestamp) obj[7]);
                antritanTelemedicData.setLastUpdateWho(obj[8] == null ? null : obj[8].toString());
                antritanTelemedicData.setFlagResep(obj[9] == null ? null : obj[9].toString());
                antritanTelemedicData.setFlagBayarKonsultasi(obj[10] == null ? null : obj[10].toString());
                antritanTelemedicData.setFlagBayarResep(obj[11] == null ? null : obj[11].toString());
                antritanTelemedicData.setApprovedFlag(obj[12] == null ? null : obj[12].toString());
                antritanTelemedicData.setNamaPelayanan(obj[13] == null ? null : obj[13].toString());
                antritanTelemedicData.setNamaDokter(obj[14] == null ? null : obj[14].toString());
                antritanTelemedicData.setStLastUpdate(antritanTelemedicData.getLastUpdate().toString());
                antritanTelemedicData.setUrlFotoStruk(obj[15] == null ? "" : obj[15].toString());

                antrianTelemedics.add(antritanTelemedicData);
            }
        }

        return antrianTelemedics;
    }
}
