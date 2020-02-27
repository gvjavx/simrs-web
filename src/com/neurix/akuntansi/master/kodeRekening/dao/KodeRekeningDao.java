package com.neurix.akuntansi.master.kodeRekening.dao;

import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.common.dao.GenericDao;
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
public class KodeRekeningDao extends GenericDao<ImKodeRekeningEntity, String> {

    @Override
    protected Class<ImKodeRekeningEntity> getEntityClass() {
        return ImKodeRekeningEntity.class;
    }

    @Override
    public List<ImKodeRekeningEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImKodeRekeningEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("rekening_id")!=null) {
                criteria.add(Restrictions.eq("rekeningId", (String) mapCriteria.get("rekening_id")));
            }
            if (mapCriteria.get("nama_kode_rekening")!=null) {
                criteria.add(Restrictions.ilike("namaKodeRekening", "%" + (String)mapCriteria.get("nama_kode_rekening") + "%"));
            }
            if (mapCriteria.get("kode_rekening")!=null) {
                criteria.add(Restrictions.eq("kodeRekening", (String) mapCriteria.get("kode_rekening")));
            }
            if (mapCriteria.get("tipe_rekening_id")!=null) {
                criteria.add(Restrictions.eq("tipeRekeningId", (String) mapCriteria.get("tipe_rekening_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("rekeningId"));

        List<ImKodeRekeningEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextKodeRekeningId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kode_rekening')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%010d", iter.next());
        return "KR"+sId;
    }

    public List<ImKodeRekeningEntity> getIdByCoa(String coa) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImKodeRekeningEntity.class);
        criteria.add(Restrictions.eq("kodeRekening", coa));
        criteria.add(Restrictions.eq("flag", "Y"));
        // Order by
        criteria.addOrder(Order.asc("rekeningId"));

        List<ImKodeRekeningEntity> results = criteria.list();
        return results;
    }

    //for typeahead
    public List<ImKodeRekeningEntity> getKodeRekeningListByLikeCoa(String coa) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImKodeRekeningEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("kodeRekening", coa + "%"),
                        Restrictions.ilike("namaKodeRekening", "%"+coa+"%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("rekeningId"));

        List<ImKodeRekeningEntity> results = criteria.list();
        return results;
    }

    //for search bank in billing
    public String searchRekeningIdBankLikeName(String namaBank){
        String result="";
        String query = "select  \n" +
                "                  rekening_id  \n" +
                "                from  \n" +
                "                im_akun_kode_rekening  \n" +
                "                where  \n" +
                "                nama_kode_rekening ilike '"+namaBank+"%'  \n" +
                "                and kode_rekening ilike '1.1.01%' \n" +
                "                and length(kode_rekening)=12 \n" +
                "                order by rekening_id \n" +
                "                limit 1";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result=null;
        }
        return result;
    }
    //for search bank in billing
    public String searchRekeningIdTunaiLikeName(String namaTunai){
        String result="";
        String query = "select  \n" +
                "                  rekening_id  \n" +
                "                from  \n" +
                "                im_akun_kode_rekening  \n" +
                "                where  \n" +
                "                nama_kode_rekening like 'Kas Tunai'  \n" +
                "                and kode_rekening ilike '1.1.01%' \n" +
                "                and length(kode_rekening)=12 \n" +
                "                order by rekening_id \n" +
                "                limit 1";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result=null;
        }
        return result;
    }
}
