
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.smk.model.ItSmkEvaluasiPegawaiAspekDetailEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
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
public class SmkEvaluasiPegawaiAspekDetailDao extends GenericDao<ItSmkEvaluasiPegawaiAspekDetailEntity, String> {

    @Override
    protected Class<ItSmkEvaluasiPegawaiAspekDetailEntity> getEntityClass() {
        return ItSmkEvaluasiPegawaiAspekDetailEntity.class;
    }

    @Override
    public List<ItSmkEvaluasiPegawaiAspekDetailEntity> getByCriteria(Map mapCriteria) {
       return null;
    }

    // Generate surrogate id from postgre
    public String getNextSmkEvaluasiPegawaiAspekDetail(String tahun) throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_evaluasi_pegawai_aspek_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "EPAD" + tahun + sId;
    }

    public void updateRatarata(String id){

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

    public void updateNilaiPrestasi(String id, double nilai){

        String query = "UPDATE it_hris_smk_evaluasi_pegawai_aspek_detail\n" +
                "SET nilai_prestasi = '"+nilai+"'\n" +
                "\n" +
                "WHERE evaluasi_pegawai_aspek_detail_id = '"+id+"' ";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();

    }

    public void updateNilaiAfterApprove(String smkId, String aspekDetail, BigDecimal nilaiPrestasi){

        String query = "update \n" +
                "\tIt_hris_smk_evaluasi_pegawai_aspek\n" +
                "set\n" +
                "\tpoint_prestasi = \n" +
                "\t(select\n" +
                "\t\tsum(detail.nilai_prestasi)\n" +
                "\tfrom\n" +
                "\t\tit_hris_smk_evaluasi_pegawai_aspek aspek\n" +
                "\t\tleft join it_hris_smk_evaluasi_pegawai_aspek_detail detail on detail.evaluasi_pegawai_aspek_id = aspek.evaluasi_pegawai_aspek_id\n" +
                "\twhere\n" +
                "\t\taspek.evaluasi_pegawai_id = '"+smkId+"' \n" +
                "\t\tand aspek.tipe_aspek_id = 'A' \n" +
                "\t\tand aspek.flag = 'Y'" +
                "\tand evaluasi_pegawai_aspek_detail_id != '"+aspekDetail+"') + " +nilaiPrestasi +"\n\n" +
                "where\n" +
                "\tevaluasi_pegawai_id = '"+smkId+"' \n" +
                "\tand tipe_aspek_id = 'A' \n" +
                "\tand flag = 'Y'";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();

    }

    public List<ItSmkEvaluasiPegawaiAspekDetailEntity> getListAspekId(String evaluasiPegawaiAspekId) throws HibernateException {
        List<ItSmkEvaluasiPegawaiAspekDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEvaluasiPegawaiAspekDetailEntity.class)
                .add(Restrictions.eq("evaluasiPegawaiAspekId", evaluasiPegawaiAspekId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

}
