package com.neurix.akuntansi.master.mappingJurnal.dao;

import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class MappingJurnalDao extends GenericDao<ImMappingJurnalEntity, String> {

    @Override
    protected Class<ImMappingJurnalEntity> getEntityClass() {
        return ImMappingJurnalEntity.class;
    }

    @Override
    public List<ImMappingJurnalEntity> getByCriteria(Map mapCriteria) {
        List<ImMappingJurnalEntity> results = new ArrayList<>();

        List<Object[]> resultQuery = new ArrayList<Object[]>();
        // Get Collection and sorting
        String searchTipeJurnal = "";
        String searchTransId = "";
        String searchFlag = "";
        String searchKodeRekening = "";
        String searchPosisi = "";
        try {

        if (mapCriteria!=null) {
            if (mapCriteria.get("flag") != null) {
                String flag = mapCriteria.get("flag").toString();
                searchFlag = " jurnal.flag = '" + flag + "' ";
            }
            if (mapCriteria.get("posisi") != null) {
                String posisi = mapCriteria.get("posisi").toString();
                searchPosisi = " AND jurnal.posisi = '" + posisi + "' ";
            }
            if (mapCriteria.get("tipe_jurnal_id")!=null) {
                String tipeJurnal = mapCriteria.get("tipe_jurnal_id").toString();
                searchTipeJurnal = " AND jurnal.tipe_jurnal_id =  '" + tipeJurnal + "' ";

            }
            if (mapCriteria.get("trans_id")!=null) {
                String transId = mapCriteria.get("trans_id").toString();
                searchTransId = " AND trans.trans_id =  '" + transId + "' ";
            }
            if (mapCriteria.get("kode_rekening") != null) {
                String kodeRekening = mapCriteria.get("kode_rekening").toString();
                searchKodeRekening = " AND jurnal.trans_id in (select trans_id from im_akun_mapping_jurnal where  kode_rekening =  '" + kodeRekening + "' ) ";
        }
//            String query = "select * from im_akun_mapping_jurnal where  " +searchFlag+ searchTransId + searchTipeJurnal + searchKodeRekening + " order by trans_id,posisi,kode_rekening ";

            String query = "select jurnal.* from im_akun_trans trans " +
                    "join im_akun_mapping_jurnal jurnal on trans.trans_id = jurnal.trans_id" +
                    " where " +searchFlag+ searchTransId + searchTipeJurnal + searchKodeRekening + " order by trans_id,posisi,kode_rekening";
        SQLQuery sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(query);
        /*List<ImMappingJurnalEntity> imMappingJurnalEntityQuery =  (List<ImMappingJurnalEntity>) sqlQuery.list();
        if(imMappingJurnalEntityQuery != null){
            results = imMappingJurnalEntityQuery;
        }*/
            resultQuery =  sqlQuery.list();
            for (Object[] row : resultQuery) {
                if(row.length == 19) {
                    ImMappingJurnalEntity imMappingJurnalEntity = new ImMappingJurnalEntity();
                    imMappingJurnalEntity.setMappingJurnalId(row[0] == null ? "" : row[0].toString());
                    imMappingJurnalEntity.setTipeJurnalId(row[1] == null ? "" : row[1].toString());
                    imMappingJurnalEntity.setKodeRekening(row[2] == null ? "" : row[2].toString());
                    imMappingJurnalEntity.setPosisi(row[3] == null ? "" : row[3].toString());
                    imMappingJurnalEntity.setFlag(row[4] == null ? "" : row[4].toString());
                    imMappingJurnalEntity.setAction(row[5] == null ? "" : row[5].toString());
                    imMappingJurnalEntity.setCreatedWho(row[6] == null ? "" : row[6].toString());
                    imMappingJurnalEntity.setLastUpdateWho(row[7] == null ? "" : row[7].toString());
                    imMappingJurnalEntity.setCreatedDate(row[8] == null ? null : (Timestamp) row[8]);
                    imMappingJurnalEntity.setLastUpdate(row[9] == null ? null : (Timestamp) row[9]);

                    imMappingJurnalEntity.setMasterId(row[10] == null ? "" : row[10].toString());
                    imMappingJurnalEntity.setBukti(row[11] == null ? "" : row[11].toString());
                    imMappingJurnalEntity.setTransId(row[12] == null ? "" : row[12].toString());
                    imMappingJurnalEntity.setKodeBarang(row[13] == null ? "" : row[13].toString());
                    imMappingJurnalEntity.setKeterangan(row[14] == null ? "" : row[14].toString());
                    imMappingJurnalEntity.setKirimList(row[15] == null ? "" : row[15].toString());
                    imMappingJurnalEntity.setDivisiId(row[16] == null ? "" : row[16].toString());
                    imMappingJurnalEntity.setEditBiaya(row[17] == null ? "" : row[17].toString());
                    imMappingJurnalEntity.setParameterCoa(row[18] == null ? "" : row[18].toString());
                    results.add(imMappingJurnalEntity);
                }
            }

    }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return results;
    }

    // Generate surrogate id from postgre
    public String getNextMappingJurnalId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mapping_jurnal')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "IJ"+sId;
    }

    public List<ImMappingJurnalEntity> getListMappingJurnalByTipeJurnalId(String id) throws HibernateException {
        List<ImMappingJurnalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImMappingJurnalEntity.class)
                .add(Restrictions.eq("tipeJurnalId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("mappingJurnalId"))
                .list();
        return results;
    }

    public List<ImMappingJurnalEntity> getMappingByTransId(String transId) throws HibernateException {

        List<ImMappingJurnalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImMappingJurnalEntity.class)
                .add(Restrictions.eq("transId", transId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("mappingJurnalId"))
                .list();

        return results;
    }

    public List<ImMappingJurnalEntity> getMappingByTipeJurnalIdNTransId(String jurnalId,String transId) throws HibernateException {

        List<ImMappingJurnalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImMappingJurnalEntity.class)
                .add(Restrictions.eq("tipeJurnalId", jurnalId))
                .add(Restrictions.eq("transId", transId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("keterangan"))
                .list();

        return results;
    }
    public String tipeJurnalByTransId(String transId){
        String result="";
        String query = "select distinct tipe_jurnal_id from im_akun_mapping_jurnal where trans_id='"+transId+"' and flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result=null;
        }
        return result;
    }

    // Generate surrogate id from postgre
    public String getNextInvoiceId(String jurnalId,String branchId) throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_invoice')");
        DateFormat dfTahun = new SimpleDateFormat("yy");
        DateFormat dfBulan = new SimpleDateFormat("MM");
        Date date = new Date(new java.util.Date().getTime());
        String tahun = dfTahun.format(date);
        String bulan = dfBulan.format(date);
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return jurnalId+branchId+bulan+tahun+sId;
    }
    public String getParameterByTransId(String transId){
        String result="";
        String query = "select keterangan from im_akun_mapping_jurnal where trans_id='"+transId+"' and flag='Y' and keterangan<>'metode_bayar' limit 1";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result=null;
        }
        return result;
    }
    public List<ImMappingJurnalEntity> getListMappingJurnalByTransIdAndKodeRekening(String id,String kodeRekening) throws HibernateException {
        List<ImMappingJurnalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImMappingJurnalEntity.class)
                .add(Restrictions.eq("transId", id))
                .add(Restrictions.eq("kodeRekening", kodeRekening))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("mappingJurnalId"))
                .list();
        return results;
    }
}
