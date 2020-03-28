package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ItSimrsReturObatDetailEntity;
import com.neurix.simrs.master.obat.model.Obat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReturObatDetailDao extends GenericDao<ItSimrsReturObatDetailEntity, String> {
    @Override
    protected Class<ItSimrsReturObatDetailEntity> getEntityClass() {
        return ItSimrsReturObatDetailEntity.class;
    }

    @Override
    public List<ItSimrsReturObatDetailEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsReturObatDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_retur_detail") != null){
                criteria.add(Restrictions.eq("idReturDetail", (String) mapCriteria.get("id_retur_detail")));
            }
            if (mapCriteria.get("id_retur_obat") != null){
                criteria.add(Restrictions.eq("idReturObat", (String) mapCriteria.get("id_retur_obat")));
            }
            if (mapCriteria.get("id_obat") != null){
                criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("id_barang") != null) {
                criteria.add(Restrictions.eq("idBarang", (String) mapCriteria.get("id_barang")));
            }
        }

        List<ItSimrsReturObatDetailEntity> results = criteria.list();

        return results;
    }

    public List<Obat> getListObatByJenisObat(String id, String branch) {

        String branchId = "%";
        if (branch != null && !"".equalsIgnoreCase(branch)) {
            branchId = branch;
        }

        String SQL = "SELECT \n" +
                "obat.id_obat,\n" +
                "obat.nama_obat,\n" +
                "obat.qty\n" +
                "FROM im_simrs_obat obat\n" +
                "INNER JOIN im_simrs_obat_gejala og ON og.id_obat = obat.id_obat\n" +
                "WHERE obat.qty is not null\n" +
                "AND obat.qty > 0\n" +
                "AND og.id_jenis_obat = :id\n" +
                "AND obat.branch_id LIKE :branch";

        List<Obat> obats = new ArrayList<>();

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .setParameter("branch", branchId)
                .list();

        if (!result.isEmpty() && result.size() > 0) {
            Obat obat;
            for (Object[] obj : result) {
                obat = new Obat();
                obat.setIdObat(obj[0].toString());
                obat.setNamaObat(obj[1].toString());
                obat.setQty((BigInteger) obj[2]);
                obats.add(obat);
            }
        }
        return obats;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_retur_obat_detail')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}