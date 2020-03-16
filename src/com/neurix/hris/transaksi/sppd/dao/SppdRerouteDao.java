
package com.neurix.hris.transaksi.sppd.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.sppd.model.ItSppdDocEntity;
import com.neurix.hris.transaksi.sppd.model.ItSppdRerouteEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
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
public class SppdRerouteDao extends GenericDao<ItSppdRerouteEntity, String> {

    @Override
    protected Class<ItSppdRerouteEntity> getEntityClass() {
        return ItSppdRerouteEntity.class;
    }

    @Override
    public List<ItSppdRerouteEntity> getByCriteria(Map mapCriteria) {
        List<ItSppdRerouteEntity> results = null;
        return results;
    }

    public String getNextSppdReroute() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_sppd_reroute')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "Route"+sId;
    }

    public List<ItSppdRerouteEntity> getListSppdReroute(String sppdId) throws HibernateException {
        List<ItSppdRerouteEntity> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "reroute.*,\n" +
                "\tkota.kota_name,\n" +
                "\tperson.person_id,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposisi.position_name,\n" +
                "\tperson.sppd_id, \n" +
                "\tperson.tanggal_kembali_realisasi,\n" +
                "\tposisi.kelompok_id\n" +
                "\tfrom\n" +
                "\tit_hris_sppd_person person\n" +
                "\tleft join it_hris_sppd_reroute reroute on reroute.sppd_person_id = person.sppd_person_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = person.person_id\n" +
                "\tleft join it_hris_pegawai_position itPegawai on itPegawai.nip = person.person_id and itPegawai.flag = 'Y'\n" +
                "\tleft join im_position posisi on posisi.position_id = itPegawai.position_id\n" +
                "\tleft join im_hris_kota kota on kota.kota_id = reroute.reroute_ke\n" +
                "where \n" +
                "\tperson.sppd_id = '"+sppdId+"'\n" +
                "\tand person.approval_sdm_flag = 'Y'\n" +
                "\tand reroute.sppd_reroute is not null\n" +
                "\tand reroute.flag = 'Y'\n" +
                "order by reroute.tanggal_reroute_ke desc";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItSppdRerouteEntity result  = new ItSppdRerouteEntity();
            result.setSppdRerouteId((String) row[0]);
            result.setSppdPersonId((String) row[1]);
            result.setTanggalRerouteKe((Date) row[2]);
            result.setRerouteKe((String) row[3]);
            result.setTanggalRerouteDari((Date) row[14]);

            result.setNip((String) row[23]);
            result.setNama((String) row[24]);
            result.setPosisiName((String) row[25]);
            result.setSppdId((String) row[26]);

            result.setTiketPergi(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setTiketKembali(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setBiayaTambahan(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setBiayaLain(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            result.setBiayaMakan(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            result.setTransportLokalBerangkat(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTransportTujuan(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setTransportLokalKembali(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
            result.setKeterangan((String) row[4]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSppdRerouteEntity> getListSppdRerouteBySppdIdNNip(String sppdId, String personId) throws HibernateException {
        List<ItSppdRerouteEntity> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "reroute.*,\n" +
                "\tkota.kota_name,\n" +
                "\tperson.person_id,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposisi.position_name,\n" +
                "\tperson.sppd_id, \n" +
                "\tperson.tanggal_kembali_realisasi,\n" +
                "\tposisi.kelompok_id,\n" +
                "\tkotaDari.kota_name\n" +
                "\tfrom\n" +
                "\tit_hris_sppd_person person\n" +
                "\tleft join it_hris_sppd_reroute reroute on reroute.sppd_person_id = person.sppd_person_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = person.person_id\n" +
                "\tleft join it_hris_pegawai_position itPegawai on itPegawai.nip = person.person_id and itPegawai.flag = 'Y'\n" +
                "\tleft join im_position posisi on posisi.position_id = itPegawai.position_id\n" +
                "\tleft join im_hris_kota kota on kota.kota_id = reroute.reroute_ke\n" +
                "\tleft join im_hris_kota kotaDari on kotaDari.kota_id = reroute.reroute_dari\n" +
                "where \n" +
                "\tperson.sppd_id = '"+sppdId+"'\n" +
                "\tand person.approval_sdm_flag = 'Y'\n" +
                "\tand reroute.sppd_person_id = '"+personId+"'\n" +
                "\tand reroute.sppd_reroute is not null\n" +
                "\tand reroute.flag = 'Y'\n" +
                "order by reroute.tanggal_reroute_ke desc";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItSppdRerouteEntity result  = new ItSppdRerouteEntity();
            result.setSppdRerouteId((String) row[0]);
            result.setSppdPersonId((String) row[1]);
            result.setTanggalRerouteKe((Date) row[2]);
            result.setRerouteKe((String) row[3]);
            result.setBerangkatVia((String) row[5]);
            result.setTanggalRerouteDari((Date) row[14]);

            result.setKota((String) row[22]);
            result.setNip((String) row[23]);
            result.setNama((String) row[24]);
            result.setPosisiName((String) row[25]);
            result.setSppdId((String) row[26]);
            result.setKotaDari((String) row[29]);

            result.setTiketPergi(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setTiketKembali(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setBiayaTambahan(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setBiayaLain(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            result.setBiayaMakan(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            result.setTransportLokalBerangkat(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTransportTujuan(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setTransportLokalKembali(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
            result.setKeterangan((String) row[4]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSppdRerouteEntity> getListSppdReroute(String sppdId, String rerouteId) throws HibernateException {
        List<ItSppdRerouteEntity> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\treroute.*,\n" +
                "\tkotaDari.kota_name as kota_dari,\n" +
                "\tkota.kota_name as kota_ke,\n" +
                "\tperson.person_id,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tposisi.position_name,\n" +
                "\tperson.sppd_id\t\n" +
                "from\n" +
                "\tit_hris_sppd_person person\n" +
                "\tleft join it_hris_sppd_reroute reroute on reroute.sppd_person_id = reroute.sppd_person_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = person.person_id\n" +
                "\tleft join it_hris_pegawai_position itPegawai on itPegawai.nip = person.person_id and itPegawai.flag = 'Y'\n" +
                "\tleft join im_position posisi on posisi.position_id = itPegawai.position_id\n" +
                "\tleft join im_hris_kota kotaDari on kotaDari.kota_id = reroute.reroute_dari\n" +
                "\tleft join im_hris_kota kota on kota.kota_id = reroute.reroute_ke\n" +
                "where \n" +
                "\tperson.sppd_id = '"+sppdId+"'\n" +
                "\tand person.approval_sdm_flag = 'Y'\n" +
                "\tand reroute.sppd_reroute = '"+rerouteId+"'\n" +
                "\tand reroute.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItSppdRerouteEntity result  = new ItSppdRerouteEntity();
            result.setSppdRerouteId((String) row[0]);
            result.setSppdPersonId((String) row[1]);
            result.setTanggalRerouteDari((Date) row[14]);
            result.setTanggalRerouteKe((Date) row[2]);
            result.setRerouteDari((String) row[13]);
            result.setRerouteKe((String) row[3]);
            result.setKotaDari((String) row[22]);
            result.setKota((String) row[23]);

            result.setKeterangan((String) row[4]);
            result.setBerangkatVia((String) row[5]);
            result.setNip((String) row[24]);
            result.setNama((String) row[25]);
            result.setPosisiName((String) row[26]);
            result.setSppdId((String) row[27]);

            result.setTransportLokalBerangkat(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTransportTujuan(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setTransportLokalKembali(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
            result.setBiayaTambahan(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setTiketPergi(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setTiketKembali(BigDecimal.valueOf(Double.valueOf(row[15].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    //mengambil tujuan reroute kemana saja
    public List<ItSppdRerouteEntity> getTujuanReroute(String sppdId) throws HibernateException {
        List<ItSppdRerouteEntity> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tkota.kota_name\n" +
                "from\n" +
                "\tit_hris_sppd sppd\n" +
                "\tleft join it_hris_sppd_person person on person.sppd_id = sppd.sppd_id\n" +
                "\tleft join it_hris_sppd_reroute reroute on reroute.sppd_person_id = person.sppd_person_id\n" +
                "\tleft join im_hris_kota kota on kota.kota_id = reroute.reroute_ke\n" +
                "where\n" +
                "\tsppd.sppd_id = '"+sppdId+"'\n" +
                "\tand reroute.flag = 'Y'\n" +
                "order by\n" +
                "\treroute.tanggal_reroute_dari";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object row : results) {
            ItSppdRerouteEntity result  = new ItSppdRerouteEntity();
            result.setKota((String) row);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItSppdRerouteEntity> getListSppdReroutePersonId(String personId) throws HibernateException {
        List<ItSppdRerouteEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdRerouteEntity.class)
                .add(Restrictions.eq("sppdPersonId", personId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

}
