package com.neurix.hris.transaksi.jadwalShiftKerja.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class JadwalShiftKerjaDao extends GenericDao<ItJadwalShiftKerjaEntity, String> {

    @Override
    protected Class<ItJadwalShiftKerjaEntity> getEntityClass() {
        return ItJadwalShiftKerjaEntity.class;
    }

    @Override
    public List<ItJadwalShiftKerjaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItJadwalShiftKerjaEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("jadwal_shift_kerja_name") != null) {
                criteria.add(Restrictions.ilike("jadwalShiftKerjaName", "%" + (String) mapCriteria.get("jadwal_shift_kerja_name") + "%"));
            }
            if (mapCriteria.get("jadwal_shift_kerja_id") != null) {
                criteria.add(Restrictions.eq("jadwalShiftKerjaId", (String) mapCriteria.get("jadwal_shift_kerja_id")));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("tanggal_dari") != null && mapCriteria.get("tanggal_selesai") != null) {
                criteria.add(Restrictions.between("tanggal", mapCriteria.get("tanggal_dari"), mapCriteria.get("tanggal_selesai")));
            } else {
                if (mapCriteria.get("tanggal_dari") != null) {
                    criteria.add(Restrictions.ge("tanggal", mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai") != null) {
                    criteria.add(Restrictions.le("tanggal", mapCriteria.get("tanggal_selesai")));
                }
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("jadwalShiftKerjaId"));

        List<ItJadwalShiftKerjaEntity> results = criteria.list();

        return results;
    }

    public List<JadwalShiftKerja> getByCriteriaJadwalShift(JadwalShiftKerja search) {
        List<JadwalShiftKerja> jadwalShiftKerjaList = new ArrayList<>();
        String query = "";
        String searchUnit = "" ;
        String searchGrup = "" ;
        String searchNip = "" ;
        String searchTanggalDari = "" ;
        String searchTanggalSelesai = "" ;

        if (search.getBranchId()!=null){
            if(!search.getBranchId().equalsIgnoreCase("")){
                searchUnit = " and j.branch_id = '" + search.getBranchId() + "' " ;
            }
        }
        if (search.getGroupId()!=null){
            if(!search.getGroupId().equalsIgnoreCase("")){
                searchGrup = " and jd.profesi_id = '" + search.getGroupId() + "' " ;
            }
        }
        if (search.getNip()!=null){
            if(!search.getNip().equalsIgnoreCase("")){
                searchNip = " and jd.nip = '" + search.getNip() + "' " ;
            }
        }
        if (search.getStTanggalAwal()!=null){
            if(!search.getStTanggalAwal().equalsIgnoreCase("")){
                searchTanggalDari = " and j.tanggal >= '" + search.getStTanggalAwal() + "' " ;
            }
        }
        if (search.getStTanggalAkhir()!=null){
            if(!search.getStTanggalAkhir().equalsIgnoreCase("")){
                searchTanggalSelesai = " and j.tanggal <= '" + search.getStTanggalAkhir() + "' " ;
            }
        }
        query = "select\n" +
                "\tj.jadwal_shift_kerja_id,\n" +
                "\tb.branch_name,\n" +
                "\tj.tanggal,\n" +
                "\ts.jam_awal,\n" +
                "\ts.jam_akhir,\n" +
                "\tjd.profesi_name,\n" +
                "\tjd.nama_pegawai,\n" +
                "\tjd.position_name,\n" +
                "\tjd.on_call,\n" +
                "\tjd.flag_panggil,\n" +
                "\tj.branch_id,\n" +
                "\ts.shift_name,\n" +
                "\tjd.flag_libur,\n" +
                "\tjd.jadwal_shift_kerja_detail_id,\n" +
                "\tbio.flag_dokter_kso,\n" +
                "\tjd.nip\n" +
                "from\n" +
                "\t( select * from it_hris_jadwal_shift_kerja) j\n" +
                "\tleft join (select * from it_hris_jadwal_shift_kerja_detail where flag='Y') jd ON j.jadwal_shift_kerja_id = jd.jadwal_shift_kerja_id\n" +
                "\tleft join ( select * from im_hris_shift ) s ON jd.shift_id = s.shift_id\n" +
                "\tleft join ( select * from im_branches ) b ON b.branch_id = j.branch_id\n" +
                "\tleft join ( select * from im_hris_pegawai ) bio ON bio.nip = jd.nip\n" +
                "where\n" +
                "\tj.flag='Y'\n" +
                searchUnit + searchGrup + searchNip + searchTanggalDari + searchTanggalSelesai +
                "\t order by j.tanggal asc,jam_awal asc,jd.profesi_name asc,position_name asc";

        List<Object[]> results = new ArrayList<Object[]>();

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            JadwalShiftKerja jadwalShiftKerja = new JadwalShiftKerja();
            jadwalShiftKerja.setJadwalShiftKerjaId((String) row[0]);
            jadwalShiftKerja.setBranchName(row[1].toString());
            jadwalShiftKerja.setTanggal((Date) row[2]);
            jadwalShiftKerja.setShiftName(row[3].toString()+ " s/d "+row[4].toString());
            jadwalShiftKerja.setProfesiName(row[5].toString());
            jadwalShiftKerja.setNamaPegawai(row[6].toString());
            jadwalShiftKerja.setPositionName(row[7].toString());
            jadwalShiftKerja.setOnCall(row[8].toString());
            jadwalShiftKerja.setFlagPanggil(row[9].toString());
            jadwalShiftKerja.setBranchId(row[10].toString());
            jadwalShiftKerja.setShiftName2(row[11].toString());
            jadwalShiftKerja.setFlagLibur(row[12].toString());
            jadwalShiftKerja.setJadwalShiftKerjaDetailId(row[13].toString());
            if (row[14]!=null){
                jadwalShiftKerja.setFlagDokterKso(row[14].toString());
            }else{
                jadwalShiftKerja.setFlagDokterKso("N");
            }
            jadwalShiftKerja.setNip(row[15].toString());

            jadwalShiftKerjaList.add(jadwalShiftKerja);
        }
        return jadwalShiftKerjaList;
    }

    public String getNextJadwalShiftKerjaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jadwal_shift_kerja')");
        Iterator<BigInteger> iter = query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String sId = String.format("%05d", iter.next());

        return "JSK" + formattedDate + sId;
    }

    public List<JadwalShiftKerja> getJadwalForReport(Date tanggalDari, Date tanggalSampai) {
        List<JadwalShiftKerja> jadwalShiftKerjaList = new ArrayList<>();
        String query = "";

        query = "SELECT \n" +
                "\tkerja.tanggal,\n" +
                "\tpegawai.nip,\n" +
                "\tshift.shift_name\n" +
                "FROM \n" +
                "\t(SELECT * FROM it_hris_jadwal_shift_kerja) kerja LEFT JOIN \n" +
                "\t(SELECT * FROM it_hris_jadwal_shift_kerja_detail) kerjadetail ON kerja.jadwal_shift_kerja_id = kerjadetail.jadwal_shift_kerja_id LEFT JOIN \n" +
                "\t(SELECT * FROM im_hris_group_shift) groupshift ON kerjadetail.group_shift_id = groupshift.group_shift_id LEFT JOIN \n" +
                "\t(SELECT * FROM imt_hris_group_member) groupmember ON groupshift.group_id = groupmember.group_id LEFT JOIN \n" +
                "\t(SELECT * FROM im_hris_shift) shift ON groupshift.shift_id = shift.shift_id LEFT JOIN\n" +
                "\t(SELECT * FROM im_hris_pegawai) pegawai ON groupmember.nip = pegawai.nip\n" +
                "WHERE\n" +
                "\tkerja.tanggal >= '" + tanggalDari + "' AND\n" +
                "\tkerja.tanggal <= '" + tanggalSampai + "' AND\n" +
                "\tkerja.flag ='Y'\n" +
                "ORDER BY\n" +
                "\tpegawai.nip,\n" +
                "\tkerja.tanggal";

        List<Object[]> results = new ArrayList<Object[]>();

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("tanggalDari", tanggalDari)
                .setParameter("tanggalSampai", tanggalSampai)
                .list();

        for (Object[] row : results) {
            JadwalShiftKerja jadwalShiftKerja = new JadwalShiftKerja();
            jadwalShiftKerja.setTanggal((Date) row[0]);
            jadwalShiftKerja.setNip(row[1].toString());
            jadwalShiftKerja.setShiftName(row[2].toString());

            jadwalShiftKerjaList.add(jadwalShiftKerja);
        }
        return jadwalShiftKerjaList;
    }

    public List<JadwalPelayananDTO> getJadwalPelayanan(String idPelayanan, String kelompokId, String branchId, String nip, Date tanggal) {
        String searchPelayanan = "";
        String searchKelompok = "";
        String searchBranch = "";
        String searchNip = "";
        String searchTanggal = "";

        if (idPelayanan != null) {
            if (!idPelayanan.equalsIgnoreCase("")) {
                searchPelayanan = " and pl.id_pelayanan = '" + idPelayanan + "' ";
            }
        }
        if (kelompokId != null) {
            if (!kelompokId.equalsIgnoreCase("")) {
                searchKelompok = " and kp.kelompok_id = '" + kelompokId + "' ";
            }
        }
        if (branchId != null) {
            if (!branchId.equalsIgnoreCase("")) {
                searchBranch = " and pp.branch_id= '" + branchId + "' ";
            }
        }
        if (nip != null) {
            if (!nip.equalsIgnoreCase("")) {
                searchNip = " and pg.nip = '" + nip + "' ";
            }
        }
        if (tanggal != null) {
            searchTanggal = " and jd.tanggal= '" + tanggal + "' ";
        }

        List<JadwalPelayananDTO> listOfResult = new ArrayList<JadwalPelayananDTO>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\tdk.id_dokter,\n" +
                "\tdk.nama_dokter,\n" +
                "\tpl.id_pelayanan,\n" +
                "\tpl.nama_pelayanan,\n" +
                "\tjd.tanggal,\n" +
                "\tsh.jam_awal,\n" +
                "\tsh.jam_akhir,\n" +
                "\tjd.branch_id,\n" +
                "\tbr.branch_name,\n" +
                "\tdk.kuota,\n" +
                "\tjdd.flag_libur\n" +
                "FROM \n" +
                "\tim_simrs_dokter dk\n" +
                "\tINNER JOIN im_simrs_dokter_pelayanan dpl ON dk.id_dokter = dpl.id_dokter\n" +
                "\tINNER JOIN im_simrs_pelayanan pl ON pl.id_pelayanan = dpl.id_pelayanan\n" +
                "\tINNER JOIN im_hris_pegawai pg ON pg.nip=dk.id_dokter\n" +
                "\tINNER JOIN it_hris_jadwal_shift_kerja_detail jdd ON jdd.nip=pg.nip\n" +
                "\tINNER JOIN it_hris_jadwal_shift_kerja jd ON jd.jadwal_shift_kerja_id=jdd.jadwal_shift_kerja_id\n" +
                "\tINNER JOIN im_hris_shift sh ON sh.shift_id=jdd.shift_id\n" +
                "\tINNER JOIN it_hris_pegawai_position pp ON pp.nip=pg.nip\n" +
                "\tINNER JOIN im_position ps ON ps.position_id=pp.position_id\n" +
                "\tINNER JOIN im_hris_kelompok_position kp ON kp.kelompok_id=ps.kelompok_id\n" +
                "\tINNER JOIN im_branches br ON br.branch_id=jd.branch_id\n" +
                "WHERE \n" +
                "\tdk.flag='Y'\n" +
                "\tAND dpl.flag='Y'\n" +
                "\tAND pl.flag='Y'\n" +
                "\tAND pg.flag='Y'\n" +
                "\tAND jdd.flag='Y'\n" +
                "\tAND jd.flag='Y'\n" +
                "\tAND sh.flag='Y'\n" +
                "\tAND pp.flag='Y'\n" +
                "\tAND ps.flag='Y'\n" +
                "\tAND kp.flag='Y'\n" +
                "\tAND br.flag='Y'\n" + searchPelayanan + searchKelompok + searchBranch + searchNip + searchTanggal +
                "ORDER BY\n" +
                "\tpg.nip";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            JadwalPelayananDTO result = new JadwalPelayananDTO();
            result.setIdDokter((String) row[0]);
            result.setNamaDokter((String) row[1]);
            result.setIdPelayanan((String) row[2]);
            result.setNamaPelayanan((String) row[3]);
            result.setTanggal((Date) row[4]);
            result.setJamAwal((String) row[5]);
            result.setJamAkhir((String) row[6]);
            result.setBranchId((String) row[7]);
            result.setBranchName((String) row[8]);
            result.setKuota((String) row[9]);
            result.setFlagLibur((String) row[10]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItJadwalShiftKerjaEntity> getJadwalShiftKerjaByUnitAndTanggal(String branchId, Date tanggal) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItJadwalShiftKerjaEntity.class);

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.add(Restrictions.eq("branchId", branchId));
        criteria.add(Restrictions.eq("tanggal", tanggal));

        // Order by
        criteria.addOrder(Order.desc("jadwalShiftKerjaId"));

        List<ItJadwalShiftKerjaEntity> results = criteria.list();

        return results;
    }

    public List<JadwalShiftKerjaDetail> getJadwalShiftKerjaByUnitAndProfesiAndTanggal(String branchId, Date tglFrom, Date tglTo, String profesiId) {
        List<JadwalShiftKerjaDetail> listOfResult = new ArrayList<JadwalShiftKerjaDetail>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\tjd.nip,\n" +
                "\tjd.nama_pegawai,\n" +
                "\ts.shift_name\n" +
                "FROM\n" +
                "\tit_hris_jadwal_shift_kerja_detail jd INNER JOIN\n" +
                "\tit_hris_jadwal_shift_kerja jk ON jd.jadwal_shift_kerja_id=jk.jadwal_shift_kerja_id INNER JOIN\n" +
                "\tim_hris_shift s ON s.shift_id=jd.shift_id " +
                "WHERE\n" +
                "\tjd.flag='Y'\n" +
                "\tAND jk.flag='Y'\n" +
                "\tAND jd.profesi_id='" + profesiId + "'\n" +
                "\tAND jk.branch_id='" + branchId + "'\n" +
                "\tAND jk.tanggal >= '" + tglFrom + "' AND jk.tanggal <='" + tglTo + "'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            JadwalShiftKerjaDetail result = new JadwalShiftKerjaDetail();
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setShiftName((String) row[2]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<JadwalShiftKerjaDetail> getJadwalShiftByBulanTahun(String nip, String branchId, String profesiId, String firstDate, String lastDate) {
        String searchNip = "";
        String searchBranchId = "";
        String searchProfesiId = "";

        if (nip != null && !nip.equalsIgnoreCase("")) {
            searchNip = " AND dt.nip = " + " '" + nip + "' ";
        }

        if (branchId != null && !branchId.equalsIgnoreCase("")) {
            searchBranchId = " AND sk.branch_id = " + " '" + branchId + "' ";
        }

        if (profesiId != null && !profesiId.equalsIgnoreCase("")) {
            searchProfesiId = " AND dt.profesi_id = " + " '" + profesiId + "' ";
        }

        List<JadwalShiftKerjaDetail> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<>();
        String query = "SELECT \n" +
                "dt.jadwal_shift_kerja_detail_id, \n" +
                "sk.jadwal_name, \n" +
                "sf.shift_name, \n" +
                "sf.jam_awal, \n" +
                "sf.jam_akhir, \n" +
                "dt.nip, \n" +
                "dt.nama_pegawai, \n" +
                "dt.profesi_name, \n" +
                "sk.branch_id, \n" +
                "dt.on_call, \n" +
                "dt.flag_panggil \n" +
                "FROM it_hris_jadwal_shift_kerja_detail dt\n" +
                "JOIN it_hris_jadwal_shift_kerja sk ON dt.jadwal_shift_kerja_id = sk.jadwal_shift_kerja_id\n" +
                "JOIN im_hris_shift sf ON dt.shift_id = sf.shift_id\n" +
                "WHERE dt.flag = 'Y'" + searchNip + searchBranchId + searchProfesiId + "\n" +
                "AND sk.tanggal >= '" + firstDate + "' \n" +
                "AND sk.tanggal <= '" + lastDate + "' \n" +
                "ORDER BY sk.tanggal DESC";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            JadwalShiftKerjaDetail result = new JadwalShiftKerjaDetail();
            result.setJadwalShiftKerjaDetailId((String) row[0]);
            result.setJadwalName(((String) row[1]));
            result.setShiftName((String) row[2]);
            result.setJamAwal((String) row[3]);
            result.setJamAkhir((String) row[4]);
            result.setNip((String) row[5]);
            result.setNamaPegawai((String) row[6]);
            result.setProfesiName((String) row[7]);
            result.setBranchId((String) row[8]);
            result.setOnCall((String) row[9]);
            result.setFlagPanggil((String) row[10]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<JadwalPelayananDTO> getListJadwalDokter(String branchId, String idPelayanan, String noteLike) {
        List<JadwalPelayananDTO> pelayananDTOList = new ArrayList<>();
        String notLikeDokter = "";

        if(noteLike != null && !"".equalsIgnoreCase(noteLike)){
            notLikeDokter = "AND a.id_dokter NOT IN ("+noteLike+") \n";
        }

        String SQL = "SELECT\n" +
                "a.id_dokter,\n" +
                "a.nama_dokter,\n" +
                "a.sip,\n" +
                "a.kode_dpjp,\n" +
                "a.kuota_tele,\n" +
                "a.kuota_on_site,\n" +
                "d.tanggal,\n" +
                "e.jam_awal,\n" +
                "e.jam_akhir,\n" +
                "c.flag_libur,\n" +
                "b.id_pelayanan,\n" +
                "g.terpakai_nonbpjs,\n" +
                "h.terpakai_bpjs,\n" +
                "a.kuota_bpjs,\n" +
                "f.foto_upload,\n" +
                "e.branch_id\n" +
                "FROM im_simrs_dokter a\n" +
                "INNER JOIN im_simrs_dokter_pelayanan b ON a.id_dokter = b.id_dokter\n" +
                "INNER JOIN it_hris_jadwal_shift_kerja_detail c ON a.id_dokter = c.nip\n" +
                "INNER JOIN it_hris_jadwal_shift_kerja d ON c.jadwal_shift_kerja_id = d.jadwal_shift_kerja_id\n" +
                "INNER JOIN im_hris_shift e ON c.shift_id = e.shift_id\n" +
                "INNER JOIN im_hris_pegawai f ON a.id_dokter = f.nip\n" +
                "LEFT JOIN (\n" +
                "SELECT\n" +
                "COUNT(c.id_dokter) as terpakai_nonbpjs,\n" +
                "c.id_dokter\n" +
                "FROM it_simrs_header_checkup a \n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN it_simrs_dokter_team c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                "INNER JOIN im_simrs_pelayanan d ON b.id_pelayanan = d.id_pelayanan\n" +
                "WHERE CAST(a.created_date AS DATE) = CURRENT_DATE\n" +
                "AND d.tipe_pelayanan IN ('rawat_jalan')\n" +
                "AND b.id_jenis_periksa_pasien NOT LIKE 'bpjs'\n" +
                "GROUP BY c.id_dokter\n" +
                ") g ON a.id_dokter = g.id_dokter\n" +
                "LEFT JOIN (\n" +
                "SELECT\n" +
                "COUNT(c.id_dokter) as terpakai_bpjs,\n" +
                "c.id_dokter\n" +
                "FROM it_simrs_header_checkup a \n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN it_simrs_dokter_team c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                "INNER JOIN im_simrs_pelayanan d ON b.id_pelayanan = d.id_pelayanan\n" +
                "WHERE CAST(a.created_date AS DATE) = CURRENT_DATE\n" +
                "AND d.tipe_pelayanan IN ('rawat_jalan')\n" +
                "AND b.id_jenis_periksa_pasien LIKE 'bpjs'\n" +
                "GROUP BY c.id_dokter\n" +
                ") h ON a.id_dokter = h.id_dokter\n" +
                "WHERE e.branch_id = :branchId \n" +
                "AND d.tanggal = CURRENT_DATE\n" + notLikeDokter+
                "AND b.id_pelayanan = :idPelayanan\n" +
                "AND a.flag = 'Y' \n" +
                "AND b.flag = 'Y' \n" +
                "AND c.flag = 'Y' \n";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .setParameter("idPelayanan", idPelayanan)
                .list();

        if (result.size() > 0) {
            for (Object[] obj : result) {
                JadwalPelayananDTO jadwalPelayananDTO = new JadwalPelayananDTO();
                jadwalPelayananDTO.setIdDokter(obj[0] != null ? (String) obj[0] : "");
                jadwalPelayananDTO.setNamaDokter(obj[1] != null ? (String) obj[1] : "");
                jadwalPelayananDTO.setSip(obj[2] != null ? (String) obj[2] : "");
                jadwalPelayananDTO.setKodeDpjp(obj[3] != null ? (String) obj[3] : "");
                jadwalPelayananDTO.setKuota(obj[4] != null ? (String) obj[4] : "");
                jadwalPelayananDTO.setKuotaOnSite(obj[5] != null ? (BigInteger) obj[5] : new BigInteger(String.valueOf("0")));
                jadwalPelayananDTO.setTanggal(obj[6] != null ? (Date) obj[6] : null);
                jadwalPelayananDTO.setJamAwal(obj[7] != null ? (String) obj[7] : "");
                jadwalPelayananDTO.setJamAkhir(obj[8] != null ? (String) obj[8] : "");
                jadwalPelayananDTO.setFlagLibur(obj[9] != null ? (String) obj[9] : "");
                jadwalPelayananDTO.setIdPelayanan(obj[10] != null ? (String) obj[10] : "");
                jadwalPelayananDTO.setKuotaTerpakaiNonBpjs(obj[11] != null ? (BigInteger) obj[11] : new BigInteger(String.valueOf("0")));
                jadwalPelayananDTO.setKuotaTerpakaiBpjs(obj[12] != null ? (BigInteger) obj[12] : new BigInteger(String.valueOf("0")));
                jadwalPelayananDTO.setKuotaBpjs(obj[13] != null ? (BigInteger) obj[13] : new BigInteger(String.valueOf("0")));
                if(obj[14] != null && !"".equalsIgnoreCase(obj[14].toString())){
                    jadwalPelayananDTO.setUrlImg(obj[14] != null ? CommonConstant.RESOURCE_PATH_USER_PHOTO+(String) obj[14] : "");
                }
                pelayananDTOList.add(jadwalPelayananDTO);
            }
        }
        return pelayananDTOList;
    }
}
