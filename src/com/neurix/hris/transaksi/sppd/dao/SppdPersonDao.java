
package com.neurix.hris.transaksi.sppd.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.sppd.model.ItSppdPersonEntity;
import com.neurix.hris.transaksi.sppd.model.ItSppdPersonEntity;
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
public class SppdPersonDao extends GenericDao<ItSppdPersonEntity, String> {

    @Override
    protected Class<ItSppdPersonEntity> getEntityClass() {
        return ItSppdPersonEntity.class;
    }

    @Override
    public List<ItSppdPersonEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("sppd_id")!=null) {
                criteria.add(Restrictions.eq("sppdId", (String) mapCriteria.get("sppd_id")));
            }
            if (mapCriteria.get("person_id")!=null) {
                criteria.add(Restrictions.eq("personId", (String)mapCriteria.get("person_id")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", "Y"));
            }

        }

        //criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("createdDate"));

        List<ItSppdPersonEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSppdPersonId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_sppd_person')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "P"+sId;
    }


    public List<ItSppdPersonEntity> getListSppdPerson() throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getListSppdPerson(String sppdId) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("sppdId", sppdId))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getListSppdPerson(String sppdId, String tipePerson) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("sppdId", sppdId))
                .add(Restrictions.eq("tipePerson", tipePerson))
                .list();
        return results;
    }

    /*public List<ItSppdPersonEntity> getListSppdPerson(String nip, Date tanggal1, Date tanggal2) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("personId",nip))
                .add(Restrictions.eq("flag","Y"))
                .add(Restrictions.ge("tanggalBerangkat",tanggal1))
                .add(Restrictions.le("tanggalBerangkat",tanggal2))

                .addOrder(Order.asc("personId"))
                .list();
        return results;
    }*/

    // cek apakah SPPD pada tanggal tersebut sudah ada
    public List<ItSppdPersonEntity> getListSppdPerson(String nip, Date tanggal1, Date tanggal2) throws HibernateException {
        List<ItSppdPersonEntity> listOfResult = new ArrayList<ItSppdPersonEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tperson.person_name,\n" +
                "\ttanggal.sppd_tanggal\n" +
                "from\n" +
                "\tit_hris_sppd_person person\n" +
                "\tleft join it_hris_sppd_tanggal tanggal on tanggal.sppd_person_id = person.sppd_person_id\n" +
                "where\n" +
                "\tperson_id = '"+nip+"'\n" +
                "\tand tanggal.sppd_tanggal between '"+tanggal1+"' and '"+tanggal2+"'\n" +
                "\tand tanggal.flag = 'Y'\n" +
                "\tand person.flag ='Y'\n" +
                "\tand tanggal.sppd_tanggal_approve = 'Y'\n" +
                "order by\n" +
                "\ttanggal.sppd_tanggal";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItSppdPersonEntity result  = new ItSppdPersonEntity();
            result.setPersonName((String) row[0]);
            result.setTanggalBerangkat((Date) row[1]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    // cek apakah SPPD pada tanggal tersebut sudah ada (untuk versi edit tanggal SPPD)
    public List<ItSppdPersonEntity> getListSppdPerson(String nip, Date tanggal1, Date tanggal2, String sppdId) throws HibernateException {
        List<ItSppdPersonEntity> listOfResult = new ArrayList<ItSppdPersonEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tperson.person_name,\n" +
                "\ttanggal.sppd_tanggal\n" +
                "from\n" +
                "        it_hris_sppd_person person\n" +
                "    left join\n" +
                "        it_hris_sppd_tanggal tanggal\n" +
                "            on tanggal.sppd_person_id = person.sppd_person_id\n" +
                "    where\n" +
                "        person_id = '"+nip+"'\n" +
                "        and tanggal.sppd_tanggal between '"+tanggal1+"' and '"+tanggal2+"'\n" +
                "        and tanggal.sppd_tanggal_approve = 'Y'\n" +
                "        and tanggal.flag = 'Y'\n" +
                "        and person.flag ='Y'\n" +
                "        and person.sppd_id != '"+sppdId+"'\n" +
                "    order by\n" +
                "        tanggal.sppd_tanggal";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItSppdPersonEntity result  = new ItSppdPersonEntity();
            result.setPersonName((String) row[0]);
            result.setTanggalBerangkat((Date) row[1]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSppdPersonEntity> getListPerson(String sppdId) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("sppdId", sppdId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getListPerson(String sppdId, String approveAtasan) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("sppdId", sppdId))
                .add(Restrictions.isNull("approvalId"))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getListPersonApproved(String sppdId) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("sppdId", sppdId))
                .add(Restrictions.isNotNull("approvalSdmFlag"))
                .add(Restrictions.eq("approvalSdmFlag", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("tipePerson"))
                .addOrder(Order.asc("positionId"))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getListSppdPerson1(String sppdId, String personId) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("sppdId", sppdId))
                .add(Restrictions.eq("personId", personId))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getListSppdPersonUsingId(String sppdPersonId) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("sppdPersonId", sppdPersonId))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getListSppdPerson2(String term) throws HibernateException {

        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("personId", term))
                .addOrder(Order.desc("createdDate"))
                .list();

        return results;
    }

    public List<ItSppdPersonEntity> cekRerouteSys(String personId) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("sppdPersonId", personId))
                .addOrder(Order.asc("createdDate"))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getListPersonalFromNip(String nip, Date tanggal) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("personId",nip))
                .add(Restrictions.le("tanggalBerangkat",tanggal))
                .add(Restrictions.ge("tanggalKembali",tanggal))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.eq("approvalSdmFlag", "Y"))
                .addOrder(Order.asc("personId"))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getListTestTanggal(Date tanggal,String nip) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("personId",nip))
                .add(Restrictions.le("tanggalBerangkat",tanggal))
                .add(Restrictions.ge("tanggalKembali",tanggal))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.eq("approvalSdmFlag", "Y"))
                .addOrder(Order.asc("personId"))
                .list();
        return results;
    }

    public List<ItSppdPersonEntity> getDataPersonReport(String sppdId){
        List<ItSppdPersonEntity> listOfResult = new ArrayList<ItSppdPersonEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tperson.*,\n" +
                "\tkota1.kota_name as berangkat,\n" +
                "\tkota2.kota_name as tujuan\n" +
                "from \n" +
                "\tit_hris_sppd_person person\n" +
                "\tleft join im_position posisi on posisi.position_id = person.position_id\n" +
                "\tleft join im_hris_kota kota1 on kota1.kota_id = person.berangkat_dari \n" +
                "\tleft join im_hris_kota kota2 on kota2.kota_id = person.tujuan_ke\n" +
                "where\n" +
                "\tperson.sppd_id = '"+sppdId+"'\n" +
                "and person.flag = 'Y'\n" +
                "and person.approval_sdm_id is not null\n" +
                "\tand person.approval_sdm_flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItSppdPersonEntity result  = new ItSppdPersonEntity();
            result.setSppdPersonId((String) row[0]);
            result.setPersonId((String) row[1]);
            result.setPersonName((String) row[2]);
            result.setTipePerson((String) row[3]);
            result.setBerangkatDari((String) row[4]);
            result.setTujuanKe((String) row[5]);
            result.setPejabatSementara((String) row[8]);
            result.setKeterangan((String) row[9]);
            result.setFlagReroute((String) row[10]);
            result.setSppdId((String) row[11]);
            result.setBerangkatVia((String) row[12]);
            result.setKembaliVia((String) row[13]);
            result.setTiketPergi(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
            result.setBranchId((String) row[31]);
            result.setDivisiId((String) row[32]);
            result.setPositionId((String) row[33]);
            result.setTanggalBerangkat((Date) row[34]);
            result.setTanggalKembali((Date) row[35]);
            result.setTiketKembali(BigDecimal.valueOf(Double.valueOf(row[38].toString())));
            result.setNoteAtasanTransport((String) row[39]);
            result.setNoteSdmTransport((String) row[40]);
            result.setPenginapan((String) row[41]);
            result.setBiayaLain(BigDecimal.valueOf(Double.valueOf(row[42].toString())));
            result.setBiayaMakan(BigDecimal.valueOf(Double.valueOf(row[43].toString())));
            result.setBiayaLokalBerangkat(BigDecimal.valueOf(Double.valueOf(row[44].toString())));
            result.setBiayaTujuan(BigDecimal.valueOf(Double.valueOf(row[45].toString())));
            result.setBiayaLokalKembali(BigDecimal.valueOf(Double.valueOf(row[46].toString())));
            result.setBiayaTambahan(BigDecimal.valueOf(Double.valueOf(row[47].toString())));
            result.setKelompokId((String) row[48]);
            result.setBerangkatDariName((String) row[49]);
            result.setTujuanKeName((String) row[53]);



            listOfResult.add(result);
        }
        return listOfResult;
    }


}
