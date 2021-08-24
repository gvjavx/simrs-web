package com.neurix.simrs.transaksi.paketperiksa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/03/20.
 */
public class ItemPaketDao extends GenericDao<MtSimrsItemPaketEntity, String> {

    @Override
    protected Class<MtSimrsItemPaketEntity> getEntityClass() {
        return MtSimrsItemPaketEntity.class;
    }

    @Override
    public List<MtSimrsItemPaketEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsItemPaketEntity.class);

        if (mapCriteria.get("id_item_paket") != null){
            criteria.add(Restrictions.eq("idItemPaket", mapCriteria.get("id_item_paket").toString()));
        }
        if (mapCriteria.get("id_paket") != null){
            criteria.add(Restrictions.eq("idPaket", mapCriteria.get("id_paket").toString()));
        }
        if (mapCriteria.get("id_item") != null){
            criteria.add(Restrictions.eq("idItem", mapCriteria.get("id_item").toString()));
        }
        if (mapCriteria.get("id_kategori_item") != null){
            criteria.add(Restrictions.eq(
                    "idKategoriItem", mapCriteria.get("id_kategori_item").toString()));
        }
        if (mapCriteria.get("jenis_item") != null){
            criteria.add(Restrictions.eq("jenisItem", mapCriteria.get("jenis_item").toString()));
        }

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_item_paket')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public ItemPaket getSumTarifPaketLab(String idPaket, String idLab){

        if(idPaket != null || idLab != null || !"".equalsIgnoreCase(idPaket) || !"".equalsIgnoreCase(idLab)){
            return null;
        }
        String SQL = "SELECT \n" +
                "id_paket, \n" +
                "id_kategori_item,\n" +
                "SUM(harga) as jumlah\n" +
                "FROM mt_simrs_item_paket_periksa\n" +
                "WHERE id_kategori_item = :idlab \n" +
                "AND id_paket = :idpaket \n" +
                "GROUP BY id_paket, id_kategori_item";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idpaket", idPaket)
                .setParameter("idlab", idLab)
                .list();

        ItemPaket itemPaket = new ItemPaket();
        BigDecimal total = new BigDecimal(0);
        if (results.size() > 0){
            for (Object[] obj : results){
                itemPaket.setIdPaket(obj[0] == null ? "" : obj[0].toString());
                itemPaket.setIdKategoriItem(obj[1] == null ? "" : obj[1].toString());
                total = obj[2] == null ? new BigDecimal(0) : (BigDecimal) obj[2];
                itemPaket.setTarif(total);
            }
            return itemPaket;
        }
        return null;
    }

    // Fahmi 2021-08-16, for getting list paket tindakan with tindakan name
    public List<Tindakan> getListPaketTIndakan(Tindakan tin, String idPaket) {
        List<Tindakan> tindakanList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "    ht.id_header_tindakan,\n" +
                "    ht.nama_tindakan,\n" +
                "    ht.kategori_ina_bpjs,\n" +
                "    ht.nama_tindakan,\n" +
                "    tin.id_tindakan,\n" +
                "    item.harga,\n" +
                "    tin.tarif_bpjs,\n" +
                "    tin.diskon,\n" +
                "    tin.id_kategori_tindakan,\n" +
                "    katin.kategori_tindakan,\n" +
                "    tin.id_pelayanan,\n" +
                "    hp.nama_pelayanan,\n" +
                "    f.branch_id,\n" +
                "    f.branch_name,\n" +
                "    tin.is_ina,\n" +
                "    tin.is_elektif,\n" +
                "    ht.flag_konsul_gizi\n" +
                "FROM\n" +
                "     mt_simrs_item_paket_periksa AS item\n" +
                "     LEFT JOIN im_simrs_tindakan AS tin ON item.id_item = tin.id_tindakan\n" +
                "     LEFT JOIN im_simrs_header_tindakan AS ht ON ht.id_header_tindakan = tin.id_header_tindakan\n" +
                "     LEFT JOIN im_simrs_kategori_tindakan katin ON tin.id_kategori_tindakan = katin.id_kategori_tindakan\n" +
                "     LEFT JOIN im_simrs_pelayanan AS p ON tin.id_pelayanan = p.id_pelayanan\n" +
                "     LEFT JOIN im_simrs_header_pelayanan AS hp ON hp.id_header_pelayanan = p.id_header_pelayanan\n" +
                "     LEFT JOIN im_branches f ON tin.branch_id = f.branch_id\n" +
                "\n" +
                "WHERE \n"+
                "    item.flag = 'Y' AND\n" +
                "    item.id_paket = :idPaket AND\n" +
                "    item.id_item = :idItem";

        List<Object[]> result;
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPaket", idPaket)
                .setParameter("idItem", tin.getIdTindakan())
                .list();

        if (result.size() > 0) {
            for (Object[] obj : result) {
                Tindakan tindakan = new Tindakan();
                tindakan.setIdHeaderTindakan(obj[0] != null ? obj[0].toString() : null);
                tindakan.setTindakan(obj[1] != null ? obj[1].toString() : null);
                tindakan.setKategoriInaBpjs(obj[2] != null ? obj[2].toString() : null);
                tindakan.setNamaKategoriTindakanIna(obj[3] != null ? obj[3].toString() : null);
                tindakan.setIdTindakan(obj[4] != null ? obj[4].toString() : null);
                tindakan.setTarif(obj[5] != null ? (BigInteger) obj[5] : null);
                tindakan.setTarifBpjs(obj[6] != null ? (BigInteger) obj[6] : null);
                tindakan.setDiskon(obj[7] != null ? (BigDecimal) obj[7] : null);
                tindakan.setIdKategoriTindakan(obj[8] != null ? obj[8].toString() : null);
                tindakan.setNamaKategoriTindakan(obj[9] != null ? obj[9].toString() : null);
                tindakan.setIdPelayanan(obj[10] != null ? obj[10].toString() : null);
                tindakan.setNamaPelayanan(obj[11] != null ? obj[11].toString() : null);
                tindakan.setBranchId(obj[12] != null ? obj[12].toString() : null);
                tindakan.setBranchName(obj[13] != null ? obj[13].toString() : null);
                tindakan.setIsIna(obj[14] != null ? obj[14].toString() : null);
                tindakan.setIsElektif(obj[15] != null ? obj[15].toString() : null);
                tindakan.setFlagKonsulGizi(obj[16] != null ? obj[16].toString() : null);
                tindakanList.add(tindakan);
            }
        }
        return tindakanList;
    }
}
