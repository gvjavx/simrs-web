package com.neurix.hris.master.dashboardAbsensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.dashboardAbsensi.model.DashboardAbsensi;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import com.neurix.hris.master.dashboard.model.ImDashboardEntity;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class DashboardAbsensiDao extends GenericDao<DashboardAbsensi, String> {

    @Override
    protected Class<DashboardAbsensi> getEntityClass() {
        return DashboardAbsensi.class;
    }

    @Override
    public List<DashboardAbsensi> getByCriteria(Map mapCriteria) {
        return null;
    }

    // get Absensi by tahun
    public List<DashboardAbsensi> getKaryawanTelat(String bulan, String tahun){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";

        if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "\tand EXTRACT(MONTH FROM absensi.tanggal) = "+bulan+"\n" ;
        }

        String query = "select\n" +
                "\tabsensi.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposition.position_name,\n" +
                "\tbidang.department_name,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount(absensi.nip) as jumlah\n" +
                "from\n" +
                "\tit_hris_absensi_pegawai absensi\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = absensi.nip\n" +
                "\tleft join it_hris_pegawai_position posisi on posisi.nip = absensi.nip\n" +
                "\tleft join im_position position on position.position_id = posisi.position_id\n" +
                "\tleft join im_hris_department bidang on bidang.department_id = position.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id  = position.bagian_id\n" +
                "where\n" +
                "\tEXTRACT(YEAR FROM absensi.tanggal) = "+tahun+"\n" + qryBulan +
                "\tand absensi.status_absensi = '02'\n" +
                "group by\n" +
                "\tabsensi.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposition.position_name,\n" +
                "\tbidang.department_name,\n" +
                "\tbagian.nama_bagian\n" +
                "order by\n" +
                "\tjumlah desc\n" +
                "limit 10";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setDivisiName((String) row[3]);
            result.setBagianName((String) row[4]);
            result.setJumlahTelat(row[5] + "");

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // get Absensi Telat
    public List<DashboardAbsensi> getKaryawanAbsensiTelat(String tanggal){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String query = "select\n" +
                "\tabsensi.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposition.position_name,\n" +
                "\tbidang.department_name,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tabsensi.jam_masuk\n" +
                "from\n" +
                "\tit_hris_absensi_dashboard absensi\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = absensi.nip\n" +
                "\tleft join it_hris_pegawai_position posisi on posisi.nip = absensi.nip\n" +
                "\tleft join im_position position on position.position_id = posisi.position_id\n" +
                "\tleft join im_hris_department bidang on bidang.department_id = position.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id  = position.bagian_id\n" +
                "where\n" +
                "\tabsensi.tanggal = '"+tanggal+"'\n" +
                "\tand absensi.status = '02'\n" +
                "\tand absensi.nip != '16-0431'\n" + //Suyanto (Driver direktur)
                "order by\n" +
                "\tabsensi.jam_masuk desc\n" +
                "limit 3";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setDivisiName((String) row[3]);
            result.setBagianName((String) row[4]);
            result.setJamMasuk((String) row[5]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // get Absensi Rajin
    public List<DashboardAbsensi> getKaryawanAbsensiRajin(String tanggal){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String query = "select\n" +
                "\tabsensi.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposition.position_name,\n" +
                "\tbidang.department_name,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tabsensi.jam_masuk\n" +
                "from\n" +
                "\tit_hris_absensi_dashboard absensi\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = absensi.nip\n" +
                "\tleft join it_hris_pegawai_position posisi on posisi.nip = absensi.nip\n" +
                "\tleft join im_position position on position.position_id = posisi.position_id\n" +
                "\tleft join im_hris_department bidang on bidang.department_id = position.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id  = position.bagian_id\n" +
                "where\n" +
                "\tabsensi.tanggal = '"+tanggal+"'\n" +
                "\tand absensi.status = '01'\n" +
                "order by\n" +
                "\tabsensi.jam_masuk \n" +
                "limit 3";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setDivisiName((String) row[3]);
            result.setBagianName((String) row[4]);
            result.setJamMasuk((String) row[5]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // get Absensi Mangkir
    public List<DashboardAbsensi> getKaryawanAbsensiMangkir(String tanggal){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String query = "select\n" +
                "\tabsensi.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposition.position_name,\n" +
                "\tbidang.department_name,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tabsensi.jam_masuk\n" +
                "from\n" +
                "\tit_hris_absensi_dashboard absensi\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = absensi.nip\n" +
                "\tleft join it_hris_pegawai_position posisi on posisi.nip = absensi.nip\n" +
                "\tleft join im_position position on position.position_id = posisi.position_id\n" +
                "\tleft join im_hris_department bidang on bidang.department_id = position.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id  = position.bagian_id\n" +
                "where\n" +
                "\tabsensi.tanggal = '"+tanggal+"'\n" +
                "\tand absensi.status = '00'\n" +
                "order by\n" +
                "\tabsensi.jam_masuk desc\n" +
                "limit 6";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setPositionName((String) row[2]);
            if(row[3] != null){
                result.setDivisiName((String) row[3]);
            }else{
                result.setDivisiName("-");
            }
            result.setBagianName((String) row[4]);
            result.setJamMasuk((String) row[5]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // get Absensi by nip
    public List<DashboardAbsensi> getKaryawanTelat(String bulan, String tahun, String nip){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";
        if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "\tand EXTRACT(MONTH FROM absensi.tanggal) = "+bulan+"\n" ;
        }

        String query = "select\n" +
                "\tabsensi.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposition.position_name,\n" +
                "\tbidang.department_name,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tabsensi.tanggal,\n" +
                "\tabsensi.jam_masuk\n" +
                "from\n" +
                "\tit_hris_absensi_pegawai absensi\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = absensi.nip\n" +
                "\tleft join it_hris_pegawai_position posisi on posisi.nip = absensi.nip\n" +
                "\tleft join im_position position on position.position_id = posisi.position_id\n" +
                "\tleft join im_hris_department bidang on bidang.department_id = position.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id  = position.bagian_id\n" +
                "where\n" +
                "\tEXTRACT(YEAR FROM absensi.tanggal) = "+tahun+"\n" + qryBulan +
                "\tand absensi.status_absensi = '02'\n" +
                "\tand absensi.nip = '"+nip+"'" +
                "order by\n" +
                "\tabsensi.tanggal";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setDivisiName((String) row[3]);
            result.setBagianName((String) row[4]);
            result.setTanggalMasuk((Date)row[5]);
            result.setStrTanggalMasuk(CommonUtil.convertDateToString((Date)row[5]));
            result.setJamMasuk((String)row[6]);
            result.setJumlahTelat(penguranganJam((String)row[6]));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    private String penguranganJam(String jam){
        String hasil = "";

        DateTimeFormatter formatter = DateTimeFormat.forPattern("H:mm a");
        DateTime time1 = formatter.parseDateTime("07:30 AM");
        DateTime time2 = formatter.parseDateTime(jam + " AM");

        Duration duration = new Duration(time1, time2);

        long pengurang = 60 ;
        pengurang *= duration.getStandardHours();

        hasil = duration.getStandardHours() + ":" + (duration.getStandardMinutes() - pengurang);
        return hasil;
    }

    // medical records
    public List<DashboardAbsensi> dashboardMedicalRecords(String tahun, String bulan){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";

        if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "\tand EXTRACT(MONTH FROM medical.tanggal_berobat) = " + bulan+"\n" ;
        }

        String query = "select \n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name,\n" +
                "\tsum(data.jumlah_biaya) as jumlah_pengobatan\n" +
                "from\n" +
                "\tim_hris_department divisi\n" +
                "\tleft join (select\n" +
                "\t\t\tmedical.medical_record_id,\n" +
                "\t\t\tmedical.branch_id,\n" +
                "\t\t\tmedical.nip,\n" +
                "\t\t\tit_posisi.position_id,\n" +
                "\t\t\tposisi.department_id,\n" +
                "\t\t\tdivisi.department_name,\n" +
                "\t\t\tposisi.bagian_id,\n" +
                "\t\t\tbagian.nama_bagian,\n" +
                "\t\t\tmedical.tanggal_berobat,\n" +
                "\t\t\tmedical.tipe_orang_berobat,\n" +
                "\t\t\tmedical.tipe_pengobatan,\n" +
                "\t\t\tCAST(coalesce(medical.jumlah_biaya, '0') AS integer) as jumlah_biaya\n" +
                "\t\tfrom\n" +
                "\t\t\tit_hris_medical_record medical\n" +
                "\t\t\tleft join it_hris_pegawai_position it_posisi on it_posisi.nip = medical.nip\n" +
                "\t\t\tleft join im_position posisi on posisi.position_id = it_posisi.position_id\n" +
                "\t\t\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "\t\t\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\t\twhere\n" +
                "\t\t\tmedical.approved = 'Y'\n" + qryBulan +
                "\t\t\tand EXTRACT(YEAR FROM medical.tanggal_berobat) = "+tahun+") data on data.department_id = divisi.department_id\n" +
                "where\n" +
                "\tdivisi.department_id not in ('D08', 'D09', 'D10', 'D11', 'D12')\n" +
                "group by\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name\n" +
                "order by\n" +
                "\tdivisi.department_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setDivisiId((String) row[0]);
            result.setDivisiName((String) row[1]);

            if(row[2] != null){
                result.setJumlahPengobatan(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                result.setJumlahPengobatan(BigDecimal.valueOf(0));
            }

            result.setStrJumlahPengobatan(CommonUtil.numbericFormat(result.getJumlahPengobatan(), "###,###"));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // Lembur
    public List<DashboardAbsensi> dashboardLembur(String tanggal1, String tanggal2){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";

        /*if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "\tand EXTRACT(MONTH FROM absensi.tanggal) = " + bulan+"\n" ;
        }*/

        String query = "select \n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name,\n" +
                "\tsum(data.biaya_lembur) as jumlah_lembur\n" +
                "from\n" +
                "\tim_hris_department divisi\n" +
                "\tleft join (select\n" +
                "\t\t\tabsensi.absensi_pegawai_id,\n" +
                "\t\t\tabsensi.branch_id,\n" +
                "\t\t\tabsensi.nip,\n" +
                "\t\t\tit_posisi.position_id,\n" +
                "\t\t\tposisi.department_id,\n" +
                "\t\t\tdivisi.department_name,\n" +
                "\t\t\tposisi.bagian_id,\n" +
                "\t\t\tbagian.nama_bagian,\n" +
                "\t\t\tabsensi.tanggal,\n" +
                "\t\t\tabsensi.lama_lembur,\n" +
                "\t\t\tabsensi.jam_lembur,\n" +
                "\t\t\tCAST(coalesce(absensi.biaya_lembur, '0') AS integer) as biaya_lembur\n" +
                "\t\tfrom\n" +
                "\t\t\tit_hris_absensi_pegawai absensi\n" +
                "\t\t\tleft join it_hris_pegawai_position it_posisi on it_posisi.nip = absensi.nip\n" +
                "\t\t\tleft join im_position posisi on posisi.position_id = it_posisi.position_id\n" +
                "\t\t\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "\t\t\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\t\twhere\n" +
                "\t\t\tabsensi.lembur = 'Y'\n" +
                "\t\tand absensi.tanggal >= '"+tanggal1+"'\n" +
                "\t\t\tand absensi.tanggal <= '"+tanggal2+"'"+
                ") data on data.department_id = divisi.department_id\n" +
                "where\n" +
                "\tdivisi.department_id not in ('D08', 'D09', 'D10', 'D11', 'D12')\n" +
                "group by\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name\n" +
                "order by\n" +
                "\tdivisi.department_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setDivisiId((String) row[0]);
            result.setDivisiName((String) row[1]);

            if(row[2] != null){
                result.setJumlahLembur(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                result.setJumlahLembur(BigDecimal.valueOf(0));
            }

            result.setStrJumlahLembur(CommonUtil.numbericFormat(result.getJumlahLembur(), "###,###"));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // medical records detail divisi
    public List<DashboardAbsensi> dashboardMedicalRecords(String bulan, String tahun, String divisiId){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";

        if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "\tand EXTRACT(MONTH FROM medical.tanggal_berobat) = " + bulan+"\n" ;
        }

        String query = "select \n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tsum(data.jumlah_biaya) as jumlah_pengobatan\n" +
                "from\n" +
                "\tim_hris_position_bagian bagian\n" +
                "\tleft join (select\n" +
                "\t\t\tmedical.medical_record_id,\n" +
                "\t\t\tmedical.branch_id,\n" +
                "\t\t\tmedical.nip,\n" +
                "\t\t\tit_posisi.position_id,\n" +
                "\t\t\tposisi.department_id,\n" +
                "\t\t\tdivisi.department_name,\n" +
                "\t\t\tposisi.bagian_id,\n" +
                "\t\t\tbagian.nama_bagian,\n" +
                "\t\t\tmedical.tanggal_berobat,\n" +
                "\t\t\tmedical.tipe_orang_berobat,\n" +
                "\t\t\tmedical.tipe_pengobatan,\n" +
                "\t\t\tCAST(coalesce(medical.jumlah_biaya, '0') AS integer) as jumlah_biaya\n" +
                "\t\tfrom\n" +
                "\t\t\tit_hris_medical_record medical\n" +
                "\t\t\tleft join it_hris_pegawai_position it_posisi on it_posisi.nip = medical.nip\n" +
                "\t\t\tleft join im_position posisi on posisi.position_id = it_posisi.position_id\n" +
                "\t\t\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "\t\t\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\t\twhere\n" +
                "\t\t\tmedical.approved = 'Y'\n" +
                "\t\t\tand posisi.department_id = '"+divisiId+"'\n" +
                "\t\t\tand EXTRACT(YEAR FROM medical.tanggal_berobat) = "+tahun+"\n" + qryBulan +
                "\t\t\t) data on data.bagian_id = bagian.bagian_id\n" +
                "where\n" +
                "\tbagian.bagian_id = data.bagian_id\n" +
                "group by\n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "order by\n" +
                "\tbagian.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);

            if(row[2] != null){
                result.setJumlahPengobatan(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                result.setJumlahPengobatan(BigDecimal.valueOf(0));
            }


            listOfResult.add(result);
        }
        return listOfResult;
    }

    //Lembur detail divisi
    public List<DashboardAbsensi> dashboardLembur(String tanggal1, String tanggal2,String divisiId){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";

        String query = "select \n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tsum(data.biaya_lembur) as biaya_lembur\n" +
                "from\n" +
                "\tim_hris_position_bagian bagian\n" +
                "\tleft join (select\n" +
                "\t\t\tabsensi.absensi_pegawai_id,\n" +
                "\t\t\tabsensi.branch_id,\n" +
                "\t\t\tabsensi.nip,\n" +
                "\t\t\tit_posisi.position_id,\n" +
                "\t\t\tposisi.department_id,\n" +
                "\t\t\tdivisi.department_name,\n" +
                "\t\t\tposisi.bagian_id,\n" +
                "\t\t\tbagian.nama_bagian,\n" +
                "\t\t\tabsensi.tanggal,\n" +
                "\t\t\tabsensi.lama_lembur,\n" +
                "\t\t\tabsensi.jam_lembur,\n" +
                "\t\t\tCAST(coalesce(absensi.biaya_lembur, '0') AS integer) as biaya_lembur\n" +
                "\t\tfrom\n" +
                "\t\t\tit_hris_absensi_pegawai absensi\n" +
                "\t\t\tleft join it_hris_pegawai_position it_posisi on it_posisi.nip = absensi.nip\n" +
                "\t\t\tleft join im_position posisi on posisi.position_id = it_posisi.position_id\n" +
                "\t\t\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "\t\t\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\t\twhere\n" +
                "\t\t\tabsensi.lembur = 'Y'\n" +
                "\t\t\tand posisi.department_id = '"+divisiId+"'\n" +
                "\t\t\tand absensi.tanggal >='"+tanggal1+"' \n" +
                "\t\t\tand absensi.tanggal <='"+tanggal2+"'" +
                "\t\t\t) data on data.bagian_id = bagian.bagian_id\n" +
                "where\n" +
                "\tbagian.bagian_id = data.bagian_id\n" +
                "group by\n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "order by\n" +
                "\tbagian.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);

            if(row[2] != null){
                result.setJumlahLembur(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                result.setJumlahLembur(BigDecimal.valueOf(0));
            }

            result.setStrJumlahLembur(CommonUtil.numbericFormat(result.getJumlahLembur(), "###,###"));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // medical records detail top 10
    public List<DashboardAbsensi> dashboardMedicalRecordsTop(String bulan, String tahun, String divisiId){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";
        String qryDivisiId = "";

        if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "and EXTRACT(MONTH FROM medical.tanggal_berobat) = " + bulan+"\n" ;
        }

        if(!divisiId.equalsIgnoreCase("")){
            qryDivisiId = "and posisi.department_id = '" + divisiId +"'\n" ;
        }

        String query = "select\n" +
                "\tmedical.nip,\n" +
                "\tbiodata.nama_pegawai,\n" +
                "\tposisi.position_name,\n" +
                "\tsum(CAST(coalesce(medical.jumlah_biaya, '0') AS integer)) as jumlah_biaya\n" +
                "from\n" +
                "\tit_hris_medical_record medical\n" +
                "\tleft join it_hris_pegawai_position it_posisi on it_posisi.nip = medical.nip\n" +
                "\tleft join im_hris_pegawai biodata on biodata.nip = it_posisi.nip\n" +
                "\tleft join im_position posisi on posisi.position_id = it_posisi.position_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where\n" +
                "\tmedical.approved = 'Y'\n" +
                "\tand EXTRACT(YEAR FROM medical.tanggal_berobat) = "+tahun+"\n" + qryBulan + qryDivisiId +
                "group by\n" +
                "\tmedical.nip,\n" +
                "\tbiodata.nama_pegawai,\n" +
                "\tposisi.position_name\n" +
                "order by \n" +
                "\tjumlah_biaya desc\n" +
                "limit 10";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setPositionName((String) row[2]);

            if(row[3] != null){
                result.setJumlahPengobatan(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            }else{
                result.setJumlahPengobatan(BigDecimal.valueOf(0));
            }

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // Lembur detail top 10
    public List<DashboardAbsensi> dashboardLemburTop(String tanggal1, String tanggal2, String divisiId){
        List<DashboardAbsensi> listOfResult = new ArrayList<DashboardAbsensi>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";
        String qrydivisiId = "";

        /*if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "and EXTRACT(MONTH FROM absensi.tanggal) = " + bulan+"\n" ;
        }*/

        if(!divisiId.equalsIgnoreCase("")){
            qrydivisiId = "and posisi.department_id = '" + divisiId+"'\n" ;
        }

        String query = "select\n" +
                "\tabsensi.nip,\n" +
                "\tbiodata.nama_pegawai,\n" +
                "\tposisi.position_name,\n" +
                "\tsum(CAST(coalesce(absensi.biaya_lembur, '0') AS integer)) as biaya_lembur,\n" +
                "\tsum(CAST(coalesce(absensi.lama_lembur, '0') AS integer)) as lama_lembur,\n" +
                "\tsum(CAST(coalesce(absensi.jam_lembur, '0') AS integer)) as jam_lembur\n" +
                "from\n" +
                "\tit_hris_absensi_pegawai absensi\n" +
                "\tleft join it_hris_pegawai_position it_posisi on it_posisi.nip = absensi.nip\n" +
                "\tleft join im_hris_pegawai biodata on biodata.nip = it_posisi.nip\n" +
                "\tleft join im_position posisi on posisi.position_id = it_posisi.position_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where\n" +
                "\tabsensi.lembur = 'Y'\n" +
                "\tand absensi.tanggal >= '"+tanggal1+"'\n" +
                "\t\t\tand absensi.tanggal <='"+tanggal2+"'\n" + qrydivisiId +
                "group by\n" +
                "\tabsensi.nip,\n" +
                "\tbiodata.nama_pegawai,\n" +
                "\tposisi.position_name\n" +
                "order by \n" +
                "\tbiaya_lembur desc\n" +
                "limit 10";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            DashboardAbsensi result  = new DashboardAbsensi();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setPositionName((String) row[2]);

            if(row[3] != null){
                result.setJumlahLembur(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            }else{
                result.setJumlahLembur(BigDecimal.valueOf(0));
            }

            if(row[4] != null){
                result.setLamaLembur(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }else{
                result.setLamaLembur(BigDecimal.valueOf(0));
            }

            if(row[5] != null){
                result.setJamLembur(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            }else{
                result.setJamLembur(BigDecimal.valueOf(0));
            }

            listOfResult.add(result);
        }
        return listOfResult;
    }
}
