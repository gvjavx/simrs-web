package com.neurix.simrs.transaksi.hargaobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 23/01/20.
 */
public class HargaObatDao extends GenericDao<MtSimrsHargaObatEntity, String> {

    @Override
    protected Class<MtSimrsHargaObatEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<MtSimrsHargaObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsHargaObatEntity.class);

        if (mapCriteria.get("id_harga_obat") != null){
            criteria.add(Restrictions.eq("idHargaObat", mapCriteria.get("id_harga_obat").toString()));
        }

        if (mapCriteria.get("id_obat") != null){
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
        }

        if (mapCriteria.get("branch_id") != null){
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }

        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<MtSimrsHargaObatEntity> results = criteria.list();
        return results;
    }


    public List<Obat> listObatForHargaJual(Obat bean){

        List<Obat> obats = new ArrayList<>();

        String idObat = "%";
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            idObat = bean.getIdObat();
        }

        String SQL = "SELECT \n" +
                "ob.id_obat,\n" +
                "ob.nama_obat,\n" +
                "ob.average_harga_box,\n" +
                "ob.average_harga_lembar,\n" +
                "ob.average_harga_biji,\n" +
                "ob.lembar_per_box,\n" +
                "ob.biji_per_lembar,\n" +
                "ob.merk,\n" +
                "ho.harga_jual,\n" +
                "ho.harga_net,\n" +
                "ho.diskon,\n" +
                "ob.id_barang,\n" +
                "mg.standar_margin,\n" +
                "ht.harga_terakhir,\n" +
                "ho.diskon_umum,\n" +
                "ho.harga_net_umum,\n" +
                "ho.harga_jual_umum\n" +
                "FROM im_simrs_obat ob\n" +
                "INNER JOIN (SELECT id_obat, MAX(id_barang) as id_barang FROM im_simrs_obat WHERE branch_id = :branch GROUP BY id_obat ) \n" +
                "obb ON obb.id_obat = ob.id_obat AND obb.id_barang = ob.id_barang\n" +
                "LEFT JOIN ( SELECT * FROM mt_simrs_harga_obat WHERE branch_id = :branch ) ho ON ho.id_obat = ob.id_obat \n" +
                "LEFT JOIN im_simrs_margin_obat mg ON mg.id_obat = ob.id_obat\n" +
                "LEFT JOIN mt_simrs_harga_terakhir ht ON ht.id_obat = ob.id_obat AND ht.branch_id = ob.branch_id\n" +
                "WHERE ob.id_obat LIKE :id \n" +
                "AND ob.branch_id = :branch";

        List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idObat)
                .setParameter("branch", bean.getBranchId())
                .list();

        if (resuts.size() > 0){

            Obat obat;
            for (Object[] obj : resuts){
                obat = new Obat();
                obat.setIdObat(obj[0].toString());
                obat.setNamaObat(obj[1].toString());
                obat.setAverageHargaBox((BigDecimal) obj[2]);
                obat.setAverageHargaLembar((BigDecimal) obj[3]);
                obat.setAverageHargaBiji((BigDecimal) obj[4]);
                obat.setLembarPerBox((BigInteger) obj[5]);
                obat.setBijiPerLembar((BigInteger) obj[6]);
                obat.setMerk(obj[7] == null ? "" : obj[7].toString());
                obat.setHargaJual(obj[8] == null ? new BigDecimal(0) : (BigDecimal) obj[8]);
                obat.setHargaNet(obj[9] == null ? new BigDecimal(0) : (BigDecimal) obj[9]);
                obat.setDiskon(obj[10] == null ? new BigDecimal(0) : (BigDecimal) obj[10]);
                obat.setIdBarang(obj[11].toString());
                obat.setStandarMargin(obj[12] == null ? null : (Integer) obj[12]);
                obat.setHargaBeli(obj[13] == null ? null : (BigDecimal) obj[13]);
                obat.setDiskonUmum(obj[14] == null ? new BigDecimal(0) : (BigDecimal) obj[14]);
                obat.setHargaNetUmum(obj[15] == null ? new BigDecimal(0) : (BigDecimal) obj[15]);
                obat.setHargaJualUmum(obj[16] == null ? new BigDecimal(0) : (BigDecimal) obj[16]);

                // Sigit 2020-12-08, hitung margin obat khusus, Start
                BigDecimal hargaRata    = obat.getAverageHargaBiji();
                BigDecimal hargaJual    = obat.getHargaJual();
                Integer intMargin       = new Integer(0);
                if (hargaJual != null){
                    BigDecimal selisih      = hargaJual.subtract(hargaRata);
                    BigDecimal margin       = hargaJual.intValue() != 0 ? selisih.divide(hargaRata, BigDecimal.ROUND_HALF_UP, 2).multiply(new BigDecimal(100)) : new BigDecimal(0);
                    intMargin               = margin.intValue();
                }
                obat.setMargin(intMargin);
                // END

                // Sigit 2020-12-08, hitung margin obat umum, Start
                BigDecimal hargaBeli        = obat.getHargaBeli();
                BigDecimal hargaJualUmum    = obat.getHargaJualUmum();
                Integer intMarginUmum       = new Integer(0);
                if (hargaJualUmum != null){
                    BigDecimal selisihUmum      = hargaJualUmum.subtract(hargaBeli);
                    BigDecimal marginUmum       = hargaJualUmum.intValue() != 0 ? selisihUmum.divide(hargaBeli, BigDecimal.ROUND_HALF_UP, 2).multiply(new BigDecimal(100)) : new BigDecimal(0);
                    intMarginUmum               = marginUmum.intValue();
                }
                obat.setMarginUmum(intMarginUmum);
                // END

                if (obat.getStandarMargin() != null){
                    if (Integer.compare(obat.getStandarMargin(), obat.getMargin()) == 1){
                        obat.setFlagKurangMargin("Y");
                    }
                    if (Integer.compare(obat.getStandarMargin(), obat.getMarginUmum()) == 1){
                        obat.setFlagKurangMargin("Y");
                    }
                } else {
                    obat.setFlagKurangMargin("R");
                }

                obats.add(obat);
            }
        }
        return obats;
    }
}
