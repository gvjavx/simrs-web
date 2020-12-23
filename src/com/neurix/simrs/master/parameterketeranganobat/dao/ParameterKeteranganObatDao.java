package com.neurix.simrs.master.parameterketeranganobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;
import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParameterKeteranganObatDao extends GenericDao<ImSimrsParameterKeteranganObatEntity, String>{
    @Override
    protected Class<ImSimrsParameterKeteranganObatEntity> getEntityClass() {
        return ImSimrsParameterKeteranganObatEntity.class;
    }

    @Override
    public List<ImSimrsParameterKeteranganObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsParameterKeteranganObatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("nama") != null)
            criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));
        return criteria.list();
    }

    public List<ParameterKeteranganObat> getParameterKeterangan(String idJenis){
        List<ParameterKeteranganObat> keteranganObatList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "a.id,\n" +
                "a.nama\n" +
                "FROM im_simrs_paremeter_keterangan_obat a\n" +
                "INNER JOIN im_simrs_keterangan_obat b ON a.id = b.id_parameter_keterangan\n" +
                "WHERE b.id_sub_jenis = '"+idJenis+"'\n" +
                "GROUP BY a.id, a.nama";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                ParameterKeteranganObat param = new ParameterKeteranganObat();
                param.setId(obj[0] != null ? obj[0].toString() : null);
                param.setNama(obj[1] != null ? obj[1].toString() : null);
                keteranganObatList.add(param);
            }
        }
        return keteranganObatList;
    }
}
