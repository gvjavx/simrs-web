package com.neurix.hris.master.reward.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.reward.model.ImRewardEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
public class RewardDao extends GenericDao<ImRewardEntity, String> {

    @Override
    protected Class<ImRewardEntity> getEntityClass() {
        return ImRewardEntity.class;
    }

    @Override
    public List<ImRewardEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    public String getNextReward() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_reward')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "RW"+sId;
    }

    public List<ImRewardEntity> getAllData(String nip) throws HibernateException {
        List<ImRewardEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImRewardEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}