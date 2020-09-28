package com.neurix.simrs.master.pasien.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.access.method.P;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class PasienDao extends GenericDao<ImSimrsPasienEntity, String> {

    @Override
    protected Class<ImSimrsPasienEntity> getEntityClass() {
        return ImSimrsPasienEntity.class;
    }

    @Override
    public List<ImSimrsPasienEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPasienEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_pasien") != null) {
                criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
            }
            if (mapCriteria.get("nama") != null) {
                criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));
            }
            if (mapCriteria.get("desa_id") != null) {
                criteria.add(Restrictions.eq("desaId", mapCriteria.get("desa_id").toString()));
            }
            if (mapCriteria.get("no_ktp") != null) {
                criteria.add(Restrictions.eq("noKtp", mapCriteria.get("no_ktp").toString()));
            }
            if (mapCriteria.get("no_bpjs") != null) {
                criteria.add(Restrictions.eq("noBpjs", mapCriteria.get("no_bpjs").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
            if (mapCriteria.get("password") != null) {
                criteria.add(Restrictions.eq("password", mapCriteria.get("password").toString()));
            }
        }

        criteria.addOrder(Order.desc("createdDate"));
        List<ImSimrsPasienEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    public List<ImSimrsPasienEntity> getListPasienByTmp(String tmp) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPasienEntity.class);
        Criterion nama= Restrictions.ilike("nama", tmp);
        Criterion idPasien = Restrictions.ilike("idPasien", tmp);
        criteria.add(Restrictions.or(nama, idPasien));
        criteria.add(Restrictions.eq("flag", "Y"));

        List<ImSimrsPasienEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    //for typeahead
    public List<ImSimrsPasienEntity> getPasienListByLike(String key) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPasienEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("nama", "%" + key + "%"),
                        Restrictions.ilike("idPasien", "%" + key + "%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idPasien"));

        List<ImSimrsPasienEntity> results = criteria.list();
        return results;
    }

    public List<ImSimrsPasienEntity> getListPasienByTmpBpjs(String tmp) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPasienEntity.class);
        criteria.add(Restrictions.ilike("noBpjs", "%" + tmp + "%"));
        criteria.add(Restrictions.eq("flag", "Y"));

        List<ImSimrsPasienEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    public List<Object[]> getListAlamat(String desaId) {

        String sql = "SELECT \n" +
                "\t\tdesa.desa_id,\n" +
                "                desa.desa_name, \n" +
                "                kecamatan.kecamatan_name, \n" +
                "                kota.kota_name, \n" +
                "                provinsi.provinsi_name, \n" +
                "                kecamatan.kecamatan_id, \n" +
                "                kota.kota_id, \n" +
                "                provinsi.provinsi_id \n" +
                "                FROM \n" +
                "                (SELECT * FROM im_hris_desa) desa INNER JOIN  \n" +
                "                (SELECT * FROM im_hris_kecamatan) kecamatan ON desa.kecamatan_id = kecamatan.kecamatan_id INNER JOIN \n" +
                "                (SELECT * FROM im_hris_kota) kota ON kecamatan.kota_id = kota.kota_id INNER JOIN \n" +
                "                (SELECT * FROM im_hris_provinsi) provinsi ON kota.provinsi_id = provinsi.provinsi_id \n" +
                "                WHERE desa.desa_id = :desaId";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setParameter("desaId", desaId)
                .list();

        return result;
    }

    public List<Pasien> getListPasienWithPaket(String nama) {

        List<Pasien> pasienList = new ArrayList<>();

        String search = "%"+nama+"%";

        String SQL = "SELECT \n" +
                "a.id_pasien,\n" +
                "a.nama,\n" +
                "a.jenis_kelamin,\n" +
                "a.no_ktp,\n" +
                "a.no_bpjs,\n" +
                "a.tempat_lahir,\n" +
                "a.tgl_lahir,\n" +
                "a.desa_id,\n" +
                "a.jalan,\n" +
                "a.suku,\n" +
                "a.agama,\n" +
                "a.profesi,\n" +
                "a.no_telp,\n" +
                "a.url_ktp,\n" +
                "b.id_paket,\n" +
                "d.id_pelayanan,\n" +
                "c.nama_paket,\n" +
                "c.tarif\n" +
                "FROM im_simrs_pasien a\n" +
                "INNER JOIN it_simrs_paket_pasien b ON a.id_pasien = b.id_pasien\n" +
                "INNER JOIN mt_simrs_paket c ON b.id_paket = c.id_paket\n" +
                "INNER JOIN (SELECT * FROM mt_simrs_detail_paket WHERE urutan = 1) d ON c.id_paket = d.id_paket \n"+
                "WHERE b.flag = 'Y' AND a.nama ILIKE :search OR a.id_pasien ILIKE :search";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("search", search)
                .list();

        if (result.size() > 0) {
            for (Object[] obj: result){
                Pasien pasien = new Pasien();
                pasien.setIdPasien(obj[0] == null ? "" : obj[0].toString());
                pasien.setNama(obj[1] == null ? "" : obj[1].toString());
                pasien.setJenisKelamin(obj[2] == null ? "" : obj[2].toString());
                pasien.setNoKtp(obj[3] == null ? "" : obj[3].toString());
                pasien.setNoBpjs(obj[4] == null ? "" : obj[4].toString());
                pasien.setTempatLahir(obj[5] == null ? "" : obj[5].toString());
                pasien.setTglLahir(obj[6] == null ? "" : obj[6].toString());
                pasien.setDesaId(obj[7] == null ? "" : obj[7].toString());
                pasien.setJalan(obj[8] == null ? "" : obj[8].toString());
                pasien.setSuku(obj[9] == null ? "" : obj[9].toString());
                pasien.setAgama(obj[10] == null ? "" : obj[10].toString());
                pasien.setProfesi(obj[11] == null ? "" : obj[11].toString());
                pasien.setNoTelp(obj[12] == null ? "" : obj[12].toString());
                pasien.setUrlKtp(obj[13] == null ? "" : CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_KTP_PASIEN +obj[13].toString());
                pasien.setIdPaket(obj[14] == null ? "" : obj[14].toString());
                pasien.setIdPelayanan(obj[15] == null ? "" : obj[15].toString());
                pasien.setNamaPaket(obj[16] == null ? "" : obj[16].toString());
                pasien.setTarif(obj[17] != null ? new BigDecimal(obj[17].toString()) : null);

                if(obj[0] != null && !"".equalsIgnoreCase(obj[0].toString())){
                    List<Object[]> objects = getListAlamat(obj[7].toString());
                    if(objects != null){
                        for (Object[] ob: objects){
                            pasien.setDesa(ob[1].toString());
                            pasien.setKecamatan(ob[2].toString());
                            pasien.setKota(ob[3].toString());
                            pasien.setProvinsi(ob[4].toString());
                            pasien.setKecamatanId(ob[5].toString());
                            pasien.setKotaId(ob[6].toString());
                            pasien.setProvinsiId(ob[7].toString());
                        }
                    }
                }

                pasienList.add(pasien);

            }
        }

        return pasienList;
    }

    public HeaderDetailCheckup getLastCheckup(String idPasien){
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        if(idPasien != null){
            String SQL = "SELECT\n" +
                    "b.id_detail_checkup,\n" +
                    "a.id_pasien,\n" +
                    "b.tgl_cekup,\n" +
                    "b.no_checkup_ulang,\n" +
                    "b.is_order_lab, \n" +
                    "b.id_pelayanan\n" +
                    "FROM it_simrs_header_checkup a \n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "WHERE id_pasien = :idPasien\n" +
                    "AND a.tgl_keluar IS NOT NULL\n" +
                    "ORDER BY a.created_date DESC\n" +
                    "LIMIT 1\n";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPasien", idPasien)
                    .list();

            if(result.size() > 0){
                Object[] obj = result.get(0);
                detailCheckup.setIdDetailCheckup(obj[0] != null ? obj[0].toString() : "");
                detailCheckup.setIdPasien(obj[1] != null ? obj[1].toString() : "");
                detailCheckup.setTglCekup(obj[2] != null ? Date.valueOf(obj[2].toString()) : null);
                detailCheckup.setNoCheckupUlang(obj[3] != null ? obj[3].toString() : "");
                detailCheckup.setIsOrderLab(obj[4] != null ? obj[4].toString() : "");
                detailCheckup.setIdPelayanan(obj[5] != null ? obj[5].toString() : "");
            }
        }
        return detailCheckup;
    }

    public Boolean cekPendaftaranPasien(String idPasien){
        Boolean res = false;
        if(idPasien != null && !"".equalsIgnoreCase(idPasien)){
            String SQL = "SELECT \n" +
                    "a.id_pasien,\n" +
                    "b.id_detail_checkup,\n" +
                    "b.status_periksa,\n" +
                    "b.created_date\n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "WHERE a.id_pasien = :id\n" +
                    "ORDER BY b.created_date DESC LIMIT 1";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPasien)
                    .list();
            if(result.size() > 0){
                Object[] objects = result.get(0);
                if(objects[2] != null){
                    if(!"3".equalsIgnoreCase(objects[2].toString())){
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    public String getNextIdPasien() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pasien')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%04d", iter.next());
        return sId;
    }
}
