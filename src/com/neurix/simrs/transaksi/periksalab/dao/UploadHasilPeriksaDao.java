package com.neurix.simrs.transaksi.periksalab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsUploadHasilPemeriksaanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UploadHasilPeriksaDao extends GenericDao<ItSimrsUploadHasilPemeriksaanEntity, String> {
    @Override
    protected Class<ItSimrsUploadHasilPemeriksaanEntity> getEntityClass() {
        return ItSimrsUploadHasilPemeriksaanEntity.class;
    }

    @Override
    public List<ItSimrsUploadHasilPemeriksaanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsUploadHasilPemeriksaanEntity.class);
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_upload_hasil_pemeriksaan")!= null) {
                criteria.add(Restrictions.eq("idUploadHasilPemeriksaan", (String) mapCriteria.get("id_upload_hasil_pemeriksaan")));
            }
            if (mapCriteria.get("id_periksa_lab")!= null) {
                criteria.add(Restrictions.eq("idPeriksaLab", (String) mapCriteria.get("id_periksa_lab")));
            }
            if (mapCriteria.get("tipe")!= null) {
                criteria.add(Restrictions.eq("tipe", (String) mapCriteria.get("tipe")));
            }
        }
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("idUploadHasilPemeriksaan"));
        List<ItSimrsUploadHasilPemeriksaanEntity> results = criteria.list();
        return results;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_upload_hasil_pemeriksaan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}