package com.neurix.hris.transaksi.medicalrecord.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.medicalrecord.model.ItHrisMedicalRecordEntity;
import com.neurix.hris.transaksi.medicalrecord.model.MedicalRecord;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class MedicalRecordDao extends GenericDao<ItHrisMedicalRecordEntity,String> {
    @Override
    protected Class<ItHrisMedicalRecordEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItHrisMedicalRecordEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisMedicalRecordEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("medical_record_id")!=null) {
                criteria.add(Restrictions.eq("medicalRecordId", (String) mapCriteria.get("medical_record_id")));
            }
            if (mapCriteria.get("tipe_pengobatan")!=null) {
                criteria.add(Restrictions.eq("tipePengobatan", (String) mapCriteria.get("tipe_pengobatan")));
            }
            if (mapCriteria.get("approval_flag")!=null) {
                criteria.add(Restrictions.eq("approved", (String) mapCriteria.get("approval_flag")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.ilike("nip", "%" + (String)mapCriteria.get("nip") + "%"));
            }
            if (mapCriteria.get("created_date")!=null) {
                criteria.add(Restrictions.ilike("createdDate", "%" + (String)mapCriteria.get("created_date") + "%"));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_sampai")!=null) {
                criteria.add(Restrictions.between("tanggalBerobat",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_sampai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggalBerobat",mapCriteria.get("tanggal_dari")));
                }
                else if (mapCriteria.get("tanggal_sampai")!=null) {
                    criteria.add(Restrictions.le("tanggalBerobat",mapCriteria.get("tanggal_sampai")));
                }
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("createdDate"));
        List<ItHrisMedicalRecordEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextMedicalId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_medical_record')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }

    // Generate surrogate id from postgre
    public String getNextjaminanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_surat_jaminan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }

    public MedicalRecord getSearchPrintSuratJaminan(String medicalRecordId) throws HibernateException{

        String id = "";
        String query = "";
        if (medicalRecordId != null){
            id = medicalRecordId;
        }

        query = "Select \n" +
                "a.medical_record_id,\n" +
                "a.nip,\n" +
                "a.no_surat_jaminan,\n" +
                "a.keluarga_id,\n" +
                "b.name,\n" +
                "b.status_keluarga,\n" +
                "a.nama_berobat,\n" +
                "h.alamat,\n" +
                "d.position_name,\n" +
                "e.kelas,\n" +
                "f.rs_name,\n" +
                "g.branch_name,\n" +
                "g.branch_address\n" +
                "from it_hris_medical_record a \n" +
                "LEFT OUTER JOIN im_hris_keluarga b on b.keluarga_id = a.keluarga_id and b.nip = a.nip\n" +
                "INNER JOIN it_hris_pegawai_position c on c.nip = a.nip\n" +
                "INNER JOIN im_position d on d.position_id = c.position_id\n" +
                "INNER JOIN im_hris_rs_kelas e on e.rs_kelas_id = a.rs_kelas_id\n" +
                "INNER JOIN im_hris_rs_kerjasama f on f.rs_id = e.rs_id\n" +
                "INNER JOIN im_branches g on g.branch_id = a.branch_id\n" +
                "INNER JOIN im_hris_pegawai h on h.nip = a.nip\n" +
                "WHERE a.medical_record_id = :medicalRecordId";

        List<Object[]> results = new ArrayList<Object[]>();

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("medicalRecordId", id)
                .list();

        MedicalRecord add = new MedicalRecord();
        for (Object[] row : results) {
            add.setMedicalRecordId(row[0].toString());
            add.setNip(row[1].toString());
            add.setNoSuratJaminan(row[2].toString());
            if (row[3] != null){
                add.setKeluargaId(row[3].toString());
            }
            if (row[4] != null){
                add.setNamaPasien(row[4].toString());
            }
            if (row[5] != null){
                add.setStatusKeluarga(row[5].toString());
            }

            add.setNama(row[6].toString());

            if (row[7] != null){
                add.setAlamat(row[7].toString());
            }
            add.setPositionName(row[8].toString());
            add.setKelas(row[9].toString());
            add.setNamaRumasSakit(row[10].toString());
            add.setBranchName(row[11].toString());
            add.setBranchAddress(row[12].toString());
        }
        return add;
    }

    public String updateFlagPayroll(String medicalRecordId, String payrollId) throws HibernateException{
        String query = "update\n" +
                "\tit_hris_medical_record\n" +
                "set\n" +
                "\tflag_payroll = :flag ,\n" +
                "\tpayroll_id = :payrollId\n" +
                "where\n" +
                "\tmedical_record_id = :medicalId";

        List<Object[]> results = new ArrayList<Object[]>();
        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("flag", "Y")
                .setParameter("payrollId", payrollId)
                .setParameter("medicalId", medicalRecordId)
                .executeUpdate();
        return null;
    }

    //mendapat data medical untuk payroll
    public List<ItHrisMedicalRecordEntity> getData(String nip,Date tanggal) throws HibernateException {
        List<ItHrisMedicalRecordEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisMedicalRecordEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approved", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.lt("tanggalBerobat",tanggal))
                .add(Restrictions.isNull("flagPayroll"))
                .list();

        return results;
    }
    public List<ItHrisMedicalRecordEntity> getListMedicalRecord(String medicalRecordId) throws HibernateException {
        List<ItHrisMedicalRecordEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisMedicalRecordEntity.class)
                .add(Restrictions.eq("medicalRecordId", medicalRecordId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}
