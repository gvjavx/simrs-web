package com.neurix.simrs.master.obat.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
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
            if (mapCriteria.get("id_seq_obat") != null) {
                criteria.add(Restrictions.eq("idSeqObat", (String) mapCriteria.get("id_seq_obat")));
            }
            if (mapCriteria.get("id_barang") != null) {
                criteria.add(Restrictions.eq("idBarang", mapCriteria.get("id_barang")));
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

            if (mapCriteria.get("lembar_per_box") != null) {
                criteria.add(Restrictions.eq("lembarPerBox", (BigInteger) mapCriteria.get("lembar_per_box")));
            }

            if (mapCriteria.get("biji_per_lembar") != null) {
                criteria.add(Restrictions.eq("bijiPerLembar", (BigInteger) mapCriteria.get("biji_per_lembar")));
            }
            if (mapCriteria.get("exp_date") != null) {
                criteria.add(Restrictions.eq("expiredDate", (Date) mapCriteria.get("exp_date")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }

            if (mapCriteria.get("asc") != null) {
                criteria.addOrder(Order.asc("createdDate"));
            } else if (mapCriteria.get("desc") != null) {
                criteria.addOrder(Order.desc("createdDate"));
            } else if (mapCriteria.get("exp") != null) {
                criteria.addOrder(Order.asc("expiredDate"));
            } else {
                criteria.addOrder(Order.asc("idObat"));
            }
        }


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
        String squen = "%";

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
        if (criteria.get("id_squen") != null) {
            squen = criteria.get("id_squen").toString();
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
                "ob.average_harga_biji,\n" +
                "ob.expired_date,\n" +
                "ob.id_barang, ob.id_seq_obat\n" +
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
                ") og ON og.id_obat = ob.id_obat ORDER BY ob.expired_date ASC";

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
            obatEntity.setExpiredDate(obj[21] == null ? null : (Date) obj[21]);
            obatEntity.setIdBarang(obj[22] == null ? null : (String) obj[22]);
            obatEntity.setIdSeqObat(obj[23] == null ? null : (String) obj[23]);

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

    public Obat getSumStockObatGudangById(String id, String ket) {

        Obat obat = new Obat();

        if ("stok".equalsIgnoreCase(ket)) {

            String SQL = "SELECT \n" +
                    "id_obat, \n" +
                    "SUM(qty_box) as qty_box, \n" +
                    "SUM(qty_lembar) as qty_lembar,\n" +
                    "SUM(qty_biji) as qty_biji\n" +
                    "FROM im_simrs_obat \n" +
                    "WHERE (qty_box, qty_lembar, qty_biji) != ('0','0','0')\n" +
                    "AND id_obat = :id\n" +
                    "AND branch_id = :branchId\n" +
                    "GROUP BY id_obat";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .setParameter("branchId", CommonUtil.userBranchLogin())
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    obat.setIdObat(obj[0].toString());
                    obat.setQtyBox(new BigInteger(String.valueOf(obj[1])));
                    obat.setQtyLembar(new BigInteger(String.valueOf(obj[2])));
                    obat.setQtyBiji(new BigInteger(String.valueOf(obj[3])));
                }
            }

        } else {

            //stok obat digudang ditambah dengan stok di apotek
            String SQLMaster = "SELECT  \n" +
                    "\tid_obat,  \n" +
                    "\tSUM(qty_box) as qty_box,  \n" +
                    "\tSUM(qty_lembar) as qty_lembar, \n" +
                    "\tSUM(qty_biji) as qty_biji \n" +
                    "\tFROM im_simrs_obat\n" +
                    "\tWHERE (qty_box, qty_lembar, qty_biji) != (0,0,0) \n" +
                    "\tAND id_obat = :id1\n" +
                    "\tAND branch_id = :branchId1\n" +
                    "\tGROUP BY id_obat";

            String SQLPoli = "SELECT \n" +
                    "\tid_obat, \n" +
                    "\tSUM(qty_box) as jml_box, \n" +
                    "\tSUM(qty_lembar) as jml_lembar, \n" +
                    "\tSUM(qty_biji) as jml_biji\n" +
                    "\tFROM mt_simrs_obat_poli\n" +
                    "\tWHERE (qty_box, qty_lembar, qty_biji) != (0,0,0) \n" +
                    "\tAND id_obat = :id2\n" +
                    "\tAND branch_id = :branchId2\n" +
                    "\tGROUP BY id_obat";

            List<Object[]> results1 = this.sessionFactory.getCurrentSession().createSQLQuery(SQLMaster)
                    .setParameter("id1", id)
                    .setParameter("branchId1", CommonUtil.userBranchLogin())
                    .list();

            List<Object[]> results2 = this.sessionFactory.getCurrentSession().createSQLQuery(SQLPoli)
                    .setParameter("id2", id)
                    .setParameter("branchId2", CommonUtil.userBranchLogin())
                    .list();

            String idObat = "";
            BigInteger totalBox1 = new BigInteger(String.valueOf(0));
            BigInteger totalLembar1 = new BigInteger(String.valueOf(0));
            BigInteger totalBiji1 = new BigInteger(String.valueOf(0));

            if (results1.size() > 0) {
                for (Object[] obj : results1) {
                    idObat = obj[0].toString();
                    if(obj[1] != null){
                        totalBox1 = new BigInteger(obj[1].toString());
                    }
                    if(obj[2] != null){
                        totalLembar1 = new BigInteger(obj[2].toString());
                    }
                    if(obj[3] != null){
                        totalBiji1 = new BigInteger(obj[3].toString());
                    }
                }
            }

            BigInteger totalBox2 = new BigInteger(String.valueOf(0));
            BigInteger totalLembar2 = new BigInteger(String.valueOf(0));
            BigInteger totalBiji2 = new BigInteger(String.valueOf(0));

            if (results2.size() > 0) {
                for (Object[] obj : results2) {
                    if(obj[1] != null){
                        totalBox2 = new BigInteger(obj[1].toString());
                    }
                    if(obj[2] != null){
                        totalLembar2 = new BigInteger(obj[2].toString());
                    }
                    if(obj[3] != null){
                        totalBiji2 = new BigInteger(obj[3].toString());
                    }
                }
            }

            if(!"".equalsIgnoreCase(idObat)){
                obat.setIdObat(idObat);
                obat.setQtyBox(totalBox1.add(totalBox2));
                obat.setQtyLembar(totalLembar1.add(totalLembar2));
                obat.setQtyBiji(totalBiji1.add(totalBiji2));
            }
        }

        return obat;
    }

    public Obat getLastIdSeqObat(String idObat) {

        String SQL = "SELECT id_seq_obat, created_date\n" +
                "FROM im_simrs_obat\n" +
                "WHERE id_obat = :id\n" +
                "GROUP BY id_seq_obat\n" +
                "ORDER BY created_date desc\n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idObat)
                .list();

        Obat obat = new Obat();
        if (results.size() > 0) {
            for (Object[] obj : results) {
                obat.setIdSeqObat((String) obj[0]);
                obat.setCreatedDate((Timestamp) obj[1]);
            }
        }

        return obat;
    }

    public List<String> getListIdObatGroupByBranchId(String branchId) {

        String SQL = "SELECT id_obat, id_pabrik\n" +
                "FROM im_simrs_obat \n" +
                "WHERE branch_id = :id\n" +
                "AND flag = 'Y'\n" +
                "GROUP BY id_obat, id_pabrik";

        List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", branchId)
                .list();

        List<String> list = new ArrayList<>();
        if (resuts.size() > 0) {
            for (Object[] obj : resuts) {
                list.add(obj[0].toString());
            }
        }

        return list;
    }

    public List<Obat> getListObatGroup(Obat bean) {

        List<Obat> list = new ArrayList<>();
        String idPabrik = "%";
        String idJenis = "%";
        String idObat = "%";
        String branchId = "%";
        String flag = "%";

        if (bean != null) {

            if (bean.getIdPabrik() != null && !"".equalsIgnoreCase(bean.getIdPabrik())) {
                idPabrik = bean.getIdPabrik();
            }
            if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
                idJenis = bean.getIdJenisObat();
            }
            if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
                idObat = bean.getIdObat();
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                flag = bean.getFlag();
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            String SQL = "SELECT \n" +
                    "a.id_pabrik,\n" +
                    "a.id_obat,\n" +
                    "a.nama_obat, \n" +
                    "a.lembar_per_box, \n" +
                    "a.biji_per_lembar, \n" +
                    "SUM(a.qty_box) as box,\n" +
                    "SUM(a.qty_lembar) as lembar, \n" +
                    "SUM(a.qty_biji) as biji, \n" +
                    "a.merk, \n" +
                    "a.flag, \n" +
                    "a.min_stok \n" +
                    "FROM im_simrs_obat a\n" +
                    "INNER JOIN (SELECT id_obat FROM im_simrs_obat_gejala WHERE id_jenis_obat LIKE :idJenis GROUP BY id_obat) b ON a.id_obat = b.id_obat\n" +
                    "WHERE a.branch_id LIKE :branchId\n" +
                    "AND a.id_pabrik LIKE :idPabrik\n" +
                    "AND a.id_obat LIKE :idObat\n" +
                    "AND a.flag LIKE :flag\n" +
                    "GROUP BY \n" +
                    "a.id_pabrik,\n" +
                    "a.id_obat,\n" +
                    "a.nama_obat,\n" +
                    "a.lembar_per_box,\n" +
                    "a.biji_per_lembar," +
                    "a.merk,\n" +
                    "a.flag, a.min_stok\n";

            List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branchId", branchId)
                    .setParameter("idPabrik", idPabrik)
                    .setParameter("idObat", idObat)
                    .setParameter("flag", flag)
                    .setParameter("idJenis", idJenis)
                    .list();

            if (resuts.size() > 0) {
                for (Object[] obj : resuts) {
                    Obat obat = new Obat();
                    obat.setIdPabrik(obj[0] == null ? "" : obj[0].toString());
                    obat.setIdObat(obj[1] == null ? "" : obj[1].toString());
                    obat.setNamaObat(obj[2] == null ? "" : obj[2].toString());
                    obat.setLembarPerBox(obj[3] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[3].toString()));
                    obat.setBijiPerLembar(obj[4] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[4].toString()));
                    obat.setQtyBox(obj[5] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[5].toString()));
                    obat.setQtyLembar(obj[6] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[6].toString()));
                    obat.setQtyBiji(obj[7] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[7].toString()));
                    obat.setMerk(obj[8] == null ? "" : obj[8].toString());
                    obat.setFlag(obj[9] == null ? "" : obj[9].toString());
                    obat.setMinStok(obj[10] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(String.valueOf(obj[10].toString())));
                    obat.setJenisObat(getObatGejalaByIdObat(obj[1].toString()));

                    if (obat.getQtyBox() != null && obat.getMinStok() != null) {
                        if (obat.getQtyBox().intValue() >= obat.getMinStok().intValue()) {
                            obat.setIsMinStok("N");
                        } else {
                            obat.setIsMinStok("Y");
                        }
                    }

                    list.add(obat);
                }
            }

        }
        return list;
    }

    private String getObatGejalaByIdObat(String idObat) {

        String jenisObat = "";

        if (idObat != null && !"".equalsIgnoreCase(idObat)) {
            String SQL = "SELECT \n" +
                    "a.id_obat_gejala, \n" +
                    "a.id_obat, \n" +
                    "b.nama_jenis_obat \n" +
                    "FROM im_simrs_obat_gejala a\n" +
                    "INNER JOIN im_simrs_jenis_obat b ON a.id_jenis_obat = b.id_jenis_obat\n" +
                    "WHERE a.id_obat = :id AND a.flag = 'Y' ";

            List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idObat)
                    .list();

            if (resuts.size() > 0) {
                for (Object[] obj : resuts) {
                    StringBuilder addedScript = new StringBuilder();
                    if (obj[2] != null) {
                        jenisObat = jenisObat + addedScript.append("<label class=\"label label-primary\">" + obj[2].toString() + "</label>");
                    }
                }
            }
        }
        return jenisObat;
    }

    public List<Obat> getListObatDetail(Obat bean) {

        List<Obat> list = new ArrayList<>();
        String idBarang = "%";
        String idObat = "%";
        String branchId = "%";

        if (bean != null) {

            if (bean.getIdBarang() != null && !"".equalsIgnoreCase(bean.getIdBarang())) {
                idBarang = bean.getIdBarang();
            }
            if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
                idObat = bean.getIdObat();
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            String SQL = "SELECT id_barang, \n" +
                    "expired_date, \n" +
                    "id_obat, \n" +
                    "nama_obat, \n" +
                    "qty_box, \n" +
                    "qty_lembar, \n" +
                    "qty_biji\n" +
                    "FROM im_simrs_obat\n" +
                    "WHERE id_obat LIKE :idObat\n" +
                    "AND id_barang LIKE :idBarang\n" +
                    "AND branch_id LIKE :branchId\n ORDER BY expired_date ASC";

            List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branchId", branchId)
                    .setParameter("idBarang", idBarang)
                    .setParameter("idObat", idObat)
                    .list();

            if (resuts.size() > 0) {
                for (Object[] obj : resuts) {
                    Obat obat = new Obat();
                    obat.setIdBarang(obj[0] == null ? "" : obj[0].toString());
                    if (obj[1] != null) {
                        obat.setExpiredDate(Date.valueOf(obj[1].toString()));
                    }
                    obat.setIdObat(obj[2] == null ? "" : obj[2].toString());
                    obat.setNamaObat(obj[3] == null ? "" : obj[3].toString());
                    obat.setQtyBox(obj[4] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[4].toString()));
                    obat.setQtyLembar(obj[5] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[5].toString()));
                    obat.setQtyBiji(obj[6] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[6].toString()));
                    list.add(obat);
                }
            }

        }

        return list;
    }

    public List<Obat> getSearchObat(String query, String branch) {
        List<Obat> obatList = new ArrayList<>();
        if (query != null && branch != null) {
            String param = "%" + query + "%";
            String SQL = "SELECT id_obat, nama_obat, id_pabrik\n" +
                    "FROM im_simrs_obat\n" +
                    "WHERE nama_obat ILIKE :id AND branch_id = :branchId\n" +
                    "GROUP BY id_obat, nama_obat, id_pabrik\n" +
                    "UNION\n" +
                    "SELECT id_obat, nama_obat, id_pabrik\n" +
                    "FROM im_simrs_obat\n" +
                    "WHERE id_pabrik ILIKE :id AND branch_id = :branchId\n" +
                    "GROUP BY id_obat, nama_obat, id_pabrik\n";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", param)
                    .setParameter("branchId", branch)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    Obat obat = new Obat();
                    obat.setIdObat(obj[0] == null ? "" : obj[0].toString());
                    obat.setNamaObat(obj[1] == null ? "" : obj[1].toString());
                    obat.setIdPabrik(obj[2] == null ? "" : obj[2].toString());
                    Obat obt = getEntityObatById(obj[0].toString());
                    obat.setLembarPerBox(obt.getLembarPerBox());
                    obat.setBijiPerLembar(obt.getBijiPerLembar());
                    obat.setFlagKronis(obt.getFlagKronis());
                    obat.setFlagBpjs(obt.getFlagBpjs());
                    obatList.add(obat);
                }
            }
        }

        return obatList;
    }

    public Obat getEntityObatById(String idObat){
        Obat obat = new Obat();
        String SQL = "SELECT \n" +
                "id_obat,\n" +
                "nama_obat,\n" +
                "lembar_per_box,\n" +
                "biji_per_lembar,\n" +
                "flag_kronis,\n" +
                "flag_bpjs\n" +
                "FROM im_simrs_obat\n" +
                "WHERE id_obat = :id\n" +
                "ORDER BY created_date DESC\n" +
                "LIMIT 1";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idObat)
                .list();
        if(result.size() > 0){
            Object[] obj = result.get(0);
            obat.setIdObat(obj[0] == null ? "" : obj[0].toString());
            obat.setNamaObat(obj[1] == null ? "" : obj[1].toString());
            obat.setLembarPerBox(obj[2] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(String.valueOf(obj[2])));
            obat.setBijiPerLembar(obj[3] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(String.valueOf(obj[3])));
            obat.setFlagKronis(obj[4] == null ? "" : obj[4].toString());
            obat.setFlagBpjs(obj[5] == null ? "" : obj[5].toString());
        }
        return obat;
    }

    public Obat getObatByIdBarang(String idBarang, String branchId) {
        Obat obat = new Obat();
        if (idBarang != null && branchId != null) {

            String SQL = "SELECT \n" +
                    "a.id_obat, \n" +
                    "a.id_barang,\n" +
                    "a.lembar_per_box,\n" +
                    "a.biji_per_lembar,\n" +
                    "b.diskon,\n" +
                    "b.bruto,\n" +
                    "b.netto,\n" +
                    "c.average_harga_box,\n" +
                    "a.flag_bpjs,\n" +
                    "a.harga_terakhir\n" +
                    "FROM im_simrs_obat a\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail_batch b ON a.id_barang = b.id_barang\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                    "WHERE a.id_barang = :id AND a.branch_id = :branch";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idBarang)
                    .setParameter("branch", branchId)
                    .list();

            if (results.size() > 0) {
                Object[] obj = results.get(0);
                if (obj != null) {
                    obat.setIdObat(obj[0] == null ? "" : obj[0].toString());
                    obat.setIdBarang(obj[1] == null ? "" : obj[1].toString());
                    obat.setLembarPerBox(obj[2] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(obj[2].toString()));
                    obat.setBijiPerLembar(obj[3] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(obj[3].toString()));
                    obat.setDiskon(obj[4] == null ? new BigDecimal(String.valueOf("0")) : new BigDecimal(obj[4].toString()));
                    obat.setBruto(obj[5] == null ? new BigDecimal(String.valueOf("0")) : new BigDecimal(obj[5].toString()));
                    obat.setNetto(obj[6] == null ? new BigDecimal(String.valueOf("0")) : new BigDecimal(obj[6].toString()));
                    obat.setAverageHargaBox(obj[7] == null ? new BigDecimal(String.valueOf("0")) : new BigDecimal(obj[7].toString()));
                    obat.setFlagBpjs(obj[8] == null ? "" : obj[8].toString());
                    obat.setHargaTerakhir(obj[9] == null ? new BigDecimal(String.valueOf("0")) : new BigDecimal(obj[9].toString()));
                }
            }
        }
        return obat;
    }
}