package com.neurix.simrs.master.askep.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.askep.model.DiagnosaAsuhanKeperawatan;
import com.neurix.simrs.master.askep.model.ImSimrsDiagnosaAsuhanKeperawatanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DiagnosaAsuhanKeperawatanDao extends GenericDao<ImSimrsDiagnosaAsuhanKeperawatanEntity, String> {

    @Override
    protected Class<ImSimrsDiagnosaAsuhanKeperawatanEntity> getEntityClass() {
        return ImSimrsDiagnosaAsuhanKeperawatanEntity.class;
    }

    @Override
    public List<ImSimrsDiagnosaAsuhanKeperawatanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDiagnosaAsuhanKeperawatanEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_diagnosa_askep")!=null) {
                criteria.add(Restrictions.eq("idDiagnosaAsuhanKeperawatan", (String) mapCriteria.get("id_diagnosa_askep")));
            }
            if (mapCriteria.get("diagnosa")!=null) {
                criteria.add(Restrictions.eq("diagnosa", (String) mapCriteria.get("diagnosa")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idDiagnosaAsuhanKeperawatan"));

        List<ImSimrsDiagnosaAsuhanKeperawatanEntity> results = criteria.list();
        return results;
    }

    public List<DiagnosaAsuhanKeperawatan> getListDiagnosa(String key, String tipe){
        List<DiagnosaAsuhanKeperawatan> list = new ArrayList<>();
        if(!"".equalsIgnoreCase(key) && key != null && !"".equalsIgnoreCase(tipe)){
            String id = "%"+key+"%";
            String SQL = "SELECT\n" +
                    "id_diagnosa_asuhan_keperawatan,\n" +
                    "diagnosa\n" +
                    "FROM im_simrs_diagnosa_asuhan_keperawatan\n" +
                    "WHERE diagnosa ILIKE :id AND tipe = :tipe";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .setParameter("tipe", tipe)
                    .list();
            if(result.size() >0){
                for (Object[] obj: result){
                    DiagnosaAsuhanKeperawatan diagnosaAsuhanKeperawatan = new DiagnosaAsuhanKeperawatan();
                    diagnosaAsuhanKeperawatan.setIdDiagnosaAsuhanKeperawatan(obj[0] == null ? "" : obj[0].toString());
                    diagnosaAsuhanKeperawatan.setDiagnosa(obj[1] == null ? "" : obj[1].toString());
                    list.add(diagnosaAsuhanKeperawatan);
                }
            }
        }
        return list;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_diagnosa_askep')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}