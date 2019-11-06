package com.neurix.hris.transaksi.indisipliner.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.indisipliner.model.ItIndisiplinerEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class IndisiplinerDao extends GenericDao<ItIndisiplinerEntity, String> {
    @Override
    protected Class<ItIndisiplinerEntity> getEntityClass() {
        return ItIndisiplinerEntity.class;
    }

    @Override
    public List<ItIndisiplinerEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItIndisiplinerEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("indisipliner_id") != null) {
                criteria.add(Restrictions.ilike("indisiplinerId", "%" + (String) mapCriteria.get("indisipliner_id")+ "%"));
            }
            if (mapCriteria.get("nip") != null) {
                criteria.add(Restrictions.ilike("nip","%" + (String) mapCriteria.get("nip")+ "%"));
            }
            if (mapCriteria.get("nama") != null) {
                criteria.add(Restrictions.ilike("nama", "%" + (String) mapCriteria.get("nama") + "%"));
            }
            if (mapCriteria.get("divisi_id") != null) {
                criteria.add(Restrictions.eq("divisiId",(String) mapCriteria.get("divisi_id")));
            }
            if (mapCriteria.get("tanggal") != null) {
                criteria.add(Restrictions.eq("tanggal",mapCriteria.get("tanggal")));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId",(String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("closed_flag") != null) {
                criteria.add(Restrictions.eq("closedFlag",(String) mapCriteria.get("closed_flag")));
            }
            if (mapCriteria.get("approval_flag") != null) {
                criteria.add(Restrictions.eq("approvalFlag",(String) mapCriteria.get("approval_flag")));
            }
            if (mapCriteria.get("tipe_indisipliner") != null) {
                criteria.add(Restrictions.eq("tipeIndisipliner",(String) mapCriteria.get("tipe_indisipliner")));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("tanggal",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggal",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal_selesai")));
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.desc("indisiplinerId"));
        List<ItIndisiplinerEntity> results = criteria.list();
        return results;
    }


    public List<ItIndisiplinerEntity> getByCriteria2(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItIndisiplinerEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("indisipliner_id") != null) {
                criteria.add(Restrictions.ilike("indisiplinerId", "%" + (String) mapCriteria.get("indisipliner_id")+ "%"));
            }
            if (mapCriteria.get("nip") != null) {
                criteria.add(Restrictions.ilike("nip","%" + (String) mapCriteria.get("nip")+ "%"));
            }
            if (mapCriteria.get("nama") != null) {
                criteria.add(Restrictions.ilike("nama", "%" + (String) mapCriteria.get("nama") + "%"));
            }
            if (mapCriteria.get("divisi_id") != null) {
                criteria.add(Restrictions.eq("divisiId",(String) mapCriteria.get("divisi_id")));
            }
            if (mapCriteria.get("tanggal") != null) {
                criteria.add(Restrictions.eq("tanggal",mapCriteria.get("tanggal")));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId",(String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("closed_flag") != null) {
                criteria.add(Restrictions.eq("closedFlag",(String) mapCriteria.get("closed_flag")));
            }
            if (mapCriteria.get("approval_flag") != null) {
                criteria.add(Restrictions.eq("approvalFlag",(String) mapCriteria.get("approval_flag")));
            }
            if (mapCriteria.get("tipe_indisipliner") != null) {
                criteria.add(Restrictions.eq("tipeIndisipliner",(String) mapCriteria.get("tipe_indisipliner")));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("tanggal",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggal",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal_selesai")));
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.desc("tanggal"));
        List<ItIndisiplinerEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextIndisiplinerId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_hris_indisipliner')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        return "IDS"+formattedDate+sId;
    }

    public List<ItIndisiplinerEntity> getListIndisiplinerByPersonAndTanggal(String nip , Date tanggal) throws HibernateException {
        List<ItIndisiplinerEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItIndisiplinerEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.le("tanggalAwalPantau",tanggal))
                .add(Restrictions.ge("tanggalAkhirPantau",tanggal))
                .add(Restrictions.ne("closedFlag","Y"))
                .addOrder(Order.asc("tipeIndisipliner"))
                .list();
        return results;
    }
    public List<ItIndisiplinerEntity> getListIndisiplinerFromBlokir(String nip , Date tanggal) throws HibernateException {
        List<ItIndisiplinerEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItIndisiplinerEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.le("tanggalAwalBlokirAbsensi",tanggal))
                .add(Restrictions.ge("tanggalAkhirBlokirSetuju",tanggal))
                .add(Restrictions.ne("closedFlag","Y"))
                .addOrder(Order.asc("tipeIndisipliner"))
                .list();
        return results;
    }
    public List<ItIndisiplinerEntity> getListIndisiplinerFromPantau(String nip , Date tanggal) throws HibernateException {
        List<ItIndisiplinerEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItIndisiplinerEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.le("tanggalAwalPantau",tanggal))
                .add(Restrictions.ge("tanggalAkhirPantau",tanggal))
                .add(Restrictions.ne("closedFlag","Y"))
                .addOrder(Order.asc("tipeIndisipliner"))
                .list();
        return results;
    }
    public List<ItIndisiplinerEntity> getListIndisiplinerFromNip(String nip) throws HibernateException {
        List<ItIndisiplinerEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItIndisiplinerEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.ne("closedFlag","Y"))
                .addOrder(Order.asc("tipeIndisipliner"))
                .list();
        return results;
    }
    public List<ItIndisiplinerEntity> getListIndisiplinerForApproval(Map mapCriteria) {
        List<ItIndisiplinerEntity> listOfResult = new ArrayList<ItIndisiplinerEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String nip = null, indisiplinerId = null,atasan=null;
        String searchNip = "" ;
        String searchIndisiplinerId = "" ;
        String searchAtasan = "" ;
        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("indisipliner_id") != null) {
                indisiplinerId = (String) mapCriteria.get("indisipliner_id");
            }
            if (mapCriteria.get("nip") != null) {
                nip = (String) mapCriteria.get("nip");
            }
            if (mapCriteria.get("nip_atasan") != null) {
                atasan = (String) mapCriteria.get("nip_atasan");
            }

            if (nip!=null){
                if(!nip.equalsIgnoreCase("")){
                    searchNip = " AND indisipliner.nip = '" + nip + "' " ;
                }
            }
            if (indisiplinerId!=null){
                if(!indisiplinerId.equalsIgnoreCase("")){
                    searchIndisiplinerId = " AND indisipliner.indisipliner_id = '" + indisiplinerId + "' " ;
                }
            }
            if (atasan!=null){
                if(!atasan.equalsIgnoreCase("")){
                    searchAtasan = " AND notifikasi.nip = '" + atasan + "' " ;
                }
            }

            String query = "SELECT indisipliner.* FROM  \n" +
                    "                    ( SELECT * FROM it_hris_notifikasi ) notifikasi LEFT JOIN  \n" +
                    "                    ( SELECT * FROM it_hris_indisipliner ) indisipliner ON notifikasi.no_request=indisipliner.indisipliner_id " +
                    "WHERE notifikasi.tipe_notif_id='TN44' AND indisipliner.flag='Y' "+searchAtasan+searchNip+searchIndisiplinerId+" ORDER BY indisipliner.indisipliner_id DESC";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                ItIndisiplinerEntity itIndisiplinerEntity = new ItIndisiplinerEntity();
                itIndisiplinerEntity.setIndisiplinerId((String) row[0]);
                itIndisiplinerEntity.setNip((String) row[1]);
                itIndisiplinerEntity.setNama((String) row[2]);
                itIndisiplinerEntity.setBagianId((String) row[3]);
                itIndisiplinerEntity.setBagianName((String) row[4]);
                itIndisiplinerEntity.setBranchId((String) row[5]);
                itIndisiplinerEntity.setTipeIndisipliner((String) row[6]);
                itIndisiplinerEntity.setKeteranganPelanggaran((String) row[7]);
                itIndisiplinerEntity.setTanggalAwalPantau((Date) row[8]);
                itIndisiplinerEntity.setTanggalAkhirPantau((Date) row[9]);
                itIndisiplinerEntity.setTanggalAwalBlokirAbsensi((Date) row[10]);
                itIndisiplinerEntity.setTanggalAkhirBlokirAbsensi((Date) row[11]);
                itIndisiplinerEntity.setApprovalDate((Timestamp) row[12]);
                itIndisiplinerEntity.setApprovalPersonId((String) row[13]);
                itIndisiplinerEntity.setApprovalPersonName((String) row[14]);
                itIndisiplinerEntity.setApprovalNote((String) row[15]);
                itIndisiplinerEntity.setApprovalFlag((String) row[16]);
                itIndisiplinerEntity.setNotApprovalNote((String) row[17]);
                itIndisiplinerEntity.setClosedFlag((String) row[18]);
                itIndisiplinerEntity.setClosedNote((String) row[19]);
                itIndisiplinerEntity.setClosedDate((Timestamp) row[20]);
                itIndisiplinerEntity.setDampak((String) row[21]);
                itIndisiplinerEntity.setFlag((String) row[22]);
                itIndisiplinerEntity.setAction((String) row[23]);
                itIndisiplinerEntity.setCreatedWho((String) row[24]);
                itIndisiplinerEntity.setLastUpdateWho((String) row[25]);
                itIndisiplinerEntity.setCreatedDate((Timestamp) row[26]);
                itIndisiplinerEntity.setLastUpdate((Timestamp) row[27]);
                itIndisiplinerEntity.setTanggalAkhirBlokirAbsensi((Date) row[28]);
                itIndisiplinerEntity.setTanggal((Date) row[29]);

                listOfResult.add(itIndisiplinerEntity);
            }
        }
        return listOfResult;
    }
}