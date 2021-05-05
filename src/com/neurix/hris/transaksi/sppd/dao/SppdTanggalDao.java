
package com.neurix.hris.transaksi.sppd.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.sppd.model.ItSppdTanggalEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
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
public class SppdTanggalDao extends GenericDao<ItSppdTanggalEntity, String> {

    @Override
    protected Class<ItSppdTanggalEntity> getEntityClass() {
        return ItSppdTanggalEntity.class;
    }

    @Override
    public List<ItSppdTanggalEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSppdTanggalEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("sppd_id")!=null) {
                criteria.add(Restrictions.eq("sppdId", (String) mapCriteria.get("sppd_id")));
            }
            if (mapCriteria.get("person_id")!=null) {
                criteria.add(Restrictions.eq("personId", (String)mapCriteria.get("person_id")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String)mapCriteria.get("flag")));
            }

        }

        //criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("sppdId"));

        List<ItSppdTanggalEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSppdTanggalId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_sppd_tanggal')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "TGL"+sId;
    }


    public List<ItSppdTanggalEntity> getHargaTiketBerangkat(String sppdPersonId) throws HibernateException {
        List<ItSppdTanggalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdTanggalEntity.class)
                .add(Restrictions.eq("sppdPersonId", sppdPersonId))
                .add(Restrictions.eq("sppdTanggalApprove", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("sppdTanggal"))
                .list();
        return results;
    }

    public void updateTanggal(String sppdPersonId, Date tanggal){
        String qry = "update\n" +
                "\tit_hris_sppd_tanggal\n" +
                "set\n" +
                "\ttransport_lokal = 0,\n" +
                "\tharga_tiket_berangkat = 0,\n" +
                "\tharga_tiket_kembali = 0,\n" +
                "\ttransport_tujuan = 0, \n" +
                "\tbiaya_transport_lainnya = 0,\n" +
                "\ttransport_kembali = 0,\n" +
                "\tbiaya_makan = 0\n" +
                "where\n" +
                "\tsppd_person_id = '"+sppdPersonId+"'\n" +
                "\tand sppd_tanggal = '"+tanggal+"'";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(qry)
                .executeUpdate();
    }

    public void updateTanggal(String sppdPersonId, Date tanggal, BigDecimal biayaMakan){
        String qry = "update\n" +
                "\tit_hris_sppd_tanggal\n" +
                "set\n" +
                "\ttransport_lokal = 0,\n" +
                "\tharga_tiket_berangkat = 0,\n" +
                "\tharga_tiket_kembali = 0,\n" +
                "\ttransport_tujuan = 0, \n" +
                "\tbiaya_transport_lainnya = 0,\n" +
                "\ttransport_kembali = 0,\n" +
                "\tbiaya_makan = "+biayaMakan+"\n" +
                "where\n" +
                "\tsppd_person_id = '"+sppdPersonId+"'\n" +
                "\tand sppd_tanggal = '"+tanggal+"'";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(qry)
                .executeUpdate();
    }

    public List<ItSppdTanggalEntity> getDataSppdTanggal(String sppdPersonId){
        List<ItSppdTanggalEntity> listOfResult = new ArrayList<ItSppdTanggalEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\t*\n" +
                "from\n" +
                "\tit_hris_sppd_tanggal\n" +
                "where\n" +
                "\tsppd_person_id = '"+sppdPersonId+"'\n" +
                "\tand flag = 'Y'\n" +
                "\torder by sppd_tanggal "  ;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItSppdTanggalEntity result  = new ItSppdTanggalEntity();
            result.setIdSppdTanggal((String) row[0]);
            result.setSppdPersonId((String) row[1]);
            result.setSppdTanggal((Date) row[2]);
            result.setSppdTanggalApprove((String) row[3]);
            result.setFlag((String) row[4]);
            result.setCreatedDate((Timestamp) row[5]);
            result.setCreatedWho((String) row[6]);
            result.setLastUpdate((Timestamp) row[7]);
            result.setLastUpdateWho((String) row[8]);
            result.setAction((String) row[9]);
            result.setBiayaLain(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setBiayaMakan(BigDecimal.valueOf(Double.valueOf(row[17].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSppdTanggalEntity> getDataSppdTanggal(String sppdId, String personId, String tanggal){
        List<ItSppdTanggalEntity> listOfResult = new ArrayList<ItSppdTanggalEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\ttanggal.*\n" +
                "from\n" +
                "\tit_hris_sppd_person person\n" +
                "left join\n" +
                "\tit_hris_sppd_tanggal tanggal on tanggal.sppd_person_id = person.sppd_person_id\n" +
                "where\n" +
                "\tperson.sppd_id = '"+sppdId+"'\n" +
                "\tand person.person_id = '"+personId+"'\n" +
                "\tand person.approval_flag = 'Y'\n" +
                "\tand tanggal.id_sppd_tanggal is not null\n" +
                "\tand tanggal.sppd_tanggal = '"+tanggal+"'\n" +
                "\tand tanggal.flag = 'Y'"  ;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItSppdTanggalEntity result  = new ItSppdTanggalEntity();
            result.setIdSppdTanggal((String) row[0]);
            result.setSppdPersonId((String) row[1]);
            result.setSppdTanggal((Date) row[2]);
            result.setSppdTanggalApprove((String) row[3]);
            result.setFlag((String) row[4]);
            result.setCreatedDate((Timestamp) row[5]);
            result.setCreatedWho((String) row[6]);
            result.setLastUpdate((Timestamp) row[7]);
            result.setLastUpdateWho((String) row[8]);
            result.setAction((String) row[9]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSppdTanggalEntity> getDataSppdTanggal2(String sppdId, String personId, String tanggal){
        List<ItSppdTanggalEntity> listOfResult = new ArrayList<ItSppdTanggalEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\ttanggal.*\n" +
                "from\n" +
                "\tit_hris_sppd_person person\n" +
                "left join\n" +
                "\tit_hris_sppd_tanggal tanggal on tanggal.sppd_person_id = person.sppd_person_id\n" +
                "where\n" +
                "\tperson.sppd_id = '"+sppdId+"'\n" +
                "\tand person.person_id = '"+personId+"'\n" +
                "\tand person.approval_flag = 'Y'\n" +
                "\tand tanggal.id_sppd_tanggal is not null\n" +
                "\tand tanggal.sppd_tanggal = '"+tanggal+"'\n" +
                "\tand tanggal.flag = 'Y'"  ;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItSppdTanggalEntity result  = new ItSppdTanggalEntity();
            result.setIdSppdTanggal((String) row[0]);
            result.setSppdPersonId((String) row[1]);
            result.setSppdTanggal((Date) row[2]);
            result.setSppdTanggalApprove((String) row[3]);
            result.setFlag((String) row[4]);
            result.setCreatedDate((Timestamp) row[5]);
            result.setCreatedWho((String) row[6]);
            result.setLastUpdate((Timestamp) row[7]);
            result.setLastUpdateWho((String) row[8]);
            result.setAction((String) row[9]);
            result.setHargaTiketBerangkat(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setHargaTiketKembali(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setTransportLokal(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setTransportTujuan(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setBiayaTransportLainnya(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setTransportKembali(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setBiayaLain(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setBiayaMakan(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSppdTanggalEntity> getDataTanggal(String nip) throws HibernateException {
        List<ItSppdTanggalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdTanggalEntity.class)
                .add(Restrictions.eq("sppdPersonId", nip))
                .add(Restrictions.eq("flag", "Y"))

                .list();
        return results;
    }

    public List<ItSppdTanggalEntity> getListDataTanggal(String sppdPersonId) throws HibernateException {
        List<ItSppdTanggalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdTanggalEntity.class)
                .add(Restrictions.eq("sppdPersonId", sppdPersonId))
                .add(Restrictions.eq("sppdTanggalApprove", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("sppdTanggal"))
                .list();
        return results;
    }

    public List<ItSppdTanggalEntity> getListDataTanggal(String sppdPersonId, Date tanggal) throws HibernateException {
        List<ItSppdTanggalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdTanggalEntity.class)
                .add(Restrictions.eq("sppdPersonId", sppdPersonId))
                .add(Restrictions.eq("sppdTanggalApprove", "Y"))
                .add(Restrictions.ge("sppdTanggal", tanggal))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

}
