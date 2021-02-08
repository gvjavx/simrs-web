package com.neurix.simrs.transaksi.laporan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.laporan.model.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LaporanOpsDao extends GenericDao<ImSimrsLaporanOpsEntity, String> {

    @Override
    protected Class<ImSimrsLaporanOpsEntity> getEntityClass() {
        return ImSimrsLaporanOpsEntity.class;
    }

    @Override
    public List<ImSimrsLaporanOpsEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsLaporanOpsEntity.class);
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_laporan_ops")!=null) {
                criteria.add(Restrictions.eq("idLaporanOps", (String) mapCriteria.get("id_asesmen_kandungan")));
            }
            if (mapCriteria.get("nama_laporan")!=null) {
                criteria.add(Restrictions.eq("namaLaporan", (String) mapCriteria.get("nama_laporan")));
            }
        }
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImSimrsLaporanOpsEntity> results = criteria.list();
        return results;
    }

    public List<LaporanOps> getLaporanRjRi(LaporanOps bean){
        List<LaporanOps> laporanList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        String SQL = "";

        if("rawat_jalan".equalsIgnoreCase(bean.getTipeLaporan())){
            if(bean.getListIdPelayanan().size() > 0){
                stringList = bean.getListIdPelayanan();
            }else{
                stringList = getIdPelayanan(bean.getBranchId());
            }
            if(stringList.size() > 0){
                int i = 1;
                for (String idPelayanan: stringList){
                    LaporanOps ops = new LaporanOps();
                    ops.setPelayananId(idPelayanan);
                    ops.setTahun(bean.getTahun());
                    if(SQL != ""){
                        SQL = SQL + ConstanQuery.plusQuery() + QueryRj.queryRJ(ops);
                    }else{
                        SQL = QueryRj.queryRJ(ops);
                    }

                    if(i == stringList.size()){
                        ops.setListIdPelayanan(stringList);
                        SQL = SQL + ConstanQuery.plusQuery() + QueryRj.queryRJ(ops);
                    }
                    i++;
                }
            }
        }

        if("rawat_inap".equalsIgnoreCase(bean.getTipeLaporan())){
            if(bean.getListIdRuangan().size() > 0){
                stringList = bean.getListIdRuangan();
            }else{
                stringList = getIdRuangan(bean.getBranchId(), bean.getIdKelasRuangan());
            }
            if(stringList.size() > 0){
                int i = 1;
                for (String idRuangan: stringList){
                    LaporanOps ops = new LaporanOps();
                    ops.setIdRuangan(idRuangan);
                    ops.setTahun(bean.getTahun());
                    if(SQL != ""){
                        SQL = SQL + ConstanQuery.plusQuery() + QueryRi.queryRI(ops);
                    }else{
                        SQL = QueryRi.queryRI(ops);
                    }

                    if(i == stringList.size()){
                        ops.setListIdRuangan(stringList);
                        SQL = SQL + ConstanQuery.plusQuery() + QueryRi.queryRI(ops);
                    }
                    i++;
                }
            }
        }

        if(!"".equalsIgnoreCase(SQL)){
            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .list();
            if(results.size() > 0){
                for (Object[] obj: results){
                    LaporanOps laporan = new LaporanOps();
                    laporan.setNomor(obj[0] != null ? obj[0].toString() : null);
                    laporan.setUrain(obj[1] != null ? obj[1].toString() : null);
                    laporan.setJan(obj[2] != null ? obj[2].toString() : null);
                    laporan.setFeb(obj[3] != null ? obj[3].toString() : null);
                    laporan.setMar(obj[4] != null ? obj[4].toString() : null);
                    laporan.setApr(obj[5] != null ? obj[5].toString() : null);
                    laporan.setMei(obj[6] != null ? obj[6].toString() : null);
                    laporan.setJun(obj[7] != null ? obj[7].toString() : null);
                    laporan.setJul(obj[8] != null ? obj[8].toString() : null);
                    laporan.setAgs(obj[9] != null ? obj[9].toString() : null);
                    laporan.setSep(obj[10] != null ? obj[10].toString() : null);
                    laporan.setOkt(obj[11] != null ? obj[11].toString() : null);
                    laporan.setNov(obj[12] != null ? obj[12].toString() : null);
                    laporan.setDes(obj[13] != null ? obj[13].toString() : null);
                    laporan.setTotal(obj[14] != null ? obj[14].toString() : null);
                    laporan.setPersen(obj[15] != null ? obj[15].toString() : null);
                    laporanList.add(laporan);
                }
            }
        }
        return laporanList;
    }

    public List<LaporanOps> getLaporanPlyUnggulan(LaporanOps bean){
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        if(bean != null){
            String branch = "%";
            String date = "";
            String tahun = "";
            if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                branch = bean.getBranchId();
            }
            if(bean.getDateFrom() != null && !"".equalsIgnoreCase(bean.getDateFrom()) &&
                    bean.getDateTo() != null && !"".equalsIgnoreCase(bean.getDateTo())){
                date = "AND CAST(a.created_date AS DATE) >= to_date('"+bean.getDateFrom()+"', 'dd-MM-yyyy') AND CAST(a.created_date AS DATE) <= to_date('"+bean.getDateTo()+"', 'dd-MM-yyyy')\n";
            }else if(bean.getDateFrom() != null && !"".equalsIgnoreCase(bean.getDateFrom())){
                date = "AND CAST(a.created_date AS DATE) >= to_date('"+bean.getDateFrom()+"', 'dd-MM-yyyy')\n";
            }else if(bean.getDateTo() != null && !"".equalsIgnoreCase(bean.getDateTo())){
                date = "CAST(a.created_date AS DATE) <= to_date('"+bean.getDateTo()+"', 'dd-MM-yyyy')\n";
            }

            if(bean.getTahun() != null && !"".equalsIgnoreCase(bean.getTahun())){
                tahun = "AND date_part('year', a.created_date) = '"+bean.getTahun()+"'\n";
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
                    "d.branch_name \n";
            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branch", branch)
                    .list();
            if(result.size() > 0){
                int i = 1;
                for (Object[] obj: result){
                    LaporanOps laporanOps = new LaporanOps();
                    laporanOps.setNomor(String.valueOf(i));
                    laporanOps.setBranchName(obj[0] != null ? obj[0].toString() : null);
                    laporanOps.setNamaPelayanan(obj[1] != null ? obj[1].toString() : null);
                    laporanOps.setTotal(obj[2] != null ? obj[2].toString() : null);
                    laporanOpsList.add(laporanOps);
                    i++;
                }
            }
        }
        return laporanOpsList;
    }

    private List<String> getIdPelayanan(String branchId){
        List<String> stringList = new ArrayList<>();
        if(branchId != null){
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
            if(result.size() > 0){
                for (Object[] obj: result){
                    stringList.add(obj[1] != null ? obj[1].toString() : null);
                }
            }
        }
        return stringList;
    }

    private List<String> getIdRuangan(String branchId, String idKelasRuangan){
        List<String> stringList = new ArrayList<>();
        if(branchId != null){
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
            if(result.size() > 0){
                for (Object[] obj: result){
                    stringList.add(obj[1] != null ? obj[1].toString() : null);
                }
            }
        }
        return stringList;
    }
}
