package com.neurix.akuntansi.transaksi.pengajuanSetor.dao;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItPengajuanSetorDetailEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
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
public class PengajuanSetorDetailDao extends GenericDao<ItPengajuanSetorDetailEntity, String> {

    @Override
    protected Class<ItPengajuanSetorDetailEntity> getEntityClass() {
        return ItPengajuanSetorDetailEntity.class;
    }

    @Override
    public List<ItPengajuanSetorDetailEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextPengajuanSetorDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengajuan_setor_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PSD"+sId;
    }
}
