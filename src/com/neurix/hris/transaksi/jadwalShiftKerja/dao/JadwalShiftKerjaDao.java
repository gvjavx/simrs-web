package com.neurix.hris.transaksi.jadwalShiftKerja.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
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
        return null;
    }

    @Override
    public List<ItJadwalShiftKerjaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItJadwalShiftKerjaEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jadwal_shift_kerja_name")!=null) {
                criteria.add(Restrictions.ilike("jadwalShiftKerjaName", "%" + (String)mapCriteria.get("jadwal_shift_kerja_name") + "%"));
            }
            if (mapCriteria.get("jadwal_shift_kerja_id")!=null) {
                criteria.add(Restrictions.eq("jadwalShiftKerjaId", (String) mapCriteria.get("jadwal_shift_kerja_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("status_giling")!=null) {
                criteria.add(Restrictions.eq("statusGiling", (String) mapCriteria.get("status_giling")));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("tanggal",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggal",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal_selesai")));
                }
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("jadwalShiftKerjaId"));

        List<ItJadwalShiftKerjaEntity> results = criteria.list();

        return results;
    }
    public String getNextJadwalShiftKerjaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jadwal_shift_kerja')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String sId = String.format("%05d", iter.next());

        return "JSK"+formattedDate+sId;
    }

    public List<JadwalShiftKerja> getJadwalForReport (Date tanggalDari, Date tanggalSampai){
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
                "\tkerja.tanggal >= :tanggalDari AND\n" +
                "\tkerja.tanggal <= :tanggalSampai AND\n" +
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

    public List<JadwalPelayananDTO> getJadwalPelayanan (String idPelayanan, String kelompokId, String branchId, String nip, Date tanggal){
        String searchPelayanan = "" ;
        String searchKelompok = "" ;
        String searchBranch = "" ;
        String searchNip = "" ;
        String searchTanggal = "" ;

        if (idPelayanan!=null){
            if(!idPelayanan.equalsIgnoreCase("")){
                searchPelayanan = " and pl.id_pelayanan = '" + idPelayanan + "' " ;
            }
        }
        if (kelompokId!=null){
            if(!kelompokId.equalsIgnoreCase("")){
                searchKelompok = " and kp.kelompok_id = '" + kelompokId + "' " ;
            }
        }
        if(branchId!=null){
            if(!branchId.equalsIgnoreCase("")){
                searchBranch = " and pp.branch_id= '" + branchId + "' " ;
            }
        }
        if (nip!=null){
            if(!nip.equalsIgnoreCase("")){
                searchNip = " and pg.nip = '" + nip + "' " ;
            }
        }
        if (tanggal!=null){
            searchTanggal = " and jd.tanggal= '" + tanggal + "' " ;
        }

        List<JadwalPelayananDTO> listOfResult = new ArrayList<JadwalPelayananDTO>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\tdk.id_dokter,\n" +
                "\tdk.nama_dokter,\n" +
                "\tds.id_spesialis,\n" +
                "\tsp.nama_spesialis,\n" +
                "\tspl.id_pelayanan,\n" +
                "\tpl.nama_pelayanan,\n" +
                "\tjd.tanggal,\n" +
                "\tsh.jam_awal,\n" +
                "\tsh.jam_akhir\n" +
                "FROM \n" +
                "\tim_simrs_dokter dk\n" +
                "\tINNER JOIN im_simrs_dokter_spesialis ds ON ds.id_dokter = dk.id_dokter\n" +
                "\tINNER JOIN im_simrs_spesialis sp ON sp.id_spesialis = ds.id_spesialis\n" +
                "\tINNER JOIN im_simrs_pelayanan_spesialis spl ON spl.id_spesialis = ds.id_spesialis\n" +
                "\tINNER JOIN im_simrs_pelayanan pl ON pl.id_pelayanan = spl.id_pelayanan\n" +
                " \tINNER JOIN im_hris_pegawai pg ON pg.nip=dk.id_dokter\n" +
                "\tINNER JOIN it_hris_jadwal_shift_kerja_detail jdd ON jdd.nip=pg.nip\n" +
                "\tINNER JOIN it_hris_jadwal_shift_kerja jd ON jd.jadwal_shift_kerja_id=jdd.jadwal_shift_kerja_id\n" +
                "\tINNER JOIN im_hris_shift sh ON sh.shift_id=jdd.shift_id\n" +
                "\tINNER JOIN it_hris_pegawai_position pp ON pp.nip=pg.nip\n" +
                "\tINNER JOIN im_position ps ON ps.position_id=pp.position_id\n" +
                "\tINNER JOIN im_hris_kelompok_position kp ON kp.kelompok_id=ps.kelompok_id\n" +
                "WHERE \n" +
                "\tdk.flag='Y'\n" +
                "\tAND ds.flag='Y'\n" +
                "\tAND sp.flag='Y'\n" +
                "\tAND spl.flag='Y'\n" +
                "\tAND pl.flag='Y'\n" +
                "\tAND pg.flag='Y'\n" +
                "\tAND jdd.flag='Y'\n" +
                "\tAND jd.flag='Y'\n" +
                "\tAND sh.flag='Y'\n" +
                "\tAND pp.flag='Y'\n" +
                "\tAND ps.flag='Y'\n" + searchPelayanan + searchKelompok + searchBranch + searchNip + searchTanggal +
                "ORDER BY\n" +
                "\tpg.nip";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            JadwalPelayananDTO result  = new JadwalPelayananDTO();
            result.setIdDokter((String) row[0]);
            result.setNamaDokter((String) row[1]);
            result.setIdSpesialis((String) row[2]);
            result.setNamaSpesialis((String) row[3]);
            result.setIdPelayanan((String) row[4]);
            result.setNamaPelayanan((String) row[5]);
            result.setTanggal((Date) row[6]);
            result.setJamAwal((String) row[7]);
            result.setJamAkhir((String) row[8]);
            listOfResult.add(result);
        }
        return listOfResult;
    }
}
