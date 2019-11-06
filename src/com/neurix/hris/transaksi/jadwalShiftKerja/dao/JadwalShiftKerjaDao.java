package com.neurix.hris.transaksi.jadwalShiftKerja.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.ItJadwalShiftKerjaEntity;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalKerjaDTO;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerja;
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

}
