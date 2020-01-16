package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ObatDao extends GenericDao<ImSimrsObatEntity, String> {
    @Override
    protected Class<ImSimrsObatEntity> getEntityClass() {
        return ImSimrsObatEntity.class;
    }

    @Override
    public List<ImSimrsObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsObatEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_seq_obat") != null){
                criteria.add(Restrictions.eq("idSeqObat", (String) mapCriteria.get("id_seq_obat")));
            }
            if (mapCriteria.get("id_obat") != null) {
                criteria.add(Restrictions.eq("idObat", (String) mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("nama_obat") != null) {
                criteria.add(Restrictions.ilike("namaObat", "%" + (String) mapCriteria.get("nama_obat") + "%"));
            }
            if (mapCriteria.get("id_jenis_obat") != null) {
                criteria.add(Restrictions.eq("idJenisObat", (String) mapCriteria.get("id_jenis_obat")));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("id_pabrik") != null) {
                criteria.add(Restrictions.eq("idPabrik", (String) mapCriteria.get("id_pabrik")));
            }

            if (mapCriteria.get("lembar_per_box") != null){
                criteria.add(Restrictions.eq("lembarPerBox", (BigInteger) mapCriteria.get("lembar_per_box")));
            }

            if (mapCriteria.get("biji_per_lembar") != null){
                criteria.add(Restrictions.eq("bijiPerLembar", (BigInteger) mapCriteria.get("biji_per_lembar")));
            }
            if (mapCriteria.get("exp_date") != null){
                criteria.add(Restrictions.eq("expiredDate", (String) mapCriteria.get("exp_date")));
            }

            criteria.add(Restrictions.ne("qtyBox", new BigInteger(String.valueOf(0))));
            criteria.add(Restrictions.ne("qtyLembar", new BigInteger(String.valueOf(0))));
            criteria.add(Restrictions.ne("qtyBiji", new BigInteger(String.valueOf(0))));

            if (mapCriteria.get("asc") != null){
                criteria.addOrder(Order.asc("createdDate"));
            } else if (mapCriteria.get("desc") != null){
                criteria.addOrder(Order.desc("createdDate"));
            } else {
                criteria.addOrder(Order.asc("idObat"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        List<ImSimrsObatEntity> results = criteria.list();

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
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public String getNextIdSeqObat() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_obat_seq')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<Obat> getJenisObat(Obat bean) {

        String branchId = "%";
        String idObat = "%";
        String idJenis = "%";

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            branchId = bean.getBranchId();
        }

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            idObat = bean.getIdObat();
        }

        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
            idJenis = bean.getIdJenisObat();
        }

        String SQL = "SELECT a.nama_jenis_obat, a.id_jenis_obat, c.id_obat, c.nama_obat\n" +
                "FROM im_simrs_jenis_obat a \n" +
                "INNER JOIN im_simrs_obat_gejala b ON a.id_jenis_obat = b.id_jenis_obat\n" +
                "INNER JOIN im_simrs_obat c ON b.id_obat = c.id_obat\n" +
                "WHERE c.id_obat LIKE :id\n" +
                "AND b.flag = 'Y'\n" +
                "AND a.id_jenis_obat LIKE :jenis\n" +
                "AND c.branch_id LIKE :branch";

        List<Obat> obats = new ArrayList<>();

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idObat)
                .setParameter("jenis", idJenis)
                .setParameter("branch", branchId)
                .list();

        if (!result.isEmpty() && result.size() > 0) {
            Obat obat;
            for (Object[] obj : result) {

                obat = new Obat();
                obat.setJenisObat(obj[0].toString());
                obat.setIdJenisObat(obj[1].toString());
                obat.setIdObat(obj[2].toString());
                obat.setNamaObat(obj[3].toString());
                obats.add(obat);
            }
        }
        return obats;
    }

    public List<ImSimrsObatEntity> getListObatByCriteria(Map criteria) {

        String idObat = "%";
        String idJenisObat = "%";
        String idPabrik = "%";
        String namaObat = "%";
        String flag = "%";
        String branchId = "%";

        if (criteria.get("id_obat") != null) {
            idObat = criteria.get("id_obat").toString();
        }
        if (criteria.get("id_jenis_obat") != null) {
            idJenisObat = criteria.get("id_jenis_obat").toString();
        }
        if (criteria.get("nama_obat") != null) {
            namaObat = "%" + criteria.get("nama_obat").toString() + "%";
        }
        if (criteria.get("branch_id") != null) {
            branchId = criteria.get("branch_id").toString();
        }
        if (criteria.get("flag") != null) {
            flag = criteria.get("flag").toString();
        }
        if (criteria.get("id_pabrik") != null) {
            idPabrik = criteria.get("id_pabrik").toString();
        }

        String SQL = "SELECT\n" +
                "ob.id_obat,\n" +
                "ob.nama_obat,\n" +
                "ob.harga,\n" +
                "ob.flag,\n" +
                "ob.action,\n" +
                "ob.created_date,\n" +
                "ob.created_who,\n" +
                "ob.last_update,\n" +
                "ob.last_update_who,\n" +
                "ob.qty,\n" +
                "ob.branch_id,\n" +
                "ob.id_pabrik,\n" +
                "ob.merk,\n" +
                "ob.qty_box,\n" +
                "ob.lembar_per_box,\n" +
                "ob.qty_lembar,\n" +
                "ob.biji_per_lembar,\n" +
                "ob.qty_biji,\n" +
                "ob.average_harga_box,\n" +
                "ob.average_harga_lembar,\n" +
                "ob.average_harga_biji\n" +
                "FROM im_simrs_obat ob \n" +
                "INNER JOIN (\n" +
                "\tSELECT\n" +
                "\tob.id_obat\n" +
                "\tFROM im_simrs_obat ob\n" +
                "\tINNER JOIN (SELECT * FROM im_simrs_obat_gejala WHERE flag = 'Y') og ON og.id_obat = ob.id_obat\n" +
                "\tWHERE og.id_obat LIKE :idObat\n" +
                "\tAND og.id_jenis_obat LIKE :idJenisObat\n" +
                "\tAND ob.nama_obat LIKE :namaObat\n" +
                "\tAND ob.flag LIKE :flag\n" +
                "\tAND ob.branch_id LIKE :branchId\n" +
                "\tAND ob.id_pabrik LIKE :idPabrik\n" +
                "\tGROUP BY ob.id_obat\n" +
                ") og ON og.id_obat = ob.id_obat";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idObat", idObat)
                .setParameter("idJenisObat", idJenisObat)
                .setParameter("flag", flag)
                .setParameter("branchId", branchId)
                .setParameter("idPabrik", idPabrik)
                .setParameter("namaObat", namaObat)
                .list();

        List<ImSimrsObatEntity> listOfResults = new ArrayList<>();
        ImSimrsObatEntity obatEntity;
        for (Object[] obj : results) {
            obatEntity = new ImSimrsObatEntity();
            obatEntity.setIdObat(obj[0].toString());
            obatEntity.setNamaObat(obj[1] == null ? "" : obj[1].toString());
            obatEntity.setHarga(obj[2] == null ? null : (BigInteger) obj[2]);
            obatEntity.setFlag(obj[3].toString());
            obatEntity.setAction(obj[4].toString());
            obatEntity.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
            obatEntity.setCreatedWho(obj[6] == null ? null : obj[6].toString());
            obatEntity.setLastUpdate(obj[7] == null ? null : (Timestamp) obj[7]);
            obatEntity.setLastUpdateWho(obj[8] == null ? null : obj[8].toString());
            obatEntity.setQty(obj[9] == null ? null : (BigInteger) obj[9]);
            obatEntity.setBranchId(obj[10] == null ? "" : obj[10].toString());
            obatEntity.setIdPabrik(obj[11] == null ? null : obj[11].toString());
            obatEntity.setMerk(obj[12] == null ? null : obj[12].toString());
            obatEntity.setQtyBox(obj[13] == null ? null : (BigInteger) obj[13]);
            obatEntity.setLembarPerBox(obj[14] == null ? null : (BigInteger) obj[14]);
            obatEntity.setQtyLembar(obj[15] == null ? null : (BigInteger) obj[15]);
            obatEntity.setBijiPerLembar(obj[16] == null ? null : (BigInteger) obj[16]);
            obatEntity.setQtyBiji(obj[17] == null ? null : (BigInteger) obj[17]);
            obatEntity.setAverageHargaBox(obj[18] == null ? null : (BigDecimal) obj[18]);
            obatEntity.setAverageHargaLembar(obj[19] == null ? null : (BigDecimal) obj[19]);
            obatEntity.setAverageHargaBiji(obj[20] == null ? null : (BigDecimal) obj[20]);

            listOfResults.add(obatEntity);
        }
        return listOfResults;
    }

    public List<Obat> getListNamaObat(Obat bean) {

        String namaObat = "%";
        String branchId = "%";

        if (bean.getNamaObat() != null && !"".equalsIgnoreCase(bean.getNamaObat())) {
            namaObat = "%" + bean.getNamaObat() + "%";
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            branchId = bean.getBranchId();
        }

        String SQL = "SELECT id_obat, nama_obat FROM im_simrs_obat\n" +
                "WHERE nama_obat LIKE :namaObat AND branch_id = :branchId\n AND flag = 'Y'" +
                "ORDER BY nama_obat ASC";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("namaObat", namaObat)
                .setParameter("branchId", branchId)
                .list();

        List<Obat> listOfResults = new ArrayList<>();
        Obat obatEntity;
        for (Object[] obj : results) {
            obatEntity = new Obat();
            obatEntity.setIdObat(obj[0].toString());
            obatEntity.setNamaObat(obj[1] == null ? "" : obj[1].toString());
            listOfResults.add(obatEntity);
        }
        return listOfResults;
    }

    public Obat getSumStockObatGudangById(String id){

        String SQL = "SELECT \n" +
                "id_obat, \n" +
                "SUM(qty_box) as qty_box, \n" +
                "SUM(qty_lembar) as qty_lembar,\n" +
                "SUM(qty_biji) as qty_biji\n" +
                "FROM im_simrs_obat \n" +
                "WHERE (qty_box, qty_lembar, qty_biji) != ('0','0','0')\n" +
                "AND id_obat = :id\n" +
                "GROUP BY id_obat";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();

        Obat obat = new Obat();
        if (results.size() > 0){
            for (Object[] obj : results){
                obat.setIdObat(obj[0].toString());
                obat.setQtyBox(new BigInteger(String.valueOf(obj[1])));
                obat.setQtyLembar(new BigInteger(String.valueOf(obj[2])));
                obat.setQtyBiji(new BigInteger(String.valueOf(obj[3])));
            }
        }

        return obat;
    }
}