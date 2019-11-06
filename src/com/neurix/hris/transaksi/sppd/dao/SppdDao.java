
package com.neurix.hris.transaksi.sppd.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.sppd.model.ImSppdEntity;
import com.neurix.hris.transaksi.sppd.model.ItSppdPersonEntity;
import com.neurix.hris.transaksi.sppd.model.Sppd;
import com.neurix.hris.transaksi.sppd.model.SppdPerson;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.security.Timestamp;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SppdDao extends GenericDao<ImSppdEntity, String> {

    @Override
    protected Class<ImSppdEntity> getEntityClass() {
        return ImSppdEntity.class;
    }

    @Override
    public List<ImSppdEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSppdEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("sppd_id")!=null) {
                criteria.add(Restrictions.eq("sppdId", (String) mapCriteria.get("sppd_id")));
            }

            if (mapCriteria.get("dinas")!=null) {
                criteria.add(Restrictions.eq("dinas", (String) mapCriteria.get("dinas")));
            }

            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("no_surat")!=null) {
                criteria.add(Restrictions.ilike("noSurat", "%" + (String)mapCriteria.get("no_surat") + "%"));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }

            if (mapCriteria.get("tanggal_sppd_berangkat")!= null && mapCriteria.get("tanggal_sppd_kembali")!=null) {
                criteria.add(Restrictions.ge("tanggalSppdBerangkat", (Date) mapCriteria.get("tanggal_sppd_berangkat")));
                criteria.add(Restrictions.le("tanggalSppdKembali", (Date) mapCriteria.get("tanggal_sppd_kembali")));
            }


        }

        // Order by
        criteria.addOrder(Order.desc("createdDate"));

        List<ImSppdEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSppdId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_sppd')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return sId;
    }

    public String getNextSppdHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_sppd_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImSppdEntity> getListSppd(String term) throws HibernateException {
        List<ImSppdEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSppdEntity.class)
                .add(Restrictions.eq("sppdId",term))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<Object[]> findInfoSppd(String idSppd, String nip) throws HibernateException {

        String query = "SELECT\n" +
                "\tsppd.sppd_id,\n" +
                "    users.user_id,\n" +
                "    users.user_name,\n" +
                "\tbranches.branch_name,\n" +
                "    positions.position_name,\n" +
                "\tdepartement.department_name,\n" +
                "    berangkat.kota_name berangkat,\n" +
                "    tujuan.kota_name tujuan,\n" +
                "    sppdperson.berangkat_via,\n" +
                "    sppdperson.pulang_via,\n" +
                "    sppd.tugas_sppd,\n" +
                "    sppdperson.penginapan,\n" +
                "    sppd.tanggal_sppd_berangkat,\n" +
                "    sppd.tanggal_sppd_kembali\n" +
                "FROM\n" +
                "    (SELECT * FROM it_hris_sppd) sppd LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_sppd_person) sppdperson ON sppd.sppd_id = sppdperson.sppd_id LEFT JOIN\n" +
                "\t(SELECT * FROM im_users) users ON sppdperson.person_id = users.user_id LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_pegawai_position) posisi ON users.user_id = posisi.nip LEFT JOIN\n" +
                "    (SELECT * FROM im_position) positions ON posisi.position_id = positions.position_id LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_department) departement ON positions.department_id = departement.department_id LEFT JOIN\n" +
                "    (SELECT * FROM im_branches) branches ON sppd.branch_id = branches.branch_id LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_kota) berangkat ON sppdperson.berangkat_dari = berangkat.kota_id LEFT JOIN\n" +
                "    (SELECT * FROM im_hris_kota) tujuan ON sppdperson.tujuan_ke = tujuan.kota_id\n" +
                "WHERE sppd.sppd_id = :idSppd AND sppdperson.person_id = :nip";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("idSppd", idSppd)
                .setParameter("nip", nip)
                .list();

        return results;
    }

    public List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws HibernateException {
        String SQL = "SELECT \n" +
                "\tsppd.sppd_id, \n" +
                "    sppdperson.person_id, \n" +
                "    sppdperson.person_name, \n" +
                "    sppdperson.tanggal_berangkat, \n" +
                "    sppdperson.tanggal_kembali, \n" +
                "    branch.branch_name, \n" +
                "    sppdperson.last_update\n" +
                "FROM\n" +
                "    (SELECT * FROM it_hris_sppd) sppd LEFT JOIN\n" +
                "    (SELECT * FROM it_hris_sppd_person) sppdperson ON sppd.sppd_id = sppdperson.sppd_id LEFT JOIN\n" +
                "    (SELECT * FROM im_users) users ON sppdperson.approval_id = users.user_id LEFT JOIN\n" +
                "    (SELECT * FROM im_branches) branch ON sppdperson.branch_id = branch.branch_id\n" +
                "WHERE sppdperson.approval_id = :nip AND sppdperson.approval_flag = :flag ORDER BY sppdperson.last_update DESC";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQL)
                .setParameter("nip", nip)
                .setParameter("flag", flag)
                .list();

        return results;
    }

    public List<SppdPerson> anggotaSppdDao(String sppdId){

        List<SppdPerson> listOfResult = new ArrayList<SppdPerson>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "  sppd.sppd_id AS sppdId,\n" +
                "  sppd.tugas_sppd,\n" +
                "  sppd.no_surat,\n" +
                "  sppd.dinas,\n" +
                "  person.person_id,\n" +
                "  person.person_name,\n" +
                "  person.branch_id,\n" +
                "  branch.branch_name,\n" +
                "  person.divisi_id,\n" +
                "  person.position_id,\n" +
                "  posisi.position_name,\n" +
                "  person.berangkat_via,\n" +
                "  person.pulang_via,\n" +
                "  person.berangkat_via,\n" +
                "  person.pulang_via,\n" +
                "  kota2.kota_name as berangkat_dari,\n" +
                "  kota1.kota_name as tujuan_ke,\n" +
                "  person.tipe_person,\n" +
                "  person.penginapan,\n" +
                "  divisi.department_name,\n" +
                "  to_char(person.tanggal_berangkat, 'dd-MM-yyyy') AS tanggal_berangkat,\n" +
                "  to_char(person.tanggal_kembali, 'dd-MM-yyyy') AS tanggal_kembali,\n" +
                "  CASE\n" +
                "    WHEN person.pejabat_sementara IS NULL THEN '-'\n" +
                "    ELSE person.pejabat_sementara\n" +
                "  END AS pejabat_sementara\n" +
                "FROM it_hris_sppd sppd\n" +
                "LEFT JOIN it_hris_sppd_person person\n" +
                "  ON person.sppd_id = sppd.sppd_id\n" +
                "LEFT JOIN im_hris_kota kota1\n" +
                "  ON person.tujuan_ke = kota1.kota_id\n" +
                "LEFT JOIN im_hris_kota kota2\n" +
                "  ON person.berangkat_dari = kota2.kota_id\n" +
                "LEFT JOIN im_branches branch\n" +
                "  ON person.branch_id = branch.branch_id\n" +
                "LEFT JOIN im_position posisi\n" +
                "  ON person.position_id = posisi.position_id\n" +
                "LEFT JOIN im_hris_department divisi\n" +
                "  ON person.divisi_id = divisi.department_id\n" +
                "where\n" +
                "\tsppd.sppd_id = '"+sppdId+"'\n" +
                "\tand person.approval_sdm_id is not null\n" +
                "\tand person.approval_sdm_flag = 'Y'\n" +
                "\tand person.tipe_person = 'Anggota'" +
                "\torder by person.position_id" ;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            SppdPerson result  = new SppdPerson();
            result.setPersonId((String) row[5]);
            result.setPersonName((String) row[10]);

            listOfResult.add(result);
        }
        return listOfResult;
    }


    public List<ItSppdPersonEntity> getListSppdPerson(String term) throws HibernateException {

        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.ilike("sppdName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("sppdId"))
                .list();
        return results;
    }
    public List<ItSppdPersonEntity> getListSppdByNipAndTanggal(String nip , java.sql.Date tanggal) throws HibernateException {
        List<ItSppdPersonEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdPersonEntity.class)
                .add(Restrictions.eq("personId", nip))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.eq("approvalSdmFlag", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.ge("tanggalKembali",tanggal))
                .add(Restrictions.le("tanggalBerangkat",tanggal))
                .list();
        return results;
    }

    public List<Sppd> getSppdTahun(String tahun, String bulan){
        List<Sppd> listOfResult = new ArrayList<Sppd>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tcount(sppd_id)\n" +
                "from\n" +
                "\tit_hris_sppd\n" +
                "where\n" +
                "\tclosed = 'Y'\n" +
                "\tand flag = 'Y'\n" +
                "\tand EXTRACT(MONTH FROM tanggal_sppd_berangkat) = "+ bulan+"\n" +
                "\tand EXTRACT(YEAR FROM tanggal_sppd_berangkat) = "+tahun;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object row : results) {
            Sppd result  = new Sppd();
            result.setSppdId(row + "");

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // get Sppd by bulan dan divisi
    public List<Sppd> getSppdTahun(String tahun, String bulan, String divisi){
        List<Sppd> listOfResult = new ArrayList<Sppd>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tdivisi.department_name,\n" +
                "\tCASE \n" +
                "\t      WHEN data.jumlah is null \n" +
                "\t      THEN 0\n" +
                "\t      else data.jumlah\n" +
                "\tend as jumlah\n" +
                "from\n" +
                "\tim_hris_department divisi\n" +
                "\tleft join (select\n" +
                "\t\t\tdivisi.department_id,\n" +
                "\t\t\tdivisi.department_name,\n" +
                "\t\t\tcount(person.divisi_id) as jumlah\n" +
                "\t\tfrom\n" +
                "\t\t\tit_hris_sppd sppd\n" +
                "\t\t\tleft join it_hris_sppd_person person on person.sppd_id = sppd.sppd_id\n" +
                "\t\t\tleft join im_hris_department divisi on divisi.department_id = person.divisi_id\n" +
                "\t\twhere\n" +
                "\t\t\tclosed = 'Y'\n" +
                "\t\t\tand sppd.flag = 'Y'\n" +
                "\t\t\tand EXTRACT(MONTH FROM sppd.tanggal_sppd_berangkat) = "+bulan+"\n" +
                "\t\t\tand EXTRACT(YEAR FROM sppd.tanggal_sppd_berangkat) = "+tahun+"\n" +
                "\t\t\tand person.divisi_id = '"+divisi+"'\n" +
                "\t\tgroup by\n" +
                "\t\t\tdivisi.department_id,\n" +
                "\t\t\tdivisi.department_name\n" +
                "\t\torder by\n" +
                "\t\t\tdivisi.department_name ) data on data.department_id = divisi.department_id\n" +
                "where\n" +
                "\tdivisi.department_id = '"+divisi+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Sppd result  = new Sppd();
            result.setDivisiName((String) row[0]);
            result.setDivisiId(row[1] + "");

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // get Sppd by bulan dan bagian
    public List<Sppd> getSppdTahunByBagian(String tahun, String bulan, String bagian){
        List<Sppd> listOfResult = new ArrayList<Sppd>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tCASE \n" +
                "\t      WHEN data.jumlah is null \n" +
                "\t      THEN 0\n" +
                "\t      else data.jumlah\n" +
                "\tend as jumlah \n" +
                "from\n" +
                "\tim_hris_position_bagian bagian\n" +
                "\tleft join (select\n" +
                "\t\t\tposisi.bagian_id,\n" +
                "\t\t\tbagian.nama_bagian,\n" +
                "\t\t\tcount(person.divisi_id) as jumlah\n" +
                "\t\tfrom\n" +
                "\t\t\tit_hris_sppd sppd\n" +
                "\t\t\tleft join it_hris_sppd_person person on person.sppd_id = sppd.sppd_id\n" +
                "\t\t\tleft join im_hris_department divisi on divisi.department_id = person.divisi_id\n" +
                "\t\t\tleft join im_position posisi on posisi.position_id = person.position_id\n" +
                "\t\t\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\t\twhere\n" +
                "\t\t\tclosed = 'Y'\n" +
                "\t\t\tand sppd.flag = 'Y'\n" +
                "\t\t\tand EXTRACT(MONTH FROM sppd.tanggal_sppd_berangkat) = "+bulan+"\n" +
                "\t\t\tand EXTRACT(YEAR FROM sppd.tanggal_sppd_berangkat) = "+tahun+"\n" +
                "\t\t\tand posisi.bagian_id = '"+bagian+"'\n" +
                "\t\tgroup by\n" +
                "\t\t\tposisi.bagian_id,\n" +
                "\t\t\tbagian.nama_bagian\n" +
                "\t\torder by\n" +
                "\t\t\tposisi.bagian_id ) data on data.bagian_id = bagian.bagian_id\n" +
                "where\n" +
                "\tbagian.bagian_id = '"+bagian+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Sppd result  = new Sppd();
            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setSppdId(row[2] + "");

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // get Sppd by tahun dan divisi
    public List<Sppd> getSppdTahunDivisi(String tahun, String bulan){
        List<Sppd> listOfResult = new ArrayList<Sppd>();
        List<Object[]> results = new ArrayList<Object[]>();
        String qryBulan = "";

        if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "\tand EXTRACT(MONTH FROM sppd.tanggal_sppd_berangkat) = "+bulan+ "\n";
        }

        String query = "select\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name,\n" +
                "\tcount(person.divisi_id)\n" +
                "from\n" +
                "\tit_hris_sppd sppd\n" +
                "\tleft join it_hris_sppd_person person on person.sppd_id = sppd.sppd_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = person.divisi_id\n" +
                "where\n" +
                "\tclosed = 'Y'\n" +
                "\tand sppd.flag = 'Y'\n" +
                "\tand EXTRACT(YEAR FROM sppd.tanggal_sppd_berangkat) = "+tahun+"\n" + qryBulan +
                "group by\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name\n" +
                "order by\n" +
                "\tdivisi.department_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Sppd result  = new Sppd();
            result.setSppdId((String) row[0]);
            result.setDivisiName((String) row[1]);
            result.setDivisiId(row[2] + "");

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // get Sppd by tahun dan bagian
    public List<Sppd> getSppdTahunBagian(String tahun, String bulan){
        List<Sppd> listOfResult = new ArrayList<Sppd>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";

        if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "\tand EXTRACT(MONTH FROM sppd.tanggal_sppd_berangkat) = "+bulan+ "\n";
        }

        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount(person.divisi_id)\n" +
                "from\n" +
                "\tit_hris_sppd sppd\n" +
                "\tleft join it_hris_sppd_person person on person.sppd_id = sppd.sppd_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = person.divisi_id\n" +
                "\tleft join im_position posisi on posisi.position_id = person.position_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where\n" +
                "\tclosed = 'Y'\n" +
                "\tand sppd.flag = 'Y'\n" +
                "\tand EXTRACT(YEAR FROM sppd.tanggal_sppd_berangkat) = "+tahun+"\n" + qryBulan +
                "group by\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "order by\n" +
                "\tposisi.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Sppd result  = new Sppd();
            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setSppdId(row[2] + "");

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<Sppd> getSppdTahunBagian(String tahun, String bulan, String divisiId){
        List<Sppd> listOfResult = new ArrayList<Sppd>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";

        if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "\tand EXTRACT(MONTH FROM sppd.tanggal_sppd_berangkat) = "+bulan+ "\n";
        }

        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount(person.divisi_id)\n" +
                "from\n" +
                "\tit_hris_sppd sppd\n" +
                "\tleft join it_hris_sppd_person person on person.sppd_id = sppd.sppd_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = person.divisi_id\n" +
                "\tleft join im_position posisi on posisi.position_id = person.position_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where\n" +
                "\tclosed = 'Y'\n" +
                "\tand sppd.flag = 'Y'\n" +
                "\tand person.divisi_id = '"+divisiId+"'\n" +
                "\tand EXTRACT(YEAR FROM sppd.tanggal_sppd_berangkat) = "+tahun+"\n" + qryBulan +
                "group by\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "order by\n" +
                "\tposisi.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Sppd result  = new Sppd();
            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);
            result.setSppdId(row[2] + "");

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // get Sppd by tahun dan nip
    public List<Sppd> getSppdTahunPerson(String tahun, String bulan, String divisiId){
        List<Sppd> listOfResult = new ArrayList<Sppd>();
        List<Object[]> results = new ArrayList<Object[]>();

        String qryBulan = "";
        String qryDivisiId = "";

        if(!bulan.equalsIgnoreCase("-")){
            qryBulan = "\tand EXTRACT(MONTH FROM sppd.tanggal_sppd_berangkat) = "+bulan+ "\n";
        }

        if(!divisiId.equalsIgnoreCase("")){
            qryDivisiId = "\tand person.divisi_id = '"+divisiId+ "'\n";
        }

        String query = "select\n" +
                "\tperson.person_id,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tcount(distinct sppd.sppd_id) as jumlah,\n" +
                "\tcount(tanggal.id_sppd_tanggal) as jumlahTanggal\n" +
                "from\n" +
                "\tit_hris_sppd sppd\n" +
                "\tleft join it_hris_sppd_person person on person.sppd_id = sppd.sppd_id\n" +
                "\tleft join it_hris_sppd_tanggal tanggal on tanggal.sppd_person_id = person.sppd_person_id and tanggal.flag = 'Y'\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = person.divisi_id\n" +
                "\tleft join im_position posisi on posisi.position_id = person.position_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = person.person_id\n" +
                "where\n" +
                "\tclosed = 'Y'\n" +
                "\tand sppd.flag = 'Y'\n" +
                "\tand EXTRACT(YEAR FROM sppd.tanggal_sppd_berangkat) = "+tahun+"\n" + qryBulan + qryDivisiId +
                "group by\n" +
                "\tperson.person_id,\n" +
                "\tpegawai.nama_pegawai\n" +
                "order by \n" +
                "\tjumlah desc,\n" +
                "\tjumlahTanggal desc\n" +
                "\tlimit 10";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Sppd result  = new Sppd();
            result.setPersonNip((String) row[0]);
            result.setPersonName((String) row[1]);
            result.setSppdId(row[2] + "");
            result.setBranchId(row[3] + "");

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // Bagian untuk SPPD
    public List<Sppd> getBagian(){
        List<Sppd> listOfResult = new ArrayList<Sppd>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tdistinct\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "from\n" +
                "\tit_hris_sppd sppd\n" +
                "\tleft join it_hris_sppd_person person on person.sppd_id = sppd.sppd_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = person.divisi_id\n" +
                "\tleft join im_position posisi on posisi.position_id = person.position_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where\n" +
                "\tclosed = 'Y'\n" +
                "\tand sppd.flag = 'Y'\n" +
                "order by\n" +
                "\tbagian.nama_bagian\n";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Sppd result  = new Sppd();
            result.setBagianId((String) row[0]);
            result.setBagianName((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // Cek Dokumen Training SPPD
    public Sppd getDokumenTraining(String sppdId) {
        Sppd sppd = new Sppd();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tsppd.tugas_sppd,\n" +
                "\tperson.training_id,\n" +
                "\tdoc.training_doc_id\n" +
                "from\n" +
                "\tit_hris_sppd_person person\n" +
                "\tleft join it_hris_training_doc doc on doc.training_id = person.training_id\n" +
                "\tleft join it_hris_sppd sppd on sppd.sppd_id = person.sppd_id\n" +
                "where\n" +
                "\tperson.sppd_id ='" + sppdId + "'\n" +
                "\tand person.tipe_person = 'Ketua'\n" +
                "\tand person.training_id is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        String hasil = "-";
        for (Object[] row : results) {
            sppd.setTugasSppd((String) row[0]);
            sppd.setIdTraining((String) row[1]);
            sppd.setSppdId((String) row[2]);
        }

        return sppd;
    }

        // cek realisasi sppd untuk absensi
        public List<SppdPerson> getRealisasiSPPD(String nip , java.sql.Date tanggal){
            List<SppdPerson> listOfResult = new ArrayList<SppdPerson>();
            List<Object[]> results = new ArrayList<Object[]>();
            String query = "SELECT\n" +
                    "\tperson.sppd_person_id,\n" +
                    "\tperson.person_id\n" +
                    "FROM\n" +
                    "\tit_hris_sppd_person person LEFT JOIN \n" +
                    "\tit_hris_sppd_tanggal tanggal ON person.sppd_person_id = tanggal.sppd_person_id\n" +
                    "WHERE\n" +
                    "\tperson.person_id='"+nip+"' AND\n" +
                    "\tperson.approval_flag='Y' AND\n" +
                    "\tperson.approval_sdm_flag='Y' AND\n" +
                    "\tperson.flag='Y' AND\n" +
                    "\ttanggal.sppd_tanggal='"+tanggal+"' AND\n" +
                    "\ttanggal.sppd_tanggal_approve = 'Y' AND\n" +
                    "\ttanggal.flag='Y'\n" +
                    "\t";
            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();
            for (Object[] row : results) {
            SppdPerson result  = new SppdPerson();
            result.setSppdPersonId((String) row[0]);
            result.setPersonId((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;
    }
}
