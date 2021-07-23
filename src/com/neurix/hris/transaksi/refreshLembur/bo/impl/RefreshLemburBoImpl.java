package com.neurix.hris.transaksi.refreshLembur.bo.impl;

import com.neurix.authorization.company.dao.CompanyDao;
import com.neurix.authorization.company.model.ImCompany;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.jenisPegawai.dao.JenisPegawaiDao;
import com.neurix.hris.master.payrollSkalaGaji.dao.PayrollSkalaGajiDao;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.payrollSkalaGajiPkwt.dao.PayrollSkalaGajiPkwtDao;
import com.neurix.hris.master.payrollSkalaGajiPkwt.model.ImPayrollSkalaGajiPkwtEntity;
import com.neurix.hris.transaksi.absensi.dao.AbsensiPegawaiDao;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawaiEntity;
import com.neurix.hris.transaksi.lembur.dao.JamLemburDao;
import com.neurix.hris.transaksi.lembur.dao.PengaliFaktorLemburDao;
import com.neurix.hris.transaksi.lembur.model.JamLemburEntity;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.lembur.model.PengaliFaktorLembur;
import com.neurix.hris.transaksi.lembur.model.PengaliFaktorLemburEntity;
import com.neurix.hris.transaksi.payroll.dao.PayrollDao;
import com.neurix.hris.transaksi.payroll.model.ItHrisPayrollEntity;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.refreshLembur.bo.RefreshLemburBo;
import com.neurix.hris.transaksi.refreshLembur.dao.RefreshLemburDao;
import com.neurix.hris.transaksi.refreshLembur.model.ItHrisRefreshLemburEntity;
import com.neurix.hris.transaksi.refreshLembur.model.RefreshLembur;
import org.hibernate.HibernateException;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

/**
 * Created by Aji Noor on 22/07/2021
 */
public class RefreshLemburBoImpl implements RefreshLemburBo {

    protected static transient Logger logger = Logger.getLogger(RefreshLemburBoImpl.class);
    private AbsensiPegawaiDao absensiPegawaiDao;
    private RefreshLemburDao refreshLemburDao;
    private PengaliFaktorLemburDao pengaliFaktorLemburDao;
    private PayrollSkalaGajiDao payrollSkalaGajiDao;
    private PayrollSkalaGajiPkwtDao payrollSkalaGajiPkwtDao;
    private CompanyDao companyDao;
    private JamLemburDao jamLemburDao;
    private PersonilPositionDao personilPositionDao;
    private JenisPegawaiDao jenisPegawaiDao;
    private PayrollDao payrollDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setAbsensiPegawaiDao(AbsensiPegawaiDao absensiPegawaiDao) {
        this.absensiPegawaiDao = absensiPegawaiDao;
    }

    public void setRefreshLemburDao(RefreshLemburDao refreshLemburDao) {
        this.refreshLemburDao = refreshLemburDao;
    }

    public void setPengaliFaktorLemburDao(PengaliFaktorLemburDao pengaliFaktorLemburDao) {
        this.pengaliFaktorLemburDao = pengaliFaktorLemburDao;
    }

    public void setPayrollSkalaGajiDao(PayrollSkalaGajiDao payrollSkalaGajiDao) {
        this.payrollSkalaGajiDao = payrollSkalaGajiDao;
    }

