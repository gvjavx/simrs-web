package com.neurix.simrs.master.ruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganTempatTidurEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.ruangan.model.TempatTidur;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TempatTidurDao extends GenericDao<MtSimrsRuanganTempatTidurEntity, String> {

    @Override
    protected Class<MtSimrsRuanganTempatTidurEntity> getEntityClass() {
        return MtSimrsRuanganTempatTidurEntity.class;
    }

    @Override
    public List<MtSimrsRuanganTempatTidurEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRuanganTempatTidurEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_tempat_tidur") != null) {
                criteria.add(Restrictions.eq("idTempatTidur", (String) mapCriteria.get("id_tempat_tidur")));
            }
            if (mapCriteria.get("nama_tempat_tidur") != null) {
                criteria.add(Restrictions.ilike("namaTempatTidur", "%" + (String) mapCriteria.get("nama_tempat_tidur") + "%"));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            if (mapCriteria.get("status") != null) {
                criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
            }
        }
        criteria.addOrder(Order.asc("idTempatTidur"));
        List<MtSimrsRuanganTempatTidurEntity> results = criteria.list();
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tempat_tidur')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "RTT"+sId;
    }

    public List<TempatTidur> getDataTempatTidur(TempatTidur bean){
        List<TempatTidur> tidurList = new ArrayList<>();
        String flag = "Y";
        String condition = "";
        if(bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            flag = bean.getFlag();
        }
        if(bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())){
            condition = "AND a.id_kelas_ruangan = '"+bean.getIdKelasRuangan()+"' \n";
        }
        if(bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            condition = condition + "AND b.id_ruangan = '"+bean.getIdRuangan()+"' \n";
        }
        if(bean.getIdTempatTidur() != null && !"".equalsIgnoreCase(bean.getIdTempatTidur())){
            condition = condition + "AND c.id_tempat_tidur = '"+bean.getIdTempatTidur()+"' \n";
        }
        if(bean.getNamaTempatTidur() != null && !"".equalsIgnoreCase(bean.getNamaTempatTidur())){
            condition = condition + "AND c.nama_tempat_tidur ILIKE '%"+bean.getNamaTempatTidur()+"%' \n";
        }
        if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            condition = condition + "AND b.branch_id = '"+bean.getBranchId()+"' \n";
        }
        String SQL = "SELECT \n" +
                "a.id_kelas_ruangan,\n" +
                "a.nama_kelas_ruangan,\n" +
                "b.id_ruangan,\n" +
                "b.no_ruangan,\n" +
                "b.nama_ruangan,\n" +
                "b.tarif,\n" +
                "c.id_tempat_tidur,\n" +
                "c.nama_tempat_tidur,\n" +
                "c.status,\n" +
                "b.position_id,\n" +
                "d.kodering\n" +
                "FROM im_simrs_kelas_ruangan a\n" +
                "INNER JOIN mt_simrs_ruangan b ON a.id_kelas_ruangan = b.id_kelas_ruangan\n" +
                "INNER JOIN mt_simrs_ruangan_tempat_tidur c ON b.id_ruangan = c.id_ruangan\n" +
                "LEFT JOIN im_position d ON d.position_id = b.position_id\n" +
                "WHERE c.flag = :flag \n" + condition;
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("flag", flag)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                TempatTidur tempatTidur = new TempatTidur();
                tempatTidur.setIdKelasRuangan(obj[0] != null ? obj[0].toString() : null);
                tempatTidur.setNamaKelasRuangan(obj[1] != null ? obj[1].toString() : null);
                tempatTidur.setIdRuangan(obj[2] != null ? obj[2].toString() : null);
                tempatTidur.setNoRuangan(obj[3] != null ? obj[3].toString() : null);
                tempatTidur.setNamaRuangan(obj[4] != null ? obj[4].toString() : null);
                tempatTidur.setTarif(obj[5] != null ? (BigInteger)obj[5] : null);
                tempatTidur.setIdTempatTidur(obj[6] != null ? obj[6].toString() : null);
                tempatTidur.setNamaTempatTidur(obj[7] != null ? obj[7].toString() : null);
                tempatTidur.setStatus(obj[8] != null ? obj[8].toString() : null);
                tempatTidur.setPositionId(obj[9] != null ? obj[9].toString() : null);
                tempatTidur.setKodering(obj[10] != null ? obj[10].toString() : null);
                tidurList.add(tempatTidur);
            }
        }
        return tidurList;
    }

    public List<Ruangan> getComboRuangan(Ruangan bean) {
        List<Ruangan> ruanganList = new ArrayList<>();
        String branch = "%";
        String idKelasRuangan = "%";
        if(bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())){
            idKelasRuangan = bean.getIdKelasRuangan();
        }
        if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branch = bean.getBranchId();
        }
        String SQL = "SELECT\n" +
                "a.id_kelas_ruangan,\n" +
                "a.nama_kelas_ruangan,\n" +
                "b.id_ruangan,\n" +
                "b.no_ruangan,\n" +
                "b.nama_ruangan\n" +
                "FROM im_simrs_kelas_ruangan a\n" +
                "INNER JOIN mt_simrs_ruangan b ON a.id_kelas_ruangan = b.id_kelas_ruangan\n" +
                "WHERE b.branch_id LIKE :branch\n" +
                "AND a.flag = 'Y'\n" +
                "AND a.id_kelas_ruangan LIKE :idKelas\n";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branch", branch)
                .setParameter("idKelas", idKelasRuangan)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                Ruangan ruangan = new Ruangan();
                ruangan.setIdKelasRuangan(obj[0] != null ? obj[0].toString() : null);
                ruangan.setNamaKelasRuangan(obj[1] != null ? obj[1].toString() : null);
                ruangan.setIdRuangan(obj[2] != null ? obj[2].toString() : null);
                ruangan.setNoRuangan(obj[3] != null ? obj[3].toString() : null);
                ruangan.setNamaRuangan(obj[4] != null ? obj[4].toString() : null);
                ruanganList.add(ruangan);
            }
        }
        return ruanganList;
    }

    public List<MtSimrsRuanganTempatTidurEntity> cekDataTT(String namaTt, String idRuangan) throws HibernateException {
        List<MtSimrsRuanganTempatTidurEntity> results = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRuanganTempatTidurEntity.class)
                .add(Restrictions.ilike("namaTempatTidur", namaTt))
                .add(Restrictions.eq("idRuangan", idRuangan))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}