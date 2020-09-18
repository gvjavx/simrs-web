package com.neurix.hris.transaksi.ijinKeluar.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golongan.dao.GolonganDao;
import com.neurix.hris.master.golongan.model.Golongan;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.ijin.dao.IjinDao;
import com.neurix.hris.master.ijin.model.ImIjinEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.transaksi.absensi.dao.AbsensiPegawaiDao;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawaiEntity;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
import com.neurix.hris.transaksi.ijinKeluar.dao.IjinKeluarAnggotaDao;
import com.neurix.hris.transaksi.ijinKeluar.dao.IjinKeluarDao;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarAnggota;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarAnggotaEntity;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarEntity;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiFcmDao;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
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
public class IjinKeluarBoImpl implements IjinKeluarBo {

    protected static transient Logger logger = Logger.getLogger(IjinKeluarBoImpl.class);
    private IjinKeluarDao ijinKeluarDao;
    private IjinKeluarAnggotaDao ijinKeluarAnggotaDao;
    private NotifikasiDao notifikasiDao;
    private NotifikasiFcmDao notifikasiFcmDao;
    private StrukturJabatanDao strukturJabatanDao;
    private BiodataDao biodataDao;
    private PersonilPositionDao personilPositionDao;
    private BranchDao branchDao;
    private DepartmentDao departmentDao;
    private PositionDao positionDao;
    private GolonganDao golonganDao;
    private IjinDao ijinDao;
    private AbsensiPegawaiDao absensiPegawaiDao;
    private String CLICK_ACTION = "TASK_IJIN";

    public AbsensiPegawaiDao getAbsensiPegawaiDao() {
        return absensiPegawaiDao;
    }

    public void setAbsensiPegawaiDao(AbsensiPegawaiDao absensiPegawaiDao) {
        this.absensiPegawaiDao = absensiPegawaiDao;
    }

    public IjinKeluarAnggotaDao getIjinKeluarAnggotaDao() {
        return ijinKeluarAnggotaDao;
    }

    public void setIjinKeluarAnggotaDao(IjinKeluarAnggotaDao ijinKeluarAnggotaDao) {
        this.ijinKeluarAnggotaDao = ijinKeluarAnggotaDao;
    }

    public IjinDao getIjinDao() {
        return ijinDao;
    }

    public void setIjinDao(IjinDao ijinDao) {
        this.ijinDao = ijinDao;
    }

    public void setNotifikasiFcmDao(NotifikasiFcmDao notifikasiFcmDao) {
        this.notifikasiFcmDao = notifikasiFcmDao;
    }

    public GolonganDao getGolonganDao() {
        return golonganDao;
    }

    public void setGolonganDao(GolonganDao golonganDao) {
        this.golonganDao = golonganDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

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
        IjinKeluarBoImpl.logger = logger;
    }

    public IjinKeluarDao getIjinKeluarDao() {
        return ijinKeluarDao;
    }

    public void setIjinKeluarDao(IjinKeluarDao ijinKeluarDao) {
        this.ijinKeluarDao = ijinKeluarDao;
    }

    public NotifikasiDao getNotifikasiDao() {
        return notifikasiDao;
    }

    public void setNotifikasiDao(NotifikasiDao notifikasiDao) {
        this.notifikasiDao = notifikasiDao;
    }

