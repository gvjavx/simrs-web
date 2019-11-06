
package com.neurix.hris.transaksi.smk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.updateGolongan.model.UpdateGolongan;
import com.neurix.hris.transaksi.smk.model.ImtHistorySmkGolonganEntity;
import com.neurix.hris.transaksi.smk.model.ItHistorySmkAspekActivityMonthlyEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
public class SmkHistoryGolonganDao extends GenericDao<ImtHistorySmkGolonganEntity, String> {

    @Override
    protected Class<ImtHistorySmkGolonganEntity> getEntityClass() {
        return ImtHistorySmkGolonganEntity.class;
    }

    @Override
    public List<ImtHistorySmkGolonganEntity> getByCriteria(Map mapCriteria) {
       return null;
    }

    // Generate surrogate id from postgre
    public String getNextId(String periode) throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_history_golongan')");
        Iterator<BigInteger> iter=query.list().iterator();

        String[] per = periode.split("");
        String sId = String.format("%05d", iter.next());

        return "HG" + per[2] + per[3] + sId;
    }

    public List<ImtHistorySmkGolonganEntity> getHistoryByUpdateId(String id) throws HibernateException {
        List<ImtHistorySmkGolonganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtHistorySmkGolonganEntity.class)
                .add(Restrictions.eq("updateGolonganId", id))
                .list();
        return results;
    }

    public List<ImtHistorySmkGolonganEntity> getHistoryByUpdateIdAndNip(String id, String nip) throws HibernateException {
        List<ImtHistorySmkGolonganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtHistorySmkGolonganEntity.class)
                .add(Restrictions.eq("updateGolonganId", id))
                .add(Restrictions.eq("nip", nip))
                .list();
        return results;
    }

    public List<ImtHistorySmkGolonganEntity> getHistoryJabatan(String nip, String tahun) throws HibernateException {
        List<ImtHistorySmkGolonganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtHistorySmkGolonganEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ImtHistorySmkGolonganEntity> getHistoryJabatan2(String tahun, String nip) throws HibernateException {
        List<ImtHistorySmkGolonganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtHistorySmkGolonganEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .list();
        return results;
    }

    public List<ImtHistorySmkGolonganEntity> getDataGolongan2Tahun(String updateGolonganId, String periode){
        List<ImtHistorySmkGolonganEntity> listOfResult = new ArrayList<ImtHistorySmkGolonganEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\thistory.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tlastHistory.golongan_id as tahun_2018,\n" +
                "\tlastHistory.point ,\n" +
                "\tlastHistory.poin_lebih,\n" +
                "\tsmk.grand_total_nilai_prestasi,\n" +
                "\tsmk.nilai_prestasi," +
                "\thistory.golongan_id as tahun_2019,\n" +
                "\thistory.point as poin_baru,\n" +
                "\thistory.poin_lebih as poin_lebih_baru,\n" +
                "\tgolonganLama.golongan_name as namaGolonganLama,\n" +
                "\tgolonganBaru.golongan_name as namaGolonganBaru,\n" +
                "\thistory.update_golongan_id,\n" +
                "\thistory.status,\n" +
                "\thistory.position_id,\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "from\n" +
                "\timt_hris_history_smk_golongan history\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = history.nip\n" +
                "\tleft join imt_hris_history_smk_golongan lastHistory on lastHistory.nip = history.nip and lastHistory.tahun = '"+periode+"' and lastHistory.branch_id = history.branch_id\n" +
                "\tleft join im_position posisi on posisi.position_id = history.position_id \n" +
                "\tleft join im_hris_golongan golonganLama on golonganLama.golongan_id = lastHistory.golongan_id\n" +
                "\tleft join im_hris_golongan golonganBaru on golonganBaru.golongan_id = history.golongan_id\n" +
                "\tleft join it_hris_smk_evaluasi_pegawai smk on smk.nip = history.nip and periode = '"+periode+"' and smk.branch_id = history.branch_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where\n" +
                "\thistory.update_golongan_id = '"+updateGolonganId+"'\n" +
                "order by\n" +
                "\tposisi.bagian_id,\n" +
                "\thistory.nip";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImtHistorySmkGolonganEntity result  = new ImtHistorySmkGolonganEntity();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setGolonganIdBefore((String) row[2]);
            if(row[3] != null){
                result.setPoinBefore((int) row[3]);
            }else{
                result.setPoinBefore(0);
            }
            if(row[4] != null){
                result.setPoinLebihBefore((int) row[4]);
            }else{
                result.setPoinLebihBefore(0);
            }
            if(row[5] != null){
                result.setNilaiAngka((BigDecimal) row[5]);
            }else{
                result.setNilaiAngka(BigDecimal.valueOf(0));
            }

            result.setNilaiHuruf((String)row[6]);
            result.setGolonganId((String) row[7]);

            if(row[8] != null){
                result.setPoin((int) row[8]);
            }else{
                result.setPoin(0);
            }
            if(row[9] != null){
                result.setPoinLebih((int) row[9]);
            }else{
                result.setPoinLebih(0);
            }
            result.setGolonganNameBefore((String) row[10]);
            result.setGolonganName((String) row[11]);
            result.setUpdateGolonganId((String) row[12]);
            result.setStatus((String) row[13]);
            result.setPositionId((String) row[14]);
            result.setBagianId((String) row[15]);
            result.setBagianName((String) row[16]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImtHistorySmkGolonganEntity> getDataGolongan2Tahun(String updateGolonganId){
        List<ImtHistorySmkGolonganEntity> listOfResult = new ArrayList<ImtHistorySmkGolonganEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\thistory.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\thistory.golongan_id as tahun_baru,\n" +
                "\thistory.point as poin_baru,\n" +
                "\thistory.poin_lebih as poin_lebih_baru,\n" +
                "\tgolonganBaru.golongan_name,\n" +
                "\thistory.update_golongan_id\n" +
                "from\n" +
                "\timt_hris_history_smk_golongan history\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = history.nip\n" +
                "\tleft join im_position posisi on posisi.position_id = history.position_id \n" +
                "\tleft join im_hris_golongan golonganBaru on golonganBaru.golongan_id = history.golongan_id\n" +
                "where\n" +
                "\thistory.update_golongan_id = '"+updateGolonganId+"'\n" +
                "order by\n" +
                "\tposisi.bagian_id," +
                "\thistory.nip";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImtHistorySmkGolonganEntity result  = new ImtHistorySmkGolonganEntity();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setGolonganId((String) row[2]);

            if(row[3] != null){
                result.setPoin((int) row[3]);
            }else{
                result.setPoin(0);
            }
            if(row[4] != null){
                result.setPoinLebih((int) row[4]);
            }else{
                result.setPoinLebih(0);
            }

            result.setGolonganName((String) row[5]);
            result.setUpdateGolonganId((String) row[6]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImtHistorySmkGolonganEntity> getDataGolongan(String tahun, String nip){
        List<ImtHistorySmkGolonganEntity> listOfResult = new ArrayList<ImtHistorySmkGolonganEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tgolongan.nip,\n" +
                "\tgolongan.branch_id,\n" +
                "\tgolongan.tahun,\n" +
                "\tgolongan.golongan_id,\n" +
                "\tmasterGolongan.golongan_name,\n" +
                "\tgolongan.point,\n" +
                "\tgolongan.poin_lebih\n" +
                "from\n" +
                "\timt_hris_history_smk_golongan golongan\n" +
                "\tleft join im_hris_golongan masterGolongan on masterGolongan.golongan_id = golongan.golongan_id\n" +
                "where\n" +
                "\tgolongan.tahun = '"+tahun+"'\n" +
                "\tand golongan.nip = '"+nip+"'\n" +
                "\tand golongan.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImtHistorySmkGolonganEntity result  = new ImtHistorySmkGolonganEntity();
            result.setNip((String) row[0]);
            result.setBranchId((String) row[1]);
            result.setTahun((String) row[2]);
            result.setGolonganId((String) row[3]);
            result.setGolonganName((String) row[4]);

            if(row[5] != null){
                result.setPoin((int) row[5]);
            }else{
                result.setPoin(0);
            }
            if(row[6] != null){
                result.setPoinLebih((int) row[6]);
            }else{
                result.setPoinLebih(0);
            }

            listOfResult.add(result);
        }
        return listOfResult;
    }
}
