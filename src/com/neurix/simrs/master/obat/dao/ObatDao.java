package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsObatEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_obat")!=null) {
                criteria.add(Restrictions.eq("idObat", (String) mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("nama_jenis_obat")!=null) {
                criteria.add(Restrictions.ilike("namaObat", "%" + (String)mapCriteria.get("nama_jenis_obat") + "%"));
            }
            if (mapCriteria.get("id_jenis_obat")!=null) {
                criteria.add(Restrictions.eq("idJenisObat", (String) mapCriteria.get("id_jenis_obat")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idObat"));

        List<ImSimrsObatEntity> results = criteria.list();

        return results;
    }

    public List<Obat> getListObatByJenisObat(String id, String branch){

        String branchId = "%";
        if (branch != null && !"".equalsIgnoreCase(branch))
        {
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

        if (!result.isEmpty() && result.size() > 0)
        {
            Obat obat;
            for (Object[] obj : result){
                obat = new Obat();
                obat.setIdObat(obj[0].toString());
                obat.setNamaObat(obj[1].toString());
                obat.setQty((BigInteger) obj[2]);
                obats.add(obat);
            }
        }
        return obats;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<Obat> getJenisObat(Obat bean){

        String branchId = "%";
        String idObat   = "%";
        String idJenis  = "%";

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            idObat = bean.getIdObat();
        }

        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())){
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

        if (!result.isEmpty() && result.size() > 0)
        {
            Obat obat;
            for (Object[] obj : result){

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

    public List<ImSimrsObatEntity> getListObatByCriteria(Map criteria){

        String idObat = "%";
        String idJenisObat = "%";
        String namaObat = "%";
        String flag = "%";
        String branchId = "%";

        if (criteria.get("id_obat") != null){
            idObat = criteria.get("id_obat").toString();
        }
        if (criteria.get("id_jenis_obat") != null){
            idJenisObat = criteria.get("id_jenis_obat").toString();
        }
        if (criteria.get("nama_obat") != null){
            namaObat = "%" +criteria.get("nama_obat").toString()+ "%";
        }
        if (criteria.get("branch_id") != null){
            branchId = criteria.get("branch_id").toString();
        }
        if (criteria.get("flag") != null){
            flag = criteria.get("flag").toString();
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
                "ob.branch_id\n" +
                "FROM im_simrs_obat ob \n" +
                "INNER JOIN (\n" +
                "\tSELECT\n" +
                "\tob.id_obat\n" +
                "\tFROM im_simrs_obat ob\n" +
                "\tINNER JOIN (SELECT * FROM im_simrs_obat_gejala WHERE flag = 'Y') og ON og.id_obat = ob.id_obat\n" +
                "\tWHERE og.id_obat LIKE :idObat\n" +
                "\tAND og.id_jenis_obat LIKE :idJenisObat\n" +
                "\tAND ob.nama_obat LIKE :namaObat\n" +
                "\tAND ob.flag = :flag\n" +
                "\tAND ob.branch_id LIKE :branchId\n" +
                "\tGROUP BY ob.id_obat\n" +
                ") og ON og.id_obat = ob.id_obat";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idObat", idObat)
                .setParameter("idJenisObat", idJenisObat)
                .setParameter("flag", flag)
                .setParameter("branchId", branchId)
                .setParameter("namaObat", namaObat)
                .list();

        List<ImSimrsObatEntity> listOfResults = new ArrayList<>();
        ImSimrsObatEntity obatEntity;
        for (Object[] obj : results)
        {
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
            obatEntity.setBranchId( obj[10] == null ? "" : obj[10].toString());
            listOfResults.add(obatEntity);
        }
        return listOfResults;
    }
}