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
            tipeWhere += "AND position.profesi_id = '"+kelompokPositionId+"' \n";
        }
        if(!("").equalsIgnoreCase(branchId)){
            tipeWhere += "AND posisi.branch_id = '"+branchId+"' \n";
        }
        String query = "SELECT\n" +
                "pegawai.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposition.profesi_name,\n" +
                "\tposition_pegawai.position_name\n" +
                "FROM\n" +
                "\tim_hris_profesi_pegawai position \n" +
                "\tLEFT JOIN it_hris_pegawai_position posisi ON position.profesi_id = posisi.profesi_id \n" +
                "\tLEFT JOIN im_hris_pegawai pegawai ON pegawai.nip = posisi.nip\n" +
                "\tLEFT JOIN im_hris_profesi_pegawai profesi ON profesi.profesi_id=position.profesi_id\n" +
                "\tLEFT JOIN im_position position_pegawai ON posisi.position_id = position_pegawai.position_id\n" +
                "WHERE\n" +
                "\tpegawai.shift='Y' AND\n" +
                "\tpegawai.flag='Y' AND\n" +
                "\tposisi.flag='Y' AND\n" +
                "\tpegawai.pin IS NOT NULL \n" +
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

}
