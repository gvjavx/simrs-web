package com.neurix.simrs.mobileapi;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.antrianonline.bo.RegistrasiOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.model.RegistrasiOnline;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ValidationAwareSupport;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

/**
 * @author gondok
 * Tuesday, 19/11/19 8:45
 */
public class RegistrasiOnlineController extends ValidationAwareSupport implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(RegistrasiOnlineController.class);
    private RegistrasiOnline model = new RegistrasiOnline();
    private RegistrasiOnlineBo registrasiOnlineBoProxy;
    private Collection<RegistrasiOnline> listOfRegistrasiOnline = new ArrayList<RegistrasiOnline>();

    private String userId;
    private String noCheckupOnline;
    private String idPasien;
    private String nama;
    private String jenisKelamin;
    private String noKtp;
    private String tempatLahir;
    private String tglLahir;
    private String desaId;
    private String jalan;
    private String suku;
    private String agama;
    private String profesi;
    private String noTelp;
    private String idJenisPeriksaPasien;
    private String branchId;
    private String urlKtp;
    private String flag;
    private String action;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;
    private String valid;

    private File fileUploadKtp;

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public Object getModel() {
        return (listOfRegistrasiOnline != null ? listOfRegistrasiOnline : model);
    }

    public void setModel(RegistrasiOnline model) {
        this.model = model;
    }

    public RegistrasiOnlineBo getRegistrasiOnlineBoProxy() {
        return registrasiOnlineBoProxy;
    }

    public Collection<RegistrasiOnline> getListOfRegistrasiOnline() {
        return listOfRegistrasiOnline;
    }

    public void setListOfRegistrasiOnline(Collection<RegistrasiOnline> listOfRegistrasiOnline) {
        this.listOfRegistrasiOnline = listOfRegistrasiOnline;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNoCheckupOnline() {
        return noCheckupOnline;
    }

    public void setNoCheckupOnline(String noCheckupOnline) {
        this.noCheckupOnline = noCheckupOnline;
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

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getSuku() {
        return suku;
    }

    public void setSuku(String suku) {
        this.suku = suku;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public void setRegistrasiOnlineBoProxy(RegistrasiOnlineBo registrasiOnlineBoProxy) {
        this.registrasiOnlineBoProxy = registrasiOnlineBoProxy;
    }

    public File getFileUploadKtp() {
        return fileUploadKtp;
    }

    public void setFileUploadKtp(File fileUploadKtp) {
        this.fileUploadKtp = fileUploadKtp;
    }

    //simrs/mobileapi/registrasi/
    public HttpHeaders create() {
        logger.info("[RegistrasiOnlineController.create] start process POST / <<<");

        if (action.equalsIgnoreCase("registrasi")) {
            RegistrasiOnline registrasiOnline = new RegistrasiOnline();
            registrasiOnline.setNoCheckupOnline(noCheckupOnline);
            registrasiOnline.setNoKtp(noKtp);
            registrasiOnline.setNama(nama);
            registrasiOnline.setDesaId(Long.valueOf(desaId));
            registrasiOnline.setBranchId(branchId);
            registrasiOnline.setTempatLahir(tempatLahir);
            registrasiOnline.setProfesi(profesi);
            registrasiOnline.setJenisKelamin(jenisKelamin);
            registrasiOnline.setIdJenisPeriksaPasien(idJenisPeriksaPasien);
            registrasiOnline.setSuku(suku);
            registrasiOnline.setAgama(agama);
            registrasiOnline.setNoTelp(noTelp);
            registrasiOnline.setJalan(jalan);
            registrasiOnline.setValid("N");
            registrasiOnline.setTglLahir(CommonUtil.convertStringToDate(tglLahir));
            registrasiOnline.setStTglLahir(tglLahir);

            String ktpPath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD_KTP_PASIEN;

            if (fileUploadKtp != null) {
                if (fileUploadKtp.length() > 0 && fileUploadKtp.length() <= 15728640) {
                    Random random = new Random(System.currentTimeMillis());
                    Integer randomName = ((1 + random.nextInt(2)) * 1000000 + random.nextInt(1000000));
                    String fileNameKtp = "KTP_" + randomName + "_" + noKtp + CommonConstant.IMAGE_TYPE;
                    File fileCreate = new File(ktpPath, fileNameKtp);
                    try {
                        FileUtils.copyFile(fileUploadKtp, fileCreate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    registrasiOnline.setUrlKtp(fileNameKtp);
                }
            } else {
                registrasiOnline.setUrlKtp("");
            }

            try {
                model = registrasiOnlineBoProxy.saveAdd(registrasiOnline);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = registrasiOnlineBoProxy.saveErrorMessage(e.getMessage(), "registrasi online index");
                } catch (GeneralBOException el) {

                }
            }

            model.setAction("registrasi");
            listOfRegistrasiOnline.add(model);
        }

        if (action.equalsIgnoreCase("validasi")) {
            //TODO action ketika validasi akun
        }

        logger.info("[RegistrasiOnlineController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
