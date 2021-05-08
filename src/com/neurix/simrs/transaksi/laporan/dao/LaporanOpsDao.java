package com.neurix.simrs.transaksi.laporan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.laporan.model.Laporan;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.transaksi.laporan.model.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class LaporanOpsDao extends GenericDao<ImSimrsLaporanOpsEntity, String> {

    @Override
    protected Class<ImSimrsLaporanOpsEntity> getEntityClass() {
        return ImSimrsLaporanOpsEntity.class;
    }

    @Override
    public List<ImSimrsLaporanOpsEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsLaporanOpsEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_laporan_ops") != null) {
                criteria.add(Restrictions.eq("idLaporanOps", (String) mapCriteria.get("id_asesmen_kandungan")));
            }
            if (mapCriteria.get("nama_laporan") != null) {
                criteria.add(Restrictions.eq("namaLaporan", (String) mapCriteria.get("nama_laporan")));
            }
        }
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImSimrsLaporanOpsEntity> results = criteria.list();
        return results;
    }

    public List<LaporanOps> getLaporanRjRi(LaporanOps bean) {
        List<LaporanOps> laporanList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        String SQL = "";

        if ("rawat_jalan".equalsIgnoreCase(bean.getTipeLaporan())) {
            if (bean.getListIdPelayanan().size() > 0) {
                stringList = bean.getListIdPelayanan();
            } else {
                stringList = getIdPelayanan(bean.getBranchId());
            }
            if (stringList.size() > 0) {
                int i = 1;
                for (String idPelayanan : stringList) {
                    LaporanOps ops = new LaporanOps();
                    ops.setPelayananId(idPelayanan);
                    ops.setTahun(bean.getTahun());
                    if (SQL != "") {
                        SQL = SQL + ConstanQuery.plusQuery() + QueryRj.queryRJ(ops);
                    } else {
                        SQL = QueryRj.queryRJ(ops);
                    }

                    if (i == stringList.size()) {
                        ops.setListIdPelayanan(stringList);
                        SQL = SQL + ConstanQuery.plusQuery() + QueryRj.queryRJ(ops);
                    }
                    i++;
                }
            }
        }

        if ("rawat_inap".equalsIgnoreCase(bean.getTipeLaporan())) {
            if (bean.getListIdRuangan().size() > 0) {
                stringList = bean.getListIdRuangan();
            } else {
                stringList = getIdRuangan(bean.getBranchId(), bean.getIdKelasRuangan());
            }
            if (stringList.size() > 0) {
                int i = 1;
                for (String idRuangan : stringList) {
                    LaporanOps ops = new LaporanOps();
                    ops.setIdRuangan(idRuangan);
                    ops.setTahun(bean.getTahun());
                    if (SQL != "") {
                        SQL = SQL + ConstanQuery.plusQuery() + QueryRi.queryRI(ops);
                    } else {
                        SQL = QueryRi.queryRI(ops);
                    }

                    if (i == stringList.size()) {
                        ops.setListIdRuangan(stringList);
                        SQL = SQL + ConstanQuery.plusQuery() + QueryRi.queryRI(ops);
                    }
                    i++;
                }
            }
        }

        if (!"".equalsIgnoreCase(SQL)) {
            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .list();
            if (results.size() > 0) {
                for (Object[] obj : results) {
                    LaporanOps laporan = new LaporanOps();
                    laporan.setNomor(obj[0] != null && !"0".equalsIgnoreCase(obj[0].toString()) ? obj[0].toString() : null);
                    laporan.setUrain(obj[1] != null && !"0".equalsIgnoreCase(obj[1].toString()) ? obj[1].toString() : null);
                    laporan.setJan(obj[2] != null && !"0".equalsIgnoreCase(obj[2].toString()) ? obj[2].toString() : null);
                    laporan.setFeb(obj[3] != null && !"0".equalsIgnoreCase(obj[3].toString()) ? obj[3].toString() : null);
                    laporan.setMar(obj[4] != null && !"0".equalsIgnoreCase(obj[4].toString()) ? obj[4].toString() : null);
                    laporan.setApr(obj[5] != null && !"0".equalsIgnoreCase(obj[5].toString()) ? obj[5].toString() : null);
                    laporan.setMei(obj[6] != null && !"0".equalsIgnoreCase(obj[6].toString()) ? obj[6].toString() : null);
                    laporan.setJun(obj[7] != null && !"0".equalsIgnoreCase(obj[7].toString()) ? obj[7].toString() : null);
                    laporan.setJul(obj[8] != null && !"0".equalsIgnoreCase(obj[8].toString()) ? obj[8].toString() : null);
                    laporan.setAgs(obj[9] != null && !"0".equalsIgnoreCase(obj[9].toString()) ? obj[9].toString() : null);
                    laporan.setSep(obj[10] != null && !"0".equalsIgnoreCase(obj[10].toString()) ? obj[10].toString() : null);
                    laporan.setOkt(obj[11] != null && !"0".equalsIgnoreCase(obj[11].toString()) ? obj[11].toString() : null);
                    laporan.setNov(obj[12] != null && !"0".equalsIgnoreCase(obj[12].toString()) ? obj[12].toString() : null);
                    laporan.setDes(obj[13] != null && !"0".equalsIgnoreCase(obj[13].toString()) ? obj[13].toString() : null);
                    laporan.setTotal(obj[14] != null && !"0".equalsIgnoreCase(obj[14].toString()) ? obj[14].toString() : null);
                    laporan.setPersen(obj[15] != null && !"0.0".equalsIgnoreCase(obj[15].toString()) ? obj[15].toString() : null);
                    laporanList.add(laporan);
                }
            }
        }
        return laporanList;
    }

    public List<LaporanOps> getLaporanPlyUnggulan(LaporanOps bean) {
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        if (bean != null) {
            String branch = "%";
            String date = "";
            String tahun = "";
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branch = bean.getBranchId();
            }
            if (bean.getDateFrom() != null && !"".equalsIgnoreCase(bean.getDateFrom()) &&
                    bean.getDateTo() != null && !"".equalsIgnoreCase(bean.getDateTo())) {
                date = "AND CAST(a.created_date AS DATE) >= to_date('" + bean.getDateFrom() + "', 'dd-MM-yyyy') AND CAST(a.created_date AS DATE) <= to_date('" + bean.getDateTo() + "', 'dd-MM-yyyy')\n";
            } else if (bean.getDateFrom() != null && !"".equalsIgnoreCase(bean.getDateFrom())) {
                date = "AND CAST(a.created_date AS DATE) >= to_date('" + bean.getDateFrom() + "', 'dd-MM-yyyy')\n";
            } else if (bean.getDateTo() != null && !"".equalsIgnoreCase(bean.getDateTo())) {
                date = "CAST(a.created_date AS DATE) <= to_date('" + bean.getDateTo() + "', 'dd-MM-yyyy')\n";
            }

            if (bean.getTahun() != null && !"".equalsIgnoreCase(bean.getTahun())) {
                tahun = "AND date_part('year', a.created_date) = '" + bean.getTahun() + "'\n";
            }
            String SQL = "SELECT\n" +
                    "d.branch_name,\n" +
                    "c.nama_pelayanan,\n" +
                    "COUNT(a.id_detail_checkup) as jumlah\n" +
                    "FROM it_simrs_header_detail_checkup a\n" +
                    "INNER JOIN im_simrs_pelayanan b ON a.id_pelayanan = b.id_pelayanan\n" +
                    "INNER JOIN im_simrs_header_pelayanan c ON b.id_header_pelayanan = c.id_header_pelayanan\n" +
                    "INNER JOIN im_branches d ON a.branch_id = d.branch_id\n" +
                    "WHERE a.branch_id LIKE :branch \n" +
                    "AND c.tipe_pelayanan = 'rawat_jalan'\n" + tahun + date +
                    "GROUP BY b.id_pelayanan, \n" +
                    "c.nama_pelayanan, \n" +
                    "a.branch_id, \n" +
                    "d.branch_name \n" +
                    "ORDER BY branch_name ASC,\n" +
                    "COUNT(a.id_detail_checkup) DESC\n";
            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branch", branch)
                    .list();
            if (result.size() > 0) {
                int i = 1;
                String tempBranch = "";
                for (Object[] obj : result) {
                    LaporanOps laporanOps = new LaporanOps();
                    laporanOps.setBranchName(obj[0] != null ? obj[0].toString() : null);
                    laporanOps.setNamaPelayanan(obj[1] != null ? obj[1].toString() : null);
                    laporanOps.setTotal(obj[2] != null ? obj[2].toString() : null);
                    if (laporanOps.getBranchName() != null) {
                        if (!laporanOps.getBranchName().equalsIgnoreCase(tempBranch)) {
                            tempBranch = laporanOps.getBranchName();
                            i = 1;
                        }
                    }
                    laporanOps.setNomor(String.valueOf(i));
                    laporanOpsList.add(laporanOps);
                    i++;
                }
            }
        }
        return laporanOpsList;
    }

    private List<String> getIdPelayanan(String branchId) {
        List<String> stringList = new ArrayList<>();
        if (branchId != null) {
            String SQL = "SELECT\n" +
                    "a.id_header_pelayanan,\n" +
                    "b.id_pelayanan,\n" +
                    "a.nama_pelayanan\n" +
                    "FROM im_simrs_header_pelayanan a\n" +
                    "INNER JOIN im_simrs_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                    "WHERE a.tipe_pelayanan IN ('rawat_jalan', 'igd') \n" +
                    "AND b.branch_id = :branch \n" +
                    "AND b.flag = 'Y'";
            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branch", branchId)
                    .list();
            if (result.size() > 0) {
                for (Object[] obj : result) {
                    stringList.add(obj[1] != null ? obj[1].toString() : null);
                }
            }
        }
        return stringList;
    }

    private List<String> getIdRuangan(String branchId, String idKelasRuangan) {
        List<String> stringList = new ArrayList<>();
        if (branchId != null) {
            String SQL = "SELECT\n" +
                    "a.id_kelas_ruangan,\n" +
                    "b.id_ruangan\n" +
                    "FROM im_simrs_kelas_ruangan a\n" +
                    "INNER JOIN mt_simrs_ruangan b ON a.id_kelas_ruangan = b.id_kelas_ruangan\n" +
                    "WHERE a.id_kelas_ruangan = :kelas\n" +
                    "AND b.branch_id = :branch\n" +
                    "AND b.flag = 'Y'";
            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branch", branchId)
                    .setParameter("kelas", idKelasRuangan)
                    .list();
            if (result.size() > 0) {
                for (Object[] obj : result) {
                    stringList.add(obj[1] != null ? obj[1].toString() : null);
                }
            }
        }
        return stringList;
    }

    public List<LaporanOps> getListTahunByOps() {
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        String SQL = "SELECT \n" +
                "CAST('tahun' AS VARCHAR) as label, \n" +
                "CAST(DATE_PART('year', created_date) AS INTEGER) as tahun\n" +
                "FROM it_simrs_header_checkup\n" +
                "GROUP BY DATE_PART('year', created_date)\n";
        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if (result.size() > 0) {
            int i = 1;
            for (Object[] obj : result) {
                LaporanOps laporanOpsAwal = new LaporanOps();
                Integer tahun = (Integer) obj[1];
                if (i == 1) {
                    Integer year = tahun - 1;
                    laporanOpsAwal.setTahun(String.valueOf(year));
                    laporanOpsList.add(laporanOpsAwal);
                }

                LaporanOps laporanOps = new LaporanOps();
                laporanOps.setTahun(obj[1] != null ? obj[1].toString() : null);
                laporanOpsList.add(laporanOps);

                LaporanOps laporanOpsAkhir = new LaporanOps();
                if (i == result.size()) {
                    Integer year = tahun + 1;
                    laporanOpsAkhir.setTahun(String.valueOf(year));
                    laporanOpsList.add(laporanOpsAkhir);
                }

                i++;
            }
        } else {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            LaporanOps laporanOps = new LaporanOps();
            laporanOps.setTahun(String.valueOf(year));
            laporanOpsList.add(laporanOps);
        }
        return laporanOpsList;
    }

    public List<LaporanOps> getListDiagnosaTerbanyak(LaporanOps bean) {
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        if (bean != null) {
            String orderBy = "";
            String tempBulan = "";
            if ("rawat_jalan".equalsIgnoreCase(bean.getTipePelayanan())) {
                orderBy = "jenis_diagnosa";
            }
            if ("rawat_inap".equalsIgnoreCase(bean.getTipePelayanan())) {
                orderBy = "created_date";
            }

            if (bean.getListBulan().size() > 0) {
                for (String bulan : bean.getListBulan()) {
                    if (!"".equalsIgnoreCase(tempBulan)) {
                        tempBulan = tempBulan + ", '" + bulan + "'";
                    } else {
                        tempBulan = "'" + bulan + "'";
                    }
                }
            }
            String SQL = "SELECT\n" +
                    "a.bulan,\n" +
                    "a.nama_bulan,\n" +
                    "b.id_diagnosa,\n" +
                    "b.keterangan_diagnosa,\n" +
                    "b.jumlah\n" +
                    "FROM (\n" +
                    "SELECT\n" +
                    "a.bulan,\n" +
                    "a.nama_bulan FROM (\n" +
                    "\tSELECT\n" +
                    "\tgenerate_series as bulan,\n" +
                    "\tCASE\n" +
                    "\t WHEN generate_series = 1 THEN 'Januari'\n" +
                    "\t WHEN generate_series = 2 THEN 'Februari'\n" +
                    "\t WHEN generate_series = 3 THEN 'Maret'\n" +
                    "\t WHEN generate_series = 4 THEN 'April'\n" +
                    "\t WHEN generate_series = 5 THEN 'Mei'\n" +
                    "\t WHEN generate_series = 6 THEN 'Juni'\n" +
                    "\t WHEN generate_series = 7 THEN 'Juli'\n" +
                    "\t WHEN generate_series = 8 THEN 'Agustus'\n" +
                    "\t WHEN generate_series = 9 THEN 'September'\n" +
                    "\t WHEN generate_series = 10 THEN 'Oktober'\n" +
                    "\t WHEN generate_series = 11 THEN 'November'\n" +
                    "\t WHEN generate_series = 12 THEN 'Desember'\n" +
                    "\t ELSE null END as nama_bulan\n" +
                    "\tFROM generate_series(1,12)\n" +
                    ")a WHERE a.bulan IN (" + tempBulan + ")\n" +
                    ")a LEFT JOIN (\n" +
                    "SELECT\n" +
                    "a.bulan,\n" +
                    "a.id_diagnosa,\n" +
                    "a.keterangan_diagnosa,\n" +
                    "COUNT(a.id_diagnosa) as jumlah\n" +
                    "FROM (\n" +
                    "\tSELECT \n" +
                    "\ta.id_detail_checkup,\n" +
                    "\td.id_diagnosa,\n" +
                    "\td.keterangan_diagnosa,\n" +
                    "\tDATE_PART('month', d.created_date) as bulan\n" +
                    "\tFROM it_simrs_header_detail_checkup a\n" +
                    "\tINNER JOIN im_simrs_pelayanan b ON a.id_pelayanan = b.id_pelayanan\n" +
                    "\tINNER JOIN im_simrs_header_pelayanan c ON b.id_header_pelayanan = c.id_header_pelayanan\n" +
                    "\tINNER JOIN (\n" +
                    "\tSELECT * FROM (\n" +
                    "\t\tSELECT\n" +
                    "\t\tid_detail_checkup,\n" +
                    "\t\tid_diagnosa,\n" +
                    "\t\tketerangan_diagnosa,\n" +
                    "\t\tjenis_diagnosa,\n" +
                    "\t\tcreated_date,\n" +
                    "\t\tRANK() OVER(PARTITION BY id_detail_checkup ORDER BY " + orderBy + " DESC) as rank\n" +
                    "\t\tFROM it_simrs_diagnosa_rawat\n" +
                    "\t) aa WHERE aa.rank = '1'\n" +
                    "\t) d ON a.id_detail_checkup = d.id_detail_checkup\n" +
                    "\tWHERE a.branch_id = :branch \n" +
                    "\tAND c.tipe_pelayanan = :tipe \n" +
                    "\tAND DATE_PART('month', d.created_date) IN (" + tempBulan + ")\n" +
                    ")a \n" +
                    "GROUP BY bulan,\n" +
                    "a.id_diagnosa,\n" +
                    "a.keterangan_diagnosa,\n" +
                    "a.bulan\n" +
                    ") b ON a.bulan = b.bulan\n" +
                    "ORDER BY a.bulan, b.id_diagnosa ASC\n";

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("tipe", bean.getTipePelayanan())
                    .setParameter("branch", bean.getBranchId())
                    .list();

            if (result.size() > 0) {
                for (Object[] obj : result) {
                    LaporanOps laporanOps = new LaporanOps();
                    laporanOps.setBulan(obj[1] != null ? obj[1].toString() : null);
                    laporanOps.setIcdx(obj[2] != null ? obj[2].toString() : null);
                    laporanOps.setNamaPenyakit(obj[3] != null ? obj[3].toString() : null);
                    laporanOps.setTotal(obj[4] != null ? obj[4].toString() : null);
                    laporanOpsList.add(laporanOps);
                }
            }
        }
        return laporanOpsList;
    }

    public List<Pelayanan> getListPenunjangMedis(Pelayanan bean) {
        List<Pelayanan> pelayananList = new ArrayList<>();
        if (bean != null) {
            String SQL = "";
            if ("farmasi".equalsIgnoreCase(bean.getTipePelayanan())) {
                SQL = "SELECT\n" +
                        "b.id_pelayanan,\n" +
                        "b.nama_pelayanan\n" +
                        "FROM im_simrs_header_pelayanan a\n" +
                        "INNER JOIN im_simrs_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                        "WHERE a.tipe_pelayanan IN ('apotek','apotek_ri')\n" +
                        "AND b.branch_id = :branch";
            }
            if("kamar".equalsIgnoreCase(bean.getTipePelayanan())){
                SQL = "SELECT\n" +
                        "b.id_ruangan,\n" +
                        "CASE \n" +
                        " WHEN b.no_ruangan IS NULL OR b.no_ruangan = '' THEN b.nama_ruangan\n" +
                        "ELSE CONCAT('[',b.no_ruangan,'] ',b.nama_ruangan) END as ruangan\n" +
                        "FROM im_simrs_kelas_ruangan a\n" +
                        "INNER JOIN mt_simrs_ruangan b ON a.id_kelas_ruangan = b.id_kelas_ruangan\n" +
                        "WHERE a.kategori NOT LIKE 'rawat_inap'\n" +
                        "AND b.branch_id = :branch";
            }

            if("lab".equalsIgnoreCase(bean.getTipePelayanan()) || "radiologi".equalsIgnoreCase(bean.getTipePelayanan())){
                SQL = "SELECT\n" +
                        "c.id_parameter_pemeriksaan,\n" +
                        "c.nama_pemeriksaan\n" +
                        "FROM im_simrs_lab a\n" +
                        "INNER JOIN im_simrs_lab_detail b ON a.id_lab = b.id_lab\n" +
                        "INNER JOIN im_simrs_parameter_pemeriksaan c ON b.id_parameter_pemeriksaan = c.id_parameter_pemeriksaan\n" +
                        "INNER JOIN im_simrs_kategori_lab d ON c.id_kategori_lab = d.id_kategori_lab\n" +
                        "WHERE d.kategori = '"+bean.getTipePelayanan()+"'\n" +
                        "AND b.branch_id = :branch";
            }

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branch", bean.getBranchId())
                    .list();

            if (result.size() > 0) {
                for (Object[] obj : result) {
                    Pelayanan pelayanan = new Pelayanan();
                    pelayanan.setIdPelayanan(obj[0] != null ? obj[0].toString() : null);
                    pelayanan.setNamaPelayanan(obj[1] != null ? obj[1].toString() : null);
                    pelayananList.add(pelayanan);
                }
            }
        }
        return pelayananList;
    }
}
