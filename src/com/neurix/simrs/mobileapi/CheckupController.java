package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.mobileapi.model.CheckupMobile;
import com.neurix.simrs.mobileapi.model.HeaderCheckupMobile;
import com.neurix.simrs.mobileapi.model.HeaderDetailCheckupMobile;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.File;
import java.util.*;

/**
 * @author gondok
 * Friday, 24/01/20 11:08
 */
public class CheckupController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(CheckupController.class);
    private CheckupMobile model = new CheckupMobile();
    private Collection<CheckupMobile> listOfCheckup = new ArrayList<>();
    private Collection<HeaderDetailCheckupMobile> listOfHeaderCheckup = new ArrayList<>();
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;


    private String idDetailCheckup;
    private String idPasien;
    private String nama;
    private String idPoli;
    private String idStatusPasien;
    private String tglMasuk;
    private String branchId;
    private String action;

    private File fileUploadTtd;
    private String fileNameTtd;

    public String getFileNameTtd() {
        return fileNameTtd;
    }

    public void setFileNameTtd(String fileNameTtd) {
        this.fileNameTtd = fileNameTtd;
    }

    public Collection<HeaderDetailCheckupMobile> getListOfHeaderCheckup() {
        return listOfHeaderCheckup;
    }

    public void setListOfHeaderCheckup(Collection<HeaderDetailCheckupMobile> listOfHeaderCheckup) {
        this.listOfHeaderCheckup = listOfHeaderCheckup;
    }

    public File getFileUploadTtd() {
        return fileUploadTtd;
    }

    public void setFileUploadTtd(File fileUploadTtd) {
        this.fileUploadTtd = fileUploadTtd;
    }

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdPoli() {
        return idPoli;
    }

    public void setIdPoli(String idPoli) {
        this.idPoli = idPoli;
    }

    public String getIdStatusPasien() {
        return idStatusPasien;
    }

    public void setIdStatusPasien(String idStatusPasien) {
        this.idStatusPasien = idStatusPasien;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(CheckupMobile model) {
        this.model = model;
    }

    public Collection<CheckupMobile> getListOfCheckup() {
        return listOfCheckup;
    }

    public void setListOfCheckup(Collection<CheckupMobile> listOfCheckup) {
        this.listOfCheckup = listOfCheckup;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    @Override
    public Object getModel() {
        switch (action) {
            case "search":
                return listOfCheckup;
            case "dataPasien":
                return listOfHeaderCheckup;
            default:
                return model;
        }
    }

    public HttpHeaders create() {
        logger.info("[CheckupController.create] start process POST / <<<");

        if (action.equalsIgnoreCase("search")) {
            List<HeaderDetailCheckup> result = new ArrayList<>();

            HeaderDetailCheckup bean = new HeaderDetailCheckup();
            bean.setIdPasien(idPasien);
            bean.setNamaPasien(nama);
            bean.setIdPelayanan(idPoli);
            bean.setStatusPeriksa(idStatusPasien);
            bean.setTglCekup(CommonUtil.convertStringToDate(tglMasuk));
            bean.setBranchId(branchId);
            bean.setFlag("Y");

            try {
               result = checkupDetailBoProxy.getSearchRawatJalan(bean);
            } catch (GeneralBOException e) {
                logger.error("CheckupController.create] Error when get rawat jalan",e);
            }

            if (result != null & result.size() > 0) {
                for (HeaderDetailCheckup item : result) {
                    CheckupMobile checkupMobile = new CheckupMobile();
                    checkupMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    checkupMobile.setNamaPelayanan(item.getNamaPelayanan());
                    checkupMobile.setJenisPeriksaPasien(item.getJenisPeriksaPasien());
                    checkupMobile.setNoCheckup(item.getNoCheckup());
                    checkupMobile.setIdPasien(item.getIdPasien());
                    checkupMobile.setNamaPasien(item.getNamaPasien());
                    checkupMobile.setAlamat(item.getAlamat());
                    checkupMobile.setStatusPeriksa(item.getStatusPeriksa());
                    checkupMobile.setStatusBayar(item.getStatusBayar());



                    listOfCheckup.add(checkupMobile);


                }
            }
        } else if (action.equalsIgnoreCase("dataPasien")){

            HeaderCheckup result = new HeaderCheckup();

            try {
               result = checkupBoProxy.getDataDetailPasien(idDetailCheckup);
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get rawat jalan",e);

            }

            if (result != null) {
               HeaderDetailCheckupMobile headerDetailCheckupMobile = new HeaderDetailCheckupMobile();
               headerDetailCheckupMobile.setNoCheckup(result.getNoCheckup());
               headerDetailCheckupMobile.setIdPasien(result.getIdPasien());
               headerDetailCheckupMobile.setNamaPasien(result.getNama());
               headerDetailCheckupMobile.setJenisKelamin(result.getJenisKelamin());
               headerDetailCheckupMobile.setNik(result.getNoKtp());
               headerDetailCheckupMobile.setTempatLahir(result.getTempatLahir());
               headerDetailCheckupMobile.setIdJenisPeriksaPasien(result.getIdJenisPeriksaPasien());
               headerDetailCheckupMobile.setIdDetailCheckup(result.getIdDetailCheckup());
               headerDetailCheckupMobile.setNamaPelayanan(result.getNamaPelayanan());
               headerDetailCheckupMobile.setNoSep(result.getNoSep());
               headerDetailCheckupMobile.setTglLahir(result.getStTglLahir());

               listOfHeaderCheckup.add(headerDetailCheckupMobile);
            }
        } else if (action.equalsIgnoreCase("uploadTtd")){

            List<HeaderDetailCheckup> result = new ArrayList<>();

            HeaderDetailCheckup bean = new HeaderDetailCheckup();
            bean.setIdDetailCheckup(idDetailCheckup);

            try {
                result = checkupDetailBoProxy.getByCriteria(bean);

            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get checkup detail by criteria",e);
            }

            if (result != null && result.size() > 0){
                if (fileUploadTtd != null) {
                    //TODO
                }
            }

        }

        logger.info("[CheckupController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
