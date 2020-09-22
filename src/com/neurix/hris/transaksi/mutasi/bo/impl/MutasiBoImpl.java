package com.neurix.hris.transaksi.mutasi.bo.impl;

import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.dao.PelatihanJabatanUserDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.biodata.model.ImtPelatihanJabatanUserEntity;
import com.neurix.hris.master.biodata.model.PelatihanJabatanUser;
import com.neurix.hris.master.kualifikasiCalonPejabat.dao.KualifikasiCalonPejabatDao;
import com.neurix.hris.master.kualifikasiCalonPejabat.model.ImHrisKualifikasiCalonPejabatEntity;
import com.neurix.hris.master.kualifikasiCalonPejabat.model.KualifikasiCalonPejabat;
import com.neurix.hris.master.payrollSkalaGaji.dao.PayrollSkalaGajiDao;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.pelatihanJabatan.model.PelatihanJabatan;
import com.neurix.hris.master.sertifikat.dao.SertifikatDao;
import com.neurix.hris.master.sertifikat.model.ImSertifikatEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.master.study.dao.StudyDao;
import com.neurix.hris.master.study.model.ImStudyEntity;
import com.neurix.hris.master.study.model.Study;
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
import com.neurix.hris.transaksi.personilPosition.dao.HistoryJabatanPegawaiDao;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.HistoryJabatanPegawai;
import com.neurix.hris.transaksi.personilPosition.model.ImtHrisHistoryJabatanPegawaiEntity;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.smk.dao.SmkHistoryGolonganDao;
import com.neurix.hris.transaksi.smk.model.ImtHistorySmkGolonganEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
//        String status1 ="";
        logger.info("[MutasiBoImpl.saveAdd] start process >>>");
        List<ItCutiPegawaiEntity> cutiPegawaiEntityList = new ArrayList<>();
        List<LemburEntity> lemburEntityList = new ArrayList<>();
        List<IjinKeluarEntity> ijinKeluarEntityList = new ArrayList<>();

