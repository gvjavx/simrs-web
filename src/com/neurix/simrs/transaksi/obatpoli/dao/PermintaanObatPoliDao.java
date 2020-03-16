package com.neurix.simrs.transaksi.obatpoli.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsPermintaanObatPoliEntity;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
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
 * Created by Toshiba on 11/12/2019.
 */
public class PermintaanObatPoliDao extends GenericDao<MtSimrsPermintaanObatPoliEntity, String> {

    @Override
    protected Class<MtSimrsPermintaanObatPoliEntity> getEntityClass() {
        return MtSimrsPermintaanObatPoliEntity.class;
    }

    @Override
    public List<MtSimrsPermintaanObatPoliEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsPermintaanObatPoliEntity.class);
        if (mapCriteria.get("id_permintaan_obat_poli") != null) {
            criteria.add(Restrictions.eq("idPermintaanObatPoli", mapCriteria.get("id_permintaan_obat_poli").toString()));
        }
        if (mapCriteria.get("id_approval_obat") != null) {
            criteria.add(Restrictions.eq("idApprovalObat", mapCriteria.get("id_approval_obat").toString()));
        }
        if (mapCriteria.get("id_obat") != null) {
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
        }
        if (mapCriteria.get("id_pelayanan") != null) {
            criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
        }
        if (mapCriteria.get("tujuan_pelayanan") != null) {
            criteria.add(Restrictions.eq("tujuanPelayanan", mapCriteria.get("tujuan_pelayanan").toString()));
        }
        if (mapCriteria.get("diterima_flag") != null) {
            criteria.add(Restrictions.eq("diterimaFlag", mapCriteria.get("diterima_flag").toString()));
        }
        if (mapCriteria.get("reture_flag") != null) {
            criteria.add(Restrictions.eq("retureFlag", mapCriteria.get("reture_flag").toString()));
        }
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<MtSimrsPermintaanObatPoliEntity> results = criteria.list();
        return results;
    }

    public List<MtSimrsPermintaanObatPoliEntity> getListPermintaanObatPoliEntity(PermintaanObatPoli bean, boolean isPoli) {
        List<MtSimrsPermintaanObatPoliEntity> listOfResults = new ArrayList<>();

        String branchId = "%";
        String idPelayanan = "%";
        String idTujuan = "%";
        String idObat = "%";
        String idPermintaanObatPoli = "%";
        String idJenisObat = "%";
        String flag = "%";
        String tipePermintaan = "%";

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            branchId = bean.getBranchId();
        }
        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
            idPelayanan = bean.getIdPelayanan();
        }
        if (bean.getTujuanPelayanan() != null && !"".equalsIgnoreCase(bean.getTujuanPelayanan())) {
            idTujuan = bean.getTujuanPelayanan();
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            idObat = bean.getIdObat();
        }
        if (bean.getIdPermintaanObatPoli() != null && !"".equalsIgnoreCase(bean.getIdPermintaanObatPoli())) {
            idPermintaanObatPoli = bean.getIdPermintaanObatPoli();
        }
        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
            idJenisObat = bean.getIdJenisObat();
        }
        if (bean.getTipePermintaan() != null && !"".equalsIgnoreCase(bean.getTipePermintaan())) {
            tipePermintaan = bean.getTipePermintaan();
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            flag = bean.getFlag();
        }

        String SQL = "";

        if (isPoli) {
            SQL = "SELECT\n" +
                    "pop.id_permintaan_obat_poli,\n" +
                    "pop.id_obat,\n" +
                    "pop.id_pelayanan,\n" +
                    "pop.id_approval_obat,\n" +
                    "pop.qty,\n" +
                    "pop.flag,\n" +
                    "pop.action,\n" +
                    "pop.created_date,\n" +
                    "pop.created_who,\n" +
                    "pop.last_update,\n" +
                    "pop.last_update_who,\n" +
                    "pop.tujuan_pelayanan,\n" +
                    "pop.diterima_flag,\n" +
                    "pop.reture_flag\n" +
                    "FROM mt_simrs_permintaan_obat_poli pop\n" +
                    "INNER JOIN\n" +
                    "(\n" +
                    "SELECT pop.id_permintaan_obat_poli\n" +
                    "FROM mt_simrs_permintaan_obat_poli pop\n" +
                    "INNER JOIN mt_simrs_approval_transaksi_obat ato ON ato.id_approval_obat = pop.id_approval_obat\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail tod ON tod.id_approval_obat = ato.id_approval_obat\n" +
                    "INNER JOIN im_simrs_obat_gejala og On og.id_obat = tod.id_obat\n" +
                    "WHERE pop.flag LIKE :flag\n" +
                    "AND ato.branch_id LIKE :branchId\n" +
                    "AND tod.id_obat LIKE :idObat\n" +
                    "AND ato.tipe_permintaan LIKE :tipePermintaan\n" +
                    "AND pop.id_pelayanan LIKE :idPelayanan\n" +
                    "AND og.id_jenis_obat LIKE :idJenisObat\n" +
                    "AND pop.id_permintaan_obat_poli LIKE :idPermintaanObatPoli\n" +
                    "AND pop.tujuan_pelayanan NOT LIKE :idTujuan\n" +
                    "GROUP BY pop.id_permintaan_obat_poli\n" +
                    ") popo ON popo.id_permintaan_obat_poli = pop.id_permintaan_obat_poli\n";
        } else {
            SQL = "SELECT\n" +
                    "pop.id_permintaan_obat_poli,\n" +
                    "pop.id_obat,\n" +
                    "pop.id_pelayanan,\n" +
                    "pop.id_approval_obat,\n" +
                    "pop.qty,\n" +
                    "pop.flag,\n" +
                    "pop.action,\n" +
                    "pop.created_date,\n" +
                    "pop.created_who,\n" +
                    "pop.last_update,\n" +
                    "pop.last_update_who,\n" +
                    "pop.tujuan_pelayanan,\n" +
                    "pop.diterima_flag,\n" +
                    "pop.reture_flag\n" +
                    "FROM mt_simrs_permintaan_obat_poli pop\n" +
                    "INNER JOIN\n" +
                    "(\n" +
                    "SELECT pop.id_permintaan_obat_poli\n" +
                    "FROM mt_simrs_permintaan_obat_poli pop\n" +
                    "INNER JOIN mt_simrs_approval_transaksi_obat ato ON ato.id_approval_obat = pop.id_approval_obat\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail tod ON tod.id_approval_obat = ato.id_approval_obat\n" +
//                    "INNER JOIN im_simrs_obat_gejala og On og.id_obat = tod.id_obat\n" +
                    "WHERE pop.flag LIKE :flag\n" +
                    "AND ato.branch_id LIKE :branchId\n" +
                    "AND tod.id_obat LIKE :idObat\n" +
                    "AND ato.tipe_permintaan LIKE :tipePermintaan\n" +
                    "AND pop.id_pelayanan LIKE :idPelayanan\n" +
//                    "AND og.id_jenis_obat LIKE :idJenisObat\n" +
                    "AND pop.id_permintaan_obat_poli LIKE :idPermintaanObatPoli\n" +
                    "AND pop.tujuan_pelayanan LIKE :idTujuan\n" +
                    "GROUP BY pop.id_permintaan_obat_poli\n" +
                    ") popo ON popo.id_permintaan_obat_poli = pop.id_permintaan_obat_poli\n";
        }

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("idTujuan", idTujuan)
                .setParameter("idObat", idObat)
                .setParameter("idPermintaanObatPoli", idPermintaanObatPoli)