    public void setPayrollSkalaGajiPkwtDao(PayrollSkalaGajiPkwtDao payrollSkalaGajiPkwtDao) {
        this.payrollSkalaGajiPkwtDao = payrollSkalaGajiPkwtDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public void setJamLemburDao(JamLemburDao jamLemburDao) {
        this.jamLemburDao = jamLemburDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public void setJenisPegawaiDao(JenisPegawaiDao jenisPegawaiDao) {
        this.jenisPegawaiDao = jenisPegawaiDao;
    }

    public void setPayrollDao(PayrollDao payrollDao) {
        this.payrollDao = payrollDao;
    }

    @Override
    public void saveDelete(RefreshLembur bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(RefreshLembur bean) throws GeneralBOException {

    }

    @Override
    public RefreshLembur saveAdd(RefreshLembur bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<RefreshLembur> getByCriteria(RefreshLembur searchBean) throws GeneralBOException {
        logger.info("[RefreshLemburBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RefreshLembur> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getGroupRefreshId() != null && !"".equalsIgnoreCase(searchBean.getGroupRefreshId())) {
                hsCriteria.put("group_id", searchBean.getLemburId());
            }

            if (searchBean.getStTglAwalLembur() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTglAwalLembur()))) {
                Date tanggalDari = CommonUtil.convertToDate(searchBean.getStTglAwalLembur());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTglAkhirLembur() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTglAkhirLembur()))) {
                Date tanggalSelesai = CommonUtil.convertToDate(searchBean.getStTglAkhirLembur());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }
            if (searchBean.getFlagApprove() !=null&& !"".equalsIgnoreCase(String.valueOf(searchBean.getFlagApprove()))) {
                if (searchBean.getFlagApprove().equalsIgnoreCase("0")){
                    searchBean.setFlagApprove("0");
                }
                hsCriteria.put("approval_flag", searchBean.getFlagApprove());
            }
            hsCriteria.put("flag", "Y");


            List<ItHrisRefreshLemburEntity> refreshLemburEntityList = null;
            try {
                refreshLemburEntityList = refreshLemburDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RefreshLemburBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if(refreshLemburEntityList != null){
                RefreshLembur returnRefreshLembur;
                // Looping from dao to object and save in collection
                for(ItHrisRefreshLemburEntity refreshLemburEntity : refreshLemburEntityList){
                    returnRefreshLembur = new RefreshLembur();

                    returnRefreshLembur.setRefreshLemburId(refreshLemburEntity.getRefreshLemburId());
                    returnRefreshLembur.setGroupRefreshId(refreshLemburEntity.getGroupRefreshId());
                    returnRefreshLembur.setAbsensiPegawaiId(refreshLemburEntity.getAbsensiPegawaiId());
                    returnRefreshLembur.setTanggal(refreshLemburEntity.getTanggal());
                    returnRefreshLembur.setStTanggal(CommonUtil.convertDateToString(returnRefreshLembur.getTanggal()));
                    returnRefreshLembur.setNip(refreshLemburEntity.getNip());
                    returnRefreshLembur.setNama(refreshLemburEntity.getNama());
                    returnRefreshLembur.setJamMasuk(refreshLemburEntity.getJamMasuk());
                    returnRefreshLembur.setJamKeluar(refreshLemburEntity.getJamKeluar());
                    returnRefreshLembur.setJenisLembur(refreshLemburEntity.getJenisLembur());
                    returnRefreshLembur.setLamaLembur(refreshLemburEntity.getLamaLembur());
                    returnRefreshLembur.setStLamaLembur(Double.toString(returnRefreshLembur.getLamaLembur()));
                    returnRefreshLembur.setJamLembur(refreshLemburEntity.getJamLembur());
                    returnRefreshLembur.setStJamLembur(Double.toString(returnRefreshLembur.getJamLembur()));
                    returnRefreshLembur.setBiayaLembur(refreshLemburEntity.getBiayaLembur());
                    returnRefreshLembur.setStBiayaLembur(Double.toString(returnRefreshLembur.getBiayaLembur()));
                    returnRefreshLembur.setTipeHari(refreshLemburEntity.getTipeHari());
                    returnRefreshLembur.setRealisasiLembur(refreshLemburEntity.getRealisasiLembur());
                    returnRefreshLembur.setStRealisasiLembur(Double.toString(returnRefreshLembur.getRealisasiLembur()));
                    returnRefreshLembur.setBranchId(refreshLemburEntity.getBranchId());
                    
                    returnRefreshLembur.setLemburId(refreshLemburEntity.getLemburId());
                    returnRefreshLembur.setTglAwalLembur(refreshLemburEntity.getTglAwalLembur());
                    returnRefreshLembur.setStTglAwalLembur(CommonUtil.convertDateToString(returnRefreshLembur.getTglAwalLembur()));
                    returnRefreshLembur.setTglAkhirLembur(refreshLemburEntity.getTglAkhirLembur());
                    returnRefreshLembur.setStTglAkhirLembur(CommonUtil.convertDateToString(returnRefreshLembur.getTglAkhirLembur()));
                    returnRefreshLembur.setJamAwalLembur(refreshLemburEntity.getJamAwalLembur());
                    returnRefreshLembur.setJamAkhirLembur(refreshLemburEntity.getJamAkhirLembur());
                    returnRefreshLembur.setFlagApprove(refreshLemburEntity.getFlagApprove());
                    returnRefreshLembur.setApprovalwho(refreshLemburEntity.getApprovalwho());
                    
                    returnRefreshLembur.setFlag(refreshLemburEntity.getFlag());
                    returnRefreshLembur.setAction(refreshLemburEntity.getAction());
                    returnRefreshLembur.setLastUpdate(refreshLemburEntity.getLastUpdate());
                    returnRefreshLembur.setCreatedDate(refreshLemburEntity.getCreatedDate());
                    returnRefreshLembur.setCreatedWho(refreshLemburEntity.getCreatedWho());
                    returnRefreshLembur.setLastUpdateWho(refreshLemburEntity.getLastUpdateWho());
                    
                    listOfResult.add(returnRefreshLembur);
                }
            }
        }
        logger.info("[RefreshLemburBoImpl.getByCriteria] end process <<<");
        return listOfResult;    
    }

