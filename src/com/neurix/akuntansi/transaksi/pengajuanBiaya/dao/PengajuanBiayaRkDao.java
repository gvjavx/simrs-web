package com.neurix.akuntansi.transaksi.pengajuanBiaya.dao;

import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ItAkunPengajuanBiayaRkEntity;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiayaRk;
import com.neurix.common.constant.CommonConstant;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PengajuanBiayaRkDao extends GenericDao<ItAkunPengajuanBiayaRkEntity, String> {

    @Override
    protected Class<ItAkunPengajuanBiayaRkEntity> getEntityClass() {
        return ItAkunPengajuanBiayaRkEntity.class;
    }

    @Override
    public List<ItAkunPengajuanBiayaRkEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItAkunPengajuanBiayaRkEntity.class);

        // Get Collection and sorting
        if (mapCriteria.get("pengajuan_biaya_rk_id")!=null) {
            criteria.add(Restrictions.eq("pengajuanBiayaRkId", (String) mapCriteria.get("pengajuan_biaya_rk_id")));
        }
        if (mapCriteria.get("branch_id")!=null) {
            criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
        }
        if (mapCriteria.get("master_id")!=null) {
            criteria.add(Restrictions.eq("masterId", (String) mapCriteria.get("master_id")));
        }
        if (mapCriteria.get("transaksi")!=null) {
            criteria.add(Restrictions.eq("transaksi", (String) mapCriteria.get("transaksi")));
        }
        if (mapCriteria.get("status")!=null) {
            criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
        }
        if (mapCriteria.get("rk_id")!=null) {
            criteria.add(Restrictions.eq("rkId", (String) mapCriteria.get("rk_id")));
        }
        if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
            criteria.add(Restrictions.between("tanggalPengajuan",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
        }
        else {
            if (mapCriteria.get("tanggal_dari")!=null) {
                criteria.add(Restrictions.ge("tanggalPengajuan",mapCriteria.get("tanggal_dari")));
            }
            if (mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.le("tanggalPengajuan",mapCriteria.get("tanggal_selesai")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("pengajuanBiayaRkId"));
        List<ItAkunPengajuanBiayaRkEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengajuan_biaya_rk')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PBR"+sId;
    }

    public List<ItAkunPengajuanBiayaRkEntity> getPembayaranDoBelumDibayar (PengajuanBiayaRk search){
        List<ItAkunPengajuanBiayaRkEntity> listOfResult = new ArrayList<ItAkunPengajuanBiayaRkEntity>();
        String tipeWhere ="";
        if(!("").equalsIgnoreCase(search.getMasterId())){
            tipeWhere = " AND master_id = '"+search.getMasterId()+"' ";
        }

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "  master_id, \n" +
                "  rekening_id, \n" +
                "  noNota, \n" +
                "  abs(debit) as debit, \n" +
                "  po.* \n" +
                "from \n" +
                "  (\n" +
                "    select \n" +
                "      b.master_id, \n" +
                "      b.rekening_id, \n" +
                "      a.mata_uang_id as mataUangId, \n" +
                "      b.no_nota as noNota, \n" +
                "      sum(jumlah_debit - jumlah_kredit) as debit \n" +
                "    from \n" +
                "      it_akun_jurnal a \n" +
                "      inner join (\n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          it_akun_jurnal_detail \n" +
                "        where \n" +
                "          nomor_rekening = '"+ CommonConstant.REKENING_ID_DO +"'\n" +
                "      ) b on a.no_jurnal = b.no_jurnal \n" +
                "      and b.no_nota != '' \n" +
                "    where \n" +
                "      a.flag = 'Y' \n" +
                "      and a.registered_flag = 'Y' \n" +
                "      and a.branch_id IN ('"+search.getBranchId()+"') \n" +
                "    group by \n" +
                "      b.no_nota, \n" +
                "      a.mata_uang_id, \n" +
                "      b.master_id, \n" +
                "      b.rekening_id\n" +
                "  ) jurnal \n" +
                "  LEFT JOIN (\n" +
                "    SELECT \n" +
                "      no_faktur, \n" +
                "      tanggal_faktur, \n" +
                "      tgl_invoice, \n" +
                "      tgl_do, \n" +
                "      no_invoice, \n" +
                "      no_do \n" +
                "    FROM \n" +
                "      mt_simrs_transaksi_obat_detail_batch \n" +
                "    WHERE \n" +
                "      status = 'Y' \n" +
                "      and approve_flag= 'Y' \n" +
                "    GROUP BY \n" +
                "      no_faktur, \n" +
                "      tanggal_faktur, \n" +
                "      tgl_invoice, \n" +
                "      tgl_do, \n" +
                "      no_invoice, \n" +
                "      no_do\n" +
                "  ) po ON po.no_do = jurnal.noNota \n" +
                "where \n" +
                "  jurnal.debit < 0 \n" +
                tipeWhere +
                "  --and no_do IS NOT NULL\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItAkunPengajuanBiayaRkEntity data= new ItAkunPengajuanBiayaRkEntity();
            data.setNoTransaksi((String) row[2]);
            data.setMasterId((String) row[0]);
            data.setJumlah((BigDecimal) row[3]);
            data.setStatus("B");
            data.setTglInvoice((Date) row[6]);
            data.setBranchId(search.getBranchId());
            data.setNoFaktur((String) row[4]);
            data.setNoInvoice((String) row[8]);
            data.setTglFaktur((Date) row[5]);
            data.setTglDo((Date) row[7]);

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<ItAkunPengajuanBiayaRkEntity> getByNoTransaksiId(String id) throws HibernateException {
        List<ItAkunPengajuanBiayaRkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPengajuanBiayaRkEntity.class)
                .add(Restrictions.eq("noTransaksi", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("noTransaksi"))
                .list();
        return results;
    }
}
