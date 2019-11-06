
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.transaksi.smk.model.ItSmkAspekActivityMonthlyEntity;
import com.neurix.hris.transaksi.smk.model.ItSmkEvaluasiPegawaiAspekEntity;
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
public class SmkEvaluasiPegawaiAspekDao extends GenericDao<ItSmkEvaluasiPegawaiAspekEntity, String> {

    @Override
    protected Class<ItSmkEvaluasiPegawaiAspekEntity> getEntityClass() {
        return ItSmkEvaluasiPegawaiAspekEntity.class;
    }

    @Override
    public List<ItSmkEvaluasiPegawaiAspekEntity> getByCriteria(Map mapCriteria) {
       return null;
    }

    // Generate surrogate id from postgre
    public String getNextSmkEvaluasiPegawaiAspekId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_evaluasi_pegawai_aspek')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "PA" + sId;
    }

    public List<ItSmkEvaluasiPegawaiAspekEntity> getListNip(String smkId, String tipeAspek) throws HibernateException {
        List<ItSmkEvaluasiPegawaiAspekEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEvaluasiPegawaiAspekEntity.class)
                .add(Restrictions.eq("evaluasiPegawaiId", smkId))
                .add(Restrictions.eq("tipeAspekId", tipeAspek))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ItSmkEvaluasiPegawaiAspekEntity> getDataAspek(String id){
        List<ItSmkEvaluasiPegawaiAspekEntity> listOfResult = new ArrayList<ItSmkEvaluasiPegawaiAspekEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tevaluasi_pegawai_aspek_id,\n" +
                "\tpoint_prestasi,\n" +
                "\ttipe_aspek_id\n" +
                "from \n" +
                "\tIt_hris_smk_evaluasi_pegawai_aspek\n" +
                "where\n" +
                "\tevaluasi_pegawai_id = '"+id+"' and flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEvaluasiPegawaiAspekEntity result  = new ItSmkEvaluasiPegawaiAspekEntity();
            result.setEvaluasiPegawaiId((String) row[0]);
            result.setPointPrestasi(Double.valueOf(row[1].toString()));
            result.setTipeAspekId((String) row[2]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

}
