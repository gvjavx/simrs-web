package com.neurix.simrs.master.labdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LabDetailDao extends GenericDao<ImSimrsLabDetailEntity, String> {

    @Override
    protected Class<ImSimrsLabDetailEntity> getEntityClass() {
        return ImSimrsLabDetailEntity.class;
    }

    @Override
    public List<ImSimrsLabDetailEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsLabDetailEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_lab_detail")!=null) {
                criteria.add(Restrictions.eq("idLabDetail", (String) mapCriteria.get("id_lab_detail")));
            }
            if (mapCriteria.get("id_lab")!=null) {
                criteria.add(Restrictions.eq("idLab", (String) mapCriteria.get("id_lab")));
            }
            if (mapCriteria.get("nama_detail_periksa")!=null) {
                criteria.add(Restrictions.ilike("namaDetailPeriksa", "%" + (String)mapCriteria.get("nama_detail_periksa") + "%"));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idLabDetail"));
        List<ImSimrsLabDetailEntity> results = criteria.list();
        return results;
    }

    public List<ImSimrsLabDetailEntity> getDataLabDetail(String namaDetailPeriksa) throws HibernateException {
        List<ImSimrsLabDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsLabDetailEntity.class)
                .add(Restrictions.eq("namaDetailPeriksa", namaDetailPeriksa))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextLabDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_lab_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "LDB" + sId;
    }

    public List<ImSimrsLabDetailEntity> cekData(String idLabDetail) throws HibernateException{
        List<ImSimrsLabDetailEntity> results = new ArrayList<>();

        String query = "SELECT a.id_lab_detail, b.id_periksa_lab_detail\n" +
                "FROM im_simrs_lab_detail a\n" +
                "INNER JOIN it_simrs_periksa_lab_detail b ON b.id_lab_detail = a.id_lab_detail\n" +
                "WHERE a.id_lab_detail = '"+idLabDetail+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    public List<LabDetail> getDetailLab(String idLab, String branchId){
        List<LabDetail> labDetailList = new ArrayList<>();
        if(idLab != null && !"".equalsIgnoreCase(idLab)){
            String SQL = "SELECT \n" +
                    "a.id_lab,\n" +
                    "b.id_lab_detail,\n" +
                    "b.nama_detail_periksa,\n" +
                    "b.satuan,\n" +
                    "b.keterangan_acuan,\n" +
                    "b.tarif\n" +
                    "FROM im_simrs_lab a\n" +
                    "INNER JOIN im_simrs_lab_detail b ON a.id_lab = b.id_lab\n" +
                    "WHERE b.id_lab = :id\n" +
                    "AND a.branch_id = :branchId";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idLab)
                    .setParameter("branchId", branchId)
                    .list();

            if(result.size() > 0){
                for (Object[] obj: result){
                    LabDetail labDetail = new LabDetail();
                    labDetail.setIdLab(obj[0] != null ? obj[0].toString() : "");
                    labDetail.setIdLabDetail(obj[1] != null ? obj[1].toString() : "");
                    labDetail.setNamaDetailPeriksa(obj[2] != null ? obj[2].toString() : "");
                    labDetail.setSatuan(obj[3] != null ? obj[3].toString() : "");
                    labDetail.setKetentuanAcuan(obj[4] != null ? obj[4].toString() : "");
                    labDetail.setTarif(obj[5] != null ? (BigDecimal) obj[5] : null);
                    labDetailList.add(labDetail);
                }
            }
        }
        return labDetailList;
    }

    public List<LabDetail> getDataParameterPemeriksaan(LabDetail bean){
        List<LabDetail> labDetailList = new ArrayList<>();
        String flag = "Y";
        String condition = "";
        if(bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            flag = bean.getFlag();
        }
        if(bean.getIdLabDetail() != null && !"".equalsIgnoreCase(bean.getIdLabDetail())){
            condition = "AND a.id_lab_detail = '"+bean.getIdLabDetail()+"' \n";
        }
        if(bean.getIdLab() != null && !"".equalsIgnoreCase(bean.getIdLab())){
            condition = condition + "AND a.id_lab = '"+bean.getIdLab()+"' \n";
        }
        if(bean.getIdParameterPemeriksaan() != null && !"".equalsIgnoreCase(bean.getIdParameterPemeriksaan())){
            condition = condition + "AND a.id_parameter_pemeriksaan = '"+bean.getIdParameterPemeriksaan()+"' \n";
        }
        if(bean.getNamaDetailPeriksa() != null && !"".equalsIgnoreCase(bean.getNamaDetailPeriksa())){
            condition = condition + "AND a.nama_pemeriksaan ILIKE '%"+bean.getNamaDetailPeriksa()+"%' \n";
        }
        if(bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())){
            condition = condition + "AND d.id_kategori_lab = '"+bean.getIdKategoriLab()+"' \n";
        }
        if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            condition = condition + "AND a.branch_id = '"+bean.getBranchId()+"' \n";
        }

        String SQL = "SELECT\n" +
                "a.id_lab_detail,\n" +
                "a.id_lab,\n" +
                "b.nama_lab,\n" +
                "a.id_parameter_pemeriksaan,\n" +
                "c.nama_pemeriksaan,\n" +
                "a.tarif,\n" +
                "c.keterangan_acuan_l,\n" +
                "c.keterangan_acuan_p,\n" +
                "c.satuan,\n" +
                "d.id_kategori_lab,\n" +
                "d.nama_kategori,\n" +
                "a.branch_id\n" +
                "FROM im_simrs_lab_detail a\n" +
                "INNER JOIN im_simrs_lab b ON a.id_lab = b.id_lab\n" +
                "INNER JOIN im_simrs_parameter_pemeriksaan c ON a.id_parameter_pemeriksaan = c.id_parameter_pemeriksaan\n" +
                "INNER JOIN im_simrs_kategori_lab d ON c.id_kategori_lab = d.id_kategori_lab\n" +
                "WHERE a.flag = :flag \n" + condition;
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("flag", flag)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                LabDetail detail = new LabDetail();
                detail.setIdLabDetail(obj[0] != null ? obj[0].toString() : null);
                detail.setIdLab(obj[1] != null ? obj[1].toString() : null);
                detail.setNamaLab(obj[2] != null ? obj[2].toString() : null);
                detail.setIdParameterPemeriksaan(obj[3] != null ? obj[3].toString() : null);
                detail.setNamaDetailPeriksa(obj[4] != null ? obj[4].toString() : null);
                detail.setTarif(obj[5] != null ? (BigDecimal) obj[5] : null);
                detail.setKeteranganAcuanL(obj[6] != null ? obj[6].toString() : null);
                detail.setKeteranganAcuanP(obj[7] != null ? obj[7].toString() : null);
                detail.setSatuan(obj[8] != null ? obj[8].toString() : null);
                detail.setIdKategoriLab(obj[9] != null ? obj[9].toString() : null);
                detail.setNamaKategoriLab(obj[10] != null ? obj[10].toString() : null);
                detail.setBranchId(obj[11] != null ? obj[11].toString() : null);
                labDetailList.add(detail);
            }
        }
        return labDetailList;
    }

    public String kategoriLab(String idLab, String branch){
        String res = "";
        if(idLab != null && branch != null){
            String SQL = "SELECT\n" +
                    "CAST('id_kategori' AS VARCHAR) as kategori,\n" +
                    "d.id_kategori_lab\n" +
                    "FROM im_simrs_lab b\n" +
                    "INNER JOIN im_simrs_lab_detail c ON b.id_lab = c.id_lab\n" +
                    "INNER JOIN im_simrs_parameter_pemeriksaan d ON c.id_parameter_pemeriksaan = d.id_parameter_pemeriksaan\n" +
                    "WHERE b.id_lab = :id \n" +
                    "AND c.branch_id = :branch \n" +
                    "GROUP BY d.id_kategori_lab";
            List<Object[]> resut = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idLab)
                    .setParameter("branch", branch)
                    .list();
            if(resut.size() > 0){
                Object[] obj = resut.get(0);
                if(obj[1] != null){
                    res = obj[1].toString();
                }
            }
        }
        return res;
    }

    public List<ImSimrsLabDetailEntity> cekDataLabDetail(String idParameter, String idLab) throws HibernateException {
        List<ImSimrsLabDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsLabDetailEntity.class)
                .add(Restrictions.eq("idLab", idLab))
                .add(Restrictions.eq("idParameterPemeriksaan", idParameter))
                .add(Restrictions.eq("branchId", CommonUtil.userBranchLogin()))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}