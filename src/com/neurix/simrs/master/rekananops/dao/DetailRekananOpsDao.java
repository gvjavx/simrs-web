package com.neurix.simrs.master.rekananops.dao;

import com.neurix.common.dao.GenericDao;

import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.ImSimrsDetailRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.tindakan.model.Tindakan;
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

public class DetailRekananOpsDao extends GenericDao<ImSimrsDetailRekananOpsEntity, String> {

    @Override
    protected Class<ImSimrsDetailRekananOpsEntity> getEntityClass() {
        return ImSimrsDetailRekananOpsEntity.class;
    }

    @Override
    public List<ImSimrsDetailRekananOpsEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDetailRekananOpsEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_detail_rekanan_ops") != null) {
                criteria.add(Restrictions.eq("idDetailRekananOps", mapCriteria.get("id_detail_rekanan_ops").toString()));
            }

            if (mapCriteria.get("id_rekanan_ops") != null) {
                criteria.add(Restrictions.eq("idRekananOps", mapCriteria.get("id_rekanan_ops").toString()));
            }

            if (mapCriteria.get("is_bpjs") != null) {
                criteria.add(Restrictions.eq("isBpjs", mapCriteria.get("is_bpjs").toString()));
            }

            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }

            if (mapCriteria.get("flag_parent") != null) {
                criteria.add(Restrictions.eq("flagParent", mapCriteria.get("flag_parent")));
            }
        }
        criteria.addOrder(Order.desc("createdDate"));
        List<ImSimrsDetailRekananOpsEntity> results = criteria.list();
        return results;
    }
    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_rekanan_ops')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "DRK"+sId;
    }

    public List<ImSimrsDetailRekananOpsEntity> getDetailRekananOps(String idRekananOps, String branchId ) throws HibernateException {
        List<ImSimrsDetailRekananOpsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDetailRekananOpsEntity.class)
                .add(Restrictions.eq("idRekananOps", idRekananOps))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)

        return results;
    }

    // Sigit 2021-03-10
    // mengambil data detail rekanan ops yg ber status parent saja dengan id
    public List<DetailRekananOps> getParentRekananOpsById(String id){

        String SQL = "SELECT \n" +
                "dro.id_detail_rekanan_ops, \n" +
                "ro.nama_rekanan,\n" +
                "dro.branch_id\n" +
                "FROM im_simrs_detail_rekanan_ops dro\n" +
                "INNER JOIN im_simrs_rekanan_ops ro ON ro.id_rekanan_ops = dro.id_rekanan_ops\n" +
                "WHERE dro.flag_parent = 'Y'\n" +
                "AND dro.flag = 'Y'\n" +
                "AND dro.id_detail_rekanan_ops = '"+id+"'";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<DetailRekananOps> detailRekananOps = new ArrayList<>();

        if (result.size() > 0){

            for (Object[] obj : result){
                DetailRekananOps detail = new DetailRekananOps();
                detail.setIdDetailRekananOps(obj[0].toString());
                detail.setNamaRekanan(obj[1].toString());
                detail.setBranchId(obj[2].toString());
                detailRekananOps.add(detail);

            }
        }

        return detailRekananOps;
    }

    // Sigit 2021-03-10
    // mengambil data detail rekanan ops yang berstatus selain parent Berdasarkan Parent id
    // yang dimana ditail nya ada tarif per tindakannya
    public List<DetailRekananOps> getListDetailRekananOpsByIdParent(String idParent){

        String SQL = "SELECT  \n" +
                "dro.id_detail_rekanan_ops,\n" +
                "dro.id_item,\n" +
                "ht.nama_tindakan, \n" +
                "dro.tarif,\n" +
                "dro.parent_id,\n" +
                "dro.flag,\n" +
                "pel.id_pelayanan,\n" +
                "headpel.nama_pelayanan,\n" +
                "tin.tarif as tarif_normal_non_bpjs,\n" +
                "tin.tarif_bpjs as tarif_normal_bpjs,\n" +
                "dro.tarif_bpjs as tarif_bpjs,\n" +
                "dro.diskon_non_bpjs,\n" +
                "dro.diskon_bpjs,\n" +
                "dro.branch_id \n" +
                "FROM (SELECT * FROM im_simrs_detail_rekanan_ops WHERE flag = 'Y' AND flag_parent != 'Y') dro\n" +
                "INNER JOIN (SELECT * FROM im_simrs_tindakan WHERE flag = 'Y') t ON t.id_tindakan = dro.id_item\n" +
                "INNER JOIN (SELECT * FROM  im_simrs_header_tindakan WHERE flag = 'Y') ht ON ht.id_header_tindakan = t.id_header_tindakan\n" +
                "INNER JOIN (SELECT * FROM im_simrs_tindakan WHERE flag = 'Y') tin ON tin.id_tindakan = dro.id_item AND tin.branch_id = dro.branch_id\n" +
                "INNER JOIN (SELECT * FROM im_simrs_pelayanan WHERE flag = 'Y') pel ON pel.id_pelayanan = tin.id_pelayanan \n" +
                "INNER JOIN (SELECT * FROM im_simrs_header_pelayanan WHERE flag = 'Y') headpel ON headpel.id_header_pelayanan = pel.id_pelayanan\n" +
                "WHERE parent_id = '"+idParent+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<DetailRekananOps> detailRekananOpsList = new ArrayList<>();

        if (list.size() > 0){
            for (Object[] obj : list){
                DetailRekananOps detail = new DetailRekananOps();
                detail.setIdDetailRekananOps(obj[0].toString());
                detail.setIdItem(obj[1].toString());
                detail.setNamaTindakan(obj[2].toString());
                detail.setTarif(nullEscapeBigDecimal(obj[3]));
                detail.setParentId(obj[4].toString());
                detail.setFlag(obj[5].toString());
                detail.setIdPelayanan(obj[6] == null ? "" : obj[6].toString());
                detail.setNamaPelayanan(obj[7] == null ? "" : obj[7].toString());
                detail.setTarifNormalNonBpjs(nullEscapeBigDecimal(obj[8]));
                detail.setTarifNormalBpjs(nullEscapeBigDecimal(obj[9]));
                detail.setTarifBpjs(nullEscapeBigDecimal(obj[10]));
                detail.setDiskonNonBpjs(nullEscapeBigDecimal(obj[11]));
                detail.setDiskonBpjs(nullEscapeBigDecimal(obj[12]));
                detail.setBranchId(obj[13].toString());
                detailRekananOpsList.add(detail);
            }
        }

        return detailRekananOpsList;
    }

    private BigDecimal nullEscapeBigDecimal(Object obj){
        if (obj == null)
            return new BigDecimal(0);
        else
            return new BigDecimal(obj.toString());
    }

    public Pelayanan getPelayananByIdItem(String idItem){

        String SQL = "\n" +
                "SELECT pel.id_pelayanan, headpel.nama_pelayanan\n" +
                "FROM (SELECT id_tindakan, id_pelayanan FROM im_simrs_tindakan WHERE flag = 'Y') tin\n" +
                "INNER JOIN (SELECT id_pelayanan, id_header_pelayanan FROM im_simrs_pelayanan) pel ON pel.id_pelayanan = tin.id_pelayanan\n" +
                "INNER JOIN (SELECT id_header_pelayanan, nama_pelayanan FROM im_simrs_header_pelayanan) headpel ON headpel.id_header_pelayanan = pel.id_header_pelayanan\n" +
                "WHERE tin.id_tindakan = '"+idItem+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            Object[] obj = list.get(0);
            Pelayanan pelayanan = new Pelayanan();
            pelayanan.setIdPelayanan(obj[0].toString());
            pelayanan.setNamaPelayanan(obj[1].toString());
            return pelayanan;
        }

        return null;
    }

    public List<Pelayanan> getListPelayananByBranchId(String branchId){

        String SQL = "SELECT pel.id_pelayanan, headpel.nama_pelayanan\n" +
                "FROM (SELECT id_pelayanan, id_header_pelayanan, branch_id FROM im_simrs_pelayanan) pel \n" +
                "INNER JOIN (SELECT id_header_pelayanan, nama_pelayanan FROM im_simrs_header_pelayanan) headpel ON headpel.id_header_pelayanan = pel.id_header_pelayanan\n" +
                "WHERE pel.branch_id = '"+branchId+"'\n";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<Pelayanan> pelayanans = new ArrayList<>();
        if (list.size() > 0){
            for (Object[] obj : list){
                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(obj[0].toString());
                pelayanan.setNamaPelayanan(obj[1].toString());
                pelayanans.add(pelayanan);
            }
        }
        return pelayanans;
    }

    public List<Tindakan> getListTindakanByIdPelayanan(String idPelayanan){


        String SQL = "SELECT \n" +
                "tin.id_tindakan, \n" +
                "headtin.nama_tindakan,\n" +
                "tin.tarif,\n" +
                "tin.tarif_bpjs\n" +
                "FROM (SELECT id_tindakan, id_pelayanan, tarif, tarif_bpjs, id_header_tindakan FROM im_simrs_tindakan WHERE flag = 'Y') tin\n" +
                "INNER JOIN (SELECT id_header_tindakan, nama_tindakan FROM im_simrs_header_tindakan WHERE flag = 'Y') headtin ON headtin.id_header_tindakan = tin.id_header_tindakan\n" +
                "WHERE tin.id_pelayanan = '"+idPelayanan+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<Tindakan> tindakans = new ArrayList<>();
        if (list.size() > 0){
            for (Object[] obj : list){
                Tindakan tindakan = new Tindakan();
                tindakan.setIdTindakan(obj[0].toString());
                tindakan.setNamaTindakan(obj[1].toString());
                tindakan.setbTarifNormal(nullEscapeBigDecimal(obj[2]));
                tindakan.setbTarifBpjs(nullEscapeBigDecimal(obj[3]));
                tindakans.add(tindakan);
            }
        }
        return tindakans;
    }

    public Tindakan getTindakanById(String idTindakan){


        String SQL = "SELECT \n" +
                "tin.id_tindakan, \n" +
                "headtin.nama_tindakan,\n" +
                "tin.tarif,\n" +
                "tin.tarif_bpjs\n" +
                "FROM (SELECT id_tindakan, id_pelayanan, tarif, tarif_bpjs, id_header_tindakan FROM im_simrs_tindakan WHERE flag = 'Y') tin\n" +
                "INNER JOIN (SELECT id_header_tindakan, nama_tindakan FROM im_simrs_header_tindakan WHERE flag = 'Y') headtin ON headtin.id_header_tindakan = tin.id_header_tindakan\n" +
                "WHERE tin.id_tindakan = '"+idTindakan+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            for (Object[] obj : list){
                Tindakan tindakan = new Tindakan();
                tindakan.setIdTindakan(obj[0].toString());
                tindakan.setNamaTindakan(obj[1].toString());
                tindakan.setbTarifNormal(nullEscapeBigDecimal(obj[2]));
                tindakan.setbTarifBpjs(nullEscapeBigDecimal(obj[3]));
                return tindakan;
            }
        }
        return null;
    }
}
