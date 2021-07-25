package com.neurix.hris.transaksi.notifikasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiHistoryEntity;
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
public class NotifikasiDao extends GenericDao<ImNotifikasiEntity, String> {

    @Override
    protected Class<ImNotifikasiEntity> getEntityClass() {
        return ImNotifikasiEntity.class;
    }

    @Override
    public List<ImNotifikasiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImNotifikasiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("notifId")!=null) {
                criteria.add(Restrictions.eq("notifId", (String) mapCriteria.get("notifId")));
            }

            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.ilike("nip", "%" + (String)mapCriteria.get("nip") + "%"));
            }
            if (mapCriteria.get("tipeNotifId")!=null) {
                criteria.add(Restrictions.ilike("tipeNotifId", "%" + (String)mapCriteria.get("tipeNotifId") + "%"));
            }
//            if (mapCriteria.get("tipeNotifName")!=null) {
//                criteria.add(Restrictions.ilike("tipeNotifName", "%" + (String)mapCriteria.get("tipeNotifName") + "%"));
//            }
//            if (mapCriteria.get("note")!=null) {
//                criteria.add(Restrictions.ilike("note", "%" + (String)mapCriteria.get("note") + "%"));
//            }
            if (mapCriteria.get("read")!=null) {
                criteria.add(Restrictions.ilike("read", "%" + (String)mapCriteria.get("read") + "%"));
            }
            if (mapCriteria.get("fromPerson")!=null) {
                criteria.add(Restrictions.ilike("fromPerson", "%" + (String)mapCriteria.get("fromPerson") + "%"));
            }
            if (mapCriteria.get("noRequest")!=null) {
                criteria.add(Restrictions.ilike("noRequest", "%" + (String)mapCriteria.get("noRequest") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("createdDate"));

        List<ImNotifikasiEntity> results = criteria.list();

        return results;
    }
    public List<ImNotifikasiEntity> getByCriteriaApproval(Map mapCriteria) {
        String nip=(String)mapCriteria.get("nip");
        List<ImNotifikasiEntity> results= new ArrayList<>();
        List<Object[]> resultQuery= new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  DISTINCT data.*, \n" +
                "  case when data.tipe_notif_id = 'TN77' AND data.lembur is null then 'L' \n" +
                "    when data.tipe_notif_id = 'TN66' AND data.cuti is null then 'C' \n" +
                "    when data.tipe_notif_id = 'TN88' AND data.ijin_keluar is null then 'I' \n" +
                "    when data.tipe_notif_id = 'TN55' AND data.dispensasi is null then 'D' \n" +
                "    when data.tipe_notif_id = 'TI'   AND data.sppd is null then 'S' \n" +
                "    when data.tipe_notif_id = 'TN33' AND data.absensi is null then 'A' \n" +
                "    when data.tipe_notif_id = 'TN99' AND data.rekruitmen is null then 'R' \n" +
                "    when data.tipe_notif_id = 'TN23' AND data.training is null then 'T' \n" +
                "    when data.tipe_notif_id = 'TN23' AND data.trainingbos is null then 'B' \n" +
                "    when data.tipe_notif_id = 'TN44' AND data.indisipliner is null then 'P' \n" +
                "    when data.tipe_notif_id = 'TN01' AND data.pengajuanRk is null then 'PBRK' \n" +
                "    when data.tipe_notif_id = 'TN04' AND data.pengajuan is null then 'PB' \n" +
                "    when data.tipe_notif_id = 'TN70' AND data.refreshLembur is null then 'RL' \n" +
                "  end as hasil \n" +
                "from \n" +
                "  (\n" +
                "    select \n" +
                "      notif.*, \n" +
                "      lembur.approval_flag as lembur, \n" +
                "      cuti.approval_flag as cuti, \n" +
                "      ijin_keluar.approval_flag as ijin_keluar, \n" +
                "      dispensasi.approval_flag as dispensasi, \n" +
                "      sppd.approval_id as sppd, \n" +
                "      absensi.approval_flag as absensi, \n" +
                "      rekruitmen.approval_gm_flag as rekruitmen, \n" +
                "      training.approval_flag as training, \n" +
                "      training.approval_bos_flag as trainingBos, \n" +
                "      indisipliner.approval_flag as indisipliner, \n" +
                "      pengajuan.aproval_flag as pengajuan,\n" +
                "      pengajuanRk.aproval_flag as pengajuanRk,\n" +
                "      refreshLembur.flag_approve as refreshLembur\n" +
                "    from \n" +
                "      (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_notifikasi \n" +
                "        WHERE \n" +
                "          flag = 'Y' \n" +
                "          AND tipe_notif_id <> 'umum' \n" +
                "          AND tipe_notif_id <> 'SPPD'\n" +
                "      ) notif \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_lembur\n" +
                "      ) lembur on lembur.lembur_id = notif.no_request \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_cuti_pegawai\n" +
                "      ) cuti on cuti.cuti_pegawai_id = notif.no_request \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_ijin_keluar \n" +
                "        WHERE \n" +
                "          ijin_Id = 'IJ001'\n" +
                "      ) ijin_keluar on ijin_keluar.ijin_keluar_id = notif.no_request \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_ijin_keluar \n" +
                "        WHERE \n" +
                "          ijin_Id <> 'IJ001'\n" +
                "      ) dispensasi on dispensasi.ijin_keluar_id = notif.no_request \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_sppd_person\n" +
                "      ) sppd on sppd.sppd_id = notif.no_request \n" +
                "      AND sppd.person_id = notif.from_person \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_absensi_pegawai\n" +
                "      ) absensi on absensi.absensi_pegawai_id = notif.no_request \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_training_person\n" +
                "      ) training on training.training_id = notif.no_request \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_rekruitmen_pabrik_detail\n" +
                "      ) rekruitmen on rekruitmen.rekruitmen_pabrik_id = notif.no_request \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_hris_indisipliner\n" +
                "      ) indisipliner on indisipliner.indisipliner_id = notif.no_request \n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_akun_pengajuan_biaya\n" +
                "      ) pengajuan on pengajuan.pengajuan_biaya_id = notif.no_request\n" +
                "      left join (\n" +
                "        SELECT \n" +
                "          * \n" +
                "        FROM \n" +
                "          it_akun_pengajuan_biaya\n" +
                "      ) pengajuanRk on pengajuanRk.pengajuan_biaya_id = notif.no_request\n" +
                "      left join (\n" +
                "        SELECT\n" +
                "          *\n" +
                "        FROM\n" +
                "          it_hris_refresh_lembur\n" +
                "        LIMIT 1\n" +
                "      ) refreshLembur on refreshLembur.group_refresh_id = notif.no_request \n" +
                "  ) as data \n" +
                "WHERE \n" +
                "  nip = '"+nip+"' \n" +
                "ORDER BY \n" +
                "  created_date DESC\n\n";
        resultQuery = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();
        String notifId="";
        for (Object[] row : resultQuery) {
            ImNotifikasiEntity result  = new ImNotifikasiEntity();
            if (row[27]!=null){
                result.setNotifId(row[0].toString());
                result.setTipeNotifId(row[1].toString());
                result.setNip(row[2].toString());
                result.setTipeNotifName(row[3].toString());
                result.setNote(row[4].toString());
                result.setRead(row[5].toString());
                result.setFromPerson(row[6].toString());
                result.setNoRequest(row[7].toString());

                result.setCreatedWho(row[8].toString());
                result.setCreatedDate((Timestamp) row[9]);
                result.setLastUpdate((Timestamp) row[11]);
                result.setAction(row[12].toString());
                result.setFlag(row[13].toString());

                if (!notifId.equalsIgnoreCase(result.getNotifId())){
                    results.add(result);
                    notifId=result.getNotifId();
                }
            }
        }
        return results;
    }

    public List<ImNotifikasiEntity> getByCriteriaPemberitahuan(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImNotifikasiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("notif_id")!=null) {
                criteria.add(Restrictions.eq("notifId", (String) mapCriteria.get("notif_id")));
            }
            if (mapCriteria.get("notif_name")!=null) {
                criteria.add(Restrictions.ilike("notifName", "%" + (String)mapCriteria.get("notif_name") + "%"));
            }
            if (mapCriteria.get("nip")!=null) {
//                criteria.add(Restrictions.eq("nip", "%" + (String)mapCriteria.get("nip") + "%"));
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("read")!=null) {
                criteria.add(Restrictions.ilike("read", "%" + (String)mapCriteria.get("read") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.add(Restrictions.eq("tipeNotifId","umum"));

        // Order by
        criteria.addOrder(Order.desc("createdDate"));

        List<ImNotifikasiEntity> results = criteria.list();

        return results;
    }
    // Generate surrogate id from postgre
    public String getNextNotifikasiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_notifikasi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%015d", iter.next());

        return "TN"+sId;
    }

    public List<ImNotifikasiEntity> getListPersonal(String term) throws HibernateException {

        List<ImNotifikasiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImNotifikasiEntity.class)
                .add(Restrictions.ilike("notifName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("notifId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImNotifikasiHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }

    public List<Object[]> findByNipAndTypeNotif(String nip, String typeNotifId) throws HibernateException {
        String SQL = "SELECT \n" +
                "cuti_pegawai_id, \n" +
                "users.nip, \n" +
                "users.nama_pegawai, \n" +
                "cuti.tanggal_dari, \n" +
                "cuti.tanggal_selesai, \n" +
                "pposition.branch_id, \n" +
                "notif.notif_id\n" +
                "FROM\n" +
                "\t(SELECT * FROM it_hris_notifikasi WHERE nip = :nip AND flag = 'Y' AND tipe_notif_id = :typeNotifId) notif LEFT JOIN\n" +
                "\t(SELECT * FROM im_hris_pegawai) users ON notif.from_person = users.nip LEFT JOIN\n" +
                "\t(SELECT * FROM it_hris_cuti_pegawai) cuti ON notif.no_request = cuti.cuti_pegawai_id LEFT JOIN\n" +
                "\t(SELECT * FROM it_hris_pegawai_position) pposition ON users.nip = pposition.nip LEFT JOIN\n" +
                "\t(SELECT * FROM im_position) position ON pposition.position_id = position.position_id LEFT JOIN\n" +
                "\t(SELECT * FROM im_hris_department) depart ON position.department_id = depart.department_id\n" +
                "WHERE cuti.approval_id IS NULL";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("typeNotifId", typeNotifId)
                .list();

        return results;
    }

    public List<Object[]> findIjinByNipAndTypeNotif(String nip, String typeNotifId) throws HibernateException {
        String SQL = "SELECT \n" +
                "   ijin_keluar_id, \n" +
                "   users.nip, \n" +
                "   users.nama_pegawai, \n" +
                "   ijin.tanggal_awal, \n" +
                "   ijin.tanggal_akhir, \n" +
                "   branches.branch_name, \n" +
                "   notif.notif_id \n" +
                "FROM \n" +
                "\t(SELECT * FROM it_hris_notifikasi WHERE nip = :nip AND flag = 'Y' AND tipe_notif_id = :typeNotifId) notif LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_pegawai) users ON notif.from_person = users.nip LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_ijin_keluar) ijin ON notif.no_request = ijin.ijin_keluar_id LEFT JOIN\n" +
                "    (SELECT * FROM im_branches) branches ON ijin.unit_id = branches.branch_id\n" +
                "WHERE ijin.approval_id IS NULL";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("typeNotifId", typeNotifId)
                .list();

        return results;
    }

    public List<Object[]> findSppdByNipAndTypeNotif(String nip, String typeNotifId) throws HibernateException {
        String SQL = "SELECT \n" +
                "\tsppd.sppd_id, \n" +
                "\tusers.nip, \n" +
                "    users.nama_pegawai, \n" +
                "    sppd.tanggal_sppd_berangkat, \n" +
                "    sppd.tanggal_sppd_kembali, \n" +
                "    branches.branch_name, \n" +
                "    notif.notif_id,\n" +
                "    depart.department_name,\n" +
                "    position.position_name,\n" +
                "\tsppdperson.*\n" +
                "FROM \n" +
                "(SELECT * FROM it_hris_notifikasi WHERE nip = :nip AND flag = 'Y' AND tipe_notif_id = :typeNotifId) notif LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_pegawai) users ON notif.from_person = users.nip LEFT JOIN\n" +
                "\t(SELECT * FROM it_hris_sppd) sppd ON notif.no_request = sppd.sppd_id LEFT JOIN\n" +
                "\t(SELECT * FROM it_hris_sppd_person)sppdperson ON sppdperson.sppd_id = sppd.sppd_id LEFT JOIN\n" +
                "    (SELECT * FROM im_branches) branches ON sppd.branch_id = branches.branch_id LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_pegawai_position) pposition ON users.nip = pposition.nip LEFT JOIN\n" +
                "    (SELECT * FROM im_position) position ON pposition.position_id = position.position_id LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_department) depart ON position.department_id = depart.department_id\n" +
                "   WHERE sppd.sppd_id IS NOT NULL AND sppdperson.approval_date IS NULL";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("typeNotifId", typeNotifId)
                .list();

        return results;
    }

    public List<Object[]> findTrainingByNipAndTypeNotif(String nip, String typeNotifId) throws HibernateException {
        String SQL1 = "SELECT\n" +
                "   training.training_id,\n" +
                "   users.nip,\n" +
                "   users.nama_pegawai,\n" +
                "   training.training_start_date,\n" +
                "   training.training_end_date,\n" +
                "   branches.branch_name,\n" +
                "   notif.notif_id\n" +
                "FROM\n" +
                "    (SELECT * FROM it_hris_notifikasi) notif LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_pegawai) users ON notif.from_person = users.nip LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_training) training ON notif.no_request = training.training_id LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_training_person) trainingperson ON training.training_id = trainingperson.training_id LEFT JOIN\n" +
                "    (SELECT * FROM im_branches) branches ON training.unit_id = branches.branch_id\n" +
                "WHERE trainingperson.approval_flag IS NULL AND notif.nip = :nip AND notif.flag = 'Y' AND notif.tipe_notif_id = :typeNotifId ";

        String SQL = "SELECT \n" +
                "                   training.training_id, \n" +
                "                   users.nip, \n" +
                "                   users.nama_pegawai, \n" +
                "                   training.training_start_date, \n" +
                "                   training.training_end_date, \n" +
                "                   branches.branch_name, \n" +
                "                   notif.notif_id \n" +
                "                FROM \n" +
                "                    (SELECT * FROM it_hris_notifikasi) notif LEFT JOIN \n" +
                "                    (SELECT * FROM im_hris_pegawai) users ON notif.from_person = users.nip LEFT JOIN \n" +
                "                    (SELECT * FROM it_hris_training) training ON notif.no_request = training.training_id LEFT JOIN \n" +
                "                    (SELECT * FROM it_hris_training_person) trainingperson ON training.training_id = trainingperson.training_id LEFT JOIN \n" +
                "                    (SELECT * FROM im_branches) branches ON training.unit_id = branches.branch_id \n" +
                "                WHERE trainingperson.approval_flag IS NULL AND notif.nip = :nip AND notif.flag = 'Y' AND notif.tipe_notif_id = :typeNotifId\n" +
                "\t\tGROUP BY training.training_id, users.nip, users.nama_pegawai, training.training_start_date, training.training_end_date, branches.branch_name, notif.notif_id";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("typeNotifId", typeNotifId)
                .list();
        return results;
    }

    public List<Object[]> findLemburByNipAndTypeNotif(String nip, String typeNotifId) {
        String SQL = "SELECT\n" +
                "      lembur_id,\n" +
                "      users.nip,\n" +
                "      users.nama_pegawai,\n" +
                "      lembur.tanggal_awal,\n" +
                "      lembur.tanggal_akhir,\n" +
                "      branches.branch_name,\n" +
                "      notif.notif_id,\n" +
                "      lembur.jam_awal,\n" +
                "      lembur.jam_akhir,\n" +
                "      lembur.lama_jam\n" +
                "FROM\n" +
                " (SELECT * FROM it_hris_notifikasi WHERE nip = :nip AND flag = 'Y' AND tipe_notif_id = :typeNotifId) notif LEFT JOIN\n" +
                " (SELECT * FROM im_hris_pegawai) users ON notif.from_person = users.nip LEFT JOIN\n" +
                " (SELECT * FROM it_hris_lembur) lembur ON notif.no_request = lembur.lembur_id LEFT JOIN\n" +
                " (SELECT * FROM it_hris_pegawai_position) position ON lembur.nip = position.nip LEFT JOIN\n" +
                " (SELECT * FROM im_branches) branches ON position.branch_id = branches.branch_id \n" +
                "WHERE approval_flag IS NULL ORDER BY lembur_id DESC";

        List<Object[]> results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("typeNotifId", typeNotifId)
                .list();
        return results;
    }

    //digunakan untuk mencari notifikasi by nomor request
    public List<ImNotifikasiEntity> getDataByNoRequest(String id,String fromPerson) throws HibernateException {
        List<ImNotifikasiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImNotifikasiEntity.class)
                .add(Restrictions.eq("noRequest", id))
                .add(Restrictions.eq("fromPerson", fromPerson))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("noRequest"))
                .list();
        return results;
    }
    //digunakan untuk mencari notifikasi by nomor request
    public List<ImNotifikasiEntity> getDataByNoRequest(String id) throws HibernateException {
        List<ImNotifikasiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImNotifikasiEntity.class)
                .add(Restrictions.eq("noRequest", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("noRequest"))
                .list();
        return results;
    }

    //digunakan untuk mencari notifikasi by nomor request
    public List<ImNotifikasiEntity> getDataForCheck(String id,String nip) throws HibernateException {
        List<ImNotifikasiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImNotifikasiEntity.class)
                .add(Restrictions.eq("noRequest", id))
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("noRequest"))
                .list();
        return results;
    }

    public List<Object[]> findNotifikasiUmum(String nip, String typeNotifId) {

        String SQL = "SELECT * FROM it_hris_notifikasi WHERE nip = :nip AND tipe_notif_id = :typeNotifId ORDER BY last_update DESC";

        List<Object[]> results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("typeNotifId", typeNotifId)
                .list();
        return results;
    }
}
