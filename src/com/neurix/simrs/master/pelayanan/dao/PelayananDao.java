package com.neurix.simrs.master.pelayanan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class PelayananDao extends GenericDao<ImSimrsPelayananEntity, String> {

    @Override
    protected Class<ImSimrsPelayananEntity> getEntityClass() {
        return ImSimrsPelayananEntity.class;
    }

    @Override
    public List<ImSimrsPelayananEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPelayananEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_pelayanan") != null){
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }
            if (mapCriteria.get("not_poli") != null){
                criteria.add(Restrictions.ne("notPoli", mapCriteria.get("not_poli").toString()));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag",  mapCriteria.get("flag").toString()));
            }
            if(mapCriteria.get("tipe_pelayanan") != null){
                criteria.add(Restrictions.eq("tipePelayanan",  mapCriteria.get("tipe_pelayanan").toString()));
            }
            if(mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId",  mapCriteria.get("branch_id").toString()));
            }
        }

        List<ImSimrsPelayananEntity> result = criteria.list();
        return result;
    }

    public List<Pelayanan> getListApotek(String branch){

        String SQL = "SELECT \n" +
                "id_pelayanan, \n" +
                "nama_pelayanan \n" +
                "FROM im_simrs_pelayanan\n" +
                "WHERE tipe_pelayanan = 'apotek'\n" +
                "AND branch_id = :branchId\n" +
                "AND flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
                .list();

        List<Pelayanan> pelayananList = new ArrayList<>();

        if (results.size() > 0)
        {
            Pelayanan pelayanan;
            for (Object[] obj : results)
            {
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayananList.add(pelayanan);
            }
        }

        return pelayananList;
    }

    public List<Pelayanan> getListPelayananPaket(String branch){

        String SQL = "SELECT \n" +
                "id_pelayanan, \n" +
                "nama_pelayanan \n" +
                "FROM im_simrs_pelayanan\n" +
                "WHERE tipe_pelayanan IN ('rawat_jalan', 'igd')\n" +
                "AND branch_id = :branchId\n" +
                "AND flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
                .list();

        List<Pelayanan> pelayananList = new ArrayList<>();

        if (results.size() > 0)
        {
            Pelayanan pelayanan;
            for (Object[] obj : results) {
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayananList.add(pelayanan);
            }
        }

        return pelayananList;
    }

}
