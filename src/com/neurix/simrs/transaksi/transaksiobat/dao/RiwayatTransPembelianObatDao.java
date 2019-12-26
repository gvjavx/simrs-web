package com.neurix.simrs.transaksi.transaksiobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsRiwayatPembelianObat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 14/12/2019.
 */
public class RiwayatTransPembelianObatDao extends GenericDao<MtSimrsRiwayatPembelianObat, String> {
    @Override
    protected Class<MtSimrsRiwayatPembelianObat> getEntityClass() {
        return MtSimrsRiwayatPembelianObat.class;
    }

    @Override
    public List<MtSimrsRiwayatPembelianObat> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRiwayatPembelianObat.class);
        if (mapCriteria.get("no_transaksi_obat") != null)
            criteria.add(Restrictions.eq("noPembelianObat", mapCriteria.get("no_transaksi_obat").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flaf", mapCriteria.get("flag").toString()));

        List<MtSimrsRiwayatPembelianObat> list = criteria.list();
        return list;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_transaksi_pembelian_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

}
