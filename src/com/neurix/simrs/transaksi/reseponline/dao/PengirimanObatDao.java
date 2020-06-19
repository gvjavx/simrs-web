package com.neurix.simrs.transaksi.reseponline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsPengirimanObatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 18/06/20.
 */
public class PengirimanObatDao extends GenericDao<ItSimrsPengirimanObatEntity, String> {

    @Override
    protected Class<ItSimrsPengirimanObatEntity> getEntityClass() {
        return ItSimrsPengirimanObatEntity.class;
    }

    @Override
    public List<ItSimrsPengirimanObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPengirimanObatEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_pasien") != null)
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        if (mapCriteria.get("id_pelayanan") != null)
            criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
        if (mapCriteria.get("id_kurir") != null)
            criteria.add(Restrictions.eq("idKurir", mapCriteria.get("id_kurir").toString()));
        if (mapCriteria.get("id_resep") != null)
            criteria.add(Restrictions.eq("idResep", mapCriteria.get("id_resep").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengiriman_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<PermintaanResep> getListObatTelemedicApproved(String branchId){

        String SQL = "SELECT\n" +
                "    mspr.id_permintaan_resep,\n" +
                "    ishdc.id_detail_checkup,\n" +
                "    isp.nama,\n" +
                "    msato.id_approval_obat,\n" +
                "    ishdc.id_jenis_periksa_pasien,\n" +
                "    mspr.flag,\n" +
                "    mspr.id_transaksi_online\n" +
                "FROM mt_simrs_approval_transaksi_obat msato\n" +
                "INNER JOIN (SELECT * FROM mt_simrs_permintaan_resep WHERE id_transaksi_online is NOT NULL ) mspr ON mspr.id_approval_obat = msato.id_approval_obat\n" +
                "INNER JOIN it_simrs_header_detail_checkup ishdc ON ishdc.id_detail_checkup = mspr.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup ishc ON ishc.no_checkup = ishdc.no_checkup\n" +
                "INNER JOIN im_simrs_pasien isp ON isp.id_pasien = ishc.id_pasien\n" +
                "LEFT JOIN it_simrs_pengiriman_obat ispo ON ispo.id_resep = mspr.id_permintaan_resep\n" +
                "WHERE msato.approval_flag = 'Y'\n" +
                "AND mspr.branch_id = :branchId \n" +
                "AND ispo.id_resep is NULL";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .list();

        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        if (results.size() > 0)
        {
            PermintaanResep permintaanResep;
            for (Object[] obj : results)
            {
                permintaanResep = new PermintaanResep();
                permintaanResep.setIdPermintaanResep(obj[0].toString());
                permintaanResep.setIdDetailCheckup(obj[1].toString());
                permintaanResep.setNamaPasien(obj[2].toString());
                permintaanResep.setIdApprovalObat(obj[3].toString());;
                permintaanResep.setIdJenisPeriksa(obj[4].toString());
                permintaanResep.setFlag(obj[5].toString());
                permintaanResep.setKetJenisAntrian(obj[6] == null ? "Resep RS" : "Telemedic");
                permintaanResepList.add(permintaanResep);
            }
        }
        return permintaanResepList;
    }
}
