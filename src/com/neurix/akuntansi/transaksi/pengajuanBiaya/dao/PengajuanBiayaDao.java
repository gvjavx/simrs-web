package com.neurix.akuntansi.transaksi.pengajuanBiaya.dao;

import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ImPengajuanBiayaEntity;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
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

    protected static transient Logger logger = Logger.getLogger(PengajuanBiayaDao.class);

    @Override
    protected Class<ImPengajuanBiayaEntity> getEntityClass() {
        return ImPengajuanBiayaEntity.class;
    }

    @Override
    public List<ImPengajuanBiayaEntity> getByCriteria(Map mapCriteria) {
        List<ImPengajuanBiayaEntity> results = null;
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPengajuanBiayaEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pengajuan_biaya_id")!=null) {
                    criteria.add(Restrictions.eq("pengajuanBiayaId", mapCriteria.get("pengajuan_biaya_id").toString()));
            }
            if (mapCriteria.get("transaksi")!=null) {
                    criteria.add(Restrictions.eq("transaksi", mapCriteria.get("transaksi").toString()));
            }
            if (mapCriteria.get("branch_id")!=null) {
                    criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("divisi_id")!=null) {
                    criteria.add(Restrictions.eq("divisiId", mapCriteria.get("divisi_id").toString()));
            }
            if (mapCriteria.get("no_jurnal")!=null) {
                    criteria.add(Restrictions.eq("noJurnal", mapCriteria.get("no_jurnal").toString()));
            }
            if (mapCriteria.get("keterangan")!=null) {
                    criteria.add(Restrictions.ilike("keterangan", "%" + mapCriteria.get("keterangan") + "%"));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("tanggal",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggal",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal_selesai")));
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.desc("pengajuanBiayaId"));
            results = criteria.list();

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
        List<Object[]> results;
        String pengajuanBiayaId = null, atasan = null;
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
                pengajuanBiaya.setTanggal((Date) row[5]);
                pengajuanBiaya.setTotalBiaya(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));

                listOfResult.add(pengajuanBiaya);
            }
        }
        return listOfResult;
    }

    public List<PengajuanBiaya> getListPengajuanBiayaRkForApproval(Map mapCriteria) {
        List<PengajuanBiaya> listOfResult = new ArrayList<PengajuanBiaya>();
        List<Object[]> results;
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
                    "WHERE notifikasi.tipe_notif_id='TN01' AND pengajuan.flag='Y' "+searchAtasan+searchNip+searchPengajuanBiayaId+" ORDER BY pengajuan.pengajuan_biaya_id DESC";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                PengajuanBiaya pengajuanBiaya = new PengajuanBiaya();
                pengajuanBiaya.setPengajuanBiayaId((String) row[0]);
                pengajuanBiaya.setDivisiId((String) row[1]);
                pengajuanBiaya.setCoaAjuan((String) row[2]);
                pengajuanBiaya.setCoaTarget((String) row[3]);
                pengajuanBiaya.setAprovalId((String) row[6]);
                pengajuanBiaya.setAprovalFlag((String) row[8]);
                pengajuanBiaya.setBranchId((String) row[10]);
                pengajuanBiaya.setTransaksi((String) row[11]);
                pengajuanBiaya.setFlag((String) row[12]);
                pengajuanBiaya.setAction((String) row[13]);
                pengajuanBiaya.setAprovalName((String) row[18]);
                pengajuanBiaya.setKeterangan((String) row[19]);
                pengajuanBiaya.setNoJurnal((String) row[20]);
                pengajuanBiaya.setTanggal((Date) row[5]);
                pengajuanBiaya.setTotalBiaya(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
                pengajuanBiaya.setStTotalBiaya(CommonUtil.numbericFormat(pengajuanBiaya.getTotalBiaya(),"###,###"));

                listOfResult.add(pengajuanBiaya);
            }
        }
        return listOfResult;
    }

    public String getIdPengajuanByIdPengajuanDetail(String pengajuanDetailId){
        String query="select\n" +
                "\tpengajuan_biaya_id\n" +
                "from\n" +
                "\tit_akun_pengajuan_biaya_detail\n" +
                "where\n" +
                "\tpengajuan_biaya_detail_id = '"+pengajuanDetailId+"'\n" +
                "LIMIT 1";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();

        return results.toString();
    }

    public String getKeperluanNameBudgetting(String keperluanId){
        String query="select\n" +
                "\tnama_kontrak\n" +
                "From\n" +
                "\tit_akun_budgeting_pengadaan\n" +
                "where\n" +
                "\tid_pengadaan='"+keperluanId+"'\n" +
                "LIMIT 1";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();

        return results.toString();
    }

    public String getNamaHeader(String pengajuanId){
        String query="SELECT keterangan FROM it_akun_pengajuan_biaya WHERE pengajuan_biaya_id = '"+pengajuanId+"' LIMIT 1";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();

        return results.toString();
    }

    public String flagSudahSelesaiRk(String idPengajuan){

        String sql1 = "SELECT COUNT(rk_dikirim)\n" +
                "FROM it_akun_pengajuan_biaya_detail \n" +
                "WHERE pengajuan_biaya_id = '"+idPengajuan+"'\n" +
                "AND flag = 'Y'";

        List<Object> result1 = this.sessionFactory.getCurrentSession().createSQLQuery(sql1).list();


        String sql2 = "SELECT COUNT(rk_dikirim)\n" +
                "FROM it_akun_pengajuan_biaya_detail \n" +
                "WHERE pengajuan_biaya_id = '"+idPengajuan+"'\n" +
                "AND rk_dikirim = 'Y' \n"+
                "AND flag = 'Y'";

        List<Object> result2 = this.sessionFactory.getCurrentSession().createSQLQuery(sql2).list();

        Object obj1 = result1.get(0);
        Object obj2 = result2.get(0);

        Integer jumlahSeluruh = new Integer(obj1.toString());
        Integer jumlahDikirim = new Integer(obj2.toString());

        if (jumlahSeluruh.compareTo(jumlahDikirim) == 0){
            return "Y";
        } else {
            return "N";
        }

    }
}
