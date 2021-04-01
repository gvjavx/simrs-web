package com.neurix.simrs.transaksi.hargaobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if(bean != null){
            String condition = "";
            if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
                condition = "AND ob.id_obat LIKE '%"+bean.getIdObat()+"%' \n";
            }
            if (bean.getNamaObat() != null && !"".equalsIgnoreCase(bean.getNamaObat())){
                condition = "AND ob.nama_obat ILIKE '%"+bean.getNamaObat()+"%'\n";
            }

            String SQL = "SELECT \n" +
                    "ob.id_obat,\n" +
                    "ob.nama_obat,\n" +
                    "mg.standar_margin,\n" +
                    "ht.harga_terakhir as harga_terakhir_umum_non_bpjs,\n" +
                    "ho.harga_jual_umum as harga_jual_umum_non_bpjs,\n" +
                    "ht.harga_rata_umum as harga_terakhir_khusus_non_bpjs,\n" +
                    "ho.harga_jual as harga_jual_khusus_non_bpjs,\n" +
                    "ht.harga_terakhir_bpjs as harga_terakhir_umum_bpjs,\n" +
                    "ho.harga_jual_umum_bpjs as harga_jual_umum_bpjs,\n" +
                    "ht.harga_rata_bpjs as harga_terakhir_khusus_bpjs,\n" +
                    "ho.harga_jual_khusus_bpjs as harga_jual_khusus_bpjs\n" +
                    "FROM im_simrs_header_obat ob\n" +
                    "INNER JOIN \n" +
                    "(\n" +
                    "\tSELECT \n" +
                    "\tid_obat,\n" +
                    "\tbranch_id\n" +
                    "\tFROM im_simrs_obat \n" +
                    "\tWHERE flag = 'Y' \n" +
                    "\tGROUP BY id_obat, branch_id\n" +
                    ") obd ON obd.id_obat = ob.id_obat\n" +
                    "LEFT JOIN im_simrs_margin_obat mg ON mg.id_obat = ob.id_obat\n" +
                    "LEFT JOIN mt_simrs_harga_terakhir ht ON ht.id_obat = ob.id_obat  \n" +
                    "LEFT JOIN mt_simrs_harga_obat ho ON ho.id_obat = ob.id_obat \n" +
                    "WHERE obd.branch_id = :branch " + condition;

            List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branch", bean.getBranchId())
                    .list();

            if (resuts.size() > 0){

                Obat obat;
                for (Object[] obj : resuts){
                    obat = new Obat();
                    obat.setIdObat(obj[0].toString());
                    obat.setNamaObat(obj[1].toString());
                    obat.setStandarMargin(obj[2] == null ? (Integer) 0 : (Integer) obj[2]);
                    obat.setHargaTerakhirUmumNonBpjs(objToBigDecimal(obj[3]));
                    obat.setHargaJualUmumNonBpjs(objToBigDecimal(obj[4]));
                    obat.setHargaTerakhirKhususNonBpjs(objToBigDecimal(obj[5]));
                    obat.setHargaJualKhususNonBpjs(objToBigDecimal(obj[6]));
                    obat.setHargaTerakhirUmumBpjs(objToBigDecimal(obj[7]));
                    obat.setHargaJualUmumBpjs(objToBigDecimal(obj[8]));
                    obat.setHargaTerakhirKhususBpjs(objToBigDecimal(obj[9]));
                    obat.setHargaJualKhususBpjs(objToBigDecimal(obj[10]));

                    // Sigit 2020-12-08, hitung margin obat khusus, Start
                    BigDecimal hargaRata        = obat.getHargaTerakhirKhususNonBpjs();
                    BigDecimal hargaJual        = obat.getHargaJualKhususNonBpjs();
                    Integer intMargin           = new Integer(0);
                    if (hargaJual != null){
                        BigDecimal selisih      = hargaJual.subtract(hargaRata);
                        BigDecimal margin       = new BigDecimal(0);
                        //sodiq, cek harga rata diatas 0, 04,02,2021 12.21
                        if(hargaJual.intValue() > 0 && hargaRata.intValue() > 0){
                            margin = selisih.divide(hargaRata, BigDecimal.ROUND_HALF_UP, 2).multiply(new BigDecimal(100));
                        }
                        intMargin               = margin.intValue();
                    }
                    obat.setMarginKhususNonBpjs(intMargin);
                    // END

                    // Sigit 2020-12-08, hitung margin obat umum, Start
                    BigDecimal hargaBeli            = obat.getHargaTerakhirUmumNonBpjs();
                    BigDecimal hargaJualUmum        = obat.getHargaJualUmumNonBpjs();
                    Integer intMarginUmum           = new Integer(0);
                    if (hargaJualUmum != null){
                        BigDecimal selisihUmum      = hargaJualUmum.subtract(hargaBeli);
                        BigDecimal marginUmum       = new BigDecimal(0);
                        //sodiq, cek harga rata diatas 0, 04,02,2021 12.21
                        if(hargaJualUmum.intValue() > 0 && hargaBeli.intValue() > 0){
                            marginUmum = selisihUmum.divide(hargaBeli, BigDecimal.ROUND_HALF_UP, 2).multiply(new BigDecimal(100));
                        }
                        intMarginUmum               = marginUmum.intValue();
                    }

                    obat.setMarginUmumNonBpjs(intMarginUmum);
                    // END

                    // Sigit 2020-12-08, hitung margin obat umum BPJS, Start
                    BigDecimal hargaBeliUmumBpjs        = obat.getHargaTerakhirUmumBpjs();
                    BigDecimal hargaJualUmumBpjs        = obat.getHargaJualUmumBpjs();
                    Integer intMarginUmumBpjs           = new Integer(0);
                    if (hargaJualUmum != null){
                        BigDecimal selisihUmum          = hargaJualUmumBpjs.subtract(hargaBeliUmumBpjs);
                        BigDecimal marginUmum           = new BigDecimal(0);

                        //sodiq, cek harga rata diatas 0, 04,02,2021 12.21
                        if(hargaJualUmumBpjs.intValue() > 0 && hargaBeliUmumBpjs.intValue() > 0){
                            marginUmum = selisihUmum.divide(hargaBeliUmumBpjs, BigDecimal.ROUND_HALF_UP, 2).multiply(new BigDecimal(100));
                        }
                        intMarginUmumBpjs               = marginUmum.intValue();
                    }

                    obat.setMarginUmumBpjs(intMarginUmumBpjs);
                    // END

                    // Sigit 2020-12-08, hitung margin obat khusu BPJS, Start
                    BigDecimal hargaBeliKhususBpjs        = obat.getHargaTerakhirKhususBpjs();
                    BigDecimal hargaJualKhususBpjs        = obat.getHargaJualKhususBpjs();
                    Integer intMarginKhususBpjs           = new Integer(0);
                    if (hargaJualUmum != null){
                        BigDecimal selisihKhusus          = hargaJualKhususBpjs.subtract(hargaBeliKhususBpjs);
                        BigDecimal marginKhusus           = new BigDecimal(0);

                        //sodiq, cek harga rata diatas 0, 04,02,2021 12.21
                        if(hargaJualKhususBpjs.intValue() > 0 && hargaBeliKhususBpjs.intValue() > 0){
                            marginKhusus = selisihKhusus.divide(hargaBeliKhususBpjs, BigDecimal.ROUND_HALF_UP, 2).multiply(new BigDecimal(100));
                        }

                        intMarginKhususBpjs               = marginKhusus.intValue();
                    }

                    obat.setMarginKhususBpjs(intMarginKhususBpjs);
                    // END

                    if (obat.getStandarMargin() != null){
                        if (Integer.compare(obat.getStandarMargin(), obat.getMarginKhususNonBpjs()) == 1){
                            obat.setFlagKurangMargin("Y");
                        }
                        if (Integer.compare(obat.getStandarMargin(), obat.getMarginUmumNonBpjs()) == 1){
                            obat.setFlagKurangMargin("Y");
                        }
                        if (Integer.compare(obat.getStandarMargin(), obat.getMarginKhususBpjs()) == 1){
                            obat.setFlagKurangMargin("Y");
                        }
                        if (Integer.compare(obat.getStandarMargin(), obat.getMarginUmumBpjs()) == 1){
                            obat.setFlagKurangMargin("Y");
                        }
                    } else {
                        obat.setFlagKurangMargin("R");
                    }
                    obats.add(obat);
                }
            }
        }

        // ORDER BY flagKurangMargin;
        //List<Obat> sortedList = obats.stream().sorted(
          //      Comparator.comparing(Obat::getFlagKurangMargin).reversed()
        //).collect(Collectors.toList());

        //return sortedList;
        return obats;
    }

    private BigDecimal objToBigDecimal(Object obj){

        if (obj == null)
            return new BigDecimal(0);
        else
            return new BigDecimal(obj.toString());

    }

}
