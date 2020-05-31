package com.neurix.hris.transaksi.cutiPegawai.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.ExpoPushNotif;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.cuti.dao.CutiDao;
import com.neurix.hris.master.cuti.model.ImCutiEntity;
import com.neurix.hris.master.cutiPanjang.dao.CutiPanjangDao;
import com.neurix.hris.master.cutiPanjang.model.CutiPanjang;
import com.neurix.hris.master.cutiPanjang.model.ImCutiPanjangEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.dao.CutiPegawaiDao;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiFcmDao;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.ItNotifikasiFcmEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.joda.time.LocalDate;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class CutiPegawaiBoImpl implements CutiPegawaiBo {

    protected static transient Logger logger = Logger.getLogger(CutiPegawaiBoImpl.class);
    private CutiPegawaiDao cutiPegawaiDao;
    private BiodataDao biodataDao;
    private CutiPanjangDao cutiPanjangDao;
    private StrukturJabatanDao strukturJabatanDao;
    private PersonilPositionDao personilPositionDao;
    private NotifikasiFcmDao notifikasiFcmDao;
    private CutiDao cutiDao;
    private DepartmentDao departmentDao;
    private PositionDao positionDao;
    private BranchDao branchDao;
    private String CLICK_ACTION = "TASK_CUTI";

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public CutiDao getCutiDao() {
        return cutiDao;
    }

    public void setCutiDao(CutiDao cutiDao) {
        this.cutiDao = cutiDao;
    }

    public void setNotifikasiFcmDao(NotifikasiFcmDao notifikasiFcmDao) {
        this.notifikasiFcmDao = notifikasiFcmDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public CutiPanjangDao getCutiPanjangDao() {
        return cutiPanjangDao;
    }

    public void setCutiPanjangDao(CutiPanjangDao cutiPanjangDao) {
        this.cutiPanjangDao = cutiPanjangDao;
    }

    public NotifikasiDao getNotifikasiDao() {
        return notifikasiDao;
    }

    public void setNotifikasiDao(NotifikasiDao notifikasiDao) {
        this.notifikasiDao = notifikasiDao;
    }

    private NotifikasiDao notifikasiDao;

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CutiPegawaiBoImpl.logger = logger;
    }

    public CutiPegawaiDao getCutiPegawaiDao() {
        return cutiPegawaiDao;
    }

    public void setCutiPegawaiDao(CutiPegawaiDao cutiPegawaiDao) {
        this.cutiPegawaiDao = cutiPegawaiDao;
    }

    @Override
    public void saveDelete(CutiPegawai bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String cutiPegawaiId = bean.getCutiPegawaiId();

            ItCutiPegawaiEntity itCutiPegawaiEntity = null;

            try {
                // Get data from database by ID
                itCutiPegawaiEntity = cutiPegawaiDao.getById("cutiPegawai", cutiPegawaiId);
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (itCutiPegawaiEntity != null) {

                // Modify from bean to entity serializable
                itCutiPegawaiEntity.setCutiPegawaiId(bean.getCutiPegawaiId());
                itCutiPegawaiEntity.setNip(bean.getNip());
                itCutiPegawaiEntity.setPegawaiPenggantiSementara(bean.getPegawaiPenggantiSementara());
                itCutiPegawaiEntity.setCutiId(bean.getCutiId());
                itCutiPegawaiEntity.setLamaHariCuti(bean.getLamaHariCuti());
                itCutiPegawaiEntity.setSisaCutiHari(bean.getSisaCutiHari());
                itCutiPegawaiEntity.setApprovalId(bean.getApprovalId());
                itCutiPegawaiEntity.setApprovalDate(bean.getApprovalDate());
                itCutiPegawaiEntity.setApprovalFlag(bean.getApprovalFlag());
                itCutiPegawaiEntity.setNote(bean.getNote());
                itCutiPegawaiEntity.setNoteApproval(bean.getNoteApproval());
                itCutiPegawaiEntity.setTanggalDari((Date) bean.getTanggalDari());
                itCutiPegawaiEntity.setTanggalSelesai((Date) bean.getTanggalSelesai());
                
                itCutiPegawaiEntity.setFlag(bean.getFlag());
                itCutiPegawaiEntity.setAction(bean.getAction());
                itCutiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itCutiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
                itCutiPegawaiEntity.setFlagPerbaikan("N");

                try {
                    // Delete (Edit) into database
                    cutiPegawaiDao.updateAndSave(itCutiPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[CutiPegawaiBoImpl.saveDelete] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");

            }
        }
        logger.info("[CutiPegawaiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(CutiPegawai bean) throws GeneralBOException {
        logger.info("[CutiPegawaiBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String cutiPegawaiId = bean.getCutiPegawaiId();
            ItCutiPegawaiEntity itCutiPegawaiEntity = null;
            try {
                itCutiPegawaiEntity = cutiPegawaiDao.getById("cutiPegawaiId", cutiPegawaiId);
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }
            if (itCutiPegawaiEntity != null) {
                itCutiPegawaiEntity.setCutiPegawaiId(bean.getCutiPegawaiId());
                itCutiPegawaiEntity.setTanggalDari((Date) bean.getTanggalDari());
                itCutiPegawaiEntity.setTanggalSelesai((Date) bean.getTanggalSelesai());
                itCutiPegawaiEntity.setCancelFlag(bean.getCancelFlag());
                itCutiPegawaiEntity.setCancelDate(bean.getCancelDate());
                itCutiPegawaiEntity.setCancelPerson(bean.getCancelPerson());
                itCutiPegawaiEntity.setCancelNote(bean.getCancelNote());
                itCutiPegawaiEntity.setAlamatCuti(bean.getAlamatCuti());
                itCutiPegawaiEntity.setKeterangan(bean.getKeterangan());
                
                itCutiPegawaiEntity.setFlag(bean.getFlag());
                itCutiPegawaiEntity.setAction(bean.getAction());
                itCutiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itCutiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
                itCutiPegawaiEntity.setFlagPerbaikan("N");
                try {
                    // Update into database
                    cutiPegawaiDao.updateAndSave(itCutiPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[CutiPegawaiBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
            }

            if (bean.getCutiId().equalsIgnoreCase("CT006")&&(Date)bean.getTanggalDari()==itCutiPegawaiEntity.getTanggalDari()&&(Date)bean.getTanggalSelesai()==itCutiPegawaiEntity.getTanggalSelesai()){
                List<ItCutiPegawaiEntity> itCutiPegawaiEntityList = cutiPegawaiDao.searchCancel(itCutiPegawaiEntity.getNip(),itCutiPegawaiEntity.getTanggalDari(),itCutiPegawaiEntity.getTanggalSelesai());
                for (ItCutiPegawaiEntity itCutiPegawaiEntity1:itCutiPegawaiEntityList){
                    ItCutiPegawaiEntity data = cutiPegawaiDao.getById("cutiPegawaiId",itCutiPegawaiEntity1.getCutiPegawaiId(),"Y");
                    data.setFlag("N");
                    cutiPegawaiDao.updateAndSave(data);
                }
            }

            //delete from notif
            if (("Y").equalsIgnoreCase(itCutiPegawaiEntity.getCancelFlag())){
                List<ImNotifikasiEntity> notifikasiEntityList = notifikasiDao.getDataByNoRequest(itCutiPegawaiEntity.getCutiPegawaiId(),itCutiPegawaiEntity.getNip());

                if (notifikasiEntityList!=null){
                    for (ImNotifikasiEntity notifikasiEntity : notifikasiEntityList){
                        notifikasiEntity.setFlag("N");

                        try {
                            // Update into database
                            notifikasiDao.updateAndSave(notifikasiEntity);
                        } catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                        }

                    }
                }
            }
        }
        logger.info("[CutiPegawaiBoImpl.saveEdit] end process <<<");
    }
    @Override
    public  List<Notifikasi> saveAddCuti ( CutiPegawai bean ) throws GeneralBOException {
        logger.info("[CutiPegawaiBoImpl.saveAdd] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();

        String nip=bean.getNip(),cutiPegawaiId;
        if (bean!=null) {
            BigInteger jumlahCutiPegawai = BigInteger.valueOf(0);
            List<ItCutiPegawaiEntity> cutiPegawaiEntityList = new ArrayList<>();

            Map hsCriteria = new HashMap();
            if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                hsCriteria.put("nip", bean.getNip());
            }
            try {
                // Generating ID, get from postgre sequence
                cutiPegawaiId = cutiPegawaiDao.getNextCutiPegawaiId();

                cutiPegawaiEntityList = cutiPegawaiDao.getJumlahHariCuti(nip,bean.getCutiId());
                for (ItCutiPegawaiEntity cutiPegawai : cutiPegawaiEntityList){
                    jumlahCutiPegawai = cutiPegawai.getSisaCutiHari();
                }
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ItCutiPegawaiEntity itCutiPegawaiEntity = new ItCutiPegawaiEntity();

            itCutiPegawaiEntity.setCutiPegawaiId( cutiPegawaiId);
            itCutiPegawaiEntity.setNip(bean.getNip());
            List<Biodata> resultBiodata = new ArrayList<>();
            try{
                resultBiodata = cutiPegawaiDao.getBranchDivisiPosisi(bean.getNip());
                for (Biodata biodata: resultBiodata){
                    itCutiPegawaiEntity.setUnitId(biodata.getBranch());
                    itCutiPegawaiEntity.setDivisiId(biodata.getDivisi());
                    itCutiPegawaiEntity.setPosisiId(biodata.getPositionId());
                    itCutiPegawaiEntity.setBagianId(biodata.getBagianId());
                }
            }catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }
            itCutiPegawaiEntity.setPegawaiPenggantiSementara(bean.getPegawaiPenggantiSementara());
            if (!"normal".equalsIgnoreCase(bean.getJenisCuti()))
                itCutiPegawaiEntity.setCutiId(bean.getCutiTanggunganId());
            else
                itCutiPegawaiEntity.setCutiId(bean.getCutiId());

            itCutiPegawaiEntity.setLamaHariCuti(bean.getLamaHariCuti());

            if (!"normal".equalsIgnoreCase(bean.getJenisCuti()))
                itCutiPegawaiEntity.setSisaCutiHari(BigInteger.valueOf(1095));
            else
                itCutiPegawaiEntity.setSisaCutiHari(jumlahCutiPegawai.subtract(bean.getLamaHariCuti()));

            itCutiPegawaiEntity.setApprovalId(bean.getApprovalId());
            itCutiPegawaiEntity.setKeterangan(bean.getKeterangan());
            itCutiPegawaiEntity.setAlamatCuti(bean.getAlamatCuti());
            itCutiPegawaiEntity.setApprovalDate(bean.getApprovalDate());
            itCutiPegawaiEntity.setNote(bean.getNote());
            itCutiPegawaiEntity.setCancelFlag("N");
            itCutiPegawaiEntity.setNoteApproval(bean.getNoteApproval());
            itCutiPegawaiEntity.setTanggalDari((Date) bean.getTanggalDari());
            itCutiPegawaiEntity.setTanggalSelesai((Date) bean.getTanggalSelesai());
            itCutiPegawaiEntity.setFlag(bean.getFlag());
            itCutiPegawaiEntity.setAction(bean.getAction());
            itCutiPegawaiEntity.setCreatedWho(bean.getCreatedWho());
            itCutiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itCutiPegawaiEntity.setCreatedDate(bean.getCreatedDate());
            itCutiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
            itCutiPegawaiEntity.setFlagPerbaikan("N");
            itCutiPegawaiEntity.setJenisCuti(bean.getJenisCuti());

            try {
                // insert into database
                cutiPegawaiDao.addAndSave(itCutiPegawaiEntity);
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            ImBiodataEntity imBiodataEntity = new ImBiodataEntity();

            try {
                imBiodataEntity =  biodataDao.getById("nip", bean.getNip(), "Y");
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            //Send notif ke atasan
            Notifikasi notifAtasan= new Notifikasi();
            notifAtasan.setNip(bean.getNip());
            notifAtasan.setNoRequest(cutiPegawaiId);
            notifAtasan.setTipeNotifId("TN66");
            notifAtasan.setTipeNotifName(("Cuti Pegawai"));
            notifAtasan.setNote("Data Dari User : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
            notifAtasan.setCreatedWho(bean.getNip());
            notifAtasan.setTo("atasan");
            notifAtasan.setOs(bean.getOs());

            notifikasiList.add(notifAtasan);
        }
        logger.info("[CutiPegawaiBoImpl.saveAdd] end process <<<");
        return notifikasiList;
    }

    @Override
    public CutiPegawai saveAdd(CutiPegawai bean) throws GeneralBOException {
        logger.info("[CutiPegawaiBoImpl.saveAdd] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();

        String atasanNip = null,nip=bean.getNip(),cutiPegawaiId;
        if (bean!=null) {
            BigInteger jumlahCutiPegawai = BigInteger.valueOf(0);
            List<ItCutiPegawaiEntity> cutiPegawaiEntityList = new ArrayList<>();

            Map hsCriteria = new HashMap();
            if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                hsCriteria.put("nip", bean.getNip());
            }
            try {
                // Generating ID, get from postgre sequence
                cutiPegawaiId = cutiPegawaiDao.getNextCutiPegawaiId();

                cutiPegawaiEntityList = cutiPegawaiDao.getJumlahHariCuti(nip,bean.getCutiId());
                for (ItCutiPegawaiEntity cutiPegawai : cutiPegawaiEntityList){
                    jumlahCutiPegawai = cutiPegawai.getSisaCutiHari();
                }
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ItCutiPegawaiEntity itCutiPegawaiEntity = new ItCutiPegawaiEntity();

            itCutiPegawaiEntity.setCutiPegawaiId( cutiPegawaiId);
            itCutiPegawaiEntity.setNip(bean.getNip());
            itCutiPegawaiEntity.setPegawaiPenggantiSementara(bean.getPegawaiPenggantiSementara());
            itCutiPegawaiEntity.setCutiId(bean.getCutiId());
            itCutiPegawaiEntity.setLamaHariCuti(bean.getLamaHariCuti());
            itCutiPegawaiEntity.setSisaCutiHari(jumlahCutiPegawai.subtract(bean.getLamaHariCuti()));
            itCutiPegawaiEntity.setApprovalId(bean.getApprovalId());
            itCutiPegawaiEntity.setKeterangan(bean.getKeterangan());
            itCutiPegawaiEntity.setAlamatCuti(bean.getAlamatCuti());
            itCutiPegawaiEntity.setApprovalDate(bean.getApprovalDate());
            itCutiPegawaiEntity.setNote(bean.getNote());
            itCutiPegawaiEntity.setCancelFlag("N");
            itCutiPegawaiEntity.setNoteApproval(bean.getNoteApproval());
            itCutiPegawaiEntity.setTanggalDari((Date) bean.getTanggalDari());
            itCutiPegawaiEntity.setTanggalSelesai((Date) bean.getTanggalSelesai());
            itCutiPegawaiEntity.setFlag(bean.getFlag());
            itCutiPegawaiEntity.setAction(bean.getAction());
            itCutiPegawaiEntity.setCreatedWho(bean.getCreatedWho());
            itCutiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itCutiPegawaiEntity.setCreatedDate(bean.getCreatedDate());
            itCutiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
            itCutiPegawaiEntity.setFlagPerbaikan(bean.getFlagPerbaikan());

            try {
                // insert into database
                cutiPegawaiDao.addAndSave(itCutiPegawaiEntity);
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            List<StrukturJabatan> strukturJabatanList = new ArrayList<>();

            try {
                strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(bean.getNip(),bean.getUnitId());
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            ImBiodataEntity imBiodataEntity =  biodataDao.getById("nip", bean.getNip(), "Y");

            for (StrukturJabatan strukturJabatan : strukturJabatanList){
                // Search Leader
                if (strukturJabatan != null){
                    String[] parts = strukturJabatan.getParentId().split("-");
                    String parent = parts[0];
                    if (parent != null){
                        // search data postion_id from struktur jabatan by parameter parent
                        hsCriteria = new HashMap();
                        hsCriteria.put("struktur_jabatan_id", parent);
                        hsCriteria.put("flag","Y");
                        List<ImStrukturJabatanEntity> strukturJabatanEntities = null;
                        try {
                            strukturJabatanEntities = strukturJabatanDao.getByCriteria(hsCriteria);
                        } catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }

                        if (strukturJabatanEntities!=null){
                            for (ImStrukturJabatanEntity listStruktur : strukturJabatanEntities){

                                // search data nip from personil by parameter position_id from struktur jabatan
                                String stPosition = "";
                                if (listStruktur.getPositionId() != null){
                                    stPosition = String.valueOf(listStruktur.getPositionId());
                                }
                                hsCriteria = new HashMap();
                                hsCriteria.put("position_id",stPosition);
                                hsCriteria.put("branch_id",bean.getUnitId());
                                hsCriteria.put("flag","Y");
                                List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
                                try {

                                    itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
                                } catch (HibernateException e) {
                                    logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                }

                                if (itPersonilPositionEntities != null){
                                    for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities){
                                        String nip2 = listPersonilPosition.getNip();

                                        hsCriteria = new HashMap();
                                        hsCriteria.put("nip",nip2);
                                        hsCriteria.put("flag","Y");

                                        List<ImBiodataEntity> imBiodataEntityList = null;
                                        try {
                                            imBiodataEntityList = biodataDao.getByCriteria(hsCriteria);
                                        } catch (HibernateException e) {
                                            logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }

                                        if (imBiodataEntityList != null){
                                            for (ImBiodataEntity listBio : imBiodataEntityList){
                                                atasanNip=listBio.getNip();
                                            }
                                        }
                                        // Send Notification
                                        ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                                        String idNotif = notifikasiDao.getNextNotifikasiId();
//                                        imBiodataEntity = biodataDao.getById("nip", bean.getNip(), "Y");
                                        String noteMobile = itCutiPegawaiEntity.getLastUpdateWho() + " menunggu di Approve";
                                        String note="Data Dari User : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve";
                                        addNotif.setNotifId(idNotif);
                                        addNotif.setNote(note);
                                        addNotif.setTipeNotifId("TN66");
                                        addNotif.setTipeNotifName("Cuti Pegawai");
                                        addNotif.setRead("Y");
                                        addNotif.setFlag("Y");
                                        addNotif.setAction("C");
                                        addNotif.setNip(atasanNip);
                                        addNotif.setFromPerson(itCutiPegawaiEntity.getNip());
                                        addNotif.setNoRequest(itCutiPegawaiEntity.getCutiPegawaiId());
                                        addNotif.setCreatedDate(itCutiPegawaiEntity.getCreatedDate());
                                        addNotif.setCreatedWho(itCutiPegawaiEntity.getCreatedWho());
                                        addNotif.setLastUpdate(itCutiPegawaiEntity.getLastUpdate());
                                        addNotif.setLastUpdateWho(itCutiPegawaiEntity.getLastUpdateWho());

                                        try {
                                            notifikasiDao.addAndSave(addNotif);
                                        } catch (HibernateException e) {
                                            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }

                                        String plt="";
                                        java.util.Date date = new java.util.Date();
                                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                        List<ItCutiPegawaiEntity> cutiPegawaiPltList= cutiPegawaiDao.getpegawaiPlt(sqlDate,nip2);
                                        if (cutiPegawaiPltList.size()!=0){
                                            for (ItCutiPegawaiEntity cutiPegawaiEntity : cutiPegawaiPltList){
                                                plt=cutiPegawaiEntity.getPegawaiPenggantiSementara();
                                                ImNotifikasiEntity addNotifPlt = new ImNotifikasiEntity();
                                                String idNotifPlt = notifikasiDao.getNextNotifikasiId();
                                                addNotifPlt.setNotifId(idNotifPlt);
                                                addNotifPlt.setNote(note);
                                                addNotifPlt.setTipeNotifId("TN66");
                                                addNotifPlt.setTipeNotifName("Cuti Pegawai");
                                                addNotifPlt.setRead("Y");
                                                addNotifPlt.setFlag("Y");
                                                addNotifPlt.setAction("C");
                                                addNotifPlt.setNip(cutiPegawaiEntity.getPegawaiPenggantiSementara());
                                                addNotifPlt.setFromPerson(nip);
                                                addNotifPlt.setNoRequest(itCutiPegawaiEntity.getCutiPegawaiId());
                                                addNotifPlt.setCreatedDate(itCutiPegawaiEntity.getCreatedDate());
                                                addNotifPlt.setCreatedWho(itCutiPegawaiEntity.getCreatedWho());
                                                addNotifPlt.setLastUpdate(itCutiPegawaiEntity.getLastUpdate());
                                                addNotifPlt.setLastUpdateWho(itCutiPegawaiEntity.getLastUpdateWho());
                                                try {
                                                    notifikasiDao.addAndSave(addNotifPlt);
                                                } catch (HibernateException e) {
                                                    logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                }
                                            }
                                        }

                                        List<ItNotifikasiFcmEntity> notifikasiFcm = null;

                                        try {
                                            notifikasiFcm = notifikasiFcmDao.getAll();
                                        } catch (HibernateException e) {
                                            logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }

                                        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
                                            if(entity.getUserId().equals(atasanNip)){
                                                String message = ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), noteMobile, bean.getOs());
                                                logger.info("[CutiPegawaiBoImpl.saveAdd] Expo Notif: " + message);
                                                break;
                                            }
                                        }

//                                        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                                            if(entity.getUserId().equals(atasanNip)){
//                                                FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), noteMobile, CLICK_ACTION);
//                                                break;
//                                            }
//                                        }
//                                        if (cutiPegawaiPltList.size()!=0){
//                                            for (ItNotifikasiFcmEntity entity : notifikasiFcm) {
//                                                if (entity.getUserId().equals(plt)) {
//                                                    String message = ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), note, bean.getOs());
//                                                    logger.info("[CutiPegawaiBoImpl.saveAdd] Expo Notif: " + message);
//                                                    break;
//                                                }
//                                            }
//                                        }

//                                        for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                                            if(entity.getUserId().equals(atasanNip)){
//                                                FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), noteMobile, CLICK_ACTION);
//                                                break;
//                                            }
//                                        }
//                                        if (cutiPegawaiPltList.size()!=0){
//                                            for (ItNotifikasiFcmEntity entity : notifikasiFcm) {
//                                                if (entity.getUserId().equals(plt)) {
//                                                    FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), note, CLICK_ACTION);
//                                                    break;
//                                                }
//                                            }
//                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("[CutiPegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public CutiPegawai saveCutiBersama(CutiPegawai bean) throws GeneralBOException {
        logger.info("[CutiPegawaiBoImpl.saveCutiBersama] start process >>>");
        if (bean!=null) {
            String cutiPegawaiId;
            try {
                // Generating ID, get from postgre sequence
                cutiPegawaiId = cutiPegawaiDao.getNextCutiPegawaiId();
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveCutiBersama] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence cutiPegawai Id , please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ItCutiPegawaiEntity itCutiPegawaiEntity = new ItCutiPegawaiEntity();

            itCutiPegawaiEntity.setCutiPegawaiId( cutiPegawaiId);
            itCutiPegawaiEntity.setNip(bean.getNip());
            itCutiPegawaiEntity.setPegawaiPenggantiSementara("");
            itCutiPegawaiEntity.setCutiId(bean.getCutiId());
            itCutiPegawaiEntity.setLamaHariCuti(bean.getLamaHariCuti());
            itCutiPegawaiEntity.setSisaCutiHari(bean.getSisaCutiHari());
            itCutiPegawaiEntity.setApprovalId(bean.getApprovalId());
            itCutiPegawaiEntity.setKeterangan(bean.getKeterangan());
            itCutiPegawaiEntity.setAlamatCuti(bean.getAlamatCuti());
            itCutiPegawaiEntity.setApprovalDate(bean.getApprovalDate());
            itCutiPegawaiEntity.setNote("");
            itCutiPegawaiEntity.setClosed("");
            itCutiPegawaiEntity.setCancelFlag("N");
            itCutiPegawaiEntity.setNoteApproval("");
            itCutiPegawaiEntity.setAlamatCuti("");
            itCutiPegawaiEntity.setApprovalId("0001");
            itCutiPegawaiEntity.setTanggalDari((Date) bean.getTanggalDari());
            itCutiPegawaiEntity.setTanggalSelesai((Date) bean.getTanggalSelesai());
            itCutiPegawaiEntity.setApprovalFlag("Y");
            itCutiPegawaiEntity.setApprovalDate(bean.getCreatedDate());
            itCutiPegawaiEntity.setApprovalId(bean.getCreatedWho());
            itCutiPegawaiEntity.setCancelPerson("");
            itCutiPegawaiEntity.setCancelNote("");
            itCutiPegawaiEntity.setFlag(bean.getFlag());
            itCutiPegawaiEntity.setAction(bean.getAction());
            itCutiPegawaiEntity.setCreatedWho(bean.getCreatedWho());
            itCutiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itCutiPegawaiEntity.setCreatedDate(bean.getCreatedDate());
            itCutiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
            itCutiPegawaiEntity.setFlagPerbaikan(bean.getFlagPerbaikan());
            try {
                // insert into database
                cutiPegawaiDao.addAndSave(itCutiPegawaiEntity);
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[CutiPegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public List<CutiPegawai> getByCriteriaForAbsensi(CutiPegawai bean, String tanggal){
        logger.info("[LemburBoImpl.saveAdd] start process >>>");
        List<CutiPegawai> listOfResult = new ArrayList();

        if (bean != null) {
            List<ItCutiPegawaiEntity> cutiPegawaiEntityList = new ArrayList<>();
            cutiPegawaiEntityList = cutiPegawaiDao.getListPersonalFromNip(bean.getNip(),CommonUtil.convertToDate(tanggal));
            for (ItCutiPegawaiEntity cutiPegawaiEntity:cutiPegawaiEntityList){
                CutiPegawai returnCutiPegawai = new CutiPegawai();
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                returnCutiPegawai.setStTanggalDari(df.format(cutiPegawaiEntity.getTanggalDari()));
                returnCutiPegawai.setStTanggalSelesai(df.format(cutiPegawaiEntity.getTanggalSelesai()));
                ImCutiEntity imCutiEntity = cutiDao.getById("cutiId",cutiPegawaiEntity.getCutiId(),"Y");
                returnCutiPegawai.setCutiName(imCutiEntity.getCutiName());

                listOfResult.add(returnCutiPegawai);
            }
        }
        return listOfResult;
    }


    @Override
    public List<CutiPegawai> getByCriteria(CutiPegawai searchBean) throws GeneralBOException {
        logger.info("[CutiPegawaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<CutiPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getCutiPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getCutiPegawaiId())) {
                hsCriteria.put("cuti_pegawai_id", searchBean.getCutiPegawaiId());
            }
            if (searchBean.getCutiId() != null && !"".equalsIgnoreCase(searchBean.getCutiId())) {
                hsCriteria.put("cuti_id", searchBean.getCutiId());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSelesai()))) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ItCutiPegawaiEntity> itCutiPegawaiEntity = null;
            try {

                itCutiPegawaiEntity = cutiPegawaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.getSearchCutiPegawaiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itCutiPegawaiEntity != null){
                CutiPegawai returnCutiPegawai;
                // Looping from dao to object and save in collection
                for(ItCutiPegawaiEntity cutiPegawaiEntity : itCutiPegawaiEntity){
                    returnCutiPegawai = new CutiPegawai();
                    returnCutiPegawai.setCutiPegawaiId(cutiPegawaiEntity.getCutiPegawaiId());
                    returnCutiPegawai.setNip(cutiPegawaiEntity.getNip());
                    if(cutiPegawaiEntity.getPegawaiPenggantiSementara()!=null){
                        if (!cutiPegawaiEntity.getPegawaiPenggantiSementara().equalsIgnoreCase("")){
                            returnCutiPegawai.setPegawaiPenggantiSementara(cutiPegawaiEntity.getPegawaiPenggantiSementara());
                        }
                    }
                    returnCutiPegawai.setCutiId(cutiPegawaiEntity.getCutiId());
                    returnCutiPegawai.setNamaPegawai(cutiPegawaiEntity.getImBiodataEntity().getNamaPegawai());
                    returnCutiPegawai.setCutiName(cutiPegawaiEntity.getImCutiEntity().getCutiName());
                    returnCutiPegawai.setLamaHariCuti(cutiPegawaiEntity.getLamaHariCuti());
                    returnCutiPegawai.setSisaCutiHari(cutiPegawaiEntity.getSisaCutiHari());
                    returnCutiPegawai.setApprovalFlag(cutiPegawaiEntity.getApprovalFlag());
                    returnCutiPegawai.setApprovalDate(cutiPegawaiEntity.getApprovalDate());
                    returnCutiPegawai.setApprovalId(cutiPegawaiEntity.getApprovalId());
                    if(cutiPegawaiEntity.getNote()!=null){
                        if (!cutiPegawaiEntity.getNote().equalsIgnoreCase("")){
                            returnCutiPegawai.setNote(cutiPegawaiEntity.getNote());
                        }
                    }
                    returnCutiPegawai.setKeterangan(cutiPegawaiEntity.getKeterangan());
                    returnCutiPegawai.setAlamatCuti(cutiPegawaiEntity.getAlamatCuti());
                    if(cutiPegawaiEntity.getNoteApproval()!=null){
                        if (!cutiPegawaiEntity.getNoteApproval().equalsIgnoreCase("")){
                            returnCutiPegawai.setNoteApproval(cutiPegawaiEntity.getNoteApproval());
                        }
                    }
                    returnCutiPegawai.setTanggalSelesai(cutiPegawaiEntity.getTanggalSelesai());
                    returnCutiPegawai.setTanggalDari(cutiPegawaiEntity.getTanggalDari());
                    returnCutiPegawai.setStrTanggalSelesai(CommonUtil.convertDateToString(cutiPegawaiEntity.getTanggalSelesai()));
                    returnCutiPegawai.setStrTanggalDari(CommonUtil.convertDateToString(cutiPegawaiEntity.getTanggalDari()));
                    returnCutiPegawai.setAlamatCuti(cutiPegawaiEntity.getAlamatCuti());
                    List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getListNip(cutiPegawaiEntity.getNip());
                    if (personilPositionEntityList!=null){
                        for (ItPersonilPositionEntity personilPosition : personilPositionEntityList){
                            ImPosition imPosition = positionDao.getById("positionId",personilPosition.getPositionId());
                            if (imPosition!=null){
                                returnCutiPegawai.setPosisiName(imPosition.getPositionName());
                                returnCutiPegawai.setPosisiId(imPosition.getPositionId());
                                if (imPosition.getDepartmentId()!=null){
                                    ImDepartmentEntity imDepartmentEntity = departmentDao.getById("departmentId",imPosition.getDepartmentId());
                                    if (imDepartmentEntity!=null){
                                        returnCutiPegawai.setDivisiName(imDepartmentEntity.getDepartmentName());
                                        returnCutiPegawai.setDivisiId(imDepartmentEntity.getDepartmentId());
                                    }
                                }
                            }
                            hsCriteria = new HashMap();
                            hsCriteria.put("branch_id",personilPosition.getBranchId());
                            hsCriteria.put("flag","Y");
                            List<ImBranches> branchesList = branchDao.getByCriteria(hsCriteria);
                            if (branchesList!=null){
                                for(ImBranches imBranches : branchesList){
                                    returnCutiPegawai.setUnitName(imBranches.getBranchName());
                                    returnCutiPegawai.setUnitId(personilPosition.getBranchId());
                                }
                            }
                        }
                    }

                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    returnCutiPegawai.setStTanggalDari(df.format(cutiPegawaiEntity.getTanggalDari()));
                    returnCutiPegawai.setStTanggalSelesai(df.format(cutiPegawaiEntity.getTanggalSelesai()));
                    returnCutiPegawai.setCancelFlag(cutiPegawaiEntity.getCancelFlag());
                    if (cutiPegawaiEntity.getCancelDate()!=null){
                        if (!cutiPegawaiEntity.getCancelDate().equals("")){
                            returnCutiPegawai.setCancelDate(cutiPegawaiEntity.getCancelDate());
                        }
                    }
                    if(cutiPegawaiEntity.getCancelNote()!=null){
                        if (!cutiPegawaiEntity.getCancelNote().equalsIgnoreCase("")){
                            returnCutiPegawai.setCancelNote(cutiPegawaiEntity.getCancelNote());
                        }
                    }
                    if(cutiPegawaiEntity.getCancelPerson()!=null){
                        if (!cutiPegawaiEntity.getCancelPerson().equalsIgnoreCase("")){
                            returnCutiPegawai.setCancelPerson(cutiPegawaiEntity.getCancelPerson());
                        }
                    }

                    hsCriteria = new HashMap();
                    hsCriteria.put("nip",cutiPegawaiEntity.getNip());
                    hsCriteria.put("flag","Y");

                    List<ImBiodataEntity> imBiodataEntities = null;
                    try {

                        imBiodataEntities = biodataDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[CutiPegawaiBoImpl.getSearchCutiPegawaiByCriteria] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }

                    if ( imBiodataEntities!=null){
                        for ( ImBiodataEntity listdata:imBiodataEntities){
                            returnCutiPegawai.setNamaPegawai(listdata.getNamaPegawai());
                        }
                    }

                    if(searchBean.isForMobile()) {
                        returnCutiPegawai.setCekatasan(false);
                    } else {
                        String user = CommonUtil.userIdLogin();
                        if (user.equalsIgnoreCase(cutiPegawaiEntity.getNip())) {
                            returnCutiPegawai.setCekatasan(true);
                        }
                    }

                    returnCutiPegawai.setJenisCuti(cutiPegawaiEntity.getJenisCuti());
                    returnCutiPegawai.setAction(cutiPegawaiEntity.getAction());
                    returnCutiPegawai.setFlag(cutiPegawaiEntity.getFlag());
                    returnCutiPegawai.setCreatedWho(cutiPegawaiEntity.getCreatedWho());
                    returnCutiPegawai.setCreatedDate(cutiPegawaiEntity.getCreatedDate());
                    returnCutiPegawai.setLastUpdate(cutiPegawaiEntity.getLastUpdate());
                    if(cutiPegawaiEntity.getClosed() != null){
                        if(cutiPegawaiEntity.getClosed().equals("Y") ){
                            returnCutiPegawai.setCutiPegawaiClosed(true);
                        }
                    }
                    if(cutiPegawaiEntity.getCancelFlag() != null){
                        if(cutiPegawaiEntity.getCancelFlag().equals("Y") ){
                            returnCutiPegawai.setCancel(true);
                        }
                    }

                    //edit ini
                    if (cutiPegawaiEntity.getApprovalFlag()!=null){
                        if(cutiPegawaiEntity.getApprovalFlag().equals("Y")){
                            returnCutiPegawai.setCutiPegawaiApprove(true);
                        }else if(cutiPegawaiEntity.getApprovalFlag().equals("N")){
                            returnCutiPegawai.setNotApprove(true);
                        }
                    }
                    if(cutiPegawaiEntity.getApprovalId() != null){
                        returnCutiPegawai.setCutiPegawaiApproveStatus(true);
                    }

                    if (cutiPegawaiEntity.getApprovalFlag() != null){
                        if (cutiPegawaiEntity.getApprovalFlag().equals("Y")){
                            returnCutiPegawai.setFinish(true);
                        }
                    }
                    //perubahan irfan
                    returnCutiPegawai.setCanCancel(true);
//                    String batalCuti = cutiPegawaiDao.findCutiToBatal(cutiPegawaiEntity.getNip());
//                    if (batalCuti!=null){
//                        if (!batalCuti.equalsIgnoreCase("")){
//                            if (batalCuti.equalsIgnoreCase(returnCutiPegawai.getCutiPegawaiId()))
//                            returnCutiPegawai.setCanCancel(true);
//                        }
//                    }
//                    java.sql.Date now =new java.sql.Date(System.currentTimeMillis());
//                    if (now.before(cutiPegawaiEntity.getTanggalDari())){
//                        returnCutiPegawai.setCanCancel(true);
//                    }
//                    List<ItCutiPegawaiEntity> lastCuti= cutiPegawaiDao.getLastCutiPegawai(cutiPegawaiEntity.getNip());
//                    for (ItCutiPegawaiEntity cutiPegawaiEntity1 : lastCuti ){
//                        if (cutiPegawaiEntity1.getCutiPegawaiId().equalsIgnoreCase(cutiPegawaiEntity.getCutiPegawaiId())){
//                            returnCutiPegawai.setCanCancel(true);
//                        }
//                    }


                    if (cutiPegawaiEntity.getClosed()!=null){
                        if (!cutiPegawaiEntity.getClosed().equalsIgnoreCase("Y")||searchBean.isForReset()){
                            listOfResult.add(returnCutiPegawai);
                        }
                    }else{
                        listOfResult.add(returnCutiPegawai);
                    }
                }
            }
        }
        logger.info("[CutiPegawaiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<CutiPegawai> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<CutiPegawai> getComboCutiPegawaiWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<CutiPegawai> listComboCutiPegawai = new ArrayList();
        String criteria = "%" + query + "%";

        List<ItCutiPegawaiEntity> listCutiPegawai = null;
        try {
            listCutiPegawai = cutiPegawaiDao.getListCutiPegawai(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listCutiPegawai != null) {
            for (ItCutiPegawaiEntity itCutiPegawaiEntity : listCutiPegawai) {
                CutiPegawai itemComboCutiPegawai = new CutiPegawai();
                itemComboCutiPegawai.setCutiPegawaiId(itCutiPegawaiEntity.getCutiPegawaiId());
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboCutiPegawai;
    }
    @Override
    public List<CutiPegawai> getListCekNipCuti(String nip) throws GeneralBOException {
        logger.info("[cutiPegawaiBoimpl.getListCekNipCuti] start process >>>");

        List<CutiPegawai> listComboCutiPegawai = new ArrayList();

        List<ItCutiPegawaiEntity> listCutiPegawai = null;
        try {
            listCutiPegawai = cutiPegawaiDao.getListCekCuti(nip);
        } catch (HibernateException e) {
            logger.error("[cutiPegawaiBoimpl.getListCekNipCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listCutiPegawai != null) {
            for (ItCutiPegawaiEntity itCutiPegawaiEntity : listCutiPegawai) {
                CutiPegawai itemComboCutiPegawai = new CutiPegawai();
                itemComboCutiPegawai.setCutiPegawaiId(itCutiPegawaiEntity.getCutiPegawaiId());
                listComboCutiPegawai.add(itemComboCutiPegawai);
            }
        }
        logger.info("[cutiPegawaiBoimpl.getListCekNipCuti] end process <<<");
        return listComboCutiPegawai;
    }

    @Override
    public List<CutiPegawai> getHistoryCuti(String nip,String cutiId) throws GeneralBOException {
        logger.info("[cutiPegawaiBoimpl.getHistoryCuti] start process >>>");
        List<CutiPegawai> listComboCutiPegawai = new ArrayList();

        List<ItCutiPegawaiEntity> listCutiPegawai = null;
        try {
            listCutiPegawai = cutiPegawaiDao.getListHistoryCutiPegawai(nip,cutiId);
        } catch (HibernateException e) {
            logger.error("[cutiPegawaiBoimpl.getHistoryCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        if (listCutiPegawai != null) {
            for (ItCutiPegawaiEntity itCutiPegawaiEntity : listCutiPegawai) {
                CutiPegawai itemComboCutiPegawai = new CutiPegawai();
                itemComboCutiPegawai.setCutiPegawaiId(itCutiPegawaiEntity.getCutiPegawaiId());
                itemComboCutiPegawai.setCutiId(itCutiPegawaiEntity.getCutiId());
                itemComboCutiPegawai.setPegawaiPenggantiSementara(itCutiPegawaiEntity.getPegawaiPenggantiSementara());
                itemComboCutiPegawai.setCutiName(itCutiPegawaiEntity.getCutiName());
                itemComboCutiPegawai.setLamaHariCuti(itCutiPegawaiEntity.getLamaHariCuti());
                itemComboCutiPegawai.setSisaCutiHari(itCutiPegawaiEntity.getSisaCutiHari());
                itemComboCutiPegawai.setApprovalId(itCutiPegawaiEntity.getApprovalId());
                itemComboCutiPegawai.setApprovalDate(itCutiPegawaiEntity.getApprovalDate());
                itemComboCutiPegawai.setApprovalFlag(itCutiPegawaiEntity.getApprovalFlag());
                itemComboCutiPegawai.setNote(itCutiPegawaiEntity.getNote());
                itemComboCutiPegawai.setTanggalDari(itCutiPegawaiEntity.getTanggalDari());
                itemComboCutiPegawai.setTanggalSelesai(itCutiPegawaiEntity.getTanggalSelesai());
                itemComboCutiPegawai.setNoteApproval(itCutiPegawaiEntity.getNoteApproval());
                itemComboCutiPegawai.setCancelFlag(itCutiPegawaiEntity.getCancelFlag());
                itemComboCutiPegawai.setAlamatCuti(itCutiPegawaiEntity.getAlamatCuti());
                itemComboCutiPegawai.setKeterangan(itCutiPegawaiEntity.getKeterangan());
                listComboCutiPegawai.add(itemComboCutiPegawai);
            }
        }
        logger.info("[cutiPegawaiBoimpl.getHistoryCuti] end process <<<");
        return listComboCutiPegawai;
    }
    public List<CutiPanjang> getComboCutiPanjangFull(String golonganId,String branchId) throws GeneralBOException {
        logger.info("[CutiPegawaiBOImpl.getComboCutiPegawaiFullWithCriteria] start process >>>");
        List<CutiPanjang> listComboCutiPanjang = new ArrayList();
        if(golonganId!=""||branchId!=""){
            Map hsCriteria = new HashMap();

            if (golonganId!=null&&!"".equalsIgnoreCase(golonganId)){
                hsCriteria.put("golongan_id",golonganId);
            }
            if(branchId!=null&&!"".equalsIgnoreCase(branchId)){
                hsCriteria.put("branch_id",branchId);
            }
            hsCriteria.put("flag","Y");

            List<ImCutiPanjangEntity> listCutiPanjang=null;
            try {
                listCutiPanjang = cutiPanjangDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.getSearchCutiPegawaiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if (listCutiPanjang!=null){
                for (ImCutiPanjangEntity imCutiPanjangEntity : listCutiPanjang) {
                    CutiPanjang itemComboCutiPanjang = new CutiPanjang();
                    itemComboCutiPanjang.setJumlahCuti(imCutiPanjangEntity.getJumlahCuti());
                    itemComboCutiPanjang.setTipeHari(imCutiPanjangEntity.getTipeHari());
                    itemComboCutiPanjang.setGolonganId(imCutiPanjangEntity.getGolonganId());
                    itemComboCutiPanjang.setBranchId(imCutiPanjangEntity.getBranchId());
                    listComboCutiPanjang.add(itemComboCutiPanjang);
                }
            }

        }
        return listComboCutiPanjang;
    }

    //get sisa cuti
    public List<CutiPegawai> getComboSisaCutiPegawaiWithCriteria(String query,String cutiId,String branchId) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<CutiPegawai> listComboSisaCutiPegawai = new ArrayList();

        List<ItCutiPegawaiEntity> listSisaCutiPegawai = null;
        try {
            listSisaCutiPegawai = cutiPegawaiDao.getListSisaCutiPegawai(query,cutiId);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSisaCutiPegawai.size()!=0) {
            for (ItCutiPegawaiEntity itCutiPegawaiEntity : listSisaCutiPegawai) {
                CutiPegawai itemComboCutiPegawai = new CutiPegawai();
                itemComboCutiPegawai.setCutiPegawaiId(itCutiPegawaiEntity.getCutiPegawaiId());
                itemComboCutiPegawai.setSisaCutiHari(itCutiPegawaiEntity.getSisaCutiHari());

                /*if (("CT002").equalsIgnoreCase(cutiId)){
                    List<ItCutiPegawaiEntity> listCutiPanjang = cutiPegawaiDao.getListCutiPanjangNip(query);
                    if (listCutiPanjang.size()!=0){
                        itemComboCutiPegawai.setSisaCutiHari(BigInteger.ZERO);
                    }
                }*/
                listComboSisaCutiPegawai.add(itemComboCutiPegawai);
            }
        }
        //ada kesalahan disini
        else if (listSisaCutiPegawai.size()==0){
                CutiPegawai itemComboCutiPegawai = new CutiPegawai();
                List<ImCutiEntity> cutiList = new ArrayList<>();
                List<ImCutiPanjangEntity> cutiPanjangEntityList = new ArrayList<>();
                ImBiodataEntity biodataEntity = new ImBiodataEntity();
            /*if (cutiId.equalsIgnoreCase("CT006")){
                biodataEntity=biodataDao.getById("nip",query,"Y");
                cutiPanjangEntityList=cutiPanjangDao.getListCutiPanjangBygolonganAndBranch(biodataEntity.getGolongan(),branchId);
                for (ImCutiPanjangEntity cutiPanjangEntity:cutiPanjangEntityList){
                    itemComboCutiPegawai.setSisaCutiHari(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()));
                }
            }else{
                cutiList =  cutiDao.getListCutiTipe(cutiId);
                for (ImCutiEntity cuti:cutiList){
                    itemComboCutiPegawai.setSisaCutiHari(BigInteger.valueOf(cuti.getJumlahCuti()));
                }
            }
            if (("CT002").equalsIgnoreCase(cutiId)){
                List<ItCutiPegawaiEntity> listCutiPanjang = cutiPegawaiDao.getListCutiPanjangNip(query);
                if (listCutiPanjang.size()!=0){
                    itemComboCutiPegawai.setSisaCutiHari(BigInteger.ZERO);
                }
            }*/
                itemComboCutiPegawai.setSisaCutiHari(BigInteger.ZERO);
                listComboSisaCutiPegawai.add(itemComboCutiPegawai);
            }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSisaCutiPegawai;
    }


    //get sisa
    @Override
    public List getComboTestTanggal(String nip, String tanggalAwal, String tanggalSelesai) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<CutiPegawai> listComboSisaCutiPegawai = new ArrayList();
        List listSisaCutiPegawai = null;
        try {
            listSisaCutiPegawai = cutiPegawaiDao.getComboTestTanggal(nip,tanggalAwal,tanggalSelesai);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listSisaCutiPegawai;
    }

    @Override
    public List getCriteriaForResetCuti(String unit) throws GeneralBOException {
        logger.info("[UserBoImpl.getCriteriaForResetCuti] start process >>>");
        String tahun;
        List<ImBiodataEntity> biodataEntityList;
        List<CutiPegawai> listCutiPegawai = new ArrayList();
        try {
            biodataEntityList = cutiPegawaiDao.getPegawaiCuti(unit);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getCriteriaForResetCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        for (ImBiodataEntity imBiodataEntity:biodataEntityList){
            String statusUnit = cutiPegawaiDao.getUnitByNip(imBiodataEntity.getNip());
            if (unit.equalsIgnoreCase(statusUnit)){
                CutiPegawai result = getSisaCuti(imBiodataEntity.getNip());
                result.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                result.setNip(imBiodataEntity.getNip());
                result.setTanggalAktif(imBiodataEntity.getTanggalAktif());
                if(imBiodataEntity.getTanggalAktif() != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String stringTanggal  = dateFormat.format(imBiodataEntity.getTanggalAktif());
                    result.setStTanggalAktif(stringTanggal);
                    result.setTanggalAktif(imBiodataEntity.getTanggalAktif());
                }else{
                    result.setStTanggalAktif("");
                }

                if (result.getSisaCutiTahunan()!=null){
                    if (Integer.parseInt(result.getSisaCutiTahunan())<0){
                        result.setSetelahResetCutiTahunan(BigInteger.valueOf(12+Integer.parseInt(result.getSisaCutiTahunan())));
                    }else{
                        result.setSetelahResetCutiTahunan(BigInteger.valueOf(12));
                    }
                }else{
                    //tambahan irfan
                    result.setSisaCutiTahunan("0");
                    //
                    result.setSetelahResetCutiTahunan(BigInteger.valueOf(12));
                }

                if (result.getSisaCutiPanjang()!=null){
                    if (imBiodataEntity.getGolongan()!=null){
                        if (!("").equalsIgnoreCase(imBiodataEntity.getGolongan())){
                            List<ImCutiPanjangEntity> cutiPanjangEntityList;
                            cutiPanjangEntityList = cutiPanjangDao.getListCutiPanjangBygolonganAndBranch(imBiodataEntity.getGolongan(),"KD01");
                            for (ImCutiPanjangEntity cutiPanjangEntity : cutiPanjangEntityList){
                                if (Integer.parseInt(result.getSisaCutiTahunan())<0){
                                    result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()+Integer.parseInt(result.getSisaCutiTahunan())));
                                }else{
                                    result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()));
//                            if(Integer.parseInt(result.getSisaCutiTahunan())<12){
//                                result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()-(12-Integer.parseInt(result.getSisaCutiTahunan()))));
//                            }else{
//                            }
                                }
                            }
                        }
                    }
                }
                //tambahan irfan
                else{
                    result.setSisaCutiPanjang("0");
                    List<ImCutiPanjangEntity> cutiPanjangEntityList;
                    cutiPanjangEntityList = cutiPanjangDao.getListCutiPanjangBygolonganAndBranch(imBiodataEntity.getGolongan(),"KD01");
                    for (ImCutiPanjangEntity cutiPanjangEntity : cutiPanjangEntityList){
                        if (Integer.parseInt(result.getSisaCutiTahunan())<0){
                            result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()+Integer.parseInt(result.getSisaCutiTahunan())));
                        }else{
                            result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()));
                        }
                    }
                }
                //
                try {
                    tahun = cutiPanjangDao.cekResetCutiPanjang(result.getNip());
                }catch (HibernateException e) {
                    logger.error("[UserBoImpl.getCriteriaForResetCuti] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                }
                Calendar now = Calendar.getInstance();
                String currentYear = String.valueOf( now.get(Calendar.YEAR));

                if (tahun == null){
                    listCutiPegawai.add(result);
                }
                else{
                    if(!tahun.equalsIgnoreCase(currentYear)){
                        listCutiPegawai.add(result);
                    }
                }
            }
//            Map hsCriteria = new HashMap();
//            hsCriteria.put("nip",result.getNip());
//            hsCriteria.put("cuti_id","CT002");
//            hsCriteria.put("flag","Y");
//            List<ItCutiPegawaiEntity> cutiPegawaiEntityList = cutiPegawaiDao.getByCriteria(hsCriteria);
//            if (cutiPegawaiEntityList.size()==0){
//            }
        }
        logger.info("[UserBoImpl.getCriteriaForResetCuti] end process <<<");
        return listCutiPegawai;
    }
    @Override
    public List getCriteriaForInisialisasiCuti(String unit) throws GeneralBOException {
        logger.info("[UserBoImpl.getCriteriaForInisialisasiCuti] start process >>>");

        List<Biodata> biodataList;
        List<CutiPegawai> listCutiPegawai = new ArrayList();
        try {
            biodataList = biodataDao.getBiodataByUnitAndNip(unit,"");
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getCriteriaForInisialisasiCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        for (Biodata biodata:biodataList){
            CutiPegawai result = getSisaCuti(biodata.getNip());
            result.setNamaPegawai(biodata.getNamaPegawai());
            result.setNip(biodata.getNip());
            if (result.getSisaCutiTahunan()==null){
                result.setSisaCutiTahunan("0");
            }
            if (result.getSisaCutiPanjang()==null){
                result.setSisaCutiPanjang("0");
            }
            result.setStSetelahResetCutiTahunan("");
            result.setStSetelahResetCutiPanjang("");

            listCutiPegawai.add(result);
        }
        logger.info("[UserBoImpl.getCriteriaForInisialisasiCuti] end process <<<");
        return listCutiPegawai;
    }
    @Override
    public List getCriteriaForCutiBersama(String unit) throws GeneralBOException {
        logger.info("[UserBoImpl.getCriteriaForResetCuti] start process >>>");

        List<ImBiodataEntity> biodataEntityList;
        List<CutiPegawai> listCutiPegawai = new ArrayList();
        try {
            biodataEntityList = cutiPegawaiDao.getPegawaiCuti(unit);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getCriteriaForResetCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        for (ImBiodataEntity imBiodataEntity:biodataEntityList){
            try{
                //cek apakah pegawai komisaris? jika tidak maka akan ditampilkan
                String statusKomisaris = cutiPegawaiDao.cekIfKomisaris(imBiodataEntity.getNip());
                if (statusKomisaris.equalsIgnoreCase("tidak")){
                    CutiPegawai result = getSisaCuti(imBiodataEntity.getNip());
                    result.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                    result.setNip(imBiodataEntity.getNip());
                    result.setTanggalAktif(imBiodataEntity.getTanggalAktif());
                    if(imBiodataEntity.getTanggalAktif() != null){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        String stringTanggal  = dateFormat.format(imBiodataEntity.getTanggalAktif());
                        result.setStTanggalAktif(stringTanggal);
                        result.setTanggalAktif(imBiodataEntity.getTanggalAktif());
                    }else{
                        result.setStTanggalAktif("");
                    }

                    if (result.getSisaCutiTahunan()!=null){
                        if (Integer.parseInt(result.getSisaCutiTahunan())<0){
                            result.setSetelahResetCutiTahunan(BigInteger.valueOf(12+Integer.parseInt(result.getSisaCutiTahunan())));
                        }else{
                            result.setSetelahResetCutiTahunan(BigInteger.valueOf(12));
                        }
                    }else{
                        result.setSetelahResetCutiTahunan(BigInteger.valueOf(12));
                    }
                    if (result.getSisaCutiPanjang()!=null){
                        if (imBiodataEntity.getGolongan()!=null){
                            if (!("").equalsIgnoreCase(imBiodataEntity.getGolongan())){
                                List<ImCutiPanjangEntity> cutiPanjangEntityList;
                                cutiPanjangEntityList = cutiPanjangDao.getListCutiPanjangBygolonganAndBranch(imBiodataEntity.getGolongan(),"KD01");
                                for (ImCutiPanjangEntity cutiPanjangEntity : cutiPanjangEntityList){
                                    if (Integer.parseInt(result.getSisaCutiTahunan())<0){
                                        result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()+Integer.parseInt(result.getSisaCutiTahunan())));
                                    }else{
                                        if(Integer.parseInt(result.getSisaCutiTahunan())<12){
                                            result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()-(12-Integer.parseInt(result.getSisaCutiTahunan()))));
                                        }else{
                                            result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()));
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (result.getSisaCutiTahunan()==null){
                        result.setSisaCutiTahunan("0");
                    }
                    if (result.getSisaCutiPanjang()==null){
                        result.setSisaCutiPanjang("0");
                    }

                    listCutiPegawai.add(result);
                }
            }catch (HibernateException e) {
                logger.error("[UserBoImpl.getCriteriaForResetCuti] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[UserBoImpl.getCriteriaForResetCuti] end process <<<");
        return listCutiPegawai;
    }
//    @Override
//    public List getCriteriaForInisialisasiCuti(String unit) throws GeneralBOException {
//        logger.info("[UserBoImpl.getCriteriaForInisialisasiCuti] start process >>>");
//
//        List<ImBiodataEntity> biodataEntityList;
//        List<CutiPegawai> listCutiPegawai = new ArrayList();
//        try {
//            biodataEntityList = cutiPegawaiDao.getPegawaiCuti(unit);
//        } catch (HibernateException e) {
//            logger.error("[UserBoImpl.getCriteriaForInisialisasiCuti] Error, " + e.getMessage());
//            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
//        }
//        for (ImBiodataEntity imBiodataEntity:biodataEntityList){
//            CutiPegawai result = getSisaCuti(imBiodataEntity.getNip());
//            result.setNamaPegawai(imBiodataEntity.getNamaPegawai());
//            result.setNip(imBiodataEntity.getNip());
//            result.setTanggalAktif(imBiodataEntity.getTanggalAktif());
//            if(imBiodataEntity.getTanggalAktif() != null){
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                String stringTanggal  = dateFormat.format(imBiodataEntity.getTanggalAktif());
//                result.setStTanggalAktif(stringTanggal);
//                result.setTanggalAktif(imBiodataEntity.getTanggalAktif());
//            }else{
//                result.setStTanggalAktif("");
//            }
//            listCutiPegawai.add(result);
//        }
//        logger.info("[UserBoImpl.getCriteriaForInisialisasiCuti] end process <<<");
//        return listCutiPegawai;
//    }
    @Override
    public CutiPegawai getSisaCuti(String nip) throws GeneralBOException {
        logger.info("[CutiBoImpl.getSisaCuti] start process >>>");
        List<ItCutiPegawaiEntity> cutiPegawaiListCutiTahunan;
        List<ItCutiPegawaiEntity> cutiPegawaiListCutiPanjang;
        List<ImCutiPanjangEntity> cutiPanjangEntityList;
        CutiPegawai result = new CutiPegawai();
        String branchId = "";
        try {
            cutiPegawaiListCutiTahunan = cutiPegawaiDao.getJumlahHariCuti(nip,"CT002");
            cutiPegawaiListCutiPanjang = cutiPegawaiDao.getJumlahHariCuti(nip,"CT006");
        } catch (HibernateException e) {
            logger.error("[CutiBoImpl.getSisaCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        for (ItCutiPegawaiEntity cutiPegawaiEntity : cutiPegawaiListCutiPanjang){
            result.setSisaCutiPanjang(String.valueOf(cutiPegawaiEntity.getSisaCutiHari()));
        }
        for (ItCutiPegawaiEntity cutiPegawaiEntity : cutiPegawaiListCutiTahunan){
            result.setSisaCutiTahunan(String.valueOf(cutiPegawaiEntity.getSisaCutiHari()));
        }
        logger.info("[CutiBoImpl.getSisaCuti] end process <<<");
        return result;
    }

    @Override
    public List<Notifikasi> saveApprove(CutiPegawai bean) throws GeneralBOException {
        logger.info("[CutiPegawaiBoImpl.saveEdit] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        boolean minus=false;
        if (bean!=null) {
            String CutiPegawaiId = bean.getCutiPegawaiId();
            ItCutiPegawaiEntity itCutiPegawaiEntity = null;
            try {
                // Get data from database by ID
                itCutiPegawaiEntity = cutiPegawaiDao.getById("cutiPegawaiId", CutiPegawaiId);
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data CutiPegawai by Kode CutiPegawai, please inform to your admin...," + e.getMessage());
            }
            if (itCutiPegawaiEntity != null) {
                itCutiPegawaiEntity.setCutiPegawaiId(bean.getCutiPegawaiId());
                itCutiPegawaiEntity.setFlag(bean.getFlag());
                //Approve
                if(bean.getTmpApprove().equals("atasan")) {
                    if (bean.getApprovalFlag().equals("Y")) {
                        itCutiPegawaiEntity.setApprovalFlag("Y");
                        itCutiPegawaiEntity.setPegawaiPenggantiSementara(bean.getPegawaiPenggantiSementara());
                    } else {
                        itCutiPegawaiEntity.setNoteApproval(bean.getNoteApproval());
                        itCutiPegawaiEntity.setApprovalFlag("N");
                    }
                    itCutiPegawaiEntity.setApprovalId(bean.getUserIdActive());
                    itCutiPegawaiEntity.setApprovalDate(bean.getLastUpdate());
                }
                itCutiPegawaiEntity.setAction(bean.getAction());
                itCutiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itCutiPegawaiEntity.setLastUpdate(bean.getLastUpdate());

                CutiPegawai sisaCuti = getSisaCuti(itCutiPegawaiEntity.getNip());
                if (itCutiPegawaiEntity.getSisaCutiHari().compareTo(BigInteger.ZERO) < 0&&itCutiPegawaiEntity.getCutiId().equalsIgnoreCase("CT002")){
                    if (sisaCuti.getSisaCutiPanjang()!=null){
                        if (Integer.valueOf(sisaCuti.getSisaCutiPanjang())>0){
                            if (bean.getApprovalFlag().equalsIgnoreCase("Y")){
                                minus=true;
                            }
                        }
                    }
                }

                if (minus){
                    BigInteger selisih= itCutiPegawaiEntity.getSisaCutiHari().abs();
                    itCutiPegawaiEntity.setSisaCutiHari(BigInteger.valueOf(0));

                    ItCutiPegawaiEntity saveCutiPanjangUpdate= new ItCutiPegawaiEntity();
                    saveCutiPanjangUpdate.setCutiPegawaiId(cutiPegawaiDao.getNextCutiPegawaiId());
                    saveCutiPanjangUpdate.setAlamatCuti(itCutiPegawaiEntity.getAlamatCuti());
                    saveCutiPanjangUpdate.setPegawaiPenggantiSementara(itCutiPegawaiEntity.getPegawaiPenggantiSementara());
                    saveCutiPanjangUpdate.setNip(itCutiPegawaiEntity.getNip());
                    saveCutiPanjangUpdate.setLamaHariCuti(BigInteger.ZERO);
                    saveCutiPanjangUpdate.setTanggalDari(itCutiPegawaiEntity.getTanggalDari());
                    saveCutiPanjangUpdate.setTanggalSelesai(itCutiPegawaiEntity.getTanggalSelesai());
                    saveCutiPanjangUpdate.setFlag(itCutiPegawaiEntity.getFlag());
                    saveCutiPanjangUpdate.setAction(itCutiPegawaiEntity.getAction());
                    saveCutiPanjangUpdate.setCreatedDate(itCutiPegawaiEntity.getCreatedDate());
                    saveCutiPanjangUpdate.setCreatedWho(itCutiPegawaiEntity.getCreatedWho());
                    saveCutiPanjangUpdate.setCancelFlag(itCutiPegawaiEntity.getCancelFlag());
                    saveCutiPanjangUpdate.setKeterangan(itCutiPegawaiEntity.getKeterangan());
                    saveCutiPanjangUpdate.setCutiId("CT006");
                    saveCutiPanjangUpdate.setSisaCutiHari(BigInteger.valueOf(Long.valueOf(sisaCuti.getSisaCutiPanjang())).subtract(selisih));
                    saveCutiPanjangUpdate.setApprovalFlag("Y");
                    saveCutiPanjangUpdate.setApprovalId(bean.getUserIdActive());
                    saveCutiPanjangUpdate.setApprovalDate(bean.getLastUpdate());
                    saveCutiPanjangUpdate.setAction(bean.getAction());
                    saveCutiPanjangUpdate.setLastUpdateWho(bean.getLastUpdateWho());
                    saveCutiPanjangUpdate.setLastUpdate(bean.getLastUpdate());

                    cutiPegawaiDao.addAndSave(saveCutiPanjangUpdate);
                }
                try {
                    // Update into database
                    cutiPegawaiDao.updateAndSave(itCutiPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                }

                // SEND NOTIF
                ImBiodataEntity imBiodataEntity =  biodataDao.getById("nip", itCutiPegawaiEntity.getNip(), "Y");
                Integer kelompok = personilPositionDao.getKelompokPosition(itCutiPegawaiEntity.getNip());

                if (bean.getApprovalFlag().equals("Y")) {
                    //Send notif ke orang yang mengajukan
                    Notifikasi notifSelf= new Notifikasi();
                    notifSelf.setNip(itCutiPegawaiEntity.getNip());
                    notifSelf.setNoRequest(bean.getCutiPegawaiId());
                    notifSelf.setTipeNotifId("umum");
                    notifSelf.setTipeNotifName(("Cuti Pegawai"));
                    notifSelf.setNote("Cuti anda pada tanggal "+CommonUtil.convertDateToString(itCutiPegawaiEntity.getTanggalDari())+" sampai dengan tanggal "+CommonUtil.convertDateToString(itCutiPegawaiEntity.getTanggalSelesai())+" di approve oleh atasan anda");
                    notifSelf.setCreatedWho(itCutiPegawaiEntity.getNip());
                    notifSelf.setTo("self");

                    notifikasiList.add(notifSelf);

                    Notifikasi notifAtasan = new Notifikasi();
                    notifAtasan.setNip(itCutiPegawaiEntity.getNip());
                    notifAtasan.setNoRequest(bean.getCutiPegawaiId());
                    notifAtasan.setTipeNotifId("umum");
                    notifAtasan.setTipeNotifName(("Cuti Pegawai"));
                    notifAtasan.setNote(imBiodataEntity.getNamaPegawai() + " mengajukan cuti pada tanggal " +CommonUtil.convertDateToString(itCutiPegawaiEntity.getTanggalDari()) + " sampai dengan tanggal " + CommonUtil.convertDateToString(itCutiPegawaiEntity.getTanggalSelesai()));
                    notifAtasan.setCreatedWho(itCutiPegawaiEntity.getNip());
                    notifAtasan.setTo("kabid");

                    notifikasiList.add(notifAtasan);

                    if (!"".equalsIgnoreCase(bean.getPegawaiPenggantiSementara())){
                        //Send notif ke orang yang mengajukan
                        Notifikasi notifElse= new Notifikasi();

                        notifElse.setNip(bean.getPegawaiPenggantiSementara());
                        notifElse.setNoRequest(bean.getCutiPegawaiId());
                        notifElse.setTipeNotifId("umum");
                        notifElse.setTipeNotifName(("Cuti Pegawai"));
                        notifElse.setNote("Tolong untuk bisa Menggantikan sementara " + imBiodataEntity.getNamaPegawai()+" pada Tanggal "+itCutiPegawaiEntity.getTanggalDari()+" sampai "+itCutiPegawaiEntity.getTanggalSelesai());
                        notifElse.setCreatedWho(itCutiPegawaiEntity.getNip());
                        notifElse.setTo("plt");

                        notifikasiList.add(notifElse);
                    }

                }else{
                    String msg="";
                    if (!("").equalsIgnoreCase(itCutiPegawaiEntity.getNoteApproval())){
                        msg="dikarenakan "+itCutiPegawaiEntity.getNoteApproval();
                    }
                    //Send notif ke orang yang mengajukan
//                    Notifikasi notifSelf= new Notifikasi();
//                    notifSelf.setNip(itCutiPegawaiEntity.getCutiPegawaiId());
//                    notifSelf.setNoRequest(bean.getCutiPegawaiId());
//                    notifSelf.setTipeNotifId("umum");
//                    notifSelf.setTipeNotifName(("Cuti Pegawai"));
//                    notifSelf.setNote("Cuti anda pada tanggal "+itCutiPegawaiEntity.getTanggalDari()+" sampai dengan tanggal "+itCutiPegawaiEntity.getTanggalSelesai()+" tidak di approve oleh atasan "+msg);
//                    notifSelf.setCreatedWho(itCutiPegawaiEntity.getNip());
//                    notifSelf.setTo("self");
//                    notifikasiList.add(notifSelf);

                    Notifikasi notifSelf= new Notifikasi();
                    notifSelf.setNip(itCutiPegawaiEntity.getNip());
                    notifSelf.setNoRequest(bean.getCutiPegawaiId());
                    notifSelf.setTipeNotifId("umum");
                    notifSelf.setTipeNotifName(("Cuti Pegawai"));
                    notifSelf.setNote("Cuti anda pada tanggal "+itCutiPegawaiEntity.getTanggalDari()+" sampai dengan tanggal "+itCutiPegawaiEntity.getTanggalSelesai()+" tidak di approve oleh atasan "+msg);
                    notifSelf.setCreatedWho(itCutiPegawaiEntity.getNip());
                    notifSelf.setTo("self");

                    notifikasiList.add(notifSelf);
                }
            } else {
                logger.error("[CutiPegawaiBoImpl.saveEdit] Error, not found data CutiPegawai with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data CutiPegawai with request id, please check again your data ...");
            }
        }
        logger.info("[CutiPegawaiBoImpl.saveEdit] end process <<<");
        return notifikasiList;
    }

    @Override
    public List<Object[]> findInfoCuti(String idCutiPegawai, String nip) throws GeneralBOException {
        logger.info("[CutiPegawaiBoImpl.findInfoCuti] start process >>>");
        List<Object[]> listCuti = null;

        try {
            listCuti = cutiPegawaiDao.findInfoCuti(idCutiPegawai, nip);
        } catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.findInfoCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[CutiPegawaiBoImpl.findInfoCuti] end process <<<");
        return listCuti;
    }

    @Override
    public List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws GeneralBOException {
        logger.info("[CutiPegawaiBoImpl.findAllConfirmByIdAtasan] start process >>>");
        List<Object[]> listConfirmStatus = null;

        try {
            listConfirmStatus = cutiPegawaiDao.findAllConfirmByIdAtasan(nip, flag);
        } catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.findAllConfirmByIdAtasan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[CutiPegawaiBoImpl.findAllConfirmByIdAtasan] end process <<<");
        return listConfirmStatus;
    }

    public List<CutiPegawai> getBiodatawithCriteria(String query) throws GeneralBOException {
        logger.info("[BiodataBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Biodata> listOfResult = new ArrayList();
        List<CutiPegawai>ListOfCutiPegawai = new ArrayList();
        Map hsCriteria = new HashMap();
        if (query!= null) {
                hsCriteria.put("nip", query);
                hsCriteria.put("flag", "Y");
            }
            List<ImBiodataEntity> imBiodataEntity = null;
            ItPersonilPositionEntity itPersonilPositionEntity = null;
            try {
                imBiodataEntity = biodataDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.getSearchPersonalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

        if(imBiodataEntity != null) {
            CutiPegawai returnCutiPegawai;
            // Looping from dao to object and save in collection
            for (ImBiodataEntity personalEntity : imBiodataEntity) {
                returnCutiPegawai = new CutiPegawai();
                returnCutiPegawai.setNip(personalEntity.getNip());
                returnCutiPegawai.setNamaPegawai(personalEntity.getNamaPegawai());
                returnCutiPegawai.setTanggalMasuk(personalEntity.getTanggalAktif());
                itPersonilPositionEntity = personilPositionDao.getById("nip",personalEntity.getNip(),"Y" );

                if(itPersonilPositionEntity.getImPosition() != null){
                    returnCutiPegawai.setPosisiId(itPersonilPositionEntity.getImPosition().getPositionId());
                }else{
                    returnCutiPegawai.setPosisiId("");
                }
                returnCutiPegawai.setProfesiId(itPersonilPositionEntity.getProfesiId());
                if (returnCutiPegawai.getPosisiId()!=null){
                    if (!"".equalsIgnoreCase(returnCutiPegawai.getPosisiId())){
                        ImPosition imPosition = positionDao.getById("positionId",returnCutiPegawai.getPosisiId());
                        returnCutiPegawai.setDivisiId(imPosition.getDepartmentId());
                    }
                }
                returnCutiPegawai.setUnitId(itPersonilPositionEntity.getImBranches().getPrimaryKey().getId());

                ListOfCutiPegawai.add(returnCutiPegawai);
            }
        }
        logger.info("[BiodataBoImpl.getByCriteria] end process <<<");

        return ListOfCutiPegawai;
    }

    @Override
    public List<CutiPegawai> sisaCutiSys(String nip) throws GeneralBOException {
        List<CutiPegawai> cutiPegawai = new ArrayList<>();
        List<ItCutiPegawaiEntity> itCutiPegawaiEntities = null;

        itCutiPegawaiEntities = cutiPegawaiDao.getSisaCutiSys(nip);
        if(itCutiPegawaiEntities != null){
            for(ItCutiPegawaiEntity itCutiPegawaiEntity: itCutiPegawaiEntities){
                CutiPegawai cutiPegawai1 = new CutiPegawai();
                cutiPegawai1.setCutiId(itCutiPegawaiEntity.getCutiId());
                cutiPegawai1.setCutiName(itCutiPegawaiEntity.getCutiName());
                cutiPegawai1.setJumlahCutihari(itCutiPegawaiEntity.getJumlahCuti());
                cutiPegawai1.setSisaCutiHari(itCutiPegawaiEntity.getSisaCutiHari());
                cutiPegawai.add(cutiPegawai1);
            }
        }

        return cutiPegawai;
    }

    @Override
    public List<CutiPegawai> getCutiUser(CutiPegawai bean){
        List<CutiPegawai> listOfCutiPegawai = new ArrayList<>();
        List<ImBiodataEntity> listOfPegawai= new ArrayList();

        try {
            if (!("".equalsIgnoreCase(bean.getNip()))){
                listOfPegawai = biodataDao.findUserCuti(bean.getNip());
            }else{
                listOfPegawai = biodataDao.findAllUserCuti();
            }
        } catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.getCutiUser] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
        }
        for (ImBiodataEntity pegawai : listOfPegawai){
            CutiPegawai result = new CutiPegawai();
            result.setNip(pegawai.getNip());
            result.setNamaPegawai(pegawai.getNamaPegawai());
            result.setTanggal(CommonUtil.convertToDate(bean.getStTanggalSelesai()));

            result.setSisaCutiTahunan(String.valueOf(sisaCutiTahunan(result)));
            result.setSisaCutiPanjang(String.valueOf(sisaCutiPanjang(result)));
            result.setStTanggalDari(bean.getStTanggalDari());
            result.setStTanggalSelesai(bean.getStTanggalSelesai());
            result.setFlag(pegawai.getFlag());

            listOfCutiPegawai.add(result);
        }

        return listOfCutiPegawai;
    }

    @Override
    public BigInteger sisaCutiTahunan(CutiPegawai search){
        BigInteger sisaCutiTahunan = BigInteger.valueOf(0);
        List<ItCutiPegawaiEntity> listOfCutiTahunan = new ArrayList<>();
        String cutiId="CT002";
        Date tanggal=search.getTanggal();
        try {
            listOfCutiTahunan = cutiPegawaiDao.getSisaCuti(search.getNip(),tanggal,cutiId);
        } catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.sisaCutiTahunan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
        }

        for (ItCutiPegawaiEntity cuti:listOfCutiTahunan){
            sisaCutiTahunan=cuti.getSisaCutiHari();
        }
        return sisaCutiTahunan;
    }
    @Override
    public BigInteger sisaCutiPanjang(CutiPegawai search){
        BigInteger sisaCutiTahunan = BigInteger.valueOf(0);
        List<ItCutiPegawaiEntity> listOfCutiTahunan = new ArrayList<>();
        String cutiId="CT006";
        Date tanggal=search.getTanggal();
        try {
            listOfCutiTahunan = cutiPegawaiDao.getSisaCuti(search.getNip(),tanggal,cutiId);
        } catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.sisaCutiTPanjang] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
        }

        for (ItCutiPegawaiEntity cuti:listOfCutiTahunan){
            sisaCutiTahunan=cuti.getSisaCutiHari();
        }
        return sisaCutiTahunan;
    }
    @Override
    public List<CutiPegawai> getComboSisaCutiPegawaiWithCriteria(String query) throws GeneralBOException {
        return null;
    }

    @Override
    public List<CutiPegawai> getListCutiForReport(CutiPegawai bean){
        List<ItCutiPegawaiEntity> cutiPegawaiEntityList = new ArrayList<>();
        List<CutiPegawai> listOfResult = new ArrayList<>();
        Date tanggalDari = CommonUtil.convertToDate(bean.getStTanggalDari());
        Date tanggalSelesai = CommonUtil.convertToDate(bean.getStTanggalSelesai());
        try {
            cutiPegawaiEntityList = cutiPegawaiDao.getListCutiReport(bean.getNip(),tanggalDari,tanggalSelesai);
        } catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.sisaCutiTPanjang] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
        }
        int i = 1;
        for (ItCutiPegawaiEntity cutiPegawaiEntity:cutiPegawaiEntityList){
            CutiPegawai result = new CutiPegawai();
            result.setNip(cutiPegawaiEntity.getNip());
            result.setTanggalDari(cutiPegawaiEntity.getTanggalDari());
            result.setTanggalSelesai(cutiPegawaiEntity.getTanggalSelesai());
            result.setStTanggalDari(CommonUtil.convertDateToString(cutiPegawaiEntity.getTanggalDari()));
            result.setStTanggalSelesai(CommonUtil.convertDateToString(cutiPegawaiEntity.getTanggalSelesai()));
            result.setAlamatCuti(cutiPegawaiEntity.getAlamatCuti());
            result.setLamaHariCuti(cutiPegawaiEntity.getLamaHariCuti());
            result.setKeterangan(cutiPegawaiEntity.getKeterangan());
            result.setTanggal(cutiPegawaiEntity.getTanggalSelesai());
            result.setSisaCutiTahunan(String.valueOf(sisaCutiTahunan(result)));
            result.setSisaCutiPanjang(String.valueOf(sisaCutiPanjang(result)));
            result.setNomor(i);
            listOfResult.add(result);
            i++;
        }
        return listOfResult;
    }

    @Override
    public List<CutiPegawai> getListSetCuti(String nip){
        List<ItCutiPegawaiEntity> cutiPegawaiEntityList = new ArrayList<>();
        List<CutiPegawai> listOfResult = new ArrayList<>();
        CutiPegawai result = new CutiPegawai();
        BigInteger sisaCutiTahunan = BigInteger.valueOf(0);
        BigInteger sisaCutiPanjang = BigInteger.valueOf(0);
        try {
            cutiPegawaiEntityList = cutiPegawaiDao.getListSisaCutiPegawai(nip,"CT002");
        } catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.getListSetCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
        }
        for (ItCutiPegawaiEntity cutiPegawaiEntity : cutiPegawaiEntityList){
            sisaCutiTahunan=cutiPegawaiEntity.getSisaCutiHari();
        }
        if (sisaCutiTahunan.equals(BigInteger.valueOf(0))){
            try {
                cutiPegawaiEntityList = cutiPegawaiDao.getListSisaCutiPegawai(nip,"CT006");
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.getListSetCuti] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
            }

            for (ItCutiPegawaiEntity cutiPegawaiEntity : cutiPegawaiEntityList){
                sisaCutiPanjang=cutiPegawaiEntity.getSisaCutiHari();
            }
            if (sisaCutiPanjang.equals(BigInteger.valueOf(0))){
                result.setSisaCutiHari(sisaCutiTahunan);
                result.setCutiId("CT002");
            }else{
                result.setSisaCutiHari(sisaCutiPanjang);
                result.setCutiId("CT006");
            }
        }else{
            result.setSisaCutiHari(sisaCutiTahunan);
            result.setCutiId("CT002");
        }
        listOfResult.add(result);
        return listOfResult;
    }

    @Override
    public List<CutiPegawai> getListSetCuti2(String nip, String jenisCuti){
        List<ItCutiPegawaiEntity> cutiPegawaiEntityList = new ArrayList<>();
        List<CutiPegawai> listOfResult = new ArrayList<>();
        CutiPegawai result = new CutiPegawai();
        BigInteger sisaCutiTahunan = BigInteger.valueOf(0);
        BigInteger sisaCutiPanjang = BigInteger.valueOf(0);
        BigInteger sisaCutiDiluarTanggungan = BigInteger.valueOf(0);

        if (jenisCuti.equalsIgnoreCase("normal")){
            try {
                cutiPegawaiEntityList = cutiPegawaiDao.getListSisaCutiPegawai(nip,"CT002");
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.getListSetCuti] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
            }
            for (ItCutiPegawaiEntity cutiPegawaiEntity : cutiPegawaiEntityList){
                sisaCutiTahunan=cutiPegawaiEntity.getSisaCutiHari();
            }
            if (sisaCutiTahunan.equals(BigInteger.valueOf(0))){
                try {
                    cutiPegawaiEntityList = cutiPegawaiDao.getListSisaCutiPegawai(nip,"CT006");
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.getListSetCuti] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
                }

                for (ItCutiPegawaiEntity cutiPegawaiEntity : cutiPegawaiEntityList){
                    sisaCutiPanjang=cutiPegawaiEntity.getSisaCutiHari();
                }
                if (sisaCutiPanjang.equals(BigInteger.valueOf(0))){
                    result.setSisaCutiHari(sisaCutiTahunan);
                    result.setCutiId("CT002");
                }else{
                    result.setSisaCutiHari(sisaCutiPanjang);
                    result.setCutiId("CT006");
                }
            }else{
                result.setSisaCutiHari(sisaCutiTahunan);
                result.setCutiId("CT002");
            }
        }else {
            try{
                cutiPegawaiEntityList = cutiPegawaiDao.getListSisaCutiPegawai(nip, "CT007");
            }catch (HibernateException e){
                logger.error("[CutiPegawaiBoImpl.getListSetCuti] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
            }

            for (ItCutiPegawaiEntity cutiPegawaiEntity : cutiPegawaiEntityList){
                sisaCutiDiluarTanggungan = cutiPegawaiEntity.getSisaCutiHari();
            }
            result.setSisaCutiHari(sisaCutiDiluarTanggungan);
            result.setCutiId("CT007");
        }

        listOfResult.add(result);
        return listOfResult;
    }
    @Override
    public List getListCutiForView(String nip) throws GeneralBOException {
        logger.info("[UserBoImpl.getListCutiForView] start process >>>");
        ImBiodataEntity imBiodataEntity=biodataDao.getById("nip",nip);
        List<CutiPegawai> listCutiPegawai = new ArrayList();
        CutiPegawai result = getSisaCuti(imBiodataEntity.getNip());


//        tambahan irfan
        List<CutiPegawai> dataPerusahaanPegawai = new ArrayList();
        try {
            dataPerusahaanPegawai = cutiPegawaiDao.getDataPerusahaanPegawai(nip);
            for (CutiPegawai cutiPegawaiLoop: dataPerusahaanPegawai){
                result.setUnitName(cutiPegawaiLoop.getUnitName());
                result.setPosisiName(cutiPegawaiLoop.getPosisiName());
                result.setBagian(cutiPegawaiLoop.getBagian());
                result.setDivisiName(cutiPegawaiLoop.getDivisiName());
                result.setUnitId(cutiPegawaiLoop.getUnitId());
            }
        }catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.getListSetCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
        }
//        =================================


        result.setNamaPegawai(imBiodataEntity.getNamaPegawai());
        result.setNip(imBiodataEntity.getNip());
        result.setTanggalAktif(imBiodataEntity.getTanggalAktif());
        if(imBiodataEntity.getTanggalAktif() != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String stringTanggal  = dateFormat.format(imBiodataEntity.getTanggalAktif());
            result.setStTanggalAktif(stringTanggal);
            result.setTanggalAktif(imBiodataEntity.getTanggalAktif());
        }else{
            result.setStTanggalAktif("");
        }
        if (result.getSisaCutiTahunan()!=null){
            if (Integer.parseInt(result.getSisaCutiTahunan())<0){
                result.setSetelahResetCutiTahunan(BigInteger.valueOf(12+Integer.parseInt(result.getSisaCutiTahunan())));
            }else{
                result.setSetelahResetCutiTahunan(BigInteger.valueOf(12));
            }
        }else{
            result.setSetelahResetCutiTahunan(BigInteger.valueOf(12));
        }
        if (result.getSisaCutiPanjang()!=null){
            if (imBiodataEntity.getGolongan()!=null){
                if (!("").equalsIgnoreCase(imBiodataEntity.getGolongan())){
                    List<ImCutiPanjangEntity> cutiPanjangEntityList;
                    cutiPanjangEntityList = cutiPanjangDao.getListCutiPanjangBygolonganAndBranch(imBiodataEntity.getGolongan(),"KD01");
                    for (ImCutiPanjangEntity cutiPanjangEntity : cutiPanjangEntityList){
                        if (Integer.parseInt(result.getSisaCutiTahunan())<0){
                            result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()+Integer.parseInt(result.getSisaCutiTahunan())));
                        }else{
                            if(Integer.parseInt(result.getSisaCutiTahunan())<12){
                                result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()-(12-Integer.parseInt(result.getSisaCutiTahunan()))));
                            }else{
                                result.setSetelahResetCutiPanjang(BigInteger.valueOf(cutiPanjangEntity.getJumlahCuti()));
                            }
                        }
                    }
                }
            }
        }
        if (result.getSisaCutiTahunan()==null){
            result.setSisaCutiTahunan("0");
        }
        if (result.getSisaCutiPanjang()==null){
            result.setSisaCutiPanjang("0");
        }
        listCutiPegawai.add(result);
        logger.info("[UserBoImpl.getListCutiForView] end process <<<");
        return listCutiPegawai;
    }

    public String findCutiAktif(String branchId){
        String status;
        status = cutiPegawaiDao.findCutiAktif(branchId);
        return status;
    }

    public String getBagianPegawai(String positionId){
        String bagian;
        bagian = cutiPegawaiDao.getBagianPegawai(positionId);
        return bagian;
    }

    public String cekIfAbsensi(String nip, String tglDari, String tglSelesai){
        String status ="";
        Date tgl;
        try {
            if (tglDari.equalsIgnoreCase(tglSelesai)){
                tgl = CommonUtil.convertStringToDate(tglDari);
                status = cutiPegawaiDao.cekIfAbsensi(nip,tgl);
            } else {
                Date dTglDari = CommonUtil.convertStringToDate(tglDari);
                Date dTglSelesaai = CommonUtil.convertStringToDate(tglSelesai);
                List<Date> datesInRange = new ArrayList<>();
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dTglDari);

                Calendar endCalendar = new GregorianCalendar();
                endCalendar.setTime(dTglSelesaai);
                String tanggal="";
                while (calendar.before(endCalendar)) {
                    tanggal = CommonUtil.convertDateToString(calendar.getTime());
//                    tgl = java.sql.Date.valueOf();
                    status = cutiPegawaiDao.cekIfAbsensi(nip,CommonUtil.convertStringToDate(tanggal));
                    if (status.equalsIgnoreCase("ya")){
                        break;
                    }else {
                        calendar.add(Calendar.DATE, 1);
                    }
                }

            }
        } catch (GeneralBOException e1) {
            logger.error("[TrainingAction.printSuratJaminan] Error when downloading ,", e1);
        }
        return status;
    }

    public void editSisaCuti(CutiPegawai bean){
        try{
            String latesCutiPegawaiId = cutiPegawaiDao.getLatesCutiPegawaiData(bean.getNip());
            ItCutiPegawaiEntity itCutiPegawaiEntity = null;
            if (!bean.getCutiPegawaiId().equalsIgnoreCase(latesCutiPegawaiId)){
                try {
                    itCutiPegawaiEntity = cutiPegawaiDao.getById("cutiPegawaiId", latesCutiPegawaiId);
                    if (itCutiPegawaiEntity != null) {

                        try {
                            // Update into database
                            BigInteger sisaCutiLama = itCutiPegawaiEntity.getSisaCutiHari();
                            itCutiPegawaiEntity.setSisaCutiHari(sisaCutiLama.add(bean.getLamaHariCuti()));
                            cutiPegawaiDao.updateAndSave(itCutiPegawaiEntity);
                        } catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                        }
                    } else {
                        logger.error("[CutiPegawaiBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                        throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
                    }


                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
                }
            }
        }catch (GeneralBOException e1) {
            logger.error("[TrainingAction.printSuratJaminan] Error when downloading ,", e1);
        }
    }

    public String getKabidSdmUmum(String branchId){
        String kabid="";
        try {
            kabid = cutiPegawaiDao.getKabid(branchId);
        }catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
        }
        return kabid;
    }

    public String getTanggalPensiun(String nip){
        String tanggalPensiun="";
        try {
            tanggalPensiun = cutiPegawaiDao.getTanggalPensiun(nip);
        }catch (HibernateException e) {
            logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
        }
        return tanggalPensiun;
    }
}