//                .setParameter("idJenisObat", idJenisObat)
                .setParameter("tipePermintaan", tipePermintaan)
                .setParameter("flag", flag)
                .list();

        if (results.size() > 0) {
            MtSimrsPermintaanObatPoliEntity obatPoliEntity;
            for (Object[] obj : results) {
                obatPoliEntity = new MtSimrsPermintaanObatPoliEntity();
                obatPoliEntity.setIdPermintaanObatPoli(obj[0].toString());
                if (obj[1] != null && !"".equalsIgnoreCase(obj[1].toString())) {
                    obatPoliEntity.setIdObat(obj[1].toString());
                } else {
                    obatPoliEntity.setIdObat("");
                }
                obatPoliEntity.setIdPelayanan(obj[2].toString());
                obatPoliEntity.setIdApprovalObat(obj[3].toString());
                obatPoliEntity.setQty((BigInteger) obj[4]);
                obatPoliEntity.setFlag(obj[5].toString());
                obatPoliEntity.setAction(obj[6].toString());
                obatPoliEntity.setCreatedDate((Timestamp) obj[7]);
                obatPoliEntity.setCreatedWho(obj[8].toString());
                obatPoliEntity.setLastUpdate((Timestamp) obj[9]);
                obatPoliEntity.setLastUpdateWho(obj[10].toString());
                obatPoliEntity.setTujuanPelayanan(obj[11].toString());

                if (obj[12] != null && !"".equalsIgnoreCase(obj[12].toString())) {
                    obatPoliEntity.setDiterimaFlag(obj[12].toString());
                }

                if (obj[13] != null && !"".equalsIgnoreCase(obj[13].toString())) {
                    obatPoliEntity.setRetureFlag(obj[13].toString());
                }

                listOfResults.add(obatPoliEntity);
            }
        }


        return listOfResults;
    }

    public List<PermintaanObatPoli> getListPermintaanPoli(PermintaanObatPoli bean) {

        String idPelayanan = "%";
        String idObat = "%";
        String idPermintaanObatPoli = "%";
        String idApprovalObat = "%";
        String diterimaFlag = "%";

        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
            idPelayanan = bean.getIdPelayanan();
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            idObat = bean.getIdObat();
        }
        if (bean.getIdPermintaanObatPoli() != null && !"".equalsIgnoreCase(bean.getIdPermintaanObatPoli())) {
            idPermintaanObatPoli = bean.getIdPermintaanObatPoli();
        }
        if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {
            idApprovalObat = bean.getIdApprovalObat();
        }
        if (bean.getDiterimaFlag() != null && !"".equalsIgnoreCase(bean.getDiterimaFlag())) {
            diterimaFlag = bean.getDiterimaFlag();
        }

        String SQL = "\n" +
                "SELECT \n" +
                "pop.id_permintaan_obat_poli,\n" +
                "pop.id_obat,\n" +
                "pop.id_pelayanan,\n" +
                "pop.id_approval_obat,\n" +
                "pop.flag,\n" +
                "pop.action,\n" +
                "pop.created_date,\n" +
                "pop.created_who,\n" +
                "ato.last_update,\n" +
                "ato.last_update_who,\n" +
                "ato.approval_flag,\n" +
                "ato.approve_person,\n" +
                "ato.qty\n" +
                "FROM\n" +
                "mt_simrs_permintaan_obat_poli pop \n" +
                "INNER JOIN mt_simrs_approval_transaksi_obat ato ON ato.id_approval_obat = pop.id_approval_obat\n" +
                "WHERE pop.id_pelayanan LIKE :idPelayanan \n" +
                "AND pop.id_obat LIKE :idObat \n" +
                "AND pop.diterima_flag LIKE :diterimaFlag \n" +
                "AND pop.id_approval_obat LIKE :idApprovalObat \n" +
                "AND pop.id_permintaan_obat_poli LIKE :idPermintaanObatPoli ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("idObat", idObat)
                .setParameter("idPermintaanObatPoli", idPermintaanObatPoli)
                .setParameter("idApprovalObat", idApprovalObat)
                .setParameter("diterimaFlag", diterimaFlag)
                .list();

        List<PermintaanObatPoli> listOfResults = new ArrayList<>();

        PermintaanObatPoli permintaanObatPoli;
        for (Object[] obj : results) {
            permintaanObatPoli = new PermintaanObatPoli();
            permintaanObatPoli.setIdPermintaanObatPoli(obj[0].toString());
            permintaanObatPoli.setIdObat(obj[1].toString());
            permintaanObatPoli.setIdPelayanan(obj[2].toString());
            permintaanObatPoli.setIdApprovalObat(obj[3].toString());
            permintaanObatPoli.setFlag(obj[4].toString());
            permintaanObatPoli.setAction(obj[5].toString());
            permintaanObatPoli.setCreatedDate((Timestamp) obj[6]);
            permintaanObatPoli.setCreatedWho(obj[7].toString());
            permintaanObatPoli.setLastUpdate((Timestamp) obj[8]);
            permintaanObatPoli.setLastUpdateWho(obj[9].toString());
            permintaanObatPoli.setApprovalFlag(obj[10].toString());
            permintaanObatPoli.setApprovePerson(obj[11].toString());
            permintaanObatPoli.setQty((BigInteger) obj[12]);
            listOfResults.add(permintaanObatPoli);
        }
        return listOfResults;
    }

    public List<PermintaanObatPoli> getDetailListPermintaan(PermintaanObatPoli bean, boolean isPoli) {

        String idPermintaan = "%";
        String idTujuan = "%";
        String flag = "%";

        if (bean.getIdPermintaanObatPoli() != null && !"".equalsIgnoreCase(bean.getIdPermintaanObatPoli())) {
            idPermintaan = bean.getIdPermintaanObatPoli();
        }

        if (bean.getTujuanPelayanan() != null && !"".equalsIgnoreCase(bean.getTujuanPelayanan())) {
            idTujuan = bean.getTujuanPelayanan();
        }

        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            flag = bean.getFlag();
        }


        String SQL = "";
        List<Object[]> results = new ArrayList<>();
        List<PermintaanObatPoli> listOfResults = new ArrayList<>();

        if (isPoli) {

            SQL = "SELECT b.id_obat, c.nama_obat, b.qty, c.qty as stok_gudang, b.id_approval_obat, b.qty_approve, d.qty_box, d.qty_lembar, d.qty_biji, c.lembar_per_box, c.biji_per_lembar, b.jenis_satuan\n" +
                    "FROM mt_simrs_permintaan_obat_poli a \n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat \n" +
                    "INNER JOIN im_simrs_obat c ON b.id_obat = c.id_obat\n" +
                    "INNER JOIN mt_simrs_obat_poli d ON b.id_obat = d.id_obat\n" +
                    "WHERE a.id_permintaan_obat_poli LIKE :idPermintaanObatPoli\n" +
                    "AND a.flag LIKE :flag \n" +
                    "AND b.flag LIKE :flag \n" +
                    "AND c.flag LIKE 'Y' \n" +
                    "AND d.id_pelayanan LIKE :idTujuan\n" +
                    "ORDER BY b.id_obat ASC";

            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPermintaanObatPoli", idPermintaan)
                    .setParameter("idTujuan", idTujuan)
                    .setParameter("flag", flag)
                    .list();

        } else {

            SQL = "SELECT b.id_obat, c.nama_obat, b.qty, c.qty as stok_gudang, b.id_approval_obat, b.qty_approve, c.qty_box, c.qty_lembar, c.qty_biji, c.lembar_per_box, c.biji_per_lembar, b.jenis_satuan\n" +
                    "FROM mt_simrs_permintaan_obat_poli a\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "INNER JOIN im_simrs_obat c ON b.id_obat = c.id_obat\n" +
                    "WHERE a.id_permintaan_obat_poli LIKE :idPermintaanObatPoli\n" +
                    "AND a.flag LIKE :flag\n" +
                    "AND b.flag LIKE :flag\n" +
                    "AND c.flag LIKE 'Y'\n" +
                    "ORDER BY b.id_obat ASC";

            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPermintaanObatPoli", idPermintaan)
                    .setParameter("flag", flag)
                    .list();
        }

        PermintaanObatPoli permintaanObatPoli;
        for (Object[] obj : results) {

            permintaanObatPoli = new PermintaanObatPoli();
            permintaanObatPoli.setIdObat( obj[0] == null ? "" : obj[0].toString());
            permintaanObatPoli.setNamaObat( obj[1] == null ? "" : obj[1].toString());

            if(obj[2] != null){
                permintaanObatPoli.setQty((BigInteger) obj[2]);
            }
            if(obj[3] != null){
                permintaanObatPoli.setQtyGudang((BigInteger)obj[3]);
            }

            permintaanObatPoli.setIdApprovalObat(obj[4].toString());

            if (obj[5] != null) {
                permintaanObatPoli.setQtyApprove((BigInteger) obj[5]);
            }
            if (obj[6] != null) {
                permintaanObatPoli.setQtyBox((BigInteger) obj[6]);
            }
            if (obj[7] != null) {
                permintaanObatPoli.setQtyLembar((BigInteger) obj[7]);
            }
            if (obj[8] != null) {
                permintaanObatPoli.setQtyBiji((BigInteger) obj[8]);
            }
            if (obj[9] != null) {
                permintaanObatPoli.setLembarPerBox((BigInteger) obj[9]);
            }
            if (obj[10] != null) {
                permintaanObatPoli.setBijiPerLembar((BigInteger) obj[10]);
            }
            if (obj[11] != null) {
                permintaanObatPoli.setJenisSatuan(obj[11].toString());
            }

            listOfResults.add(permintaanObatPoli);
        }
        return listOfResults;
    }

    public List<PermintaanObatPoli> getListObatDetailRequest(PermintaanObatPoli bean) {

        String idPermintaan = "%";
        String branchId = "%";

        if(bean.getIdPermintaanObatPoli() != null && !"".equalsIgnoreCase(bean.getIdPermintaanObatPoli())){
            idPermintaan = bean.getIdPermintaanObatPoli();
        }
        if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }

        List<PermintaanObatPoli> result = new ArrayList<>();
        String SQL = "SELECT a.id_permintaan_obat_poli,  b.id_obat, d.nama_obat, c.expired_date, c.qty_approve, c.id_barang, c.jenis_satuan, b.id_transaksi_obat_detail, c.id FROM mt_simrs_permintaan_obat_poli a \n" +
                "INNER JOIN (SELECT id_approval_obat, id_obat, id_transaksi_obat_detail FROM mt_simrs_transaksi_obat_detail) b ON a.id_approval_obat = b.id_approval_obat\n" +
                "INNER JOIN (SELECT expired_date, id_transaksi_obat_detail, qty_approve, id_barang, status, approve_flag, jenis_satuan, id FROM mt_simrs_transaksi_obat_detail_batch) c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                "INNER JOIN (SELECT id_obat, nama_obat FROM im_simrs_obat GROUP BY id_obat, nama_obat) d ON b.id_obat = d.id_obat\n" +
                "WHERE a.id_permintaan_obat_poli LIKE :idPermintaanObatPoli\n" +
                "AND a.branch_id LIKE :branchId\n" +
                "AND c.status = 'Y'\n" +
                "AND c.approve_flag = 'Y'\n" +
                "ORDER BY c.expired_date ASC";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPermintaanObatPoli", idPermintaan)
                .setParameter("branchId", branchId)
                .list();

        PermintaanObatPoli permintaanObatPoli;
        for (Object[] obj : results) {

            permintaanObatPoli = new PermintaanObatPoli();
            permintaanObatPoli.setIdPermintaanObatPoli( obj[0] == null ? "" : obj[0].toString());
            permintaanObatPoli.setIdObat(obj[1] == null ? "" : obj[1].toString());
            permintaanObatPoli.setNamaObat(obj[2] == null ? "" : obj[2].toString());
            if(obj[3] != null){
                permintaanObatPoli.setExpiredDate((Date) obj[3]);
            }
            if(obj[4] != null){
                permintaanObatPoli.setQtyApprove((BigInteger) obj[4]);
            }
            permintaanObatPoli.setIdBarang(obj[5] == null ? "" : obj[5].toString());
            permintaanObatPoli.setJenisSatuan(obj[6] == null ? "": obj[6].toString());
            permintaanObatPoli.setIdTransaksiObatDetail(obj[7] == null ? "": obj[7].toString());
            permintaanObatPoli.setIdBatch(obj[8] == null ? "": obj[8].toString());

            result.add(permintaanObatPoli);
        }

        return result;
    }

    public List<TransaksiObatDetail> getListOldPermintaan(String idPermintaan){

        String SQL = "SELECT\n" +
                "tb.id_transaksi_obat_detail,\n" +
                "tb.id_barang,\n" +
                "ob.nama_obat,\n" +
                "od.qty,\n" +
                "tb.qty_approve,\n" +
                "tb.jenis_satuan,\n" +
                "op.id_permintaan_obat_poli,\n" +
                "od.id_obat\n" +
                "FROM mt_simrs_transaksi_obat_detail_batch tb\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail od ON od.id_transaksi_obat_detail = tb.id_transaksi_obat_detail\n" +
                "INNER JOIN (SELECT id_obat, nama_obat FROM im_simrs_obat GROUP BY id_obat, nama_obat) ob ON ob.id_obat = od.id_obat\n" +
                "INNER JOIN mt_simrs_permintaan_obat_poli op ON op.id_approval_obat = od.id_approval_obat\n" +
                "WHERE tb.status = 'Y'\n" +
                "AND tb.diterima_flag = 'Y' \n" +
                "AND op.id_permintaan_obat_poli = :id\n" +
                "AND tb.id_barang is not null\n" +
                "AND tb.qty_approve > 0\n" +
                "OR tb.qty_approve != null\n" +
                "ORDER BY od.id_obat ASC\n";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idPermintaan)
                .list();

        List<TransaksiObatDetail> obatDetails = new ArrayList<>();
        if (results.size() > 0){
            TransaksiObatDetail obatDetail;
            for (Object[] obj : results){
                obatDetail = new TransaksiObatDetail();
                obatDetail.setIdTransaksiObatDetail(obj[0].toString());
                obatDetail.setIdBarang(obj[1].toString());
                obatDetail.setNamaObat(obj[2].toString());
                obatDetail.setQty((BigInteger) obj[3]);
                obatDetail.setQtyApprove((BigInteger) obj[4]);
                obatDetail.setJenisSatuan(obj[5].toString());
                obatDetail.setIdObat(obj[7].toString());
                obatDetails.add(obatDetail);
            }
        }
        return obatDetails;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_permintaan_detail_poli')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
