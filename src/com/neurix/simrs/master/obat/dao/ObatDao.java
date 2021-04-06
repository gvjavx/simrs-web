package com.neurix.simrs.master.obat.dao;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.riwayatbarang.model.TransaksiStok;
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
                criteria.add(Restrictions.eq("idBarang", (String) mapCriteria.get("id_barang")));
            }
            if (mapCriteria.get("id_obat") != null) {
                criteria.add(Restrictions.eq("idObat", (String) mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("nama_obat") != null) {
                criteria.add(Restrictions.ilike("namaObat", "%" + (String) mapCriteria.get("nama_obat") + "%"));
            }
            if (mapCriteria.get("nama_obat_fix") != null) {
                criteria.add(Restrictions.ilike("namaObat", (String) mapCriteria.get("nama_obat_fix")));
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
            if (mapCriteria.get("flag_bpjs") != null) {
                criteria.add(Restrictions.eq("flagBpjs", mapCriteria.get("flag_bpjs")));
            }
            // Sigit 2021-04-06, mencari obat selain bpjs
            if (mapCriteria.get("non_flag_bpjs") != null) {
                criteria.add(Restrictions.ne("flagBpjs", mapCriteria.get("non_flag_bpjs")));
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

            // limit
            if (mapCriteria.get("limit") != null){
                criteria.setMaxResults(Integer.valueOf(mapCriteria.get("limit").toString()));
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

    public Obat getSumStockObatGudangById(String id, String ket, String branchId, String flagBpjs) {

        Obat obat = new Obat();

        String andFlag = "";
        if (flagBpjs != null && "Y".equalsIgnoreCase(flagBpjs))
            andFlag = "AND flag_bpjs = 'Y' \n";
        else if (flagBpjs != null && "N".equalsIgnoreCase(flagBpjs))
            andFlag = "AND flag_bpjs != 'Y' \n";

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
                    .setParameter("branchId", branchId)
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
                    "id_obat,  \n" +
                    "a.qty_box,  \n" +
                    "a.qty_lembar, \n" +
                    "a.qty_biji,\n" +
                    "a.lembar_per_box,\n" +
                    "a.biji_per_lembar\n" +
                    "FROM (\n" +
                    "\tSELECT \n" +
                    "\tqty_biji, \n" +
                    "\tqty_lembar, \n" +
                    "\tqty_box, \n" +
                    "\tlembar_per_box, \n" +
                    "\tbiji_per_lembar,\n" +
                    "\tid_obat,\n" +
                    "\tbranch_id\n" +
                    "\tFROM im_simrs_obat\n" +
                    "\tWHERE (qty_box, qty_lembar, qty_biji) != (0,0,0) \n" + andFlag +
                    //"\tAND flag_bpjs = :flagBpjs \n" +
                    ") a\n" +
                    "WHERE a.id_obat = :id1 \n" +
                    "AND a.branch_id = :branchId1 ";

            if (flagBpjs != null && "Y".equalsIgnoreCase(flagBpjs))
                andFlag = "WHERE a.flag_bpjs = 'Y' \n";
            else if (flagBpjs != null && "N".equalsIgnoreCase(flagBpjs))
                andFlag = "WHERE a.flag_bpjs != 'Y' \n";

            String SQLPoli = "\n" +
                    "SELECT  \n" +
                    "aa.id_obat,  \n" +
                    "aa.qty_box,  \n" +
                    "aa.qty_lembar, \n" +
                    "aa.qty_biji,\n" +
                    "aa.lembar_per_box,\n" +
                    "aa.biji_per_lembar\n" +
                    "FROM (\n" +
                    "\tSELECT \n" +
                    "\ta.lembar_per_box, \n" +
                    "\ta.biji_per_lembar,\n" +
                    "\ta.id_obat,\n" +
                    "\ta.branch_id,\n" +
                    "\tSUM(b.qty_box) as qty_box,\n" +
                    "\tSUM(b.qty_lembar) as qty_lembar,\n" +
                    "\tSUM(b.qty_biji) as qty_biji\n" +
                    "\tFROM im_simrs_obat a\n" +
                    "\tINNER JOIN ( \n" +
                    "\t\tSELECT id_barang, qty_box, qty_lembar, qty_biji FROM mt_simrs_obat_poli\n" +
                    "\t\t) b ON b.id_barang = a.id_barang\n" + andFlag +
                    "\tGROUP BY \n" +
                    "\ta.lembar_per_box, \n" +
                    "\ta.biji_per_lembar,\n" +
                    "\ta.id_obat,\n" +
                    "\ta.branch_id\n" +
                    ") aa\n" +
                    "WHERE aa.id_obat = :id2 \n" +
                    "AND aa.branch_id = :branchId2 ";

            List<Object[]> results1 = this.sessionFactory.getCurrentSession().createSQLQuery(SQLMaster)
                    .setParameter("id1", id)
                    .setParameter("branchId1", branchId)
                    .setParameter("flagBpjs", flagBpjs)
                    .list();

            List<Object[]> results2 = this.sessionFactory.getCurrentSession().createSQLQuery(SQLPoli)
                    .setParameter("id2", id)
                    .setParameter("branchId2", branchId)
                    .setParameter("flagBpjs", flagBpjs)
                    .list();

            String idObat = "";
            BigInteger totalBox1 = new BigInteger(String.valueOf(0));
            BigInteger totalLembar1 = new BigInteger(String.valueOf(0));
            BigInteger totalBiji1 = new BigInteger(String.valueOf(0));
            BigInteger jumlahBijiAll = new BigInteger(String.valueOf(0));

            System.out.println(" ====== Obat Gudang : ");
            int i = 1;
            if (results1.size() > 0) {
                for (Object[] obj : results1) {
                    idObat = obj[0].toString();

                    BigInteger totalBox = new BigInteger(String.valueOf(0));
                    BigInteger totalLembar = new BigInteger(String.valueOf(0));
                    BigInteger totalBiji = new BigInteger(String.valueOf(0));

                    if(obj[1] != null){
                        totalBox = new BigInteger(obj[1].toString());
                        totalBox1 = totalBox1.add(totalBox);
//                        totalBox1 = new BigInteger(obj[1].toString());
                    }
                    if(obj[2] != null){
                        totalLembar = new BigInteger(obj[2].toString());
                        totalLembar1 = totalLembar1.add(totalLembar);
//                        totalLembar1 = new BigInteger(obj[2].toString());
                    }
                    if(obj[3] != null){
                        totalBiji = new BigInteger(obj[3].toString());
                        totalBiji1 = totalBiji1.add(totalBiji);
//                        totalBiji1 = new BigInteger(obj[3].toString());
                    }

                    BigInteger lembarPerBox = new BigInteger(obj[4].toString());
                    BigInteger bijiPerLembar = new BigInteger(obj[5].toString());
                    BigInteger consBox = lembarPerBox.multiply(bijiPerLembar);

                    System.out.println("loop ke : "+ i++ +" idobat : "+idObat+" box : "+totalBox+" lembar : "+totalLembar+" biji : "+totalBiji);
                    System.out.println("    -> lembar / box : "+lembarPerBox+" biji / lembar : "+bijiPerLembar);

                    BigInteger boxToBiji = totalBiji.multiply(consBox);
                    BigInteger lembarToBiji = totalLembar.multiply(bijiPerLembar);

                    // jumlah Biji all
                    jumlahBijiAll = jumlahBijiAll.add(boxToBiji.add(lembarToBiji).add(totalBiji));

                }
            }

            System.out.println(" ====== Obat Poli : ");

            BigInteger totalBox2 = new BigInteger(String.valueOf(0));
            BigInteger totalLembar2 = new BigInteger(String.valueOf(0));
            BigInteger totalBiji2 = new BigInteger(String.valueOf(0));

            if (results2.size() > 0) {
                for (Object[] obj : results2) {

                    BigInteger totalBox = new BigInteger(String.valueOf(0));
                    BigInteger totalLembar = new BigInteger(String.valueOf(0));
                    BigInteger totalBiji = new BigInteger(String.valueOf(0));

                    if(obj[1] != null){
                        totalBox = new BigInteger(obj[1].toString());
                        totalBox2 = totalBox2.add(totalBox);
//                        totalBox2 = totalBox2.add(new BigInteger(obj[1].toString()));
                    }
                    if(obj[2] != null){
                        totalLembar = new BigInteger(obj[2].toString());
                        totalLembar2 = totalLembar2.add(totalLembar);
//                        totalLembar2 = totalLembar2.add(new BigInteger(obj[2].toString()));
                    }
                    if(obj[3] != null){
                        totalBiji = new BigInteger(obj[3].toString());
                        totalBiji2 = totalBiji2.add(totalBiji);
//                        totalBiji2 = totalBiji2.add(new BigInteger(obj[3].toString()));
                    }

                    BigInteger lembarPerBox = new BigInteger(obj[4].toString());
                    BigInteger bijiPerLembar = new BigInteger(obj[5].toString());
                    BigInteger consBox = lembarPerBox.multiply(bijiPerLembar);

                    System.out.println("loop ke : "+ i++ +" idobat : "+idObat+" box : "+totalBox+" lembar : "+totalLembar+" biji : "+totalBiji);
                    System.out.println("    -> lembar / box : "+obj[4].toString()+" biji / lembar : "+obj[5].toString());

                    BigInteger boxToBiji = totalBox.multiply(consBox);
                    BigInteger lembarToBiji = totalLembar.multiply(bijiPerLembar);

                    // jumlah Biji all
                    jumlahBijiAll = jumlahBijiAll.add(boxToBiji.add(lembarToBiji).add(totalBiji));
                }
            }

            if(!"".equalsIgnoreCase(idObat)){
                obat.setIdObat(idObat);
                obat.setQtyBox(totalBox1.add(totalBox2));
                obat.setQtyLembar(totalLembar1.add(totalLembar2));
                obat.setQtyBiji(totalBiji1.add(totalBiji2));
                obat.setQtyAllBiji(jumlahBijiAll);
            }
        }

        return obat;
    }

    public Obat getLastIdSeqObat(String idObat, String flagBpjs) {

        String andFlag = "";
        if (flagBpjs != null && "Y".equalsIgnoreCase(flagBpjs))
            andFlag = "AND flag_bpjs = 'Y' \n";
        else if (flagBpjs != null && "N".equalsIgnoreCase(flagBpjs))
            andFlag = "AND flag_bpjs != 'Y' \n";

        String SQL = "SELECT id_seq_obat, created_date\n" +
                "FROM im_simrs_obat\n" +
                "WHERE id_obat = :id\n" + andFlag +
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
        String condition = "";
        String flag = "%";
        String branch = "";

        if (bean != null) {
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                flag = bean.getFlag();
            }
            if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
                condition += "AND a.id_obat ILIKE '%"+bean.getIdObat()+"%'";
            }
            if (bean.getNamaObat() != null && !"".equalsIgnoreCase(bean.getNamaObat())) {
                condition += "AND a.nama_obat ILIKE '%"+bean.getNamaObat()+"%'";
            }
            if (bean.getIdBentuk() != null && !"".equalsIgnoreCase(bean.getIdBentuk())) {
                condition += "AND a.id_bentuk = '"+bean.getIdBentuk()+"'\n";
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId()) && !CommonConstant.BRANCH_KP.equalsIgnoreCase(bean.getBranchId())) {
                branch = "WHERE branch_id = '"+bean.getBranchId()+"'\n";
            }

            String SQL = "SELECT\n" +
                    "a.id_obat,\n" +
                    "a.nama_obat,\n" +
                    "a.id_kategori_persediaan,\n" +
                    "a.id_bentuk,\n" +
                    "a.id_jenis_obat,\n" +
                    "a.id_sub_jenis,\n" +
                    "a.flag_kronis,\n" +
                    "a.flag_generic,\n" +
                    "a.flag_is_formularium,\n" +
                    "a.flag_bpjs,\n" +
                    "a.flag_parenteral,\n"+
                    "a.min_stok,\n" +
                    "b.sum_biji,\n" +
                    "c.standar_margin,\n" +
                    "a.lembar_per_box,\n" +
                    "a.biji_per_lembar\n" +
                    "FROM im_simrs_header_obat a\n" +
                    "INNER JOIN (\n" +
                    "SELECT\n" +
                    "id_obat,\n" +
                    "SUM(qty_biji) as sum_biji\n" +
                    "FROM im_simrs_obat\n" + branch +
                    "GROUP BY id_obat\n" +
                    ") b ON a.id_obat = b.id_obat\n" +
                    "LEFT JOIN im_simrs_margin_obat c ON a.id_obat = c.id_obat\n" +
                    "WHERE a.flag = :flag \n" +condition;

            List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("flag", flag)
                    .list();

            if (resuts.size() > 0) {
                for (Object[] obj : resuts) {
                    Obat obat = new Obat();
                    obat.setIdObat(obj[0] == null ? "" : obj[0].toString());
                    obat.setNamaObat(obj[1] == null ? "" : obj[1].toString());
                    obat.setIdKategoriPersediaan(obj[2] == null ? "" : (String) obj[2]);
                    obat.setIdBentuk(obj[3] == null ? "" : (String) obj[3]);
                    obat.setIdJenisBentuk(obj[4] == null ? "" : (String) obj[4]);
                    obat.setIdJenisSub(obj[5] == null ? "" : (String) obj[5]);
                    obat.setFlagKronis(obj[6] == null ? "N" : obj[6].toString());
                    obat.setFlagGeneric(obj[7] == null ? "N" : obj[7].toString());
                    obat.setFlagFormula(obj[8] == null ? "N" : (String) obj[8]);
                    obat.setFlagBpjs(obj[9] == null ? "N" : obj[9].toString());
                    obat.setFlagParenteral(obj[10] == null ? "N" : (String) obj[10]);
                    obat.setMinStok(obj[11] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(String.valueOf(obj[11].toString())));
                    obat.setQtyBiji(obj[12] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[12].toString()));
                    obat.setMargin(obj[13] == null ? 0 : (Integer) obj[13]);
                    obat.setLembarPerBox(obj[14] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[14].toString()));
                    obat.setBijiPerLembar(obj[15] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[15].toString()));
                    obat.setJenisObat(getObatGejalaByIdObat(obj[0].toString()));
                    if (obat.getQtyBiji() != null && obat.getMinStok() != null) {
                        if (obat.getQtyBiji().intValue() >= obat.getMinStok().intValue()) {
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

            String SQL = "SELECT\n" +
                    "a.id_barang, \n" +
                    "a.expired_date, \n" +
                    "a.id_obat, \n" +
                    "a.nama_obat, \n" +
                    "a.qty_biji,\n" +
                    "a.merk,\n" +
                    "a.id_pabrik_obat,\n" +
                    "b.nama,\n" +
                    "a.nomor_produksi\n" +
                    "FROM im_simrs_obat a\n" +
                    "LEFT JOIN im_simrs_pabrik_obat b ON a.id_pabrik_obat = b.id\n" +
                    "WHERE a.id_obat LIKE :idObat\n" +
                    "AND a.id_barang LIKE :idBarang\n" +
                    "AND a.branch_id LIKE :branchId\n" +
                    "ORDER BY a.expired_date ASC \n";

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
                    obat.setQtyBiji(obj[4] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[4].toString()));
                    obat.setMerk(obj[5] == null ? "" : obj[5].toString());
                    obat.setIdPabrikObat(obj[6] == null ? "" : obj[6].toString());
                    obat.setNamaPabrikObat(obj[7] == null ? "" : obj[7].toString());
                    obat.setNomorProduksi(obj[8] == null ? "" : obj[8].toString());
                    list.add(obat);
                }
            }
        }
        return list;
    }

    public List<Obat> getSearchObat(String query, String branch) {
        List<Obat> obatList = new ArrayList<>();
        if (query != null) {
            String param = "%" + query + "%";

            String SQL = "SELECT  \n" +
                    "id_obat, \n" +
                    "nama_obat,\n" +
                    "lembar_per_box, \n" +
                    "biji_per_lembar, \n" +
                    "flag_generic, \n" +
                    "min_stok, \n" +
                    "merk, \n" +
                    "flag_bpjs \n" +
                    "FROM im_simrs_header_obat\n" +
                    "WHERE flag = 'Y'\n" +
                    "AND nama_obat ILIKE :id\n" +
                    "OR id_obat ILIKE :id\n" +
                    "LIMIT 10";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", param)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    Obat obat = new Obat();
                    obat.setIdObat(obj[0] == null ? "" : obj[0].toString());
                    obat.setNamaObat(obj[1] == null ? "" : obj[1].toString());
                    obat.setLembarPerBox(obj[2] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[2]);
                    obat.setBijiPerLembar(obj[3] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[3]);
                    obat.setFlagGeneric(obj[4] == null ? "" : obj[4].toString());
                    obat.setMinStok(obj[5] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[5]);
                    obat.setMerk(obj[6] == null ? "" : obj[6].toString());
                    obat.setFlagBpjs(obj[7] == null ? "" : obj[7].toString());
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

    public List<Aging> getAgingStokObat(String branchId, String idPelayanan, String periode){

        if (idPelayanan == null || "".equalsIgnoreCase(idPelayanan)){
            idPelayanan = "%";
        }

        String SQL = "SELECT\n" +
                "br.branch_id,\n" +
                "a.*,\n" +
                "pl.nama_pelayanan,\n" +
                "br.branch_name,\n" +
                "ts.qty as qty_awal,\n" +
                "ob.id_obat\n" +
                "FROM\n" +
                "(\n" +
                "        SELECT \n" +
                "        a.id_barang,\n" +
                "        a.nama_obat,\n" +
                "        CASE WHEN a.qty_d - a.qty_k <= 0 THEN 0 ELSE a.qty_d - a.qty_k END AS qty,\n" +
                "        a.registered_date,\n" +
                "        a.id_pelayanan\n" +
                "        FROM\n" +
                "        (\n" +
                "                SELECT\n" +
                "                tso.id_barang,\n" +
                "                tso.nama_obat,\n" +
                "                SUM(tso.qty_d) AS qty_d,\n" +
                "                SUM(tso.qty_k) AS qty_k,\n" +
                "                tso.registered_date,\n" +
                "                tso.id_pelayanan\n" +
                "                FROM \n" +
                "                (\n" +
                "                        SELECT \n" +
                "                        ts.id_barang,\n" +
                "                        ob.nama_obat,\n" +
                "                        CASE WHEN ts.tipe = 'D' THEN SUM(ts.qty) ELSE 0 END AS qty_d,\n" +
                "                        CASE WHEN ts.tipe = 'K' THEN SUM(ts.qty) ELSE 0 END AS qty_k,\n" +
                "                        ts.registered_date,\n" +
                "                        ts.id_pelayanan\n" +
                "                        FROM it_simrs_transaksi_stok ts\n" +
                "                        INNER JOIN im_simrs_obat ob ON ob.id_barang = ts.id_barang\n" +
                "                        WHERE to_date( cast(ts.registered_date as TEXT), 'MM-yyyy') \n" +
                "                        < ( to_date(:periode, 'MM-yyyy')+ Interval '1 month') \n" +
                "                        GROUP BY \n" +
                "                        ts.id_barang,\n" +
                "                        ob.nama_obat,\n" +
                "                        ts.tipe,\n" +
                "                        ts.registered_date,\n" +
                "                        ts.id_pelayanan\n" +
                "                        ORDER BY ts.id_pelayanan, ob.nama_obat, ts.registered_date, ts.id_barang\n" +
                "                ) tso\n" +
                "                GROUP BY\n" +
                "                tso.id_barang,\n" +
                "                tso.nama_obat,\n" +
                "                tso.registered_date,\n" +
                "                tso.id_pelayanan\n" +
                "                ORDER BY id_pelayanan, registered_date, id_barang\n" +
                "        ) a\n" +
                "        GROUP BY\n" +
                "        a.id_barang,\n" +
                "        a.nama_obat,\n" +
                "        a.registered_date,\n" +
                "        a.id_pelayanan,\n" +
                "        a.qty_d,\n" +
                "        a.qty_k\n" +
                "        ORDER BY id_pelayanan, registered_date, id_barang\n" +
                ") a \n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "a.branch_id,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) pl ON pl.id_pelayanan = a.id_pelayanan\n" +
                "INNER JOIN im_branches br ON br.branch_id = pl.branch_id\n" +
                "INNER JOIN im_simrs_obat ob ON ob.id_barang = a.id_barang \n" +
                "INNER JOIN (\n" +
                "        SELECT \n" +
                "        id_barang,\n" +
                "        registered_date,\n" +
                "        id_pelayanan,\n" +
                "        SUM(qty) as qty\n" +
                "        FROM \n" +
                "        it_simrs_transaksi_stok\n" +
                "        WHERE tipe = 'D'\n" +
                "        GROUP BY\n" +
                "        id_barang,\n" +
                "        registered_date,\n" +
                "        id_pelayanan\n" +
                ") ts ON ts.id_barang = a.id_barang AND ts.registered_date = a.registered_date AND ts.id_pelayanan = a.id_pelayanan\n" +
                "WHERE a.qty > 0\n" +
                "AND br.branch_id LIKE :unit \n" +
                "AND pl.id_pelayanan LIKE :pelayanan \n" +
                "ORDER BY \n" +
                "pl.id_pelayanan DESC, ob.id_obat, a.nama_obat, a.registered_date, a.id_barang";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .setParameter("pelayanan", idPelayanan)
                .setParameter("periode", periode)
                .list();

        List<Aging> agingList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] row : results){
                Aging data = new Aging();
                data.setKodeRekening((String) row[0]);
                data.setNoNota((String) row[1]);
                data.setTotal(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
                data.setTglJurnal((Date) row[4]);
                data.setMasterId((String) row[5]);
                data.setNamaMaster((String) row[6]);
                data.setNamaRekening((String) row[7]);
                data.setTotalAwal((BigDecimal) row[8]);
                data.setIdItem((String) row[9]);
                data.setNamaItem((String) row[2]);
                agingList.add(data);
            }
        }
        return agingList;
    }

    public TransaksiStok getSumKreditByPeriodeTransaksiStok(String branchId, String idPelayanan, String periode, String idBarang){

        periode = periode + "%";

        String SQL = "SELECT \n" +
                "id_pelayanan,\n" +
                "registered_date,\n" +
                "tipe,\n" +
                "id_barang,\n" +
                "SUM(qty) as total_qty\n" +
                "FROM it_simrs_transaksi_stok\n" +
                "WHERE branch_id LIKE :unit \n" +
                "AND tipe = 'K'\n" +
                "AND id_pelayanan = :pelayanan \n" +
                "AND id_barang LIKE :idbarang \n" +
                "AND CAST(registered_date as VARCHAR) LIKE :periode \n" +
                "GROUP BY \n" +
                "id_pelayanan,\n" +
                "registered_date,\n" +
                "tipe,\n" +
                "id_barang";

        List<Object[]> objects = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .setParameter("pelayanan", idPelayanan)
                .setParameter("periode", periode)
                .setParameter("idbarang", idBarang)
                .list();

        TransaksiStok transaksiStok = new TransaksiStok();
        if (objects != null && objects.size() > 0){
            for (Object[] obj : objects){
                transaksiStok.setIdPelayanan(obj[0].toString());
                transaksiStok.setRegisteredDate((Date) obj[1]);
                transaksiStok.setTipe(obj[2].toString());
                transaksiStok.setIdPelayanan(obj[3].toString());
                transaksiStok.setQty(obj[4] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(String.valueOf((BigDecimal) obj[4])));
            }
        }

        return transaksiStok;
    }

    public Obat getLastEveragePricePerBiji(String idObat, String branchId){

        String SQL = "SELECT a.id_obat, a.average_harga_biji, b.harga_jual \n" +
                "FROM im_simrs_obat a \n" +
                "INNER JOIN mt_simrs_harga_obat b ON b.id_obat = a.id_obat\n" +
                "WHERE a.id_obat = :id \n" +
                "AND a.branch_id = :unit \n" +
                "ORDER BY a.created_date DESC LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .setParameter("id", idObat)
                .list();

        if (results.size() > 0){
            for (Object[] obj : results){
                Obat obat = new Obat();
                obat.setIdObat(obj[0].toString());
                obat.setAverageHargaBiji(obj[1] == null ? new BigDecimal(0) : (BigDecimal) obj[1]);
                obat.setHargaJual(obj[2] == null ? new BigDecimal(0) : (BigDecimal) obj[2]);
                return obat;
            }
        }

        return null;
    }
}