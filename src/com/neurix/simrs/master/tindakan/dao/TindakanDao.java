package com.neurix.simrs.master.tindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class TindakanDao extends GenericDao<ImSimrsTindakanEntity, String> {
    @Override
    protected Class<ImSimrsTindakanEntity> getEntityClass() {
        return ImSimrsTindakanEntity.class;
    }

    @Override
    public List<ImSimrsTindakanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_tindakan") != null) {
                criteria.add(Restrictions.eq("idTindakan", mapCriteria.get("id_tindakan").toString()));
            }
            if (mapCriteria.get("id_kategori_tindakan") != null) {
                criteria.add(Restrictions.eq("idKategoriTindakan", mapCriteria.get("id_kategori_tindakan").toString()));
            }
            if (mapCriteria.get("id_kategori_tindakan") != null) {
                criteria.add(Restrictions.eq("idKategoriTindakan", mapCriteria.get("id_kategori_tindakan").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }
        List<ImSimrsTindakanEntity> result = criteria.list();
        return result;
    }

    public List<Tindakan> getListComboBoxTindakan(Tindakan bean){
        List<Tindakan> tindakanList = new ArrayList<>();
        if(bean != null){
            String idKategori  = "%";
            String idPelayanan = "%";

            if(bean.getIdKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakan())){
                idKategori = bean.getIdKategoriTindakan();
            }

            if(bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                idPelayanan = bean.getIdPelayanan();
            }

            String SQL = "SELECT a.id_tindakan, a.id_kategori_tindakan, b.id_pelayanan, a.tindakan\n" +
                    "FROM im_simrs_tindakan a\n" +
                    "INNER JOIN im_simrs_tindakan_pelayanan b ON a.id_tindakan = b.id_tindakan\n" +
                    "WHERE a.id_kategori_tindakan LIKE :idKat\n" +
                    "AND b.id_pelayanan LIKE :idPel AND a.flag = 'Y' AND b.flag  = 'Y' \n";

            List<Object[]> results =  new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idKat", idKategori)
                    .setParameter("idPel", idPelayanan)
                    .list();

            if(results != null){

                Tindakan tindakan;

                for (Object[] obj: results){

                    tindakan = new Tindakan();
                    tindakan.setIdTindakan(obj[0] == null ? "" : obj[0].toString());
                    tindakan.setIdKategoriTindakan(obj[1] == null ? "" : obj[1].toString());
                    tindakan.setIdPelayanan(obj[2] == null ? "" : obj[2].toString());
                    tindakan.setTindakan(obj[3] == null ? "" : obj[3].toString());
                    tindakanList.add(tindakan);
                }
            }
        }
        return tindakanList;
    }
}