    @Override
    public void saveDelete(IjinKeluar bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String ijinKeluarId = bean.getIjinKeluarId();

            IjinKeluarEntity imIjinKeluarEntity = null;

            try {
                // Get data from database by ID
                imIjinKeluarEntity = ijinKeluarDao.getById("ijinKeluar", ijinKeluarId);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imIjinKeluarEntity != null) {

                // Modify from bean to entity serializable
                imIjinKeluarEntity.setIjinKeluarId(bean.getIjinKeluarId());
                imIjinKeluarEntity.setIjinId(bean.getIjinId());
                imIjinKeluarEntity.setIjinName(bean.getIjinName());
                imIjinKeluarEntity.setLamaIjin(bean.getLamaIjin());
                imIjinKeluarEntity.setNip(bean.getNip());
                imIjinKeluarEntity.setNamaPegawai(bean.getNamaPegawai());
                imIjinKeluarEntity.setKeterangan(bean.getKeterangan());
                imIjinKeluarEntity.setApprovalId(bean.getApprovalId());
                imIjinKeluarEntity.setApprovalName(bean.getApprovalName());
                imIjinKeluarEntity.setApprovalDate(bean.getApprovalDate());
                imIjinKeluarEntity.setNotApprovalNote(bean.getNotApprovalNote());
                imIjinKeluarEntity.setGolonganId(bean.getGolonganId());
                imIjinKeluarEntity.setGolonganName(bean.getGolonganName());
                imIjinKeluarEntity.setPositionId(bean.getPositionId());
                imIjinKeluarEntity.setPositionName(bean.getPositionName());
                imIjinKeluarEntity.setUnitId(bean.getUnitId());
                imIjinKeluarEntity.setTanggalAwal(bean.getTanggalAwal());
                imIjinKeluarEntity.setTanggalAkhir(bean.getTanggalAkhir());

                imIjinKeluarEntity.setFlag(bean.getFlag());
                imIjinKeluarEntity.setAction(bean.getAction());
                imIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    ijinKeluarDao.updateAndSave(imIjinKeluarEntity);
                } catch (HibernateException e) {
                    logger.error("[IjinKeluarBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[IjinKeluarBoImpl.saveDelete] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
            }
        }
        logger.info("[IjinKeluarBoImpl.saveDelete] end process <<<");
    }
    @Override
    public void saveEdit(IjinKeluar bean) throws GeneralBOException {
        logger.info("[IjinKeluarBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String ijinKeluarId = bean.getIjinKeluarId();
            IjinKeluarEntity imIjinKeluarEntity = null;

            try {
                // Get data from database by ID
                imIjinKeluarEntity = ijinKeluarDao.getById("ijinKeluarId", ijinKeluarId);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imIjinKeluarEntity != null) {
                //
                imIjinKeluarEntity.setIjinKeluarId(bean.getIjinKeluarId());
                imIjinKeluarEntity.setIjinId(bean.getIjinId());
//                imIjinKeluarEntity.setLamaIjin(bean.getLamaIjin());
                imIjinKeluarEntity.setLamaIjinOld(bean.getLamaIjin());

                imIjinKeluarEntity.setNip(bean.getNip());
                imIjinKeluarEntity.setNamaPegawai(bean.getNamaPegawai());
                imIjinKeluarEntity.setKeterangan(bean.getKeterangan());
                imIjinKeluarEntity.setGolonganId(bean.getGolonganId());
                imIjinKeluarEntity.setPositionId(bean.getPositionId());
                imIjinKeluarEntity.setUnitId(bean.getUnitId());
                imIjinKeluarEntity.setCancelFlag(bean.getCancelFlag());
                imIjinKeluarEntity.setCancelDate(bean.getCancelDate());
                imIjinKeluarEntity.setCancelPerson(bean.getCancelPerson());
                imIjinKeluarEntity.setCancelNote(bean.getCancelNote());

                if (!"Y".equalsIgnoreCase(bean.getCancelFlag())){
                    if ("Melahirkan".equalsIgnoreCase(bean.getIjinName())){
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            //tglMelahirkan
                            java.util.Date date = format.parse(bean.getStTglMelahirkan().toString());
                            Date tglMelahirkan = new Date(date.getTime());
                            imIjinKeluarEntity.setTglMelahirkan(tglMelahirkan);

                            //tglAkhirOld
                            java.util.Date date1 = format.parse(bean.getStTanggalAkhir().toString());
                            Date tglAkhirOld = new Date(date1.getTime());
                            imIjinKeluarEntity.setTanggalAkhirOld(tglAkhirOld);

                            //tglAkhirNew
//                    java.util.Date date2 = format.parse(bean.getTanggalAkhirBaru().toString());
//                    Date tglAkhirNew = new Date(date2.getTime());
//                    imIjinKeluarEntity.setTanggalAkhir(tglAkhirNew);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(tglMelahirkan);
                            calendar.add(Calendar.DAY_OF_MONTH, 45);
                            Date tglAkhirNew = new Date(calendar.getTimeInMillis());
                            imIjinKeluarEntity.setTanggalAkhir(tglAkhirNew);
                        } catch (ParseException e) {
                            logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                        }

                        imIjinKeluarEntity.setLamaIjin(bean.getLamaIjinBaru());
                    }
                }


                imIjinKeluarEntity.setFlag(bean.getFlag());
                imIjinKeluarEntity.setAction(bean.getAction());
                imIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());
                if (bean.getUploadFile() != null){
                    imIjinKeluarEntity.setSuratDokter(bean.getUploadFile());
                }


//                    imIjinKeluarEntity.setTglAkhirUpdate(bean.getTanggalAkhir());

                try {
                    // Update into database
                    ijinKeluarDao.updateAndSave(imIjinKeluarEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }

                //delete from notif
                if (("Y").equalsIgnoreCase(imIjinKeluarEntity.getCancelFlag())){
                    List<ImNotifikasiEntity> notifikasiEntityList = notifikasiDao.getDataByNoRequest(imIjinKeluarEntity.getIjinKeluarId(),imIjinKeluarEntity.getNip());

                    if (notifikasiEntityList!=null){
                        for (ImNotifikasiEntity notifikasiEntity : notifikasiEntityList){
                            notifikasiEntity.setFlag("N");

                            try {
                                // Update into database
                                notifikasiDao.updateAndSave(notifikasiEntity);
                            } catch (HibernateException e) {
                                logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                            }

                        }
                    }
                }
            } else {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
//                condition = "Error, not found data alat with request id, please check again your data ...";
            }
        }
        logger.info("[IjinKeluarBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void savePengajuanBatal(IjinKeluar bean) throws GeneralBOException {
        logger.info("[IjinKeluarBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String ijinKeluarId = bean.getIjinKeluarId();
            IjinKeluarEntity imIjinKeluarEntity = null;
            try {
                // Get data from database by ID
                imIjinKeluarEntity = ijinKeluarDao.getById("ijinKeluarId", ijinKeluarId);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imIjinKeluarEntity != null) {
                //
                imIjinKeluarEntity.setIjinKeluarId(bean.getIjinKeluarId());
                imIjinKeluarEntity.setIjinId(bean.getIjinId());
                imIjinKeluarEntity.setNip(bean.getNip());
                imIjinKeluarEntity.setCancelNote(bean.getCancelNote());
                imIjinKeluarEntity.setFlagPengajuanBatal(bean.getFlagPengajuanBatal());
                imIjinKeluarEntity.setFlag(bean.getFlag());
                imIjinKeluarEntity.setAction(bean.getAction());
                imIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    ijinKeluarDao.updateAndSave(imIjinKeluarEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
//                condition = "Error, not found data alat with request id, please check again your data ...";
            }
        }
        logger.info("[IjinKeluarBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void saveTolakPengajuanBatal(IjinKeluar bean) throws GeneralBOException {
        logger.info("[IjinKeluarBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String ijinKeluarId = bean.getIjinKeluarId();
            IjinKeluarEntity imIjinKeluarEntity = null;
            try {
                // Get data from database by ID
                imIjinKeluarEntity = ijinKeluarDao.getById("ijinKeluarId", ijinKeluarId);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imIjinKeluarEntity != null) {
                //
                imIjinKeluarEntity.setIjinKeluarId(bean.getIjinKeluarId());
                imIjinKeluarEntity.setFlagPengajuanBatal(bean.getFlagPengajuanBatal());
                imIjinKeluarEntity.setFlag(bean.getFlag());
                imIjinKeluarEntity.setAction(bean.getAction());
                imIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    ijinKeluarDao.updateAndSave(imIjinKeluarEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
//                condition = "Error, not found data alat with request id, please check again your data ...";
            }
        }
        logger.info("[IjinKeluarBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void saveEditIjinKeluarKantor(IjinKeluar bean) throws GeneralBOException {
        logger.info("[IjinKeluarBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String ijinKeluarId = bean.getIjinKeluarId();

            IjinKeluarEntity imIjinKeluarEntity = null;

            try {
                // Get data from database by ID
                imIjinKeluarEntity = ijinKeluarDao.getById("ijinKeluarId", ijinKeluarId);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imIjinKeluarEntity != null) {
                imIjinKeluarEntity.setIjinKeluarId(bean.getIjinKeluarId());
                imIjinKeluarEntity.setCancelFlag(bean.getCancelFlag());
                imIjinKeluarEntity.setCancelDate(bean.getCancelDate());
                imIjinKeluarEntity.setCancelPerson(bean.getCancelPerson());
                imIjinKeluarEntity.setCancelNote(bean.getCancelNote());

                imIjinKeluarEntity.setFlag(bean.getFlag());
                imIjinKeluarEntity.setAction(bean.getAction());
                imIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    ijinKeluarDao.updateAndSave(imIjinKeluarEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
//                condition = "Error, not found data alat with request id, please check again your data ...";
            }
        }
        logger.info("[IjinKeluarBoImpl.saveEdit] end process <<<");
    }
    @Override
    public IjinKeluar saveAdd(IjinKeluar bean) throws GeneralBOException {
        logger.info("[IjinKeluarBoImpl.saveAdd] start process >>>");

        logger.info("[IjinKeluarBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Notifikasi> saveAddIjinKeluar(IjinKeluar bean) throws GeneralBOException {
        logger.info("[IjinKeluarBoImpl.saveAdd] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        if (bean != null) {
            String status = cekStatusIjin(bean.getNip());
            if (!status.equalsIgnoreCase("exist")){

                // search data kelompok_id from im_positions by parameter parent
                List<ImPosition> imPositionList = null;
                try {
                    imPositionList = positionDao.getDataKelompokId(bean.getPositionId());
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }

                if ("KL44".equalsIgnoreCase(imPositionList.get(0).getKelompokId())){


                    if ("IJ010".equalsIgnoreCase(bean.getIjinId()) || "IJ032".equalsIgnoreCase(bean.getIjinId())){
                        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                        Biodata biodata1 = new Biodata();
                        BiodataBo biodataBo = (BiodataBo) context.getBean("biodataBoProxy");
                        biodata1.setNip(bean.getNip());
                        biodata1.setFlag("Y");
                        List<Biodata> biodataList = biodataBo.getByCriteria(biodata1);
                        String agama = biodataList.get(0).getAgama();

                        String cekDispen = cekAgama(bean.getNip(), bean.getIjinId(), agama);
                        if (cekDispen.equalsIgnoreCase("true")){

                            String ijinKeluarId;
                            Map hsCriteria = new HashMap();
                            if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                                hsCriteria.put("nip", bean.getNip());
                            }
                            try {
                                // Generating ID, get from postgre sequence
                                if (bean.getJamKeluar()!=null||bean.getJamKembali()!=null){
                                    ijinKeluarId = ijinKeluarDao.getNextIjinKeluarKantorId();
                                }
                                else {
                                    ijinKeluarId = ijinKeluarDao.getNextIjinKeluarId();
                                }
                            } catch (HibernateException e) {
                                logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                            }

                            // creating object entity serializable
                            IjinKeluarEntity imIjinKeluarEntity = new IjinKeluarEntity();
                            imIjinKeluarEntity.setIjinKeluarId(ijinKeluarId);
                            imIjinKeluarEntity.setIjinId(bean.getIjinId());
                            imIjinKeluarEntity.setIjinName(bean.getIjinName());
                            imIjinKeluarEntity.setLamaIjin(bean.getLamaIjin());
                            imIjinKeluarEntity.setNip(bean.getNip());
                            imIjinKeluarEntity.setCancelFlag("N");
                            imIjinKeluarEntity.setNamaPegawai(bean.getNamaPegawai());
                            imIjinKeluarEntity.setKeterangan(bean.getKeterangan());
                            imIjinKeluarEntity.setApprovalId(bean.getApprovalId());
                            imIjinKeluarEntity.setApprovalName(bean.getApprovalName());
                            imIjinKeluarEntity.setApprovalDate(bean.getApprovalDate());
                            imIjinKeluarEntity.setJamKeluar(bean.getJamKeluar());
                            imIjinKeluarEntity.setJamKembali(bean.getJamKembali());
                            imIjinKeluarEntity.setPositionId(bean.getPositionId());
                            imIjinKeluarEntity.setGolonganId(bean.getGolonganId());
                            imIjinKeluarEntity.setKeperluan(bean.getKeperluan());

                            if (bean.getUploadFile() != null){
                                imIjinKeluarEntity.setSuratDokter(bean.getUploadFile());
                            }

                            //tambahan irfan
                            List<Biodata> resultBiodata = new ArrayList<>();
                            try{
                                resultBiodata = ijinKeluarAnggotaDao.getBranchDivisiPosisi(bean.getNip());
                                for (Biodata biodata: resultBiodata){
                                    imIjinKeluarEntity.setDivisiId(biodata.getDivisi());
                                    imIjinKeluarEntity.setBagianId(biodata.getBagianId());
                                }
                            }catch (HibernateException e) {
                                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                            }


                            Map hsCriteria2 =  new HashMap();
                            hsCriteria2.put("position_id",bean.getPositionId());
                            hsCriteria2.put("flag","Y");
                            List<ImPosition> positionEntityList = positionDao.getByCriteria(hsCriteria2);
                            for (ImPosition imPosition : positionEntityList){
                                imIjinKeluarEntity.setPositionName(imPosition.getPositionName());
                            }
                            Map hsCriteria3 =  new HashMap();
                            hsCriteria3.put("golongan_id",bean.getGolonganId());
                            hsCriteria3.put("flag","Y");
                            List<ImGolonganEntity> golonganEntityList = golonganDao.getByCriteria(hsCriteria3);
                            for (ImGolonganEntity imGolonganEntity : golonganEntityList){
                                imIjinKeluarEntity.setGolonganName(imGolonganEntity.getGolonganName());
                            }
                            imIjinKeluarEntity.setNotApprovalNote(bean.getNotApprovalNote());
                            imIjinKeluarEntity.setUnitId(bean.getUnitId());
                            imIjinKeluarEntity.setTanggalAwal(bean.getTanggalAwal());
                            imIjinKeluarEntity.setTanggalAkhir(bean.getTanggalAkhir());

                            imIjinKeluarEntity.setApprovalId(bean.getNip());
                            imIjinKeluarEntity.setApprovalName(bean.getCreatedWho());
                            imIjinKeluarEntity.setApprovalDate(bean.getCreatedDate());
                            imIjinKeluarEntity.setApprovalFlag("Y");

                            imIjinKeluarEntity.setFlag(bean.getFlag());
                            imIjinKeluarEntity.setAction(bean.getAction());
                            imIjinKeluarEntity.setCreatedWho(bean.getCreatedWho());
                            imIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            imIjinKeluarEntity.setCreatedDate(bean.getCreatedDate());
                            imIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());

                            try {
                                // insert into database
                                ijinKeluarDao.addAndSave(imIjinKeluarEntity);
                            } catch (HibernateException e) {
                                logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                            }

                            if (("K").equalsIgnoreCase(bean.getKeperluan())){
                                HttpSession session = ServletActionContext.getRequest().getSession();
                                List<IjinKeluar> ijinKeluarList = (List<IjinKeluar>) session.getAttribute("listOfResultAnggotaIjinKeluarKantor");
                                if (ijinKeluarList!=null){
                                    for (IjinKeluar ijinKeluar : ijinKeluarList){
                                        IjinKeluarAnggotaEntity ijinKeluarAnggotaEntity = new IjinKeluarAnggotaEntity();
                                        String ijinKeluarAnggotaId  = ijinKeluarAnggotaDao.getNextIjinKeluarAnggotaId();
                                        ijinKeluarAnggotaEntity.setIjinKeluarAnggotaId(ijinKeluarAnggotaId);
                                        ijinKeluarAnggotaEntity.setIjinKeluarKantorId(ijinKeluarId);
                                        ijinKeluarAnggotaEntity.setNip(ijinKeluar.getNip());
                                        ijinKeluarAnggotaEntity.setNamaPegawai(ijinKeluar.getNamaPegawai());
                                        ijinKeluarAnggotaEntity.setPositionId(ijinKeluar.getPositionId());
                                        ijinKeluarAnggotaEntity.setJamKembali(bean.getJamKembali());
                                        ijinKeluarAnggotaEntity.setJamKeluar(bean.getJamKeluar());
                                        ijinKeluarAnggotaEntity.setTanggal(bean.getTanggalAwal());

                                        //tambahan irfan
//                        List<Biodata> hasilBiodata = new ArrayList<>();
                                        try{
                                            resultBiodata = ijinKeluarAnggotaDao.getBranchDivisiPosisi(ijinKeluar.getNip());
                                            for (Biodata biodata: resultBiodata){
                                                ijinKeluarAnggotaEntity.setBranchId(biodata.getBranch());
                                                ijinKeluarAnggotaEntity.setBidangId(biodata.getDivisi());
                                                ijinKeluarAnggotaEntity.setBagianId(biodata.getBagianId());
                                            }
                                        }catch (HibernateException e) {
                                            logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                                        }

                                        ijinKeluarAnggotaEntity.setFlag(bean.getFlag());
                                        ijinKeluarAnggotaEntity.setAction(bean.getAction());
                                        ijinKeluarAnggotaEntity.setCreatedWho(bean.getCreatedWho());
                                        ijinKeluarAnggotaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                        ijinKeluarAnggotaEntity.setCreatedDate(bean.getCreatedDate());
                                        ijinKeluarAnggotaEntity.setLastUpdate(bean.getLastUpdate());

                                        try {
                                            ijinKeluarAnggotaDao.addAndSave(ijinKeluarAnggotaEntity);
                                        } catch (HibernateException e) {
                                            logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                                        }
                                    }
                                }
                            }

//                            ImBiodataEntity imBiodataEntity;
//
//                            try {
//                                imBiodataEntity =  biodataDao.getById("nip", bean.getNip(), "Y");
//                            } catch (HibernateException e) {
//                                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
//                                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
//                            }
//
//                            //Send notif ke atasan
//                            Notifikasi notifAtasan= new Notifikasi();
//                            notifAtasan.setNip(bean.getNip());
//                            notifAtasan.setNoRequest(ijinKeluarId);
//                            if (bean.getJamKeluar()!=null||bean.getJamKembali()!=null){
//                                notifAtasan.setTipeNotifId("TN88");
//                                notifAtasan.setTipeNotifName("Ijin Keluar Kantor");
//                            } else {
//                                notifAtasan.setTipeNotifId("TN55");
//                                notifAtasan.setTipeNotifName("Dispensasi");
//                            }
//
//                            notifAtasan.setNote("Data dari user : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
//                            notifAtasan.setCreatedWho(bean.getNip());
//                            notifAtasan.setTo("atasan");
//                            notifAtasan.setOs(bean.getOs());
//
//                            notifikasiList.add(notifAtasan);

                        }else {
                            throw new GeneralBOException("Peringatan: Haji hanya diperbolehkan oleh Muslim / Ziarah hanya diperbolehkan oleh Non Muslim");
                        }
                    }else {
                        String ijinKeluarId;
                        Map hsCriteria = new HashMap();
                        if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                            hsCriteria.put("nip", bean.getNip());
                        }
                        try {
                            // Generating ID, get from postgre sequence
                            if (bean.getJamKeluar()!=null||bean.getJamKembali()!=null){
                                ijinKeluarId = ijinKeluarDao.getNextIjinKeluarKantorId();
                            }
                            else {
                                ijinKeluarId = ijinKeluarDao.getNextIjinKeluarId();
                            }
                        } catch (HibernateException e) {
                            logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                        }

                        // creating object entity serializable
                        IjinKeluarEntity imIjinKeluarEntity = new IjinKeluarEntity();
                        imIjinKeluarEntity.setIjinKeluarId(ijinKeluarId);
                        imIjinKeluarEntity.setIjinId(bean.getIjinId());
                        imIjinKeluarEntity.setIjinName(bean.getIjinName());
                        imIjinKeluarEntity.setLamaIjin(bean.getLamaIjin());
                        imIjinKeluarEntity.setNip(bean.getNip());
                        imIjinKeluarEntity.setCancelFlag("N");
                        imIjinKeluarEntity.setNamaPegawai(bean.getNamaPegawai());
                        imIjinKeluarEntity.setKeterangan(bean.getKeterangan());
                        imIjinKeluarEntity.setApprovalId(bean.getApprovalId());
                        imIjinKeluarEntity.setApprovalName(bean.getApprovalName());
                        imIjinKeluarEntity.setApprovalDate(bean.getApprovalDate());
                        imIjinKeluarEntity.setJamKeluar(bean.getJamKeluar());
                        imIjinKeluarEntity.setJamKembali(bean.getJamKembali());
                        imIjinKeluarEntity.setPositionId(bean.getPositionId());
                        imIjinKeluarEntity.setGolonganId(bean.getGolonganId());
                        imIjinKeluarEntity.setKeperluan(bean.getKeperluan());

                        if (bean.getUploadFile() != null){
                            imIjinKeluarEntity.setSuratDokter(bean.getUploadFile());
                        }

                        //tambahan irfan
                        List<Biodata> resultBiodata = new ArrayList<>();
                        try{
                            resultBiodata = ijinKeluarAnggotaDao.getBranchDivisiPosisi(bean.getNip());
                            for (Biodata biodata: resultBiodata){
                                imIjinKeluarEntity.setDivisiId(biodata.getDivisi());
                                imIjinKeluarEntity.setBagianId(biodata.getBagianId());
                            }
                        }catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                        }


                        Map hsCriteria2 =  new HashMap();
                        hsCriteria2.put("position_id",bean.getPositionId());
                        hsCriteria2.put("flag","Y");
                        List<ImPosition> positionEntityList = positionDao.getByCriteria(hsCriteria2);
                        for (ImPosition imPosition : positionEntityList){
                            imIjinKeluarEntity.setPositionName(imPosition.getPositionName());
                        }
                        Map hsCriteria3 =  new HashMap();
                        hsCriteria3.put("golongan_id",bean.getGolonganId());
                        hsCriteria3.put("flag","Y");
                        List<ImGolonganEntity> golonganEntityList = golonganDao.getByCriteria(hsCriteria3);
                        for (ImGolonganEntity imGolonganEntity : golonganEntityList){
                            imIjinKeluarEntity.setGolonganName(imGolonganEntity.getGolonganName());
                        }
                        imIjinKeluarEntity.setNotApprovalNote(bean.getNotApprovalNote());
                        imIjinKeluarEntity.setUnitId(bean.getUnitId());
                        imIjinKeluarEntity.setTanggalAwal(bean.getTanggalAwal());
                        imIjinKeluarEntity.setTanggalAkhir(bean.getTanggalAkhir());

                        imIjinKeluarEntity.setApprovalId(bean.getNip());
                        imIjinKeluarEntity.setApprovalName(bean.getCreatedWho());
                        imIjinKeluarEntity.setApprovalDate(bean.getCreatedDate());
                        imIjinKeluarEntity.setApprovalFlag("Y");

                        imIjinKeluarEntity.setFlag(bean.getFlag());
                        imIjinKeluarEntity.setAction(bean.getAction());
                        imIjinKeluarEntity.setCreatedWho(bean.getCreatedWho());
                        imIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imIjinKeluarEntity.setCreatedDate(bean.getCreatedDate());
                        imIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());

                        try {
                            // insert into database
                            ijinKeluarDao.addAndSave(imIjinKeluarEntity);
                        } catch (HibernateException e) {
                            logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                        }

                        if (("K").equalsIgnoreCase(bean.getKeperluan())){
                            HttpSession session = ServletActionContext.getRequest().getSession();
                            List<IjinKeluar> ijinKeluarList = (List<IjinKeluar>) session.getAttribute("listOfResultAnggotaIjinKeluarKantor");
                            if (ijinKeluarList!=null){
                                for (IjinKeluar ijinKeluar : ijinKeluarList){
                                    IjinKeluarAnggotaEntity ijinKeluarAnggotaEntity = new IjinKeluarAnggotaEntity();
                                    String ijinKeluarAnggotaId  = ijinKeluarAnggotaDao.getNextIjinKeluarAnggotaId();
                                    ijinKeluarAnggotaEntity.setIjinKeluarAnggotaId(ijinKeluarAnggotaId);
                                    ijinKeluarAnggotaEntity.setIjinKeluarKantorId(ijinKeluarId);
                                    ijinKeluarAnggotaEntity.setNip(ijinKeluar.getNip());
                                    ijinKeluarAnggotaEntity.setNamaPegawai(ijinKeluar.getNamaPegawai());
                                    ijinKeluarAnggotaEntity.setPositionId(ijinKeluar.getPositionId());
                                    ijinKeluarAnggotaEntity.setJamKembali(bean.getJamKembali());
                                    ijinKeluarAnggotaEntity.setJamKeluar(bean.getJamKeluar());
                                    ijinKeluarAnggotaEntity.setTanggal(bean.getTanggalAwal());

                                    //tambahan irfan
//                        List<Biodata> hasilBiodata = new ArrayList<>();
                                    try{
                                        resultBiodata = ijinKeluarAnggotaDao.getBranchDivisiPosisi(ijinKeluar.getNip());
                                        for (Biodata biodata: resultBiodata){
                                            ijinKeluarAnggotaEntity.setBranchId(biodata.getBranch());
                                            ijinKeluarAnggotaEntity.setBidangId(biodata.getDivisi());
                                            ijinKeluarAnggotaEntity.setBagianId(biodata.getBagianId());
                                        }
                                    }catch (HibernateException e) {
                                        logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                                    }

                                    ijinKeluarAnggotaEntity.setFlag(bean.getFlag());
                                    ijinKeluarAnggotaEntity.setAction(bean.getAction());
                                    ijinKeluarAnggotaEntity.setCreatedWho(bean.getCreatedWho());
                                    ijinKeluarAnggotaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                    ijinKeluarAnggotaEntity.setCreatedDate(bean.getCreatedDate());
                                    ijinKeluarAnggotaEntity.setLastUpdate(bean.getLastUpdate());

                                    try {
                                        ijinKeluarAnggotaDao.addAndSave(ijinKeluarAnggotaEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                                    }
                                }
                            }
                        }

//                        ImBiodataEntity imBiodataEntity;
//
//                        try {
//                            imBiodataEntity =  biodataDao.getById("nip", bean.getNip(), "Y");
//                        } catch (HibernateException e) {
//                            logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
//                            throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
//                        }
//
//                        //Send notif ke atasan
//                        Notifikasi notifAtasan= new Notifikasi();
//                        notifAtasan.setNip(bean.getNip());
//                        notifAtasan.setNoRequest(ijinKeluarId);
//                        if (bean.getJamKeluar()!=null||bean.getJamKembali()!=null){
//                            notifAtasan.setTipeNotifId("TN88");
//                            notifAtasan.setTipeNotifName("Ijin Keluar Kantor");
//                        } else {
//                            notifAtasan.setTipeNotifId("TN55");
//                            notifAtasan.setTipeNotifName("Dispensasi");
//                        }
//
//                        notifAtasan.setNote("Data dari user : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
//                        notifAtasan.setCreatedWho(bean.getNip());
//                        notifAtasan.setTo("atasan");
//                        notifAtasan.setOs(bean.getOs());
//
//                        notifikasiList.add(notifAtasan);
                    }



                }else {


                    if ("IJ010".equalsIgnoreCase(bean.getIjinId()) || "IJ032".equalsIgnoreCase(bean.getIjinId())){
                        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                        Biodata biodata1 = new Biodata();
                        BiodataBo biodataBo = (BiodataBo) context.getBean("biodataBoProxy");
                        biodata1.setNip(bean.getNip());
                        biodata1.setFlag("Y");
                        List<Biodata> biodataList = biodataBo.getByCriteria(biodata1);
                        String agama = biodataList.get(0).getAgama();

                        String cekDispen = cekAgama(bean.getNip(), bean.getIjinId(), agama);
                        if (cekDispen.equalsIgnoreCase("true")){

                            String ijinKeluarId;
                            Map hsCriteria = new HashMap();
                            if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                                hsCriteria.put("nip", bean.getNip());
                            }
                            try {
                                // Generating ID, get from postgre sequence
                                if (bean.getJamKeluar()!=null||bean.getJamKembali()!=null){
                                    ijinKeluarId = ijinKeluarDao.getNextIjinKeluarKantorId();
                                }
                                else {
                                    ijinKeluarId = ijinKeluarDao.getNextIjinKeluarId();
                                }
                            } catch (HibernateException e) {
                                logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                            }

                            // creating object entity serializable
                            IjinKeluarEntity imIjinKeluarEntity = new IjinKeluarEntity();
                            imIjinKeluarEntity.setIjinKeluarId(ijinKeluarId);
                            imIjinKeluarEntity.setIjinId(bean.getIjinId());
                            imIjinKeluarEntity.setIjinName(bean.getIjinName());
                            imIjinKeluarEntity.setLamaIjin(bean.getLamaIjin());
                            imIjinKeluarEntity.setNip(bean.getNip());
                            imIjinKeluarEntity.setCancelFlag("N");
                            imIjinKeluarEntity.setNamaPegawai(bean.getNamaPegawai());
                            imIjinKeluarEntity.setKeterangan(bean.getKeterangan());
                            imIjinKeluarEntity.setApprovalId(bean.getApprovalId());
                            imIjinKeluarEntity.setApprovalName(bean.getApprovalName());
                            imIjinKeluarEntity.setApprovalDate(bean.getApprovalDate());
                            imIjinKeluarEntity.setJamKeluar(bean.getJamKeluar());
                            imIjinKeluarEntity.setJamKembali(bean.getJamKembali());
                            imIjinKeluarEntity.setPositionId(bean.getPositionId());
                            imIjinKeluarEntity.setGolonganId(bean.getGolonganId());
                            imIjinKeluarEntity.setKeperluan(bean.getKeperluan());

                            if (bean.getUploadFile() != null){
                                imIjinKeluarEntity.setSuratDokter(bean.getUploadFile());
                            }

                            //tambahan irfan
                            List<Biodata> resultBiodata = new ArrayList<>();
                            try{
                                resultBiodata = ijinKeluarAnggotaDao.getBranchDivisiPosisi(bean.getNip());
                                for (Biodata biodata: resultBiodata){
                                    imIjinKeluarEntity.setDivisiId(biodata.getDivisi());
                                    imIjinKeluarEntity.setBagianId(biodata.getBagianId());
                                }
                            }catch (HibernateException e) {
                                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                            }


                            Map hsCriteria2 =  new HashMap();
                            hsCriteria2.put("position_id",bean.getPositionId());
                            hsCriteria2.put("flag","Y");
                            List<ImPosition> positionEntityList = positionDao.getByCriteria(hsCriteria2);
                            for (ImPosition imPosition : positionEntityList){
                                imIjinKeluarEntity.setPositionName(imPosition.getPositionName());
                            }
                            Map hsCriteria3 =  new HashMap();
                            hsCriteria3.put("golongan_id",bean.getGolonganId());
                            hsCriteria3.put("flag","Y");
                            List<ImGolonganEntity> golonganEntityList = golonganDao.getByCriteria(hsCriteria3);
                            for (ImGolonganEntity imGolonganEntity : golonganEntityList){
                                imIjinKeluarEntity.setGolonganName(imGolonganEntity.getGolonganName());
                            }
                            imIjinKeluarEntity.setNotApprovalNote(bean.getNotApprovalNote());
                            imIjinKeluarEntity.setUnitId(bean.getUnitId());
                            imIjinKeluarEntity.setTanggalAwal(bean.getTanggalAwal());
                            imIjinKeluarEntity.setTanggalAkhir(bean.getTanggalAkhir());

                            imIjinKeluarEntity.setFlag(bean.getFlag());
                            imIjinKeluarEntity.setAction(bean.getAction());
                            imIjinKeluarEntity.setCreatedWho(bean.getCreatedWho());
                            imIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            imIjinKeluarEntity.setCreatedDate(bean.getCreatedDate());
                            imIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());

                            try {
                                // insert into database
                                ijinKeluarDao.addAndSave(imIjinKeluarEntity);
                            } catch (HibernateException e) {
                                logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                            }

                            if (("K").equalsIgnoreCase(bean.getKeperluan())){
                                HttpSession session = ServletActionContext.getRequest().getSession();
                                List<IjinKeluar> ijinKeluarList = (List<IjinKeluar>) session.getAttribute("listOfResultAnggotaIjinKeluarKantor");
                                if (ijinKeluarList!=null){
                                    for (IjinKeluar ijinKeluar : ijinKeluarList){
                                        IjinKeluarAnggotaEntity ijinKeluarAnggotaEntity = new IjinKeluarAnggotaEntity();
                                        String ijinKeluarAnggotaId  = ijinKeluarAnggotaDao.getNextIjinKeluarAnggotaId();
                                        ijinKeluarAnggotaEntity.setIjinKeluarAnggotaId(ijinKeluarAnggotaId);
                                        ijinKeluarAnggotaEntity.setIjinKeluarKantorId(ijinKeluarId);
                                        ijinKeluarAnggotaEntity.setNip(ijinKeluar.getNip());
                                        ijinKeluarAnggotaEntity.setNamaPegawai(ijinKeluar.getNamaPegawai());
                                        ijinKeluarAnggotaEntity.setPositionId(ijinKeluar.getPositionId());
                                        ijinKeluarAnggotaEntity.setJamKembali(bean.getJamKembali());
                                        ijinKeluarAnggotaEntity.setJamKeluar(bean.getJamKeluar());
                                        ijinKeluarAnggotaEntity.setTanggal(bean.getTanggalAwal());

                                        //tambahan irfan
//                        List<Biodata> hasilBiodata = new ArrayList<>();
                                        try{
                                            resultBiodata = ijinKeluarAnggotaDao.getBranchDivisiPosisi(ijinKeluar.getNip());
                                            for (Biodata biodata: resultBiodata){
                                                ijinKeluarAnggotaEntity.setBranchId(biodata.getBranch());
                                                ijinKeluarAnggotaEntity.setBidangId(biodata.getDivisi());
                                                ijinKeluarAnggotaEntity.setBagianId(biodata.getBagianId());
                                            }
                                        }catch (HibernateException e) {
                                            logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                                        }

                                        ijinKeluarAnggotaEntity.setFlag(bean.getFlag());
                                        ijinKeluarAnggotaEntity.setAction(bean.getAction());
                                        ijinKeluarAnggotaEntity.setCreatedWho(bean.getCreatedWho());
                                        ijinKeluarAnggotaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                        ijinKeluarAnggotaEntity.setCreatedDate(bean.getCreatedDate());
                                        ijinKeluarAnggotaEntity.setLastUpdate(bean.getLastUpdate());

                                        try {
                                            ijinKeluarAnggotaDao.addAndSave(ijinKeluarAnggotaEntity);
                                        } catch (HibernateException e) {
                                            logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                                        }
                                    }
                                }
                            }

                            ImBiodataEntity imBiodataEntity;

                            try {
                                imBiodataEntity =  biodataDao.getById("nip", bean.getNip(), "Y");
                            } catch (HibernateException e) {
                                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                            }

                            //Send notif ke atasan
                            Notifikasi notifAtasan= new Notifikasi();
                            notifAtasan.setNip(bean.getNip());
                            notifAtasan.setNoRequest(ijinKeluarId);
                            if (bean.getJamKeluar()!=null||bean.getJamKembali()!=null){
                                notifAtasan.setTipeNotifId("TN88");
                                notifAtasan.setTipeNotifName("Ijin Keluar Kantor");
                            } else {
                                notifAtasan.setTipeNotifId("TN55");
                                notifAtasan.setTipeNotifName("Dispensasi");
                            }

                            notifAtasan.setNote("Data dari user : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
                            notifAtasan.setCreatedWho(bean.getNip());
                            notifAtasan.setTo("atasan");
                            notifAtasan.setOs(bean.getOs());

                            notifikasiList.add(notifAtasan);

                        }else {
                            throw new GeneralBOException("Peringatan: Haji hanya diperbolehkan oleh Muslim / Ziarah hanya diperbolehkan oleh Non Muslim");
                        }
                    }else {

                        String ijinKeluarId;
                        Map hsCriteria = new HashMap();
                        if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                            hsCriteria.put("nip", bean.getNip());
                        }
                        try {
                            // Generating ID, get from postgre sequence
                            if (bean.getJamKeluar()!=null||bean.getJamKembali()!=null){
                                ijinKeluarId = ijinKeluarDao.getNextIjinKeluarKantorId();
                            }
                            else {
                                ijinKeluarId = ijinKeluarDao.getNextIjinKeluarId();
                            }
                        } catch (HibernateException e) {
                            logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                        }

                        // creating object entity serializable
                        IjinKeluarEntity imIjinKeluarEntity = new IjinKeluarEntity();
                        imIjinKeluarEntity.setIjinKeluarId(ijinKeluarId);
                        imIjinKeluarEntity.setIjinId(bean.getIjinId());
                        imIjinKeluarEntity.setIjinName(bean.getIjinName());
                        imIjinKeluarEntity.setLamaIjin(bean.getLamaIjin());
                        imIjinKeluarEntity.setNip(bean.getNip());
                        imIjinKeluarEntity.setCancelFlag("N");
                        imIjinKeluarEntity.setNamaPegawai(bean.getNamaPegawai());
                        imIjinKeluarEntity.setKeterangan(bean.getKeterangan());
                        imIjinKeluarEntity.setApprovalId(bean.getApprovalId());
                        imIjinKeluarEntity.setApprovalName(bean.getApprovalName());
                        imIjinKeluarEntity.setApprovalDate(bean.getApprovalDate());
                        imIjinKeluarEntity.setJamKeluar(bean.getJamKeluar());
                        imIjinKeluarEntity.setJamKembali(bean.getJamKembali());
                        imIjinKeluarEntity.setPositionId(bean.getPositionId());
                        imIjinKeluarEntity.setGolonganId(bean.getGolonganId());
                        imIjinKeluarEntity.setKeperluan(bean.getKeperluan());

                        if (bean.getUploadFile() != null){
                            imIjinKeluarEntity.setSuratDokter(bean.getUploadFile());
                        }

                        //tambahan irfan
                        List<Biodata> resultBiodata = new ArrayList<>();
                        try{
                            resultBiodata = ijinKeluarAnggotaDao.getBranchDivisiPosisi(bean.getNip());
                            for (Biodata biodata: resultBiodata){
                                imIjinKeluarEntity.setDivisiId(biodata.getDivisi());
                                imIjinKeluarEntity.setBagianId(biodata.getBagianId());
                            }
                        }catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                        }


                        Map hsCriteria2 =  new HashMap();
                        hsCriteria2.put("position_id",bean.getPositionId());
                        hsCriteria2.put("flag","Y");
                        List<ImPosition> positionEntityList = positionDao.getByCriteria(hsCriteria2);
                        for (ImPosition imPosition : positionEntityList){
                            imIjinKeluarEntity.setPositionName(imPosition.getPositionName());
                        }
                        Map hsCriteria3 =  new HashMap();
                        hsCriteria3.put("golongan_id",bean.getGolonganId());
                        hsCriteria3.put("flag","Y");
                        List<ImGolonganEntity> golonganEntityList = golonganDao.getByCriteria(hsCriteria3);
                        for (ImGolonganEntity imGolonganEntity : golonganEntityList){
                            imIjinKeluarEntity.setGolonganName(imGolonganEntity.getGolonganName());
                        }
                        imIjinKeluarEntity.setNotApprovalNote(bean.getNotApprovalNote());
                        imIjinKeluarEntity.setUnitId(bean.getUnitId());
                        imIjinKeluarEntity.setTanggalAwal(bean.getTanggalAwal());
                        imIjinKeluarEntity.setTanggalAkhir(bean.getTanggalAkhir());

                        imIjinKeluarEntity.setFlag(bean.getFlag());
                        imIjinKeluarEntity.setAction(bean.getAction());
                        imIjinKeluarEntity.setCreatedWho(bean.getCreatedWho());
                        imIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imIjinKeluarEntity.setCreatedDate(bean.getCreatedDate());
                        imIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());

                        try {
                            // insert into database
                            ijinKeluarDao.addAndSave(imIjinKeluarEntity);
                        } catch (HibernateException e) {
                            logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                        }

                        if (("K").equalsIgnoreCase(bean.getKeperluan())){
                            HttpSession session = ServletActionContext.getRequest().getSession();
                            List<IjinKeluar> ijinKeluarList = (List<IjinKeluar>) session.getAttribute("listOfResultAnggotaIjinKeluarKantor");
                            if (ijinKeluarList!=null){
                                for (IjinKeluar ijinKeluar : ijinKeluarList){
                                    IjinKeluarAnggotaEntity ijinKeluarAnggotaEntity = new IjinKeluarAnggotaEntity();
                                    String ijinKeluarAnggotaId  = ijinKeluarAnggotaDao.getNextIjinKeluarAnggotaId();
                                    ijinKeluarAnggotaEntity.setIjinKeluarAnggotaId(ijinKeluarAnggotaId);
                                    ijinKeluarAnggotaEntity.setIjinKeluarKantorId(ijinKeluarId);
                                    ijinKeluarAnggotaEntity.setNip(ijinKeluar.getNip());
                                    ijinKeluarAnggotaEntity.setNamaPegawai(ijinKeluar.getNamaPegawai());
                                    ijinKeluarAnggotaEntity.setPositionId(ijinKeluar.getPositionId());
                                    ijinKeluarAnggotaEntity.setJamKembali(bean.getJamKembali());
                                    ijinKeluarAnggotaEntity.setJamKeluar(bean.getJamKeluar());
                                    ijinKeluarAnggotaEntity.setTanggal(bean.getTanggalAwal());

                                    //tambahan irfan
//                        List<Biodata> hasilBiodata = new ArrayList<>();
                                    try{
                                        resultBiodata = ijinKeluarAnggotaDao.getBranchDivisiPosisi(ijinKeluar.getNip());
                                        for (Biodata biodata: resultBiodata){
                                            ijinKeluarAnggotaEntity.setBranchId(biodata.getBranch());
                                            ijinKeluarAnggotaEntity.setBidangId(biodata.getDivisi());
                                            ijinKeluarAnggotaEntity.setBagianId(biodata.getBagianId());
                                        }
                                    }catch (HibernateException e) {
                                        logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                                    }

                                    ijinKeluarAnggotaEntity.setFlag(bean.getFlag());
                                    ijinKeluarAnggotaEntity.setAction(bean.getAction());
                                    ijinKeluarAnggotaEntity.setCreatedWho(bean.getCreatedWho());
                                    ijinKeluarAnggotaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                    ijinKeluarAnggotaEntity.setCreatedDate(bean.getCreatedDate());
                                    ijinKeluarAnggotaEntity.setLastUpdate(bean.getLastUpdate());

                                    try {
                                        ijinKeluarAnggotaDao.addAndSave(ijinKeluarAnggotaEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                                    }
                                }
                            }
                        }

                        ImBiodataEntity imBiodataEntity;

                        try {
                            imBiodataEntity =  biodataDao.getById("nip", bean.getNip(), "Y");
                        } catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                        }

                        //Send notif ke atasan
                        Notifikasi notifAtasan= new Notifikasi();
                        notifAtasan.setNip(bean.getNip());
                        notifAtasan.setNoRequest(ijinKeluarId);
                        if (bean.getJamKeluar()!=null||bean.getJamKembali()!=null){
                            notifAtasan.setTipeNotifId("TN88");
                            notifAtasan.setTipeNotifName("Ijin Keluar Kantor");
                        } else {
                            notifAtasan.setTipeNotifId("TN55");
                            notifAtasan.setTipeNotifName("Dispensasi");
                        }

                        notifAtasan.setNote("Data dari user : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
                        notifAtasan.setCreatedWho(bean.getNip());
                        notifAtasan.setTo("atasan");
                        notifAtasan.setOs(bean.getOs());

                        notifikasiList.add(notifAtasan);

                    }


                }

            }else {
                throw new GeneralBOException("Maaf dispensasi Haji/Ziarah hanya dapat dilakukan satu kali");
            }
        }

