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
            if(mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId",  mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("not_own_branch") != null){
                criteria.add(Restrictions.ne("branchId", mapCriteria.get("not_own_branch")));
            }

            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        List<ImSimrsPelayananEntity> result = criteria.list();
        return result;
    }

    public List<Pelayanan> getListApotek(String branch, String tipeApotek){

        String SQL = "SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                "WHERE a.flag = 'Y'\n" +
                "AND b.tipe_pelayanan ILIKE :tipe \n" +
                "AND a.branch_id = :branchId \n" +
                "ORDER BY b.nama_pelayanan ASC";

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

        String SQL = "SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                "WHERE a.flag = 'Y'\n" +
                "AND b.tipe_pelayanan IN ('rawat_jalan', 'igd')\n" +
                "AND a.branch_id = :branchId\n" +
                "ORDER BY b.nama_pelayanan ASC";

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

    public List<ImSimrsPelayananEntity> getDataPelayanan(String idHeaderpelayanan) throws HibernateException {
        List<ImSimrsPelayananEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPelayananEntity.class)
                .add(Restrictions.eq("idHeaderPelayanan", idHeaderpelayanan))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("branchId", CommonUtil.userBranchLogin()))
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

        String SQL = "SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                "WHERE a.flag = 'Y'\n" +
                "AND b.tipe_pelayanan IN ('gudang_obat', 'apotek', 'apotek_ri')\n" +
                "AND a.branch_id = :unit \n" +
                "ORDER BY b.nama_pelayanan ASC";

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
        return "PYN" + sId;
    }

    public List<Pelayanan> getListPelayananWithLab(String tipe){
        String ply = "('rawat_jalan')";
        if("umum".equalsIgnoreCase(tipe)){
            ply = "('rawat_jalan', 'lab', 'radiologi')";
        }
        if("igd".equalsIgnoreCase(tipe)){
            ply = "('igd','ugd')";
        }
        String SQL = "SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                "WHERE a.flag = 'Y'\n" +
                "AND b.tipe_pelayanan IN " + ply + "\n"+
                "AND a.branch_id = :branchId \n" +
                "ORDER BY b.nama_pelayanan ASC";

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

    public List<ImSimrsPelayananEntity> getPelayananByBranch(String branchId) throws HibernateException {
        List<ImSimrsPelayananEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPelayananEntity.class)
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<Pelayanan> getJutsPelayananByBranch(String branchId) throws HibernateException {
        String notLike = "('radiologi','lab','gudang_obat','gizi')";
        return getListPelayanan(branchId, notLike);
    }

    public List<Pelayanan> getJutsPelayananAndLab(String branchId) throws HibernateException {
        String notLike = "('gudang_obat','gizi','apotek','apotek_ri')";
        return getListPelayanan(branchId, notLike);
    }

    public List<Pelayanan> getListPelayanan(String branchId, String notLike){
        String br = "%";
        String wherenot = "";
        List<Pelayanan> pelayananList = new ArrayList<>();
        if(branchId != null && !"".equalsIgnoreCase(branchId)){
            br = branchId;
        }
        if(notLike != null && !"".equalsIgnoreCase(notLike)){
            wherenot = "AND b.tipe_pelayanan NOT IN "+notLike+" \n";
        }
        String SQL = "SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                "WHERE a.flag = 'Y'\n" + wherenot +
                "AND a.branch_id LIKE :branchId\n" +
                "ORDER BY b.nama_pelayanan ASC";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", br)
                .list();

        if (results.size() > 0) {
            for (Object[] obj : results) {
                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0] != null ? obj[0].toString() : "");
                pelayanan.setNamaPelayanan(obj[1] != null ? obj[1].toString() : "");
                pelayanan.setTipePelayanan(obj[2] != null ? obj[2].toString() : "");
                pelayanan.setKategoriPelayanan(obj[3] != null ? obj[3].toString() : "");
                pelayanan.setDivisiId(obj[4] != null ? obj[4].toString() : "");
                pelayanan.setKodePoliVclaim(obj[5] != null ? obj[5].toString() : "");
                pelayananList.add(pelayanan);
            }
        }
        return pelayananList;
    }

    public Pelayanan getObjectPelayanan(Pelayanan bean){
        Pelayanan pelayanan = new Pelayanan();
        if(bean != null){
            String condition = "";
            if(bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                condition += "AND a.id_pelayanan = '"+bean.getIdPelayanan()+"' \n";
            }
            if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                condition += "AND a.branch_id = '"+bean.getBranchId()+"' \n";
            }
            if(bean.getTipePelayanan() != null && !"".equalsIgnoreCase(bean.getTipePelayanan())){
                condition += "AND b.tipe_pelayanan = '"+bean.getTipePelayanan()+"' \n";
            }
            if(bean.getKategoriPelayanan() != null && !"".equalsIgnoreCase(bean.getKategoriPelayanan())){
                condition += "AND b.kategori_pelayanan = '"+bean.getKategoriPelayanan()+"' \n";
            }
            String SQL = "SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "b.kode_vclaim\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                    "WHERE a.flag = 'Y'\n" + condition +
                    "ORDER BY b.nama_pelayanan ASC";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .list();

            if (results.size() > 0) {
                Object[] obj = results.get(0);
                pelayanan.setIdPelayanan(obj[0] != null ? obj[0].toString() : "");
                pelayanan.setNamaPelayanan(obj[1] != null ? obj[1].toString() : "");
                pelayanan.setTipePelayanan(obj[2] != null ? obj[2].toString() : "");
                pelayanan.setKategoriPelayanan(obj[3] != null ? obj[3].toString() : "");
                pelayanan.setDivisiId(obj[4] != null ? obj[4].toString() : "");
                pelayanan.setKodePoliVclaim(obj[5] != null ? obj[5].toString() : "");
            }
        }
        return pelayanan;
    }

    public List<Pelayanan> getListObjectPelayanan(Pelayanan bean){
        List<Pelayanan> pelayananList = new ArrayList<>();
        if(bean != null){
            String condition = "";
            if(bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                condition += "AND a.id_pelayanan = '"+bean.getIdPelayanan()+"' \n";
            }
            if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                condition += "AND a.branch_id = '"+bean.getBranchId()+"' \n";
            }
            if(bean.getTipePelayanan() != null && !"".equalsIgnoreCase(bean.getTipePelayanan())){
                condition += "AND b.tipe_pelayanan = '"+bean.getTipePelayanan()+"' \n";
            }
            if(bean.getKategoriPelayanan() != null && !"".equalsIgnoreCase(bean.getKategoriPelayanan())){
                condition += "AND b.kategori_pelayanan = '"+bean.getKategoriPelayanan()+"' \n";
            }
            String SQL = "SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "b.kode_vclaim\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                    "WHERE a.flag = 'Y'\n" + condition +
                    "ORDER BY b.nama_pelayanan ASC";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj: results){
                    Pelayanan pelayanan = new Pelayanan();
                    pelayanan.setIdPelayanan(obj[0] != null ? obj[0].toString() : "");
                    pelayanan.setNamaPelayanan(obj[1] != null ? obj[1].toString() : "");
                    pelayanan.setTipePelayanan(obj[2] != null ? obj[2].toString() : "");
                    pelayanan.setKategoriPelayanan(obj[3] != null ? obj[3].toString() : "");
                    pelayanan.setDivisiId(obj[4] != null ? obj[4].toString() : "");
                    pelayanan.setKodePoliVclaim(obj[5] != null ? obj[5].toString() : "");
                    pelayananList.add(pelayanan);
                }
            }
        }
        return pelayananList;
    }
    public Pelayanan getPelayananById(String column, String value){
        Pelayanan pelayanan = new Pelayanan();
        if(column != null && value != null){
            String condition = "";
            if("idPelayanan".equalsIgnoreCase(column)){
                condition = "AND a.id_pelayanan = '"+value+"' \n";
            }
            if("tipePelayanan".equalsIgnoreCase(column)){
                condition = "AND b.tipe_pelayanan = '"+value+"' \n";
            }
            if("kategoriPelayanan".equalsIgnoreCase(column)){
                condition = "AND b.kategori_pelayanan = '"+value+"' \n";
            }
            String SQL = "SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "b.kode_vclaim\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                    "WHERE a.flag = 'Y'\n" + condition +
                    "ORDER BY b.nama_pelayanan ASC";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .list();

            if (results.size() > 0) {
                Object[] obj = results.get(0);
                pelayanan.setIdPelayanan(obj[0] != null ? obj[0].toString() : "");
                pelayanan.setNamaPelayanan(obj[1] != null ? obj[1].toString() : "");
                pelayanan.setTipePelayanan(obj[2] != null ? obj[2].toString() : "");
                pelayanan.setKategoriPelayanan(obj[3] != null ? obj[3].toString() : "");
                pelayanan.setDivisiId(obj[4] != null ? obj[4].toString() : "");
                pelayanan.setKodePoliVclaim(obj[5] != null ? obj[5].toString() : "");
            }
        }
        return pelayanan;
    }
}
