package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.mobileapi.model.TelemedicineMobile;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.antriantelemedic.model.StatusAntrianTelemedic;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Wednesday, 10/06/20 11:25
 */
public class TelemedicineController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(TelemedicineController.class);
    private TelemedicineMobile model = new TelemedicineMobile();
    private Collection<TelemedicineMobile> listOfTelemedic;
    private TelemedicBo telemedicBoProxy;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private NotifikasiFcmBo notifikasiFcmBoProxy;

    private String action;

    private String idPelayanan;
    private String idDokter;
    private String status;
    private String branchId;
    private String kodeBank;
    private String keluhan;

    private String idTele;
    private String idPasien;

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public NotifikasiFcmBo getNotifikasiFcmBoProxy() {
        return notifikasiFcmBoProxy;
    }

    public void setNotifikasiFcmBoProxy(NotifikasiFcmBo notifikasiFcmBoProxy) {
        this.notifikasiFcmBoProxy = notifikasiFcmBoProxy;
    }

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }


    public String getIdTele() {
        return idTele;
    }

    public void setIdTele(String idTele) {
        this.idTele = idTele;
    }

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HttpHeaders create() {
        logger.info("[TelemedicineController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        StatusAntrianTelemedic status = new StatusAntrianTelemedic();

        if (action.equalsIgnoreCase("saveAdd")) {
            ItSimrsAntrianTelemedicEntity bean = new ItSimrsAntrianTelemedicEntity();
            bean.setIdDokter(model.getIdDokter());
            bean.setIdJenisPeriksaPasien(model.getIdJenisPeriksaPasien());
            bean.setIdPelayanan(model.getIdPelayanan());
            bean.setIdPasien(model.getIdPasien());
            bean.setIdAsuransi(model.getIdAsuransi());
            bean.setFlagResep(model.getFlagResep());
            bean.setNoKartu(model.getNoKartu());
            bean.setCreatedDate(now);
            bean.setLastUpdate(now);
            bean.setCreatedWho(model.getIdPasien());
            bean.setLastUpdateWho(model.getIdPasien());
            bean.setKeluhan(model.getKeluhan());
            bean.setAction("C");
            bean.setFlag("Y");


            try {
                String msg = telemedicBoProxy.saveAdd(bean, branchId, kodeBank);
                model.setMessage(msg);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("editStatus")) {

            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setId(idTele);
            bean.setStatus(this.status);

            try {
                telemedicBoProxy.saveEdit(bean, branchId, "");
                if (this.status.equalsIgnoreCase("PD")) {

                    //KIRIM PUSH NOTIF JIKA STATUS MENJADI PD
                    List<NotifikasiFcm> result = new ArrayList<>();
                    NotifikasiFcm beanNotif = new NotifikasiFcm();
                    beanNotif.setUserId(idPasien);

                    result = notifikasiFcmBoProxy.getByCriteria(beanNotif);
                    FirebasePushNotif.sendNotificationFirebase(result.get(0).getTokenFcm(), "Telemedic", "Dokter Memanggil ...", "PD", result.get(0).getOs());
                }
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("getListAntrianSL")) {
            listOfTelemedic = new ArrayList<>();
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setIdDokter(idDokter);
            bean.setIdPelayanan(idPelayanan);
            bean.setStatus(status.getStatusAntrianOrder().get(0).getStatus());
            bean.setFlag("Y");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
               result = telemedicBoProxy.getListAntrianByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getListAntrianSL] Error, " + e.getMessage());
            }

            int i = 1;
            for(AntrianTelemedic item : result) {
                TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
                telemedicineMobile.setNoAntrian(String.valueOf(i));
                telemedicineMobile.setId(item.getId());
                telemedicineMobile.setIdAsuransi(item.getIdAsuransi());
                telemedicineMobile.setIdDokter(item.getIdDokter());
                telemedicineMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                telemedicineMobile.setIdPasien(item.getIdPasien());
                telemedicineMobile.setStatus(item.getStatus());
                telemedicineMobile.setFlagResep(item.getFlagResep());
                telemedicineMobile.setNoKartu(item.getNoKartu());
                telemedicineMobile.setIdPelayanan(item.getIdPelayanan());

                listOfTelemedic.add(telemedicineMobile);
            }
        }

        if (action.equalsIgnoreCase("getListAntrianAll")) {
            listOfTelemedic = new ArrayList<>();
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setIdDokter(idDokter);
            bean.setIdPelayanan(idPelayanan);
            bean.setFlag("Y");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
                result = telemedicBoProxy.getListAntrianByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getListAntrianSL] Error, " + e.getMessage());
            }

            int i = 1;
            for(AntrianTelemedic item : result) {
                    TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
                    telemedicineMobile.setNoAntrian(String.valueOf(i++));
                    telemedicineMobile.setId(item.getId());
                    telemedicineMobile.setIdAsuransi(item.getIdAsuransi());
                    telemedicineMobile.setIdDokter(item.getIdDokter());
                    telemedicineMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                    telemedicineMobile.setIdPasien(item.getIdPasien());
                    telemedicineMobile.setStatus(item.getStatus());
                    telemedicineMobile.setFlagResep(item.getFlagResep());
                    telemedicineMobile.setNoKartu(item.getNoKartu());
                    telemedicineMobile.setIdPelayanan(item.getIdPelayanan());
                    telemedicineMobile.setNamaDokter(item.getNamaDokter());
                    telemedicineMobile.setNamaPasien(item.getNamaPasien());
                    telemedicineMobile.setNamaPelayanan(item.getNamaPelayanan());
                    telemedicineMobile.setKetStatus(item.getKetStatus());
                    telemedicineMobile.setCreatedDate(item.getCreatedDate().toLocaleString());
                    telemedicineMobile.setKeluhan(item.getKeluhan());
                    telemedicineMobile.setBranchId(item.getBranchId());

                    listOfTelemedic.add(telemedicineMobile);

            }
        }

        if(action.equalsIgnoreCase("getListAntrianNonSL")) {
            listOfTelemedic = new ArrayList<>();
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setIdDokter(idDokter);
            bean.setIdPelayanan(idPelayanan);
            bean.setFlag("Y");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
                result = telemedicBoProxy.getListAntrianByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getListAntrianSL] Error, " + e.getMessage());
            }

            int i = 1;
            for(AntrianTelemedic item : result) {
                if (!item.getStatus().equalsIgnoreCase("SL")) {
                TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
                telemedicineMobile.setNoAntrian(String.valueOf(i++));
                telemedicineMobile.setId(item.getId());
                telemedicineMobile.setIdAsuransi(item.getIdAsuransi());
                telemedicineMobile.setIdDokter(item.getIdDokter());
                telemedicineMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                telemedicineMobile.setIdPasien(item.getIdPasien());
                telemedicineMobile.setStatus(item.getStatus());
                telemedicineMobile.setFlagResep(item.getFlagResep());
                telemedicineMobile.setNoKartu(item.getNoKartu());
                telemedicineMobile.setIdPelayanan(item.getIdPelayanan());
                telemedicineMobile.setNamaDokter(item.getNamaDokter());
                telemedicineMobile.setNamaPasien(item.getNamaPasien());
                telemedicineMobile.setNamaPelayanan(item.getNamaPelayanan());
                telemedicineMobile.setKetStatus(item.getKetStatus());

                listOfTelemedic.add(telemedicineMobile);
                }

            }
        }

        if (action.equalsIgnoreCase("checkTele")) {

            listOfTelemedic = new ArrayList<>();
            List<AntrianTelemedic> result = new ArrayList<>();

            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setId(idTele);

            try {
                result = telemedicBoProxy.getSearchByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.checkTele] Error, " + e.getMessage());
            }

            TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
            telemedicineMobile.setId(result.get(0).getId());
            telemedicineMobile.setIdAsuransi(result.get(0).getIdAsuransi());
            telemedicineMobile.setIdDokter(result.get(0).getIdDokter());
            telemedicineMobile.setIdJenisPeriksaPasien(result.get(0).getIdJenisPeriksaPasien());
            telemedicineMobile.setIdPasien(result.get(0).getIdPasien());
            telemedicineMobile.setStatus(result.get(0).getStatus());
            telemedicineMobile.setFlagResep(result.get(0).getFlagResep());
            telemedicineMobile.setNoKartu(result.get(0).getNoKartu());
            telemedicineMobile.setIdPelayanan(result.get(0).getIdPelayanan());
            telemedicineMobile.setNamaDokter(result.get(0).getNamaDokter());
            telemedicineMobile.setNamaPasien(result.get(0).getNamaPasien());
            telemedicineMobile.setNamaPelayanan(result.get(0).getNamaPelayanan());
            telemedicineMobile.setKetStatus(result.get(0).getKetStatus());
            telemedicineMobile.setFlagBayarResep(result.get(0).getFlagBayarResep());
            telemedicineMobile.setFlagBayarKonsultasi(result.get(0).getFlagBayarKonsultasi());

            listOfTelemedic.add(telemedicineMobile);

        }

        if(action.equalsIgnoreCase("checkNoAntrian")){
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setIdDokter(idDokter);
            bean.setIdPelayanan(idPelayanan);
            bean.setFlag("Y");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
                result = telemedicBoProxy.getListAntrianByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getListAntrianSL] Error, " + e.getMessage());
            }

            int i = 1;
            for(AntrianTelemedic item : result) {
                if(item.getId().equalsIgnoreCase(idTele)){
                    model.setNoAntrian(String.valueOf(i));
                    model.setJumlahAntrian(String.valueOf(result.size()));
                    break;
                }
                i++;
            }
        }

        if (action.equalsIgnoreCase("getDetailCheckup")) {
            HeaderCheckup beanHeader = new HeaderCheckup();
            beanHeader.setIdAntrianOnline(idTele);


            List<HeaderCheckup> resultCheckup = new ArrayList<>();
            List<HeaderDetailCheckup> resultDetailCheckup = new ArrayList<>();

            try {
               resultCheckup = checkupBoProxy.getByCriteria(beanHeader);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.getDetailCheckup] Error, " + e.getMessage());
            }

            HeaderDetailCheckup beanDetail = new HeaderDetailCheckup();
            beanDetail.setNoCheckup(resultCheckup.get(0).getNoCheckup());

            try {
                resultDetailCheckup = checkupDetailBoProxy.getByCriteria(beanDetail);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getDetailCheckup] Error, " + e.getMessage());
            }

            model.setNoCheckup(resultDetailCheckup.get(0).getNoCheckup());
            model.setIdDetailCheckup(resultDetailCheckup.get(0).getIdDetailCheckup());

        }

        logger.info("[TelemedicineController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();

    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public Object getModel() {
        return listOfTelemedic != null ? listOfTelemedic : model;
    }

    public void setModel(TelemedicineMobile model) {
        this.model = model;
    }

    public Collection<TelemedicineMobile> getListOfTelemedic() {
        return listOfTelemedic;
    }

    public void setListOfTelemedic(Collection<TelemedicineMobile> listOfTelemedic) {
        this.listOfTelemedic = listOfTelemedic;
    }

    public TelemedicBo getTelemedicBoProxy() {
        return telemedicBoProxy;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }
}
