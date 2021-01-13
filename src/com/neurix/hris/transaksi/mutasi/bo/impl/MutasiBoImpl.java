package com.neurix.hris.transaksi.mutasi.bo.impl;

import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.dao.PelatihanJabatanUserDao;
import com.neurix.hris.master.biodata.dao.TunjLainPegawaiDao;
import com.neurix.hris.master.biodata.model.*;
import com.neurix.hris.master.jenisPegawai.dao.JenisPegawaiDao;
import com.neurix.hris.master.jenisPegawai.model.ImHrisJenisPegawaiEntity;
import com.neurix.hris.master.jenisPegawai.model.JenisPegawai;
import com.neurix.hris.master.keluarga.dao.KeluargaDao;
import com.neurix.hris.master.keluarga.model.ImKeluargaEntity;
import com.neurix.hris.master.keluarga.model.Keluarga;
import com.neurix.hris.master.kualifikasiCalonPejabat.dao.KualifikasiCalonPejabatDao;
import com.neurix.hris.master.kualifikasiCalonPejabat.model.ImHrisKualifikasiCalonPejabatEntity;
import com.neurix.hris.master.kualifikasiCalonPejabat.model.KualifikasiCalonPejabat;
import com.neurix.hris.master.payrollSkalaGaji.dao.PayrollSkalaGajiDao;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.pelatihanJabatan.model.PelatihanJabatan;
import com.neurix.hris.master.sertifikat.dao.SertifikatDao;
import com.neurix.hris.master.sertifikat.model.ImSertifikatEntity;
import com.neurix.hris.master.statusMutasi.dao.StatusMutasiDao;
import com.neurix.hris.master.statusMutasi.model.ImHrisStatusMutasiEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.master.study.dao.StudyDao;
import com.neurix.hris.master.study.model.ImStudyEntity;
import com.neurix.hris.master.study.model.Study;
import com.neurix.hris.master.studyJurusan.dao.StudyJurusanDao;
import com.neurix.hris.master.studyJurusan.model.ImStudyJurusanEntity;
import com.neurix.hris.master.updateGolongan.dao.UpdateGolonganDao;
import com.neurix.hris.master.updateGolongan.model.ImtUpdateGolonganEntity;
import com.neurix.hris.transaksi.cutiPegawai.dao.CutiPegawaiDao;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
import com.neurix.hris.transaksi.ijinKeluar.dao.IjinKeluarDao;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarEntity;
import com.neurix.hris.transaksi.lembur.dao.LemburDao;
import com.neurix.hris.transaksi.lembur.model.LemburEntity;
import com.neurix.hris.transaksi.mutasi.dao.MutasiDao;
import com.neurix.hris.transaksi.mutasi.dao.MutasiDocDao;
import com.neurix.hris.transaksi.mutasi.model.ItMutasiDocEntity;
import com.neurix.hris.transaksi.mutasi.model.Mutasi;
import com.neurix.hris.transaksi.mutasi.model.ItMutasiEntity;
import com.neurix.hris.transaksi.mutasi.bo.MutasiBo;
import com.neurix.hris.transaksi.mutasi.model.MutasiDoc;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.payroll.dao.PayrollTunjanganLainDao;
import com.neurix.hris.transaksi.personilPosition.dao.HistoryJabatanPegawaiDao;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.HistoryJabatanPegawai;
import com.neurix.hris.transaksi.personilPosition.model.ImtHrisHistoryJabatanPegawaiEntity;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.smk.dao.SmkHistoryGolonganDao;
import com.neurix.hris.transaksi.smk.model.ImtHistorySmkGolonganEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class MutasiBoImpl implements MutasiBo {

    protected static transient Logger logger = Logger.getLogger(MutasiBoImpl.class);
    private BiodataDao biodataDao;
    private MutasiDao mutasiDao;
    private KualifikasiCalonPejabatDao kualifikasiCalonPejabatDao;
    private MutasiDocDao mutasiDocDao;
    private StrukturJabatanDao strukturJabatanDao;
    private PersonilPositionDao personilPositionDao;
    private PositionDao positionDao;
    private StudyDao studyDao;
    private SertifikatDao sertifikatDao;
    private HistoryJabatanPegawaiDao historyJabatanPegawaiDao;
    private SmkHistoryGolonganDao historyGolonganDao;
    private PayrollSkalaGajiDao skalaGajiDao;
    private IjinKeluarDao ijinKeluarDao;
    private CutiPegawaiDao cutiPegawaiDao;
    private LemburDao lemburDao;
    private StatusMutasiDao statusMutasiDao;
    private JenisPegawaiDao jenisPegawaiDao;
    private KeluargaDao keluargaDao;
    private StudyJurusanDao studyJurusanDao;
    private TunjLainPegawaiDao tunjLainPegawaiDao;

    public void setStudyJurusanDao(StudyJurusanDao studyJurusanDao) {
        this.studyJurusanDao = studyJurusanDao;
    }

    public void setTunjLainPegawaiDao(TunjLainPegawaiDao tunjLainPegawaiDao) {
        this.tunjLainPegawaiDao = tunjLainPegawaiDao;
    }

    public void setKeluargaDao(KeluargaDao keluargaDao) {
        this.keluargaDao = keluargaDao;
    }

    public void setJenisPegawaiDao(JenisPegawaiDao jenisPegawaiDao) {
        this.jenisPegawaiDao = jenisPegawaiDao;
    }

    public StatusMutasiDao getStatusMutasiDao() {
        return statusMutasiDao;
    }

    public void setStatusMutasiDao(StatusMutasiDao statusMutasiDao) {
        this.statusMutasiDao = statusMutasiDao;
    }

    public IjinKeluarDao getIjinKeluarDao() {
        return ijinKeluarDao;
    }

    public void setIjinKeluarDao(IjinKeluarDao ijinKeluarDao) {
        this.ijinKeluarDao = ijinKeluarDao;
    }

    public CutiPegawaiDao getCutiPegawaiDao() {
        return cutiPegawaiDao;
    }

    public void setCutiPegawaiDao(CutiPegawaiDao cutiPegawaiDao) {
        this.cutiPegawaiDao = cutiPegawaiDao;
    }

    public LemburDao getLemburDao() {
        return lemburDao;
    }

    public void setLemburDao(LemburDao lemburDao) {
        this.lemburDao = lemburDao;
    }

    public PayrollSkalaGajiDao getSkalaGajiDao() {
        return skalaGajiDao;
    }

    public void setSkalaGajiDao(PayrollSkalaGajiDao skalaGajiDao) {
        this.skalaGajiDao = skalaGajiDao;
    }

    private PelatihanJabatanUserDao pelatihanJabatanUserDao;

    public PelatihanJabatanUserDao getPelatihanJabatanUserDao() {
        return pelatihanJabatanUserDao;
    }

    public void setPelatihanJabatanUserDao(PelatihanJabatanUserDao pelatihanJabatanUserDao) {
        this.pelatihanJabatanUserDao = pelatihanJabatanUserDao;
    }

    public SmkHistoryGolonganDao getHistoryGolonganDao() {
        return historyGolonganDao;
    }

    public void setHistoryGolonganDao(SmkHistoryGolonganDao historyGolonganDao) {
        this.historyGolonganDao = historyGolonganDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public SertifikatDao getSertifikatDao() {
        return sertifikatDao;
    }

    public void setSertifikatDao(SertifikatDao sertifikatDao) {
        this.sertifikatDao = sertifikatDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public KualifikasiCalonPejabatDao getKualifikasiCalonPejabatDao() {
        return kualifikasiCalonPejabatDao;
    }

    public void setKualifikasiCalonPejabatDao(KualifikasiCalonPejabatDao kualifikasiCalonPejabatDao) {
        this.kualifikasiCalonPejabatDao = kualifikasiCalonPejabatDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public HistoryJabatanPegawaiDao getHistoryJabatanPegawaiDao() {
        return historyJabatanPegawaiDao;
    }

    public void setHistoryJabatanPegawaiDao(HistoryJabatanPegawaiDao historyJabatanPegawaiDao) {
        this.historyJabatanPegawaiDao = historyJabatanPegawaiDao;
    }

    private ItMutasiDocEntity itMutasiDocEntity ;

    public ItMutasiDocEntity getItMutasiDocEntity() {
        return itMutasiDocEntity;
    }

    public void setItMutasiDocEntity(ItMutasiDocEntity itMutasiDocEntity) {
        this.itMutasiDocEntity = itMutasiDocEntity;
    }

    public MutasiDocDao getMutasiDocDao() {
        return mutasiDocDao;
    }

    public void setMutasiDocDao(MutasiDocDao mutasiDocDao) {
        this.mutasiDocDao = mutasiDocDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
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

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MutasiBoImpl.logger = logger;
    }

    public MutasiDao getMutasiDao() {
        return mutasiDao;
    }


    public void setMutasiDao(MutasiDao mutasiDao) {
        this.mutasiDao = mutasiDao;
    }

    @Override
    public void saveDelete(Mutasi bean) throws GeneralBOException {
        /*logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String mutasiId = bean.getMutasiId();

            ItMutasiEntity imMutasiEntity = null;

            try {
                // Get data from database by ID
                imMutasiEntity = mutasiDao.getById("mutasiId", mutasiId);
            } catch (HibernateException e) {
                logger.error("[MutasiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imMutasiEntity != null) {

                // Modify from bean to entity serializable
                imMutasiEntity.setMutasiId(bean.getMutasiId());
                imMutasiEntity.setMutasiName(bean.getMutasiName());
                imMutasiEntity.setFlag(bean.getFlag());
                imMutasiEntity.setAction(bean.getAction());
                imMutasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imMutasiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    mutasiDao.updateAndSave(imMutasiEntity);
                } catch (HibernateException e) {
                    logger.error("[MutasiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Mutasi, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[MutasiBoImpl.saveDelete] Error, not found data Mutasi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Mutasi with request id, please check again your data ...");

            }
        }
        logger.info("[MutasiBoImpl.saveDelete] end process <<<");*/
    }

    @Override
    public void saveEdit(Mutasi bean) throws GeneralBOException {

    }

    @Override
    public Mutasi saveAdd(Mutasi bean) throws GeneralBOException {

        return null;
    }

    @Override
    public void saveMutasi(Mutasi bean, List<Mutasi> mutasiList) throws GeneralBOException {
        logger.info("[MutasiBoImpl.saveMutasi] start process >>>");
        List<ItCutiPegawaiEntity> cutiPegawaiEntityList = new ArrayList<>();
        List<LemburEntity> lemburEntityList = new ArrayList<>();
        List<IjinKeluarEntity> ijinKeluarEntityList = new ArrayList<>();

        // Sigit 2020-01-06, Penyesuaian dengan bisnis proses yang baru

        for (Mutasi dataMutasi : mutasiList){

            //validasi jika ada pengajuan menggantung
            try {
                cutiPegawaiEntityList = cutiPegawaiDao.getListCekCuti(dataMutasi.getNip());
                lemburEntityList = lemburDao.getCekLembur(dataMutasi.getNip(), (CommonUtil.convertStringToDate("")));
                ijinKeluarEntityList = ijinKeluarDao.getListCekIjinKeluar(dataMutasi.getNip());
            }catch (HibernateException e){
                logger.error("[MutasiBoImpl.saveMutasi] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if(cutiPegawaiEntityList.size() > 0){
                String status = "Maaf masih ada Cuti yang menggantung di tanggal ini.";
                logger.error("[MutasiBoImpl.saveMutasi] Error :, " + status);
                throw new GeneralBOException(status);
            }else if(lemburEntityList.size() > 0){
                String status = "Maaf masih ada Lembur yang menggantung di tanggal ini.";
                logger.error("[MutasiBoImpl.saveMutasi] Error :, " + status);
                throw new GeneralBOException(status);
            }else if(ijinKeluarEntityList.size() > 0){
                String status = "Maaf masih ada Dispensasi yang menggantung di tanggal ini.";
                logger.error("[MutasiBoImpl.saveMutasi] Error :, " + status);
                throw new GeneralBOException(status);
            }

            if (("M").equalsIgnoreCase(dataMutasi.getStatus())||("R").equalsIgnoreCase(dataMutasi.getStatus())){
                if (("M").equalsIgnoreCase(dataMutasi.getTipeMutasi())){
                    if (dataMutasi.getBranchBaruId().equalsIgnoreCase(dataMutasi.getBranchLamaId())){
                        String status ="ERROR : "+dataMutasi.getNama()+" , gunakan rotasi jika unitnya sama";
                        logger.error("[PengalamanKerjaBoImpl.save mutasi] "+ status);
                        throw new GeneralBOException(status);
                    }
                }else if (("R").equalsIgnoreCase(dataMutasi.getTipeMutasi())){
                    if (!dataMutasi.getBranchBaruId().equalsIgnoreCase(dataMutasi.getBranchLamaId())){
                        String status ="ERROR : "+dataMutasi.getNama()+" , gunakan Mutasi jika unitnya berbeda";
                        logger.error("[PengalamanKerjaBoImpl.save mutasi] "+ status);
                        throw new GeneralBOException(status);
                    }
                }
            }
        }

        // loop mutasi dan insert berdarkan status mutasi
        if (bean!=null) {
            if(mutasiList != null){
                for (Mutasi mutasi: mutasiList) {

                    ItMutasiEntity itMutasiEntity = new ItMutasiEntity();
                    ItMutasiDocEntity itDoc = new ItMutasiDocEntity();

                    String mutasiId = mutasiDao.getNextMutasiId() ;
                    itMutasiEntity.setMutasiId(mutasiId);
                    itMutasiEntity.setNip(mutasi.getNip());
                    itMutasiEntity.setBranchLamaId(mutasi.getBranchLamaId());
                    if(!"".equalsIgnoreCase(mutasi.getDivisiLamaId())){
                        itMutasiEntity.setDivisiLamaId(mutasi.getDivisiLamaId());
                    }
                    itMutasiEntity.setPositionLamaId(mutasi.getPositionLamaId());
                    itMutasiEntity.setMenggantikanNip(mutasi.getPenggantiNip());
                    itMutasiEntity.setStatus(mutasi.getStatus());
                    itMutasiEntity.setTipeMutasi(mutasi.getTipeMutasi());
                    itMutasiEntity.setLevelLama(mutasi.getLevelLama());
                    itMutasiEntity.setLevelLamaName(mutasi.getLevelLamaName());

                    if("M".equalsIgnoreCase(mutasi.getStatus()) || "R".equalsIgnoreCase(mutasi.getStatus())){
                        itMutasiEntity.setBranchBaruId(mutasi.getBranchBaruId());
                        itMutasiEntity.setDivisiBaruId(mutasi.getDivisiBaruId());
                        itMutasiEntity.setPositionBaruId(mutasi.getPositionBaruId());
                        itMutasiEntity.setLevelBaru(mutasi.getLevelBaru());
                        itMutasiEntity.setLevelBaruName(mutasi.getLevelBaruName());
                    }

                    if ("RS".equalsIgnoreCase(mutasi.getStatus())){
                        itMutasiEntity.setIdKet(mutasi.getIdKetResign());
                    }

                    itMutasiEntity.setPjs(mutasi.getPjs());
                    itMutasiEntity.setFlag(bean.getFlag());
                    itMutasiEntity.setTanggalEfektif(bean.getTanggalEfektif());
                    itMutasiEntity.setAction(bean.getAction());
                    itMutasiEntity.setCreatedWho(bean.getCreatedWho());
                    itMutasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    itMutasiEntity.setCreatedDate(bean.getCreatedDate());
                    itMutasiEntity.setLastUpdate(bean.getLastUpdate());
                    itMutasiEntity.setNoSk(mutasi.getNoSk());

                    itDoc.setDocMutasiId(mutasiDocDao.getNextId());
                    itDoc.setMutasiId(mutasiId);
                    itDoc.setFlag(bean.getFlag());
                    itDoc.setAction(bean.getAction());
                    itDoc.setCreatedWho(bean.getCreatedWho());
                    itDoc.setLastUpdateWho(bean.getLastUpdateWho());
                    itDoc.setCreatedDate(bean.getCreatedDate());
                    itDoc.setLastUpdate(bean.getLastUpdate());

                    String tgl[] = CommonUtil.convertTimestampToString(bean.getCreatedDate()).split("-");
                    bean.setStTahun(tgl[2]);
                    if (("M").equalsIgnoreCase(mutasi.getStatus())||("R").equalsIgnoreCase(mutasi.getStatus())){

                        // cek apakah update golongan sudah diiinsert
                        List<ImtHistorySmkGolonganEntity> smkGolongan = historyGolonganDao.getHistoryJabatan(mutasi.getNip(), tgl[2]);
                        if(smkGolongan.size() > 0){
                            for(ImtHistorySmkGolonganEntity smkGolonganLoop: smkGolongan){
                                ImtHistorySmkGolonganEntity smkGolonganEntity = historyGolonganDao.getById("idHistorySmkGolongan", smkGolonganLoop.getIdHistorySmkGolongan());
                                smkGolonganEntity.setFlagMutasi("Y");
                                smkGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                smkGolonganEntity.setLastUpdate(bean.getLastUpdate());
                                historyGolonganDao.updateAndSave(smkGolonganEntity);
                            }
                        }

                        List<ItPersonilPositionEntity> itPersonil = null;
                        itPersonil = personilPositionDao.getPosisi(mutasi.getNip(), mutasi.getPositionLamaId());
                        if(itPersonil.size() > 0){
                            ItPersonilPositionEntity itPerson = new ItPersonilPositionEntity();
                            for(ItPersonilPositionEntity itPersonilPositionEntity: itPersonil){
                                itPersonilPositionEntity.setNip(mutasi.getNip());
                                itPersonilPositionEntity.setPjs(mutasi.getPjs());

                                // Sigit 2020-01-08, Penambahan flag digaji dan jenis pegawai
                                itPersonilPositionEntity.setJenisPegawai(mutasi.getJenisPegawaiId());
                                itPersonilPositionEntity.setFlagDigaji(mutasi.getFlagDigaji());
                                // END

                                itPersonilPositionEntity.setProfesiId(mutasi.getProfesiBaruId());
                                //tanggal aktif digunakan untuk mengisi kolom tanggal / tahun diangkat di biodata-riwayat kerja
                                itPersonilPositionEntity.setTanggalAktif(bean.getTanggalEfektif());

                                if("M".equalsIgnoreCase(mutasi.getStatus())) {
                                    itPersonilPositionEntity.setBranchId(mutasi.getBranchBaruId());
                                    itPersonilPositionEntity.setPositionId(mutasi.getPositionBaruId());
                                    itPersonilPositionEntity.setAction("U");
                                } else {
                                    itPersonilPositionEntity.setPositionId(mutasi.getPositionBaruId());
                                    itPersonilPositionEntity.setAction("U");
                                }

                                itPersonilPositionEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                itPersonilPositionEntity.setLastUpdate(bean.getLastUpdate());

                                try {
                                    personilPositionDao.updateAndSave(itPersonilPositionEntity);
                                } catch (HibernateException e) {
                                    logger.error("[MutasiBoImpl.saveMutasi] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
                                }
                            }
                        }
                    } else if ("L".equalsIgnoreCase(mutasi.getStatus())){

                        // Sigit 2020-01-10, matikan posisi lama;
                        PersonilPosition personilPositionLama = new PersonilPosition();
                        personilPositionLama.setNip(mutasi.getNip());
                        personilPositionLama.setPositionId(mutasi.getPositionLamaId());
                        personilPositionLama.setFlag("Y");

                        List<ItPersonilPositionEntity> personilPositionLamaEntities = getPersonilPositionEntity(personilPositionLama);
                        if (personilPositionLamaEntities != null && personilPositionLamaEntities.size() > 0){
                            for (ItPersonilPositionEntity personilPositionEntity : personilPositionLamaEntities){

                                // matikan posisi lama
                                personilPositionEntity.setFlag("N");
                                personilPositionEntity.setAction("U");
                                personilPositionEntity.setLastUpdate(mutasi.getLastUpdate());

                                try {
                                    personilPositionDao.updateAndSave(personilPositionEntity);
                                } catch (HibernateException e){
                                    logger.error("[MutasiBoImpl.saveMutasi] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem save data status lepas jabatan, please inform to your admin...," + e.getMessage());
                                }

                                // save history
                            }
                        }
                        // END

                        // jika update posisiId != null berarti ada posisi selain normal dan bisa, update yg tidak selain normal (pjs, plt, dll ..)
                        if (mutasi.getUpdatePosisiId() != null && !"".equalsIgnoreCase(mutasi.getUpdatePosisiId())){

                            // Sigit 2020-01-10, rubah posisi id yang lain menjadi yang utama / normal
                            PersonilPosition personilPositionBaru = new PersonilPosition();
                            personilPositionBaru.setNip(mutasi.getNip());
                            personilPositionBaru.setPositionId(mutasi.getUpdatePosisiId());
                            personilPositionBaru.setFlag("Y");

                            List<ItPersonilPositionEntity> personilPositionBaruEntities = getPersonilPositionEntity(personilPositionBaru);
                            if (personilPositionBaruEntities != null && personilPositionBaruEntities.size() > 0){
                                for (ItPersonilPositionEntity personilPositionEntity : personilPositionBaruEntities){

                                    // Update jenis pegawai utama / normal
                                    JenisPegawai jenisPegawai = new JenisPegawai();
                                    jenisPegawai.setFlagDefault("Y");
                                    jenisPegawai.setFlag("Y");
                                    List<ImHrisJenisPegawaiEntity> jenisPegawaiEntities = getJenisListPegawaiEntity(jenisPegawai);
                                    if (jenisPegawaiEntities != null && jenisPegawaiEntities.size() > 0) {
                                        ImHrisJenisPegawaiEntity jenisPegawaiEntity = jenisPegawaiEntities.get(0);
                                        personilPositionEntity.setJenisPegawai(jenisPegawaiEntity.getJenisPegawaiId());
                                    }
                                    // END

                                    personilPositionEntity.setFlagDigaji("Y");
                                    personilPositionEntity.setPositionId(mutasi.getUpdatePosisiId());
                                    personilPositionEntity.setLastUpdate(mutasi.getLastUpdate());

                                    try {
                                        personilPositionDao.updateAndSave(personilPositionEntity);
                                    } catch (HibernateException e){
                                        logger.error("[MutasiBoImpl.saveMutasi] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem save data status lepas jabatan, please inform to your admin...," + e.getMessage());
                                    }

                                    // save history
                                }
                            }
                            // END
                        }

                    } else if ("RS".equalsIgnoreCase(mutasi.getStatus())){

                        // jika mengundurkan diri
                        nonAktifAllPegawaiByCriteria(mutasi);

                    } else {

                        // Sigit 2020-01-08
                        // add jika ada jabatan baru. biasanya pjs / plt, Status Rangkap
                        ItPersonilPositionEntity personilPositionEntity = new ItPersonilPositionEntity();
                        personilPositionEntity.setPersonilPositionId(getNextSeqPersonilPositionId());
                        personilPositionEntity.setNip(mutasi.getNip());
                        personilPositionEntity.setBranchId(mutasi.getBranchBaruId());
                        personilPositionEntity.setProfesiId(mutasi.getProfesiBaruId());
                        personilPositionEntity.setPositionId(mutasi.getPositionBaruId());
                        personilPositionEntity.setJenisPegawai(mutasi.getJenisPegawaiId());
                        personilPositionEntity.setFlagDigaji(mutasi.getFlagDigaji());
                        personilPositionEntity.setFlag("Y");
                        personilPositionEntity.setAction("C");
                        personilPositionEntity.setCreatedDate(mutasi.getLastUpdate());
                        personilPositionEntity.setCreatedWho(mutasi.getLastUpdateWho());
                        personilPositionEntity.setLastUpdate(mutasi.getLastUpdate());
                        personilPositionEntity.setLastUpdateWho(mutasi.getLastUpdateWho());

                        try {
                            personilPositionDao.addAndSave(personilPositionEntity);
                        } catch (HibernateException e){
                            logger.error("[MutasiBoImpl.saveMutasi] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when add personil position, please inform to your admin...," + e.getMessage());
                        }
                    }

                    try {
                        mutasiDao.addAndSave(itMutasiEntity);
                        mutasiDocDao.addAndSave(itDoc);
                    } catch (HibernateException e) {
                        logger.error("[MutasiBoImpl.saveMutasi] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when add and save Mutasi, please inform to your admin...," + e.getMessage());
                    }

                    // save history jabatan
                    saveHistoryJabatan(mutasi, bean);
                }
                // loop END
            }
        }

        logger.info("[MutasiBoImpl.saveAdd] end process <<<");
    }

    private String getNextSeqPersonilPositionId(){
        String id = "";
        try {
            id = personilPositionDao.getNextPersonilPositionId();
        } catch (HibernateException e){
            logger.error("[MutasiBoImpl.getNextSeqPersonilPositionId] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data seq personil position, please inform to your admin...," + e.getMessage());
        }
        return id;
    }

    @Override
    public List<Mutasi> getByCriteria(Mutasi searchBean) throws GeneralBOException {
        List<Mutasi> listOfResult = new ArrayList();
        logger.info("[MutasiBoImpl.getByCriteria] start process >>>");

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getMutasiId() != null && !"".equalsIgnoreCase(searchBean.getMutasiId())) {
                hsCriteria.put("mutasi_id", searchBean.getMutasiId());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getStatus() != null && !"".equalsIgnoreCase(searchBean.getStatus())) {
                hsCriteria.put("status", searchBean.getStatus());
            }
            if (searchBean.getBranchLamaId() != null && !"".equalsIgnoreCase(searchBean.getBranchLamaId())) {
                hsCriteria.put("branch_lama_id", searchBean.getBranchLamaId());
            }
            if (searchBean.getDivisiLamaId() != null && !"".equalsIgnoreCase(searchBean.getDivisiLamaId())) {
                hsCriteria.put("divisi_lama_id", searchBean.getDivisiLamaId());
            }
            if (searchBean.getPositionLamaId() != null && !"".equalsIgnoreCase(searchBean.getPositionLamaId())) {
                hsCriteria.put("position_lama_id", searchBean.getPositionLamaId());
            }
            if (searchBean.getPositionBaruId() != null && !"".equalsIgnoreCase(searchBean.getPositionBaruId())) {
                hsCriteria.put("position_baru_id", searchBean.getPositionBaruId());
            }
            if (searchBean.getTanggalEfektif() != null) {

                hsCriteria.put("tanggal_efektif", searchBean.getTanggalEfektif());
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


            List<ItMutasiEntity> imMutasiEntity = null;
            ItMutasiDocEntity itMutasiDocEntities = null;
            try {

                imMutasiEntity = mutasiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[MutasiBoImpl.getSearchMutasiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imMutasiEntity != null){
                Mutasi returnMutasi;
                // Looping from dao to object and save in collection
                for(ItMutasiEntity itMutasiEntity : imMutasiEntity){
                    returnMutasi = new Mutasi();

                    itMutasiDocEntities = mutasiDocDao.getById("mutasiId", itMutasiEntity.getMutasiId());

                    if(itMutasiDocEntities != null){
                        returnMutasi.setFilePath(itMutasiDocEntities.getFileUpload());
                        if(itMutasiDocEntities.getFileUpload() != null){
                            returnMutasi.setSK1(true);
                        }
                    }else{
                        returnMutasi.setFilePath("");
                        returnMutasi.setSK1(false);
                    }

                    returnMutasi.setMutasiId(itMutasiEntity.getMutasiId());
                    returnMutasi.setNip(itMutasiEntity.getNip());
                    returnMutasi.setNama(itMutasiEntity.getImBiodataEntity().getNamaPegawai());
                    returnMutasi.setStTanggalEfektif(CommonUtil.convertTimestampToString(itMutasiEntity.getTanggalEfektif()));
                    returnMutasi.setBranchBaruId(itMutasiEntity.getBranchBaruId());
                    returnMutasi.setDivisiBaruId(itMutasiEntity.getDivisiBaruId());
                    returnMutasi.setPositionBaruId(itMutasiEntity.getPositionBaruId());

                    if(itMutasiEntity.getBranchBaruId() != null){
                        returnMutasi.setBranchBaruName(itMutasiEntity.getImBranchesBaru().getBranchName());
                    }
                    if(itMutasiEntity.getDivisiBaruId() != null){
                        if(!"".equalsIgnoreCase(itMutasiEntity.getDivisiBaruId())){
                            returnMutasi.setDivisiBaruName(itMutasiEntity.getImDepartmentEntityBaru().getDepartmentName());
                        }
                    }
                    if(itMutasiEntity.getPositionBaruId() != null){
                        returnMutasi.setPositionBaruName(itMutasiEntity.getImPositionBaru().getPositionName());
                    }

                    returnMutasi.setBranchLamaId(itMutasiEntity.getBranchLamaId());
                    returnMutasi.setDivisiLamaId(itMutasiEntity.getDivisiLamaId());
                    returnMutasi.setPositionLamaId(itMutasiEntity.getPositionLamaId());
                    returnMutasi.setBranchLamaName(itMutasiEntity.getImBranchesLama().getBranchName());
                    if(itMutasiEntity.getDivisiLamaId() != null){
                        returnMutasi.setDivisiLamaName(itMutasiEntity.getImDepartmentEntityLama().getDepartmentName());
                    }
                    if(itMutasiEntity.getPositionLamaId() != null){
                        if (!itMutasiEntity.getPositionLamaId().equalsIgnoreCase("")){
                            returnMutasi.setPositionLamaName(itMutasiEntity.getImPositionLama().getPositionName());
                        }
                    }


                    returnMutasi.setCreatedWho(itMutasiEntity.getCreatedWho());
                    returnMutasi.setCreatedDate(itMutasiEntity.getCreatedDate());
                    returnMutasi.setLastUpdate(itMutasiEntity.getLastUpdate());
                    returnMutasi.setAction(itMutasiEntity.getAction());
                    returnMutasi.setFlag(itMutasiEntity.getFlag());

                    ImHrisStatusMutasiEntity statusMutasiEntity = statusMutasiDao.getById("statusMutasiId",itMutasiEntity.getStatus());
                    if (statusMutasiEntity!= null){
                        returnMutasi.setStatusName(statusMutasiEntity.getStatusMutasiName());
                    }
                    listOfResult.add(returnMutasi);
                }
            }
        }
        logger.info("[MutasiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    public List<Mutasi> getComboMutasi(String nip) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Mutasi> listComboMutasi = new ArrayList();

        List<ItMutasiEntity> listSppd = null;
        try {
            listSppd = mutasiDao.getListMutasi(nip);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSppd != null) {
            for (ItMutasiEntity itMutasiEntity : listSppd) {
                Mutasi returnMutasi = new Mutasi();
                returnMutasi.setMutasiId(itMutasiEntity.getMutasiId());
                returnMutasi.setNip(itMutasiEntity.getNip());
                returnMutasi.setNama(itMutasiEntity.getImBiodataEntity().getNamaPegawai());
                returnMutasi.setStTanggalEfektif(CommonUtil.convertTimestampToString(itMutasiEntity.getTanggalEfektif()));
                returnMutasi.setBranchBaruId(itMutasiEntity.getBranchBaruId());
                returnMutasi.setDivisiBaruId(itMutasiEntity.getDivisiBaruId());
                returnMutasi.setPositionBaruId(itMutasiEntity.getPositionBaruId());

                returnMutasi.setBranchBaruName(itMutasiEntity.getImBranchesBaru().getBranchName());
                returnMutasi.setDivisiBaruName(itMutasiEntity.getImDepartmentEntityBaru().getDepartmentName());
                returnMutasi.setPositionBaruName(itMutasiEntity.getImPositionBaru().getPositionName());

                returnMutasi.setBranchLamaId(itMutasiEntity.getBranchLamaId());
                returnMutasi.setDivisiLamaId(itMutasiEntity.getDivisiLamaId());
                returnMutasi.setPositionLamaId(itMutasiEntity.getPositionLamaId());
                returnMutasi.setBranchLamaName(itMutasiEntity.getImBranchesLama().getBranchName());
                returnMutasi.setDivisiLamaName(itMutasiEntity.getImDepartmentEntityLama().getDepartmentName());
                returnMutasi.setPositionLamaName(itMutasiEntity.getImPositionLama().getPositionName());

                listComboMutasi.add(returnMutasi);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboMutasi;
    }

    public List<PersonilPosition> getAvailableJabatan(PersonilPosition searchBean) throws GeneralBOException {
        List<PersonilPosition> personilPositionList = new ArrayList();
        List<ItPersonilPositionEntity> itPersonilPositionEntities = null;

        itPersonilPositionEntities = personilPositionDao.getListPersonilPosition(searchBean.getBranchId(), searchBean.getDivisiId(), searchBean.getPositionId());
        if (itPersonilPositionEntities != null) {

            for (ItPersonilPositionEntity itPersonilPositionEntity : itPersonilPositionEntities) {
                PersonilPosition personilPosition = new PersonilPosition();
                personilPosition.setNip(itPersonilPositionEntity.getNip());
                personilPosition.setBranchName(itPersonilPositionEntity.getImBranches().getBranchName());
                personilPosition.setPersonName(itPersonilPositionEntity.getImBiodataEntity().getNamaPegawai());
                personilPosition.setPositionName(itPersonilPositionEntity.getImPosition().getPositionName());
                if(itPersonilPositionEntity.getImPosition().getDepartmentId() != null){
                    personilPosition.setDivisiName(itPersonilPositionEntity.getImPosition().getImDepartmentEntity().getDepartmentName());
                }
                personilPositionList.add(personilPosition);
            }
        }

        return personilPositionList;
    }

    @Override
    public List<Mutasi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Mutasi> getComboMutasiWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Mutasi> listComboMutasi = new ArrayList();

        return listComboMutasi;
    }

    @Override
    public List<MutasiDoc> getMutasiDoc(String mutasiId) throws GeneralBOException {
        ItMutasiDocEntity itMutasiDocEntity = mutasiDocDao.getById("mutasiId", mutasiId, "Y");
        List<MutasiDoc> mutasiDocList =  new ArrayList();
        if (itMutasiDocEntity != null) {
            MutasiDoc mutasiDoc = new MutasiDoc();
            mutasiDoc.setMutasiId(itMutasiDocEntity.getMutasiId());
            mutasiDoc.setFileType(itMutasiDocEntity.getFileType());
            mutasiDoc.setFileUpload(itMutasiDocEntity.getFileUpload());
            mutasiDoc.setDocMutasiId(itMutasiDocEntity.getDocMutasiId());
            mutasiDocList.add(mutasiDoc);
        }
        return mutasiDocList;
    }

    public MutasiDoc addMutasiDoc(MutasiDoc bean) throws GeneralBOException {
        logger.info("[MutasiBoImpl.addMutasiDoc] start process >>>");

        if (bean!=null) {

            ItMutasiDocEntity itMutasiDocEntity = mutasiDocDao.getById("mutasiId", bean.getMutasiId());
            if (itMutasiDocEntity != null) {
                if(bean.getFileUpload() != null){
                    itMutasiDocEntity.setFileUpload(bean.getFileUpload());
                }
                itMutasiDocEntity.setFlag(bean.getFlag());
                itMutasiDocEntity.setFileType(bean.getFileType());
                itMutasiDocEntity.setAction("U");
                itMutasiDocEntity.setCreatedWho(bean.getCreatedWho());
                itMutasiDocEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itMutasiDocEntity.setCreatedDate(bean.getCreatedDate());
                itMutasiDocEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    mutasiDocDao.updateAndSave(itMutasiDocEntity);
                } catch (HibernateException e) {
                    logger.error("[MutasiBoImpl.addMutasiDoc] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Department, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[MutasiBoImpl.addMutasiDoc] end process <<<");
        return null;
    }

    @Override
    public Mutasi getDataReportMutasi(String mutasiId) throws GeneralBOException {
        logger.info("[MutasiBoImpl.getDataReportMutasi] start process >>>");

        Mutasi resultMutasi = new Mutasi();
        List<Mutasi> mutasiList = null;

        try {
            mutasiList = mutasiDao.getDataReportMutasi(mutasiId);
        } catch (HibernateException e){
            logger.error("[MutasiBoImpl.getDataReportMutasi] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get data mutasi, please info to your admin..." + e.getMessage());
        }
        if(mutasiList != null){
            for(Mutasi mutasi : mutasiList){
                resultMutasi.setNama(mutasi.getNama());
                resultMutasi.setPositionLamaName(mutasi.getPositionLamaName());
                resultMutasi.setBranchLamaName(mutasi.getBranchLamaName());
                resultMutasi.setBranchBaruName(mutasi.getBranchBaruName());
                resultMutasi.setPositionBaruName(mutasi.getPositionBaruName());
                resultMutasi.setLevelBaruName(mutasi.getLevelBaruName());
                resultMutasi.setLevelBaru(mutasi.getLevelBaru());
                resultMutasi.setStTanggalEfektif(mutasi.getStTanggalEfektif());
            }
        }

        logger.info("[MutasiBoImpl.getDataReportMutasi] start process <<<");
        return resultMutasi;
    }

    @Override
    public String cekDataMutasiSys() throws GeneralBOException {
        String hasil = "berhasil";

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Mutasi> mutasiList = (List<Mutasi>) session.getAttribute("listOfMutasi");
        String flagCari = "N";

        String awalUnit = "";
        String awalPosisi = "";
        String lastPosition = "";
        String lastPositionName = "";
        String lastUnit = "";
        String lastStatus;

        if(mutasiList != null){
            int i = 1 ;
            for(Mutasi mutasi: mutasiList){
                flagCari = "N";
                if(i == 1){
                }
                awalUnit = mutasi.getBranchBaruId();
                awalPosisi = mutasi.getPositionBaruId();

                for(Mutasi mutasicari : mutasiList){
                    if(mutasi.getStatus().equalsIgnoreCase("M")){
                        if(mutasi.getPenggantiNip().equalsIgnoreCase("-")){
                            flagCari = "Y";
                        }else{
                            if(mutasi.getBranchBaruId().equalsIgnoreCase(mutasicari.getBranchLamaId()) && mutasi.getPenggantiNip().equalsIgnoreCase(mutasicari.getNip())) {
                                flagCari = "Y";
                                break;
                            }
                        }
                    }else{
                        flagCari = "Y";
                        break;
                    }
                }
                if(flagCari.equalsIgnoreCase("N")){
                    if(cekJabatanData(mutasi.getBranchBaruId(), "", mutasi.getPositionBaruId()) == false){
                        hasil = "Data Mutasi tidak dapat disimpan, karena masih ada jabatan " + mutasi.getPositionBaruName() + " yang tidak sesuai";
                        flagCari = "Y";
                        break;
                    }
                }

                if(mutasiList.size() == i){
                    if(mutasi.getStatus().equalsIgnoreCase("M")){
                        if(!mutasi.getBranchBaruId().equalsIgnoreCase(awalUnit) && !mutasi.getPositionBaruId().equalsIgnoreCase(awalPosisi)){
                            flagCari = "N";
                            if(cekJabatanData(mutasi.getBranchBaruId(), mutasi.getDivisiBaruId(), mutasi.getPositionBaruId()) == false){
                                hasil = "Data Mutasi tidak dapat disimpan, karena masih ada jabatan " + mutasi.getPositionBaruName() + " yang tidak sesuai";
                                break;
                            }
                        }
                    }
                }
                i++;
            }

            /*if(!lastPosition.equalsIgnoreCase("")){
                for(Mutasi mutasi:mutasiList){
                    lastPosition.equalsIgnoreCase(mutasi.getPositionBaruId());
                    flagCari = "Y";
                    break;
                }

                if(flagCari.equalsIgnoreCase("N")){
                    if(cekJabatanData(lastUnit, "", lastPosition) == false){
                        hasil = "Data Mutasi tidak dapat disimpan, karena masih ada jabatan " + lastPosition + " yang tidak sesuai";
                    }
                }
            }*/
        }else{
            hasil = "Data Masih Kosong!";
        }

        return hasil;
    }

    @Override
    public String getDirektur() throws GeneralBOException {
        String nama = "";
        nama = strukturJabatanDao.getNamaDirektur();
        return nama;
    }

    private boolean cekJabatanData(String branchId, String divisiId, String positionId){
        boolean hasil = false;
        List<ItPersonilPositionEntity> itposisi = null;

        itposisi = personilPositionDao.getListPersonilPosition(branchId, divisiId, positionId);
        if(itposisi.size() == 0){
            hasil = true;
        }else{
            hasil = false;
        }
        return hasil;
    }

    public BigDecimal getGajiPokok(String golonganId,String tahun){
        BigDecimal nilai=new BigDecimal(0);
        List<ImPayrollSkalaGajiEntity> gaji = new ArrayList<>();
        gaji = skalaGajiDao.getDataSkalaGajiSimRs(golonganId,tahun);
        if (gaji!=null){
            for (ImPayrollSkalaGajiEntity gajiEntity: gaji){
                nilai = gajiEntity.getNilai();
            }
        }
        return  nilai;
    }

    private List<ItPersonilPositionEntity> getPersonilPositionEntity(PersonilPosition bean){
        logger.info("[MutasiBoImpl.getPersonilPositionEntity] START >>>");

        List<ItPersonilPositionEntity> personilPositionEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getPositionId() != null && !"".equalsIgnoreCase(bean.getPositionId()))
            hsCriteria.put("position_id", bean.getPositionId());
        if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip()))
            hsCriteria.put("nip", bean.getNip());
        if (bean.getPersonilPositionId() != null && !"".equalsIgnoreCase(bean.getPersonilPositionId()))
            hsCriteria.put("personil_position_id", bean.getPersonilPositionId());
        if (bean.getJenisPegawai() != null && !"".equalsIgnoreCase(bean.getJenisPegawai()))
            hsCriteria.put("jenis_pegawai", bean.getJenisPegawai());
        if (bean.getFlagDigaji() != null && !"".equalsIgnoreCase(bean.getFlagDigaji()))
            hsCriteria.put("flag_digaji", bean.getFlagDigaji());
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId()))
            hsCriteria.put("branch_id", bean.getBranchId());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());

        try {
            personilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[MutasiBoImpl.getPersonilPositionEntity] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get personil position entity, please info to your admin..." + e.getMessage());
        }

        logger.info("[MutasiBoImpl.getPersonilPositionEntity] END <<<");
        return personilPositionEntities;
    }

    private List<ImKeluargaEntity> getListKeluargaEntity(Keluarga bean){
        logger.info("[MutasiBoImpl.getListKeluargaEntity] START >>>");

        List<ImKeluargaEntity> keluargaEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getKeluargaId() != null && !"".equalsIgnoreCase(bean.getKeluargaId()))
            hsCriteria.put("keluarga_id", bean.getKeluargaId());
        if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip()))
            hsCriteria.put("nip", bean.getNip());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());

        try {
            keluargaEntities = keluargaDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[MutasiBoImpl.getListKeluargaEntity] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get keluarga entity, please info to your admin..." + e.getMessage());
        }

        logger.info("[MutasiBoImpl.getListKeluargaEntity] END <<<");
        return keluargaEntities;
    }

    private List<ImStudyEntity> getListStudyEntity(Study bean){
        logger.info("[MutasiBoImpl.getListStudyEntity] START >>>");

        List<ImStudyEntity> studyEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getStudyId() != null && !"".equalsIgnoreCase(bean.getStudyId()))
            hsCriteria.put("study_id", bean.getStudyId());
        if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip()))
            hsCriteria.put("nip", bean.getNip());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());

        try {
            studyEntities = studyDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[MutasiBoImpl.getListStudyEntity] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get list study entity, please info to your admin..." + e.getMessage());
        }

        logger.info("[MutasiBoImpl.getListStudyEntity] END <<<");
        return studyEntities;
    }

    private List<ItTunjLainPegawaiEntity> getListTunjanganEntity(TunjLainPegawai bean){
        logger.info("[MutasiBoImpl.getListTunjanganEntity] START >>>");

        List<ItTunjLainPegawaiEntity> tunjLainPegawaiEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip()))
            hsCriteria.put("nip", bean.getNip());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());

        try {
            tunjLainPegawaiEntities = tunjLainPegawaiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[MutasiBoImpl.getListTunjanganEntity] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get tunjangan lain entity, please info to your admin..." + e.getMessage());
        }

        logger.info("[MutasiBoImpl.getListTunjanganEntity] END <<<");
        return tunjLainPegawaiEntities;
    }

    private void nonAktifAllPegawaiByCriteria(Mutasi mutasi){
        logger.info("[MutasiBoImpl.nonAktifAllPegawaiByCriteria] START >>>");

        ImBiodataEntity biodataEntity = new ImBiodataEntity();
        try {
            biodataEntity = biodataDao.getById("nip", mutasi.getNip());
        } catch (HibernateException e){
            logger.error("[MutasiBoImpl.nonAktifAllPegawaiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when search data biodata, please inform to your admin...," + e.getMessage());
        }
        if (biodataEntity != null && biodataEntity.getNip() != null){

            // update flag N pada biodate berdasarkan nip
            biodataEntity.setFlag("N");
            if (mutasi.getStTanggalKeluar() != null && !"".equalsIgnoreCase(mutasi.getStTanggalKeluar()))
                biodataEntity.setTanggalKeluar(CommonUtil.convertStringToDate2(mutasi.getStTanggalKeluar()));
            if (mutasi.getKetResign() != null && !"".equalsIgnoreCase(mutasi.getKetResign()))
                biodataEntity.setKeterangan("Telah "+mutasi.getKetResign()+" Pada Tanggal : "+ mutasi.getStTanggalKeluar());

            biodataEntity.setAction("U");
            biodataEntity.setLastUpdate(mutasi.getLastUpdate());
            biodataEntity.setLastUpdateWho(mutasi.getLastUpdateWho());
            biodataEntity.setKeterangan("Telah nonaktif pada tanggal " + biodataEntity.getTanggalKeluar());

            try {
                biodataDao.updateAndSave(biodataEntity);
            } catch (HibernateException e){
                logger.error("[MutasiBoImpl.nonAktifAllPegawaiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when update data biodata, please inform to your admin...," + e.getMessage());
            }
            // END


            // Update flag N pada semua position berdasarkan nip
            PersonilPosition personilPosition = new PersonilPosition();
            personilPosition.setNip(mutasi.getNip());
            personilPosition.setFlag("Y");

            List<ItPersonilPositionEntity> personilPositionEntities = getPersonilPositionEntity(personilPosition);

            if (personilPositionEntities.size() > 0){
                for (ItPersonilPositionEntity personilPositionEntity : personilPositionEntities){

                    personilPositionEntity.setFlag("N");
                    personilPositionEntity.setAction("U");
                    personilPositionEntity.setLastUpdate(mutasi.getLastUpdate());
                    personilPositionEntity.setLastUpdateWho(mutasi.getLastUpdateWho());

                    try {
                        personilPositionDao.updateAndSave(personilPositionEntity);
                    } catch (HibernateException e){
                        logger.error("[MutasiBoImpl.nonAktifAllPegawaiByCriteria] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when update data personil position, please inform to your admin...," + e.getMessage());
                    }
                }
            }
            // END

            // Update flag N pada semua keluarga berdasarkan nip
            Keluarga keluarga = new Keluarga();
            keluarga.setNip(mutasi.getNip());
            keluarga.setFlag("Y");

            List<ImKeluargaEntity> keluargaEntities = getListKeluargaEntity(keluarga);

            if (keluargaEntities.size() > 0){
                for (ImKeluargaEntity keluargaEntity : keluargaEntities){

                    keluargaEntity.setFlag("Y");
                    keluargaEntity.setAction("U");
                    keluargaEntity.setLastUpdate(mutasi.getLastUpdate());
                    keluargaEntity.setLastUpdateWho(mutasi.getLastUpdateWho());

                    try {
                        keluargaDao.updateAndSave(keluargaEntity);
                    } catch (HibernateException e){
                        logger.error("[MutasiBoImpl.nonAktifAllPegawaiByCriteria] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when update data keluarga, please inform to your admin...," + e.getMessage());
                    }
                }
            }
            // END

            // Update flag N pada semua study berdasarkan nip
            Study study = new Study();
            study.setNip(mutasi.getNip());
            study.setFlag("Y");

            List<ImStudyEntity> studyEntities = getListStudyEntity(study);

            if (studyEntities.size() > 0){
                for (ImStudyEntity studyEntity : studyEntities){

                    studyEntity.setFlag("N");
                    studyEntity.setAction("U");
                    studyEntity.setLastUpdate(mutasi.getLastUpdate());
                    studyEntity.setLastUpdateWho(mutasi.getLastUpdateWho());

                    try {
                        studyDao.updateAndSave(studyEntity);
                    } catch (HibernateException e){
                        logger.error("[MutasiBoImpl.nonAktifAllPegawaiByCriteria] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when update data study, please inform to your admin...," + e.getMessage());
                    }
                }
            }
            // END

            // Update flag N pada semua tunjangan lain berdasarkan nip
            TunjLainPegawai tunjLainPegawai = new TunjLainPegawai();
            tunjLainPegawai.setNip(mutasi.getNip());
            tunjLainPegawai.setFlag("Y");

            List<ItTunjLainPegawaiEntity> tunjLainPegawaiEntities = getListTunjanganEntity(tunjLainPegawai);

            if (tunjLainPegawaiEntities.size() > 0){
                for (ItTunjLainPegawaiEntity tunjLainPegawaiEntity : tunjLainPegawaiEntities){

                    tunjLainPegawaiEntity.setFlag("N");
                    tunjLainPegawaiEntity.setAction("U");
                    tunjLainPegawaiEntity.setLastUpdate(mutasi.getLastUpdate());
                    tunjLainPegawaiEntity.setLastUpdateWho(mutasi.getLastUpdateWho());

                    try {
                        tunjLainPegawaiDao.updateAndSave(tunjLainPegawaiEntity);
                    } catch (HibernateException e){
                        logger.error("[MutasiBoImpl.nonAktifAllPegawaiByCriteria] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when update data study, please inform to your admin...," + e.getMessage());
                    }
                }
            }
            // END
        }

        logger.info("[MutasiBoImpl.nonAktifAllPegawaiByCriteria] END <<<");
    }

    private List<ImHrisJenisPegawaiEntity> getJenisListPegawaiEntity(JenisPegawai bean){
        logger.info("[MutasiBoImpl.getJenisListPegawaiEntity] START >>>");

        List<ImHrisJenisPegawaiEntity> jenisPegawaiEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getJenisPegawaiId() != null && !"".equalsIgnoreCase(bean.getJenisPegawaiId()))
            hsCriteria.put("jenis_pegawai_id", bean.getJenisPegawaiId());
        if (bean.getFlagDefault() != null && !"".equalsIgnoreCase(bean.getFlagDefault()))
            hsCriteria.put("flag_default", bean.getFlagDefault());
        if (bean.getFlag() !=  null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());

        try {
            jenisPegawaiEntities = jenisPegawaiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[MutasiBoImpl.getJenisListPegawaiEntity] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when search jenis pegawai entity, please inform to your admin...," + e.getMessage());
        }

        logger.info("[MutasiBoImpl.getJenisListPegawaiEntity] END <<<");
        return jenisPegawaiEntities;
    }

    @Override
    public List<Position> getListOtherPosition(String positionId, String nip) {
        logger.info("[MutasiBoImpl.getListOtherPosition] START >>>");

        List<Position> positions = new ArrayList<>();
        try {
            positions = mutasiDao.getListOtherPosition(positionId, nip);
        } catch (HibernateException e){
            logger.error("[MutasiBoImpl.getListOtherPosition] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when search list other position, please inform to your admin...," + e.getMessage());
        }

        logger.info("[MutasiBoImpl.getListOtherPosition] END <<<");
        return positions;
    }

    @Override
    public Boolean checkJenisPegawaiDefault(String nip, String positionId) {
        logger.info("[MutasiBoImpl.checkJenisPegawaiDefault] START >>>");

        Boolean found = false;
        try {
            found = mutasiDao.checkJenisPegawaiIsDefaultWithNip(nip, positionId);
        } catch (HibernateException e) {
            logger.error("[MutasiBoImpl.checkJenisPegawaiDefault] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when check is jenis pegawai default, please inform to your admin...," + e.getMessage());
        }

        logger.info("[MutasiBoImpl.checkJenisPegawaiDefault] END <<<");
        return found;
    }

    @Override
    public Boolean checkPositionByJenisPegawai(String nip, String jenisPegawai) {
        logger.info("[MutasiBoImpl.checkPositionByJenisPegawai] START >>>");

        Boolean found = false;
        try {
            found = mutasiDao.checkPositionUtamaAktif(nip, jenisPegawai);
        } catch (HibernateException e) {
            logger.error("[MutasiBoImpl.checkPositionByJenisPegawai] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when check is jenis pegawai default, please inform to your admin...," + e.getMessage());
        }

        logger.info("[MutasiBoImpl.checkPositionByJenisPegawai] END <<<");
        return found;
    }

    private void saveHistoryJabatan(Mutasi mutasi, Mutasi bean){
        logger.info("[MutasiBoImpl.saveHistoryJabatan] START >>>");

        //update tanggal akhir jabatan lama di history jabatan pegawai
        String historiJabatanId;
        ImtHrisHistoryJabatanPegawaiEntity pengalamanLama = null;
        try{
            historiJabatanId = mutasiDao.getHistoryJabatanIdLama(mutasi.getNip(), mutasi.getPositionLamaId());
            if (historiJabatanId != null && !"".equalsIgnoreCase(historiJabatanId)){
                pengalamanLama = historyJabatanPegawaiDao.getById("historyJabatanId", historiJabatanId);
                pengalamanLama.setTanggalKeluar(bean.getStTanggalEfektif());
                pengalamanLama.setJabatanFlag("N");
                historyJabatanPegawaiDao.updateAndSave(pengalamanLama);
            }


        } catch (HibernateException e) {
            logger.error("[PengalamanKerjaBoImpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
        }

        //variable untuk proses update dan insert ke history jabatan
        String golonganId,pengalamanId, branchName, positionname, divisiName, golonganName, tipePegawaiName, tanggalAktif, bidangId, bidangName, stTanggalMasuk;

        //save jabatan baru ke history jabatan pegawai
        ImtHrisHistoryJabatanPegawaiEntity historyJabatanPegawai = new ImtHrisHistoryJabatanPegawaiEntity();
        historyJabatanPegawai.setNip(mutasi.getNip());
        historyJabatanPegawai.setBranchId(mutasi.getBranchBaruId());
        historyJabatanPegawai.setDivisiId(mutasi.getDivisiBaruId());
        historyJabatanPegawai.setBidangId(mutasi.getDivisiBaruId());
        historyJabatanPegawai.setBidangName(mutasi.getDivisiBaruName());
        historyJabatanPegawai.setPositionId(mutasi.getPositionBaruId());
        historyJabatanPegawai.setProfesiId(mutasi.getProfesiBaruId());
        historyJabatanPegawai.setTanggalSkMutasi(CommonUtil.convertStringToDate(bean.getStTanggalEfektif()));
        historyJabatanPegawai.setPoint("0");
        historyJabatanPegawai.setPointLebih("0");
        historyJabatanPegawai.setNilaiSmk(BigDecimal.valueOf(0));
        historyJabatanPegawai.setTahun(bean.getStTahun());
        historyJabatanPegawai.setGradeSmk("-");
        if ("RS".equalsIgnoreCase(mutasi.getStatus())){
            historyJabatanPegawai.setTanggalKeluar(mutasi.getStTanggalKeluar());
        }

        //update reza (02-04-2020) penambahan flagmutasi di history jabatan
        historyJabatanPegawai.setJabatanFlag("Y");
        historyJabatanPegawai.setMutasiFlag("Y");

        try {
            golonganId = mutasiDao.getGolonganId(mutasi.getNip());
            historyJabatanPegawai.setGolonganId(golonganId);
        }catch (HibernateException e) {
            logger.error("[MutasiBoImpl.saveMutasi] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
        }

        historyJabatanPegawai.setFlag(bean.getFlag());
        historyJabatanPegawai.setAction(bean.getAction());
        historyJabatanPegawai.setCreatedWho(bean.getCreatedWho());
        historyJabatanPegawai.setLastUpdateWho(bean.getLastUpdateWho());
        historyJabatanPegawai.setCreatedDate(bean.getCreatedDate());
        historyJabatanPegawai.setLastUpdate(bean.getLastUpdate());
        historyJabatanPegawai.setJenisPegawai(mutasi.getJenisPegawaiId());
        historyJabatanPegawai.setFlagDigaji(mutasi.getFlagDigaji());

        try {
            // Generating ID, get from postgre sequence
            pengalamanId = historyJabatanPegawaiDao.getNextPersonilPositionId();
            historyJabatanPegawai.setHistoryJabatanId(pengalamanId);

            //mengambil branch name, position name, divisi name, golongan name, tipe pegawai name
            branchName = historyJabatanPegawaiDao.getBranchById(mutasi.getBranchBaruId());
            historyJabatanPegawai.setBranchName(branchName);
            positionname = historyJabatanPegawaiDao.getPositionById(mutasi.getPositionBaruId());
            historyJabatanPegawai.setPositionName(positionname);
            divisiName = historyJabatanPegawaiDao.getDivisiById(mutasi.getDivisiBaruId());
            historyJabatanPegawai.setDivisiName(divisiName);
            golonganName = historyJabatanPegawaiDao.getGolonganById(golonganId);
            historyJabatanPegawai.setGolonganName(golonganName);
            historyJabatanPegawai.setTanggal(bean.getStTanggalEfektif());

            List<ImBiodataEntity> imBiodataEntitys;
            imBiodataEntitys = biodataDao.findBiodataLikeNip(mutasi.getNip());
            if (imBiodataEntitys != null){
                for (ImBiodataEntity imBiodataEntity : imBiodataEntitys) {
                    tipePegawaiName = historyJabatanPegawaiDao.getTipePegawaiById(imBiodataEntity.getTipePegawai());
                    historyJabatanPegawai.setTipePegawaiName(tipePegawaiName);
                }
            }

            List<HistoryJabatanPegawai> historyJabatan = new ArrayList<>();
            historyJabatan = historyJabatanPegawaiDao.geyBagianByPositionId(mutasi.getPositionLamaId());
            if (historyJabatan.size() >0){
                for (HistoryJabatanPegawai result: historyJabatan){
                    historyJabatanPegawai.setBagianId(result.getBagianId());
                    historyJabatanPegawai.setBagianName(result.getBagianName());
                }
            }

            try{
                historyJabatanPegawaiDao.addAndSave(historyJabatanPegawai);
            }catch (HibernateException e) {
                logger.error("[MutasiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence PengalamanKerjaId id, please info to your admin..." + e.getMessage());
            }
        } catch (HibernateException e) {
            logger.error("[MutasiBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence PengalamanKerjaId id, please info to your admin..." + e.getMessage());
        }

        logger.info("[MutasiBoImpl.saveHistoryJabatan] END <<<");
    }
}