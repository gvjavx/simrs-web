package com.neurix.simrs.mobileapi;

import com.neurix.akuntansi.master.pembayaran.bo.PembayaranBo;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.telemedic.bo.RekeningTelemedicBo;
import com.neurix.simrs.master.telemedic.model.RekeningTelemedic;
import com.neurix.simrs.mobileapi.model.PembayaranMobile;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author gondok
 * Thursday, 11/06/20 11:01
 */
public class PembayaranController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(PembayaranController.class);
    private PembayaranMobile model = new PembayaranMobile();
    private Collection<PembayaranMobile> listOfPembayaran;
    private VerifikatorPembayaranBo verifikatorPembayaranBoProxy;
    private RekeningTelemedicBo rekeningTelemedicBoProxy;
    private TelemedicBo telemedicBoProxy;

    private String action;

    private String idTele;
    private String keterangan;
    private File fileUploadBukti;
    private String fileName;

    private String jenisPengambilan;
    private String noTelp;
    private String alamat;
    private String bankCoa;
    private String desaId;

    private String idPasien;
    private String idPelayanan;
    private String branchId;

    private String lat;
    private String lon;

    private String idRekening;
    private String username;
    private String roleId;

    private String idPembayaranOnline;

    public String getIdPembayaranOnline() {
        return idPembayaranOnline;
    }

    public void setIdPembayaranOnline(String idPembayaranOnline) {
        this.idPembayaranOnline = idPembayaranOnline;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public RekeningTelemedicBo getRekeningTelemedicBoProxy() {
        return rekeningTelemedicBoProxy;
    }

    public void setRekeningTelemedicBoProxy(RekeningTelemedicBo rekeningTelemedicBoProxy) {
        this.rekeningTelemedicBoProxy = rekeningTelemedicBoProxy;
    }

    public String getIdRekening() {
        return idRekening;
    }

    public void setIdRekening(String idRekening) {
        this.idRekening = idRekening;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getJenisPengambilan() {
        return jenisPengambilan;
    }

    public void setJenisPengambilan(String jenisPengambilan) {
        this.jenisPengambilan = jenisPengambilan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public TelemedicBo getTelemedicBoProxy() {
        return telemedicBoProxy;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getBankCoa() {
        return bankCoa;
    }

    public void setBankCoa(String bankCoa) {
        this.bankCoa = bankCoa;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public File getFileUploadBukti() {
        return fileUploadBukti;
    }

    public void setFileUploadBukti(File fileUploadBukti) {
        this.fileUploadBukti = fileUploadBukti;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getIdTele() {
        return idTele;
    }

    public void setIdTele(String idTele) {
        this.idTele = idTele;
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

    @Override
    public Object getModel() {
        return listOfPembayaran != null ? listOfPembayaran : model;
    }

    public void setModel(PembayaranMobile model) {
        this.model = model;
    }

    public Collection<PembayaranMobile> getListOfPembayaran() {
        return listOfPembayaran;
    }

    public void setListOfPembayaran(Collection<PembayaranMobile> listOfPembayaran) {
        this.listOfPembayaran = listOfPembayaran;
    }

    public VerifikatorPembayaranBo getVerifikatorPembayaranBoProxy() {
        return verifikatorPembayaranBoProxy;
    }

    public void setVerifikatorPembayaranBoProxy(VerifikatorPembayaranBo verifikatorPembayaranBoProxy) {
        this.verifikatorPembayaranBoProxy = verifikatorPembayaranBoProxy;
    }

    public HttpHeaders create() {
        logger.info("[PembayaranController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (action.equalsIgnoreCase("getPembayaranOnline")) {

            listOfPembayaran = new ArrayList<>();

            PembayaranOnline bean = new PembayaranOnline();
            bean.setIdAntrianTelemedic(idTele);
            bean.setKeterangan(keterangan);

            List<PembayaranOnline> result = new ArrayList<>();

            try {
                result = verifikatorPembayaranBoProxy.getSearchByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PembayaranController.create] Error, " + e.getMessage());
            }

            for (PembayaranOnline item : result ) {
                PembayaranMobile pembayaranMobile = new PembayaranMobile();
                pembayaranMobile.setId(item.getId());
                pembayaranMobile.setIdAntrianTelemedic(item.getIdAntrianTelemedic());
                pembayaranMobile.setIdRiwayatTindakan(item.getIdRiwayatTindakan());
                pembayaranMobile.setKeterangan(item.getKeterangan());
                pembayaranMobile.setKodeBank(item.getKodeBank());
                pembayaranMobile.setNominal(CommonUtil.numbericFormat(item.getNominal(),"###,###"));
                pembayaranMobile.setApprovedFlag(item.getApprovedFlag());
                pembayaranMobile.setJenisPengambilan(item.getJenisPengambilan());
                pembayaranMobile.setIdItem(item.getIdItem());
                pembayaranMobile.setIdRekening(item.getIdRekening());
                pembayaranMobile.setUrlFotoBukti(item.getUrlFotoBukti());

                RekeningTelemedic rekeningTelemedic = new RekeningTelemedic();
                rekeningTelemedic.setIdRekening(item.getIdRekening());

                List<RekeningTelemedic> rekeningTelemedicList = new ArrayList<>();

                try {
                   rekeningTelemedicList = rekeningTelemedicBoProxy.getByCriteria(rekeningTelemedic);
                } catch (GeneralBOException e){
                    logger.error("[PembayaranController.create] Error, " + e.getMessage());
                }

                if (rekeningTelemedicList.size() == 1){
                    pembayaranMobile.setNoRekening(rekeningTelemedicList.get(0).getNoRekening());
                    pembayaranMobile.setNamaRekening(rekeningTelemedicList.get(0).getNamaRekening());
                }

                pembayaranMobile.setWaktuBayar(CommonUtil.addJamBayar(item.getCreatedDate()));

                listOfPembayaran.add(pembayaranMobile);
            }
        }

        if (action.equalsIgnoreCase("updateBuktiTransfer")) {

            if (fileUploadBukti != null) {
                fileName = idTele+".jpeg";
                File fileCreate = new File(CommonUtil.getPropertyParams("upload.folder")+CommonConstant.RESOURCE_PATH_BUKTI_TRANSFER, fileName);
                try {
                    FileUtils.copyFile(fileUploadBukti, fileCreate);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }


            try {
                verifikatorPembayaranBoProxy.updateBuktiTransfer(idTele, fileName, keterangan);
                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("[PembayaranController.create] Error, " + e.getMessage());
            }

            telemedicBoProxy.createNotifikasiAdmin(idTele, "tele", branchId, username, now, username + "telah upload bukti transfer " + keterangan);
        }

        if (action.equalsIgnoreCase("saveEditPembayaranResep")) {
            ItSimrsPembayaranOnlineEntity newPembayaran;

            PembayaranOnline bean = new PembayaranOnline();
            bean.setIdAntrianTelemedic(idTele);
            bean.setKeterangan("resep");

            List<ItSimrsPembayaranOnlineEntity> listEntity = new ArrayList<>();

            try {
               listEntity = verifikatorPembayaranBoProxy.getSearchEntityByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PembayaranController.create] Error, " + e.getMessage());
            }

            if(listEntity.size() > 0) {
                newPembayaran = listEntity.get(0);

                newPembayaran.setKodeBank(bankCoa);
                newPembayaran.setLastUpdate(now);
                newPembayaran.setLastUpdateWho(idPasien);
                newPembayaran.setIdRekening(idRekening);

                try {
                    verifikatorPembayaranBoProxy.saveEdit(newPembayaran);
                    model.setMessage("Success");
                } catch (GeneralBOException e) {
                    logger.error("[PembayaranController.create] Error, " + e.getMessage());
                }
            }


            if (keterangan.equalsIgnoreCase("all")) {
                bean.setIdAntrianTelemedic(idTele);
                bean.setKeterangan("konsultasi");

                try {
                    listEntity = verifikatorPembayaranBoProxy.getSearchEntityByCriteria(bean);
                } catch (GeneralBOException e) {
                    logger.error("[PembayaranController.create] Error, " + e.getMessage());
                }

                if(listEntity.size() > 0) {
                    newPembayaran = listEntity.get(0);

                    newPembayaran.setKodeBank(bankCoa);
                    newPembayaran.setIdRekening(idRekening);
                    newPembayaran.setLastUpdate(now);
                    newPembayaran.setLastUpdateWho(idPasien);
                    try {
                        verifikatorPembayaranBoProxy.saveEdit(newPembayaran);
                    } catch (GeneralBOException e) {
                        logger.error("[PembayaranController.create] Error, " + e.getMessage());
                    }

                    try {
                        telemedicBoProxy.updateBankCoa(idTele, bankCoa);
                        model.setMessage("Success");
                    } catch (GeneralBOException e){
                        logger.error("[PembayaranController.create] Error, " + e.getMessage());
                    }
                }

            }

        }

        if (action.equalsIgnoreCase("updateWaktuBayar")) {
            listOfPembayaran = new ArrayList<>();

            PembayaranOnline bean = new PembayaranOnline();
            bean.setIdAntrianTelemedic(idTele);
            bean.setKeterangan(keterangan);

            List<ItSimrsPembayaranOnlineEntity> result = new ArrayList<>();

            try {
                result = verifikatorPembayaranBoProxy.getSearchEntityByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PembayaranController.create] Error, " + e.getMessage());
            }

            if(result.get(0).getWaktuBayar() == null) {
                result.get(0).setWaktuBayar(now);
                try {
                    verifikatorPembayaranBoProxy.saveEdit(result.get(0));
                } catch (GeneralBOException e) {
                    logger.error("[PembayaranController.create] Error, " + e.getMessage());
                }
                model.setMessage("Success");
            } else {
                model.setMessage("Failed");
            }
        }

        logger.info("[PembayaranController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }

}
