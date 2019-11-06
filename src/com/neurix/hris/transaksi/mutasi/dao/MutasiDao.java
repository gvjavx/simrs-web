
package com.neurix.hris.transaksi.mutasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.mutasi.model.ItMutasiEntity;
import com.neurix.hris.transaksi.mutasi.model.Mutasi;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.security.Timestamp;
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
public class MutasiDao extends GenericDao<ItMutasiEntity, String> {

    @Override
    protected Class<ItMutasiEntity> getEntityClass() {
        return ItMutasiEntity.class;
    }

    @Override
    public List<ItMutasiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItMutasiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("mutasi_id")!=null) {
                criteria.add(Restrictions.eq("mutasiId", (String) mapCriteria.get("mutasi_id")));
            }

            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }

            if (mapCriteria.get("tipe_mutasi")!=null) {
                criteria.add(Restrictions.eq("tipeMutasi", (String) mapCriteria.get("tipe_mutasi")));
            }

            if (mapCriteria.get("branch_lama_id")!=null) {
                criteria.add(Restrictions.eq("branchLamaId", (String) mapCriteria.get("branch_lama_id")));
            }

            if (mapCriteria.get("divisi_lama_id")!=null) {
                criteria.add(Restrictions.eq("divisiLamaId", (String) mapCriteria.get("divisi_lama_id")));
            }

            if (mapCriteria.get("position_lama_id")!=null) {
                criteria.add(Restrictions.eq("positionLamaId", (String) mapCriteria.get("position_lama_id")));
            }
            if (mapCriteria.get("position_baru_id")!=null) {
                criteria.add(Restrictions.eq("positionBaruId", (String) mapCriteria.get("position_baru_id")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("mutasiId"));

        List<ItMutasiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextMutasiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mutasi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "M"+sId;
    }

    public List<Mutasi> getKualifikasi(String qryWhere) throws HibernateException {
        List<Mutasi> listOfResult = new ArrayList<Mutasi>();

        String query = "select\n" +
                "\titPosisi.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\titPosisi.branch_id,\n" +
                "\titPosisi.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tposisi.kelompok_id,\n" +
                "\tbranch.branch_name\n," +
                "\tdivisi.department_name " +
                "from\n" +
                "\tit_hris_pegawai_position itPosisi\n" +
                "\tleft join im_position posisi on posisi.position_id = itPosisi.position_id\n" +
                "\tleft join im_branches branch on branch.branch_id = itPosisi.branch_id \n " +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = itPosisi.nip\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id" + qryWhere +
                "\n\torder by\n" +
                "\tbranch.branch_id";


        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        Mutasi mutasi;
        for(Object[] rows: results){
            mutasi = new Mutasi();
            mutasi.setNip(rows[0].toString());
            mutasi.setNama(rows[1].toString());

            mutasi.setPositionBaruName(rows[4].toString());
            mutasi.setBranchBaruName(rows[6].toString());
            mutasi.setDivisiBaruName(rows[7].toString());

            listOfResult.add(mutasi);
        }
        return listOfResult;
    }

    public List<ItMutasiEntity> getListMutasi(String term) throws HibernateException {
        List<ItMutasiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItMutasiEntity.class)
                .add(Restrictions.eq("nip",term))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ItMutasiEntity> getListMutasi(String nip, String tahun) throws HibernateException {
        List<ItMutasiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItMutasiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", nip))
                .add(Restrictions.eq("flag", "Y"))
                .setMaxResults(1)
                .list();
        return results;
    }

    public List<Mutasi> getDataReportMutasi(String mutasiId) throws HibernateException {
        List<Mutasi> listOfResult = new ArrayList<Mutasi>();

        String query = "SELECT\n" +
                "  mutasi.mutasi_id,\n" +
                "  personil.nip,\n" +
                "  personil.nama_pegawai,\n" +
                "  branch.branch_name AS branch_lama,\n" +
                "  divisi.department_name AS divisi_lama,\n" +
                "  position.position_name AS posisi_lama,\n" +
                "  branch_baru.branch_name AS branch_baru,\n" +
                "  divisi_baru.department_name AS divisi_baru,\n" +
                "  position_baru.position_name AS posisi_baru,\n" +
                "  to_char(mutasi.tanggal_efektif, 'dd-MM-yyyy') AS tanggal_efektif,\n" +
                "  mutasi.pjs,\n" +
                "  to_char(now(), 'dd-MM-yyyy') AS tanggal_sekarang\n" +
                "FROM it_hris_mutasi_jabatan mutasi\n" +
                "LEFT JOIN im_hris_pegawai personil\n" +
                "  ON personil.nip = mutasi.nip\n" +
                "LEFT JOIN im_branches branch\n" +
                "  ON branch.branch_id = mutasi.branch_lama_id\n" +
                "LEFT JOIN im_position position\n" +
                "  ON position.position_id = mutasi.position_lama_id\n" +
                "LEFT JOIN im_hris_department divisi\n" +
                "  ON divisi.department_id = mutasi.divisi_lama_id\n" +
                "\n" +
                "LEFT JOIN im_branches branch_baru\n" +
                "  ON branch_baru.branch_id = mutasi.branch_baru_id\n" +
                "LEFT JOIN im_position position_baru\n" +
                "  ON position_baru.position_id = mutasi.position_baru_id\n" +
                "LEFT JOIN im_hris_department divisi_baru\n" +
                "  ON divisi_baru.department_id = mutasi.divisi_baru_id\n" +
                "WHERE mutasi.mutasi_id = '"+mutasiId+"'";


        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        Mutasi mutasi;
        for(Object[] rows: results){
            mutasi = new Mutasi();
            mutasi.setMutasiId(rows[0].toString());
            mutasi.setNip(rows[1].toString());
            mutasi.setNama(rows[2].toString());
            if(rows[3] != null){
                mutasi.setBranchLamaName(rows[3].toString());
            }else{
                mutasi.setBranchLamaName("");
            }
            if(rows[4] != null){
                mutasi.setDivisiLamaName(rows[4].toString());
            }else{
                mutasi.setDivisiLamaName("");
            }
            mutasi.setPositionLamaName(rows[5].toString());

            if(rows[6] != null){
                mutasi.setBranchBaruName(rows[6].toString());
            }else{
                mutasi.setBranchBaruName("");
            }

            if(rows[7] != null){
                mutasi.setDivisiBaruName(rows[7].toString());
            }else{
                mutasi.setDivisiBaruName("");
            }

            if(rows[8] != null){
                mutasi.setPositionBaruName(rows[8].toString());
            }else{
                mutasi.setPositionBaruName("");
            }

            mutasi.setStTanggalEfektif(rows[9].toString());
            mutasi.setPjs(rows[10].toString());
            mutasi.setStTanggalSekarang(rows[11].toString());
            listOfResult.add(mutasi);
        }

        return listOfResult;
    }

    public List<Mutasi> getDataMutasi(String nip, String tahun) throws HibernateException {
        List<Mutasi> listOfResult = new ArrayList<Mutasi>();

        String query = "select\n" +
                "\tmutasi_id,\n" +
                "\tnip,\n" +
                "\tposition_lama_id,\n" +
                "\tdivisi_lama_id,\n" +
                "\tbranch_lama_id\n" +
                "from\n" +
                "\tit_hris_mutasi_jabatan\n" +
                "where\n" +
                "\tnip = '"+nip+"'\n" +
                "\tand date_part('year', tanggal_efektif) = '"+tahun+"'";


        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        Mutasi mutasi;
        for(Object[] rows: results){
            mutasi = new Mutasi();

            mutasi.setMutasiId(rows[0].toString());
            mutasi.setNip(rows[1].toString());
            mutasi.setPositionLamaId(rows[2].toString());
            mutasi.setDivisiLamaId(rows[3].toString());
            mutasi.setBranchLamaId(rows[4].toString());
            listOfResult.add(mutasi);
        }

        return listOfResult;
    }

}
