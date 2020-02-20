package com.neurix.hris.transaksi.ijinKeluar.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarEntity;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
public class IjinKeluarDao extends GenericDao<IjinKeluarEntity, String> {
    @Override
    protected Class<IjinKeluarEntity> getEntityClass() {
        return IjinKeluarEntity.class;
    }

    @Override
    public List<IjinKeluarEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(IjinKeluarEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("ijin_keluar_id")!=null) {
                criteria.add(Restrictions.eq("ijinKeluarId", (String) mapCriteria.get("ijin_keluar_id")));
            }
            if (mapCriteria.get("approval_flag")!=null) {
                criteria.add(Restrictions.eq("approvalFlag", (String) mapCriteria.get("approval_flag")));
            }
            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }
            if (mapCriteria.get("approval_sdm_flag")!=null) {
                criteria.add(Restrictions.eq("approvalSdmFlag", (String) mapCriteria.get("approval_sdm_flag")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.ilike("nip", "%" + (String)mapCriteria.get("nip") + "%"));
            }
            if (mapCriteria.get("unit_id")!=null) {
                criteria.add(Restrictions.ilike("unitId", "%" + (String)mapCriteria.get("unit_id") + "%"));
            }
            if (mapCriteria.get("ijin_id")!=null) {
                criteria.add(Restrictions.ilike("ijinId", "%" + (String)mapCriteria.get("ijin_id") + "%"));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("tanggalAwal",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
                criteria.add(Restrictions.between("tanggalAkhir",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggalAwal",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("tanggalAkhir",mapCriteria.get("tanggal_selesai")));
                }
            }
            if (mapCriteria.get("from")!=null) {
                if (mapCriteria.get("from").equals("absensi")) {
                    if (mapCriteria.get("tanggal")!=null) {
                        criteria.add(Restrictions.eq("tanggalAwal",mapCriteria.get("tanggal")));
                    }
                }
            }
            if (mapCriteria.get("from")!=null) {
                if (mapCriteria.get("from").equals("ijinKeluarKantor")) {
                    if (mapCriteria.get("tanggal_dari")!=null&&mapCriteria.get("tanggal_selesai")!=null) {
                        criteria.add(Restrictions.le("tanggalAwal",mapCriteria.get("tanggal_selesai")));
                        criteria.add(Restrictions.ge("tanggalAwal",mapCriteria.get("tanggal_dari")));
                    }
                }
            }
            if (mapCriteria.get("to")!=null) {
                if (mapCriteria.get("to").equals("ijin_keluar_kantor")) {
                    criteria.add(Restrictions.eq("tanggalAwal",mapCriteria.get("tanggal")));
                }
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("ijinKeluarId"));

        List<IjinKeluarEntity> results = criteria.list();

        return results;
    }

    //Get sisa hari cuti
    public BigInteger getJumlahHariCuti(String nip) throws HibernateException {
        String sql = "SELECT sisa_hari_cuti FROM it_hris_cuti_pegawai WHERE nip LIKE :nip ORDER BY created_date DESC LIMIT 1";
        String nipId = nip;
        BigInteger hasil = null;
        Query query = this.sessionFactory.getCurrentSession()
                .createSQLQuery(sql)
                .setParameter("nip",nipId);
        if (query != null){
        }
        return hasil;
    }
    // Generate surrogate id from postgre
    public String getNextIjinKeluarKantorId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_ijin_keluar_kantor')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "IJK"+sId;
    }
    public String getNextIjinKeluarId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_ijin_keluar')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "IJK"+sId;
    }

    public List<IjinKeluarEntity> getListIjinKeluar(String term) throws HibernateException {

        List<IjinKeluarEntity> results = this.sessionFactory.getCurrentSession().createCriteria(IjinKeluarEntity.class)
                .add(Restrictions.eq("flag", "Y"))
//                .addOrder(Order.asc("primaryKey.id"))
                .list();

        return results;
    }

    //Get Sisa Cuti

    public List<IjinKeluarEntity> getListSisaIjinKeluar(String term) throws HibernateException {
        List<IjinKeluarEntity> results = this.sessionFactory.getCurrentSession().createCriteria(IjinKeluarEntity.class)
                .add(Restrictions.eq("nip",term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();
        return results;
    }

    public List<Object[]> findInfoIjin(String idIjinPegawai, String nip) throws HibernateException {
        String SQL = "SELECT\n" +
                "    users.nip,\n" +
                "    users.nama_pegawai,\n" +
                "    departement.department_name,\n" +
                "    headercuti.ijin_name,\n" +
                "    positions.position_name,\n" +
                "    ijin.tanggal_awal,\n" +
                "    ijin.tanggal_akhir,\n" +
                "    ijin.lama_ijin,\n" +
                "    branches.branch_name,\n" +
                "    ijin.ijin_keluar_id,\n" +
                "    ijin.keterangan \n" +
                "FROM\n" +
                "    (SELECT * FROM it_hris_ijin_keluar WHERE ijin_keluar_id = :idIjinPegawai) ijin LEFT JOIN\n" +
                "\t(SELECT * FROM im_hris_pegawai WHERE nip = :nip ) users ON ijin.nip = users.nip LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_pegawai_position) posisi ON users.nip = posisi.nip LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_ijin) headercuti ON ijin.ijin_id = headercuti.ijin_id LEFT JOIN\n" +
                "    (SELECT * FROM im_position) positions ON posisi.position_id = positions.position_id LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_department) departement ON positions.department_id = departement.department_id LEFT JOIN\n" +
                "    (SELECT * FROM im_branches) branches ON ijin.unit_id = branches.branch_id";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("idIjinPegawai", idIjinPegawai)
                .setParameter("nip", nip)
                .list();

        return results;
    }

    public List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws HibernateException {
        String SQL = "SELECT \n" +
                "\tijin_keluar_id, \n" +
                "\tijin.nip, \n" +
                "\tijin.nama_pegawai, \n" +
                "\tijin.tanggal_awal, \n" +
                "\tijin.tanggal_akhir, \n" +
                "\tbranch.branch_name, \n" +
                "\tijin.last_update,\n" +
                "\tijin.keterangan \n" +
                "\tFROM \n" +
                "\t\t(SELECT * FROM it_hris_ijin_keluar WHERE approval_id = :nip AND approval_flag = :flag) ijin LEFT JOIN \n" +
                "                (SELECT * FROM im_hris_pegawai) users ON ijin.approval_id = users.nip LEFT JOIN \n" +
                "                (SELECT * FROM im_branches) branch ON ijin.unit_id = branch.branch_id \n" +
                "                WHERE ijin.ijin_id <>'IJ001' \n" +
                "                ORDER BY last_update DESC";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("flag", flag)
                .list();

        return results;
    }

    public List getComboTestTanggal(String nip, String tanggalDari, String tanggalSelesai) throws HibernateException, ParseException {
        IjinKeluarEntity imIjinKeluarEntity = new IjinKeluarEntity();
        String sql = "SELECT * FROM it_hris_ijin_keluar " +
                "WHERE nip=:nip " +
                "AND flag='Y' " +
                "AND approval_flag='Y' " +
                "AND approval_sdm_flag='Y' " +
                "AND ijin_id<>'IJ001' " +
                "AND tanggal_awal " +
                "BETWEEN TO_TIMESTAMP(:tanggalDari,'DD/MM/YYYY') " +
                "AND TO_TIMESTAMP(:tanggalSelesai,'DD/MM/YYYY') " +
                "OR nip=:nip " +
                "AND flag='Y' " +
                "AND approval_flag='Y' " +
                "AND approval_sdm_flag='Y' " +
                "AND ijin_id<>'IJ001' "+
                "AND tanggal_akhir " +
                "BETWEEN TO_TIMESTAMP(:tanggalDari,'DD/MM/YYYY') " +
                "AND TO_TIMESTAMP(:tanggalSelesai,'DD/MM/YYYY')";
        String nipId = nip;
        String tanggalAwal = tanggalDari;
        String tanggalAkhir = tanggalSelesai;
        boolean hasil;
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameter("nip", nipId);
        query.setParameter("tanggalDari",tanggalAwal);
        query.setParameter("tanggalSelesai",tanggalAkhir);
        List cek = query.list();
        return cek;
    }
    public List<IjinKeluarEntity> getListPersonalFromNip(String nip, Date tanggal) throws HibernateException {
        List<IjinKeluarEntity> results = this.sessionFactory.getCurrentSession().createCriteria(IjinKeluarEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.eq("ijinId","IJ001"))
                .add(Restrictions.eq("tanggalAwal",tanggal))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    public List<IjinKeluarEntity> getListIjinByNipAndTanggal(String nip , Date tanggal) throws HibernateException {
        List<IjinKeluarEntity> results = this.sessionFactory.getCurrentSession().createCriteria(IjinKeluarEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .add(Restrictions.ne("ijinId","IJ001"))
                .add(Restrictions.ge("tanggalAkhir",tanggal))
                .add(Restrictions.le("tanggalAwal",tanggal))
                .list();
        return results;
    }
    public List<IjinKeluarEntity> getListTestTanggal(Date tanggal,String nip) throws HibernateException {
        List<IjinKeluarEntity> results = this.sessionFactory.getCurrentSession().createCriteria(IjinKeluarEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.eq("approvalSdmFlag", "Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .add(Restrictions.ne("ijinId","IJ001"))
                .add(Restrictions.le("tanggalAwal",tanggal))
                .add(Restrictions.ge("tanggalAkhir",tanggal))
                .list();
        return results;
    }

    public List<IjinKeluarEntity> getListIjinKeluarForApproval(Map mapCriteria) {
        List<IjinKeluarEntity> listOfResult = new ArrayList<IjinKeluarEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String nip = null, ijinKeluarId = null,atasan=null;
        String searchNip = "" ;
        String searchIjinKeluarId = "" ;
        String searchAtasan = "" ;
        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("ijin_keluar_id") != null) {
                ijinKeluarId = (String) mapCriteria.get("ijin_keluar_id");
            }
            if (mapCriteria.get("nip") != null) {
                nip = (String) mapCriteria.get("nip");
            }
            if (mapCriteria.get("nip_atasan") != null) {
                atasan = (String) mapCriteria.get("nip_atasan");
            }

            if (nip!=null){
                if(!nip.equalsIgnoreCase("")){
                    searchNip = " AND ijinKeluar.nip = '" + nip + "' " ;
                }
            }
            if (ijinKeluarId!=null){
                if(!ijinKeluarId.equalsIgnoreCase("")){
                    searchIjinKeluarId = " AND ijinKeluar.ijin_keluar_id = '" + ijinKeluarId + "' " ;
                }
            }
            if (atasan!=null){
                if(!atasan.equalsIgnoreCase("")){
                    searchAtasan = " AND notifikasi.nip = '" + atasan + "' " ;
                }
            }

            String query = "SELECT ijinKeluar.* FROM  \n" +
                    "                    ( SELECT * FROM it_hris_notifikasi ) notifikasi LEFT JOIN  \n" +
                    "                    ( SELECT * FROM it_hris_ijin_keluar ) ijinKeluar ON notifikasi.no_request=ijinKeluar.ijin_keluar_id " +
                    "WHERE notifikasi.tipe_notif_id='TN55' AND ijinKeluar.flag='Y' "+searchAtasan+searchNip+searchIjinKeluarId+" OR notifikasi.tipe_notif_id='TN88' AND ijinKeluar.flag='Y' "+searchAtasan+searchNip+searchIjinKeluarId+" ORDER BY ijinKeluar.ijin_keluar_id DESC";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                IjinKeluarEntity ijinKeluarEntity = new IjinKeluarEntity();
                ijinKeluarEntity.setIjinKeluarId((String) row[0]);
                ijinKeluarEntity.setIjinId((String) row[1]);
                ijinKeluarEntity.setIjinName((String) row[2]);
                ijinKeluarEntity.setLamaIjin((BigInteger) row[3]);
                ijinKeluarEntity.setNip((String) row[4]);
                ijinKeluarEntity.setNamaPegawai((String) row[5]);
                ijinKeluarEntity.setKeterangan((String) row[6]);
                ijinKeluarEntity.setApprovalId((String) row[7]);
                ijinKeluarEntity.setApprovalName((String) row[8]);
                ijinKeluarEntity.setApprovalDate((Timestamp) row[9]);
                ijinKeluarEntity.setNotApprovalNote((String) row[10]);
                ijinKeluarEntity.setUnitId((String) row[11]);
                ijinKeluarEntity.setTanggalAwal((Date) row[12]);
                ijinKeluarEntity.setTanggalAkhir((Date) row[13]);
                ijinKeluarEntity.setCreatedDate((Timestamp) row[14]);
                ijinKeluarEntity.setCreatedWho((String) row[15]);
                ijinKeluarEntity.setLastUpdate((Timestamp) row[16]);
                ijinKeluarEntity.setLastUpdateWho((String) row[17]);
                ijinKeluarEntity.setAction((String) row[18]);
                ijinKeluarEntity.setFlag((String) row[19]);
                ijinKeluarEntity.setApprovalFlag((String) row[20]);
                ijinKeluarEntity.setApprovalSdmId((String) row[21]);
                ijinKeluarEntity.setApprovalSdmName((String) row[22]);
                ijinKeluarEntity.setApprovalSdmDate((Timestamp) row[23]);
                ijinKeluarEntity.setApprovalSdmFlag((String) row[24]);
                ijinKeluarEntity.setNotApprovalSdmNote((String) row[25]);
                ijinKeluarEntity.setGolonganId((String) row[26]);
                ijinKeluarEntity.setGolonganName((String) row[27]);
                ijinKeluarEntity.setPositionId((String) row[28]);
                ijinKeluarEntity.setPositionName((String) row[29]);
                ijinKeluarEntity.setJamKeluar((String) row[30]);
                ijinKeluarEntity.setJamKembali((String) row[31]);
                ijinKeluarEntity.setCancelFlag((String) row[32]);
                ijinKeluarEntity.setCancelDate((Timestamp) row[33]);
                ijinKeluarEntity.setCancelPerson((String) row[34]);
                ijinKeluarEntity.setCancelNote((String) row[35]);

                listOfResult.add(ijinKeluarEntity);
            }
        }
        return listOfResult;
    }
    public List<ItPersonilPositionEntity> getPegawaiDispensasiMasal(String unit) throws HibernateException {
        List<ItPersonilPositionEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPersonilPositionEntity.class)
                .add(Restrictions.eq("flag","Y"))
                .add(Restrictions.eq("branchId",unit))
                .add(Restrictions.ne("positionId","00"))
                .add(Restrictions.ne("positionId","01"))
                .add(Restrictions.ne("positionId","02"))
                .list();
        return results;
    }

    public String getName(String nip){
        String result="";
        String query="select nama_pegawai\n" +
                "from im_hris_pegawai\n" +
                "where nip = '"+nip+"'\n" +
                "and flag = 'Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result="";
        }
        return result;
    }
}
