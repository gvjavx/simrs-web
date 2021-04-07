package com.neurix.simrs.transaksi.teamdokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DokterTeamDao extends GenericDao<ItSimrsDokterTeamEntity, String> {
    @Override
    protected Class<ItSimrsDokterTeamEntity> getEntityClass() {
        return ItSimrsDokterTeamEntity.class;
    }

    @Override
    public List<ItSimrsDokterTeamEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDokterTeamEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_team_dokter")!=null) {
                criteria.add(Restrictions.eq("idTeamDokter", (String) mapCriteria.get("id_team_dokter")));
            }
            if (mapCriteria.get("id_dokter")!=null) {
                criteria.add(Restrictions.eq("idDokter", (String) mapCriteria.get("id_dokter")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("kategori")!=null) {
                criteria.add(Restrictions.eq("kategori", (String) mapCriteria.get("kategori")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));

        // Order by
        criteria.addOrder(Order.asc("idTeamDokter"));

        List<ItSimrsDokterTeamEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_team_dokter')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public String namaPelayanan(String idPelayanan) {
        String res = "";
        if(idPelayanan != null && !"".equalsIgnoreCase(idPelayanan) ){
            String SQL = "SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "a.branch_id,"+
                    "b.kode_vclaim\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan \n"+
                    "WHERE a.id_pelayanan = :idPelayanan";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPelayanan", idPelayanan)
                    .list();

            if(result.size() > 0){
                Object[] obj = result.get(0);
                res = obj[1].toString();
            }
        }

        return res;
    }

    public DokterTeam getNamaDokter(String idDetailCheckup) {
        DokterTeam res = new DokterTeam();
        if(!"".equalsIgnoreCase(idDetailCheckup) && idDetailCheckup != null){
            String SQL = "SELECT \n" +
                    "a.id_dokter,\n" +
                    "b.nama_dokter,\n" +
                    "b.sip\n" +
                    "FROM it_simrs_dokter_team a\n" +
                    "INNER JOIN im_simrs_dokter b ON a.id_dokter = b.id_dokter\n" +
                    "WHERE a.id_detail_checkup = :id\n";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDetailCheckup)
                    .list();
            if(result.size() > 0){
                Object[] obj = result.get(0);
                res.setIdDokter(obj[0] == null ? "" : obj[0].toString());
                res.setNamaDokter(obj[1] == null ? "" : obj[1].toString());
                res.setSip(obj[2] == null ? "" : obj[2].toString());
            }
        }
        return res;
    }

    public List<ItSimrsDokterTeamEntity> cekRequestDokter(String idDetailCheckup){
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDokterTeamEntity.class);
        criteria.add(Restrictions.eq("idDetailCheckup", idDetailCheckup));
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ItSimrsDokterTeamEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    public List<DokterTeam> cekRequestDokterByIdDokter(String idDokter, String flagApprove){

        String query = "";
        if (flagApprove != null) {
            query = " AND dt.flag_approve = '" + flagApprove + "' ";
        }


        List<DokterTeam> listOfResult = new ArrayList<>();

        String sql ="SELECT dt.id_team_dokter,\n" +
                "dk.nama_dokter,\n" +
                "dt.id_dokter,\n" +
                "dt.id_detail_checkup,\n" +
                "dt.jenis_dpjp,\n" +
                "dt.flag_approve,\n" +
                "dt.keterangan,\n" +
                "pl.nama_pelayanan,\n" +
                "pl.tipe_pelayanan,\n" +
                "dt.created_date\n" +
                "FROM it_simrs_dokter_team dt\n" +
                "INNER JOIN im_simrs_dokter dk ON dk.id_dokter = dt.id_dokter\n" +
                "INNER JOIN it_simrs_header_detail_checkup dc ON dt.id_detail_checkup = dc.id_detail_checkup\n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "a.branch_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                ") pl ON pl.id_pelayanan = dc.id_pelayanan\n" +
                "WHERE dt.id_dokter = :id " + query ;
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setParameter("id", idDokter)
                .list();
        if (result.size() > 0){
            for (Object[] item : result) {
                DokterTeam dokterTeam = new DokterTeam();
                dokterTeam.setIdTeamDokter(item[0].toString());
                dokterTeam.setNamaDokter(item[1].toString());
                dokterTeam.setIdDokter(item[2].toString());
                dokterTeam.setIdDetailCheckup(item[3].toString());
                dokterTeam.setJenisDpjp(item[4] != null ? item[4].toString() : "");
                dokterTeam.setFlagApprove(item[5] != null ? item[5].toString() : "");
                dokterTeam.setKeterangan(item[6] != null ? item[6].toString() : "");
                dokterTeam.setNamaPelayanan(item[7].toString());
                dokterTeam.setTipePelayanan(item[8].toString());
                dokterTeam.setCreatedDate(Timestamp.valueOf(item[9].toString()));

                listOfResult.add(dokterTeam);
            }
        }
        return listOfResult;
    }
}
