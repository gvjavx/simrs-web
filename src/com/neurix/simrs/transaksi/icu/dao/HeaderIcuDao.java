package com.neurix.simrs.transaksi.icu.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.icu.model.HeaderIcu;
import com.neurix.simrs.transaksi.icu.model.ItSimrsHeaderIcuEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HeaderIcuDao extends GenericDao<ItSimrsHeaderIcuEntity, String> {

    @Override
    protected Class<ItSimrsHeaderIcuEntity> getEntityClass() {
        return ItSimrsHeaderIcuEntity.class;
    }

    @Override
    public List<ItSimrsHeaderIcuEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderIcuEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_header_icu")!=null) {
                criteria.add(Restrictions.eq("idHeaderIcu", (String) mapCriteria.get("id_header_icu")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
            if (mapCriteria.get("kategori")!=null) {
                criteria.add(Restrictions.eq("kategori", (String) mapCriteria.get("kategori")));
            }

        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idHeaderIcu"));

        List<ItSimrsHeaderIcuEntity> results = criteria.list();
        return results;
    }

    public List<HeaderIcu> getListDetail(String id, String kategori){
        List<HeaderIcu> list = new ArrayList<>();
        String kat = "";
        if(kategori != null && !"".equalsIgnoreCase(kategori)){
            kat = "AND a.kategori = '"+kategori+"' \n";
        }
        if(!"".equalsIgnoreCase(id)){
            String SQL = "SELECT\n" +
                    "a.id_header_icu,\n" +
                    "b.id_detail_icu,\n" +
                    "a.jenis,\n" +
                    "a.kategori,\n" +
                    "b.nilai,\n" +
                    "b.waktu,\n" +
                    "b.created_date\n" +
                    "FROM it_simrs_header_icu a\n" +
                    "INNER JOIN it_simrs_detail_icu b\n" +
                    "ON a.id_header_icu = b.id_header_icu\n" +
                    "WHERE a.id_detail_checkup = :id\n" + kat +
                    "AND b.flag = 'Y' \n"+
                    "ORDER BY CAST(b.created_date AS date) ASC, b.waktu ASC, a.id_header_icu ASC";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .list();
            if(result.size() > 0){
                for (Object[] obj: result){
                    HeaderIcu headerIcu = new HeaderIcu();
                    headerIcu.setIdHeaderIcu(obj[0] == null ? "" : obj[0].toString());
                    headerIcu.setIdDetailIcu(obj[1] == null ? "" : obj[1].toString());
                    headerIcu.setJenis(obj[2] == null ? "" : obj[2].toString());
                    headerIcu.setKategori(obj[3] == null ? "" : obj[3].toString());
                    headerIcu.setNilai(obj[4] == null ? "" : obj[4].toString());
                    headerIcu.setWaktu(obj[5] == null ? "" : obj[5].toString());
                    headerIcu.setCreatedDate(obj[6] == null ? null : (Timestamp) obj[6]);
                    list.add(headerIcu);
                }
            }
        }
        return list;
    }

    public List<HeaderIcu> getHeadIcu(String id, String kategori, String tgl){
        List<HeaderIcu> list = new ArrayList<>();
        String consition = "";
        if(id != null && !"".equalsIgnoreCase(id)){
            consition = consition + "AND id_detail_checkup = '"+id+"' \n";
        }
        if(kategori != null && !"".equalsIgnoreCase(kategori)){
            consition = consition + "AND kategori = '"+kategori+"' \n";
        }
        if(tgl != null && !"".equalsIgnoreCase(tgl)){
            consition = consition + "AND CAST(created_date AS DATE) = '"+tgl+"' \n";
        }
        String SQL = "SELECT \n" +
                "id_header_icu, \n" +
                "jenis, \n" +
                "created_date \n" +
                "FROM it_simrs_header_icu \n" +
                "WHERE flag = 'Y' \n" + consition +" ORDER BY id_header_icu ASC";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                HeaderIcu headerIcu = new HeaderIcu();
                headerIcu.setIdHeaderIcu(obj[0] == null ? "" : obj[0].toString());
                headerIcu.setJenis(obj[1] == null ? "" : obj[1].toString());
                headerIcu.setCreatedDate(obj[2] == null ? null : (Timestamp) obj[2]);
                list.add(headerIcu);
            }
        }
        return list;
    }

    public String lastNilai(String id){
        String nilai = "";
        String consition = "";
        if(id != null && !"".equalsIgnoreCase(id)){
            consition = "AND id_header_icu = '"+id+"' \n";
        }
        String SQL = "SELECT \n" +
                "id_header_icu, \n" +
                "nilai, \n" +
                "created_date \n" +
                "FROM it_simrs_detail_icu\n" +
                "WHERE flag = 'Y'\n" + consition +
                "ORDER BY created_date DESC\n" +
                "LIMIT 1\n";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if(result.size() > 0){
            Object[] obj = result.get(0);
            if(obj[1] != null){
                String str = obj[1].toString();
                String[] rplStr = str.split("#");
                if(rplStr[1] != null && !"".equalsIgnoreCase(rplStr[1])){
                    nilai = rplStr[1];
                }
            }

        }
        return nilai;
    }

    public Boolean cekData(String id, String waktu, String kategori){
        Boolean response = false;
        if(!"".equalsIgnoreCase(id) && !"".equalsIgnoreCase(waktu) && !"".equalsIgnoreCase(kategori)){
            String SQL = "SELECT \n" +
                    "a.id_header_icu\n" +
                    "FROM it_simrs_header_icu a\n" +
                    "INNER JOIN it_simrs_detail_icu b\n" +
                    "ON a.id_header_icu = b.id_header_icu\n" +
                    "WHERE a.id_detail_checkup = :id\n" +
                    "AND b.waktu = :waktu\n" +
                    "AND b.flag = 'Y'\n" +
                    "AND a.kategori = :kategori\n" +
                    "AND CAST(b.created_date AS DATE) = CURRENT_DATE";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .setParameter("waktu", waktu)
                    .setParameter("kategori", kategori)
                    .list();
            if(result.size() > 0){
                response = true;
            }
        }
        return response;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_header_icu')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}