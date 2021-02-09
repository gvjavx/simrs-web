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
import java.sql.Timestamp;
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
                "c.tarif,\n" +
                "a.pendidikan,\n" +
                "a.status_perkawinan\n" +
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
                pasien.setPendidikan(obj[18] != null ? obj[18].toString() : null);
                pasien.setStatusPerkawinan(obj[19] != null ? obj[19].toString() : null);

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
                    "WHERE a.id_pasien = :id AND b.status_periksa NOT IN ('3', '5')\n" +
                    "ORDER BY b.created_date DESC LIMIT 1";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPasien)
                    .list();
            if(result.size() > 0){
                res = true;
            }
        }
        return res;
    }

    public String getNextIdPasien() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pasien')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%06d", iter.next());
        return sId;
    }

    public List<Pasien> getComboRmLama(String key) {
        List<Pasien> listOfResult = new ArrayList<>();
        String SQL = "SELECT\n" +
                "id_pasien,\n" +
                "nama,\n" +
                "no_rm_lama,\n" +
                "desa_id,\n" +
                "no_bpjs\n" +
                "FROM im_simrs_pasien\n" +
                "WHERE id_pasien ILIKE '%"+key+"%'\n" +
                "OR nama ILIKE '%"+key+"%'\n" +
                "OR no_bpjs ILIKE '%"+key+"%'\n" +
                "OR no_rm_lama ILIKE '%"+key+"%' LIMIT 10";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                Pasien pasien = new Pasien();
                pasien.setIdPasien(obj[0] != null ? obj[0].toString() : "");
                pasien.setNama(obj[1] != null ? obj[1].toString() : "");
                pasien.setNoRmLama(obj[2] != null ? obj[2].toString() : "");
                pasien.setNoBpjs(obj[4] != null ? obj[4].toString() : "");
                if(obj[3] != null){
                    pasien.setDesa(desaName(obj[3].toString()));
                }
                listOfResult.add(pasien);
            }
        }
        return listOfResult;
    }

    private String desaName(String id){
        String res = "";
        if(id != null && !"".equalsIgnoreCase(id)){
            String SQL = "SELECT\n" +
                    "desa_id,\n" +
                    "desa_name\n" +
                    "FROM im_hris_desa\n" +
                    "WHERE desa_id = :id";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .list();
            if(result.size() > 0){
                Object[] obj = result.get(0);
                res = obj[1].toString();
            }
        }
        return res;
    }

    public List<ImSimrsPasienEntity> getDetailPasien(String tmp) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPasienEntity.class);
        criteria.add(Restrictions.eq("idPasien", tmp));
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImSimrsPasienEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    public List<Pasien> getSearchPasienForMaster(Pasien param) {

        String where = "";
        if (param.getIdPasien() != null && !"".equalsIgnoreCase(param.getIdPasien()))
            where += "AND ps.id_pasien = '"+param.getIdPasien()+"' \n";
        if (param.getNama() != null && !"".equalsIgnoreCase(param.getNama()))
            where += "AND ps.nama ILIKE '%"+param.getNama()+"%' \n";
        if (param.getDesaId() != null && !"".equalsIgnoreCase(param.getDesaId()))
            where += "AND ps.desa_id = '"+param.getDesaId()+"' \n";
        if (param.getNoKtp() != null && !"".equalsIgnoreCase(param.getNoKtp()))
            where += "AND ps.no_ktp = '"+param.getNoKtp()+"' \n";
        if (param.getNoBpjs() != null && !"".equalsIgnoreCase(param.getNoBpjs()))
            where += "AND ps.no_bpjs = '"+param.getNoBpjs()+"' \n";

        String SQL = "SELECT\n" +
                "ps.id_pasien,\n" +
                "ps.nama,\n" +
                "ps.jenis_kelamin,\n" +
                "ps.no_ktp,\n" +
                "ps.no_bpjs,\n" +
                "ps.tempat_lahir,\n" +
                "ps.tgl_lahir,\n" +
                "ps.desa_id,\n" +
                "ps.jalan,\n" +
                "ps.suku,\n" +
                "ps.agama,\n" +
                "ps.profesi,\n" +
                "ps.no_telp,\n" +
                "ps.url_ktp,\n" +
                "ps.flag,\n" +
                "ps.action,\n" +
                "ps.created_date,\n" +
                "ps.created_who,\n" +
                "ps.last_update,\n" +
                "ps.last_update_who,\n" +
                "ps.email,\n" +
                "ps.password,\n" +
                "ps.pendidikan,\n" +
                "ps.status_perkawinan,\n" +
                "ps.flag_login,\n" +
                "pss.desa_name,\n" +
                "pss.kecamatan_name,\n" +
                "pss.kota_name,\n" +
                "pss.provinsi_name,\n" +
                "pss.id_finger_data\n" +
                "FROM im_simrs_pasien ps\n" +
                "INNER JOIN (\n" +
                "\tSELECT\n" +
                "\tps.id_pasien,\n" +
                "\tds.desa_name,\n" +
                "\tkc.kecamatan_name,\n" +
                "\tkt.kota_name,\n" +
                "\tpv.provinsi_name,\n" +
                "\tfd.id_finger_data\n" +
                "\tFROM im_simrs_pasien ps\n" +
                "\tLEFT JOIN (SELECT desa_id, desa_name, kecamatan_id FROM im_hris_desa WHERE flag = 'Y') ds ON ds.desa_id = CAST( ps.desa_id AS VARCHAR )\n" +
                "\tINNER JOIN (SELECT kecamatan_id, kecamatan_name, kota_id FROM im_hris_kecamatan WHERE flag = 'Y') kc ON kc.kecamatan_id = ds.kecamatan_id\n" +
                "\tINNER JOIN (SELECT kota_id, kota_name, provinsi_id FROM im_hris_kota WHERE flag = 'Y') kt ON kt.kota_id = kc.kota_id\n" +
                "\tINNER JOIN (SELECT provinsi_id, provinsi_name FROM im_hris_provinsi WHERE flag = 'Y') pv ON pv.provinsi_id = kt.provinsi_id\n" +
                "\tLEFT JOIN (SELECT id_finger_data, id_pasien FROM im_simrs_finger_data WHERE flag = 'Y') fd ON fd.id_pasien = ps.id_pasien\n" +
                ") pss ON pss.id_pasien = ps.id_pasien \n" +
                "WHERE ps.flag = 'Y'\n" + where;

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<Pasien> pasienList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                Pasien pasien = new Pasien();
                pasien.setIdPasien(obj[0].toString());
                pasien.setNama(stNullEscape(obj[1]));
                pasien.setJenisKelamin(stNullEscape(obj[2]));
                pasien.setNoKtp(stNullEscape(obj[3]));
                pasien.setNoBpjs(stNullEscape(obj[4]));
                pasien.setTempatLahir(stNullEscape(obj[5]));
                pasien.setTglLahir(stNullEscape(obj[6]));
                pasien.setDesaId(stNullEscape(obj[7]));
                pasien.setJalan(stNullEscape(obj[8]));
                pasien.setSuku(stNullEscape(obj[9]));
                pasien.setAgama(stNullEscape(obj[10]));
                pasien.setProfesi(stNullEscape(obj[11]));
                pasien.setNoTelp(stNullEscape(obj[12]));
                pasien.setUrlKtp(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_KTP_PASIEN + stNullEscape(obj[13]));
                pasien.setFlag(stNullEscape(obj[14]));
                pasien.setAction(stNullEscape(obj[15]));
                pasien.setCreatedDate(obj[16] == null ? null : (Timestamp) obj[16]);
                pasien.setCreatedWho(stNullEscape(obj[17]));
                pasien.setLastUpdate(obj[18] == null ? null : (Timestamp) obj[18]);
                pasien.setLastUpdateWho(stNullEscape(obj[19]));
                pasien.setEmail(stNullEscape(obj[20]));
                pasien.setPassword(stNullEscape(obj[21]));
                pasien.setPendidikan(stNullEscape(obj[22]));
                pasien.setStatusPerkawinan(stNullEscape(obj[23]));
                pasien.setFlagLogin(stNullEscape(obj[24]));
                pasien.setDesa(stNullEscape(obj[25]));
                pasien.setKecamatan(stNullEscape(obj[26]));
                pasien.setKota(stNullEscape(obj[27]));
                pasien.setProvinsi(stNullEscape(obj[28]));
                pasien.setDisabledFingerData(foundBoolean(obj[29]));
                pasienList.add(pasien);
            }
        }
        return pasienList;
    }

    public List<Pasien> getSearchForTransaksi(Pasien param){

        String where = "";
        if (param.getIdPasien() != null && !"".equalsIgnoreCase(param.getIdPasien()))
            where += "AND ps.id_pasien = '"+param.getIdPasien()+"' \n";
        if (param.getNama() != null && !"".equalsIgnoreCase(param.getNama()))
            where += "AND ps.nama ILIKE '%"+param.getNama()+"%' \n";
        if (param.getDesaId() != null && !"".equalsIgnoreCase(param.getDesaId()))
            where += "AND ps.desa_id = '"+param.getDesaId()+"' \n";
        if (param.getNoKtp() != null && !"".equalsIgnoreCase(param.getNoKtp()))
            where += "AND ps.no_ktp = '"+param.getNoKtp()+"' \n";
        if (param.getNoBpjs() != null && !"".equalsIgnoreCase(param.getNoBpjs()))
            where += "AND ps.no_bpjs = '"+param.getNoBpjs()+"' \n";

        String SQL = "SELECT\n" +
                "ps.id_pasien,\n" +
                "ps.nama,\n" +
                "ps.jenis_kelamin,\n" +
                "ps.no_ktp,\n" +
                "ps.no_bpjs,\n" +
                "ps.tempat_lahir,\n" +
                "ps.tgl_lahir,\n" +
                "ps.desa_id,\n" +
                "ps.jalan,\n" +
                "ps.suku,\n" +
                "ps.agama,\n" +
                "ps.profesi,\n" +
                "ps.no_telp,\n" +
                "ps.url_ktp,\n" +
                "ps.flag,\n" +
                "ps.action,\n" +
                "ps.created_date,\n" +
                "ps.created_who,\n" +
                "ps.last_update,\n" +
                "ps.last_update_who,\n" +
                "ps.email,\n" +
                "ps.password,\n" +
                "ps.pendidikan,\n" +
                "ps.status_perkawinan,\n" +
                "ps.flag_login,\n" +
                "pss.desa_name,\n" +
                "pss.kecamatan_name,\n" +
                "pss.kota_name,\n" +
                "pss.provinsi_name,\n" +
                "pss.id_finger_data,\n" +
                "pss.id_pelayanan,\n" +
                "pss.no_checkup_ulang,\n" +
                "pss.id_detail_checkup,\n" +
                "pss.is_order_lab,\n" +
                "pss.pasien_lama\n" +
                "FROM im_simrs_pasien ps\n" +
                "INNER JOIN (\n" +
                "\tSELECT\n" +
                "\tps.id_pasien,\n" +
                "\tds.desa_name,\n" +
                "\tkc.kecamatan_name,\n" +
                "\tkt.kota_name,\n" +
                "\tpv.provinsi_name,\n" +
                "\tfd.id_finger_data,\n" +
                "\tdc.id_pelayanan,\n" +
                "\tdc.no_checkup_ulang,\n" +
                "\tdc.id_detail_checkup,\n" +
                "\tdc.is_order_lab,\n" +
                "\thd.id_pasien as pasien_lama\n" +
                "\tFROM im_simrs_pasien ps\n" +
                "\tLEFT JOIN (SELECT desa_id, desa_name, kecamatan_id FROM im_hris_desa WHERE flag = 'Y') ds ON ds.desa_id = CAST( ps.desa_id AS VARCHAR )\n" +
                "\tINNER JOIN (SELECT kecamatan_id, kecamatan_name, kota_id FROM im_hris_kecamatan WHERE flag = 'Y') kc ON kc.kecamatan_id = ds.kecamatan_id\n" +
                "\tINNER JOIN (SELECT kota_id, kota_name, provinsi_id FROM im_hris_kota WHERE flag = 'Y') kt ON kt.kota_id = kc.kota_id\n" +
                "\tINNER JOIN (SELECT provinsi_id, provinsi_name FROM im_hris_provinsi WHERE flag = 'Y') pv ON pv.provinsi_id = kt.provinsi_id\n" +
                "\tLEFT JOIN (SELECT id_finger_data, id_pasien FROM im_simrs_finger_data WHERE flag = 'Y') fd ON fd.id_pasien = ps.id_pasien\n" +
                "\tLEFT JOIN (SELECT id_pasien FROM it_simrs_header_checkup WHERE flag = 'Y' GROUP BY id_pasien) hd ON hd.id_pasien = ps.id_pasien\n" +
                "\tLEFT JOIN ( \n" +
                "\t\t-- mencari data detail checkup terakhir terhadap pasien as dc;\n" +
                "\t\tSELECT \n" +
                "\t\tb.id_detail_checkup,\n" +
                "\t\ta.id_pasien,\n" +
                "\t\tb.tgl_cekup,\n" +
                "\t\tb.no_checkup_ulang,\n" +
                "\t\tb.is_order_lab, \n" +
                "\t\tb.id_pelayanan\n" +
                "\t\tFROM it_simrs_header_detail_checkup b\n" +
                "\t\tINNER JOIN \n" +
                "\t\t(\n" +
                "\t\t\tSELECT \n" +
                "\t\t\tb.id_pasien,\n" +
                "\t\t\ta.id_detail_checkup\n" +
                "\t\t\tFROM (\n" +
                "\t\t\t\tSELECT id_detail_checkup, b.id_pasien, a.created_date FROM it_simrs_header_detail_checkup a\n" +
                "\t\t\t\tINNER JOIN (SELECT no_checkup, id_pasien FROM it_simrs_header_checkup) b ON b.no_checkup = a.no_checkup\n" +
                "\t\t\t) a\n" +
                "\t\t\tINNER JOIN \n" +
                "\t\t\t(\n" +
                "\t\t\t\tSELECT \n" +
                "\t\t\t\ta.id_pasien,\n" +
                "\t\t\t\tMAX(b.created_date) as created_date\n" +
                "\t\t\t\tFROM (SELECT id_pasien, no_checkup FROM it_simrs_header_checkup WHERE tgl_keluar IS NOT NULL) a\n" +
                "\t\t\t\tINNER JOIN (SELECT no_checkup, created_date FROM it_simrs_header_detail_checkup) b ON b.no_checkup = a.no_checkup\n" +
                "\t\t\t\tGROUP BY a.id_pasien\n" +
                "\t\t\t) b ON b.id_pasien = a.id_pasien AND b.created_date = a.created_date\n" +
                "\t\t) a ON b.id_detail_checkup = a.id_detail_checkup\n" +
                "\t\t-- end sub query\n" +
                "\t) dc ON dc.id_pasien = ps.id_pasien\n" +
                ") pss ON pss.id_pasien = ps.id_pasien" +
                "WHERE ps.flag = 'Y' \n" + where;

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<Pasien> pasienList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                Pasien pasien = new Pasien();
                pasien.setIdPasien(obj[0].toString());
                pasien.setNama(stNullEscape(obj[1]));
                pasien.setJenisKelamin(stNullEscape(obj[2]));
                pasien.setNoKtp(stNullEscape(obj[3]));
                pasien.setNoBpjs(stNullEscape(obj[4]));
                pasien.setTempatLahir(stNullEscape(obj[5]));
                pasien.setTglLahir(stNullEscape(obj[6]));
                pasien.setDesaId(stNullEscape(obj[7]));
                pasien.setJalan(stNullEscape(obj[8]));
                pasien.setSuku(stNullEscape(obj[9]));
                pasien.setAgama(stNullEscape(obj[10]));
                pasien.setProfesi(stNullEscape(obj[11]));
                pasien.setNoTelp(stNullEscape(obj[12]));
                pasien.setUrlKtp(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_KTP_PASIEN + stNullEscape(obj[13]));
                pasien.setFlag(stNullEscape(obj[14]));
                pasien.setAction(stNullEscape(obj[15]));
                pasien.setCreatedDate(obj[16] == null ? null : (Timestamp) obj[16]);
                pasien.setCreatedWho(stNullEscape(obj[17]));
                pasien.setLastUpdate(obj[18] == null ? null : (Timestamp) obj[18]);
                pasien.setLastUpdateWho(stNullEscape(obj[19]));
                pasien.setEmail(stNullEscape(obj[20]));
                pasien.setPassword(stNullEscape(obj[21]));
                pasien.setPendidikan(stNullEscape(obj[22]));
                pasien.setStatusPerkawinan(stNullEscape(obj[23]));
                pasien.setFlagLogin(stNullEscape(obj[24]));
                pasien.setDesa(stNullEscape(obj[25]));
                pasien.setKecamatan(stNullEscape(obj[26]));
                pasien.setKota(stNullEscape(obj[27]));
                pasien.setProvinsi(stNullEscape(obj[28]));
                pasien.setDisabledFingerData(foundBoolean(obj[29]));
                pasien.setIdPelayanan(stNullEscape(30));
                pasien.setNoCheckuoUlang(stNullEscape(31));
                pasien.setIdLastDetailCheckup(stNullEscape(32));
                pasien.setIsOrderLab(stNullEscape(33));
                pasien.setIsPasienLama(foundBoolean(obj[34]));
                pasienList.add(pasien);
            }
        }
        return pasienList;
    }

    private String stNullEscape(Object obj){
        return obj == null ? "" : obj.toString();
    }

    private Boolean foundBoolean(Object obj){
        boolean found = obj != null;
        return found;
    }
}
