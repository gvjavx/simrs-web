package com.neurix.simrs.transaksi.obatpoli.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsPermintaanObatPoliEntity;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class PermintaanObatPoliDao extends GenericDao<MtSimrsPermintaanObatPoliEntity,String>{

    @Override
    protected Class<MtSimrsPermintaanObatPoliEntity> getEntityClass() {
        return MtSimrsPermintaanObatPoliEntity.class;
    }

    @Override
    public List<MtSimrsPermintaanObatPoliEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsPermintaanObatPoliEntity.class);
        if (mapCriteria.get("id_permintaan_obat_poli") != null){
            criteria.add(Restrictions.eq("idPermintaanObatPoli", mapCriteria.get("id_permintaan_obat_poli").toString()));
        }
        if (mapCriteria.get("id_approval_obat") != null){
            criteria.add(Restrictions.eq("idApprovalObat", mapCriteria.get("id_approval_obat").toString()));
        }
        if (mapCriteria.get("id_obat") != null){
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
        }
        if (mapCriteria.get("id_pelayanan") != null){
            criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
        }
        if (mapCriteria.get("diterima_flag") != null){
            criteria.add(Restrictions.eq("diterimaFlag", mapCriteria.get("diterima_flag").toString()));
        }
        if (mapCriteria.get("reture_flag") != null){
            criteria.add(Restrictions.eq("retureFlag", mapCriteria.get("reture_flag").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<MtSimrsPermintaanObatPoliEntity> results = criteria.list();
        return results;
    }

    public List<MtSimrsPermintaanObatPoliEntity> getListPermintaanObatPoliEntity(PermintaanObatPoli bean){
        List<MtSimrsPermintaanObatPoliEntity> listOfResults = new ArrayList<>();

        String branchId = "%";
        String idPelayanan = "%";
        String idObat = "%";
        String idPermintaanObatPoli = "%";
        String idJenisObat = "%";
        String flag = "%";
        String tipePermintaan = "%";

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }
        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
            idPelayanan = bean.getIdPelayanan();
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            idObat = bean.getIdObat();
        }
        if (bean.getIdPermintaanObatPoli() != null && !"".equalsIgnoreCase(bean.getIdPermintaanObatPoli())){
            idPermintaanObatPoli = bean.getIdPermintaanObatPoli();
        }
        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())){
            idJenisObat = bean.getIdJenisObat();
        }
        if (bean.getTipePermintaan() != null && !"".equalsIgnoreCase(bean.getTipePermintaan())){
            tipePermintaan = bean.getTipePermintaan();
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            flag = bean.getFlag();
        }

        String SQL = "SELECT \n" +
                "pop.id_permintaan_obat_poli,\n" +
                "pop.id_obat,\n" +
                "pop.id_pelayanan,\n" +
                "pop.id_approval_obat,\n" +
                "pop.qty,\n" +
                "pop.flag,\n" +
                "pop.action,\n" +
                "pop.created_date,\n" +
                "pop.created_who,\n" +
                "pop.last_update,\n" +
                "pop.last_update_who \n" +
                "FROM mt_simrs_permintaan_obat_poli pop\n" +
                "INNER JOIN \n" +
                "(\n" +
                "\tSELECT pop.id_permintaan_obat_poli\n" +
                "\tFROM mt_simrs_permintaan_obat_poli pop\n" +
                "\tINNER JOIN mt_simrs_approval_transaksi_obat ato ON ato.id_approval_obat = pop.id_approval_obat\n" +
                "\tINNER JOIN im_simrs_obat_gejala og On og.id_obat = pop.id_obat\n" +
                "\tWHERE pop.flag LIKE :flag \n" +
                "\tAND ato.branch_id LIKE :branchId \n" +
                "\tAND pop.id_obat LIKE :idObat \n" +
                "\tAND ato.tipe_permintaan LIKE :tipePermintaan \n" +
                "\tAND pop.id_pelayanan LIKE :idPelayanan \n" +
                "\tAND og.id_jenis_obat LIKE :idJenisObat \n" +
                "\tAND pop.id_permintaan_obat_poli LIKE :idPermintaanObatPoli \n" +
                "\tGROUP BY pop.id_permintaan_obat_poli \n" +
                ") popo ON popo.id_permintaan_obat_poli = pop.id_permintaan_obat_poli ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("idObat", idObat)
                .setParameter("idPermintaanObatPoli", idPermintaanObatPoli)
                .setParameter("idJenisObat", idJenisObat)
                .setParameter("tipePermintaan", tipePermintaan)
                .setParameter("flag", flag)
                .list();

        if (results.size() > 0)
        {
            MtSimrsPermintaanObatPoliEntity obatPoliEntity;
            for (Object[] obj : results){
                obatPoliEntity = new MtSimrsPermintaanObatPoliEntity();
                obatPoliEntity.setIdPermintaanObatPoli(obj[0].toString());
                obatPoliEntity.setIdObat(obj[1].toString());
                obatPoliEntity.setIdPelayanan(obj[2].toString());
                obatPoliEntity.setIdApprovalObat(obj[3].toString());
                obatPoliEntity.setQty((BigInteger) obj[4]);
                obatPoliEntity.setFlag(obj[5].toString());
                obatPoliEntity.setAction(obj[6].toString());
                obatPoliEntity.setCreatedDate((Timestamp) obj[7]);
                obatPoliEntity.setCreatedWho(obj[8].toString());
                obatPoliEntity.setLastUpdate((Timestamp) obj[9]);
                obatPoliEntity.setLastUpdateWho(obj[10].toString());
                listOfResults.add(obatPoliEntity);
            }
        }


        return listOfResults;
    }

    public List<PermintaanObatPoli> getListPermintaanPoli(PermintaanObatPoli bean){

        String idPelayanan = "%";
        String idObat = "%";
        String idPermintaanObatPoli = "%";
        String idApprovalObat = "%";
        String diterimaFlag = "%";

        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
            idPelayanan = bean.getIdPelayanan();
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            idObat = bean.getIdObat();
        }
        if (bean.getIdPermintaanObatPoli() != null && !"".equalsIgnoreCase(bean.getIdPermintaanObatPoli())){
            idPermintaanObatPoli = bean.getIdPermintaanObatPoli();
        }
        if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())){
            idApprovalObat = bean.getIdApprovalObat();
        }
        if (bean.getDiterimaFlag() != null && !"".equalsIgnoreCase(bean.getDiterimaFlag())){
            diterimaFlag = bean.getDiterimaFlag();
        }

        String SQL = "\n" +
                "SELECT \n" +
                "pop.id_permintaan_obat_poli,\n" +
                "pop.id_obat,\n" +
                "pop.id_pelayanan,\n" +
                "pop.id_approval_obat,\n" +
                "pop.flag,\n" +
                "pop.action,\n" +
                "pop.created_date,\n" +
                "pop.created_who,\n" +
                "ato.last_update,\n" +
                "ato.last_update_who,\n" +
                "ato.approval_flag,\n" +
                "ato.approve_person,\n" +
                "ato.qty\n" +
                "FROM\n" +
                "mt_simrs_permintaan_obat_poli pop \n" +
                "INNER JOIN mt_simrs_approval_transaksi_obat ato ON ato.id_approval_obat = pop.id_approval_obat\n" +
                "WHERE pop.id_pelayanan LIKE :idPelayanan \n" +
                "AND pop.id_obat LIKE :idObat \n" +
                "AND pop.diterima_flag LIKE :diterimaFlag \n" +
                "AND pop.id_approval_obat LIKE :idApprovalObat \n" +
                "AND pop.id_permintaan_obat_poli LIKE :idPermintaanObatPoli ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("idObat",idObat)
                .setParameter("idPermintaanObatPoli", idPermintaanObatPoli)
                .setParameter("idApprovalObat", idApprovalObat)
                .setParameter("diterimaFlag", diterimaFlag)
                .list();

        List<PermintaanObatPoli> listOfResults = new ArrayList<>();

        PermintaanObatPoli permintaanObatPoli;
        for (Object[] obj : results){
            permintaanObatPoli = new PermintaanObatPoli();
            permintaanObatPoli.setIdPermintaanObatPoli(obj[0].toString());
            permintaanObatPoli.setIdObat(obj[1].toString());
            permintaanObatPoli.setIdPelayanan(obj[2].toString());
            permintaanObatPoli.setIdApprovalObat(obj[3].toString());
            permintaanObatPoli.setFlag(obj[4].toString());
            permintaanObatPoli.setAction(obj[5].toString());
            permintaanObatPoli.setCreatedDate((Timestamp) obj[6]);
            permintaanObatPoli.setCreatedWho(obj[7].toString());
            permintaanObatPoli.setLastUpdate((Timestamp) obj[8]);
            permintaanObatPoli.setLastUpdateWho(obj[9].toString());
            permintaanObatPoli.setApprovalFlag(obj[10].toString());
            permintaanObatPoli.setApprovePerson(obj[11].toString());
            permintaanObatPoli.setQty((BigInteger) obj[12]);
            listOfResults.add(permintaanObatPoli);
        }
        return listOfResults;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_permintaan_detail_poli')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
