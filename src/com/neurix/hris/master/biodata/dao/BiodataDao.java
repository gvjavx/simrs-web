package com.neurix.hris.master.biodata.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.biodata.model.ImBiodataHistoryEntity;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
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
public class BiodataDao extends GenericDao<ImBiodataEntity, String> {

    @Override
    protected Class<ImBiodataEntity> getEntityClass() {
        return ImBiodataEntity.class;
    }

    @Override
    public List<ImBiodataEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("nama_pegawai")!=null) {
                criteria.add(Restrictions.ilike("namaPegawai", "%" + (String)mapCriteria.get("nama_pegawai") + "%"));
            }
            if (mapCriteria.get("divisi")!=null) {
                criteria.add(Restrictions.eq("divisi", (String) mapCriteria.get("divisi")));
            }
            if (mapCriteria.get("branch")!=null) {
                criteria.add(Restrictions.eq("branch", (String) mapCriteria.get("branch")));
            }
            if (mapCriteria.get("tipe_pegawai")!=null) {
                criteria.add(Restrictions.eq("tipePegawai", (String) mapCriteria.get("tipe_pegawai")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("masa_giling")!=null) {
                criteria.add(Restrictions.eq("masaGiling", (String) mapCriteria.get("masa_giling")));
            }
            if (mapCriteria.get("from")!=null) {
                criteria.add(Restrictions.ne("tipePegawai", "TP01"));
            }
            if (mapCriteria.get("pin")!=null) {
                criteria.add(Restrictions.eq("pin", (String) mapCriteria.get("pin")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("nip"));

        List<ImBiodataEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPersonalId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "D"+sId;
    }

    public String getNextPengalamanKerja() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengalaman_kerja')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "PK"+sId;
    }

    public String getNextPersonalHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImBiodataEntity> getListPersonal() throws HibernateException {

        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public List<ImBiodataEntity> getListPersonal(String nip) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    public List<ImBiodataEntity> getListPersonalAll() throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.isNotNull("pin"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    public List<ImBiodataEntity> getListPersonalByBranch(String term, String branch) throws HibernateException {

        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
//                .add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public List<ImBiodataEntity> getAllData() throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ImBiodataEntity> getPegawaiMess() throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("flagMess", "Y"))
                .list();
        return results;
    }
    public List<ImBiodataEntity> getPegawaiPensiun6bulan(Date tanggalSekarang,Date tanggal6Bulan) throws HibernateException {
        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT pegawai.nip,pegawai.nama_pegawai ,pegawai.tanggal_pensiun\n" +
                "FROM \n" +
                "\t( SELECT * FROM im_hris_pegawai ) pegawai LEFT JOIN\n" +
                "\t( SELECT * FROM it_hris_pegawai_position ) posisi ON pegawai.nip=posisi.nip\n" +
                "WHERE\n" +
                "\tpegawai.flag='Y' AND tanggal_pensiun<='"+tanggal6Bulan+"' AND tanggal_pensiun>='"+tanggalSekarang+"' AND posisi.flag='Y'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImBiodataEntity imBiodataEntity= new ImBiodataEntity();
            imBiodataEntity.setNip((String) row[0]);
            imBiodataEntity.setNamaPegawai((String) row[1]);
            imBiodataEntity.setTanggalPensiun((Date) row[2]);

            listOfResult.add(imBiodataEntity);
        }
        return listOfResult;
    }
    public List<ImBiodataEntity> getPegawaiCutiTahunan() throws HibernateException {
        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT pegawai.nip,pegawai.nama_pegawai,pegawai.tanggal_aktif\n" +
                "FROM \n" +
                "( SELECT * FROM im_hris_pegawai) pegawai LEFT JOIN \n" +
                "( SELECT * FROM it_hris_pegawai_position) posisi ON pegawai.nip=posisi.nip\n" +
                "WHERE pegawai.flag='Y' \n" +
                "\tAND pegawai.tipe_pegawai='TP01'\n" +
//                "\tAND pegawai.tanggal_aktif+ interval '1 year'<NOW()\n" +
                "\tAND date_part('year', pegawai.tanggal_aktif+ interval '1 year')=date_part('year', CURRENT_DATE)\n" +
                "\tAND posisi.branch_id='KD01'" +
                "\tAND posisi.flag='Y'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImBiodataEntity imBiodataEntity= new ImBiodataEntity();
            imBiodataEntity.setNip((String) row[0]);
            imBiodataEntity.setNamaPegawai((String) row[1]);
            imBiodataEntity.setTanggalAktif((Date) row[2]);

            listOfResult.add(imBiodataEntity);
        }
        return listOfResult;
    }
    public List<ImBiodataEntity> getPegawaiCutiPanjang() throws HibernateException {
        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT pegawai.nip,pegawai.nama_pegawai,pegawai.tanggal_aktif\n" +
                "FROM \n" +
                "( SELECT * FROM im_hris_pegawai) pegawai LEFT JOIN \n" +
                "( SELECT * FROM it_hris_pegawai_position) posisi ON pegawai.nip=posisi.nip\n" +
                "WHERE pegawai.flag='Y' \n" +
                "\tAND pegawai.tipe_pegawai='TP01'\n" +
                "\tAND pegawai.status_pegawai='KS'\n" +
                "\tAND pegawai.tanggal_aktif+ interval '5 year'<NOW()\n" +
                "\tAND MOD(CAST(((SELECT date_part('year', CURRENT_DATE))-(SELECT date_part('year', pegawai.tanggal_aktif)))as bigint),5)=0\n" +
                "\tAND CAST(((SELECT date_part('month', CURRENT_DATE))-(SELECT date_part('month', pegawai.tanggal_aktif)))as bigint)>=0\n" +
                "\tAND CAST(((SELECT date_part('day', CURRENT_DATE))-(SELECT date_part('day', pegawai.tanggal_aktif)))as bigint)>=0\n" +
                "\tAND posisi.branch_id='KD01'" +
                "\tAND posisi.flag='Y'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImBiodataEntity imBiodataEntity= new ImBiodataEntity();
            imBiodataEntity.setNip((String) row[0]);
            imBiodataEntity.setNamaPegawai((String) row[1]);
            imBiodataEntity.setTanggalAktif((Date) row[2]);

            listOfResult.add(imBiodataEntity);
        }
        return listOfResult;
    }

    public List<ImBiodataEntity> getAbsensiPerson() throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.isNotNull("pin"))
                .list();
        return results;
    }
    public List<ImBiodataEntity> getListPersonalByBranch2(String term) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.ilike("nip",term))
                //.add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }
    public List<ImBiodataEntity> getListPersonalFromPin(String pin) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.ilike("pin",pin))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    public List<ImBiodataEntity> getListPersonalFromPinAndFlag(String pin) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.ilike("pin",pin))
                .add(Restrictions.ilike("flag","Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }

    public List<ImBiodataEntity> findBiodataLikeNip(String keyword) {
        return this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.like("nip", keyword, MatchMode.START))
                .add(Restrictions.eq("flag", "Y"))
                .list();

    }

    public List<ImBiodataEntity> findAllBiodata(String keyword) {
        return this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.like("namaPegawai", keyword, MatchMode.START).ignoreCase())
                //.add(Restrictions.like("nip", keyword, MatchMode.START))
                .list();
    }

    public List<ImBiodataEntity> findBiodataLikeName(String keyword,String branchId) {
        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String tipeWhere = "";
        if(!("").equalsIgnoreCase(branchId)){
            tipeWhere = "AND posisi.branch_id = '"+branchId+"' ";
        }
        if(!("").equalsIgnoreCase(keyword)){
            tipeWhere = "AND UPPER(pegawai.nama_pegawai) LIKE UPPER('%"+keyword+"%')";
        }

        String query = "SELECT \n" +
                "\tpegawai.nip, \n" +
                "\tpegawai.nama_pegawai, \n" +
                "\tposition.position_name, \n" +
                "\tpegawai.tipe_pegawai\n" +
                "\n" +
                "\tFROM im_hris_pegawai pegawai\n" +
                "\t\tLEFT JOIN it_hris_pegawai_position posisi \n" +
                "\t\t\tON posisi.nip = pegawai.nip\n" +
                "\t\tLEFT JOIN im_branches branch \n" +
                "\t\t\tON branch.branch_id = posisi.branch_id \n" +
                "\t\tLEFT JOIN im_position position \n" +
                "\t\t\tON position.position_id = posisi.position_id \n" +
                "\t\t\t\n" +
                "\tWHERE pegawai.flag = 'Y'\n" +
                "\t\tAND pegawai.pin IS NOT NULL\n" +
                "\t\tAND posisi.flag = 'Y' \n" +
                tipeWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImBiodataEntity result  = new ImBiodataEntity();
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);

            listOfResult.add(result);
        }
        return listOfResult;


