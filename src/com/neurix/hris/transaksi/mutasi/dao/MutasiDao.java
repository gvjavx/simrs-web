
package com.neurix.hris.transaksi.mutasi.dao;

import com.neurix.authorization.position.model.Position;
import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.mutasi.model.ItMutasiEntity;
import com.neurix.hris.transaksi.mutasi.model.Mutasi;
import com.neurix.hris.transaksi.personilPosition.model.HistoryJabatanPegawai;
import com.neurix.hris.transaksi.personilPosition.model.ImtHrisHistoryJabatanPegawaiEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
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
            if (mapCriteria.get("status")!=null) {
                criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
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
            if (mapCriteria.get("tanggal_efektif")!=null) {
                criteria.add(Restrictions.eq("tanggalEfektif", mapCriteria.get("tanggal_efektif")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
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
                "  to_char(now(), 'dd-MM-yyyy') AS tanggal_sekarang,\n" +
                "  mutasi.branch_lama_id,\n" +
                "  mutasi.level_baru_name,\n" +
                "  mutasi.level_baru\n," +
                "  mutasi.no_sk\n" +
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
            if(rows[5] != null){
                mutasi.setPositionLamaName(rows[5].toString());
            }else{
                mutasi.setPositionLamaName("");
            }
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
            mutasi.setPjs(rows[10] == null ? "N" : rows[10].toString());
            mutasi.setStTanggalSekarang(rows[11].toString());
            if(rows[12] != null){
                mutasi.setBranchLamaId(rows[12].toString());
            }else{
                mutasi.setBranchLamaId("");
            }
            mutasi.setLevelBaruName(rows[13].toString());
            mutasi.setLevelBaru(rows[14].toString());
            mutasi.setNoSk(rows[15] == null ? "" : rows[15].toString());
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

    public String getTahun(String nip){
        String result="";
        String stTmp;
        int temp=0;
        String query ="select tahun from imt_hris_history_jabatan_pegawai where nip ='"+nip+"'";
        List<String> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (String rows: results){
            stTmp = rows;
            if ( Integer.parseInt(stTmp) >= temp){
                temp = Integer.parseInt(stTmp);
            }
        }
        stTmp = String.valueOf(temp);
        result = "01-01-"+stTmp;

        return result;
    }

    public String getGolonganId(String nip){
        String listOfResult;
        String query ="select golongan_id from im_hris_pegawai where nip ='"+nip+"'";

        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            listOfResult = results.toString();
        }else {
            listOfResult=null;
        }
        return listOfResult;

    }
    public String getTanggalAktif(String nip){
        String listOfResult;
        String query ="select tanggal_aktif from it_hris_pegawai_position where nip ='"+nip+"'";

        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            listOfResult = dateFormat.format(results);
        }else {
            listOfResult=null;
        }
        return listOfResult;

    }

    public String getHistoryJabatanIdLama(String nip, String positionId){
        String listOfResult = "";

//        String query = "select history_jabatan_pegawai_id from imt_hris_history_jabatan_pegawai\n" +
//                "where imt_hris_history_jabatan_pegawai.nip = '"+nip+"'\n" +
//                "order by imt_hris_history_jabatan_pegawai.created_date DESC\n" +
//                "limit 1";
        //update query reza 02-04-2020
//        String query = "select history_jabatan_pegawai_id from imt_hris_history_jabatan_pegawai\n" +
//                "                where imt_hris_history_jabatan_pegawai.nip = '"+nip+"' AND jabatan_flag = 'Y'\n" +
//                "                order by imt_hris_history_jabatan_pegawai.created_date DESC \n" +
//                "                limit 1";

        // Sigit 2020-01-12, Penambahan pecarian berdasarkan Posisi
        String SQL = "SELECT history_jabatan_pegawai_id FROM imt_hris_history_jabatan_pegawai \n" +
                "WHERE \n" +
                "nip = :nip \n" +
                "AND position_id = position \n" +
                "AND jabatan_flag = 'Y'\n" +
                "ORDER BY created_date DESC \n" +
                "LIMIT 1";

        Object results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("position", positionId)
                .uniqueResult();

        if (results!=null){
            listOfResult = results.toString();
        }else {
            listOfResult = null;
        }
        return listOfResult;
    }

    public List<ItMutasiEntity> checkDataDelete(String statusMutasi) throws HibernateException {

        List<ItMutasiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItMutasiEntity.class)
                .add(Restrictions.eq("statusMutasi", statusMutasi))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<Position> getListOtherPosition(String positionId, String nip) throws HibernateException{

        String SQL = "SELECT pp.position_id, p.position_name FROM it_hris_pegawai_position pp\n" +
                "INNER JOIN im_position p ON p.position_id = pp.position_Id\n" +
                "WHERE pp.flag = 'Y'\n" +
                "AND pp.position_id != :position \n" +
                "AND pp.nip = :nip ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("position", positionId)
                .setParameter("nip", nip)
                .list();

        List<Position> positions = new ArrayList<>();
        if (results.size() > 0){
            Position position;
            for (Object[] obj : results){
                position = new Position();
                position.setPositionId(obj[0].toString());
                position.setPositionName(obj[1].toString());
                positions.add(position);
            }
        }

        return positions;
    }

    public Boolean checkJenisPegawaiIsDefaultWithNip(String nip, String positionId){

        String SQL = "SELECT jp.jenis_pegawai_id, jp.jenis_pegawai_name  \n" +
                "FROM it_hris_pegawai_position pp\n" +
                "INNER JOIN im_position p ON p.position_id = pp.position_id\n" +
                "INNER JOIN im_hris_jenis_pegawai jp ON jp.jenis_pegawai_id = pp.jenis_pegawai\n" +
                "WHERE pp.flag = 'Y' \n" +
                "AND jp.flag_default = 'Y' \n" +
                "AND pp.nip = :nip \n" +
                "AND pp.position_id = :position";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("position", positionId)
                .list();

        if (results != null && results.size() > 0)
            return true;

        return false;
    }

    public Boolean checkPositionUtamaAktif(String nip, String jenisPegawai){

        String SQL = "SELECT jp.jenis_pegawai_id, jp.jenis_pegawai_name, pp.jenis_pegawai \n" +
                "FROM it_hris_pegawai_position pp\n" +
                "INNER JOIN im_position p ON p.position_id = pp.position_id\n" +
                "INNER JOIN im_hris_jenis_pegawai jp ON jp.jenis_pegawai_id = pp.jenis_pegawai\n" +
                "WHERE pp.flag = 'Y'\n" +
                "AND jp.flag_default = 'Y'\n" +
                "AND jp.jenis_pegawai_id = :jenispegawai \n" +
                "AND pp.nip = :nip";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("jenispegawai", jenisPegawai)
                .list();

        if (results != null && results.size() > 0)
            return true;

        return false;

    }

}