//        ItCutiPegawaiEntity cutiPegawaiEntity = cutiPegawaiEntityList.get(0);
//        Calendar c1 = Calendar.getInstance();
//        java.util.Date tanggalAwalCuti = new java.util.Date(cutiPegawaiEntity.getTanggalDari().getTime());
//        c1.setTime(tanggalAwalCuti);
//        int date1 = c1.get(Calendar.DATE);
//
//        Calendar d1 = Calendar.getInstance();
//        java.util.Date tanggalAkhirCuti = new java.util.Date(cutiPegawaiEntity.getTanggalSelesai().getTime());
//        d1.setTime(tanggalAkhirCuti);
//        int dateAkhir1 = d1.get(Calendar.DATE);
//
//        LemburEntity lemburEntity = lemburEntityList.get(0);
//        Calendar c2 = Calendar.getInstance();
//        java.util.Date tanggalAwalLembur = new java.util.Date(lemburEntity.getTanggalAwal().getTime());
//        c2.setTime(tanggalAwalLembur);
//        int date2 = c2.get(Calendar.DATE);
//
//        Calendar d2 = Calendar.getInstance();
//        java.util.Date tanggalAkhirLembur = new java.util.Date(lemburEntity.getTanggalAkhir().getTime());
//        d2.setTime(tanggalAkhirLembur);
//        int dateAkhir2 = d2.get(Calendar.DATE);
//
//        IjinKeluarEntity ijinKeluarEntity = ijinKeluarEntityList.get(0);
//        Calendar c3 = Calendar.getInstance();
//        java.util.Date tanggalAwalDispensasi = new java.util.Date(ijinKeluarEntity.getTanggalAwal().getTime());
//        c3.setTime(tanggalAwalDispensasi);
//        int date3 = c3.get(Calendar.DATE);
//
//        Calendar d3 = Calendar.getInstance();
//        java.util.Date tanggalAkhirDispensasi = new java.util.Date(ijinKeluarEntity.getTanggalAkhir().getTime());
//        d3.setTime(tanggalAkhirDispensasi);
//        int dateAkhir3 = d3.get(Calendar.DATE);

        //validasi jika menggantikan maka yang digantikan harus juga move
        for (Mutasi dataMutasi : mutasiList){
            if (("M").equalsIgnoreCase(dataMutasi.getStatus())){
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
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }else if(lemburEntityList.size() > 0){
                    String status = "Maaf masih ada Lembur yang menggantung di tanggal ini.";
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }else if(ijinKeluarEntityList.size() > 0){
                    String status = "Maaf masih ada Dispensasi yang menggantung di tanggal ini.";
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }

                if (!("-").equalsIgnoreCase(dataMutasi.getPenggantiNip())){
                    boolean adaPengganti = false;
                    for (Mutasi cekData : mutasiList){
                        if (cekData.getNip().equalsIgnoreCase(dataMutasi.getPenggantiNip())){
                            adaPengganti=true;
                            break;
                        }
                    }
                    if (!adaPengganti){
                        String status ="ERROR : "+dataMutasi.getPenggantiNama()+" harus dimutasi";
                        logger.error("[PengalamanKerjaBoImpl.save mutasi] "+ status);
                        throw new GeneralBOException(status);
                    }
                }

                if (("MT").equalsIgnoreCase(dataMutasi.getTipeMutasi())){
                    if (dataMutasi.getBranchBaruId().equalsIgnoreCase(dataMutasi.getBranchLamaId())){
                        String status ="ERROR : "+dataMutasi.getNama()+" , gunakan rotasi jika unitnya sama";
                        logger.error("[PengalamanKerjaBoImpl.save mutasi] "+ status);
                        throw new GeneralBOException(status);
                    }
                }else if (("RT").equalsIgnoreCase(dataMutasi.getTipeMutasi())){
                    if (!dataMutasi.getBranchBaruId().equalsIgnoreCase(dataMutasi.getBranchLamaId())){
                        String status ="ERROR : "+dataMutasi.getNama()+" , gunakan Mutasi jika unitnya berbeda";
                        logger.error("[PengalamanKerjaBoImpl.save mutasi] "+ status);
                        throw new GeneralBOException(status);
                    }
                }
            }
            if (("R").equalsIgnoreCase(dataMutasi.getStatus())){
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
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }else if(lemburEntityList.size() > 0){
                    String status = "Maaf masih ada Lembur yang menggantung di tanggal ini.";
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }else if(ijinKeluarEntityList.size() > 0){
                    String status = "Maaf masih ada Dispensasi yang menggantung di tanggal ini.";
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }
            }
            if (("P").equalsIgnoreCase(dataMutasi.getStatus())){
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
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }else if(lemburEntityList.size() > 0){
                    String status = "Maaf masih ada Lembur yang menggantung di tanggal ini.";
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }else if(ijinKeluarEntityList.size() > 0){
                    String status = "Maaf masih ada Dispensasi yang menggantung di tanggal ini.";
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }
            }
            if (("MH").equalsIgnoreCase(dataMutasi.getStatus())){
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
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }else if(lemburEntityList.size() > 0){
                    String status = "Maaf masih ada Lembur yang menggantung di tanggal ini.";
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }else if(ijinKeluarEntityList.size() > 0){
                    String status = "Maaf masih ada Dispensasi yang menggantung di tanggal ini.";
                    logger.error("[MutasiBoImpl.saveAdd] Error :, " + status);
                    throw new GeneralBOException(status);
                }
            }
        }

        //validasi jika mutasi dan rotasi



        if (bean!=null) {
            if(mutasiList != null){
                for (Mutasi mutasi: mutasiList) {
                    //mendapat profesi Id;
                    String profesiId="";
                    List<ItPersonilPositionEntity> posisiPegawai = personilPositionDao.getListNip(mutasi.getNip());
                    for (ItPersonilPositionEntity posisi : posisiPegawai ){
                        profesiId=posisi.getProfesiId();
                    }

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

                    if(mutasi.getStatus().equalsIgnoreCase("M")){
                        itMutasiEntity.setBranchBaruId(mutasi.getBranchBaruId());
                        itMutasiEntity.setDivisiBaruId(mutasi.getDivisiBaruId());
                        itMutasiEntity.setPositionBaruId(mutasi.getPositionBaruId());
                        itMutasiEntity.setLevelBaru(mutasi.getLevelBaru());
                        itMutasiEntity.setLevelBaruName(mutasi.getLevelBaruName());
                    }
                    itMutasiEntity.setPjs(mutasi.getPjs());

                    itMutasiEntity.setFlag(bean.getFlag());
                    itMutasiEntity.setTanggalEfektif(bean.getTanggalEfektif());
                    itMutasiEntity.setAction(bean.getAction());
                    itMutasiEntity.setCreatedWho(bean.getCreatedWho());
                    itMutasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    itMutasiEntity.setCreatedDate(bean.getCreatedDate());
                    itMutasiEntity.setLastUpdate(bean.getLastUpdate());


                    itDoc.setDocMutasiId(mutasiDocDao.getNextId());
                    itDoc.setMutasiId(mutasiId);
                    itDoc.setFlag(bean.getFlag());
                    itDoc.setAction(bean.getAction());
                    itDoc.setCreatedWho(bean.getCreatedWho());
                    itDoc.setLastUpdateWho(bean.getLastUpdateWho());
                    itDoc.setCreatedDate(bean.getCreatedDate());
                    itDoc.setLastUpdate(bean.getLastUpdate());

                    //update tanggal akhir jabatan lama di history jabatan pegawai
                    String HistoryJabatanId;
                    ImtHrisHistoryJabatanPegawaiEntity pengalamanLama = null;
                    try{
                        HistoryJabatanId = mutasiDao.getHistoryJabatanIdLama(mutasi.getNip());
                        if (HistoryJabatanId!=null){
                            if (!HistoryJabatanId.equalsIgnoreCase("")){
                                pengalamanLama = historyJabatanPegawaiDao.getById("historyJabatanId", HistoryJabatanId);
                                pengalamanLama.setTanggalKeluar(bean.getStTanggalEfektif());
                                pengalamanLama.setJabatanFlag("N");
                                historyJabatanPegawaiDao.updateAndSave(pengalamanLama);
                            }else{
                                String status = "ERROR : history jabatan terakhir tidak ditemukan ";
                                logger.error("[PengalamanKerjaBoImpl.saveEdit] "+ status);
                                throw new GeneralBOException(status);
                            }
                        }else{
                            String status = "ERROR : history jabatan terakhir tidak ditemukan ";
                            logger.error("[PengalamanKerjaBoImpl.saveEdit] "+ status);
                            throw new GeneralBOException(status);
                        }

                    }catch (HibernateException e) {
                        logger.error("[PengalamanKerjaBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
                    }

                    String tgl[] = CommonUtil.convertTimestampToString(bean.getCreatedDate()).split("-");
                    if (("M").equalsIgnoreCase(mutasi.getStatus())){
                        //variable untuk proses update dan insert ke history jabatan
                        String golonganId,pengalamanId, branchName, positionname, divisiName, golonganName, tipePegawaiName, tanggalAktif, bidangId, bidangName, stTanggalMasuk;
                        String[] tanggalMasuk;

                        //save jabatan baru ke history jabatan pegawai
                        ImtHrisHistoryJabatanPegawaiEntity historyJabatanPegawai = new ImtHrisHistoryJabatanPegawaiEntity();
                        historyJabatanPegawai.setNip(mutasi.getNip());
                        historyJabatanPegawai.setBranchId(mutasi.getBranchBaruId());
                        historyJabatanPegawai.setDivisiId(mutasi.getDivisiBaruId());
                        historyJabatanPegawai.setBidangId(mutasi.getDivisiBaruId());
                        historyJabatanPegawai.setBidangName(mutasi.getDivisiBaruName());
                        historyJabatanPegawai.setPositionId(mutasi.getPositionBaruId());
//                        historyJabatanPegawai.setProfesiId(profesiId);
                        historyJabatanPegawai.setProfesiId(mutasi.getProfesiBaruId());
//                    historyJabatanPegawai.setTanggalKeluar(CommonUtil.convertTimestampToString(bean.getTanggalEfektif()));
                        historyJabatanPegawai.setTanggalSkMutasi(CommonUtil.convertStringToDate(bean.getStTanggalEfektif()));
                        historyJabatanPegawai.setPoint("0");
                        historyJabatanPegawai.setPointLebih("0");
                        historyJabatanPegawai.setNilaiSmk(BigDecimal.valueOf(0));
                        historyJabatanPegawai.setTahun(tgl[2]);
                        historyJabatanPegawai.setGradeSmk("-");

                        //update reza (02-04-2020) penambahan flagmutasi di history jabatan
                        historyJabatanPegawai.setJabatanFlag("Y");
                        historyJabatanPegawai.setMutasiFlag("Y");

                        //isi pjs flag
                        if(mutasi.getPjs()!=null){
                            if (!("").equalsIgnoreCase(mutasi.getPjs())){
                                historyJabatanPegawai.setPjsFlag(mutasi.getPjs());
                            }
                            else {
                                historyJabatanPegawai.setPjsFlag("N");
                            }
                        }
                        else {
                            historyJabatanPegawai.setPjsFlag("N");
                        }

                        try {
                            golonganId = mutasiDao.getGolonganId(mutasi.getNip());
                            historyJabatanPegawai.setGolonganId(golonganId);
                        }catch (HibernateException e) {
                            logger.error("[MutasiBoImpl.saveDelete] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
                        }

                        historyJabatanPegawai.setFlag(bean.getFlag());
                        historyJabatanPegawai.setAction(bean.getAction());
                        historyJabatanPegawai.setCreatedWho(bean.getCreatedWho());
                        historyJabatanPegawai.setLastUpdateWho(bean.getLastUpdateWho());
                        historyJabatanPegawai.setCreatedDate(bean.getCreatedDate());
                        historyJabatanPegawai.setLastUpdate(bean.getLastUpdate());

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

                                    if (imBiodataEntity.getTipePegawai().equalsIgnoreCase("TP01")){
                                        historyJabatanPegawai.setGolonganId(mutasi.getLevelBaru());
                                        golonganName = historyJabatanPegawaiDao.getGolonganById(mutasi.getLevelBaru());
                                        historyJabatanPegawai.setGolonganName(golonganName);
                                        historyJabatanPegawai.setTipePegawaiId(imBiodataEntity.getTipePegawai());
                                        tipePegawaiName = historyJabatanPegawaiDao.getTipePegawaiById(imBiodataEntity.getTipePegawai());
                                        historyJabatanPegawai.setTipePegawaiName(tipePegawaiName);
                                    }else {
                                        historyJabatanPegawai.setGolonganId(mutasi.getLevelBaru());
                                        golonganName = historyJabatanPegawaiDao.getGolonganById(mutasi.getLevelBaru());
                                        historyJabatanPegawai.setGolonganName(golonganName);
                                        historyJabatanPegawai.setTipePegawaiId("TP01");
                                        tipePegawaiName = historyJabatanPegawaiDao.getTipePegawaiById("TP01");
                                        historyJabatanPegawai.setTipePegawaiName(tipePegawaiName);
                                    }
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
                                logger.error("[PengalamanKerjaBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when getting sequence PengalamanKerjaId id, please info to your admin..." + e.getMessage());
                            }
                        } catch (HibernateException e) {
                            logger.error("[PengalamanKerjaBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when getting sequence PengalamanKerjaId id, please info to your admin..." + e.getMessage());
                        }
                    }
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

                    // biodata pegawai
                    ImBiodataEntity imBiodataEntity = biodataDao.getById("nip", mutasi.getNip());

                    List<ItPersonilPositionEntity> itPersonil = null;
                    itPersonil = personilPositionDao.getPosisi(mutasi.getNip(), mutasi.getPositionLamaId());
                    if(itPersonil.size() > 0){
                        ItPersonilPositionEntity itPerson = new ItPersonilPositionEntity();
                        for(ItPersonilPositionEntity itPersonilPositionEntity: itPersonil){
                            itPersonilPositionEntity.setNip(mutasi.getNip());
                            itPersonilPositionEntity.setPjs(mutasi.getPjs());
//                            itPersonilPositionEntity.setProfesiId(profesiId);
                            itPersonilPositionEntity.setProfesiId(mutasi.getProfesiBaruId());
                            //tanggal aktif digunakan untuk mengisi kolom tanggal / tahun diangkat di biodata-riwayat kerja
                            itPersonilPositionEntity.setTanggalAktif(bean.getTanggalEfektif());

                            if(mutasi.getStatus().equalsIgnoreCase("R") || mutasi.getStatus().equalsIgnoreCase("P")|| mutasi.getStatus().equalsIgnoreCase("MH")){
                                imBiodataEntity.setBranchIdTerakhir(mutasi.getBranchLamaId());
                                imBiodataEntity.setPositionIdTerakhir(mutasi.getPositionLamaId());
                                itPersonilPositionEntity.setAction("D");
                                itPersonilPositionEntity.setFlag("N");
                                itPersonilPositionEntity.setBranchId(mutasi.getBranchLamaId());
                                itPersonilPositionEntity.setPositionId(mutasi.getPositionLamaId());
                                imBiodataEntity.setFlag("N");
                                imBiodataEntity.setLastUpdate(bean.getLastUpdate());
                                imBiodataEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                if(mutasi.getStatus().equalsIgnoreCase("R")){
                                    imBiodataEntity.setKeterangan("Telah Resign pada tanggal " + bean.getTanggalEfektif());
                                }else if(mutasi.getStatus().equalsIgnoreCase("P")){
                                    imBiodataEntity.setKeterangan("Telah Pensiun pada tanggal " + bean.getTanggalEfektif());
                                }else {
                                    imBiodataEntity.setKeterangan("Telah Move Holding pada tanggal " + bean.getTanggalEfektif());
                                }

                            }else{
                                itPersonilPositionEntity.setBranchId(mutasi.getBranchBaruId());
                                itPersonilPositionEntity.setPositionId(mutasi.getPositionBaruId());
                                itPersonilPositionEntity.setAction("U");
                            }
                            if (imBiodataEntity.getTipePegawai().equalsIgnoreCase("TP03")){
                                imBiodataEntity.setTipePegawai("TP01");
                                imBiodataEntity.setGolongan(mutasi.getLevelBaru());
                            }else {
                                imBiodataEntity.setGolongan(mutasi.getLevelBaru());
                            }

                            itPersonilPositionEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            itPersonilPositionEntity.setLastUpdate(bean.getLastUpdate());

                            try {
                                personilPositionDao.updateAndSave(itPersonilPositionEntity);
                                biodataDao.updateAndSave(imBiodataEntity);
                            } catch (HibernateException e) {
                                logger.error("[MutasiBoImpl.saveDelete] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
                            }
                        }

                    }
                    try {
                        mutasiDao.addAndSave(itMutasiEntity);
                        mutasiDocDao.addAndSave(itDoc);
                    } catch (HibernateException e) {
                        logger.error("[MutasiBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
                    }
                }
            }
        }

        logger.info("[MutasiBoImpl.saveAdd] end process <<<");
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
            if (searchBean.getTipeMutasi() != null && !"".equalsIgnoreCase(searchBean.getTipeMutasi())) {
                hsCriteria.put("tipe_mutasi", searchBean.getTipeMutasi());
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

                    if (itMutasiEntity.getStatus()!= null){
                        if ("M".equalsIgnoreCase(itMutasiEntity.getStatus())){
                            returnMutasi.setStatusName("Move");
                        }
                        else if ("P".equalsIgnoreCase(itMutasiEntity.getStatus())){
                            returnMutasi.setStatusName("Pensiun");
                        }
                        else if ("R".equalsIgnoreCase(itMutasiEntity.getStatus())){
                            returnMutasi.setStatusName("Resign");
                        }
                        else{
                            returnMutasi.setStatusName("Move Holding");
                        }
                    }
                    returnMutasi.setTipeMutasi(itMutasiEntity.getTipeMutasi());
                    if (itMutasiEntity.getTipeMutasi()!= null) {
                        if ("MT".equalsIgnoreCase(itMutasiEntity.getTipeMutasi())) {
                            returnMutasi.setTipeMutasiName("Mutasi");
                        } else {
                            returnMutasi.setTipeMutasiName("Rotasi");
                        }
                    }
                    listOfResult.add(returnMutasi);
                }
            }
        }
        logger.info("[MutasiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    /*public List<Mutasi> getKualifikasi(Mutasi searchBean) throws GeneralBOException {
        List<Mutasi> listOfResult = new ArrayList();
        List<StrukturJabatan> tmp = new ArrayList();
        int syaratGolongan = 0;
        int noStrata = 0;
        String divisi = "";
        String strata = "";
        String qryStrata = "";
        String kelompokPisah[];
        String qryWhere = "";
        String qryBranch = "";
        String qryDivisi = "";

        List<ImHrisKualifikasiCalonPejabatEntity> listKualifikasi = new ArrayList();
        List<Biodata> listBiodataFinal = new ArrayList<>();

        // mengambil kriteria dari posisi yang dicari
        KualifikasiCalonPejabat kualifikasiCalonPejabat = new KualifikasiCalonPejabat();
        try {
            listKualifikasi = kualifikasiCalonPejabatDao.getKualifikasi(searchBean.getPositionLamaId());
            if(listKualifikasi.size() > 0){
                for(ImHrisKualifikasiCalonPejabatEntity kualifikasiLoop: listKualifikasi){
                    kualifikasiCalonPejabat.setKualifikasiId(kualifikasiLoop.getKualifikasiId());
                    kualifikasiCalonPejabat.setJabatanId(kualifikasiLoop.getJabatanId());
                    kualifikasiCalonPejabat.setDivisiId(kualifikasiLoop.getDivisiId());
                    kualifikasiCalonPejabat.setBagianId(kualifikasiLoop.getBagianId());
                    kualifikasiCalonPejabat.setThMinKerja(kualifikasiLoop.getThMinKerja());
                    kualifikasiCalonPejabat.setThMinBidang(kualifikasiLoop.getThMinBidang());
                    kualifikasiCalonPejabat.setBranchId(kualifikasiLoop.getBranchId());
                    kualifikasiCalonPejabat.setGolonganId(kualifikasiLoop.getGolonganId());

                    kualifikasiCalonPejabat.setStatusJurusan(kualifikasiLoop.getStatusJurusan());
                    kualifikasiCalonPejabat.setJabatanRotasi(kualifikasiLoop.getJabatanRotasi());
                    kualifikasiCalonPejabat.setStatusJabatanRotasi(kualifikasiLoop.getStatusJabatanRotasi());
                    kualifikasiCalonPejabat.setJabatanPromosi(kualifikasiLoop.getJabatanPromosi());
                    kualifikasiCalonPejabat.setStatusJabatanPromosi(kualifikasiLoop.getStatusJabatanPromosi());



                    kualifikasiCalonPejabat.setPendidikan(kualifikasiLoop.getPendidikan());
                    kualifikasiCalonPejabat.setPelatihanJabatan(kualifikasiLoop.getPelatihanJabatan());
                    kualifikasiCalonPejabat.setThMinBagian(kualifikasiLoop.getThMinBagian());
                    if("-".equalsIgnoreCase(kualifikasiLoop.getPelatihanJabatan())){
                        kualifikasiCalonPejabat.setPelatihanJabatan("");
                    }
                    else {
                        kualifikasiCalonPejabat.setPelatihanJabatan(kualifikasiLoop.getPelatihanJabatan());
                    }

                    if("".equalsIgnoreCase(kualifikasiLoop.getJurusanId())){
                        kualifikasiCalonPejabat.setJurusanId("");
                    }
                    else {
                        kualifikasiCalonPejabat.setJurusanId(kualifikasiLoop.getJurusanId());
                    }
                    if(kualifikasiCalonPejabat.getGolonganId() != null){
                        syaratGolongan = CommonUtil.convertRomawiToNumber(kualifikasiCalonPejabat.getGolonganId());
                    }
                }
            }
        } catch (HibernateException e) {
            logger.error("[MutasiBoImpl.getKualifikasi] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }


        if(listKualifikasi.size() > 0){
            // mengambil user yang memenuhi kriteria (awal) dengan syarat pendidikan dan pelatihan jabatan
            List<Biodata> listBiodata = new ArrayList<>();
            try {
                listBiodata = kualifikasiCalonPejabatDao.getBiodataForKualifikasi(kualifikasiCalonPejabat.getPendidikan(),kualifikasiCalonPejabat.getPelatihanJabatan());
                if (listBiodata.size()>0){
                    for (Biodata biodataLoop: listBiodata){
                        // melakukan cek syarat minimal golongan
                            if(CommonUtil.convertRomawiToNumber(biodataLoop.getGolonganId()) >= syaratGolongan){
                                //melakukan pengecekan jalur profesi
                                List<Biodata> biodataOnSameKelompok = new ArrayList<>();
                                List<HistoryJabatanPegawai> historyJabatanPegawai = new ArrayList<>();
                                String KelpositionId;
                                int newKelpositionId;
                                if("rotasi".equalsIgnoreCase(searchBean.getStatus())){
                                    try{
                                        //ambil data dengan kelompok posisi yg sama
                                        if (!kualifikasiCalonPejabat.getJabatanRotasi().equalsIgnoreCase("")){
                                            String[] positionNeeded = kualifikasiCalonPejabat.getJabatanRotasi().split(";");
                                            String positionFound;
                                            for (String positionLoop: positionNeeded){
                                                try{
                                                    positionFound = kualifikasiCalonPejabatDao.getJabatanForRotasiorPromosi(biodataLoop.getNip(), positionLoop);
                                                    if (positionFound != null){
                                                        listBiodataFinal.add(biodataLoop);
                                                        break;
                                                    }
                                                }catch (HibernateException e) {
                                                    logger.error("[MutasiBoImpl.getKualifikasi] Error, " + e.getMessage());
                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                }
                                            }
                                        }
                                        else{
                                            KelpositionId = kualifikasiCalonPejabatDao.getKelompokPositionId(kualifikasiCalonPejabat.getJabatanId());
                                            biodataOnSameKelompok = kualifikasiCalonPejabatDao.getBiodataForRotasi(biodataLoop.getNip(), kualifikasiCalonPejabat.getJabatanId(), KelpositionId);
                                            if (biodataOnSameKelompok.size()>0){
                                                listBiodataFinal.add(biodataLoop);
                                            }
                                        }
                                    }catch (HibernateException e) {
                                        logger.error("[MutasiBoImpl.getKualifikasi] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                    }
                                }else{
                                    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//                                    int bidangMasuk, bidangKeluar, bagianMasuk, bagianKeluar;

                                    try{
                                        KelpositionId = kualifikasiCalonPejabatDao.getKelompokPositionId(kualifikasiCalonPejabat.getJabatanId());
                                        newKelpositionId = CommonUtil.convertStringIdtoInt(KelpositionId,"KL");
                                        if (newKelpositionId == 4){
                                            KelpositionId = CommonUtil.convertIntToStringId(newKelpositionId+2);
                                        }
                                        else{
                                            KelpositionId = CommonUtil.convertIntToStringId(newKelpositionId+1);
                                        }
                                        //pengecekan jurusan disini
                                        String[] jurusanNeeded= kualifikasiCalonPejabat.getJurusanId().split(";");
                                        for (String jurusanLoop: jurusanNeeded){
                                            biodataOnSameKelompok = kualifikasiCalonPejabatDao.getBiodataForPromosi(biodataLoop.getNip(),KelpositionId, jurusanLoop ,kualifikasiCalonPejabat.getStatusJurusan(), kualifikasiCalonPejabat.getPendidikan() );
                                            if (biodataOnSameKelompok.size()>0){
                                                int aktif = Integer.parseInt(dateFormat.format(biodataLoop.getTanggalAktif()));
                                                int sekarang = Integer.parseInt(dateFormat.format(new Date()));
                                                Double tahun = Double.valueOf((sekarang - aktif) / 10000);
                                                //filter berdasarkan lama tahun kerja
                                                if ( tahun >= Double.valueOf(kualifikasiCalonPejabat.getThMinKerja())){
                                                    //filter berdsarkan bidang dan bagian
                                                    if(!"".equalsIgnoreCase(kualifikasiCalonPejabat.getDivisiId()) && kualifikasiCalonPejabat.getDivisiId()!= null){
                                                        List<HistoryJabatanPegawai> historiBidang = new ArrayList<>();
                                                        historiBidang = kualifikasiCalonPejabatDao.getBidangPeriod(biodataLoop.getNip(), kualifikasiCalonPejabat.getDivisiId());
                                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                                        float thBidang = 0;
                                                        float thBagian = 0;
                                                        for (HistoryJabatanPegawai result: historiBidang){
                                                            try{
//                                                                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                                                                Date tglMasuk = formatter.parse(result.getTanggal());
                                                                int bidangMasuk = Integer.parseInt(dateFormat.format(tglMasuk));
                                                                Date tglakhir = formatter.parse(result.getTanggalKeluar());
                                                                int bidangKeluar = Integer.parseInt(dateFormat.format(tglakhir));
                                                                float th = bidangKeluar-bidangMasuk;
                                                                thBidang += th /10000;
//                                                                DateTime newTglMasuk = new DateTime(tglMasuk);
//                                                                DateTime newTglKeluar = new DateTime(tglakhir);
//                                                                Period period = new Period(newTglMasuk, newTglKeluar , PeriodType.yearMonthDayTime());
                                                            }catch (ParseException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                            //filter bagian setelah syarat minimum bidang terpenuhi
                                                        if(thBidang >= Float.valueOf(kualifikasiCalonPejabat.getThMinBidang())){
                                                            List<HistoryJabatanPegawai> history;
                                                            history = kualifikasiCalonPejabatDao.getBagianPeriod(biodataLoop.getNip(), kualifikasiCalonPejabat.getBagianId());
//                                                            int thBagian = 0;
                                                            for (HistoryJabatanPegawai result2: history){
                                                                try{
                                                                    Date tglMasuk = formatter.parse(result2.getTanggal());
                                                                    int bagianMasuk = Integer.parseInt(dateFormat.format(tglMasuk));
                                                                    Date tglakhir = formatter.parse(result2.getTanggalKeluar());
                                                                    int bagianKeluar = Integer.parseInt(dateFormat.format(tglakhir));
                                                                    float th = bagianKeluar-bagianMasuk;
                                                                    thBagian += th /10000;
//                                                                    thBagian += bagianKeluar-bagianMasuk;
//                                                                    Date tglMasuk = formatter.parse(result2.getTanggal());
//                                                                    Date tglakhir = formatter.parse(result2.getTanggalKeluar());
//                                                                    DateTime newTglMasuk = new DateTime(tglMasuk);
//                                                                    DateTime newTglKeluar = new DateTime(tglakhir);
//                                                                    Period period = new Period(newTglMasuk, newTglKeluar , PeriodType.yearMonthDayTime());
//                                                                    thBagian += period.getYears();
                                                                }catch (ParseException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
//                                                            thBagian = thBagian /10000;
                                                            if(thBagian >= Float.valueOf(kualifikasiCalonPejabat.getThMinBagian())){
                                                                String[] positionNeeded = kualifikasiCalonPejabat.getJabatanPromosi().split(";");
                                                                String positionFound;
                                                                for (String positionLoop: positionNeeded){
                                                                    try{
                                                                        if (!positionLoop.equalsIgnoreCase("")){
                                                                            positionFound = kualifikasiCalonPejabatDao.getJabatanForRotasiorPromosi(biodataLoop.getNip(), positionLoop);
                                                                            if (positionFound != null){
                                                                                listBiodataFinal.add(biodataLoop);
                                                                                break;
                                                                            }
                                                                        }
                                                                        else {
                                                                            listBiodataFinal.add(biodataLoop);
                                                                        }
                                                                    }catch (HibernateException e) {
                                                                        logger.error("[MutasiBoImpl.getKualifikasi] Error, " + e.getMessage());
                                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
//                                                filter data tanpa bidang dengan bagian
                                                    else if (!"".equalsIgnoreCase(kualifikasiCalonPejabat.getBagianId()) && kualifikasiCalonPejabat.getBagianId()!= null){
                                                        List<HistoryJabatanPegawai> history;
                                                        history = kualifikasiCalonPejabatDao.getBagianPeriod(biodataLoop.getNip(), kualifikasiCalonPejabat.getBagianId());
//                                                        int thBagian = 0;
//                                                        Double thBidang = 0.0;
                                                        float thBagian = 0;
                                                        for (HistoryJabatanPegawai result: history){
                                                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                                            try{
                                                                Date tglMasuk = formatter.parse(result.getTanggal());
                                                                int bagianMasuk = Integer.parseInt(dateFormat.format(tglMasuk));
                                                                Date tglakhir = formatter.parse(result.getTanggalKeluar());
                                                                int bagianKeluar = Integer.parseInt(dateFormat.format(tglakhir));
                                                                float th = bagianKeluar-bagianMasuk;
                                                                thBagian += th /10000;
//                                                                Date tglMasuk = formatter.parse(result.getTanggal());
//                                                                Date tglakhir = formatter.parse(result.getTanggalKeluar());
//                                                                DateTime newTglMasuk = new DateTime(tglMasuk);
//                                                                DateTime newTglKeluar = new DateTime(tglakhir);
//                                                                Period period = new Period(newTglMasuk, newTglKeluar , PeriodType.yearMonthDayTime());
//                                                                thBagian += period.getYears();
                                                            }catch (ParseException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
//                                                        thBagian = thBagian /10000;
                                                        if(thBagian >= Float.valueOf(kualifikasiCalonPejabat.getThMinBagian())){
                                                            String[] positionNeeded = kualifikasiCalonPejabat.getJabatanPromosi().split(";");
                                                            String positionFound;
                                                            for (String positionLoop: positionNeeded){
                                                                try{
                                                                    if (!positionLoop.equalsIgnoreCase("")){
                                                                        positionFound = kualifikasiCalonPejabatDao.getJabatanForRotasiorPromosi(biodataLoop.getNip(), positionLoop);
                                                                        if (positionFound != null){
                                                                            listBiodataFinal.add(biodataLoop);
                                                                            break;
                                                                        }
                                                                    }
                                                                    else {
                                                                        listBiodataFinal.add(biodataLoop);
                                                                    }

                                                                }catch (HibernateException e) {
                                                                    logger.error("[MutasiBoImpl.getKualifikasi] Error, " + e.getMessage());
                                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                                }
                                                            }
                                                        }
                                                    }
                                                    //filter data tanpa bidang dan bagian
                                                    else{
                                                        if (kualifikasiCalonPejabat.getStatusJabatanPromosi().equalsIgnoreCase("wajib")){
                                                            String[] positionNeeded = kualifikasiCalonPejabat.getJabatanPromosi().split(";");
                                                            String positionFound;
                                                            for (String positionLoop: positionNeeded){
                                                                try{
                                                                    if (!positionLoop.equalsIgnoreCase("")){
                                                                        positionFound = kualifikasiCalonPejabatDao.getJabatanForRotasiorPromosi(biodataLoop.getNip(), positionLoop);
                                                                        if (positionFound != null){
                                                                            listBiodataFinal.add(biodataLoop);
                                                                            break;
                                                                        }
                                                                    }
                                                                    else {
                                                                        listBiodataFinal.add(biodataLoop);
                                                                    }
                                                                }catch (HibernateException e) {
                                                                    logger.error("[MutasiBoImpl.getKualifikasi] Error, " + e.getMessage());
                                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                                }
                                                            }
                                                        }else {
                                                            listBiodataFinal.add(biodataLoop);
                                                        }
                                                    }

                                                }
                                            }
                                        }

                                    } catch (HibernateException e) {
                                        logger.error("[MutasiBoImpl.getKualifikasi] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                    }
                                }
                            }
                    }

                }

            } catch (HibernateException e) {
                logger.error("[MutasiBoImpl.getKualifikasi] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
        }

        if(listBiodataFinal.size() > 0){
            for(Biodata biodata: listBiodataFinal){
                Mutasi mutasi = new Mutasi();
                mutasi.setNip(biodata.getNip());
                mutasi.setNama(biodata.getNamaPegawai());
                mutasi.setBranchBaruName(biodata.getBranchName());
                mutasi.setPositionBaruName(biodata.getPositionName());
                mutasi.setDivisiBaruName(biodata.getDivisiName());
                if("diutamakan".equalsIgnoreCase(kualifikasiCalonPejabat.getStatusJurusan()) && kualifikasiCalonPejabat.getStatusJurusan()!=null && "promosi".equalsIgnoreCase(searchBean.getStatus())){
                    if(biodata.getJurusanId()!=null &&  kualifikasiCalonPejabat.getJurusanId().contains(biodata.getJurusanId())){ // tambahkan contains
                        mutasi.setStatusPromosi("diutamakan");
                    }
                }
                listOfResult.add(mutasi);
            }
        }

        return listOfResult;

        *//*ImPosition position = null;
        position = positionDao.getById("positionId", searchBean.getPositionLamaId());

        if(position != null){
            strata = position.getStrataId();
            if(strata != null && !"".equalsIgnoreCase(strata)){
                noStrata = Integer.parseInt(strata.substring(2));
            }
            //qryDivisi = " and posisi.department_id = '"+ position.getDepartmentId() +"' ";
        }

        if(!searchBean.getBranchLamaId().equalsIgnoreCase("")){
            qryBranch = " and itPosisi.branch_id = '" + searchBean.getBranchLamaId() + "' ";
        }

        if(searchBean.getStatus().equalsIgnoreCase("promosi") && position.getStrataId() != null){
            if(searchBean.getBranchLamaId().equalsIgnoreCase("")){
                int noStrata2 = noStrata - 1;
                noStrata-=2;
                qryStrata = "IN ('CS00"+noStrata + "', 'CS00" + noStrata2+"') ";
            }else if(searchBean.getBranchLamaId().equalsIgnoreCase("KD01") && position.getStrataId().equalsIgnoreCase("CS004")){
                noStrata-=2;
                qryStrata = "= 'CS00"+noStrata + "' ";
            }else{
                noStrata--;
                qryStrata = "= 'CS00"+noStrata + "' ";
            }

            qryStrata = "CS00"+noStrata;
            qryWhere = " where itPosisi.flag = 'Y' \n" +
                    "\tand posisi.carrer_path_strata_id = '"+qryStrata+"'" + qryBranch + qryDivisi;
        }else if(searchBean.getStatus().equalsIgnoreCase("rotasi")){
            qryWhere = " where posisi.carrer_path_strata_id = '"+strata+"' " + qryBranch + qryDivisi;
        }else if(searchBean.getStatus().equalsIgnoreCase("demosi")){
            if(searchBean.getBranchLamaId().equalsIgnoreCase("")){
                int noStrata2 = noStrata + 1;
                noStrata+=2;
                qryStrata = "IN ('CS00"+noStrata + "', 'CS00" + noStrata2+"') ";
            }else if(searchBean.getBranchLamaId().equalsIgnoreCase("KD01") && position.getStrataId().equalsIgnoreCase("CS002")){
                noStrata+=2;
                qryStrata = "= 'CS00"+noStrata + "' ";
            }else{
                noStrata++;
                qryStrata = "= 'CS00"+noStrata + "' ";
            }

            qryWhere = " where itPosisi.flag = 'Y' \n" +
                    "\tand posisi.carrer_path_strata_id "+qryStrata + qryBranch + qryDivisi;
        }*//*


        // digunakan untuk mencari orang yang memenuhi kritaria
        *//*try {
            listOfResult = mutasiDao.getKualifikasi(qryWhere);
        } catch (HibernateException e) {
            logger.error("[StrukturJabatanBoImpl.getSearchStrukturJabatanByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }*//*

    }*/

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
        logger.info("[DepartmentBoImpl.saveAdd] start process >>>");

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
                    logger.error("[DepartmentBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Department, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[DepartmentBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public Mutasi getDataReportMutasi(String mutasiId) throws GeneralBOException {
        ImBiodataEntity biodata = new ImBiodataEntity();
        Mutasi resultMutasi = new Mutasi();
        List<Mutasi> mutasiList = null;
        String label1, label2, label3, label4, label5, label6, label7 ;
        mutasiList = mutasiDao.getDataReportMutasi(mutasiId);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);
        String[]tahun = stDate.split("-");
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
}