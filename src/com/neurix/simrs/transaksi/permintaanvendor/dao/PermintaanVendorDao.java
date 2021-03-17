package com.neurix.simrs.transaksi.permintaanvendor.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pabrikobat.model.PabrikObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.DocPo;
import com.neurix.simrs.transaksi.permintaanvendor.model.MtSimrsPermintaanVendorEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatBatch;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class PermintaanVendorDao extends GenericDao<MtSimrsPermintaanVendorEntity, String> {

    @Override
    protected Class<MtSimrsPermintaanVendorEntity> getEntityClass() {
        return MtSimrsPermintaanVendorEntity.class;
    }

    @Override
    public List<MtSimrsPermintaanVendorEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsPermintaanVendorEntity.class);
        if (mapCriteria.get("id_permintaan_vendor") != null) {
            criteria.add(Restrictions.eq("idPermintaanVendor", mapCriteria.get("id_permintaan_vendor").toString()));
        }
        if (mapCriteria.get("id_approval_obat") != null) {
            criteria.add(Restrictions.eq("idApprovalObat", mapCriteria.get("id_approval_obat").toString()));
        }
        if (mapCriteria.get("branch_id") != null) {
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }
        if (mapCriteria.get("tipe_transaksi") != null) {
            criteria.add(Restrictions.eq("tipeTransaksi", mapCriteria.get("tipe_transaksi").toString()));
        }
        criteria.addOrder(Order.desc("createdDate"));
        List<MtSimrsPermintaanVendorEntity> list = criteria.list();
        return list;
    }

    public Boolean isAvailNotConfirm(String idApprovalObat) {

        Boolean check = true;
        if (!"".equalsIgnoreCase(idApprovalObat)) {
            String SQL = "SELECT odb.id_transaksi_obat_detail, odb.id\n" +
                    "FROM mt_simrs_transaksi_obat_detail od\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail_batch odb ON odb.id_transaksi_obat_detail = od.id_transaksi_obat_detail\n" +
                    "WHERE odb.approve_flag is null\n" +
                    "AND od.id_approval_obat = :id";

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idApprovalObat)
                    .list();

            if (result.size() > 0) {
                check = false;
            } else {
                String SQL2 = "SELECT odb.id_transaksi_obat_detail, odb.id\n" +
                        "FROM mt_simrs_transaksi_obat_detail od\n" +
                        "INNER JOIN mt_simrs_transaksi_obat_detail_batch odb ON odb.id_transaksi_obat_detail = od.id_transaksi_obat_detail\n" +
                        "WHERE od.id_approval_obat = :id2";

                List<Object[]> result2 = this.sessionFactory.getCurrentSession().createSQLQuery(SQL2)
                        .setParameter("id2", idApprovalObat)
                        .list();

                if (result2.size() == 0) {
                    check = false;
                }
            }
        }
        return check;
    }

    public List<TransaksiObatDetail> getListObatByBatch(String idPermintaan, Integer noBatch) {

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if (!"".equalsIgnoreCase(idPermintaan) && idPermintaan != null && !"".equalsIgnoreCase(noBatch.toString()) && noBatch != null) {

            String SQL = "SELECT \n" +
                    "b.id_obat,\n" +
                    "d.nama_obat,\n" +
                    "b.qty,\n" +
                    "c.qty_approve,\n" +
                    "b.jenis_satuan,\n" +
                    "CASE \n" +
                    "    WHEN b.jenis_satuan = 'box' THEN b.average_harga_box \n" +
                    "    WHEN b.jenis_satuan = 'lembar' THEN b.average_harga_lembar\n" +
                    "    WHEN b.jenis_satuan = 'biji' THEN b.average_harga_biji\n" +
                    "    ELSE null END as harga,\n" +
                    "b.id_transaksi_obat_detail,\n" +
                    "c.diskon,\n" +
                    "c.bruto,\n" +
                    "c.netto\n" +
                    "FROM mt_simrs_permintaan_obat_vendor a\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "INNER JOIN (\n" +
                    "\tSELECT \n" +
                    "\tid_transaksi_obat_detail, \n" +
                    "\tno_batch, SUM(qty_approve) as qty_approve, \n" +
                    "\tdiskon, \n" +
                    "\tbruto, \n" +
                    "\tnetto\n" +
                    "\tFROM mt_simrs_transaksi_obat_detail_batch \n" +
                    "\tGROUP BY \n" +
                    "\tid_transaksi_obat_detail, \n" +
                    "\tno_batch, \n" +
                    "\tdiskon, \n" +
                    "\tbruto, \n" +
                    "\tnetto\n" +
                    ") c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                    "INNER JOIN (\n" +
                    "\tSELECT id_obat, nama_obat FROM im_simrs_obat GROUP BY id_obat, nama_obat\n" +
                    ") d ON b.id_obat = d.id_obat\n" +
                    "WHERE a.id_permintaan_obat_vendor = :id\n" +
                    "AND c.no_batch = :noBatch";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPermintaan)
                    .setParameter("noBatch", noBatch)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    TransaksiObatDetail detail = new TransaksiObatDetail();
                    detail.setIdObat(obj[0] == null ? "" : obj[0].toString());
                    detail.setNamaObat(obj[1] == null ? "" : obj[1].toString());
                    detail.setQty(obj[2] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[2].toString()));
                    detail.setQtyApprove(obj[3] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[3].toString()));
                    detail.setJenisSatuan(obj[4] == null ? "" : obj[4].toString());
                    detail.setHargaPo(obj[5] == null ? new BigDecimal(String.valueOf(0)) : new BigDecimal(obj[5].toString()));
                    detail.setIdTransaksiObatDetail(obj[6] == null ? "" : obj[6].toString());
                    detail.setDiskon(obj[7] == null ? new BigDecimal(0) : (BigDecimal) obj[7]);
                    detail.setBruto(obj[8] == null ? new BigDecimal(0) : (BigDecimal) obj[8]);
                    detail.setNetto(obj[9] == null ? new BigDecimal(0) : (BigDecimal) obj[9]);
                    obatDetailList.add(detail);
                }
            }
        }
        return obatDetailList;
    }

    public List<TransaksiObatDetail> getListObatByBatchByDo(String idPermintaan, String noDo) {

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if (!"".equalsIgnoreCase(idPermintaan) && idPermintaan != null && noDo != null && !"".equalsIgnoreCase(noDo) ) {

            String SQL = "SELECT  \n" +
                    "b.id_obat, \n" +
                    "d.nama_obat, \n" +
                    "b.qty, \n" +
                    "c.qty_approve, \n" +
                    "b.jenis_satuan, \n" +
                    "CASE  \n" +
                    "    WHEN b.jenis_satuan = 'box' THEN b.average_harga_box  \n" +
                    "    WHEN b.jenis_satuan = 'lembar' THEN b.average_harga_lembar \n" +
                    "    WHEN b.jenis_satuan = 'biji' THEN b.average_harga_biji \n" +
                    "    ELSE null END as harga, \n" +
                    "b.id_transaksi_obat_detail, \n" +
                    "c.diskon, \n" +
                    "c.bruto, \n" +
                    "c.netto, \n" +
                    "c.no_batch \n" +
                    "FROM mt_simrs_permintaan_obat_vendor a \n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat \n" +
                    "INNER JOIN ( \n" +
                    "     SELECT  \n" +
                    "\t\t id_transaksi_obat_detail,  \n" +
                    "\t\t no_batch, \n" +
                    "\t\t SUM(qty_approve) as qty_approve,  \n" +
                    "\t\t diskon,  \n" +
                    "\t\t bruto,  \n" +
                    "\t\t netto,\n" +
                    "\t\t no_do\n" +
                    "     FROM mt_simrs_transaksi_obat_detail_batch  \n" +
                    "     GROUP BY  \n" +
                    "\t\t id_transaksi_obat_detail,  \n" +
                    "\t\t no_batch,  \n" +
                    "\t\t diskon,  \n" +
                    "\t\t bruto,  \n" +
                    "\t\t netto,\n" +
                    "\t\t no_do\n" +
                    ") c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail \n" +
                    "INNER JOIN ( \n" +
                    "     SELECT id_obat, nama_obat FROM im_simrs_obat GROUP BY id_obat, nama_obat \n" +
                    ") d ON b.id_obat = d.id_obat \n" +
                    "WHERE a.id_permintaan_obat_vendor = :id\n" +
                    "AND c.no_do = :noDo";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPermintaan)
                    .setParameter("noDo", noDo)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    TransaksiObatDetail detail = new TransaksiObatDetail();
                    detail.setIdObat(obj[0] == null ? "" : obj[0].toString());
                    detail.setNamaObat(obj[1] == null ? "" : obj[1].toString());
                    detail.setQty(obj[2] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[2].toString()));
                    detail.setQtyApprove(obj[3] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[3].toString()));
                    detail.setJenisSatuan(obj[4] == null ? "" : obj[4].toString());
                    detail.setHargaPo(obj[5] == null ? new BigDecimal(String.valueOf(0)) : new BigDecimal(obj[5].toString()));
                    detail.setIdTransaksiObatDetail(obj[6] == null ? "" : obj[6].toString());
                    detail.setDiskon(obj[7] == null ? new BigDecimal(0) : (BigDecimal) obj[7]);
                    detail.setBruto(obj[8] == null ? new BigDecimal(0) : (BigDecimal) obj[8]);
                    detail.setNetto(obj[9] == null ? new BigDecimal(0) : (BigDecimal) obj[9]);
                    detail.setNoBatch(obj[10] == null ? new Integer(0) : (Integer) obj[10]);
                    obatDetailList.add(detail);
                }
            }
        }
        return obatDetailList;
    }

    public TransaksiObatDetail getDiskonBrutoNetto(String idObat){
        TransaksiObatDetail detail = new TransaksiObatDetail();
        if(idObat != null && !"".equalsIgnoreCase(idObat)) {
            String SQL = "SELECT\n" +
                    "a.id_transaksi_obat_detail,\n" +
                    "b.diskon,\n" +
                    "b.bruto,\n" +
                    "b.netto\n" +
                    "FROM\n" +
                    "\t(\n" +
                    "\tSELECT \n" +
                    "\ta.id_transaksi_obat_detail,\n" +
                    "\ta.id_obat,\n" +
                    "\ta.created_date\n" +
                    "\tFROM mt_simrs_transaksi_obat_detail a\n" +
                    "\tINNER JOIN mt_simrs_approval_transaksi_obat b\n" +
                    "\tON a.id_approval_obat = b.id_approval_obat\n" +
                    "\tINNER JOIN mt_simrs_permintaan_obat_vendor c\n" +
                    "\tON a.id_approval_obat = c.id_approval_obat\n" +
                    "\tWHERE a.id_obat = :idObat\n" +
                    "\tAND b.tipe_permintaan = '004'\n" +
                    "\tAND c.tipe_transaksi NOT LIKE 'reture'\n" +
                    "\tORDER BY a.created_date DESC\n" +
                    "\tLIMIT 1\n" +
                    "\t) a\n" +
                    "INNER JOIN \n" +
                    "\t(\n" +
                    "\tSELECT \n" +
                    "\tid_transaksi_obat_detail,\n" +
                    "\tdiskon,\n" +
                    "\tbruto,\n" +
                    "\tnetto\n" +
                    "\tFROM \n" +
                    "\tmt_simrs_transaksi_obat_detail_batch\n" +
                    "\t) b\n" +
                    "ON a.id_transaksi_obat_detail = b.id_transaksi_obat_detail\n" +
                    "\n";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idObat", idObat)
                    .list();
            if(result.size() > 0){
                Object[] objects = result.get(0);
                detail.setIdObat(objects[0] != null ? objects[0].toString() : "");
                detail.setDiskon(objects[1] != null ? new BigDecimal(objects[1].toString()) : new BigDecimal(String.valueOf(0)));
                detail.setBruto(objects[2] != null ? new BigDecimal(objects[2].toString()) : new BigDecimal(String.valueOf(0)));
                detail.setNetto(objects[3] != null ? new BigDecimal(objects[3].toString()) : new BigDecimal(String.valueOf(0)));
            }
        }
        return detail;
    }

    public List<DocPo> getListDaftarDoc(String idPermintaan, String noBatch){

        String SQL = "SELECT id_item, id_permintaan_obat_vendor, jenis_nomor, no_batch\n" +
                "FROM it_simrs_doc_po \n" +
                "WHERE id_permintaan_obat_vendor = :idPermintaan \n" +
                "AND no_batch = :noBatch \n" +
                "GROUP BY id_item, id_permintaan_obat_vendor, jenis_nomor, no_batch\n" +
                "ORDER BY jenis_nomor";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPermintaan", idPermintaan)
                .setParameter("noBatch", new Integer(noBatch))
                .list();

        List<DocPo> docPoList = new ArrayList<>();
        if (result.size() > 0){
            for (Object[] obj : result){
                DocPo docPo = new DocPo();
                docPo.setIdItem(obj[0].toString());
                docPo.setIdPermintaanObatVendor(obj[1] == null ? "" : obj[1].toString());
                docPo.setJenisNomor(obj[2] == null ? "" : obj[2].toString());
                docPo.setNoBatch(obj[3] == null ? new Integer(0) : (Integer) obj[3]);
                docPoList.add(docPo);
            }
        }
        return docPoList;

    }

    public List<DocPo> getListImg(String idItem){

        String SQL = "SELECT \n" +
                "id_item,\n" +
                "url_img\n" +
                "FROM it_simrs_doc_po\n" +
                "WHERE id_item = :idItem \n" +
                "ORDER BY created_date DESC";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idItem", idItem)
                .list();

        List<DocPo> docPoList = new ArrayList<>();
        if (result.size() > 0){
            for (Object[] obj : result){
                DocPo docPo = new DocPo();
                docPo.setIdItem(obj[0].toString());
                docPo.setUrlImg(obj[1] == null ? "" : obj[1].toString());
                docPoList.add(docPo);
            }
        }
        return docPoList;

    }

    public List<TransaksiObatBatch> getListBatchByJenis(String idItem, String jenis){

        if (idItem != null && !"".equalsIgnoreCase(idItem)){

            String where = "";
            if ("faktur".equalsIgnoreCase(jenis))
                where = "WHERE no_faktur = '"+idItem+"' \n";

            if ("invoice".equalsIgnoreCase(jenis))
                where = "WHERE no_invoice = '"+idItem+"' \n";

            if ("do".equalsIgnoreCase(jenis))
                where = "WHERE no_do = '"+idItem+"' \n";

            String SQL = "SELECT no_faktur, tanggal_faktur, no_invoice, no_do, jenis \n" +
                    "FROM mt_simrs_transaksi_obat_detail_batch \n" + where;

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

            List<TransaksiObatBatch> obatBatches = new ArrayList<>();
            if (result.size() > 0){
                for (Object[] obj : result){
                    TransaksiObatBatch obatBatch = new TransaksiObatBatch();
                    obatBatch.setNoFaktur(obj[0] == null ? "" : obj[0].toString());
                    obatBatch.setTanggalFaktur(obj[1] == null ? null : (Date) obj[1]);
                    obatBatch.setNoInvoice(obj[2] == null ? "" : obj[2].toString());
                    obatBatch.setNoDo(obj[3] == null ? "" : obj[3].toString());
                    obatBatch.setJenis(obj[4] == null ? "" : obj[4].toString());
                    obatBatches.add(obatBatch);
                }
            }
            return obatBatches;
        }
        return null;
    }

    public List<TransaksiObatBatch> getListBatchByJenis(String idItem, String jenis, String idTransaksi, String batch){

        if (idItem != null && !"".equalsIgnoreCase(idItem)){

            String where = "";
            if ("faktur".equalsIgnoreCase(jenis))
                where = "WHERE a.no_faktur = '"+idItem+"' \n";

            if ("invoice".equalsIgnoreCase(jenis))
                where = "WHERE a.no_invoice = '"+idItem+"' \n";

            if ("do".equalsIgnoreCase(jenis))
                where = "WHERE a.no_do = '"+idItem+"' \n";

            String SQL = "SELECT a.no_faktur, a.tanggal_faktur, a.no_invoice, a.no_do, a.jenis \n" +
                    "FROM mt_simrs_transaksi_obat_detail_batch a\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail b on b.id_transaksi_obat_detail = a.id_transaksi_obat_detail\n"
                    + where +
                    "AND (b.id_approval_obat, a.no_batch) != ( :idApproval, :noBatch )";

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idApproval", idTransaksi)
                    .setParameter("noBatch", Integer.valueOf(batch))
                    .list();

            List<TransaksiObatBatch> obatBatches = new ArrayList<>();
            if (result.size() > 0){
                for (Object[] obj : result){
                    TransaksiObatBatch obatBatch = new TransaksiObatBatch();
                    obatBatch.setNoFaktur(obj[0] == null ? "" : obj[0].toString());
                    obatBatch.setTanggalFaktur(obj[1] == null ? null : (Date) obj[1]);
                    obatBatch.setNoInvoice(obj[2] == null ? "" : obj[2].toString());
                    obatBatch.setNoDo(obj[3] == null ? "" : obj[3].toString());
                    obatBatch.setJenis(obj[4] == null ? "" : obj[4].toString());
                    obatBatches.add(obatBatch);
                }
            }
            return obatBatches;
        }
        return null;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_permintaan_vendor')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    // 2021-02-17, Sigit. pencarian pabrik obat
    // dibagi menjadi dua, "specific" & "all"
    // specific -> untuk mencari pabrik berdasarkan yg telah terdaftar ke master obat
    // all -> untuk memunculkan semua data pabrik obat pada master pabrik obat
    // jika specific tidak ditemukan / belum ada data pada master obat. maka get data semua pabrik pada master pabrik
    public List<PabrikObat> getListDataPabrikObatForPO(String idObat, String tipePencarian){

        List<PabrikObat> pabrikObatList = new ArrayList<>();
        if ("all".equalsIgnoreCase(tipePencarian)){
            pabrikObatList = getAllMasterObat();
        } else {

            String SQL = "SELECT \n" +
                    "ob.id_pabrik_obat,\n" +
                    "pb.nama\n" +
                    "FROM (\n" +
                    "\tSELECT\n" +
                    "\ta.*\n" +
                    "\tFROM \n" +
                    "\t(\n" +
                    "\t\tSELECT\n" +
                    "\t\ta.id_obat,\n" +
                    "\t\ta.id_pabrik_obat,\n" +
                    "\t\tMAX(a.created_date) as created_date\n" +
                    "\t\tFROM im_simrs_obat a\n" +
                    "\t\tGROUP BY a.id_obat, a.id_pabrik_obat\n" +
                    "\t) a ORDER BY id_obat, created_date DESC\n" +
                    ") ob\n" +
                    "INNER JOIN (SELECT * FROM im_simrs_pabrik_obat WHERE flag = 'Y') pb ON pb.id = ob.id_pabrik_obat\n" +
                    "WHERE ob.id_obat = '"+idObat+"'";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
            if (results.size() > 0){
                for (Object[] obj : results){
                    PabrikObat pabrikObat = new PabrikObat();
                    pabrikObat.setId(obj[0].toString());
                    pabrikObat.setNama(obj[1].toString());
                    pabrikObatList.add(pabrikObat);
                }
            }
//            else {
//                pabrikObatList = getAllMasterObat();
//            }
        }
        return pabrikObatList;

    }

    private List<PabrikObat> getAllMasterObat(){
        String SQL = "SELECT id, nama FROM im_simrs_pabrik_obat WHERE flag = 'Y' ORDER BY nama";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<PabrikObat> pabrikObatList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                PabrikObat pabrikObat = new PabrikObat();
                pabrikObat.setId(obj[0].toString());
                pabrikObat.setNama(obj[1].toString());
                pabrikObatList.add(pabrikObat);
            }
        }
        return pabrikObatList;
    }

    public TransaksiObatDetail getDataFisikObatMasukBatch(BigInteger idBatch){

        String SQL = "SELECT\n" +
                "a.id_transaksi_obat_detail,\n" +
                "SUM(b.qty_approve) as qty_approve,\n" +
                "a.lembar_per_box, \n" +
                "a.biji_per_lembar,\n" +
                "SUM(b.netto) as netto,\n" +
                "b.jenis_satuan,\n" +
                "a.id_pabrik_obat,\n" +
                "a.nomor_produksi,\n" +
                "a.mrek \n"+
                "FROM mt_simrs_transaksi_obat_detail a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail_batch b on b.id_transaksi_obat_detail = a.id_transaksi_obat_detail\n" +
                "WHERE b.id = "+idBatch+"\n" +
                "GROUP BY \n" +
                "a.id_transaksi_obat_detail,\n" +
                "a.lembar_per_box, \n" +
                "a.biji_per_lembar, \n" +
                "b.jenis_satuan,\n" +
                "a.id_pabrik_obat,\n" +
                "a.nomor_produksi,\n" +
                "a.mrek";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (result.size() > 0){
            Object[] obj = result.get(0);
            TransaksiObatDetail trans = new TransaksiObatDetail();
            trans.setIdTransaksiObatDetail(obj[0].toString());
            trans.setQtyApprove(obj[1] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[1].toString()));
            trans.setLembarPerBox(obj[2] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[2].toString()));
            trans.setBijiPerLembar(obj[3] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[3].toString()));
            trans.setNetto(obj[4] == null ? new BigDecimal(0) : new BigDecimal(obj[4].toString()));
            trans.setJenisSatuan(obj[5] == null ? "" : obj[5].toString());
            trans.setJenisSatuan(obj[5] == null ? "" : obj[5].toString());
            trans.setIdPabrikObat(obj[6] == null ? "" : obj[6].toString());
            trans.setNomorProduksi(obj[7] == null ? "" : obj[7].toString());
            trans.setMerek(obj[8] == null ? "" : obj[8].toString());
            trans.setQtyBox(trans.getQtyBox());
            BigInteger jumlahBiji   = trans.getQtyApprove();
            trans.setQtyBiji(jumlahBiji);
            trans.setAverageHargaBiji(trans.getNetto().divide(new BigDecimal(jumlahBiji), 2, RoundingMode.HALF_UP));
            return trans;
        }

        return null;
    }

    public PabrikObat getPabrikObat(String idPabrik){

        String SQL = "SELECT id, nama FROM im_simrs_pabrik_obat\n" +
                "WHERE id = '"+idPabrik+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            Object[] obj = list.get(0);
            PabrikObat pbo = new PabrikObat();
            pbo.setId(obj[0].toString());
            pbo.setNama(obj[1].toString());
            return pbo;
        }

        return null;
    }

    public Obat cekNoProduksi(String noProduksi){
        Obat obat = new Obat();
        String SQL = "SELECT * FROM (\n" +
                "SELECT\n" +
                "id_obat,\n" +
                "nama_obat,\n" +
                "biji_per_lembar,\n" +
                "lembar_per_box,\n" +
                "merk,\n" +
                "id_pabrik_obat,\n" +
                "RANK() OVER(PARTITION BY id_obat ORDER BY created_date DESC) as rank\n" +
                "FROM im_simrs_obat\n" +
                "WHERE nomor_produksi = '"+noProduksi+"'\n" +
                ") a WHERE rank = 1";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            Object[] obj = list.get(0);
            obat.setIdObat(obj[0] != null ? obj[0].toString() : "");
            obat.setNamaObat(obj[1] != null ? obj[1].toString() : "");
            obat.setBijiPerLembar(obj[2] != null ? (BigInteger) obj[2] : null);
            obat.setLembarPerBox(obj[3] != null ? (BigInteger) obj[3] : null);
            obat.setMerk(obj[4] != null ? obj[4].toString() : "");
            obat.setIdPabrik(obj[5] != null ? obj[5].toString() : "");
            obat.setStatus("success");
            obat.setMsg("ok");
        }else{
            obat.setStatus("error");
            obat.setMsg("notoke");
        }

        return obat;
    }

    public String getJenisPo(String idPermintaanVendor){

        String SQL = "SELECT  \n" +
                "od.flag_obat_bpjs \n" +
                "FROM (SELECT id_transaksi_obat_detail, id_approval_obat, flag_obat_bpjs FROM mt_simrs_transaksi_obat_detail) od\n" +
                "INNER JOIN (SELECT id_approval_obat FROM mt_simrs_approval_transaksi_obat) ato ON ato.id_approval_obat = od.id_approval_obat\n" +
                "INNER JOIN (SELECT id_approval_obat, id_permintaan_obat_vendor FROM mt_simrs_permintaan_obat_vendor) ov ON ov.id_approval_obat = ato.id_approval_obat\n" +
                "WHERE ov.id_permintaan_obat_vendor = '"+idPermintaanVendor+"' \n" +
                "LIMIT 1";

        List<Object> objs = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (objs.size() > 0){
            boolean flagBpjs = "Y".equalsIgnoreCase(objs.get(0).toString());
            if (flagBpjs){
                return "BPJS";
            }
            return "UMUM";
        }
        return "";
    }

    public String countJumlahItemPo(String idPermintaanVendor){

        String SQL = "SELECT  \n" +
                "COUNT(id_transaksi_obat_detail) as jumlah \n" +
                "FROM (SELECT id_transaksi_obat_detail, id_approval_obat, flag_obat_bpjs FROM mt_simrs_transaksi_obat_detail) od\n" +
                "INNER JOIN (SELECT id_approval_obat FROM mt_simrs_approval_transaksi_obat) ato ON ato.id_approval_obat = od.id_approval_obat\n" +
                "INNER JOIN (SELECT id_approval_obat, id_permintaan_obat_vendor FROM mt_simrs_permintaan_obat_vendor) ov ON ov.id_approval_obat = ato.id_approval_obat\n" +
                "WHERE ov.id_permintaan_obat_vendor = '"+idPermintaanVendor+"' ";

        List<Object> objs = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (objs.size() > 0){
            BigInteger jumlah = (BigInteger) objs.get(0);
            return jumlah.toString();
        }
        return "";
    }
}
