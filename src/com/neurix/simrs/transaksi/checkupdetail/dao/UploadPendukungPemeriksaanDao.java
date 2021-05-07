package com.neurix.simrs.transaksi.checkupdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUploadPendukungPemeriksaanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UploadPendukungPemeriksaanDao extends GenericDao<ItSimrsUploadPendukungPemeriksaanEntity, String> {

    @Override
    protected Class<ItSimrsUploadPendukungPemeriksaanEntity> getEntityClass() {
        return ItSimrsUploadPendukungPemeriksaanEntity.class;
    }

    @Override
    public List<ItSimrsUploadPendukungPemeriksaanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsUploadPendukungPemeriksaanEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_upload") != null) {
                criteria.add(Restrictions.eq("idUpload", mapCriteria.get("id_upload").toString()));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
            }
            if (mapCriteria.get("url_img") != null) {
                criteria.add(Restrictions.eq("urlImg", mapCriteria.get("url_img").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }
        criteria.addOrder(Order.desc("createdDate"));
        List<ItSimrsUploadPendukungPemeriksaanEntity> results = criteria.list();
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_upload_pendukung_pemeriksaan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "UPP"+sId;
    }
}
