package com.neurix.hris.mobileapi;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.ijin.bo.IjinBo;
import com.neurix.hris.master.ijin.model.Ijin;
import com.neurix.hris.mobileapi.model.Branch;
import com.neurix.hris.mobileapi.model.Dispensasi;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @author gondok
 * Wednesday, 20/02/19 13:32
 */
public class DispensasiController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(DispensasiController.class);

    private IjinBo ijinBoProxy;
    private IjinKeluarBo ijinKeluarBoProxy;
    private NotifikasiBo notifikasiBoProxy;
    private List<Dispensasi> listOfDispensasi = null;
    private Dispensasi model = new Dispensasi();

    private String id;
    private String statusApprove;

    private String gender;

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private File fileSurat;
    private String fileNameSurat;

    public File getFileSurat() {
        return fileSurat;
    }

    public void setFileSurat(File fileSurat) {
        this.fileSurat = fileSurat;
    }

    public String getFileNameSurat() {
        return fileNameSurat;
    }

    public void setFileNameSurat(String fileNameSurat) {
        this.fileNameSurat = fileNameSurat;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIjinBoProxy(IjinBo ijinBoProxy) {
        this.ijinBoProxy = ijinBoProxy;
    }

    public void setIjinKeluarBoProxy(IjinKeluarBo ijinKeluarBoProxy) {
        this.ijinKeluarBoProxy = ijinKeluarBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public HttpHeaders create() {

        logger.info("[DispensasiController.index] end process POST /branch <<<");

        if  (action.equalsIgnoreCase("edit")) {
            com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar search = new IjinKeluar();
            search.setIjinKeluarId(model.getIjinKeluarId());
            search.setFlag("Y");
            search.setFrom("ijinKeluar");
            search.setMobile(true);

            List<IjinKeluar> listOfIjinKeluar = null;

            try {
                listOfIjinKeluar = ijinKeluarBoProxy.getByCriteria(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "DispensasiController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            String path = null;
            if (fileSurat != null) {
                String idSuratDokter = ijinKeluarBoProxy.getNextSuratDokterId();

                String fileNamePhoto = idSuratDokter+"_"+fileNameSurat+".jpg";
                String filePath = CommonConstant.RESOURCE_PATH_USER_UPLOAD_SURAT_DOKTER;
                File fileCreate = new File(filePath, fileNamePhoto);
                path = filePath+fileNamePhoto;
                try {
                    FileUtils.copyFile(fileSurat, fileCreate);
                }catch (IOException e){
                    e.printStackTrace();
                }
                listOfIjinKeluar.get(0).setFileType("IMG");
                listOfIjinKeluar.get(0).setFilePath(path);
                listOfIjinKeluar.get(0).setUploadFile(fileNamePhoto);

            }
            listOfIjinKeluar.get(0).setLastUpdate(new Timestamp(System.currentTimeMillis()));
            listOfIjinKeluar.get(0).setLastUpdateWho(model.getNamaPegawai());
            listOfIjinKeluar.get(0).setFlag("Y");
            listOfIjinKeluar.get(0).setAction("U");
            listOfIjinKeluar.get(0).setDispenLahir(true);
            listOfIjinKeluar.get(0).setStTglMelahirkan(model.getTanggalKelahiran());
            listOfIjinKeluar.get(0).setStTanggalAkhir(model.getTanggalAkhir());
            listOfIjinKeluar.get(0).setTanggalAkhir(CommonUtil.convertToDate(model.getTanggalAkhir()));
            listOfIjinKeluar.get(0).setLamaIjinBaru(model.getLamaIjin());

            try{
                ijinKeluarBoProxy.saveEdit(listOfIjinKeluar.get(0));
            } catch (GeneralBOException e) {
                logger.error("[DispensasiController.saveEdit] Error when saving dispen,", e);
            }

        } else if (action.equalsIgnoreCase("cekHajiZiarah")) {
            String result = "";
            try {
                result  = ijinKeluarBoProxy.cekStatusIjin(model.getNip());
            } catch (GeneralBOException e) {
                logger.error("[DispensasiController.saveEdit] Error when saving dispen,", e);
            }

            model.setMessage(result);

        } else {
            List<Ijin> modelIjin = null;
            try {
                Ijin search = new Ijin();
                search.setFlag("Y");
                search.setGender(gender);

                modelIjin = ijinBoProxy.getByCriteria(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "DispensasiController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            listOfDispensasi = new ArrayList<>();

            if(modelIjin != null){
                for(Ijin item : modelIjin){
                    Dispensasi dispensasi = new Dispensasi();
                    dispensasi.setNamaIjin(item.getIjinName());
                    dispensasi.setMaksimalIjin(item.getJumlahIjin());
                    dispensasi.setIjinId(item.getIjinId());
                    dispensasi.setGender(item.getGender());

                    listOfDispensasi.add(dispensasi);
                }
            }
        }

        logger.info("[DispensasiController.index] end process POST /branch <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders update() {
        logger.info("[DispensasiController.update] end process POST /pengajuancuti/{id} <<<");

        Dispensasi result = new Dispensasi();
        listOfDispensasi = new ArrayList<>();
        result.setActionError("");

        try {

            IjinKeluar ijinKeluar = new IjinKeluar();
            ijinKeluar.setIjinId(model.getIjinId());
            ijinKeluar.setIjinName(model.getIjinName());
            ijinKeluar.setLamaIjin(model.getLamaIjin());
            ijinKeluar.setNip(model.getNip());
            ijinKeluar.setNamaPegawai(model.getNamaPegawai());
            ijinKeluar.setKeterangan(model.getKeterangan());
            ijinKeluar.setApprovalFlag("N");
            ijinKeluar.setPositionId(model.getPositionId());
            ijinKeluar.setGolonganId(model.getGolonganId());
            ijinKeluar.setUnitId(model.getUnit());
            ijinKeluar.setStTanggalAwal(model.getTanggalAwal());
            ijinKeluar.setStTanggalAkhir(model.getTanggalAkhir());
            ijinKeluar.setDivisiId(model.getDivisi());
            ijinKeluar.setTanggalAwal(CommonUtil.convertToDate(model.getTanggalAwal()));
            ijinKeluar.setTanggalAkhir(CommonUtil.convertToDate(model.getTanggalAkhir()));

            ijinKeluar.setSelf("Y");
            ijinKeluar.setFlag("Y");
            ijinKeluar.setAction("C");
            ijinKeluar.setCreatedWho(model.getNamaPegawai());
            ijinKeluar.setLastUpdateWho(model.getNamaPegawai());
            ijinKeluar.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            ijinKeluar.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

            ijinKeluar.setOs(model.getOs());

            String path = null;
            if (fileSurat != null) {
                String idSuratDokter = ijinKeluarBoProxy.getNextSuratDokterId();

                String fileNamePhoto = idSuratDokter+"_"+fileNameSurat+".jpg";
                String filePath = CommonConstant.RESOURCE_PATH_USER_UPLOAD_SURAT_DOKTER;
                File fileCreate = new File(filePath, fileNamePhoto);
                path = filePath+fileNamePhoto;
                try {
                    FileUtils.copyFile(fileSurat, fileCreate);
                }catch (IOException e){
                    e.printStackTrace();
                }
                ijinKeluar.setFileType("IMG");
                ijinKeluar.setFilePath(path);
                ijinKeluar.setUploadFile(fileNamePhoto);
            }


            List<Notifikasi> notifikasiList = ijinKeluarBoProxy.saveAddIjinKeluar(ijinKeluar);

            for ( Notifikasi notifikasi : notifikasiList){
                notifikasiBoProxy.sendNotif(notifikasi);
            }


        } catch (GeneralBOException e) {
            result.setActionError(e.getMessage());
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "DispensasiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        listOfDispensasi.add(result);


        logger.info("[DispensasiController.update] end process POST /pengajuancuti/{id} <<<");

        return new DefaultHttpHeaders("update").disableCaching();
    }

    public String status() {
        logger.info("[DispensasiController.update] end process POST /dispensasi/{nip}/status <<<");
        String nip = getId();

        com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar search = new IjinKeluar();
        search.setNip(nip);
        search.setFlag("Y");
        search.setFrom("ijinKeluar");
        search.setMobile(true);

        List<IjinKeluar> listOfIjinKeluar = null;

        try {
            listOfIjinKeluar = ijinKeluarBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "DispensasiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        int size = listOfIjinKeluar.size();
        if(statusApprove.equals("Y")) {
            statusApprove = "Y";
        } else {
            statusApprove = "N";
        }
        listOfDispensasi = new ArrayList<>();

        if(listOfIjinKeluar != null){
            for(IjinKeluar dispensasi : listOfIjinKeluar){
                if(dispensasi.getApprovalFlag() == null) {
                    Dispensasi model = new Dispensasi();
                    model.setDivisi(dispensasi.getDivisiId());
                    model.setDivisiName(dispensasi.getDivisiName());
                    model.setIjinKeluarId(dispensasi.getIjinKeluarId());
                    model.setIjinId(dispensasi.getIjinId());
                    model.setIjinName(dispensasi.getIjinName());
                    model.setLamaIjin(dispensasi.getLamaIjin());
                    model.setNip(dispensasi.getNip());
                    model.setNamaPegawai(dispensasi.getNamaPegawai());
                    model.setKeterangan(dispensasi.getKeterangan());
                    model.setTanggalAwal(dispensasi.getStTanggalAwal());
                    model.setTanggalAkhir(dispensasi.getStTanggalAkhir());
                    model.setUnitName(dispensasi.getUnitName());
                    model.setUnit(dispensasi.getUnitId());
                    model.setPositionId(dispensasi.getPositionId());
                    model.setPositionName(dispensasi.getPositionName());
                    model.setGolonganId(dispensasi.getGolonganId());
                    model.setGolonganName(dispensasi.getGolonganName());
                    model.setNamaIjin(dispensasi.getIjinName());

//                    listOfDispensasi.add(model);

                } else if(dispensasi.getApprovalFlag().equals("Y") && statusApprove.equals("Y")){

                    Dispensasi model = new Dispensasi();
                    model.setDivisi(dispensasi.getDivisiId());
                    model.setDivisiName(dispensasi.getDivisiName());
                    model.setIjinKeluarId(dispensasi.getIjinKeluarId());
                    model.setIjinId(dispensasi.getIjinId());
                    model.setIjinName(dispensasi.getIjinName());
                    model.setLamaIjin(dispensasi.getLamaIjin());
                    model.setNip(dispensasi.getNip());
                    model.setNamaPegawai(dispensasi.getNamaPegawai());
                    model.setKeterangan(dispensasi.getKeterangan());
                    model.setTanggalAwal(dispensasi.getStTanggalAwal());
                    model.setTanggalAkhir(dispensasi.getStTanggalAkhir());
                    model.setUnitName(dispensasi.getUnitName());
                    model.setUnit(dispensasi.getUnitId());
                    model.setPositionId(dispensasi.getPositionId());
                    model.setPositionName(dispensasi.getPositionName());
                    model.setGolonganId(dispensasi.getGolonganId());
                    model.setGolonganName(dispensasi.getGolonganName());
                    model.setNamaIjin(dispensasi.getIjinName());

                    listOfDispensasi.add(model);

                } else if (dispensasi.getApprovalFlag().equals("N") && statusApprove.equals("N")){
                    Dispensasi model = new Dispensasi();
                    model.setDivisi(dispensasi.getDivisiId());
                    model.setDivisiName(dispensasi.getDivisiName());
                    model.setIjinKeluarId(dispensasi.getIjinKeluarId());
                    model.setIjinId(dispensasi.getIjinId());
                    model.setIjinName(dispensasi.getIjinName());
                    model.setLamaIjin(dispensasi.getLamaIjin());
                    model.setNip(dispensasi.getNip());
                    model.setNamaPegawai(dispensasi.getNamaPegawai());
                    model.setKeterangan(dispensasi.getKeterangan());
                    model.setTanggalAwal(dispensasi.getStTanggalAwal());
                    model.setTanggalAkhir(dispensasi.getStTanggalAkhir());
                    model.setUnitName(dispensasi.getUnitName());
                    model.setUnit(dispensasi.getUnitId());
                    model.setPositionId(dispensasi.getPositionId());
                    model.setPositionName(dispensasi.getPositionName());
                    model.setGolonganId(dispensasi.getGolonganId());
                    model.setGolonganName(dispensasi.getGolonganName());
                    model.setNamaIjin(dispensasi.getIjinName());

                    listOfDispensasi.add(model);
                }
            }
        }

        logger.info("[DispensasiController.create] end process POST /dispensasi/{nip}/status <<<");
        return "success";
    }

    @Override
    public Object getModel() {
        return (listOfDispensasi != null ? listOfDispensasi : model);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusApprove() {
        return statusApprove;
    }

    public void setStatusApprove(String statusApprove) {
        this.statusApprove = statusApprove;
    }
}
