package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class HeaderCheckupDao extends GenericDao<ItSimrsHeaderChekupEntity, String> {
    @Override
    protected Class<ItSimrsHeaderChekupEntity> getEntityClass() {
        return ItSimrsHeaderChekupEntity.class;
    }

    @Override
    public List<ItSimrsHeaderChekupEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderChekupEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("no_checkup") != null)
                criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
            if (mapCriteria.get("id_pasien") != null)
                criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
            if (mapCriteria.get("branch_id") != null)
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("id_branch").toString()));
            if (mapCriteria.get("desa_id") != null)
                criteria.add(Restrictions.eq("desaId", mapCriteria.get("desa_id").toString()));
            if (mapCriteria.get("no_ktp") != null)
                criteria.add(Restrictions.eq("noKtp", mapCriteria.get("no_ktp").toString()));
            if (mapCriteria.get("list_no_checkup") != null)
                criteria.add(Restrictions.in("noCheckup", (List<String>) mapCriteria.get("list_no_checkup")));

        List<ItSimrsHeaderChekupEntity> result = criteria.list();
        return result;
    }

    public HeaderDetailCheckup getLastPoliAndStatus(String noCheckup){

        if (noCheckup != null && !"".equalsIgnoreCase(noCheckup)){
            String SQL = "SELECT \n" +
                    "detail.no_checkup,\n" +
                    "detail.id_pelayanan,\n" +
                    "detail.status_periksa,\n" +
                    "status.keterangan as status_name\n" +
                    "FROM \n" +
                    "it_simrs_header_detail_checkup detail\n" +
                    "INNER JOIN im_simrs_status_pasien status ON status.id_status_pasien = detail.status_periksa\n" +
                    "WHERE (detail.no_checkup, detail.created_date) = \n" +
                    "(\n" +
                    "\tSELECT\n" +
                    "\td.no_checkup,\n" +
                    "\tmax(d.created_date) as created_date\n" +
                    "\tFROM it_simrs_header_detail_checkup d\n" +
                    "\tWHERE d.no_checkup = :noCheckup \n" +
                    "\tGROUP BY d.no_checkup\n" +
                    ")";

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("noCheckup", noCheckup)
                    .list();

            if (!result.isEmpty()){
                Object[] obj = result.get(0);
                HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                headerDetailCheckup.setNoCheckup(obj[0].toString());
                headerDetailCheckup.setIdPelayanan(obj[1].toString());
                headerDetailCheckup.setStatusPeriksa(obj[2].toString());
                headerDetailCheckup.setStatusPeriksaName(obj[3].toString());
                return headerDetailCheckup;
            }
        }
        return new HeaderDetailCheckup();
    }

    public List<String> getListNoCheckupByCriteria(Map mapCriteria, Boolean isPoli, Boolean isStatus){

        String idPasien = "%";
        String noKtp = "%";
        String nama = "%";
        String branchId = "%";
        String idPelayanan = "%";
        String statusPeriksa = "%";

        if(mapCriteria.get("id_pasien") != null){
            idPasien = mapCriteria.get("id_pasien").toString();
        }
        if(mapCriteria.get("no_ktp") != null){
            noKtp = mapCriteria.get("no_ktp").toString();
        }
        if(mapCriteria.get("nama") != null){
            nama = "%"+ mapCriteria.get("nama").toString()+"%";
        }
        if(mapCriteria.get("branch_id") != null){
            branchId = mapCriteria.get("branch_id").toString();
        }
        if(mapCriteria.get("id_pelayanan") != null){
            idPelayanan = mapCriteria.get("id_pelayanan").toString();
        }
        if(mapCriteria.get("status_periksa") != null) {
            statusPeriksa = mapCriteria.get("status_periksa").toString();
        }

        String SQL = "SELECT\n" +
                "detail.no_checkup,\n" +
                "h.branch_id\n" +
                "FROM \n" +
                "it_simrs_header_detail_checkup detail\n" +
                "INNER JOIN it_simrs_header_checkup h ON h.no_checkup = detail.no_checkup\n" +
                "WHERE h.id_pasien LIKE :idPasien\n" +
                "AND h.no_ktp LIKE :noKtp\n" +
                "AND h.nama LIKE :nama\n" +
                "AND h.branch_id = :branchId\n" +
                "AND detail.id_pelayanan LIKE :idPelayanan\n" +
                "AND detail.status_periksa LIKE :statusPeriksa\n" +
                "AND detail.flag = 'Y'\n" +
                "GROUP BY detail.no_checkup, h.branch_id";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPasien", idPasien)
                .setParameter("noKtp", noKtp)
                .setParameter("nama", nama)
                .setParameter("branchId", branchId)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("statusPeriksa", statusPeriksa)
                .list();

        List<String> listOfResult = new ArrayList<>();

        if (!result.isEmpty()){
            for (Object[] obj : result){
                listOfResult.add(obj[0].toString());
            }
        }

        return listOfResult;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_header_checkup')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
