package com.neurix.simrs.master.jenisperiksapasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class JenisPeriksaPasienDao extends GenericDao<ImJenisPeriksaPasienEntity, String> {
    @Override
    protected Class<ImJenisPeriksaPasienEntity> getEntityClass() {
        return ImJenisPeriksaPasienEntity.class;
    }

    @Override
    public List<ImJenisPeriksaPasienEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImJenisPeriksaPasienEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_jenis_periksa_pasien") != null) {
                criteria.add(Restrictions.eq("idJenisPeriksaPasien", mapCriteria.get("id_jenis_periksa_pasien").toString()));
            }
            if (mapCriteria.get("except_bpjs") != null) {
                criteria.add(Restrictions.ne("idJenisPeriksaPasien", mapCriteria.get("except_bpjs").toString()));
            }
        }

        criteria.addOrder(Order.desc("keterangan"));
        List<ImJenisPeriksaPasienEntity> result = criteria.list();
        return result;
    }

    public List<JenisPriksaPasien> getListJenisPeriksaByidDetaiCheckup(String jenisPeriksa, String idDetailCheckup){
        List<JenisPriksaPasien> list = new ArrayList<>();
        if(!"".equalsIgnoreCase(jenisPeriksa) && jenisPeriksa != null &&
           !"".equalsIgnoreCase(idDetailCheckup) && idDetailCheckup != null){

            String SQL = "SELECT\n" +
                    "a.id_jenis_periksa_pasien,\n" +
                    "a.keterangan,\n" +
                    "a.master_id\n" +
                    "FROM(\n" +
                    "\tSELECT\n" +
                    "\ta.id_jenis_periksa_pasien,\n" +
                    "\ta.keterangan,\n" +
                    "\ta.master_id\n" +
                    "\tFROM(\n" +
                    "\t\tSELECT \n" +
                    "\t\ta.id_jenis_periksa_pasien,\n" +
                    "\t\ta.keterangan,\n" +
                    "\t\ta.master_id\n" +
                    "\t\tFROM im_simrs_jenis_periksa_pasien a\n" +
                    "\t\tWHERE a.id_jenis_periksa_pasien NOT IN (\n" +
                    "\t\tSELECT \n" +
                    "\t\tid_jenis_periksa_pasien_setelahnya\n" +
                    "\t\tFROM it_simrs_header_detail_checkup_log \n" +
                    "\t\tWHERE id_detail_checkup = :idDetail\n" +
                    "\t\t)\n" +
                    "\t)a \n" +
                    "\tWHERE a.id_jenis_periksa_pasien\n" +
                    "\tNOT IN ('paket_perusahaan','ptpn','paket_individu')\n" +
                    ")a\n" +
                    "WHERE a.id_jenis_periksa_pasien NOT LIKE :jenis";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idDetail", idDetailCheckup)
                    .setParameter("jenis", jenisPeriksa)
                    .list();
            if(result.size() > 0){
                for (Object[] obj: result){
                    JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
                    jenisPriksaPasien.setIdJenisPeriksaPasien(obj[0] != null ? obj[0].toString() : "");
                    jenisPriksaPasien.setKeterangan(obj[1] != null ? obj[1].toString() : "");
                    jenisPriksaPasien.setNoMaster(obj[2] != null ? obj[2].toString() : "");
                    list.add(jenisPriksaPasien);
                }

            }
        }
        return list;
    }

    public List<JenisPriksaPasien> getJenisPeriksaExcMCU() {
        List<JenisPriksaPasien> list = new ArrayList<>();
        String sql = "SELECT \n" +
                "id_jenis_periksa_pasien, \n" +
                "keterangan \n" +
                "FROM im_simrs_jenis_periksa_pasien \n" +
                "WHERE id_jenis_periksa_pasien != 'paket_perusahaan'";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
                .list();

        if (result.size() > 0) {
            for (Object[] obj: result){
                JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
                jenisPriksaPasien.setIdJenisPeriksaPasien(obj[0] != null ? obj[0].toString() : "");
                jenisPriksaPasien.setKeterangan(obj[1] != null ? obj[1].toString() : "");
                list.add(jenisPriksaPasien);
            }
        }

        return list;
    }
}
