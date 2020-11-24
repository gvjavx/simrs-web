package com.neurix.simrs.master.dokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class DokterDao extends GenericDao<ImSimrsDokterEntity, String> {

    @Override
    protected Class<ImSimrsDokterEntity> getEntityClass() {
        return ImSimrsDokterEntity.class;
    }

    @Override
    public List<ImSimrsDokterEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class);
        if (mapCriteria != null){
            if (mapCriteria.get("id_dokter") != null){
                criteria.add(Restrictions.eq("idDokter", mapCriteria.get("id_dokter").toString()));
            }
            if (mapCriteria.get("nama_dokter") != null){
                criteria.add(Restrictions.ilike("namaDokter", "%" + (String)mapCriteria.get("nama_dokter") + "%"));
            }
            if (mapCriteria.get("id_pelayanan") != null){
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        List<ImSimrsDokterEntity> result = criteria.list();
        return result;
    }

    public List<Dokter> getListDokterById(String idDokter) {
        List<Dokter> list = new ArrayList<>();

        if (idDokter != null && !"".equalsIgnoreCase(idDokter)){

            String SQL = "SELECT \n" +
                    "a.id_dokter, \n" +
                    "a.nama_dokter, \n" +
                    "a.kode_dpjp, \n" +
                    "a.flag_tele, \n" +
                    "a.kuota, \n" +
                    "a.kuota_tele, \n" +
                    "b.id_pelayanan, \n" +
                    "c.nama_pelayanan, \n" +
                    "a.lat, \n" +
                    "a.lon \n" +
                    "FROM im_simrs_dokter a\n" +
                    "INNER JOIN im_simrs_dokter_pelayanan b ON a.id_dokter = b.id_dokter\n" +
                    "INNER JOIN im_simrs_pelayanan c ON c.id_pelayanan = b.id_pelayanan\n" +
                    "WHERE a.id_dokter = :id";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDokter)
                    .list();

            if(result.size() > 0){

                for (Object[] obj: result){
                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(obj[0] == null ? "" : obj[0].toString());
                    dokter.setNamaDokter(obj[1] == null ? "" : obj[1].toString());
                    dokter.setKodeDpjp(obj[2] == null ? "" : obj[2].toString());
                    dokter.setFlagTele(obj[3] == null ? "" : obj[3].toString());
                    dokter.setKuota(obj[4] == null ? "" : obj[4].toString());
                    dokter.setKuotaTele(obj[5] == null ? "" : obj[5].toString());
                    dokter.setIdPelayanan(obj[6] == null ? "" : obj[6].toString());
                    dokter.setNamaPelayanan(obj[7] == null ? "" : obj[7].toString());
                    dokter.setLat(obj[8] == null ? "": obj[8].toString());
                    dokter.setLon(obj[9] == null ? "": obj[9].toString());
                    list.add(dokter);
                }
            }
        }
        return list;
    }

    public List<Dokter> getListDokterByPelayanan(String idPelayanan, String notLike){

        List<Dokter> list = new ArrayList<>();
        String notLikeIdDokter = "";

        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)){

            if(!"".equalsIgnoreCase(notLike) && notLike != null){
                notLikeIdDokter = "AND a.id_dokter NOT IN "+notLike;
            }
            String SQL = "SELECT \n" +
                    "a.id_dokter, \n" +
                    "a.nama_dokter, \n" +
                    "a.kode_dpjp, \n" +
                    "a.flag_tele, \n" +
                    "a.kuota, \n" +
                    "a.kuota_tele \n" +
                    "FROM im_simrs_dokter a\n" +
                    "INNER JOIN im_simrs_dokter_pelayanan b ON a.id_dokter = b.id_dokter\n" +
                    "WHERE b.id_pelayanan = :id \n"+notLikeIdDokter;
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPelayanan)
                    .list();

            if(result.size() > 0){

                for (Object[] obj: result){
                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(obj[0] == null ? "" : obj[0].toString());
                    dokter.setNamaDokter(obj[1] == null ? "" : obj[1].toString());
                    dokter.setKodeDpjp(obj[2] == null ? "" : obj[2].toString());
                    dokter.setFlagTele(obj[3] == null ? "" : obj[3].toString());
                    dokter.setKuota(obj[4] == null ? "" : obj[4].toString());
                    dokter.setKuotaTele(obj[5] == null ? "" : obj[5].toString());
                    list.add(dokter);
                }
            }
        }
        return list;
    }

    //for typeahead
    public List<ImSimrsDokterEntity> getDokterListByLike(String name) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("kodering", name + "%"),
                        Restrictions.ilike("namaDokter", "%"+name+"%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idDokter"));

        List<ImSimrsDokterEntity> results = criteria.list();
        return results;
    }

    public List<ImSimrsDokterEntity> getDataDokter(String namaDokter) throws HibernateException {
        List<ImSimrsDokterEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class)
                .add(Restrictions.eq("namaDokter", namaDokter))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ImSimrsDokterEntity> getDataDokterByKodering(String kodering) throws HibernateException {
        List<ImSimrsDokterEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class)
                .add(Restrictions.eq("kodering", kodering))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public String getNextDokter() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_dokter')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "DKR" + sId;
    }

    public String getNextKodering() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kodering')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%01d", iter.next());

        return sId;
    }

    public List<ImSimrsDokterEntity> cekData(String idDokter) throws HibernateException{
        List<ImSimrsDokterEntity> results = new ArrayList<>();

        String query = "SELECT a.id_team_dokter, b.id_dokter\n" +
                "FROM it_simrs_dokter_team a\n" +
                "INNER JOIN im_simrs_dokter b ON b.id_dokter = a.id_dokter\n" +
                "WHERE a.id_dokter = '"+idDokter+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    //for typeahead
    public List<ImSimrsDokterEntity> getDokterListByLikeDokterName(String dokterName) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("idDokter", dokterName + "%"),
                        Restrictions.ilike("namaDokter", "%"+dokterName+"%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idDokter"));

        List<ImSimrsDokterEntity> results = criteria.list();
        return results;
    }

    public List<Dokter> getListDokterByBranchId(String branchId, String idDokter){
        List<Dokter> dokterList = new ArrayList<>();
        String notLike = "";
        if(branchId != null && !"".equalsIgnoreCase(branchId)){

            if(!"".equalsIgnoreCase(idDokter) && idDokter != null){
                notLike = "AND b.id_dokter NOT IN "+idDokter;
            }
            String SQL = "SELECT \n" +
                    "a.id_dokter,\n" +
                    "a.nama_dokter,\n" +
                    "c.id_pelayanan,\n" +
                    "c.nama_pelayanan,\n" +
                    "c.branch_id,\n" +
                    "c.tipe_pelayanan,\n" +
                    "a.sip\n" +
                    "FROM im_simrs_dokter a\n" +
                    "INNER JOIN im_simrs_dokter_pelayanan b ON a.id_dokter = b.id_dokter\n" +
                    "INNER JOIN im_simrs_pelayanan c ON b.id_pelayanan = c.id_pelayanan\n" +
                    "WHERE c.branch_id = :branchId\n" + notLike +
                    "AND c.tipe_pelayanan = 'rawat_jalan' \n"+
                    "ORDER BY c.nama_pelayanan ASC";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branchId", branchId)
                    .list();

            if(result.size() > 0){
                for (Object[] obj: result){
                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(obj[0] != null ? obj[0].toString() : "");
                    dokter.setNamaDokter(obj[1] != null ? obj[1].toString() : "");
                    dokter.setIdPelayanan(obj[2] != null ? obj[2].toString() : "");
                    dokter.setNamaPelayanan(obj[3] != null ? obj[3].toString() : "");
                    dokter.setSip(obj[6] != null ? obj[6].toString() : "");
                    dokterList.add(dokter);
                }
            }
        }
        return dokterList;
    }

    public List<Dokter> getListDokterByIdDetailCheckup(String idDetailCheckup, String approve){
        List<Dokter> dokterList = new ArrayList<>();
        String flag = "";
        if("Y".equalsIgnoreCase(approve)){
            flag = "AND a.flag_approve = 'Y' \n";
        }
        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)){
            String SQL = "SELECT \n" +
                    "b.id_dokter, \n" +
                    "b.nama_dokter, \n" +
                    "c.id_pelayanan, \n" +
                    "c.nama_pelayanan, \n" +
                    "a.id_team_dokter, \n" +
                    "a.flag_approve, \n" +
                    "a.jenis_dpjp, \n" +
                    "a.keterangan\n" +
                    "FROM it_simrs_dokter_team a\n" +
                    "INNER JOIN im_simrs_dokter b ON a.id_dokter = b.id_dokter\n" +
                    "INNER JOIN im_simrs_pelayanan c ON a.id_pelayanan = c.id_pelayanan\n" +
                    "WHERE a.id_detail_checkup = :id \n" + flag +
                    "ORDER BY a.created_date ASC";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDetailCheckup)
                    .list();

            if(result.size() > 0){
                for (Object[] obj: result){
                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(obj[0] != null ? obj[0].toString() : "");
                    dokter.setNamaDokter(obj[1] != null ? obj[1].toString() : "");
                    dokter.setIdPelayanan(obj[2] != null ? obj[2].toString() : "");
                    dokter.setNamaPelayanan(obj[3] != null ? obj[3].toString() : "");
                    dokter.setIdTeamDokter(obj[4] != null ? obj[4].toString() : "");
                    dokter.setFlagApprove(obj[5] != null ? obj[5].toString() : null);
                    dokter.setJenisDpjp(obj[6] != null ? obj[6].toString() : null);
                    dokter.setKeterangan(obj[7] != null ? obj[7].toString() : null);
                    dokterList.add(dokter);
                }
            }
        }
        return dokterList;
    }
}
