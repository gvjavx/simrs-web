package com.neurix.simrs.transaksi.plankegiatanrawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.ItSimrsPlanKegiatanRawatEntity;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.PlanKegiatanRawat;
import com.neurix.simrs.transaksi.transaksitindakanbpjs.model.ItSimrsTindakanBpjsEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 06/03/20.
 */
public class PlanKegiatanRawatDao extends GenericDao<ItSimrsPlanKegiatanRawatEntity, String> {
    @Override
    protected Class<ItSimrsPlanKegiatanRawatEntity> getEntityClass() {
        return ItSimrsPlanKegiatanRawatEntity.class;
    }

    @Override
    public List<ItSimrsPlanKegiatanRawatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPlanKegiatanRawatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        if (mapCriteria.get("branch_id") != null)
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        if (mapCriteria.get("tgl_mulai") != null)
            criteria.add(Restrictions.eq("tglMulai", (Date) mapCriteria.get("tgl_mulai")));

        List<ItSimrsPlanKegiatanRawatEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_plan_kegiatan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<PlanKegiatanRawat> getSearchPlanKegiataRawat(PlanKegiatanRawat bean){

        String idPasien = "%";
        String idDetailCheckup = "%";
        String idPelayanan = "%";
        String branchId = "%";
        if (bean.getIdPasien() != null){
            idPasien = bean.getIdPasien();
        }
        if (bean.getIdDetailCheckup() != null){
            idDetailCheckup = bean.getIdDetailCheckup();
        }
        if (bean.getIdPelayanan() != null){
            idPelayanan = bean.getIdPelayanan();
        }
        if (bean.getBranchId() != null){
            branchId = bean.getBranchId();
        }

        String SQL = "SELECT \n" +
                "dt.id_detail_checkup,\n" +
                "dt.created_date as tgl_masuk,\n" +
                "ps.nama,\n" +
                "pl.nama_pelayanan, \n" +
                "ds.keterangan_diagnosa\n" +
                "FROM it_simrs_header_detail_checkup dt\n" +
                "INNER JOIN im_simrs_pelayanan pl ON pl.id_pelayanan = dt.id_pelayanan\n" +
                "INNER JOIN it_simrs_header_checkup hd ON hd.no_checkup = dt.no_checkup\n" +
                "INNER JOIN im_simrs_pasien ps ON ps.id_pasien = hd.id_pasien\n" +
                "LEFT JOIN \n" +
                "(\n" +
                "\tSELECT \n" +
                "\ta.id_detail_checkup,\n" +
                "\ta.keterangan_diagnosa\n" +
                "\tFROM\n" +
                "\tit_simrs_diagnosa_rawat a\n" +
                "\tINNER JOIN \n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\tid_detail_checkup,\n" +
                "\t\tMAX(created_date) as created_date\n" +
                "\t\tFROM it_simrs_diagnosa_rawat\n" +
                "\t\tGROUP BY id_detail_checkup\n" +
                "\t) b ON b.id_detail_checkup = a.id_detail_checkup AND b.created_date = a.created_date\n" +
                ") ds ON ds.id_detail_checkup = dt.id_detail_checkup\n" +
                "WHERE\n" +
                "dt.status_periksa != '3'\n" +
                "AND pl.id_pelayanan LIKE :poli \n" +
                "AND hd.branch_id = :unit \n" +
                "AND dt.id_detail_checkup LIKE :idDetailCheckup \n" +
                "AND hd.id_pasien LIKE :pasien ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("poli", idPelayanan)
                .setParameter("unit", branchId)
                .setParameter("pasien", idPasien)
                .setParameter("idDetailCheckup", idDetailCheckup)
                .list();

        List<PlanKegiatanRawat> planKegiatanRawats = new ArrayList<>();
        if (results.size() > 0){
            PlanKegiatanRawat plan;
            for (Object[] obj : results){
                plan = new PlanKegiatanRawat();
                plan.setIdDetailCheckup(obj[0].toString());
                plan.setCreatedDate((Timestamp) obj[1]);
                plan.setNamaPasien(obj[2].toString());
                plan.setNamaPelayanan(obj[3].toString());
                plan.setDiagnosa(obj[4] == null ? "" : obj[4].toString());
                plan.setStCreatedDate(obj[1].toString());
                planKegiatanRawats.add(plan);
            }
        }

        return planKegiatanRawats;
    }
}
