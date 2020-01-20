package com.neurix.simrs.transaksi.obatpoli.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsObatPoliEntity;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
        if (mapCriteria.get("id_obat") != null) {
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat")));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        if (mapCriteria.get("asc") != null){
            criteria.addOrder(Order.asc("createdDate"));
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
}
