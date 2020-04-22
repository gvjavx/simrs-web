package com.neurix.simrs.transaksi.obatpoli.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsObatPoliEntity;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 10/12/2019.
 */
public class ObatPoliDao extends GenericDao<MtSimrsObatPoliEntity,String> {

    @Override
    protected Class<MtSimrsObatPoliEntity> getEntityClass() {
        return MtSimrsObatPoliEntity.class;
    }

    @Override
    public List<MtSimrsObatPoliEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsObatPoliEntity.class);

        if (mapCriteria.get("id_barang") != null) {
            criteria.add(Restrictions.eq("primaryKey.idBarang", mapCriteria.get("id_barang")));
        }
        if (mapCriteria.get("id_pelayanan") != null) {
            criteria.add(Restrictions.eq("primaryKey.idPelayanan", mapCriteria.get("id_pelayanan")));
        }
        if (mapCriteria.get("id_obat") != null) {
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat")));
        }
        if (mapCriteria.get("branch_id") != null) {
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id")));
        }
        if (mapCriteria.get("id_pabrik") != null) {
            criteria.add(Restrictions.eq("idPabrik", mapCriteria.get("id_pabrik")));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        if (mapCriteria.get("asc") != null){
            criteria.addOrder(Order.asc("createdDate"));
        }
        if (mapCriteria.get("exp") != null){
            criteria.addOrder(Order.asc("expiredDate"));
        }

        //criteria.addOrder(Order.asc("primaryKey.idObat"));
        List<MtSimrsObatPoliEntity> results = criteria.list();
        return results;
    }

    public List<PermintaanObatPoli> cekIdObatInTransaksi(PermintaanObatPoli bean){

        String idObat      = "%";
        String idPelayanan = "%";
        String branchId    = "%";

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            idObat = bean.getIdObat();
        }

        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
            idPelayanan = bean.getIdPelayanan();
        }

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }

        String SQL = "SELECT b.id_obat, a.id_pelayanan, a.branch_id " +
                "FROM mt_simrs_approval_transaksi_obat a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail b " +
                "ON a.id_approval_obat = b.id_approval_obat\n" +
                "WHERE a.id_pelayanan LIKE :idPelayanan " +
                "AND a.branch_id LIKE :branchId " +
                "AND b.id_obat LIKE :idObat " +
                "AND a.flag = 'Y' AND b.flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("branchId", branchId)
                .setParameter("idObat", idObat)
                .list();

        List<PermintaanObatPoli> obatPoliList = new ArrayList<>();

        if (results.size() > 0)
        {
            PermintaanObatPoli permintaanObatPoli;
            for (Object[] obj : results)
            {
                permintaanObatPoli = new PermintaanObatPoli();
                permintaanObatPoli.setIdObat(obj[0].toString());
                permintaanObatPoli.setIdPelayanan(obj[1].toString());
                permintaanObatPoli.setBranchId(obj[2].toString());
                obatPoliList.add(permintaanObatPoli);
            }
        }

        return obatPoliList;
    }

    public List<PermintaanObatPoli> cekIdObatInTransaksiRequestGudang(PermintaanObatPoli bean){

        String idObat      = "%";
        String idPelayanan = "%";
        String branchId    = "%";

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            idObat = bean.getIdObat();
        }

        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
            idPelayanan = bean.getIdPelayanan();
        }

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }

        String SQL = "SELECT b.id_obat, a.id_pelayanan, a.branch_id \n" +
                "FROM mt_simrs_approval_transaksi_obat a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                "INNER JOIN mt_simrs_permintaan_obat_poli pol ON pol.id_approval_obat = a.id_approval_obat\n" +
                "WHERE a.id_pelayanan LIKE :idPelayanan " +
                "AND a.branch_id LIKE :branchId " +
                "AND b.id_obat LIKE :idObat " +
                "AND a.flag = 'Y' AND b.flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("branchId", branchId)
                .setParameter("idObat", idObat)
                .list();

        List<PermintaanObatPoli> obatPoliList = new ArrayList<>();

        if (results.size() > 0)
        {
            PermintaanObatPoli permintaanObatPoli;
            for (Object[] obj : results)
            {
                permintaanObatPoli = new PermintaanObatPoli();
                permintaanObatPoli.setIdObat(obj[0].toString());
                permintaanObatPoli.setIdPelayanan(obj[1].toString());
                permintaanObatPoli.setBranchId(obj[2].toString());
                obatPoliList.add(permintaanObatPoli);
            }
        }

        return obatPoliList;
    }

    public List<ObatPoli> getTujuanPelyanan(ObatPoli bean){

        String idPelayanan = "%";

        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
            idPelayanan = bean.getIdPelayanan();
        }

        String SQL = "SELECT id_pelayanan, nama_pelayanan FROM im_simrs_pelayanan\n" +
                "    WHERE id_pelayanan NOT LIKE :idPelayanan AND flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .list();

        List<ObatPoli> obatPoliList = new ArrayList<>();

        if (results.size() > 0)
        {
            ObatPoli obatPoli;
            for (Object[] obj : results)
            {
                obatPoli = new ObatPoli();
                obatPoli.setIdPelayanan(obj[0].toString());
                obatPoli.setNamaPelayanan(obj[1].toString());
                obatPoliList.add(obatPoli);
            }
        }

        return obatPoliList;
    }

    public List<String> getIdObatGroup(String idPelayanan, String branchId){

        String SQL = "SELECT id_obat, id_pelayanan\n" +
                "FROM mt_simrs_obat_poli\n" +
                "WHERE id_pelayanan = :idPelayanan \n" +
                "AND branch_id = :branchId \n" +
                "GROUP BY id_obat, id_pelayanan";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("branchId", branchId)
                .list();

        List<String> stringList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                stringList.add(obj[0].toString());
            }
        }
        return stringList;
    }

    public List<ObatPoli> getIdObatGroupPoli(String idPelayanan, String branchId, String flagBpjs){

        List<ObatPoli> obatPoliList = new ArrayList<>();

        if(idPelayanan != null && !"".equalsIgnoreCase(idPelayanan) && branchId != null && !"".equalsIgnoreCase(branchId)){

            String flag = "N";

            if("bpjs".equalsIgnoreCase(flagBpjs)){
                flag = "%";
            }

            String SQL = "SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.id_obat,\n" +
                    "b.nama_obat,\n" +
                    "SUM(a.qty_box) as box,\n" +
                    "SUM(a.qty_lembar) as lembar,\n" +
                    "SUM(a.qty_biji) as biji,\n" +
                    "b.lembar_per_box,\n" +
                    "b.biji_per_lembar,\n" +
                    "b.flag_kronis,\n" +
                    "c.harga_jual\n" +
                    "FROM mt_simrs_obat_poli a\n" +
                    "INNER JOIN (\n" +
                    "SELECT id_obat, nama_obat, lembar_per_box, biji_per_lembar, flag_kronis\n" +
                    "FROM im_simrs_obat WHERE flag_bpjs LIKE :flag GROUP BY id_obat, nama_obat, lembar_per_box, biji_per_lembar, flag_kronis\n" +
                    ") b ON a.id_obat = b.id_obat\n" +
                    "INNER JOIN mt_simrs_harga_obat c ON a.id_obat = c.id_obat\n" +
                    "WHERE a.id_pelayanan = :idPelayanan \n" +
                    "AND a.branch_id = :branchId \n" +
                    "GROUP BY a.id_pelayanan,\n" +
                    "b.id_obat,\n" +
                    "b.nama_obat,\n" +
                    "b.lembar_per_box,\n" +
                    "b.biji_per_lembar,\n" +
                    "b.flag_kronis,\n" +
                    "c.harga_jual\n";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPelayanan", idPelayanan)
                    .setParameter("branchId", branchId)
                    .setParameter("flag", flag)
                    .list();

            if (results.size() > 0){
                for (Object[] obj : results){
                    ObatPoli obatPoli = new ObatPoli();
                    obatPoli.setIdPelayanan(obj[0] == null ? "" : obj[0].toString());
                    obatPoli.setIdObat(obj[1] == null ? "" : obj[1].toString());
                    obatPoli.setNamaObat(obj[2] == null ? "" : obj[2].toString());
                    obatPoli.setQtyBox(obj[3] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[3].toString()));
                    obatPoli.setQtyLembar(obj[4] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[4].toString()));
                    obatPoli.setQtyBiji(obj[5] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[5].toString()));
                    obatPoli.setLembarPerBox(obj[6] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[6].toString()));
                    obatPoli.setBijiPerLembar(obj[7] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[7].toString()));
                    obatPoli.setFlagKronis(obj[8] == null ? "" : obj[8].toString());
                    obatPoli.setHarga(obj[9] == null ? "" : obj[9].toString());
                    obatPoliList.add(obatPoli);
                }
            }
        }
        return obatPoliList;
    }

    public ObatPoli getSumStockObatPoliById(String id, String idPelayanan){

        String SQL = "SELECT \n" +
                "id_obat, \n" +
                "SUM(qty_box) as qty_box, \n" +
                "SUM(qty_lembar) as qty_lembar,\n" +
                "SUM(qty_biji) as qty_biji\n" +
                "FROM mt_simrs_obat_poli \n" +
                "WHERE (qty_box, qty_lembar, qty_biji) != ('0','0','0')\n" +
                "AND id_obat = :id\n" +
                "AND id_pelayanan = :idPoli\n" +
                "GROUP BY id_obat";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .setParameter("idPoli", idPelayanan)
                .list();

        ObatPoli obatPoli = new ObatPoli();
        if (results.size() > 0){
            for (Object[] obj : results){
                obatPoli.setIdObat(obj[0].toString());
                obatPoli.setQtyBox(new BigInteger(String.valueOf(obj[1])));
                obatPoli.setQtyLembar(new BigInteger(String.valueOf(obj[2])));
                obatPoli.setQtyBiji(new BigInteger(String.valueOf(obj[3])));
            }
        }

        return obatPoli;
    }

}
