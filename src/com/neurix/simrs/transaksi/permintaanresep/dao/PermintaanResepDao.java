package com.neurix.simrs.transaksi.permintaanresep.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.ObatKronis;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
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
public class PermintaanResepDao extends GenericDao<ImSimrsPermintaanResepEntity, String> {
    @Override
    protected Class<ImSimrsPermintaanResepEntity> getEntityClass() {
        return ImSimrsPermintaanResepEntity.class;
    }

    @Override
    public List<ImSimrsPermintaanResepEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPermintaanResepEntity.class);
        if (mapCriteria.get("id_permintaan_resep") != null){
            criteria.add(Restrictions.eq("idPermintaanResep", mapCriteria.get("id_permintaan_resep").toString()));
        }
        if (mapCriteria.get("id_approval_obat") != null){
            criteria.add(Restrictions.eq("idApprovalObat", mapCriteria.get("id_approval_obat").toString()));
        }
        if (mapCriteria.get("id_pasien") != null){
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        }
        if (mapCriteria.get("id_detail_checkup") != null){
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        if (mapCriteria.get("branch_id") != null){
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<ImSimrsPermintaanResepEntity> results = criteria.list();
        return results;
    }



    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_permintaan_resep')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<ObatKronis> getLastObatKronis(String idPasien, String flagDiambil){

        String sqlDiambil = "is null";
        if ("Y".equalsIgnoreCase(flagDiambil)){
            sqlDiambil = "is not null";
        }

        String SQL = "SELECT \n" +
                "\thc.id_pasien,\n" +
                "\tdc.id_detail_checkup,\n" +
                "\tpr.id_approval_obat,\n" +
                "\thari_kronis,\n" +
                "\tMAX(dt.created_date) as created_date\n" +
                "\tFROM it_simrs_header_detail_checkup dc\n" +
                "\tINNER JOIN it_simrs_header_checkup hc ON hc.no_checkup = dc.no_checkup\n" +
                "\tINNER JOIN mt_simrs_permintaan_resep pr ON pr.id_detail_checkup = dc.id_detail_checkup\n" +
                "\tINNER JOIN mt_simrs_transaksi_obat_detail dt ON dt.id_approval_obat = pr.id_approval_obat\n" +
                "\tWHERE flag_kronis_diambil "+sqlDiambil+"\n"+
                "\tAND hari_kronis is not null\n" +
                "\tAND hc.id_pasien = :idPasien\n" +
                "\tAND dc.id_jenis_periksa_pasien = 'bpjs'\n" +
                "\tAND dc.status_periksa = '3'\n" +
                "\tGROUP BY\n" +
                "\thc.id_pasien,\n" +
                "\tdc.id_detail_checkup,\n" +
                "\tpr.id_approval_obat,\n" +
                "\thari_kronis LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPasien", idPasien)
                .list();

        List<ObatKronis> obatKronisList = new ArrayList<>();
        ObatKronis obatDetail;
        if (results.size() > 0){
            for (Object[] obj : results){
                obatDetail = new ObatKronis();
                obatDetail.setIdPasien((String) obj[0]);
                obatDetail.setIdDetailCheckup((String) obj[1]);
                obatDetail.setIdApprovalObat((String) obj[2]);
                obatDetail.setIntervalHariKronis((Integer) obj[3]);
                obatDetail.setCreatedDate((Timestamp) obj[4]);
                obatKronisList.add(obatDetail);
            }
        }
        return obatKronisList;
    }

    public List<PermintaanResep> getListResepPasien(String noCheckup, String jenis){
        List<PermintaanResep> rawatList = new ArrayList<>();
        String SQL = "SELECT \n" +
                "a.id_permintaan_resep,\n" +
                "a.id_approval_obat,\n" +
                "a.status,\n" +
                "a.id_detail_checkup,\n" +
                "d.id_pelayanan,\n" +
                "d.nama_pelayanan,\n" +
                "a.created_date\n" +
                "FROM mt_simrs_permintaan_resep a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                "LEFT JOIN im_simrs_pelayanan d ON b.id_pelayanan = d.id_pelayanan\n" +
                "WHERE c.no_checkup = :id AND b.id_jenis_periksa_pasien = :jen \n" +
                "ORDER BY a.id_detail_checkup ASC\n";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", noCheckup)
                .setParameter("jen", jenis)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                PermintaanResep permintaanResep = new PermintaanResep();
                permintaanResep.setIdPermintaanResep(obj[0] != null ? obj[0].toString() : "");
                permintaanResep.setIdApprovalObat(obj[1] != null ? obj[1].toString() : "");
                permintaanResep.setStatus(obj[2] != null ? obj[2].toString() : "");
                permintaanResep.setIdDetailCheckup(obj[3] != null ? obj[3].toString() : "");
                permintaanResep.setIdPelayanan(obj[4] != null ? obj[4].toString() : "");
                permintaanResep.setNamaPelayanan(obj[5] != null ? obj[5].toString() : "");
                permintaanResep.setCreatedDate(obj[6] != null ? (Timestamp) obj[6] : null);
                rawatList.add(permintaanResep);
            }
        }
        return rawatList;
    }
}
