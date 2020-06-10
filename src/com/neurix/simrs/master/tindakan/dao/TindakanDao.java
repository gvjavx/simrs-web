package com.neurix.simrs.master.tindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
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
            if (mapCriteria.get("tindakan") != null){
                criteria.add(Restrictions.ilike("tindakan", "%" + (String)mapCriteria.get("tindakan") + "%"));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
//            if (mapCriteria.get("branch_id") != null) {
//                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
//            }
            if (mapCriteria.get("is_ina") != null) {
                criteria.add(Restrictions.eq("isIna", mapCriteria.get("is_ina").toString()));
            }
            if (mapCriteria.get("kategori_ina_bpjs") != null) {
                criteria.add(Restrictions.eq("kategoriInaBpjs", mapCriteria.get("kategori_ina_bpjs").toString()));
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
//            String idPelayanan = "%";

            if(bean.getIdKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakan())){
                idKategori = bean.getIdKategoriTindakan();
            }
//
//            if(bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
//                idPelayanan = bean.getIdPelayanan();
//            }

//            String SQL = "SELECT a.id_tindakan, a.id_kategori_tindakan, b.id_pelayanan, a.tindakan\n" +
//                    "FROM im_simrs_tindakan a\n" +
//                    "INNER JOIN im_simrs_tindakan_pelayanan b ON a.id_tindakan = b.id_tindakan\n" +
//                    "WHERE a.id_kategori_tindakan LIKE :idKat\n" +
//                    "AND b.id_pelayanan LIKE :idPel AND a.flag = 'Y' AND b.flag  = 'Y' \n";
            String SQL = "SELECT\n" +
                    "a.id_tindakan,\n" +
                    "a.id_kategori_tindakan,\n" +
                    "a.tindakan\n" +
                    "FROM im_simrs_tindakan a\n" +
                    "WHERE a.id_kategori_tindakan LIKE :idKat\n" +
                    "AND a.flag = 'Y'\n";

            List<Object[]> results =  new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idKat", idKategori)
                    .list();

            if(results != null){

                Tindakan tindakan;

                for (Object[] obj: results){

                    tindakan = new Tindakan();
                    tindakan.setIdTindakan(obj[0] == null ? "" : obj[0].toString());
                    tindakan.setIdKategoriTindakan(obj[1] == null ? "" : obj[1].toString());
                    tindakan.setTindakan(obj[2] == null ? "" : obj[2].toString());
                    tindakanList.add(tindakan);
                }
            }
        }
        return tindakanList;
    }

    public String getNextPelayananId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tindakan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "TDK" + sId;
    }

    public List<ImSimrsTindakanEntity> getDataTindakan(String namaTindakan) throws HibernateException {
        List<ImSimrsTindakanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanEntity.class)
                .add(Restrictions.eq("tindakan", namaTindakan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImSimrsTindakanEntity> cekData(String idTindakan) throws HibernateException{
        List<ImSimrsTindakanEntity> results = new ArrayList<>();

        String query = "SELECT a.id_tindakan, b.id_tindakan_rawat\n" +
                "FROM im_simrs_tindakan a\n" +
                "INNER JOIN it_simrs_tindakan_rawat b ON b.id_tindakan = a.id_tindakan\n" +
                "WHERE a.id_tindakan = '"+idTindakan+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

}
