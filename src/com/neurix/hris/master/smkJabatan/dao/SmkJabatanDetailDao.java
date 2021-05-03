
package com.neurix.hris.master.smkJabatan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkJabatan.model.ImtSmkJabatanDetailEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
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
public class SmkJabatanDetailDao extends GenericDao<ImtSmkJabatanDetailEntity, String> {

    @Override
    protected Class<ImtSmkJabatanDetailEntity> getEntityClass() {
        return ImtSmkJabatanDetailEntity.class;
    }

    @Override
    public List<ImtSmkJabatanDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtSmkJabatanDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jabatanSmkId")!=null) {
                criteria.add(Restrictions.eq("jabatanSmkId", (String) mapCriteria.get("jabatanSmkId")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("tipeAspekId")!=null) {
                criteria.add(Restrictions.eq("tipeAspekId", (String) mapCriteria.get("tipeAspekId")));
            }
            if (mapCriteria.get("positionId")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("positionId")));
            }
            if (mapCriteria.get("divisiId")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisiId")));
            }
            if (mapCriteria.get("branchId")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branchId")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.asc("jabatanSmkId"));

        List<ImtSmkJabatanDetailEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSmkJabatanDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_jabatan_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "JSD"+sId;
    }

    public List<ImtSmkJabatanDetailEntity> getListData(String smkJabatanid) throws HibernateException {
        List<ImtSmkJabatanDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtSmkJabatanDetailEntity.class)
                .add(Restrictions.eq("jabatanSmkId", smkJabatanid))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("jabatanSmkDetailId"))
                .list();
        return results;
    }


    // Generate surrogate id from postgre
    public String deleteIndikator(String kode) throws HibernateException {
        String query = "update imt_hris_smk_jabatan_detail set flag = 'N', action = 'D' where jabatan_smk_id = '"+kode+"'";
        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();
        return null;
    }

    public List<ImtSmkJabatanDetailEntity> getIndikatorA(String jabatanSmkid){
        List<ImtSmkJabatanDetailEntity> listOfResult = new ArrayList<ImtSmkJabatanDetailEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\t* \n" +
                "from \n" +
                "\timt_hris_smk_jabatan_detail\n" +
                "where\n" +
                "\tjabatan_smk_id = 'SJ029' and flag = 'Y'\n";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImtSmkJabatanDetailEntity result  = new ImtSmkJabatanDetailEntity();
            result.setJabatanSmkDetailId((String) row[0]);
            result.setIndikatorKinerja((String) row[2]);
            result.setBobot(BigDecimal.valueOf(Integer.valueOf(row[3].toString())));
            result.setSubAspekA((String) row[4]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImtSmkJabatanDetailEntity> getIndikator(String positionId, String branchId, String tipeAspek){
        List<ImtSmkJabatanDetailEntity> listOfResult = new ArrayList<ImtSmkJabatanDetailEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tdetail.*\n" +
                "from \n" +
                "\timt_hris_smk_jabatan jabatan\n" +
                "\tleft join imt_hris_smk_jabatan_detail detail on detail.jabatan_smk_id = jabatan.jabatan_smk_id and detail.flag = 'Y'\n" +
                "where \n" +
                "\tjabatan.position_id = '"+positionId+"'\n" +
                "\tand jabatan.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "\tAND jabatan.branch_id = '"+branchId+"'\n" +
                "\tand jabatan.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImtSmkJabatanDetailEntity result  = new ImtSmkJabatanDetailEntity();
            result.setJabatanSmkDetailId((String) row[0]);
            result.setIndikatorKinerja((String) row[2]);
            result.setBobot(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setSubAspekA((String) row[4]);
            result.setSatuan((String) row[11]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImtSmkJabatanDetailEntity> getIndikatorHistory(String nip, String tipeAspek, String periode){
        List<ImtSmkJabatanDetailEntity> listOfResult = new ArrayList<ImtSmkJabatanDetailEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tsmk.nip,\n" +
                "\tsmk.pegawai_name,\n" +
                "\tsmk.periode,\n" +
                "\tsmk.evaluasi_pegawai_id,\n" +
                "\taspek.evaluasi_pegawai_aspek_id,\n" +
                "\taspekDetail.uraian,\n" +
                "\taspekDetail.bobot,\n" +
                "\taspekDetail.satuan\n" +
                "from\n" +
                "\tit_hris_smk_evaluasi_pegawai smk\n" +
                "\tleft join it_hris_smk_evaluasi_pegawai_aspek aspek on aspek.evaluasi_pegawai_id = smk.evaluasi_pegawai_id\n" +
                "\tleft join it_hris_smk_evaluasi_pegawai_aspek_detail aspekDetail on aspekDetail.evaluasi_pegawai_aspek_id = aspek.evaluasi_pegawai_aspek_id\n" +
                "where\n" +
                "\tsmk.nip = '"+nip+"'\n" +
                "\tand periode = '"+periode+"'\n" +
                "\tand aspek.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "\tand smk.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImtSmkJabatanDetailEntity result  = new ImtSmkJabatanDetailEntity();
            result.setJabatanSmkDetailId("");
            result.setIndikatorKinerja((String) row[5]);
            result.setBobot(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setSatuan((String) row[7]);
            result.setSubAspekA("");

            listOfResult.add(result);
        }
        return listOfResult;
    }

}
