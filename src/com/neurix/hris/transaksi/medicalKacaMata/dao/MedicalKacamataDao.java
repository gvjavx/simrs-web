package com.neurix.hris.transaksi.medicalKacamata.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.medicalKacamata.model.ItMedicalKacamataEntity;
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
public class MedicalKacamataDao extends GenericDao<ItMedicalKacamataEntity, String> {

    @Override
    protected Class<ItMedicalKacamataEntity> getEntityClass() {
        return ItMedicalKacamataEntity.class;
    }

    @Override
    public List<ItMedicalKacamataEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItMedicalKacamataEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("kacamata_id")!=null) {
                criteria.add(Restrictions.eq("medicalKacamataId", (String) mapCriteria.get("kacamata_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("bidang_id")!=null) {
                criteria.add(Restrictions.eq("bidangId", (String) mapCriteria.get("bidang_id")));
            }

            if (mapCriteria.get("bagian_id")!=null) {
                criteria.add(Restrictions.eq("bagianId", (String) mapCriteria.get("bagian_id")));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }

        }

        // Order by
        criteria.addOrder(Order.asc("medicalKacamataId"));

        List<ItMedicalKacamataEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextKacamata() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_medical_record_kacamata')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "MK"+sId;
    }

    // Generate surrogate id from postgre
    public String getNextGolonganHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_golongan_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ItMedicalKacamataEntity> getListMedical(String nip) throws HibernateException {

        String[] tipe = { "Set", "Gagang" };
        List<ItMedicalKacamataEntity> results = sessionFactory.getCurrentSession().createCriteria(ItMedicalKacamataEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.in("tipePembayaran", tipe))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("tanggalPembayaran"))
                .list();

        return results;
    }

    public List<ItMedicalKacamataEntity> getListMedical(String id, String nip) throws HibernateException {

        String[] tipe = { "Set", "Gagang" };
        List<ItMedicalKacamataEntity> results = sessionFactory.getCurrentSession().createCriteria(ItMedicalKacamataEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.in("tipePembayaran", tipe))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.ne("medicalKacamataId", id))
                .addOrder(Order.asc("tanggalPembayaran"))
                .list();

        return results;
    }

    public List<ItMedicalKacamataEntity> getListGolonganById(String id) throws HibernateException {

        List<ItMedicalKacamataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItMedicalKacamataEntity.class)
                .add(Restrictions.eq("updateGolonganId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganId"))
                .list();

        return results;
    }

}