    @Override
    public List<RefreshLembur> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String refreshAbsensiLembur(List<Lembur> lemburList, Date tanggal, Boolean chance) throws GeneralBOException{
        logger.info("[RefreshLemburBoImpl.refreshLembur] START >>>>>>");

        String msgResult = "noNeed";
        /*  msgResult :
                "success"     --> (kesempatan refresh masih ada) ada data lembur yang perlu diRefresh, langsung diUpdate.
                "needApprove" --> (kesempatan refresh habis) ada data lembur yang perlu diRefresh, Update menunggu approve Kantor pusat (perlu kirim notif ke VP Finance (SDM) Kantor Pusat.
                "noNeed"      --> tidak ada data lembur yang perlu direfresh.
         */
        String groupId = "";

        try{
            groupId = refreshLemburDao.getNextGroupLemburId();
        }catch (HibernateException e){
            logger.error("[RefreshLemburBoImpl.refreshAbsensilembur] Error, " + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        Map hsCriteria = new HashMap();
        hsCriteria.put("lembur", "N");
        hsCriteria.put("tanggal", tanggal);
        hsCriteria.put("flag", "Y");
        hsCriteria.put("nip","");
        for(Lembur lembur : lemburList){
            hsCriteria.replace("nip", lembur.getNip());
            List<AbsensiPegawaiEntity> absensiPegawaiList = new ArrayList<>();
            try{
                absensiPegawaiList = absensiPegawaiDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("[RefreshLemburBoImpl.refreshAbsensiLembur] Error, " + e.getMessage());
                throw new GeneralBOException(e.getMessage());
            }

            if(absensiPegawaiList.size() > 0) {
                if (absensiPegawaiList.get(0).getJamMasuk() != null && absensiPegawaiList.get(0).getJamKeluar() != null) {
                    if (chance) {
                        msgResult = "success";
                    } else {
                        msgResult = "needApprove";
                    }
                    ItHrisRefreshLemburEntity refreshLembur = new ItHrisRefreshLemburEntity();

                    try {
                        String refreshLemburId = refreshLemburDao.getNextRefreshLemburId();
                        refreshLembur.setRefreshLemburId(refreshLemburId);
                    } catch (HibernateException e) {
                        logger.error("[RefreshLemburBoImpl.refreshAbsensiLembur] Error, " + e.getMessage());
                        throw new GeneralBOException(e.getMessage());
                    }

                    refreshLembur.setGroupRefreshId(groupId);
                    refreshLembur.setAbsensiPegawaiId(absensiPegawaiList.get(0).getAbsensiPegawaiId());
                    refreshLembur.setTanggal(absensiPegawaiList.get(0).getTanggal());
                    refreshLembur.setJamMasuk(absensiPegawaiList.get(0).getJamMasuk());
                    refreshLembur.setJamKeluar(absensiPegawaiList.get(0).getJamKeluar());
                    refreshLembur.setTipeHari(absensiPegawaiList.get(0).getTipeHari());
                    refreshLembur.setBranchId(absensiPegawaiList.get(0).getBranchId());

                    refreshLembur.setLemburId(lembur.getLemburId());
                    refreshLembur.setNip(lembur.getNip());
                    refreshLembur.setNama(lembur.getPegawaiName());
                    refreshLembur.setTglAwalLembur(lembur.getTanggalAwal());
                    refreshLembur.setTglAkhirLembur(lembur.getTanggalAkhir());
                    refreshLembur.setJamAwalLembur(lembur.getJamAwal());
                    refreshLembur.setJamAkhirLembur(lembur.getJamAkhir());
                    refreshLembur.setJenisLembur(lembur.getTipeLembur());

                    Lembur calcLembur = calcBiayaLembur(lembur, absensiPegawaiList.get(0));

                    refreshLembur.setLamaLembur(calcLembur.getLamaJam());
                    refreshLembur.setJamLembur(calcLembur.getLamaHitungan());
                    refreshLembur.setRealisasiLembur(calcLembur.getJamRealisasi());
                    refreshLembur.setBiayaLembur(calcLembur.getUpahLembur());

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    refreshLembur.setFlag("Y");
                    refreshLembur.setAction("C");
                    refreshLembur.setCreatedDate(updateTime);
                    refreshLembur.setLastUpdate(updateTime);
                    refreshLembur.setCreatedWho(userLogin);
                    refreshLembur.setLastUpdateWho(userLogin);
                    refreshLembur.setFlagApprove("0");

                    if (chance) {

                        refreshLembur.setFlagApprove("Y");
                        refreshLembur.setApprovalwho(userLogin);

                        absensiPegawaiList.get(0).setLembur("Y");
                        absensiPegawaiList.get(0).setJenisLembur(refreshLembur.getJenisLembur());
                        absensiPegawaiList.get(0).setLamaLembur(refreshLembur.getLamaLembur());
                        absensiPegawaiList.get(0).setJamLembur(refreshLembur.getJamLembur());
                        absensiPegawaiList.get(0).setBiayaLembur(refreshLembur.getBiayaLembur());
                        absensiPegawaiList.get(0).setRealisasiJamLembur(refreshLembur.getRealisasiLembur());
                        try {
                            absensiPegawaiDao.addAndSave(absensiPegawaiList.get(0));
                        } catch (HibernateException e) {
                            logger.error("[RefreshLemburBoImpl.refreshAbsensiLembur] Error, " + e.getMessage());
                            throw new GeneralBOException(e.getMessage());
                        }
                    }

                    try {
                        refreshLemburDao.addAndSave(refreshLembur);
                    } catch (HibernateException e) {
                        logger.error("[RefreshLemburBoImpl.refreshAbsensiLembur] Error, " + e.getMessage());
                        throw new GeneralBOException(e.getMessage());
                    }
                }
            }
        }
        logger.info("[RefreshLemburBoImpl.refreshLembur] END >>>>>>>>");

        return msgResult;
    }

    private Lembur calcBiayaLembur(Lembur lembur, AbsensiPegawaiEntity absensiPegawai) throws GeneralBOException{

        Lembur returnLembur = new Lembur();

        String tahunGaji = "";
        double faktor = 0;
        Double gapok = 0d;
        Double sankhus = 0d;

        try{
            ImCompany company = companyDao.getCompanyInfo("Y");
            tahunGaji = company.getPeriodeGaji();
        }catch (HibernateException e) {
            logger.error("[RefreshLemburBoImpl.cronInquiry] Error " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
        Map hsCriteria4 = new HashMap();
        hsCriteria4.put("tipe_pegawai_id", lembur.getTipePegawaiId());
        hsCriteria4.put("flag", "Y");
        try {
            pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
        } catch (HibernateException e) {
            logger.error("[RefreshLemburBoImpl.cronInquiry] Error " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
            faktor = pengaliFaktorLemburEntity.getFaktor();
        }

        hsCriteria4 = new HashMap();
        hsCriteria4.put("golongan_id", lembur.getGolonganId());
//        hsCriteria4.put("point", (int) Math.round(biodata.getPoint()));
        hsCriteria4.put("tahun", tahunGaji);
        hsCriteria4.put("flag", "Y");
        List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
        List<ImPayrollSkalaGajiPkwtEntity> payrollSkalaGajiPkwtEntityList = new ArrayList<>();

        if (CommonConstant.TIPE_PEGAWAI_TETAP.equalsIgnoreCase(lembur.getTipePegawaiId())) {
            try{
                payrollSkalaGajiList = payrollSkalaGajiDao.getDataSkalaGajiSimRs(lembur.getGolonganId(), tahunGaji);
            }catch (HibernateException e){
                logger.error("[RefreshLemburBoImpl.getDetailLembur] Error, " + e.getMessage());
                throw new GeneralBOException("Problem when retrieving Skala Gaji, " + e.getMessage());
            }
            for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
//                sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
            }
        } else if (CommonConstant.PEGAWAI_PKWT.equalsIgnoreCase(lembur.getTipePegawaiId())) {
            try{
                payrollSkalaGajiPkwtEntityList = payrollSkalaGajiPkwtDao.getSkalaGajiPkwt(lembur.getGolonganId(), tahunGaji);
            }catch (HibernateException e){
                logger.error("[RefreshLemburBoImpl.getDetailLembur] Error, " + e.getMessage());
                throw new GeneralBOException("Problem when retrieving Skala Gaji PKWT, " + e.getMessage());
            }
            for (ImPayrollSkalaGajiPkwtEntity skalaGajiLoop : payrollSkalaGajiPkwtEntityList) {
                gapok = skalaGajiLoop.getGajiPokok().doubleValue();
                sankhus = skalaGajiLoop.getSantunanKhusus().doubleValue();
            }
        }

        double lamaLembur = lembur.getLamaJam();
        double finalLamaLembur = 0;
        double finalLamaLembur2 = 0;

        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        java.util.Date jamAwalLembur;
        java.util.Date jamAkhirLembur;
        java.util.Date jamAwalAbsen;
        java.util.Date jamAkhirAbsen;

        try{
            jamAwalAbsen = dateFormat.parse(absensiPegawai.getJamMasuk());
            jamAkhirAbsen = dateFormat.parse(absensiPegawai.getJamKeluar());
            jamAwalLembur = dateFormat.parse(lembur.getJamAwal());
            jamAkhirLembur = dateFormat.parse(lembur.getJamAkhir());
        }catch (ParseException e){
            logger.error("[RefreshLemburBoImpl.cronInquiry] Error " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        java.util.Date realJamAwal;
        java.util.Date realJamAkhir;

        if(jamAwalAbsen.after(jamAwalLembur)){
            realJamAwal = jamAwalAbsen;
        }else{
            realJamAwal = jamAwalLembur;
        }

        if(jamAkhirAbsen.before(jamAkhirLembur)){
            realJamAkhir = jamAkhirAbsen;
        }else{
            realJamAkhir = jamAkhirLembur;
        }

        double realisasiLembur = (realJamAkhir.getTime() - realJamAwal.getTime())/(60*60*1000); // Dijadikan perJam

        if (lamaLembur < realisasiLembur) {
            finalLamaLembur = lamaLembur;
        } else {
            finalLamaLembur = realisasiLembur;
        }

        returnLembur.setJamRealisasi(finalLamaLembur);

        //hitung poin lembur / perhitungan lembur
        int jLembur = 1;
        Double pointLembur = 0.0;
        if (finalLamaLembur > 0) {
            do {
                if (finalLamaLembur > 0 && finalLamaLembur < 1) {
                    //RAKA-18FEB2021==>Perhitungan langsung sisa jam lembur < 1 jam
                    if (finalLamaLembur >= 0.5) {
                        pointLembur = pointLembur + 0.5;
                    }
                    //RAKA-end
                    finalLamaLembur = (double) 0;
                } else {
                    Map hsCriteria5 = new HashMap();
                    hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                    hsCriteria5.put("jam_lembur", jLembur);
                    hsCriteria5.put("flag", "Y");
                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                    try {
                        jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                    } catch (HibernateException e) {
                        logger.error("[RefreshLemburBoImpl.getWaktuLembur] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retrieving Jam Lembur by criteria, " + e.getMessage());
                    }
                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                        pointLembur = pointLembur + (jamLemburEntity.getPengaliJamLembur());
                    }
                    finalLamaLembur = finalLamaLembur - 1;
                }
                jLembur++;
            } while (finalLamaLembur > 0);
        }

        Double peralihan = 0d;
        BigDecimal prosentase = new BigDecimal(0);
        String personPosition = "";

        try {
            personPosition = personilPositionDao.getJenisPegawaiByNip(absensiPegawai.getNip());
        } catch (HibernateException e) {
            logger.error("[RefreshLemburBoImpl.cronInquiry] Error " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        //RAKA-18FEB2021==>Mengambil Persen Gaji melalui tabel Jenis Pegawai;
        try {
            prosentase = jenisPegawaiDao.getPersenGaji(personPosition);
        } catch (HibernateException e) {
            logger.error("[RefreshLemburBoImpl.cronInquiry] Error " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        //RAKA-end
        // Sigit, 2020-11-26 perubahan Perhitungan upah biaya lembur per jam, START
        peralihan = getPeralihanGapok(absensiPegawai.getNip(), absensiPegawai.getTanggal()).doubleValue();
        Double totalGapokPeralihan = gapok + peralihan;
        BigDecimal bGapokPeralihan = new BigDecimal(totalGapokPeralihan);
        BigDecimal bFaktor = new BigDecimal(faktor / 100);
        BigDecimal bJamLembur = new BigDecimal(pointLembur);
        Double upahLembur = 0d;
        String stUpahLembur;

        BigDecimal bUpahLembur = bGapokPeralihan.multiply(prosentase).multiply(bFaktor).multiply(bJamLembur);
        upahLembur = Math.floor(bUpahLembur.doubleValue());
        // END

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol(""); //Menghilangkan currency symbol u/ hemat space tampilan
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        stUpahLembur = kursIndonesia.format(upahLembur);

        returnLembur.setJamRealisasi(realisasiLembur);
        returnLembur.setLamaJam(lamaLembur);
        returnLembur.setLamaHitungan(pointLembur);
        returnLembur.setTipeLembur(lembur.getTipeLembur());
        returnLembur.setUpahLembur(upahLembur);
        returnLembur.setStUpahLembur(stUpahLembur);

        return returnLembur;
    }

    // Sigit, 2020-11-26
    // Perhitungan Tunjangan Peralihan Gapok, bulan terakhir pada tahun sekarang
    private BigDecimal getPeralihanGapok(String nip, Date tanggal) {
        BigDecimal hasil = new BigDecimal(0);
        List<ItHrisPayrollEntity> itPayrollEntityList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy");
        String tahun = df.format(tanggal);
        try {
            itPayrollEntityList = payrollDao.getTunjanganPeralihanForAbsensi(nip, tahun);
        } catch (HibernateException e) {
            logger.error("[RefreshLemburBoImpl.getPeralihanGapok] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving Tunjangan Peralihan For Absensi, " + e.getMessage());
        }
        for (ItHrisPayrollEntity itPayrollEntity : itPayrollEntityList) {
            hasil = hasil.add(itPayrollEntity.getPeralihanGapok());
            break;
        }
        return hasil;
    }
}
