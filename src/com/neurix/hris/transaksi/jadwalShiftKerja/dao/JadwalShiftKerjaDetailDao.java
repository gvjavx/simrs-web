package com.neurix.hris.transaksi.jadwalShiftKerja.dao;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.ItJadwalShiftKerjaDetailEntity;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.ItJadwalShiftKerjaEntity;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerjaDetail;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
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
public class JadwalShiftKerjaDetailDao extends GenericDao<ItJadwalShiftKerjaDetailEntity, String> {

    @Override
    protected Class<ItJadwalShiftKerjaDetailEntity> getEntityClass() {
        return ItJadwalShiftKerjaDetailEntity.class;
    }

    @Override
    public List<ItJadwalShiftKerjaDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItJadwalShiftKerjaDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jadwal_shift_kerja_id")!=null) {
                criteria.add(Restrictions.eq("jadwalShiftKerjaId", (String) mapCriteria.get("jadwal_shift_kerja_id")));
            }
            if (mapCriteria.get("jadwal_shift_kerja_detail_id")!=null) {
                criteria.add(Restrictions.eq("jadwalShiftKerjaDetailId", (String) mapCriteria.get("jadwal_shift_kerja_detail_id")));
            }
            if (mapCriteria.get("shift_group_id")!=null) {
                criteria.add(Restrictions.eq("shiftGroupId", (String) mapCriteria.get("shift_group_id")));
            }
            if (mapCriteria.get("profesi_id")!=null) {
                criteria.add(Restrictions.eq("profesiId", (String) mapCriteria.get("profesi_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("jadwalShiftKerjaDetailId"));

        List<ItJadwalShiftKerjaDetailEntity> results = criteria.list();

        return results;
    }
    public String getNextJadwalShiftKerjaDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jadwal_shift_kerja_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "JSKD"+sId;
    }

    public List<JadwalShiftKerjaDetail> getPegawaiByKelompokPositionId(String kelompokPositionId,String branchId) throws HibernateException {
        List<JadwalShiftKerjaDetail> listOfResult = new ArrayList<JadwalShiftKerjaDetail>();
        List<Object[]> results = new ArrayList<Object[]>();
        String tipeWhere = "";
        if(!("").equalsIgnoreCase(kelompokPositionId)){
            tipeWhere += "AND position_pegawai.bagian_id = '"+kelompokPositionId+"' \n";
        }
        if(!("").equalsIgnoreCase(branchId)){
            tipeWhere += "AND posisi.branch_id = '"+branchId+"' \n";
        }
        String query = "SELECT\n" +
                "pegawai.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposition_bagian.nama_bagian,\n" +
                "\tposition_pegawai.position_name\n" +
                "FROM\n" +
                "\tim_hris_profesi_pegawai position \n" +
                "\tLEFT JOIN it_hris_pegawai_position posisi ON position.profesi_id = posisi.profesi_id \n" +
                "\tLEFT JOIN im_hris_pegawai pegawai ON pegawai.nip = posisi.nip\n" +
                "\tLEFT JOIN im_hris_profesi_pegawai profesi ON profesi.profesi_id=position.profesi_id\n" +
                "\tLEFT JOIN im_position position_pegawai ON posisi.position_id = position_pegawai.position_id\n" +
                "\tLEFT JOIN im_hris_position_bagian position_bagian ON position_pegawai.bagian_id = position_bagian.bagian_id\n" +
                "WHERE\n" +
                "\tpegawai.shift='Y' AND\n" +
                "\tpegawai.flag='Y' AND\n" +
                "\tposisi.flag='Y'\n" +
                tipeWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            JadwalShiftKerjaDetail result = new JadwalShiftKerjaDetail();
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setProfesiName((String) row[2]);
            result.setPositionName((String) row[3]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public Boolean foundShiftKerjaByNipAndTime(String nip, String stTanggal, String stTime){
        String SQL = "SELECT \n" +
                "skd.jadwal_shift_kerja_detail_id, \n" +
                "sk.jadwal_shift_kerja_id, \n" +
                "ms.shift_id, \n" +
                "sk.tanggal, \n" +
                "skd.nip,\n" +
                "ms.jam_awal,\n" +
                "ms.jam_akhir\n" +
                "FROM (SELECT * FROM it_hris_jadwal_shift_kerja_detail WHERE flag = 'Y') skd\n" +
                "INNER JOIN (SELECT * FROM it_hris_jadwal_shift_kerja WHERE flag = 'Y') sk ON sk.jadwal_shift_kerja_id = skd.jadwal_shift_kerja_id\n" +
                "INNER JOIN im_hris_shift ms ON ms.shift_id = skd.shift_id\n" +
                "WHERE sk.tanggal = '"+stTanggal+" '\n" +
                "AND skd.nip = '"+nip+"' \n" +
                "AND ms.jam_awal <= '"+stTime+"' \n" +
                "AND ms.jam_akhir >= '"+stTime+"' ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (results != null && results.size() > 0)
            return true;
        else
            return false;

    }

    public String getShifIdByNipAndTanggal(String nip, String tanggal, String tipeRole, String branchId){

        String SQL = "SELECT \n" +
                    "ms.shift_id, \n" +
                    "skd.jadwal_shift_kerja_detail_id\n" +
                    "FROM (SELECT * FROM it_hris_jadwal_shift_kerja_detail WHERE flag = 'Y') skd\n" +
                    "INNER JOIN (SELECT * FROM it_hris_jadwal_shift_kerja WHERE flag = 'Y') sk ON sk.jadwal_shift_kerja_id = skd.jadwal_shift_kerja_id\n" +
                    "INNER JOIN (\n" +
                    "\tSELECT \n" +
                    "\t* \n" +
                    "\tFROM im_hris_shift \n" +
                    "\tWHERE branch_id = '"+branchId+"' \n" +
                    "\tAND tipe_shift_kasir = '"+tipeRole+"' \n" +
                    "\tAND flag = 'Y' \n" +
                    ") ms ON ms.shift_id = skd.shift_id\n" +
                    "WHERE sk.tanggal = '"+tanggal+"'\n" +
                    "AND skd.nip = '"+nip+"'";

        List<Object[]> objects = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        String id = "";
        if (objects.size() > 0){
            Object[] obj = objects.get(0);
            id =  obj[0].toString();
        }
        return id;
    }

    public String checkByNipAndShift(String nip, String jadwalId, String shiftId){
        String status = "N";
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItJadwalShiftKerjaDetailEntity.class);

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.add(Restrictions.eq("nip", nip));
        criteria.add(Restrictions.eq("jadwalShiftKerjaId", jadwalId));
        criteria.add(Restrictions.eq("shiftId", shiftId));

        // Order by
        criteria.addOrder(Order.desc("jadwalShiftKerjaDetailId"));

        List<ItJadwalShiftKerjaEntity> results = criteria.list();

        if(results.size() != 0) status = "Y";

        return status;
    }

}
