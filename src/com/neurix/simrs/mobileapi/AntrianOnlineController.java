package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.mobileapi.model.AntrianMobile;
import com.neurix.simrs.mobileapi.model.CheckupMobile;
import com.neurix.simrs.mobileapi.model.HeaderCheckupMobile;
import com.neurix.simrs.transaksi.antrianonline.bo.AntrianOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Thursday, 21/11/19 12:32
 */
public class AntrianOnlineController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(AntrianOnlineController.class);
    private AntrianMobile model = new AntrianMobile();
    private Collection<AntrianMobile> listOfAntrianOnline = new ArrayList<>();
    private Collection<HeaderCheckupMobile> listOfHeaderCheckup = new ArrayList<>();
    private AntrianOnlineBo antrianOnlineBoProxy;
    private CheckupBo checkupBoProxy;

    private String idAntrianOnline;
    private String noCheckupOnline;
    private String idPelayanan;
    private String idDokter;
    private String createdDate;
    private String branchId;
    private String jamAwal;
    private String jamAkhir;
    private String tglCheckup;
    private String action;

    private String idDetailCheckup;

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public Collection<HeaderCheckupMobile> getListOfHeaderCheckup() {
        return listOfHeaderCheckup;
    }

    public void setListOfHeaderCheckup(Collection<HeaderCheckupMobile> listOfHeaderCheckup) {
        this.listOfHeaderCheckup = listOfHeaderCheckup;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public void setModel(AntrianMobile model) {
        this.model = model;
    }

    public Collection<AntrianMobile> getListOfAntrianOnline() {
        return listOfAntrianOnline;
    }

    public void setListOfAntrianOnline(Collection<AntrianMobile> listOfAntrianOnline) {
        this.listOfAntrianOnline = listOfAntrianOnline;
    }

    public String getTglCheckup() {
        return tglCheckup;
    }

    public void setTglCheckup(String tglCheckup) {
        this.tglCheckup = tglCheckup;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getIdAntrianOnline() {
        return idAntrianOnline;
    }

    public void setIdAntrianOnline(String idAntrianOnline) {
        this.idAntrianOnline = idAntrianOnline;
    }

    public String getNoCheckupOnline() {
        return noCheckupOnline;
    }

    public void setNoCheckupOnline(String noCheckupOnline) {
        this.noCheckupOnline = noCheckupOnline;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public static Logger getLogger() {
        return logger;
    }

    public AntrianOnlineBo getAntrianOnlineBoProxy() {
        return antrianOnlineBoProxy;
    }

    public void setAntrianOnlineBoProxy(AntrianOnlineBo antrianOnlineBoProxy) {
        this.antrianOnlineBoProxy = antrianOnlineBoProxy;
    }

    @Override
    public Object getModel() {
        switch (action){
            case "show":
                return listOfAntrianOnline;
            case "antrianAll":
                return listOfHeaderCheckup;
            default: return model;
        }

    }

    public HttpHeaders create() {
        logger.info("[AntrianOnlineController.create] start process POST / <<<");

        AntianOnline antianOnline = new AntianOnline();
        antianOnline.setIdPelayanan(idPelayanan);
        antianOnline.setIdDokter(idDokter);
        antianOnline.setNoCheckupOnline(noCheckupOnline);
        antianOnline.setJamAkhir(jamAkhir);
        antianOnline.setJamAwal(jamAwal);
        antianOnline.setTglCheckup(tglCheckup);
        antianOnline.setBranchId(branchId);

        if (action.equalsIgnoreCase("tambah")) {
            try {
                antrianOnlineBoProxy.saveAdd(antianOnline);
            } catch (GeneralBOException e) {

            }
        }

        if (action.equalsIgnoreCase("show")) {
            List<AntianOnline> result = new ArrayList<>();
            try {
                result = antrianOnlineBoProxy.getAntrianByCriteria(idPelayanan, idDokter, noCheckupOnline, CommonUtil.convertStringToDate(tglCheckup), jamAwal, jamAkhir, branchId);
            } catch (GeneralBOException e) {

            }

            for (AntianOnline item : result) {
                AntrianMobile antrian = new AntrianMobile();
                antrian.setIdAntrianOnline(item.getIdAntrianOnline());
                antrian.setJumlahAntrian(item.getJumlahAntrian());
                antrian.setBranchId(item.getBranchId());
                antrian.setBranchName(item.getBranchName());
                antrian.setIdDokter(item.getIdDokter());
                antrian.setNamaDokter(item.getNamaDokter());
                antrian.setIdPelayanan(item.getIdPelayanan());
                antrian.setNamaPelayanan(item.getNamaPelayanan());
                antrian.setNama(item.getNama());
                antrian.setJamAwal(item.getJamAwal());
                antrian.setJamAkhir(item.getJamAkhir());
                antrian.setNoAntrian(item.getNoAntrian());
                antrian.setNoCheckupOnline(item.getNoCheckupOnline());
                antrian.setNoCheckup(item.getNoCheckup());
                antrian.setIdDetailCheckup(item.getIdDetailCheckup());
                antrian.setTglCheckup(item.getTglCheckup());

                listOfAntrianOnline.add(antrian);
            }

        }

        if (action.equalsIgnoreCase("antrianAll")){
            List<HeaderCheckup> result = new ArrayList<>();

            try {
                result = checkupBoProxy.getListAntrian(branchId, idPelayanan);
            } catch (GeneralBOException e) {

            }

            if (result.size() > 0){
                for (HeaderCheckup item :result){
                    HeaderCheckupMobile headerCheckupMobile = new HeaderCheckupMobile();
                    headerCheckupMobile.setIdPasien(item.getIdPasien());
                    headerCheckupMobile.setNama(item.getNama());
                    headerCheckupMobile.setNamaDesa(item.getNamaDesa());
                    headerCheckupMobile.setNamaPelayanan(item.getNamaPelayanan());
                    headerCheckupMobile.setNamaKecamatan(item.getNamaKecamatan());
                    headerCheckupMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    headerCheckupMobile.setNoCheckup(item.getNoCheckup());

                    listOfHeaderCheckup.add(headerCheckupMobile);
                }
            }
        }

        logger.info("[AntrianOnlineController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
