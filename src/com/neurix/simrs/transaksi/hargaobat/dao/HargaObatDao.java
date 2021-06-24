package com.neurix.simrs.transaksi.hargaobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
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
                    "ht.harga_rata_umum as harga_terakhir_khusus_non_bpjs,\n" +
                    "ht.harga_terakhir_bpjs as harga_terakhir_umum_bpjs,\n" +
                    "ht.harga_rata_bpjs as harga_terakhir_khusus_bpjs\n" +
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
                    "LEFT JOIN (SELECT * FROM mt_simrs_harga_terakhir WHERE branch_id = :branch) ht ON ht.id_obat = ob.id_obat  \n" +
                    "WHERE obd.branch_id = :branch \n" + condition;

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
                    obat.setHargaTerakhirKhususNonBpjs(objToBigDecimal(obj[4]));
                    obat.setHargaTerakhirUmumBpjs(objToBigDecimal(obj[5]));
                    obat.setHargaTerakhirKhususBpjs(objToBigDecimal(obj[6]));
                    obats.add(obat);
                }
            }
        }
        return obats;
    }

    private BigDecimal objToBigDecimal(Object obj){

        if (obj == null)
            return new BigDecimal(0);
        else
            return new BigDecimal(obj.toString());

    }

    public String getIdHargaObatByIdObatAndBranch(String idObat, String branchId){

        String SQL = "SELECT id FROM mt_simrs_harga_terakhir \n" +
                "WHERE id_obat = '"+idObat+"' \n" +
                "AND branch_id = '"+branchId+"'";

        List<Object> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0)
            return list.get(0).toString();
        else
            return null;

    }



}
