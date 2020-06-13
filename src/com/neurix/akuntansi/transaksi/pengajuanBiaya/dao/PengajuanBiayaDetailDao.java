package com.neurix.akuntansi.transaksi.pengajuanBiaya.dao;

import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ItPengajuanBiayaDetailEntity;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
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
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PengajuanBiayaDetailDao extends GenericDao<ItPengajuanBiayaDetailEntity, String> {

    @Override
    protected Class<ItPengajuanBiayaDetailEntity> getEntityClass() {
        return ItPengajuanBiayaDetailEntity.class;
    }

    @Override
    public List<ItPengajuanBiayaDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pengajuan_biaya_id")!=null) {
                criteria.add(Restrictions.eq("pengajuanBiayaId", (String) mapCriteria.get("pengajuan_biaya_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("pengajuanBiayaId"));
        List<ItPengajuanBiayaDetailEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPengajuanBiayaDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengajuan_biaya_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PBD"+sId;
    }


    public List<ItPengajuanBiayaDetailEntity> getListMasihMengajukan(String branchId,String divisiId) throws HibernateException {
        List<ItPengajuanBiayaDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("divisiId", divisiId))
                .add(Restrictions.isNull("closed"))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public List<ItPengajuanBiayaDetailEntity> getByPengajuanBiayaId(String id) throws HibernateException {
        List<ItPengajuanBiayaDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class)
                .add(Restrictions.eq("pengajuanBiayaId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pengajuanBiayaDetailId"))
                .list();
        return results;
    }

    public List<ItPengajuanBiayaDetailEntity> getDetailPengajuanForRk(String id) throws HibernateException {
        List<ItPengajuanBiayaDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class)
                .add(Restrictions.eq("pengajuanBiayaId", id))
                .add(Restrictions.eq("approvalKeuanganKpFlag", "Y"))
                .add(Restrictions.eq("statusKeuangan", "KP"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("pengajuanBiayaDetailId"))
                .list();
        return results;
    }
    public List<ItPengajuanBiayaDetailEntity> getListPengajuanBiayaDetailForKasKeluar(String id,String divisiId) throws HibernateException {
        List<ItPengajuanBiayaDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class)
                .add(Restrictions.ilike("pengajuanBiayaId", "%"+id+"%"))
                .add(Restrictions.eq("divisiId", divisiId))
                .add(Restrictions.eq("approvalKeuanganFlag", "Y"))
                .add(Restrictions.eq("closed", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("pengajuanBiayaDetailId"))
                .list();
        return results;
    }

    public List<ItPengajuanBiayaDetailEntity> getPengajuanBiayaMenggantung(String branchId) {
        List<ItPengajuanBiayaDetailEntity> listOfResult = new ArrayList<ItPengajuanBiayaDetailEntity>();
        List<Object[]> results = new ArrayList<Object[]>();

            String query = "select\n" +
                    "\t*\n" +
                    "from\n" +
                    "\tit_akun_pengajuan_biaya_detail";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity = new ItPengajuanBiayaDetailEntity();
                pengajuanBiayaDetailEntity.setPengajuanBiayaDetailId((String) row[0]);
                pengajuanBiayaDetailEntity.setKeperluan((String) row[6]);
                pengajuanBiayaDetailEntity.setTanggalRealisasi((Date) row[40]);
                pengajuanBiayaDetailEntity.setDivisiId((String) row[3]);
                listOfResult.add(pengajuanBiayaDetailEntity);
            }
        return listOfResult;
    }
}
