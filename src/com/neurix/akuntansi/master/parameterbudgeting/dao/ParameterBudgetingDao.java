package com.neurix.akuntansi.master.parameterbudgeting.dao;

import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunParameterBudgetingEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ParameterBudgeting;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/08/20.
 */
public class ParameterBudgetingDao extends GenericDao<ImAkunParameterBudgetingEntity, String> {
    @Override
    protected Class<ImAkunParameterBudgetingEntity> getEntityClass() {
        return ImAkunParameterBudgetingEntity.class;
    }

    @Override
    public List<ImAkunParameterBudgetingEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImAkunParameterBudgetingEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("id_jenis_budgeting") != null){
            criteria.add(Restrictions.eq("idJenisBudgeting", mapCriteria.get("id_jenis_budgeting").toString()));
        }
        if (mapCriteria.get("id_kategori_budgeting") != null){
            criteria.add(Restrictions.eq("idKategoriBudgeting", mapCriteria.get("id_kategori_budgeting").toString()));
        }
        if (mapCriteria.get("divisi_id") != null){
            criteria.add(Restrictions.eq("divisiId", mapCriteria.get("divisi_id").toString()));
        }
        if (mapCriteria.get("master_id") != null){
            criteria.add(Restrictions.eq("masterId", mapCriteria.get("master_id").toString()));
        }
        if (mapCriteria.get("id_param_rekening") != null){
            criteria.add(Restrictions.eq("idParamRekening", mapCriteria.get("id_param_rekening").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }
        return criteria.list();
    }

    public boolean foundParameterBudgetingByCriteria(String idKategoriBudgeting, String divisi, String master, String idParamRekening){

        String SQL = "SELECT id, id_kategori_budgeting, divisi_id, master_id, id_param_rekening \n" +
                "FROM im_akun_parameter_budgeting \n" +
                "WHERE (id_kategori_budgeting, divisi_id, master_id, id_param_rekening ) = (:idkategori, :divisi, :master, :idparam)";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idkategori",idKategoriBudgeting)
                .setParameter("divisi", divisi)
                .setParameter("master", master)
                .setParameter("idparam", idParamRekening)
                .list();
        if (results != null && results.size() > 0){
            return true;
        }
        return false;
    }

    public boolean cekdataByCriteria(String id, String flag){

        String SQL = "SELECT id, flag \n" +
                "FROM im_akun_parameter_budgeting \n" +
                "WHERE (id, flag ) = (:id,:flag)";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .setParameter("flag", flag)
                .list();
        if (results != null && results.size() > 0){
            return true;
        }
        return false;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_parameter_budgeting')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%07d", iter.next());
        return "PRMR" + sId;
    }

    public List<ParameterBudgeting> getSearchParameterBudgeting(ParameterBudgeting bean){

        String andWhere = "";
        if (bean.getIdKategoriBudgeting() != null && !"".equalsIgnoreCase(bean.getIdKategoriBudgeting()))
            andWhere += "AND pb.id_kategori_budgeting = '"+bean.getIdKategoriBudgeting()+"' \n";
        if (bean.getMasterId() != null && !"".equalsIgnoreCase(bean.getMasterId()))
            andWhere += "AND pb.master_id = '"+bean.getMasterId()+"'\n";
        if (bean.getDivisiId() != null && !"".equalsIgnoreCase(bean.getDivisiId()))
            andWhere += "AND pb.divisi_id = '"+bean.getDivisiId()+"'\n";
        if (bean.getIdParamRekening() != null && !"".equalsIgnoreCase(bean.getIdParamRekening()))
            andWhere +=  "AND pb.id_param_rekening = '"+bean.getIdParamRekening()+"'\n";
        if (bean.getFlag() != null)
            andWhere += "AND pb.flag = '"+bean.getFlag()+"'\n";

        if (bean.getIdJenisBudgeting() == null)
            bean.setIdJenisBudgeting("%");

        String SQL = "SELECT\n" +
                "pb.id,\n" +
                "kb.id_jenis_budgeting,\n" +
                "jb.nama_jenis as nama_jenis_budgeting,\n" +
                "pb.id_kategori_budgeting,\n" +
                "kb.nama as nama_kategori_budgeting,\n" +
                "m.nomor_master,\n" +
                "m.nama as nama_master,\n" +
                "p.kodering as divisi_id,\n" +
                "p.position_name as divisi_name,\n" +
                "pb.id_param_rekening,\n" +
                "pbr.nama as nama_param_rekening\n" +
                "FROM im_akun_parameter_budgeting pb\n" +
                "INNER JOIN im_akun_kategori_parameter_budgeting kb ON kb.id = pb.id_kategori_budgeting\n" +
                "INNER JOIN im_akun_jenis_budgeting jb ON jb.id = kb.id_jenis_budgeting\n" +
                "LEFT JOIN im_position p ON p.kodering = pb.divisi_id\n" +
                "LEFT JOIN im_akun_master m ON m.nomor_master = pb.master_id\n" +
                "LEFT JOIN im_akun_parameter_budgeting_rekening pbr ON pbr.id = pb.id_param_rekening\n" +
                "WHERE kb.id_jenis_budgeting LIKE :idjenis \n" +
                andWhere;

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idjenis", bean.getIdJenisBudgeting())
                .list();

        List<ParameterBudgeting> parameterBudgetings = new ArrayList<>();
        if (results != null && results.size() > 0){
            for (Object[] obj : results){
                ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
                parameterBudgeting.setId(objToString(obj[0]));
                parameterBudgeting.setIdJenisBudgeting(objToString(obj[1]));
                parameterBudgeting.setNamaJenisBudgeting(objToString(obj[2]));
                parameterBudgeting.setIdKategoriBudgeting(objToString(obj[3]));
                parameterBudgeting.setNamaKategoriBudgeting(objToString(obj[4]));
                parameterBudgeting.setMasterId(objToString(obj[5]));
                parameterBudgeting.setNamaMaster(objToString(obj[6]));
                parameterBudgeting.setDivisiId(objToString(obj[7]));
                parameterBudgeting.setNamaDivisi(objToString(obj[8]));
                parameterBudgeting.setIdParamRekening(objToString(obj[9]));
                parameterBudgeting.setNamaParamRekening(objToString(obj[10]));
                parameterBudgetings.add(parameterBudgeting);
            }
        }

        return parameterBudgetings;
    }

    private String objToString(Object obj){
        if (obj != null)
            return (String) obj;
        return null;
    }
}
