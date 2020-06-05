package com.neurix.akuntansi.transaksi.pengajuanBiaya.dao;

import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ImPengajuanBiayaEntity;
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
public class PengajuanBiayaDao extends GenericDao<ImPengajuanBiayaEntity, String> {

    @Override
    protected Class<ImPengajuanBiayaEntity> getEntityClass() {
        return ImPengajuanBiayaEntity.class;
    }

    @Override
    public List<ImPengajuanBiayaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPengajuanBiayaEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pengajuan_biaya_id")!=null) {
                criteria.add(Restrictions.eq("pengajuanBiayaId", (String) mapCriteria.get("pengajuan_biaya_id")));
            }
            if (mapCriteria.get("transaksi")!=null) {
                criteria.add(Restrictions.eq("transaksi", (String) mapCriteria.get("transaksi")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }
            if (mapCriteria.get("no_jurnal")!=null) {
                criteria.add(Restrictions.eq("noJurnal", (String) mapCriteria.get("no_jurnal")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("pengajuanBiayaId"));
        List<ImPengajuanBiayaEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPengajuanBiayaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengajuan_biaya')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PB"+sId;
    }
    public List<PengajuanBiaya> getListPengajuanBiayaForApproval(Map mapCriteria) {
        List<PengajuanBiaya> listOfResult = new ArrayList<PengajuanBiaya>();
        List<Object[]> results = new ArrayList<Object[]>();
        String nip = null, pengajuanBiayaId = null,atasan=null;
        String searchNip = "" ;
        String searchPengajuanBiayaId = "" ;
        String searchAtasan = "" ;
        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("pengajuan_biaya_id") != null) {
                pengajuanBiayaId = (String) mapCriteria.get("pengajuan_biaya_id");
            }
            if (mapCriteria.get("nip_atasan") != null) {
                atasan = (String) mapCriteria.get("nip_atasan");
            }
            if (pengajuanBiayaId!=null){
                if(!pengajuanBiayaId.equalsIgnoreCase("")){
                    searchPengajuanBiayaId = " AND pengajuan.pengajuan_biaya_id= '" + pengajuanBiayaId + "' " ;
                }
            }
            if (atasan!=null){
                if(!atasan.equalsIgnoreCase("")){
                    searchAtasan = " AND notifikasi.nip = '" + atasan + "' " ;
                }
            }

            String query = "SELECT pengajuan.* FROM  \n" +
                    "                    ( SELECT * FROM it_hris_notifikasi ) notifikasi LEFT JOIN  \n" +
                    "                    ( SELECT * FROM it_akun_pengajuan_biaya ) pengajuan ON notifikasi.no_request=pengajuan.pengajuan_biaya_id " +
                    "WHERE notifikasi.tipe_notif_id='TN04' AND pengajuan.flag='Y' "+searchAtasan+searchNip+searchPengajuanBiayaId+" ORDER BY pengajuan.pengajuan_biaya_id DESC";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                PengajuanBiaya pengajuanBiaya = new PengajuanBiaya();
                pengajuanBiaya.setPengajuanBiayaId((String) row[0]);
                pengajuanBiaya.setDivisiId((String) row[1]);
                pengajuanBiaya.setAprovalId((String) row[6]);
                pengajuanBiaya.setAprovalFlag((String) row[8]);
                pengajuanBiaya.setBranchId((String) row[10]);
                pengajuanBiaya.setTransaksi((String) row[11]);
                pengajuanBiaya.setFlag((String) row[12]);
                pengajuanBiaya.setAction((String) row[13]);
                pengajuanBiaya.setAprovalName((String) row[18]);
                pengajuanBiaya.setKeterangan((String) row[19]);
                pengajuanBiaya.setNoJurnal((String) row[20]);

                listOfResult.add(pengajuanBiaya);
            }
        }
        return listOfResult;
    }
}
