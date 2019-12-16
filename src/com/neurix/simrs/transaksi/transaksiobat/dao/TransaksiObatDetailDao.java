package com.neurix.simrs.transaksi.transaksiobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TransaksiObatDetailDao extends GenericDao<ImtSimrsTransaksiObatDetailEntity, String> {
    @Override
    protected Class<ImtSimrsTransaksiObatDetailEntity> getEntityClass() {
        return ImtSimrsTransaksiObatDetailEntity.class;
    }

    @Override
    public List<ImtSimrsTransaksiObatDetailEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtSimrsTransaksiObatDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_transaksi_obat_detail")!=null) {
                criteria.add(Restrictions.eq("idTransaksiObatDetail", (String) mapCriteria.get("id_transaksi_obat_detail")));
            }
            if (mapCriteria.get("id_approval_obat")!=null) {
                criteria.add(Restrictions.ilike("idApprovalObat", (String)mapCriteria.get("id_approval_obat")));
            }
            if (mapCriteria.get("id_obat")!=null) {
                criteria.add(Restrictions.eq("idObat", (String) mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("qty")!=null) {
                criteria.add(Restrictions.eq("qty", mapCriteria.get("qty")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idTransaksiObatDetail"));

        List<ImtSimrsTransaksiObatDetailEntity> results = criteria.list();

        return results;
    }

    public List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransObatDetails(TransaksiObatDetail bean){

        String idTransaksi          = "%";
        String idApprovalObat       = "%";
        String idPermintaanResep    = "%";
        String flag                 = "%";
        String branchId             = "%";

        if (bean.getIdTransaksiObatDetail() != null && !"".equalsIgnoreCase(bean.getIdTransaksiObatDetail())){
            idTransaksi = bean.getIdTransaksiObatDetail();
        }

        if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())){
            idApprovalObat = bean.getIdApprovalObat();
        }

        if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())){
            idPermintaanResep = bean.getIdPermintaanResep();
        }

        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            flag = bean.getFlag();
        }

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }

        String SQL = "\n" +
                "SELECT \n" +
                "tod.id_transaksi_obat_detail,\n" +
                "tod.id_approval_obat,\n" +
                "tod.id_obat,\n" +
                "tod.qty,\n" +
                "tod.flag,\n" +
                "tod.action,\n" +
                "tod.created_date,\n" +
                "tod.created_who,\n" +
                "tod.last_update,\n" +
                "tod.last_update_who\n" +
                "FROM mt_simrs_transaksi_obat_detail tod\n" +
                "INNER JOIN \n" +
                "(\n" +
                "\tSELECT \n" +
                "\tid_approval_obat,\n" +
                "\tbranch_id \n" +
                "\tFROM mt_simrs_approval_transaksi_obat \n" +
                "\tWHERE tipe_permintaan = '001' \n" +
                "\tAND flag = 'Y'\n" +
                ") ato ON ato.id_approval_obat = tod.id_approval_obat\n" +
                "INNER JOIN mt_simrs_permintaan_resep pr ON pr.id_approval_obat = ato.id_approval_obat\n" +
                "WHERE tod.flag LIKE :flag \n" +
                "AND ato.branch_id LIKE :branchId \n" +
                "AND tod.id_approval_obat LIKE :idApprovalObat \n" +
                "AND tod.id_transaksi_obat_detail LIKE :idTransaksi \n" +
                "AND pr.id_permintaan_resep LIKE :idPermintaanResep ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idTransaksi", idTransaksi)
                .setParameter("idApprovalObat", idApprovalObat)
                .setParameter("idPermintaanResep", idPermintaanResep)
                .setParameter("flag", flag)
                .setParameter("branchId", branchId)
                .list();

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();

        if (results.size() > 0)
        {
            ImtSimrsTransaksiObatDetailEntity obatDetailEntity;
            for (Object[] obj : results)
            {
                obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                obatDetailEntity.setIdTransaksiObatDetail(obj[0].toString());
                obatDetailEntity.setIdApprovalObat(obj[1].toString());
                obatDetailEntity.setIdObat(obj[2].toString());
                obatDetailEntity.setQty((BigInteger) obj[3]);
                obatDetailEntity.setFlag(obj[4].toString());
                obatDetailEntity.setAction(obj[5].toString());
                obatDetailEntity.setCreatedDate((Timestamp) obj[6]);
                obatDetailEntity.setCreatedWho(obj[7].toString());
                obatDetailEntity.setLastUpdate((Timestamp)obj[8]);
                obatDetailEntity.setLastUpdateWho(obj[9].toString());
                obatDetailEntities.add(obatDetailEntity);
            }
        }

        return obatDetailEntities;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_transaksi_obat_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}