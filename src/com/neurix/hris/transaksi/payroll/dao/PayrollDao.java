package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.payroll.model.*;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PayrollDao extends GenericDao<ItHrisPayrollEntity, String> {

    @Override
    protected Class<ItHrisPayrollEntity> getEntityClass() {
        return ItHrisPayrollEntity.class;
    }

    @Override
    public List<ItHrisPayrollEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("payroll_id")!=null) {
                criteria.add(Restrictions.eq("payrollId", (String) mapCriteria.get("payroll_id")));
            }

            if (mapCriteria.get("bulan")!=null) {
                criteria.add(Restrictions.eq("bulan", (String) mapCriteria.get("bulan")));
            }

            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("tipe_payroll")!=null) {
                criteria.add(Restrictions.eq("tipePayroll", (String) mapCriteria.get("tipe_payroll")));
            }

            if (mapCriteria.get("approval_flag")!=null) {
                criteria.add(Restrictions.eq("approvalFlag", (String) mapCriteria.get("approval_flag")));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }

            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }


        }

        // Order by
        criteria.addOrder(Order.desc("payrollId"));

        List<ItHrisPayrollEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPayrollId(String bulan, String tahun) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = bulan + per[2] + per[3];

        return "PYR" + hasil + sId;
    }

    //updated by ferdi, 01-12-2020, refresh initial data payroll bulanan (pendapatan rutin)
    public PegawaiPayroll refreshInitialDataPayrollBulanan(String nip,
                                                           String branchId,
                                                           String periodePayroll,
                                                           String tahunPayroll,
                                                           String tahunSkalaPayroll,
                                                           String tanggalAwalLembur,
                                                           String tanggalAkhirLembur) throws HibernateException {

        //get tanggal gaji -> default tanggal 25
        String tanggalGaji = CommonConstant.TANGGAL_GAJI + "-" + periodePayroll + "-" + tahunPayroll;
        List<PegawaiPayroll> listOfResult = new ArrayList<PegawaiPayroll>();

        List<Object[]> results = new ArrayList<Object[]>();

        String queryHeader = "" +
                "select\n" +
                "peg.*,\n" +
                "ptkp.nilai                                                                      as ptkp_pegawai\n" +
                "from\n" +
                "     (\n" ;
        String queryBod =
                "      select pegawai.nip                                                               as nip,\n" +
                "             pegawai.nip_lama                                                           as nip_lama,\n" +
                "             case\n" +
                "               when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "               else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "             pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "             pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "             branch.branch_name                                                         as branch_name,\n" +
                "             posisi.department_id                                                       as divisi,\n" +
                "             depart.department_name                                                     as divisi_name,\n" +
                "             posisi.bagian_id                                                           as sub_divisi,\n" +
                "             bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "             posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "             kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "             pegawai.golongan_id                                                        as golongan_id,\n" +
                "             golongan.golongan_id                                                       as golongan_name,\n" +
                "             ''                                                                         as golongan_dapen,\n" +
                "             0                                                                          as grade_level,\n" +
                "             pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "             pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "             tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "             pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "             case\n" +
                "               when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "               else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "             pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "             jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "             pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "             posisi.position_name                                                       as posisi_name,\n" +
                "             pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "             profesi.profesi_name                                                       as profesi_name,\n" +
                "             pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "             ''                                                                         as dana_pensiun_name,\n" +
                "             0                                                                          as persen_kary_dana_pensiun,\n" +
                "             0                                                                          as persen_pers_dana_pensiun,\n" +
                "             case when skala_gaji.gaji is null then 0 else skala_gaji.gaji end          as gaji_pokok,\n" +
                "             0                                                                          as santunan_khusus,\n" +
                "             case when skala_gaji.tunj_rumah is null then 0 else skala_gaji.tunj_rumah end as tunj_rumah,\n" +
                "             0                                                                          as tunj_listrik,\n" +
                "             0                                                                          as tunj_air,\n" +
                "             0                                                                          as tunj_bbm,\n" +
                "             0                                                                          as tunj_jabatan,\n" +
                "             0                                                                          as tunj_struktural,\n" +
                "             0                                                                          as tunj_fungsional,\n" +
                "             0                                                                          as tunj_tambahan,\n" +
                "             0                                                                          as gaji_pensiun,\n" +
                "             0                                                                          as iuran_pers_dapen,\n" +
                "             0                                                                          as iuran_kary_dapen,\n" +
                "             0                                                                          as tunj_peralihan_gapok,\n" +
                "             0                                                                          as tunj_peralihan_sankhus,\n" +
                "             0                                                                          as tunj_peralihan_tunjangan,\n" +
                "             case\n" +
                "               when skala_gaji.tunj_telekomunikasi is null then 0\n" +
                "               else skala_gaji.tunj_telekomunikasi end                                  as tunj_komunikasi,\n" +
                "             0                                                                          as tunj_pemondokan,\n" +
                "             0                                                                          as tunj_lembur,\n" +
                "             0                                                                          as tunj_supervisi,\n" +
                "             0                                                                          as tunj_lokal,\n" +
                "             0                                                                          as tunj_siaga,\n" +
                "             param_bpjs.min_bpjs_ks,\n" +
                "             param_bpjs.max_bpjs_ks,\n" +
                "             param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "             param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "             param_bpjs.min_bpjs_tk,\n" +
                "             param_bpjs.max_bpjs_tk,\n" +
                "             param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "             param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "             param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "             param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "             param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "             param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "             pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "             case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "             case\n" +
                "               when pegawai.jumlah_anak is not null then\n" +
                "                 case\n" +
                "                   when pegawai.jumlah_anak > 3 then 3\n" +
                "                   else pegawai.jumlah_anak end\n" +
                "               else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                "             pegawai.npwp                                                               as npwp,\n" +
                "             pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "             pegawai.nama_bank                                                          as nama_bank,\n" +
                "             pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "             pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "             pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                "             pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                "             pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "             posisi.kodering                                                            as kodering,\n" +
                "             pegawai.jenis_kelamin                                                      as gender,\n" +
                "             pegawai.no_bpjs_kesehatan                                                 as no_bpjs_kes,\n" +
                "             pegawai.no_bpjs_ketenagakerjaan                                           as no_bpjs_tk,\n" +
                "             branch.umr                                                                as umr,\n" +
                "             1                                                                          as multifikator\n" +
                "      from im_hris_pegawai pegawai\n" +
                "             left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id\n" +
                "                                                      and golongan.flag = 'Y'\n" +
                "             left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "             left join it_hris_pegawai_position pegawai_posisi\n" +
                "               on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                "                  pegawai_posisi.flag_digaji = 'Y'\n" +
                "             left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "               on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "             left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "             left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "             left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "             left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "             left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "             left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "             left join im_hris_payroll_skala_gaji_bod skala_gaji\n" +
                "               on skala_gaji.position_id = pegawai_posisi.position_id and skala_gaji.flag = 'Y' and\n" +
                "                  skala_gaji.tahun = :tahunSkalaPayroll\n" +
                "             left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = :branchId and param_bpjs.flag = 'Y'\n" +
                "      where pegawai.flag = 'Y'\n" +
                "        and pegawai.tipe_pegawai = 'TP02'\n" + //RAKA-update tipe pegawai
                "        and pegawai_posisi.branch_id = :branchId\n" +
                "      union\n" ;

        String queryPegTetap =
                "      select pegawai.nip                                                                as nip,\n" +
                "             pegawai.nip_lama                                                           as nip_lama,\n" +
                "             case\n" +
                "               when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "               else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "             pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "             pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "             branch.branch_name                                                         as branch_name,\n" +
                "             posisi.department_id                                                       as divisi,\n" +
                "             depart.department_name                                                     as divisi_name,\n" +
                "             posisi.bagian_id                                                           as sub_divisi,\n" +
                "             bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "             posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "             kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "             pegawai.golongan_id                                                        as golongan_id,\n" +
                "             golongan.golongan_name                                                     as golongan_name,\n" +
                "             golongan.golongan_pensiun                                                  as golongan_dapen,\n" +
                "             golongan.grade_level                                                       as grade_level,\n" +
                "             pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "             pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "             tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "             pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "             case\n" +
                "               when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "               else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "             pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "             jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "             pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "             posisi.position_name                                                       as posisi_name,\n" +
                "             pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "             profesi.profesi_name                                                       as profesi_name,\n" +
                "             pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "             dana_pensiun.dana_pensiun                                                  as dana_pensiun_name,\n" +
                "             dana_pensiun.persentase_kary                                               as persen_kary_dana_pensiun,\n" +
                "             dana_pensiun.persentase_pers                                               as persen_pers_dana_pensiun,\n" +
                "             case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                "             case\n" +
                "               when skala_gaji.santunan_khusus is null then 0\n" +
                "               else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                "             case\n" +
                "               when pegawai.flag_tunj_rumah = 'Y' then\n" +
                "                 case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                "               else 0 end                                                                as tunj_rumah,\n" +
                "             case\n" +
                "               when pegawai.flag_tunj_listrik = 'Y' then\n" +
                "                 case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                "               else 0 end                                                               as tunj_listrik,\n" +
                "             case\n" +
                "               when pegawai.flag_tunj_air = 'Y' then\n" +
                "                 case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                "               else 0 end                                                               as tunj_air,\n" +
                "             case\n" +
                "               when pegawai.flag_tunj_bbm = 'Y' then\n" +
                "                 case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                "               else 0 end                                                               as tunj_bbm,\n" +
                "             case\n" +
                "               when tunj_struktural.tunj_jabatan is null then 0\n" +
                "               else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                "             case\n" +
                "               when tunj_struktural.tunj_struktural is null then 0\n" +
                "               else tunj_struktural.tunj_struktural end                                 as tunj_struktural,\n" +
                "             case\n" +
                "               when tunj_strategis.nilai is null then 0\n" +
                "               else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                "             0                                                                          as tunj_tambahan,\n" +
                "             case\n" +
                "               when skala_gaji_pensiun.nilai is null then 0\n" +
                "               else skala_gaji_pensiun.nilai end                                        as gaji_pensiun,\n" +
                "             case\n" +
                "               when pegawai.dana_pensiun is null or pegawai.dana_pensiun = '' then 0\n" +
                "               else (skala_gaji_pensiun.nilai * dana_pensiun.persentase_pers) / 100 end as iuran_pers_dapen,\n" +
                "             case\n" +
                "               when pegawai.dana_pensiun = 'DP01' then case\n" +
                "                                                         when skala_gaji_dplk_pegawai.nilai is null then 0\n" +
                "                                                         else skala_gaji_dplk_pegawai.nilai end\n" +
                "               else case\n" +
                "                      when pegawai.dana_pensiun = 'DP02'\n" +
                "                              then (skala_gaji_pensiun.nilai * dana_pensiun.persentase_kary) / 100\n" +
                "                      else 0 end end                                                    as iuran_kary_dapen,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                "                              then tunj_lainnya.tunj_peralihan_gapok\n" +
                "                         else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                "                              then tunj_lainnya.tunj_peralihan_sankhus\n" +
                "                         else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                "                              then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                "                         else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                "             case\n" +
                "               when kelompok.tunj_telekomunikasi is null then 0\n" +
                "               else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                "                              then tunj_lainnya.tunj_pemondokan\n" +
                "                         else 0 end end                                                 as tunj_pemondokan,\n" +
                "             case\n" +
                "               when absensi_lembur.tunj_lembur is null then 0\n" +
                "               else absensi_lembur.tunj_lembur end                                      as tunj_lembur,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                "                              then tunj_lainnya.tunj_supervisi\n" +
                "                         else 0 end end                                                  as tunj_supervisi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                "                              then tunj_lainnya.tunj_lokasi\n" +
                "                         else 0 end end                                                  as tunj_lokasi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                "                              then tunj_lainnya.tunj_siaga\n" +
                "                         else 0 end end                                                  as tunj_siaga,\n" +
                "             param_bpjs.min_bpjs_ks,\n" +
                "             param_bpjs.max_bpjs_ks,\n" +
                "             param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "             param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "             param_bpjs.min_bpjs_tk,\n" +
                "             param_bpjs.max_bpjs_tk,\n" +
                "             param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "             param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "             param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "             param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "             param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "             param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "             pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "             case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "             case\n" +
                "               when pegawai.jumlah_anak is not null then\n" +
                "                 case\n" +
                "                   when pegawai.jumlah_anak > 3 then 3\n" +
                "                   else pegawai.jumlah_anak end\n" +
                "               else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                "             pegawai.npwp                                                               as npwp,\n" +
                "             pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "             pegawai.nama_bank                                                          as nama_bank,\n" +
                "             pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "             pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "             pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                "             pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                "             pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "             posisi.kodering                                                            as kodering,\n" +
                "             pegawai.jenis_kelamin                                                      as gender,\n" +
                "             pegawai.no_bpjs_kesehatan                                                  as no_bpjs_kes,\n" +
                "             pegawai.no_bpjs_ketenagakerjaan                                            as no_bpjs_tk,\n" +
                "             branch.umr                                                                 as umr,\n" +
                "             1                                                                          as multifikator\n" +
                "      from im_hris_pegawai pegawai\n" +
                "             left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                "                                                    golongan.flag = 'Y'\n" +
                "             left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "             left join it_hris_pegawai_position pegawai_posisi\n" +
                "               on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                "                  pegawai_posisi.flag_digaji = 'Y'\n" +
                "             left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "               on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "             left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "             left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "             left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "             left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "             left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "             left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "             left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                "               on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                "                  tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                "             left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                "               on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                "             left join im_hris_payroll_skala_gaji skala_gaji\n" +
                "               on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                "                  skala_gaji.tahun = :tahunSkalaPayroll\n" +
                "             left join im_hris_payroll_dana_pensiun dana_pensiun\n" +
                "               on dana_pensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "             left join im_hris_payroll_skala_gaji_pensiun skala_gaji_pensiun\n" +
                "               on skala_gaji_pensiun.tipe_dapen_id = pegawai.dana_pensiun and\n" +
                "                  skala_gaji_pensiun.golongan_id = pegawai.golongan_id and\n" +
                "                  skala_gaji_pensiun.masa_kerja_gol = pegawai.masa_kerja_gol\n" +
                "             left join im_hris_payroll_skala_gaji_dplk_pegawai skala_gaji_dplk_pegawai\n" +
                "               on skala_gaji_dplk_pegawai.golongan_id = pegawai.golongan_id\n" +
                "             left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = :branchId and param_bpjs.flag = 'Y'\n" +
                "             left join (select nip, sum(biaya_lembur) as tunj_lembur\n" +
                "                        from it_hris_absensi_pegawai\n" +
                "                        where tanggal between to_date(:tglAwalLembur, 'DD-MM-YYYY') and to_date(:tglAkhirLembur, 'DD-MM-YYYY') and flag = 'Y'\n" +
                "                        group by nip) absensi_lembur on absensi_lembur.nip = pegawai.nip\n" +
                "             left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                "      where pegawai.flag = 'Y'\n" +
                "        and to_date(:tanggalGaji, 'DD-MM-YYYY') <= pegawai.tanggal_pensiun\n" +
                "        and (pegawai.flag_pegawai_cuti_diluar_tanggungan = 'N' or pegawai.flag_pegawai_cuti_diluar_tanggungan is null)\n" +
                "        and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                "        and pegawai_posisi.branch_id = :branchId\n" +
                "      union\n" ;

        String queryPkwt =
                "      select pegawai.nip                                                                as nip,\n" +
                "             pegawai.nip_lama                                                           as nip_lama,\n" +
                "             case\n" +
                "               when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "               else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "             pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "             pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "             branch.branch_name                                                         as branch_name,\n" +
                "             posisi.department_id                                                       as divisi,\n" +
                "             depart.department_name                                                     as divisi_name,\n" +
                "             posisi.bagian_id                                                           as sub_divisi,\n" +
                "             bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "             posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "             kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "             pegawai.golongan_id                                                        as golongan_id,\n" +
                "             golongan.golongan_pkwt_id                                                  as golongan_name,\n" +
                "             ''                                                                         as golongan_dapen,\n" +
                "             0                                                                          as grade_level,\n" +
                "             pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "             pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "             tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "             pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "             case\n" +
                "               when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "               else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "             pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "             jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "             pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "             posisi.position_name                                                       as posisi_name,\n" +
                "             pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "             profesi.profesi_name                                                       as profesi_name,\n" +
                "             pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "             ''                                                                         as dana_pensiun_name,\n" +
                "             0                                                                          as persen_kary_dana_pensiun,\n" +
                "             0                                                                          as persen_pers_dana_pensiun,\n" +
                "             case when skala_gaji.gaji_pokok is null then 0 else skala_gaji.gaji_pokok end\n" +
                "                                                                                        as gaji_pokok,\n" +
                "             case\n" +
                "               when skala_gaji.santunan_khusus is null then 0\n" +
                "               else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                "             0                                                                          as tunj_rumah,\n" +
                "             0                                                                          as tunj_listrik,\n" +
                "             0                                                                          as tunj_air,\n" +
                "             0                                                                          as tunj_bbm,\n" +
                "             0                                                                          as tunj_jabatan,\n" +
                "             0                                                                          as tunj_struktural,\n" +
                "             case\n" +
                "               when skala_gaji.tunj_fungsional is null then 0\n" +
                "               else skala_gaji.tunj_fungsional end                                      as tunj_fungsional,\n" +
                "             case\n" +
                "               when skala_gaji.tunj_tambahan is null then 0\n" +
                "               else skala_gaji.tunj_tambahan end                                        as tunj_tambahan,\n" +
                "\n" +
                "             0                                                                          as gaji_pensiun,\n" +
                "             0                                                                          as iuran_pers_dapen,\n" +
                "             0                                                                          as iuran_kary_dapen,\n" +
                "             0                                                                          as tunj_peralihan_gapok,\n" +
                "             0                                                                          as tunj_peralihan_sankhus,\n" +
                "             0                                                                          as tunj_peralihan_tunjangan,\n" +
                "             0                                                                          as tunj_komunikasi,\n" +
                "             0                                                                          as tunj_pemondokan,\n" +
                "             case\n" +
                "               when absensi_lembur.tunj_lembur is null then 0\n" +
                "               else absensi_lembur.tunj_lembur end                                      as tunj_lembur,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                "                              then tunj_lainnya.tunj_supervisi\n" +
                "                         else 0 end end                                                  as tunj_supervisi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                "                              then tunj_lainnya.tunj_lokasi\n" +
                "                         else 0 end end                                                  as tunj_lokasi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                "                              then tunj_lainnya.tunj_siaga\n" +
                "                         else 0 end end                                                  as tunj_siaga,\n" +
                "             param_bpjs.min_bpjs_ks,\n" +
                "             param_bpjs.max_bpjs_ks,\n" +
                "             param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "             param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "             param_bpjs.min_bpjs_tk,\n" +
                "             param_bpjs.max_bpjs_tk,\n" +
                "             param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "             param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "             param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "             param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "             param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "             param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "             pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "             case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "             case\n" +
                "               when pegawai.jumlah_anak is not null then\n" +
                "                 case\n" +
                "                   when pegawai.jumlah_anak > 3 then 3\n" +
                "                   else pegawai.jumlah_anak end\n" +
                "               else 0 end                                                               as jumlah_anak_ditanggung,\n" +
                "             pegawai.npwp                                                               as npwp,\n" +
                "             pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "             pegawai.nama_bank                                                          as nama_bank,\n" +
                "             pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "             pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "             pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                "             pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                "             pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "             posisi.kodering                                                            as kodering,\n" +
                "             pegawai.jenis_kelamin                                                      as gender,\n" +
                "             pegawai.no_bpjs_kesehatan                                                  as no_bpjs_kes,\n" +
                "             pegawai.no_bpjs_ketenagakerjaan                                            as no_bpjs_tk,\n" +
                "             branch.umr                                                                 as umr,\n" +
                "             case\n" +
                "               when trim(to_char(extract (month from pegawai.tanggal_masuk),'00')) = :periodePayroll \n" +
                "                      and trim(to_char(extract (year from pegawai.tanggal_masuk),'0000')) = :tahunPayroll  then\n" +
                "                 round(((to_date(to_char(to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') + INTERVAL '1 MONTH - 1 day','DD-MM-YYYY'),'DD-MM-YYYY')) - pegawai.tanggal_masuk + 1)  / cast(((to_date(to_char(to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') + INTERVAL '1 MONTH - 1 day','DD-MM-YYYY'),'DD-MM-YYYY') - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')) + 1) as decimal),2)\n" +
                "               else 1 end                                                               as multifikator\n" +
                "\n" +
                "      from im_hris_pegawai pegawai\n" +
                "             left join im_hris_golongan_pkwt golongan on pegawai.golongan_id = golongan.golongan_pkwt_id\n" +
                "                                                           and golongan.flag = 'Y'\n" +
                "             left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "             left join it_hris_pegawai_position pegawai_posisi\n" +
                "               on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                "                  pegawai_posisi.flag_digaji = 'Y'\n" +
                "             left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "               on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "             left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "             left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "             left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "             left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "             left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "             left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "             left join im_hris_payroll_skala_gaji_pkwt skala_gaji\n" +
                "               on skala_gaji.golongan_pkwt_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                "                  skala_gaji.tahun = :tahunSkalaPayroll \n" +
                "             left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = :branchId and param_bpjs.flag = 'Y'\n" +
                "             left join (select nip, sum(biaya_lembur) as tunj_lembur\n" +
                "                        from it_hris_absensi_pegawai\n" +
                "                        where tanggal between to_date(:tglAwalLembur, 'DD-MM-YYYY') and to_date(:tglAkhirLembur, 'DD-MM-YYYY') and flag = 'Y'\n" +
                "                        group by nip) absensi_lembur on absensi_lembur.nip = pegawai.nip\n" +
                "             left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                "      where pegawai.flag = 'Y'\n" +
                "        and pegawai.tipe_pegawai = 'TP04'\n" + //RAKA-update tipe pegawai
                "        and pegawai_posisi.branch_id = :branchId\n" +
                "      union\n" ;

        String queryPegTetapResign =
                //pegawai tetap yg exception ( resign )
                "      select pegawai.nip                                                                as nip,\n" +
                "             pegawai.nip_lama                                                           as nip_lama,\n" +
                "             case\n" +
                "               when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "               else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "             pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "             pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "             branch.branch_name                                                         as branch_name,\n" +
                "             posisi.department_id                                                       as divisi,\n" +
                "             depart.department_name                                                     as divisi_name,\n" +
                "             posisi.bagian_id                                                           as sub_divisi,\n" +
                "             bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "             posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "             kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "             pegawai.golongan_id                                                        as golongan_id,\n" +
                "             golongan.golongan_name                                                     as golongan_name,\n" +
                "             golongan.golongan_pensiun                                                  as golongan_dapen,\n" +
                "             golongan.grade_level                                                       as grade_level,\n" +
                "             pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "             pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "             tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "             pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "             case\n" +
                "               when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "               else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "             pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "             jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "             pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "             posisi.position_name                                                       as posisi_name,\n" +
                "             pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "             profesi.profesi_name                                                       as profesi_name,\n" +
                "             pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "             dana_pensiun.dana_pensiun                                                  as dana_pensiun_name,\n" +
                "             dana_pensiun.persentase_kary                                               as persen_kary_dana_pensiun,\n" +
                "             dana_pensiun.persentase_pers                                               as persen_pers_dana_pensiun,\n" +
                "             case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                "             case\n" +
                "               when skala_gaji.santunan_khusus is null then 0\n" +
                "               else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                "             case\n" +
                "               when pegawai.flag_tunj_rumah = 'Y' then\n" +
                "                 case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                "               else 0 end                                                                as tunj_rumah,\n" +
                "             case\n" +
                "               when pegawai.flag_tunj_listrik = 'Y' then\n" +
                "                 case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                "               else 0 end                                                               as tunj_listrik,\n" +
                "             case\n" +
                "               when pegawai.flag_tunj_air = 'Y' then\n" +
                "                 case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                "               else 0 end                                                               as tunj_air,\n" +
                "             case\n" +
                "               when pegawai.flag_tunj_bbm = 'Y' then\n" +
                "                 case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                "               else 0 end                                                               as tunj_bbm,\n" +
                "             case\n" +
                "               when tunj_struktural.tunj_jabatan is null then 0\n" +
                "               else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                "             case\n" +
                "               when tunj_struktural.tunj_struktural is null then 0\n" +
                "               else tunj_struktural.tunj_struktural end                                 as tunj_struktural,\n" +
                "             case\n" +
                "               when tunj_strategis.nilai is null then 0\n" +
                "               else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                "             0                                                                          as tunj_tambahan,\n" +
                "             case\n" +
                "               when skala_gaji_pensiun.nilai is null then 0\n" +
                "               else skala_gaji_pensiun.nilai end                                        as gaji_pensiun,\n" +
                "             case\n" +
                "               when pegawai.dana_pensiun is null or pegawai.dana_pensiun = '' then 0\n" +
                "               else (skala_gaji_pensiun.nilai * dana_pensiun.persentase_pers) / 100 end as iuran_pers_dapen,\n" +
                "             case\n" +
                "               when pegawai.dana_pensiun = 'DP01' then case\n" +
                "                                                         when skala_gaji_dplk_pegawai.nilai is null then 0\n" +
                "                                                         else skala_gaji_dplk_pegawai.nilai end\n" +
                "               else case\n" +
                "                      when pegawai.dana_pensiun = 'DP02'\n" +
                "                              then (skala_gaji_pensiun.nilai * dana_pensiun.persentase_kary) / 100\n" +
                "                      else 0 end end                                                    as iuran_kary_dapen,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                "                              then tunj_lainnya.tunj_peralihan_gapok\n" +
                "                         else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                "                              then tunj_lainnya.tunj_peralihan_sankhus\n" +
                "                         else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                "                              then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                "                         else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                "             case\n" +
                "               when kelompok.tunj_telekomunikasi is null then 0\n" +
                "               else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                "                              then tunj_lainnya.tunj_pemondokan\n" +
                "                         else 0 end end                                                 as tunj_pemondokan,\n" +
                "             case\n" +
                "               when absensi_lembur.tunj_lembur is null then 0\n" +
                "               else absensi_lembur.tunj_lembur end                                      as tunj_lembur,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                "                              then tunj_lainnya.tunj_supervisi\n" +
                "                         else 0 end end                                                  as tunj_supervisi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                "                              then tunj_lainnya.tunj_lokasi\n" +
                "                         else 0 end end                                                  as tunj_lokasi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                "                              then tunj_lainnya.tunj_siaga\n" +
                "                         else 0 end end                                                  as tunj_siaga,\n" +
                "             param_bpjs.min_bpjs_ks,\n" +
                "             param_bpjs.max_bpjs_ks,\n" +
                "             param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "             param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "             param_bpjs.min_bpjs_tk,\n" +
                "             param_bpjs.max_bpjs_tk,\n" +
                "             param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "             param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "             param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "             param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "             param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "             param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "             pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "             case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "             case\n" +
                "               when pegawai.jumlah_anak is not null then\n" +
                "                 case\n" +
                "                   when pegawai.jumlah_anak > 3 then 3\n" +
                "                   else pegawai.jumlah_anak end\n" +
                "               else 0 end                                                               as jumlah_anak_ditanggung,\n" +
                "             pegawai.npwp                                                               as npwp,\n" +
                "             pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "             pegawai.nama_bank                                                          as nama_bank,\n" +
                "             pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "             pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "             pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                "             pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                "             pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "             posisi.kodering                                                            as kodering,\n" +
                "             pegawai.jenis_kelamin                                                      as gender,\n" +
                "             pegawai.no_bpjs_kesehatan                                                  as no_bpjs_kes,\n" +
                "             pegawai.no_bpjs_ketenagakerjaan                                            as no_bpjs_tk,\n" +
                "             branch.umr                                                                 as umr,\n" +
                "             case\n" +
                "               when trim(to_char(extract (month from pegawai.tanggal_keluar),'00')) = :periodePayroll \n" +
                "                      and trim(to_char(extract (year from pegawai.tanggal_keluar),'0000')) = :tahunPayroll  then\n" +
                "                 round((pegawai.tanggal_keluar - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')  + 1)  / cast(((to_date(to_char(to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') + INTERVAL '1 MONTH - 1 day','DD-MM-YYYY'),'DD-MM-YYYY') - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')) + 1) as decimal),2)\n" +
                "               else 1 end                                                               as multifikator\n" +
                "\n" +
                "      from im_hris_pegawai pegawai\n" +
                "             left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                "                                                    golongan.flag = 'Y'\n" +
                "             left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "             left join it_hris_pegawai_position pegawai_posisi\n" +
                "               on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'N' and\n" +
                "                  pegawai_posisi.flag_digaji = 'Y'\n" +
                "             left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "               on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "             left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "             left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "             left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "             left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "             left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "             left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "             left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                "               on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                "                  tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                "             left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                "               on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                "             left join im_hris_payroll_skala_gaji skala_gaji\n" +
                "               on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                "                  skala_gaji.tahun = :tahunSkalaPayroll\n" +
                "             left join im_hris_payroll_dana_pensiun dana_pensiun\n" +
                "               on dana_pensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "             left join im_hris_payroll_skala_gaji_pensiun skala_gaji_pensiun\n" +
                "               on skala_gaji_pensiun.tipe_dapen_id = pegawai.dana_pensiun and\n" +
                "                  skala_gaji_pensiun.golongan_id = pegawai.golongan_id and\n" +
                "                  skala_gaji_pensiun.masa_kerja_gol = pegawai.masa_kerja_gol\n" +
                "             left join im_hris_payroll_skala_gaji_dplk_pegawai skala_gaji_dplk_pegawai\n" +
                "               on skala_gaji_dplk_pegawai.golongan_id = pegawai.golongan_id\n" +
                "             left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = '01' and param_bpjs.flag = 'Y'\n" +
                "             left join (select nip, sum(biaya_lembur) as tunj_lembur\n" +
                "                        from it_hris_absensi_pegawai\n" +
                "                        where tanggal between to_date(:tglAwalLembur, 'DD-MM-YYYY') and to_date(:tglAkhirLembur, 'DD-MM-YYYY') and flag = 'Y'\n" +
                "                        group by nip) absensi_lembur on absensi_lembur.nip = pegawai.nip\n" +
                "             left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                "      where (pegawai.flag = 'N' and cast(extract(month from pegawai.tanggal_keluar) as varchar)  = :periodePayroll and cast(extract(year from pegawai.tanggal_keluar) as varchar)  = :tahunPayroll) \n" +
                "        and to_date(:tanggalGaji, 'DD-MM-YYYY') <= pegawai.tanggal_pensiun\n" +
                "        and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                "        and pegawai_posisi.branch_id = :branchId \n" +
                "      union\n" ;

        String queryPkwtResign =
                //pkwt yang exception (resign)
                "      select pegawai.nip                                                                as nip,\n" +
                "             pegawai.nip_lama                                                           as nip_lama,\n" +
                "             case\n" +
                "               when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "               else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "             pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "             pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "             branch.branch_name                                                         as branch_name,\n" +
                "             posisi.department_id                                                       as divisi,\n" +
                "             depart.department_name                                                     as divisi_name,\n" +
                "             posisi.bagian_id                                                           as sub_divisi,\n" +
                "             bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "             posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "             kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "             pegawai.golongan_id                                                        as golongan_id,\n" +
                "             golongan.golongan_pkwt_id                                                  as golongan_name,\n" +
                "             ''                                                                         as golongan_dapen,\n" +
                "             0                                                                          as grade_level,\n" +
                "             pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "             pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "             tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "             pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "             case\n" +
                "               when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "               else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "             pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "             jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "             pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "             posisi.position_name                                                       as posisi_name,\n" +
                "             pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "             profesi.profesi_name                                                       as profesi_name,\n" +
                "             pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "             ''                                                                         as dana_pensiun_name,\n" +
                "             0                                                                          as persen_kary_dana_pensiun,\n" +
                "             0                                                                          as persen_pers_dana_pensiun,\n" +
                "             case when skala_gaji.gaji_pokok is null then 0 else skala_gaji.gaji_pokok end\n" +
                "                                                                                        as gaji_pokok,\n" +
                "             case\n" +
                "               when skala_gaji.santunan_khusus is null then 0\n" +
                "               else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                "             0                                                                          as tunj_rumah,\n" +
                "             0                                                                          as tunj_listrik,\n" +
                "             0                                                                          as tunj_air,\n" +
                "             0                                                                          as tunj_bbm,\n" +
                "             0                                                                          as tunj_jabatan,\n" +
                "             0                                                                          as tunj_struktural,\n" +
                "             case\n" +
                "               when skala_gaji.tunj_fungsional is null then 0\n" +
                "               else skala_gaji.tunj_fungsional end                                      as tunj_fungsional,\n" +
                "             case\n" +
                "               when skala_gaji.tunj_tambahan is null then 0\n" +
                "               else skala_gaji.tunj_tambahan end                                        as tunj_tambahan,\n" +
                "\n" +
                "             0                                                                          as gaji_pensiun,\n" +
                "             0                                                                          as iuran_pers_dapen,\n" +
                "             0                                                                          as iuran_kary_dapen,\n" +
                "             0                                                                          as tunj_peralihan_gapok,\n" +
                "             0                                                                          as tunj_peralihan_sankhus,\n" +
                "             0                                                                          as tunj_peralihan_tunjangan,\n" +
                "             0                                                                          as tunj_komunikasi,\n" +
                "             0                                                                          as tunj_pemondokan,\n" +
                "             case\n" +
                "               when absensi_lembur.tunj_lembur is null then 0\n" +
                "               else absensi_lembur.tunj_lembur end                                      as tunj_lembur,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                "                              then tunj_lainnya.tunj_supervisi\n" +
                "                         else 0 end end                                                  as tunj_supervisi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                "                              then tunj_lainnya.tunj_lokasi\n" +
                "                         else 0 end end                                                  as tunj_lokasi,\n" +
                "             case\n" +
                "               when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                "               else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                "                              then tunj_lainnya.tunj_siaga\n" +
                "                         else 0 end end                                                  as tunj_siaga,\n" +
                "             param_bpjs.min_bpjs_ks,\n" +
                "             param_bpjs.max_bpjs_ks,\n" +
                "             param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "             param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "             param_bpjs.min_bpjs_tk,\n" +
                "             param_bpjs.max_bpjs_tk,\n" +
                "             param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "             param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "             param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "             param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "             param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "             param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "             pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "             case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "             case\n" +
                "               when pegawai.jumlah_anak is not null then\n" +
                "                 case\n" +
                "                   when pegawai.jumlah_anak > 3 then 3\n" +
                "                   else pegawai.jumlah_anak end\n" +
                "               else 0 end                                                               as jumlah_anak_ditanggung,\n" +
                "             pegawai.npwp                                                               as npwp,\n" +
                "             pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "             pegawai.nama_bank                                                          as nama_bank,\n" +
                "             pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "             pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "             pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                "             pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                "             pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "             posisi.kodering                                                            as kodering,\n" +
                "             pegawai.jenis_kelamin                                                      as gender,\n" +
                "             pegawai.no_bpjs_kesehatan                                                  as no_bpjs_kes,\n" +
                "             pegawai.no_bpjs_ketenagakerjaan                                            as no_bpjs_tk,\n" +
                "             branch.umr                                                                 as umr,\n" +
                "             case\n" +
                "               when trim(to_char(extract (month from pegawai.tanggal_keluar),'00')) = :periodePayroll\n" +
                "                      and trim(to_char(extract (year from pegawai.tanggal_keluar),'0000')) = :tahunPayroll  then\n" +
                "                 round((pegawai.tanggal_keluar - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')  + 1)  / cast(((to_date(to_char(to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') + INTERVAL '1 MONTH - 1 day','DD-MM-YYYY'),'DD-MM-YYYY') - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')) + 1) as decimal),2)\n" +
                "               else 1 end                                                               as multifikator\n" +
                "\n" +
                "      from im_hris_pegawai pegawai\n" +
                "             left join im_hris_golongan_pkwt golongan on pegawai.golongan_id = golongan.golongan_pkwt_id\n" +
                "                                                           and golongan.flag = 'Y'\n" +
                "             left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "             left join it_hris_pegawai_position pegawai_posisi\n" +
                "               on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'N' and\n" +
                "                  pegawai_posisi.flag_digaji = 'Y'\n" +
                "             left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "               on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "             left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "             left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "             left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "             left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "             left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "             left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "             left join im_hris_payroll_skala_gaji_pkwt skala_gaji\n" +
                "               on skala_gaji.golongan_pkwt_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                "                  skala_gaji.tahun = :tahunSkalaPayroll \n" +
                "             left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = :branchId and param_bpjs.flag = 'Y'\n" +
                "             left join (select nip, sum(biaya_lembur) as tunj_lembur\n" +
                "                        from it_hris_absensi_pegawai\n" +
                "                        where tanggal between to_date(:tglAwalLembur, 'DD-MM-YYYY') and to_date(:tglAkhirLembur, 'DD-MM-YYYY') and flag = 'Y'\n" +
                "                        group by nip) absensi_lembur on absensi_lembur.nip = pegawai.nip\n" +
                "             left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                "      where (pegawai.flag = 'N' and cast(extract(month from pegawai.tanggal_keluar) as varchar)  = :periodePayroll and cast(extract(year from pegawai.tanggal_keluar) as varchar)  = :tahunPayroll) \n" +
                "        and pegawai.tipe_pegawai = 'TP04'\n" + //RAKA-update tipe pegawai
                "        and pegawai_posisi.branch_id = :branchId\n" +
                "      order by status_pegawai desc\n" +
                "    ) peg\n" +
                "    left join im_hris_payroll_ptkp ptkp on ptkp.status_keluarga = peg.status_keluarga\n" +
                "    and ptkp.jumlah_tanggungan = peg.jumlah_anak_ditanggung and ptkp.flag = 'Y'\n" +
                "    where nip = :nip" ;

        String query = org.apache.commons.lang.StringUtils.join(new String[]{queryHeader,queryBod,queryPegTetap,queryPkwt,queryPegTetapResign,queryPkwtResign});


        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("branchId", branchId)
                .setParameter("periodePayroll", periodePayroll)
                .setParameter("tahunPayroll", tahunPayroll)
                .setParameter("tanggalGaji", tanggalGaji)
                .setParameter("tahunSkalaPayroll", tahunSkalaPayroll)
                .setParameter("tglAwalLembur", tanggalAwalLembur)
                .setParameter("tglAkhirLembur", tanggalAkhirLembur)
                .setParameter("nip", nip)
                .list();

        for (Object[] row : results) {
            PegawaiPayroll result  = new PegawaiPayroll();

            result.setTipePayroll(CommonConstant.CODE_PAYROLL);

            //set periode,tahun,dan skala gaji, dan tanggal awal dan akhir lembur
            result.setPeriodePayroll(periodePayroll);
            result.setTahunPayroll(tahunPayroll);
            result.setTahunSkalaGaji(tahunSkalaPayroll);
            result.setTanggalAwalLembur(CommonUtil.convertStringToDate(tanggalAwalLembur));
            result.setTanggalAkhirLembur(CommonUtil.convertStringToDate(tanggalAkhirLembur));

            result.setNip((String) row[0]);
            result.setNipLama(row[1]!=null ? (String) row[1] : "");
            result.setGelar(row[2]!=null ? (String) row[2] : "");
            result.setNamaPegawai((String) row[3]);
            result.setBranchId(row[4]!=null ? (String) row[4] : "");
            result.setBranchName(row[5]!=null ? (String) row[5] : "");
            result.setDivisiId(row[6]!=null ? (String) row[6] : "");
            result.setDivisiName(row[7]!=null ? (String) row[7] : "");
            result.setSubDivisiId(row[8]!=null ? (String) row[8] : "");
            result.setSubDivisiName(row[9]!=null ? (String) row[9] : "");
            result.setKelompokPosisiId(row[10]!=null ? (String) row[10] : "");
            result.setKelompokPosisiName(row[11]!=null ? (String) row[11] : "");
            result.setGolonganId(row[12]!=null ? (String) row[12] : "");
            result.setGolonganName(row[13]!=null ? (String) row[13] : "");

            result.setGolonganDapen(row[14]!=null ? (String) row[14] : "" );
            result.setGradeLevel(row[15]!=null ? (Integer) row[15] : null );
            result.setStGradeLevel(row[15]!=null ? ((Integer) row[15]).toString() : "");

            result.setMasaKerjaGol((String) row[16]);
            result.setiMasaKerjaGol(Integer.parseInt(result.getMasaKerjaGol()));

            result.setTipePegawai(row[17]!=null ? (String) row[17] : "");
            result.setTipePegawaiName(row[18]!=null ? (String) row[18] : "");
            result.setStatusPegawai(row[19]!=null ? (String) row[19] : "");
            result.setStatusPegawaiName(row[20]!=null ? (String) row[20] : "");
            result.setJenisPegawai(row[21]!=null ? (String) row[21] : "");
            result.setJenisPegawaiName(row[22]!=null ? (String) row[22] : "");

            result.setPosisiId(row[23]!=null ? (String) row[23] : "");
            result.setPosisiName(row[24]!=null ? (String) row[24] : "");
            result.setProfesiId(row[25]!=null ? (String) row[25] : "");
            result.setProfesiName(row[26]!=null ? (String) row[26] : "");

            result.setDanaPensiun(row[27]!=null ? (String) row[27] : "");
            result.setDanaPensiunName(row[28]!=null ? (String) row[28] : "");

            result.setPersenDapenKaryNilai(row[29]!=null ? (BigDecimal) row[29] : new BigDecimal(0));
            result.setPersenDapenPershNilai(row[30]!=null ? (BigDecimal) row[30] : new BigDecimal(0));
            result.setPersenDapenKary(CommonUtil.numbericFormat(result.getPersenDapenKaryNilai(),"###,###"));
            result.setPersenDapenPersh(CommonUtil.numbericFormat(result.getPersenDapenPershNilai(),"###,###"));

            result.setGajiPokokNilai(row[31]!=null ? (BigDecimal) row[31] : new BigDecimal(0));
            result.setGajiPokokNilai(result.getGajiPokokNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setGajiPokok(CommonUtil.numbericFormat(result.getGajiPokokNilai(),"###,###"));

            result.setSantunanKhususNilai(row[32]!=null ? (BigDecimal) row[32] : new BigDecimal(0));
            result.setSantunanKhususNilai(result.getSantunanKhususNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setSantunanKhusus(CommonUtil.numbericFormat(result.getSantunanKhususNilai(),"###,###"));

            result.setTunjRumahNilai(row[33]!=null ? (BigDecimal) row[33] : new BigDecimal(0));
            result.setTunjRumahNilai(result.getTunjRumahNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjRumah(CommonUtil.numbericFormat(result.getTunjRumahNilai(),"###,###"));

            result.setTunjListrikNilai(row[34]!=null ? (BigDecimal) row[34] : new BigDecimal(0));
            result.setTunjListrikNilai(result.getTunjListrikNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjListrik(CommonUtil.numbericFormat(result.getTunjListrikNilai(),"###,###"));

            result.setTunjAirNilai(row[35]!=null ? (BigDecimal) row[35] : new BigDecimal(0));
            result.setTunjAirNilai(result.getTunjAirNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjAir(CommonUtil.numbericFormat(result.getTunjAirNilai(),"###,###"));

            result.setTunjBbmNilai(row[36]!=null ? (BigDecimal) row[36] : new BigDecimal(0));
            result.setTunjBbmNilai(result.getTunjBbmNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjBbm(CommonUtil.numbericFormat(result.getTunjBbmNilai(),"###,###"));

            result.setTunjJabatanNilai(row[37]!=null ? (BigDecimal) row[37] : new BigDecimal(0));
            result.setTunjJabatanNilai(result.getTunjJabatanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjJabatan(CommonUtil.numbericFormat(result.getTunjJabatanNilai(),"###,###"));

            result.setTunjStrukturalNilai(row[38]!=null ? (BigDecimal) row[38] : new BigDecimal(0));
            result.setTunjStrukturalNilai(result.getTunjStrukturalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjStruktural(CommonUtil.numbericFormat(result.getTunjStrukturalNilai(),"###,###"));

            result.setTunjFungsionalNilai(row[39]!=null ? (BigDecimal) row[39] : new BigDecimal(0));
            result.setTunjFungsionalNilai(result.getTunjFungsionalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjFungsional(CommonUtil.numbericFormat(result.getTunjFungsionalNilai(),"###,###"));

            result.setTunjTambahanNilai(row[40]!=null ? (BigDecimal) row[40] : new BigDecimal(0));
            result.setTunjTambahanNilai(result.getTunjTambahanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjTambahan(CommonUtil.numbericFormat(result.getTunjTambahanNilai(),"###,###"));

            result.setGajiPensiunNilai(row[41]!=null ? (BigDecimal) row[41] : new BigDecimal(0));
            result.setGajiPensiunNilai(result.getGajiPensiunNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setGajiPensiun(CommonUtil.numbericFormat(result.getGajiPensiunNilai(),"###,###"));

            result.setIuranDapenPershNilai(row[42]!=null ? (BigDecimal) row[42] : new BigDecimal(0));
            result.setIuranDapenPershNilai(result.getIuranDapenPershNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setIuranDapenPersh(CommonUtil.numbericFormat(result.getIuranDapenPershNilai(),"###,###"));

            result.setIuranDapenKaryNilai(row[43]!=null ? (BigDecimal) row[43] : new BigDecimal(0));
            result.setIuranDapenKaryNilai(result.getIuranDapenKaryNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setIuranDapenKary(CommonUtil.numbericFormat(result.getIuranDapenKaryNilai(),"###,###"));

            result.setTunjPeralihanGapokNilai(row[44]!=null ? (BigDecimal) row[44] : new BigDecimal(0));
            result.setTunjPeralihanGapokNilai(result.getTunjPeralihanGapokNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanGapok(CommonUtil.numbericFormat(result.getTunjPeralihanGapokNilai(),"###,###"));

            result.setTunjPeralihanSankhusNilai(row[45]!=null ? (BigDecimal) row[45] : new BigDecimal(0));
            result.setTunjPeralihanSankhusNilai(result.getTunjPeralihanSankhusNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanSankhus(CommonUtil.numbericFormat(result.getTunjPeralihanSankhusNilai(),"###,###"));

            result.setTunjPeralihanTunjNilai(row[46]!=null ? (BigDecimal) row[46] : new BigDecimal(0));
            result.setTunjPeralihanTunjNilai(result.getTunjPeralihanTunjNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanTunj(CommonUtil.numbericFormat(result.getTunjPeralihanTunjNilai(),"###,###"));

            result.setTunjKomunikasiNilai(row[47]!=null ? (BigDecimal) row[47] : new BigDecimal(0));
            result.setTunjKomunikasiNilai(result.getTunjKomunikasiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjKomunikasi(CommonUtil.numbericFormat(result.getTunjKomunikasiNilai(),"###,###"));

            result.setTunjPemondokanNilai(row[48]!=null ? (BigDecimal) row[48] : new BigDecimal(0));
            result.setTunjPemondokanNilai(result.getTunjPemondokanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPemondokan(CommonUtil.numbericFormat(result.getTunjPemondokanNilai(),"###,###"));

            result.setTunjLemburNilai(row[49]!=null ? (BigDecimal) row[49] : new BigDecimal(0));
            result.setTunjLemburNilai(result.getTunjLemburNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjLembur(CommonUtil.numbericFormat(result.getTunjLemburNilai(),"###,###"));

            result.setTunjSupervisiNilai(row[50]!=null ? (BigDecimal) row[50] : new BigDecimal(0));
            result.setTunjSupervisiNilai(result.getTunjSupervisiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjSupervisi(CommonUtil.numbericFormat(result.getTunjSupervisiNilai(),"###,###"));

            result.setTunjLokalNilai(row[51]!=null ? (BigDecimal) row[51] : new BigDecimal(0));
            result.setTunjLokalNilai(result.getTunjLokalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjLokal(CommonUtil.numbericFormat(result.getTunjLokalNilai(),"###,###"));

            result.setTunjSiagaNilai(row[52]!=null ? (BigDecimal) row[52] : new BigDecimal(0));
            result.setTunjSiagaNilai(result.getTunjSiagaNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjSiaga(CommonUtil.numbericFormat(result.getTunjSiagaNilai(),"###,###"));

            result.setMinBpjsKsNilai(row[53]!=null ? (BigDecimal) row[53] : new BigDecimal(0));
            result.setMinBpjsKsNilai(result.getMinBpjsKsNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setMinBpjsKs(CommonUtil.numbericFormat(result.getMinBpjsKsNilai(),"###,###"));

            result.setMaxBpjsKsNilai(row[54]!=null ? (BigDecimal) row[54] : new BigDecimal(0));
            result.setMaxBpjsKsNilai(result.getMaxBpjsKsNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setMaxBpjsKs(CommonUtil.numbericFormat(result.getMaxBpjsKsNilai(),"###,###"));

            result.setPersenBpjsKsKaryNilai(row[55]!=null ? (BigDecimal) row[55] : new BigDecimal(0));
            result.setPersenBpjsKsKary(CommonUtil.numbericFormat(result.getPersenBpjsKsKaryNilai(),"###,###"));

            result.setPersenBpjsKsPershNilai(row[56]!=null ? (BigDecimal) row[56] : new BigDecimal(0));
            result.setPersenBpjsKsPersh(CommonUtil.numbericFormat(result.getPersenBpjsKsPershNilai(),"###,###"));

            result.setMinBpjsTkNilai(row[57]!=null ? (BigDecimal) row[57] : new BigDecimal(0));
            result.setGajiPokokNilai(result.getGajiPokokNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setMinBpjsTk(CommonUtil.numbericFormat(result.getMinBpjsTkNilai(),"###,###"));

            result.setMaxBpjsTkNilai(row[58]!=null ? (BigDecimal) row[58] : new BigDecimal(0));
            result.setMaxBpjsTkNilai(result.getMaxBpjsTkNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setMaxBpjsTk(CommonUtil.numbericFormat(result.getMaxBpjsTkNilai(),"###,###"));

            result.setPersenBpjsTkIuranKaryNilai(row[59]!=null ? (BigDecimal) row[59] : new BigDecimal(0));
            result.setPersenBpjsTkIuranKary(CommonUtil.numbericFormat(result.getPersenBpjsTkIuranKaryNilai(),"###,###"));

            result.setPersenBpjsTkJpkKaryNilai(row[60]!=null ? (BigDecimal) row[60] : new BigDecimal(0));
            result.setPersenBpjsTkJpkKary(CommonUtil.numbericFormat(result.getPersenBpjsTkJpkKaryNilai(),"###,###"));

            result.setPersenBpjsTkJkkPershNilai(row[61]!=null ? (BigDecimal) row[61] : new BigDecimal(0));
            result.setPersenBpjsTkJkkPersh(CommonUtil.numbericFormat(result.getPersenBpjsTkJkkPershNilai(),"###,###"));

            result.setPersenBpjsTkJhtPershNilai(row[62]!=null ? (BigDecimal) row[62] : new BigDecimal(0));
            result.setPersenBpjsTkJhtPersh(CommonUtil.numbericFormat(result.getPersenBpjsTkJhtPershNilai(),"###,###"));

            result.setPersenBpjsTkJkmPershNilai(row[63]!=null ? (BigDecimal) row[63] : new BigDecimal(0));
            result.setPersenBpjsTkJkmPersh(CommonUtil.numbericFormat(result.getPersenBpjsTkJkmPershNilai(),"###,###"));

            result.setPersenBpjsTkJpkPershNilai(row[64]!=null ? (BigDecimal) row[64] : new BigDecimal(0));
            result.setPersenBpjsTkJpkPersh(CommonUtil.numbericFormat(result.getPersenBpjsTkJpkPershNilai(),"###,###"));

            result.setStatusKeluarga(row[65]!=null ? (String) row[65] : "");
            result.setJumlahAnak(row[66]!=null ? ((BigInteger) row[66]).intValue() : new Integer(0));
            result.setStJumlahAnak(String.valueOf(result.getJumlahAnak()));

            result.setJumlahAnakDitanggung(row[67]!=null ? ((BigInteger) row[67]).intValue() : new Integer(0));
            result.setStJumlahAnakDitanggung(String.valueOf(result.getJumlahAnakDitanggung()));

            result.setNpwpPegawai(row[68]!=null ? (String) row[68] : "");
            result.setNoRekBank(row[69]!=null ? (String) row[69] : "");
            result.setNamaBank(row[70]!=null ? (String) row[70] : "");

            result.setTanggalAktif(row[71]!=null ? (Date) row[71] : null);
            result.setTanggalAkhirKontrak(row[72]!=null ? (Date) row[72] : null);
            result.setTanggalPraPensiun(row[73]!=null ? (Date) row[73] : null);
            result.setTanggalPensiun(row[74]!=null ? (Date) row[74] : null);
            result.setTanggalKeluar(row[75]!=null ? (Date) row[75] : null);

            result.setStTanggalAktif(result.getTanggalAktif()!=null ? CommonUtil.simpleDateFormat(result.getTanggalAktif()) : "");
            result.setStTanggalAkhirKontrak(result.getTanggalAkhirKontrak()!=null ? CommonUtil.simpleDateFormat(result.getTanggalAkhirKontrak()) : "");
            result.setStTanggalPraPensiun(result.getTanggalPraPensiun()!=null ? CommonUtil.simpleDateFormat(result.getTanggalPraPensiun()) : "");
            result.setStTanggalPensiun(result.getTanggalPensiun()!=null ? CommonUtil.simpleDateFormat(result.getTanggalPensiun()) : "");
            result.setStTanggalKeluar(result.getTanggalKeluar()!=null ? CommonUtil.simpleDateFormat(result.getTanggalKeluar()) : "");

            result.setKodering(row[76]!=null ? (String) row[76] : "");
            result.setGender(row[77]!=null ? (String) row[77] : "");

            result.setNoBpjsKs(row[78]!=null ? (String) row[78] : "");
            result.setNoBpjsTk(row[79]!=null ? (String) row[79] : "");

            result.setUmrNilai(row[80]!=null ? (BigDecimal) row[80] : new BigDecimal(0));
            result.setUmrNilai(result.getUmrNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setUmr(CommonUtil.numbericFormat(result.getUmrNilai(),"###,###"));

            result.setMultifikatorNilai(row[81]!=null ? (BigDecimal) row[81] : new BigDecimal(0));
            result.setMultifikator(CommonUtil.numbericFormat(result.getMultifikatorNilai(),"###,###"));

            result.setPtkpPegawaiNilai(row[82]!=null ? (BigDecimal) row[82] : new BigDecimal(0));
            result.setPtkpPegawaiNilai(result.getPtkpPegawaiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setPtkpPegawai(CommonUtil.numbericFormat(result.getPtkpPegawaiNilai(),"###,###"));

            listOfResult.add(result);
        }

        return listOfResult.get(0);
    }

    //updated by ferdi, 01-12-2020, refresh initial data pendapatan non rutin
    public PegawaiPayroll refreshInitialDataIncomeNonRutin(String nip,
                                                           String branchId,
                                                           String periodePayroll,
                                                           String tahunPayroll,
                                                           String tahunSkalaPayroll,
                                                           String tipePayroll) throws HibernateException {

        List<Object[]> results = new ArrayList<Object[]>();
        List<PegawaiPayroll> listOfResult = new ArrayList<PegawaiPayroll>();

        String query = "";

        //untuk perhitungan THR/insentif/jasop
        if (CommonConstant.CODE_THR.equalsIgnoreCase(tipePayroll) ||
                CommonConstant.CODE_INSENTIF.equalsIgnoreCase(tipePayroll) ||
                CommonConstant.CODE_JASOP.equalsIgnoreCase(tipePayroll)) {

            query = "select " +
                    "peg.*,\n" +
                    "ptkp.nilai                                                                      as ptkp_pegawai" +
                    "from (\n" +
                    //bod
                    "     select pegawai.nip                                                                as nip,\n" +
                    "            pegawai.nip_lama                                                           as nip_lama,\n" +
                    "            case\n" +
                    "              when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "              else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "            pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "            pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "            branch.branch_name                                                         as branch_name,\n" +
                    "            posisi.department_id                                                       as divisi,\n" +
                    "            depart.department_name                                                     as divisi_name,\n" +
                    "            posisi.bagian_id                                                           as sub_divisi,\n" +
                    "            bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "            posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "            kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "            pegawai.golongan_id                                                        as golongan_id,\n" +
                    "            golongan.golongan_id                                                       as golongan_name,\n" +
                    "            0                                                                          as grade_level,\n" +
                    "            pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "            pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "            tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "            pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "            case\n" +
                    "              when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "              else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "            pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "            jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "            pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "            posisi.position_name                                                       as posisi_name,\n" +
                    "            pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "            profesi.profesi_name                                                       as profesi_name,\n" +
                    "            case when skala_gaji.gaji is null then 0 else skala_gaji.gaji end          as gaji_pokok,\n" +
                    "            0                                                                          as santunan_khusus,\n" +
                    "            case when skala_gaji.tunj_rumah is null then 0 else skala_gaji.tunj_rumah end as tunj_rumah,\n" +
                    "            0                                                                          as tunj_listrik,\n" +
                    "            0                                                                          as tunj_air,\n" +
                    "            0                                                                          as tunj_bbm,\n" +
                    "            0                                                                          as tunj_jabatan,\n" +
                    "            0                                                                          as tunj_struktural,\n" +
                    "            0                                                                          as tunj_fungsional,\n" +
                    "            0                                                                          as tunj_tambahan,\n" +
                    "            0                                                                          as tunj_peralihan_gapok,\n" +
                    "            0                                                                          as tunj_peralihan_sankhus,\n" +
                    "            0                                                                          as tunj_peralihan_tunjangan,\n" +
                    "            case\n" +
                    "              when skala_gaji.tunj_telekomunikasi is null then 0\n" +
                    "              else skala_gaji.tunj_telekomunikasi end                                  as tunj_komunikasi,\n" +
                    "            0                                                                          as tunj_pemondokan,\n" +
                    "            0                                                                          as tunj_supervisi,\n" +
                    "            0                                                                          as tunj_lokal,\n" +
                    "            0                                                                          as tunj_siaga,\n" +
                    "            pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "            case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "            case\n" +
                    "              when pegawai.jumlah_anak is not null then\n" +
                    "                case\n" +
                    "                  when pegawai.jumlah_anak > 3 then 3\n" +
                    "                  else pegawai.jumlah_anak end\n" +
                    "              else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                    "            pegawai.npwp                                                               as npwp,\n" +
                    "            pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "            pegawai.nama_bank                                                          as nama_bank,\n" +
                    "            pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "            pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "            pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "            pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "            pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "            posisi.kodering                                                            as kodering,\n" +
                    "            pegawai.jenis_kelamin                                                      as gender,\n" +
                    "            1                                                                          as multifikator\n" +
                    "     from im_hris_pegawai pegawai\n" +
                    "            left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id\n" +
                    "                                                     and golongan.flag = 'Y'\n" +
                    "            left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "            left join it_hris_pegawai_position pegawai_posisi\n" +
                    "              on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "                 pegawai_posisi.flag_digaji = 'Y'\n" +
                    "            left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "              on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "            left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "            left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "            left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "            left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "            left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "            left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "            left join im_hris_payroll_skala_gaji_bod skala_gaji\n" +
                    "              on skala_gaji.position_id = pegawai_posisi.position_id and skala_gaji.flag = 'Y' and\n" +
                    "                 skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "\n" +
                    "     where pegawai.flag = 'Y'\n" +
                    "       and pegawai.tipe_pegawai = 'TP02'\n" + //RAKA-update tipe pegawai
                    "       and pegawai_posisi.branch_id = :branchId\n" +
                    "     union\n" +
                    //pegawai tetap
                    "     select pegawai.nip                                                                as nip,\n" +
                    "            pegawai.nip_lama                                                           as nip_lama,\n" +
                    "            case\n" +
                    "              when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "              else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "            pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "            pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "            branch.branch_name                                                         as branch_name,\n" +
                    "            posisi.department_id                                                       as divisi,\n" +
                    "            depart.department_name                                                     as divisi_name,\n" +
                    "            posisi.bagian_id                                                           as sub_divisi,\n" +
                    "            bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "            posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "            kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "            pegawai.golongan_id                                                        as golongan_id,\n" +
                    "            golongan.golongan_name                                                     as golongan_name,\n" +
                    "            golongan.grade_level                                                       as grade_level,\n" +
                    "            pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "            pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "            tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "            pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "            case\n" +
                    "              when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "              else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "            pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "            jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "            pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "            posisi.position_name                                                       as posisi_name,\n" +
                    "            pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "            profesi.profesi_name                                                       as profesi_name,\n" +
                    "            case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                    "            case\n" +
                    "              when skala_gaji.santunan_khusus is null then 0\n" +
                    "              else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                    "            case\n" +
                    "              when pegawai.flag_tunj_rumah = 'Y' then\n" +
                    "                case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                    "              else 0 end                                                                as tunj_rumah,\n" +
                    "            case\n" +
                    "              when pegawai.flag_tunj_listrik = 'Y' then\n" +
                    "                case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                    "              else 0 end                                                               as tunj_listrik,\n" +
                    "            case\n" +
                    "              when pegawai.flag_tunj_air = 'Y' then\n" +
                    "                case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                    "              else 0 end                                                               as tunj_air,\n" +
                    "            case\n" +
                    "              when pegawai.flag_tunj_bbm = 'Y' then\n" +
                    "                case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                    "              else 0 end                                                               as tunj_bbm,\n" +
                    "            case\n" +
                    "              when tunj_struktural.tunj_jabatan is null then 0\n" +
                    "              else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                    "            case\n" +
                    "              when tunj_struktural.tunj_struktural is null then 0\n" +
                    "              else tunj_struktural.tunj_struktural end                                 as tunj_struktural,\n" +
                    "            case\n" +
                    "              when tunj_strategis.nilai is null then 0\n" +
                    "              else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                    "            0                                                                          as tunj_tambahan,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_peralihan_gapok\n" +
                    "                        else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_peralihan_sankhus\n" +
                    "                        else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                    "                        else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                    "            case\n" +
                    "              when kelompok.tunj_telekomunikasi is null then 0\n" +
                    "              else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_pemondokan\n" +
                    "                        else 0 end end                                                 as tunj_pemondokan,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_supervisi\n" +
                    "                        else 0 end end                                                  as tunj_supervisi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_lokasi\n" +
                    "                        else 0 end end                                                  as tunj_lokasi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_siaga\n" +
                    "                        else 0 end end                                                  as tunj_siaga,\n" +
                    "            pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "            case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "            case\n" +
                    "              when pegawai.jumlah_anak is not null then\n" +
                    "                case\n" +
                    "                  when pegawai.jumlah_anak > 3 then 3\n" +
                    "                  else pegawai.jumlah_anak end\n" +
                    "              else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                    "            pegawai.npwp                                                               as npwp,\n" +
                    "            pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "            pegawai.nama_bank                                                          as nama_bank,\n" +
                    "            pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "            pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "            pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "            pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "            pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "            posisi.kodering                                                            as kodering,\n" +
                    "            pegawai.jenis_kelamin                                                      as gender,\n" +
                    "            1                                                                          as multifikator\n" +
                    "     from im_hris_pegawai pegawai\n" +
                    "            left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                    "                                                   golongan.flag = 'Y'\n" +
                    "            left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "            left join it_hris_pegawai_position pegawai_posisi\n" +
                    "              on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "                 pegawai_posisi.flag_digaji = 'Y'\n" +
                    "            left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "              on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "            left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "            left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "            left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "            left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "            left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "            left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "            left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                    "              on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                    "                 tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                    "            left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                    "              on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                    "            left join im_hris_payroll_skala_gaji skala_gaji\n" +
                    "              on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                    "                 skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "            left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                    "\n" +
                    "     where pegawai.flag = 'Y'\n" +
                    "       and (pegawai.flag_pegawai_cuti_diluar_tanggungan = 'N' or pegawai.flag_pegawai_cuti_diluar_tanggungan is null)\n" +
                    "       and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                    "       and pegawai_posisi.branch_id = :branchId\n" +
                    "     union\n" +
                    //pkwt
                    "     select pegawai.nip                                                                as nip,\n" +
                    "            pegawai.nip_lama                                                           as nip_lama,\n" +
                    "            case\n" +
                    "              when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "              else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "            pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "            pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "            branch.branch_name                                                         as branch_name,\n" +
                    "            posisi.department_id                                                       as divisi,\n" +
                    "            depart.department_name                                                     as divisi_name,\n" +
                    "            posisi.bagian_id                                                           as sub_divisi,\n" +
                    "            bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "            posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "            kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "            pegawai.golongan_id                                                        as golongan_id,\n" +
                    "            golongan.golongan_pkwt_id                                                  as golongan_name,\n" +
                    "            0                                                                          as grade_level,\n" +
                    "            pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "            pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "            tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "            pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "            case\n" +
                    "              when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "              else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "            pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "            jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "            pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "            posisi.position_name                                                       as posisi_name,\n" +
                    "            pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "            profesi.profesi_name                                                       as profesi_name,\n" +
                    "            case when skala_gaji.gaji_pokok is null then 0 else skala_gaji.gaji_pokok end\n" +
                    "                                                                                       as gaji_pokok,\n" +
                    "            case\n" +
                    "              when skala_gaji.santunan_khusus is null then 0\n" +
                    "              else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                    "            0                                                                          as tunj_rumah,\n" +
                    "            0                                                                          as tunj_listrik,\n" +
                    "            0                                                                          as tunj_air,\n" +
                    "            0                                                                          as tunj_bbm,\n" +
                    "            0                                                                          as tunj_jabatan,\n" +
                    "            0                                                                          as tunj_struktural,\n" +
                    "            case\n" +
                    "              when skala_gaji.tunj_fungsional is null then 0\n" +
                    "              else skala_gaji.tunj_fungsional end                                      as tunj_fungsional,\n" +
                    "            case\n" +
                    "              when skala_gaji.tunj_tambahan is null then 0\n" +
                    "              else skala_gaji.tunj_tambahan end                                        as tunj_tambahan,\n" +
                    "            0                                                                          as tunj_peralihan_gapok,\n" +
                    "            0                                                                          as tunj_peralihan_sankhus,\n" +
                    "            0                                                                          as tunj_peralihan_tunjangan,\n" +
                    "            0                                                                          as tunj_komunikasi,\n" +
                    "            0                                                                          as tunj_pemondokan,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_supervisi\n" +
                    "                        else 0 end end                                                  as tunj_supervisi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_lokasi\n" +
                    "                        else 0 end end                                                  as tunj_lokasi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_siaga\n" +
                    "                        else 0 end end                                                  as tunj_siaga,\n" +
                    "            pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "            case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "            case\n" +
                    "              when pegawai.jumlah_anak is not null then\n" +
                    "                case\n" +
                    "                  when pegawai.jumlah_anak > 3 then 3\n" +
                    "                  else pegawai.jumlah_anak end\n" +
                    "              else 0 end                                                               as jumlah_anak_ditanggung,\n" +
                    "            pegawai.npwp                                                               as npwp,\n" +
                    "            pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "            pegawai.nama_bank                                                          as nama_bank,\n" +
                    "            pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "            pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "            pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "            pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "            pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "            posisi.kodering                                                            as kodering,\n" +
                    "            pegawai.jenis_kelamin                                                      as gender,\n" +
                    "            case\n" +
                    "               when to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') - INTERVAL '1 YEAR' < pegawai.tanggal_masuk  then\n" +
                    "                    round((to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')  - pegawai.tanggal_masuk + 1)  / cast(365 as decimal),2)\n" +
                    "               else 1 end                                                              as multifikator\n" +
                    "     from im_hris_pegawai pegawai\n" +
                    "            left join im_hris_golongan_pkwt golongan on pegawai.golongan_id = golongan.golongan_pkwt_id\n" +
                    "                                                          and golongan.flag = 'Y'\n" +
                    "            left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "            left join it_hris_pegawai_position pegawai_posisi\n" +
                    "              on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "                 pegawai_posisi.flag_digaji = 'Y'\n" +
                    "            left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "              on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "            left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "            left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "            left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "            left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "            left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "            left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "            left join im_hris_payroll_skala_gaji_pkwt skala_gaji\n" +
                    "              on skala_gaji.golongan_pkwt_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                    "                 skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "            left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                    "     where pegawai.flag = 'Y'\n" +
                    "       and pegawai.tipe_pegawai = 'TP04'\n" + //RAKA-update tipe pegawai
                    "       and pegawai_posisi.branch_id = :branchId\n" +
                    "     order by status_pegawai desc\n" +
                    "    ) peg \n" +
                    "      left join im_hris_payroll_ptkp ptkp on ptkp.status_keluarga = peg.status_keluarga\n" +
                    "                                           and ptkp.jumlah_tanggungan = peg.jumlah_anak_ditanggung and ptkp.flag = 'Y'\n" +
                    "      where nip = :nip" ;

            // untuk perhitungan cuti panjang dan tahunan khusus untuk pegawai tetap
        } else if (CommonConstant.CODE_CUTI_PANJANG.equalsIgnoreCase(tipePayroll) ) {

            query = "select\n" +
                    "peg.*,\n" +
                    "ptkp.nilai                                                                 as ptkp_pegawai\n" +
                    "from\n" +
                    "(" +
                    "select pegawai.nip                                                                as nip,\n" +
                    "       pegawai.nip_lama                                                           as nip_lama,\n" +
                    "       case\n" +
                    "         when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "         else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "       pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "       pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "       branch.branch_name                                                         as branch_name,\n" +
                    "       posisi.department_id                                                       as divisi,\n" +
                    "       depart.department_name                                                     as divisi_name,\n" +
                    "       posisi.bagian_id                                                           as sub_divisi,\n" +
                    "       bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "       posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "       kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "       pegawai.golongan_id                                                        as golongan_id,\n" +
                    "       golongan.golongan_name                                                     as golongan_name,\n" +
                    "       golongan.grade_level                                                       as grade_level,\n" +
                    "       pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "       pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "       tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "       pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "       case\n" +
                    "         when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "         else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "       pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "       jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "       pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "       posisi.position_name                                                       as posisi_name,\n" +
                    "       pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "       profesi.profesi_name                                                       as profesi_name,\n" +
                    "       case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                    "       case\n" +
                    "         when skala_gaji.santunan_khusus is null then 0\n" +
                    "         else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_rumah = 'Y' then\n" +
                    "           case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                    "         else 0 end                                                                as tunj_rumah,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_listrik = 'Y' then\n" +
                    "           case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                    "         else 0 end                                                               as tunj_listrik,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_air = 'Y' then\n" +
                    "           case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                    "         else 0 end                                                               as tunj_air,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_bbm = 'Y' then\n" +
                    "           case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                    "         else 0 end                                                               as tunj_bbm,\n" +
                    "       case\n" +
                    "         when tunj_struktural.tunj_jabatan is null then 0\n" +
                    "         else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                    "       case\n" +
                    "         when tunj_struktural.tunj_struktural is null then 0\n" +
                    "         else tunj_struktural.tunj_struktural end                                 as tunj_struktural,\n" +
                    "       case\n" +
                    "         when tunj_strategis.nilai is null then 0\n" +
                    "         else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                    "       0                                                                          as tunj_tambahan,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_gapok\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_sankhus\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                    "       case\n" +
                    "         when kelompok.tunj_telekomunikasi is null then 0\n" +
                    "         else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_pemondokan\n" +
                    "                   else 0 end end                                                 as tunj_pemondokan,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_supervisi\n" +
                    "                   else 0 end end                                                  as tunj_supervisi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_lokasi\n" +
                    "                   else 0 end end                                                  as tunj_lokasi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_siaga\n" +
                    "                   else 0 end end                                                  as tunj_siaga,\n" +
                    "       pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "       case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "       case\n" +
                    "         when pegawai.jumlah_anak is not null then\n" +
                    "           case\n" +
                    "             when pegawai.jumlah_anak > 3 then 3\n" +
                    "             else pegawai.jumlah_anak end\n" +
                    "         else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                    "       pegawai.npwp                                                               as npwp,\n" +
                    "       pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "       pegawai.nama_bank                                                          as nama_bank,\n" +
                    "       pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "       pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "       pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "       pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "       pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "       posisi.kodering                                                            as kodering,\n" +
                    "       pegawai.jenis_kelamin                                                      as gender,\n" +
                    "       company.persen_cuti_panjang/100                                            as multifikator\n" +
                    "from im_hris_pegawai pegawai\n" +
                    "       left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                    "                                              golongan.flag = 'Y'\n" +
                    "       left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "       left join it_hris_pegawai_position pegawai_posisi\n" +
                    "         on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "            pegawai_posisi.flag_digaji = 'Y'\n" +
                    "       left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "         on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "       left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "       left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "       left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "       left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "       left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "       left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "       left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                    "         on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                    "            tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                    "       left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                    "         on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                    "       left join im_hris_payroll_skala_gaji skala_gaji\n" +
                    "         on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                    "            skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "       left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                    "       left join im_company company on company.flag = 'Y'\n" +
                    "\n" +
                    "where pegawai.flag = 'Y'\n" +
                    "  and (pegawai.flag_pegawai_cuti_diluar_tanggungan = 'N' or pegawai.flag_pegawai_cuti_diluar_tanggungan is null)\n" +
                    "  and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                    "  and pegawai_posisi.branch_id = :branchId \n" +
                    ")peg \n" +
                    "left join im_hris_payroll_ptkp ptkp on ptkp.status_keluarga = peg.status_keluarga\n" +
                    "    and ptkp.jumlah_tanggungan = peg.jumlah_anak_ditanggung and ptkp.flag = 'Y' \n" +
                    "where nip = :nip ";


        } else if (CommonConstant.CODE_CUTI_TAHUNAN.equalsIgnoreCase(tipePayroll)) {

            query = "select\n" +
                    "peg.*,\n" +
                    "ptkp.nilai                                                                 as ptkp_pegawai\n" +
                    "from\n" +
                    "(" +
                    "select pegawai.nip                                                                as nip,\n" +
                    "       pegawai.nip_lama                                                           as nip_lama,\n" +
                    "       case\n" +
                    "         when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "         else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "       pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "       pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "       branch.branch_name                                                         as branch_name,\n" +
                    "       posisi.department_id                                                       as divisi,\n" +
                    "       depart.department_name                                                     as divisi_name,\n" +
                    "       posisi.bagian_id                                                           as sub_divisi,\n" +
                    "       bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "       posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "       kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "       pegawai.golongan_id                                                        as golongan_id,\n" +
                    "       golongan.golongan_name                                                     as golongan_name,\n" +
                    "       golongan.grade_level                                                       as grade_level,\n" +
                    "       pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "       pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "       tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "       pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "       case\n" +
                    "         when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "         else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "       pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "       jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "       pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "       posisi.position_name                                                       as posisi_name,\n" +
                    "       pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "       profesi.profesi_name                                                       as profesi_name,\n" +
                    "       case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                    "       case\n" +
                    "         when skala_gaji.santunan_khusus is null then 0\n" +
                    "         else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_rumah = 'Y' then\n" +
                    "           case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                    "         else 0 end                                                                as tunj_rumah,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_listrik = 'Y' then\n" +
                    "           case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                    "         else 0 end                                                               as tunj_listrik,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_air = 'Y' then\n" +
                    "           case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                    "         else 0 end                                                               as tunj_air,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_bbm = 'Y' then\n" +
                    "           case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                    "         else 0 end                                                               as tunj_bbm,\n" +
                    "       case\n" +
                    "         when tunj_struktural.tunj_jabatan is null then 0\n" +
                    "         else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                    "       case\n" +
                    "         when tunj_struktural.tunj_struktural is null then 0\n" +
                    "         else tunj_struktural.tunj_struktural end                                 as tunj_struktural,\n" +
                    "       case\n" +
                    "         when tunj_strategis.nilai is null then 0\n" +
                    "         else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                    "       0                                                                          as tunj_tambahan,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_gapok\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_sankhus\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                    "       case\n" +
                    "         when kelompok.tunj_telekomunikasi is null then 0\n" +
                    "         else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_pemondokan\n" +
                    "                   else 0 end end                                                 as tunj_pemondokan,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_supervisi\n" +
                    "                   else 0 end end                                                  as tunj_supervisi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_lokasi\n" +
                    "                   else 0 end end                                                  as tunj_lokasi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_siaga\n" +
                    "                   else 0 end end                                                  as tunj_siaga,\n" +
                    "       pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "       case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "       case\n" +
                    "         when pegawai.jumlah_anak is not null then\n" +
                    "           case\n" +
                    "             when pegawai.jumlah_anak > 3 then 3\n" +
                    "             else pegawai.jumlah_anak end\n" +
                    "         else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                    "       pegawai.npwp                                                               as npwp,\n" +
                    "       pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "       pegawai.nama_bank                                                          as nama_bank,\n" +
                    "       pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "       pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "       pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "       pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "       pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "       posisi.kodering                                                            as kodering,\n" +
                    "       pegawai.jenis_kelamin                                                      as gender,\n" +
                    "       company.persen_cuti_tahunan/100                                            as multifikator \n" +
                    "from im_hris_pegawai pegawai\n" +
                    "       left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                    "                                              golongan.flag = 'Y'\n" +
                    "       left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "       left join it_hris_pegawai_position pegawai_posisi\n" +
                    "         on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "            pegawai_posisi.flag_digaji = 'Y'\n" +
                    "       left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "         on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "       left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "       left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "       left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "       left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "       left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "       left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "       left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                    "         on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                    "            tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                    "       left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                    "         on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                    "       left join im_hris_payroll_skala_gaji skala_gaji\n" +
                    "         on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                    "            skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "       left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                    "       left join im_company company on company.flag = 'Y'\n" +
                    "\n" +
                    "where pegawai.flag = 'Y'\n" +
                    "  and (pegawai.flag_pegawai_cuti_diluar_tanggungan = 'N' or pegawai.flag_pegawai_cuti_diluar_tanggungan is null)\n" +
                    "  and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                    "  and pegawai_posisi.branch_id = :branchId \n" +
                    ")peg \n" +
                    "left join im_hris_payroll_ptkp ptkp on ptkp.status_keluarga = peg.status_keluarga\n" +
                    "    and ptkp.jumlah_tanggungan = peg.jumlah_anak_ditanggung and ptkp.flag = 'Y' \n" +
                    "where nip = :nip" ;

        }

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("branchId", branchId)
                .setParameter("tahunSkalaPayroll", tahunSkalaPayroll)
                .setParameter("periodePayroll", periodePayroll)
                .setParameter("tahunPayroll", tahunPayroll)
                .setParameter("nip", nip)
                .list();

        for (Object[] row : results) {
            PegawaiPayroll result = new PegawaiPayroll();

            result.setTipePayroll(tipePayroll);

            //set periode,tahun,dan skala gaji
            result.setPeriodePayroll(periodePayroll);
            result.setTahunPayroll(tahunPayroll);
            result.setTahunSkalaGaji(tahunSkalaPayroll);

            result.setNip((String) row[0]);
            result.setNipLama(row[1] != null ? (String) row[1] : "");
            result.setGelar(row[2] != null ? (String) row[2] : "");
            result.setNamaPegawai((String) row[3]);
            result.setBranchId(row[4] != null ? (String) row[4] : "");
            result.setBranchName(row[5] != null ? (String) row[5] : "");
            result.setDivisiId(row[6] != null ? (String) row[6] : "");
            result.setDivisiName(row[7] != null ? (String) row[7] : "");
            result.setSubDivisiId(row[8] != null ? (String) row[8] : "");
            result.setSubDivisiName(row[9] != null ? (String) row[9] : "");
            result.setKelompokPosisiId(row[10] != null ? (String) row[10] : "");
            result.setKelompokPosisiName(row[11] != null ? (String) row[11] : "");
            result.setGolonganId(row[12] != null ? (String) row[12] : "");
            result.setGolonganName(row[13] != null ? (String) row[13] : "");

            result.setGradeLevel(row[14] != null ? (Integer) row[14] : null);
            result.setStGradeLevel(row[14] != null ? ((Integer) row[14]).toString() : "");

            result.setMasaKerjaGol((String) row[15]);
            result.setiMasaKerjaGol(Integer.parseInt(result.getMasaKerjaGol()));

            result.setTipePegawai(row[16] != null ? (String) row[16] : "");
            result.setTipePegawaiName(row[17] != null ? (String) row[17] : "");
            result.setStatusPegawai(row[18] != null ? (String) row[18] : "");
            result.setStatusPegawaiName(row[19] != null ? (String) row[19] : "");
            result.setJenisPegawai(row[20] != null ? (String) row[20] : "");
            result.setJenisPegawaiName(row[21] != null ? (String) row[21] : "");

            result.setPosisiId(row[22] != null ? (String) row[22] : "");
            result.setPosisiName(row[23] != null ? (String) row[23] : "");
            result.setProfesiId(row[24] != null ? (String) row[24] : "");
            result.setProfesiName(row[25] != null ? (String) row[25] : "");

            result.setGajiPokokNilai(row[26] != null ? (BigDecimal) row[26] : new BigDecimal(0));
            result.setGajiPokokNilai(result.getGajiPokokNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setGajiPokok(CommonUtil.numbericFormat(result.getGajiPokokNilai(), "###,###"));

            result.setSantunanKhususNilai(row[27] != null ? (BigDecimal) row[27] : new BigDecimal(0));
            result.setSantunanKhususNilai(result.getSantunanKhususNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setSantunanKhusus(CommonUtil.numbericFormat(result.getSantunanKhususNilai(), "###,###"));

            result.setTunjRumahNilai(row[28] != null ? (BigDecimal) row[28] : new BigDecimal(0));
            result.setTunjRumahNilai(result.getTunjRumahNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjRumah(CommonUtil.numbericFormat(result.getTunjRumahNilai(), "###,###"));

            result.setTunjListrikNilai(row[29] != null ? (BigDecimal) row[29] : new BigDecimal(0));
            result.setTunjListrikNilai(result.getTunjListrikNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjListrik(CommonUtil.numbericFormat(result.getTunjListrikNilai(), "###,###"));

            result.setTunjAirNilai(row[30] != null ? (BigDecimal) row[30] : new BigDecimal(0));
            result.setTunjAirNilai(result.getTunjAirNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjAir(CommonUtil.numbericFormat(result.getTunjAirNilai(), "###,###"));

            result.setTunjBbmNilai(row[31] != null ? (BigDecimal) row[31] : new BigDecimal(0));
            result.setTunjBbmNilai(result.getTunjBbmNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjBbm(CommonUtil.numbericFormat(result.getTunjBbmNilai(), "###,###"));

            result.setTunjJabatanNilai(row[32] != null ? (BigDecimal) row[32] : new BigDecimal(0));
            result.setTunjJabatanNilai(result.getTunjJabatanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjJabatan(CommonUtil.numbericFormat(result.getTunjJabatanNilai(), "###,###"));

            result.setTunjStrukturalNilai(row[33] != null ? (BigDecimal) row[33] : new BigDecimal(0));
            result.setTunjStrukturalNilai(result.getTunjStrukturalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjStruktural(CommonUtil.numbericFormat(result.getTunjStrukturalNilai(), "###,###"));

            result.setTunjFungsionalNilai(row[34] != null ? (BigDecimal) row[34] : new BigDecimal(0));
            result.setTunjFungsionalNilai(result.getTunjFungsionalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjFungsional(CommonUtil.numbericFormat(result.getTunjFungsionalNilai(), "###,###"));

            result.setTunjTambahanNilai(row[35] != null ? new BigDecimal((Integer)row[35]) : new BigDecimal(0));
            result.setTunjTambahanNilai(result.getTunjTambahanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjTambahan(CommonUtil.numbericFormat(result.getTunjTambahanNilai(), "###,###"));

            result.setTunjPeralihanGapokNilai(row[36] != null ? (BigDecimal) row[36] : new BigDecimal(0));
            result.setTunjPeralihanGapokNilai(result.getTunjPeralihanGapokNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanGapok(CommonUtil.numbericFormat(result.getTunjPeralihanGapokNilai(), "###,###"));

            result.setTunjPeralihanSankhusNilai(row[37] != null ? (BigDecimal) row[37] : new BigDecimal(0));
            result.setTunjPeralihanSankhusNilai(result.getTunjPeralihanSankhusNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanSankhus(CommonUtil.numbericFormat(result.getTunjPeralihanSankhusNilai(), "###,###"));

            result.setTunjPeralihanTunjNilai(row[38] != null ? (BigDecimal) row[38] : new BigDecimal(0));
            result.setTunjPeralihanTunjNilai(result.getTunjPeralihanTunjNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanTunj(CommonUtil.numbericFormat(result.getTunjPeralihanTunjNilai(), "###,###"));

            result.setTunjKomunikasiNilai(row[39] != null ? (BigDecimal) row[39] : new BigDecimal(0));
            result.setTunjKomunikasiNilai(result.getTunjKomunikasiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjKomunikasi(CommonUtil.numbericFormat(result.getTunjKomunikasiNilai(), "###,###"));

            result.setTunjPemondokanNilai(row[40] != null ? (BigDecimal) row[40] : new BigDecimal(0));
            result.setTunjPemondokanNilai(result.getTunjPemondokanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPemondokan(CommonUtil.numbericFormat(result.getTunjPemondokanNilai(), "###,###"));

            result.setTunjSupervisiNilai(row[41] != null ? (BigDecimal) row[41] : new BigDecimal(0));
            result.setTunjSupervisiNilai(result.getTunjSupervisiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjSupervisi(CommonUtil.numbericFormat(result.getTunjSupervisiNilai(), "###,###"));

            result.setTunjLokalNilai(row[42] != null ? (BigDecimal) row[42] : new BigDecimal(0));
            result.setTunjLokalNilai(result.getTunjLokalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjLokal(CommonUtil.numbericFormat(result.getTunjLokalNilai(), "###,###"));

            result.setTunjSiagaNilai(row[43] != null ? (BigDecimal) row[43] : new BigDecimal(0));
            result.setTunjSiagaNilai(result.getTunjSiagaNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjSiaga(CommonUtil.numbericFormat(result.getTunjSiagaNilai(), "###,###"));

            result.setStatusKeluarga(row[44] != null ? (String) row[44] : "");
            result.setJumlahAnak(row[45] != null ? ((BigInteger) row[45]).intValue() : new Integer(0));
            result.setStJumlahAnak(String.valueOf(result.getJumlahAnak()));

            result.setJumlahAnakDitanggung(row[46] != null ? ((BigInteger) row[46]).intValue() : new Integer(0));
            result.setStJumlahAnakDitanggung(String.valueOf(result.getJumlahAnakDitanggung()));

            result.setNpwpPegawai(row[47] != null ? (String) row[47] : "");
            result.setNoRekBank(row[48] != null ? (String) row[48] : "");
            result.setNamaBank(row[49] != null ? (String) row[49] : "");

            result.setTanggalAktif(row[50] != null ? (Date) row[50] : null);
            result.setTanggalAkhirKontrak(row[51] != null ? (Date) row[51] : null);
            result.setTanggalPraPensiun(row[52] != null ? (Date) row[52] : null);
            result.setTanggalPensiun(row[53] != null ? (Date) row[53] : null);
            result.setTanggalKeluar(row[54] != null ? (Date) row[54] : null);

            result.setStTanggalAktif(result.getTanggalAktif() != null ? CommonUtil.simpleDateFormat(result.getTanggalAktif()) : "");
            result.setStTanggalAkhirKontrak(result.getTanggalAkhirKontrak() != null ? CommonUtil.simpleDateFormat(result.getTanggalAkhirKontrak()) : "");
            result.setStTanggalPraPensiun(result.getTanggalPraPensiun() != null ? CommonUtil.simpleDateFormat(result.getTanggalPraPensiun()) : "");
            result.setStTanggalPensiun(result.getTanggalPensiun() != null ? CommonUtil.simpleDateFormat(result.getTanggalPensiun()) : "");
            result.setStTanggalKeluar(result.getTanggalKeluar() != null ? CommonUtil.simpleDateFormat(result.getTanggalKeluar()) : "");

            result.setKodering(row[55] != null ? (String) row[55] : "");
            result.setGender(row[56] != null ? (String) row[56] : "");

            result.setMultifikatorNilai(row[57] != null ? (BigDecimal) row[57] : new BigDecimal(0));
            result.setMultifikator(CommonUtil.numbericFormat(result.getMultifikatorNilai(), "###,###"));

            result.setPtkpPegawaiNilai(row[58] != null ? (BigDecimal) row[58] : new BigDecimal(0));
            result.setPtkpPegawaiNilai(result.getPtkpPegawaiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setPtkpPegawai(CommonUtil.numbericFormat(result.getPtkpPegawaiNilai(), "###,###"));

            listOfResult.add(result);
        }

        return listOfResult.get(0);
    }

    //updated by ferdi, 01-12-2020, rebuild to get data payroll bulanan (pendapatan rutin)
    //RAKA-05MEI2021==> Update. mengali tunj. Struktural & tunj. Jabatan dengan persen gaji jenis pegawai
    public List<PegawaiPayroll> getInitialDataPayrollBulanan(String branchId,
                                                             String periodePayroll,
                                                             String tahunPayroll,
                                                             String periodePayrollSebelumnya,
                                                             String tahunPayrollSebelumnya,
                                                             String tahunSkalaPayroll,
                                                             String tanggalAwalLembur,
                                                             String tanggalAkhirLembur) throws HibernateException {

        //get tanggal gaji -> default tanggal 25
        String tanggalGaji = CommonConstant.TANGGAL_GAJI + "-" + periodePayroll + "-" + tahunPayroll;
        List<PegawaiPayroll> listOfResult = new ArrayList<PegawaiPayroll>();

        List<Object[]> results = new ArrayList<Object[]>();

        String queryHeader = "select\n" +
                "kary.*,\n" +
                "case when kary.no_bpjs_kes is not null and kary.no_bpjs_kes !=  '' then (kary.dasar_perhitungan_bpjs_ks * kary.persen_bpjs_ks_kary)/100 else 0 end as iuran_bpjs_ks_kary,\n" +
                "case when kary.no_bpjs_kes is not null and kary.no_bpjs_kes !=  '' then (kary.dasar_perhitungan_bpjs_ks * kary.persen_bpjs_ks_pers)/100 else 0 end as iuran_bpjs_ks_pers,\n" +
                "case when kary.no_bpjs_tk is not null and kary.no_bpjs_tk !=  '' then (kary.dasar_perhitungan_bpjs_tk * kary.persen_bpjs_tk_iuran_kary)/100 else 0 end as iuran_bpjs_tk_kary,\n" +
                "case when kary.no_bpjs_tk is not null and kary.no_bpjs_tk !=  '' then (kary.dasar_perhitungan_bpjs_tk * kary.persen_bpjs_tk_jpk_kary)/100 else 0 end as jpk_bpjs_tk_kary,\n" +
                "case when kary.no_bpjs_tk is not null and kary.no_bpjs_tk !=  '' then (kary.dasar_perhitungan_bpjs_tk * kary.persen_bpjs_tk_jkk_pers)/100 else 0 end as jkk_bpjs_tk_pers,\n" +
                "case when kary.no_bpjs_tk is not null and kary.no_bpjs_tk !=  '' then (kary.dasar_perhitungan_bpjs_tk * kary.persen_bpjs_tk_jht_pers)/100 else 0 end as jht_bpjs_tk_pers,\n" +
                "case when kary.no_bpjs_tk is not null and kary.no_bpjs_tk !=  '' then (kary.dasar_perhitungan_bpjs_tk * kary.persen_bpjs_tk_jkm_pers)/100 else 0 end as jkm_bpjs_tk_pers,\n" +
                "case when kary.no_bpjs_tk is not null and kary.no_bpjs_tk !=  '' then (kary.dasar_perhitungan_bpjs_tk * kary.persen_bpjs_tk_jpk_pers)/100 else 0 end as jpk_bpjs_tk_pers\n" +
                "from (select peg.*,\n" +
                "             case\n" +
                "               when (peg.gaji_pokok + peg.santunan_khusus + peg.tunj_peralihan_gapok +\n" +
                "                     peg.tunj_peralihan_sankhus) < min_bpjs_ks then min_bpjs_ks\n" +
                "               else case\n" +
                "                      when (peg.gaji_pokok + peg.santunan_khusus + peg.tunj_peralihan_gapok +\n" +
                "                            peg.tunj_peralihan_sankhus) > max_bpjs_ks then max_bpjs_ks\n" +
                "                      else (peg.gaji_pokok + peg.santunan_khusus + peg.tunj_peralihan_gapok +\n" +
                "                            peg.tunj_peralihan_sankhus) end end as dasar_perhitungan_bpjs_ks,\n" +
                "             case\n" +
                "               when (peg.gaji_pokok + peg.santunan_khusus + peg.tunj_peralihan_gapok +\n" +
                "                     peg.tunj_peralihan_sankhus) < min_bpjs_tk then min_bpjs_tk\n" +
                "               else case\n" +
                "                      when (peg.gaji_pokok + peg.santunan_khusus + peg.tunj_peralihan_gapok +\n" +
                "                            peg.tunj_peralihan_sankhus) > max_bpjs_tk then max_bpjs_tk\n" +
                "                      else (peg.gaji_pokok + peg.santunan_khusus + peg.tunj_peralihan_gapok +\n" +
                "                            peg.tunj_peralihan_sankhus) end end as dasar_perhitungan_bpjs_tk,\n" +
                "             ptkp.nilai                                                                      as ptkp_pegawai\n" +
                "      from (\n" ;

        String queryBodBoc =
                //bod / boc
                "            select pegawai.nip                                                               as nip,\n" +
                "                  pegawai.nip_lama                                                           as nip_lama,\n" +
                "                  case\n" +
                "                    when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "                    else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "                  pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "                  pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "                  branch.branch_name                                                         as branch_name,\n" +
                "                  posisi.department_id                                                       as divisi,\n" +
                "                  depart.department_name                                                     as divisi_name,\n" +
                "                  posisi.bagian_id                                                           as sub_divisi,\n" +
                "                  bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "                  posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "                  kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "                  pegawai.golongan_id                                                        as golongan_id,\n" +
                "                  golongan.golongan_id                                                       as golongan_name,\n" +
                "                  ''                                                                         as golongan_dapen,\n" +
                "                  0                                                                          as grade_level,\n" +
                "                  pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "                  pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "                  tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "                  pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "                  case\n" +
                "                    when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "                    else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "                  pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "                  jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "                  pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "                  posisi.position_name                                                       as posisi_name,\n" +
                "                  pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "                  profesi.profesi_name                                                       as profesi_name,\n" +
                "                  pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "                  ''                                                                        as dana_pensiun_name,\n" +
                "                  0                                                                         as persen_kary_dana_pensiun,\n" +
                "                  0                                                                         as persen_pers_dana_pensiun,\n" +
                "                  case when skala_gaji.gaji is null then 0 else skala_gaji.gaji end          as gaji_pokok,\n" +
                "                  0                                                                          as santunan_khusus,\n" +
                "                  case when skala_gaji.tunj_rumah is null then 0 else skala_gaji.tunj_rumah end as tunj_rumah,\n" +
                "                  0                                                                          as tunj_listrik,\n" +
                "                  0                                                                          as tunj_air,\n" +
                "                  0                                                                          as tunj_bbm,\n" +
                "                  0                                                                          as tunj_jabatan,\n" +
                "                  0                                                                          as tunj_struktural,\n" +
                "                  0                                                                          as tunj_fungsional,\n" +
                "                  0                                                                          as tunj_tambahan,\n" +
                "                  0                                                                          as gaji_pensiun,\n" +
                "                  0                                                                          as iuran_pers_dapen,\n" +
                "                  0                                                                          as iuran_kary_dapen,\n" +
                "                  0                                                                          as tunj_peralihan_gapok,\n" +
                "                  0                                                                          as tunj_peralihan_sankhus,\n" +
                "                  0                                                                          as tunj_peralihan_tunjangan,\n" +
                "                  case\n" +
                "                    when skala_gaji.tunj_telekomunikasi is null then 0\n" +
                "                    else skala_gaji.tunj_telekomunikasi end                                  as tunj_komunikasi,\n" +
                "                  0                                                                          as tunj_pemondokan,\n" +
                "                  0                                                                          as tunj_lembur,\n" +
                "                  0                                                                          as tunj_supervisi,\n" +
                "                  0                                                                          as tunj_lokal,\n" +
                "                  0                                                                          as tunj_siaga,\n" +
                "                  0                                                                          as iuran_kopkar_bln_lalu,\n" +
                "                  0                                                                          as iuran_sp_bln_lalu,\n" +
                "                  0                                                                          as iuran_piikb_bln_lalu,\n" +
                "                  0                                                                          as iuran_bank_mandiri_bln_lalu,\n" +
                "                  0                                                                          as iuran_bank_bri_bln_lalu,\n" +
                "                  0                                                                          as iuran_infaq_bln_lalu,\n" +
                "                  0                                                                          as iuran_perkes_dan_obat_bln_lalu,\n" +
                "                  0                                                                          as iuran_listrik_bln_lalu,\n" +
                "                  0                                                                          as iuran_profesi_bln_lalu,\n" +
                "                  0                                                                          as iuran_potongan_lain_bln_lalu,\n" +
                "                  param_bpjs.min_bpjs_ks,\n" +
                "                  param_bpjs.max_bpjs_ks,\n" +
                "                  param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "                  param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "                  param_bpjs.min_bpjs_tk,\n" +
                "                  param_bpjs.max_bpjs_tk,\n" +
                "                  param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "                  param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "                  param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "                  param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "                  param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "                  param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "                  pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "                  case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "                   case\n" +
                "                     when pegawai.jumlah_anak is not null then\n" +
                "                       case\n" +
                "                         when pegawai.jumlah_anak > 3 then 3\n" +
                "                         else pegawai.jumlah_anak end\n" +
                "                     else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                "                  pegawai.npwp                                                               as npwp,\n" +
                "                  pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "                  pegawai.nama_bank                                                          as nama_bank,\n" +
                "                  pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "                  pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "                  pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                "                  pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                "                  pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "                  posisi.kodering                                                            as kodering,\n" +
                "                  pegawai.jenis_kelamin                                                      as gender,\n                   " +
                "                  pegawai.no_bpjs_kesehatan                                                  as no_bpjs_kes,\n" +
                "                  pegawai.no_bpjs_ketenagakerjaan                                            as no_bpjs_tk,\n" +
                "                  branch.umr                                                                 as umr,\n" +
                "                  1                                                                          as multifikator\n" +
                "           from im_hris_pegawai pegawai\n" +
                "                  left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id\n" +
                "                                                           and golongan.flag = 'Y'\n" +
                "                  left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "                  left join it_hris_pegawai_position pegawai_posisi\n" +
                "                    on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                "                       pegawai_posisi.flag_digaji = 'Y'\n" +
                "                  left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "                    on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "                  left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "                  left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "                  left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "                  left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "                  left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "                  left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "                  left join im_hris_payroll_skala_gaji_bod skala_gaji\n" +
                "                    on skala_gaji.position_id = pegawai_posisi.position_id and skala_gaji.flag = 'Y' and\n" +
                "                       skala_gaji.tahun = :tahunSkalaPayroll \n" +
                "                  left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = :branchId and param_bpjs.flag = 'Y'\n" +
                "\n" +
                "            where pegawai.flag = 'Y'\n" +
                "             and (pegawai.tipe_pegawai = 'TP02' or pegawai.tipe_pegawai = 'TP01')\n" + //RAKA-update tipe pegawai
                "             and pegawai_posisi.branch_id = :branchId \n" +
                "           union\n" ;

        String queryPegTetap =
                //pegawai tetap
                "            select pegawai.nip                                                                as nip,\n" +
                "                   pegawai.nip_lama                                                           as nip_lama,\n" +
                "                   case\n" +
                "                     when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "                     else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "                   pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "                   pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "                   branch.branch_name                                                         as branch_name,\n" +
                "                   posisi.department_id                                                       as divisi,\n" +
                "                   depart.department_name                                                     as divisi_name,\n" +
                "                   posisi.bagian_id                                                           as sub_divisi,\n" +
                "                   bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "                   posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "                   kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "                   pegawai.golongan_id                                                        as golongan_id,\n" +
                "                   golongan.golongan_name                                                     as golongan_name,\n" +
                "                   golongan.golongan_pensiun                                                  as golongan_dapen,\n" +
                "                   golongan.grade_level                                                       as grade_level,\n" +
                "                   pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "                   pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "                   tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "                   pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "                   case\n" +
                "                     when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "                     else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "                   pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "                   jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "                   pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "                   posisi.position_name                                                       as posisi_name,\n" +
                "                   pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "                   profesi.profesi_name                                                       as profesi_name,\n" +
                "                   pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "                   dana_pensiun.dana_pensiun                                                  as dana_pensiun_name,\n" +
                "                   dana_pensiun.persentase_kary                                               as persen_kary_dana_pensiun,\n" +
                "                   dana_pensiun.persentase_pers                                               as persen_pers_dana_pensiun,\n" +
                "                   case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                "                   case\n" +
                "                     when skala_gaji.santunan_khusus is null then 0\n" +
                "                     else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                "                   case\n" +
                "                     when pegawai.flag_tunj_rumah = 'Y' then\n" +
                "                       case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                "                    else 0 end                                                                as tunj_rumah,\n" +
                "                   case\n" +
                "                     when pegawai.flag_tunj_listrik = 'Y' then\n" +
                "                       case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                "                     else 0 end                                                               as tunj_listrik,\n" +
                "                   case\n" +
                "                     when pegawai.flag_tunj_air = 'Y' then\n" +
                "                       case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                "                     else 0 end                                                               as tunj_air,\n" +
                "                   case\n" +
                "                     when pegawai.flag_tunj_bbm = 'Y' then\n" +
                "                       case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                "                     else 0 end                                                               as tunj_bbm,\n" +
                "                   case\n" +
                "                     when tunj_struktural.tunj_jabatan is null then 0\n" +
                "                     else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                "                   case\n" +
                "                     when tunj_struktural.tunj_struktural is null then 0\n" +
                "                     else (tunj_struktural.tunj_struktural * jenis_pegawai.persen_gaji)/100 end        as tunj_struktural,\n" +
                "                   case\n" +
                "                     when tunj_strategis.nilai is null then 0\n" +
                "                     else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                "                   0                                                                          as tunj_tambahan,\n" +
                "                   case\n" +
                "                     when skala_gaji_pensiun.nilai is null then 0\n" +
                "                     else skala_gaji_pensiun.nilai end                                        as gaji_pensiun,\n" +
                "                   case\n" +
                "                     when pegawai.dana_pensiun is null or pegawai.dana_pensiun = '' then 0\n" +
                "                     else (skala_gaji_pensiun.nilai * dana_pensiun.persentase_pers) / 100 end as iuran_pers_dapen,\n" +
                "                   case\n" +
                "                     when pegawai.dana_pensiun = 'DP01' then case\n" +
                "                                                               when skala_gaji_dplk_pegawai.nilai is null then 0\n" +
                "                                                               else skala_gaji_dplk_pegawai.nilai end\n" +
                "                     else case\n" +
                "                            when pegawai.dana_pensiun = 'DP02'\n" +
                "                                    then (skala_gaji_pensiun.nilai * dana_pensiun.persentase_kary) / 100\n" +
                "                            else 0 end end                                                    as iuran_kary_dapen,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_peralihan_gapok\n" +
                "                               else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_peralihan_sankhus\n" +
                "                               else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                "                               else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                "                   case\n" +
                "                     when kelompok.tunj_telekomunikasi is null then 0\n" +
                "                     else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_pemondokan\n" +
                "                               else 0 end end                                                 as tunj_pemondokan,\n" +
                "                  case\n" +
                "                     when absensi_lembur.tunj_lembur is null then 0\n" +
                "                     else absensi_lembur.tunj_lembur end                                      as tunj_lembur,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_supervisi\n" +
                "                               else 0 end end                                                  as tunj_supervisi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_lokasi\n" +
                "                               else 0 end end                                                  as tunj_lokasi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_siaga\n" +
                "                               else 0 end end                                                  as tunj_siaga,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_kopkar is null then 0\n" +
                "                     else transaksi_payroll.iuran_kopkar end                                          as iuran_kopkar_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_sp is null then 0\n" +
                "                     else transaksi_payroll.iuran_sp end                                        as iuran_sp_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_piikb is null then 0\n" +
                "                     else transaksi_payroll.iuran_piikb end                                     as iuran_piikb_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_bank_mandiri is null then 0\n" +
                "                     else transaksi_payroll.iuran_bank_mandiri end                                    as iuran_bank_mandiri_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_bank_bri is null then 0\n" +
                "                     else transaksi_payroll.iuran_bank_bri end                                        as iuran_bank_bri_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_infaq is null then 0\n" +
                "                     else transaksi_payroll.iuran_infaq end                                           as iuran_infaq_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_perkes_dan_obat is null then 0\n" +
                "                     else transaksi_payroll.iuran_perkes_dan_obat end                                 as iuran_perkes_dan_obat_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_listrik is null then 0\n" +
                "                     else transaksi_payroll.iuran_listrik end                                         as iuran_listrik_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_profesi is null then 0\n" +
                "                     else transaksi_payroll.iuran_profesi end                                   as iuran_profesi_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_potongan_lain is null then 0\n" +
                "                     else transaksi_payroll.iuran_potongan_lain end                                   as iuran_potongan_lain_bln_lalu,\n" +
                "\n" +
                "                   param_bpjs.min_bpjs_ks,\n" +
                "                   param_bpjs.max_bpjs_ks,\n" +
                "                   param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "                   param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "                   param_bpjs.min_bpjs_tk,\n" +
                "                   param_bpjs.max_bpjs_tk,\n" +
                "                   param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "                   param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "                   param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "                   param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "                   param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "                   param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "                   pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "                   case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "                   case\n" +
                "                     when pegawai.jumlah_anak is not null then\n" +
                "                       case\n" +
                "                         when pegawai.jumlah_anak > 3 then 3\n" +
                "                         else pegawai.jumlah_anak end\n" +
                "                      else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                "                   pegawai.npwp                                                               as npwp,\n" +
                "                   pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "                   pegawai.nama_bank                                                          as nama_bank,\n" +
                "                   pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "                   pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "                   pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                "                   pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                "                   pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "                   posisi.kodering                                                            as kodering,\n" +
                "                   pegawai.jenis_kelamin                                                      as gender,\n" +
                "                   pegawai.no_bpjs_kesehatan                                                  as no_bpjs_kes,\n" +
                "                   pegawai.no_bpjs_ketenagakerjaan                                            as no_bpjs_tk,\n" +
                "                   branch.umr                                                                 as umr,\n" +
                "                   1                                                                          as multifikator\n" +
                "            from im_hris_pegawai pegawai\n" +
                "                   left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                "                                                          golongan.flag = 'Y'\n" +
                "                   left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "                   left join it_hris_pegawai_position pegawai_posisi\n" +
                "                     on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                "                        pegawai_posisi.flag_digaji = 'Y'\n" +
                "                   left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "                     on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "                   left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "                   left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "                   left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "                   left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "                   left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "                   left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "                   left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                "                     on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                "                        tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                "                   left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                "                     on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                "                   left join im_hris_payroll_skala_gaji skala_gaji\n" +
                "                     on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                "                        skala_gaji.tahun = :tahunSkalaPayroll \n" +
                "                   left join im_hris_payroll_dana_pensiun dana_pensiun\n" +
                "                     on dana_pensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "                   left join im_hris_payroll_skala_gaji_pensiun skala_gaji_pensiun\n" +
                "                     on skala_gaji_pensiun.tipe_dapen_id = pegawai.dana_pensiun and\n" +
                "                        skala_gaji_pensiun.golongan_id = pegawai.golongan_id and\n" +
                "                        skala_gaji_pensiun.masa_kerja_gol = pegawai.masa_kerja_gol\n" +
                "                   left join im_hris_payroll_skala_gaji_dplk_pegawai skala_gaji_dplk_pegawai\n" +
                "                     on skala_gaji_dplk_pegawai.golongan_id = pegawai.golongan_id\n" +
                "                   left join it_hris_payroll transaksi_payroll\n" +
                "                     on transaksi_payroll.nip = pegawai.nip and transaksi_payroll.flag = 'Y' and\n" +
                "                        transaksi_payroll.flag = 'Y' and transaksi_payroll.bulan = :periodeSebelumnya and\n" +
                "                        transaksi_payroll.tahun = :tahunSebelumnya \n" +
                "                   left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = :branchId and param_bpjs.flag = 'Y'\n" +
                "                   left join (select nip, sum(biaya_lembur) as tunj_lembur\n" +
                "                              from it_hris_absensi_pegawai\n" +
                "                              where tanggal between to_date(:tglAwalLembur, 'DD-MM-YYYY') and to_date(:tglAkhirLembur, 'DD-MM-YYYY') and flag = 'Y'\n" +
                "                              group by nip) absensi_lembur on absensi_lembur.nip = pegawai.nip\n" +
                "                   left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                "\n" +
                "            where pegawai.flag = 'Y'\n" +
                "              and to_date(:tanggalGaji, 'DD-MM-YYYY') <= pegawai.tanggal_pensiun \n" +
                "              and (pegawai.flag_pegawai_cuti_diluar_tanggungan = 'N' or pegawai.flag_pegawai_cuti_diluar_tanggungan is null)\n" +
                "              and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                "              and pegawai_posisi.branch_id = :branchId \n" +
                "            union\n" ;

        String queryPkwt =
                //pkwt
                "            select pegawai.nip                                                                as nip,\n" +
                "                   pegawai.nip_lama                                                           as nip_lama,\n" +
                "                   case\n" +
                "                     when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "                     else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "                   pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "                   pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "                   branch.branch_name                                                         as branch_name,\n" +
                "                   posisi.department_id                                                       as divisi,\n" +
                "                   depart.department_name                                                     as divisi_name,\n" +
                "                   posisi.bagian_id                                                           as sub_divisi,\n" +
                "                   bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "                   posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "                   kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "                   pegawai.golongan_id                                                        as golongan_id,\n" +
                "                   golongan.golongan_pkwt_id                                                  as golongan_name,\n" +
                "                   ''                                                                         as golongan_dapen,\n" +
                "                   0                                                                          as grade_level,\n" +
                "                   pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "                   pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "                   tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "                   pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "                   case\n" +
                "                     when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "                     else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "                   pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "                   jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "                   pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "                   posisi.position_name                                                       as posisi_name,\n" +
                "                   pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "                   profesi.profesi_name                                                       as profesi_name,\n" +
                "                   pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "                   ''                                                                        as dana_pensiun_name,\n" +
                "                   0                                                                         as persen_kary_dana_pensiun,\n" +
                "                   0                                                                         as persen_pers_dana_pensiun,\n" +
                "                   case when skala_gaji.gaji_pokok is null then 0 else skala_gaji.gaji_pokok end\n" +
                "                                                                                              as gaji_pokok,\n" +
                "                   case\n" +
                "                     when skala_gaji.santunan_khusus is null then 0\n" +
                "                     else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                "                   0                                                                          as tunj_rumah,\n" +
                "                   0                                                                          as tunj_listrik,\n" +
                "                   0                                                                          as tunj_air,\n" +
                "                   0                                                                          as tunj_bbm,\n" +
                "                   0                                                                          as tunj_jabatan,\n" +
                "                   0                                                                          as tunj_struktural,\n" +
                "                   case\n" +
                "                     when skala_gaji.tunj_fungsional is null then 0\n" +
                "                     else skala_gaji.tunj_fungsional end                                      as tunj_fungsional,\n" +
                "                   case\n" +
                "                     when skala_gaji.tunj_tambahan is null then 0\n" +
                "                     else skala_gaji.tunj_tambahan end                                        as tunj_tambahan,\n" +
                "\n" +
                "                   0                                                                          as gaji_pensiun,\n" +
                "                   0                                                                          as iuran_pers_dapen,\n" +
                "                   0                                                                          as iuran_kary_dapen,\n" +
                "                   0                                                                          as tunj_peralihan_gapok,\n" +
                "                   0                                                                          as tunj_peralihan_sankhus,\n" +
                "                   0                                                                          as tunj_peralihan_tunjangan,\n" +
                "                   0                                                                          as tunj_komunikasi,\n" +
                "                   0                                                                          as tunj_pemondokan,\n" +
                "                   case\n" +
                "                     when absensi_lembur.tunj_lembur is null then 0\n" +
                "                     else absensi_lembur.tunj_lembur end                                      as tunj_lembur,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_supervisi\n" +
                "                               else 0 end end                                                  as tunj_supervisi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_lokasi\n" +
                "                               else 0 end end                                                  as tunj_lokasi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_siaga\n" +
                "                               else 0 end end                                                  as tunj_siaga,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_kopkar is null then 0\n" +
                "                     else transaksi_payroll.iuran_kopkar end                                          as iuran_kopkar_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_sp is null then 0\n" +
                "                     else transaksi_payroll.iuran_sp end                                        as iuran_sp_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_piikb is null then 0\n" +
                "                     else transaksi_payroll.iuran_piikb end                                     as iuran_piikb_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_bank_mandiri is null then 0\n" +
                "                     else transaksi_payroll.iuran_bank_mandiri end                                    as iuran_bank_mandiri_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_bank_bri is null then 0\n" +
                "                     else transaksi_payroll.iuran_bank_bri end                                        as iuran_bank_bri_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_infaq is null then 0\n" +
                "                     else transaksi_payroll.iuran_infaq end                                           as iuran_infaq_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_perkes_dan_obat is null then 0\n" +
                "                     else transaksi_payroll.iuran_perkes_dan_obat end                                 as iuran_perkes_dan_obat_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_listrik is null then 0\n" +
                "                     else transaksi_payroll.iuran_listrik end                                         as iuran_listrik_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_profesi is null then 0\n" +
                "                     else transaksi_payroll.iuran_profesi end                                   as iuran_profesi_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_potongan_lain is null then 0\n" +
                "                     else transaksi_payroll.iuran_potongan_lain end                                   as iuran_potongan_lain_bln_lalu,\n" +
                "                   param_bpjs.min_bpjs_ks,\n" +
                "                   param_bpjs.max_bpjs_ks,\n" +
                "                   param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "                   param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "                   param_bpjs.min_bpjs_tk,\n" +
                "                   param_bpjs.max_bpjs_tk,\n" +
                "                   param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "                   param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "                   param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "                   param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "                   param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "                   param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "                   pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "                   case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "                   case\n" +
                "                     when pegawai.jumlah_anak is not null then\n" +
                "                       case\n" +
                "                         when pegawai.jumlah_anak > 3 then 3\n" +
                "                         else pegawai.jumlah_anak end\n" +
                "                     else 0 end                                                               as jumlah_anak_ditanggung,\n" +
                "                   pegawai.npwp                                                               as npwp,\n" +
                "                   pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "                   pegawai.nama_bank                                                          as nama_bank,\n" +
                "                   pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "                   pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "                   null                                                                       as tanggal_pra_pensiun,\n" +
                "                   null                                                                       as tanggal_pensiun,\n" +
                "                   pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "                   posisi.kodering                                                            as kodering,\n" +
                "                   pegawai.jenis_kelamin                                                      as gender,\n" +
                "                   pegawai.no_bpjs_kesehatan                                                  as no_bpjs_kes,\n" +
                "                   pegawai.no_bpjs_ketenagakerjaan                                            as no_bpjs_tk,\n" +
                "                   branch.umr                                                                 as umr,\n" +
                "                   case\n" +
                "                     when trim(to_char(extract (month from pegawai.tanggal_masuk),'00')) = :periodePayroll \n" +
                "                            and trim(to_char(extract (year from pegawai.tanggal_masuk),'0000')) = :tahunPayroll  then\n" +
                "                       round(((to_date(to_char(to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') + INTERVAL '1 MONTH - 1 day','DD-MM-YYYY'),'DD-MM-YYYY')) - pegawai.tanggal_masuk + 1)  / cast(((to_date(to_char(to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') + INTERVAL '1 MONTH - 1 day','DD-MM-YYYY'),'DD-MM-YYYY') - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')) + 1) as decimal),2)\n" +
                "                     else 1 end                                                               as multifikator\n" +
                "            from im_hris_pegawai pegawai\n" +
                "                   left join im_hris_golongan_pkwt golongan on pegawai.golongan_id = golongan.golongan_pkwt_id\n" +
                "                                                                 and golongan.flag = 'Y'\n" +
                "                   left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "                   left join it_hris_pegawai_position pegawai_posisi\n" +
                "                     on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                "                        pegawai_posisi.flag_digaji = 'Y'\n" +
                "                   left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "                     on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "                   left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "                   left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "                   left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "                   left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "                   left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "                   left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "                   left join im_hris_payroll_skala_gaji_pkwt skala_gaji\n" +
                "                     on skala_gaji.golongan_pkwt_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                "                        skala_gaji.tahun = :tahunSkalaPayroll \n" +
                "                   left join it_hris_payroll transaksi_payroll\n" +
                "                     on transaksi_payroll.nip = pegawai.nip and transaksi_payroll.flag = 'Y' and\n" +
                "                        transaksi_payroll.flag = 'Y' and transaksi_payroll.bulan = :periodeSebelumnya and\n" +
                "                        transaksi_payroll.tahun = :tahunSebelumnya \n" +
                "                   left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = :branchId and param_bpjs.flag = 'Y'\n" +
                "                   left join (select nip, sum(biaya_lembur) as tunj_lembur\n" +
                "                              from it_hris_absensi_pegawai\n" +
                "                              where tanggal between to_date(:tglAwalLembur, 'DD-MM-YYYY') and to_date(:tglAkhirLembur, 'DD-MM-YYYY') and flag = 'Y'\n" +
                "                              group by nip) absensi_lembur on absensi_lembur.nip = pegawai.nip\n" +
                "                   left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                "            where pegawai.flag = 'Y'\n" +
                "              and pegawai.tipe_pegawai = 'TP04'\n" + //RAKA-update tipe pegawai
                "              and pegawai_posisi.branch_id = :branchId \n" +
                "            union\n" ;

        String queryPegTetapResign =
                // pegawai tetap yg exception ( resign )
                "            select pegawai.nip                                                                as nip,\n" +
                "                   pegawai.nip_lama                                                           as nip_lama,\n" +
                "                   case\n" +
                "                     when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "                     else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "                   pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "                   pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "                   branch.branch_name                                                         as branch_name,\n" +
                "                   posisi.department_id                                                       as divisi,\n" +
                "                   depart.department_name                                                     as divisi_name,\n" +
                "                   posisi.bagian_id                                                           as sub_divisi,\n" +
                "                   bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "                   posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "                   kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "                   pegawai.golongan_id                                                        as golongan_id,\n" +
                "                   golongan.golongan_name                                                     as golongan_name,\n" +
                "                   golongan.golongan_pensiun                                                  as golongan_dapen,\n" +
                "                   golongan.grade_level                                                       as grade_level,\n" +
                "                   pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "                   pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "                   tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "                   pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "                   case\n" +
                "                     when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "                     else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "                   pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "                   jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "                   pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "                   posisi.position_name                                                       as posisi_name,\n" +
                "                   pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "                   profesi.profesi_name                                                       as profesi_name,\n" +
                "                   pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "                   dana_pensiun.dana_pensiun                                                  as dana_pensiun_name,\n" +
                "                   dana_pensiun.persentase_kary                                               as persen_kary_dana_pensiun,\n" +
                "                   dana_pensiun.persentase_pers                                               as persen_pers_dana_pensiun,\n" +
                "                   case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                "                   case\n" +
                "                     when skala_gaji.santunan_khusus is null then 0\n" +
                "                     else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                "                   case\n" +
                "                     when pegawai.flag_tunj_rumah = 'Y' then\n" +
                "                       case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                "                     else 0 end                                                                as tunj_rumah,\n" +
                "                   case\n" +
                "                     when pegawai.flag_tunj_listrik = 'Y' then\n" +
                "                       case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                "                     else 0 end                                                               as tunj_listrik,\n" +
                "                   case\n" +
                "                     when pegawai.flag_tunj_air = 'Y' then\n" +
                "                       case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                "                     else 0 end                                                               as tunj_air,\n" +
                "                   case\n" +
                "                     when pegawai.flag_tunj_bbm = 'Y' then\n" +
                "                       case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                "                     else 0 end                                                               as tunj_bbm,\n" +
                "                   case\n" +
                "                     when tunj_struktural.tunj_jabatan is null then 0\n" +
                "                     else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                "                   case\n" +
                "                     when tunj_struktural.tunj_struktural is null then 0\n" +
                "                     else (tunj_struktural.tunj_struktural * jenis_pegawai.persen_gaji) / 100 end      as tunj_struktural,\n" +
                "                   case\n" +
                "                     when tunj_strategis.nilai is null then 0\n" +
                "                     else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                "                   0                                                                          as tunj_tambahan,\n" +
                "                   case\n" +
                "                     when skala_gaji_pensiun.nilai is null then 0\n" +
                "                     else skala_gaji_pensiun.nilai end                                        as gaji_pensiun,\n" +
                "                   case\n" +
                "                     when pegawai.dana_pensiun is null or pegawai.dana_pensiun = '' then 0\n" +
                "                     else (skala_gaji_pensiun.nilai * dana_pensiun.persentase_pers) / 100 end as iuran_pers_dapen,\n" +
                "                   case\n" +
                "                     when pegawai.dana_pensiun = 'DP01' then case\n" +
                "                                                               when skala_gaji_dplk_pegawai.nilai is null then 0\n" +
                "                                                               else skala_gaji_dplk_pegawai.nilai end\n" +
                "                     else case\n" +
                "                            when pegawai.dana_pensiun = 'DP02'\n" +
                "                                    then (skala_gaji_pensiun.nilai * dana_pensiun.persentase_kary) / 100\n" +
                "                            else 0 end end                                                    as iuran_kary_dapen,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_peralihan_gapok\n" +
                "                               else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_peralihan_sankhus\n" +
                "                               else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                "                               else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                "                   case\n" +
                "                     when kelompok.tunj_telekomunikasi is null then 0\n" +
                "                     else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_pemondokan\n" +
                "                               else 0 end end                                                 as tunj_pemondokan,\n" +
                "                   case\n" +
                "                     when absensi_lembur.tunj_lembur is null then 0\n" +
                "                     else absensi_lembur.tunj_lembur end                                      as tunj_lembur,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_supervisi\n" +
                "                               else 0 end end                                                  as tunj_supervisi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_lokasi\n" +
                "                               else 0 end end                                                  as tunj_lokasi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_siaga\n" +
                "                               else 0 end end                                                  as tunj_siaga,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_kopkar is null then 0\n" +
                "                     else transaksi_payroll.iuran_kopkar end                                          as iuran_kopkar_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_sp is null then 0\n" +
                "                     else transaksi_payroll.iuran_sp end                                        as iuran_sp_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_piikb is null then 0\n" +
                "                     else transaksi_payroll.iuran_piikb end                                     as iuran_piikb_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_bank_mandiri is null then 0\n" +
                "                     else transaksi_payroll.iuran_bank_mandiri end                                    as iuran_bank_mandiri_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_bank_bri is null then 0\n" +
                "                     else transaksi_payroll.iuran_bank_bri end                                        as iuran_bank_bri_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_infaq is null then 0\n" +
                "                     else transaksi_payroll.iuran_infaq end                                           as iuran_infaq_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_perkes_dan_obat is null then 0\n" +
                "                     else transaksi_payroll.iuran_perkes_dan_obat end                                 as iuran_perkes_dan_obat_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_listrik is null then 0\n" +
                "                     else transaksi_payroll.iuran_listrik end                                         as iuran_listrik_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_profesi is null then 0\n" +
                "                     else transaksi_payroll.iuran_profesi end                                   as iuran_profesi_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_potongan_lain is null then 0\n" +
                "                     else transaksi_payroll.iuran_potongan_lain end                                   as iuran_potongan_lain_bln_lalu,\n" +
                "\n" +
                "                   param_bpjs.min_bpjs_ks,\n" +
                "                   param_bpjs.max_bpjs_ks,\n" +
                "                   param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "                   param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "                   param_bpjs.min_bpjs_tk,\n" +
                "                   param_bpjs.max_bpjs_tk,\n" +
                "                   param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "                   param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "                   param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "                   param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "                   param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "                   param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "                   pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "                   case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "                   case\n" +
                "                     when pegawai.jumlah_anak is not null then\n" +
                "                       case\n" +
                "                         when pegawai.jumlah_anak > 3 then 3\n" +
                "                         else pegawai.jumlah_anak end\n" +
                "                     else 0 end                                                               as jumlah_anak_ditanggung,\n" +
                "                   pegawai.npwp                                                               as npwp,\n" +
                "                   pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "                   pegawai.nama_bank                                                          as nama_bank,\n" +
                "                   pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "                   pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "                   pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                "                   pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                "                   pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "                   posisi.kodering                                                            as kodering,\n" +
                "                   pegawai.jenis_kelamin                                                      as gender,\n" +
                "                   pegawai.no_bpjs_kesehatan                                                  as no_bpjs_kes,\n" +
                "                   pegawai.no_bpjs_ketenagakerjaan                                            as no_bpjs_tk,\n" +
                "                   branch.umr                                                                 as umr,\n" +
                "                   case\n" +
                "                     when trim(to_char(extract (month from pegawai.tanggal_keluar),'00')) = :periodePayroll \n" +
                "                            and trim(to_char(extract (year from pegawai.tanggal_keluar),'0000')) = :tahunPayroll  then\n" +
                "                       round((pegawai.tanggal_keluar - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')  + 1)  / cast(((to_date(to_char(to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') + INTERVAL '1 MONTH - 1 day','DD-MM-YYYY'),'DD-MM-YYYY') - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')) + 1)as decimal),2)\n" +
                "                     else 1 end                                                               as multifikator\n" +
                "            from im_hris_pegawai pegawai\n" +
                "                   left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                "                                                          golongan.flag = 'Y'\n" +
                "                   left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "                   left join it_hris_pegawai_position pegawai_posisi\n" +
                "                     on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'N' and\n" +
                "                        pegawai_posisi.flag_digaji = 'Y'\n" +
                "                   left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "                     on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "                   left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "                   left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "                   left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "                   left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "                   left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "                   left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "                   left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                "                     on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                "                        tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                "                   left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                "                     on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                "                   left join im_hris_payroll_skala_gaji skala_gaji\n" +
                "                     on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                "                        skala_gaji.tahun = :tahunSkalaPayroll \n" +
                "                   left join im_hris_payroll_dana_pensiun dana_pensiun\n" +
                "                     on dana_pensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "                   left join im_hris_payroll_skala_gaji_pensiun skala_gaji_pensiun\n" +
                "                     on skala_gaji_pensiun.tipe_dapen_id = pegawai.dana_pensiun and\n" +
                "                        skala_gaji_pensiun.golongan_id = pegawai.golongan_id and\n" +
                "                        skala_gaji_pensiun.masa_kerja_gol = pegawai.masa_kerja_gol\n" +
                "                   left join im_hris_payroll_skala_gaji_dplk_pegawai skala_gaji_dplk_pegawai\n" +
                "                     on skala_gaji_dplk_pegawai.golongan_id = pegawai.golongan_id\n" +
                "                   left join it_hris_payroll transaksi_payroll\n" +
                "                     on transaksi_payroll.nip = pegawai.nip and transaksi_payroll.flag = 'Y' and\n" +
                "                        transaksi_payroll.flag = 'Y' and transaksi_payroll.bulan = :periodeSebelumnya and\n" +
                "                        transaksi_payroll.tahun = :tahunSebelumnya \n" +
                "                   left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = :branchId and param_bpjs.flag = 'Y'\n" +
                "                   left join (select nip, sum(biaya_lembur) as tunj_lembur\n" +
                "                              from it_hris_absensi_pegawai\n" +
                "                              where tanggal between to_date(:tglAwalLembur, 'DD-MM-YYYY') and to_date(:tglAkhirLembur, 'DD-MM-YYYY') and flag = 'Y'\n" +
                "                              group by nip) absensi_lembur on absensi_lembur.nip = pegawai.nip\n" +
                "                   left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                "\n" +
                "            where (pegawai.flag = 'N' and cast(extract(month from pegawai.tanggal_keluar) as varchar)  = :periodePayroll and cast(extract(year from pegawai.tanggal_keluar) as varchar)  = :tahunPayroll) \n" +
                "              and to_date(:tanggalGaji, 'DD-MM-YYYY') <= pegawai.tanggal_pensiun \n" +
                "              and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                "              and pegawai_posisi.branch_id = :branchId \n" +
                "            union\n" ;

        String queryPkwtResign =
                //pkwt yang exception (resign)
                "            select pegawai.nip                                                                as nip,\n" +
                "                   pegawai.nip_lama                                                           as nip_lama,\n" +
                "                   case\n" +
                "                     when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                "                     else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                "                   pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                "                   pegawai_posisi.branch_id                                                   as branch_id,\n" +
                "                   branch.branch_name                                                         as branch_name,\n" +
                "                   posisi.department_id                                                       as divisi,\n" +
                "                   depart.department_name                                                     as divisi_name,\n" +
                "                   posisi.bagian_id                                                           as sub_divisi,\n" +
                "                   bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                "                   posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                "                   kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                "                   pegawai.golongan_id                                                        as golongan_id,\n" +
                "                   golongan.golongan_pkwt_id                                                  as golongan_name,\n" +
                "                   ''                                                                         as golongan_dapen,\n" +
                "                   0                                                                          as grade_level,\n" +
                "                   pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                "                   pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                "                   tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                "                   pegawai.status_pegawai                                                     as status_pegawai,\n" +
                "                   case\n" +
                "                     when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                "                     else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                "                   pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                "                   jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                "                   pegawai_posisi.position_id                                                 as posisi_id,\n" +
                "                   posisi.position_name                                                       as posisi_name,\n" +
                "                   pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                "                   profesi.profesi_name                                                       as profesi_name,\n" +
                "                   pegawai.dana_pensiun                                                       as dana_pensiun,\n" +
                "                   ''                                                                         as dana_pensiun_name,\n" +
                "                   0                                                                          as persen_kary_dana_pensiun,\n" +
                "                   0                                                                          as persen_pers_dana_pensiun,\n" +
                "                   case when skala_gaji.gaji_pokok is null then 0 else skala_gaji.gaji_pokok end\n" +
                "                                                                                              as gaji_pokok,\n" +
                "                   case\n" +
                "                     when skala_gaji.santunan_khusus is null then 0\n" +
                "                     else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                "                   0                                                                          as tunj_rumah,\n" +
                "                   0                                                                          as tunj_listrik,\n" +
                "                   0                                                                          as tunj_air,\n" +
                "                   0                                                                          as tunj_bbm,\n" +
                "                   0                                                                          as tunj_jabatan,\n" +
                "                   0                                                                          as tunj_struktural,\n" +
                "                   case\n" +
                "                     when skala_gaji.tunj_fungsional is null then 0\n" +
                "                     else skala_gaji.tunj_fungsional end                                      as tunj_fungsional,\n" +
                "                   case\n" +
                "                     when skala_gaji.tunj_tambahan is null then 0\n" +
                "                     else skala_gaji.tunj_tambahan end                                        as tunj_tambahan,\n" +
                "\n" +
                "                   0                                                                          as gaji_pensiun,\n" +
                "                   0                                                                          as iuran_pers_dapen,\n" +
                "                   0                                                                          as iuran_kary_dapen,\n" +
                "                   0                                                                          as tunj_peralihan_gapok,\n" +
                "                   0                                                                          as tunj_peralihan_sankhus,\n" +
                "                   0                                                                          as tunj_peralihan_tunjangan,\n" +
                "                   0                                                                          as tunj_komunikasi,\n" +
                "                   0                                                                          as tunj_pemondokan,\n" +
                "                   case\n" +
                "                     when absensi_lembur.tunj_lembur is null then 0\n" +
                "                     else absensi_lembur.tunj_lembur end                                      as tunj_lembur,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_supervisi\n" +
                "                               else 0 end end                                                  as tunj_supervisi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_lokasi\n" +
                "                               else 0 end end                                                  as tunj_lokasi,\n" +
                "                   case\n" +
                "                     when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                "                     else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                "                                    then tunj_lainnya.tunj_siaga\n" +
                "                               else 0 end end                                                  as tunj_siaga,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_kopkar is null then 0\n" +
                "                     else transaksi_payroll.iuran_kopkar end                                          as iuran_kopkar_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_sp is null then 0\n" +
                "                     else transaksi_payroll.iuran_sp end                                        as iuran_sp_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_piikb is null then 0\n" +
                "                     else transaksi_payroll.iuran_piikb end                                     as iuran_piikb_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_bank_mandiri is null then 0\n" +
                "                     else transaksi_payroll.iuran_bank_mandiri end                                    as iuran_bank_mandiri_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_bank_bri is null then 0\n" +
                "                     else transaksi_payroll.iuran_bank_bri end                                        as iuran_bank_bri_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_infaq is null then 0\n" +
                "                     else transaksi_payroll.iuran_infaq end                                           as iuran_infaq_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_perkes_dan_obat is null then 0\n" +
                "                     else transaksi_payroll.iuran_perkes_dan_obat end                                 as iuran_perkes_dan_obat_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_listrik is null then 0\n" +
                "                     else transaksi_payroll.iuran_listrik end                                         as iuran_listrik_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_profesi is null then 0\n" +
                "                     else transaksi_payroll.iuran_profesi end                                   as iuran_profesi_bln_lalu,\n" +
                "                   case\n" +
                "                     when transaksi_payroll.iuran_potongan_lain is null then 0\n" +
                "                     else transaksi_payroll.iuran_potongan_lain end                                   as iuran_potongan_lain_bln_lalu,\n" +
                "                   param_bpjs.min_bpjs_ks,\n" +
                "                   param_bpjs.max_bpjs_ks,\n" +
                "                   param_bpjs.percent_ks_kary                                                 as persen_bpjs_ks_kary,\n" +
                "                   param_bpjs.percent_ks_pers                                                 as persen_bpjs_ks_pers,\n" +
                "                   param_bpjs.min_bpjs_tk,\n" +
                "                   param_bpjs.max_bpjs_tk,\n" +
                "                   param_bpjs.iur_peg                                                         as persen_bpjs_tk_iuran_kary,\n" +
                "                   param_bpjs.jpk_kary                                                        as persen_bpjs_tk_jpk_kary,\n" +
                "                   param_bpjs.jkk                                                             as persen_bpjs_tk_jkk_pers,\n" +
                "                   param_bpjs.jht                                                             as persen_bpjs_tk_jht_pers,\n" +
                "                   param_bpjs.jkm                                                             as persen_bpjs_tk_jkm_pers,\n" +
                "                   param_bpjs.jpk_pers                                                        as persen_bpjs_tk_jpk_pers,\n" +
                "                   pegawai.status_keluarga                                                    as status_keluarga,\n" +
                "                   case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                "                   case\n" +
                "                     when pegawai.jumlah_anak is not null then\n" +
                "                       case\n" +
                "                         when pegawai.jumlah_anak > 3 then 3\n" +
                "                         else pegawai.jumlah_anak end\n" +
                "                     else 0 end                                                               as jumlah_anak_ditanggung,\n" +
                "                   pegawai.npwp                                                               as npwp,\n" +
                "                   pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                "                   pegawai.nama_bank                                                          as nama_bank,\n" +
                "                   pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                "                   pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                "                   null                                                                       as tanggal_pra_pensiun,\n" +
                "                   null                                                                       as tanggal_pensiun,\n" +
                "                   pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                "                   posisi.kodering                                                            as kodering,\n" +
                "                   pegawai.jenis_kelamin                                                      as gender,\n" +
                "                   pegawai.no_bpjs_kesehatan                                                  as no_bpjs_kes,\n" +
                "                   pegawai.no_bpjs_ketenagakerjaan                                            as no_bpjs_tk,\n" +
                "                   branch.umr                                                                 as umr,\n" +
                "                   case\n" +
                "                     when trim(to_char(extract (month from pegawai.tanggal_keluar),'00')) = :periodePayroll \n" +
                "                            and trim(to_char(extract (year from pegawai.tanggal_keluar),'0000')) = :tahunPayroll  then\n" +
                "                       round((pegawai.tanggal_keluar - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')  + 1)  / cast(((to_date(to_char(to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') + INTERVAL '1 MONTH - 1 day','DD-MM-YYYY'),'DD-MM-YYYY') - to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')) + 1) as decimal),2)\n" +
                "                     else 1 end                                                               as multifikator\n" +
                "            from im_hris_pegawai pegawai\n" +
                "                   left join im_hris_golongan_pkwt golongan on pegawai.golongan_id = golongan.golongan_pkwt_id\n" +
                "                                                                 and golongan.flag = 'Y'\n" +
                "                   left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                "                   left join it_hris_pegawai_position pegawai_posisi\n" +
                "                     on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'N' and\n" +
                "                        pegawai_posisi.flag_digaji = 'Y'\n" +
                "                   left join im_hris_jenis_pegawai jenis_pegawai\n" +
                "                     on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                "                   left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                "                   left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                "                   left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                "                   left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "                   left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "                   left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                "                   left join im_hris_payroll_skala_gaji_pkwt skala_gaji\n" +
                "                     on skala_gaji.golongan_pkwt_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                "                        skala_gaji.tahun = :tahunSkalaPayroll\n" +
                "                   left join it_hris_payroll transaksi_payroll\n" +
                "                     on transaksi_payroll.nip = pegawai.nip and transaksi_payroll.flag = 'Y' and\n" +
                "                        transaksi_payroll.flag = 'Y' and transaksi_payroll.bulan = :periodeSebelumnya and\n" +
                "                        transaksi_payroll.tahun = :tahunSebelumnya \n" +
                "                   left join im_hris_payroll_bpjs param_bpjs on param_bpjs.branch_id = :branchId and param_bpjs.flag = 'Y'\n" +
                "                   left join (select nip, sum(biaya_lembur) as tunj_lembur\n" +
                "                              from it_hris_absensi_pegawai\n" +
                "                              where tanggal between to_date(:tglAwalLembur, 'DD-MM-YYYY') and to_date(:tglAkhirLembur, 'DD-MM-YYYY') and flag = 'Y'\n" +
                "                              group by nip) absensi_lembur on absensi_lembur.nip = pegawai.nip\n" +
                "                   left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                "\n" +
                "            where (pegawai.flag = 'N' and cast(extract(month from pegawai.tanggal_keluar) as varchar)  = :periodePayroll and cast(extract(year from pegawai.tanggal_keluar) as varchar)  = :tahunPayroll) \n" +
                "              and pegawai.tipe_pegawai = 'TP04'\n" + //RAKA-update tipe pegawai
                "              and pegawai_posisi.branch_id = :branchId \n" +
                "            order by status_pegawai desc ) peg\n" +
                "             left join im_hris_payroll_ptkp ptkp on ptkp.status_keluarga = peg.status_keluarga\n" +
                "                                                      and ptkp.jumlah_tanggungan = peg.jumlah_anak_ditanggung and ptkp.flag = 'Y'\n" +
                "               order by grade_level, masa_kerja_gol desc , nip) kary";

        String query = org.apache.commons.lang.StringUtils.join(new String[]{queryHeader,queryBodBoc,queryPegTetap,queryPkwt,queryPegTetapResign,queryPkwtResign});

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("branchId", branchId)
                .setParameter("periodePayroll", periodePayroll)
                .setParameter("tahunPayroll", tahunPayroll)
                .setParameter("tanggalGaji", tanggalGaji)
                .setParameter("tahunSkalaPayroll", tahunSkalaPayroll)
                .setParameter("tglAwalLembur", tanggalAwalLembur)
                .setParameter("tglAkhirLembur", tanggalAkhirLembur)
                .setParameter("periodeSebelumnya", periodePayrollSebelumnya)
                .setParameter("tahunSebelumnya", tahunPayrollSebelumnya)
                .list();

        for (Object[] row : results) {
            PegawaiPayroll result  = new PegawaiPayroll();

            result.setTipePayroll(CommonConstant.CODE_PAYROLL);

            //set periode,tahun,dan skala gaji, dan tanggal awal dan akhir lembur
            result.setPeriodePayroll(periodePayroll);
            result.setTahunPayroll(tahunPayroll);
            result.setTahunSkalaGaji(tahunSkalaPayroll);
            result.setTanggalAwalLembur(CommonUtil.convertStringToDate(tanggalAwalLembur));
            result.setTanggalAkhirLembur(CommonUtil.convertStringToDate(tanggalAkhirLembur));

            result.setNip((String) row[0]);
            result.setNipLama(row[1]!=null ? (String) row[1] : "");
            result.setGelar(row[2]!=null ? (String) row[2] : "");
            result.setNamaPegawai((String) row[3]);
            result.setBranchId(row[4]!=null ? (String) row[4] : "");
            result.setBranchName(row[5]!=null ? (String) row[5] : "");
            result.setDivisiId(row[6]!=null ? (String) row[6] : "");
            result.setDivisiName(row[7]!=null ? (String) row[7] : "");
            result.setSubDivisiId(row[8]!=null ? (String) row[8] : "");
            result.setSubDivisiName(row[9]!=null ? (String) row[9] : "");
            result.setKelompokPosisiId(row[10]!=null ? (String) row[10] : "");
            result.setKelompokPosisiName(row[11]!=null ? (String) row[11] : "");
            result.setGolonganId(row[12]!=null ? (String) row[12] : "");
            result.setGolonganName(row[13]!=null ? (String) row[13] : "");

            result.setGolonganDapen(row[14]!=null ? (String) row[14] : "" );
            result.setGradeLevel(row[15]!=null ? (Integer) row[15] : null );
            result.setStGradeLevel(row[15]!=null ? ((Integer) row[15]).toString() : "");

            result.setMasaKerjaGol((String) row[16]);
            result.setiMasaKerjaGol(Integer.parseInt(result.getMasaKerjaGol()));

            result.setTipePegawai(row[17]!=null ? (String) row[17] : "");
            result.setTipePegawaiName(row[18]!=null ? (String) row[18] : "");
            result.setStatusPegawai(row[19]!=null ? (String) row[19] : "");
            result.setStatusPegawaiName(row[20]!=null ? (String) row[20] : "");
            result.setJenisPegawai(row[21]!=null ? (String) row[21] : "");
            result.setJenisPegawaiName(row[22]!=null ? (String) row[22] : "");

            result.setPosisiId(row[23]!=null ? (String) row[23] : "");
            result.setPosisiName(row[24]!=null ? (String) row[24] : "");
            result.setProfesiId(row[25]!=null ? (String) row[25] : "");
            result.setProfesiName(row[26]!=null ? (String) row[26] : "");

            result.setDanaPensiun(row[27]!=null ? (String) row[27] : "");
            result.setDanaPensiunName(row[28]!=null ? (String) row[28] : "");

            result.setPersenDapenKaryNilai(row[29]!=null ? (BigDecimal) row[29] : new BigDecimal(0));
            result.setPersenDapenPershNilai(row[30]!=null ? (BigDecimal) row[30] : new BigDecimal(0));
            result.setPersenDapenKary(CommonUtil.numbericFormat(result.getPersenDapenKaryNilai(),"###,###"));
            result.setPersenDapenPersh(CommonUtil.numbericFormat(result.getPersenDapenPershNilai(),"###,###"));

            result.setGajiPokokNilai(row[31]!=null ? (BigDecimal) row[31] : new BigDecimal(0));
            result.setGajiPokokNilai(result.getGajiPokokNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setGajiPokok(CommonUtil.numbericFormat(result.getGajiPokokNilai(),"###,###"));

            result.setSantunanKhususNilai(row[32]!=null ? (BigDecimal) row[32] : new BigDecimal(0));
            result.setSantunanKhususNilai(result.getSantunanKhususNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setSantunanKhusus(CommonUtil.numbericFormat(result.getSantunanKhususNilai(),"###,###"));

            result.setTunjRumahNilai(row[33]!=null ? (BigDecimal) row[33] : new BigDecimal(0));
            result.setTunjRumahNilai(result.getTunjRumahNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjRumah(CommonUtil.numbericFormat(result.getTunjRumahNilai(),"###,###"));

            result.setTunjListrikNilai(row[34]!=null ? (BigDecimal) row[34] : new BigDecimal(0));
            result.setTunjListrikNilai(result.getTunjListrikNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjListrik(CommonUtil.numbericFormat(result.getTunjListrikNilai(),"###,###"));

            result.setTunjAirNilai(row[35]!=null ? (BigDecimal) row[35] : new BigDecimal(0));
            result.setTunjAirNilai(result.getTunjAirNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjAir(CommonUtil.numbericFormat(result.getTunjAirNilai(),"###,###"));

            result.setTunjBbmNilai(row[36]!=null ? (BigDecimal) row[36] : new BigDecimal(0));
            result.setTunjBbmNilai(result.getTunjBbmNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjBbm(CommonUtil.numbericFormat(result.getTunjBbmNilai(),"###,###"));

            result.setTunjJabatanNilai(row[37]!=null ? (BigDecimal) row[37] : new BigDecimal(0));
            result.setTunjJabatanNilai(result.getTunjJabatanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjJabatan(CommonUtil.numbericFormat(result.getTunjJabatanNilai(),"###,###"));

            result.setTunjStrukturalNilai(row[38]!=null ? (BigDecimal) row[38] : new BigDecimal(0));
            result.setTunjStrukturalNilai(result.getTunjStrukturalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjStruktural(CommonUtil.numbericFormat(result.getTunjStrukturalNilai(),"###,###"));

            result.setTunjFungsionalNilai(row[39]!=null ? (BigDecimal) row[39] : new BigDecimal(0));
            result.setTunjFungsionalNilai(result.getTunjFungsionalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjFungsional(CommonUtil.numbericFormat(result.getTunjFungsionalNilai(),"###,###"));

            result.setTunjTambahanNilai(row[40]!=null ? (BigDecimal)row[40] : new BigDecimal(0));
            result.setTunjTambahanNilai(result.getTunjTambahanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjTambahan(CommonUtil.numbericFormat(result.getTunjTambahanNilai(),"###,###"));

            result.setGajiPensiunNilai(row[41]!=null ? (BigDecimal) row[41] : new BigDecimal(0));
            result.setGajiPensiunNilai(result.getGajiPensiunNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setGajiPensiun(CommonUtil.numbericFormat(result.getGajiPensiunNilai(),"###,###"));

            result.setIuranDapenPershNilai(row[42]!=null ? (BigDecimal) row[42] : new BigDecimal(0));
            result.setIuranDapenPershNilai(result.getIuranDapenPershNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setIuranDapenPersh(CommonUtil.numbericFormat(result.getIuranDapenPershNilai(),"###,###"));

            result.setIuranDapenKaryNilai(row[43]!=null ? (BigDecimal) row[43] : new BigDecimal(0));
            result.setIuranDapenKaryNilai(result.getIuranDapenKaryNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setIuranDapenKary(CommonUtil.numbericFormat(result.getIuranDapenKaryNilai(),"###,###"));

            result.setTunjPeralihanGapokNilai(row[44]!=null ? (BigDecimal) row[44] : new BigDecimal(0));
            result.setTunjPeralihanGapokNilai(result.getTunjPeralihanGapokNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanGapok(CommonUtil.numbericFormat(result.getTunjPeralihanGapokNilai(),"###,###"));

            result.setTunjPeralihanSankhusNilai(row[45]!=null ? (BigDecimal) row[45] : new BigDecimal(0));
            result.setTunjPeralihanSankhusNilai(result.getTunjPeralihanSankhusNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanSankhus(CommonUtil.numbericFormat(result.getTunjPeralihanSankhusNilai(),"###,###"));

            result.setTunjPeralihanTunjNilai(row[46]!=null ? (BigDecimal) row[46] : new BigDecimal(0));
            result.setTunjPeralihanTunjNilai(result.getTunjPeralihanTunjNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanTunj(CommonUtil.numbericFormat(result.getTunjPeralihanTunjNilai(),"###,###"));

            result.setTunjKomunikasiNilai(row[47]!=null ? (BigDecimal) row[47] : new BigDecimal(0));
            result.setTunjKomunikasiNilai(result.getTunjKomunikasiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjKomunikasi(CommonUtil.numbericFormat(result.getTunjKomunikasiNilai(),"###,###"));

            result.setTunjPemondokanNilai(row[48]!=null ? (BigDecimal) row[48] : new BigDecimal(0));
            result.setTunjPemondokanNilai(result.getTunjPemondokanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPemondokan(CommonUtil.numbericFormat(result.getTunjPemondokanNilai(),"###,###"));

            result.setTunjLemburNilai(row[49]!=null ? (BigDecimal) row[49] : new BigDecimal(0));
            result.setTunjLemburNilai(result.getTunjLemburNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjLembur(CommonUtil.numbericFormat(result.getTunjLemburNilai(),"###,###"));

            result.setTunjSupervisiNilai(row[50]!=null ? (BigDecimal) row[50] : new BigDecimal(0));
            result.setTunjSupervisiNilai(result.getTunjSupervisiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjSupervisi(CommonUtil.numbericFormat(result.getTunjSupervisiNilai(),"###,###"));

            result.setTunjLokalNilai(row[51]!=null ? (BigDecimal) row[51] : new BigDecimal(0));
            result.setTunjLokalNilai(result.getTunjLokalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjLokal(CommonUtil.numbericFormat(result.getTunjLokalNilai(),"###,###"));

            result.setTunjSiagaNilai(row[52]!=null ? (BigDecimal) row[52] : new BigDecimal(0));
            result.setTunjSiagaNilai(result.getTunjSiagaNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjSiaga(CommonUtil.numbericFormat(result.getTunjSiagaNilai(),"###,###"));

            result.setKopkarNilai(row[53]!=null ? (BigDecimal) row[53] : new BigDecimal(0));
            result.setKopkar(CommonUtil.numbericFormat(result.getKopkarNilai(),"###,###"));

            result.setIuranSpNilai(row[54]!=null ? (BigDecimal) row[54] : new BigDecimal(0));
            result.setIuranSp(CommonUtil.numbericFormat(result.getIuranSpNilai(),"###,###"));

            result.setIuranPiikbNilai(row[55]!=null ? (BigDecimal) row[55] : new BigDecimal(0));
            result.setIuranPiikb(CommonUtil.numbericFormat(result.getIuranPiikbNilai(),"###,###"));

            result.setBankMandiriNilai(row[56]!=null ? (BigDecimal) row[56] : new BigDecimal(0));
            result.setBankMandiri(CommonUtil.numbericFormat(result.getBankMandiriNilai(),"###,###"));

            result.setBankBriNilai(row[57]!=null ? (BigDecimal) row[57] : new BigDecimal(0));
            result.setBankBri(CommonUtil.numbericFormat(result.getBankBriNilai(),"###,###"));

            result.setInfaqNilai(row[58]!=null ? (BigDecimal) row[58] : new BigDecimal(0));
            result.setInfaq(CommonUtil.numbericFormat(result.getInfaqNilai(),"###,###"));

            result.setPerkesDanObatNilai(row[59]!=null ? (BigDecimal) row[59] : new BigDecimal(0));
            result.setPerkesDanObat(CommonUtil.numbericFormat(result.getPerkesDanObatNilai(),"###,###"));

            result.setListrikNilai(row[60]!=null ? (BigDecimal) row[60] : new BigDecimal(0));
            result.setListrik(CommonUtil.numbericFormat(result.getListrikNilai(),"###,###"));

            result.setIuranProfesiNilai(row[61]!=null ? (BigDecimal) row[61] : new BigDecimal(0));
            result.setIuranProfesi(CommonUtil.numbericFormat(result.getIuranProfesiNilai(),"###,###"));

            result.setPotonganLainNilai(row[62]!=null ? (BigDecimal) row[62] : new BigDecimal(0));
            result.setPotonganLain(CommonUtil.numbericFormat(result.getPotonganLainNilai(),"###,###"));

            result.setMinBpjsKsNilai(row[63]!=null ? (BigDecimal) row[63] : new BigDecimal(0));
            result.setMinBpjsKsNilai(result.getMinBpjsKsNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setMinBpjsKs(CommonUtil.numbericFormat(result.getMinBpjsKsNilai(),"###,###"));

            result.setMaxBpjsKsNilai(row[64]!=null ? (BigDecimal) row[64] : new BigDecimal(0));
            result.setMaxBpjsKsNilai(result.getMaxBpjsKsNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setMaxBpjsKs(CommonUtil.numbericFormat(result.getMaxBpjsKsNilai(),"###,###"));

            result.setPersenBpjsKsKaryNilai(row[65]!=null ? (BigDecimal) row[65] : new BigDecimal(0));
            result.setPersenBpjsKsKary(CommonUtil.numbericFormat(result.getPersenBpjsKsKaryNilai(),"###,###"));

            result.setPersenBpjsKsPershNilai(row[66]!=null ? (BigDecimal) row[66] : new BigDecimal(0));
            result.setPersenBpjsKsPersh(CommonUtil.numbericFormat(result.getPersenBpjsKsPershNilai(),"###,###"));

            result.setMinBpjsTkNilai(row[67]!=null ? (BigDecimal) row[67] : new BigDecimal(0));
            result.setTunjStrukturalNilai(result.getTunjStrukturalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setMinBpjsTk(CommonUtil.numbericFormat(result.getMinBpjsTkNilai(),"###,###"));

            result.setMaxBpjsTkNilai(row[68]!=null ? (BigDecimal) row[68] : new BigDecimal(0));
            result.setMaxBpjsTkNilai(result.getMaxBpjsTkNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setMinBpjsTk(CommonUtil.numbericFormat(result.getMinBpjsTkNilai(),"###,###"));

            result.setPersenBpjsTkIuranKaryNilai(row[69]!=null ? (BigDecimal) row[69] : new BigDecimal(0));
            result.setPersenBpjsTkIuranKary(CommonUtil.numbericFormat(result.getPersenBpjsTkIuranKaryNilai(),"###,###"));

            result.setPersenBpjsTkJpkKaryNilai(row[70]!=null ? (BigDecimal) row[70] : new BigDecimal(0));
            result.setPersenBpjsTkJpkKary(CommonUtil.numbericFormat(result.getPersenBpjsTkJpkKaryNilai(),"###,###"));

            result.setPersenBpjsTkJkkPershNilai(row[71]!=null ? (BigDecimal) row[71] : new BigDecimal(0));
            result.setPersenBpjsTkJkkPersh(CommonUtil.numbericFormat(result.getPersenBpjsTkJkkPershNilai(),"###,###"));

            result.setPersenBpjsTkJhtPershNilai(row[72]!=null ? (BigDecimal) row[72] : new BigDecimal(0));
            result.setPersenBpjsTkJhtPersh(CommonUtil.numbericFormat(result.getPersenBpjsTkJhtPershNilai(),"###,###"));

            result.setPersenBpjsTkJkmPershNilai(row[73]!=null ? (BigDecimal) row[73] : new BigDecimal(0));
            result.setPersenBpjsTkJkmPersh(CommonUtil.numbericFormat(result.getPersenBpjsTkJkmPershNilai(),"###,###"));

            result.setPersenBpjsTkJpkPershNilai(row[74]!=null ? (BigDecimal) row[74] : new BigDecimal(0));
            result.setPersenBpjsTkJpkPersh(CommonUtil.numbericFormat(result.getPersenBpjsTkJpkPershNilai(),"###,###"));

            result.setStatusKeluarga(row[75]!=null ? (String) row[75] : "");
            result.setJumlahAnak(row[76]!=null ? ((BigInteger) row[76]).intValue() : new Integer(0));
            result.setStJumlahAnak(String.valueOf(result.getJumlahAnak()));

            result.setJumlahAnakDitanggung(row[77]!=null ? ((BigInteger) row[77]).intValue() : new Integer(0));
            result.setStJumlahAnakDitanggung(String.valueOf(result.getJumlahAnakDitanggung()));

            result.setNpwpPegawai(row[78]!=null ? (String) row[78] : "");
            result.setNoRekBank(row[79]!=null ? (String) row[79] : "");
            result.setNamaBank(row[80]!=null ? (String) row[80] : "");

            result.setTanggalAktif(row[81]!=null ? (Date) row[81] : null);
            result.setTanggalAkhirKontrak(row[82]!=null ? (Date) row[82] : null);
            result.setTanggalPraPensiun(row[83]!=null ? (Date) row[83] : null);
            result.setTanggalPensiun(row[84]!=null ? (Date) row[84] : null);
            result.setTanggalKeluar(row[85]!=null ? (Date) row[85] : null);

            result.setStTanggalAktif(result.getTanggalAktif()!=null ? CommonUtil.simpleDateFormat(result.getTanggalAktif()) : "");
            result.setStTanggalAkhirKontrak(result.getTanggalAkhirKontrak()!=null ? CommonUtil.simpleDateFormat(result.getTanggalAkhirKontrak()) : "");
            result.setStTanggalPraPensiun(result.getTanggalPraPensiun()!=null ? CommonUtil.simpleDateFormat(result.getTanggalPraPensiun()) : "");
            result.setStTanggalPensiun(result.getTanggalPensiun()!=null ? CommonUtil.simpleDateFormat(result.getTanggalPensiun()) : "");
            result.setStTanggalKeluar(result.getTanggalKeluar()!=null ? CommonUtil.simpleDateFormat(result.getTanggalKeluar()) : "");

            result.setKodering(row[86]!=null ? (String) row[86] : "");
            result.setGender(row[87]!=null ? (String) row[87] : "");

            result.setNoBpjsKs(row[88]!=null ? (String) row[88] : "");
            result.setNoBpjsTk(row[89]!=null ? (String) row[89] : "");

            result.setUmrNilai(row[90]!=null ? (BigDecimal) row[90] : new BigDecimal(0));
            result.setUmr(CommonUtil.numbericFormat(result.getUmrNilai(),"###,###"));

            result.setMultifikatorNilai(row[91]!=null ? (BigDecimal) row[91] : new BigDecimal(0));
            result.setMultifikator(CommonUtil.numbericFormat(result.getMultifikatorNilai(),"###,###"));

            result.setDasarPerhitunganBpjsKsNilai(row[92]!=null ? (BigDecimal) row[92] : new BigDecimal(0));
            result.setDasarPerhitunganBpjsKsNilai(result.getDasarPerhitunganBpjsKsNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setDasarPerhitunganBpjsKs(CommonUtil.numbericFormat(result.getDasarPerhitunganBpjsKsNilai(),"###,###"));

            result.setDasarPerhitunganBpjsTkNilai(row[93]!=null ? (BigDecimal) row[93] : new BigDecimal(0));
            result.setDasarPerhitunganBpjsTkNilai(result.getDasarPerhitunganBpjsTkNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setDasarPerhitunganBpjsTk(CommonUtil.numbericFormat(result.getDasarPerhitunganBpjsTkNilai(),"###,###"));

            result.setPtkpPegawaiNilai(row[94]!=null ? (BigDecimal) row[94] : new BigDecimal(0));
            result.setPtkpPegawaiNilai(result.getPtkpPegawaiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setPtkpPegawai(CommonUtil.numbericFormat(result.getPtkpPegawaiNilai(),"###,###"));

            result.setIuranBpjsKsKaryNilai(row[95]!=null ? (BigDecimal) row[95] : new BigDecimal(0));
            result.setIuranBpjsKsKaryNilai(result.getIuranBpjsKsKaryNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setIuranBpjsKsKary(CommonUtil.numbericFormat(result.getIuranBpjsKsKaryNilai(),"###,###"));

            result.setIuranBpjsKsPersNilai(row[96]!=null ? (BigDecimal) row[96] : new BigDecimal(0));
            result.setIuranBpjsKsPersNilai(result.getIuranBpjsKsPersNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setIuranBpjsKsPers(CommonUtil.numbericFormat(result.getIuranBpjsKsPersNilai(),"###,###"));

            result.setIuranBpjsTkKaryNilai(row[97]!=null ? (BigDecimal) row[97] : new BigDecimal(0));
            result.setIuranBpjsTkKaryNilai(result.getIuranBpjsTkKaryNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setIuranBpjsTkKary(CommonUtil.numbericFormat(result.getIuranBpjsTkKaryNilai(),"###,###"));

            result.setJpkBpjsTkKaryNilai(row[98]!=null ? (BigDecimal) row[98] : new BigDecimal(0));
            result.setJpkBpjsTkKaryNilai(result.getJpkBpjsTkKaryNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setJpkBpjsTkKary(CommonUtil.numbericFormat(result.getJpkBpjsTkKaryNilai(),"###,###"));

            result.setJkkBpjsTkPershNilai(row[99]!=null ? (BigDecimal) row[99] : new BigDecimal(0));
            result.setJkkBpjsTkPershNilai(result.getJkkBpjsTkPershNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setJkkBpjsTkPersh(CommonUtil.numbericFormat(result.getJkkBpjsTkPershNilai(),"###,###"));

            result.setJhtBpjsTkPershNilai(row[100]!=null ? (BigDecimal) row[100] : new BigDecimal(0));
            result.setJhtBpjsTkPershNilai(result.getJhtBpjsTkPershNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setJhtBpjsTkPersh(CommonUtil.numbericFormat(result.getJhtBpjsTkPershNilai(),"###,###"));

            result.setJkmBpjsTkPershNilai(row[101]!=null ? (BigDecimal) row[101] : new BigDecimal(0));
            result.setJkmBpjsTkPershNilai(result.getJkmBpjsTkPershNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setJkmBpjsTkPersh(CommonUtil.numbericFormat(result.getJkmBpjsTkPershNilai(),"###,###"));

            result.setJpkBpjsTkPershNilai(row[102]!=null ? (BigDecimal) row[102] : new BigDecimal(0));
            result.setJpkBpjsTkPershNilai(result.getJpkBpjsTkPershNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setJpkBpjsTkPersh(CommonUtil.numbericFormat(result.getJpkBpjsTkPershNilai(),"###,###"));

            listOfResult.add(result);
        }
        return listOfResult;

    }

    //updated by ferdi, 01-12-2020, rebuild to get data pendapatan tidak tetap (PTP) seperti : THR, Insentif, Jasop, Cuti Panjang, Cuti Tahunan
    public List<PegawaiPayroll> getInitialDataIncomeNonRutin(String branchId,
                                                             String periodePayroll,
                                                             String tahunPayroll,
                                                             String tahunSkalaPayroll,
                                                             String tipePayroll) throws HibernateException {

        List<Object[]> results = new ArrayList<Object[]>();
        List<PegawaiPayroll> listOfResult = new ArrayList<PegawaiPayroll>();

        String query = "";

        //untuk perhitungan THR/insentif/jasop
        if (CommonConstant.CODE_THR.equalsIgnoreCase(tipePayroll) ||
                CommonConstant.CODE_INSENTIF.equalsIgnoreCase(tipePayroll) ||
                CommonConstant.CODE_JASOP.equalsIgnoreCase(tipePayroll)) {

            query = "select " +
                    "peg.*,\n" +
                    "ptkp.nilai                                                                      as ptkp_pegawai\n" +
                    "from (\n" +
                    //bod / boc
                    "     select pegawai.nip                                                                as nip,\n" +
                    "            pegawai.nip_lama                                                           as nip_lama,\n" +
                    "            case\n" +
                    "              when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "              else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "            pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "            pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "            branch.branch_name                                                         as branch_name,\n" +
                    "            posisi.department_id                                                       as divisi,\n" +
                    "            depart.department_name                                                     as divisi_name,\n" +
                    "            posisi.bagian_id                                                           as sub_divisi,\n" +
                    "            bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "            posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "            kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "            pegawai.golongan_id                                                        as golongan_id,\n" +
                    "            golongan.golongan_id                                                       as golongan_name,\n" +
                    "            0                                                                          as grade_level,\n" +
                    "            pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "            pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "            tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "            pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "            case\n" +
                    "              when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "              else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "            pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "            jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "            pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "            posisi.position_name                                                       as posisi_name,\n" +
                    "            pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "            profesi.profesi_name                                                       as profesi_name,\n" +
                    "            case when skala_gaji.gaji is null then 0 else skala_gaji.gaji end          as gaji_pokok,\n" +
                    "            0                                                                          as santunan_khusus,\n" +
                    "            case when skala_gaji.tunj_rumah is null then 0 else skala_gaji.tunj_rumah end as tunj_rumah,\n" +
                    "            0                                                                          as tunj_listrik,\n" +
                    "            0                                                                          as tunj_air,\n" +
                    "            0                                                                          as tunj_bbm,\n" +
                    "            0                                                                          as tunj_jabatan,\n" +
                    "            0                                                                          as tunj_struktural,\n" +
                    "            0                                                                          as tunj_fungsional,\n" +
                    "            0                                                                          as tunj_tambahan,\n" +
                    "            0                                                                          as tunj_peralihan_gapok,\n" +
                    "            0                                                                          as tunj_peralihan_sankhus,\n" +
                    "            0                                                                          as tunj_peralihan_tunjangan,\n" +
                    "            case\n" +
                    "              when skala_gaji.tunj_telekomunikasi is null then 0\n" +
                    "              else skala_gaji.tunj_telekomunikasi end                                  as tunj_komunikasi,\n" +
                    "            0                                                                          as tunj_pemondokan,\n" +
                    "            0                                                                          as tunj_supervisi,\n" +
                    "            0                                                                          as tunj_lokal,\n" +
                    "            0                                                                          as tunj_siaga,\n" +
                    "            pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "            case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "            case\n" +
                    "              when pegawai.jumlah_anak is not null then\n" +
                    "                case\n" +
                    "                  when pegawai.jumlah_anak > 3 then 3\n" +
                    "                  else pegawai.jumlah_anak end\n" +
                    "              else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                    "            pegawai.npwp                                                               as npwp,\n" +
                    "            pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "            pegawai.nama_bank                                                          as nama_bank,\n" +
                    "            pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "            pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "            pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "            pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "            pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "            posisi.kodering                                                            as kodering,\n" +
                    "            pegawai.jenis_kelamin                                                      as gender,\n" +
                    "            1                                                                          as multifikator\n" +
                    "     from im_hris_pegawai pegawai\n" +
                    "            left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id\n" +
                    "                                                     and golongan.flag = 'Y'\n" +
                    "            left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "            left join it_hris_pegawai_position pegawai_posisi\n" +
                    "              on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "                 pegawai_posisi.flag_digaji = 'Y'\n" +
                    "            left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "              on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "            left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "            left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "            left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "            left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "            left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "            left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "            left join im_hris_payroll_skala_gaji_bod skala_gaji\n" +
                    "              on skala_gaji.position_id = pegawai_posisi.position_id and skala_gaji.flag = 'Y' and\n" +
                    "                 skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "\n" +
                    "     where pegawai.flag = 'Y'\n" +
                    "       and (pegawai.tipe_pegawai = 'TP02' or pegawai.tipe_pegawai = 'TP01')\n" + //RAKA-update tipe pegawai
                    "       and pegawai_posisi.branch_id = :branchId\n" +
                    "     union\n" +
                    //pegawai tetap
                    "     select pegawai.nip                                                                as nip,\n" +
                    "            pegawai.nip_lama                                                           as nip_lama,\n" +
                    "            case\n" +
                    "              when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "              else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "            pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "            pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "            branch.branch_name                                                         as branch_name,\n" +
                    "            posisi.department_id                                                       as divisi,\n" +
                    "            depart.department_name                                                     as divisi_name,\n" +
                    "            posisi.bagian_id                                                           as sub_divisi,\n" +
                    "            bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "            posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "            kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "            pegawai.golongan_id                                                        as golongan_id,\n" +
                    "            golongan.golongan_name                                                     as golongan_name,\n" +
                    "            golongan.grade_level                                                       as grade_level,\n" +
                    "            pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "            pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "            tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "            pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "            case\n" +
                    "              when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "              else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "            pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "            jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "            pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "            posisi.position_name                                                       as posisi_name,\n" +
                    "            pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "            profesi.profesi_name                                                       as profesi_name,\n" +
                    "            case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                    "            case\n" +
                    "              when skala_gaji.santunan_khusus is null then 0\n" +
                    "              else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                    "            case\n" +
                    "              when pegawai.flag_tunj_rumah = 'Y' then\n" +
                    "                case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                    "              else 0 end                                                                as tunj_rumah,\n" +
                    "            case\n" +
                    "              when pegawai.flag_tunj_listrik = 'Y' then\n" +
                    "                case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                    "              else 0 end                                                               as tunj_listrik,\n" +
                    "            case\n" +
                    "              when pegawai.flag_tunj_air = 'Y' then\n" +
                    "                case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                    "              else 0 end                                                               as tunj_air,\n" +
                    "            case\n" +
                    "              when pegawai.flag_tunj_bbm = 'Y' then\n" +
                    "                case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                    "              else 0 end                                                               as tunj_bbm,\n" +
                    "            case\n" +
                    "              when tunj_struktural.tunj_jabatan is null then 0\n" +
                    "              else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                    "            case\n" +
                    "              when tunj_struktural.tunj_struktural is null then 0\n" +
                    "              else tunj_struktural.tunj_struktural end                                 as tunj_struktural,\n" +
                    "            case\n" +
                    "              when tunj_strategis.nilai is null then 0\n" +
                    "              else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                    "            0                                                                          as tunj_tambahan,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_peralihan_gapok\n" +
                    "                        else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_peralihan_sankhus\n" +
                    "                        else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                    "                        else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                    "            case\n" +
                    "              when kelompok.tunj_telekomunikasi is null then 0\n" +
                    "              else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_pemondokan\n" +
                    "                        else 0 end end                                                 as tunj_pemondokan,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_supervisi\n" +
                    "                        else 0 end end                                                  as tunj_supervisi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_lokasi\n" +
                    "                        else 0 end end                                                  as tunj_lokasi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_siaga\n" +
                    "                        else 0 end end                                                  as tunj_siaga,\n" +
                    "            pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "            case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "            case\n" +
                    "              when pegawai.jumlah_anak is not null then\n" +
                    "                case\n" +
                    "                  when pegawai.jumlah_anak > 3 then 3\n" +
                    "                  else pegawai.jumlah_anak end\n" +
                    "              else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                    "            pegawai.npwp                                                               as npwp,\n" +
                    "            pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "            pegawai.nama_bank                                                          as nama_bank,\n" +
                    "            pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "            pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "            pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "            pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "            pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "            posisi.kodering                                                            as kodering,\n" +
                    "            pegawai.jenis_kelamin                                                      as gender,\n" +
                    "            1                                                                          as multifikator\n" +
                    "     from im_hris_pegawai pegawai\n" +
                    "            left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                    "                                                   golongan.flag = 'Y'\n" +
                    "            left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "            left join it_hris_pegawai_position pegawai_posisi\n" +
                    "              on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "                 pegawai_posisi.flag_digaji = 'Y'\n" +
                    "            left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "              on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "            left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "            left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "            left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "            left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "            left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "            left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "            left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                    "              on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                    "                 tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                    "            left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                    "              on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                    "            left join im_hris_payroll_skala_gaji skala_gaji\n" +
                    "              on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                    "                 skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "            left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                    "\n" +
                    "     where pegawai.flag = 'Y'\n" +
                    "       and (pegawai.flag_pegawai_cuti_diluar_tanggungan = 'N' or pegawai.flag_pegawai_cuti_diluar_tanggungan is null)\n" +
                    "       and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                    "       and pegawai_posisi.branch_id = :branchId\n" +
                    "     union\n" +
                    //pkwt
                    "     select pegawai.nip                                                                as nip,\n" +
                    "            pegawai.nip_lama                                                           as nip_lama,\n" +
                    "            case\n" +
                    "              when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "              else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "            pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "            pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "            branch.branch_name                                                         as branch_name,\n" +
                    "            posisi.department_id                                                       as divisi,\n" +
                    "            depart.department_name                                                     as divisi_name,\n" +
                    "            posisi.bagian_id                                                           as sub_divisi,\n" +
                    "            bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "            posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "            kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "            pegawai.golongan_id                                                        as golongan_id,\n" +
                    "            golongan.golongan_pkwt_id                                                  as golongan_name,\n" +
                    "            0                                                                          as grade_level,\n" +
                    "            pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "            pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "            tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "            pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "            case\n" +
                    "              when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "              else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "            pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "            jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "            pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "            posisi.position_name                                                       as posisi_name,\n" +
                    "            pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "            profesi.profesi_name                                                       as profesi_name,\n" +
                    "            case when skala_gaji.gaji_pokok is null then 0 else skala_gaji.gaji_pokok end\n" +
                    "                                                                                       as gaji_pokok,\n" +
                    "            case\n" +
                    "              when skala_gaji.santunan_khusus is null then 0\n" +
                    "              else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                    "            0                                                                          as tunj_rumah,\n" +
                    "            0                                                                          as tunj_listrik,\n" +
                    "            0                                                                          as tunj_air,\n" +
                    "            0                                                                          as tunj_bbm,\n" +
                    "            0                                                                          as tunj_jabatan,\n" +
                    "            0                                                                          as tunj_struktural,\n" +
                    "            case\n" +
                    "              when skala_gaji.tunj_fungsional is null then 0\n" +
                    "              else skala_gaji.tunj_fungsional end                                      as tunj_fungsional,\n" +
                    "            case\n" +
                    "              when skala_gaji.tunj_tambahan is null then 0\n" +
                    "              else skala_gaji.tunj_tambahan end                                        as tunj_tambahan,\n" +
                    "            0                                                                          as tunj_peralihan_gapok,\n" +
                    "            0                                                                          as tunj_peralihan_sankhus,\n" +
                    "            0                                                                          as tunj_peralihan_tunjangan,\n" +
                    "            0                                                                          as tunj_komunikasi,\n" +
                    "            0                                                                          as tunj_pemondokan,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_supervisi\n" +
                    "                        else 0 end end                                                  as tunj_supervisi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_lokasi\n" +
                    "                        else 0 end end                                                  as tunj_lokasi,\n" +
                    "            case\n" +
                    "              when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                    "              else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                    "                             then tunj_lainnya.tunj_siaga\n" +
                    "                        else 0 end end                                                  as tunj_siaga,\n" +
                    "            pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "            case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "            case\n" +
                    "              when pegawai.jumlah_anak is not null then\n" +
                    "                case\n" +
                    "                  when pegawai.jumlah_anak > 3 then 3\n" +
                    "                  else pegawai.jumlah_anak end\n" +
                    "              else 0 end                                                               as jumlah_anak_ditanggung,\n" +
                    "            pegawai.npwp                                                               as npwp,\n" +
                    "            pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "            pegawai.nama_bank                                                          as nama_bank,\n" +
                    "            pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "            pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "            pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "            pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "            pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "            posisi.kodering                                                            as kodering,\n" +
                    "            pegawai.jenis_kelamin                                                      as gender,\n" +
                    "            case\n" +
                    "               when to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY') - INTERVAL '1 YEAR' < pegawai.tanggal_masuk  then\n" +
                    "                    round((to_date('01' || '-' || :periodePayroll || '-' || :tahunPayroll, 'DD-MM-YYYY')  - pegawai.tanggal_masuk + 1)  / cast(365 as decimal),2)\n" +
                    "               else 1 end                                                              as multifikator\n" +
                    "     from im_hris_pegawai pegawai\n" +
                    "            left join im_hris_golongan_pkwt golongan on pegawai.golongan_id = golongan.golongan_pkwt_id\n" +
                    "                                                          and golongan.flag = 'Y'\n" +
                    "            left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "            left join it_hris_pegawai_position pegawai_posisi\n" +
                    "              on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "                 pegawai_posisi.flag_digaji = 'Y'\n" +
                    "            left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "              on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "            left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "            left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "            left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "            left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "            left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "            left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "            left join im_hris_payroll_skala_gaji_pkwt skala_gaji\n" +
                    "              on skala_gaji.golongan_pkwt_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                    "                 skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "            left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                    "     where pegawai.flag = 'Y'\n" +
                    "       and pegawai.tipe_pegawai = 'TP04'\n" + //RAKA-update tipe pegawai
                    "       and pegawai_posisi.branch_id = :branchId\n" +
                    "     order by status_pegawai desc\n" +
                    "    ) peg \n" +
                    "      left join im_hris_payroll_ptkp ptkp on ptkp.status_keluarga = peg.status_keluarga\n" +
                    "                                           and ptkp.jumlah_tanggungan = peg.jumlah_anak_ditanggung and ptkp.flag = 'Y'\n" +
                    "    order by grade_level, masa_kerja_gol desc , nip" ;

            // untuk perhitungan cuti panjang dan tahunan khusus untuk pegawai tetap
        } else if (CommonConstant.CODE_CUTI_PANJANG.equalsIgnoreCase(tipePayroll) ) {

            query = "select\n" +
                    "peg.*,\n" +
                    "ptkp.nilai                                                                 as ptkp_pegawai\n" +
                    "from\n" +
                    "(" +
                    "select pegawai.nip                                                                as nip,\n" +
                    "       pegawai.nip_lama                                                           as nip_lama,\n" +
                    "       case\n" +
                    "         when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "         else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "       pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "       pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "       branch.branch_name                                                         as branch_name,\n" +
                    "       posisi.department_id                                                       as divisi,\n" +
                    "       depart.department_name                                                     as divisi_name,\n" +
                    "       posisi.bagian_id                                                           as sub_divisi,\n" +
                    "       bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "       posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "       kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "       pegawai.golongan_id                                                        as golongan_id,\n" +
                    "       golongan.golongan_name                                                     as golongan_name,\n" +
                    "       golongan.grade_level                                                       as grade_level,\n" +
                    "       pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "       pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "       tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "       pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "       case\n" +
                    "         when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "         else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "       pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "       jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "       pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "       posisi.position_name                                                       as posisi_name,\n" +
                    "       pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "       profesi.profesi_name                                                       as profesi_name,\n" +
                    "       case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                    "       case\n" +
                    "         when skala_gaji.santunan_khusus is null then 0\n" +
                    "         else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_rumah = 'Y' then\n" +
                    "           case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                    "         else 0 end                                                                as tunj_rumah,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_listrik = 'Y' then\n" +
                    "           case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                    "         else 0 end                                                               as tunj_listrik,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_air = 'Y' then\n" +
                    "           case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                    "         else 0 end                                                               as tunj_air,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_bbm = 'Y' then\n" +
                    "           case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                    "         else 0 end                                                               as tunj_bbm,\n" +
                    "       case\n" +
                    "         when tunj_struktural.tunj_jabatan is null then 0\n" +
                    "         else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                    "       case\n" +
                    "         when tunj_struktural.tunj_struktural is null then 0\n" +
                    "         else tunj_struktural.tunj_struktural end                                 as tunj_struktural,\n" +
                    "       case\n" +
                    "         when tunj_strategis.nilai is null then 0\n" +
                    "         else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                    "       0                                                                          as tunj_tambahan,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_gapok\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_sankhus\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                    "       case\n" +
                    "         when kelompok.tunj_telekomunikasi is null then 0\n" +
                    "         else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_pemondokan\n" +
                    "                   else 0 end end                                                 as tunj_pemondokan,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_supervisi\n" +
                    "                   else 0 end end                                                  as tunj_supervisi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_lokasi\n" +
                    "                   else 0 end end                                                  as tunj_lokasi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_siaga\n" +
                    "                   else 0 end end                                                  as tunj_siaga,\n" +
                    "       pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "       case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "       case\n" +
                    "         when pegawai.jumlah_anak is not null then\n" +
                    "           case\n" +
                    "             when pegawai.jumlah_anak > 3 then 3\n" +
                    "             else pegawai.jumlah_anak end\n" +
                    "         else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                    "       pegawai.npwp                                                               as npwp,\n" +
                    "       pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "       pegawai.nama_bank                                                          as nama_bank,\n" +
                    "       pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "       pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "       pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "       pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "       pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "       posisi.kodering                                                            as kodering,\n" +
                    "       pegawai.jenis_kelamin                                                      as gender,\n" +
                    "       company.persen_cuti_panjang/100                                            as multifikator \n" +
                    "from im_hris_pegawai pegawai\n" +
                    "       left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                    "                                              golongan.flag = 'Y'\n" +
                    "       left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "       left join it_hris_pegawai_position pegawai_posisi\n" +
                    "         on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "            pegawai_posisi.flag_digaji = 'Y'\n" +
                    "       left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "         on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "       left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "       left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "       left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "       left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "       left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "       left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "       left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                    "         on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                    "            tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                    "       left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                    "         on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                    "       left join im_hris_payroll_skala_gaji skala_gaji\n" +
                    "         on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                    "            skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "       left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                    "       left join im_company company on company.flag = 'Y'\n" +
                    "\n" +
                    "where pegawai.flag = 'Y'\n" +
                    "  and (pegawai.flag_pegawai_cuti_diluar_tanggungan = 'N' or pegawai.flag_pegawai_cuti_diluar_tanggungan is null)\n" +
                    "  and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                    "  and pegawai_posisi.branch_id = :branchId \n" +
                    ")peg \n" +
                    "left join im_hris_payroll_ptkp ptkp on ptkp.status_keluarga = peg.status_keluarga\n" +
                    "    and ptkp.jumlah_tanggungan = peg.jumlah_anak_ditanggung and ptkp.flag = 'Y' \n" +
                    "order by grade_level, masa_kerja_gol desc , nip";


        } else if (CommonConstant.CODE_CUTI_TAHUNAN.equalsIgnoreCase(tipePayroll)) {

            query = "select\n" +
                    "peg.*,\n" +
                    "ptkp.nilai                                                                 as ptkp_pegawai\n" +
                    "from\n" +
                    "(" +
                    "select pegawai.nip                                                                as nip,\n" +
                    "       pegawai.nip_lama                                                           as nip_lama,\n" +
                    "       case\n" +
                    "         when pegawai.gelar_depan is null or pegawai.gelar_depan = '' then pegawai.gelar_belakang\n" +
                    "         else pegawai.gelar_depan || ',' || pegawai.gelar_belakang end            as gelar,\n" +
                    "       pegawai.nama_pegawai                                                       as nama_pegawai,\n" +
                    "       pegawai_posisi.branch_id                                                   as branch_id,\n" +
                    "       branch.branch_name                                                         as branch_name,\n" +
                    "       posisi.department_id                                                       as divisi,\n" +
                    "       depart.department_name                                                     as divisi_name,\n" +
                    "       posisi.bagian_id                                                           as sub_divisi,\n" +
                    "       bagian.nama_bagian                                                         as sub_divisi_name,\n" +
                    "       posisi.kelompok_id                                                         as kelompok_posisi,\n" +
                    "       kelompok.kelompok_name                                                     as kelompok_posisi_name,\n" +
                    "       pegawai.golongan_id                                                        as golongan_id,\n" +
                    "       golongan.golongan_name                                                     as golongan_name,\n" +
                    "       golongan.grade_level                                                       as grade_level,\n" +
                    "       pegawai.masa_kerja_gol                                                     as masa_kerja_gol,\n" +
                    "       pegawai.tipe_pegawai                                                       as tipe_pegawai,\n" +
                    "       tipe_pegawai.tipe_pegawai_name                                             as tipe_pegawai_name,\n" +
                    "       pegawai.status_pegawai                                                     as status_pegawai,\n" +
                    "       case\n" +
                    "         when pegawai.status_pegawai = 'KNS' then 'Pelaksana'\n" +
                    "         else case when pegawai.status_pegawai = 'KS' then 'Pimpinan' end end     as status_pegawai_name,\n" +
                    "       pegawai_posisi.jenis_pegawai                                               as jenis_pegawai,\n" +
                    "       jenis_pegawai.jenis_pegawai_name                                           as jenis_pegawai_name,\n" +
                    "       pegawai_posisi.position_id                                                 as posisi_id,\n" +
                    "       posisi.position_name                                                       as posisi_name,\n" +
                    "       pegawai_posisi.profesi_id                                                  as profesi_id,\n" +
                    "       profesi.profesi_name                                                       as profesi_name,\n" +
                    "       case when skala_gaji.nilai is null then 0 else skala_gaji.nilai end        as gaji_pokok,\n" +
                    "       case\n" +
                    "         when skala_gaji.santunan_khusus is null then 0\n" +
                    "         else skala_gaji.santunan_khusus end                                      as santunan_khusus,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_rumah = 'Y' then\n" +
                    "           case when skala_gaji.rumah is null then 0 else skala_gaji.rumah end\n" +
                    "         else 0 end                                                                as tunj_rumah,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_listrik = 'Y' then\n" +
                    "           case when skala_gaji.listrik is null then 0 else skala_gaji.listrik end\n" +
                    "         else 0 end                                                               as tunj_listrik,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_air = 'Y' then\n" +
                    "           case when skala_gaji.air is null then 0 else skala_gaji.air end\n" +
                    "         else 0 end                                                               as tunj_air,\n" +
                    "       case\n" +
                    "         when pegawai.flag_tunj_bbm = 'Y' then\n" +
                    "           case when skala_gaji.bbm is null then 0 else skala_gaji.bbm end\n" +
                    "         else 0 end                                                               as tunj_bbm,\n" +
                    "       case\n" +
                    "         when tunj_struktural.tunj_jabatan is null then 0\n" +
                    "         else tunj_struktural.tunj_jabatan end                                    as tunj_jabatan,\n" +
                    "       case\n" +
                    "         when tunj_struktural.tunj_struktural is null then 0\n" +
                    "         else tunj_struktural.tunj_struktural end                                 as tunj_struktural,\n" +
                    "       case\n" +
                    "         when tunj_strategis.nilai is null then 0\n" +
                    "         else tunj_strategis.nilai end                                            as tunj_fungsional,\n" +
                    "       0                                                                          as tunj_tambahan,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_gapok is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_gapok = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_gapok\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_gapok,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_sankhus is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_sankhus = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_sankhus\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_sankhus,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_peralihan_tunjangan is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_peralihan_tunjangan = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_peralihan_tunjangan\n" +
                    "                   else 0 end end                                                 as tunj_peralihan_tunjangan,\n" +
                    "       case\n" +
                    "         when kelompok.tunj_telekomunikasi is null then 0\n" +
                    "         else kelompok.tunj_telekomunikasi end                                    as tunj_komunikasi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_pemondokan is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_pemondokan = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_pemondokan\n" +
                    "                   else 0 end end                                                 as tunj_pemondokan,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_supervisi is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_supervisi = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_supervisi\n" +
                    "                   else 0 end end                                                  as tunj_supervisi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_lokasi is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_lokasi = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_lokasi\n" +
                    "                   else 0 end end                                                  as tunj_lokasi,\n" +
                    "       case\n" +
                    "         when tunj_lainnya.flag_tunj_siaga is null then 0\n" +
                    "         else case when tunj_lainnya.flag_tunj_siaga = 'Y'\n" +
                    "                        then tunj_lainnya.tunj_siaga\n" +
                    "                   else 0 end end                                                  as tunj_siaga,\n" +
                    "       pegawai.status_keluarga                                                    as status_keluarga,\n" +
                    "       case when pegawai.jumlah_anak is not null then pegawai.jumlah_anak else 0 end   as jumlah_anak,\n" +
                    "       case\n" +
                    "         when pegawai.jumlah_anak is not null then\n" +
                    "           case\n" +
                    "             when pegawai.jumlah_anak > 3 then 3\n" +
                    "             else pegawai.jumlah_anak end\n" +
                    "         else 0 end                                                              as jumlah_anak_ditanggung,\n" +
                    "       pegawai.npwp                                                               as npwp,\n" +
                    "       pegawai.no_rek_bank                                                        as no_rek_bank,\n" +
                    "       pegawai.nama_bank                                                          as nama_bank,\n" +
                    "       pegawai.tanggal_aktif                                                      as tanggal_aktif,\n" +
                    "       pegawai.tanggal_akhir_kontrak                                              as tanggal_akhir_kontrak,\n" +
                    "       pegawai.tanggal_pra_pensiun                                                as tanggal_pra_pensiun,\n" +
                    "       pegawai.tanggal_pensiun                                                    as tanggal_pensiun,\n" +
                    "       pegawai.tanggal_keluar                                                     as tanggal_keluar,\n" +
                    "       posisi.kodering                                                            as kodering,\n" +
                    "       pegawai.jenis_kelamin                                                      as gender,\n" +
                    "       company.persen_cuti_tahunan/100                                            as multifikator \n" +
                    "from im_hris_pegawai pegawai\n" +
                    "       left join im_hris_golongan golongan on pegawai.golongan_id = golongan.golongan_id and\n" +
                    "                                              golongan.flag = 'Y'\n" +
                    "       left join im_hris_tipe_pegawai tipe_pegawai on pegawai.tipe_pegawai = tipe_pegawai.tipe_pegawai_id\n" +
                    "       left join it_hris_pegawai_position pegawai_posisi\n" +
                    "         on pegawai.nip = pegawai_posisi.nip and pegawai_posisi.flag = 'Y' and\n" +
                    "            pegawai_posisi.flag_digaji = 'Y'\n" +
                    "       left join im_hris_jenis_pegawai jenis_pegawai\n" +
                    "         on pegawai_posisi.jenis_pegawai = jenis_pegawai.jenis_pegawai_id\n" +
                    "       left join im_branches branch on branch.branch_id = pegawai_posisi.branch_id\n" +
                    "       left join im_position posisi on posisi.position_id = pegawai_posisi.position_id\n" +
                    "       left join im_hris_department depart on depart.department_id = posisi.department_id\n" +
                    "       left join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                    "       left join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                    "       left join im_hris_profesi_pegawai profesi on pegawai_posisi.profesi_id = profesi.profesi_id\n" +
                    "       left join im_hris_payroll_tunjangan_strategis tunj_strategis\n" +
                    "         on tunj_strategis.profesi_id = pegawai_posisi.profesi_id and\n" +
                    "            tunj_strategis.golongan_id = pegawai.golongan_id and tunj_strategis.flag = 'Y'\n" +
                    "       left join im_hris_payroll_tunjangan_jabatan_struktural tunj_struktural\n" +
                    "         on tunj_struktural.kelompok_id = posisi.kelompok_id and tunj_struktural.flag = 'Y'\n" +
                    "       left join im_hris_payroll_skala_gaji skala_gaji\n" +
                    "         on skala_gaji.golongan_id = pegawai.golongan_id and skala_gaji.flag = 'Y' and\n" +
                    "            skala_gaji.tahun = :tahunSkalaPayroll\n" +
                    "       left join it_hris_tunj_lain_pegawai tunj_lainnya on tunj_lainnya.nip = pegawai.nip and tunj_lainnya.flag = 'Y'\n" +
                    "       left join im_company company on company.flag = 'Y'\n" +
                    "\n" +
                    "where pegawai.flag = 'Y'\n" +
                    "  and (pegawai.flag_pegawai_cuti_diluar_tanggungan = 'N' or pegawai.flag_pegawai_cuti_diluar_tanggungan is null)\n" +
                    "  and pegawai.tipe_pegawai = 'TP03'\n" + //RAKA-update tipe pegawai
                    "  and pegawai_posisi.branch_id = :branchId \n" +
                    ")peg \n" +
                    "left join im_hris_payroll_ptkp ptkp on ptkp.status_keluarga = peg.status_keluarga\n" +
                    "    and ptkp.jumlah_tanggungan = peg.jumlah_anak_ditanggung and ptkp.flag = 'Y' \n" +
                    "order by grade_level, masa_kerja_gol desc , nip";
        }

//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .setParameter("branchId", branchId)
//                .setParameter("tahunSkalaPayroll", tahunSkalaPayroll)
//                .setParameter("periodePayroll", periodePayroll)
//                .setParameter("tahunPayroll", tahunPayroll)
//                .list();

        if (CommonConstant.CODE_THR.equalsIgnoreCase(tipePayroll) ||
                CommonConstant.CODE_INSENTIF.equalsIgnoreCase(tipePayroll) ||
                CommonConstant.CODE_JASOP.equalsIgnoreCase(tipePayroll)) {
            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .setParameter("branchId", branchId)
                    .setParameter("tahunSkalaPayroll", tahunSkalaPayroll)
                    .setParameter("periodePayroll", periodePayroll)
                    .setParameter("tahunPayroll", tahunPayroll)
                    .list();
        } else {
            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .setParameter("branchId", branchId)
                    .setParameter("tahunSkalaPayroll", tahunSkalaPayroll)
                    .list();
        }

        for (Object[] row : results) {
            PegawaiPayroll result = new PegawaiPayroll();

            result.setTipePayroll(tipePayroll);

            //set periode,tahun,dan skala gaji
            result.setPeriodePayroll(periodePayroll);
            result.setTahunPayroll(tahunPayroll);
            result.setTahunSkalaGaji(tahunSkalaPayroll);

            result.setNip((String) row[0]);
            result.setNipLama(row[1] != null ? (String) row[1] : "");
            result.setGelar(row[2] != null ? (String) row[2] : "");
            result.setNamaPegawai((String) row[3]);
            result.setBranchId(row[4] != null ? (String) row[4] : "");
            result.setBranchName(row[5] != null ? (String) row[5] : "");
            result.setDivisiId(row[6] != null ? (String) row[6] : "");
            result.setDivisiName(row[7] != null ? (String) row[7] : "");
            result.setSubDivisiId(row[8] != null ? (String) row[8] : "");
            result.setSubDivisiName(row[9] != null ? (String) row[9] : "");
            result.setKelompokPosisiId(row[10] != null ? (String) row[10] : "");
            result.setKelompokPosisiName(row[11] != null ? (String) row[11] : "");
            result.setGolonganId(row[12] != null ? (String) row[12] : "");
            result.setGolonganName(row[13] != null ? (String) row[13] : "");

            result.setGradeLevel(row[14] != null ? (Integer) row[14] : null);
            result.setStGradeLevel(row[14] != null ? ((Integer) row[14]).toString() : "");

            result.setMasaKerjaGol((String) row[15]);
            result.setiMasaKerjaGol(Integer.parseInt(result.getMasaKerjaGol()));

            result.setTipePegawai(row[16] != null ? (String) row[16] : "");
            result.setTipePegawaiName(row[17] != null ? (String) row[17] : "");
            result.setStatusPegawai(row[18] != null ? (String) row[18] : "");
            result.setStatusPegawaiName(row[19] != null ? (String) row[19] : "");
            result.setJenisPegawai(row[20] != null ? (String) row[20] : "");
            result.setJenisPegawaiName(row[21] != null ? (String) row[21] : "");

            result.setPosisiId(row[22] != null ? (String) row[22] : "");
            result.setPosisiName(row[23] != null ? (String) row[23] : "");
            result.setProfesiId(row[24] != null ? (String) row[24] : "");
            result.setProfesiName(row[25] != null ? (String) row[25] : "");

            result.setGajiPokokNilai(row[26] != null ? (BigDecimal) row[26] : new BigDecimal(0));
            result.setGajiPokokNilai(result.getGajiPokokNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setGajiPokok(CommonUtil.numbericFormat(result.getGajiPokokNilai(), "###,###"));

            result.setSantunanKhususNilai(row[27] != null ? (BigDecimal) row[27] : new BigDecimal(0));
            result.setSantunanKhususNilai(result.getSantunanKhususNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setSantunanKhusus(CommonUtil.numbericFormat(result.getSantunanKhususNilai(), "###,###"));

            result.setTunjRumahNilai(row[28] != null ? (BigDecimal) row[28] : new BigDecimal(0));
            result.setTunjRumahNilai(result.getTunjRumahNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjRumah(CommonUtil.numbericFormat(result.getTunjRumahNilai(), "###,###"));

            result.setTunjListrikNilai(row[29] != null ? (BigDecimal) row[29] : new BigDecimal(0));
            result.setTunjListrikNilai(result.getTunjListrikNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjListrik(CommonUtil.numbericFormat(result.getTunjListrikNilai(), "###,###"));

            result.setTunjAirNilai(row[30] != null ? (BigDecimal) row[30] : new BigDecimal(0));
            result.setTunjAirNilai(result.getTunjAirNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjAir(CommonUtil.numbericFormat(result.getTunjAirNilai(), "###,###"));

            result.setTunjBbmNilai(row[31] != null ? (BigDecimal) row[31] : new BigDecimal(0));
            result.setTunjBbmNilai(result.getTunjBbmNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjBbm(CommonUtil.numbericFormat(result.getTunjBbmNilai(), "###,###"));

            result.setTunjJabatanNilai(row[32] != null ? (BigDecimal) row[32] : new BigDecimal(0));
            result.setTunjJabatanNilai(result.getTunjJabatanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjJabatan(CommonUtil.numbericFormat(result.getTunjJabatanNilai(), "###,###"));

            result.setTunjStrukturalNilai(row[33] != null ? (BigDecimal) row[33] : new BigDecimal(0));
            result.setTunjStrukturalNilai(result.getTunjStrukturalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjStruktural(CommonUtil.numbericFormat(result.getTunjStrukturalNilai(), "###,###"));

            result.setTunjFungsionalNilai(row[34] != null ? (BigDecimal) row[34] : new BigDecimal(0));
            result.setTunjFungsionalNilai(result.getTunjFungsionalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjFungsional(CommonUtil.numbericFormat(result.getTunjFungsionalNilai(), "###,###"));

            if(tipePayroll.equalsIgnoreCase(CommonConstant.CODE_CUTI_TAHUNAN) || tipePayroll.equalsIgnoreCase(CommonConstant.CODE_CUTI_PANJANG)){
                result.setTunjTambahanNilai(row[35] != null ? new BigDecimal((Integer)row[35]) : new BigDecimal(0)); //CUTI
            } else {
                result.setTunjTambahanNilai(row[35] != null ? (BigDecimal) row[35] : new BigDecimal(0)); //THR
            }
            result.setTunjTambahanNilai(result.getTunjTambahanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjTambahan(CommonUtil.numbericFormat(result.getTunjTambahanNilai(), "###,###"));

            result.setTunjPeralihanGapokNilai(row[36] != null ? (BigDecimal) row[36] : new BigDecimal(0));
            result.setTunjPeralihanGapokNilai(result.getTunjPeralihanGapokNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanGapok(CommonUtil.numbericFormat(result.getTunjPeralihanGapokNilai(), "###,###"));

            result.setTunjPeralihanSankhusNilai(row[37] != null ? (BigDecimal) row[37] : new BigDecimal(0));
            result.setTunjPeralihanSankhusNilai(result.getTunjPeralihanSankhusNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanSankhus(CommonUtil.numbericFormat(result.getTunjPeralihanSankhusNilai(), "###,###"));

            result.setTunjPeralihanTunjNilai(row[38] != null ? (BigDecimal) row[38] : new BigDecimal(0));
            result.setTunjPeralihanTunjNilai(result.getTunjPeralihanTunjNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPeralihanTunj(CommonUtil.numbericFormat(result.getTunjPeralihanTunjNilai(), "###,###"));

            result.setTunjKomunikasiNilai(row[39] != null ? (BigDecimal) row[39] : new BigDecimal(0));
            result.setTunjKomunikasiNilai(result.getTunjKomunikasiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjKomunikasi(CommonUtil.numbericFormat(result.getTunjKomunikasiNilai(), "###,###"));

            result.setTunjPemondokanNilai(row[40] != null ? (BigDecimal) row[40] : new BigDecimal(0));
            result.setTunjPemondokanNilai(result.getTunjPemondokanNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjPemondokan(CommonUtil.numbericFormat(result.getTunjPemondokanNilai(), "###,###"));

            result.setTunjSupervisiNilai(row[41] != null ? (BigDecimal) row[41] : new BigDecimal(0));
            result.setTunjSupervisiNilai(result.getTunjSupervisiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjSupervisi(CommonUtil.numbericFormat(result.getTunjSupervisiNilai(), "###,###"));

            result.setTunjLokalNilai(row[42] != null ? (BigDecimal) row[42] : new BigDecimal(0));
            result.setTunjLokalNilai(result.getTunjLokalNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjLokal(CommonUtil.numbericFormat(result.getTunjLokalNilai(), "###,###"));

            result.setTunjSiagaNilai(row[43] != null ? (BigDecimal) row[43] : new BigDecimal(0));
            result.setTunjSiagaNilai(result.getTunjSiagaNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setTunjSiaga(CommonUtil.numbericFormat(result.getTunjSiagaNilai(), "###,###"));

            result.setStatusKeluarga(row[44] != null ? (String) row[44] : "");
            result.setJumlahAnak(row[45] != null ? ((BigInteger) row[45]).intValue() : new Integer(0));
            result.setStJumlahAnak(String.valueOf(result.getJumlahAnak()));

            result.setJumlahAnakDitanggung(row[46] != null ? ((BigInteger) row[46]).intValue() : new Integer(0));
            result.setStJumlahAnakDitanggung(String.valueOf(result.getJumlahAnakDitanggung()));

            result.setNpwpPegawai(row[47] != null ? (String) row[47] : "");
            result.setNoRekBank(row[48] != null ? (String) row[48] : "");
            result.setNamaBank(row[49] != null ? (String) row[49] : "");

            result.setTanggalAktif(row[50] != null ? (Date) row[50] : null);
            result.setTanggalAkhirKontrak(row[51] != null ? (Date) row[51] : null);
            result.setTanggalPraPensiun(row[52] != null ? (Date) row[52] : null);
            result.setTanggalPensiun(row[53] != null ? (Date) row[53] : null);
            result.setTanggalKeluar(row[54] != null ? (Date) row[54] : null);

            result.setStTanggalAktif(result.getTanggalAktif() != null ? CommonUtil.simpleDateFormat(result.getTanggalAktif()) : "");
            result.setStTanggalAkhirKontrak(result.getTanggalAkhirKontrak() != null ? CommonUtil.simpleDateFormat(result.getTanggalAkhirKontrak()) : "");
            result.setStTanggalPraPensiun(result.getTanggalPraPensiun() != null ? CommonUtil.simpleDateFormat(result.getTanggalPraPensiun()) : "");
            result.setStTanggalPensiun(result.getTanggalPensiun() != null ? CommonUtil.simpleDateFormat(result.getTanggalPensiun()) : "");
            result.setStTanggalKeluar(result.getTanggalKeluar() != null ? CommonUtil.simpleDateFormat(result.getTanggalKeluar()) : "");

            result.setKodering(row[55] != null ? (String) row[55] : "");
            result.setGender(row[56] != null ? (String) row[56] : "");

            result.setMultifikatorNilai(row[57] != null ? (BigDecimal) row[57] : new BigDecimal(0));
            result.setMultifikator(CommonUtil.numbericFormat(result.getMultifikatorNilai(), "###,###"));

            result.setPtkpPegawaiNilai(row[58] != null ? (BigDecimal) row[58] : new BigDecimal(0));
            result.setPtkpPegawaiNilai(result.getPtkpPegawaiNilai().setScale(0, BigDecimal.ROUND_HALF_UP));
            result.setPtkpPegawai(CommonUtil.numbericFormat(result.getPtkpPegawaiNilai(), "###,###"));

            listOfResult.add(result);
        }

        return listOfResult;
    }



    //uncomment by ferdi, 01 des 2020
//    public List<ItPayrollEntity> getDataEdit(String branchId, String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "SELECT DISTINCT \n" +
//                "  pegawai.nip,\n" +
//                "  pegawai.nama_pegawai,\n" +
//                "  posisi.branch_id,\n" +
//                "  branch.branch_name,\n" +
//                "  position.department_id,\n" +
//                "  department.department_name,\n" +
//                "  posisi.position_id,\n" +
//                "  position.position_name,\n" +
//                "  pegawai.golongan_id,\n" +
//                "  golongan.grade_level,\n" +
//                "  position.kelompok_id,\n" +
//                "  pegawai.point,\n" +
//                "  pegawai.status_keluarga,\n" +
//                "  pegawai.jumlah_anak,\n" +
//                "  branch.multifikator,\n" +
//                "  pegawai.zakat_profesi,\n" +
//                "  pegawai.jenis_kelamin,\n" +
//                "  pegawai.dana_pensiun, \n" +
//                "  pegawai.tanggal_aktif, \n" +
//                "  pegawai.tanggal_pensiun, \n" +
//                "  pegawai.tipe_pegawai, \n" +
//                "  pegawai.struktur_gaji, \n" +
//                "  pegawai.gaji, \n" +
//                "  tipePegawai.tipe_pegawai_name, \n" +
//                "  position.kelompok_id,\n" +
//                "  pegawai.status_giling,\n" +
//                "  danaPensiun.dana_pensiun as nama_dana_pensiun,\n" +
//                "  posisi.pjs_flag,\n" +
//                "  pegawai.npwp,\n" +
//                "  pegawai.status_pegawai, \n" +
//                "  pegawai.golongan_dapen, \n" +
//                "  pegawai.golongan_dapen_nusindo,  \n" +
//                "  pegawai.poin_lebih," +
//                "  branch.umr, \n" +
//                "  pegawai.golongan_dapen_id, \n"+
//                "  pegawai.masa_kerja_gol, \n"+
//                "  pegawai.tgl_akhir_kontrak, \n"+
//                "  posisi.profesi_id,\n" +
//                "  pegawai.gaji\n"+
//                "   FROM im_hris_pegawai pegawai\n" +
//                "LEFT JOIN it_hris_pegawai_position posisi\n" +
//                "  ON posisi.nip = pegawai.nip\n" +
//                "LEFT JOIN im_branches branch\n" +
//                "  ON branch.branch_id = posisi.branch_id\n" +
//                "LEFT JOIN im_position position\n" +
//                "  ON position.position_id = posisi.position_id\n" +
//                "LEFT JOIN im_hris_department department\n" +
//                "  ON department.department_id = position.department_id\n" +
//                "LEFT JOIN im_hris_golongan golongan\n" +
//                "  ON golongan.golongan_id = pegawai.golongan_id\n" +
//                "LEFT JOIN im_hris_tipe_pegawai tipePegawai\n" +
//                "  ON tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
//                "LEFT JOIN im_hris_payroll_dana_pensiun danaPensiun\n" +
//                "  ON danaPensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "WHERE pegawai.flag = 'Y'\n" +
//                "AND posisi.flag = 'Y'\n" +
//                "AND pegawai.flag_dokter_kso = 'N'\n" +
//                "AND posisi.branch_id = '"+branchId+"'\n" +
//                strWhere+"\n" +
//                "ORDER BY position.kelompok_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setBranchId((String) row[2]);
//            result.setBranchName((String) row[3]);
//            result.setDepartmentId((String) row[4]);
//            result.setDepartmentName((String) row[5]);
//            result.setPositionId((String) row[6]);
//            result.setPositionName((String) row[7]);
//            result.setGolonganId((String) row[8]);
//
//            Integer level = 0;
//            if (row[9]!=null){
//                level = (Integer) row[9];
//            }
//
//            result.setGolonganName(String.valueOf(level));
//            result.setKelompokId((String) row[10]);
//            if (row[11]!=null){
//                result.setPoint(Integer.parseInt(row[11].toString()));
//            }else{
//                result.setPoint(0);
//            }
//            result.setStatusKeluarga((String) row[12]);
//
//            if (row[13]!=null){
//                result.setJumlahAnak(Integer.valueOf(row[13].toString()));
//            }else{
//                result.setJumlahAnak(0);
//            }
//
//            if (row[14]!=null){
//                result.setMultifikator(Double.valueOf(row[14].toString()).intValue() + "");
//            }
//            result.setFlagZakat((String) row[15]);
//            result.setGender((String) row[16]);
//            result.setDanaPensiun((String) row[17]);
//            result.setTanggalAktif((Date) row[18]);
//            result.setTanggalPensiun((Date) row[19]);
//            result.setTipePegawai((String) row[20]);
//            result.setStrukturGaji((String) row[21]);
//            if (row[22]!=null){
//                result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
//            }
//            result.setTipePegawaiName((String) row[23]);
//            result.setKelompokId((String) row[24]);
//            result.setStatusGiling((String) row[25]);
//            result.setDanaPensiunName((String) row[26]);
//            result.setFlagPjs((String) row[27]);
//            result.setNpwp((String) row[28]);
//            result.setStatusPegawai((String) row[29]);
//            result.setGolonganDapen((String) row[30]);
//            result.setGolonganDapenNusindo((String) row[31]);
//            result.setPointLebih(Integer.parseInt(row[32].toString()));
//            if (row[33]!=null){
//                result.setUmr(BigDecimal.valueOf(Double.parseDouble(row[33].toString())));
//            }
//            result.setGolonganDapenId((String) row[34]);
//            result.setMasaKerjaGol((Integer)row[35]);
//            if (row[36]!=null){
//                result.setTanggalAkhirKontrak((Date)row[36]);
//            }
//            if (row[37]!=null){
//                result.setProfesiId((String) row[37]);
//            }
//            result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[38].toString())));
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataEditPegawaiNonAktif(String branchId, String strWhere,String bulan,String tahun){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "SELECT DISTINCT \n" +
//                "  pegawai.nip,\n" +
//                "  pegawai.nama_pegawai,\n" +
//                "  posisi.branch_id,\n" +
//                "  branch.branch_name,\n" +
//                "  position.department_id,\n" +
//                "  department.department_name,\n" +
//                "  posisi.position_id,\n" +
//                "  position.position_name,\n" +
//                "  pegawai.golongan_id,\n" +
//                "  golongan.grade_level,\n" +
//                "  position.kelompok_id,\n" +
//                "  pegawai.point,\n" +
//                "  pegawai.status_keluarga,\n" +
//                "  pegawai.jumlah_anak,\n" +
//                "  branch.multifikator,\n" +
//                "  pegawai.zakat_profesi,\n" +
//                "  pegawai.jenis_kelamin,\n" +
//                "  pegawai.dana_pensiun, \n" +
//                "  pegawai.tanggal_aktif, \n" +
//                "  pegawai.tanggal_pensiun, \n" +
//                "  pegawai.tipe_pegawai, \n" +
//                "  pegawai.struktur_gaji, \n" +
//                "  pegawai.gaji, \n" +
//                "  tipePegawai.tipe_pegawai_name, \n" +
//                "  position.kelompok_id,\n" +
//                "  pegawai.status_giling,\n" +
//                "  danaPensiun.dana_pensiun as nama_dana_pensiun,\n" +
//                "  posisi.pjs_flag,\n" +
//                "  pegawai.npwp,\n" +
//                "  pegawai.status_pegawai, \n" +
//                "  pegawai.golongan_dapen, \n" +
//                "  pegawai.golongan_dapen_nusindo,  \n" +
//                "  pegawai.poin_lebih,  branch.umr, \n" +
//                "  pegawai.golongan_dapen_id, \n" +
//                "  pegawai.masa_kerja_gol, \n" +
//                "  pegawai.tgl_akhir_kontrak, \n" +
//                "  posisi.profesi_id,\n" +
//                "  pegawai.gaji,\n" +
//                "  mj.tanggal_efektif\n" +
//                "   FROM im_hris_pegawai pegawai\n" +
//                "LEFT JOIN it_hris_pegawai_position posisi\n" +
//                "  ON posisi.nip = pegawai.nip\n" +
//                "LEFT JOIN im_branches branch\n" +
//                "  ON branch.branch_id = posisi.branch_id\n" +
//                "LEFT JOIN im_position position\n" +
//                "  ON position.position_id = posisi.position_id\n" +
//                "LEFT JOIN im_hris_department department\n" +
//                "  ON department.department_id = position.department_id\n" +
//                "LEFT JOIN im_hris_golongan golongan\n" +
//                "  ON golongan.golongan_id = pegawai.golongan_id\n" +
//                "LEFT JOIN im_hris_tipe_pegawai tipePegawai\n" +
//                "  ON tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
//                "LEFT JOIN im_hris_payroll_dana_pensiun danaPensiun\n" +
//                "  ON danaPensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "LEFT JOIN it_hris_mutasi_jabatan mj ON mj.nip=pegawai.nip\n" +
//                "LEFT JOIN im_hris_status_mutasi sm ON sm.status_mutasi_id = mj.status\n" +
//                "WHERE pegawai.flag = 'N'\n" +
//                "AND posisi.flag = 'N'\n" +
//                "AND pegawai.flag_dokter_kso = 'N'\n" +
//                "AND posisi.branch_id = '"+branchId+"'\n" +
//                 strWhere +
//                "AND to_char(mj.tanggal_efektif, 'MM-YYYY') = '"+bulan+"-"+tahun+"'\n" +
//                "AND sm.flag_gaji_proporsional='Y'\n" +
//                "ORDER BY position.kelompok_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setBranchId((String) row[2]);
//            result.setBranchName((String) row[3]);
//            result.setDepartmentId((String) row[4]);
//            result.setDepartmentName((String) row[5]);
//            result.setPositionId((String) row[6]);
//            result.setPositionName((String) row[7]);
//            result.setGolonganId((String) row[8]);
//
//            Integer level = 0;
//            if (row[9]!=null){
//                level = (Integer) row[9];
//            }
//
//            result.setGolonganName(String.valueOf(level));
//            result.setKelompokId((String) row[10]);
//            if (row[11]!=null){
//                result.setPoint(Integer.parseInt(row[11].toString()));
//            }else{
//                result.setPoint(0);
//            }
//            result.setStatusKeluarga((String) row[12]);
//
//            if (row[13]!=null){
//                result.setJumlahAnak(Integer.valueOf(row[13].toString()));
//            }else{
//                result.setJumlahAnak(0);
//            }
//
//            if (row[14]!=null){
//                result.setMultifikator(Double.valueOf(row[14].toString()).intValue() + "");
//            }
//            result.setFlagZakat((String) row[15]);
//            result.setGender((String) row[16]);
//            result.setDanaPensiun((String) row[17]);
//            result.setTanggalAktif((Date) row[18]);
//            result.setTanggalPensiun((Date) row[19]);
//            result.setTipePegawai((String) row[20]);
//            result.setStrukturGaji((String) row[21]);
//            if (row[22]!=null){
//                result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
//            }
//            result.setTipePegawaiName((String) row[23]);
//            result.setKelompokId((String) row[24]);
//            result.setStatusGiling((String) row[25]);
//            result.setDanaPensiunName((String) row[26]);
//            result.setFlagPjs((String) row[27]);
//            result.setNpwp((String) row[28]);
//            result.setStatusPegawai((String) row[29]);
//            result.setGolonganDapen((String) row[30]);
//            result.setGolonganDapenNusindo((String) row[31]);
//            result.setPointLebih(Integer.parseInt(row[32].toString()));
//            if (row[33]!=null){
//                result.setUmr(BigDecimal.valueOf(Double.parseDouble(row[33].toString())));
//            }
//            result.setGolonganDapenId((String) row[34]);
//            result.setMasaKerjaGol((Integer)row[35]);
//            if (row[36]!=null){
//                result.setTanggalAkhirKontrak((Date)row[36]);
//            }
//            if (row[37]!=null){
//                result.setProfesiId((String) row[37]);
//            }
//            result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[38].toString())));
//            Timestamp tanggalEfektif =(Timestamp) row[39];
//            result.setTanggalEfektif(new Date (tanggalEfektif.getTime()));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataEditRapel(String branchId, String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "SELECT\n" +
//                "  pegawai.nip,\n" +
//                "  pegawai.nama_pegawai,\n" +
//                "  posisi.branch_id,\n" +
//                "  branch.branch_name,\n" +
//                "  position.department_id,\n" +
//                "  department.department_name,\n" +
//                "  posisi.position_id,\n" +
//                "  position.position_name,\n" +
//                "  pegawai.golongan_id,\n" +
//                "  golongan.golongan_name,\n" +
//                "  position.kelompok_id,\n" +
//                "  pegawai.point,\n" +
//                "  pegawai.status_keluarga,\n" +
//                "  pegawai.jumlah_anak,\n" +
//                "  branch.multifikator,\n" +
//                "  pegawai.zakat_profesi,\n" +
//                "  pegawai.jenis_kelamin,\n" +
//                "  pegawai.dana_pensiun, \n" +
//                "  pegawai.tanggal_aktif, \n" +
//                "  pegawai.tanggal_pensiun, \n" +
//                "  pegawai.tipe_pegawai, \n" +
//                "  pegawai.struktur_gaji, \n" +
//                "  pegawai.gaji, \n" +
//                "  tipePegawai.tipe_pegawai_name, \n" +
//                "  position.kelompok_id,\n" +
//                "  pegawai.status_giling,\n" +
//                "  danaPensiun.dana_pensiun as nama_dana_pensiun,\n" +
//                "  posisi.pjs_flag,\n" +
//                "  pegawai.npwp,\n" +
//                "  pegawai.status_pegawai, \n" +
//                "  pegawai.golongan_dapen, \n" +
//                "  pegawai.golongan_dapen_nusindo,  \n" +
//                "  pegawai.poin_lebih," +
//                "  branch.umr \n" +
//                "   FROM im_hris_pegawai pegawai\n" +
//                "LEFT JOIN it_hris_pegawai_position posisi\n" +
//                "  ON posisi.nip = pegawai.nip\n" +
//                "LEFT JOIN im_branches branch\n" +
//                "  ON branch.branch_id = posisi.branch_id\n" +
//                "LEFT JOIN im_position position\n" +
//                "  ON position.position_id = posisi.position_id\n" +
//                "LEFT JOIN im_hris_department department\n" +
//                "  ON department.department_id = position.department_id\n" +
//                "LEFT JOIN im_hris_golongan golongan\n" +
//                "  ON golongan.golongan_id = pegawai.golongan_id\n" +
//                "LEFT JOIN im_hris_tipe_pegawai tipePegawai\n" +
//                "  ON tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
//                "LEFT JOIN im_hris_payroll_dana_pensiun danaPensiun\n" +
//                "  ON danaPensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "WHERE posisi.flag = 'Y'\n" +
//                "AND posisi.branch_id = '"+branchId+"'\n" +
//                strWhere;
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setBranchId((String) row[2]);
//            result.setBranchName((String) row[3]);
//            result.setDepartmentId((String) row[4]);
//            result.setDepartmentName((String) row[5]);
//            result.setPositionId((String) row[6]);
//            result.setPositionName((String) row[7]);
//            result.setGolonganId((String) row[8]);
//            result.setGolonganName((String) row[9]);
//            result.setKelompokId((String) row[10]);
//            result.setPoint(Integer.parseInt(row[11].toString()));
//            result.setStatusKeluarga((String) row[12]);
//            result.setJumlahAnak(Integer.valueOf(row[13].toString()));
//            result.setMultifikator(Double.valueOf(row[14].toString()).intValue() + "");
//            result.setFlagZakat((String) row[15]);
//            result.setGender((String) row[16]);
//            result.setDanaPensiun((String) row[17]);
//            result.setTanggalAktif((Date) row[18]);
//            result.setTanggalPensiun((Date) row[19]);
//            result.setTipePegawai((String) row[20]);
//            result.setStrukturGaji((String) row[21]);
//            result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
//            result.setTipePegawaiName((String) row[23]);
//            result.setKelompokId((String) row[24]);
//            result.setStatusGiling((String) row[25]);
//            result.setDanaPensiunName((String) row[26]);
//            result.setFlagPjs((String) row[27]);
//            result.setNpwp((String) row[28]);
//            result.setStatusPegawai((String) row[29]);
//            result.setGolonganDapen((String) row[30]);
//            result.setGolonganDapenNusindo((String) row[31]);
//            result.setPointLebih(Integer.parseInt(row[32].toString()));
//            result.setUmr(BigDecimal.valueOf(Double.parseDouble(row[33].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataAnggotaJasprod(String branchId, String periode){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "SELECT\n" +
//                "  pegawai.nip,\n" +
//                "  pegawai.nama_pegawai,\n" +
//                "  smk.branch_id,\n" +
//                "  branch.branch_name,\n" +
//                "  position.department_id,\n" +
//                "  department.department_name,\n" +
//                "  smk.position_id,\n" +
//                "  position.position_name,\n" +
//                "  historyJabatan.golongan_id,\n" +
//                "  golongan.golongan_name,\n" +
//                "  position.kelompok_id,\n" +
//                "  historyJabatan.point,\n" +
//                "  pegawai.status_keluarga,\n" +
//                "  pegawai.jumlah_anak,\n" +
//                "  branch.multifikator,\n" +
//                "  pegawai.zakat_profesi,\n" +
//                "  pegawai.jenis_kelamin,\n" +
//                "  pegawai.dana_pensiun, \n" +
//                "  pegawai.tanggal_aktif, \n" +
//                "  pegawai.tanggal_pensiun, \n" +
//                "  pegawai.tipe_pegawai, \n" +
//                "  pegawai.struktur_gaji, \n" +
//                "  pegawai.gaji, \n" +
//                "  tipePegawai.tipe_pegawai_name, \n" +
//                "  position.kelompok_id,\n" +
//                "  pegawai.status_giling,\n" +
//                "  danaPensiun.dana_pensiun as nama_dana_pensiun,\n" +
//                "  pegawai.npwp,\n" +
//                "  pegawai.status_pegawai, \n" +
//                "  pegawai.golongan_dapen, \n" +
//                "  pegawai.golongan_dapen_nusindo,  \n" +
//                "  pegawai.poin_lebih,  \n" +
//                "  branch.umr,\n" +
//                "  historyJabatan.poin_lebih\n" +
//                "from\n" +
//                "  it_hris_smk_evaluasi_pegawai smk \n" +
//                "LEFT JOIN im_hris_pegawai pegawai \n" +
//                "  ON pegawai.nip = smk.nip\n" +
//                "LEFT JOIN im_branches branch\n" +
//                "  ON branch.branch_id = smk.branch_id\n" +
//                "LEFT JOIN im_position position\n" +
//                "  ON position.position_id = smk.position_id\n" +
//                "LEFT JOIN imt_hris_history_smk_golongan historyJabatan\n" +
//                "  ON historyJabatan.nip = smk.nip and historyJabatan.branch_id = smk.branch_id \n" +
//                "LEFT JOIN im_hris_department department\n" +
//                "  ON department.department_id = position.department_id\n" +
//                "LEFT JOIN im_hris_golongan golongan\n" +
//                "  ON golongan.golongan_id = historyJabatan.golongan_id\n" +
//                "LEFT JOIN im_hris_tipe_pegawai tipePegawai\n" +
//                "  ON tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
//                "LEFT JOIN im_hris_payroll_dana_pensiun danaPensiun\n" +
//                "  ON danaPensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "WHERE \n" +
//                "smk.flag = 'Y'\n" +
//                "AND smk.branch_id = '"+branchId+"'\n" +
//                "and smk.periode = '"+periode+"'\n" +
//                "and historyJabatan.tahun = '"+periode+"'\n" +
//                "and pegawai.nip is not null \n" +
//                "order by \n" +
//                "pegawai.nama_pegawai";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setBranchId((String) row[2]);
//            result.setBranchName((String) row[3]);
//            result.setDepartmentId((String) row[4]);
//            result.setDepartmentName((String) row[5]);
//            result.setPositionId((String) row[6]);
//            result.setPositionName((String) row[7]);
//            result.setGolonganId((String) row[8]);
//            result.setGolonganName((String) row[9]);
//            result.setKelompokId((String) row[10]);
//            result.setPoint(Integer.parseInt(row[11].toString()));
//            result.setStatusKeluarga((String) row[12]);
//            result.setJumlahAnak(Integer.valueOf(row[13].toString()));
//            result.setMultifikator(Double.valueOf(row[14].toString()).intValue() + "");
//            result.setFlagZakat((String) row[15]);
//            result.setGender((String) row[16]);
//            result.setDanaPensiun((String) row[17]);
//            result.setTanggalAktif((Date) row[18]);
//            result.setTanggalPensiun((Date) row[19]);
//            result.setTipePegawai((String) row[20]);
//            result.setStrukturGaji((String) row[21]);
//            result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
//            result.setTipePegawaiName((String) row[23]);
//            result.setKelompokId((String) row[24]);
//            result.setStatusGiling((String) row[25]);
//            result.setDanaPensiunName((String) row[26]);
//            //result.setFlagPjs((String) row[27]);
//            result.setNpwp((String) row[27]);
//            result.setStatusPegawai((String) row[28]);
//            result.setGolonganDapen((String) row[29]);
//            result.setGolonganDapenNusindo((String) row[30]);
//            result.setPointLebih(Integer.parseInt(row[31].toString()));
//            result.setUmr(BigDecimal.valueOf(Double.parseDouble(row[32].toString())));
//            result.setPointLebih(Integer.parseInt(row[33].toString()));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataSearchHome(String branchId, String bulan1, String tahun1, String bulan2, String tahun2, String tipe){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String tipeWhere = "";
//        if(tipe.equalsIgnoreCase("PR")){
//            tipeWhere = "and payroll.flag_payroll = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("T")){
//            tipeWhere = "and payroll.flag_thr = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("CP")){
//            tipeWhere = "and payroll.flag_cuti_panjang = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("CT")){
//            tipeWhere = "and payroll.flag_cuti_tahunan= 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JP")){
//            tipeWhere = "and payroll.flag_jasprod = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JB")){
//            tipeWhere = "and payroll.flag_jubileum = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("PN")){
//            tipeWhere = "and payroll.flag_pensiun= 'Y' ";
//        }else if(tipe.equalsIgnoreCase("IN")){
//            tipeWhere = "and payroll.flag_insentif= 'Y' ";
//        }
//
//        String query = "SELECT\n" +
//                "  payroll.bulan,\n" +
//                "  payroll.tahun,\n" +
//                "  COUNT(payroll.nip),\n" +
//                "  branch.branch_name,\n" +
//                "  SUM(payroll.gaji_bersih) as gaji_bersih,\n" +
//                "  SUM(payroll.total_a) as gaji_kotor,\n" +
//                "  payroll.approval_flag,\n" +
//                "  payroll.approval_unit_flag,\n" +
//                "  payroll.approval_sdm_flag,\n" +
//                "  payroll.approval_date,\n" +
//                "  payroll.branch_id,\n" +
//                "  payroll.flag_payroll,\n" +
//                "  payroll.flag_thr,\n" +
//                "  payroll.flag_cuti_tahunan,\n" +
//                "  payroll.flag_cuti_panjang,\n" +
//                "  payroll.flag_jasprod,\n" +
//                "  payroll.flag_jubileum,\n" +
//                "  payroll.flag_pensiun,\n" +
//                "  payroll.flag_insentif,\n" +
//                "  payroll.approval_aks_flag\n" +
//                "FROM it_hris_payroll payroll\n" +
//                "LEFT JOIN im_branches branch\n" +
//                "  ON branch.branch_id = payroll.branch_id\n" +
//                "WHERE payroll.bulan >= '"+bulan1+"'\n" +
//                "AND payroll.bulan <= '"+bulan2+"'\n" +
//                "AND payroll.tahun >= '"+tahun1+"'\n" +
//                "AND payroll.tahun <= '"+tahun2+"'\n" +
//                "AND payroll.flag = 'Y'\n" +
//                "AND payroll.branch_id = '"+branchId+"'\n" +
//                tipeWhere +
//                "GROUP BY payroll.bulan,\n" +
//                "         payroll.tahun,\n" +
//                "         branch.branch_name,\n" +
//                "         payroll.approval_flag,\n" +
//                "         payroll.approval_unit_flag,\n" +
//                "         payroll.approval_sdm_flag,\n" +
//                "         payroll.approval_aks_flag,\n" +
//                "         payroll.approval_date," +
//                "         payroll.branch_id,\n" +
//                "         payroll.flag_payroll,\n" +
//                "         payroll.flag_thr,\n" +
//                "         payroll.flag_cuti_tahunan,\n" +
//                "         payroll.flag_cuti_panjang,\n" +
//                "         payroll.flag_jasprod,\n" +
//                "         payroll.flag_jubileum,\n" +
//                "         payroll.flag_pensiun,\n" +
//                "         payroll.flag_insentif\n" +
//                "order by approval_date desc";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setBulan((String) row[0]);
//            result.setTahun((String) row[1]);
//            result.setJumlahPegawai(Integer.valueOf(row[2].toString()));
//            result.setBranchName((String) row[3]);
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setApprovalFlag((String) row[6]);
//            result.setApprovalUnitFlag((String) row[7]);
//            result.setApprovalSdmFlag((String) row[8]);
//            result.setApprovalDate((java.sql.Timestamp) row[9]);
//            result.setBranchId((String) row[10]);
//            result.setFlagPayroll((String) row[11]);
//            result.setFlagThr((String) row[12]);
//            result.setFlagCutiTahunan((String) row[13]);
//            result.setFlagCutiPanjang((String) row[14]);
//            result.setFlagJasprod((String) row[15]);
//            result.setFlagJubileum((String) row[16]);
//            result.setFlagPensiun((String) row[17]);
//            result.setFlagInsentif((String) row[18]);
//            result.setApprovalAksFlag((String) row[19]);
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    // retrieve data insentif dari database
//    public List<ItPayrollInsentifEntity> getDataInsentif(String branchId){
//        List<ItPayrollInsentifEntity> listOfResult = new ArrayList<ItPayrollInsentifEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tinsentif.nip,\n" +
//                "\tpegawai.nama_pegawai,\n" +
//                "\tbranch.branch_name,\n" +
//                "\tsum(insentif.jumlah_insentif) as nilai_insentif,\n" +
//                "\tsum(insentif.jumlah_pph) as nilai_pph\n" +
//                "from\n" +
//                "\tit_hris_payroll_insentif insentif\n" +
//                "\tleft join it_hris_pegawai_position pegawaiPosition on pegawaiPosition.nip = insentif.nip\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = insentif.nip\n" +
//                "\tleft join im_branches branch on branch.branch_id = pegawaiPosition.branch_id\n" +
//                "where\n" +
//                "\tpegawaiPosition.branch_id = '"+branchId+"'\n" +
//                "group by\n" +
//                "\tinsentif.nip,\n" +
//                "\tpegawai.nama_pegawai,\n" +
//                "\tbranch.branch_name";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollInsentifEntity result  = new ItPayrollInsentifEntity();
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setBranchName((String) row[2]);
//            result.setJumlahInsentif(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setJumlahPph(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataViewAndEdit(String bulan, String tahun){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.*,\n" +
//                "\tpph.*,\n" +
//                "\tpegawai.*\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id \n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip and pegawai.flag = 'Y'\n" +
//                "where\n" +
//                "\tpayroll.bulan = '"+bulan+"' \n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.flag = 'Y'\n" +
//                "\t";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setPayrollId((String) row[0]);
//            result.setBulan((String) row[1]);
//            result.setTahun((String) row[2]);
//            result.setNip((String) row[77]);
//            result.setNama((String) row[78]);
//            result.setTanggalAktif((Date) row[99]);
//            result.setPositionId((String) row[5]);
//            result.setPositionName((String) row[6]);
//            result.setGolonganId((String) row[7]);
//            result.setGolonganName((String) row[8]);
//            result.setDepartmentId((String) row[9]);
//            result.setDepartmentName((String) row[10]);
//            result.setBranchId((String) row[11]);
//            result.setBranchName((String) row[12]);
//            result.setPoint(Integer.parseInt(row[13].toString()));
//            result.setStatusKeluarga((String) row[14]);
//            result.setJumlahAnak(Integer.valueOf(row[15].toString()));
//            result.setMultifikator((String) row[19]);
//
//
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setBranchName((String) row[3]);
//            result.setApprovalFlag((String) row[5]);
//            result.setApprovalDate((java.sql.Timestamp) row[6]);
//            result.setBranchId((String) row[7]);
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> cekPensiun(String nip){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpensiun.pensiun_id,\n" +
//                "\tpensiun.created_date,\n" +
//                "\tpensiun.netto_pensiun\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tinner join it_hris_payroll_pensiun pensiun on pensiun.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.nip = '"+nip+"'\n" +
//                "\tand payroll.flag = 'Y'\n" +
//                "\tand pensiun.flag = 'Y'\n" +
//                "\nand payroll.approval_flag = 'Y'";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setPayrollId((String) row[0]);
//            result.setApprovalDate((java.sql.Timestamp) row[4]);
//            result.setNettoPensiun(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> cekJubileum(String nip){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tjubileum.jubileum_id,\n" +
//                "\tjubileum.created_date\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tinner join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.nip = '"+nip+"'\n" +
//                "\tand payroll.flag = 'Y'\n" +
//                "\tand jubileum.flag = 'Y'\n" +
//                "\tand payroll.approval_flag = 'Y'";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setTanggalAktif(CommonUtil.convertTimestampToDate((java.sql.Timestamp) row[4]));
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> cekTotalPphTahun(String nip, String tahun){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tcase\n" +
//                "\t\twhen sum(pph_gaji) is null then 0 else sum(pph_gaji) end as pphTahun  \n" +
//                "from\n" +
//                "\tit_hris_payroll\n" +
//                "where\n" +
//                "\tnip = '"+nip+"'\n" +
//                "\tand tahun = '"+tahun+"'";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setPphTahun(BigDecimal.valueOf(Double.valueOf(row.toString())));
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> cekTotalBrutoTahun(String nip, String tahun){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tcase\n" +
//                "\t\twhen sum(pph.bruto / 12) is null then 0 else sum(pph.bruto / 12) end\n" +
//                "\tas hasil\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tnip = '"+nip+"'\n" +
//                "\tand tahun = '"+tahun+"'";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setPphTahun(BigDecimal.valueOf(Double.valueOf(row.toString())));
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    // mengambil data history lembur (filter msh static)
//    public List<ItPayrollEntity> cekHistoryLembur(String nip, String tanggal1, String tanggal2){
//        tanggal1 = "01-"+tanggal1;
//        tanggal2 = "01-"+tanggal2;
//        Date dTanggal1 = CommonUtil.convertStringToDate(tanggal1);
//        Date dTanggal2 = CommonUtil.convertStringToDate(tanggal2);
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tabsensi.absensi_pegawai_id,\n" +
//                "\tabsensi.nip,\n" +
//                "\tabsensi.tanggal,\n" +
//                "\tabsensi.jenis_lembur,\n" +
//                "\tabsensi.lama_lembur,\n" +
//                "\tabsensi.jam_lembur,\n" +
//                "\tabsensi.biaya_lembur,\n" +
//                "\tpayroll.gaji_golongan,\n" +
//                "\tpayroll.tunjangan_umk,\n" +
//                "\tpayroll.tunjangan_peralihan,\n" +
//                "\tpegawai.tipe_pegawai,\n" +
//                "\tfaktorLembur.faktor\n" +
//                "\t\n" +
//                "from\n" +
//                "\tit_hris_absensi_pegawai absensi\n" +
//                "\tleft join it_hris_payroll payroll on payroll.nip = absensi.nip and payroll.flag_payroll = 'Y' \n" +
//                "\tand cast(NULLIF(payroll.bulan, '') AS Integer) = EXTRACT(Month FROM absensi.tanggal)\n" +
//                "\tand cast(NULLIF(payroll.tahun, '') AS Integer) = EXTRACT(Year FROM absensi.tanggal)\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = absensi.nip \n" +
//                "\tleft join im_hris_pengali_faktor_lembur faktorLembur on faktorLembur.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
//                "where\n" +
//                "\tabsensi.lembur = 'Y'\n" +
//                "\tand absensi.nip ='"+nip+"'\n" +
//                "\tand absensi.tanggal >= '"+dTanggal1+"'\n" +
//                "\tand absensi.tanggal <= '"+dTanggal2+"'\n" +
//                "order by\n" +
//                "\tabsensi.tanggal\n" +
//                "\t";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setAbsensiId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setTanggal((Date) row[2]);
//            result.setJamLembur(Double.valueOf(row[5].toString()));
//            result.setBiayaLemburLama(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//
//            if(row[7] != null){
//                result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            }else{
//                result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(0)));
//            }
//
//            if(row[8] != null){
//                result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            }else{
//                result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(0)));
//            }
//
//            if(row[9] != null){
//                result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            }else{
//                result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(0)));
//            }
//
//            result.setTipePegawai((String) row[10]);
//            result.setFaktorKali(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> cekApprove(String branchId, String bulan, String tahun){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\t*\n" +
//                "from\n" +
//                "\tit_hris_payroll\n" +
//                "where\n" +
//                "\tbulan = '"+bulan+"'\n" +
//                "\tand tahun = '"+tahun+"'\n" +
//                "\tand approval_flag is null\n" +
//                "\tand branch_id = '"+branchId+"' limit 1";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setBulan((String) row[1]);
//            result.setTahun((String) row[2]);
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }

//    public List<ItPayrollEntity> getLastPayroll(String nip, String bulan, String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("flagPayroll", "Y"))
//                .add(Restrictions.eq("approvalFlag", "Y"))
//                .list();
//
//        return results;
//    }
//
//    public List<ItPayrollEntity> getPakaianDinasByNipAndTahun(String nip, String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("flagPayroll", "Y"))
//                .add(Restrictions.ge("tunjanganBajuDinas",0))
//                .list();
//
//        return results;
//    }
//
//    public List<ItPayrollEntity> getLastPayroll(String nip, String bulan, String tahun, String branchId) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("flagPayroll", "Y"))
//                .add(Restrictions.eq("approvalFlag", "Y"))
//                .list();
//
//        return results;
//    }
    //RAKA-30APR2021==> UNCOMMENT
    public List<ItHrisPayrollEntity> getTunjanganPeralihan(String nip, String bulan, String tahun) throws HibernateException {
        List<ItHrisPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tipePayroll", "PY"))
                .list();

        return results;
    }
//
//    public List<ItPayrollEntity> getLastPayroll(String nip) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("flagPayroll", "Y"))
//                .add(Restrictions.eq("approvalFlag", "Y"))
//                .addOrder(Order.desc("createdDate"))
//                .setMaxResults(1)
//                .list();
//
//        return results;
//    }
//
    public List<ItHrisPayrollEntity> getAllPayroll(String nip) throws HibernateException {
        List<ItHrisPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tipePayroll", "PY"))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public List<ItHrisPayrollEntity> getDataViewMobile(String nip, String branchId, String bulan, String tahun, String payrollId) throws HibernateException {
        List<ItHrisPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("payrollId", payrollId))
                .list();
        return results;
    }
//
//    /*public List<ItPayrollEntity> getDataPayrollBulan(String bulan1, String tahun1, String bulan2, String tahun2, String unit) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("branchId", unit))
//                .add(Restrictions.gt("bulan", bulan1))
//                .add(Restrictions.le("bulan", bulan2))
//                .add(Restrictions.gt("tahun", tahun1))
//                .add(Restrictions.le("tahun", tahun2))
//                .list();
//        return results;
//    }*/
//
//    public List<ItPayrollEntity> getDataView(String branchId, String bulan, String tahun, String tipe) throws HibernateException {
//        String tipeWhere = "";
//        if(tipe.equalsIgnoreCase("PR")){
//            tipeWhere = "flagPayroll";
//        }else if(tipe.equalsIgnoreCase("T")){
//            tipeWhere = "flagThr";
//        }else if(tipe.equalsIgnoreCase("CT")){
//            tipeWhere = "flagCutiTahunan";
//        }else if(tipe.equalsIgnoreCase("CP")){
//            tipeWhere = "flagCutiPanjang";
//        }else if(tipe.equalsIgnoreCase("JP")){
//            tipeWhere = "flagJasprod";
//        }else if(tipe.equalsIgnoreCase("JB")){
//            tipeWhere = "flagJubileum";
//        }else if(tipe.equalsIgnoreCase("PN")){
//            tipeWhere = "flagPensiun";
//        }else if(tipe.equalsIgnoreCase("IN")){
//            tipeWhere = "flagInsentif";
//        }
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq(tipeWhere, "Y"))
//                .createCriteria("imPosition")
//                .addOrder(Order.asc("kelompokId"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataViewPensiun(String branchId, String bulan, String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flagPensiun", "Y"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataViewJubileum(String branchId, String bulan, String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flagJubileum", "Y"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataViewTanpaBulan(String nip, String branchId, String tahun, String tipe) throws HibernateException {
//        String tipeWhere = "";
//        if(tipe.equalsIgnoreCase("PR")){
//            tipeWhere = "flagPayroll";
//        }else if(tipe.equalsIgnoreCase("T")){
//            tipeWhere = "flagThr";
//        }else if(tipe.equalsIgnoreCase("PD")){
//            tipeWhere = "flagPendidikan";
//        }else if(tipe.equalsIgnoreCase("R")){
//            tipeWhere = "flagRapel";
//        }else if(tipe.equalsIgnoreCase("JP")){
//            tipeWhere = "flagJasprod";
//        }else if(tipe.equalsIgnoreCase("JB")){
//            tipeWhere = "flagJubileum";
//        }else if(tipe.equalsIgnoreCase("PN")){
//            tipeWhere = "flagPensiun";
//        }else if(tipe.equalsIgnoreCase("IN")){
//            tipeWhere = "flagInsentif";
//        }
//
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq(tipeWhere, "Y"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataViewBefore(String branchId) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("branchId", branchId))
//                .addOrder(Order.desc("createdDate"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataView(String nip, String branchId, String bulan, String tahun, String tipe) throws HibernateException {
//        String tipeWhere = "";
//        if(tipe.equalsIgnoreCase("PR")){
//            tipeWhere = "flagPayroll";
//        }else if(tipe.equalsIgnoreCase("T")){
//            tipeWhere = "flagThr";
//        }else if(tipe.equalsIgnoreCase("PD")){
//            tipeWhere = "flagPendidikan";
//        }else if(tipe.equalsIgnoreCase("R")){
//            tipeWhere = "flagRapel";
//        }else if(tipe.equalsIgnoreCase("JP")){
//            tipeWhere = "flagJasprod";
//        }else if(tipe.equalsIgnoreCase("JB")){
//            tipeWhere = "flagJubileum";
//        }else if(tipe.equalsIgnoreCase("PN")){
//            tipeWhere = "flagPensiun";
//        }
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq(tipeWhere, "Y"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataViewPensiun(String nip, String branchId, String bulan, String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flagPensiun", "Y"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataViewJubileum(String nip, String branchId, String bulan, String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flagJubileum", "Y"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataRapel(String nip, String branchId) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("flagRapel", "Y"))
//                .add(Restrictions.eq("approvalFlag", "Y"))
//                .setMaxResults(1)
//                .addOrder(Order.desc("createdDate"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getFirstPayroll(String nip, String branchId) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("branchId", branchId))
//                .addOrder(Order.asc("createdDate"))
//                .setMaxResults(1)
//                .list();
//        return results;
//    }
//
//    // cek berapa bulan, rapel
//    public List<ItPayrollEntity> getBulanRapel(String nip, String tahun, String[] bulan) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flagPayroll", "Y"))
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("approvalFlag", "Y"))
//                .add(Restrictions.in("bulan", bulan))
//                //.add(Restrictions.ne("bulan", bulan))
//                .addOrder(Order.asc("bulan"))
//                .list();
//        return results;
//    }
//
//    // cek rapel selain bulan
//    public List<ItPayrollEntity> getBulanRapelLain(String nip, String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flagPayroll", "N"))
//                .add(Restrictions.eq("flagJasprod", "N"))
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("approvalFlag", "Y"))
//                .addOrder(Order.asc("bulan"))
//                .list();
//        return results;
//    }
//
//    // Get history gaji perbulan
//    public List<ItPayrollEntity> getPayrollByNipAndBulan(String nip, String bulan, String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("flagPayroll", "Y"))
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("approvalFlag", "Y"))
//                .addOrder(Order.asc("bulan"))
//                .list();
//        return results;
//    }
//
//    public void approvePayroll(String branchId, String bulan, String tahun, String statusApprove, String tipe){
//        String id = CommonUtil.userIdLogin();
//        String tipeWhere = "";
//        if(tipe.equalsIgnoreCase("PR")){
//            tipeWhere = "and flag_payroll = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("T")){
//            tipeWhere = "and flag_thr = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("PD")){
//            tipeWhere = "and flag_pendidikan = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("R")){
//            tipeWhere = "and flag_rapel= 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JP")){
//            tipeWhere = "and flag_jasprod = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JB")){
//            tipeWhere = "and flag_jubileum = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("PN")){
//            tipeWhere = "and flag_pensiun= 'Y' ";
//        }
//        String query ="";
//        if ("Y".equalsIgnoreCase(statusApprove)){
//            query = "UPDATE it_hris_payroll\n" +
//                    "SET approval_id = '"+id+"',\n" +
//                    "    approval_date = now(),\n" +
//                    "    approval_flag = '"+statusApprove+"'\n" +
//                    "WHERE bulan = '"+bulan+"'\n" +
//                    "AND tahun = '"+tahun+"'\n" +
//                    tipeWhere+
//                    "AND branch_id = '"+branchId+"'\n" +
//                    "AND flag = 'Y'";
//        }else{
//            query = "UPDATE it_hris_payroll\n" +
//                    "SET approval_id = '"+id+"',\n" +
//                    "    approval_date = now(),\n" +
//                    "    approval_flag = '"+statusApprove+"',\n" +
//                    "    approval_sdm_flag = null, \n" +
//                    "    approval_sdm_id = null,\n" +
//                    "    approval_sdm_date = null \n" +
//                    "WHERE bulan = '"+bulan+"'\n" +
//                    "AND tahun = '"+tahun+"'\n" +
//                    tipeWhere+
//                    "AND branch_id = '"+branchId+"'\n" +
//                    "AND flag = 'Y'";
//        }
//
//        this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .executeUpdate();
//    }
//
//    public void approvePayrollAks(String branchId, String bulan, String tahun, String statusApprove, String tipe){
//        String id = CommonUtil.userIdLogin();
//        String tipeWhere = "";
//        if(tipe.equalsIgnoreCase("PR")){
//            tipeWhere = "and flag_payroll = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("T")){
//            tipeWhere = "and flag_thr = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("PD")){
//            tipeWhere = "and flag_pendidikan = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("R")){
//            tipeWhere = "and flag_rapel= 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JP")){
//            tipeWhere = "and flag_jasprod = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JB")){
//            tipeWhere = "and flag_jubileum = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("PN")){
//            tipeWhere = "and flag_pensiun= 'Y' ";
//        }
//
//        String query ="";
//
//        if ("Y".equalsIgnoreCase(statusApprove)){
//            query = "UPDATE it_hris_payroll\n" +
//                    "SET approval_aks_id = '"+id+"',\n" +
//                    "    approval_aks_date = now(),\n" +
//                    "    approval_aks_flag = '"+statusApprove+"'\n" +
//                    "WHERE bulan = '"+bulan+"'\n" +
//                    "AND tahun = '"+tahun+"'\n" +
//                    tipeWhere+
//                    "AND branch_id = '"+branchId+"'\n" +
//                    "AND flag = 'Y'";
//        }else{
//            query = "UPDATE it_hris_payroll\n" +
//                    "SET approval_aks_id = '"+id+"',\n" +
//                    "    approval_aks_date = now(),\n" +
//                    "    approval_aks_flag = '"+statusApprove+"',\n" +
//                    "    approval_sdm_date = null,\n" +
//                    "    approval_sdm_id = null,\n" +
//                    "    approval_sdm_flag = null\n" +
//                    "WHERE bulan = '"+bulan+"'\n" +
//                    "AND tahun = '"+tahun+"'\n" +
//                    tipeWhere+
//                    "AND branch_id = '"+branchId+"'\n" +
//                    "AND flag = 'Y'";
//        }
//
//        this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .executeUpdate();
//    }
//
//    public void approvePayrollUnit(String branchId, String bulan, String tahun, String statusApprove, String tipe){
//        String id = CommonUtil.userIdLogin();
//        String name = CommonUtil.userLogin();
//        String tipeWhere = "";
//        if(tipe.equalsIgnoreCase("PR")){
//            tipeWhere = "and flag_payroll = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("T")){
//            tipeWhere = "and flag_thr = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("PD")){
//            tipeWhere = "and flag_pendidikan = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("R")){
//            tipeWhere = "and flag_rapel= 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JP")){
//            tipeWhere = "and flag_jasprod = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JB")){
//            tipeWhere = "and flag_jubileum = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("PN")){
//            tipeWhere = "and flag_pensiun= 'Y' ";
//        }
//
//        String query ="";
//        if (statusApprove.equalsIgnoreCase("Y")){
//            query = "UPDATE it_hris_payroll\n" +
//                    "SET approval_unit_id = '"+id+"',\n" +
//                    "    approval_unit_date = now(),\n" +
//                    "    approval_unit_name = '"+name+"',\n" +
//                    "    approval_unit_flag = '"+statusApprove+"'\n" +
//                    "WHERE bulan = '"+bulan+"'\n" +
//                    "AND tahun = '"+tahun+"'\n" +
//                    tipeWhere+
//                    "AND branch_id = '"+branchId+"'\n" +
//                    "AND flag = 'Y'";
//        }else{
//            if (CommonUtil.userBranchLogin().equalsIgnoreCase(CommonConstant.ID_KANPUS)){
//                query = "UPDATE it_hris_payroll\n" +
//                        "SET approval_unit_id = '"+id+"',\n" +
//                        "    approval_unit_date = now(),\n" +
//                        "    approval_unit_name = '"+name+"',\n" +
//                        "    approval_unit_flag = '"+statusApprove+"'\n" +
//                        "WHERE bulan = '"+bulan+"'\n" +
//                        "AND tahun = '"+tahun+"'\n" +
//                        tipeWhere+
//                        "AND branch_id = '"+branchId+"'\n" +
//                        "AND flag = 'Y'";
//            }else{
//                query = "UPDATE it_hris_payroll\n" +
//                        "SET approval_unit_id = '"+id+"',\n" +
//                        "    approval_unit_date = now(),\n" +
//                        "    approval_unit_name = '"+name+"',\n" +
//                        "    approval_unit_flag = '"+statusApprove+"',\n" +
//                        "    approval_id = '"+id+"',\n" +
//                        "    approval_date = now(),\n" +
//                        "    approval_name = '"+name+"', \n" +
//                        "    flag = '"+statusApprove+"', \n" +
//                        "    approval_flag = '"+statusApprove+"' \n" +
//                        "WHERE bulan = '"+bulan+"'\n" +
//                        "AND tahun = '"+tahun+"'\n" +
//                        tipeWhere+
//                        "AND branch_id = '"+branchId+"'\n" +
//                        "AND flag = 'Y'";
//            }
//
//        }
//
//        this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .executeUpdate();
//    }
//
//    public void approvePayrollSdm(String branchId, String bulan, String tahun, String statusApprove, String tipe){
//        String id = CommonUtil.userIdLogin();
//        String name = CommonUtil.userLogin();
//        String tipeWhere = "";
//        if(tipe.equalsIgnoreCase("PR")){
//            tipeWhere = "and flag_payroll = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("T")){
//            tipeWhere = "and flag_thr = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("PD")){
//            tipeWhere = "and flag_pendidikan = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("R")){
//            tipeWhere = "and flag_rapel= 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JP")){
//            tipeWhere = "and flag_jasprod = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("JB")){
//            tipeWhere = "and flag_jubileum = 'Y' ";
//        }else if(tipe.equalsIgnoreCase("PN")){
//            tipeWhere = "and flag_pensiun= 'Y' ";
//        }
//
//        String query = "";
//
//        if (CommonConstant.ID_KANPUS.equalsIgnoreCase(branchId)){
//            if ("N".equalsIgnoreCase(statusApprove)){
//                query = "UPDATE it_hris_payroll\n" +
//                        "SET approval_sdm_id = '"+id+"',\n" +
//                        "    approval_sdm_date = now(),\n" +
//                        "    approval_sdm_name = '"+name+"',\n" +
//                        "    approval_sdm_flag = '"+statusApprove+"',\n" +
//                        "    flag = '"+statusApprove+"'\n" +
//                        "WHERE bulan = '"+bulan+"'\n" +
//                        "AND tahun = '"+tahun+"'\n" +
//                        tipeWhere+
//                        "AND branch_id = '"+branchId+"'\n" +
//                        "AND flag = 'Y'";
//            }else{
//                query = "UPDATE it_hris_payroll\n" +
//                        "SET approval_sdm_id = '"+id+"',\n" +
//                        "    approval_sdm_date = now(),\n" +
//                        "    approval_sdm_name = '"+name+"',\n" +
//                        "    approval_sdm_flag = '"+statusApprove+"'\n" +
//                        "WHERE bulan = '"+bulan+"'\n" +
//                        "AND tahun = '"+tahun+"'\n" +
//                        tipeWhere+
//                        "AND branch_id = '"+branchId+"'\n" +
//                        "AND flag = 'Y'";
//            }
//        }else{
//            if ("N".equalsIgnoreCase(statusApprove)){
//                query = "UPDATE it_hris_payroll\n" +
//                        "SET approval_sdm_id = '"+id+"',\n" +
//                        "    approval_sdm_date = now(),\n" +
//                        "    approval_sdm_name = '"+name+"',\n" +
//                        "    approval_sdm_flag = '"+statusApprove+"',\n" +
//                        "    approval_unit_id = null,\n" +
//                        "    approval_unit_date = null,\n" +
//                        "    approval_unit_name = null,\n" +
//                        "    approval_unit_flag = null\n" +
//                        "WHERE bulan = '"+bulan+"'\n" +
//                        "AND tahun = '"+tahun+"'\n" +
//                        tipeWhere+
//                        "AND branch_id = '"+branchId+"'\n" +
//                        "AND flag = 'Y'";
//            }else{
//                query = "UPDATE it_hris_payroll\n" +
//                        "SET approval_sdm_id = '"+id+"',\n" +
//                        "    approval_sdm_date = now(),\n" +
//                        "    approval_sdm_name = '"+name+"',\n" +
//                        "    approval_sdm_flag = '"+statusApprove+"'\n" +
//                        "WHERE bulan = '"+bulan+"'\n" +
//                        "AND tahun = '"+tahun+"'\n" +
//                        tipeWhere+
//                        "AND branch_id = '"+branchId+"'\n" +
//                        "AND flag = 'Y'";
//            }
//        }
//
//        this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .executeUpdate();
//    }
//
//    public List<ItPayrollEntity> getListBayarUpah(String nip, String bulanAwal, String bulanAkhir, String tahunAwal, String tahunAkhir){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_Id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.position_name,\n" +
//                "\tpayroll.bulan,\n" +
//                "\tpayroll.tahun,\n" +
//                "\tupahHarian.tanggal,\n" +
//                "\tpegawai.gaji,\n" +
//                "\tCASE WHEN payroll.approval_flag IS NULL THEN 'B' ELSE payroll.approval_flag END" +
//                "\n" +
//                "from \n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join it_hris_payroll_upah_harian upahHarian on upahHarian.payroll_id = payroll.payroll_id and upahHarian.flag = 'Y'\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "where\n" +
//                "\tpayroll.nip = '"+nip+"'\n" +
//                "\tand payroll.flag = 'Y'\n" +
//                "and upahHarian is not null" +
//                "\t";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setTanggal((Date) row[6]);
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setApprovalFlag((String) row[8]);
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollBulan(String bulan1, String tahun1, String unit, String statusPegawai){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.*,\n" +
//                "\tpegawai.point,\n " +
//                "\tpegawai.jenis_kelamin " +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_payroll = 'Y'\n" +
//                "\tand pegawai.status_pegawai = '"+statusPegawai+"'";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setPayrollId((String) row[1]);
//            result.setGolonganName((String) row[8]);
//            if (row[91]!=null){
//                result.setPoint((Integer) row[91]);
//                result.setGender((String) row[92]);
//            }else{
//                result.setPoint((Integer) row[91]);
//                result.setGender((String) row[92]);
//            }
//            result.setNip((String) row[3]);
//            result.setNama((String) row[4]);
//            result.setStatusKeluarga((String) row[14]);
//            result.setJumlahAnak((Integer) row[15]);
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
//            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
//            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
//            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[29].toString())));
//            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
//            result.setTunjanganLain(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
//            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
//            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
//            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
//
//            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[72].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[76].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollPotonganDinas(String bulan1, String tahun1, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.*,\n" +
//                "\tpegawai.point\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_payroll = 'Y'";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setPayrollId((String) row[1]);
//            result.setGolonganName((String) row[8]);
//            result.setPoint((Integer) row[91]);
//            result.setNip((String) row[3]);
//            result.setNama((String) row[4]);
//            result.setStatusKeluarga((String) row[14]);
//            result.setJumlahAnak((Integer) row[15]);
//
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[36].toString())));
//            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[37].toString())));
//            result.setIuranPensiun(BigDecimal.valueOf(Double.valueOf(row[38].toString())));
//            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[39].toString())));
//            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[41].toString())));
//            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[40].toString())));
//            result.setUangMukaLainnya(BigDecimal.valueOf(Double.valueOf(row[42].toString())));
//            result.setKekuranganBpjsTk(BigDecimal.valueOf(Double.valueOf(row[43].toString())));
//
//            result.setTotalB(BigDecimal.valueOf(Double.valueOf(row[77].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollPotonganLainLain(String bulan1, String tahun1, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.*,\n" +
//                "\tpegawai.point\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_payroll = 'Y'";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setPayrollId((String) row[1]);
//            result.setNip((String) row[3]);
//            result.setNama((String) row[4]);
//
//            result.setPengobatan(BigDecimal.valueOf(Double.valueOf(row[44].toString())));
//            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[45].toString())));
//            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[46].toString())));
//            result.setSP(BigDecimal.valueOf(Double.valueOf(row[47].toString())));
//            result.setBazis(BigDecimal.valueOf(Double.valueOf(row[48].toString())));
//            result.setBapor(BigDecimal.valueOf(Double.valueOf(row[49].toString())));
//            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[51].toString())));
//            result.setZakat(BigDecimal.valueOf(Double.valueOf(row[50].toString())));
//            result.setTotalC(BigDecimal.valueOf(Double.valueOf(row[78].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollPenghasilanKaryawan(String bulan1, String tahun1, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.*,\n" +
//                "\tpegawai.point\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_payroll = 'Y'";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setPayrollId((String) row[1]);
//            result.setGolonganName((String) row[8]);
//            result.setPoint((Integer) row[87]);
//            result.setNip((String) row[3]);
//            result.setNama((String) row[4]);
//            result.setStatusKeluarga((String) row[14]);
//            result.setJumlahAnak((Integer) row[15]);
//
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[72].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[76].toString())));
//            result.setTotalB(BigDecimal.valueOf(Double.valueOf(row[77].toString())));
//            result.setTotalC(BigDecimal.valueOf(Double.valueOf(row[78].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollPendidikan(String bulan1, String tahun1, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpendidikan.*,\n" +
//                "\tpegawai.point,\n" +
//                "\tpayroll.pph_gaji,\n" +
//                "\tpayroll.gaji_bersih\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_pendidikan pendidikan on pendidikan.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_pendidikan = 'Y'\n" +
//                "\tand pendidikan.pendidikan_id is not null";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setGolonganName((String) row[3]);
//            result.setStatusKeluarga((String) row[4]);
//            result.setJumlahAnak((Integer) row[5]);
//            result.setBulan((Integer) row[23] + "");
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//            result.setPoint((Integer) row[26]);
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
//
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//            result.setTotalPendidikan(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<PayrollRapel> getDataPayrollRapel(String bulan1, String tahun1, String unit, String status){
//        List<PayrollRapel> listOfResult = new ArrayList<PayrollRapel>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\trapel.*,\n" +
//                "\tpegawai.point\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand pegawai.status_pegawai = '"+status+"' " +
//                "\tand rapel.flag = 'Y'\n "+
//                "\tand payroll.flag_rapel = 'Y' ";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            PayrollRapel result  = new PayrollRapel();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setGolonganName((String) row[3]);
//            result.setStatusKeluarga((String) row[4]);
//            result.setJumlahAnak((Integer) row[5]);
//
//
//            if(row[6] != null){
//                result.setJumlahBulan((Integer) row[33]);
//                result.setTunjanganStrukturalLamaNilai(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//                result.setTunjanganJabatanStrukturalLamaNilai(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//                result.setTunjanganStrategisLamaNilai(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//                result.setTunjanganAirListrikLamaNilai(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//                result.setTunjanganUmkLamaNilai(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//                result.setGajiGolonganLamaNilai(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//                result.setTunjanganPerumahanLamaNilai(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//
//                result.setGajiGolonganBaruNilai(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//                result.setTunjanganStrukturalBaruNilai(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
//                result.setTunjanganUmkBaruNilai(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
//                result.setTunjanganJabatanStrukturalBaruNilai(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//                result.setTunjanganStrategisBaruNilai(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
//                result.setTunjanganAirListrikBaruNilai(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
//                result.setTunjanganPerumahanBaruNilai(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
//                result.setGolonganIdLama((String) row[29]);
//                result.setPointLama((String) row[30]);
//                result.setGolonganIdBaru((String) row[31]);
//                result.setPointBaru((String) row[32]);
//
//                result.setGajiGolonganNilai(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
//                result.setTunjanganUmkNilai(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
//                result.setTunjanganStrukturalNilai(BigDecimal.valueOf(Double.valueOf(row[36].toString())));
//                result.setTunjanganJabatanStrukturalNilai(BigDecimal.valueOf(Double.valueOf(row[37].toString())));
//                result.setTunjanganAirListrikNilai(BigDecimal.valueOf(Double.valueOf(row[38].toString())));
//                result.setTunjanganPerumahanNilai(BigDecimal.valueOf(Double.valueOf(row[39].toString())));
//                result.setTunjanganStrategisNilai(BigDecimal.valueOf(Double.valueOf(row[40].toString())));
//
//                result.setTotalRapelInsentifNilai(BigDecimal.valueOf(Double.valueOf(row[41].toString())));
//                result.setTotalRapelThrNilai(BigDecimal.valueOf(Double.valueOf(row[42].toString())));
//                result.setTotalRapelPendidikanNilai(BigDecimal.valueOf(Double.valueOf(row[43].toString())));
//                result.setTotalRapelJubileumNilai(BigDecimal.valueOf(Double.valueOf(row[44].toString())));
//
//                result.setTotalRapelBulanNilai(BigDecimal.valueOf(Double.valueOf(row[45].toString())));
//                result.setTotalRapelFinalNilai(BigDecimal.valueOf(Double.valueOf(row[46].toString())));
//                result.setTotalRapelLemburNilai(BigDecimal.valueOf(Double.valueOf(row[47].toString())));
//
//                result.setPphRapelNilai(BigDecimal.valueOf(Double.valueOf(row[50].toString())));
//                result.setPphRapelPribadiNilai(BigDecimal.valueOf(Double.valueOf(row[51].toString())));
//                result.setTunjanganPphNilai(BigDecimal.valueOf(Double.valueOf(row[52].toString())));
//                result.setRapelBersihNilai(BigDecimal.valueOf(Double.valueOf(row[53].toString())));
//
//                result.setPoint((Integer) row[54]);
//
//                result.setTotalRapelNilai(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//            }else{
//                result.setTunjanganStrukturalLamaNilai(BigDecimal.valueOf(0));
//                result.setTunjanganJabatanStrukturalLamaNilai(BigDecimal.valueOf(0));
//                result.setTunjanganStrategisLamaNilai(BigDecimal.valueOf(0));
//                result.setTunjanganAirListrikLamaNilai(BigDecimal.valueOf(0));
//                result.setTunjanganUmkLamaNilai(BigDecimal.valueOf(0));
//                result.setGajiGolonganLamaNilai(BigDecimal.valueOf(0));
//                result.setTunjanganPerumahanLamaNilai(BigDecimal.valueOf(0));
//
//                result.setGajiGolonganBaruNilai(BigDecimal.valueOf(0));
//                result.setTunjanganStrukturalBaruNilai(BigDecimal.valueOf(0));
//                result.setTunjanganUmkBaruNilai(BigDecimal.valueOf(0));
//                result.setTunjanganJabatanStrukturalBaruNilai(BigDecimal.valueOf(0));
//                result.setTunjanganStrategisBaruNilai(BigDecimal.valueOf(0));
//                result.setTunjanganAirListrikBaruNilai(BigDecimal.valueOf(0));
//                result.setTunjanganPerumahanBaruNilai(BigDecimal.valueOf(0));
//
//                result.setGajiGolonganNilai(BigDecimal.valueOf(0));
//                result.setTunjanganUmkNilai(BigDecimal.valueOf(0));
//                result.setTunjanganStrukturalNilai(BigDecimal.valueOf(0));
//                result.setTunjanganJabatanStrukturalNilai(BigDecimal.valueOf(0));
//                result.setTunjanganAirListrikNilai(BigDecimal.valueOf(0));
//                result.setTunjanganPerumahanNilai(BigDecimal.valueOf(0));
//                result.setTunjanganStrategisNilai(BigDecimal.valueOf(0));
//
//                result.setTotalRapelInsentifNilai(BigDecimal.valueOf(0));
//                result.setTotalRapelThrNilai(BigDecimal.valueOf(0));
//                result.setTotalRapelPendidikanNilai(BigDecimal.valueOf(0));
//                result.setTotalRapelJubileumNilai(BigDecimal.valueOf(0));
//                result.setTotalRapelLemburNilai(BigDecimal.valueOf(0));
//
//                result.setTotalRapelBulanNilai(BigDecimal.valueOf(0));
//                result.setTotalRapelFinalNilai(BigDecimal.valueOf(0));
//
//                result.setPphRapelNilai(BigDecimal.valueOf(Double.valueOf(0)));
//                result.setPphRapelPribadiNilai(BigDecimal.valueOf(Double.valueOf(0)));
//                result.setTunjanganPphNilai(BigDecimal.valueOf(Double.valueOf(0)));
//                result.setRapelBersihNilai(BigDecimal.valueOf(Double.valueOf(0)));
//                //result.setPoint((Integer) row[47]);
//
//                result.setTotalRapelNilai(BigDecimal.valueOf(0));
//            }
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    // Rapel THR
//    public List<PayrollRapelThr> getDataPayrollRapelThr(String bulan1, String tahun1, String unit, String status){
//        List<PayrollRapelThr> listOfResult = new ArrayList<PayrollRapelThr>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpegawai.point,\n" +
//                "\trapel.rapel_id,\n" +
//                "\trapelThr.payroll_thr_id,\n" +
//                "\trapelThr.gaji_golongan_baru,\n" +
//                "\trapelThr.tunjangan_umk_baru,\n" +
//                "\trapelThr.tunjangan_peralihan_baru,\n" +
//                "\trapelThr.tunjangan_struktural_baru,\n" +
//                "\trapelThr.tunjangan_strategis as tunjangan_strategis_baru,\n" +
//                "\trapelThr.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural_baru,\n" +
//                "\trapelThr.selisih_gaji_golongan,\n" +
//                "\trapelThr.selisih_tunjangan_umk,\n" +
//                "\trapelThr.selisih_tunjangan_peralihan,\n" +
//                "\trapelThr.selisih_tunjangan_struktural,\n" +
//                "\trapelThr.selisih_tunjangan_jabatan_struktural,\n" +
//                "\trapelThr.selisih_tunjangan_strategis,\n" +
//                "\trapelThr.total_selisih_thr,\n" +
//                "\tthr.*,\n" +
//                "\tpayrollLama.golongan_name as golongan_name_lama,\n" +
//                "\tpayrollLama.point as point_baru\n" +
//                "\t\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_rapel_thr rapelThr on rapelThr.payroll_rapel_id = rapel.rapel_id\n" +
//                "\tleft join it_hris_payroll_thr thr on thr.thr_id = rapelThr.payroll_thr_id\n" +
//                "\tleft join it_hris_payroll payrollLama on payrollLama.payroll_id = thr.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_rapel = 'Y'\n" +
//                "\tand pegawai.status_pegawai = '"+status+"'\n" +
//                "\tand rapelThr.payroll_thr_id is not null";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            PayrollRapelThr result  = new PayrollRapelThr();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setGolonganName((String) row[3]);
//            result.setStatusKeluarga((String) row[4]);
//            result.setJumlahAnak((Integer) row[5]);
//            result.setPoint((Integer) row[6]);
//
//            result.setRapelId((String) row[7]);
//            result.setPayrollThrId((String) row[8]);
//
//            result.setGolonganNameLama((String) row[40]);
//            result.setPointLama((Integer) row[41]);
//
//            if(row[8] != null){
//                result.setThrGajiGolonganNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//                result.setThrUmkNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//                result.setThrPeralihanNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//                result.setThrStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//                result.setThrStrategisNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//                result.setThrJabStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//
//                result.setThrGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//                result.setThrUmkNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//                result.setThrPeralihanNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//                result.setThrStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//                result.setThrJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//                result.setThrStrategisNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//
//                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//
//                result.setThrPeralihanNilaiLama(BigDecimal.valueOf(Double.valueOf(row[39].toString())));
//                result.setThrUmkNilaiLama(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
//                result.setThrStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
//                result.setThrJabStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
//                result.setThrStrategisNilaiLama(BigDecimal.valueOf(Double.valueOf(row[33].toString())));
//                result.setThrGajiGolonganNilaiLama(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
//            }else{
//                result.setThrGajiGolonganNilaiBaru(BigDecimal.valueOf(0));
//                result.setThrUmkNilaiBaru(BigDecimal.valueOf(0));
//                result.setThrPeralihanNilaiBaru(BigDecimal.valueOf(0));
//                result.setThrStrukturalNilaiBaru(BigDecimal.valueOf(0));
//                result.setThrStrategisNilaiBaru(BigDecimal.valueOf(0));
//                result.setThrJabStrukturalNilaiBaru(BigDecimal.valueOf(0));
//
//                result.setThrGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setThrUmkNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setThrPeralihanNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setThrStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setThrJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setThrStrategisNilaiSelisihBaru(BigDecimal.valueOf(0));
//
//                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(0));
//
//                result.setThrUmkNilaiLama(BigDecimal.valueOf(0));
//                result.setThrPeralihanNilaiLama(BigDecimal.valueOf(0));
//                result.setThrStrukturalNilaiLama(BigDecimal.valueOf(0));
//                result.setThrJabStrukturalNilaiLama(BigDecimal.valueOf(0));
//                result.setThrStrategisNilaiLama(BigDecimal.valueOf(0));
//                result.setThrGajiGolonganNilaiLama(BigDecimal.valueOf(0));
//            }
//
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    // Rapel Jubileum
//    public List<PayrollRapelJubileum> getDataPayrollRapelJubileum(String bulan1, String tahun1, String unit){
//        List<PayrollRapelJubileum> listOfResult = new ArrayList<PayrollRapelJubileum>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tposisi.position_name,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpegawai.point,\n" +
//                "\trapel.rapel_id,\n" +
//                "\trapelJubileum.payroll_jubileum_id,\n" +
//                "\trapelJubileum.gaji_golongan_baru,\n" +
//                "\trapelJubileum.tunjangan_umk_baru,\n" +
//                "\trapelJubileum.tunjangan_struktural_baru,\n" +
//                "\trapelJubileum.tunjangan_peralihan_baru,\n" +
//                "\trapelJubileum.tunjangan_jabatan_struktural_baru,\n" +
//                "\trapelJubileum.selisih_gaji_golongan,\n" +
//                "\trapelJubileum.selisih_tunjangan_umk,\n" +
//                "\trapelJubileum.selisih_tunjangan_struktural,\n" +
//                "\trapelJubileum.selisih_tunjangan_jabatan_struktural,\n" +
//                "\trapelJubileum.selisih_tunjangan_peralihan,\n" +
//                "\trapelJubileum.total_selisih_jubileum,\n" +
//                "\trapelJubileum.total_rapel_jubileum,\n" +
//                "\tjubileum.gaji_golongan as gaji_golongan_lama,\n" +
//                "\tjubileum.tunjangan_umk as tunjangan_umk_lama,\n" +
//                "\tjubileum.tunjangan_struktural as tunjangan_struktural_lama,\n" +
//                "\tjubileum.tunjangan_peralihan as tunjangan_peralihan_lama,\n" +
//                "\tjubileum.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural_lama,\n" +
//                "\tjubileum.total_jubileum as total_jubileum_lama,\n" +
//                "\tjubileum.grand_total as total_rapel_jubileum_lama,\n" +
//                "\tjubileum.tanggal_jubileum,\n" +
//                "\tgolonganBaru.golongan_name as golongan_nama_baru,\n" +
//                "\tpayroll.point as point_baru,\n" +
//                "\tgolonganLama.golongan_name as golongan_nama_lama,\n" +
//                "\tpayrollLama.point as point_lama\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_rapel_jubileum rapelJubileum on rapelJubileum.payroll_rapel_id = rapel.rapel_id\n" +
//                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.jubileum_id = rapelJubileum.payroll_jubileum_id\n" +
//                "\tleft join im_position posisi on posisi.position_id = payroll.position_id\n" +
//                "\tleft join im_hris_golongan golonganBaru on golonganBaru.golongan_id = payroll.golongan_id\n" +
//                "\tleft join it_hris_payroll payrollLama on payrollLama.payroll_id = jubileum.payroll_id\n" +
//                "\tleft join im_hris_golongan golonganLama on golonganLama.golongan_id = payrollLama.golongan_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_rapel = 'Y'\n" +
//                "\tand rapelJubileum.payroll_jubileum_id is not null";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            PayrollRapelJubileum result  = new PayrollRapelJubileum();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setPositionName((String) row[2]);
//            result.setNama((String) row[3]);
//            result.setGolonganName((String) row[4]);
//            result.setStatusKeluarga((String) row[5]);
//            result.setJumlahAnak((Integer) row[6]);
//            result.setPoint((Integer) row[7]);
//
//            result.setRapelId((String) row[8]);
//            result.setPayrollJubileumId((String) row[9]);
//
//            if(row[9] != null){
//                result.setJubileumGajiGolonganNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//                result.setJubileumUmkNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//                result.setJubileumStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//                result.setJubileumPeralihanNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//                result.setJubileumJabStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//
//                result.setJubileumGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//                result.setJubileumUmkNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//                result.setJubileumStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//                result.setJubileumJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//                result.setJubileumPeralihanNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//
//                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//                result.setTotalRapelJubileumNilai(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//
//                result.setJubileumGajiGolonganNilaiLama(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//                result.setJubileumUmkNilaiLama(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
//                result.setJubileumStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
//                result.setJubileumPeralihanNilaiLama(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//                result.setJubileumJabStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
//
//                result.setTotalRapelNilaiSelisihLama(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
//                result.setTotalRapelJubileumLamaNilai(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
//                result.setTanggalJubileum((Date)row[29]);
//
//                result.setGolonganName((String)row[30]);
//                result.setPoint((Integer) row[31]);
//
//                result.setGolonganNameLama((String)row[32]);
//                result.setPointLama((Integer) row[33]);
//            }else{
//                result.setJubileumGajiGolonganNilaiBaru(BigDecimal.valueOf(0));
//                result.setJubileumUmkNilaiBaru(BigDecimal.valueOf(0));
//                result.setJubileumStrukturalNilaiBaru(BigDecimal.valueOf(0));
//                result.setJubileumPeralihanNilaiBaru(BigDecimal.valueOf(0));
//                result.setJubileumJabStrukturalNilaiBaru(BigDecimal.valueOf(0));
//
//                result.setJubileumGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setJubileumUmkNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setJubileumStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setJubileumJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setJubileumPeralihanNilaiSelisihBaru(BigDecimal.valueOf(0));
//
//                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setTotalRapelJubileumNilai(BigDecimal.valueOf(0));
//
//                result.setJubileumGajiGolonganNilaiLama(BigDecimal.valueOf(0));
//                result.setJubileumUmkNilaiLama(BigDecimal.valueOf(0));
//                result.setJubileumStrukturalNilaiLama(BigDecimal.valueOf(0));
//                result.setJubileumPeralihanNilaiLama(BigDecimal.valueOf(0));
//                result.setJubileumJabStrukturalNilaiLama(BigDecimal.valueOf(0));
//
//                result.setTotalRapelNilaiSelisihLama(BigDecimal.valueOf(0));
//                result.setTotalRapelJubileumLamaNilai(BigDecimal.valueOf(0));
//            }
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    // Rapel Pendidikan
//    public List<PayrollRapelPendidikan> getDataPayrollRapelPendidikan(String bulan1, String tahun1, String unit, String status){
//        List<PayrollRapelPendidikan> listOfResult = new ArrayList<PayrollRapelPendidikan>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpegawai.point,\n" +
//                "\trapel.rapel_id,\n" +
//                "\trapelPendidikan.payroll_pendidikan_id,\n" +
//                "\trapelPendidikan.gaji_golongan_baru,\n" +
//                "\trapelPendidikan.tunjangan_umk_baru,\n" +
//                "\trapelPendidikan.tunjangan_peralihan_baru,\n" +
//                "\trapelPendidikan.tunjangan_struktural_baru,\n" +
//                "\trapelPendidikan.tunjangan_strategis as tunjangan_strategis_baru,\n" +
//                "\trapelPendidikan.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural_baru,\n" +
//                "\trapelPendidikan.tunjangan_air_listrik as tunjangan_air_listrik_baru,\n" +
//                "\trapelPendidikan.selisih_gaji_dasar,\n" +
//                "\trapelPendidikan.selisih_tunjangan_umk,\n" +
//                "\trapelPendidikan.selisih_tunjangan_peralihan,\n" +
//                "\trapelPendidikan.selisih_tunjangan_struktural,\n" +
//                "\trapelPendidikan.selisih_tunjangan_jabatan_struktural,\n" +
//                "\trapelPendidikan.selisih_tunjangan_strategis,\n" +
//                "\trapelPendidikan.selisih_tunjangan_air_listrik,\n" +
//                "\trapelPendidikan.total_selisih_pendidikan,\n" +
//                "\tpendidikan.*,\n" +
//                "\tpayrollLama.golongan_name as golongan_name_lama,\n" +
//                "\tpayrollLama.point as point_baru\n" +
//                "\t\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_rapel_pendidikan rapelPendidikan on rapelPendidikan.payroll_rapel_id = rapel.rapel_id\n" +
//                "\tleft join it_hris_payroll_pendidikan pendidikan on pendidikan.pendidikan_id = rapelPendidikan.payroll_pendidikan_id\n" +
//                "\tleft join it_hris_payroll payrollLama on payrollLama.payroll_id = pendidikan.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_rapel = 'Y'\n" +
//                "\tand pegawai.status_pegawai = '"+status+"'\n" +
//                "\tand rapelPendidikan.payroll_pendidikan_id is not null";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            PayrollRapelPendidikan result  = new PayrollRapelPendidikan();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setGolonganName((String) row[3]);
//            result.setStatusKeluarga((String) row[4]);
//            result.setJumlahAnak((Integer) row[5]);
//            result.setPoint((Integer) row[6]);
//            result.setGolonganNameLama((String) row[44]);
//            result.setPointLama((Integer) row[45]);
//
//            result.setRapelId((String) row[7]);
//            result.setPayrollPendidikanId((String) row[8]);
//
//            if(row[8] != null){
//                result.setPendidikanGajiGolonganNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//                result.setPendidikanUmkNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//                result.setPendidikanPeralihanNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//                result.setPendidikanStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//                result.setPendidikanStrategisNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//                result.setPendidikanJabStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//                if(row[15] != null){
//                    result.setPendidikanAirListrikNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//                }else{
//                    result.setPendidikanAirListrikNilaiBaru(BigDecimal.valueOf(0));
//                }
//
//                result.setPendidikanGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//                result.setPendidikanUmkNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//                result.setPendidikanPeralihanNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//                result.setPendidikanStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//                result.setPendidikanJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//                result.setPendidikanStrategisNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//                result.setPendidikanAirListrikNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//
//                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
//
//                result.setPendidikanUmkNilaiLama(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
//                result.setPendidikanPeralihanNilaiLama(BigDecimal.valueOf(Double.valueOf(row[43].toString())));
//                result.setPendidikanStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[33].toString())));
//                result.setPendidikanJabStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
//                result.setPendidikanStrategisNilaiLama(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
//                result.setPendidikanAirListrikNilaiLama(BigDecimal.valueOf(Double.valueOf(row[37].toString())));
//                result.setPendidikanGajiGolonganNilaiLama(BigDecimal.valueOf(Double.valueOf(row[39].toString())));
//            }else{
//                result.setPendidikanGajiGolonganNilaiBaru(BigDecimal.valueOf(0));
//                result.setPendidikanUmkNilaiBaru(BigDecimal.valueOf(0));
//                result.setPendidikanPeralihanNilaiBaru(BigDecimal.valueOf(0));
//                result.setPendidikanStrukturalNilaiBaru(BigDecimal.valueOf(0));
//                result.setPendidikanStrategisNilaiBaru(BigDecimal.valueOf(0));
//                result.setPendidikanJabStrukturalNilaiBaru(BigDecimal.valueOf(0));
//                result.setPendidikanAirListrikNilaiBaru(BigDecimal.valueOf(0));
//
//                result.setPendidikanGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setPendidikanUmkNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setPendidikanPeralihanNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setPendidikanStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setPendidikanJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setPendidikanStrategisNilaiSelisihBaru(BigDecimal.valueOf(0));
//                result.setPendidikanAirListrikNilaiSelisihBaru(BigDecimal.valueOf(0));
//
//                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(0));
//
//                result.setPendidikanPeralihanNilaiLama(BigDecimal.valueOf(0));
//                result.setPendidikanUmkNilaiLama(BigDecimal.valueOf(0));
//                result.setPendidikanStrukturalNilaiLama(BigDecimal.valueOf(0));
//                result.setPendidikanJabStrukturalNilaiLama(BigDecimal.valueOf(0));
//                result.setPendidikanStrategisNilaiLama(BigDecimal.valueOf(0));
//                result.setPendidikanGajiGolonganNilaiLama(BigDecimal.valueOf(0));
//                result.setPendidikanAirListrikNilaiLama(BigDecimal.valueOf(0));
//            }
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    // Rapel Insentif
//    public List<PayrollRapelInsentif> getDataPayrollRapelInsentif(String bulan1, String tahun1, String unit, String status){
//        List<PayrollRapelInsentif> listOfResult = new ArrayList<PayrollRapelInsentif>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpegawai.point,\n" +
//                "\trapel.rapel_id,\n" +
//                "\trapelinsentif.nip,\n" +
//                "\trapelinsentif.payroll_insentif_id,\n" +
//                "\trapelinsentif.gaji_golongan_baru,\n" +
//                "\trapelinsentif.tunjangan_umk_baru,\n" +
//                "\trapelinsentif.tunjangan_struktural_baru,\n" +
//                "\trapelinsentif.tunjangan_strategis_baru,\n" +
//                "\trapelinsentif.tunjangan_peralihan_baru,\n" +
//                "\trapelinsentif.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural_baru,\n" +
//                "\trapelinsentif.bruto_baru,\n" +
//                "\trapelinsentif.potongan_insentif,\n" +
//                "\trapelinsentif.potongan_insentif_individu_baru,\n" +
//                "\trapelinsentif.masa_kerja,\n" +
//                "\trapelinsentif.smk_standart,\n" +
//                "\trapelinsentif.smk_huruf,\n" +
//                "\trapelinsentif.smk_angka,\n" +
//                "\trapelinsentif.jumlah_insentif_baru,\n" +
//                "\trapelinsentif.jumlah_insentif_lama,\n" +
//                "\trapelinsentif.total_rapel_insentif,\n" +
//                "\tinsentif.gaji_golongan,\n" +
//                "\tinsentif.tunjangan_umk,\n" +
//                "\tinsentif.tunjangan_struktural,\n" +
//                "\tinsentif.tunjangan_peralihan,\n" +
//                "\tinsentif.tunjangan_jab_struktural,\n" +
//                "\tinsentif.tunjangan_strategis,\n" +
//                "\tinsentif.jumlah_bruto,\n" +
//                "\tinsentif.potongan_insentif_individu,\n" +
//                "\tpayrollLama.golongan_name as golongan_name_lama,\n" +
//                "\tpayrollLama.point as point_baru\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_rapel_insentif rapelinsentif on rapelinsentif.payroll_rapel_id = rapel.rapel_id\n" +
//                "\tleft join it_hris_payroll_insentif insentif on insentif.insentif_id = rapelinsentif.payroll_insentif_id\n" +
//                "\tleft join it_hris_payroll payrollLama on payrollLama.payroll_id = insentif.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_rapel = 'Y'\n" +
//                "\tand pegawai.status_pegawai = '"+status+"'\n" +
//                "\tand rapelInsentif.payroll_insentif_id is not null";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            PayrollRapelInsentif result  = new PayrollRapelInsentif();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setGolonganName((String) row[3]);
//            result.setStatusKeluarga((String) row[4]);
//            result.setJumlahAnak((Integer) row[5]);
//            result.setPoint((Integer) row[6]);
//
//            result.setGolonganNameLama((String) row[34]);
//            result.setPointLama((Integer) row[35]);
//
//            result.setRapelId((String) row[7]);
//            result.setNip((String) row[8]);
//            result.setPayrollInsentifId((String) row[9]);
//
//            if(row[9] != null){
//                result.setInsentifGajiGolonganNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//                result.setInsentifUmkNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//                result.setInsentifStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//                result.setInsentifStrategisNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//                result.setInsentifPeralihanNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//                result.setInsentifJabStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//                result.setBrutoInsentifNilai(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//                result.setPotonganInsentifNilai(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//                result.setPotonganInsentifIndividuNilai(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//                result.setMasaKerja((Integer) row[19]);
//                result.setSmkStandart(row[20].toString());
//                result.setSmkStandartNilai(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//                result.setSmkHuruf((String) row[21]);
//                result.setSmkAngkaNilai(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//                result.setInsentifYangDiterimaBaruNilai(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
//                result.setInsentifYangDiterimaLamaNilai(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
//                result.setInsentifYangDiterimaSelisihNilai(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//
//                result.setInsentifGajiGolonganNilaiLama(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
//                result.setInsentifUmkNilaiLama(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
//                result.setInsentifStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
//                result.setInsentifPeralihanNilaiLama(BigDecimal.valueOf(Double.valueOf(row[29].toString())));
//                result.setInsentifJabStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
//                result.setInsentifStrategisNilaiLama(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
//                result.setBrutoInsentifLamaNilai(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
//                result.setPotonganInsentifIndividuLamaNilai(BigDecimal.valueOf(Double.valueOf(row[33].toString())));
//            }else{
//                result.setInsentifGajiGolonganNilaiBaru(BigDecimal.valueOf(0));
//                result.setInsentifUmkNilaiBaru(BigDecimal.valueOf(0));
//                result.setInsentifStrukturalNilaiBaru(BigDecimal.valueOf(0));
//                result.setInsentifStrategisNilaiBaru(BigDecimal.valueOf(0));
//                result.setInsentifPeralihanNilaiBaru(BigDecimal.valueOf(0));
//                result.setInsentifJabStrukturalNilaiBaru(BigDecimal.valueOf(0));
//                result.setBrutoInsentifNilai(BigDecimal.valueOf(0));
//                result.setPotonganInsentifNilai(BigDecimal.valueOf(0));
//                result.setPotonganInsentifIndividuNilai(BigDecimal.valueOf(0));
//                result.setMasaKerja(0);
//                result.setSmkStandart("0");
//                result.setSmkHuruf("");
//                result.setSmkAngkaNilai(BigDecimal.valueOf(Double.valueOf(0)));
//                result.setInsentifYangDiterimaBaruNilai(BigDecimal.valueOf(0));
//                result.setInsentifYangDiterimaLamaNilai(BigDecimal.valueOf(0));
//                result.setInsentifYangDiterimaSelisihNilai(BigDecimal.valueOf(0));
//
//                result.setInsentifGajiGolonganNilaiLama(BigDecimal.valueOf(0));
//                result.setInsentifUmkNilaiLama(BigDecimal.valueOf(0));
//                result.setInsentifStrukturalNilaiLama(BigDecimal.valueOf(0));
//                result.setInsentifPeralihanNilaiLama(BigDecimal.valueOf(0));
//                result.setInsentifJabStrukturalNilaiLama(BigDecimal.valueOf(0));
//                result.setInsentifStrategisNilaiLama(BigDecimal.valueOf(0));
//                result.setBrutoInsentifLamaNilai(BigDecimal.valueOf(0));
//                result.setPotonganInsentifIndividuLamaNilai(BigDecimal.valueOf(0));
//                result.setSmkAngkaNilai(BigDecimal.valueOf(Double.valueOf(0)));
//                result.setSmkStandartNilai(BigDecimal.valueOf(Double.valueOf(0)));
//            }
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<PayrollRapelLembur> getDataPayrollRapelLembur(String bulan1, String tahun1, String unit, String status){
//        List<PayrollRapelLembur> listOfResult = new ArrayList<PayrollRapelLembur>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpegawai.point,\n" +
//                "\trapel.rapel_id,\n" +
//                "\tsum(rapelLembur.jam_lembur) as jam_lembur,\n" +
//                "\tsum(rapelLembur.biaya_lembur_lama) as biaya_lembur_lama,\n" +
//                "\trapelLembur.gaji_golongan_baru,\n" +
//                "\trapelLembur.tunjangan_umk_baru,\n" +
//                "\trapelLembur.tunjangan_peralihan_baru,\n" +
//                "\trapelLembur.faktor_pengali,\n" +
//                "\tsum(rapelLembur.biaya_lembur_baru) as biaya_lembur_baru,\n" +
//                "\tsum(rapelLembur.selisih_biaya_lembur_baru) as selisih_biaya_lembur_baru\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_rapel_lembur rapelLembur on rapelLembur.rapel_id = rapel.rapel_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_rapel = 'Y' \n" +
//                "\tand pegawai.status_pegawai = '"+status+"'\n" +
//                "group by\n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpegawai.point,\n" +
//                "\trapel.rapel_id,\n" +
//                "\trapelLembur.gaji_golongan_baru,\n" +
//                "\trapelLembur.tunjangan_umk_baru,\n" +
//                "\trapelLembur.tunjangan_peralihan_baru,\n" +
//                "\trapelLembur.faktor_pengali";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            PayrollRapelLembur result  = new PayrollRapelLembur();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setGolonganName((String) row[3]);
//            result.setStatusKeluarga((String) row[4]);
//            result.setJumlahAnak((Integer) row[5]);
//            result.setPoint((Integer) row[6]);
//            result.setRapelId((String) row[7]);
//
//            if(row[8] != null){
//                result.setJamLembur(Double.valueOf(row[8].toString()));
//                result.setBiayaLemburLama(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//                result.setGajiGolonganBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//                result.setTunjanganUmkBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//                result.setTunjanganPeralihanBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//
//                result.setFaktorPengali(Double.valueOf(row[13].toString()));
//                result.setBiayaLemburBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//                result.setSelisihBiayaLemburBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            }else{
//                result.setJamLembur(Double.valueOf(0));
//                result.setBiayaLemburLama(BigDecimal.valueOf(0));
//                result.setGajiGolonganBaru(BigDecimal.valueOf(0));
//                result.setTunjanganUmkBaru(BigDecimal.valueOf(0));
//                result.setTunjanganPeralihanBaru(BigDecimal.valueOf(0));
//
//                result.setFaktorPengali(Double.valueOf(0));
//                result.setBiayaLemburBaru(BigDecimal.valueOf(0));
//                result.setSelisihBiayaLemburBaru(BigDecimal.valueOf(0));
//            }
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollThr(String bulan1, String tahun1, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tthr.*,\n" +
//                "\tpegawai.point,\n" +
//                "\tpayroll.pph_gaji,\n" +
//                "\tpayroll.gaji_bersih\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_thr thr on thr.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_thr = 'Y'\n" +
//                "\tand thr.thr_id is not null";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setGolonganName((String) row[3]);
//            result.setStatusKeluarga((String) row[4]);
//            result.setJumlahAnak((Integer) row[5]);
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//            result.setBulan((Integer) row[21] + "");
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
//            result.setPoint((Integer) row[24]);
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollJasprod(String bulan1, String tahun1, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpegawai.point,\n" +
//                "\tpayroll.pph_gaji,\n" +
//                "\tpayroll.gaji_bersih,\n" +
//                "\tjasprod.*\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_jasprod jasprod on jasprod.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_jasprod = 'Y'\n" +
//                "\tand jasprod.jasprod_id is not null";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setGolonganName((String) row[3]);
//            result.setStatusKeluarga((String) row[4]);
//            result.setJumlahAnak((Integer) row[5]);
//            result.setPoint((Integer) row[6]);
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setNilaiSmk(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//            result.setMasaKerja((Integer) row[23]);
//            result.setGajiMasaKerja(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
//            result.setFaktorKali(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//            result.setPersenSmk(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
//            result.setGajiMasaKerjaFaktorSmk(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
//            result.setGajiMasaKerjaFaktor(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
//            result.setTambahan(BigDecimal.valueOf(Double.valueOf(row[29].toString())));
//            result.setBrutoJasprod(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
//            result.setSelisihTotalGajiSmkFaktor(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
//            result.setJumlahPersenSmk(Double.valueOf(row[32].toString()));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[33].toString())));
//
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
//            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[36].toString())));
//            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[37].toString())));
//            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[38].toString())));
//            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[39].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollInsentif(String bulan1, String tahun1, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select \n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpegawai.point,\n" +
//                "\tpayroll.pph_gaji,\n" +
//                "\tpayroll.gaji_bersih,\n" +
//                "\tinsentif.*\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join it_hris_payroll_insentif insentif on insentif.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand payroll.bulan = '"+bulan1+"'\n" +
//                "\tand payroll.tahun = '"+tahun1+"'\n" +
//                "\tand payroll.flag_insentif = 'Y'\n" +
//                "\tand payroll.flag = 'Y'\n" +
//                "\tand insentif.insentif_id is not null";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setPayrollId((String) row[0]);
//            result.setNip((String) row[1]);
//            result.setNama((String) row[2]);
//            result.setGolonganName((String) row[3]);
//            result.setStatusKeluarga((String) row[4]);
//            result.setJumlahAnak((Integer) row[5]);
//            result.setPoint((Integer) row[6]);
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//
//            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//            result.setMasaKerja((Integer) row[26]);
//            result.setPotonganInsentif(Double.valueOf(row[27].toString()));
//            result.setPotonganInsentifIndividu(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
//            result.setSmkStandart(Double.valueOf(row[29].toString()));
//            result.setSmkHuruf((String) row[30]);
//            result.setSmkAngka(Double.valueOf(row[31].toString()));
//            result.setTahun((String) row[32]);
//            result.setInsentifDiterima(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
//            result.setPotPphLain(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportDanaPensiunSys(String bulan, String tahun, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.point,\n" +
//                "\tdapen.dana_pensiun,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpayroll.gaji_pensiun as phdp,\n" +
//                "\tpayroll.iuran_pensiun,\n" +
//                "\tCASE \n" +
//                "\t\tWHEN dapen.dana_pensiun_id = 'DP01' THEN payroll.gaji_pensiun * 12 / 100\n" +
//                "\t\tWHEN dapen.dana_pensiun_id = 'DP02' THEN payroll.gaji_pensiun * 16.09 / 100\n" +
//                "\t\tWHEN dapen.dana_pensiun_id = 'DP03' THEN payroll.gaji_pensiun * 13.13 / 100\n" +
//                "\tELSE \n" +
//                "\t\t0 \n" +
//                "\tEND as iuran_perusahaan\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "where\n" +
//                "\tpayroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.dana_pensiun is not null\n" +
//                "\tand pegawai.dana_pensiun != ''\n" +
//                "\tand pegawai.flag = 'Y'\n" +
//                "\tand flag_payroll = 'Y'\n" +
//                "order by\n" +
//                "\tpegawai.dana_pensiun";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            BigDecimal jumlahIuranPensiun = new BigDecimal(0);
//            jumlahIuranPensiun = BigDecimal.valueOf(Double.valueOf(row[8].toString())).add(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setGolonganName((String) row[2]);
//            result.setPoint((Integer) row[3]);
//            result.setDanaPensiunName((String) row[4]);
//            result.setStatusKeluarga((String) row[5]);
//            result.setJumlahAnak((Integer) row[6]);
//            result.setGajiPensiunBpjs(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setIuranPensiunPerusahaan(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setIuranPensiunJumlah(jumlahIuranPensiun);
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportBpjsSys(String bulan, String tahun, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tpayroll.golongan_name,\n" +
//                "\tpayroll.point,\n" +
//                "\tdapen.dana_pensiun,\n" +
//                "\tpayroll.status_keluarga,\n" +
//                "\tpayroll.jumlah_anak,\n" +
//                "\tpayroll.gaji_pensiun_bpjs as gaji_bpjs,\n" +
//                "\tpayroll.iuran_bpjs_tk,\n" +
//                "\tpayroll.iuran_bpjs_pensiun,\n" +
//                "\tpayroll.iuran_bpjs_kesehatan,\n" +
//                "\tpayroll.iuran_bpjs_tk + payroll.iuran_bpjs_pensiun + payroll.iuran_bpjs_kesehatan as jumlah\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "where\n" +
//                "\tpayroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.flag = 'Y'\n" +
//                "\tand flag_payroll = 'Y'\n" +
//                "order by\n" +
//                "\tpegawai.dana_pensiun";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            BigDecimal jumlahIuranPensiun = new BigDecimal(0);
//            jumlahIuranPensiun = BigDecimal.valueOf(Double.valueOf(row[8].toString())).add(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setGolonganName((String) row[2]);
//            result.setPoint((Integer) row[3]);
//            result.setDanaPensiunName((String) row[4]);
//            result.setStatusKeluarga((String) row[5]);
//            result.setJumlahAnak((Integer) row[6]);
//            result.setGajiPensiunBpjs(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setIuranPensiunJumlah(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportRekapGajiSys(String bulan, String tahun, String unit, String statusPegawai,
//                                                          String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian,\n" +
//                "\tcount (posisiPegawai.nip) as jumlah_orang,\n" +
//                "\tsum(payroll.gaji_golongan) as gaji_dasar,\n" +
//                "\tsum(payroll.tunjangan_umk) as tunjangan_umk,\n" +
//                "\tsum(payroll.tunjangan_struktural) as tunjangan_struktural,\n" +
//                "\tsum(payroll.tunjangan_peralihan) as tunjangan_peralihan,\n" +
//                "\tsum(payroll.tunjangan_jabatan_struktural) as tunjangan_jabatan_struktural,\n" +
//                "\tsum(payroll.tunjangan_strategis) as tunjangan_strategis,\n" +
//                "\tsum(payroll.kompensasi) as tunjangan_kompensasi,\n" +
//                "\tsum(payroll.tunjangan_transport) as tunjangan_transport,\n" +
//                "\tsum(payroll.tunjangan_air_listrik) as tunjangan_air_listrik,\n" +
//                "\tsum(payroll.tunjangan_pengobatan) as tunjangan_pengobatan,\n" +
//                "\tsum(payroll.tunjangan_perumahan) as tunjangan_perumahan,\n" +
//                "\tsum(payroll.tunjangan_pph) as tunjangan_pph,\n" +
//                "\tsum(payroll.tunjangan_lembur) as tunjangan_lembur,\n" +
//                "\tsum(payroll.tunjangan_lain) as tunjangan_lain,\n" +
//                "\tsum(payroll.total_a) as penghasilan_kotor\n" +
//                "from\n" +
//                "\tim_position posisi\n" +
//                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.position_id = posisi.position_id\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = posisiPegawai.nip\n" +
//                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
//                "\tleft join it_hris_payroll payroll on payroll.nip = posisiPegawai.nip\n" +
//                "where\n" +
//                "\tposisiPegawai.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
//                "\tand pegawai.flag ='Y'\n" +
//                "\tand posisiPegawai.flag ='Y'\n" +
//                "\tand payroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.flag_payroll = 'Y'\n" +
//                "\tand " + strWhere +
//                "\ngroup by\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian\n" +
//                "\torder by \n posisi.bagian_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNamaBagian((String) row[1]);
//            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setTunjanganLain(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    //update posisi mengambil di tabel payroll, bukan tabel it+_pegawai_position
//    public List<ItPayrollEntity> searchReportRekapGajiSysV2(String bulan, String tahun, String unit, String statusPegawai,
//                                                          String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian,\n" +
//                "\tcount (payroll.nip) as jumlah_orang,\n" +
//                "\tsum(payroll.gaji_golongan) as gaji_dasar,\n" +
//                "\tsum(payroll.tunjangan_umk) as tunjangan_umk,\n" +
//                "\tsum(payroll.tunjangan_struktural) as tunjangan_struktural,\n" +
//                "\tsum(payroll.tunjangan_peralihan) as tunjangan_peralihan,\n" +
//                "\tsum(payroll.tunjangan_jabatan_struktural) as tunjangan_jabatan_struktural,\n" +
//                "\tsum(payroll.tunjangan_strategis) as tunjangan_strategis,\n" +
//                "\tsum(payroll.kompensasi) as tunjangan_kompensasi,\n" +
//                "\tsum(payroll.tunjangan_transport) as tunjangan_transport,\n" +
//                "\tsum(payroll.tunjangan_air_listrik) as tunjangan_air_listrik,\n" +
//                "\tsum(payroll.tunjangan_pengobatan) as tunjangan_pengobatan,\n" +
//                "\tsum(payroll.tunjangan_perumahan) as tunjangan_perumahan,\n" +
//                "\tsum(payroll.tunjangan_pph) as tunjangan_pph,\n" +
//                "\tsum(payroll.tunjangan_lembur) as tunjangan_lembur,\n" +
//                "\tsum(payroll.tunjangan_lain) as tunjangan_lain,\n" +
//                "\tsum(payroll.total_a) as penghasilan_kotor\n" +
//                "from\n" +
//                "\tim_position posisi\n" +
//                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
//                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
//                "\tand pegawai.flag ='Y'\n" +
//                "\tand payroll.flag_payroll ='Y'\n" +
//                "\tand payroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.flag_payroll = 'Y'\n" +
//                "\tand " + strWhere +
//                "group by\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian\n" +
//                "\torder by \n" +
//                " posisi.bagian_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNamaBagian((String) row[1]);
//            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setTunjanganLain(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportRekapThr(String bulan, String tahun, String unit, String statusPegawai,
//                                                          String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian,\n" +
//                "\tcount (payroll.nip) as jumlah_orang,\n" +
//                "\tsum(thr.gaji_golongan) as gaji_dasar,\n" +
//                "\tsum(thr.tunjangan_umk) as tunjangan_umk,\n" +
//                "\tsum(thr.tunjangan_struktural) as tunjangan_struktural,\n" +
//                "\tsum(thr.tunjangan_peralihan) as tunjangan_peralihan,\n" +
//                "\tsum(thr.tunjangan_jabatan_struktural) as tunjangan_jabatan_struktural,\n" +
//                "\tsum(thr.tunjangan_strategis) as tunjangan_strategis,\n" +
//                "\tsum(thr.tunjangan_pph) as tunjangan_pph,\n" +
//                "\tsum(payroll.pph_gaji) as pot_pph,\n" +
//                "\tsum(thr.thr_bersih) as penghasilan_kotor,\n" +
//                "\tsum(payroll.gaji_bersih) as thr_bersih\n" +
//                "from\n" +
//                "\tim_position posisi\n" +
//                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
//                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
//                "\tleft join it_hris_payroll_thr thr on thr.payroll_id = payroll.payroll_id\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
//                "\tand pegawai.flag ='Y'\n" +
//                "\tand payroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.flag_thr = 'Y'\n" +
//                "\tand  " + strWhere +
//                "group by\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian\n" +
//                "\torder by\n" +
//                " posisi.bagian_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNamaBagian((String) row[1]);
//            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setPotPph(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportRekapJasprod(String bulan, String tahun, String unit, String statusPegawai,
//                                                          String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian,\n" +
//                "\tcount (payroll.nip) as jumlah_orang,\n" +
//                "\tsum(jasprod.gaji_golongan) as gaji_dasar,\n" +
//                "\tsum(jasprod.tunjangan_umk) as tunjangan_umk,\n" +
//                "\tsum(jasprod.tunjangan_struktural) as tunjangan_struktural,\n" +
//                "\tsum(jasprod.tunjangan_peralihan) as tunjangan_peralihan,\n" +
//                "\tsum(jasprod.tunjangan_jabatan_struktural) as tunjangan_jabatan_struktural,\n" +
//                "\tsum(jasprod.tunjangan_strategis) as tunjangan_strategis,\n" +
//                "\tsum(jasprod.gaji_bruto) as gaji_bruto,\n" +
//                "\tsum(jasprod.gaji_masa_kerja) as gaji_masaKerja,\n" +
//                "\tsum(jasprod.nilai_smk) as nilai_smk,\n" +
//                "\tsum(jasprod.gaji_masa_kerja_faktor_smk) as perhitungan,\n" +
//                "\tsum(jasprod.gaji_masa_kerja_faktor) as gaji_faktorNormal,\n" +
//                "\tsum(jasprod.tambahan) as tambahan,\n" +
//                "\tsum(jasprod.bruto) as bruto,\n" +
//                "\tsum(jasprod.pph_jasprod) as pot_pph,\n" +
//                "\tsum(jasprod.pot_koperasi) as pot_koperasi,\n" +
//                "\tsum(jasprod.pot_taliasih) as pot_taliasih,\n" +
//                "\tsum(jasprod.pot_lain) as pot_lain,\n" +
//                "\tsum(jasprod.final_jasprod) as final_jasprod\n" +
//                "from\n" +
//                "\tim_position posisi\n" +
//                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
//                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
//                "\tleft join it_hris_payroll_jasprod jasprod on jasprod.payroll_id = payroll.payroll_id\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
//                "\tand payroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.flag_jasprod = 'Y'\n" +
//                "group by\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian\n" +
//                "\torder by\n" +
//                " posisi.bagian_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNamaBagian((String) row[1]);
//            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setGajiMasaKerja(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setNilaiSmk(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//            result.setGajiMasaKerjaFaktorSmk(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setGajiMasaKerjaFaktor(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//            result.setTambahan(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//            result.setBrutoJasprod(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setPotPph(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportRekapInsentif(String bulan, String tahun, String unit, String statusPegawai,
//                                                          String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian,\n" +
//                "\tcount (payroll.nip) as jumlah_orang,\n" +
//                "\tsum(insentif.gaji_golongan) as gaji_dasar,\n" +
//                "\tsum(insentif.tunjangan_umk) as tunjangan_umk,\n" +
//                "\tsum(insentif.tunjangan_struktural) as tunjangan_struktural,\n" +
//                "\tsum(insentif.tunjangan_peralihan) as tunjangan_peralihan,\n" +
//                "\tsum(insentif.tunjangan_jab_struktural) as tunjangan_jabatan_struktural,\n" +
//                "\tsum(insentif.tunjangan_strategis) as tunjangan_strategis,\n" +
//                "\tsum(insentif.jumlah_bruto) as gaji_bruto,\n" +
//                "\tsum(insentif.potongan_insentif_individu) as pot_insentif_individu,\n" +
//                "\tsum(insentif.insentif_yang_diterima) as insentif_diterima,\n" +
//                "\tsum(insentif.jumlah_pph) as jumlah_pph,\n" +
//                "\tsum(insentif.jumlah_insentif) as netto\n" +
//                "from\n" +
//                "\tim_position posisi\n" +
//                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
//                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
//                "\tleft join it_hris_payroll_insentif insentif on insentif.payroll_id = payroll.payroll_id\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
//                "\tand payroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.flag_insentif = 'Y'\n" +
//                "\tand payroll.flag = 'Y'\n" +
//                "\tand posisi.bagian_id not in ('PB023', 'PB010')\n" +
//                "group by\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian\n" +
//                "\torder by\n" +
//                " posisi.bagian_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNamaBagian((String) row[1]);
//            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//
//            result.setPotonganInsentifIndividu(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setInsentifDiterima(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//            result.setJumlahPph(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportRekapPotonganSys(String bulan, String tahun, String unit, String statusPegawai,
//                                                              String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian,\n" +
//                "\tcount (posisiPegawai.nip) as jumlah_orang,\n" +
//                "\tsum(payroll.pph_gaji) as pph_gaji,\n" +
//                "\tsum(payroll.pph_pengobatan) as pph_pengobatan,\n" +
//                "\tsum(payroll.pph_gaji + payroll.pph_pengobatan) as jumlah_pph,\n" +
//                "\tsum(payroll.iuran_pensiun) as iuran_pensiun,\n" +
//                "\tsum(payroll.iuran_bpjs_tk) as Astek,\n" +
//                "\tsum(payroll.iuran_bpjs_kesehatan) as Kesehatan,\n" +
//                "\tsum(payroll.iuran_bpjs_pensiun) as Pensiun,\n" +
//                "\tsum(payroll.uang_muka_lainnya) as um_lainlain,\n" +
//                "\tsum(payroll.kekurangan_bpjs_tk) as Kekurangan_bpjs,\n" +
//                "\tsum(total_b) as J_pot_dinas,\n" +
//                "\tsum(payroll.pengobatan) as Biaya_obat,\n" +
//                "\tsum(payroll.koperasi) as koperasi,\n" +
//                "\tsum(payroll.dansos) as dansos,\n" +
//                "\tsum(payroll.sp) as sp,\n" +
//                "\tsum(payroll.bazis) as bazis,\n" +
//                "\tsum(payroll.bapor) as bapor,\n" +
//                "\tsum(payroll.lain_lain) as lain_lain,\n" +
//                "\tsum(payroll.total_c) as jumlah_pot_lain,\n" +
//                "\tsum(payroll.gaji_bersih) as jumlah_bersih\n" +
//                "from\n" +
//                "\tim_position posisi\n" +
//                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.position_id = posisi.position_id\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = posisiPegawai.nip\n" +
//                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
//                "\tleft join it_hris_payroll payroll on payroll.nip = posisiPegawai.nip\n" +
//                "where\n" +
//                "\tposisiPegawai.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
//                "\tand pegawai.flag ='Y'\n" +
//                "\tand posisiPegawai.flag ='Y'\n" +
//                "\tand payroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.flag_payroll = 'Y'\n" +
//                "\tand " + strWhere +
//                "\ngroup by\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian\n" +
//                "order by\n" +
//                "\tposisi.bagian_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNamaBagian((String) row[1]);
//            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setJumlahPph(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setIuranPensiun(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setUangMukaLainnya(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setKekuranganBpjsTk(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//            result.setTotalB(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setPengobatan(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setSP(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//            result.setBazis(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//            result.setBapor(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//            result.setTotalC(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    //rekap potongan v2, mengambil posisi pegawai melalui tabel it_payroll bukan melalui table it_biodata dan it_pegawai_position
//    public List<ItPayrollEntity> searchReportRekapPotonganSysV2(String bulan, String tahun, String unit, String statusPegawai,
//                                                              String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian,\n" +
//                "\tcount (payroll.nip) as jumlah_orang,\n" +
//                "\tsum(payroll.pph_gaji) as pph_gaji,\n" +
//                "\tsum(payroll.pph_pengobatan) as pph_pengobatan,\n" +
//                "\tsum(payroll.pph_gaji + payroll.pph_pengobatan) as jumlah_pph,\n" +
//                "\tsum(payroll.iuran_pensiun) as iuran_pensiun,\n" +
//                "\tsum(payroll.iuran_bpjs_tk) as Astek,\n" +
//                "\tsum(payroll.iuran_bpjs_kesehatan) as Kesehatan,\n" +
//                "\tsum(payroll.iuran_bpjs_pensiun) as Pensiun,\n" +
//                "\tsum(payroll.uang_muka_lainnya) as um_lainlain,\n" +
//                "\tsum(payroll.kekurangan_bpjs_tk) as Kekurangan_bpjs,\n" +
//                "\tsum(total_b) as J_pot_dinas,\n" +
//                "\tsum(payroll.pengobatan) as Biaya_obat,\n" +
//                "\tsum(payroll.koperasi) as koperasi,\n" +
//                "\tsum(payroll.dansos) as dansos,\n" +
//                "\tsum(payroll.sp) as sp,\n" +
//                "\tsum(payroll.bazis) as bazis,\n" +
//                "\tsum(payroll.bapor) as bapor,\n" +
//                "\tsum(payroll.lain_lain) as lain_lain,\n" +
//                "\tsum(payroll.total_c) as jumlah_pot_lain,\n" +
//                "\tsum(payroll.gaji_bersih) as jumlah_bersih\n" +
//                "from\n" +
//                "\tim_position posisi\n" +
//                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
//                "where\n" +
//                "\tpayroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
//                "\tand pegawai.flag ='Y'\n" +
//                "\tand payroll.flag_payroll ='Y'\n" +
//                "\tand payroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.flag_payroll = 'Y'\n" +
//                "\tand " + strWhere +
//                "group by\n" +
//                "\tposisi.bagian_id,\n" +
//                "\tbagian.nama_bagian\n" +
//                "order by\n" +
//                "\tposisi.bagian_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNamaBagian((String) row[1]);
//            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setJumlahPph(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setIuranPensiun(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setUangMukaLainnya(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setKekuranganBpjsTk(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//            result.setTotalB(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setPengobatan(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setSP(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//            result.setBazis(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//            result.setBapor(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//            result.setTotalC(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportTransferGajiSys(String bulan, String tahun, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.nama_bank is null then '-'\n" +
//                "\t\twhen pegawai.nama_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.nama_bank\n" +
//                "\tend as nama_bank ,\n" +
//                "\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.cabang_bank is null then '-'\n" +
//                "\t\twhen pegawai.cabang_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.cabang_bank\n" +
//                "\tend as cabang_bank ,\n" +
//                "\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.no_rek_bank is null then '-'\n" +
//                "\t\twhen pegawai.no_rek_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.no_rek_bank\n" +
//                "\tend as no_rek_bank ,\n" +
//                "\t\n" +
//                "\tpayroll.gaji_bersih\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "where\n" +
//                "\tpayroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.flag = 'Y'\n" +
//                "\tand payroll.flag_payroll = 'Y'\n" +
//                "order by\n" +
//                "\tpegawai.nama_bank";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setNamaBank((String) row[2]);
//            result.setCabangBank((String) row[3]);
//            result.setNoRek((String) row[4]);
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportTransferPendidikanSys(String bulan, String tahun, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.nama_bank is null then '-'\n" +
//                "\t\twhen pegawai.nama_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.nama_bank\n" +
//                "\tend as nama_bank ,\n" +
//                "\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.cabang_bank is null then '-'\n" +
//                "\t\twhen pegawai.cabang_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.cabang_bank\n" +
//                "\tend as cabang_bank ,\n" +
//                "\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.no_rek_bank is null then '-'\n" +
//                "\t\twhen pegawai.no_rek_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.no_rek_bank\n" +
//                "\tend as no_rek_bank ,\n" +
//                "\t\n" +
//                "\tpayroll.gaji_bersih\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "where\n" +
//                "\tpayroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.flag = 'Y'\n" +
//                "\tand payroll.flag_pendidikan = 'Y'\n" +
//                "order by\n" +
//                "\tpegawai.nama_bank";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setNamaBank((String) row[2]);
//            result.setCabangBank((String) row[3]);
//            result.setNoRek((String) row[4]);
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportTransferThrSys(String bulan, String tahun, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.nama_bank is null then '-'\n" +
//                "\t\twhen pegawai.nama_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.nama_bank\n" +
//                "\tend as nama_bank ,\n" +
//                "\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.cabang_bank is null then '-'\n" +
//                "\t\twhen pegawai.cabang_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.cabang_bank\n" +
//                "\tend as cabang_bank ,\n" +
//                "\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.no_rek_bank is null then '-'\n" +
//                "\t\twhen pegawai.no_rek_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.no_rek_bank\n" +
//                "\tend as no_rek_bank ,\n" +
//                "\t\n" +
//                "\tpayroll.gaji_bersih\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "where\n" +
//                "\tpayroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.flag = 'Y'\n" +
//                "\tand payroll.flag_thr = 'Y'\n" +
//                "order by\n" +
//                "\tpegawai.nama_bank";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setNamaBank((String) row[2]);
//            result.setCabangBank((String) row[3]);
//            result.setNoRek((String) row[4]);
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> searchReportTransferJasprodSys(String bulan, String tahun, String unit){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.nama,\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.nama_bank is null then '-'\n" +
//                "\t\twhen pegawai.nama_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.nama_bank\n" +
//                "\tend as nama_bank ,\n" +
//                "\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.cabang_bank is null then '-'\n" +
//                "\t\twhen pegawai.cabang_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.cabang_bank\n" +
//                "\tend as cabang_bank ,\n" +
//                "\n" +
//                "\tcase \n" +
//                "\t\twhen pegawai.no_rek_bank is null then '-'\n" +
//                "\t\twhen pegawai.no_rek_bank = '' then '-'\n" +
//                "\telse\n" +
//                "\t\tpegawai.no_rek_bank\n" +
//                "\tend as no_rek_bank ,\n" +
//                "\t\n" +
//                "\tpayroll.gaji_bersih\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
//                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "where\n" +
//                "\tpayroll.bulan = '"+bulan+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.branch_id = '"+unit+"'\n" +
//                "\tand pegawai.flag = 'Y'\n" +
//                "\tand payroll.flag_jasprod = 'Y'\n" +
//                "order by\n" +
//                "\tpegawai.nama_bank";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setNamaBank((String) row[2]);
//            result.setCabangBank((String) row[3]);
//            result.setNoRek((String) row[4]);
//            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getJumlahKotor(String nip, String bulan, String tahun, String branchId){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\t\n" +
//                "payroll.payroll_id,\n" +
//                "\tpayroll.bulan,\n" +
//                "\tpayroll.tahun,\n" +
//                "\tpayroll.total_a as gaji,\n" +
//                "\tpayroll.pph_gaji as pph,\n" +
//                "\tpayroll.total_thr,\n" +
//                "\tpayroll.total_pendidikan,\n" +
//                "\tpayroll.total_jasprod,\n" +
//                "\tpayroll.total_insentif,\n" +
//                "\tpayroll.flag_thr,\n" +
//                "\tpayroll.flag_pendidikan,\n" +
//                "\tpayroll.flag_jasprod,\n " +
//                "\tpayroll.tunjangan_baju_dinas,\n" +
//                "\tpayroll.flag_payroll,\n" +
//                "\tpayroll.flag_insentif,\n" +
//                "\tpayroll.pph_pengobatan," +
//                "\tpayroll.flag_jubileum,\n" +
//                "\tpayroll.flag_rapel,\n" +
//                "\tpayroll.total_jubileum,\n" +
//                "\tpayroll.flag_rapel,\n" +
//                "\tjubileum.pph_jubileum,\n" +
//                "\tpayroll.total_rapel,\n" +
//                "\tpayroll.tunjangan_pengobatan,\n" +
//                "\tpayroll.pengobatan,\n" +
//                "\tcase when rapel.total_rapel_bulan is null then 0 else rapel.total_rapel_bulan end\n," +
//                "\tcase when rapel.total_rapel_thr is null then 0 else rapel.total_rapel_thr end ,\n" +
//                "\tcase when rapel.total_rapel_pendidikan is null then 0 else rapel.total_rapel_pendidikan end ,\n" +
//                "\tcase when rapel.total_rapel_insentif is null then 0 else rapel.total_rapel_insentif end ,\n" +
//                "\tcase when rapel.total_rapel_jubileum is null then 0 else rapel.total_rapel_jubileum end,\n" +
//                "\tcase when rapel.pph_rapel is null then 0 else rapel.pph_rapel end,\n" +
//                "\tcase when rapel.pph_rapel_pribadi is null then 0 else rapel.pph_rapel_pribadi end,\n" +
//                "\tcase when rapel.tunjangan_pph is null then 0 else rapel.tunjangan_pph end\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.bulan "+bulan+" \n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.nip = '"+nip+"'\n" +
//                "\tand payroll.branch_id = '"+branchId+"'\n" +
//                "\tand payroll.flag_pensiun = 'N'\n" +
//                "\t--and payroll.flag_rapel = 'N'\n" +
//                "\tand payroll.flag_jubileum = 'N'" ;
//
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setPayrollId((String) row[0]);
//            result.setBulan((String) row[1]);
//            result.setTahun((String) row[2]);
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTotalPendidikan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setFlagThr((String) row[9]);
//            result.setFlagPendidikan((String) row[10]);
//            result.setFlagJasprod((String) row[11]);
//            result.setTunjanganBajuDinas(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setFlagPayroll((String) row[13]);
//            result.setFlagInsentif((String) row[14]);
//            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setFlagJubileum((String) row[16]);
//            result.setFlagRapel((String) row[17]);
//            if(row[18] != null){
//                result.setTotalJubileum(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            }else{
//                result.setTotalJubileum(BigDecimal.valueOf(Double.valueOf(0)));
//            }
//            result.setTotalRapel(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//            result.setPengobatan(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
//            result.setTotalRapelBulan(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
//            result.setTotalRapelThr(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//            result.setTotalRapelPendidikan(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
//            result.setTotalRapelInsentif(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
//            result.setTotalRapelJubileum(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
//            result.setPphGajiRapel(BigDecimal.valueOf(Double.valueOf(row[29].toString())));
//            result.setPphGajiRapelPribadi(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
//            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
//            listOfResult.add(result);
//        }
//
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getJumlahKotorKabid(String nip, String bulan, String tahun, String branchId){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\t\n" +
//                "payroll.payroll_id,\n" +
//                "\tpayroll.bulan,\n" +
//                "\tpayroll.tahun,\n" +
//                "\tpayroll.total_a as gaji,\n" +
//                "\tpayroll.pph_gaji as pph,\n" +
//                "\tpayroll.total_thr,\n" +
//                "\tpayroll.total_pendidikan,\n" +
//                "\tpayroll.total_jasprod,\n" +
//                "\tpayroll.total_insentif,\n" +
//                "\tpayroll.flag_thr,\n" +
//                "\tpayroll.flag_pendidikan,\n" +
//                "\tpayroll.flag_jasprod,\n " +
//                "\tpayroll.tunjangan_baju_dinas,\n" +
//                "\tpayroll.flag_payroll,\n" +
//                "\tpayroll.flag_insentif,\n" +
//                "\tpayroll.pph_pengobatan," +
//                "\tpayroll.flag_jubileum,\n" +
//                "\tpayroll.flag_rapel,\n" +
//                "\tpayroll.total_jubileum,\n" +
//                "\tpayroll.total_rapel,\n" +
//                "\tjubileum.pph_jubileum\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.bulan "+bulan+" \n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.nip = '"+nip+"'\n" +
//                "\tand payroll.branch_id = '"+branchId+"'\n" +
//                "\tand payroll.flag_pensiun = 'N'\n" +
//                "\tand payroll.flag_insentif = 'N'\n" +
//                "\tand payroll.flag_jasprod = 'N'\n" +
//                "\tand payroll.flag_jubileum = 'N'" ;
//
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setPayrollId((String) row[0]);
//            result.setBulan((String) row[1]);
//            result.setTahun((String) row[2]);
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTotalPendidikan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setFlagThr((String) row[9]);
//            result.setFlagPendidikan((String) row[10]);
//            result.setFlagJasprod((String) row[11]);
//            result.setTunjanganBajuDinas(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setFlagPayroll((String) row[13]);
//            result.setFlagInsentif((String) row[14]);
//            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setFlagJubileum((String) row[16]);
//            result.setFlagRapel((String) row[17]);
//            if(row[18] != null){
//                result.setPphJubileum(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            }else{
//                result.setPphJubileum(BigDecimal.valueOf(Double.valueOf(0)));
//            }
//            listOfResult.add(result);
//        }
//
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getJumlahKotorKabidTanpaPayroll(String nip, String bulan, String tahun, String branchId){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\t\n" +
//                "payroll.payroll_id,\n" +
//                "\tpayroll.bulan,\n" +
//                "\tpayroll.tahun,\n" +
//                "\tpayroll.total_a as gaji,\n" +
//                "\tpayroll.pph_gaji as pph,\n" +
//                "\tpayroll.total_thr,\n" +
//                "\tpayroll.total_pendidikan,\n" +
//                "\tpayroll.total_jasprod,\n" +
//                "\tpayroll.total_insentif,\n" +
//                "\tpayroll.flag_thr,\n" +
//                "\tpayroll.flag_pendidikan,\n" +
//                "\tpayroll.flag_jasprod,\n " +
//                "\tpayroll.tunjangan_baju_dinas,\n" +
//                "\tpayroll.flag_payroll,\n" +
//                "\tpayroll.flag_insentif,\n" +
//                "\tpayroll.pph_pengobatan," +
//                "\tpayroll.flag_jubileum,\n" +
//                "\tpayroll.flag_rapel,\n" +
//                "\tpayroll.total_jubileum,\n" +
//                "\tpayroll.total_rapel,\n" +
//                "\tjubileum.pph_jubileum\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.bulan "+bulan+" \n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.nip = '"+nip+"'\n" +
//                "\tand payroll.branch_id = '"+branchId+"'\n" +
//                "\tand payroll.flag_payroll = 'N'\n" +
//                "\tand payroll.flag_pendidikan = 'N'\n" +
//                "\tand payroll.flag_thr = 'N'";
//
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setPayrollId((String) row[0]);
//            result.setBulan((String) row[1]);
//            result.setTahun((String) row[2]);
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            if(row[4] != null){
//                result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            }else{
//                result.setPphGaji(BigDecimal.valueOf(Double.valueOf(0)));
//            }
//            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTotalPendidikan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setFlagThr((String) row[9]);
//            result.setFlagPendidikan((String) row[10]);
//            result.setFlagJasprod((String) row[11]);
//            result.setTunjanganBajuDinas(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setFlagPayroll((String) row[13]);
//            result.setFlagInsentif((String) row[14]);
//            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setFlagJubileum((String) row[16]);
//            result.setFlagRapel((String) row[17]);
//            if(row[18] != null){
//                result.setPphJubileum(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            }else{
//                result.setPphJubileum(BigDecimal.valueOf(Double.valueOf(0)));
//            }
//            listOfResult.add(result);
//        }
//
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getTunjanganAsumsi(String nip, String bulan, String tahun, String branchId){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\t\n" +
//                "payroll.payroll_id,\n" +
//                "\tpayroll.bulan,\n" +
//                "\tpayroll.tahun,\n" +
//                "\tpph.asumsi_thr,\n" +
//                "\tpph.asumsi_pendidikan,\n" +
//                "\tpph.asumsi_jasprod,\n" +
//                "\tpph.pph_id\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.bulan = '"+bulan+"' \n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.nip = '"+nip+"'\n" +
//                "\tand payroll.branch_id = '"+branchId+"'";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setPayrollId((String) row[0]);
//            result.setBulan((String) row[1]);
//            result.setTahun((String) row[2]);
//            result.setAsumsiThr(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setAsumsiPendidikan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setAsumsiJasprod(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setPphId((String) row[6]);
//
//            listOfResult.add(result);
//        }
//
//        return listOfResult;
//    }
//
//    // cek berapa bulan pernah menjabat bukan sebagain direktur dan komisaris di tahun tertentu
//    public List<ItPayrollEntity> kalkulasiBulanBukanDirektur(String nip, String tahun, String strBulan){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        if(!strBulan.equalsIgnoreCase("")){
//            strBulan = " and payroll.bulan in " + strBulan + "\n";
//        }
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.payroll_id,\n" +
//                "\tpayroll.gaji_golongan,\n" +
//                "\tpayroll.tunjangan_umk,\n" +
//                "\tpayroll.tunjangan_struktural,\n" +
//                "\tpayroll.tunjangan_peralihan,\n" +
//                "\tpayroll.tunjangan_jabatan_struktural,\n" +
//                "\tpayroll.tunjangan_strategis,\n" +
//                "\tpayroll.kompensasi,\n" +
//                "\tpayroll.tunjangan_transport,\n" +
//                "\tpayroll.tunjangan_air_listrik,\n" +
//                "\tpayroll.tunjangan_perumahan,\n" +
//                "\tposisi.kelompok_id\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join im_position posisi on posisi.position_id = payroll.position_id\n" +
//                "where\n" +
//                "\tpayroll.nip ='"+nip+"'\n" +
//                "\tand payroll.flag_payroll = 'Y'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" + strBulan +
//                "\tand posisi.kelompok_id NOT IN ('KL00', 'KL01')";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setPayrollId((String) row[0]);
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[1].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[2].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setKelompokId((String) row[11]);
//            listOfResult.add(result);
//        }
//
//        return listOfResult;
//    }

    /*// digunakan oleh THR
    // cek berapa bulan menjabat bukan
    public List<ItPayrollEntity> kalkulasiBulanBukanDirektur(String nip, String tahun){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.gaji_golongan,\n" +
                "\tpayroll.tunjangan_umk,\n" +
                "\tpayroll.tunjangan_struktural,\n" +
                "\tpayroll.tunjangan_peralihan,\n" +
                "\tpayroll.tunjangan_jabatan_struktural,\n" +
                "\tpayroll.tunjangan_strategis,\n" +
                "\tpayroll.kompensasi,\n" +
                "\tpayroll.tunjangan_transport,\n" +
                "\tpayroll.tunjangan_air_listrik,\n" +
                "\tpayroll.tunjangan_perumahan,\n" +
                "\tposisi.kelompok_id\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_position posisi on posisi.position_id = payroll.position_id\n" +
                "where\n" +
                "\tpayroll.nip ='"+nip+"'\n" +
                "\tand payroll.flag_payroll = 'Y'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand posisi.kelompok_id NOT IN ('KL00', 'KL01')";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPayrollId((String) row[0]);
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[1].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[2].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setKelompokId((String) row[11]);
            listOfResult.add(result);
        }

        return listOfResult;
    } */

    //uncomment by ferdi, 01 des 2020

//    public List<Payroll> getPphDetail(String tahun, String nip){
//        List<Payroll> listOfResult = new ArrayList<Payroll>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tpayroll.nip,\n" +
//                "\tpayroll.bulan,\n" +
//                "\tpayroll.tahun,\n" +
//                "\tpayroll.flag_payroll,\n" +
//                "\tpayroll.flag_thr,\n" +
//                "\tpayroll.flag_pendidikan,\n" +
//                "\tpayroll.flag_insentif,\n" +
//                "\tpayroll.flag_jasprod,\n" +
//                "\tpayroll.flag_rapel,\n" +
//                "\tpayroll.total_a,\n" +
//                "\tpayroll.pph_gaji,\n" +
//                "\tpph.pph_gaji,\n" +
//                "\tpayroll.total_insentif,\n" +
//                "\tpayroll.total_pendidikan,\n" +
//                "\tpayroll.total_thr,\n" +
//                "\tpayroll.total_jasprod,\n" +
//                "\tpayroll.total_rapel,\n" +
//                "\tpayroll.pph_pengobatan,\n" +
//                "\tpayroll.flag_jubileum,\n" +
//                "\tpayroll.total_jubileum,\n" +
//                "\tjubileum.pph_jubileum\n" +
//                "from\n" +
//                "\tit_hris_payroll payroll\n" +
//                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
//                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
//                "where\n" +
//                "\tpayroll.nip = '"+nip+"'\n" +
//                "\tand payroll.tahun = '"+tahun+"'\n" +
//                "\tand payroll.flag = 'Y'\n" +
//                "order by\n" +
//                "\tpayroll.flag_payroll desc,\n" +
//                "\tpayroll.bulan asc";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            Payroll result  = new Payroll();
//            result.setNip((String) row[0]);
//            result.setBulan((String) row[1]);
//            result.setTahun((String) row[2]);
//            result.setFlagPayroll((String) row[3]);
//            result.setFlagThr((String) row[4]);
//            result.setFlagPendidikan((String) row[5]);
//            result.setFlagInsentif((String) row[6]);
//            result.setFlagJasprod((String) row[7]);
//            result.setFlagRapel((String) row[8]);
//
//            result.setTotalANilai(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            if(row[10] != null){
//                result.setPphGajiNilai(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            }else{
//                result.setPphGajiNilai(BigDecimal.valueOf(0));
//            }
//            result.setTotalInsentifNilai(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setTotalPendidikanNilai(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//            result.setTotalThrNilai(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//            result.setTotalJasProdNilai(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setTotalRapelNilai(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//            result.setPphPengobatanNilai(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//
//            result.setFlagJubileum((String) row[18]);
//            result.setTotalJubileumNilai(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//
//            if(row[20] != null){
//                result.setPotPphNilai(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//            }else{
//                result.setPotPphNilai(BigDecimal.valueOf(0));
//            }
//
//            listOfResult.add(result);
//        }
//
//        return listOfResult;
//    }
//
//    // digunakan oleh AddPayroll.jsp
//    // untuk mengambil data insentif yang sudah dibayar pada bulan dan tahun insentif
//    public List<Payroll> cekTunjanganInsentif(int bulanMulai, int tahun, String branchId){
//        List<Payroll> listOfResult = new ArrayList<Payroll>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tinsentif.bulan_mulai,\n" +
//                "\tinsentif.bulan_sampai,\n" +
//                "\tinsentif.tahun_insentif,\n" +
//                "\tpayroll.branch_id\n" +
//                "from\n" +
//                "\tit_hris_payroll_insentif insentif\n" +
//                "\tleft join it_hris_payroll payroll on payroll.payroll_id = insentif.payroll_id\n" +
//                "where\n" +
//                "\tinsentif.tahun_insentif ='"+tahun+"'\n" +
//                "\tand payroll.flag = 'Y'\n" +
//                "\tand payroll.branch_id = '"+branchId+"'\n" +
//                "\tand insentif.bulan_sampai > '"+bulanMulai+"'\n" +
//                "group by\n" +
//                "\tinsentif.bulan_mulai,\n" +
//                "\tinsentif.bulan_sampai,\n" +
//                "\tinsentif.tahun_insentif,\n" +
//                "\tpayroll.branch_id";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            Payroll result  = new Payroll();
//            result.setBulanInsentifMulai((int) row[0]);
//            result.setBulanInsentifSampai((int) row[1]);
//            result.setInsentifTahun((int) row[2]);
//
//            listOfResult.add(result);
//        }
//
//        return listOfResult;
//    }
    //RAKA-30APR2021==> UNCOMMENT
    public List<ItHrisPayrollEntity> getTunjanganPeralihanForAbsensi(String nip, String tahun) throws HibernateException {
        List<ItHrisPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tipePayroll", "PY"))
                .addOrder(Order.desc("bulan"))
                .setMaxResults(1)
                .list();

        return results;
    }
//
//    // pengobatan selama 1 tahun
//    public List<PayrollPph> getJumlahPengobatan(String tahun, String nip){
//        List<PayrollPph> listOfResult = new ArrayList<PayrollPph>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "select\n" +
//                "\tcase when sum(pengobatan) is null then 0 else sum(pengobatan) end as jumlah_pengobatan,\n" +
//                "\tcase when sum(pph_pengobatan) is null then 0 else sum(pph_pengobatan) end as pph_pengobatan\n" +
//                "from\n" +
//                "\tit_hris_payroll\n" +
//                "where\n" +
//                "\tnip = '"+nip+"'\n" +
//                "\tand tahun = '"+tahun+"'\n" +
//                "\tand flag_payroll = 'Y'\n" +
//                "\tand approval_flag = 'Y'\n";
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            PayrollPph result  = new PayrollPph();
//            result.setJumlahPengobatanNilai(BigDecimal.valueOf(Double.valueOf(row[0].toString())));
//            result.setJumlahPphPengobatanNilai(BigDecimal.valueOf(Double.valueOf(row[1].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
    //RAKA-04MEI2021 ==> recover by raka (penyesuaian perlu dicek)
    public List<PayrollEsptDTO> searchReportEspt(String tahun, String unit) {
        List<PayrollEsptDTO> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        final String query = "SELECT\n" +
                "                MIN(p.bulan) AS masaperolehanawal, \n" +
                "                MAX(p.bulan) AS masaperolehanakhir, \n" +
                "                peg.npwp AS npwp, \n" +
                "                peg.no_ktp AS noktp, \n" +
                "                peg.nama_pegawai AS nama, \n" +
                "                peg.alamat AS alamat, \n" +
                "                peg.jenis_kelamin AS jk, \n" +
                "                peg.status_keluarga AS status, \n" +
                "                peg.jumlah_anak AS jumlahanak, \n" +
                "                pos.position_name AS namajabatan, \n" +
                "                sum(p.gaji_pokok) as jumlah1gaji, \n" +
                "                sum(p.tunjangan_pph) as jumlah2tunjpph, \n" +
                "                sum(p.tunjangan_jabatan_struktural+p.tunjangan_struktural+p.tunjangan_peralihan+p.tunjangan_lain+p.tunjangan_tambahan+p.tunjangan_lembur+p.tunjangan_pemondokan+p.tunjangan_komunikasi+p.total_rlab+p.tunjangan_dapen+p.tunjangan_bpjs_ks+p.tunjangan_bpjs_tk) as jumlah3tunjanganlemburlainnya, \n" +
                "                sum(p.iuran_dapen_kary+p.iuran_bpjs_tk_kary+p.iuran_bpjs_ks_kary) as jumlah10iuranpensiunthtjht, \n" +
                "                p.nip,  \n" +
                "                pph.ptkp  \n" +
                "                from \n" +
                "                it_hris_payroll p \n" +
                "                left join it_hris_pegawai_position pp on p.nip = pp.nip \n" +
                "                left join im_position pos on pp.position_id = pos.position_id \n" +
                "                left join im_hris_pegawai peg on peg.nip = p.nip \n" +
                "                left join it_hris_payroll_pph pph on pph.payroll_id = p.payroll_id\n" +
                "                where \n" +
                "                pp.branch_id= '"+ unit +"' \n" +
                "                and peg.flag ='Y' \n" +
                "                and pp.flag='Y' \n" +
                "                and p.flag='Y' \n" +
                "                and pos.flag='Y' \n" +
                "                and p.tipe_payroll ='PY' \n" +
                "                and p.tahun = '"+ tahun +"' \n" +
                "                group by \n" +
                "                p.nip, \n" +
                "                peg.npwp, \n" +
                "                peg.no_ktp, \n" +
                "                peg.nama_pegawai, \n" +
                "                peg.alamat, \n" +
                "                peg.jenis_kelamin, \n" +
                "                peg.status_keluarga, \n" +
                "                peg.jumlah_anak, \n" +
                "                pos.position_name, \n" +
                "                pos.kelompok_id, \n" +
                "                pph.ptkp \n" +
                "                order by \n" +
                "                pos.kelompok_id ";

        results = (List<Object[]>)this.sessionFactory.getCurrentSession().createSQLQuery(query).list();
        for (Object[] row : results) {
            PayrollEsptDTO result = new PayrollEsptDTO();
            result.setMasaPerolehanAwal((String)row[0]);
            result.setMasaPerolehanAkhir((String)row[1]);
            result.setNpwp((String)row[2]);
            result.setNik((String)row[3]);
            result.setNama((String)row[4]);
            result.setAlamat((String)row[5]);
            result.setJenisKelamin((String)row[6]);
            result.setStatusPtkp((String)row[7]);
            result.setJumlahTanggungan(String.valueOf(row[8]));
            result.setNamaJabatan((String)row[9]);
            result.setJumlah1(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setJumlah2(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setJumlah3(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setJumlah10(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setNip((String)row[14]);
            result.setJumlah15(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }
    //RAKA-end
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
                "       py.department_id,\n" +
                "       py.department_name,\n" +
                "       dept.kodering as dep_kodering,\n" +
                "       py.sub_divisi,\n" +
                "       py.sub_divisi_name,\n" +
                "       bag.kodering as bag_kodering,\n" +
                "       py.position_id,\n" +
                "       py.position_name\n" +
                "\n" +
                "from it_hris_payroll_header head\n" +
                "       left join it_hris_payroll py on py.payroll_header_id = head.payroll_header_id\n" +
                "       left join it_hris_payroll_pph pph on pph.payroll_id = py.payroll_id\n" +
                "       left join im_hris_department dept on dept.department_id = py.department_id\n" +
                "       left join im_hris_position_bagian bag on bag.bagian_id = py.sub_divisi\n" +
                "       left join\n" +
                "         (select\n" +
                "             ap.nip,\n" +
                "             sum(realisasi_jam_lembur) as jam_lembur,\n" +
                "             sum(jam_lembur) as fak_lembur\n" +
                "           from it_hris_absensi_pegawai ap\n" +
                "           left join it_hris_payroll pay on pay.nip = ap.nip\n" +
                "           where ap.tanggal between pay.tanggal_awal_lembur and pay.tanggal_akhir_lembur\n" +
                "           and pay.bulan = '"+ bulan +"'\n" +
                "           and pay.tahun = '"+ tahun +"'\n" +
                "           group by ap.nip\n" +
                "        ) absen on absen.nip = py.nip \n" +
                "where head.bulan = '"+ bulan +"'\n" +
                "    and head.tahun = '"+ tahun +"'\n" +
                "    and head.branch_id = '"+ unit +"'\n" +
                "    and head.tipe_payroll = 'PY'\n" +
                "    and head.approval_aks_flag = 'Y'\n" +
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

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public String statApprovPayroll(String idPayroalHeader){
        String statPayroll = "";
        List<Object[]> results = new ArrayList<Object[]>();
        final String query = "select\n" +
                "\tapproval_sdm_flag,\n" +
                "\tapproval_aks_flag\n" +
                "from it_hris_payroll_header\n" +
                "where payroll_header_id = '"+ idPayroalHeader +"'\n";
        results = this.sessionFactory.getCurrentSession().createSQLQuery(query).list();

        if(results != null){
            if("Y".equalsIgnoreCase(results.get(0)[1].toString())){
                statPayroll = "approveAKS";
            }else if("Y".equalsIgnoreCase(results.get(0)[0].toString())){
                statPayroll = "approveSDM";
            }else{
                statPayroll = "inProcess";
            }
        }
        return statPayroll;
    }

    //RAKA-end
//
//    public List<ItPayrollEntity> searchReportTarikanPendapatanPPH(final String tahun, final String unit) {
//        final List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//        List<Object[]> results = new ArrayList<Object[]>();
//        final String query = "select\n\tpayroll.nip,\n\tpayroll.gaji_golongan as gaji_dasar,\n\tpayroll.tunjangan_umk as tunjangan_umk,\n\tpayroll.tunjangan_struktural as tunjangan_struktural,\n\tpayroll.tunjangan_peralihan as tunjangan_peralihan,\n\tpayroll.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural,\n\tpayroll.tunjangan_strategis as tunjangan_strategis,\n\tpayroll.kompensasi as tunjangan_kompensasi,\n\tpayroll.tunjangan_transport as tunjangan_transport,\n\tpayroll.tunjangan_air_listrik as tunjangan_air_listrik,\n\tpayroll.tunjangan_pengobatan as tunjangan_pengobatan,\n\tpayroll.tunjangan_perumahan as tunjangan_perumahan,\n\tpayroll.tunjangan_pph as tunjangan_pph,\n\tpayroll.tunjangan_lembur as tunjangan_lembur,\n\tpayroll.tunjangan_lain as tunjangan_lain,\n\tpayroll.total_a as penghasilan_kotor,\n\tpayroll.tunjangan_baju_dinas as tunjangan_baju_dinas,\n\tpayroll.iuran_pensiun as iuran_pensiun,\n\tpayroll.iuran_bpjs_tk as iuran_bpjs_tk,\n\tpayroll.iuran_bpjs_pensiun as iuran_bpjs_pensiun,\n\tpayroll.iuran_bpjs_kesehatan as iuran_bpjs_kesehatan,\n\tpayroll.uang_muka_lainnya as uang_muka_lainnya,\n\tpayroll.kekurangan_bpjs_tk as kekurangan_bpjs_tk,\n\tpayroll.bulan as bulan,\n\tpayroll.pph_gaji as pph,\n\tpayroll.pph_pengobatan as pph_obat,\n\tpayroll.tunjangan_pengobatan as tunj_obat,\n\tpayroll.tunjangan_lembur as lembur\nfrom\n\tim_position posisi\n\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n\tleft join it_hris_pegawai_position pegawai_position on pegawai.nip = pegawai_position.nip\nwhere\n\tpegawai_position.branch_id= '" + unit + "'\n" + "\tand pegawai.flag ='Y'\n" + "\tand payroll.flag_payroll ='Y'\n" + "\tand payroll.tahun = '" + tahun + "'\n" + "\tand payroll.flag_payroll = 'Y'\n" + "order by\n" + "\tpayroll.nip";
//        results = (List<Object[]>)this.sessionFactory.getCurrentSession().createSQLQuery(query).list();
//        for (final Object[] row : results) {
//            final ItPayrollEntity result = new ItPayrollEntity();
//            result.setNip((String)row[0]);
//            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[1].toString())));
//            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[2].toString())));
//            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
//            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
//            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
//            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
//            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
//            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
//            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
//            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
//            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
//            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
//            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
//            result.setTunjanganLain(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
//            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
//            result.setTunjanganBajuDinas(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
//            result.setIuranPensiun(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
//            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
//            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
//            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
//            result.setUangMukaLainnya(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
//            result.setKekuranganBpjsTk(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
//            result.setBulan((String)row[23]);
//            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
//            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
//            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
//            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public List<ItPayrollEntity> getDataEditPensiun(String branchId, String strWhere){
//        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
//
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "SELECT\n" +
//                "  pegawai.nip,\n" +
//                "  pegawai.nama_pegawai,\n" +
//                "  pegawai.branch_id_terakhir,\n" +
//                "  branch.branch_name,\n" +
//                "  position.department_id,\n" +
//                "  department.department_name,\n" +
//                "  pegawai.position_id_terakhir,\n" +
//                "  position.position_name,\n" +
//                "  pegawai.golongan_id,\n" +
//                "  golongan.golongan_name,\n" +
//                "  position.kelompok_id,\n" +
//                "  pegawai.point,\n" +
//                "  pegawai.status_keluarga,\n" +
//                "  pegawai.jumlah_anak,\n" +
//                "  branch.multifikator,\n" +
//                "  pegawai.zakat_profesi,\n" +
//                "  pegawai.jenis_kelamin,\n" +
//                "  pegawai.dana_pensiun, \n" +
//                "  pegawai.tanggal_aktif, \n" +
//                "  pegawai.tanggal_pensiun, \n" +
//                "  pegawai.tipe_pegawai, \n" +
//                "  pegawai.struktur_gaji, \n" +
//                "  pegawai.gaji, \n" +
//                "  tipePegawai.tipe_pegawai_name, \n" +
//                "  position.kelompok_id,\n" +
//                "  pegawai.status_giling,\n" +
//                "  danaPensiun.dana_pensiun as nama_dana_pensiun,\n" +
//                "  pegawai.npwp,\n" +
//                "  pegawai.status_pegawai, \n" +
//                "  pegawai.golongan_dapen, \n" +
//                "  pegawai.golongan_dapen_nusindo,  \n" +
//                "  pegawai.poin_lebih,  branch.umr \n" +
//                "   FROM im_hris_pegawai pegawai\n" +
//                "LEFT JOIN im_branches branch\n" +
//                "  ON branch.branch_id = pegawai.branch_id_terakhir\n" +
//                "LEFT JOIN im_position position\n" +
//                "  ON position.position_id = pegawai.position_id_terakhir\n" +
//                "LEFT JOIN im_hris_department department\n" +
//                "  ON department.department_id = position.department_id\n" +
//                "LEFT JOIN im_hris_golongan golongan\n" +
//                "  ON golongan.golongan_id = pegawai.golongan_id\n" +
//                "LEFT JOIN im_hris_tipe_pegawai tipePegawai\n" +
//                "  ON tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
//                "LEFT JOIN im_hris_payroll_dana_pensiun danaPensiun\n" +
//                "  ON danaPensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
//                "WHERE \n" +
//                "pegawai.flag = 'N'\n" +
//                "AND pegawai.position_id_terakhir is not null\n" +
//                "AND pegawai.branch_id_terakhir= '"+branchId+"'\n" +
//                strWhere;
//
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//
//        for (Object[] row : results) {
//            ItPayrollEntity result  = new ItPayrollEntity();
//            result.setNip((String) row[0]);
//            result.setNama((String) row[1]);
//            result.setBranchId((String) row[2]);
//            result.setBranchName((String) row[3]);
//            result.setDepartmentId((String) row[4]);
//            result.setDepartmentName((String) row[5]);
//            result.setPositionId((String) row[6]);
//            result.setPositionName((String) row[7]);
//            result.setGolonganId((String) row[8]);
//            result.setGolonganName((String) row[9]);
//            result.setKelompokId((String) row[10]);
//            result.setPoint(Integer.parseInt(row[11].toString()));
//            result.setStatusKeluarga((String) row[12]);
//            result.setJumlahAnak(Integer.valueOf(row[13].toString()));
//            result.setMultifikator(Double.valueOf(row[14].toString()).intValue() + "");
//            result.setFlagZakat((String) row[15]);
//            result.setGender((String) row[16]);
//            result.setDanaPensiun((String) row[17]);
//            result.setTanggalAktif((Date) row[18]);
//            result.setTanggalPensiun((Date) row[19]);
//            result.setTipePegawai((String) row[20]);
//            result.setStrukturGaji((String) row[21]);
//            result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
//            result.setTipePegawaiName((String) row[23]);
//            result.setKelompokId((String) row[24]);
//            result.setStatusGiling((String) row[25]);
//            result.setDanaPensiunName((String) row[26]);
//            result.setFlagPjs(("N"));
//            result.setNpwp((String) row[27]);
//            result.setStatusPegawai((String) row[28]);
//            result.setGolonganDapen((String) row[29]);
//            result.setGolonganDapenNusindo((String) row[30]);
//            result.setPointLebih(Integer.parseInt(row[31].toString()));
//            result.setUmr(BigDecimal.valueOf(Double.parseDouble(row[32].toString())));
//
//            listOfResult.add(result);
//        }
//        return listOfResult;
//    }
//
//    public BigDecimal totalLain(String tahun, String nip){
//        BigDecimal total = new BigDecimal(0);
//        String query="select \n" +
//                "\tsum(lain_lain) as jumlah\n" +
//                "\tfrom it_hris_payroll\n" +
//                "\t\twhere nip = '"+nip+"'\n" +
//                "\t\tand tahun='"+tahun+"'\n" +
//                "\t\tand bulan<>'12'\n" +
//                "\t\tand flag='Y'\n" +
//                "\t\tand flag_payroll='Y'\n" +
//                "\t\tand approval_flag='Y'";
//        Object results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query).uniqueResult();
//        if (results!=null){
//            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
//        }else{
//            total = BigDecimal.valueOf(0);
//        }
//        return total;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollByBulanBranchAndTahun(String branchId,String bulan,String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("branchId", branchId))
//                .addOrder(Order.desc("payrollId"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollByBulan12Branch(String branchId,String tahun) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("bulan", "12"))
//                .add(Restrictions.eq("branchId", branchId))
//                .addOrder(Order.desc("payrollId"))
//                .list();
//        return results;
//    }
//
//    public List<ItPayrollEntity> getDataPayrollByBulanBranchApproveNull(String branchId,String tipe) throws HibernateException {
//        String tipeWhere="";
//        switch (tipe) {
//            case "T":
//                tipeWhere = "flagThr";
//                break;
//            case "JP":
//                tipeWhere = "flagJasprod";
//                break;
//            case "IN":
//                tipeWhere = "flagInsentif";
//                break;
//            case "CP":
//                tipeWhere = "flagCutiPanjang";
//                break;
//            case "CT":
//                tipeWhere = "flagCutiTahunan";
//                break;
//        }
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.or(
//                        Restrictions.isNull("approvalFlag"),
//                        Restrictions.eq("approvalFlag", "N")
//                ))
//                .addOrder(Order.desc("payrollId"))
//                .list();
//        return results;
//    }
//    public List<ItPayrollEntity> getBonusDalam1Tahun(String branchId,String tahun,String tipeWhere) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq(tipeWhere, "Y"))
//                .addOrder(Order.desc("payrollId"))
//                .list();
//        return results;
//    }
//    public List<ItPayrollEntity> getBonusDalam1TahunNip(String branchId,String tahun,String tipeWhere,String nip) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("tahun", tahun))
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq(tipeWhere, "Y"))
//                .addOrder(Order.desc("payrollId"))
//                .list();
//        return results;
//    }
//    public BigDecimal getBruto11Bulan(String tahun,String nip){
//        BigDecimal total = new BigDecimal(0);
//        String query=" SELECT " +
//                "sum (gaji_golongan+\n" +
//                "\t\t   tunjangan_umk+\n" +
//                "\t\t   tunjangan_struktural+\n" +
//                "\t\t   tunjangan_peralihan+\n" +
//                "\t\t   tunjangan_jabatan_struktural+\n" +
//                "\t\t   tunjangan_strategis+\n" +
//                "\t\t   tunjangan_lain+\n" +
//                "\t\t   tunjangan_tambahan+\n" +
//                "\t\t   tunjangan_lembur+\n" +
//                "\t\t   pemondokan+\n" +
//                "\t\t   komunikasi+\n" +
//                "\t\t   total_rlab+\n" +
//                "\t\t   tunjangan_tambahan+\n" +
//                "\t\t   tunjangan_dapen+\n" +
//                "\t\t   tunjangan_bpjs_ks+\n" +
//                "\t\t   tunjangan_bpjs_tk ) as jumlah\n" +
//                "\tfrom it_hris_payroll" +
//                "\t\twhere nip = '"+nip+"'\n" +
//                "\t\tand tahun='"+tahun+"'\n" +
//                "\t\tand bulan<>'12'\n" +
//                "\t\tand flag='Y'\n" +
//                "\t\tand flag_payroll='Y'\n" +
//                "\t\tand approval_flag='Y'";
//        Object results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query).uniqueResult();
//        if (results!=null){
//            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
//        }else{
//            total = BigDecimal.valueOf(0);
//        }
//        return total;
//    }
//    public BigDecimal getPPhGaji11Bulan(String tahun,String nip){
//        BigDecimal total = new BigDecimal(0);
//        String query="select \n" +
//                "\tsum(pph_gaji\n" +
//                "\t\t  ) as jumlah\n" +
//                "\tfrom it_hris_payroll\n" +
//                "\t\twhere nip = '"+nip+"'\n" +
//                "\t\tand tahun='"+tahun+"'\n" +
//                "\t\tand bulan<>'12'\n" +
//                "\t\tand flag='Y'\n" +
//                "\t\tand flag_payroll='Y'\n" +
//                "\t\tand approval_flag='Y'";
//        Object results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query).uniqueResult();
//        if (results!=null){
//            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
//        }else{
//            total = BigDecimal.valueOf(0);
//        }
//        return total;
//    }
    public BigDecimal getPPhGaji12Bulan(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select\n" +
                "\tsum(pph.pph_gaji\n" +
                "\t  ) as jumlah\n" +
                "from it_hris_payroll py\n" +
                "    left join it_hris_payroll_header hd on hd.payroll_header_id = py.payroll_header_id\n" +
                "    left join it_hris_payroll_pph pph on pph.payroll_id = py.payroll_id\n" +
                "where py.nip = '"+ nip +"'\n" +
                "\tand hd.tahun='"+tahun+"'\n" +
                "\tand hd.flag='Y'\n" +
                "\tand py.tipe_payroll='PY'\n" +
                "\tand hd.approval_aks_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
//
    public BigDecimal getPPhGajiBonusSetahun(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select\n" +
                "\tsum(pph.pph_gaji\n" +
                "\t  ) as jumlah\n" +
                "from it_hris_payroll py\n" +
                "    left join it_hris_payroll_header hd on hd.payroll_header_id = py.payroll_header_id\n" +
                "    left join it_hris_payroll_pph pph on pph.payroll_id = py.payroll_id\n" +
                "where py.nip = '"+ nip +"'\n" +
                "\tand hd.tahun='"+ tahun +"'\n" +
                "\tand hd.flag='Y'\n" +
                "\tand py.tipe_payroll not in ('PY','PN')\n" +
                "\tand hd.approval_aks_flag='Y';";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
//
//    public BigDecimal getTunjanganPPhGaji11Bulan(String tahun,String nip){
//        BigDecimal total = new BigDecimal(0);
//        String query="select \n" +
//                "\tsum(tunjangan_pph\n" +
//                "\t\t  ) as jumlah\n" +
//                "\tfrom it_hris_payroll\n" +
//                "\t\twhere nip = '"+nip+"'\n" +
//                "\t\tand tahun='"+tahun+"'\n" +
//                "\t\tand bulan<>'12'\n" +
//                "\t\tand flag='Y'\n" +
//                "\t\tand flag_payroll='Y'\n" +
//                "\t\tand approval_flag='Y'";
//        Object results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query).uniqueResult();
//        if (results!=null){
//            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
//        }else{
//            total = BigDecimal.valueOf(0);
//        }
//        return total;
//    }
    public BigDecimal getTunjanganPPhGajiBonusSetahun(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select  \n" +
                "                sum(tunjangan_pph \n" +
                "                  ) as jumlah \n" +
                "                from it_hris_payroll \n" +
                "                where nip = '" +nip+ "' \n" +
                "                and tahun='" +tahun+ "' \n" +
                "                and flag='Y' ";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
//
    public BigDecimal getTotalBonusSetahun(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select\n" +
                "sum(py.thp\n" +
                "  ) as jumlah\n" +
                "from it_hris_payroll py\n" +
                "left join it_hris_payroll_header head on head.payroll_header_id = py.payroll_header_id\n" +
                "left join it_hris_payroll_pph pph on pph.payroll_id = py.payroll_id\n" +
                "where py.nip = '" + nip + "'\n" +
                "and head.tahun ='" + tahun + "'\n" +
                "and head.flag='Y'\n" +
                "and approval_aks_flag = 'Y'\n" +
                "and py.tipe_payroll not in ('PY', 'PN');";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
//    public BigDecimal getIuran11Bulan(String tahun,String nip){
//        BigDecimal total = new BigDecimal(0);
//        String query="select \n" +
//                "\tsum(iuran_dapen_peg+\n" +
//                "\t\tiuran_bpjs_tk_kary+\n" +
//                "\t\tiuran_bpjs_ks_kary\n" +
//                "\t\t  ) as jumlah\n" +
//                "\tfrom it_hris_payroll\n" +
//                "\t\twhere nip = '"+nip+"'\n" +
//                "\t\tand tahun='"+tahun+"'\n" +
//                "\t\tand bulan<>'12'\n" +
//                "\t\tand flag='Y'\n" +
//                "\t\tand flag_payroll='Y'";
//        Object results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query).uniqueResult();
//        if (results!=null){
//            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
//        }else{
//            total = BigDecimal.valueOf(0);
//        }
//        return total;
//    }
//
//    public List<ItPayrollEntity> getDetailPendapatanTidakTeratur(String nip,String tahun) {
//        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class);
//        criteria.add(Restrictions.eq("nip", nip));
//        criteria.add(Restrictions.eq("tahun", tahun));
//        criteria.add(Restrictions.eq("approvalFlag", "Y"));
//        criteria.add(Restrictions.ne("flagPayroll", "Y"));
//        criteria.add(Restrictions.eq("flag", "Y"));
//
//        // Order by
//        criteria.addOrder(Order.desc("payrollId"));
//
//        List<ItPayrollEntity> results = criteria.list();
//
//        return results;
//    }
//    public List<ItPayrollEntity> getDetailPPh11Bulan(String nip,String tahun) {
//        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class);
//        criteria.add(Restrictions.eq("nip", nip));
//        criteria.add(Restrictions.eq("tahun", tahun));
//        criteria.add(Restrictions.eq("approvalFlag", "Y"));
////        criteria.add(Restrictions.eq("flagPayroll", "Y"));
//        criteria.add(Restrictions.eq("flag", "Y"));
//        criteria.add(Restrictions.ne("bulan", "12"));
//
//        // Order by
//        criteria.addOrder(Order.asc("payrollId"));
//
//        List<ItPayrollEntity> results = criteria.list();
//
//        return results;
//    }
//    public List<ItPayrollEntity> getPayrollBulan12(String nip,String tahun) {
//        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class);
//        criteria.add(Restrictions.eq("nip", nip));
//        criteria.add(Restrictions.eq("tahun", tahun));
//        criteria.add(Restrictions.eq("flagPayroll", "Y"));
//        criteria.add(Restrictions.eq("flag", "Y"));
//        criteria.add(Restrictions.eq("bulan", "12"));
//
//        // Order by
//        criteria.addOrder(Order.asc("payrollId"));
//
//        List<ItPayrollEntity> results = criteria.list();
//
//        return results;
//    }
//
//    public List<Payroll> getDaftarGajiKaryawan(String bulan, String tahun,String branch){
//        List<Payroll> listOfResult = new ArrayList<>();
//
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "SELECT\n" +
//                "\tpay.nip,\n" +
//                "\tpay.nama,\n" +
//                "\tpay.golongan_name,\n" +
//                "\tpeg.no_rek_bank,\n" +
//                "\tpay.status_keluarga,\n" +
//                "\tpay.jumlah_anak,\n" +
//                "\tpay.gaji_golongan,\n" +
//                "\tpay.tunjangan_umk,\n" +
//                "\tpay.tunjangan_jabatan_struktural,\n" +
//                "\tpay.tunjangan_struktural,\n" +
//                "\tpay.tunjangan_strategis,\n" +
//                "\tpay.tunjangan_peralihan,\n" +
//                "\tpay.tunjangan_lain,\n" +
//                "\tpay.tunjangan_lembur,\n" +
//                "\tpay.tunjangan_rumah,\n" +
//                "\tpay.tunjangan_listrik,\n" +
//                "\tpay.tunjangan_air,\n" +
//                "\tpay.tunjangan_bbm,\n" +
//                "\tpay.tunjangan_pph,\n" +
//                "\tpay.tunjangan_dapen,\n" +
//                "\tpay.tunjangan_bpjs_tk,\n" +
//                "\tpay.tunjangan_bpjs_ks,\n" +
//                "\tpay.tunjangan_sosial_lain,\n" +
//                "\tpay.pph_gaji,\n" +
//                "\tpay.iuran_ypks,\n" +
//                "\tpay.iuran_dapen_peg,\n" +
//                "\tpay.iuran_dapen_persh,\n" +
//                "\tpay.iuran_bpjs_tk_pers,\n" +
//                "\tpay.iuran_bpjs_tk_kary,\n" +
//                "\tpay.iuran_bpjs_ks_pers,\n" +
//                "\tpay.iuran_bpjs_ks_kary,\n" +
//                "\tpay.total_potongan_lain,\n" +
//                "\tpay.department_id,\n" +
//                "\tpay.department_name\n" +
//                "FROM\n" +
//                "\tit_hris_payroll pay \n" +
//                "\tLEFT JOIN im_hris_pegawai peg ON pay.nip = peg.nip \n" +
//                "\tLEFT JOIN im_position pos ON pay.position_id = pos.position_id\n" +
//                "WHERE\n" +
//                "\tbulan='"+bulan+"' \n" +
//                "\tAND tahun='"+tahun+"'\n" +
//                "\tAND branch_id='"+branch+"'\n" +
//                "\tAND approval_flag='Y'\n" +
//                "\tAND flag_payroll='Y'\n" +
//                "ORDER BY\n" +
//                "\tpay.department_id ASC ,\n" +
//                "\tpos.kelompok_id ASC";
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            Payroll data= new Payroll();
//            data.setNip((String) row[0]);
//            data.setNama((String) row[1]);
//            data.setGolonganName((String) row[2]);
//            data.setNoRek((String) row[3]);
//            data.setStatusKeluarga((String) row[4]+"/"+row[5].toString());
//            data.setGajiGolonganNilai(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
//            data.setTunjanganUmkNilai(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
//            data.setTunjanganJabatanStrukturalNilai(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
//            data.setTunjanganStrukturalNilai(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
//            data.setTunjanganStrategisNilai(BigDecimal.valueOf(Double.parseDouble(row[10].toString())));
//            data.setTunjanganPeralihanNilai(BigDecimal.valueOf(Double.parseDouble(row[11].toString())));
//            data.setTunjanganLainNilai(BigDecimal.valueOf(Double.parseDouble(row[12].toString())));
//            data.setTunjanganLemburNilai(BigDecimal.valueOf(Double.parseDouble(row[13].toString())));
//            data.setTunjanganRumahNilai(BigDecimal.valueOf(Double.parseDouble(row[14].toString())));
//            data.setTunjanganListrikNilai(BigDecimal.valueOf(Double.parseDouble(row[15].toString())));
//            data.setTunjanganAirNilai(BigDecimal.valueOf(Double.parseDouble(row[16].toString())));
//            data.setTunjanganBBMNilai(BigDecimal.valueOf(Double.parseDouble(row[17].toString())));
//            data.setTunjanganPphNilai(BigDecimal.valueOf(Double.parseDouble(row[18].toString())));
//            data.setTunjanganDapenNilai(BigDecimal.valueOf(Double.parseDouble(row[19].toString())));
//            data.setTunjanganBpjsTkNilai(BigDecimal.valueOf(Double.parseDouble(row[20].toString())));
//            data.setTunjanganBpjsKsNilai(BigDecimal.valueOf(Double.parseDouble(row[21].toString())));
//            data.setTunjanganSosialLainNilai(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
//            data.setPphGajiNilai(BigDecimal.valueOf(Double.parseDouble(row[23].toString())));
//            data.setIuranYpksNilai(BigDecimal.valueOf(Double.parseDouble(row[24].toString())));
//            data.setIuranDapenPegNilai(BigDecimal.valueOf(Double.parseDouble(row[25].toString())));
//            data.setIuranDapenPershNilai(BigDecimal.valueOf(Double.parseDouble(row[26].toString())));
//            data.setIuranBpjsTkPersNilai(BigDecimal.valueOf(Double.parseDouble(row[27].toString())));
//            data.setIuranBpjsTkKaryNilai(BigDecimal.valueOf(Double.parseDouble(row[28].toString())));
//            data.setIuranBpjsKsPersNilai(BigDecimal.valueOf(Double.parseDouble(row[29].toString())));
//            data.setIuranBpjsKsKaryNilai(BigDecimal.valueOf(Double.parseDouble(row[30].toString())));
//            data.setTotalPotonganLainNilai(BigDecimal.valueOf(Double.parseDouble(row[31].toString())));
//            data.setDepartmentId((String) row[32]);
//            data.setDepartmentName((String) row[33]);
//            listOfResult.add(data);
//        }
//        return listOfResult;
//    }
//
//    public List<Payroll> getRekapGajiKaryawan(String bulan, String tahun,String branch){
//        List<Payroll> listOfResult = new ArrayList<>();
//
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "SELECT\n" +
//                "\tpb.nama_bagian,\n" +
//                "\tsum(pay.gaji_golongan) as gajigolongan,\n" +
//                "\tsum(pay.tunjangan_umk) as sankhus,\n" +
//                "\tsum(pay.tunjangan_jabatan_struktural) as tjabatan,\n" +
//                "\tsum(pay.tunjangan_struktural) as tstruktural,\n" +
//                "\tsum(pay.tunjangan_strategis) as tstrategis,\n" +
//                "\tsum(pay.tunjangan_peralihan) as tperalihan,\n" +
//                "\tsum(pay.tunjangan_lain) as tlain,\n" +
//                "\tsum(pay.tunjangan_lembur) as tlembur,\n" +
//                "\tsum(pay.tunjangan_rumah) as trumah,\n" +
//                "\tsum(pay.tunjangan_listrik) as tlistrik,\n" +
//                "\tsum(pay.tunjangan_air) as tair,\n" +
//                "\tsum(pay.tunjangan_bbm) as tbbm,\n" +
//                "\tsum(pay.tunjangan_pph) as tpph,\n" +
//                "\tsum(pay.tunjangan_dapen) as tdapen,\n" +
//                "\tsum(pay.tunjangan_bpjs_tk) as tbpjstk,\n" +
//                "\tsum(pay.tunjangan_bpjs_ks) as tbpjsks,\n" +
//                "\tsum(pay.tunjangan_sosial_lain) as tsosiallain,\n" +
//                "\tsum(pay.pph_gaji) as pphgaji,\n" +
//                "\tsum(pay.iuran_ypks) as iuranypks,\n" +
//                "\tsum(pay.iuran_dapen_peg) as iurandapenpeg,\n" +
//                "\tsum(pay.iuran_dapen_persh) as iurandapenpers,\n" +
//                "\tsum(pay.iuran_bpjs_tk_pers) as iuranbpjstkpers,\n" +
//                "\tsum(pay.iuran_bpjs_tk_kary) as iuranbpjstkkary,\n" +
//                "\tsum(pay.iuran_bpjs_ks_pers) as iuranbpjskspers,\n" +
//                "\tsum(pay.iuran_bpjs_ks_kary) as iuranbpjskskary,\n" +
//                "\tsum(pay.total_potongan_lain) as totalpotlain,\n" +
//                "\tpay.department_id,\n" +
//                "\tpay.department_name\n" +
//                "FROM\n" +
//                "\tit_hris_payroll pay \n" +
//                "\tLEFT JOIN im_hris_pegawai peg ON pay.nip = peg.nip \n" +
//                "\tLEFT JOIN im_position pos ON pay.position_id = pos.position_id\n" +
//                "\tLEFT JOIN im_hris_position_bagian pb ON pb.bagian_id = pos.bagian_id \n" +
//                "WHERE\n" +
//                "\tpay.bulan='"+bulan+"' \n" +
//                "\tAND pay.tahun='"+tahun+"'\n" +
//                "\tAND pay.branch_id='"+branch+"'\n" +
//                "\tAND pay.approval_flag='Y'\n" +
//                "\tAND pay.flag_payroll='Y'\n" +
//                "GROUP BY\n" +
//                "\tpb.nama_bagian,\n" +
//                "\tpay.department_id,\n" +
//                "\tpay.department_name\n" +
//                "ORDER BY\n" +
//                "\tpay.department_id ASC ";
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        for (Object[] row : results) {
//            Payroll data= new Payroll();
//            data.setNama((String) row[0]);
//            data.setGajiGolonganNilai(BigDecimal.valueOf(Double.parseDouble(row[1].toString())));
//            data.setTunjanganUmkNilai(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
//            data.setTunjanganJabatanStrukturalNilai(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
//            data.setTunjanganStrukturalNilai(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
//            data.setTunjanganStrategisNilai(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
//            data.setTunjanganPeralihanNilai(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
//            data.setTunjanganLainNilai(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
//            data.setTunjanganLemburNilai(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
//            data.setTunjanganRumahNilai(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
//            data.setTunjanganListrikNilai(BigDecimal.valueOf(Double.parseDouble(row[10].toString())));
//            data.setTunjanganAirNilai(BigDecimal.valueOf(Double.parseDouble(row[11].toString())));
//            data.setTunjanganBBMNilai(BigDecimal.valueOf(Double.parseDouble(row[12].toString())));
//            data.setTunjanganPphNilai(BigDecimal.valueOf(Double.parseDouble(row[13].toString())));
//            data.setTunjanganDapenNilai(BigDecimal.valueOf(Double.parseDouble(row[14].toString())));
//            data.setTunjanganBpjsTkNilai(BigDecimal.valueOf(Double.parseDouble(row[15].toString())));
//            data.setTunjanganBpjsKsNilai(BigDecimal.valueOf(Double.parseDouble(row[16].toString())));
//            data.setTunjanganSosialLainNilai(BigDecimal.valueOf(Double.parseDouble(row[17].toString())));
//            data.setPphGajiNilai(BigDecimal.valueOf(Double.parseDouble(row[18].toString())));
//            data.setIuranYpksNilai(BigDecimal.valueOf(Double.parseDouble(row[19].toString())));
//            data.setIuranDapenPegNilai(BigDecimal.valueOf(Double.parseDouble(row[20].toString())));
//            data.setIuranDapenPershNilai(BigDecimal.valueOf(Double.parseDouble(row[21].toString())));
//            data.setIuranBpjsTkPersNilai(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
//            data.setIuranBpjsTkKaryNilai(BigDecimal.valueOf(Double.parseDouble(row[23].toString())));
//            data.setIuranBpjsKsPersNilai(BigDecimal.valueOf(Double.parseDouble(row[24].toString())));
//            data.setIuranBpjsKsKaryNilai(BigDecimal.valueOf(Double.parseDouble(row[25].toString())));
//            data.setTotalPotonganLainNilai(BigDecimal.valueOf(Double.parseDouble(row[26].toString())));
//            data.setDepartmentId((String) row[27]);
//            data.setDepartmentName((String) row[28]);
//            listOfResult.add(data);
//        }
//        return listOfResult;
//    }
//
//    public List<Payroll> getPotonganIuranDapen(String bulan, String tahun,String branch,String tipeDapen){
//        List<Payroll> listOfResult = new ArrayList<>();
//
//        List<Object[]> results = new ArrayList<Object[]>();
//        String query = "SELECT \n" +
//                "\tpay.department_name,\n" +
//                "\tpay.nip,\n" +
//                "\tpay.nama,\n" +
//                "\tpeg.no_anggota_dapen,\n" +
//                "\tgdp.golongan_dapen_name,\n" +
//                "\tpay.gaji_pensiun,\n" +
//                "\tpay.iuran_dapen_peg,\n" +
//                "\tpay.iuran_dapen_persh\n" +
//                "FROM\n" +
//                "\tit_hris_payroll pay\n" +
//                "\tLEFT JOIN im_hris_pegawai peg ON pay.nip=peg.nip\n" +
//                "\tLEFT JOIN im_hris_golongan_dapen gdp ON gdp.golongan_dapen_id=peg.golongan_dapen_id\n" +
//                "WHERE\n" +
//                "\tpay.bulan='"+bulan+"'\n" +
//                "\tAND pay.tahun='"+tahun+"'\n" +
//                "\tAND pay.branch_id='"+branch+"'\n" +
//                "\tAND peg.dana_pensiun='"+tipeDapen+"'\n" +
//                "ORDER BY\n" +
//                "\tdepartment_id ASC";
//        results = this.sessionFactory.getCurrentSession()
//                .createSQLQuery(query)
//                .list();
//
//        int urut=1;
//        String departmentName = "";
//        for (Object[] row : results) {
//            if (!departmentName.equalsIgnoreCase((String) row[0])){
//                urut=1;
//                departmentName=(String) row[0];
//            }
//            Payroll data= new Payroll();
//            data.setPoint(urut);
//            data.setDepartmentName((String) row[0]);
//            data.setNip((String) row[1]);
//            data.setNama((String) row[2]);
//            data.setNoAnggotaDapen((String) row[3]);
//            data.setGolonganDapenName((String) row[4]);
//            data.setGajiPensiunNilai(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
//            data.setIuranDapenPegNilai(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
//            data.setIuranDapenPershNilai(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
//            data.setIuranPensiunJumlahNilai(data.getIuranDapenPegNilai().add(data.getIuranDapenPershNilai()));
//            urut++;
//            listOfResult.add(data);
//        }
//        return listOfResult;
//    }
}