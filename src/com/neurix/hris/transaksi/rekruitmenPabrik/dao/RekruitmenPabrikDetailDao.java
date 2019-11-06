
package com.neurix.hris.transaksi.rekruitmenPabrik.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.ItRekruitmenPabrikDetailHistoryEntity;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.ItRekruitmenPabrikHistoryEntity;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.ItRekruitmenPabrikDetailEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
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
public class RekruitmenPabrikDetailDao extends GenericDao<ItRekruitmenPabrikDetailEntity, String> {

    @Override
    protected Class<ItRekruitmenPabrikDetailEntity> getEntityClass() {
        return ItRekruitmenPabrikDetailEntity.class;
    }

    @Override
    public List<ItRekruitmenPabrikDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenPabrikDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("rekruitmen_pabrik_id")!=null) {
                criteria.add(Restrictions.eq("rekruitmenPabrikId", (String) mapCriteria.get("rekruitmen_pabrik_id")));
            }
            if (mapCriteria.get("rekruitmen_pabrik_detail_id")!=null) {
                criteria.add(Restrictions.eq("rekruitmenPabrikDetailId", (String) mapCriteria.get("rekruitmen_pabrik_detail_id")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
/*            if (mapCriteria.get("nama_pegawai")!=null) {
                criteria.add(Restrictions.ilike("namaPegawai", "%" + (String)mapCriteria.get("nama_pegawai") + "%"));
            }
            if (mapCriteria.get("divisi")!=null) {
                criteria.add(Restrictions.eq("divisi", (String) mapCriteria.get("divisi")));
            }
            if (mapCriteria.get("branch")!=null) {
                criteria.add(Restrictions.eq("branch", (String) mapCriteria.get("branch")));
            }
            if (mapCriteria.get("tipe_pegawai")!=null) {
                criteria.add(Restrictions.eq("tipePegawai", (String) mapCriteria.get("tipe_pegawai")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }*/
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("rekruitmenPabrikDetailId"));

        List<ItRekruitmenPabrikDetailEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextRekruitmenPabrikDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekruitmen_pabrik_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        return "RPD"+formattedDate+sId;
    }

    public String getNextPersonalHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ItRekruitmenPabrikDetailEntity> getListPersonal(String term) throws HibernateException {

        List<ItRekruitmenPabrikDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenPabrikDetailEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }
    public List<ItRekruitmenPabrikDetailEntity> getListPersonalByBranch(String term, String branch) throws HibernateException {

        List<ItRekruitmenPabrikDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenPabrikDetailEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public List<ItRekruitmenPabrikDetailEntity> getListPersonalByBranch2(String term) throws HibernateException {
        List<ItRekruitmenPabrikDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenPabrikDetailEntity.class)
                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.ilike("nip",term))
                //.add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }
    // Generate surrogate id from postgre
    public String getNextRekruitmenPabrikDetailHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekruitmen_pabrik_detail_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }
    public void addAndSaveHistory(ItRekruitmenPabrikDetailHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
    public List<ItRekruitmenPabrikDetailEntity> getListRekruitmenPabrikForApproval(Map mapCriteria) {
        List<ItRekruitmenPabrikDetailEntity> listOfResult = new ArrayList<ItRekruitmenPabrikDetailEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String nip = null, rekruitmenPabrikId = null,atasan=null;
        String searchNip = "" ;
        String searchRekruitmenPabrikId = "" ;
        String searchAtasan = "" ;
        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("rekruitmen_pabrik_id") != null) {
                rekruitmenPabrikId = (String) mapCriteria.get("rekruitmen_pabrik_id");
            }
            if (mapCriteria.get("nip") != null) {
                nip = (String) mapCriteria.get("nip");
            }
            if (mapCriteria.get("nip_atasan") != null) {
                atasan = (String) mapCriteria.get("nip_atasan");
            }

            if (nip!=null){
                if(!nip.equalsIgnoreCase("")){
                    searchNip = " AND rekruitmenPabrik.nip = '" + nip + "' " ;
                }
            }
            if (rekruitmenPabrikId!=null){
                if(!rekruitmenPabrikId.equalsIgnoreCase("")){
                    searchRekruitmenPabrikId = " AND rekruitmenPabrik.rekruitmen_pabrik_id = '" + rekruitmenPabrikId + "' " ;
                }
            }
            if (atasan!=null){
                if(!atasan.equalsIgnoreCase("")){
                    searchAtasan = " AND notifikasi.nip = '" + atasan + "' " ;
                }
            }

            String query = "SELECT rekruitmenPabrik.* FROM  \n" +
                    "                    ( SELECT * FROM it_hris_notifikasi ) notifikasi LEFT JOIN  \n" +
                    "                    ( SELECT * FROM it_hris_rekruitmen_pabrik_detail ) rekruitmenPabrik ON notifikasi.no_request=rekruitmenPabrik.rekruitmen_pabrik_id " +
                    "WHERE notifikasi.tipe_notif_id='TN99' AND rekruitmenPabrik.flag='Y' "+searchAtasan+searchNip+searchRekruitmenPabrikId+" ORDER BY rekruitmenPabrik.rekruitmen_pabrik_id DESC";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                ItRekruitmenPabrikDetailEntity itRekruitmenPabrikDetailEntity = new ItRekruitmenPabrikDetailEntity();
                itRekruitmenPabrikDetailEntity.setRekruitmenPabrikDetailId((String) row[0]);
                itRekruitmenPabrikDetailEntity.setRekruitmenPabrikId((String) row[1]);
                itRekruitmenPabrikDetailEntity.setNip((String) row[2]);
                itRekruitmenPabrikDetailEntity.setNamaPegawai((String) row[3]);
                itRekruitmenPabrikDetailEntity.setPosisiLama((String) row[4]);
                itRekruitmenPabrikDetailEntity.setPosisiBaru((String) row[5]);
                itRekruitmenPabrikDetailEntity.setTanggalAktif((Timestamp) row[6]);
                itRekruitmenPabrikDetailEntity.setStatusGiling((String) row[7]);
                itRekruitmenPabrikDetailEntity.setApprovalGmFlag((String) row[8]);
                itRekruitmenPabrikDetailEntity.setApprovalGmId((String) row[9]);
                itRekruitmenPabrikDetailEntity.setApprovalGmName((String) row[10]);
                itRekruitmenPabrikDetailEntity.setApprovalGmdate((Timestamp) row[11]);
                itRekruitmenPabrikDetailEntity.setApprovalSdmId((String) row[12]);
                itRekruitmenPabrikDetailEntity.setApprovalSdmFlag((String) row[13]);
                itRekruitmenPabrikDetailEntity.setApprovalSdmName((String) row[14]);
                itRekruitmenPabrikDetailEntity.setApprovalSdmdate((Timestamp) row[15]);
                itRekruitmenPabrikDetailEntity.setNoKontrak((String) row[16]);
                itRekruitmenPabrikDetailEntity.setCreatedDate((Timestamp) row[17]);
                itRekruitmenPabrikDetailEntity.setLastUpdate((Timestamp) row[18]);
                itRekruitmenPabrikDetailEntity.setCreatedWho((String) row[19]);
                itRekruitmenPabrikDetailEntity.setLastUpdateWho((String) row[20]);
                itRekruitmenPabrikDetailEntity.setFlag((String) row[21]);
                itRekruitmenPabrikDetailEntity.setAction((String) row[22]);

                listOfResult.add(itRekruitmenPabrikDetailEntity);
            }
        }
        return listOfResult;
    }

}
