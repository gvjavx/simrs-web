package com.neurix.hris.master.strukturJabatan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanHistoryEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
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
public class StrukturJabatanDao extends GenericDao<ImStrukturJabatanEntity, String> {

    @Override
    protected Class<ImStrukturJabatanEntity> getEntityClass() {
        return ImStrukturJabatanEntity.class;
    }

    @Override
    public List<ImStrukturJabatanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImStrukturJabatanEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("struktur_jabatan_id")!=null) {
                criteria.add(Restrictions.eq("strukturJabatanId", (String) mapCriteria.get("struktur_jabatan_id")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("level")!=null) {
                criteria.add(Restrictions.eq("level", mapCriteria.get("level")));
            }



        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("strukturJabatanId"));

        List<ImStrukturJabatanEntity> results = criteria.list();

        return results;
    }

    public List<ImStrukturJabatanEntity> getByCriteriaNotif(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImStrukturJabatanEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by

        List<ImStrukturJabatanEntity> results = criteria.list();

        Criteria criteria2=this.sessionFactory.getCurrentSession().createCriteria(ImStrukturJabatanEntity.class);
        if(results != null){
            for (ImStrukturJabatanEntity imStrukturJabatanEntity: results) {
                if(!imStrukturJabatanEntity.getFlag().equals("N")){
                    if (!("-").equalsIgnoreCase(imStrukturJabatanEntity.getParentId())){
                        String[] parts = imStrukturJabatanEntity.getParentId().split("-");
                        criteria2.add(Restrictions.eq("strukturJabatanId", (String) parts[0]));
                        criteria2.add(Restrictions.eq("flag", "Y"));
                    }
                }
            }
        }

        // Order by
        //criteria2.addOrder(Order.asc("subParent"));
        results = criteria2.list();

        return results;
    }

    public List<ImStrukturJabatanEntity> getPegawaiLevel(String positionId, String branchId) throws HibernateException {
        List<ImStrukturJabatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStrukturJabatanEntity.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public List<ImStrukturJabatanEntity> get2UpLevel(Long level, String branchId) throws HibernateException {
        List<ImStrukturJabatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStrukturJabatanEntity.class)
                .add(Restrictions.eq("level", level))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextStrukturJabatanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_struktur_jabatan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return "SJ"+sId;
    }

    public String getNextStrukturJabatanHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_struktur_jabatan_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());
        return "HJ"+sId;
    }

    public List<ImStrukturJabatanEntity> getListStrukturJabatan(String term) throws HibernateException {
        List<ImStrukturJabatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStrukturJabatanEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("strukturJabatanId"))
                .list();

        return results;
    }

    /*public List<ImStrukturJabatanEntity> getParent(String branchId, String positionId) throws HibernateException {
        List<ImStrukturJabatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStrukturJabatanEntity.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }*/

    public void addAndSaveHistory(ImStrukturJabatanHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public StrukturJabatan searchStrukturRelation(String id) throws HibernateException{

        String strukturId = id;
        String query = "";

        if (id == ""){
            strukturId = "%";
        } else {
            strukturId = id;
        }

        query = "SELECT a.nip, a.nama_pegawai, c.parent_id,c.position_id FROM im_hris_pegawai a\n" +
                "INNER JOIN it_hris_pegawai_position b on b.nip = a.nip\n" +
                "INNER JOIN im_hris_struktur_jabatan c on c.position_id = b.position_id\n" +
                "WHERE a.nip = :strukturId";

        List<Object[]> results = new ArrayList<Object[]>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("strukturId", strukturId)
                .list();


        StrukturJabatan result  = new StrukturJabatan();
        for (Object[] row : results) {
            result.setNip((String) row[0]);
            result.setName((String) row[1]);
            result.setParentId((String) row[2]);
            result.setPositionId((String) row[3]);
        }
        return result;
    }
    /*public StrukturJabatan searchKabidByBagian(String bagian) throws HibernateException{

        String query = "SELECT pegawai.nip,pegawai.nama_pegawai\n" +
                "\tFROM \n" +
                "\t\t( SELECT * FROM im_hris_pegawai ) pegawai LEFT JOIN\n" +
                "\t\t( SELECT * FROM it_hris_pegawai_position WHERE flag='Y') position ON pegawai.nip=position.nip LEFT JOIN\n" +
                "\t\t( SELECT * FROM im_hris_struktur_jabatan WHERE flag='Y' AND branch_id='KD01') jabatan ON position.position_id=jabatan.position_id LEFT JOIN\n" +
                "\t\t( SELECT * FROM im_position WHERE flag='Y') posisi ON posisi.position_id=position.position_id LEFT JOIN\n" +
                "\t\t( SELECT * FROM im_hris_position_bagian WHERE flag='Y') bagian ON bagian.divisi_id=posisi.department_id\n" +
                "\tWHERE \n" +
                "\t\tjabatan.level=2 AND\n" +
                "\t\tposition.branch_id='KD01' AND\n" +
                "\t\tposisi.bagian_id=:bagian";

        List<Object[]> results = new ArrayList<Object[]>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("bagian", bagian)
                .list();


        StrukturJabatan result  = new StrukturJabatan();
        for (Object[] row : results) {
            result.setNip((String) row[0]);
        }
        return result;
    }*/
    public List<StrukturJabatan> searchStrukturRelation2(String id,String branchId) throws HibernateException{
        List<StrukturJabatan> strukturJabatanList = new ArrayList<>();
        String strukturId = id;
        String unit = branchId;
        String query = "";

        if (id .equalsIgnoreCase("")){
            strukturId = "%";
        } else {
            strukturId = id;
        }

        query = "SELECT itPosisi.nip,\n" +
                "       pegawai.nama_pegawai,\n" +
                "       struktur.parent_id\n" +
                "FROM it_hris_pegawai_position itPosisi\n" +
                "LEFT JOIN im_hris_pegawai pegawai ON pegawai.nip = itPosisi.nip\n" +
                "LEFT JOIN im_hris_struktur_jabatan struktur ON struktur.position_id = itPosisi.position_id\n" +
                "AND struktur.branch_id = itPosisi.branch_id\n" +
                "WHERE itPosisi.branch_id = :branchId\n" +
                "  AND itPosisi.nip =:strukturId\n" +
                "  AND itPosisi.flag='Y'\n" +
                "  AND struktur.flag='Y'";

        List<Object[]> results = new ArrayList<Object[]>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("strukturId", strukturId)
                .setParameter("branchId",unit)
                .list();


        for (Object[] row : results) {
            StrukturJabatan result  = new StrukturJabatan();
            result.setNip((String) row[0]);
            result.setName((String) row[1]);
            result.setParentId((String) row[2]);

            strukturJabatanList.add(result);
        }
        return strukturJabatanList;
    }

    //tambahan reza
    public List<StrukturJabatan> searchStrukturRelationUser(String strukturJabatanid,String branchId) throws HibernateException{
        List<StrukturJabatan> strukturJabatanList = new ArrayList<>();

//        String query = "SELECT itPosisi.nip,\n" +
//                "       pegawai.nama_pegawai,\n" +
//                "       struktur.parent_id\n" +
//                "FROM it_hris_pegawai_position itPosisi\n" +
//                "LEFT JOIN im_hris_pegawai pegawai ON pegawai.nip = itPosisi.nip\n" +
//                "LEFT JOIN im_hris_struktur_jabatan struktur ON struktur.position_id = itPosisi.position_id\n" +
//                "AND struktur.branch_id = itPosisi.branch_id\n" +
//                "WHERE itPosisi.branch_id = :branchId\n" +
//                "  AND itPosisi.nip =:strukturId\n" +
//                "  AND itPosisi.flag='Y'\n" +
//                "  AND struktur.flag='Y'";

        String query = "SELECT * FROM im_hris_struktur_jabatan WHERE struktur_jabatan_id = :strukturId AND branch_id = :branchId AND flag = 'Y'";

        List<Object[]> results = new ArrayList<Object[]>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("strukturId", strukturJabatanid)
                .setParameter("branchId",branchId)
                .list();


        for (Object[] row : results) {
            StrukturJabatan result  = new StrukturJabatan();
            result.setParentId((String) row[2]);

            strukturJabatanList.add(result);
        }
        return strukturJabatanList;
    }


    public List<StrukturJabatan> searchStruktur(String positionId,String branchId) throws HibernateException{
        List<StrukturJabatan> strukturJabatanList = new ArrayList<>();

        String query = "SELECT * FROM im_hris_struktur_jabatan WHERE position_Id = :positionId AND branch_id = :branchId AND flag = 'Y'";

        List<Object[]> results = new ArrayList<Object[]>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("positionId", positionId)
                .setParameter("branchId",branchId)
                .list();


        for (Object[] row : results) {
            StrukturJabatan result  = new StrukturJabatan();
            result.setParentId((String) row[2]);
            result.setPositionId((String) row[8]);

            strukturJabatanList.add(result);
        }
        return strukturJabatanList;
    }


    public List<ImStrukturJabatanEntity> getListStruktur(String Branch, String Posisi) throws HibernateException {
        List<ImStrukturJabatanEntity> results = new ArrayList<ImStrukturJabatanEntity>();
        if(Branch.equals("")){
            results= this.sessionFactory.getCurrentSession().createCriteria(ImStrukturJabatanEntity.class)
                    .add(Restrictions.eq("positionId", Posisi))
                    .add(Restrictions.eq("flag", "Y"))
                    .addOrder(Order.asc("strukturJabatanId"))
                    .list();
        }else{
            results= this.sessionFactory.getCurrentSession().createCriteria(ImStrukturJabatanEntity.class)
                    .add(Restrictions.eq("flag", "Y"))
                    .add(Restrictions.eq("positionId", Posisi))
                    .add(Restrictions.eq("branchId", Branch))
                    .addOrder(Order.asc("strukturJabatanId"))
                    .list();
        }
        return results;
    }

    public List<ImStrukturJabatanEntity> getTambahanAspekA(String id){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tjabatan.parent_id,\n" +
                "\tposisi.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tkelompok.kelompok_id,\n" +
                "\tkelompok.kelompok_name\n" +
                "from \n" +
                "\tim_hris_struktur_jabatan jabatan\n" +
                "\tleft join im_position posisi on posisi.position_id = jabatan.position_id\n" +
                "\tleft join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "where \n" +
                "\tjabatan.struktur_jabatan_id = '"+id+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setParentId((String) row[0]);
            result.setPositionId((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setKelompokId((String) row[3]);
            result.setKelompokName((String)(row[4]));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImStrukturJabatanEntity> getParent(String positionId, String branchId){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tjabatan.parent_id,\n" +
                "\tposisi.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tkelompok.kelompok_id,\n" +
                "\tkelompok.kelompok_name\n" +
                "from \n" +
                "\tim_hris_struktur_jabatan jabatan\n" +
                "\tleft join im_position posisi on posisi.position_id = jabatan.position_id\n" +
                "\tleft join im_hris_kelompok_position kelompok on kelompok.kelompok_id = posisi.kelompok_id\n" +
                "where \n" +
                "\tposisi.position_id = '"+positionId+"'\n" +
                "\tand jabatan.branch_id = '"+branchId+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setParentId((String) row[0]);
            result.setPositionId((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setKelompokId((String) row[3]);
            result.setKelompokName((String)(row[4]));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImStrukturJabatanEntity> getIdParent(String nip){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\t* \n" +
                "from \n" +
                "\tstruktur_jabatan \n" +
                "where \n" +
                "\tnip = '"+nip+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setStrukturJabatanId((String) row[0]);
            result.setParentId((String) row[1]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    //Mengambil Id dari Struktur jabatan yang digunakan untuk mencari jabatan dibawah id tersebut melalui table struktur jabatan
    public List<ImStrukturJabatanEntity> getIdStrukturJabatanAtasan(String branchId, String positionId){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tjabatan.struktur_jabatan_id,\n" +
                "\tjabatan.level,\n" +
                "\tjabatan.parent_id,\n" +
                "\tjabatan.branch_id,\n" +
                "\tjabatan.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tposisiPegawai.nip,\n" +
                "\tposisi.kelompok_id\n" +
                "from\n" +
                "\tim_hris_struktur_jabatan jabatan\n" +
                "\tleft join im_position posisi on posisi.position_id = jabatan.position_id\n" +
                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.branch_id = jabatan.branch_id and posisiPegawai.position_id = jabatan.position_id\n" +
                "where\n" +
                "\tjabatan.position_id = '"+positionId+"'\n" +
                "\tand jabatan.branch_id = '"+branchId+"'\n" +
                "\tand jabatan.flag = 'Y'\n" +
                "\tand posisi.flag = 'Y'\n" +
                "\tand posisiPegawai.flag = 'Y' ";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setStrukturJabatanId((String) row[0]);
            result.setLevel(Long.valueOf(row[1].toString()));
            result.setParentId((String) row[2]);
            result.setBranchId((String) row[3]);
            result.setPositionId((String) row[4]);
            result.setPositionName((String) row[5]);
            result.setNip((String) row[6]);
            result.setKelompokId((String) row[7]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    //Mengambil Id dari Struktur jabatan yang digunakan untuk mencari jabatan dibawah id tersebut melalui table struktur jabatan, digunakan untuk loop
    public List<ImStrukturJabatanEntity> getIdStrukturJabatan(String idParent){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tjabatan.struktur_jabatan_id,\n" +
                "\tjabatan.level,\n" +
                "\tjabatan.parent_id,\n" +
                "\tjabatan.branch_id,\n" +
                "\tjabatan.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tposisiPegawai.nip,\n" +
                "\tposisi.kelompok_id,\n" +
                "\tpegawai.status_pegawai\n" +
                "from\n" +
                "\tim_hris_struktur_jabatan jabatan\n" +
                "\tleft join im_position posisi on posisi.position_id = jabatan.position_id\n" +
                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.branch_id = jabatan.branch_id and posisiPegawai.position_id = jabatan.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = posisiPegawai.nip\n" +
                "where\n" +
                "\tjabatan.parent_id = '"+idParent+"'\n" +
                "\tand jabatan.flag = 'Y'\n" +
                "\tand posisi.flag = 'Y'\n" +
                "\tand posisiPegawai.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setStrukturJabatanId((String) row[0]);
            result.setLevel(Long.valueOf(row[1].toString()));
            result.setParentId((String) row[2]);
            result.setBranchId((String) row[3]);
            result.setPositionId((String) row[4]);
            result.setPositionName((String) row[5]);
            result.setNip((String) row[6]);
            result.setKelompokId((String) row[7]);
            result.setStatusPegawai((String) row[8]);
            listOfResult.add(result);
        }
        return listOfResult;
    }


    //Mengambil Id dari Struktur jabatan yang digunakan untuk mencari jabatan diatas id tersebut melalui table struktur jabatan
    public List<ImStrukturJabatanEntity> getIdStrukturJabatanAtas(String idParent){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tjabatan.struktur_jabatan_id,\n" +
                "\tjabatan.level,\n" +
                "\tjabatan.parent_id,\n" +
                "\tjabatan.branch_id,\n" +
                "\tjabatan.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tposisiPegawai.nip,\n" +
                "\tposisi.kelompok_id\n" +
                "from\n" +
                "\tim_hris_struktur_jabatan jabatan\n" +
                "\tleft join im_position posisi on posisi.position_id = jabatan.position_id\n" +
                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.branch_id = jabatan.branch_id and posisiPegawai.position_id = jabatan.position_id\n" +
                "where\n" +
                "\tjabatan.struktur_jabatan_id = '"+idParent+"'\n" +
                "\tand jabatan.flag = 'Y'\n" +
                "\tand posisi.flag = 'Y'\n" +
                "\tand posisiPegawai.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setStrukturJabatanId((String) row[0]);
            result.setLevel(Long.valueOf(row[1].toString()));
            result.setParentId((String) row[2]);
            result.setBranchId((String) row[3]);
            result.setPositionId((String) row[4]);
            result.setPositionName((String) row[5]);
            result.setNip((String) row[6]);
            result.setKelompokId((String) row[7]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImStrukturJabatanEntity> getIdStrukturJabatanBawah(String idParent){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tjabatan.struktur_jabatan_id,\n" +
                "\tjabatan.level,\n" +
                "\tjabatan.parent_id,\n" +
                "\tjabatan.branch_id,\n" +
                "\tjabatan.position_id,\n" +
                "\tposisi.position_name,\n" +
                "\tposisiPegawai.nip,\n" +
                "\tposisi.kelompok_id\n" +
                "from\n" +
                "\tim_hris_struktur_jabatan jabatan\n" +
                "\tleft join im_position posisi on posisi.position_id = jabatan.position_id\n" +
                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.branch_id = jabatan.branch_id and posisiPegawai.position_id = jabatan.position_id\n" +
                "where\n" +
                "\tjabatan.parent_id = '"+idParent+"'\n" +
                "\tand jabatan.flag = 'Y'\n" +
                "\tand posisi.flag = 'Y'\n" +
                "\tand posisiPegawai.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setStrukturJabatanId((String) row[0]);
            result.setLevel(Long.valueOf(row[1].toString()));
            result.setParentId((String) row[2]);
            result.setBranchId((String) row[3]);
            result.setPositionId((String) row[4]);
            result.setPositionName((String) row[5]);
            result.setNip((String) row[6]);
            result.setKelompokId((String) row[7]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public String getNamaDirektur(){
        String nama = "";
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\t* \n" +
                "from \n" +
                "\tstruktur_jabatan\n" +
                "where\n" +
                "\tposition_id = '1'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();

            nama = (String) row[12];
        }
        return nama;
    }


    public List<ImStrukturJabatanEntity> getStrukturJabatanSearch(String branchId, String posisiId, String parentId, String nip){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        List<Object[]> results2 = new ArrayList<Object[]>();
        String strPosisi = "";
        String strParent = "";
        String strNip = "";
        if(!posisiId.equalsIgnoreCase("")){
            strPosisi = " and posisi.position_id = '"+posisiId+"' ";
        }

        if(!parentId.equalsIgnoreCase("")){
            strParent = " and jabatan.parent_id = '"+parentId+"' ";
        }

        if(!nip.equalsIgnoreCase("")){
            strNip = " and itPosisi.nip = '"+nip+"' ";
        }

        String query = "select distinct \n" +
                "\tjabatan.*,\n" +
                "\titPosisi.nip,\n" +
                "\tpegawai.nama_pegawai, \n" +
                "\tposisi.position_name \n" +
                "from \n" +
                "\tim_hris_struktur_jabatan jabatan\n" +
                "\tleft join it_hris_pegawai_position itPosisi on itPosisi.position_id = jabatan.position_id and itPosisi.flag = 'Y' and jabatan.branch_id = itPosisi.branch_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = itPosisi.nip \n" +
                "\tleft join im_position posisi on posisi.position_id = jabatan.position_id \n" +
                "where \n" +
                "\tjabatan.flag = 'Y'\n" +
                "\tand jabatan.branch_id = '"+branchId+"'\n " + strPosisi + strParent + strNip;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();

            result.setStrukturJabatanId((String) row[0]);
            result.setLevel(Long.valueOf(row[1].toString()));
            result.setParentId((String) row[2]);
            result.setBranchId((String) row[7]);
            result.setPositionId((String) row[8]);

            if ((String) row[11]!=null){
                result.setNip((String) row[11]);
                result.setNamaPegawai((String) row[12]);
            } else{
                String position = (String) row[8];
                String queryPlt = "select * from im_hris_pegawai where position_plt_id='"+position+"'";
                results2 = this.sessionFactory.getCurrentSession()
                        .createSQLQuery(queryPlt)
                        .list();

                for (Object[] obj : results2){
                    result.setNip((String) obj[0]);
                    result.setNamaPegawai((String) obj[1]);
                }
            }
            result.setPositionName((String) row[13]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImStrukturJabatanEntity> getPerBagianDao(){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();
        List<Object[]> results = new ArrayList<Object[]>();

        String query = "select\n" +
                "\tstruktur_jabatan_id,\n" +
                "\tparent_id,\n" +
                "\tbranch_id,\n" +
                "\tposition_id,\n" +
                "\tposition_name,\n" +
                "\tkelompok_id,\n" +
                "\tdepartment_name,\n" +
                "\tnip,\n" +
                "\tnama_pegawai\n" +
                "from\n" +
                "\tstruktur_jabatan\n" +
                "where\n" +
                "\tkelompok_id = 'KL04' AND branch_id='KD01'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setStrukturJabatanId((String) row[0]);
            result.setParentId((String) row[1]);
            result.setBranchId((String) row[2]);
            result.setPositionId((String) row[3]);
            result.setPositionName((String) row[4]);
            result.setKelompokId((String) row[5]);
            result.setNip((String) row[7]);
            result.setNamaPegawai((String) row[8]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImStrukturJabatanEntity> getPerBagianBranchDao(String branchId){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();
        List<Object[]> results = new ArrayList<Object[]>();

        String query = "select\n" +
                "\tstruktur.struktur_jabatan_id,\n" +
                "\tstruktur.parent_id,\n" +
                "\tstruktur.branch_id,\n" +
                "\tstruktur.position_id,\n" +
                "\tstruktur.position_name,\n" +
                "\tstruktur.kelompok_id,\n" +
                "\tstruktur.department_name,\n" +
                "\tstruktur.nip,\n" +
                "\tstruktur.nama_pegawai,\n" +
                "\tpegawai.status_pegawai\n" +
                "from\n" +
                "\tstruktur_jabatan struktur\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = struktur.nip\n" +
                "where\n" +
                "\tstruktur.kelompok_id in ('KL04', 'KL03', 'KL02', 'KL01')\t\n" +
                "\tand struktur.branch_id = '"+branchId+"'\n" +
                "";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setStrukturJabatanId((String) row[0]);
            result.setParentId((String) row[1]);
            result.setBranchId((String) row[2]);
            result.setPositionId((String) row[3]);
            result.setPositionName((String) row[4]);
            result.setKelompokId((String) row[5]);
            result.setNip((String) row[7]);
            result.setNamaPegawai((String) row[8]);
            result.setStatusPegawai((String) row[9]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImStrukturJabatanEntity> getPerBagianDireksiDao(String unit){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();
        List<Object[]> results = new ArrayList<Object[]>();

        String query = "\n" +
                "select\n" +
                "\tposisi.branch_id,\n" +
                "\tjabatan.position_id,\n" +
                "\tjabatan.position_name,\n" +
                "\tjabatan.kelompok_id,\n" +
                "\tpegawai.nip,\n" +
                "\tpegawai.nama_pegawai\n" +
                "from \n" +
                "\tim_hris_pegawai pegawai\n" +
                "\tleft join it_hris_pegawai_position posisi on posisi.nip = pegawai.nip\n" +
                "\tleft join im_position jabatan on jabatan.position_id = posisi.position_id\n" +
                "\tleft join im_hris_kelompok_position kelompok on kelompok.kelompok_id = jabatan.kelompok_id\n" +
                "where\n" +
                "\tposisi.branch_id = '"+unit+"'\n" +
                "\tand jabatan.kelompok_id in ('KL01', 'KL00', 'KL02')\n" +
                "\tand posisi.flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setStrukturJabatanId("");
            result.setParentId("");
            result.setBranchId((String) row[0]);
            result.setPositionId((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setKelompokId((String) row[3]);
            result.setNip((String) row[4]);
            result.setNamaPegawai((String) row[5]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImStrukturJabatanEntity> getAllStrukturJabatan(){
        List<ImStrukturJabatanEntity> listOfResult = new ArrayList<ImStrukturJabatanEntity>();
        List<Object[]> results = new ArrayList<Object[]>();

        String query = "select\n" +
                "\tstruktur.struktur_jabatan_id,\n" +
                "\tstruktur.branch_id,\n" +
                "\tstruktur.position_id,\n" +
                "\tstruktur.position_name,\n" +
                "\tstruktur.nip,\n" +
                "\tstruktur.nama_pegawai,\n" +
                "\tpegawai.status_pegawai\n" +
                "from\n" +
                "\tstruktur_jabatan struktur\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = struktur.nip\n";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();
            result.setStrukturJabatanId((String) row[0]);
            result.setBranchId((String) row[1]);
            result.setPositionId((String) row[2]);
            result.setPositionName((String) row[3]);
            result.setNip((String) row[4]);
            result.setNamaPegawai((String) row[5]);
            result.setStatusPegawai((String) row[6]);

            listOfResult.add(result);
        }
        return listOfResult;
    }
    public List<StrukturJabatan>cekKelompok(String id,String branchId) throws HibernateException{

        String strukturId = id;
        String unit = branchId;
        String query = "";

        if (id == ""){
            strukturId = "%";
        } else {
            strukturId = id;
        }

        query = "SELECT \n" +
                "posisi.nip,kelompok.kelompok_id, struktur_jabatan.parent_id, struktur_jabatan.position_id\n" +
                "FROM \n" +
                "im_hris_struktur_jabatan struktur_jabatan, \n" +
                "it_hris_pegawai_position posisi,\n" +
                "im_position imposisi,\n" +
                "im_hris_kelompok_position kelompok\n" +
                "WHERE  \n" +
                "struktur_jabatan_id='"+strukturId+"' AND \n" +
                "posisi.branch_id='"+unit+"'\n" +
                "and posisi.position_id = struktur_jabatan.position_id\n" +
                "and posisi.position_id = imposisi.position_id\n" +
                "and kelompok.kelompok_id = imposisi.kelompok_id";

        List<Object[]> results = new ArrayList<Object[]>();
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        List<StrukturJabatan> result  = new ArrayList<>();
        for (Object[] row : results) {
            StrukturJabatan strukturJabatan = new StrukturJabatan();
            strukturJabatan.setNip((String) row[0]);
            strukturJabatan.setPositionKelompokId((String) row[1]);
            strukturJabatan.setParentId((String) row[2]);
            strukturJabatan.setPositionId((String) row[3]);
            result.add(strukturJabatan);
        }
        return result;
    }

    public String getNamaKabidSdm(){
        String nama = "";
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\t* \n" +
                "from \n" +
                "\tstruktur_jabatan\n" +
                "where\n" +
                "\tposition_id = '9'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImStrukturJabatanEntity result  = new ImStrukturJabatanEntity();

            nama = (String) row[12];
        }
        return nama;
    }
}