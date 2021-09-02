/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItHrisPayrollTempEntity;
import com.neurix.hris.transaksi.payroll.model.ReportPayroll;
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

public class PayrollTempDao extends GenericDao<ItHrisPayrollTempEntity, String> {

    @Override
    protected Class<ItHrisPayrollTempEntity> getEntityClass() {
        return ItHrisPayrollTempEntity.class;
    }

    @Override
    public List<ItHrisPayrollTempEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollTempEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("payroll_temp_id") != null) {
                criteria.add(Restrictions.eq("payrollTempId", (String) mapCriteria.get("payroll_temp_id")));
            }

            if (mapCriteria.get("payroll_header_id") != null) {
                criteria.add(Restrictions.eq("payrollHeaderId", (String) mapCriteria.get("payroll_header_id")));
            }

            if (mapCriteria.get("nip") != null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }

            if (mapCriteria.get("bulan") != null) {
                criteria.add(Restrictions.eq("bulan", (String) mapCriteria.get("bulan")));
            }

            if (mapCriteria.get("tahun") != null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }

            if (mapCriteria.get("tipe_payroll") != null) {
                criteria.add(Restrictions.eq("tipePayroll", (String) mapCriteria.get("tipe_payroll")));
            }

            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("payrollTempId"));

        List<ItHrisPayrollTempEntity> results= criteria.list();

        return results;
    }

    //updated by ferdi, 01-12-2020, to generated pph temp seq
    public String getNextPayrollTempId(String bulan, String tahun) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_temp')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = "PYRTEM" + bulan + per[2] + per[3] + sId;

        return hasil;
    }


    public ItHrisPayrollTempEntity getDataView(String payrollTempId) throws HibernateException {
        List<ItHrisPayrollTempEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollTempEntity.class)
                .add(Restrictions.eq("payrollTempId", payrollTempId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        ItHrisPayrollTempEntity resultItem = results.get(0);

        return resultItem;
    }

    //RAKA-11MEI2021===>detail rekap payroll excel
    public List<ReportPayroll> reportRekapPayroll(String bulan, String tahun, String unit){
        List<ReportPayroll> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        final String query = "select\n" +
                "       py.nip,\n" +            // 0
                "       py.nama_pegawai,\n" +   // 1
                "       py.golongan_name,\n" +  // 2
                "       py.npwp,\n" +           // 3
                "       case\n" +
                "         when py.tipe_pegawai_id = 'TP03' then concat(py.golongan_dapen, '/', py.masa_kerja_gol)\n" +
                "         else py.golongan_dapen\n" +
                "           end as golongan_pens,\n" +  // 4
                "       py.tipe_pegawai_name,\n" +      // 5
                "       cast('-' as varchar) as no_cek,\n" +     // 6
                "       concat(py.status_keluarga, '/', py.jumlah_anak) as status_keluarga,\n" +    // 7
                "       py.gaji_pokok,\n" +         // 8
                "       py.tunjungan_sankhus,\n" +  // 9
                "       py.tunjangan_jabatan_struktural as tunjangan_jabatan,\n" +  // 10
                "       py.tunjangan_struktural,\n" +   // 11
                "       py.tunjangan_fungsional,\n" +   // 12
                "       py.tunjangan_strategis as tunjangan_profesional,\n" +   // 13
                "       py.peralihan_gapok,\n" +        // 14
                "       py.peralihan_sankhus,\n" +      // 15
                "       py.peralihan_tunjangan,\n" +    // 16
                "       py.tunjangan_peralihan as tot_tunj_peralihan,\n" +  // 17
                "       0.0 as rpl_gaji,\n" +         // 18
                "       0.0 as rpl_sankhus,\n" +      // 19
                "       0.0 as rpl_tjjab,\n" +        // 20
                "       0.0 as rpl_tjstr,\n" +        // 21
                "       0.0 as rpl_tjfung,\n" +       // 22
                "       0.0 as rpl_tjprof,\n" +       // 23
                "       0.0 as rpl_tjalih,\n" +       // 24
                "       py.tunjangan_komunikasi,\n" +   // 25
                "       py.tunjangan_tambahan,\n" +     // 26
                "       (py.tunjangan_siaga + py.tunjangan_lokal + py.tunjangan_supervisi + py.tunjangan_pemondokan) as tot_tunj_lain,\n" + // 27
                "       py.tunjangan_lokal,\n" +    // 28
                "       absen.jam_lembur,\n" +       // 29
                "       absen.fak_lembur,\n" +       // 30
                "       0.0 as by_lembur,\n" +        // 31
                "       py.tunjangan_lembur,\n" +   // 32
                "       py.tunjangan_perumahan,\n" +    // 33
                "       py.tunjangan_listrik,\n" +  // 34
                "       py.tunjangan_air,\n" +      // 35
                "       py.tunjangan_bbm,\n" +      // 36
                "       0.0 as tunj_sos,\n" +         // 37
                "       (py.tunjangan_dapen + py.tunjangan_bpjs_tk +py.tunjangan_bpjs_ks) as tot_tunj_sos,\n" + // 38
                "       py.total_rlab,\n" +         // 39
                "       py.tunjangan_siaga,\n" +    // 40
                "       py.tunjangan_dapen,\n" +    // 41
                "       py.tunjangan_bpjs_tk,\n" +  // 42
                "       py.tunjangan_bpjs_ks,\n" +  // 43
                "       0.0 as pend_rutin,\n" +       // 44
                "       0.0 as pend_tdk_rutin,\n" +   // 45
                "       py.pph_gaji,\n" +   // 46
                "       0.0 as poypks,\n" +   // 47
                "       py.iuran_dapen_kary,\n" +   // 48
                "       py.iuran_dapen_pers,\n" +   // 49
                "       py.total_iuran_bpjs_tk_kary,\n" +   // 50
                "       py.total_iuran_bpjs_tk_pers,\n" +   // 51
                "       py.iuran_bpjs_ks_kary,\n" +     // 52
                "       py.iuran_bpjs_ks_pers,\n" +     // 53
                "       (py.iuran_kopkar + py.iuran_sp + py.iuran_piikb + py.iuran_bank_bri + py.iuran_bank_mandiri + py.iuran_infaq +\n" +
                "        py.iuran_perkes_dan_obat + py.iuran_listrik + py.iuran_potongan_lain) as tot_potongan_lain,\n" +   // 54
                "       cast('-' as varchar) as perkbayar,\n" +  // 55
                "       cast('-' as varchar) as klomperk,\n" +   // 56
                "       cast('-' as varchar) as kdpoli,\n" +     // 57
                "       cast('-' as varchar) as bkodeb,\n" +     // 58
                "       cast('-' as varchar) as pgol,\n" +       // 59
                "       cast('-' as varchar) as pruang,\n" +     // 60
                "       cast('-' as varchar) as pmasa,\n" +      // 61
                "       cast('-' as varchar) as noskdapen,\n" +  // 62
                "       cast('-' as varchar) as noskgaji,\n" +   // 63
                "       cast('-' as varchar) as noidbio,\n" +    // 64
                "       cast('-' as varchar) as prypks,\n" +     // 65
                "       cast('-' as varchar) as prdapenp,\n" +   // 66
                "       cast('-' as varchar) as prdapeng,\n" +   // 67
                "       cast('-' as varchar) as prbpjstp,\n" +   // 68
                "       cast('-' as varchar) as prbpjstg,\n" +   // 69
                "       cast('-' as varchar) as prbpjssp,\n" +   // 70
                "       cast('-' as varchar) as prbpjssg,\n" +   // 71
                "       0.0 as rpl_rlab,\n" +   // 72
                "       py.tanggal_awal_lembur,\n" +    // 73
                "       py.tanggal_akhir_lembur,\n" +   // 74
                "       py.department_id,\n" +      // 75
                "       py.department_name,\n" +    // 76
                "       dept.kodering as dep_kodering,\n" + // 77
                "       py.sub_divisi,\n" +         // 78
                "       py.sub_divisi_name,\n" +    // 79
                "       bag.kodering as bag_kodering,\n" +  // 80
                "       py.position_id,\n" +    // 81
                "       py.position_name,\n" +  // 82
                "       case \n" +
                "           when peg.nip_lama is null then cast('-' as varchar)\n" +
                "           when peg.nip_lama = '' then cast('-' as varchar)\n" +
                "           else peg.nip_lama \n" +
                "       end  as nip_lama, \n" + // 83
                "       peg.tipe_pegawai, \n" + // 84
                "       peg.golongan_id, \n" +  // 85
                "       py.tahun_skala_gaji \n" +  // 86
                "\n" +
                "from it_hris_payroll_header head\n" +
                "       left join it_hris_payroll_temp py on py.payroll_header_id = head.payroll_header_id\n" +
                "       left join it_hris_payroll_pph_temp pph on pph.payroll_temp_id = py.payroll_temp_id\n" +
                "       left join im_hris_department dept on dept.department_id = py.department_id\n" +
                "       left join im_hris_position_bagian bag on bag.bagian_id = py.sub_divisi\n" +
                "       left join\n" +
                "         (select\n" +
                "             ap.nip,\n" +
                "             sum(realisasi_jam_lembur) as jam_lembur,\n" +
                "             sum(jam_lembur) as fak_lembur\n" +
                "           from it_hris_absensi_pegawai ap\n" +
                "           left join it_hris_payroll_temp pay on pay.nip = ap.nip\n" +
                "           where ap.tanggal between pay.tanggal_awal_lembur and pay.tanggal_akhir_lembur\n" +
                "           and pay.bulan = '"+ bulan +"'\n" +
                "           and pay.tahun = '"+ tahun +"'\n" +
                "           group by ap.nip\n" +
                "        ) absen on absen.nip = py.nip \n" +
                "       left join im_hris_pegawai peg on peg.nip = py.nip \n" +
                "where head.bulan = '"+ bulan +"'\n" +
                "    and head.tahun = '"+ tahun +"'\n" +
                "    and head.branch_id = '"+ unit +"'\n" +
                "    and head.tipe_payroll = 'PY'\n" +
                "    and head.flag = 'Y'\n" +
                "    order by\n" +
                "    (  case\n" +
                "    when dept.department_id = 'D07' then 1\n" +
                "    else 2\n" +
                "    end\n" +
                "    ),\n" +
                "    py.position_id;";

        results = this.sessionFactory.getCurrentSession().createSQLQuery(query).list();
        for (Object[] row : results) {
            ReportPayroll result = new ReportPayroll();

            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setGolKary((String) row[2]);
            result.setNpwp((String) row[3]);
            result.setGolPens((String) row[4]);
            result.setStatPeg((String) row[5]);
            result.setNoCek((String) row[6]);
            result.setStatKeluarga((String) row[7]);
            result.setGaji((BigDecimal) row[8]);
            result.setSankhus((BigDecimal) row[9]);
            result.setTunjJab((BigDecimal) row[10]);
            result.setTunjStruk((BigDecimal) row[11]);
            result.setTunjFung((BigDecimal) row[12]);
            if(row[13] == null){
                result.setTunjProf(BigDecimal.ZERO);
            }else {
                result.setTunjProf((BigDecimal) row[13]);
            }
            result.setTjAlihGapok((BigDecimal) row[14]);
            result.setTjAlihSankhus((BigDecimal) row[15]);
            result.setTjAlihTunj((BigDecimal) row[16]);
            result.setTunjAlihTot((BigDecimal) row[17]);
            result.setRplGaji((BigDecimal) row[18]);
            result.setRplSankhus((BigDecimal) row[19]);
            result.setRplTunjJab((BigDecimal) row[20]);
            result.setRplTunjStr((BigDecimal) row[21]);
            result.setRplTunjFung((BigDecimal) row[22]);
            result.setRplTunjProf((BigDecimal) row[23]);
            result.setRplTunjAlih((BigDecimal) row[24]);
            result.setTunjKom((BigDecimal) row[25]);
            result.setTunjTbh((BigDecimal) row[26]);
            result.setTunjLain((BigDecimal) row[27]);
            result.setTunjLok((BigDecimal) row[28]);
            if(row[29] == null) {
                result.setJamLbr(BigDecimal.ZERO);
            }else {
                result.setJamLbr((BigDecimal) row[29]);
            }
            if(row[30] == null) {
                result.setfJamLbr(BigDecimal.ZERO);
            }else{
                result.setfJamLbr((BigDecimal) row[30]);
            }
            result.setByLembur((BigDecimal) row[31]);
            result.setUpahLembur((BigDecimal) row[32]);
            result.setTunjRmh((BigDecimal) row[33]);
            result.setTunjList((BigDecimal) row[34]);
            result.setTunjAir((BigDecimal) row[35]);
            result.setTunjBbm((BigDecimal) row[36]);
            result.setTunjSos((BigDecimal) row[37]);
            result.setRplRlab((BigDecimal) row[72]);
            result.setTunjSiaga((BigDecimal) row[40]);
            result.setTjPensiunPers((BigDecimal) row[41]);
            result.setTjBpjsTkPers((BigDecimal) row[42]);
            result.setTjBpjsKsPers((BigDecimal) row[43]);
            result.setPendRutin((BigDecimal) row[44]);
            result.setPendTdkRutin((BigDecimal) row[45]);
            result.setPoPph((BigDecimal) row[46]);
            result.setPoYpks((BigDecimal) row[47]);
            result.setIurPensiunPeg((BigDecimal) row[48]);
            result.setIurPensiunPers((BigDecimal) row[49]);
            result.setIurBpjsTkPeg((BigDecimal) row[50]);
            result.setIurBpjsTkPers((BigDecimal) row[51]);
            result.setIurBpjsKsPeg((BigDecimal) row[52]);
            result.setIurBpjsKsPers((BigDecimal) row[53]);
            result.setPoLain((BigDecimal) row[54]);
            result.setPerkbayar((String) row[55]);
            result.setKlomperk((String) row[56]);
            result.setKdpoli((String) row[57]);
            result.setBkodeb((String) row[58]);
            result.setPgol((String) row[59]);
            result.setPruang((String) row[60]);
            result.setPmasa((String) row[61]);
            result.setNoskdapen((String) row[62]);
            result.setNoskgaji((String) row[63]);
            result.setNoidbio((String) row[64]);
            result.setPrypks((String) row[65]);
            result.setPrdapenp((String) row[66]);
            result.setPrdapeng((String) row[67]);
            result.setPrbpjstp((String) row[68]);
            result.setPrbpjstg((String) row[69]);
            result.setPrbpjssp((String) row[70]);
            result.setPrbpjssg((String) row[71]);

            result.setTglAwalLbr((Date) row[73]);
            result.setTglAkhirLbr((Date) row[74]);

            result.setNipLama((String) row[83]);
            result.setTipePegawai(row[84]!=null?(String) row[84]:"-");
            result.setGolonganId(row[85]!=null?(String) row[85]:"-");
            result.setTahunSkalaGaji((String) row[86]);

            listOfResult.add(result);
        }
        return listOfResult;
    }
    //RAKA-end

}