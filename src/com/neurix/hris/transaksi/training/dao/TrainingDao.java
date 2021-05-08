package com.neurix.hris.transaksi.training.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.medicalrecord.model.ItHrisMedicalRecordEntity;
import com.neurix.hris.transaksi.medicalrecord.model.MedicalRecord;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingEntity;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingPersonEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class TrainingDao extends GenericDao<ItHrisTrainingEntity,String> {
    @Override
    protected Class<ItHrisTrainingEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItHrisTrainingEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisTrainingEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("training_id")!=null) {
                criteria.add(Restrictions.eq("trainingId", (String) mapCriteria.get("training_id")));
            }
            if (mapCriteria.get("unit_id")!=null) {
                criteria.add(Restrictions.ilike("unitId", "%" + (String)mapCriteria.get("unit_id") + "%"));
            }
            if (mapCriteria.get("training_name")!=null) {
                criteria.add(Restrictions.ilike("trainingName", "%" + (String)mapCriteria.get("training_name") + "%"));
            }
            if (mapCriteria.get("created_date")!=null) {
                criteria.add(Restrictions.ilike("createdDate", "%" + (String)mapCriteria.get("created_date") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("createdDate"));
        List<ItHrisTrainingEntity> results = criteria.list();
        return results;
    }

    public List<Object[]> findInfoTraining(String idTraining, String nip) throws HibernateException {

        String query = "SELECT \n" +
                "    training.training_id, \n" +
                "    training.training_name,\n" +
                "    users.nip, \n" +
                "    users.nama_pegawai, \n" +
                "    training.training_start_date,\n" +
                "    training.training_end_date,\n" +
                "    branches.branch_name,\n" +
                "    positions.position_name,\n" +
                "    departement.department_name, \n" +
                "    trainingperson.training_person_id \n" +
                "FROM \n" +
                "    (SELECT * FROM it_hris_training) training LEFT JOIN \n" +
                "    (SELECT * FROM it_hris_training_person) trainingperson ON training.training_id = trainingperson.training_id LEFT JOIN \n" +
                "    (SELECT * FROM im_hris_pegawai) users ON trainingperson.person_id = users.nip LEFT JOIN \n" +
                "    (SELECT * FROM it_hris_pegawai_position) posisi ON users.nip = posisi.nip LEFT JOIN \n" +
                "    (SELECT * FROM im_position) positions ON posisi.position_id = positions.position_id LEFT JOIN\n" +
                "    (SELECT * FROM im_branches) branches ON posisi.branch_id = branches.branch_id LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_department) departement ON positions.department_id = departement.department_id \n" +
                "WHERE training.training_id = :idTraining AND trainingperson.person_id = :nip";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("idTraining", idTraining)
                .setParameter("nip", nip)
                .list();

        return results;
    }


    public List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws HibernateException {

        String query = "SELECT training.training_id, users.nip, users.nama_pegawai,trainid.training_start_date, trainid.training_end_date, trainid.tipe_training, branch.branch_name, divisi.department_name, training.last_update FROM\n" +
                "    (SELECT * FROM it_hris_training_person WHERE approval_id = :nip AND approval_flag = :flag) training LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_training) trainid ON training.training_id = trainid.training_id LEFT JOIN\n" +
                "    (SELECT * FROM im_branches) branch ON trainid.unit_id = branch.branch_id LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_pegawai) users ON training.person_id = users.nip LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_department) divisi ON training.divisi_id = divisi.department_id \n" +
                "ORDER BY last_update DESC";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("nip", nip)
                .setParameter("flag", flag)
                .list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_training')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());
        return sId;
    }

//    // Generate surrogate id from postgre
//    public String getNextjaminanId() throws HibernateException {
//        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_surat_jaminan')");
//        Iterator<BigInteger> iter=query.list().iterator();
//        String sId = String.format("%03d", iter.next());
//        return sId;
//    }

//    public MedicalRecord getSearchPrintSuratJaminan(String medicalRecordId) throws HibernateException{
//
//        String id = "";
//        String query = "";
//        if (medicalRecordId != null){
//            id = medicalRecordId;
//        }
//
//        query = "SELECT\n" +
//                "a.medical_record_id, \n" +
//                "a.nip, \n" +
//                "a.no_surat_jaminan, \n" +
//                "a.keluarga_id, \n" +
//                "b.name, \n" +
//                "b.status_keluarga, \n" +
//                "c.nama_pegawai, \n" +
//                "c.alamat,\n" +
//                "e.position_name,\n" +
//                "f.kelas,\n" +
//                "g.rs_name,\n" +
//                "h.branch_name,\n" +
//                "h.branch_address\n" +
//                "from it_hris_medical_record a\n" +
//                "LEFT OUTER JOIN im_hris_keluarga b on b.keluarga_id = a.keluarga_id and b.nip = a.nip\n" +
//                "INNER JOIN im_hris_personil c on c.nip = a.nip\n" +
//                "INNER JOIN im_users d on d.user_id = c.nip\n" +
//                "INNER JOIN im_position e on e.position_id = d.position_id\n" +
//                "INNER JOIN im_hris_rs_kelas f on f.kelompok_id = e.kelompok_id\n" +
//                "INNER JOIN im_hris_rs_kerjasama g on g.rs_id = f.rs_id\n" +
//                "INNER JOIN im_branches h on h.branch_id = a.branch_id\n" +
//                "WHERE a.medical_record_id = :medicalRecordId";
//
//        List<Object[]> results = new ArrayList<Object[]>();
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .setParameter("medicalRecordId", id)
//                .list();
//
//        MedicalRecord add = new MedicalRecord();
//        for (Object[] row : results) {
//            add.setMedicalRecordId(row[0].toString());
//            add.setNip(row[1].toString());
//            add.setNoSuratJaminan(row[2].toString());
//            if (row[3] != null){
//                add.setKeluargaId(row[3].toString());
//            }
//            if (row[4] != null){
//                add.setNamaPasien(row[4].toString());
//            }
//            if (row[5] != null){
//                add.setStatusKeluarga(row[5].toString());
//            }
//            add.setNama(row[6].toString());
//            add.setAlamat(row[7].toString());
//            add.setPositionName(row[8].toString());
//            add.setKelas(row[9].toString());
//            add.setNamaRumasSakit(row[10].toString());
//            add.setBranchName(row[11].toString());
//            add.setBranchAddress(row[12].toString());
//        }
//        return add;
//    }

    public List<ItHrisTrainingEntity> getListTrainingByNipAndTanggal(java.sql.Date tanggal) throws HibernateException {
        List<ItHrisTrainingEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisTrainingEntity.class)
                .add(Restrictions.ge("trainingEndDate",tanggal))
                .add(Restrictions.le("trainingStartDate",tanggal))
                .list();
        return results;
    }

    public List<ItHrisTrainingEntity> getListTrainingByNipAndTanggalForTest(Date tanggal) throws HibernateException {
        List<ItHrisTrainingEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisTrainingEntity.class)
                .add(Restrictions.le("trainingStartDate",tanggal))
                .add(Restrictions.ge("trainingEndDate",tanggal))
                .list();
        return results;
    }
    public List<ItHrisTrainingEntity> getListTrainingById(String id) throws HibernateException {
        List<ItHrisTrainingEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisTrainingEntity.class)
                .add(Restrictions.eq("trainingId",id))
                .list();
        return results;
    }
}
