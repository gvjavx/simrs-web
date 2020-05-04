package com.neurix.akuntansi.master.kodeRekening.dao;

import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
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
            if (mapCriteria.get("parent_id")!=null) {
                criteria.add(Restrictions.eq("parentId", (String) mapCriteria.get("parent_id")));
            }
            if (mapCriteria.get("level")!=null) {
                criteria.add(Restrictions.eq("level", (Long) mapCriteria.get("level")));
            }
            if (mapCriteria.get("post_coa")!=null) {
                criteria.add(Restrictions.ilike("kodeRekening", mapCriteria.get("post_coa").toString() + "%"));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        if (mapCriteria.get("parent_order") != null){
            criteria.addOrder(Order.asc("parentId"));
        } else {
            criteria.addOrder(Order.asc("kodeRekening"));
        }

        List<ImKodeRekeningEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextKodeRekeningId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kode_rekening')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%010d", iter.next());
        return sId;
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

    public List<ImKodeRekeningEntity> getKodeRekeningList(String coa) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImKodeRekeningEntity.class);
        criteria.add(Restrictions.ilike("kodeRekening", coa + "%"));
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("kodeRekening"));

        List<ImKodeRekeningEntity> results = criteria.list();
        return results;
    }

    //
    public String getKodeRekeningKas(){
        String result="";
        String query = "select \n" +
                "  kode_rekening \n" +
                "from \n" +
                "  im_akun_kode_rekening \n" +
                "where \n" +
                "  nama_kode_rekening ilike 'kas' \n" +
                "order by \n" +
                "  rekening_id asc \n" +
                "limit \n" +
                "  1\n";
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
    public String searchRekeningIdBankLikeName(String namaBank){
        String result="";
        String query = "select \n" +
                "\trekening_id \n" +
                "from \n" +
                "\tim_akun_kode_rekening \n" +
                "where \n" +
                "\tkode_rekening = '"+namaBank+"'";
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
        String kodeRekeningKas=getKodeRekeningKas();
        String query = "select  \n" +
                "                  rekening_id  \n" +
                "                from  \n" +
                "                im_akun_kode_rekening  \n" +
                "                where  \n" +
                "                nama_kode_rekening like '"+namaTunai+"'  \n" +
                "                and kode_rekening ilike '"+kodeRekeningKas+"%' \n" +
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

    //untuk mendapat kode rekening kas dari jurnal
    public String getKodeRekeningKasForJurnal(String noJurnal){
        String result="";
        String kodeRekeningKas=getKodeRekeningKas();
        String query = "select \n" +
                "  kode_rekening\n" +
                "from \n" +
                "  it_akun_jurnal_detail jd INNER JOIN \n" +
                "  im_akun_kode_rekening kr ON jd.rekening_id=kr.rekening_id\n" +
                "where \n" +
                "  no_jurnal='"+noJurnal+"'\n" +
                "  and kode_rekening ilike '"+kodeRekeningKas+"%'\n";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result=null;
        }
        return result;
    }
    //untuk mendapat kode rekening kas dari jurnal
    public String getNamaRekeningKasForJurnal(String noJurnal){
        String result="";
        String kodeRekeningKas=getKodeRekeningKas();
        String query = "select \n" +
                "  nama_kode_rekening\n" +
                "from \n" +
                "  it_akun_jurnal_detail jd INNER JOIN \n" +
                "  im_akun_kode_rekening kr ON jd.rekening_id=kr.rekening_id\n" +
                "where \n" +
                "  no_jurnal='"+noJurnal+"'\n" +
                "  and kode_rekening ilike '"+kodeRekeningKas+"%'\n";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result=null;
        }
        return result;
    }
    public Integer searchKodeRekeningInJurnal(String rekeningId){
        Integer result=0;
        String[] tabel = {"it_akun_jurnal_detail","it_akun_saldo_akhir","it_akun_jurnal_detail_akhir_tahun"};

        for (int i=0 ; i<3 ; i++){
            String query = "SELECT\n" +
                    "\tCOUNT(rekening_id)\n" +
                    "FROM\n" +
                    "\t"+tabel[i]+"\n" +
                    "WHERE\n" +
                    "\tflag='Y' AND\n" +
                    "\trekening_id='"+rekeningId+"'";
            Object results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query).uniqueResult();
            if (results!=null){
                result = result+Integer.parseInt(results.toString());
            }
        }
        return result;
    }

    public List<KodeRekening> getKodeRekeningLawanByTransId(String transId){

        List<KodeRekening> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tkr.*\n" +
                "from\n" +
                "\tim_akun_mapping_jurnal j\n" +
                "\tleft join im_akun_kode_rekening kr ON kr.kode_rekening ILIKE '%' || j.kode_rekening || '%'\n" +
                "where\n" +
                "\ttrans_id='"+transId+"'\n" +
                "\tand kr.kode_rekening not ilike '1%'\n" +
                "\tand kr.level=5";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            KodeRekening data= new KodeRekening();
            data.setRekeningId((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setNamaKodeRekening((String) row[2]);

            listOfResult.add(data);
        }
        return listOfResult;
    }

    //untuk mendapat rekening id dari coa
    public String getRekeningIdByCoa(String coa) {
        String result = "";
        String query = "select \n" +
                "  rekening_id\n" +
                "from \n" +
                "  im_akun_kode_rekening \n" +
                "where \n" +
                "  kode_rekening = '" + coa + "'\n";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results != null) {
            result = results.toString();
        } else {
            result = null;
        }
        return result;
    }

    public Long getLowestLevelKodeRekening(){

        String SQL = "SELECT rekening_id, MAX(level) FROM im_akun_kode_rekening \n" +
                "GROUP BY rekening_id ORDER BY level DESC LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        Long result = Long.valueOf(0);
        if (results.size() > 0){
            for (Object[] obj : results){
                BigInteger bigresult = obj[1] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[1];
                result = Long.valueOf(bigresult.toString());
            }
        }
        return result;
    }

    public List<ImKodeRekeningEntity> getKodeRekeningListByLevel(String coa, Long level) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImKodeRekeningEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("kodeRekening", coa + "%"),
                        Restrictions.ilike("namaKodeRekening", "%"+coa+"%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.add(Restrictions.eq("level", level));
        criteria.addOrder(Order.desc("kodeRekening"));

        List<ImKodeRekeningEntity> results = criteria.list();
        return results;
    }
}
