package com.neurix.hris.transaksi.payroll.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.download.excel.CellDetail;
import com.neurix.common.download.excel.DownloadUtil;
import com.neurix.common.download.excel.RowData;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawaiEntity;
import com.neurix.hris.transaksi.payroll.bo.PayrollBo;
import com.neurix.hris.transaksi.payroll.model.*;
import com.neurix.hris.transaksi.smk.model.SmkHistoryEvaluasiPegawai;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class PayrollAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollAction.class);
    private PayrollBo payrollBoProxy;
    private Payroll payroll;
    private PayrollPph payrollPph;
    private PayrollPensiun payrollPensiun;
    private PayrollRapel payrollRapel;
    private PayrollPendidikan payrollPendidikan;
    private PayrollJasprod payrollJasprod;
    private PayrollInsentif payrollInsentif;
    private PayrollPotonganLain payrollPotonganLain;
    private String bulan;
    private String tahun;
    private String bulan2;
    private String tahun2;
    private String branchId;
    private String statusPegawai;
    private String tipe;
    protected String noSurat;
    protected String tanggal;
    private String tanggalAwal;
    private String tanggalAkhir;
    private String kabidSdm;
    private String tanggalSk;
    private String positionId;
    private String tanggalAktif;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(String tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public String getTanggalSk() {
        return tanggalSk;
    }

    public void setTanggalSk(String tanggalSk) {
        this.tanggalSk = tanggalSk;
    }

    public String getKabidSdm() {
        return kabidSdm;
    }

    public void setKabidSdm(String kabidSdm) {
        this.kabidSdm = kabidSdm;
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

    public PayrollInsentif getPayrollInsentif() {
        return payrollInsentif;
    }

    public void setPayrollInsentif(PayrollInsentif payrollInsentif) {
        this.payrollInsentif = payrollInsentif;
    }

    public String getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public String getBulan2() {
        return bulan2;
    }

    public void setBulan2(String bulan2) {
        this.bulan2 = bulan2;
    }

    public String getTahun2() {
        return tahun2;
    }

    public void setTahun2(String tahun2) {
        this.tahun2 = tahun2;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public PayrollPendidikan getPayrollPendidikan() {
        return payrollPendidikan;
    }

    public void setPayrollPendidikan(PayrollPendidikan payrollPendidikan) {
        this.payrollPendidikan = payrollPendidikan;
    }

    public PayrollRapel getPayrollRapel() {
        return payrollRapel;
    }

    public void setPayrollRapel(PayrollRapel payrollRapel) {
        this.payrollRapel = payrollRapel;
    }

    public PayrollJasprod getPayrollJasprod() {
        return payrollJasprod;
    }

    public void setPayrollJasprod(PayrollJasprod payrollJasprod) {
        this.payrollJasprod = payrollJasprod;
    }

    public PayrollPensiun getPayrollPensiun() {
        return payrollPensiun;
    }

    public void setPayrollPensiun(PayrollPensiun payrollPensiun) {
        this.payrollPensiun = payrollPensiun;
    }

    public PayrollPotonganLain getPayrollPotonganLain() {
        return payrollPotonganLain;
    }

    public void setPayrollPotonganLain(PayrollPotonganLain payrollPotonganLain) {
        this.payrollPotonganLain = payrollPotonganLain;
    }

    public PayrollPph getPayrollPph() {
        return payrollPph;
    }

    public void setPayrollPph(PayrollPph payrollPph) {
        this.payrollPph = payrollPph;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

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

    private List<Payroll> listComboPayroll = new ArrayList<Payroll>();

    public List<Payroll> getListComboPayroll() {
        return listComboPayroll;
    }

    public void setListComboPayroll(List<Payroll> listComboPayroll) {
        this.listComboPayroll = listComboPayroll;
    }

    public PayrollBo getPayrollBoProxy() {
        return payrollBoProxy;
    }

    public void setPayrollBoProxy(PayrollBo payrollBoProxy) {
        this.payrollBoProxy = payrollBoProxy;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    private List<Payroll> initComboPayroll;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollAction.logger = logger;
    }


    public List<Payroll> getInitComboPayroll() {
        return initComboPayroll;
    }

    public void setInitComboPayroll(List<Payroll> initComboPayroll) {
        this.initComboPayroll = initComboPayroll;
    }

    public Payroll init(String kode, String flag){
        logger.info("[PayrollAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Payroll> listOfResult = (List<Payroll>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Payroll payroll: listOfResult) {
                    if(kode.equalsIgnoreCase(payroll.getPayrollId()) && flag.equalsIgnoreCase(payroll.getFlag())){
                        setPayroll(payroll);
                        break;
                    }
                }
            } else {
                setPayroll(new Payroll());
            }

            logger.info("[PayrollAction.init] end process >>>");
        }
        return getPayroll();
    }

    public List<Payroll> copyDataPayroll(List<Payroll> data){
        logger.info("[PayrollAction.copyDataPayroll] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Payroll> hasilCopy = new ArrayList<>();

        if(data != null){
            for (Payroll payroll: data) {
                hasilCopy.add(payroll);
            }
        }

        logger.info("[PayrollAction.copyDataPayroll] end process >>>");
        return hasilCopy;
    }

    @Override
    public String add() {
        logger.info("[PayrollAction.add] start process >>>");
        Payroll addSppd = new Payroll();
        setPayroll(addSppd);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("dataPayroll");

        logger.info("[PayrollAction.add] stop process >>>");
        return "init_add";
    }

    public String loadAdd() {
        logger.info("[PayrollAction.add] start process >>>");
        Payroll payroll =  null;

        HttpSession session = ServletActionContext.getRequest().getSession();
        payroll = (Payroll) session.getAttribute("dataPayroll");

        setPayroll(payroll);
        logger.info("[PayrollAction.add] stop process >>>");
        return "init_add_v2";
    }

    public String detailAdd() {
        logger.info("[PayrollAction.add] start process >>>");
        Payroll payroll =  null;

        HttpSession session = ServletActionContext.getRequest().getSession();
        payroll = (Payroll) session.getAttribute("dataPayroll");

        setPayroll(payroll);
        logger.info("[PayrollAction.add] stop process >>>");
        return "modal_add_payroll";
    }

    public String detailRapel() {
        logger.info("[PayrollAction.add] start process >>>");
        logger.info("[PayrollAction.add] stop process >>>");
        return "detail_rapel";
    }

    public String detailThr() {
        logger.info("[PayrollAction.add] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Payroll> listOfResult = (List<Payroll>) session.getAttribute("listDataPayroll");
        String nip = getId();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (Payroll payroll: listOfResult) {
                    if(nip.equalsIgnoreCase(payroll.getNip())){
                        setPayroll(payroll);
                        break;
                    }
                }
            } else {
                setPayroll(new Payroll());
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        logger.info("[PayrollAction.add] stop process >>>");
        return "detail_thr";
    }

    public String searchDataSession() {
        logger.info("[PayrollAction.add] start process >>>");
        Payroll searchPayroll = getPayroll();

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Payroll> listOfResult = (List<Payroll>) session.getAttribute("listDataPayroll");
        String nip = searchPayroll.getNip();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (Payroll payroll: listOfResult) {
                    if(nip.equalsIgnoreCase(payroll.getNip())){
                        setPayroll(payroll);
                        break;
                    }
                }
            } else {
                setPayroll(new Payroll());
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        logger.info("[PayrollAction.add] stop process >>>");
        return "detail_thr";
    }

    public String detailPendidikan() {
        logger.info("[PayrollAction.add] start process >>>");
        logger.info("[PayrollAction.add] stop process >>>");
        return "detail_pendidikan";
    }

    public String detailJasprod() {
        logger.info("[PayrollAction.add] start process >>>");
        logger.info("[PayrollAction.add] stop process >>>");
        return "detail_jasprod";
    }

    public void saveEditSession(String nip, String tunjPeralihan, String kompensasi, String transport, String uangMuka, String kurangBpjs,
                                String koperasi, String dansos, String sp, String bazis, String bapor, String lainLain, String tunjLain,
                                String flagJubileum, String flagPensiun){
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<Payroll> listPayroll = (List<Payroll>) session.getAttribute("listDataPayrollSearch");
        List<Payroll> listHasil = new ArrayList();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listPayroll != null){
                for (Payroll payroll: listPayroll) {
                    if(nip.equalsIgnoreCase(payroll.getNip())){
                        Payroll newPayroll = new Payroll();
                        newPayroll.setNip(payroll.getNip());
                        newPayroll.setTunjanganPeralihanNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(tunjPeralihan))));
                        newPayroll.setKompensasiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(kompensasi))));
                        newPayroll.setTunjanganTransportNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(transport))));
                        newPayroll.setUangMukaLainnyaNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(uangMuka))));
                        newPayroll.setKekuranganBpjsTkNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(kurangBpjs))));
                        newPayroll.setKoperasiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(koperasi))));
                        newPayroll.setDansosNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(dansos))));
                        newPayroll.setSPNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(sp))));
                        newPayroll.setBazisNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(bazis))));
                        newPayroll.setBaporNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(bapor))));
                        newPayroll.setTunjanganLainNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(tunjLain))));
                        newPayroll.setLainLainNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(lainLain))));

                        newPayroll.setCentangJubileum(flagJubileum);
                        newPayroll.setFlagJubileum(flagJubileum);
                        newPayroll.setCentangPensiun(flagPensiun);
                        newPayroll.setFlagPensiun(flagPensiun);

                        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
                        payrollBo.saveEditDataSys(newPayroll);
                    }
                }
            }
            logger.info("[PayrollAction.init] end process >>>");
        }
    }

    public void saveEditSession2(String branchId, String nip){
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<Payroll> listPayroll = (List<Payroll>) session.getAttribute("listDataPayrollSearch");
        List<Payroll> listHasil = new ArrayList();
        Payroll newPayroll = new Payroll();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listPayroll != null){
                for (Payroll payroll: listPayroll) {
                    if(nip.equalsIgnoreCase(payroll.getNip())){
                        newPayroll = payroll;
                        payroll.setTunjanganLemburNilai(new BigDecimal(2000000));
                        payroll.setTunjanganLembur("2.000.000");
                        /*ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
                        payrollBo.saveEditDataSys(newPayroll);*/
                    }
                }
            }
            logger.info("[PayrollAction.init] end process >>>");
        }
    }

    public void saveEditSessionData(String nip, //data pegawai
                                    String tunjPeralihan,String pemondokan,String komunikasi, //komponen A
                                    String kopkar, String iuranSp, String iuranPiikb, String bankBri, String bankMandiri, // Komponen rincian C
                                    String infaq, String perkesDanObat, String listrik, String iuranProfesi, String potonganLain, // Komponen rincian C
                                    String flagJubileum, String flagPensiun){
        Payroll newPayroll = new Payroll();
        newPayroll.setNip(nip);
//        newPayroll.setTipePegawai(tipePegawai);
        //Komponen A
        newPayroll.setTunjanganPeralihan(tunjPeralihan);
        newPayroll.setTunjanganPeralihanNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(tunjPeralihan))));
        newPayroll.setPemondokan(pemondokan);
        newPayroll.setPemondokanNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pemondokan))));
        newPayroll.setKomunikasi(komunikasi);
        newPayroll.setKomunikasiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(komunikasi))));

        //Rincian Potongan C
        newPayroll.setKopkar(kopkar);
        newPayroll.setKopkarNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(kopkar))));
        newPayroll.setIuranSp(iuranSp);
        newPayroll.setIuranSpNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(iuranPiikb))));
        newPayroll.setIuranPiikb(iuranSp);
        newPayroll.setIuranPiikbNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(iuranPiikb))));
        newPayroll.setBankBri(bankBri);
        newPayroll.setBankBriNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(bankBri))));
        newPayroll.setBankMandiri(bankMandiri);
        newPayroll.setBankMandiriNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(bankMandiri))));
        newPayroll.setInfaq(infaq);
        newPayroll.setInfaqNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(infaq))));
        newPayroll.setPerkesDanObat(perkesDanObat);
        newPayroll.setPerkesDanObatNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(perkesDanObat))));
        newPayroll.setListrik(listrik);
        newPayroll.setListrikNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(listrik))));
        newPayroll.setIuranProfesi(iuranProfesi);
        newPayroll.setIuranProfesiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(iuranProfesi))));
        newPayroll.setPotonganLain(potonganLain);
        newPayroll.setPotonganLainNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(potonganLain))));

        newPayroll.setCentangJubileum(flagJubileum);
        newPayroll.setFlagJubileum(flagJubileum);
        newPayroll.setCentangPensiun(flagPensiun);
        newPayroll.setFlagPensiun(flagPensiun);
        /*newPayroll.setCentangListrikAir(flagListrikAir);
        newPayroll.setCentangPerumahan(flagPerumahan);
        newPayroll.setCentangKalkulasiPph(flagKalkulasiPph);*/

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollBo.saveEditDataSessionSys(newPayroll);
    }

    public void saveEditSessionDataUsingPayrollId(String payrollId, String nip, //data pegawai
                                                  String tunjPeralihan,String pemondokan,String komunikasi, //komponen A
                                                  String kopkar, String iuranSp, String iuranPiikb, String bankBri, String bankMandiri, // Komponen rincian C
                                                  String infaq, String perkesDanObat, String listrik, String iuranProfesi, String potonganLain, // Komponen rincian C
                                                  String flagJubileum, String flagPensiun){
        Payroll newPayroll = new Payroll();
        newPayroll.setPayrollId(payrollId);
//        newPayroll.setTipePegawai(tipePegawai);

        //Komponen A
        newPayroll.setTunjanganPeralihan(tunjPeralihan);
        newPayroll.setTunjanganPeralihanNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(tunjPeralihan))));
        newPayroll.setPemondokan(pemondokan);
        newPayroll.setPemondokanNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pemondokan))));
        newPayroll.setKomunikasi(komunikasi);
        newPayroll.setKomunikasiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(komunikasi))));

        //Rincian Potongan C
        newPayroll.setKopkar(kopkar);
        newPayroll.setKopkarNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(kopkar))));
        newPayroll.setIuranSp(iuranSp);
        newPayroll.setIuranSpNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(iuranPiikb))));
        newPayroll.setIuranPiikb(iuranSp);
        newPayroll.setIuranPiikbNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(iuranPiikb))));
        newPayroll.setBankBri(bankBri);
        newPayroll.setBankBriNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(bankBri))));
        newPayroll.setBankMandiri(bankMandiri);
        newPayroll.setBankMandiriNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(bankMandiri))));
        newPayroll.setInfaq(infaq);
        newPayroll.setInfaqNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(infaq))));
        newPayroll.setPerkesDanObat(perkesDanObat);
        newPayroll.setPerkesDanObatNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(perkesDanObat))));
        newPayroll.setListrik(listrik);
        newPayroll.setListrikNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(listrik))));
        newPayroll.setIuranProfesi(iuranProfesi);
        newPayroll.setIuranProfesiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(iuranProfesi))));
        newPayroll.setPotonganLain(potonganLain);
        newPayroll.setPotonganLainNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(potonganLain))));

        newPayroll.setCentangJubileum(flagJubileum);
        newPayroll.setFlagJubileum(flagJubileum);
        newPayroll.setCentangPensiun(flagPensiun);
        newPayroll.setFlagPensiun(flagPensiun);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollBo.saveEditSessionDataUsingPayrollIdSys(newPayroll);

    }

    public void saveEditSessionDataUsingPayrollJasprod(String payrollId, String koperasi, String dansos, String lainLain, String flagKalkulasi, String pphGaji){
        Payroll newPayroll = new Payroll();
        newPayroll.setPayrollId(payrollId);

        newPayroll.setKoperasiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(koperasi))));
        newPayroll.setDansosNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(dansos))));
        newPayroll.setLainLainNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(lainLain))));
        newPayroll.setPphGajiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pphGaji))));

        newPayroll.setCentangKalkulasiPph(flagKalkulasi);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollBo.saveEditSessionDataUsingPayrollIdJasprodSys(newPayroll);

    }

    public String cekJubileum(String nip){
        String hasil = "";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        hasil = payrollBo.cekJubileumSys(nip);
        return hasil;
    }

    public String cekPensiun(String nip){
        String hasil = "";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        hasil = payrollBo.cekPensiunSys(nip);
        return hasil;
    }

    public void saveEditPersonalSession(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Payroll payrollPerson = (Payroll) session.getAttribute("listDataPayrollPerson");
        List<Payroll> listOfResult = (List<Payroll>) session.getAttribute("listDataPayroll");
        List<Payroll> resultSearchPayroll = new ArrayList<>();

        if(payrollPerson != null) {
            if(payrollPerson.getFlagPayroll() != null ){
                if (payrollPerson.getNip() != null && !"".equalsIgnoreCase(payrollPerson.getNip())) {
                    if (listOfResult != null) {
                        for (Payroll payroll : listOfResult) {
                            if(payrollPerson.getNip().equalsIgnoreCase(payroll.getNip())){
                                resultSearchPayroll.add(payrollPerson);
                            }else{
                                resultSearchPayroll.add(payroll);
                            }
                        }
                    }
                    session.removeAttribute("listDataPayrollSearch");
                    session.setAttribute("listDataPayrollSearch", resultSearchPayroll);
                    session.removeAttribute("listDataPayroll");
                    session.setAttribute("listDataPayroll", resultSearchPayroll);
                }
            }
        }
    }

    public String refreshData(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Payroll> listPayrollBackup = (List<Payroll>) session.getAttribute("listDataPayrollBackup");
        List<PayrollPph> listPayrollPph = (List<PayrollPph>) session.getAttribute("listDataPayrollPphBackup");
        Payroll payroll = (Payroll) session.getAttribute("dataPayroll");
        List<Payroll> resultListPayroll = new ArrayList<>();

        for(Payroll payrollBackup : listPayrollBackup){
            Payroll payrollPerson = new Payroll();

            payrollPerson.setBulan(payrollBackup.getBulan());
            payrollPerson.setTahun(payrollBackup.getTahun());
            payrollPerson.setNip(payrollBackup.getNip());
            payrollPerson.setNama(payrollBackup.getNama());
            payrollPerson.setKelompokId(payrollBackup.getKelompokId());
            payrollPerson.setNpwp(payrollBackup.getNpwp());
            payrollPerson.setStatusPegawai(payrollBackup.getStatusPegawai());
            payrollPerson.setTipePegawai(payrollBackup.getTipePegawai());
            payrollPerson.setTipePegawaiName(payrollBackup.getTipePegawaiName());
            payrollPerson.setStrukturGaji(payrollBackup.getStrukturGaji());
            payrollPerson.setStTanggalAktif(payrollBackup.getStTanggalAktif());

            payrollPerson.setStTanggalAktifSekarang(payrollBackup.getStTanggalAktifSekarang());

            payrollPerson.setStTanggalPayroll(payrollBackup.getStTanggalPayroll());
            payrollPerson.setMasaKerjaTahun(payrollBackup.getMasaKerjaTahun());
            payrollPerson.setMasaKerjaBulan(payrollBackup.getMasaKerjaBulan());
            payrollPerson.setPositionId(payrollBackup.getPositionId());
            payrollPerson.setPositionName(payrollBackup.getPositionName());
            payrollPerson.setDepartmentId(payrollBackup.getDepartmentId());
            payrollPerson.setDepartmentName(payrollBackup.getDepartmentName());
            payrollPerson.setBranchId(payrollBackup.getBranchId());
            payrollPerson.setBranchName(payrollBackup.getBranchName());
            payrollPerson.setGolonganId(payrollBackup.getGolonganId());
            payrollPerson.setGolonganName(payrollBackup.getGolonganName());
            payrollPerson.setPoint(payrollBackup.getPoint());
            payrollPerson.setStatusKeluarga(payrollBackup.getStatusKeluarga());
            payrollPerson.setMultifikator(payrollBackup.getMultifikator());
            payrollPerson.setJumlahAnak(payrollBackup.getJumlahAnak());
            payrollPerson.setGender(payrollBackup.getGender());
            payrollPerson.setDanaPensiunName(payrollBackup.getDanaPensiunName());
            payrollPerson.setFlagPjs(payrollBackup.getFlagPjs());
            payrollPerson.setFlagPromosiOn(payrollBackup.isFlagPromosiOn());

            payrollPerson.setFlagPayroll(payrollBackup.getFlagPayroll());
            payrollPerson.setFlagRapel(payrollBackup.getFlagRapel());
            payrollPerson.setFlagThr(payrollBackup.getFlagThr());
            payrollPerson.setFlagPendidikan(payrollBackup.getFlagPendidikan());
            payrollPerson.setFlagJubileum(payrollBackup.getFlagJubileum());
            payrollPerson.setFlagJasprod(payrollBackup.getFlagJasprod());
            payrollPerson.setFlagPensiun(payrollBackup.getFlagPensiun());
            payrollPerson.setFlagZakat(payrollBackup.getFlagZakat());

            payrollPerson.setFaktorKeluargaId(payrollBackup.getFaktorKeluargaId());

            payrollPerson.setCentangListrikAir("Y");
            payrollPerson.setCentangPerumahan("Y");
            payrollPerson.setCentangListrikAir(payrollBackup.getCentangListrikAir());
            payrollPerson.setCentangPerumahan(payrollBackup.getCentangPerumahan());
            payrollPerson.setFlagListrikAirOn(payrollBackup.isFlagListrikAirOn());
            payrollPerson.setFlagPerumahanOn(payrollBackup.isFlagPerumahanOn());

            payrollPerson.setFlagPensiunOn(payrollBackup.isFlagPensiunOn());
            payrollPerson.setStTanggalPensiun(payrollBackup.getStTanggalPensiun());
            payrollPerson.setLabelPensiun(payrollBackup.getLabelPensiun());
            payrollPerson.setCentangPensiun(payrollBackup.getCentangPensiun());
            payrollPerson.setTanggalJubileum(payrollBackup.getTanggalJubileum());
            payrollPerson.setFlagJubileumOn(payrollBackup.isFlagJubileumOn());
            payrollPerson.setCentangJubileum(payrollBackup.getCentangJubileum());
            payrollPerson.setFlagJubileum(payrollBackup.getFlagJubileum());
            payrollPerson.setLabelJubileum(payrollBackup.getLabelJubileum());

            payrollPerson.setGajiGolongan(payrollBackup.getGajiGolongan()); //Gaji
            payrollPerson.setGajiGolonganNilai(payrollBackup.getGajiGolonganNilai()); //Gaji Nilai
            payrollPerson.setTunjanganUmk(payrollBackup.getTunjanganUmk()); //Tunj. UMK
            payrollPerson.setTunjanganUmkNilai(payrollBackup.getTunjanganUmkNilai()); //Tunj. UMK Nilai
            payrollPerson.setGajiPensiun(payrollBackup.getGajiPensiun()); //Gaji Untuk Pensiun
            payrollPerson.setGajiPensiunNilai(payrollBackup.getGajiPensiunNilai()); //Gaji Untuk Pensiun
            payrollPerson.setGajiBpjs(payrollBackup.getGajiBpjs()); //Gaji Untuk BPJS
            payrollPerson.setGajiBpjsNilai(payrollBackup.getGajiBpjsNilai()); //Gaji Untuk BPJS Nilai

            payrollPerson.setTunjanganStruktural(payrollBackup.getTunjanganStruktural()); //Tunj. Struktural
            payrollPerson.setTunjanganStrukturalNilai(payrollBackup.getTunjanganStrukturalNilai()); //Tunj. Struktural Nilai
            payrollPerson.setTunjanganPendidikan(payrollBackup.getTunjanganPendidikan()); //Tunj. Pendidikan
            payrollPerson.setTunjanganPendidikanNilai(payrollBackup.getTunjanganPendidikanNilai()); //Tunj. Pendidikan Nilai
            payrollPerson.setTunjanganPeralihan(payrollBackup.getTunjanganPeralihan()); //Tunj. Peraliahan ---
            payrollPerson.setTunjanganPeralihanNilai(payrollBackup.getTunjanganPeralihanNilai()); //Tunj. Peraliahan Nilai---

            payrollPerson.setTunjanganJabatanStruktural(payrollBackup.getTunjanganJabatanStruktural()); //Tunj. Jabatan Struktural
            payrollPerson.setTunjanganJabatanStrukturalNilai(payrollBackup.getTunjanganJabatanStrukturalNilai()); //Tunj. Jabatan Struktural Nilai
            payrollPerson.setTunjanganStrategis(payrollBackup.getTunjanganStrategis()); //Tunj. Strategis
            payrollPerson.setTunjanganStrategisNilai(payrollBackup.getTunjanganStrategisNilai()); //Tunj. Strategis Nilai
            payrollPerson.setKompensasi(payrollBackup.getKompensasi()); //Kompensasi
            payrollPerson.setKompensasiNilai(payrollBackup.getKompensasiNilai()); //Kompensasi Nilai
            payrollPerson.setTunjanganTransport(payrollBackup.getTunjanganTransport()); //Transport
            payrollPerson.setTunjanganTransportNilai(payrollBackup.getTunjanganTransportNilai()); //Transport Nilai

            payrollPerson.setTunjanganAirListrik(payrollBackup.getTunjanganAirListrik()); //Tunj Air Listrik
            payrollPerson.setTunjanganAirListrikNilai(payrollBackup.getTunjanganAirListrikNilai()); //Tunj Air Listrik Nilai
            payrollPerson.setTunjanganPengobatan(payrollBackup.getTunjanganPengobatan()); //Tunj Pengobatan
            payrollPerson.setTunjanganPengobatanNilai(payrollBackup.getTunjanganPengobatanNilai()); //Tunj Pengobatan Nilai
            payrollPerson.setTunjanganBajuDinas(payrollBackup.getTunjanganBajuDinas()); //Tunj Pakaian DinAs
            payrollPerson.setTunjanganBajuDinasNilai(payrollBackup.getTunjanganBajuDinasNilai()); //Tunj Pakaian DinAs Nilai

            payrollPerson.setTunjanganPerumahan(payrollBackup.getTunjanganPerumahan()); //Tunj. Perumahan
            payrollPerson.setTunjanganPerumahanNilai(payrollBackup.getTunjanganPerumahanNilai()); //Tunj. Perumahan Nilai
            payrollPerson.setTunjanganPph(payrollBackup.getTunjanganPph()); //Tunj PPh ---
            payrollPerson.setTunjanganPphNilai(payrollBackup.getTunjanganPphNilai()); //Tunj PPh Nilai ---
            payrollPerson.setTunjanganLain(payrollBackup.getTunjanganLain()); //Tunj Lain - lain
            payrollPerson.setTunjanganLainNilai(payrollBackup.getTunjanganLainNilai()); //Tunj Lain - lain Nilai
            payrollPerson.setTunjanganLembur(payrollBackup.getTunjanganLembur()); //Tunj Lembur ---
            payrollPerson.setTunjanganLemburNilai(payrollBackup.getTunjanganLemburNilai()); //Tunj Lembur Nilai ---

            payrollPerson.setTotalA(payrollBackup.getTotalA());
            payrollPerson.setTotalANilai(payrollBackup.getTotalANilai()); //Nilai Total A


            payrollPerson.setUangMukaLainnya(payrollBackup.getUangMukaLainnya()); //Uang Muka Lainnya
            payrollPerson.setUangMukaLainnyaNilai(payrollBackup.getUangMukaLainnyaNilai()); //Uang Muka Lainnya Nilai
            payrollPerson.setIuranBpjsPensiun(payrollBackup.getIuranBpjsPensiun()); //Iuran Bpjs Pensiun
            payrollPerson.setIuranBpjsPensiunNilai(payrollBackup.getIuranBpjsPensiunNilai()); //Iuran Bpjs Pensiun Nilai
            payrollPerson.setIuranPensiun(payrollBackup.getIuranPensiun()); //Iuran Pensiun
            payrollPerson.setIuranPensiunNilai(payrollBackup.getIuranPensiunNilai()); //Iuran Pensiun Nilai
            payrollPerson.setPphGaji(payrollBackup.getPphGaji()); //Pph Gaji
            payrollPerson.setPphGajiNilai(payrollBackup.getPphGajiNilai()); //Pph Gaji Nilai
            payrollPerson.setPphPengobatan(payrollBackup.getPphPengobatan()); //Pph pengobatan
            payrollPerson.setPphPengobatanNilai(payrollBackup.getPphPengobatanNilai()); //Pph Pengobatan Nilai
            payrollPerson.setIuranBpjsKesehatan(payrollBackup.getIuranBpjsKesehatan()); //Iuran Bpjs Kesehatan
            payrollPerson.setIuranBpjsKesehatanNilai(payrollBackup.getIuranBpjsKesehatanNilai()); //Iuran Bpjs Kesehatan Nilai
            payrollPerson.setIuranBpjsTk(payrollBackup.getIuranBpjsTk()); //Iuran Bpjs Tk
            payrollPerson.setIuranBpjsTkNilai(payrollBackup.getIuranBpjsTkNilai()); //Iuran Bpjs Tk Nilai
            payrollPerson.setKekuranganBpjsTk(payrollBackup.getKekuranganBpjsTk()); //Kekurangan Bpjs
            payrollPerson.setKekuranganBpjsTkNilai(payrollBackup.getKekuranganBpjsTkNilai()); //Kekurangan Bpjs Nilai
            payrollPerson.setTotalB(payrollBackup.getTotalB()); //Total B
            payrollPerson.setTotalBNilai(payrollBackup.getTotalBNilai()); //Total B

            payrollPerson.setFlagPromosiOn(payrollBackup.isFlagPromosiOn());
            payrollPerson.setUmr(payrollBackup.getUmr());
            payrollPerson.setZakat(payrollBackup.getZakat()); //Zakat
            payrollPerson.setZakatNilai(payrollBackup.getZakatNilai()); //Zakat Nilai
            payrollPerson.setPengobatan(payrollBackup.getPengobatan()); //Pengobatan
            payrollPerson.setPengobatanNilai(payrollBackup.getPengobatanNilai()); //Pengobatan Nilai
            payrollPerson.setKoperasi(payrollBackup.getKoperasi()); //Koperasi
            payrollPerson.setKoperasiNilai(payrollBackup.getKoperasiNilai()); //Koperasi Nilai
            payrollPerson.setDansos(payrollBackup.getDansos()); //Dansos
            payrollPerson.setDansosNilai(payrollBackup.getDansosNilai()); //Dansos Nilai
            payrollPerson.setSP(payrollBackup.getSP()); //Sp
            payrollPerson.setSPNilai(payrollBackup.getSPNilai()); //Sp Nilai
            payrollPerson.setBazis(payrollBackup.getBazis()); //Bazis
            payrollPerson.setBazisNilai(payrollBackup.getBazisNilai()); //Bazis Nilai
            payrollPerson.setBapor(payrollBackup.getBapor()); //Bapor
            payrollPerson.setBaporNilai(payrollBackup.getBaporNilai()); //Baporilai
            payrollPerson.setLainLain(payrollBackup.getLainLain()); //LainLain
            payrollPerson.setLainLainNilai(payrollBackup.getLainLainNilai()); //LainLain Nilai
            payrollPerson.setTotalC(payrollBackup.getTotalC()); //Total C
            payrollPerson.setTotalCNilai(payrollBackup.getTotalCNilai()); //Total C Nilai

            payrollPerson.setTotalRapel(payrollBackup.getTotalRapel());
            payrollPerson.setTotalRapelNilai(payrollBackup.getTotalRapelNilai());
            payrollPerson.setTotalThr(payrollBackup.getTotalThr());
            payrollPerson.setTotalThrNilai(payrollBackup.getTotalThrNilai());
            payrollPerson.setTotalPendidikan(payrollBackup.getTotalPendidikan());
            payrollPerson.setTotalPendidikanNilai(payrollBackup.getTotalPendidikanNilai());
            payrollPerson.setTotalJasProd(payrollBackup.getTotalJasProd());
            payrollPerson.setTotalJasProdNilai(payrollBackup.getTotalJasProdNilai());
            payrollPerson.setTotalPensiun(payrollBackup.getTotalPensiun());
            payrollPerson.setTotalPensiunNilai(payrollBackup.getTotalPensiunNilai());
            payrollPerson.setBesarJubileum(payrollBackup.getBesarJubileum());
            payrollPerson.setBesarJubileumNilai(payrollBackup.getBesarJubileumNilai());
            payrollPerson.setTotalKaliJubileum(payrollBackup.getTotalKaliJubileum());
            payrollPerson.setTotalKaliJubileumNilai(payrollBackup.getTotalKaliJubileumNilai());

            payrollPerson.setTotalTambahan(payrollBackup.getTotalTambahan()); //Total D
            payrollPerson.setTotalTambahanNilai(payrollBackup.getTotalTambahanNilai()); //Total C Nilai
            payrollPerson.setTotalGajiBersih(payrollBackup.getTotalGajiBersih());
            payrollPerson.setTotalGajiBersihNilai(payrollBackup.getTotalGajiBersihNilai());

            payrollPerson.setFlagKonsistensi(payrollBackup.isFlagKonsistensi());

            resultListPayroll.add(payrollPerson);
        }

        session.removeAttribute("listDataPayrollSearch");
        session.removeAttribute("listDataPayroll");
        session.setAttribute("listDataPayrollSearch", resultListPayroll);
        session.setAttribute("listDataPayroll", resultListPayroll);
        session.setAttribute("listDataPayrollPph", listPayrollPph);
        setPayroll(payroll);

        return "init_add_v2";
    }

    public String cancelPage(){
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listDataPayroll");
        session.removeAttribute("listDataPayrollSearch");
        session.removeAttribute("listDataPayrollBackup");
        session.removeAttribute("listOfGajiPkwt");
        session.removeAttribute("listDataPayrollJasprod");
        session.removeAttribute("listDataPayrollJasprod");
        session.removeAttribute("listDataPayrollPensiun");
        session.removeAttribute("listDataPayrollJubileum");
        session.removeAttribute("listOfPotonganLain");

        return INPUT;
    }


    public Payroll getDetailAdd(String nip) {
        Payroll payrollPerson = new Payroll();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Payroll> listOfResult = (List<Payroll>) session.getAttribute("listDataPayroll");

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (Payroll payroll: listOfResult) {
                    if(nip.equalsIgnoreCase(payroll.getNip())){
                        payrollPerson.setBulan(payroll.getBulan());
                        payrollPerson.setTahun(payroll.getTahun());
                        payrollPerson.setNip(payroll.getNip());
                        payrollPerson.setNama(payroll.getNama());
                        payrollPerson.setKelompokId(payroll.getKelompokId());
                        payrollPerson.setNpwp(payroll.getNpwp());
                        payrollPerson.setStatusPegawai(payroll.getStatusPegawai());
                        payrollPerson.setTipePegawai(payroll.getTipePegawai());
                        payrollPerson.setTipePegawaiName(payroll.getTipePegawaiName());
                        payrollPerson.setStrukturGaji(payroll.getStrukturGaji());
                        payrollPerson.setStTanggalAktif(payroll.getStTanggalAktif());

                        payrollPerson.setStTanggalAktifSekarang(payroll.getStTanggalAktifSekarang());

                        payrollPerson.setStTanggalPayroll(payroll.getStTanggalPayroll());
                        payrollPerson.setMasaKerjaTahun(payroll.getMasaKerjaTahun());
                        payrollPerson.setMasaKerjaBulan(payroll.getMasaKerjaBulan());
                        payrollPerson.setPositionId(payroll.getPositionId());
                        payrollPerson.setPositionName(payroll.getPositionName());
                        payrollPerson.setDepartmentId(payroll.getDepartmentId());
                        payrollPerson.setDepartmentName(payroll.getDepartmentName());
                        payrollPerson.setBranchId(payroll.getBranchId());
                        payrollPerson.setBranchName(payroll.getBranchName());
                        payrollPerson.setGolonganId(payroll.getGolonganId());
                        payrollPerson.setGolonganName(payroll.getGolonganName());
                        payrollPerson.setPoint(payroll.getPoint());
                        payrollPerson.setStatusKeluarga(payroll.getStatusKeluarga());
                        payrollPerson.setMultifikator(payroll.getMultifikator());
                        payrollPerson.setJumlahAnak(payroll.getJumlahAnak());
                        payrollPerson.setGender(payroll.getGender());
                        payrollPerson.setDanaPensiunName(payroll.getDanaPensiunName());
                        payrollPerson.setFlagPjs(payroll.getFlagPjs());
                        payrollPerson.setFlagPromosiOn(payroll.isFlagPromosiOn());

                        payrollPerson.setStMasaKerjaGol(payroll.getStMasaKerjaGol());
                        payrollPerson.setMasaKerjaGol(payroll.getMasaKerjaGol());
                        payrollPerson.setGolonganDapenId(payroll.getGolonganDapenId());
                        payrollPerson.setGolonganDapenName(payroll.getGolonganDapenName());
                        payrollPerson.setGajiPensiunNilai(payroll.getGajiPensiunNilai());
                        payrollPerson.setGajiPensiun(payroll.getGajiPensiun());

                        payrollPerson.setFlagPayroll(payroll.getFlagPayroll());
                        payrollPerson.setFlagRapel(payroll.getFlagRapel());
                        payrollPerson.setFlagThr(payroll.getFlagThr());
                        payrollPerson.setFlagJubileum(payroll.getFlagJubileum());
                        payrollPerson.setFlagJasprod(payroll.getFlagJasprod());
                        payrollPerson.setFlagInsentif(payroll.getFlagInsentif());
                        payrollPerson.setFlagPensiun(payroll.getFlagPensiun());

                        payrollPerson.setFaktorKeluargaId(payroll.getFaktorKeluargaId());

                        //Komponen A
                        payrollPerson.setGajiGolongan(payroll.getGajiGolongan()); //Gaji
                        payrollPerson.setGajiGolonganNilai(payroll.getGajiGolonganNilai()); //Gaji Nilai
                        payrollPerson.setTunjanganUmk(payroll.getTunjanganUmk()); //sankhus
                        payrollPerson.setTunjanganUmkNilai(payroll.getTunjanganUmkNilai()); //sankhus Nilai
                        payrollPerson.setTunjanganJabatanStruktural(payroll.getTunjanganJabatanStruktural()); //Tunj. Jabatan
                        payrollPerson.setTunjanganJabatanStrukturalNilai(payroll.getTunjanganJabatanStrukturalNilai()); //Tunj. Jabatan Nilai
                        payrollPerson.setTunjanganStruktural(payroll.getTunjanganStruktural()); //Tunj. Struktural
                        payrollPerson.setTunjanganStrukturalNilai(payroll.getTunjanganStrukturalNilai()); //Tunj. Struktural Nilai
                        payrollPerson.setTunjanganStrategis(payroll.getTunjanganStrategis()); //Tunj. Strategis
                        payrollPerson.setTunjanganStrategisNilai(payroll.getTunjanganStrategisNilai()); //Tunj. Strategis Nilai
                        payrollPerson.setTunjanganPeralihan(payroll.getTunjanganPeralihan()); //Tunj. Peraliahan ---
                        payrollPerson.setTunjanganPeralihanNilai(payroll.getTunjanganPeralihanNilai()); //Tunj. Peraliahan Nilai---*/
                        payrollPerson.setTunjanganLain(payroll.getTunjanganLain()); //Tunj. lain ---
                        payrollPerson.setTunjanganLainNilai(payroll.getTunjanganLainNilai()); //Tunj. lain Nilai---*/
                        payrollPerson.setTunjanganTambahan(payroll.getTunjanganTambahan()); //Tunj. tambahan ---
                        payrollPerson.setTunjanganTambahanNilai(payroll.getTunjanganTambahanNilai()); //Tunj. tambahan Nilai---*/
                        payrollPerson.setPemondokan(payroll.getPemondokan());
                        payrollPerson.setPemondokanNilai(payroll.getPemondokanNilai());
                        payrollPerson.setKomunikasi(payroll.getKomunikasi());
                        payrollPerson.setKomunikasiNilai(payroll.getKomunikasiNilai());
                        payrollPerson.setTunjanganLembur(payroll.getTunjanganLembur());
                        payrollPerson.setTunjanganLemburNilai(payroll.getTunjanganLemburNilai());
                        payrollPerson.setTambahanLain(payroll.getTambahanLain());
                        payrollPerson.setTambahanLainNilai(payroll.getTambahanLainNilai());
                        payrollPerson.setTunjanganLembur(payroll.getTunjanganLembur());
                        payrollPerson.setTunjanganLemburNilai(payroll.getTunjanganLemburNilai());
                        payrollPerson.setTotalA(payroll.getTotalA());
                        payrollPerson.setTotalANilai(payroll.getTotalANilai()); //Nilai Total A


                        //Komponen B

                        //RLAB
                        payrollPerson.setTunjanganRumah(payroll.getTunjanganRumah());
                        payrollPerson.setTunjanganRumahNilai(payroll.getTunjanganRumahNilai());
                        payrollPerson.setTunjanganListrik(payroll.getTunjanganListrik());
                        payrollPerson.setTunjanganListrikNilai(payroll.getTunjanganListrikNilai());
                        payrollPerson.setTunjanganAir(payroll.getTunjanganAir());
                        payrollPerson.setTunjanganAirNilai(payroll.getTunjanganAirNilai());
                        payrollPerson.setTunjanganBbm(payroll.getTunjanganBbm());
                        payrollPerson.setTunjanganBBMNilai(payroll.getTunjanganBBMNilai());
                        payrollPerson.setTotalRlab(payroll.getTotalRlab());
                        payrollPerson.setTotalRlabNilai(payroll.getTotalRlabNilai());

                        payrollPerson.setTunjanganBpjsKs(payroll.getTunjanganBpjsKs());
                        payrollPerson.setTunjanganBpjsKsNilai(payroll.getTunjanganBpjsKsNilai());
                        payrollPerson.setTunjanganBpjsTk(payroll.getTunjanganBpjsTk());
                        payrollPerson.setTunjanganBpjsTkNilai(payroll.getTunjanganBpjsTkNilai());

                        payrollPerson.setTunjanganDapen(payroll.getTunjanganDapen());
                        payrollPerson.setTunjanganDapenNilai(payroll.getTunjanganDapenNilai());

                        payrollPerson.setTunjanganSosialLain(payroll.getTunjanganSosialLain());
                        payrollPerson.setTunjanganSosialLainNilai(payroll.getTunjanganSosialLainNilai());

                        payrollPerson.setTunjanganPph(payroll.getTunjanganPph());
                        payrollPerson.setTunjanganPphNilai(payroll.getTunjanganPphNilai());


                        //Komponen C
                        payrollPerson.setIuranDapenPeg(payroll.getIuranDapenPeg());
                        payrollPerson.setIuranDapenPegNilai(payroll.getIuranDapenPegNilai());
                        payrollPerson.setIuranDapenPersh(payroll.getIuranDapenPersh());
                        payrollPerson.setIuranDapenPershNilai(payroll.getIuranDapenPershNilai());
                        payrollPerson.setIuranBpjsKsKary(payroll.getIuranBpjsKsKary());
                        payrollPerson.setIuranBpjsKsKaryNilai(payroll.getIuranBpjsKsKaryNilai());
                        payrollPerson.setIuranBpjsKsPersh(payroll.getIuranBpjsKsPersh());
                        payrollPerson.setIuranBpjsKsPersNilai(payroll.getIuranBpjsKsPersNilai());
                        payrollPerson.setIuranBpjsTkKary(payroll.getIuranBpjsTkKary());
                        payrollPerson.setIuranBpjsTkKaryNilai(payroll.getIuranBpjsTkKaryNilai());
                        payrollPerson.setIuranBpjsTkPers(payroll.getIuranBpjsTkPers());
                        payrollPerson.setIuranBpjsTkPersNilai(payroll.getIuranBpjsTkPersNilai());
                        payrollPerson.setPphGaji(payroll.getPphGaji());
                        payrollPerson.setPphGajiNilai(payroll.getPphGajiNilai());
                        payrollPerson.setTotalPotonganLain(payroll.getTotalPotonganLain());
                        payrollPerson.setTotalPotonganLainNilai(payroll.getTotalPotonganLainNilai());
                        payrollPerson.setIuranYpks(payroll.getIuranYpks());
                        payrollPerson.setIuranYpksNilai(payroll.getIuranYpksNilai());


                        //Rincian Potongan lain - lain
                        payrollPerson.setKopkar(payroll.getKopkar());
                        payrollPerson.setIuranSp(payroll.getIuranSp());
                        payrollPerson.setIuranPiikb(payroll.getIuranPiikb());
                        payrollPerson.setBankBri(payroll.getBankBri());
                        payrollPerson.setBankMandiri(payroll.getBankMandiri());
                        payrollPerson.setInfaq(payroll.getInfaq());
                        payrollPerson.setPerkesDanObat(payroll.getPerkesDanObat());
                        payrollPerson.setListrik(payroll.getListrik());
                        payrollPerson.setIuranProfesi(payroll.getIuranProfesi());
                        payrollPerson.setPotonganLain(payroll.getPotonganLain());

                        payrollPerson.setKopkarNilai(payroll.getKopkarNilai());
                        payrollPerson.setIuranSpNilai(payroll.getIuranSpNilai());
                        payrollPerson.setIuranPiikbNilai(payroll.getIuranPiikbNilai());
                        payrollPerson.setBankBriNilai(payroll.getBankBriNilai());
                        payrollPerson.setBankMandiriNilai(payroll.getBankMandiriNilai());
                        payrollPerson.setInfaqNilai(payroll.getInfaqNilai());
                        payrollPerson.setPerkesDanObatNilai(payroll.getPerkesDanObatNilai());
                        payrollPerson.setListrikNilai(payroll.getListrikNilai());
                        payrollPerson.setIuranProfesiNilai(payroll.getIuranProfesiNilai());
                        payrollPerson.setPotonganLainNilai(payroll.getPotonganLainNilai());
                        //Total A + B + C
                        payrollPerson.setTotalA(payroll.getTotalA()); //Total A
                        payrollPerson.setTotalANilai(payroll.getTotalANilai()); //Total A
                        payrollPerson.setTotalB(payroll.getTotalB()); //Total B
                        payrollPerson.setTotalBNilai(payroll.getTotalBNilai()); //Total B
                        payrollPerson.setTotalC(payroll.getTotalC()); //Total B
                        payrollPerson.setTotalCNilai(payroll.getTotalCNilai()); //Total B

                        payrollPerson.setTotalGajiBersih(payroll.getTotalGajiBersih());
                        payrollPerson.setTotalGajiBersihNilai(payroll.getTotalGajiBersihNilai());

                        /*payrollPerson.setGajiPensiun(payroll.getGajiPensiun()); //Gaji Untuk Pensiun
                        payrollPerson.setGajiPensiunNilai(payroll.getGajiPensiunNilai()); //Gaji Untuk Pensiun
                        payrollPerson.setGajiBpjs(payroll.getGajiBpjs()); //Gaji Untuk BPJS
                        payrollPerson.setGajiBpjsNilai(payroll.getGajiBpjsNilai()); //Gaji Untuk BPJS Nilai*/


                        /*payrollPerson.setTunjanganPendidikan(payroll.getTunjanganPendidikan()); //Tunj. Pendidikan
                        payrollPerson.setTunjanganPendidikanNilai(payroll.getTunjanganPendidikanNilai()); //Tunj. Pendidikan Nilai
                        payrollPerson.setTunjanganPeralihan(payroll.getTunjanganPeralihan()); //Tunj. Peraliahan ---
                        payrollPerson.setTunjanganPeralihanNilai(payroll.getTunjanganPeralihanNilai()); //Tunj. Peraliahan Nilai---*/



                        /*payrollPerson.setKompensasi(payroll.getKompensasi()); //Kompensasi
                        payrollPerson.setKompensasiNilai(payroll.getKompensasiNilai()); //Kompensasi Nilai
                        payrollPerson.setTunjanganTransport(payroll.getTunjanganTransport()); //Transport
                        payrollPerson.setTunjanganTransportNilai(payroll.getTunjanganTransportNilai()); //Transport Nilai

                        payrollPerson.setTunjanganAirListrik(payroll.getTunjanganAirListrik()); //Tunj Air Listrik
                        payrollPerson.setTunjanganAirListrikNilai(payroll.getTunjanganAirListrikNilai()); //Tunj Air Listrik Nilai
                        payrollPerson.setTunjanganPengobatan(payroll.getTunjanganPengobatan()); //Tunj Pengobatan
                        payrollPerson.setTunjanganPengobatanNilai(payroll.getTunjanganPengobatanNilai()); //Tunj Pengobatan Nilai
                        payrollPerson.setTunjanganBajuDinas(payroll.getTunjanganBajuDinas()); //Tunj Pakaian DinAs
                        payrollPerson.setTunjanganBajuDinasNilai(payroll.getTunjanganBajuDinasNilai()); //Tunj Pakaian DinAs Nilai

                        payrollPerson.setTunjanganPerumahan(payroll.getTunjanganPerumahan()); //Tunj. Perumahan
                        payrollPerson.setTunjanganPerumahanNilai(payroll.getTunjanganPerumahanNilai()); //Tunj. Perumahan Nilai
                        payrollPerson.setTunjanganPph(payroll.getTunjanganPph()); //Tunj PPh ---
                        payrollPerson.setTunjanganPphNilai(payroll.getTunjanganPphNilai()); //Tunj PPh Nilai ---
                        payrollPerson.setTunjanganLain(payroll.getTunjanganLain()); //Tunj Lain - lain
                        payrollPerson.setTunjanganLainNilai(payroll.getTunjanganLainNilai()); //Tunj Lain - lain Nilai
                        payrollPerson.setTunjanganLembur(payroll.getTunjanganLembur()); //Tunj Lembur ---
                        payrollPerson.setTunjanganLemburNilai(payroll.getTunjanganLemburNilai()); //Tunj Lembur Nilai ---*/




                        /*payrollPerson.setUangMukaLainnya(payroll.getUangMukaLainnya()); //Uang Muka Lainnya
                        payrollPerson.setUangMukaLainnyaNilai(payroll.getUangMukaLainnyaNilai()); //Uang Muka Lainnya Nilai
                        payrollPerson.setIuranBpjsPensiun(payroll.getIuranBpjsPensiun()); //Iuran Bpjs Pensiun
                        payrollPerson.setIuranBpjsPensiunNilai(payroll.getIuranBpjsPensiunNilai()); //Iuran Bpjs Pensiun Nilai
                        payrollPerson.setIuranPensiun(payroll.getIuranPensiun()); //Iuran Pensiun
                        payrollPerson.setIuranPensiunNilai(payroll.getIuranPensiunNilai()); //Iuran Pensiun Nilai
                        payrollPerson.setPphGaji(payroll.getPphGaji()); //Pph Gaji
                        payrollPerson.setPphGajiNilai(payroll.getPphGajiNilai()); //Pph Gaji Nilai
                        payrollPerson.setPphPengobatan(payroll.getPphPengobatan()); //Pph pengobatan
                        payrollPerson.setPphPengobatanNilai(payroll.getPphPengobatanNilai()); //Pph Pengobatan Nilai
                        payrollPerson.setIuranBpjsKesehatan(payroll.getIuranBpjsKesehatan()); //Iuran Bpjs Kesehatan
                        payrollPerson.setIuranBpjsKesehatanNilai(payroll.getIuranBpjsKesehatanNilai()); //Iuran Bpjs Kesehatan Nilai
                        payrollPerson.setIuranBpjsTk(payroll.getIuranBpjsTk()); //Iuran Bpjs Tk
                        payrollPerson.setIuranBpjsTkNilai(payroll.getIuranBpjsTkNilai()); //Iuran Bpjs Tk Nilai
                        payrollPerson.setKekuranganBpjsTk(payroll.getKekuranganBpjsTk()); //Kekurangan Bpjs
                        payrollPerson.setKekuranganBpjsTkNilai(payroll.getKekuranganBpjsTkNilai()); //Kekurangan Bpjs Nilai
                        payrollPerson.setTotalB(payroll.getTotalB()); //Total B
                        payrollPerson.setTotalBNilai(payroll.getTotalBNilai()); //Total B*/

                        payrollPerson.setFlagPromosiOn(payroll.isFlagPromosiOn());
                        payrollPerson.setUmr(payroll.getUmr());
                        payrollPerson.setZakat(payroll.getZakat()); //Zakat
                        payrollPerson.setZakatNilai(payroll.getZakatNilai()); //Zakat Nilai
                        payrollPerson.setPengobatan(payroll.getPengobatan()); //Pengobatan
                        payrollPerson.setPengobatanNilai(payroll.getPengobatanNilai()); //Pengobatan Nilai
                        payrollPerson.setKoperasi(payroll.getKoperasi()); //Koperasi
                        payrollPerson.setKoperasiNilai(payroll.getKoperasiNilai()); //Koperasi Nilai
                        payrollPerson.setDansos(payroll.getDansos()); //Dansos
                        payrollPerson.setDansosNilai(payroll.getDansosNilai()); //Dansos Nilai
                        payrollPerson.setSP(payroll.getSP()); //Sp
                        payrollPerson.setSPNilai(payroll.getSPNilai()); //Sp Nilai
                        payrollPerson.setBazis(payroll.getBazis()); //Bazis
                        payrollPerson.setBazisNilai(payroll.getBazisNilai()); //Bazis Nilai
                        payrollPerson.setBapor(payroll.getBapor()); //Bapor
                        payrollPerson.setBaporNilai(payroll.getBaporNilai()); //Baporilai
                        payrollPerson.setLainLain(payroll.getLainLain()); //LainLain
                        payrollPerson.setLainLainNilai(payroll.getLainLainNilai()); //LainLain Nilai
                        payrollPerson.setTotalC(payroll.getTotalC()); //Total C
                        payrollPerson.setTotalCNilai(payroll.getTotalCNilai()); //Total C Nilai

                        payrollPerson.setTotalRapel(payroll.getTotalRapel());
                        payrollPerson.setTotalRapelNilai(payroll.getTotalRapelNilai());
                        payrollPerson.setTotalThr(payroll.getTotalThr());
                        payrollPerson.setTotalThrNilai(payroll.getTotalThrNilai());
                        payrollPerson.setTotalThrBersih(payroll.getTotalThrBersih());
                        payrollPerson.setTotalThrBersihNilai(payroll.getTotalThrBersihNilai());


                        payrollPerson.setTotalPendidikan(payroll.getTotalPendidikan());
                        payrollPerson.setTotalPendidikanNilai(payroll.getTotalPendidikanNilai());
                        payrollPerson.setTotalJasProd(payroll.getTotalJasProd());
                        payrollPerson.setTotalJasProdNilai(payroll.getTotalJasProdNilai());
                        payrollPerson.setTotalInsentif(payroll.getTotalInsentif());
                        payrollPerson.setTotalInsentifNilai(payroll.getTotalInsentifNilai());
                        payrollPerson.setNettoPensiun(payroll.getNettoPensiun());
                        payrollPerson.setNettoPensiunNilai(payroll.getNettoPensiunNilai());
                        payrollPerson.setBesarJubileum(payroll.getBesarJubileum());
                        payrollPerson.setBesarJubileumNilai(payroll.getBesarJubileumNilai());
                        payrollPerson.setTotalKaliJubileum(payroll.getTotalKaliJubileum());
                        payrollPerson.setTotalKaliJubileumNilai(payroll.getTotalKaliJubileumNilai());
                        payrollPerson.setNettoJubileum(payroll.getNettoJubileum());
                        payrollPerson.setNettoJubileumNilai(payroll.getNettoJubileumNilai());

                        payrollPerson.setTotalTambahan(payroll.getTotalTambahan()); //Total D
                        payrollPerson.setTotalTambahanNilai(payroll.getTotalTambahanNilai()); //Total C Nilai
                        payrollPerson.setTotalGajiBersih(payroll.getTotalGajiBersih());
                        payrollPerson.setTotalGajiBersihNilai(payroll.getTotalGajiBersihNilai());

                        payrollPerson.setFlagKonsistensi(payroll.isFlagKonsistensi());

                        payrollPerson.setCentangKalkulasiPph(payroll.getCentangKalkulasiPph());
                        payrollPerson.setCentangKalkulasiPphPengobatan(payroll.getCentangKalkulasiPphPengobatan());
                        // PPH Pengobatan

                        setPayroll(payrollPerson);
                        session.setAttribute("listDataPayrollPerson", payrollPerson);
                        break;
                    }
                }
            } else {
                setPayroll(new Payroll());
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        //payrolls.addAll(getPayroll());
        return getPayroll();
    }

    public Payroll reloadDetail(String nip) {
        Payroll payrollPerson = new Payroll();
        HttpSession session = ServletActionContext.getRequest().getSession();
        Payroll payroll = (Payroll) session.getAttribute("listDataPayrollPerson");

        if(nip != null && !"".equalsIgnoreCase(nip)){

            if(nip.equalsIgnoreCase(payroll.getNip())){
                payrollPerson.setBulan(payroll.getBulan());
                payrollPerson.setTahun(payroll.getTahun());
                payrollPerson.setNip(payroll.getNip());
                payrollPerson.setNama(payroll.getNama());
                payrollPerson.setKelompokId(payroll.getKelompokId());
                payrollPerson.setNpwp(payroll.getNpwp());
                payrollPerson.setStatusPegawai(payroll.getStatusPegawai());
                payrollPerson.setTipePegawai(payroll.getTipePegawai());
                payrollPerson.setTipePegawaiName(payroll.getTipePegawaiName());
                payrollPerson.setStrukturGaji(payroll.getStrukturGaji());
                payrollPerson.setStTanggalAktif(payroll.getStTanggalAktif());

                payrollPerson.setStTanggalAktifSekarang(payroll.getStTanggalAktifSekarang());

                payrollPerson.setStTanggalPayroll(payroll.getStTanggalPayroll());
                payrollPerson.setMasaKerjaTahun(payroll.getMasaKerjaTahun());
                payrollPerson.setMasaKerjaBulan(payroll.getMasaKerjaBulan());
                payrollPerson.setPositionId(payroll.getPositionId());
                payrollPerson.setPositionName(payroll.getPositionName());
                payrollPerson.setDepartmentId(payroll.getDepartmentId());
                payrollPerson.setDepartmentName(payroll.getDepartmentName());
                payrollPerson.setBranchId(payroll.getBranchId());
                payrollPerson.setBranchName(payroll.getBranchName());
                payrollPerson.setGolonganId(payroll.getGolonganId());
                payrollPerson.setGolonganName(payroll.getGolonganName());
                payrollPerson.setStatusKeluarga(payroll.getStatusKeluarga());
                payrollPerson.setJumlahAnak(payroll.getJumlahAnak());
                payrollPerson.setGender(payroll.getGender());
                payrollPerson.setDanaPensiunName(payroll.getDanaPensiunName());
                payrollPerson.setFlagPjs(payroll.getFlagPjs());
                payrollPerson.setGajiPensiun(payroll.getGajiPensiun());
                payrollPerson.setGajiPensiunNilai(payroll.getGajiPensiunNilai());


                payrollPerson.setFlagPayroll(payroll.getFlagPayroll());
                payrollPerson.setFlagRapel(payroll.getFlagRapel());
                payrollPerson.setFlagThr(payroll.getFlagThr());
                payrollPerson.setFlagJubileum(payroll.getFlagJubileum());
                payrollPerson.setFlagJasprod(payroll.getFlagJasprod());
                payrollPerson.setFlagPensiun(payroll.getFlagPensiun());

                payrollPerson.setFaktorKeluargaId(payroll.getFaktorKeluargaId());

                /*payrollPerson.setCentangListrikAir("Y");
                payrollPerson.setCentangPerumahan("Y");
                payrollPerson.setCentangListrikAir(payroll.getCentangListrikAir());
                payrollPerson.setCentangPerumahan(payroll.getCentangPerumahan());
                payrollPerson.setFlagListrikAirOn(payroll.isFlagListrikAirOn());
                payrollPerson.setFlagPerumahanOn(payroll.isFlagPerumahanOn());*/

                payrollPerson.setFlagPensiunOn(payroll.isFlagPensiunOn());
                payrollPerson.setStTanggalPensiun(payroll.getStTanggalPensiun());
                payrollPerson.setLabelPensiun(payroll.getLabelPensiun());
                payrollPerson.setCentangPensiun(payroll.getCentangPensiun());
                payrollPerson.setTanggalJubileum(payroll.getTanggalJubileum());
                payrollPerson.setFlagJubileumOn(payroll.isFlagJubileumOn());
                payrollPerson.setCentangJubileum(payroll.getCentangJubileum());
                payrollPerson.setFlagJubileum(payroll.getFlagJubileum());
                payrollPerson.setLabelJubileum(payroll.getLabelJubileum());

                //Komponen A
                payrollPerson.setGajiGolongan(payroll.getGajiGolongan()); //Gaji
                payrollPerson.setGajiGolonganNilai(payroll.getGajiGolonganNilai()); //Gaji Nilai
                payrollPerson.setTunjanganUmk(payroll.getTunjanganUmk()); //sankhus
                payrollPerson.setTunjanganUmkNilai(payroll.getTunjanganUmkNilai()); //sankhus Nilai
                payrollPerson.setTunjanganJabatanStruktural(payroll.getTunjanganJabatanStruktural()); //Tunj. Jabatan
                payrollPerson.setTunjanganJabatanStrukturalNilai(payroll.getTunjanganJabatanStrukturalNilai()); //Tunj. Jabatan Nilai
                payrollPerson.setTunjanganStruktural(payroll.getTunjanganStruktural()); //Tunj. Struktural
                payrollPerson.setTunjanganStrukturalNilai(payroll.getTunjanganStrukturalNilai()); //Tunj. Struktural Nilai
                payrollPerson.setTunjanganStrategis(payroll.getTunjanganStrategis()); //Tunj. Strategis
                payrollPerson.setTunjanganStrategisNilai(payroll.getTunjanganStrategisNilai()); //Tunj. Strategis Nilai
                payrollPerson.setTunjanganPeralihan(payroll.getTunjanganPeralihan()); //Tunj. Peraliahan ---
                payrollPerson.setTunjanganPeralihanNilai(payroll.getTunjanganPeralihanNilai()); //Tunj. Peraliahan Nilai---*/
                payrollPerson.setTunjanganLain(payroll.getTunjanganLain()); //Tunj. lain ---
                payrollPerson.setTunjanganLainNilai(payroll.getTunjanganLainNilai()); //Tunj. lain Nilai---*/
                payrollPerson.setTunjanganTambahan(payroll.getTunjanganTambahan()); //Tunj. tambahan ---
                payrollPerson.setTunjanganTambahanNilai(payroll.getTunjanganTambahanNilai()); //Tunj. tambahan Nilai---*/
                payrollPerson.setPemondokan(payroll.getPemondokan());
                payrollPerson.setPemondokanNilai(payroll.getPemondokanNilai());
                payrollPerson.setKomunikasi(payroll.getKomunikasi());
                payrollPerson.setKomunikasiNilai(payroll.getKomunikasiNilai());
                payrollPerson.setTunjanganLembur(payroll.getTunjanganLembur());
                payrollPerson.setTunjanganLemburNilai(payroll.getTunjanganLemburNilai());
                payrollPerson.setTotalA(payroll.getTotalA());
                payrollPerson.setTotalANilai(payroll.getTotalANilai()); //Nilai Total A


                //Komponen B

                //RLAB
                payrollPerson.setTunjanganRumah(payroll.getTunjanganRumah());
                payrollPerson.setTunjanganRumahNilai(payroll.getTunjanganRumahNilai());
                payrollPerson.setTunjanganListrik(payroll.getTunjanganListrik());
                payrollPerson.setTunjanganListrikNilai(payroll.getTunjanganListrikNilai());
                payrollPerson.setTunjanganAir(payroll.getTunjanganAir());
                payrollPerson.setTunjanganAirNilai(payroll.getTunjanganAirNilai());
                payrollPerson.setTunjanganBbm(payroll.getTunjanganBbm());
                payrollPerson.setTunjanganBBMNilai(payroll.getTunjanganBBMNilai());
                payrollPerson.setTotalRlab(payroll.getTotalRlab());
                payrollPerson.setTotalRlabNilai(payroll.getTotalRlabNilai());

                payrollPerson.setTunjanganBpjsKs(payroll.getTunjanganBpjsKs());
                payrollPerson.setTunjanganBpjsKsNilai(payroll.getTunjanganBpjsKsNilai());
                payrollPerson.setTunjanganBpjsTk(payroll.getTunjanganBpjsTk());
                payrollPerson.setTunjanganBpjsTkNilai(payroll.getTunjanganBpjsTkNilai());

                payrollPerson.setTunjanganDapen(payroll.getTunjanganDapen());
                payrollPerson.setTunjanganDapenNilai(payroll.getTunjanganDapenNilai());

                payrollPerson.setTunjanganSosialLain(payroll.getTunjanganSosialLain());
                payrollPerson.setTunjanganSosialLainNilai(payroll.getTunjanganSosialLainNilai());

                payrollPerson.setTunjanganPph(payroll.getTunjanganPph());
                payrollPerson.setTunjanganPphNilai(payroll.getTunjanganPphNilai());


                //Komponen C
                payrollPerson.setIuranDapenPeg(payroll.getIuranDapenPeg());
                payrollPerson.setIuranDapenPegNilai(payroll.getIuranDapenPegNilai());
                payrollPerson.setIuranDapenPersh(payroll.getIuranDapenPersh());
                payrollPerson.setIuranDapenPershNilai(payroll.getIuranDapenPershNilai());
                payrollPerson.setIuranBpjsKsKary(payroll.getIuranBpjsKsKary());
                payrollPerson.setIuranBpjsKsKaryNilai(payroll.getIuranBpjsKsKaryNilai());
                payrollPerson.setIuranBpjsKsPersh(payroll.getIuranBpjsKsPersh());
                payrollPerson.setIuranBpjsKsPersNilai(payroll.getIuranBpjsKsPersNilai());
                payrollPerson.setIuranBpjsTkKary(payroll.getIuranBpjsTkKary());
                payrollPerson.setIuranBpjsTkKaryNilai(payroll.getIuranBpjsTkKaryNilai());
                payrollPerson.setIuranBpjsTkPers(payroll.getIuranBpjsTkPers());
                payrollPerson.setIuranBpjsTkPersNilai(payroll.getIuranBpjsTkPersNilai());
                payrollPerson.setPphGaji(payroll.getPphGaji());
                payrollPerson.setPphGajiNilai(payroll.getPphGajiNilai());
                payrollPerson.setTotalPotonganLain(payroll.getTotalPotonganLain());
                payrollPerson.setTotalPotonganLainNilai(payroll.getTotalPotonganLainNilai());
                payrollPerson.setIuranYpks(payroll.getIuranYpks());
                payrollPerson.setIuranYpksNilai(payroll.getIuranYpksNilai());


                //Rincian Potongan lain - lain
                payrollPerson.setKopkar(payroll.getKopkar());
                payrollPerson.setIuranSp(payroll.getIuranSp());
                payrollPerson.setIuranPiikb(payroll.getIuranPiikb());
                payrollPerson.setBankBri(payroll.getBankBri());
                payrollPerson.setBankMandiri(payroll.getBankMandiri());
                payrollPerson.setInfaq(payroll.getInfaq());
                payrollPerson.setPerkesDanObat(payroll.getPerkesDanObat());
                payrollPerson.setListrik(payroll.getListrik());
                payrollPerson.setIuranProfesi(payroll.getIuranProfesi());
                payrollPerson.setPotonganLain(payroll.getPotonganLain());

                payrollPerson.setKopkarNilai(payroll.getKopkarNilai());
                payrollPerson.setIuranSpNilai(payroll.getIuranSpNilai());
                payrollPerson.setIuranPiikbNilai(payroll.getIuranPiikbNilai());
                payrollPerson.setBankBriNilai(payroll.getBankBriNilai());
                payrollPerson.setBankMandiriNilai(payroll.getBankMandiriNilai());
                payrollPerson.setInfaqNilai(payroll.getInfaqNilai());
                payrollPerson.setPerkesDanObatNilai(payroll.getPerkesDanObatNilai());
                payrollPerson.setListrikNilai(payroll.getListrikNilai());
                payrollPerson.setIuranProfesiNilai(payroll.getIuranProfesiNilai());
                payrollPerson.setPotonganLainNilai(payroll.getPotonganLainNilai());
                //Total A + B + C
                payrollPerson.setTotalA(payroll.getTotalA()); //Total A
                payrollPerson.setTotalANilai(payroll.getTotalANilai()); //Total A
                payrollPerson.setTotalB(payroll.getTotalB()); //Total B
                payrollPerson.setTotalBNilai(payroll.getTotalBNilai()); //Total B
                payrollPerson.setTotalC(payroll.getTotalC()); //Total B
                payrollPerson.setTotalCNilai(payroll.getTotalCNilai()); //Total B

                payrollPerson.setTotalGajiBersih(payroll.getTotalGajiBersih());
                payrollPerson.setTotalGajiBersihNilai(payroll.getTotalGajiBersihNilai());

                setPayroll(payrollPerson);
                session.setAttribute("listDataPayrollPerson", payrollPerson);
            } else {
                setPayroll(new Payroll());
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        //payrolls.addAll(getPayroll());
        return getPayroll();
    }

    public Payroll reloadDetailEdit(String payrollId) {
        Payroll payrollPerson = new Payroll();
        HttpSession session = ServletActionContext.getRequest().getSession();
        Payroll payroll = (Payroll) session.getAttribute("listDataPayrollPerson");

        if(payrollId != null && !"".equalsIgnoreCase(payrollId)){

            if(payrollId.equalsIgnoreCase(payroll.getPayrollId())){
                payrollPerson.setPayrollId(payroll.getPayrollId());
                payrollPerson.setBulan(payroll.getBulan());
                payrollPerson.setTahun(payroll.getTahun());
                payrollPerson.setNip(payroll.getNip());
                payrollPerson.setNama(payroll.getNama());
                payrollPerson.setKelompokId(payroll.getKelompokId());
                payrollPerson.setNpwp(payroll.getNpwp());
                payrollPerson.setStatusPegawai(payroll.getStatusPegawai());
                payrollPerson.setTipePegawai(payroll.getTipePegawai());
                payrollPerson.setTipePegawaiName(payroll.getTipePegawaiName());
                payrollPerson.setStrukturGaji(payroll.getStrukturGaji());
                payrollPerson.setStTanggalAktif(payroll.getStTanggalAktif());

                payrollPerson.setStTanggalAktifSekarang(payroll.getStTanggalAktifSekarang());

                payrollPerson.setStTanggalPayroll(payroll.getStTanggalPayroll());
                payrollPerson.setMasaKerjaTahun(payroll.getMasaKerjaTahun());
                payrollPerson.setMasaKerjaBulan(payroll.getMasaKerjaBulan());
                payrollPerson.setPositionId(payroll.getPositionId());
                payrollPerson.setPositionName(payroll.getPositionName());
                payrollPerson.setDepartmentId(payroll.getDepartmentId());
                payrollPerson.setDepartmentName(payroll.getDepartmentName());
                payrollPerson.setBranchId(payroll.getBranchId());
                payrollPerson.setBranchName(payroll.getBranchName());
                payrollPerson.setGolonganId(payroll.getGolonganId());
                payrollPerson.setGolonganName(payroll.getGolonganName());
                payrollPerson.setStatusKeluarga(payroll.getStatusKeluarga());
                payrollPerson.setJumlahAnak(payroll.getJumlahAnak());
                payrollPerson.setGender(payroll.getGender());
                payrollPerson.setDanaPensiunName(payroll.getDanaPensiunName());

                payrollPerson.setStMasaKerjaGol(payroll.getStMasaKerjaGol());
                payrollPerson.setMasaKerjaGol(payroll.getMasaKerjaGol());
                payrollPerson.setGolonganDapenId(payroll.getGolonganDapenId());
                payrollPerson.setGolonganDapenName(payroll.getGolonganDapenName());
                payrollPerson.setGajiPensiunNilai(payroll.getBpjsPensiunNilai());
                payrollPerson.setGajiPensiun(payroll.getGajiPensiun());

                payrollPerson.setFlagPayroll(payroll.getFlagPayroll());
                payrollPerson.setFlagRapel(payroll.getFlagRapel());
                payrollPerson.setFlagThr(payroll.getFlagThr());
                payrollPerson.setFlagJubileum(payroll.getFlagJubileum());
                payrollPerson.setFlagJasprod(payroll.getFlagJasprod());
                payrollPerson.setFlagInsentif(payroll.getFlagInsentif());
                payrollPerson.setFlagPensiun(payroll.getFlagPensiun());

                payrollPerson.setFaktorKeluargaId(payroll.getFaktorKeluargaId());

                        /*

                        payrollPerson.setFlagPensiunOn(payroll.isFlagPensiunOn());
                        payrollPerson.setStTanggalPensiun(payroll.getStTanggalPensiun());
                        payrollPerson.setLabelPensiun(payroll.getLabelPensiun());
                        payrollPerson.setCentangPensiun(payroll.getCentangPensiun());
                        payrollPerson.setTanggalJubileum(payroll.getTanggalJubileum());
                        payrollPerson.setFlagJubileumOn(payroll.isFlagJubileumOn());
                        payrollPerson.setCentangJubileum(payroll.getCentangJubileum());
                        payrollPerson.setFlagJubileum(payroll.getFlagJubileum());
                        payrollPerson.setLabelJubileum(payroll.getLabelJubileum());*/

                //Komponen A
                payrollPerson.setGajiGolongan(payroll.getGajiGolongan()); //Gaji
                payrollPerson.setGajiGolonganNilai(payroll.getGajiGolonganNilai()); //Gaji Nilai
                payrollPerson.setTunjanganUmk(payroll.getTunjanganUmk()); //sankhus
                payrollPerson.setTunjanganUmkNilai(payroll.getTunjanganUmkNilai()); //sankhus Nilai
                payrollPerson.setTunjanganJabatanStruktural(payroll.getTunjanganJabatanStruktural()); //Tunj. Jabatan
                payrollPerson.setTunjanganJabatanStrukturalNilai(payroll.getTunjanganJabatanStrukturalNilai()); //Tunj. Jabatan Nilai
                payrollPerson.setTunjanganStruktural(payroll.getTunjanganStruktural()); //Tunj. Struktural
                payrollPerson.setTunjanganStrukturalNilai(payroll.getTunjanganStrukturalNilai()); //Tunj. Struktural Nilai
                payrollPerson.setTunjanganStrategis(payroll.getTunjanganStrategis()); //Tunj. Strategis
                payrollPerson.setTunjanganStrategisNilai(payroll.getTunjanganStrategisNilai()); //Tunj. Strategis Nilai
                payrollPerson.setTunjanganPeralihan(payroll.getTunjanganPeralihan()); //Tunj. Peraliahan ---
                payrollPerson.setTunjanganPeralihanNilai(payroll.getTunjanganPeralihanNilai()); //Tunj. Peraliahan Nilai---*/
                payrollPerson.setTunjanganLain(payroll.getTunjanganLain()); //Tunj. lain ---
                payrollPerson.setTunjanganLainNilai(payroll.getTunjanganLainNilai()); //Tunj. lain Nilai---*/
                payrollPerson.setTunjanganTambahan(payroll.getTunjanganTambahan()); //Tunj. tambahan ---
                payrollPerson.setTunjanganTambahanNilai(payroll.getTunjanganTambahanNilai()); //Tunj. tambahan Nilai---*/
                payrollPerson.setTunjanganLembur(payroll.getTunjanganLembur());
                payrollPerson.setTunjanganLemburNilai(payroll.getTunjanganLemburNilai()); //Tunj. tambahan Nilai---*/
                payrollPerson.setPemondokan(payroll.getPemondokan());
                payrollPerson.setPemondokanNilai(payroll.getPemondokanNilai());
                payrollPerson.setKomunikasi(payroll.getKomunikasi());
                payrollPerson.setKomunikasiNilai(payroll.getKomunikasiNilai());
                payrollPerson.setTotalA(payroll.getTotalA());
                payrollPerson.setTotalANilai(payroll.getTotalANilai()); //Nilai Total A


                //Komponen B

                //RLAB
                payrollPerson.setTunjanganRumah(payroll.getTunjanganRumah());
                payrollPerson.setTunjanganRumahNilai(payroll.getTunjanganRumahNilai());
                payrollPerson.setTunjanganListrik(payroll.getTunjanganListrik());
                payrollPerson.setTunjanganListrikNilai(payroll.getTunjanganListrikNilai());
                payrollPerson.setTunjanganAir(payroll.getTunjanganAir());
                payrollPerson.setTunjanganAirNilai(payroll.getTunjanganAirNilai());
                payrollPerson.setTunjanganBbm(payroll.getTunjanganBbm());
                payrollPerson.setTunjanganBBMNilai(payroll.getTunjanganBBMNilai());
                payrollPerson.setTotalRlab(payroll.getTotalRlab());
                payrollPerson.setTotalRlabNilai(payroll.getTotalRlabNilai());

                payrollPerson.setTunjanganBpjsKs(payroll.getTunjanganBpjsKs());
                payrollPerson.setTunjanganBpjsKsNilai(payroll.getTunjanganBpjsKsNilai());
                payrollPerson.setTunjanganBpjsTk(payroll.getTunjanganBpjsTk());
                payrollPerson.setTunjanganBpjsTkNilai(payroll.getTunjanganBpjsTkNilai());

                payrollPerson.setTunjanganDapen(payroll.getTunjanganDapen());
                payrollPerson.setTunjanganDapenNilai(payroll.getTunjanganDapenNilai());

                payrollPerson.setTunjanganSosialLain(payroll.getTunjanganSosialLain());
                payrollPerson.setTunjanganSosialLainNilai(payroll.getTunjanganSosialLainNilai());

                payrollPerson.setTunjanganPph(payroll.getTunjanganPph());
                payrollPerson.setTunjanganPphNilai(payroll.getTunjanganPphNilai());


                //Komponen C
                payrollPerson.setIuranDapenPeg(payroll.getIuranDapenPeg());
                payrollPerson.setIuranDapenPegNilai(payroll.getIuranDapenPegNilai());
                payrollPerson.setIuranDapenPersh(payroll.getIuranDapenPersh());
                payrollPerson.setIuranDapenPershNilai(payroll.getIuranDapenPershNilai());
                payrollPerson.setIuranBpjsKsKary(payroll.getIuranBpjsKsKary());
                payrollPerson.setIuranBpjsKsKaryNilai(payroll.getIuranBpjsKsKaryNilai());
                payrollPerson.setIuranBpjsKsPersh(payroll.getIuranBpjsKsPersh());
                payrollPerson.setIuranBpjsKsPersNilai(payroll.getIuranBpjsKsPersNilai());
                payrollPerson.setIuranBpjsTkKary(payroll.getIuranBpjsTkKary());
                payrollPerson.setIuranBpjsTkKaryNilai(payroll.getIuranBpjsTkKaryNilai());
                payrollPerson.setIuranBpjsTkPers(payroll.getIuranBpjsTkPers());
                payrollPerson.setIuranBpjsTkPersNilai(payroll.getIuranBpjsTkPersNilai());
                payrollPerson.setPphGaji(payroll.getPphGaji());
                payrollPerson.setPphGajiNilai(payroll.getPphGajiNilai());
                payrollPerson.setTotalPotonganLain(payroll.getTotalPotonganLain());
                payrollPerson.setTotalPotonganLainNilai(payroll.getTotalPotonganLainNilai());
                payrollPerson.setIuranYpks(payroll.getIuranYpks());
                payrollPerson.setIuranYpksNilai(payroll.getIuranYpksNilai());


                //Rincian Potongan lain - lain
                payrollPerson.setKopkar(payroll.getKopkar());
                payrollPerson.setIuranSp(payroll.getIuranSp());
                payrollPerson.setIuranPiikb(payroll.getIuranPiikb());
                payrollPerson.setBankBri(payroll.getBankBri());
                payrollPerson.setBankMandiri(payroll.getBankMandiri());
                payrollPerson.setInfaq(payroll.getInfaq());
                payrollPerson.setPerkesDanObat(payroll.getPerkesDanObat());
                payrollPerson.setListrik(payroll.getListrik());
                payrollPerson.setIuranProfesi(payroll.getIuranProfesi());
                payrollPerson.setPotonganLain(payroll.getPotonganLain());

                payrollPerson.setKopkarNilai(payroll.getKopkarNilai());
                payrollPerson.setIuranSpNilai(payroll.getIuranSpNilai());
                payrollPerson.setIuranPiikbNilai(payroll.getIuranPiikbNilai());
                payrollPerson.setBankBriNilai(payroll.getBankBriNilai());
                payrollPerson.setBankMandiriNilai(payroll.getBankMandiriNilai());
                payrollPerson.setInfaqNilai(payroll.getInfaqNilai());
                payrollPerson.setPerkesDanObatNilai(payroll.getPerkesDanObatNilai());
                payrollPerson.setListrikNilai(payroll.getListrikNilai());
                payrollPerson.setIuranProfesiNilai(payroll.getIuranProfesiNilai());
                payrollPerson.setPotonganLainNilai(payroll.getPotonganLainNilai());
                //Total A + B + C
                payrollPerson.setTotalA(payroll.getTotalA()); //Total A
                payrollPerson.setTotalANilai(payroll.getTotalANilai()); //Total A
                payrollPerson.setTotalB(payroll.getTotalB()); //Total B
                payrollPerson.setTotalBNilai(payroll.getTotalBNilai()); //Total B
                payrollPerson.setTotalC(payroll.getTotalC()); //Total B
                payrollPerson.setTotalCNilai(payroll.getTotalCNilai()); //Total B

                payrollPerson.setTotalGajiBersih(payroll.getTotalGajiBersih());
                payrollPerson.setTotalGajiBersihNilai(payroll.getTotalGajiBersihNilai());
                setPayroll(payrollPerson);
                session.setAttribute("listDataPayrollPerson", payrollPerson);
            } else {
                setPayroll(new Payroll());
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        //payrolls.addAll(getPayroll());
        return getPayroll();
    }

    public Payroll getDetailEdit(String payrollId) {
        Payroll payrolls = new Payroll();
        Payroll payrollPerson = new Payroll();
        PayrollPph payrollPphPerson = new PayrollPph();
        List<PayrollTunjanganLain>listOfResultPayrollTunjanganLain = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrolls = payrollBo.getDetailEditSys(payrollId);
        payrollPphPerson = payrollBo.getDetailEditPphSys(payrollId);
        listOfResultPayrollTunjanganLain = payrollBo.getDetailEditTunjLainSys(payrollId);
        // PPh pengobatan
        payrollPerson.setPayrollId(payrollId);
        payrollPerson.setBulan(payrolls.getBulan());
        payrollPerson.setTahun(payrolls.getTahun());
        payrollPerson.setNip(payrolls.getNip());
        payrollPerson.setNama(payrolls.getNama());
        payrollPerson.setKelompokId(payrolls.getKelompokId());
        payrollPerson.setNpwp(payrolls.getNpwp());
        payrollPerson.setStatusPegawai(payrolls.getStatusPegawai());
        payrollPerson.setTipePegawai(payrolls.getTipePegawai());
        payrollPerson.setTipePegawaiName(payrolls.getTipePegawaiName());
        payrollPerson.setStrukturGaji(payrolls.getStrukturGaji());
        payrollPerson.setStTanggalAktif(payrolls.getStTanggalAktif());

        payrollPerson.setStTanggalAktifSekarang(payrolls.getStTanggalAktifSekarang());

        payrollPerson.setStTanggalPayroll(payrolls.getStTanggalPayroll());
        payrollPerson.setMasaKerjaTahun(payrolls.getMasaKerjaTahun());
        payrollPerson.setMasaKerjaBulan(payrolls.getMasaKerjaBulan());
        payrollPerson.setPositionId(payrolls.getPositionId());
        payrollPerson.setPositionName(payrolls.getPositionName());
        payrollPerson.setDepartmentId(payrolls.getDepartmentId());
        payrollPerson.setDepartmentName(payrolls.getDepartmentName());
        payrollPerson.setBranchId(payrolls.getBranchId());
        payrollPerson.setBranchName(payrolls.getBranchName());
        payrollPerson.setGolonganId(payrolls.getGolonganId());
        payrollPerson.setGolonganName(payrolls.getGolonganName());
        payrollPerson.setStatusKeluarga(payrolls.getStatusKeluarga());
        payrollPerson.setJumlahAnak(payrolls.getJumlahAnak());
        payrollPerson.setGender(payrolls.getGender());
        payrollPerson.setDanaPensiunName(payrolls.getDanaPensiunName());
        payrollPerson.setFlagPjs(payrolls.getFlagPjs());
        payrollPerson.setFlagPromosiOn(payrolls.isFlagPromosiOn());

        payrollPerson.setStMasaKerjaGol(payrolls.getStMasaKerjaGol());
        payrollPerson.setMasaKerjaGol(payrolls.getMasaKerjaGol());
        payrollPerson.setGolonganDapenId(payrolls.getGolonganDapenId());
        payrollPerson.setGolonganDapenName(payrolls.getGolonganDapenName());
        payrollPerson.setGajiPensiunNilai(payrolls.getBpjsPensiunNilai());
        payrollPerson.setGajiPensiun(payrolls.getGajiPensiun());

        payrollPerson.setFlagPayroll(payrolls.getFlagPayroll());
        payrollPerson.setFlagRapel(payrolls.getFlagRapel());
        payrollPerson.setFlagThr(payrolls.getFlagThr());
        payrollPerson.setFlagJubileum(payrolls.getFlagJubileum());
        payrollPerson.setFlagJasprod(payrolls.getFlagJasprod());
        payrollPerson.setFlagInsentif(payrolls.getFlagInsentif());
        payrollPerson.setFlagPensiun(payrolls.getFlagPensiun());

        payrollPerson.setFaktorKeluargaId(payrolls.getFaktorKeluargaId());
        //Komponen A
        payrollPerson.setGajiGolongan(payrolls.getGajiGolongan()); //Gaji
        payrollPerson.setGajiGolonganNilai(payrolls.getGajiGolonganNilai()); //Gaji Nilai
        payrollPerson.setTunjanganUmk(payrolls.getTunjanganUmk()); //sankhus
        payrollPerson.setTunjanganUmkNilai(payrolls.getTunjanganUmkNilai()); //sankhus Nilai
        payrollPerson.setTunjanganJabatanStruktural(payrolls.getTunjanganJabatanStruktural()); //Tunj. Jabatan
        payrollPerson.setTunjanganJabatanStrukturalNilai(payrolls.getTunjanganJabatanStrukturalNilai()); //Tunj. Jabatan Nilai
        payrollPerson.setTunjanganStruktural(payrolls.getTunjanganStruktural()); //Tunj. Struktural
        payrollPerson.setTunjanganStrukturalNilai(payrolls.getTunjanganStrukturalNilai()); //Tunj. Struktural Nilai
        payrollPerson.setTunjanganStrategis(payrolls.getTunjanganStrategis()); //Tunj. Strategis
        payrollPerson.setTunjanganStrategisNilai(payrolls.getTunjanganStrategisNilai()); //Tunj. Strategis Nilai
        payrollPerson.setTunjanganPeralihan(payrolls.getTunjanganPeralihan()); //Tunj. Peraliahan ---
        payrollPerson.setTunjanganPeralihanNilai(payrolls.getTunjanganPeralihanNilai()); //Tunj. Peraliahan Nilai---*/
        payrollPerson.setTunjanganLain(payrolls.getTunjanganLain()); //Tunj. lain ---
        payrollPerson.setTunjanganLainNilai(payrolls.getTunjanganLainNilai()); //Tunj. lain Nilai---*/
        payrollPerson.setTunjanganTambahan(payrolls.getTunjanganTambahan()); //Tunj. tambahan ---
        payrollPerson.setTunjanganTambahanNilai(payrolls.getTunjanganTambahanNilai()); //Tunj. tambahan Nilai---*/
        payrollPerson.setTunjanganLembur(payrolls.getTunjanganLembur());
        payrollPerson.setTunjanganLemburNilai(payrolls.getTunjanganLemburNilai()); //Tunj. tambahan Nilai---*/
        payrollPerson.setPemondokan(payrolls.getPemondokan());
        payrollPerson.setPemondokanNilai(payrolls.getPemondokanNilai());
        payrollPerson.setKomunikasi(payrolls.getKomunikasi());
        payrollPerson.setKomunikasiNilai(payrolls.getKomunikasiNilai());
        payrollPerson.setTambahanLain(payrolls.getTambahanLain());
        payrollPerson.setTambahanLainNilai(payrolls.getTambahanLainNilai());
        payrollPerson.setTotalA(payrolls.getTotalA());
        payrollPerson.setTotalANilai(payrolls.getTotalANilai()); //Nilai Total A


        //Komponen B

        //RLAB
        payrollPerson.setTunjanganRumah(payrolls.getTunjanganRumah());
        payrollPerson.setTunjanganRumahNilai(payrolls.getTunjanganRumahNilai());
        payrollPerson.setTunjanganListrik(payrolls.getTunjanganListrik());
        payrollPerson.setTunjanganListrikNilai(payrolls.getTunjanganListrikNilai());
        payrollPerson.setTunjanganAir(payrolls.getTunjanganAir());
        payrollPerson.setTunjanganAirNilai(payrolls.getTunjanganAirNilai());
        payrollPerson.setTunjanganBbm(payrolls.getTunjanganBbm());
        payrollPerson.setTunjanganBBMNilai(payrolls.getTunjanganBBMNilai());
        payrollPerson.setTotalRlab(payrolls.getTotalRlab());
        payrollPerson.setTotalRlabNilai(payrolls.getTotalRlabNilai());

        payrollPerson.setTunjanganBpjsKs(payrolls.getTunjanganBpjsKs());
        payrollPerson.setTunjanganBpjsKsNilai(payrolls.getTunjanganBpjsKsNilai());
        payrollPerson.setTunjanganBpjsTk(payrolls.getTunjanganBpjsTk());
        payrollPerson.setTunjanganBpjsTkNilai(payrolls.getTunjanganBpjsTkNilai());

        payrollPerson.setTunjanganDapen(payrolls.getTunjanganDapen());
        payrollPerson.setTunjanganDapenNilai(payrolls.getTunjanganDapenNilai());

        payrollPerson.setTunjanganSosialLain(payrolls.getTunjanganSosialLain());
        payrollPerson.setTunjanganSosialLainNilai(payrolls.getTunjanganSosialLainNilai());

        payrollPerson.setTunjanganPph(payrolls.getTunjanganPph());
        payrollPerson.setTunjanganPphNilai(payrolls.getTunjanganPphNilai());


        //Komponen C
        payrollPerson.setIuranDapenPeg(payrolls.getIuranDapenPeg());
        payrollPerson.setIuranDapenPegNilai(payrolls.getIuranDapenPegNilai());
        payrollPerson.setIuranDapenPersh(payrolls.getIuranDapenPersh());
        payrollPerson.setIuranDapenPershNilai(payrolls.getIuranDapenPershNilai());
        payrollPerson.setIuranBpjsKsKary(payrolls.getIuranBpjsKsKary());
        payrollPerson.setIuranBpjsKsKaryNilai(payrolls.getIuranBpjsKsKaryNilai());
        payrollPerson.setIuranBpjsKsPersh(payrolls.getIuranBpjsKsPersh());
        payrollPerson.setIuranBpjsKsPersNilai(payrolls.getIuranBpjsKsPersNilai());
        payrollPerson.setIuranBpjsTkKary(payrolls.getIuranBpjsTkKary());
        payrollPerson.setIuranBpjsTkKaryNilai(payrolls.getIuranBpjsTkKaryNilai());
        payrollPerson.setIuranBpjsTkPers(payrolls.getIuranBpjsTkPers());
        payrollPerson.setIuranBpjsTkPersNilai(payrolls.getIuranBpjsTkPersNilai());
        payrollPerson.setPphGaji(payrolls.getPphGaji());
        payrollPerson.setPphGajiNilai(payrolls.getPphGajiNilai());
        payrollPerson.setTotalPotonganLain(payrolls.getTotalPotonganLain());
        payrollPerson.setTotalPotonganLainNilai(payrolls.getTotalPotonganLainNilai());
        payrollPerson.setIuranYpks(payrolls.getIuranYpks());
        payrollPerson.setIuranYpksNilai(payrolls.getIuranYpksNilai());


        //Rincian Potongan lain - lain
        payrollPerson.setKopkar(payrolls.getKopkar());
        payrollPerson.setIuranSp(payrolls.getIuranSp());
        payrollPerson.setIuranPiikb(payrolls.getIuranPiikb());
        payrollPerson.setBankBri(payrolls.getBankBri());
        payrollPerson.setBankMandiri(payrolls.getBankMandiri());
        payrollPerson.setInfaq(payrolls.getInfaq());
        payrollPerson.setPerkesDanObat(payrolls.getPerkesDanObat());
        payrollPerson.setListrik(payrolls.getListrik());
        payrollPerson.setIuranProfesi(payrolls.getIuranProfesi());
        payrollPerson.setPotonganLain(payrolls.getPotonganLain());

        payrollPerson.setKopkarNilai(payrolls.getKopkarNilai());
        payrollPerson.setIuranSpNilai(payrolls.getIuranSpNilai());
        payrollPerson.setIuranPiikbNilai(payrolls.getIuranPiikbNilai());
        payrollPerson.setBankBriNilai(payrolls.getBankBriNilai());
        payrollPerson.setBankMandiriNilai(payrolls.getBankMandiriNilai());
        payrollPerson.setInfaqNilai(payrolls.getInfaqNilai());
        payrollPerson.setPerkesDanObatNilai(payrolls.getPerkesDanObatNilai());
        payrollPerson.setListrikNilai(payrolls.getListrikNilai());
        payrollPerson.setIuranProfesiNilai(payrolls.getIuranProfesiNilai());
        payrollPerson.setPotonganLainNilai(payrolls.getPotonganLainNilai());
        //Total A + B + C
        payrollPerson.setTotalA(payrolls.getTotalA()); //Total A
        payrollPerson.setTotalANilai(payrolls.getTotalANilai()); //Total A
        payrollPerson.setTotalB(payrolls.getTotalB()); //Total B
        payrollPerson.setTotalBNilai(payrolls.getTotalBNilai()); //Total B
        payrollPerson.setTotalC(payrolls.getTotalC()); //Total B
        payrollPerson.setTotalCNilai(payrolls.getTotalCNilai()); //Total B

        payrollPerson.setTotalGajiBersih(payrolls.getTotalGajiBersih());
        payrollPerson.setTotalGajiBersihNilai(payrolls.getTotalGajiBersihNilai());

                        /*payrollPerson.setGajiPensiun(payroll.getGajiPensiun()); //Gaji Untuk Pensiun
                        payrollPerson.setGajiPensiunNilai(payroll.getGajiPensiunNilai()); //Gaji Untuk Pensiun
                        payrollPerson.setGajiBpjs(payroll.getGajiBpjs()); //Gaji Untuk BPJS
                        payrollPerson.setGajiBpjsNilai(payroll.getGajiBpjsNilai()); //Gaji Untuk BPJS Nilai*/


                        /*payrollPerson.setTunjanganPendidikan(payroll.getTunjanganPendidikan()); //Tunj. Pendidikan
                        payrollPerson.setTunjanganPendidikanNilai(payroll.getTunjanganPendidikanNilai()); //Tunj. Pendidikan Nilai
                        payrollPerson.setTunjanganPeralihan(payroll.getTunjanganPeralihan()); //Tunj. Peraliahan ---
                        payrollPerson.setTunjanganPeralihanNilai(payroll.getTunjanganPeralihanNilai()); //Tunj. Peraliahan Nilai---*/



                        /*payrollPerson.setKompensasi(payroll.getKompensasi()); //Kompensasi
                        payrollPerson.setKompensasiNilai(payroll.getKompensasiNilai()); //Kompensasi Nilai
                        payrollPerson.setTunjanganTransport(payroll.getTunjanganTransport()); //Transport
                        payrollPerson.setTunjanganTransportNilai(payroll.getTunjanganTransportNilai()); //Transport Nilai

                        payrollPerson.setTunjanganAirListrik(payroll.getTunjanganAirListrik()); //Tunj Air Listrik
                        payrollPerson.setTunjanganAirListrikNilai(payroll.getTunjanganAirListrikNilai()); //Tunj Air Listrik Nilai
                        payrollPerson.setTunjanganPengobatan(payroll.getTunjanganPengobatan()); //Tunj Pengobatan
                        payrollPerson.setTunjanganPengobatanNilai(payroll.getTunjanganPengobatanNilai()); //Tunj Pengobatan Nilai
                        payrollPerson.setTunjanganBajuDinas(payroll.getTunjanganBajuDinas()); //Tunj Pakaian DinAs
                        payrollPerson.setTunjanganBajuDinasNilai(payroll.getTunjanganBajuDinasNilai()); //Tunj Pakaian DinAs Nilai

                        payrollPerson.setTunjanganPerumahan(payroll.getTunjanganPerumahan()); //Tunj. Perumahan
                        payrollPerson.setTunjanganPerumahanNilai(payroll.getTunjanganPerumahanNilai()); //Tunj. Perumahan Nilai
                        payrollPerson.setTunjanganPph(payroll.getTunjanganPph()); //Tunj PPh ---
                        payrollPerson.setTunjanganPphNilai(payroll.getTunjanganPphNilai()); //Tunj PPh Nilai ---
                        payrollPerson.setTunjanganLain(payroll.getTunjanganLain()); //Tunj Lain - lain
                        payrollPerson.setTunjanganLainNilai(payroll.getTunjanganLainNilai()); //Tunj Lain - lain Nilai
                        payrollPerson.setTunjanganLembur(payroll.getTunjanganLembur()); //Tunj Lembur ---
                        payrollPerson.setTunjanganLemburNilai(payroll.getTunjanganLemburNilai()); //Tunj Lembur Nilai ---*/




                        /*payrollPerson.setUangMukaLainnya(payroll.getUangMukaLainnya()); //Uang Muka Lainnya
                        payrollPerson.setUangMukaLainnyaNilai(payroll.getUangMukaLainnyaNilai()); //Uang Muka Lainnya Nilai
                        payrollPerson.setIuranBpjsPensiun(payroll.getIuranBpjsPensiun()); //Iuran Bpjs Pensiun
                        payrollPerson.setIuranBpjsPensiunNilai(payroll.getIuranBpjsPensiunNilai()); //Iuran Bpjs Pensiun Nilai
                        payrollPerson.setIuranPensiun(payroll.getIuranPensiun()); //Iuran Pensiun
                        payrollPerson.setIuranPensiunNilai(payroll.getIuranPensiunNilai()); //Iuran Pensiun Nilai
                        payrollPerson.setPphGaji(payroll.getPphGaji()); //Pph Gaji
                        payrollPerson.setPphGajiNilai(payroll.getPphGajiNilai()); //Pph Gaji Nilai
                        payrollPerson.setPphPengobatan(payroll.getPphPengobatan()); //Pph pengobatan
                        payrollPerson.setPphPengobatanNilai(payroll.getPphPengobatanNilai()); //Pph Pengobatan Nilai
                        payrollPerson.setIuranBpjsKesehatan(payroll.getIuranBpjsKesehatan()); //Iuran Bpjs Kesehatan
                        payrollPerson.setIuranBpjsKesehatanNilai(payroll.getIuranBpjsKesehatanNilai()); //Iuran Bpjs Kesehatan Nilai
                        payrollPerson.setIuranBpjsTk(payroll.getIuranBpjsTk()); //Iuran Bpjs Tk
                        payrollPerson.setIuranBpjsTkNilai(payroll.getIuranBpjsTkNilai()); //Iuran Bpjs Tk Nilai
                        payrollPerson.setKekuranganBpjsTk(payroll.getKekuranganBpjsTk()); //Kekurangan Bpjs
                        payrollPerson.setKekuranganBpjsTkNilai(payroll.getKekuranganBpjsTkNilai()); //Kekurangan Bpjs Nilai
                        payrollPerson.setTotalB(payroll.getTotalB()); //Total B
                        payrollPerson.setTotalBNilai(payroll.getTotalBNilai()); //Total B*/

       /* payrollPerson.setFlagPromosiOn(payroll.isFlagPromosiOn());
        payrollPerson.setUmr(payroll.getUmr());
        payrollPerson.setZakat(payroll.getZakat()); //Zakat
        payrollPerson.setZakatNilai(payroll.getZakatNilai()); //Zakat Nilai
        payrollPerson.setPengobatan(payroll.getPengobatan()); //Pengobatan
        payrollPerson.setPengobatanNilai(payroll.getPengobatanNilai()); //Pengobatan Nilai
        payrollPerson.setKoperasi(payroll.getKoperasi()); //Koperasi
        payrollPerson.setKoperasiNilai(payroll.getKoperasiNilai()); //Koperasi Nilai
        payrollPerson.setDansos(payroll.getDansos()); //Dansos
        payrollPerson.setDansosNilai(payroll.getDansosNilai()); //Dansos Nilai
        payrollPerson.setSP(payroll.getSP()); //Sp
        payrollPerson.setSPNilai(payroll.getSPNilai()); //Sp Nilai
        payrollPerson.setBazis(payroll.getBazis()); //Bazis
        payrollPerson.setBazisNilai(payroll.getBazisNilai()); //Bazis Nilai
        payrollPerson.setBapor(payroll.getBapor()); //Bapor
        payrollPerson.setBaporNilai(payroll.getBaporNilai()); //Baporilai
        payrollPerson.setLainLain(payroll.getLainLain()); //LainLain
        payrollPerson.setLainLainNilai(payroll.getLainLainNilai()); //LainLain Nilai
        payrollPerson.setTotalC(payroll.getTotalC()); //Total C
        payrollPerson.setTotalCNilai(payroll.getTotalCNilai()); //Total C Nilai*/

        /*payrollPerson.setTotalRapel(payroll.getTotalRapel());
        payrollPerson.setTotalRapelNilai(payroll.getTotalRapelNilai());
        payrollPerson.setTotalThr(payroll.getTotalThr());
        payrollPerson.setTotalThrNilai(payroll.getTotalThrNilai());
        payrollPerson.setTotalThrBersih(payroll.getTotalThrBersih());
        payrollPerson.setTotalThrBersihNilai(payroll.getTotalThrBersihNilai());


        payrollPerson.setTotalPendidikan(payroll.getTotalPendidikan());
        payrollPerson.setTotalPendidikanNilai(payroll.getTotalPendidikanNilai());
        payrollPerson.setTotalJasProd(payroll.getTotalJasProd());
        payrollPerson.setTotalJasProdNilai(payroll.getTotalJasProdNilai());
        payrollPerson.setTotalInsentif(payroll.getTotalInsentif());
        payrollPerson.setTotalInsentifNilai(payroll.getTotalInsentifNilai());
        payrollPerson.setNettoPensiun(payroll.getNettoPensiun());
        payrollPerson.setNettoPensiunNilai(payroll.getNettoPensiunNilai());
        payrollPerson.setBesarJubileum(payroll.getBesarJubileum());
        payrollPerson.setBesarJubileumNilai(payroll.getBesarJubileumNilai());
        payrollPerson.setTotalKaliJubileum(payroll.getTotalKaliJubileum());
        payrollPerson.setTotalKaliJubileumNilai(payroll.getTotalKaliJubileumNilai());
        payrollPerson.setNettoJubileum(payroll.getNettoJubileum());
        payrollPerson.setNettoJubileumNilai(payroll.getNettoJubileumNilai());

        payrollPerson.setTotalTambahan(payroll.getTotalTambahan()); //Total D
        payrollPerson.setTotalTambahanNilai(payroll.getTotalTambahanNilai()); //Total C Nilai
        payrollPerson.setTotalGajiBersih(payroll.getTotalGajiBersih());
        payrollPerson.setTotalGajiBersihNilai(payroll.getTotalGajiBersihNilai());

        payrollPerson.setFlagKonsistensi(payroll.isFlagKonsistensi());

        payrollPerson.setCentangKalkulasiPph(payroll.getCentangKalkulasiPph());
        payrollPerson.setCentangKalkulasiPphPengobatan(payroll.getCentangKalkulasiPphPengobatan());
        // PPH Pengobatan
        payrollPerson.setJumlahPengobatan(payroll.getJumlahPengobatan());
        payrollPerson.setHutangPphPengobatan(payroll.getHutangPphPengobatan());
        payrollPerson.setKurangPphPengobatan(payroll.getKurangPphPengobatan());
        payrollPerson.setJumlahPphPengobatan(payroll.getJumlahPphPengobatan());*/

        session.removeAttribute("listDataPayrollPerson");
        session.removeAttribute("listDataPayrollPphPerson");
        session.removeAttribute("listDataPayrollPphPengobatanPerson");
        session.removeAttribute("listDataPayrollJubileum");
        session.removeAttribute("listDataPayrollTunjanganLain");
        session.setAttribute("listDataPayrollPerson", payrollPerson);
        session.setAttribute("listDataPayrollPphPerson", payrollPphPerson);
        session.setAttribute("listDataPayrollTunjanganLain", listOfResultPayrollTunjanganLain);
        return payrolls;
    }

    public List<AbsensiPegawai> searchDetailLembur(String nip, String branchId, String bulan, String tahun) {
        List<AbsensiPegawai> absensiPegawaiList = null;
        AbsensiPegawai searchAbsensi = new AbsensiPegawai();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        Branch branch = branchBo.getBranchById(branchId,"Y");

        int bulanBefore = Integer.valueOf(bulan);
        String strBulanBefore = bulan;
        String strBulan = bulan;
        int tahunBefore = Integer.valueOf(tahun);

        bulanBefore -= 1 ;
        if(bulanBefore == 0){
            tahunBefore -= 1 ;
            bulanBefore = 12 ;
        }
        if(bulanBefore < 10){
            strBulanBefore = "0"+bulanBefore;
        }else{
            strBulanBefore = ""+ bulanBefore;
        }

        String awal = branch.getLemburGajiAwal()+"-" + strBulanBefore + "-"+ tahunBefore;
        String akhir = branch.getLemburGajiAkhir()+"-" + bulan + "-" + tahun ;

//        String awal = "11-" + "01" + "-"+ tahunBefore;
//        String akhir = "10-" + "02" + "-" + tahun ;

        searchAbsensi.setTanggal(CommonUtil.convertToDate(awal));
        searchAbsensi.setTanggalAkhir(CommonUtil.convertToDate(akhir));
        searchAbsensi.setNip(nip);
        searchAbsensi.setBranchId(branchId);
        absensiPegawaiList = payrollBo.dataAbsensiLembur(searchAbsensi);

        //payrolls.addAll(getPayroll());
        return absensiPegawaiList;
    }


    public PayrollJubileum searchJubileumMasaKerjaDetail(String nip) {
        PayrollJubileum payrollJubileumList = null;
        PayrollJubileum searchPayrollJubileum = new PayrollJubileum();

        searchPayrollJubileum.setNip(nip);
        searchPayrollJubileum.setTipePegawai("TP03");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollJubileumList = payrollBo.searchJubileumMasaKerjaDetailSys(searchPayrollJubileum);

        //payrolls.addAll(getPayroll());
        return payrollJubileumList;
    }

    public PayrollPph getDetailPph(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollPph> listOfResult = (List<PayrollPph>) session.getAttribute("listDataPayrollPph");

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollPph payrollPph: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollPph.getNip())){
                        setPayrollPph(payrollPph);
                        break;
                    }
                }
            } else {
                setPayrollPph(new PayrollPph());
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        return getPayrollPph();
    }

    public PayrollPph getDetailPphPengobatan(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollPph> listOfResult = (List<PayrollPph>) session.getAttribute("listDataPayrollPphPengobatan");
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollPph payrollPph: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollPph.getNip())){
                        setPayrollPph(payrollPph);
                        break;
                    }
                }
            } else {
                setPayrollPph(new PayrollPph());
            }
            logger.info("[PayrollAction.getDetailPphPengobatan] end process >>>");
        }

        return getPayrollPph();
    }

    public PayrollPph getDetailPphEditSession(String payrollId) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        PayrollPph listOfResult = (PayrollPph) session.getAttribute("listDataPayrollPphPerson");

        return listOfResult;
    }

    // dwr. detail pph pengobatan
    public PayrollPph getDetailPphPengobatanByPayrollId(String payrollId) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        PayrollPph listOfResult = (PayrollPph) session.getAttribute("listDataPayrollPphPengobatanPerson");

        return listOfResult;
    }

    public PayrollPph getDetailEditPph(String payrollId) {
        PayrollPph listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditPphSys(payrollId);
        return listOfResult;
    }

    public PayrollRapel getDetailEditRapel(String payrollId) {
        PayrollRapel listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditRapelSys(payrollId);
        return listOfResult;
    }

    public PayrollRapelInsentif getDetailEditRapelInsentif(String payrollRapelId) {
        PayrollRapelInsentif listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditRapelInsentifSys(payrollRapelId);
        return listOfResult;
    }

    public List<PayrollRapelLembur> getDetailEditRapelLembur(String payrollRapelId) {
        List<PayrollRapelLembur> listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditRapelLemburSys(payrollRapelId);
        return listOfResult;
    }

    public PayrollRapelThr getDetailEditRapelThr(String rapelId) {
        PayrollRapelThr listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditRapelThrSys(rapelId);
        return listOfResult;
    }

    public PayrollRapelJubileum getDetailEditRapelJubileum(String rapelId) {
        PayrollRapelJubileum listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditRapelJubileumSys(rapelId);
        return listOfResult;
    }

    public PayrollRapelPendidikan getDetailEditRapelPendidikan(String payrollId) {
        PayrollRapelPendidikan listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditRapelPendidikanSys(payrollId);
        return listOfResult;
    }

    public PayrollThr getDetailEditThr(String payrollId) {
        PayrollThr listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditThrSys(payrollId);
        return listOfResult;
    }

    public PayrollJasprod getDetailEditJasprod(String payrollId) {
        PayrollJasprod listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditJasprodSys(payrollId);
        return listOfResult;
    }

    public PayrollInsentif getDetailEditInsentif(String payrollId) {
        PayrollInsentif listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditInsentifSys(payrollId);
        return listOfResult;
    }

    public PayrollJubileum getDetailEditJubileum(String payrollId) {
        PayrollJubileum listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditJubileumSys(payrollId);
        if(listOfResult.getGajiGolongan() == null){
            HttpSession session = ServletActionContext.getRequest().getSession();
            PayrollJubileum payrollJubileumList = (PayrollJubileum) session.getAttribute("listDataPayrollJubileum");
            listOfResult = payrollJubileumList;
        }
        return listOfResult;
    }

    public PayrollPendidikan getDetailEditPendidikan(String payrollId) {
        PayrollPendidikan listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditPendidikanSys(payrollId);
        return listOfResult;
    }

    public PayrollPensiun getDetailEditPensiun(String payrollId) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        PayrollPensiun listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailEditPensiunSys(payrollId);
        return listOfResult;
    }

    // payroll pph tahun detail
    public List<Payroll> payrollDetailPphTahun(String tahun, String nip) {
        List<Payroll> listOfResult = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        listOfResult = payrollBo.getDetailPphTahun(tahun, nip);
        return listOfResult;
    }
    public List<PayrollTunjanganLain> payrollDetailTunjLain(String bulan,String tahun, String nip) {
        List<PayrollTunjanganLain> listOfResult = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollTunjanganLain> listTunjLain = (List<PayrollTunjanganLain>) session.getAttribute("listDataPayrollTunjanganLain");
        for (PayrollTunjanganLain tunjLainLoop: listTunjLain){
            if (tunjLainLoop.getNip().equalsIgnoreCase(nip)){
                if (tunjLainLoop.getTahun().equalsIgnoreCase(tahun)){
                    if (tunjLainLoop.getBulan().equalsIgnoreCase(bulan)){
                        PayrollTunjanganLain tunjLainSave = new PayrollTunjanganLain();
                        tunjLainSave.setNip(tunjLainLoop.getNip());
                        tunjLainSave.setNamaTunjangan(tunjLainLoop.getNamaTunjangan());
                        tunjLainSave.setNilai(tunjLainLoop.getNilai());
                        tunjLainSave.setNilaiNilai(tunjLainLoop.getNilaiNilai());

                        listOfResult.add(tunjLainSave);
                    }
                }
            }
        }

        return listOfResult;
    }

    public PayrollPensiun getDetailPensiun(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollPensiun> listOfResult = (List<PayrollPensiun>) session.getAttribute("listDataPayrollPensiun");

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollPensiun payrollPensiun: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollPensiun.getNip())){
                        setPayrollPensiun(payrollPensiun);
                        break;
                    }
                }
            } else {
                setPayrollPensiun(new PayrollPensiun());
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        return getPayrollPensiun();
    }

    public PayrollJubileum getDetailJubileum(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollJubileum> listOfResult = (List<PayrollJubileum>) session.getAttribute("listDataPayrollJubileum");
        PayrollJubileum payrollJubileum = new PayrollJubileum();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollJubileum payrollJubileum1: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollJubileum1.getNip())){
                        payrollJubileum = payrollJubileum1;
                        break;
                    }
                }
            } else {
                payrollJubileum = new PayrollJubileum();
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        return payrollJubileum;
    }

    // Get Detail Rapel
    public PayrollRapel getDetailRapel(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollRapel> listOfResult = (List<PayrollRapel>) session.getAttribute("listDataPayrollRapel");

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollRapel payrollRapel: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollRapel.getNip())){
                        setPayrollRapel(payrollRapel);
                        break;
                    }
                }
            } else {
                setPayrollRapel(new PayrollRapel());
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        return getPayrollRapel();
    }

    // Reproses Payroll bulan
    public void reprosesPayroll(String payrollId, String nip, String branchId, String bulan, String tahun) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollBo.reprosesPayroll(payrollId, nip, branchId, bulan, tahun);
    }

    // Reload Biaya Lembur
    public String reloadBiayaLembur(String nip, String branchId, String bulan, String tahun) {
        List<AbsensiPegawai> absensiPegawaiList = null;
        Branch branch = new Branch();
        AbsensiPegawai searchAbsensi = new AbsensiPegawai();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        branch = branchBo.getBranchById(branchId,"Y");

        int bulanBefore = Integer.valueOf(bulan);
        String strBulanBefore = bulan;
        int tahunBefore = Integer.valueOf(tahun);

        bulanBefore -= 1 ;
        if(bulanBefore == 0){
            tahunBefore -= 1 ;
            bulanBefore = 12 ;
        }
        if(bulanBefore < 10){
            strBulanBefore = "0"+bulanBefore;
        }else{
            strBulanBefore = ""+ bulanBefore;
        }

        String awal = branch.getLemburGajiAwal()+"-" + strBulanBefore + "-"+ tahunBefore;
        String akhir = branch.getLemburGajiAkhir()+"-" + bulan + "-" + tahun ;

//        String awal = "11-" + "01" + "-"+ tahunBefore;
//        String akhir = "10-" + "02" + "-" + tahun ;

        searchAbsensi.setTanggal(CommonUtil.convertToDate(awal));
        searchAbsensi.setTanggalAkhir(CommonUtil.convertToDate(akhir));
        searchAbsensi.setNip(nip);
        searchAbsensi.setBranchId(branchId);

        absensiPegawaiList = payrollBo.dataAbsensiLembur(searchAbsensi);

        return CommonUtil.numbericFormat(payrollBo.reloadBiayaLembur(searchAbsensi), "###,###");
    }

    //Get Detail Thr
    public PayrollThr getDetailThr(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollThr> listOfResult = (List<PayrollThr>) session.getAttribute("listDataPayrollThr");
        PayrollThr payrollThr1 = null ;
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollThr payrollThr: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollThr.getNip())){
                        payrollThr1 = payrollThr;
                        break;
                    }
                }
            } else {
                payrollThr1 = new PayrollThr();
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        return payrollThr1;
    }

    //Get Detail Rapel Thr
    public PayrollRapelThr getDetailRapelThr(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollRapelThr> listOfResult = (List<PayrollRapelThr>) session.getAttribute("listDataPayrollRapelThr");
        PayrollRapelThr payrollThr1 = null ;
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollRapelThr payrollRapelThr: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollRapelThr.getNip())){
                        payrollThr1 = payrollRapelThr;
                        break;
                    }
                }
            } else {
                payrollThr1 = new PayrollRapelThr();
            }
            logger.info("[PayrollAction.getDetailRapelThr] end process >>>");
        }

        return payrollThr1;
    }

    //Get Detail Rapel Pendidikan
    public PayrollRapelPendidikan getDetailRapelPendidikan(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollRapelPendidikan> listOfResult = (List<PayrollRapelPendidikan>) session.getAttribute("listDataPayrollRapelPendidikan");
        PayrollRapelPendidikan payrollPendidikan1 = null ;
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollRapelPendidikan payrollRapelPendidikan: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollRapelPendidikan.getNip())){
                        payrollPendidikan1 = payrollRapelPendidikan;
                        break;
                    }
                }
            } else {
                payrollPendidikan1 = new PayrollRapelPendidikan();
            }
            logger.info("[PayrollAction.getDetailRapelPendidikan] end process >>>");
        }

        return payrollPendidikan1;
    }//Get Detail Rapel Pendidikan

    // Get Detail Rapel Lembur
    public List<PayrollRapelLembur> getDetailRapelLembur(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollRapelLembur> listOfResult = (List<PayrollRapelLembur>) session.getAttribute("listDataPayrollRapelLembur");
        List<PayrollRapelLembur> hasilLembur = new ArrayList<>() ;
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollRapelLembur rapelLemburLoop: listOfResult) {
                    if(nip.equalsIgnoreCase(rapelLemburLoop.getNip())){
                        rapelLemburLoop.setStrTanggal(CommonUtil.convertDateToString(rapelLemburLoop.getTanggal()));
                        hasilLembur.add(rapelLemburLoop);
                    }
                }
            } else {
                hasilLembur = new ArrayList<>();
            }
            logger.info("[PayrollAction.getDetailRapelPendidikan] end process >>>");
        }

        return hasilLembur;
    }

    //Get Detail Rapel Jubileum
    public PayrollRapelJubileum getDetailRapelJubileum(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollRapelJubileum> listOfResult = (List<PayrollRapelJubileum>) session.getAttribute("listDataPayrollRapelJubileum");
        PayrollRapelJubileum payrollJubileum1 = null ;
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollRapelJubileum payrollRapelJubileum: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollRapelJubileum.getNip())){
                        payrollJubileum1 = payrollRapelJubileum;
                        break;
                    }
                }
            } else {
                payrollJubileum1 = new PayrollRapelJubileum();
            }
            logger.info("[PayrollAction.getDetailRapelJubileum] end process >>>");
        }

        return payrollJubileum1;
    }

    //Get Detail Rapel Insentif
    public PayrollRapelInsentif getDetailRapelInsentif(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollRapelInsentif> listOfResult = (List<PayrollRapelInsentif>) session.getAttribute("listDataPayrollRapelInsentif");
        PayrollRapelInsentif payrollRapelInsentif = null ;
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollRapelInsentif payrollRapelinsentifLoop: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollRapelinsentifLoop.getNip())){
                        payrollRapelInsentif = payrollRapelinsentifLoop;
                        break;
                    }
                }
            } else {
                payrollRapelInsentif = new PayrollRapelInsentif();
            }
            logger.info("[PayrollAction.getDetailRapelInsentif] end process >>>");
        }

        return payrollRapelInsentif;
    }

    //Get Detail Pendidikan
    public PayrollPendidikan getDetailPendidikan(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollPendidikan> listOfResult = (List<PayrollPendidikan>) session.getAttribute("listDataPayrollPendidikan");

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollPendidikan payrollPendidikan: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollPendidikan.getNip())){
                        setPayrollPendidikan(payrollPendidikan);
                        break;
                    }
                }
            } else {
                setPayrollPendidikan(new PayrollPendidikan());
            }
            logger.info("[PayrollAction.init] end process >>>");
        }

        return getPayrollPendidikan();
    }

    public PayrollJasprod getDetailJasprod(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollJasprod> listOfResult = (List<PayrollJasprod>) session.getAttribute("listDataPayrollJasprod");

        logger.info("[PayrollAction.getDetailJasprod] start process >>>");
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollJasprod payrollJasprod: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollJasprod.getNip())){
                        setPayrollJasprod(payrollJasprod);
                        break;
                    }
                }
            } else {
                setPayrollJasprod(new PayrollJasprod());
            }
            logger.info("[PayrollAction.getDetailJasprod] end process >>>");
        }

        return getPayrollJasprod();
    }

    public PayrollInsentif getDetailInsentif(String nip) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollInsentif> listOfResult = (List<PayrollInsentif>) session.getAttribute("listDataPayrollInsentif");

        logger.info("[PayrollAction.getDetailInsentif] start process >>>");
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollInsentif payrollInsentif: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollInsentif.getNip())){
                        setPayrollInsentif(payrollInsentif);
                        break;
                    }
                }
            } else {
                setPayrollInsentif(new PayrollInsentif());
            }
            logger.info("[PayrollAction.getDetailInsentif] end process >>>");
        }

        return getPayrollInsentif();
    }

    @Override
    public String edit() {
        logger.info("[PayrollAction.saveAdd] start process >>>");
        try {
            Payroll payroll ;
            if(getPayroll() != null){
                payroll = getPayroll();
                if(payroll.getNama().equalsIgnoreCase("")){
                    payroll.setNip("");
                }
            }else{
                payroll = new Payroll();
                payroll.setBulan(getBulan());
                payroll.setTahun(getTahun());
                payroll.setBranchId(getBranchId());
                payroll.setTipe(getTipe());
                payroll.setNip("");
            }

            List<Payroll> listDataPayroll = new ArrayList();

            listDataPayroll = payrollBoProxy.getDataView(payroll);

            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("dataPayroll", payroll);
            session.setAttribute("listDataPayroll", listDataPayroll);

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[payrollAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[payrollAction.saveAdd] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Payroll deletePayroll = new Payroll();

        if (itemFlag != null ) {

            try {
                deletePayroll = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "PayrollBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayroll != null) {
                setPayroll(deletePayroll);

            } else {
                deletePayroll.setPayrollId(itemId);
                deletePayroll.setFlag(itemFlag);
                setPayroll(deletePayroll);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayroll.setPayrollId(itemId);
            deletePayroll.setFlag(itemFlag);
            setPayroll(deletePayroll);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[PayrollAction.saveAdd] start process >>>");
        try {
            Payroll payroll ;
            if(getPayroll() != null){
                payroll = getPayroll();
                if(payroll.getNama().equalsIgnoreCase("")){
                    payroll.setNip("");
                }
            }else{
                payroll = new Payroll();
                payroll.setBulan(getBulan());
                payroll.setTahun(getTahun());
                payroll.setBranchId(getBranchId());
                payroll.setTipe(getTipe());
                payroll.setNip("");
            }

            List<Payroll> listDataPayroll = new ArrayList();

            listDataPayroll = payrollBoProxy.getDataView(payroll);

            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("dataPayroll", payroll);
            session.setAttribute("listDataPayrollSearch", listDataPayroll);
            session.setAttribute("listDataPayroll", listDataPayroll);

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[payrollAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[payrollAction.saveAdd] end process >>>");
        return "view_payroll";
    }

    @Override
    public String save() {
        logger.info("[PayrollAction.saveAdd] start process >>>");
        try {
            Payroll payroll = getPayroll();
            List<Payroll> listDataPayroll = new ArrayList();
            List<Payroll> listDataPayrollBackup = new ArrayList();

            listDataPayroll = payrollBoProxy.dataAddPayroll(payroll);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

            listDataPayrollBackup = payrollBo.copyDataPayroll(listDataPayroll);

            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("dataPayroll", payroll);
            session.setAttribute("listDataPayroll", listDataPayroll);
            session.setAttribute("listDataPayrollSearch", listDataPayroll);
            session.setAttribute("listDataPayrollBackup", listDataPayrollBackup);

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[payrollAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[ "+ e + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[payrollAction.saveAdd] end process >>>");
        return "init_add_v2";
    }

    public void saveEditData(String payrollId, String nip, //data pegawai
                             String tunjPeralihan,String pemondokan,String komunikasi, //komponen A
                             String kopkar, String iuranSp, String iuranPiikb, String bankBri, String bankMandiri, // Komponen rincian C
                             String infaq, String perkesDanObat, String listrik, String iuranProfesi, String potonganLain, // Komponen rincian C
                             String flagJubileum, String flagPensiun, String tunjPph, String pphGaji){
        Payroll newPayroll = new Payroll();
        newPayroll.setNip(nip);
//        newPayroll.setTipePegawai(tipePegawai);
        //Komponen A
        newPayroll.setPayrollId(payrollId);
        newPayroll.setTunjanganPeralihan(tunjPeralihan);
        newPayroll.setTunjanganPeralihanNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(tunjPeralihan))));
        newPayroll.setPemondokan(pemondokan);
        newPayroll.setPemondokanNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pemondokan))));
        newPayroll.setKomunikasi(komunikasi);
        newPayroll.setKomunikasiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(komunikasi))));

        //Komponen B
        newPayroll.setTunjanganPph(tunjPph);
        newPayroll.setTunjanganPphNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(tunjPph))));

        //Rincian Potongan C
        newPayroll.setPphGaji(pphGaji);
        newPayroll.setPphGajiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pphGaji))));
        newPayroll.setIuranSp(iuranSp);
        newPayroll.setKopkar(kopkar);
        newPayroll.setKopkarNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(kopkar))));
        newPayroll.setIuranSp(iuranSp);
        newPayroll.setIuranSpNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(iuranPiikb))));
        newPayroll.setIuranPiikb(iuranSp);
        newPayroll.setIuranPiikbNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(iuranPiikb))));
        newPayroll.setBankBri(bankBri);
        newPayroll.setBankBriNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(bankBri))));
        newPayroll.setBankMandiri(bankMandiri);
        newPayroll.setBankMandiriNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(bankMandiri))));
        newPayroll.setInfaq(infaq);
        newPayroll.setInfaqNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(infaq))));
        newPayroll.setPerkesDanObat(perkesDanObat);
        newPayroll.setPerkesDanObatNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(perkesDanObat))));
        newPayroll.setListrik(listrik);
        newPayroll.setListrikNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(listrik))));
        newPayroll.setIuranProfesi(iuranProfesi);
        newPayroll.setIuranProfesiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(iuranProfesi))));
        newPayroll.setPotonganLain(potonganLain);
        newPayroll.setPotonganLainNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(potonganLain))));

        newPayroll.setCentangJubileum(flagJubileum);
        newPayroll.setFlagJubileum(flagJubileum);
        newPayroll.setCentangPensiun(flagPensiun);
        newPayroll.setFlagPensiun(flagPensiun);
        /*newPayroll.setCentangListrikAir(flagListrikAir);
        newPayroll.setCentangPerumahan(flagPerumahan);
        newPayroll.setCentangKalkulasiPph(flagKalkulasiPph);*/

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        newPayroll.setLastUpdate(updateTime);
        newPayroll.setLastUpdateWho(userLogin);
        newPayroll.setAction("U");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        //payrollBo.editDataSys(newPayroll);
        payrollBo.saveEditDataSys(newPayroll);
    }

    public void saveEditDataJasprod(String payrollId, String koperasi, String dansos, String lainLain, String flagKalkulasiPph, String pphGaji){

        Payroll newPayroll = new Payroll();
        newPayroll.setPayrollId(payrollId);

        newPayroll.setKoperasiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(koperasi))));
        newPayroll.setDansosNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(dansos))));
        newPayroll.setLainLainNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(lainLain))));
        newPayroll.setPphGajiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pphGaji))));

        newPayroll.setCentangKalkulasiPph(flagKalkulasiPph);

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        newPayroll.setLastUpdate(updateTime);
        newPayroll.setLastUpdateWho(userLogin);
        newPayroll.setAction("U");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        payrollBo.saveEditDataJasprodSys(newPayroll);
    }

    public void saveEditDataJan(String payrollId, String pengobatan, String tunjPph, String pphGaji){

        Payroll newPayroll = new Payroll();
        newPayroll.setPayrollId(payrollId);

        newPayroll.setTunjanganPengobatanNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pengobatan))));
        newPayroll.setTunjanganPphNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(tunjPph))));
        newPayroll.setPphGajiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pphGaji))));

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        payrollBo.saveEditDataJanuari(newPayroll);

    }

    public Payroll getApproval(String branchId, String bulan, String tahun, String tipe){
        Payroll payroll = null;
        Payroll searchPayroll = new Payroll();
        searchPayroll.setBranchId(branchId);
        searchPayroll.setBulan(bulan);
        searchPayroll.setTahun(tahun);
        searchPayroll.setTipe(tipe);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        payroll = payrollBo.getPayrollApprove(searchPayroll);
        return payroll ;
    }

    public List<SmkHistoryEvaluasiPegawai> listPromosi(String nip){
        List<SmkHistoryEvaluasiPegawai> historyList = null;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        historyList = payrollBo.listPromosiSys(nip);
        return historyList ;
    }

    public String cekAvailable(String branchId, String bulan, String tahun){
        Payroll payroll = new Payroll();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        payroll.setBranchId(branchId);
        payroll.setBulan(bulan);
        payroll.setTahun(tahun);
        String isFlag = payrollBo.cekAvailableSys(payroll);
        return isFlag;
    }

    public String cekBeforePayroll(String branchId, String bulan, String tahun){
        Payroll payroll = new Payroll();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        payroll.setBranchId(branchId);
        payroll.setBulan(bulan);
        payroll.setTahun(tahun);
        String isFlag = payrollBo.cekBeforePayrollSys(payroll);
        return isFlag;
    }

    public String cekApprove(String branchId, String bulan, String tahun, String tipe){
        Payroll payroll = new Payroll();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        payroll.setBranchId(branchId);
        payroll.setBulan(bulan);
        payroll.setTahun(tahun);
        payroll.setTipe("PR");
        String isFlag = payrollBo.cekApproveSys(payroll);
        return isFlag;
    }

    public void approvePayroll(String branchId, String bulan, String tahun, String statusApprove, String tipe){
        Payroll searchPayroll = new Payroll();
        searchPayroll.setBranchId(branchId);
        searchPayroll.setBulan(bulan);
        searchPayroll.setTahun(tahun);
        searchPayroll.setTipe(tipe);
        searchPayroll.setApprovalFlag(statusApprove);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollBo.approvePayroll(searchPayroll);
    }

    public String saveDelete(){
        logger.info("[PayrollAction.saveDelete] start process >>>");
        try {

            Payroll deletePayroll = getPayroll();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayroll.setLastUpdate(updateTime);
            deletePayroll.setLastUpdateWho(userLogin);
            deletePayroll.setAction("U");
            deletePayroll.setFlag("N");

            payrollBoProxy.saveDelete(deletePayroll);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "PayrollBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollAction.saveAdd] start process >>>");
        Payroll payroll = new Payroll();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        payroll.setCreatedDate(updateTime);
        payroll.setCreatedWho(userLogin);
        payroll.setLastUpdate(updateTime);
        payroll.setLastUpdateWho(userLogin);
        payroll.setAction("C");
        payroll.setFlag("Y");

        try {
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<Payroll> listPayroll = (List<Payroll>) session.getAttribute("listDataPayroll");

            payrollBoProxy.saveAddData(listPayroll, payroll);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollBO.saveAdd");
            } catch (HibernateException e1) {
                logger.error("[payrollAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[payrollAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listDataPayroll");
        session.removeAttribute("listDataPayrollBackup");

        logger.info("[payrollAction.saveAdd] end process >>>");
        return INPUT;
    }

    public String printReportPayroll(){
        logger.info("[ReportAction.printReportPayroll] start process >>>");
        String hasil = "";
        String id = getId();
        String tipe = getTipe();
        String noSurat = getNoSurat();
        String tanggalSK = getTanggalSk();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ItPayrollEntity payroll = new ItPayrollEntity();
        if (id != null) {
            if(tipe.equalsIgnoreCase("PR")){
                Branch branch = new Branch();

                try{
                    PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
                    payroll = payrollBo.getPayrollById(id);
                    BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                    branch = branchBo.getBranchById(payroll.getBranchId(),"Y");
                }catch( HibernateException e){

                }
                String logo ="";
                if (payroll.getBranchId().equalsIgnoreCase("RS01")){
                    logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
                }else if (payroll.getBranchId().equalsIgnoreCase("RS02")){
                    logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
                }else if (payroll.getBranchId().equalsIgnoreCase("RS03")){
                    logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
                }else{
                    logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
                }
                String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
                reportParams.put("urlLogo", logo);
                reportParams.put("branchId", payroll.getBranchId());
                reportParams.put("branchName", branch.getBranchName());
                reportParams.put("alamatSurat", branch.getAlamatSurat()+","+stTanggal);
                reportParams.put("bulan", payroll.getBulan());
                reportParams.put("tahun", payroll.getTahun());
                reportParams.put("titleReport", "Slip Gaji");
                reportParams.put("payrollId", id);
                reportParams.put("date", stTanggal);
                hasil = "success_print_report_payroll";
            }else if(tipe.equalsIgnoreCase("T")){

            }else if(tipe.equalsIgnoreCase("PD")){

            }else if(tipe.equalsIgnoreCase("JP")){

            }else if(tipe.equalsIgnoreCase("JB")){

            }else if(tipe.equalsIgnoreCase("PN")) {
                String payrollId = getId();
                String tahun = getTahun();
                String bulan = getBulan();
                String tanggalPensiunRealisasi = getTanggalSk();
                String positionId = getPositionId();
                String tanggalAktif = getTanggalAktif();

                Date tanggalPensiun = CommonUtil.convertStringToDate(tanggalPensiunRealisasi);
                Calendar cal = Calendar.getInstance();
                cal.setTime(tanggalPensiun);
                cal.add(Calendar.DATE, -1);
                java.util.Date tangalMasaKerjaAkhir =  cal.getTime();

                String masaKerjaAkhirRealisasi = CommonUtil.convertDateToString(tangalMasaKerjaAkhir);
                String[] tanggalSk = getTanggalSk().split("-");
                String tglPensiun = tanggalSk[0] + " " + CommonUtil.convertNumberToStringBulan(tanggalSk[1]).toUpperCase() + " " + tanggalSk[2];
                String tglCetakPensiun = tanggalSk[0] + " " + CommonUtil.convertNumberToStringBulan(tanggalSk[1]) + " " + tanggalSk[2];
                String namaDirektur = payrollBoProxy.getDirektur();
                payroll = payrollBoProxy.getPayrollById(payrollId);
                String nettoDiterima =CommonUtil.angkaToTerbilang(payroll.getGajiBersih().longValue());

                if (payrollId != null) {
                    Branch branch = new Branch();

                    try{
                        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
                        payroll = payrollBo.getPayrollById(payrollId);
                        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                        branch = branchBo.getBranchById(payroll.getBranchId(),"Y");
                    }catch( HibernateException e){

                    }
                    String logo ="";
                    if (payroll.getBranchId().equalsIgnoreCase("RS01")){
                        logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
                    }else if (payroll.getBranchId().equalsIgnoreCase("RS02")){
                        logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
                    }else if (payroll.getBranchId().equalsIgnoreCase("RS03")){
                        logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
                    }else{
                        logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
                    }
                    String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
                    reportParams.put("urlLogo", logo);
                    reportParams.put("branchId", payroll.getBranchId());
                    reportParams.put("branchName", branch.getBranchName());
                    reportParams.put("alamatSurat", branch.getAlamatSurat()+","+stTanggal);
                    reportParams.put("tahun", tahun);
                    reportParams.put("tanggalSk", tanggalSK);
                    reportParams.put("bulan", bulan);
                    reportParams.put("noSurat", getNoSurat());
                    reportParams.put("payrollId", payrollId);
                    reportParams.put("direktur", namaDirektur);
                    reportParams.put("tanggalPensiunRealisasi", tanggalPensiunRealisasi);
                    reportParams.put("masaKerjaAkhirRealisasi", masaKerjaAkhirRealisasi);
                    reportParams.put("tglPensiun", tglPensiun);
                    reportParams.put("tglCetakPensiun", tglCetakPensiun);
                    reportParams.put("nettoDiterima", nettoDiterima);
                    reportParams.put("tanggalAktif", tanggalAktif);
                    reportParams.put("positionId", positionId);
                    reportParams.put("date", stTanggal);

                    hasil = "success_print_report_payroll_pensiun";
                }
            }
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayroll");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayroll] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportPayroll] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportPayroll] Error when print report kpi unit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayroll] end process <<<");

        return hasil;
    }

    public String printReportPayrollByBranch(){
        logger.info("[ReportAction.printReportKPIUnit] start process >>>");
        String branchId = getBranchId();
        Branch branch = new Branch();
        String bulan = getBulan();
        String tahun = getTahun();
        String tipe = getTipe();

        String hasil = "";

        if(tipe.equalsIgnoreCase("PR")){
            hasil = "success_print_report_branch_payroll";
            reportParams.put("titleReport", "Slip Gaji");
        }else if(tipe.equalsIgnoreCase("CT")){
            hasil = "success_print_report_payroll_Cuti_tahunan_branch";
        }else if(tipe.equalsIgnoreCase("T")){
            hasil = "success_print_report_payroll_thr_branch";
        }else if(tipe.equalsIgnoreCase("IN")){
            reportParams.put("titleReport", "Slip Tunjangan Insentif");
            hasil = "success_print_report_payroll_insentif_branch";
        }else if(tipe.equalsIgnoreCase("JP")){
            reportParams.put("titleReport", "Slip Jasa Produksi");
            hasil = "success_print_report_payroll_jasprod_branch";
        }else if(tipe.equalsIgnoreCase("CP")){
            hasil = "success_print_report_payroll_Cuti_panjang_branch";
        }
        if (branchId != null) {
            try{
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                branch = branchBo.getBranchById(branchId,"Y");
            }catch( HibernateException e){

            }
            String logo ="";
            if (branchId.equalsIgnoreCase("RS01")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
            }else if (branchId.equalsIgnoreCase("RS02")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
            }else if (branchId.equalsIgnoreCase("RS03")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
            }else{
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
            }
            String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
            reportParams.put("urlLogo", logo);
            reportParams.put("branchId", branchId);
            reportParams.put("branchName", branch.getBranchName());
            reportParams.put("alamatSurat", branch.getAlamatSurat()+","+stTanggal);
            reportParams.put("bulan", bulan);
            reportParams.put("tahun", tahun);
            reportParams.put("date", stTanggal);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollByBranch");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportKPIUnit] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportKPIUnit] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportKPIUnit] Error when print report kpi unit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayrollByBranch] end process <<<");

        return hasil;
    }

    public String printReportJasprod(){
        logger.info("[ReportAction.printReportPayrollJasprod] start process >>>");
        String payrollId = getId();
        if (payrollId != null) {
            Branch branch = new Branch();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ItPayrollEntity payroll = new ItPayrollEntity();
            try{
                PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
                payroll = payrollBo.getPayrollById(payrollId);
                BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                branch = branchBo.getBranchById(payroll.getBranchId(),"Y");
            }catch( HibernateException e){

            }
            String logo ="";
            if (payroll.getBranchId().equalsIgnoreCase("RS01")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS02")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS03")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
            }else{
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
            }
            String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
            reportParams.put("urlLogo", logo);
            reportParams.put("branchId", payroll.getBranchId());
            reportParams.put("branchName", branch.getBranchName());
            reportParams.put("alamatSurat", branch.getAlamatSurat()+","+stTanggal);
            reportParams.put("bulan", payroll.getBulan());
            reportParams.put("tahun", payroll.getTahun());
            reportParams.put("titleReport", "Slip Gaji");
            reportParams.put("payrollId", id);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollJasprod");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayrollJasprod] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportKPIUnit] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportPayrollJasprod] Error when print report kpi unit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayrollJasprod] end process <<<");
        return "success_print_report_payroll_jasprod";
    }

    public String printReportJubileum(){
        logger.info("[ReportAction.printReportPayrollJubileum] start process >>>");
        String payrollId = getId();
        String kabidSdm = getKabidSdm();
        if (("").equalsIgnoreCase(kabidSdm)){
            kabidSdm = payrollBoProxy.getKabidSdm();
        }
        String tanggalAkhirMasaKerja=getTanggalAkhir();
        String tanggalCetak=getTanggal();
        String[] tgl = tanggalCetak.split("-");
        String tglCetakFinal = tgl[0]+" "+CommonUtil.convertNumberToStringBulan(tgl[1])+" "+tgl[2];

        if (payrollId != null) {
            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("titleReport", "PENETAPAN UANG JUBILEUM");
            reportParams.put("payrollId", payrollId);
            reportParams.put("namaKabid", kabidSdm);
            reportParams.put("tanggalAkhirMasaKerja", tanggalAkhirMasaKerja);
            reportParams.put("tanggalCetak", tglCetakFinal);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollJasprod");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayrollJasprod] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportKPIUnit] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportPayrollJubileum] Error when print Payroll Jubileum, Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, Payroll Id is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayrollJubileum] end process <<<");
        return "success_print_report_payroll_jubileum";
    }

    public String printReportInsentif(){
        logger.info("[PayrollAction.printReportPayrollInsentif] start process >>>");
        String payrollId = getId();
        if (payrollId != null) {
            Branch branch = new Branch();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ItPayrollEntity payroll = new ItPayrollEntity();
            try{
                PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
                payroll = payrollBo.getPayrollById(payrollId);
                BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                branch = branchBo.getBranchById(payroll.getBranchId(),"Y");
            }catch( HibernateException e){

            }
            String logo ="";
            if (payroll.getBranchId().equalsIgnoreCase("RS01")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS02")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS03")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
            }else{
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
            }
            String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
            reportParams.put("urlLogo", logo);
            reportParams.put("branchId", payroll.getBranchId());
            reportParams.put("branchName", branch.getBranchName());
            reportParams.put("alamatSurat", branch.getAlamatSurat()+","+stTanggal);
            reportParams.put("bulan", payroll.getBulan());
            reportParams.put("tahun", payroll.getTahun());
            reportParams.put("titleReport", "Slip Gaji");
            reportParams.put("payrollId", id);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollInsentif");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayrollJasprod] Error when downloading ,", e1);
                }
                logger.error("[PayrollAction.printReportPayrollInsentif] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[PayrollAction.printReportPayrollInsentif] Error when print Payroll Jubileum, Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, Payroll Id is empty, please inform to your admin.");
        }

        logger.info("[PayrollAction.printReportPayrollInsentif] end process <<<");
        return "success_print_report_payroll_insentif";
    }

    public String printReportPensiun(){
        logger.info("[ReportAction.printReportPayrollPensiun] start process >>>");
        String payrollId = getId();
        String tahun = getTahun();
        String bulan = getBulan();
        String tanggalPensiunRealisasi = getTanggal();

        Date tanggalPensiun = CommonUtil.convertStringToDate(tanggalPensiunRealisasi);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggalPensiun);
        cal.add(Calendar.DATE, -1);
        Date tangalMasaKerjaAkhir= (Date) cal.getTime();

        String masaKerjaAkhirRealisasi = CommonUtil.convertDateToString(tangalMasaKerjaAkhir);
        String[] tanggalSk = getTanggalSk().split("-");
        String tglPensiun = tanggalSk[0]+" "+CommonUtil.convertNumberToStringBulan(tanggalSk[1]).toUpperCase()+" "+tanggalSk[2];
        String tglCetakPensiun = tanggalSk[0]+" "+CommonUtil.convertNumberToStringBulan(tanggalSk[1])+" "+tanggalSk[2];
        String namaDirektur = payrollBoProxy.getDirektur();

        if (payrollId != null) {
            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("tahun", tahun);
            reportParams.put("bulan", bulan);
            reportParams.put("payrollId", payrollId);
            reportParams.put("direktur", namaDirektur);
            reportParams.put("tanggalPensiunRealisasi", tanggalPensiunRealisasi);
            reportParams.put("masaKerjaAkhirRealisasi", masaKerjaAkhirRealisasi);
            reportParams.put("tglPensiun", tglPensiun);
            reportParams.put("tglCetakPensiun", tglCetakPensiun);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollJasprod");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayrollJasprod] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportPayrollPensiun] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportPayrollPensiun] Error when print report kpi unit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayrollPensiun] end process <<<");
        return "success_print_report_payroll_pensiun";
    }

    public String printReportRapel(){
        logger.info("[ReportAction.printReportPayrollRapel] start process >>>");
        String payrollId = getId();
        if (payrollId != null) {
            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("tahun", tahun);
            reportParams.put("bulan", bulan);
            reportParams.put("payrollId", payrollId);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollRapel");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayrollRapel] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportPayrollPensiun] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportPayrollRapel] Error when print report Rapel, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayrollPensiun] end process <<<");
        return "success_print_report_payroll_rapel";
    }

    public String printReportCutiTahunan(){
        logger.info("[ReportAction.printReportPayrollThr] start process >>>");
        String payrollId = getId();
        if (payrollId != null) {
            Branch branch = new Branch();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ItPayrollEntity payroll = new ItPayrollEntity();
            try{
                PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
                payroll = payrollBo.getPayrollById(payrollId);
                BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                branch = branchBo.getBranchById(payroll.getBranchId(),"Y");
            }catch( HibernateException e){

            }
            String logo ="";
            if (payroll.getBranchId().equalsIgnoreCase("RS01")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS02")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS03")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
            }else{
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
            }
            String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
            reportParams.put("urlLogo", logo);
            reportParams.put("branchId", payroll.getBranchId());
            reportParams.put("branchName", branch.getBranchName());
            reportParams.put("alamatSurat", branch.getAlamatSurat()+","+stTanggal);
            reportParams.put("bulan", payroll.getBulan());
            reportParams.put("tahun", payroll.getTahun());
            reportParams.put("titleReport", "Slip Gaji");
            reportParams.put("payrollId", id);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollThr");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayrollThr] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportPayrollThr] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportPayrollThr] Error when print report Thr Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayrollThr] end process <<<");
        return "success_print_report_payroll_cutiTahunan";
    }
    public String printReportCutiPanjang(){
        logger.info("[ReportAction.printReportPayrollThr] start process >>>");
        String payrollId = getId();
        if (payrollId != null) {
            Branch branch = new Branch();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ItPayrollEntity payroll = new ItPayrollEntity();
            try{
                PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
                payroll = payrollBo.getPayrollById(payrollId);
                BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                branch = branchBo.getBranchById(payroll.getBranchId(),"Y");
            }catch( HibernateException e){

            }
            String logo ="";
            if (payroll.getBranchId().equalsIgnoreCase("RS01")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS02")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS03")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
            }else{
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
            }
            String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
            reportParams.put("urlLogo", logo);
            reportParams.put("branchId", payroll.getBranchId());
            reportParams.put("branchName", branch.getBranchName());
            reportParams.put("alamatSurat", branch.getAlamatSurat()+","+stTanggal);
            reportParams.put("bulan", payroll.getBulan());
            reportParams.put("tahun", payroll.getTahun());
            reportParams.put("titleReport", "Slip Gaji");
            reportParams.put("payrollId", id);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollThr");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayrollThr] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportPayrollThr] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportPayrollThr] Error when print report Thr Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayrollThr] end process <<<");
        return "success_print_report_payroll_cutiPanjang";
    }

    public String printReportThr(){
        logger.info("[ReportAction.printReportPayrollThr] start process >>>");
        String payrollId = getId();
        if (payrollId != null) {
            Branch branch = new Branch();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ItPayrollEntity payroll = new ItPayrollEntity();
            try{
                PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
                payroll = payrollBo.getPayrollById(payrollId);
                BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
                branch = branchBo.getBranchById(payroll.getBranchId(),"Y");
            }catch( HibernateException e){

            }
            String logo ="";
            if (payroll.getBranchId().equalsIgnoreCase("RS01")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS02")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
            }else if (payroll.getBranchId().equalsIgnoreCase("RS03")){
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
            }else{
                logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
            }
            String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
            reportParams.put("urlLogo", logo);
            reportParams.put("branchId", payroll.getBranchId());
            reportParams.put("branchName", branch.getBranchName());
            reportParams.put("alamatSurat", branch.getAlamatSurat()+","+stTanggal);
            reportParams.put("bulan", payroll.getBulan());
            reportParams.put("tahun", payroll.getTahun());
            reportParams.put("titleReport", "Slip Gaji");
            reportParams.put("payrollId", id);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollThr");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayrollThr] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportPayrollThr] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportPayrollThr] Error when print report Thr Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayrollThr] end process <<<");
        return "success_print_report_payroll_thr";
    }

    public String printReportPendidikan(){
        logger.info("[ReportAction.printReportPayrollPendidikan] start process >>>");
        String payrollId = getId();
        if (payrollId != null) {
            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("payrollId", payrollId);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollPendidikan");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.printReportPayrollThr] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.printReportPayrollThr] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[ReportAction.printReportPayrollPendidikan] Error when print report Pendidikan Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
        }

        logger.info("[ReportAction.printReportPayrollPendidikan] end process <<<");
        return "success_print_report_payroll_pendidikan";
    }

    public String searchCsv(){
        logger.info("[PayrollAction.search] start process >>>");

        Payroll searchPayroll = getPayroll();
        List<Payroll> listOfsearchPayroll = new ArrayList();

        try {
            listOfsearchPayroll = payrollBoProxy.getCsvPajak(searchPayroll);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "PayrollBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfCsvPajak");
        session.setAttribute("listOfCsvPajak", listOfsearchPayroll);

        logger.info("[PayrollAction.search] end process <<<");

        return "csv_pajak";
    }

    public String downloadXlsRekaptulasiPenghasilan() {
        logger.info("[payrollAction.downloadXlsRekaptulasiPenghasilan] start process >>>");
        String bulan = getBulan();
        String tahun = getTahun();
        String unit = getBranchId();
        String status = getStatusPegawai();
        String tipe = getTipe();
        String titleReport = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        List<Payroll> listData = new ArrayList<>();

        String periode = "Bulan " + CommonUtil.convertNumberToStringBulan(bulan) + " Tahun " + tahun;
        CellDetail cellDetail;
        RowData rowData;
        List listOfData = new ArrayList();
        List listOfCell;
        List listOfColumn = new ArrayList();
        if(tipe.equalsIgnoreCase("R")){
            listData = payrollBo.printReportPayrollPenghasilanKaryawanSys(bulan, tahun, unit, status);

            titleReport = "Rekapitulasi Penghasilan Karyawan";
            setContentDisposition("filename=\"" + "RekapuitulasiPenghasilan.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Status");
            listOfColumn.add("Golongan");
            listOfColumn.add("Penghasilan Kotor");
            listOfColumn.add("Potongan Dinas");
            listOfColumn.add("Jumlah Penghasilan");
            listOfColumn.add("Jumlah Potongann Lain");
            listOfColumn.add("Jumlah Penerima Bersih");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //status
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getStatusKeluarga());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Golongan
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getGolonganId());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getTotalA())){
                    //Jumlah Penghasilan Kotor
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTotalA());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah potongan dinas
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTotalB());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah Penghasilan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah Potongan Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTotalC());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah Penerima Bersih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTotalGajiBersih());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }else{
                    //Jumlah Penghasilan Kotor
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah potongan dinas
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTotalBNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah Penghasilan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah Potongan Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTotalCNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah Penerima Bersih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTotalGajiBersihNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }

                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("PK")){
            listData = payrollBo.printReportPayrollBulanSys(bulan, tahun, unit, status);
            titleReport = "Penghasilan Kotor";

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Gol");
            listOfColumn.add("Status");
            listOfColumn.add("Gaji Dasar");
            listOfColumn.add("Tunj Umk");
            listOfColumn.add("Tunj Struktural");
            listOfColumn.add("Tunj Peralihan");
            listOfColumn.add("Tunj Jab Struktural");
            listOfColumn.add("Strategis");
            listOfColumn.add("Kompensasi");
            listOfColumn.add("Tunjangan Transport");
            listOfColumn.add("Tunjangan Listrik Air");
            listOfColumn.add("Tunjangan Pengobatan");
            listOfColumn.add("Tunjangan Lain");
            listOfColumn.add("Tunjangan Perumahan");
            listOfColumn.add("Tunjangan Lembur");
            listOfColumn.add("Penghasilan Kotor");

            setContentDisposition("filename=\"" + "PenghasilanKaryawan.${documentFormat}\"");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();
                BigDecimal jumlahPph = new BigDecimal(0);

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Golongan
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGolonganId());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Status
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getStatusKeluarga());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);


                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);


                    //Tunj Umk
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunj Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunj Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunj Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganJabatanStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTunjanganStrategis());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Kompensasi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getKompensasi());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Transport
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganTransport());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganAirListrik());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Pengobatan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganPengobatan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganLain());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Perumahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTunjanganPerumahan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Lembur
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganLembur());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Kotor
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getTotalA());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }else{
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);


                    //Tunj Umk
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunj Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunj Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunj Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Kompensasi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getKompensasiNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Transport
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganTransportNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganAirListrikNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Pengobatan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganPengobatanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganLainNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Perumahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTunjanganPerumahanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Lembur
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganLemburNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Kotor
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("PD")){
            listData = payrollBo.printReportPayrollPotonganDinasSys(bulan, tahun, unit, status);
            titleReport = "Potongan Dinas";

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("PPh Gaji");
            listOfColumn.add("PPh Pengobatan");
            listOfColumn.add("Jumlah PPh");
            listOfColumn.add("Iuran Pensiun");
            listOfColumn.add("BPJS JHT");
            listOfColumn.add("BPJS Kesehatan");
            listOfColumn.add("BPJS Pensiun");
            listOfColumn.add("UM Lain Lain");
            listOfColumn.add("Kur Irp ");
            listOfColumn.add("Jumlah Potongan");

            setContentDisposition("filename=\"" + "PotonganDinas.${documentFormat}\"");

            double tmpNilai ;
            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getPphGaji())){
                    //Pph Gaji
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getPphGaji());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //PPh Pengobatan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getPphPengobatan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    BigDecimal jumlahPph = new BigDecimal(0);
                    //jumlahPph = data.getPphPengobatanNilai().add(data.getPphGajiNilai());
                    //Jumla Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTotalC());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);


                    //Iuran Pensiun
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getIuranPensiun());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //BPJS JHT
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getBpjsJht());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //BPJS Kesehatan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getIuranBpjsKesehatan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //BPJS Pensiun
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getBpjsPensiun());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //UM Lain lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getUangMukaLainnya());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Kur IRP
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getKekuranganBpjsTk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah Potongan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTotalB());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }else{
                    //Pph Gaji
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getPphGajiNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //PPh Pengobatan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getPphPengobatanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    BigDecimal jumlahPph = new BigDecimal(0);
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTotalCNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);


                    //Iuran Pensiun
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getIuranPensiunNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //BPJS JHT
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getBpjsJhtNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //BPJS Kesehatan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getIuranBpjsKesehatanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //BPJS Pensiun
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getBpjsPensiunNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //UM Lain lain
                    cellDetail = new CellDetail();
                    cellDetail.setValueCell(data.getUangMukaLainnyaNilai().doubleValue());
                    cellDetail.setCellID(10);
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Kur IRP
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getKekuranganBpjsTkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah Potongan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTotalBNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("PL")){
            listData = payrollBo.printReportPayrollPotonganLainLainSys(bulan, tahun, unit, status);
            titleReport = "Potongan Lain";
            setContentDisposition("filename=\"" + "PotonganLain.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Biaya Pengobatan");
            listOfColumn.add("Koperasi");
            listOfColumn.add("Dansos");
            listOfColumn.add("SP");
            listOfColumn.add("Bazis");
            listOfColumn.add("Bapor");
            listOfColumn.add("Lain Lain");
            listOfColumn.add("Zakat");
            listOfColumn.add("Jumlah");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getPengobatan())){
                    //Biaya Pengobatan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getPengobatan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Koperasi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getKoperasi());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Dansos
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getDansos());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SP
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getSP());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bazis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getBazis());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bapor
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getBapor());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Lain Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getLainLain());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Zakat
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getZakat());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTotalC());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }else{
                    //Biaya Pengobatan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getPengobatanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Koperasi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getKoperasiNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Dansos
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getDansosNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SP
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getSPNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bazis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getBazisNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bapor
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getBaporNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Lain Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getLainLainNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Zakat
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getZakatNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jumlah
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTotalCNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("pendidikan")){
            listData = payrollBo.printReportPayrollPendidikanSys(bulan, tahun, unit, status);
            titleReport = "Tunjangan Pendidikan";
            setContentDisposition("filename=\"" + "Tunjangan_Pendidikan.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Gol.");
            listOfColumn.add("Bln.");
            listOfColumn.add("Gaji Dasar");
            listOfColumn.add("T. Umk");
            listOfColumn.add("T. Struktural");
            listOfColumn.add("T. Jab. Struktural");
            listOfColumn.add("T. Strategis");
            listOfColumn.add("T. Peralihan");
            listOfColumn.add("T. Kompensasi");
            listOfColumn.add("T. Listrik Air");
            listOfColumn.add("T. Pph");
            listOfColumn.add("Pendidikan");
            listOfColumn.add("Biaya Pendidikan");
            listOfColumn.add("Pot. Pph");
            listOfColumn.add("Final Pendidikan");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Golongan
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGolonganId());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bulan
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getBulan());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganJabatanStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganStrategis());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Kompensasi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getKompensasi());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTunjanganAirListrik());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganPph());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTotalPendidikan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Biaya Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTotalGajiBersih());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getPphGaji());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Final Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTotalA());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }else{
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Kompensasi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getKompensasiNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTunjanganAirListrikNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganPphNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTotalPendidikanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Biaya Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTotalGajiBersihNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getPphGajiNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Final Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("thr")){
            listData = payrollBo.printReportPayrollThrSys(bulan, tahun, unit, status);
            titleReport = "Tunjangan THR";
            setContentDisposition("filename=\"" + "Tunjangan_THR.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Gol.");
            listOfColumn.add("Bln.");
            listOfColumn.add("Gaji Dasar");
            listOfColumn.add("T. Umk");
            listOfColumn.add("T. Struktural");
            listOfColumn.add("T. Jab. Struktural");
            listOfColumn.add("T. Strategis");
            listOfColumn.add("T. Peralihan");
            listOfColumn.add("T. Pph");
            listOfColumn.add("thr");
            listOfColumn.add("Biaya thr");
            listOfColumn.add("Pot. Pph");
            listOfColumn.add("Final thr");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Golongan
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGolonganId());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bulan
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getBulan());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganJabatanStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganStrategis());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);


                    //Tunjangan Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTunjanganPph());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //thr
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTotalThr());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Biaya thr
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTotalGajiBersih());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getPphGaji());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Final Thr
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTotalA());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }else{
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTunjanganPphNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Thr
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTotalThrNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Biaya thr
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTotalGajiBersihNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getPphGajiNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Final Thr
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("jasprod")){
            listData = payrollBo.printReportPayrollJasprodSys(bulan, tahun, unit, status);
            titleReport = "Tunjangan Jasprod";
            setContentDisposition("filename=\"" + "Tunjangan_Jasprod.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Gaji");
            listOfColumn.add("T. Umk");
            listOfColumn.add("T. Struktural");
            listOfColumn.add("T. Jab. Struktural");
            listOfColumn.add("T. Peralihan");
            listOfColumn.add("T. Strategis");
            listOfColumn.add("Gaji Bruto");
            listOfColumn.add("MK");
            listOfColumn.add("Gaji x Masa Kerja");
            listOfColumn.add("Fakt");
            listOfColumn.add("%");
            listOfColumn.add("SMK");
            listOfColumn.add("Perhitungan");
            listOfColumn.add("Gaji x Faktor Normal");
            listOfColumn.add("Tambahan");
            listOfColumn.add("Bruto");
            listOfColumn.add("Pajak");
            listOfColumn.add("Pot. Koperasi");
            listOfColumn.add("Pot Taliasih");
            listOfColumn.add("Pit Lain");
            listOfColumn.add("Netto");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTunjanganStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganJabatanStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrategis());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total jasprod bruto
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTotalA());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Masa Kerja
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getMasaKerja());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Masa kerja
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getGajiMasaKerja());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Faktor
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getFaktorKali());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //PersenSmk
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getPersenSmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Nilai SMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getNilaiSmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //perhitungan Nilai
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getPerhitungan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji X Faktor Normal
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getGajiXfaktorNormal());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tambahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTambahan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bruto jasprod
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getBrutoJasprod());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //PPH Jasprod
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getPphJasprod());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    // Pot Koperasi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getKoperasi());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    // Pot Taliasih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getDansos());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getLainLain());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Netto Jasprod
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getNettoJasprod());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }else{
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total jasprod bruto
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Masa Kerja
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getMasaKerja());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Masa kerja
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getGajiMasaKerjaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Faktor
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getFaktorKali());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    if(data.getPersenSmkNilai() != null){
                        //PersenSmk
                        cellDetail = new CellDetail();
                        cellDetail.setCellID(12);
                        cellDetail.setValueCell(data.getPersenSmkNilai().doubleValue());
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                    }else{
                        //PersenSmk
                        listOfCell.add(cellDetail);
                        cellDetail = new CellDetail();
                        cellDetail.setCellID(12);
                        cellDetail.setValueCell("");
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                    }

                    //Nilai SMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getNilaiSmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //perhitungan Nilai
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getPerhitunganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji X Faktor Normal
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getGajiXfaktorNormalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tambahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTambahanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bruto jasprod
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getBrutoJasprodNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //PPH Jasprod
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getPphJasprodNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    // Pot Koperasi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getKoperasiNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    // Pot Tali asih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getDansosNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    // Pot Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getLainLainNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Netto Jasprod
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(22);
                    cellDetail.setValueCell(data.getNettoJasprodNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("insentif")){
            listData = payrollBo.printReportPayrollInsentifSys(bulan, tahun, unit, status);
            titleReport = "Tunjangan Insentif";
            setContentDisposition("filename=\"" + "Tunjangan_Insentif.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Gaji");
            listOfColumn.add("T. Umk");
            listOfColumn.add("T. Struktural");
            listOfColumn.add("T. Jab. Struktural");
            listOfColumn.add("T. Peralihan");
            listOfColumn.add("T. Strategis");
            listOfColumn.add("Gaji Bruto");
            listOfColumn.add("MK");
            listOfColumn.add("Potongan Insentif");
            listOfColumn.add("Potongan Insentif Individu");
            listOfColumn.add("SMK Standart");
            listOfColumn.add("SMK Huruf");
            listOfColumn.add("SMK Angka");
            listOfColumn.add("Insentif Diterima");
            listOfColumn.add("PPH");
            listOfColumn.add("Pot Pph Lain");
            listOfColumn.add("Netto");


            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTunjanganStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganJabatanStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrategis());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total insentif bruto
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTotalA());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Masa Kerja
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getMasaKerja());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getPotonganInsentif());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan Insentif Individu
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getPotonganInsentifIndividu());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Standart
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getSmkStandart());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Huruf
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getSmkStandart());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Angka
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getSmkAngka());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //insentif Diterima
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getInsentifDiterima());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //PPH Gaji
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getPphGaji());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //PPH Gaji Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getPotPphLain());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getTotalInsentif());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                }else{
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total insentif bruto
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Masa Kerja
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getMasaKerja());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getPotonganInsentif());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan Insentif Individu
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getPotonganInsentifIndividuNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);


                    //SMK Standart
                    if(!data.getSmkStandart().equalsIgnoreCase("")){
                        cellDetail = new CellDetail();
                        cellDetail.setCellID(12);
                        cellDetail.setValueCell(Double.parseDouble(data.getSmkStandart()));
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                    }else{
                        cellDetail = new CellDetail();
                        cellDetail.setCellID(12);
                        cellDetail.setValueCell("");
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                    }

                    //SMK Huruf
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getSmkHuruf());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Angka
                    if(!data.getSmkAngka().equalsIgnoreCase("")){
                        cellDetail = new CellDetail();
                        cellDetail.setCellID(14);
                        cellDetail.setValueCell(Double.parseDouble(data.getSmkAngka()));
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                    }else{
                        cellDetail = new CellDetail();
                        cellDetail.setCellID(14);
                        cellDetail.setValueCell("");
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                    }

                    //insentif Diterima
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getInsentifDiterimaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //PPH Gaji
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getPphGajiNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //PPH Gaji Lain
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getPotPphLainNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getTotalInsentifNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("rapelBulan")){
            listData = payrollBo.printReportPayrollRapelSys(bulan, tahun, unit, status);
            titleReport = "Rapel";
            setContentDisposition("filename=\"" + "Rapel.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Gol Lama");
            listOfColumn.add("Gol Baru");

            listOfColumn.add("Gaji Dasar Lama");
            listOfColumn.add("T. Umk Lama");
            listOfColumn.add("T. Struktural Lama");
            listOfColumn.add("T. Jab. Struktural Lama");
            listOfColumn.add("T. Listrik Air Lama");
            listOfColumn.add("T. Strategis Lama");
            listOfColumn.add("T. Perumahan Lama");

            listOfColumn.add("Gaji Dasar Baru");
            listOfColumn.add("T. Umk Baru");
            listOfColumn.add("T. Struktural Baru");
            listOfColumn.add("T. Jab. Struktural Baru");
            listOfColumn.add("T. Listrik Air Baru");
            listOfColumn.add("T. Strategis Baru");
            listOfColumn.add("T. Perumahan Baru");

            listOfColumn.add("Gaji Dasar Selisih");
            listOfColumn.add("T. Umk Selisih");
            listOfColumn.add("T. Struktural Selisih");
            listOfColumn.add("T. Jab. Struktural Selisih");
            listOfColumn.add("T. Listrik Air Selisih");
            listOfColumn.add("T. Strategis Selisih");
            listOfColumn.add("T. Perumahan Selisih");

            listOfColumn.add("Rapel");
            listOfColumn.add("Jumlah Bulan");
            listOfColumn.add("Rapel X Bln");

            listOfColumn.add("Insentif");
            listOfColumn.add("Jubileum");
            listOfColumn.add("THR");
            listOfColumn.add("Pendidikan");
            listOfColumn.add("Lembur");

            listOfColumn.add("Total Rapel");
            listOfColumn.add("Pot Pph");
            listOfColumn.add("Pot Pph Pribadi");
            listOfColumn.add("Tunjangan Pph");
            listOfColumn.add("Total Rapel Bersih");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gol Lama
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGolonganIdLama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gol Baru
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getGolonganId());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    // Tunjangan Lama
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganAirListrikLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganStrategisLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Perumahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTunjanganPerumahanLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Baru
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getGajiGolonganBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganUmkBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganAirListrikBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTunjanganStrategisBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Perumahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganPerumahanBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Selisih
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getTunjanganStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getTunjanganJabatanStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(22);
                    cellDetail.setValueCell(data.getTunjanganAirListrik());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(23);
                    cellDetail.setValueCell(data.getTunjanganStrategis());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Perumahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(24);
                    cellDetail.setValueCell(data.getTunjanganPerumahan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Rapel
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(25);
                    cellDetail.setValueCell(data.getTotalRapel());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bulan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(26);
                    cellDetail.setValueCell(data.getBulan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Rapel X Bulan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(27);
                    cellDetail.setValueCell(data.getTotalB());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(28);
                    cellDetail.setValueCell(data.getTotalInsentif());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jubileum
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(29);
                    cellDetail.setValueCell(data.getTotalJubileum());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //THR
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(30);
                    cellDetail.setValueCell(data.getTotalThr());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(31);
                    cellDetail.setValueCell(data.getTotalPendidikan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Lembur
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(32);
                    cellDetail.setValueCell(data.getTotalLembur());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Final
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(33);
                    cellDetail.setValueCell(data.getTotalA());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(34);
                    cellDetail.setValueCell(data.getPphGaji());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot Pph Pribadi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(35);
                    cellDetail.setValueCell(data.getPotPph());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan PPH
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(36);
                    cellDetail.setValueCell(data.getTunjanganPph());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Rapel Bersih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(37);
                    cellDetail.setValueCell(data.getTotalGajiBersih());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }else{
                    // Tunjangan Lama
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganAirListrikLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganStrategisLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Perumahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTunjanganPerumahanLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Baru
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getGajiGolonganBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganUmkBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganAirListrikBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTunjanganStrategisBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Perumahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganPerumahanBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Selisih
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Jabatan Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(22);
                    cellDetail.setValueCell(data.getTunjanganAirListrikNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(23);
                    cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Perumahan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(24);
                    cellDetail.setValueCell(data.getTunjanganPerumahanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Rapel
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(25);
                    cellDetail.setValueCell(data.getTotalRapelNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bulan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(26);
                    cellDetail.setValueCell(data.getBulan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Rapel X Bulan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(27);
                    cellDetail.setValueCell(data.getTotalBNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(28);
                    cellDetail.setValueCell(data.getTotalInsentifNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jubileum
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(29);
                    cellDetail.setValueCell(data.getTotalJubileumNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //THR
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(30);
                    cellDetail.setValueCell(data.getTotalThrNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(31);
                    cellDetail.setValueCell(data.getTotalPendidikanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Lembur
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(32);
                    cellDetail.setValueCell(data.getTotalLemburNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Final
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(33);
                    cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot Pph
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(34);
                    cellDetail.setValueCell(data.getPphGajiNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot Pph Pribadi
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(35);
                    cellDetail.setValueCell(data.getPotPphNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan PPH
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(36);
                    cellDetail.setValueCell(data.getTunjanganPphNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Rapel Bersih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(37);
                    cellDetail.setValueCell(data.getTotalGajiBersihNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("rapelBulanDetail")) {
            PayrollRapel payrollRapel = payrollBo.rapelJumlahBulan(bulan, tahun, unit);
            listData = payrollBo.printReportPayrollRapelSys(bulan, tahun, unit, status);
            titleReport = "Rapel Detail";
            setContentDisposition("filename=\"" + "Rapel.${documentFormat}\"");

            String []strTanggal1 = CommonUtil.convertDateToString(payrollRapel.getTanggalAwal()).split("-");
            String []strTanggal2 = CommonUtil.convertDateToString(payrollRapel.getTanggalAkhir()).split("-");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");

            for (int a = Integer.parseInt(strTanggal1[1]); a <= Integer.parseInt(strTanggal2[1]); a++) {
                listOfColumn.add(a + " Gadas Baru");
                listOfColumn.add(a + " Gadas Lama");
                listOfColumn.add(a + " Gadas ");

                listOfColumn.add(a + " Umk Baru");
                listOfColumn.add(a + " Umk Lama");
                listOfColumn.add(a + " Umk");

                listOfColumn.add(a + " Peralihan Baru");
                listOfColumn.add(a + " Peralihan Lama");
                listOfColumn.add(a + " Peralihan");

                listOfColumn.add(a + " Struktural Baru");
                listOfColumn.add(a + " Struktural Lama");
                listOfColumn.add(a + " Struktural");

                listOfColumn.add(a + " Jab Struktural Baru");
                listOfColumn.add(a + " Jab Struktural Lama");
                listOfColumn.add(a + " Jab Struktural");

                listOfColumn.add(a + " Strategis Baru");
                listOfColumn.add(a + " Strategis Lama");
                listOfColumn.add(a + " Strategis");

                listOfColumn.add(a + " Air Listrik Baru");
                listOfColumn.add(a + " Air Listrik Lama");
                listOfColumn.add(a + " Air Listrik");

                listOfColumn.add(a + " Perumahan Baru");
                listOfColumn.add(a + " Perumahan Lama");
                listOfColumn.add(a + " Perumahan");
            }

            listOfColumn.add("Total Selisih Rapel");


            BigDecimal []jumlahGadasBaru = new BigDecimal[12];
            BigDecimal []jumlahGadasLama = new BigDecimal[12];
            BigDecimal []jumlahGadas = new BigDecimal[12];

            BigDecimal []jumlahUmkBaru = new BigDecimal[12];
            BigDecimal []jumlahUmkLama = new BigDecimal[12];
            BigDecimal []jumlahUmk = new BigDecimal[12];

            BigDecimal []jumlahPeralihanBaru = new BigDecimal[12];
            BigDecimal []jumlahPeralihanLama = new BigDecimal[12];
            BigDecimal []jumlahPeralihan = new BigDecimal[12];

            BigDecimal []jumlahStrukturalBaru = new BigDecimal[12];
            BigDecimal []jumlahStrukturalLama = new BigDecimal[12];
            BigDecimal []jumlahStruktural = new BigDecimal[12];

            BigDecimal []jumlahJabStrukturalBaru = new BigDecimal[12];
            BigDecimal []jumlahJabStrukturalLama = new BigDecimal[12];
            BigDecimal []jumlahJabStruktural = new BigDecimal[12];

            BigDecimal []jumlahStrategisBaru = new BigDecimal[12];
            BigDecimal []jumlahStrategisLama = new BigDecimal[12];
            BigDecimal []jumlahStrategis = new BigDecimal[12];

            BigDecimal []jumlahPerumahanBaru = new BigDecimal[12];
            BigDecimal []jumlahPerumahanLama = new BigDecimal[12];
            BigDecimal []jumlahPerumahan = new BigDecimal[12];

            BigDecimal []jumlahAirListrikBaru = new BigDecimal[12];
            BigDecimal []jumlahAirListrikLama = new BigDecimal[12];
            BigDecimal []jumlahAirListrik = new BigDecimal[12];

            BigDecimal jumlahRapel = new BigDecimal(0);
            BigDecimal totalRapel = new BigDecimal(0);

            BigDecimal []totalGadasBaru = new BigDecimal[12];
            BigDecimal []totalGadasLama = new BigDecimal[12];
            BigDecimal []totalGadas = new BigDecimal[12];

            BigDecimal []totalUmkBaru = new BigDecimal[12];
            BigDecimal []totalUmkLama = new BigDecimal[12];
            BigDecimal []totalUmk = new BigDecimal[12];

            BigDecimal []totalPeralihanBaru = new BigDecimal[12];
            BigDecimal []totalPeralihanLama = new BigDecimal[12];
            BigDecimal []totalPeralihan = new BigDecimal[12];

            BigDecimal []totalStrukturalBaru = new BigDecimal[12];
            BigDecimal []totalStrukturalLama = new BigDecimal[12];
            BigDecimal []totalStruktural = new BigDecimal[12];

            BigDecimal []totalJabStrukturalBaru = new BigDecimal[12];
            BigDecimal []totalJabStrukturalLama = new BigDecimal[12];
            BigDecimal []totalJabStruktural = new BigDecimal[12];

            BigDecimal []totalStrategisBaru = new BigDecimal[12];
            BigDecimal []totalStrategisLama = new BigDecimal[12];
            BigDecimal []totalStrategis = new BigDecimal[12];

            BigDecimal []totalAirListrikBaru = new BigDecimal[12];
            BigDecimal []totalAirListrikLama = new BigDecimal[12];
            BigDecimal []totalAirListrik = new BigDecimal[12];

            BigDecimal []totalPerumahanBaru = new BigDecimal[12];
            BigDecimal []totalPerumahanLama = new BigDecimal[12];
            BigDecimal []totalPerumahan = new BigDecimal[12];

            for (Payroll data : listData) {

                rowData = new RowData();
                listOfCell = new ArrayList();
                int index = 0;
                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(index);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                index++;

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(index);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                index++;

                for(int a = Integer.parseInt(strTanggal1[1]); a <= Integer.parseInt(strTanggal2[1]); a++){
                    if(!data.getPayrollId().equalsIgnoreCase("") && data.getPayrollId() != null){
                        String strBulan = "";
                        if(a < 10){
                            strBulan = "0" + a;
                        }
                        List<PayrollRapelDetail> payrollRapelDetails = payrollBo.printReportPayrollRapelDetailSys(strBulan, tahun, data.getPayrollId());

                        if(payrollRapelDetails.size() > 0){
                            for(PayrollRapelDetail payrollRapelDetail: payrollRapelDetails){
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getGajiGolonganBaruNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getGajiGolonganLamaNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getGajiGolonganNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;

                                if(jumlahGadasBaru[a] != null){
                                    jumlahGadasBaru[a] = jumlahGadasBaru[a].add(payrollRapelDetail.getGajiGolonganBaruNilai());
                                    totalGadasBaru[a] = totalGadasBaru[a].add(payrollRapelDetail.getGajiGolonganBaruNilai());
                                }else{
                                    jumlahGadasBaru[a] = payrollRapelDetail.getGajiGolonganBaruNilai();
                                    totalGadasBaru[a] = payrollRapelDetail.getGajiGolonganBaruNilai();
                                }
                                if(jumlahGadasLama[a] != null){
                                    jumlahGadasLama[a] = jumlahGadasLama[a].add(payrollRapelDetail.getGajiGolonganLamaNilai());
                                    totalGadasLama[a] = totalGadasLama[a].add(payrollRapelDetail.getGajiGolonganLamaNilai());
                                }else{
                                    jumlahGadasLama[a] = payrollRapelDetail.getGajiGolonganLamaNilai();
                                    totalGadasLama[a] = payrollRapelDetail.getGajiGolonganLamaNilai();
                                }
                                if(jumlahGadas[a] != null){
                                    jumlahGadas[a] = jumlahGadas[a].add(payrollRapelDetail.getGajiGolonganNilai());
                                    totalGadas[a] = totalGadas[a].add(payrollRapelDetail.getGajiGolonganNilai());
                                }else{
                                    jumlahGadas[a] = payrollRapelDetail.getGajiGolonganNilai();
                                    totalGadas[a] = payrollRapelDetail.getGajiGolonganNilai();
                                }

                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getUmkBaruNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getUmkLamaNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getUmkNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;

                                if(jumlahUmkBaru[a] != null){
                                    jumlahUmkBaru[a] = jumlahUmkBaru[a].add(payrollRapelDetail.getUmkBaruNilai());
                                    totalUmkBaru[a] = totalUmkBaru[a].add(payrollRapelDetail.getUmkBaruNilai());
                                }else{
                                    jumlahUmkBaru[a] = payrollRapelDetail.getUmkBaruNilai();
                                    totalUmkBaru[a] = payrollRapelDetail.getUmkBaruNilai();
                                }
                                if(jumlahUmkLama[a] != null){
                                    jumlahUmkLama[a] = jumlahUmkLama[a].add(payrollRapelDetail.getUmkLamaNilai());
                                    totalUmkLama[a] = totalUmkLama[a].add(payrollRapelDetail.getUmkLamaNilai());
                                }else{
                                    jumlahUmkLama[a] = payrollRapelDetail.getUmkLamaNilai();
                                    totalUmkLama[a] = payrollRapelDetail.getUmkLamaNilai();
                                }
                                if(jumlahUmk[a] != null){
                                    jumlahUmk[a] = jumlahUmk[a].add(payrollRapelDetail.getUmkNilai());
                                    totalUmk[a] = totalUmk[a].add(payrollRapelDetail.getUmkNilai());
                                }else{
                                    jumlahUmk[a] = payrollRapelDetail.getUmkNilai();
                                    totalUmk[a] = payrollRapelDetail.getUmkNilai();
                                }

                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getPeralihanBaruNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getPeralihanLamaNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getPeralihanNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;

                                if(jumlahPeralihanBaru[a] != null){
                                    jumlahPeralihanBaru[a] = jumlahPeralihanBaru[a].add(payrollRapelDetail.getPeralihanBaruNilai());
                                    totalPeralihanBaru[a] = totalPeralihanBaru[a].add(payrollRapelDetail.getPeralihanBaruNilai());
                                }else{
                                    jumlahPeralihanBaru[a] = payrollRapelDetail.getPeralihanBaruNilai();
                                    totalPeralihanBaru[a] = payrollRapelDetail.getPeralihanBaruNilai();
                                }
                                if(jumlahPeralihanLama[a] != null){
                                    jumlahPeralihanLama[a] = jumlahPeralihanLama[a].add(payrollRapelDetail.getPeralihanLamaNilai());
                                    totalPeralihanLama[a] = totalPeralihanLama[a].add(payrollRapelDetail.getPeralihanLamaNilai());
                                }else{
                                    jumlahPeralihanLama[a] = payrollRapelDetail.getPeralihanLamaNilai();
                                    totalPeralihanLama[a] = payrollRapelDetail.getPeralihanLamaNilai();
                                }
                                if(jumlahPeralihan[a] != null){
                                    jumlahPeralihan[a] = jumlahPeralihan[a].add(payrollRapelDetail.getPeralihanNilai());
                                    totalPeralihan[a] = totalPeralihan[a].add(payrollRapelDetail.getPeralihanNilai());
                                }else{
                                    jumlahPeralihan[a] = payrollRapelDetail.getPeralihanNilai();
                                    totalPeralihan[a] = payrollRapelDetail.getPeralihanNilai();
                                }

                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getStrukturalBaruNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getStrukturalLamaNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getStrukturalNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;

                                if(jumlahStrukturalBaru[a] != null){
                                    jumlahStrukturalBaru[a] = jumlahStrukturalBaru[a].add(payrollRapelDetail.getStrukturalBaruNilai());
                                    totalStrukturalBaru[a] = totalStrukturalBaru[a].add(payrollRapelDetail.getStrukturalBaruNilai());
                                }else{
                                    jumlahStrukturalBaru[a] = payrollRapelDetail.getStrukturalBaruNilai();
                                    totalStrukturalBaru[a] = payrollRapelDetail.getStrukturalBaruNilai();
                                }
                                if(jumlahStrukturalLama[a] != null){
                                    jumlahStrukturalLama[a] = jumlahStrukturalLama[a].add(payrollRapelDetail.getStrukturalLamaNilai());
                                    totalStrukturalLama[a] = totalStrukturalLama[a].add(payrollRapelDetail.getStrukturalLamaNilai());
                                }else{
                                    jumlahStrukturalLama[a] = payrollRapelDetail.getStrukturalLamaNilai();
                                    totalStrukturalLama[a] = payrollRapelDetail.getStrukturalLamaNilai();
                                }
                                if(jumlahStruktural[a] != null){
                                    jumlahStruktural[a] = jumlahStruktural[a].add(payrollRapelDetail.getStrukturalNilai());
                                    totalStruktural[a] = totalStruktural[a].add(payrollRapelDetail.getStrukturalNilai());
                                }else{
                                    jumlahStruktural[a] = payrollRapelDetail.getStrukturalNilai();
                                    totalStruktural[a] = payrollRapelDetail.getStrukturalNilai();
                                }

                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getJabStrukturalBaruNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getJabStrukturalLamaNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getJabStrukturalNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                if(jumlahJabStrukturalBaru[a] != null){
                                    jumlahJabStrukturalBaru[a] = jumlahJabStrukturalBaru[a].add(payrollRapelDetail.getJabStrukturalBaruNilai());
                                    totalJabStrukturalBaru[a] = totalJabStrukturalBaru[a].add(payrollRapelDetail.getJabStrukturalBaruNilai());
                                }else{
                                    jumlahJabStrukturalBaru[a] = payrollRapelDetail.getJabStrukturalBaruNilai();
                                    totalJabStrukturalBaru[a] = payrollRapelDetail.getJabStrukturalBaruNilai();
                                }
                                if(jumlahJabStrukturalLama[a] != null){
                                    jumlahJabStrukturalLama[a] = jumlahJabStrukturalLama[a].add(payrollRapelDetail.getJabStrukturalLamaNilai());
                                    totalJabStrukturalLama[a] = totalJabStrukturalLama[a].add(payrollRapelDetail.getJabStrukturalLamaNilai());
                                }else{
                                    jumlahJabStrukturalLama[a] = payrollRapelDetail.getJabStrukturalLamaNilai();
                                    totalJabStrukturalLama[a] = payrollRapelDetail.getJabStrukturalLamaNilai();
                                }
                                if(jumlahJabStruktural[a] != null){
                                    jumlahJabStruktural[a] = jumlahJabStruktural[a].add(payrollRapelDetail.getJabStrukturalNilai());
                                    totalJabStruktural[a] = totalJabStruktural[a].add(payrollRapelDetail.getJabStrukturalNilai());
                                }else{
                                    jumlahJabStruktural[a] = payrollRapelDetail.getJabStrukturalNilai();
                                    totalJabStruktural[a] = payrollRapelDetail.getJabStrukturalNilai();
                                }

                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getStrategisBaruNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getStrategisLamaNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getStrategisNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                if(jumlahStrategisBaru[a] != null){
                                    jumlahStrategisBaru[a] = jumlahStrategisBaru[a].add(payrollRapelDetail.getStrategisBaruNilai());
                                    totalStrategisBaru[a] = totalStrategisBaru[a].add(payrollRapelDetail.getStrategisBaruNilai());
                                }else{
                                    jumlahStrategisBaru[a] = payrollRapelDetail.getStrategisBaruNilai();
                                    totalStrategisBaru[a] = payrollRapelDetail.getStrategisBaruNilai();
                                }
                                if(jumlahStrategisLama[a] != null){
                                    jumlahStrategisLama[a] = jumlahStrategisLama[a].add(payrollRapelDetail.getStrategisLamaNilai());
                                    totalStrategisLama[a] = totalStrategisLama[a].add(payrollRapelDetail.getStrategisLamaNilai());
                                }else{
                                    jumlahStrategisLama[a] = payrollRapelDetail.getStrategisLamaNilai();
                                    totalStrategisLama[a] = payrollRapelDetail.getStrategisLamaNilai();
                                }
                                if(jumlahStrategis[a] != null){
                                    jumlahStrategis[a] = jumlahStrategis[a].add(payrollRapelDetail.getStrategisNilai());
                                    totalStrategis[a] = totalStrategis[a].add(payrollRapelDetail.getStrategisNilai());
                                }else{
                                    jumlahStrategis[a] = payrollRapelDetail.getStrategisNilai();
                                    totalStrategis[a] = payrollRapelDetail.getStrategisNilai();
                                }

                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getAirListrikBaruNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getAirListrikLamaNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getAirListrikNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                if(jumlahAirListrikBaru[a] != null){
                                    jumlahAirListrikBaru[a] = jumlahAirListrikBaru[a].add(payrollRapelDetail.getAirListrikBaruNilai());
                                    totalAirListrikBaru[a] = totalAirListrikBaru[a].add(payrollRapelDetail.getAirListrikBaruNilai());
                                }else{
                                    jumlahAirListrikBaru[a] = payrollRapelDetail.getAirListrikBaruNilai();
                                    totalAirListrikBaru[a] = payrollRapelDetail.getAirListrikBaruNilai();
                                }
                                if(jumlahAirListrikLama[a] != null){
                                    jumlahAirListrikLama[a] = jumlahAirListrikLama[a].add(payrollRapelDetail.getAirListrikLamaNilai());
                                    totalAirListrikLama[a] = totalAirListrikLama[a].add(payrollRapelDetail.getAirListrikLamaNilai());
                                }else{
                                    jumlahAirListrikLama[a] = payrollRapelDetail.getAirListrikLamaNilai();
                                    totalAirListrikLama[a] = payrollRapelDetail.getAirListrikLamaNilai();
                                }
                                if(jumlahAirListrik[a] != null){
                                    jumlahAirListrik[a] = jumlahAirListrik[a].add(payrollRapelDetail.getAirListrikNilai());
                                    totalAirListrik[a] = totalAirListrik[a].add(payrollRapelDetail.getAirListrikNilai());
                                }else{
                                    jumlahAirListrik[a] = payrollRapelDetail.getAirListrikNilai();
                                    totalAirListrik[a] = payrollRapelDetail.getAirListrikNilai();
                                }

                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getPerumahanBaruNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getPerumahanLamaNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;
                                cellDetail = new CellDetail();
                                cellDetail.setCellID(index);
                                cellDetail.setValueCell(payrollRapelDetail.getPerumahanNilai().doubleValue());
                                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                                listOfCell.add(cellDetail);
                                index++;

                                if(jumlahPerumahanBaru[a] != null){
                                    jumlahPerumahanBaru[a] = jumlahPerumahanBaru[a].add(payrollRapelDetail.getPerumahanBaruNilai());
                                    totalPerumahanBaru[a] = totalPerumahanBaru[a].add(payrollRapelDetail.getPerumahanBaruNilai());
                                }else{
                                    jumlahPerumahanBaru[a] = payrollRapelDetail.getPerumahanBaruNilai();
                                    totalPerumahanBaru[a] = payrollRapelDetail.getPerumahanBaruNilai();
                                }
                                if(jumlahPerumahanLama[a] != null){
                                    jumlahPerumahanLama[a] = jumlahPerumahanLama[a].add(payrollRapelDetail.getPerumahanLamaNilai());
                                    totalPerumahanLama[a] = totalPerumahanLama[a].add(payrollRapelDetail.getPerumahanLamaNilai());
                                }else{
                                    jumlahPerumahanLama[a] = payrollRapelDetail.getPerumahanLamaNilai();
                                    totalPerumahanLama[a] = payrollRapelDetail.getPerumahanLamaNilai();
                                }
                                if(jumlahPerumahan[a] != null){
                                    jumlahPerumahan[a] = jumlahPerumahan[a].add(payrollRapelDetail.getPerumahanNilai());
                                    totalPerumahan[a] = totalPerumahan[a].add(payrollRapelDetail.getPerumahanNilai());
                                }else{
                                    jumlahPerumahan[a] = payrollRapelDetail.getPerumahanNilai();
                                    totalPerumahan[a] = payrollRapelDetail.getPerumahanNilai();
                                }
                            }
                        }else{
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(BigDecimal.valueOf(0).doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                        }
                    }else{
                        if(data.getNip().equalsIgnoreCase("NIP")){
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Gadas Baru");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Gadas Lama");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Gadas");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Umk Baru");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Umk Lama");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Umk");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Peralihan Baru");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Peralihan Lama");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Peralihan");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Struktural Baru");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Struktural Lama");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Struktural");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Jab Struktural Baru");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Jab Struktural Lama");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Jab Struktural");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Strategis Baru");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Strategis Lama");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Strategis");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Air Listrik Baru");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Air Listrik Lama");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Air Listrik");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Perumahan Baru");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Perumahan Lama");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(a + " Perumahan");
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                        }else if(data.getNama().equalsIgnoreCase("Total")){
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahGadasBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahGadasLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahGadas[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahUmkBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahUmkLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahUmk[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahPeralihanBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahPeralihanLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahPeralihan[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahStrukturalBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahStrukturalLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahStruktural[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahJabStrukturalBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahJabStrukturalLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahJabStruktural[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahStrategisBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahStrategisLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahStrategis[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahAirListrikBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahAirListrikLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahAirListrik[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahPerumahanBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahPerumahanLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(jumlahPerumahan[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            jumlahGadasBaru[a] = BigDecimal.valueOf(0);
                            jumlahGadasLama[a] = BigDecimal.valueOf(0);
                            jumlahGadas[a] = BigDecimal.valueOf(0);

                            jumlahUmkBaru[a] = BigDecimal.valueOf(0);
                            jumlahUmkLama[a] = BigDecimal.valueOf(0);
                            jumlahUmk[a] = BigDecimal.valueOf(0);

                            jumlahPeralihanBaru[a] = BigDecimal.valueOf(0);
                            jumlahPeralihanLama[a] = BigDecimal.valueOf(0);
                            jumlahPeralihan[a] = BigDecimal.valueOf(0);

                            jumlahStrukturalBaru[a] = BigDecimal.valueOf(0);
                            jumlahStrukturalLama[a] = BigDecimal.valueOf(0);
                            jumlahStruktural[a] = BigDecimal.valueOf(0);

                            jumlahJabStrukturalBaru[a] = BigDecimal.valueOf(0);
                            jumlahJabStrukturalLama[a] = BigDecimal.valueOf(0);
                            jumlahJabStruktural[a] = BigDecimal.valueOf(0);

                            jumlahStrategisBaru[a] = BigDecimal.valueOf(0);
                            jumlahStrategisLama[a] = BigDecimal.valueOf(0);
                            jumlahStrategis[a] = BigDecimal.valueOf(0);

                            jumlahAirListrikBaru[a] = BigDecimal.valueOf(0);
                            jumlahAirListrikLama[a] = BigDecimal.valueOf(0);
                            jumlahAirListrik[a] = BigDecimal.valueOf(0);

                            jumlahPerumahanBaru[a] = BigDecimal.valueOf(0);
                            jumlahPerumahanLama[a] = BigDecimal.valueOf(0);
                            jumlahPerumahan[a] = BigDecimal.valueOf(0);
                        }else if(data.getNama().equalsIgnoreCase("Total Keseluruhan")){
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalGadasBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalGadasLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalGadas[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalUmkBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalUmkLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalUmk[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalPeralihanBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalPeralihanLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalPeralihan[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalStrukturalBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalStrukturalLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalStruktural[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalJabStrukturalBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalJabStrukturalLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalJabStruktural[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalStrategisBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalStrategisLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalStrategis[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalAirListrikBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalAirListrikLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalAirListrik[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalPerumahanBaru[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalPerumahanLama[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;
                            cellDetail = new CellDetail();
                            cellDetail.setCellID(index);
                            cellDetail.setValueCell(totalPerumahan[a].doubleValue());
                            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                            listOfCell.add(cellDetail);
                            index++;

                            jumlahGadasBaru[a] = BigDecimal.valueOf(0);
                            jumlahGadasLama[a] = BigDecimal.valueOf(0);
                            jumlahGadas[a] = BigDecimal.valueOf(0);

                            jumlahUmkBaru[a] = BigDecimal.valueOf(0);
                            jumlahUmkLama[a] = BigDecimal.valueOf(0);
                            jumlahUmk[a] = BigDecimal.valueOf(0);

                            jumlahPeralihanBaru[a] = BigDecimal.valueOf(0);
                            jumlahPeralihanLama[a] = BigDecimal.valueOf(0);
                            jumlahPeralihan[a] = BigDecimal.valueOf(0);

                            jumlahStrukturalBaru[a] = BigDecimal.valueOf(0);
                            jumlahStrukturalLama[a] = BigDecimal.valueOf(0);
                            jumlahStruktural[a] = BigDecimal.valueOf(0);

                            jumlahJabStrukturalBaru[a] = BigDecimal.valueOf(0);
                            jumlahJabStrukturalLama[a] = BigDecimal.valueOf(0);
                            jumlahJabStruktural[a] = BigDecimal.valueOf(0);

                            jumlahStrategisBaru[a] = BigDecimal.valueOf(0);
                            jumlahStrategisLama[a] = BigDecimal.valueOf(0);
                            jumlahStrategis[a] = BigDecimal.valueOf(0);

                            jumlahAirListrikBaru[a] = BigDecimal.valueOf(0);
                            jumlahAirListrikLama[a] = BigDecimal.valueOf(0);
                            jumlahAirListrik[a] = BigDecimal.valueOf(0);

                            jumlahPerumahanBaru[a] = BigDecimal.valueOf(0);
                            jumlahPerumahanLama[a] = BigDecimal.valueOf(0);
                            jumlahPerumahan[a] = BigDecimal.valueOf(0);
                        }
                    }
                    /*cellDetail = new CellDetail();
                    cellDetail.setCellID(index);
                    cellDetail.setValueCell(" Total Selisih Rapel");
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                    index++;*/
                }

                if(data.getNama() != null && !data.getNama().equalsIgnoreCase("")){
                    if(data.getNip().equalsIgnoreCase("NIP")){
                        cellDetail = new CellDetail();
                        cellDetail.setCellID(index);
                        cellDetail.setValueCell(" Total Rapel");
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                        index++;
                    }else if(data.getNama().equalsIgnoreCase("Total")){
                        cellDetail = new CellDetail();
                        cellDetail.setCellID(index);
                        cellDetail.setValueCell(jumlahRapel.doubleValue());
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                        index++;
                        jumlahRapel = BigDecimal.valueOf(0);
                    }else if(data.getNama().equalsIgnoreCase("Total Keseluruhan")){
                        cellDetail = new CellDetail();
                        cellDetail.setCellID(index);
                        cellDetail.setValueCell(totalRapel.doubleValue());
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                        index++;
                    }else{
                        jumlahRapel = jumlahRapel.add(data.getTotalBNilai());
                        totalRapel = totalRapel.add(data.getTotalBNilai());

                        cellDetail = new CellDetail();
                        cellDetail.setCellID(index);
                        cellDetail.setValueCell(data.getTotalBNilai().doubleValue());
                        cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                        listOfCell.add(cellDetail);
                        index++;
                    }
                }

                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }


        }else if(tipe.equalsIgnoreCase("rapelLembur")){
            listData = payrollBo.printReportPayrollRapelLemburSys(bulan, tahun, unit, status);
            titleReport = "Rapel";
            setContentDisposition("filename=\"" + "Rapel Lembur.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Gaji Dasar Baru");
            listOfColumn.add("T. Umk Baru");
            listOfColumn.add("T. Peralihan Baru");

            listOfColumn.add("Jam Lembur");
            listOfColumn.add("Faktor Pengali");
            listOfColumn.add("Biaya Lembur Lama");
            listOfColumn.add("Biaya Lembur Baru");
            listOfColumn.add("Selisih Biaya Lembur");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jam Lembur
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getStrJamLembur());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Faktor Pengali
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getFaktorKali());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Biaya Lembur Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getStrBiayaLemburLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Biaya Lembur Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getStrBiayaLemburBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Selisih Lembur
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getStrselisihBiayaLemburBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                }else{
                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(2);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(3);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Jam Lembur
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getJamLembur().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Faktor Pengali
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getFaktorKali());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Biaya Lembur Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getBiayaLemburLama().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Biaya Lembur Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getBiayaLemburBaru().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Selisih Lembur
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getSelisihBiayaLemburBaru().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("rapelThr")){
            listData = payrollBo.printReportPayrollRapelThrSys(bulan, tahun, unit, status);
            titleReport = "Rapel";
            setContentDisposition("filename=\"" + "Rapel THR.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Golongan Lama");
            listOfColumn.add("Golongan Baru");

            listOfColumn.add("Gaji Dasar Lama");
            listOfColumn.add("T. Umk Lama");
            listOfColumn.add("T. Peralihan Lama");
            listOfColumn.add("T. Struktural Lama");
            listOfColumn.add("T. Jab. Struktural Lama");
            listOfColumn.add("T. Strategis Lama");

            listOfColumn.add("Gaji Dasar Baru");
            listOfColumn.add("T. Umk Baru");
            listOfColumn.add("T. Peralihan Baru");
            listOfColumn.add("T. Struktural Baru");
            listOfColumn.add("T. Jab. Struktural Baru");
            listOfColumn.add("T. Strategis Baru");

            listOfColumn.add("Gaji Dasar Selisih");
            listOfColumn.add("T. Umk Selisih");
            listOfColumn.add("T. Peralihan Selisih");
            listOfColumn.add("T. Struktural Selisih");
            listOfColumn.add("T. Jab. Struktural Selisih");
            listOfColumn.add("T. Strategis Selisih");

            listOfColumn.add("Rapel Thr");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Golongan Lama
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGolonganIdLama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Golongan Baru
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getGolonganId());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    //Gaji Dasar Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganPeralihanLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganStrategisLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getGajiGolonganBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTunjanganUmkBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganPeralihanBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganStrategisBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getTunjanganStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getTunjanganJabatanStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getTunjanganStrategis());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Rapel THR Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(22);
                    cellDetail.setValueCell(data.getTotalThr());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                }else{
                    //Gaji Dasar Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganPeralihanLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganStrategisLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getGajiGolonganBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTunjanganUmkBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganPeralihanBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganStrategisBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Rapel THR Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getTotalThrNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("rapelJubileum")){
            listData = payrollBo.printReportPayrollRapelJubileumSys(bulan, tahun, unit, status);
            titleReport = "Rapel";
            setContentDisposition("filename=\"" + "Rapel Jubileum.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Nama");
            listOfColumn.add("Golongan Lama");
            listOfColumn.add("Golongan Baru");
            listOfColumn.add("Tanggal Jubileum");

            listOfColumn.add("Gaji Dasar Lama");
            listOfColumn.add("T. Umk Lama");
            listOfColumn.add("T. Struktural Lama");
            listOfColumn.add("T. Jab. Struktural Lama");
            listOfColumn.add("T. Peralihan Lama");

            listOfColumn.add("Gaji Dasar Baru");
            listOfColumn.add("T. Umk Baru");
            listOfColumn.add("T. Struktural Baru");
            listOfColumn.add("T. Jab. Struktural Baru");
            listOfColumn.add("T. Peralihan Baru");

            listOfColumn.add("Gaji Dasar Selisih");
            listOfColumn.add("T. Umk Selisih");
            listOfColumn.add("T. Struktural Selisih");
            listOfColumn.add("T. Jab. Struktural Selisih");
            listOfColumn.add("T. Peralihan Selisih");

            listOfColumn.add("Total Selisih");
            listOfColumn.add("Total Rapel Jubileum");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nama
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Golongan Lama
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGolonganNameLama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Golongan Baru
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getGolonganName());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tanggal Jubileum
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);
                cellDetail.setValueCell(data.getTanggalJubileum());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    //Gaji Dasar Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getGajiGolonganLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganUmkLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganStrategisLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getGajiGolonganBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTunjanganUmkBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganPeralihanBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getTunjanganJabatanStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Selisih Jubileum
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getTotalA());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Jubileum
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getTotalJubileum());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                }else{
                    //Gaji Dasar Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getGajiGolonganLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganUmkLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganPeralihanLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getGajiGolonganBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTunjanganUmkBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganPeralihanBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan Selisih
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Selisih Jubileum
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Jubileum
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getTotalJubileumNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("rapelPendidikan")){
            listData = payrollBo.printReportPayrollRapelPendidikanSys(bulan, tahun, unit, status);
            titleReport = "Rapel";
            setContentDisposition("filename=\"" + "Rapel Pendidikan.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Golongan Lama");
            listOfColumn.add("Golongan Baru");

            listOfColumn.add("Gaji Dasar Lama");
            listOfColumn.add("T. Umk Lama");
            listOfColumn.add("T. Peralihan Lama");
            listOfColumn.add("T. Struktural Lama");
            listOfColumn.add("T. Jab. Struktural Lama");
            listOfColumn.add("T. Strategis Lama");
            listOfColumn.add("T. Listrik Air Lama");

            listOfColumn.add("Gaji Dasar Baru");
            listOfColumn.add("T. Peralihan Baru");
            listOfColumn.add("T. Umk Baru");
            listOfColumn.add("T. Struktural Baru");
            listOfColumn.add("T. Jab. Struktural Baru");
            listOfColumn.add("T. Strategis Baru");
            listOfColumn.add("T. Listrik Air Baru");

            listOfColumn.add("Gaji Dasar Selisih");
            listOfColumn.add("T. Umk Selisih");
            listOfColumn.add("T. Peralihan Selisih");
            listOfColumn.add("T. Struktural Selisih");
            listOfColumn.add("T. Jab. Struktural Selisih");
            listOfColumn.add("T. Strategis Selisih");
            listOfColumn.add("T. Listrik Air");

            listOfColumn.add("Rapel Pendidikan");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Lama
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGolonganIdLama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Baru
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getGolonganId());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    //Gaji Dasar Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganPeralihanLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganStrategisLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Listrik Air Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTunjanganAirListrikLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getGajiGolonganBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganUmkBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganPeralihanBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTunjanganStrategisBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Listrik Air Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganAirListrikBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getGajiGolongan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getTunjanganUmk());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getTunjanganStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(22);
                    cellDetail.setValueCell(data.getTunjanganJabatanStruktural());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(23);
                    cellDetail.setValueCell(data.getTunjanganStrategis());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(24);
                    cellDetail.setValueCell(data.getTunjanganAirListrik());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Rapel Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(25);
                    cellDetail.setValueCell(data.getTotalPendidikan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                }else{
                    //Gaji Dasar Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganPeralihanLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganStrategisLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Listrik Air Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getTunjanganAirListrikLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getGajiGolonganBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganUmkBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganPeralihanBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getTunjanganStrategisBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Listrik Air Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTunjanganAirListrikBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(22);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(23);
                    cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Listrik Air
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(24);
                    cellDetail.setValueCell(data.getTunjanganAirListrikNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Rapel Pendidikan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(25);
                    cellDetail.setValueCell(data.getTotalPendidikanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("rapelInsentif")){
            listData = payrollBo.printReportPayrollRapelInsentifSys(bulan, tahun, unit, status);
            titleReport = "Rapel Insentif";
            setContentDisposition("filename=\"" + "Rapel Insentif.${documentFormat}\"");

            listOfColumn.add("Nip");
            listOfColumn.add("Bagian");
            listOfColumn.add("Golongan Lama");
            listOfColumn.add("Golongan Baru");

            listOfColumn.add("Gaji Dasar Lama");
            listOfColumn.add("T. Umk Lama");
            listOfColumn.add("T. Struktural Lama");
            listOfColumn.add("T. Jab. Struktural Lama");
            listOfColumn.add("T. Strategis Lama");
            listOfColumn.add("T. Peralihan Lama");

            listOfColumn.add("Gaji Dasar Baru");
            listOfColumn.add("T. Umk Baru");
            listOfColumn.add("T. Struktural Baru");
            listOfColumn.add("T. Jab. Struktural Baru");
            listOfColumn.add("T. Strategis Baru");
            listOfColumn.add("T. Peralihan Baru");

            listOfColumn.add("Bruto Lama");
            listOfColumn.add("Bruto Baru");
            listOfColumn.add("MK");
            listOfColumn.add("Pot. Insentif");
            listOfColumn.add("Smk Standart");
            listOfColumn.add("Smk Huruf");
            listOfColumn.add("Smk Angka");

            listOfColumn.add("Pot. Insentif Individu Lama");
            listOfColumn.add("Pot. Insentif Individu Baru");

            listOfColumn.add("Insentif Diterima Lama");
            listOfColumn.add("Insentif Diterima Baru");

            listOfColumn.add("Rapel Insentif");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Lama
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGolonganIdLama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Baru
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getGolonganId());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                if(data.getNip().equalsIgnoreCase("NIP") || "".equalsIgnoreCase(data.getGajiGolongan())){
                    //Gaji Dasar Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganStrategisLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganPeralihanLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getGajiGolonganBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTunjanganUmkBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganStrategisBaru());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganPeralihan());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bruto Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getBrutoInsentifLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bruto Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTotalA());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Masa kerja(MK)
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getMasaKerja());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot. Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getPotonganInsentif());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Standart
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    cellDetail.setValueCell(data.getSmkStandart());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Huruf
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getSmkHuruf());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Angka
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(22);
                    cellDetail.setValueCell(data.getSmkAngka());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan insentif individu lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(23);
                    cellDetail.setValueCell(data.getPotonganInsentifIndividuLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Potongan insentif individu Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(24);
                    cellDetail.setValueCell(data.getPotonganInsentifIndividu());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Insentif Diterima Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(25);
                    cellDetail.setValueCell(data.getInsentifDiterimaLama());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Insentif Diterima Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(26);
                    cellDetail.setValueCell(data.getInsentifDiterima());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Rapel Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(27);
                    cellDetail.setValueCell(data.getTotalInsentif());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }else{
                    //Gaji Dasar Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(4);
                    cellDetail.setValueCell(data.getGajiGolonganLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(5);
                    cellDetail.setValueCell(data.getTunjanganUmkLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(6);
                    cellDetail.setValueCell(data.getTunjanganStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(7);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(8);
                    cellDetail.setValueCell(data.getTunjanganStrategisLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(9);
                    cellDetail.setValueCell(data.getTunjanganPeralihanLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Gaji Dasar Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(10);
                    cellDetail.setValueCell(data.getGajiGolonganBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Tunjangan UMK Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(11);
                    cellDetail.setValueCell(data.getTunjanganUmkBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(12);
                    cellDetail.setValueCell(data.getTunjanganStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Jab. Struktural Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(13);
                    cellDetail.setValueCell(data.getTunjanganJabatanStrukturalBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Strategis Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(14);
                    cellDetail.setValueCell(data.getTunjanganStrategisBaruNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //T. Peralihan Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(15);
                    cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bruto Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(16);
                    cellDetail.setValueCell(data.getBrutoInsentifLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Bruto Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(17);
                    cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Masa Kerja (MK)
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(18);
                    cellDetail.setValueCell(data.getMasaKerja());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot. Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(19);
                    cellDetail.setValueCell(data.getPotonganInsentif());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Standart
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(20);
                    if(data.getSmkStandartNilai() != null){
                        cellDetail.setValueCell(data.getSmkStandartNilai().doubleValue());
                    }else{
                        cellDetail.setValueCell(data.getSmkStandart());
                    }
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Huruf
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(21);
                    cellDetail.setValueCell(data.getSmkHuruf());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //SMK Angka
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(22);
                    if(data.getSmkStandartNilai() != null){
                        cellDetail.setValueCell(data.getSmkAngkaNilai().doubleValue());
                    }else{
                        cellDetail.setValueCell(data.getSmkAngka());
                    }
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot. Insentif Individu Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(23);
                    cellDetail.setValueCell(data.getPotonganInsentifIndividuLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot. Insentif Individu Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(24);
                    cellDetail.setValueCell(data.getPotonganInsentifIndividuNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot. Insentif Diterima Lama
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(25);
                    cellDetail.setValueCell(data.getInsentifDiterimaLamaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Pot. Insentif Diterima Baru
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(26);
                    cellDetail.setValueCell(data.getInsentifDiterimaNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);

                    //Total Rapel Insentif
                    cellDetail = new CellDetail();
                    cellDetail.setCellID(27);
                    cellDetail.setValueCell(data.getTotalInsentifNilai().doubleValue());
                    cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                    listOfCell.add(cellDetail);
                }


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }

        HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, periode, listOfColumn, listOfData, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            wb.write(baos);
        } catch (IOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollAction.downloadXlsRekaptulasiPenghasilan");
            } catch (GeneralBOException e1) {
                logger.error("[payrollAction.downloadXlsRekaptulasiPenghasilan] Error when downloading,", e1);
            }
            logger.error("[payrollAction.downloadXlsRekaptulasiPenghasilan] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
            return ERROR;
        }

        setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
        logger.info("[payrollAction.downloadXlsRekaptulasiPenghasilan] end process <<<");
        return "downloadXlsRekaptulasiPenghasilan";
    }

    public String searchReportDanaPensiun() {
        logger.info("[payrollAction.searchReportDanaPensiun] start process >>>");
        Payroll payroll = getPayroll();

        String titleReport = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        List<Payroll> listData = new ArrayList<>();

        CellDetail cellDetail;
        RowData rowData;
        List listOfData = new ArrayList();
        List listOfCell;
        List listOfColumn = new ArrayList();
        listData = payrollBo.searchReportDanaPensiunSys(payroll.getBulan(), payroll.getTahun(), payroll.getBranchId());
        String periode = "Bulan " + CommonUtil.convertNumberToStringBulan(payroll.getBulan()) + " Tahun " + payroll.getTahun();

        titleReport = "Rekapitulasi Dana Pensiun Karyawan bulan " + CommonUtil.convertNumberToStringBulan(payroll.getBulan() + " Tahun " + payroll.getTahun());

        listOfColumn.add("Nip");
        listOfColumn.add("Nama");
        listOfColumn.add("Golongan");
        listOfColumn.add("Dana Pensiun");
        listOfColumn.add("Status");
        listOfColumn.add("PHDP");
        listOfColumn.add("Iuran Peserta");
        listOfColumn.add("Iuran Perusahaan");
        listOfColumn.add("Jumlah");

        for (Payroll data : listData) {
            rowData = new RowData();
            listOfCell = new ArrayList();

            //Nip
            cellDetail = new CellDetail();
            cellDetail.setCellID(0);
            cellDetail.setValueCell(data.getNip());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Bagian
            cellDetail = new CellDetail();
            cellDetail.setCellID(1);
            cellDetail.setValueCell(data.getNama());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Golongan
            cellDetail = new CellDetail();
            cellDetail.setCellID(2);
            cellDetail.setValueCell(data.getGolonganName());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //status
            cellDetail = new CellDetail();
            cellDetail.setCellID(3);
            cellDetail.setValueCell(data.getDanaPensiunName());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Golongan
            cellDetail = new CellDetail();
            cellDetail.setCellID(4);
            cellDetail.setValueCell(data.getStatusKeluarga());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Golongan
            cellDetail = new CellDetail();
            cellDetail.setCellID(5);
            cellDetail.setValueCell(data.getGajiPensiunNilai().doubleValue());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Jumlah Penghasilan Kotor
            cellDetail = new CellDetail();
            cellDetail.setCellID(6);
            cellDetail.setValueCell(data.getIuranPensiunNilai().doubleValue());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Jumlah potongan dinas
            cellDetail = new CellDetail();
            cellDetail.setCellID(7);
            cellDetail.setValueCell(data.getIuranPensiunPerusahaanNilai().doubleValue());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Jumlah Potongan Lain
            cellDetail = new CellDetail();
            cellDetail.setCellID(8);
            cellDetail.setValueCell(data.getIuranPensiunJumlahNilai().doubleValue());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Jumlah Penerima Bersih
            cellDetail = new CellDetail();
            cellDetail.setCellID(9);
            cellDetail.setValueCell(data.getTotalGajiBersih());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);


            rowData.setListOfCell(listOfCell);
            listOfData.add(rowData);
        }

        HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, periode, listOfColumn, listOfData, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            wb.write(baos);
        } catch (IOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollAction.searchReportDanaPensiun");
            } catch (GeneralBOException e1) {
                logger.error("[payrollAction.searchReportDanaPensiun] Error when downloading,", e1);
            }
            logger.error("[payrollAction.searchReportDanaPensiun] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
            return ERROR;
        }

        setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
        setContentDisposition("filename=\"" + "ReportDanaPensiun.${documentFormat}\"");
        logger.info("[payrollAction.searchReportDanaPensiun] end process <<<");
        return "downloadXlsDanaPensiun";
    }

    public String searchReportBpjs() {
        logger.info("[payrollAction.searchReportBpjs] start process >>>");
        Payroll payroll = getPayroll();

        String titleReport = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        List<Payroll> listData = new ArrayList<>();

        CellDetail cellDetail;
        RowData rowData;
        List listOfData = new ArrayList();
        List listOfCell;
        List listOfColumn = new ArrayList();
        listData = payrollBo.searchReportBpjs(payroll.getBulan(), payroll.getTahun(), payroll.getBranchId());
        String periode = "Bulan " + CommonUtil.convertNumberToStringBulan(payroll.getBulan()) + " Tahun " + payroll.getTahun();

        titleReport = "Rekapitulasi Bpjs";

        listOfColumn.add("Nip");
        listOfColumn.add("Nama");
        listOfColumn.add("Golongan");
        listOfColumn.add("Dana Pensiun");
        listOfColumn.add("Status");
        listOfColumn.add("Gaji BPJS");
        listOfColumn.add("BPJS TK");
        listOfColumn.add("BPJS Pensiun");
        listOfColumn.add("BPJS Kesehatan");
        listOfColumn.add("Jumlah");

        for (Payroll data : listData) {
            rowData = new RowData();
            listOfCell = new ArrayList();

            //Nip
            cellDetail = new CellDetail();
            cellDetail.setCellID(0);
            cellDetail.setValueCell(data.getNip());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Bagian
            cellDetail = new CellDetail();
            cellDetail.setCellID(1);
            cellDetail.setValueCell(data.getNama());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Golongan
            cellDetail = new CellDetail();
            cellDetail.setCellID(2);
            cellDetail.setValueCell(data.getGolonganName());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //status
            cellDetail = new CellDetail();
            cellDetail.setCellID(3);
            cellDetail.setValueCell(data.getDanaPensiunName());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Golongan
            cellDetail = new CellDetail();
            cellDetail.setCellID(4);
            cellDetail.setValueCell(data.getStatusKeluarga());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Gaji Bbpjs
            cellDetail = new CellDetail();
            cellDetail.setCellID(5);
            cellDetail.setValueCell(data.getGajiBpjsNilai().doubleValue());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Bpjs TK
            cellDetail = new CellDetail();
            cellDetail.setCellID(6);
            cellDetail.setValueCell(data.getIuranBpjsTkNilai().doubleValue());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //BPJS Pensiun
            cellDetail = new CellDetail();
            cellDetail.setCellID(7);
            cellDetail.setValueCell(data.getIuranBpjsPensiunNilai().doubleValue());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //BPJS Kesehatan
            cellDetail = new CellDetail();
            cellDetail.setCellID(8);
            cellDetail.setValueCell(data.getIuranBpjsKesehatanNilai().doubleValue());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Jumlah
            cellDetail = new CellDetail();
            cellDetail.setCellID(9);
            cellDetail.setValueCell(data.getIuranPensiunJumlahNilai().doubleValue());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);


            rowData.setListOfCell(listOfCell);
            listOfData.add(rowData);
        }

        HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, periode, listOfColumn, listOfData, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            wb.write(baos);
        } catch (IOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollAction.searchReportBpjs");
            } catch (GeneralBOException e1) {
                logger.error("[payrollAction.searchReportBpjs] Error when downloading,", e1);
            }
            logger.error("[payrollAction.searchReportBpjs] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
            return ERROR;
        }

        setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
        setContentDisposition("filename=\"" + "ReportBpjs.${documentFormat}\"");
        logger.info("[payrollAction.searchReportBpjs] end process <<<");
        return "downloadXlsBpjs";
    }

    public String payrollReportExcelRekapGaji() {
        logger.info("[payrollAction.payrollReportExcelRekapGaji] start process >>>");
        String bulan = getBulan();
        String tahun = getTahun();
        String unit = getBranchId();
        String status = getStatusPegawai();
        String tipe = getTipe();

        String titleReport = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        List<Payroll> listData = new ArrayList<>();

        CellDetail cellDetail;
        RowData rowData;
        List listOfData = new ArrayList();
        List listOfCell;
        List listOfColumn = new ArrayList();
        String periode = "Bulan " + CommonUtil.convertNumberToStringBulan(bulan) + " Tahun " + tahun;

        if(tipe.equalsIgnoreCase("RG") || tipe.equalsIgnoreCase("RGD")){
            String strWhere = " posisi.bagian_id not in ('PB010', 'PB023')";
            titleReport = "Report Rekap bulan " + CommonUtil.convertNumberToStringBulan(bulan + " Tahun " + tahun);

            if(tipe.equalsIgnoreCase("RGD")){
                strWhere = " posisi.bagian_id in ('PB010', 'PB023')";
                titleReport = "Report Rekap Dirkom bulan " + CommonUtil.convertNumberToStringBulan(bulan + " Tahun " + tahun);
            }

            listData = payrollBo.searchReportRekapGajiSys(bulan, tahun, unit, status, strWhere);

            listOfColumn.add("Nama Bagian");
            listOfColumn.add("J. Org");
            listOfColumn.add("Gaji Dasar");
            listOfColumn.add("Tunj. UMK");
            listOfColumn.add("Tunj. Struktural");
            listOfColumn.add("Tunj. Peralihan");
            listOfColumn.add("Tunj.Jab. Struktural");
            listOfColumn.add("Tunj. Strategis");
            listOfColumn.add("Tunj. Kompensasi");
            listOfColumn.add("Tunj. Transport");
            listOfColumn.add("Tunj. Listrik/Air");
            listOfColumn.add("Tunj. Obat");
            listOfColumn.add("Tunj. Lain lain");
            listOfColumn.add("Tunj. PPh");
            listOfColumn.add("Tunj. Perumahan");
            listOfColumn.add("Tunj. Lembur");
            listOfColumn.add("Tunj. Penghasilan Kotor");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nama Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNamaBagian());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Jumlah Pegawai
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getJumlahPegawai());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Dasar
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Umk
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunjangan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);
                cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Peralihan
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //tunjangan Jabatan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(6);
                cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Strategis
                cellDetail = new CellDetail();
                cellDetail.setCellID(7);
                cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Kompensasi
                cellDetail = new CellDetail();
                cellDetail.setCellID(8);
                cellDetail.setValueCell(data.getKompensasiNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Transport
                cellDetail = new CellDetail();
                cellDetail.setCellID(9);
                cellDetail.setValueCell(data.getTunjanganTransportNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Listrik Air
                cellDetail = new CellDetail();
                cellDetail.setCellID(10);
                cellDetail.setValueCell(data.getTunjanganAirListrikNilai().doubleValue());

                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Obat
                cellDetail = new CellDetail();
                cellDetail.setCellID(11);
                cellDetail.setValueCell(data.getTunjanganPengobatanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Lain
                cellDetail = new CellDetail();
                cellDetail.setCellID(12);
                cellDetail.setValueCell(data.getTunjanganLainNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj PPh
                cellDetail = new CellDetail();
                cellDetail.setCellID(13);
                cellDetail.setValueCell(data.getTunjanganPphNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Perumahan
                cellDetail = new CellDetail();
                cellDetail.setCellID(14);
                cellDetail.setValueCell(data.getTunjanganPerumahanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Lembur
                cellDetail = new CellDetail();
                cellDetail.setCellID(15);
                cellDetail.setValueCell(data.getTunjanganLemburNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);


                //Penghasilan Kotor
                cellDetail = new CellDetail();
                cellDetail.setCellID(16);
                cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("RP")){
            String strWhere = " posisi.bagian_id not in ('PB010', 'PB023')";
            titleReport = "Report Rekap bulan " + CommonUtil.convertNumberToStringBulan(bulan + " Tahun " + tahun);

            if(tipe.equalsIgnoreCase("RPD")){
                strWhere = " posisi.bagian_id in ('PB010', 'PB023')";
                titleReport = "Report Rekap Dirkom bulan " + CommonUtil.convertNumberToStringBulan(bulan + " Tahun " + tahun);
            }

            listData = payrollBo.searchReportRekapPotonganSys(bulan, tahun, unit, status, strWhere);
            titleReport = "Report Rekap bulan " + CommonUtil.convertNumberToStringBulan(bulan + " Tahun " + tahun);

            listOfColumn.add("Nama Bagian");
            listOfColumn.add("J. Org");
            listOfColumn.add("PPh Gaji");
            listOfColumn.add("PPh Obat");
            listOfColumn.add("Jumlah PPh");
            listOfColumn.add("Iuran Pensiun");
            listOfColumn.add("Astek");
            listOfColumn.add("Bpjs Kesehatan");
            listOfColumn.add("Bpjs Pensiun");
            listOfColumn.add("UM Lain");
            listOfColumn.add("Kurang Astek");
            listOfColumn.add("Jumlah Potongan");
            listOfColumn.add("Biaya Obat");
            listOfColumn.add("Koperasi");
            listOfColumn.add("Dana Sosial");
            listOfColumn.add("SP");
            listOfColumn.add("Bazis");
            listOfColumn.add("Bapor");
            listOfColumn.add("Lain lain");
            listOfColumn.add("Jumlah Pot Lain");
            listOfColumn.add("Jumlah Bersih");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nama Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNamaBagian());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Jumlah Pegawai
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getJumlahPegawai());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getPphGajiNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getPphPengobatanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Peralihan
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);
                cellDetail.setValueCell(data.getJumlahPph().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunjangan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getIuranPensiunNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //tunjangan Jabatan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(6);
                cellDetail.setValueCell(data.getIuranBpjsTkNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Strategis
                cellDetail = new CellDetail();
                cellDetail.setCellID(7);
                cellDetail.setValueCell(data.getIuranBpjsKesehatanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Strategis
                cellDetail = new CellDetail();
                cellDetail.setCellID(8);
                cellDetail.setValueCell(data.getIuranBpjsPensiunNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Kompensasi
                cellDetail = new CellDetail();
                cellDetail.setCellID(9);
                cellDetail.setValueCell(data.getUangMukaLainnyaNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Transport
                cellDetail = new CellDetail();
                cellDetail.setCellID(10);
                cellDetail.setValueCell(data.getKekuranganBpjsTkNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Listrik Air
                cellDetail = new CellDetail();
                cellDetail.setCellID(11);
                cellDetail.setValueCell(data.getTotalBNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Obat
                cellDetail = new CellDetail();
                cellDetail.setCellID(12);
                cellDetail.setValueCell(data.getPengobatanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Perumahan
                cellDetail = new CellDetail();
                cellDetail.setCellID(13);
                cellDetail.setValueCell(data.getKoperasiNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj PPh
                cellDetail = new CellDetail();
                cellDetail.setCellID(14);
                cellDetail.setValueCell(data.getDansosNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Lembur
                cellDetail = new CellDetail();
                cellDetail.setCellID(15);
                cellDetail.setValueCell(data.getSPNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Lain
                cellDetail = new CellDetail();
                cellDetail.setCellID(16);
                cellDetail.setValueCell(data.getBazisNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Penghasilan Kotor
                cellDetail = new CellDetail();
                cellDetail.setCellID(17);
                cellDetail.setValueCell(data.getBaporNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                cellDetail = new CellDetail();
                cellDetail.setCellID(18);
                cellDetail.setValueCell(data.getLainLainNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                cellDetail = new CellDetail();
                cellDetail.setCellID(19);
                cellDetail.setValueCell(data.getTotalCNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                cellDetail = new CellDetail();
                cellDetail.setCellID(20);
                cellDetail.setValueCell(data.getTotalGajiBersihNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("THR") || tipe.equalsIgnoreCase("THRD")){
            String strWhere = " posisi.bagian_id not in ('PB010', 'PB023')";
            titleReport = "Report Rekap THR Tahun " + tahun;

            if(tipe.equalsIgnoreCase("THRD")){
                strWhere = " posisi.bagian_id in ('PB010', 'PB023')";
                titleReport = "Report Rekap THR Dirkom Tahun " + tahun;
            }

            listData = payrollBo.searchReportRekapThrSys(bulan, tahun, unit, status, strWhere);

            listOfColumn.add("Nama Bagian");
            listOfColumn.add("J. Org");
            listOfColumn.add("Gaji Dasar");
            listOfColumn.add("Tunj. UMK");
            listOfColumn.add("Tunj. Struktural");
            listOfColumn.add("Tunj. Peralihan");
            listOfColumn.add("Tunj.Jab. Struktural");
            listOfColumn.add("Tunj. Strategis");
            listOfColumn.add("Tunj. PPh");
            listOfColumn.add("THR Kotor");
            listOfColumn.add("Pot Pph");
            listOfColumn.add("Final THR");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nama Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNamaBagian());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Jumlah Pegawai
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getJumlahPegawai());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Dasar
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Umk
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunjangan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);
                cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Peralihan
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //tunjangan Jabatan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(6);
                cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Strategis
                cellDetail = new CellDetail();
                cellDetail.setCellID(7);
                cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj PPh
                cellDetail = new CellDetail();
                cellDetail.setCellID(8);
                cellDetail.setValueCell(data.getTunjanganPphNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);


                //Penghasilan Kotor
                cellDetail = new CellDetail();
                cellDetail.setCellID(9);
                cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Potongan PPH
                cellDetail = new CellDetail();
                cellDetail.setCellID(10);
                cellDetail.setValueCell(data.getPotPphNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Potongan PPH
                cellDetail = new CellDetail();
                cellDetail.setCellID(11);
                cellDetail.setValueCell(data.getTotalThrNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("jasprod")){
            String strWhere = " posisi.bagian_id not in ('PB010', 'PB023')";
            titleReport = "Report Rekap Jasprod Tahun " + tahun;

            /*if(tipe.equalsIgnoreCase("THRD")){
                strWhere = " posisi.bagian_id in ('PB010', 'PB023')";
                titleReport = "Report Rekap THR Dirkom Tahun " + tahun;
            }*/

            listData = payrollBo.searchReportRekapJasprodSys(bulan, tahun, unit, status, strWhere);

            listOfColumn.add("Nama Bagian");
            listOfColumn.add("J. Org");
            listOfColumn.add("Gaji Dasar");
            listOfColumn.add("Tunj. UMK");
            listOfColumn.add("Tunj. Struktural");
            listOfColumn.add("Tunj. Peralihan");
            listOfColumn.add("Tunj.Jab. Struktural");
            listOfColumn.add("Tunj. Strategis");
            listOfColumn.add("Gaji Bruto");
            listOfColumn.add("Gaji X Masa Kerja");
            listOfColumn.add("Nilai SMK");
            listOfColumn.add("Perhitungan");
            listOfColumn.add("Gaji X Faktor Normal");
            listOfColumn.add("Tambahan");
            listOfColumn.add("Bruto");
            listOfColumn.add("Pot. Pph");
            listOfColumn.add("Pot. Koperasi");
            listOfColumn.add("Pot. Taliasih");
            listOfColumn.add("Pot. lain");
            listOfColumn.add("Final Thr");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nama Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNamaBagian());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Jumlah Pegawai
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getJumlahPegawai());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Dasar
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Umk
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunjangan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);
                cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Peralihan
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //tunjangan Jabatan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(6);
                cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Strategis
                cellDetail = new CellDetail();
                cellDetail.setCellID(7);
                cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Bruto
                cellDetail = new CellDetail();
                cellDetail.setCellID(8);
                cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji X Masa Kerja
                cellDetail = new CellDetail();
                cellDetail.setCellID(9);
                cellDetail.setValueCell(data.getGajiMasaKerjaNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai SMK
                cellDetail = new CellDetail();
                cellDetail.setCellID(10);
                cellDetail.setValueCell(data.getNilaiSmkNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai Perhitungan
                cellDetail = new CellDetail();
                cellDetail.setCellID(11);
                cellDetail.setValueCell(data.getGajiMasaKerjaFaktorSmkNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai Gaji X Faktor Normal
                cellDetail = new CellDetail();
                cellDetail.setCellID(12);
                cellDetail.setValueCell(data.getGajiMasaKerjaFaktorNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai Tambahan
                cellDetail = new CellDetail();
                cellDetail.setCellID(13);
                cellDetail.setValueCell(data.getTambahanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai Bruto Jasprod
                cellDetail = new CellDetail();
                cellDetail.setCellID(14);
                cellDetail.setValueCell(data.getBrutoJasprodNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai PPH Jasprod
                cellDetail = new CellDetail();
                cellDetail.setCellID(15);
                cellDetail.setValueCell(data.getPotPphNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai Pot Koperasi
                cellDetail = new CellDetail();
                cellDetail.setCellID(16);
                cellDetail.setValueCell(data.getKoperasiNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai Pot Taliasih
                cellDetail = new CellDetail();
                cellDetail.setCellID(17);
                cellDetail.setValueCell(data.getDansosNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai Pot Lain
                cellDetail = new CellDetail();
                cellDetail.setCellID(18);
                cellDetail.setValueCell(data.getLainLainNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nilai Final Jasprod
                cellDetail = new CellDetail();
                cellDetail.setCellID(19);
                cellDetail.setValueCell(data.getTotalJasProdNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);


                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }else if(tipe.equalsIgnoreCase("insentif")){
            String strWhere = " posisi.bagian_id not in ('PB010', 'PB023')";
            titleReport = "Report Rekap Insentif Tahun " + tahun;

            /*if(tipe.equalsIgnoreCase("THRD")){
                strWhere = " posisi.bagian_id in ('PB010', 'PB023')";
                titleReport = "Report Rekap THR Dirkom Tahun " + tahun;
            }*/

            listData = payrollBo.searchReportRekapInsentifSys(bulan, tahun, unit, status, strWhere);

            listOfColumn.add("Nama Bagian");
            listOfColumn.add("J. Org");
            listOfColumn.add("Gaji Dasar");
            listOfColumn.add("Tunj. UMK");
            listOfColumn.add("Tunj. Struktural");
            listOfColumn.add("Tunj. Peralihan");
            listOfColumn.add("Tunj.Jab. Struktural");
            listOfColumn.add("Tunj. Strategis");
            listOfColumn.add("Gaji Bruto");
            listOfColumn.add("Pot. Insentif Individu");
            listOfColumn.add("Insentif Diterima");
            listOfColumn.add("Potongan Pph");
            listOfColumn.add("Final Insentif");

            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nama Bagian
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNamaBagian());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Jumlah Pegawai
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getJumlahPegawai());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Dasar
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Umk
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunjangan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);
                cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Peralihan
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //tunjangan Jabatan Struktural
                cellDetail = new CellDetail();
                cellDetail.setCellID(6);
                cellDetail.setValueCell(data.getTunjanganJabatanStrukturalNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Tunj Strategis
                cellDetail = new CellDetail();
                cellDetail.setCellID(7);
                cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Gaji Bruto
                cellDetail = new CellDetail();
                cellDetail.setCellID(8);
                cellDetail.setValueCell(data.getTotalANilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Pot. Insentif Individu
                cellDetail = new CellDetail();
                cellDetail.setCellID(9);
                cellDetail.setValueCell(data.getPotonganInsentifIndividuNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Insentif Diterima
                cellDetail = new CellDetail();
                cellDetail.setCellID(10);
                cellDetail.setValueCell(data.getInsentifDiterimaNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Pph
                cellDetail = new CellDetail();
                cellDetail.setCellID(11);
                cellDetail.setValueCell(data.getPphGajiNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Pph
                cellDetail = new CellDetail();
                cellDetail.setCellID(12);
                cellDetail.setValueCell(data.getTotalInsentifNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }

        HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, periode, listOfColumn, listOfData, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            wb.write(baos);
        } catch (IOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollAction.searchReportDanaPensiun");
            } catch (GeneralBOException e1) {
                logger.error("[payrollAction.searchReportDanaPensiun] Error when downloading,", e1);
            }
            logger.error("[payrollAction.searchReportDanaPensiun] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
            return ERROR;
        }

        setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
        setContentDisposition("filename=\"" + "ReportRekapGaji.${documentFormat}\"");
        logger.info("[payrollAction.payrollReportExcelRekapGaji] end process <<<");
        return "downloadXlsRekapGaji";
    }

    public String searchReportTransferGaji() {
        logger.info("[payrollAction.searchReportTransferGaji] start process >>>");
        Payroll payroll = getPayroll();

        String titleReport = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        List<Payroll> listData = new ArrayList<>();

        CellDetail cellDetail;
        RowData rowData;
        List listOfData = new ArrayList();
        List listOfCell;
        List listOfColumn = new ArrayList();

        if(payroll.getTipe().equalsIgnoreCase("PR")){
            listData = payrollBo.searchReportTransferGajiSys(payroll.getBulan(), payroll.getTahun(), payroll.getBranchId());

            titleReport = "Transfer Gaji ";

            listOfColumn.add("Nip");
            listOfColumn.add("Nama");
            listOfColumn.add("Cabang");
            listOfColumn.add("BANK");
            listOfColumn.add("NO Rekening");
            listOfColumn.add("Jumlah");


            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nama
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nama Bank
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getNamaBank());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Cabang Bank
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getCabangBank());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //No Rekening
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);

                cellDetail.setValueCell(data.getNoRek());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Jumlah
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getTotalGajiBersihNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }

            String periode = "Bulan " + CommonUtil.convertNumberToStringBulan(payroll.getBulan()) + " Tahun " + payroll.getTahun();
            HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, periode, listOfColumn, listOfData, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                wb.write(baos);
            } catch (IOException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollAction.searchReportDanaPensiun");
                } catch (GeneralBOException e1) {
                    logger.error("[payrollAction.searchReportTransferGaji] Error when downloading,", e1);
                }
                logger.error("[payrollAction.searchReportTransferGaji] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
                return ERROR;
            }

            setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
            setContentDisposition("filename=\"" + "ReportTransferGaji.${documentFormat}\"");
        }else if(payroll.getTipe().equalsIgnoreCase("PD")){ //Pendidikan

            listData = payrollBo.searchReportTransferPendidikanSys(payroll.getBulan(), payroll.getTahun(), payroll.getBranchId());
            titleReport = "Transfer Tunjangan Pendidikan ";

            listOfColumn.add("Nip");
            listOfColumn.add("Nama");
            listOfColumn.add("Cabang");
            listOfColumn.add("BANK");
            listOfColumn.add("NO Rekening");
            listOfColumn.add("Jumlah");


            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nama
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nama Bank
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getNamaBank());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Cabang Bank
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getCabangBank());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //No Rekening
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);

                cellDetail.setValueCell(data.getNoRek());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Jumlah
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getTotalGajiBersihNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }

            String periode = "Bulan " + CommonUtil.convertNumberToStringBulan(payroll.getBulan()) + " Tahun " + payroll.getTahun();
            HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, periode, listOfColumn, listOfData, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                wb.write(baos);
            } catch (IOException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollAction.searchReportDanaPensiun");
                } catch (GeneralBOException e1) {
                    logger.error("[payrollAction.searchReportTransferGaji] Error when downloading,", e1);
                }
                logger.error("[payrollAction.searchReportTransferGaji] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
                return ERROR;
            }

            setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
            setContentDisposition("filename=\"" + "ReportTransferPendidikan.${documentFormat}\"");
        }else if(payroll.getTipe().equalsIgnoreCase("T")){ //THR

            listData = payrollBo.searchReportTransferThrSys(payroll.getBulan(), payroll.getTahun(), payroll.getBranchId());
            titleReport = "Transfer Tunjangan THR ";

            listOfColumn.add("Nip");
            listOfColumn.add("Nama");
            listOfColumn.add("Cabang");
            listOfColumn.add("BANK");
            listOfColumn.add("NO Rekening");
            listOfColumn.add("Jumlah");


            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nama
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nama Bank
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getNamaBank());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Cabang Bank
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getCabangBank());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //No Rekening
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);

                cellDetail.setValueCell(data.getNoRek());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Jumlah
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getTotalGajiBersihNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }

            String periode = "Bulan " + CommonUtil.convertNumberToStringBulan(payroll.getBulan()) + " Tahun " + payroll.getTahun();
            HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, periode, listOfColumn, listOfData, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                wb.write(baos);
            } catch (IOException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollAction.searchReportDanaPensiun");
                } catch (GeneralBOException e1) {
                    logger.error("[payrollAction.searchReportTransferGaji] Error when downloading,", e1);
                }
                logger.error("[payrollAction.searchReportTransferGaji] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
                return ERROR;
            }

            setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
            setContentDisposition("filename=\"" + "ReportTransferThr.${documentFormat}\"");
        }else if(payroll.getTipe().equalsIgnoreCase("JP")){ //THR

            listData = payrollBo.searchReportTransferJasprodSys(payroll.getBulan(), payroll.getTahun(), payroll.getBranchId());
            titleReport = "Transfer Jasprod ";

            listOfColumn.add("Nip");
            listOfColumn.add("Nama");
            listOfColumn.add("Cabang");
            listOfColumn.add("BANK");
            listOfColumn.add("NO Rekening");
            listOfColumn.add("Jumlah");


            for (Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();

                //Nip
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nama
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Nama Bank
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getNamaBank());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Cabang Bank
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getCabangBank());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //No Rekening
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);

                cellDetail.setValueCell(data.getNoRek());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                //Jumlah
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getTotalGajiBersihNilai().doubleValue());
                cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
                listOfCell.add(cellDetail);

                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }

            String periode = "Bulan " + CommonUtil.convertNumberToStringBulan(payroll.getBulan()) + " Tahun " + payroll.getTahun();
            HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, periode, listOfColumn, listOfData, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                wb.write(baos);
            } catch (IOException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollAction.searchReportDanaPensiun");
                } catch (GeneralBOException e1) {
                    logger.error("[payrollAction.searchReportTransferGaji] Error when downloading,", e1);
                }
                logger.error("[payrollAction.searchReportTransferGaji] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
                return ERROR;
            }

            setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
            setContentDisposition("filename=\"" + "ReportTransferThr.${documentFormat}\"");
        }

        logger.info("[payrollAction.searchReportTransferGaji] end process <<<");
        return "downloadXlsTransferGaji";
    }

    public String searchReport(){
        logger.info("[PayrollAction.searchReport] start process >>>");

        String hasil = "";
        Payroll searchPayroll = getPayroll();
        List<Payroll> listData = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

        logger.info("[PayrollAction.searchReport] end process <<<");

        String statusPegawai = "";
        String unit = "";
        if(searchPayroll.getStatusPegawai() != null && searchPayroll.getStatusPegawai().equalsIgnoreCase("KS")){
            statusPegawai = "Karyawan Staf";
        }else{
            statusPegawai = "Karyawan Non Staf";
        }

        if(searchPayroll.getBranchId() != null && searchPayroll.getBranchId().equalsIgnoreCase("KD01")){
            unit = "Kantor Direksi";
        }else if(searchPayroll.getBranchId() != null && searchPayroll.getBranchId().equalsIgnoreCase("PGKB")){
            unit = "Krebet Baru";
        }else if(searchPayroll.getBranchId() != null && searchPayroll.getBranchId().equalsIgnoreCase("PGRA")){
            unit = "Rejo Agung";
        }

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("statusPegawai", statusPegawai + " - " + unit);
        reportParams.put("strBulan", "Periode : " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan()) + "  " + searchPayroll.getTahun());

        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        if(searchPayroll.getTipe().equalsIgnoreCase("PK")){
            listData = payrollBo.printReportPayrollBulanSys(searchPayroll.getBulan(), searchPayroll.getTahun(),
                    searchPayroll.getBranchId(), searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Daftar Penghasilan Kotor Karyawan");
            reportParams.put("itemDataSource", itemData);

            hasil = "success_print_report_payroll_bulan";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("PD")){

            listData = payrollBo.printReportPayrollPotonganDinasSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Daftar Potongan Dinas Karyawan");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_potongan_dinas";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("PL")){

            listData = payrollBo.printReportPayrollPotonganLainLainSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Daftar Potongan Lain Lain");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_potongan_lain";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("R")){
            listData = payrollBo.printReportPayrollPenghasilanKaryawanSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rekapitulasi Penghasilan Karyawan");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_penghasilan_karyawan";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("pendidikan")){
            listData = payrollBo.printReportPayrollPendidikanSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rekapitulasi Tunjangan Pendidikan");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_pendidikan";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("thr")){
            listData = payrollBo.printReportPayrollThrSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rekapitulasi Tunjangan THR");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_thr";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("jasprod")){
            listData = payrollBo.printReportPayrollJasprodSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rekapitulasi Tunjangan Jasprod");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_jasprod";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("insentif")){
            listData = payrollBo.printReportPayrollInsentifSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rekapitulasi Tunjangan Insentif");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_insentif";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("rapelBulan")){
            listData = payrollBo.printReportPayrollRapelSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rapel");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_rapel";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("rapelThr")){
            listData = payrollBo.printReportPayrollRapelThrSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rapel THR");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_rapel_thr";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("rapelPendidikan")){
            listData = payrollBo.printReportPayrollRapelPendidikanSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rapel Pendidikan");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_rapel_pendidikan";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("rapelLembur")){
            listData = payrollBo.printReportPayrollRapelLemburSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rapel Lembur");
            reportParams.put("itemDataSource", itemData);

            reportParams.put("strBulan", "Tanggal : 11-12-2018 s/d 10-07-2019" );
            hasil = "print_report_rapel_lembur";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("rapelInsentif")){
            listData = payrollBo.printReportPayrollRapelInsentifSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rapel Insentif");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_rapel_insentif";
        }else if(searchPayroll.getTipe().equalsIgnoreCase("rapelJubileum")){
            listData = payrollBo.printReportPayrollRapelJubileumSys(searchPayroll.getBulan(), searchPayroll.getTahun(), searchPayroll.getBranchId(),
                    searchPayroll.getStatusPegawai());
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rapel Jubileum");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_rapel_jubileum";
        }

        if(searchPayroll != null){
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "searchReport");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.searchReport] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.searchReport] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }
        }

        return hasil;
    }

    public String searchReportRekapGaji(){
        logger.info("[PayrollAction.searchReportRekapGaji] start process >>>");

        Payroll searchPayroll = getPayroll();
        String statusPegawai = "";
        String hasil = "";
        String unit = "";

        if(searchPayroll.getStatusPegawai() != null && searchPayroll.getStatusPegawai().equalsIgnoreCase("KS")){
            statusPegawai = "Karyawan Staf";
        }else{
            statusPegawai = "Karyawan Non Staf";
        }

        if(searchPayroll.getTipe().equalsIgnoreCase("RG")){
            hasil = "print_report_rekap_gaji";
            reportParams.put("titleReport", "Rekap Gaji " + statusPegawai + " Bulan " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan())
                    + "  " + searchPayroll.getTahun());
        }else if(searchPayroll.getTipe().equalsIgnoreCase("RP")){
            hasil = "print_report_rekap_potongan";
            reportParams.put("titleReport", "Rekap Potongan Gaji " + statusPegawai + " Bulan " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan())
                    + "  " + searchPayroll.getTahun());
        }else if(searchPayroll.getTipe().equalsIgnoreCase("RGD")){
            hasil = "print_report_rekap_gaji_dirkom";
            statusPegawai = "";
            reportParams.put("titleReport", "Rekap Gaji Dirkom" + " Bulan " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan())
                    + "  " + searchPayroll.getTahun());
        }else if(searchPayroll.getTipe().equalsIgnoreCase("RPD")){
            hasil = "print_report_rekap_potongan_dirkom";
            statusPegawai = "";
            reportParams.put("titleReport", "Rekap Potongan Gaji Dirkom " + statusPegawai + " Bulan " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan())
                    + "  " + searchPayroll.getTahun());
        }else if(searchPayroll.getTipe().equalsIgnoreCase("THR")){
            hasil = "print_report_rekap_thr";
            reportParams.put("titleReport", "Rekap THR " + statusPegawai + " Bulan " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan())
                    + "  " + searchPayroll.getTahun());
        }else if(searchPayroll.getTipe().equalsIgnoreCase("THRD")){
            hasil = "print_report_rekap_thr_dirkom";
            reportParams.put("titleReport", "Rekap THR Dirkom"  + " Bulan " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan())
                    + "  " + searchPayroll.getTahun());
        }else if(searchPayroll.getTipe().equalsIgnoreCase("jasprod")){
            hasil = "print_report_rekap_jasprod";
            reportParams.put("titleReport", "Rekap Jasprod " + statusPegawai + " Bulan " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan())
                    + "  " + searchPayroll.getTahun());
        }else if(searchPayroll.getTipe().equalsIgnoreCase("insentif")){
            hasil = "print_report_rekap_insentif";
            reportParams.put("titleReport", "Rekap Insentif " + statusPegawai + " Bulan " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan())
                    + "  " + searchPayroll.getTahun());
        }

        if(searchPayroll.getBranchId() != null && searchPayroll.getBranchId().equalsIgnoreCase("KD01")){
            unit = "Kantor Direksi";
        }else if(searchPayroll.getBranchId() != null && searchPayroll.getBranchId().equalsIgnoreCase("PGKB")){
            unit = "Krebet Baru";
        }else if(searchPayroll.getBranchId() != null && searchPayroll.getBranchId().equalsIgnoreCase("PGRA")){
            unit = "Rejo Agung";
        }

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("statusPegawai", searchPayroll.getStatusPegawai());
        reportParams.put("branchId", searchPayroll.getBranchId());
        reportParams.put("periodeBulan", searchPayroll.getBulan());
        reportParams.put("periodeTahun", searchPayroll.getTahun());
        reportParams.put("unit", unit);


        if(searchPayroll != null){
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "searchReportRekapGaji");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollAction.searchReportRekapGaji] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.searchReportRekapGaji] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }
        }

        logger.info("[PayrollAction.searchReportRekapGaji] end process <<<");
        return hasil;
    }

    public String searchReportDraft(){
        logger.info("[PayrollAction.searchReport] start process >>>");

        String hasil = "";

        String unitId = getBranchId();
        String tipe = getTipe();
        String bulan = getBulan();
        String tahun = getTahun();
        String searchStatus = getStatusPegawai();

        List<Payroll> listData = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

        logger.info("[PayrollAction.searchReport] end process <<<");

        String statusPegawai = "";
        String unit = "";
        if(statusPegawai != null && statusPegawai.equalsIgnoreCase("KS")){
            statusPegawai = "Karyawan Staf";
        }else{
            statusPegawai = "Karyawan Non Staf";
        }

        if(unitId != null && unitId.equalsIgnoreCase("KD01")){
            unit = "Kantor Direksi";
        }else if(unitId != null && unitId.equalsIgnoreCase("PGKB")){
            unit = "Krebet Baru";
        }else if(unitId != null && unitId.equalsIgnoreCase("PGRA")){
            unit = "Rejo Agung";
        }

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("statusPegawai", "Draft " + statusPegawai + " - " + unit);
        reportParams.put("strBulan", "Periode : " + CommonUtil.convertNumberToStringBulan(bulan) + "  " + tahun);

        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        if(tipe.equalsIgnoreCase("PK")){
            listData = payrollBo.printReportPayrollBulanSys(bulan, tahun,
                    unitId, searchStatus);
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Daftar Penghasilan Kotor Karyawan");
            reportParams.put("itemDataSource", itemData);

            hasil = "success_print_report_payroll_bulan";
        }else if(tipe.equalsIgnoreCase("PD")){

            listData = payrollBo.printReportPayrollPotonganDinasSys(bulan, tahun,
                    unitId, searchStatus);
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Daftar Potongan Dinas Karyawan");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_potongan_dinas";
        }else if(tipe.equalsIgnoreCase("PL")){

            listData = payrollBo.printReportPayrollPotonganLainLainSys(bulan, tahun,
                    unitId, searchStatus);

            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Daftar Potongan Lain Lain");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_potongan_lain";
        }else if(tipe.equalsIgnoreCase("R")){
            listData = payrollBo.printReportPayrollPenghasilanKaryawanSys(bulan, tahun,
                    unitId, searchStatus);
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("titleReport", "Rekapitulasi Penghasilan Karyawan");
            reportParams.put("itemDataSource", itemData);

            hasil = "print_report_penghasilan_karyawan";
        }

        if(unitId != null){
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "searchReportDraft");
                } catch (GeneralBOException e1) {
                    logger.error("[ReportAction.searchReport] Error when downloading ,", e1);
                }
                logger.error("[ReportAction.searchReportDraft] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }
        }

        return hasil;
    }

    @Override
    public String search() {
        logger.info("[PayrollAction.search] start process >>>");
        String hasil = SUCCESS;

        Payroll searchPayroll = getPayroll();

        int a = 0;
        a += 2 ;
        try {
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResult");

            // insentif lama
            /*if(searchPayroll.getTipe().equalsIgnoreCase("IN")){
                hasil = "success_insentif";
                List<PayrollInsentif> listOfsearchPayroll = new ArrayList();
                listOfsearchPayroll = payrollBoProxy.getSearchInsentif(searchPayroll);
                session.setAttribute("listOfResult", listOfsearchPayroll);
            }else{
                List<Payroll> listOfsearchPayroll = new ArrayList();
                listOfsearchPayroll = payrollBoProxy.getSearchHome(searchPayroll);
                session.setAttribute("listOfResult", listOfsearchPayroll);
            }*/

            List<Payroll> listOfsearchPayroll = new ArrayList();
            listOfsearchPayroll = payrollBoProxy.getSearchHome(searchPayroll);
            session.setAttribute("listOfResult", listOfsearchPayroll);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "PayrollBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        logger.info("[PayrollAction.search] end process <<<");

        return hasil;
    }

    public String searchSession(String nip) {
        //Payroll payroll = getPayroll();
        String flag = "N";

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Payroll> listOfResultDataPayroll = (List<Payroll>) session.getAttribute("listDataPayroll");
        List<Payroll> listOfResultDataBackup = (List<Payroll>) session.getAttribute("listDataPayrollBackup");
        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResultDataPayroll != null){
                for (Payroll payroll1: listOfResultDataPayroll) {
                    if(nip.equalsIgnoreCase(payroll1.getNip())){
                        session.removeAttribute("listDataPayrollSearch");
                        session.setAttribute("listDataPayrollSearch", payroll1);
                        flag = "Y";
                        break;
                    }
                }
            }
        }else {
            flag = "Y";
            session.removeAttribute("listDataPayrollSearch");
            session.setAttribute("listDataPayrollSearch", listOfResultDataPayroll);
        }

        if(flag.equalsIgnoreCase("N")){
            session.removeAttribute("listDataPayrollSearch");
        }

        return "init_add_v2";
    }

    public String searchPayroll() {
        logger.info("[PayrollAction.search] start process >>>");

        Payroll searchPayroll = new Payroll();
        searchPayroll.setFlag("Y");
        List<Payroll> listOfsearchPayroll = new ArrayList();

        try {
            listOfsearchPayroll = payrollBoProxy.getByCriteria(searchPayroll);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "PayrollBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayroll.addAll(listOfsearchPayroll);
        return SUCCESS;
    }

    public String searchJubileum() {
        logger.info("[PayrollAction.searchJubileum] start process >>>");

        Payroll searchJubileum = getPayroll();
        List<PayrollJubileum> listOfsearchPayrollJubileum = new ArrayList();

        try {
            listOfsearchPayrollJubileum = payrollBoProxy.searchJubileumSys(searchJubileum);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "PayrollBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAction.searchJubileum] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAction.searchJubileum] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPayrollJubileum");
        session.setAttribute("listOfResultPayrollJubileum", listOfsearchPayrollJubileum);

        return "reportJubileum_payroll";
    }

    public String searchPensiun() {
        logger.info("[PayrollAction.searchPensiun] start process >>>");

        Payroll searchPensiun = getPayroll();
        List<PayrollPensiun> listOfsearchPayrollPensiun = new ArrayList();

        try {
            listOfsearchPayrollPensiun = payrollBoProxy.searchPensiunSys(searchPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "PayrollBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAction.searchPensiun] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAction.searchPensiun] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPayrollPensiun");
        session.setAttribute("listOfResultPayrollPensiun", listOfsearchPayrollPensiun);

        return "reportPensiun_payroll";
    }

    @Override
    public String initForm() {
        logger.info("[PayrollAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollAction.initForm] end process >>>");
        return INPUT;
    }

    public String csvPajak() {
        logger.info("[PayrollAction.csvPajak] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultCsvPajak");
        logger.info("[PayrollAction.csvPajak] end process >>>");
        return "csv_pajak";
    }

    public String report() {
        logger.info("[PayrollAction.report] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultReport");
        logger.info("[PayrollAction.report] end process >>>");
        return "report_payroll";
    }

    public String reportRekapGaji() {
        logger.info("[PayrollAction.reportRekapGaji] start process >>>");

        logger.info("[PayrollAction.reportRekapGaji] end process >>>");
        return "reportRekapGaji_payroll";
    }

    public String reportJubileum() {
        logger.info("[PayrollAction.reportJubileum] start process >>>");

        logger.info("[PayrollAction.reportJubileum] end process >>>");
        return "reportJubileum_payroll";
    }
    public String reportEspt() {
        logger.info("[PayrollAction.reportEspt] start process >>>");

        logger.info("[PayrollAction.reportEspt] end process >>>");
        return "reportEspt_payroll";
    }
    public String reportPensiun() {
        logger.info("[PayrollAction.reportPensiun] start process >>>");

        logger.info("[PayrollAction.reportPensiun] end process >>>");
        return "reportPensiun_payroll";
    }

    public String reportRekapDapen() {
        logger.info("[PayrollAction.reportRekapDapen] start process >>>");

        logger.info("[PayrollAction.reportRekapDapen] end process >>>");
        return "reportRekapDapen_payroll";
    }

    public String reportRekapBpjs() {
        logger.info("[PayrollAction.reportRekapBpjs] start process >>>");

        logger.info("[PayrollAction.reportRekapBpjs] end process >>>");
        return "reportRekapBpjs_payroll";
    }

    public String reportTransferGaji() {
        logger.info("[PayrollAction.reportTransferGaji] start process >>>");

        logger.info("[PayrollAction.reportTransferGaji] end process >>>");
        return "reportTransferGaji_payroll";
    }

    public String initPayroll() {
        logger.info("[PayrollAction.search] start process >>>");

        Payroll searchPayroll = new Payroll();
        searchPayroll.setFlag("Y");
        List<Payroll> listOfsearchPayroll = new ArrayList();

        try {
            listOfsearchPayroll = payrollBoProxy.getByCriteria(searchPayroll);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "PayrollBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayroll");
        session.setAttribute("listOfResultPayroll", listOfsearchPayroll);

        logger.info("[PayrollAction.search] end process <<<");

        return "";
    }

    public void potonganLain(String nip, String keterangan1, String keterangan2, String keterangan3, String keterangan4, String keterangan5, String nilai1,
                             String nilai2, String nilai3, String nilai4, String nilai5, String total){

        PayrollPotonganLain potonganLain = new PayrollPotonganLain();
        potonganLain.setNip(nip);
        potonganLain.setKeterangan1(keterangan1);
        potonganLain.setKeterangan2(keterangan2);
        potonganLain.setKeterangan3(keterangan3);
        potonganLain.setKeterangan4(keterangan4);
        potonganLain.setKeterangan5(keterangan5);
        potonganLain.setNilai1(BigDecimal.valueOf(Double.parseDouble(nilai1)));
        potonganLain.setNilai2(BigDecimal.valueOf(Double.parseDouble(nilai2)));
        potonganLain.setNilai3(BigDecimal.valueOf(Double.parseDouble(nilai3)));
        potonganLain.setNilai4(BigDecimal.valueOf(Double.parseDouble(nilai4)));
        potonganLain.setNilai5(BigDecimal.valueOf(Double.parseDouble(nilai5)));
        potonganLain.setTotal(BigDecimal.valueOf(Double.parseDouble(total)));

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollPotonganLain> listOfResult = (List<PayrollPotonganLain>) session.getAttribute("listOfPotonganLain");
        if(listOfResult == null){
            listOfResult = new ArrayList<>();
            listOfResult.add(potonganLain);
        }else{
            listOfResult.add(potonganLain);
        }
        session.removeAttribute("listOfPotonganLain");
        session.setAttribute("listOfPotonganLain", listOfResult);
    }

    public void savePotonganLainData(String payrollId, String keterangan1, String keterangan2, String keterangan3, String keterangan4, String keterangan5, String nilai1,
                                     String nilai2, String nilai3, String nilai4, String nilai5, String total){

        PayrollPotonganLain potonganLain = new PayrollPotonganLain();
        potonganLain.setPayrollId(payrollId);
        potonganLain.setKeterangan1(keterangan1);
        potonganLain.setKeterangan2(keterangan2);
        potonganLain.setKeterangan3(keterangan3);
        potonganLain.setKeterangan4(keterangan4);
        potonganLain.setKeterangan5(keterangan5);
        potonganLain.setNilai1(BigDecimal.valueOf(Double.parseDouble(nilai1)));
        potonganLain.setNilai2(BigDecimal.valueOf(Double.parseDouble(nilai2)));
        potonganLain.setNilai3(BigDecimal.valueOf(Double.parseDouble(nilai3)));
        potonganLain.setNilai4(BigDecimal.valueOf(Double.parseDouble(nilai4)));
        potonganLain.setNilai5(BigDecimal.valueOf(Double.parseDouble(nilai5)));
        potonganLain.setTotal(BigDecimal.valueOf(Double.parseDouble(total)));

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollBo.savePotonganLainData(potonganLain);
    }

    public PayrollPotonganLain searchPotonganLain(String nip){
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollPotonganLain> listOfResult = (List<PayrollPotonganLain>) session.getAttribute("listOfPotonganLain");

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfResult != null){
                for (PayrollPotonganLain payrollPotonganLain: listOfResult) {
                    if(nip.equalsIgnoreCase(payrollPotonganLain.getNip())){
                        setPayrollPotonganLain(payrollPotonganLain);
                        break;
                    }
                }
            } else {
                setPayrollPotonganLain(new PayrollPotonganLain());
            }

            logger.info("[PayrollAction.init] end process >>>");
        }
        return getPayrollPotonganLain();
    }

    public PayrollPotonganLain getDataPotonganLain(String payrollId){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        setPayrollPotonganLain(payrollBo.getDataPotonganLainSys(payrollId));

        return getPayrollPotonganLain();
    }

    public List<PayrollUpahHarian> listGajiHarian(String nip, String branchId, String tanggalAwal, String tanggalAkhir){
        List<PayrollUpahHarian> resultGajiHarian = null;
        PayrollUpahHarian searchUpah = new PayrollUpahHarian();
        searchUpah.setNip(nip);
        searchUpah.setBranchId(branchId);
        searchUpah.setStTanggalAwal(tanggalAwal);
        searchUpah.setStTanggalAkhir(tanggalAkhir);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        resultGajiHarian = payrollBo.upahHarianSys(searchUpah);

        return resultGajiHarian;
    }

    public List<PayrollUpahHarian> listGajiHarianData(String nip, String branchId, String tanggalAwal, String tanggalAkhir){
        List<PayrollUpahHarian> resultGajiHarian = null;
        PayrollUpahHarian searchUpah = new PayrollUpahHarian();
        searchUpah.setNip(nip);
        searchUpah.setBranchId(branchId);
        searchUpah.setStTanggalAwal(tanggalAwal);
        searchUpah.setStTanggalAkhir(tanggalAkhir);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        resultGajiHarian = payrollBo.upahHarianDataSys(searchUpah);

        return resultGajiHarian;
    }

    public void removeSessionGajiPkwt(String nip){
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollUpahHarian> listBaru = new ArrayList<>();
        List<PayrollUpahHarian> listOfResult = (List<PayrollUpahHarian>) session.getAttribute("listOfGajiPkwt");
        if(listOfResult != null){
            for(PayrollUpahHarian payrollUpahHarian :listOfResult ){
                if(!nip.equalsIgnoreCase(payrollUpahHarian.getNip())){
                    listBaru.add(payrollUpahHarian);
                }
            }
        }

        session.removeAttribute("listOfGajiPkwt");
        session.setAttribute("listOfGajiPkwt", listBaru);
    }
    public void saveSessionGajiPkwt(String nip, String tanggal){
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollUpahHarian> listOfResult = (List<PayrollUpahHarian>) session.getAttribute("listOfGajiPkwt");
        PayrollUpahHarian payrollUpahHarian = new PayrollUpahHarian();
        payrollUpahHarian.setNip(nip);
        payrollUpahHarian.setStTnggal(tanggal);
        if(listOfResult != null){
            listOfResult.add(payrollUpahHarian);
        }else{
            listOfResult = new ArrayList<>();
            listOfResult.add(payrollUpahHarian);
        }

        session.removeAttribute("listOfGajiPkwt");
        session.setAttribute("listOfGajiPkwt", listOfResult);
    }
    public void removeDataGajiPkwt(String nip){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollBo.removeDataGajiPkwtSys(nip);
    }
    public void saveDataGajiPkwt(String payrollId, String tanggal){
        PayrollUpahHarian payrollUpahHarian = new PayrollUpahHarian();
        payrollUpahHarian.setPayrollId(payrollId);
        payrollUpahHarian.setStTnggal(tanggal);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollBo.updateUpahHarianDataSys(payrollUpahHarian);
    }

    public PayrollKonsistensi showKonsistensiGaji(String nip, String bulan, String tahun){
        PayrollKonsistensi listPayroll = new PayrollKonsistensi();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        listPayroll = payrollBo.showKonsistensiGaji(nip, bulan, tahun);

        return listPayroll;
    }

    public String printReportPayrollBulan(){
        logger.info("[PayrollAction.printReportPayrollBulan] start process >>>");

        String unitId = getBranchId();
        String bulan1 = getBulan();
        String tahun1 = getTahun();
        String bulan2 = getBulan2();
        String tahun2 = getTahun2();

        if (unitId != null) {
            List<Payroll> listData = new ArrayList<>();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
            listData = payrollBo.printReportPayrollBulanSys(bulan1, tahun1, unitId, "");
            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("titleReport", "Report Payroll Bulan");
            reportParams.put("itemDataSource", itemData);

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = payrollBoProxy.saveErrorMessage(e.getMessage(), "printReportPayrollBulan");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollAction.printReportPayrollBulan] Error when downloading ,", e1);
                }
                logger.error("[PayrollAction.printReportPayrollBulan] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }

        } else {
            logger.error("[PayrollAction.printReportPayrollBulan] Error when print report, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, please inform to your admin.");
        }

        logger.info("[PayrollAction.printReportPayrollBulan] end process <<<");

        return "success_print_report_payroll_bulan";
    }

    public String paging(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Payroll payroll = new Payroll();
        payroll = (Payroll) session.getAttribute("dataPayroll");

        setPayroll(payroll);
        return SUCCESS;
    }

    public List<PayrollRapelDetail> loadDataRapelDetail(String rapelId){
        List<PayrollRapelDetail> payrollRapels = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollRapel> payrollRapel = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        payrollRapels = payrollBo.loadRapelDetail(rapelId);

        return payrollRapels;
    }

    public List<PayrollRapelDetail> loadRapelDetail(String nip){
        List<PayrollRapelDetail> payrollRapels = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollRapelDetail> payrollRapel = new ArrayList<>();
        payrollRapel = (List<PayrollRapelDetail>) session.getAttribute("listDataPayrollRapelDetail");

        if(payrollRapel.size() > 0){
            for(PayrollRapelDetail rapelLoop: payrollRapel){
                if(rapelLoop.getNip().equalsIgnoreCase(nip)){
                    payrollRapels.add(rapelLoop);
                }
            }
        }

        return payrollRapels;
    }

    // digunakan oleh addPayroll.jsp untuk mengecek apakah tunjangan insentif dibulan dan tahun yang dipih sudah pernah dibayarkan atau belum
    public String cekTunjanganInsentif(String bulanMulai, String bulanSampai, String tahun, String branchId){
        String hasil = "";
        try{
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

            hasil = payrollBo.cekTunjanganInsentif(Integer.parseInt(bulanMulai), Integer.parseInt(bulanSampai), Integer.parseInt(tahun), branchId);
        }catch (Exception E){
            hasil = "Terdapat kesalahan " + E;
        }

        return hasil;
    }

    public String payrollReportExcelEspt() {
        PayrollAction.logger.info((Object)"[payrollAction.payrollReportExcelEspt] start process >>>");
        final String tahun = this.getTahun();
        final String unit = this.getBranchId();
        final String tipe = this.getTipe();
        String titleReport = "";
        String filename = "";
        final ApplicationContext ctx = (ApplicationContext)ContextLoader.getCurrentWebApplicationContext();
        final PayrollBo payrollBo = (PayrollBo)ctx.getBean("payrollBoProxy");
        final BranchBo branchBo = (BranchBo)ctx.getBean("branchBoProxy");
        Branch branch = new Branch();
        branch = branchBo.getBranchById(unit, "Y");
        List<Payroll> listData = new ArrayList<Payroll>();
        final List listOfData = new ArrayList();
        final List listOfColumn = new ArrayList();
        final String periode = " Tahun " + tahun;
        if (tipe.equalsIgnoreCase("1721")) {
            titleReport = "Tarikan Excel 1721 " + branch.getBranchName();
            filename = "Tarikan Excel 1721-" + tahun + "-" + unit;
            listData = (List<Payroll>)payrollBo.searchReportEsptSys(tahun, unit);
            listOfColumn.add("nip");
            listOfColumn.add("");
            listOfColumn.add("");
            listOfColumn.add("nama");
            listOfColumn.add("npwp");
            listOfColumn.add("masa kerja");
            listOfColumn.add("st_gaji");
            listOfColumn.add("ptkp");
            listOfColumn.add("gaji");
            listOfColumn.add("rapel_gaji");
            listOfColumn.add("gaji+rapel");
            listOfColumn.add("t_umk");
            listOfColumn.add("t_struk");
            listOfColumn.add("t_alih");
            listOfColumn.add("t_strat");
            listOfColumn.add("t_kompen");
            listOfColumn.add("tunj_trp");
            listOfColumn.add("tunj_lair");
            listOfColumn.add("tunj_rumah");
            listOfColumn.add("tunj_obat");
            listOfColumn.add("upah_lbr");
            listOfColumn.add("rapel_tunj");
            listOfColumn.add("jmlTunj");
            listOfColumn.add("tunj_pph");
            listOfColumn.add("kast_prs");
            listOfColumn.add("pak_dinas");
            listOfColumn.add("pendidikan");
            listOfColumn.add("thr");
            listOfColumn.add("japrod");
            listOfColumn.add("jubilium");
            listOfColumn.add("insentif");
            listOfColumn.add("Jml bonus");
            listOfColumn.add("BRUTO");
            listOfColumn.add("bi_jab");
            listOfColumn.add("pensiun+kekurangan");
            listOfColumn.add("astek+kekurangan");
            listOfColumn.add("PKP");
            listOfColumn.add("PAJAK");
            RowData rowData = new RowData();
            List listOfCell = new ArrayList();
            CellDetail cellDetail = new CellDetail();
            cellDetail.setCellID(0);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(1);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(1);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(2);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(3);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(4);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(5);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(6);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(7);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(8);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(9);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(10);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(11);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(12);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(13);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(14);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(15);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(16);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(17);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(18);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(19);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(20);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(21);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(22);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(23);
            cellDetail.setValueCell("bpjs beban perusahaan");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(24);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(25);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(26);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(27);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(28);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(29);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(30);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(31);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(32);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(33);
            cellDetail.setValueCell("beban pribadi");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(34);
            cellDetail.setValueCell("beban pribadi");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(35);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            cellDetail = new CellDetail();
            cellDetail.setCellID(36);
            cellDetail.setValueCell("");
            cellDetail.setAlignmentCell(1);
            listOfCell.add(cellDetail);
            rowData.setListOfCell(listOfCell);
            listOfData.add(rowData);
            for (final Payroll data : listData) {
                rowData = new RowData();
                listOfCell = new ArrayList();
                String nip = "";
                if (data.getNip() != null && !"".equalsIgnoreCase(data.getNip())) {
                    final String[] bagianNip = data.getNip().split("-");
                    nip = bagianNip[1];
                }
                final BigDecimal gajirapel = data.getGajiGolonganNilai().add(data.getRapelGajiGolongan());
                final BigDecimal jmlTunjangan = data.getTunjanganUmkNilai().add(data.getTunjanganStrukturalNilai()).add(data.getTunjanganPeralihanNilai()).add(data.getTunjanganStrategisNilai()).add(data.getKompensasiNilai()).add(data.getTunjanganTransportNilai()).add(data.getTunjanganAirListrikNilai()).add(data.getTunjanganPerumahanNilai()).add(data.getTunjanganPengobatanNilai()).add(data.getTunjanganLemburNilai()).add(data.getRapelTunjangan()).add(data.getTunjanganLainNilai()).add(data.getTunjanganJabatanStrukturalNilai());
                final BigDecimal jmlBonus = data.getTotalPendidikanNilai().add(data.getTotalThrNilai()).add(data.getTotalJasProdNilai()).add(data.getTotalJubileumNilai()).add(data.getTotalInsentifNilai());
                final BigDecimal bruto = jmlTunjangan.add(data.getTunjanganPphNilai()).add(data.getTunjanganBajuDinasNilai()).add(jmlBonus).add(gajirapel).add(data.getKastPrs());
                final BigDecimal bruto5Persen = bruto.multiply(BigDecimal.valueOf(5L).divide(BigDecimal.valueOf(100L)));
                BigDecimal bijab;
                if (bruto5Persen.compareTo(BigDecimal.valueOf(6000000L)) > 0) {
                    bijab = BigDecimal.valueOf(6000000L);
                }
                else {
                    bijab = bruto5Persen;
                }
                final BigDecimal pkp = bruto.subtract(bijab).subtract(data.getPtkpNilai()).subtract(data.getIuranPensiunNilai()).subtract(data.getIuranBpjsTkNilai());
                final BigDecimal pajak = this.hitungHutangPajak(pkp);
                cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data.getNip());
                cellDetail.setAlignmentCell(1);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(nip);
                cellDetail.setAlignmentCell(1);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell("");
                cellDetail.setAlignmentCell(1);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data.getNama());
                cellDetail.setAlignmentCell(1);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data.getNpwp());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);
                cellDetail.setValueCell(data.getMasaKerjaBulan());
                cellDetail.setAlignmentCell(2);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data.getStatusKeluarga() + "/" + data.getJumlahAnak());
                cellDetail.setAlignmentCell(2);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(6);
                cellDetail.setValueCell(data.getPtkpNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(7);
                cellDetail.setValueCell(data.getGajiGolonganNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(8);
                cellDetail.setValueCell(data.getRapelGajiGolongan().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(9);
                cellDetail.setValueCell(gajirapel.doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(10);
                cellDetail.setValueCell(data.getTunjanganUmkNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(11);
                cellDetail.setValueCell(data.getTunjanganStrukturalNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(12);
                cellDetail.setValueCell(data.getTunjanganPeralihanNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(13);
                cellDetail.setValueCell(data.getTunjanganStrategisNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(14);
                cellDetail.setValueCell(data.getKompensasiNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(15);
                cellDetail.setValueCell(data.getTunjanganTransportNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(16);
                cellDetail.setValueCell(data.getTunjanganAirListrikNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(17);
                cellDetail.setValueCell(data.getTunjanganPerumahanNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(18);
                cellDetail.setValueCell(data.getTunjanganPengobatanNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(19);
                cellDetail.setValueCell(data.getTunjanganLemburNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(20);
                cellDetail.setValueCell(data.getRapelTunjangan().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(21);
                cellDetail.setValueCell(jmlTunjangan.doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(22);
                cellDetail.setValueCell(data.getTunjanganPphNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(23);
                cellDetail.setValueCell(data.getKastPrs().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(24);
                cellDetail.setValueCell(data.getTunjanganBajuDinasNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(25);
                cellDetail.setValueCell(data.getTotalPendidikanNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(26);
                cellDetail.setValueCell(data.getTotalThrNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(27);
                cellDetail.setValueCell(data.getTotalJasProdNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(28);
                cellDetail.setValueCell(data.getTotalJubileumNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(29);
                cellDetail.setValueCell(data.getTotalInsentifNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(30);
                cellDetail.setValueCell(jmlBonus.doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(31);
                cellDetail.setValueCell(bruto.doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(32);
                cellDetail.setValueCell(bijab.doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(33);
                cellDetail.setValueCell(data.getIuranPensiunNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(34);
                cellDetail.setValueCell(data.getIuranBpjsTkNilai().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(35);
                cellDetail.setValueCell(pkp.doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(36);
                cellDetail.setValueCell(pajak.doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }
        else if (tipe.equalsIgnoreCase("pdppph")) {
            titleReport = "Tarikan Pendapatan+pph " + branch.getBranchName();
            filename = "Tarikan Pendapatan+pph-" + tahun + "-" + unit;
            final List<PayrollPendapatanPphDTO> listData2 = (List<PayrollPendapatanPphDTO>)payrollBo.searchReportPendapatanPph(tahun, unit);
            listOfColumn.add("NIP");
            listOfColumn.add("NAMA");
            listOfColumn.add("PDP_01");
            listOfColumn.add("PPH_01");
            listOfColumn.add("PDP_02");
            listOfColumn.add("PPH_02");
            listOfColumn.add("PDP_03");
            listOfColumn.add("PPH_03");
            listOfColumn.add("PDP_04");
            listOfColumn.add("PPH_04");
            listOfColumn.add("PDP_05");
            listOfColumn.add("PPH_05");
            listOfColumn.add("PDP_06");
            listOfColumn.add("PPH_06");
            listOfColumn.add("PDP_07");
            listOfColumn.add("PPH_07");
            listOfColumn.add("PDP_08");
            listOfColumn.add("PPH_08");
            listOfColumn.add("PDP_09");
            listOfColumn.add("PPH_09");
            listOfColumn.add("PDP_10");
            listOfColumn.add("PPH_10");
            listOfColumn.add("PDP_11");
            listOfColumn.add("PPH_11");
            listOfColumn.add("PDP_12");
            listOfColumn.add("PPH_12");
            listOfColumn.add("PDP_DIDIK");
            listOfColumn.add("PPH_DIDIK");
            listOfColumn.add("PDP_THR");
            listOfColumn.add("PPH_THR");
            listOfColumn.add("PDP_BONUS");
            listOfColumn.add("PPH_BONUS");
            listOfColumn.add("JBT_BONUS");
            listOfColumn.add("PDP_RAPEL");
            listOfColumn.add("PPH_RAPEL");
            listOfColumn.add("RPL1");
            listOfColumn.add("PDP_JUBEL");
            listOfColumn.add("PPH_LAIN");
            listOfColumn.add("JUMLAH GAJI");
            listOfColumn.add("");
            listOfColumn.add("TUNJ_OBT01");
            listOfColumn.add("PPH_OBT01");
            listOfColumn.add("TUNJ_OBT02");
            listOfColumn.add("PPH_OBT02");
            listOfColumn.add("TUNJ_OBT03");
            listOfColumn.add("PPH_OBT03");
            listOfColumn.add("TUNJ_OBT04");
            listOfColumn.add("PPH_OBT04");
            listOfColumn.add("TUNJ_OBT05");
            listOfColumn.add("PPH_OBT05");
            listOfColumn.add("TUNJ_OBT06");
            listOfColumn.add("PPH_OBT06");
            listOfColumn.add("TUNJ_OBT07");
            listOfColumn.add("PPH_OBT07");
            listOfColumn.add("TUNJ_OBT08");
            listOfColumn.add("PPH_OBT08");
            listOfColumn.add("TUNJ_OBT09");
            listOfColumn.add("PPH_OBT09");
            listOfColumn.add("TUNJ_OBT10");
            listOfColumn.add("PPH_OBT10");
            listOfColumn.add("TUNJ_OBT11");
            listOfColumn.add("PPH_OBT11");
            listOfColumn.add("TUNJ_OBT12");
            listOfColumn.add("PPH_OBT12");
            listOfColumn.add("JUMLAH OBT");
            listOfColumn.add("");
            listOfColumn.add("LBR_01");
            listOfColumn.add("LBR_02");
            listOfColumn.add("LBR_03");
            listOfColumn.add("LBR_04");
            listOfColumn.add("LBR_05");
            listOfColumn.add("LBR_06");
            listOfColumn.add("LBR_07");
            listOfColumn.add("LBR_08");
            listOfColumn.add("LBR_09");
            listOfColumn.add("LBR_10");
            listOfColumn.add("LBR_11");
            listOfColumn.add("LBR_12");
            listOfColumn.add("JML_LBR");
            listOfColumn.add("");
            listOfColumn.add("PDP(LBR)+OBT");
            listOfColumn.add("KAST_PER");
            listOfColumn.add("PAK_DINAS");
            RowData rowData = new RowData();
            List listOfCell = new ArrayList();
            for (int i = 0; i <= 82; ++i) {
                final CellDetail cellDetail = new CellDetail();
                cellDetail.setCellID(i);
                cellDetail.setValueCell(i + 1);
                cellDetail.setAlignmentCell(2);
                listOfCell.add(cellDetail);
            }
            rowData.setListOfCell(listOfCell);
            listOfData.add(rowData);
            for (final PayrollPendapatanPphDTO data2 : listData2) {
                rowData = new RowData();
                listOfCell = new ArrayList();
                CellDetail cellDetail = new CellDetail();
                cellDetail.setCellID(0);
                cellDetail.setValueCell(data2.getNip());
                cellDetail.setAlignmentCell(1);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(1);
                cellDetail.setValueCell(data2.getNama());
                cellDetail.setAlignmentCell(1);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(2);
                cellDetail.setValueCell(data2.getPdp01().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(3);
                cellDetail.setValueCell(data2.getPph01().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(4);
                cellDetail.setValueCell(data2.getPdp02().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(5);
                cellDetail.setValueCell(data2.getPph02().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(6);
                cellDetail.setValueCell(data2.getPdp03().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(7);
                cellDetail.setValueCell(data2.getPph03().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(8);
                cellDetail.setValueCell(data2.getPdp04().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(9);
                cellDetail.setValueCell(data2.getPph04().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(10);
                cellDetail.setValueCell(data2.getPdp05().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(11);
                cellDetail.setValueCell(data2.getPph05().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(12);
                cellDetail.setValueCell(data2.getPdp06().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(13);
                cellDetail.setValueCell(data2.getPph06().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(14);
                cellDetail.setValueCell(data2.getPdp07().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(15);
                cellDetail.setValueCell(data2.getPph07().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(16);
                cellDetail.setValueCell(data2.getPdp08().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(17);
                cellDetail.setValueCell(data2.getPph08().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(18);
                cellDetail.setValueCell(data2.getPdp09().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(19);
                cellDetail.setValueCell(data2.getPph09().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(20);
                cellDetail.setValueCell(data2.getPdp10().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(21);
                cellDetail.setValueCell(data2.getPph10().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(22);
                cellDetail.setValueCell(data2.getPdp11().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(23);
                cellDetail.setValueCell(data2.getPph11().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(24);
                cellDetail.setValueCell(data2.getPdp12().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(25);
                cellDetail.setValueCell(data2.getPph12().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(26);
                cellDetail.setValueCell(data2.getPdpDidik().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(27);
                cellDetail.setValueCell(data2.getPphDidik().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(28);
                cellDetail.setValueCell(data2.getPdpThr().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(29);
                cellDetail.setValueCell(data2.getPphThr().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(30);
                cellDetail.setValueCell(data2.getPdpBonus().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(31);
                cellDetail.setValueCell(data2.getPphBonus().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(32);
                cellDetail.setValueCell("");
                cellDetail.setValueCell(data2.getJbtBonus().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(33);
                cellDetail.setValueCell(data2.getPdpRapel().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(34);
                cellDetail.setValueCell(data2.getPphRapel().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(35);
                cellDetail.setValueCell("");
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(36);
                cellDetail.setValueCell(data2.getPdpJubel().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(37);
                cellDetail.setValueCell(data2.getPphLain().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(38);
                cellDetail.setValueCell(data2.getJumlahGaji().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(39);
                cellDetail.setValueCell(data2.getJumlahPphGaji().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(40);
                cellDetail.setValueCell(data2.getTunObt01().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(41);
                cellDetail.setValueCell(data2.getPphObt01().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(42);
                cellDetail.setValueCell(data2.getTunObt02().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(43);
                cellDetail.setValueCell(data2.getPphObt02().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(44);
                cellDetail.setValueCell(data2.getTunObt03().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(45);
                cellDetail.setValueCell(data2.getPphObt03().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(46);
                cellDetail.setValueCell(data2.getTunObt04().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(47);
                cellDetail.setValueCell(data2.getPphObt04().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(48);
                cellDetail.setValueCell(data2.getTunObt05().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(49);
                cellDetail.setValueCell(data2.getPphObt05().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(50);
                cellDetail.setValueCell(data2.getTunObt06().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(51);
                cellDetail.setValueCell(data2.getPphObt06().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(52);
                cellDetail.setValueCell(data2.getTunObt07().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(53);
                cellDetail.setValueCell(data2.getPphObt07().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(54);
                cellDetail.setValueCell(data2.getTunObt08().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(55);
                cellDetail.setValueCell(data2.getPphObt08().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(56);
                cellDetail.setValueCell(data2.getTunObt09().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(57);
                cellDetail.setValueCell(data2.getPphObt09().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(58);
                cellDetail.setValueCell(data2.getTunObt10().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(59);
                cellDetail.setValueCell(data2.getPphObt10().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(60);
                cellDetail.setValueCell(data2.getTunObt11().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(61);
                cellDetail.setValueCell(data2.getPphObt11().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(62);
                cellDetail.setValueCell(data2.getTunObt12().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(63);
                cellDetail.setValueCell(data2.getPphObt12().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(64);
                cellDetail.setValueCell(data2.getJumlahObt().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(65);
                cellDetail.setValueCell(data2.getJumlahPphObt().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(66);
                cellDetail.setValueCell(data2.getLbr01().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(67);
                cellDetail.setValueCell(data2.getLbr02().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(68);
                cellDetail.setValueCell(data2.getLbr03().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(69);
                cellDetail.setValueCell(data2.getLbr04().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(70);
                cellDetail.setValueCell(data2.getLbr05().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(71);
                cellDetail.setValueCell(data2.getLbr06().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(72);
                cellDetail.setValueCell(data2.getLbr07().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(73);
                cellDetail.setValueCell(data2.getLbr08().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(74);
                cellDetail.setValueCell(data2.getLbr09().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(75);
                cellDetail.setValueCell(data2.getLbr10().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(76);
                cellDetail.setValueCell(data2.getLbr11().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(77);
                cellDetail.setValueCell(data2.getLbr12().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(78);
                cellDetail.setValueCell(data2.getJumlahlbr().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(79);
                cellDetail.setValueCell("");
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(80);
                cellDetail.setValueCell(data2.getJumlahPdpLbrObt().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(81);
                cellDetail.setValueCell(data2.getKasPer().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                cellDetail = new CellDetail();
                cellDetail.setCellID(82);
                cellDetail.setValueCell(data2.getPakaianDinas().doubleValue());
                cellDetail.setAlignmentCell(3);
                listOfCell.add(cellDetail);
                rowData.setListOfCell(listOfCell);
                listOfData.add(rowData);
            }
        }
        final HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, periode, listOfColumn, listOfData, (List)null);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            wb.write((OutputStream)baos);
        }
        catch (IOException e) {
            Long logId = null;
            try {
                logId = this.payrollBoProxy.saveErrorMessage(e.getMessage(), "payrollAction.payrollReportExcelEspt");
            }
            catch (GeneralBOException e2) {
                PayrollAction.logger.error((Object)"[payrollAction.payrollReportExcelEspt] Error when downloading,", (Throwable)e2);
            }
            PayrollAction.logger.error((Object)("[payrollAction.payrollReportExcelEspt] Error when downloading data of function,[" + logId + "] Found problem when downloading data, please inform to your admin."), (Throwable)e);
            this.addActionError("Error, [code=" + logId + "] Found problem when downloding data, please inform to your admin.");
            return "error";
        }
        this.setExcelStream((InputStream)new ByteArrayInputStream(baos.toByteArray()));
        this.setContentDisposition("filename=\"" + filename + ".${documentFormat}\"");
        PayrollAction.logger.info((Object)"[payrollAction.payrollReportExcelEspt] end process <<<");
        return "downloadXlsRekapGaji";
    }

    //Perhitungan Hutang Pajak
    private BigDecimal hitungHutangPajak(BigDecimal pkp){
        BigDecimal hasil = new BigDecimal(0) ;

        if(pkp.compareTo(BigDecimal.valueOf(50000000)) <= 0){
            hasil = BigDecimal.valueOf(0.05).multiply(pkp);
        }else if(pkp.compareTo(BigDecimal.valueOf(50000000)) > 0 && pkp.compareTo(BigDecimal.valueOf(250000000)) <= 0){
            hasil = BigDecimal.valueOf(2500000).add((BigDecimal.valueOf(0.15).multiply(pkp.subtract(BigDecimal.valueOf(50000000))))) ;
        }else if(pkp.compareTo(BigDecimal.valueOf(250000000)) > 0 && pkp.compareTo(BigDecimal.valueOf(500000000)) <= 0){
            hasil = BigDecimal.valueOf(32500000).add((BigDecimal.valueOf(0.25).multiply((pkp.subtract(BigDecimal.valueOf(250000000))))));
        }else{
            hasil = BigDecimal.valueOf(95000000).add(BigDecimal.valueOf(0.3).multiply(pkp.subtract(BigDecimal.valueOf(500000000))));
        }

        return hasil.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void saveEditPayrollPphD(String payrollId,String tipePegawai, String pphGaji){
        Payroll newPayroll = new Payroll();
        newPayroll.setPayrollId(payrollId);
        newPayroll.setTipePegawai(tipePegawai);

        newPayroll.setPphGajiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pphGaji))));

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
        payrollBo.saveEditPayrollPphSessionDataUsingPayrollId(newPayroll);

    }

    public void saveEditDataTambahanD(String payrollId, String tipePegawai, String pphGaji){
        Payroll newPayroll = new Payroll();
        newPayroll.setPayrollId(payrollId);
        newPayroll.setTipePegawai(tipePegawai);
        newPayroll.setPphGajiNilai(BigDecimal.valueOf(Double.parseDouble(CommonUtil.removeCommaNumber(pphGaji))));

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        newPayroll.setLastUpdate(updateTime);
        newPayroll.setLastUpdateWho(userLogin);
        newPayroll.setAction("U");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");

        payrollBo.saveEditDataTambahanD(newPayroll);
    }
    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}