//        return this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
//                .add(Restrictions.like("namaPegawai", keyword, MatchMode.ANYWHERE).ignoreCase())
//                .add(Restrictions.eq("flag", "Y"))
//                .list();
    }

    public List<ImBiodataEntity> getDataBiodataHris(String nip, String nama, String branchId, String divisiId, String tipePegawai, String flag, String gender){
        String searchNip = "" ;
        String searchNama = "" ;
        String searchBranchId = "" ;
        String searchDivisiId = "" ;
        String searchTipePegawai = "" ;
        String searchGender ="";

        if (gender!=null){
            if(!gender.equalsIgnoreCase("")){
                searchGender = " and pegawai.jenis_kelamin = '" + gender + "' " ;
            }
        }if (nip!=null){
            if(!nip.equalsIgnoreCase("")){
                searchNip = " and pegawai.nip = '" + nip + "' " ;
            }
        }
        if (nama!=null){
            if(!nama.equalsIgnoreCase("")){
                searchNama = " and UPPER(pegawai.nama_pegawai) like UPPER('%" + nama + "%') " ;
            }
        }
        if(branchId!=null){
            if(!branchId.equalsIgnoreCase("")){
                searchBranchId = " and itPosisi.branch_id= '" + branchId + "' " ;
            }
        }
        if (divisiId!=null){
            if(!divisiId.equalsIgnoreCase("")){
                searchDivisiId = " and posisi.department_id = '" + divisiId + "' " ;
            }
        }
        if (tipePegawai!=null){
            if(!tipePegawai.equalsIgnoreCase("")){
                searchTipePegawai = " and pegawai.tipe_pegawai= '" + tipePegawai + "' " ;
            }
        }

        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\titPosisi.branch_id,\n" +
                "\tdivisi.department_id,\n" +
                "\titPosisi.position_id,\n" +
                "\tprovinsi_name,\n" +
                "\tkabupaten.kota_name,\n" +
                "\tkecamatan.kecamatan_name,\n" +
                "\tdesa.desa_name,\n" +
                "\ttipePegawai.tipe_pegawai_name,\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name,\n" +
                "\titPosisi.pjs_flag,\n" +
                "\tpegawai.*,\n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "from\n" +
                "\tim_hris_pegawai pegawai \n" +
                "\tleft join it_hris_pegawai_position itPosisi on itPosisi.nip = pegawai.nip \n" +
                "\tleft join im_hris_provinsi provinsi on provinsi.provinsi_id = pegawai.provinsi\n" +
                "\tleft join im_hris_kota kabupaten on kabupaten.kota_id = pegawai.kabupaten\n" +
                "\tleft join im_hris_kecamatan kecamatan on kecamatan.kecamatan_id = pegawai.kecamatan\n" +
                "\tleft join im_hris_desa desa on desa.desa_id= pegawai.desa\n" +
                "\tleft join im_hris_tipe_pegawai tipePegawai on tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "\tleft join im_position posisi on posisi.position_id = itPosisi.position_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where " +
                "\t itPosisi.flag='"+flag+"' AND" +
                "\tpegawai.flag = '"+flag+"'\n" + searchNip + searchNama + searchBranchId + searchDivisiId + searchTipePegawai + searchGender +
                "\torder by \n" +
                "\titPosisi.position_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImBiodataEntity result  = new ImBiodataEntity();
            result.setBranchId((String) row[0]);
            result.setDivisiId((String) row[1]);
            result.setPosisiId((String) row[2]);
            result.setProvinsiName((String) row[3]);
            result.setKotaName((String) row[4]);
            result.setKecamatanName((String) row[5]);
            result.setDesaName((String) row[6]);
            result.setTipePegawaiName((String) row[7]);
            result.setPjs((String) row[10]);

            result.setNip((String) row[11]);
            result.setNamaPegawai((String) row[12]);
            result.setGelarDepan((String) row[13]);
            result.setGelarBelakang((String) row[14]);
            result.setNoKtp((String) row[15]);
            result.setAlamat((String) row[16]);
            result.setRtRw((String) row[17]);
            result.setDesaId((String) row[18]);
            result.setKecamatanId((String) row[19]);
            result.setNoTelp((String) row[20]);
            result.setKotaId((String) row[21]);
            result.setProvinsiId((String) row[22]);
            result.setTanggalLahir((Date) row[23]);
            result.setTempatLahir((String) row[24]);
            result.setTipePegawai((String) row[25]);
            result.setFotoUpload((String) row[26]);
            result.setStatusCaption((String) row[27]);
            result.setKeterangan((String) row[28]);
            result.setFlag((String) row[29]);
            result.setAction((String) row[30]);
            result.setCreatedWho((String) row[31]);
            result.setLastUpdateWho((String) row[32]);
            result.setTanggalAktif((Date) row[33]);
            result.setGolongan((String) row[34]);
            result.setStatusPegawai((String) row[35]);
            result.setStatusKeluarga((String) row[36]);
            result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[37].toString())));
            result.setGender((String) row[38]);
            result.setStatusGiling((String) row[39]);
            result.setNoSkAktif((String) row[40]);
            result.setPin((String) row[41]);
            result.setPoint((int) row[42]);
            result.setZakatProfesi((String) row[43]);
            result.setTanggalPensiun((Date) row[46]);
            result.setDanaPensiun((String) row[47]);
            result.setStrukturGaji((String) row[48]);
            if(row[49] != null){
                result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[49].toString())));
            }
            result.setPoinLebih((int) row[50]);
            result.setAgama((String) row[51]);
            result.setTanggalMenikah((Date) row[52]);
            result.setNpwp((String) row[53]);
            result.setMt((String) row[54]);
            result.setTanggalAkhirKontrak((Date) row[55]);
            result.setNoAnggotaDapen((String) row[56]);
            result.setNoBpjsKetenagakerjaan((String) row[57]);
            result.setNoBpjsKetenagakerjaanPensiun((String) row[58]);
            result.setNoBpjsKesehatan((String) row[59]);

            result.setGolonganDapen((String) row[61]);
            result.setGolonganDapenNusindo((String) row[62]);
            result.setNamaBank((String) row[63]);
            result.setNoRekBank((String) row[64]);
            result.setCabangBank((String) row[66]);

            result.setTanggalMasuk((Date) row[68]);
            result.setBagianId((String) row[71]);
            result.setBagianName((String) row[72]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImBiodataEntity> getDataBiodata(String nip, String nama, String branchId, String divisiId, String tipePegawai, String flag){
        String searchNip = "" ;
        String searchNama = "" ;
        String searchBranchId = "" ;
        String searchDivisiId = "" ;
        String searchTipePegawai = "" ;

        if (nip!=null){
            if(!nip.equalsIgnoreCase("")){
                searchNip = " and pegawai.nip = '" + nip + "' " ;
            }
        }
        if (nama!=null){
            if(!nama.equalsIgnoreCase("")){
                searchNama = " and UPPER(pegawai.nama_pegawai) like UPPER('%" + nama + "%') " ;
            }
        }
        if(branchId!=null){
            if(!branchId.equalsIgnoreCase("")){
                searchBranchId = " and itPosisi.branch_id= '" + branchId + "' " ;
            }
        }
        if (divisiId!=null){
            if(!divisiId.equalsIgnoreCase("")){
                searchDivisiId = " and posisi.department_id = '" + divisiId + "' " ;
            }
        }
        if (tipePegawai!=null){
            if(!tipePegawai.equalsIgnoreCase("")){
                searchTipePegawai = " and pegawai.tipe_pegawai= '" + tipePegawai + "' " ;
            }
        }

        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select DISTINCT \n" +
                "\titPosisi.branch_id,\n" +
                "\tdivisi.department_id,\n" +
                "\titPosisi.position_id,\n" +
                "\tprovinsi_name,\n" +
                "\tkabupaten.kota_name,\n" +
                "\tkecamatan.kecamatan_name,\n" +
                "\tdesa.desa_name,\n" +
                "\ttipePegawai.tipe_pegawai_name,\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name,\n" +
                "\titPosisi.pjs_flag,\n" +
                "\tpegawai.*,\n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\titPosisi.profesi_id\n" +
                "from\n" +
                "\tim_hris_pegawai pegawai \n" +
                "\tleft join it_hris_pegawai_position itPosisi on itPosisi.nip = pegawai.nip \n" +
                "\tleft join im_hris_provinsi provinsi on provinsi.provinsi_id = pegawai.provinsi\n" +
                "\tleft join im_hris_kota kabupaten on kabupaten.kota_id = pegawai.kabupaten\n" +
                "\tleft join im_hris_kecamatan kecamatan on kecamatan.kecamatan_id = pegawai.kecamatan\n" +
                "\tleft join im_hris_desa desa on desa.desa_id= pegawai.desa\n" +
                "\tleft join im_hris_tipe_pegawai tipePegawai on tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "\tleft join im_position posisi on posisi.position_id = itPosisi.position_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where " +
                "\t itPosisi.flag='"+flag+"' AND" +
                "\tpegawai.flag = '"+flag+"'\n" + searchNip + searchNama + searchBranchId + searchDivisiId + searchTipePegawai +
                "\torder by \n" +
                "\titPosisi.position_id";

//        String query = "select DISTINCT \n" +
//                "\titPosisi.branch_id,\n" +
//                "\tdivisi.department_id,\n" +
//                "\titPosisi.position_id,\n" +
//                "\tprovinsi_name,\n" +
//                "\tkabupaten.kota_name,\n" +
//                "\tkecamatan.kecamatan_name,\n" +
//                "\tdesa.desa_name,\n" +
//                "\ttipePegawai.tipe_pegawai_name,\n" +
//                "\tdivisi.department_id,\n" +
//                "\tdivisi.department_name,\n" +
//                "\titPosisi.pjs_flag,\n" +
//                "\tpegawai.*,\n" +
//                "\tbagian.bagian_id,\n" +
//                "\tbagian.nama_bagian,\n" +
//                "\titPosisi.profesi_id,\n" +
//                "\thistory.profesi_id AS history_profesi \n" +
//                "from\n" +
//                "\tim_hris_pegawai pegawai \n" +
//                "\tleft join it_hris_pegawai_position itPosisi on itPosisi.nip = pegawai.nip \n" +
//                "\tleft join im_hris_provinsi provinsi on provinsi.provinsi_id = pegawai.provinsi\n" +
//                "\tleft join im_hris_kota kabupaten on kabupaten.kota_id = pegawai.kabupaten\n" +
//                "\tleft join im_hris_kecamatan kecamatan on kecamatan.kecamatan_id = pegawai.kecamatan\n" +
//                "\tleft join im_hris_desa desa on desa.desa_id= pegawai.desa\n" +
//                "\tleft join im_hris_tipe_pegawai tipePegawai on tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
//                "\tleft join im_position posisi on posisi.position_id = itPosisi.position_id\n" +
//                "\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
//                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
//                "\tleft join imt_hris_history_jabatan_pegawai history on history.nip = pegawai.nip\n" +
//                "where " +
//                "\t itPosisi.flag='"+flag+"' AND" +
//                "\tpegawai.flag = '"+flag+"'\n" + searchNip + searchNama + searchBranchId + searchDivisiId + searchTipePegawai +
//                "\torder by \n" +
//                "\titPosisi.position_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImBiodataEntity result  = new ImBiodataEntity();
            result.setBranchId((String) row[0]);
            result.setDivisiId((String) row[1]);
            result.setPosisiId((String) row[2]);
            result.setProvinsiName((String) row[3]);
            result.setKotaName((String) row[4]);
            result.setKecamatanName((String) row[5]);
            result.setDesaName((String) row[6]);
            result.setTipePegawaiName((String) row[7]);
            result.setPjs((String) row[10]);

            result.setNip((String) row[11]);
            result.setNamaPegawai((String) row[12]);
            result.setGelarDepan((String) row[13]);
            result.setGelarBelakang((String) row[14]);
            result.setNoKtp((String) row[15]);
            result.setAlamat((String) row[16]);
            result.setRtRw((String) row[17]);
            result.setDesaId((String) row[18]);
            result.setKecamatanId((String) row[19]);
            result.setNoTelp((String) row[20]);

            result.setKotaId((String) row[21]);
            result.setProvinsiId((String) row[22]);
            result.setTanggalLahir((Date) row[23]);
            result.setTempatLahir((String) row[24]);
            result.setTipePegawai((String) row[25]);
            result.setFotoUpload((String) row[26]);
            result.setStatusCaption((String) row[27]);
            result.setKeterangan((String) row[28]);
            result.setFlag((String) row[29]);
            result.setAction((String) row[30]);

            result.setCreatedWho((String) row[31]);
            result.setLastUpdateWho((String) row[32]);
            result.setTanggalAktif((Date) row[33]);
            result.setGolongan((String) row[34]);
            result.setStatusPegawai((String) row[35]);
            result.setStatusKeluarga((String) row[36]);
            if (row[37] != null)
                result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[37].toString())));
            else
                result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(0)));
            result.setGender((String) row[38]);
            result.setStatusGiling((String) row[39]);
            result.setNoSkAktif((String) row[40]);

            result.setPin((String) row[41]);
            result.setPoint((int) row[42]);
            result.setZakatProfesi((String) row[43]);
            result.setLastUpdate((Timestamp)row[44]);
            result.setCreatedDate((Timestamp) row[45]);
            result.setTanggalPensiun((Date) row[46]);
            result.setDanaPensiun((String) row[47]);
            result.setStrukturGaji((String) row[48]);
            if(row[49] != null){
                result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[49].toString())));
            }
            result.setPoinLebih((int) row[50]);

            result.setAgama((String) row[51]);
            result.setTanggalMenikah((Date) row[52]);
            result.setNpwp((String) row[53]);
            result.setMt((String) row[54]);
            result.setTanggalAkhirKontrak((Date) row[55]);
            result.setNoAnggotaDapen((String) row[56]);
            result.setNoBpjsKetenagakerjaan((String) row[57]);
            result.setNoBpjsKetenagakerjaanPensiun((String) row[58]);
            result.setNoBpjsKesehatan((String) row[59]);

            result.setNamaBank((String) row[63]);
            result.setNoRekBank((String) row[64]);
            result.setCabangBank((String) row[66]);

            if (row[68] != null){
                result.setTanggalMasuk((Date) row[68]);
            }
            if (row[69]!=null){
                result.setGolonganDapenId(row[69].toString());
            }
            result.setMasaKerjaGolongan((Integer) row[70]);

            if(row[71] != null){
                result.setTanggalAkhirKontrak((Date)row[71]);
            }

            if (row[78] != null){
                result.setTanggalPraPensiun((Date) row[78]);
            }

            result.setFlagMess((String) row[67]);
            result.setFlagPlt((String) row[79]);
            result.setFlagPjs((String) row[80]);
            result.setFlagFingerMobile((String) row[81]);
            result.setFlagTunjRumah((String) row[82]);
            result.setFlagTunjAir((String) row[83]);
            result.setFlagTunjListrik((String) row[84]);
            result.setFlagTunjBbm((String) row[85]);
            result.setFlagBpjsKs((String) row[86]);
            result.setFlagBpjsTk((String) row[87]);
            result.setFlagPercobaan((String) row[88]);
            result.setPositionPltId((String) row[89]);
            result.setNipLama((String) row[90]);

            result.setBagianId((String) row[91]);
            result.setBagianName((String) row[92]);
            result.setProfesiId((String)row[93]);

            listOfResult.add(result);
        }
        return listOfResult;
    }
    public List<ImBiodataEntity> getPegawaiJubilium6bulan(String tanggalSekarang,String tanggal6Bulan) throws HibernateException {
        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT pegawai.nip,pegawai.nama_pegawai ,pegawai.tanggal_aktif\n" +
                "FROM \n" +
                "\t( SELECT * FROM im_hris_pegawai ) pegawai LEFT JOIN\n" +
                "\t( SELECT * FROM it_hris_pegawai_position ) posisi ON pegawai.nip=posisi.nip\n" +
                "WHERE\n" +
                "\tpegawai.flag='Y' AND pegawai.tanggal_aktif<='"+tanggal6Bulan+"' AND pegawai.tanggal_aktif>='"+tanggalSekarang+"' AND posisi.flag='Y'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImBiodataEntity imBiodataEntity= new ImBiodataEntity();
            imBiodataEntity.setNip((String) row[0]);
            imBiodataEntity.setNamaPegawai((String) row[1]);
            imBiodataEntity.setTanggalAktif((Date) row[2]);

            listOfResult.add(imBiodataEntity);
        }
        return listOfResult;
    }

    //digunakan untuk SMK
    public List<ImBiodataEntity> getDataBiodata(String keyword){

        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\titPosisi.branch_id,\n" +
                "\tdivisi.department_id,\n" +
                "\titPosisi.position_id,\n" +
                "\tprovinsi_name,\n" +
                "\tkabupaten.kota_name,\n" +
                "\tkecamatan.kecamatan_name,\n" +
                "\tdesa.desa_name,\n" +
                "\ttipePegawai.tipe_pegawai_name,\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name,\n" +
                "\titPosisi.pjs_flag,\n" +
                "\tpegawai.*,\n" +
                "\tposisi.bagian_id\n" +
                "from\n" +
                "\tim_hris_pegawai pegawai \n" +
                "\tleft join it_hris_pegawai_position itPosisi on itPosisi.nip = pegawai.nip \n" +
                "\tleft join im_hris_provinsi provinsi on provinsi.provinsi_id = pegawai.provinsi\n" +
                "\tleft join im_hris_kota kabupaten on kabupaten.kota_id = pegawai.kabupaten\n" +
                "\tleft join im_hris_kecamatan kecamatan on kecamatan.kecamatan_id = pegawai.kecamatan\n" +
                "\tleft join im_hris_desa desa on desa.desa_id= pegawai.desa\n" +
                "\tleft join im_hris_tipe_pegawai tipePegawai on tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "\tleft join im_position posisi on posisi.position_id = itPosisi.position_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "where " +
                "\tnama_pegawai ilike '%"+keyword+"%'" +
                "\torder by \n" +
                "\titPosisi.position_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImBiodataEntity result  = new ImBiodataEntity();
            result.setBranchId((String) row[0]);
            result.setDivisiId((String) row[1]);
            result.setPosisiId((String) row[2]);
            result.setProvinsiName((String) row[3]);
            result.setKotaName((String) row[4]);
            result.setKecamatanName((String) row[5]);
            result.setDesaName((String) row[6]);
            result.setTipePegawaiName((String) row[7]);
            result.setPjs((String) row[10]);

            result.setNip((String) row[11]);
            result.setNamaPegawai((String) row[12]);
            result.setGelarDepan((String) row[13]);
            result.setGelarBelakang((String) row[14]);
            result.setNoKtp((String) row[15]);
            result.setAlamat((String) row[16]);
            result.setRtRw((String) row[17]);
            result.setDesaId((String) row[18]);
            result.setKecamatanId((String) row[19]);
            result.setNoTelp((String) row[20]);
            result.setKotaId((String) row[21]);
            result.setProvinsiId((String) row[22]);
            result.setTanggalLahir((Date) row[23]);
            result.setTempatLahir((String) row[24]);
            result.setTipePegawai((String) row[25]);
            result.setFotoUpload((String) row[26]);
            result.setStatusCaption((String) row[27]);
            result.setKeterangan((String) row[28]);
            result.setFlag((String) row[29]);
            result.setAction((String) row[30]);
            result.setCreatedWho((String) row[31]);
            result.setLastUpdateWho((String) row[32]);
            result.setTanggalAktif((Date) row[33]);
            result.setGolongan((String) row[34]);
            result.setStatusPegawai((String) row[35]);
            result.setStatusKeluarga((String) row[36]);
            result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[37].toString())));
            result.setGender((String) row[38]);
            result.setStatusGiling((String) row[39]);
            result.setNoSkAktif((String) row[40]);
            result.setPin((String) row[41]);
            result.setPoint((int) row[42]);
            result.setZakatProfesi((String) row[43]);
            result.setTanggalPensiun((Date) row[46]);
            result.setDanaPensiun((String) row[47]);
            result.setStrukturGaji((String) row[48]);
            if(row[49] != null){
                result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[49].toString())));
            }
            result.setPoinLebih((int) row[50]);
            result.setAgama((String) row[51]);
            result.setTanggalMenikah((Date) row[52]);
            result.setNpwp((String) row[53]);
            result.setMt((String) row[54]);
            result.setTanggalAkhirKontrak((Date) row[55]);
            result.setNoAnggotaDapen((String) row[56]);
            result.setNoBpjsKetenagakerjaan((String) row[57]);
            result.setNoBpjsKetenagakerjaanPensiun((String) row[58]);
            result.setNoBpjsKesehatan((String) row[59]);

            result.setNamaBank((String) row[63]);
            result.setNoRekBank((String) row[64]);
            result.setCabangBank((String) row[66]);

            result.setBagianId((String) row[69]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImBiodataEntity> getDataBiodataPensiun(String tahun, String branchId, String flag){

        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\titPosisi.branch_id,\n" +
                "\tdivisi.department_id,\n" +
                "\titPosisi.position_id,\n" +
                "\tpegawai.*,\n" +
                "\tprovinsi_name,\n" +
                "\tkabupaten.kota_name,\n" +
                "\tkecamatan.kecamatan_name,\n" +
                "\tdesa.desa_name,\n" +
                "\ttipePegawai.tipe_pegawai_name,\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name,\n" +
                "\tpegawai.nama_bank,\n" +
                "\tpegawai.no_rek_bank,\n " +
                "\tpegawai.cabang_bank\n" +
                "from\n" +
                "\tim_hris_pegawai pegawai \n" +
                "\tleft join it_hris_pegawai_position itPosisi on itPosisi.nip = pegawai.nip \n" +
                "\tleft join im_hris_provinsi provinsi on provinsi.provinsi_id = pegawai.provinsi\n" +
                "\tleft join im_hris_kota kabupaten on kabupaten.kota_id = pegawai.kabupaten\n" +
                "\tleft join im_hris_kecamatan kecamatan on kecamatan.kecamatan_id = pegawai.kecamatan\n" +
                "\tleft join im_hris_desa desa on desa.desa_id= pegawai.desa\n" +
                "\tleft join im_hris_tipe_pegawai tipePegawai on tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "\tleft join im_position posisi on posisi.position_id = itPosisi.position_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "where " +
                "\tpegawai.flag = '"+flag+"'\n" +
                "\t and itPosisi.branch_id = '"+branchId+"'\n" +
                "\t AND itPosisi.flag='Y' "+
                "\tand date_part('year', pegawai.tanggal_pensiun) = '"+tahun+"' " +
                "\torder by tanggal_pensiun" ;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImBiodataEntity result  = new ImBiodataEntity();
            result.setBranchId((String) row[0]);
            result.setDivisiId((String) row[1]);
            result.setPosisiId((String) row[2]);
            result.setNip((String) row[3]);
            result.setNamaPegawai((String) row[4]);
            result.setGelarDepan((String) row[5]);
            result.setGelarBelakang((String) row[6]);
            result.setNoKtp((String) row[7]);
            result.setAlamat((String) row[8]);
            result.setRtRw((String) row[9]);
            result.setDesaId((String) row[10]);
            result.setKecamatanId((String) row[11]);
            result.setNoTelp((String) row[12]);
            result.setKotaId((String) row[13]);
            result.setProvinsiId((String) row[14]);
            result.setTanggalLahir((Date) row[15]);
            result.setTempatLahir((String) row[16]);
            result.setTipePegawai((String) row[17]);
            result.setFotoUpload((String) row[18]);
            result.setStatusCaption((String) row[19]);
            result.setKeterangan((String) row[20]);
            result.setFlag((String) row[21]);
            result.setAction((String) row[22]);
            result.setCreatedWho((String) row[23]);
            result.setLastUpdateWho((String) row[24]);
            result.setTanggalAktif((Date) row[25]);
            result.setGolongan((String) row[26]);
            result.setStatusPegawai((String) row[27]);
            result.setStatusKeluarga((String) row[28]);
            result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[29].toString())));
            result.setGender((String) row[30]);
            result.setStatusGiling((String) row[31]);
            result.setNoSkAktif((String) row[32]);
            result.setPin((String) row[33]);
            result.setPoint((int) row[34]);
            result.setZakatProfesi((String) row[35]);
            result.setTanggalPensiun((Date) row[38]);
            result.setDanaPensiun((String) row[39]);
            result.setStrukturGaji((String) row[40]);
            if(row[41] != null){
                result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[41].toString())));
            }
            result.setPoinLebih((int) row[42]);
            result.setAgama((String) row[43]);
            result.setTanggalMenikah((Date) row[44]);
            result.setNpwp((String) row[45]);
            result.setMt((String) row[46]);
            result.setTanggalAkhirKontrak((Date) row[47]);
            result.setNoAnggotaDapen((String) row[48]);
            result.setNoBpjsKetenagakerjaan((String) row[49]);
            result.setNoBpjsKetenagakerjaanPensiun((String) row[50]);
            result.setNoBpjsKesehatan((String) row[51]);

            result.setProvinsiName((String) row[59]);
            result.setKotaName((String) row[60]);
            result.setKecamatanName((String) row[61]);
            result.setDesaName((String) row[62]);
            result.setTipePegawaiName((String) row[63]);
            result.setNamaBank((String) row[66]);
            result.setNoRekBank((String) row[67]);
            result.setCabangBank((String) row[68]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public void addAndSaveHistory(ImBiodataHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    //for Cuti
    public List<ImBiodataEntity> findAllUserCuti() throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("tipePegawai","TP01"))
                .add(Restrictions.isNotNull("pin"))
                .add(Restrictions.eq("flag","Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    //for Cuti
    public List<ImBiodataEntity> findUserCuti(String nip) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.eq("tipePegawai","TP01"))
                .add(Restrictions.isNotNull("pin"))
                .add(Restrictions.eq("flag","Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }

    public List<ImBiodataEntity> getBiodataByUnit(String branchId){
        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String tipeWhere = "";
        if(!("").equalsIgnoreCase(branchId)){
            tipeWhere = "AND posisi.branch_id = '"+branchId+"' ";
        }

        String query = "SELECT \n" +
                "\tpegawai.nip, \n" +
                "\tpegawai.nama_pegawai, \n" +
                "\tpegawai.tipe_pegawai,\n" +
                "\tposisi.position_id,\n" +
                "\tpegawai.golongan_id,\n" +
                "\tpegawai.point,\n" +
                "\tpegawai.poin_lebih\n" +
                "\n" +
                "\tFROM im_hris_pegawai pegawai\n" +
                "\t\tLEFT JOIN it_hris_pegawai_position posisi \n" +
                "\t\t\tON posisi.nip = pegawai.nip\n" +
                "\t\tLEFT JOIN im_branches branch \n" +
                "\t\t\tON branch.branch_id = posisi.branch_id \n" +
                "\t\tLEFT JOIN im_position position \n" +
                "\t\t\tON position.position_id = posisi.position_id \n" +
                "\t\t\t\n" +
                "\tWHERE pegawai.flag = 'Y'\n" +
                "\t\tAND posisi.flag = 'Y' \n" +
                "\t\tand pegawai.golongan_id is not null\n" +
                "\t\tand pegawai.golongan_id != ''\n" +
                tipeWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImBiodataEntity result  = new ImBiodataEntity();
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setTipePegawai((String) row[2]);
            result.setPosisiId((String) row[3]);
            result.setGolongan((String) row[4]);
            result.setPoint((int) row[5]);
            result.setPoinLebih((int) row[6]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<Biodata> getBiodataByUnitAndNip(String branchId,String nip){
        List<Biodata> listOfResult = new ArrayList<Biodata>();
        List<Object[]> results = new ArrayList<Object[]>();
        String tipeWhere = "";
        if(!("").equalsIgnoreCase(branchId)){
            tipeWhere = "AND posisi.branch_id = '"+branchId+"' ";
        }else if(!("").equalsIgnoreCase(nip)){
            tipeWhere = "AND pegawai.nip = '"+nip+"' ";
        }

        String query = "SELECT \n" +
                "\tpegawai.nip, \n" +
                "\tpegawai.nama_pegawai, \n" +
                "\tposition.position_name, \n" +
                "\tpegawai.tipe_pegawai, \n" +
                "\tposisi.profesi_id\n" +
                "\n" +
                "\tFROM im_hris_pegawai pegawai\n" +
                "\t\tLEFT JOIN it_hris_pegawai_position posisi \n" +
                "\t\t\tON posisi.nip = pegawai.nip\n" +
                "\t\tLEFT JOIN im_branches branch \n" +
                "\t\t\tON branch.branch_id = posisi.branch_id \n" +
                "\t\tLEFT JOIN im_position position \n" +
                "\t\t\tON position.position_id = posisi.position_id \n" +
                "\t\t\t\n" +
                "\tWHERE pegawai.flag = 'Y'\n" +
                "\t\tAND pegawai.pin IS NOT NULL\n" +
                "\t\tAND posisi.flag = 'Y' \n" +
                tipeWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            Biodata result  = new Biodata();
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setTipePegawai((String) row[3]);
            result.setProfesiId((String)row[4]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public Biodata getBiodataKabagSdm(String branchId){
        Biodata result  = new Biodata();
        List<Object[]> results = new ArrayList<Object[]>();
        String tipeWhere = "";
        if(!("").equalsIgnoreCase(branchId)){
            tipeWhere = "AND posisi.branch_id = '"+branchId+"' ";
        }

        String query = "SELECT \n" +
                "\tpegawai.nip, \n" +
                "\tpegawai.nama_pegawai, \n" +
                "\tposition.position_name, \n" +
                "\tpegawai.tipe_pegawai\n" +
                "\n" +
                "\tFROM im_hris_pegawai pegawai\n" +
                "\t\tLEFT JOIN it_hris_pegawai_position posisi \n" +
                "\t\t\tON posisi.nip = pegawai.nip\n" +
                "\t\tLEFT JOIN im_branches branch \n" +
                "\t\t\tON branch.branch_id = posisi.branch_id \n" +
                "\t\tLEFT JOIN im_position position \n" +
                "\t\t\tON position.position_id = posisi.position_id \n" +
                "\t\t\t\n" +
                "\tWHERE pegawai.flag = 'Y'\n" +
                "\t\tAND pegawai.pin IS NOT NULL\n" +
                "\t\tAND posisi.flag = 'Y' \n" +
                "\t\tAND posisi.position_id = '24' \n" +
                tipeWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setPositionName((String) row[2]);
        }
        return result;
    }
    public Biodata getBiodataDirektur(String branchId){
        Biodata result  = new Biodata();
        List<Object[]> results = new ArrayList<Object[]>();
        String tipeWhere = "";
        if(!("").equalsIgnoreCase(branchId)){
            tipeWhere = "AND posisi.branch_id = '"+branchId+"' ";
        }

        String query = "SELECT \n" +
                "\tpegawai.nip, \n" +
                "\tpegawai.nama_pegawai, \n" +
                "\tposition.position_name, \n" +
                "\tpegawai.tipe_pegawai\n" +
                "\n" +
                "\tFROM im_hris_pegawai pegawai\n" +
                "\t\tLEFT JOIN it_hris_pegawai_position posisi \n" +
                "\t\t\tON posisi.nip = pegawai.nip\n" +
                "\t\tLEFT JOIN im_branches branch \n" +
                "\t\t\tON branch.branch_id = posisi.branch_id \n" +
                "\t\tLEFT JOIN im_position position \n" +
                "\t\t\tON position.position_id = posisi.position_id \n" +
                "\t\t\t\n" +
                "\tWHERE pegawai.flag = 'Y'\n" +
                "\t\tAND pegawai.pin IS NOT NULL\n" +
                "\t\tAND posisi.flag = 'Y' \n" +
                "\t\tAND posisi.position_id = '1' \n" +
                tipeWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setPositionName((String) row[2]);
        }
        return result;
    }
    public List<Biodata> getBiodataByNip(String nip){
        List<Biodata> listOfResult = new ArrayList<Biodata>();
        List<Object[]> results = new ArrayList<Object[]>();
        String tipeWhere = "";
        if(!("").equalsIgnoreCase(nip)){
            tipeWhere = "AND pegawai.nip = '"+nip+"' ";
        }

        String query = "SELECT \n" +
                "\tpegawai.nip, \n" +
                "\tpegawai.nama_pegawai, \n" +
                "\tposition.position_name, \n" +
                "\tposition.kelompok_id, \n" +
                "\tpegawai.tipe_pegawai\n" +
                "\n" +
                "\tFROM im_hris_pegawai pegawai\n" +
                "\t\tLEFT JOIN it_hris_pegawai_position posisi \n" +
                "\t\t\tON posisi.nip = pegawai.nip\n" +
                "\t\tLEFT JOIN im_branches branch \n" +
                "\t\t\tON branch.branch_id = posisi.branch_id \n" +
                "\t\tLEFT JOIN im_position position \n" +
                "\t\t\tON position.position_id = posisi.position_id \n" +
                "\t\t\t\n" +
                "\tWHERE pegawai.flag = 'Y'\n" +
                "\t\tAND pegawai.pin IS NOT NULL\n" +
                "\t\tAND posisi.flag = 'Y' \n" +
                tipeWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            Biodata result  = new Biodata();
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setKelompokId((String) row[3]);
            result.setTipePegawai((String) row[4]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<Biodata> getBiodataListForAbsensi(String branchId,String divisiId,String bagianId,String nip){
        List<Biodata> listOfResult = new ArrayList<Biodata>();
        List<Object[]> results = new ArrayList<Object[]>();
        String searchBranchId = "" ;
        String searchDivisiId = "" ;
        String searchBagianId = "" ;
        String searchNip = "" ;

        if (branchId!=null){
            if(!branchId.equalsIgnoreCase("")){
                searchBranchId = " AND posisi.branch_id = '" + branchId + "' " ;
            }
        }
        if (divisiId!=null){
            if(!divisiId.equalsIgnoreCase("")){
                searchDivisiId = " AND position.department_id = '" + divisiId + "' " ;
            }
        }
        if (bagianId!=null){
            if(!bagianId.equalsIgnoreCase("")){
                searchBagianId = " AND position.bagian_id = '" + bagianId + "' OR position.bagian_asli_id = '" + bagianId + "' " ;
            }
        }
        if (nip!=null){
            if(!nip.equalsIgnoreCase("")){
                searchNip = " AND pegawai.nip = '" + nip + "' " ;
            }
        }

        String query = "SELECT   \n" +
                "                   pegawai.nip,   \n" +
                "                   pegawai.nama_pegawai,   \n" +
                "                   position.position_id,   \n" +
                "                   position.position_name,   \n" +
                "                   pegawai.tipe_pegawai, \n" +
                "                   branch.branch_id, \n" +
                "                   branch.branch_name, \n" +
                "                   bagian.bagian_id, \n" +
                "                   bagian.nama_bagian, \n" +
                "                   divisi.department_id, \n" +
                "                   divisi.department_name,\n" +
                "                   position.bagian_asli_id,\n" +
                "                   bagian2.nama_bagian\n" +
                "                                FROM im_hris_pegawai pegawai  \n" +
                "                                LEFT JOIN it_hris_pegawai_position posisi   \n" +
                "                                ON posisi.nip = pegawai.nip  \n" +
                "                                LEFT JOIN im_branches branch   \n" +
                "                                ON branch.branch_id = posisi.branch_id   \n" +
                "                                LEFT JOIN im_position position   \n" +
                "                                ON position.position_id = posisi.position_id \n" +
                "                                LEFT JOIN im_hris_position_bagian bagian \n" +
                "                                ON position.bagian_id = bagian.bagian_id \n" +
                "                                LEFT JOIN im_hris_department divisi \n" +
                "                                ON position.department_id = divisi.department_id \n" +
                "                                LEFT JOIN im_hris_position_bagian bagian2 \n" +
                "                                ON position.bagian_asli_id = bagian2.bagian_id\n" +
                "                                WHERE pegawai.flag = 'Y'  \n" +
                "                                AND posisi.flag = 'Y'  \n" +searchBranchId+searchDivisiId+searchBagianId+searchNip+" ORDER BY posisi.branch_id ASC,position.department_id ASC,position.bagian_id ASC,position.position_id ASC";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Biodata result  = new Biodata();
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setPositionId((String) row[2]);
            result.setPositionName((String) row[3]);
            result.setTipePegawai((String) row[4]);
            result.setBranch((String) row[5]);
            result.setBranchName((String) row[6]);
            result.setBagianId((String) row[7]);
            result.setBagianName((String) row[8]);
            result.setDivisi((String) row[9]);
            result.setDivisiName((String) row[10]);

            if (row[11] !=null){
                result.setBagianId((String) row[11]);
                result.setBagianName((String) row[12]);
            }
            listOfResult.add(result);
        }
        return listOfResult;
    }
    public List<Biodata> getBiodataListForUangMakan(String branchId,String divisiId,String bagianId,String nip){
        List<Biodata> listOfResult = new ArrayList<Biodata>();
        List<Object[]> results = new ArrayList<Object[]>();
        String searchBranchId = "" ;
        String searchDivisiId = "" ;
        String searchBagianId = "" ;
        String searchNip = "" ;

        if (branchId!=null){
            if(!branchId.equalsIgnoreCase("")){
                searchBranchId = " AND posisi.branch_id = '" + branchId + "' " ;
            }
        }
        if (divisiId!=null){
            if(!divisiId.equalsIgnoreCase("")){
                searchDivisiId = " AND position.department_id = '" + divisiId + "' " ;
            }
        }
        if (bagianId!=null){
            if(!bagianId.equalsIgnoreCase("")){
                searchBagianId = " AND position.bagian_id = '" + bagianId + "' OR position.bagian_asli_id = '" + bagianId + "' OR position.bagian_uang_makan = '" + bagianId + "' " ;
            }
        }
        if (nip!=null){
            if(!nip.equalsIgnoreCase("")){
                searchNip = " AND pegawai.nip = '" + nip + "' " ;
            }
        }

        String query = "SELECT   \n" +
                "                   pegawai.nip,   \n" +
                "                   pegawai.nama_pegawai,   \n" +
                "                   position.position_id,   \n" +
                "                   position.position_name,   \n" +
                "                   pegawai.tipe_pegawai, \n" +
                "                   branch.branch_id, \n" +
                "                   branch.branch_name, \n" +
                "                   bagian.bagian_id, \n" +
                "                   bagian.nama_bagian, \n" +
                "                   divisi.department_id, \n" +
                "                   divisi.department_name,\n" +
                "                   position.bagian_asli_id,\n" +
                "                   bagian2.nama_bagian,\n" +
                "                   position.bagian_uang_makan,\n" +
                "                   bagian3.nama_bagian,\n" +
                "                   pegawai.shift\n" +
                "                                FROM im_hris_pegawai pegawai  \n" +
                "                                LEFT JOIN it_hris_pegawai_position posisi   \n" +
                "                                ON posisi.nip = pegawai.nip  \n" +
                "                                LEFT JOIN im_branches branch   \n" +
                "                                ON branch.branch_id = posisi.branch_id   \n" +
                "                                LEFT JOIN im_position position   \n" +
                "                                ON position.position_id = posisi.position_id \n" +
                "                                LEFT JOIN im_hris_position_bagian bagian \n" +
                "                                ON position.bagian_id = bagian.bagian_id \n" +
                "                                LEFT JOIN im_hris_department divisi \n" +
                "                                ON position.department_id = divisi.department_id \n" +
                "                                LEFT JOIN im_hris_position_bagian bagian2 \n" +
                "                                ON position.bagian_asli_id = bagian2.bagian_id\n" +
                "                                LEFT JOIN im_hris_position_bagian bagian3 \n" +
                "                                ON position.bagian_uang_makan = bagian3.bagian_id\n" +
                "                                WHERE pegawai.flag = 'Y'  \n" +
                "                                AND posisi.flag = 'Y'  \n" +searchBranchId+searchDivisiId+searchBagianId+searchNip+" ORDER BY posisi.branch_id ASC,position.department_id ASC,position.bagian_id ASC,position.position_id ASC";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            boolean add = false;
            Biodata result  = new Biodata();
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setPositionId((String) row[2]);
            result.setPositionName((String) row[3]);
            result.setTipePegawai((String) row[4]);
            result.setBranch((String) row[5]);
            result.setBranchName((String) row[6]);
            result.setBagianId((String) row[7]);
            result.setBagianName((String) row[8]);
            result.setDivisi((String) row[9]);
            result.setDivisiName((String) row[10]);

            if (row[11] !=null){
                result.setBagianId((String) row[11]);
                result.setBagianName((String) row[12]);
            }
            if (row[13] !=null){
                result.setBagianId((String) row[13]);
                result.setBagianName((String) row[14]);
            }
            if (row[15] !=null){
                result.setShift((String) row[15]);
            }
            if (!bagianId.equalsIgnoreCase((""))){
                if (result.getBagianId().equalsIgnoreCase(bagianId)){
                    add=true;
                }
            }else{
                add=true;
            }
            if (add){
                listOfResult.add(result);
            }
        }
        return listOfResult;
    }
    public List<Biodata> getBiodataListDefault(String branchId,String divisiId,String bagianId,String nip){
        List<Biodata> listOfResult = new ArrayList<Biodata>();
        List<Object[]> results = new ArrayList<Object[]>();
        String searchBranchId = "" ;
        String searchDivisiId = "" ;
        String searchBagianId = "" ;
        String searchNip = "" ;

        if (branchId!=null){
            if(!branchId.equalsIgnoreCase("")){
                searchBranchId = " AND posisi.branch_id = '" + branchId + "' " ;
            }
        }
        if (divisiId!=null){
            if(!divisiId.equalsIgnoreCase("")){
                searchDivisiId = " AND position.department_id = '" + divisiId + "' " ;
            }
        }
        if (bagianId!=null){
            if(!bagianId.equalsIgnoreCase("")){
                searchBagianId = " AND position.bagian_id = '" + bagianId + "' OR position.bagian_asli_id = '" + bagianId + "' " ;
            }
        }
        if (nip!=null){
            if(!nip.equalsIgnoreCase("")){
                searchNip = " AND pegawai.nip = '" + nip + "' " ;
            }
        }

        String query = "SELECT   \n" +
                "                   pegawai.nip,   \n" +
                "                   pegawai.nama_pegawai,   \n" +
                "                   position.position_id,   \n" +
                "                   position.position_name,   \n" +
                "                   pegawai.tipe_pegawai, \n" +
                "                   branch.branch_id, \n" +
                "                   branch.branch_name, \n" +
                "                   bagian.bagian_id, \n" +
                "                   bagian.nama_bagian, \n" +
                "                   divisi.department_id, \n" +
                "                   divisi.department_name,\n" +
                "                   position.bagian_asli_id,\n" +
                "                   bagian2.nama_bagian\n" +
                "                                FROM im_hris_pegawai pegawai  \n" +
                "                                LEFT JOIN it_hris_pegawai_position posisi   \n" +
                "                                ON posisi.nip = pegawai.nip  \n" +
                "                                LEFT JOIN im_branches branch   \n" +
                "                                ON branch.branch_id = posisi.branch_id   \n" +
                "                                LEFT JOIN im_position position   \n" +
                "                                ON position.position_id = posisi.position_id \n" +
                "                                LEFT JOIN im_hris_position_bagian bagian \n" +
                "                                ON position.bagian_id = bagian.bagian_id \n" +
                "                                LEFT JOIN im_hris_department divisi \n" +
                "                                ON position.department_id = divisi.department_id \n" +
                "                                LEFT JOIN im_hris_position_bagian bagian2 \n" +
                "                                ON position.bagian_asli_id = bagian2.bagian_id\n" +
                "                                WHERE pegawai.flag = 'Y'  \n" +
                "                                AND posisi.flag = 'Y'  \n" +searchBranchId+searchDivisiId+searchBagianId+searchNip+" ORDER BY posisi.branch_id ASC,position.department_id ASC,position.bagian_id ASC,position.position_id ASC";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Biodata result  = new Biodata();
            result.setNip((String) row[0]);
            result.setNamaPegawai((String) row[1]);
            result.setPositionId((String) row[2]);
            result.setPositionName((String) row[3]);
            result.setTipePegawai((String) row[4]);
            result.setBranch((String) row[5]);
            result.setBranchName((String) row[6]);
            result.setBagianId((String) row[7]);
            result.setBagianName((String) row[8]);
            result.setDivisi((String) row[9]);
            result.setDivisiName((String) row[10]);

            listOfResult.add(result);
        }
        return listOfResult;
    }
    //for Cuti
    public List<ImBiodataEntity> getListPersonalSatpam() throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("shift","Y"))
                .add(Restrictions.isNotNull("pin"))
                .add(Restrictions.eq("flag","Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    public List<ImBiodataEntity> getAllBiodata(String nip, String nama, String branchId, String divisiId, String tipePegawai, String flag){
        String searchNip = "" ;
        String searchNama = "" ;
        String searchBranchId = "" ;
        String searchDivisiId = "" ;
        String searchTipePegawai = "" ;

        if (nip!=null){
            if(!nip.equalsIgnoreCase("")){
                searchNip = " and pegawai.nip = '" + nip + "' " ;
            }
        }
        if (nama!=null){
            if(!nama.equalsIgnoreCase("")){
                searchNama = " UPPER(pegawai.nama_pegawai) like UPPER('%" + nama + "%') " ;
            }
        }
        if(branchId!=null){
            if(!branchId.equalsIgnoreCase("")){
                searchBranchId = " and itPosisi.branch_id= '" + branchId + "' " ;
            }
        }
        if (divisiId!=null){
            if(!divisiId.equalsIgnoreCase("")){
                searchDivisiId = " and posisi.department_id = '" + divisiId + "' " ;
            }
        }
        if (tipePegawai!=null){
            if(!tipePegawai.equalsIgnoreCase("")){
                searchTipePegawai = " and pegawai.tipe_pegawai= '" + tipePegawai + "' " ;
            }
        }

        List<ImBiodataEntity> listOfResult = new ArrayList<ImBiodataEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\titPosisi.branch_id,\n" +
                "\tdivisi.department_id,\n" +
                "\titPosisi.position_id,\n" +
                "\tprovinsi_name,\n" +
                "\tkabupaten.kota_name,\n" +
                "\tkecamatan.kecamatan_name,\n" +
                "\tdesa.desa_name,\n" +
                "\ttipePegawai.tipe_pegawai_name,\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name,\n" +
                "\titPosisi.pjs_flag,\n" +
                "\tpegawai.*,\n" +
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "from\n" +
                "\tim_hris_pegawai pegawai \n" +
                "\tleft join it_hris_pegawai_position itPosisi on itPosisi.nip = pegawai.nip \n" +
                "\tleft join im_hris_provinsi provinsi on provinsi.provinsi_id = pegawai.provinsi\n" +
                "\tleft join im_hris_kota kabupaten on kabupaten.kota_id = pegawai.kabupaten\n" +
                "\tleft join im_hris_kecamatan kecamatan on kecamatan.kecamatan_id = pegawai.kecamatan\n" +
                "\tleft join im_hris_desa desa on desa.desa_id= pegawai.desa\n" +
                "\tleft join im_hris_tipe_pegawai tipePegawai on tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "\tleft join im_position posisi on posisi.position_id = itPosisi.position_id\n" +
                "\tleft join im_hris_department divisi on divisi.department_id = posisi.department_id\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where " +
                searchNama + searchNip + searchBranchId + searchDivisiId + searchTipePegawai +
                "\torder by \n" +
                "\titPosisi.position_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImBiodataEntity result  = new ImBiodataEntity();
            result.setBranchId((String) row[0]);
            result.setDivisiId((String) row[1]);
            result.setPosisiId((String) row[2]);
            result.setProvinsiName((String) row[3]);
            result.setKotaName((String) row[4]);
            result.setKecamatanName((String) row[5]);
            result.setDesaName((String) row[6]);
            result.setTipePegawaiName((String) row[7]);
            result.setPjs((String) row[10]);

            result.setNip((String) row[11]);
            result.setNamaPegawai((String) row[12]);
            result.setGelarDepan((String) row[13]);
            result.setGelarBelakang((String) row[14]);
            result.setNoKtp((String) row[15]);
            result.setAlamat((String) row[16]);
            result.setRtRw((String) row[17]);
            result.setDesaId((String) row[18]);
            result.setKecamatanId((String) row[19]);
            result.setNoTelp((String) row[20]);
            result.setKotaId((String) row[21]);
            result.setProvinsiId((String) row[22]);
            result.setTanggalLahir((Date) row[23]);
            result.setTempatLahir((String) row[24]);
            result.setTipePegawai((String) row[25]);
            result.setFotoUpload((String) row[26]);
            result.setStatusCaption((String) row[27]);
            result.setKeterangan((String) row[28]);
            result.setFlag((String) row[29]);
            result.setAction((String) row[30]);
            result.setCreatedWho((String) row[31]);
            result.setLastUpdateWho((String) row[32]);
            result.setTanggalAktif((Date) row[33]);
            result.setGolongan((String) row[34]);
            result.setStatusPegawai((String) row[35]);
            result.setStatusKeluarga((String) row[36]);
            if (row[37] != null)
                result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[37].toString())));
            else
                result.setJumlahAnak(BigInteger.valueOf(0));
            result.setGender((String) row[38]);
            result.setStatusGiling((String) row[39]);
            result.setNoSkAktif((String) row[40]);
            result.setPin((String) row[41]);
            result.setPoint((int) row[42]);
            result.setZakatProfesi((String) row[43]);
            result.setTanggalPensiun((Date) row[46]);
            result.setDanaPensiun((String) row[47]);
            result.setStrukturGaji((String) row[48]);
            if(row[49] != null){
                result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[49].toString())));
            }
            result.setPoinLebih((int) row[50]);
            result.setAgama((String) row[51]);
            result.setTanggalMenikah((Date) row[52]);
            result.setNpwp((String) row[53]);
            result.setMt((String) row[54]);
            result.setTanggalAkhirKontrak((Date) row[55]);
            result.setNoAnggotaDapen((String) row[56]);
            result.setNoBpjsKetenagakerjaan((String) row[57]);
            result.setNoBpjsKetenagakerjaanPensiun((String) row[58]);
            result.setNoBpjsKesehatan((String) row[59]);

            result.setGolonganDapen((String) row[65]);
            result.setGolonganDapenNusindo((String) row[59]);
            result.setNamaBank((String) row[63]);
            result.setNoRekBank((String) row[64]);
            result.setCabangBank((String) row[66]);

            result.setTanggalMasuk((Date) row[68]);
            result.setMasaKerjaGolongan((Integer) row[70]);
            result.setBagianId((String) row[72]);
            result.setBagianName((String) row[73]);

            listOfResult.add(result);
        }
        return listOfResult;
    }
    public List<ImBiodataEntity> getAbsensiPersonShift(String shift) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("shift", shift))
                .add(Restrictions.isNotNull("pin"))
                .list();
        return results;
    }

    //for typeahead
    public List<ImBiodataEntity> getBiodataListByLike(String key) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("namaPegawai", "%" + key + "%"),
                        Restrictions.ilike("nip", "%" + key + "%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("nip"));

        List<ImBiodataEntity> results = criteria.list();
        return results;
    }

    //tambahan reza
    public List<Biodata> searchBiodataUser(String positionId) throws HibernateException{
        List<Biodata> biodataList = new ArrayList<>();

        String query = "SELECT nip, position_plt_id FROM im_hris_pegawai WHERE position_plt_id = :positionPltId AND flag = 'Y'";

        List<Object[]> results = new ArrayList<Object[]>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("positionPltId", positionId)
                .list();


        for (Object[] row : results) {
            Biodata result  = new Biodata();
            result.setNip((String) row[0]);
            result.setPositionPltId((String) row[1]);

            biodataList.add(result);
        }
        return biodataList;
    }


    //tambahan reza
    public List<Biodata> searchBiodataByCriteria(String nip) throws HibernateException{
        List<Biodata> biodataList = new ArrayList<>();

        String query = "SELECT nip, position_plt_id FROM im_hris_pegawai WHERE nip = :nip AND flag = 'Y'";

        List<Object[]> results = new ArrayList<Object[]>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("nip", nip)
                .list();


        for (Object[] row : results) {
            Biodata result  = new Biodata();
            result.setNip((String) row[0]);
            result.setPositionPltId((String) row[1]);

            biodataList.add(result);
        }
        return biodataList;
    }
}