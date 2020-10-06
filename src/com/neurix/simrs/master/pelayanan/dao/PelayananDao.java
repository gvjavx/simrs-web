package com.neurix.simrs.master.pelayanan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class PelayananDao extends GenericDao<ImSimrsPelayananEntity, String> {

    @Override
    protected Class<ImSimrsPelayananEntity> getEntityClass() {
        return ImSimrsPelayananEntity.class;
    }

    @Override
    public List<ImSimrsPelayananEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPelayananEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_pelayanan") != null){
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }
            if (mapCriteria.get("nama_pelayanan") != null){
                criteria.add(Restrictions.ilike("namaPelayanan", "%" + (String)mapCriteria.get("nama_pelayanan") + "%"));
            }
            if(mapCriteria.get("tipe_pelayanan") != null){
                criteria.add(Restrictions.eq("tipePelayanan",  mapCriteria.get("tipe_pelayanan").toString()));
            }
            if(mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId",  mapCriteria.get("branch_id").toString()));
            }
            if(mapCriteria.get("divisi_id") != null){
                criteria.add(Restrictions.eq("divisiId",  mapCriteria.get("divisi_id").toString()));
            }
            if (mapCriteria.get("in_pelayanan_medic") != null){

                List<String> arrayList = new ArrayList();
                arrayList.add("gudang_obat");
                arrayList.add("apotek");
                arrayList.add("apotek_ri");

                criteria.add(Restrictions.in("tipePelayanan", arrayList));
            }

            if (mapCriteria.get("not_own_branch") != null)
                criteria.add(Restrictions.ne("branchId", mapCriteria.get("not_own_branch")));

            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        List<ImSimrsPelayananEntity> result = criteria.list();
        return result;
    }

    public List<Pelayanan> getListApotek(String branch, String tipeApotek){

        String SQL = "SELECT \n" +
                "id_pelayanan, \n" +
                "nama_pelayanan \n" +
                "FROM im_simrs_pelayanan\n" +
                "WHERE tipe_pelayanan ILIKE :tipe\n" +
                "AND branch_id = :branchId\n" +
                "AND flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
                .setParameter("tipe", tipeApotek)
                .list();

        List<Pelayanan> pelayananList = new ArrayList<>();

        if (results.size() > 0)
        {
            Pelayanan pelayanan;
            for (Object[] obj : results)
            {
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayananList.add(pelayanan);
            }
        }

        return pelayananList;
    }

    public List<Pelayanan> getListPelayananPaket(String branch){

        String SQL = "SELECT \n" +
                "id_pelayanan, \n" +
                "nama_pelayanan \n" +
                "FROM im_simrs_pelayanan\n" +
                "WHERE tipe_pelayanan IN ('rawat_jalan', 'igd')\n" +
                "AND branch_id = :branchId\n" +
                "AND flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
                .list();

        List<Pelayanan> pelayananList = new ArrayList<>();

        if (results.size() > 0)
        {
            Pelayanan pelayanan;
            for (Object[] obj : results) {
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayananList.add(pelayanan);
            }
        }

        return pelayananList;
    }

    public List<ImSimrsPelayananEntity> getDataPelayanan(String namaPelayanan) throws HibernateException {
        List<ImSimrsPelayananEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPelayananEntity.class)
                .add(Restrictions.eq("namaPelayanan", namaPelayanan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImSimrsPelayananEntity> cekData(String idPelayanan) throws HibernateException{
        List<ImSimrsPelayananEntity> results = new ArrayList<>();

        String query = "SELECT a.id_pelayanan, b.id_detail_checkup\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON b.id_pelayanan = a.id_pelayanan\n" +
                "WHERE a.id_pelayanan = '"+idPelayanan+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    public List<Pelayanan> getListPelayananFarmasi(String branchId){

        String SQL = "SELECT \n" +
                "id_pelayanan, nama_pelayanan\n" +
                "FROM im_simrs_pelayanan\n" +
                "WHERE tipe_pelayanan IN ('gudang_obat', 'apotek', 'apotek_ri')\n" +
                "AND flag = 'Y'\n" +
                "AND branch_id = :unit";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .list();

        List<Pelayanan> pelayanans = new ArrayList<>();
        if (results.size() > 0){

            Pelayanan pelayanan;
            for (Object[] obj : results){
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayanans.add(pelayanan);
            }
        }
        return pelayanans;
    }

    public String getNextPelayananId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pelayanan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "SG" + sId;
    }

    public String getNextKodering() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kodering')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%01d", iter.next());

        return sId;
    }

    public List<Pelayanan> getListPelayananWithLab(String tipe){
        String ply = "('rawat_jalan')";
        if("umum".equalsIgnoreCase(tipe)){
            ply = "('rawat_jalan', 'lab', 'radiologi')";
        }
        String SQL = "SELECT\n" +
                "id_pelayanan,\n" +
                "nama_pelayanan,\n" +
                "tipe_pelayanan,\n" +
                "kategori_pelayanan\n" +
                "FROM im_simrs_pelayanan\n" +
                "WHERE tipe_pelayanan IN " + ply + "\n"+
                "AND branch_id = :branchId\n" +
                "ORDER BY nama_pelayanan ASC";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", CommonUtil.userBranchLogin())
                .list();

        List<Pelayanan> pelayananList = new ArrayList<>();

        if (results.size() > 0) {
            for (Object[] obj : results) {
                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0] != null ? obj[0].toString() : "");
                pelayanan.setNamaPelayanan(obj[1] != null ? obj[1].toString() : "");
                pelayanan.setTipePelayanan(obj[2] != null ? obj[2].toString() : "");
                pelayanan.setKategoriPelayanan(obj[3] != null ? obj[3].toString() : "");
                pelayananList.add(pelayanan);
            }
        }
        return pelayananList;
    }
}
