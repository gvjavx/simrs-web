package com.neurix.simrs.master.tindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import io.agora.recording.common.Common;
import io.agora.recording.common.RecordingConfig;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class TindakanDao extends GenericDao<ImSimrsTindakanEntity, String> {
    @Override
    protected Class<ImSimrsTindakanEntity> getEntityClass() {
        return ImSimrsTindakanEntity.class;
    }

    @Override
    public List<ImSimrsTindakanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_tindakan") != null) {
                criteria.add(Restrictions.eq("idTindakan", mapCriteria.get("id_tindakan").toString()));
            }
            if (mapCriteria.get("tindakan") != null) {
                criteria.add(Restrictions.ilike("tindakan", "%" + (String) mapCriteria.get("tindakan") + "%"));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("is_ina") != null) {
                criteria.add(Restrictions.eq("isIna", mapCriteria.get("is_ina").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }

            if (mapCriteria.get("id_header_tindakan") != null) {
                criteria.add(Restrictions.eq("idHeaderTindakan", mapCriteria.get("id_header_tindakan").toString()));
            }

            if (mapCriteria.get("id_pelayanan") != null) {
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }
        }

        List<ImSimrsTindakanEntity> result = criteria.list();
        return result;
    }

    public List<Tindakan> getListComboBoxTindakan(Tindakan bean) {
        List<Tindakan> tindakanList = new ArrayList<>();
        if (bean != null) {
            if (bean.getIdKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakan())) {
                String union  = "";
                String vaksin = "";
                String ambulance = "";
                if (bean.getIsVaksin() != null) {
                    if ("Y".equalsIgnoreCase(bean.getIsVaksin())) {
                        vaksin = "AND b.flag_vaksin = 'Y' \n";
                    }
                }
                if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())) {
                    union = "UNION ALL\n" +
                            "SELECT\n" +
                            "a.id_tindakan,\n" +
                            "b.nama_tindakan,\n" +
                            "a.tarif,\n" +
                            "a.tarif_bpjs,\n" +
                            "a.diskon,\n" +
                            "a.is_elektif\n" +
                            "FROM im_simrs_tindakan a\n" +
                            "INNER JOIN im_simrs_header_tindakan b ON a.id_header_tindakan = b.id_header_tindakan\n" +
                            "WHERE a.id_kategori_tindakan = :idKat AND a.branch_id = :branch \n" +
                            "AND a.flag_kelas_ruangan = 'Y' \n" +
                            "AND a.id_kelas_ruangan = '" + bean.getIdKelasRuangan() + "'\n" +
                            "AND a.flag = 'Y'";
                }

                String tipePelayanan = cekTipePelayanan(bean.getIdKategoriTindakan(), bean.getBranchId());
                if(!"".equalsIgnoreCase(tipePelayanan) && !"rawat_jalan".equalsIgnoreCase(tipePelayanan)){
                    ambulance = "UNION ALL\n" +
                            "SELECT\n" +
                            "a.id_tindakan,\n" +
                            "b.nama_tindakan,\n" +
                            "a.tarif,\n" +
                            "a.tarif_bpjs,\n" +
                            "a.diskon,\n" +
                            "a.is_elektif\n" +
                            "FROM im_simrs_tindakan a\n" +
                            "INNER JOIN im_simrs_header_tindakan b ON a.id_header_tindakan = b.id_header_tindakan\n" +
                            "WHERE a.id_kategori_tindakan = :idKat \n" +
                            "AND a.branch_id = :branch\n" +
                            "AND a.flag = 'Y'\n" +
                            "AND b.kategori IN ('ambulance_datang', 'ambulance_pulang', 'mobil_jenazah')\n";
                }

                String SQL = "SELECT\n" +
                        "a.id_tindakan,\n" +
                        "b.nama_tindakan,\n" +
                        "a.tarif,\n" +
                        "a.tarif_bpjs,\n" +
                        "a.diskon,\n" +
                        "a.is_elektif\n" +
                        "FROM im_simrs_tindakan a\n" +
                        "INNER JOIN im_simrs_header_tindakan b ON a.id_header_tindakan = b.id_header_tindakan\n" +
                        "WHERE a.id_kategori_tindakan = :idKat AND a.branch_id = :branch \n" +
                        "AND a.flag_kelas_ruangan = 'N'\n" +
                        "AND a.flag = 'Y' \n"+
                        "AND b.kategori IS NULL \n"+ vaksin + union + ambulance;

                List<Object[]> results = new ArrayList<>();
                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idKat", bean.getIdKategoriTindakan())
                        .setParameter("branch", bean.getBranchId())
                        .list();

                if (results.size() > 0) {
                    Tindakan tindakan;
                    for (Object[] obj : results) {
                        tindakan = new Tindakan();
                        tindakan.setIdTindakan(obj[0] == null ? "" : obj[0].toString());
                        tindakan.setTindakan(obj[1] == null ? "" : obj[1].toString());
                        tindakan.setTarif(obj[2] == null ? null : (BigInteger) obj[2]);
                        tindakan.setTarifBpjs(obj[3] == null ? null : (BigInteger) obj[3]);
                        tindakan.setDiskon(obj[4] == null ? null : (BigDecimal) obj[4]);
                        tindakan.setIsElektif(obj[5] == null ? "" : obj[5].toString());
                        tindakanList.add(tindakan);
                    }
                }
            }
        }
        return tindakanList;
    }


    private String cekTipePelayanan(String idKat, String branch){
        String res = "";
        String SQL = "SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.tipe_pelayanan\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                "WHERE id_pelayanan = (\n" +
                "SELECT\n" +
                "id_pelayanan\n" +
                "FROM im_simrs_tindakan\n" +
                "WHERE id_kategori_tindakan = :idKat\n" +
                "AND branch_id = :branch LIMIT 1\n" +
                ")\n";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idKat", idKat)
                .setParameter("branch", branch)
                .list();
        if(results.size() > 0){
            Object[] obj = results.get(0);
            res = obj[1].toString();
        }
        return res;
    }

    public String getNextPelayananId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tindakan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "TDK" + sId;
    }

    public List<ImSimrsTindakanEntity> getDataTindakan(String namaTindakan) throws HibernateException {
        List<ImSimrsTindakanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanEntity.class)
                .add(Restrictions.eq("tindakan", namaTindakan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImSimrsTindakanEntity> cekData(String idTindakan) throws HibernateException {
        List<ImSimrsTindakanEntity> results = new ArrayList<>();

        String query = "SELECT a.id_tindakan, b.id_tindakan_rawat\n" +
                "FROM im_simrs_tindakan a\n" +
                "INNER JOIN it_simrs_tindakan_rawat b ON b.id_tindakan = a.id_tindakan\n" +
                "WHERE a.id_tindakan = '" + idTindakan + "' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    public List<Tindakan> getListDataTindakan(Tindakan bean) {
        List<Tindakan> tindakanList = new ArrayList<>();
        String flag = "Y";
        String condition = "";
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            flag = bean.getFlag();
        }
        if (bean.getIdHeaderTindakan() != null && !"".equalsIgnoreCase(bean.getIdHeaderTindakan())) {
            condition = "AND a.id_header_tindakan = '" + bean.getIdHeaderTindakan() + "' \n";
        }
        if (bean.getIdTindakan() != null && !"".equalsIgnoreCase(bean.getIdTindakan())) {
            condition = condition + "AND b.id_tindakan = '" + bean.getIdTindakan() + "' \n";
        }
        if (bean.getTindakan() != null && !"".equalsIgnoreCase(bean.getTindakan())) {
            condition = condition + "AND a.nama_tindakan ILIKE '%" + bean.getTindakan() + "%' \n";
        }
        if (bean.getIdHeaderTindakan() != null && !"".equalsIgnoreCase(bean.getIdHeaderTindakan())) {
            condition = condition + "AND a.id_header_tindakan = '" + bean.getIdHeaderTindakan() + "' \n";
        }
        if (bean.getIsIna() != null && !"".equalsIgnoreCase(bean.getIsIna())) {
            condition = condition + "AND b.is_ina = '" + bean.getIsIna() + "' \n";
        }
        if (bean.getIsElektif() != null && !"".equalsIgnoreCase(bean.getIsElektif())) {
            condition = condition + "AND b.is_elektif = '" + bean.getIsElektif() + "' \n";
        }
        if (bean.getIdKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakan())) {
            condition = condition + "AND b.id_kategori_tindakan = '" + bean.getIdKategoriTindakan() + "' \n";
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            condition = condition + "AND b.branch_id = '" + bean.getBranchId() + "' \n";
        }
        if (bean.getKategoriInaBpjs() != null && !"".equalsIgnoreCase(bean.getKategoriInaBpjs())) {
            condition = condition + "AND a.kategori_ina_bpjs = '" + bean.getKategoriInaBpjs() + "' \n";
        }
        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
            condition = condition + "AND b.id_pelayanan = '" + bean.getIdPelayanan() + "' \n";
        }
        String SQL = "SELECT \n" +
                "a.id_header_tindakan,\n" +
                "a.nama_tindakan,\n" +
                "a.kategori_ina_bpjs,\n" +
                "e.nama,\n" +
                "b.id_tindakan,\n" +
                "b.tarif,\n" +
                "b.tarif_bpjs,\n" +
                "b.diskon,\n" +
                "b.id_kategori_tindakan,\n" +
                "c.kategori_tindakan,\n" +
                "b.id_pelayanan,\n" +
                "d.nama_pelayanan,\n" +
                "f.branch_id,\n" +
                "f.branch_name,\n" +
                "b.is_ina,\n" +
                "b.is_elektif\n" +
                "FROM im_simrs_header_tindakan a\n" +
                "INNER JOIN im_simrs_tindakan b ON  a.id_header_tindakan = b.id_header_tindakan\n" +
                "INNER JOIN im_simrs_kategori_tindakan c ON b.id_kategori_tindakan = c.id_kategori_tindakan\n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "a.branch_id,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) d ON b.id_pelayanan = d.id_pelayanan\n" +
                "INNER JOIN im_simrs_kategori_tindakan_ina e ON a.kategori_ina_bpjs = e.id\n" +
                "INNER JOIN im_branches f ON b.branch_id = f.branch_id\n" +
                "WHERE b.flag = :flag\n " + condition;

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("flag", flag)
                .list();
        if (result.size() > 0) {
            for (Object[] obj : result) {
                Tindakan tindakan = new Tindakan();
                tindakan.setIdHeaderTindakan(obj[0] != null ? obj[0].toString() : null);
                tindakan.setTindakan(obj[1] != null ? obj[1].toString() : null);
                tindakan.setKategoriInaBpjs(obj[2] != null ? obj[2].toString() : null);
                tindakan.setNamaKategoriTindakanIna(obj[3] != null ? obj[3].toString() : null);
                tindakan.setIdTindakan(obj[4] != null ? obj[4].toString() : null);
                tindakan.setTarif(obj[5] != null ? (BigInteger) obj[5] : null);
                tindakan.setTarifBpjs(obj[6] != null ? (BigInteger) obj[6] : null);
                tindakan.setDiskon(obj[7] != null ? (BigDecimal) obj[7] : null);
                tindakan.setIdKategoriTindakan(obj[8] != null ? obj[8].toString() : null);
                tindakan.setNamaKategoriTindakan(obj[9] != null ? obj[9].toString() : null);
                tindakan.setIdPelayanan(obj[10] != null ? obj[10].toString() : null);
                tindakan.setNamaPelayanan(obj[11] != null ? obj[11].toString() : null);
                tindakan.setBranchId(obj[12] != null ? obj[12].toString() : null);
                tindakan.setBranchName(obj[13] != null ? obj[13].toString() : null);
                tindakan.setIsIna(obj[14] != null ? obj[14].toString() : null);
                tindakan.setIsElektif(obj[15] != null ? obj[15].toString() : null);
                tindakanList.add(tindakan);
            }
        }
        return tindakanList;
    }

    public List<ImSimrsTindakanEntity> cekDataTindakan(String idHeader, String idPelayanan, String idKelas) throws HibernateException {
        List<ImSimrsTindakanEntity> results = new ArrayList<>();
        if ("empty".equalsIgnoreCase(idKelas)) {
            results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanEntity.class)
                    .add(Restrictions.eq("idHeaderTindakan", idHeader))
                    .add(Restrictions.eq("branchId", CommonUtil.userBranchLogin()))
                    .add(Restrictions.eq("idPelayanan", idPelayanan))
                    .add(Restrictions.eq("flag", "Y"))
                    .list();
        } else {
            results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanEntity.class)
                    .add(Restrictions.eq("idHeaderTindakan", idHeader))
                    .add(Restrictions.eq("branchId", CommonUtil.userBranchLogin()))
                    .add(Restrictions.eq("idPelayanan", idPelayanan))
                    .add(Restrictions.eq("idKelasRuangan", idKelas))
                    .add(Restrictions.eq("flag", "Y"))
                    .list();
        }
        return results;
    }

    public List<Tindakan> getListTindakanApotek(String branchId, String idPelayanan, String idTindakan) {
        List<Tindakan> tindakanList = new ArrayList<>();
        String notLike1 = "";
        String notLike2 = "";
        if (idTindakan != null && !"".equalsIgnoreCase(idTindakan)) {
            notLike1 = "AND a.id_obat NOT IN (" + idTindakan + ")";
            notLike2 = "AND b.id_tindakan NOT IN (" + idTindakan + ")";
        }
        String SQL = "SELECT\n" +
                "a.id_obat as id_tindakan,\n" +
                "a.nama_obat as keterangan,\n" +
                "c.harga_jual as tarif,\n" +
                "c.harga_jual as tarif_bpjs,\n" +
                "CAST('0' AS NUMERIC) as diskon,\n" +
                "CAST('obat' AS VARCHAR) as tipe\n" +
                "FROM im_simrs_header_obat a\n" +
                "INNER JOIN (\n" +
                "\tSELECT \n" +
                "\tid_obat,\n" +
                "\tSUM(qty_box) as qty_box,\n" +
                "\tSUM(qty_lembar) as qty_lembar,\n" +
                "\tSUM(qty_biji) as qty_biji\n" +
                "\tFROM im_simrs_obat\n" +
                "\tWHERE branch_id = '" + branchId + "'\n" +
                "\tGROUP BY id_obat\n" +
                "\tHAVING SUM(qty_box) > 0 OR SUM(qty_lembar) > 0 OR SUM(qty_biji) > 0 \n" +
                ") b ON a.id_obat = b.id_obat\n" +
                "INNER JOIN mt_simrs_harga_obat c ON a.id_obat = c.id_obat\n" +
                "WHERE a.id_kategori_persediaan = 'KTP000003'\n" +
                "AND a.id_bentuk ILIKE 'capsule'\n" + notLike1 + "\n" +
                "UNION ALL\n" +
                "SELECT \n" +
                "b.id_tindakan,\n" +
                "a.nama_tindakan,\n" +
                "b.tarif,\n" +
                "b.tarif_bpjs,\n" +
                "b.diskon,\n" +
                "CAST('tindakan' AS VARCHAR) as tipe\n" +
                "FROM im_simrs_header_tindakan a\n" +
                "INNER JOIN im_simrs_tindakan b ON a.id_header_tindakan = b.id_header_tindakan\n" +
                "WHERE b.branch_id = '" + branchId + "' AND b.id_pelayanan = '" + idPelayanan + "'\n" + notLike2 + "\n";
        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        if (result.size() > 0) {
            for (Object[] obj : result) {
                Tindakan tindakan = new Tindakan();
                tindakan.setIdTindakan(obj[0] != null ? obj[0].toString() : null);
                tindakan.setTindakan(obj[1] != null ? obj[1].toString() : null);
                tindakan.setbDTarif(obj[2] != null ? (BigDecimal) obj[2] : null);
                tindakan.setbDTarifBpjs(obj[3] != null ? (BigDecimal) obj[3] : null);
                tindakan.setDiskon(obj[4] != null ? (BigDecimal) obj[4] : null);
                tindakan.setTipe(obj[5] != null ? obj[5].toString() : null);
                tindakanList.add(tindakan);
            }
        }
        return tindakanList;
    }

    public List<Tindakan> getComboAmbulance(String branchId) {
        List<Tindakan> tindakanList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "a.id_tindakan,\n" +
                "b.nama_tindakan,\n" +
                "a.tarif,\n" +
                "a.tarif_bpjs,\n" +
                "a.diskon,\n" +
                "a.is_elektif\n" +
                "FROM im_simrs_tindakan a\n" +
                "INNER JOIN im_simrs_header_tindakan b ON a.id_header_tindakan = b.id_header_tindakan\n" +
                "WHERE a.branch_id = :branch\n" +
                "AND a.flag = 'Y'\n" +
                "AND b.kategori IN ('ambulance_datang', 'ambulance_pulang', 'mobil_jenazah')";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).
                setParameter("branch", branchId)
                .list();
        if (result.size() > 0) {
            Tindakan tindakan;
            for (Object[] obj : result) {
                tindakan = new Tindakan();
                tindakan.setIdTindakan(obj[0] == null ? "" : obj[0].toString());
                tindakan.setTindakan(obj[1] == null ? "" : obj[1].toString());
                tindakan.setTarif(obj[2] == null ? null : (BigInteger) obj[2]);
                tindakan.setTarifBpjs(obj[3] == null ? null : (BigInteger) obj[3]);
                tindakan.setDiskon(obj[4] == null ? null : (BigDecimal) obj[4]);
                tindakan.setIsElektif(obj[5] == null ? "" : obj[5].toString());
                tindakanList.add(tindakan);
            }
        }
        return tindakanList;
    }

    public List<Tindakan> getTindakanPelayanan(String idPelayanan, String branchId) throws HibernateException{
        List<Tindakan> tindakanList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "a.id_tindakan,\n" +
                "b.nama_tindakan\n" +
                "FROM im_simrs_tindakan a\n" +
                "INNER JOIN im_simrs_header_tindakan b ON a.id_header_tindakan = b.id_header_tindakan\n" +
                "WHERE a.id_pelayanan = '" + idPelayanan + "'\n" +
                "AND a.branch_id = '" + branchId + "'\n" +
                "AND a.flag = 'Y'";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (result.size() > 0) {
            Tindakan tindakan;
            for (Object[] obj : result) {
                tindakan = new Tindakan();
                tindakan.setIdTindakan(obj[0] == null ? "" : obj[0].toString());
                tindakan.setTindakan(obj[1] == null ? "" : obj[1].toString());
                tindakanList.add(tindakan);
            }
        }
        return tindakanList;
    }
}
