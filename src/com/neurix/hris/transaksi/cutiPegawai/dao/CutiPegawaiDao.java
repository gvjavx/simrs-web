package com.neurix.hris.transaksi.cutiPegawai.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class CutiPegawaiDao extends GenericDao<ItCutiPegawaiEntity, String> {

    @Override
    protected Class<ItCutiPegawaiEntity> getEntityClass() {
        return ItCutiPegawaiEntity.class;
    }

    @Override
    public List<ItCutiPegawaiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("cuti_pegawai_id") != null) {
                criteria.add(Restrictions.eq("cutiPegawaiId", (String) mapCriteria.get("cuti_pegawai_id")));
            }
            if (mapCriteria.get("cuti_id") != null) {
                criteria.add(Restrictions.ilike("cutiId", "%" + (String) mapCriteria.get("cuti_id") + "%"));
            }
            if (mapCriteria.get("nip") != null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("tanggalDari",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
                criteria.add(Restrictions.between("tanggalSelesai",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggalDari",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("tanggalSelesai",mapCriteria.get("tanggal_selesai")));
                }
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("cutiPegawaiId"));

        List<ItCutiPegawaiEntity> results = criteria.list();

        return results;
    }


    public List<ItCutiPegawaiEntity> getJumlahHariCuti(String nip,String cutiId) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("cutiId", cutiId))
                .add(Restrictions.ne("cancelFlag", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getSisaCutiSys(String nip){
        List<ItCutiPegawaiEntity> listOfResult = new ArrayList<ItCutiPegawaiEntity>();
        List<Object[]> resultsCuti = new ArrayList<Object[]>();

        String query1 = "select  \n" +
                "                cuti.cuti_id, \n" +
                "                cuti.cuti_name, \n" +
                "                cuti.jumlah_cuti_harI\n" +
                "                from  \n" +
                "                im_hris_cuti cuti \n" +
                "                where \n " +
                "                cuti.cuti_id != 'CT005'";
        resultsCuti = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query1)
                .list();

        for (Object[] row : resultsCuti) {
            ItCutiPegawaiEntity result  = new ItCutiPegawaiEntity();
            result.setCutiId((String) row[0]);
            result.setCutiName((String) row[1]);
            result.setJumlahCuti(BigInteger.valueOf(Integer.valueOf(row[2].toString())));

            List<Object[]> results = new ArrayList<Object[]>();
            String query2 = "select  nip,sisa_hari_cuti\n" +
                    "        from  it_hris_cuti_pegawai cuti\n" +
                    "        WHERE nip='"+nip+"' AND cuti_id='"+result.getCutiId()+"' AND approval_flag='Y' \n" +
                    "        ORDER BY cuti_pegawai_id DESC\t\n" +
                    "        LIMIT 1\n";
            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query2)
                    .list();

            if (results.size()==0){
                result.setSisaCutiHari(BigInteger.valueOf(0));
            }else{
                for(Object[] row1:results){
                    result.setSisaCutiHari((BigInteger) row1[1]);
                }
            }
            listOfResult.add(result);
        }
        return listOfResult;
    }

    // Generate surrogate id from postgre
    public String getNextCutiPegawaiId() throws HibernateException {


        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_hris_cuti_pegawai')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "CP" + sId;
    }

    public List<ItCutiPegawaiEntity> getListCekCuti(String nip) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.isNull("approvalFlag"))
                .add(Restrictions.ne("cancelFlag", "Y"))
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getListCutiPegawai(String term) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getListCutiPegawaiById(String term) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("cutiPegawaiId",term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .list();
        return results;
    }
    //Get Sisa Cuti

    public List<ItCutiPegawaiEntity> getListSisaCutiPegawai(String term,String cutiId) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("cutiId",cutiId))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getLastCutiPegawai(String nip) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getListHistoryCutiPegawai(String term,String cutiId) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip", term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("cutiId",cutiId))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }
    public List getComboTestTanggal(String nip, String tanggalDari, String tanggalSelesai) throws HibernateException, ParseException {
        ItCutiPegawaiEntity imCutiPegawaiEntity = new ItCutiPegawaiEntity();
        String sql = "SELECT * FROM it_hris_cuti_pegawai \n" +
                "WHERE nip=:nip \n" +
                "AND flag='Y' \n" +
                "AND cancel_flag<>'Y' \n" +
                "AND tanggal_dari BETWEEN TO_TIMESTAMP(:tanggalDari,'DD/MM/YYYY') \n" +
                "AND TO_TIMESTAMP(:tanggalSelesai,'DD/MM/YYYY') \n" +
                "AND approval_flag='Y'\n" +
                "OR nip=:nip \n" +
                "AND flag='Y' \n" +
                "AND cancel_flag<>'Y' \n" +
                "AND tanggal_selesai BETWEEN TO_TIMESTAMP(:tanggalDari,'DD/MM/YYYY') \n" +
                "AND TO_TIMESTAMP(:tanggalSelesai,'DD/MM/YYYY')\n" +
                "AND approval_flag='Y'\n" ;
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

    public List<Object[]> findInfoCuti(String idCutiPegawai, String nip) throws HibernateException {
        String SQL = "SELECT  \n" +
                "                users.nip,  \n" +
                "                users.nama_pegawai,  \n" +
                "                departement.department_name,  \n" +
                "                headercuti.cuti_name,  \n" +
                "                positions.position_name,  \n" +
                "                cuti.tanggal_dari,  \n" +
                "                cuti.tanggal_selesai,  \n" +
                "                cuti.lama_hari_cuti,  \n" +
                "                cuti.pegawai_pengganti_sementara,  \n" +
                "                branches.branch_name, \n" +
                "                cuti.cuti_pegawai_id,  \n" +
                "                cuti.sisa_hari_cuti,\n" +
                "                cuti.keterangan  \n" +
                "                FROM\n" +
                "\t\t\t(SELECT * FROM it_hris_cuti_pegawai WHERE cuti_pegawai_id = :idCutiPegawai) cuti LEFT JOIN \n" +
                "\t\t\t(SELECT * FROM im_hris_pegawai WHERE nip = :nip) users ON cuti.nip = users.nip LEFT JOIN \n" +
                "\t\t\t(SELECT * FROM it_hris_pegawai_position) posisi ON posisi.nip = users.nip LEFT JOIN \n" +
                "\t\t\t(SELECT * FROM im_position) positions ON posisi.position_id = positions.position_id LEFT JOIN \n" +
                "\t\t\t(SELECT * FROM im_hris_department) departement ON positions.department_id = departement.department_id LEFT JOIN\n" +
                "\t\t\t(SELECT * FROM im_hris_cuti) headercuti ON cuti.cuti_id = headercuti.cuti_id LEFT JOIN\n" +
                "\t\t\t(SELECT * FROM im_branches) branches ON posisi.branch_id = branches.branch_id";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("idCutiPegawai", idCutiPegawai)
                .setParameter("nip", nip)
                .list();

        return results;
    }


    public List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws HibernateException {
        String SQL = "SELECT cuti_pegawai_id, users.nip, users.nama_pegawai, cuti.tanggal_dari, cuti.tanggal_selesai, branch.branch_name, cuti.last_update, CASE WHEN cuti.note_approval IS NULL THEN '-' ELSE cuti.note_approval END FROM \n" +
                "\t(SELECT * FROM it_hris_cuti_pegawai WHERE approval_id = :nip AND approval_flag = :flag ) cuti LEFT JOIN\n" +
                "\t(SELECT * FROM im_hris_pegawai) users ON cuti.nip = users.nip LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_pegawai_position) posisi ON posisi.nip = users.nip LEFT JOIN\n" +
                "\t(SELECT * FROM im_branches) branch ON posisi.branch_id = branch.branch_id\n" +
                "ORDER BY last_update DESC";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("flag", flag)
                .list();
        return results;
    }

    //Digunakan Pada payroll untuk mengambil data cuti (Jubileum)
    public List<ItCutiPegawaiEntity> getDataCutiPegawai(String nip, Timestamp awal, Timestamp akhir) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.ge("tanggalDari", awal))
                .add(Restrictions.le("tanggalSelesai", akhir))
                .add(Restrictions.ne("cancelFlag","Y"))
                .list();
        return results;
    }

    public List<ItCutiPegawaiEntity> getListCutiPegawaiZeroCutiPanjang(String nip) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.le("sisaCutiHari",BigInteger.valueOf(0)))
                .add(Restrictions.eq("cutiId","CT001"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getListCutiByNipAndTanggal(String nip , Date tanggal) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .add(Restrictions.ge("tanggalSelesai",tanggal))
                .add(Restrictions.le("tanggalDari",tanggal))
                .list();
        return results;
    }

    // cek jika ada cuti, untuk SPPD
    public List<ItCutiPegawaiEntity> cekCutiPegawaiForSPPD(String nip , Date tanggal1, Date tanggal2) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("cancelFlag","N"))
                .add(Restrictions.le("tanggalSelesai",tanggal2))
                .add(Restrictions.ge("tanggalDari",tanggal1))
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> searchCancel(String nip , Date tanggalDari,Date tanggalSelesai) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag","Y"))
                .add(Restrictions.eq("closed","Y"))
                .add(Restrictions.eq("tanggalDari",tanggalDari))
                .add(Restrictions.eq("tanggalSelesai",tanggalSelesai))
                .add(Restrictions.eq("cutiId","CT002"))
                .list();
        return results;
    }
    public List<ImBiodataEntity> getPegawaiCuti(String unit) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("flag","Y"))
                .add(Restrictions.eq("tipePegawai","TP01"))
                .add(Restrictions.isNotNull("pin"))
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getListPersonalFromNip(String nip, Date tanggal) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.le("tanggalDari",tanggal))
                .add(Restrictions.ge("tanggalSelesai",tanggal))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getListCutiPanjangNip(String nip) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .add(Restrictions.eq("cutiId","CT006"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getListTestTanggal(Date tanggal,String nip) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.le("tanggalDari",tanggal))
                .add(Restrictions.ge("tanggalSelesai",tanggal))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getSisaCuti(String nip,Date tanggalAwal,String cutiId) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.le("tanggalSelesai",tanggalAwal))
                .add(Restrictions.eq("cutiId",cutiId))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();
        return results;
    }
    public List<ItCutiPegawaiEntity> getpegawaiPlt(Date tanggal,String nip) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.le("tanggalDari",tanggal))
                .add(Restrictions.ge("tanggalSelesai",tanggal))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .add(Restrictions.isNotNull("pegawaiPenggantiSementara"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }

    public List<ItCutiPegawaiEntity> getListCutiReport(String nip,Date tanggalDari,Date tanggalSelesai) throws HibernateException {
        List<ItCutiPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItCutiPegawaiEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.ge("tanggalDari",tanggalDari))
                .add(Restrictions.le("tanggalSelesai",tanggalSelesai))
                .add(Restrictions.eq("approvalFlag","Y"))
                .add(Restrictions.ne("cancelFlag","Y"))
                .add(Restrictions.isNotNull("keterangan"))
                .addOrder(Order.asc("cutiPegawaiId"))
                .list();
        return results;
    }

    public List<ItCutiPegawaiEntity> getListCutiForApproval(Map mapCriteria) {
        List<ItCutiPegawaiEntity> listOfResult = new ArrayList<ItCutiPegawaiEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String nip = null, cutiPegawaiId = null,atasan=null;
        String searchNip = "" ;
        String searchCutiPegawaiId = "" ;
        String searchAtasan = "" ;
        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("cuti_pegawai_id") != null) {
                cutiPegawaiId = (String) mapCriteria.get("cuti_pegawai_id");
            }
            if (mapCriteria.get("nip") != null) {
                nip = (String) mapCriteria.get("nip");
            }
            if (mapCriteria.get("nip_atasan") != null) {
                atasan = (String) mapCriteria.get("nip_atasan");
            }

            if (nip!=null){
                if(!nip.equalsIgnoreCase("")){
                    searchNip = " AND cuti.nip = '" + nip + "' " ;
                }
            }
            if (cutiPegawaiId!=null){
                if(!cutiPegawaiId.equalsIgnoreCase("")){
                    searchCutiPegawaiId = " AND cuti.cuti_pegawai_id = '" + cutiPegawaiId + "' " ;
                }
            }
            if (atasan!=null){
                if(!atasan.equalsIgnoreCase("")){
                    searchAtasan = " AND notifikasi.nip = '" + atasan + "' " ;
                }
            }

            String query = "SELECT cuti.* FROM  \n" +
                    "                    ( SELECT * FROM it_hris_notifikasi ) notifikasi LEFT JOIN  \n" +
                    "                    ( SELECT * FROM it_hris_cuti_pegawai ) cuti ON notifikasi.no_request=cuti.cuti_pegawai_id" +
                    " WHERE notifikasi.tipe_notif_id='TN66' AND cuti.flag='Y' "+searchAtasan+searchNip+searchCutiPegawaiId+" ORDER BY cuti.cuti_pegawai_id DESC";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                ItCutiPegawaiEntity cutiPegawaiEntity = new ItCutiPegawaiEntity();
                cutiPegawaiEntity.setCutiPegawaiId((String) row[0]);
                cutiPegawaiEntity.setNip((String) row[1]);
                cutiPegawaiEntity.setPegawaiPenggantiSementara((String) row[2]);
                cutiPegawaiEntity.setCutiId((String) row[3]);
                cutiPegawaiEntity.setLamaHariCuti((BigInteger) row[4]);
                cutiPegawaiEntity.setSisaCutiHari((BigInteger) row[5]);
                cutiPegawaiEntity.setApprovalId((String) row[6]);
                cutiPegawaiEntity.setApprovalDate((Timestamp) row[7]);
                cutiPegawaiEntity.setApprovalFlag((String) row[8]);
                cutiPegawaiEntity.setNote((String) row[9]);
                cutiPegawaiEntity.setNoteApproval((String) row[10]);
                Timestamp tanggalDari = (Timestamp) row[11];
                Timestamp tanggalSelesai = (Timestamp) row[12];
                cutiPegawaiEntity.setTanggalDari(new Date(tanggalDari.getTime()));
                cutiPegawaiEntity.setTanggalSelesai(new Date(tanggalSelesai.getTime()));
                cutiPegawaiEntity.setFlag((String) row[13]);
                cutiPegawaiEntity.setAction((String) row[14]);
                cutiPegawaiEntity.setCreatedWho((String) row[15]);
                cutiPegawaiEntity.setLastUpdateWho((String) row[16]);
                cutiPegawaiEntity.setCreatedDate((Timestamp) row[17]);
                cutiPegawaiEntity.setLastUpdate((Timestamp) row[18]);
                cutiPegawaiEntity.setClosed((String) row[19]);
                cutiPegawaiEntity.setCancelFlag((String) row[25]);
                cutiPegawaiEntity.setCancelDate((Timestamp) row[26]);
                cutiPegawaiEntity.setCancelPerson((String) row[27]);
                cutiPegawaiEntity.setCancelNote((String) row[28]);
                cutiPegawaiEntity.setAlamatCuti((String) row[29]);
                cutiPegawaiEntity.setKeterangan((String) row[30]);

                listOfResult.add(cutiPegawaiEntity);
            }
        }
        return listOfResult;
    }

}