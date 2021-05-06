package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.mobileapi.model.ProfilPayroll;
import com.neurix.hris.transaksi.payroll.bo.PayrollBo;
import com.neurix.hris.transaksi.payroll.model.ItHrisPayrollEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollEntity;
import com.neurix.hris.transaksi.payroll.model.Payroll;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ViewPayrollController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(ViewPayrollController.class);

    private BiodataBo biodataBoProxy;
    private PayrollBo payrollBoProxy;

    private ProfilPayroll model = new ProfilPayroll();
    private List<ProfilPayroll> listOfPayroll = new ArrayList<>();

    private String nip;
    private String branchId;
    private String bulan;
    private String tahun;
    private String payrollId;

    public PayrollBo getPayrollBoProxy() {
        return payrollBoProxy;
    }

    public void setPayrollBoProxy(PayrollBo payrollBoProxy) {
        this.payrollBoProxy = payrollBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[ViewPayrollController.create] end process POST /activity <<<");

        List<Payroll> payroll = null;
        ItHrisPayrollEntity payrollEntity = new ItHrisPayrollEntity(); //RAKA-30APR2021==> merubah ItPayrollEntity ==> ItHrisPayrollEntity
        try {
            payroll = biodataBoProxy.viewPayrollSys(nip,branchId,bulan,tahun, payrollId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "ViewPayrollController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[ViewPayrollController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[ViewPayrollController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        try {
            payrollEntity = payrollBoProxy.getPayrollById(payrollId);
        } catch (GeneralBOException e){
            logger.error("[ViewPayrollController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e);
        }


//        BigDecimal gajiKotor = payrollEntity.getTotalA().add(payrollEntity.getTotalB().add(payrollEntity.getLainLain().add(payrollEntity.getTambahanLain())));
        BigDecimal gajiKotor = payrollEntity.getTotalA().add(payrollEntity.getTotalB());


        if(payroll != null){
            ProfilPayroll returnPayroll;
            for(Payroll personal : payroll){
                returnPayroll = new ProfilPayroll();
                returnPayroll.setPayrollId(personal.getPayrollId());
                returnPayroll.setBranchId(personal.getBranchId());
                returnPayroll.setBranchName(personal.getBranchName());
                returnPayroll.setNip(personal.getNip());
                returnPayroll.setBulan(personal.getBulan());
                returnPayroll.setTahun(personal.getTahun());
                returnPayroll.setGajiKotor(CommonUtil.numbericFormat(gajiKotor.subtract(payrollEntity.getTunjanganLembur()), "###,###"));
                returnPayroll.setPotongan(CommonUtil.numbericFormat(payrollEntity.getTotalC(), "###,###"));
                returnPayroll.setPphGaji(personal.getPphGaji());
                returnPayroll.setGajiBersih(personal.getTotalGajiBersih());
                returnPayroll.setRapel(personal.getTotalRapel());
                returnPayroll.setThr(personal.getTotalThr());
                returnPayroll.setPendidikan(personal.getTotalPendidikan());
                returnPayroll.setJasprod(personal.getTotalJasProd());
                returnPayroll.setPensiun(personal.getTotalPensiun());
                returnPayroll.setJubileum(personal.getTotalJubileum());
                returnPayroll.setIurDpPegawai(CommonUtil.numbericFormat(payrollEntity.getIuranDapenKary(), "###,###"));
                returnPayroll.setIurBpjsTkPegawai(CommonUtil.numbericFormat(payrollEntity.getIuranBpjsTkKary(), "###,###"));
                returnPayroll.setIurBpjsKsPegawai(CommonUtil.numbericFormat(payrollEntity.getIuranBpjsKsKary(), "###,###"));
                if (payrollEntity.getTunjanganPph() != null) {
                    returnPayroll.setPotPph(CommonUtil.numbericFormat(payrollEntity.getTunjanganPph(), "###,###"));
                } else  returnPayroll.setPotPph(CommonUtil.numbericFormat(new BigDecimal(0), "###,###"));
                returnPayroll.setTotalC(CommonUtil.numbericFormat(payrollEntity.getTotalC(), "###,###"));
                returnPayroll.setKopkar(CommonUtil.numbericFormat(payrollEntity.getIuranKopkar(), "###,###"));
                returnPayroll.setIuranSp(CommonUtil.numbericFormat(payrollEntity.getIuranSp(), "###,###"));
                returnPayroll.setIuranPIIKB(CommonUtil.numbericFormat(payrollEntity.getIuranPiikb(), "###,###"));
                returnPayroll.setBankBri(CommonUtil.numbericFormat(payrollEntity.getIuranBankBri(), "###,###"));
                returnPayroll.setBankMandiri(CommonUtil.numbericFormat(payrollEntity.getIuranBankMandiri(), "###,###"));
                returnPayroll.setInfaq(CommonUtil.numbericFormat(payrollEntity.getIuranInfaq(), "###,###"));
                returnPayroll.setPerkesDanObat(CommonUtil.numbericFormat(payrollEntity.getIuranPerkesDanObat(), "###,###"));
                returnPayroll.setListrik(CommonUtil.numbericFormat(payrollEntity.getIuranListrik(), "###,###"));
                returnPayroll.setIuranProfesi(CommonUtil.numbericFormat(payrollEntity.getIuranProfesi(), "###,###"));
                returnPayroll.setPotonganLain(CommonUtil.numbericFormat(payrollEntity.getIuranPotonganLain(), "###,###"));
                returnPayroll.setIurDpPerush(CommonUtil.numbericFormat(payrollEntity.getIuranDapenPers(), "###,###"));
                returnPayroll.setIurBpjsTkPerush(CommonUtil.numbericFormat(payrollEntity.getTotalIuranBpjsTkPers(), "###,###"));
                returnPayroll.setIurBpjsKsPerush(CommonUtil.numbericFormat(payrollEntity.getIuranBpjsKsPers(), "###,###"));
                returnPayroll.setTunjanganLemburNilai(CommonUtil.numbericFormat(payrollEntity.getTunjanganLembur(), "###,###"));

                listOfPayroll.add(returnPayroll);
            }
        }
        logger.info("[ViewPayrollController.create] end process POST /activity <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }


    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
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

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    @Override
    public Object getModel() {
        return (listOfPayroll !=null ? listOfPayroll : model);
    }
}
