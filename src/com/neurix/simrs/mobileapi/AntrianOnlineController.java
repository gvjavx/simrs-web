package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antrianonline.bo.AntrianOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author gondok
 * Thursday, 21/11/19 12:32
 */
public class AntrianOnlineController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(AntrianOnlineController.class);
    private AntianOnline model = new AntianOnline();
    private Collection<AntianOnline> listOfAntrianOnline = new ArrayList<>();
    private AntrianOnlineBo antrianOnlineBoProxy;

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

    public Collection<AntianOnline> getListOfAntrianOnline() {
        return listOfAntrianOnline;
    }

    public void setListOfAntrianOnline(Collection<AntianOnline> listOfAntrianOnline) {
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

    public void setModel(AntianOnline model) {
        this.model = model;
    }

    public AntrianOnlineBo getAntrianOnlineBoProxy() {
        return antrianOnlineBoProxy;
    }

    public void setAntrianOnlineBoProxy(AntrianOnlineBo antrianOnlineBoProxy) {
        this.antrianOnlineBoProxy = antrianOnlineBoProxy;
    }

    @Override
    public Object getModel() {
       return (listOfAntrianOnline != null ? listOfAntrianOnline : model);
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
            try {
                listOfAntrianOnline = antrianOnlineBoProxy.getAntrianByCriteria(idPelayanan, idDokter, noCheckupOnline, tglCheckup, jamAwal, jamAkhir);
            } catch (GeneralBOException e) {

            }
        }

        logger.info("[AntrianOnlineController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
