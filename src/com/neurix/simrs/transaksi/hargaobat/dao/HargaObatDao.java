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
        if (mapCriteria.get("id_obat") != null){
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
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
                "ob.id_barang\n" +
                "FROM im_simrs_obat ob\n" +
                "INNER JOIN (SELECT id_obat, MAX(id_barang) as id_barang FROM im_simrs_obat GROUP BY id_obat ) obb ON obb.id_obat = ob.id_obat AND obb.id_barang = ob.id_barang\n" +
                "LEFT JOIN mt_simrs_harga_obat ho ON ho.id_obat = ob.id_obat\n" +
                "WHERE ob.id_obat LIKE :id \n" +
                "AND ob.branch_id = :branch ";

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
                obat.setMerk(obj[7].toString());
                obat.setHargaJual(obj[8] == null ? new BigDecimal(0) : (BigDecimal) obj[8]);
                obat.setHargaNet(obj[9] == null ? new BigDecimal(0) : (BigDecimal) obj[9]);
                obat.setHargaJual(obj[10] == null ? new BigDecimal(0) : (BigDecimal) obj[10]);
                obat.setIdBarang(obj[11].toString());
                obats.add(obat);

            }
        }
        return obats;
    }
}
