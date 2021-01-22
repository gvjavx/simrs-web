package com.neurix.hris.master.biodata.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.biodata.model.ImBiodataHistoryEntity;
import com.neurix.hris.master.jenisPegawai.model.JenisPegawai;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
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
        String sId = String.format("%04d", iter.next());
        return sId;
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
                "\tpegawai.flag='Y' AND tanggal_pra_pensiun<='"+tanggal6Bulan+"' AND tanggal_pensiun>='"+tanggalSekarang+"' AND posisi.flag='Y'";
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
        String query = "SELECT pegawai.nip,pegawai.nama_pegawai,pegawai.tanggal_masuk\n" +
                "FROM \n" +
                "( SELECT * FROM im_hris_pegawai) pegawai LEFT JOIN \n" +
                "( SELECT * FROM it_hris_pegawai_position) posisi ON pegawai.nip=posisi.nip\n" +
                "WHERE pegawai.flag='Y' \n" +
                "\tAND date_part('year', pegawai.tanggal_masuk+ interval '1 year')=date_part('year', CURRENT_DATE)\n" +
                "\tAND posisi.branch_id='"+ CommonUtil.userBranchLogin() +"'" +
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
                "\tAND posisi.branch_id='"+ CommonUtil.userBranchLogin() +"'" +
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
                .add(Restrictions.ne("pin",""))
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
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tpegawai.*\n" +
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
            result.setBagianId((String) row[11]);
            result.setBagianName((String) row[12]);

            result.setNip((String) row[13]);
            result.setNamaPegawai((String) row[14]);
            result.setGelarDepan((String) row[15]);
            result.setGelarBelakang((String) row[16]);
            result.setNoKtp((String) row[17]);
            result.setAlamat((String) row[18]);
            result.setRtRw((String) row[19]);
            result.setDesaId((String) row[20]);
            result.setKecamatanId((String) row[21]);
            result.setNoTelp((String) row[22]);
            result.setKotaId((String) row[23]);
            result.setProvinsiId((String) row[24]);
            result.setTanggalLahir((Date) row[25]);
            result.setTempatLahir((String) row[26]);
            result.setTipePegawai((String) row[27]);
            result.setFotoUpload((String) row[28]);
            result.setStatusCaption((String) row[29]);
            result.setKeterangan((String) row[30]);
            result.setFlag((String) row[31]);
            result.setAction((String) row[32]);
            result.setCreatedWho((String) row[33]);
            result.setLastUpdateWho((String) row[34]);
            result.setTanggalAktif((Date) row[35]);
            result.setGolongan((String) row[36]);
            result.setStatusPegawai((String) row[37]);
            result.setStatusKeluarga((String) row[38]);
            result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[39].toString())));
            result.setGender((String) row[40]);
            result.setStatusGiling((String) row[41]);
            result.setNoSkAktif((String) row[42]);
            result.setPin((String) row[43]);
            result.setPoint((int) row[44]);
            result.setZakatProfesi((String) row[45]);
            result.setTanggalPensiun((Date) row[48]);
            result.setDanaPensiun((String) row[49]);
            result.setStrukturGaji((String) row[50]);
            if(row[51] != null){
                result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[51].toString())));
            }
            result.setPoinLebih((int) row[52]);
            result.setAgama((String) row[53]);
            result.setTanggalMenikah((Date) row[54]);
            result.setNpwp((String) row[55]);
            result.setMt((String) row[56]);
            result.setTanggalAkhirKontrak((Date) row[57]);
            result.setNoAnggotaDapen((String) row[58]);
            result.setNoBpjsKetenagakerjaan((String) row[59]);
            result.setNoBpjsKetenagakerjaanPensiun((String) row[60]);
            result.setNoBpjsKesehatan((String) row[61]);

            result.setGolonganDapen((String) row[64]);
            result.setGolonganDapenNusindo((String) row[65]);
            result.setNamaBank((String) row[65]);
            result.setNoRekBank((String) row[66]);
            result.setCabangBank((String) row[68]);
            result.setTanggalMasuk((Date) row[70]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImBiodataEntity> getDataBiodata(String nip, String nama, String branchId, String divisiId, BigInteger jmlAnak, String tipePegawai, String flag){
        String searchNip = "" ;
        String searchNama = "" ;
        String searchBranchId = "" ;
        String searchDivisiId = "" ;
        String searchTipePegawai = "" ;
        String searchJmlAnak = "";

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
        if(jmlAnak!=null){
            String anak = jmlAnak.toString();
            if(!anak.equalsIgnoreCase("")) {
                searchJmlAnak = " AND pegawai.jumlah_anak = '" + anak + "' ";
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
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\titPosisi.profesi_id,\n" +
                "\tpegawai.*\n" +
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
                "\tpegawai.flag = '"+flag+"'\n" + searchNip + searchNama + searchBranchId + searchDivisiId + searchTipePegawai + searchJmlAnak +
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

        List<String> bioNIP = new ArrayList<>();

        for (Object[] row : results) {
            if(!bioNIP.contains((String) row[11])) {
                bioNIP.add((String) row[11]);

                ImBiodataEntity result = new ImBiodataEntity();
                result.setBranchId((String) row[0]);
                result.setDivisiId((String) row[1]);
                result.setPosisiId((String) row[2]);
                result.setProvinsiName((String) row[3]);
                result.setKotaName((String) row[4]);
                result.setKecamatanName((String) row[5]);
                result.setDesaName((String) row[6]);
                result.setTipePegawaiName((String) row[7]);
                result.setDivisiName((String) row[9]);
                result.setPjs((String) row[10]);

                result.setBagianId((String) row[11]);
                result.setBagianName((String) row[12]);
                result.setProfesiId((String) row[13]);

                result.setNip((String) row[14]);
                result.setNamaPegawai((String) row[15]);
                result.setGelarDepan((String) row[16]);
                result.setGelarBelakang((String) row[17]);
                result.setNoKtp((String) row[18]);
                result.setAlamat((String) row[19]);
                result.setRtRw((String) row[20]);
                result.setDesaId((String) row[21]);
                result.setKecamatanId((String) row[22]);
                result.setNoTelp((String) row[23]);

                result.setKotaId((String) row[24]);
                result.setProvinsiId((String) row[25]);
                result.setTanggalLahir((Date) row[26]);
                result.setTempatLahir((String) row[27]);
                result.setTipePegawai((String) row[28]);
                result.setFotoUpload((String) row[29]);
                result.setStatusCaption((String) row[30]);
                result.setKeterangan((String) row[31]);
                result.setFlag((String) row[32]);
                result.setAction((String) row[33]);

                result.setShift((String) row[63]);
                result.setCreatedWho((String) row[34]);
                result.setLastUpdateWho((String) row[35]);
                result.setTanggalAktif((Date) row[36]);
                result.setGolongan((String) row[37]);
                result.setStatusPegawai((String) row[38]);
                result.setStatusKeluarga((String) row[39]);
                if (row[40] != null)
                    result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[40].toString())));
                else
                    result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(0)));
                result.setGender((String) row[41]);
                result.setStatusGiling((String) row[42]);
                result.setNoSkAktif((String) row[43]);

                result.setPin((String) row[44]);
                result.setPoint((int) row[45]);
                result.setZakatProfesi((String) row[46]);
                result.setLastUpdate((Timestamp) row[47]);
                result.setCreatedDate((Timestamp) row[48]);
                result.setTanggalPensiun((Date) row[49]);
                result.setDanaPensiun((String) row[50]);
                result.setStrukturGaji((String) row[51]);
                if (row[52] != null) {
                    result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[52].toString())));
                }
                result.setPoinLebih((int) row[53]);

                result.setAgama((String) row[54]);
                result.setTanggalMenikah((Date) row[55]);
                result.setNpwp((String) row[56]);
                result.setMt((String) row[57]);
                result.setTanggalAkhirKontrak((Date) row[58]);
                result.setNoAnggotaDapen((String) row[59]);
                result.setNoBpjsKetenagakerjaan((String) row[60]);
                result.setNoBpjsKetenagakerjaanPensiun((String) row[61]);
                result.setNoBpjsKesehatan((String) row[62]);

                result.setNamaBank((String) row[66]);
                result.setNoRekBank((String) row[67]);
                result.setCabangBank((String) row[69]);

                if (row[71] != null) {
                    result.setTanggalMasuk((Date) row[71]);
                }
                if (row[72] != null) {
                    result.setGolonganDapenId(row[72].toString());
                }
                result.setMasaKerjaGolongan((Integer) row[73]);

                if (row[74] != null) {
                    result.setTanggalAkhirKontrak((Date) row[74]);
                }

                if (row[81] != null) {
                    result.setTanggalPraPensiun((Date) row[81]);
                }

                result.setFlagMess((String) row[70]);
                result.setFlagPlt((String) row[82]);
                result.setFlagPjs((String) row[83]);
                result.setFlagFingerMobile((String) row[84]);
                result.setFlagTunjRumah((String) row[85]);
                result.setFlagTunjAir((String) row[86]);
                result.setFlagTunjListrik((String) row[87]);
                result.setFlagTunjBbm((String) row[88]);
                result.setFlagBpjsKs((String) row[89]);
                result.setFlagBpjsTk((String) row[90]);
                result.setFlagPercobaan((String) row[91]);
                result.setPositionPltId((String) row[92]);
                result.setNipLama((String) row[93]);
                result.setFlagDokterKso((String) row[94]);
                result.setJenisPegawai((String) row[95]);

                if (row[96] != null) {
                    result.setPeralihanGapok(BigDecimal.valueOf(Double.parseDouble(row[96].toString())));
                }
                if (row[97] != null) {
                    result.setPeralihanSankhus(BigDecimal.valueOf(Double.parseDouble(row[97].toString())));
                }
                if (row[98] != null) {
                    result.setPeralihanTunjangan(BigDecimal.valueOf(Double.parseDouble(row[98].toString())));
                }

                result.setFlagPegawaiCutiDiluarTanggungan((String) row[100]);
                result.setTanggalCutiDiluarAwal((Date) row[101]);
                result.setTanggalCutiDiluarAkhir((Date) row[102]);

                listOfResult.add(result);
            }
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
                "\tposisi.bagian_id,\n" +
                "\tpegawai.*\n" +
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
            result.setBagianId((String) row[11]);

            result.setNip((String) row[12]);
            result.setNamaPegawai((String) row[13]);
            result.setGelarDepan((String) row[14]);
            result.setGelarBelakang((String) row[15]);
            result.setNoKtp((String) row[16]);
            result.setAlamat((String) row[17]);
            result.setRtRw((String) row[18]);
            result.setDesaId((String) row[19]);
            result.setKecamatanId((String) row[20]);
            result.setNoTelp((String) row[21]);
            result.setKotaId((String) row[22]);
            result.setProvinsiId((String) row[23]);
            result.setTanggalLahir((Date) row[24]);
            result.setTempatLahir((String) row[25]);
            result.setTipePegawai((String) row[26]);
            result.setFotoUpload((String) row[27]);
            result.setStatusCaption((String) row[28]);
            result.setKeterangan((String) row[29]);
            result.setFlag((String) row[30]);
            result.setAction((String) row[31]);
            result.setCreatedWho((String) row[32]);
            result.setLastUpdateWho((String) row[33]);
            result.setTanggalAktif((Date) row[34]);
            result.setGolongan((String) row[35]);
            result.setStatusPegawai((String) row[36]);
            result.setStatusKeluarga((String) row[37]);
            result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[38].toString())));
            result.setGender((String) row[39]);
            result.setStatusGiling((String) row[40]);
            result.setNoSkAktif((String) row[41]);
            result.setPin((String) row[42]);
            result.setPoint((int) row[43]);
            result.setZakatProfesi((String) row[44]);
            result.setTanggalPensiun((Date) row[47]);
            result.setDanaPensiun((String) row[48]);
            result.setStrukturGaji((String) row[49]);
            if(row[50]!= null){
                result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[50].toString())));
            }
            result.setPoinLebih((int) row[51]);
            result.setAgama((String) row[52]);
            result.setTanggalMenikah((Date) row[53]);
            result.setNpwp((String) row[54]);
            result.setMt((String) row[55]);
            result.setTanggalAkhirKontrak((Date) row[56]);
            result.setNoAnggotaDapen((String) row[57]);
            result.setNoBpjsKetenagakerjaan((String) row[58]);
            result.setNoBpjsKetenagakerjaanPensiun((String) row[59]);
            result.setNoBpjsKesehatan((String) row[60]);

            result.setNamaBank((String) row[64]);
            result.setNoRekBank((String) row[65]);
            result.setCabangBank((String) row[67]);

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
                "\tprovinsi_name,\n" +
                "\tkabupaten.kota_name,\n" +
                "\tkecamatan.kecamatan_name,\n" +
                "\tdesa.desa_name,\n" +
                "\ttipePegawai.tipe_pegawai_name,\n" +
                "\tdivisi.department_id,\n" +
                "\tdivisi.department_name,\n" +
                "\tpegawai.*\n" +

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
            result.setProvinsiName((String) row[3]);
            result.setKotaName((String) row[4]);
            result.setKecamatanName((String) row[5]);
            result.setDesaName((String) row[6]);
            result.setTipePegawaiName((String) row[7]);

            result.setNip((String) row[10]);
            result.setNamaPegawai((String) row[11]);
            result.setGelarDepan((String) row[12]);
            result.setGelarBelakang((String) row[13]);
            result.setNoKtp((String) row[14]);
            result.setAlamat((String) row[15]);
            result.setRtRw((String) row[16]);
            result.setDesaId((String) row[17]);
            result.setKecamatanId((String) row[18]);
            result.setNoTelp((String) row[19]);
            result.setKotaId((String) row[20]);
            result.setProvinsiId((String) row[21]);
            result.setTanggalLahir((Date) row[22]);
            result.setTempatLahir((String) row[23]);
            result.setTipePegawai((String) row[24]);
            result.setFotoUpload((String) row[25]);
            result.setStatusCaption((String) row[26]);
            result.setKeterangan((String) row[27]);
            result.setFlag((String) row[28]);
            result.setAction((String) row[29]);
            result.setCreatedWho((String) row[30]);
            result.setLastUpdateWho((String) row[31]);
            result.setTanggalAktif((Date) row[32]);
            result.setGolongan((String) row[33]);
            result.setStatusPegawai((String) row[34]);
            result.setStatusKeluarga((String) row[35]);
            result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[36].toString())));
            result.setGender((String) row[37]);
            result.setStatusGiling((String) row[38]);
            result.setNoSkAktif((String) row[39]);
            result.setPin((String) row[40]);
            result.setPoint((int) row[41]);
            result.setZakatProfesi((String) row[42]);
            result.setTanggalPensiun((Date) row[45]);
            result.setDanaPensiun((String) row[46]);
            result.setStrukturGaji((String) row[47]);
            if(row[48]!= null){
                result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[48].toString())));
            }
            result.setPoinLebih((int) row[49]);
            result.setAgama((String) row[50]);
            result.setTanggalMenikah((Date) row[51]);
            result.setNpwp((String) row[52]);
            result.setMt((String) row[53]);
            result.setTanggalAkhirKontrak((Date) row[54]);
            result.setNoAnggotaDapen((String) row[55]);
            result.setNoBpjsKetenagakerjaan((String) row[56]);
            result.setNoBpjsKetenagakerjaanPensiun((String) row[57]);
            result.setNoBpjsKesehatan((String) row[58]);

            result.setNamaBank((String) row[62]);
            result.setNoRekBank((String) row[63]);
            result.setCabangBank((String) row[65]);

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
//                .add(Restrictions.eq("tipePegawai","TP01"))
//                .add(Restrictions.isNotNull("pin"))
                .add(Restrictions.eq("flag","Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }
    //for Cuti
    public List<ImBiodataEntity> findUserCuti(String nip) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("nip",nip))
//                .add(Restrictions.eq("tipePegawai","TP01"))
//                .add(Restrictions.isNotNull("pin"))
                .add(Restrictions.eq("flag","Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }

    public List<ImBiodataEntity> getByNip(String nip) throws  HibernateException{
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tipePegawai", "TP01"))
                .add(Restrictions.isNotNull("tanggalAktif"))
                .add(Restrictions.eq("flag", "Y"))
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
                "\tpegawai.tanggal_masuk, \n" +
                "\tposisi.profesi_id\n" +
                "\ttipe.tipe_pegawai_name\n" +
                "\n" +
                "\tFROM im_hris_pegawai pegawai\n" +
                "\t\tLEFT JOIN it_hris_pegawai_position posisi \n" +
                "\t\t\tON posisi.nip = pegawai.nip\n" +
                "\t\tLEFT JOIN im_branches branch \n" +
                "\t\t\tON branch.branch_id = posisi.branch_id \n" +
                "\t\tLEFT JOIN im_position position \n" +
                "\t\t\tON position.position_id = posisi.position_id \n" +
                "\t\tLEFT JOIN im_hris_tipe_pegawai tipe \n" +
                "\t\t\tON tipe.tipe_pegawai_id = pegawai.tipe_pegawai \n" +
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
            result.setTanggalMasuk((Date) row[4]);
            result.setProfesiId((String)row[5]);
            result.setTipePegawaiName((String) row[6]);

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
                "\tpegawai.tipe_pegawai,\n" +
                "\ttipe.tipe_pegawai_name\n" +
                "\n" +
                "\tFROM im_hris_pegawai pegawai\n" +
                "\t\tLEFT JOIN it_hris_pegawai_position posisi \n" +
                "\t\t\tON posisi.nip = pegawai.nip\n" +
                "\t\tLEFT JOIN im_branches branch \n" +
                "\t\t\tON branch.branch_id = posisi.branch_id \n" +
                "\t\tLEFT JOIN im_position position \n" +
                "\t\t\tON position.position_id = posisi.position_id \n" +
                "\t\tLEFT JOIN im_hris_tipe_pegawai tipe \n" +
                "\t\t\tON tipe.tipe_pegawai_name= pegawai.tipe_pegawai\n" +
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
            result.setTipePegawaiName((String) row[5]);

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
                searchBagianId = " AND position.bagian_id = '" + bagianId + "' OR position.bagian_id = '" + bagianId + "' " ;
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
                "                   position.bagian_id,\n" +
                "                   bagian2.nama_bagian,\n" +
                "                   tipe.tipe_pegawai_name\n" +
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
                "                                ON position.bagian_id = bagian2.bagian_id\n" +
                "                                LEFT JOIN im_hris_tipe_pegawai tipe \n" +
                "                                ON tipe.tipe_pegawai_name= pegawai.tipe_pegawai\n" +
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
            result.setTipePegawaiName((String) row[11]);

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

//        String query = "SELECT   \n" +
//                "                   pegawai.nip,   \n" +
//                "                   pegawai.nama_pegawai,   \n" +
//                "                   position.position_id,   \n" +
//                "                   position.position_name,   \n" +
//                "                   pegawai.tipe_pegawai, \n" +
//                "                   branch.branch_id, \n" +
//                "                   branch.branch_name, \n" +
//                "                   bagian.bagian_id, \n" +
//                "                   bagian.nama_bagian, \n" +
//                "                   divisi.department_id, \n" +
//                "                   divisi.department_name,\n" +
//                "                   position.bagian_id,\n" +
//                "                   bagian2.nama_bagian,\n" +
//                "                   position.bagian_uang_makan,\n" +
//                "                   bagian3.nama_bagian,\n" +
//                "                   pegawai.shift\n" +
//                "                                FROM im_hris_pegawai pegawai  \n" +
//                "                                LEFT JOIN it_hris_pegawai_position posisi   \n" +
//                "                                ON posisi.nip = pegawai.nip  \n" +
//                "                                LEFT JOIN im_branches branch   \n" +
//                "                                ON branch.branch_id = posisi.branch_id   \n" +
//                "                                LEFT JOIN im_position position   \n" +
//                "                                ON position.position_id = posisi.position_id \n" +
//                "                                LEFT JOIN im_hris_position_bagian bagian \n" +
//                "                                ON position.bagian_id = bagian.bagian_id \n" +
//                "                                LEFT JOIN im_hris_department divisi \n" +
//                "                                ON position.department_id = divisi.department_id \n" +
//                "                                LEFT JOIN im_hris_position_bagian bagian2 \n" +
//                "                                ON position.bagian_id = bagian2.bagian_id\n" +
//                "                                LEFT JOIN im_hris_position_bagian bagian3 \n" +
//                "                                ON position.bagian_uang_makan = bagian3.bagian_id\n" +
//                "                                WHERE pegawai.flag = 'Y'  \n" +
//                "                                AND posisi.flag = 'Y'  \n" +searchBranchId+searchDivisiId+searchBagianId+searchNip+" ORDER BY posisi.branch_id ASC,position.department_id ASC,position.bagian_id ASC,position.position_id ASC";

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
                "                   position.bagian_id,\n" +
                "                   bagian2.nama_bagian,\n" +
                "                   pegawai.shift,\n" +
                "                   pegawai.tanggal_aktif, \n" +
                "                   tipe.tipe_pegawai_name\n" +
                "                                FROM im_hris_pegawai pegawai\n" +
                "                                LEFT JOIN it_hris_pegawai_position posisi ON posisi.nip = pegawai.nip  \n" +
                "                                LEFT JOIN im_branches branch ON branch.branch_id = posisi.branch_id   \n" +
                "                                LEFT JOIN im_position position ON position.position_id = posisi.position_id \n" +
                "                                LEFT JOIN im_hris_position_bagian bagian ON position.bagian_id = bagian.bagian_id \n" +
                "                                LEFT JOIN im_hris_department divisi ON position.department_id = divisi.department_id \n" +
                "                                LEFT JOIN im_hris_position_bagian bagian2 ON position.bagian_id = bagian2.bagian_id\n" +
                "                                LEFT JOIN im_hris_position_bagian bagian3 ON position.bagian_id = bagian3.bagian_id\n" +
                "                                LEFT JOIN im_hris_tipe_pegawai tipe ON tipe.tipe_pegawai_name = pegawai.tipe_pegawai\n" +
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
            result.setTipePegawaiName((String) row[15]);

            if (row[11] !=null){
                result.setBagianId((String) row[11]);
                result.setBagianName((String) row[12]);
            }
//            if (row[13] !=null){
//                result.setBagianId((String) row[13]);
//                result.setBagianName((String) row[14]);
//            }
            if (row[13] !=null){
                result.setShift((String) row[13]);
            }
            if (row[14] != null){
                result.setTanggalAktif((Date) row[14]);
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
                "                   position.bagian_id,\n" +
                "                   bagian2.nama_bagian,\n" +
                "                   tipe.tipe_pegawai_name \n" +
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
                "                                ON position.bagian_id = bagian2.bagian_id\n" +
                "                                LEFT JOIN im_hris_tipe_pegawai tipe \n" +
                "                                ON tipe.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
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
            result.setTipePegawaiName((String) row[13]);

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
                "\tbagian.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tpegawai.*\n" +
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

        List<String> bioNIP = new ArrayList<>();

        for (Object[] row : results) {
            if (!bioNIP.contains((String) row[11])) {
                bioNIP.add((String) row[11]);

                ImBiodataEntity result = new ImBiodataEntity();
                result.setBranchId((String) row[0]);
                result.setDivisiId((String) row[1]);
                result.setPosisiId((String) row[2]);
                result.setProvinsiName((String) row[3]);
                result.setKotaName((String) row[4]);
                result.setKecamatanName((String) row[5]);
                result.setDesaName((String) row[6]);
                result.setTipePegawaiName((String) row[7]);
                result.setPjs((String) row[10]);
                result.setBagianId((String) row[11]);
                result.setBagianName((String) row[12]);

                result.setNip((String) row[13]);
                result.setNamaPegawai((String) row[14]);
                result.setGelarDepan((String) row[15]);
                result.setGelarBelakang((String) row[16]);
                result.setNoKtp((String) row[17]);
                result.setAlamat((String) row[18]);
                result.setRtRw((String) row[19]);
                result.setDesaId((String) row[20]);
                result.setKecamatanId((String) row[21]);
                result.setNoTelp((String) row[22]);
                result.setKotaId((String) row[23]);
                result.setProvinsiId((String) row[24]);
                result.setTanggalLahir((Date) row[25]);
                result.setTempatLahir((String) row[26]);
                result.setTipePegawai((String) row[27]);
                result.setFotoUpload((String) row[28]);
                result.setStatusCaption((String) row[29]);
                result.setKeterangan((String) row[30]);
                result.setFlag((String) row[31]);
                result.setAction((String) row[32]);
                result.setCreatedWho((String) row[33]);
                result.setLastUpdateWho((String) row[34]);
                result.setTanggalAktif((Date) row[35]);
                result.setGolongan((String) row[36]);
                result.setStatusPegawai((String) row[37]);
                result.setStatusKeluarga((String) row[38]);
                if (row[39] != null)
                    result.setJumlahAnak(BigInteger.valueOf(Integer.valueOf(row[39].toString())));
                else
                    result.setJumlahAnak(BigInteger.valueOf(0));
//            result.setStatusGiling((String) row[39]);
                result.setNoSkAktif((String) row[42]);
                result.setPin((String) row[43]);
//            result.setPoint((int) row[42]);
//            result.setZakatProfesi((String) row[43]);
                result.setTanggalPensiun((Date) row[48]);
                result.setDanaPensiun((String) row[49]);
                result.setStrukturGaji((String) row[50]);
                if (row[51] != null) {
                    result.setGaji(BigDecimal.valueOf(Double.parseDouble(row[51].toString())));
                }
//            result.setPoinLebih((int) row[50]);
                result.setAgama((String) row[53]);
                result.setTanggalMenikah((Date) row[54]);
                result.setNpwp((String) row[55]);
//            result.setMt((String) row[54]);
                result.setTanggalAkhirKontrak((Date) row[57]);
                result.setNoAnggotaDapen((String) row[58]);
                result.setNoBpjsKetenagakerjaan((String) row[59]);
                result.setNoBpjsKetenagakerjaanPensiun((String) row[60]);
                result.setNoBpjsKesehatan((String) row[61]);

                result.setGolonganDapen((String) row[67]);
//            result.setGolonganDapenNusindo((String) row[59]);
                result.setNamaBank((String) row[65]);
                result.setNoRekBank((String) row[66]);
                result.setCabangBank((String) row[67]);

                result.setTanggalMasuk((Date) row[70]);
                result.setMasaKerjaGolongan((Integer) row[72]);

                listOfResult.add(result);
            }
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

    public List<ImBiodataEntity> getBiodataByGolonganId(String id) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("golongan", id))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }

    public List<ImBiodataEntity> getBiodataByTipePegawai(String id) throws HibernateException {
        List<ImBiodataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataEntity.class)
                .add(Restrictions.eq("tipePegawai", id))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }

    public Boolean checkAvailJenisPegawaiDefault(List<String> listOfJenisPegawai){

        String SQL = "SELECT jenis_pegawai_id, jenis_pegawai_name FROM im_hris_jenis_pegawai\n" +
                "WHERE jenis_pegawai_id IN :listJenisPegawai \n" +
                "AND flag_default = 'Y'\n" +
                "ORDER BY flag_default";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameterList("listJenisPegawai", listOfJenisPegawai)
                .list();

        if (results.size() > 0)
            return true;
        return false;
    }

    public List<JenisPegawai> getAllListJenisPegawai(){

        String SQL = "SELECT jenis_pegawai_id, jenis_pegawai_name, flag_default FROM im_hris_jenis_pegawai WHERE flag = 'Y' \n" +
                "ORDER BY flag_default";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<JenisPegawai> jenisPegawais = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                JenisPegawai jenisPegawai = new JenisPegawai();
                jenisPegawai.setJenisPegawaiId(obj[0].toString());
                jenisPegawai.setJenisPegawaiName(obj[1].toString());
                jenisPegawai.setFlagDefault(obj[2] == null ? "" : obj[2].toString());
                jenisPegawais.add(jenisPegawai);
            }
        }

        return jenisPegawais;
    }

    public List<Biodata> getDataPersonilForMutasi(String param, String branchId){

        if (branchId == null || "".equalsIgnoreCase(branchId))
            branchId = "%";

        String SQL = "SELECT\n" +
                "a.nip,\n" +
                "b.nama_pegawai,\n" +
                "a.position_id,\n" +
                "c.position_name,\n" +
                "a.branch_id,\n" +
                "b.golongan_id,\n" +
                "a.profesi_id,\n" +
                "b.tipe_pegawai,\n" +
                "c.department_id \n" +
                "FROM (SELECT * FROM it_hris_pegawai_position WHERE flag = 'Y') a\n" +
                "INNER JOIN (SELECT * FROM im_hris_pegawai WHERE flag = 'Y') b ON b.nip = a.nip\n" +
                "INNER JOIN im_position c ON c.position_id = a.position_id\n" +
                "WHERE a.branch_id ILIKE :unit \n" +
                "AND b.nama_pegawai ILIKE :nama ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .setParameter("nama", param)
                .list();

        List<Biodata> biodataList = new ArrayList<>();
        if (results != null && results.size() > 0){
            for (Object[] obj : results){
                Biodata biodata = new Biodata();
                biodata.setNip(obj[0].toString());
                biodata.setNamaPegawai(obj[1].toString());
                biodata.setPositionId(obj[2].toString());
                biodata.setPositionName(obj[3].toString());
                biodata.setBranch(obj[4].toString());
                biodata.setGolonganId(obj[5] == null ? null : obj[5].toString());
                biodata.setProfesiId(obj[6] == null ? null : obj[6].toString());
                biodata.setTipePegawai(obj[7] == null ? null : obj[7].toString());
                biodata.setDivisi(obj[8] == null ? null : obj[8].toString());
                biodataList.add(biodata);
            }
        }

        return biodataList;
    }

    public List<PersonilPosition> getListPersonilPositionByNip(String nip){

        String SQL = "SELECT \n" +
                "pp.position_id,\n" +
                "p.position_name,\n" +
                "pp.profesi_id,\n" +
                "pr.profesi_name, \n" +
                "pp.branch_id,\n" +
                "br.branch_name,\n" +
                "pp.jenis_pegawai,\n" +
                "jp.jenis_pegawai_name,\n" +
                "pp.flag_digaji, \n" +
                "pp.personil_position_id, \n" +
                "pp.nip, \n" +
                "p.department_id," +
                "pp.flag \n" +
                "FROM it_hris_pegawai_position pp \n" +
                "INNER JOIN im_position p ON p.position_id = pp.position_id\n" +
                "INNER JOIN im_hris_profesi_pegawai pr ON pr.profesi_id = pp.profesi_id\n" +
                "INNER JOIN im_branches br ON br.branch_id = pp.branch_id\n" +
                "INNER JOIN im_hris_jenis_pegawai jp ON jp.jenis_pegawai_id = pp.jenis_pegawai\n" +
                "WHERE pp.nip LIKE :nip \n" +
                "AND pp.flag = 'Y' ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("nip", nip)
                .list();

        List<PersonilPosition> personilPositions = new ArrayList<>();

        for (Object[] obj : results){
            PersonilPosition personilPosition = new PersonilPosition();
            personilPosition.setPositionId(obj[0].toString());
            personilPosition.setPositionName(obj[1].toString());
            personilPosition.setProfesiId(obj[2].toString());
            personilPosition.setProfesiName(obj[3].toString());
            personilPosition.setBranchId(obj[4].toString());
            personilPosition.setBranchName(obj[5].toString());
            personilPosition.setJenisPegawai(obj[6].toString());
            personilPosition.setJenisPegawaiName(obj[7].toString());
            personilPosition.setFlagDigaji(obj[8].toString());
            personilPosition.setPersonilPositionId(obj[9].toString());
            personilPosition.setNip(obj[10].toString());
            personilPosition.setDivisiId(obj[11].toString());
            personilPosition.setFlag(obj[12] == null ? "" : obj[12].toString());
            personilPositions.add(personilPosition);
        }

        return personilPositions;
    }
}