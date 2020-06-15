package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.mobileapi.model.ProfilBiodata;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

public class ProfilBiodataController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(ProfilBiodataController.class);

    private BiodataBo biodataBoProxy;
//    private UserBo userBoProxy;

    private ProfilBiodata model = new ProfilBiodata();
    private List<ProfilBiodata> listOfBiodata;

    private String nip;

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[BiodataController.create] end process POST /activity <<<");

//        Biodata searchBiodata = new Biodata();
//        searchBiodata.setNip(nip);
//        searchBiodata.setFlag("Y");

        Biodata bio = null;
        try {
            bio = biodataBoProxy.detailBiodataSys(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "BiodataController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[BiodataController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }


        model = new ProfilBiodata();
        if(bio != null){
            model.setNip(bio.getNip());
            model.setNamaPegawai(bio.getNamaPegawai());
            model.setGelarDepan(bio.getGelarDepan());
            model.setGelarBelakang(bio.getGelarBelakang());
            model.setNoKtp(bio.getNoKtp());
            model.setAlamat(bio.getAlamat());
            model.setTempatLahir(bio.getTempatLahir());
//                newBiodata.setTanggalLahir((Date) personal);
            model.setTanggalLahirSt(bio.getStTanggalLahir());
//                newBiodata.setTanggalAktif((Date) personal);
            model.setTanggalAktifSt(bio.getStTanggalAktif());
            model.setTanggalPensiun(bio.getStTanggalPensiun());
            model.setMasakerja(bio.getMasaKerja());
            model.setBranchName(bio.getBranchName());
            model.setDivisiName(bio.getDivisiName());
            model.setPositionName(bio.getPositionName());
            model.setNpwp(bio.getNpwp());
            model.setStatusPegawai(bio.getStatusPegawaiName());
            model.setTipePegawai(bio.getTipePegawaiName());
            model.setStatusGiling(bio.getStatusGiling());
            model.setZakatProfesi(bio.getFlagZakat());
            model.setNipLama(bio.getNipLama());
            model.setPathFoto(bio.getPathFoto());
            model.setFotoUpload(bio.getFotoUpload());

        }
        logger.info("[BiodataController.create] end process POST /activity <<<");
        return new DefaultHttpHeaders("success").disableCaching();
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public Object getModel() {
        return (listOfBiodata != null ? listOfBiodata : model);
    }
}
