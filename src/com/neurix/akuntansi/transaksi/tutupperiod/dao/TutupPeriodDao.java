package com.neurix.akuntansi.transaksi.tutupperiod.dao;

import com.neurix.akuntansi.transaksi.tutupperiod.model.ItAkunTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 20/03/20.
 */
public class TutupPeriodDao extends GenericDao<ItAkunTutupPeriodEntity, String> {

    @Override
    protected Class<ItAkunTutupPeriodEntity> getEntityClass() {
        return ItAkunTutupPeriodEntity.class;
    }

    @Override
    public List<ItAkunTutupPeriodEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunTutupPeriodEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("rekening_id") != null)
            criteria.add(Restrictions.eq("rekeningId", mapCriteria.get("rekening_id").toString()));
        if (mapCriteria.get("parent_id") != null)
            criteria.add(Restrictions.eq("parentId", mapCriteria.get("parent_id").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_tutup_period')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<TutupPeriod> getListDetailJurnalByCriteria(TutupPeriod bean){

//        String rekening = "%";
//        if (bean.getRekeningId() != null && !"".equalsIgnoreCase(bean.getRekeningId())){
//            rekening = bean.getRekeningId();
//        }

        String SQL = "SELECT \n" +
                "dt.rekening_id,\n" +
                "kd.parent_id,\n" +
                "kd.kode_rekening,\n" +
                "kd.nama_kode_rekening,\n" +
                "SUM(dt.jumlah_debit) as jumlah_debit,\n" +
                "SUM(dt.jumlah_kredit) as jumlah_kredit\n" +
                "FROM it_akun_jurnal h\n" +
                "INNER JOIN it_akun_jurnal_detail dt ON dt.no_jurnal = h.no_jurnal\n" +
                "INNER JOIN im_akun_kode_rekening kd ON kd.rekening_id = dt.rekening_id\n" +
                "WHERE registered_flag = 'Y'\n" +
                "AND EXTRACT(MONTH FROM h.tanggal_jurnal) = :bulan \n" +
                "AND EXTRACT(YEAR FROM h.tanggal_jurnal) = :tahun \n" +
                "AND h.branch_id = :unit \n" +
                "GROUP\n" +
                "BY \n" +
                "dt.rekening_id,\n" +
                "kd.parent_id,\n" +
                "kd.kode_rekening,\n" +
                "kd.nama_kode_rekening\n" +
                "ORDER BY kd.kode_rekening";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("bulan", new Integer(bean.getBulan()))
                .setParameter("tahun", new Integer(bean.getTahun()))
                .setParameter("unit", bean.getUnit())
                .list();

        List<TutupPeriod> tutupPeriods = new ArrayList<>();
        if (results.size() > 0){
            TutupPeriod tutupPeriod;
            for (Object[] obj : results){
                tutupPeriod = new TutupPeriod();
                tutupPeriod.setRekeningId(obj[0].toString());
                tutupPeriod.setParentId(obj[1].toString());
                tutupPeriod.setKodeRekening(obj[2].toString());
                tutupPeriod.setNamaKodeRekening(obj[3].toString());
                tutupPeriod.setJumlahDebit(obj[4] == null ? new BigDecimal(0) : (BigDecimal) obj[4]);
                tutupPeriod.setJumlahKredit(obj[5] == null ? new BigDecimal(0) : (BigDecimal) obj[5]);
            }
        }

        return tutupPeriods;
    }
}
