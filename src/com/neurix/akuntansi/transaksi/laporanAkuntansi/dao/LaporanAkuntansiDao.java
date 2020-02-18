
package com.neurix.akuntansi.transaksi.laporanAkuntansi.dao;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.ItLaporanAkuntansiEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class LaporanAkuntansiDao extends GenericDao<ItLaporanAkuntansiEntity, String> {

    @Override
    protected Class<ItLaporanAkuntansiEntity> getEntityClass() {
        return ItLaporanAkuntansiEntity.class;
    }

    @Override
    public List<ItLaporanAkuntansiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItLaporanAkuntansiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.asc("laporanAkuntansiId"));
        List<ItLaporanAkuntansiEntity> results = criteria.list();
        return results;
    }

    public List<Aging> getAging(String branchId, String periode,String masterId,String tipeAging,String reportId){
        List<Aging> listOfResult = new ArrayList<>();
        String tipeWhere = "";
        if (!"".equalsIgnoreCase(masterId)){
            tipeWhere = "and masterId like '"+masterId+"'";
        }
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "  * \n" +
                "from \n" +
                "  (\n" +
                "    select \n" +
                "\t  kr.kode_rekening,\n" +
                "      b.rekening_id, \n" +
                "      b.no_nota as noNota, \n" +
                "      a.tanggal_jurnal as tglJurnal, \n" +
                "      c.kode_mata_uang as mataUang, \n" +
                "      (b.jumlah_debit - b.jumlah_kredit) as total, \n" +
                "      b.master_id as masterId, \n" +
                "      d.nama as namaMaster, \n" +
                "      d.master_id as masterGrp, \n" +
                "      f.nilai_kurs as kurs\n" +
                "    from \n" +
                "      (\n" +
                "\t\t-- mencari semua data di jurnal yang di joinkan dengan setting aging jurnal\n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          it_akun_jurnal\n" +
                "        where \n" +
                "          flag = 'Y' \n" +
                "          and registered_flag = 'Y'\n" +
                "\t\t  and branch_id='"+branchId+"'\n" +
                "      ) a \n" +
                "      inner join it_akun_jurnal_detail b on b.no_jurnal = a.no_jurnal \n" +
                "      inner join m_mata_uang c on a.mata_uang_id = c.mata_uang_id \n" +
                "      inner join im_akun_master d on b.master_id = d.nomor_master \n" +
                "\t  INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = b.rekening_id\n" +
                "      INNER JOIN (\n" +
                "\t  \tSELECT\n" +
                "\t\t  *\n" +
                "\t\tFROM\n" +
                "\t\t  im_akun_setting_aging_tipe_jurnal\n" +
                "\t\tWHERE\n" +
                "\t\t  tipe_aging='"+tipeAging+"' AND flag='Y'\n" +
                "\t  ) saj ON saj.tipe_jurnal_id=a.tipe_jurnal_id\n" +
                "      INNER JOIN (\n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          mt_kurs \n" +
                "        where \n" +
                "          flag = 'A'\n" +
                "      ) f on f.mata_uang_id = a.mata_uang_id \n" +
                "    where \n" +
                "      a.flag = 'Y' \n" +
                "      and a.registered_flag = 'Y'\n" +
                "  ) foo \n" +
                "where\n" +
                "  to_date(\n" +
                "    cast(tgljurnal as TEXT), \n" +
                "    'MM-yyyy'\n" +
                "  ) < (\n" +
                "    to_date('"+periode+"', 'MM-yyyy')+ Interval '1 month'\n" +
                "  ) \n" +
                "  "+tipeWhere+" \n" +
                "order by \n" +
                "  mastergrp, \n" +
                "  masterId, \n" +
                "  tglJurnal, \n" +
                "  masterId asc";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Aging data= new Aging();
            data.setKodeRekening((String) row[0]);
            data.setRekeningId((String) row[1]);
            data.setNoNota((String) row[2]);
            data.setTglJurnal((Date) row[3]);
            data.setMataUang((String) row[4]);
            data.setTotal(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            data.setMasterId((String) row[6]);
            data.setNamaMaster((String) row[7]);
            data.setMasterGrp(row[8].toString());
            data.setKurs(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
            listOfResult.add(data);
        }
        return listOfResult;
    }
}
