package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.mobileapi.model.HistoryPegawaiMobile;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Tuesday, 11/08/20 13:32
 */
public class HistoryPegawaiController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(HistoryPegawaiController.class);

    HistoryPegawaiMobile model = new HistoryPegawaiMobile();
    Collection<HistoryPegawaiMobile> listOfHistoryPegawaiMoblile;

    CutiPegawaiBo cutiPegawaiBoProxy;
    IjinKeluarBo ijinKeluarBoProxy;
    AbsensiBo absensiBoProxy;

    private String nip;
    private String branchId;
    private String action;
    private String bulan;
    private String tahun;

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public Object getModel() {
        return listOfHistoryPegawaiMoblile != null ? listOfHistoryPegawaiMoblile : model;
    }

    public void setModel(HistoryPegawaiMobile model) {
        this.model = model;
    }

    public Collection<HistoryPegawaiMobile> getListOfHistoryPegawaiMoblile() {
        return listOfHistoryPegawaiMoblile;
    }

    public void setListOfHistoryPegawaiMoblile(Collection<HistoryPegawaiMobile> listOfHistoryPegawaiMoblile) {
        this.listOfHistoryPegawaiMoblile = listOfHistoryPegawaiMoblile;
    }

    public CutiPegawaiBo getCutiPegawaiBoProxy() {
        return cutiPegawaiBoProxy;
    }

    public void setCutiPegawaiBoProxy(CutiPegawaiBo cutiPegawaiBoProxy) {
        this.cutiPegawaiBoProxy = cutiPegawaiBoProxy;
    }

    public IjinKeluarBo getIjinKeluarBoProxy() {
        return ijinKeluarBoProxy;
    }

    public void setIjinKeluarBoProxy(IjinKeluarBo ijinKeluarBoProxy) {
        this.ijinKeluarBoProxy = ijinKeluarBoProxy;
    }

    public AbsensiBo getAbsensiBoProxy() {
        return absensiBoProxy;
    }

    public void setAbsensiBoProxy(AbsensiBo absensiBoProxy) {
        this.absensiBoProxy = absensiBoProxy;
    }



    public HttpHeaders create() {
        logger.info("[HistoryPegawaiController.create] start process POST /historypegawai/ <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(action.equalsIgnoreCase("getJumlah")) {

            List<CutiPegawai> cutiPegawai = null;
            List<AbsensiPegawai> absensiPegawai = null;
            List<IjinKeluar> ijinKeluar = null;

            try {
                cutiPegawai = cutiPegawaiBoProxy.sisaCutiSys(nip);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "SisaCutiController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[SisaCutiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[SisaCutiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            absensiPegawai = getAbsensi(nip, branchId, CommonUtil.convertTimestampToDate(now));
            ijinKeluar = getDispen(nip, branchId, CommonUtil.convertTimestampToDate(now));

            //jika cuti tahunan 0, ambil sisa cuti panjang
            boolean isCutiTahunan = true;
            for (CutiPegawai item : cutiPegawai) {
                if (item.getCutiName().equalsIgnoreCase("Cuti Tahunan")) {
                    if (!item.getSisaCutiHari().toString().equalsIgnoreCase("0")) {
                        model.setSisaCuti(item.getSisaCutiHari().toString());
                        break;
                    } else isCutiTahunan = false;
                }
                if (item.getCutiName().equalsIgnoreCase("Cuti Panjang")) {
                    if (!isCutiTahunan){
                        model.setSisaCuti(item.getSisaCutiHari().toString());
                    }
                }
            }

            if (absensiPegawai != null){
                model.setJumlahHadir(String.valueOf(absensiPegawai.size()));
            }
            if (ijinKeluar != null) {
               int totalIjin = 0;

               //looping untuk menghitung hari
               for (IjinKeluar item : ijinKeluar) {

                   //cek jika total ijin telah melebihi jumlah hari di bulan ini,
                   if (totalIjin != Integer.valueOf(CommonUtil.getLastDayOfMonth())){
                       totalIjin += item.getLamaIjin().intValue();
                   } else break;
               }
                model.setJumlahDispen(String.valueOf(totalIjin));
            } else model.setJumlahDispen("0");


        }

        if  (action.equalsIgnoreCase("getDispen")) {
            listOfHistoryPegawaiMoblile = new ArrayList<>();
            List<IjinKeluar> ijinKeluar = null;

            ijinKeluar = getDispen(nip, branchId, CommonUtil.convertTimestampToDate(now));

            if (ijinKeluar != null) {
                for (IjinKeluar item : ijinKeluar) {
                    HistoryPegawaiMobile historyPegawaiMobile = new HistoryPegawaiMobile();
                    historyPegawaiMobile.setIjinKeluarName(item.getIjinName());
                    historyPegawaiMobile.setTanggalAwal(CommonUtil.convertDateToString(item.getTanggalAwal()));
                    historyPegawaiMobile.setTanggalAkhir(CommonUtil.convertDateToString(item.getTanggalAkhir()));
                    historyPegawaiMobile.setLamaIjin(item.getLamaIjin().toString());
                    historyPegawaiMobile.setIjinId(item.getIjinKeluarId());

                    listOfHistoryPegawaiMoblile.add(historyPegawaiMobile);
                }
            }
        }

        if  (action.equalsIgnoreCase("getAbsen")) {
            listOfHistoryPegawaiMoblile  = new ArrayList<>();
            List<AbsensiPegawai> absensiPegawai = null;

            absensiPegawai = getAbsensi(nip, branchId, CommonUtil.convertTimestampToDate(now));

            if (absensiPegawai != null) {
                for (AbsensiPegawai item : absensiPegawai) {
//                    if  (!item.getStatusAbsensi().equalsIgnoreCase("00") && !item.getStatusAbsensi().equalsIgnoreCase("08") && !item.getStatusAbsensi().equalsIgnoreCase("10") && !item.getStatusAbsensi().equalsIgnoreCase("11") && !item.getStatusAbsensi().equalsIgnoreCase("12") && !item.getStatusAbsensi().equalsIgnoreCase("13")) {
                        HistoryPegawaiMobile historyPegawaiMobile = new HistoryPegawaiMobile();
                        historyPegawaiMobile.setAbsensiPegawaiId(item.getAbsensiPegawaiId());
                        historyPegawaiMobile.setTanggalAbsen(CommonUtil.convertDateToString(item.getTanggal()));
                        historyPegawaiMobile.setJamDatang(item.getJamMasuk());
                        historyPegawaiMobile.setJamPulang(item.getJamKeluar());
                        historyPegawaiMobile.setStatus(item.getStatusAbsensi());
                        historyPegawaiMobile.setIsLembur(item.getLembur());
                        historyPegawaiMobile.setIsDispen(item.getIjin());
                        historyPegawaiMobile.setNamaStatus(CommonUtil.statusName(item.getStatusAbsensi()));

                        listOfHistoryPegawaiMoblile.add(historyPegawaiMobile);

                }
            }
        }

        if (action.equalsIgnoreCase("getAbsensiByCriteria")) {
            listOfHistoryPegawaiMoblile = new ArrayList<>();

            List<AbsensiPegawai> result = new ArrayList<>();
            String tanggalAwal;
            String tanggalAkhir;

            //jika bulan kosong, maka akan mencari absensi dalam setahun
            if (!bulan.equalsIgnoreCase("")) {
                tanggalAwal = "01-"+bulan+"-"+tahun;

                //Mencari tanggal terakhir di bulan yg diinputkan
                Date dtTanggalAkhir = CommonUtil.convertStringToDate(tanggalAwal);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dtTanggalAkhir);
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                tanggalAkhir = CommonUtil.convertDateToString(calendar.getTime());
            } else {
                tanggalAwal = "01-01-"+tahun;
                tanggalAkhir = "31-12-"+tahun;
            }

            AbsensiPegawai bean = new AbsensiPegawai();
            bean.setNip(nip);
            bean.setBranchId(branchId);
            bean.setStTanggalDari(tanggalAwal);
            bean.setStTanggalSelesai(tanggalAkhir);
            bean.setMobile(true);

            try {
                result = absensiBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[SisaCutiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e);
            }

            if (result.size() > 0) {
                for (AbsensiPegawai item : result) {
                    HistoryPegawaiMobile historyPegawaiMobile = new HistoryPegawaiMobile();
                    historyPegawaiMobile.setAbsensiPegawaiId(item.getAbsensiPegawaiId());
                    historyPegawaiMobile.setTanggalAbsen(CommonUtil.convertDateToString(item.getTanggal()));
                    historyPegawaiMobile.setNama(item.getNama());
                    historyPegawaiMobile.setNip(item.getNip());
                    historyPegawaiMobile.setJamMasuk(item.getJamMasuk());
                    historyPegawaiMobile.setJamKeluar(item.getJamKeluar());
                    historyPegawaiMobile.setJamPulang(item.getJamPulang());
                    historyPegawaiMobile.setTanggal(item.getStTanggal());
                    historyPegawaiMobile.setStatus(item.getStatusAbsensi());
                    historyPegawaiMobile.setIsLembur(item.getLembur());
                    historyPegawaiMobile.setIsDispen(item.getIjin());
                    historyPegawaiMobile.setNamaStatus(CommonUtil.statusName(item.getStatusAbsensi()));
                    historyPegawaiMobile.setTelat(item.getTelat().toString());
                    listOfHistoryPegawaiMoblile.add(historyPegawaiMobile);
                }
            }
        }

        logger.info("[HistoryPegawaiConteroller.create] end process POST /historypegawai/ <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }

    private List<IjinKeluar> getDispen(String nip, String branchId, Date date){
        List<IjinKeluar> ijinKeluar = null;

        try {
            ijinKeluar = ijinKeluarBoProxy.getHistoryIjinKeluarByMonth(nip, branchId, date);
        } catch (GeneralBOException e) {
            logger.error("[SisaCutiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e);
        }


        return ijinKeluar;
    }

    private List<AbsensiPegawai> getAbsensi(String nip, String branchId, Date date){
        List<AbsensiPegawai> absensiPegawai = null;

        try {
            absensiPegawai = absensiBoProxy.getHistoryAbsensiByMonth(nip, branchId, date);
        } catch (GeneralBOException e) {
            logger.error("[SisaCutiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e);
        }

        return absensiPegawai;
    }
}
