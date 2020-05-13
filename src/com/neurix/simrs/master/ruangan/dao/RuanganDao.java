package com.neurix.simrs.master.ruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
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

public class RuanganDao extends GenericDao<MtSimrsRuanganEntity, String> {
    @Override
    protected Class<MtSimrsRuanganEntity> getEntityClass() {
        return MtSimrsRuanganEntity.class;
    }

    @Override
    public List<MtSimrsRuanganEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRuanganEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_ruangan") != null) {
                criteria.add(Restrictions.eq("idRuangan", (String) mapCriteria.get("id_ruangan")));
            }
            if (mapCriteria.get("nama_ruangan") != null) {
                criteria.add(Restrictions.ilike("namaRuangan", "%" + (String) mapCriteria.get("nama_ruangan") + "%"));
            }
            if (mapCriteria.get("id_kelas_ruangan") != null) {
                criteria.add(Restrictions.eq("idKelasRuangan", (String) mapCriteria.get("id_kelas_ruangan")));
            }
            if (mapCriteria.get("status_ruangan") != null) {
                criteria.add(Restrictions.eq("statusRuangan", (String) mapCriteria.get("status_ruangan")));
            }
            if (mapCriteria.get("no_ruangan") != null) {
                criteria.add(Restrictions.eq("noRuangan", (String) mapCriteria.get("no_ruangan")));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("id_kelas_ruangan") != null) {
                criteria.add(Restrictions.eq("idKelasRuangan", (String) mapCriteria.get("id_kelas_ruangan")));
            }
            if (mapCriteria.get("status_ruangan") != null) {
                criteria.add(Restrictions.eq("statusRuangan", (String) mapCriteria.get("status_ruangan")));
            }

            if (mapCriteria.get("tarif") != null) {
                criteria.add(Restrictions.eq("tarif", (BigInteger) mapCriteria.get("tarif")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            if (mapCriteria.get("sisa_kuota") != null) {
                criteria.add(Restrictions.gt("sisaKuota", Integer.parseInt(mapCriteria.get("sisa_kuota").toString())));
            }

        }

        // Order by
        criteria.addOrder(Order.asc("idRuangan"));

        List<MtSimrsRuanganEntity> results = criteria.list();

        return results;
    }

    public String getNextIdRuangan() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_ruangan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<Ruangan> getListRuangan(Ruangan bean) {

        String idRuang = "%";
        String idkelas = "%";
        String namaRuang = "%";

        List<Ruangan> ruanganList = new ArrayList<>();

        if (bean != null) {
            if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
                idRuang = bean.getIdRuangan();
            }

            if (bean.getNamaRuangan() != null && !"".equalsIgnoreCase(bean.getNamaRuangan())) {
                namaRuang = bean.getNamaRuangan();
            }

            if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())) {
                idkelas = bean.getIdKelasRuangan();
            }

            String SQL = "SELECT \n" +
                    "a.id_kelas_ruangan, \n" +
                    "b.id_ruangan, \n" +
                    "b.nama_ruangan, \n" +
                    "b.no_ruangan, \n" +
                    "b.status_ruangan,\n" +
                    "c.id_detail_checkup, \n" +
                    "c.tgl_masuk, \n" +
                    "b.sisa_kuota, \n" +
                    "b.kuota \n" +
                    "FROM im_simrs_kelas_ruangan a\n" +
                    "INNER JOIN mt_simrs_ruangan b ON a.id_kelas_ruangan = b.id_kelas_ruangan\n" +
                    "LEFT JOIN (\n" +
                    "SELECT * \n" +
                    "FROM it_simrs_rawat_inap a\n" +
                    "INNER JOIN it_simrs_header_checkup b \n" +
                    "ON a.no_checkup = b.no_checkup\n" +
                    "WHERE a.flag = 'Y'\n" +
                    "AND b.branch_id LIKE :branchId) c On b.id_ruangan = c.id_ruangan\n" +
                    "WHERE a.id_kelas_ruangan LIKE :idKelas\n" +
                    "AND b.id_ruangan LIKE :idRuang\n" +
                    "AND b.nama_ruangan LIKE :namaRuang\n" +
                    "ORDER BY a.id_kelas_ruangan ASC";

            List<Object[]> results = new ArrayList<>();

            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idKelas", idkelas)
                    .setParameter("idRuang", idRuang)
                    .setParameter("namaRuang", namaRuang)
                    .setParameter("branchId", CommonUtil.userBranchLogin())
                    .list();

            if (results != null) {

                Ruangan ruangan;
                for (Object[] obj : results) {

                    ruangan = new Ruangan();
                    ruangan.setIdKelasRuangan(obj[0] == null ? "" : obj[0].toString());
                    ruangan.setIdRuangan(obj[1] == null ? "" : obj[1].toString());
                    ruangan.setNamaRuangan(obj[2] == null ? "" : obj[2].toString());
                    ruangan.setNoRuangan(obj[3] == null ? "" : obj[3].toString());
                    ruangan.setStatusRuangan(obj[4] == null ? "" : obj[4].toString());
                    ruangan.setIdDetailCheckup(obj[5] == null ? "" : obj[5].toString());
                    ruangan.setTglMasuk(obj[6] == null ? "" : obj[6].toString());

                    if(obj[7] != null){
                        ruangan.setSisaKuota((Integer) obj[7]);
                    }
                    if(obj[8] != null){
                        ruangan.setKuota((Integer) obj[8]);
                    }

                    ruanganList.add(ruangan);

                }
            }
        }

        return ruanganList;
    }

    public List<MtSimrsRuanganEntity> getDataPelayanan(String namaRuangan) throws HibernateException {
        List<MtSimrsRuanganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRuanganEntity.class)
                .add(Restrictions.eq("namaRuangan", namaRuangan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}