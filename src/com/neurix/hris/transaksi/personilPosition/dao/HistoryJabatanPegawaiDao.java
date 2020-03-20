
package com.neurix.hris.transaksi.personilPosition.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.personilPosition.model.HistoryJabatanPegawai;
import com.neurix.hris.transaksi.personilPosition.model.ImtHrisHistoryJabatanPegawaiEntity;
import com.neurix.hris.transaksi.personilPosition.model.ImtHrisHistoryJabatanPegawaiEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
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
public class HistoryJabatanPegawaiDao extends GenericDao<ImtHrisHistoryJabatanPegawaiEntity, String> {

    @Override
    protected Class<ImtHrisHistoryJabatanPegawaiEntity> getEntityClass() {
        return ImtHrisHistoryJabatanPegawaiEntity.class;
    }

    @Override
    public List<ImtHrisHistoryJabatanPegawaiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtHrisHistoryJabatanPegawaiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.asc("personilPositionId"));

        List<ImtHrisHistoryJabatanPegawaiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPersonilPositionId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personil_position')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "HJP"+sId;
    }

    public List<ImtHrisHistoryJabatanPegawaiEntity> getDataHistoryJabatan(String nip) throws HibernateException {
        List<ImtHrisHistoryJabatanPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtHrisHistoryJabatanPegawaiEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("nip", nip))
                .addOrder(Order.desc("tahun"))
                .addOrder(Order.desc("tanggal"))
                .list();
        return results;
    }

    public List<ImtHrisHistoryJabatanPegawaiEntity> getDataHistoryJabatan() throws HibernateException {
        List<ImtHrisHistoryJabatanPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtHrisHistoryJabatanPegawaiEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.isNotNull("positionId"))
                .addOrder(Order.desc("tahun"))
                .list();
        return results;
    }

    public List<ImtHrisHistoryJabatanPegawaiEntity> getDataHistoryJabatan(String nip, String tahun, String branchId) throws HibernateException {
        List<ImtHrisHistoryJabatanPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtHrisHistoryJabatanPegawaiEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("branchId", branchId))
                .addOrder(Order.desc("tahun"))
                .list();
        return results;
    }

    public List<ImtHrisHistoryJabatanPegawaiEntity> getDataHistoryJabatan(String nip, String tahun) throws HibernateException {
        List<ImtHrisHistoryJabatanPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtHrisHistoryJabatanPegawaiEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .addOrder(Order.asc("tanggalMutasiSk"))
                .list();
        return results;
    }

    public List<ImtHrisHistoryJabatanPegawaiEntity> historyJabatanDanSmk(String nip){
        List<ImtHrisHistoryJabatanPegawaiEntity> listOfResult = new ArrayList<ImtHrisHistoryJabatanPegawaiEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "historyJabatan.history_jabatan_pegawai_id,\n" +
                "\thistoryJabatan.tahun,\n" +
                "\thistoryJabatan.nip,\n" +
                "\thistoryJabatan.position_name,\n" +
                "\thistoryJabatan.tipe_pegawai,\n" +
                "\tsmkGolongan.history_smk_golongan_id,\n" +
                "\tsmkGolongan.golongan_id,\n" +
                "\tsmkGolongan.point,\n" +
                "\tsmkGolongan.poin_lebih,\n" +
                "\tsmkGolongan.position_id,\n" +
                "\tsmkGolongan.nilai_angka,\n" +
                "\tsmkGolongan.nilai_huruf,\n" +
                "\thistoryJabatan.branch_name\n" +
                "from\n" +
                "\timt_hris_history_jabatan_pegawai historyJabatan\n" +
                "\tleft join imt_hris_history_smk_golongan smkGolongan on smkGolongan.nip = historyJabatan.nip and smkGolongan.tahun = historyJabatan.tahun\n" +
                "where\n" +
                "\thistoryJabatan.nip = '"+nip+"'\n" +
                "\tand historyJabatan.flag = 'Y'\n" +
                "order by\n" +
                "\thistoryJabatan.tahun";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImtHrisHistoryJabatanPegawaiEntity result  = new ImtHrisHistoryJabatanPegawaiEntity();
            result.setHistoryJabatanId((String) row[0]);
            result.setTahun((String) row[1]);
            result.setNip((String) row[2]);
            result.setPositionName((String) row[3]);

            if(row[4] != null){
                result.setTipePegawaiName((String) row[4]);
            }else{
                result.setTipePegawaiName("-");
            }

            if(row[6] != null){
                result.setGolonganId((String) row[6]);
            }else{
                result.setGolonganId("-");
            }

            if(row[7] != null){
                result.setPoint((Integer) row[7] + "");
            }else{
                result.setPoint("0");
            }

            if(row[8] != null){
                result.setPointLebih((Integer) row[8] + "");
            }else{
                result.setPointLebih("0");
            }

            if(row[10] != null){
                result.setNilaiSmk(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            }else{
                result.setNilaiSmk(BigDecimal.valueOf(0));
            }

            if(row[11] != null){
                result.setGradeSmk((String) row[11]);
            }else{
                result.setGradeSmk("-");
            }
            result.setBranchName((String) row[12]);

            listOfResult.add(result);
        }

        return listOfResult;
    }
    public String getBranchById(String branchId) throws HibernateException {
        String Result;
        String query ="select branch_name from im_branches where branch_id = '"+branchId+"'";

        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        Result = results.toString();
        return Result;
    }
    public String getPositionById(String positionId) throws HibernateException {
        String Result;
        String query ="select position_name from im_position where position_id ='"+positionId+"'";

        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        Result = results.toString();
        return Result;
    }

    public String getDivisiById(String divisiId) throws HibernateException {
        String Result;
        String query ="select department_name from im_hris_department where department_id ='"+divisiId+"'";

        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        Result = results.toString();
        return Result;
    }
    public String getGolonganById(String golonganId) throws HibernateException {
        String Result;
        String query ="select golongan_name from im_hris_golongan where golongan_id ='"+golonganId+"'";

        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        Result = results.toString();
        return Result;
    }
    public String getGolonganPkwtById(String golonganId) throws HibernateException {
        String Result;
        String query ="select golongan_pkwt_name from im_hris_golongan_pkwt where golongan_pkwt_id ='"+golonganId+"'";

        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        Result = results.toString();
        return Result;
    }
    public String getTipePegawaiById(String tipePegawaiId) throws HibernateException {
        String Result;
        String query ="select tipe_pegawai_name from im_hris_tipe_pegawai where tipe_pegawai_id ='"+tipePegawaiId+"'";

        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        Result = results.toString();
        return Result;
    }

    public List<HistoryJabatanPegawai> geyBagianByPositionId(String positionId) throws HibernateException {
        List<HistoryJabatanPegawai> listOfResult = new ArrayList<HistoryJabatanPegawai>();

        String query = "select DISTINCT \n" +
                "\t im_hris_position_bagian.bagian_id, \n" +
                "\t im_hris_position_bagian.nama_bagian \n" +
                "from\n" +
                "\t im_hris_position_bagian, \n"+
                "\t im_position \n"+
                "WHERE \n"+
                "\t im_position.bagian_id = im_hris_position_bagian.bagian_id \n"+
                "\t and im_position.position_id = '"+positionId+"'";
        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        HistoryJabatanPegawai historyJabatan;
        for(Object[] rows: results){
            historyJabatan = new HistoryJabatanPegawai();
            historyJabatan.setBagianId(rows[0].toString());
            historyJabatan.setBagianName(rows[1].toString());
            listOfResult.add(historyJabatan);
        }
        return listOfResult;
    }
    public List<ImtHrisHistoryJabatanPegawaiEntity> getDataHistoryForThp(String nip) throws HibernateException {
        List<ImtHrisHistoryJabatanPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtHrisHistoryJabatanPegawaiEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("nip", nip))
                .addOrder(Order.asc("tahun"))
                .addOrder(Order.asc("tanggal"))
                .list();
        return results;
    }
    
}
