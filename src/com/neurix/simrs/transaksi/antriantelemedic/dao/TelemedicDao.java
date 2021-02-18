package com.neurix.simrs.transaksi.antriantelemedic.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.shift.model.Shift;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by reza on 08/06/20.
 */
public class TelemedicDao extends GenericDao<ItSimrsAntrianTelemedicEntity, String> {

    @Override
    protected Class<ItSimrsAntrianTelemedicEntity> getEntityClass() {
        return ItSimrsAntrianTelemedicEntity.class;
    }

    @Override
    public List<ItSimrsAntrianTelemedicEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAntrianTelemedicEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_pasien") != null)
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        if (mapCriteria.get("id_pelayanan") != null)
            criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
        if (mapCriteria.get("id_dokter") != null)
            criteria.add(Restrictions.eq("idDokter", mapCriteria.get("id_dokter").toString()));
        if (mapCriteria.get("status") != null)
            criteria.add(Restrictions.eq("status", mapCriteria.get("status").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        if (mapCriteria.get("jenis_pasien") != null)
            criteria.add(Restrictions.eq("idJenisPeriksaPasien", mapCriteria.get("jenis_pasien").toString()));
        if (mapCriteria.get("branch_id") != null)
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        if (mapCriteria.get("flag_resep") != null)
            criteria.add(Restrictions.eq("flagResep", mapCriteria.get("flag_resep").toString()));
        if (mapCriteria.get("flag_bayar_resep") != null)
            criteria.add(Restrictions.eq("flagBayarResep", mapCriteria.get("flag_bayar_resep").toString()));
        if (mapCriteria.get("created_date_to_date") != null)
            criteria.add(Restrictions.sqlRestriction("DATE(created_date) = '"+ mapCriteria.get("created_date_to_date")+"'"));
        if (mapCriteria.get("date_from") != null)
            criteria.add(Restrictions.sqlRestriction("DATE(created_date) >= '"+ mapCriteria.get("date_from")+"'"));
        if (mapCriteria.get("date_to") != null)
            criteria.add(Restrictions.sqlRestriction("DATE(created_date) <= '"+ mapCriteria.get("date_to")+"'"));
        if (mapCriteria.get("asc_limit_1") != null){
            criteria.addOrder(Order.asc("createdDate"));
            criteria.setMaxResults(1);
        }
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_antrian_telemedic')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }

    public List<AntrianTelemedic> getHistoryEobatByIdPasien(String idPasien) {
        String sql = "SELECT tele.id AS id_antrian_telemedic, tele.flag_resep, tele.flag_eresep, tele.id_pasien, pelayanan.nama_pelayanan, tele.id_pelayanan, tele.status, tele.flag_bayar_konsultasi, tele.flag_bayar_resep, pembayaran.id AS id_pembayaran, pembayaran.keterangan, pembayaran.nominal, tele.no_kartu, tele.id_jenis_periksa_pasien, tele.id_asuransi, tele.kode_bank, tele.jenis_pembayaran, tele.created_date, tele.flag, pembayaran.approved_flag\n" +
                "                FROM it_simrs_antrian_telemedic tele\n" +
                "                INNER JOIN im_simrs_pelayanan pelayanan ON pelayanan.id_pelayanan = tele.id_pelayanan\n" +
                "                INNER JOIN it_simrs_pembayaran_online pembayaran ON pembayaran.id_antrian_telemedic = tele.id\n" +
                "                WHERE tele.id_pasien = :idPasien \n" +
                "                AND tele.flag_eresep = 'Y'\n" +
                "                ORDER BY tele.created_date DESC";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setParameter("idPasien", idPasien)
                .list();

        List<AntrianTelemedic> antrianTelemedicList = new ArrayList<>();
        if (result.size() > 0){
            AntrianTelemedic antrianTelemedic;
            for (Object[] obj: result){
                antrianTelemedic = new AntrianTelemedic();
                antrianTelemedic.setId(obj[0] != null ? obj[0].toString() : null);
                antrianTelemedic.setFlagResep(obj[1] != null ? obj[1].toString() : null);
                antrianTelemedic.setFlagEresep( obj[2] != null ? obj[2].toString() : null);
                antrianTelemedic.setIdPasien(obj[3] != null ? obj[3].toString() : null);

                antrianTelemedic.setNamaPelayanan( obj[4] != null ? obj[4].toString() : null);
                antrianTelemedic.setIdPelayanan( obj[5] != null ? obj[5].toString() : null);
                antrianTelemedic.setStatus( obj[6] != null ? obj[6].toString() : null);
                antrianTelemedic.setFlagBayarKonsultasi( obj[7] != null ? obj[7].toString() : null);
                antrianTelemedic.setFlagBayarResep( obj[8] != null ? obj[8].toString() : null);
                antrianTelemedic.setIdPembayaran( obj[9] != null ? obj[9].toString() : null);
                antrianTelemedic.setKeterangan(obj[10] != null ? obj[10].toString() : null);
                antrianTelemedic.setNominal(obj[11] != null ? new BigDecimal(Float.valueOf(obj[11].toString())) : new BigDecimal(0));
                antrianTelemedic.setNoKartu( obj[12] != null ? obj[12].toString() : null);
                antrianTelemedic.setIdJenisPeriksaPasien( obj[13] != null ? obj[13].toString() : null);
                antrianTelemedic.setIdAsuransi( obj[14] != null ? obj[14].toString() : null);
                antrianTelemedic.setKodeBank( obj[15] != null ? obj[15].toString() : null);
                antrianTelemedic.setJenisPembayaran( obj[16] != null ? obj[16].toString() : null);
                try{
                    antrianTelemedic.setCreatedDate(obj[17] != null ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(obj[17].toString()).getTime()) : null);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                antrianTelemedic.setFlag(obj[18].toString());
                antrianTelemedic.setApprovedFlag(obj[19] != null ? obj[19].toString() : null);
                antrianTelemedicList.add(antrianTelemedic);
            }
        }
        return antrianTelemedicList;

    }

    public List<AntrianTelemedic> getHistoryByIdPasien(String idPasien){

        String sql = "SELECT tele.id AS id_antrian_telemedic, tele.flag_resep, tele.flag_eresep, tele.id_pasien, dokter.nama_dokter, tele.id_dokter, pelayanan.nama_pelayanan, tele.id_pelayanan, tele.status, tele.flag_bayar_konsultasi, tele.flag_bayar_resep, pembayaran.id AS id_pembayaran, pembayaran.keterangan, pembayaran.nominal, tele.no_kartu, tele.id_jenis_periksa_pasien, tele.id_asuransi, tele.kode_bank, tele.jenis_pembayaran, tele.created_date, tele.flag, pembayaran.approved_flag\n" +
                "FROM it_simrs_antrian_telemedic tele \n" +
                "INNER JOIN im_simrs_dokter dokter ON dokter.id_dokter = tele.id_dokter\n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "a.branch_id,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) pelayanan ON pelayanan.id_pelayanan = tele.id_pelayanan\n" +
                "INNER JOIN it_simrs_pembayaran_online pembayaran ON pembayaran.id_antrian_telemedic = tele.id\n" +
                "WHERE tele.id_pasien = :idPasien\n" +
                "ORDER BY tele.created_date DESC";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setParameter("idPasien", idPasien)
                .list();

        List<AntrianTelemedic> antrianTelemedicList = new ArrayList<>();
        if (result.size() > 0){
            AntrianTelemedic antrianTelemedic;
            for (Object[] obj: result){
                antrianTelemedic = new AntrianTelemedic();
                antrianTelemedic.setId(obj[0] != null ? obj[0].toString() : null);
                antrianTelemedic.setFlagResep(obj[1] != null ? obj[1].toString() : null);
                antrianTelemedic.setFlagEresep( obj[2] != null ? obj[2].toString() : null);
                antrianTelemedic.setIdPasien(obj[3] != null ? obj[3].toString() : null);
                antrianTelemedic.setNamaDokter(obj[4] != null ? obj[4].toString() : null);
                antrianTelemedic.setIdDokter( obj[5] != null ? obj[5].toString() : null);
                antrianTelemedic.setNamaPelayanan( obj[6] != null ? obj[6].toString() : null);
                antrianTelemedic.setIdPelayanan( obj[7] != null ? obj[7].toString() : null);
                antrianTelemedic.setStatus( obj[8] != null ? obj[8].toString() : null);
                antrianTelemedic.setFlagBayarKonsultasi( obj[9] != null ? obj[9].toString() : null);
                antrianTelemedic.setFlagBayarResep( obj[10] != null ? obj[10].toString() : null);
                antrianTelemedic.setIdPembayaran( obj[11] != null ? obj[11].toString() : null);
                antrianTelemedic.setKeterangan(obj[12] != null ? obj[12].toString() : null);
                antrianTelemedic.setNominal(obj[13] != null ? new BigDecimal(Float.valueOf(obj[13].toString())) : new BigDecimal(0));
                antrianTelemedic.setNoKartu( obj[14] != null ? obj[14].toString() : null);
                antrianTelemedic.setIdJenisPeriksaPasien( obj[15] != null ? obj[15].toString() : null);
                antrianTelemedic.setIdAsuransi( obj[16] != null ? obj[16].toString() : null);
                antrianTelemedic.setKodeBank( obj[17] != null ? obj[17].toString() : null);
                antrianTelemedic.setJenisPembayaran( obj[18] != null ? obj[18].toString() : null);
                try{
                    antrianTelemedic.setCreatedDate(obj[19] != null ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(obj[19].toString()).getTime()) : null);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                antrianTelemedic.setFlag(obj[20].toString());
                antrianTelemedic.setApprovedFlag(obj[21] != null ? obj[21].toString() : null);
                antrianTelemedicList.add(antrianTelemedic);
            }
        }
        return antrianTelemedicList;
    }

    public List<AntrianTelemedic> getHistoryByIdDokter(String idDokter) {

        String sql = "SELECT tele.id AS id_antrian_telemedic, tele.flag_resep, tele.flag_eresep, tele.id_pasien, dokter.nama_dokter, tele.id_dokter, pelayanan.nama_pelayanan, tele.id_pelayanan, tele.status, tele.flag_bayar_konsultasi, tele.flag_bayar_resep, pembayaran.id AS id_pembayaran, pembayaran.keterangan, pembayaran.nominal, tele.no_kartu, tele.id_jenis_periksa_pasien, tele.id_asuransi, tele.kode_bank, tele.jenis_pembayaran, tele.created_date, tele.flag, pembayaran.approved_flag\n" +
                "FROM it_simrs_antrian_telemedic tele \n" +
                "INNER JOIN im_simrs_dokter dokter ON dokter.id_dokter = tele.id_dokter\n" +
                "INNER JOIN im_simrs_pelayanan pelayanan ON pelayanan.id_pelayanan = tele.id_pelayanan\n" +
                "INNER JOIN it_simrs_pembayaran_online pembayaran ON pembayaran.id_antrian_telemedic = tele.id\n" +
                "WHERE tele.id_dokter = :idDokter\n" +
                "ORDER BY tele.created_date DESC";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setParameter("idDokter", idDokter)
                .list();

        List<AntrianTelemedic> antrianTelemedicList = new ArrayList<>();
        if (result.size() > 0){
            AntrianTelemedic antrianTelemedic;
            for (Object[] obj: result){
                antrianTelemedic = new AntrianTelemedic();
                antrianTelemedic.setId(obj[0] != null ? obj[0].toString() : null);
                antrianTelemedic.setFlagResep(obj[1] != null ? obj[1].toString() : null);
                antrianTelemedic.setFlagEresep( obj[2] != null ? obj[2].toString() : null);
                antrianTelemedic.setIdPasien(obj[3] != null ? obj[3].toString() : null);
                antrianTelemedic.setNamaDokter(obj[4] != null ? obj[4].toString() : null);
                antrianTelemedic.setIdDokter( obj[5] != null ? obj[5].toString() : null);
                antrianTelemedic.setNamaPelayanan( obj[6] != null ? obj[6].toString() : null);
                antrianTelemedic.setIdPelayanan( obj[7] != null ? obj[7].toString() : null);
                antrianTelemedic.setStatus( obj[8] != null ? obj[8].toString() : null);
                antrianTelemedic.setFlagBayarKonsultasi( obj[9] != null ? obj[9].toString() : null);
                antrianTelemedic.setFlagBayarResep( obj[10] != null ? obj[10].toString() : null);
                antrianTelemedic.setIdPembayaran( obj[11] != null ? obj[11].toString() : null);
                antrianTelemedic.setKeterangan(obj[12] != null ? obj[12].toString() : null);
                antrianTelemedic.setNominal(obj[13] != null ? new BigDecimal(Float.valueOf(obj[13].toString())) : new BigDecimal(0));
                antrianTelemedic.setNoKartu( obj[14] != null ? obj[14].toString() : null);
                antrianTelemedic.setIdJenisPeriksaPasien( obj[15] != null ? obj[15].toString() : null);
                antrianTelemedic.setIdAsuransi( obj[16] != null ? obj[16].toString() : null);
                antrianTelemedic.setKodeBank( obj[17] != null ? obj[17].toString() : null);
                antrianTelemedic.setJenisPembayaran( obj[18] != null ? obj[18].toString() : null);
                try{
                    antrianTelemedic.setCreatedDate(obj[19] != null ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(obj[19].toString()).getTime()) : null);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                antrianTelemedic.setFlag(obj[20].toString());
                antrianTelemedic.setApprovedFlag(obj[21] != null ? obj[21].toString() : null);
                antrianTelemedicList.add(antrianTelemedic);
            }
        }
        return antrianTelemedicList;
    }

    public boolean foundIfAllFlagNotActive(String id){

        String SQL = "SELECT a.*\n" +
                "FROM (\n" +
                "\tSELECT a.id, \n" +
                "\ta.flag, \n" +
                "\tb.flag, \n" +
                "\tCASE WHEN b.url_foto_bukti = '' THEN null ELSE b.url_foto_bukti END as url_foto_bukti\n" +
                "\tFROM it_simrs_antrian_telemedic a\n" +
                "\tINNER JOIN it_simrs_pembayaran_online b On b.id_antrian_telemedic = a.id\n" +
                "\tAND a.flag = 'N'\n" +
                "\tAND b.flag = 'N'\n" +
                ") a\n" +
                "WHERE a.id = :id\n" +
                "AND a.url_foto_bukti is null";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();

        if (results.size() > 0){
            return true;
        }
        return false;
    }

    public List<AntrianTelemedic> getListAntrianTelemedicSearch(AntrianTelemedic bean){

        String where = "";
        String idPasien = bean.getIdPasien();
        String id = bean.getId();

        if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter())){
            where += "AND at.id_dokter = '"+bean.getIdDokter()+"' \n";
        }
        if (bean.getIdPelayanan() != null  && !"".equalsIgnoreCase(bean.getIdPelayanan()) ){
            where += "AND at.id_pelayanan = '"+bean.getIdPelayanan()+"' \n";
        }
        if (bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())){
            where += "AND at.status = '"+bean.getStatus()+"' \n";
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            where += "AND at.flag = '"+bean.getFlag()+"' \n";
        }
        if (bean.getIdJenisPeriksaPasien() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
            where += "AND at.id_jenis_periksa_pasien = '"+bean.getIdJenisPeriksaPasien()+"' \n";
        }
        if (bean.getFlagResep() != null && !"".equalsIgnoreCase(bean.getFlagResep())){
            where += "AND at.flag_resep = '"+bean.getFlagResep()+"' \n";
        }
        if (bean.getFlagBayarResep() != null && !"".equalsIgnoreCase(bean.getFlagBayarResep())) {
            where += "AND at.flag_bayar_resep = '"+bean.getFlagBayarResep()+"' \n";
        }
        if (bean.getFlagEresep() != null && !"".equalsIgnoreCase(bean.getFlagEresep())) {
            where += "AND at.flag_eresep = '"+bean.getFlagEresep()+"' \n";
        }
        if (bean.getIdTransaksi() != null && !"".equalsIgnoreCase(bean.getIdTransaksi())) {
            where += "AND po.id = '"+bean.getIdTransaksi()+"' \n";
        }
        if (idPasien == null || "".equalsIgnoreCase(idPasien))
            idPasien = "%";
        if (id == null || "".equalsIgnoreCase(id))
            id = "%";

        String SQL = "SELECT \n" +
                "a.id,\n" +
                "a.last_update,\n" +
                "b.tanggal_upload\n" +
                "FROM\n" +
                "(\n" +
                "\tSELECT \n" +
                "\ta.*\n" +
                "\tFROM (\n" +
                "\t\tSELECT \n" +
                "\t\tat.id, \n" +
                "\t\tMAX(po.last_update) as last_update\n" +
                "\t\tFROM it_simrs_antrian_telemedic at\n" +
                "\t\tLEFT JOIN (\n" +
                "\t\t\tSELECT \n" +
                "\t\t\t* \n" +
                "\t\t\tFROM it_simrs_pembayaran_online \n" +
                "\t\t\tWHERE last_update IS NOT NULL\n" +
                "\t\t) po ON po.id_antrian_telemedic = at.id\n" +
                "\t\tWHERE DATE(at.created_date) = '"+bean.getFlagDateNow()+"' \n" +
                "\t\tAND at.id_pasien ILIKE '"+ idPasien +"' \n" +
                "\t\tAND at.id ILIKE '"+ id +"' \n" + where +
                "\t\tGROUP BY at.id\n" +
                "\t) a \n" +
                "\tWHERE a.last_update IS NOT NULL\n" +
                ") a \n" +
                "LEFT JOIN (SELECT * FROM it_simrs_pembayaran_online WHERE tanggal_upload IS NOT NULL) b ON b.id_antrian_telemedic = a.id AND b.last_update = a.last_update\n" +
                "ORDER BY tanggal_upload";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<AntrianTelemedic> antrianTelemedicList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                AntrianTelemedic telemedic = new AntrianTelemedic();
                telemedic.setId(obj[0].toString());
                telemedic.setLastUpdate((Timestamp) obj[1]);
                telemedic.setTanggalUpload(obj[2] == null ? null : (Timestamp) obj[2]);
                telemedic.setStTangalUpload(telemedic.getTanggalUpload() == null ? "" : telemedic.getTanggalUpload().toString());

                Map hsCriteria = new HashMap();
                hsCriteria.put("id", telemedic.getId());

                List<ItSimrsAntrianTelemedicEntity> antrianTelemedicEntities = getByCriteria(hsCriteria);
                if (antrianTelemedicEntities != null && antrianTelemedicEntities.size() > 0){
                    ItSimrsAntrianTelemedicEntity entity = antrianTelemedicEntities.get(0);

                    telemedic.setIdPasien(entity.getIdPasien());
                    telemedic.setIdDokter(entity.getIdDokter());
                    telemedic.setIdPelayanan(entity.getIdPelayanan());
                    telemedic.setFlagResep(entity.getFlagResep());
                    telemedic.setStatus(entity.getStatus());
                    telemedic.setFlagBayarKonsultasi(entity.getFlagBayarKonsultasi());
                    telemedic.setFlagBayarResep(entity.getFlagBayarResep());
                    telemedic.setCreatedDate(entity.getCreatedDate());
                    telemedic.setCreatedWho(entity.getCreatedWho());
                    telemedic.setLastUpdateWho(entity.getLastUpdateWho());
                    telemedic.setFlag(entity.getFlag());
                    telemedic.setAction(entity.getAction());
                    telemedic.setNoKartu(entity.getNoKartu());
                    telemedic.setIdJenisPeriksaPasien(entity.getIdJenisPeriksaPasien());
                    telemedic.setIdAsuransi(entity.getIdAsuransi());
                    telemedic.setAsuransi(entity.getIdAsuransi());
                    telemedic.setKodeBank(entity.getKodeBank());
                    telemedic.setBranchId(entity.getBranchId());
                    telemedic.setKeluhan(entity.getKeluhan());
                    telemedic.setFlagEresep(entity.getFlagEresep());
                    telemedic.setUrlResep(entity.getUrlFotoResep());
                    telemedic.setJenisPengambilan(entity.getJenisPengambilan());
                    telemedic.setNoJurnal(entity.getNoJurnal());
                    telemedic.setNoRujukan(entity.getNoRujukan());
                    telemedic.setJenisRujukan(entity.getJenisRujukan());
                    telemedic.setNoSep(entity.getNoSep());
                    telemedic.setIdDiagnosa(entity.getIdDiagnosa());
                    telemedic.setKetDiagnosa(entity.getKetDiagnosa());
                    telemedic.setIdRekening(entity.getIdRekening());
                    telemedic.setJumlahCover(entity.getJumlahCover());
                    telemedic.setStCreatedDate(entity.getCreatedDate() == null ? "" : entity.getCreatedDate().toString());
                    antrianTelemedicList.add(telemedic);
                }

            }
        }
        return antrianTelemedicList;
    }

    public List<Shift> getJadwalShiftKasirTelemedicineByDate(String branchId, String stDate, String shiftId){

        String whereShiftId = "";
        if (shiftId != null && !"".equalsIgnoreCase(shiftId)){
            whereShiftId = "AND po.shift_id = '"+shiftId+"' \n";
        }

        String SQL = "SELECT po.shift_id, sft.shift_name, at.branch_id\n" +
                "FROM \n" +
                "it_simrs_pembayaran_online po\n" +
                "INNER JOIN it_simrs_antrian_telemedic at ON at.id = po.id_antrian_telemedic\n" +
                "INNER JOIN im_hris_shift sft ON sft.shift_id = po.shift_id\n" +
                "WHERE DATE(po.last_update) = '"+stDate+"'\n" +
                "AND at.branch_id = '"+branchId+"'\n" + whereShiftId +
                "GROUP BY po.shift_id, sft.shift_name, at.branch_id";

        List<Object[]> objects = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<Shift> shifts = new ArrayList<>();
        if (objects != null && objects.size() > 0){
            for (Object[] obj : objects){
                Shift shift = new Shift();
                shift.setShiftId(obj[0].toString());
                shift.setShiftName(obj[1].toString());
                shifts.add(shift);
            }
        }

        return shifts;
    }
}
