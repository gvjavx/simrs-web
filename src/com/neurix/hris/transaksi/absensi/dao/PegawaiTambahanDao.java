package com.neurix.hris.transaksi.absensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetailEntity;
import com.neurix.hris.transaksi.absensi.model.PegawaiTambahanEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class PegawaiTambahanDao extends GenericDao<PegawaiTambahanEntity, String> {
    @Override
    protected Class<PegawaiTambahanEntity> getEntityClass() {
        return PegawaiTambahanEntity.class;
    }

    @Override
    public List<PegawaiTambahanEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    //digunakan mendapat pegawai tambahan pada database
    public List<PegawaiTambahanEntity> getPegawaiTambahanList() throws HibernateException {
        List<PegawaiTambahanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(PegawaiTambahanEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
    //digunakan mendapat pegawai tambahan pada database
    public List<PegawaiTambahanEntity> getPegawaiTambahanListBagian( String bagian ) throws HibernateException {
        List<PegawaiTambahanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(PegawaiTambahanEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("bagian", bagian))
                .list();
        return results;
    }
}
