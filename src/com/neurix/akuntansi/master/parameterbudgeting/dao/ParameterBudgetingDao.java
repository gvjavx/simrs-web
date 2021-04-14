package com.neurix.akuntansi.master.parameterbudgeting.dao;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
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

    public boolean foundParameterBudgetingByCriteria(String idKategoriBudgeting, String divisi, String master, String rekeningId){

        String where = "";
        if ("INV".equalsIgnoreCase(master)){
            where = "WHERE (id_kategori_budgeting, master_id, rekening_id ) = ('"+idKategoriBudgeting+"',  '"+master+"', '"+rekeningId+"')";
        } else if ("BYA".equalsIgnoreCase(master)){
            where = "WHERE (id_kategori_budgeting, divisi_id, master_id, rekening_id ) = ('"+idKategoriBudgeting+"', '"+divisi+"', '"+master+"', '"+rekeningId+"')";
        } else {
            where = "WHERE (id_kategori_budgeting, divisi_id, master_id, rekening_id ) = ('"+idKategoriBudgeting+"', '"+divisi+"', '"+master+"', '"+rekeningId+"')";
        }

        String SQL = "SELECT id, id_kategori_budgeting, divisi_id, master_id, id_param_rekening \n" +
                "FROM im_akun_parameter_budgeting \n" + where;

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
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
        if (bean.getIdJenisBudgeting() != null && !"".equalsIgnoreCase(bean.getIdJenisBudgeting()))
            andWhere +=  "AND pb.id_jenis_budgeting = '"+bean.getIdJenisBudgeting()+"'\n";
        if (bean.getFlag() == null || "".equalsIgnoreCase(bean.getFlag()))
            bean.setFlag("%");

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
                "pbr.nama as nama_param_rekening,\n" +
                "kr.rekening_id as rekening_id,\n" +
                "kr.nama_kode_rekening as nama_kode_rekening\n" +
                "FROM im_akun_parameter_budgeting pb\n" +
                "INNER JOIN im_akun_kategori_parameter_budgeting kb ON kb.id = pb.id_kategori_budgeting\n" +
                "INNER JOIN im_akun_jenis_budgeting jb ON jb.id = kb.id_jenis_budgeting\n" +
                "LEFT JOIN im_position p ON p.position_id = pb.position_id\n" +
                "LEFT JOIN im_akun_master m ON m.nomor_master = pb.master_id\n" +
                "LEFT JOIN im_akun_parameter_budgeting_rekening pbr ON pbr.id = pb.id_param_rekening\n" +
                "LEFT JOIN im_akun_kode_rekening kr ON kr.rekening_id = pb.rekening_id\n" +
                "WHERE pb.flag LIKE :flag \n" +
                andWhere;

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("flag", bean.getFlag())
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
                parameterBudgeting.setRekeningId(objToString(obj[11]));
                parameterBudgeting.setNamaKodeRekening(objToString(obj[12]));
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

    public List<KodeRekening> getListKodeRekeningByTipeKodeRekening(String tipeKodeRekening){

        String filterKodeRekening = "";
        if (tipeKodeRekening != null && !"".equalsIgnoreCase(tipeKodeRekening))
            filterKodeRekening = "AND tipe_coa = '"+tipeKodeRekening+"' \n";

        String SQL = "SELECT rekening_id, kode_rekening, nama_kode_rekening, level \n" +
                "FROM im_akun_kode_rekening \n" +
                "WHERE flag = 'Y' \n" + filterKodeRekening +
                "ORDER BY kode_rekening;";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<KodeRekening> kodeRekenings = new ArrayList<>();
        if (list.size() > 0){

            for (Object[] obj : list){
                KodeRekening kodeRekening = new KodeRekening();
                kodeRekening.setRekeningId(obj[0].toString());
                kodeRekening.setKodeRekening(obj[1].toString());
                kodeRekening.setNamaKodeRekening(obj[2].toString());
                kodeRekening.setbLevel((BigInteger) obj[3]);
                kodeRekenings.add(kodeRekening);
            }

        }

        return kodeRekenings;
    }

    public String getTipeKodeRekeningById(String id){

        String SQL = "SELECT tipe_rekening_id FROM im_akun_jenis_budgeting \n" +
                "WHERE id = '"+id+"'";

        List<Object> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        if (list.size() > 0)
            return list.get(0).toString();
        return null;
    }

    public String getKoderingPositionByPositionId(String id){

        String SQL = "SELECT kodering FROM im_position\n" +
                "WHERE position_id = '"+id+"'";

        List<Object> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0)
            return list.get(0).toString();
        return null;
    }
}
