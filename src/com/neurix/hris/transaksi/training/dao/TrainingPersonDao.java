package com.neurix.hris.transaksi.training.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.training.model.ImtHrisHistoryTrainingPegawaiEntity;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingPersonEntity;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class TrainingPersonDao extends GenericDao<ItHrisTrainingPersonEntity,String> {
    @Override
    protected Class<ItHrisTrainingPersonEntity> getEntityClass() {
        return ItHrisTrainingPersonEntity.class;
    }

    @Override
    public List<ItHrisTrainingPersonEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisTrainingPersonEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("training_person_id")!=null) {
                criteria.add(Restrictions.eq("trainingPersonId", (String) mapCriteria.get("training_person_id")));
            }
            if (mapCriteria.get("training_id")!=null) {
                criteria.add(Restrictions.ilike("trainingId", "%" + (String)mapCriteria.get("training_id") + "%"));
            }
            if (mapCriteria.get("approval_id")!=null) {
                criteria.add(Restrictions.ilike("approvalId", "%" + (String)mapCriteria.get("approval_id") + "%"));
            }
            if (mapCriteria.get("approval_bos_id")!=null) {
                criteria.add(Restrictions.ilike("approvalBosId", "%" + (String)mapCriteria.get("approval_bos_id") + "%"));
            }
            if (mapCriteria.get("person_id")!=null) {
                criteria.add(Restrictions.ilike("personId", "%" + (String)mapCriteria.get("person_id") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("trainingPersonId"));
        List<ItHrisTrainingPersonEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_training_person')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }
    public List<ItHrisTrainingPersonEntity> getListTrainingByNipAndId(String id , String nip) throws HibernateException {
        List<ItHrisTrainingPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisTrainingPersonEntity.class)
                .add(Restrictions.eq("trainingId",id))
                .add(Restrictions.eq("personId",nip))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.eq("approvalBosFlag","Y"))
                .list();
        return results;
    }
    public List<ItHrisTrainingPersonEntity> getListTrainingById(String id) throws HibernateException {
        List<ItHrisTrainingPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisTrainingPersonEntity.class)
                .add(Restrictions.eq("trainingId",id))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.eq("approvalBosFlag","Y"))
                .list();
        return results;
    }

    public List<ItHrisTrainingPersonEntity> cekPersonApprove(String id) throws HibernateException {
        List<ItHrisTrainingPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisTrainingPersonEntity.class)
                .add(Restrictions.eq("trainingId",id))
                .add(Restrictions.isNull("approvalBosFlag"))
                .list();
        return results;
    }
    // Generate surrogate id from postgre
    public String getNextTrainingHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_history_training')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "HTP"+sId;
    }
    public void addAndSaveHistory(ImtHrisHistoryTrainingPegawaiEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<TrainingPerson> getDataTrainingPerson(String nip) throws HibernateException {
        List<TrainingPerson> listOfResult = new ArrayList<TrainingPerson>();

        String query = "select\n" +
                "\tperson.training_id,\n" +
                "\tperson.person_id,\n" +
                "\ttraining.tipe_training,\n" +
                "\ttraining.training_start_date,\n" +
                "\ttraining.training_end_date,\n" +
                "\ttraining.instansi,\n" +
                "\tkota.kota_name\n" +
                "from \n" +
                "\tit_hris_training_person person\n" +
                "\tleft join it_hris_training training on training.training_id = person.training_id\n" +
                "\tleft join im_hris_kota kota on kota.kota_id = training.kota\n" +
                "where\n" +
                "\tperson.approval_flag = 'Y'\n" +
                "\tand person.flag = 'Y'\n" +
                "\tand person.person_id = '"+nip+"'\n" +
                "\n" +
                "\t";


        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        TrainingPerson trainingPerson;
        for(Object[] rows: results){
            trainingPerson = new TrainingPerson();
            trainingPerson.setTrainingId(rows[0].toString());
            trainingPerson.setPersonId(rows[1].toString());
            trainingPerson.setTipeTraining(rows[2].toString());
            trainingPerson.setStTrainingStartdate(CommonUtil.convertDateToString((Date) rows[3]));
            trainingPerson.setStTrainingEndDate(CommonUtil.convertDateToString((Date) rows[4]));
            trainingPerson.setInstansi(rows[5].toString());
            trainingPerson.setKota(rows[6].toString());

            listOfResult.add(trainingPerson);
        }

        return listOfResult;
    }

    public List<ItHrisTrainingPersonEntity> getListTrainingForApproval(Map mapCriteria) {
        List<ItHrisTrainingPersonEntity> listOfResult = new ArrayList<ItHrisTrainingPersonEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String nip = null, trainingId = null,atasan=null;
        String searchNip = "" ;
        String searchTrainingId = "" ;
        String searchAtasan = "" ;
        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("training_id") != null) {
                trainingId = (String) mapCriteria.get("training_id");
            }
            if (mapCriteria.get("nip") != null) {
                nip = (String) mapCriteria.get("nip");
            }
            if (mapCriteria.get("nip_atasan") != null) {
                atasan = (String) mapCriteria.get("nip_atasan");
            }

            if (nip!=null){
                if(!nip.equalsIgnoreCase("")){
                    searchNip = " AND training.person_id = '" + nip + "' " ;
                }
            }
            if (trainingId!=null){
                if(!trainingId.equalsIgnoreCase("")){
                    searchTrainingId = " AND training.training_id = '" + trainingId + "' " ;
                }
            }
            if (atasan!=null){
                if(!atasan.equalsIgnoreCase("")){
                    searchAtasan = " AND notifikasi.nip = '" + atasan + "' " ;
                }
            }

            String query = "SELECT DISTINCT training.* FROM  \n" +
                    "                    ( SELECT * FROM it_hris_notifikasi ) notifikasi LEFT JOIN  \n" +
                    "                    ( SELECT * FROM it_hris_training_person ) training ON notifikasi.no_request=training.training_id " +
                    "WHERE notifikasi.tipe_notif_id='TN23' AND training.flag='Y' "+searchAtasan+searchNip+searchTrainingId+" ORDER BY training.training_id DESC";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                ItHrisTrainingPersonEntity itHrisTrainingPersonEntity = new ItHrisTrainingPersonEntity();
                itHrisTrainingPersonEntity.setTrainingPersonId((String) row[0]);
                itHrisTrainingPersonEntity.setTrainingId((String) row[1]);
                itHrisTrainingPersonEntity.setPersonId((String) row[2]);
                itHrisTrainingPersonEntity.setPersonName((String) row[3]);
                itHrisTrainingPersonEntity.setDivisiId((String) row[4]);
                itHrisTrainingPersonEntity.setApprovalId((String) row[5]);
                itHrisTrainingPersonEntity.setApprovalName((String) row[6]);
                itHrisTrainingPersonEntity.setApprovalDate((Timestamp) row[7]);
                itHrisTrainingPersonEntity.setApprovalFlag((String) row[8]);
                itHrisTrainingPersonEntity.setNotApprovalNote((String) row[9]);
                itHrisTrainingPersonEntity.setApprovalBosId((String) row[10]);
                itHrisTrainingPersonEntity.setApprovalBosName((String) row[11]);
                itHrisTrainingPersonEntity.setApprovalBosDate((Timestamp) row[12]);
                itHrisTrainingPersonEntity.setApprovalBosFlag((String) row[13]);
                itHrisTrainingPersonEntity.setNotApprovalBosNote((String) row[14]);
                itHrisTrainingPersonEntity.setCreatedDate((Timestamp) row[15]);
                itHrisTrainingPersonEntity.setCreateDateWho((String) row[16]);
                itHrisTrainingPersonEntity.setLastUpdate((Timestamp) row[17]);
                itHrisTrainingPersonEntity.setLastUpdateWho((String) row[18]);
                itHrisTrainingPersonEntity.setFlag((String) row[19]);
                itHrisTrainingPersonEntity.setAction((String) row[20]);
                itHrisTrainingPersonEntity.setApprovalSdmDate((Timestamp) row[21]);
                itHrisTrainingPersonEntity.setApprovalSdm((String) row[22]);
                itHrisTrainingPersonEntity.setNotApprovalSdmNote((String) row[23]);


                listOfResult.add(itHrisTrainingPersonEntity);
            }
        }
        return listOfResult;
    }
}
