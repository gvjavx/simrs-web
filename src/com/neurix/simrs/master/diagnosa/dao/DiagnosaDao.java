package com.neurix.simrs.master.diagnosa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.master.diagnosa.model.ImSimrsDiagnosaEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class DiagnosaDao extends GenericDao<ImSimrsDiagnosaEntity, String> {
    @Override
    protected Class<ImSimrsDiagnosaEntity> getEntityClass() {
        return ImSimrsDiagnosaEntity.class;
    }

    @Override
    public List<ImSimrsDiagnosaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDiagnosaEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_diagnosa") != null)
                criteria.add(Restrictions.eq("idDiagnosa", mapCriteria.get("id_diagnosa").toString()));

        List<ImSimrsDiagnosaEntity> result = criteria.list();
        return result;
    }

    public List<Diagnosa> getSearchDiagnosa(String key){
        List<Diagnosa> diagnosaList = new ArrayList<>();
        if(!"".equalsIgnoreCase(key) && key != null){
            String id = "%"+key+"%";
            String SQL = "SELECT \n" +
                    "id_diagnosa, \n" +
                    "desc_diagnosa \n" +
                    "FROM im_simrs_diagnosa\n" +
                    "WHERE id_diagnosa ILIKE :id OR desc_diagnosa ILIKE :id\n" +
                    "ORDER BY id_diagnosa ASC\n";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .list();

            if(result.size() > 0){
                for (Object[] obj : result){
                    Diagnosa diagnosa = new Diagnosa();
                    diagnosa.setIdDiagnosa(obj[0] == null ? "" : obj[0].toString());
                    diagnosa.setDescOfDiagnosa(obj[1] == null ? "" : obj[1].toString());
                    diagnosaList.add(diagnosa);
                }
            }
        }
        return diagnosaList;
    }
}
