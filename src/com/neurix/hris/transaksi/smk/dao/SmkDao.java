
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.ImSmkIndikatorPenilaianAspekEntity;
import com.neurix.hris.transaksi.smk.model.ItSmkEntity;
import com.neurix.hris.transaksi.smk.model.ItSmkEvaluasiPegawaiAspekDetailEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
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
public class SmkDao extends GenericDao<ItSmkEntity, String> {

    @Override
    protected Class<ItSmkEntity> getEntityClass() {
        return ItSmkEntity.class;
    }

    @Override
    public List<ItSmkEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSmkEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }

            if (mapCriteria.get("periode")!=null) {
                criteria.add(Restrictions.eq("periode", (String) mapCriteria.get("periode")));
            }

            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.ilike("positionId", (String)mapCriteria.get("position_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", "Y"));

        // Order by
        criteria.addOrder(Order.asc("evaluasiPegawaiId"));
        List<ItSmkEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSmkId(String per) throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "EP" + per + sId;
    }

    public String getNextSmkHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ItSmkEntity> getListSmk(String term) throws HibernateException {
        List<ItSmkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEntity.class)
                .add(Restrictions.eq("smkId",term))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ItSmkEntity> getListSmk() throws HibernateException {
        List<ItSmkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ItSmkEntity> getListSmkByNipPeriode(String nip, String periode) throws HibernateException {
        List<ItSmkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.eq("periode",periode))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ItSmkEntity> getListSmkByNipPeriode(String nip, String periode, String branchId) throws HibernateException {
        List<ItSmkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.eq("periode",periode))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    //get data berdasarkan branch, posisi dan periode
    public List<ItSmkEntity> getList(String nip, String branch, String posisi, String periode) throws HibernateException {
        List<ItSmkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branch))
                .add(Restrictions.eq("positionId", posisi))
                .add(Restrictions.eq("periode", periode))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ItSmkEntity> getList(String nip, String branch, String periode) throws HibernateException {
        List<ItSmkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branch))
                .add(Restrictions.eq("periode", periode))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    //list by Posisi_id
    public List<ItSmkEntity> getListByPositionId(String positionId, String branch, String periode) throws HibernateException {
        List<ItSmkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEntity.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("branchId", branch))
                .add(Restrictions.eq("periode", periode))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ItSmkEntity> getListNilai(String nip, String branch, String periode) throws HibernateException {
        List<ItSmkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSmkEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branch))
                .add(Restrictions.eq("periode", periode))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.isNotNull("nilaiPrestasi"))
                .list();

        return results;
    }

    //digunakan untuk mengambil data dari tabel it hris smk evaluasi pegawai khusus Aspek A
    public List<ItSmkEvaluasiPegawaiAspekDetailEntity> aspekDetail(String periode, String nip, String uraian ){
        List<ItSmkEvaluasiPegawaiAspekDetailEntity> listOfResult = new ArrayList<ItSmkEvaluasiPegawaiAspekDetailEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "  pegawai.evaluasi_pegawai_id,\n" +
                "  pegawai.nip,\n" +
                "  pegawai.pegawai_name,\n" +
                "  pegawai.position_id,\n" +
                "  pegawai.branch_id,\n" +
                "  pegawai.periode,\n" +
                "  aspek.evaluasi_pegawai_aspek_id,\n" +
                "  aspekDetail.evaluasi_pegawai_aspek_detail_id,\n" +
                "  aspekDetail.uraian,\n" +
                "  aspekDetail.nilai,\n" +
                "  aspekDetail.nilai_prestasi,\n" +
                "  jabatanDetail.bobot\n" +
                "FROM it_hris_smk_evaluasi_pegawai pegawai\n" +
                "LEFT JOIN it_hris_smk_evaluasi_pegawai_aspek aspek\n" +
                "  ON aspek.evaluasi_pegawai_id = pegawai.evaluasi_pegawai_id\n" +
                "LEFT JOIN it_hris_smk_evaluasi_pegawai_aspek_detail aspekDetail\n" +
                "  ON aspekDetail.evaluasi_pegawai_aspek_id = aspek.evaluasi_pegawai_aspek_id\n" +
                "LEFT JOIN imt_hris_smk_jabatan jabatan\n" +
                "  ON jabatan.branch_id = pegawai.branch_id\n" +
                "  AND jabatan.position_id = pegawai.position_id\n" +
                "  AND jabatan.tipe_aspek_id = aspek.tipe_aspek_id\n" +
                "LEFT JOIN imt_hris_smk_jabatan_detail jabatanDetail\n" +
                "  ON jabatanDetail.jabatan_smk_id = jabatan.jabatan_smk_id\n" +
                "  AND jabatanDetail.indikator_kinerja = aspekDetail.uraian\n" +
                "WHERE pegawai.periode = '"+periode+"'\n" +
                "AND pegawai.nip = '"+nip+"'\n" +
                "AND aspek.tipe_aspek_id = 'A'\n" +
                "AND aspekDetail.uraian = '"+uraian+"'\n" +
                "AND pegawai.flag = 'Y'\n" +
                "AND aspek.flag = 'Y'\n" +
                "AND aspekDetail.flag = 'Y'\n" +
                "and jabatan.flag = 'Y'\n" +
                "and jabatanDetail.bobot is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEvaluasiPegawaiAspekDetailEntity result  = new ItSmkEvaluasiPegawaiAspekDetailEntity();
            result.setEvaluasiPegawaiId((String) row[0]);
            result.setEvaluasiPegawaiAspekDetailId((String) row[7]);
            result.setUraian((String) row[8]);
            result.setNilai(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setNilaiPrestasi(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setBobot(BigDecimal.valueOf(Double.valueOf(row[11].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEntity> getDataAspekA(String periode, String nip, String tipeAspek ){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        /*String query = "SELECT\n" +
                "  pegawai.nip,\n" +
                "  pegawai.pegawai_name,\n" +
                "  pegawai.position_id,\n" +
                "  pegawai.divisi_id,\n" +
                "  pegawai.branch_id,\n" +
                "  pegawai.golongan_id,\n" +
                "  pegawai.periode,\n" +
                "  jabatan.jabatan_smk_id,\n" +
                "  jabatan.bobot AS jumlah_bobot,\n" +
                "  detail.indikator_kinerja,\n" +
                "  detail.sub_aspek_a,\n" +
                "  detail.bobot,\n" +
                "  aspek.point_prestasi,\n" +
                "  b.*,\n" +
                "  aspek.evaluasi_pegawai_aspek_id " +
                "FROM it_hris_smk_evaluasi_pegawai pegawai\n" +
                "LEFT JOIN imt_hris_smk_jabatan jabatan\n" +
                "  ON jabatan.branch_id = pegawai.branch_id\n" +
                "  AND jabatan.position_id = pegawai.position_id\n" +
                "  AND jabatan.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "LEFT JOIN imt_hris_smk_jabatan_detail detail\n" +
                "  ON detail.jabatan_smk_id = jabatan.jabatan_smk_id\n" +
                "LEFT JOIN it_hris_smk_evaluasi_pegawai_aspek aspek\n" +
                "  ON aspek.evaluasi_pegawai_id = pegawai.evaluasi_pegawai_id\n" +
                "  AND aspek.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "LEFT JOIN (SELECT\n" +
                "  aspek_detail.evaluasi_pegawai_aspek_detail_id,\n" +
                "  aspek_detail.target,\n" +
                "  aspek_detail.realisasi,\n" +
                "  aspek_detail.nilai,\n" +
                "  aspek_detail.uraian,\n" +
                "  aspek_detail.sub_aspek_id,\n" +
                "  aspek_detail.nilai_prestasi\n" +
                "FROM it_hris_smk_evaluasi_pegawai pegawai\n" +
                "LEFT JOIN it_hris_smk_evaluasi_pegawai_aspek kategori\n" +
                "  ON kategori.evaluasi_pegawai_id = pegawai.evaluasi_pegawai_id\n" +
                "  AND kategori.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "LEFT JOIN it_hris_smk_evaluasi_pegawai_aspek_detail aspek_detail\n" +
                "  ON aspek_detail.evaluasi_pegawai_aspek_id = kategori.evaluasi_pegawai_aspek_id\n" +
                "WHERE pegawai.periode = '"+periode+"'\n" +
                "AND pegawai.nip = '"+nip+"'\n" +
                "AND pegawai.flag = 'Y'\n" +
                "AND aspek_detail.flag = 'Y'\n" +
                "AND kategori.flag = 'Y'\n" +
                "ORDER BY aspek_detail.uraian) AS b\n" +
                "  ON b.uraian = detail.indikator_kinerja\n" +
                "WHERE pegawai.periode = '"+periode+"'\n" +
                "AND pegawai.nip = '"+nip+"'\n" +
                "AND pegawai.flag = 'Y'\n" +
                "AND jabatan.flag = 'Y'\n" +
                "AND detail.flag = 'Y'\n" +
                "AND aspek.flag = 'Y'\n" +
                "ORDER BY b.evaluasi_pegawai_aspek_detail_id";*/
        String query = "SELECT\n" +
                "  pegawai.nip,\n" +
                "  pegawai.pegawai_name,\n" +
                "  pegawai.position_id,\n" +
                "  pegawai.divisi_id,\n" +
                "  pegawai.branch_id,\n" +
                "  pegawai.golongan_id,\n" +
                "  pegawai.periode,\n" +
                "  jabatan.jabatan_smk_id,\n" +
                "  jabatan.bobot AS jumlah_bobot,\n" +
                "  aspek.point_prestasi,\n" +
                "  aspek_detail.evaluasi_pegawai_aspek_detail_id,\n" +
                "  aspek_detail.target,\n" +
                "  aspek_detail.realisasi,\n" +
                "  aspek_detail.nilai,\n" +
                "  aspek_detail.uraian,\n" +
                "  aspek_detail.sub_aspek_id,\n" +
                "  aspek_detail.nilai_prestasi,\n" +
                "  aspek_detail.bobot,\n" +
                "  aspek.evaluasi_pegawai_aspek_id FROM it_hris_smk_evaluasi_pegawai pegawai\n" +
                "LEFT JOIN imt_hris_smk_jabatan jabatan\n" +
                "  ON jabatan.branch_id = pegawai.branch_id\n" +
                "  AND jabatan.position_id = pegawai.position_id\n" +
                "  AND jabatan.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "LEFT JOIN it_hris_smk_evaluasi_pegawai_aspek aspek\n" +
                "  ON aspek.evaluasi_pegawai_id = pegawai.evaluasi_pegawai_id\n" +
                "  AND aspek.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "LEFT JOIN it_hris_smk_evaluasi_pegawai_aspek_detail aspek_detail\n" +
                "  ON aspek_detail.evaluasi_pegawai_aspek_id = aspek.evaluasi_pegawai_aspek_id\n" +
                "WHERE pegawai.periode = '"+periode+"'\n" +
                "AND pegawai.nip = '"+nip+"'\n" +
                "AND pegawai.flag = 'Y'\n" +
                "AND jabatan.flag = 'Y'\n" +
                "AND aspek.flag = 'Y'\n" +
                "AND aspek_detail.flag = 'Y'\n" +
                "ORDER BY aspek_detail.evaluasi_pegawai_aspek_detail_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();
            result.setNip((String) row[0]);
            result.setPegawaiName((String) row[1]);
            result.setPositionId((String) row[2]);
            result.setDivisiId((String) row[3]);
            result.setBranchId((String) row[4]);
            result.setGolonganId((String) row[5]);
            result.setPeriode((String) row[6]);
            result.setJabatanSmkId((String) row[7]);
            result.setJumlahBobot(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setPointPrestasi(Double.valueOf(row[9].toString()));
            result.setEvaluasiPegawaiAspekDetailId((String) row[10]);
            result.setTarget((String) row[11]);
            result.setRealisasi((String) row[12]);
            result.setNilai(Double.valueOf(row[13].toString()));
            result.setIndikatorKinerja((String) row[14]);
            result.setNilaiPrestasiItem(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setBobot(Double.valueOf(row[17].toString()));
            result.setEvaluasiPegawaiAspekId((String) row[18]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEntity> getDataAspek(String periode, String nip , String tipeAspek){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT DISTINCT\n" +
                "  pegawai.nip,\n" +
                "  pegawai.pegawai_name,\n" +
                "  pegawai.position_id,\n" +
                "  pegawai.divisi_id,\n" +
                "  pegawai.branch_id,\n" +
                "  pegawai.golongan_id,\n" +
                "  pegawai.periode,\n" +
                "  kategori.tipe_aspek_id,\n" +
                "  aspek.indikator_name,\n" +
                "  aspek.bobot,\n" +
                "  b.*\n" +
                "\n" +
                "FROM it_hris_smk_evaluasi_pegawai pegawai\n" +
                "LEFT JOIN im_hris_smk_kategori_aspek kategori\n" +
                "  ON kategori.branch_id = pegawai.branch_id\n" +
                "  AND kategori.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "LEFT JOIN im_hris_smk_indikator_penilaian_aspek aspek\n" +
                "  ON aspek.kategori_aspek_id = kategori.kategori_aspek_id\n" +
                "LEFT JOIN (SELECT DISTINCT\n" +
                "  aspek_detail.evaluasi_pegawai_aspek_detail_id,\n" +
                "  aspek_detail.uraian,\n" +
                "  aspek_detail.nilai,\n" +
                "  aspek_detail.nilai_prestasi,\n" +
                "  pegawai_aspek.evaluasi_pegawai_aspek_id\n" +
                "FROM it_hris_smk_evaluasi_pegawai pegawai\n" +
                "LEFT JOIN im_hris_smk_kategori_aspek kategori\n" +
                "  ON kategori.branch_id = pegawai.branch_id\n" +
                "  AND kategori.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "LEFT JOIN im_hris_smk_indikator_penilaian_aspek aspek\n" +
                "  ON aspek.kategori_aspek_id = kategori.kategori_aspek_id\n" +
                "LEFT JOIN it_hris_smk_evaluasi_pegawai_aspek pegawai_aspek\n" +
                "  ON pegawai_aspek.evaluasi_pegawai_id = pegawai.evaluasi_pegawai_id\n" +
                "  AND pegawai_aspek.tipe_aspek_id = '"+tipeAspek+"'\n" +
                "LEFT JOIN it_hris_smk_evaluasi_pegawai_aspek_detail aspek_detail\n" +
                "  ON aspek_detail.evaluasi_pegawai_aspek_id = pegawai_aspek.evaluasi_pegawai_aspek_id\n" +
                "WHERE pegawai.periode = '"+periode+"'\n" +
                "AND pegawai.nip = '"+nip+"'\n" +
                "AND pegawai.flag = 'Y'\n" +
                "AND kategori.flag = 'Y'\n" +
                "AND aspek.flag = 'Y'\n" +
                "AND pegawai_aspek.flag = 'Y'\n" +
                "AND aspek_detail.flag = 'Y'\n" +
                "ORDER BY aspek_detail.uraian) AS b\n" +
                "  ON b.uraian = aspek.indikator_name\n" +
                "WHERE pegawai.periode = '"+periode+"'\n" +
                "AND pegawai.nip = '"+nip+"'\n" +
                "AND pegawai.flag = 'Y'\n" +
                "AND kategori.flag = 'Y'\n" +
                "AND aspek.flag = 'Y'\n" +
                "ORDER BY aspek.indikator_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();
            result.setNip((String) row[0]);
            result.setPegawaiName((String) row[1]);
            result.setPositionId((String) row[2]);
            result.setDivisiId((String) row[3]);
            result.setBranchId((String) row[4]);
            result.setGolonganId((String) row[5]);
            result.setPeriode((String) row[6]);
            result.setIndikatorKinerja((String) row[8]);
            result.setBobot(Double.valueOf(row[9].toString()));
            result.setEvaluasiPegawaiAspekDetailId((String) row[10]);
            result.setNilai(Double.valueOf(row[12].toString()));
            result.setNilaiPrestasiItem(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setEvaluasiPegawaiAspekId(row[14].toString());

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEntity> getDataAspekDetail(String id){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpegawai.evaluasi_pegawai_id,\n" +
                "\tpegawai.nip,\n" +
                "\tpegawai.periode,\n" +
                "\taspek_detail.evaluasi_pegawai_aspek_detail_id,\n" +
                "\taspek_detail.uraian,\n" +
                "\taspek_detail.nilai,\n" +
                "\tactivity.rata_rata,\n" +
                "\tcase when pegawai.closed is null then 'B' else pegawai.closed end\n" +
                "from \n" +
                "\tit_hris_smk_evaluasi_pegawai pegawai\n" +
                "\tleft join it_hris_smk_evaluasi_pegawai_aspek aspek on aspek.evaluasi_pegawai_id = pegawai.evaluasi_pegawai_id\n" +
                "\tleft join it_hris_smk_evaluasi_pegawai_aspek_detail aspek_detail on aspek_detail.evaluasi_pegawai_aspek_id = aspek.evaluasi_pegawai_aspek_id\n" +
                "\tleft join it_hris_smk_evaluasi_pegawai_aspek_activity activity on activity.evaluasi_pegawai_detail_id = aspek_detail.evaluasi_pegawai_aspek_detail_id\n" +
                "where \n" +
                "\taspek_detail.evaluasi_pegawai_aspek_detail_id = '"+id+"' and\n" +
                "\tpegawai.flag = 'Y' and\n" +
                "\taspek.flag = 'Y' and\n" +
                "\taspek_detail.flag = 'Y' and\n" +
                "\tactivity.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();
            result.setEvaluasiPegawaiId((String) row[0]);
            result.setNip((String) row[1]);
            result.setPeriode((String) row[2]);
            result.setEvaluasiPegawaiAspekDetailId((String) row[3]);
            result.setIndikatorKinerja((String) row[4]);
            result.setNilai(Double.valueOf(row[5].toString()));
            result.setClosed((String)row[7]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEntity> getSearchData(String strukturJabatan, String nip, String periode){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tstruktur.nip,\n" +
                "\tstruktur.branch_name,\n" +
                "\tstruktur.department_name,\n" +
                "\tsmk.*\n" +
                "from\n" +
                "\tstruktur_jabatan struktur\n" +
                "\tinner join it_hris_smk_evaluasi_pegawai smk on smk.nip = struktur.nip\n" +
                "where \n" +
                "\tstruktur.parent_id = '"+strukturJabatan+"' or smk.nip = '"+nip+"'\n" +
                "\tand smk.periode = '"+periode+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();
            result.setNip((String) row[0]);
            result.setBranchName((String) row[1]);
            result.setDivisiName((String) row[2]);
            result.setEvaluasiPegawaiAspekId((String) row[3]);
            result.setPegawaiName((String) row[5]);
            result.setPeriode((String) row[14]);
            result.setClosed((String) row[25]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEntity> getDataAspekADetail(String id){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpegawai.evaluasi_pegawai_id,\n" +
                "\tpegawai.nip,\n" +
                "\tpegawai.periode,\n" +
                "\taspek_detail.evaluasi_pegawai_aspek_detail_id,\n" +
                "\taspek_detail.uraian,\n" +
                "\taspek_detail.sub_aspek_id,\n" +
                "\taspek_detail.target,\n" +
                "\taspek_detail.realisasi,\n" +
                "\taspek_detail.nilai,\n" +
                "\tcase when pegawai.closed is null then 'B' else pegawai.closed end\n" +
                "from \n" +
                "\tit_hris_smk_evaluasi_pegawai pegawai\n" +
                "\tleft join it_hris_smk_evaluasi_pegawai_aspek aspek on aspek.evaluasi_pegawai_id = pegawai.evaluasi_pegawai_id\n" +
                "\tleft join it_hris_smk_evaluasi_pegawai_aspek_detail aspek_detail on aspek_detail.evaluasi_pegawai_aspek_id = aspek.evaluasi_pegawai_aspek_id\n" +
                "where \n" +
                "\taspek_detail.evaluasi_pegawai_aspek_detail_id = '"+id+"' and\n" +
                "\tpegawai.flag = 'Y' and\n" +
                "\taspek.flag = 'Y' and\n" +
                "\taspek_detail.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();
            result.setEvaluasiPegawaiId((String) row[0]);
            result.setNip((String) row[1]);
            result.setPeriode((String) row[2]);
            result.setEvaluasiPegawaiAspekDetailId((String) row[3]);
            result.setIndikatorKinerja((String) row[4]);
            result.setSubAspekA((String) row[5]);
            result.setTarget((String) row[6]);
            result.setRealisasi((String) row[7]);
            result.setNilai(Double.valueOf(row[8].toString()));
            result.setClosed((String)(row[9]));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEntity> getDetailReportSmk(String periode, String unit){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tsmk.nip,\n" +
                "\tpegawai.nama_pegawai, \n" +
                "\tposisi.position_name, \n" +
                "\tgolongan.golongan_name,\n" +
                "\tpegawai.point,\n" +
                "\tpegawai.poin_lebih,\n" +
                "\tpegawai.golongan_id\n" +
                "\t\n" +
                "from\n" +
                "\tit_hris_smk_evaluasi_pegawai smk\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = smk.bagian_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = smk.nip\n" +
                "\tleft join im_position posisi on posisi.position_id = smk.position_id\n" +
                "\tleft join im_hris_golongan golongan on golongan.golongan_id = pegawai.golongan_id\n" +
                "where\n" +
                "\tsmk.flag = 'Y'\n" +
                "\tand smk.periode = '"+periode+"'\n" +
                "\tand smk.branch_id = '"+unit+"'\n" +
                "\tand nama_pegawai is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();
            result.setNip((String) row[0]);
            result.setPegawaiName((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setPoin((Integer) row[4]);
            result.setPoinLebih((Integer) row[5]);
            result.setGolonganId((String) row[6]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEntity> getEditMasaKerjaGolongan(String nip, String golonganId){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tnip,\n" +
                "\ttahun,\n" +
                "\tpoint,\n" +
                "\tpoin_lebih\n" +
                "from\n" +
                "\timt_hris_history_smk_golongan\n" +
                "where\n" +
                "\tnip = '"+nip+"'\n" +
                "\tand golongan_id = '"+golonganId+"'\n" +
                "order by \n" +
                "\ttahun\n" +
                "\tasc\n" +
                "limit 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();
            result.setNip((String) row[0]);
            result.setPeriode((String) row[1]);
            result.setPoin((Integer) row[2]);
            result.setPoinLebih((Integer) row[3]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEntity> getJumlahPoinMkg(String nip, String golonganId){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tnip,\n" +
                "\ttahun,\n" +
                "\tpoint,\n" +
                "\tpoin_lebih,\n" +
                "\tcase \n" +
                "\t\twhen nilai_huruf is null then '-' \n" +
                "\t\twhen nilai_huruf = '' then '-' \n" +
                "\telse \n" +
                "\t\tnilai_huruf \n" +
                "\tend\n" +
                "from\n" +
                "\timt_hris_history_smk_golongan\n" +
                "where\n" +
                "\tnip = '"+nip+"'\n" +
                "\tand golongan_id = '"+golonganId+"'\n" +
                "order by \n" +
                "\ttahun";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();
            result.setNip((String) row[0]);
            result.setPeriode((String) row[1]);
            result.setPoin((Integer) row[2]);
            result.setPoinLebih((Integer) row[3]);
            result.setNilaiPrestasi((String) row[4]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSmkEntity> getDetailTahunSmk(String nip, String branchId, String txtWhere){
        List<ItSmkEntity> listOfResult = new ArrayList<ItSmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\timtHistory.nip,\n" +
                "\timtHistory.tahun,\n" +
                "\timtHistory.nilai_huruf,\n" +
                "\tgolongan.golongan_name,\n" +
                "\timtHistory.point,\n" +
                "\timtHistory.poin_lebih,\n" +
                "\timtHistory.nilai_angka\n" +
                "\t\n" +
                "from\n" +
                "\timt_hris_history_smk_golongan imtHistory\n" +
                "\tleft join im_hris_golongan golongan on golongan.golongan_id = imtHistory.golongan_id\n" +
                "where\n" +
                "\timtHistory.flag = 'Y'\n" +
                "\tand imtHistory.branch_id = '"+branchId+"'\n" +
                "\tand imtHistory.nip = '"+nip+"'\n" +
                "\n" + txtWhere + "\n" +
                "order by\n" +
                "\timtHistory.tahun";


        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItSmkEntity result  = new ItSmkEntity();
            result.setNip((String) row[0]);
            result.setPeriode((String) row[1]);
            result.setNilaiPrestasi((String) row[2]);
            result.setGolonganName((String) row[3]);
            if(row[4] != null){
                result.setPoin((Integer) row[4]);
            }
            if(row[5] != null){
                result.setPoinLebih((Integer) row[5]);
            }
            if(row[6] != null){
                result.setPointPrestasi(Double.parseDouble(row[6].toString()));
            }

            listOfResult.add(result);
        }
        return listOfResult;
    }
}
