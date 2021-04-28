package com.neurix.simrs.transaksi.hargaobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObatPerKonsumen;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatPerKonsumenEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 23/01/20.
 */
public class HargaObatPerKonsumenDao extends GenericDao<MtSimrsHargaObatPerKonsumenEntity, String> {

    @Override
    protected Class<MtSimrsHargaObatPerKonsumenEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<MtSimrsHargaObatPerKonsumenEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsHargaObatPerKonsumenEntity.class);

        if (mapCriteria.get("id_harga_obat") != null){
            criteria.add(Restrictions.eq("idHargaObat", mapCriteria.get("id_harga_obat").toString()));
        }

        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }

        if (mapCriteria.get("id_rekanan") != null){
            criteria.add(Restrictions.eq("idRekanan", mapCriteria.get("id_rekanan").toString()));
        }

        if (mapCriteria.get("jenis_konsumen") != null){
            criteria.add(Restrictions.eq("jenisKonsumen", mapCriteria.get("jenis_konsumen").toString()));
        }

        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<MtSimrsHargaObatPerKonsumenEntity> results = criteria.list();
        return results;
    }

    private BigDecimal objToBigDecimal(Object obj){
        if (obj == null)
            return new BigDecimal(0);
        else
            return new BigDecimal(obj.toString());
    }

    private String objToString(Object obj){
        if (obj == null)
            return null;
        else
            return obj.toString();
    }

    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_harga_obat_per_konsumen')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "HPK" + sId;
    }

    public List<HargaObatPerKonsumen> listRekananAndHargaByBranch(String idObat, String branchId){

        String SQLNONREKANAN = "SELECT \n" +
                "hopk.id, \n" +
                "hopk.harga_bruto, \n" +
                "hopk.margin, \n" +
                "hopk.harga_jual, \n" +
                "hopk.jenis_konsumen,\n" +
                "CASE \n" +
                "WHEN hopk.jenis_konsumen = 'bpjs' THEN ht.harga_terakhir_bpjs\n" +
                "WHEN hopk.jenis_konsumen = 'umum' THEN ht.harga_terakhir\n" +
                "ELSE 0 END as harga_terakhir\n" +
                "FROM (SELECT * FROM mt_simrs_harga_obat_per_konsumen WHERE jenis_konsumen IN ('umum','bpjs')) hopk \n" +
                "INNER JOIN mt_simrs_harga_obat ho ON ho.id_harga_obat = hopk.id_harga_obat\n" +
                "INNER JOIN mt_simrs_harga_terakhir ht ON ht.id_obat = ho.id_obat AND ht.branch_id = ho.branch_id\n" +
                "WHERE ho.branch_id = '"+branchId+"' \n" +
                "AND ho.id_obat = '"+idObat+"'\n" +
                "ORDER BY jenis_konsumen DESC";

        String SQLREKANAN = "SELECT\n" +
                "hopk.id, \n" +
                "dro.id_detail_rekanan_ops,\n" +
                "ro.nama_rekanan,\n" +
                "ro.tipe,\n" +
                "dro.is_bpjs,\n" +
                "hopk.harga_bruto,\n" +
                "hopk.margin,\n" +
                "hopk.harga_jual,\n" +
                "CASE \n" +
                "WHEN dro.is_bpjs = 'Y' AND ro.tipe = 'khusus' THEN ht.harga_rata_bpjs\n" +
                "WHEN dro.is_bpjs = 'N' AND ro.tipe = 'khusus' THEN ht.harga_rata_umum\n" +
                "WHEN dro.is_bpjs = 'Y' AND ro.tipe = 'normal' THEN ht.harga_terakhir_bpjs\n" +
                "WHEN dro.is_bpjs = 'N' AND ro.tipe = 'normal' THEN ht.harga_terakhir\n" +
                "ELSE 0 END as harga_terakhir\n" +
                "FROM (SELECT * FROM im_simrs_detail_rekanan_ops WHERE flag_parent = 'Y') dro\n" +
                "INNER JOIN im_simrs_rekanan_ops ro ON ro.id_rekanan_ops = dro.id_rekanan_ops\n" +
                "LEFT JOIN mt_simrs_harga_obat_per_konsumen hopk ON hopk.id_rekanan = dro.id_detail_rekanan_ops\n" +
                "LEFT JOIN mt_simrs_harga_terakhir ht ON ht.branch_id = dro.branch_id\n" +
                "WHERE dro.branch_id = '"+branchId+"'\n" +
                "AND ht.id_obat = '"+idObat+"'\n" +
                "ORDER BY ro.nama_rekanan";

        List<Object[]> listNonRekanan = this.sessionFactory.getCurrentSession().createSQLQuery(SQLNONREKANAN).list();
        List<Object[]> listRekanan = this.sessionFactory.getCurrentSession().createSQLQuery(SQLREKANAN).list();

        List<HargaObatPerKonsumen> perKonsumenList = new ArrayList<>();

        if (listNonRekanan.size() > 0){
            for (Object[] obj : listNonRekanan){
                HargaObatPerKonsumen obatPerKonsumen = new HargaObatPerKonsumen();
                obatPerKonsumen.setId(objToString(obj[0]));
                obatPerKonsumen.setHargaBruto(objToBigDecimal(obj[1]));
                obatPerKonsumen.setMargin(objToBigDecimal(obj[2]));
                obatPerKonsumen.setHargaJual(objToBigDecimal(obj[3]));
                obatPerKonsumen.setJenisKonsumen(objToString(obj[4]));
                obatPerKonsumen.setHargaTerakhir(objToBigDecimal(obj[5]));
                obatPerKonsumen.setNamaKonsumen(obatPerKonsumen.getJenisKonsumen().toUpperCase());
                perKonsumenList.add(obatPerKonsumen);
            }
        }

        if (listRekanan.size() > 0){
            for (Object[] obj : listRekanan){
                HargaObatPerKonsumen obatPerKonsumen = new HargaObatPerKonsumen();
                obatPerKonsumen.setId(objToString(obj[0]));
                obatPerKonsumen.setIdRekanan(objToString(obj[1]));
                obatPerKonsumen.setNamaKonsumen(objToString(obj[2]));
                obatPerKonsumen.setTipeRekanan(objToString(obj[3]));
                obatPerKonsumen.setFlagIsBpjs(objToString(obj[4]));
                obatPerKonsumen.setHargaBruto(objToBigDecimal(obj[5]));
                obatPerKonsumen.setMargin(objToBigDecimal(obj[6]));
                obatPerKonsumen.setHargaJual(objToBigDecimal(obj[7]));
                obatPerKonsumen.setHargaTerakhir(objToBigDecimal(obj[8]));
                perKonsumenList.add(obatPerKonsumen);
            }
        }
        return perKonsumenList;
    }
}
