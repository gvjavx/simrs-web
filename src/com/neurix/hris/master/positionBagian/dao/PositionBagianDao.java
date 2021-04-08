package com.neurix.hris.master.positionBagian.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.department.model.Department;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianHistoryEntity;
import com.neurix.hris.master.positionBagian.model.PositionBagian;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
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
public class PositionBagianDao extends GenericDao<ImPositionBagianEntity, String> {

    @Override
    protected Class<ImPositionBagianEntity> getEntityClass() {
        return ImPositionBagianEntity.class;
    }

    @Override
    public List<ImPositionBagianEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImPositionBagianEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("bagian_id") != null) {
                criteria.add(Restrictions.eq("bagianId", (String) mapCriteria.get("bagian_id")));
            }
            if (mapCriteria.get("divisi_id") != null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }
            if (mapCriteria.get("bagian_name") != null) {
                criteria.add(Restrictions.ilike("bagianName", "%" + (String) mapCriteria.get("bagian_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by

        criteria.addOrder(Order.asc("kodering"));

        List<ImPositionBagianEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPositioBagianId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_position_bagian')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%02d", iter.next());
        return "PB2" + sId;
    }

    public String getNextKelompokPositionHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kelompok_position_history')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%02d", iter.next());
        return "HK" + sId;
    }

    public List<ImPositionBagianEntity> getListPositionBagian(String term) throws HibernateException {

        List<ImPositionBagianEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPositionBagianEntity.class)
                .add(Restrictions.ilike("bagianName", term))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImPositionBagianEntity> getListPositionBagianKodering(String term) throws HibernateException {

        List<ImPositionBagianEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPositionBagianEntity.class)
                .add(Restrictions.ilike("kodering", term))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImPositionBagianEntity> getPositionBagianName(String term) throws HibernateException {

        List<ImPositionBagianEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPositionBagianEntity.class)
                .add(Restrictions.ilike("bagianId", term))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImPositionBagianEntity> getComboPositionBagianKandir() throws HibernateException {

        List<ImPositionBagianEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPositionBagianEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("branchId", "KD01"))
                .list();

        return results;
    }

    public List<ImPositionBagianEntity> getComboPositionBagian() throws HibernateException {
        List<ImPositionBagianEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPositionBagianEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public void addAndSaveHistory(ImPositionBagianHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImPositionBagianEntity> getAllDataPositionBagian() {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tbagian_id, nama_bagian,\n" +
                "\tkodering\n" +
                "from\n" +
                "\tim_hris_position_bagian\n" +
                "\torder by kodering asc";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getDataBagianSmk(String periode) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tsmk.nip,\n" +
                "\tbagian.kodering\n" +
                "from\n" +
                "\tit_hris_smk_evaluasi_pegawai smk\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = smk.bagian_id\n" +
                "where\n" +
                "\tsmk.flag = 'Y'\n" +
                "\tand periode = '" + periode + "'\n" +
                "order by \n" +
                "\tbagian.kodering";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setNip((String) row[2]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getDataBagianSmkV2(String periode, String branchId) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tsmk.nip,\n" +
                "\tsmk.position_id,\n" +
                "\tsmk.divisi_id,\n" +
                "\tposisi.kelompok_id,\n" +
                "\tdivisi.department_name\n" +
                "from\n" +
                "\tit_hris_smk_evaluasi_pegawai smk\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = smk.bagian_id\n" +
                "\tleft join im_position posisi on posisi.position_id = smk.position_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = smk.divisi_id\n" +
                "where\n" +
                "\tsmk.flag = 'Y'\n" +
                "\tand periode = '" + periode + "'\n" +
                "\tand smk.branch_id = '" + branchId + "'\n" +
                //"\tand posisi.kelompok_id not in ('KL01','KL02','KL03', 'KL04', 'KL05')\n" +
                "order by\n" +
                "\tsmk.divisi_id,\n" +
                "\tbagian.bagian_id,\n" +
                "\tposisi.kelompok_id\n";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setNip((String) row[2]);
            result.setPositionId((String) row[3]);
            result.setDivisiId((String) row[4]);
            result.setKelompokId((String) row[5]);
            result.setDivisiName((String) row[6]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getAllDataPositionBagian(String txtWhere) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tbagian_id, nama_bagian,\n" +
                "\tkodering\n" +
                "from\n" +
                "\tim_hris_position_bagian\n" +
                "\torder by kodering asc\n" +
                txtWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getPosisiPerBagian(String bagianId, String unitId, String statusPegawai) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tposisiPegawai.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposisiPegawai.branch_id\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = posisiPegawai.nip\n" +
                "where\n" +
                "\tposisi.bagian_id = '" + bagianId + "'\n" +
                "\tand posisiPegawai.branch_id = '" + unitId + "'\n" +
                "\tand pegawai.status_pegawai = '" + statusPegawai + "'\n" +
                "\t--and pegawai.flag ='Y'\n" +
                "\tand posisiPegawai.flag ='Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setNip((String) row[2]);
            result.setNamaPegawai((String) row[3]);
            result.setBranchId((String) row[4]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getPosisiPerBagian(String bagianId, String unitId, String statusPegawai, String bulan, String tahun) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tpayroll.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tpayroll.branch_id\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tposisi.bagian_id = '" + bagianId + "'\n" +
                "\tand pegawai.status_pegawai = '" + statusPegawai + "'\n" +
                "\tand pegawai.flag ='Y'\n" +
                "\t\n" +
                "\tand payroll.bulan = '" + bulan + "' \n" +
                "\tand payroll.tahun ='" + tahun + "' \n" +
                "\tand payroll.branch_id = '" + unitId + "' \n" +
                "\tand payroll.flag_payroll = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setNip((String) row[2]);
            result.setNamaPegawai((String) row[3]);
            result.setBranchId((String) row[4]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getPosisiPerBagianThr(String bagianId, String unitId, String statusPegawai, String bulan, String tahun) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tpayroll.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tpayroll.branch_id\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tposisi.bagian_id = '" + bagianId + "'\n" +
                "\tand pegawai.status_pegawai = '" + statusPegawai + "'\n" +
                "\tand pegawai.flag ='Y'\n" +
                "\t\n" +
                "\tand payroll.bulan = '" + bulan + "' \n" +
                "\tand payroll.tahun ='" + tahun + "' \n" +
                "\tand payroll.branch_id = '" + unitId + "' \n" +
                "\tand payroll.flag_thr = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setNip((String) row[2]);
            result.setNamaPegawai((String) row[3]);
            result.setBranchId((String) row[4]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    // posisi bagian untuk master update golongan
    public List<ImPositionBagianEntity> getPosisiPerBagianUpdateGolongan(String id, String bagianId) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tposisi.position_name,\n" +
                "\thistory.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\thistory.branch_id,\n" +
                "\tposisi.kodering\n" +
                "from\n" +
                "\timt_hris_history_smk_golongan history\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = history.nip\n" +
                "\tleft join im_position posisi on posisi.position_id = history.position_id \n" +
                "where\n" +
                "\thistory.update_golongan_id = '" + id + "'\n" +
                "\tand posisi.bagian_id = '" + bagianId + "'\n" +
                "order by\n" +
                "\tposisi.kodering";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setNip((String) row[2]);
            result.setNamaPegawai((String) row[3]);
            result.setBranchId((String) row[4]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getPosisiPerBagianJasprod(String bagianId, String unitId, String statusPegawai, String bulan, String tahun) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tpayroll.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tpayroll.branch_id\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_smk_evaluasi_pegawai smk on smk.nip = payroll.nip\n" +
                "where\n" +
                "\tsmk.bagian_id = '" + bagianId + "'\n" +
                "\tand smk.status_pegawai = '" + statusPegawai + "'\n" +
                "\tand payroll.bulan = '" + bulan + "' \n" +
                "\tand payroll.tahun ='" + tahun + "' \n" +
                "\tand payroll.branch_id = '" + unitId + "' \n" +
                "\tand payroll.flag_jasprod = 'Y'\n" +
                "\tand smk.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setNip((String) row[2]);
            result.setNamaPegawai((String) row[3]);
            result.setBranchId((String) row[4]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getPosisiPerBagianInsentif(String bagianId, String unitId, String statusPegawai, String bulan, String tahun) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tpayroll.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tpayroll.branch_id\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tposisi.bagian_id = '" + bagianId + "'\n" +
                "\tand pegawai.status_pegawai = '" + statusPegawai + "'\n" +
                "\tand payroll.bulan = '" + bulan + "' \n" +
                "\tand payroll.tahun ='" + tahun + "' \n" +
                "\tand payroll.branch_id = '" + unitId + "' \n" +
                "\tand payroll.flag_insentif = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setNip((String) row[2]);
            result.setNamaPegawai((String) row[3]);
            result.setBranchId((String) row[4]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getPosisiPerBagianPendidikan(String bagianId, String unitId, String statusPegawai, String bulan, String tahun) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tpayroll.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tpayroll.branch_id\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tposisi.bagian_id = '" + bagianId + "'\n" +
                "\tand pegawai.status_pegawai = '" + statusPegawai + "'\n" +
                "\tand pegawai.flag ='Y'\n" +
                "\t\n" +
                "\tand payroll.bulan = '" + bulan + "' \n" +
                "\tand payroll.tahun ='" + tahun + "' \n" +
                "\tand payroll.branch_id = '" + unitId + "' \n" +
                "\tand payroll.flag_pendidikan = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();

            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setNip((String) row[2]);
            result.setNamaPegawai((String) row[3]);
            result.setBranchId((String) row[4]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPositionBagianEntity> getListOfBagian(String branchId) {
        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "FROM\n" +
                "( select bagian.nama_bagian \n" +
                "\tfrom \n" +
                "\tim_position posisi \n" +
                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.position_id = posisi.position_id  \n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id \n" +
                "\tWHERE posisiPegawai.branch_id ='" + branchId + "'  AND bagian.bagian_id IS NOT NULL GROUP BY bagian.nama_bagian ) hasilBagian\n" +
                "\tLEFT JOIN im_hris_position_bagian bagian ON bagian.nama_bagian = hasilBagian.nama_bagian\n";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();
            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public String getNextPositionBagianHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_position_bagian_history')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return "PBS" + sId;
    }

    public List<ImPositionBagianEntity> getDataPosisiBagian(String divisiId) {

        String bagian = "";


//        if(!divisiId.equalsIgnoreCase("")){
//            bagian = " and department_id = '"+divisiId+"' ";
//        }

        List<ImPositionBagianEntity> listOfResult = new ArrayList<ImPositionBagianEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                " \n" +
                "                bagian_id, \n" +
                "                nama_bagian, \n" +
                "                kodering\n" +
                "                from \n" +
                "                im_hris_position_bagian \n" +
                "                where \n" +
                "                bagian_id is not null and divisi_id = '" + divisiId + "'  \n" +
                "                order by \n" +
                "                \tkodering";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPositionBagianEntity result = new ImPositionBagianEntity();
            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<PositionBagian> getDataDevisiId(PositionBagian bean) {
        List<PositionBagian> positionBagianList = new ArrayList<>();
        String departementId = "%";
        if (bean.getDivisiId() != null && !"".equalsIgnoreCase(bean.getDivisiId())) {
            departementId = bean.getDivisiId();
        }

        String sql = "SELECT \n" +
                "im_hris_position_bagian.bagian_id ,\n" +//0
                "im_hris_position_bagian.nama_bagian,\n" +//1
                "im_hris_department.department_id,  \n" +//2
                "im_hris_department.department_name,\n" +//3
                "im_hris_position_bagian.kodering,\n" +
                "im_hris_department.kodering\n" +
                "FROM \n" +
                "im_hris_department \n" +
                "INNER JOIN im_hris_position_bagian ON im_hris_department.department_id = im_hris_position_bagian.divisi_id \n" +
                "where\n" +
                "im_hris_department.department_id LIKE :id \n" +
                "ORDER BY\n" +
//                "im_hris_department.department_id,im_hris_position_bagian.bagian_id";
                "im_hris_department.kodering,im_hris_position_bagian.kodering";

        List<Object[]> datatree = new ArrayList<>();
        datatree = this.sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter("id", departementId).list();
        if (datatree.size() > 0) {
            for (Object[] obj : datatree) {
                PositionBagian pb = new PositionBagian();
                pb.setBagianId(obj[0] != null ? obj[0].toString() : "");
                pb.setBagianName(obj[1] != null ? obj[1].toString() : "");
                pb.setDivisiId(obj[2] != null ? obj[2].toString() : "");
                pb.setDivisiName(obj[3] != null ? obj[3].toString() : "");
                positionBagianList.add(pb);
            }
        }


        return positionBagianList;
    }

    public List<Department> getHeadDepartent(PositionBagian positionBagian) {
        List<Department> positionBagianList = new ArrayList<>();

        String bagianId = "%";
        String bagianName = "%";
        String divisiId = "%";
        String flag = "%";

        if (positionBagian.getBagianId() != null && !"".equalsIgnoreCase(positionBagian.getBagianId())) {
            bagianId = positionBagian.getBagianId();
        }
        if (positionBagian.getBagianName() != null && !"".equalsIgnoreCase(positionBagian.getBagianName())) {
            bagianName = "%" + positionBagian.getBagianName() + "%";
        }
        if (positionBagian.getDivisiId() != null && !"".equalsIgnoreCase(positionBagian.getDivisiId())) {
            divisiId = positionBagian.getDivisiId();
        }
        if (positionBagian.getFlag() != null && !"".equalsIgnoreCase(positionBagian.getFlag())) {
            flag = positionBagian.getFlag();
        }



        String sql = "SELECT \n" +
                "b.department_id,  \n" +
                "a.department_name,\n" +
                "a.created_date,\n" +
                "a.created_who,\n" +
                "a.last_update,\n" +
                "a.last_update_who,\n" +
                "a.kodering\n" +
                "FROM im_hris_department a\n" +
                "INNER JOIN (\n" +
                "\tSELECT\n" +
                "\ta.department_id,  \n" +
                "\ta.department_name\n" +
                "\tFROM im_hris_department a \n" +
                "\tINNER JOIN im_hris_position_bagian b ON a.department_id = b.divisi_id\n" +
                "\tWHERE a.department_id LIKE :divisiId \n" +
                "\tAND b.bagian_id LIKE :bagianId \n" +
                "\tAND b.nama_bagian ILIKE :bagianName \n" +
                "\tAND a.flag LIKE :flag \n" +
                "\tGROUP BY a.department_id\n" +
                "\tORDER BY a.department_name ASC\t\n" +
                ") b ON a.department_id = b.department_id\n" +
                "ORDER BY a.kodering ASC";

        List<Object[]> datatree = new ArrayList<>();
        datatree = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setParameter("bagianId", bagianId)
                .setParameter("bagianName", bagianName)
                .setParameter("divisiId", divisiId)
                .setParameter("flag", flag)
                .list();

        if (datatree.size() > 0) {
            for (Object[] obj : datatree) {
                Department department = new Department();
                department.setDepartmentId(obj[0] != null ? obj[0].toString() : "");
                department.setDepartmentName(obj[1] != null ? obj[1].toString() : "");
                department.setCreatedDate(obj[2] == null ? null : (Timestamp) obj[2]);
                department.setStCreatedDate(obj[2] != null ? obj[2].toString() : "");
                department.setCreatedWho(obj[3] != null ?  obj[3].toString() : "");
                department.setLastUpdate(obj[4] == null ?  null : (Timestamp) obj[4]);
                department.setStLastUpdate(obj[4] != null ? obj[4].toString() : "");
                department.setLastUpdateWho(obj[5] != null ? obj[5].toString() : "");
                department.setKodering(obj[6] != null ? obj[6].toString() : "");

                positionBagianList.add(department);
            }
        }
        return positionBagianList;
    }



    public List<ImPositionBagianEntity> getListPositionBagianByDivisi(String id) throws HibernateException {

        List<ImPositionBagianEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPositionBagianEntity.class)
                .add(Restrictions.ilike("divisiId", id))
                .list();

        return results;
    }

    public PositionBagian getPositionBagianById(String id){

        String SQL = "SELECT bagian_id, nama_bagian, kodering FROM im_hris_position_bagian WHERE bagian_id = '"+id+"' \n";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (results.size() > 0){
            Object[] obj = results.get(0);
            PositionBagian positionBagian = new PositionBagian();
            positionBagian.setBagianId(obj[0].toString());
            positionBagian.setBagianName(obj[1].toString());
            positionBagian.setKodering(obj[2] == null ? "" : obj[2].toString());
            return positionBagian;
        }

        return null;
    }

}
