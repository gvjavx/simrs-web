package com.neurix.hris.mobileapi;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.User;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.jamkerja.bo.JamKerjaBo;
import com.neurix.hris.master.jamkerja.model.JamKerja;
import com.neurix.hris.master.libur.bo.LiburBo;
import com.neurix.hris.master.libur.model.Libur;
import com.neurix.hris.mobileapi.model.Lembur;
import com.neurix.hris.transaksi.lembur.bo.LemburBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class    LemburController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(LemburController.class);
    private List<Lembur> listOfLembur = new ArrayList<>();
    private Lembur model = new Lembur();
    private LemburBo lemburBoProxy;
    private LiburBo liburBoProxy;
    private UserBo userBoProxy;
    private JamKerjaBo jamKerjaBoProxy;
    private BiodataBo biodataBoProxy;
    private NotifikasiBo notifikasiBoProxy;

    private String nip;
    private String idLembur;
    private String id;
    private String who;
    private String statusApprove;
    private String confirm;

    private String tanggalAwal;
    private String tanggalAkhir;
    private String jamAwal;
    private String jamAkhir;
    private String lamaJamLembur;
    private String namaPegawai;

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getId() {
        return id;
    }

    public String getWho() {
        return who;
    }

    public String getStatusApprove() {
        return statusApprove;
    }

    public String getConfirm() {
        return confirm;
    }

    public String getLamaJamLembur() {
        return lamaJamLembur;
    }

    public void setLamaJamLembur(String lamaJamLembur) {
        this.lamaJamLembur = lamaJamLembur;
    }

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setJamKerjaBoProxy(JamKerjaBo jamKerjaBoProxy) {
        this.jamKerjaBoProxy = jamKerjaBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public void setLiburBoProxy(LiburBo liburBoProxy) {
        this.liburBoProxy = liburBoProxy;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public void setLemburBoProxy(LemburBo lemburBoProxy) {
        this.lemburBoProxy = lemburBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[LemburController.create] end process POST /lembur <<<");

        List<Object[]> daftarLembur = null;
        try {
            daftarLembur = lemburBoProxy.findInfoLembur(idLembur, nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[LemburController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[LemburController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(daftarLembur != null){
            for(Object[] obj : daftarLembur){
                model = new Lembur();
                model.setNip(obj[0].toString());
                model.setNamaPegawai(obj[1].toString());
                model.setPosisi(obj[2].toString());
                model.setDivisi(obj[3].toString());
                model.setTanggalAwal(obj[4].toString());
                model.setTanggalAkhir(obj[5].toString());
                model.setJamLemburAwal(obj[6].toString());
                model.setLamaJamLembur(obj[7].toString());
                model.setUnit(obj[8].toString());
                model.setKeterangan(obj[9].toString());
                model.setJamLemburAkhir(obj[10].toString());
            }
        }
        listOfLembur = null;
        logger.info("[LemburController.create] end process POST /lembur <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }


    public HttpHeaders update(){
        logger.info("[LemburController.update] begin proccess PUT /lembur/{id}");

//        User user = userBoProxy.getUserById(id, "Y");

        try {
            com.neurix.hris.transaksi.lembur.model.Lembur editLembur = new com.neurix.hris.transaksi.lembur.model.Lembur();
            editLembur.setLemburId(idLembur);
            if (who.equals("atasan")){
                if(statusApprove.equals("Y")){
                    editLembur.setApprovalFlag(statusApprove);
                }else{
                    editLembur.setApprovalFlag("N");
                }
            }
            if (!("").equalsIgnoreCase(model.getKeterangan())){
                editLembur.setNotApprovalNote(model.getKeterangan());
            }
            editLembur.setTmpApprove(who);
            editLembur.setNip(nip);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date tanggalAwalst = simpleDateFormat.parse(tanggalAwal);
            java.util.Date tanggalAkhirst = simpleDateFormat.parse(tanggalAkhir);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String tangAwl = sdf.format(tanggalAwalst);
            String tangAkh = sdf.format(tanggalAkhirst);

            editLembur.setTanggalAwalSetuju(CommonUtil.convertToDate(tangAwl));
            editLembur.setTanggalAkhirSetuju(CommonUtil.convertToDate(tangAkh));
            editLembur.setLamaJam(Double.valueOf(lamaJamLembur));
            editLembur.setLastUpdateWho(namaPegawai);
            editLembur.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            editLembur.setAction("U");
            editLembur.setFlag("Y");
            editLembur.setApprovalId(id);
            editLembur.setApprovalName(namaPegawai);

            List<Notifikasi> notifikasiList = lemburBoProxy.saveApprove(editLembur);

            for (Notifikasi notifikasi: notifikasiList){
                notifikasiBoProxy.sendNotif(notifikasi);
            }

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[LemburController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[LemburController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("[LemburController.isFoundOtherSessionActiveUserSessionLog] ", e);

        }

        logger.info("[LemburController.update] end proccess PUT /lembur/{id}");
        return new DefaultHttpHeaders().renderResult("201").disableCaching();
    }

    public String confirm(){
        logger.info("[LemburController.confirm] start proccess GET /lembur/{id}/confirm");

        List<Object[]> lemburs = null;
        try {
            lemburs = lemburBoProxy.findAllConfirmByAtasan(id, confirm);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[LemburController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[LemburController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(lemburs != null && lemburs.size() != 0){
            for(Object[] obj : lemburs){
                Lembur model = new Lembur();
                model.setIdLembur(obj[0].toString());
                model.setNip(obj[1].toString());
                model.setNamaPegawai(obj[2].toString());
                model.setTanggalAwal(obj[3].toString());
                model.setTanggalAkhir(obj[4].toString());
                model.setJamLemburAwal(obj[5].toString());
                model.setJamLemburAkhir(obj[6].toString());
                model.setLamaJamLembur(obj[7].toString());
                model.setUnit(obj[8].toString());

                listOfLembur.add(model);
            }
        }

        logger.info("[LemburController.confirm] start proccess GET /lembur/{id}/confirm");
        return "success";
    }

    public String tanggal() {
        logger.info("[LemburController.confirm] start proccess GET /lembur/{id}/tanggal");
        String lemburs = null;

        Date dateTanggalAwal = CommonUtil.convertToDate(tanggalAwal);
        Date dateTanggalAkhir = CommonUtil.convertToDate(tanggalAkhir);
        try {
            lemburs = lemburBoProxy.testTanggal(dateTanggalAwal, dateTanggalAkhir, id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[LemburController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[LemburController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        Lembur result = new Lembur();

        if(!lemburs.equals("")){
            result.setMessage(lemburs);
        } else {
            result.setMessage("Permohonan siap diajukan");
        }
        model = result;
        listOfLembur.add(result);
        logger.info("[LemburController.confirm] start proccess GET /lembur/{id}/tanggal");
        return "success";
    }

    // check off work by parameter start date and last date
    public String offwork() throws ParseException {
        logger.info("[LemburController.offwork] start proccess GET /lembur/{id}/offwork");

        int jumlahHari = 0;

        Libur libur = new Libur();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date tanggalAwal1 = new java.sql.Date(sdf1.parse(tanggalAwal).getTime());
        java.sql.Date tanggalAkhir1 = new java.sql.Date(sdf1.parse(tanggalAkhir).getTime());

        Calendar start = Calendar.getInstance();
        start.setTime(tanggalAwal1);
        Calendar end = Calendar.getInstance();
        end.setTime(tanggalAkhir1);
        end.add(Calendar.DATE,1);
        java.util.Date date;

        List<Libur> liburList = new ArrayList<>();
        for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            java.sql.Timestamp sq = new java.sql.Timestamp(date.getTime());
            libur.setTanggal(sq);
            libur.setFlag("Y");
            liburList=liburBoProxy.getByCriteria(libur);
            if (liburList.size()!=0) {
                jumlahHari=jumlahHari+1;
            }
        }

        Lembur result = new Lembur();
        result.setJumlah(String.valueOf(jumlahHari));
        listOfLembur.add(result);

        logger.info("[LemburController.offwork] start proccess GET /lembur/{id}/offwork");
        return "success";
    }

    // calculate time of lembur
    public String calculate() throws ParseException {
        logger.info("[LemburAction.calcJamLembur] start process >>>");
        listOfLembur = null;
        String hariKerja ="hari_libur";

        Double hasil = (double) 0;
        String sHasil;
        String sJamKerjaAwalDb="",sJamKerjaAkhirDb="";
        int iJamAwalKerja=Integer.parseInt(jamAwal.replace(":",""));
        int iJamAkhirKerja=Integer.parseInt(jamAkhir.replace(":",""));
        int iJamAwalDb = 0,iJamAkhirDb=0;

        String tangAwl = "";
        String tangAkh = "";

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date tanggalAwalst = simpleDateFormat.parse(tanggalAwal);
            java.util.Date tanggalAkhirst = simpleDateFormat.parse(tanggalAkhir);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            tangAwl = sdf.format(tanggalAwalst);
            tangAkh = sdf.format(tanggalAkhirst);
        } catch (ParseException e) {
            logger.info(e.getMessage());
            throw new GeneralBOException(e);
        }

        Date startDate = CommonUtil.convertToDate(tangAwl);
        Date endDate = CommonUtil.convertToDate(tangAkh);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DATE,1);
        java.util.Date date;

        for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int hari = c.get(Calendar.DAY_OF_WEEK);
            if (hari>1 && hari<7) {
                hariKerja = "hari_kerja";
            }
            Timestamp tanggal = new Timestamp(date.getTime());
            Libur libur = new Libur();
            libur.setTanggal(tanggal);
            libur.setFlag("Y");
            List<Libur> liburList = liburBoProxy.getByCriteria(libur);
            if (liburList.size() != 0) {
                hariKerja = "hari_libur";
            }
        }
        Biodata searchBiodata = new Biodata();
        searchBiodata.setNip(id);
        searchBiodata.setFlag("Y");
        Biodata result = biodataBoProxy.getShift(searchBiodata);
        if (("Y").equalsIgnoreCase(result.getShift())){
            hariKerja="hari_libur";
        }

        if (hariKerja=="hari_kerja"){
            JamKerja search = new JamKerja();
            search.setFlag("Y");
            List<JamKerja> jamKerjaList = jamKerjaBoProxy.getByCriteria(search);
            for (JamKerja jamKerja : jamKerjaList){
                sJamKerjaAwalDb=jamKerja.getJamAwalKerja();
                sJamKerjaAkhirDb=jamKerja.getJamAkhirKerja();
                iJamAwalDb=Integer.parseInt(sJamKerjaAwalDb.replace(":",""));
                iJamAkhirDb=Integer.parseInt(sJamKerjaAkhirDb.replace(":",""));
                break;
            }
            if (iJamAwalKerja<iJamAwalDb){
                hasil=hasil+SubtractJamAwalDanJamAkhir (jamAwal,sJamKerjaAwalDb,"positif");
                if (iJamAkhirKerja>iJamAkhirDb){
                    hasil=hasil+SubtractJamAwalDanJamAkhir (sJamKerjaAkhirDb,jamAkhir,"positif");
                }
            }
            if (iJamAwalKerja>=iJamAkhirDb){
                hasil=hasil+SubtractJamAwalDanJamAkhir (jamAwal,jamAkhir,"positif");
            }
        }else{
            hasil=SubtractJamAwalDanJamAkhir (jamAwal,jamAkhir,"positif");
        }
        sHasil = hasil.toString();
        model.setLamaJamLembur(sHasil);
        logger.info("[LemburController.offwork] start proccess GET /lembur/{id}/offwork");
        return "success";
    }

    private Double SubtractJamAwalDanJamAkhir (String jamAwal,String jamAkhir,String status) throws ParseException {
        java.text.DateFormat df = new java.text.SimpleDateFormat("dd:MM:yyyy HH:mm");
        java.util.Date date1 = df.parse("01:01:2000 "+jamAwal);
        java.util.Date date2 = df.parse("01:01:2000 "+jamAkhir);
        long diff = date2.getTime() - date1.getTime();
        if (diff<0&&status.equalsIgnoreCase("positif")){
            date2 = df.parse("02:01:2000 "+jamAkhir);
            diff = date2.getTime() - date1.getTime();
        }
        int timeInSeconds = (int) (diff / 1000);
        int hours, minutes;
        hours = timeInSeconds / 3600;
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        double hasil=hours;
        if (minutes<15){hasil=hasil+0;}
        else if (minutes<30){hasil=hasil+0.25;}
        else if (minutes<45){hasil=hasil+0.50;}
        else if (minutes<60){hasil=hasil+0.75;}
        return hasil;
    }
    @Override
    public Object getModel() {
        return listOfLembur != null ? listOfLembur: model;
    }

    public String getIdLembur() {
        return idLembur;
    }

    public void setIdLembur(String idLembur) {
        this.idLembur = idLembur;
    }

    public void setModel(Lembur model) {
        this.model = model;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatusApprove(String statusApprove) {
        this.statusApprove = statusApprove;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(String tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(String tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        this.jamAkhir = jamAkhir;
    }
}
