package com.neurix.simrs.master.pelayanan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

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
            if (mapCriteria.get("not_null") != null){
                criteria.add(Restrictions.isNotNull("idHeaderPelayanan"));
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
        return getListPelayanan(branchId, notLike, null);
    }

    public List<Pelayanan> getJutsPelayananAndLab(String branchId) throws HibernateException {
        String notLike = "('gudang_obat','gizi','apotek','apotek_ri')";
        return getListPelayanan(branchId, notLike, null);
    }

    public List<Pelayanan> getJutsPelayananOnly(String branchId) throws HibernateException {
        String like = "('rawat_jalan', 'igd')";
        return getListPelayanan(branchId, null, like);
    }

    public List<Pelayanan> getJutsPelayananOnlyRJ(String branchId) throws HibernateException {
        String like = "('rawat_jalan')";
        return getListPelayanan(branchId, null, like);
    }

    public List<Pelayanan> getListPelayanan(String branchId, String notLike, String like){
        String br = "%";
        String where = "";
        List<Pelayanan> pelayananList = new ArrayList<>();
        if(branchId != null && !"".equalsIgnoreCase(branchId)){
            br = branchId;
        }
        if(notLike != null && !"".equalsIgnoreCase(notLike)){
            where = "AND b.tipe_pelayanan NOT IN "+notLike+" \n";
        }
        if(like != null && !"".equalsIgnoreCase(like)){
            where = "AND b.tipe_pelayanan IN "+like+" \n";
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
                "WHERE a.flag = 'Y'\n" + where +
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
            if(bean.getKodePoliVclaim() != null && !"".equalsIgnoreCase(bean.getKodePoliVclaim())){
                condition += "AND b.kode_vclaim = '"+bean.getKodePoliVclaim()+"' \n";
            }
            String SQL = "SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "b.kode_vclaim,\n" +
                    "a.branch_id\n" +
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
                pelayanan.setBranchId(obj[6] != null ? obj[6].toString() : "");
            }
        }
        return pelayanan;
    }

    public List<Pelayanan> getListObjectPelayanan(Pelayanan bean){
        List<Pelayanan> pelayananList = new ArrayList<>();
        if(bean != null){
            String condition = "";
            String flag = "Y";
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
            if(bean.getNamaPelayanan() != null && !"".equalsIgnoreCase(bean.getNamaPelayanan())){
                condition += "AND b.nama_pelayanan ILIKE '%"+bean.getNamaPelayanan()+"%' \n";
            }
            if(bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
                flag = bean.getFlag();
            }
            String SQL = "SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "b.kode_vclaim,\n" +
                    "d.position_name, \n" +
                    "b.id_header_pelayanan,\n" +
                    "c.branch_name,\n" +
                    "a.branch_id\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                    "INNER JOIN im_branches c ON a.branch_id = c.branch_id \n" +
                    "LEFT JOIN im_position d ON b.divisi_id = d.position_id\n" +
                    "WHERE a.flag = :flag \n" + condition +
                    "ORDER BY b.nama_pelayanan ASC";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("flag", flag)
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
                    pelayanan.setDivisiName(obj[6] != null ? obj[6].toString() : "");
                    pelayanan.setIdHeaderPelayanan(obj[7] != null ? obj[7].toString() : "");
                    pelayanan.setBranchName(obj[8] != null ? obj[8].toString() : "");
                    pelayanan.setBranchId(obj[9] != null ? obj[9].toString() : "");
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

    public List <Pelayanan> getListPelayananTelemedic(String branchId) {
        String sql = "SELECT ply.id_pelayanan, hply.nama_pelayanan \n" +
                "FROM im_simrs_header_pelayanan hply\n" +
                "INNER JOIN im_simrs_pelayanan ply ON ply.id_header_pelayanan = hply.id_header_pelayanan\n" +
                "INNER JOIN im_simrs_tindakan tdk ON ply.id_pelayanan = tdk.id_pelayanan\n" +
                "INNER JOIN im_simrs_header_tindakan htdk ON tdk.id_header_tindakan = htdk.id_header_tindakan\n" +
                "WHERE htdk.flag_konsul_tele = 'Y'\n" +
                "AND hply.flag = 'Y' \n" +
                "AND ply.branch_id = :branchId\n" +
                "ORDER BY nama_pelayanan DESC\n";


        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setParameter("branchId", branchId)
                .list();

        List<Pelayanan> pelayananList = new ArrayList<>();

        if (results.size() > 0) {
            for (Object[] obj : results) {
                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());

                pelayananList.add(pelayanan);
            }
        }

        return pelayananList;

    }

    public List<Pelayanan> getListPelayananByTipe(String tipe, String branchId){

        String SQL = "SELECT \n" +
                "b.id_pelayanan,\n" +
                "a.nama_pelayanan,\n" +
                "a.tipe_pelayanan,\n" +
                "a.divisi_id\n" +
                "FROM\n" +
                "im_simrs_header_pelayanan a\n" +
                "INNER JOIN (SELECT id_pelayanan, id_header_pelayanan, branch_id FROM im_simrs_pelayanan WHERE flag = 'Y') b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                "WHERE \n" +
                "b.branch_id = '"+branchId+"'\n" +
                "AND a.tipe_pelayanan = '"+tipe+"'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<Pelayanan> listPelayanan = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayanan.setTipePelayanan(obj[2].toString());
                pelayanan.setDivisiId(obj[3].toString());
                listPelayanan.add(pelayanan);
            }
        }
        return listPelayanan;
    }

    public List<Pelayanan> getListPelayananByBranch(String branchId){

        String SQL = "SELECT \n" +
                "p.id_pelayanan,\n" +
                "hp.nama_pelayanan\n" +
                "FROM (SELECT * FROM im_simrs_pelayanan WHERE flag = 'Y') p \n" +
                "INNER JOIN im_simrs_header_pelayanan hp ON hp.id_header_pelayanan = p.id_header_pelayanan\n" +
                "WHERE p.branch_id = '"+branchId+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<Pelayanan> pelayananList = new ArrayList<>();
        if (list.size() > 0){

            for (Object[] obj : list){
                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayananList.add(pelayanan);
            }
        }

        return pelayananList;
    }
}
