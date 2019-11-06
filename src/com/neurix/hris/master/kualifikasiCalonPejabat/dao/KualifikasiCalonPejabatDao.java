package com.neurix.hris.master.kualifikasiCalonPejabat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.kualifikasiCalonPejabat.model.ImHrisKualifikasiCalonPejabatEntity;
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
public class KualifikasiCalonPejabatDao extends GenericDao<ImHrisKualifikasiCalonPejabatEntity,String> {
    @Override
    protected Class<ImHrisKualifikasiCalonPejabatEntity> getEntityClass() {
        return ImHrisKualifikasiCalonPejabatEntity.class;
    }

    @Override
    public List<ImHrisKualifikasiCalonPejabatEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisKualifikasiCalonPejabatEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("kualifikasi_id")!=null) {
                criteria.add(Restrictions.eq("kualifikasiId", (String) mapCriteria.get("kualifikasi_id")));
            }

            if (mapCriteria.get("jurusan_id")!=null) {
                criteria.add(Restrictions.eq("jurusanId", (String) mapCriteria.get("jurusan_id")));
            }

            if (mapCriteria.get("jabatan_id")!=null) {
                criteria.add(Restrictions.eq("jabatanId", (String) mapCriteria.get("jabatan_id")));
            }

            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("bagian_id")!=null) {
                criteria.add(Restrictions.eq("bagianId", (String) mapCriteria.get("bagian_id")));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("kualifikasiId"));
        List<ImHrisKualifikasiCalonPejabatEntity> results = criteria.list();

        return results;
    }

    public List<ImHrisKualifikasiCalonPejabatEntity> getKualifikasi(String positionId) throws HibernateException {
        List<ImHrisKualifikasiCalonPejabatEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisKualifikasiCalonPejabatEntity.class)
                .add(Restrictions.eq("jabatanId", positionId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<Biodata> getBiodataAwal(String statusPegawai, String branchId) throws HibernateException {
        List<Biodata> listOfResult = new ArrayList<Biodata>();

        String query = "select\n" +
                "\tpegawai.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tpegawai.tanggal_aktif,\n" +
                "\tpegawai.golongan_id\n" +
                "from\n" +
                "\tim_hris_pegawai pegawai\n" +
                "\tleft join it_hris_pegawai_position posisi on posisi.nip = pegawai.nip\n" +
                "where\n" +
                "\tpegawai.status_pegawai = '"+statusPegawai+"'\n" +
                "\tand pegawai.flag = 'Y'\n" +
                "\tand posisi.branch_id = '"+branchId+"'\n" +
                "\tand golongan_id is not null";


        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        Biodata biodata;
        for(Object[] rows: results){
            biodata = new Biodata();
            biodata.setNip(rows[0].toString());
            biodata.setNamaPegawai(rows[1].toString());

            if(rows[2] != null){
                biodata.setTanggalAktif((Date) rows[2]);
            }

            listOfResult.add(biodata);
        }

        return listOfResult;
    }

    // Generate surrogate id from postgre
    public String getNextCarrerPathId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_carrer_path')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return "CP"+sId;
    }
    // Generate surrogate id from postgre
    /*public String getNextGroupId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_group')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }*/

}
