package com.neurix.simrs.master.kategoritindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kategoritindakan.model.ImSimrsKategoriTindakanEntity;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class KategoriTindakanDao extends GenericDao<ImSimrsKategoriTindakanEntity, String> {
    @Override
    protected Class<ImSimrsKategoriTindakanEntity> getEntityClass() {
        return ImSimrsKategoriTindakanEntity.class;
    }

    @Override
    public List<ImSimrsKategoriTindakanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriTindakanEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_kategori_tindakan") != null) {
                criteria.add(Restrictions.eq("idKategoriTindakan", mapCriteria.get("id_kategori_tindakan").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }

        List<ImSimrsKategoriTindakanEntity> result = criteria.list();
        return result;
    }

    public List<KategoriTindakan> getListKategoriTindakan(String idPelayanan){

        List<KategoriTindakan> tindakanList = new ArrayList<>();
        String pelayanan = "%";

        if(idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)){
            pelayanan = idPelayanan;
        }

        String SQL = "SELECT a.id_kategori_tindakan, a.kategori_tindakan \n" +
                "FROM im_simrs_kategori_tindakan a\n" +
                "INNER JOIN im_simrs_kategori_tindakan_pelayanan b ON a.id_kategori_tindakan = b.id_kategori\n" +
                "WHERE b.id_pelayanan LIKE :idPel";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPel", pelayanan)
                .list();

        if(results.size() > 0){
            for (Object[] obj: results){
                KategoriTindakan tindakan = new KategoriTindakan();
                tindakan.setIdKategoriTindakan(obj[0] == null ? "" : obj[0].toString());
                tindakan.setKategoriTindakan(obj[1] == null ? "" : obj[1].toString());
                tindakanList.add(tindakan);
            }
        }

        return tindakanList;
    }
}
