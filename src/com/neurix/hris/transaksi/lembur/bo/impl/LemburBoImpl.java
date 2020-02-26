package com.neurix.hris.transaksi.lembur.bo.impl;

import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.ExpoPushNotif;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golongan.dao.GolonganDao;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.jamkerja.dao.JamKerjaDao;
import com.neurix.hris.master.libur.dao.LiburDao;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.absensi.dao.AbsensiPegawaiDao;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawaiEntity;
import com.neurix.hris.transaksi.cutiPegawai.dao.CutiPegawaiDao;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
import com.neurix.hris.transaksi.ijinKeluar.dao.IjinKeluarDao;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarEntity;
import com.neurix.hris.transaksi.lembur.bo.LemburBo;
import com.neurix.hris.transaksi.lembur.dao.LemburDao;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.lembur.model.LemburEntity;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiFcmDao;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.ItNotifikasiFcmEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.sppd.dao.SppdPersonDao;
import com.neurix.hris.transaksi.sppd.model.ItSppdPersonEntity;
import com.neurix.hris.transaksi.training.dao.TrainingDao;
import com.neurix.hris.transaksi.training.dao.TrainingPersonDao;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingEntity;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingPersonEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class LemburBoImpl implements LemburBo {

    protected static transient Logger logger = Logger.getLogger(LemburBoImpl.class);
    private LemburDao lemburDao;
    private AbsensiPegawaiDao absensiPegawaiDao;
    private BiodataDao biodataDao;
    private PersonilPositionDao personilPositionDao;
    private PositionDao positionDao;
    private DepartmentDao departmentDao;
    private GolonganDao golonganDao;
    private StrukturJabatanDao strukturJabatanDao;
    private NotifikasiDao notifikasiDao;
    private CutiPegawaiDao cutiPegawaiDao;
    private IjinKeluarDao ijinKeluarDao;
    private SppdPersonDao sppdPersonDao;
    private TrainingPersonDao trainingPersonDao;
    private TrainingDao trainingDao;
    private LiburDao liburDao;
    private JamKerjaDao jamKerjaDao;
    private NotifikasiFcmDao notifikasiFcmDao;
    private String ACTION_CLICK = "TASK_LEMBUR";

    public void setNotifikasiFcmDao(NotifikasiFcmDao notifikasiFcmDao) {
        this.notifikasiFcmDao = notifikasiFcmDao;
    }

    public JamKerjaDao getJamKerjaDao() {
        return jamKerjaDao;
    }

    public void setJamKerjaDao(JamKerjaDao jamKerjaDao) {
        this.jamKerjaDao = jamKerjaDao;
    }

    public LiburDao getLiburDao() {
        return liburDao;
    }

    public void setLiburDao(LiburDao liburDao) {
        this.liburDao = liburDao;
    }

    public AbsensiPegawaiDao getAbsensiPegawaiDao() {
        return absensiPegawaiDao;
    }

    public void setAbsensiPegawaiDao(AbsensiPegawaiDao absensiPegawaiDao) {
        this.absensiPegawaiDao = absensiPegawaiDao;
    }

    public IjinKeluarDao getIjinKeluarDao() {
        return ijinKeluarDao;
    }

    public void setIjinKeluarDao(IjinKeluarDao ijinKeluarDao) {
        this.ijinKeluarDao = ijinKeluarDao;
    }

    public SppdPersonDao getSppdPersonDao() {
        return sppdPersonDao;
    }

    public void setSppdPersonDao(SppdPersonDao sppdPersonDao) {
        this.sppdPersonDao = sppdPersonDao;
    }

    public TrainingPersonDao getTrainingPersonDao() {
        return trainingPersonDao;
    }

    public void setTrainingPersonDao(TrainingPersonDao trainingPersonDao) {
        this.trainingPersonDao = trainingPersonDao;
    }

    public TrainingDao getTrainingDao() {
        return trainingDao;
    }

    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public CutiPegawaiDao getCutiPegawaiDao() {
        return cutiPegawaiDao;
    }

    public void setCutiPegawaiDao(CutiPegawaiDao cutiPegawaiDao) {
        this.cutiPegawaiDao = cutiPegawaiDao;
    }

    public NotifikasiDao getNotifikasiDao() {
        return notifikasiDao;
    }

    public void setNotifikasiDao(NotifikasiDao notifikasiDao) {
        this.notifikasiDao = notifikasiDao;
    }

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    public GolonganDao getGolonganDao() {
        return golonganDao;
    }

    public void setGolonganDao(GolonganDao golonganDao) {
        this.golonganDao = golonganDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LemburBoImpl.logger = logger;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public LemburDao getLemburDao() {
        return lemburDao;
    }

    public void setLemburDao(LemburDao lemburDao) {
        this.lemburDao = lemburDao;
    }


    @Override
    public void saveDelete(Lembur bean) throws GeneralBOException {
        logger.info("[LemburBoImpl.saveDelete] start process >>>");
        if (bean!=null) {
            String lemburId = bean.getLemburId();
            LemburEntity imLemburEntity = null;
            try {
                // Get data from database by ID
                imLemburEntity = lemburDao.getById("lemburId", lemburId);
            } catch (HibernateException e) {
                logger.error("[lemburBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode lembur, please inform to your admin...," + e.getMessage());
            }
            if (imLemburEntity != null) {
                imLemburEntity.setFlag(bean.getFlag());
                imLemburEntity.setAction(bean.getAction());
                imLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imLemburEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    lemburDao.updateAndSave(imLemburEntity);
                } catch (HibernateException e) {
                    logger.error("[LemburBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data lembur, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[LemburBoImpl.saveDelete] Error, not found data lembur with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
            }
        }logger.info("[LemburBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Lembur bean) throws GeneralBOException {
        logger.info("[LemburBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String lemburId = bean.getLemburId();
            LemburEntity imLemburEntity = null;
            try {
                // Get data from database by ID
                imLemburEntity = lemburDao.getById("lemburId", lemburId);
            } catch (HibernateException e) {
                logger.error("[lemburBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode lembur, please inform to your admin...," + e.getMessage());
            }
            if (imLemburEntity != null) {
                imLemburEntity.setLemburId(bean.getLemburId());
                imLemburEntity.setTanggalAwalSetuju(bean.getTanggalAwalSetuju());
                imLemburEntity.setTanggalAkhirSetuju(bean.getTanggalAkhirSetuju());
                imLemburEntity.setLamaJam(bean.getLamaJam());
                imLemburEntity.setJamAwal(bean.getJamAwal());
                imLemburEntity.setJamAkhir(bean.getJamAkhir());
                imLemburEntity.setTanggalAwal(bean.getTanggalAwal());
                imLemburEntity.setTanggalAkhir(bean.getTanggalAkhir());
                imLemburEntity.setKeterangan(bean.getKeterangan());

                imLemburEntity.setFlag(bean.getFlag());
                imLemburEntity.setAction(bean.getAction());
                imLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imLemburEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    lemburDao.updateAndSave(imLemburEntity);
                } catch (HibernateException e) {
                    logger.error("[LemburBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data lembur, please info to your admin..." + e.getMessage());
                }

                //delete from notif
                if (("N").equalsIgnoreCase(imLemburEntity.getFlag())){
                    List<ImNotifikasiEntity> notifikasiEntityList = notifikasiDao.getDataByNoRequest(imLemburEntity.getLemburId(),imLemburEntity.getNip());

                    if (notifikasiEntityList!=null){
                        for (ImNotifikasiEntity notifikasiEntity : notifikasiEntityList){
                            notifikasiEntity.setFlag("N");

                            try {
                                // Update into database
                                notifikasiDao.updateAndSave(notifikasiEntity);
                            } catch (HibernateException e) {
                                logger.error("[LemburBoImpl.saveEdit] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                            }

                        }
                    }
                }
            } else {
                logger.error("[LemburBoImpl.saveEdit] Error, not found data lembur with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
            }
        }logger.info("[LemburBoImpl.saveEdit] end process <<<");
    }

    @Override
    public List<Lembur> getByCriteria(Lembur searchBean) throws GeneralBOException {
        logger.info("[LemburBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Lembur> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getLemburId() != null && !"".equalsIgnoreCase(searchBean.getLemburId())) {
                hsCriteria.put("lembur_id", searchBean.getLemburId());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getStatusGiling() != null && !"".equalsIgnoreCase(searchBean.getStatusGiling())) {
                hsCriteria.put("status_giling", searchBean.getStatusGiling());
            }
            if (searchBean.getStTanggalAwal() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalAwal()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalAwal());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalAkhir() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalAkhir()))) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalAkhir());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }
            hsCriteria.put("flag", "Y");


            List<LemburEntity> itLemburEntity = null;
            try {
                itLemburEntity = lemburDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.getSearchIjinKeluarByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if(itLemburEntity != null){
                Lembur returnLembur;
                // Looping from dao to object and save in collection
                for(LemburEntity lemburEntity : itLemburEntity){
                    returnLembur = new Lembur();
                    returnLembur.setLemburId(lemburEntity.getLemburId());
                    returnLembur.setNip(lemburEntity.getNip());
                    returnLembur.setPegawaiName(lemburEntity.getPegawaiName());
                    returnLembur.setDivisiId(lemburEntity.getDivisiId());
                    returnLembur.setDivisiName(lemburEntity.getDivisiName());
                    returnLembur.setPositionId(lemburEntity.getPositionId());
                    returnLembur.setPositionName(lemburEntity.getPositionName());
                    returnLembur.setGolonganId(lemburEntity.getGolonganId());
                    returnLembur.setGolonganName(lemburEntity.getGolonganName());
                    returnLembur.setTipePegawaiId(lemburEntity.getTipePegawaiId());
                    returnLembur.setStatusGiling(lemburEntity.getStatusGiling());
                    returnLembur.setTanggalAwal(lemburEntity.getTanggalAwal());
                    returnLembur.setTanggalAkhir(lemburEntity.getTanggalAkhir());
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    returnLembur.setStTanggalAwal(df.format(lemburEntity.getTanggalAwal()));
                    returnLembur.setStTanggalAkhir(df.format(lemburEntity.getTanggalAkhir()));
                    returnLembur.setLamaJam(lemburEntity.getLamaJam());
                    returnLembur.setApprovalFlag(lemburEntity.getApprovalFlag());

                    List<AbsensiPegawaiEntity> absensiPegawaiList = new ArrayList<>();
                    if (lemburEntity.getTanggalAwalSetuju()!=null){
                        try {
                            absensiPegawaiList = absensiPegawaiDao.getListAbsensiByNipAndTanggal(returnLembur.getNip(),lemburEntity.getTanggalAwalSetuju());
                        } catch (HibernateException e) {
                            logger.error("[absensiPegawaiDao.getListAbsensiByNipAndTanggal] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                    }else{
                        returnLembur.setTerRealisasi(false);
                        returnLembur.setJamRealisasi((double) 0);
                    }
                    if (absensiPegawaiList.size()!=0){
                        returnLembur.setAdaAbsen(true);
                        for (AbsensiPegawaiEntity absensiPegawaiEntity : absensiPegawaiList){
                            if (!("Y").equalsIgnoreCase(absensiPegawaiEntity.getLembur())){
                                returnLembur.setTerRealisasi(false);
                                returnLembur.setJamRealisasi((double) 0);
                            }else{
                                returnLembur.setTerRealisasi(true);
                                returnLembur.setJamRealisasi(absensiPegawaiEntity.getRealisasiJamLembur());
                                if (!returnLembur.getJamRealisasi().equals(returnLembur.getLamaJam())){
                                    returnLembur.setSesuai(false);
                                    returnLembur.setJamRealisasi(absensiPegawaiEntity.getRealisasiJamLembur());
                                }
                            }
                            List<LemburEntity> lemburEntities = lemburDao.getListLemburByNipAndTanggal(absensiPegawaiEntity.getNip(),absensiPegawaiEntity.getTanggal());
                            if (lemburEntities.size()>1){
                                Double jamLembur= (double) 0;
                                for (LemburEntity lembur : lemburEntities){
                                    jamLembur = jamLembur+lembur.getLamaJam();
                                }
                                if (jamLembur.equals(absensiPegawaiEntity.getRealisasiJamLembur())){
                                    returnLembur.setSesuai(true);
                                }
                            }
                        }
                    }else{
                        returnLembur.setTerRealisasi(false);
                        returnLembur.setJamRealisasi((double) 0);
                    }
                    returnLembur.setJamAwal(lemburEntity.getJamAwal());
                    returnLembur.setJamAkhir(lemburEntity.getJamAkhir());
                    returnLembur.setTipeLembur(lemburEntity.getTipeLembur());
                    returnLembur.setKeterangan(lemburEntity.getKeterangan());
                    returnLembur.setAction(lemburEntity.getAction());
                    returnLembur.setFlag(lemburEntity.getFlag());
                    returnLembur.setCreatedWho(lemburEntity.getCreatedWho());
                    returnLembur.setCreatedDate(lemburEntity.getCreatedDate());
                    returnLembur.setLastUpdate(lemburEntity.getLastUpdate());

                    if (("Y").equalsIgnoreCase(lemburEntity.getApprovalFlag())){
                        returnLembur.setLemburEdit(false);
                        returnLembur.setLemburApprove(true);
                        returnLembur.setStTanggalAwal(df.format(lemburEntity.getTanggalAwalSetuju()));
                        returnLembur.setStTanggalAkhir(df.format(lemburEntity.getTanggalAkhirSetuju()));
                    }else if (("N").equalsIgnoreCase(lemburEntity.getApprovalFlag())){
                        returnLembur.setNotApprove(true);
                    }
                    if (lemburEntity.getTipeLembur().equalsIgnoreCase("R")){
                        returnLembur.setTipeLemburName("Rutin");
                    }
                    if (lemburEntity.getTipeLembur().equalsIgnoreCase("I")){
                        returnLembur.setTipeLemburName("Non Rutin");
                    }

//                    if (CommonUtil.roleAsLogin().equalsIgnoreCase("ADMIN")){
//                        returnLembur.setCekAdmin(true);
//                    }
                    listOfResult.add(returnLembur);
                }
            }
        }
        logger.info("[IjinKeluarBoImpl.getByCriteria] end process <<<");
        return listOfResult;
    }
    @Override
    public List<Lembur> getBiodatawithCriteria(String query) throws GeneralBOException {
        logger.info("[BiodataBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Biodata> listOfResult = new ArrayList();
        List<Lembur>ListOfLembur = new ArrayList();
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
            Lembur returnLembur;
            // Looping from dao to object and save in collection
            for (ImBiodataEntity personalEntity : imBiodataEntity) {
                returnLembur = new Lembur();
                returnLembur.setNip(personalEntity.getNip());
                returnLembur.setPegawaiName(personalEntity.getNamaPegawai());
                returnLembur.setStatusGiling(personalEntity.getMasaGiling());
                returnLembur.setGolonganId(personalEntity.getGolongan());
                returnLembur.setTipePegawaiId(personalEntity.getTipePegawai());
                itPersonilPositionEntity = personilPositionDao.getById("nip",personalEntity.getNip(),"Y" );
                if(itPersonilPositionEntity != null){
                    returnLembur.setBranchId(itPersonilPositionEntity.getBranchId());
                }
                if(itPersonilPositionEntity.getPositionId() != null){
                    returnLembur.setPositionId(itPersonilPositionEntity.getPositionId());
                }else{
                    returnLembur.setPositionId("");
                }
                ImPosition imPosition = positionDao.getById("positionId",returnLembur.getPositionId());

                if (imPosition!=null){
                    returnLembur.setDivisiId(imPosition.getDepartmentId());
                }
                ListOfLembur.add(returnLembur);
            }
        }
        logger.info("[BiodataBoImpl.getByCriteria] end process <<<");

        return ListOfLembur;
    }

    @Override
    public List<Lembur> getByCriteriaForAbsensi(Lembur bean, String tanggal){
        logger.info("[LemburBoImpl.saveAdd] start process >>>");
        List<Lembur> listOfResult = new ArrayList();
        Lembur returnLembur = new Lembur();
        if (bean != null) {
            List<LemburEntity> lemburEntityList = new ArrayList<>();
            lemburEntityList = lemburDao.getListPersonalFromNip(bean.getNip(),CommonUtil.convertToDate(tanggal));
            for (LemburEntity lemburEntity:lemburEntityList){
                returnLembur.setJamAwal(lemburEntity.getJamAwal());
                returnLembur.setJamAkhir(lemburEntity.getJamAkhir());
                if (lemburEntity.getTipeLembur().equalsIgnoreCase("R")){
                    returnLembur.setTipeLemburName("Rutin");
                }else{
                    returnLembur.setTipeLemburName("Non Rutin");
                }
                listOfResult.add(returnLembur);
            }
        }
        return listOfResult;
    }
    @Override
    public Lembur saveAdd(Lembur bean) throws GeneralBOException {
        logger.info("[LemburBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            String lemburId,nip;
            String atasanName;
            String atasanNip = null;
            Map hsCriteria = new HashMap();
            if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                hsCriteria.put("nip", bean.getNip());
            }
            try {
                // Generating ID, get from postgre sequence
                lemburId = lemburDao.getNextLemburId();
            } catch (HibernateException e) {
                logger.error("[LemburBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence lembur id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            LemburEntity itLemburEntity = new LemburEntity();
            ImDepartmentEntity imDepartmentEntity = null;
            ImBiodataEntity imBiodataEntity = null ;
            ImPosition imPosition = null;
            ImGolonganEntity imGolonganEntity=null;

            if (!bean.getNip().equals("")||bean.getNip()!=null){
                imBiodataEntity = biodataDao.getById("nip", bean.getNip(), "Y");
            }
            if (!bean.getPositionId().equals("")||bean.getPositionId()!=null){
                imPosition = positionDao.getById("positionId", bean.getPositionId(), "Y");
            }
            if (!bean.getDivisiId().equals("")||bean.getDivisiId()!=null){
                imDepartmentEntity= departmentDao.getById("departmentId", bean.getDivisiId(), "Y");
            }
            if (!bean.getGolonganId().equals("")||bean.getGolonganId()!=null){
                imGolonganEntity= golonganDao.getById("golonganId", bean.getGolonganId(), "Y");
            }
            List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getListPersonilPosition(bean.getNip());
            for (ItPersonilPositionEntity personilPositionEntity : personilPositionEntityList){
                bean.setBranchId(personilPositionEntity.getBranchId());
            }
            itLemburEntity.setLemburId(lemburId);
            itLemburEntity.setNip(bean.getNip());
            itLemburEntity.setPegawaiName(bean.getPegawaiName());
            itLemburEntity.setDivisiId(bean.getDivisiId());
            itLemburEntity.setPositionId(bean.getPositionId());
            itLemburEntity.setGolonganId(bean.getGolonganId());
            itLemburEntity.setTipePegawaiId(bean.getTipePegawaiId());
            itLemburEntity.setStatusGiling(bean.getStatusGiling());
            itLemburEntity.setJamAwal(bean.getJamAwal());
            itLemburEntity.setJamAkhir(bean.getJamAkhir());
            itLemburEntity.setLamaJam(bean.getLamaJam());
            itLemburEntity.setKeterangan(bean.getKeterangan());
            itLemburEntity.setTipeLembur(bean.getTipeLembur());
            itLemburEntity.setTanggalAwal(bean.getTanggalAwal());
            itLemburEntity.setTanggalAkhir(bean.getTanggalAkhir());
            itLemburEntity.setTanggalAwalSetuju(bean.getTanggalAwal());
            itLemburEntity.setTanggalAkhirSetuju(bean.getTanggalAkhir());
            itLemburEntity.setPositionName(imPosition.getPositionName());
            itLemburEntity.setDivisiName(imDepartmentEntity.getDepartmentName());
            if (imGolonganEntity!=null){
                itLemburEntity.setGolonganName(imGolonganEntity.getGolonganName());
            }
            itLemburEntity.setFlag(bean.getFlag());
            itLemburEntity.setAction(bean.getAction());
            itLemburEntity.setCreatedWho(bean.getCreatedWho());
            itLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itLemburEntity.setCreatedDate(bean.getCreatedDate());
            itLemburEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                lemburDao.addAndSave(itLemburEntity);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }



            List<StrukturJabatan> strukturJabatanList = new ArrayList<>();

            try {
                strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(bean.getNip(),bean.getBranchId());
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            for (StrukturJabatan strukturJabatan:strukturJabatanList){
                // Search Leader
                if (strukturJabatan != null){
                    String[] parts = strukturJabatan.getParentId().split("-");
                    String parent = parts[0];
                    int batas=1;
                    if (parent != null){
                        boolean sudah = false;
                        //cek apakah parent adalah kabag
                        StrukturJabatan hasilStruktur=new StrukturJabatan();
                        List<StrukturJabatan> resultStruktur = new ArrayList<>();
                        do {
                            try {
                                resultStruktur = strukturJabatanDao.cekKelompok(parent,bean.getBranchId());
                            } catch (HibernateException e) {
                                logger.error("[LemburBoimpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                            for (StrukturJabatan search : resultStruktur){
                                hasilStruktur.setPositionKelompokId(search.getPositionKelompokId());
                                hasilStruktur.setNip(search.getNip());
                                hasilStruktur.setParentId(search.getParentId());
                                parent=search.getParentId();

                            }
                            batas++;
                            if (("KL04").equalsIgnoreCase(hasilStruktur.getPositionKelompokId())||batas==10){
                                sudah=true;
                                atasanNip=hasilStruktur.getNip();
                            }
                        }while (!sudah);
                        // Send Notification
                        if (sudah){
                            break;
                        }
                    }
                }
            }
            ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
            String idNotif = notifikasiDao.getNextNotifikasiId();
            String noteMobile = "Data Dari User : " + itLemburEntity.getPegawaiName() + " Menunggu di Approve";
            addNotif.setNotifId(idNotif);
            addNotif.setNote("Data Dari User : " + itLemburEntity.getPegawaiName() + " Menunggu di Approve");
            addNotif.setTipeNotifId("TN77");
            addNotif.setTipeNotifName("Lembur");
            addNotif.setRead("Y");
            addNotif.setFlag("Y");
            addNotif.setAction("C");
            addNotif.setNip(atasanNip);
            addNotif.setFromPerson(itLemburEntity.getNip());
            addNotif.setNoRequest(itLemburEntity.getLemburId());
            addNotif.setCreatedDate(itLemburEntity.getCreatedDate());
            addNotif.setCreatedWho(itLemburEntity.getCreatedWho());
            addNotif.setLastUpdate(itLemburEntity.getLastUpdate());
            addNotif.setLastUpdateWho(itLemburEntity.getLastUpdateWho());
            try {
                notifikasiDao.addAndSave(addNotif);
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
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
                    ExpoPushNotif.sendNotificationExpo(entity.getTokenExpo(), addNotif.getTipeNotifName(), noteMobile,  bean.getOs());
                    break;
                }
            }

//            for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                if(entity.getUserId().equals(atasanNip)){
//                    FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), noteMobile, ACTION_CLICK);
//                    break;
//                }
//            }
        }
        logger.info("[IjinKeluarBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Notifikasi> saveAddLembur(Lembur bean) throws GeneralBOException {
        logger.info("[LemburBoImpl.saveAdd] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();

        if (bean != null) {
            String lemburId;
            Map hsCriteria = new HashMap();
            if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                hsCriteria.put("nip", bean.getNip());
            }
            try {
                // Generating ID, get from postgre sequence
                lemburId = lemburDao.getNextLemburId();
            } catch (HibernateException e) {
                logger.error("[LemburBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence lembur id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            LemburEntity itLemburEntity = new LemburEntity();
            ImDepartmentEntity imDepartmentEntity = null;
            ImBiodataEntity imBiodataEntity = null ;
            ImPosition imPosition = null;
            ImGolonganEntity imGolonganEntity=null;

            if (!bean.getNip().equals("")||bean.getNip()!=null){
                imBiodataEntity = biodataDao.getById("nip", bean.getNip(), "Y");
            }
            if (!bean.getPositionId().equals("")||bean.getPositionId()!=null){
                imPosition = positionDao.getById("positionId", bean.getPositionId(), "Y");
            }
            if (!bean.getDivisiId().equals("")||bean.getDivisiId()!=null){
                imDepartmentEntity= departmentDao.getById("departmentId", bean.getDivisiId(), "Y");
            }
            if (!bean.getGolonganId().equals("")||bean.getGolonganId()!=null){
                imGolonganEntity= golonganDao.getById("golonganId", bean.getGolonganId(), "Y");
            }
            List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getListPersonilPosition(bean.getNip());
            for (ItPersonilPositionEntity personilPositionEntity : personilPositionEntityList){
                bean.setBranchId(personilPositionEntity.getBranchId());
            }
            itLemburEntity.setLemburId(lemburId);
            itLemburEntity.setNip(bean.getNip());
            itLemburEntity.setPegawaiName(bean.getPegawaiName());
            itLemburEntity.setDivisiId(bean.getDivisiId());
            itLemburEntity.setPositionId(bean.getPositionId());
            itLemburEntity.setGolonganId(bean.getGolonganId());
            itLemburEntity.setTipePegawaiId(bean.getTipePegawaiId());
            itLemburEntity.setStatusGiling(bean.getStatusGiling());
            itLemburEntity.setJamAwal(bean.getJamAwal());
            itLemburEntity.setJamAkhir(bean.getJamAkhir());
            itLemburEntity.setLamaJam(bean.getLamaJam());
            itLemburEntity.setKeterangan(bean.getKeterangan());
            itLemburEntity.setTipeLembur(bean.getTipeLembur());
            itLemburEntity.setTanggalAwal(bean.getTanggalAwal());
            itLemburEntity.setTanggalAkhir(bean.getTanggalAkhir());
            itLemburEntity.setTanggalAwalSetuju(bean.getTanggalAwal());
            itLemburEntity.setTanggalAkhirSetuju(bean.getTanggalAkhir());
            itLemburEntity.setPositionName(imPosition.getPositionName());
            itLemburEntity.setDivisiName(imDepartmentEntity.getDepartmentName());
            if (imGolonganEntity!=null){
                itLemburEntity.setGolonganName(imGolonganEntity.getGolonganName());
            }
            itLemburEntity.setFlag(bean.getFlag());
            itLemburEntity.setAction(bean.getAction());
            itLemburEntity.setCreatedWho(bean.getCreatedWho());
            itLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itLemburEntity.setCreatedDate(bean.getCreatedDate());
            itLemburEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                lemburDao.addAndSave(itLemburEntity);
            } catch (HibernateException e) {
                logger.error("[IjinKeluarBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            //Send notif ke kabag
            Notifikasi notifAtasan= new Notifikasi();
            notifAtasan.setNip(bean.getNip());
            notifAtasan.setNoRequest(lemburId);
            notifAtasan.setTipeNotifId("TN77");
            notifAtasan.setTipeNotifName(("Lembur"));
            notifAtasan.setNote("Data Dari User : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
            notifAtasan.setCreatedWho(bean.getNip());
            notifAtasan.setTo("kabag");
            notifAtasan.setOs(bean.getOs());

            notifikasiList.add(notifAtasan);
        }
        logger.info("[IjinKeluarBoImpl.saveAdd] end process <<<");
        return notifikasiList;
    }


    @Override
    public List<Notifikasi> saveApprove(Lembur bean) throws GeneralBOException {
        logger.info("[LemburBoImpl.saveApprove] start process >>>");
        List<ImStrukturJabatanEntity> atasan;
        List<Notifikasi> notifikasiList = new ArrayList<>();
        String atasanName;
        Map hsCriteria = new HashMap();
        String atasanNip = null;

        if (bean!=null) {
            String LemburId = bean.getLemburId();
            LemburEntity itLemburEntity = null;
            try {
                // Get data from database by ID
                itLemburEntity = lemburDao.getById("lemburId", LemburId);
            } catch (HibernateException e) {
                logger.error("[LemburBoImpl.saveApprove] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Lembur by Kode Lembur, please inform to your admin...," + e.getMessage());
            }

            if (itLemburEntity != null) {
                itLemburEntity.setLemburId(bean.getLemburId());
                itLemburEntity.setFlag(bean.getFlag());
                //Approve
                if(bean.getTmpApprove().equals("atasan")) {
                    if (bean.getApprovalFlag().equals("Y")) {
                        itLemburEntity.setApprovalFlag("Y");
                    }else{
                        itLemburEntity.setApprovalFlag("N");
                    }
                    itLemburEntity.setTanggalAwalSetuju(bean.getTanggalAwalSetuju());
                    itLemburEntity.setTanggalAkhirSetuju(bean.getTanggalAkhirSetuju());
                    itLemburEntity.setLamaJam(bean.getLamaJam());
                    itLemburEntity.setApprovalId(bean.getApprovalId());
                    itLemburEntity.setApprovalName(bean.getApprovalName());
                    itLemburEntity.setApprovalDate(bean.getLastUpdate());
                }
                itLemburEntity.setNotApprovalNote(bean.getNotApprovalNote());
                itLemburEntity.setAction(bean.getAction());
                itLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itLemburEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    lemburDao.updateAndSave(itLemburEntity);
                } catch (HibernateException e) {
                    logger.error("[IjinKeluarBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data IjinKeluar, please info to your admin..." + e.getMessage());
                }


                // SEND NOTIF
                ImBiodataEntity imBiodataEntity =  biodataDao.getById("nip", itLemburEntity.getNip(), "Y");
                Integer kelompok = personilPositionDao.getKelompokPosition(itLemburEntity.getNip());

                if (bean.getApprovalFlag().equals("Y")) {
                    //Send notif ke orang yang mengajukan
                    Notifikasi notifSelf= new Notifikasi();
                    notifSelf.setNip(itLemburEntity.getNip());
                    notifSelf.setNoRequest(bean.getLemburId());
                    notifSelf.setTipeNotifId("umum");
                    notifSelf.setTipeNotifName(("Lembur"));
                    notifSelf.setNote("Lembur anda pada tanggal "+bean.getStTanggalAwal()+" sampai dengan tanggal "+bean.getStTanggalAkhir()+" di approve oleh atasan anda");
                    notifSelf.setCreatedWho(itLemburEntity.getNip());
                    notifSelf.setTo("self");

                    notifikasiList.add(notifSelf);
                }else{
                    String msg="";
                    if (!("").equalsIgnoreCase(itLemburEntity.getNotApprovalNote())){
                        msg="dikarenakan "+itLemburEntity.getNotApprovalNote();
                    }
                    //Send notif ke orang yang mengajukan
                    Notifikasi notifSelf= new Notifikasi();
                    notifSelf.setNip(itLemburEntity.getNip());
                    notifSelf.setNoRequest(bean.getLemburId());
                    notifSelf.setTipeNotifId("umum");
                    notifSelf.setTipeNotifName(("Lembur"));
                    notifSelf.setNote("Lembur anda pada tanggal "+bean.getStTanggalAwal()+" sampai dengan tanggal "+bean.getStTanggalAkhir()+" tidak di approve oleh atasan "+msg);
                    notifSelf.setCreatedWho(itLemburEntity.getNip());
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
    public String testTanggal(Date tanggalAwal,Date tanggalAkhir, String nip){
        logger.info("[LemburBoImpl.testTanggal] start process >>>");
        String status="";
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        java.sql.Date now = new java.sql.Date(cal.getTime().getTime());
        /*if (tanggalAwal.before(now)||tanggalAkhir.before(now)){
            status = "Tanggal tidak boleh sebelum tanggal sekarang";
        }else if (tanggalAwal.compareTo(tanggalAkhir)>0){
            status="tanggal mulai tidak boleh melebihi tanggal selesai";

        } else{*/
            List<ItCutiPegawaiEntity> cutiPegawaiEntityList = new ArrayList<>();
            List<LemburEntity> lemburEntityList = new ArrayList<>();
            List<ItHrisTrainingPersonEntity> trainingPersonEntityList = new ArrayList<>();
            List<ItHrisTrainingEntity> trainingEntityList = new ArrayList<>();
            List<IjinKeluarEntity> ijinKeluarEntityList = new ArrayList<>();
            List<ItSppdPersonEntity> sppdPersonEntityList = new ArrayList<>();
            try {
                Calendar start = Calendar.getInstance();
                start.setTime(tanggalAwal);
                Calendar end = Calendar.getInstance();
                end.setTime(tanggalAkhir);
                end.add(Calendar.DATE,1);
                java.util.Date date;
                for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                Date tanggal = new Date(date.getTime());
                    cutiPegawaiEntityList = cutiPegawaiDao.getListTestTanggal(tanggal,nip);
                    lemburEntityList = lemburDao.getListLemburTestTanggal(tanggal,nip);
                    trainingEntityList = trainingDao.getListTrainingByNipAndTanggalForTest(tanggal);
                    for (ItHrisTrainingEntity itHrisTrainingEntity : trainingEntityList){
                        trainingPersonEntityList = trainingPersonDao.getListTrainingByNipAndId(itHrisTrainingEntity.getTrainingId(),nip);
                    }
                    ijinKeluarEntityList = ijinKeluarDao.getListTestTanggal(tanggal,nip);
                    sppdPersonEntityList = sppdPersonDao.getListTestTanggal(tanggal,nip);

                    if (cutiPegawaiEntityList.size()!=0||lemburEntityList.size()!=0||trainingPersonEntityList.size()!=0||ijinKeluarEntityList.size()!=0||sppdPersonEntityList.size()!=0){
                        break;
                    }
                }


            } catch (HibernateException e) {
                logger.error("[LemburBoImpl.testTanggal] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (cutiPegawaiEntityList.size()!=0){
                status =  "Maaf Sudah mengajukan Cuti di tanggal ini.";
            }else if(lemburEntityList.size()!=0){
                status =  "Maaf Sudah mengajukan Lembur di tanggal ini.";
            }else if(trainingPersonEntityList.size()!=0){
                status =  "Maaf Sudah mengajukan Training di tanggal ini.";
            }else if (ijinKeluarEntityList.size()!=0){
                status =  "Maaf Sudah mengajukan Ijin Tidak Masuk di tanggal ini.";
            }else if (sppdPersonEntityList.size()!=0){
                status =  "Maaf Sudah mengajukan SPPD di tanggal ini.";
            }else {
                status = "";
            }
//        }
        return status;
    }

    @Override
    public List<Object[]> findInfoLembur(String idLembur, String nip) throws GeneralBOException {
        logger.info("[LemburBoImpl.findInfoLembur] start process >>>");
        List<Object[]> listLembur = null;

        try {
            listLembur = lemburDao.findInfoLembur(idLembur, nip);
        } catch (HibernateException e) {
            logger.error("[LemburBoImpl.findInfoLembur] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[LemburBoImpl.findInfoLembur] end process <<<");
        return listLembur;
    }

    @Override
    public List<Object[]> findAllConfirmByAtasan(String id, String flag) throws GeneralBOException {
        logger.info("[LemburBoImpl.findAllConfirmByIdAtasan] start process >>>");
        List<Object[]> listConfirmStatus = null;

        try {
            listConfirmStatus = lemburDao.findAllConfirmByIdAtasan(id, flag);
        } catch (HibernateException e) {
            logger.error("[LemburBoImpl.findAllConfirmByIdAtasan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[LemburBoImpl.findAllConfirmByIdAtasan] end process <<<");
        return listConfirmStatus;
    }

    @Override
    public List<Lembur> getAll() throws GeneralBOException {
        return null;
    }
    @Override
    public List<Lembur> getCekLembur(String nip, Date tanggal) {
        List<Lembur> result = new ArrayList<>();
        List<LemburEntity> lemburEntityList = new ArrayList<>();
        Lembur hasil = new Lembur();

        try {
            lemburEntityList = lemburDao.getCekLembur(nip,tanggal);
        } catch (HibernateException e) {
            logger.error("[LemburBoImpl.getNotApprove] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        for (LemburEntity lemburEntity : lemburEntityList){
            hasil.setLemburId(lemburEntity.getLemburId());
            result.add(hasil);
        }
        return result;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
