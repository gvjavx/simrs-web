package com.neurix.hris.master.biodata.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.dao.PelatihanJabatanUserDao;
import com.neurix.hris.master.biodata.dao.PengalamanKerjaDao;
import com.neurix.hris.master.biodata.dao.TunjLainPegawaiDao;
import com.neurix.hris.master.biodata.model.*;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golonganPkwt.dao.GolonganPkwtDao;
import com.neurix.hris.master.golonganPkwt.model.ImGolonganPkwtEntity;
import com.neurix.hris.master.keluarga.dao.KeluargaDao;
import com.neurix.hris.master.keluarga.model.ImKeluargaEntity;
import com.neurix.hris.master.keluarga.model.ImKeluargaPK;
import com.neurix.hris.master.keluarga.model.Keluarga;
import com.neurix.hris.master.payrollDanaPensiun.dao.PayrollDanaPensiunDao;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.*;
import com.neurix.hris.master.profesi.dao.ProfesiDao;
import com.neurix.hris.master.profesi.model.ImProfesiEntity;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;
import com.neurix.hris.master.provinsi.model.ImProvinsiEntity;
import com.neurix.hris.master.reward.dao.RewardDao;
import com.neurix.hris.master.reward.model.ImRewardEntity;
import com.neurix.hris.master.reward.model.Reward;
import com.neurix.hris.master.rsKerjasama.dao.RsKerjasamaDao;
import com.neurix.hris.master.rsKerjasama.model.ImRsKerjasamaEntity;
import com.neurix.hris.master.rskelas.dao.RsKelasDao;
import com.neurix.hris.master.rskelas.model.ImHrisRsKelas;
import com.neurix.hris.master.rskelas.model.RsKelas;
import com.neurix.hris.master.sertifikat.dao.SertifikatDao;
import com.neurix.hris.master.sertifikat.model.ImSertifikatEntity;
import com.neurix.hris.master.sertifikat.model.Sertifikat;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.master.study.dao.StudyDao;
import com.neurix.hris.master.study.model.ImStudyEntity;
import com.neurix.hris.master.study.model.Study;
import com.neurix.hris.transaksi.ijinKeluar.dao.IjinKeluarDao;
import com.neurix.hris.transaksi.lembur.dao.LemburDao;
import com.neurix.hris.transaksi.payroll.dao.PayrollDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollEntity;
import com.neurix.hris.transaksi.payroll.model.Payroll;
import com.neurix.hris.transaksi.personilPosition.dao.HistoryJabatanPegawaiDao;
import com.neurix.hris.transaksi.personilPosition.model.HistoryJabatanPegawai;
import com.neurix.hris.transaksi.personilPosition.model.ImtHrisHistoryJabatanPegawaiEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.smk.dao.SmkHistoryGolonganDao;
import com.neurix.hris.transaksi.smk.model.ImtHistorySmkGolonganEntity;
import com.neurix.hris.transaksi.training.dao.TrainingDao;
import com.neurix.hris.transaksi.training.dao.TrainingPersonDao;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingEntity;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingPersonEntity;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.joda.time.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class BiodataBoImpl implements BiodataBo {

    protected static transient Logger logger = Logger.getLogger(BiodataBoImpl.class);
    private BiodataDao biodataDao;
    private TunjLainPegawaiDao tunjLainPegawaiDao;
    private PengalamanKerjaDao pengalamanKerjaDao;
    private RewardDao rewardDao;
    private StrukturJabatanDao strukturJabatanDao;
    private SertifikatDao sertifikatDao;
    private PelatihanJabatanUserDao pelatihanJabatanUserDao;
    private TrainingPersonDao trainingPersonDao;
    private TrainingDao trainingDao;
    private RsKelasDao rsKelasDao;
    private PositionDao positionDao;
    private RsKerjasamaDao rsKerjasamaDao;
    private PersonilPositionDao personilPositionDao;
    private IjinKeluarDao ijinKeluarDao;
    private RsKerjasamaDao cutiPegawai;
    private RsKerjasamaDao notifikasi;
    private LemburDao lemburDao;
    private DepartmentDao departmentDao;
    private BranchDao branchDao;
    private ProvinsiDao provinsiDao;
    private KeluargaDao keluargaDao;
    private StudyDao studyDao;
    private HistoryJabatanPegawaiDao historyJabatanPegawaiDao;
    private SmkHistoryGolonganDao smkHistoryGolonganDao;
    private PayrollDanaPensiunDao danaPensiunDao;
    private PayrollDao payrollDao;
    private PositionBagianDao positionBagianDao;
    private GolonganPkwtDao golonganPkwtDao;
    private ProfesiDao profesiDao;

    public ProfesiDao getProfesiDao() {
        return profesiDao;
    }

    public void setProfesiDao(ProfesiDao profesiDao) {
        this.profesiDao = profesiDao;
    }

    public List<StrukturJabatan> getStrukturJabatanList() {
        return strukturJabatanList;
    }

    public void setStrukturJabatanList(List<StrukturJabatan> strukturJabatanList) {
        this.strukturJabatanList = strukturJabatanList;
    }

    public GolonganPkwtDao getGolonganPkwtDao() {
        return golonganPkwtDao;
    }

    public void setGolonganPkwtDao(GolonganPkwtDao golonganPkwtDao) {
        this.golonganPkwtDao = golonganPkwtDao;
    }

    public TunjLainPegawaiDao getTunjLainPegawaiDao() {
        return tunjLainPegawaiDao;
    }

    public void setTunjLainPegawaiDao(TunjLainPegawaiDao tunjLainPegawaiDao) {
        this.tunjLainPegawaiDao = tunjLainPegawaiDao;
    }

    public PelatihanJabatanUserDao getPelatihanJabatanUserDao() {
        return pelatihanJabatanUserDao;
    }

    public void setPelatihanJabatanUserDao(PelatihanJabatanUserDao pelatihanJabatanUserDao) {
        this.pelatihanJabatanUserDao = pelatihanJabatanUserDao;
    }

    public SmkHistoryGolonganDao getSmkHistoryGolonganDao() {
        return smkHistoryGolonganDao;
    }

    public void setSmkHistoryGolonganDao(SmkHistoryGolonganDao smkHistoryGolonganDao) {
        this.smkHistoryGolonganDao = smkHistoryGolonganDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    public SertifikatDao getSertifikatDao() {
        return sertifikatDao;
    }

    public void setSertifikatDao(SertifikatDao sertifikatDao) {
        this.sertifikatDao = sertifikatDao;
    }

    public RewardDao getRewardDao() {
        return rewardDao;
    }

    public void setRewardDao(RewardDao rewardDao) {
        this.rewardDao = rewardDao;
    }

    public PengalamanKerjaDao getPengalamanKerjaDao() {
        return pengalamanKerjaDao;
    }

    public void setPengalamanKerjaDao(PengalamanKerjaDao pengalamanKerjaDao) {
        this.pengalamanKerjaDao = pengalamanKerjaDao;
    }

    public PayrollDao getPayrollDao() {
        return payrollDao;
    }

    public void setPayrollDao(PayrollDao payrollDao) {
        this.payrollDao = payrollDao;
    }

    public PayrollDanaPensiunDao getDanaPensiunDao() {
        return danaPensiunDao;
    }

    public void setDanaPensiunDao(PayrollDanaPensiunDao danaPensiunDao) {
        this.danaPensiunDao = danaPensiunDao;
    }

    public HistoryJabatanPegawaiDao getHistoryJabatanPegawaiDao() {
        return historyJabatanPegawaiDao;
    }

    public void setHistoryJabatanPegawaiDao(HistoryJabatanPegawaiDao historyJabatanPegawaiDao) {
        this.historyJabatanPegawaiDao = historyJabatanPegawaiDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public KeluargaDao getKeluargaDao() {
        return keluargaDao;
    }

    public void setKeluargaDao(KeluargaDao keluargaDao) {
        this.keluargaDao = keluargaDao;
    }

    public ProvinsiDao getProvinsiDao() {
        return provinsiDao;
    }

    public void setProvinsiDao(ProvinsiDao provinsiDao) {
        this.provinsiDao = provinsiDao;
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

    public LemburDao getLemburDao() {
        return lemburDao;
    }

    public void setLemburDao(LemburDao lemburDao) {
        this.lemburDao = lemburDao;
    }

    public RsKerjasamaDao getRsKerjasamaDao() {
        return rsKerjasamaDao;
    }

    public void setRsKerjasamaDao(RsKerjasamaDao rsKerjasamaDao) {
        this.rsKerjasamaDao = rsKerjasamaDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public RsKelasDao getRsKelasDao() {
        return rsKelasDao;
    }

    public void setRsKelasDao(RsKelasDao rsKelasDao) {
        this.rsKelasDao = rsKelasDao;
    }

    public TrainingDao getTrainingDao() {
        return trainingDao;
    }

    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public TrainingPersonDao getTrainingPersonDao() {
        return trainingPersonDao;
    }

    public void setTrainingPersonDao(TrainingPersonDao trainingPersonDao) {
        this.trainingPersonDao = trainingPersonDao;
    }
    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BiodataBoImpl.logger = logger;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }
    @Override
    public List<Payroll> viewPayrollSys(String nip, String branchId, String bulan, String tahun, String payrollId) throws GeneralBOException {
        List<Payroll> payroll = new ArrayList<>();
        List<ItPayrollEntity> itPayroll = null;

        itPayroll = payrollDao.getDataViewMobile(nip, branchId, bulan, tahun, payrollId);
        if(itPayroll.size() > 0){
            for(ItPayrollEntity itPayrollEntity : itPayroll){
                Payroll payroll1 = new Payroll();
                payroll1.setPayrollId(itPayrollEntity.getPayrollId());
                payroll1.setBranchId(itPayrollEntity.getBranchId());
                payroll1.setBranchName(itPayrollEntity.getBranchName());
                payroll1.setBulan(itPayrollEntity.getBulan());
                payroll1.setTahun(itPayrollEntity.getTahun());
                payroll1.setNip(itPayrollEntity.getNip());
                payroll1.setTotalA(CommonUtil.numbericFormat(itPayrollEntity.getTotalA(), "###,###"));
                payroll1.setTotalB(CommonUtil.numbericFormat(itPayrollEntity.getTotalB(), "###,###"));
                payroll1.setPphGaji(CommonUtil.numbericFormat(itPayrollEntity.getPphGaji(), "###,###"));
                payroll1.setTotalGajiBersih(CommonUtil.numbericFormat(itPayrollEntity.getGajiBersih(), "###,###"));
//                payroll1.setTotalRapel(CommonUtil.numbericFormat(itPayrollEntity.getTotalRapel(), "###,###"));
//                payroll1.setTotalThr(CommonUtil.numbericFormat(itPayrollEntity.getTotalThr(), "###,###"));
//                payroll1.setTotalPendidikan(CommonUtil.numbericFormat(itPayrollEntity.getTotalPendidikan(), "###,###"));
//                payroll1.setTotalJasProd(CommonUtil.numbericFormat(itPayrollEntity.getTotalJasProd(), "###,###"));
//                payroll1.setTotalPensiun(CommonUtil.numbericFormat(itPayrollEntity.getTotalPensiun(), "###,###"));
//                payroll1.setTotalJubileum(CommonUtil.numbericFormat(itPayrollEntity.getTotalJubileum(), "###,###"));

                payroll.add(payroll1);
            }
        }

        return payroll;
    }
    @Override
    public void saveDelete(Biodata bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String personalId = bean.getNip();

            ImBiodataEntity imBiodataEntity = null;

            try {
                // Get data from database by ID
                imBiodataEntity = biodataDao.getById("nip", personalId);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imBiodataEntity != null) {

                // Modify from bean to entity serializable
                imBiodataEntity.setNip(bean.getNip());
                imBiodataEntity.setNamaPegawai(bean.getNamaPegawai());
                imBiodataEntity.setKeterangan(bean.getKeterangan());
                imBiodataEntity.setFlag(bean.getFlag());
                imBiodataEntity.setAction(bean.getAction());
                imBiodataEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBiodataEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    biodataDao.updateAndSave(imBiodataEntity);
                } catch (HibernateException e) {
                    logger.error("[BiodataBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Biodata, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[BiodataBoImpl.saveDelete] Error, not found data Biodata with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Biodata with request id, please check again your data ...");

            }
        }
        logger.info("[BiodataBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Biodata bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String personalId = bean.getNip();

            ImBiodataEntity imBiodataEntity = null;
            List<ItPersonilPositionEntity> itPersonilPositionEntity = null;
            ImBiodataHistoryEntity imBiodataHistoryEntity = new ImBiodataHistoryEntity();
            try {
                // Get data from database by ID
                imBiodataEntity = biodataDao.getById("nip", personalId);
                itPersonilPositionEntity = personilPositionDao.getListNip(bean.getNip());
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Biodata by Kode Biodata, please inform to your admin...," + e.getMessage());
            }

            if (imBiodataEntity != null) {
                imBiodataEntity.setNip(bean.getNip());
                imBiodataEntity.setNamaPegawai(bean.getNamaPegawai());
                imBiodataEntity.setGender(bean.getGender());
                imBiodataEntity.setAgama(bean.getAgama());
                imBiodataEntity.setStatusKeluarga(bean.getStatusKeluarga());
                imBiodataEntity.setJumlahAnak(bean.getJumlahAnak());
                imBiodataEntity.setTempatLahir(bean.getTempatLahir());
                imBiodataEntity.setTanggalLahir(bean.getTanggalLahir());
                imBiodataEntity.setTanggalPensiun(bean.getTanggalPensiun());
                imBiodataEntity.setTanggalMasuk(bean.getTanggalMasuk());
                imBiodataEntity.setTanggalAktif(bean.getTanggalAktif());
                imBiodataEntity.setBranchId(bean.getBranch());
                imBiodataEntity.setPosisiId(bean.getPositionId());
                imBiodataEntity.setNpwp(bean.getNpwp());
                imBiodataEntity.setGelarDepan(bean.getGelarDepan());
                imBiodataEntity.setGelarBelakang(bean.getGelarBelakang());
                imBiodataEntity.setNoKtp(bean.getNoKtp());
                imBiodataEntity.setNoTelp(bean.getNoTelp());
                imBiodataEntity.setAlamat(bean.getAlamat());
                imBiodataEntity.setProvinsiId(bean.getProvinsiId());
                imBiodataEntity.setKotaId(bean.getKabupatenId());
                imBiodataEntity.setKecamatanId(bean.getKecamatanId());
                imBiodataEntity.setDesaId(bean.getDesaId());
                imBiodataEntity.setRtRw(bean.getRtRw());
                imBiodataEntity.setTipePegawai(bean.getTipePegawai());
                imBiodataEntity.setStrukturGaji(bean.getStrukturGaji());
//                imBiodataEntity.setGaji(BigDecimal.valueOf(Double.parseDouble(bean.getGaji())));
                imBiodataEntity.setStatusGiling(bean.getStatusGiling());
                imBiodataEntity.setMt(bean.getMt());
                imBiodataEntity.setGolongan(bean.getGolongan());
                imBiodataEntity.setPin(bean.getPin());
                imBiodataEntity.setStatusPegawai(bean.getStatusPegawai());
                imBiodataEntity.setDanaPensiun(bean.getDanaPensiun());
                imBiodataEntity.setNoAnggotaDapen(bean.getNoAnggotaDapen());
                imBiodataEntity.setNoBpjsKetenagakerjaan(bean.getNoBpjsKetenagakerjaan());
                imBiodataEntity.setNoBpjsKetenagakerjaanPensiun(bean.getNoBpjsKetenagakerjaanPensiun());
                imBiodataEntity.setNoBpjsKesehatan(bean.getNoBpjsKesehatan());
                imBiodataEntity.setNamaBank(bean.getNamaBank());
                imBiodataEntity.setNoRekBank(bean.getNoRekBank());
                imBiodataEntity.setCabangBank(bean.getCabangBank());

                imBiodataEntity.setMasaKerjaGolongan(Integer.parseInt(bean.getStMasaKerjaGol()));
                imBiodataEntity.setGolonganDapenId(bean.getGolonganDapenId());

                if(bean.getFotoUpload() != null){
                    imBiodataEntity.setFotoUpload(bean.getFotoUpload());
                }
                imBiodataEntity.setFlag(bean.getFlag());
                imBiodataEntity.setAction(bean.getAction());
                imBiodataEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBiodataEntity.setLastUpdate(bean.getLastUpdate());

                if(itPersonilPositionEntity != null){
                    for(ItPersonilPositionEntity itPerson : itPersonilPositionEntity){
                        itPerson.setBranchId(bean.getBranch());
                        itPerson.setDivisiId(bean.getDivisi());
                        itPerson.setPositionId(bean.getPositionId());
                        itPerson.setProfesiId(bean.getProfesiId());
                        itPerson.setPjs(bean.getPjs());
                        itPerson.setFlag(bean.getFlag());
                        itPerson.setAction(bean.getAction());
                        itPerson.setLastUpdateWho(bean.getLastUpdateWho());
                        itPerson.setLastUpdate(bean.getLastUpdate());
                        personilPositionDao.updateAndSave(itPerson);
                    }
                }
                try {
                    // Update into database
                    biodataDao.updateAndSave(imBiodataEntity);

                } catch (HibernateException e) {
                    logger.error("[BiodataBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Biodata, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[BiodataBoImpl.saveEdit] Error, not found data Biodata with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Biodata with request id, please check again your data ...");

            }
        }
        logger.info("[BiodataBoImpl.saveEdit] end process <<<");
    }

    public void saveEditCaption(Biodata bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String personalId = bean.getNip();

            ImBiodataEntity imBiodataEntity = null;
            ImBiodataHistoryEntity imBiodataHistoryEntity = new ImBiodataHistoryEntity();
            try {
                // Get data from database by ID
                imBiodataEntity = biodataDao.getById("nip", personalId);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Biodata by Kode Biodata, please inform to your admin...," + e.getMessage());
            }

            if (imBiodataEntity != null) {

                imBiodataEntity.setNip(bean.getNip());
                imBiodataEntity.setStatusCaption(bean.getStatusCaption());
                try {
                    // Update into database
                    biodataDao.updateAndSave(imBiodataEntity);

                } catch (HibernateException e) {
                    logger.error("[BiodataBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Biodata, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[BiodataBoImpl.saveEdit] Error, not found data Biodata with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Biodata with request id, please check again your data ...");

            }
        }
        logger.info("[BiodataBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Biodata saveAdd(Biodata bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getNip(), bean.getNoKtp());
            if (!status.equalsIgnoreCase("Exist")){
                String personPosition;

                personPosition =  personilPositionDao.getNextPersonilPositionId();
                // creating object entity serializable
                ImBiodataEntity imBiodataEntity = new ImBiodataEntity();
                ItPersonilPositionEntity itPersonilPositionEntity = new ItPersonilPositionEntity();

                itPersonilPositionEntity.setPersonilPositionId(personPosition);
                itPersonilPositionEntity.setNip(bean.getNip());
                itPersonilPositionEntity.setBranchId(bean.getBranch());
                itPersonilPositionEntity.setPositionId(bean.getPositionId());
                itPersonilPositionEntity.setProfesiId(bean.getProfesiId());
                itPersonilPositionEntity.setPjs(bean.getPjs());
                itPersonilPositionEntity.setFlag(bean.getFlag());
                itPersonilPositionEntity.setAction(bean.getAction());
                itPersonilPositionEntity.setCreatedWho(bean.getCreatedWho());
                itPersonilPositionEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itPersonilPositionEntity.setCreatedDate(bean.getCreatedDate());
                itPersonilPositionEntity.setLastUpdate(bean.getLastUpdate());

                imBiodataEntity.setNip(bean.getNip());
                imBiodataEntity.setProfesiId(bean.getProfesiId());
                imBiodataEntity.setNamaPegawai(bean.getNamaPegawai());
                imBiodataEntity.setGelarDepan(bean.getGelarDepan());
                imBiodataEntity.setGelarBelakang(bean.getGelarBelakang());
                imBiodataEntity.setNoKtp(bean.getNoKtp());
                imBiodataEntity.setAlamat(bean.getAlamat());
                imBiodataEntity.setRtRw(bean.getRtRw());
                imBiodataEntity.setDesaId(bean.getDesaId());
                imBiodataEntity.setKecamatanId(bean.getKecamatanId());
                imBiodataEntity.setNoTelp(bean.getNoTelp());
                imBiodataEntity.setKotaId(bean.getKabupatenId());
                imBiodataEntity.setProvinsiId(bean.getProvinsiId());
                imBiodataEntity.setTanggalLahir(bean.getTanggalLahir());
                imBiodataEntity.setTanggalPensiun(bean.getTanggalPensiun());
                imBiodataEntity.setTanggalMasuk(bean.getTanggalMasuk());
                imBiodataEntity.setTanggalAktif(bean.getTanggalAktif());
                imBiodataEntity.setTempatLahir(bean.getTempatLahir());
                imBiodataEntity.setTipePegawai(bean.getTipePegawai());
                imBiodataEntity.setFotoUpload(bean.getFotoUpload());
                imBiodataEntity.setStatusCaption(bean.getStatusCaption());
                imBiodataEntity.setKeterangan(bean.getKeterangan());
                imBiodataEntity.setTanggalMasuk(CommonUtil.convertStringToDate(bean.getStTanggalMasuk()));
                imBiodataEntity.setStatusPegawai(bean.getStatusPegawai());
                imBiodataEntity.setStatusKeluarga(bean.getStatusKeluarga());
                imBiodataEntity.setGolongan(bean.getGolongan());
                imBiodataEntity.setMasaKerjaGolongan(Integer.parseInt(bean.getStMasaKerjaGol()));
                imBiodataEntity.setGolonganDapenId(bean.getGolonganDapenId());

                //Tanggal Pensiun Lama
            /*DateTime tglLahir = new DateTime(bean.getTanggalLahir());
            tglLahir = tglLahir.plusYears(55);
            String strTglLahir[] = CommonUtil.convertDateToString(bean.getTanggalLahir()).split("-");
            imBiodataEntity.setTanggalPensiun(CommonUtil.convertStringToDate((strTglLahir[0]) + "-" + strTglLahir[1] + "-" + strTglLahir[2]));*/

                imBiodataEntity.setGender(bean.getGender());
                imBiodataEntity.setJumlahAnak(bean.getJumlahAnak());
                imBiodataEntity.setNpwp(bean.getNpwp());
                imBiodataEntity.setDanaPensiun(bean.getDanaPensiun());
                imBiodataEntity.setNoAnggotaDapen(bean.getNoAnggotaDapen());
                imBiodataEntity.setNoBpjsKetenagakerjaan(bean.getNoBpjsKetenagakerjaan());
                imBiodataEntity.setNoBpjsKetenagakerjaanPensiun(bean.getNoBpjsKetenagakerjaanPensiun());
                imBiodataEntity.setNoBpjsKesehatan(bean.getNoBpjsKesehatan());
                imBiodataEntity.setStrukturGaji(bean.getStrukturGaji());
                if(bean.getGaji() != null && !"".equalsIgnoreCase(bean.getGaji())){
                    imBiodataEntity.setGaji(BigDecimal.valueOf(Double.parseDouble(bean.getGaji())));
                }else{
                    imBiodataEntity.setGaji(BigDecimal.valueOf(0));
                }
                imBiodataEntity.setAgama(bean.getAgama());
                imBiodataEntity.setMt(bean.getMt());
                imBiodataEntity.setPin(bean.getPin());
                imBiodataEntity.setNamaBank(bean.getNamaBank());
                imBiodataEntity.setNoRekBank(bean.getNoRekBank());

                imBiodataEntity.setFlag(bean.getFlag());
                imBiodataEntity.setAction(bean.getAction());
                imBiodataEntity.setCreatedWho(bean.getCreatedWho());
                imBiodataEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBiodataEntity.setCreatedDate(bean.getCreatedDate());
                imBiodataEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    personilPositionDao.addAndSave(itPersonilPositionEntity);
                    biodataDao.addAndSave(imBiodataEntity);
                } catch (HibernateException e) {
                    logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Biodata, please info to your admin..." + e.getMessage());
                }


                //save flag tunjangan2 jabatan
                String idTunjLain = tunjLainPegawaiDao.getNextTunjLain();
                ItTunjLainPegawaiEntity tunjanganentity = new ItTunjLainPegawaiEntity();
                tunjanganentity.setTunjLainId(idTunjLain);
                tunjanganentity.setNip(bean.getNip());
                if (!bean.getFlagTunjSupervisi().equalsIgnoreCase("")){
                    tunjanganentity.setFlagTunjSupervisi("Y");
                }else{
                    tunjanganentity.setFlagTunjSupervisi("N");
                }
                if (!bean.getFlagTunjLokasi().equalsIgnoreCase("")){
                    tunjanganentity.setFlagTunjLokasi("Y");
                }else{
                    tunjanganentity.setFlagTunjLokasi("N");
                }
                if (!bean.getFlagTunjSiaga().equalsIgnoreCase("")){
                    tunjanganentity.setFlagTunjSiaga("Y");
                }else{
                    tunjanganentity.setFlagTunjSiaga("N");
                }
                if (!bean.getFlagTunjProfesional().equalsIgnoreCase("")){
                    tunjanganentity.setFlagTunjProfesional("Y");
                }else{
                    tunjanganentity.setFlagTunjProfesional("N");
                }

                tunjanganentity.setFlag(bean.getFlag());
                tunjanganentity.setAction(bean.getAction());
                tunjanganentity.setCreatedWho(bean.getCreatedWho());
                tunjanganentity.setLastUpdateWho(bean.getLastUpdateWho());
                tunjanganentity.setCreatedDate(bean.getCreatedDate());
                tunjanganentity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    tunjLainPegawaiDao.addAndSave(tunjanganentity);
                } catch (HibernateException e) {
                    logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Biodata, please info to your admin..." + e.getMessage());
                }


                HttpSession session = ServletActionContext.getRequest().getSession();
                List<Keluarga> listKeluarga = (List<Keluarga>) session.getAttribute("listKeluarga");
                if(listKeluarga != null){
                    for(Keluarga keluarga: listKeluarga){
                        ImKeluargaEntity imKeluargaEntity = new ImKeluargaEntity();
                        imKeluargaEntity.setKeluargaId(keluargaDao.getNextKeluargaId());
                        imKeluargaEntity.setNip(bean.getNip());
                        imKeluargaEntity.setName(keluarga.getName());
                        imKeluargaEntity.setStatusKeluarga(keluarga.getStatusKeluargaId());

                        imKeluargaEntity.setFlag(bean.getFlag());
                        imKeluargaEntity.setAction(bean.getAction());
                        imKeluargaEntity.setCreatedWho(bean.getCreatedWho());
                        imKeluargaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imKeluargaEntity.setCreatedDate(bean.getCreatedDate());
                        imKeluargaEntity.setLastUpdate(bean.getLastUpdate());
                        try {
                            keluargaDao.addAndSave(imKeluargaEntity);
                        } catch (HibernateException e) {
                            logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data Biodata, please info to your admin..." + e.getMessage());
                        }
                    }
                }

                List<Study> listStudy = (List<Study>) session.getAttribute("listStudy");
                if(listStudy != null){
                    for(Study study: listStudy){
                        ImStudyEntity imStudyEntity = new ImStudyEntity();
                        imStudyEntity.setStudyId(studyDao.getNextStudyId());
                        imStudyEntity.setNip(bean.getNip());
                        imStudyEntity.setTypeStudy(study.getTypeStudy());
                        imStudyEntity.setStudyName(study.getStudyName());
                        imStudyEntity.setTahunAwal(study.getTahunAwal());
                        imStudyEntity.setTahunAkhir(study.getTahunAkhir());

                        imStudyEntity.setFlag(bean.getFlag());
                        imStudyEntity.setAction(bean.getAction());
                        imStudyEntity.setCreatedWho(bean.getCreatedWho());
                        imStudyEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imStudyEntity.setCreatedDate(bean.getCreatedDate());
                        imStudyEntity.setLastUpdate(bean.getLastUpdate());
                        try {
                            studyDao.addAndSave(imStudyEntity);
                        } catch (HibernateException e) {
                            logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data Biodata, please info to your admin..." + e.getMessage());
                        }
                    }
                }

                List<PengalamanKerja> listPengalamanKerja = (List<PengalamanKerja>) session.getAttribute("listPengalamanKerja");
                if(listPengalamanKerja != null){
                    for(PengalamanKerja pengalamanKerja: listPengalamanKerja){
                        ImPengalamanKerjaEntity imPengalamanKerjaEntity = new ImPengalamanKerjaEntity();
                        imPengalamanKerjaEntity.setPengalamanId(pengalamanKerjaDao.getNextPengalamanKerja());
                        imPengalamanKerjaEntity.setNip(bean.getNip());
                        imPengalamanKerjaEntity.setNamaPerusahaan(pengalamanKerja.getNamaPerusahaan());
                        imPengalamanKerjaEntity.setJabatan(pengalamanKerja.getJabatan());
                        if(pengalamanKerja.getStTahunKeluar() != null && !"".equalsIgnoreCase(pengalamanKerja.getStTahunKeluar()) &&
                                pengalamanKerja.getStTtahunMasuk() != null && !"".equalsIgnoreCase(pengalamanKerja.getStTtahunMasuk())){
                            imPengalamanKerjaEntity.setTahunMasuk(CommonUtil.convertStringToDate(pengalamanKerja.getStTtahunMasuk()));
                            imPengalamanKerjaEntity.setTahunKeluar(CommonUtil.convertStringToDate(pengalamanKerja.getStTahunKeluar()));
                        }

                        imPengalamanKerjaEntity.setFlag(bean.getFlag());
                        imPengalamanKerjaEntity.setAction(bean.getAction());
                        imPengalamanKerjaEntity.setCreatedWho(bean.getCreatedWho());
                        imPengalamanKerjaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imPengalamanKerjaEntity.setCreatedDate(bean.getCreatedDate());
                        imPengalamanKerjaEntity.setLastUpdate(bean.getLastUpdate());
                        try {
                            pengalamanKerjaDao.addAndSave(imPengalamanKerjaEntity);
                        } catch (HibernateException e) {
                            logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data Biodata, please info to your admin..." + e.getMessage());
                        }
                    }
                }

                List<Reward> listReward = (List<Reward>) session.getAttribute("listReward");
                if(listReward != null){
                    for(Reward reward: listReward){
                        ImRewardEntity imRewardEntity = new ImRewardEntity();
                        imRewardEntity.setRewardId(rewardDao.getNextReward());
                        imRewardEntity.setNip(bean.getNip());
                        imRewardEntity.setJenis(reward.getJenis());
                        imRewardEntity.setKeterangan(reward.getKeterangan());
                        if(reward.getStTanggal() != null && !"".equalsIgnoreCase(reward.getStTanggal())){
                            imRewardEntity.setTanggal(CommonUtil.convertStringToDate(reward.getStTanggal()));
                        }

                        imRewardEntity.setFlag(bean.getFlag());
                        imRewardEntity.setAction(bean.getAction());
                        imRewardEntity.setCreatedWho(bean.getCreatedWho());
                        imRewardEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imRewardEntity.setCreatedDate(bean.getCreatedDate());
                        imRewardEntity.setLastUpdate(bean.getLastUpdate());
                        try {
                            rewardDao.addAndSave(imRewardEntity);
                        } catch (HibernateException e) {
                            logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data Biodata, please info to your admin..." + e.getMessage());
                        }
                    }
                }

                List<Sertifikat> listSertifikat = (List<Sertifikat>) session.getAttribute("listSertifikat");
                if(listSertifikat != null){
                    for(Sertifikat sertifikat: listSertifikat){
                        ImSertifikatEntity imSertifikatEntity = new ImSertifikatEntity();
                        imSertifikatEntity.setSertifikatId(sertifikatDao.getNextSertifikat());
                        imSertifikatEntity.setNip(bean.getNip());

                        imSertifikatEntity.setJenis(sertifikat.getJenis());
                        imSertifikatEntity.setNama(sertifikat.getNama());
                        imSertifikatEntity.setLembaga(sertifikat.getLembaga());
                        imSertifikatEntity.setTempatPelaksana(sertifikat.getTempatPelaksana());
                        imSertifikatEntity.setNilai(sertifikat.getNilai());
                        imSertifikatEntity.setLulus(sertifikat.getLulus());
                        imSertifikatEntity.setPrestasiGrade(sertifikat.getPrestasiGrade());

                        imSertifikatEntity.setTanggalPengesahan(sertifikat.getTanggalPengesahan());
                        imSertifikatEntity.setMasaBerlaku(sertifikat.getMasaBerlaku());
                        imSertifikatEntity.setMasaBerakhir(sertifikat.getMasaBerakhir());

                        imSertifikatEntity.setFlag(bean.getFlag());
                        imSertifikatEntity.setAction(bean.getAction());
                        imSertifikatEntity.setCreatedWho(bean.getCreatedWho());
                        imSertifikatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imSertifikatEntity.setCreatedDate(bean.getCreatedDate());
                        imSertifikatEntity.setLastUpdate(bean.getLastUpdate());
                        try {
                            sertifikatDao.addAndSave(imSertifikatEntity);
                        } catch (HibernateException e) {
                            logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data Biodata, please info to your admin..." + e.getMessage());
                        }
                    }
                }
            }else{
                throw new GeneralBOException("Maaf Nip, Atau No Ktp Sudah Ada, Harap Periksa Kembali");
            }
        }

        logger.info("[BiodataBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Biodata> getByCriteriaForRekruitmenPabrik(Biodata searchBean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Biodata> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();
            Map hsCriteria2 = new HashMap();

            if (searchBean.getDivisi() != null && !"".equalsIgnoreCase(searchBean.getDivisi())) {
                hsCriteria2.put("divisi_id", searchBean.getDivisi());
            }
            if (searchBean.getBranch() != null && !"".equalsIgnoreCase(searchBean.getBranch())) {
                hsCriteria2.put("branch_id", searchBean.getBranch());
            }

            if (searchBean.getFrom()!=null && !"".equalsIgnoreCase(searchBean.getFrom())){
                hsCriteria.put("from", searchBean.getFrom());
            }
            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                    hsCriteria2.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                    hsCriteria2.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
                hsCriteria2.put("flag", "Y");
            }

            List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
            List<ImBiodataEntity> imBiodataEntity = null;
            ItPersonilPositionEntity itPersonilPositionEntity = null;
            try {
                itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria2);
                imBiodataEntity = biodataDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.getSearchPersonalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if (itPersonilPositionEntities!=null){
                if(imBiodataEntity != null){
                    Biodata returnBiodata;
                    // Looping from dao to object and save in collection
                    for (ItPersonilPositionEntity personilPositionEntity : itPersonilPositionEntities){
                        for(ImBiodataEntity personalEntity : imBiodataEntity){
                            if (personilPositionEntity.getNip().equals(personalEntity.getNip())){
                                returnBiodata = new Biodata();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                if(personalEntity.getTanggalLahir() != null){
                                    String stringTanggal  = dateFormat.format(personalEntity.getTanggalLahir());
                                    returnBiodata.setStTanggalLahir(stringTanggal);
                                }else{
                                    returnBiodata.setStTanggalLahir("");
                                }

                                returnBiodata.setNip(personalEntity.getNip());
                                returnBiodata.setNamaPegawai(personalEntity.getNamaPegawai());
                                returnBiodata.setGelarDepan(personalEntity.getGelarDepan());
                                returnBiodata.setGelarBelakang(personalEntity.getGelarBelakang());
                                returnBiodata.setNoKtp(personalEntity.getNoKtp());
                                returnBiodata.setAlamat(personalEntity.getAlamat());
                                returnBiodata.setRtRw(personalEntity.getRtRw());
                                returnBiodata.setNoTelp(personalEntity.getNoTelp());
                                returnBiodata.setTanggalLahir(personalEntity.getTanggalLahir());
                                returnBiodata.setTempatLahir(personalEntity.getTempatLahir());
                                returnBiodata.setTipePegawai(personalEntity.getTipePegawai());
                                returnBiodata.setFotoUpload(personalEntity.getFotoUpload());
                                returnBiodata.setStatusCaption(personalEntity.getStatusCaption());
                                returnBiodata.setKeterangan(personalEntity.getKeterangan());

                                itPersonilPositionEntity = personilPositionDao.getById("nip",personalEntity.getNip(),"Y" );

                                if(itPersonilPositionEntity.getImDepartmentEntity() != null){
                                    returnBiodata.setDivisi(itPersonilPositionEntity.getImDepartmentEntity().getDepartmentId());
                                }else{
                                    returnBiodata.setDivisi("");
                                }
                                hsCriteria = new HashMap();
                                hsCriteria.put("department_id",returnBiodata.getDivisi());
                                hsCriteria.put("flag","Y");
                                List<ImDepartmentEntity> imDepartmentEntityList = departmentDao.getByCriteria(hsCriteria);
                                for (ImDepartmentEntity imDepartmentEntity : imDepartmentEntityList){
                                    returnBiodata.setDivisiName(imDepartmentEntity.getDepartmentName());
                                }

                                if(itPersonilPositionEntity.getImPosition() != null){
                                    returnBiodata.setPositionId(itPersonilPositionEntity.getImPosition().getPositionId());
                                    returnBiodata.setPositionId2(itPersonilPositionEntity.getImPosition().getPositionId());
                                }else{
                                    returnBiodata.setPositionId("");
                                    returnBiodata.setPositionId2("");
                                }
                                hsCriteria = new HashMap();
                                hsCriteria.put("position_id",returnBiodata.getPositionId());
                                hsCriteria.put("flag","Y");
                                List<ImPosition> imPositionList = positionDao.getByCriteria(hsCriteria);
                                for (ImPosition imPosition : imPositionList){
                                    returnBiodata.setPositionName(imPosition.getPositionName());
                                }
                                returnBiodata.setBranch(itPersonilPositionEntity.getImBranches().getPrimaryKey().getId());

                                if (personalEntity.getMasaGiling()!=null){
                                    returnBiodata.setMasaGiling(personalEntity.getMasaGiling());
                                }else {
                                    returnBiodata.setMasaGiling("");
                                }
                                returnBiodata.setStatusPegawai(personalEntity.getStatusPegawai());
                                returnBiodata.setStatusKeluarga(personalEntity.getStatusKeluarga());
                                returnBiodata.setStatusKeluarga(personalEntity.getStatusKeluarga());


                                ImPosition positionList = positionDao.getById("positionId", returnBiodata.getPositionId());
                                if(positionList != null){
                                    returnBiodata.setPositionName(positionList.getPositionName());
                                    if(positionList.getDepartmentId() != null){
                                        returnBiodata.setDivisiName(positionList.getImDepartmentEntity().getDepartmentName());
                                        ImPositionBagianEntity positionBagianEntity = new ImPositionBagianEntity();
                                        positionBagianEntity = positionBagianDao.getById("bagianId",positionList.getBagianId());
                                        returnBiodata.setBagianId(positionBagianEntity.getBagianId());
                                        returnBiodata.setBagianName(positionBagianEntity.getBagianName());
                                    }
                                }

                                if(personalEntity.getTipePegawai() != null){
                                    returnBiodata.setTipePegawaiName(personalEntity.getImHrisTipePegawai().getTipePegawaiName());
                                }else{
                                    returnBiodata.setTipePegawaiName("");
                                }

                                returnBiodata.setCreatedWho(personalEntity.getCreatedWho());
                                returnBiodata.setCreatedDate(personalEntity.getCreatedDate());
                                returnBiodata.setLastUpdate(personalEntity.getLastUpdate());
                                returnBiodata.setAction(personalEntity.getAction());
                                returnBiodata.setFlag(personalEntity.getFlag());
                                returnBiodata.setStatusPegawai(personalEntity.getStatusPegawai());
                                listOfResult.add(returnBiodata);
                            }
                        }
                    }
                }
            }
        }
        logger.info("[BiodataBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public Biodata getBiodataRekruitmen(Biodata searchBean) throws GeneralBOException {
        ImBiodataEntity biodataEntity = new ImBiodataEntity();
        Biodata result = new Biodata();
        biodataEntity = biodataDao.getById("nip",searchBean.getNip());

        result.setNip(biodataEntity.getNip());
        result.setNamaPegawai(biodataEntity.getNamaPegawai());
        return result;
    }
    @Override
    public Biodata getShift(Biodata searchBean) throws GeneralBOException {
        ImBiodataEntity biodataEntity = new ImBiodataEntity();
        Biodata result = new Biodata();
        biodataEntity = biodataDao.getById("nip",searchBean.getNip());

        result.setNip(biodataEntity.getNip());
        result.setShift(biodataEntity.getShift());
        return result;
    }
    @Override
    public List<Biodata> getPegawaiMess() throws GeneralBOException {
        List<ImBiodataEntity> biodataEntityList = new ArrayList<>();
        List<Biodata> biodataList= new ArrayList<>();

        biodataEntityList=biodataDao.getPegawaiMess();

        for (ImBiodataEntity biodataEntity : biodataEntityList){
            Biodata biodata = new Biodata();
            biodata.setNip(biodataEntity.getNip());

            biodataList.add(biodata);
        }
        return biodataList;
    }
    @Override
    public List<Biodata> getByCriteria(Biodata searchBean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Biodata> listOfResult = new ArrayList();

        if (searchBean != null) {
            List<ImBiodataEntity> imBiodataEntity = null;
            ItPersonilPositionEntity itPersonilPositionEntity = null;
            try {
                imBiodataEntity = biodataDao.getDataBiodata(searchBean.getNip(), searchBean.getNamaPegawai(), searchBean.getBranch(), searchBean.getDivisi(),
                        searchBean.getTipePegawai(), searchBean.getFlag());
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.getSearchPersonalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imBiodataEntity != null){
                Biodata returnBiodata;
                // Looping from dao to object and save in collection
                for(ImBiodataEntity personalEntity : imBiodataEntity){
                    returnBiodata = new Biodata();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    if(personalEntity.getTanggalLahir() != null){
                        String stringTanggal  = dateFormat.format(personalEntity.getTanggalLahir());
                        returnBiodata.setStTanggalLahir(stringTanggal);
                    }else{
                        returnBiodata.setStTanggalLahir("");
                    }
                    if(personalEntity.getTanggalPensiun() != null){
                        String stringTanggal  = dateFormat.format(personalEntity.getTanggalPensiun());
                        returnBiodata.setStTanggalPensiun(stringTanggal);
                    }else{
                        returnBiodata.setStTanggalPensiun("");
                    }
                    if(personalEntity.getTanggalMasuk() != null){
                        String stringTanggal  = dateFormat.format(personalEntity.getTanggalMasuk());
                        returnBiodata.setStTanggalMasuk(stringTanggal);
                    }else{
                        returnBiodata.setStTanggalMasuk("");
                    }

                    if(personalEntity.getTanggalAktif() != null){
                        String stringTanggal  = dateFormat.format(personalEntity.getTanggalAktif());
                        returnBiodata.setStTanggalAktif(stringTanggal);
                    }else{
                        returnBiodata.setStTanggalAktif("");
                    }

                    returnBiodata.setNip(personalEntity.getNip());
                    returnBiodata.setNamaPegawai(personalEntity.getNamaPegawai());
                    returnBiodata.setGelarDepan(personalEntity.getGelarDepan());
                    returnBiodata.setGelarBelakang(personalEntity.getGelarBelakang());
                    returnBiodata.setNoKtp(personalEntity.getNoKtp());
                    returnBiodata.setAlamat(personalEntity.getAlamat());
                    returnBiodata.setRtRw(personalEntity.getRtRw());
                    returnBiodata.setNoTelp(personalEntity.getNoTelp());
                    returnBiodata.setTanggalLahir(personalEntity.getTanggalLahir());
                    returnBiodata.setTempatLahir(personalEntity.getTempatLahir());
                    returnBiodata.setTipePegawai(personalEntity.getTipePegawai());
                    returnBiodata.setFotoUpload(personalEntity.getFotoUpload());
                    returnBiodata.setStatusCaption(personalEntity.getStatusCaption());
                    returnBiodata.setKeterangan(personalEntity.getKeterangan());
                    returnBiodata.setStMasaKerjaGol(String.valueOf(personalEntity.getMasaKerjaGolongan()));
                    returnBiodata.setMasaKerjaGolongan(personalEntity.getMasaKerjaGolongan());
                    returnBiodata.setGolonganDapenId(personalEntity.getGolonganDapenId());
                    returnBiodata.setProfesiId(personalEntity.getProfesiId());
                    List<ImProfesiEntity> listOfProfesi = new ArrayList<>();
                    if (personalEntity.getProfesiId()!=null){
                        if (!personalEntity.getProfesiId().equalsIgnoreCase("")){
                            listOfProfesi = profesiDao.getProfesiById(personalEntity.getProfesiId());
                            if (listOfProfesi.size()>0){
                                for (ImProfesiEntity profesiLoop: listOfProfesi){
                                    returnBiodata.setProfesiName(profesiLoop.getProfesiName());
                                }
                            }else{
                                returnBiodata.setProfesiName("-");
                            }
                        }else{
                            returnBiodata.setProfesiName("-");
                        }
                    }else{
                        returnBiodata.setProfesiName("-");
                    }

                    itPersonilPositionEntity = personilPositionDao.getById("nip",personalEntity.getNip(),"Y" );
                    Map hsCriteria2 = new HashMap();
                    if(itPersonilPositionEntity != null){
                        if(itPersonilPositionEntity.getImPosition() != null){
                            returnBiodata.setPositionId(itPersonilPositionEntity.getImPosition().getPositionId());
                            returnBiodata.setPositionId2(itPersonilPositionEntity.getImPosition().getPositionId());
                        }else{
                            returnBiodata.setPositionId("");
                            returnBiodata.setPositionId2("");
                        }
                        returnBiodata.setBranch(itPersonilPositionEntity.getImBranches().getPrimaryKey().getId());
                        returnBiodata.setPjs(itPersonilPositionEntity.getPjs());
                    }else{
                        returnBiodata.setPositionId("");
                    }

                    hsCriteria2 = new HashMap();
                    hsCriteria2.put("position_id",returnBiodata.getPositionId());
                    hsCriteria2.put("flag","Y");
                    List<ImPosition> positionList = positionDao.getByCriteria(hsCriteria2);
                    for(ImPosition imPosition : positionList){
                        returnBiodata.setPositionName(imPosition.getPositionName());
                        returnBiodata.setDivisi(imPosition.getDepartmentId());
                        returnBiodata.setBagianId(imPosition.getBagianId());

                        if(imPosition.getBagianId() != null){
                            ImPositionBagianEntity positionBagianEntity = positionBagianDao.getById("bagianId",returnBiodata.getBagianId());
                            returnBiodata.setBagianName(positionBagianEntity.getBagianName());
                        }else{
                            returnBiodata.setBagianName("-");
                        }
                    }

                    ImPosition positionList2 = positionDao.getById("positionId", returnBiodata.getPositionId());
                    if(positionList2 != null){
                        returnBiodata.setPositionName(positionList2.getPositionName());
                        if(positionList2.getDepartmentId() != null){
                            returnBiodata.setDivisiName(positionList2.getImDepartmentEntity().getDepartmentName());
                        }
                    }
                    if(personalEntity.getImGolonganEntity() != null){
                        returnBiodata.setGolonganName(personalEntity.getImGolonganEntity().getGolonganName());
                        returnBiodata.setGolongan(personalEntity.getGolongan());
                    }else{
                        returnBiodata.setGolongan(personalEntity.getGolongan());
                    }

                    List<ImBranches> branch = branchDao.getListBranchById(returnBiodata.getBranch());
                    for (ImBranches imBranches:branch){
                        returnBiodata.setBranchName(imBranches.getBranchName());
                        break;
                    }

                    returnBiodata.setPoint(personalEntity.getPoint());
                    returnBiodata.setPoinLebih(personalEntity.getPoinLebih());
                    returnBiodata.setMasaGiling(personalEntity.getMasaGiling());
                    returnBiodata.setStatusPegawai(personalEntity.getStatusPegawai());
                    returnBiodata.setStatusKeluarga(personalEntity.getStatusKeluarga());

                    returnBiodata.setProvinsiId(personalEntity.getProvinsiId());
                    returnBiodata.setProvinsiName(personalEntity.getProvinsiName());

                    returnBiodata.setKabupatenId(personalEntity.getKotaId());
                    returnBiodata.setKotaName(personalEntity.getKotaName());

                    returnBiodata.setKecamatanId(personalEntity.getKecamatanId());
                    returnBiodata.setKecamatanName(personalEntity.getKecamatanName());

                    returnBiodata.setDesaId(personalEntity.getDesaId());
                    returnBiodata.setDesaName(personalEntity.getDesaName());

                    if (returnBiodata.getTipePegawai().equalsIgnoreCase("KS")){
                        returnBiodata.setTipePegawaiName("Karyawan Staff");
                    }else if (returnBiodata.getTipePegawai().equalsIgnoreCase("KNS")){
                        returnBiodata.setTipePegawaiName("Karyawan Non Staff");
                    }
                    returnBiodata.setFotoUpload(personalEntity.getFotoUpload());
                    returnBiodata.setMasaGiling(personalEntity.getMasaGiling());
                    returnBiodata.setMt(personalEntity.getMt());
                    returnBiodata.setPin(personalEntity.getPin());
                    returnBiodata.setKotaName(personalEntity.getKotaName());
                    returnBiodata.setKecamatanName(personalEntity.getKecamatanName());
                    returnBiodata.setDesaName(personalEntity.getDesaName());
                    returnBiodata.setGolonganId(personalEntity.getGolongan());
                    returnBiodata.setFlagZakat(personalEntity.getZakatProfesi());
                    returnBiodata.setGender(personalEntity.getGender());
                    returnBiodata.setNpwp(personalEntity.getNpwp());
                    returnBiodata.setGaji(personalEntity.getGaji().toPlainString());
                    returnBiodata.setStrukturGaji(personalEntity.getStrukturGaji());
                    returnBiodata.setStrukturGaji2(personalEntity.getStrukturGaji());
                    returnBiodata.setStatusGiling(personalEntity.getStatusGiling());
                    returnBiodata.setJumlahAnak(personalEntity.getJumlahAnak());
                    returnBiodata.setDanaPensiun(personalEntity.getDanaPensiun());
                    returnBiodata.setNoAnggotaDapen(personalEntity.getNoAnggotaDapen());
                    returnBiodata.setNoBpjsKetenagakerjaan(personalEntity.getNoBpjsKetenagakerjaan());
                    returnBiodata.setNoBpjsKetenagakerjaanPensiun(personalEntity.getNoBpjsKetenagakerjaanPensiun());
                    returnBiodata.setNoBpjsKesehatan(personalEntity.getNoBpjsKesehatan());
                    returnBiodata.setAgama(personalEntity.getAgama());

                    returnBiodata.setNamaBank(personalEntity.getNamaBank());
                    returnBiodata.setNoRekBank(personalEntity.getNoRekBank());
                    returnBiodata.setCabangBank(personalEntity.getCabangBank());

                    returnBiodata.setCreatedWho(personalEntity.getCreatedWho());
                    returnBiodata.setCreatedDate(personalEntity.getCreatedDate());
                    returnBiodata.setLastUpdate(personalEntity.getLastUpdate());
                    returnBiodata.setAction(personalEntity.getAction());
                    returnBiodata.setFlag(personalEntity.getFlag());
                    returnBiodata.setStatusPegawai(personalEntity.getStatusPegawai());
                    if(personalEntity.getFotoUpload() != null){
                        returnBiodata.setPathFoto(ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD
                                + personalEntity.getFotoUpload());
                    }else{
                        returnBiodata.setPathFoto(ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD
                                + "unknown-person2.jpg");
                    }
                    listOfResult.add(returnBiodata);
                }
            }
        }

        logger.info("[BiodataBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Biodata> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Biodata> getComboBiodataWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Biodata> listComboBiodata = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBiodataEntity> listPersonal = null;
        try {
            listPersonal = biodataDao.getListPersonal(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImBiodataEntity imBiodataEntity : listPersonal) {
                Biodata itemComboBiodata = new Biodata();
                itemComboBiodata.setNip(imBiodataEntity.getNip());
                itemComboBiodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                listComboBiodata.add(itemComboBiodata);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboBiodata;
    }
    @Override
    public List<Biodata> getSatpam() throws GeneralBOException {
        logger.info("[UserBoImpl.getSatpam] start process >>>");

        List<Biodata> listComboBiodata = new ArrayList();

        List<ImBiodataEntity> listPersonal = null;
        try {
            listPersonal = biodataDao.getListPersonalSatpam();
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getSatpam] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list satpam with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImBiodataEntity imBiodataEntity : listPersonal) {
                Biodata itemComboBiodata = new Biodata();
                itemComboBiodata.setNip(imBiodataEntity.getNip());
                itemComboBiodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                listComboBiodata.add(itemComboBiodata);
            }
        }
        logger.info("[UserBoImpl.getSatpam] end process <<<");
        return listComboBiodata;
    }
    @Override
    public Biodata getKabagSdm() throws GeneralBOException {
        logger.info("[BiodataBoImpl.getKabagSdm] start process >>>");

        Biodata result = new Biodata();

        try {
            result = biodataDao.getBiodataKabagSdm("KD01");
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.getKabagSdm] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list satpam with criteria, please info to your admin..." + e.getMessage());
        }

        logger.info("[BiodataBoImpl.getKabagSdm] end process <<<");
        return result;
    }

    @Override
    public Biodata getDirekturUtama() throws GeneralBOException {
        logger.info("[BiodataBoImpl.getDirekturUtama] start process >>>");

        Biodata result = new Biodata();

        try {
            result = biodataDao.getBiodataDirektur("KD01");
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.getDirekturUtama] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list satpam with criteria, please info to your admin..." + e.getMessage());
        }

        logger.info("[BiodataBoImpl.getDirekturUtama] end process <<<");
        return result;
    }

    @Override
    public List<Biodata> getListOfPersonil(String query, String branchId) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Biodata> listComboBiodata = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBiodataEntity> listPersonal = null;
        try {
            if (!"".equalsIgnoreCase(branchId)){
                listPersonal = biodataDao.getDataBiodata("",query,branchId,"","","Y");
            } else {
                listPersonal = biodataDao.getDataBiodata("",query,"","","","Y");
            }
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImBiodataEntity imBiodataEntity : listPersonal) {
                Biodata itemComboBiodata = new Biodata();
                String date = "";
                if(imBiodataEntity.getTanggalAktif() != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = dateFormat.format(imBiodataEntity.getTanggalAktif());
                    itemComboBiodata.setStTanggalAktif(date);
                }
                itemComboBiodata.setNip(imBiodataEntity.getNip());
                itemComboBiodata.setGolongan(imBiodataEntity.getGolongan());
                itemComboBiodata.setTipePegawai(imBiodataEntity.getTipePegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setPoint(imBiodataEntity.getPoint());

                itemComboBiodata.setPositionId2(imBiodataEntity.getPosisiId());
                itemComboBiodata.setBranch(imBiodataEntity.getBranchId());
                itemComboBiodata.setPjs(imBiodataEntity.getPjs());
                itemComboBiodata.setPositionId(imBiodataEntity.getPosisiId());
                itemComboBiodata.setDivisi(imBiodataEntity.getDivisiId());
                itemComboBiodata.setProfesiId(imBiodataEntity.getProfesiId());
                itemComboBiodata.setBagianId(imBiodataEntity.getBagianId());

                if (itemComboBiodata.getDivisi()==null){
                    itemComboBiodata.setDivisi("");
                }

                if (itemComboBiodata.getBagianId()==null){
                    itemComboBiodata.setBagianId("");
                }

//                itemComboBiodata.setDivisi(imBiodataEntity.getDivisi());
                itemComboBiodata.setTipePegawai(imBiodataEntity.getTipePegawai());
                itemComboBiodata.setMasaGiling(imBiodataEntity.getMasaGiling());
                itemComboBiodata.setTanggalAktif(imBiodataEntity.getTanggalAktif());
                itemComboBiodata.setGolonganId(imBiodataEntity.getGolongan());
//                itemComboBiodata.setPositionId(imBiodataEntity.getPositionId());
                listComboBiodata.add(itemComboBiodata);

            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboBiodata;
    }

    @Override
    public List<Biodata> getListOfPersonilForSmk(String query, String periode) throws GeneralBOException {
        logger.info("[BiodataBoImpl.getListOfPersonilForSmk] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("historyJabatanForSmk");
        session.removeAttribute("branchForSmk");
        session.removeAttribute("positionIdForSmk");

        List<Biodata> listComboBiodata = new ArrayList();

        List<ImBiodataEntity> listPersonal = null;
        try {
            listPersonal = biodataDao.getDataBiodata(query);
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.getListOfPersonilForSmk] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImBiodataEntity imBiodataEntity : listPersonal) {
                Biodata itemComboBiodata = new Biodata();
                List<ImtHrisHistoryJabatanPegawaiEntity> historyJabatan = historyJabatanPegawaiDao.getDataHistoryJabatan(imBiodataEntity.getNip());
                session.setAttribute("historyJabatanForSmk", historyJabatan);

                if(historyJabatan.size() > 0){
                    for(ImtHrisHistoryJabatanPegawaiEntity imtHrisHistoryJabatanPegawaiEntity: historyJabatan){
                        itemComboBiodata.setBranch(imtHrisHistoryJabatanPegawaiEntity.getBranchId());
                        // Flag Pjs
                        if(imtHrisHistoryJabatanPegawaiEntity.getPjsFlag() != null){
                            if(!imtHrisHistoryJabatanPegawaiEntity.getPjsFlag().equalsIgnoreCase("")){
                                itemComboBiodata.setPjs(imtHrisHistoryJabatanPegawaiEntity.getPjsFlag());
                            }else{
                                itemComboBiodata.setPjs("");
                            }
                        }else{
                            itemComboBiodata.setPjs("");
                        }

                        itemComboBiodata.setPositionId(imtHrisHistoryJabatanPegawaiEntity.getPositionId());
                        if(imtHrisHistoryJabatanPegawaiEntity.getImPosition() != null){
                            itemComboBiodata.setPositionId2(imtHrisHistoryJabatanPegawaiEntity.getPositionId());
                            itemComboBiodata.setBagianId(imtHrisHistoryJabatanPegawaiEntity.getImPosition().getBagianId());

                            if (itemComboBiodata.getBagianId() == null || itemComboBiodata.getBagianId().equalsIgnoreCase("")){
                                itemComboBiodata.setBagianId("-");
                                //itemComboBiodata.setBagianId(imBiodataEntity.getBagianId());
                            }
                            itemComboBiodata.setDivisi(imtHrisHistoryJabatanPegawaiEntity.getDivisiId());
                            if (itemComboBiodata.getDivisi() == null || itemComboBiodata.getDivisi().equalsIgnoreCase("")){
                                itemComboBiodata.setDivisi("-");
                            }
                        }else{
                            itemComboBiodata.setPositionId2(imBiodataEntity.getPosisiId());
                            itemComboBiodata.setBranch(imBiodataEntity.getBranchId());
                            itemComboBiodata.setPjs(imBiodataEntity.getPjs());
                            itemComboBiodata.setPositionId(imBiodataEntity.getPosisiId());
                            itemComboBiodata.setBagianId(imBiodataEntity.getBagianId());
                            itemComboBiodata.setDivisi(imBiodataEntity.getDivisiId());
                        }

                    }
                }else{
                    itemComboBiodata.setPositionId2(imBiodataEntity.getPosisiId());
                    itemComboBiodata.setBranch(imBiodataEntity.getBranchId());
                    itemComboBiodata.setPjs(imBiodataEntity.getPjs());
                    itemComboBiodata.setPositionId(imBiodataEntity.getPosisiId());
                    itemComboBiodata.setBagianId(imBiodataEntity.getBagianId());
                    itemComboBiodata.setDivisi(imBiodataEntity.getDivisiId());
                }

                String date = "";

                itemComboBiodata.setNip(imBiodataEntity.getNip());
                itemComboBiodata.setGolongan(imBiodataEntity.getGolongan());
                itemComboBiodata.setGolonganId(imBiodataEntity.getGolongan());
                itemComboBiodata.setTipePegawai(imBiodataEntity.getTipePegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setPoint(imBiodataEntity.getPoint());

                listComboBiodata.add(itemComboBiodata);
            }
        }
        logger.info("[BiodataBoImpl.getListOfPersonilForSmk] end process <<<");
        return listComboBiodata;
    }

    public List<Biodata> getListOfPersonilPosition(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Biodata> listComboBiodata = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBiodataEntity> listPersonal = null;
        try {
            listPersonal = biodataDao.getListPersonalByBranch2(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImBiodataEntity imBiodataEntity : listPersonal) {
                Biodata itemComboBiodata = new Biodata();
                itemComboBiodata.setNip(imBiodataEntity.getNip());
                itemComboBiodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                ItPersonilPositionEntity itPersonilPositionEntity = (ItPersonilPositionEntity) personilPositionDao.getById("nip",imBiodataEntity.getNip(),"Y");
                itemComboBiodata.setBranch(itPersonilPositionEntity.getBranchId());
                itemComboBiodata.setPositionId(itPersonilPositionEntity.getPositionId());
                itemComboBiodata.setPositionId2(itPersonilPositionEntity.getPositionId());
                if(itPersonilPositionEntity.getImPosition().getDepartmentId() != null){
                    itemComboBiodata.setDivisi(itPersonilPositionEntity.getImPosition().getDepartmentId());
                }
                listComboBiodata.add(itemComboBiodata);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboBiodata;
    }

    @Override
    public List<TrainingPerson> getListTrainingPerson(TrainingPerson bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.getListTrainingPerson] start process <<<");
        List<TrainingPerson> result = new ArrayList<TrainingPerson>();
        if (bean != null){
            Map hsCriteria = new HashMap();
            if (bean.getPersonId() != null && !"".equalsIgnoreCase(bean.getPersonId())){
                hsCriteria.put("person_id", bean.getPersonId());
                hsCriteria.put("flag", bean.getFlag());
            }

            List<ItHrisTrainingPersonEntity> trainingPersonEntities;

            try {
                trainingPersonEntities = trainingPersonDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }

            if (trainingPersonEntities != null){
                TrainingPerson addData;
                for (ItHrisTrainingPersonEntity listData : trainingPersonEntities){
                    if (listData.getApprovalFlag() != null && listData.getApprovalBosFlag() != null){
                        addData = new TrainingPerson();
                        addData.setTrainingPersonId(listData.getTrainingPersonId());
                        addData.setTrainingId(listData.getTrainingId());
                        addData.setPersonId(listData.getPersonId());
                        addData.setPersonName(listData.getPersonName());
                        addData.setDivisiId(listData.getDivisiId());
                        addData.setApprovalId(listData.getApprovalId());
                        addData.setApprovalName(listData.getApprovalName());
                        addData.setApprovalDate(listData.getApprovalDate());
                        addData.setApprovalFlag(listData.getApprovalFlag());
                        addData.setNotApprovalNote(listData.getNotApprovalNote());
                        addData.setApprovalBosId(listData.getApprovalBosId());
                        addData.setApprovalBosName(listData.getApprovalBosName());
                        addData.setApprovalBosDate(listData.getApprovalBosDate());
                        addData.setApprovalBosFlag(listData.getApprovalBosFlag());
                        addData.setNotApprovalBosNote(listData.getNotApprovalBosNote());
                        addData.setCreatedDate(listData.getCreatedDate());
                        addData.setCreatedWho(listData.getCreateDateWho());
                        addData.setLastUpdate(listData.getLastUpdate());
                        addData.setLastUpdateWho(listData.getLastUpdateWho());
                        addData.setFlag(listData.getFlag());
                        addData.setAction(listData.getAction());

                        if (listData.getTrainingId() != null){
                            hsCriteria = new HashMap();
                            hsCriteria.put("training_id",listData.getTrainingId());
                            hsCriteria.put("flag","Y");
                            List<ItHrisTrainingEntity> dataTraining;
                            try {
                                dataTraining = trainingDao.getByCriteria(hsCriteria);
                            } catch (HibernateException e) {
                                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                            }

                            if (dataTraining != null){
                                for (ItHrisTrainingEntity listTraining : dataTraining){
                                    addData.setInstansi(listTraining.getInstansi());
                                    addData.setTipeTraining(listTraining.getTipeTraining());
                                    addData.setTrainingName(listTraining.getTrainingName());
                                    addData.setTrainingStartdate(listTraining.getTrainingStartDate());
                                    addData.setTrainingEndDate(listTraining.getTrainingEndDate());

                                    if (listTraining.getTrainingStartDate()!=null){
                                        addData.setStTrainingStartdate(CommonUtil.convertDateToString(listTraining.getTrainingStartDate()));
                                    }
                                    if (listTraining.getTrainingEndDate()!=null){
                                        addData.setStTrainingEndDate(CommonUtil.convertDateToString(listTraining.getTrainingEndDate()));
                                    }
                                }
                            }
                        }
                        result.add(addData);
                    }
                }
            }
        }

        logger.info("[BiodataBoImpl.getListTrainingPerson] end process <<<");

        return result;
    }

    @Override
    public List<Biodata> getListOfRsKelas(String query, String status, String nip, String golonganId, String statusRawat) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Biodata> result = new ArrayList<Biodata>();

        List<Biodata> listComboBiodata = new ArrayList();
        String criteria = "%" + query + "%";

        boolean notFound = false;
        String kelompokId = "";
        if ("RI".equalsIgnoreCase(statusRawat)){
            Map hsCriteria = new HashMap();
            hsCriteria.put("nip",nip);
            hsCriteria.put("flag","Y");
            List<ImBiodataEntity> listBiodata = null;
            try {
                listBiodata = biodataDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
            String id = "";
            if (listBiodata != null){
                for (ImBiodataEntity listBio :listBiodata){
                    id = listBio.getNip();
                }
            }
            if (nip != ""){
                hsCriteria = new HashMap();
                hsCriteria.put("nip",id);
                hsCriteria.put("flag","Y");
                List<ItPersonilPositionEntity>personilPositionEntities = null;
                try {
                    personilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                }
                String position = "";
                if (personilPositionEntities != null){
                    for (ItPersonilPositionEntity listPersoniPosition : personilPositionEntities){
                        position = listPersoniPosition.getPositionId();
                    }
                }
                if (position != null){
                    hsCriteria = new HashMap();
                    hsCriteria.put("position_id", position);
                    hsCriteria.put("flag","Y");
                    List<ImPosition> positions = null;
                    try {
                        positions = positionDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                    }
                    if (positions != null){
                        for (ImPosition listPosition : positions){
                            kelompokId = listPosition.getKelompokId();
                        }
                    }
                    if (kelompokId != null){
                        List<ImHrisRsKelas> rsKelases = null;
                        try {
                            rsKelases = rsKelasDao.getListRskelasByKelompok(criteria, kelompokId);
                        } catch (HibernateException e) {
                            logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                        }
                        if (rsKelases != null){
                            Biodata biodata;
                            for (ImHrisRsKelas listKelas : rsKelases){
                                biodata = new Biodata();
                                biodata.setRsKerjaSama(listKelas.getRsId());
                                hsCriteria = new HashMap();
                                hsCriteria.put("rs_id",listKelas.getRsId());
                                hsCriteria.put("flag","Y");
                                List<ImRsKerjasamaEntity> imRsKerjasamaEntityList = null;
                                try {
                                    imRsKerjasamaEntityList = rsKerjasamaDao.getByCriteria(hsCriteria);
                                } catch (HibernateException e) {
                                    logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                                }
                                if (imRsKerjasamaEntityList != null){
                                    for (ImRsKerjasamaEntity listRs : imRsKerjasamaEntityList){
                                        biodata.setRsName(listRs.getRsName());
                                    }
                                }
                                biodata.setRsKelas(listKelas.getRsKelasId());
                                biodata.setRsKelasName(listKelas.getKelas());
                                result.add(biodata);
                            }
                        }
                    }
                }
            }
            if (result.isEmpty()){
                notFound = true;
            }
            if (notFound){
                List<ImHrisRsKelas> rsKelases = null;
                try {
                    rsKelases = rsKelasDao.getListRskelasByGolongan(criteria, golonganId);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                }

                if (rsKelases != null){
                    Biodata biodata;
                    for (ImHrisRsKelas listKelas : rsKelases){
                        biodata = new Biodata();
                        biodata.setRsKerjaSama(listKelas.getRsId());

                        hsCriteria = new HashMap();
                        hsCriteria.put("rs_id",listKelas.getRsId());
                        hsCriteria.put("flag","Y");

                        List<ImRsKerjasamaEntity> imRsKerjasamaEntityList = null;

                        try {
                            imRsKerjasamaEntityList = rsKerjasamaDao.getByCriteria(hsCriteria);
                        } catch (HibernateException e) {
                            logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                        }

                        if (imRsKerjasamaEntityList != null){
                            for (ImRsKerjasamaEntity listRs : imRsKerjasamaEntityList){
                                biodata.setRsName(listRs.getRsName());
                            }
                        }

                        biodata.setRsKelas(listKelas.getRsKelasId());
                        biodata.setRsKelasName(listKelas.getKelas());
                        result.add(biodata);
                    }
                }
            }
        }else if ("RJ".equalsIgnoreCase(statusRawat)){
                Biodata biodata;
                biodata = new Biodata();
                Map hsCriteria=new HashMap();
                hsCriteria.put("flag","Y");
                List<ImRsKerjasamaEntity> imRsKerjasamaEntityList = null;
                try {
                    imRsKerjasamaEntityList = rsKerjasamaDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.searchTrainingPerson] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                }

                if (imRsKerjasamaEntityList != null){
                    for (ImRsKerjasamaEntity listRs : imRsKerjasamaEntityList){
                        biodata = new Biodata();
                        biodata.setRsName(listRs.getRsName());
                        biodata.setRsKerjaSama(listRs.getRsId());
                        result.add(biodata);
                    }
                }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return result;
    }
    @Override
    public List<PersonilPosition> getByCriteriaPersonilPosition(PersonilPosition searchBean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.getByCriteriaPersonilPosition] start process >>>");
        // Mapping with collection and put
        List<PersonilPosition> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                hsCriteria.put("flag", searchBean.getFlag());
            }
            List<ItPersonilPositionEntity> itPersonilPositionEntityList = null;
            try {
                itPersonilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.getSearchPersonalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if(itPersonilPositionEntityList != null){
                // Looping from dao to object and save in collection
                for(ItPersonilPositionEntity personilPositionEntity : itPersonilPositionEntityList){
                    PersonilPosition returnPersonilPosition = new PersonilPosition();
                    returnPersonilPosition.setNip(personilPositionEntity.getNip());
                    returnPersonilPosition.setPersonilPositionId(personilPositionEntity.getPersonilPositionId());
                    Map hsCriteria2 = new HashMap();
                    hsCriteria2.put("department_id",personilPositionEntity.getDivisiId());
                    hsCriteria2.put("flag","Y");
                    List<ImDepartmentEntity> departmentEntityList = departmentDao.getByCriteria(hsCriteria2);
                    for(ImDepartmentEntity imDepartmentEntity : departmentEntityList){
                        returnPersonilPosition.setDivisiName(imDepartmentEntity.getDepartmentName());
                    }
                    hsCriteria2 = new HashMap();
                    hsCriteria2.put("position_id",personilPositionEntity.getPositionId());
                    hsCriteria2.put("flag","Y");
                    List<ImPosition> positionList = positionDao.getByCriteria(hsCriteria2);
                    for(ImPosition imPosition : positionList){
                        returnPersonilPosition.setPositionName(imPosition.getPositionName());
                    }
                    hsCriteria2 = new HashMap();
                    hsCriteria2.put("branch_id",personilPositionEntity.getBranchId());
                    hsCriteria2.put("flag","Y");
                    List<ImBranches> branchesList = branchDao.getByCriteria(hsCriteria2);
                    for(ImBranches imBranches : branchesList){
                        returnPersonilPosition.setBranchName(imBranches.getBranchName());
                    }
                    listOfResult.add(returnPersonilPosition);
                }
            }
        }
        logger.info("[BiodataBoImpl.getByCriteria] end process <<<");
        return listOfResult;
    }

    @Override
    public List<HistoryJabatanPegawai> historyJabatanSys(String nip) throws GeneralBOException {
        List<HistoryJabatanPegawai> listHistory = new ArrayList<>();
        List<ImtHrisHistoryJabatanPegawaiEntity> listImtHistory = null;

        listImtHistory = historyJabatanPegawaiDao.getDataHistoryJabatan(nip);
        if(listImtHistory != null){
            for(ImtHrisHistoryJabatanPegawaiEntity imtHistory : listImtHistory){
                HistoryJabatanPegawai historyJabatanPegawai = new HistoryJabatanPegawai();
                historyJabatanPegawai.setHistoryJabatanId(imtHistory.getHistoryJabatanId());
                historyJabatanPegawai.setNip(imtHistory.getNip());
                historyJabatanPegawai.setBidangName(imtHistory.getBidangName());
                historyJabatanPegawai.setStatus(imtHistory.getTipePegawaiName());
                historyJabatanPegawai.setPositionName(imtHistory.getPositionName());
                historyJabatanPegawai.setBranchName(imtHistory.getBranchName());
                historyJabatanPegawai.setTahun(imtHistory.getTahun());
                historyJabatanPegawai.setPjsFlag(imtHistory.getPjsFlag());
                listHistory.add(historyJabatanPegawai);
            }
        }

        return listHistory;
    }

    @Override
    public List<Payroll> searchPayrollSys(String nip) throws GeneralBOException {
        List<Payroll> payroll = new ArrayList<>();
        List<ItPayrollEntity> itPayroll = null;

        itPayroll = payrollDao.getAllPayroll(nip);
        if(itPayroll.size() > 0){
            for(ItPayrollEntity itPayrollEntity : itPayroll){
                Payroll payroll1 = new Payroll();
                payroll1.setPayrollId(itPayrollEntity.getPayrollId());
                payroll1.setBulan(itPayrollEntity.getBulan());
                payroll1.setTahun(itPayrollEntity.getTahun());
                payroll1.setNip(itPayrollEntity.getNip());
                payroll1.setBranchName(itPayrollEntity.getBranchName());
                payroll1.setTotalA(CommonUtil.numbericFormat(itPayrollEntity.getTotalA(), "###,###"));
                payroll1.setTotalB(CommonUtil.numbericFormat(itPayrollEntity.getTotalB(), "###,###"));
                if(itPayrollEntity.getPphGaji() != null){
                    payroll1.setPphGaji(CommonUtil.numbericFormat(itPayrollEntity.getPphGaji(), "###,###"));
                }else{
                    payroll1.setPphGaji(CommonUtil.numbericFormat(BigDecimal.valueOf(0), "###,###"));
                }
                payroll1.setTotalGajiBersih(CommonUtil.numbericFormat(itPayrollEntity.getGajiBersih(), "###,###"));
//                payroll1.setTotalRapel(CommonUtil.numbericFormat(itPayrollEntity.getTotalRapel(), "###,###"));
//                payroll1.setTotalThr(CommonUtil.numbericFormat(itPayrollEntity.getTotalThr(), "###,###"));
//                payroll1.setTotalPendidikan(CommonUtil.numbericFormat(itPayrollEntity.getTotalPendidikan(), "###,###"));
//                payroll1.setTotalJasProd(CommonUtil.numbericFormat(itPayrollEntity.getTotalJasProd(), "###,###"));
//                payroll1.setTotalPensiun(CommonUtil.numbericFormat(itPayrollEntity.getTotalPensiun(), "###,###"));
//                payroll1.setTotalJubileum(CommonUtil.numbericFormat(itPayrollEntity.getTotalJubileum(), "###,###"));

                payroll1.setFlagPayroll(itPayrollEntity.getFlagPayroll());
                payroll1.setFlagRapel(itPayrollEntity.getFlagRapel());
                payroll1.setFlagThr(itPayrollEntity.getFlagThr());
                payroll1.setFlagPendidikan(itPayrollEntity.getFlagPendidikan());
                payroll1.setFlagJasprod(itPayrollEntity.getFlagJasprod());
                payroll1.setFlagPensiun(itPayrollEntity.getFlagPensiun());
                payroll1.setFlagJubileum(itPayrollEntity.getFlagJubileum());
                payroll1.setFlagInsentif(itPayrollEntity.getFlagInsentif());
                payroll1.setFlagCutiPanjang(itPayrollEntity.getFlagCutiPanjang());
                payroll1.setFlagCutiTahunan(itPayrollEntity.getFlagCutiTahunan());

                payroll.add(payroll1);
            }
        }

        return payroll;
    }
    @Override
    public Biodata detailBiodataSys(String nip) throws GeneralBOException {
        ImBiodataEntity imBiodata = null;
        Biodata biodata = new Biodata();

        imBiodata = biodataDao.getById("nip", nip);
        if(imBiodata != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            if(imBiodata.getTanggalLahir() != null){
                String stringTanggal  = dateFormat.format(imBiodata.getTanggalLahir());
                biodata.setStTanggalLahir(stringTanggal);
            }else{
                biodata.setStTanggalLahir("");
            }
            if(imBiodata.getTanggalPensiun() != null){
                String stringTanggal  = dateFormat.format(imBiodata.getTanggalPensiun());
                biodata.setStTanggalPensiun(stringTanggal);
            }else{
                biodata.setStTanggalPensiun("");
            }
            if(imBiodata.getTanggalAktif() != null){
                String stringTanggal  = dateFormat.format(imBiodata.getTanggalAktif());
                biodata.setStTanggalAktif(stringTanggal);
                biodata.setTanggalAktif(imBiodata.getTanggalAktif());
            }else{
                biodata.setStTanggalAktif("");
            }

            if(imBiodata.getTanggalMasuk() != null){
                String stringTanggal  = dateFormat.format(imBiodata.getTanggalMasuk());
                biodata.setStTanggalMasuk(stringTanggal);
                biodata.setTanggalMasuk(imBiodata.getTanggalMasuk());
            }else{
                biodata.setStTanggalMasuk("");
            }

            DateTime awal = null;
            DateTime sekarang = new DateTime();
            String masaKerja = "";
            if(imBiodata.getTanggalAktif() != null){
                awal = new DateTime(imBiodata.getTanggalAktif());
                Years y = Years.yearsBetween(awal, sekarang);
                masaKerja = y.getYears() + " Tahun";
            }else{
                masaKerja = "-";
            }

            biodata.setNip(imBiodata.getNip());
            biodata.setMasaKerja(masaKerja);
            biodata.setNamaPegawai(imBiodata.getNamaPegawai());
            biodata.setGelarDepan(imBiodata.getGelarDepan());
            biodata.setGelarBelakang(imBiodata.getGelarBelakang());
            biodata.setNoKtp(imBiodata.getNoKtp());
            biodata.setAlamat(imBiodata.getAlamat());
            biodata.setRtRw(imBiodata.getRtRw());
            biodata.setNoTelp(imBiodata.getNoTelp());
            biodata.setTanggalLahir(imBiodata.getTanggalLahir());
            biodata.setTempatLahir(imBiodata.getTempatLahir());
            biodata.setTipePegawai(imBiodata.getTipePegawai());
            if(imBiodata.getTipePegawai() != null){
                biodata.setTipePegawaiName(imBiodata.getImHrisTipePegawai().getTipePegawaiName());
            }else{
                biodata.setTipePegawaiName("");
            }
            biodata.setFotoUpload(imBiodata.getFotoUpload());
            biodata.setStatusCaption(imBiodata.getStatusCaption());
            biodata.setKeterangan(imBiodata.getKeterangan());

            ItPersonilPositionEntity itPersonilPositionEntity = null;
            itPersonilPositionEntity = personilPositionDao.getById("nip",imBiodata.getNip(),"Y" );

            if(itPersonilPositionEntity != null){
                if(itPersonilPositionEntity.getImDepartmentEntity() != null){
                    biodata.setDivisi(itPersonilPositionEntity.getImDepartmentEntity().getDepartmentId());
                }else{
                    biodata.setDivisi("");
                }
            }else{
                biodata.setDivisi("");
            }
            Map hsCriteria2 = new HashMap();

            if(itPersonilPositionEntity != null){
                if(itPersonilPositionEntity.getImPosition() != null){
                    biodata.setPositionId(itPersonilPositionEntity.getImPosition().getPositionId());
                    biodata.setKelompokId(itPersonilPositionEntity.getImPosition().getKelompokId());
                }else{
                    biodata.setPositionId("");
                }
                biodata.setPjs(itPersonilPositionEntity.getPjs());
                biodata.setBranch(itPersonilPositionEntity.getImBranches().getPrimaryKey().getId());
                biodata.setBranchName(itPersonilPositionEntity.getImBranches().getBranchName());
            }else{
                biodata.setPositionId("");
            }

            ImPosition positionList = positionDao.getById("positionId", biodata.getPositionId());
            if(positionList != null){
                biodata.setPositionName(positionList.getPositionName());
                if(positionList.getDepartmentId() != null){
                    biodata.setDivisi(positionList.getImDepartmentEntity().getDepartmentId());
                    biodata.setDivisiName(positionList.getImDepartmentEntity().getDepartmentName());
                }
            }

            if(!"".equalsIgnoreCase(imBiodata.getGolongan())){
                if(imBiodata.getImGolonganEntity() != null){
                    biodata.setGolonganName(imBiodata.getImGolonganEntity().getGolonganName());
                    biodata.setGolongan(imBiodata.getGolongan());
                }else{
                    biodata.setGolongan(imBiodata.getGolongan());
                }
            } else{
                biodata.setGolongan(imBiodata.getGolongan());
            }

            biodata.setMasaGiling(imBiodata.getMasaGiling());
            biodata.setStatusPegawai(imBiodata.getStatusPegawai());
            if(imBiodata.getStatusPegawai() != null){
                if(imBiodata.getStatusPegawai().equalsIgnoreCase("KS")){
                    biodata.setStatusPegawaiName("Karyawan Staf");
                }else{
                    biodata.setStatusPegawaiName("Karyawan Non Staf");
                }
            }
            biodata.setStatusKeluarga(imBiodata.getStatusKeluarga());
            if(imBiodata.getStatusKeluarga() != null){
                if(imBiodata.getStatusKeluarga().equalsIgnoreCase("K")){
                    biodata.setStatusKeluargaName("Keluarga");
                }else if(imBiodata.getStatusKeluarga().equalsIgnoreCase("B")){
                    biodata.setStatusKeluargaName("Belum");
                }
            }else{
                biodata.setStatusKeluargaName("");
            }


            biodata.setProvinsiName(imBiodata.getProvinsiId());
            biodata.setProvinsiName(imBiodata.getProvinsiName());

            biodata.setKabupatenId(imBiodata.getKotaId());
            biodata.setKotaName(imBiodata.getKotaName());

            biodata.setKecamatanId(imBiodata.getKecamatanId());
            biodata.setKecamatanName(imBiodata.getKecamatanName());

            biodata.setDesaId(imBiodata.getDesaId());
            biodata.setDesaName(imBiodata.getDesaName());

            if(imBiodata.getFotoUpload() != null){
                biodata.setFotoUpload(imBiodata.getFotoUpload());
            }else{
                biodata.setFotoUpload("unknown-person2.jpg");
            }
            biodata.setMasaGiling(imBiodata.getMasaGiling());
            biodata.setMt(imBiodata.getMt());
            biodata.setPin(imBiodata.getPin());
            biodata.setDanaPensiun(imBiodata.getDanaPensiun());
            biodata.setNoAnggotaDapen(imBiodata.getNoAnggotaDapen());
            biodata.setNoBpjsKetenagakerjaan(imBiodata.getNoBpjsKetenagakerjaan());
            biodata.setNoBpjsKetenagakerjaanPensiun(imBiodata.getNoBpjsKetenagakerjaanPensiun());
            biodata.setNoBpjsKesehatan(imBiodata.getNoBpjsKesehatan());
            biodata.setKotaName(imBiodata.getKotaName());
            biodata.setKecamatanName(imBiodata.getKecamatanName());
            biodata.setDesaName(imBiodata.getDesaName());
            biodata.setGolonganId(imBiodata.getGolongan());
            biodata.setPoint(imBiodata.getPoint());
            biodata.setFlagZakat(imBiodata.getZakatProfesi());

            biodata.setNamaBank(imBiodata.getNamaBank());
            biodata.setCabangBank(imBiodata.getCabangBank());
            biodata.setNoRekBank(imBiodata.getNoRekBank());
            if(imBiodata.getZakatProfesi() != null){
                if(imBiodata.getZakatProfesi().equalsIgnoreCase("Y")){
                    biodata.setZakatName("Iya");
                }else{
                    biodata.setZakatName("-");
                }
            }
            biodata.setGender(imBiodata.getGender());
            if(imBiodata.getGender() != null){
                if(imBiodata.getGender().equals("P")){
                    biodata.setGenderName("Perempuan");
                }else if(imBiodata.getGender().equalsIgnoreCase("L")){
                    biodata.setGenderName("Laki - Laki");
                }
            }else{
                biodata.setGenderName("");
            }

            biodata.setNpwp(imBiodata.getNpwp());
            biodata.setGaji(imBiodata.getGaji().toPlainString());
            biodata.setStrukturGaji(imBiodata.getStrukturGaji());
            biodata.setStrukturGaji2(imBiodata.getStrukturGaji());
            biodata.setStatusGiling(imBiodata.getStatusGiling());
            if(imBiodata.getStatusGiling() != null){
                if(imBiodata.getStatusGiling().equalsIgnoreCase("DMG")){
                    biodata.setStatusGilingName("Dalam Masa Giling");
                }else if(imBiodata.getStatusGiling().equalsIgnoreCase("LMG")){
                    biodata.setStatusGilingName("Luar Masa Giling");
                }
            }else{
                biodata.setStatusGilingName("");
            }
            biodata.setJumlahAnak(imBiodata.getJumlahAnak());

            String danPens = "";
            if(imBiodata.getDanaPensiun() != null){
                if(!imBiodata.getDanaPensiun().equalsIgnoreCase("")){
                    danPens = imBiodata.getImDanaPensiunEntity().getDanaPensiun();
                }
            }

            biodata.setDanaPensiun(danPens);
            biodata.setAgama(imBiodata.getAgama());

            biodata.setCreatedWho(imBiodata.getCreatedWho());
            biodata.setCreatedDate(imBiodata.getCreatedDate());
            biodata.setLastUpdate(imBiodata.getLastUpdate());
            biodata.setAction(imBiodata.getAction());
            biodata.setFlag(imBiodata.getFlag());
            biodata.setStatusPegawai(imBiodata.getStatusPegawai());
            if(imBiodata.getFotoUpload() != null){
                biodata.setPathFoto(ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD
                        + imBiodata.getFotoUpload());
            }else{
                biodata.setPathFoto(ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD
                        + "unknown-person2.jpg");
            }

        }else{
            biodata.setKeterangan("--kosong--");
            biodata.setFotoUpload("unknown-person2.jpg");
        }

        return biodata;
    }

    @Override
    public void saveUploadImage(Biodata bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveUploadImage] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String personalId = bean.getNip();

            ImBiodataEntity imBiodataEntity = null;
            ImBiodataHistoryEntity imBiodataHistoryEntity = new ImBiodataHistoryEntity();
            try {
                // Get data from database by ID
                imBiodataEntity = biodataDao.getById("nip", personalId);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Biodata by Kode Biodata, please inform to your admin...," + e.getMessage());
            }

            if (imBiodataEntity != null) {

                imBiodataEntity.setNip(bean.getNip());

                if(bean.getFotoUpload() != null){
                    imBiodataEntity.setFotoUpload(bean.getFotoUpload());
                }

                try {
                    // Update into database
                    biodataDao.updateAndSave(imBiodataEntity);

                } catch (HibernateException e) {
                    logger.error("[BiodataBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Biodata, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[BiodataBoImpl.saveEdit] Error, not found data Biodata with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Biodata with request id, please check again your data ...");

            }
        }
        logger.info("[BiodataBoImpl.saveUploadImage] end process <<<");
    }

    public void saveEditPengalamanKerja(HistoryJabatanPegawai bean) throws GeneralBOException {
        logger.info("[PengalamanKerjaBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String PengalamanKerjaId = bean.getHistoryJabatanId();
            String golonganName ="";
//            String pengalamanId;
            List<HistoryJabatanPegawai> historyJabatanPegawai = new ArrayList<>();
            String branchName, positionname, divisiName, tipePegawaiName;
            ImtHrisHistoryJabatanPegawaiEntity imPengalamanKerjaEntity = null;
            try {
                // Get data from database by ID
                imPengalamanKerjaEntity = historyJabatanPegawaiDao.getById("historyJabatanId", PengalamanKerjaId);

                //mengambil branch name, position name, divisi name, golongan name, tipe pegawai name
                if (!bean.getBranchId().equalsIgnoreCase("0")){
                    branchName = historyJabatanPegawaiDao.getBranchById(bean.getBranchId());

                }else {
                    branchName = bean.getBranchName();
                }
                if (!bean.getDivisiId().equalsIgnoreCase("0")){
                    divisiName = historyJabatanPegawaiDao.getDivisiById(bean.getDivisiId());
                }else {
                    divisiName = bean.getDivisiName();
                }
                if (!bean.getPositionId().equalsIgnoreCase("0")){
                    positionname = historyJabatanPegawaiDao.getPositionById(bean.getPositionId());
                }else {
                    positionname = bean.getPositionName();
                }
                if(bean.getGolonganId()!= null){
                    if (!bean.getGolonganId().equalsIgnoreCase("")){
                        if (bean.getTipePegawaiId().equalsIgnoreCase("TP01")){
                            golonganName = historyJabatanPegawaiDao.getGolonganById(bean.getGolonganId());
                        }
                        if (bean.getTipePegawaiId().equalsIgnoreCase("TP03")){
                            golonganName = historyJabatanPegawaiDao.getGolonganPkwtById(bean.getGolonganId());
                        }
                    }else {
                        golonganName = null;
                    }
                }else{
                    golonganName = null;
                }
                tipePegawaiName = historyJabatanPegawaiDao.getTipePegawaiById(bean.getTipePegawaiId());


                historyJabatanPegawai = historyJabatanPegawaiDao.geyBagianByPositionId(bean.getPositionId());
                if (historyJabatanPegawai.size() >0){
                    for (HistoryJabatanPegawai result: historyJabatanPegawai){
                        imPengalamanKerjaEntity.setBagianId(result.getBagianId());
                        imPengalamanKerjaEntity.setBagianName(result.getBagianName());
                    }
                }
            } catch (HibernateException e) {
                logger.error("[PengalamanKerjaBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
            }

            if (imPengalamanKerjaEntity != null) {
                ImtHrisHistoryJabatanPegawaiEntity historyJabatan = new ImtHrisHistoryJabatanPegawaiEntity();

//                historyJabatan.setHistoryJabatanId(pengalamanId);
                imPengalamanKerjaEntity.setNip(bean.getNip());
                imPengalamanKerjaEntity.setBranchId(bean.getBranchId());
                imPengalamanKerjaEntity.setBranchName(branchName);
                imPengalamanKerjaEntity.setDivisiId(bean.getDivisiId());
                imPengalamanKerjaEntity.setDivisiName(divisiName);
                imPengalamanKerjaEntity.setPositionId(bean.getPositionId());
                imPengalamanKerjaEntity.setPositionName(positionname);
                imPengalamanKerjaEntity.setTipePegawaiId(bean.getTipePegawaiId());
                imPengalamanKerjaEntity.setTipePegawaiName(tipePegawaiName);
                imPengalamanKerjaEntity.setGolonganId(bean.getGolonganId());
                imPengalamanKerjaEntity.setGolonganName(golonganName);
                imPengalamanKerjaEntity.setTanggal(bean.getTanggal());
                imPengalamanKerjaEntity.setTanggalKeluar(bean.getTanggalKeluar());
                imPengalamanKerjaEntity.setBidangId(bean.getDivisiId());
                imPengalamanKerjaEntity.setBidangName(divisiName);

                imPengalamanKerjaEntity.setFlag(bean.getFlag());
                imPengalamanKerjaEntity.setAction(bean.getAction());
                imPengalamanKerjaEntity.setCreatedWho(bean.getCreatedWho());
                imPengalamanKerjaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPengalamanKerjaEntity.setCreatedDate(bean.getCreatedDate());
                imPengalamanKerjaEntity.setLastUpdate(bean.getLastUpdate());

                String tahun = "";
                String smkAda = "N";
                if(bean.getTanggal().length() > 4){
                    String strBln[] = bean.getTanggal().split("-");
                    tahun = strBln[2];
                }else{
                    tahun = bean.getTanggal();
                }

                imPengalamanKerjaEntity.setTahun(tahun);
                imPengalamanKerjaEntity.setPoint(bean.getPoint());
                imPengalamanKerjaEntity.setPointLebih(bean.getPointLebih());
                imPengalamanKerjaEntity.setNilaiSmk(bean.getNilaiSmk());
                imPengalamanKerjaEntity.setGradeSmk(bean.getGradeSmk());
//                imPengalamanKerjaEntity.setGolonganName(bean.getGolonganName());

                try {
                    // insert into database
                    historyJabatanPegawaiDao.updateAndSave(imPengalamanKerjaEntity);

                } catch (HibernateException e) {
                    logger.error("[PengalamanKerjaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengalamanKerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengalamanKerjaBoImpl.saveEdit] Error, not found data PengalamanKerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengalamanKerja with request id, please check again your data ...");
//                condition = "Error, not found data PengalamanKerja with request id, please check again your data ...";
            }
        }
        logger.info("[PengalamanKerjaBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void addPengalamanKerja(HistoryJabatanPegawai bean) throws GeneralBOException {
        logger.info("[PengalamanKerjaBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            // creating object entity serializable
            ImtHrisHistoryJabatanPegawaiEntity historyJabatan = new ImtHrisHistoryJabatanPegawaiEntity();
            String pengalamanId;
            List<HistoryJabatanPegawai> historyJabatanPegawai = new ArrayList<>();
            String branchName, positionname, divisiName, tipePegawaiName;
            String golonganName="";
            try {
                // Generating ID, get from postgre sequence
                pengalamanId = historyJabatanPegawaiDao.getNextPersonilPositionId();

                //mengambil branch name, position name, divisi name, golongan name, tipe pegawai name
                if (!bean.getBranchId().equalsIgnoreCase("0")){
                    branchName = historyJabatanPegawaiDao.getBranchById(bean.getBranchId());

                }else {
                    branchName = bean.getBranchName();
                }
                if (!bean.getDivisiId().equalsIgnoreCase("0")){
                    divisiName = historyJabatanPegawaiDao.getDivisiById(bean.getDivisiId());
                }else {
                    divisiName = bean.getDivisiName();
                }
                if (!bean.getPositionId().equalsIgnoreCase("0")){
                    positionname = historyJabatanPegawaiDao.getPositionById(bean.getPositionId());
                }else {
                    positionname = bean.getPositionName();
                }
                if(bean.getGolonganId()!= null){
                    if (!bean.getGolonganId().equalsIgnoreCase("")){
                        if (bean.getTipePegawaiId().equalsIgnoreCase("TP01")){
                            golonganName = historyJabatanPegawaiDao.getGolonganById(bean.getGolonganId());
                        }
                        if (bean.getTipePegawaiId().equalsIgnoreCase("TP03")){
                            List<ImGolonganPkwtEntity> golonganPkwtEntities = new ArrayList<>();
                            golonganPkwtEntities = golonganPkwtDao.getGolonganById(bean.getGolonganId());
                            if (golonganPkwtEntities.size()>0){
                                for (ImGolonganPkwtEntity golonganPkwtLoop: golonganPkwtEntities){
                                    golonganName = golonganPkwtLoop.getGolonganPkwtName();
                                }
                            }
                        }
                    }else {
                        golonganName = null;
                    }
                }else{
                    golonganName = null;
                }
                tipePegawaiName = historyJabatanPegawaiDao.getTipePegawaiById(bean.getTipePegawaiId());

                historyJabatanPegawai = historyJabatanPegawaiDao.geyBagianByPositionId(bean.getPositionId());
                if (historyJabatanPegawai.size() >0){
                    for (HistoryJabatanPegawai result: historyJabatanPegawai){
                        historyJabatan.setBagianId(result.getBagianId());
                        historyJabatan.setBagianName(result.getBagianName());
                    }
                }
            } catch (HibernateException e) {
                logger.error("[PengalamanKerjaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence PengalamanKerjaId id, please info to your admin..." + e.getMessage());
            }


            historyJabatan.setHistoryJabatanId(pengalamanId);
            historyJabatan.setNip(bean.getNip());
            historyJabatan.setBranchId(bean.getBranchId());
            historyJabatan.setBranchName(branchName);
            historyJabatan.setDivisiId(bean.getDivisiId());
            historyJabatan.setDivisiName(divisiName);
            historyJabatan.setPositionId(bean.getPositionId());
            historyJabatan.setPositionName(positionname);
            historyJabatan.setTipePegawaiId(bean.getTipePegawaiId());
            historyJabatan.setTipePegawaiName(tipePegawaiName);
            historyJabatan.setGolonganId(bean.getGolonganId());
            historyJabatan.setGolonganName(golonganName);
            historyJabatan.setTanggal(bean.getTanggal());
            historyJabatan.setTanggalKeluar(bean.getTanggalKeluar());
            historyJabatan.setBidangId(bean.getDivisiId());
            historyJabatan.setBidangName(divisiName);
            historyJabatan.setProfesiId(bean.getProfesiId());

            historyJabatan.setFlag(bean.getFlag());
            historyJabatan.setAction(bean.getAction());
            historyJabatan.setCreatedWho(bean.getCreatedWho());
            historyJabatan.setLastUpdateWho(bean.getLastUpdateWho());
            historyJabatan.setCreatedDate(bean.getCreatedDate());
            historyJabatan.setLastUpdate(bean.getLastUpdate());
            historyJabatan.setPjsFlag(bean.getPjsFlag());

            String tahun = "";
            String smkAda = "N";
            if(bean.getTanggal().length() > 4){
                String strBln[] = bean.getTanggal().split("-");
                tahun = strBln[2];
            }else{
                tahun = bean.getTahun();
            }
            historyJabatan.setTahun(tahun);
            try {
                // insert into database
                historyJabatanPegawaiDao.addAndSave(historyJabatan);
            } catch (HibernateException e) {
                logger.error("[PengalamanKerjaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PengalamanKerja, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PengalamanKerjaBoImpl.saveAdd] end process <<<");
    }

    @Override
    public void addReward(Reward bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String rewardId;
            try {
                // Generating ID, get from postgre sequence
                rewardId = rewardDao.getNextReward();
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence PengalamanKerjaId id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            ImRewardEntity imRewardEntity = new ImRewardEntity();

            imRewardEntity.setRewardId(rewardId);
            imRewardEntity.setNip(bean.getNip());
            imRewardEntity.setJenis(bean.getJenis());
            imRewardEntity.setKeterangan(bean.getKeterangan());

            if(bean.getStTanggal() != null && !"".equalsIgnoreCase(bean.getStTanggal())){
                imRewardEntity.setTanggal(CommonUtil.convertStringToDate(bean.getStTanggal()));
            }

            imRewardEntity.setFlag(bean.getFlag());
            imRewardEntity.setAction(bean.getAction());
            imRewardEntity.setCreatedWho(bean.getCreatedWho());
            imRewardEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imRewardEntity.setCreatedDate(bean.getCreatedDate());
            imRewardEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                rewardDao.addAndSave(imRewardEntity);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PengalamanKerja, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[BiodataBoImpl.saveAddReward] end process <<<");
    }

    @Override
    public void addSertifikat(Sertifikat bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveAdd] start process >>>");
        if (bean!=null) {

            String sertifikatId;
            try {
                // Generating ID, get from postgre sequence
                sertifikatId = sertifikatDao.getNextSertifikat();
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence PengalamanKerjaId id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            ImSertifikatEntity imSertifikatEntity = new ImSertifikatEntity();
            imSertifikatEntity.setSertifikatId(sertifikatId);
            imSertifikatEntity.setNip(bean.getNip());
            imSertifikatEntity.setJenis(bean.getJenis());
            imSertifikatEntity.setNama(bean.getNama());
            imSertifikatEntity.setLembaga(bean.getLembaga());
            imSertifikatEntity.setTempatPelaksana(bean.getTempatPelaksana());
            imSertifikatEntity.setNilai(bean.getNilai());
            imSertifikatEntity.setLulus(bean.getLulus());
            imSertifikatEntity.setPrestasiGrade(bean.getPrestasiGrade());
            imSertifikatEntity.setJenis(bean.getJenis());
            imSertifikatEntity.setJumlahHari(bean.getJumlahHari());

            if(bean.getStTanggalPengesahan() != null && !bean.getStTanggalPengesahan().equalsIgnoreCase("")){
                imSertifikatEntity.setTanggalPengesahan(CommonUtil.convertStringToDate(bean.getStTanggalPengesahan()));
            }

            if(bean.getStMasaBerlaku() != null && !bean.getStMasaBerlaku().equalsIgnoreCase("")){
                imSertifikatEntity.setMasaBerlaku(CommonUtil.convertStringToDate(bean.getStMasaBerlaku()));
            }

            if(bean.getStMasaBerlaku() != null && !bean.getStMasaBerakhir().equalsIgnoreCase("")){
                imSertifikatEntity.setMasaBerakhir(CommonUtil.convertStringToDate(bean.getStMasaBerakhir()));
            }

            imSertifikatEntity.setFlag(bean.getFlag());
            imSertifikatEntity.setAction(bean.getAction());
            imSertifikatEntity.setCreatedWho(bean.getCreatedWho());
            imSertifikatEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSertifikatEntity.setCreatedDate(bean.getCreatedDate());
            imSertifikatEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                sertifikatDao.addAndSave(imSertifikatEntity);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveAddSertifikat] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PengalamanKerja, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[BiodataBoImpl.saveAddReward] end process <<<");
    }

    @Override
    public void addPelatihan(PelatihanJabatanUser bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.addPelatihan] start process >>>");
        if (bean!=null) {

            String pelatihanId;
            try {
                // Generating ID, get from postgre sequence
                pelatihanId = pelatihanJabatanUserDao.getNextPelatihanJabatan();
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.addPelatihan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence PengalamanKerjaId id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            ImtPelatihanJabatanUserEntity imtPelatihanJabatanUserEntity = new ImtPelatihanJabatanUserEntity();
            imtPelatihanJabatanUserEntity.setPelatihanUserId(pelatihanId);
            imtPelatihanJabatanUserEntity.setPelatihanJabatanId(bean.getPelatihanJabatanId());
            imtPelatihanJabatanUserEntity.setLembaga(bean.getLembaga());
            imtPelatihanJabatanUserEntity.setAngkatan(bean.getAngkatan());
            imtPelatihanJabatanUserEntity.setTahun(bean.getTahun());
            imtPelatihanJabatanUserEntity.setStatus(bean.getStatus());
            imtPelatihanJabatanUserEntity.setNilai(bean.getNilai());
            imtPelatihanJabatanUserEntity.setNip(bean.getNip());

            imtPelatihanJabatanUserEntity.setFlag(bean.getFlag());
            imtPelatihanJabatanUserEntity.setAction(bean.getAction());
            imtPelatihanJabatanUserEntity.setCreatedWho(bean.getCreatedWho());
            imtPelatihanJabatanUserEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imtPelatihanJabatanUserEntity.setCreatedDate(bean.getCreatedDate());
            imtPelatihanJabatanUserEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                pelatihanJabatanUserDao.addAndSave(imtPelatihanJabatanUserEntity);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.addPelatihan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PengalamanKerja, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[BiodataBoImpl.addPelatihan] end process <<<");
    }

    @Override
    public PengalamanKerja searchPengalamanKerja(String pengalamanId) throws GeneralBOException {
        ImtHrisHistoryJabatanPegawaiEntity searchPengalamanKerja = null;
        PengalamanKerja pengalamanKerja = new PengalamanKerja();
//        HistoryJabatanPegawai newSearchPengalamanerja = null;
        searchPengalamanKerja = historyJabatanPegawaiDao.getById("historyJabatanId", pengalamanId);

        if(searchPengalamanKerja != null){
            pengalamanKerja.setPengalamanId(searchPengalamanKerja.getHistoryJabatanId());
            pengalamanKerja.setNamaPerusahaan(searchPengalamanKerja.getBranchName());
            if(searchPengalamanKerja.getBranchId().equalsIgnoreCase("lain")){
                pengalamanKerja.setBranchId("0");
                pengalamanKerja.setNamaPerusahaan(searchPengalamanKerja.getBranchName());
            }else{
                pengalamanKerja.setBranchId(searchPengalamanKerja.getBranchId());
                pengalamanKerja.setNamaPerusahaan(searchPengalamanKerja.getBranchName());
            }
            pengalamanKerja.setJabatan(searchPengalamanKerja.getPositionName());
            pengalamanKerja.setStTtahunMasuk(searchPengalamanKerja.getTanggal());
            pengalamanKerja.setStTahunKeluar(searchPengalamanKerja.getTanggalKeluar());

            if(searchPengalamanKerja.getTipePegawaiId() != null && !searchPengalamanKerja.getTipePegawaiId().equalsIgnoreCase("")){
                pengalamanKerja.setTipePegawaiId(searchPengalamanKerja.getTipePegawaiId());
                pengalamanKerja.setTipePegawai(searchPengalamanKerja.getTipePegawaiName());
            }else{
                pengalamanKerja.setTipePegawaiId("-");
            }
            if (searchPengalamanKerja.getDivisiId()!=null){
                if (!searchPengalamanKerja.getDivisiId().equalsIgnoreCase("")){
                    pengalamanKerja.setDivisiId(searchPengalamanKerja.getDivisiId());
                }else{
                    pengalamanKerja.setDivisiId("0");
                }
            }else{
                pengalamanKerja.setDivisiId("0");
            }
            pengalamanKerja.setDivisiName(searchPengalamanKerja.getBidangName());
            if (searchPengalamanKerja.getPositionId()!=null){
                if (!searchPengalamanKerja.getPositionId().equalsIgnoreCase("")){
                    pengalamanKerja.setPosisiId(searchPengalamanKerja.getPositionId());
                }else{
                    pengalamanKerja.setPosisiId("0");
                }
            }else{
                pengalamanKerja.setPosisiId("0");
            }
            pengalamanKerja.setPositionName(searchPengalamanKerja.getPositionName());
            pengalamanKerja.setGolonganId(searchPengalamanKerja.getGolonganId());
            pengalamanKerja.setGolonganName(searchPengalamanKerja.getGolonganName());
            pengalamanKerja.setTanggalMasuk(searchPengalamanKerja.getTanggal());
            pengalamanKerja.setTanggalKeluar(searchPengalamanKerja.getTanggalKeluar());
            if (searchPengalamanKerja.getTanggalKeluar()!=null){
                if (searchPengalamanKerja.getTanggalKeluar().equalsIgnoreCase("")){
                    pengalamanKerja.setFlagJabatanAktif("Y");
                }
                else {
                    pengalamanKerja.setFlagJabatanAktif("N");
                }
            }
            else {
                pengalamanKerja.setFlagJabatanAktif("N");
            }
            pengalamanKerja.setPjsFlag(searchPengalamanKerja.getPjsFlag());

        }

        return pengalamanKerja;
    }

    @Override
    public Reward searchReward(String rewardId) throws GeneralBOException {
        ImRewardEntity searchReward = null;
        Reward reward = new Reward();
        searchReward = rewardDao.getById("rewardId", rewardId);

        if(searchReward != null){
            reward.setRewardId(searchReward.getRewardId());
            reward.setStTanggal(CommonUtil.convertDateToString(searchReward.getTanggal()));
            reward.setTanggal(searchReward.getTanggal());
            reward.setJenis(searchReward.getJenis());
            reward.setKeterangan(searchReward.getKeterangan());
        }

        return reward;
    }

    @Override
    public Sertifikat searchSertifikat(String sertifikatId) throws GeneralBOException {
        ImSertifikatEntity searchSertifikat = null;
        Sertifikat sertifikat = new Sertifikat();
        searchSertifikat = sertifikatDao.getById("sertifikatId", sertifikatId);

        if(searchSertifikat != null){
            sertifikat.setSertifikatId(sertifikatId);
            sertifikat.setNip(searchSertifikat.getNip());
            sertifikat.setJenis(searchSertifikat.getJenis());
            sertifikat.setNama(searchSertifikat.getNama());
            sertifikat.setLembaga(searchSertifikat.getLembaga());
            sertifikat.setTempatPelaksana(searchSertifikat.getTempatPelaksana());
            sertifikat.setNilai(searchSertifikat.getNilai());
            sertifikat.setLulus(searchSertifikat.getLulus());
            sertifikat.setPrestasiGrade(searchSertifikat.getPrestasiGrade());
            sertifikat.setJenis(searchSertifikat.getJenis());
            sertifikat.setJumlahHari(searchSertifikat.getJumlahHari());

            if(searchSertifikat.getTanggalPengesahan() != null){
                sertifikat.setTanggalPengesahan(searchSertifikat.getTanggalPengesahan());
                sertifikat.setStTanggalPengesahan(CommonUtil.convertDateToString(searchSertifikat.getTanggalPengesahan()));
            }else{
                sertifikat.setStTanggalPengesahan("-");
            }

            if(searchSertifikat.getMasaBerlaku() != null){
                sertifikat.setMasaBerlaku(searchSertifikat.getMasaBerlaku());
                sertifikat.setStMasaBerlaku(CommonUtil.convertDateToString(searchSertifikat.getMasaBerlaku()));
            }else{
                sertifikat.setStMasaBerlaku("-");
            }

            if(searchSertifikat.getMasaBerakhir() != null){
                sertifikat.setMasaBerakhir(searchSertifikat.getMasaBerakhir());
                sertifikat.setStMasaBerakhir(CommonUtil.convertDateToString(searchSertifikat.getMasaBerakhir()));
            }else{
                sertifikat.setStMasaBerakhir("-");
            }
        }
        return sertifikat;
    }

    @Override
    public List<PengalamanKerja> searchDataPengalamanKerja(String nip) throws GeneralBOException {
        List<ImPengalamanKerjaEntity> ImPengalamanKerja = new ArrayList<>();
        List<PengalamanKerja> pengalamanKerja = new ArrayList<>();
        ImPengalamanKerja = pengalamanKerjaDao.getAllData(nip);

        if(ImPengalamanKerja.size() > 0){
            for(ImPengalamanKerjaEntity pengalamanKerjaEntity : ImPengalamanKerja){
                PengalamanKerja pengalamanKerja1 = new PengalamanKerja();
                pengalamanKerja1.setPengalamanId(pengalamanKerjaEntity.getPengalamanId());
                pengalamanKerja1.setNamaPerusahaan(pengalamanKerjaEntity.getNamaPerusahaan());
                pengalamanKerja1.setJabatan(pengalamanKerjaEntity.getJabatan());
                pengalamanKerja1.setTahunMasuk(pengalamanKerjaEntity.getTahunMasuk());
                pengalamanKerja1.setTahunKeluar(pengalamanKerjaEntity.getTahunKeluar());
                pengalamanKerja1.setStTtahunMasuk(CommonUtil.convertDateToString(pengalamanKerjaEntity.getTahunMasuk()));
                pengalamanKerja1.setStTahunKeluar(CommonUtil.convertDateToString(pengalamanKerjaEntity.getTahunKeluar()));

                pengalamanKerja.add(pengalamanKerja1);
            }
        }

        return pengalamanKerja;
    }

    @Override
    public List<PengalamanKerja> searchDataRiwayatKerja(String nip) throws GeneralBOException {
        List<ImtHrisHistoryJabatanPegawaiEntity> ImPengalamanKerja = new ArrayList<>();
        List<PengalamanKerja> pengalamanKerjas = new ArrayList<>();
        ImPengalamanKerja = historyJabatanPegawaiDao.getDataHistoryJabatan(nip);

        if(ImPengalamanKerja.size() > 0){
            for (ImtHrisHistoryJabatanPegawaiEntity historyLoop: ImPengalamanKerja){
                PengalamanKerja pengalamanKerja = new PengalamanKerja();
                pengalamanKerja.setPengalamanId(historyLoop.getHistoryJabatanId());
                pengalamanKerja.setNamaPerusahaan(historyLoop.getBranchName());
                pengalamanKerja.setNip(historyLoop.getNip());
                pengalamanKerja.setJabatan(historyLoop.getPositionName());
                pengalamanKerja.setProfesiId(historyLoop.getProfesiId());
                if (historyLoop.getProfesiId()!=null){
                    ImProfesiEntity profesiEntity = new ImProfesiEntity();
                    if (!historyLoop.getProfesiId().equalsIgnoreCase("")){
                        profesiEntity = profesiDao.getById("profesiId",historyLoop.getProfesiId());
                        pengalamanKerja.setProfesiName(profesiEntity.getProfesiName());
                    }else{
                        pengalamanKerja.setProfesiName("-");
                    }
                }else{
                    pengalamanKerja.setProfesiName("-");
                }
                if (historyLoop.getTahun()!=null){
                    if(!historyLoop.getTahun().equalsIgnoreCase("")){
                        pengalamanKerja.setStTtahunMasuk(historyLoop.getTahun());
                    }
                    else{
                        pengalamanKerja.setStTtahunMasuk("-");
                    }
                }else{
                    pengalamanKerja.setStTtahunMasuk("-");
                }
                if ("Y".equalsIgnoreCase(historyLoop.getPjsFlag()) && historyLoop.getPjsFlag()!=null){
                    pengalamanKerja.setPjsFlag("Ya");
                }
                else{
                    pengalamanKerja.setPjsFlag("Tidak");
                }

                if(historyLoop.getTipePegawaiName() != null && !historyLoop.getTipePegawaiName().equalsIgnoreCase("")){
                    pengalamanKerja.setTipePegawai(historyLoop.getTipePegawaiName());
                }else{
                    pengalamanKerja.setTipePegawai("-");
                }

                String golongan = "-";
                String gandul = "-";
                if(historyLoop.getGolonganName() != null && !historyLoop.getGolonganName().equalsIgnoreCase("")){
                    golongan = historyLoop.getGolonganName();
                }
                pengalamanKerja.setGolonganName(golongan);
                pengalamanKerjas.add(pengalamanKerja);
            }
        }

        return pengalamanKerjas;
    }

    @Override
    public List<Reward> searchDataReward(String nip) throws GeneralBOException {
        List<ImRewardEntity> imRewardEntities= new ArrayList<>();
        List<Reward> rewards = new ArrayList<>();
        imRewardEntities = rewardDao.getAllData(nip);

        if(imRewardEntities.size() > 0){
            for(ImRewardEntity imRewardEntity : imRewardEntities){
                Reward reward = new Reward();
                reward.setRewardId(imRewardEntity.getRewardId());
                reward.setNip(imRewardEntity.getNip());
                reward.setTanggal(imRewardEntity.getTanggal());
                reward.setStTanggal(CommonUtil.convertDateToString(imRewardEntity.getTanggal()));
                reward.setKeterangan(imRewardEntity.getKeterangan());
                reward.setJenis(imRewardEntity.getJenis());

                rewards.add(reward);
            }
        }

        return rewards;
    }

    @Override
    public List<Sertifikat> searchDataSertifikat(String nip) throws GeneralBOException {
        List<ImSertifikatEntity> imSertifikatEntities= new ArrayList<>();
        List<Sertifikat> sertifikats = new ArrayList<>();
        ImRewardEntity imRewardEntity = null;
        try {
            // Get data from database by ID
            imSertifikatEntities = sertifikatDao.getAllData(nip);
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.searchDataSertifikat] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data Pengalaman by Kode , please inform to your admin...," + e.getMessage());
        }

        if(imSertifikatEntities != null){
            for(ImSertifikatEntity imSertifikatEntity : imSertifikatEntities){
                Sertifikat sertifikat = new Sertifikat();
                sertifikat.setSertifikatId(imSertifikatEntity.getSertifikatId());
                sertifikat.setJenis(imSertifikatEntity.getJenis());
                sertifikat.setTanggalPengesahan(imSertifikatEntity.getTanggalPengesahan());
                sertifikat.setMasaBerlaku(imSertifikatEntity.getMasaBerlaku());
                sertifikat.setMasaBerakhir(imSertifikatEntity.getMasaBerakhir());

                sertifikat.setJumlahHari(imSertifikatEntity.getJumlahHari());

                if(imSertifikatEntity.getTanggalPengesahan() != null){
                    sertifikat.setStTanggalPengesahan(CommonUtil.convertDateToString(imSertifikatEntity.getTanggalPengesahan()));
                }else{
                    sertifikat.setStTanggalPengesahan("-");
                }

                if(imSertifikatEntity.getMasaBerlaku() != null){
                    sertifikat.setStMasaBerlaku(CommonUtil.convertDateToString(imSertifikatEntity.getMasaBerlaku()));
                }else{
                    sertifikat.setStMasaBerlaku("-");
                }

                if(imSertifikatEntity.getMasaBerakhir() != null){
                    sertifikat.setStMasaBerakhir(CommonUtil.convertDateToString(imSertifikatEntity.getMasaBerakhir()));
                }else{
                    sertifikat.setStMasaBerakhir("-");
                }

                sertifikat.setNama(imSertifikatEntity.getNama());
                sertifikat.setLembaga(imSertifikatEntity.getLembaga());
                sertifikat.setTempatPelaksana(imSertifikatEntity.getTempatPelaksana());
                sertifikat.setNilai(imSertifikatEntity.getNilai());
                sertifikat.setLulus(imSertifikatEntity.getLulus());
                sertifikat.setPrestasiGrade(imSertifikatEntity.getPrestasiGrade());
                sertifikat.setNip(imSertifikatEntity.getNip());

                sertifikats.add(sertifikat);
            }
        }

        return sertifikats;
    }

    @Override
    public List<PelatihanJabatanUser> searchDataPelatihanjabatanUser(String nip) throws GeneralBOException {
        List<ImtPelatihanJabatanUserEntity> imPelatihan= new ArrayList<>();
        List<PelatihanJabatanUser> pelatihanJabatanUsers = new ArrayList<>();
        imPelatihan = pelatihanJabatanUserDao.getAllData(nip);

        if(imPelatihan != null){
            for(ImtPelatihanJabatanUserEntity pelatihanLoop : imPelatihan){
                PelatihanJabatanUser pelatihanJabatanUser = new PelatihanJabatanUser();
                pelatihanJabatanUser.setPelatihanUserId(pelatihanLoop.getPelatihanUserId());
                pelatihanJabatanUser.setPelatihanJabatanId(pelatihanLoop.getPelatihanJabatanId());
                if(pelatihanLoop.getPelatihanJabatanId() != null && !pelatihanLoop.getPelatihanJabatanId().equalsIgnoreCase("")){
                    if(pelatihanLoop.getImPelatihanJabatanEntitiy() != null){
                        pelatihanJabatanUser.setPelatihanJabatanName(pelatihanLoop.getImPelatihanJabatanEntitiy().getPelatihanName());
                    }else{
                        pelatihanJabatanUser.setPelatihanJabatanName("-");
                    }
                }else{
                    pelatihanJabatanUser.setPelatihanJabatanName("-");
                }

                pelatihanJabatanUser.setLembaga(pelatihanLoop.getLembaga());
                pelatihanJabatanUser.setAngkatan(pelatihanLoop.getAngkatan());
                pelatihanJabatanUser.setTahun(pelatihanLoop.getTahun());
                pelatihanJabatanUser.setStatus(pelatihanLoop.getStatus());
                pelatihanJabatanUser.setNilai(pelatihanLoop.getNilai());
                pelatihanJabatanUser.setNip(pelatihanLoop.getNip());

                pelatihanJabatanUsers.add(pelatihanJabatanUser);
            }
        }

        return pelatihanJabatanUsers;
    }

    @Override
    public PelatihanJabatanUser searchPelatihan(String pelatihanId) throws GeneralBOException {
        ImtPelatihanJabatanUserEntity imPelatihan= new ImtPelatihanJabatanUserEntity();
        List<PelatihanJabatanUser> pelatihanJabatanUsers = new ArrayList<>();
        imPelatihan = pelatihanJabatanUserDao.getById("pelatihanUserId" ,pelatihanId);

        PelatihanJabatanUser pelatihanJabatanUser = new PelatihanJabatanUser();
        if(imPelatihan != null){
            pelatihanJabatanUser.setPelatihanUserId(imPelatihan.getPelatihanUserId());
            pelatihanJabatanUser.setPelatihanJabatanId(imPelatihan.getPelatihanJabatanId());
            if(imPelatihan.getPelatihanJabatanId() != null && !imPelatihan.getPelatihanJabatanId().equalsIgnoreCase("")){
                if(imPelatihan.getImPelatihanJabatanEntitiy() != null){
                    pelatihanJabatanUser.setPelatihanJabatanName(imPelatihan.getImPelatihanJabatanEntitiy().getPelatihanName());
                }else{
                    pelatihanJabatanUser.setPelatihanJabatanName("-");
                }
            }else{
                pelatihanJabatanUser.setPelatihanJabatanName("-");
            }

            pelatihanJabatanUser.setLembaga(imPelatihan.getLembaga());
            pelatihanJabatanUser.setAngkatan(imPelatihan.getAngkatan());
            pelatihanJabatanUser.setTahun(imPelatihan.getTahun());
            pelatihanJabatanUser.setStatus(imPelatihan.getStatus());
            pelatihanJabatanUser.setNilai(imPelatihan.getNilai());
            pelatihanJabatanUser.setNip(imPelatihan.getNip());

        }

        return pelatihanJabatanUser;
    }

    @Override
    public void saveEditReward(Reward bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveEditReward] start process >>>");

        if (bean!=null) {
            String rewardId = bean.getRewardId();

            ImRewardEntity imRewardEntity = null;
            try {
                // Get data from database by ID
                imRewardEntity = rewardDao.getById("rewardId", rewardId);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
            }

            if (imRewardEntity != null) {
                imRewardEntity.setRewardId(bean.getRewardId());
                imRewardEntity.setJenis(bean.getJenis());
                imRewardEntity.setKeterangan(bean.getKeterangan());

                if(bean.getStTanggal() != null && !"".equalsIgnoreCase(bean.getStTanggal())){
                    imRewardEntity.setTanggal(CommonUtil.convertStringToDate(bean.getStTanggal()));
                }

                imRewardEntity.setFlag(bean.getFlag());
                imRewardEntity.setAction(bean.getAction());
                imRewardEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imRewardEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    rewardDao.updateAndSave(imRewardEntity);
                } catch (HibernateException e) {
                    logger.error("[BiodataBoImpl.saveEditReward] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengalamanKerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengalamanKerjaBoImpl.saveEdit] Error, not found data PengalamanKerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengalamanKerja with request id, please check again your data ...");
            }
        }
        logger.info("[BiodataBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void saveEditSertifikat(Sertifikat bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveEditSertifikat] start process >>>");

        if (bean!=null) {
            String sertifikatId = bean.getSertifikatId();

            ImSertifikatEntity imSertifikatEntity = null;
            try {
                // Get data from database by ID
                imSertifikatEntity = sertifikatDao.getById("sertifikatId", sertifikatId);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveEditSertifikat] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
            }

            if (imSertifikatEntity != null) {
                imSertifikatEntity.setSertifikatId(sertifikatId);
                imSertifikatEntity.setNip(bean.getNip());
                imSertifikatEntity.setJenis(bean.getJenis());
                imSertifikatEntity.setNama(bean.getNama());
                imSertifikatEntity.setLembaga(bean.getLembaga());
                imSertifikatEntity.setTempatPelaksana(bean.getTempatPelaksana());
                imSertifikatEntity.setNilai(bean.getNilai());
                imSertifikatEntity.setLulus(bean.getLulus());
                imSertifikatEntity.setPrestasiGrade(bean.getPrestasiGrade());
                imSertifikatEntity.setJenis(bean.getJenis());

                imSertifikatEntity.setTanggalPengesahan(bean.getTanggalPengesahan());
                imSertifikatEntity.setMasaBerlaku(bean.getMasaBerlaku());
                imSertifikatEntity.setMasaBerakhir(bean.getMasaBerakhir());
                imSertifikatEntity.setJumlahHari(bean.getJumlahHari());

                imSertifikatEntity.setFlag(bean.getFlag());
                imSertifikatEntity.setAction(bean.getAction());
                imSertifikatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSertifikatEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    sertifikatDao.updateAndSave(imSertifikatEntity);
                } catch (HibernateException e) {
                    logger.error("[BiodataBoImpl.saveEditReward] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengalamanKerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengalamanKerjaBoImpl.saveEdit] Error, not found data PengalamanKerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengalamanKerja with request id, please check again your data ...");
            }
        }
        logger.info("[BiodataBoImpl.saveEditSertifikat] end process <<<");
    }

    @Override
    public void saveEditPelatihan(PelatihanJabatanUser bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveEditPelatihan] start process >>>");

        if (bean!=null) {
            String pelatihanId = bean.getPelatihanUserId();

            ImtPelatihanJabatanUserEntity imtPelatihanJabatanUserEntity = null;
            try {
                // Get data from database by ID
                imtPelatihanJabatanUserEntity = pelatihanJabatanUserDao.getById("pelatihanUserId", pelatihanId);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveEditPelatihan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
            }

            if (imtPelatihanJabatanUserEntity != null) {
                imtPelatihanJabatanUserEntity.setPelatihanJabatanId(bean.getPelatihanJabatanId());
                imtPelatihanJabatanUserEntity.setLembaga(bean.getLembaga());
                imtPelatihanJabatanUserEntity.setAngkatan(bean.getAngkatan());
                imtPelatihanJabatanUserEntity.setTahun(bean.getTahun());
                imtPelatihanJabatanUserEntity.setStatus(bean.getStatus());
                imtPelatihanJabatanUserEntity.setNilai(bean.getNilai());

                imtPelatihanJabatanUserEntity.setFlag(bean.getFlag());
                imtPelatihanJabatanUserEntity.setAction(bean.getAction());
                imtPelatihanJabatanUserEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imtPelatihanJabatanUserEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    pelatihanJabatanUserDao.updateAndSave(imtPelatihanJabatanUserEntity);
                } catch (HibernateException e) {
                    logger.error("[BiodataBoImpl.saveEditPelatihan] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengalamanKerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengalamanKerjaBoImpl.saveEditPelatihan] Error, not found data PengalamanKerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengalamanKerja with request id, please check again your data ...");
            }
        }
        logger.info("[BiodataBoImpl.saveEditPelatihan] end process <<<");
    }

    @Override
    public void saveDeletePengalamanKerja(PengalamanKerja bean) throws GeneralBOException {
        logger.info("[PengalamanKerjaBoImpl.saveDelete] start process >>>");

        if (bean!=null) {
            String PengalamanKerjaId = bean.getPengalamanId();
            ImtHrisHistoryJabatanPegawaiEntity imPengalamanKerjaEntity = null;
            try {
                // Get data from database by ID
                imPengalamanKerjaEntity = historyJabatanPegawaiDao.getById("historyJabatanId", PengalamanKerjaId);
            } catch (HibernateException e) {
                logger.error("[PengalamanKerjaBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
            }

            if (imPengalamanKerjaEntity != null) {

                imPengalamanKerjaEntity.setFlag(bean.getFlag());
                imPengalamanKerjaEntity.setAction(bean.getAction());
                imPengalamanKerjaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPengalamanKerjaEntity.setLastUpdate(bean.getLastUpdate());

                String flag;

                String tahun = "";
                String smkAda = "N";
                if(imPengalamanKerjaEntity.getTahun().length() > 4){
                    String strBln[] = imPengalamanKerjaEntity.getTahun().split("-");
                    tahun = strBln[2];
                }else{
                    tahun = bean.getStTtahunMasuk();
                }


                try {
                    historyJabatanPegawaiDao.updateAndSave(imPengalamanKerjaEntity);
                } catch (HibernateException e) {
                    logger.error("[PengalamanKerjaBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengalamanKerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengalamanKerjaBoImpl.saveDelete] Error, not found data PengalamanKerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengalamanKerja with request id, please check again your data ...");
            }
        }
        logger.info("[PengalamanKerjaBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveDeleteReward(Reward bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveDelete] start process >>>");

        if (bean!=null) {
            String rewardId = bean.getRewardId();
            ImRewardEntity imRewardEntity = null;
            try {
                // Get data from database by ID
                imRewardEntity = rewardDao.getById("rewardId", rewardId);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveDeleteReward] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
            }

            if (imRewardEntity != null) {

                imRewardEntity.setFlag(bean.getFlag());
                imRewardEntity.setAction(bean.getAction());
                imRewardEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imRewardEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    rewardDao.updateAndSave(imRewardEntity);
                } catch (HibernateException e) {
                    logger.error("[PengalamanKerjaBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengalamanKerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengalamanKerjaBoImpl.saveDelete] Error, not found data PengalamanKerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengalamanKerja with request id, please check again your data ...");
            }
        }
        logger.info("[BiodataBoImpl.saveDeleteReward] end process <<<");
    }

    @Override
    public void saveDeleteSertifikat(Sertifikat bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveDeleteSertifikat] start process >>>");

        if (bean!=null) {
            String sertifikatId = bean.getSertifikatId();
            ImSertifikatEntity imSertifikatEntity = null;
            try {
                // Get data from database by ID
                imSertifikatEntity = sertifikatDao.getById("sertifikatId", sertifikatId);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveDeleteSertifikat] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
            }

            if (imSertifikatEntity != null) {

                imSertifikatEntity.setFlag(bean.getFlag());
                imSertifikatEntity.setAction(bean.getAction());
                imSertifikatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSertifikatEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    sertifikatDao.updateAndSave(imSertifikatEntity);
                } catch (HibernateException e) {
                    logger.error("[PengalamanKerjaBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengalamanKerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengalamanKerjaBoImpl.saveDelete] Error, not found data PengalamanKerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengalamanKerja with request id, please check again your data ...");
            }
        }
        logger.info("[BiodataBoImpl.saveDeleteSertifikat] end process <<<");
    }

    @Override
    public void saveDeletePelatihan(PelatihanJabatanUser bean) throws GeneralBOException {
        logger.info("[BiodataBoImpl.saveDeletePelatihan] start process >>>");

        if (bean!=null) {
            String pelatihanId = bean.getPelatihanUserId();
            ImtPelatihanJabatanUserEntity imtPelatihanJabatanUserEntity = null;
            try {
                // Get data from database by ID
                imtPelatihanJabatanUserEntity = pelatihanJabatanUserDao.getById("pelatihanUserId", pelatihanId);
            } catch (HibernateException e) {
                logger.error("[BiodataBoImpl.saveDeleteSertifikat] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Pengalaman by Kode Pengalaman, please inform to your admin...," + e.getMessage());
            }

            if (imtPelatihanJabatanUserEntity != null) {

                imtPelatihanJabatanUserEntity.setFlag(bean.getFlag());
                imtPelatihanJabatanUserEntity.setAction(bean.getAction());
                imtPelatihanJabatanUserEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imtPelatihanJabatanUserEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    pelatihanJabatanUserDao.updateAndSave(imtPelatihanJabatanUserEntity);
                } catch (HibernateException e) {
                    logger.error("[PengalamanKerjaBoImpl.saveDeletePelatihan] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengalamanKerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengalamanKerjaBoImpl.saveDeletePelatihan] Error, not found data PengalamanKerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengalamanKerja with request id, please check again your data ...");
            }
        }
        logger.info("[BiodataBoImpl.saveDeletePelatihan] end process <<<");
    }

    private List<Biodata> searchDataBiodata(){
        List<Biodata> listComboBiodata = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<ImBiodataEntity> listPersonal = null;
        try {
            listPersonal = biodataDao.getListPersonal();
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImBiodataEntity imBiodataEntity : listPersonal) {
                Biodata itemComboBiodata = new Biodata();
                String date = "";
                if(imBiodataEntity.getTanggalAktif() != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = dateFormat.format(imBiodataEntity.getTanggalAktif());
                    itemComboBiodata.setStTanggalAktif(date);
                }
                itemComboBiodata.setNip(imBiodataEntity.getNip());
                itemComboBiodata.setGolongan(imBiodataEntity.getGolongan());
                itemComboBiodata.setTipePegawai(imBiodataEntity.getTipePegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setPoint(imBiodataEntity.getPoint());

                Map hsCriteria = new HashMap();
                hsCriteria.put("nip",imBiodataEntity.getNip());
                hsCriteria.put("flag","Y");

                List<ItPersonilPositionEntity> itPersonilPositionEntityList = null;
                try {
                    itPersonilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                }
                if (itPersonilPositionEntityList!=null){
                    for ( ItPersonilPositionEntity listOfPersonil:itPersonilPositionEntityList){
                        itemComboBiodata.setPositionId(listOfPersonil.getPositionId());

                        if(listOfPersonil.getPositionId() != null){
                            ImPosition posisi = positionDao.getById("positionId", listOfPersonil.getPositionId());
                            if(posisi != null){
                                if(posisi.getDepartmentId() != null){
                                    itemComboBiodata.setDivisi(posisi.getDepartmentId());
                                }else{
                                    itemComboBiodata.setDivisi("");
                                }
                            }else{
                                itemComboBiodata.setDivisi("");
                            }
                        }
                        itemComboBiodata.setPositionId2(listOfPersonil.getPositionId());
                        itemComboBiodata.setBranch(listOfPersonil.getBranchId());
                        itemComboBiodata.setPjs(listOfPersonil.getPjs());
                    }
                }
                itemComboBiodata.setTipePegawai(imBiodataEntity.getTipePegawai());
                itemComboBiodata.setMasaGiling(imBiodataEntity.getMasaGiling());
                itemComboBiodata.setTanggalAktif(imBiodataEntity.getTanggalAktif());
                itemComboBiodata.setGolonganId(imBiodataEntity.getGolongan());
                listComboBiodata.add(itemComboBiodata);
            }
        }
        session.setAttribute("listDataBiodata", listComboBiodata);
        return listComboBiodata;
    }

    @Override
    public List<Biodata> getListOfPersonilBagian(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");
        List<Biodata> listComboBiodataBagian = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Biodata> listComboBiodata = (List<Biodata>) session.getAttribute("listDataBiodata");

        if(listComboBiodata == null){
            listComboBiodata = searchDataBiodata();
        }

        String strBagian = CommonUtil.convertIdBagian(CommonUtil.userIdLogin());
        List<StrukturJabatan> filterBagian = new ArrayList<>();
        for(StrukturJabatan strukturJabatan: getPerBagianSys()){
            if(strukturJabatan.getBagian().equalsIgnoreCase(strBagian)){
                filterBagian.add(strukturJabatan);
            }
        }

        for(Biodata biodata: listComboBiodata){
            for(StrukturJabatan listBagian: filterBagian){
                if(biodata.getNip().equalsIgnoreCase(listBagian.getNip())){
                    listComboBiodataBagian.add(biodata);
                }
            }
        }


        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboBiodataBagian;
    }

    private List<StrukturJabatan> strukturJabatanList = new ArrayList();
    private List <StrukturJabatan> getPerBagianSys() throws GeneralBOException {
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities  = strukturJabatanDao.getPerBagianDao();
        List<StrukturJabatan> strukturJabatans  = new ArrayList<>();
        if(imStrukturJabatanEntities.size() > 0){
            strukturJabatanList.clear();
            for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){

                StrukturJabatan itemComboStrukturJabatan = new StrukturJabatan();

                itemComboStrukturJabatan.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                itemComboStrukturJabatan.setParentId(imStrukturJabatanEntity.getParentId());
                itemComboStrukturJabatan.setBranchId(imStrukturJabatanEntity.getBranchId());
                itemComboStrukturJabatan.setPositionId(imStrukturJabatanEntity.getPositionId());
                itemComboStrukturJabatan.setPositionName(imStrukturJabatanEntity.getPositionName());
                itemComboStrukturJabatan.setPositionKelompokId(imStrukturJabatanEntity.getKelompokId());
                itemComboStrukturJabatan.setNip(imStrukturJabatanEntity.getNip());
                itemComboStrukturJabatan.setName(imStrukturJabatanEntity.getNamaPegawai());

                getListStruktur(imStrukturJabatanEntity.getStrukturJabatanId());

                String bagian[] = itemComboStrukturJabatan.getPositionName().split(" ");
                String bagian1 = "Bagian ";
                String bagian2 = "Bagian ";

                for(int a = 0 ; a < bagian.length; a++){
                    if(bagian[a].equalsIgnoreCase("Bagian")){
                        for(int b = a ; b < bagian.length - 1; b++){
                            bagian1 += " " + bagian[b + 1];
                        }
                        break;
                    }
                }

                String strBagian[] = bagian1.split(" ");
                for(int a = 0 ; a < strBagian.length; a++){
                    if(strBagian[a].equalsIgnoreCase("dan")){
                        for(int b = a ; b < strBagian.length - 1; b++){
                            bagian2 += " " + strBagian[b + 1];
                        }
                        break;
                    }
                }

                itemComboStrukturJabatan.setBagian(bagian1);
                strukturJabatans.add(itemComboStrukturJabatan);

                boolean ada = false;
                for(StrukturJabatan strukturJabatan: strukturJabatanList){
                    for(StrukturJabatan strukturJabatan1 : strukturJabatans){
                        if(strukturJabatan.getNip().equalsIgnoreCase(strukturJabatan1.getNip())){
                            ada = true;
                            break;
                        }
                    }
                    if(ada == false){
                        strukturJabatan.setBagian(bagian1);
                        strukturJabatans.add(strukturJabatan);
                    }
                    ada = false;
                }
            }
        }
        return strukturJabatans;
    }

    private String getListStruktur(String id){
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities = null;
        String hasil = "";
        imStrukturJabatanEntities = strukturJabatanDao.getIdStrukturJabatan(id);
        if(imStrukturJabatanEntities.size() > 0){
            for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){
                StrukturJabatan strukturJabatan1 = new StrukturJabatan();
                strukturJabatan1.setNip(imStrukturJabatanEntity.getNip());
                strukturJabatan1.setPositionName(imStrukturJabatanEntity.getPositionName());
                strukturJabatan1.setBagian(imStrukturJabatanEntity.getPositionName());

                hasil = imStrukturJabatanEntity.getStrukturJabatanId();
                strukturJabatanList.add(strukturJabatan1);
                if(imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL02") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL03") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL04") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL05") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL06") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL07")){
                    strukturJabatanList.add(strukturJabatan1);
                }
                getListStruktur(getListStruktur(hasil));
            }
        }
        return hasil;
    }
    @Override
    public List<Biodata> getListOfPersonilOnlyName(String query, String branchId) throws GeneralBOException {
        logger.info("[BiodataBoImpl.getListOfPersonilOnlyName] start process >>>");

        List<Biodata> listComboBiodata = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBiodataEntity> listPersonal = null;
        try {
                listPersonal = biodataDao.getListPersonalByBranch(query, branchId);
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.getListOfPersonilOnlyName] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        if (listPersonal != null) {
            for (ImBiodataEntity imBiodataEntity : listPersonal) {
                Biodata itemComboBiodata = new Biodata();
                itemComboBiodata.setNip(imBiodataEntity.getNip());
                itemComboBiodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                listComboBiodata.add(itemComboBiodata);

            }
        }
        logger.info("[BiodataBoImpl.getListOfPersonilOnlyName] end process <<<");
        return listComboBiodata;
    }

    @Override
    public List<ImBiodataEntity> searchKaryawanDanBatihSys(Biodata biodata) throws GeneralBOException {
        List<ImBiodataEntity> imBiodataEntities = biodataDao.getDataBiodata(biodata.getNip(), biodata.getNip(), biodata.getBranch(), "", "", "Y");
        List<ImBiodataEntity> biodataList = new ArrayList<>();

        int noAnggota = 0;
        if (imBiodataEntities.size() > 0) {
            for (ImBiodataEntity biodataEntity : imBiodataEntities) {
                int batih = 1;
                noAnggota++;
                biodataEntity.setNoAnggota(noAnggota);
                biodataEntity.setBatih(batih);
                biodataEntity.setStatusKeluarga("");
                biodataEntity.setUmur(calculateAge(biodataEntity.getTanggalLahir()));
                biodataList.add(biodataEntity);

                List<ImKeluargaEntity> keluargaEntities = new ArrayList<>();
                keluargaEntities = keluargaDao.getListKeluargaById("", biodataEntity.getNip());
                if (keluargaEntities.size() > 0) {
                    for (ImKeluargaEntity keluargaEntity : keluargaEntities) {
                        batih++;
                        ImBiodataEntity imBiodataEntity = new ImBiodataEntity();

                        imBiodataEntity.setBatih(batih);
                        imBiodataEntity.setNamaPegawai(keluargaEntity.getName());
                        if (keluargaEntity.getTanggalLahir() != null) {
                            imBiodataEntity.setTanggalLahir(keluargaEntity.getTanggalLahir());
                            imBiodataEntity.setStTanggalLahir(CommonUtil.convertDateToString(keluargaEntity.getTanggalLahir()));
                        }
                        imBiodataEntity.setStatusKeluarga(keluargaEntity.getStatusKeluarga());

                        imBiodataEntity.setUmur(calculateAge(keluargaEntity.getTanggalLahir()));
                        biodataList.add(imBiodataEntity);
                    }
                }
            }
        }
        return biodataList;
    }
    @Override
    public List<Biodata> getBiodataforAbsensi(String branchId, String divisiId, String bagianId, String nip) {
        List<Biodata> result = new ArrayList<>();
        try {
            result = biodataDao.getBiodataListForAbsensi(branchId,divisiId,bagianId,nip);
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.getListOfPersonilOnlyName] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        return result;
    }
    @Override
    public List<Biodata> getBiodataforUangMakan(String branchId, String divisiId, String bagianId, String nip) {
        List<Biodata> result = new ArrayList<>();
        try {
            result = biodataDao.getBiodataListForUangMakan(branchId,divisiId,bagianId,nip);
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.getListOfPersonilOnlyName] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        return result;
    }
    @Override
    public List<Biodata> getBiodataByBagian(String branchId, String divisiId, String bagianId, String nip) {
        List<Biodata> result = new ArrayList<>();
        try {
            result = biodataDao.getBiodataListDefault(branchId,divisiId,bagianId,nip);
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.getBiodataByBagian] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        return result;
    }
    /*This method for autocomplete nip in form mobile*/
    @Override
    public List<Biodata> findBiodataLikeNip(String keyword) {
        logger.info("[BiodataBoImpl.findBiodataLikeNip] start process >>>");
        List<Biodata> biodataList = new ArrayList<>();
        List<ImBiodataEntity> imBiodataEntitys = null;

        try {
            imBiodataEntitys = biodataDao.findBiodataLikeNip(keyword);
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.findBiodataLikeNip] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data Biodata by Kode Biodata, please inform to your admin...," + e.getMessage());
        }
        logger.info("[BiodataBoImpl.findBiodataLikeNip] end process <<<");

        if (imBiodataEntitys != null){
            for (ImBiodataEntity imBiodataEntity : imBiodataEntitys) {
                Biodata biodata = new Biodata();
                biodata.setNip(imBiodataEntity.getNip());
                biodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());

                biodataList.add(biodata);
            }
        }

        return biodataList;
    }


    /*This method for autocomplete nip in form mobile*/
    @Override
    public List<Biodata> findBiodataLikeNama(String keyword,String branchId) {
        logger.info("[BiodataBoImpl.findBiodataLikeNama] start process >>>");
        List<Biodata> biodataList = new ArrayList<>();
        List<ImBiodataEntity> imBiodataEntitys = null;

        try {
            imBiodataEntitys = biodataDao.findBiodataLikeName(keyword,branchId);
        } catch (HibernateException e) {
            logger.error("[BiodataBoImpl.findBiodataLikeNama] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data Biodata by Kode Biodata, please inform to your admin...," + e.getMessage());
        }

        if (imBiodataEntitys != null){
            for (ImBiodataEntity imBiodataEntity : imBiodataEntitys) {
                Biodata biodata = new Biodata();
                biodata.setNip(imBiodataEntity.getNip());
                biodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());

                biodataList.add(biodata);
            }
        }
        logger.info("[BiodataBoImpl.findBiodataLikeNama] end process <<<");
        return biodataList;
    }

    public void setIjinKeluarDao(IjinKeluarDao ijinKeluarDao) {
        this.ijinKeluarDao = ijinKeluarDao;
    }

    public IjinKeluarDao getIjinKeluarDao() {
        return ijinKeluarDao;
    }

    public void setCutiPegawai(RsKerjasamaDao cutiPegawai) {
        this.cutiPegawai = cutiPegawai;
    }

    public RsKerjasamaDao getCutiPegawai() {
        return cutiPegawai;
    }

    public void setNotifikasi(RsKerjasamaDao notifikasi) {
        this.notifikasi = notifikasi;
    }

    public RsKerjasamaDao getNotifikasi() {
        return notifikasi;
    }


    private String calculateAge(Date tglLahir){
        DateTime tglSekarang = new DateTime();
        DateTime tanggalLahir = new DateTime(tglLahir);

        BigInteger jumlahThnTetap = BigInteger.valueOf(0);
        BigInteger jumlahBlnTetap = BigInteger.valueOf(0);
        BigInteger jumlahHariTetap = BigInteger.valueOf(0);


        jumlahThnTetap = BigInteger.valueOf(Years.yearsBetween(tanggalLahir, tglSekarang).getYears());
        jumlahBlnTetap = BigInteger.valueOf(Months.monthsBetween(tanggalLahir, tglSekarang).getMonths());
        jumlahBlnTetap = jumlahBlnTetap.mod(BigInteger.valueOf(12));
        jumlahHariTetap= BigInteger.valueOf(Days.daysBetween(tanggalLahir, tglSekarang).getDays());
        jumlahHariTetap= jumlahHariTetap.mod(BigInteger.valueOf(30));

        String hasil = jumlahThnTetap + " Tahun, " + jumlahBlnTetap + " Bulan, " + jumlahHariTetap + " Hari.";

        return hasil ;
    }

    @Override
    public List<Keluarga> listKeluarga(String nip) {
        List<ImKeluargaEntity> keluargaEntities = new ArrayList<>();
        List<Keluarga> keluargas = new ArrayList<>();

        keluargaEntities = keluargaDao.getListKeluargaById("", nip);
        if (keluargaEntities.size() > 0) {
            for (ImKeluargaEntity keluargaEntity : keluargaEntities) {
                Keluarga keluarga = new Keluarga();
                keluarga.setName(keluargaEntity.getName());

                if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("I")){
                    keluarga.setStatusKeluargaName("Istri");
                }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("S")){
                    keluarga.setStatusKeluargaName("Suami");
                }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A1")){
                    keluarga.setStatusKeluargaName("Anak Pertama");
                }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A2")){
                    keluarga.setStatusKeluargaName("Anak Kedua");
                }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A3")){
                    keluarga.setStatusKeluargaName("Anak Ketiga");
                }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A4")){
                    keluarga.setStatusKeluargaName("Anak Keempat");
                }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A5")){
                    keluarga.setStatusKeluargaName("Anak Kelima");
                }else {
                    keluarga.setStatusKeluargaName("-");
                }

                if(keluargaEntity.getTanggalLahir() != null){
                    keluarga.setStTanggalLahir(CommonUtil.convertDateToString(keluargaEntity.getTanggalLahir()));
                }else{
                    keluarga.setStTanggalLahir("-");
                }

                if(keluargaEntity.getGender() != null){
                    if(keluargaEntity.getGender().equalsIgnoreCase("L")){
                        keluarga.setGender("Laki-laki");
                    }else if(keluargaEntity.getGender().equalsIgnoreCase("P")){
                        keluarga.setGender("Perempuan");
                    }else{
                        keluarga.setGender("-");
                    }
                }else{
                    keluarga.setGender("-");
                }

                keluargas.add(keluarga);
            }
        }

        return keluargas;
    }

    @Override
    public List<HistoryJabatanPegawai> listRiwayatPekerjaan(String nip) {
        List<HistoryJabatanPegawai> listHistory = new ArrayList<>();
        List<ImtHrisHistoryJabatanPegawaiEntity> listImtHistory = null;

        listImtHistory = historyJabatanPegawaiDao.getDataHistoryJabatan(nip);
        if(listImtHistory != null){
            for(ImtHrisHistoryJabatanPegawaiEntity imtHistory : listImtHistory){
                HistoryJabatanPegawai historyJabatanPegawai = new HistoryJabatanPegawai();
                historyJabatanPegawai.setHistoryJabatanId(imtHistory.getHistoryJabatanId());
                historyJabatanPegawai.setNip(imtHistory.getNip());
                if(imtHistory.getBidangName() != null){
                    historyJabatanPegawai.setBidangName(imtHistory.getBidangName());
                }else{
                    historyJabatanPegawai.setBidangName("-");
                }
                if(imtHistory.getTipePegawaiName() != null){
                    historyJabatanPegawai.setTipePegawaiName(imtHistory.getTipePegawaiName());
                }else{
                    historyJabatanPegawai.setTipePegawaiName("-");
                }
                historyJabatanPegawai.setPositionName(imtHistory.getPositionName());
                if(imtHistory.getBranchName() != null){
                    historyJabatanPegawai.setBranchName(imtHistory.getBranchName());
                }else{
                    historyJabatanPegawai.setBranchName("-");
                }
                historyJabatanPegawai.setTahun(imtHistory.getTahun());
                historyJabatanPegawai.setTanggal(imtHistory.getTanggal());
                historyJabatanPegawai.setPjsFlag(imtHistory.getPjsFlag());
                listHistory.add(historyJabatanPegawai);
            }
        }

        return listHistory;
    }

    @Override
    public List<Study> listStudy(String nip) {
        List<ImStudyEntity> imStudyEntities = new ArrayList<>();
        List<Study> studies = new ArrayList<>();

        imStudyEntities = studyDao.getListStudyByNip(nip);
        for(ImStudyEntity imStudyEntity: imStudyEntities){
            Study study = new Study();
            study.setTypeStudy(imStudyEntity.getTypeStudy());
            study.setStudyName(imStudyEntity.getStudyName());

            if(imStudyEntity.getFakultasId() != null){
                study.setFakultasName(imStudyEntity.getImStudyJurusanEntity().getJurusanName());
            }else{
                study.setFakultasName("-");
            }

            if(imStudyEntity.getProgramStudy() != null){
                study.setProgramStudy(imStudyEntity.getProgramStudy());
            }else{
                study.setProgramStudy("-");
            }

            if(imStudyEntity.getTahunAkhir() != null){
                study.setTahunAkhir(imStudyEntity.getTahunAkhir());
            }else{
                study.setTahunAkhir("-");
            }

            studies.add(study);
        }

        return studies;
    }
    public List<Biodata> getAllListOfPersonil(String query, String branchId) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Biodata> listComboBiodata = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBiodataEntity> listPersonal = null;
        try {
            if (!"".equalsIgnoreCase(branchId)){
                listPersonal = biodataDao.getAllBiodata("",query,branchId,"","","Y");
            } else {
                listPersonal = biodataDao.getAllBiodata("",query,"","","","Y");
            }
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImBiodataEntity imBiodataEntity : listPersonal) {
                Biodata itemComboBiodata = new Biodata();
                String date = "";
                if(imBiodataEntity.getTanggalAktif() != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = dateFormat.format(imBiodataEntity.getTanggalAktif());
                    itemComboBiodata.setStTanggalAktif(date);
                }
                itemComboBiodata.setNip(imBiodataEntity.getNip());
                itemComboBiodata.setGolongan(imBiodataEntity.getGolongan());
                itemComboBiodata.setTipePegawai(imBiodataEntity.getTipePegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setPoint(imBiodataEntity.getPoint());

                itemComboBiodata.setPositionId2(imBiodataEntity.getPosisiId());
                itemComboBiodata.setBranch(imBiodataEntity.getBranchId());
                itemComboBiodata.setPjs(imBiodataEntity.getPjs());
                itemComboBiodata.setPositionId(imBiodataEntity.getPosisiId());
                itemComboBiodata.setDivisi(imBiodataEntity.getDivisiId());
                itemComboBiodata.setBagianId(imBiodataEntity.getBagianId());

                if (itemComboBiodata.getDivisi()==null){
                    itemComboBiodata.setDivisi("");
                }

                if (itemComboBiodata.getBagianId()==null){
                    itemComboBiodata.setBagianId("");
                }

//                itemComboBiodata.setDivisi(imBiodataEntity.getDivisi());
                itemComboBiodata.setTipePegawai(imBiodataEntity.getTipePegawai());
                itemComboBiodata.setMasaGiling(imBiodataEntity.getMasaGiling());
                itemComboBiodata.setTanggalAktif(imBiodataEntity.getTanggalAktif());
                itemComboBiodata.setGolonganId(imBiodataEntity.getGolongan());
//                itemComboBiodata.setPositionId(imBiodataEntity.getPositionId());
                listComboBiodata.add(itemComboBiodata);

            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboBiodata;
    }
    public String cekStatus(String golonganId, String noKtp)throws GeneralBOException{
        String status ="";
        ImBiodataEntity skalaGajiEntity = new ImBiodataEntity();
        ImBiodataEntity skalaGajiEntity2 = new ImBiodataEntity();
        try {
            skalaGajiEntity = biodataDao.getById("nip", golonganId);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (skalaGajiEntity!=null){

            status = "exist";
        }else{
            skalaGajiEntity2 = biodataDao.getById("noKtp", noKtp);
            if (skalaGajiEntity2!=null){
                status = "exist";
            }else{
                status="notExits";
            }
        }
        return status;
    }
}