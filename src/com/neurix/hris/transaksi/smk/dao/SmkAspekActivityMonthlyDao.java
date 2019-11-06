
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.smk.model.ItSmkAspekActivityMonthlyEntity;
import com.neurix.hris.transaksi.smk.model.ItSmkEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkAspekActivityMonthlyDao extends GenericDao<ItSmkAspekActivityMonthlyEntity, String> {

    @Override
    protected Class<ItSmkAspekActivityMonthlyEntity> getEntityClass() {
        return ItSmkAspekActivityMonthlyEntity.class;
    }

    @Override
    public List<ItSmkAspekActivityMonthlyEntity> getByCriteria(Map mapCriteria) {
       return null;
    }

    // Generate surrogate id from postgre
    public String getNextSmkAspekActivityMonthlyId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_aspek_activity_monthly')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "AM" + sId;
    }

    public List<ItSmkAspekActivityMonthlyEntity> getDataDetail(String id){
        List<ItSmkAspekActivityMonthlyEntity> listOfResult = new ArrayList<ItSmkAspekActivityMonthlyEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\ta.rata_rata,\n" +
                "\tb.*\n" +
                "from\n" +
                "\tit_hris_smk_evaluasi_pegawai_aspek_activity a\n" +
                "left join it_hris_smk_evaluasi_pegawai_aspek_activity_monthly b on b.evaluasi_pegawai_aspek_activity_id = a.evaluasi_pegawai_aspek_activity_id\n" +
                "\n" +
                "where\n" +
                "\ta.evaluasi_pegawai_detail_id = '"+id+"' and\n" +
                "\ta.flag = 'Y' and\n" +
                "\tb.flag = 'Y' order by b.bulan";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkAspekActivityMonthlyEntity result  = new ItSmkAspekActivityMonthlyEntity();
            result.setRataRata(Double.valueOf(row[0].toString()));
            result.setAspekActivityMonthly((String) row[1]);
            result.setEvaluasiPegawaiAspekId((String) row[2]);
            result.setBulan(Integer.valueOf(row[3].toString()));
            result.setNilai(Double.valueOf(row[4].toString()));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkAspekActivityMonthlyEntity> getDataView(String activity, int bulan) throws HibernateException {
        List<ItSmkAspekActivityMonthlyEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkAspekActivityMonthlyEntity.class)
                .add(Restrictions.eq("evaluasiPegawaiAspekId", activity))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ItSmkAspekActivityMonthlyEntity> getData(String activity) throws HibernateException {
        List<ItSmkAspekActivityMonthlyEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkAspekActivityMonthlyEntity.class)
                .add(Restrictions.eq("evaluasiPegawaiAspekId", activity))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ItSmkEntity> getDataDetailCheckList(String id){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "\n" +
                "select \n" +
                "\tdetail.evaluasi_pegawai_aspek_detail_id,\n" +
                "\tdetail.uraian,\n" +
                "\tcase when checklist.check_list_name is null or check_list_name = '' then '-' else check_list_name end,\n" +
                "\tactivity.evaluasi_pegawai_aspek_activity_id,\n" +
                "\tsum(case when monthly.bulan = 1 then monthly.nilai end) as jan,\n" +
                "\tsum(case when monthly.bulan = 2 then monthly.nilai end) as feb,\n" +
                "\tsum(case when monthly.bulan = 3 then monthly.nilai end) as mar,\n" +
                "\tsum(case when monthly.bulan = 4 then monthly.nilai end) as apr,\n" +
                "\tsum(case when monthly.bulan = 5 then monthly.nilai end) as mei,\n" +
                "\tsum(case when monthly.bulan = 6 then monthly.nilai end) as jun,\n" +
                "\tsum(case when monthly.bulan = 7 then monthly.nilai end) as jul,\n" +
                "\tsum(case when monthly.bulan = 8 then monthly.nilai end) as ags,\n" +
                "\tsum(case when monthly.bulan = 9 then monthly.nilai end) as sep,\n" +
                "\tsum(case when monthly.bulan = 10 then monthly.nilai end) as okt,\n" +
                "\tsum(case when monthly.bulan = 11 then monthly.nilai end) as nov,\n" +
                "\tsum(case when monthly.bulan = 12 then monthly.nilai end) as des,\n" +
                "\tavg(monthly.nilai) as rata_rata\n" +
                "\t\n" +
                "from\n" +
                "\tit_hris_smk_evaluasi_pegawai_aspek_detail detail\n" +
                "left join it_hris_smk_evaluasi_pegawai_aspek_activity activity on activity.evaluasi_pegawai_detail_id = detail.evaluasi_pegawai_aspek_detail_id\n" +
                "left join it_hris_smk_evaluasi_pegawai_aspek_activity_monthly monthly on monthly.evaluasi_pegawai_aspek_activity_id = activity.evaluasi_pegawai_aspek_activity_id\n" +
                "left join im_hris_smk_check_list checklist on checklist.check_list_id = detail.sub_aspek_id\n" +
                "where\n" +
                "\tevaluasi_pegawai_aspek_detail_id = '"+id+"'and activity.flag = 'Y' and monthly.flag = 'Y' \n" +
                "group by detail.evaluasi_pegawai_aspek_detail_id, checklist.check_list_name,activity.evaluasi_pegawai_aspek_activity_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();

            result.setEvaluasiPegawaiAspekDetailId((String) row[0]);
            result.setIndikatorKinerja((String) row[1]);
            result.setCheckList((String) row[2]);
            result.setActivityId((String) row[3]);
            result.setJan(Double.valueOf(row[4].toString()));
            result.setFeb(Double.valueOf(row[5].toString()));
            result.setMar(Double.valueOf(row[6].toString()));
            result.setApr(Double.valueOf(row[7].toString()));
            result.setMei(Double.valueOf(row[8].toString()));
            result.setJun(Double.valueOf(row[9].toString()));
            result.setJul(Double.valueOf(row[10].toString()));
            result.setAgs(Double.valueOf(row[11].toString()));
            result.setSep(Double.valueOf(row[12].toString()));
            result.setOkt(Double.valueOf(row[13].toString()));
            result.setNov(Double.valueOf(row[14].toString()));
            result.setDes(Double.valueOf(row[15].toString()));
            result.setRataRata(Double.valueOf(row[16].toString()));


            listOfResult.add(result);
        }
        return listOfResult;
    }


    public void getDataRata(String id){
        List<ItSmkAspekActivityMonthlyEntity> listOfResult = new ArrayList<ItSmkAspekActivityMonthlyEntity>();

        String query = "UPDATE it_hris_smk_evaluasi_pegawai_aspek_activity\n" +
                "SET rata_rata = (SELECT\n" +
                "  AVG(nilai) AS rata_rata\n" +
                "FROM it_hris_smk_evaluasi_pegawai_aspek_activity_monthly\n" +
                "WHERE evaluasi_pegawai_aspek_activity_id = '"+id+"'\n" +
                "GROUP BY evaluasi_pegawai_aspek_activity_id)\n" +
                "\n" +
                "WHERE evaluasi_pegawai_aspek_activity_id = '"+id+"'";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();

    }

    public void updateNilai(String idDetail, String id){
        String query = "update\n" +
                "\tit_hris_smk_evaluasi_pegawai_aspek_detail\n" +
                "set \n" +
                "\tnilai = (\n" +
                "\t\tSELECT\n" +
                "\t\t\tAVG(nilai) AS rata_rata\n" +
                "\t\tFROM \n" +
                "\t\t\tit_hris_smk_evaluasi_pegawai_aspek_activity_monthly\n" +
                "\t\tWHERE \n" +
                "\t\t\tevaluasi_pegawai_aspek_activity_id = '"+id+"'\n" +
                "\t\tGROUP BY \n" +
                "\t\t\tevaluasi_pegawai_aspek_activity_id )\n" +
                "where\n" +
                "\tevaluasi_pegawai_aspek_detail_id = '"+idDetail+"'";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();
    }

    public void updateNilaiPrestasi(String idDetail, String id, double bobot){

        String query = "UPDATE it_hris_smk_evaluasi_pegawai_aspek_detail\n" +
                "set \n" +
                "\tnilai_prestasi = ((\n" +
                "\t\tSELECT\n" +
                "\t\t\tAVG(nilai) AS rata_rata\n" +
                "\t\tFROM \n" +
                "\t\t\tit_hris_smk_evaluasi_pegawai_aspek_activity_monthly\n" +
                "\t\tWHERE \n" +
                "\t\t\tevaluasi_pegawai_aspek_activity_id = '"+id+"'\n" +
                "\t\tGROUP BY \n" +
                "\t\t\tevaluasi_pegawai_aspek_activity_id ) * "+bobot+") / 100 \n" +
                "WHERE evaluasi_pegawai_aspek_detail_id = '"+idDetail+"' ";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();

    }

    public void updateData(String activityId, int bulan, double nilai){
        String query = "UPDATE it_hris_smk_evaluasi_pegawai_aspek_activity_monthly\n" +
                "SET nilai = '"+nilai+"'\n" +
                "WHERE evaluasi_pegawai_aspek_activity_id = '"+activityId+"'\n" +
                "AND flag = 'Y'\n" +
                "AND bulan = '"+bulan+"'";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();
    }

    public void updateNilaiPoint(String id){
        String query = "update\n" +
                "\tit_hris_smk_evaluasi_pegawai\n" +
                "set\n" +
                "\tgrand_total_nilai_prestasi = (\n" +
                "\t\tselect \n" +
                "\t\t\tsum(point_prestasi)\n" +
                "\t\tfrom \n" +
                "\t\t\tIt_hris_smk_evaluasi_pegawai_aspek\n" +
                "\t\twhere\n" +
                "\t\t\tevaluasi_pegawai_id = '"+id+"' and flag = 'Y' )\n" +
                "where\n" +
                "\tevaluasi_pegawai_id = '"+id+"'";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();
    }



}
