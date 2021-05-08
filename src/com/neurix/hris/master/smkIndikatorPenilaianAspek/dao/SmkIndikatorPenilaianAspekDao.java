
package com.neurix.hris.master.smkIndikatorPenilaianAspek.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.ImSmkIndikatorPenilaianAspekEntity;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
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
public class SmkIndikatorPenilaianAspekDao extends GenericDao<ImSmkIndikatorPenilaianAspekEntity, String> {

    @Override
    protected Class<ImSmkIndikatorPenilaianAspekEntity> getEntityClass() {
        return ImSmkIndikatorPenilaianAspekEntity.class;
    }

    @Override
    public List<ImSmkIndikatorPenilaianAspekEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkIndikatorPenilaianAspekEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("indikatorPenilaianAspekId")!=null) {
                criteria.add(Restrictions.eq("indikatorPenilaianAspekId", (String) mapCriteria.get("indikatorPenilaianAspekId")));
            }
            if (mapCriteria.get("kategoriAspekId")!=null) {
                criteria.add(Restrictions.eq("kategoriAspekId", (String)mapCriteria.get("kategoriAspekId")));
            }
            if (mapCriteria.get("indikatorName")!=null) {
                criteria.add(Restrictions.eq("indikatorName", (String)mapCriteria.get("indikatorName")));
            }
            if (mapCriteria.get("nilai")!=null) {
                criteria.add(Restrictions.eq("nilai", (Integer)mapCriteria.get("nilai")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("indikatorPenilaianAspekId"));

        List<ImSmkIndikatorPenilaianAspekEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String deleteIndikator(String kode) throws HibernateException {
        String query = "update im_hris_smk_indikator_penilaian_aspek set flag = 'N' where kategori_aspek_id = '"+kode+"'";
        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();
        return null;
    }

    public String getNextSmkIndikatorId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_indikator_penilaian_aspek')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return "IPA"+sId;
    }

    public List<ImSmkIndikatorPenilaianAspekEntity> getDataAspek(String branchId, String tipeAspek){
        List<ImSmkIndikatorPenilaianAspekEntity> listOfResult = new ArrayList<ImSmkIndikatorPenilaianAspekEntity>();


        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "indikator.indikator_penilaian_aspek_id,\n" +
                "\taspek.branch_id,\n" +
                "\tindikator.indikator_name,\n" +
                "\tindikator.bobot\n" +
                "from \n" +
                "\tim_hris_smk_kategori_aspek aspek\n" +
                "left join im_hris_smk_indikator_penilaian_aspek indikator on indikator.kategori_aspek_id = aspek.kategori_aspek_id\n" +
                "where \n" +
                "\taspek.flag = 'Y' and aspek.tipe_aspek_id = '"+tipeAspek+"' and\n" +
                "\taspek.branch_id = '"+branchId+"'\n";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImSmkIndikatorPenilaianAspekEntity result  = new ImSmkIndikatorPenilaianAspekEntity();
            result.setIndikatorPenilaianAspekId((String) row[0]);
            result.setBranchId((String) row[1]);
            result.setIndikatorName((String) row[2]);
            result.setBobot(Double.valueOf(row[3].toString()));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImSmkIndikatorPenilaianAspekEntity> getDataIndikator(String branchId, String positionId, String tipeAspek){
        List<ImSmkIndikatorPenilaianAspekEntity> listOfResult = new ArrayList<ImSmkIndikatorPenilaianAspekEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\t\n" +
                "\timt.jabatan_smk_id,\n" +
                "\timt.position_id,\n" +
                "\timt.divisi_id,\n" +
                "\timt.branch_id,\n" +
                "\timt.bobot,\n" +
                "\timt.tipe_aspek_id,\n" +
                "\taspek.kategori_aspek_id,\n" +
                "\tindikator.indikator_name,\n" +
                "\tindikator.bobot as bobot_indikator\n" +
                "from \n" +
                "\timt_hris_smk_jabatan imt\n" +
                "left join im_hris_smk_kategori_aspek aspek on aspek.tipe_aspek_id = imt.tipe_aspek_id and aspek.branch_id = imt.branch_id \n" +
                "left join im_hris_smk_indikator_penilaian_aspek indikator on indikator.kategori_aspek_id = aspek.kategori_aspek_id\n" +
                "where imt.tipe_aspek_id = '"+tipeAspek+"' and imt.position_id = '"+positionId+"' and imt.branch_id = '"+branchId+"' and imt.flag = 'Y' and aspek.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImSmkIndikatorPenilaianAspekEntity result  = new ImSmkIndikatorPenilaianAspekEntity();
            result.setBranchId((String) row[3]);
            result.setKategoriAspekId((String) row[6]);
            result.setIndikatorName((String) row[7]);
            result.setBobot(Double.valueOf(row[8].toString()));
            listOfResult.add(result);
        }
        return listOfResult;
    }

}
