package com.neurix.akuntansi.transaksi.koreksi.dao;

import com.neurix.akuntansi.transaksi.koreksi.model.ItAkunKoreksiDetailEntity;
import com.neurix.akuntansi.transaksi.koreksi.model.KoreksiDetail;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aji Noor on 18/03/2021
 */
public class KoreksiDetailDao extends GenericDao<ItAkunKoreksiDetailEntity, String> {


    @Override
    protected Class<ItAkunKoreksiDetailEntity> getEntityClass() {
        return ItAkunKoreksiDetailEntity.class;
    }

    @Override
    public List<ItAkunKoreksiDetailEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItAkunKoreksiDetailEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("koreksi_id")!=null) {
                criteria.add(Restrictions.eq("koreksiId", (String) mapCriteria.get("koreksi_id")));
            }
            if (mapCriteria.get("kode_coa")!=null) {
                criteria.add(Restrictions.eq("kodeCoa", (String) mapCriteria.get("kode_coa")));
            }
            if (mapCriteria.get("kode_coa")!=null) {
                criteria.add(Restrictions.eq("kodeVendor", (String) mapCriteria.get("kode_coa")));
            }
            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }
            if (mapCriteria.get("no_nota")!=null) {
                criteria.add(Restrictions.eq("noNota", (String) mapCriteria.get("no_nota")));
            }
            if (mapCriteria.get("posisi")!=null) {
                criteria.add(Restrictions.eq("posisi", (String) mapCriteria.get("posisi")));
            }
            if (mapCriteria.get("koreksi_detail_id")!=null) {
                criteria.add(Restrictions.eq("koreksiDetailId", (String) mapCriteria.get("koreksi_detail_id")));
            }
        }
        // Order by
        criteria.addOrder(Order.desc("koreksiId"));
        List<ItAkunKoreksiDetailEntity> results = criteria.list();
        return results;
    }



    public List<ItAkunKoreksiDetailEntity> getByAkunKoreksiId(String id) throws HibernateException {
        List<ItAkunKoreksiDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunKoreksiDetailEntity.class)
                .add(Restrictions.eq("koreksiId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("koreksiDetailId"))
                .addOrder(Order.asc("posisi"))
                .list();
        return results;
    }

    public ItAkunKoreksiDetailEntity getByDetailId(String detailId){
        ItAkunKoreksiDetailEntity result = null;
        String query = "SELECT * FROM it_akun_koreksi_detail WHERE koreksi_detail_id = :detailId AND flag = 'Y'";

        List<Object[]> results = new ArrayList<Object[]>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("detailId", detailId)
                .list();

        for (Object[] row : results) {
            ItAkunKoreksiDetailEntity resultdata  = new ItAkunKoreksiDetailEntity();
        }

        return result;
    }

    public  void saveAdd(KoreksiDetail ko){

    }

}
