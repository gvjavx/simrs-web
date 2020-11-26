package com.neurix.hris.transaksi.lembur.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.lembur.model.LemburEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class LemburDao extends GenericDao<LemburEntity, String> {

    @Override
    protected Class<LemburEntity> getEntityClass() {
        return LemburEntity.class;
    }

    @Override
    public List<LemburEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(LemburEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("lembur_id") != null) {
                criteria.add(Restrictions.ilike("lemburId", "%" + (String) mapCriteria.get("lembur_id") + "%"));
            }
            if (mapCriteria.get("nip") != null) {
                criteria.add(Restrictions.ilike("nip", "%" + (String) mapCriteria.get("nip") + "%"));
            }
            if (mapCriteria.get("status_giling") != null) {
                criteria.add(Restrictions.eq("statusGiling", (String) mapCriteria.get("status_giling")));
            }
            if (mapCriteria.get("approval_flag") !=null) {
                if (mapCriteria.get("approval_flag")=="0"){
                    criteria.add(Restrictions.isNull("approvalFlag"));
//                    criteria.add(Restrictions.ne("approvalFlag","N"));
                }else {
                    criteria.add(Restrictions.eq("approvalFlag", (String) mapCriteria.get("approval_flag")));
                }
            }
            if (mapCriteria.get("tanggal_dari") != null && mapCriteria.get("tanggal_selesai") != null) {
                criteria.add(Restrictions.between("tanggalAwal", mapCriteria.get("tanggal_dari"), mapCriteria.get("tanggal_selesai")));
                criteria.add(Restrictions.between("tanggalAkhir", mapCriteria.get("tanggal_dari"), mapCriteria.get("tanggal_selesai")));
            } else {
                if (mapCriteria.get("tanggal_dari") != null) {
                    criteria.add(Restrictions.ge("tanggalAwal", mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai") != null) {
                    criteria.add(Restrictions.le("tanggalAkhir", mapCriteria.get("tanggal_selesai")));
                }
            }
            if (mapCriteria.get("from") != null) {
                if (mapCriteria.get("from").equals("absensi")) {
                    if (mapCriteria.get("tanggal") != null) {
                        criteria.add(Restrictions.le("tanggalAwal", mapCriteria.get("tanggal")));
                        criteria.add(Restrictions.ge("tanggalAkhir", mapCriteria.get("tanggal")));
                    }
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("lemburId"));

        List<LemburEntity> results = criteria.list();

        return results;
    }

    public String getNextLemburId() throws HibernateException {


        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_lembur')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "LE" + sId;
    }

    public List<LemburEntity> getListPersonalFromNip(String nip, Date tanggal) throws HibernateException {
        List<LemburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(LemburEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.le("tanggalAwalSetuju", tanggal))
                .add(Restrictions.ge("tanggalAkhirSetuju", tanggal))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }

    public List<LemburEntity> getListLemburByNipAndTanggal(String nip, Date tanggal) throws HibernateException {
        List<LemburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(LemburEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.le("tanggalAwalSetuju", tanggal))
                .add(Restrictions.ge("tanggalAkhirSetuju", tanggal))
                .list();
        return results;
    }

    public List<LemburEntity> getListLemburByNipAndTanggalForInquiry(String nip, Date tanggal) throws HibernateException {
        List<LemburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(LemburEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.ne("tanggalAwalSetuju", tanggal))
                .list();
        return results;
    }

    public List<LemburEntity> getListLemburTestTanggal(Date tanggal, String nip) throws HibernateException {
        List<LemburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(LemburEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.le("tanggalAwalSetuju", tanggal))
                .add(Restrictions.ge("tanggalAkhirSetuju", tanggal))
                .list();
        return results;
    }

    public List<LemburEntity> getCekLembur(String nip, Date tanggal) throws HibernateException {
        List<LemburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(LemburEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.le("tanggalAwalSetuju", tanggal))
                .add(Restrictions.ge("tanggalAkhirSetuju", tanggal))
                .list();
        return results;
    }

    public List<Object[]> findInfoLembur(String idLembur, String nip) throws HibernateException {
        String SQL = "SELECT \n" +
                "\tusers.nip,\n" +
                "\tusers.nama_pegawai,\n" +
                "\tpositions.position_name posisi,\n" +
                "\tdepartement.department_name divisi,\n" +
                "\tlembur.tanggal_awal,\n" +
                "\tlembur.tanggal_akhir,\n" +
                "\tlembur.jam_awal,\n" +
                "\tlembur.lama_jam,\n" +
                "\tbranches.branch_name,\n" +
                "\tlembur.keterangan,\n" +
                "\tlembur.jam_akhir\n" +
                "FROM\n" +
                "\t(SELECT * FROM it_hris_lembur WHERE lembur_id = :idLembur) lembur LEFT JOIN\n" +
                "\t(SELECT * FROM im_hris_pegawai WHERE nip = :nip) users ON lembur.nip = users.nip LEFT JOIN\n" +
                "\t(SELECT * FROM it_hris_pegawai_position) posisi ON users.nip = posisi.nip LEFT JOIN\n" +
                "\t(SELECT * FROM im_position) positions ON posisi.position_id = positions.position_id LEFT JOIN\n" +
                "\t(SELECT * FROM im_hris_department) departement ON positions.department_id = departement.department_id LEFT JOIN\n" +
                "\t(SELECT * FROM im_branches) branches ON posisi.branch_id = branches.branch_id";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("idLembur", idLembur)
                .setParameter("nip", nip)
                .list();

        return results;
    }

    public List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws HibernateException {
        String SQL = "SELECT lembur.lembur_id, lembur.nip, lembur.pegawai_name, lembur.tanggal_awal, lembur.tanggal_akhir, \n" +
                "lembur.jam_awal, lembur.jam_akhir, lembur.lama_jam, branch.branch_name FROM\n" +
                "\t(SELECT * FROM it_hris_lembur WHERE approval_id = :nip AND approval_flag = :flag) lembur LEFT JOIN\n" +
                "\t(SELECT * FROM im_hris_pegawai) users ON lembur.approval_id = users.nip LEFT JOIN\n" +
                "\t(SELECT * FROM it_hris_pegawai_position) pegawai ON pegawai.nip = users.nip LEFT JOIN\n" +
                "\t(SELECT * FROM im_branches) branch ON branch.branch_id = pegawai.branch_id\n" +
                "ORDER BY lembur.last_update DESC";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("flag", flag)
                .list();

        return results;
    }

    public List<LemburEntity> getListLemburForApproval(Map mapCriteria) {
        List<LemburEntity> listOfResult = new ArrayList<LemburEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String nip = null, lemburId = null,atasan=null;
        String searchNip = "" ;
        String searchLemburId = "" ;
        String searchAtasan = "" ;
        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("lembur_id") != null) {
                lemburId = (String) mapCriteria.get("lembur_id");
            }
            if (mapCriteria.get("nip") != null) {
                nip = (String) mapCriteria.get("nip");
            }
            if (mapCriteria.get("nip_atasan") != null) {
                atasan = (String) mapCriteria.get("nip_atasan");
            }

            if (nip!=null){
                if(!nip.equalsIgnoreCase("")){
                    searchNip = " AND lembur.nip = '" + nip + "' " ;
                }
            }
            if (lemburId!=null){
                if(!lemburId.equalsIgnoreCase("")){
                    searchLemburId = " AND lembur.lembur_id = '" + lemburId + "' " ;
                }
            }
            if (atasan!=null){
                if(!atasan.equalsIgnoreCase("")){
                    searchAtasan = " AND notifikasi.nip = '" + atasan + "' " ;
                }
            }

            String query = "SELECT lembur.* FROM \n" +
                    "\t( SELECT * FROM it_hris_notifikasi ) notifikasi LEFT JOIN \n" +
                    "\t( SELECT * FROM it_hris_lembur ) lembur ON notifikasi.no_request=lembur.lembur_id\n" +
                    "WHERE notifikasi.tipe_notif_id='TN77' AND lembur.flag='Y' "+searchAtasan+searchNip+searchLemburId+" ORDER BY lembur.lembur_id DESC";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                LemburEntity lemburEntity = new LemburEntity();
                lemburEntity.setLemburId((String) row[0]);
                lemburEntity.setNip((String) row[1]);
                lemburEntity.setPegawaiName((String) row[2]);
                lemburEntity.setDivisiId((String) row[3]);
                lemburEntity.setDivisiName((String) row[4]);
                lemburEntity.setPositionId((String) row[5]);
                lemburEntity.setPositionName((String) row[6]);
                lemburEntity.setGolonganId((String) row[7]);
                lemburEntity.setGolonganName((String) row[8]);
                lemburEntity.setTipePegawaiId((String) row[9]);
                lemburEntity.setStatusGiling((String) row[10]);
                lemburEntity.setTanggalAwal((Date) row[11]);
                lemburEntity.setTanggalAkhir((Date) row[12]);
                lemburEntity.setJamAwal((String) row[13]);
                lemburEntity.setJamAkhir((String) row[14]);
                BigDecimal lamaJam =(BigDecimal)row[15];
                lemburEntity.setLamaJam(lamaJam.doubleValue());
                lemburEntity.setApprovalId((String) row[16]);
                lemburEntity.setApprovalName((String) row[17]);
                lemburEntity.setApprovalDate((Timestamp) row[18]);
                lemburEntity.setApprovalFlag((String) row[19]);
                lemburEntity.setTipeLembur((String) row[20]);
                lemburEntity.setAction((String) row[21]);
                lemburEntity.setFlag((String) row[22]);
                lemburEntity.setCreatedWho((String) row[23]);
                lemburEntity.setCreatedDate((Timestamp) row[24]);
                lemburEntity.setLastUpdate((Timestamp) row[25]);
                lemburEntity.setLastUpdateWho((String) row[26]);
                lemburEntity.setKeterangan((String) row[27]);
                lemburEntity.setTanggalAwalSetuju((Date) row[28]);
                lemburEntity.setTanggalAkhirSetuju((Date) row[29]);
                listOfResult.add(lemburEntity);
            }
        }
        return listOfResult;
    }
}
