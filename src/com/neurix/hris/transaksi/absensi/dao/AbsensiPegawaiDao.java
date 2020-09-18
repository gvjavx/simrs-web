package com.neurix.hris.transaksi.absensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawaiEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class AbsensiPegawaiDao extends GenericDao<AbsensiPegawaiEntity, String> {
    @Override
    protected Class<AbsensiPegawaiEntity> getEntityClass() {
        return AbsensiPegawaiEntity.class;
    }

    @Override
    public List<AbsensiPegawaiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("absensi_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("absensiPegawaiId", (String) mapCriteria.get("absensi_pegawai_id")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.ilike("nip", "%" + (String)mapCriteria.get("nip") + "%"));
            }
            if (mapCriteria.get("status_absensi")!=null) {
                criteria.add(Restrictions.eq("statusAbsensi", (String) mapCriteria.get("status_absensi")));
            }
            if (mapCriteria.get("lembur")!=null) {
                criteria.add(Restrictions.eq("lembur", (String) mapCriteria.get("lembur")));
            }
            if (mapCriteria.get("ijin")!=null) {
                criteria.add(Restrictions.eq("ijin", (String) mapCriteria.get("ijin")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("tanggal")!=null) {
                criteria.add(Restrictions.eq("tanggal",mapCriteria.get("tanggal")));
            }
            if (mapCriteria.get("jam_masuk")!=null) {
                criteria.add(Restrictions.eq("jamMasuk",(String) mapCriteria.get("jam_masuk")));
            }
            if (mapCriteria.get("jam_keluar")!=null) {
                criteria.add(Restrictions.eq("jamKeluar",(String) mapCriteria.get("jam_keluar")));
            }
            if (mapCriteria.get("status_absensi")!=null) {
                criteria.add(Restrictions.eq("statusAbsensi",(String) mapCriteria.get("status_absensi")));
            }
            if (mapCriteria.get("lama_lembur")!=null) {
                criteria.add(Restrictions.eq("lamaLembur",mapCriteria.get("lama_lembur")));
            }
            if (mapCriteria.get("status_absensi")!=null) {
                criteria.add(Restrictions.eq("statusAbsensi",mapCriteria.get("status_absensi")));
            }
            if (mapCriteria.get("jam_lembur")!=null) {
                criteria.add(Restrictions.eq("jamLembur",mapCriteria.get("jam_lembur")));
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
        criteria.addOrder(Order.desc("tanggal"));

        List<AbsensiPegawaiEntity> results = criteria.list();

        return results;
    }

    public List<AbsensiPegawaiEntity> getByCriteriaForReportUangMakan(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
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
        criteria.addOrder(Order.asc("tanggal"));

        List<AbsensiPegawaiEntity> results = criteria.list();

        return results;
    }

    public String getNextAbsensiPegawaiId() throws HibernateException {


        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_absensi_pegawai')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        DateFormat dm = new SimpleDateFormat("MM"); // Just the Month, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String formattedMonth = dm.format(Calendar.getInstance().getTime());
        String sId = String.format("%05d", iter.next());

        return "AP"+formattedDate+formattedMonth+sId;
    }

    public List<AbsensiPegawaiEntity> getByCriteriaForReportLembur(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("tanggal_awal")!=null) {
                criteria.add(Restrictions.ge("tanggal",mapCriteria.get("tanggal_awal")));
            }if (mapCriteria.get("tanggal_akhir")!=null) {
                criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal_akhir")));
            }
        }
        criteria.add(Restrictions.eq("lembur","Y"));
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("tanggal"));

        List<AbsensiPegawaiEntity> results = criteria.list();

        return results;
    }
    public List<AbsensiPegawaiEntity> getByCriteriaForRekapLembur(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tanggal_awal")!=null) {
                criteria.add(Restrictions.ge("tanggal",mapCriteria.get("tanggal_awal")));
            }
            if (mapCriteria.get("nip")!=null) {
                if (!("").equalsIgnoreCase(mapCriteria.get("nip").toString())){
                    criteria.add(Restrictions.eq("nip",mapCriteria.get("nip")));
                }
            }
            if (mapCriteria.get("tanggal_akhir")!=null) {
                criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal_akhir")));
            }
        }
        criteria.add(Restrictions.eq("lembur","Y"));
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("nip"));
        List<AbsensiPegawaiEntity> results = criteria.list();
        return results;
    }

    public AbsensiPegawai getSearchJadwalShift(String nip, Date tanggal) throws HibernateException{

        String id = "";
        String query = "";

        query = "SELECT * FROM\n" +
                "\t(SELECT * FROM it_hris_jadwal_shift_kerja) kerja LEFT JOIN\n" +
                "\t(SELECT * FROM it_hris_jadwal_shift_kerja_detail) kerjadetail ON kerja.jadwal_shift_kerja_id = kerjadetail.jadwal_shift_kerja_id LEFT JOIN\n" +
                "\t(SELECT * FROM im_hris_shift) shift ON kerjadetail.shift_id = shift.shift_id\n" +
                "WHERE kerjadetail.nip =:nip AND kerja.tanggal= :tanggal";

        List<Object[]> results = new ArrayList<Object[]>();

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("tanggal", tanggal)
                .setParameter("nip", nip)
                .list();

        AbsensiPegawai add = new AbsensiPegawai();
        for (Object[] row : results) {
            add.setJamMasuk(row[35].toString());
            add.setJamPulang(row[36].toString());
        }
        return add;
    }

    //digunakan untuk lembur
    public List<AbsensiPegawaiEntity> getDataLembur(String nip, String branchId, Date awal, Date akhir) throws HibernateException {
        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.le("tanggal", akhir))
                .add(Restrictions.ge("tanggal", awal))
                .add(Restrictions.eq("lembur", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("tanggal"))
                .list();

        return results;
    }
    //digunakan untuk cek realisasi
    public List<AbsensiPegawaiEntity> getListAbsensiByNipAndTanggal(String nip,Date tanggal) throws HibernateException {
        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tanggal", tanggal))
                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("lembur", "Y"))
                .addOrder(Order.asc("tanggal"))
                .list();

        return results;
    }

    //digunakan untuk upah gaji
    public List<AbsensiPegawaiEntity> getDataUpahHarian(String nip, String branchId, Date awal, Date akhir) throws HibernateException {
        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.le("tanggal", akhir))
                .add(Restrictions.ge("tanggal", awal))
                .list();

        return results;
    }

    //digunkana pada biodata
    public List<AbsensiPegawaiEntity> cariAbsensiSys(String nip, java.util.Date tgl1, java.util.Date tgl2) throws HibernateException {
        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.le("tanggal", tgl2))
                .add(Restrictions.ge("tanggal", tgl1))
                .list();
        return results;
    }
    public List<AbsensiPegawaiEntity> cariAbsensiSysStatusAbsensi(String nip, java.util.Date tgl1, java.util.Date tgl2, String statusAbsensi) throws HibernateException {
        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.le("tanggal", tgl2))
                .add(Restrictions.ge("tanggal", tgl1))
                .add(Restrictions.eq("statusAbsensi", statusAbsensi))
                .list();
        return results;
    }
    //digunakan pada absensi
    public List<AbsensiPegawaiEntity> searchDetailLembur(String nip,Date tgl) throws HibernateException {
        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tanggal", tgl))
                .list();
        return results;
    }
    public List<AbsensiPegawaiEntity> searchExistingAbsensi(String nip , Date tanggal){
        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tanggal", tanggal))
                .add(Restrictions.eq("flag","Y"))
                .list();
        return results;
    }
    //digunakan untuk get data Lembur
    public List<AbsensiPegawaiEntity> getDataLemburByNip(String nip, Date tglAwal, Date tglAkhir) throws HibernateException {
        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.ge("tanggal", tglAwal))
                .add(Restrictions.le("tanggal", tglAkhir))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("tanggal"))
                .list();
        return results;
    }

    //digunakan untuk get data absensi triwulan
    public List<AbsensiPegawaiEntity> getDataForAbsensiTriwulan(String nip, Date tglAwal, Date tglAkhir) throws HibernateException {
        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.ge("tanggal", tglAwal))
                .add(Restrictions.le("tanggal", tglAkhir))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.ne("tipeHari","hari_libur"))
                .addOrder(Order.asc("tanggal"))
                .list();

        return results;
    }

    public List<AbsensiPegawaiEntity> getListAbsensiForApproval(Map mapCriteria) {
        List<AbsensiPegawaiEntity> listOfResult = new ArrayList<AbsensiPegawaiEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String nip = null, absensiPegawaiId = null,atasan=null;
        String searchNip = "" ;
        String searchAbsensiPegawaiId = "" ;
        String searchAtasan = "" ;
        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("absensi_pegawai_id") != null) {
                absensiPegawaiId = (String) mapCriteria.get("absensi_pegawai_id");
            }
            if (mapCriteria.get("nip") != null) {
                nip = (String) mapCriteria.get("nip");
            }
            if (mapCriteria.get("nip_atasan") != null) {
                atasan = (String) mapCriteria.get("nip_atasan");
            }

            if (nip!=null){
                if(!nip.equalsIgnoreCase("")){
                    searchNip = " AND absensi.nip = '" + nip + "' " ;
                }
            }
            if (absensiPegawaiId!=null){
                if(!absensiPegawaiId.equalsIgnoreCase("")){
                    searchAbsensiPegawaiId = " AND absensi.absensi_pegawai_id = '" + absensiPegawaiId + "' " ;
                }
            }
            if (atasan!=null){
                if(!atasan.equalsIgnoreCase("")){
                    searchAtasan = " AND notifikasi.nip = '" + atasan + "' " ;
                }
            }

            String query = "SELECT absensi.* FROM  \n" +
                    "                    ( SELECT * FROM it_hris_notifikasi ) notifikasi LEFT JOIN  \n" +
                    "                    ( SELECT * FROM it_hris_absensi_pegawai ) absensi ON notifikasi.no_request=absensi.absensi_pegawai_id " +
                    "WHERE notifikasi.tipe_notif_id='TN33' AND absensi.flag='Y' "+searchAtasan+searchNip+searchAbsensiPegawaiId+" ORDER BY absensi.absensi_pegawai_id DESC";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                AbsensiPegawaiEntity absensiPegawaiEntity = new AbsensiPegawaiEntity();
                absensiPegawaiEntity.setAbsensiPegawaiId((String) row[0]);
                absensiPegawaiEntity.setNip((String) row[1]);
                absensiPegawaiEntity.setTanggal((Date) row[2]);
                absensiPegawaiEntity.setJamMasuk((String) row[3]);
                absensiPegawaiEntity.setJamKeluar((String) row[4]);
                absensiPegawaiEntity.setStatusAbsensi((String) row[5]);
                absensiPegawaiEntity.setLembur((String) row[6]);
                absensiPegawaiEntity.setIjin((String) row[7]);
                absensiPegawaiEntity.setBranchId((String) row[8]);
                absensiPegawaiEntity.setAction((String) row[9]);
                absensiPegawaiEntity.setFlag((String) row[10]);
                absensiPegawaiEntity.setCreatedDate((Timestamp) row[11]);
                absensiPegawaiEntity.setCreatedWho((String) row[12]);
                absensiPegawaiEntity.setLastUpdate((Timestamp) row[13]);
                absensiPegawaiEntity.setLastUpdateWho((String) row[14]);
                absensiPegawaiEntity.setJenisLembur((String) row[15]);
                absensiPegawaiEntity.setLamaLembur((Double) row[16]);
                absensiPegawaiEntity.setJamLembur((Double) row[17]);
                absensiPegawaiEntity.setBiayaLembur((Double) row[18]);
                absensiPegawaiEntity.setTipeHari((String) row[19]);
                absensiPegawaiEntity.setRealisasiJamLembur((Double) row[20]);
                absensiPegawaiEntity.setKeterangan((String) row[21]);
                absensiPegawaiEntity.setFlagUangMakan((String) row[22]);
                absensiPegawaiEntity.setApprovalId((String) row[23]);
                absensiPegawaiEntity.setApprovalFlag((String) row[24]);
                absensiPegawaiEntity.setApprovalName((String) row[25]);
                absensiPegawaiEntity.setApprovalDate((Timestamp) row[26]);
                absensiPegawaiEntity.setNotApprovalNote((String) row[27]);

                listOfResult.add(absensiPegawaiEntity);
            }
        }
        return listOfResult;
    }

    public List<AbsensiPegawai> getAbsensiByMonth(String nip, String branchId, String firstDate, String lastDate) {
        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        Date dtFirst = CommonUtil.convertStringToDate(firstDate);
        Date dtLast = CommonUtil.convertStringToDate(lastDate);
        String query = "SELECT tanggal, jam_masuk, jam_keluar, status_absensi, lembur, ijin, absensi_pegawai_id FROM it_hris_absensi_pegawai \n" +
                "WHERE nip = :nip\n" +
                "AND tanggal BETWEEN :firstDate AND :lastDate\n" +
                "AND branch_id = :branchId\n " +
                "AND status_absensi NOT IN ('00', '08', '10', '11', '12', '13')\n" +
                "ORDER BY tanggal ASC";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("nip", nip)
                .setParameter("branchId", branchId)
                .setParameter("firstDate", dtFirst)
                .setParameter("lastDate", dtLast)
                .list();

        if (results != null) {
            for (Object[] item : results) {
                AbsensiPegawai absensiPegawai = new AbsensiPegawai();
                absensiPegawai.setTanggal(item[0] != null ? (Date) item[0] : null);
                absensiPegawai.setJamMasuk(item[1] != null ? (String) item[1].toString() : "");
                absensiPegawai.setJamKeluar(item[2] != null ? (String) item[2].toString() : "");
                absensiPegawai.setStatusAbsensi(item[3] != null ? (String) item[3].toString() : "");
                absensiPegawai.setLembur(item[4] != null ? (String) item[4].toString() : "");
                absensiPegawai.setIjin(item[5] != null ? (String) item[5].toString() : "");
                absensiPegawai.setAbsensiPegawaiId(item[6] != null ? (String) item[6].toString() : "");

                absensiPegawaiList.add(absensiPegawai);
            }
        }

        return absensiPegawaiList;
    }

    public List<AbsensiPegawaiEntity> checkDataDelete(String statusAbsensi) throws HibernateException {

        List<AbsensiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(AbsensiPegawaiEntity.class)
                .add(Restrictions.eq("statusAbsensi", statusAbsensi))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

}