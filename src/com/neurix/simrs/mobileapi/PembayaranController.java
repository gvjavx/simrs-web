package com.neurix.simrs.mobileapi;

import com.neurix.akuntansi.master.pembayaran.bo.PembayaranBo;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.mobileapi.model.PembayaranMobile;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.File;
import java.io.IOException;
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

    private String action;

    private String idTele;
    private String keterangan;
    private File fileUploadBukti;
    private String fileName;

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

        if (action.equalsIgnoreCase("getPembayaranOnline")) {

            listOfPembayaran = new ArrayList<>();

            PembayaranOnline bean = new PembayaranOnline();
            bean.setIdAntrianTelemedic(idTele);

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

                pembayaranMobile.setCreatedDate(CommonUtil.addJamBayar(item.getCreatedDate()));

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
        }

        logger.info("[PembayaranController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }

}