        logger.info("[IjinKeluarBoImpl.saveAdd] end process <<<");
        return notifikasiList;
    }

    @Override
    public List<IjinKeluar> getByCriteriaForAbsensi(IjinKeluar bean, String tanggal){
        logger.info("[LemburBoImpl.saveAdd] start process >>>");
        List<IjinKeluar> listOfResult = new ArrayList();

        if (bean != null) {
            List<IjinKeluarEntity> ijinKeluarEntityList = new ArrayList<>();
            ijinKeluarEntityList = ijinKeluarDao.getListPersonalFromNip(bean.getNip(),CommonUtil.convertToDate(tanggal));
            for (IjinKeluarEntity ijinKeluarEntity:ijinKeluarEntityList){
                IjinKeluar returnIjinKeluar = new IjinKeluar();
                returnIjinKeluar.setJamKeluar(ijinKeluarEntity.getJamKeluar());
                returnIjinKeluar.setJamKembali(ijinKeluarEntity.getJamKembali());
                returnIjinKeluar.setKeterangan(ijinKeluarEntity.getKeterangan());

                listOfResult.add(returnIjinKeluar);
            }
        }
        return listOfResult;
    }

    @Override
    public List<IjinKeluar> getByCriteria(IjinKeluar searchBean) throws GeneralBOException {
        logger.info("[IjinKeluarBoImpl.getByCriteria] start process >>>");
        String from =searchBean.getFrom();
        // Mapping with collection and put
        List<IjinKeluar> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getIjinKeluarId() != null && !"".equalsIgnoreCase(searchBean.getIjinKeluarId())) {
                hsCriteria.put("ijin_keluar_id", searchBean.getIjinKeluarId());
            }
            if (searchBean.getIjinId() != null && !"".equalsIgnoreCase(searchBean.getIjinId())) {
                hsCriteria.put("ijin_id", searchBean.getIjinId());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getUnitId() != null && !"".equalsIgnoreCase(searchBean.getUnitId())) {
                hsCriteria.put("unit_id", searchBean.getUnitId());
            }
            if (searchBean.getFrom() != null && !"".equalsIgnoreCase(searchBean.getFrom())) {
                hsCriteria.put("from", searchBean.getFrom());
            }
            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
            }
            if (searchBean.getStTanggalAwal() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalAwal()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalAwal());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalAkhir() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalAkhir()))) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalAkhir());
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


            List<IjinKeluarEntity> itIjinKeluarEntity = null;
            try {

                itIjinKeluarEntity = ijinKeluarDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.getSearchIjinKeluarByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itIjinKeluarEntity != null){
                IjinKeluar returnIjinKeluar;
                // Looping from dao to object and save in collection
                for(IjinKeluarEntity ijinKeluarEntity : itIjinKeluarEntity){
                    if (from.equalsIgnoreCase("ijinKeluarKantor")) {
                        if (ijinKeluarEntity.getIjinKeluarId().length() == 8) {
                            returnIjinKeluar = new IjinKeluar();
                            returnIjinKeluar.setIjinKeluarId(ijinKeluarEntity.getIjinKeluarId());
                            returnIjinKeluar.setIjinId(ijinKeluarEntity.getIjinId());
                            returnIjinKeluar.setIjinName(ijinKeluarEntity.getIjinName());
                            returnIjinKeluar.setLamaIjin(ijinKeluarEntity.getLamaIjin());

                            returnIjinKeluar.setNip(ijinKeluarEntity.getNip());
                            returnIjinKeluar.setNamaPegawai(ijinKeluarEntity.getNamaPegawai());
                            returnIjinKeluar.setKeterangan(ijinKeluarEntity.getKeterangan());
                            returnIjinKeluar.setApprovalId(ijinKeluarEntity.getApprovalId());
                            returnIjinKeluar.setKeperluan(ijinKeluarEntity.getKeperluan());
                            returnIjinKeluar.setCancelNote(ijinKeluarEntity.getCancelNote());
                            returnIjinKeluar.setApprovalFlag(ijinKeluarEntity.getApprovalFlag());
                            returnIjinKeluar.setApprovalName(ijinKeluarEntity.getApprovalName());
                            returnIjinKeluar.setJamKembali(ijinKeluarEntity.getJamKembali());
                            returnIjinKeluar.setPositionId(ijinKeluarEntity.getPositionId());
                            returnIjinKeluar.setGolonganId(ijinKeluarEntity.getGolonganId());
                            returnIjinKeluar.setPositionName(ijinKeluarEntity.getPositionName());
                            returnIjinKeluar.setGolonganName(ijinKeluarEntity.getGolonganName());
                            returnIjinKeluar.setJamKeluar(ijinKeluarEntity.getJamKeluar());
                            returnIjinKeluar.setApprovalDate(ijinKeluarEntity.getApprovalDate());
                            returnIjinKeluar.setNotApprovalNote(ijinKeluarEntity.getNotApprovalNote());
                            returnIjinKeluar.setUnitId(ijinKeluarEntity.getUnitId());
                            returnIjinKeluar.setKeperluan(ijinKeluarEntity.getKeperluan());
                            Map hsCriteria2 = new HashMap();
                            hsCriteria2.put("branch_id",ijinKeluarEntity.getUnitId());
                            hsCriteria2.put("flag","Y");
                            List<ImBranches> branchList = new ArrayList<>();
                            branchList = branchDao.getByCriteria(hsCriteria2);
                            for (ImBranches imBranches : branchList){
                                returnIjinKeluar.setUnitName(imBranches.getBranchName());
                            }
                            Map hsCriteria3 = new HashMap();
                            hsCriteria3.put("position_id",ijinKeluarEntity.getPositionId());
                            hsCriteria3.put("flag","Y");
                            List<ImPosition> positionList = new ArrayList<>();
                            positionList = positionDao.getByCriteria(hsCriteria3);
                            for (ImPosition imPosition : positionList){
                                returnIjinKeluar.setPositionName(imPosition.getPositionName());
                            }
                            Map hsCriteria4 = new HashMap();
                            hsCriteria4.put("nip",ijinKeluarEntity.getNip());
                            hsCriteria4.put("flag","Y");
                            List<ItPersonilPositionEntity> itPersonilPositionEntityList = new ArrayList<>();
                            itPersonilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria4);
                            for (ItPersonilPositionEntity itPersonilPositionEntity : itPersonilPositionEntityList){
                                ImPosition imPosition = positionDao.getById("positionId",itPersonilPositionEntity.getPositionId(),"Y");
                                returnIjinKeluar.setDivisiId(imPosition.getDepartmentId());
                            }
                            if (ijinKeluarEntity.getKeperluan()!=null){
                                if (("K").equalsIgnoreCase(ijinKeluarEntity.getKeperluan())){
                                    returnIjinKeluar.setKeperluanName("Kantor");
                                }else{
                                    returnIjinKeluar.setKeperluanName("Pribadi");
                                }
                            }
                            if (returnIjinKeluar.getDivisiId()!=null){
                                Map hsCriteria5 = new HashMap();
                                hsCriteria5.put("department_id",returnIjinKeluar.getDivisiId());
                                hsCriteria5.put("flag","Y");
                                List<ImDepartmentEntity> imDepartmentEntityList = new ArrayList<>();
                                imDepartmentEntityList = departmentDao.getByCriteria(hsCriteria5);
                                for (ImDepartmentEntity imDepartmentEntity : imDepartmentEntityList){
                                    returnIjinKeluar.setDivisiName(imDepartmentEntity.getDepartmentName());
                                }
                            }
                            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            returnIjinKeluar.setStTanggalAwal(df.format(ijinKeluarEntity.getTanggalAwal()));
                            returnIjinKeluar.setTanggalAwal(ijinKeluarEntity.getTanggalAwal());
                            returnIjinKeluar.setTanggalAkhir(ijinKeluarEntity.getTanggalAkhir());

                            String user = CommonUtil.userIdLogin();
                            if (user.equalsIgnoreCase(ijinKeluarEntity.getNip())) {
                                returnIjinKeluar.setCekatasan(true);
                                returnIjinKeluar.setCeksdm(true);
                            }
                            returnIjinKeluar.setAction(ijinKeluarEntity.getAction());
                            returnIjinKeluar.setFlag(ijinKeluarEntity.getFlag());
                            returnIjinKeluar.setCreatedWho(ijinKeluarEntity.getCreatedWho());
                            returnIjinKeluar.setCreatedDate(ijinKeluarEntity.getCreatedDate());
                            returnIjinKeluar.setLastUpdate(ijinKeluarEntity.getLastUpdate());


                            if (ijinKeluarEntity.getApprovalFlag()!=null){
                                if (ijinKeluarEntity.getApprovalFlag().equals("Y")) {
                                    returnIjinKeluar.setIjinKeluarApprove(true);
                                }
                                if (ijinKeluarEntity.getApprovalFlag().equals("N")) {
                                    returnIjinKeluar.setNotApprove(true);
                                }
                            }

                            if (ijinKeluarEntity.getApprovalId() != null) {
                                returnIjinKeluar.setIjinKeluarApproveStatus(true);
                            }
                            if (ijinKeluarEntity.getApprovalSdmFlag()!=null){
                                if (ijinKeluarEntity.getApprovalSdmFlag().equals("Y")) {
                                    returnIjinKeluar.setIjinKeluarApproveSdm(true);
                                }
                                if (ijinKeluarEntity.getApprovalSdmFlag().equals("N")) {
                                    returnIjinKeluar.setNotApproveSdm(true);
                                }
                            }
                            if(ijinKeluarEntity.getCancelFlag() != null){
                                if(ijinKeluarEntity.getCancelFlag().equals("Y") ){
                                    returnIjinKeluar.setCancel(true);
                                }
                            }
                            java.sql.Date now =new java.sql.Date(System.currentTimeMillis());
                            List<AbsensiPegawaiEntity>statusAbsensi = new ArrayList<>();
                            try {
                                statusAbsensi = absensiPegawaiDao.getListAbsensiByNipAndTanggal(ijinKeluarEntity.getNip(),ijinKeluarEntity.getTanggalAwal());
                                if (statusAbsensi.size()==0){
                                    returnIjinKeluar.setCanCancel(true);
                                }
                            }catch (HibernateException e) {
                                logger.error("[IjinKeluarBoImpl.getSearchIjinKeluarByCriteria] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }


                            if (ijinKeluarEntity.getApprovalSdmId() != null) {
                                returnIjinKeluar.setIjinKeluarApproveStatusSdm(true);
                            }
                            if (ijinKeluarEntity.getApprovalFlag()!=null){
                                if (ijinKeluarEntity.getApprovalFlag().equals("Y") ) {
                                    returnIjinKeluar.setFinish(true);
                                }
                            }
                            listOfResult.add(returnIjinKeluar);
                        }
                    }
                    else if (from.equalsIgnoreCase("ijinKeluar")){
                            if (ijinKeluarEntity.getIjinKeluarId().length()==9){
                                returnIjinKeluar = new IjinKeluar();
                                returnIjinKeluar.setIjinKeluarId(ijinKeluarEntity.getIjinKeluarId());
                                returnIjinKeluar.setIjinId(ijinKeluarEntity.getIjinId());
                                returnIjinKeluar.setIjinName(ijinKeluarEntity.getIjinName());
                                returnIjinKeluar.setLamaIjin(ijinKeluarEntity.getLamaIjin());

                                returnIjinKeluar.setNip(ijinKeluarEntity.getNip());
                                returnIjinKeluar.setNamaPegawai(ijinKeluarEntity.getNamaPegawai());
                                returnIjinKeluar.setKeterangan(ijinKeluarEntity.getKeterangan());
                                returnIjinKeluar.setApprovalId(ijinKeluarEntity.getApprovalId());
                                returnIjinKeluar.setApprovalFlag(ijinKeluarEntity.getApprovalFlag());
                                returnIjinKeluar.setApprovalName(ijinKeluarEntity.getApprovalName());
                                returnIjinKeluar.setPositionId(ijinKeluarEntity.getPositionId());
                                returnIjinKeluar.setPositionName(ijinKeluarEntity.getPositionName());
                                returnIjinKeluar.setGolonganId(ijinKeluarEntity.getGolonganId());
                                returnIjinKeluar.setGolonganName(ijinKeluarEntity.getGolonganName());
                                returnIjinKeluar.setApprovalDate(ijinKeluarEntity.getApprovalDate());
                                returnIjinKeluar.setNotApprovalNote(ijinKeluarEntity.getNotApprovalNote());
                                returnIjinKeluar.setUnitId(ijinKeluarEntity.getUnitId());
                                returnIjinKeluar.setTanggalAwal(ijinKeluarEntity.getTanggalAwal());
                                returnIjinKeluar.setCancelDate(ijinKeluarEntity.getCancelDate());
                                returnIjinKeluar.setCancelFlag(ijinKeluarEntity.getCancelFlag());
                                returnIjinKeluar.setCancelNote(ijinKeluarEntity.getCancelNote());
                                returnIjinKeluar.setCancelPerson(ijinKeluarEntity.getCancelPerson());
                                returnIjinKeluar.setTanggalAkhir(ijinKeluarEntity.getTanggalAkhir());

                                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                returnIjinKeluar.setStTanggalAwal(df.format(ijinKeluarEntity.getTanggalAwal()));
                                returnIjinKeluar.setStTanggalAkhir(df.format(ijinKeluarEntity.getTanggalAkhir()));

//                                if (ijinKeluarEntity.getTglAkhirUpdate() != null){
                                if ("IJ013".equalsIgnoreCase(ijinKeluarEntity.getIjinId())){
//                                    returnIjinKeluar.setStTanggalAkhir(df.format(ijinKeluarEntity.getTglAkhirUpdate()));
//                                    Date tgl = ijinKeluarEntity.getTglAkhirUpdate();
//                                    Calendar calendar = Calendar.getInstance();
//                                    calendar.setTime(tgl);
//                                    calendar.add(Calendar.DATE, -45);
//                                    returnIjinKeluar.setStTglMelahirkan(df.format(calendar.getTime()));
//                                    returnIjinKeluar.setDispenLahir(true);
//                                    returnIjinKeluar.setUploadFile(ijinKeluarEntity.getSuratDokter());
//                                    returnIjinKeluar.setStTanggalAkhir(df.format(ijinKeluarEntity.getTanggalAkhir()));
                                    if (ijinKeluarEntity.getTglMelahirkan() != null){
                                        returnIjinKeluar.setCetakSurat(true);
                                        returnIjinKeluar.setLamaIjin(ijinKeluarEntity.getLamaIjinOld());
                                        returnIjinKeluar.setLamaIjinBaru(ijinKeluarEntity.getLamaIjin());
                                        returnIjinKeluar.setTanggalAkhir(ijinKeluarEntity.getTanggalAkhirOld());
                                        returnIjinKeluar.setStTanggalAkhir(df.format(ijinKeluarEntity.getTanggalAkhirOld()));
                                        returnIjinKeluar.setTglAkhirBaru(ijinKeluarEntity.getTanggalAkhir());
                                        returnIjinKeluar.setTanggalAkhirBaru(df.format(ijinKeluarEntity.getTanggalAkhir()));
//                                        if (ijinKeluarEntity.getTglMelahirkan() != null)
                                        returnIjinKeluar.setStTglMelahirkan(df.format(ijinKeluarEntity.getTglMelahirkan()));
                                    }

                                    returnIjinKeluar.setDispenLahir(true);
                                    if ("".equalsIgnoreCase(ijinKeluarEntity.getSuratDokter())){
                                        returnIjinKeluar.setSuratDokter(false);
                                    }else{
                                        returnIjinKeluar.setUploadFile(ijinKeluarEntity.getSuratDokter());
                                        returnIjinKeluar.setSuratDokter(true);
                                    }
                                }
//                                else {
//                                    returnIjinKeluar.setStTanggalAkhir(df.format(ijinKeluarEntity.getTanggalAkhir()));
//                                }

                                if(searchBean.isMobile()) {

                                } else {
                                    String user = CommonUtil.userIdLogin();
                                    if (user.equalsIgnoreCase(ijinKeluarEntity.getNip())) {
                                        returnIjinKeluar.setCekatasan(true);
                                        returnIjinKeluar.setCeksdm(true);
                                    }
                                }
                                Map hsCriteria2 = new HashMap();
                                hsCriteria2.put("branch_id",ijinKeluarEntity.getUnitId());
                                hsCriteria2.put("flag","Y");
                                List<ImBranches> branchList = new ArrayList<>();
                                branchList = branchDao.getByCriteria(hsCriteria2);
                                for (ImBranches imBranches : branchList){
                                    returnIjinKeluar.setUnitName(imBranches.getBranchName());
                                }
                                Map hsCriteria3 = new HashMap();
                                hsCriteria3.put("position_id",ijinKeluarEntity.getPositionId());
                                hsCriteria3.put("flag","Y");
                                List<ImPosition> positionList = new ArrayList<>();
                                positionList = positionDao.getByCriteria(hsCriteria3);
                                for (ImPosition imPosition : positionList){
                                    returnIjinKeluar.setPositionName(imPosition.getPositionName());
                                    returnIjinKeluar.setDivisiId(imPosition.getDepartmentId());
                                }
                                Map hsCriteria5 = new HashMap();
                                hsCriteria5.put("department_id",returnIjinKeluar.getDivisiId());
                                hsCriteria5.put("flag","Y");
                                List<ImDepartmentEntity> imDepartmentEntityList = new ArrayList<>();
                                imDepartmentEntityList = departmentDao.getByCriteria(hsCriteria5);
                                for (ImDepartmentEntity imDepartmentEntity : imDepartmentEntityList){
                                    returnIjinKeluar.setDivisiName(imDepartmentEntity.getDepartmentName());
                                }
                                returnIjinKeluar.setAction(ijinKeluarEntity.getAction());
                                returnIjinKeluar.setFlag(ijinKeluarEntity.getFlag());
                                returnIjinKeluar.setCreatedWho(ijinKeluarEntity.getCreatedWho());
                                returnIjinKeluar.setCreatedDate(ijinKeluarEntity.getCreatedDate());
                                returnIjinKeluar.setLastUpdate(ijinKeluarEntity.getLastUpdate());

                                if (ijinKeluarEntity.getApprovalFlag()!=null){
                                    if(ijinKeluarEntity.getApprovalFlag().equals("Y")){
                                        returnIjinKeluar.setIjinKeluarApprove(true);
                                        returnIjinKeluar.setCanCancel(false);
                                    }
                                    if (ijinKeluarEntity.getApprovalFlag().equals("N")) {
                                        returnIjinKeluar.setNotApprove(true);
                                    }
                                }

                                if(ijinKeluarEntity.getApprovalId() != null){
                                    returnIjinKeluar.setIjinKeluarApproveStatus(true);
                                }
                                if (ijinKeluarEntity.getApprovalSdmFlag()!=null){
                                    if(ijinKeluarEntity.getApprovalSdmFlag().equals("Y")){
                                        returnIjinKeluar.setIjinKeluarApproveSdm(true);
                                    }
                                    if (ijinKeluarEntity.getApprovalSdmFlag().equals("N")) {
                                        returnIjinKeluar.setNotApproveSdm(true);
                                    }
                                }
                                if(ijinKeluarEntity.getApprovalSdmId() != null){
                                    returnIjinKeluar.setIjinKeluarApproveStatusSdm(true);
                                }
                                if (ijinKeluarEntity.getApprovalFlag()!=null){
                                    if (ijinKeluarEntity.getApprovalFlag().equals("Y")){
                                        returnIjinKeluar.setFinish(true);
                                    }
                                }
                                if(ijinKeluarEntity.getCancelFlag() != null){
                                    if(ijinKeluarEntity.getCancelFlag().equals("Y") ){
                                        returnIjinKeluar.setCancel(true);
                                    }
                                }
                                java.sql.Date now =new java.sql.Date(System.currentTimeMillis());
                                if (now.before(ijinKeluarEntity.getTanggalAwal())){
                                    if (ijinKeluarEntity.getApprovalFlag() != null){
                                        if(ijinKeluarEntity.getApprovalFlag().equals("Y") && !searchBean.getRoleId().equalsIgnoreCase("1")){
                                            returnIjinKeluar.setCanCancel(false);
                                            returnIjinKeluar.setPengajuanBatal(true);
                                        }else {
                                            if (ijinKeluarEntity.getFlagPengajuanBatal() != null){
                                                if (searchBean.getRoleId().equalsIgnoreCase("1") && ijinKeluarEntity.getFlagPengajuanBatal().equalsIgnoreCase("Y")){
                                                    returnIjinKeluar.setCanCancel(true);
                                                    returnIjinKeluar.setPengajuanBatal(false);
                                                }else {
                                                    returnIjinKeluar.setCanCancel(false);
                                                    returnIjinKeluar.setPengajuanBatal(false);
                                                }
                                            }
                                        }
                                    }else {
                                        returnIjinKeluar.setPengajuanBatal(false);
                                        returnIjinKeluar.setCanCancel(true);
                                    }
                                }

                                if (ijinKeluarEntity.getApprovalFlag() != null){
                                    if(ijinKeluarEntity.getApprovalFlag().equals("Y") && !searchBean.getRoleId().equalsIgnoreCase("1")){
                                        returnIjinKeluar.setCanCancel(false);
                                        returnIjinKeluar.setPengajuanBatal(true);
                                    }else {
                                        if (ijinKeluarEntity.getFlagPengajuanBatal() != null) {
                                            if (searchBean.getRoleId().equalsIgnoreCase("1") && ijinKeluarEntity.getFlagPengajuanBatal().equalsIgnoreCase("Y")) {
                                                returnIjinKeluar.setCanCancel(true);
                                                returnIjinKeluar.setPengajuanBatal(false);
                                            } else {
                                                returnIjinKeluar.setCanCancel(false);
                                                returnIjinKeluar.setPengajuanBatal(false);
                                            }
                                        }
                                    }
                                }

                                if (ijinKeluarEntity.getFlagPengajuanBatal() != null)
                                    returnIjinKeluar.setFlagPengajuanBatal(ijinKeluarEntity.getFlagPengajuanBatal());

                                if(searchBean.getDivisiId()!=null){
                                    if (!searchBean.getDivisiId().equalsIgnoreCase("")){
                                        if (returnIjinKeluar.getDivisiId().equalsIgnoreCase(searchBean.getDivisiId())){
                                            listOfResult.add(returnIjinKeluar);
                                        }
                                    }else{
                                        listOfResult.add(returnIjinKeluar);
                                    }
                                }else{
                                    listOfResult.add(returnIjinKeluar);
                                }
                            }
                        }
                    }
                }
            }
        logger.info("[IjinKeluarBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<IjinKeluar> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<IjinKeluar> getComboIjinKeluarWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<IjinKeluar> listComboIjinKeluar = new ArrayList();
        String criteria = "%" + query + "%";

        List<IjinKeluarEntity> listIjinKeluar = null;
        try {
            listIjinKeluar = ijinKeluarDao.getListIjinKeluar(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listIjinKeluar != null) {
            for (IjinKeluarEntity itIjinKeluarEntity : listIjinKeluar) {
                IjinKeluar itemComboIjinKeluar = new IjinKeluar();
                itemComboIjinKeluar.setIjinKeluarId(itIjinKeluarEntity.getIjinKeluarId());
                itemComboIjinKeluar.setIjinId(itIjinKeluarEntity.getIjinId());
                itemComboIjinKeluar.setIjinName(itIjinKeluarEntity.getIjinName());
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboIjinKeluar;
    }
    @Override
    public List<IjinKeluarAnggota> getijinKeluarAnggota(String id) throws GeneralBOException {
        logger.info("[UserBoImpl.getijinKeluarAnggota] start process >>>");

        List<IjinKeluarAnggota> ijinKeluarAnggotaList = new ArrayList();
        List<IjinKeluarAnggotaEntity> listResultQuery = null;
        try {
            listResultQuery = ijinKeluarAnggotaDao.getIjinKeluarAnggotaById(id);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getijinKeluarAnggota] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
        }

        if (listResultQuery != null) {
            for (IjinKeluarAnggotaEntity ijinKeluarAnggotaEntity : listResultQuery) {
                IjinKeluarAnggota ijinKeluarAnggota = new IjinKeluarAnggota();
                ijinKeluarAnggota.setNip(ijinKeluarAnggotaEntity.getNip());
                ijinKeluarAnggota.setNamaPegawai(ijinKeluarAnggotaEntity.getNamaPegawai());
                ijinKeluarAnggota.setPositionId(ijinKeluarAnggotaEntity.getPositionId());
                ImPosition position = new ImPosition();
                try {
                    position = positionDao.getById("positionId",ijinKeluarAnggotaEntity.getPositionId());
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.getijinKeluarAnggota] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
                }

                ijinKeluarAnggota.setPositionName(position.getPositionName());
                ijinKeluarAnggotaList.add(ijinKeluarAnggota);
            }
        }
        logger.info("[UserBoImpl.getijinKeluarAnggota] end process <<<");
        return ijinKeluarAnggotaList;
    }
    //get sisa cuti
    public List<IjinKeluar> getComboSisaIjinKeluarWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<IjinKeluar> listComboSisaIjinKeluar = new ArrayList();
        String criteria = query ;

        List<IjinKeluarEntity> listSisaIjinKeluar = new ArrayList<>();
        try {
            listSisaIjinKeluar = ijinKeluarDao.getListSisaIjinKeluar(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSisaIjinKeluar != null) {
            for (IjinKeluarEntity itIjinKeluarEntity : listSisaIjinKeluar) {
                IjinKeluar itemComboIjinKeluar = new IjinKeluar();
                itemComboIjinKeluar.setIjinKeluarId(itIjinKeluarEntity.getIjinKeluarId());
                //itemComboIjinKeluar.setSisaCutiHari(itIjinKeluarEntity.getSisaCutiHari());
                listComboSisaIjinKeluar.add(itemComboIjinKeluar);
            }
        }
        else {
            for (IjinKeluarEntity itIjinKeluarEntity : listSisaIjinKeluar) {
                IjinKeluar itemComboIjinKeluar = new IjinKeluar();
                itemComboIjinKeluar.setIjinKeluarId(null);
               // itemComboIjinKeluar.setSisaCutiHari(null);
                listComboSisaIjinKeluar.add(itemComboIjinKeluar);

            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSisaIjinKeluar;
    }


    public List<Notifikasi> saveApprove(IjinKeluar bean) throws GeneralBOException {
        List<Notifikasi> notifikasiList = new ArrayList<>();
        String atasanName;
        logger.info("[IjinKeluarBoImpl.saveEdit] start process >>>");
        Map hsCriteria = new HashMap();
        String atasanNip = null;
        if (bean!=null) {
            String IjinKeluarId = bean.getIjinKeluarId();
            IjinKeluarEntity itIjinKeluarEntity = null;
            //ItIjinKeluarEntity itIjinKeluarEntity = null;
            try {
                // Get data from database by ID
                itIjinKeluarEntity = ijinKeluarDao.getById("ijinKeluarId", IjinKeluarId);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data IjinKeluar by Kode IjinKeluar, please inform to your admin...," + e.getMessage());
            }
            if (itIjinKeluarEntity != null) {
                String userLogin = bean.getUserNameActive();
                itIjinKeluarEntity.setIjinKeluarId(bean.getIjinKeluarId());
                itIjinKeluarEntity.setFlag(bean.getFlag());
                //Approve
                if(bean.getTmpApprove().equals("atasan")) {
                    if (bean.getApprovalFlag().equals("Y")) {
                        itIjinKeluarEntity.setApprovalFlag("Y");
                    } else {
                        itIjinKeluarEntity.setNotApprovalNote(bean.getNotApprovalNote());
                        itIjinKeluarEntity.setApprovalFlag("N");
                    }
                    itIjinKeluarEntity.setApprovalId(bean.getUserIdActive());
                    itIjinKeluarEntity.setApprovalName(bean.getUserNameActive());
                    itIjinKeluarEntity.setApprovalDate(bean.getLastUpdate());
                }
                else {
                    if (bean.getApprovalSdmFlag().equals("Y")) {
                        itIjinKeluarEntity.setApprovalSdmFlag("Y");
                        itIjinKeluarEntity.setApprovalSdmId(bean.getUserIdActive());
                        itIjinKeluarEntity.setApprovalSdmName(bean.getUserNameActive());
                    } else {
                        itIjinKeluarEntity.setNotApprovalSdmNote(bean.getNotApprovalSdmNote());
                        itIjinKeluarEntity.setApprovalSdmFlag("N");
                    }
                    itIjinKeluarEntity.setApprovalSdmId(bean.getUserIdActive());
                    itIjinKeluarEntity.setApprovalSdmName(bean.getUserNameActive());
                    itIjinKeluarEntity.setApprovalSdmDate(bean.getLastUpdate());
                }
                itIjinKeluarEntity.setAction(bean.getAction());
                itIjinKeluarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itIjinKeluarEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    ijinKeluarDao.updateAndSave(itIjinKeluarEntity);
                } catch (HibernateException e) {
                    logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data IjinKeluar, please info to your admin..." + e.getMessage());
                }

                // SEND NOTIF
                ImBiodataEntity imBiodataEntity =  biodataDao.getById("nip", itIjinKeluarEntity.getNip(), "Y");
                Integer kelompok = personilPositionDao.getKelompokPosition(itIjinKeluarEntity.getNip());

                String tipeNotifName="Dispensasi";
                if (("IJ001").equalsIgnoreCase(itIjinKeluarEntity.getIjinId())){
                    tipeNotifName="Ijin Keluar Kantor";
                }

                if (bean.getApprovalFlag().equals("Y")) {
                    //Send notif ke orang yang mengajukan
                    Notifikasi notifSelf= new Notifikasi();
                    notifSelf.setNip(itIjinKeluarEntity.getNip());
                    notifSelf.setNoRequest(bean.getIjinKeluarId());
                    notifSelf.setTipeNotifId("umum");
//                    notifSelf.setTipeNotifName((tipeNotifName));
                    notifSelf.setTipeNotifName(("Pemberitahuan"));
                    notifSelf.setNote(tipeNotifName+" anda pada tanggal "+CommonUtil.convertDateToString(itIjinKeluarEntity.getTanggalAwal())+" di approve oleh atasan anda");
                    notifSelf.setCreatedWho(itIjinKeluarEntity.getNip());
                    notifSelf.setTo("self");

                    notifikasiList.add(notifSelf);

                    Notifikasi notifAtasan = new Notifikasi();
                    if (bean.isMobile())
//                        notifAtasan.setNip(bean.getNip());
                        notifAtasan.setNip(bean.getNipUserLogin());
                    else
                        notifAtasan.setNip(CommonUtil.userIdLogin());

                    notifAtasan.setNoRequest(bean.getIjinKeluarId());
                    notifAtasan.setTipeNotifId("umum");
//                    notifAtasan.setTipeNotifName(("Ijin Keluar Pegawai"));
                    notifAtasan.setTipeNotifName(("Pemberitahuan"));
                    notifAtasan.setNote(imBiodataEntity.getNamaPegawai() + " mengajukan dispensasi pada tanggal " +CommonUtil.convertDateToString(itIjinKeluarEntity.getTanggalAwal()) + " sampai dengan tanggal " + CommonUtil.convertDateToString(itIjinKeluarEntity.getTanggalAkhir()));
                    if (bean.isMobile())
//                        notifAtasan.setNip(bean.getNip());
                        notifAtasan.setNip(bean.getNipUserLogin());
                    else
                        notifAtasan.setNip(CommonUtil.userIdLogin());

                    notifAtasan.setTo("atasan");

                    notifikasiList.add(notifAtasan);

                    /*if (kelompok>6){
                        //Send notif ke kabag
                        Notifikasi notifKabag= new Notifikasi();
                        notifKabag.setNip(itIjinKeluarEntity.getNip());
                        notifKabag.setNoRequest(bean.getIjinKeluarId());
                        notifKabag.setTipeNotifId("umum");
                        notifKabag.setTipeNotifName((tipeNotifName));
                        notifKabag.setNote(imBiodataEntity.getNamaPegawai()+" mengajukan "+tipeNotifName+ " pada tanggal "+CommonUtil.convertDateToString(itIjinKeluarEntity.getTanggalAwal()));
                        notifKabag.setCreatedWho(itIjinKeluarEntity.getNip());
                        notifKabag.setTo("kabag");

                        notifikasiList.add(notifKabag);
                    }*/

                }else {
                    String msg="";
                    if (!("").equalsIgnoreCase(itIjinKeluarEntity.getNotApprovalNote())){
                        msg="dikarenakan "+itIjinKeluarEntity.getNotApprovalNote();
                    }
                    //Send notif ke orang yang mengajukan
                    Notifikasi notifSelf= new Notifikasi();
                    notifSelf.setNip(itIjinKeluarEntity.getNip());
                    notifSelf.setNoRequest(bean.getIjinKeluarId());
                    notifSelf.setTipeNotifId("umum");
//                    notifSelf.setTipeNotifName(tipeNotifName);
                    notifSelf.setTipeNotifName(("Pemberitahuan"));
                    notifSelf.setNote(tipeNotifName+" anda pada tanggal "+CommonUtil.convertDateToString(itIjinKeluarEntity.getTanggalAwal())+" tidak di approve oleh atasan "+msg);
                    notifSelf.setCreatedWho(itIjinKeluarEntity.getNip());
                    notifSelf.setTo("self");
                    notifikasiList.add(notifSelf);
                }

            } else {
                logger.error("[IjinKeluarBoImpl.saveEdit] Error, not found data IjinKeluar with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data IjinKeluar with request id, please check again your data ...");
            }
        }

        logger.info("[IjinKeluarBoImpl.saveEdit] end process <<<");
        return notifikasiList;
    }

    @Override
    public List<Object[]> findInfoIjin(String idIjinPegawai, String nip) throws GeneralBOException {
        logger.info("[IjinKeluarBoImpl.findInfoIjin] start process >>>");
        List<Object[]> listCuti = null;

        try {
            listCuti = ijinKeluarDao.findInfoIjin(idIjinPegawai, nip);
        } catch (HibernateException e) {
            logger.error("[IjinKeluarBoImpl.findInfoIjin] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[IjinKeluarBoImpl.findInfoIjin] end process <<<");
        return listCuti;
    }

    @Override
    public List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws GeneralBOException {
        logger.info("[IjinKeluarBoImpl.findAllConfirmByIdAtasan] start process >>>");
        List<Object[]> listConfirmStatus = null;

        try {
            listConfirmStatus = ijinKeluarDao.findAllConfirmByIdAtasan(nip, flag);
        } catch (HibernateException e) {
            logger.error("[IjinKeluarBoImpl.findAllConfirmByIdAtasan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[IjinKeluarBoImpl.findAllConfirmByIdAtasan] end process <<<");
        return listConfirmStatus;
    }

    public List<IjinKeluar> getBiodatawithCriteria(String query) throws GeneralBOException {
        logger.info("[BiodataBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Biodata> listOfResult = new ArrayList();
        List<IjinKeluar>ListOfIjinKeluar = new ArrayList();
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
            IjinKeluar returnIjinKeluar;
            // Looping from dao to object and save in collection
            for (ImBiodataEntity personalEntity : imBiodataEntity) {
                returnIjinKeluar = new IjinKeluar();
                returnIjinKeluar.setNip(personalEntity.getNip());
                returnIjinKeluar.setNamaPegawai(personalEntity.getNamaPegawai());
                returnIjinKeluar.setGolonganId(personalEntity.getGolongan());
                itPersonilPositionEntity = personilPositionDao.getById("nip",personalEntity.getNip(),"Y" );
                if(itPersonilPositionEntity.getPositionId() != null){
                    returnIjinKeluar.setPositionId(itPersonilPositionEntity.getPositionId());
                }
                ImPosition imPosition = positionDao.getById("positionId",returnIjinKeluar.getPositionId());
                if (imPosition!=null){
                    returnIjinKeluar.setDivisiId(imPosition.getDepartmentId());
                }
                returnIjinKeluar.setUnitId(itPersonilPositionEntity.getImBranches().getPrimaryKey().getId());

                ListOfIjinKeluar.add(returnIjinKeluar);
            }
        }
        logger.info("[BiodataBoImpl.getByCriteria] end process <<<");

        return ListOfIjinKeluar;
    }
    @Override
    public List getComboTestTanggal(String nip, String tanggalAwal, String tanggalSelesai) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<IjinKeluar> listComboSisaIjinKeluar = new ArrayList();
        List listSisaIjinKeluar = null;
        try {
            listSisaIjinKeluar = ijinKeluarDao.getComboTestTanggal(nip,tanggalAwal,tanggalSelesai);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listSisaIjinKeluar;
    }
    @Override
    public List<IjinKeluar> getByCriteriaForAbsensiIjinTidakMasuk(IjinKeluar bean, String tanggal){
        logger.info("[LemburBoImpl.saveAdd] start process >>>");
        List<IjinKeluar> listOfResult = new ArrayList();
        if (bean != null) {
            List<IjinKeluarEntity> ijinKeluarEntityList = new ArrayList<>();
            ijinKeluarEntityList = ijinKeluarDao.getListIjinByNipAndTanggal(bean.getNip(),CommonUtil.convertToDate(tanggal));
            for (IjinKeluarEntity ijinKeluarEntity:ijinKeluarEntityList){
                IjinKeluar returnIjinKeluar = new IjinKeluar();
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                returnIjinKeluar.setStTanggalAwal(df.format(ijinKeluarEntity.getTanggalAwal()));
                returnIjinKeluar.setStTanggalAkhir(df.format(ijinKeluarEntity.getTanggalAkhir()));
                ImIjinEntity imIjinEntity = ijinDao.getById("ijinId",ijinKeluarEntity.getIjinId());
                returnIjinKeluar.setIjinName(imIjinEntity.getIjinName());

                listOfResult.add(returnIjinKeluar);
            }
        }
        return listOfResult;
    }
    public List getListDispensasiMasal(String unit){
        List<IjinKeluar> listOfResult = new ArrayList<>();
        List<ItPersonilPositionEntity> biodataEntityList;
        try{
            biodataEntityList = ijinKeluarDao.getPegawaiDispensasiMasal(unit);
            for (ItPersonilPositionEntity biodataLoop: biodataEntityList){
                IjinKeluar returnIjinKeluar = new IjinKeluar();

                String namaPegawai = ijinKeluarDao.getName(biodataLoop.getNip());
                if (!namaPegawai.equalsIgnoreCase("")){
                    returnIjinKeluar.setNip(biodataLoop.getNip());
                    returnIjinKeluar.setNamaPegawai(namaPegawai);
                    listOfResult.add(returnIjinKeluar);
                }
            }
        }catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        return listOfResult;
    }
    public void saveAddDispensasiMasal(IjinKeluar bean){
        String ijinKeluarId;
        if (bean!=null){
            try{
                ijinKeluarId = ijinKeluarDao.getNextIjinKeluarId();
            }catch (HibernateException e) {
                logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }

            IjinKeluarEntity imIjinKeluarEntity = new IjinKeluarEntity();

            imIjinKeluarEntity.setIjinKeluarId(ijinKeluarId);
            imIjinKeluarEntity.setIjinId(bean.getIjinId());
            imIjinKeluarEntity.setIjinName(bean.getIjinName());
            imIjinKeluarEntity.setLamaIjin(bean.getLamaIjin());
            imIjinKeluarEntity.setNip(bean.getNip());

            String namaPegawai = ijinKeluarDao.getName(bean.getNip());

            imIjinKeluarEntity.setNamaPegawai(namaPegawai);
            imIjinKeluarEntity.setKeterangan(bean.getKeterangan());
            imIjinKeluarEntity.setTanggalAwal(bean.getTanggalAwal());
            imIjinKeluarEntity.setTanggalAkhir(bean.getTanggalAkhir());

            imIjinKeluarEntity.setCreatedDate(bean.getCreatedDate());
            imIjinKeluarEntity.setCreatedWho(bean.getCreatedWho());
            imIjinKeluarEntity.setFlag(bean.getFlag());
            imIjinKeluarEntity.setAction(bean.getAction());

            //set data approval
            imIjinKeluarEntity.setApprovalFlag(bean.getApprovalFlag());
            imIjinKeluarEntity.setApprovalId(bean.getApprovalId());
            imIjinKeluarEntity.setApprovalName(bean.getApprovalName());
            imIjinKeluarEntity.setApprovalDate(bean.getApprovalDate());

            imIjinKeluarEntity.setApprovalSdmFlag(bean.getApprovalSdmFlag());
            imIjinKeluarEntity.setApprovalSdmId(bean.getApprovalSdmId());
            imIjinKeluarEntity.setApprovalSdmName(bean.getApprovalSdmName());
            imIjinKeluarEntity.setApprovalSdmDate(bean.getApprovalSdmDate());

            //ambil Golongan Pegawai
            List<Golongan> golonganPegawai = new ArrayList<>();
            try{
                golonganPegawai = golonganDao.getGolonganByNip(bean.getNip());
                if (golonganPegawai.size()>0){
                    for (Golongan golonganLoop: golonganPegawai){
                        imIjinKeluarEntity.setGolonganId(golonganLoop.getGolonganId());
                        imIjinKeluarEntity.setGolonganName(golonganLoop.getGolonganName());
                    }
                }
            }catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }


            imIjinKeluarEntity.setCancelFlag("N");

            //set data perusahaan pegawai
            List<Biodata> resultBiodata = new ArrayList<>();
            try{
                resultBiodata = ijinKeluarAnggotaDao.getBranchDivisiPosisi(bean.getNip());
                for (Biodata biodata: resultBiodata){

                    imIjinKeluarEntity.setUnitId(biodata.getBranch());
                    imIjinKeluarEntity.setDivisiId(biodata.getDivisi());
                    imIjinKeluarEntity.setBagianId(biodata.getBagianId());

                    imIjinKeluarEntity.setPositionId(biodata.getPositionId());
                    List<ImPosition> positionPegawai = positionDao.getPositionById(biodata.getPositionId());
                    for (ImPosition positionLoop: positionPegawai){
                        imIjinKeluarEntity.setPositionName(positionLoop.getPositionName());
                    }
                }
            }catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ijinKeluarDao.addAndSave(imIjinKeluarEntity);
        }
    }

    @Override
    public String getNextSuratDokterId() throws GeneralBOException {
        String suratDokterId;
        try{
            suratDokterId = ijinKeluarDao.getNextSuratDokteId();
        }catch (HibernateException e){
            logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence ijinKeluarId, please info to your admin..." + e.getMessage());
        }
        return suratDokterId;
    }


    @Override
    public String cekIfAbsensi(String nip, String tglDari, String tglSelesai) {
        String status ="";
        Date tgl;
        try {
            if (tglDari.equalsIgnoreCase(tglSelesai)){
                tgl = CommonUtil.convertStringToDate(tglDari);
                status = ijinKeluarDao.cekIfAbsensi(nip,tgl);
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
                    status = ijinKeluarDao.cekIfAbsensi(nip,CommonUtil.convertStringToDate(tanggal));
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

    @Override
    public String cekPengajuanIjinKeluar(String nip) {
        logger.info("[IjinKeluarBoImpl.cekPengajuanCuti] start process >>>");
        String status = "";
        List<IjinKeluarEntity> listIjinKeluar = null;
        try {
            listIjinKeluar = ijinKeluarDao.getListCekIjinKeluar(nip);
        } catch (HibernateException e) {
            logger.error("[IjinKeluarBoImpl.cekPengajuanCuti] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listIjinKeluar != null){
            if (listIjinKeluar.size() > 0){
                status = "exist";
            }else {
                status = "notExist";
            }
        }else {
            status = "notExist";
        }

        return status;
    }

    @Override
    public List<IjinKeluar> getListCekNipIjinKeluar(String nip) throws GeneralBOException {
        logger.info("[IjinKeluarBoimpl.getListCekNipIjinKeluar] start process >>>");

        List<IjinKeluar> listComboIjinKeluar = new ArrayList();

        List<IjinKeluarEntity> listIjinKeluar = null;
        try {
            listIjinKeluar = ijinKeluarDao.getListCekIjinKeluar(nip);
        } catch (HibernateException e) {
            logger.error("[IjinKeluarBoImpl.getListCekNipIjinKeluar] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listIjinKeluar != null) {
            for (IjinKeluarEntity entity : listIjinKeluar) {
                IjinKeluar ijinKeluar = new IjinKeluar();
                ijinKeluar.setNip(entity.getNip());
                listComboIjinKeluar.add(ijinKeluar);
            }
        }
        logger.info("[IjinKeluarBoImpl.getListCekNipIjinKeluar] end process <<<");
        return listComboIjinKeluar;
    }

    public String cekStatus(String nip, Date tglAwal, Date tglAkhir){
        String status = "";
        List<ItCutiPegawaiEntity> itCutiPegawaiEntities = new ArrayList<>();

        try{
            itCutiPegawaiEntities = ijinKeluarDao.cekData(nip, tglAwal, tglAkhir);
        }catch (HibernateException e){
            logger.error("[IjinKeluarBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if (itCutiPegawaiEntities.size() > 0){
            status = "exist";
        }else {
            status = "notExist";
        }

        return status;
    }


    public String cekAgama(String nip, String ijinId, String agama){
        String status = "";

        if ("islam".equalsIgnoreCase(agama) && "IJ010".equalsIgnoreCase(ijinId)){
            status = "true";
        }else if (!"islam".equalsIgnoreCase(agama) && !"IJ010".equalsIgnoreCase(ijinId)){
            status = "true";
        }else
            status = "false";

        return status;
    }

    public String cekStatusIjin(String nip){
        String status = "";
        List<IjinKeluarEntity> ijinKeluarEntities = new ArrayList<>();

        try{
            ijinKeluarEntities = ijinKeluarDao.cekHajiZiarah(nip);
        }catch (HibernateException e){
            logger.error("[IjinKeluarBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if (ijinKeluarEntities.size() > 0){
            status = "exist";
        }else {
            status = "notExist";
        }

        return status;
    }

    @Override
    public List<IjinKeluar> getHistoryIjinKeluarByMonth (String nip, String branchId, Date date) {
        List<IjinKeluar> result = new ArrayList<>();
        SimpleDateFormat dateFormat  = new SimpleDateFormat("YYYY");
        String year = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("MM");
        String month = dateFormat.format(date);
        String firstDate = "01-" + month + "-" + year;
        String lastDate = CommonUtil.getLastDayOfMonth() + "-" + month + "-" + year;

        try {
           result = ijinKeluarDao.getHistoryIjinKeluarByMonth(nip, branchId, firstDate, lastDate);
        } catch (HibernateException e){
            logger.error("[AbsensiBoImpl.getByCriteriaMesin] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        return result;
    }
}