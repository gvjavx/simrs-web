
package com.neurix.hris.master.smkIndikatorPenilaianCheckList.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.ImSmkIndikatorPenilaianCheckListEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkIndikatorPenilaianCheckListDao extends GenericDao<ImSmkIndikatorPenilaianCheckListEntity, String> {

    @Override
    protected Class<ImSmkIndikatorPenilaianCheckListEntity> getEntityClass() {
        return ImSmkIndikatorPenilaianCheckListEntity.class;
    }

    @Override
    public List<ImSmkIndikatorPenilaianCheckListEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkIndikatorPenilaianCheckListEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("indikatorPenilaianCheckListId")!=null) {
                criteria.add(Restrictions.eq("indikatorPenilaianCheckListId", (String) mapCriteria.get("indikatorPenilaianCheckListId")));
            }
            if (mapCriteria.get("checkListId")!=null) {
                criteria.add(Restrictions.eq("checkListId", (String) mapCriteria.get("checkListId")));
            }
            if (mapCriteria.get("indikatorName")!=null) {
                criteria.add(Restrictions.ilike("indikatorName", "%" + (String)mapCriteria.get("indikatorName") + "%"));
            }
            if (mapCriteria.get("nilai")!=null) {
                criteria.add(Restrictions.ilike("nilai", "%" + (String)mapCriteria.get("nilai") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("indikatorPenilaianCheckListId"));

        List<ImSmkIndikatorPenilaianCheckListEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSmkIndikatorPenilaianCheckListId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_indikator_penilaian_check_list')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "IPCL"+sId;
    }

    public List<ImSmkIndikatorPenilaianCheckListEntity> getListSmkIndikatorPenilaianCheckList(String term) throws HibernateException {

        List<ImSmkIndikatorPenilaianCheckListEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSmkIndikatorPenilaianCheckListEntity.class)
                .add(Restrictions.ilike("checkListName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("smkIndikatorPenilaianCheckListId"))
                .list();

        return results;
    }
    public String deleteIndikator(String kode) throws HibernateException {
        String query = "update im_hris_smk_indikator_penilaian_check_list set flag = 'N' where check_list_id = '"+kode+"'";
        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).executeUpdate();
        return null;
    }
}
