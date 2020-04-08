package com.neurix.simrs.transaksi.paketperiksa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsPaketEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/03/20.
 */
public class PaketDao extends GenericDao<MtSimrsPaketEntity, String> {

    @Override
    protected Class<MtSimrsPaketEntity> getEntityClass() {
        return MtSimrsPaketEntity.class;
    }

    @Override
    public List<MtSimrsPaketEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsPaketEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_paket") != null) {
                criteria.add(Restrictions.eq("idPaket", mapCriteria.get("id_paket").toString()));
            }
            if (mapCriteria.get("nama_paket") != null) {
                criteria.add(Restrictions.ilike("namaPaket", "%"+mapCriteria.get("nama_paket").toString()+"%"));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_paket_priksa')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
