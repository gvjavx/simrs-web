
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.smk.model.ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkEvaluasiPegawaiAspekActivityPeristiwaDao extends GenericDao<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity, String> {

    @Override
    protected Class<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> getEntityClass() {
        return ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.class;
    }

    @Override
    public List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> getByCriteria(Map mapCriteria) {
       return null;
    }

    // Generate surrogate id from postgre
    public String getNextSmkAspekActivityMonthlyPeristiwaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_evaluasi_aspek_activity_peristiwa')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "EAP" + sId;
    }

    public List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> getDataMonthly(String id){
        List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> listOfResult = new ArrayList<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\t* \n" +
                "from \n" +
                "\tit_hris_smk_evaluasi_pegawai_aspek_activity_peristiwa\n" +
                "where\n" +
                "\taspek_activity_monthly = '"+id+"'\n" +
                "\tand flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity result  = new ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity();
            result.setEvaluasiActivityPeristiwaId((String) row[0]);
            result.setAspekActivityMonthlyId((String) row[1]);
            result.setTanggalKejadian((java.sql.Date) row[2]);
            result.setKejadian((String) row[3]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> getData(String activity) throws HibernateException {
        List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.class)
                .add(Restrictions.eq("evaluasiActivityPeristiwaId", activity))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> getDataPeristiwa(String id){
        List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> listOfResult = new ArrayList<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tperistiwa.* \n" +
                "from \n" +
                "\tit_hris_smk_evaluasi_pegawai_aspek_activity_monthly activityMonthly\n" +
                "\tinner join it_hris_smk_evaluasi_pegawai_aspek_activity_peristiwa peristiwa on peristiwa.aspek_activity_monthly = activityMonthly.aspek_activity_monthly\n" +
                "and peristiwa.flag = 'Y' " +
                "where \n" +
                "\tactivityMonthly.evaluasi_pegawai_aspek_activity_id = '"+id+"'\n" +
                "\tand activityMonthly.flag = 'Y'" +
                " order by peristiwa.tanggal_kejadian";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity result  = new ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity();
            result.setEvaluasiActivityPeristiwaId((String) row[0]);
            result.setAspekActivityMonthlyId((String) row[1]);
            result.setTanggalKejadian((java.sql.Date) row[2]);
            result.setKejadian((String) row[3]);

            listOfResult.add(result);
        }
        return listOfResult;
    }
}
